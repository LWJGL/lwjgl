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
#include "common_tools.h"
#include "extgl.h"
#include "extgl_glx.h"
#include "Window.h"
#include "display.h"
#include "org_lwjgl_opengl_Display.h"

#define USEGLX13 extgl_Extensions.GLX13
#define ERR_MSG_SIZE 1024

static GLXContext context = NULL; // OpenGL rendering context
static GLXFBConfig *configs = NULL;
static GLXWindow glx_window;
static XVisualInfo *vis_info = NULL;

static Atom delete_atom;
static Colormap cmap;
static Window current_win;
static bool current_fullscreen;
static int current_height;
static int current_width;

static bool input_released;

static bool dirty;
static bool vsync_enabled;
static bool minimized;
static bool focused;
static bool closerequested;
static bool grab;
static bool ignore_motion_events;

static int current_screen;
static Display *display_connection = NULL;
static int display_connection_usage = 0;
static bool async_x_error;
static char error_message[ERR_MSG_SIZE];
static Atom warp_atom;

GLXFBConfig getCurrentGLXFBConfig(void) {
	return configs[0];
}

GLXContext getCurrentGLXContext(void) {
	return context;
}

Atom getWarpAtom(void) {
	return warp_atom;
}

int getCurrentScreen(void) {
	return current_screen;
}

