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
 * Base Win32 display
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#define _PRIVATE_WINDOW_H_
#include "Window.h"
#include "extgl_wgl.h"
#include "common_tools.h"
#include "extgl_wgl.h"
#include "display.h"
#include "org_lwjgl_opengl_Display.h"

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
static int pixel_format_index;

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

static int findPixelFormatARBFromBPP(JNIEnv *env, jobject pixel_format, jobject pixelFormatCaps, int bpp, bool window, bool double_buffer) {
	jclass cls_pixel_format = env->GetObjectClass(pixel_format);
	int alpha = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "alpha", "I"));
	int depth = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "depth", "I"));
	int stencil = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "stencil", "I"));
	int samples = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "samples", "I"));
	int num_aux_buffers = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "num_aux_buffers", "I"));
	int accum_bpp = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "accum_bpp", "I"));
	int accum_alpha = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "accum_alpha", "I"));
	jboolean stereo = env->GetBooleanField(pixel_format, env->GetFieldID(cls_pixel_format, "stereo", "Z"));
	int iPixelFormat;
	unsigned int num_formats_returned;
	attrib_list_t attrib_list;
	initAttribList(&attrib_list);
	if (window) {
		putAttrib(&attrib_list, WGL_DRAW_TO_WINDOW_ARB); putAttrib(&attrib_list, TRUE);
	} else {
		putAttrib(&attrib_list, WGL_DRAW_TO_PBUFFER_ARB); putAttrib(&attrib_list, TRUE);
	}
	putAttrib(&attrib_list, WGL_ACCELERATION_ARB); putAttrib(&attrib_list, WGL_FULL_ACCELERATION_ARB);
	putAttrib(&attrib_list, WGL_PIXEL_TYPE_ARB); putAttrib(&attrib_list, WGL_TYPE_RGBA_ARB);
	putAttrib(&attrib_list, WGL_DOUBLE_BUFFER_ARB); putAttrib(&attrib_list, window ? TRUE : FALSE);
	putAttrib(&attrib_list, WGL_SUPPORT_OPENGL_ARB); putAttrib(&attrib_list, TRUE);
	putAttrib(&attrib_list, WGL_COLOR_BITS_ARB); putAttrib(&attrib_list, bpp);
	putAttrib(&attrib_list, WGL_ALPHA_BITS_ARB); putAttrib(&attrib_list, alpha);
	putAttrib(&attrib_list, WGL_DEPTH_BITS_ARB); putAttrib(&attrib_list, depth);
	putAttrib(&attrib_list, WGL_STENCIL_BITS_ARB); putAttrib(&attrib_list, stencil);
	if (samples > 0 && extgl_Extensions.WGL_ARB_multisample) {
		putAttrib(&attrib_list, WGL_SAMPLE_BUFFERS_ARB); putAttrib(&attrib_list, 1);
		putAttrib(&attrib_list, WGL_SAMPLES_ARB); putAttrib(&attrib_list, samples);
	}
	putAttrib(&attrib_list, WGL_ACCUM_BITS_ARB); putAttrib(&attrib_list, accum_bpp);
	putAttrib(&attrib_list, WGL_ACCUM_ALPHA_BITS_ARB); putAttrib(&attrib_list, accum_alpha);
	putAttrib(&attrib_list, WGL_STEREO_ARB); putAttrib(&attrib_list, stereo ? TRUE : FALSE);
	putAttrib(&attrib_list, WGL_AUX_BUFFERS_ARB); putAttrib(&attrib_list, num_aux_buffers);
	if ( pixelFormatCaps != NULL ) {
		if ( !extgl_Extensions.WGL_ARB_render_texture ) {
			throwException(env, "The render-to-texture extension is not supported.");
			return -1;
		}

		GLuint *pixelFormatCaps_ptr = (GLuint *)env->GetDirectBufferAddress(pixelFormatCaps);
		jlong pixelFormatCapsSize = env->GetDirectBufferCapacity(pixelFormatCaps);

		for (jlong i = 0; i < pixelFormatCapsSize;)
			putAttrib(&attrib_list, pixelFormatCaps_ptr[i]);
	}
	putAttrib(&attrib_list, 0); putAttrib(&attrib_list, 0);
	BOOL result = wglChoosePixelFormatARB(hdc, attrib_list.attribs, NULL, 1, &iPixelFormat, &num_formats_returned);

	if (result == FALSE || num_formats_returned < 1) {
		throwException(env, "Could not choose ARB pixel formats.");
		return -1;
	}
	return iPixelFormat;
}

