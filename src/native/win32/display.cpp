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
 
/**
 * $Id$
 *
 * Win32 specific library for display handling.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#include <windows.h>
#include <jni.h>
#include "display.h"
#include "common_tools.h"

#define WINDOWCLASSNAME "LWJGLWINDOW"

#define GAMMA_SIZE (3*256)

static jobjectArray GetAvailableDisplayModesEx(JNIEnv * env);
static jobjectArray GetAvailableDisplayModes(JNIEnv * env);
static char * getDriver();
static bool modeSet = false; // Whether we've done a display mode change
static WORD originalGamma[GAMMA_SIZE]; // Original gamma settings
static WORD currentGamma[GAMMA_SIZE]; // Current gamma settings
static DEVMODE devmode; // Now we'll remember this value for the future
extern HWND				display_hwnd;						              // Handle to the window
extern RECT clientSize;
static char * driver = getDriver();

jobjectArray getAvailableDisplayModes(JNIEnv *env)
{
	jobjectArray result = GetAvailableDisplayModesEx(env);
	if (result == NULL) {
		printfDebug("Extended display mode selection failed, using fallback\n");
		result = GetAvailableDisplayModes(env);
	}
	return result;
}

/**
 * Choose displaymodes using extended codepath (multiple displaydevices)
 */
static jobjectArray GetAvailableDisplayModesEx(JNIEnv * env) {
	typedef BOOL (WINAPI * EnumDisplayDevicesAPROC)(IN LPCSTR lpDevice, IN DWORD iDevNum, OUT PDISPLAY_DEVICEA lpDisplayDevice, IN DWORD dwFlags);
	typedef BOOL (WINAPI * EnumDisplaySettingsExAPROC)(IN LPCSTR lpszDeviceName, IN DWORD iModeNum, OUT LPDEVMODEA lpDevMode, IN DWORD dwFlags);
	EnumDisplayDevicesAPROC EnumDisplayDevicesA;
	EnumDisplaySettingsExAPROC EnumDisplaySettingsExA;

	HMODULE lib_handle = LoadLibrary("user32.dll");
	if (lib_handle == NULL) {
		printfDebug("Could not load user32.dll\n");
		return NULL;
	}
	EnumDisplayDevicesA = (EnumDisplayDevicesAPROC)GetProcAddress(lib_handle, "EnumDisplayDevicesA");
	if (EnumDisplayDevicesA == NULL)
		return NULL;
	EnumDisplaySettingsExA = (EnumDisplaySettingsExAPROC)GetProcAddress(lib_handle, "EnumDisplaySettingsExA");
	if (EnumDisplaySettingsExA == NULL)
		return NULL;

	int i = 0, j = 0, n = 0;
	int AvailableModes = 0;

	DISPLAY_DEVICE DisplayDevice;
	DEVMODE DevMode;

	ZeroMemory(&DevMode, sizeof(DEVMODE));
	ZeroMemory(&DisplayDevice, sizeof(DISPLAY_DEVICE));

	DevMode.dmSize = sizeof(DEVMODE);
	DisplayDevice.cb = sizeof(DISPLAY_DEVICE);
  
	//enumerate all displays, and all of their displaymodes
	while(EnumDisplayDevicesA(NULL, i++, &DisplayDevice, 0) != 0) {
		// continue if mirroring device
		if((DisplayDevice.StateFlags & DISPLAY_DEVICE_MIRRORING_DRIVER) != 0) {
			continue;
		}
		
		// go ahead
		printfDebug("Querying %s device\n", DisplayDevice.DeviceString);
		j = 0;
		while(EnumDisplaySettingsExA((const char *) DisplayDevice.DeviceName, j++, &DevMode, 0) != 0) {
			if (DevMode.dmBitsPerPel > 8 && ChangeDisplaySettings(&DevMode, CDS_FULLSCREEN | CDS_TEST) == DISP_CHANGE_SUCCESSFUL) {
				AvailableModes++;
			}
		}
	}

	printfDebug("Found %d displaymodes\n", AvailableModes);
  
	// now that we have the count create the classes, and add 'em all - we'll remove dups in Java
	// Allocate an array of DisplayModes big enough
	jclass displayModeClass = env->FindClass("org/lwjgl/opengl/DisplayMode");

	jobjectArray ret = env->NewObjectArray(AvailableModes, displayModeClass, NULL);
	jmethodID displayModeConstructor = env->GetMethodID(displayModeClass, "<init>", "(IIII)V");
  
	i = 0, n = 0;
	while(EnumDisplayDevicesA(NULL, i++, &DisplayDevice, 0) != 0) {
	  // continue if mirroring device
		if((DisplayDevice.StateFlags & DISPLAY_DEVICE_MIRRORING_DRIVER) != 0) {
			continue;
		}
	
		j = 0;
		while(EnumDisplaySettingsExA((const char *) DisplayDevice.DeviceName, j++, &DevMode, 0) != 0) {
			// Filter out indexed modes
			if (DevMode.dmBitsPerPel > 8 && ChangeDisplaySettings(&DevMode, CDS_FULLSCREEN | CDS_TEST) == DISP_CHANGE_SUCCESSFUL) {
				jobject displayMode;
				displayMode = env->NewObject(displayModeClass, displayModeConstructor, 
											 DevMode.dmPelsWidth, DevMode.dmPelsHeight,
											 DevMode.dmBitsPerPel, DevMode.dmDisplayFrequency);

				env->SetObjectArrayElement(ret, n++, displayMode);
			}
		}
	}
	FreeLibrary(lib_handle);
	return ret;
}

