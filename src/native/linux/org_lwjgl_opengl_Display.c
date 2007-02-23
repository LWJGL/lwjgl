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
#include <X11/extensions/xf86vmode.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <jni.h>
#include <jawt.h>
#include "common_tools.h"
#include "extgl.h"
#include "extgl_glx.h"
#include "context.h"
#include "org_lwjgl_opengl_LinuxDisplay.h"
#include "org_lwjgl_opengl_LinuxDisplayPeerInfo.h"

#define ERR_MSG_SIZE 1024

typedef struct {
	unsigned long flags;
	unsigned long functions;
	unsigned long decorations;
	long input_mode;
	unsigned long status;
} MotifWmHints;

#define MWM_HINTS_DECORATIONS   (1L << 1)

static GLXWindow glx_window = None;

static Colormap cmap;
static int current_depth;
static Pixmap current_icon_pixmap;	

static Visual *current_visual;

static bool async_x_error;
static char error_message[ERR_MSG_SIZE];

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

static jlong openDisplay(JNIEnv *env) {
	async_x_error = false;
	XSetErrorHandler(errorHandler);
	Display *display_connection = XOpenDisplay(NULL);
	if (display_connection == NULL) {
		throwException(env, "Could not open X display connection");
		return (intptr_t)NULL;
	}
	return (intptr_t)display_connection;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nGetDefaultScreen(JNIEnv *env, jclass unused, jlong display_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	return XDefaultScreen(disp);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nInternAtom(JNIEnv *env, jclass unused, jlong display_ptr, jstring atom_name_obj, jboolean only_if_exists) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	char *atom_name = GetStringNativeChars(env, atom_name_obj);
	if (atom_name == NULL)
		return 0;
	Atom atom = XInternAtom(disp, atom_name, only_if_exists ? True : False);
	free(atom_name);
	return atom;
}

static void waitMapped(Display *disp, Window win) {
	XEvent event;
	do {
		XMaskEvent(disp, StructureNotifyMask, &event);
	} while ((event.type != MapNotify) || (event.xmap.event != win));
}

static void __attribute__ ((destructor)) my_fini(void) { 
	Display *disp = XOpenDisplay(NULL);
	if (disp == NULL) {
		return;
	}
	XKeyboardControl repeat_mode;
	repeat_mode.auto_repeat_mode = AutoRepeatModeDefault;
	XChangeKeyboardControl(disp, KBAutoRepeatMode, &repeat_mode);
	XCloseDisplay(disp);
} 

static void setDecorations(Display *disp, Window window, int dec) {
	Atom motif_hints_atom = XInternAtom(disp, "_MOTIF_WM_HINTS", False);
	MotifWmHints motif_hints;
	motif_hints.flags = MWM_HINTS_DECORATIONS;
	motif_hints.decorations = dec;
	XChangeProperty(disp, window, motif_hints_atom, motif_hints_atom, 32, PropModeReplace, (unsigned char *)&motif_hints, sizeof(MotifWmHints)/sizeof(long));
}

static bool isLegacyFullscreen(jint window_mode) {
	return window_mode == org_lwjgl_opengl_LinuxDisplay_FULLSCREEN_LEGACY;
}

static void setWindowTitle(Display *disp, Window window, const char *title) {
	XStoreName(disp, window, title);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxDisplay_openDisplay(JNIEnv *env, jclass clazz) {
	return openDisplay(env);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_closeDisplay(JNIEnv *env, jclass clazz, jlong display) {
	Display *disp = (Display *)(intptr_t)display;
	XCloseDisplay(disp);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplayPeerInfo_initDrawable(JNIEnv *env, jclass clazz, jlong window, jobject peer_info_handle) {
	X11PeerInfo *peer_info = (*env)->GetDirectBufferAddress(env, peer_info_handle);
	if (peer_info->glx13)
		peer_info->drawable = glx_window;
	else
		peer_info->drawable = window;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplayPeerInfo_initDefaultPeerInfo(JNIEnv *env, jclass clazz, jlong display, jint screen, jobject peer_info_handle, jobject pixel_format) {
	Display *disp = (Display *)(intptr_t)display;
	initPeerInfo(env, peer_info_handle, disp, screen, pixel_format, true, GLX_WINDOW_BIT, true, false);
}
  
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nSetTitle(JNIEnv * env, jclass clazz, jlong display, jlong window_ptr, jstring title_obj) {
	Display *disp = (Display *)(intptr_t)display;
	Window window = (Window)window_ptr;
	char * title = GetStringNativeChars(env, title_obj);
	setWindowTitle(disp, window, title);
	free(title);
}

static void freeIconPixmap(Display *disp) {
	if (current_icon_pixmap != 0) {
		XFreePixmap(disp, current_icon_pixmap);
		current_icon_pixmap = 0;
	}
}

static void destroyWindow(JNIEnv *env, Display *disp, Window window) {
	if (glx_window != None) {
		lwjgl_glXDestroyWindow(disp, glx_window);
		glx_window = None;
	}
	XDestroyWindow(disp, window);
	XFreeColormap(disp, cmap);
	freeIconPixmap(disp);
}

static bool isNetWMFullscreenSupported(JNIEnv *env, Display *disp, int screen) {
	unsigned long nitems;
	Atom actual_type;
	int actual_format;
	unsigned long bytes_after;
	Atom *supported_list;
	Atom netwm_supported_atom = XInternAtom(disp, "_NET_SUPPORTED", False);
	int result = XGetWindowProperty(disp, RootWindow(disp, screen), netwm_supported_atom, 0, 10000, False, AnyPropertyType, &actual_type, &actual_format, &nitems, &bytes_after, (void *)&supported_list);
	if (result != Success) {
		throwException(env, "Unable to query _NET_SUPPORTED window property");
		return false;
	}
	Atom fullscreen_atom = XInternAtom(disp, "_NET_WM_STATE_FULLSCREEN", False);
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

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nIsNetWMFullscreenSupported(JNIEnv *env, jclass unused, jlong display, jint screen) {
	Display *disp = (Display *)(intptr_t)display;
	return isNetWMFullscreenSupported(env, disp, screen) ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nReshape(JNIEnv *env, jclass clazz, jlong display, jlong window_ptr, jint x, jint y, jint width, jint height) {
	Display *disp = (Display *)(intptr_t)display;
	Window window = (Window)window_ptr;
	XMoveWindow(disp, window, x, y);
}

static Window createWindow(JNIEnv* env, Display *disp, int screen, jint window_mode, X11PeerInfo *peer_info, int x, int y, int width, int height) {
	bool undecorated = getBooleanProperty(env, "org.lwjgl.opengl.Window.undecorated");
	Window root_win;
	Window win;
	XSetWindowAttributes attribs;
	int attribmask;

	root_win = RootWindow(disp, screen);
	XVisualInfo *vis_info = getVisualInfoFromPeerInfo(env, peer_info);
	if (vis_info == NULL)
		return false;
	cmap = XCreateColormap(disp, root_win, vis_info->visual, AllocNone);
	attribs.colormap = cmap;
	attribs.event_mask = ExposureMask | /*FocusChangeMask | */VisibilityChangeMask | StructureNotifyMask | KeyPressMask | KeyReleaseMask | ButtonPressMask | ButtonReleaseMask | PointerMotionMask;
	attribmask = CWColormap | CWEventMask;
	if (isLegacyFullscreen(window_mode)) {
		attribmask |= CWOverrideRedirect;
		attribs.override_redirect = True;
	}
	win = XCreateWindow(disp, root_win, x, y, width, height, 0, vis_info->depth, InputOutput, vis_info->visual, attribmask, &attribs);
	
	current_depth = vis_info->depth;
	current_visual = vis_info->visual;
	
	XFree(vis_info);
	if (!checkXError(env, disp)) {
		XFreeColormap(disp, cmap);
		return false;
	}
	printfDebugJava(env, "Created window");
	if (window_mode != org_lwjgl_opengl_LinuxDisplay_WINDOWED || undecorated) {
		// Use Motif decoration hint property and hope the window manager respects them
		setDecorations(disp, win, 0);
	}
	XSizeHints * size_hints = XAllocSizeHints();
	size_hints->flags = PMinSize | PMaxSize;
	size_hints->min_width = width;
	size_hints->max_width = width;
	size_hints->min_height = height;
	size_hints->max_height = height;
	XSetWMNormalHints(disp, win, size_hints);
	XFree(size_hints);
	Atom delete_atom = XInternAtom(disp, "WM_DELETE_WINDOW", False);
	XSetWMProtocols(disp, win, &delete_atom, 1);
	if (window_mode == org_lwjgl_opengl_LinuxDisplay_FULLSCREEN_NETWM) {
		Atom fullscreen_atom = XInternAtom(disp, "_NET_WM_STATE_FULLSCREEN", False);
		XChangeProperty(disp, win, XInternAtom(disp, "_NET_WM_STATE", False),
						XInternAtom(disp, "ATOM", False), 32, PropModeReplace, (const unsigned char*)&fullscreen_atom, 1);
	}
	XMapRaised(disp, win);
	waitMapped(disp, win);
	XSetInputFocus(disp, win, RevertToNone, CurrentTime);
	if (!checkXError(env, disp)) {
		destroyWindow(env, disp, win);
		return 0;
	}
	return win;
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nCreateWindow(JNIEnv *env, jclass clazz, jlong display, jint screen, jobject peer_info_handle, jobject mode, jint window_mode, jint x, jint y) {
	Display *disp = (Display *)(intptr_t)display;
	X11PeerInfo *peer_info = (*env)->GetDirectBufferAddress(env, peer_info_handle);
	GLXFBConfig *fb_config = NULL;
	if (peer_info->glx13) {
		fb_config = getFBConfigFromPeerInfo(env, peer_info);
		if (fb_config == NULL)
			return 0;
	}
	jclass cls_displayMode = (*env)->GetObjectClass(env, mode);
	jfieldID fid_width = (*env)->GetFieldID(env, cls_displayMode, "width", "I");
	jfieldID fid_height = (*env)->GetFieldID(env, cls_displayMode, "height", "I");
	int width = (*env)->GetIntField(env, mode, fid_width);
	int height = (*env)->GetIntField(env, mode, fid_height);
	Window win = createWindow(env, disp, screen, window_mode, peer_info, x, y, width, height);
	if ((*env)->ExceptionOccurred(env)) {
		return 0;
	}
	if (peer_info->glx13) {
		glx_window = lwjgl_glXCreateWindow(disp, *fb_config, win, NULL);
		XFree(fb_config);
	}
	if (!checkXError(env, disp)) {
		lwjgl_glXDestroyWindow(disp, glx_window);
		destroyWindow(env, disp, win);
	}
	return win;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nDestroyWindow(JNIEnv *env, jclass clazz, jlong display, jlong window_ptr) {
	Display *disp = (Display *)(intptr_t)display;
	Window window = (Window)window_ptr;
	destroyWindow(env, disp, window);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nLockAWT(JNIEnv *env, jclass clazz) {
	JAWT jawt;
	jawt.version = JAWT_VERSION_1_4;
	if (JAWT_GetAWT(env, &jawt) != JNI_TRUE) {
		throwException(env, "GetAWT failed");
		return;
	}
	jawt.Lock(env);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nUnlockAWT(JNIEnv *env, jclass clazz) {
	JAWT jawt;
	jawt.version = JAWT_VERSION_1_4;
	if (JAWT_GetAWT(env, &jawt) != JNI_TRUE) {
		throwException(env, "GetAWT failed");
		return;
	}
	jawt.Unlock(env);
}

static void setIcon(JNIEnv *env, Display *disp, Window window, char *data, int icon_size, int width,int height) {
	XWMHints* win_hints;
	freeIconPixmap(disp);
	current_icon_pixmap = XCreatePixmap(disp, window, width, height, current_depth);
	/* We need to copy the image data since XDestroyImage will also free its data buffer, which can't be allowed
	 * since the data buffer is managed by the jvm (it's the storage for the direct ByteBuffer)
	 */
	char *icon_copy = (char *)malloc(sizeof(*icon_copy)*icon_size);

	if (icon_copy == NULL) {
		throwException(env, "malloc failed");
		return;
	}
	memcpy(icon_copy, data, icon_size);
	XImage *image = XCreateImage(disp, current_visual, current_depth, ZPixmap, 0, icon_copy, width, height, 32, 0);
	if (image == NULL) {
		freeIconPixmap(disp);
		free(icon_copy);
		throwException(env, "XCreateImage failed");
		return;
	}
	
	GC gc = XCreateGC(disp, current_icon_pixmap, 0, NULL);
	XPutImage(disp, current_icon_pixmap, gc, image, 0, 0, 0, 0, width, height);
	XFreeGC(disp, gc);
	XDestroyImage(image);
	// We won't free icon_copy because it is freed by XDestroyImage
	
	win_hints = XAllocWMHints();
	if (win_hints == NULL) {
		throwException(env, "XAllocWMHints failed");
		return;
	}
	
	win_hints->flags = IconPixmapHint;               
	win_hints->icon_pixmap = current_icon_pixmap;
	
	XSetWMHints(disp, window, win_hints);
	XFree(win_hints);
	XFlush(disp);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nSetWindowIcon
  (JNIEnv *env, jclass clazz, jlong display, jlong window_ptr, jobject iconBuffer, jint icon_size, jint width, jint height)
{
	Display *disp = (Display *)(intptr_t)display;
	Window window = (Window)window_ptr;
	char *imgData = (char *)(*env)->GetDirectBufferAddress(env, iconBuffer);

	setIcon(env, disp, window, imgData, icon_size, width, height);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nUngrabKeyboard(JNIEnv *env, jclass unused, jlong display_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	return XUngrabKeyboard(disp, CurrentTime);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nGrabKeyboard(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = (Window)window_ptr;
	return XGrabKeyboard(disp, win, False, GrabModeAsync, GrabModeAsync, CurrentTime);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nGrabPointer(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr, jlong cursor_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = (Window)window_ptr;
	Cursor cursor = (Cursor)cursor_ptr;
	int grab_mask = PointerMotionMask | ButtonPressMask | ButtonReleaseMask;
	return XGrabPointer(disp, win, False, grab_mask, GrabModeAsync, GrabModeAsync, win, cursor, CurrentTime);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nSetViewPort(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr, jint screen) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = (Window)window_ptr;
	XWindowAttributes win_attribs;

	XGetWindowAttributes(disp, win, &win_attribs);
	XF86VidModeSetViewPort(disp, screen, win_attribs.x, win_attribs.y);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nUngrabPointer(JNIEnv *env, jclass unused, jlong display_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	return XUngrabPointer(disp, CurrentTime);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nDefineCursor(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr, jlong cursor_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = (Window)window_ptr;
	Cursor cursor = (Cursor)cursor_ptr;
	XDefineCursor(disp, win, cursor);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nCreateBlankCursor(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = (Window)window_ptr;
	unsigned int best_width, best_height;
	if (XQueryBestCursor(disp, win, 1, 1, &best_width, &best_height) == 0) {
		throwException(env, "Could not query best cursor size");
		return false;
	}
	Pixmap mask = XCreatePixmap(disp, win, best_width, best_height, 1);
	XGCValues gc_values;
	gc_values.foreground = 0;
	GC gc = XCreateGC(disp, mask, GCForeground, &gc_values);
	XFillRectangle(disp, mask, gc, 0, 0, best_width, best_height);
	XFreeGC(disp, gc);
	XColor dummy_color;
	Cursor cursor = XCreatePixmapCursor(disp, mask, mask, &dummy_color, &dummy_color, 0, 0);
	XFreePixmap(disp, mask);
	return cursor;
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nGetInputFocus(JNIEnv *env, jclass unused, jlong display_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	int revert_mode;
	Window win;
	XGetInputFocus(disp, &win, &revert_mode);
	return win;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nSetRepeatMode(JNIEnv *env, jclass unused, jlong display_ptr, jint mode) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	XKeyboardControl repeat_mode;
	repeat_mode.auto_repeat_mode = mode;
	XChangeKeyboardControl(disp, KBAutoRepeatMode, &repeat_mode);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nIconifyWindow(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr, jint screen) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = (Window)window_ptr;
	XIconifyWindow(disp, win, screen);
}
