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
 * Mac OS X specific display functions.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#import <Cocoa/Cocoa.h>
#import <Carbon/Carbon.h>
#import <jawt_md.h>
#import <jni.h>
#import <unistd.h>
//#import "display.h"
#import "common_tools.h"
#import "org_lwjgl_opengl_MacOSXDisplay.h"
#import "org_lwjgl_MacOSXSysImplementation.h"

#define WAIT_DELAY 100

JNIEXPORT jint JNICALL Java_org_lwjgl_DefaultSysImplementation_getJNIVersion
  (JNIEnv *env, jobject ignored) {
	return org_lwjgl_MacOSXSysImplementation_JNI_VERSION;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_restoreGamma(JNIEnv *env, jobject this) {
	CGDisplayRestoreColorSyncSettings();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_setGammaRamp(JNIEnv *env, jobject this, jobject gamma_buffer) {
	const CGGammaValue *values = (*env)->GetDirectBufferAddress(env, gamma_buffer);
	CGTableCount table_size = (*env)->GetDirectBufferCapacity(env, gamma_buffer);
	CGDisplayErr err = CGSetDisplayTransferByTable(kCGDirectMainDisplay, table_size, values, values, values);
	if (err != CGDisplayNoErr) {
		throwException(env, "Could not set display gamma");
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_MacOSXDisplay_nHideUI(JNIEnv *env, jobject this, jboolean hide) {
	if (hide == JNI_TRUE) {
		SetSystemUIMode(kUIModeContentSuppressed, 0);
	} else {
		SetSystemUIMode(kUIModeNormal, 0);
	}
}
