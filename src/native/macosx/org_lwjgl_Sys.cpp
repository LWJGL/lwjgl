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
 * Linux system library.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <sched.h>
#include <sys/time.h>
#include <sys/resource.h>
#include <Carbon/Carbon.h>
#include "org_lwjgl_Sys.h"

long int		hires_timer_freq;			// Hires timer frequency
long int		hires_timer_start;			// Hires timer start
long int		hires_timer;				// Hires timer current time

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

static long queryTime(void) {
    struct timeval tv;
    if (gettimeofday(&tv, NULL) == -1) {
#ifdef _DEBUG
        printf("Could not read current time\n");
#endif
    }
    long result = tv.tv_sec * 1000000l + tv.tv_usec;

    return result;
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    getTime
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_Sys_getTime
(JNIEnv * env, jclass clazz)
{
    hires_timer = queryTime();
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
    hires_timer_start = queryTime();
    // We don't have a real resolution so assume highest possible
    hires_timer_freq = 1000000;
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
#ifdef _DEBUG
    printf("Unsupported\n");
#endif
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    alert
 * Signature: (Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Sys_alert(JNIEnv * env, jclass clazz, jstring title, jstring message)
{
    jboolean copy = JNI_FALSE;
    const char * eMessageText = env->GetStringUTFChars(message, &copy);
    const char * cTitleBarText = env->GetStringUTFChars(title, &copy);
    printf("*** Alert ***\n%s\n%s\n", cTitleBarText, eMessageText);

    env->ReleaseStringUTFChars(message, eMessageText);
    env->ReleaseStringUTFChars(title, cTitleBarText);
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    openURL
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Sys_nOpenURL
  (JNIEnv * env, jclass clazz, jstring url)
{
	const char * urlString = env->GetStringUTFChars(url, NULL);

	OSStatus err;
	ICInstance inst;
	long startSel;
	long endSel;
	Str255  urlStr;

	CopyCStringToPascal(urlString, urlStr);
	env->ReleaseStringUTFChars(url, urlString);
/*	err = ICStart(&inst, '????'); // Use your creator code if you have one!
	if (err == noErr) {
		startSel = 0;
		endSel = urlStr[0];
		err = ICLaunchURL(inst, "\p", (char *) &urlStr[1], urlStr[0], &startSel, &endSel);
		(void) ICStop(inst);
	}*/
}
