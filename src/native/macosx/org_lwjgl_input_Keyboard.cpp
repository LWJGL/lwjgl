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

#include "org_lwjgl_input_Keyboard.h"
#include "Window.h"
#include "tools.h"
#include "common_tools.h"
#include "hid.h"

#define KEYBOARD_SIZE 256
#define UNICODE_BUFFER_SIZE 10

static unsigned char key_buf[KEYBOARD_SIZE];
static bool buffer_enabled = false;
static bool translation_enabled = false;
static event_queue_t event_queue;
static hid_device_t hid_dev;

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

/*
static bool handleKey(UInt32 key_code, unsigned char state) {
	if (key_code >= KEYBOARD_SIZE) {
#ifdef _DEBUG
		printf("Key code >= %d %x\n", KEYBOARD_SIZE, (unsigned int)key_code);
#endif
		return false;
	}
	unsigned char mapped_code = key_map[key_code];
	if (mapped_code == 0) {
#ifdef _DEBUG
		printf("unknown key code: %x\n", (unsigned int)key_code);
#endif
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

static bool writeChars(int num_chars, UniChar *buffer) {
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

static bool handleUnicode(EventRef event) {
	UniChar unicode_buffer[UNICODE_BUFFER_SIZE];
	UInt32 data_size;
	int num_chars;
	OSStatus err = GetEventParameter(event, kEventParamKeyUnicodes, typeUnicodeText, NULL, 0, &data_size, NULL);
	if (err != noErr) {
#ifdef _DEBUG
		printf("Could not get unicode char count\n");
#endif
		return false;
	}
	num_chars = data_size/sizeof(UniChar);
	if (num_chars >= UNICODE_BUFFER_SIZE) {
#ifdef _DEBUG
		printf("Unicode chars could not fit in buffer\n");
#endif
		return false;
	}
	err = GetEventParameter(event, kEventParamKeyUnicodes, typeUnicodeText, NULL, data_size, NULL, unicode_buffer);
	if (err != noErr) {
#ifdef _DEBUG
		printf("Could not get unicode chars\n");
#endif
		return false;
	}
	return writeChars(num_chars, unicode_buffer);
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
	if (handleKey(key_code, 1)) {
		if (translation_enabled) {
			if (!handleUnicode(event)) {
				putEventElement(&event_queue, 0);
				putEventElement(&event_queue, 0);
			}
		} else {
			putEventElement(&event_queue, 0);
			putEventElement(&event_queue, 0);
		}
	}
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
	if (handleKey(key_code, 0)) {
		putEventElement(&event_queue, 0);
		putEventElement(&event_queue, 0);
	}
	return noErr;
}

static void handleModifier(UInt32 modifier_bit_mask, UInt32 modifier_bit, unsigned char key_code) {
	bool key_down = (modifier_bit_mask & modifier_bit) == modifier_bit;
	unsigned char key_state = key_down ? 1 : 0;
	if (handleMappedKey(key_code, key_state)) {
		putEventElement(&event_queue, 0);
		putEventElement(&event_queue, 0);
	}
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
*/

static void initCookie(hid_cookie_t *hid_cookies, int index, long usage) {
	hid_cookies[index].usage_page = kHIDPage_KeyboardOrKeypad;
	hid_cookies[index].usage = usage;
}

