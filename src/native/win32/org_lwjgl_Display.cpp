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
 * Win32 specific library for display handdling.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#include "Window.h"
#include "org_lwjgl_Display.h"

#define WINDOWCLASSNAME "LWJGLWINDOW"

jobjectArray GetAvailableDisplayModesNT(JNIEnv * env);
jobjectArray GetAvailableDisplayModes9x(JNIEnv * env);



/*
 * Class:     org_lwjgl_Display
 * Method:    nGetAvailableDisplayModes
 * Signature: ()[Lorg/lwjgl/DisplayMode;
 */
JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_Display_nGetAvailableDisplayModes
  (JNIEnv * env, jclass clazz)
{
  // Determine whether to use a display name or NULL, which depends on the operating
	// system
	OSVERSIONINFO osvi;

	osvi.dwOSVersionInfoSize = sizeof(osvi);
	GetVersionEx(&osvi);
	
	if (osvi.dwPlatformId == VER_PLATFORM_WIN32_NT) {
#ifdef _DEBUG
  printf("Selecting NT display mode check");
#endif	
    return GetAvailableDisplayModesNT(env);
  }
  
#ifdef _DEBUG
  printf("Selecting 9x display mode check");
#endif	
  return GetAvailableDisplayModes9x(env);
}

/**
 * Choose displaymodes using NT codepath (multiple displaydevices)
 */
jobjectArray GetAvailableDisplayModesNT(JNIEnv * env) {
	int i = 0, j = 0, n = 0;
	int AvailableModes = 0;

	DISPLAY_DEVICE DisplayDevice;
	DEVMODE DevMode;

	DevMode.dmSize = sizeof(DEVMODE);
	DisplayDevice.cb = sizeof(DISPLAY_DEVICE);
  
  //enumerate all displays, and all of their displaymodes
	while(EnumDisplayDevices(NULL, i++, &DisplayDevice, 0) != 0) {
		while(EnumDisplaySettings(DisplayDevice.DeviceName, j++, &DevMode) != 0) {
			if (DevMode.dmBitsPerPel > 8) {
				AvailableModes++;
			}
		}
	}
		
#ifdef _DEBUG
	printf("Found %d displaymodes\n", AvailableModes);
#endif
  
	// now that we have the count create the classes, and add 'em all - we'll remove dups in Java
	// Allocate an array of DisplayModes big enough
	jclass displayModeClass = env->FindClass("org/lwjgl/DisplayMode");

	jobjectArray ret = env->NewObjectArray(AvailableModes, displayModeClass, NULL);
	jmethodID displayModeConstructor = env->GetMethodID(displayModeClass, "<init>", "(IIII)V");
  
	i = 0, j = 0, n = 0;
	while(EnumDisplayDevices(NULL, i++, &DisplayDevice, 0) != 0) {
		while(EnumDisplaySettings(DisplayDevice.DeviceName, j++, &DevMode) != 0) {
			// Filter out indexed modes
			if (DevMode.dmBitsPerPel > 8) {
				jobject displayMode;
				displayMode = env->NewObject(displayModeClass, displayModeConstructor, 
											 DevMode.dmPelsWidth, DevMode.dmPelsHeight,
											 DevMode.dmBitsPerPel, DevMode.dmDisplayFrequency);

				env->SetObjectArrayElement(ret, n++, displayMode);
			}
		}
	}
	return ret;
}

/**
 * Choose displaymodes using 9x codepath (single displaydevice)
 */
jobjectArray GetAvailableDisplayModes9x(JNIEnv * env) {
	int i = 0, j = 0, n = 0;
	int AvailableModes = 0;

	DEVMODE DevMode;

	DevMode.dmSize = sizeof(DEVMODE);
  
	//enumerate all displaymodes
	while(EnumDisplaySettings(NULL, j++, &DevMode) != 0) {
		if (DevMode.dmBitsPerPel > 8) {
			AvailableModes++;
		}
	}
		
#ifdef _DEBUG
	printf("Found %d displaymodes\n", AvailableModes);
#endif
  
	// now that we have the count create the classes, and add 'em all - we'll remove dups in Java
	// Allocate an array of DisplayModes big enough
	jclass displayModeClass = env->FindClass("org/lwjgl/DisplayMode");

	jobjectArray ret = env->NewObjectArray(AvailableModes, displayModeClass, NULL);
	jmethodID displayModeConstructor = env->GetMethodID(displayModeClass, "<init>", "(IIII)V");  
  
	i = 0, j = 0, n = 0;
	while(EnumDisplaySettings(NULL, j++, &DevMode) != 0) {
		// Filter out indexed modes
		if (DevMode.dmBitsPerPel > 8) {
			jobject displayMode;
			displayMode = env->NewObject(displayModeClass, displayModeConstructor,
			                              DevMode.dmPelsWidth, DevMode.dmPelsHeight,
						                  DevMode.dmBitsPerPel, DevMode.dmDisplayFrequency);
			env->SetObjectArrayElement(ret, n++, displayMode);
		}
	}
	return ret;
}


