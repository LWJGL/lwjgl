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

#include "common_tools.h"

typedef struct _PbufferInfo {

	HGLRC Pbuffer_context;

	HPBUFFERARB Pbuffer;

	HDC Pbuffer_dc;

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

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    nCreate
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Pbuffer_nCreate
  (JNIEnv *env, jclass clazz,
  jint width, jint height,
  jint bpp, jint alpha, jint depth, jint stencil,
  jint samples,
  jobject pixelFormatCaps, jint pixelFormatCapsSize, jobject pBufferAttribs, jint pBufferAttribsSize)
{
	int iPixelFormat;
	unsigned int num_formats_returned;
	int pixelAttribList[] = {WGL_DRAW_TO_PBUFFER_ARB, TRUE,
					     WGL_ACCELERATION_ARB, WGL_FULL_ACCELERATION_ARB,
						 WGL_PIXEL_TYPE_ARB, WGL_TYPE_RGBA_ARB,
						 WGL_DOUBLE_BUFFER_ARB, FALSE,
					     WGL_SUPPORT_OPENGL_ARB, TRUE,
					     WGL_COLOR_BITS_ARB, bpp,
					     WGL_ALPHA_BITS_ARB, alpha,
					     WGL_DEPTH_BITS_ARB, depth,
					     WGL_STENCIL_BITS_ARB, stencil,
						 0, 0, /* For ARB_multisample */
						 0, 0, /*                     */
						 0, 0, /* For WGL_ARB_render_texture */
						 0, 0, /*							 */
						 0};

	if (samples > 0 && extgl_Extensions.WGL_ARB_multisample) {
		pixelAttribList[18] = WGL_SAMPLE_BUFFERS_ARB;
		pixelAttribList[19] = 1;
		pixelAttribList[20] = WGL_SAMPLES_ARB;
		pixelAttribList[21] = samples;
	}

	if ( pixelFormatCaps != NULL ) {
		if ( !extgl_Extensions.WGL_ARB_render_texture ) {
			throwException(env, "The render-to-texture extension is not supported.");
			return (jint)NULL;
		}

		GLuint *pixelFormatCaps_ptr = (GLuint *)env->GetDirectBufferAddress(pixelFormatCaps);

		for ( int i = 0; i < pixelFormatCapsSize; )
			pixelAttribList[22 + i++] = pixelFormatCaps_ptr[i];
	}

	BOOL result = wglChoosePixelFormatARB(hdc, pixelAttribList, NULL, 1, &iPixelFormat, &num_formats_returned);
	if (result == FALSE || num_formats_returned < 1) {
		throwException(env, "Could not choose pixel formats.");
		return (jint)NULL;

	}

	HPBUFFERARB Pbuffer;

	if ( pBufferAttribs != NULL ) {
		GLuint *pBufferAttribs_ptr = (GLuint *)env->GetDirectBufferAddress(pBufferAttribs);

		int pBufferAttribList[9];

		int i;
		for ( i = 0; i < pBufferAttribsSize; )
			pBufferAttribList[i++] = pBufferAttribs_ptr[i];

		pBufferAttribList[i] = 0;

		Pbuffer = wglCreatePbufferARB(hdc, iPixelFormat, width, height, pBufferAttribList);
	} else {
		Pbuffer = wglCreatePbufferARB(hdc, iPixelFormat, width, height, NULL);
	}

	if (Pbuffer == NULL) {
		throwException(env, "Could not create Pbuffer.");
		return (jint)NULL;
	}

	HDC Pbuffer_dc = wglGetPbufferDCARB(Pbuffer);
	if (Pbuffer_dc == NULL) {
		wglDestroyPbufferARB(Pbuffer);
		throwException(env, "Could not get Pbuffer dc.");
		return (jint)NULL;
	}

	// Create a rendering context
	HGLRC Pbuffer_context = wglCreateContext(Pbuffer_dc);
	if (Pbuffer_context == NULL) {
		wglReleasePbufferDCARB(Pbuffer, Pbuffer_dc);
		wglDestroyPbufferARB(Pbuffer);
		throwException(env, "Failed to create Pbuffer rendering context");
		return (jint)NULL;
	}

	if (!wglShareLists(hglrc, Pbuffer_context)) {
		wglDeleteContext(Pbuffer_context);
		wglReleasePbufferDCARB(Pbuffer, Pbuffer_dc);
		wglDestroyPbufferARB(Pbuffer);
		throwException(env, "Could not share buffer context.");
		return (jint)NULL;

	}

	PbufferInfo *Pbuffer_info = (PbufferInfo *)malloc(sizeof(PbufferInfo));
	Pbuffer_info->Pbuffer = Pbuffer;
	Pbuffer_info->Pbuffer_context = Pbuffer_context;
	Pbuffer_info->Pbuffer_dc = Pbuffer_dc;

	return (jint)Pbuffer_info;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nReleaseContext
  (JNIEnv *env, jclass clazz)
{
	wglMakeCurrent(hdc, hglrc);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Pbuffer_nIsBufferLost
  (JNIEnv *env, jclass clazz, jint handle)
{
	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;
	BOOL buffer_lost;
	wglQueryPbufferARB(Pbuffer_info->Pbuffer, WGL_PBUFFER_LOST_ARB, &buffer_lost);
	return buffer_lost ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nMakeCurrent
  (JNIEnv *env, jclass clazz, jint handle)
{
	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;
	wglMakeCurrent(Pbuffer_info->Pbuffer_dc, Pbuffer_info->Pbuffer_context);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nDestroy
  (JNIEnv *env, jclass clazz, jint handle)
{
	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;
	wglDeleteContext(Pbuffer_info->Pbuffer_context);
	wglReleasePbufferDCARB(Pbuffer_info->Pbuffer, Pbuffer_info->Pbuffer_dc);
	wglDestroyPbufferARB(Pbuffer_info->Pbuffer);
	free(Pbuffer_info);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nSetAttrib
  (JNIEnv *env, jclass clazz, jint handle, jint attrib, jint value)
{
	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;

	int attribs[3];

	attribs[0] = attrib;
	attribs[1] = value;
	attribs[2] = 0;

	wglSetPbufferAttribARB(Pbuffer_info->Pbuffer, attribs);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nBindTexImage
  (JNIEnv *env, jclass clazz, jint handle, jint buffer)
{
	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;
	wglBindTexImageARB(Pbuffer_info->Pbuffer, buffer);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nReleaseTexImage
  (JNIEnv *env, jclass clazz, jint handle, jint buffer)
{
	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;
	wglReleaseTexImageARB(Pbuffer_info->Pbuffer, buffer);
}
