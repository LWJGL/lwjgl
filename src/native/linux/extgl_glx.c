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
#include "extgl_glx.h"

GLXExtensions extension_flags;

glXGetFBConfigsPROC glXGetFBConfigs = NULL;
glXChooseFBConfigPROC glXChooseFBConfig = NULL;
glXGetFBConfigAttribPROC glXGetFBConfigAttrib = NULL;
glXGetVisualFromFBConfigPROC glXGetVisualFromFBConfig = NULL;
glXCreateWindowPROC glXCreateWindow = NULL;
glXDestroyWindowPROC glXDestroyWindow = NULL;
glXCreatePixmapPROC glXCreatePixmap = NULL;
glXDestroyPixmapPROC glXDestroyPixmap = NULL;
glXCreatePbufferPROC glXCreatePbuffer = NULL;
glXDestroyPbufferPROC glXDestroyPbuffer = NULL;
glXQueryDrawablePROC glXQueryDrawable = NULL;
glXCreateNewContextPROC glXCreateNewContext = NULL;
glXMakeContextCurrentPROC glXMakeContextCurrent = NULL;
glXGetCurrentReadDrawablePROC glXGetCurrentReadDrawable = NULL;
glXGetCurrentDisplayPROC glXGetCurrentDisplay = NULL;
glXQueryContextPROC glXQueryContext = NULL;
glXSelectEventPROC glXSelectEvent = NULL;
glXGetSelectedEventPROC glXGetSelectedEvent = NULL;
glXChooseVisualPROC glXChooseVisual = NULL;
glXCopyContextPROC glXCopyContext = NULL;
glXCreateContextPROC glXCreateContext = NULL;
glXCreateGLXPixmapPROC glXCreateGLXPixmap = NULL;
glXDestroyContextPROC glXDestroyContext = NULL;
glXDestroyGLXPixmapPROC glXDestroyGLXPixmap = NULL;
glXGetConfigPROC glXGetConfig = NULL;
glXGetCurrentContextPROC glXGetCurrentContext = NULL;
glXGetCurrentDrawablePROC glXGetCurrentDrawable = NULL;
glXIsDirectPROC glXIsDirect = NULL;
glXMakeCurrentPROC glXMakeCurrent = NULL;
glXQueryExtensionPROC glXQueryExtension = NULL;
glXQueryVersionPROC glXQueryVersion = NULL;
glXSwapBuffersPROC glXSwapBuffers = NULL;
glXUseXFontPROC glXUseXFont = NULL;
glXWaitGLPROC glXWaitGL = NULL;
glXWaitXPROC glXWaitX = NULL;
glXGetClientStringPROC glXGetClientString = NULL;
glXQueryServerStringPROC glXQueryServerString = NULL;
glXQueryExtensionsStringPROC glXQueryExtensionsString = NULL;

/* GLX_SGI_swap_control */
glXSwapIntervalSGIPROC glXSwapIntervalSGI = NULL;

/** returns true if the extention is available */
static bool GLXQueryExtension(JNIEnv* env, Display *disp, int screen, const char *name)
{
	const GLubyte *exts = (const GLubyte *)glXQueryExtensionsString(disp, screen);
	return extgl_QueryExtension(env, exts, name);
}

