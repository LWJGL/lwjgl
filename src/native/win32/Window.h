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
 * Include file to access public window features
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
#ifndef _LWJGL_WINDOW_H_INCLUDED_
	#define _LWJGL_WINDOW_H_INCLUDED_

	#define WIN32_LEAN_AND_MEAN
	#define _WIN32_WINDOWS 0x0410
	#define WINVER 0x0410
	#define _WIN32_WINNT 0x0400

	#include <windows.h>
	#include <jni.h>
	#include "extgl.h"

	#ifdef _PRIVATE_WINDOW_H_
		#define WINDOW_H_API
	#else
		#define WINDOW_H_API extern
		extern bool		isFullScreen;				// Whether we're fullscreen or not
		extern bool		isMinimized;				// Whether we're minimized or not
		extern bool		isFocused;					// Whether we're focused or not
		extern bool		isDirty;					  // Whether we're dirty or not
	#endif /* _PRIVATE_WINDOW_H_ */

	WINDOW_H_API HWND getCurrentHWND();

	WINDOW_H_API HGLRC getCurrentContext();

	WINDOW_H_API bool applyPixelFormat(HDC hdc, int iPixelFormat);

	WINDOW_H_API void closeWindow(HWND *hwnd, HDC *hdc);

	WINDOW_H_API void handleMouseMoved(int x, int y);

	WINDOW_H_API void handleMouseScrolled(int dwheel);

	WINDOW_H_API void handleMouseButton(int button, int state);

	WINDOW_H_API void handleMessages(void);

	/*
	 * Find a suitable pixel format
	 */
	WINDOW_H_API int findPixelFormat(JNIEnv *env, HDC hdc, jobject pixel_format);

	/*
	 * Find a suitable pixel format using the WGL_ARB_pixel_format extension
	 */
	WINDOW_H_API int findPixelFormatARB(JNIEnv *env, HDC hdc, jobject pixel_format, jobject pixelFormatCaps, bool use_hdc_bpp, bool window, bool pbuffer, bool double_buffer);

	/*
	 * Create a window with the specified title, position, size, and
	 * fullscreen attribute. The window will have DirectInput associated
	 * with it.
	 * 
	 * Returns true for success, or false for failure
	 */
	WINDOW_H_API HWND createWindow(int x, int y, int width, int height, bool fullscreen, bool undecorated);


	/*
	 * Handle native Win32 messages
	 */
	WINDOW_H_API void handleMessage(JNIEnv * env, jobject obj);

#endif /* _LWJGL_WINDOW_H_INCLUDED_ */
