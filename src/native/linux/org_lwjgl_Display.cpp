/*
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of
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
 * Linux specific library for display handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */


#include "extgl.h"
#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/extensions/xf86vmode.h>
#include <X11/Xutil.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <jni.h>
#include "org_lwjgl_Display.h"

Display * disp;
int screen;
Window win;

static jfieldID fid_close;
static bool current_fullscreen;
static bool current_minimized;
static bool input_released;
static int win_width;
static int win_height;
static XF86VidModeModeInfo **avail_modes;
static XVisualInfo * vis_info;
static Atom delete_atom;
static jclass saved_clazz;

extern void handlePointerMotion(XMotionEvent *);
extern void handleButtonPress(XButtonEvent *);
extern void handleButtonRelease(XButtonEvent *);
extern void handleKeyEvent(XKeyEvent *);
extern void releaseKeyboard(void);
extern void releasePointer(void);
extern void acquireKeyboard(void);
extern void acquirePointer(void);

struct pixelformat {
	int bpp;
	int depth;
	int alpha;
	int stencil;
};

static XVisualInfo *chooseVisual(Display *disp, int screen, int bpp, int depth, int alpha, int stencil) {
	int bpe;
	switch (bpp) {
		case 32:
		case 24:
			bpe = 8;
			break;
		case 16:
			bpe = 4;
			break;
		default:
			return JNI_FALSE;
	}
	
	int attriblist[] = { GLX_RGBA,
                             GLX_DOUBLEBUFFER,
                             GLX_DEPTH_SIZE, depth,
                             GLX_RED_SIZE, bpe,
                             GLX_GREEN_SIZE, bpe,
                             GLX_BLUE_SIZE, bpe,
                             GLX_ALPHA_SIZE, alpha,
			     GLX_STENCIL_SIZE, stencil,
                             None };
	return glXChooseVisual(disp, screen, attriblist);
}

static void dumpVisualInfo(Display *disp, XVisualInfo *vis_info) {
	int alpha, depth, stencil, r, g, b;
	glXGetConfig(disp, vis_info, GLX_RED_SIZE, &r);
	glXGetConfig(disp, vis_info, GLX_GREEN_SIZE, &g);
	glXGetConfig(disp, vis_info, GLX_BLUE_SIZE, &b);
	glXGetConfig(disp, vis_info, GLX_ALPHA_SIZE, &alpha);
	glXGetConfig(disp, vis_info, GLX_DEPTH_SIZE, &depth);
	glXGetConfig(disp, vis_info, GLX_STENCIL_SIZE, &stencil);
	printf("Pixel format chosen sizes: r = %d, g = %d, b = %d, a = %d, depth = %d, stencil = %d\n", r, g, b, alpha, depth, stencil);
}

static void waitMapped(Display *disp, Window win) {
	XEvent event;

	do {
		XMaskEvent(disp, StructureNotifyMask, &event);
	} while ((event.type != MapNotify) || (event.xmap.event != win));
}

static void setRepeatMode(int mode) {
	XKeyboardControl repeat_mode;
	repeat_mode.auto_repeat_mode = mode;
	XChangeKeyboardControl(disp, KBAutoRepeatMode, &repeat_mode);
}

bool releaseInput(void) {
	if (current_fullscreen)
		return false;
	releaseKeyboard();
	releasePointer();
	input_released = true;
	setRepeatMode(AutoRepeatModeDefault);
	return true;
}

static void acquireInput(void) {
	if (input_released) {
		setRepeatMode(AutoRepeatModeOff);
		acquireKeyboard();
		acquirePointer();
		input_released = false;
	}
}

