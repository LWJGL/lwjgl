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
HGLRC				hglrc = NULL;						// OpenGL context
LPDIRECTINPUT		lpdi = NULL;						// DirectInput
bool				isFullScreen = false;				// Whether we're fullscreen or not
bool				isMinimized = false;				// Whether we're minimized or not
JNIEnv *			environment = NULL;					// Cached environment
jclass				window;								// Cached Java Window class
extern HINSTANCE	dll_handle;							// Handle to the LWJGL dll
RECT clientSize;

//CAS: commented these out as no longer used
//extern	void			tempRestoreDisplayMode();
//extern	void			tempResetDisplayMode();

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
 * Find an appropriate pixel format
 */
static int findPixelFormat(JNIEnv *env, unsigned int flags, int bpp, int alpha, int depth, int stencil)
{
	PIXELFORMATDESCRIPTOR pfd = { 
		sizeof(PIXELFORMATDESCRIPTOR),   // size of this pfd 
		1,                     // version number 
		flags,         // RGBA type 
		PFD_TYPE_RGBA,
		(BYTE)bpp,       
		0, 0, 0, 0, 0, 0,      // color bits ignored 
		(BYTE)alpha,       
		0,                     // shift bit ignored 
		0,                     // no accumulation buffer 
		0, 0, 0, 0,            // accum bits ignored 
		(BYTE)depth,       
		(BYTE)stencil,     
		0,                     // No auxiliary buffer 
		PFD_MAIN_PLANE,        // main layer
		0,                     // reserved 
		0, 0, 0                // layer masks ignored
	};

	// get the best available match of pixel format for the device context  
	int iPixelFormat = ChoosePixelFormat(hdc, &pfd);
	if (iPixelFormat == 0) {
		throwException(env, "Failed to choose pixel format");
		return -1;
	}

#ifdef _DEBUG
	printf("Pixel format is %d\n", iPixelFormat);
#endif

	// make that the pixel format of the device context 
	if (SetPixelFormat(hdc, iPixelFormat, &pfd) == FALSE) {
		printf("Failed to set pixel format\n");
		throwException(env, "Failed to choose pixel format");
		return -1;
	}

	// 3. Check the chosen format matches or exceeds our specifications
	PIXELFORMATDESCRIPTOR desc;
	if (DescribePixelFormat(hdc, iPixelFormat, sizeof(PIXELFORMATDESCRIPTOR), &desc) == 0) {
		throwException(env, "Could not describe pixel format");
		return -1;
	}

	if (desc.cColorBits < bpp) {
		throwException(env, "This application requires a greater colour depth");
		return -1;
	}

	if (desc.cAlphaBits < alpha) {
		throwException(env, "This application requires a greater alpha depth");
		return -1;
	}

	if (desc.cStencilBits < stencil) {
		throwException(env, "This application requires a greater stencil depth");
		return -1;
	}

	if (desc.cDepthBits < depth) {
		throwException(env, "This application requires a greater depth buffer depth");
		return -1;
	}

	if ((desc.dwFlags & PFD_GENERIC_FORMAT) != 0 || (desc.dwFlags & PFD_GENERIC_ACCELERATED) != 0) {
		throwException(env, "Mode not supported by hardware");
		return -1;
	}

	if ((desc.dwFlags & flags) != flags) {
		throwException(env, "Capabilities not supported");
		return -1;
	}

	// 4. Initialise other things now
	if (extgl_Open() != 0) {
		throwException(env, "Failed to open extgl");
		return -1;
	}
	return iPixelFormat;
}
/*
 * Create DirectInput.
 * Returns true for success, or false for failure
 */
static bool createDirectInput()
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
static void closeWindow()
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
}

/*
 * Called when the application is alt-tabbed to or from
 */
static void appActivate(bool active)
{
//	if (!active) {
//		tempResetDisplayMode();
//	}
	if (active) {
		SetForegroundWindow(hwnd);
		ShowWindow(hwnd, SW_RESTORE);
	} else if (isFullScreen) {
		ShowWindow(hwnd, SW_MINIMIZE);
	}
//	if (active) {
//		tempRestoreDisplayMode();
//	}
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
				environment->SetStaticBooleanField(window, environment->GetFieldID(environment->GetObjectClass(window), "minimized", "Z"), JNI_TRUE);
				appActivate(false);
				break;
			case SC_RESTORE:
				environment->SetStaticBooleanField(window, environment->GetFieldID(environment->GetObjectClass(window), "minimized", "Z"), JNI_FALSE);
				appActivate(true);
				break;
			case SC_CLOSE:
				environment->SetStaticBooleanField(window, environment->GetFieldID(environment->GetObjectClass(window), "closeRequested", "Z"), JNI_TRUE);
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
				environment->SetStaticBooleanField(window, environment->GetFieldID(environment->GetObjectClass(window), "minimized", "Z"), JNI_FALSE);
				isMinimized = false;

				break;
			case WA_INACTIVE:
				environment->SetStaticBooleanField(window, environment->GetFieldID(environment->GetObjectClass(window), "minimized", "Z"), JNI_TRUE);
				isMinimized = true;

				break;
			}
			appActivate(!isMinimized);
		}
		break;
		case WM_QUIT:
		{
			environment->SetStaticBooleanField(window, environment->GetFieldID(environment->GetObjectClass(window), "closeRequested", "Z"), JNI_TRUE);
			return 0L;
		}
		case WM_PAINT:
		{
			environment->SetStaticBooleanField(window, environment->GetFieldID(environment->GetObjectClass(window), "dirty", "Z"), JNI_TRUE);
		}
	}

	// default action
    return DefWindowProc(hWnd, msg, wParam, lParam);
}