static void initCookies(hid_cookie_t *hid_cookies) {
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_ESCAPE, kHIDUsage_KeyboardEscape);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_1, kHIDUsage_Keyboard1);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_2, kHIDUsage_Keyboard2);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_3, kHIDUsage_Keyboard3);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_4, kHIDUsage_Keyboard4);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_5, kHIDUsage_Keyboard5);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_6, kHIDUsage_Keyboard6);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_7, kHIDUsage_Keyboard7);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_8, kHIDUsage_Keyboard8);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_9, kHIDUsage_Keyboard9);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_0, kHIDUsage_Keyboard0);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_MINUS, kHIDUsage_KeyboardHyphen);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_EQUALS, kHIDUsage_KeyboardEqualSign);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_BACK, kHIDUsage_KeyboardDeleteOrBackspace);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_TAB, kHIDUsage_KeyboardTab);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_Q, kHIDUsage_KeyboardQ);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_W, kHIDUsage_KeyboardW);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_E, kHIDUsage_KeyboardE);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_R, kHIDUsage_KeyboardR);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_T, kHIDUsage_KeyboardT);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_Y, kHIDUsage_KeyboardY);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_U, kHIDUsage_KeyboardU);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_I, kHIDUsage_KeyboardI);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_O, kHIDUsage_KeyboardO);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_P, kHIDUsage_KeyboardP);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_LBRACKET, kHIDUsage_KeyboardOpenBracket);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_RBRACKET, kHIDUsage_KeyboardCloseBracket);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_RETURN, kHIDUsage_KeyboardReturnOrEnter);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_LCONTROL, kHIDUsage_KeyboardLeftControl);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_A, kHIDUsage_KeyboardA);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_S, kHIDUsage_KeyboardS);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_D, kHIDUsage_KeyboardD);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F, kHIDUsage_KeyboardF);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_G, kHIDUsage_KeyboardG);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_H, kHIDUsage_KeyboardH);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_J, kHIDUsage_KeyboardJ);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_K, kHIDUsage_KeyboardK);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_L, kHIDUsage_KeyboardL);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_SEMICOLON, kHIDUsage_KeyboardSemicolon);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_APOSTROPHE, kHIDUsage_KeyboardQuote);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_GRAVE, kHIDUsage_KeyboardGraveAccentAndTilde);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_LSHIFT, kHIDUsage_KeyboardLeftShift);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_BACKSLASH, kHIDUsage_KeyboardBackslash);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_Z, kHIDUsage_KeyboardZ);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_X, kHIDUsage_KeyboardX);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_C, kHIDUsage_KeyboardC);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_V, kHIDUsage_KeyboardV);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_B, kHIDUsage_KeyboardB);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_N, kHIDUsage_KeyboardN);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_M, kHIDUsage_KeyboardM);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_COMMA, kHIDUsage_KeyboardComma);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_PERIOD, kHIDUsage_KeyboardPeriod);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_SLASH, kHIDUsage_KeyboardSlash);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_RSHIFT, kHIDUsage_KeyboardRightShift);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_MULTIPLY, kHIDUsage_KeypadAsterisk);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_LMENU, kHIDUsage_KeyboardLeftGUI);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_SPACE, kHIDUsage_KeyboardSpacebar);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_CAPITAL, kHIDUsage_KeyboardCapsLock);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F1, kHIDUsage_KeyboardF1);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F2, kHIDUsage_KeyboardF2);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F3, kHIDUsage_KeyboardF3);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F4, kHIDUsage_KeyboardF4);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F5, kHIDUsage_KeyboardF5);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F6, kHIDUsage_KeyboardF6);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F7, kHIDUsage_KeyboardF7);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F8, kHIDUsage_KeyboardF8);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F9, kHIDUsage_KeyboardF9);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F10, kHIDUsage_KeyboardF10);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NUMLOCK, kHIDUsage_KeypadNumLock);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_SCROLL, kHIDUsage_KeyboardScrollLock);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NUMPAD7, kHIDUsage_Keypad7);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NUMPAD8, kHIDUsage_Keypad8);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NUMPAD9, kHIDUsage_Keypad9);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_SUBTRACT, kHIDUsage_KeypadHyphen);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NUMPAD4, kHIDUsage_Keypad4);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NUMPAD5, kHIDUsage_Keypad5);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NUMPAD6, kHIDUsage_Keypad6);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_ADD, kHIDUsage_KeypadPlus);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NUMPAD1, kHIDUsage_Keypad1);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NUMPAD2, kHIDUsage_Keypad2);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NUMPAD3, kHIDUsage_Keypad3);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NUMPAD0, kHIDUsage_Keypad0);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_DECIMAL, kHIDUsage_KeypadPeriod);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F11, kHIDUsage_KeyboardF11);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F12, kHIDUsage_KeyboardF12);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F13, kHIDUsage_KeyboardF13);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F14, kHIDUsage_KeyboardF14);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_F15, kHIDUsage_KeyboardF15);
/*	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_KANA, kHIDUsage_KeyboardKANA);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_CONVERT, kHIDUsage_KeyboardCONVERT);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NOCONVERT, kHIDUsage_KeyboardNOCONVERT);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_YEN, kHIDUsage_KeyboardYEN);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NUMPADEQUALS, kHIDUsage_KeyboardNUMPADEQUALS);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_CIRCUMFLEX, kHIDUsage_KeyboardCIRCUMFLEX);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_AT, kHIDUsage_KeyboardAT);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_COLON, kHIDUsage_KeyboardCOLON);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_UNDERLINE, kHIDUsage_KeyboardUNDERLINE);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_KANJI, kHIDUsage_KeyboardKANJI);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_STOP, kHIDUsage_KeyboardSTOP);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_AX, kHIDUsage_KeyboardAX);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_UNLABELED, kHIDUsage_KeyboardUNLABELED);*/
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NUMPADENTER, kHIDUsage_KeypadEnter);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_RCONTROL, kHIDUsage_KeyboardRightControl);
//	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NUMPADCOMMA, kHIDUsage_KeyboardNUMPADCOMMA);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_DIVIDE, kHIDUsage_KeypadSlash);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_SYSRQ, kHIDUsage_KeyboardSysReqOrAttention);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_RMENU, kHIDUsage_KeyboardRightGUI);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_PAUSE, kHIDUsage_KeyboardPause);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_HOME, kHIDUsage_KeyboardHome);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_UP, kHIDUsage_KeyboardUpArrow);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_PRIOR, kHIDUsage_KeyboardPageUp);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_LEFT, kHIDUsage_KeyboardLeftArrow);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_RIGHT, kHIDUsage_KeyboardRightArrow);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_END, kHIDUsage_KeyboardEnd);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_DOWN, kHIDUsage_KeyboardDownArrow);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_NEXT, kHIDUsage_KeyboardPageDown);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_INSERT, kHIDUsage_KeyboardInsert);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_DELETE, kHIDUsage_KeyboardDeleteForward);
/*	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_LWIN, kHIDUsage_KeyboardLWIN);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_RWIN, kHIDUsage_KeyboardRWIN);*/
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_APPS, kHIDUsage_KeyboardApplication);
	initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_POWER, kHIDUsage_KeyboardPower);
	//initCookie(hid_cookies, org_lwjgl_input_Keyboard_KEY_SLEEP, kHIDUsage_KeyboardSleep);
}

