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

#include <windows.h>
#include "org_lwjgl_Display.h"

#undef  DIRECTINPUT_VERSION
#define DIRECTINPUT_VERSION 0x0300

#include <dinput.h>

#define WINDOWCLASSNAME "LWJGLWINDOW"

// Initialise static variables
bool			oneShotInitialised = false;
HWND			hwnd = NULL;						// Handle to the window
HDC				hdc = NULL;							// Device context
LPDIRECTINPUT	lpdi = NULL;

void dumpLastError(void) {
	LPVOID lpMsgBuf;
	FormatMessage( 
		FORMAT_MESSAGE_ALLOCATE_BUFFER | 
		FORMAT_MESSAGE_FROM_SYSTEM | 
		FORMAT_MESSAGE_IGNORE_INSERTS,
		NULL,
		GetLastError(),
		0, // Default language
		(LPTSTR) &lpMsgBuf,
		0,
		NULL
	);
	printf("System error: %s\n", lpMsgBuf);
	LocalFree(lpMsgBuf);
}

/*
 *	A dummy WindowProc which does nothing. Used so we can have an invisible OpenGL window
 */
LRESULT CALLBACK WindowProc(HWND hWnd,
							UINT msg,
							WPARAM wParam,
							LPARAM lParam)
{
	switch (msg) {
		// disable screen saver and monitor power down messages which wreak havoc
		case WM_SYSCOMMAND:
		{
			switch (wParam) {
			case SC_SCREENSAVE:
			case SC_MONITORPOWER:
				return 0;
				break;
			default:
				break;
			}
		}
		case WM_PAINT:
			return 0;
	}

	// default action
    return DefWindowProc(hWnd, msg, wParam, lParam);
}

/*
 * Sets the fullscreen display mode.
 * Returns 1 for success and -1 for failure.
 */
int SetDisplayMode(int width, int height, int bpp, int freq)
{
	// Step 2: set display mode using OpenGL friendly tactics

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
	switch (cdsret) {
	case DISP_CHANGE_BADFLAGS :
		printf("Failed to set screen mode: bad flags\n");
		return -1;
	case DISP_CHANGE_FAILED:
		printf("Failed to set screen mode: change failed\n");
		return -1;
	case DISP_CHANGE_BADMODE:
		printf("Failed to set screen mode: bad mode\n");
		return -1;
	case DISP_CHANGE_SUCCESSFUL :
		// Success!
		break;
	default:
		printf("Failed to set screen mode: unknown error\n");
		return -1;
	}
	return 1;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    getAvailableDisplayModes
 * Signature: ()[Lorg/lwjgl/DisplayMode;
 */
JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_Display_getAvailableDisplayModes
  (JNIEnv * env, jclass clazz)
{

	DEVMODE mode;

	// First count the number of display modes.
	int i = 0, n = 0;
	while (EnumDisplaySettings(NULL, i ++, &mode) != 0) {
		// Filter out indexed modes
		if (mode.dmBitsPerPel < 16) {
			continue;
		} else
			n ++;
	}
		; // Do nothing

	// Allocate an array of DisplayModes big enough
	jclass displayModeClass = env->FindClass("org/lwjgl/DisplayMode");
	jobjectArray ret = env->NewObjectArray(n, displayModeClass, NULL);
	jmethodID displayModeConstructor = env->GetMethodID(displayModeClass, "<init>", "(IIII)V");

	i = n = 0;
	while (EnumDisplaySettings(NULL, i ++, &mode) != 0) {
		// Filter out indexed modes
		if (mode.dmBitsPerPel < 16) {
			continue;
		} else {
			jobject displayMode = env->NewObject(displayModeClass, displayModeConstructor, mode.dmPelsWidth, mode.dmPelsHeight,
				mode.dmBitsPerPel, mode.dmDisplayFrequency);

			env->SetObjectArrayElement(ret, n ++, displayMode);
		}
	}

	return ret;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    nCreate
 * Signature: (IIIIZ)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_Display_nCreate
  (JNIEnv * env, jclass clazz, jint width, jint height, jint bpp, jint freq, jboolean fullscreen)
{
#ifdef _DEBUG
	printf("Creating display: size %dx%d %dhz %dbpp...\n", width, height, freq, bpp);
#endif
	if (fullscreen && SetDisplayMode(width, height, bpp, freq) != 1)
		return JNI_FALSE;

	/*
		Register a window. This window does nothing, it's just a requirement that we get
		a handle to it so we can do other things
	*/
	if (!oneShotInitialised) {
		WNDCLASS windowClass;

		windowClass.style = CS_GLOBALCLASS | CS_OWNDC;
		windowClass.lpfnWndProc = WindowProc;
		windowClass.cbClsExtra = 0;
		windowClass.cbWndExtra = 0;
		windowClass.hInstance = (HINSTANCE) GetCurrentProcess();
		windowClass.hIcon = LoadIcon(NULL, IDI_APPLICATION);
		windowClass.hCursor = LoadIcon(NULL, IDC_ARROW);
		windowClass.hbrBackground = (HBRUSH)GetStockObject(BLACK_BRUSH);
		windowClass.lpszMenuName = NULL;
		windowClass.lpszClassName = WINDOWCLASSNAME;

		if (RegisterClass(&windowClass) == 0) {
			dumpLastError();
			printf("Failed to register window class\n");
			return JNI_FALSE;
		}
		oneShotInitialised = true;
	}

	// Create the window now, using that class:
	hwnd = CreateWindow(
		WINDOWCLASSNAME,
		"LWJGL",
		WS_POPUP, // | WS_MAXIMIZE,
		0, 0,
		width, height,
		NULL,
		NULL,
		GetModuleHandle(NULL),
		NULL);
	// And we never look at windowClass again...

	ShowWindow(hwnd, SW_SHOW);
	UpdateWindow(hwnd);

	hdc = GetWindowDC(hwnd);  

	// Success! Now you need to initialize a GL object, which creates a GL rendering context;
	// and then to issue commands to it, you need to call gl::makeCurrent().
#ifdef _DEBUG
	printf("Created display\n");
#endif

	// Hide the mouse
	ShowCursor(FALSE);

	// Create input
	HRESULT ret = DirectInputCreate(GetModuleHandle(NULL), DIRECTINPUT_VERSION, &lpdi, NULL);
	if (ret != DI_OK && ret != DIERR_BETADIRECTINPUTVERSION ) {
		printf("Failed to create directinput");
		switch (ret) {
			case DIERR_BETADIRECTINPUTVERSION :
				printf(" - Beta versio0n\n");
				break;
			case DIERR_INVALIDPARAM :
				printf(" - Invalid parameter\n");
				break;
			case DIERR_OLDDIRECTINPUTVERSION :
				printf(" - Old Version\n");
				break;
			case DIERR_OUTOFMEMORY :
				printf(" - Out Of Memory\n");
				break;
			default:
				printf("\n");
		}
		return JNI_FALSE;
	}

	jfieldID fid_handle = env->GetStaticFieldID(clazz, "handle", "I");
	env->SetStaticIntField(clazz, fid_handle, (jint) hwnd);

	return JNI_TRUE;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Display_nDestroy
  (JNIEnv * env, jclass clazz)
{
	// Reset the display if necessary
	ChangeDisplaySettings(NULL, 0);

	if (hwnd != NULL) {
		// Vape the window
		DestroyWindow(hwnd);
		hwnd = NULL;
	}

#ifdef _DEBUG
	printf("Destroyed display\n");
#endif

	// Show the mouse
	ShowCursor(TRUE);
}
