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
 * $Id$
 *
 * Base Windows display
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#define _PRIVATE_WINDOW_H_
#include <malloc.h>
#include "Window.h"
#include <windowsx.h>
/*#include "extgl_wgl.h"*/
#include "common_tools.h"
#include "display.h"
#include "org_lwjgl_opengl_WindowsDisplay.h"
#include "org_lwjgl_WindowsSysImplementation.h"
#include "context.h"
#include <commctrl.h>

#define WINDOWCLASSNAME _T("LWJGL")

static jclass windowsDisplayClass;
static jmethodID javaWindowProc;

/*
 *	WindowProc for the GL window.
 */
static LRESULT CALLBACK lwjglWindowProc(HWND hWnd,
							     UINT msg,
							     WPARAM wParam,
							     LPARAM lParam)
{
	/*
	jclass display_class;
	jclass display_class_global;
	jmethodID handleMessage_method;
	LONG message_time;
	*/
	JNIEnv *env = getThreadEnv();
	if (env != NULL && !(*env)->ExceptionOccurred(env)) {
		/*
		 * We'll cache a global reference to the WindowsDisplay class in the window's user data.
		 * This is not so much to avoid lookup overhead as it is to avoid problems
		 * with AWT. Specifically, awt code can indirectly call this message handler
		 * when it does a SendMessage on the main thread to the currently focused window,
		 * which could be a LWJGL window. The FindClass will then fail because the calling
		 * internal awt class is using the system class loader, not the application loader
		 * where lwjgl is found.
		 *
		 * The very first message sent to this handler is sent when
		 * a window is created, where we are sure that the calling class' classloader has
		 * LWJGL classes in it.
		 */

		/*
		display_class_global = (jclass)(LONG_PTR)GetWindowLongPtr(hWnd, GWLP_USERDATA);
		if (display_class_global == NULL) {
			display_class = (*env)->FindClass(env, "org/lwjgl/opengl/WindowsDisplay");
			if (display_class != NULL) {
				display_class_global = (*env)->NewGlobalRef(env, display_class);
				if (display_class_global != NULL)
					SetWindowLongPtr(hWnd, GWLP_USERDATA, (LONG_PTR)display_class_global);
			}
		}
		if (display_class_global != NULL) {
			message_time = GetMessageTime();
			handleMessage_method = (*env)->GetStaticMethodID(env, display_class_global, "handleMessage", "(JIJJJ)J");
			if (handleMessage_method != NULL)
				return (*env)->CallStaticLongMethod(env, display_class_global, handleMessage_method, (jlong)(intptr_t)hWnd, (jint)msg, (jlong)wParam, (jlong)lParam, (jlong)message_time);

		}
		*/

		return (*env)->CallStaticLongMethod(
			env, windowsDisplayClass, javaWindowProc,
			(jlong)(intptr_t)hWnd, (jint)msg, (jlong)wParam, (jlong)lParam, (jlong)GetMessageTime()
		);
	}
	return DefWindowProc(hWnd, msg, wParam, lParam);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_setWindowProc(JNIEnv *env, jclass clazz, jobject method) {
	windowsDisplayClass = clazz;
	javaWindowProc = (*env)->FromReflectedMethod(env, method);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_WindowsDisplay_defWindowProc(JNIEnv *env, jclass unused, jlong hWnd, jint msg, jlong wParam, jlong lParam) {
    return DefWindowProc((HWND)(INT_PTR)hWnd, msg, wParam, lParam);
}

/*
 * Handle native Windows messages
 */
static void handleMessages(JNIEnv *env) {
	/*
	 * Now's our chance to deal with Windows messages that are
	 * otherwise just piling up and causing everything not to
	 * work properly
	 */
	MSG msg;
	while (!(*env)->ExceptionOccurred(env) && PeekMessage(
				&msg,         // message information
				NULL,           // handle to window
				0,  // first message
				0,  // last message
				PM_REMOVE      // removal options
				))
	{
		if (msg.message == WM_QUIT)
			break;
		TranslateMessage(&msg);
		DispatchMessage(&msg);
	}
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_WindowsDisplay_getDC(JNIEnv *env, jclass unused, jlong hwnd_ptr) {
	HWND hwnd = (HWND)(INT_PTR)hwnd_ptr;
	return (INT_PTR)GetDC(hwnd);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nSetTitle
  (JNIEnv * env, jclass unused, jlong hwnd_ptr, jlong title) {
    HWND hwnd = (HWND)(INT_PTR)hwnd_ptr;
	SetWindowText(hwnd, (LPCTSTR)(intptr_t)title);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nUpdate(JNIEnv * env, jclass class) {
	handleMessages(env);
}

JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_opengl_WindowsDisplay_getAvailableDisplayModes(JNIEnv *env, jobject self) {
	return getAvailableDisplayModes(env);
}

JNIEXPORT jint JNICALL Java_org_lwjgl_DefaultSysImplementation_getJNIVersion
  (JNIEnv *env, jobject ignored) {
	return org_lwjgl_WindowsSysImplementation_JNI_VERSION;
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nCreateWindow(JNIEnv *env, jclass unused, jint x, jint y, jint width, jint height, jboolean undecorated, jboolean child_window, jlong parent_hwnd) {
	HWND hwnd;
	static bool oneShotInitialised = false;
	if (!oneShotInitialised) {
		if (!registerWindow(lwjglWindowProc, WINDOWCLASSNAME)) {
			throwException(env, "Could not register window class");
			return 0;
		}
		oneShotInitialised = true;
	}

	hwnd = createWindow(WINDOWCLASSNAME, x, y, width, height, undecorated, child_window, (HWND)(INT_PTR)parent_hwnd);
	return (INT_PTR)hwnd;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nReleaseDC(JNIEnv *env, jclass clazz, jlong hwnd_ptr, jlong hdc_ptr) {
	HWND hwnd = (HWND)(INT_PTR)hwnd_ptr;
	HDC hdc = (HDC)(INT_PTR)hdc_ptr;
	ReleaseDC(hwnd, hdc);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nDestroyWindow(JNIEnv *env, jclass clazz, jlong hwnd_ptr) {
	jclass display_class_global;
	HWND hwnd = (HWND)(INT_PTR)hwnd_ptr;
	display_class_global = (jclass)(LONG_PTR)GetWindowLongPtr(hwnd, GWLP_USERDATA);
	ShowWindow(hwnd, SW_HIDE);
	DestroyWindow(hwnd);
	if (display_class_global != NULL)
		(*env)->DeleteGlobalRef(env, display_class_global);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_clientToScreen(JNIEnv *env, jclass unused, jlong hwnd_int, jobject buffer_handle) {
	HWND hwnd = (HWND)(INT_PTR)hwnd_int;
	POINT point;
	jint *buffer = (jint *)(*env)->GetDirectBufferAddress(env, buffer_handle);
	jlong size = (*env)->GetDirectBufferCapacity(env, buffer_handle);
	if (size < 2) {
		throwFormattedRuntimeException(env, "Buffer size < 2", size);
		return;
	}
	point.x = buffer[0];
	point.y = buffer[1];
	ClientToScreen(hwnd, &point);
	buffer[0] = point.x;
	buffer[1] = point.y;
}


JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_WindowsDisplay_getWindowRect(JNIEnv *env, jclass unused, jlong hwnd_int, jobject buffer_handle) {
	HWND hwnd = (HWND)(INT_PTR)hwnd_int;
	RECT *buffer = (RECT *)(*env)->GetDirectBufferAddress(env, buffer_handle);
	jlong size = (*env)->GetDirectBufferCapacity(env, buffer_handle);
	if (size < 4) {
		throwFormattedRuntimeException(env, "Buffer size < 4", size);
		return false;
	}
	return GetWindowRect(hwnd, buffer);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_WindowsDisplay_getForegroundWindow(JNIEnv *env, jclass unused) {
	return (INT_PTR)GetForegroundWindow();
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_WindowsDisplay_getDesktopWindow(JNIEnv *env, jclass unused) {
	return (INT_PTR)GetDesktopWindow();
}

static void copyBufferToRect(JNIEnv *env, jobject buffer_handle, RECT *rect) {
	jint *buffer = (jint *)(*env)->GetDirectBufferAddress(env, buffer_handle);
	jlong size = (*env)->GetDirectBufferCapacity(env, buffer_handle);
	if (size < 4) {
		throwFormattedRuntimeException(env, "Buffer size < 4", size);
		return;
	}
	rect->top = buffer[0];
	rect->bottom = buffer[1];
	rect->left = buffer[2];
	rect->right = buffer[3];
}

static void copyRectToBuffer(JNIEnv *env, RECT *rect, jobject buffer_handle) {
	jint *buffer = (jint *)(*env)->GetDirectBufferAddress(env, buffer_handle);
	jlong size = (*env)->GetDirectBufferCapacity(env, buffer_handle);
	if (size < 4) {
		throwFormattedRuntimeException(env, "Buffer size < 4", size);
		return;
	}
	buffer[0] = rect->top;
	buffer[1] = rect->bottom;
	buffer[2] = rect->left;
	buffer[3] = rect->right;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_clipCursor(JNIEnv *env, jclass unused, jobject handle_buffer) {
	RECT clip_rect;
	LPRECT clip_rect_ptr;
	if (handle_buffer != NULL) {
		copyBufferToRect(env, handle_buffer, &clip_rect);
		clip_rect_ptr = &clip_rect;
	} else
		clip_rect_ptr = NULL;
	if (ClipCursor(clip_rect_ptr) == 0)
		throwFormattedException(env, "ClipCursor failed (%d)", GetLastError());
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nSwitchDisplayMode(JNIEnv *env, jobject self, jobject mode) {
	switchDisplayMode(env, mode);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nResetDisplayMode(JNIEnv *env, jobject self) {
	resetDisplayMode(env);
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_WindowsDisplay_getCurrentDisplayMode(JNIEnv *env, jclass unused) {
	return getCurrentDisplayMode(env);
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_WindowsDisplay_convertToNativeRamp(JNIEnv *env, jclass unused, jobject float_ramp) {
	return convertToNativeRamp(env, float_ramp);
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_WindowsDisplay_getCurrentGammaRamp(JNIEnv *env, jclass unused) {
	return getCurrentGammaRamp(env);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nSetGammaRamp(JNIEnv *env, jclass unused, jobject gamma_buffer) {
	setGammaRamp(env, gamma_buffer);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_showWindow(JNIEnv *env, jclass unused, jlong hwnd_ptr, jint mode) {
	HWND hwnd = (HWND)(INT_PTR)hwnd_ptr;
	ShowWindow(hwnd, mode);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_setForegroundWindow(JNIEnv *env, jclass unused, jlong hwnd_ptr) {
	HWND hwnd = (HWND)(INT_PTR)hwnd_ptr;
	SetForegroundWindow(hwnd);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_setFocus(JNIEnv *env, jclass unused, jlong hwnd_ptr) {
	HWND hwnd = (HWND)(INT_PTR)hwnd_ptr;
	SetFocus(hwnd);
}

JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nGetVersion(JNIEnv *env, jobject self, jstring driver) {
	char *driver_str;
	jstring result;
	driver_str = GetStringNativeChars(env, driver);
	if (driver_str == NULL)
		return NULL;
	result = getVersion(env, driver_str);
	free(driver_str);
	return result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nReshape(JNIEnv *env, jclass unused, jlong hwnd_ptr, jint x, jint y, jint width, jint height, jboolean undecorated, jboolean child) {
	HWND hwnd = (HWND)(INT_PTR)hwnd_ptr;
	DWORD exstyle, windowflags;
	RECT clientSize;

	getWindowFlags(&windowflags, &exstyle, undecorated, child);

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
	SetWindowPos(hwnd, HWND_TOP, x, y, clientSize.right - clientSize.left, clientSize.bottom - clientSize.top, SWP_NOZORDER);
}

static HICON createWindowIcon(JNIEnv *env, jint *pixels, jint width, jint height) {
	BITMAPV5HEADER bitmapInfo;
	HBITMAP cursorMask;
	HBITMAP	colorBitmap;
	ICONINFO iconInfo;
	HICON icon;
	char *ptrCursorImage;
	int x, y;
	char *dstPtr;
	int imageSize;
	unsigned char *maskPixels;
	int widthInBytes;
	int scanlineWidth;
	HBITMAP colorDIB;

	memset(&bitmapInfo, 0, sizeof(BITMAPV5HEADER));
	bitmapInfo.bV5Size              = sizeof(BITMAPV5HEADER);
	bitmapInfo.bV5Width             = width;
	bitmapInfo.bV5Height            = -height;
	bitmapInfo.bV5Planes            = 1;
	bitmapInfo.bV5BitCount          = 32;
	bitmapInfo.bV5Compression       = BI_BITFIELDS;
	bitmapInfo.bV5RedMask   =  0x00FF0000;
    bitmapInfo.bV5GreenMask =  0x0000FF00;
    bitmapInfo.bV5BlueMask  =  0x000000FF;
    bitmapInfo.bV5AlphaMask =  0xFF000000;

	colorDIB = CreateDIBSection(GetDC(NULL), (BITMAPINFO*)&(bitmapInfo),
			DIB_RGB_COLORS, (void*)&(ptrCursorImage), NULL, 0);
	if (!ptrCursorImage) {
		throwException(env, "Could not allocate DIB section.");
	}

	for (y = 0; y < height; y++ ) {
   		dstPtr = ptrCursorImage + width*4*y;;
   		for (x = 0; x < width; x++ ) {
			dstPtr[0] = (pixels[y*width+x] >> 0x10) & 0xFF;
			dstPtr[1] = (pixels[y*width+x] >> 0x08) & 0xFF;
			dstPtr[2] = pixels[y*width+x] & 0xFF;
			dstPtr[3] = (pixels[y*width+x] >> 0x18) & 0xFF;
   			dstPtr += 4;
   		}
   	}


	colorBitmap = CreateDIBitmap(GetDC(NULL),
			(BITMAPINFOHEADER*)&bitmapInfo,
			CBM_INIT,
			(void *)ptrCursorImage,
			(BITMAPINFO*)&bitmapInfo,
			DIB_RGB_COLORS);

	DeleteObject(colorDIB);

	// Convert alpha map to pixel packed mask

	// number of bytes it takes to fit a bit packed scan line.
	widthInBytes = (width & 0x7) != 0 ? (width >> 3) + 1 : (width >> 3);

	// number of bytes it takes to fit WORD padded scan line.
	scanlineWidth = widthInBytes;
	if (scanlineWidth % sizeof(WORD) != 0) {
		scanlineWidth = (scanlineWidth / sizeof(WORD)) * sizeof(WORD) + sizeof(WORD);
	}
	imageSize = scanlineWidth*height;
	maskPixels = (unsigned char*)malloc(sizeof(unsigned char)*imageSize);
	memset(maskPixels, 0xFF, sizeof(unsigned char)*imageSize);

	cursorMask = CreateBitmap(width, height, 1, 1, maskPixels);

	memset(&iconInfo, 0, sizeof(ICONINFO));
	iconInfo.hbmMask = cursorMask;
	iconInfo.hbmColor = colorBitmap;
	iconInfo.fIcon = TRUE;
	iconInfo.xHotspot = 0;
	iconInfo.yHotspot = 0;
	icon = CreateIconIndirect(&iconInfo);
	DeleteObject(colorBitmap);
	DeleteObject(cursorMask);
	free(maskPixels);

	return icon;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_destroyIcon
  (JNIEnv *env, jclass clazz, jlong handle) {
	  HICON icon = (HICON)(INT_PTR)handle;
	  DestroyIcon(icon);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_WindowsDisplay_createIcon
  (JNIEnv *env, jclass clazz, jint width, jint height, jobject iconBuffer) {
	jint *imgData = (jint *)(*env)->GetDirectBufferAddress(env, iconBuffer);
	return (INT_PTR)createWindowIcon(env, imgData, width, height);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_WindowsDisplay_sendMessage
  (JNIEnv *env, jclass clazz, jlong hwnd_ptr, jlong msg, jlong wparam, jlong lparam) {
	HWND hwnd = (HWND)(INT_PTR)hwnd_ptr;
	return SendMessage(hwnd, (UINT)msg, (WPARAM)wparam, (LPARAM)lparam);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_WindowsDisplay_setWindowLongPtr
  (JNIEnv *env, jclass clazz, jlong hwnd_ptr, jint nindex, jlong longPtr) {
	HWND hwnd		= (HWND)(INT_PTR)hwnd_ptr;
	return SetWindowLongPtr(hwnd, nindex, (LONG_PTR) longPtr);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_WindowsDisplay_getWindowLongPtr
  (JNIEnv *env, jclass clazz, jlong hwnd_ptr, jint nindex) {
	HWND hwnd		= (HWND)(INT_PTR)hwnd_ptr;
	jlong result = GetWindowLongPtr(hwnd, nindex);
	return result;
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_WindowsDisplay_setWindowPos
  (JNIEnv *env, jclass clazz, jlong hwnd_ptr, jlong hwnd_after_ptr, jint x, jint y, jint width, jint height, jlong uflags) {
	jboolean result;
	HWND hwnd		= (HWND)(INT_PTR)hwnd_ptr;
	HWND hwnd_after	= (HWND)(INT_PTR)hwnd_after_ptr;

	result = SetWindowPos(hwnd, hwnd_after, x, y, width, height, (UINT) uflags);
	return result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nSetCursorPosition
(JNIEnv * env, jclass unused, jint x, jint y) {
	if (!SetCursorPos(x, y))
		printfDebugJava(env, "SetCursorPos failed");
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_getClientRect
	(JNIEnv *env, jclass unused, jlong hwnd_int, jobject rect_buffer) {
	HWND hwnd = (HWND)(INT_PTR)hwnd_int;
	RECT clientRect;
	GetClientRect(hwnd, &clientRect);
	copyRectToBuffer(env, &clientRect, rect_buffer);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_WindowsDisplay_adjustWindowRectEx
	(JNIEnv *env, jclass unused, jobject rect_buffer, jint style, jboolean menu, jint styleex) {
	jboolean result;
	RECT clientRect;
	copyBufferToRect(env, rect_buffer, &clientRect);
	result = AdjustWindowRectEx(&clientRect, style, menu, styleex);
	copyRectToBuffer(env, &clientRect, rect_buffer);
	return result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nSetNativeCursor
	(JNIEnv *env, jclass unused, jlong hwnd_int, jobject handle_buffer)
{
	HWND hwnd = (HWND)(INT_PTR)hwnd_int;
	HCURSOR *cursor_handle;
	HCURSOR cursor;
	if (handle_buffer != NULL) {
		cursor_handle = (HCURSOR *)(*env)->GetDirectBufferAddress(env, handle_buffer);
		cursor = *cursor_handle;
		SetClassLongPtr(hwnd, GCLP_HCURSOR, (LONG_PTR)cursor);
		SetCursor(cursor);
	} else {
		SetClassLongPtr(hwnd, GCLP_HCURSOR, (LONG_PTR)NULL);
		SetCursor(LoadCursor(NULL, IDC_ARROW));
	}
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDisplay_getSystemMetrics(JNIEnv *env, jclass unused, jint index) {
	return GetSystemMetrics(index);
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nSetCapture(JNIEnv *env, jclass unused, jlong hwnd_int) {
	HWND hwnd = (HWND)(INT_PTR)hwnd_int;
	return (INT_PTR) SetCapture(hwnd);
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nReleaseCapture(JNIEnv *env, jclass unused) {
	return ReleaseCapture();
}

JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nTrackMouseEvent(JNIEnv *env, jclass clazz, jlong hwnd_ptr) {
		HWND hwnd = (HWND)(INT_PTR)hwnd_ptr;
		TRACKMOUSEEVENT tme;
		tme.cbSize = sizeof(TRACKMOUSEEVENT);
		tme.dwFlags = TME_LEAVE;
		tme.hwndTrack = hwnd;
		return TrackMouseEvent(&tme);
}

