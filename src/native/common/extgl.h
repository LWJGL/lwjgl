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

#ifdef _AGL
#include <Carbon/Carbon.h>
#include <OpenGL/gliContext.h>
#include <OpenGL/gliDispatch.h>
#include <AGL/aglContext.h>

typedef struct __AGLRendererInfoRec  *AGLRendererInfo;
typedef struct __AGLPixelFormatRec   *AGLPixelFormat;
typedef struct __AGLContextRec       *AGLContext;

typedef GDHandle AGLDevice;
typedef CGrafPtr AGLDrawable;

/*OSStatus aglInitEntryPoints(void);
void aglDellocEntryPoints (void);
void * aglGetProcAddress (char * pszProc);
*/
typedef AGLPixelFormat (APIENTRY * aglChoosePixelFormatPROC)(const AGLDevice *gdevs, GLint ndev, const GLint *attribs);
typedef void (APIENTRY * aglDestroyPixelFormatPROC)(AGLPixelFormat pix);
typedef AGLPixelFormat (APIENTRY * aglNextPixelFormatPROC)(AGLPixelFormat pix);
typedef GLboolean (APIENTRY * aglDescribePixelFormatPROC)(AGLPixelFormat pix, GLint attrib, GLint *value);
typedef AGLDevice * (APIENTRY * aglDevicesOfPixelFormatPROC)(AGLPixelFormat pix, GLint *ndevs);
typedef AGLRendererInfo (APIENTRY * aglQueryRendererInfoPROC)(const AGLDevice *gdevs, GLint ndev);
typedef void (APIENTRY * aglDestroyRendererInfoPROC)(AGLRendererInfo rend);
typedef AGLRendererInfo (APIENTRY * aglNextRendererInfoPROC)(AGLRendererInfo rend);
typedef GLboolean (APIENTRY * aglDescribeRendererPROC)(AGLRendererInfo rend, GLint prop, GLint *value);
typedef AGLContext (APIENTRY * aglCreateContextPROC)(AGLPixelFormat pix, AGLContext share);
typedef GLboolean (APIENTRY * aglDestroyContextPROC)(AGLContext ctx);
typedef GLboolean (APIENTRY * aglCopyContextPROC)(AGLContext src, AGLContext dst, GLuint mask);
typedef GLboolean (APIENTRY * aglUpdateContextPROC)(AGLContext ctx);
typedef GLboolean (APIENTRY * aglSetCurrentContextPROC)(AGLContext ctx);
typedef AGLContext (APIENTRY * aglGetCurrentContextPROC)(void);
typedef GLboolean (APIENTRY * aglSetDrawablePROC)(AGLContext ctx, AGLDrawable draw);
typedef GLboolean (APIENTRY * aglSetOffScreenPROC)(AGLContext ctx, GLsizei width, GLsizei height, GLsizei rowbytes, GLvoid *baseaddr);
typedef GLboolean (APIENTRY * aglSetFullScreenPROC)(AGLContext ctx, GLsizei width, GLsizei height, GLsizei freq, GLint device);
typedef AGLDrawable (APIENTRY * aglGetDrawablePROC)(AGLContext ctx);
typedef GLboolean (APIENTRY * aglSetVirtualScreenPROC)(AGLContext ctx, GLint screen);
typedef GLint (APIENTRY * aglGetVirtualScreenPROC)(AGLContext ctx);
typedef void (APIENTRY * aglGetVersionPROC)(GLint *major, GLint *minor);
typedef void (APIENTRY * aglSwapBuffersPROC)(AGLContext ctx);
typedef GLboolean (APIENTRY * aglEnablePROC)(AGLContext ctx, GLenum pname);
typedef GLboolean (APIENTRY * aglDisablePROC)(AGLContext ctx, GLenum pname);
typedef GLboolean (APIENTRY * aglIsEnabledPROC)(AGLContext ctx, GLenum pname);
typedef GLboolean (APIENTRY * aglSetIntegerPROC)(AGLContext ctx, GLenum pname, const GLint *params);
typedef GLboolean (APIENTRY * aglGetIntegerPROC)(AGLContext ctx, GLenum pname, GLint *params);
typedef GLboolean (APIENTRY * aglUseFontPROC)(AGLContext ctx, GLint fontID, Style face, GLint size, GLint first, GLint count, GLint base);
typedef GLenum (APIENTRY * aglGetErrorPROC)(void);
typedef const GLubyte * (APIENTRY * aglErrorStringPROC)(GLenum code);
typedef void (APIENTRY * aglResetLibraryPROC)(void);
typedef void (APIENTRY * aglSurfaceTexturePROC) (AGLContext context, GLenum target, GLenum internalformat, AGLContext surfacecontext);

