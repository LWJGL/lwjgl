/*
 * Copyright (c) 2002-2011 LWJGL Project
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
 * $Id: org_lwjgl_opengl_Display.c 3555 2011-07-02 20:50:27Z kappa1 $
 *
 * Linux specific display functions.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision: 3555 $
 */

#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <X11/extensions/xf86vmode.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <jni.h>
#include <jawt_md.h>
#include "common_tools.h"
#include "extgl.h"
#include "context.h"
#include "org_lwjgl_opengl_LinuxDisplay.h"
//#include "org_lwjgl_opengl_LinuxDisplayPeerInfo.h"
#include "org_lwjgl_LinuxSysImplementation.h"

#define ERR_MSG_SIZE 1024

typedef struct {
	unsigned long flags;
	unsigned long functions;
	unsigned long decorations;
	long input_mode;
	unsigned long status;
} MotifWmHints;

#define MWM_HINTS_DECORATIONS   (1L << 1)

static Colormap cmap;
static int current_depth;

static Visual *current_visual;

static bool checkXError(JNIEnv *env, Display *disp) {
	XSync(disp, False);
	return (*env)->ExceptionCheck(env) == JNI_FALSE;
}

static int global_error_handler(Display *disp, XErrorEvent *error) {
	JNIEnv *env = getThreadEnv();
	if (env != NULL) {
		jclass org_lwjgl_LinuxDisplay_class = (*env)->FindClass(env, "org/lwjgl/opengl/LinuxDisplay");
		if (org_lwjgl_LinuxDisplay_class == NULL) {
			// Don't propagate error
			(*env)->ExceptionClear(env);
			return 0;
		}
		jmethodID handler_method = (*env)->GetStaticMethodID(env, org_lwjgl_LinuxDisplay_class, "globalErrorHandler", "(JJJJJJJ)I");
		if (handler_method == NULL)
			return 0;
		return (*env)->CallStaticIntMethod(env, org_lwjgl_LinuxDisplay_class, handler_method, (jlong)(intptr_t)disp, (jlong)(intptr_t)error,
				(jlong)(intptr_t)error->display, (jlong)error->serial, (jlong)error->error_code, (jlong)error->request_code, (jlong)error->minor_code);
	} else
		return 0;
}

