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

static bool blankCursor(void) {
	unsigned int best_width, best_height;
	if (XQueryBestCursor(getDisplay(), getCurrentWindow(), 1, 1, &best_width, &best_height) == 0) {
		printfDebug("Could not query best cursor size\n");
		return false;
	}
	Pixmap mask = XCreatePixmap(getDisplay(), getCurrentWindow(), best_width, best_height, 1);
	XGCValues gc_values;
	gc_values.foreground = 0;
	GC gc = XCreateGC(getDisplay(), mask, GCForeground, &gc_values);
	XFillRectangle(getDisplay(), mask, gc, 0, 0, best_width, best_height);
	XFreeGC(getDisplay(), gc);
	XColor dummy_color;
	blank_cursor = XCreatePixmapCursor(getDisplay(), mask, mask, &dummy_color, &dummy_color, 0, 0);
	XFreePixmap(getDisplay(), mask);
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
			result = XGrabPointer(getDisplay(), getCurrentWindow(), False, grab_mask, GrabModeAsync, 
						GrabModeAsync, getCurrentWindow(), current_cursor, CurrentTime);
			if (result == GrabSuccess) {
				pointer_grabbed = true;
				// make sure we have a centered window
				XF86VidModeSetViewPort(getDisplay(), getCurrentScreen(), 0, 0);
				XFlush(getDisplay());
			}
		}
	}
}

static void ungrabPointer(void) {
	if (pointer_grabbed) {
		pointer_grabbed = false;
		XUngrabPointer(getDisplay(), CurrentTime);
		XFlush(getDisplay());
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
	XWarpPointer(getDisplay(), None, getCurrentWindow(), 0, 0, 0, 0, current_x, current_y);
	XEvent event;
	// Try to catch the warp pointer event
	for (i = 0; i < WARP_RETRY; i++) {
		XMaskEvent(getDisplay(), PointerMotionMask, &event);
		if (event.xmotion.x > current_x - POINTER_WARP_BORDER &&
			event.xmotion.x < current_x + POINTER_WARP_BORDER &&
			event.xmotion.y > current_y - POINTER_WARP_BORDER &&
			event.xmotion.y < current_y + POINTER_WARP_BORDER)
			break;
		printfDebug("Skipped event searching for warp event %d, %d\n", event.xmotion.x, event.xmotion.y);
	}
	if (i == WARP_RETRY)
		printfDebug("Never got warp event\n");
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
	XcursorBool argb_supported = XcursorSupportsARGB(getDisplay());
	XcursorBool anim_supported = XcursorSupportsAnim(getDisplay());
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
		XDefineCursor(getDisplay(), getCurrentWindow(), cursor);
		current_cursor = cursor;
		updateInput();
	} else {
		if (native_cursor) {
			current_cursor = blank_cursor;
			XUndefineCursor(getDisplay(), getCurrentWindow());
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
	XQueryBestCursor(getDisplay(), getCurrentWindow(), 1, 1, &width_return, &height_return);
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
	XQueryBestCursor(getDisplay(), getCurrentWindow(), 0xffffffff, 0xffffffff, &width_return, &height_return);
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
	Display *disp = incDisplay(env);
	if (disp == NULL)
		return;
	int i;
	current_z = last_z = 0;
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
	doWarpPointer();
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nDestroy
  (JNIEnv * env, jclass clazz)
{
	closeXcursor();
	ungrabPointer();
	XFreeCursor(getDisplay(), blank_cursor);
	created = false;
	should_grab = false;
	decDisplay();
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

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nPoll(JNIEnv * env, jclass clazz, jobject coord_buffer_obj, jobject button_buffer_obj) {
	int moved_x = current_x - last_x;
	int moved_y = -(current_y - last_y);
	int moved_z = current_z - last_z;
	int *coords = (int *)env->GetDirectBufferAddress(coord_buffer_obj);
	int coords_length = env->GetDirectBufferCapacity(coord_buffer_obj);
	unsigned char *buttons_buffer = (unsigned char *)env->GetDirectBufferAddress(button_buffer_obj);
	int buttons_length = env->GetDirectBufferCapacity(button_buffer_obj);
	if (coords_length < 3) {
		printfDebug("ERROR: Not enough space in coords array: %d < 3\n", coords_length);
		return;
	}
	coords[0] = moved_x;
	coords[1] = moved_y;
	coords[2] = moved_z;
	last_x = current_x;
	last_y = current_y;
	last_z = current_z;
	int num_buttons = NUM_BUTTONS;
	if (num_buttons > buttons_length)
		num_buttons = buttons_length;
	for (int i = 0; i < num_buttons; i++)
		buttons_buffer[i] = buttons[i];
	warpPointer();
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nEnableBuffer(JNIEnv *env, jclass clazz) {
	buffer_enabled = true;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nRead(JNIEnv *env, jclass clazz, jobject buffer, jint buffer_position) {
	unsigned char* buffer_ptr = (unsigned char *)env->GetDirectBufferAddress(buffer);
	int buffer_size = env->GetDirectBufferCapacity(buffer) - buffer_position;
	return copyEvents(&event_queue, buffer_ptr + buffer_position, buffer_size, 2);
}
