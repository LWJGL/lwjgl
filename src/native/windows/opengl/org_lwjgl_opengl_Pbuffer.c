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
 * Windows Pbuffer.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <stdlib.h>
#include "org_lwjgl_opengl_WindowsDisplay.h"
#include "org_lwjgl_opengl_Pbuffer.h"
#include "context.h"

#include "extgl.h"
#include "extgl_wgl.h"

#include "common_tools.h"

static bool isPbufferSupported(WGLExtensions *extensions) {
	return extensions->WGL_ARB_pixel_format && extensions->WGL_ARB_pbuffer;
}

static bool getExtensions(JNIEnv *env, WGLExtensions *extensions, jobject pixel_format, jobject pixelFormatCaps) {
	int origin_x = 0; int origin_y = 0;
	HWND dummy_hwnd;
	HDC dummy_hdc;
	HGLRC dummy_context;
	HDC saved_hdc;
	HGLRC saved_context;
	int pixel_format_id;

	dummy_hwnd = createDummyWindow(origin_x, origin_y);
	if (dummy_hwnd == NULL) {
		throwException(env, "Could not create dummy window");
		return false;
	}
	dummy_hdc = GetDC(dummy_hwnd);
	pixel_format_id = findPixelFormatOnDC(env, dummy_hdc, origin_x, origin_y, pixel_format, pixelFormatCaps, false, true, false, false);
	if (pixel_format_id == -1) {
		closeWindow(&dummy_hwnd, &dummy_hdc);
		return false;
	}
	if (!applyPixelFormat(env, dummy_hdc, pixel_format_id)) {
		closeWindow(&dummy_hwnd, &dummy_hdc);
		return false;
	}
	dummy_context = wglCreateContext(dummy_hdc);
	if (dummy_context == NULL) {
		closeWindow(&dummy_hwnd, &dummy_hdc);
		throwException(env, "Could not create dummy context");
		return false;
	}
	saved_hdc = wglGetCurrentDC();
	saved_context = wglGetCurrentContext();
	if (!wglMakeCurrent(dummy_hdc, dummy_context)) {
		wglMakeCurrent(saved_hdc, saved_context);
		closeWindow(&dummy_hwnd, &dummy_hdc);
		wglDeleteContext(dummy_context);
		throwException(env, "Could not make dummy context current");
		return false;
	}
	extgl_InitWGL(extensions);
	if (!wglMakeCurrent(saved_hdc, saved_context))
		printfDebugJava(env, "ERROR: Could not restore current context");
	closeWindow(&dummy_hwnd, &dummy_hdc);
	wglDeleteContext(dummy_context);
	return true;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nGetPbufferCapabilities
  (JNIEnv *env, jobject self, jobject pixel_format)
{
	int caps = 0;
	WGLExtensions extensions;
	if (!getExtensions(env, &extensions, pixel_format, NULL))
		return 0;
	if (isPbufferSupported(&extensions))
		caps |= org_lwjgl_opengl_Pbuffer_PBUFFER_SUPPORTED;

	if (extensions.WGL_ARB_render_texture)
		caps |= org_lwjgl_opengl_Pbuffer_RENDER_TEXTURE_SUPPORTED;

	if (extensions.WGL_NV_render_texture_rectangle)
		caps |= org_lwjgl_opengl_Pbuffer_RENDER_TEXTURE_RECTANGLE_SUPPORTED;

	if (extensions.WGL_NV_render_depth_texture)
		caps |= org_lwjgl_opengl_Pbuffer_RENDER_DEPTH_TEXTURE_SUPPORTED;

	return caps;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsPbufferPeerInfo_nCreate
  (JNIEnv *env, jobject self, jobject peer_info_handle,
  jint width, jint height, jobject pixel_format,
  jobject pixelFormatCaps, jobject pBufferAttribs)
{
	int origin_x = 0; int origin_y = 0;
	HWND dummy_hwnd;
	HDC dummy_hdc;
	HPBUFFERARB Pbuffer;
	HDC Pbuffer_dc;
	WGLExtensions extensions;
	const int *pBufferAttribs_ptr;
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	int pixel_format_id;

	if ( pBufferAttribs != NULL ) {
		pBufferAttribs_ptr = (const int *)(*env)->GetDirectBufferAddress(env, pBufferAttribs);
	} else {
		pBufferAttribs_ptr = NULL;
	}
	if (!getExtensions(env, &extensions, pixel_format, pixelFormatCaps))
		return;
	dummy_hwnd = createDummyWindow(origin_x, origin_y);
	if (dummy_hwnd == NULL) {
		throwException(env, "Could not create dummy window");
		return;
	}
	dummy_hdc = GetDC(dummy_hwnd);
	pixel_format_id = findPixelFormatOnDC(env, dummy_hdc, origin_x, origin_y, pixel_format, pixelFormatCaps, false, false, true, false);
	if (pixel_format_id == -1) {
		closeWindow(&dummy_hwnd, &dummy_hdc);
		return;
	}
	Pbuffer = extensions.wglCreatePbufferARB(dummy_hdc, pixel_format_id, width, height, pBufferAttribs_ptr);
	closeWindow(&dummy_hwnd, &dummy_hdc);
	if (Pbuffer == NULL) {
		throwException(env, "Could not create Pbuffer");
		return;
	}
	Pbuffer_dc = extensions.wglGetPbufferDCARB(Pbuffer);
	if (Pbuffer_dc == NULL) {
		extensions.wglDestroyPbufferARB(Pbuffer);
		throwException(env, "Could not get Pbuffer DC");
		return;
	}
	peer_info->extensions = extensions;
	peer_info->u.pbuffer = Pbuffer;
	peer_info->drawable_hdc = Pbuffer_dc;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsPbufferPeerInfo_nDestroy
  (JNIEnv *env, jclass clazz, jobject peer_info_handle) {
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	peer_info->extensions.wglReleasePbufferDCARB(peer_info->u.pbuffer, peer_info->drawable_hdc);
	peer_info->extensions.wglDestroyPbufferARB(peer_info->u.pbuffer);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_WindowsPbufferPeerInfo_nIsBufferLost
  (JNIEnv *env, jclass clazz, jobject peer_info_handle) {
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	BOOL buffer_lost;
	peer_info->extensions.wglQueryPbufferARB(peer_info->u.pbuffer, WGL_PBUFFER_LOST_ARB, &buffer_lost);
	return buffer_lost ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsPbufferPeerInfo_nSetPbufferAttrib
  (JNIEnv *env, jclass clazz, jobject peer_info_handle, jint attrib, jint value) {
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	int attribs[3];

	attribs[0] = attrib;
	attribs[1] = value;
	attribs[2] = 0;

	peer_info->extensions.wglSetPbufferAttribARB(peer_info->u.pbuffer, attribs);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsPbufferPeerInfo_nBindTexImageToPbuffer
  (JNIEnv *env, jclass clazz, jobject peer_info_handle, jint buffer) {
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	peer_info->extensions.wglBindTexImageARB(peer_info->u.pbuffer, buffer);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsPbufferPeerInfo_nReleaseTexImageFromPbuffer
  (JNIEnv *env, jclass clazz, jobject peer_info_handle, jint buffer) {
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	peer_info->extensions.wglReleaseTexImageARB(peer_info->u.pbuffer, buffer);
}