static void pollKeyboardDevice(void) {
	hid_event_t event;
	while (nextDeviceEvent(&hid_dev, &event)) {
		if (event.cookie_index >= KEYBOARD_SIZE) {
#ifdef _DEBUG
			printf("Uknown key code\n");
#endif
			return;
		}
		unsigned char key_code = (unsigned char)event.cookie_index;
		unsigned char state = event.value != 0 ? 1 : 0;
		if (handleMappedKey(key_code, state)) {
			putEventElement(&event_queue, 0);
			putEventElement(&event_queue, 0);
		}
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_initIDs(JNIEnv * env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nCreate(JNIEnv * env, jclass clazz) {
	buffer_enabled = false;
	translation_enabled = false;
	initEventQueue(&event_queue);
	memset(key_buf, 0, KEYBOARD_SIZE*sizeof(unsigned char));
	hid_cookie_t hid_cookies[KEYBOARD_SIZE];
	for (int i = 0; i < KEYBOARD_SIZE; i++) {
		hid_cookies[i].usage_page = kHIDPage_Undefined;
		hid_cookies[i].usage = kHIDUsage_Undefined;
	}
	initCookies(hid_cookies);
	if (!findDevice(&hid_dev, kHIDPage_GenericDesktop, kHIDUsage_GD_Keyboard, KEYBOARD_SIZE, hid_cookies, EVENT_BUFFER_SIZE)) {
		throwException(env, "Could not find a keyboard device");
		return;
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nDestroy(JNIEnv * env, jclass clazz) {
	shutdownDevice(&hid_dev);
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nPoll(JNIEnv * env, jclass clazz, jobject buffer) {
	pollKeyboardDevice();
	unsigned char *new_keyboard_buffer = (unsigned char *)env->GetDirectBufferAddress(buffer);
	memcpy(new_keyboard_buffer, key_buf, KEYBOARD_SIZE*sizeof(unsigned char));
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nRead(JNIEnv * env, jclass clazz) {
	int num_events = 0;
	int event_size;
	if (translation_enabled)
		event_size = 4;
	else
		event_size = 2;
	num_events = copyEvents(&event_queue, event_size);
	return num_events;
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nEnableTranslation(JNIEnv *env, jclass clazz) {
	translation_enabled = true;
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_input_Keyboard_nEnableBuffer(JNIEnv * env, jclass clazz) {
	jobject new_buffer = env->NewDirectByteBuffer(getOutputList(&event_queue), getEventBufferSize(&event_queue));
	buffer_enabled = true;
	return new_buffer;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nisStateKeySet(JNIEnv *env, jclass clazz, jint key) {
	return org_lwjgl_input_Keyboard_STATE_UNKNOWN;
}
