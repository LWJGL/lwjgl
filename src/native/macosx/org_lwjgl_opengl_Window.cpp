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

#include "Window.h"
#include <QuickTime/Movies.h>
#include "org_lwjgl_opengl_Window.h"
#include "extgl.h"
#include "tools.h"

static WindowRef win_ref;
static AGLContext context;
static bool close_requested;
static Ptr fullscreen_ptr;
static bool current_fullscreen;
static bool miniaturized;
static bool activated;
 
static void setWindowTitle(JNIEnv *env, jstring title_obj) {
	const char* title = env->GetStringUTFChars(title_obj, NULL);
	CFStringRef cf_title = CFStringCreateWithCString(NULL, title, kCFStringEncodingUTF8);
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

void setQuitRequested(void) {
	lock();
	close_requested = true;
	unlock();
}

static pascal OSStatus doMiniaturized(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
	lock();
	miniaturized = true;
	unlock();
	return eventNotHandledErr;
}

static pascal OSStatus doMaximize(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
	lock();
	miniaturized = false;
	unlock();
	return eventNotHandledErr;
}

static pascal OSStatus doActivate(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
	lock();
	miniaturized = false;
	activated = true;
	unlock();
	return eventNotHandledErr;
}

static pascal OSStatus doDeactivate(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
	lock();
	activated = false;
	unlock();
	return eventNotHandledErr;
}

static pascal OSStatus doQuit(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
	setQuitRequested();
	return noErr;
}

static bool registerEventHandlers(JNIEnv *env) {
	bool error;
	error = registerHandler(env, win_ref, doQuit, kEventClassWindow, kEventWindowClose);
	error = error || registerHandler(env, win_ref, doActivate, kEventClassWindow, kEventWindowActivated);
	error = error || registerHandler(env, win_ref, doDeactivate, kEventClassWindow, kEventWindowDeactivated);
	error = error || registerHandler(env, win_ref, doMiniaturized, kEventClassWindow, kEventWindowCollapsed);
	error = error || registerHandler(env, win_ref, doMaximize, kEventClassWindow, kEventWindowExpanded);
	return !error && registerKeyboardHandler(env, win_ref) && registerMouseHandler(env, win_ref);
}

static void destroyWindow(void) {
	if (current_fullscreen)
		EndFullScreen(fullscreen_ptr, 0);
	else
		DisposeWindow(win_ref);
}

static void destroy(void) {
	aglSetCurrentContext(NULL);
	aglDestroyContext(context);
	destroyWindow();
	extgl_Close();
	destroyLock();
}

static bool createContext(JNIEnv *env, jint bpp, jint alpha, jint depth, jint stencil) {
	AGLDrawable drawable = GetWindowPort(win_ref);
	SetPort(drawable);
	GLint attrib[] = {AGL_RGBA, 
			  AGL_DOUBLEBUFFER, 
			  AGL_ACCELERATED,
			  AGL_NO_RECOVERY,
			  AGL_MINIMUM_POLICY,
			  AGL_PIXEL_SIZE, bpp,
			  AGL_DEPTH_SIZE, depth, 
			  AGL_ALPHA_SIZE, alpha,
			  AGL_STENCIL_SIZE, stencil,
			  AGL_NONE};
	AGLPixelFormat format = aglChoosePixelFormat(NULL, 0, attrib);
	if (format == NULL) {
		throwException(env, "Could not find matching pixel format");
		return false;
	}
	context = aglCreateContext (format, NULL);
	aglDestroyPixelFormat(format);
	if (context == NULL) {
		throwException(env, "Could not create context");
		return false;
	}
	if (aglSetDrawable(context, drawable) == GL_FALSE) {
		aglDestroyContext(context);
		throwException(env, "Could not attach context");
		return false;
	}
	if (aglSetCurrentContext(context) == GL_FALSE) {
		aglDestroyContext(context);
		throwException(env, "Could not set current context");
		return false;
	}
	return true;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsCloseRequested(JNIEnv *, jclass) {
	bool saved;
	lock();
	saved = close_requested;
	close_requested = false;
	unlock();
	return saved;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nCreate(JNIEnv *env, jclass clazz, jstring title, jint x, jint y, jint width, jint height, jboolean fullscreen, jint bpp, jint alpha, jint depth, jint stencil, jobject ext_set) {
	Rect rect;
	OSStatus status;
	const WindowAttributes window_attr = kWindowCloseBoxAttribute|
				       kWindowCollapseBoxAttribute|
				       kWindowStandardHandlerAttribute;
	SetRect(&rect, x, y, x + width, y + height);
	current_fullscreen = fullscreen == JNI_TRUE;
	miniaturized = false;
	activated = true;
	close_requested = false;
	if (!extgl_Open()) {
		throwException(env, "Could not load gl library");
		return;
	}
	if (!extgl_InitAGL(env, ext_set)) {
		throwException(env, "Could not load agl symbols");
		return;
	}
	if (current_fullscreen)
		status = BeginFullScreen(&fullscreen_ptr, NULL, 0, 0, &win_ref, NULL, 0);
	else
		status = CreateNewWindow(kDocumentWindowClass, window_attr, &rect, &win_ref);
	if (noErr != status) {
		extgl_Close();
		throwException(env, "Could not create window");
		return;
	}
	if (!registerEventHandlers(env)) {
		destroyWindow();
		extgl_Close();
		return;
	}
	if (!initLock(env)) {
		destroyWindow();
		extgl_Close();
		return;
	}
	setWindowTitle(env, title);
	const RGBColor background_color = {0, 0, 0};
	SetWindowContentColor(win_ref, &background_color);
	if (!createContext(env, bpp, alpha, depth, stencil)) {
		destroyLock();
		destroyWindow();
		extgl_Close();
		return;
	}
	if (!extgl_Initialize(env, ext_set)) {
		destroy();
		throwException(env, "Could not load gl function pointers");
		return;
	}
	ShowWindow(win_ref);
	SelectWindow(win_ref);
	CGPoint p = {x, y};
	CGWarpMouseCursorPosition(p);
	CGPostMouseEvent(p, FALSE, 1, TRUE);
	CGPostMouseEvent(p, FALSE, 1, FALSE);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nSetTitle(JNIEnv * env, jclass clazz, jstring title_obj) {
	  setWindowTitle(env, title_obj);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_update(JNIEnv *env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_swapBuffers(JNIEnv * env, jclass clazz) {                                                                                                                                                                                              
	aglSwapBuffers(context);
}
                                                                                                                                                                
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_minimize(JNIEnv *env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_restore(JNIEnv *env, jclass clazz) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nDestroy(JNIEnv *env, jclass clazz) {
	destroy();
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsFocused(JNIEnv *env, jclass clazz) {
	bool result;
	lock();
	result = activated;
	unlock();
	return result;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsDirty(JNIEnv *env, jclass clazz) {
	return JNI_FALSE;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsMinimized(JNIEnv *env, jclass clazz) {
	bool result;
	lock();
	result = miniaturized;
	unlock();
	return result;
}
