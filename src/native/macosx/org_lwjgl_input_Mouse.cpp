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
 * Mac OS X mouse handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <ApplicationServices/ApplicationServices.h>
#include "org_lwjgl_input_Mouse.h"
#include "Window.h"
#include "tools.h"
#include "common_tools.h"
#include "hid.h"

static const int NUM_BUTTONS = 7;
static const int NUM_COOKIES = NUM_BUTTONS + 3;
static const int WHEEL_SCALE = 120;

static jfieldID fid_dx;
static jfieldID fid_dy;
static jfieldID fid_dwheel;
static jfieldID fid_buttons;

static jbyte button_states[NUM_BUTTONS];
static bool buffer_enabled;
/*static int x_axis_index = NUM_BUTTONS;
static int y_axis_index = NUM_BUTTONS + 1;
static int z_axis_index = NUM_BUTTONS + 2;
static hid_device_t hid_dev;*/
static event_queue_t event_queue;

static int last_dx;
static int last_dy;
static int last_dz;
static bool created;

static void handleButton(unsigned char button_index, jbyte state) {
	if (button_index >= NUM_BUTTONS) {
		printfDebug("Button index %d out of range [0..%d]\n", button_index, NUM_BUTTONS);
		return;
	}
	button_states[button_index] = state;
	if (buffer_enabled) {
		putEventElement(&event_queue, button_index);
		putEventElement(&event_queue, state);
	}
}

/*static void pollMouseDevice() {
	hid_event_t event;
cont:
	while (nextDeviceEvent(&hid_dev, &event)) {
		if (event.cookie_index == x_axis_index) {
			last_dx += event.value;
			continue;
		}
		else if (event.cookie_index == y_axis_index) {
			last_dy += event.value;
			continue;
		}
		else if (event.cookie_index == z_axis_index) {
			last_dz += event.value;
			continue;
		} else {
			for (int i = 0; i < NUM_BUTTONS; i++) {
				if (event.cookie_index == i) {
					if (event.value != 0)
						handleButton(i, 1);
					else
						handleButton(i, 0);
					goto cont;
				}
			}
		}
		printfDebug("Recieved an unknown HID device event\n");
	}
}
*/
static void resetDeltas(void) {
	last_dx = 0;
	last_dy = 0;
	last_dz = 0;
}

static void handleButtonEvent(EventRef event, unsigned char state) {
	EventMouseButton button;
	OSStatus err = GetEventParameter(event, kEventParamMouseButton, typeMouseButton, NULL, sizeof(button), NULL, &button);
	if (err != noErr) {
		printfDebug("Could not get button parameter from event\n");
		return;
	}
	handleButton(button - 1, state);
}

static void handleMovedEvent(EventRef event) {
	HIPoint delta;
	OSStatus err = GetEventParameter(event, kEventParamMouseDelta, typeHIPoint, NULL, sizeof(delta), NULL, &delta);
	if (err != noErr) {
		printfDebug("Could not delta parameter from event\n");
		return;
	}
	last_dx += (int)delta.x;
	last_dy += (int)delta.y;
}

static void handleWheelEvent(EventRef event) {
	long delta;
	OSStatus err = GetEventParameter(event, kEventParamMouseWheelDelta, typeLongInteger, NULL, sizeof(delta), NULL, &delta);
	if (err != noErr) {
		printfDebug("Could not delta parameter from event\n");
		return;
	}
	last_dz += (int)delta;
}

void handleMouseEvent(EventRef event) {
	UInt32 event_kind = GetEventKind(event);
	switch (event_kind) {
		case kEventMouseDown:
			handleButtonEvent(event, 1);
			break;
		case kEventMouseUp:
			handleButtonEvent(event, 0);
			break;
		case kEventMouseDragged:
		case kEventMouseMoved:
			handleMovedEvent(event);
			break;
		case kEventMouseWheelMoved:
			handleWheelEvent(event);
			break;
		default:
			break;
	}
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Mouse_nHasWheel(JNIEnv *, jclass) {
	return JNI_TRUE;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetButtonCount(JNIEnv *, jclass) {
	return NUM_BUTTONS;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_initIDs(JNIEnv * env, jclass clazz) {
	fid_dx = env->GetStaticFieldID(clazz, "dx", "I");
	fid_dy = env->GetStaticFieldID(clazz, "dy", "I");
	fid_dwheel = env->GetStaticFieldID(clazz, "dwheel", "I");
	fid_buttons = env->GetStaticFieldID(clazz, "buttons", "[B");
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetNativeCursorCaps(JNIEnv *env, jclass clazz) {
	return 0;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nSetNativeCursor(JNIEnv *env, jclass clazz, jlong cursor_handle) {
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetMinCursorSize(JNIEnv *env, jclass clazz) {
	return 16;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetMaxCursorSize(JNIEnv *env, jclass clazz) {
	return 16;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nCreate(JNIEnv * env, jclass clazz) {
//	native_cursor = false;
	buffer_enabled = false;
	resetDeltas();
	for (int i = 0; i < NUM_BUTTONS; i++) {
		button_states[i] = 0;
	}
	initEventQueue(&event_queue);
/*	hid_cookie_t hid_cookies[NUM_COOKIES];
	for (int i = 0; i < NUM_BUTTONS; i++) {
		hid_cookies[i].usage_page = kHIDPage_Button;
		hid_cookies[i].usage = i + 1;
	}
	hid_cookies[x_axis_index].usage_page = kHIDPage_GenericDesktop;
	hid_cookies[x_axis_index].usage = kHIDUsage_GD_X;
	hid_cookies[y_axis_index].usage_page = kHIDPage_GenericDesktop;
	hid_cookies[y_axis_index].usage = kHIDUsage_GD_Y;
	hid_cookies[z_axis_index].usage_page = kHIDPage_GenericDesktop;
	hid_cookies[z_axis_index].usage = kHIDUsage_GD_Wheel;
	if (!findDevice(&hid_dev, kHIDPage_GenericDesktop, kHIDUsage_GD_Mouse, NUM_COOKIES, hid_cookies, EVENT_BUFFER_SIZE)) {
		throwException(env, "Could not find HID mouse device");
		return;
	}*/
	CGAssociateMouseAndMouseCursorPosition(FALSE);
	CGDisplayHideCursor(CGMainDisplayID());
	created = true;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nDestroy(JNIEnv * env, jclass clazz) {
	//shutdownDevice(&hid_dev);
	if (created) {
		created = false;
		CGDisplayShowCursor(CGMainDisplayID());
		CGAssociateMouseAndMouseCursorPosition(TRUE);
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nPoll(JNIEnv * env, jclass clazz) {
	int dx, dy, dz;
	//pollMouseDevice();
	dz = last_dz*WHEEL_SCALE;
	dx = last_dx;
	dy = -last_dy;
	env->SetStaticIntField(clazz, fid_dx, (jint)dx);
	env->SetStaticIntField(clazz, fid_dy, (jint)dy);
	env->SetStaticIntField(clazz, fid_dwheel, (jint)dz);
	jbyteArray buttons_array = (jbyteArray)env->GetStaticObjectField(clazz, fid_buttons);
	env->SetByteArrayRegion(buttons_array, 0, NUM_BUTTONS, button_states);
	resetDeltas();
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_input_Mouse_nEnableBuffer(JNIEnv *env, jclass clazz) {
	jobject newBuffer = env->NewDirectByteBuffer(getOutputList(&event_queue), getEventBufferSize(&event_queue));
	buffer_enabled = true;
	return newBuffer;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nRead(JNIEnv *env, jclass clazz) {
	return copyEvents(&event_queue, 2);
}
