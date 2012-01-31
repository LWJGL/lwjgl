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
 * $Id: org_lwjgl_input_Keyboard.c 2399 2006-06-30 19:28:00Z elias_naur $
 *
 * Linux mouse handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision: 2399 $
 */

#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include "common_tools.h"
#include "org_lwjgl_opengl_LinuxMouse.h"

static void getWindowAttributes(jlong display_ptr, jlong window_ptr, XWindowAttributes *attr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = (Window)window_ptr;
	XGetWindowAttributes(disp, win, attr);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxMouse_nGetWindowHeight(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr) {
	XWindowAttributes window_attributes;
	getWindowAttributes(display_ptr, window_ptr, &window_attributes);
	return window_attributes.height;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxMouse_nGetWindowWidth(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr) {
	XWindowAttributes window_attributes;
	getWindowAttributes(display_ptr, window_ptr, &window_attributes);
	return window_attributes.width;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxMouse_nWarpCursor(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr, jint x, jint y) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = (Window)window_ptr;
	XWarpPointer(disp, None, win, 0, 0, 0, 0, x, y);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxMouse_nQueryPointer(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr, jobject result_buffer) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = (Window)window_ptr;
	Window root_return, child_return;
	int root_x, root_y, win_x, win_y;
	unsigned int mask_return;
	jint *result = (jint *)(*env)->GetDirectBufferAddress(env, result_buffer);
	int result_size = (*env)->GetDirectBufferCapacity(env, result_buffer);
	if (result_size < 4) {
		throwFormattedException(env, "Not enough space in result buffer (%d)", result_size);
		return (intptr_t)NULL;
	}
	
	XQueryPointer(disp, win, &root_return, &child_return, &root_x, &root_y, &win_x, &win_y, &mask_return);
	result[0] = root_x;
	result[1] = root_y;
	result[2] = win_x;
	result[3] = win_y;
	return root_return;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxMouse_nSendWarpEvent(JNIEnv *env, jclass unusued, jlong display_ptr, jlong window_ptr, jlong warp_atom_ptr, jint x, jint y) {
	Atom warp_atom = (Atom)warp_atom_ptr;
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = (Window)window_ptr;
	XEvent warp_event;
	warp_event.type = ClientMessage;
	warp_event.xclient.window = win;
	warp_event.xclient.message_type = warp_atom;
	warp_event.xclient.format = 32;
	warp_event.xclient.data.l[0] = x;
	warp_event.xclient.data.l[1] = y;
	XSendEvent(disp, win, False, 0, &warp_event);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxMouse_nGetButtonCount(JNIEnv *env, jclass unused, jlong display_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;

	int count = 256;

	unsigned char * pointer_map = malloc(sizeof(unsigned char) * count);
	count = XGetPointerMapping(disp, pointer_map, count);

	free(pointer_map);

	return count;
}
