/* 
 * Copyright (c) 2002-2004 LWJGL Project
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

#include <X11/X.h>
#include <X11/Xlib.h>
#include "org_lwjgl_opengl_GLContext.h"
#include "extgl.h"
#include "extgl_glx.h"
#include "common_tools.h"
#include "Window.h"

#define ERR_MSG_SIZE 1024

static int current_screen;
static Display *display_connection = NULL;
static int display_connection_usage = 0;
static bool async_x_error;
static char error_message[ERR_MSG_SIZE];
static Atom warp_atom;

Atom getWarpAtom(void) {
	        return warp_atom;
}

int getCurrentScreen(void) {
	        return current_screen;
}

bool checkXError(JNIEnv *env) {
	XSync(getDisplay(), False);
	if (async_x_error) {
		async_x_error = false;
		throwException(env, error_message);
		return false;
	} else
		return true;
}

static int errorHandler(Display *disp, XErrorEvent *error) {
	char err_msg_buffer[ERR_MSG_SIZE];
	XGetErrorText(disp, error->error_code, err_msg_buffer, ERR_MSG_SIZE);
	err_msg_buffer[ERR_MSG_SIZE - 1] = '\0';
	snprintf(error_message, ERR_MSG_SIZE, "X Error - serial: %d, error_code: %s, request_code: %d, minor_code: %d", (int)error->serial, err_msg_buffer, (int)error->request_code, (int)error->minor_code);
	error_message[ERR_MSG_SIZE - 1] = '\0';
	async_x_error = true;
	return 0;
}


Display *getDisplay(void) {
	return display_connection;
}

Display *incDisplay(JNIEnv *env) {
	if (display_connection_usage == 0) {
		async_x_error = false;
		XSetErrorHandler(errorHandler);
		display_connection = XOpenDisplay(NULL);
		if (display_connection == NULL) {
			throwException(env, "Could not open X display");
			return NULL;
		}
		warp_atom = XInternAtom(getDisplay(), "ignore_warp_atom", False);
	}
	display_connection_usage++;
	return display_connection;
}

void decDisplay(void) {
	display_connection_usage--;
	if (display_connection_usage == 0)
		XCloseDisplay(display_connection);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLContext_nLoadOpenGLLibrary(JNIEnv * env, jclass clazz) {
	if (!extgl_Open()) {
		throwException(env, "Failed to load OpenGL library");
		return;
	}
	Display *disp = incDisplay(env);
	if (disp == NULL) {
		extgl_Close();
		return;
	}
	current_screen = XDefaultScreen(disp);
	if (!extgl_InitGLX(env, disp, current_screen)) {
		decDisplay();
		extgl_Close();
		throwException(env, "Could not init GLX");
		return;
	}

}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLContext_nUnloadOpenGLLibrary(JNIEnv * env, jclass clazz) {
	decDisplay();
	extgl_Close();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLContext_resetNativeStubs(JNIEnv *env, jclass clazz, jclass gl_class) {
	env->UnregisterNatives(gl_class);
}
