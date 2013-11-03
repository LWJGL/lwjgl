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
 * @author kappaOne <one.kappa@gmail.com>
 * @version $Revision$
 */

#import <Cocoa/Cocoa.h>
#include <jni.h>
#include <jawt_md.h>
#include "awt_tools.h"
#include "org_lwjgl_opengl_MacOSXCanvasPeerInfo.h"
#include "context.h"
#include "common_tools.h"

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_MacOSXCanvasPeerInfo_nInitHandle
(JNIEnv *env, jclass clazz, jobject lock_buffer_handle, jobject peer_info_handle, jobject window_handle, jboolean forceCALayer, jboolean autoResizable, jint x, jint y) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	
	MacOSXPeerInfo *peer_info = (MacOSXPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	AWTSurfaceLock *surface = (AWTSurfaceLock *)(*env)->GetDirectBufferAddress(env, lock_buffer_handle);
	JAWT_MacOSXDrawingSurfaceInfo *macosx_dsi = (JAWT_MacOSXDrawingSurfaceInfo *)surface->dsi->platformInfo;
	
	// force CALayer usage or check if CALayer is supported (i.e. on Java 5 and Java 6)
	if(forceCALayer || (surface->awt.version & 0x80000000)) { //JAWT_MACOSX_USE_CALAYER) {
		
		if (macosx_dsi != NULL) {
			
			if (window_handle == NULL) {
				window_handle = newJavaManagedByteBuffer(env, sizeof(MacOSXWindowInfo));
				if (window_handle == NULL) {
					throwException(env, "Could not create handle buffer");
				}
			} else if (peer_info->window_info->window != nil) {
				return window_handle;
			}
			
			if (peer_info->isCALayer) {
				[peer_info->glLayer release];
			}
			
			peer_info->glLayer = [GLLayer new];
			
			peer_info->glLayer->macosx_dsi = macosx_dsi;
			peer_info->glLayer->canvasBounds = (JAWT_Rectangle)surface->dsi->bounds;
			peer_info->window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
			peer_info->glLayer->window_info = peer_info->window_info;
			peer_info->glLayer->autoResizable = autoResizable;

			// ensure the CALayer size is correct, needed for Java 7+
            peer_info->glLayer.frame = CGRectMake(x, y, peer_info->glLayer->canvasBounds.width, peer_info->glLayer->canvasBounds.height);
			
			[peer_info->glLayer performSelectorOnMainThread:@selector(createWindow:) withObject:peer_info->pixel_format waitUntilDone:YES];
			
			peer_info->isCALayer = true;
			peer_info->isWindowed = true;
			peer_info->parent = nil;
			
			[pool release];
			return window_handle;
		}
	}
	
	// no CALayer support, fallback to using legacy method of getting the NSView of an AWT Canvas
	peer_info->parent = macosx_dsi->cocoaViewRef;
	peer_info->isCALayer = false;
	peer_info->isWindowed = true;
	
	[pool release];
	return NULL;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXCanvasPeerInfo_nSetLayerPosition
(JNIEnv *env, jclass clazz, jobject peer_info_handle, jint x, jint y) {
	MacOSXPeerInfo *peer_info = (MacOSXPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	
	if (peer_info->glLayer != nil) {
		NSPoint point = NSMakePoint(x, y);
		NSValue *value = [NSValue valueWithPoint:point];
		[peer_info->glLayer performSelectorOnMainThread:@selector(updatePosition:) withObject:value waitUntilDone:NO];
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXCanvasPeerInfo_nSetLayerBounds
(JNIEnv *env, jclass clazz, jobject peer_info_handle, jint x, jint y, jint width, jint height) {
	MacOSXPeerInfo *peer_info = (MacOSXPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	
	if (peer_info->glLayer != nil) {
		NSRect rect = NSMakeRect(x, y, width, height);
		NSValue *value = [NSValue valueWithRect:rect];
		[peer_info->glLayer performSelectorOnMainThread:@selector(updateBounds:) withObject:value waitUntilDone:NO];
	}
}

@implementation GLLayer

- (void) attachLayer {
	self.asynchronous = YES;
	self.needsDisplayOnBoundsChange = YES;
	self.opaque = NO;
	if (autoResizable) {
		self.autoresizingMask = kCALayerWidthSizable | kCALayerHeightSizable;
	}
	else {
		self.autoresizingMask = kCALayerNotSizable;
	}
	
	// get root layer of the AWT Canvas and add self to it
	id <JAWT_SurfaceLayers> surfaceLayers = (id <JAWT_SurfaceLayers>)macosx_dsi;
	
	if (surfaceLayers.layer != self) {
		surfaceLayers.layer = self;
        
		// flip CALayer y position, needed for Java 7 workaround
		self.frame = CGRectMake(self.frame.origin.x,
								self.superlayer.bounds.size.height - self.frame.origin.y - self.frame.size.height,
								self.frame.size.width, self.frame.size.height);
	}
}

- (void) removeLayer {
    
    // clean up resources
    glDeleteFramebuffersEXT(1, &fboID);
    glDeleteRenderbuffersEXT(1, &imageRenderBufferID);
    glDeleteRenderbuffersEXT(1, &depthRenderBufferID);
    
	// finish any pending blits before destroying the offscreen window to prevent crashes
	glFinish();
    
    // destroy offscreen Display window
    [self destroyWindow];
	
	// remove self from root layer
	[self removeFromSuperlayer];
}

- (void)updatePosition:(NSValue*)value {
	NSPoint point = [value pointValue];
	self.position = CGPointMake(point.x, self.superlayer.bounds.size.height - point.y - self.bounds.size.height);
}

- (void)updateBounds:(NSValue*)value {
	NSRect rect = [value rectValue];
	self.frame = CGRectMake(rect.origin.x, self.superlayer.bounds.size.height - rect.origin.y - self.bounds.size.height, rect.size.width, rect.size.height);
}

- (int) getWidth {
	return canvasBounds.width;
}

- (int) getHeight {
	return canvasBounds.height;
}

- (void) createWindow:(NSOpenGLPixelFormat*)pixel_format {
	if (window_info->window != nil) {
		[window_info->window close];
	}
	
	window_info->display_rect = [[NSScreen mainScreen] frame];
	
	window_info->view = [[MacOSXOpenGLView alloc] initWithFrame:window_info->display_rect pixelFormat:pixel_format];
	
	window_info->window = [[MacOSXKeyableWindow alloc] initWithContentRect:window_info->display_rect styleMask:NSBorderlessWindowMask backing:NSBackingStoreBuffered defer:NO];
	[window_info->window setContentView:window_info->view];
	
	[window_info->window orderOut:nil];
}

- (void) destroyWindow {
	if (window_info->window != nil) {
		[window_info->view removeFromSuperviewWithoutNeedingDisplay];
		[window_info->window close];
		window_info->window = nil;
    }
}

- (void) blitFrameBuffer {
	
	// get the size of the CALayer/AWT Canvas
	int width = self.bounds.size.width;
	int height = self.bounds.size.height;
	
	if (width != fboWidth || height != fboHeight) {
		
		// store current fbo/renderbuffers for later deletion
		int oldFboID = fboID;
		int oldImageRenderBufferID = imageRenderBufferID;
		int oldDepthRenderBufferID = depthRenderBufferID;
		
		// create new fbo
		int tempFBO;
		glGenFramebuffersEXT(1, &tempFBO);
		
		// create new render buffers
		glGenRenderbuffersEXT(1, &imageRenderBufferID);
		glGenRenderbuffersEXT(1, &depthRenderBufferID);
		
		// switch to new fbo to attach render buffers
		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, tempFBO);
		
		// initialize and attach image render buffer
		glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, imageRenderBufferID);
		glRenderbufferStorageEXT(GL_RENDERBUFFER_EXT, GL_RGB, width, height);
		glFramebufferRenderbufferEXT(GL_FRAMEBUFFER_EXT, GL_COLOR_ATTACHMENT0_EXT, GL_RENDERBUFFER_EXT, imageRenderBufferID);
		
		// initialize and attach depth render buffer
		glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, depthRenderBufferID);
		glRenderbufferStorageEXT(GL_RENDERBUFFER_EXT, GL_DEPTH_COMPONENT24, width, height);
		glFramebufferRenderbufferEXT(GL_FRAMEBUFFER_EXT, GL_DEPTH_ATTACHMENT_EXT, GL_RENDERBUFFER_EXT, depthRenderBufferID);
		
		// clear garbage background on new fbo
		glClearColor(0.0, 0.0, 0.0, 1.0);
		glClear(GL_COLOR_BUFFER_BIT);
		
		// blit frameBuffer to the new fbo
		glBindFramebufferEXT(GL_READ_FRAMEBUFFER_EXT, 0);
		glBindFramebufferEXT(GL_DRAW_FRAMEBUFFER_EXT, tempFBO);
		glBlitFramebufferEXT(0, 0, width, height,
							 0, 0, width, height,
							 GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT,
							 GL_NEAREST);
		
		glFinish(); // finish before using new fbo and resizing the window
		
		// set new fbo and its sizes
		fboID = tempFBO;
		fboWidth = width;
		fboHeight = height;
		
		// set the size of the offscreen frame buffer window
		window_info->display_rect = NSMakeRect(0, 0, width, height);
		
		// clean up the old fbo and renderBuffers
		glDeleteFramebuffersEXT(1, &oldFboID);
		glDeleteRenderbuffersEXT(1, &oldImageRenderBufferID);
		glDeleteRenderbuffersEXT(1, &oldDepthRenderBufferID);
	}
	else {
		glBindFramebufferEXT(GL_READ_FRAMEBUFFER_EXT, 0);
		glBindFramebufferEXT(GL_DRAW_FRAMEBUFFER_EXT, fboID);
		
		glBlitFramebufferEXT(0, 0, width, height,
							 0, 0, width, height,
							 GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT,
							 GL_NEAREST);
	}
	
	// restore default framebuffer
	glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
}

-(void)drawInCGLContext:(CGLContextObj)glContext
						pixelFormat:(CGLPixelFormatObj)pixelFormat
						forLayerTime:(CFTimeInterval)timeInterval
						displayTime:(const CVTimeStamp *)timeStamp {
	
	// set the current context
	CGLSetCurrentContext(glContext);
	
	// get the size of the CALayer/AWT Canvas
	int width = self.bounds.size.width;
	int height = self.bounds.size.height;
	
	if (width != fboWidth || height != fboHeight) {
		// clear garbage background before lwjgl fbo blit
		glClearColor(0.0, 0.0, 0.0, 1.0);
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	// read the LWJGL FBO and blit it into this CALayers FBO
	glBindFramebufferEXT(GL_READ_FRAMEBUFFER_EXT, fboID);
	glBlitFramebufferEXT(0, 0, width, height,
						 0, 0, width, height,
						 GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT,
						 GL_NEAREST);
	
	// call super to finalize the drawing - by default all it does is call glFlush()
	[super drawInCGLContext:glContext pixelFormat:pixelFormat forLayerTime:timeInterval displayTime:timeStamp];
}

-(BOOL)canDrawInCGLContext:(CGLContextObj)glContext
							pixelFormat:(CGLPixelFormatObj)pixelFormat
							forLayerTime:(CFTimeInterval)timeInterval
							displayTime:(const CVTimeStamp *)timeStamp {
	return YES;
}

- (CGLContextObj)copyCGLContextForPixelFormat:(CGLPixelFormatObj)pixelFormat {
	CGLCreateContext(pixelFormat, [window_info->context CGLContextObj], &contextObject);
	return contextObject;
}

- (void)releaseCGLContext:(CGLContextObj)glContext {
	CGLClearDrawable(contextObject);
    // disable releasing context due to nvidia crash bug when releasing shared contexts
    //CGLDestroyContext(contextObject);
}

- (CGLPixelFormatObj)copyCGLPixelFormatForDisplayMask:(uint32_t)mask {
	return CGLGetPixelFormat([window_info->context CGLContextObj]);
}

- (void)releaseCGLPixelFormat:(CGLPixelFormatObj)pixelFormat {
	
}

@end