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
short readBuffer[KEYBOARD_BUFFER_SIZE];
jfieldID fid_readBuffer;
jfieldID fid_readBufferAddress;
unsigned char key_buf[KEYBOARD_SIZE];

int keyboard_grabbed;

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
		keyboard_grabbed = 1;
	return result;
}

void ungrabKeyboard(void) {
	keyboard_grabbed = 0;
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
	ungrabKeyboard();
}

int checkKeyEvents(unsigned char *result_buf) {
	XEvent event;
	int count = 0;
	int buf_count = 0;
	updateKeyboardGrab();
	while (XCheckMaskEvent(disp, KeyPressMask | KeyReleaseMask, &event)) {
		unsigned char keycode = (unsigned char)((event.xkey.keycode - 8) & 0xff);
		if (result_buf != NULL) {
			result_buf[buf_count++] = keycode;
			result_buf[buf_count++] = 1;
		}
		count++;
		if (event.type == KeyPress) {
			key_buf[keycode] = 1;
		} else if (event.type == KeyRelease) {
			key_buf[keycode] = 0;
		} else
			assert(0);
	}
	return count;
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nPoll
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nPoll
  (JNIEnv * env, jclass clazz, jint buf)
{
	checkKeyEvents(NULL);
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
	return checkKeyEvents((unsigned char *)keys);
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nEnableBuffer
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nEnableBuffer
  (JNIEnv * env, jclass clazz)
{
	jobject newBuffer = env->NewDirectByteBuffer(&readBuffer, KEYBOARD_BUFFER_SIZE);
	env->SetStaticObjectField(clazz, fid_readBuffer, newBuffer);
	env->SetStaticIntField(clazz, fid_readBufferAddress, (jint) (&readBuffer));
	return KEYBOARD_BUFFER_SIZE;
}
