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
#include "common_tools.h"
#include <CoreServices/CoreServices.h>

#define KEYBOARD_SIZE 256
#define TRANSLATION_BUFFER_SIZE 10

static unsigned char key_buf[KEYBOARD_SIZE];
static unsigned char key_map[KEYBOARD_SIZE];
static bool buffer_enabled = false;
static bool translation_enabled = false;
static event_queue_t event_queue;
static UInt32 deadKeyState;

static bool handleMappedKey(unsigned char mapped_code, unsigned char state) {
	unsigned char old_state = key_buf[mapped_code];
	if (old_state != state) {
		key_buf[mapped_code] = state;
		if (buffer_enabled) {
			putEventElement(&event_queue, mapped_code);
			putEventElement(&event_queue, state);
			return translation_enabled;
		}
	}
	return false;
}

static bool handleKey(UInt32 key_code, unsigned char state) {
	if (key_code >= KEYBOARD_SIZE) {
		printfDebug("Key code >= %d %x\n", KEYBOARD_SIZE, (unsigned int)key_code);
		return false;
	}
	unsigned char mapped_code = key_map[key_code];
	if (mapped_code == 0) {
		printfDebug("unknown key code: %x\n", (unsigned int)key_code);
		return false;
	}
	return handleMappedKey(mapped_code, state);
}

static unsigned char getSecondByte(UniChar ch) {
	return (unsigned char)(ch & 0xff);
}

static unsigned char getFirstByte(UniChar ch) {
	return (unsigned char)((ch & 0xff00) >> 16);
}

static bool writeUniChars(int num_chars, const UniChar *buffer) {
	if (num_chars == 0)
		return false;
	unsigned char b0 = getFirstByte(buffer[0]);
	unsigned char b1 = getSecondByte(buffer[0]);
	putEventElement(&event_queue, b0);
	putEventElement(&event_queue, b1);
	for (int i = 1; i < num_chars; i++) {
		putEventElement(&event_queue, 0);
		putEventElement(&event_queue, 0);
		b0 = getFirstByte(buffer[i]);
		b1 = getSecondByte(buffer[i]);
		putEventElement(&event_queue, b0);
		putEventElement(&event_queue, b1);
	}
	return true;
}

static bool writeAsciiChars(int num_chars, const unsigned char *buffer) {
	if (num_chars == 0)
		return false;
	unsigned char c = buffer[0];
	putEventElement(&event_queue, 0);
	putEventElement(&event_queue, c);
	for (int i = 1; i < num_chars; i++) {
		putEventElement(&event_queue, 0);
		putEventElement(&event_queue, 0);
		unsigned char c = buffer[i];
		putEventElement(&event_queue, 0);
		putEventElement(&event_queue, c);
	}
	return true;
}

static bool handleTranslation(EventRef event, bool state) {
	UniChar unicodeInputString[TRANSLATION_BUFFER_SIZE];

	KeyboardLayoutRef layout;
	OSStatus err = KLGetCurrentKeyboardLayout(&layout);
	if (err != noErr) {
		printfDebug("Could not get current keyboard layout\n");
		return false;
	}

	UInt32 keyboardType;
	UInt32 modifierKeyState;
	UInt32 virtualKeyCode;
	UniCharCount actualStringLength;
	OSStatus status;
	UCKeyboardLayout *uchrHandle;
	bool success = GetEventParameter(event, kEventParamKeyCode, typeUInt32, NULL, sizeof(virtualKeyCode), NULL, &virtualKeyCode) == noErr;
	success = success && GetEventParameter(event, kEventParamKeyboardType, typeUInt32, NULL, sizeof(keyboardType), NULL, &keyboardType) == noErr;
	success = success && GetEventParameter(event, kEventParamKeyModifiers, typeUInt32, NULL, sizeof(modifierKeyState), NULL, &modifierKeyState) == noErr;
	if (!success) {
		printfDebug("Could not get event parameters for character translation\n");
		return false;
	}
	err = KLGetKeyboardLayoutProperty(layout, kKLuchrData, (const void **)&uchrHandle);
	if (err == noErr && uchrHandle != NULL) {
		UInt16 action = state ? kUCKeyActionDown : kUCKeyActionUp;
		status = UCKeyTranslate(uchrHandle, virtualKeyCode, action,
					modifierKeyState, keyboardType, 0,
					&deadKeyState, TRANSLATION_BUFFER_SIZE,
					&actualStringLength, unicodeInputString);
		if (state)
			return writeUniChars(actualStringLength, unicodeInputString);
	} else {
		void * kchrHandle;
		err = KLGetKeyboardLayoutProperty(layout, kKLKCHRData, (const void **)&kchrHandle);
		if (err == noErr && kchrHandle != NULL) {
			UInt16 action = state ? 0x80 : 0x00;
			UInt16 key_code = (virtualKeyCode & 0x7f);
			UInt16 modifier_code = (modifierKeyState & 0xff00);
			UInt16 code = modifier_code | action | key_code;
			UInt32 character = KeyTranslate(kchrHandle, code, &deadKeyState);
			int count = 0;
			unsigned char ascii_buffer[2];
			unsigned char c1 = (unsigned char)(character & 0xff);
			if (c1 != 0)
				ascii_buffer[count++] = c1;
			unsigned char c2 = (unsigned char)((character & 0xff0000) >> 16);
			if (c2 != 0)
				ascii_buffer[count++] = c2;
			if (state)
				return writeAsciiChars(count, ascii_buffer);
		} else {
			printfDebug("Could not translate key\n");
			return false;
		}
	}
	return false;
}

