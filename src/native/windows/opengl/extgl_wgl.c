/* ----------------------------------------------------------------------------
Copyright (c) 2001-2002, Lev Povalahev
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

	* Redistributions of source code must retain the above copyright notice,
	  this list of conditions and the following disclaimer.
	* Redistributions in binary form must reproduce the above copyright notice,
	  this list of conditions and the following disclaimer in the documentation
	  and/or other materials provided with the distribution.
	* The name of the author may be used to endorse or promote products
	  derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
THE POSSIBILITY OF SUCH DAMAGE.
------------------------------------------------------------------------------*/
/*
	Lev Povalahev

	levp@gmx.net

	http://www.uni-karlsruhe.de/~uli2/

*/

#include <stdio.h>
#include <string.h>
#include "extgl_wgl.h"
#include "extgl.h"

static HMODULE lib_gl_handle = NULL;

void *extgl_GetProcAddress(const char *name) {
	void *t = wglGetProcAddress(name);
	if (t == NULL)
	{
		t = GetProcAddress(lib_gl_handle, name);
		if (t == NULL)
		{
			printfDebug("Could not locate symbol %s\n", name);
		}
	}
	return t;
}

bool extgl_Open(JNIEnv *env) {
	if (lib_gl_handle != NULL)
		return true;
	// load the dynamic libraries for OpenGL
	lib_gl_handle = LoadLibrary("opengl32.dll");
	if (lib_gl_handle == NULL) {
		throwException(env, "Could not load OpenGL library");
		return false;
	}
	return true;
}

void extgl_Close(void) {
	FreeLibrary(lib_gl_handle);
	lib_gl_handle = NULL;
}

/** returns true if the extension is available */
static bool WGLQueryExtension(WGLExtensions *extensions, const char *name) {
	const GLubyte *extension_string;

	if (!extensions->WGL_ARB_extensions_string)
		if (!extensions->WGL_EXT_extensions_string)
			return false;
		else
			extension_string = (GLubyte*)extensions->wglGetExtensionsStringEXT();
	else
		extension_string = (GLubyte*)extensions->wglGetExtensionsStringARB(wglGetCurrentDC());
	return extgl_QueryExtension(extension_string, name);
}

/*---------------------------------------------------------------------*/

