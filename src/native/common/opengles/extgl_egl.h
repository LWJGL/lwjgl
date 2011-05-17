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
#ifndef _EXTGL_EGL_H
#define _EXTGL_EGL_H

#include "extgl.h"
#include "common_tools.h"

#include <EGL/eglext.h>

typedef struct {
    bool EGLKHRConfigAttribs;
    bool EGLKHRLockSurface;
    bool EGLKHRImage;
    bool EGLKHRVGParentImage;
    bool EGLKHRGLTexture2DImage;
    bool EGLKHRGLTextureCubemapImage;
    bool EGLKHRGLTexture3DImage;
    bool EGLKHRGLRenderbufferImage;
    bool EGLKHRFenceSync;
    bool EGLKHRReusableSync;
    bool EGLKHRImageBase;
    bool EGLKHRImagePixmap;
    bool EGLIMGContextPriority;
    bool EGLNVCoverageSample;
    bool EGLNVDepthNonlinear;
    bool EGLNVSync;

    PFNEGLLOCKSURFACEKHRPROC eglLockSurfaceKHR;
    PFNEGLUNLOCKSURFACEKHRPROC eglUnlockSurfaceKHR;

    PFNEGLCREATEIMAGEKHRPROC eglCreateImageKHR;
    PFNEGLDESTROYIMAGEKHRPROC eglDestroyImageKHR;

    PFNEGLCREATESYNCKHRPROC eglCreateSyncKHR;
    PFNEGLDESTROYSYNCKHRPROC eglDestroySyncKHR;
    PFNEGLCLIENTWAITSYNCKHRPROC eglClientWaitSyncKHR;
    PFNEGLSIGNALSYNCKHRPROC eglSignalSyncKHR;
    PFNEGLGETSYNCATTRIBKHRPROC eglGetSyncAttribKHR;

    PFNEGLCREATEFENCESYNCNVPROC eglCreateFenceSyncNV;
    PFNEGLDESTROYSYNCNVPROC eglDestroySyncNV;
    PFNEGLFENCENVPROC eglFenceNV;
    PFNEGLCLIENTWAITSYNCNVPROC eglClientWaitSyncNV;
    PFNEGLSIGNALSYNCNVPROC eglSignalSyncNV;
    PFNEGLGETSYNCATTRIBNVPROC eglGetSyncAttribNV;
} EGLExtensions;

extern void extgl_InitEGL(EGLDisplay dpy, EGLExtensions *extensions);

#endif
