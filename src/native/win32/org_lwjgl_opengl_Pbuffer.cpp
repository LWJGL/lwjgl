/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
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
	return extgl_Extensions.WGL_ARB_pbuffer ? org_lwjgl_opengl_Pbuffer_PBUFFER_SUPPORTED : 0;
}

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    nCreate
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Pbuffer_nCreate
  (JNIEnv *env, jclass clazz, jint width, jint height, jint bpp, jint alpha, jint depth, jint stencil, jint samples)
{
	int iPixelFormat;
	unsigned int num_formats_returned;
	int attrib_list[] = {WGL_DRAW_TO_PBUFFER_ARB, TRUE,
					     WGL_ACCELERATION_ARB, WGL_FULL_ACCELERATION_ARB,
						 WGL_PIXEL_TYPE_ARB, WGL_TYPE_RGBA_ARB,
						 WGL_DOUBLE_BUFFER_ARB, FALSE,
					     WGL_SUPPORT_OPENGL_ARB, TRUE,
					     WGL_COLOR_BITS_ARB, bpp,
					     WGL_ALPHA_BITS_ARB, alpha,
					     WGL_DEPTH_BITS_ARB, depth,
					     WGL_STENCIL_BITS_ARB, stencil,
						 0};
	BOOL result = wglChoosePixelFormatARB(hdc, attrib_list, NULL, 1, &iPixelFormat, &num_formats_returned);
	if (result == FALSE) {
		throwException(env, "Could not choose pixel formats.");
		return (jint)NULL;
	}
	HPBUFFERARB Pbuffer = wglCreatePbufferARB(hdc, iPixelFormat, width, height, NULL);
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

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    nReleaseContext
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nReleaseContext
  (JNIEnv *env, jclass clazz)
{
	wglMakeCurrent(hdc, hglrc);
}

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    nIsBufferLost
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Pbuffer_nIsBufferLost
  (JNIEnv *env, jclass clazz, jint handle)
{
	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;
	BOOL buffer_lost;
	wglQueryPbufferARB(Pbuffer_info->Pbuffer, WGL_PBUFFER_LOST_ARB, &buffer_lost);
	return buffer_lost ? JNI_TRUE : JNI_FALSE;
}

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    nMakeCurrent
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nMakeCurrent
  (JNIEnv *env, jclass clazz, jint handle)
{
	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;
	wglMakeCurrent(Pbuffer_info->Pbuffer_dc, Pbuffer_info->Pbuffer_context);
}

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    nDestroyGL
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nDestroy
  (JNIEnv *env, jclass clazz, jint handle)
{
	PbufferInfo *Pbuffer_info = (PbufferInfo *)handle;
	wglDeleteContext(Pbuffer_info->Pbuffer_context);
	wglReleasePbufferDCARB(Pbuffer_info->Pbuffer, Pbuffer_info->Pbuffer_dc);
	wglDestroyPbufferARB(Pbuffer_info->Pbuffer);
	free(Pbuffer_info);
}
