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
 * Include file to access public window features
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#ifndef _LWJGL_WINDOW_H_INCLUDED_
	#define _LWJGL_WINDOW_H_INCLUDED_

	#define WIN32_LEAN_AND_MEAN
	#include <windows.h>
	#include <jni.h>
	#undef  DIRECTINPUT_VERSION
	#define DIRECTINPUT_VERSION 0x0500
	#include <dinput.h>
	#include "extgl.h"

	#ifdef _PRIVATE_WINDOW_H_
		#define WINDOW_H_API
	#else
		#define WINDOW_H_API extern

		extern HWND		hwnd;								// Handle to the window
		extern HDC		hdc;								// Device context
		extern LPDIRECTINPUT	lpdi;				// DirectInput
		extern bool		isFullScreen;				// Whether we're fullscreen or not
		extern bool		isMinimized;				// Whether we're minimized or not
		extern bool		isFocused;					// Whether we're focused or not
		extern bool		isDirty;					  // Whether we're dirty or not
		extern RECT		clientSize;
		extern HGLRC	hglrc;
	#endif /* _PRIVATE_WINDOW_H_ */

	/*
	 * Create a window with the specified title, position, size, and
	 * fullscreen attribute. The window will have DirectInput associated
	 * with it.
	 * 
	 * Returns true for success, or false for failure
	 */
	WINDOW_H_API bool createWindow(const char * title, int x, int y, int width, int height, bool fullscreen);


	/*
	 * Handle native Win32 messages
	 */
	WINDOW_H_API void handleMessage(JNIEnv * env, jobject obj);


	/*
	 * Close the window
	 */
	WINDOW_H_API void closeWindow();

#endif /* _LWJGL_WINDOW_H_INCLUDED_ */
