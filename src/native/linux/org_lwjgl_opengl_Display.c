/* 
 * Copyright (c) 2002-2004 LWJGL Project
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are 
 * met:
 * 
 * * Redistributions of source code must retain the above copyright 
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of 
 *   its contributors may be used to endorse or promote products derived 
 *   from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * $Id$
 *
 * Linux specific display functions.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <jni.h>
#include <jawt.h>
#include "common_tools.h"
#include "extgl.h"
#include "extgl_glx.h"
#include "Window.h"
#include "context.h"
#include "display.h"
#include "org_lwjgl_opengl_LinuxDisplay.h"
#include "org_lwjgl_opengl_LinuxDisplayPeerInfo.h"

//#define USEGLX13 extension_flags.GLX13
#define ERR_MSG_SIZE 1024

typedef struct {
	unsigned long flags;
	unsigned long functions;
	unsigned long decorations;
	long input_mode;
	unsigned long status;
} MotifWmHints;

#define MWM_HINTS_DECORATIONS   (1L << 1)

typedef enum {FULLSCREEN_LEGACY, FULLSCREEN_NETWM, WINDOWED} window_mode;

//static GLXContext display_context = NULL; // OpenGL rendering context
//static GLXFBConfig *configs = NULL;
static GLXWindow glx_window = None;
//static XVisualInfo *vis_info = NULL;

static Atom delete_atom;
static Colormap cmap;
static Window current_win;
static window_mode current_window_mode;
static int current_height;
static int current_width;

static bool input_released;

static bool dirty;
static bool vsync_enabled;
static bool minimized;
static bool focused;
static bool closerequested;
static bool grab;

static int current_screen;
static Display *display_connection = NULL;
static int display_connection_usage = 0;
static bool async_x_error;
static char error_message[ERR_MSG_SIZE];
static Atom warp_atom;

/*GLXContext getDisplayContext(void) {
	return display_context;
}
*/
int getCurrentScreen(void) {
	return current_screen;
}

bool checkXError(JNIEnv *env, Display *disp) {
	XSync(disp, False);
	if (async_x_error) {
		async_x_error = false;
		if (env != NULL)
			throwException(env, error_message);
		else
			printfDebug(error_message);
		return false;
	} else
		return true;
}

static int errorHandler(Display *disp, XErrorEvent *error) {
	char err_msg_buffer[ERR_MSG_SIZE];
	XGetErrorText(disp, error->error_code, err_msg_buffer, ERR_MSG_SIZE);
	err_msg_buffer[ERR_MSG_SIZE - 1] = '\0';
	snprintf(error_message, ERR_MSG_SIZE, "X Error - serial: %d, error_code: %s, request_code: %d, minor_code: %d", (int)error->serial, err_msg_buffer, (int)error->request_code, (int)error->minor_code);
	error_message[ERR_MSG_SIZE - 1] = '\0';
	async_x_error = true;
	return 0;
}


Display *getDisplay(void) {
	return display_connection;
}

static Display *incDisplay(JNIEnv *env) {
	if (display_connection_usage == 0) {
		async_x_error = false;
		XSetErrorHandler(errorHandler);
		display_connection = XOpenDisplay(NULL);
		if (display_connection == NULL) {
			if (env != NULL)
				throwException(env, "Could not open X display connection");
			else
				printfDebugJava(env, "Could not open X display connection");
			return NULL;
		}
		current_screen = XDefaultScreen(getDisplay());
		warp_atom = XInternAtom(display_connection, "_LWJGL_WARP", False);
	}
	async_x_error = false;
	display_connection_usage++;
	return display_connection;
}

Atom getWarpAtom(void) {
	return warp_atom;
}

static void decDisplay(void) {
	display_connection_usage--;
	if (display_connection_usage == 0) {
		XCloseDisplay(display_connection);
		display_connection = NULL;
	}
}

