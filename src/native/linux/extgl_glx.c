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
#include <dlfcn.h>
#include "extgl_glx.h"

glXGetFBConfigsPROC _glXGetFBConfigs = NULL;
glXChooseFBConfigPROC _glXChooseFBConfig = NULL;
glXGetFBConfigAttribPROC _glXGetFBConfigAttrib = NULL;
glXGetVisualFromFBConfigPROC _glXGetVisualFromFBConfig = NULL;
glXCreateWindowPROC _glXCreateWindow = NULL;
glXDestroyWindowPROC _glXDestroyWindow = NULL;
glXCreatePixmapPROC _glXCreatePixmap = NULL;
glXDestroyPixmapPROC _glXDestroyPixmap = NULL;
glXCreatePbufferPROC _glXCreatePbuffer = NULL;
glXDestroyPbufferPROC _glXDestroyPbuffer = NULL;
glXQueryDrawablePROC _glXQueryDrawable = NULL;
glXCreateNewContextPROC _glXCreateNewContext = NULL;
glXMakeContextCurrentPROC _glXMakeContextCurrent = NULL;
glXGetCurrentReadDrawablePROC _glXGetCurrentReadDrawable = NULL;
glXGetCurrentDisplayPROC _glXGetCurrentDisplay = NULL;
glXQueryContextPROC _glXQueryContext = NULL;
glXSelectEventPROC _glXSelectEvent = NULL;
glXGetSelectedEventPROC _glXGetSelectedEvent = NULL;
glXChooseVisualPROC _glXChooseVisual = NULL;
glXCopyContextPROC _glXCopyContext = NULL;
glXCreateContextPROC _glXCreateContext = NULL;
glXCreateGLXPixmapPROC _glXCreateGLXPixmap = NULL;
glXDestroyContextPROC _glXDestroyContext = NULL;
glXDestroyGLXPixmapPROC _glXDestroyGLXPixmap = NULL;
glXGetConfigPROC _glXGetConfig = NULL;
glXGetCurrentContextPROC _glXGetCurrentContext = NULL;
glXGetCurrentDrawablePROC _glXGetCurrentDrawable = NULL;
glXIsDirectPROC _glXIsDirect = NULL;
glXMakeCurrentPROC _glXMakeCurrent = NULL;
glXQueryExtensionPROC _glXQueryExtension = NULL;
glXQueryVersionPROC _glXQueryVersion = NULL;
glXSwapBuffersPROC _glXSwapBuffers = NULL;
glXUseXFontPROC _glXUseXFont = NULL;
glXWaitGLPROC _glXWaitGL = NULL;
glXWaitXPROC _glXWaitX = NULL;
glXGetClientStringPROC _glXGetClientString = NULL;
glXQueryServerStringPROC _glXQueryServerString = NULL;
glXQueryExtensionsStringPROC _glXQueryExtensionsString = NULL;

/* GLX_SGI_swap_control */
glXSwapIntervalSGIPROC _glXSwapIntervalSGI = NULL;

static void * lib_gl_handle = NULL;

typedef void * (APIENTRY * glXGetProcAddressARBPROC) (const GLubyte *procName);

static glXGetProcAddressARBPROC _glXGetProcAddressARB;

static GLXExtensions symbols_flags;

/** returns true if the extention is available */
static bool GLXQueryExtension(Display *disp, int screen, const char *name) {
	const GLubyte *exts = (const GLubyte *)_glXQueryExtensionsString(disp, screen);
	return extgl_QueryExtension(exts, name);
}

