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
#include "extgl_egl.h"

static void extgl_InitEGLKHRLockSurface(EGLExtensions *extensions) {
	ExtFunction functions[] = {
		{ "eglLockSurfaceKHR", (void *)&extensions->eglLockSurfaceKHR },
		{ "eglUnlockSurfaceKHR", (void *)&extensions->eglUnlockSurfaceKHR }
	};
	if ( extensions->EGLKHRLockSurface )
		extensions->EGLKHRLockSurface = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitEGLKHRImageBase(EGLExtensions *extensions) {
	ExtFunction functions[] = {
		{ "eglCreateImageKHR", (void *)&extensions->eglCreateImageKHR },
		{ "eglDestroyImageKHR", (void *)&extensions->eglDestroyImageKHR }
	};
	if ( extensions->EGLKHRImageBase )
		extensions->EGLKHRImageBase = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitEGLKHRFenceSync(EGLExtensions *extensions) {
	ExtFunction functions[] = {
		{ "eglCreateSyncKHR", (void *)&extensions->eglCreateSyncKHR },
	    { "eglDestroySyncKHR", (void *)&extensions->eglDestroySyncKHR },
	    { "eglClientWaitSyncKHR", (void *)&extensions->eglClientWaitSyncKHR },
	    { "eglSignalSyncKHR", (void *)&extensions->eglSignalSyncKHR },
	    { "eglGetSyncAttribKHR", (void *)&extensions->eglGetSyncAttribKHR }
	};
	if (extensions->EGLKHRFenceSync)
		extensions->EGLKHRFenceSync = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitEGLNVSync(EGLExtensions *extensions) {
	ExtFunction functions[] = {
		{ "eglCreateFenceSyncNV", (void *)&extensions->eglCreateFenceSyncNV },
	    { "eglDestroySyncNV", (void *)&extensions->eglDestroySyncNV },
	    { "eglFenceNV", (void *)&extensions->eglFenceNV },
	    { "eglClientWaitSyncNV", (void *)&extensions->eglClientWaitSyncNV },
	    { "eglSignalSyncNV", (void *)&extensions->eglSignalSyncNV },
	    { "eglGetSyncAttribNV", (void *)&extensions->eglGetSyncAttribNV }

	};
	if (extensions->EGLNVSync)
		extensions->EGLNVSync = extgl_InitializeFunctions(sizeof(functions)/sizeof(ExtFunction), functions);
}

static void extgl_InitSupportedEGLExtensions(EGLDisplay dpy, EGLExtensions *extensions) {
    const char *extension_string = eglQueryString(dpy, EGL_EXTENSIONS);

    extensions->EGLKHRConfigAttribs = extgl_QueryExtension(extension_string, "EGL_KHR_config_attribs");
    extensions->EGLKHRLockSurface = extgl_QueryExtension(extension_string, "EGL_KHR_lock_surface");
    extensions->EGLKHRImage = extgl_QueryExtension(extension_string, "EGL_KHR_image");
    extensions->EGLKHRVGParentImage = extgl_QueryExtension(extension_string, "EGL_KHR_vg_parent_image");
    extensions->EGLKHRGLTexture2DImage = extgl_QueryExtension(extension_string, "EGL_KHR_gl_texture_2D_image");
    extensions->EGLKHRGLTextureCubemapImage = extgl_QueryExtension(extension_string, "EGL_KHR_gl_texture_cubemap_image");
    extensions->EGLKHRGLTexture3DImage = extgl_QueryExtension(extension_string, "EGL_KHR_gl_texture_3D_image");
    extensions->EGLKHRGLRenderbufferImage = extgl_QueryExtension(extension_string, "EGL_KHR_gl_renderbuffer_image");
    extensions->EGLKHRReusableSync = extgl_QueryExtension(extension_string, "EGL_KHR_reusable_sync");
    extensions->EGLKHRImageBase = extgl_QueryExtension(extension_string, "EGL_KHR_image_base");
    extensions->EGLKHRImagePixmap = extgl_QueryExtension(extension_string, "EGL_KHR_image_pixmap");
    extensions->EGLIMGContextPriority = extgl_QueryExtension(extension_string, "EGL_IMG_context_priority");
    extensions->EGLNVCoverageSample = extgl_QueryExtension(extension_string, "EGL_NV_coverage_sample");
    extensions->EGLNVDepthNonlinear = extgl_QueryExtension(extension_string, "EGL_NV_depth_nonlinear");
    extensions->EGLNVSync = extgl_QueryExtension(extension_string, "EGL_NV_sync");
    extensions->EGLKHRFenceSync = extgl_QueryExtension(extension_string, "EGL_KHR_fence_sync");
}

void extgl_InitEGL(EGLDisplay dpy, EGLExtensions *extensions) {
	extgl_InitSupportedEGLExtensions(dpy, extensions);

    extgl_InitEGLKHRLockSurface(extensions);
    extgl_InitEGLKHRImageBase(extensions);
    extgl_InitEGLKHRFenceSync(extensions);
    extgl_InitEGLNVSync(extensions);
}