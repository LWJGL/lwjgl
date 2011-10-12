/*
 * Copyright (c) 2002-2011 LWJGL Project
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

#include <jni.h>
#include "common_tools.h"
#include "org_lwjgl_opengles_EGLKHRReusableSync.h"
#include "extgl_egl.h"

static PFNEGLSIGNALSYNCKHRPROC eglSignalSyncKHR;

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGLKHRReusableSync_neglSignalSyncKHR(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong sync_ptr, jint mode) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLSyncKHR sync = (EGLSyncKHR)(intptr_t)sync_ptr;
    return eglSignalSyncKHR(dpy, sync, mode);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_EGLKHRReusableSync_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"neglSignalSyncKHR", "(JJI)Z", (void *)&Java_org_lwjgl_opengles_EGLKHRReusableSync_neglSignalSyncKHR, "eglSignalSyncKHR", (void *)&eglSignalSyncKHR, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
