/* Small parts were taken from glext.h, here's the lisence: */

/*
** License Applicability. Except to the extent portions of this file are
** made subject to an alternative license as permitted in the SGI Free
** Software License B, Version 1.1 (the "License"), the contents of this
** file are subject only to the provisions of the License. You may not use
** this file except in compliance with the License. You may obtain a copy
** of the License at Silicon Graphics, Inc., attn: Legal Services, 1600
** Amphitheatre Parkway, Mountain View, CA 94043-1351, or at:
**
** http://oss.sgi.com/projects/FreeB
**
** Note that, as provided in the License, the Software is distributed on an
** "AS IS" basis, with ALL EXPRESS AND IMPLIED WARRANTIES AND CONDITIONS
** DISCLAIMED, INCLUDING, WITHOUT LIMITATION, ANY IMPLIED WARRANTIES AND
** CONDITIONS OF MERCHANTABILITY, SATISFACTORY QUALITY, FITNESS FOR A
** PARTICULAR PURPOSE, AND NON-INFRINGEMENT.
**
** Original Code. The Original Code is: OpenGL Sample Implementation,
** Version 1.2.1, released January 26, 2000, developed by Silicon Graphics,
** Inc. The Original Code is Copyright (c) 1991-2000 Silicon Graphics, Inc.
** Copyright in any portions created by third parties is as indicated
** elsewhere herein. All Rights Reserved.
**
** Additional Notice Provisions: This software was created using the
** OpenGL(R) version 1.2.1 Sample Implementation published by SGI, but has
** not been independently verified as being compliant with the OpenGL(R)
** version 1.2.1 Specification.
*/

/*  Most parts copyright (c) 2001-2002 Lev Povalahev under this lisence: */

/* ----------------------------------------------------------------------------
Copyright (c) 2002, Lev Povalahev
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
    GL_draw_range_elements support added by Benjamin Karaban

    Lev Povalahev contact information:

    levp@gmx.net

    http://www.uni-karlsruhe.de/~uli2/
*/

#ifndef __EXTGL_H__
#define __EXTGL_H__

#include <jni.h>


/*-----------------------------------------*/
/*-----------------------------------------*/

#if defined(_WIN32) && !defined(APIENTRY)
#define WIN32_LEAN_AND_MEAN 1
#include <windows.h>

#endif

#define __glext_h_
#define __GLEXT_H_
#define __gl_h_
#define __GL_H__

#include <string.h>

#ifndef APIENTRY
#define APIENTRY
#endif

/* for mingw compatibility */
typedef void (*_GLfuncptr)();

#define GLAPI extern
#define GLAPIENTRY

#include "common_tools.h"

#ifdef __cplusplus
extern "C" {
#endif

/* OpenGL 1.1 definition */

#ifdef _MACOSX
typedef unsigned long GLenum;
typedef unsigned char GLboolean;
typedef unsigned long GLbitfield;
typedef signed char GLbyte;
typedef short GLshort;
typedef long GLint;
typedef long GLsizei;
typedef unsigned char GLubyte;
typedef unsigned short GLushort;
typedef unsigned long GLuint;
typedef float GLfloat;
typedef float GLclampf;
typedef double GLdouble;
typedef double GLclampd;
typedef void GLvoid;
#else
typedef unsigned int GLenum;
typedef unsigned char GLboolean;
typedef unsigned int GLbitfield;
typedef signed char GLbyte;
typedef short GLshort;
typedef int GLint;
typedef int GLsizei;
typedef unsigned char GLubyte;
typedef unsigned short GLushort;
typedef unsigned int GLuint;
typedef float GLfloat;
typedef float GLclampf;
typedef double GLdouble;
typedef double GLclampd;
typedef void GLvoid;
#endif

// OpenGL 2.0 types
typedef int GLintptr;
typedef unsigned int GLsizeiptr;
typedef unsigned char GLchar;

// ARB_shader_objects types
typedef unsigned int GLhandleARB;
// ARB_vertex_buffer_object types
typedef int GLintptrARB;
typedef unsigned int GLsizeiptrARB;
typedef unsigned char GLcharARB;

// NV_half_float types
typedef unsigned short GLhalf;

/* helper stuff */

#ifndef _MACOSX
struct ExtensionTypes
{
#ifdef _WIN32 /* WGL extensions */
    bool WGL_ARB_buffer_region;
    bool WGL_ARB_extensions_string;
    bool WGL_ARB_make_current_read;
    bool WGL_ARB_multisample;
    bool WGL_ARB_pbuffer;
    bool WGL_ARB_pixel_format;
    bool WGL_ARB_render_texture;
    bool WGL_EXT_extensions_string;
    bool WGL_EXT_swap_control;
    bool WGL_NV_render_depth_texture;
    bool WGL_NV_render_texture_rectangle;
#endif /* WIN32 */
#ifdef _X11
    bool GLX12;
    bool GLX13;
    bool GLX_EXT_visual_info;
    bool GLX_EXT_visual_rating;
    bool GLX_SGI_swap_control;
    bool GLX_ARB_multisample;
#endif /* X11 */
};

extern struct ExtensionTypes extgl_Extensions;
#endif

/* initializes everything, call this right after the rc is created. the function returns true if successful */
extern bool extgl_Open(JNIEnv *env);
extern void extgl_Close(void);
extern void extgl_InitializeClass(JNIEnv *env, jclass clazz, int num_functions, JavaMethodAndExtFunction *functions);
extern bool extgl_InitializeFunctions(int num_functions, ExtFunction *functions);
extern bool extgl_QueryExtension(JNIEnv *env, const GLubyte*extensions, const char *name);

#ifdef __cplusplus
}
#endif

#endif /* __EXTGL_H__ */