/**
 * Choose displaymodes using standard codepath (single displaydevice)
 */
static jobjectArray GetAvailableDisplayModes(JNIEnv * env) {
	int i = 0, j = 0, n = 0;
	int AvailableModes = 0;

	DEVMODE DevMode;

	ZeroMemory(&DevMode, sizeof(DEVMODE));

	DevMode.dmSize = sizeof(DEVMODE);
  
	//enumerate all displaymodes
	while(EnumDisplaySettings(NULL, j++, &DevMode) != 0) {
		if (DevMode.dmBitsPerPel > 8 && ChangeDisplaySettings(&DevMode, CDS_FULLSCREEN | CDS_TEST) == DISP_CHANGE_SUCCESSFUL) {
			AvailableModes++;
		}
	}
		
	printfDebug("Found %d displaymodes\n", AvailableModes);
  
	// now that we have the count create the classes, and add 'em all - we'll remove dups in Java
	// Allocate an array of DisplayModes big enough
	jclass displayModeClass = env->FindClass("org/lwjgl/opengl/DisplayMode");

	jobjectArray ret = env->NewObjectArray(AvailableModes, displayModeClass, NULL);
	jmethodID displayModeConstructor = env->GetMethodID(displayModeClass, "<init>", "(IIII)V");  
  
	i = 0, j = 0, n = 0;
	while(EnumDisplaySettings(NULL, j++, &DevMode) != 0) {
		// Filter out indexed modes
		if (DevMode.dmBitsPerPel > 8 && ChangeDisplaySettings(&DevMode, CDS_FULLSCREEN | CDS_TEST) == DISP_CHANGE_SUCCESSFUL) {
			jobject displayMode;
			displayMode = env->NewObject(displayModeClass, displayModeConstructor,
			                              DevMode.dmPelsWidth, DevMode.dmPelsHeight,
						                  DevMode.dmBitsPerPel, DevMode.dmDisplayFrequency);
			env->SetObjectArrayElement(ret, n++, displayMode);
		}
	}
	return ret;
}

