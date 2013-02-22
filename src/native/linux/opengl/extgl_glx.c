/*
 * Copyright (c) 2002-2008 LWJGL Project
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

glXGetFBConfigsPROC lwjgl_glXGetFBConfigs = NULL;
glXChooseFBConfigPROC lwjgl_glXChooseFBConfig = NULL;
glXGetFBConfigAttribPROC lwjgl_glXGetFBConfigAttrib = NULL;
glXGetVisualFromFBConfigPROC lwjgl_glXGetVisualFromFBConfig = NULL;
glXCreateWindowPROC lwjgl_glXCreateWindow = NULL;
glXDestroyWindowPROC lwjgl_glXDestroyWindow = NULL;
glXCreatePixmapPROC lwjgl_glXCreatePixmap = NULL;
glXDestroyPixmapPROC lwjgl_glXDestroyPixmap = NULL;
glXCreatePbufferPROC lwjgl_glXCreatePbuffer = NULL;
glXDestroyPbufferPROC lwjgl_glXDestroyPbuffer = NULL;
glXQueryDrawablePROC lwjgl_glXQueryDrawable = NULL;
glXCreateNewContextPROC lwjgl_glXCreateNewContext = NULL;
glXMakeContextCurrentPROC lwjgl_glXMakeContextCurrent = NULL;
glXGetCurrentReadDrawablePROC lwjgl_glXGetCurrentReadDrawable = NULL;
glXGetCurrentDisplayPROC lwjgl_glXGetCurrentDisplay = NULL;
glXQueryContextPROC lwjgl_glXQueryContext = NULL;
glXSelectEventPROC lwjgl_glXSelectEvent = NULL;
glXGetSelectedEventPROC lwjgl_glXGetSelectedEvent = NULL;
glXChooseVisualPROC lwjgl_glXChooseVisual = NULL;
glXCopyContextPROC lwjgl_glXCopyContext = NULL;
glXCreateContextPROC lwjgl_glXCreateContext = NULL;
glXCreateGLXPixmapPROC lwjgl_glXCreateGLXPixmap = NULL;
glXDestroyContextPROC lwjgl_glXDestroyContext = NULL;
glXDestroyGLXPixmapPROC lwjgl_glXDestroyGLXPixmap = NULL;
glXGetConfigPROC lwjgl_glXGetConfig = NULL;
glXGetCurrentContextPROC lwjgl_glXGetCurrentContext = NULL;
glXGetCurrentDrawablePROC lwjgl_glXGetCurrentDrawable = NULL;
glXIsDirectPROC lwjgl_glXIsDirect = NULL;
glXMakeCurrentPROC lwjgl_glXMakeCurrent = NULL;
glXQueryExtensionPROC lwjgl_glXQueryExtension = NULL;
glXQueryVersionPROC lwjgl_glXQueryVersion = NULL;
glXSwapBuffersPROC lwjgl_glXSwapBuffers = NULL;
glXUseXFontPROC lwjgl_glXUseXFont = NULL;
glXWaitGLPROC lwjgl_glXWaitGL = NULL;
glXWaitXPROC lwjgl_glXWaitX = NULL;
glXGetClientStringPROC lwjgl_glXGetClientString = NULL;
glXQueryServerStringPROC lwjgl_glXQueryServerString = NULL;
glXQueryExtensionsStringPROC lwjgl_glXQueryExtensionsString = NULL;

/* GLX_SGI_swap_control */
glXSwapIntervalSGIPROC lwjgl_glXSwapIntervalSGI = NULL;

/* GLX_EXT_swap_control */
glXSwapIntervalEXTPROC lwjgl_glXSwapIntervalEXT = NULL;

/* GLX_ARB_create_context */
glXCreateContextAttribsARBPROC lwjgl_glXCreateContextAttribsARB = NULL;

/* GLX_NV_present_video */
glXEnumerateVideoDevicesNVPROC lwjgl_glXEnumerateVideoDevicesNV = NULL;
glXBindVideoDeviceNVPROC lwjgl_glXBindVideoDeviceNV = NULL;

/* GLX_NV_video_capture */
glXBindVideoCaptureDeviceNVPROC lwjgl_glXBindVideoCaptureDeviceNV = NULL;
glXEnumerateVideoCaptureDevicesNVPROC lwjgl_glXEnumerateVideoCaptureDevicesNV = NULL;
glXLockVideoCaptureDeviceNVPROC lwjgl_glXLockVideoCaptureDeviceNV = NULL;
glXQueryVideoCaptureDeviceNVPROC lwjgl_glXQueryVideoCaptureDeviceNV = NULL;
glXReleaseVideoCaptureDeviceNVPROC lwjgl_glXReleaseVideoCaptureDeviceNV = NULL;

static void * lib_gl_handle = NULL;