extern aglChoosePixelFormatPROC aglChoosePixelFormat;
extern aglDestroyPixelFormatPROC aglDestroyPixelFormat;
extern aglNextPixelFormatPROC aglNextPixelFormat;
extern aglDescribePixelFormatPROC aglDescribePixelFormat;
extern aglDevicesOfPixelFormatPROC aglDevicesOfPixelFormat;
extern aglQueryRendererInfoPROC aglQueryRendererInfo;
extern aglDestroyRendererInfoPROC aglDestroyRendererInfo;
extern aglNextRendererInfoPROC aglNextRendererInfo;
extern aglDescribeRendererPROC aglDescribeRenderer;
extern aglCreateContextPROC aglCreateContext;
extern aglDestroyContextPROC aglDestroyContext;
extern aglCopyContextPROC aglCopyContext;
extern aglUpdateContextPROC aglUpdateContext;
extern aglSetCurrentContextPROC aglSetCurrentContext;
extern aglGetCurrentContextPROC aglGetCurrentContext;
extern aglSetDrawablePROC aglSetDrawable;
extern aglSetOffScreenPROC aglSetOffScreen;
extern aglSetFullScreenPROC aglSetFullScreen;
extern aglGetDrawablePROC aglGetDrawable;
extern aglSetVirtualScreenPROC aglSetVirtualScreen;
extern aglGetVirtualScreenPROC aglGetVirtualScreen;
extern aglGetVersionPROC aglGetVersion;
extern aglSwapBuffersPROC aglSwapBuffers;
extern aglEnablePROC aglEnable;
extern aglDisablePROC aglDisable;
extern aglIsEnabledPROC aglIsEnabled;
extern aglSetIntegerPROC aglSetInteger;
extern aglGetIntegerPROC aglGetInteger;
extern aglUseFontPROC aglUseFont;
extern aglGetErrorPROC aglGetError;
extern aglErrorStringPROC aglErrorString;
extern aglResetLibraryPROC aglResetLibrary;
extern aglSurfaceTexturePROC aglSurfaceTexture;


/************************************************************************/

/*
 ** Attribute names for aglChoosePixelFormat and aglDescribePixelFormat.
 */
#define AGL_NONE                   0
#define AGL_ALL_RENDERERS          1  /* choose from all available renderers          */
#define AGL_BUFFER_SIZE            2  /* depth of the index buffer                    */
#define AGL_LEVEL                  3  /* level in plane stacking                      */
#define AGL_RGBA                   4  /* choose an RGBA format                        */
#define AGL_DOUBLEBUFFER           5  /* double buffering supported                   */
#define AGL_STEREO                 6  /* stereo buffering supported                   */
#define AGL_AUX_BUFFERS            7  /* number of aux buffers                        */
#define AGL_RED_SIZE               8  /* number of red component bits                 */
#define AGL_GREEN_SIZE             9  /* number of green component bits               */
#define AGL_BLUE_SIZE             10  /* number of blue component bits                */
#define AGL_ALPHA_SIZE            11  /* number of alpha component bits               */
#define AGL_DEPTH_SIZE            12  /* number of depth bits                         */
#define AGL_STENCIL_SIZE          13  /* number of stencil bits                       */
#define AGL_ACCUM_RED_SIZE        14  /* number of red accum bits                     */
#define AGL_ACCUM_GREEN_SIZE      15  /* number of green accum bits                   */
#define AGL_ACCUM_BLUE_SIZE       16  /* number of blue accum bits                    */
#define AGL_ACCUM_ALPHA_SIZE      17  /* number of alpha accum bits                   */

/*
 ** Extended attributes
 */
