/* 
 * Copyright (c) 2002-2008 LWJGL Project
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
 * $Id: org_lwjgl_input_Keyboard.c 2385 2006-06-23 16:45:21Z elias_naur $
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision: 2385 $
 */

#undef  DIRECTINPUT_VERSION
#define DIRECTINPUT_VERSION 0x0800
#include "Window.h"
#include "dinputhelper.h"
#include <dinput.h>
#include <jni.h>
#include "org_lwjgl_opengl_WindowsDirectInput8.h"
#include "org_lwjgl_opengl_WindowsDirectInputDevice8.h"

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDirectInputDevice8_unacquire(JNIEnv *env, jobject unused, jlong di_device) {
	LPDIRECTINPUTDEVICE8 lpdevice = (LPDIRECTINPUTDEVICE8)(LONG_PTR)di_device;
	return IDirectInputDevice8_Unacquire(lpdevice);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDirectInputDevice8_poll(JNIEnv *env, jobject unused, jlong di_device) {
	LPDIRECTINPUTDEVICE8 lpdevice = (LPDIRECTINPUTDEVICE8)(LONG_PTR)di_device;
	return IDirectInputDevice8_Poll(lpdevice);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDirectInputDevice8_setDataFormat(JNIEnv *env, jobject unused, jlong di_device, jint type) {
	LPDIRECTINPUTDEVICE8 lpdevice = (LPDIRECTINPUTDEVICE8)(LONG_PTR)di_device;
	LPCDIDATAFORMAT format;

	switch (type) {
		case org_lwjgl_opengl_WindowsDirectInput8_KEYBOARD_TYPE:
			format = &c_dfDIKeyboard;
			break;
		case org_lwjgl_opengl_WindowsDirectInput8_MOUSE_TYPE:
			format = &c_dfDIMouse;
			break;
		default:
			throwFormattedException(env, "Unknown device type (%d)", type);
			return DIERR_INVALIDPARAM;
	}
	return IDirectInputDevice8_SetDataFormat(lpdevice, format);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDirectInputDevice8_getDeviceState(JNIEnv *env, jobject unused, jlong di_device, jobject buffer_obj, jint pos, jint size) {
	LPDIRECTINPUTDEVICE8 lpdevice = (LPDIRECTINPUTDEVICE8)(LONG_PTR)di_device;
    jbyte *buffer = pos + (jbyte *)(*env)->GetDirectBufferAddress(env, buffer_obj);
    return IDirectInputDevice8_GetDeviceState(lpdevice, size, buffer);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDirectInputDevice8_getEventSize(JNIEnv *env, jobject unused) {
	return sizeof(DIDEVICEOBJECTDATA);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDirectInputDevice8_getDeviceData(JNIEnv *env, jobject unused, jlong di_device, jobject event_buffer_obj, jint event_buffer_size, jobject buffer_obj, jint buffer_pos, jint buffer_size) {
	LPDIRECTINPUTDEVICE8 lpdevice = (LPDIRECTINPUTDEVICE8)(LONG_PTR)di_device;
    jint *buffer = buffer_pos + (jint *)(*env)->GetDirectBufferAddress(env, buffer_obj);
    DIDEVICEOBJECTDATA *event_buffer = (*env)->GetDirectBufferAddress(env, event_buffer_obj);
	DIDEVICEOBJECTDATA *current_event;
	DWORD num_events = event_buffer_size/sizeof(DIDEVICEOBJECTDATA);
	HRESULT ret = IDirectInputDevice8_GetDeviceData(lpdevice, sizeof(DIDEVICEOBJECTDATA), event_buffer, &num_events, 0);
	jint num_buffer_events = buffer_size/org_lwjgl_opengl_WindowsDirectInputDevice8_DATA_SIZE;
	jint i;
	if (ret != DI_OK && ret != DI_BUFFEROVERFLOW)
		return ret;

	if (num_buffer_events < num_events) {
		num_events = num_buffer_events;
		ret = DI_BUFFEROVERFLOW;
	}
	for (i = 0; i < num_events; i++) {
		current_event = event_buffer + i;
		buffer[buffer_pos++] = current_event->dwOfs;
		buffer[buffer_pos++] = current_event->dwData;
		buffer[buffer_pos++] = current_event->dwTimeStamp;
	}
	positionBuffer(env, buffer_obj, buffer_pos);
	return ret;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDirectInputDevice8_acquire(JNIEnv *env, jobject unused, jlong di_device) {
	LPDIRECTINPUTDEVICE8 lpdevice = (LPDIRECTINPUTDEVICE8)(LONG_PTR)di_device;
    return IDirectInputDevice8_Acquire(lpdevice);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDirectInputDevice8_setBufferSize(JNIEnv *env, jobject unused, jlong di_device, jint buffer_size) {
	LPDIRECTINPUTDEVICE8 lpdevice = (LPDIRECTINPUTDEVICE8)(LONG_PTR)di_device;
	DIPROPDWORD dipropdw;
	dipropdw.diph.dwSize = sizeof(DIPROPDWORD);
	dipropdw.diph.dwHeaderSize = sizeof(DIPROPHEADER);
	dipropdw.diph.dwObj = 0;
	dipropdw.diph.dwHow = DIPH_DEVICE;
	dipropdw.dwData = buffer_size;
    return IDirectInputDevice8_SetProperty(lpdevice, DIPROP_BUFFERSIZE, &dipropdw.diph);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDirectInputDevice8_setCooperativeLevel(JNIEnv *env, jobject unused, jlong di_device, jlong hwnd_int, jint flags) {
	LPDIRECTINPUTDEVICE8 lpdevice = (LPDIRECTINPUTDEVICE8)(LONG_PTR)di_device;
	HWND hwnd = (HWND)(LONG_PTR)hwnd_int;
	return IDirectInputDevice8_SetCooperativeLevel(lpdevice, hwnd, flags);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDirectInputDevice8_release(JNIEnv *env, jobject unused, jlong di_device) {
	LPDIRECTINPUTDEVICE8 lpdevice = (LPDIRECTINPUTDEVICE8)(LONG_PTR)di_device;
	IDirectInputDevice8_Release(lpdevice);
}

static BOOL CALLBACK EnumMouseObjectsCallback(LPCDIDEVICEOBJECTINSTANCE lpddoi, LPVOID pvRef) {
	enum_context_t *enum_context = (enum_context_t *)pvRef;
	jint object_type;

	if (IsEqualGUID(&lpddoi->guidType, &GUID_Button)) {
		object_type = org_lwjgl_opengl_WindowsDirectInputDevice8_GUID_Button;
	} else if(IsEqualGUID(&lpddoi->guidType, &GUID_XAxis)) {
		object_type = org_lwjgl_opengl_WindowsDirectInputDevice8_GUID_XAxis;
	} else if(IsEqualGUID(&lpddoi->guidType, &GUID_YAxis)) {
		object_type = org_lwjgl_opengl_WindowsDirectInputDevice8_GUID_YAxis;
	} else if(IsEqualGUID(&lpddoi->guidType, &GUID_ZAxis)) {
		object_type = org_lwjgl_opengl_WindowsDirectInputDevice8_GUID_ZAxis;
	} else {
		object_type = org_lwjgl_opengl_WindowsDirectInputDevice8_GUID_Unknown;
	}

	return objectCallback(enum_context->env, enum_context->enumerator, object_type, lpddoi->tszName) ? DIENUM_CONTINUE : DIENUM_STOP;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDirectInputDevice8_enumObjects(JNIEnv *env, jobject unused, jlong di_device, jobject enumerator) {
	LPDIRECTINPUTDEVICE8 lpdevice = (LPDIRECTINPUTDEVICE8)(LONG_PTR)di_device;
	enum_context_t enum_context;
	enum_context.env = env;
	enum_context.enumerator = enumerator;
	return IDirectInputDevice8_EnumObjects(lpdevice, EnumMouseObjectsCallback, &enum_context, DIDFT_ALL);
}
