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
 * Base Windows display
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
#include "org_lwjgl_opengl_WindowsDisplay.h"
#include "context.h"

static HICON small_icon = NULL;
static HICON large_icon = NULL;
static HWND       display_hwnd = NULL;            // Handle to the window
static HDC        display_hdc = NULL;             // Device context
										// has recovered from minimized

#define WINDOWCLASSNAME "LWJGL"

HDC getCurrentHDC() {
	return display_hdc;
}

HWND getCurrentHWND() {
	return display_hwnd;
}

static void freeLargeIcon() {
	if (large_icon != NULL) {
		DestroyIcon(large_icon);
		large_icon = NULL;
	}
}

static void freeSmallIcon() {
	if (small_icon != NULL) {
		DestroyIcon(small_icon);
		small_icon = NULL;
	}
}

/*
 *	WindowProc for the GL window.
 */
static LRESULT CALLBACK lwjglWindowProc(HWND hWnd,
							     UINT msg,
							     WPARAM wParam,
							     LPARAM lParam)
{
	jclass display_class;
	jclass display_class_global;
	jmethodID handleMessage_method;
	LONG message_time;
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
			handleMessage_method = (*env)->GetStaticMethodID(env, display_class_global, "handleMessage", "(JIJJJ)Z");
			if (handleMessage_method != NULL)
				if ((*env)->CallStaticBooleanMethod(env, display_class_global, handleMessage_method, (jlong)hWnd, (jint)msg, (jlong)wParam, (jlong)lParam, (jlong)message_time))
					return 0;
		}
	}

	// default action
    return DefWindowProc(hWnd, msg, wParam, lParam);
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
	if (display_hwnd != NULL) {
		while (!(*env)->ExceptionOccurred(env) && PeekMessage(
					&msg,         // message information
					NULL,           // handle to window
					0,  // first message
					0,  // last message
					PM_REMOVE      // removal options
					))
		{
			/*
			 * It would be better to filter messages
			 * to display_hwnd by specifying that to
			 * PeekMessage instead of this check. However,
			 * Windows will then mark LWJGL apps as "not
			 * responding".
			 */
			if (msg.hwnd == display_hwnd)
				DispatchMessage(&msg);
		}
	}
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_WindowsDisplay_getHwnd(JNIEnv *env, jclass unused) {
	return (INT_PTR)display_hwnd;
}

