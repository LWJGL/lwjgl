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
 * Mac OS X Pbuffer.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include "org_lwjgl_opengl_Pbuffer.h"
#include "extgl.h"
#include <OpenGL/OpenGL.h>
#include "common_tools.h"

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Pbuffer_nIsBufferLost(JNIEnv *env, jclass clazz, jint handle) {
	return JNI_FALSE;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Pbuffer_getPbufferCaps(JNIEnv *env, jclass clazz) {
/*	CGLRendererInfoObj renderers;
	long num_renderers;
	CGLError err = CGLQueryRendererInfo(~0L, &renderers, &num_renderers);
	if (err)
		return 0;
	int result = 0;
	for (long i = 0; i < num_renderers; i++) {
		long pbuffer_supported;
		err = CGLDescribeRenderer(renderers, i, kCGLRPOffScreen, &pbuffer_supported);
		if (!err && pbuffer_supported == true)
			result = org_lwjgl_opengl_Pbuffer_PBUFFER_SUPPORTED;
	}
	CGLDestroyRendererInfo(renderers);
	return result;*/
	return 0;
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
/*	CGLPixelFormatObj pixel_format;
	CGLContextObj context;
	int dummy;
	CGLPixelFormatAttribute attribs[] = {kCGLPFAOffScreen,
					     kCGLPFAMinimumPolicy,
					     kCGLPFAColorSize,
					     (CGLPixelFormatAttribute)bpp,
					     kCGLPFAAlphaSize,
					     (CGLPixelFormatAttribute)alpha,
					     kCGLPFADepthSize,
					     (CGLPixelFormatAttribute)depth,
					     kCGLPFAStencilSize,
					     (CGLPixelFormatAttribute)stencil,
					     (CGLPixelFormatAttribute)NULL};
	CGLChoosePixelFormat(attribs, &pixel_format, &dummy);
	if (pixel_format == NULL) {
		throwException(env, "Could not find matching pixel format");
		return -1;
	}
	CGLCreateContext(pixel_format, NULL, &context);
	CGLDestroyPixelFormat(pixel_format);
	if (context == NULL) {
		throwException(env, "Could not create offscreen context");
		return -1;
	}
	CGLSetOffscreen(*/
	return -1;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nReleaseContext(JNIEnv *env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nMakeCurrent(JNIEnv *env, jclass clazz, jint handle) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nDestroy(JNIEnv *env, jclass clazz, jint handle) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nSetAttrib
  (JNIEnv *env, jclass clazz, jint handle, jint attrib, jint value)
{
	throwException(env, "The render-to-texture extension is not supported.");
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nBindTexImage
  (JNIEnv *env, jclass clazz, jint handle, jint buffer)
{
	throwException(env, "The render-to-texture extension is not supported.");
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nReleaseTexImage
  (JNIEnv *env, jclass clazz, jint handle, jint buffer)
{
	throwException(env, "The render-to-texture extension is not supported.");
}