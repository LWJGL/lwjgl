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
 * Win32 system library.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#include <windows.h>
#include "org_lwjgl_Sys.h"

// Handle to the application's window
extern HWND hwnd;

__int64		hires_timer_freq;			// Hires timer frequency
__int64		hires_timer_start;			// Hires timer start
__int64		hires_timer;				// Hires timer current time

/*
 * Class:     org_lwjgl_Sys
 * Method:    nGetNULLValue
 * Signature: ()I
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_Sys_nGetNULLValue
  (JNIEnv *env, jclass clazz)
{
	return env->NewDirectByteBuffer(NULL, 0);
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    getTimerResolution
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_Sys_getTimerResolution
  (JNIEnv * env, jclass clazz)
{
	return hires_timer_freq;
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    getTime
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_Sys_getTime
  (JNIEnv * env, jclass clazz)
{
	QueryPerformanceCounter((LARGE_INTEGER*) &hires_timer);
	hires_timer -= hires_timer_start;
	return hires_timer;
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    setTime
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Sys_setTime
  (JNIEnv * env, jclass clazz, jlong startTime)
{
	QueryPerformanceFrequency((LARGE_INTEGER*) &hires_timer_freq);
	QueryPerformanceCounter((LARGE_INTEGER*) &hires_timer_start);
	hires_timer_start -= startTime;
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    setProcessPriority
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Sys_setProcessPriority
  (JNIEnv * env, jclass clazz, jint priority)
{
	HANDLE me = GetCurrentProcess();
	int win32priority;

	switch (priority) {
	case org_lwjgl_Sys_REALTIME_PRIORITY:
		win32priority = REALTIME_PRIORITY_CLASS;
		break;
	case org_lwjgl_Sys_HIGH_PRIORITY:
		win32priority = HIGH_PRIORITY_CLASS;
		break;
	case org_lwjgl_Sys_NORMAL_PRIORITY:
		win32priority = NORMAL_PRIORITY_CLASS;
		break;
	case org_lwjgl_Sys_LOW_PRIORITY:
		win32priority = IDLE_PRIORITY_CLASS;
		break;
	default:
		return;
	}

	if (!SetPriorityClass(me, win32priority)) {
#ifdef _DEBUG
		printf("Failed to set priority class.\n");
#endif
	}
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    alert
 * Signature: (Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Sys_alert
  (JNIEnv * env, jclass clazz, jstring title, jstring message)
{
	jboolean copy = JNI_FALSE;
	const char * eMessageText = env->GetStringUTFChars(message, &copy);
	const char * cTitleBarText = env->GetStringUTFChars(title, &copy);
	MessageBox(hwnd, eMessageText, cTitleBarText, MB_OK | MB_TOPMOST);

#ifdef _DEBUG
	printf("*** Alert ***%s\n%s\n", cTitleBarText, eMessageText);
#endif
	
	env->ReleaseStringUTFChars(message, eMessageText);
	env->ReleaseStringUTFChars(title, cTitleBarText);
}