bool checkXError(JNIEnv *env) {
	XSync(getDisplay(), False);
	if (async_x_error) {
		async_x_error = false;
		throwException(env, error_message);
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

Display *incDisplay(JNIEnv *env) {
	if (display_connection_usage == 0) {
		async_x_error = false;
		XSetErrorHandler(errorHandler);
		display_connection = XOpenDisplay(NULL);
		if (display_connection == NULL) {
			throwException(env, "Could not open X display");
			return NULL;
		}
		warp_atom = XInternAtom(getDisplay(), "ignore_warp_atom", False);
	}
	async_x_error = false;
	display_connection_usage++;
	return display_connection;
}

void decDisplay(void) {
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

static void setRepeatMode(int mode) {
	XKeyboardControl repeat_mode;
	repeat_mode.auto_repeat_mode = mode;
	Display *disp = XOpenDisplay(NULL);
	if (disp == NULL) {
		printfDebug("Could not open display to set repeat mode\n");
		return;
	}
	XChangeKeyboardControl(disp, KBAutoRepeatMode, &repeat_mode);
	XCloseDisplay(disp);
}

bool releaseInput(void) {
	if (current_fullscreen || input_released)
		return false;
	input_released = true;
	setRepeatMode(AutoRepeatModeDefault);
	updateInputGrab();
	return true;
}

static void acquireInput(void) {
	if (current_fullscreen || !input_released)
		return;
	input_released = false;
	setRepeatMode(AutoRepeatModeOff);
	updateInputGrab();
}

bool isFullscreen(void) {
	return current_fullscreen;
}

bool shouldGrab(void) {
	return !input_released && grab;
}

void setGrab(bool new_grab) {
	grab = new_grab;
	updateInputGrab();
}

static void handleMotion(XMotionEvent *event) {
	if (ignore_motion_events) {
		resetCursor(event->x, event->y);
	} else {
		handlePointerMotion(event);
	}
}

static void checkInput(void) {
	Window win;
	int revert_mode;
	XGetInputFocus(getDisplay(), &win, &revert_mode);
	if (win == current_win) {
		acquireInput();
		focused = true;
	}
}
static void handleMessages() {
	XEvent event;
	Window win;
	int revert_mode;
	while (XPending(getDisplay()) > 0) {
		XNextEvent(getDisplay(), &event);
		switch (event.type) {
			case ClientMessage:
				if (event.xclient.message_type == getWarpAtom()) {
					ignore_motion_events = event.xclient.data.b[0] == 1 ? true : false;
				} else if ((event.xclient.format == 32) && ((Atom)event.xclient.data.l[0] == delete_atom))
					closerequested = true;
				break;
			case FocusOut:
				XGetInputFocus(getDisplay(), &win, &revert_mode);
				if (win != current_win) {
					releaseInput();
					focused = false;
				}
				break;
			case FocusIn:
				checkInput();
				break;
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
				checkInput();
				handleButtonPress(&(event.xbutton));
				break;
			case ButtonRelease:
				handleButtonRelease(&(event.xbutton));
				break;
			case MotionNotify:
				handleMotion(&(event.xmotion));
				break;
			case KeyPress:
			case KeyRelease:
				handleKeyEvent(&(event.xkey));
				break;
		}
	}
}

static void setWindowTitle(const char *title) {
	XStoreName(getDisplay(), current_win, title);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_nSetTitle
  (JNIEnv * env, jclass clazz, jstring title_obj)
{
	const char * title = env->GetStringUTFChars(title_obj, NULL);
	setWindowTitle(title);
	env->ReleaseStringUTFChars(title_obj, title);
}

static void destroyWindow(void) {
	if (USEGLX13)
		glXDestroyWindow(getDisplay(), glx_window);
	XDestroyWindow(getDisplay(), current_win);
	XFreeColormap(getDisplay(), cmap);
	setRepeatMode(AutoRepeatModeDefault);
}

static bool createWindow(JNIEnv* env, int width, int height) {
	bool undecorated = getBooleanProperty(env, "org.lwjgl.opengl.Window.undecorated");
	dirty = true;
	focused = true;
	minimized = false;
	closerequested = false;
	vsync_enabled = false;
	grab = false;
	ignore_motion_events = false;
	Window root_win;
	Window win;
	XSetWindowAttributes attribs;
	int attribmask;

	input_released = false;
	current_width = width;
	current_height = height;

	root_win = RootWindow(getDisplay(), getCurrentScreen());
	cmap = XCreateColormap(getDisplay(), root_win, vis_info->visual, AllocNone);
	attribs.colormap = cmap;
	attribs.event_mask = ExposureMask | FocusChangeMask | VisibilityChangeMask| StructureNotifyMask | KeyPressMask | KeyReleaseMask | ButtonPressMask | ButtonReleaseMask | PointerMotionMask;
	attribs.background_pixel = 0xFF000000;
	attribmask = CWColormap | CWBackPixel | CWEventMask;
	if (current_fullscreen || undecorated) {
		attribmask |= CWOverrideRedirect;
		attribs.override_redirect = True;
	}
	win = XCreateWindow(getDisplay(), root_win, 0, 0, width, height, 0, vis_info->depth, InputOutput, vis_info->visual, attribmask, &attribs);
	if (!checkXError(env)) {
		XFreeColormap(getDisplay(), cmap);
		return false;
	}
	printfDebug("Created window\n");
	current_win = win;
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
	XMapRaised(getDisplay(), win);
	waitMapped(win);
	XClearWindow(getDisplay(), win);
	setRepeatMode(AutoRepeatModeOff);
	if (!checkXError(env)) {
		destroyWindow();
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

/*
 * Class:     org_lwjgl_Window
 * Method:    nUpdate
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_nUpdate
  (JNIEnv *env, jclass clazz)
{
	handleMessages();
}

static bool makeCurrent(void) {
	if (USEGLX13)
		return glXMakeContextCurrent(getDisplay(), glx_window, glx_window, context) == True;
	else
		return glXMakeCurrent(getDisplay(), getCurrentWindow(), context) == True;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_nMakeCurrent
  (JNIEnv *env, jclass clazz)
{
	if (!makeCurrent())
		throwException(env, "Could not make display context current");
}

int convertToBPE(int bpp) {
	int bpe;
	switch (bpp) {
		case 32:
		case 24:
			bpe = 8;
			break;
		case 16: /* Fall through */
		default:
			bpe = 4;
			break;
	}
	return bpe;
}

GLXContext getCurrentContext(void) {
	return context;
}

static GLXFBConfig *chooseVisualGLX13FromBPP(JNIEnv *env, jobject pixel_format, int bpp, int drawable_type, bool double_buffer) {
	jclass cls_pixel_format = env->GetObjectClass(pixel_format);
	int alpha = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "alpha", "I"));
	int depth = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "depth", "I"));
	int stencil = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "stencil", "I"));
	int samples = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "samples", "I"));
	int num_aux_buffers = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "num_aux_buffers", "I"));
	int accum_bpp = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "accum_bpp", "I"));
	int accum_alpha = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "accum_alpha", "I"));
	bool stereo = (bool)env->GetBooleanField(pixel_format, env->GetFieldID(cls_pixel_format, "stereo", "Z"));

	int bpe = convertToBPE(bpp);
	int accum_bpe = convertToBPE(accum_bpp);
	attrib_list_t attrib_list;
	initAttribList(&attrib_list);
	putAttrib(&attrib_list, GLX_RENDER_TYPE); putAttrib(&attrib_list, GLX_RGBA_BIT);
	putAttrib(&attrib_list, GLX_DOUBLEBUFFER); putAttrib(&attrib_list, double_buffer ? True : False);
	putAttrib(&attrib_list, GLX_DRAWABLE_TYPE); putAttrib(&attrib_list, drawable_type);
	putAttrib(&attrib_list, GLX_DEPTH_SIZE); putAttrib(&attrib_list, depth);
	putAttrib(&attrib_list, GLX_RED_SIZE); putAttrib(&attrib_list, bpe);
	putAttrib(&attrib_list, GLX_GREEN_SIZE); putAttrib(&attrib_list, bpe);
	putAttrib(&attrib_list, GLX_BLUE_SIZE); putAttrib(&attrib_list, bpe);
	putAttrib(&attrib_list, GLX_ALPHA_SIZE); putAttrib(&attrib_list, alpha);
	putAttrib(&attrib_list, GLX_STENCIL_SIZE); putAttrib(&attrib_list, stencil);
	putAttrib(&attrib_list, GLX_AUX_BUFFERS); putAttrib(&attrib_list, num_aux_buffers);
	putAttrib(&attrib_list, GLX_ACCUM_RED_SIZE); putAttrib(&attrib_list, accum_bpe);
	putAttrib(&attrib_list, GLX_ACCUM_GREEN_SIZE); putAttrib(&attrib_list, accum_bpe);
	putAttrib(&attrib_list, GLX_ACCUM_BLUE_SIZE); putAttrib(&attrib_list, accum_bpe);
	putAttrib(&attrib_list, GLX_ACCUM_ALPHA_SIZE); putAttrib(&attrib_list, accum_alpha);
	putAttrib(&attrib_list, GLX_STEREO); putAttrib(&attrib_list, stereo ? True : False);
	if (samples > 0 && extgl_Extensions.GLX_ARB_multisample) {
		putAttrib(&attrib_list, GLX_SAMPLE_BUFFERS_ARB); putAttrib(&attrib_list, 1);
		putAttrib(&attrib_list, GLX_SAMPLES_ARB); putAttrib(&attrib_list, samples);
	}
	putAttrib(&attrib_list, None); putAttrib(&attrib_list, None);
	int num_formats = 0;
	GLXFBConfig* configs = glXChooseFBConfig(getDisplay(), getCurrentScreen(), attrib_list.attribs, &num_formats);
	if (num_formats > 0) {
		return configs;
	} else {
		if (configs != NULL)
			XFree(configs);
		return NULL;
	}
}

