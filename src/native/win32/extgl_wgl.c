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

WGLExtensions extension_flags;

/* WGL_EXT_etxension_string */

wglGetExtensionsStringEXTPROC wglGetExtensionsStringEXT = NULL;

/* WGL_ARB_extension_string */

wglGetExtensionsStringARBPROC wglGetExtensionsStringARB = NULL;

/* WGL_ARB_pbuffer */

wglCreatePbufferARBPROC wglCreatePbufferARB = NULL;
wglGetPbufferDCARBPROC wglGetPbufferDCARB = NULL;
wglReleasePbufferDCARBPROC wglReleasePbufferDCARB = NULL;
wglDestroyPbufferARBPROC wglDestroyPbufferARB = NULL;
wglQueryPbufferARBPROC wglQueryPbufferARB = NULL;

/* WGL_ARB_pixel_format */

wglGetPixelFormatAttribivARBPROC wglGetPixelFormatAttribivARB = NULL;
wglGetPixelFormatAttribfvARBPROC wglGetPixelFormatAttribfvARB = NULL;
wglChoosePixelFormatARBPROC wglChoosePixelFormatARB = NULL;

/* WGL_ARB_render_texture */

wglBindTexImageARBPROC wglBindTexImageARB = NULL;
wglReleaseTexImageARBPROC wglReleaseTexImageARB = NULL;
wglSetPbufferAttribARBPROC wglSetPbufferAttribARB = NULL;

/* WGL_EXT_swap_control */

wglSwapIntervalEXTPROC wglSwapIntervalEXT = NULL;
wglGetSwapIntervalEXTPROC wglGetSwapIntervalEXT = NULL;

/* WGL_ARB_make_current_read */

wglMakeContextCurrentARBPROC wglMakeContextCurrentARB = NULL;
wglGetCurrentReadDCARBPROC wglGetCurrentReadDCARB = NULL;

/** returns true if the extension is available */
static bool WGLQueryExtension(JNIEnv *env, const char *name)
{
	const GLubyte *extensions;

	if (wglGetExtensionsStringARB == NULL)
		if (wglGetExtensionsStringEXT == NULL)
			return false;
		else
			extensions = (GLubyte*)wglGetExtensionsStringEXT();
	else
		extensions = (GLubyte*)wglGetExtensionsStringARB(wglGetCurrentDC());
	return extgl_QueryExtension(env, extensions, name);
}

static void extgl_InitWGLARBPbuffer(JNIEnv *env)
{
	ExtFunction functions[] = {
		{"wglCreatePbufferARB", (void **)&wglCreatePbufferARB},
		{"wglGetPbufferDCARB", (void **)&wglGetPbufferDCARB},
		{"wglReleasePbufferDCARB", (void **)&wglReleasePbufferDCARB},
		{"wglDestroyPbufferARB", (void **)&wglDestroyPbufferARB},
		{"wglQueryPbufferARB", (void **)&wglQueryPbufferARB}};
	if (extension_flags.WGL_ARB_pbuffer)
		extension_flags.WGL_ARB_pbuffer = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitWGLARBPixelFormat(JNIEnv *env)
{
	ExtFunction functions[] = {
		{"wglGetPixelFormatAttribivARB", (void **)&wglGetPixelFormatAttribivARB},
		{"wglGetPixelFormatAttribfvARB", (void **)&wglGetPixelFormatAttribfvARB},
		{"wglChoosePixelFormatARB", (void **)&wglChoosePixelFormatARB}};
	if (extension_flags.WGL_ARB_pixel_format)
		extension_flags.WGL_ARB_pixel_format = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitWGLARBRenderTexture(JNIEnv *env)
{
	ExtFunction functions[] = {
		{"wglBindTexImageARB", (void **)&wglBindTexImageARB},
		{"wglReleaseTexImageARB", (void **)&wglReleaseTexImageARB},
		{"wglSetPbufferAttribARB", (void **)&wglSetPbufferAttribARB}};
	if (extension_flags.WGL_ARB_render_texture)
		extension_flags.WGL_ARB_render_texture = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitWGLEXTSwapControl(JNIEnv *env)
{
	ExtFunction functions[] = {
		{"wglSwapIntervalEXT", (void **)&wglSwapIntervalEXT},
		{"wglGetSwapIntervalEXT", (void **)&wglGetSwapIntervalEXT}};
	if (extension_flags.WGL_EXT_swap_control)
		extension_flags.WGL_EXT_swap_control = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitWGLARBMakeCurrentRead(JNIEnv *env)
{
	ExtFunction functions[] = {
		{"wglMakeContextCurrentARB", (void **)&wglMakeContextCurrentARB},
		{"wglGetCurrentReadDCARB", (void **)&wglGetCurrentReadDCARB}};
	if (extension_flags.WGL_ARB_make_current_read)
		extension_flags.WGL_ARB_make_current_read = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitSupportedWGLExtensions(JNIEnv *env)
{
	extension_flags.WGL_ARB_buffer_region = WGLQueryExtension(env, "WGL_ARB_buffer_region");
	extension_flags.WGL_ARB_make_current_read = WGLQueryExtension(env, "WGL_ARB_make_current_read");
	extension_flags.WGL_ARB_multisample = WGLQueryExtension(env, "WGL_ARB_multisample");
	extension_flags.WGL_ARB_pbuffer = WGLQueryExtension(env, "WGL_ARB_pbuffer");
	extension_flags.WGL_ARB_pixel_format = WGLQueryExtension(env, "WGL_ARB_pixel_format");
	extension_flags.WGL_ARB_render_texture = WGLQueryExtension(env, "WGL_ARB_render_texture");
	extension_flags.WGL_EXT_swap_control = WGLQueryExtension(env, "WGL_EXT_swap_control");
	extension_flags.WGL_NV_render_depth_texture = WGLQueryExtension(env, "WGL_NV_render_depth_texture");
	extension_flags.WGL_NV_render_texture_rectangle = WGLQueryExtension(env, "WGL_NV_render_texture_rectangle");
}

void extgl_InitWGL(JNIEnv *env)
{
	ExtFunction functions[] = {
		{"wglGetExtensionsStringARB", (void **)&wglGetExtensionsStringARB},
		{"wglGetExtensionsStringEXT", (void **)&wglGetExtensionsStringEXT}};
	extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
	extension_flags.WGL_ARB_extensions_string = wglGetExtensionsStringARB != NULL;
	extension_flags.WGL_EXT_extensions_string = wglGetExtensionsStringEXT != NULL;

	extgl_InitSupportedWGLExtensions(env);

	extgl_InitWGLARBMakeCurrentRead(env);
	extgl_InitWGLEXTSwapControl(env);
	extgl_InitWGLARBRenderTexture(env);
	extgl_InitWGLARBPixelFormat(env);
	extgl_InitWGLARBPbuffer(env);
	//extgl_InitWGLARBBufferRegion(env);
}
