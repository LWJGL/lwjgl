/*
 * org_lwjgl_Sys.cpp
 *
 * Win32 specific library
 *
 * (C) 2002 Shaven Puppy Ltd
 *
 * Created 10 August 2002 by foo
 *
 */

#include <windows.h>
#include "org_lwjgl_Sys.h"

__int64		hires_timer_freq;			// Hires timer frequency
__int64		hires_timer_start;			// Hires timer start
__int64		hires_timer;				// Hires timer current time

/*
 * Class:     org_lwjgl_Sys
 * Method:    getDirectBufferAddress
 * Signature: (Ljava/nio/Buffer;)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_Sys_getDirectBufferAddress
  (JNIEnv * env, jclass clazz, jobject buf)
{
	return (jint) env->GetDirectBufferAddress(buf);
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    createDirectBuffer
 * Signature: (II)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_Sys_createDirectBuffer
  (JNIEnv * env, jclass clazz, jint address, jint length)
{
	return env->NewDirectByteBuffer((void *)address, length);
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
