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
 * Base Win32 window
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#define _PRIVATE_WINDOW_H_
#include "Window.h"
#include "common_tools.h"
#include "org_lwjgl_opengl_Window.h"

static bool				oneShotInitialised = false;			// Registers the LWJGL window class

HWND				hwnd = NULL;						              // Handle to the window
HDC					hdc = NULL;							              // Device context
HGLRC				hglrc = NULL;						              // OpenGL context
static bool				isFullScreen = false;		        // Whether we're fullscreen or not
static bool				isMinimized = false;		        // Whether we're minimized or not
static bool       isFocused = false;              // whether we're focused or not
static bool       isDirty = false;                // Whether we're dirty or not
static bool       isUndecorated = false;                // Whether we're undecorated or not
extern HINSTANCE	dll_handle;							        // Handle to the LWJGL dll
RECT clientSize;

static bool closerequested;
static jboolean allowSoftwareOpenGL;              // Whether to allow software opengl

//CAS: commented these out as no longer used
//extern	void			tempRestoreDisplayMode();
//extern	void			tempResetDisplayMode();

#define WINDOWCLASSNAME "LWJGL"

static bool applyPixelFormat(JNIEnv *env, HDC hdc, int iPixelFormat) {
	PIXELFORMATDESCRIPTOR desc;
	if (DescribePixelFormat(hdc, iPixelFormat, sizeof(PIXELFORMATDESCRIPTOR), &desc) == 0) {
		throwException(env, "Could not describe pixel format");
		return false;
	}

	// make that the pixel format of the device context 
	if (SetPixelFormat(hdc, iPixelFormat, &desc) == FALSE) {
		throwException(env, "Failed to set pixel format");
		return false;
	}
	return true;
}

static int findPixelFormatARB(JNIEnv *env, int bpp, int alpha, int depth, int stencil, int samples) {
	int iPixelFormat;
	unsigned int num_formats_returned;
	int attrib_list[] = {WGL_DRAW_TO_WINDOW_ARB, TRUE,
					     WGL_ACCELERATION_ARB, WGL_FULL_ACCELERATION_ARB,
						 WGL_PIXEL_TYPE_ARB, WGL_TYPE_RGBA_ARB,
						 WGL_DOUBLE_BUFFER_ARB, TRUE,
					     WGL_SUPPORT_OPENGL_ARB, TRUE,
					     WGL_COLOR_BITS_ARB, bpp,
					     WGL_ALPHA_BITS_ARB, alpha,
					     WGL_DEPTH_BITS_ARB, depth,
					     WGL_STENCIL_BITS_ARB, stencil,
						 0, 0, /* For ARB_multisample */
						 0, 0, /*                     */
						 0};

	if (samples > 0 && extgl_Extensions.WGL_ARB_multisample) {
		attrib_list[18] = WGL_SAMPLE_BUFFERS_ARB;
		attrib_list[19] = 1;
		attrib_list[20] = WGL_SAMPLES_ARB;
		attrib_list[21] = samples;
	}
	BOOL result = wglChoosePixelFormatARB(hdc, attrib_list, NULL, 1, &iPixelFormat, &num_formats_returned);

	if (result == FALSE || num_formats_returned < 1) {
		throwException(env, "Could not choose ARB pixel formats.");
		return -1;
	}
	return iPixelFormat;
}

/*
 * Find an appropriate pixel format
 */
static int findPixelFormat(JNIEnv *env, int bpp, int alpha, int depth, int stencil)
{
	unsigned int flags = PFD_DRAW_TO_WINDOW |   // support window 
		PFD_SUPPORT_OPENGL |   // support OpenGL 
		PFD_DOUBLEBUFFER;      // double buffered 

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
	  // secondary check for software override
	  if(!allowSoftwareOpenGL) {
		  throwException(env, "Mode not supported by hardware");
		  return -1;
		}
	}

	if ((desc.dwFlags & flags) != flags) {
		throwException(env, "Capabilities not supported");
		return -1;
	}

	return iPixelFormat;
}

/*
 * Close the window
 */
static void closeWindow()
{
	// Release device context
	if (hdc != NULL && hwnd != NULL) {
		printfDebug("Releasing DC\n");
		ReleaseDC(hwnd, hdc);
	}

	// Close the window
	if (hwnd != NULL) {
		ShowWindow(hwnd, SW_HIDE);
		printfDebug("Destroy window\n");
		DestroyWindow(hwnd);
		hwnd = NULL;
	}
}

