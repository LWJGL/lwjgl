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
 * $Id: context.c -1   $
 *
 * @author elias_naue <elias_naur@users.sourceforge.net>
 * @version $Revision: -1 $
 */

#include <malloc.h>
#include "Window.h"
#include "extgl.h"
/*#include "extgl_wgl.h"*/
#include "context.h"

extern HINSTANCE dll_handle;                     // Handle to the LWJGL dll

#define _CONTEXT_PRIVATE_CLASS_NAME _T("__lwjgl_context_class_name")

/*
 * Register the LWJGL window class.
 * Returns true for success, or false for failure
 */
bool registerWindow(WNDPROC win_proc, LPCTSTR class_name)
{
	WNDCLASS windowClass;
	memset(&windowClass, 0, sizeof(windowClass));
	windowClass.style = CS_OWNDC;
	windowClass.lpfnWndProc = win_proc;
	windowClass.cbClsExtra = 0;
	windowClass.cbWndExtra = 0;
	windowClass.hInstance = dll_handle;
	windowClass.hIcon = LoadIcon(NULL, IDI_APPLICATION);
	windowClass.hCursor = LoadCursor(NULL, IDC_ARROW);
	windowClass.hbrBackground = NULL;
	windowClass.lpszMenuName = NULL;
	windowClass.lpszClassName = class_name;

	if (RegisterClass(&windowClass) == 0) {
		printfDebug("Failed to register window class\n");
		return false;
	}
	return true;
}

static LRESULT CALLBACK dummyWindowProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam) {
	return DefWindowProc(hwnd, msg, wParam, lParam);
}

/*
bool applyPixelFormat(JNIEnv *env, HDC hdc, int iPixelFormat) {
	PIXELFORMATDESCRIPTOR desc;
	if (DescribePixelFormat(hdc, iPixelFormat, sizeof(PIXELFORMATDESCRIPTOR), &desc) == 0) {
		throwFormattedException(env, "DescribePixelFormat failed (%d)", GetLastError());
		return false;
	}

	// make that the pixel format of the device context
	if (SetPixelFormat(hdc, iPixelFormat, &desc) == FALSE) {
		throwFormattedException(env, "SetPixelFormat failed (%d)", GetLastError());
		return false;
	}
	return true;
}
*/

/*
 * Close the window
 */
void closeWindow(HWND *hwnd, HDC *hdc)
{
	// Release device context
	if (*hdc != NULL && *hwnd != NULL) {
		ReleaseDC(*hwnd, *hdc);
		*hdc = NULL;
	}

	// Close the window
	if (*hwnd != NULL) {
		ShowWindow(*hwnd, SW_HIDE);
		DestroyWindow(*hwnd);
		*hwnd = NULL;
	}
}

void getWindowFlags(DWORD *windowflags_return, DWORD *exstyle_return, bool undecorated, bool child_window) {
	DWORD exstyle, windowflags;
	if (undecorated) {
		exstyle = WS_EX_APPWINDOW;
		windowflags = WS_POPUP;
	} else if (child_window) {
		exstyle = 0;
		windowflags = WS_CHILDWINDOW;
	} else {
		exstyle = WS_EX_APPWINDOW;
		windowflags = WS_OVERLAPPED | WS_BORDER | WS_CAPTION | WS_MINIMIZEBOX | WS_SYSMENU;
	}
	windowflags = windowflags | WS_CLIPCHILDREN | WS_CLIPSIBLINGS;
	*windowflags_return = windowflags;
	*exstyle_return = exstyle;
}

HWND createWindow(LPCTSTR window_class_name, int x, int y, int width, int height, bool undecorated, bool child_window, HWND parent)
{
	RECT clientSize;
	DWORD exstyle, windowflags;
	HWND new_hwnd;

	getWindowFlags(&windowflags, &exstyle, undecorated, child_window);

	clientSize.bottom = height;
	clientSize.left = 0;
	clientSize.right = width;
	clientSize.top = 0;

	AdjustWindowRectEx(
	  &clientSize,    // client-rectangle structure
	  windowflags,    // window styles
	  FALSE,       // menu-present option
	  exstyle   // extended window style
	);
	// Create the window now, using that class:
	new_hwnd = CreateWindowEx (
			exstyle,
			window_class_name,
			_T(""),
			windowflags,
			x, y, clientSize.right - clientSize.left, clientSize.bottom - clientSize.top,
			parent,
			NULL,
			dll_handle,
			NULL);

	return new_hwnd;
}