GLXFBConfig *chooseVisualGLX13(JNIEnv *env, jobject pixel_format, bool use_display_bpp, int drawable_type, bool double_buffer) {
	jclass cls_pixel_format = env->GetObjectClass(pixel_format);
	int bpp;
	if (use_display_bpp) {
		bpp = XDefaultDepthOfScreen(XScreenOfDisplay(getDisplay(), getCurrentScreen()));
		GLXFBConfig *configs = chooseVisualGLX13FromBPP(env, pixel_format, bpp, drawable_type, double_buffer);
		if (configs != NULL)
			return configs;
		else
			bpp = 16;
	} else
		bpp = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "bpp", "I"));
	return chooseVisualGLX13FromBPP(env, pixel_format, bpp, drawable_type, double_buffer);
}

static XVisualInfo *chooseVisualGLX(JNIEnv *env, jobject pixel_format) {
	int bpp = XDefaultDepthOfScreen(XScreenOfDisplay(getDisplay(), getCurrentScreen()));
	jclass cls_pixel_format = env->GetObjectClass(pixel_format);
	int alpha = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "alpha", "I"));
	int depth = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "depth", "I"));
	int stencil = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "stencil", "I"));
	int samples = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "samples", "I"));
	int num_aux_buffers = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "num_aux_buffers", "I"));
	int accum_bpp = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "accum_bpp", "I"));
	int accum_alpha = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "accum_alpha", "I"));
	bool stereo = (bool)env->GetBooleanField(pixel_format, env->GetFieldID(cls_pixel_format, "stereo", "Z"));

	int bpe = convertToBPE(bpp);
	int accum_bpe = convertToBPE(accum_bpp);
	attrib_list_t attrib_list;
	initAttribList(&attrib_list);
	putAttrib(&attrib_list, GLX_RGBA);
	putAttrib(&attrib_list, GLX_DOUBLEBUFFER);
	putAttrib(&attrib_list, GLX_DEPTH_SIZE); putAttrib(&attrib_list, depth);
	putAttrib(&attrib_list, GLX_RED_SIZE); putAttrib(&attrib_list, bpe);
	putAttrib(&attrib_list, GLX_GREEN_SIZE); putAttrib(&attrib_list, bpe);
	putAttrib(&attrib_list, GLX_BLUE_SIZE); putAttrib(&attrib_list, bpe);
	putAttrib(&attrib_list, GLX_ALPHA_SIZE); putAttrib(&attrib_list, alpha);
	putAttrib(&attrib_list, GLX_STENCIL_SIZE); putAttrib(&attrib_list, stencil);
	putAttrib(&attrib_list, GLX_AUX_BUFFERS); putAttrib(&attrib_list, num_aux_buffers);
	putAttrib(&attrib_list, GLX_ACCUM_RED_SIZE); putAttrib(&attrib_list, accum_bpe);
	putAttrib(&attrib_list, GLX_ACCUM_GREEN_SIZE); putAttrib(&attrib_list, accum_bpe);
	putAttrib(&attrib_list, GLX_ACCUM_BLUE_SIZE); putAttrib(&attrib_list, accum_bpe);
	putAttrib(&attrib_list, GLX_ACCUM_ALPHA_SIZE); putAttrib(&attrib_list, accum_alpha);
	if (stereo)
		putAttrib(&attrib_list, GLX_STEREO);
	if (samples > 0 && extgl_Extensions.GLX_ARB_multisample) {
		putAttrib(&attrib_list, GLX_SAMPLE_BUFFERS_ARB); putAttrib(&attrib_list, 1);
		putAttrib(&attrib_list, GLX_SAMPLES_ARB); putAttrib(&attrib_list, samples);
	}
	putAttrib(&attrib_list, None);
	return glXChooseVisual(getDisplay(), getCurrentScreen(), attrib_list.attribs);
}