#define AGL_PIXEL_SIZE            50  /* frame buffer bits per pixel                  */
#define AGL_MINIMUM_POLICY        51  /* never choose smaller buffers than requested  */
#define AGL_MAXIMUM_POLICY        52  /* choose largest buffers of type requested     */
#define AGL_OFFSCREEN             53  /* choose an off-screen capable renderer        */
#define AGL_FULLSCREEN            54  /* choose a full-screen capable renderer        */
#define AGL_SAMPLE_BUFFERS_ARB    55  /* number of multi sample buffers               */
#define AGL_SAMPLES_ARB	          56  /* number of samples per multi sample buffer    */
#define AGL_AUX_DEPTH_STENCIL	  57  /* independent depth and/or stencil buffers for the aux buffer */

/* Renderer management */
#define AGL_RENDERER_ID           70  /* request renderer by ID                       */
#define AGL_SINGLE_RENDERER       71  /* choose a single renderer for all screens     */
#define AGL_NO_RECOVERY           72  /* disable all failure recovery systems         */
#define AGL_ACCELERATED           73  /* choose a hardware accelerated renderer       */
#define AGL_CLOSEST_POLICY        74  /* choose the closest color buffer to request   */
#define AGL_ROBUST                75  /* renderer does not need failure recovery      */
#define AGL_BACKING_STORE         76  /* back buffer contents are valid after swap    */
#define AGL_MP_SAFE               78  /* renderer is multi-processor safe             */

/*
 ** Only for aglDescribePixelFormat
 */
#define AGL_WINDOW                80  /* can be used to render to an onscreen window  */
#define AGL_MULTISCREEN           81  /* single window can span multiple screens      */
#define AGL_VIRTUAL_SCREEN        82  /* virtual screen number                        */
#define AGL_COMPLIANT             83  /* renderer is opengl compliant                 */

/*
 ** Property names for aglDescribeRenderer
 */
/* #define AGL_OFFSCREEN          53 */
/* #define AGL_FULLSCREEN         54 */
/* #define AGL_RENDERER_ID        70 */
/* #define AGL_ACCELERATED        73 */
/* #define AGL_ROBUST             75 */
/* #define AGL_BACKING_STORE      76 */
/* #define AGL_MP_SAFE            78 */
/* #define AGL_WINDOW             80 */
/* #define AGL_MULTISCREEN        81 */
/* #define AGL_COMPLIANT          83 */
#define AGL_BUFFER_MODES         100
#define AGL_MIN_LEVEL            101
#define AGL_MAX_LEVEL            102
#define AGL_COLOR_MODES          103
#define AGL_ACCUM_MODES          104
#define AGL_DEPTH_MODES          105
#define AGL_STENCIL_MODES        106
#define AGL_MAX_AUX_BUFFERS      107
#define AGL_VIDEO_MEMORY         120
#define AGL_TEXTURE_MEMORY       121

/*
 ** Integer parameter names
 */
#define AGL_SWAP_RECT	         200  /* Enable or set the swap rectangle              */
#define AGL_BUFFER_RECT          202  /* Enable or set the buffer rectangle            */
#define AGL_SWAP_LIMIT           203  /* Enable or disable the swap async limit        */
#define AGL_COLORMAP_TRACKING    210  /* Enable or disable colormap tracking           */
#define AGL_COLORMAP_ENTRY       212  /* Set a colormap entry to {index, r, g, b}      */
#define AGL_RASTERIZATION        220  /* Enable or disable all rasterization           */
#define AGL_SWAP_INTERVAL        222  /* 0 -> Don't sync, n -> Sync every n retrace    */
#define AGL_STATE_VALIDATION     230  /* Validate state for multi-screen functionality */
#define AGL_BUFFER_NAME          231  /* Set the buffer name. Allows for multi ctx to share a buffer */
#define AGL_ORDER_CONTEXT_TO_FRONT  232  /* Order the current context in front of all the other contexts. */
#define AGL_CONTEXT_SURFACE_ID   233  /* aglGetInteger only - returns the ID of the drawable surface for the context */
#define AGL_CONTEXT_DISPLAY_ID   234  /* aglGetInteger only - returns the display ID(s) of all displays touched by the context, up to a maximum of 32 displays */
#define AGL_SURFACE_ORDER        235  /* Position of OpenGL surface relative to window: 1 -> Above window, -1 -> Below Window */
#define AGL_SURFACE_OPACITY      236  /* Opacity of OpenGL surface: 1 -> Surface is opaque (default), 0 -> non-opaque */
#define AGL_CLIP_REGION          254  /* Enable or set the drawable clipping region */
#define AGL_FS_CAPTURE_SINGLE    255  /* Enable the capture of only a single display for aglFullScreen, normally disabled */