static void doKeyDown(EventRef event) {
	UInt32 key_code;
	OSStatus err = GetEventParameter(event, kEventParamKeyCode, typeUInt32, NULL, sizeof(key_code), NULL, &key_code);
	if (err != noErr) {
		printfDebug("Could not get event key code\n");
		return;
	}
	if (handleKey(key_code, 1) && !handleTranslation(event, true)) {
		putEventElement(&event_queue, 0);
		putEventElement(&event_queue, 0);
	}
}

static void doKeyUp(EventRef event) {
	UInt32 key_code;
	OSStatus err = GetEventParameter(event, kEventParamKeyCode, typeUInt32, NULL, sizeof(key_code), NULL, &key_code);
	if (err != noErr) {
		printfDebug("Could not get event key code\n");
		return;
	}
	if (handleKey(key_code, 0) && !handleTranslation(event, false)) {
		putEventElement(&event_queue, 0);
		putEventElement(&event_queue, 0);
	}
	return;
}

static void handleModifier(UInt32 modifier_bit_mask, UInt32 modifier_bit, unsigned char key_code) {
	bool key_down = (modifier_bit_mask & modifier_bit) == modifier_bit;
	unsigned char key_state = key_down ? 1 : 0;
	if (handleMappedKey(key_code, key_state)) {
		putEventElement(&event_queue, 0);
		putEventElement(&event_queue, 0);
	}
}

