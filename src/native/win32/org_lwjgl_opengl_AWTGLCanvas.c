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
 * @author$
 * @version$
 */

#include <windowsx.h>
#include <jawt_md.h>
#include "../common/common_tools.h"
#include "extgl_wgl.h"
#include "org_lwjgl_opengl_AWTGLCanvas.h"

extern bool createARBContextAndPixelFormat(JNIEnv *env, HDC hdc, jobject pixel_format, int *pixel_format_index_return, HGLRC *context_return);

/*
 * Grab the context from the incoming AWTGLCanvas and return it
 */
HGLRC getContext(JNIEnv * env, jobject awtglcanvas) {
    jclass cls_AWTGLCanvas = (*env)->GetObjectClass(env, awtglcanvas);
    jlong hglrc = (int)(*env)->GetLongField(env, awtglcanvas, (*env)->GetFieldID(env, cls_AWTGLCanvas, "context", "J"));
	return (HGLRC) hglrc;
}

/*
 * Grab the HWND from the incoming AWTGLCanvas's peer
 */
HWND getHWND(JNIEnv * env, jobject awtglcanvas) {
    jclass cls_AWTGLCanvas = (*env)->GetObjectClass(env, awtglcanvas);
    jobject componentPeer = (*env)->GetObjectField(env, awtglcanvas, (*env)->GetFieldID(env, cls_AWTGLCanvas, "peer", "Ljava/awt/peer/ComponentPeer;"));
	jclass cls_CanvasPeer = (*env)->GetObjectClass(env, componentPeer);
    jlong hwnd = (*env)->GetLongField(env, componentPeer, (*env)->GetFieldID(env, cls_CanvasPeer, "hwnd", "J"));
	return (HWND) hwnd;
}

/*
 * Stash the incoming context int the incoming AWTGLCanvas
 */
void setContext(JNIEnv * env, jobject awtglcanvas, HGLRC hglrc) {
    jclass cls_AWTGLCanvas = (*env)->GetObjectClass(env, awtglcanvas);
    (*env)->SetLongField(env, awtglcanvas, (*env)->GetFieldID(env, cls_AWTGLCanvas, "context", "J"), (jlong) hglrc);
}

