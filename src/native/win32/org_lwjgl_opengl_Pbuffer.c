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
 * Win32 Pbuffer.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <stdlib.h>
#include "org_lwjgl_opengl_Win32Display.h"
#include "org_lwjgl_opengl_Pbuffer.h"
#include "Window.h"

#include "extgl.h"
#include "extgl_wgl.h"

#include "common_tools.h"

typedef struct _PbufferInfo {
	HGLRC Pbuffer_context;
	HPBUFFERARB Pbuffer;
	HDC Pbuffer_dc;
} PbufferInfo;

static bool isPbuffersSupported() {
	return extgl_Extensions.WGL_ARB_pixel_format && extgl_Extensions.WGL_ARB_pbuffer;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Win32Display_getPbufferCapabilities
  (JNIEnv *env, jobject self)
{
	int caps = 0;
	if (isPbuffersSupported())
		caps |= org_lwjgl_opengl_Pbuffer_PBUFFER_SUPPORTED;

	if (extgl_Extensions.WGL_ARB_render_texture)
		caps |= org_lwjgl_opengl_Pbuffer_RENDER_TEXTURE_SUPPORTED;

	if (extgl_Extensions.WGL_NV_render_texture_rectangle)
		caps |= org_lwjgl_opengl_Pbuffer_RENDER_TEXTURE_RECTANGLE_SUPPORTED;

	if (extgl_Extensions.WGL_NV_render_depth_texture)
		caps |= org_lwjgl_opengl_Pbuffer_RENDER_DEPTH_TEXTURE_SUPPORTED;

	return caps;
}

static HPBUFFERARB createPbuffer(JNIEnv *env, int width, int height, jobject pixel_format, jobject pixelFormatCaps, const int *pBufferAttribs_ptr) {
	HWND dummy_hwnd = createWindow(0, 0, 1, 1, false, false);
      HDC dummy_hdc;
      int iPixelFormat;
      HGLRC dummy_hglrc;
      BOOL result;
      HPBUFFERARB Pbuffer;
	bool pbuffers_supported;

	if (dummy_hwnd == NULL) {
		throwException(env, "Could not create dummy window");
		return NULL;
	}
        dummy_hdc = GetDC(dummy_hwnd);
        iPixelFormat = findPixelFormat(env, dummy_hdc, pixel_format);
	if (iPixelFormat == -1) {
		return NULL;
	}
	if (!applyPixelFormat(dummy_hdc, iPixelFormat)) {
		closeWindow(dummy_hwnd, dummy_hdc);
		throwException(env, "Could not apply pixel format to window");
		return NULL;
	}
	
        dummy_hglrc = wglCreateContext(dummy_hdc);
	if (dummy_hglrc == NULL) {
		closeWindow(dummy_hwnd, dummy_hdc);
		throwException(env, "Failed to create OpenGL rendering context");
		return NULL;
	}
        result = wglMakeCurrent(dummy_hdc, dummy_hglrc);
	if (!result) {
		wglDeleteContext(dummy_hglrc);
		closeWindow(dummy_hwnd, dummy_hdc);
		throwException(env, "Could not bind context to dummy window");
		return NULL;
	}
	extgl_InitWGL(env);
	pbuffers_supported = isPbuffersSupported();
	iPixelFormat = findPixelFormatARB(env, dummy_hdc, pixel_format, pixelFormatCaps, false, false, true, false);
	if (iPixelFormat == -1)
		iPixelFormat = findPixelFormatARB(env, dummy_hdc, pixel_format, pixelFormatCaps, false, false, true, true);
	wglDeleteContext(dummy_hglrc);
	if (!pbuffers_supported) {
		closeWindow(dummy_hwnd, dummy_hdc);
		throwException(env, "No Pbuffer support.");
		return NULL;
	}
	if (iPixelFormat == -1) {
		closeWindow(dummy_hwnd, dummy_hdc);
		throwException(env, "Could not find suitable pixel format.");
		return NULL;
	}
	Pbuffer = wglCreatePbufferARB(dummy_hdc, iPixelFormat, width, height, pBufferAttribs_ptr);
	closeWindow(dummy_hwnd, dummy_hdc);
	return Pbuffer;
}

static HGLRC createPbufferContext(JNIEnv *env, HDC Pbuffer_dc, HGLRC shared_context) {
	HGLRC Pbuffer_context = wglCreateContext(Pbuffer_dc);
	if (Pbuffer_context == NULL) {
		throwException(env, "Failed to create Pbuffer rendering context");
		return NULL;
	}
	if (shared_context != NULL && !wglShareLists(shared_context, Pbuffer_context)) {
		wglDeleteContext(Pbuffer_context);
		throwException(env, "Could not share buffer context.");
		return NULL;
	}
	return Pbuffer_context;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_nCreatePbuffer
  (JNIEnv *env, jobject self, jobject buffer_handle,
  jint width, jint height, jobject pixel_format,
  jobject pixelFormatCaps, jobject pBufferAttribs, jobject shared_context_handle_buffer)
{
	HPBUFFERARB Pbuffer;
	const int *pBufferAttribs_ptr;
        HDC Pbuffer_dc;
	HGLRC Pbuffer_context;
	HGLRC shared_context;
        PbufferInfo *Pbuffer_info;
        if ((*env)->GetDirectBufferCapacity(env, buffer_handle) < sizeof(PbufferInfo)) {
		throwException(env, "Buffer handle not large enough");
		return;
	}
	if ( pBufferAttribs != NULL ) {
                pBufferAttribs_ptr = (const int *)(*env)->GetDirectBufferAddress(env, pBufferAttribs);
	} else {
		pBufferAttribs_ptr = NULL;
	}
	Pbuffer = createPbuffer(env, width, height, pixel_format, pixelFormatCaps, pBufferAttribs_ptr);

	if (Pbuffer == NULL) {
		throwException(env, "Could not create Pbuffer.");
		return;
	}

        Pbuffer_dc = wglGetPbufferDCARB(Pbuffer);
	if (Pbuffer_dc == NULL) {
		wglDestroyPbufferARB(Pbuffer);
		throwException(env, "Could not get Pbuffer dc.");
		return;
	}
	shared_context = getCurrentContext();
	if (shared_context_handle_buffer != NULL) {
		Pbuffer_info = (PbufferInfo *)(*env)->GetDirectBufferAddress(env, shared_context_handle_buffer);
		shared_context = Pbuffer_info->Pbuffer_context;
	}
	Pbuffer_context = createPbufferContext(env, Pbuffer_dc, shared_context);
	if (Pbuffer_context == NULL) {
		wglReleasePbufferDCARB(Pbuffer, Pbuffer_dc);
		wglDestroyPbufferARB(Pbuffer);
		return;
	}
        Pbuffer_info = (PbufferInfo *)(*env)->GetDirectBufferAddress(env, buffer_handle);
	Pbuffer_info->Pbuffer = Pbuffer;
	Pbuffer_info->Pbuffer_context = Pbuffer_context;
	Pbuffer_info->Pbuffer_dc = Pbuffer_dc;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Win32Display_isBufferLost
  (JNIEnv *env, jobject self, jobject buffer_handle)
{
        PbufferInfo *Pbuffer_info = (PbufferInfo *)(*env)->GetDirectBufferAddress(env, buffer_handle);
	BOOL buffer_lost;
	wglQueryPbufferARB(Pbuffer_info->Pbuffer, WGL_PBUFFER_LOST_ARB, &buffer_lost);
	return buffer_lost ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_makePbufferCurrent
  (JNIEnv *env, jobject self, jobject buffer_handle)
{
        PbufferInfo *Pbuffer_info = (PbufferInfo *)(*env)->GetDirectBufferAddress(env, buffer_handle);
//	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;
	if (!wglMakeCurrent(Pbuffer_info->Pbuffer_dc, Pbuffer_info->Pbuffer_context))
		throwException(env, "Could not make pbuffer context current");
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_destroyPbuffer
  (JNIEnv *env, jobject self, jobject buffer_handle)
{
        PbufferInfo *Pbuffer_info = (PbufferInfo *)(*env)->GetDirectBufferAddress(env, buffer_handle);
	wglDeleteContext(Pbuffer_info->Pbuffer_context);
	wglReleasePbufferDCARB(Pbuffer_info->Pbuffer, Pbuffer_info->Pbuffer_dc);
	wglDestroyPbufferARB(Pbuffer_info->Pbuffer);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_setPbufferAttrib
  (JNIEnv *env, jobject self, jobject buffer_handle, jint attrib, jint value)
{
        PbufferInfo *Pbuffer_info = (PbufferInfo *)(*env)->GetDirectBufferAddress(env, buffer_handle);

	int attribs[3];

	attribs[0] = attrib;
	attribs[1] = value;
	attribs[2] = 0;

	wglSetPbufferAttribARB(Pbuffer_info->Pbuffer, attribs);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_bindTexImageToPbuffer
  (JNIEnv *env, jobject self, jobject buffer_handle, jint buffer)
{
        PbufferInfo *Pbuffer_info = (PbufferInfo *)(*env)->GetDirectBufferAddress(env, buffer_handle);
	wglBindTexImageARB(Pbuffer_info->Pbuffer, buffer);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_releaseTexImageFromPbuffer
  (JNIEnv *env, jobject self, jobject buffer_handle, jint buffer)
{
        PbufferInfo *Pbuffer_info = (PbufferInfo *)(*env)->GetDirectBufferAddress(env, buffer_handle);
	wglReleaseTexImageARB(Pbuffer_info->Pbuffer, buffer);
}
