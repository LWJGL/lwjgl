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
#include <string.h>
#include <assert.h>
#include "org_lwjgl_input_Keyboard.h"

#define KEYBOARD_BUFFER_SIZE 50
#define KEYBOARD_SIZE 256
#define KEY_EVENT_BACKLOG 20

unsigned char readBuffer[KEYBOARD_BUFFER_SIZE * 2];
jfieldID fid_readBuffer;
jfieldID fid_readBufferAddress;
unsigned char key_buf[KEYBOARD_SIZE];
unsigned char key_map[KEYBOARD_SIZE];

typedef struct {
	unsigned char keycode;
	unsigned char state;
} input_event;

input_event saved_key_events[KEY_EVENT_BACKLOG];
int list_start = 0;
int list_end = 0;

bool keyboard_grabbed;

extern Display *disp;
extern Window win;
extern int current_fullscreen;

extern int isFocused(void);

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_initIDs
  (JNIEnv * env, jclass clazz)
{
	// Get a global class instance, just to be sure
	static jobject globalClassLock = NULL;

	if (globalClassLock == NULL) {
		globalClassLock = env->NewGlobalRef(clazz);
	}

	fid_readBuffer = env->GetStaticFieldID(clazz, "readBuffer", "Ljava/nio/ByteBuffer;");
	fid_readBufferAddress = env->GetStaticFieldID(clazz, "readBufferAddress", "I");
}

int grabKeyboard(void) {
	int result = XGrabKeyboard(disp, win, False, GrabModeAsync, GrabModeAsync, CurrentTime);
	if (result == GrabSuccess)
		keyboard_grabbed = true;
	return result;
}

void ungrabKeyboard(void) {
	keyboard_grabbed = false;
	XUngrabKeyboard(disp, CurrentTime);
}

int updateKeyboardGrab(void) {
	if (current_fullscreen) {
		if (!keyboard_grabbed)
			return grabKeyboard();
	} else {
		if (isFocused()) {
			if (!keyboard_grabbed)
				return grabKeyboard();
		} else {
			if (keyboard_grabbed)
				ungrabKeyboard();
		}
	}
	return GrabSuccess;
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nCreate
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_input_Keyboard_nCreate
  (JNIEnv * env, jclass clazz)
{
	keyboard_grabbed = 0;
	if (updateKeyboardGrab() != GrabSuccess) {
#ifdef _DEBUG
		printf("Could not grab keyboard\n");
#endif
		return JNI_FALSE;
	}
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
	key_map[0x68] = 0xb5; // Numpad divide
	
	memset(key_buf, 0, KEYBOARD_SIZE*sizeof(unsigned char));
	return JNI_TRUE;
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nDestroy
  (JNIEnv * env, jclass clazz)
{
	if (keyboard_grabbed)
		ungrabKeyboard();
}

input_event *nextEventElement(void) {
	if (list_start == list_end)
		return NULL;
	input_event *result = &(saved_key_events[list_start]);
	list_start = (list_start + 1)%KEY_EVENT_BACKLOG;
	return result;
}

void putEventElement(unsigned char keycode, unsigned char state) {
	int next_index = (list_end + 1)%KEY_EVENT_BACKLOG;
	if (next_index == list_start)
		return;
	saved_key_events[list_end].keycode = keycode;
	saved_key_events[list_end].state = state;
	list_end = next_index;
}

unsigned char getKeycode(XEvent *event) {
	unsigned char keycode = (unsigned char)((event->xkey.keycode - 8) & 0xff);
	keycode = key_map[keycode];
	return keycode;
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nPoll
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nPoll
  (JNIEnv * env, jclass clazz, jint buf)
{
	XEvent event;
	int state;
	
	updateKeyboardGrab();

	while (XCheckMaskEvent(disp, KeyPressMask | KeyReleaseMask, &event)) {
		unsigned char keycode = getKeycode(&event);
		if (event.type == KeyPress) {
			state = 1;
		} else if (event.type == KeyRelease) {
			state = 0;
		} else
			assert(0);
		key_buf[keycode] = state;
		putEventElement(keycode, state);
	}
	memcpy((unsigned char*)buf, key_buf, KEYBOARD_SIZE*sizeof(unsigned char));
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nRead
 * Signature: (I)V
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nRead
  (JNIEnv * env, jclass clazz, jint keys)
{
	XEvent event;
	int count = 0;
	int buf_count = 0;
	int state;
	input_event *input_ev;
	unsigned char *result_buf = (unsigned char *)keys;

	updateKeyboardGrab();

	while ((input_ev = nextEventElement()) != NULL) {
		count++;
//		printf("Reading a key %d %d count %d\n", (int)input_ev->keycode, (int)input_ev->state, count);
		result_buf[buf_count++] = input_ev->keycode;
		result_buf[buf_count++] = input_ev->state;
		if (buf_count >= KEYBOARD_BUFFER_SIZE * 2)
			return count;
	}

	while (XCheckMaskEvent(disp, KeyPressMask | KeyReleaseMask, &event)) {
		count++;
		unsigned char keycode = getKeycode(&event);
		if (event.type == KeyPress) {
			state = 1;
		} else if (event.type == KeyRelease) {
			state = 0;
		} else
			assert(0);
		key_buf[keycode] = state;
		result_buf[buf_count++] = keycode;
		result_buf[buf_count++] = state;
		if (buf_count >= KEYBOARD_BUFFER_SIZE * 2)
			return count;
	}
	return count;
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nEnableBuffer
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nEnableBuffer
  (JNIEnv * env, jclass clazz)
{
	jobject newBuffer = env->NewDirectByteBuffer(&readBuffer, KEYBOARD_BUFFER_SIZE * 2);
	env->SetStaticObjectField(clazz, fid_readBuffer, newBuffer);
	env->SetStaticIntField(clazz, fid_readBufferAddress, (jint) (&readBuffer));
	return KEYBOARD_BUFFER_SIZE;
}
