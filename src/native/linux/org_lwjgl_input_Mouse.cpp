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

static bool pointer_grabbed;
static bool created;

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

static int transformY(int y) {
	return getWindowHeight() - 1 - y;
}

static void transformCursorPos(int x, int y) {
	// transform to OpenGL coordinate system center
	y = transformY(y);
	setCursorPos(x, y);
}

void resetCursor(int x, int y) {
	transformCursorPos(x, y);
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

static void updateCursor(void) {
	Cursor cursor;
	if (shouldGrab())
		cursor = blank_cursor;
	else
		cursor = current_cursor;
	XDefineCursor(getDisplay(), getCurrentWindow(), cursor);
}

static void grabPointer(void) {
	if (!pointer_grabbed) {
		int result;
		int grab_mask = PointerMotionMask | ButtonPressMask | ButtonReleaseMask;
		result = XGrabPointer(getDisplay(), getCurrentWindow(), False, grab_mask, GrabModeAsync, 
					GrabModeAsync, getCurrentWindow(), None, CurrentTime);
		if (result == GrabSuccess) {
			pointer_grabbed = true;
			// make sure we have a centered window
			XF86VidModeSetViewPort(getDisplay(), getCurrentScreen(), 0, 0);
			XFlush(getDisplay());
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

void updatePointerGrab(void) {
	if (!created)
		return;
	if (isFullscreen() || shouldGrab()) {
		grabPointer();
	} else {
		ungrabPointer();
	}
	updateCursor();
}

static void doWarpPointer(void ) {
	XEvent ignore_warp_guard;
	ignore_warp_guard.type = ClientMessage;
	ignore_warp_guard.xclient.message_type = getWarpAtom();
	ignore_warp_guard.xclient.format = 8;
	// Tell event loop to start ignoring motion events
	ignore_warp_guard.xclient.data.b[0] = 1;
	XSendEvent(getDisplay(), getCurrentWindow(), False, 0, &ignore_warp_guard);
	XWarpPointer(getDisplay(), None, getCurrentWindow(), 0, 0, 0, 0, getWindowWidth()/2, transformY(getWindowHeight()/2));
	// Tell event loop to stop ignoring motion events
	ignore_warp_guard.xclient.data.b[0] = 0;
	XSendEvent(getDisplay(), getCurrentWindow(), False, 0, &ignore_warp_guard);
}

static void warpPointer(void) {
	if (!pointer_grabbed || !shouldGrab())
		return;
	// Reset pointer to middle of screen if outside a certain inner border
	if (current_x < POINTER_WARP_BORDER || current_y < POINTER_WARP_BORDER || 
			current_x > getWindowWidth() - POINTER_WARP_BORDER || current_y > getWindowHeight() - POINTER_WARP_BORDER)
		doWarpPointer();
}

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

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nSetNativeCursor (JNIEnv *env, jclass clazz, jlong cursor_handle) {
	if (cursor_handle != 0) {
		Cursor cursor = (Cursor)cursor_handle;
		current_cursor = cursor;
	} else
		current_cursor = None;
	updateCursor();
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetMinCursorSize
  (JNIEnv *env, jclass clazz)
{
	unsigned int width_return = 0;
	unsigned int height_return = 0;
	XQueryBestCursor(getDisplay(), getCurrentWindow(), 1, 1, &width_return, &height_return);
	return width_return > height_return ? width_return : height_return;
}

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
	current_cursor = None;
	created = true;
	pointer_grabbed = false;
	buffer_enabled = false;
	updatePointerGrab();
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

void handleButtonRelease(XButtonEvent *event) {
	handleButton(event, 0);
}

void handlePointerMotion(XMotionEvent *event) {
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

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nGrabMouse(JNIEnv * env, jclass clazz, jboolean new_grab) {
	setGrab(new_grab == JNI_TRUE ? true : false);
	resetCursor(getWindowWidth()/2, transformY(getWindowHeight()/2));
}
