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
 * Linux mouse handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */


#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/extensions/xf86vmode.h>
#include <assert.h>
#include <string.h>
#include "org_lwjgl_input_Mouse.h"

#define NUM_BUTTONS 3

#define POINTER_WARP_BORDER 10
#define WARP_RETRY 5

extern Display *disp;
extern Window win;
extern int screen;
extern bool isFullscreen(void);
extern bool isFocused(void);
extern int getWindowWidth(void);
extern int getWindowHeight(void);

static bool pointer_grabbed;

static jfieldID fid_has_wheel = NULL;
static jfieldID fid_button_count = NULL;
static jfieldID fid_buttons = NULL;
static jfieldID fid_dx = NULL;
static jfieldID fid_dy = NULL;
static jfieldID fid_dwheel = NULL;

static int last_x;
static int last_y;
static int last_z;
static int current_x;
static int current_y;
static int current_z;
static unsigned char buttons[NUM_BUTTONS];

static Cursor blank_cursor;

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_initIDs
  (JNIEnv * env, jclass clazz)
{
	if (fid_has_wheel == NULL)
		fid_has_wheel = env->GetStaticFieldID(clazz, "hasWheel", "Z");
	if (fid_button_count == NULL)
		fid_button_count = env->GetStaticFieldID(clazz, "buttonCount", "I");
	if (fid_buttons == NULL)
		fid_buttons = env->GetStaticFieldID(clazz, "buttons", "[Z");
	if (fid_dx == NULL)
		fid_dx = env->GetStaticFieldID(clazz, "dx", "I");
	if (fid_dy == NULL)
		fid_dy = env->GetStaticFieldID(clazz, "dy", "I");
	if (fid_dwheel == NULL)
		fid_dwheel = env->GetStaticFieldID(clazz, "dwheel", "I");
}

int blankCursor(void) {
	unsigned int best_width, best_height;
	if (XQueryBestCursor(disp, win, 1, 1, &best_width, &best_height) == 0) {
#ifdef _DEBUG
		printf("Could not query best cursor size\n");
#endif
		return 0;
	}
	Pixmap mask = XCreatePixmap(disp, win, best_width, best_height, 1);
	XGCValues gc_values;
	gc_values.foreground = 0;
	GC gc = XCreateGC(disp, mask, GCForeground, &gc_values);
	XFillRectangle(disp, mask, gc, 0, 0, best_width, best_height);
	XFreeGC(disp, gc);
	XColor dummy_color;
	blank_cursor = XCreatePixmapCursor(disp, mask, mask, &dummy_color, &dummy_color, 0, 0);
	XFreePixmap(disp, mask);
	return 1;
}

int grabPointer(void) {
	int result;
	int mask = EnterWindowMask | LeaveWindowMask | PointerMotionMask | ButtonPressMask | ButtonReleaseMask;
	if (isFullscreen()) {
		result = XGrabPointer(disp, win, False, mask, GrabModeAsync, GrabModeAsync, win, blank_cursor, CurrentTime);
		XWarpPointer(disp, None, win, 0, 0, 0, 0, current_x, current_y);
		XF86VidModeSetViewPort(disp, screen, 0, 0); // make sure we have a centered window
	} else
		result = XGrabPointer(disp, win, False, mask, GrabModeAsync, GrabModeAsync, None, blank_cursor, CurrentTime);
	if (result == GrabSuccess)
		pointer_grabbed = true;
	return result;
}

void ungrabPointer(void) {
	pointer_grabbed = false;
	XUngrabPointer(disp, CurrentTime);
}

