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
 * $Id$
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <jni.h>
#include "org_lwjgl_opengl_WindowsContextImplementation.h"
#include "context.h"
#include "extgl_wgl.h"
#include "common_tools.h"

typedef struct {
	HGLRC context;
} WindowsContext;

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_WindowsContextImplementation_nCreate
  (JNIEnv *env, jclass clazz, jobject peer_info_handle, jobject attribs, jobject shared_context_handle) {
	WindowsPeerInfo *peer_info;
	WindowsContext *shared_context_info;
	WindowsContext *context_info;
	HGLRC context;
	HGLRC shared_context = NULL;

	// -- We need to create a temporary context to detect the presence of WGL_ARB_create_context
	HDC saved_current_hdc;
	HGLRC saved_current_hglrc;
	WGLExtensions extensions;
	const int *attribList = attribs == NULL ? NULL : ((const int *)(*env)->GetDirectBufferAddress(env, attribs));

	jobject context_handle = newJavaManagedByteBuffer(env, sizeof(WindowsContext));
	if (context_handle == NULL) {
		throwException(env, "Could not create handle buffer");
		return NULL;
	}

	peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	if (shared_context_handle != NULL) {
		shared_context_info = (WindowsContext *)(*env)->GetDirectBufferAddress(env, shared_context_handle);
		shared_context = shared_context_info->context;
	}

	// Create the context
	context = wglCreateContext(peer_info->drawable_hdc);
	if (context == NULL) {
		throwException(env, "Could not create context");
		return NULL;
	}

	// Save the current HDC and HGLRC to avoid disruption
	saved_current_hdc = wglGetCurrentDC();
	saved_current_hglrc = wglGetCurrentContext();

	// Make context current and detect extensions
	if (!wglMakeCurrent(peer_info->drawable_hdc, context)) {
		throwException(env, "Could not bind dummy context");
		return NULL;
	}
	extgl_InitWGL(&extensions);
	peer_info->extensions = extensions;

	// Restore previous context
	wglMakeCurrent(saved_current_hdc, saved_current_hglrc);

	//
	if ( extensions.WGL_ARB_create_context ) { // We support WGL_ARB_create_context, use the new context creation routine
		// If we have no context to share and no special attributes, we don't have to recreate the context - wglCreateContext is equivalent to wglCreateContextAttribs(hdc,0,NULL).
		if ( shared_context != NULL || attribList != NULL ) {
			// Delete the oldschool context
			wglDeleteContext(context);
			// Create a new context using WGL_ARB_create_context
			context = (HGLRC)extensions.wglCreateContextAttribsARB(peer_info->drawable_hdc, shared_context, attribList);
			if (context == NULL) {
				throwException(env, "Could not create context (WGL_ARB_create_context)");
				return NULL;
			}
		}
	} else { // We don't support WGL_ARB_create_context, use the old context creation routine
		if (shared_context != NULL && !wglShareLists(shared_context, context)) { // Use wglShareLists to share context data
			wglDeleteContext(context);
			throwException(env, "Could not share contexts");
			return NULL;
		}
	}

	context_info = (WindowsContext *)(*env)->GetDirectBufferAddress(env, context_handle);
	context_info->context = context;
	return context_handle;
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_WindowsContextImplementation_getHGLRC(JNIEnv *env, jclass clazz, jobject context_handle) {
    WindowsContext *context_info = (WindowsContext *)(*env)->GetDirectBufferAddress(env, context_handle);
    return (intptr_t)context_info->context;
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_WindowsContextImplementation_getHDC(JNIEnv *env, jclass clazz, jobject peer_info_handle) {
    WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
    return (intptr_t)peer_info->drawable_hdc;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsContextImplementation_nSwapBuffers
  (JNIEnv *env, jclass clazz, jobject peer_info_handle) {
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	SwapBuffers(peer_info->drawable_hdc);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsContextImplementation_nReleaseCurrentContext
  (JNIEnv *env, jclass clazz) {
	wglMakeCurrent(NULL, NULL);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsContextImplementation_nMakeCurrent
  (JNIEnv *env, jclass clazz, jobject peer_info_handle, jobject context_handle) {
	WindowsContext *context_info = (WindowsContext *)(*env)->GetDirectBufferAddress(env, context_handle);
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	if (!wglMakeCurrent(peer_info->drawable_hdc, context_info->context))
		throwException(env, "Could not make context current");
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_WindowsContextImplementation_nIsCurrent
  (JNIEnv *env, jclass clazz, jobject context_handle) {
	WindowsContext *context_info = (WindowsContext *)(*env)->GetDirectBufferAddress(env, context_handle);
	return wglGetCurrentContext() == context_info->context;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_WindowsContextImplementation_nSetSwapInterval
  (JNIEnv *env, jclass clazz, jint value) {
	WGLExtensions extensions;
	extgl_InitWGL(&extensions);
	if (extensions.WGL_EXT_swap_control) {
		return extensions.wglSwapIntervalEXT(value) ? JNI_TRUE : JNI_FALSE;
	} else
		return JNI_FALSE;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsContextImplementation_nDestroy
  (JNIEnv *env, jclass clazz, jobject context_handle) {
	WindowsContext *context_info = (WindowsContext *)(*env)->GetDirectBufferAddress(env, context_handle);
	wglDeleteContext(context_info->context);
}
