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
 * Linux specific window functions.
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
#include <Window.h>
#include "org_lwjgl_Window.h"

static Atom delete_atom;
static Display *current_disp;
static Window current_win;
static int current_screen;
static bool current_fullscreen;
static int current_height;
static int current_width;

static bool input_released;

static void waitMapped(Display *disp, Window win) {
	XEvent event;

	do {
		XMaskEvent(disp, StructureNotifyMask, &event);
	} while ((event.type != MapNotify) || (event.xmap.event != win));
}

bool releaseInput(void) {
	if (current_fullscreen)
		return false;
	releaseKeyboard();
	releasePointer();
	input_released = true;
	return true;
}

static void acquireInput(void) {
	if (input_released) {
		acquireKeyboard();
		acquirePointer();
		input_released = false;
	}
}

static void handleMessages(JNIEnv *env, jobject window_obj) {
	XEvent event;
	while (XPending(current_disp) > 0) {
		XNextEvent(current_disp, &event);
		switch (event.type) {
			case ClientMessage:
				if ((event.xclient.format == 32) && ((Atom)event.xclient.data.l[0] == delete_atom))
					env->SetBooleanField(window_obj, env->GetFieldID(env->GetObjectClass(window_obj), "closeRequested", "Z"), JNI_TRUE);
				break;
			case FocusIn:
				acquireInput();
				break;
			case MapNotify:
				env->SetBooleanField(window_obj, env->GetFieldID(env->GetObjectClass(window_obj), "dirty", "Z"), JNI_TRUE);
				env->SetBooleanField(window_obj, env->GetFieldID(env->GetObjectClass(window_obj), "minimized", "Z"), JNI_FALSE);
				break;
			case UnmapNotify:
				env->SetBooleanField(window_obj, env->GetFieldID(env->GetObjectClass(window_obj), "minimized", "Z"), JNI_TRUE);
				break;
			case Expose:
				env->SetBooleanField(window_obj, env->GetFieldID(env->GetObjectClass(window_obj), "dirty", "Z"), JNI_TRUE);
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

static void setWindowTitle(const char *title) {
	XStoreName(current_disp, current_win, title);
}

JNIEXPORT void JNICALL Java_org_lwjgl_Window_nSetTitle
  (JNIEnv * env, jobject obj, jstring title_obj)
{
	const char * title = env->GetStringUTFChars(title_obj, NULL);
	setWindowTitle(title);
	env->ReleaseStringUTFChars(title_obj, title);
}

void createWindow(JNIEnv* env, Display *disp, int screen, XVisualInfo *vis_info, jstring title, int x, int y, int width, int height, bool fullscreen) {
	Window root_win;
	Window win;
	XSetWindowAttributes attribs;
	Colormap cmap;
	int attribmask;

	current_disp = disp;
	current_screen = screen;
	input_released = false;
	current_fullscreen = fullscreen;
	current_width = width;
	current_height = height;

	root_win = RootWindow(disp, screen);
	cmap = XCreateColormap(disp, root_win, vis_info->visual, AllocNone);
	attribs.colormap = cmap;
	attribs.event_mask = ExposureMask | FocusChangeMask | VisibilityChangeMask| StructureNotifyMask | KeyPressMask | KeyReleaseMask | ButtonPressMask | ButtonReleaseMask | PointerMotionMask;
	attribs.background_pixel = 0xFF000000;
	attribmask = CWColormap | CWBackPixel | CWEventMask;
	if (fullscreen) {
		attribmask |= CWOverrideRedirect;
		attribs.override_redirect = True;
	}
	win = XCreateWindow(disp, root_win, x, y, width, height, 0, vis_info->depth, InputOutput, vis_info->visual, attribmask, &attribs);
	XFreeColormap(disp, cmap);
#ifdef _DEBUG
	printf("Created window\n");
#endif
	current_win = win;
	Java_org_lwjgl_Window_nSetTitle(env, NULL, title);
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
	XClearWindow(disp, win);
	XSync(disp, True);
}

void destroyWindow() {
	XDestroyWindow(current_disp, current_win);
	current_disp = NULL;
}

Display *getCurrentDisplay(void) {
	return current_disp;
}

int getCurrentScreen(void) {
	return current_screen;
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
 * Method:    tick
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Window_tick
  (JNIEnv *env, jobject obj)
{
	handleMessages(env, obj);
}

/*
 * Utility function to throw an Exception
 */
void throwException(JNIEnv * env, const char * err)
{
	jclass cls = env->FindClass("java/lang/Exception");
	env->ThrowNew(cls, err);
	env->DeleteLocalRef(cls);
}

/*
 * Utility function to throw a RuntimeException
 */
void throwRuntimeException(JNIEnv * env, const char * err)
{
	jclass cls = env->FindClass("java/lang/RuntimeException");
	env->ThrowNew(cls, err);
	env->DeleteLocalRef(cls);
}
