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
 * Base Win32 functionality for GL.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#include <windows.h>
#include "org_lwjgl_opengl_BaseGL.h"
#include "extgl.h"

HGLRC			hglrc = NULL;						// OpenGL rendering context
extern HDC		hdc;
extern HWND		hwnd;

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    nCreate
 * Signature: (IIII)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_BaseGL_nCreate
  (JNIEnv * env, jobject obj, jint colorBits, jint alphaBits, jint depthBits, jint stencilBits)
{

	if (!hwnd) {
		printf("No window handle\n");
		return JNI_FALSE;
	}
	int flags = PFD_DRAW_TO_WINDOW |   // support window 
		PFD_SUPPORT_OPENGL |   // support OpenGL 
		PFD_GENERIC_ACCELERATED |
		PFD_DOUBLEBUFFER;      // double buffered 

	PIXELFORMATDESCRIPTOR pfd = { 
		sizeof(PIXELFORMATDESCRIPTOR),   // size of this pfd 
		1,                     // version number 
		flags,         // RGBA type 
		PFD_TYPE_RGBA,
		(BYTE)colorBits,       
		0, 0, 0, 0, 0, 0,      // color bits ignored 
		(BYTE)alphaBits,       
		0,                     // shift bit ignored 
		0,                     // no accumulation buffer 
		0, 0, 0, 0,            // accum bits ignored 
		(BYTE)depthBits,       
		(BYTE)stencilBits,     
		0,                     // One auxiliary buffer 
		PFD_MAIN_PLANE,        // main layer
		0,                     // reserved 
		0, 0, 0                // layer masks ignored
	};

	// Ensure desktop color depth is adequate
	int availableBitDepth = GetDeviceCaps(hdc, BITSPIXEL);
	if (availableBitDepth < colorBits) {
		printf("This application requires a greater colour depth.\n");
		return JNI_FALSE;
	};

	int  iPixelFormat;  

	// get the best available match of pixel format for the device context  
	iPixelFormat = ChoosePixelFormat(hdc, &pfd);
	if (iPixelFormat == 0) {
		printf("Failed to choose pixel format.\n");
		return JNI_FALSE;
	}

	PIXELFORMATDESCRIPTOR desc;
	if (DescribePixelFormat(hdc, iPixelFormat, sizeof(PIXELFORMATDESCRIPTOR), &desc) == 0) {
		printf("Could not describe pixel format\n");
		return JNI_FALSE;
	}

	if (desc.cColorBits < colorBits) {
		printf("This application requires a greater colour depth.\n");
		return JNI_FALSE;
	}

	if (desc.cStencilBits < stencilBits) {
		printf("This application requires a greater stencil depth.\n");
		return JNI_FALSE;
	}

	if (desc.cDepthBits < depthBits) {
		printf("This application requires a greater depth buffer depth.\n");
		return JNI_FALSE;
	}

	if ((desc.dwFlags & flags) == 0) {
		printf("Capabilities not supported.\n");
		return JNI_FALSE;
	}

#ifdef _DEBUG
	printf("Pixel format is %d\n", iPixelFormat);
#endif

	// make that the pixel format of the device context 
	if (SetPixelFormat(hdc, iPixelFormat, &pfd) == FALSE) {
		printf("Failed to set pixel format\n");
		return JNI_FALSE;
	}
	if (extgl_Open() != 0)
		return JNI_FALSE;
	// Create a rendering context
	hglrc = wglCreateContext(hdc);
	if (hglrc == NULL) {
		printf("Failed to create device context.\n");
		return JNI_FALSE;
	}

	// Automatically make it the current context
	wglMakeCurrent(hdc, hglrc);

	if (extgl_Initialize() != 0) {
		printf("Failed to initialize GL\n");
		return JNI_FALSE;
	}

#ifdef _DEBUG
	char * p = (char *) glGetString(GL_EXTENSIONS);
    if (NULL == p) {
        printf("NO extensions available\n");
    } else {
		printf("Available extensions:\n%s\n", p);
	}
#endif	
	return JNI_TRUE;
}

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_nDestroy
  (JNIEnv * env, jobject obj)
{
	wglMakeCurrent(NULL, NULL);

	// Delete the rendering context
	if (hglrc != NULL)
		wglDeleteContext(hglrc); 
	extgl_Close();
}

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    swapBuffers
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_swapBuffers
  (JNIEnv *, jobject)
{
	wglSwapLayerBuffers(hdc, WGL_SWAP_MAIN_PLANE);
}

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    nFreeContext
 * Signature: ()V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_nReleaseContext
  (JNIEnv *env, jobject obj)
{
	wglMakeCurrent(hdc, NULL);
}


/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    nMakeCurrent
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_nMakeCurrent
  (JNIEnv * env, jobject obj)
{
	wglMakeCurrent(hdc, hglrc);
}