/*
 * Called when the application is alt-tabbed to or from
 */
static void appActivate(bool active)
{
	if (active) {
		SetForegroundWindow(hwnd);
		ShowWindow(hwnd, SW_RESTORE);
	} else if (isFullScreen) {
		ShowWindow(hwnd, SW_MINIMIZE);
	}
}

/*
 *	WindowProc for the GL window.
 */
LRESULT CALLBACK lwjglWindowProc(HWND hWnd,
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
				return 0L;
			case SC_MINIMIZE:
				isMinimized = true;
				appActivate(false);
				break;
			case SC_RESTORE:
				isMinimized = false;
				appActivate(true);
				break;
			case SC_CLOSE:
				closerequested = true;
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
				isMinimized = false;
				isFocused = true;
				break;
			case WA_INACTIVE:
				isFocused = false;
				break;
			}
			appActivate(!isMinimized);
		}
		break;
		case WM_QUIT:
		{
			closerequested = true;
			return 0L;
		}
		case WM_PAINT:
		{
			isDirty = true;
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
			printfDebug("Failed to register window class\n");
			return false;
		}
		printfDebug("Window registered\n");
		oneShotInitialised = true;
	}

	return true;
}

/*
 * Handle native Win32 messages
 */
static void handleMessages(JNIEnv * env, jclass clazz)
{
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
 * Create a window with the specified title, position, size, and
 * fullscreen attribute. The window will have DirectInput associated
 * with it.
 * 
 * Returns true for success, or false for failure
 */
static bool createWindow(JNIEnv *env, jstring title_obj, int x, int y, int width, int height, bool fullscreen, bool undecorated)
{
	int exstyle, windowflags;

	if (fullscreen) {
		exstyle = WS_EX_APPWINDOW | WS_EX_TOPMOST;
		windowflags = WS_POPUP;
	} else if (undecorated) {
		exstyle = WS_EX_APPWINDOW;
		windowflags = WS_POPUP;
	} else {
		exstyle = WS_EX_APPWINDOW;
		windowflags = WS_OVERLAPPED | WS_BORDER | WS_CAPTION | WS_CLIPSIBLINGS | WS_CLIPCHILDREN | WS_MINIMIZEBOX | WS_SYSMENU;
	}

	// If we're not a fullscreen window, adjust the height to account for the
	// height of the title bar (unless undecorated)
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
	const char * title = env->GetStringUTFChars(title_obj, NULL);
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

	env->ReleaseStringUTFChars(title_obj, title);
	if (hwnd == NULL) {
		throwException(env, "Failed to create the window.");
		return false;
	}

	hdc = GetDC(hwnd);  

	return true;
}

static bool createContext(JNIEnv *env, int iPixelFormat) {
	if (iPixelFormat == -1) {
		closeWindow();
		return false;
	}
	if (!applyPixelFormat(env, hdc, iPixelFormat)) {
		closeWindow();
		return false;
	}
	hglrc = wglCreateContext(hdc);
	if (hglrc == NULL) {
		throwException(env, "Failed to create OpenGL rendering context");
		closeWindow();
		return false;
	}
	wglMakeCurrent(hdc, hglrc);
	return true;
}

/*
 * Class:     org_lwjgl_Window
 * Method:    nSetTitle
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nSetTitle
  (JNIEnv * env, jclass clazz, jstring title_obj)
{
	const char * title = env->GetStringUTFChars(title_obj, NULL);
	SetWindowText(hwnd, title);
	env->ReleaseStringUTFChars(title_obj, title);
}

/*
 * Class:     org_lwjgl_Window
 * Method:    update
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nUpdate
  (JNIEnv * env, jclass clazz)
{
	handleMessages(env, clazz);
}


/*
 * Class:     org_lwjgl_Window
 * Method:    swapBuffers
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_swapBuffers
  (JNIEnv * env, jclass clazz)
{
	isDirty = false;
	SwapBuffers(hdc);
//	wglSwapLayerBuffers(hdc, WGL_SWAP_MAIN_PLANE);
}

/*
 * Class:     org_lwjgl_Window
 * Method:    nCreate
 * Signature: (Ljava/lang/String;IIIIZIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nCreate
  (JNIEnv * env, jclass clazz, jstring title, jint x, jint y, jint width, jint height, jboolean fullscreen, jint bpp, jint alpha, jint depth, jint stencil, jint samples)
{
	closerequested = false;
	isMinimized = false;
	isFocused = true;
	isDirty = true;
	isFullScreen = fullscreen == JNI_TRUE;
	isUndecorated = getBooleanProperty(env, "org.lwjgl.opengl.Window.undecorated");

  // Speacial option for allowing software opengl	
	allowSoftwareOpenGL = getBooleanProperty(env, "org.lwjgl.opengl.Window.allowSoftwareOpenGL");
	
	// 1. Register window class if necessary
	if (!registerWindow()) {
		throwException(env, "Could not register window class");
		return;
	}

	if (!extgl_Open()) {
		throwException(env, "Failed to open extgl");
		return;
	}

	if (!createWindow(env, title, x, y, width, height, isFullScreen, isUndecorated)) {
		extgl_Close();
		return;
	}
	int iPixelFormat = findPixelFormat(env, bpp, alpha, depth, stencil);
	if (!createContext(env, iPixelFormat)) {
		extgl_Close();
		return;
	}

	// Some crazy strangeness here so we can use ARB_pixel_format to specify the number
	// of multisamples we want. If the extension is present we'll delete the existing
	// rendering context and start over, using the ARB extension instead to pick the context.
	extgl_InitWGL(env);
	if (extgl_Extensions.WGL_ARB_pixel_format && samples > 0) {
		wglMakeCurrent(NULL, NULL);
		wglDeleteContext(hglrc);
		closeWindow();
		if (!createWindow(env, title, x, y, width, height, isFullScreen, isUndecorated)) {
			extgl_Close();
			return;
		}
		iPixelFormat = findPixelFormatARB(env, bpp, alpha, depth, stencil, samples);
		if (!createContext(env, iPixelFormat)) {
			extgl_Close();
			return;
		}
	}

	ShowWindow(hwnd, SW_SHOW);
	UpdateWindow(hwnd);
	SetForegroundWindow(hwnd);
	SetFocus(hwnd);
}

/*
 * Class:     org_lwjgl_Window
 * Method:    doDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nDestroy
  (JNIEnv * env, jclass clazz)
{
	wglMakeCurrent(NULL, NULL);

	// Delete the rendering context
	if (hglrc != NULL) {
		printfDebug("Deleting GL context\n");
		wglDeleteContext(hglrc); 
		hglrc = NULL;
	}
	closeWindow();
	extgl_Close();
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nIsDirty
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsDirty
  (JNIEnv *env, jclass clazz) {
	bool result = isDirty;
	isDirty = false;
	return result ? JNI_TRUE : JNI_FALSE;
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nIsVisible
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsVisible
  (JNIEnv *env, jclass clazz) {
	return isMinimized ? JNI_FALSE : JNI_TRUE;
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nIsCloseRequested
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsCloseRequested
  (JNIEnv *, jclass) {
	bool saved = closerequested;
	closerequested = false;
	return saved;
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nIsActive
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Window_nIsActive
  (JNIEnv *env, jclass clazz) {
	return isFocused;
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nSetVSyncEnabled
 * Signature: (Z)Z
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nSetVSyncEnabled
  (JNIEnv * env, jclass clazz, jboolean sync)
{
	if (extgl_Extensions.WGL_EXT_swap_control) {
		if (sync == JNI_TRUE) {
			wglSwapIntervalEXT(1);
		} else {
			wglSwapIntervalEXT(0);
		}
	}
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nMakeCurrent
  (JNIEnv *env, jclass clazz)
{
	wglMakeCurrent(hdc, hglrc);
}

/*
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Window_nReshape
  (JNIEnv *env, jclass clazz, jint x, jint y, jint width, jint height)
{
	if (isFullScreen) {
		return;
	}

	int exstyle, windowflags;

	if (isUndecorated) {
		exstyle = WS_EX_APPWINDOW;
		windowflags = WS_OVERLAPPED;
	} else {
		exstyle = WS_EX_APPWINDOW;
		windowflags = WS_OVERLAPPED | WS_BORDER | WS_CAPTION | WS_CLIPSIBLINGS | WS_CLIPCHILDREN | WS_MINIMIZEBOX | WS_SYSMENU;
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

	SetWindowPos(hwnd, HWND_TOP, x, y, clientSize.right - clientSize.left, 
		clientSize.bottom - clientSize.top, SWP_NOZORDER);
}
*/
