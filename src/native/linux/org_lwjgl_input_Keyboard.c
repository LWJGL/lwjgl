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
#include <iconv.h>
#include <errno.h>
#include "Window.h"
#include "common_tools.h"
#include "org_lwjgl_input_Keyboard.h"
#include "org_lwjgl_opengl_LinuxDisplay.h"

#define KEYBOARD_BUFFER_SIZE 50
#define KEYBOARD_SIZE 256
#define KEY_EVENT_BACKLOG 40

static unsigned char key_buf[KEYBOARD_SIZE];
static unsigned char key_map[KEYBOARD_SIZE];

static event_queue_t event_queue;

static bool keyboard_grabbed;
static bool created = false;

// X input manager values
static iconv_t iconv_descriptor;
static XIM xim = NULL;
static XIC xic = NULL;

static void grabKeyboard(void) {
	if (!keyboard_grabbed) {
		int result = XGrabKeyboard(getDisplay(), getCurrentWindow(), False, GrabModeAsync, GrabModeAsync, CurrentTime);
		if (result == GrabSuccess)
			keyboard_grabbed = true;
	}
}

static void ungrabKeyboard(void) {
	if (keyboard_grabbed) {
		keyboard_grabbed = false;
		XUngrabKeyboard(getDisplay(), CurrentTime);
	}
}

void updateKeyboardGrab(void) {
	if (!created)
		return;
	if (isLegacyFullscreen()/* || shouldGrab()*/) {
		grabKeyboard();
	} else {
		ungrabKeyboard();
	}
}

static void closeUnicodeStructs() {
	if (iconv_descriptor != (iconv_t)-1) {
		iconv_close(iconv_descriptor);
		iconv_descriptor = (iconv_t)-1;
	}
	if (xic != NULL) {
		XDestroyIC(xic);
		xic = NULL;
	}
	if (xim != NULL) {
		XCloseIM(xim);
		xim = NULL;
	}
}

