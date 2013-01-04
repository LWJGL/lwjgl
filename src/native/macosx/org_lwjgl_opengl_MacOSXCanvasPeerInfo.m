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
#include <jni.h>
#include <jawt_md.h>
#include "awt_tools.h"
#include "org_lwjgl_opengl_MacOSXCanvasPeerInfo.h"
#include "context.h"
#include "common_tools.h"

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXCanvasPeerInfo_nInitHandle
(JNIEnv *env, jclass clazz, jobject lock_buffer_handle, jobject peer_info_handle) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	
	MacOSXPeerInfo *peer_info = (MacOSXPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	AWTSurfaceLock *surface = (AWTSurfaceLock *)(*env)->GetDirectBufferAddress(env, lock_buffer_handle);
	JAWT_MacOSXDrawingSurfaceInfo *macosx_dsi = (JAWT_MacOSXDrawingSurfaceInfo *)surface->dsi->platformInfo;
	
	// check for CALayer support
	if(surface->awt.version & 0x80000000) { //JAWT_MACOSX_USE_CALAYER) {
		
		if (macosx_dsi != NULL) {
			peer_info->glLayer = [GLLayer new];
			peer_info->glLayer->macosx_dsi = macosx_dsi;
			peer_info->glLayer->window_info = peer_info->window_info;
		}
		
		peer_info->isCALayer = true;
		peer_info->isWindowed = true;
		peer_info->parent = nil;
		
		[pool release];
		return;
	}
	
	// no CALayer support, fallback to using legacy method of getting the NSView of an AWT Canvas
	peer_info->parent = macosx_dsi->cocoaViewRef;
	peer_info->isCALayer = false;
	peer_info->isWindowed = true;
	
	[pool release];
}

@implementation GLLayer

- (void) attachLayer {
	self.asynchronous = YES;
	self.needsDisplayOnBoundsChange = YES;
	self.opaque = NO;
	self.autoresizingMask = kCALayerWidthSizable | kCALayerHeightSizable;
	
	// get root layer of the AWT Canvas and add self to it
	id <JAWT_SurfaceLayers> surfaceLayers = (id <JAWT_SurfaceLayers>)macosx_dsi;
	surfaceLayers.layer = self;
}

- (void) removeLayer {
	// remove self from root layer
	id <JAWT_SurfaceLayers> surfaceLayers = (id <JAWT_SurfaceLayers>)macosx_dsi;
	surfaceLayers.layer = nil;
}

-(void)drawInCGLContext:(CGLContextObj)glContext
						pixelFormat:(CGLPixelFormatObj)pixelFormat
						forLayerTime:(CFTimeInterval)timeInterval
						displayTime:(const CVTimeStamp *)timeStamp {
	
	// set the current context
	CGLSetCurrentContext(glContext);
	
	GLint originalFBO;
	GLint originalReadFBO;
	GLint originalDrawFBO;
	
	// get and save the current fbo values
	glGetIntegerv(GL_FRAMEBUFFER_BINDING_EXT, &originalFBO);
	glGetIntegerv(GL_READ_FRAMEBUFFER_BINDING_EXT, &originalReadFBO);
	glGetIntegerv(GL_DRAW_FRAMEBUFFER_BINDING_EXT, &originalDrawFBO);
	
	/*glClearColor(0.0, 0.0, 0.0, 1.0);
	glClear(GL_COLOR_BUFFER_BIT);
	
	// copy/blit the LWJGL FBO to this CALayers FBO
	// TODO
	glBindFramebufferEXT(GL_READ_FRAMEBUFFER_EXT, 1);//lwjglFBO);
	glBindFramebufferEXT(GL_DRAW_FRAMEBUFFER_EXT, originalFBO);
 
	glBlitFramebufferEXT(0, 0, 640, 480,//width, height,
	0, 0, 640, 480,//width, height,
	GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT,
	GL_NEAREST);*/
	
	// for testing, draw a single yellow quad spinning around based on the current time
	GLfloat rotate = timeInterval * 60.0; // 60 degrees per second
	glClearColor(0.0, 0.0, 0.0, 1.0);
	glClear(GL_COLOR_BUFFER_BIT);
	glMatrixMode(GL_MODELVIEW);
	glPushMatrix();
	glRotatef(rotate, 0.0, 0.0, 1.0);
	glBegin(GL_QUADS);
	glColor3f(1.0, 1.0, 0.0);
	glVertex2f(-0.5, -0.5);
	glVertex2f(-0.5,  0.5);
	glVertex2f( 0.5,  0.5);
	glVertex2f( 0.5, -0.5);
	glEnd();
	glPopMatrix();
	
	
	// restore original fbo read and draw values
	glBindFramebufferEXT(GL_READ_FRAMEBUFFER_EXT, originalReadFBO);
	glBindFramebufferEXT(GL_DRAW_FRAMEBUFFER_EXT, originalDrawFBO);
	
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
	CGLDestroyContext(contextObject);
}

- (CGLPixelFormatObj)copyCGLPixelFormatForDisplayMask:(uint32_t)mask {
	return CGLGetPixelFormat([window_info->context CGLContextObj]);
}

- (void)releaseCGLPixelFormat:(CGLPixelFormatObj)pixelFormat {
	
}

@end
