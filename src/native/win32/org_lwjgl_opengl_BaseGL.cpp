/*
 * org_lwjgl_opengl_BaseGL.cpp
 *
 * Win32 specific library
 *
 * (C) 2002 Shaven Puppy Ltd
 *
 * Created 10 August 2002 by foo
 *
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

	PIXELFORMATDESCRIPTOR pfd = { 
		sizeof(PIXELFORMATDESCRIPTOR),   // size of this pfd 
		1,                     // version number 
		PFD_DRAW_TO_WINDOW |   // support window 
		PFD_SUPPORT_OPENGL |   // support OpenGL 
		PFD_GENERIC_ACCELERATED |
		PFD_DOUBLEBUFFER,      // double buffered 
		PFD_TYPE_RGBA,         // RGBA type 
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
		printf("This application requires a greater colour depth.");
		return JNI_FALSE;
	};

	int  iPixelFormat;  

	// get the best available match of pixel format for the device context  
	iPixelFormat = ChoosePixelFormat(hdc, &pfd);
	if (iPixelFormat == 0) {
		printf("Failed to choose pixel format.\n");
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

	_wglSetDC(hdc);
	if (!glInitialize()) {
		printf("Failed to initialize GL\n");
		return JNI_FALSE;
	}

	// Create a rendering context
	hglrc = wglCreateContext(hdc);
	if (hglrc == NULL) {
		printf("Failed to create device context.\n");
		return JNI_FALSE;
	}

	// Automatically make it the current context
	Java_org_lwjgl_opengl_BaseGL_nMakeCurrent(env, obj);

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
 * Method:    nMakeCurrent
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_nMakeCurrent
  (JNIEnv * env, jobject obj)
{
	wglMakeCurrent(hdc, hglrc);
}

