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

/*-------------------------------------------------------------------*/
/*------------WGL EXTENSIONS HERE------------------------------------*/
/*-------------------------------------------------------------------*/

#ifdef _WIN32

/*-------------------------------------------------------------------*/
/*------------WGL_EXT_EXTENSION_STRING-------------------------------*/
/*-------------------------------------------------------------------*/

typedef const char* (APIENTRY * wglGetExtensionsStringEXTPROC) ();

extern wglGetExtensionsStringEXTPROC wglGetExtensionsStringEXT;

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_EXTENSION_STRING-------------------------------*/
/*-------------------------------------------------------------------*/

typedef const char* (APIENTRY * wglGetExtensionsStringARBPROC) (HDC hdc);

extern wglGetExtensionsStringARBPROC wglGetExtensionsStringARB;

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_PBUFFER----------------------------------------*/
/*-------------------------------------------------------------------*/

#define WGL_DRAW_TO_PBUFFER_ARB                                 0x202D
#define WGL_DRAW_TO_PBUFFER_ARB                                 0x202D
#define WGL_MAX_PBUFFER_PIXELS_ARB                              0x202E
#define WGL_MAX_PBUFFER_WIDTH_ARB                               0x202F
#define WGL_MAX_PBUFFER_HEIGHT_ARB                              0x2030
#define WGL_PBUFFER_LARGEST_ARB                                 0x2033
#define WGL_PBUFFER_WIDTH_ARB                                   0x2034
#define WGL_PBUFFER_HEIGHT_ARB                                  0x2035
#define WGL_PBUFFER_LOST_ARB                                    0x2036

DECLARE_HANDLE(HPBUFFERARB);

typedef HPBUFFERARB (APIENTRY * wglCreatePbufferARBPROC) (HDC hDC, int iPixelFormat, int iWidth, int iHeight, const int *piAttribList);
typedef HDC (APIENTRY * wglGetPbufferDCARBPROC) (HPBUFFERARB hPbuffer);
typedef int (APIENTRY * wglReleasePbufferDCARBPROC) (HPBUFFERARB hPbuffer, HDC hDC);
typedef BOOL (APIENTRY * wglDestroyPbufferARBPROC) (HPBUFFERARB hPbuffer);
typedef BOOL (APIENTRY * wglQueryPbufferARBPROC) (HPBUFFERARB hPbuffer, int iAttribute, int *piValue);

extern wglCreatePbufferARBPROC wglCreatePbufferARB;
extern wglGetPbufferDCARBPROC wglGetPbufferDCARB;
extern wglReleasePbufferDCARBPROC wglReleasePbufferDCARB;
extern wglDestroyPbufferARBPROC wglDestroyPbufferARB;
extern wglQueryPbufferARBPROC wglQueryPbufferARB;

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_PIXEL_FORMAT-----------------------------------*/
/*-------------------------------------------------------------------*/

