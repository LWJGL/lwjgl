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
 * $Id: org_lwjglWindow.cpp,v 1.0 2003/02/12 09:29:07 cix_foo Exp $
 *
 * Base Win32 window
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision: 1.0 $
 */

#define _PRIVATE_WINDOW_H_
#include "Window.h"
#include "org_lwjgl_Window.h"

bool				oneShotInitialised = false;			// Registers the LWJGL window class
HWND				hwnd = NULL;						// Handle to the window
HDC					hdc = NULL;							// Device context
LPDIRECTINPUT		lpdi = NULL;						// DirectInput
bool				isFullScreen = false;				// Whether we're fullscreen or not
bool				isMinimized = false;				// Whether we're minimized or not
JNIEnv *			environment = NULL;					// Cached environment
jobject				window;								// Cached Java Window instance handle
extern HINSTANCE	dll_handle;							// Handle to the LWJGL dll

#define WINDOWCLASSNAME "LWJGL"

/*
 * Utility function to throw an Exception
 */
void throwException(JNIEnv * env, const char * err)
{
	jclass cls = env->FindClass("java/lang/Exception");
	env->ThrowNew(cls, err);
	env->DeleteLocalRef(cls);
}

/*
 * Utility function to throw a RuntimeException
 */
void throwRuntimeException(JNIEnv * env, const char * err)
{
	jclass cls = env->FindClass("java/lang/RuntimeException");
	env->ThrowNew(cls, err);
	env->DeleteLocalRef(cls);
}

/*
 * Create DirectInput.
 * Returns true for success, or false for failure
 */
bool createDirectInput()
{
	// Create input
	HRESULT ret = DirectInputCreate(dll_handle, DIRECTINPUT_VERSION, &lpdi, NULL);
	if (ret != DI_OK && ret != DIERR_BETADIRECTINPUTVERSION ) {
		printf("Failed to create directinput");
		switch (ret) {
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
				printf(" - Unknown failure\n");
		}
		return false;
	} else {
		return true;
	}
}

/*
 * Close the window
 */
void closeWindow()
{
	// Release DirectInput
	if (lpdi != NULL) {
#ifdef _DEBUG
		printf("Destroying directinput\n");
#endif
		lpdi->Release();
		lpdi = NULL;
	}

	// Release device context
	if (hdc != NULL && hwnd != NULL) {
#ifdef _DEBUG
		printf("Releasing DC\n");
#endif
		ReleaseDC(hwnd, hdc);
	}

	// Close the window
	if (hwnd != NULL) {
#ifdef _DEBUG
		printf("Destroy window\n");
#endif
		// Vape the window
		DestroyWindow(hwnd);
#ifdef _DEBUG
		printf("Destroyed window\n");
#endif
		hwnd = NULL;
	}

	// Show the mouse
	if (isFullScreen) {
		ShowCursor(TRUE);
	}
}

/*
 * Called when the application is alt-tabbed to or from
 */
void appActivate(bool active)
{
	if (active) {
		SetForegroundWindow(hwnd);
		ShowWindow(hwnd, SW_RESTORE);
	} else if (isFullScreen)
		ShowWindow(hwnd, SW_MINIMIZE);
}

/*
 *	WindowProc for the GL window.
 */
LRESULT CALLBACK lwjglWindowProc(HWND hWnd,
							     UINT msg,
							     WPARAM wParam,
							     LPARAM lParam)
{
	if (environment == NULL) {
		return DefWindowProc(hWnd, msg, wParam, lParam);
	}

	switch (msg) {
		// disable screen saver and monitor power down messages which wreak havoc
		case WM_SYSCOMMAND:
		{
			switch (wParam) {
			case SC_SCREENSAVE:
			case SC_MONITORPOWER:
				return 0L;
			case SC_MINIMIZE:
				environment->SetBooleanField(window, environment->GetFieldID(environment->GetObjectClass(window), "minimized", "Z"), true);
				appActivate(true);
				break;
			case SC_RESTORE:
				environment->SetBooleanField(window, environment->GetFieldID(environment->GetObjectClass(window), "minimized", "Z"), false);
				appActivate(false);
				break;
			case SC_CLOSE:
				environment->SetBooleanField(window, environment->GetFieldID(environment->GetObjectClass(window), "closeRequested", "Z"), true);
				//don't continue processing this command since this 
				//would shutdown the window, which the application might not want to
				return 0L;
			}
		}
		break;
		case WM_ACTIVATE:
		{
			switch(LOWORD(wParam)) {
			case WA_ACTIVE:
			case WA_CLICKACTIVE:
				environment->SetBooleanField(window, environment->GetFieldID(environment->GetObjectClass(window), "minimized", "Z"), false);
				isMinimized = false;
				break;
			case WA_INACTIVE:
				environment->SetBooleanField(window, environment->GetFieldID(environment->GetObjectClass(window), "minimized", "Z"), true);
				isMinimized = true;
				break;
			}
			appActivate(!isMinimized);
		}
		break;
		case WM_QUIT:
		{
			environment->SetBooleanField(window, environment->GetFieldID(environment->GetObjectClass(window), "closeRequested", "Z"), true);
			return 0L;
		}
		case WM_PAINT:
		{
			environment->SetBooleanField(window, environment->GetFieldID(environment->GetObjectClass(window), "dirty", "Z"), true);
		}
	}

	// default action
    return DefWindowProc(hWnd, msg, wParam, lParam);
}