static void extgl_InitGLX13() {
	ExtFunction functions[] = {
		{"glXGetFBConfigs", (void*)&_glXGetFBConfigs},
		{"glXChooseFBConfig", (void*)&_glXChooseFBConfig},
		{"glXGetFBConfigAttrib", (void*)&_glXGetFBConfigAttrib},
		{"glXGetVisualFromFBConfig", (void*)&_glXGetVisualFromFBConfig},
		{"glXCreateWindow", (void*)&_glXCreateWindow},
		{"glXDestroyWindow", (void*)&_glXDestroyWindow},
		{"glXCreatePixmap", (void*)&_glXCreatePixmap},
		{"glXDestroyPixmap", (void*)&_glXDestroyPixmap},
		{"glXCreatePbuffer", (void*)&_glXCreatePbuffer},
		{"glXDestroyPbuffer", (void*)&_glXDestroyPbuffer},
		{"glXQueryDrawable", (void*)&_glXQueryDrawable},
		{"glXCreateNewContext", (void*)&_glXCreateNewContext},
		{"glXMakeContextCurrent", (void*)&_glXMakeContextCurrent},
		{"glXGetCurrentReadDrawable", (void*)&_glXGetCurrentReadDrawable},
		{"glXGetCurrentDisplay", (void*)&_glXGetCurrentDisplay},
		{"glXQueryContext", (void*)&_glXQueryContext},
		{"glXSelectEvent", (void*)&_glXSelectEvent},
		{"glXGetSelectedEvent", (void*)&_glXGetSelectedEvent}};
	symbols_flags.GLX13 = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitGLX12(void) {
	ExtFunction functions[] = {
		{"glXChooseVisual", (void*)&_glXChooseVisual},
		{"glXCopyContext", (void*)&_glXCopyContext},
		{"glXCreateContext", (void*)&_glXCreateContext},
		{"glXCreateGLXPixmap", (void*)&_glXCreateGLXPixmap},
		{"glXDestroyContext", (void*)&_glXDestroyContext},
		{"glXDestroyGLXPixmap", (void*)&_glXDestroyGLXPixmap},
		{"glXGetConfig", (void*)&_glXGetConfig},
		{"glXGetCurrentContext", (void*)&_glXGetCurrentContext},
		{"glXGetCurrentDrawable", (void*)&_glXGetCurrentDrawable},
		{"glXIsDirect", (void*)&_glXIsDirect},
		{"glXMakeCurrent", (void*)&_glXMakeCurrent},
		{"glXQueryExtension", (void*)&_glXQueryExtension},
		{"glXQueryVersion", (void*)&_glXQueryVersion},
		{"glXSwapBuffers", (void*)&_glXSwapBuffers},
		{"glXUseXFont", (void*)&_glXUseXFont},
		{"glXWaitGL", (void*)&_glXWaitGL},
		{"glXWaitX", (void*)&_glXWaitX},
		{"glXGetClientString", (void*)&_glXGetClientString},
		{"glXQueryServerString", (void*)&_glXQueryServerString},
		{"glXQueryExtensionsString", (void*)&_glXQueryExtensionsString}};
	symbols_flags.GLX12 = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitGLXSGISwapControl() {
	ExtFunction functions[] = {
		{"glXSwapIntervalSGI", (void*)&_glXSwapIntervalSGI}};
	symbols_flags.GLX_SGI_swap_control = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitGLXSupportedExtensions(Display *disp, int screen, GLXExtensions *extension_flags) {
/*	extension_flags.GLX_EXT_visual_info = GLXQueryExtension(disp, screen, "GLX_EXT_visual_info");
	extension_flags.GLX_EXT_visual_rating = GLXQueryExtension(disp, screen, "GLX_EXT_visual_rating");*/
	extension_flags->GLX_SGI_swap_control = symbols_flags.GLX_SGI_swap_control && GLXQueryExtension(disp, screen, "GLX_SGI_swap_control");
	extension_flags->GLX_ARB_multisample = GLXQueryExtension(disp, screen, "GLX_ARB_multisample");
}

bool extgl_Open(JNIEnv *env) {
#define BUFFER_SIZE 2000
	static char buffer[BUFFER_SIZE];
	if (lib_gl_handle != NULL)
		return true;
	lib_gl_handle = dlopen("libGL.so.1", RTLD_LAZY);
	if (lib_gl_handle == NULL) {
		snprintf(buffer, BUFFER_SIZE, "Error loading libGL.so.1: %s", dlerror());
		buffer[BUFFER_SIZE - 1] = '\0';
		throwException(env, buffer);
		return false;
	}
	_glXGetProcAddressARB = (glXGetProcAddressARBPROC)dlsym(lib_gl_handle, "glXGetProcAddressARB");
	if (_glXGetProcAddressARB == NULL) {
		extgl_Close();
		throwException(env, "Could not get address of glXGetProcAddressARB");
		return false;
	}
	/* Unlike Windows, GLX function addresses are context-independent
	 * so we only have to initialize the addresses once at load
	 */
	extgl_InitGLX12();
	extgl_InitGLX13();
	extgl_InitGLXSGISwapControl();
	return true;
}

void *extgl_GetProcAddress(const char *name) {
	void *t = (void*)_glXGetProcAddressARB((const GLubyte*)name);
	if (t == NULL) {
		t = dlsym(lib_gl_handle, name);
		if (t == NULL) {
			printfDebug("Could not locate symbol %s\n", name);
		}
	}
	return t;
}

void extgl_Close(void) {
	dlclose(lib_gl_handle);
	lib_gl_handle = NULL;
}

bool extgl_InitGLX(Display *disp, int screen, GLXExtensions *extension_flags) {
	int major, minor;
	/* Assume glx ver >= 1.2 */
	// Check GLX 1.2 symbols available
	if (!symbols_flags.GLX12)
		return false;
	if (_glXQueryVersion(disp, &major, &minor) != True)
		return false;
	bool glx12 = major > 1 || (major == 1 && minor >= 2);
	// Check GLX 1.2 version
	if (!glx12)
		return false;
	extension_flags->GLX12 = glx12;
	extension_flags->GLX13 = major > 1 || (major == 1 && minor >= 3);
	extgl_InitGLXSupportedExtensions(disp, screen, extension_flags);
	return true;
}
