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
#include "Window.h"
#include "common_tools.h"
#include "org_lwjgl_input_Mouse.h"
#include "extxcursor.h"

#define NUM_BUTTONS 3

#define POINTER_WARP_BORDER 10
#define WARP_RETRY 5
// scale the mouse wheel according to win32
#define WHEEL_SCALE 120 

static bool pointer_grabbed = false;
static bool created = false;
static bool should_grab = false;
static bool native_cursor = false;

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
static jbyte buttons[NUM_BUTTONS];
static event_queue_t event_queue;
static bool buffer_enabled;

static Cursor blank_cursor;
static Cursor current_cursor;

static int cap(int val, int min, int max) {
	if (val < min)
		return min;
	else if (val > max)
		return max;
	else
		return val;
}

static void setCursorPos(int x, int y) {
	current_x = cap(x, 0, getWindowWidth() - 1);
	current_y = cap(y, 0, getWindowHeight() - 1);
}

static void transformCursorPos(int x, int y) {
	// transform to OpenGL coordinate system center
	y = getWindowHeight() - 1 - y;
	setCursorPos(x, y);
}

static void centerCursor() {
	transformCursorPos(getWindowWidth()/2, getWindowHeight()/2);
	last_x = current_x;
	last_y = current_y;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_initIDs
  (JNIEnv * env, jclass clazz)
{
	if (fid_buttons == NULL)
		fid_buttons = env->GetStaticFieldID(clazz, "buttons", "[B");
	if (fid_dx == NULL)
		fid_dx = env->GetStaticFieldID(clazz, "dx", "I");
	if (fid_dy == NULL)
		fid_dy = env->GetStaticFieldID(clazz, "dy", "I");
	if (fid_dwheel == NULL)
		fid_dwheel = env->GetStaticFieldID(clazz, "dwheel", "I");
}

static bool blankCursor(void) {
	unsigned int best_width, best_height;
	if (XQueryBestCursor(getCurrentDisplay(), getCurrentWindow(), 1, 1, &best_width, &best_height) == 0) {
#ifdef _DEBUG
		printf("Could not query best cursor size\n");
#endif
		return false;
	}
	Pixmap mask = XCreatePixmap(getCurrentDisplay(), getCurrentWindow(), best_width, best_height, 1);
	XGCValues gc_values;
	gc_values.foreground = 0;
	GC gc = XCreateGC(getCurrentDisplay(), mask, GCForeground, &gc_values);
	XFillRectangle(getCurrentDisplay(), mask, gc, 0, 0, best_width, best_height);
	XFreeGC(getCurrentDisplay(), gc);
	XColor dummy_color;
	blank_cursor = XCreatePixmapCursor(getCurrentDisplay(), mask, mask, &dummy_color, &dummy_color, 0, 0);
	XFreePixmap(getCurrentDisplay(), mask);
	return true;
}

bool isNativeCursor(void) {
	return native_cursor;
}

static void grabPointer(void) {
	if (isFullscreen() || !native_cursor) {
		if (!pointer_grabbed) {
			int result;
			int grab_mask = PointerMotionMask | ButtonPressMask | ButtonReleaseMask;
			result = XGrabPointer(getCurrentDisplay(), getCurrentWindow(), False, grab_mask, GrabModeAsync, 
						GrabModeAsync, getCurrentWindow(), current_cursor, CurrentTime);
			if (result == GrabSuccess) {
				pointer_grabbed = true;
				// make sure we have a centered window
				XF86VidModeSetViewPort(getCurrentDisplay(), getCurrentScreen(), 0, 0);
				XFlush(getCurrentDisplay());
			}
		}
	}
}

static void ungrabPointer(void) {
	if (pointer_grabbed) {
		pointer_grabbed = false;
		XUngrabPointer(getCurrentDisplay(), CurrentTime);
		XFlush(getCurrentDisplay());
	}
}

static void updateGrab(void) {
	if (!created)
		return;
	if (should_grab) {
		grabPointer();
	} else {
		ungrabPointer();
	}
}

void acquirePointer(void) {
	should_grab = true;
	updateGrab();
}

void releasePointer(void) {
	should_grab = false;
	updateGrab();
}

static void doWarpPointer(void ) {
	int i;
	centerCursor();
	XWarpPointer(getCurrentDisplay(), None, getCurrentWindow(), 0, 0, 0, 0, current_x, current_y);
	XEvent event;
	// Try to catch the warp pointer event
	for (i = 0; i < WARP_RETRY; i++) {
		XMaskEvent(getCurrentDisplay(), PointerMotionMask, &event);
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

static void warpPointer(void) {
	if (!pointer_grabbed || native_cursor)
		return;
	// Reset pointer to middle of screen if outside a certain inner border
	if (current_x < POINTER_WARP_BORDER || current_y < POINTER_WARP_BORDER || 
			current_x > getWindowWidth() - POINTER_WARP_BORDER || current_y > getWindowHeight() - POINTER_WARP_BORDER)
		doWarpPointer();
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nIsNativeCursorSupported
 * Signature: ()Z
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetNativeCursorCaps
  (JNIEnv *env, jclass clazz) {
	int caps = 0;
	if (!isXcursorLoaded())
		return caps;
	XcursorBool argb_supported = XcursorSupportsARGB(getCurrentDisplay());
	XcursorBool anim_supported = XcursorSupportsAnim(getCurrentDisplay());
	if (argb_supported)
		caps |= org_lwjgl_input_Mouse_CURSOR_8_BIT_ALPHA | org_lwjgl_input_Mouse_CURSOR_ONE_BIT_TRANSPARENCY;
	if (anim_supported)
		caps |= org_lwjgl_input_Mouse_CURSOR_ANIMATION;
	return caps;
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nSetNativeCursor
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nSetNativeCursor
  (JNIEnv *env, jclass clazz, jlong cursor_handle)
{
	if (cursor_handle != 0) {
		Cursor cursor = (Cursor)cursor_handle;
		if (!native_cursor) {
			doWarpPointer();
			native_cursor = true;
		}
		XDefineCursor(getCurrentDisplay(), getCurrentWindow(), cursor);
		current_cursor = cursor;
		updateInput();
	} else {
		if (native_cursor) {
			current_cursor = blank_cursor;
			XUndefineCursor(getCurrentDisplay(), getCurrentWindow());
			native_cursor = false;
			updateInput();
		}
	}
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nGetMaxCursorSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetMinCursorSize
  (JNIEnv *env, jclass clazz)
{
	unsigned int width_return = 0;
	unsigned int height_return = 0;
	XQueryBestCursor(getCurrentDisplay(), getCurrentWindow(), 1, 1, &width_return, &height_return);
	return width_return > height_return ? width_return : height_return;
}

/*
 * Class:     org_lwjgl_input_Mouse
 * Method:    nGetMaxCursorSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetMaxCursorSize
  (JNIEnv *env, jclass clazz)
{
	unsigned int width_return = 0;
	unsigned int height_return = 0;
	XQueryBestCursor(getCurrentDisplay(), getCurrentWindow(), 0xffffffff, 0xffffffff, &width_return, &height_return);
	return width_return > height_return ? height_return : width_return;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Mouse_nHasWheel(JNIEnv *, jclass) {
	return JNI_TRUE;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetButtonCount(JNIEnv *, jclass) {
	return NUM_BUTTONS;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nCreate
  (JNIEnv * env, jclass clazz)
{
	int i;
	current_z = last_z = 0;
	doWarpPointer();
	for (i = 0; i < NUM_BUTTONS; i++)
		buttons[i] = 0;
	if (!blankCursor()) {
		throwException(env, "Could not create blank cursor");
		return;
	}
	current_cursor = blank_cursor;
	native_cursor = false;
	created = true;
	should_grab = true;
	pointer_grabbed = false;
	buffer_enabled = false;
	updateGrab();
	initEventQueue(&event_queue);
	loadXcursor();
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nDestroy
  (JNIEnv * env, jclass clazz)
{
	closeXcursor();
	ungrabPointer();
	XFreeCursor(getCurrentDisplay(), blank_cursor);
	created = false;
	should_grab = false;
}

static unsigned char mapButton(XButtonEvent *event) {
	switch (event->button) {
		case Button1:
			return 0;
		case Button2:
			return 2;
		case Button3:
			return 1;
		default: return NUM_BUTTONS;
	}
}

static void handleButton(XButtonEvent *event, unsigned char state) {
	unsigned char button_num = mapButton(event);
	if (button_num == NUM_BUTTONS)
		return;
	buttons[button_num] = state;
	if (buffer_enabled) {
		putEventElement(&event_queue, button_num);
		putEventElement(&event_queue, state);
	}
}

void handleButtonPress(XButtonEvent *event) {
	if (pointer_grabbed || native_cursor) {
		switch (event->button) {
			case Button4:
				current_z += WHEEL_SCALE;
				break;
			case Button5:
				current_z -= WHEEL_SCALE;
				break;
			default: break;
		}
		handleButton(event, 1);
	}
}

void handleButtonRelease(XButtonEvent *event) {
	if (pointer_grabbed || native_cursor) {
		handleButton(event, 0);
	}
}

void handlePointerMotion(XMotionEvent *event) {
	if (pointer_grabbed || native_cursor)
		setCursorPos(event->x, event->y);
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nPoll(JNIEnv * env, jclass clazz) {
	int moved_x = current_x - last_x;
	int moved_y = -(current_y - last_y);
	int moved_z = current_z - last_z;
	env->SetStaticIntField(clazz, fid_dx, (jint)moved_x);
	env->SetStaticIntField(clazz, fid_dy, (jint)moved_y);
	env->SetStaticIntField(clazz, fid_dwheel, (jint)moved_z);
	last_x = current_x;
	last_y = current_y;
	last_z = current_z;
	jbyteArray buttons_array = (jbyteArray)env->GetStaticObjectField(clazz, fid_buttons);
	env->SetByteArrayRegion(buttons_array, 0, NUM_BUTTONS, buttons);
	warpPointer();
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_input_Mouse_nEnableBuffer(JNIEnv *env, jclass clazz) {
	jobject newBuffer = env->NewDirectByteBuffer(getOutputList(&event_queue), getEventBufferSize(&event_queue));
	buffer_enabled = true;
	return newBuffer;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nRead(JNIEnv *env, jclass clazz) {
	return copyEvents(&event_queue, 2);
}