#define WGL_NUMBER_PIXEL_FORMATS_ARB                            0x2000
#define WGL_DRAW_TO_WINDOW_ARB                                  0x2001
#define WGL_DRAW_TO_BITMAP_ARB                                  0x2002
#define WGL_ACCELERATION_ARB                                    0x2003
#define WGL_NEED_PALETTE_ARB                                    0x2004
#define WGL_NEED_SYSTEM_PALETTE_ARB                             0x2005
#define WGL_SWAP_LAYER_BUFFERS_ARB                              0x2006
#define WGL_SWAP_METHOD_ARB                                     0x2007
#define WGL_NUMBER_OVERLAYS_ARB                                 0x2008
#define WGL_NUMBER_UNDERLAYS_ARB                                0x2009
#define WGL_TRANSPARENT_ARB                                     0x200A
#define WGL_TRANSPARENT_RED_VALUE_ARB                           0x2037
#define WGL_TRANSPARENT_GREEN_VALUE_ARB                         0x2038
#define WGL_TRANSPARENT_BLUE_VALUE_ARB                          0x2039
#define WGL_TRANSPARENT_ALPHA_VALUE_ARB                         0x203A
#define WGL_TRANSPARENT_INDEX_VALUE_ARB                         0x203B
#define WGL_SHARE_DEPTH_ARB                                     0x200C
#define WGL_SHARE_STENCIL_ARB                                   0x200D
#define WGL_SHARE_ACCUM_ARB                                     0x200E
#define WGL_SUPPORT_GDI_ARB                                     0x200F
#define WGL_SUPPORT_OPENGL_ARB                                  0x2010
#define WGL_DOUBLE_BUFFER_ARB                                   0x2011
#define WGL_STEREO_ARB                                          0x2012
#define WGL_PIXEL_TYPE_ARB                                      0x2013
#define WGL_COLOR_BITS_ARB                                      0x2014
#define WGL_RED_BITS_ARB                                        0x2015
#define WGL_RED_SHIFT_ARB                                       0x2016
#define WGL_GREEN_BITS_ARB                                      0x2017
#define WGL_GREEN_SHIFT_ARB                                     0x2018
#define WGL_BLUE_BITS_ARB                                       0x2019
#define WGL_BLUE_SHIFT_ARB                                      0x201A
#define WGL_ALPHA_BITS_ARB                                      0x201B
#define WGL_ALPHA_SHIFT_ARB                                     0x201C
#define WGL_ACCUM_BITS_ARB                                      0x201D
#define WGL_ACCUM_RED_BITS_ARB                                  0x201E
#define WGL_ACCUM_GREEN_BITS_ARB                                0x201F
#define WGL_ACCUM_BLUE_BITS_ARB                                 0x2020
#define WGL_ACCUM_ALPHA_BITS_ARB                                0x2021
#define WGL_DEPTH_BITS_ARB                                      0x2022
#define WGL_STENCIL_BITS_ARB                                    0x2023
#define WGL_AUX_BUFFERS_ARB                                     0x2024
#define WGL_NO_ACCELERATION_ARB                                 0x2025
#define WGL_GENERIC_ACCELERATION_ARB                            0x2026
#define WGL_FULL_ACCELERATION_ARB                               0x2027
#define WGL_SWAP_EXCHANGE_ARB                                   0x2028
#define WGL_SWAP_COPY_ARB                                       0x2029
#define WGL_SWAP_UNDEFINED_ARB                                  0x202A
#define WGL_TYPE_RGBA_ARB                                       0x202B
#define WGL_TYPE_COLORINDEX_ARB                                 0x202C

typedef BOOL (APIENTRY * wglGetPixelFormatAttribivARBPROC) (HDC hdc, int iPixelFormat, int iLayerPlane, UINT nAttributes, const int *piAttributes, int *piValues);
typedef BOOL (APIENTRY * wglGetPixelFormatAttribfvARBPROC) (HDC hdc, int iPixelFormat, int iLayerPlane, UINT nAttributes, const int *piAttributes, FLOAT *pfValues);
typedef BOOL (APIENTRY * wglChoosePixelFormatARBPROC) (HDC hdc, const int *piAttribIList, const FLOAT *pfAttribFList, UINT nMaxFormats, int *piFormats, UINT *nNumFormats);

extern wglGetPixelFormatAttribivARBPROC wglGetPixelFormatAttribivARB;
extern wglGetPixelFormatAttribfvARBPROC wglGetPixelFormatAttribfvARB;
extern wglChoosePixelFormatARBPROC wglChoosePixelFormatARB;

typedef BOOL (APIENTRY * wglBindTexImageARBPROC) (HPBUFFERARB hPbuffer, int iBuffer);
typedef BOOL (APIENTRY * wglReleaseTexImageARBPROC) (HPBUFFERARB hPbuffer, int iBuffer);
typedef BOOL (APIENTRY * wglSetPbufferAttribARBPROC) (HPBUFFERARB hPbuffer, const int *piAttribList);

extern wglBindTexImageARBPROC wglBindTexImageARB;
extern wglReleaseTexImageARBPROC wglReleaseTexImageARB;
extern wglSetPbufferAttribARBPROC wglSetPbufferAttribARB;

