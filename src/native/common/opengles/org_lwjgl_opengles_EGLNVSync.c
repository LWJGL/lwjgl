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
#include "org_lwjgl_opengles_EGLNVSync.h"
#include "extgl_egl.h"

static PFNEGLCREATEFENCESYNCNVPROC eglCreateFenceSyncNV;
static PFNEGLDESTROYSYNCNVPROC eglDestroySyncNV;
static PFNEGLFENCENVPROC eglFenceNV;
static PFNEGLCLIENTWAITSYNCNVPROC eglClientWaitSyncNV;
static PFNEGLSIGNALSYNCNVPROC eglSignalSyncNV;
static PFNEGLGETSYNCATTRIBNVPROC eglGetSyncAttribNV;

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengles_EGLNVSync_neglCreateFenceSyncNV(JNIEnv *env, jclass clazz, jlong dpy_ptr, jint condition, jlong attrib_list) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    const EGLint *attrib_list_address = (EGLint *)(intptr_t)attrib_list;

    return (intptr_t)eglCreateFenceSyncNV(dpy, condition, attrib_list_address);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGLNVSync_neglDestroySyncNV(JNIEnv *env, jclass clazz, jlong sync_ptr) {
    EGLSyncNV sync = (EGLSyncNV)(intptr_t)sync_ptr;

    return eglDestroySyncNV(sync);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGLNVSync_neglFenceNV(JNIEnv *env, jclass clazz, jlong sync_ptr) {
    EGLSyncNV sync = (EGLSyncNV)(intptr_t)sync_ptr;

    return eglFenceNV(sync);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengles_EGLNVSync_neglClientWaitSyncNV(JNIEnv *env, jclass clazz, jlong sync_ptr, jint flags, jlong timeout) {
    EGLSyncNV sync = (EGLSyncNV)(intptr_t)sync_ptr;

    return eglClientWaitSyncNV(sync, flags, timeout);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGLNVSync_neglSignalSyncNV(JNIEnv *env, jclass clazz, jlong sync_ptr, jint mode) {
    EGLSyncNV sync = (EGLSyncNV)(intptr_t)sync_ptr;

    return eglSignalSyncNV(sync, mode);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGLNVSync_neglGetSyncAttribNV(JNIEnv *env, jclass clazz, jlong sync_ptr, jint attribute, jlong value) {
    EGLSyncNV sync = (EGLSyncNV)(intptr_t)sync_ptr;
    EGLint *value_address = (EGLint *)(intptr_t)value;

    return eglGetSyncAttribNV(sync, attribute, value_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_EGLNVSync_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"neglCreateFenceSyncNV", "(JIJ)J", (void *)&Java_org_lwjgl_opengles_EGLNVSync_neglCreateFenceSyncNV, "eglCreateFenceSyncNV", (void *)&eglCreateFenceSyncNV, false},
		{"neglDestroySyncNV", "(J)Z", (void *)&Java_org_lwjgl_opengles_EGLNVSync_neglDestroySyncNV, "eglDestroySyncNV", (void *)&eglDestroySyncNV, false},
		{"neglFenceNV", "(J)Z", (void *)&Java_org_lwjgl_opengles_EGLNVSync_neglFenceNV, "eglFenceNV", (void *)&eglFenceNV, false},
		{"neglClientWaitSyncNV", "(JIJ)I", (void *)&Java_org_lwjgl_opengles_EGLNVSync_neglClientWaitSyncNV, "eglClientWaitSyncNV", (void *)&eglClientWaitSyncNV, false},
		{"neglSignalSyncNV", "(JI)Z", (void *)&Java_org_lwjgl_opengles_EGLNVSync_neglSignalSyncNV, "eglSignalSyncNV", (void *)&eglSignalSyncNV, false},
		{"neglGetSyncAttribNV", "(JIJ)Z", (void *)&Java_org_lwjgl_opengles_EGLNVSync_neglGetSyncAttribNV, "eglGetSyncAttribNV", (void *)&eglGetSyncAttribNV, false}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
