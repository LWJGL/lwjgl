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
#include <windowsx.h>
#include <malloc.h>
#include "Window.h"
#include "extgl_wgl.h"
#include "common_tools.h"
#include "display.h"
#include "org_lwjgl_opengl_Win32Display.h"
#include "context.h"

static HWND       display_hwnd = NULL;            // Handle to the window
static HDC        display_hdc = NULL;             // Device context
static bool				isFullScreen = false;           // Whether we're fullscreen or not
static bool				isMinimized = false;            // Whether we're minimized or not
static bool       isFocused = false;              // whether we're focused or not
static bool       isDirty = false;                // Whether we're dirty or not
static bool       isUndecorated = false;          // Whether we're undecorated or not
static bool		  did_maximize = false; // A flag to tell when a window
										// has recovered from minimized

static bool closerequested;

#define WINDOWCLASSNAME "LWJGL"

HDC getCurrentHDC() {
	return display_hdc;
}

HWND getCurrentHWND() {
	return display_hwnd;
}

static void setupCursorClipping() {
	RECT hwnd_client;
	if (display_hwnd != NULL && GetWindowRect(display_hwnd, &hwnd_client) != 0) {
		if (ClipCursor(&hwnd_client) == 0)
			printfDebug("ClipCursor failed\n");
	}
}

static void resetDisplayModeAndClipping(JNIEnv *env) {
	resetDisplayMode(env);
	ClipCursor(NULL);
}

/*
 * Called when the application is alt-tabbed to or from
 */