/*-------------------------------------------------------------------*/
/*------------WGL_EXT_SWAP_CONTROL-----------------------------------*/
/*-------------------------------------------------------------------*/

typedef BOOL (APIENTRY * wglSwapIntervalEXTPROC) (int interval);
typedef int (APIENTRY * wglGetSwapIntervalEXTPROC) (void);

extern wglSwapIntervalEXTPROC wglSwapIntervalEXT;
extern wglGetSwapIntervalEXTPROC wglGetSwapIntervalEXT;

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_MAKE_CURRENT_READ------------------------------*/
/*-------------------------------------------------------------------*/

#define ERROR_INVALID_PIXEL_TYPE_ARB                            0x2043
#define ERROR_INCOMPATIBLE_DEVICE_CONTEXTS_ARB	                0x2054

typedef BOOL (APIENTRY * wglMakeContextCurrentARBPROC) (HDC hDrawDC, HDC hReadDC, HGLRC hglrc);
typedef HDC (APIENTRY * wglGetCurrentReadDCARBPROC) (void);

extern wglMakeContextCurrentARBPROC wglMakeContextCurrentARB;
extern wglGetCurrentReadDCARBPROC wglGetCurrentReadDCARB;

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_MULTISAMPLE------------------------------------*/
/*-------------------------------------------------------------------*/

#define WGL_SAMPLE_BUFFERS_ARB                                  0x2041
#define WGL_SAMPLES_ARB                                         0x2042

/*-------------------------------------------------------------------*/
/*------------END WGL EXTENSIONS-------------------------------------*/
/*-------------------------------------------------------------------*/