/*
 * Register the LWJGL window class.
 * Returns true for success, or false for failure
 */
static bool registerWindow()
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
 * Handle native Win32 messages
 */
static void handleMessages(JNIEnv * env, jclass clazz)
{
	// Cache env and obj
	environment = env;
	window = clazz;

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
	environment = NULL;
	window = NULL;
}

/*
 * Create a window with the specified title, position, size, and
 * fullscreen attribute. The window will have DirectInput associated
 * with it.
 * 
 * Returns true for success, or false for failure
 */
static bool createWindow(const char * title, int x, int y, int width, int height, bool fullscreen)
{
	// 1. Register window class if necessary
	if (!registerWindow())
		return false;

	// 2. Create the window
	int exstyle, windowflags;

	if (fullscreen) {
		exstyle = WS_EX_APPWINDOW | WS_EX_TOPMOST;
		windowflags = WS_POPUP | WS_VISIBLE;
	} else {
		exstyle = WS_EX_APPWINDOW;
		windowflags = WS_OVERLAPPED | WS_BORDER | WS_CAPTION | WS_VISIBLE | WS_MINIMIZEBOX | WS_SYSMENU;
	}

	// If we're not a fullscreen window, adjust the height to account for the
	// height of the title bar:
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

	// Create the window now, using that class:
	hwnd = CreateWindowEx (
		 exstyle, 
		 WINDOWCLASSNAME,
		 title,
		 windowflags,
		 x, y, clientSize.right - clientSize.left, clientSize.bottom - clientSize.top,
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

//	ShowWindow(hwnd, SW_SHOWNORMAL);
	ShowWindow(hwnd, SW_SHOW);
	UpdateWindow(hwnd);
	SetForegroundWindow(hwnd);
	SetFocus(hwnd);

	hdc = GetWindowDC(hwnd);  

	// Success! Now you need to initialize a GL object, which creates a GL rendering context;
	// and then to issue commands to it, you need to call gl::makeCurrent().

	// 3. Hide the mouse if necessary
	isFullScreen = fullscreen == JNI_TRUE;

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
  (JNIEnv * env, jclass clazz, jstring title_obj)
{
	const char * title = env->GetStringUTFChars(title_obj, NULL);
	SetWindowText(hwnd, title);
	env->ReleaseStringUTFChars(title_obj, title);
}

/*
 * Class:     org_lwjgl_Window
 * Method:    tick
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Window_tick
  (JNIEnv * env, jclass clazz)
{
	handleMessages(env, clazz);
}


/*
 * Class:     org_lwjgl_Window
 * Method:    minimize
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Window_minimize
  (JNIEnv * env, jclass clazz)
{
	if (isMinimized)
		return;
	ShowWindow(hwnd, SW_MINIMIZE);
}

/*
 * Class:     org_lwjgl_Window
 * Method:    minimize
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Window_restore
  (JNIEnv * env, jclass clazz)
{
	if (!isMinimized)
		return;

	ShowWindow(hwnd, SW_RESTORE);
}

/*
 * Class:     org_lwjgl_Window
 * Method:    swapBuffers
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Window_swapBuffers
  (JNIEnv * env, jclass clazz)
{
	wglSwapLayerBuffers(hdc, WGL_SWAP_MAIN_PLANE);
}

/*
 * Class:     org_lwjgl_Window
 * Method:    nCreate
 * Signature: (Ljava/lang/String;IIIIZIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Window_nCreate
  (JNIEnv * env, jclass clazz, jstring title, jint x, jint y, jint width, jint height, jboolean fullscreen, jint bpp, jint alpha, jint depth, jint stencil)
{
	// 1. Create a window
	const char * titleString = env->GetStringUTFChars(title, NULL);
	if (!createWindow(titleString, x, y, width, height, fullscreen == JNI_TRUE ? true : false)) {
		env->ReleaseStringUTFChars((jstring) title, titleString);
		closeWindow();
		throwException(env, "Failed to create the window.");
		return;
	}
	env->ReleaseStringUTFChars(title, titleString);

	// 2. Choose a pixel format and set it
	unsigned int flags = PFD_DRAW_TO_WINDOW |   // support window 
		PFD_SUPPORT_OPENGL |   // support OpenGL 
		PFD_DOUBLEBUFFER;      // double buffered 

	int iPixelFormat = findPixelFormat(env, flags, bpp, alpha, depth, stencil);
	if (iPixelFormat == -1) {
		closeWindow();
		return;
	}
	// Create a rendering context
	hglrc = wglCreateContext(hdc);
	if (hglrc == NULL) {
		throwException(env, "Failed to create OpenGL rendering context");
		closeWindow();
		return;
	}

	// Automatically make it the current context
	wglMakeCurrent(hdc, hglrc);

	// Initialise GL extensions
	if (extgl_Initialize() != 0) {
		closeWindow();
		throwException(env, "Failed to initialize GL extensions");
		return;
	}
}

/*
 * Class:     org_lwjgl_Window
 * Method:    doDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Window_nDestroy
  (JNIEnv * env, jclass clazz)
{
	wglMakeCurrent(NULL, NULL);

	// Delete the rendering context
	if (hglrc != NULL) {
#ifdef _DEBUG
		printf("Delete GL context\n");
#endif
		wglDeleteContext(hglrc); 
		hglrc = NULL;
	}
	closeWindow();
	extgl_Close();
}
