/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
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
 * Base OSX functionality for GL.
 *
 * @author Elias Naur <elias_naur@sourceforge.net>
 * @version $Revision$
 */

#include <Carbon/Carbon.h>
#include <JavaVM/jni.h>
#include "org_lwjgl_opengl_Window.h"

static WindowRef win_ref;
static bool close_requested;
 
/* 
 * Utility function to throw an Exception
 */
static void throwException(JNIEnv * env, const char * err)
{
	jclass cls = env->FindClass("java/lang/Exception");
	env->ThrowNew(cls, err);
	env->DeleteLocalRef(cls);
}

static void setWindowTitle(JNIEnv *env, jstring title_obj) {
	const char* title = env->GetStringUTFChars(title_obj, NULL);
	int str_len = env->GetStringUTFLength(title_obj);
	CFStringRef cf_title = CFStringCreateWithBytes(NULL, (const UInt8*)title, str_len, kCFStringEncodingUTF8, false);
	if (cf_title == NULL) {
#ifdef _DEBUG
		printf("Could not set window title\n");
#endif
		return;
	}
	SetWindowTitleWithCFString(win_ref, cf_title);
	CFRelease(cf_title);
	env->ReleaseStringUTFChars(title_obj, title);
}

static pascal OSStatus doWindowClose(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
	close_requested = true;
	return noErr;
}

static void registerEventHandlers(void) {
/*	EventTargetRef event_target = GetWindowEventTarget(win_ref);
	status = InstallStandardEventHandler(event_target);
	if (noErr != status) {
		DisposeWindow(win_ref);
		throwException(env, "Could not install default window event handler");
		return;
	}*/
	EventTypeSpec eventType;
	eventType.eventClass = kEventClassWindow;
	eventType.eventKind  = kEventWindowClose;
	EventHandlerUPP handlerUPP = NewEventHandlerUPP(doWindowClose);
	InstallWindowEventHandler(win_ref, handlerUPP, 1, &eventType, NULL, NULL);
	DisposeEventHandlerUPP(handlerUPP);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsCloseRequested(JNIEnv *, jclass) {
	const bool saved = close_requested;
	close_requested = false;
	return saved;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nCreate(JNIEnv *env, jclass clazz, jstring title, jint x, jint y, jint width, jint height, jboolean fullscreen, jint bpp, jint alpha, jint depth, jint stencil) {
	Rect rect;
	OSStatus status;
	const WindowAttributes window_attr = kWindowStandardDocumentAttributes/*kWindowCloseBoxAttribute|
				       kWindowCollapseBoxAttribute*/|
				       kWindowStandardHandlerAttribute;

	SetRect(&rect, x, y, x + width, y + height);
	close_requested = false;
	
	status = CreateNewWindow(kDocumentWindowClass, window_attr, &rect, &win_ref);
	if (noErr != status) {
		throwException(env, "Could not create window");
		return;
	}
/*	setWindowTitle(env, title);
	const RGBColor background_color = { 0, 0, 0 };
	status = SetWindowContentColor(win_ref, &background_color);
	if (noErr != status) {
		DisposeWindow(win_ref);
		throwException(env, "Could not alter window background color");
		return;
	}
	registerEventHandlers();*/
	status = TransitionWindow(win_ref, kWindowZoomTransitionEffect, kWindowShowTransitionAction, NULL);
	if (noErr != status) {
		DisposeWindow(win_ref);
		throwException(env, "Could not show window");
		return;
	}
//	SelectWindow(win_ref);
	InitCursor();
	RunApplicationEventLoop();
	printf("Window creation succeeded\n");
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nDestroy
  (JNIEnv *env, jclass clazz)
{
	DisposeWindow(win_ref);
}