/*
 * Register the LWJGL window class.
 * Returns true for success, or false for failure
 */
bool registerWindow()
{
	if (!oneShotInitialised) {
		WNDCLASS windowClass;

		windowClass.style = CS_GLOBALCLASS | CS_OWNDC;
		windowClass.lpfnWndProc = lwjglWindowProc;
		windowClass.cbClsExtra = 0;
		windowClass.cbWndExtra = 0;
		windowClass.hInstance = dll_handle;
		windowClass.hIcon = LoadIcon(NULL, IDI_APPLICATION);
		windowClass.hCursor = LoadCursor(NULL, IDC_ARROW);
		windowClass.hbrBackground = (HBRUSH)GetStockObject(BLACK_BRUSH);
		windowClass.lpszMenuName = NULL;
		windowClass.lpszClassName = WINDOWCLASSNAME;

		if (RegisterClass(&windowClass) == 0) {
			printf("Failed to register window class\n");
			return false;
		}
#ifdef _DEBUG
		printf("Window registered\n");
#endif
		oneShotInitialised = true;
	}

	return true;
}

/*
 * Create a window with the specified title, position, size, and
 * fullscreen attribute. The window will have DirectInput associated
 * with it.
 * 
 * Returns true for success, or false for failure
 */
bool createWindow(const char * title, int x, int y, int width, int height, bool fullscreen)
{
	// 1. Register window class if necessary
	if (!registerWindow())
		return false;

	// 2. Create the window
	int exstyle, windowflags;

	if (fullscreen) {
		exstyle = WS_EX_TOPMOST;
		windowflags = WS_POPUP | WS_VISIBLE;
	} else {
		exstyle = 0;
		windowflags = WS_OVERLAPPED | WS_BORDER | WS_CAPTION | WS_VISIBLE | WS_MINIMIZEBOX | WS_SYSMENU;
	}

	// If we're not a fullscreen window, adjust the height to account for the
	// height of the title bar:
	RECT clientSize;
	clientSize.bottom = height;
	clientSize.left = 0;
	clientSize.right = width;
	clientSize.top = 0;
	
	AdjustWindowRectEx(
	  &clientSize,    // client-rectangle structure
	  windowflags,    // window styles
	  FALSE,       // menu-present option
	  exstyle   // extended window style
	);

	clientSize.bottom -= clientSize.top;
	clientSize.right -= clientSize.left;

	// Create the window now, using that class:
	hwnd = CreateWindowEx (
		 exstyle, 
		 WINDOWCLASSNAME,
		 title,
		 windowflags,
		 x, y, clientSize.right, clientSize.bottom,
		 NULL,
		 NULL,
		 dll_handle,
		 NULL);

	if (hwnd == NULL) {
		printf("Failed to create window\n");
		return false;
	}

#ifdef _DEBUG
	printf("Created window\n");
#endif

	ShowWindow(hwnd, SW_SHOWNORMAL);
	UpdateWindow(hwnd);
	SetForegroundWindow(hwnd);
	SetFocus(hwnd);

	hdc = GetWindowDC(hwnd);  

	// Success! Now you need to initialize a GL object, which creates a GL rendering context;
	// and then to issue commands to it, you need to call gl::makeCurrent().

	// 3. Hide the mouse if necessary
	isFullScreen = fullscreen == JNI_TRUE;
	if (isFullScreen) {
		ShowCursor(FALSE);
	}

	// 4. Create DirectInput
	if (!createDirectInput()) {
		// Close the window
		closeWindow();
		return false;

	}

	return true;
}

/*
 * Class:     org_lwjgl_Window
 * Method:    nSetTitle
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Window_nSetTitle
  (JNIEnv * env, jobject obj)
{
	const char * title = env->GetStringUTFChars((jstring) obj, NULL);
	SetWindowText(hwnd, title);
	env->ReleaseStringUTFChars((jstring) obj, title);
}

/*
 * Class:     org_lwjgl_Window
 * Method:    swapBuffers
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Window_swapBuffers
  (JNIEnv * env, jobject obj)
{
	SwapBuffers(hdc);
}

/*
 * Class:     org_lwjgl_Window
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Window_nDestroy
  (JNIEnv * env, jobject obj)
{
	// Cache env and obj
	environment = env;
	window = obj;

	closeWindow();

	environment = NULL;
	window = NULL;
}

/*
 * Handle native Win32 messages
 */
void handleMessages(JNIEnv * env, jobject obj)
{
	// Cache env and obj
	environment = env;
	window = obj;

	/*
	 * Now's our chance to deal with Windows messages that are
	 * otherwise just piling up and causing everything not to
	 * work properly
	 */
	MSG msg;
	while (PeekMessage(
		&msg,         // message information
		hwnd,           // handle to window
		0,  // first message
		0,  // last message
		PM_REMOVE      // removal options
		))
	{
		TranslateMessage(&msg);
      	DispatchMessage(&msg);
	};
}

/*
 * Class:     org_lwjgl_Window
 * Method:    tick
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Window_tick
  (JNIEnv * env, jobject obj)
{
	handleMessages(env, obj);
}