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
#include <X11/Xcursor/Xcursor.h>
#include <assert.h>
#include <string.h>
#include "Window.h"
#include "common_tools.h"
#include "display.h"
#include "org_lwjgl_input_Mouse.h"

#define NUM_BUTTONS 3

#define POINTER_WARP_BORDER 10
// scale the mouse wheel according to win32
#define WHEEL_SCALE 120 

static bool pointer_grabbed;
static bool created;

static int accum_dx;
static int accum_dy;
static int accum_dz;
static int last_poll_x;
static int last_poll_y;
static int last_event_x;
static int last_event_y;
static int last_z;
static jbyte buttons[NUM_BUTTONS];
static event_queue_t event_queue;
static bool buffer_enabled;

static Cursor blank_cursor;
static Cursor current_cursor;

static bool putMouseEvent(jint button, jint state, jint dx, jint dy, jint dz) {
	jint event[] = {button, state, dx, dy, dz};
	return putEvent(&event_queue, event);
}

static void setCursorPos(int x, int y) {
	jint poll_dx = x - last_poll_x;
	jint poll_dy = y - last_poll_y;
	accum_dx += poll_dx;
	accum_dy += poll_dy;
	last_poll_x = x;
	last_poll_y = y;
	jint event_dx = x - last_event_x;
	jint event_dy = y - last_event_y;
	if (putMouseEvent(-1, 0, event_dx, -event_dy, 0)) {
		last_event_x = x;
		last_event_y = y;
	}
}

static int transformY(int y) {
	return getWindowHeight() - 1 - y;
}

static void resetCursor(int x, int y) {
	last_poll_x = x;
	last_poll_y = y;
	last_event_x = x;
	last_event_y = y;
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
	if (shouldGrab()) {
		cursor = blank_cursor;
	} else {
		cursor = current_cursor;
	}
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
			if (isLegacyFullscreen()) {
				XWindowAttributes win_attribs;
				XGetWindowAttributes(getDisplay(), getCurrentWindow(), &win_attribs);
				XF86VidModeSetViewPort(getDisplay(), getCurrentScreen(), win_attribs.x, win_attribs.y);
			}
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
	if (isLegacyFullscreen() || shouldGrab()) {
		grabPointer();
	} else {
		ungrabPointer();
	}
	updateCursor();
}

void handleWarpEvent(XClientMessageEvent *event) {
	int center_x = event->data.l[0];
	int center_y = event->data.l[1];
	resetCursor(center_x, center_y);
}

