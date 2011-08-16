/*
 * Copyright (c) 2002-2011 LWJGL Project
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
 * $Id: context.h -1   $
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision: -1 $
 */

#ifndef __LWJGL_CONTEXT_H
#define __LWJGL_CONTEXT_H

#include "Window.h"
#include "extgl.h"

typedef struct {
	union {
		HWND hwnd;
	} u;
	HDC drawable_hdc;
} WindowsPeerInfo;

/*
 * Register the LWJGL window class.
 * Returns true for success, or false for failure
 */
extern bool registerWindow();

//extern bool applyPixelFormat(JNIEnv *env, HDC hdc, int iPixelFormat);

/*
 * Close the window
 */
extern void closeWindow(HWND *hwnd, HDC *hdc);

/**
 * Create a dummy window suitable to create contexts from
 */
extern HWND createDummyWindow(int x, int y);

/**
 * Return appropriate window and extended style flags from the given fullscreen and undecorated property
 */
extern void getWindowFlags(DWORD *windowflags_return, DWORD *exstyle_return, bool undecorated, bool child_window);

/*
 * Create a window with the specified position, size, and
 * fullscreen attribute. The window will have DirectInput associated
 * with it.
 *
 * Returns true for success, or false for failure
 */
extern HWND createWindow(LPCTSTR window_class_name, int x, int y, int width, int height, bool undecorated, bool child_window, HWND parent);

//extern int findPixelFormatOnDC(JNIEnv *env, HDC hdc, int origin_x, int origin_y, jobject pixel_format, jobject pixelFormatCaps, bool use_hdc_bpp, bool window, bool pbuffer, bool double_buffer);

#endif