typedef void * (APIENTRY * glXGetProcAddressARBPROC) (const GLubyte *procName);

static glXGetProcAddressARBPROC lwjgl_glXGetProcAddressARB;

static GLXExtensions symbols_flags;

/** returns true if the extention is available */
static inline bool GLXQueryExtension(const GLubyte *exts, const char *name) {
	return extgl_QueryExtension(exts, name);
}

static void extgl_InitGLX13() {
	ExtFunction functions[] = {
		{"glXGetFBConfigs", (void*)&lwjgl_glXGetFBConfigs},
		{"glXChooseFBConfig", (void*)&lwjgl_glXChooseFBConfig},
		{"glXGetFBConfigAttrib", (void*)&lwjgl_glXGetFBConfigAttrib},
		{"glXGetVisualFromFBConfig", (void*)&lwjgl_glXGetVisualFromFBConfig},
		{"glXCreateWindow", (void*)&lwjgl_glXCreateWindow},
		{"glXDestroyWindow", (void*)&lwjgl_glXDestroyWindow},
		{"glXCreatePixmap", (void*)&lwjgl_glXCreatePixmap},
		{"glXDestroyPixmap", (void*)&lwjgl_glXDestroyPixmap},
		{"glXCreatePbuffer", (void*)&lwjgl_glXCreatePbuffer},
		{"glXDestroyPbuffer", (void*)&lwjgl_glXDestroyPbuffer},
		{"glXQueryDrawable", (void*)&lwjgl_glXQueryDrawable},
		{"glXCreateNewContext", (void*)&lwjgl_glXCreateNewContext},
		{"glXMakeContextCurrent", (void*)&lwjgl_glXMakeContextCurrent},
		{"glXGetCurrentReadDrawable", (void*)&lwjgl_glXGetCurrentReadDrawable},
		{"glXGetCurrentDisplay", (void*)&lwjgl_glXGetCurrentDisplay},
		{"glXQueryContext", (void*)&lwjgl_glXQueryContext},
		{"glXSelectEvent", (void*)&lwjgl_glXSelectEvent},
		{"glXGetSelectedEvent", (void*)&lwjgl_glXGetSelectedEvent}};
	symbols_flags.GLX13 = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitGLX12(void) {
	ExtFunction functions[] = {
		{"glXChooseVisual", (void*)&lwjgl_glXChooseVisual},
		{"glXCopyContext", (void*)&lwjgl_glXCopyContext},
		{"glXCreateContext", (void*)&lwjgl_glXCreateContext},
		{"glXCreateGLXPixmap", (void*)&lwjgl_glXCreateGLXPixmap},
		{"glXDestroyContext", (void*)&lwjgl_glXDestroyContext},
		{"glXDestroyGLXPixmap", (void*)&lwjgl_glXDestroyGLXPixmap},
		{"glXGetConfig", (void*)&lwjgl_glXGetConfig},
		{"glXGetCurrentContext", (void*)&lwjgl_glXGetCurrentContext},
		{"glXGetCurrentDrawable", (void*)&lwjgl_glXGetCurrentDrawable},
		{"glXIsDirect", (void*)&lwjgl_glXIsDirect},
		{"glXMakeCurrent", (void*)&lwjgl_glXMakeCurrent},
		{"glXQueryExtension", (void*)&lwjgl_glXQueryExtension},
		{"glXQueryVersion", (void*)&lwjgl_glXQueryVersion},
		{"glXSwapBuffers", (void*)&lwjgl_glXSwapBuffers},
		{"glXUseXFont", (void*)&lwjgl_glXUseXFont},
		{"glXWaitGL", (void*)&lwjgl_glXWaitGL},
		{"glXWaitX", (void*)&lwjgl_glXWaitX},
		{"glXGetClientString", (void*)&lwjgl_glXGetClientString},
		{"glXQueryServerString", (void*)&lwjgl_glXQueryServerString},
		{"glXQueryExtensionsString", (void*)&lwjgl_glXQueryExtensionsString}};
	symbols_flags.GLX12 = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitGLXSGISwapControl() {
	ExtFunction functions[] = {
		{"glXSwapIntervalSGI", (void*)&lwjgl_glXSwapIntervalSGI}};
	symbols_flags.GLX_SGI_swap_control = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitGLXEXTSwapControl() {
	ExtFunction functions[] = {
		{"glXSwapIntervalEXT", (void*)&lwjgl_glXSwapIntervalEXT}};
	symbols_flags.GLX_EXT_swap_control = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitGLXARBCreateContext() {
	ExtFunction functions[] = {
		{"glXCreateContextAttribsARB", (void*)&lwjgl_glXCreateContextAttribsARB}};
	symbols_flags.GLX_ARB_create_context = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitGLXNVPresentVideo() {
	ExtFunction functions[] = {
		{ "glXEnumerateVideoDevicesNV", (void*)&lwjgl_glXEnumerateVideoDevicesNV },
		{ "glXBindVideoDeviceNV", (void*)&lwjgl_glXBindVideoDeviceNV }
	};

	symbols_flags.GLX_NV_present_video = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitGLXNVVideoCapture() {
	ExtFunction functions[] = {
		{ "glXBindVideoCaptureDeviceNV", (void*)&lwjgl_glXBindVideoCaptureDeviceNV },
		{ "glXEnumerateVideoCaptureDevicesNV", (void*)&lwjgl_glXEnumerateVideoCaptureDevicesNV },
		{ "glXLockVideoCaptureDeviceNV", (void*)&lwjgl_glXLockVideoCaptureDeviceNV },
		{ "glXQueryVideoCaptureDeviceNV", (void*)&lwjgl_glXQueryVideoCaptureDeviceNV },
		{ "glXReleaseVideoCaptureDeviceNV", (void*)&lwjgl_glXReleaseVideoCaptureDeviceNV }
	};

	symbols_flags.GLX_NV_video_capture = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitGLXSupportedExtensions(Display *disp, int screen, GLXExtensions *extension_flags) {
	const GLubyte *exts = (const GLubyte *)lwjgl_glXQueryExtensionsString(disp, screen);

/*	extension_flags.GLX_EXT_visual_info = GLXQueryExtension(exts, "GLX_EXT_visual_info");
	extension_flags.GLX_EXT_visual_rating = GLXQueryExtension(exts, "GLX_EXT_visual_rating");*/
	extension_flags->GLX_SGI_swap_control = symbols_flags.GLX_SGI_swap_control && GLXQueryExtension(exts, "GLX_SGI_swap_control");
	extension_flags->GLX_EXT_swap_control = symbols_flags.GLX_EXT_swap_control && GLXQueryExtension(exts, "GLX_EXT_swap_control");
	extension_flags->GLX_ARB_multisample = GLXQueryExtension(exts, "GLX_ARB_multisample");
	extension_flags->GLX_ARB_fbconfig_float = GLXQueryExtension(exts, "GLX_ARB_fbconfig_float");
	extension_flags->GLX_EXT_fbconfig_packed_float = GLXQueryExtension(exts, "GLX_EXT_fbconfig_packed_float");
	extension_flags->GLX_ARB_framebuffer_sRGB = GLXQueryExtension(exts, "GLX_ARB_framebuffer_sRGB") || GLXQueryExtension(exts, "GLX_EXT_framebuffer_sRGB");
	extension_flags->GLX_ARB_create_context = GLXQueryExtension(exts, "GLX_ARB_create_context");
	extension_flags->GLX_NV_multisample_coverage = GLXQueryExtension(exts, "GLX_NV_multisample_coverage");
	extension_flags->GLX_NV_present_video = GLXQueryExtension(exts, "GLX_NV_present_video");
	extension_flags->GLX_NV_video_capture = GLXQueryExtension(exts, "GLX_NV_video_capture");
}

bool extgl_Open(JNIEnv *env) {
	if (lib_gl_handle != NULL)
		return true;
	/*
	 * Actually we don't need the RTLD_GLOBAL flag, since the symbols
	 * we load should be private to us. However, according to the
	 * documentation at
	 *
	 * http://dri.sourceforge.net/doc/DRIuserguide.html
	 *
	 * DRI drivers need this flag to work properly
	 */
	lib_gl_handle = dlopen("libGL.so.1", RTLD_LAZY | RTLD_GLOBAL);
	if (lib_gl_handle == NULL) {
		throwFormattedException(env, "Error loading libGL.so.1: %s", dlerror());
		return false;
	}
	lwjgl_glXGetProcAddressARB = (glXGetProcAddressARBPROC)dlsym(lib_gl_handle, "glXGetProcAddressARB");
	if (lwjgl_glXGetProcAddressARB == NULL) {
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
	extgl_InitGLXEXTSwapControl();
	extgl_InitGLXARBCreateContext();
	extgl_InitGLXNVPresentVideo();
	extgl_InitGLXNVVideoCapture();
	return true;
}

void *extgl_GetProcAddress(const char *name) {
	void *t = (void*)lwjgl_glXGetProcAddressARB((const GLubyte*)name);
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
	if (lwjgl_glXQueryVersion(disp, &major, &minor) != True)
		return false;
	bool glx12 = major > 1 || (major == 1 && minor >= 2);
	// Check GLX 1.2 version
	if (!glx12)
		return false;
	extension_flags->GLX12 = glx12;
	extension_flags->GLX13 = major > 1 || (major == 1 && minor >= 3);
	extension_flags->GLX14 = major > 1 || (major == 1 && minor >= 4);
	extgl_InitGLXSupportedExtensions(disp, screen, extension_flags);
	return true;
}