static void waitMapped(Window win) {
	XEvent event;
	do {
		XMaskEvent(getDisplay(), StructureNotifyMask, &event);
	} while ((event.type != MapNotify) || (event.xmap.event != win));
}

static void updateInputGrab(void) {
	updatePointerGrab();
	updateKeyboardGrab();
}

static void setRepeatMode(JNIEnv *env, int mode) {
	XKeyboardControl repeat_mode;
	repeat_mode.auto_repeat_mode = mode;
	Display *disp = XOpenDisplay(NULL);
	if (disp == NULL) {
		printfDebugJava(env, "Could not open display to set repeat mode");
		return;
	}
	XChangeKeyboardControl(disp, KBAutoRepeatMode, &repeat_mode);
	XCloseDisplay(disp);
}

static void setDecorations(int dec) {
	Atom motif_hints_atom = XInternAtom(getDisplay(), "_MOTIF_WM_HINTS", False);
	MotifWmHints motif_hints;
	motif_hints.flags = MWM_HINTS_DECORATIONS;
	motif_hints.decorations = dec;
	XChangeProperty (getDisplay(), getCurrentWindow(), motif_hints_atom, motif_hints_atom, 32, PropModeReplace, (unsigned char *)&motif_hints, sizeof(MotifWmHints)/sizeof(long));
}

static bool releaseInput(JNIEnv *env) {
	if (isLegacyFullscreen() || input_released)
		return false;
	input_released = true;
	setRepeatMode(env, AutoRepeatModeDefault);
	updateInputGrab();
	if (current_window_mode == FULLSCREEN_NETWM) {
		XIconifyWindow(getDisplay(), getCurrentWindow(), getCurrentScreen());
		resetDisplayMode(env, getCurrentScreen(), true);
	}
	return true;
}

static void acquireInput(JNIEnv *env) {
	if (isLegacyFullscreen() || !input_released)
		return;
	input_released = false;
	setRepeatMode(env, AutoRepeatModeOff);
	updateInputGrab();
	if (current_window_mode == FULLSCREEN_NETWM) {
		temporaryRestoreMode(env, getCurrentScreen());
	}
}

bool isFullscreen(void) {
	return current_window_mode == FULLSCREEN_LEGACY || current_window_mode == FULLSCREEN_NETWM;
}

bool isLegacyFullscreen(void) {
	return current_window_mode == FULLSCREEN_LEGACY;
}

bool shouldGrab(void) {
	return !input_released && grab;
}

bool isGrabbed(void) {
	return grab;
}

void setGrab(bool new_grab) {
	if (new_grab != grab) {
		grab = new_grab;
		updateInputGrab();
	}
}

static void checkInput(JNIEnv *env) {
	Window win;
	int revert_mode;
	XGetInputFocus(getDisplay(), &win, &revert_mode);
	if (win == current_win) {
		acquireInput(env);
		focused = true;
	} else {
		releaseInput(env);
		focused = false;
	}
}

void handleMessages(JNIEnv *env) {
	XEvent event;
/*	Window win;
	int revert_mode;*/
	while (XPending(getDisplay()) > 0) {
		XNextEvent(getDisplay(), &event);
		if (XFilterEvent(&event, None) == True)
			continue;
		switch (event.type) {
			case ClientMessage:
				if (event.xclient.message_type == warp_atom) {
					handleWarpEvent(&(event.xclient));
				} else if ((event.xclient.format == 32) && ((Atom)event.xclient.data.l[0] == delete_atom))
					closerequested = true;
				break;
/*			case FocusOut:
				XGetInputFocus(getDisplay(), &win, &revert_mode);
				if (win != current_win) {
					releaseInput();
					focused = false;
				}
				break;
			case FocusIn:
				checkInput();
				break;*/
			case MapNotify:
				dirty = true;
				minimized = false;
				break;
			case UnmapNotify:
				dirty = true;
				minimized = true;
				break;
			case Expose:
				dirty = true;
				break;
			case ButtonPress:
				handleButtonPress(&(event.xbutton));
				break;
			case ButtonRelease:
				handleButtonRelease(&(event.xbutton));
				break;
			case MotionNotify:
				handlePointerMotion(&(event.xmotion));
				break;
			case KeyPress:
			case KeyRelease:
				handleKeyEvent(&(event.xkey));
				break;
		}
	}
	checkInput(env);
}

