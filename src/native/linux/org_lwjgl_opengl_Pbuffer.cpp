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
	bool use_display_context;
} PbufferInfo;

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Pbuffer_nIsBufferLost
  (JNIEnv *env, jclass clazz, jobject handle_buffer)
{
	// The buffer is never lost, because of the GLX_PRESERVED_CONTENTS flag
	return JNI_FALSE;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Pbuffer_getPbufferCaps
  (JNIEnv *env, jclass clazz)
{
	// Only support the GLX 1.3 Pbuffers and ignore the GLX_SGIX_pbuffer extension
	return extgl_Extensions.GLX13 ? org_lwjgl_opengl_Pbuffer_PBUFFER_SUPPORTED : 0;
}

static void destroyPbuffer(PbufferInfo *buffer_info) {
	GLXPbuffer buffer = buffer_info->buffer;
	GLXContext context = buffer_info->context;
	glXDestroyPbuffer(getDisplay(), buffer);
	if (!buffer_info->use_display_context)
		glXDestroyContext(getDisplay(), context);
//	free(buffer_info);
	decDisplay();
}

static bool checkPbufferCaps(JNIEnv *env, GLXFBConfig config, int width, int height) {
	int max;
	int result = glXGetFBConfigAttrib(getDisplay(), config, GLX_MAX_PBUFFER_WIDTH, &max);
	if (result != Success) {
		throwException(env, "Could not get GLX_MAX_PBUFFER_WIDTH from configuration");
		return false;
	}
	if (max < width) {
		throwException(env, "Width too large");
		return false;
	}
	result = glXGetFBConfigAttrib(getDisplay(), config, GLX_MAX_PBUFFER_HEIGHT, &max);
	if (result != Success) {
		throwException(env, "Could not get GLX_MAX_PBUFFER_WIDTH from configuration");
		return false;
	}
	if (max < height) {
		throwException(env, "Height too large");
		return false;
	}
	return true;
}

static bool createPbufferUsingUniqueContext(JNIEnv *env, PbufferInfo *pbuffer_info, jobject pixel_format, int width, int height, const int *buffer_attribs) {
	GLXFBConfig *configs = chooseVisualGLX13(env, pixel_format, false, GLX_PBUFFER_BIT, false);
	if (configs == NULL) {
		GLXFBConfig *configs = chooseVisualGLX13(env, pixel_format, false, GLX_PBUFFER_BIT, true);
		if (configs == NULL) {
			throwException(env, "No matching pixel format");
			return false;
		}
	}
	if (!checkPbufferCaps(env, configs[0], width, height)) {
		XFree(configs);
		return false;
	}
	GLXContext context = glXCreateNewContext(getDisplay(), configs[0], GLX_RGBA_TYPE, getCurrentGLXContext(), True);
	if (context == NULL) {
		XFree(configs);
		throwException(env, "Could not create a GLX context");
		return false;
	}
	jboolean allow_software_acceleration = getBooleanProperty(env, "org.lwjgl.opengl.Window.allowSoftwareOpenGL");
	if (!allow_software_acceleration && glXIsDirect(getDisplay(), context) == False) {
		glXDestroyContext(getDisplay(), context);
		XFree(configs);
		throwException(env, "Could not create a direct GLX context");
		return false;
	}
	GLXPbuffer buffer = glXCreatePbuffer(getDisplay(), configs[0], buffer_attribs);
	XFree(configs);
	pbuffer_info->context = context;
	pbuffer_info->buffer = buffer;
	return true;
}

static bool configMatches(const GLXFBConfig config1, const GLXFBConfig config2, int glx_val) {
	int config_val1;
	int config_val2;
	if (glXGetFBConfigAttrib(getDisplay(), config1, glx_val, &config_val1) != Success)
		return false;
	if (glXGetFBConfigAttrib(getDisplay(), config2, glx_val, &config_val2) != Success)
		return false;
	return config_val1 == config_val2;
}

static GLXFBConfig chooseSingleBufferedConfigFromConfig(const GLXFBConfig orig_config) {
	int num_elements;
	GLXFBConfig *configs = glXGetFBConfigs(getDisplay(), getCurrentScreen(), &num_elements);
	for (int i = 0; i < num_elements; i++) {
		GLXFBConfig config = configs[i];
		int double_buffer;
		int drawable_type;
		if (glXGetFBConfigAttrib(getDisplay(), config, GLX_DOUBLEBUFFER, &double_buffer) != Success)
			return NULL;
		if (glXGetFBConfigAttrib(getDisplay(), config, GLX_DRAWABLE_TYPE, &drawable_type) != Success)
			return NULL;
		if (double_buffer != False || (drawable_type & GLX_PBUFFER_BIT == 0))
			continue;
		if (configMatches(config, orig_config, GLX_RED_SIZE) &&
			configMatches(config, orig_config, GLX_GREEN_SIZE) &&
			configMatches(config, orig_config, GLX_BLUE_SIZE) &&
			configMatches(config, orig_config, GLX_ALPHA_SIZE) &&
			configMatches(config, orig_config, GLX_DEPTH_SIZE) &&
			configMatches(config, orig_config, GLX_STENCIL_SIZE) &&
			configMatches(config, orig_config, GLX_STEREO) &&
			configMatches(config, orig_config, GLX_AUX_BUFFERS) &&
			configMatches(config, orig_config, GLX_ACCUM_RED_SIZE) &&
			configMatches(config, orig_config, GLX_ACCUM_GREEN_SIZE) &&
			configMatches(config, orig_config, GLX_ACCUM_BLUE_SIZE) &&
			configMatches(config, orig_config, GLX_ACCUM_ALPHA_SIZE) &&
			configMatches(config, orig_config, GLX_RENDER_TYPE) &&
			(!extgl_Extensions.GLX_ARB_multisample || (configMatches(config, orig_config, GLX_SAMPLE_BUFFERS_ARB) &&
													   configMatches(config, orig_config, GLX_SAMPLES_ARB)))) {
			return config;
		}
	}
	return NULL;
}

static bool createPbufferUsingDisplayContext(JNIEnv *env, PbufferInfo *buffer_info, int width, int height, const int *buffer_attribs) {
	if (!checkPbufferCaps(env, getCurrentGLXFBConfig(), width, height)) {
		return false;
	}
	int drawable_type;
	if (glXGetFBConfigAttrib(getDisplay(), getCurrentGLXFBConfig(), GLX_DRAWABLE_TYPE, &drawable_type) != Success) {
		throwException(env, "Could not get GLX_DRAWABLE_TYPE attribute from Display context");
		return false;
	}
	if (drawable_type & GLX_PBUFFER_BIT == 0) {
		throwException(env, "Display context does not support Pbuffers");
		return false;
	}
	GLXFBConfig config = chooseSingleBufferedConfigFromConfig(getCurrentGLXFBConfig());
	if (config == NULL)
		config = getCurrentGLXFBConfig();
	GLXPbuffer buffer = glXCreatePbuffer(getDisplay(), config, buffer_attribs);
	buffer_info->buffer = buffer;
	buffer_info->context = getCurrentGLXContext();
	return true;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nCreate(JNIEnv *env, jclass clazz, jobject handle_buffer, jboolean use_display_context, jint width, jint height, jobject pixel_format, jobject pixelFormatCaps, jobject pBufferAttribs)
{
	Display *disp = incDisplay(env);
	if (disp == NULL) {
		return;
	}
	int current_screen = getCurrentScreen();
	if (!extgl_InitGLX(env, disp, current_screen)) {
		decDisplay();
		throwException(env, "Could not init GLX");
		return;
	}

	const int buffer_attribs[] = {GLX_PBUFFER_WIDTH, width,
				      GLX_PBUFFER_HEIGHT, height,
				      GLX_PRESERVED_CONTENTS, True,
				      GLX_LARGEST_PBUFFER, False,
					  None, None};

	if (env->GetDirectBufferCapacity(handle_buffer) < sizeof(PbufferInfo)) {
		throwException(env, "Handle buffer not large enough");
		return;
	}
	PbufferInfo *buffer_info = (PbufferInfo *)env->GetDirectBufferAddress(handle_buffer);
//	PbufferInfo *buffer_info = (PbufferInfo *)malloc(sizeof(PbufferInfo));
	buffer_info->use_display_context = use_display_context;
	bool result;
	if (use_display_context) {
		result = createPbufferUsingDisplayContext(env, buffer_info, width, height, buffer_attribs);
	} else {
		result = createPbufferUsingUniqueContext(env, buffer_info, pixel_format, width, height, buffer_attribs);
	}
	if (!result)
		return;
	if (!checkXError(env)) {
		destroyPbuffer(buffer_info);
		return;
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nMakeCurrent
  (JNIEnv *env, jclass clazz, jobject handle_buffer)
{
	PbufferInfo *buffer_info = (PbufferInfo *)env->GetDirectBufferAddress(handle_buffer);
	//PbufferInfo *buffer_info = (PbufferInfo *)handle;
	GLXPbuffer buffer = buffer_info->buffer;
	GLXContext context = buffer_info->context;
	if (glXMakeContextCurrent(getDisplay(), buffer, buffer, context) == False) {
		throwException(env, "Could not make pbuffer context current");
	}
}

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    nDestroyGL
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nDestroy
  (JNIEnv *env, jclass clazz, jobject handle_buffer)
{
	PbufferInfo *buffer_info = (PbufferInfo *)env->GetDirectBufferAddress(handle_buffer);
	//PbufferInfo *buffer_info = (PbufferInfo *)handle;
	destroyPbuffer(buffer_info);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nSetAttrib
  (JNIEnv *env, jclass clazz, jobject handle_buffer, jint attrib, jint value)
{
	throwException(env, "The render-to-texture extension is not supported.");
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nBindTexImage
  (JNIEnv *env, jclass clazz, jobject handle_buffer, jint buffer)
{
	throwException(env, "The render-to-texture extension is not supported.");
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nReleaseTexImage
  (JNIEnv *env, jclass clazz, jobject handle_buffer, jint buffer)
{
	throwException(env, "The render-to-texture extension is not supported.");
}