void handleMessages(JNIEnv *env) {
	XEvent event;
	while (XPending(disp) > 0) {
		XNextEvent(disp, &event);
		switch (event.type) {
			case ClientMessage:
				if ((event.xclient.format == 32) && ((Atom)event.xclient.data.l[0] == delete_atom))
					env->SetStaticBooleanField(saved_clazz, fid_close, JNI_TRUE);
				break;
			case FocusIn:
				acquireInput();
				break;
			case MapNotify:
				current_minimized = false;
				break;
			case UnmapNotify:
				current_minimized = true;
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
}

static bool loadGL(Display *disp, int screen) {
	if (extgl_Open(disp, screen) != 0) {
#ifdef _DEBUG
		printf("Could not load gl libs\n");
#endif
		return false;
	}
	return true;
}

static void closeGL(void) {
	extgl_Close();
}

static int getDisplayModes(Display *disp, int screen, int *num_modes, XF86VidModeModeInfo ***avail_modes) {
	int event_base, error_base, xvid_ver, xvid_rev;
	
	if (!XF86VidModeQueryExtension(disp, &event_base, &error_base)) {
#ifdef _DEBUG
		printf("XF86VidMode extension not available\n");
#endif
		return 0;
	}
	XF86VidModeQueryVersion(disp, &xvid_ver, &xvid_rev);
#ifdef _DEBUG
	printf("XF86VidMode extension version %i.%i\n", xvid_ver, xvid_rev);
#endif
	XF86VidModeGetAllModeLines(disp, screen, num_modes, avail_modes);
	return 1;
}

static bool isMinimized(JNIEnv *env, jclass clazz) {
	handleMessages(env);
	return current_minimized;
}

int getWindowHeight(void) {
	return win_height;
}

int getWindowWidth(void) {
	return win_width;
}

XVisualInfo *getVisualInfo(void) {
	return vis_info;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    isMinimized
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_Display_isMinimized(JNIEnv *env, jclass clazz) {
	return isMinimized(env, clazz) ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_Display_nCreate(JNIEnv * env, jclass clazz, jint width, jint height, jint bpp, jint freq, jint alpha_bits, jint depth_bits, jint stencil_bits, jboolean fullscreen, jstring title) {
        // Get a global class instance, just to be sure
	static jobject globalClassLock = NULL;
	
	if (globalClassLock == NULL) {
		globalClassLock = env->NewGlobalRef(clazz);
	}

	saved_clazz = clazz;
	
	fid_close = env->GetStaticFieldID(clazz, "closeRequested", "Z");
	
	Window root_win;
	XSetWindowAttributes attribs;
	Colormap cmap;
	int attribmask;
        int num_modes, i;

	win_width = width;
	win_height = height;
	if (fullscreen == JNI_TRUE)
		current_fullscreen = true;
	else
		current_fullscreen = false;
	current_minimized = false;
	input_released = false;
	disp = XOpenDisplay(NULL);
	if (disp == NULL) {
#ifdef _DEBUG
		printf("Could not open X connection\n");
#endif
		return JNI_FALSE;
	}
	screen = DefaultScreen(disp);
	if (!loadGL(disp, screen)) {
		XCloseDisplay(disp);
#ifdef _DEBUG
		printf("Could not load GL libs\n");
#endif
		return JNI_FALSE;
	}
	if (!getDisplayModes(disp, screen, &num_modes, &avail_modes)) {
		XCloseDisplay(disp);
#ifdef _DEBUG
		printf("Could not get display modes\n");
#endif
		return JNI_FALSE;
	}
	root_win = RootWindow(disp, screen);
	vis_info = chooseVisual(disp, screen, bpp, depth_bits, alpha_bits, stencil_bits);
        if (vis_info == NULL) {
		XCloseDisplay(disp);
#ifdef _DEBUG
		printf("Could not choose glx visual\n");
#endif
		return JNI_FALSE;
	}
#ifdef _DEBUG
	dumpVisualInfo(disp, vis_info);
#endif
	cmap = XCreateColormap(disp, root_win, vis_info->visual, AllocNone);
	attribs.colormap = cmap;
	attribs.event_mask = FocusChangeMask | VisibilityChangeMask| StructureNotifyMask | KeyPressMask | KeyReleaseMask | ButtonPressMask | ButtonReleaseMask | PointerMotionMask;
	attribs.background_pixel = 0xFF000000;
	attribmask = CWColormap | CWBackPixel | CWEventMask;
	if (fullscreen) {
		attribmask |= CWOverrideRedirect;
		attribs.override_redirect = True;
	}
	win = XCreateWindow(disp, root_win, 0, 0, width, height, 0, vis_info->depth, InputOutput, vis_info->visual, attribmask, &attribs);
	XFreeColormap(disp, cmap);
#ifdef _DEBUG
	printf("Created window\n");
#endif
	const char * title_str = env->GetStringUTFChars(title, NULL);
	XStoreName(disp, win, title_str);
	env->ReleaseStringUTFChars(title, title_str);
	XSizeHints * size_hints = XAllocSizeHints();
	size_hints->flags = PMinSize | PMaxSize;
	size_hints->min_width = width;
	size_hints->max_width = width;
	size_hints->min_height = height;
	size_hints->max_height = height;
	XSetWMNormalHints(disp, win, size_hints);
	XFree(size_hints);
	delete_atom = XInternAtom(disp, "WM_DELETE_WINDOW", False);
	XSetWMProtocols(disp, win, &delete_atom, 1);
	XMapRaised(disp, win);
	waitMapped(disp, win);
	if (fullscreen) {
		for ( i = 0; i < num_modes; ++i ) {
#ifdef _DEBUG
			printf("Mode %d: %dx%d\n", i, avail_modes[i]->hdisplay, avail_modes[i]->vdisplay);
#endif
			if (avail_modes[i]->hdisplay == width && avail_modes[i]->vdisplay == height) {
				if (!XF86VidModeSwitchToMode(disp, screen, avail_modes[i])) {
					XFree(vis_info);
					XFree(avail_modes);
					XDestroyWindow(disp, win);
					XCloseDisplay(disp);
#ifdef _DEBUG
					printf("Could not switch mode\n");
#endif
					return JNI_FALSE;
				}
				XF86VidModeLockModeSwitch(disp, screen, 1);
				break;
			}
		}
		XF86VidModeSetViewPort(disp, screen, 0, 0);
	}
	XClearWindow(disp, win);
	setRepeatMode(AutoRepeatModeOff);
	XSync(disp, True);
	return JNI_TRUE;
}

JNIEXPORT void JNICALL Java_org_lwjgl_Display_nDestroy(JNIEnv * env, jclass clazz) {
	setRepeatMode(AutoRepeatModeDefault);
	XDestroyWindow(disp, win);
	if (current_fullscreen) {
		XF86VidModeLockModeSwitch(disp, screen, 0);
		if (!XF86VidModeSwitchToMode(disp, screen, avail_modes[0])) {
#ifdef _DEBUG
			printf("Could not switch mode\n");
#endif
		}
	}
	XFree(avail_modes);
	avail_modes = NULL;
	XFree(vis_info);
	vis_info = NULL;
	XCloseDisplay(disp);
	disp = NULL;
	closeGL();
#ifdef _DEBUG
	printf("Closed X connection\n");
#endif
}

/*
 * Class:     org_lwjgl_Display
 * Method:    nGetAvailableDisplayModes
 * Signature: ()[Lorg/lwjgl/DisplayMode;
 */
JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_Display_nGetAvailableDisplayModes
  (JNIEnv * env, jclass clazz)
{
	int num_modes, i;

	Display *disp = XOpenDisplay(NULL);
	int screen;
	XF86VidModeModeInfo **avail_modes;

	if (disp == NULL) {
#ifdef _DEBUG
		printf("Could not open X connection\n");
#endif
		XCloseDisplay(disp);
		return NULL;
	}
	
	screen = DefaultScreen(disp);
	int bpp = XDefaultDepth(disp, screen);
	
	if (!getDisplayModes(disp, screen, &num_modes, &avail_modes)) {
#ifdef _DEBUG
		printf("Could not get display modes\n");
#endif
		XCloseDisplay(disp);
		return NULL;
	}
	// Allocate an array of DisplayModes big enough
	jclass displayModeClass = env->FindClass("org/lwjgl/DisplayMode");
	jobjectArray ret = env->NewObjectArray(num_modes, displayModeClass, NULL);
	jmethodID displayModeConstructor = env->GetMethodID(displayModeClass, "<init>", "(IIII)V");
	
	for (i = 0; i < num_modes; i++) {
		jobject displayMode = env->NewObject(displayModeClass, displayModeConstructor, avail_modes[i]->hdisplay, avail_modes[i]->vdisplay, bpp, 0);
		env->SetObjectArrayElement(ret, i, displayMode);
	}
	XFree(avail_modes);
	XCloseDisplay(disp);
	return ret;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    getPlatform
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_Display_getPlatform
  (JNIEnv * env, jclass clazz)
{
	return org_lwjgl_Display_PLATFORM_GLX;
}