static void extgl_InitGLX13(JNIEnv *env)
{
	ExtFunction functions[] = {
		{"glXGetFBConfigs", (void*)&glXGetFBConfigs},
		{"glXChooseFBConfig", (void*)&glXChooseFBConfig},
		{"glXGetFBConfigAttrib", (void*)&glXGetFBConfigAttrib},
		{"glXGetVisualFromFBConfig", (void*)&glXGetVisualFromFBConfig},
		{"glXCreateWindow", (void*)&glXCreateWindow},
		{"glXDestroyWindow", (void*)&glXDestroyWindow},
		{"glXCreatePixmap", (void*)&glXCreatePixmap},
		{"glXDestroyPixmap", (void*)&glXDestroyPixmap},
		{"glXCreatePbuffer", (void*)&glXCreatePbuffer},
		{"glXDestroyPbuffer", (void*)&glXDestroyPbuffer},
		{"glXQueryDrawable", (void*)&glXQueryDrawable},
		{"glXCreateNewContext", (void*)&glXCreateNewContext},
		{"glXMakeContextCurrent", (void*)&glXMakeContextCurrent},
		{"glXGetCurrentReadDrawable", (void*)&glXGetCurrentReadDrawable},
		{"glXGetCurrentDisplay", (void*)&glXGetCurrentDisplay},
		{"glXQueryContext", (void*)&glXQueryContext},
		{"glXSelectEvent", (void*)&glXSelectEvent},
		{"glXGetSelectedEvent", (void*)&glXGetSelectedEvent}};
	if (extension_flags.GLX13)
		extension_flags.GLX13 = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static bool extgl_InitGLX12(void)
{
	ExtFunction functions[] = {
		{"glXChooseVisual", (void*)&glXChooseVisual},
		{"glXCopyContext", (void*)&glXCopyContext},
		{"glXCreateContext", (void*)&glXCreateContext},
		{"glXCreateGLXPixmap", (void*)&glXCreateGLXPixmap},
		{"glXDestroyContext", (void*)&glXDestroyContext},
		{"glXDestroyGLXPixmap", (void*)&glXDestroyGLXPixmap},
		{"glXGetConfig", (void*)&glXGetConfig},
		{"glXGetCurrentContext", (void*)&glXGetCurrentContext},
		{"glXGetCurrentDrawable", (void*)&glXGetCurrentDrawable},
		{"glXIsDirect", (void*)&glXIsDirect},
		{"glXMakeCurrent", (void*)&glXMakeCurrent},
		{"glXQueryExtension", (void*)&glXQueryExtension},
		{"glXQueryVersion", (void*)&glXQueryVersion},
		{"glXSwapBuffers", (void*)&glXSwapBuffers},
		{"glXUseXFont", (void*)&glXUseXFont},
		{"glXWaitGL", (void*)&glXWaitGL},
		{"glXWaitX", (void*)&glXWaitX},
		{"glXGetClientString", (void*)&glXGetClientString},
		{"glXQueryServerString", (void*)&glXQueryServerString},
		{"glXQueryExtensionsString", (void*)&glXQueryExtensionsString}};
	return extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitGLXSupportedExtensions(JNIEnv *env, Display *disp, int screen)
{
	extension_flags.GLX_EXT_visual_info = GLXQueryExtension(env, disp, screen, "GLX_EXT_visual_info");
	extension_flags.GLX_EXT_visual_rating = GLXQueryExtension(env, disp, screen, "GLX_EXT_visual_rating");
	extension_flags.GLX_SGI_swap_control = GLXQueryExtension(env, disp, screen, "GLX_SGI_swap_control");
	extension_flags.GLX_ARB_multisample = GLXQueryExtension(env, disp, screen, "GLX_ARB_multisample");
}

static void extgl_InitGLXSGISwapControl(JNIEnv *env)
{
	ExtFunction functions[] = {
		{"glXSwapIntervalSGI", (void*)&glXSwapIntervalSGI}};
	if (extension_flags.GLX_SGI_swap_control)
		extension_flags.GLX_SGI_swap_control = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

bool extgl_InitGLX(JNIEnv *env, Display *disp, int screen)
{
	int major, minor;
	/* Assume glx ver >= 1.2 */
	extension_flags.GLX12 = true;
	if (!extgl_InitGLX12())
		return false;
	extgl_InitGLXSupportedExtensions(env, disp, screen);
	if (glXQueryVersion(disp, &major, &minor) != True)
		return false;
	if (major > 1 || (major == 1 && minor >= 3))
		extension_flags.GLX13 = true;
	extgl_InitGLX13(env);
	extgl_InitGLXSGISwapControl(env);
	return true;
}
