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
#include "context.h"

#include "extgl.h"
#include "extgl_wgl.h"

#include "common_tools.h"

typedef struct _PbufferInfo {
	HGLRC Pbuffer_context;
	HPBUFFERARB Pbuffer;
	HDC Pbuffer_dc;
} PbufferInfo;

static bool isPbuffersSupported() {
	return extension_flags.WGL_ARB_pixel_format && extension_flags.WGL_ARB_pbuffer;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Win32Display_getPbufferCapabilities
  (JNIEnv *env, jobject self)
{
	int caps = 0;
	if (isPbuffersSupported())
		caps |= org_lwjgl_opengl_Pbuffer_PBUFFER_SUPPORTED;

	if (extension_flags.WGL_ARB_render_texture)
		caps |= org_lwjgl_opengl_Pbuffer_RENDER_TEXTURE_SUPPORTED;

	if (extension_flags.WGL_NV_render_texture_rectangle)
		caps |= org_lwjgl_opengl_Pbuffer_RENDER_TEXTURE_RECTANGLE_SUPPORTED;

	if (extension_flags.WGL_NV_render_depth_texture)
		caps |= org_lwjgl_opengl_Pbuffer_RENDER_DEPTH_TEXTURE_SUPPORTED;

	return caps;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32PbufferPeerInfo_nCreate
  (JNIEnv *env, jobject self, jobject peer_info_handle,
  jint width, jint height, jobject pixel_format,
  jobject pixelFormatCaps, jobject pBufferAttribs)
{
	int origin_x = 0; int origin_y = 0;
	HWND dummy_hwnd;
	HDC dummy_hdc;
	HPBUFFERARB Pbuffer;
	HDC Pbuffer_dc;
	const int *pBufferAttribs_ptr;
	Win32PeerInfo *peer_info = (Win32PeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	int pixel_format_id;
	
	if ( pBufferAttribs != NULL ) {
		pBufferAttribs_ptr = (const int *)(*env)->GetDirectBufferAddress(env, pBufferAttribs);
	} else {
		pBufferAttribs_ptr = NULL;
	}
	pixel_format_id = findPixelFormat(env, origin_x, origin_y, pixel_format, pixelFormatCaps, false, false, true, false);

	dummy_hwnd = createDummyWindow(origin_x, origin_y);
	if (dummy_hwnd == NULL) {
		throwException(env, "Could not create dummy window");
		return;
	}
	dummy_hdc = GetDC(dummy_hwnd);
	if (!applyPixelFormat(dummy_hdc, pixel_format_id)) {
		closeWindow(&dummy_hwnd, &dummy_hdc);
		throwException(env, "Could not apply pixel format");
		return;
	}
	Pbuffer = wglCreatePbufferARB(dummy_hdc, pixel_format_id, width, height, pBufferAttribs_ptr);
	closeWindow(&dummy_hwnd, &dummy_hdc);
	if (Pbuffer == NULL) {
		throwException(env, "Could not create Pbuffer");
		return;
	}
	Pbuffer_dc = wglGetPbufferDCARB(Pbuffer);
	if (Pbuffer_dc == NULL) {
		wglDestroyPbufferARB(Pbuffer);
		throwException(env, "Could not get Pbuffer DC");
		return;
	}
	peer_info->format_hdc = Pbuffer_dc;
	peer_info->pbuffer = Pbuffer;
	peer_info->drawable_hdc = Pbuffer_dc;

}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32PbufferPeerInfo_nDestroy
  (JNIEnv *env, jclass clazz, jobject peer_info_handle) {
	Win32PeerInfo *peer_info = (Win32PeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	wglReleasePbufferDCARB(peer_info->pbuffer, peer_info->drawable_hdc);
	wglDestroyPbufferARB(peer_info->pbuffer);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Win32PbufferPeerInfo_nIsBufferLost
  (JNIEnv *env, jclass clazz, jobject peer_info_handle) {
	Win32PeerInfo *peer_info = (Win32PeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	BOOL buffer_lost;
	wglQueryPbufferARB(peer_info->pbuffer, WGL_PBUFFER_LOST_ARB, &buffer_lost);
	return buffer_lost ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32PbufferPeerInfo_nSetPbufferAttrib
  (JNIEnv *env, jclass clazz, jobject peer_info_handle, jint attrib, jint value) {
	Win32PeerInfo *peer_info = (Win32PeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	int attribs[3];

	attribs[0] = attrib;
	attribs[1] = value;
	attribs[2] = 0;

	wglSetPbufferAttribARB(peer_info->pbuffer, attribs);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32PbufferPeerInfo_nBindTexImageToPbuffer
  (JNIEnv *env, jclass clazz, jobject peer_info_handle, jint buffer) {
	Win32PeerInfo *peer_info = (Win32PeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	wglBindTexImageARB(peer_info->pbuffer, buffer);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32PbufferPeerInfo_nReleaseTexImageFromPbuffer
  (JNIEnv *env, jclass clazz, jobject peer_info_handle, jint buffer) {
	Win32PeerInfo *peer_info = (Win32PeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	wglReleaseTexImageARB(peer_info->pbuffer, buffer);
}