static void dumpVisualInfo(XVisualInfo *vis_info) {
	int alpha, depth, stencil, r, g, b;
	int sample_buffers = 0;
	int samples = 0;
	glXGetConfig(getDisplay(), vis_info, GLX_RED_SIZE, &r);
	glXGetConfig(getDisplay(), vis_info, GLX_GREEN_SIZE, &g);
	glXGetConfig(getDisplay(), vis_info, GLX_BLUE_SIZE, &b);
	glXGetConfig(getDisplay(), vis_info, GLX_ALPHA_SIZE, &alpha);
	glXGetConfig(getDisplay(), vis_info, GLX_DEPTH_SIZE, &depth);
	glXGetConfig(getDisplay(), vis_info, GLX_STENCIL_SIZE, &stencil);
	if (extgl_Extensions.GLX_ARB_multisample) {
		glXGetConfig(getDisplay(), vis_info, GLX_SAMPLE_BUFFERS_ARB, &sample_buffers);
		glXGetConfig(getDisplay(), vis_info, GLX_SAMPLES_ARB, &samples);
	}
	printfDebug("Pixel format info: r = %d, g = %d, b = %d, a = %d, depth = %d, stencil = %d, sample buffers = %d, samples = %d\n", r, g, b, alpha, depth, stencil, sample_buffers, samples);
}

static void destroyContext(void) {
	if (USEGLX13) {
		XFree(configs);
		configs = NULL;
	}
	XFree(vis_info);
	vis_info = NULL;
	glXDestroyContext(getDisplay(), context);
	context = NULL;
}

static bool initWindowGLX13(JNIEnv *env, jobject pixel_format) {
	configs = chooseVisualGLX13(env, pixel_format, true, GLX_WINDOW_BIT, true);
	if (configs == NULL) {
		throwException(env, "Could not find a matching pixel format");
		return false;
	}
	context = glXCreateNewContext(getDisplay(), configs[0], GLX_RGBA_TYPE, NULL, True);
	if (context == NULL) {
		XFree(configs);
		throwException(env, "Could not create a GLX context");
		return false;
	}
	jboolean allow_software_acceleration = getBooleanProperty(env, "org.lwjgl.opengl.Window.allowSoftwareOpenGL");
	if (!allow_software_acceleration && (glXIsDirect(getDisplay(), context) == False)) {
		glXDestroyContext(getDisplay(), context);
		XFree(configs);
		throwException(env, "Could not create a direct GLX context");
		return false;
	}
	vis_info = glXGetVisualFromFBConfig(getDisplay(), configs[0]);
	if (vis_info == NULL) {
		glXDestroyContext(getDisplay(), context);
		XFree(configs);
		throwException(env, "Could not get visual from FB config");
		return false;
	}
	if (!checkXError(env)) {
		glXDestroyContext(getDisplay(), context);
		XFree(configs);
		XFree(vis_info);
		return false;
	}
	return true;
}