/*
 * Class:     org_lwjgl_Window
 * Method:    nSetTitle
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_setTitle
  (JNIEnv * env, jobject self, jstring title_obj) {
	char * title = GetStringNativeChars(env, title_obj);
	SetWindowText(display_hwnd, title);
	free(title);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nUpdate(JNIEnv * env, jclass class) {
	handleMessages(env);
}

JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_opengl_WindowsDisplay_getAvailableDisplayModes(JNIEnv *env, jobject self) {
	return getAvailableDisplayModes(env);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nCreateWindow(JNIEnv *env, jobject self, jobject mode, jboolean fullscreen, jint x, jint y) {
	jclass cls_displayMode = (*env)->GetObjectClass(env, mode);
	jfieldID fid_width = (*env)->GetFieldID(env, cls_displayMode, "width", "I");
	jfieldID fid_height = (*env)->GetFieldID(env, cls_displayMode, "height", "I");
	int width = (*env)->GetIntField(env, mode, fid_width);
	int height = (*env)->GetIntField(env, mode, fid_height);
	BOOL result;
	bool isUndecorated;          // Whether we're undecorated or not
	static bool oneShotInitialised = false;
	if (!oneShotInitialised) {
		if (!registerWindow(lwjglWindowProc, WINDOWCLASSNAME)) {
			throwException(env, "Could not register window class");
			return;
		}
		oneShotInitialised = true;
	}

	isUndecorated = getBooleanProperty(env, "org.lwjgl.opengl.Window.undecorated");
	display_hwnd = createWindow(WINDOWCLASSNAME, x, y, width, height, fullscreen, isUndecorated);
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

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nDestroyWindow(JNIEnv *env, jclass clazz) {
	jclass display_class_global = (jclass)(LONG_PTR)GetWindowLongPtr(display_hwnd, GWLP_USERDATA);
	closeWindow(&display_hwnd, &display_hdc);
	if (display_class_global != NULL)
		(*env)->DeleteGlobalRef(env, display_class_global);
	freeLargeIcon();
	freeSmallIcon();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_resetCursorClipping(JNIEnv *env, jclass unused) {
	ClipCursor(NULL);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_setupCursorClipping(JNIEnv *env, jclass unused, jlong hwnd_ptr) {
	HWND hwnd = (HWND)(INT_PTR)hwnd_ptr;
	RECT hwnd_client;
	if (display_hwnd != NULL && GetWindowRect(hwnd, &hwnd_client) != 0) {
		if (ClipCursor(&hwnd_client) == 0)
			throwFormattedException(env, "ClipCursor failed (%d)", GetLastError());
	}
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

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nReshape(JNIEnv *env, jclass unused, jlong hwnd_ptr, jint x, jint y, jint width, jint height) {
	HWND hwnd = (HWND)(INT_PTR)hwnd_ptr;
	DWORD exstyle, windowflags;
	RECT clientSize;

	getWindowFlags(&windowflags, &exstyle, false, getBooleanProperty(env, "org.lwjgl.opengl.Window.undecorated"));
	
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
	unsigned char col;
	unsigned char mask;
	BITMAPINFO bitmapInfo;
	HBITMAP cursorMask;
	HBITMAP	colorBitmap;
	ICONINFO iconInfo;
	HICON icon;
	char *ptrCursorImage;
	int x, y;
	char *dstPtr;
	int pixelCount;
	int scanlinePad;
	int wordAlignedWidth;
	int imageSize;
	unsigned char *maskPixels;
	int widthInBytes;
	int leftShift;
	int maskPixelsOff;
	int scanlineWidth;
	HBITMAP colorDIB;
	
    jsize pixelsLen = width * height;
	
	memset(&bitmapInfo, 0, sizeof(BITMAPINFO));
	bitmapInfo.bmiHeader.biSize              = sizeof(BITMAPINFOHEADER);
	bitmapInfo.bmiHeader.biWidth             = width;
	bitmapInfo.bmiHeader.biHeight            = -height;
	bitmapInfo.bmiHeader.biPlanes            = 1;
	bitmapInfo.bmiHeader.biBitCount          = 24;
	bitmapInfo.bmiHeader.biCompression       = BI_RGB;

	colorDIB = CreateDIBSection(GetDC(NULL), (BITMAPINFO*)&(bitmapInfo),
			DIB_RGB_COLORS, (void**)&(ptrCursorImage), NULL, 0);
	if (!ptrCursorImage) {
		throwException(env, "Could not allocate DIB section.");
	}
	
	wordAlignedWidth = width * 3;
	if (wordAlignedWidth % sizeof(long) != 0) {
		wordAlignedWidth = (wordAlignedWidth / sizeof(long)) * sizeof(long) + sizeof(long);
	}
	for (y = 0; y < height; y++ ) {
		dstPtr = ptrCursorImage + wordAlignedWidth*y;;
		for (x = 0; x < width; x++ ) {
			if ((pixels[y*width+x] & 0xFF000000) != 0) 
			{
				dstPtr[0] = (pixels[y*width+x] >> 0x10) & 0xFF;
				dstPtr[1] = (pixels[y*width+x] >> 0x08) & 0xFF;
				dstPtr[2] = pixels[y*width+x] & 0xFF;
			} 
			else 
			{
				dstPtr[0] = 0;
				dstPtr[1] = 0;
				dstPtr[2] = 0;
			}
			
			dstPtr += 3;
		}
	}
	

	colorBitmap = CreateDIBitmap(GetDC(NULL),
			(BITMAPINFOHEADER*)&bitmapInfo.bmiHeader,
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
	memset(maskPixels, 0, imageSize);
	
	for (y = 0; y < height; y++) {
		leftShift = 7;
		mask = 0;
		maskPixelsOff = scanlineWidth*y;
		for (x = 0; x < width; x++) {
			col = (((pixels[(width*y)+x] & 0xFF000000) != 0) ? 1 : 0) << leftShift;
			mask = mask | col;
			leftShift--;
			if (leftShift == -1) {
				maskPixels[maskPixelsOff++] = ~mask;
				leftShift = 7;
				mask = 0;
			}
		}
		// write what is left over
		if (leftShift != 7) {
			maskPixels[maskPixelsOff++] = ~mask;
		}
	}
	
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

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nSetWindowIcon16
  (JNIEnv *env, jclass clazz, jobject iconBuffer)
{
	int *imgData = (int *)(*env)->GetDirectBufferAddress(env, iconBuffer);
	
	freeSmallIcon();
	small_icon = createWindowIcon(env, imgData, 16, 16);
	if (small_icon != NULL) {
		if (display_hwnd != NULL) {
			SendMessage(display_hwnd, WM_SETICON, ICON_SMALL,  (LPARAM) (small_icon));
			
			return 0;
		}
	}
	
	return -1;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nSetWindowIcon32
  (JNIEnv *env, jclass clazz, jobject iconBuffer)
{
	int *imgData = (int *)(*env)->GetDirectBufferAddress(env, iconBuffer);
	
	freeLargeIcon();
	large_icon = createWindowIcon(env, imgData, 32, 32);
	if (large_icon != NULL) {
		if (display_hwnd != NULL) {
			SendMessage(display_hwnd, WM_SETICON, ICON_BIG,  (LPARAM) (large_icon));
			
			return 0;
		}
	}
	
	return -1;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_nSetCursorPosition
(JNIEnv * env, jclass unused, jint x, jint y, jboolean fullscreen) {
	DWORD windowflags, exstyle;
	int transformed_x, transformed_y;
	RECT window_rect;
	RECT client_rect;
	RECT adjusted_client_rect;

	int left_border_width;
	int bottom_border_width;

	getWindowFlags(&windowflags, &exstyle, fullscreen, getBooleanProperty(env, "org.lwjgl.opengl.Window.undecorated"));
	if (!GetClientRect(getCurrentHWND(), &client_rect)) {
		printfDebugJava(env, "GetClientRect failed");
		return;
	}

	adjusted_client_rect = client_rect;
	if (!AdjustWindowRectEx(&adjusted_client_rect, windowflags, FALSE, exstyle)) {
		printfDebugJava(env, "AdjustWindowRectEx failed");
		return;
	}
	
	if (!GetWindowRect(getCurrentHWND(), &window_rect)) {
		printfDebugJava(env, "GetWindowRect failed");
		return;
	}
	left_border_width = -adjusted_client_rect.left;
	bottom_border_width = adjusted_client_rect.bottom - client_rect.bottom;
	
	transformed_x = window_rect.left + left_border_width + x;
	transformed_y = window_rect.bottom - bottom_border_width - 1 - y;
	if (!SetCursorPos(transformed_x, transformed_y))
		printfDebugJava(env, "SetCursorPos failed");
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_WindowsDisplay_setNativeCursor
	(JNIEnv *env, jobject self, jobject handle_buffer)
{
	HCURSOR *cursor_handle;
	HCURSOR cursor;
	if (handle_buffer != NULL) {
		cursor_handle = (HCURSOR *)(*env)->GetDirectBufferAddress(env, handle_buffer);
		cursor = *cursor_handle;
		SetClassLongPtr(getCurrentHWND(), GCL_HCURSOR, (LONG_PTR)cursor);
		SetCursor(cursor);
	} else {
		SetClassLongPtr(getCurrentHWND(), GCL_HCURSOR, (LONG_PTR)NULL);
		SetCursor(LoadCursor(NULL, IDC_ARROW));
	}
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDisplay_transformY(JNIEnv *env, jclass unused, jlong hwnd_int, jint y) {
	HWND hwnd = (HWND)(INT_PTR)hwnd_int;
	RECT clientRect;
	GetClientRect(hwnd, &clientRect);
	return (clientRect.bottom - clientRect.top) - 1 - y;
}

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_WindowsDisplay_getSystemMetrics(JNIEnv *env, jclass unused, jint index) {
	return GetSystemMetrics(index);
}

