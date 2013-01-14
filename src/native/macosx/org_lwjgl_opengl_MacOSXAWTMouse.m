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
 * Mac OS X mouse handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <jni.h>
#include <ApplicationServices/ApplicationServices.h>
#include "org_lwjgl_opengl_MacOSXMouseEventQueue.h"
#include "common_tools.h"

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXMouseEventQueue_nGrabMouse(JNIEnv *env, jclass unused, jboolean grab) {
	CGAssociateMouseAndMouseCursorPosition(grab == JNI_TRUE ? FALSE : TRUE);
	if (grab)
		CGDisplayHideCursor(kCGDirectMainDisplay);
	else
		CGDisplayShowCursor(kCGDirectMainDisplay);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXMouseEventQueue_nWarpCursor(JNIEnv *env, jclass unused, jint x, jint y) {
	CGPoint p;
	p.x = x;
	p.y = y;
	CGWarpMouseCursorPosition(p);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXMouseEventQueue_getMouseDeltas(JNIEnv *env, jclass unused, jobject delta_buffer) {
	CGMouseDelta dx, dy;
	CGGetLastMouseDelta(&dx, &dy);
	int buffer_length = (*env)->GetDirectBufferCapacity(env, delta_buffer);
	if (buffer_length != 2) {
		printfDebugJava(env, "Delta buffer not large enough!");
		return;
	}
	jint *buffer = (*env)->GetDirectBufferAddress(env, delta_buffer);
	buffer[0] = dx;
	buffer[1] = dy;
}