int findPixelFormatARB(JNIEnv *env, jobject pixel_format, jobject pixelFormatCaps, bool use_hdc_bpp, bool window, bool double_buffer) {
	int bpp;
	jclass cls_pixel_format = env->GetObjectClass(pixel_format);
	if (use_hdc_bpp) {
		bpp = GetDeviceCaps(hdc, BITSPIXEL);
		int iPixelFormat = findPixelFormatARBFromBPP(env, pixel_format, pixelFormatCaps, bpp, window, double_buffer);
		if (iPixelFormat == -1)
			bpp = 16;
		else
			return iPixelFormat;
	} else
		bpp = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "bpp", "I"));
	return findPixelFormatARBFromBPP(env, pixel_format, pixelFormatCaps, bpp, window, double_buffer);
}

/*
 * Find an appropriate pixel format
 */
static int findPixelFormatFromBPP(JNIEnv *env, jobject pixel_format, int bpp)
{
	jclass cls_pixel_format = env->GetObjectClass(pixel_format);
	int alpha = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "alpha", "I"));
	int depth = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "depth", "I"));
	int stencil = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "stencil", "I"));
	int samples = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "samples", "I"));
	int num_aux_buffers = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "num_aux_buffers", "I"));
	int accum_bpp = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "accum_bpp", "I"));
	int accum_alpha = (int)env->GetIntField(pixel_format, env->GetFieldID(cls_pixel_format, "accum_alpha", "I"));
	jboolean stereo = env->GetBooleanField(pixel_format, env->GetFieldID(cls_pixel_format, "stereo", "Z"));
	unsigned int flags = PFD_DRAW_TO_WINDOW |   // support window 
		PFD_SUPPORT_OPENGL |   // support OpenGL 
		PFD_DOUBLEBUFFER;      // double buffered 
	if (stereo)
		flags = flags | PFD_STEREO;
	PIXELFORMATDESCRIPTOR pfd = { 
		sizeof(PIXELFORMATDESCRIPTOR),   // size of this pfd 
		1,                     // version number 
		flags,         // RGBA type 
		PFD_TYPE_RGBA,
		(BYTE)bpp,       
		0, 0, 0, 0, 0, 0,      // color bits ignored 
		(BYTE)alpha,       
		0,                     // shift bit ignored 
		accum_bpp + accum_alpha,                     // no accumulation buffer 
		0, 0, 0, 0,            // accum bits ignored 
		(BYTE)depth,       
		(BYTE)stencil,     
		num_aux_buffers, 
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
		jboolean allowSoftwareOpenGL = getBooleanProperty(env, "org.lwjgl.opengl.Window.allowSoftwareOpenGL");
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

static int findPixelFormat(JNIEnv *env, jobject pixel_format) {
	int bpp;
	jclass cls_pixel_format = env->GetObjectClass(pixel_format);
	bpp = GetDeviceCaps(hdc, BITSPIXEL);
	int iPixelFormat = findPixelFormatFromBPP(env, pixel_format, bpp);
	if (iPixelFormat == -1) {
		return findPixelFormatFromBPP(env, pixel_format, 16);
	} else
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
static bool createWindow(JNIEnv *env, int width, int height, bool fullscreen, bool undecorated)
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
	hwnd = CreateWindowEx (
		 exstyle, 
		 WINDOWCLASSNAME,
		 "",
		 windowflags,
		 0, 0, clientSize.right - clientSize.left, clientSize.bottom - clientSize.top,
		 NULL,
		 NULL,
		 dll_handle,
		 NULL);

	if (hwnd == NULL) {
		throwException(env, "Failed to create the window.");
		return false;
	}

	hdc = GetDC(hwnd);
	return true;
}

/*
 * Class:     org_lwjgl_Window
 * Method:    nSetTitle
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_nSetTitle
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
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_nUpdate
  (JNIEnv * env, jclass clazz)
{
	handleMessages(env, clazz);
}


/*
 * Class:     org_lwjgl_Window
 * Method:    swapBuffers
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_swapBuffers
  (JNIEnv * env, jclass clazz)
{
	isDirty = false;
	SwapBuffers(hdc);
//	wglSwapLayerBuffers(hdc, WGL_SWAP_MAIN_PLANE);
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nIsDirty
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Display_nIsDirty
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
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Display_nIsVisible
  (JNIEnv *env, jclass clazz) {
	return isMinimized ? JNI_FALSE : JNI_TRUE;
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nIsCloseRequested
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Display_nIsCloseRequested
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
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Display_nIsActive
  (JNIEnv *env, jclass clazz) {
	return isFocused;
}

/*
 * Class:     org_lwjgl_opengl_Window
 * Method:    nSetVSyncEnabled
 * Signature: (Z)Z
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_nSetVSyncEnabled
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

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_nMakeCurrent
  (JNIEnv *env, jclass clazz)
{
	wglMakeCurrent(hdc, hglrc);
}

/*
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_nReshape
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

JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_opengl_Display_nGetAvailableDisplayModes(JNIEnv *env, jclass clazz) {
	return getAvailableDisplayModes(env);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_nCreateWindow(JNIEnv *env, jclass clazz, jobject mode, jboolean fullscreen) {
	closerequested = false;
	isMinimized = false;
	isFocused = true;
	isDirty = true;
	isFullScreen = fullscreen == JNI_TRUE;
	isUndecorated = getBooleanProperty(env, "org.lwjgl.opengl.Window.undecorated");
	jclass cls_displayMode = env->GetObjectClass(mode);
	jfieldID fid_width = env->GetFieldID(cls_displayMode, "width", "I");
	jfieldID fid_height = env->GetFieldID(cls_displayMode, "height", "I");
	int width = env->GetIntField(mode, fid_width);
	int height = env->GetIntField(mode, fid_height);

	if (!createWindow(env, width, height, isFullScreen, isUndecorated)) {
		return;
	}
	if (!applyPixelFormat(env, hdc, pixel_format_index)) {
		closeWindow();
		return;
	}

	BOOL result = wglMakeCurrent(hdc, hglrc);
	if (!result) {
		throwException(env, "Could not bind context to window");
		closeWindow();
		return;
	}
	extgl_InitWGL(env);
	ShowWindow(hwnd, SW_SHOW);
	UpdateWindow(hwnd);
	SetForegroundWindow(hwnd);
	SetFocus(hwnd);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_nDestroyWindow(JNIEnv *env, jclass clazz) {
	closeWindow();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_switchDisplayMode(JNIEnv *env, jclass clazz, jobject mode) {
	switchDisplayMode(env, mode);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_resetDisplayMode(JNIEnv *env, jclass clazz) {
	resetDisplayMode(env);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Display_getGammaRampLength(JNIEnv *env, jclass clazz) {
	return getGammaRampLength();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_setGammaRamp(JNIEnv *env, jclass clazz, jobject gamma_buffer) {
	setGammaRamp(env, gamma_buffer);
}

JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_Display_getAdapter(JNIEnv *env, jclass clazz) {
	return getAdapter(env);
}

JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_Display_getVersion(JNIEnv *env, jclass clazz) {
	return getVersion(env);
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_Display_init(JNIEnv *env, jclass clazz) {
	return initDisplay(env);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_createContext(JNIEnv *env, jclass clazz, jobject pixel_format) {
	if (!extgl_Open()) {
		throwException(env, "Failed to open extgl");
		return;
	}

	// 1. Register window class if necessary
	if (!registerWindow()) {
		throwException(env, "Could not register window class");
		return;
	}

	if (!createWindow(env, 1, 1, false, false)) {
		extgl_Close();
		return;
	}
	pixel_format_index = findPixelFormat(env, pixel_format);
	if (pixel_format_index == -1) {
		extgl_Close();
		return;
	}
	// Special option for allowing software opengl	
	if (!applyPixelFormat(env, hdc, pixel_format_index)) {
		closeWindow();
		extgl_Close();
		return;
	}
	
	hglrc = wglCreateContext(hdc);
	if (hglrc == NULL) {
		throwException(env, "Failed to create OpenGL rendering context");
		extgl_Close();
		return;
	}
	BOOL result = wglMakeCurrent(hdc, hglrc);
	if (!result) {
		throwException(env, "Could not bid context to dummy window");
		wglDeleteContext(hglrc);
		closeWindow();
		return;
	}
	// Some crazy strangeness here so we can use ARB_pixel_format to specify the number
	// of multisamples we want. If the extension is present we'll delete the existing
	// rendering context and start over, using the ARB extension instead to pick the context.
	extgl_InitWGL(env);
	if (extgl_Extensions.WGL_ARB_pixel_format) {
		pixel_format_index = findPixelFormatARB(env, pixel_format, NULL, true, true, true);
		wglMakeCurrent(NULL, NULL);
		wglDeleteContext(hglrc);
		if (pixel_format_index == -1) {
			extgl_Close();
			return;
		}
		hglrc = wglCreateContext(hdc);
		closeWindow();
		if (hglrc == NULL) {
			throwException(env, "Failed to create OpenGL rendering context (ARB)");
			extgl_Close();
			return;
		}
	} else
		closeWindow();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Display_destroyContext(JNIEnv *env, jclass clazz) {
	wglMakeCurrent(NULL, NULL);

	// Delete the rendering context
	if (hglrc != NULL) {
		printfDebug("Deleting GL context\n");
		wglDeleteContext(hglrc); 
		hglrc = NULL;
	}
	extgl_Close();
}