void switchDisplayMode(JNIEnv * env, jobject mode)
{
	jclass cls_displayMode = env->GetObjectClass(mode);
	jfieldID fid_width = env->GetFieldID(cls_displayMode, "width", "I");
	jfieldID fid_height = env->GetFieldID(cls_displayMode, "height", "I");
	jfieldID fid_bpp = env->GetFieldID(cls_displayMode, "bpp", "I");
	jfieldID fid_freq = env->GetFieldID(cls_displayMode, "freq", "I");

	int width = env->GetIntField(mode, fid_width);
	int height = env->GetIntField(mode, fid_height);
	int bpp = env->GetIntField(mode, fid_bpp);
	int freq = env->GetIntField(mode, fid_freq);

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
		printfDebug("Failed to set display mode... assuming dual monitors\n");
		devmode.dmPelsWidth = width * 2;
		cdsret = ChangeDisplaySettings(&devmode, CDS_FULLSCREEN);

		if (cdsret != DISP_CHANGE_SUCCESSFUL) {
			printfDebug("Failed to set display mode using dual monitors\n");
			throwException(env, "Failed to set display mode.");
			return;
		}
	}
	modeSet = true;
}

int getGammaRampLength(void)
{
	return 256;
}

void setGammaRamp(JNIEnv * env, jobject gammaRampBuffer)
{
	const float *gammaRamp = (const float *)env->GetDirectBufferAddress(gammaRampBuffer);
	// Turn array of floats into array of RGB WORDs

	for (int i = 0; i < 256; i ++) {
		float scaledRampEntry = gammaRamp[i]*0xffff;
		WORD rampEntry = (WORD)scaledRampEntry;
		currentGamma[i] = rampEntry;
		currentGamma[i + 256] = rampEntry;
		currentGamma[i + 512] = rampEntry;
	}
	HDC screenDC = GetDC(NULL);
	if (SetDeviceGammaRamp(screenDC, currentGamma) == FALSE) {
		throwException(env, "Failed to set device gamma.");
	}
	ReleaseDC(NULL, screenDC);
}


jobject initDisplay(JNIEnv * env)
{
	// Determine the current screen resolution
	// Get the screen
	HDC screenDC = GetDC(NULL);
	if (!screenDC) {
		throwException(env, "Couldn't get screen DC!");
		return NULL;
	}
	// Get the device caps
	int width = GetDeviceCaps(screenDC, HORZRES);
	int height = GetDeviceCaps(screenDC, VERTRES);
	int bpp = GetDeviceCaps(screenDC, BITSPIXEL);
	int freq = GetDeviceCaps(screenDC, VREFRESH);
	if (freq <= 1)
		freq = 0; // Unknown

	jclass jclass_DisplayMode = env->FindClass("org/lwjgl/opengl/DisplayMode");
	jmethodID ctor = env->GetMethodID(jclass_DisplayMode, "<init>", "(IIII)V");
	jobject newMode = env->NewObject(jclass_DisplayMode, ctor, width, height, bpp, freq);

	// Get the default gamma ramp
	if (GetDeviceGammaRamp(screenDC, originalGamma) == FALSE) {
		printfDebug("Failed to get initial device gamma\n");
	}
	memcpy(currentGamma, originalGamma, sizeof(WORD)*GAMMA_SIZE);
	ReleaseDC(NULL, screenDC);
	return newMode;
}

void resetDisplayMode(JNIEnv * env) {
	// Return device gamma to normal
	HDC screenDC = GetDC(NULL);
	if (!SetDeviceGammaRamp(screenDC, originalGamma)) {
		printfDebug("Could not reset device gamma\n");
	}
	ReleaseDC(NULL, screenDC);	

	if (modeSet) {
		modeSet = false;
		// Under Win32, all we have to do is:
		ChangeDisplaySettings(NULL, 0);

		// And we'll call init() again to put the correct mode back in Display
		if (env != NULL)
			initDisplay(env);
	}
}

/*
 * Put display settings back to what they were when the window is maximized.
 */