static jlong openDisplay(JNIEnv *env) {
	Display *display_connection = XOpenDisplay(NULL);
	if (display_connection == NULL) {
		throwException(env, "Could not open X display connection");
		return (intptr_t)NULL;
	}
	return (intptr_t)display_connection;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_DefaultSysImplementation_getJNIVersion
  (JNIEnv *env, jobject ignored) {
	return org_lwjgl_LinuxSysImplementation_JNI_VERSION;
}

JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_LinuxDisplay_getErrorText(JNIEnv *env, jclass unused, jlong display_ptr, jlong error_code) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	char err_msg_buffer[ERR_MSG_SIZE];
	XGetErrorText(disp, error_code, err_msg_buffer, ERR_MSG_SIZE);
	err_msg_buffer[ERR_MSG_SIZE - 1] = '\0';
	return NewStringNativeWithLength(env, err_msg_buffer, strlen(err_msg_buffer));
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxDisplay_callErrorHandler(JNIEnv *env, jclass unused, jlong handler_ptr, jlong display_ptr, jlong event_ptr) {
	XErrorHandler handler = (XErrorHandler)(intptr_t)handler_ptr;
	Display *disp = (Display *)(intptr_t)display_ptr;
	XErrorEvent *event = (XErrorEvent *)(intptr_t)event_ptr;
	return (jint)handler(disp, event);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxDisplay_setErrorHandler(JNIEnv *env, jclass unused) {
	return (intptr_t)XSetErrorHandler(global_error_handler);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxDisplay_resetErrorHandler(JNIEnv *env, jclass unused, jlong handler_ptr) {
	XErrorHandler handler = (XErrorHandler)(intptr_t)handler_ptr;
	return (intptr_t)XSetErrorHandler(handler);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nSync(JNIEnv *env, jclass unused, jlong display_ptr, jboolean throw_away_events) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	XSync(disp, throw_away_events ? True : False);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_sync(JNIEnv *env, jclass unused, jlong display_ptr, jboolean throw_away_events) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	XSync(disp, throw_away_events ? True : False);
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

static void setWindowTitle(Display *disp, Window window, jlong title, jint len) {
	// ASCII fallback if XChangeProperty fails.
	XStoreName(disp, window, (const char *)(intptr_t)title);

	// Set the UTF-8 encoded title
	XChangeProperty(disp, window,
					XInternAtom(disp, "_NET_WM_NAME", False),
					XInternAtom(disp, "UTF8_STRING", False),
					8, PropModeReplace, (const unsigned char *)(intptr_t)title,
					len);
}

static void setClassHint(Display *disp, Window window, jlong wm_name, jlong wm_class) {
	XClassHint* hint = XAllocClassHint();
	
	hint->res_name = (const unsigned char *)(intptr_t)wm_name;
	hint->res_class = (const unsigned char *)(intptr_t)wm_class;
	
	XSetClassHint(disp, window, hint);
	
	XFree(hint);
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
	peer_info->drawable = window;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplayPeerInfo_initDefaultPeerInfo(JNIEnv *env, jclass clazz, jlong display, jint screen, jobject peer_info_handle) {
	//Display *disp = (Display *)(intptr_t)display;
	//initPeerInfo(env, peer_info_handle, disp, screen);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nSetTitle(JNIEnv * env, jclass clazz, jlong display, jlong window_ptr, jlong title, jint len) {
	Display *disp = (Display *)(intptr_t)display;
	Window window = (Window)window_ptr;
	setWindowTitle(disp, window, title, len);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nSetClassHint(JNIEnv * env, jclass clazz, jlong display, jlong window_ptr, jlong wm_name, jlong wm_class) {
	Display *disp = (Display *)(intptr_t)display;
	Window window = (Window)window_ptr;
	setClassHint(disp, window, wm_name, wm_class);
}

static void destroyWindow(JNIEnv *env, Display *disp, Window window) {
	XDestroyWindow(disp, window);
	XFreeColormap(disp, cmap);
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
	XResizeWindow(disp, window, width, height);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_synchronize(JNIEnv *env, jclass clazz, jlong display, jboolean synchronize) {
	Display *disp = (Display *)(intptr_t)display;
	XSynchronize(disp, synchronize ? True : False);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxDisplay_getRootWindow(JNIEnv *env, jclass clazz, jlong display, jint screen) {
	Display *disp = (Display *)(intptr_t)display;
	return RootWindow(disp, screen);
}

static Window getCurrentWindow(JNIEnv *env, jlong display_ptr, jlong window_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;

	Window parent = (Window)window_ptr;
	Window win, root;

	Window *children;
	unsigned int nchildren;

	do {
		win = parent;

		if (XQueryTree(disp, win, &root, &parent, &children, &nchildren) == 0) {
			throwException(env, "XQueryTree failed");
			return 0;
		}

		if (children != NULL) XFree(children);
	} while (parent != root);

	return win;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nGetX(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = getCurrentWindow(env, display_ptr, window_ptr);

	XWindowAttributes win_attribs;
	XGetWindowAttributes(disp, win, &win_attribs);

	return win_attribs.x;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nGetY(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = getCurrentWindow(env, display_ptr, window_ptr);

	XWindowAttributes win_attribs;
	XGetWindowAttributes(disp, win, &win_attribs);

	return win_attribs.y;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nGetWidth(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = (Window)window_ptr;
	XWindowAttributes win_attribs;

	XGetWindowAttributes(disp, win, &win_attribs);

	return win_attribs.width;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nGetHeight(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = (Window)window_ptr;
	XWindowAttributes win_attribs;

	XGetWindowAttributes(disp, win, &win_attribs);

	return win_attribs.height;
}

static void updateWindowHints(JNIEnv *env, Display *disp, Window window) {
	XWMHints* win_hints = XAllocWMHints();
	if (win_hints == NULL) {
		throwException(env, "XAllocWMHints failed");
		return;
	}

	win_hints->flags = InputHint;
	win_hints->input = True;

	XSetWMHints(disp, window, win_hints);
	XFree(win_hints);
	XFlush(disp);
}

static void updateWindowBounds(Display *disp, Window win, int x, int y, int width, int height, jboolean position, jboolean resizable) {
	XSizeHints *window_hints = XAllocSizeHints();

	if (position) {
		window_hints->flags |= PPosition;
		window_hints->x = x;
		window_hints->y = y;
	}

	if (!resizable) {
		window_hints->flags |= PMinSize | PMaxSize;
		window_hints->min_width = width;
		window_hints->max_width = width;
		window_hints->min_height = height;
		window_hints->max_height = height;
	}

	XSetWMNormalHints(disp, win, window_hints);
	XFree(window_hints);
}

static Window createWindow(JNIEnv* env, Display *disp, int screen, jint window_mode, X11PeerInfo *peer_info, int x, int y, int width, int height, jboolean undecorated, long parent_handle, jboolean resizable) {
	Window parent = (Window)parent_handle;
	Window win;
	XSetWindowAttributes attribs;
	unsigned int attribmask;

    XVisualInfo vis_info;
    if ( !XMatchVisualInfo(disp, screen, DefaultDepth(disp, screen), TrueColor, &vis_info) ) {
        throwException(env, "Failed to acquire X visual.");
        return false;
    }

	cmap = XCreateColormap(disp, parent, vis_info.visual, AllocNone);
	if (!checkXError(env, disp)) {
		return false;
	}
	attribs.colormap = cmap;
	attribs.border_pixel = 0;
	attribs.event_mask = ExposureMask | FocusChangeMask | VisibilityChangeMask | StructureNotifyMask | KeyPressMask | KeyReleaseMask | ButtonPressMask | ButtonReleaseMask | PointerMotionMask;
	attribmask = CWColormap | CWEventMask | CWBorderPixel | CWBackPixel;
	if (isLegacyFullscreen(window_mode)) {
		attribmask |= CWOverrideRedirect;
		attribs.override_redirect = True;
	}
	win = XCreateWindow(disp, parent, x, y, width, height, 0, vis_info.depth, InputOutput, vis_info.visual, attribmask, &attribs);

	current_depth = vis_info.depth;
	current_visual = vis_info.visual;

	if (!checkXError(env, disp)) {
		XFreeColormap(disp, cmap);
		return false;
	}
	if (undecorated) {
		// Use Motif decoration hint property and hope the window manager respects them
		setDecorations(disp, win, 0);
	}

	if (RootWindow(disp, screen) == parent_handle) { // only set hints when Display.setParent isn't used
		updateWindowBounds(disp, win, x, y, width, height, JNI_TRUE, resizable);
		updateWindowHints(env, disp, win);
	}

#define NUM_ATOMS 1
	Atom protocol_atoms[NUM_ATOMS] = {XInternAtom(disp, "WM_DELETE_WINDOW", False)/*, XInternAtom(disp, "WM_TAKE_FOCUS", False)*/};
	XSetWMProtocols(disp, win, protocol_atoms, NUM_ATOMS);
	if (window_mode == org_lwjgl_opengl_LinuxDisplay_FULLSCREEN_NETWM) {
		Atom fullscreen_atom = XInternAtom(disp, "_NET_WM_STATE_FULLSCREEN", False);
		XChangeProperty(disp, win, XInternAtom(disp, "_NET_WM_STATE", False),
						XInternAtom(disp, "ATOM", False), 32, PropModeReplace, (const unsigned char*)&fullscreen_atom, 1);
	}
	if (!checkXError(env, disp)) {
		destroyWindow(env, disp, win);
		return 0;
	}
	return win;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_reparentWindow(JNIEnv *env, jclass unused, jlong display, jlong window_ptr, jlong parent_ptr, jint x, jint y) {
	Display *disp = (Display *)(intptr_t)display;
	Window window = (Window)window_ptr;
	Window parent = (Window)parent_ptr;
	XReparentWindow(disp, window, parent, x, y);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_mapRaised(JNIEnv *env, jclass unused, jlong display, jlong window_ptr) {
	Display *disp = (Display *)(intptr_t)display;
	Window window = (Window)window_ptr;
	XMapRaised(disp, window);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxDisplay_getParentWindow(JNIEnv *env, jclass unused, jlong display, jlong window_ptr) {
	Display *disp = (Display *)(intptr_t)display;
	Window window = (Window)window_ptr;
	Window root, parent;
	Window *children;
	unsigned int nchildren;
	if (XQueryTree(disp, window, &root, &parent, &children, &nchildren) == 0) {
		throwException(env, "XQueryTree failed");
		return None;
	}
	if (children != NULL)
		XFree(children);
	return parent;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxDisplay_getChildCount(JNIEnv *env, jclass unused, jlong display, jlong window_ptr) {
	Display *disp = (Display *)(intptr_t)display;
	Window window = (Window)window_ptr;
	Window root, parent;
	Window *children;
	unsigned int nchildren;
	if (XQueryTree(disp, window, &root, &parent, &children, &nchildren) == 0) {
		throwException(env, "XQueryTree failed");
		return None;
	}
	if (children != NULL)
		XFree(children);

	return nchildren;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_LinuxDisplay_hasProperty(JNIEnv *env, jclass unusued, jlong display, jlong window_ptr, jlong property_ptr) {
	Display *disp = (Display *)(intptr_t)display;
	Window window = (Window)window_ptr;
	Atom property = (Atom)property_ptr;
	int num_props;
	Atom *properties = XListProperties(disp, window, &num_props);
	if (properties == NULL)
		return JNI_FALSE;
	jboolean result = JNI_FALSE;
	for (int i = 0; i < num_props; i++) {
		if (properties[i] == property) {
			result = JNI_TRUE;
			break;
		}
	}
	XFree(properties);
	return result;
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nGetInputFocus(JNIEnv *env, jclass unused, jlong display_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	int revert_mode;
	Window win;
	XGetInputFocus(disp, &win, &revert_mode);
	return win;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nSetInputFocus(JNIEnv *env, jclass clazz, jlong display, jlong window_ptr, jlong time) {
	Display *disp = (Display *)(intptr_t)display;
	Window window = (Window)window_ptr;
	XSetInputFocus(disp, window, RevertToParent, time);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nCreateWindow(JNIEnv *env, jclass clazz, jlong display, jint screen, jobject peer_info_handle, jobject mode, jint window_mode, jint x, jint y, jboolean undecorated, jlong parent_handle, jboolean resizable) {
	Display *disp = (Display *)(intptr_t)display;
	X11PeerInfo *peer_info = (*env)->GetDirectBufferAddress(env, peer_info_handle);

	jclass cls_displayMode = (*env)->GetObjectClass(env, mode);

	jfieldID fid_width = (*env)->GetFieldID(env, cls_displayMode, "width", "I");
	jfieldID fid_height = (*env)->GetFieldID(env, cls_displayMode, "height", "I");

	int width = (*env)->GetIntField(env, mode, fid_width);
	int height = (*env)->GetIntField(env, mode, fid_height);

	Window win = createWindow(env, disp, screen, window_mode, peer_info, x, y, width, height, undecorated, parent_handle, resizable);

	if ((*env)->ExceptionOccurred(env))
		return 0;

	if (!checkXError(env, disp))
		destroyWindow(env, disp, win);

	return win;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nSetWindowSize(JNIEnv *env, jclass clazz, jlong display, jlong window_ptr, jint width, jint height, jboolean resizable) {
	Display *disp = (Display *)(intptr_t)display;
	Window win = (Window)window_ptr;
	updateWindowBounds(disp, win, 0, 0, width, height, JNI_FALSE, resizable);
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

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nSetWindowIcon
  (JNIEnv *env, jclass clazz, jlong display, jlong window_ptr, jobject icons_buffer, jint icons_buffer_size)
{
	Display *disp = (Display *)(intptr_t)display;
	Window window = (Window)window_ptr;
	const unsigned char *icons_char_buffer = (const unsigned char *)(*env)->GetDirectBufferAddress(env, icons_buffer);
	
	int length = icons_buffer_size/4;
	unsigned long icons_long_buffer[length];
	int i = 0;

	// copy byte array to long array
	for (i = 0; i < icons_buffer_size; i += 4) {
		unsigned long argb = (icons_char_buffer[i] << 24) | 
							(icons_char_buffer[i+1] << 16) | 
							(icons_char_buffer[i+2] << 8) | 
							(icons_char_buffer[i+3]);
		icons_long_buffer[i/4] = argb;
	}

	XChangeProperty(disp, window,
					XInternAtom(disp, "_NET_WM_ICON", False),
					XInternAtom(disp, "CARDINAL", False),
					32, PropModeReplace, (const unsigned char*) icons_long_buffer, length);
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

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nIconifyWindow(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr, jint screen) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = (Window)window_ptr;
	XIconifyWindow(disp, win, screen);
}
