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
#include <jni.h>
#include "org_lwjgl_Display.h"


Display * disp;
int screen;
int current_fullscreen;
int current_focused;
Window win;
int win_width;
int win_height;
XF86VidModeModeInfo **avail_modes;
XVisualInfo * vis_info;

void waitMapped(Display *disp, Window win) {
	XEvent event;

	do {
		XMaskEvent(disp, StructureNotifyMask, &event);
	} while ((event.type != MapNotify) || (event.xmap.event != win));
}

int isFocused(void) {
	XEvent event;
	while (XCheckMaskEvent(disp, EnterWindowMask | LeaveWindowMask, &event)) {
		if (event.type == EnterNotify)
			current_focused = 1;
		else if (event.type == LeaveNotify)
			current_focused = 0;
	}
	return current_focused;
}

int getDisplayModes(Display *disp, int screen, int *num_modes, XF86VidModeModeInfo ***avail_modes) {
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

JNIEXPORT jboolean JNICALL Java_org_lwjgl_Display_nCreate(JNIEnv * env, jclass clazz, jint width, jint height, jint bpp, jint freq, jint alpha_bits, jint depth_bits, jint stencil_bits, jboolean fullscreen) {
	Window root_win;
	XSetWindowAttributes attribs;
	Colormap cmap;
	int attribmask;
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
	if (depth_bits == 32)
		depth_bits = 24;
	
	int attriblist[] = { GLX_RGBA,
                             GLX_DOUBLEBUFFER,
                             GLX_DEPTH_SIZE, depth_bits,
                             GLX_RED_SIZE, bpe,
                             GLX_GREEN_SIZE, bpe,
                             GLX_BLUE_SIZE, bpe,
                             GLX_ALPHA_SIZE, alpha_bits,
			     GLX_STENCIL_SIZE, stencil_bits,
                             None };
/*	int attriblistna[] = { GLX_RGBA,
                             GLX_DOUBLEBUFFER,
                             GLX_DEPTH_SIZE, bpp,
                             GLX_RED_SIZE, bpe,
                             GLX_GREEN_SIZE, bpe,
                             GLX_BLUE_SIZE, bpe,
                             None };
*/
        int num_modes, i;
                            


	win_width = width;
	win_height = height;
	current_fullscreen = fullscreen;
	current_focused = 0;
	disp = XOpenDisplay(NULL);
	if (disp == NULL) {
#ifdef _DEBUG
		printf("Could not open X connection\n");
#endif
		return JNI_FALSE;
	}
	screen = DefaultScreen(disp);
	if (!getDisplayModes(disp, screen, &num_modes, &avail_modes)) {
		XCloseDisplay(disp);
#ifdef _DEBUG
		printf("Could not get display modes\n");
#endif
		return JNI_FALSE;
	}
	root_win = RootWindow(disp, screen);
	if (extgl_Open() != 0) {
#ifdef _DEBUG
		printf("Could not load gl libs\n");
#endif
		return JNI_FALSE;
	}
	vis_info = glXChooseVisual(disp, screen, attriblist);

        /* might be a better way to handle not being able to set GLX_ALPHA_SIZE... */
/*        if (vis_info == NULL) {
            vis_info = glXChooseVisual(disp, screen, attriblistna);
        }
*/	
        if (vis_info == NULL) {
		XCloseDisplay(disp);
#ifdef _DEBUG
		printf("Could not choose glx visual\n");
#endif
		return JNI_FALSE;
	}

	cmap = XCreateColormap(disp, root_win, vis_info->visual, AllocNone);
	attribs.colormap = cmap;
	attribs.event_mask = StructureNotifyMask | EnterWindowMask | LeaveWindowMask | KeyPressMask | KeyReleaseMask | ButtonPressMask | ButtonReleaseMask | PointerMotionMask;
	attribs.background_pixel = 0xFF000000;
	attribmask = CWColormap | CWBackPixel | CWEventMask;
	if (fullscreen) {
		attribmask |= CWOverrideRedirect;
		attribs.override_redirect = True;
	}
	win = XCreateWindow(disp, root_win, 0, 0, width, height, 0, vis_info->depth, InputOutput, vis_info->visual, attribmask, &attribs);
#ifdef _DEBUG
	printf("Created window\n");
#endif
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
			}
		}
		XF86VidModeSetViewPort(disp, screen, 0, 0);
	}
	XClearWindow(disp, win);
	XSync(disp, True);
        isFocused();
	return JNI_TRUE;
}

JNIEXPORT void JNICALL Java_org_lwjgl_Display_nDestroy(JNIEnv * env, jclass clazz) {
	XDestroyWindow(disp, win);
	if (current_fullscreen) {
		if (!XF86VidModeSwitchToMode(disp, screen, avail_modes[0])) {
#ifdef _DEBUG
			printf("Could not switch mode\n");
#endif
		}
	}
	XFree(avail_modes);
	XFree(vis_info);
	XCloseDisplay(disp);
	extgl_Close();
#ifdef _DEBUG
	printf("Closed X connection\n");
#endif
}

/*
 * Class:     org_lwjgl_Display
 * Method:    getAvailableDisplayModes
 * Signature: ()[Lorg/lwjgl/DisplayMode;
 */
JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_Display_getAvailableDisplayModes
  (JNIEnv * env, jclass clazz)
{
	int num_modes, i;

	Display *disp = XOpenDisplay(NULL);
	int screen = DefaultScreen(disp);
	XF86VidModeModeInfo **avail_modes;

	int depth = DefaultDepth(disp, screen);

	if (disp == NULL) {
#ifdef _DEBUG
		printf("Could not open X connection\n");
#endif
		return NULL;
	}
	if (!getDisplayModes(disp, screen, &num_modes, &avail_modes)) {
#ifdef _DEBUG
		printf("Could not get display modes\n");
#endif
		return NULL;
	}
	// Allocate an array of DisplayModes big enough
	jclass displayModeClass = env->FindClass("org/lwjgl/DisplayMode");
	jobjectArray ret = env->NewObjectArray(num_modes, displayModeClass, NULL);
	jmethodID displayModeConstructor = env->GetMethodID(displayModeClass, "<init>", "(IIII)V");
	
	for (i = 0; i < num_modes; i++) {
		jobject displayMode = env->NewObject(displayModeClass, displayModeConstructor, avail_modes[i]->hdisplay, avail_modes[i]->vdisplay, depth, 0);
		env->SetObjectArrayElement(ret, i, displayMode);
	}
	XFree(avail_modes);
	XCloseDisplay(disp);
	return ret;
}

