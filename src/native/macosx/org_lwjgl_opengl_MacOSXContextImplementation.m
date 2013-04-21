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
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @author kappaOne <one.kappa@gmail.com>
 * @version $Revision$
 */

#import <jni.h>
#import <OpenGL/CGLCurrent.h>
#import <OpenGL/CGLTypes.h>
#import <OpenGL/CGLDevice.h>
#import <Cocoa/Cocoa.h>
#import "org_lwjgl_opengl_MacOSXContextImplementation.h"
#import "context.h"
#import "common_tools.h"

typedef struct {
    NSOpenGLContext *context;
    MacOSXPeerInfo *peer_info;
} MacOSXContext;

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_MacOSXContextImplementation_nCreate
  (JNIEnv *env, jclass clazz, jobject peer_info_handle, jobject shared_context_handle) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	MacOSXPeerInfo *peer_info;
	MacOSXContext *shared_context_info;
	MacOSXContext *context_info;
    NSOpenGLContext *context;
    NSOpenGLContext *shared_context = NULL;

	jobject context_handle = newJavaManagedByteBuffer(env, sizeof(MacOSXContext));
	if (context_handle == NULL) {
		throwException(env, "Could not create handle buffer");
		return NULL;
	}
	peer_info = (MacOSXPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	if (shared_context_handle != NULL) {
		shared_context_info = (MacOSXContext *)(*env)->GetDirectBufferAddress(env, shared_context_handle);
        shared_context = shared_context_info->context;
	}
    context = [[NSOpenGLContext alloc] initWithFormat:peer_info->pixel_format shareContext:shared_context];
	if (context == NULL) {
		throwException(env, "Could not create context");
		return NULL;
	}
	
	if (peer_info->isWindowed) {
		if (peer_info->window_info->fullscreen) {
			// set a fixed backbuffer size for fullscreen
			CGLContextObj cgcontext = (CGLContextObj)[context CGLContextObj];
			NSSize displaySize = peer_info->window_info->display_rect.size;
			GLint dim[2] = {displaySize.width, displaySize.height};
			CGLSetParameter(cgcontext, kCGLCPSurfaceBackingSize, dim);
			CGLEnable(cgcontext, kCGLCESurfaceBackingSize);
		}
		else {
			// disable any fixed backbuffer size to allow resizing
			CGLContextObj cgcontext = (CGLContextObj)[context CGLContextObj];
			CGLDisable(cgcontext, kCGLCESurfaceBackingSize); 
		}
		
		[peer_info->window_info->view setOpenGLContext:context];
		peer_info->window_info->context = context;
	}
	
	context_info = (MacOSXContext *)(*env)->GetDirectBufferAddress(env, context_handle);
	context_info->context = context;
	context_info->peer_info = peer_info;
	
	[pool release];
	return context_handle;
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_MacOSXContextImplementation_getCGLShareGroup
  (JNIEnv *env, jclass clazz, jobject context_handle) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	MacOSXContext *context_info = (MacOSXContext *)(*env)->GetDirectBufferAddress(env, context_handle);
	CGLContextObj cgl_context = [context_info->context CGLContextObj];
	CGLShareGroupObj share_group = CGLGetShareGroup(cgl_context);
	[pool release];
	return (jlong)share_group;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXContextImplementation_nSwapBuffers
  (JNIEnv *env, jclass clazz, jobject context_handle) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	MacOSXContext *context_info = (MacOSXContext *)(*env)->GetDirectBufferAddress(env, context_handle);
	[context_info->context flushBuffer];
	  
	if (context_info->peer_info->isCALayer) {
		// blit the contents of buffer to CALayer
		[context_info->peer_info->glLayer blitFrameBuffer];
	}
	  
    [pool release];
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXContextImplementation_nUpdate
  (JNIEnv *env, jclass clazz, jobject context_handle) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	MacOSXContext *context_info = (MacOSXContext *)(*env)->GetDirectBufferAddress(env, context_handle);
	[context_info->context update];
	[pool release];
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXContextImplementation_clearDrawable
(JNIEnv *env, jclass clazz, jobject context_handle) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	MacOSXContext *context_info = (MacOSXContext *)(*env)->GetDirectBufferAddress(env, context_handle);
	[context_info->context clearDrawable];
	[pool release];
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXContextImplementation_nReleaseCurrentContext
  (JNIEnv *env, jclass clazz) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
    [NSOpenGLContext clearCurrentContext];
	[pool release];
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXContextImplementation_setView
	(JNIEnv *env, jclass clazz, jobject peer_info_handle, jobject context_handle) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	MacOSXContext *context_info = (MacOSXContext *)(*env)->GetDirectBufferAddress(env, context_handle);
	MacOSXPeerInfo *peer_info = (MacOSXPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	
	if (peer_info->isWindowed) {
		if (peer_info->isCALayer && [context_info->context view] != peer_info->window_info->view) {
			// mark glViewport to be set manually when setting a new context view
			peer_info->glLayer->setViewport = YES;
		}
		
		[context_info->context setView: peer_info->window_info->view];
	}
	else {
		[context_info->context setPixelBuffer:peer_info->pbuffer cubeMapFace:0 mipMapLevel:0 currentVirtualScreen:0];
	}
	
	if (peer_info->isCALayer) {
		// if using a CALayer, attach it to AWT Canvas and create a shared opengl context with current context
		[peer_info->glLayer performSelectorOnMainThread:@selector(attachLayer) withObject:nil waitUntilDone:NO];
	}
	  
	[pool release];
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXContextImplementation_nMakeCurrent
  (JNIEnv *env, jclass clazz, jobject context_handle) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
    MacOSXContext *context_info = (MacOSXContext *)(*env)->GetDirectBufferAddress(env, context_handle);
	[context_info->context makeCurrentContext];
	
	if (context_info->peer_info->isCALayer && context_info->peer_info->glLayer->setViewport) {
		context_info->peer_info->glLayer->setViewport = NO;
		glViewport(0, 0, [context_info->peer_info->glLayer getWidth], [context_info->peer_info->glLayer getHeight]);
	}
	  
	[pool release];
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_MacOSXContextImplementation_nIsCurrent
  (JNIEnv *env, jclass clazz, jobject context_handle) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
    MacOSXContext *context_info = (MacOSXContext *)(*env)->GetDirectBufferAddress(env, context_handle);
	bool result = context_info->context == [NSOpenGLContext currentContext];
	[pool release];
	return result ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXContextImplementation_nSetSwapInterval
  (JNIEnv *env, jclass clazz, jobject context_handle, jint int_value) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	MacOSXContext *context_info = (MacOSXContext *)(*env)->GetDirectBufferAddress(env, context_handle);
	GLint value = int_value;
	[context_info->context setValues:&value forParameter:NSOpenGLCPSwapInterval];
	[pool release];
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXContextImplementation_nDestroy
  (JNIEnv *env, jclass clazz, jobject context_handle) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	
	MacOSXContext *context_info = (MacOSXContext *)(*env)->GetDirectBufferAddress(env, context_handle);
	
	if (context_info->peer_info->isCALayer) {
		context_info->peer_info->isCALayer = false;
		[context_info->peer_info->glLayer performSelectorOnMainThread:@selector(removeLayer) withObject:nil waitUntilDone:YES];
		[context_info->peer_info->glLayer release];
		context_info->peer_info->glLayer = nil;
        // don't release context due to nvidia driver bug when releasing shared contexts
        [context_info->context retain];
	}
	
	[context_info->context clearDrawable];
	
	if (context_info->peer_info->isWindowed) {
        [context_info->peer_info->window_info->view setOpenGLContext:nil];
		[context_info->context release];
		context_info->context = nil;
		context_info->peer_info->window_info->context = nil;
	}
    else {
        // don't release context due to nvidia driver bug when releasing shared contexts
        //[context_info->context release];
		//context_info->context = nil;
    }
	
	[pool release];
}