static bool initWindowGLX(JNIEnv *env, jobject pixel_format) {
	vis_info = chooseVisualGLX(env, pixel_format);
	if (vis_info == NULL) {
		throwException(env, "Could not find a matching pixel format");
		return false;
	}
	if (isDebugEnabled())
		dumpVisualInfo(vis_info);
	context = glXCreateContext(getDisplay(), vis_info, NULL, True);
	if (context == NULL) {
		XFree(vis_info);
		throwException(env, "Could not create a GLX context");
		return false;
	}
	jboolean allow_software_acceleration = getBooleanProperty(env, "org.lwjgl.opengl.Window.allowSoftwareOpenGL");
	if (!allow_software_acceleration && glXIsDirect(getDisplay(), context) == False) {
		glXDestroyContext(getDisplay(), context);
		XFree(vis_info);
		throwException(env, "Could not create a direct GLX context");
		return false;
	}
	if (!checkXError(env)) {
		glXDestroyContext(getDisplay(), context);
		XFree(vis_info);
		return false;
	}
	return true;
}

JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_opengl_Display_nGetAvailableDisplayModes(JNIEnv *env, jclass clazz) {
	return getAvailableDisplayModes(env);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_switchDisplayMode(JNIEnv *env, jclass clazz, jobject mode) {
	switchDisplayMode(env, mode);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_resetDisplayMode(JNIEnv *env, jclass clazz) {
	resetDisplayMode(env);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Display_getGammaRampLength(JNIEnv *env, jclass clazz) {
	return (jint)getGammaRampLength();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_setGammaRamp(JNIEnv *env, jclass clazz, jobject gamma_buffer) {
	setGammaRamp(env, gamma_buffer);
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_Display_init(JNIEnv *env, jclass clazz) {
	return initDisplay(env);
}

JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_Display_getAdapter(JNIEnv *env , jclass clazz) {
	return NULL;
}

JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_Display_getVersion(JNIEnv *env, jclass clazz) {
	return NULL;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_createContext(JNIEnv *env, jclass clazz, jobject pixel_format) {
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

	if (USEGLX13) {
		initWindowGLX13(env, pixel_format);
	} else {
		initWindowGLX(env, pixel_format);
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_destroyContext(JNIEnv *env, jclass clazz) {
	destroyContext();
	decDisplay();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_nCreateWindow(JNIEnv *env, jclass clazz, jobject mode, jboolean fullscreen) {
	current_fullscreen = fullscreen == JNI_TRUE;
	jclass cls_displayMode = env->GetObjectClass(mode);
	jfieldID fid_width = env->GetFieldID(cls_displayMode, "width", "I");
	jfieldID fid_height = env->GetFieldID(cls_displayMode, "height", "I");
	int width = env->GetIntField(mode, fid_width);
	int height = env->GetIntField(mode, fid_height);
	bool window_created = createWindow(env, width, height);
	if (!window_created) {
		return;
	}
	if (isDebugEnabled())
		dumpVisualInfo(vis_info);
	if (USEGLX13)
		glx_window = glXCreateWindow(getDisplay(), configs[0], getCurrentWindow(), NULL);
	if (!makeCurrent() || !checkXError(env)) {
		glXDestroyWindow(getDisplay(), glx_window);
		destroyWindow();
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_nDestroyWindow(JNIEnv *env, jclass clazz) {
	destroyWindow();
}

/*
 * Class:     org_lwjgl_opengl_GLWindow
 * Method:    swapBuffers
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_swapBuffers(JNIEnv * env, jclass clazz)
{
	dirty = false;
	if (USEGLX13)
		glXSwapBuffers(getDisplay(), glx_window);
	else
		glXSwapBuffers(getDisplay(), getCurrentWindow());
}


/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nIsDirty
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Display_nIsDirty
  (JNIEnv *env, jclass clazz) {
	bool result = dirty;
	dirty = false;
	return result ? JNI_TRUE : JNI_FALSE;
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nIsVisible
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Display_nIsVisible
  (JNIEnv *env, jclass clazz) {
	return minimized ? JNI_FALSE : JNI_TRUE;
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nIsCloseRequested
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Display_nIsCloseRequested
  (JNIEnv *, jclass) {
	bool saved = closerequested;
	closerequested = false;
	return saved;
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nIsActive
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Display_nIsActive
  (JNIEnv *env, jclass clazz) {
	return focused ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_nSetVSyncEnabled
  (JNIEnv * env, jclass clazz, jboolean sync)
{
	if (extgl_Extensions.GLX_SGI_swap_control) {
		bool vsync = sync == JNI_TRUE ? true : false;
		if (vsync != vsync_enabled) {
			int interval = vsync ? 1 : 0;
			glXSwapIntervalSGI(interval);
			vsync_enabled = vsync;
		}
	}
}

