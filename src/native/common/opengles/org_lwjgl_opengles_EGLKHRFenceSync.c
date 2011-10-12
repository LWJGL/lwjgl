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
#include "org_lwjgl_opengles_EGLKHRFenceSync.h"
#include "extgl_egl.h"

static PFNEGLCREATESYNCKHRPROC eglCreateSyncKHR;
static PFNEGLDESTROYSYNCKHRPROC eglDestroySyncKHR;
static PFNEGLCLIENTWAITSYNCKHRPROC eglClientWaitSyncKHR;
static PFNEGLGETSYNCATTRIBKHRPROC eglGetSyncAttribKHR;

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengles_EGLKHRFenceSync_neglCreateSyncKHR(JNIEnv *env, jclass clazz, jlong dpy_ptr, jint type, jlong attrib_list) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    const EGLint *attrib_list_address = (EGLint *)(intptr_t)attrib_list;

    return (intptr_t)eglCreateSyncKHR(dpy, type, attrib_list_address);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGLKHRFenceSync_neglDestroySyncKHR(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong sync_ptr) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLSyncKHR sync = (EGLSyncKHR)(intptr_t)sync_ptr;

    return eglDestroySyncKHR(dpy, sync);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengles_EGLKHRFenceSync_neglClientWaitSyncKHR(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong sync_ptr, jint flags, jlong timeout) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLSyncKHR sync = (EGLSyncKHR)(intptr_t)sync_ptr;

    return eglClientWaitSyncKHR(dpy, sync, flags, timeout);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGLKHRFenceSync_neglGetSyncAttribKHR(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong sync_ptr, jint attribute, jlong value) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLSyncKHR sync = (EGLSyncKHR)(intptr_t)sync_ptr;
    EGLint *value_address = (EGLint *)(intptr_t)value;

    return eglGetSyncAttribKHR(dpy, sync, attribute, value_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_EGLKHRFenceSync_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"neglCreateSyncKHR", "(JIJ)J", (void *)&Java_org_lwjgl_opengles_EGLKHRFenceSync_neglCreateSyncKHR, "eglCreateSyncKHR", (void *)&eglCreateSyncKHR, false},
		{"neglDestroySyncKHR", "(JJ)Z", (void *)&Java_org_lwjgl_opengles_EGLKHRFenceSync_neglDestroySyncKHR, "eglDestroySyncKHR", (void *)&eglDestroySyncKHR, false},
		{"neglClientWaitSyncKHR", "(JJIJ)I", (void *)&Java_org_lwjgl_opengles_EGLKHRFenceSync_neglClientWaitSyncKHR, "eglClientWaitSyncKHR", (void *)&eglClientWaitSyncKHR, false},
		{"neglGetSyncAttribKHR", "(JJIJ)Z", (void *)&Java_org_lwjgl_opengles_EGLKHRFenceSync_neglGetSyncAttribKHR, "eglGetSyncAttribKHR", (void *)&eglGetSyncAttribKHR, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}