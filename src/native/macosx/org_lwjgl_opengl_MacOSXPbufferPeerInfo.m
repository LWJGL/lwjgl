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
 * @version $Revision$
 */

#import <jni.h>
#import <Cocoa/Cocoa.h>
#import "org_lwjgl_opengl_MacOSXPbufferPeerInfo.h"
#import "context.h"
#import "common_tools.h"

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXPbufferPeerInfo_nCreate(JNIEnv *env, jclass clazz, jobject peer_info_handle, jint width, jint height) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	NSOpenGLPixelBuffer *pbuffer = nil;
	// check if the texture is power of 2
	if ( (( width > 0 ) && ( width & ( width-1)) == 0) || (( height > 0 ) && ( height & ( height-1)) == 0) )
	{
		pbuffer = [[NSOpenGLPixelBuffer alloc] initWithTextureTarget:GL_TEXTURE_2D
			textureInternalFormat:GL_RGBA
			textureMaxMipMapLevel:0
			pixelsWide:width
			pixelsHigh:height];
	}
	else
	{
		pbuffer = [[NSOpenGLPixelBuffer alloc] initWithTextureTarget:GL_TEXTURE_RECTANGLE_EXT
			textureInternalFormat:GL_RGBA
			textureMaxMipMapLevel:0
			pixelsWide:width
			pixelsHigh:height];
	}
	MacOSXPeerInfo *peer_info = (MacOSXPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	peer_info->pbuffer = pbuffer;
	peer_info->isWindowed = false;
	[pool release];
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXPbufferPeerInfo_nDestroy
  (JNIEnv *env, jclass clazz, jobject peer_info_handle) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	MacOSXPeerInfo *peer_info = (MacOSXPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	[peer_info->pbuffer release];
	[pool release];
}