/*
 * Class:     org_lwjgl_Display
 * Method:    getPlatform
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_Display_getPlatform
  (JNIEnv * env, jclass clazz)
{
	return org_lwjgl_Display_PLATFORM_WGL;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    setDisplayMode
 * Signature: (Lorg/lwjgl/DisplayMode;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Display_setDisplayMode
  (JNIEnv * env, jclass clazz, jobject mode)
{

	jfieldID fid_width = env->GetFieldID(clazz, "width", "I");
	jfieldID fid_height = env->GetFieldID(clazz, "height", "I");
	jfieldID fid_bpp = env->GetFieldID(clazz, "bpp", "I");
	jfieldID fid_freq = env->GetFieldID(clazz, "freq", "I");

	int width = env->GetIntField(mode, fid_width);
	int height = env->GetIntField(mode, fid_height);
	int bpp = env->GetIntField(mode, fid_bpp);
	int freq = env->GetIntField(mode, fid_freq);


	DEVMODE devmode;
	devmode.dmSize = sizeof(DEVMODE);
	devmode.dmBitsPerPel = bpp;
	devmode.dmPelsWidth = width;
	devmode.dmPelsHeight = height;
	devmode.dmDisplayFlags = 0;
	devmode.dmDisplayFrequency = freq;
	devmode.dmFields = DM_BITSPERPEL | DM_PELSWIDTH | DM_PELSHEIGHT | DM_DISPLAYFLAGS;
	if (freq != 0)
		devmode.dmFields |= DM_DISPLAYFREQUENCY;


	LONG cdsret = ChangeDisplaySettings(&devmode, CDS_FULLSCREEN);

	if (cdsret != DISP_CHANGE_SUCCESSFUL) {
		// Failed: so let's check to see if it's a wierd dual screen display
#ifdef _DEBUG
		printf("Failed to set display mode... assuming dual monitors\n");
#endif
		devmode.dmPelsWidth = width * 2;
		cdsret = ChangeDisplaySettings(&devmode, CDS_FULLSCREEN);

		if (cdsret != DISP_CHANGE_SUCCESSFUL) {
#ifdef _DEBUG
			printf("Failed to set display mode using dual monitors\n");
#endif
			throwException(env, "Failed to set display mode.");
		}
	}

	// The change was successful but might not be the exact change we were expecting.
	// Now we'll construct a new DisplayMode instance and stash it back in the Display
	// class's mode instance variable.

	// Get the screen
	HDC screenDC = CreateCompatibleDC(NULL);
	// Get the device caps
	width = GetDeviceCaps(screenDC, HORZRES);
	height = GetDeviceCaps(screenDC, VERTRES);
	bpp = GetDeviceCaps(screenDC, COLORRES);
	freq = GetDeviceCaps(screenDC, VREFRESH);
	if (freq <= 1)
		freq = 0; // Unknown
	ReleaseDC(NULL, screenDC);

	jclass jclass_DisplayMode = env->FindClass("org/lwjgl/DisplayMode");
	jmethodID ctor = env->GetMethodID(jclass_DisplayMode, "<init>", "(IIII)V");
	jobject newMode = env->NewObject(jclass_DisplayMode, ctor, width, height, bpp, freq);
	jfieldID fid_mode= env->GetStaticFieldID(clazz, "mode", "[org/lwjgl/DisplayMode;");
	env->SetStaticObjectField(clazz, fid_mode, newMode);
	env->DeleteLocalRef(newMode);

}

/*
 * Class:     org_lwjgl_Display
 * Method:    resetDisplayMode
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Display_resetDisplayMode
  (JNIEnv * env, jclass clazz)
{
	// Under Win32, all we have to do is:
	ChangeDisplaySettings(NULL, 0);

	// And we'll call init() again to put the correct mode back in Display
	Java_org_lwjgl_Display_init(env, clazz);
}

/*
 * Class:     org_lwjgl_Display
 * Method:    getGammaRamp
 * Signature: ()[I
 */
JNIEXPORT jintArray JNICALL Java_org_lwjgl_Display_getGammaRamp
  (JNIEnv * env, jclass clazz)
{
	return NULL;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    setGammaRamp
 * Signature: ([I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Display_setGammaRamp
  (JNIEnv * env, jclass clazz, jintArray gamma)
{
}


/*
 * Class:     org_lwjgl_Display
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Display_init
  (JNIEnv * env, jclass clazz)
{
	// Determine the current screen resolution

	// Get the screen
	HDC screenDC = CreateCompatibleDC(NULL);
	// Get the device caps
	int width = GetDeviceCaps(screenDC, HORZRES);
	int height = GetDeviceCaps(screenDC, VERTRES);
	int bpp = GetDeviceCaps(screenDC, COLORRES);
	int freq = GetDeviceCaps(screenDC, VREFRESH);
	if (freq <= 1)
		freq = 0; // Unknown
	ReleaseDC(NULL, screenDC);

	jclass jclass_DisplayMode = env->FindClass("org/lwjgl/DisplayMode");
	jmethodID ctor = env->GetMethodID(jclass_DisplayMode, "<init>", "(IIII)V");
	jobject newMode = env->NewObject(jclass_DisplayMode, ctor, width, height, bpp, freq);
	
	jfieldID fid_initialMode = env->GetFieldID(clazz, "initialMode", "[org/lwjgl/DisplayMode;");
	env->SetStaticObjectField(clazz, fid_initialMode, newMode);
	env->DeleteLocalRef(newMode);
}





