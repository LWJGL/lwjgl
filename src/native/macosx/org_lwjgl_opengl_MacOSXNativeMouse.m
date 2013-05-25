/* 
 * Copyright (c) 2002-2012 LWJGL Project
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
 * $Id: org_lwjgl_opengl_MacOSXNativeKeyboard.m 3055 2012-08-29 0:46:00Z mojang $
 *
 * Mac OS X native keyboard functions.
 *
 * @author mojang
 * @author kappaOne <one.kappa@gmail.com>
 * @version $Revision: 3055 $
 */

#import <AppKit/NSApplication.h>
#import <Cocoa/Cocoa.h>
#import <jni.h>
#import <unistd.h>
#import "common_tools.h"
#import "org_lwjgl_opengl_MacOSXNativeMouse.h"
#import "context.h"

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXNativeMouse_nGrabMouse(JNIEnv *env, jclass this, jboolean grab) {
	CGAssociateMouseAndMouseCursorPosition(grab == JNI_TRUE ? FALSE : TRUE);
	if (grab) {
		CGDisplayHideCursor(kCGDirectMainDisplay);
	}
	else {
		CGDisplayShowCursor(kCGDirectMainDisplay);
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXNativeMouse_nSetCursorPosition(JNIEnv *env, jclass this, jobject window_handle, jint x, jint y) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
    
	CGPoint p;
	
	if (window_info->fullscreen) {
		NSPoint point = NSMakePoint(x, y);
		
		// convert point to window/screen coordinates
		point = [window_info->view convertPoint:point fromView:nil];
		
		p.x = point.x;
		p.y = point.y;
	}
	else {
		NSRect screenRect = [[window_info->window screen] frame];
		NSRect viewRect = [window_info->view frame];
		NSRect winRect = [window_info->window frame];
		
		// get window coords of the view origin
		NSPoint viewPoint = [window_info->view convertPoint:viewRect.origin fromView:nil];
		
		// convert y to screen coordinates, origin bottom left
		p.y = winRect.origin.y + viewPoint.y + (viewRect.size.height - y - 1);
		
		p.x = winRect.origin.x + viewPoint.x + x;
		// flip y coordinates (origin top left) to allow use with CGDisplayMoveCursorToPoint
		p.y = screenRect.size.height - p.y - 1;
	}
	
	CGDisplayMoveCursorToPoint(CGMainDisplayID(), p);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXNativeMouse_nRegisterMouseListener(JNIEnv *env, jobject _this, jobject window_handle) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
	[window_info->window setAcceptsMouseMovedEvents:YES];
	window_info->jmouse = (*env)->NewGlobalRef(env, _this);
	
	// since initial mouse location is not reported until mouse is moved 
	// manually get the mouse location and report it with a fake event
	NSPoint mouseLocation = [window_info->window mouseLocationOutsideOfEventStream];
    mouseLocation = [window_info->view convertPoint:mouseLocation fromView:nil];
	
	NSEvent *mouseLocationEvent = [NSEvent
								   mouseEventWithType:NSMouseMoved
								   location:mouseLocation
								   modifierFlags:NSMouseMovedMask
								   timestamp:0
								   windowNumber:[window_info->window windowNumber]
								   context:nil
								   eventNumber:0
								   clickCount:0
								   pressure:0];
	
	[window_info->view mouseMoved:mouseLocationEvent];
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXNativeMouse_nUnregisterMouseListener(JNIEnv *env, jobject this, jobject window_handle) {
	MacOSXWindowInfo *window_info = (MacOSXWindowInfo *)(*env)->GetDirectBufferAddress(env, window_handle);
	[window_info->window setAcceptsMouseMovedEvents:NO];
	window_info->jmouse = nil;
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_MacOSXNativeMouse_nCreateCursor(JNIEnv *env, jobject _this, jint width, jint height, jint x_hotspot, jint y_hotspot, jint num_images, jobject image_buffer, jint images_offset, jobject delay_buffer, jint delays_offset) {
	NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
	
	jlong *bytes = (jint *)(*env)->GetDirectBufferAddress(env, image_buffer) + images_offset;
	
	NSBitmapImageRep *bitmap = [[[NSBitmapImageRep alloc]
								initWithBitmapDataPlanes:NULL
								pixelsWide:width pixelsHigh:height
								bitsPerSample:8
								samplesPerPixel:4
								hasAlpha:YES
								isPlanar:NO
								colorSpaceName:NSDeviceRGBColorSpace
								bitmapFormat:NSAlphaNonpremultipliedBitmapFormat
								bytesPerRow:width*4
								bitsPerPixel:32] autorelease];
	memcpy((void*)bitmap.bitmapData, (void*)bytes, width*height*4);
	
	NSImage *image = [[[NSImage alloc] initWithSize:NSMakeSize(width, height)] autorelease];
	
	[image addRepresentation:bitmap];
	
	
	NSCursor *cursor = [[NSCursor alloc] initWithImage:image hotSpot:NSMakePoint(x_hotspot, y_hotspot)];
	
	[pool release];
	
	return (jlong)cursor;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXNativeMouse_nDestroyCursor(JNIEnv *env, jobject _this, jlong cursor_pointer) {
	if (cursor_pointer != 0) {
		NSCursor *cursor = (NSCursor *)cursor_pointer;
		[cursor release];
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXNativeMouse_nSetCursor(JNIEnv *env, jobject _this, jlong cursor_pointer) {
	if (cursor_pointer == 0) {
		// restore default cursor
		[[NSCursor arrowCursor] performSelectorOnMainThread:@selector(set) withObject:nil waitUntilDone:NO];
	}
	else {
		NSCursor *cursor = (NSCursor *)cursor_pointer;
		[cursor performSelectorOnMainThread:@selector(set) withObject:nil waitUntilDone:NO];
	}
}