/*
static int convertToBPE(int bpp) {
	int bpe;
	switch (bpp) {
		case 0:
			bpe = 0;
			break;
		case 32:
		case 24:
			bpe = 8;
			break;
		case 16: // Fall through
		default:
			bpe = 4;
			break;
	}
	return bpe;
}
*/

/*
static int findPixelFormatARBFromBPP(JNIEnv *env, HDC hdc, WGLExtensions *extensions, jobject pixel_format, jobject pixelFormatCaps, int bpp, bool window, bool pbuffer, bool double_buffer) {
	jclass cls_pixel_format = (*env)->GetObjectClass(env, pixel_format);
	int alpha = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "alpha", "I"));
	int depth = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "depth", "I"));
	int stencil = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "stencil", "I"));
	int samples = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "samples", "I"));
	int colorSamples = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "colorSamples", "I"));
	int num_aux_buffers = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "num_aux_buffers", "I"));
	int accum_bpp = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "accum_bpp", "I"));
	int accum_alpha = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "accum_alpha", "I"));

	jboolean stereo = (*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "stereo", "Z"));
	jboolean floating_point = (*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "floating_point", "Z"));
	jboolean floating_point_packed = (*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "floating_point_packed", "Z"));
	jboolean sRGB = (*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "sRGB", "Z"));

	int pixel_type;
	int iPixelFormat;
	unsigned int num_formats_returned;
	attrib_list_t attrib_list;
	GLuint *pixelFormatCaps_ptr;
	jlong pixelFormatCapsSize;
	BOOL result;
	jlong i;
	int bpe = convertToBPE(bpp);

	if ( floating_point )
		pixel_type = WGL_TYPE_RGBA_FLOAT_ARB;
	else if ( floating_point_packed )
		pixel_type = WGL_TYPE_RGBA_UNSIGNED_FLOAT_EXT;
	else
		pixel_type = WGL_TYPE_RGBA_ARB;

	initAttribList(&attrib_list);
	if (window) {
		putAttrib(&attrib_list, WGL_DRAW_TO_WINDOW_ARB); putAttrib(&attrib_list, TRUE);
	}
	if (pbuffer) {
		putAttrib(&attrib_list, WGL_DRAW_TO_PBUFFER_ARB); putAttrib(&attrib_list, TRUE);
	}
	if (!getBooleanProperty(env, "org.lwjgl.opengl.Display.allowSoftwareOpenGL")) {
		putAttrib(&attrib_list, WGL_ACCELERATION_ARB); putAttrib(&attrib_list, WGL_FULL_ACCELERATION_ARB);
	}
	putAttrib(&attrib_list, WGL_PIXEL_TYPE_ARB); putAttrib(&attrib_list, pixel_type);
	putAttrib(&attrib_list, WGL_DOUBLE_BUFFER_ARB); putAttrib(&attrib_list, double_buffer ? TRUE : FALSE);
	putAttrib(&attrib_list, WGL_SUPPORT_OPENGL_ARB); putAttrib(&attrib_list, TRUE);
	putAttrib(&attrib_list, WGL_RED_BITS_ARB); putAttrib(&attrib_list, bpe);
	putAttrib(&attrib_list, WGL_GREEN_BITS_ARB); putAttrib(&attrib_list, bpe);
	putAttrib(&attrib_list, WGL_BLUE_BITS_ARB); putAttrib(&attrib_list, bpe);
	putAttrib(&attrib_list, WGL_ALPHA_BITS_ARB); putAttrib(&attrib_list, alpha);
	putAttrib(&attrib_list, WGL_DEPTH_BITS_ARB); putAttrib(&attrib_list, depth);
	putAttrib(&attrib_list, WGL_STENCIL_BITS_ARB); putAttrib(&attrib_list, stencil);
	// Assume caller checked extension availability
	if (samples > 0) {
		putAttrib(&attrib_list, WGL_SAMPLE_BUFFERS_ARB); putAttrib(&attrib_list, 1);
	    putAttrib(&attrib_list, WGL_SAMPLES_ARB); putAttrib(&attrib_list, samples); // WGL_COVERAGE_SAMPLES_NV if colorSamples > 0
	    if ( colorSamples > 0 ) {
	        putAttrib(&attrib_list, WGL_COLOR_SAMPLES_NV); putAttrib(&attrib_list, colorSamples);
        }
	}
	putAttrib(&attrib_list, WGL_ACCUM_BITS_ARB); putAttrib(&attrib_list, accum_bpp);
	putAttrib(&attrib_list, WGL_ACCUM_ALPHA_BITS_ARB); putAttrib(&attrib_list, accum_alpha);
	putAttrib(&attrib_list, WGL_STEREO_ARB); putAttrib(&attrib_list, stereo ? TRUE : FALSE);
	putAttrib(&attrib_list, WGL_AUX_BUFFERS_ARB); putAttrib(&attrib_list, num_aux_buffers);
	if (sRGB) {
		putAttrib(&attrib_list, WGL_FRAMEBUFFER_SRGB_CAPABLE_ARB); putAttrib(&attrib_list, TRUE);
	}

	// Assume caller checked extension availability
	if (pixelFormatCaps != NULL) {
		pixelFormatCaps_ptr = (GLuint *)(*env)->GetDirectBufferAddress(env, pixelFormatCaps);
		pixelFormatCapsSize = (*env)->GetDirectBufferCapacity(env, pixelFormatCaps);

		for (i = 0; i < pixelFormatCapsSize; i++)
			putAttrib(&attrib_list, pixelFormatCaps_ptr[i]);
	}
	putAttrib(&attrib_list, 0); putAttrib(&attrib_list, 0);
	result = extensions->wglChoosePixelFormatARB(hdc, attrib_list.attribs, NULL, 1, &iPixelFormat, &num_formats_returned);

	if (result == FALSE || num_formats_returned < 1) {
		throwFormattedException(env, "Failed to find ARB pixel format %d %d\n", result, num_formats_returned);
		return -1;
	}
	return iPixelFormat;
}

static int findPixelFormatARB(JNIEnv *env, HDC hdc, WGLExtensions *extensions, jobject pixel_format, jobject pixelFormatCaps, bool use_hdc_bpp, bool window, bool pbuffer, bool double_buffer) {
	int bpp;
	int iPixelFormat;
	int fallback_bpp = 16;
	jclass cls_pixel_format = (*env)->GetObjectClass(env, pixel_format);
	if (use_hdc_bpp) {
		bpp = GetDeviceCaps(hdc, BITSPIXEL);
		iPixelFormat = findPixelFormatARBFromBPP(env, hdc, extensions, pixel_format, pixelFormatCaps, bpp, window, pbuffer, double_buffer);
		if ((*env)->ExceptionOccurred(env)) {
			(*env)->ExceptionClear(env);
			printfDebugJava(env, "Failed to find ARB pixel format with HDC depth %d, falling back to %d\n", bpp, fallback_bpp);
			bpp = fallback_bpp;
		} else
			return iPixelFormat;
	} else
		bpp = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "bpp", "I"));
	return findPixelFormatARBFromBPP(env, hdc, extensions, pixel_format, pixelFormatCaps, bpp, window, pbuffer, double_buffer);
}
*/

