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
 * Linux keyboard handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <string.h>
#include <assert.h>
#include "Window.h"
#include "common_tools.h"
#include "org_lwjgl_input_Keyboard.h"

#define KEYBOARD_BUFFER_SIZE 50
#define KEYBOARD_SIZE 256
#define KEY_EVENT_BACKLOG 40

static unsigned char key_buf[KEYBOARD_SIZE];
static unsigned char key_map[KEYBOARD_SIZE];

static event_queue_t event_queue;

static bool keyboard_grabbed;
static bool buffer_enabled;
static bool translation_enabled;
static bool created = false;
static bool should_grab = false;

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_initIDs(JNIEnv * env, jclass clazz) {
}

static void setRepeatMode(int mode) {
	XKeyboardControl repeat_mode;
	repeat_mode.auto_repeat_mode = mode;
	XChangeKeyboardControl(getCurrentDisplay(), KBAutoRepeatMode, &repeat_mode);
}

static void grabKeyboard(void) {
	if (isFullscreen() || !isNativeCursor()) {
		if (!keyboard_grabbed) {
			int result = XGrabKeyboard(getCurrentDisplay(), getCurrentWindow(), False, GrabModeAsync, GrabModeAsync, CurrentTime);
			if (result == GrabSuccess) {
				keyboard_grabbed = true;
				setRepeatMode(AutoRepeatModeOff);
				XFlush(getCurrentDisplay());
			}
		}
	} else
		setRepeatMode(AutoRepeatModeOff);
}

static void ungrabKeyboard(void) {
	if (keyboard_grabbed) {
		keyboard_grabbed = false;
		XUngrabKeyboard(getCurrentDisplay(), CurrentTime);
		XFlush(getCurrentDisplay());
	}
	setRepeatMode(AutoRepeatModeDefault);
}

static void updateGrab(void) {
	if (!created)
		return;
	if (should_grab) {
		grabKeyboard();
	} else {
		ungrabKeyboard();
	}
}

void acquireKeyboard(void) {
	should_grab = true;
	updateGrab();
}

void releaseKeyboard(void) {
	should_grab = false;
	updateGrab();
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nCreate
 * Signature: ()Z
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nCreate
  (JNIEnv * env, jclass clazz)
{
	for (int i =  0; i < KEYBOARD_SIZE; i++)
		key_map[i] = i;
	key_map[0x6b] = 0xdb; // Left doze key
	key_map[0x6c] = 0xdc; // Right doze key
	key_map[0x6d] = 0xdd; // Apps key
	key_map[0x5a] = 0xc8; // Up arrow
	key_map[0x5c] = 0xcb; // Left arrow
	key_map[0x5e] = 0xcd; // Right arrow
	key_map[0x60] = 0xd0; // Down arrow
	key_map[0x59] = 0xc7; // Home
	key_map[0x62] = 0xd2; // Insert
	key_map[0x63] = 0xd3; // Delete
	key_map[0x5f] = 0xcf; // End
	key_map[0x5b] = 0xc9; // Page up
	key_map[0x61] = 0xd1; // Page down
	key_map[0x67] = 0xb7; // SysRQ
	key_map[0x66] = 0xc5; // Pause
	key_map[0x64] = 0x9c; // Numpad enter
	key_map[0x65] = 0x9d; // Right control
	key_map[0x68] = 0xb5; // Numpad divide
	
	memset(key_buf, 0, KEYBOARD_SIZE*sizeof(unsigned char));
	created = true;
	keyboard_grabbed = false;
	translation_enabled = false;
	buffer_enabled = false;
	should_grab = true;
	initEventQueue(&event_queue);
	updateGrab();
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nDestroy
  (JNIEnv * env, jclass clazz)
{
	ungrabKeyboard();
	created = false;
}

static unsigned char getKeycode(XKeyEvent *event) {
	unsigned char keycode = (unsigned char)((event->keycode - 8) & 0xff);
	keycode = key_map[keycode];
	return keycode;
}

static int translateEvent(XKeyEvent *event) {
	static char temp_translation_buffer[KEYBOARD_BUFFER_SIZE];
	static XComposeStatus status;
        int num_chars, i;

       	if (event->type == KeyRelease) {
		putEventElement(&event_queue, 0);
		putEventElement(&event_queue, 0);
		return 0;
	}
	num_chars = XLookupString(event, temp_translation_buffer, KEYBOARD_BUFFER_SIZE, NULL, &status);
	if (num_chars > 0) {
		num_chars--;
		/* Assume little endian byte order */
		putEventElement(&event_queue, temp_translation_buffer[0]);
		putEventElement(&event_queue, 0);
		for (i = 0; i < num_chars; i++) {
			putEventElement(&event_queue, 0);
			putEventElement(&event_queue, 0);
			putEventElement(&event_queue, temp_translation_buffer[i + 1]);
			putEventElement(&event_queue, 0);
		}
	} else {
		putEventElement(&event_queue, 0);
		putEventElement(&event_queue, 0);
	}
	return num_chars;
}

static unsigned char eventState(XKeyEvent *event) {
	if (event->type == KeyPress) {
		return 1;
	} else if (event->type == KeyRelease) {
		return 0;
	} else
		assert(0);
}

static void bufferEvent(XKeyEvent *key_event) {
	unsigned char keycode = getKeycode(key_event);
	unsigned char state = eventState(key_event);
	//printf("Reading a key %d %d count %d\n", (int)keycode, (int)state, num_events);
	putEventElement(&event_queue, keycode);
	putEventElement(&event_queue, state);
	if (translation_enabled)
		translateEvent(key_event);
}

void handleKeyEvent(XKeyEvent *event) {
	unsigned char keycode = getKeycode(event);
	unsigned char state = eventState(event);
	key_buf[keycode] = state;
	if (key_buf[org_lwjgl_input_Keyboard_KEY_LMENU] == 1 ||
			key_buf[org_lwjgl_input_Keyboard_KEY_RMENU] == 1) {
		if (key_buf[org_lwjgl_input_Keyboard_KEY_TAB] == 1) {
			if (releaseInput()) {
				key_buf[org_lwjgl_input_Keyboard_KEY_RMENU] = 0;
				key_buf[org_lwjgl_input_Keyboard_KEY_LMENU] = 0;
				key_buf[org_lwjgl_input_Keyboard_KEY_TAB] = 0;
				return;
			}
		}
	}
	if (buffer_enabled)
		bufferEvent(event);
}

JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nPoll(JNIEnv * env, jclass clazz, jobject buffer) {
	unsigned char *new_keyboard_buffer = (unsigned char *)env->GetDirectBufferAddress(buffer);
	memcpy(new_keyboard_buffer, key_buf, KEYBOARD_SIZE*sizeof(unsigned char));
}

JNIEXPORT int JNICALL Java_org_lwjgl_input_Keyboard_nRead(JNIEnv * env, jclass clazz, jobject buffer, jint buffer_position) {
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
