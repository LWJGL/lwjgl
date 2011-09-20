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
 * Linux keyboard handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision: 2399 $
 */

#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/XKBlib.h>
#include <X11/Xutil.h>
#include <X11/keysym.h>
#include "common_tools.h"
#include "org_lwjgl_opengl_LinuxKeyboard.h"

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_getModifierMapping(JNIEnv *env, jclass unused, jlong display_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	XModifierKeymap *modifier_map = XGetModifierMapping(disp);
	return (intptr_t)modifier_map;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_nSetDetectableKeyRepeat(JNIEnv *env, jclass unused, jlong display_ptr, jboolean set_enabled) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Bool enabled = set_enabled == JNI_TRUE ? True : False;
	Bool result = XkbSetDetectableAutoRepeat(disp, enabled, NULL);
	return result == enabled ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_freeModifierMapping(JNIEnv *env, jclass unused, jlong mapping_ptr) {
	XModifierKeymap *modifier_map = (XModifierKeymap *)(intptr_t)mapping_ptr;
	XFreeModifiermap(modifier_map);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_getMaxKeyPerMod(JNIEnv *env, jclass unsused, jlong mapping_ptr) {
	XModifierKeymap *modifier_map = (XModifierKeymap *)(intptr_t)mapping_ptr;
	return modifier_map->max_keypermod;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_lookupModifierMap(JNIEnv *env, jclass unused, jlong mapping_ptr, jint index) {
	XModifierKeymap *modifier_map = (XModifierKeymap *)(intptr_t)mapping_ptr;
	KeyCode key_code = modifier_map->modifiermap[index];
	return key_code;
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_keycodeToKeySym(JNIEnv *env, jclass unused, jlong display_ptr, jint key_code) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	KeySym key_sym = XKeycodeToKeysym(disp, key_code, 0);
	return key_sym;
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_openIM(JNIEnv *env, jclass unused, jlong display_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	XSetLocaleModifiers ("@im=none");
	XIM xim = XOpenIM(disp, NULL, NULL, NULL);
	return (intptr_t)xim;
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_createIC(JNIEnv *env, jclass unused, jlong xim_ptr, jlong window_ptr) {
	Window win = (Window)window_ptr;
	XIM xim = (XIM)(intptr_t)xim_ptr;
	XIC xic = XCreateIC(xim,  XNClientWindow, win, XNFocusWindow, win, XNInputStyle, XIMPreeditNothing | XIMStatusNothing, NULL);
	return (intptr_t)xic;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_setupIMEventMask(JNIEnv *env, jclass unused, jlong display_ptr, jlong window_ptr, jlong xic_ptr) {
	Display *disp = (Display *)(intptr_t)display_ptr;
	Window win = (Window)window_ptr;
	XIC xic = (XIC)(intptr_t)xic_ptr;
	long im_event_mask;
	XWindowAttributes win_attributes;

	XGetWindowAttributes(disp, win, &win_attributes);
	XGetICValues(xic, XNFilterEvents, &im_event_mask, NULL);
	XSelectInput(disp, win, win_attributes.your_event_mask | im_event_mask);
	XSetICFocus(xic);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_destroyIC(JNIEnv *env, jclass unused, jlong xic_ptr) {
	XIC xic = (XIC)(intptr_t)xic_ptr;
	XDestroyIC(xic);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_closeIM(JNIEnv *env, jclass unused, jlong xim_ptr) {
	XIM xim = (XIM)(intptr_t)xim_ptr;
	XCloseIM(xim);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_lookupKeysym(JNIEnv *env, jclass unused, jlong event_ptr, jint index) {
	XKeyEvent *event = (XKeyEvent *)(intptr_t)event_ptr;
	return XLookupKeysym(event, index);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_toUpper(JNIEnv *env, jclass unused, jlong keysym) {
	KeySym lower_case, upper_case;
	XConvertCase(keysym, &lower_case, &upper_case);
	return upper_case;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_lookupString(JNIEnv *env, jclass unused, jlong event_ptr, jobject buffer_obj, jobject compose_status_obj) {
	XKeyEvent *event = (XKeyEvent *)(intptr_t)event_ptr;
	char *buffer = (char *)(*env)->GetDirectBufferAddress(env, buffer_obj);
	int capacity = (*env)->GetDirectBufferCapacity(env, buffer_obj);
	XComposeStatus *status = (XComposeStatus *)(*env)->GetDirectBufferAddress(env, compose_status_obj);
	return XLookupString(event, buffer, capacity, NULL, status);
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_allocateComposeStatus(JNIEnv *env, jclass unused) {
	return newJavaManagedByteBuffer(env, sizeof(XComposeStatus));
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_LinuxKeyboard_utf8LookupString(JNIEnv *env, jclass unused, jlong xic_ptr, jlong event_ptr, jobject buffer_obj, jint buffer_position, jint buffer_size) {
	XIC xic = (XIC)(intptr_t)xic_ptr;
	XKeyEvent *event = (XKeyEvent *)(intptr_t)event_ptr;
	char *buffer = buffer_position + (char *)(*env)->GetDirectBufferAddress(env, buffer_obj);
	Status status;
	size_t num_bytes = Xutf8LookupString(xic, event, buffer, buffer_size, NULL, &status);
	positionBuffer(env, buffer_obj, num_bytes);
	return status;
}
