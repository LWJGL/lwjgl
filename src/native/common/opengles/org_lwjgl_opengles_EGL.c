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
#include "org_lwjgl_opengles_EGL.h"
#include "extgl_egl.h"

JNIEXPORT jint JNICALL Java_org_lwjgl_opengles_EGL_eglGetError(JNIEnv *env, jclass clazz) {
    return eglGetError();
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengles_EGL_neglGetDisplay(JNIEnv *env, jclass clazz, jlong display_id) {
    return (intptr_t)eglGetDisplay((EGLNativeDisplayType)(intptr_t)display_id);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_neglInitialize(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong version) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLint *version_address = (EGLint *)(intptr_t)version;

    return eglInitialize(dpy, version_address, version_address + 1);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_neglTerminate(JNIEnv *env, jclass clazz, jlong dpy_ptr) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;

    return eglTerminate(dpy);
}

JNIEXPORT jstring JNICALL Java_org_lwjgl_opengles_EGL_neglQueryString(JNIEnv *env, jclass clazz, jlong dpy_ptr, jint name) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    const char * __result = eglQueryString(dpy, name);
   	if ( __result == NULL )
   		return NULL;

   	return NewStringNativeWithLength(env, __result, (jsize)strlen(__result));
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_neglGetConfigs(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong configs, jint config_size, jlong num_config) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLConfig *configs_address = (EGLConfig *)(intptr_t)configs;
    EGLint *num_config_address = (EGLint *)(intptr_t)num_config;

	return eglGetConfigs(dpy, configs_address, config_size, num_config_address);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_neglChooseConfig(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong attrib_list, jlong configs, jint config_size, jlong num_config) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    const EGLint *attrib_list_address = (EGLint *)(intptr_t)attrib_list;
    EGLConfig *configs_address = (EGLConfig *)(intptr_t)configs;
    EGLint *num_config_address = (EGLint *)(intptr_t)num_config;

    return eglChooseConfig(dpy, attrib_list_address, configs_address, config_size, num_config_address);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_neglGetConfigAttrib(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong config_ptr, jint attribute, jlong value) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLConfig config = (EGLConfig)(intptr_t)config_ptr;
    EGLint *value_address = (EGLint *)(intptr_t)value;

    return eglGetConfigAttrib(dpy, config, attribute, value_address);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengles_EGL_neglCreateWindowSurface(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong config_ptr, jlong win, jlong attrib_list) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLConfig config = (EGLConfig)(intptr_t)config_ptr;
    const EGLint *attrib_list_address = (EGLint *)(intptr_t)attrib_list;

    return (intptr_t)eglCreateWindowSurface(dpy, config, (EGLNativeWindowType)(intptr_t)win, attrib_list_address);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengles_EGL_neglCreatePbufferSurface(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong config_ptr, jlong attrib_list) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLConfig config = (EGLConfig)(intptr_t)config_ptr;
    const EGLint *attrib_list_address = (EGLint *)(intptr_t)attrib_list;

    return (intptr_t)eglCreatePbufferSurface(dpy, config, attrib_list_address);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_neglDestroySurface(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong surface_ptr) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLSurface surface = (EGLSurface)(intptr_t)surface_ptr;

    return eglDestroySurface(dpy, surface);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_neglSurfaceAttrib(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong surface_ptr, jint attribute, jint value) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLSurface surface = (EGLSurface)(intptr_t)surface_ptr;

    return eglSurfaceAttrib(dpy, surface, attribute, value);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_neglQuerySurface(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong surface_ptr, jint attribute, jlong value) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLSurface surface = (EGLSurface)(intptr_t)surface_ptr;
    EGLint *value_address = (EGLint *)(intptr_t)value;

    return eglQuerySurface(dpy, surface, attribute, value_address);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_eglBindAPI(JNIEnv *env, jclass clazz, jint api) {
    return eglBindAPI(api);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengles_EGL_eglQueryAPI(JNIEnv *env, jclass clazz) {
    return eglQueryAPI();
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_eglReleaseThread(JNIEnv *env, jclass clazz) {
    return eglReleaseThread();
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_neglSwapInterval(JNIEnv *env, jclass clazz, jlong dpy_ptr, jint interval) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    return eglSwapInterval(dpy, interval);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengles_EGL_neglCreateContext(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong config_ptr, jlong share_context_ptr, jlong attrib_list) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLConfig config = (EGLConfig)(intptr_t)config_ptr;
    EGLContext share_context = (EGLContext)(intptr_t)share_context_ptr;
    const EGLint *attrib_list_address = (EGLint *)(intptr_t)attrib_list;

    return (intptr_t)eglCreateContext(dpy, config, share_context, attrib_list_address);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_neglDestroyContext(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong ctx_ptr) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLContext ctx = (EGLContext)(intptr_t)ctx_ptr;

    return eglDestroyContext(dpy, ctx);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_neglMakeCurrent(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong draw_ptr, jlong read_ptr, jlong ctx_ptr) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLSurface draw = (EGLSurface)(intptr_t)draw_ptr;
    EGLSurface read = (EGLSurface)(intptr_t)read_ptr;
    EGLContext ctx = (EGLContext)(intptr_t)ctx_ptr;

    return eglMakeCurrent(dpy, draw, read, ctx);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengles_EGL_neglGetCurrentContext(JNIEnv *env, jclass clazz) {
    return (intptr_t)eglGetCurrentContext();
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengles_EGL_neglGetCurrentSurface(JNIEnv *env, jclass clazz, jint readdraw) {
    return (intptr_t)eglGetCurrentSurface(readdraw);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengles_EGL_neglGetCurrentDisplay(JNIEnv *env, jclass clazz) {
    return (intptr_t)eglGetCurrentDisplay();
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_neglQueryContext(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong ctx_ptr, jint attribute, jlong value) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLContext ctx = (EGLContext)(intptr_t)ctx_ptr;
    EGLint *value_address = (EGLint *)(intptr_t)value;

    return eglQueryContext(dpy, ctx, attribute, value_address);

}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_eglWaitClient(JNIEnv *env, jclass clazz) {
    return eglWaitClient();
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_eglWaitGL(JNIEnv *env, jclass clazz) {
    return eglWaitGL();
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_eglWaitNative(JNIEnv *env, jclass clazz, jint engine) {
    return eglWaitNative(engine);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengles_EGL_neglSwapBuffers(JNIEnv *env, jclass clazz, jlong dpy_ptr, jlong surface_ptr) {
    EGLDisplay dpy = (EGLDisplay)(intptr_t)dpy_ptr;
    EGLSurface surface = (EGLSurface)(intptr_t)surface_ptr;

    return eglSwapBuffers(dpy, surface);
}