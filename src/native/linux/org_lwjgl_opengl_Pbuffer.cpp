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
 * Linux Pbuffer.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <stdlib.h>
#include "org_lwjgl_opengl_Pbuffer.h"
#include "extgl.h"
#include "Window.h"
#include "common_tools.h"

typedef struct _PbufferInfo {
	GLXPbuffer buffer;
	GLXContext context;
} PbufferInfo;

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    nIsBufferLost
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Pbuffer_nIsBufferLost
  (JNIEnv *env, jclass clazz, jint handle)
{
	// The buffer is never lost, because of the GLX_PRESERVED_CONTENTS flag
	return JNI_FALSE;
}

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    isPbufferSupported
 * Signature: ()Z
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Pbuffer_getPbufferCaps
  (JNIEnv *env, jclass clazz)
{
	// Only support thw GLX 1.3 Pbuffers and ignore the GLX_SGIX_pbuffer extension
	return extgl_Extensions.GLX13 ? org_lwjgl_opengl_Pbuffer_PBUFFER_SUPPORTED : 0;
}

static void destroyPbuffer(PbufferInfo *buffer_info) {
	GLXPbuffer buffer = buffer_info->buffer;
	GLXContext context = buffer_info->context;
	glXDestroyPbuffer(getDisplay(), buffer);
	glXDestroyContext(getDisplay(), context);
	free(buffer_info);
	decDisplay();
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
	Display *disp = incDisplay(env);
	if (disp == NULL)
		return 0;
	int bpe = convertToBPE(bpp);
	int attrib_list[] = {GLX_RENDER_TYPE, GLX_RGBA_BIT,
				   GLX_DOUBLEBUFFER, False,
				   GLX_RED_SIZE, bpe,
				   GLX_GREEN_SIZE, bpe,
				   GLX_BLUE_SIZE, bpe,
				   GLX_ALPHA_SIZE, alpha,
				   GLX_DEPTH_SIZE, depth,
				   GLX_STENCIL_SIZE, stencil,
				   GLX_DRAWABLE_TYPE, GLX_PBUFFER_BIT,
				   None, None, /* for ARB_multisample */
				   None, None, /*                     */
				   None};
	int num_configs;
	if (samples > 0 && extgl_Extensions.GLX_ARB_multisample) {
		attrib_list[18] = GLX_SAMPLE_BUFFERS_ARB;
		attrib_list[19] = 1;
		attrib_list[20] = GLX_SAMPLES_ARB;
		attrib_list[21] = samples;
	}
	GLXFBConfig *configs = glXChooseFBConfig(disp, getCurrentScreen(), attrib_list, &num_configs);
	if (num_configs == 0) {
		XFree(configs);
		throwException(env, "No matching pixel format");
		return -1;
	}
	int max;
	glXGetFBConfigAttrib(disp, configs[0], GLX_MAX_PBUFFER_WIDTH, &max);
	if (max < width) {
		XFree(configs);
		throwException(env, "Width too large");
		return -1;
	}
	glXGetFBConfigAttrib(disp, configs[0], GLX_MAX_PBUFFER_HEIGHT, &max);
	if (max < height) {
		XFree(configs);
		throwException(env, "Height too large");
		return -1;
	}
        GLXContext context = glXCreateNewContext(disp, configs[0], GLX_RGBA_TYPE, getCurrentContext(), True);
        if (context == NULL) {
                XFree(configs);
                throwException(env, "Could not create a GLX context");
                return -1;
        }
	jboolean allow_software_acceleration = getBooleanProperty(env, "org.lwjgl.opengl.Window.allowSoftwareOpenGL");
        if (!allow_software_acceleration && glXIsDirect(disp, context) == False) {
                glXDestroyContext(disp, context);
                XFree(configs);
                throwException(env, "Could not create a direct GLX context");
                return -1;
        }
	const int buffer_attribs[] = {GLX_PBUFFER_WIDTH, width,
				      GLX_PBUFFER_HEIGHT, height,
				      GLX_PRESERVED_CONTENTS, True,
				      GLX_LARGEST_PBUFFER, False};

	GLXPbuffer buffer = glXCreatePbuffer(disp, configs[0], buffer_attribs);
	XFree(configs);
	PbufferInfo *buffer_info = (PbufferInfo *)malloc(sizeof(PbufferInfo));
	buffer_info->buffer = buffer;
	buffer_info->context = context;
	if (!checkXError(env)) {
		destroyPbuffer(buffer_info);
		return -1;
	}
	return (jint)buffer_info;
}

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    nMakeCurrent
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nMakeCurrent
  (JNIEnv *env, jclass clazz, jint handle)
{
	PbufferInfo *buffer_info = (PbufferInfo *)handle;
	GLXPbuffer buffer = buffer_info->buffer;
	GLXContext context = buffer_info->context;
	if (glXMakeContextCurrent(getDisplay(), buffer, buffer, context) == False) {
		printfDebug("Could not make pbuffer current");
	}
}

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    nDestroyGL
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nDestroy
  (JNIEnv *env, jclass clazz, jint handle)
{
	PbufferInfo *buffer_info = (PbufferInfo *)handle;
	destroyPbuffer(buffer_info);
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
