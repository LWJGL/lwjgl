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

static pascal OSStatus doKeyDown(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
	UInt32 key_code;
	OSStatus err = GetEventParameter(event, kEventParamKeyCode, typeUInt32, NULL, sizeof(key_code), NULL, &key_code);
	if (err != noErr) {
#ifdef _DEBUG
		printf("Could not get event key code\n");
#endif
		return eventNotHandledErr;
	}
printf("key down, key %d\n", key_code);
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
printf("key up, key %d\n", key_code);
	return noErr;
}

bool registerKeyboardHandler(JNIEnv* env, WindowRef win_ref) {
	EventTypeSpec event_types[1];
	EventHandlerUPP handler_upp = NewEventHandlerUPP(doKeyUp);
	event_types[0].eventClass = kEventClassKeyboard;
	event_types[0].eventKind  = kEventRawKeyUp;
	OSStatus err = InstallWindowEventHandler(win_ref, handler_upp, 1, event_types, NULL, NULL);
	DisposeEventHandlerUPP(handler_upp);
	if (noErr != err) {
		throwException(env, "Could not register window event handler");
		return false;
	}
	handler_upp = NewEventHandlerUPP(doKeyDown);
	event_types[0].eventClass = kEventClassKeyboard;
	event_types[0].eventKind  = kEventRawKeyDown;
	err = InstallWindowEventHandler(win_ref, handler_upp, 1, event_types, NULL, NULL);
	DisposeEventHandlerUPP(handler_upp);
	if (noErr != err) {
		throwException(env, "Could not register window event handler");
		return false;
	}
	return true;
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_initIDs
  (JNIEnv * env, jclass clazz)
{
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nCreate
 * Signature: ()Z
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nCreate
  (JNIEnv * env, jclass clazz)
{
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nDestroy
  (JNIEnv * env, jclass clazz)
{
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nPoll
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nPoll
  (JNIEnv * env, jclass clazz, jobject buffer)
{
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nRead
 * Signature: (I)V
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nRead
  (JNIEnv * env, jclass clazz)
{
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nEnableTranslation
 * Signature: ()I
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Keyboard_nEnableTranslation
  (JNIEnv *env, jclass clazz)
{
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nEnableBuffer
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nEnableBuffer
  (JNIEnv * env, jclass clazz)
{
}

/*
 * Class:     org_lwjgl_input_Keyboard
 * Method:    nisStateKeySet
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Keyboard_nisStateKeySet(JNIEnv *env, jclass clazz, jint key)
{
  return org_lwjgl_input_Keyboard_STATE_UNKNOWN;
}
