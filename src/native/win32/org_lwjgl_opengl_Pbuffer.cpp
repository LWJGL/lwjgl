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
#include "org_lwjgl_opengl_Pbuffer.h"
#include "Window.h"

#include "extgl.h"
#include "extgl_wgl.h"

#include "common_tools.h"

typedef struct _PbufferInfo {
	HGLRC Pbuffer_context;
	HPBUFFERARB Pbuffer;
	HDC Pbuffer_dc;
	bool use_display_context;
} PbufferInfo;

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    isPbufferSupported
 * Signature: ()Z
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Pbuffer_getPbufferCaps
  (JNIEnv *env, jclass clazz)
{
	int caps = 0;
	if ( extgl_Extensions.WGL_ARB_pixel_format && extgl_Extensions.WGL_ARB_pbuffer )
		caps |= org_lwjgl_opengl_Pbuffer_PBUFFER_SUPPORTED;

	if ( extgl_Extensions.WGL_ARB_render_texture )
		caps |= org_lwjgl_opengl_Pbuffer_RENDER_TEXTURE_SUPPORTED;

	if ( extgl_Extensions.WGL_NV_render_texture_rectangle )
		caps |= org_lwjgl_opengl_Pbuffer_RENDER_TEXTURE_RECTANGLE_SUPPORTED;

	if ( extgl_Extensions.WGL_NV_render_depth_texture )
		caps |= org_lwjgl_opengl_Pbuffer_RENDER_DEPTH_TEXTURE_SUPPORTED;

	return caps;
}

static HPBUFFERARB createPbuffer(JNIEnv *env, int width, int height, jobject pixel_format, jobject pixelFormatCaps, const int *pBufferAttribs_ptr) {
	HWND dummy_hwnd = createWindow(1, 1, false, false);
	if (dummy_hwnd == NULL) {
		throwException(env, "Could not create dummy window");
		return NULL;
	}
	HDC dummy_hdc = GetDC(dummy_hwnd);
	int iPixelFormat = findPixelFormat(env, dummy_hdc, pixel_format);
	if (iPixelFormat == -1) {
		return NULL;
	}
	if (!applyPixelFormat(dummy_hdc, iPixelFormat)) {
		closeWindow(dummy_hwnd, dummy_hdc);
		throwException(env, "Could not apply pixel format to window");
		return NULL;
	}
	
	HGLRC dummy_hglrc = wglCreateContext(dummy_hdc);
	if (dummy_hglrc == NULL) {
		closeWindow(dummy_hwnd, dummy_hdc);
		throwException(env, "Failed to create OpenGL rendering context");
		return NULL;
	}
	BOOL result = wglMakeCurrent(dummy_hdc, dummy_hglrc);
	if (!result) {
		wglDeleteContext(dummy_hglrc);
		closeWindow(dummy_hwnd, dummy_hdc);
		throwException(env, "Could not bind context to dummy window");
		return NULL;
	}
	extgl_InitWGL(env);

	iPixelFormat = findPixelFormatARB(env, dummy_hdc, pixel_format, pixelFormatCaps, false, false, true, false);
	if (iPixelFormat == -1)
		iPixelFormat = findPixelFormatARB(env, dummy_hdc, pixel_format, pixelFormatCaps, false, false, true, true);
	wglDeleteContext(dummy_hglrc);
	if (iPixelFormat == -1) {
		closeWindow(dummy_hwnd, dummy_hdc);
		throwException(env, "Could not find suitable pixel format.");
		return NULL;
	}
	HPBUFFERARB Pbuffer = wglCreatePbufferARB(dummy_hdc, iPixelFormat, width, height, pBufferAttribs_ptr);
	closeWindow(dummy_hwnd, dummy_hdc);
	return Pbuffer;
}

static HGLRC createPbufferContext(JNIEnv *env, HDC Pbuffer_dc) {
	HGLRC Pbuffer_context = wglCreateContext(Pbuffer_dc);
	if (Pbuffer_context == NULL) {
		throwException(env, "Failed to create Pbuffer rendering context");
		return NULL;
	}
	if (getCurrentContext() != NULL && !wglShareLists(getCurrentContext(), Pbuffer_context)) {
		wglDeleteContext(Pbuffer_context);
		throwException(env, "Could not share buffer context.");
		return NULL;
	}
	return Pbuffer_context;
}

