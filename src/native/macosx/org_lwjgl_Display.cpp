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
 * Mac OS X specific library for display handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <ApplicationServices/ApplicationServices.h>
#include "org_lwjgl_Display.h"
#include "common_tools.h"
#include "tools.h"

static CFDictionaryRef original_mode;
static bool initialized = false;

static void saveMode(JNIEnv *env, long width, long height, long bpp, long freq) {
	jclass display_class = env->FindClass("org/lwjgl/Display");
	jclass jclass_DisplayMode = env->FindClass("org/lwjgl/DisplayMode");
	jmethodID ctor = env->GetMethodID(jclass_DisplayMode, "<init>", "(IIII)V");
	jobject newMode = env->NewObject(jclass_DisplayMode, ctor, width, height, bpp, freq);
	jfieldID fid_initialMode = env->GetStaticFieldID(display_class, "mode", "Lorg/lwjgl/DisplayMode;");
	env->SetStaticObjectField(display_class, fid_initialMode, newMode);
}

static void saveOriginalMode(JNIEnv *env) {
	long width;
	long height;
	long bpp;
	long freq;
	getDictLong(original_mode, kCGDisplayWidth, &width);
	getDictLong(original_mode, kCGDisplayHeight, &height);
	getDictLong(original_mode, kCGDisplayBitsPerPixel, &bpp);
	getDictLong(original_mode, kCGDisplayRefreshRate, &freq);
	saveMode(env, width, height, bpp, freq);
}

static void init(JNIEnv *env) {
	if (!initialized) {
		initialized = true;
		original_mode = CGDisplayCurrentMode(kCGDirectMainDisplay);
		saveOriginalMode(env);
	}
}

void switchMode(JNIEnv *env, long width, long height, long bpp, long freq) {
	init(env);
	CGDisplayCapture(kCGDirectMainDisplay);
	CFDictionaryRef displayMode = CGDisplayBestModeForParametersAndRefreshRate(kCGDirectMainDisplay, bpp, width, height, freq, NULL);
	CGDisplaySwitchToMode(kCGDirectMainDisplay, displayMode);
	saveMode(env, width, height, bpp, freq);
}

void resetMode(JNIEnv *env) {
	init(env);
	CGDisplaySwitchToMode(kCGDirectMainDisplay, original_mode);
	CGDisplayRelease(kCGDirectMainDisplay);
	saveOriginalMode(env);
}

JNIEXPORT void JNICALL Java_org_lwjgl_Display_init(JNIEnv * env, jclass clazz) {
	init(env);
}

JNIEXPORT void JNICALL Java_org_lwjgl_Display_setDisplayMode(JNIEnv * env, jclass clazz, jobject mode) {
	jclass cls_displayMode = env->FindClass("org/lwjgl/DisplayMode");
	jfieldID fid_width = env->GetFieldID(cls_displayMode, "width", "I");
	jfieldID fid_height = env->GetFieldID(cls_displayMode, "height", "I");
	jfieldID fid_bpp = env->GetFieldID(cls_displayMode, "bpp", "I");
	jfieldID fid_freq = env->GetFieldID(cls_displayMode, "freq", "I");
	int width = env->GetIntField(mode, fid_width);
	int height = env->GetIntField(mode, fid_height);
	int bpp = env->GetIntField(mode, fid_bpp);
	int freq = env->GetIntField(mode, fid_freq);
	switchMode(env, width, height, bpp, freq);
}

JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_Display_nGetAvailableDisplayModes(JNIEnv * env, jclass clazz) {
	CFArrayRef modes = CGDisplayAvailableModes(kCGDirectMainDisplay);
	int size = CFArrayGetCount(modes);
	int avail_modes = 0;
	for (int i = 0; i < size; i++) {
		CFDictionaryRef mode = (CFDictionaryRef)CFArrayGetValueAtIndex(modes, i);
		long bpp;
		getDictLong(mode, kCGDisplayBitsPerPixel, &bpp);
		if (bpp > 8)
			avail_modes++;
	}
	jclass displayModeClass = env->FindClass("org/lwjgl/DisplayMode");
	jobjectArray ret = env->NewObjectArray(avail_modes, displayModeClass, NULL);
	jmethodID displayModeConstructor = env->GetMethodID(displayModeClass, "<init>", "(IIII)V");
	int array_index = 0;
	for (int i = 0; i < size; i++) {
		CFDictionaryRef mode = (CFDictionaryRef)CFArrayGetValueAtIndex(modes, i);
		long width;
		long height;
		long bpp;
		long freq;
		getDictLong(mode, kCGDisplayWidth, &width);
		getDictLong(mode, kCGDisplayHeight, &height);
		getDictLong(mode, kCGDisplayBitsPerPixel, &bpp);
		getDictLong(mode, kCGDisplayRefreshRate, &freq);
		if (bpp > 8) {
			jobject displayMode = env->NewObject(displayModeClass, displayModeConstructor, width, height, bpp, freq);
			env->SetObjectArrayElement(ret, array_index++, displayMode);
		}
	}
	return ret;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_Display_getPlatform(JNIEnv * env, jclass clazz) {
	return org_lwjgl_Display_PLATFORM_AGL;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_Display_getGammaRampLength(JNIEnv *env, jclass clazz) {
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_Display_setGammaRamp(JNIEnv *env, jclass clazz, jobject gamma_ramp_buffer) {
}

JNIEXPORT void JNICALL Java_org_lwjgl_Display_resetDisplayMode(JNIEnv *env, jclass clazz) {
	resetMode(env);
}

JNIEXPORT jstring JNICALL Java_org_lwjgl_Display_getAdapter(JNIEnv * , jclass) {
	return NULL;
}

JNIEXPORT jstring JNICALL Java_org_lwjgl_Display_getVersion(JNIEnv * , jclass) {
	return NULL;
}