static void doKeyModifier(EventRef event) {
	UInt32 modifier_bits;
	OSStatus err = GetEventParameter(event, kEventParamKeyModifiers, typeUInt32, NULL, sizeof(modifier_bits), NULL, &modifier_bits);
	if (err != noErr) {
		printfDebug("Could not get event key code\n");
		return;
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
	return;
}

void handleKeyboardEvent(EventRef event) {
	UInt32 event_kind = GetEventKind(event);
	switch (event_kind) {
		case kEventRawKeyDown:
			doKeyDown(event);
			break;
		case kEventRawKeyUp:
			doKeyUp(event);
			break;
		case kEventRawKeyModifiersChanged:
			doKeyModifier(event);
			break;
	}
}

static void setupMappings(void) {
	memset(key_map, 0, KEYBOARD_SIZE*sizeof(unsigned char));
	key_map[0x35] = org_lwjgl_input_Keyboard_KEY_ESCAPE;
	key_map[0x12] = org_lwjgl_input_Keyboard_KEY_1;
	key_map[0x13] = org_lwjgl_input_Keyboard_KEY_2;
	key_map[0x14] = org_lwjgl_input_Keyboard_KEY_3;
	key_map[0x15] = org_lwjgl_input_Keyboard_KEY_4;
	key_map[0x17] = org_lwjgl_input_Keyboard_KEY_5;
	key_map[0x16] = org_lwjgl_input_Keyboard_KEY_6;
	key_map[0x1a] = org_lwjgl_input_Keyboard_KEY_7;
	key_map[0x1c] = org_lwjgl_input_Keyboard_KEY_8;
	key_map[0x19] = org_lwjgl_input_Keyboard_KEY_9;
	key_map[0x1d] = org_lwjgl_input_Keyboard_KEY_0;
	key_map[0x1b] = org_lwjgl_input_Keyboard_KEY_MINUS;
	key_map[0x18] = org_lwjgl_input_Keyboard_KEY_EQUALS;
	key_map[0x33] = org_lwjgl_input_Keyboard_KEY_BACK;
	key_map[0x30] = org_lwjgl_input_Keyboard_KEY_TAB;
	key_map[0x0c] = org_lwjgl_input_Keyboard_KEY_Q;
	key_map[0x0d] = org_lwjgl_input_Keyboard_KEY_W;
	key_map[0x0e] = org_lwjgl_input_Keyboard_KEY_E;
	key_map[0x0f] = org_lwjgl_input_Keyboard_KEY_R;
	key_map[0x11] = org_lwjgl_input_Keyboard_KEY_T;
	key_map[0x10] = org_lwjgl_input_Keyboard_KEY_Y;
	key_map[0x20] = org_lwjgl_input_Keyboard_KEY_U;
	key_map[0x22] = org_lwjgl_input_Keyboard_KEY_I;
	key_map[0x1f] = org_lwjgl_input_Keyboard_KEY_O;
	key_map[0x23] = org_lwjgl_input_Keyboard_KEY_P;
	key_map[0x21] = org_lwjgl_input_Keyboard_KEY_LBRACKET;
	key_map[0x1e] = org_lwjgl_input_Keyboard_KEY_RBRACKET;
	key_map[0x24] = org_lwjgl_input_Keyboard_KEY_RETURN;
	key_map[0x00] = org_lwjgl_input_Keyboard_KEY_A;
	key_map[0x01] = org_lwjgl_input_Keyboard_KEY_S;
	key_map[0x02] = org_lwjgl_input_Keyboard_KEY_D;
	key_map[0x03] = org_lwjgl_input_Keyboard_KEY_F;
	key_map[0x05] = org_lwjgl_input_Keyboard_KEY_G;
	key_map[0x04] = org_lwjgl_input_Keyboard_KEY_H;
	key_map[0x26] = org_lwjgl_input_Keyboard_KEY_J;
	key_map[0x28] = org_lwjgl_input_Keyboard_KEY_K;
	key_map[0x25] = org_lwjgl_input_Keyboard_KEY_L;
	key_map[0x29] = org_lwjgl_input_Keyboard_KEY_SEMICOLON;
	key_map[0x27] = org_lwjgl_input_Keyboard_KEY_APOSTROPHE;
	key_map[0x2a] = org_lwjgl_input_Keyboard_KEY_GRAVE;
	key_map[0x32] = org_lwjgl_input_Keyboard_KEY_BACKSLASH;
	key_map[0x06] = org_lwjgl_input_Keyboard_KEY_Z;
	key_map[0x07] = org_lwjgl_input_Keyboard_KEY_X;
	key_map[0x08] = org_lwjgl_input_Keyboard_KEY_C;
	key_map[0x09] = org_lwjgl_input_Keyboard_KEY_V;
	key_map[0x0b] = org_lwjgl_input_Keyboard_KEY_B;
	key_map[0x2d] = org_lwjgl_input_Keyboard_KEY_N;
	key_map[0x2e] = org_lwjgl_input_Keyboard_KEY_M;
	key_map[0x2b] = org_lwjgl_input_Keyboard_KEY_COMMA;
	key_map[0x2f] = org_lwjgl_input_Keyboard_KEY_PERIOD;
	key_map[0x2c] = org_lwjgl_input_Keyboard_KEY_SLASH;
	key_map[0x43] = org_lwjgl_input_Keyboard_KEY_MULTIPLY;
	key_map[0x31] = org_lwjgl_input_Keyboard_KEY_SPACE;
	key_map[0x7a] = org_lwjgl_input_Keyboard_KEY_F1;
	key_map[0x78] = org_lwjgl_input_Keyboard_KEY_F2;
	key_map[0x63] = org_lwjgl_input_Keyboard_KEY_F3;
	key_map[0x76] = org_lwjgl_input_Keyboard_KEY_F4;
	key_map[0x60] = org_lwjgl_input_Keyboard_KEY_F5;
	key_map[0x61] = org_lwjgl_input_Keyboard_KEY_F6;
	key_map[0x62] = org_lwjgl_input_Keyboard_KEY_F7;
	key_map[0x64] = org_lwjgl_input_Keyboard_KEY_F8;
	key_map[0x65] = org_lwjgl_input_Keyboard_KEY_F9;
	key_map[0x6d] = org_lwjgl_input_Keyboard_KEY_F10;
	key_map[0x59] = org_lwjgl_input_Keyboard_KEY_NUMPAD7;
	key_map[0x5b] = org_lwjgl_input_Keyboard_KEY_NUMPAD8;
	key_map[0x5c] = org_lwjgl_input_Keyboard_KEY_NUMPAD9;
	key_map[0x4e] = org_lwjgl_input_Keyboard_KEY_SUBTRACT;
	key_map[0x56] = org_lwjgl_input_Keyboard_KEY_NUMPAD4;
	key_map[0x57] = org_lwjgl_input_Keyboard_KEY_NUMPAD5;
	key_map[0x58] = org_lwjgl_input_Keyboard_KEY_NUMPAD6;
	key_map[0x45] = org_lwjgl_input_Keyboard_KEY_ADD;
	key_map[0x53] = org_lwjgl_input_Keyboard_KEY_NUMPAD1;
	key_map[0x54] = org_lwjgl_input_Keyboard_KEY_NUMPAD2;
	key_map[0x55] = org_lwjgl_input_Keyboard_KEY_NUMPAD3;
	key_map[0x52] = org_lwjgl_input_Keyboard_KEY_NUMPAD0;
	key_map[0x41] = org_lwjgl_input_Keyboard_KEY_DECIMAL;
	key_map[0x67] = org_lwjgl_input_Keyboard_KEY_F11;
	key_map[0x6f] = org_lwjgl_input_Keyboard_KEY_F12;
	key_map[0x51] = org_lwjgl_input_Keyboard_KEY_NUMPADEQUALS;
	key_map[0x4c] = org_lwjgl_input_Keyboard_KEY_NUMPADENTER;
	key_map[0x4b] = org_lwjgl_input_Keyboard_KEY_DIVIDE;
	key_map[0x73] = org_lwjgl_input_Keyboard_KEY_HOME;
	key_map[0x7e] = org_lwjgl_input_Keyboard_KEY_UP;
	key_map[0x74] = org_lwjgl_input_Keyboard_KEY_PRIOR;
	key_map[0x7b] = org_lwjgl_input_Keyboard_KEY_LEFT;
	key_map[0x7c] = org_lwjgl_input_Keyboard_KEY_RIGHT;
	key_map[0x7d] = org_lwjgl_input_Keyboard_KEY_DOWN;
	key_map[0x79] = org_lwjgl_input_Keyboard_KEY_NEXT;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_initIDs(JNIEnv * env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nCreate(JNIEnv * env, jclass clazz) {
	buffer_enabled = false;
	translation_enabled = false;
	deadKeyState = 0;
	initEventQueue(&event_queue);
	memset(key_buf, 0, KEYBOARD_SIZE*sizeof(unsigned char));
	setupMappings();
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nDestroy(JNIEnv * env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nPoll(JNIEnv * env, jclass clazz, jobject buffer) {
	unsigned char *new_keyboard_buffer = (unsigned char *)env->GetDirectBufferAddress(buffer);
	memcpy(new_keyboard_buffer, key_buf, KEYBOARD_SIZE*sizeof(unsigned char));
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nRead(JNIEnv * env, jclass clazz, jobject buffer, jint buffer_position) {
	int event_size;
	if (translation_enabled)
		event_size = 4;
	else
		event_size = 2;
	unsigned char* buffer_ptr = (unsigned char *)env->GetDirectBufferAddress(buffer);
	int buffer_size = env->GetDirectBufferCapacity(buffer) - buffer_position;
	return copyEvents(&event_queue, buffer_ptr + buffer_position, buffer_size, event_size);
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nEnableTranslation(JNIEnv *env, jclass clazz) {
	translation_enabled = true;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nEnableBuffer(JNIEnv * env, jclass clazz) {
	buffer_enabled = true;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nisStateKeySet(JNIEnv *env, jclass clazz, jint key) {
	return org_lwjgl_input_Keyboard_STATE_UNKNOWN;
}
