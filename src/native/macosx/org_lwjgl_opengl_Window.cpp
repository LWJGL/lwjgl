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
#include "org_lwjgl_opengl_Window.h"
#include "extgl.h"
#include "tools.h"

static WindowRef win_ref;
static AGLContext context;
static bool close_requested;
 
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
	close_requested = true;
}

static pascal OSStatus doQuit(EventHandlerCallRef next_handler, EventRef event, void *user_data) {
	setQuitRequested();
	return noErr;
}

static void registerEventHandlers(JNIEnv *env) {
	EventTypeSpec event_types[2];
	OSStatus err;
	EventHandlerUPP handler_upp = NewEventHandlerUPP(doQuit);
	event_types[0].eventClass = kEventClassWindow;
	event_types[0].eventKind  = kEventWindowClose;
	err = InstallWindowEventHandler(win_ref, handler_upp, 1, event_types, NULL, NULL);
	DisposeEventHandlerUPP(handler_upp);
	if (noErr != err) {
		throwException(env, "Could not register window event handler");
		return;
	}
}

static void destroy(void) {
	aglSetCurrentContext(NULL);
	aglDestroyContext(context);
	DisposeWindow(win_ref);
	extgl_Close();
}

static bool createContext(JNIEnv *env, jint bpp, jint alpha, jint depth, jint stencil) {
	AGLDrawable drawable = GetWindowPort(win_ref);
	SetPort(drawable);
	GLint attrib[] = {AGL_RGBA, 
			  AGL_DOUBLEBUFFER, 
			  AGL_ACCELERATED,
			  //AGL_FULLSCREEN,
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
	const bool saved = close_requested;
	close_requested = false;
	return saved;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nCreate(JNIEnv *env, jclass clazz, jstring title, jint x, jint y, jint width, jint height, jboolean fullscreen, jint bpp, jint alpha, jint depth, jint stencil, jobject ext_set) {
	Rect rect;
	OSStatus status;
	const WindowAttributes window_attr = kWindowCloseBoxAttribute|
				       kWindowCollapseBoxAttribute|
				       kWindowStandardHandlerAttribute;
	SetRect(&rect, x, y, x + width, y + height);
	close_requested = false;
	if (!extgl_Open()) {
		throwException(env, "Could not load gl library");
		return;
	}
	if (!extgl_InitAGL(env, ext_set)) {
		throwException(env, "Could not load agl symbols");
		return;
	}
	status = CreateNewWindow(kDocumentWindowClass, window_attr, &rect, &win_ref);
	if (noErr != status) {
		throwException(env, "Could not create window");
		return;
	}
	registerEventHandlers(env);
	setWindowTitle(env, title);
	const RGBColor background_color = { 0, 0, 0 };
	SetWindowContentColor(win_ref, &background_color);
	if (!createContext(env, bpp, alpha, depth, stencil)) {
		DisposeWindow(win_ref);
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
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_update
  (JNIEnv *env, jclass clazz) 
{
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_swapBuffers(JNIEnv * env, jclass clazz)
{                                                                                                                                                                                              
	aglSwapBuffers(context);
}
                                                                                                                                                                

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nDestroy
  (JNIEnv *env, jclass clazz)
{
	destroy();
}