static void setWindowTitle(const char *title) {
	XStoreName(getDisplay(), current_win, title);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_incDisplay(JNIEnv *env, jclass clazz) {
	incDisplay(env);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_decDisplay(JNIEnv *env, jclass clazz) {
	decDisplay();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplayPeerInfo_initDrawable(JNIEnv *env, jclass clazz, jobject peer_info_handle) {
	X11PeerInfo *peer_info = (*env)->GetDirectBufferAddress(env, peer_info_handle);
	if (peer_info->glx13)
		peer_info->drawable = glx_window;
	else
		peer_info->drawable = getCurrentWindow();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplayPeerInfo_initDefaultPeerInfo(JNIEnv *env, jclass clazz, jobject peer_info_handle, jobject pixel_format) {
	initPeerInfo(env, peer_info_handle, getDisplay(), getCurrentScreen(), pixel_format, true, GLX_WINDOW_BIT, true, false);
}
  
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nSetTitle(JNIEnv * env, jclass clazz, jstring title_obj) {
	char * title = GetStringNativeChars(env, title_obj);
	setWindowTitle(title);
	free(title);
}

static void destroyWindow(JNIEnv *env) {
	if (glx_window != None) {
		glXDestroyWindow(getDisplay(), glx_window);
		glx_window = None;
	}
	XDestroyWindow(getDisplay(), current_win);
	XFreeColormap(getDisplay(), cmap);
	setRepeatMode(env, AutoRepeatModeDefault);
}

static bool isNetWMFullscreenSupported(JNIEnv *env) {
	unsigned long nitems;
	Atom actual_type;
	int actual_format;
	unsigned long bytes_after;
	Atom *supported_list;
	Atom netwm_supported_atom = XInternAtom(getDisplay(), "_NET_SUPPORTED", False);
	int result = XGetWindowProperty(getDisplay(), RootWindow(getDisplay(), getCurrentScreen()), netwm_supported_atom, 0, 10000, False, AnyPropertyType, &actual_type, &actual_format, &nitems, &bytes_after, (void *)&supported_list);
	if (result != Success) {
		printfDebugJava(env, "Unable to query _NET_SUPPORTED window property");
		return false;
	}
	Atom fullscreen_atom = XInternAtom(getDisplay(), "_NET_WM_STATE_FULLSCREEN", False);
	bool supported = false;
	unsigned long i;
	for (i = 0; i < nitems; i++) {
		if (fullscreen_atom == supported_list[i]) {
			supported = true;
			break;
		}
	}
	XFree(supported_list);
	return supported;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nReshape(JNIEnv *env, jclass clazz, jint x, jint y, jint width, jint height) {
	XMoveWindow(getDisplay(), getCurrentWindow(), x, y);
}

static bool createWindow(JNIEnv* env, X11PeerInfo *peer_info, int x, int y, int width, int height) {
	bool undecorated = getBooleanProperty(env, "org.lwjgl.opengl.Window.undecorated");
	dirty = true;
	focused = true;
	minimized = false;
	closerequested = false;
	vsync_enabled = false;
	grab = false;
	Window root_win;
	Window win;
	XSetWindowAttributes attribs;
	int attribmask;

	input_released = false;
	current_width = width;
	current_height = height;
	root_win = RootWindow(getDisplay(), getCurrentScreen());
	XVisualInfo *vis_info = getVisualInfoFromPeerInfo(env, peer_info);
	if (vis_info == NULL)
		return false;
	cmap = XCreateColormap(getDisplay(), root_win, vis_info->visual, AllocNone);
	attribs.colormap = cmap;
	attribs.event_mask = ExposureMask | /*FocusChangeMask | */VisibilityChangeMask | StructureNotifyMask | KeyPressMask | KeyReleaseMask | ButtonPressMask | ButtonReleaseMask | PointerMotionMask;
	attribs.background_pixel = 0xFF000000;
	attribs.win_gravity = NorthWestGravity;
	attribmask = CWColormap | CWBackPixel | CWEventMask | CWWinGravity;
	if (isLegacyFullscreen()) {
		attribmask |= CWOverrideRedirect;
		attribs.override_redirect = True;
	}
	win = XCreateWindow(getDisplay(), root_win, x, y, width, height, 0, vis_info->depth, InputOutput, vis_info->visual, attribmask, &attribs);
	XFree(vis_info);
	if (!checkXError(env, getDisplay())) {
		XFreeColormap(getDisplay(), cmap);
		return false;
	}
	printfDebugJava(env, "Created window");
	current_win = win;
	if (current_window_mode != WINDOWED || undecorated) {
		// Use Motif decoration hint property and hope the window manager respects them
		setDecorations(0);
	}
	XSizeHints * size_hints = XAllocSizeHints();
	size_hints->flags = PMinSize | PMaxSize;
	size_hints->min_width = width;
	size_hints->max_width = width;
	size_hints->min_height = height;
	size_hints->max_height = height;
	XSetWMNormalHints(getDisplay(), win, size_hints);
	XFree(size_hints);
	delete_atom = XInternAtom(getDisplay(), "WM_DELETE_WINDOW", False);
	XSetWMProtocols(getDisplay(), win, &delete_atom, 1);
	if (current_window_mode == FULLSCREEN_NETWM) {
		Atom fullscreen_atom = XInternAtom(getDisplay(), "_NET_WM_STATE_FULLSCREEN", False);
		XChangeProperty(getDisplay(), getCurrentWindow(), XInternAtom(getDisplay(), "_NET_WM_STATE", False),
						XInternAtom(getDisplay(), "ATOM", False), 32, PropModeReplace, (const unsigned char*)&fullscreen_atom, 1);
	}
	XMapRaised(getDisplay(), win);
	waitMapped(win);
	XClearWindow(getDisplay(), win);
	setRepeatMode(env, AutoRepeatModeOff);
	if (!checkXError(env, getDisplay())) {
		destroyWindow(env);
		return false;
	}
	return true;
}

Window getCurrentWindow(void) {
	return current_win;
}

int getWindowWidth(void) {
	return current_width;
}

int getWindowHeight(void) {
	return current_height;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nUpdate
  (JNIEnv *env, jclass clazz)
{
	handleMessages(env);
}

/*bool releaseContext(GLXContext context) {
	if (glXGetCurrentContext() != context)
		return true;
	if (USEGLX13)
		return glXMakeContextCurrent(getDisplay(), None, None, NULL) == True;
	else
		return glXMakeCurrent(getDisplay(), None, NULL) == True;
}

static bool makeCurrent(void) {
	if (USEGLX13)
		return glXMakeContextCurrent(getDisplay(), glx_window, glx_window, display_context) == True;
	else
		return glXMakeCurrent(getDisplay(), getCurrentWindow(), display_context) == True;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nMakeCurrent
  (JNIEnv *env, jclass clazz)
{
	if (!makeCurrent())
		throwException(env, "Could not make display context current");
}
*/
/*static void destroyContext(void) {
	if (USEGLX13) {
		XFree(configs);
		configs = NULL;
	}
	XFree(vis_info);
	vis_info = NULL;
	releaseContext(display_context);
	glXDestroyContext(getDisplay(), display_context);
	display_context = NULL;
}
*/
/*static bool initWindowGLX13(JNIEnv *env, jobject pixel_format) {
	configs = chooseVisualGLX13(env, getDisplay(), getCurrentScreen(), pixel_format, true, GLX_WINDOW_BIT, true);
	if (configs == NULL) {
		throwException(env, "Could not find a matching pixel format");
		return false;
	}
	display_context = glXCreateNewContext(getDisplay(), configs[0], GLX_RGBA_TYPE, NULL, True);
	if (display_context == NULL) {
		XFree(configs);
		throwException(env, "Could not create a GLX context");
		return false;
	}
	jboolean allow_software_acceleration = getBooleanProperty(env, "org.lwjgl.opengl.Display.allowSoftwareOpenGL");
	if (!allow_software_acceleration && (glXIsDirect(getDisplay(), display_context) == False)) {
		glXDestroyContext(getDisplay(), display_context);
		XFree(configs);
		throwException(env, "Could not create a direct GLX context");
		return false;
	}
	vis_info = glXGetVisualFromFBConfig(getDisplay(), configs[0]);
	if (vis_info == NULL) {
		glXDestroyContext(getDisplay(), display_context);
		XFree(configs);
		throwException(env, "Could not get visual from FB config");
		return false;
	}
	if (!checkXError(env, getDisplay())) {
		glXDestroyContext(getDisplay(), display_context);
		XFree(configs);
		XFree(vis_info);
		return false;
	}
	return true;
}

static bool initWindowGLX(JNIEnv *env, jobject pixel_format) {
	vis_info = chooseVisualGLX(env, getDisplay(), getCurrentScreen(), pixel_format, true, true);
	if (vis_info == NULL) {
		throwException(env, "Could not find a matching pixel format");
		return false;
	}
	display_context = glXCreateContext(getDisplay(), vis_info, NULL, True);
	if (display_context == NULL) {
		XFree(vis_info);
		throwException(env, "Could not create a GLX context");
		return false;
	}
	jboolean allow_software_acceleration = getBooleanProperty(env, "org.lwjgl.opengl.Display.allowSoftwareOpenGL");
	if (!allow_software_acceleration && glXIsDirect(getDisplay(), display_context) == False) {
		glXDestroyContext(getDisplay(), display_context);
		XFree(vis_info);
		throwException(env, "Could not create a direct GLX context");
		return false;
	}
	if (!checkXError(env, getDisplay())) {
		glXDestroyContext(getDisplay(), display_context);
		XFree(vis_info);
		return false;
	}
	return true;
}
*/
JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nGetAvailableDisplayModes(JNIEnv *env, jclass clazz) {
	return getAvailableDisplayModes(env, getCurrentScreen());
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nSwitchDisplayMode(JNIEnv *env, jclass clazz, jobject mode) {
	switchDisplayMode(env, mode, getCurrentScreen());
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nResetDisplayMode(JNIEnv *env, jclass clazz) {
	resetDisplayMode(env, getCurrentScreen(), false);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nGetGammaRampLength(JNIEnv *env, jclass clazz) {
	return (jint)getGammaRampLength(env, getCurrentScreen());
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nSetGammaRamp(JNIEnv *env, jclass clazz, jobject gamma_buffer) {
	setGammaRamp(env, gamma_buffer, getCurrentScreen());
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nInit(JNIEnv *env, jclass clazz) {
	return initDisplay(env, getCurrentScreen());
}

/*JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nCreateContext(JNIEnv *env, jclass clazz, jobject pixel_format) {
	Display *disp = incDisplay(env);
	if (disp == NULL) {
		return;
	}
	current_screen = XDefaultScreen(disp);
	if (!extgl_InitGLX(env, disp, current_screen)) {
		decDisplay();
		throwException(env, "Could not init GLX");
		return;
	}

	bool result;
	if (USEGLX13) {
		result = initWindowGLX13(env, pixel_format);
	} else {
		result = initWindowGLX(env, pixel_format);
	}
	if (!result)
		decDisplay();
}
*/
/*JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nDestroyContext(JNIEnv *env, jclass clazz) {
	destroyContext();
	decDisplay();
}
*/
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nCreateWindow(JNIEnv *env, jclass clazz, jobject peer_info_handle, jobject mode, jboolean fullscreen, jint x, jint y) {
	bool current_fullscreen = fullscreen == JNI_TRUE;
	if (current_fullscreen) {
		if (getCurrentDisplayModeExtension() == XRANDR && isNetWMFullscreenSupported(env)) {
			printfDebugJava(env, "Using NetWM for fullscreen window");
			current_window_mode = FULLSCREEN_NETWM;
		} else {
			printfDebugJava(env, "Using legacy mode for fullscreen window");
			current_window_mode = FULLSCREEN_LEGACY;
		}
	} else
		current_window_mode = WINDOWED;
	X11PeerInfo *peer_info = (*env)->GetDirectBufferAddress(env, peer_info_handle);
	GLXFBConfig *fb_config = NULL;
	if (peer_info->glx13) {
		fb_config = getFBConfigFromPeerInfo(env, peer_info);
		if (fb_config == NULL)
			return;
	}
	jclass cls_displayMode = (*env)->GetObjectClass(env, mode);
	jfieldID fid_width = (*env)->GetFieldID(env, cls_displayMode, "width", "I");
	jfieldID fid_height = (*env)->GetFieldID(env, cls_displayMode, "height", "I");
	int width = (*env)->GetIntField(env, mode, fid_width);
	int height = (*env)->GetIntField(env, mode, fid_height);
	bool window_created = createWindow(env, peer_info, x, y, width, height);
	if (!window_created) {
		return;
	}
	if (peer_info->glx13) {
		glx_window = glXCreateWindow(getDisplay(), *fb_config, getCurrentWindow(), NULL);
		XFree(fb_config);
	}
	if (!checkXError(env, getDisplay())) {
		glXDestroyWindow(getDisplay(), glx_window);
		destroyWindow(env);
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nDestroyWindow(JNIEnv *env, jclass clazz) {
	destroyWindow(env);
}

/*JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nSwapBuffers(JNIEnv * env, jclass clazz)
{
	dirty = false;
	if (USEGLX13)
		glXSwapBuffers(getDisplay(), glx_window);
	else
		glXSwapBuffers(getDisplay(), getCurrentWindow());
}
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nIsDirty
  (JNIEnv *env, jclass clazz) {
	bool result = dirty;
	dirty = false;
	return result ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nIsVisible
  (JNIEnv *env, jclass clazz) {
	return minimized ? JNI_FALSE : JNI_TRUE;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nIsCloseRequested
  (JNIEnv *env, jclass clazz) {
	bool saved = closerequested;
	closerequested = false;
	return saved;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nIsActive
  (JNIEnv *env, jclass clazz) {
	return focused || isLegacyFullscreen() ? JNI_TRUE : JNI_FALSE;
}

/*JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nSetVSyncEnabled
  (JNIEnv *env, jclass clazz, jboolean sync)
{
	if (extension_flags.GLX_SGI_swap_control) {
		bool vsync = sync == JNI_TRUE ? true : false;
		if (vsync != vsync_enabled) {
			int interval = vsync ? 1 : 0;
			glXSwapIntervalSGI(interval);
			vsync_enabled = vsync;
		}
	}
}
*/
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_lockAWT(JNIEnv *env, jclass clazz) {
	JAWT jawt;
	jawt.version = JAWT_VERSION_1_4;
	if (JAWT_GetAWT(env, &jawt) != JNI_TRUE) {
		throwGeneralException(env, "java/lang/RuntimeException", "GetAWT failed");
		return;
	}
	jawt.Lock(env);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_unlockAWT(JNIEnv *env, jclass clazz) {
	JAWT jawt;
	jawt.version = JAWT_VERSION_1_4;
	if (JAWT_GetAWT(env, &jawt) != JNI_TRUE) {
		throwGeneralException(env, "java/lang/RuntimeException", "GetAWT failed");
		return;
	}
	jawt.Unlock(env);
}