static void doWarpPointer(int center_x, int center_y) {
	XEvent warp_event;
	warp_event.type = ClientMessage;
	warp_event.xclient.message_type = getWarpAtom();
	warp_event.xclient.format = 32;
	warp_event.xclient.data.l[0] = center_x;
	warp_event.xclient.data.l[1] = center_y;
	XSendEvent(getDisplay(), getCurrentWindow(), False, 0, &warp_event);
	XWarpPointer(getDisplay(), None, getCurrentWindow(), 0, 0, 0, 0, center_x, center_y);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetNativeCursorCaps
  (JNIEnv *env, jclass clazz) {
	int caps = 0;
	Display *disp = incDisplay(env);
	if (disp == NULL)
		return caps;
	XcursorBool argb_supported = XcursorSupportsARGB(getDisplay());
	XcursorBool anim_supported = XcursorSupportsAnim(getDisplay());
	if (argb_supported)
		caps |= org_lwjgl_input_Mouse_CURSOR_8_BIT_ALPHA | org_lwjgl_input_Mouse_CURSOR_ONE_BIT_TRANSPARENCY;
	if (anim_supported)
		caps |= org_lwjgl_input_Mouse_CURSOR_ANIMATION;
	decDisplay();
	return caps;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nSetNativeCursor(JNIEnv *env, jclass clazz, jobject cursor_handle) {
	if (cursor_handle != NULL) {
		Cursor *cursor = (Cursor *)(*env)->GetDirectBufferAddress(env, cursor_handle);
		current_cursor = *cursor;
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

JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Mouse_nHasWheel(JNIEnv *env , jclass clazz) {
	return JNI_TRUE;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetButtonCount(JNIEnv *env, jclass clazz) {
	return NUM_BUTTONS;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nCreate
  (JNIEnv * env, jclass clazz)
{
	Display *disp = incDisplay(env);
	if (disp == NULL)
		return;
	int i;
	last_z = last_poll_y = last_poll_x = last_event_x = last_event_y = accum_dx = accum_dy = accum_dz = 0;
	for (i = 0; i < NUM_BUTTONS; i++)
		buttons[i] = 0;
	if (!blankCursor()) {
		decDisplay();
		throwException(env, "Could not create blank cursor");
		return;
	}
	current_cursor = None;
	created = true;
	pointer_grabbed = false;
	buffer_enabled = false;
	updatePointerGrab();
	initEventQueue(&event_queue, 5);
	doWarpPointer(getWindowWidth()/2, getWindowHeight()/2);
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nDestroy
  (JNIEnv * env, jclass clazz)
{
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
	putMouseEvent(button_num, state, 0, 0, 0);
}

void handleButtonPress(XButtonEvent *event) {
	jint delta = 0;
	switch (event->button) {
		case Button4:
			delta = WHEEL_SCALE;
			putMouseEvent(-1, 0, 0, 0, delta);
			break;
		case Button5:
			delta = -WHEEL_SCALE;
			putMouseEvent(-1, 0, 0, 0, delta);
			break;
		default: 
			break;
	}
	accum_dz += delta;
	handleButton(event, 1);
}

void handleButtonRelease(XButtonEvent *event) {
	handleButton(event, 0);
}

static int max(int v1, int v2) {
	return v1 > v2 ? v1 : v2;
}

static int min(int v1, int v2) {
	return v1 < v2 ? v1 : v2;
}

void handlePointerMotion(XMotionEvent *event) {
	int x = event->x;
	int y = event->y;
	setCursorPos(x, y);
	if (!pointer_grabbed || !shouldGrab())
		return;
	int x_root = event->x_root;
	int y_root = event->y_root;
	// find the window position in root coordinates
	int win_left = x_root - x;
	int win_top = y_root - y;
	int win_right = win_left + getWindowWidth();
	int win_bottom = win_top + getWindowHeight();
	// cap the window position to the screen dimensions
	int border_left = max(0, win_left);
	int border_top = max(0, win_top);
	int border_right = min(getScreenModeWidth(), win_right);
	int border_bottom = min(getScreenModeHeight(), win_bottom);
	// determine whether the cursor is outside the bounds
	bool outside_limits = 	x_root < border_left + POINTER_WARP_BORDER || y_root < border_top + POINTER_WARP_BORDER ||
							x_root > border_right - POINTER_WARP_BORDER || y_root > border_bottom - POINTER_WARP_BORDER;
	if (outside_limits) {
		// Find the center of the limits in window coordinates
		int center_x = (border_right - border_left)/2;
		int center_y = (border_bottom - border_top)/2;
		doWarpPointer(center_x, center_y);
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nPoll(JNIEnv * env, jclass clazz, jobject coord_buffer_obj, jobject button_buffer_obj) {
	int *coords = (int *)(*env)->GetDirectBufferAddress(env, coord_buffer_obj);
	int coords_length = (*env)->GetDirectBufferCapacity(env, coord_buffer_obj);
	unsigned char *buttons_buffer = (unsigned char *)(*env)->GetDirectBufferAddress(env, button_buffer_obj);
	int buttons_length = (*env)->GetDirectBufferCapacity(env, button_buffer_obj);
	handleMessages();
	if (coords_length < 3) {
		printfDebug("ERROR: Not enough space in coords array: %d < 3\n", coords_length);
		return;
	}
	coords[0] = accum_dx;
	coords[1] = -accum_dy;
	coords[2] = accum_dz;
	accum_dx = accum_dy = accum_dz = 0;
	int num_buttons = NUM_BUTTONS;
	if (num_buttons > buttons_length)
		num_buttons = buttons_length;
	int i;
	for (i = 0; i < num_buttons; i++)
		buttons_buffer[i] = buttons[i];
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nEnableBuffer(JNIEnv *env, jclass clazz) {
	buffer_enabled = true;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nRead(JNIEnv *env, jclass clazz, jobject buffer, jint buffer_position) {
	jint* buffer_ptr = (jint *)(*env)->GetDirectBufferAddress(env, buffer);
	int buffer_size = ((*env)->GetDirectBufferCapacity(env, buffer))/sizeof(jint) - buffer_position;
	handleMessages();
	return copyEvents(&event_queue, buffer_ptr + buffer_position, buffer_size);
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nGrabMouse(JNIEnv * env, jclass clazz, jboolean new_grab) {
	setGrab(new_grab == JNI_TRUE ? true : false);
	resetCursor(getWindowWidth()/2, transformY(getWindowHeight()/2));
	accum_dx = accum_dy = 0;
}
