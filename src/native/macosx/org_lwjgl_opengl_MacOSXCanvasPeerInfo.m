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
 * @author Pelle Johnsen
 * @version $Revision$
 */

#import <Cocoa/Cocoa.h>
#import <JavaNativeFoundation.h>

#include <jni.h>
#include <jawt_md.h>
#include "awt_tools.h"
#include "org_lwjgl_opengl_MacOSXCanvasPeerInfo.h"
#include "context.h"
#include "common_tools.h"

// forward declaration
@interface PBufferGLLayer : NSOpenGLLayer {
    MacOSXPeerInfo *peer_info;
    GLuint textureID;
}

- (MacOSXPeerInfo*) peer_info;
- (GLuint) textureID;

- (void) setPeer_info: (MacOSXPeerInfo*)input;
- (void) setTextureID: (GLuint)input;

@end


JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXCanvasPeerInfo_nInitHandle
(JNIEnv *env, jclass clazz, jobject lock_buffer_handle, jobject peer_info_handle, jboolean allowCALayer) {
    NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	
	MacOSXPeerInfo *peer_info = (MacOSXPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	AWTSurfaceLock *surface = (AWTSurfaceLock *)(*env)->GetDirectBufferAddress(env, lock_buffer_handle);
	JAWT_MacOSXDrawingSurfaceInfo *macosx_dsi = (JAWT_MacOSXDrawingSurfaceInfo *)surface->dsi->platformInfo;
    
    if (allowCALayer) {
		// check for CALayer support
		if(surface->awt.version & 0x80000000) { //JAWT_MACOSX_USE_CALAYER) {
			jint width = surface->dsi->bounds.width;
			jint height = surface->dsi->bounds.height;
			
			if(peer_info->pbuffer == NULL || peer_info->window || width != [peer_info->pbuffer pixelsWide] || height != [peer_info->pbuffer pixelsHigh]) {
				if(peer_info->pbuffer != NULL) {
					[peer_info->pbuffer release];
				}
            
				// make pbuffer
				NSOpenGLPixelBuffer *pbuffer = nil;
				NSLog(@"Make pbuffer: %d x %d", width, height);
				pbuffer = [[NSOpenGLPixelBuffer alloc] initWithTextureTarget:GL_TEXTURE_RECTANGLE_EXT
													   textureInternalFormat:GL_RGBA
													   textureMaxMipMapLevel:0
														pixelsWide:width
														pixelsHigh:height];
            
				peer_info->pbuffer = pbuffer;
				peer_info->window = false;
				peer_info->canDrawGL = true;
			}
        
			if (macosx_dsi != NULL) {
				[JNFRunLoop performOnMainThreadWaiting:YES withBlock:^(){
					// attach the "root layer" to the AWT Canvas surface layers
					id <JAWT_SurfaceLayers> surfaceLayers = (id <JAWT_SurfaceLayers>)macosx_dsi;//dsi->platformInfo;
					if(surfaceLayers.layer == NULL) {
						PBufferGLLayer *caGLLayer = [[PBufferGLLayer new] autorelease];
						caGLLayer.peer_info = peer_info;
						caGLLayer.asynchronous = YES;
						caGLLayer.needsDisplayOnBoundsChange = YES;
						caGLLayer.opaque = YES;
						surfaceLayers.layer = caGLLayer;                
					}
				}];
			}
			
			[pool release];
			return;
		}
	}
	
	peer_info->nsview = macosx_dsi->cocoaViewRef;
    peer_info->window = true;
    
	[pool release];
}

// rotates a red square when asked to draw
@implementation PBufferGLLayer

// override to draw custom GL content
-(void)drawInCGLContext:(CGLContextObj)glContext
			pixelFormat:(CGLPixelFormatObj)pixelFormat
		   forLayerTime:(CFTimeInterval)timeInterval
			displayTime:(const CVTimeStamp *)timeStamp {
	
    if(!peer_info || !peer_info->pbuffer) {
        return;
    }
    
    peer_info->canDrawGL = false;
    
    NSOpenGLPixelBuffer *pbuffer = self.peer_info->pbuffer;
    
    // set the current context
    CGLSetCurrentContext(glContext);
	
    GLsizei width = [pbuffer pixelsWide];
    GLsizei height = [pbuffer pixelsHigh];
	
    if(textureID == 0) {
        glGenTextures(1, &textureID);
    }
    glBindTexture(GL_TEXTURE_RECTANGLE_EXT, self.textureID);
    CGLTexImagePBuffer(glContext,[pbuffer CGLPBufferObj], GL_FRONT);
	
    glTexParameteri(GL_TEXTURE_RECTANGLE_ARB, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameteri(GL_TEXTURE_RECTANGLE_ARB, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    glTexParameteri(GL_TEXTURE_RECTANGLE_ARB, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_RECTANGLE_ARB, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
	
    glEnable(GL_TEXTURE_RECTANGLE_EXT);
	
    static GLfloat verts[] = {
        -1.0, -1.0,
        -1.0,  1.0,
		1.0,  1.0,
		1.0, -1.0
    };
    
    GLfloat tex[] = {
        0.0, 0.0,
        0.0, height,
        width, height,
        width, 0.0
    };
    
    glEnableClientState(GL_VERTEX_ARRAY);
    glEnableClientState(GL_TEXTURE_COORD_ARRAY);
    glVertexPointer(2, GL_FLOAT, 0, verts);
    glTexCoordPointer(2, GL_FLOAT, 0, tex);
    
    glDrawArrays(GL_QUADS, 0, 4);
    
    glDisableClientState(GL_VERTEX_ARRAY);
    glDisableClientState(GL_TEXTURE_COORD_ARRAY);
    
    glDisable(GL_TEXTURE_RECTANGLE_EXT);
	
    // call super to finalize the drawing - by default all it does is call glFlush()
    [super drawInCGLContext:glContext pixelFormat:pixelFormat forLayerTime:timeInterval displayTime:timeStamp];
}

-(BOOL)canDrawInCGLContext:(CGLContextObj)glContext
			   pixelFormat:(CGLPixelFormatObj)pixelFormat
			  forLayerTime:(CFTimeInterval)timeInterval
			   displayTime:(const CVTimeStamp *)timeStamp {
    return (peer_info->canDrawGL && !peer_info->window) ? YES : NO;
}

- (MacOSXPeerInfo*) peer_info {
    return peer_info;
}

- (GLuint) textureID {
    return textureID;
}

- (void) setPeer_info: (MacOSXPeerInfo*)input {
    peer_info = input;
}

- (void) setTextureID: (GLuint)input {
    textureID = input;
}

@end