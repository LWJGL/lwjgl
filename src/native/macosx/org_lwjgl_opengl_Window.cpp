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

/*static void dumpRootMenu() {
	MenuRef root = AcquireRootMenu();
	if (root == NULL)
		printf("NULL menu\n");
	UInt16 count = CountMenuItems(root);
	printf("item count: %d\n", count);
	for (int i = 0; i < count; i++) {
		Str255 menu_text;
		GetMenuItemText(root, i, menu_text);
		printf("Item text: %s\n", menu_text);
	}
	ReleaseMenu(root);
}
*/
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
/*	MenuRef root;
	OSStatus err = CreateNewMenu(1, 0, &root);
	assert(err == noErr);
	MenuItemIndex item_index;
	err = AppendMenuItemTextWithCFString(root, cf_title, 0, FOUR_CHAR_CODE('1234'), &item_index);
	assert(err == noErr);
	err = SetRootMenu(root);
	assert(err == noErr);
	ReleaseMenu(root);*/
	CFRelease(cf_title);
	env->ReleaseStringUTFChars(title_obj, title);
//	dumpRootMenu();
}

static pascal OSStatus doQuit(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
printf("Close requested\n");
	close_requested = true;
	return noErr;
}

static void registerEventHandlers(JNIEnv *env) {
	/*EventTargetRef event_target = GetWindowEventTarget(win_ref);
	status = InstallStandardEventHandler(event_target);
	if (noErr != status) {
		DisposeWindow(win_ref);
		throwException(env, "Could not install default window event handler");
		return;
	}*/
	EventTypeSpec event_types[1];
	OSStatus err;
	EventHandlerUPP handler_upp = NewEventHandlerUPP(doQuit);
	event_types[0].eventClass = kEventClassWindow;
	event_types[0].eventKind  = kEventWindowClose;
	err = InstallWindowEventHandler(win_ref, handler_upp, 1, event_types, NULL, NULL);
	if (noErr != err) {
		DisposeEventHandlerUPP(handler_upp);
		throwException(env, "Could not register window event handler");
		return;
	}
	event_types[0].eventClass = kEventClassApplication;
	event_types[0].eventKind = kEventAppQuit;
	/*event_types[1].eventClass = kEventClassAppleEvent;
	event_types[1].eventKind = kEventAppleEvent;*/
	err = InstallApplicationEventHandler(handler_upp, 1, event_types, NULL, NULL);
	if (noErr != err) {
		DisposeEventHandlerUPP(handler_upp);
		throwException(env, "Could not register application event handler");
		return;
	}
	DisposeEventHandlerUPP(handler_upp);
}


JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsCloseRequested(JNIEnv *, jclass) {
	const bool saved = close_requested;
	close_requested = false;
	return saved;
}

/*OSErr aehandler(const AppleEvent * theAppleEvent, AppleEvent * reply, SInt32 handlerRefcon) {
	printf("handler called\n");
	return noErr;
}
*/

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nCreate(JNIEnv *env, jclass clazz, jstring title, jint x, jint y, jint width, jint height, jboolean fullscreen, jint bpp, jint alpha, jint depth, jint stencil, jobject ext_set) {
	Rect rect;
	OSStatus status;
	const WindowAttributes window_attr = kWindowCloseBoxAttribute|
				       kWindowCollapseBoxAttribute|
				       kWindowStandardHandlerAttribute;
//	CPSEnableForegroundOperation();
	/*//AEObjectInit();
	AEEventHandlerUPP handler = NewAEEventHandlerUPP(aehandler);
	if (noErr != AEInstallEventHandler(kCoreEventClass, kAEOpenApplication, handler, 0, FALSE))
		printf("error\n");
	if (noErr != AEInstallEventHandler(kCoreEventClass, kAEOpenDocuments, handler, 0, FALSE))
		printf("error\n");
	if (noErr != AEInstallEventHandler(kCoreEventClass, kAEPrintDocuments, handler, 0, FALSE))
		printf("error\n");
	if (noErr != AEInstallEventHandler(kCoreEventClass, kAEQuitApplication, handler, 0, FALSE))
		printf("error\n");
	DisposeAEEventHandlerUPP(handler);*/
	/*
	 * Hacks to activate the application window
	 */
	/*CFBundleRef bundle = CFBundleGetMainBundle();
	if (bundle != NULL) {
		printf("bundle != NULL\n");
		CFRelease(bundle);
	}*/
	/*ProcessSerialNumber PSN;
 	GetCurrentProcess(&PSN);
 	SetFrontProcess(&PSN);*/
 	EventLoopRef queue = GetCurrentEventLoop();
	EventLoopRef main = GetMainEventLoop();
	QuitEventLoop(queue);
	QuitEventLoop(main);
	/*if (queue == main)
		printf("equals\n");*/
	SetRect(&rect, x, y, x + width, y + height);
	close_requested = false;
	
	status = CreateNewWindow(kDocumentWindowClass, window_attr, &rect, &win_ref);
	if (noErr != status) {
		throwException(env, "Could not create window");
		return;
	}
	registerEventHandlers(env);
	setWindowTitle(env, title);
	const RGBColor background_color = { 0, 0, 0 };
	SetWindowContentColor(win_ref, &background_color);
	status = TransitionWindow(win_ref, kWindowZoomTransitionEffect, kWindowShowTransitionAction, NULL);
	if (noErr != status) {
		DisposeWindow(win_ref);
		throwException(env, "Could not show window");
		return;
	}
	SelectWindow(win_ref);
	InitCursor();
//QuitApplicationEventLoop();
//RunApplicationEventLoop();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_update
  (JNIEnv *env, jclass clazz) 
{
	/*EventRef event;
	OSStatus err;
	int num_events = GetNumEventsInQueue(GetCurrentEventQueue());
	for (int i = 0; i < num_events; i++) {
		UInt32 class_type = GetEventClass(event);
		UInt32 kind = GetEventKind(event);
		UInt32 test = FOUR_CHAR_CODE('eppc');
		EventTime event_time = GetEventTime(event);
		//UInt32 test = FOUR_CHAR_CODE('appl');
		printf("recieved event: %x %x %x at %f\n", class_type, kind, test, event_time);
		err = ReceiveNextEvent(0, NULL, 0, true, &event);
	/*	if (kind == kEventClassAppleEvent)
			AEProcessAppleEvent(event);*/
	  /*      ReleaseEvent(event);
	}
	//RunCurrentEventLoop(0);
/*	if (eventLoopTimedOutErr != RunCurrentEventLoop(0))
		printf("Could not run current event loop\n");*/
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nDestroy
  (JNIEnv *env, jclass clazz)
{
	DisposeWindow(win_ref);
}