/*
 * Class:     org_lwjgl_opengl_AWTGLCanvas
 * Method:    nCreateContext
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_AWTGLCanvas_nCreateContext
  (JNIEnv * env, jobject awtglcanvas)
{
	HWND hwnd;
	HDC hdc;
	HGLRC hglrc;
	BOOL result;
	jclass cls_pixel_format;
	int samples;
	int pixel_format_index_arb;
	int pixel_format_index;
	HGLRC context_arb;
	bool arb_success;
	jclass cls_AWTGLCanvas;
	jobject pixel_format;

	hwnd = getHWND(env, awtglcanvas);
	hdc = GetDC(hwnd);
    cls_AWTGLCanvas = (*env)->GetObjectClass(env, awtglcanvas);
    pixel_format = (*env)->GetObjectField(env, awtglcanvas, (*env)->GetFieldID(env, cls_AWTGLCanvas, "pixelFormat", "Lorg/lwjgl/opengl/PixelFormat;"));
	pixel_format_index = findPixelFormat(env, hdc, pixel_format);

	if (pixel_format_index == -1) {
		throwException(env, "Could not find a suitable pixel format");
		ReleaseDC(hwnd, hdc);
		return;
	}

	if (!applyPixelFormat(hdc, pixel_format_index)) {
		throwException(env, "Could not apply pixel format to component");
		ReleaseDC(hwnd, hdc);
		return;
	}
	
	hglrc = wglCreateContext(hdc);
	if (hglrc == NULL) {
		throwException(env, "Failed to create OpenGL rendering context");
		ReleaseDC(hwnd, hdc);
		return;
	}
	result = wglMakeCurrent(hdc, hglrc);
	if (!result) {
		throwException(env, "Could not bind context to component");
		wglDeleteContext(hglrc);
		ReleaseDC(hwnd, hdc);
		return;
	}
	extgl_InitWGL(env);
	cls_pixel_format = (*env)->GetObjectClass(env, pixel_format);
	samples = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "samples", "I"));
	if (samples > 0) {
		// Create a new context using ARB pixel format instead
		arb_success = createARBContextAndPixelFormat(env, hdc, pixel_format, &pixel_format_index_arb, &context_arb);
		wglDeleteContext(hglrc);
		if (!arb_success) {
			throwException(env, "Samples > 0 but could not find a suitable ARB pixel format");
			return;
		}
		hglrc = context_arb;
		pixel_format_index = pixel_format_index_arb;
	}

	// 4. Stash the native handle back
	setContext(env, awtglcanvas, hglrc);

	// 5. Release the GLRC
	wglMakeCurrent(hdc, NULL);

	// 6. Release DC back to windoze
	ReleaseDC(hwnd, hdc);

}

/*
 * Class:     org_lwjgl_opengl_AWTGLCanvas
 * Method:    nMakeCurrent
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_AWTGLCanvas_nMakeCurrent
  (JNIEnv * env, jobject awtglcanvas)
{
	HGLRC hglrc = getContext(env, awtglcanvas);
	HWND hwnd = getHWND(env, awtglcanvas);
	HDC hdc = GetDC(hwnd);
	BOOL result = wglMakeCurrent(hdc, hglrc);
	ReleaseDC(hwnd, hdc);
	if (result != TRUE) {
		LPVOID lpMsgBuf;
		if (!FormatMessage( 
			FORMAT_MESSAGE_ALLOCATE_BUFFER | 
			FORMAT_MESSAGE_FROM_SYSTEM | 
			FORMAT_MESSAGE_IGNORE_INSERTS,
			NULL,
			GetLastError(),
			MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), // Default language
			(LPTSTR) &lpMsgBuf,
			0,
			NULL ))
		{
			// Handle the error.
			printf("Failed\n");
		} else {
			throwException(env, (LPCTSTR)lpMsgBuf);
			// Free the buffer.
			LocalFree( lpMsgBuf );
		}
	}
}


/*
 * Class:     org_lwjgl_opengl_AWTGLCanvas
 * Method:    nDestroyContext
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_AWTGLCanvas_nDestroyContext
  (JNIEnv *env, jobject canvas)
{
	JAWT awt;
	JAWT_DrawingSurface* ds;
	JAWT_DrawingSurfaceInfo* dsi;
	JAWT_Win32DrawingSurfaceInfo* dsi_win;
	jboolean result;
	HGLRC hglrc;
	BOOL mcResult;
	LPVOID lpMsgBuf;
	jclass cls_AWTGLCanvas;
	jmethodID mid_doPaint;

	// Get the AWT
	awt.version = JAWT_VERSION_1_4;
	result = JAWT_GetAWT(env, &awt);
	if (result == JNI_FALSE) {
		throwGeneralException(env, "java/lang/RuntimeException", "Failed get AWT.");
		return;
	}

	// Get the drawing surface
	ds = awt.GetDrawingSurface(env, canvas);
	if (ds == NULL) {
		throwGeneralException(env, "java/lang/RuntimeException", "Failed get drawing surface.");
		return;
	}

	// Get the drawing surface info
	dsi = ds->GetDrawingSurfaceInfo(ds);

	// Get the platform-specific drawing info
	dsi_win = (JAWT_Win32DrawingSurfaceInfo*)dsi->platformInfo;

	hglrc = getContext(env, canvas);
	// make the rendering context not current 
	wglMakeCurrent(NULL, NULL) ; 
	// delete the rendering context 
	wglDeleteContext(hglrc); 

	// Free the drawing surface info
	ds->FreeDrawingSurfaceInfo(dsi);

	// Free the drawing surface
	awt.FreeDrawingSurface(ds);
}

/*
 * Class:     org_lwjgl_opengl_AWTGLCanvas
 * Method:    nPaint
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_AWTGLCanvas_nPaint
  (JNIEnv *env, jobject canvas)
{
	JAWT awt;
	JAWT_DrawingSurface* ds;
	JAWT_DrawingSurfaceInfo* dsi;
	JAWT_Win32DrawingSurfaceInfo* dsi_win;
	jboolean result;
	jint lock;
	HGLRC hglrc;
	BOOL mcResult;
	LPVOID lpMsgBuf;
	jclass cls_AWTGLCanvas;
	jmethodID mid_doPaint;

	// Get the AWT
	awt.version = JAWT_VERSION_1_4;
	result = JAWT_GetAWT(env, &awt);
	if (result == JNI_FALSE) {
		throwGeneralException(env, "java/lang/RuntimeException", "Failed get AWT.");
		return;
	}

	// Get the drawing surface
	ds = awt.GetDrawingSurface(env, canvas);
	if (ds == NULL) {
		throwGeneralException(env, "java/lang/RuntimeException", "Failed get drawing surface.");
		return;
	}

	// Lock the drawing surface
	lock = ds->Lock(ds);
	if ((lock & JAWT_LOCK_ERROR) != 0) {
		throwGeneralException(env, "java/lang/RuntimeException", "Failed to lock drawing surface.");
		return;
	}

	// Get the drawing surface info
	dsi = ds->GetDrawingSurfaceInfo(ds);

	// Get the platform-specific drawing info
	dsi_win = (JAWT_Win32DrawingSurfaceInfo*)dsi->platformInfo;

	hglrc = getContext(env, canvas);
	mcResult = wglMakeCurrent(dsi_win->hdc, hglrc);
	if (mcResult != TRUE) {
		if (!FormatMessage( 
			FORMAT_MESSAGE_ALLOCATE_BUFFER | 
			FORMAT_MESSAGE_FROM_SYSTEM | 
			FORMAT_MESSAGE_IGNORE_INSERTS,
			NULL,
			GetLastError(),
			MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), // Default language
			(LPTSTR) &lpMsgBuf,
			0,
			NULL ))
		{
			// Handle the error.
			printf("Failed\n");
		} else {
			printfDebug("%s\n", lpMsgBuf);
			throwGeneralException(env, "java/lang/RuntimeException", lpMsgBuf);
			// Free the buffer.
			LocalFree( lpMsgBuf );
			// Don't return yet, let's free up stuff
		}
	} else {
		// Callback paint
		cls_AWTGLCanvas = (*env)->GetObjectClass(env, canvas);
		mid_doPaint = (*env)->GetMethodID(env, cls_AWTGLCanvas, "cPaint", "()V");
		(*env)->CallVoidMethod(env, canvas, mid_doPaint);
		SwapBuffers(dsi_win->hdc);
	}

	// Free the drawing surface info
	ds->FreeDrawingSurfaceInfo(dsi);

	// Unlock the drawing surface
	ds->Unlock(ds);

	// Free the drawing surface
	awt.FreeDrawingSurface(ds);
}

