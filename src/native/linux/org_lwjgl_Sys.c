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
 * Linux system library.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <sched.h>
#include <sys/time.h>
#include <sys/resource.h>
#include "org_lwjgl_Sys.h"
#include "common_tools.h"

static long int		hires_timer;				// Hires timer current time

/*
 * Class:     org_lwjgl_Sys
 * Method:    getTimerResolution
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_Sys_getTimerResolution
  (JNIEnv * env, jclass clazz)
{
	// Constant on linux
	return 1000000;
}

static long queryTime(void) {
	struct timeval tv;
	if (gettimeofday(&tv, NULL) == -1) {
		printfDebug("Could not read current time\n");
	}
	long result = tv.tv_sec * 1000000l + tv.tv_usec;

	return result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_Sys_setDebug(JNIEnv *env, jclass clazz, jboolean enabled) {
	setDebugEnabled(enabled == JNI_TRUE ? true : false);
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    ngetTime
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_Sys_ngetTime
  (JNIEnv * env, jclass clazz)
{
	hires_timer = queryTime();
	return (jlong) hires_timer;
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    setProcessPriority
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Sys_setProcessPriority
  (JNIEnv * env, jclass clazz, jint priority)
{
	int linux_priority;
	int max_pri, min_pri;
	struct sched_param sched_pri;

	if (sched_getscheduler(0) != SCHED_OTHER) {
		// Reset scheduler to normal
		sched_pri.sched_priority = 0;
		if (sched_setscheduler(0, SCHED_OTHER, &sched_pri) != 0) {
			printfDebug("Could not set realtime priority\n");
			return;
		}
	}

	switch (priority) {
	case org_lwjgl_Sys_REALTIME_PRIORITY:
		min_pri = sched_get_priority_min(SCHED_FIFO);
		max_pri = sched_get_priority_max(SCHED_FIFO);
		if (min_pri == -1 || max_pri == -1) {
			printfDebug("Failed to set realtime priority\n");
			return;
		}
		sched_pri.sched_priority = (max_pri + min_pri)/2;
		if (sched_setscheduler(0, SCHED_FIFO, &sched_pri) != 0) {
			printfDebug("Could not set realtime priority\n");
			return;
		}
		return;
	case org_lwjgl_Sys_HIGH_PRIORITY:
		linux_priority = -20;
		break;
	case org_lwjgl_Sys_NORMAL_PRIORITY:
		linux_priority = 0;
		break;
	case org_lwjgl_Sys_LOW_PRIORITY:
		linux_priority = 20;
		break;
	default:
		return;
	}

	if (setpriority(PRIO_PROCESS, 0, linux_priority) == -1) {
		printfDebug("Failed to set priority.\n");
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_Sys_nAlert(JNIEnv * env, jclass clazz, jstring title, jstring message)
{
	jboolean copy = JNI_FALSE;
	const char * eMessageText = (*env)->GetStringUTFChars(env, message, &copy);
	const char * cTitleBarText = (*env)->GetStringUTFChars(env, title, &copy);
	printf("*** Alert ***\n%s\n%s\n", cTitleBarText, eMessageText);

	(*env)->ReleaseStringUTFChars(env, message, eMessageText);
	(*env)->ReleaseStringUTFChars(env, title, cTitleBarText);
}

JNIEXPORT jstring JNICALL Java_org_lwjgl_Sys_getNativeLibraryVersion(JNIEnv *env, jclass clazz) {
	return getVersionString(env);
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    openURL
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Sys_nOpenURL
  (JNIEnv * env, jclass clazz, jstring url)
{
	const char * urlString = (*env)->GetStringUTFChars(env, url, NULL);
	printf("*** Please visit %s\n", urlString);
	(*env)->ReleaseStringUTFChars(env, url, urlString);
}


JNIEXPORT jstring JNICALL Java_org_lwjgl_Sys_nGetClipboard
  (JNIEnv * env, jclass clazz)
{
	return NULL;
}
