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

#include "Window.h"
#include "tools.h"
#include "org_lwjgl_input_Mouse.h"

#define NUM_BUTTONS 7

static jfieldID fid_dx;
static jfieldID fid_dy;
static jfieldID fid_dwheel;
static jfieldID fid_buttons;

static unsigned char button_state[NUM_BUTTONS];
static int last_x;
static int last_y;
static int wheel_dz;

static pascal OSStatus doMouseDown(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
printf("Mouse down\n");
	lock();
	unlock();
	return eventNotHandledErr; // allow the event to propagate
}

static pascal OSStatus doMouseUp(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
printf("Mouse up\n");
	lock();
	unlock();
	return eventNotHandledErr; // allow the event to propagate
}

static pascal OSStatus doMouseWheel(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
printf("Mouse wheel\n");
	lock();
	unlock();
	return noErr;
}

bool registerMouseHandler(JNIEnv* env, WindowRef win_ref) {
	bool error = registerHandler(env, win_ref, doMouseDown, kEventClassMouse, kEventMouseDown);
	error = error || registerHandler(env, win_ref, doMouseUp, kEventClassMouse, kEventMouseUp);
	error = error || registerHandler(env, win_ref, doMouseWheel, kEventClassMouse, kEventMouseWheelMoved);
	return !error;
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
	fid_buttons = env->GetStaticFieldID(clazz, "buttons", "[Z");
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetNativeCursorCaps(JNIEnv *env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nSetNativeCursor(JNIEnv *env, jclass clazz, jlong cursor_handle) {
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetMinCursorSize(JNIEnv *env, jclass clazz) {
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Mouse_nGetMaxCursorSize(JNIEnv *env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nCreate(JNIEnv * env, jclass clazz) {
	last_x = 0;
	last_y = 0;
	wheel_dz = 0;
	CGAssociateMouseAndMouseCursorPosition(FALSE);
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nDestroy(JNIEnv * env, jclass clazz) {
	CGAssociateMouseAndMouseCursorPosition(TRUE);
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Mouse_nPoll(JNIEnv * env, jclass clazz) {
	lock();
	/*Point cursor_pos;
	GetMouse(&cursor_pos);
	int dx = cursor_pos.v - last_x;
	int dy = cursor_pos.h - last_y;
	last_x += dx;
	last_y += dy;*/
	CGMouseDelta dx, dy;
	CGGetLastMouseDelta(&dx, &dy);
	env->SetStaticIntField(clazz, fid_dx, (jint)dx);
	env->SetStaticIntField(clazz, fid_dy, (jint)dy);
	env->SetStaticIntField(clazz, fid_dwheel, (jint)wheel_dz);
	jbooleanArray buttons_array = (jbooleanArray)env->GetStaticObjectField(clazz, fid_buttons);
	env->SetBooleanArrayRegion(buttons_array, 0, NUM_BUTTONS, button_state);
	wheel_dz = 0;
	unlock();
/*
	if (dx != 0 || dy != 0)
		printf("dx %d dy %d, lx %d ly %d\n", dx, dy, last_x, last_y);*/
}
