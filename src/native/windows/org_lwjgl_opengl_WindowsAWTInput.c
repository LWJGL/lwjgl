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
 * $Id: org_lwjgl_opengl_WindowsAWTGLCanvasPeerInfo.c 2575 2006-09-19 14:17:13Z elias_naur $
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision: 2575 $
 */

#include <jni.h>
#include "context.h"
#include "org_lwjgl_opengl_WindowsAWTInput.h"

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsAWTInput_getWindowStyles(JNIEnv *env, jclass unused, jlong hwnd_ptr) {
	HWND hwnd = (HWND)(intptr_t)hwnd_ptr;
	WINDOWINFO window_info;
	if (!GetWindowInfo(hwnd, &window_info)) {
		throwFormattedException(env, "Failed to get window info (%d).", GetLastError());
		return 0;
	}
	return window_info.dwStyle;
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_WindowsAWTInput_getParentWindow(JNIEnv *env, jclass unused, jlong hwnd_ptr) {
	HWND hwnd = (HWND)(intptr_t)hwnd_ptr;
	return (intptr_t)GetParent(hwnd);
}