static bool formatMatches(HDC hdc, int pixel_format1, int pixel_format2, int attribute) {
	int format_val1;
	int format_val2;
	if (wglGetPixelFormatAttribivARB(hdc, pixel_format1, 0, 1, &attribute, &format_val1) != TRUE)
		return false;
	if (wglGetPixelFormatAttribivARB(hdc, pixel_format2, 0, 1, &attribute, &format_val2) != TRUE)
		return false;
	return format_val1 == format_val2;
}

static int chooseSingleBufferedFormatFromFormat(HDC hdc, int orig_pixel_format) {
	int max_pixel_format_index = DescribePixelFormat(hdc, 1, sizeof(PIXELFORMATDESCRIPTOR), NULL);
	for (int i = 1; i <= max_pixel_format_index; i++) {
		int attribute = WGL_DOUBLE_BUFFER_ARB;
		int double_buffer;
		if (wglGetPixelFormatAttribivARB(hdc, i, 0, 1, &attribute, &double_buffer) != TRUE) {
			return -1;
		}
		attribute = WGL_DRAW_TO_PBUFFER_ARB;
		int draw_to_pbuffer;
		if (wglGetPixelFormatAttribivARB(hdc, i, 0, 1, &attribute, &draw_to_pbuffer) != TRUE) {
			return -1;
		}
		if (double_buffer != FALSE || draw_to_pbuffer != TRUE)
			continue;
		if (formatMatches(hdc, i, orig_pixel_format, WGL_RED_BITS_ARB) &&
			formatMatches(hdc, i, orig_pixel_format, WGL_GREEN_BITS_ARB) &&
			formatMatches(hdc, i, orig_pixel_format, WGL_BLUE_BITS_ARB) &&
			formatMatches(hdc, i, orig_pixel_format, WGL_ALPHA_BITS_ARB) &&
			formatMatches(hdc, i, orig_pixel_format, WGL_ACCUM_RED_BITS_ARB) &&
			formatMatches(hdc, i, orig_pixel_format, WGL_ACCUM_GREEN_BITS_ARB) &&
			formatMatches(hdc, i, orig_pixel_format, WGL_ACCUM_BLUE_BITS_ARB) &&
			formatMatches(hdc, i, orig_pixel_format, WGL_ACCUM_ALPHA_BITS_ARB) &&
			formatMatches(hdc, i, orig_pixel_format, WGL_DEPTH_BITS_ARB) &&
			formatMatches(hdc, i, orig_pixel_format, WGL_STENCIL_BITS_ARB) &&
			formatMatches(hdc, i, orig_pixel_format, WGL_ACCELERATION_ARB) &&
			formatMatches(hdc, i, orig_pixel_format, WGL_SUPPORT_OPENGL_ARB) &&
			formatMatches(hdc, i, orig_pixel_format, WGL_STEREO_ARB) &&
			formatMatches(hdc, i, orig_pixel_format, WGL_AUX_BUFFERS_ARB) &&
			formatMatches(hdc, i, orig_pixel_format, WGL_PIXEL_TYPE_ARB) &&
			(!extgl_Extensions.WGL_ARB_multisample || (formatMatches(hdc, i, orig_pixel_format, WGL_SAMPLE_BUFFERS_ARB) &&
													   formatMatches(hdc, i, orig_pixel_format, WGL_SAMPLES_ARB)))) {
			return i;
		}
	}
	return -1;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nCreate
  (JNIEnv *env, jclass clazz, jobject buffer_handle, jboolean use_display_context,
  jint width, jint height, jobject pixel_format,
  jobject pixelFormatCaps, jobject pBufferAttribs)
{
	if (env->GetDirectBufferCapacity(buffer_handle) < sizeof(PbufferInfo)) {
		throwException(env, "Buffer handle not large enough");
		return;
	}
	HPBUFFERARB Pbuffer;
	const int *pBufferAttribs_ptr;
	if ( pBufferAttribs != NULL ) {
		pBufferAttribs_ptr = (const int *)env->GetDirectBufferAddress(pBufferAttribs);
	} else {
		pBufferAttribs_ptr = NULL;
	}
	if (use_display_context) {
		int iPixelFormat = chooseSingleBufferedFormatFromFormat(getCurrentWindowDC(), getCurrentPixelFormat());
		if (iPixelFormat == -1)
			iPixelFormat = getCurrentPixelFormat();
		Pbuffer = wglCreatePbufferARB(getCurrentWindowDC(), iPixelFormat, width, height, pBufferAttribs_ptr);
	} else {
		Pbuffer = createPbuffer(env, width, height, pixel_format, pixelFormatCaps, pBufferAttribs_ptr);
	}

	if (Pbuffer == NULL) {
		throwException(env, "Could not create Pbuffer.");
		return;
	}

	HDC Pbuffer_dc = wglGetPbufferDCARB(Pbuffer);
	if (Pbuffer_dc == NULL) {
		wglDestroyPbufferARB(Pbuffer);
		throwException(env, "Could not get Pbuffer dc.");
		return;
	}
	HGLRC Pbuffer_context;
	if (use_display_context) {
		Pbuffer_context = getCurrentContext();
	} else {
		Pbuffer_context = createPbufferContext(env, Pbuffer_dc);
		if (Pbuffer_context == NULL) {
			wglReleasePbufferDCARB(Pbuffer, Pbuffer_dc);
			wglDestroyPbufferARB(Pbuffer);
			return;
		}
	}

	PbufferInfo *Pbuffer_info = (PbufferInfo *)env->GetDirectBufferAddress(buffer_handle);
//	PbufferInfo *Pbuffer_info = (PbufferInfo *)malloc(sizeof(PbufferInfo));
	Pbuffer_info->Pbuffer = Pbuffer;
	Pbuffer_info->Pbuffer_context = Pbuffer_context;
	Pbuffer_info->Pbuffer_dc = Pbuffer_dc;
	Pbuffer_info->use_display_context = use_display_context == JNI_TRUE;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Pbuffer_nIsBufferLost
  (JNIEnv *env, jclass clazz, jobject buffer_handle)
{
	PbufferInfo *Pbuffer_info = (PbufferInfo *)env->GetDirectBufferAddress(buffer_handle);
//	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;
	BOOL buffer_lost;
	wglQueryPbufferARB(Pbuffer_info->Pbuffer, WGL_PBUFFER_LOST_ARB, &buffer_lost);
	return buffer_lost ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nMakeCurrent
  (JNIEnv *env, jclass clazz, jobject buffer_handle)
{
	PbufferInfo *Pbuffer_info = (PbufferInfo *)env->GetDirectBufferAddress(buffer_handle);
//	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;
	if (!wglMakeCurrent(Pbuffer_info->Pbuffer_dc, Pbuffer_info->Pbuffer_context))
		throwException(env, "Could not make pbuffer context current");
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nDestroy
  (JNIEnv *env, jclass clazz, jobject buffer_handle)
{
	PbufferInfo *Pbuffer_info = (PbufferInfo *)env->GetDirectBufferAddress(buffer_handle);
//	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;
	if (!Pbuffer_info->use_display_context)
		wglDeleteContext(Pbuffer_info->Pbuffer_context);
	wglReleasePbufferDCARB(Pbuffer_info->Pbuffer, Pbuffer_info->Pbuffer_dc);
	wglDestroyPbufferARB(Pbuffer_info->Pbuffer);
//	free(Pbuffer_info);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nSetAttrib
  (JNIEnv *env, jclass clazz, jobject buffer_handle, jint attrib, jint value)
{
	PbufferInfo *Pbuffer_info = (PbufferInfo *)env->GetDirectBufferAddress(buffer_handle);
//	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;

	int attribs[3];

	attribs[0] = attrib;
	attribs[1] = value;
	attribs[2] = 0;

	wglSetPbufferAttribARB(Pbuffer_info->Pbuffer, attribs);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nBindTexImage
  (JNIEnv *env, jclass clazz, jobject buffer_handle, jint buffer)
{
	PbufferInfo *Pbuffer_info = (PbufferInfo *)env->GetDirectBufferAddress(buffer_handle);
//	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;
	wglBindTexImageARB(Pbuffer_info->Pbuffer, buffer);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nReleaseTexImage
  (JNIEnv *env, jclass clazz, jobject buffer_handle, jint buffer)
{
	PbufferInfo *Pbuffer_info = (PbufferInfo *)env->GetDirectBufferAddress(buffer_handle);
//	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;
	wglReleaseTexImageARB(Pbuffer_info->Pbuffer, buffer);
}
