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
 * Mac OS X Pbuffer.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#import <jni.h>
#import <OpenGL/gl.h>
#import "org_lwjgl_opengl_MacOSXDisplay.h"
#import "org_lwjgl_opengl_Pbuffer.h"
#import "display.h"

typedef struct {
	NSOpenGLPixelBuffer *pbuffer;
	NSOpenGLContext *context;
} PbufferInfo;

/* Check capacity and throw i not large enough to hold a PbufferInfo struct */
static bool checkCapacity(JNIEnv *env, jobject pbuffer_handle) {
	if ((*env)->GetDirectBufferCapacity(env, pbuffer_handle) < sizeof(PbufferInfo)) {
		throwException(env, "Handle buffer not large enough");
		return false;
	} else
		return true;
}

static PbufferInfo *getPbufferInfoFromBuffer(JNIEnv *env, jobject pbuffer_handle) {
	if (checkCapacity(env, pbuffer_handle))
		return (PbufferInfo *)(*env)->GetDirectBufferAddress(env, pbuffer_handle);
	else
		return NULL;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_makePbufferCurrent(JNIEnv *env, jobject this, jobject pbuffer_handle) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	PbufferInfo *pbuffer_handle_ptr = getPbufferInfoFromBuffer(env, pbuffer_handle);
	if (pbuffer_handle_ptr == NULL)
		return;
	[pbuffer_handle_ptr->context makeCurrentContext];
	[pool release];
}

static void createPbuffer(JNIEnv *env, jobject pbuffer_handle, jint width, jint height, jobject pixel_format, NSOpenGLContext shared_context) {
	if (!checkCapacity(env, pbuffer_handle))
		return;
	NSOpenGLPixelBuffer *pbuffer = [[NSOpenGLPixelBuffer alloc] initWithTextureTarget:GL_TEXTURE_2D textureInternalFormat:GL_RGBA textureMaxMipMapLevel:0 pixelsWide:width pixelsHigh:height];
	if (pbuffer == nil) {
		throwException(env, "Could not allocate Pbuffer");
		return;
	}
	NSOpenGLContext *context = createContext(env, pixel_format, false, false, NSOpenGLPFAPixelBuffer, shared_context);
	if (context == nil)
		return;
	int screen;
	if (display_context != NULL)
		screen = [display_context currentVirtualScreen];
	else
		screen = 0;
	[context setPixelBuffer:pbuffer cubeMapFace:0 mipMapLevel:0 currentVirtualScreen:screen];
	PbufferInfo *pbuffer_handle_ptr = (PbufferInfo *)(*env)->GetDirectBufferAddress(env, pbuffer_handle);
	pbuffer_handle_ptr->pbuffer = pbuffer;
	pbuffer_handle_ptr->context = context;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nCreatePbuffer(JNIEnv *env, jobject this, jobject pbuffer_handle, jint width, jint height, jobject pixel_format, jobject pixelFormatCaps, jobject pBufferAttribs, jobject shared_context_handle_buffer) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	NSOpenGLContext shared_context = getDisplayContext();
	if (shared_context_handle_buffer != NULL) {
		PbufferInfo *pbuffer_handle_ptr = (PbufferInfo *)(*env)->GetDirectBufferAddress(env, shared_context_handle_buffer);
		shared_context = pbuffer_handle_ptr->context;
	}
	createPbuffer(env, pbuffer_handle, width, height, pixel_format, shared_context);
	[pool release];
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_destroyPbuffer(JNIEnv *env, jobject this, jobject pbuffer_handle) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	PbufferInfo *pbuffer_handle_ptr = getPbufferInfoFromBuffer(env, pbuffer_handle);
	if (pbuffer_handle_ptr == NULL)
		return;
	[pbuffer_handle_ptr->context clearDrawable];
	[pbuffer_handle_ptr->context release];
	[pbuffer_handle_ptr->pbuffer release];
	[pool release];
}