/*
 * Find an appropriate pixel format
 */
 /*
static int findPixelFormatFromBPP(JNIEnv *env, HDC hdc, jobject pixel_format, int bpp, bool double_buffer)
{
	jclass cls_pixel_format = (*env)->GetObjectClass(env, pixel_format);
	int alpha = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "alpha", "I"));
	int depth = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "depth", "I"));
	int stencil = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "stencil", "I"));
	int num_aux_buffers = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "num_aux_buffers", "I"));
	int accum_bpp = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "accum_bpp", "I"));
	int accum_alpha = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "accum_alpha", "I"));
	jboolean stereo = (*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "stereo", "Z"));
	unsigned int flags = PFD_DRAW_TO_WINDOW |   // support window
		PFD_SUPPORT_OPENGL |
		(double_buffer ? PFD_DOUBLEBUFFER : 0) |
		(stereo ? PFD_STEREO : 0);
	PIXELFORMATDESCRIPTOR desc;
	int iPixelFormat;
	PIXELFORMATDESCRIPTOR pfd = {
		sizeof(PIXELFORMATDESCRIPTOR),   // size of this pfd
		1,                     // version number
		flags,         // RGBA type
		PFD_TYPE_RGBA,
		(BYTE)bpp,
		0, 0, 0, 0, 0, 0,      // color bits ignored
		(BYTE)alpha,
		0,                     // shift bit ignored
		accum_bpp + accum_alpha,                     // no accumulation buffer
		0, 0, 0, 0,            // accum bits ignored
		(BYTE)depth,
		(BYTE)stencil,
		num_aux_buffers,
		PFD_MAIN_PLANE,        // main layer
		0,                     // reserved
		0, 0, 0                // layer masks ignored
	};
	// get the best available match of pixel format for the device context
	iPixelFormat = ChoosePixelFormat(hdc, &pfd);
	if (iPixelFormat == 0) {
		throwException(env, "Failed to choose pixel format");
		return -1;
	}

	if (DescribePixelFormat(hdc, iPixelFormat, sizeof(PIXELFORMATDESCRIPTOR), &desc) == 0) {
		throwException(env, "Could not describe pixel format");
		return -1;
	}

	if (desc.cColorBits < bpp) {
		throwException(env, "Insufficient color precision");
		return -1;
	}

	if (desc.cAlphaBits < alpha) {
		throwException(env, "Insufficient alpha precision");
		return -1;
	}

	if (desc.cStencilBits < stencil) {
		throwException(env, "Insufficient stencil precision");
		return -1;
	}

	if (desc.cDepthBits < depth) {
		throwException(env, "Insufficient depth buffer precision");
		return -1;
	}

	if ((desc.dwFlags & PFD_GENERIC_FORMAT) != 0) {
		jboolean allowSoftwareOpenGL = getBooleanProperty(env, "org.lwjgl.opengl.Display.allowSoftwareOpenGL");
		// secondary check for software override
		if(!allowSoftwareOpenGL) {
			throwException(env, "Pixel format not accelerated");
			return -1;
		}
	}

	if ((desc.dwFlags & flags) != flags) {
		throwException(env, "Capabilities not supported");
		return -1;
	}
	return iPixelFormat;
}

static int findPixelFormatDefault(JNIEnv *env, HDC hdc, jobject pixel_format, bool use_hdc_bpp, bool double_buffer) {
	int bpp;
	int iPixelFormat;
	int fallback_bpp = 16;
	jclass cls_pixel_format = (*env)->GetObjectClass(env, pixel_format);
	if (use_hdc_bpp) {
		bpp = GetDeviceCaps(hdc, BITSPIXEL);
		iPixelFormat = findPixelFormatFromBPP(env, hdc, pixel_format, bpp, double_buffer);
		if ((*env)->ExceptionOccurred(env)) {
			(*env)->ExceptionClear(env);
			printfDebugJava(env, "Failed to find pixel format with HDC depth %d, falling back to %d\n", bpp, fallback_bpp);
			bpp = fallback_bpp;
		} else
			return iPixelFormat;
	} else
		bpp = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "bpp", "I"));
	return findPixelFormatFromBPP(env, hdc, pixel_format, bpp, double_buffer);
}
*/

