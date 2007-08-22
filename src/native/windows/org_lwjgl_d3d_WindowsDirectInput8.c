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
 * $Id: org_lwjgl_input_Keyboard.c 2385 2006-06-23 16:45:21Z elias_naur $
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision: 2385 $
 */

#undef  DIRECTINPUT_VERSION
#define DIRECTINPUT_VERSION 0x0800
#include "Window.h"
#include <dinput.h>
#include <jni.h>
#include "org_lwjgl_d3d_WindowsDirectInput8.h"

JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_WindowsDirectInput8_createDirectInput(JNIEnv *env, jobject unused, jlong hinst_int) {
	HINSTANCE hinst = (HINSTANCE)(LONG_PTR)hinst_int;
	LPDIRECTINPUT8 lpdi;
	HRESULT ret;

	ret = CoInitialize(NULL);
	if (ret != S_OK && ret != S_FALSE) {
		throwFormattedException(env, "CoInitialize failed (%x)", ret);
		return (LONG_PTR)NULL;
	}
	ret = CoCreateInstance(&CLSID_DirectInput8, NULL, CLSCTX_INPROC_SERVER, &IID_IDirectInput8, (void *)&lpdi);
	if (ret != DI_OK && ret != DIERR_BETADIRECTINPUTVERSION) {
		throwFormattedException(env, "Failed to create DirectInput (%x)", ret);
		return (LONG_PTR)NULL;
	}

	ret = IDirectInput8_Initialize(lpdi, hinst, DIRECTINPUT_VERSION);
	if (FAILED(ret)) {
		throwFormattedException(env, "Failed to initialize DirectInput (%x)", ret);
		IDirectInput8_Release(lpdi);
		return (LONG_PTR)NULL;
	}
	return (LONG_PTR)lpdi;
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d_WindowsDirectInput8_nCreateDevice(JNIEnv *env, jclass unused, jlong di_interface, jint type) {
	LPDIRECTINPUT8 lpdi = (LPDIRECTINPUT8)(LONG_PTR)di_interface;
	LPDIRECTINPUTDEVICE8 lpdevice;
	GUID device_guid;
	HRESULT ret;

	switch (type) {
		case org_lwjgl_d3d_WindowsDirectInput8_KEYBOARD_TYPE:
			device_guid = GUID_SysKeyboard;
			break;
		case org_lwjgl_d3d_WindowsDirectInput8_MOUSE_TYPE:
			device_guid = GUID_SysMouse;
			break;
		default:
			throwFormattedException(env, "Unknown device type (%d)", type);
			return (LONG_PTR)NULL;
	}
    ret = IDirectInput8_CreateDevice(lpdi, &device_guid, &lpdevice, NULL);
    if (ret != DI_OK) {
		throwFormattedException(env, "Failed to create keyboard (%x).", ret);
		return (LONG_PTR)NULL;
	}
	return (LONG_PTR)lpdevice;
}

JNIEXPORT void JNICALL Java_org_lwjgl_d3d_WindowsDirectInput8_release(JNIEnv *env, jobject unused, jlong di_interface) {
	LPDIRECTINPUT8 lpdi = (LPDIRECTINPUT8)(LONG_PTR)di_interface;
	IDirectInput8_Release(lpdi);
	CoUninitialize();
}
