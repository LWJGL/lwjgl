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
 * @author elias_naue <elias_naur@users.sourceforge.net>
 * @version $Revision: 2385 $
 */

#include "Window.h"
#include <jni.h>
#include "org_lwjgl_opengl_WindowsKeyboard.h"

JNIEXPORT jshort JNICALL Java_org_lwjgl_opengl_WindowsKeyboard_GetKeyState(JNIEnv *env, jclass unused, jint virt_key) {
	return GetKeyState(virt_key);
}

JNIEXPORT jshort JNICALL Java_org_lwjgl_opengl_WindowsKeyboard_GetAsyncKeyState(JNIEnv *env, jclass unused, jint virt_key) {
	return GetAsyncKeyState(virt_key);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsKeyboard_MapVirtualKey(JNIEnv *env, jclass unused, jint uCode, jint uMapType) {
	return MapVirtualKey(uCode, uMapType);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_WindowsKeyboard_isWindowsNT(JNIEnv *env, jclass unused) {
	OSVERSIONINFO osvi;

	osvi.dwOSVersionInfoSize = sizeof(osvi);
	GetVersionEx(&osvi);
	return osvi.dwPlatformId == VER_PLATFORM_WIN32_NT ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsKeyboard_ToUnicode(JNIEnv *env, jclass unused, jint wVirtKey, jint wScanCode, jobject lpKeyState_obj, jobject pwszBuff_obj, jint cchBuff, jint flags) {
	const PBYTE lpKeyState = (*env)->GetDirectBufferAddress(env, lpKeyState_obj);
	LPWSTR pwszBuff = (*env)->GetDirectBufferAddress(env, pwszBuff_obj);
	return ToUnicode(wVirtKey, wScanCode, lpKeyState, pwszBuff, cchBuff, flags);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsKeyboard_ToAscii(JNIEnv *env, jclass unused, jint wVirtKey, jint wScanCode, jobject lpKeyState_obj, jobject lpChar_obj, jint flags) {
	const PBYTE lpKeyState = (*env)->GetDirectBufferAddress(env, lpKeyState_obj);
	LPWORD lpChar = (*env)->GetDirectBufferAddress(env, lpChar_obj);
	return ToAscii(wVirtKey, wScanCode, lpKeyState, lpChar, flags);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsKeyboard_GetKeyboardState(JNIEnv *env, jclass unused, jobject lpKeyState_obj) {
	PBYTE lpKeyState = (*env)->GetDirectBufferAddress(env, lpKeyState_obj);
	return GetKeyboardState(lpKeyState);
}