#endif /* WIN32 */

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

    bool OpenGL12;
    bool OpenGL13;
    bool OpenGL14;
    bool OpenGL15;

    bool GL_ARB_imaging;
	bool GL_ARB_depth_texture;
	bool GL_ARB_fragment_program;
	bool GL_ARB_fragment_program_shadow;
	bool GL_ARB_fragment_shader;
	bool GL_ARB_matrix_palette;
	bool GL_ARB_multisample;
	bool GL_ARB_multitexture;
	bool GL_ARB_occlusion_query;
	bool GL_ARB_point_parameters;
	bool GL_ARB_point_sprite;
	bool GL_ARB_shading_language_100;
	bool GL_ARB_shader_objects;
	bool GL_ARB_shadow;
	bool GL_ARB_shadow_ambient;
	bool GL_ARB_texture_border_clamp;
	bool GL_ARB_texture_compression;
	bool GL_ARB_texture_cube_map;
	bool GL_ARB_texture_env_add;
	bool GL_ARB_texture_env_combine;
	bool GL_ARB_texture_env_crossbar;
	bool GL_ARB_texture_env_dot3;
	bool GL_ARB_texture_mirrored_repeat;
	bool GL_ARB_texture_non_power_of_two;
	bool GL_ARB_transpose_matrix;
	bool GL_ARB_vertex_blend;
	bool GL_ARB_vertex_buffer_object;
	bool GL_ARB_vertex_program;
	bool GL_ARB_vertex_shader;
	bool GL_ARB_window_pos;

	bool GL_EXT_abgr;
	bool GL_EXT_bgra;
	bool GL_EXT_blend_equation_separate;
	bool GL_EXT_blend_func_separate;
	bool GL_EXT_blend_subtract;
	bool GL_EXT_Cg_shader;
	bool GL_EXT_compiled_vertex_array;
	bool GL_EXT_depth_bounds_test;
	bool GL_EXT_draw_range_elements;
	bool GL_EXT_fog_coord;
	bool GL_EXT_multi_draw_arrays;
	bool GL_EXT_packed_pixels;
	bool GL_EXT_pixel_buffer_object;
	bool GL_EXT_point_parameters;
	bool GL_EXT_rescale_normal;
	bool GL_EXT_secondary_color;
	bool GL_EXT_separate_specular_color;
	bool GL_EXT_shadow_funcs;
	bool GL_EXT_shared_texture_palette;
	bool GL_EXT_stencil_two_side;
	bool GL_EXT_stencil_wrap;
	bool GL_EXT_texture_compression_s3tc;
	bool GL_EXT_texture_env_combine;
	bool GL_EXT_texture_env_dot3;
	bool GL_EXT_texture_filter_anisotropic;
	bool GL_EXT_texture_lod_bias;
	bool GL_EXT_texture_rectangle;
	bool GL_EXT_vertex_shader;
	bool GL_EXT_vertex_weighting;

	bool GL_ATI_draw_buffers;
	bool GL_ATI_element_array;
	bool GL_ATI_envmap_bumpmap;
	bool GL_ATI_fragment_shader;
	bool GL_ATI_map_object_buffer;
	bool GL_ATI_pn_triangles;
	bool GL_ATI_separate_stencil;
	bool GL_ATI_texture_float;
	bool GL_ATI_texture_mirror_once;
	bool GL_ATI_vertex_array_object;
	bool GL_ATI_vertex_streams;
	bool GL_ATI_vertex_attrib_array_object;

	bool GL_NV_blend_square;
	bool GL_NV_copy_depth_to_color;
	bool GL_NV_depth_clamp;
	bool GL_NV_evaluators;
	bool GL_NV_fence;
	bool GL_NV_float_buffer;
	bool GL_NV_fog_distance;
	bool GL_NV_fragment_program;
	bool GL_NV_fragment_program_option;
	bool GL_NV_half_float;
	bool GL_NV_light_max_exponent;
	bool GL_NV_multisample_filter_hint;
	bool GL_NV_occlusion_query;
	bool GL_NV_packed_depth_stencil;
	bool GL_NV_pixel_data_range;
	bool GL_NV_point_sprite;
	bool GL_NV_primitive_restart;
	bool GL_NV_register_combiners;
	bool GL_NV_register_combiners2;
	bool GL_NV_texgen_reflection;
	bool GL_NV_texture_compression_vtc;
	bool GL_NV_texture_env_combine4;
	bool GL_NV_texture_expand_normal;
	bool GL_NV_texture_rectangle;
	bool GL_NV_texture_shader;
	bool GL_NV_texture_shader2;
	bool GL_NV_texture_shader3;
	bool GL_NV_vertex_array_range;
	bool GL_NV_vertex_array_range2;
	bool GL_NV_vertex_program;
	bool GL_NV_vertex_program1_1;
	bool GL_NV_vertex_program2;
	bool GL_NV_vertex_program2_option;
};

extern struct ExtensionTypes extgl_Extensions;
//extern bool extgl_error;

typedef GLenum (APIENTRY * glGetErrorPROC) (void);
typedef const GLubyte * (APIENTRY * glGetStringPROC) (GLenum name);

extern glGetErrorPROC glGetError;
extern glGetStringPROC glGetString;

/* initializes everything, call this right after the rc is created. the function returns 0 if successful */
extern bool extgl_Initialize(JNIEnv *env, jobject gl_extensions);
extern bool extgl_Open(void);
#ifdef _WIN32
extern void extgl_InitWGL(JNIEnv *env);
#endif
#ifdef _AGL
extern bool extgl_InitAGL(JNIEnv *env);
#endif
extern void extgl_Close(void);
extern bool extgl_InitializeClass(JNIEnv *env, jclass clazz, jobject ext_set, const char *ext_name, int num_functions, JavaMethodAndExtFunction *functions);
extern bool extgl_InitializeFunctions(int num_functions, ExtFunction *functions);
extern bool extgl_QueryExtension(JNIEnv *env, jobject ext_set, const GLubyte*extensions, const char *name);

#define EXTGL_SANITY_CHECK(e,x)		if (extgl_error) { \
						extgl_Extensions.x = false; \
						printf("NOTICE: %s disabled because of missing driver symbols\n", #x); \
						extgl_error = false; \
					}

#ifdef __cplusplus
}
#endif

#endif /* __EXTGL_H__ */