static void setupIMEventMask() {
	long im_event_mask;
	XWindowAttributes win_attributes;
	
	XGetWindowAttributes(getDisplay(), getCurrentWindow(), &win_attributes);
	XGetICValues(xic, XNFilterEvents, &im_event_mask, NULL);
	XSelectInput(getDisplay(), getCurrentWindow(), win_attributes.your_event_mask | im_event_mask);
	XSetICFocus(xic);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nCreateKeyboard
  (JNIEnv * env, jclass clazz)
{
	int i;
	for (i =  0; i < KEYBOARD_SIZE; i++)
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
	initEventQueue(&event_queue, 3);
	updateKeyboardGrab();
	
	// Allocate unicode structures
	iconv_descriptor = iconv_open("UCS-2", "UTF-8");
	if (iconv_descriptor != (iconv_t)-1) {
		xim = XOpenIM(getDisplay(), NULL, NULL, NULL);
		if (xim != NULL) {
			xic = XCreateIC(xim,  XNClientWindow, getCurrentWindow(), XNFocusWindow, getCurrentWindow(), XNInputStyle, XIMPreeditNothing | XIMStatusNothing, NULL);
			if (xic != NULL) {
				setupIMEventMask();
			} else {
				closeUnicodeStructs();
			}
		} else
			closeUnicodeStructs();
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nDestroyKeyboard
  (JNIEnv * env, jclass clazz)
{
	closeUnicodeStructs();
	ungrabKeyboard();
	created = false;
}

static unsigned char getKeycode(XKeyEvent *event) {
	unsigned char keycode = (unsigned char)((event->keycode - 8) & 0xff);
	keycode = key_map[keycode];
	return keycode;
}

static void putKeyboardEvent(jint keycode, jint state, jint ch) {
	int event_list[] = {keycode, state, ch};
	putEvent(&event_queue, event_list);
}

static int lookupStringISO88591(XKeyEvent *event, jint *translation_buffer) {
	static XComposeStatus status;
	char char_translation_buffer[KEYBOARD_BUFFER_SIZE];
	int i;
	
	int num_chars = XLookupString(event, char_translation_buffer, KEYBOARD_BUFFER_SIZE*sizeof(char), NULL, &status);
	for (i = 0; i < num_chars; i++)
		translation_buffer[i] = ((jint)char_translation_buffer[i]) & 0xff;
	return num_chars;
}

static int lookupStringUnicode(XKeyEvent *event, jint *translation_buffer) {
	char utf8_translation_buffer[KEYBOARD_BUFFER_SIZE];
	
	jchar ucs2_translation_buffer[KEYBOARD_BUFFER_SIZE];
	char *utf8_buf = utf8_translation_buffer;
	jchar *ucs2_buf = ucs2_translation_buffer;
	size_t ucs2_bytes = KEYBOARD_BUFFER_SIZE*sizeof(jchar); 
	Status status;
	int i;
	
	size_t utf8_bytes = Xutf8LookupString(xic, event, utf8_translation_buffer, KEYBOARD_BUFFER_SIZE*sizeof(char), NULL, &status);
	if (status != XLookupChars && status != XLookupBoth)
		return 0;
	// reset converter
	iconv(iconv_descriptor, NULL, NULL, NULL, NULL);
	// convert from UTF-8 to UCS-2
	size_t iconv_result = iconv(iconv_descriptor, &utf8_buf, &utf8_bytes, (void *)&ucs2_buf, &ucs2_bytes);
	// compute number of characters converted
	int num_chars_converted = KEYBOARD_BUFFER_SIZE - ucs2_bytes/sizeof(jchar);
	if (iconv_result == (size_t)-1) {
		errno = 0; // ignore conversion error and return no chars converted
		return 0;
	}
	for (i = 0; i < num_chars_converted; i++) {
		translation_buffer[i] = ((jint)ucs2_translation_buffer[i]) & 0xffff;
	}
	return num_chars_converted;
}

static int lookupString(XKeyEvent *event, jint *translation_buffer) {
	if (xic != NULL) {
		return lookupStringUnicode(event, translation_buffer);
	} else
		return lookupStringISO88591(event, translation_buffer);
}
				
static void translateEvent(XKeyEvent *event, jint keycode, jint state) {
	jint temp_translation_buffer[KEYBOARD_BUFFER_SIZE];
	int num_chars, i;
	jint ch;

	if (event->type == KeyRelease) {
		putKeyboardEvent(keycode, state, 0);
		return;
	}
	num_chars = lookupString(event, temp_translation_buffer);
	if (num_chars > 0) {
		ch = temp_translation_buffer[0];
		putKeyboardEvent(keycode, state, ch);
		for (i = 1; i < num_chars; i++) {
			ch = temp_translation_buffer[i];
			putKeyboardEvent(0, 0, ch);
		}
	} else {
		putKeyboardEvent(keycode, state, 0);
	}
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
	translateEvent(key_event, keycode, state);
}

void handleKeyEvent(XKeyEvent *event) {
	unsigned char keycode = getKeycode(event);
	unsigned char state = eventState(event);
	key_buf[keycode] = state;
	bufferEvent(event);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nPollKeyboard(JNIEnv * env, jclass clazz, jobject buffer) {
	unsigned char *new_keyboard_buffer = (unsigned char *)(*env)->GetDirectBufferAddress(env, buffer);
	handleMessages(env);
	memcpy(new_keyboard_buffer, key_buf, KEYBOARD_SIZE*sizeof(unsigned char));
}

JNIEXPORT int JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nReadKeyboard(JNIEnv * env, jclass clazz, jobject buffer, jint buffer_position) {
	handleMessages(env);
	jint* buffer_ptr = (jint *)(*env)->GetDirectBufferAddress(env, buffer);
	int buffer_size = ((*env)->GetDirectBufferCapacity(env, buffer))/sizeof(jint) - buffer_position;
	return copyEvents(&event_queue, buffer_ptr + buffer_position, buffer_size);
}
