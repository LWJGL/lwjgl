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
 * Base OSX functionality for GL.
 *
 * @author Elias Naur <elias_naur@sourceforge.net>
 * @version $Revision$
 */

#include <OpenGL/OpenGL.h>
#include "org_lwjgl_opengl_Window.h"
#include "Window.h"
#include "extgl.h"
#include "tools.h"
#include "common_tools.h"

static CGLContextObj context;
static bool vsync_enabled;
static bool current_fullscreen;
 
static void destroyMode(JNIEnv *env, jclass clazz) {
	if (!current_fullscreen);
		resetMode(env);
}

static void destroy(JNIEnv *env, jclass clazz) {
	CGLSetCurrentContext(NULL);
	CGLDestroyContext(context);
	destroyMode(env, clazz);
	extgl_Close();
}

static bool createFullscreenContext(JNIEnv *env, jint bpp, jint alpha, jint depth, jint stencil) {
	CGOpenGLDisplayMask display_mask = CGDisplayIDToOpenGLDisplayMask(kCGDirectMainDisplay);
	CGLPixelFormatObj pixel_format;
	long num_formats;
	CGLPixelFormatAttribute attribs[] = {kCGLPFAFullScreen,
					     kCGLPFADoubleBuffer,
					     kCGLPFAMinimumPolicy,
					     kCGLPFAAccelerated,
					     kCGLPFADisplayMask,
					     (CGLPixelFormatAttribute)display_mask,
					     kCGLPFAColorSize,
					     (CGLPixelFormatAttribute)bpp,
					     kCGLPFAAlphaSize,
					     (CGLPixelFormatAttribute)alpha,
					     kCGLPFADepthSize,
					     (CGLPixelFormatAttribute)depth,
					     kCGLPFAStencilSize,
					     (CGLPixelFormatAttribute)stencil,
					     (CGLPixelFormatAttribute)NULL};
	CGLChoosePixelFormat(attribs, &pixel_format, &num_formats);
	if (pixel_format == NULL) {
		throwException(env, "Could not find matching pixel format");
		return false;
	}
	CGLCreateContext(pixel_format, NULL, &context);
	CGLDestroyPixelFormat(pixel_format);
	if (context == NULL) {
		throwException(env, "Could not create fullscreen context");
		return false;
	}
	CGLSetFullScreen(context);
	CGLSetCurrentContext(context);
	FlushEventQueue(GetMainEventQueue());
	return true;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsCloseRequested(JNIEnv *, jclass) {
	return JNI_FALSE;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nCreate(JNIEnv *env, jclass clazz, jstring title, jint x, jint y, jint width, jint height, jboolean fullscreen, jint bpp, jint alpha, jint depth, jint stencil, jobject ext_set) {
	vsync_enabled = false;
	current_fullscreen = fullscreen == JNI_TRUE;
	if (!extgl_Open()) {
		throwException(env, "Could not load gl library");
		return;
	}
	if (!extgl_InitAGL(env, ext_set)) {
		throwException(env, "Could not load agl symbols");
		return;
	}
	if (!current_fullscreen) {
		if (!switchMode(env, width, height, bpp, 60)) {
			destroyMode(env, clazz);
			extgl_Close();
			throwException(env, "Could not switch mode.");
			return;
		}
	}
	if (!createFullscreenContext(env, bpp, alpha, depth, stencil)) {
		destroyMode(env, clazz);
		extgl_Close();
		return;
	}
	if (!extgl_Initialize(env, ext_set)) {
		destroy(env, clazz);
		throwException(env, "Could not load gl function pointers");
		return;
	}
	FlushEventQueue(GetMainEventQueue());
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nSetTitle(JNIEnv * env, jclass clazz, jstring title_obj) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_update(JNIEnv *env, jclass clazz) {
	EventRef event;
	OSStatus err = ReceiveNextEvent(0, NULL, 0, true, &event);
	if (err == noErr) {
		UInt32 event_class = GetEventClass(event);
		switch (event_class) {
			case kEventClassKeyboard:
				handleKeyboardEvent(event);
				break;
			case kEventClassMouse:
				handleMouseEvent(event);
				break;
			default:
				break;
		}
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_swapBuffers(JNIEnv * env, jclass clazz) {
	CGLFlushDrawable(context);
}
                                                                                                                                                                
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_minimize(JNIEnv *env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_restore(JNIEnv *env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nDestroy(JNIEnv *env, jclass clazz) {
	destroy(env, clazz);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsFocused(JNIEnv *env, jclass clazz) {
	return JNI_TRUE;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsDirty(JNIEnv *env, jclass clazz) {
	return JNI_FALSE;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsMinimized(JNIEnv *env, jclass clazz) {
	return JNI_FALSE;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsVSyncEnabled(JNIEnv *env, jclass clazz) {
	return vsync_enabled;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nSetVSyncEnabled(JNIEnv *env, jclass clazz, jboolean enable) {
	bool should_enable = enable == JNI_TRUE;
	if (vsync_enabled != should_enable) {
		vsync_enabled = should_enable;
		long swap_interval = vsync_enabled ? 1 : 0;
		CGLSetParameter(context, kCGLCPSwapInterval, &swap_interval);
	}
}