void restoreDisplayMode(void) {
	// Restore gamma
	HDC screenDC = GetDC(NULL);
	if (!SetDeviceGammaRamp(screenDC, currentGamma)) {
		printfDebug("Could not restore device gamma\n");
	}
	ReleaseDC(NULL, screenDC);

	if (!modeSet) {
		printfDebug("Attempting to restore the display mode\n");
		modeSet = true;
		LONG cdsret = ChangeDisplaySettings(&devmode, CDS_FULLSCREEN);

		if (cdsret != DISP_CHANGE_SUCCESSFUL) {
			printfDebug("Failed to restore display mode\n");
		}
	}
}

static char * getDriver() {
	#define MY_BUFSIZE 256

	HKEY hKey;
	static TCHAR szAdapterKey[MY_BUFSIZE], szDriverValue[MY_BUFSIZE];
	DWORD dwBufLen = MY_BUFSIZE;
	LONG lRet;

	if(RegOpenKeyEx(HKEY_LOCAL_MACHINE,
		TEXT("HARDWARE\\DeviceMap\\Video"),
					0,
					KEY_QUERY_VALUE,
					&hKey) != ERROR_SUCCESS) return NULL;

	lRet = RegQueryValueEx(hKey,
					TEXT("\\Device\\Video0"),
					NULL,
					NULL,
					(LPBYTE)szAdapterKey,
					&dwBufLen);

	RegCloseKey(hKey);

	if(lRet != ERROR_SUCCESS) return NULL;

	printfDebug("Adapter key: %s\n", szAdapterKey);

	// szAdapterKey now contains something like \Registry\Machine\System\CurrentControlSet\Control\Video\{B70DBD2A-90C4-41CF-A58E-F3BA69F1A6BC}\0000
	// We'll check for the first chunk:
	if (strncmp("\\Registry\\Machine", szAdapterKey, 17) == 0) {
		// Yes, it's right, so let's look for that key now

		TCHAR szDriverKey[MY_BUFSIZE];
		strcpy(szDriverKey, &szAdapterKey[18]);

		if(RegOpenKeyEx(HKEY_LOCAL_MACHINE,
			TEXT(szDriverKey),
						0,
						KEY_QUERY_VALUE,
						&hKey) != ERROR_SUCCESS) return NULL;

		lRet = RegQueryValueEx(hKey,
						TEXT("InstalledDisplayDrivers"),
						NULL,
						NULL,
						(LPBYTE)szDriverValue,
						&dwBufLen);

		RegCloseKey(hKey);

	}

	if(lRet != ERROR_SUCCESS) return NULL;

	return szDriverValue;
}

jstring getAdapter(JNIEnv * env)
{

	jstring ret = NULL;
	if (driver == NULL) {
		return NULL;
	}
	ret = NewStringNative(env, driver);
	return ret;
}



jstring getVersion(JNIEnv * env)
{
	jstring ret = NULL;

	TCHAR driverDLL[256] = "\0";

	if (driver == NULL) {
		return NULL;
	}
	strcat(driverDLL, driver);
	strcat(driverDLL, ".dll");
	DWORD var = 0;
	DWORD dwInfoSize = GetFileVersionInfoSize(driverDLL, &var);
	LPVOID lpInfoBuff = new unsigned char[dwInfoSize];
	BOOL bRetval = GetFileVersionInfo(driverDLL, NULL, dwInfoSize, lpInfoBuff);
	if (bRetval == 0) {
	} else {
		VS_FIXEDFILEINFO * fxdFileInfo;

		UINT uiLen = 0;
		bRetval = VerQueryValue(lpInfoBuff, TEXT("\\"), (void **) &fxdFileInfo, &uiLen);
		if (bRetval != 0) {
			TCHAR version[256];
			TCHAR ms[10], ls[10];
			sprintf(ms, "%d.%d\0", fxdFileInfo->dwProductVersionMS >> 16, fxdFileInfo->dwProductVersionMS & 0xFFFF);
			sprintf(ls, "%d.%d\0", fxdFileInfo->dwProductVersionLS >> 16, fxdFileInfo->dwProductVersionLS & 0xFFFF);
			sprintf(version, "%s.%s\0", ms, ls);
			ret = NewStringNative(env, version);
		}
	}

	delete lpInfoBuff;

	return ret;
}