static void extgl_InitWGLARBPbuffer(WGLExtensions *extensions) {
	ExtFunction functions[] = {
		{"wglCreatePbufferARB", (void *)&extensions->wglCreatePbufferARB},
		{"wglGetPbufferDCARB", (void *)&extensions->wglGetPbufferDCARB},
		{"wglReleasePbufferDCARB", (void *)&extensions->wglReleasePbufferDCARB},
		{"wglDestroyPbufferARB", (void *)&extensions->wglDestroyPbufferARB},
		{"wglQueryPbufferARB", (void *)&extensions->wglQueryPbufferARB}};
	if (extensions->WGL_ARB_pbuffer)
		extensions->WGL_ARB_pbuffer = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitWGLARBPixelFormat(WGLExtensions *extensions) {
	ExtFunction functions[] = {
		{"wglGetPixelFormatAttribivARB", (void *)&extensions->wglGetPixelFormatAttribivARB},
		{"wglGetPixelFormatAttribfvARB", (void *)&extensions->wglGetPixelFormatAttribfvARB},
		{"wglChoosePixelFormatARB", (void *)&extensions->wglChoosePixelFormatARB}};
	if (extensions->WGL_ARB_pixel_format)
		extensions->WGL_ARB_pixel_format = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitWGLARBRenderTexture(WGLExtensions *extensions) {
	ExtFunction functions[] = {
		{"wglBindTexImageARB", (void *)&extensions->wglBindTexImageARB},
		{"wglReleaseTexImageARB", (void *)&extensions->wglReleaseTexImageARB},
		{"wglSetPbufferAttribARB", (void *)&extensions->wglSetPbufferAttribARB}};
	if (extensions->WGL_ARB_render_texture)
		extensions->WGL_ARB_render_texture = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitWGLEXTSwapControl(WGLExtensions *extensions) {
	ExtFunction functions[] = {
		{"wglSwapIntervalEXT", (void *)&extensions->wglSwapIntervalEXT},
		{"wglGetSwapIntervalEXT", (void *)&extensions->wglGetSwapIntervalEXT}};
	if (extensions->WGL_EXT_swap_control)
		extensions->WGL_EXT_swap_control = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitWGLARBMakeCurrentRead(WGLExtensions *extensions) {
	ExtFunction functions[] = {
		{"wglMakeContextCurrentARB", (void *)&extensions->wglMakeContextCurrentARB},
		{"wglGetCurrentReadDCARB", (void *)&extensions->wglGetCurrentReadDCARB}};
	if (extensions->WGL_ARB_make_current_read)
		extensions->WGL_ARB_make_current_read = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitWGLARBCreateContext(WGLExtensions *extensions) {
	ExtFunction functions[] = {
		{"wglCreateContextAttribsARB", (void *)&extensions->wglCreateContextAttribsARB}
	};
	if (extensions->WGL_ARB_create_context)
		extensions->WGL_ARB_create_context = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitWGLNVPresentVideo(WGLExtensions *extensions) {
	ExtFunction functions[] = {
		{"wglEnumerateVideoDevicesNV", (void *)&extensions->wglEnumerateVideoDevicesNV},
		{"wglBindVideoDeviceNV", (void *)&extensions->wglBindVideoDeviceNV},
		{"wglQueryCurrentContextNV", (void *)&extensions->wglQueryCurrentContextNV}
	};

	if (extensions->WGL_NV_present_video)
		extensions->WGL_NV_present_video = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitWGLNVVideoCapture(WGLExtensions *extensions) {
	ExtFunction functions[] = {
		{"wglBindVideoCaptureDeviceNV", (void *)&extensions->wglBindVideoCaptureDeviceNV},
		{"wglEnumerateVideoCaptureDevicesNV", (void *)&extensions->wglEnumerateVideoCaptureDevicesNV},
		{"wglLockVideoCaptureDeviceNV", (void *)&extensions->wglLockVideoCaptureDeviceNV},
		{"wglQueryVideoCaptureDeviceNV", (void *)&extensions->wglQueryVideoCaptureDeviceNV},
		{"wglReleaseVideoCaptureDeviceNV", (void *)&extensions->wglReleaseVideoCaptureDeviceNV}
	};

	if (extensions->WGL_NV_video_capture)
		extensions->WGL_NV_video_capture = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

/*---------------------------------------------------------------------*/

static void extgl_InitSupportedWGLExtensions(WGLExtensions *extensions) {
	extensions->WGL_ARB_buffer_region = WGLQueryExtension(extensions, "WGL_ARB_buffer_region");
	extensions->WGL_ARB_make_current_read = WGLQueryExtension(extensions, "WGL_ARB_make_current_read");
	extensions->WGL_ARB_multisample = WGLQueryExtension(extensions, "WGL_ARB_multisample");
	extensions->WGL_ARB_pixel_format_float = WGLQueryExtension(extensions, "WGL_ARB_pixel_format_float");
	extensions->WGL_ATI_pixel_format_float = WGLQueryExtension(extensions, "WGL_ATI_pixel_format_float");
	extensions->WGL_ARB_pbuffer = WGLQueryExtension(extensions, "WGL_ARB_pbuffer");
	extensions->WGL_ARB_pixel_format = WGLQueryExtension(extensions, "WGL_ARB_pixel_format");
	extensions->WGL_ARB_render_texture = WGLQueryExtension(extensions, "WGL_ARB_render_texture");
	extensions->WGL_EXT_swap_control = WGLQueryExtension(extensions, "WGL_EXT_swap_control");
	extensions->WGL_NV_render_depth_texture = WGLQueryExtension(extensions, "WGL_NV_render_depth_texture");
	extensions->WGL_NV_render_texture_rectangle = WGLQueryExtension(extensions, "WGL_NV_render_texture_rectangle");
	extensions->WGL_ARB_framebuffer_sRGB = WGLQueryExtension(extensions, "WGL_ARB_framebuffer_sRGB") || WGLQueryExtension(extensions, "WGL_EXT_framebuffer_sRGB");
	extensions->WGL_EXT_pixel_format_packed_float = WGLQueryExtension(extensions, "WGL_EXT_pixel_format_packed_float");
	extensions->WGL_ARB_create_context = WGLQueryExtension(extensions, "WGL_ARB_create_context");
	extensions->WGL_NV_multisample_coverage = WGLQueryExtension(extensions, "WGL_NV_multisample_coverage");
	extensions->WGL_NV_present_video = WGLQueryExtension(extensions, "WGL_NV_present_video");
	extensions->WGL_NV_video_capture = WGLQueryExtension(extensions, "WGL_NV_video_capture");
}

static void extgl_InitWGLEXTExtensionsString(WGLExtensions *extensions) {
	ExtFunction functions[] = {
		{"wglGetExtensionsStringEXT", (void *)&extensions->wglGetExtensionsStringEXT}
	};
	extensions->WGL_EXT_extensions_string = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitWGLARBExtensionsString(WGLExtensions *extensions) {
	ExtFunction functions[] = {
		{"wglGetExtensionsStringARB", (void *)&extensions->wglGetExtensionsStringARB}
	};
	extensions->WGL_ARB_extensions_string = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

void extgl_InitWGL(WGLExtensions *extensions) {
	extgl_InitWGLARBExtensionsString(extensions);
	extgl_InitWGLEXTExtensionsString(extensions);

	extgl_InitSupportedWGLExtensions(extensions);

	extgl_InitWGLARBMakeCurrentRead(extensions);
	extgl_InitWGLEXTSwapControl(extensions);
	extgl_InitWGLARBRenderTexture(extensions);
	extgl_InitWGLARBPixelFormat(extensions);
	extgl_InitWGLARBPbuffer(extensions);
	extgl_InitWGLARBCreateContext(extensions);
	extgl_InitWGLNVPresentVideo(extensions);
	extgl_InitWGLNVVideoCapture(extensions);
}
