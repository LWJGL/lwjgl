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
 * Mac OS Xspecific display functions.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#import <Cocoa/Cocoa.h>
#import <OpenGL/OpenGL.h>
#import <Carbon/Carbon.h>
#import <jawt_md.h>
#import <jni.h>
#import <unistd.h>
#import "common_tools.h"

#define WAIT_DELAY 100

static NSOpenGLContext *gl_context;

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_createContext(JNIEnv *env, jobject this, jobject pixel_format) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];

	size_t bpp = CGDisplayBitsPerPixel(kCGDirectMainDisplay);
	
	jclass cls_pixel_format = (*env)->GetObjectClass(env, pixel_format);
	int alpha = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "alpha", "I"));
	int depth = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "depth", "I"));
	int stencil = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "stencil", "I"));
	int samples = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "samples", "I"));
	int num_aux_buffers = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "num_aux_buffers", "I"));
	int accum_bpp = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "accum_bpp", "I"));
	int accum_alpha = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "accum_alpha", "I"));
	bool stereo = (bool)(*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "stereo", "Z"));

	NSOpenGLPixelFormatAttribute attribs[] = {
		NSOpenGLPFAAccelerated,
		NSOpenGLPFADoubleBuffer,
		NSOpenGLPFAColorSize, bpp,
		NSOpenGLPFAAlphaSize, alpha,
		NSOpenGLPFADepthSize, depth,
		NSOpenGLPFAStencilSize, stencil,
		NSOpenGLPFAAccumSize, accum_bpp + accum_alpha,
		NSOpenGLPFASampleBuffers, samples > 0 ? 1 : 0,
		NSOpenGLPFASamples, samples,
		NSOpenGLPFAAuxBuffers, num_aux_buffers,
		NSOpenGLPFAWindow,
		0,
		0
	};

	if (stereo)
		attribs[19] = NSOpenGLPFAStereo;
	NSOpenGLPixelFormat* fmt = [[NSOpenGLPixelFormat alloc] initWithAttributes:attribs];

	if (fmt == nil) {
		throwException(env, "Could not create pixel format");
		[pool release];
		return;
	}

	gl_context = [[NSOpenGLContext alloc] initWithFormat:fmt shareContext:nil];

	[fmt release];
	if (gl_context == nil)
		throwException(env, "Could not create context");
	[pool release];
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_updateContext(JNIEnv *env, jobject this) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	[gl_context update];
	[pool release];
}

static void setView(JNIEnv *env, jobject canvas) {
	JAWT awt;
	JAWT_DrawingSurface* ds;
	JAWT_DrawingSurfaceInfo* dsi;
	JAWT_MacOSXDrawingSurfaceInfo* dsi_mac;
	jboolean result = JNI_FALSE;
	jint lock = 0;

	// get the AWT
	awt.version = JAWT_VERSION_1_4;
	NSView *view;
	while (true) {
		result = JAWT_GetAWT(env, &awt);
		if (result == JNI_FALSE) {
			throwException(env, "Could not get the JAWT interface");
			return;
		}

		ds = awt.GetDrawingSurface(env, canvas);
		if (ds == NULL) {
			throwException(env, "Could not get the drawing surface");
			return;
		}

		lock = ds->Lock(ds);
		if((lock & JAWT_LOCK_ERROR) != 0) {
			awt.FreeDrawingSurface(ds);
			throwException(env, "Could not lock the drawing surface");
			return;
		}

		dsi = ds->GetDrawingSurfaceInfo(ds);
		if (dsi != NULL) {
			dsi_mac = (JAWT_MacOSXDrawingSurfaceInfo*)dsi->platformInfo;
			view = dsi_mac->cocoaViewRef;
			if ([view lockFocusIfCanDraw] == YES)
				break;
			view = NULL;
			ds->FreeDrawingSurfaceInfo(dsi);
		}

		printfDebug("Could not get drawing surface info, retrying... \n");
		ds->Unlock(ds);
		awt.FreeDrawingSurface(ds);
		usleep(WAIT_DELAY);
	}

//	[gl_context clearDrawable];
	[gl_context setView:view];

	[view unlockFocus];

	ds->FreeDrawingSurfaceInfo(dsi);
	ds->Unlock(ds);
	awt.FreeDrawingSurface(ds);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_setVSyncEnabled(JNIEnv *env, jobject this, jboolean vsync) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	long vsync_value = vsync  == JNI_TRUE ? 1 : 0;
	[gl_context setValues:&vsync_value forParameter:NSOpenGLCPSwapInterval];
	[pool release];
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_swapBuffers(JNIEnv *env, jobject this) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	[gl_context flushBuffer];
	[pool release];
}
  
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_setView(JNIEnv *env, jobject this, jobject canvas) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	if (canvas != NULL) {
		setView(env, canvas);
	} else {
		[gl_context clearDrawable];
	}
	[pool release];
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_makeCurrent(JNIEnv *env, jobject this) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	[gl_context makeCurrentContext];
	[pool release];
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_destroyContext(JNIEnv *env, jobject this) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	if (gl_context != nil) {
		[gl_context clearDrawable];
		[gl_context release];
		gl_context = nil;
	}
	[pool release];
}