/*
 ** Option names for aglConfigure.
 */
#define AGL_FORMAT_CACHE_SIZE    501  /* Set the size of the pixel format cache        */
#define AGL_CLEAR_FORMAT_CACHE   502  /* Reset the pixel format cache                  */
#define AGL_RETAIN_RENDERERS     503  /* Whether to retain loaded renderers in memory  */

/* buffer_modes */
#define AGL_MONOSCOPIC_BIT       0x00000001
#define AGL_STEREOSCOPIC_BIT     0x00000002
#define AGL_SINGLEBUFFER_BIT     0x00000004
#define AGL_DOUBLEBUFFER_BIT     0x00000008

/* bit depths */
#define AGL_0_BIT                0x00000001
#define AGL_1_BIT                0x00000002
#define AGL_2_BIT                0x00000004
#define AGL_3_BIT                0x00000008
#define AGL_4_BIT                0x00000010
#define AGL_5_BIT                0x00000020
#define AGL_6_BIT                0x00000040
#define AGL_8_BIT                0x00000080
#define AGL_10_BIT               0x00000100
#define AGL_12_BIT               0x00000200
#define AGL_16_BIT               0x00000400
#define AGL_24_BIT               0x00000800
#define AGL_32_BIT               0x00001000
#define AGL_48_BIT               0x00002000
#define AGL_64_BIT               0x00004000
#define AGL_96_BIT               0x00008000
#define AGL_128_BIT              0x00010000

/* color modes */
#define AGL_RGB8_BIT             0x00000001  /* 8 rgb bit/pixel,     RGB=7:0, inverse colormap         */
#define AGL_RGB8_A8_BIT          0x00000002  /* 8-8 argb bit/pixel,  A=7:0, RGB=7:0, inverse colormap  */
#define AGL_BGR233_BIT           0x00000004  /* 8 rgb bit/pixel,     B=7:6, G=5:3, R=2:0               */
#define AGL_BGR233_A8_BIT        0x00000008  /* 8-8 argb bit/pixel,  A=7:0, B=7:6, G=5:3, R=2:0        */
#define AGL_RGB332_BIT           0x00000010  /* 8 rgb bit/pixel,     R=7:5, G=4:2, B=1:0               */
#define AGL_RGB332_A8_BIT        0x00000020  /* 8-8 argb bit/pixel,  A=7:0, R=7:5, G=4:2, B=1:0        */
#define AGL_RGB444_BIT           0x00000040  /* 16 rgb bit/pixel,    R=11:8, G=7:4, B=3:0              */
#define AGL_ARGB4444_BIT         0x00000080  /* 16 argb bit/pixel,   A=15:12, R=11:8, G=7:4, B=3:0     */
#define AGL_RGB444_A8_BIT        0x00000100  /* 8-16 argb bit/pixel, A=7:0, R=11:8, G=7:4, B=3:0       */
#define AGL_RGB555_BIT           0x00000200  /* 16 rgb bit/pixel,    R=14:10, G=9:5, B=4:0             */
#define AGL_ARGB1555_BIT         0x00000400  /* 16 argb bit/pixel,   A=15, R=14:10, G=9:5, B=4:0       */
#define AGL_RGB555_A8_BIT        0x00000800  /* 8-16 argb bit/pixel, A=7:0, R=14:10, G=9:5, B=4:0      */
#define AGL_RGB565_BIT           0x00001000  /* 16 rgb bit/pixel,    R=15:11, G=10:5, B=4:0            */
#define AGL_RGB565_A8_BIT        0x00002000  /* 8-16 argb bit/pixel, A=7:0, R=15:11, G=10:5, B=4:0     */
#define AGL_RGB888_BIT           0x00004000  /* 32 rgb bit/pixel,    R=23:16, G=15:8, B=7:0            */
#define AGL_ARGB8888_BIT         0x00008000  /* 32 argb bit/pixel,   A=31:24, R=23:16, G=15:8, B=7:0   */
#define AGL_RGB888_A8_BIT        0x00010000  /* 8-32 argb bit/pixel, A=7:0, R=23:16, G=15:8, B=7:0     */
#define AGL_RGB101010_BIT        0x00020000  /* 32 rgb bit/pixel,    R=29:20, G=19:10, B=9:0           */
#define AGL_ARGB2101010_BIT      0x00040000  /* 32 argb bit/pixel,   A=31:30  R=29:20, G=19:10, B=9:0  */
#define AGL_RGB101010_A8_BIT     0x00080000  /* 8-32 argb bit/pixel, A=7:0  R=29:20, G=19:10, B=9:0    */
#define AGL_RGB121212_BIT        0x00100000  /* 48 rgb bit/pixel,    R=35:24, G=23:12, B=11:0          */
#define AGL_ARGB12121212_BIT     0x00200000  /* 48 argb bit/pixel,   A=47:36, R=35:24, G=23:12, B=11:0 */
#define AGL_RGB161616_BIT        0x00400000  /* 64 rgb bit/pixel,    R=47:32, G=31:16, B=15:0          */
#define AGL_ARGB16161616_BIT     0x00800000  /* 64 argb bit/pixel,   A=63:48, R=47:32, G=31:16, B=15:0 */
#define AGL_INDEX8_BIT           0x20000000  /* 8 bit color look up table                              */
#define AGL_INDEX16_BIT          0x40000000  /* 16 bit color look up table                             */