int updatePointerGrab(void) {
	if (isFullscreen()) {
		if (!pointer_grabbed)
			return grabPointer();
	} else {
		if (isFocused()) {
			if (!pointer_grabbed)
				return grabPointer();
		} else {
			if (pointer_grabbed)
				ungrabPointer();
		}
	}
	return GrabSuccess;
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nCreate
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Mouse_nCreate
  (JNIEnv * env, jclass clazz)
{
	int i;
	
	jbooleanArray buttons_array = env->NewBooleanArray(NUM_BUTTONS);
	env->SetStaticObjectField(clazz, fid_buttons, buttons_array);
	env->SetStaticIntField(clazz, fid_button_count, NUM_BUTTONS);
	env->SetStaticBooleanField(clazz, fid_has_wheel, JNI_TRUE);
	current_x = current_y = current_z = last_x = last_y = last_z = pointer_grabbed = 0;
	for (i = 0; i < NUM_BUTTONS; i++)
		buttons[i] = JNI_FALSE;
	if (!blankCursor()) {
#ifdef _DEBUG
		printf("Could create blank cursor\n");
#endif
		return JNI_FALSE;
	}
	if (updatePointerGrab() != GrabSuccess) {
#ifdef _DEBUG
		printf("Could not grab pointer\n");
#endif
		return JNI_FALSE;
	}
	return JNI_TRUE;
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nDestroy
  (JNIEnv * env, jclass clazz)
{
	if (pointer_grabbed)
		ungrabPointer();
	XFreeCursor(disp, blank_cursor);
}

int checkPointer() {
	XEvent event;
	int count = 0;
	updatePointerGrab();
	while (XCheckMaskEvent(disp, ButtonPressMask | ButtonReleaseMask | PointerMotionMask, &event)) {
		count++;
		switch (event.type) {
			case ButtonPress:
				switch (event.xbutton.button) {
					case Button1:
						buttons[0] = JNI_TRUE;
						break;
					case Button2:
						buttons[1] = JNI_TRUE;
						break;
					case Button3:
						buttons[2] = JNI_TRUE;
						break;
					case Button4:
						current_z--;
						break;
					case Button5:
						current_z++;
						break;
					default: assert(0);
				}
				break;
			case ButtonRelease:
				switch (event.xbutton.button) {
					case Button1:
						buttons[0] = JNI_FALSE;
						break;
					case Button2:
						buttons[1] = JNI_FALSE;
						break;
					case Button3:
						buttons[2] = JNI_FALSE;
						break;
					case Button4: /* Fall through */
					case Button5:
						break;
					default: assert(0);
				}
				break;
			case MotionNotify:
				current_x = event.xmotion.x;
				current_y = event.xmotion.y;
				break;
			default: assert(0);
		}
	}
	return count;
}

void warpPointer(void) {
	int i;
	// Reset pointer to middle of screen if inside a certain inner border
	if (current_x < POINTER_WARP_BORDER || current_y < POINTER_WARP_BORDER || 
			current_x > getWindowWidth() - POINTER_WARP_BORDER || current_y > getWindowHeight() - POINTER_WARP_BORDER) {
		current_x = last_x = getWindowWidth()/2;
		current_y = last_y = getWindowHeight()/2;
		XWarpPointer(disp, None, win, 0, 0, 0, 0, current_x, current_y);
		XEvent event;
		// Try to catch the warp pointer event
		for (i = 0; i < WARP_RETRY; i++) {
			XMaskEvent(disp, PointerMotionMask, &event);
			if (event.xmotion.x > current_x - POINTER_WARP_BORDER &&
				event.xmotion.x < current_x + POINTER_WARP_BORDER &&
				event.xmotion.y > current_y - POINTER_WARP_BORDER &&
				event.xmotion.y < current_y + POINTER_WARP_BORDER)
				break;
#ifdef _DEBUG
			printf("Skipped event searching for warp event %d, %d\n", event.xmotion.x, event.xmotion.y);
#endif
		}
#ifdef _DEBUG
		if (i == WARP_RETRY)
			printf("Never got warp event\n");
#endif
	}
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nPoll
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nPoll
  (JNIEnv * env, jclass clazz)
{
	checkPointer();
	int moved_x = current_x - last_x;
	int moved_y = current_y - last_y;
	int moved_z = current_z - last_z;
	env->SetStaticIntField(clazz, fid_dx, (jint)moved_x);
	env->SetStaticIntField(clazz, fid_dy, (jint)moved_y);
	env->SetStaticIntField(clazz, fid_dwheel, (jint)moved_z);
	last_x = current_x;
	last_y = current_y;
	last_z = current_z;
	jbooleanArray buttons_array = (jbooleanArray)env->GetStaticObjectField(clazz, fid_buttons);
	env->SetBooleanArrayRegion(buttons_array, 0, NUM_BUTTONS, buttons);
	if (isFullscreen())
		warpPointer();
}


/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nEnableBuffer
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nEnableBuffer
  (JNIEnv * env, jclass clazz) {
      printf("*** FIXME: nEnableBuffer not implemented!\n*");
}