static void appActivate(bool active)
{
	static bool inAppActivate = false;
	if (inAppActivate) {
		return;
	}
	inAppActivate = true;
	isFocused = active;
	if (active) {
		if (isFullScreen) {
			restoreDisplayMode();
		}
		ShowWindow(display_hwnd, SW_RESTORE);
		SetForegroundWindow(display_hwnd);
		SetFocus(display_hwnd);
		did_maximize = true;
	} else if (isFullScreen) {
		ShowWindow(display_hwnd, SW_SHOWMINNOACTIVE);
		resetDisplayModeAndClipping(NULL);
	}
	inAppActivate = false;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Win32Display_didMaximize
  (JNIEnv *env, jobject self) {
	jboolean result = did_maximize ? JNI_TRUE : JNI_FALSE;
	did_maximize = false;
	return result;
}
/*
 *	WindowProc for the GL window.
 */
LRESULT CALLBACK lwjglWindowProc(HWND hWnd,
							     UINT msg,
							     WPARAM wParam,
							     LPARAM lParam)
{
      int xPos; 
      int yPos;
      int dwheel;
	if (isFullScreen && !isMinimized && isFocused)
		setupCursorClipping();
	switch (msg) {
		// disable screen saver and monitor power down messages which wreak havoc
		case WM_SYSCOMMAND:
		{
			switch (wParam) {
			case SC_KEYMENU:
			case SC_MOUSEMENU:
				// Ignore system menu retrieval
			case SC_SCREENSAVE:
			case SC_MONITORPOWER:
				return 0L;
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
			switch (wParam) {
				case WA_ACTIVE:
				case WA_CLICKACTIVE:
					appActivate(true);
					break;
				case WA_INACTIVE:
					appActivate(false);
					break;
			}
			return 0L;
		case WM_SIZE:
			switch (wParam) {
				case SIZE_RESTORED:
				case SIZE_MAXIMIZED:
					isMinimized = false;
					break;
				case SIZE_MINIMIZED:
					isMinimized = true;
					break;
			}
			break;
		case WM_MOUSEMOVE:
		{
                        xPos = GET_X_LPARAM(lParam); 
                        yPos = GET_Y_LPARAM(lParam);
			handleMouseMoved(xPos, yPos);
			return 0;
		}
		case WM_MOUSEWHEEL:
		{
                        dwheel = GET_WHEEL_DELTA_WPARAM(wParam);
			handleMouseScrolled(dwheel);
			return 0;
		}
		case WM_LBUTTONDOWN:
		{
			handleMouseButton(0, 1);
			return 0;
		}
		case WM_LBUTTONUP:
		{
			handleMouseButton(0, 0);
			return 0;
		}
		case WM_RBUTTONDOWN:
		{
			handleMouseButton(1, 1);
			return 0;
		}
		case WM_RBUTTONUP:
		{
			handleMouseButton(1, 0);
			return 0;
		}
		case WM_MBUTTONDOWN:
		{
			handleMouseButton(2, 1);
			return 0;
		}
		case WM_MBUTTONUP:
		{
			handleMouseButton(2, 0);
			return 0;
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
 * Handle native Win32 messages
 */
void handleMessages(void)
{
	/*
	 * Now's our chance to deal with Windows messages that are
	 * otherwise just piling up and causing everything not to
	 * work properly
	 */
	MSG msg;
	while (PeekMessage(
		&msg,         // message information
		NULL,           // handle to window
		0,  // first message
		0,  // last message
		PM_REMOVE      // removal options
		))
	{
		if (display_hwnd != NULL && msg.hwnd == display_hwnd)
	      	DispatchMessage(&msg);
	};
}

/*
 * Class:     org_lwjgl_Window
 * Method:    nSetTitle
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_setTitle
  (JNIEnv * env, jobject self, jstring title_obj)
{
	char * title = GetStringNativeChars(env, title_obj);
	SetWindowText(display_hwnd, title);
	free(title);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_nUpdate
  (JNIEnv * env, jobject self)
{
	handleMessages();
}


JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Win32Display_isDirty
  (JNIEnv *env, jobject self) {
	bool result = isDirty;
	isDirty = false;
	return result ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Win32Display_isVisible
  (JNIEnv *env, jobject self) {
	return isMinimized ? JNI_FALSE : JNI_TRUE;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Win32Display_isCloseRequested
  (JNIEnv *env, jobject self) {
	bool saved = closerequested;
	closerequested = false;
	return saved;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Win32Display_isActive
  (JNIEnv *env, jobject self) {
	return isFocused;
}

JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_opengl_Win32Display_getAvailableDisplayModes(JNIEnv *env, jobject self) {
	return getAvailableDisplayModes(env);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_nCreateWindow(JNIEnv *env, jobject self, jobject mode, jboolean fullscreen, jint x, jint y) {
	jclass cls_displayMode = (*env)->GetObjectClass(env, mode);
	jfieldID fid_width = (*env)->GetFieldID(env, cls_displayMode, "width", "I");
	jfieldID fid_height = (*env)->GetFieldID(env, cls_displayMode, "height", "I");
	int width = (*env)->GetIntField(env, mode, fid_width);
	int height = (*env)->GetIntField(env, mode, fid_height);
	BOOL result;
	static bool oneShotInitialised = false;
	if (!oneShotInitialised) {
		if (!registerWindow(lwjglWindowProc, WINDOWCLASSNAME)) {
			throwException(env, "Could not register window class");
			return;
		}
		oneShotInitialised = true;
	}

	closerequested = false;
	isMinimized = false;
	isFocused = false;
	isDirty = true;
	isFullScreen = fullscreen == JNI_TRUE;
	isUndecorated = getBooleanProperty(env, "org.lwjgl.opengl.Window.undecorated");
	display_hwnd = createWindow(WINDOWCLASSNAME, x, y, width, height, isFullScreen, isUndecorated);
	if (display_hwnd == NULL) {
		throwException(env, "Failed to create the window.");
		return;
	}
	display_hdc = GetDC(display_hwnd);
	ShowWindow(display_hwnd, SW_SHOWDEFAULT);
	UpdateWindow(display_hwnd);
	SetForegroundWindow(display_hwnd);
	SetFocus(display_hwnd);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_destroyWindow(JNIEnv *env, jobject self) {
	closeWindow(&display_hwnd, &display_hdc);
	if (isFullScreen)
		ClipCursor(NULL);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_switchDisplayMode(JNIEnv *env, jobject self, jobject mode) {
	switchDisplayMode(env, mode);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_resetDisplayMode(JNIEnv *env, jobject self) {
	resetDisplayModeAndClipping(env);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Win32Display_getGammaRampLength(JNIEnv *env, jobject self) {
	return getGammaRampLength();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_setGammaRamp(JNIEnv *env, jobject self, jobject gamma_buffer) {
	setGammaRamp(env, gamma_buffer);
}

JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_Win32Display_getAdapter(JNIEnv *env, jobject self) {
	return getAdapter(env);
}

JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_Win32Display_getVersion(JNIEnv *env, jobject self) {
	return getVersion(env);
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_Win32Display_init(JNIEnv *env, jobject self) {
	return initDisplay(env);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Win32Display_reshape(JNIEnv *env, jobject self, jint x, jint y, jint width, jint height) {
	DWORD exstyle, windowflags;
	RECT clientSize;

	if (isFullScreen) {
		return;
	}

	getWindowFlags(&windowflags, &exstyle, isFullscreen, getBooleanProperty(env, "org.lwjgl.opengl.Window.undecorated"));
	
	// If we're not a fullscreen window, adjust the height to account for the
	// height of the title bar:
	clientSize.bottom = height;
	clientSize.left = 0;
	clientSize.right = width;
	clientSize.top = 0;
	
	AdjustWindowRectEx(
	  &clientSize,    // client-rectangle structure
	  windowflags,    // window styles
	  FALSE,          // menu-present option
	  exstyle         // extended window style
	);

	SetWindowPos(display_hwnd, HWND_TOP, x, y, clientSize.right - clientSize.left, clientSize.bottom - clientSize.top, SWP_NOZORDER);
}