/*
 ** Error return values from aglGetError.
 */
#define AGL_NO_ERROR                 0 /* no error                        */

#define AGL_BAD_ATTRIBUTE        10000 /* invalid pixel format attribute  */
#define AGL_BAD_PROPERTY         10001 /* invalid renderer property       */
#define AGL_BAD_PIXELFMT         10002 /* invalid pixel format            */
#define AGL_BAD_RENDINFO         10003 /* invalid renderer info           */
#define AGL_BAD_CONTEXT          10004 /* invalid context                 */
#define AGL_BAD_DRAWABLE         10005 /* invalid drawable                */
#define AGL_BAD_GDEV             10006 /* invalid graphics device         */
#define AGL_BAD_STATE            10007 /* invalid context state           */
#define AGL_BAD_VALUE            10008 /* invalid numerical value         */
#define AGL_BAD_MATCH            10009 /* invalid share context           */
#define AGL_BAD_ENUM             10010 /* invalid enumerant               */
#define AGL_BAD_OFFSCREEN        10011 /* invalid offscreen drawable      */
#define AGL_BAD_FULLSCREEN       10012 /* invalid offscreen drawable      */
#define AGL_BAD_WINDOW           10013 /* invalid window                  */
#define AGL_BAD_POINTER          10014 /* invalid pointer                 */
#define AGL_BAD_MODULE           10015 /* invalid code module             */
#define AGL_BAD_ALLOC            10016 /* memory allocation failure       */

/************************************************************************/

#endif /* _AGL */

#define GL_VERSION                                              0x1F02
#define GL_EXTENSIONS                                           0x1F03

/* helper stuff */

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

typedef GLenum (APIENTRY * glGetErrorPROC) (void);
typedef const GLubyte * (APIENTRY * glGetStringPROC) (GLenum name);

extern glGetErrorPROC glGetError;
extern glGetStringPROC glGetString;

/* initializes everything, call this right after the rc is created. the function returns 0 if successful */
extern bool extgl_Open(void);
#ifdef _AGL
extern bool extgl_InitAGL(JNIEnv *env);
#endif
extern void extgl_Close(void);
extern void extgl_InitializeClass(JNIEnv *env, jclass clazz, int num_functions, JavaMethodAndExtFunction *functions);
extern bool extgl_InitializeFunctions(int num_functions, ExtFunction *functions);
extern bool extgl_QueryExtension(JNIEnv *env, const GLubyte*extensions, const char *name);

#ifdef __cplusplus
}
#endif

#endif /* __EXTGL_H__ */
