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
 * Mac OS X keyboard handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include "Window.h"
#include "tools.h"
#include "org_lwjgl_input_Keyboard.h"

#define KEYBOARD_BUFFER_SIZE 50
#define KEYBOARD_SIZE 256
#define KEY_EVENT_BACKLOG 40

static unsigned char key_buf[KEYBOARD_SIZE];
static unsigned char key_map[KEYBOARD_SIZE];

static void handleKey(UInt32 key_code, unsigned char state) {
	if (key_code >= KEYBOARD_SIZE) {
#ifdef _DEBUG
		printf("Key code too large %x\n", (unsigned int)key_code);
#endif
		return;
	}
	lock();
	unsigned char mapped_code = key_map[key_code];
	unsigned char old_state = key_buf[mapped_code];
	if (old_state != state) {
if (state == 1)
	printf("key down, key %x\n", key_code);
else
	printf("key up, key %x\n", key_code);
		key_buf[mapped_code] = state;
	}
	unlock();
}

static pascal OSStatus doKeyDown(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
	UInt32 key_code;
	OSStatus err = GetEventParameter(event, kEventParamKeyCode, typeUInt32, NULL, sizeof(key_code), NULL, &key_code);
	if (err != noErr) {
#ifdef _DEBUG
		printf("Could not get event key code\n");
#endif
		return eventNotHandledErr;
	}
	handleKey(key_code, 1);
	return noErr;
}

static pascal OSStatus doKeyUp(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
	UInt32 key_code;
	OSStatus err = GetEventParameter(event, kEventParamKeyCode, typeUInt32, NULL, sizeof(key_code), NULL, &key_code);
	if (err != noErr) {
#ifdef _DEBUG
		printf("Could not get event key code\n");
#endif
		return eventNotHandledErr;
	}
	handleKey(key_code, 0);
	return noErr;
}

static void handleModifier(UInt32 modifier_bit_mask, UInt32 modifier_bit, unsigned char key_code) {
	bool key_down = (modifier_bit_mask & modifier_bit) == modifier_bit;
	unsigned char key_state = key_down ? 1 : 0;
	handleKey(key_code, key_state);
}

static pascal OSStatus doKeyModifier(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
	UInt32 modifier_bits;
	OSStatus err = GetEventParameter(event, kEventParamKeyModifiers, typeUInt32, NULL, sizeof(modifier_bits), NULL, &modifier_bits);
	if (err != noErr) {
#ifdef _DEBUG
		printf("Could not get event key code\n");
#endif
		return eventNotHandledErr;
	}
	handleModifier(modifier_bits, controlKey, 0x1d);
	handleModifier(modifier_bits, rightControlKey, 0x9d);
	handleModifier(modifier_bits, shiftKey, 0x2a);
	handleModifier(modifier_bits, rightShiftKey, 0x36);
	handleModifier(modifier_bits, optionKey, 0x38);
	handleModifier(modifier_bits, rightOptionKey, 0xb8);
	handleModifier(modifier_bits, cmdKey, 0xdb);
	handleModifier(modifier_bits, alphaLock, 0x3a);
	handleModifier(modifier_bits, kEventKeyModifierNumLockMask, 0x45);
	//handleModifier(modifier_bits, rightCmdKey, 0xdc);
	return noErr;
}

static bool registerHandler(JNIEnv* env, WindowRef win_ref, EventHandlerProcPtr func, UInt32 event_kind) {
	EventTypeSpec event_type;
	EventHandlerUPP handler_upp = NewEventHandlerUPP(func);
	event_type.eventClass = kEventClassKeyboard;
	event_type.eventKind  = event_kind;
	OSStatus err = InstallWindowEventHandler(win_ref, handler_upp, 1, &event_type, NULL, NULL);
	DisposeEventHandlerUPP(handler_upp);
	if (noErr != err) {
		throwException(env, "Could not register window event handler");
		return true;
	}
	return false;
}

bool registerKeyboardHandler(JNIEnv* env, WindowRef win_ref) {
	bool error = registerHandler(env, win_ref, doKeyUp, kEventRawKeyUp);
	error = error || registerHandler(env, win_ref, doKeyDown, kEventRawKeyDown);
	error = error || registerHandler(env, win_ref, doKeyModifier, kEventRawKeyModifiersChanged);
	return !error;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_initIDs(JNIEnv * env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nCreate(JNIEnv * env, jclass clazz) {
	memset(key_buf, 0, KEYBOARD_SIZE*sizeof(unsigned char));
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nDestroy(JNIEnv * env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nPoll(JNIEnv * env, jclass clazz, jobject buffer) {
	unsigned char *new_keyboard_buffer = (unsigned char *)env->GetDirectBufferAddress(buffer);
	lock();
	memcpy(new_keyboard_buffer, key_buf, KEYBOARD_SIZE*sizeof(unsigned char));
	unlock();
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nRead(JNIEnv * env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nEnableTranslation(JNIEnv *env, jclass clazz) {
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nEnableBuffer(JNIEnv * env, jclass clazz) {
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nisStateKeySet(JNIEnv *env, jclass clazz, jint key) {
	return org_lwjgl_input_Keyboard_STATE_UNKNOWN;
}