/*
static bool validateAndGetExtensions(JNIEnv *env, WGLExtensions *extensions, HDC dummy_hdc, HGLRC dummy_hglrc, int samples, int colorSamples, bool floating_point, bool floating_point_packed, bool sRGB, jobject pixelFormatCaps) {
	if (!wglMakeCurrent(dummy_hdc, dummy_hglrc)) {
		throwException(env, "Could not bind context to dummy window");
		return false;
	}
	extgl_InitWGL(extensions);

	if (!extensions->WGL_ARB_pixel_format) {
		throwException(env, "No support for WGL_ARB_pixel_format");
		return false;
	}
	if (samples > 0 && !extensions->WGL_ARB_multisample) {
		throwException(env, "No support for WGL_ARB_multisample");
		return false;
	}
	if (colorSamples > 0 && !extensions->WGL_NV_multisample_coverage) {
	    throwException(env, "No support for WGL_NV_multisample_coverage");
		return false;
	}

	// Apparently, some drivers don't report WGL_ARB_pixel_format_float
	// even though GL_ARB_color_buffer_float and WGL_ATI_color_format_float
	// is supported.
	if (floating_point && !(extensions->WGL_ARB_pixel_format_float || extensions->WGL_ATI_pixel_format_float)) {
		throwException(env, "No support for WGL_ARB_pixel_format_float nor WGL_ATI_pixel_format_float");
		return false;
	}
	if (floating_point_packed && !(extensions->WGL_EXT_pixel_format_packed_float)) {
		throwException(env, "No support for WGL_EXT_pixel_format_packed_float");
		return false;
	}
	if (sRGB && !(extensions->WGL_ARB_framebuffer_sRGB)) {
		throwException(env, "No support for WGL_ARB_framebuffer_sRGB");
		return false;
	}
	if (pixelFormatCaps != NULL && !extensions->WGL_ARB_render_texture) {
		throwException(env, "No support for WGL_ARB_render_texture");
		return false;
	}
	return true;
}

int findPixelFormatOnDC(JNIEnv *env, HDC hdc, int origin_x, int origin_y, jobject pixel_format, jobject pixelFormatCaps, bool use_hdc_bpp, bool window, bool pbuffer, bool double_buffer) {
	HGLRC dummy_hglrc;
	HDC saved_current_hdc;
	HGLRC saved_current_hglrc;
	WGLExtensions extensions;
	HWND dummy_hwnd;
	HDC dummy_hdc;
	int pixel_format_id;
	jclass cls_pixel_format = (*env)->GetObjectClass(env, pixel_format);

	int samples = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "samples", "I"));
	int colorSamples = (int)(*env)->GetIntField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "colorSamples", "I"));
	bool floating_point = (bool)(*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "floating_point", "Z"));
	bool floating_point_packed = (bool)(*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "floating_point_packed", "Z"));
	bool sRGB = (bool)(*env)->GetBooleanField(env, pixel_format, (*env)->GetFieldID(env, cls_pixel_format, "sRGB", "Z"));
	bool use_arb_selection = samples > 0 || floating_point || floating_point_packed || sRGB || pbuffer || pixelFormatCaps != NULL;

	pixel_format_id = findPixelFormatDefault(env, hdc, pixel_format, use_hdc_bpp, double_buffer);
	if (!(*env)->ExceptionOccurred(env) && use_arb_selection) {
		dummy_hwnd = createDummyWindow(origin_x, origin_y);
		if (dummy_hwnd == NULL) {
			throwException(env, "Could not create dummy window");
			return -1;
		}
		dummy_hdc = GetDC(dummy_hwnd);
		if (!applyPixelFormat(env, dummy_hdc, pixel_format_id)) {
			closeWindow(&dummy_hwnd, &dummy_hdc);
			return -1;
		}
		dummy_hglrc = wglCreateContext(dummy_hdc);
		if (dummy_hglrc == NULL) {
			closeWindow(&dummy_hwnd, &dummy_hdc);
			throwException(env, "Failed to create OpenGL rendering context");
			return -1;
		}
		// Save the current HDC and HGLRC to avoid disruption
		saved_current_hdc = wglGetCurrentDC();
		saved_current_hglrc = wglGetCurrentContext();
		if (validateAndGetExtensions(env, &extensions, dummy_hdc, dummy_hglrc, samples, colorSamples, floating_point, floating_point_packed, sRGB, pixelFormatCaps)) {
			pixel_format_id = findPixelFormatARB(env, hdc, &extensions, pixel_format, pixelFormatCaps, use_hdc_bpp, window, pbuffer, double_buffer);
		}
		wglMakeCurrent(saved_current_hdc, saved_current_hglrc);
		wglDeleteContext(dummy_hglrc);
		closeWindow(&dummy_hwnd, &dummy_hdc);
	}
	return pixel_format_id;
}
*/

static bool registerDummyWindow() {
	static bool window_registered = false;
	if (!window_registered) {
		if (!registerWindow(dummyWindowProc, _CONTEXT_PRIVATE_CLASS_NAME)) {
			return false;
		}
		window_registered = true;
	}
	return true;
}

HWND createDummyWindow(int origin_x, int origin_y) {
	if (!registerDummyWindow())
		return NULL;
	return createWindow(_CONTEXT_PRIVATE_CLASS_NAME, origin_x, origin_y, 1, 1, false, false, NULL);
}
