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
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#ifndef _LWJGL_WINDOW_H_INCLUDED_
	#define _LWJGL_WINDOW_H_INCLUDED_

	#include <jni.h>
	#include <X11/X.h>
	#include <X11/Xlib.h>
	#include <X11/Xutil.h>
	#include "extgl.h"
        #include "extgl_glx.h"

	/*
	 * update input grabbing(keyboard, mouse)
	 */
	extern void updateInput(void);

	/*
	 * release input (keyboard, mouse)
	 */
	extern bool releaseInput(void);

	/*
	 * Various functions to release/acquire keyboard and mouse
	 */
	extern void handlePointerMotion(XMotionEvent *);
	extern void handleButtonPress(XButtonEvent *);
	extern void handleButtonRelease(XButtonEvent *);
	extern void handleKeyEvent(XKeyEvent *);
	extern void releaseKeyboard(void);
	extern void releasePointer(void);
	extern void acquireKeyboard(void);
	extern void acquirePointer(void);

	/*
	 * get the current window width
	 */
	extern int getWindowWidth(void);
	
	/*
	 * get the current window height
	 */
	extern int getWindowHeight(void);
	
	/*
	 * get the current display
	 */
	extern Display *getCurrentDisplay(void);
	
	/*
	 * get the current screen
	 */
	extern int getCurrentScreen(void);
	
	/*
	 * get the current window
	 */
	extern Window getCurrentWindow(void);

	/*
	 * Return true if a native cursor is active
	 */
	extern bool isNativeCursor(void);

	/*
	 * Return true if we are in fullscreen mode
	 */
	extern bool isFullscreen(void);

	/*
	 * convert bit-per-pixel to bits-per-element
	 */
	extern int convertToBPE(int bpp);

	/*
	 * Make the window context current
	 */
	void makeCurrent(void);

	/*
	 * Return the current OpenGL window context
	 */
	GLXContext getCurrentContext(void);

#endif /* _LWJGL_WINDOW_H_INCLUDED_ */
