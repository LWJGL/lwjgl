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
/* These extensions are supported:
GL_ARB_vertex_buffer_object
GL_ARB_depth_texture
GL_ARB_fragment_program
GL_ARB_imaging
GL_ARB_matrix_palette
GL_ARB_multisample
GL_ARB_multitexture
GL_ARB_point_parameters
GL_ARB_shadow
GL_ARB_shadow_ambient
GL_ARB_texture_compression
GL_ARB_texture_env_add
GL_ARB_texture_env_dot3
GL_ARB_texture_env_combine
GL_ARB_texture_env_crossbar
GL_ARB_texture_border_clamp
GL_ARB_texture_cube_map
GL_ARB_texture_mirrored_repeat
GL_ARB_transpose_matrix
GL_ARB_vertex_blend
GL_ARB_vertex_program
GL_ARB_window_pos
GL_EXT_abgr
GL_EXT_bgra
GL_EXT_blend_function_separate
GL_EXT_compiled_vertex_array
GL_EXT_cull_vertex
GL_EXT_draw_range_elements
GL_EXT_fog_coord
GL_EXT_multi_draw_arrays
GL_EXT_point_parameters
GL_EXT_secondary_color
GL_EXT_separate_specular_color
GL_EXT_shadow_funcs
GL_EXT_stencil_two_side
GL_EXT_stencil_wrap
GL_EXT_texture_compression_s3tc
GL_EXT_texture_filter_anisotropic
GL_EXT_texture_lod_bias
GL_EXT_vertex_shader
GL_EXT_vertex_weighting
GL_ATI_element_array
GL_ATI_envmap_bumpmap
GL_ATI_fragment_shader
GL_ATI_pn_triangles
GL_ATI_text_fragment_shader
GL_ATI_texture_mirror_once
GL_ATI_vertex_array_object;
GL_ATI_vertex_streams
GL_ATIX_point_sprites
GL_ATIX_texture_env_route
GL_HP_occlusion_test
GL_NV_blend_square
GL_NV_copy_depth_to_color
GL_NV_depth_clamp
GL_NV_element_array
GL_NV_evaluators
GL_NV_fence
GL_NV_float_buffer
GL_NV_fog_distance
GL_NV_fragment_program
GL_NV_light_max_exponent
GL_NV_occlusion_query
GL_NV_packed_depth_stencil
GL_NV_point_sprite
GL_NV_primitive_restart
GL_NV_register_combiners
GL_NV_register_combiners2
GL_NV_texgen_reflection
GL_NV_texture_env_combine4
GL_NV_texture_rectangle
GL_NV_texture_shader
GL_NV_texture_shader2
GL_NV_texture_shader3
GL_NV_vertex_array_range
GL_NV_vertex_array_range2
GL_NV_vertex_program
GL_NV_vertex_program1_1
GL_NV_vertex_program2
GL_SGIS_generate_mipmap
GL_SGIX_shadow
GL_SGIX_depth_texture
WGL_ARB_buffer_region
WGL_ARB_extensions_string
WGL_ARB_make_current_read;
WGL_ARB_multisample
WGL_ARB_pbuffer
WGL_ARB_pixel_format
WGL_ARB_render_texture
WGL_EXT_extensions_string
WGL_EXT_swap_control
WGL_NV_render_depth_texture
WGL_NV_render_texture_rectangle
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

#ifdef _X11

#include <X11/Xutil.h>

/*
 * Names for attributes to glXGetConfig.
 */
#define GLX_USE_GL              1       /* support GLX rendering */
#define GLX_BUFFER_SIZE         2       /* depth of the color buffer */
#define GLX_LEVEL               3       /* level in plane stacking */
#define GLX_RGBA                4       /* true if RGBA mode */
#define GLX_DOUBLEBUFFER        5       /* double buffering supported */
#define GLX_STEREO              6       /* stereo buffering supported */
#define GLX_AUX_BUFFERS         7       /* number of aux buffers */
#define GLX_RED_SIZE            8       /* number of red component bits */
#define GLX_GREEN_SIZE          9       /* number of green component bits */
#define GLX_BLUE_SIZE           10      /* number of blue component bits */
#define GLX_ALPHA_SIZE          11      /* number of alpha component bits */
#define GLX_DEPTH_SIZE          12      /* number of depth bits */
#define GLX_STENCIL_SIZE        13      /* number of stencil bits */
#define GLX_ACCUM_RED_SIZE      14      /* number of red accum bits */
#define GLX_ACCUM_GREEN_SIZE    15      /* number of green accum bits */
#define GLX_ACCUM_BLUE_SIZE     16      /* number of blue accum bits */
#define GLX_ACCUM_ALPHA_SIZE    17      /* number of alpha accum bits */

#define GLX_SAMPLE_BUFFERS_ARB  100000  /* number of multisample buffers */
#define GLX_SAMPLES_ARB         100001  /* number of multisample samples */

/*
 * FBConfig-specific attributes
 */
#define GLX_X_VISUAL_TYPE               0x22
#define GLX_CONFIG_CAVEAT               0x20    /* Like visual_info VISUAL_CAVEAT */
#define GLX_TRANSPARENT_TYPE            0x23
#define GLX_TRANSPARENT_INDEX_VALUE     0x24
#define GLX_TRANSPARENT_RED_VALUE       0x25
#define GLX_TRANSPARENT_GREEN_VALUE     0x26
#define GLX_TRANSPARENT_BLUE_VALUE      0x27
#define GLX_TRANSPARENT_ALPHA_VALUE     0x28
#define GLX_DRAWABLE_TYPE               0x8010
#define GLX_RENDER_TYPE                 0x8011
#define GLX_X_RENDERABLE                0x8012
#define GLX_FBCONFIG_ID                 0x8013
#define GLX_MAX_PBUFFER_WIDTH           0x8016
#define GLX_MAX_PBUFFER_HEIGHT          0x8017
#define GLX_MAX_PBUFFER_PIXELS          0x8018
#define GLX_VISUAL_ID                   0x800B

#define GLX_DRAWABLE_TYPE_SGIX          GLX_DRAWABLE_TYPE
#define GLX_RENDER_TYPE_SGIX            GLX_RENDER_TYPE
#define GLX_X_RENDERABLE_SGIX           GLX_X_RENDERABLE
#define GLX_FBCONFIG_ID_SGIX            GLX_FBCONFIG_ID
#define GLX_MAX_PBUFFER_WIDTH_SGIX      GLX_MAX_PBUFFER_WIDTH
#define GLX_MAX_PBUFFER_HEIGHT_SGIX     GLX_MAX_PBUFFER_HEIGHT
#define GLX_MAX_PBUFFER_PIXELS_SGIX     GLX_MAX_PBUFFER_PIXELS
#define GLX_OPTIMAL_PBUFFER_WIDTH_SGIX  0x8019
#define GLX_OPTIMAL_PBUFFER_HEIGHT_SGIX 0x801A

/*
 * Error return values from glXGetConfig.  Success is indicated by
 * a value of 0.
 */
#define GLX_BAD_SCREEN                  1  /* screen # is bad */
#define GLX_BAD_ATTRIBUTE               2  /* attribute to get is bad */
#define GLX_NO_EXTENSION                3  /* no glx extension on server */
#define GLX_BAD_VISUAL                  4  /* visual # not known by GLX */
#define GLX_BAD_CONTEXT                 5
#define GLX_BAD_VALUE                   6
#define GLX_BAD_ENUM                    7


/* FBConfig attribute values */

/*
 * Generic "don't care" value for glX ChooseFBConfig attributes (except
 * GLX_LEVEL).
 */
#define GLX_DONT_CARE                   0xFFFFFFFF

/* GLX_RENDER_TYPE bits */
#define GLX_RGBA_BIT                    0x00000001
#define GLX_COLOR_INDEX_BIT             0x00000002
#define GLX_RGBA_BIT_SGIX               GLX_RGBA_BIT
#define GLX_COLOR_INDEX_BIT_SGIX        GLX_COLOR_INDEX_BIT

/* GLX_DRAWABLE_TYPE bits */
#define GLX_WINDOW_BIT                  0x00000001
#define GLX_PIXMAP_BIT                  0x00000002
#define GLX_PBUFFER_BIT                 0x00000004
#define GLX_WINDOW_BIT_SGIX             GLX_WINDOW_BIT
#define GLX_PIXMAP_BIT_SGIX             GLX_PIXMAP_BIT
#define GLX_PBUFFER_BIT_SGIX            GLX_PBUFFER_BIT

/* GLX_CONFIG_CAVEAT attribute values */
#define GLX_NONE                        0x8000
#define GLX_SLOW_CONFIG                 0x8001
#define GLX_NON_CONFORMANT_CONFIG       0x800D

/* GLX_X_VISUAL_TYPE attribute values */
#define GLX_TRUE_COLOR                  0x8002
#define GLX_DIRECT_COLOR                0x8003
#define GLX_PSEUDO_COLOR                0x8004
#define GLX_STATIC_COLOR                0x8005
#define GLX_GRAY_SCALE                  0x8006
#define GLX_STATIC_GRAY                 0x8007

/* GLX_TRANSPARENT_TYPE attribute values */
/* #define GLX_NONE                        0x8000 */
#define GLX_TRANSPARENT_RGB             0x8008
#define GLX_TRANSPARENT_INDEX           0x8009

/* glXCreateGLXPbuffer attributes */
#define GLX_PRESERVED_CONTENTS          0x801B
#define GLX_LARGEST_PBUFFER             0x801C
#define GLX_PBUFFER_HEIGHT              0x8040  /* New for GLX 1.3 */
#define GLX_PBUFFER_WIDTH               0x8041  /* New for GLX 1.3 */
#define GLX_PRESERVED_CONTENTS_SGIX     GLX_PRESERVED_CONTENTS
#define GLX_LARGEST_PBUFFER_SGIX        GLX_LARGEST_PBUFFER

/* glXQueryGLXPBuffer attributes */
#define GLX_WIDTH                       0x801D
#define GLX_HEIGHT                      0x801E
#define GLX_EVENT_MASK                  0x801F
#define GLX_WIDTH_SGIX                  GLX_WIDTH
#define GLX_HEIGHT_SGIX                 GLX_HEIGHT
#define GLX_EVENT_MASK_SGIX             GLX_EVENT_MASK

/* glXCreateNewContext render_type attribute values */
#define GLX_RGBA_TYPE                   0x8014
#define GLX_COLOR_INDEX_TYPE            0x8015
#define GLX_RGBA_TYPE_SGIX              GLX_RGBA_TYPE
#define GLX_COLOR_INDEX_TYPE_SGIX       GLX_COLOR_INDEX_TYPE

/* glXQueryContext attributes */
/* #define GLX_FBCONFIG_ID                0x8013 */
/* #define GLX_RENDER_TYPE                0x8011 */
#define GLX_SCREEN                      0x800C

/* glXSelectEvent event mask bits */
#define GLX_PBUFFER_CLOBBER_MASK        0x08000000
#define GLX_PBUFFER_CLOBBER_MASK_SGIX   GLX_PBUFFER_CLOBBER_MASK

/* GLXPbufferClobberEvent event_type values */
#define GLX_DAMAGED                     0x8020
#define GLX_SAVED                       0x8021
#define GLX_DAMAGED_SGIX                GLX_DAMAGED
#define GLX_SAVED_SGIX                  GLX_SAVED

/* GLXPbufferClobberEvent draw_type values */
#define GLX_WINDOW                      0x8022
#define GLX_PBUFFER                     0x8023
#define GLX_WINDOW_SGIX                 GLX_WINDOW
#define GLX_PBUFFER_SGIX                GLX_PBUFFER

/* GLXPbufferClobberEvent buffer_mask bits */
#define GLX_FRONT_LEFT_BUFFER_BIT       0x00000001
#define GLX_FRONT_RIGHT_BUFFER_BIT      0x00000002
#define GLX_BACK_LEFT_BUFFER_BIT        0x00000004
#define GLX_BACK_RIGHT_BUFFER_BIT       0x00000008
#define GLX_AUX_BUFFERS_BIT             0x00000010
#define GLX_DEPTH_BUFFER_BIT            0x00000020
#define GLX_STENCIL_BUFFER_BIT          0x00000040
#define GLX_ACCUM_BUFFER_BIT            0x00000080
#define GLX_FRONT_LEFT_BUFFER_BIT_SGIX  GLX_FRONT_LEFT_BUFFER_BIT
#define GLX_FRONT_RIGHT_BUFFER_BIT_SGIX GLX_FRONT_RIGHT_BUFFER_BIT
#define GLX_BACK_LEFT_BUFFER_BIT_SGIX   GLX_BACK_LEFT_BUFFER_BIT
#define GLX_BACK_RIGHT_BUFFER_BIT_SGIX  GLX_BACK_RIGHT_BUFFER_BIT
#define GLX_AUX_BUFFERS_BIT_SGIX        GLX_AUX_BUFFERS_BIT
#define GLX_DEPTH_BUFFER_BIT_SGIX       GLX_DEPTH_BUFFER_BIT
#define GLX_STENCIL_BUFFER_BIT_SGIX     GLX_STENCIL_BUFFER_BIT
#define GLX_ACCUM_BUFFER_BIT_SGIX       GLX_ACCUM_BUFFER_BIT

/*
 * Extension return values from glXGetConfig.  These are also
 * accepted as parameter values for glXChooseVisual.
 */

#define GLX_X_VISUAL_TYPE_EXT           0x22    /* visual_info extension type */
#define GLX_TRANSPARENT_TYPE_EXT        0x23    /* visual_info extension */
#define GLX_TRANSPARENT_INDEX_VALUE_EXT 0x24    /* visual_info extension */
#define GLX_TRANSPARENT_RED_VALUE_EXT   0x25    /* visual_info extension */
#define GLX_TRANSPARENT_GREEN_VALUE_EXT 0x26    /* visual_info extension */
#define GLX_TRANSPARENT_BLUE_VALUE_EXT  0x27    /* visual_info extension */
#define GLX_TRANSPARENT_ALPHA_VALUE_EXT 0x28    /* visual_info extension */

/* Property values for visual_type */
#define GLX_TRUE_COLOR_EXT              0x8002
#define GLX_DIRECT_COLOR_EXT            0x8003
#define GLX_PSEUDO_COLOR_EXT            0x8004
#define GLX_STATIC_COLOR_EXT            0x8005
#define GLX_GRAY_SCALE_EXT              0x8006
#define GLX_STATIC_GRAY_EXT             0x8007

/* Property values for transparent pixel */
#define GLX_NONE_EXT                    0x8000
#define GLX_TRANSPARENT_RGB_EXT         0x8008
#define GLX_TRANSPARENT_INDEX_EXT       0x8009

/* Property values for visual_rating */
#define GLX_VISUAL_CAVEAT_EXT           0x20    /* visual_rating extension type */
#define GLX_SLOW_VISUAL_EXT             0x8001
#define GLX_NON_CONFORMANT_VISUAL_EXT   0x800D

/*
 * Names for attributes to glXGetClientString.
 */
#define GLX_VENDOR                      0x1
#define GLX_VERSION                     0x2
#define GLX_EXTENSIONS                  0x3

/*
 * Names for attributes to glXQueryContextInfoEXT.
 */
#define GLX_SHARE_CONTEXT_EXT           0x800A  /* id of share context */
#define GLX_VISUAL_ID_EXT               0x800B  /* id of context's visual */
#define GLX_SCREEN_EXT                  0x800C  /* screen number */

/* NV_float_buffer */
#define GLX_FLOAT_COMPONENTS_NV         0x20B0

typedef XID GLXContextID;
typedef XID GLXPixmap;
typedef XID GLXDrawable;
typedef XID GLXPbuffer;
typedef XID GLXWindow;
typedef XID GLXFBConfigID;

typedef struct __GLXcontextRec *GLXContext;

typedef struct __GLXFBConfigRec *GLXFBConfig;

typedef GLXFBConfig * (APIENTRY * glXGetFBConfigsPROC) (Display *dpy, int screen, int *nelements);
typedef GLXFBConfig * (APIENTRY * glXChooseFBConfigPROC) (Display *dpy, int screen, const int *attrib_list, int *nelements);
typedef int (APIENTRY * glXGetFBConfigAttribPROC) (Display *dpy, GLXFBConfig config, int attribute, int *value);
typedef XVisualInfo * (APIENTRY * glXGetVisualFromFBConfigPROC) (Display *dpy, GLXFBConfig config);
typedef GLXWindow (APIENTRY * glXCreateWindowPROC) (Display *dpy, GLXFBConfig config, Window win, const int *attrib_list);
typedef void (APIENTRY * glXDestroyWindowPROC) (Display *dpy, GLXWindow win);
typedef GLXPixmap (APIENTRY * glXCreatePixmapPROC) (Display *dpy, GLXFBConfig config, Pixmap pixmap, const int *attrib_list);
typedef void (APIENTRY * glXDestroyPixmapPROC) (Display *dpy, GLXPixmap pixmap);
typedef GLXPbuffer (APIENTRY * glXCreatePbufferPROC) (Display *dpy, GLXFBConfig config, const int *attrib_list);
typedef void (APIENTRY * glXDestroyPbufferPROC) (Display *dpy, GLXPbuffer pbuf);
typedef void (APIENTRY * glXQueryDrawablePROC) (Display *dpy, GLXDrawable draw, int attribute, unsigned int *value);
typedef GLXContext (APIENTRY * glXCreateNewContextPROC) (Display *dpy, GLXFBConfig config, int render_type, GLXContext share_list, Bool direct);
typedef Bool (APIENTRY * glXMakeContextCurrentPROC) (Display *display, GLXDrawable draw, GLXDrawable read, GLXContext ctx);
typedef GLXDrawable (APIENTRY * glXGetCurrentReadDrawablePROC) (void);
typedef Display * (APIENTRY * glXGetCurrentDisplayPROC) (void);
typedef int (APIENTRY * glXQueryContextPROC) (Display *dpy, GLXContext ctx, int attribute, int *value);
typedef void (APIENTRY * glXSelectEventPROC) (Display *dpy, GLXDrawable draw, unsigned long event_mask);
typedef void (APIENTRY * glXGetSelectedEventPROC) (Display *dpy, GLXDrawable draw, unsigned long *event_mask);

typedef GLXContextID (APIENTRY * glXGetContextIDEXTPROC) (const GLXContext ctx);
typedef GLXDrawable (APIENTRY * glXGetCurrentDrawableEXTPROC) (void);
typedef GLXContext (APIENTRY * glXImportContextEXTPROC) (Display *dpy, GLXContextID contextID);
typedef void (APIENTRY * glXFreeContextEXTPROC) (Display *dpy, GLXContext ctx);
typedef int (APIENTRY * glXQueryContextInfoEXTPROC) (Display *dpy, GLXContext ctx, int attribute, int *value);

typedef void (* (APIENTRY * glXGetProcAddressARBPROC) (const GLubyte *procName))(void);

typedef XVisualInfo* (APIENTRY * glXChooseVisualPROC) (Display *dpy, int screen, int *attribList);
typedef void (APIENTRY * glXCopyContextPROC) (Display *dpy, GLXContext src, GLXContext dst, unsigned long mask);
typedef GLXContext (APIENTRY * glXCreateContextPROC) (Display *dpy, XVisualInfo *vis, GLXContext shareList, Bool direct);
typedef GLXPixmap (APIENTRY * glXCreateGLXPixmapPROC) (Display *dpy, XVisualInfo *vis, Pixmap pixmap);
typedef void (APIENTRY * glXDestroyContextPROC) (Display *dpy, GLXContext ctx);
typedef void (APIENTRY * glXDestroyGLXPixmapPROC) (Display *dpy, GLXPixmap pix);
typedef int (APIENTRY * glXGetConfigPROC) (Display *dpy, XVisualInfo *vis, int attrib, int *value);
typedef GLXContext (APIENTRY * glXGetCurrentContextPROC) (void);
typedef GLXDrawable (APIENTRY * glXGetCurrentDrawablePROC) (void);
typedef Bool (APIENTRY * glXIsDirectPROC) (Display *dpy, GLXContext ctx);
typedef Bool (APIENTRY * glXMakeCurrentPROC) (Display *dpy, GLXDrawable drawable, GLXContext ctx);
typedef Bool (APIENTRY * glXQueryExtensionPROC) (Display *dpy, int *errorBase, int *eventBase);
typedef Bool (APIENTRY * glXQueryVersionPROC) (Display *dpy, int *major, int *minor);
typedef void (APIENTRY * glXSwapBuffersPROC) (Display *dpy, GLXDrawable drawable);
typedef void (APIENTRY * glXUseXFontPROC) (Font font, int first, int count, int listBase);
typedef void (APIENTRY * glXWaitGLPROC) (void);
typedef void (APIENTRY * glXWaitXPROC) (void);
typedef const char * (APIENTRY * glXGetClientStringPROC) (Display *dpy, int name );
typedef const char * (APIENTRY * glXQueryServerStringPROC) (Display *dpy, int screen, int name );
typedef const char * (APIENTRY * glXQueryExtensionsStringPROC) (Display *dpy, int screen );

extern glXGetFBConfigsPROC glXGetFBConfigs;
extern glXChooseFBConfigPROC glXChooseFBConfig;
extern glXGetFBConfigAttribPROC glXGetFBConfigAttrib;
extern glXGetVisualFromFBConfigPROC glXGetVisualFromFBConfig;
extern glXCreateWindowPROC glXCreateWindow;
extern glXDestroyWindowPROC glXDestroyWindow;
extern glXCreatePixmapPROC glXCreatePixmap;
extern glXDestroyPixmapPROC glXDestroyPixmap;
extern glXCreatePbufferPROC glXCreatePbuffer;
extern glXDestroyPbufferPROC glXDestroyPbuffer;
extern glXQueryDrawablePROC glXQueryDrawable;
extern glXCreateNewContextPROC glXCreateNewContext;
extern glXMakeContextCurrentPROC glXMakeContextCurrent;
extern glXGetCurrentReadDrawablePROC glXGetCurrentReadDrawable;
extern glXGetCurrentDisplayPROC glXGetCurrentDisplay;
extern glXQueryContextPROC glXQueryContext;
extern glXSelectEventPROC glXSelectEvent;
extern glXGetSelectedEventPROC glXGetSelectedEvent;

extern glXGetProcAddressARBPROC glXGetProcAddressARB;

extern glXChooseVisualPROC glXChooseVisual;
extern glXCopyContextPROC glXCopyContext;
extern glXCreateContextPROC glXCreateContext;
extern glXCreateGLXPixmapPROC glXCreateGLXPixmap;
extern glXDestroyContextPROC glXDestroyContext;
extern glXDestroyGLXPixmapPROC glXDestroyGLXPixmap;
extern glXGetConfigPROC glXGetConfig;
extern glXGetCurrentContextPROC glXGetCurrentContext;
extern glXGetCurrentDrawablePROC glXGetCurrentDrawable;
extern glXIsDirectPROC glXIsDirect;
extern glXMakeCurrentPROC glXMakeCurrent;
extern glXQueryExtensionPROC glXQueryExtension;
extern glXQueryVersionPROC glXQueryVersion;
extern glXSwapBuffersPROC glXSwapBuffers;
extern glXUseXFontPROC glXUseXFont;
extern glXWaitGLPROC glXWaitGL;
extern glXWaitXPROC glXWaitX;
extern glXGetClientStringPROC glXGetClientString;
extern glXQueryServerStringPROC glXQueryServerString;
extern glXQueryExtensionsStringPROC glXQueryExtensionsString;

/* GLX_SGI_swap_control */
typedef void (APIENTRY * glXSwapIntervalSGIPROC)(int interval);

extern glXSwapIntervalSGIPROC glXSwapIntervalSGI;

#endif /* X11 */

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

#define GL_VERSION_1_1                                          1
#define GL_ACCUM                                                0x0100
#define GL_LOAD                                                 0x0101
#define GL_RETURN                                               0x0102
#define GL_MULT                                                 0x0103
#define GL_ADD                                                  0x0104
#define GL_NEVER                                                0x0200
#define GL_LESS                                                 0x0201
#define GL_EQUAL                                                0x0202
#define GL_LEQUAL                                               0x0203
#define GL_GREATER                                              0x0204
#define GL_NOTEQUAL                                             0x0205
#define GL_GEQUAL                                               0x0206
#define GL_ALWAYS                                               0x0207
#define GL_CURRENT_BIT                                          0x00000001
#define GL_POINT_BIT                                            0x00000002
#define GL_LINE_BIT                                             0x00000004
#define GL_POLYGON_BIT                                          0x00000008
#define GL_POLYGON_STIPPLE_BIT                                  0x00000010
#define GL_PIXEL_MODE_BIT                                       0x00000020
#define GL_LIGHTING_BIT                                         0x00000040
#define GL_FOG_BIT                                              0x00000080
#define GL_DEPTH_BUFFER_BIT                                     0x00000100
#define GL_ACCUM_BUFFER_BIT                                     0x00000200
#define GL_STENCIL_BUFFER_BIT                                   0x00000400
#define GL_VIEWPORT_BIT                                         0x00000800
#define GL_TRANSFORM_BIT                                        0x00001000
#define GL_ENABLE_BIT                                           0x00002000
#define GL_COLOR_BUFFER_BIT                                     0x00004000
#define GL_HINT_BIT                                             0x00008000
#define GL_EVAL_BIT                                             0x00010000
#define GL_LIST_BIT                                             0x00020000
#define GL_TEXTURE_BIT                                          0x00040000
#define GL_SCISSOR_BIT                                          0x00080000
#define GL_ALL_ATTRIB_BITS                                      0x000fffff
#define GL_POINTS                                               0x0000
#define GL_LINES                                                0x0001
#define GL_LINE_LOOP                                            0x0002
#define GL_LINE_STRIP                                           0x0003
#define GL_TRIANGLES                                            0x0004
#define GL_TRIANGLE_STRIP                                       0x0005
#define GL_TRIANGLE_FAN                                         0x0006
#define GL_QUADS                                                0x0007
#define GL_QUAD_STRIP                                           0x0008
#define GL_POLYGON                                              0x0009
#define GL_ZERO                                                 0
#define GL_ONE                                                  1
#define GL_SRC_COLOR                                            0x0300
#define GL_ONE_MINUS_SRC_COLOR                                  0x0301
#define GL_SRC_ALPHA                                            0x0302
#define GL_ONE_MINUS_SRC_ALPHA                                  0x0303
#define GL_DST_ALPHA                                            0x0304
#define GL_ONE_MINUS_DST_ALPHA                                  0x0305
#define GL_DST_COLOR                                            0x0306
#define GL_ONE_MINUS_DST_COLOR                                  0x0307
#define GL_SRC_ALPHA_SATURATE                                   0x0308
#define GL_TRUE                                                 1
#define GL_FALSE                                                0
#define GL_CLIP_PLANE0                                          0x3000
#define GL_CLIP_PLANE1                                          0x3001
#define GL_CLIP_PLANE2                                          0x3002
#define GL_CLIP_PLANE3                                          0x3003
#define GL_CLIP_PLANE4                                          0x3004
#define GL_CLIP_PLANE5                                          0x3005
#define GL_BYTE                                                 0x1400
#define GL_UNSIGNED_BYTE                                        0x1401
#define GL_SHORT                                                0x1402
#define GL_UNSIGNED_SHORT                                       0x1403
#define GL_INT                                                  0x1404
#define GL_UNSIGNED_INT                                         0x1405
#define GL_FLOAT                                                0x1406
#define GL_2_BYTES                                              0x1407
#define GL_3_BYTES                                              0x1408
#define GL_4_BYTES                                              0x1409
#define GL_DOUBLE                                               0x140A
#define GL_NONE                           0
#define GL_FRONT_LEFT                                           0x0400
#define GL_FRONT_RIGHT                                          0x0401
#define GL_BACK_LEFT                                            0x0402
#define GL_BACK_RIGHT                                           0x0403
#define GL_FRONT                                                0x0404
#define GL_BACK                                                 0x0405
#define GL_LEFT                                                 0x0406
#define GL_RIGHT                                                0x0407
#define GL_FRONT_AND_BACK                                       0x0408
#define GL_AUX0                                                 0x0409
#define GL_AUX1                                                 0x040A
#define GL_AUX2                                                 0x040B
#define GL_AUX3                                                 0x040C
#define GL_NO_ERROR                       0
#define GL_INVALID_ENUM                                         0x0500
#define GL_INVALID_VALUE                                        0x0501
#define GL_INVALID_OPERATION                                    0x0502
#define GL_STACK_OVERFLOW                                       0x0503
#define GL_STACK_UNDERFLOW                                      0x0504
#define GL_OUT_OF_MEMORY                                        0x0505
#define GL_2D                                                   0x0600
#define GL_3D                                                   0x0601
#define GL_3D_COLOR                                             0x0602
#define GL_3D_COLOR_TEXTURE                                     0x0603
#define GL_4D_COLOR_TEXTURE                                     0x0604
#define GL_PASS_THROUGH_TOKEN                                   0x0700
#define GL_POINT_TOKEN                                          0x0701
#define GL_LINE_TOKEN                                           0x0702
#define GL_POLYGON_TOKEN                                        0x0703
#define GL_BITMAP_TOKEN                                         0x0704
#define GL_DRAW_PIXEL_TOKEN                                     0x0705
#define GL_COPY_PIXEL_TOKEN                                     0x0706
#define GL_LINE_RESET_TOKEN                                     0x0707
#define GL_EXP                                                  0x0800
#define GL_EXP2                                                 0x0801
#define GL_CW                                                   0x0900
#define GL_CCW                                                  0x0901
#define GL_COEFF                                                0x0A00
#define GL_ORDER                                                0x0A01
#define GL_DOMAIN                                               0x0A02
#define GL_CURRENT_COLOR                                        0x0B00
#define GL_CURRENT_INDEX                                        0x0B01
#define GL_CURRENT_NORMAL                                       0x0B02
#define GL_CURRENT_TEXTURE_COORDS                               0x0B03
#define GL_CURRENT_RASTER_COLOR                                 0x0B04
#define GL_CURRENT_RASTER_INDEX                                 0x0B05
#define GL_CURRENT_RASTER_TEXTURE_COORDS                        0x0B06
#define GL_CURRENT_RASTER_POSITION                              0x0B07
#define GL_CURRENT_RASTER_POSITION_VALID                        0x0B08
#define GL_CURRENT_RASTER_DISTANCE                              0x0B09
#define GL_POINT_SMOOTH                                         0x0B10
#define GL_POINT_SIZE                                           0x0B11
#define GL_POINT_SIZE_RANGE                                     0x0B12
#define GL_POINT_SIZE_GRANULARITY                               0x0B13
#define GL_LINE_SMOOTH                                          0x0B20
#define GL_LINE_WIDTH                                           0x0B21
#define GL_LINE_WIDTH_RANGE                                     0x0B22
#define GL_LINE_WIDTH_GRANULARITY                               0x0B23
#define GL_LINE_STIPPLE                                         0x0B24
#define GL_LINE_STIPPLE_PATTERN                                 0x0B25
#define GL_LINE_STIPPLE_REPEAT                                  0x0B26
#define GL_LIST_MODE                                            0x0B30
#define GL_MAX_LIST_NESTING                                     0x0B31
#define GL_LIST_BASE                                            0x0B32
#define GL_LIST_INDEX                                           0x0B33
#define GL_POLYGON_MODE                                         0x0B40
#define GL_POLYGON_SMOOTH                                       0x0B41
#define GL_POLYGON_STIPPLE                                      0x0B42
#define GL_EDGE_FLAG                                            0x0B43
#define GL_CULL_FACE                                            0x0B44
#define GL_CULL_FACE_MODE                                       0x0B45
#define GL_FRONT_FACE                                           0x0B46
#define GL_LIGHTING                                             0x0B50
#define GL_LIGHT_MODEL_LOCAL_VIEWER                             0x0B51
#define GL_LIGHT_MODEL_TWO_SIDE                                 0x0B52
#define GL_LIGHT_MODEL_AMBIENT                                  0x0B53
#define GL_SHADE_MODEL                                          0x0B54
#define GL_COLOR_MATERIAL_FACE                                  0x0B55
#define GL_COLOR_MATERIAL_PARAMETER                             0x0B56
#define GL_COLOR_MATERIAL                                       0x0B57
#define GL_FOG                                                  0x0B60
#define GL_FOG_INDEX                                            0x0B61
#define GL_FOG_DENSITY                                          0x0B62
#define GL_FOG_START                                            0x0B63
#define GL_FOG_END                                              0x0B64
#define GL_FOG_MODE                                             0x0B65
#define GL_FOG_COLOR                                            0x0B66
#define GL_DEPTH_RANGE                                          0x0B70
#define GL_DEPTH_TEST                                           0x0B71
#define GL_DEPTH_WRITEMASK                                      0x0B72
#define GL_DEPTH_CLEAR_VALUE                                    0x0B73
#define GL_DEPTH_FUNC                                           0x0B74
#define GL_ACCUM_CLEAR_VALUE                                    0x0B80
#define GL_STENCIL_TEST                                         0x0B90
#define GL_STENCIL_CLEAR_VALUE                                  0x0B91
#define GL_STENCIL_FUNC                                         0x0B92
#define GL_STENCIL_VALUE_MASK                                   0x0B93
#define GL_STENCIL_FAIL                                         0x0B94
#define GL_STENCIL_PASS_DEPTH_FAIL                              0x0B95
#define GL_STENCIL_PASS_DEPTH_PASS                              0x0B96
#define GL_STENCIL_REF                                          0x0B97
#define GL_STENCIL_WRITEMASK                                    0x0B98
#define GL_MATRIX_MODE                                          0x0BA0
#define GL_NORMALIZE                                            0x0BA1
#define GL_VIEWPORT                                             0x0BA2
#define GL_MODELVIEW_STACK_DEPTH                                0x0BA3
#define GL_PROJECTION_STACK_DEPTH                               0x0BA4
#define GL_TEXTURE_STACK_DEPTH                                  0x0BA5
#define GL_MODELVIEW_MATRIX                                     0x0BA6
#define GL_PROJECTION_MATRIX                                    0x0BA7
#define GL_TEXTURE_MATRIX                                       0x0BA8
#define GL_ATTRIB_STACK_DEPTH                                   0x0BB0
#define GL_CLIENT_ATTRIB_STACK_DEPTH                            0x0BB1
#define GL_ALPHA_TEST                                           0x0BC0
#define GL_ALPHA_TEST_FUNC                                      0x0BC1
#define GL_ALPHA_TEST_REF                                       0x0BC2
#define GL_DITHER                                               0x0BD0
#define GL_BLEND_DST                                            0x0BE0
#define GL_BLEND_SRC                                            0x0BE1
#define GL_BLEND                                                0x0BE2
#define GL_LOGIC_OP_MODE                                        0x0BF0
#define GL_INDEX_LOGIC_OP                                       0x0BF1
#define GL_COLOR_LOGIC_OP                                       0x0BF2
#define GL_AUX_BUFFERS                                          0x0C00
#define GL_DRAW_BUFFER                                          0x0C01
#define GL_READ_BUFFER                                          0x0C02
#define GL_SCISSOR_BOX                                          0x0C10
#define GL_SCISSOR_TEST                                         0x0C11
#define GL_INDEX_CLEAR_VALUE                                    0x0C20
#define GL_INDEX_WRITEMASK                                      0x0C21
#define GL_COLOR_CLEAR_VALUE                                    0x0C22
#define GL_COLOR_WRITEMASK                                      0x0C23
#define GL_INDEX_MODE                                           0x0C30
#define GL_RGBA_MODE                                            0x0C31
#define GL_DOUBLEBUFFER                                         0x0C32
#define GL_STEREO                                               0x0C33
#define GL_RENDER_MODE                                          0x0C40
#define GL_PERSPECTIVE_CORRECTION_HINT                          0x0C50
#define GL_POINT_SMOOTH_HINT                                    0x0C51
#define GL_LINE_SMOOTH_HINT                                     0x0C52
#define GL_POLYGON_SMOOTH_HINT                                  0x0C53
#define GL_FOG_HINT                                             0x0C54
#define GL_TEXTURE_GEN_S                                        0x0C60
#define GL_TEXTURE_GEN_T                                        0x0C61
#define GL_TEXTURE_GEN_R                                        0x0C62
#define GL_TEXTURE_GEN_Q                                        0x0C63
#define GL_PIXEL_MAP_I_TO_I                                     0x0C70
#define GL_PIXEL_MAP_S_TO_S                                     0x0C71
#define GL_PIXEL_MAP_I_TO_R                                     0x0C72
#define GL_PIXEL_MAP_I_TO_G                                     0x0C73
#define GL_PIXEL_MAP_I_TO_B                                     0x0C74
#define GL_PIXEL_MAP_I_TO_A                                     0x0C75
#define GL_PIXEL_MAP_R_TO_R                                     0x0C76
#define GL_PIXEL_MAP_G_TO_G                                     0x0C77
#define GL_PIXEL_MAP_B_TO_B                                     0x0C78
#define GL_PIXEL_MAP_A_TO_A                                     0x0C79
#define GL_PIXEL_MAP_I_TO_I_SIZE                                0x0CB0
#define GL_PIXEL_MAP_S_TO_S_SIZE                                0x0CB1
#define GL_PIXEL_MAP_I_TO_R_SIZE                                0x0CB2
#define GL_PIXEL_MAP_I_TO_G_SIZE                                0x0CB3
#define GL_PIXEL_MAP_I_TO_B_SIZE                                0x0CB4
#define GL_PIXEL_MAP_I_TO_A_SIZE                                0x0CB5
#define GL_PIXEL_MAP_R_TO_R_SIZE                                0x0CB6
#define GL_PIXEL_MAP_G_TO_G_SIZE                                0x0CB7
#define GL_PIXEL_MAP_B_TO_B_SIZE                                0x0CB8
#define GL_PIXEL_MAP_A_TO_A_SIZE                                0x0CB9
#define GL_UNPACK_SWAP_BYTES                                    0x0CF0
#define GL_UNPACK_LSB_FIRST                                     0x0CF1
#define GL_UNPACK_ROW_LENGTH                                    0x0CF2
#define GL_UNPACK_SKIP_ROWS                                     0x0CF3
#define GL_UNPACK_SKIP_PIXELS                                   0x0CF4
#define GL_UNPACK_ALIGNMENT                                     0x0CF5
#define GL_PACK_SWAP_BYTES                                      0x0D00
#define GL_PACK_LSB_FIRST                                       0x0D01
#define GL_PACK_ROW_LENGTH                                      0x0D02
#define GL_PACK_SKIP_ROWS                                       0x0D03
#define GL_PACK_SKIP_PIXELS                                     0x0D04
#define GL_PACK_ALIGNMENT                                       0x0D05
#define GL_MAP_COLOR                                            0x0D10
#define GL_MAP_STENCIL                                          0x0D11
#define GL_INDEX_SHIFT                                          0x0D12
#define GL_INDEX_OFFSET                                         0x0D13
#define GL_RED_SCALE                                            0x0D14
#define GL_RED_BIAS                                             0x0D15
#define GL_ZOOM_X                                               0x0D16
#define GL_ZOOM_Y                                               0x0D17
#define GL_GREEN_SCALE                                          0x0D18
#define GL_GREEN_BIAS                                           0x0D19
#define GL_BLUE_SCALE                                           0x0D1A
#define GL_BLUE_BIAS                                            0x0D1B
#define GL_ALPHA_SCALE                                          0x0D1C
#define GL_ALPHA_BIAS                                           0x0D1D
#define GL_DEPTH_SCALE                                          0x0D1E
#define GL_DEPTH_BIAS                                           0x0D1F
#define GL_MAX_EVAL_ORDER                                       0x0D30
#define GL_MAX_LIGHTS                                           0x0D31
#define GL_MAX_CLIP_PLANES                                      0x0D32
#define GL_MAX_TEXTURE_SIZE                                     0x0D33
#define GL_MAX_PIXEL_MAP_TABLE                                  0x0D34
#define GL_MAX_ATTRIB_STACK_DEPTH                               0x0D35
#define GL_MAX_MODELVIEW_STACK_DEPTH                            0x0D36
#define GL_MAX_NAME_STACK_DEPTH                                 0x0D37
#define GL_MAX_PROJECTION_STACK_DEPTH                           0x0D38
#define GL_MAX_TEXTURE_STACK_DEPTH                              0x0D39
#define GL_MAX_VIEWPORT_DIMS                                    0x0D3A
#define GL_MAX_CLIENT_ATTRIB_STACK_DEPTH                        0x0D3B
#define GL_SUBPIXEL_BITS                                        0x0D50
#define GL_INDEX_BITS                                           0x0D51
#define GL_RED_BITS                                             0x0D52
#define GL_GREEN_BITS                                           0x0D53
#define GL_BLUE_BITS                                            0x0D54
#define GL_ALPHA_BITS                                           0x0D55
#define GL_DEPTH_BITS                                           0x0D56
#define GL_STENCIL_BITS                                         0x0D57
#define GL_ACCUM_RED_BITS                                       0x0D58
#define GL_ACCUM_GREEN_BITS                                     0x0D59
#define GL_ACCUM_BLUE_BITS                                      0x0D5A
#define GL_ACCUM_ALPHA_BITS                                     0x0D5B
#define GL_NAME_STACK_DEPTH                                     0x0D70
#define GL_AUTO_NORMAL                                          0x0D80
#define GL_MAP1_COLOR_4                                         0x0D90
#define GL_MAP1_INDEX                                           0x0D91
#define GL_MAP1_NORMAL                                          0x0D92
#define GL_MAP1_TEXTURE_COORD_1                                 0x0D93
#define GL_MAP1_TEXTURE_COORD_2                                 0x0D94
#define GL_MAP1_TEXTURE_COORD_3                                 0x0D95
#define GL_MAP1_TEXTURE_COORD_4                                 0x0D96
#define GL_MAP1_VERTEX_3                                        0x0D97
#define GL_MAP1_VERTEX_4                                        0x0D98
#define GL_MAP2_COLOR_4                                         0x0DB0
#define GL_MAP2_INDEX                                           0x0DB1
#define GL_MAP2_NORMAL                                          0x0DB2
#define GL_MAP2_TEXTURE_COORD_1                                 0x0DB3
#define GL_MAP2_TEXTURE_COORD_2                                 0x0DB4
#define GL_MAP2_TEXTURE_COORD_3                                 0x0DB5
#define GL_MAP2_TEXTURE_COORD_4                                 0x0DB6
#define GL_MAP2_VERTEX_3                                        0x0DB7
#define GL_MAP2_VERTEX_4                                        0x0DB8
#define GL_MAP1_GRID_DOMAIN                                     0x0DD0
#define GL_MAP1_GRID_SEGMENTS                                   0x0DD1
#define GL_MAP2_GRID_DOMAIN                                     0x0DD2
#define GL_MAP2_GRID_SEGMENTS                                   0x0DD3
#define GL_TEXTURE_1D                                           0x0DE0
#define GL_TEXTURE_2D                                           0x0DE1
#define GL_FEEDBACK_BUFFER_POINTER                              0x0DF0
#define GL_FEEDBACK_BUFFER_SIZE                                 0x0DF1
#define GL_FEEDBACK_BUFFER_TYPE                                 0x0DF2
#define GL_SELECTION_BUFFER_POINTER                             0x0DF3
#define GL_SELECTION_BUFFER_SIZE                                0x0DF4
#define GL_TEXTURE_WIDTH                                        0x1000
#define GL_TEXTURE_HEIGHT                                       0x1001
#define GL_TEXTURE_INTERNAL_FORMAT                              0x1003
#define GL_TEXTURE_BORDER_COLOR                                 0x1004
#define GL_TEXTURE_BORDER                                       0x1005
#define GL_DONT_CARE                                            0x1100
#define GL_FASTEST                                              0x1101
#define GL_NICEST                                               0x1102
#define GL_LIGHT0                                               0x4000
#define GL_LIGHT1                                               0x4001
#define GL_LIGHT2                                               0x4002
#define GL_LIGHT3                                               0x4003
#define GL_LIGHT4                                               0x4004
#define GL_LIGHT5                                               0x4005
#define GL_LIGHT6                                               0x4006
#define GL_LIGHT7                                               0x4007
#define GL_AMBIENT                                              0x1200
#define GL_DIFFUSE                                              0x1201
#define GL_SPECULAR                                             0x1202
#define GL_POSITION                                             0x1203
#define GL_SPOT_DIRECTION                                       0x1204
#define GL_SPOT_EXPONENT                                        0x1205
#define GL_SPOT_CUTOFF                                          0x1206
#define GL_CONSTANT_ATTENUATION                                 0x1207
#define GL_LINEAR_ATTENUATION                                   0x1208
#define GL_QUADRATIC_ATTENUATION                                0x1209
#define GL_COMPILE                                              0x1300
#define GL_COMPILE_AND_EXECUTE                                  0x1301
#define GL_CLEAR                                                0x1500
#define GL_AND                                                  0x1501
#define GL_AND_REVERSE                                          0x1502
#define GL_COPY                                                 0x1503
#define GL_AND_INVERTED                                         0x1504
#define GL_NOOP                                                 0x1505
#define GL_XOR                                                  0x1506
#define GL_OR                                                   0x1507
#define GL_NOR                                                  0x1508
#define GL_EQUIV                                                0x1509
#define GL_INVERT                                               0x150A
#define GL_OR_REVERSE                                           0x150B
#define GL_COPY_INVERTED                                        0x150C
#define GL_OR_INVERTED                                          0x150D
#define GL_NAND                                                 0x150E
#define GL_SET                                                  0x150F
#define GL_EMISSION                                             0x1600
#define GL_SHININESS                                            0x1601
#define GL_AMBIENT_AND_DIFFUSE                                  0x1602
#define GL_COLOR_INDEXES                                        0x1603
#define GL_MODELVIEW                                            0x1700
#define GL_PROJECTION                                           0x1701
#define GL_TEXTURE                                              0x1702
#define GL_COLOR                                                0x1800
#define GL_DEPTH                                                0x1801
#define GL_STENCIL                                              0x1802
#define GL_COLOR_INDEX                                          0x1900
#define GL_STENCIL_INDEX                                        0x1901
#define GL_DEPTH_COMPONENT                                      0x1902
#define GL_RED                                                  0x1903
#define GL_GREEN                                                0x1904
#define GL_BLUE                                                 0x1905
#define GL_ALPHA                                                0x1906
#define GL_RGB                                                  0x1907
#define GL_RGBA                                                 0x1908
#define GL_LUMINANCE                                            0x1909
#define GL_LUMINANCE_ALPHA                                      0x190A
#define GL_BITMAP                                               0x1A00
#define GL_POINT                                                0x1B00
#define GL_LINE                                                 0x1B01
#define GL_FILL                                                 0x1B02
#define GL_RENDER                                               0x1C00
#define GL_FEEDBACK                                             0x1C01
#define GL_SELECT                                               0x1C02
#define GL_FLAT                                                 0x1D00
#define GL_SMOOTH                                               0x1D01
#define GL_KEEP                                                 0x1E00
#define GL_REPLACE                                              0x1E01
#define GL_INCR                                                 0x1E02
#define GL_DECR                                                 0x1E03
#define GL_VENDOR                                               0x1F00
#define GL_RENDERER                                             0x1F01
#define GL_VERSION                                              0x1F02
#define GL_EXTENSIONS                                           0x1F03
#define GL_S                                                    0x2000
#define GL_T                                                    0x2001
#define GL_R                                                    0x2002
#define GL_Q                                                    0x2003
#define GL_MODULATE                                             0x2100
#define GL_DECAL                                                0x2101
#define GL_TEXTURE_ENV_MODE                                     0x2200
#define GL_TEXTURE_ENV_COLOR                                    0x2201
#define GL_TEXTURE_ENV                                          0x2300
#define GL_EYE_LINEAR                                           0x2400
#define GL_OBJECT_LINEAR                                        0x2401
#define GL_SPHERE_MAP                                           0x2402
#define GL_TEXTURE_GEN_MODE                                     0x2500
#define GL_OBJECT_PLANE                                         0x2501
#define GL_EYE_PLANE                                            0x2502
#define GL_NEAREST                                              0x2600
#define GL_LINEAR                                               0x2601
#define GL_NEAREST_MIPMAP_NEAREST                               0x2700
#define GL_LINEAR_MIPMAP_NEAREST                                0x2701
#define GL_NEAREST_MIPMAP_LINEAR                                0x2702
#define GL_LINEAR_MIPMAP_LINEAR                                 0x2703
#define GL_TEXTURE_MAG_FILTER                                   0x2800
#define GL_TEXTURE_MIN_FILTER                                   0x2801
#define GL_TEXTURE_WRAP_S                                       0x2802
#define GL_TEXTURE_WRAP_T                                       0x2803
#define GL_CLAMP                                                0x2900
#define GL_REPEAT                                               0x2901
#define GL_CLIENT_PIXEL_STORE_BIT                               0x00000001
#define GL_CLIENT_VERTEX_ARRAY_BIT                              0x00000002
#define GL_CLIENT_ALL_ATTRIB_BITS                               0xffffffff
#define GL_POLYGON_OFFSET_FACTOR                                0x8038
#define GL_POLYGON_OFFSET_UNITS                                 0x2A00
#define GL_POLYGON_OFFSET_POINT                                 0x2A01
#define GL_POLYGON_OFFSET_LINE                                  0x2A02
#define GL_POLYGON_OFFSET_FILL                                  0x8037
#define GL_ALPHA4                                               0x803B
#define GL_ALPHA8                                               0x803C
#define GL_ALPHA12                                              0x803D
#define GL_ALPHA16                                              0x803E
#define GL_LUMINANCE4                                           0x803F
#define GL_LUMINANCE8                                           0x8040
#define GL_LUMINANCE12                                          0x8041
#define GL_LUMINANCE16                                          0x8042
#define GL_LUMINANCE4_ALPHA4                                    0x8043
#define GL_LUMINANCE6_ALPHA2                                    0x8044
#define GL_LUMINANCE8_ALPHA8                                    0x8045
#define GL_LUMINANCE12_ALPHA4                                   0x8046
#define GL_LUMINANCE12_ALPHA12                                  0x8047
#define GL_LUMINANCE16_ALPHA16                                  0x8048
#define GL_INTENSITY                                            0x8049
#define GL_INTENSITY4                                           0x804A
#define GL_INTENSITY8                                           0x804B
#define GL_INTENSITY12                                          0x804C
#define GL_INTENSITY16                                          0x804D
#define GL_R3_G3_B2                                             0x2A10
#define GL_RGB4                                                 0x804F
#define GL_RGB5                                                 0x8050
#define GL_RGB8                                                 0x8051
#define GL_RGB10                                                0x8052
#define GL_RGB12                                                0x8053
#define GL_RGB16                                                0x8054
#define GL_RGBA2                                                0x8055
#define GL_RGBA4                                                0x8056
#define GL_RGB5_A1                                              0x8057
#define GL_RGBA8                                                0x8058
#define GL_RGB10_A2                                             0x8059
#define GL_RGBA12                                               0x805A
#define GL_RGBA16                                               0x805B
#define GL_TEXTURE_RED_SIZE                                     0x805C
#define GL_TEXTURE_GREEN_SIZE                                   0x805D
#define GL_TEXTURE_BLUE_SIZE                                    0x805E
#define GL_TEXTURE_ALPHA_SIZE                                   0x805F
#define GL_TEXTURE_LUMINANCE_SIZE                               0x8060
#define GL_TEXTURE_INTENSITY_SIZE                               0x8061
#define GL_PROXY_TEXTURE_1D                                     0x8063
#define GL_PROXY_TEXTURE_2D                                     0x8064
#define GL_TEXTURE_PRIORITY                                     0x8066
#define GL_TEXTURE_RESIDENT                                     0x8067
#define GL_TEXTURE_BINDING_1D                                   0x8068
#define GL_TEXTURE_BINDING_2D                                   0x8069
#define GL_VERTEX_ARRAY                                         0x8074
#define GL_NORMAL_ARRAY                                         0x8075
#define GL_COLOR_ARRAY                                          0x8076
#define GL_INDEX_ARRAY                                          0x8077
#define GL_TEXTURE_COORD_ARRAY                                  0x8078
#define GL_EDGE_FLAG_ARRAY                                      0x8079
#define GL_VERTEX_ARRAY_SIZE                                    0x807A
#define GL_VERTEX_ARRAY_TYPE                                    0x807B
#define GL_VERTEX_ARRAY_STRIDE                                  0x807C
#define GL_NORMAL_ARRAY_TYPE                                    0x807E
#define GL_NORMAL_ARRAY_STRIDE                                  0x807F
#define GL_COLOR_ARRAY_SIZE                                     0x8081
#define GL_COLOR_ARRAY_TYPE                                     0x8082
#define GL_COLOR_ARRAY_STRIDE                                   0x8083
#define GL_INDEX_ARRAY_TYPE                                     0x8085
#define GL_INDEX_ARRAY_STRIDE                                   0x8086
#define GL_TEXTURE_COORD_ARRAY_SIZE                             0x8088
#define GL_TEXTURE_COORD_ARRAY_TYPE                             0x8089
#define GL_TEXTURE_COORD_ARRAY_STRIDE                           0x808A
#define GL_EDGE_FLAG_ARRAY_STRIDE                               0x808C
#define GL_VERTEX_ARRAY_POINTER                                 0x808E
#define GL_NORMAL_ARRAY_POINTER                                 0x808F
#define GL_COLOR_ARRAY_POINTER                                  0x8090
#define GL_INDEX_ARRAY_POINTER                                  0x8091
#define GL_TEXTURE_COORD_ARRAY_POINTER                          0x8092
#define GL_EDGE_FLAG_ARRAY_POINTER                              0x8093
#define GL_V2F                                                  0x2A20
#define GL_V3F                                                  0x2A21
#define GL_C4UB_V2F                                             0x2A22
#define GL_C4UB_V3F                                             0x2A23
#define GL_C3F_V3F                                              0x2A24
#define GL_N3F_V3F                                              0x2A25
#define GL_C4F_N3F_V3F                                          0x2A26
#define GL_T2F_V3F                                              0x2A27
#define GL_T4F_V4F                                              0x2A28
#define GL_T2F_C4UB_V3F                                         0x2A29
#define GL_T2F_C3F_V3F                                          0x2A2A
#define GL_T2F_N3F_V3F                                          0x2A2B
#define GL_T2F_C4F_N3F_V3F                                      0x2A2C
#define GL_T4F_C4F_N3F_V4F                                      0x2A2D
#define GL_LOGIC_OP GL_INDEX_LOGIC_OP
#define GL_TEXTURE_COMPONENTS GL_TEXTURE_INTERNAL_FORMAT

/* functions */
/*typedef void (APIENTRY * glAccumPROC) (GLenum op, GLfloat value);
typedef void (APIENTRY * glAlphaFuncPROC) (GLenum func, GLclampf ref);
typedef GLboolean (APIENTRY * glAreTexturesResidentPROC) (GLsizei n, const GLuint *textures, GLboolean *residences);
typedef void (APIENTRY * glArrayElementPROC) (GLint i);
typedef void (APIENTRY * glBeginPROC) (GLenum mode);
typedef void (APIENTRY * glBindTexturePROC) (GLenum target, GLuint texture);
typedef void (APIENTRY * glBitmapPROC) (GLsizei width, GLsizei height, GLfloat xorig, GLfloat yorig, GLfloat xmove, GLfloat ymove, const GLubyte *bitmap);
typedef void (APIENTRY * glBlendFuncPROC) (GLenum sfactor, GLenum dfactor);
typedef void (APIENTRY * glCallListPROC) (GLuint list);
typedef void (APIENTRY * glCallListsPROC) (GLsizei n, GLenum type, const GLvoid *lists);
typedef void (APIENTRY * glClearPROC) (GLbitfield mask);
typedef void (APIENTRY * glClearAccumPROC) (GLfloat red, GLfloat green, GLfloat blue, GLfloat alpha);
typedef void (APIENTRY * glClearColorPROC) (GLclampf red, GLclampf green, GLclampf blue, GLclampf alpha);
typedef void (APIENTRY * glClearDepthPROC) (GLclampd depth);
typedef void (APIENTRY * glClearIndexPROC) (GLfloat c);
typedef void (APIENTRY * glClearStencilPROC) (GLint s);
typedef void (APIENTRY * glClipPlanePROC) (GLenum plane, const GLdouble *equation);
typedef void (APIENTRY * glColor3bPROC) (GLbyte red, GLbyte green, GLbyte blue);
typedef void (APIENTRY * glColor3bvPROC) (const GLbyte *v);
typedef void (APIENTRY * glColor3dPROC) (GLdouble red, GLdouble green, GLdouble blue);
typedef void (APIENTRY * glColor3dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glColor3fPROC) (GLfloat red, GLfloat green, GLfloat blue);
typedef void (APIENTRY * glColor3fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glColor3iPROC) (GLint red, GLint green, GLint blue);
typedef void (APIENTRY * glColor3ivPROC) (const GLint *v);
typedef void (APIENTRY * glColor3sPROC) (GLshort red, GLshort green, GLshort blue);
typedef void (APIENTRY * glColor3svPROC) (const GLshort *v);
typedef void (APIENTRY * glColor3ubPROC) (GLubyte red, GLubyte green, GLubyte blue);
typedef void (APIENTRY * glColor3ubvPROC) (const GLubyte *v);
typedef void (APIENTRY * glColor3uiPROC) (GLuint red, GLuint green, GLuint blue);
typedef void (APIENTRY * glColor3uivPROC) (const GLuint *v);
typedef void (APIENTRY * glColor3usPROC) (GLushort red, GLushort green, GLushort blue);
typedef void (APIENTRY * glColor3usvPROC) (const GLushort *v);
typedef void (APIENTRY * glColor4bPROC) (GLbyte red, GLbyte green, GLbyte blue, GLbyte alpha);
typedef void (APIENTRY * glColor4bvPROC) (const GLbyte *v);
typedef void (APIENTRY * glColor4dPROC) (GLdouble red, GLdouble green, GLdouble blue, GLdouble alpha);
typedef void (APIENTRY * glColor4dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glColor4fPROC) (GLfloat red, GLfloat green, GLfloat blue, GLfloat alpha);
typedef void (APIENTRY * glColor4fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glColor4iPROC) (GLint red, GLint green, GLint blue, GLint alpha);
typedef void (APIENTRY * glColor4ivPROC) (const GLint *v);
typedef void (APIENTRY * glColor4sPROC) (GLshort red, GLshort green, GLshort blue, GLshort alpha);
typedef void (APIENTRY * glColor4svPROC) (const GLshort *v);
typedef void (APIENTRY * glColor4ubPROC) (GLubyte red, GLubyte green, GLubyte blue, GLubyte alpha);
typedef void (APIENTRY * glColor4ubvPROC) (const GLubyte *v);
typedef void (APIENTRY * glColor4uiPROC) (GLuint red, GLuint green, GLuint blue, GLuint alpha);
typedef void (APIENTRY * glColor4uivPROC) (const GLuint *v);
typedef void (APIENTRY * glColor4usPROC) (GLushort red, GLushort green, GLushort blue, GLushort alpha);
typedef void (APIENTRY * glColor4usvPROC) (const GLushort *v);
typedef void (APIENTRY * glColorMaskPROC) (GLboolean red, GLboolean green, GLboolean blue, GLboolean alpha);
typedef void (APIENTRY * glColorMaterialPROC) (GLenum face, GLenum mode);
typedef void (APIENTRY * glColorPointerPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glCopyPixelsPROC) (GLint x, GLint y, GLsizei width, GLsizei height, GLenum type);
typedef void (APIENTRY * glCopyTexImage1DPROC) (GLenum target, GLint level, GLenum internalFormat, GLint x, GLint y, GLsizei width, GLint border);
typedef void (APIENTRY * glCopyTexImage2DPROC) (GLenum target, GLint level, GLenum internalFormat, GLint x, GLint y, GLsizei width, GLsizei height, GLint border);
typedef void (APIENTRY * glCopyTexSubImage1DPROC) (GLenum target, GLint level, GLint xoffset, GLint x, GLint y, GLsizei width);
typedef void (APIENTRY * glCopyTexSubImage2DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint x, GLint y, GLsizei width, GLsizei height);
typedef void (APIENTRY * glCullFacePROC) (GLenum mode);
typedef void (APIENTRY * glDeleteListsPROC) (GLuint list, GLsizei range);
typedef void (APIENTRY * glDeleteTexturesPROC) (GLsizei n, const GLuint *textures);
typedef void (APIENTRY * glDepthFuncPROC) (GLenum func);
typedef void (APIENTRY * glDepthMaskPROC) (GLboolean flag);
typedef void (APIENTRY * glDepthRangePROC) (GLclampd zNear, GLclampd zFar);
typedef void (APIENTRY * glDisablePROC) (GLenum cap);
typedef void (APIENTRY * glDisableClientStatePROC) (GLenum array);
typedef void (APIENTRY * glDrawArraysPROC) (GLenum mode, GLint first, GLsizei count);
typedef void (APIENTRY * glDrawBufferPROC) (GLenum mode);
typedef void (APIENTRY * glDrawElementsPROC) (GLenum mode, GLsizei count, GLenum type, const GLvoid *indices);
typedef void (APIENTRY * glDrawPixelsPROC) (GLsizei width, GLsizei height, GLenum format, GLenum type, const GLvoid *pixels);
typedef void (APIENTRY * glEdgeFlagPROC) (GLboolean flag);
typedef void (APIENTRY * glEdgeFlagPointerPROC) (GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glEdgeFlagvPROC) (const GLboolean *flag);
typedef void (APIENTRY * glEnablePROC) (GLenum cap);
typedef void (APIENTRY * glEnableClientStatePROC) (GLenum array);
typedef void (APIENTRY * glEndPROC) (void);
typedef void (APIENTRY * glEndListPROC) (void);
typedef void (APIENTRY * glEvalCoord1dPROC) (GLdouble u);
typedef void (APIENTRY * glEvalCoord1dvPROC) (const GLdouble *u);
typedef void (APIENTRY * glEvalCoord1fPROC) (GLfloat u);
typedef void (APIENTRY * glEvalCoord1fvPROC) (const GLfloat *u);
typedef void (APIENTRY * glEvalCoord2dPROC) (GLdouble u, GLdouble v);
typedef void (APIENTRY * glEvalCoord2dvPROC) (const GLdouble *u);
typedef void (APIENTRY * glEvalCoord2fPROC) (GLfloat u, GLfloat v);
typedef void (APIENTRY * glEvalCoord2fvPROC) (const GLfloat *u);
typedef void (APIENTRY * glEvalMesh1PROC) (GLenum mode, GLint i1, GLint i2);
typedef void (APIENTRY * glEvalMesh2PROC) (GLenum mode, GLint i1, GLint i2, GLint j1, GLint j2);
typedef void (APIENTRY * glEvalPoint1PROC) (GLint i);
typedef void (APIENTRY * glEvalPoint2PROC) (GLint i, GLint j);
typedef void (APIENTRY * glFeedbackBufferPROC) (GLsizei size, GLenum type, GLfloat *buffer);
typedef void (APIENTRY * glFinishPROC) (void);
typedef void (APIENTRY * glFlushPROC) (void);
typedef void (APIENTRY * glFogfPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glFogfvPROC) (GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glFogiPROC) (GLenum pname, GLint param);
typedef void (APIENTRY * glFogivPROC) (GLenum pname, const GLint *params);
typedef void (APIENTRY * glFrontFacePROC) (GLenum mode);
typedef void (APIENTRY * glFrustumPROC) (GLdouble left, GLdouble right, GLdouble bottom, GLdouble top, GLdouble zNear, GLdouble zFar);
typedef GLuint (APIENTRY * glGenListsPROC) (GLsizei range);
typedef void (APIENTRY * glGenTexturesPROC) (GLsizei n, GLuint *textures);
typedef void (APIENTRY * glGetBooleanvPROC) (GLenum pname, GLboolean *params);
typedef void (APIENTRY * glGetClipPlanePROC) (GLenum plane, GLdouble *equation);
typedef void (APIENTRY * glGetDoublevPROC) (GLenum pname, GLdouble *params);
typedef GLenum (APIENTRY * glGetErrorPROC) (void);
typedef void (APIENTRY * glGetFloatvPROC) (GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetIntegervPROC) (GLenum pname, GLint *params);
typedef void (APIENTRY * glGetLightfvPROC) (GLenum light, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetLightivPROC) (GLenum light, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetMapdvPROC) (GLenum target, GLenum query, GLdouble *v);
typedef void (APIENTRY * glGetMapfvPROC) (GLenum target, GLenum query, GLfloat *v);
typedef void (APIENTRY * glGetMapivPROC) (GLenum target, GLenum query, GLint *v);
typedef void (APIENTRY * glGetMaterialfvPROC) (GLenum face, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetMaterialivPROC) (GLenum face, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetPixelMapfvPROC) (GLenum map, GLfloat *values);
typedef void (APIENTRY * glGetPixelMapuivPROC) (GLenum map, GLuint *values);
typedef void (APIENTRY * glGetPixelMapusvPROC) (GLenum map, GLushort *values);
typedef void (APIENTRY * glGetPointervPROC) (GLenum pname, GLvoid* *params);
typedef void (APIENTRY * glGetPolygonStipplePROC) (GLubyte *mask);
typedef const GLubyte * (APIENTRY * glGetStringPROC) (GLenum name);
typedef void (APIENTRY * glGetTexEnvfvPROC) (GLenum target, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetTexEnvivPROC) (GLenum target, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetTexGendvPROC) (GLenum coord, GLenum pname, GLdouble *params);
typedef void (APIENTRY * glGetTexGenfvPROC) (GLenum coord, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetTexGenivPROC) (GLenum coord, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetTexImagePROC) (GLenum target, GLint level, GLenum format, GLenum type, GLvoid *pixels);
typedef void (APIENTRY * glGetTexLevelParameterfvPROC) (GLenum target, GLint level, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetTexLevelParameterivPROC) (GLenum target, GLint level, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetTexParameterfvPROC) (GLenum target, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetTexParameterivPROC) (GLenum target, GLenum pname, GLint *params);
typedef void (APIENTRY * glHintPROC) (GLenum target, GLenum mode);
typedef void (APIENTRY * glIndexMaskPROC) (GLuint mask);
typedef void (APIENTRY * glIndexPointerPROC) (GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glIndexdPROC) (GLdouble c);
typedef void (APIENTRY * glIndexdvPROC) (const GLdouble *c);
typedef void (APIENTRY * glIndexfPROC) (GLfloat c);
typedef void (APIENTRY * glIndexfvPROC) (const GLfloat *c);
typedef void (APIENTRY * glIndexiPROC) (GLint c);
typedef void (APIENTRY * glIndexivPROC) (const GLint *c);
typedef void (APIENTRY * glIndexsPROC) (GLshort c);
typedef void (APIENTRY * glIndexsvPROC) (const GLshort *c);
typedef void (APIENTRY * glIndexubPROC) (GLubyte c);
typedef void (APIENTRY * glIndexubvPROC) (const GLubyte *c);
typedef void (APIENTRY * glInitNamesPROC) (void);
typedef void (APIENTRY * glInterleavedArraysPROC) (GLenum format, GLsizei stride, const GLvoid *pointer);
typedef GLboolean (APIENTRY * glIsEnabledPROC) (GLenum cap);
typedef GLboolean (APIENTRY * glIsListPROC) (GLuint list);
typedef GLboolean (APIENTRY * glIsTexturePROC) (GLuint texture);
typedef void (APIENTRY * glLightModelfPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glLightModelfvPROC) (GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glLightModeliPROC) (GLenum pname, GLint param);
typedef void (APIENTRY * glLightModelivPROC) (GLenum pname, const GLint *params);
typedef void (APIENTRY * glLightfPROC) (GLenum light, GLenum pname, GLfloat param);
typedef void (APIENTRY * glLightfvPROC) (GLenum light, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glLightiPROC) (GLenum light, GLenum pname, GLint param);
typedef void (APIENTRY * glLightivPROC) (GLenum light, GLenum pname, const GLint *params);
typedef void (APIENTRY * glLineStipplePROC) (GLint factor, GLushort pattern);
typedef void (APIENTRY * glLineWidthPROC) (GLfloat width);
typedef void (APIENTRY * glListBasePROC) (GLuint base);
typedef void (APIENTRY * glLoadIdentityPROC) (void);
typedef void (APIENTRY * glLoadMatrixdPROC) (const GLdouble *m);
typedef void (APIENTRY * glLoadMatrixfPROC) (const GLfloat *m);
typedef void (APIENTRY * glLoadNamePROC) (GLuint name);
typedef void (APIENTRY * glLogicOpPROC) (GLenum opcode);
typedef void (APIENTRY * glMap1dPROC) (GLenum target, GLdouble u1, GLdouble u2, GLint stride, GLint order, const GLdouble *points);
typedef void (APIENTRY * glMap1fPROC) (GLenum target, GLfloat u1, GLfloat u2, GLint stride, GLint order, const GLfloat *points);
typedef void (APIENTRY * glMap2dPROC) (GLenum target, GLdouble u1, GLdouble u2, GLint ustride, GLint uorder, GLdouble v1, GLdouble v2, GLint vstride, GLint vorder, const GLdouble *points);
typedef void (APIENTRY * glMap2fPROC) (GLenum target, GLfloat u1, GLfloat u2, GLint ustride, GLint uorder, GLfloat v1, GLfloat v2, GLint vstride, GLint vorder, const GLfloat *points);
typedef void (APIENTRY * glMapGrid1dPROC) (GLint un, GLdouble u1, GLdouble u2);
typedef void (APIENTRY * glMapGrid1fPROC) (GLint un, GLfloat u1, GLfloat u2);
typedef void (APIENTRY * glMapGrid2dPROC) (GLint un, GLdouble u1, GLdouble u2, GLint vn, GLdouble v1, GLdouble v2);
typedef void (APIENTRY * glMapGrid2fPROC) (GLint un, GLfloat u1, GLfloat u2, GLint vn, GLfloat v1, GLfloat v2);
typedef void (APIENTRY * glMaterialfPROC) (GLenum face, GLenum pname, GLfloat param);
typedef void (APIENTRY * glMaterialfvPROC) (GLenum face, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glMaterialiPROC) (GLenum face, GLenum pname, GLint param);
typedef void (APIENTRY * glMaterialivPROC) (GLenum face, GLenum pname, const GLint *params);
typedef void (APIENTRY * glMatrixModePROC) (GLenum mode);
typedef void (APIENTRY * glMultMatrixdPROC) (const GLdouble *m);
typedef void (APIENTRY * glMultMatrixfPROC) (const GLfloat *m);
typedef void (APIENTRY * glNewListPROC) (GLuint list, GLenum mode);
typedef void (APIENTRY * glNormal3bPROC) (GLbyte nx, GLbyte ny, GLbyte nz);
typedef void (APIENTRY * glNormal3bvPROC) (const GLbyte *v);
typedef void (APIENTRY * glNormal3dPROC) (GLdouble nx, GLdouble ny, GLdouble nz);
typedef void (APIENTRY * glNormal3dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glNormal3fPROC) (GLfloat nx, GLfloat ny, GLfloat nz);
typedef void (APIENTRY * glNormal3fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glNormal3iPROC) (GLint nx, GLint ny, GLint nz);
typedef void (APIENTRY * glNormal3ivPROC) (const GLint *v);
typedef void (APIENTRY * glNormal3sPROC) (GLshort nx, GLshort ny, GLshort nz);
typedef void (APIENTRY * glNormal3svPROC) (const GLshort *v);
typedef void (APIENTRY * glNormalPointerPROC) (GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glOrthoPROC) (GLdouble left, GLdouble right, GLdouble bottom, GLdouble top, GLdouble zNear, GLdouble zFar);
typedef void (APIENTRY * glPassThroughPROC) (GLfloat token);
typedef void (APIENTRY * glPixelMapfvPROC) (GLenum map, GLsizei mapsize, const GLfloat *values);
typedef void (APIENTRY * glPixelMapuivPROC) (GLenum map, GLsizei mapsize, const GLuint *values);
typedef void (APIENTRY * glPixelMapusvPROC) (GLenum map, GLsizei mapsize, const GLushort *values);
typedef void (APIENTRY * glPixelStorefPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glPixelStoreiPROC) (GLenum pname, GLint param);
typedef void (APIENTRY * glPixelTransferfPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glPixelTransferiPROC) (GLenum pname, GLint param);
typedef void (APIENTRY * glPixelZoomPROC) (GLfloat xfactor, GLfloat yfactor);
typedef void (APIENTRY * glPointSizePROC) (GLfloat size);
typedef void (APIENTRY * glPolygonModePROC) (GLenum face, GLenum mode);
typedef void (APIENTRY * glPolygonOffsetPROC) (GLfloat factor, GLfloat units);
typedef void (APIENTRY * glPolygonStipplePROC) (const GLubyte *mask);
typedef void (APIENTRY * glPopAttribPROC) (void);
typedef void (APIENTRY * glPopClientAttribPROC) (void);
typedef void (APIENTRY * glPopMatrixPROC) (void);
typedef void (APIENTRY * glPopNamePROC) (void);
typedef void (APIENTRY * glPrioritizeTexturesPROC) (GLsizei n, const GLuint *textures, const GLclampf *priorities);
typedef void (APIENTRY * glPushAttribPROC) (GLbitfield mask);
typedef void (APIENTRY * glPushClientAttribPROC) (GLbitfield mask);
typedef void (APIENTRY * glPushMatrixPROC) (void);
typedef void (APIENTRY * glPushNamePROC) (GLuint name);
typedef void (APIENTRY * glRasterPos2dPROC) (GLdouble x, GLdouble y);
typedef void (APIENTRY * glRasterPos2dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glRasterPos2fPROC) (GLfloat x, GLfloat y);
typedef void (APIENTRY * glRasterPos2fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glRasterPos2iPROC) (GLint x, GLint y);
typedef void (APIENTRY * glRasterPos2ivPROC) (const GLint *v);
typedef void (APIENTRY * glRasterPos2sPROC) (GLshort x, GLshort y);
typedef void (APIENTRY * glRasterPos2svPROC) (const GLshort *v);
typedef void (APIENTRY * glRasterPos3dPROC) (GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glRasterPos3dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glRasterPos3fPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glRasterPos3fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glRasterPos3iPROC) (GLint x, GLint y, GLint z);

typedef void (APIENTRY * glRasterPos3ivPROC) (const GLint *v);
typedef void (APIENTRY * glRasterPos3sPROC) (GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY * glRasterPos3svPROC) (const GLshort *v);
typedef void (APIENTRY * glRasterPos4dPROC) (GLdouble x, GLdouble y, GLdouble z, GLdouble w);
typedef void (APIENTRY * glRasterPos4dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glRasterPos4fPROC) (GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glRasterPos4fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glRasterPos4iPROC) (GLint x, GLint y, GLint z, GLint w);
typedef void (APIENTRY * glRasterPos4ivPROC) (const GLint *v);
typedef void (APIENTRY * glRasterPos4sPROC) (GLshort x, GLshort y, GLshort z, GLshort w);
typedef void (APIENTRY * glRasterPos4svPROC) (const GLshort *v);
typedef void (APIENTRY * glReadBufferPROC) (GLenum mode);
typedef void (APIENTRY * glReadPixelsPROC) (GLint x, GLint y, GLsizei width, GLsizei height, GLenum format, GLenum type, GLvoid *pixels);
typedef void (APIENTRY * glRectdPROC) (GLdouble x1, GLdouble y1, GLdouble x2, GLdouble y2);
typedef void (APIENTRY * glRectdvPROC) (const GLdouble *v1, const GLdouble *v2);
typedef void (APIENTRY * glRectfPROC) (GLfloat x1, GLfloat y1, GLfloat x2, GLfloat y2);
typedef void (APIENTRY * glRectfvPROC) (const GLfloat *v1, const GLfloat *v2);
typedef void (APIENTRY * glRectiPROC) (GLint x1, GLint y1, GLint x2, GLint y2);
typedef void (APIENTRY * glRectivPROC) (const GLint *v1, const GLint *v2);
typedef void (APIENTRY * glRectsPROC) (GLshort x1, GLshort y1, GLshort x2, GLshort y2);
typedef void (APIENTRY * glRectsvPROC) (const GLshort *v1, const GLshort *v2);
typedef GLint (APIENTRY * glRenderModePROC) (GLenum mode);
typedef void (APIENTRY * glRotatedPROC) (GLdouble angle, GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glRotatefPROC) (GLfloat angle, GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glScaledPROC) (GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glScalefPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glScissorPROC) (GLint x, GLint y, GLsizei width, GLsizei height);
typedef void (APIENTRY * glSelectBufferPROC) (GLsizei size, GLuint *buffer);
typedef void (APIENTRY * glShadeModelPROC) (GLenum mode);
typedef void (APIENTRY * glStencilFuncPROC) (GLenum func, GLint ref, GLuint mask);
typedef void (APIENTRY * glStencilMaskPROC) (GLuint mask);
typedef void (APIENTRY * glStencilOpPROC) (GLenum fail, GLenum zfail, GLenum zpass);
typedef void (APIENTRY * glTexCoord1dPROC) (GLdouble s);
typedef void (APIENTRY * glTexCoord1dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glTexCoord1fPROC) (GLfloat s);
typedef void (APIENTRY * glTexCoord1fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glTexCoord1iPROC) (GLint s);
typedef void (APIENTRY * glTexCoord1ivPROC) (const GLint *v);
typedef void (APIENTRY * glTexCoord1sPROC) (GLshort s);
typedef void (APIENTRY * glTexCoord1svPROC) (const GLshort *v);
typedef void (APIENTRY * glTexCoord2dPROC) (GLdouble s, GLdouble t);
typedef void (APIENTRY * glTexCoord2dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glTexCoord2fPROC) (GLfloat s, GLfloat t);
typedef void (APIENTRY * glTexCoord2fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glTexCoord2iPROC) (GLint s, GLint t);
typedef void (APIENTRY * glTexCoord2ivPROC) (const GLint *v);
typedef void (APIENTRY * glTexCoord2sPROC) (GLshort s, GLshort t);
typedef void (APIENTRY * glTexCoord2svPROC) (const GLshort *v);
typedef void (APIENTRY * glTexCoord3dPROC) (GLdouble s, GLdouble t, GLdouble r);
typedef void (APIENTRY * glTexCoord3dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glTexCoord3fPROC) (GLfloat s, GLfloat t, GLfloat r);
typedef void (APIENTRY * glTexCoord3fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glTexCoord3iPROC) (GLint s, GLint t, GLint r);
typedef void (APIENTRY * glTexCoord3ivPROC) (const GLint *v);
typedef void (APIENTRY * glTexCoord3sPROC) (GLshort s, GLshort t, GLshort r);
typedef void (APIENTRY * glTexCoord3svPROC) (const GLshort *v);
typedef void (APIENTRY * glTexCoord4dPROC) (GLdouble s, GLdouble t, GLdouble r, GLdouble q);
typedef void (APIENTRY * glTexCoord4dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glTexCoord4fPROC) (GLfloat s, GLfloat t, GLfloat r, GLfloat q);
typedef void (APIENTRY * glTexCoord4fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glTexCoord4iPROC) (GLint s, GLint t, GLint r, GLint q);
typedef void (APIENTRY * glTexCoord4ivPROC) (const GLint *v);
typedef void (APIENTRY * glTexCoord4sPROC) (GLshort s, GLshort t, GLshort r, GLshort q);
typedef void (APIENTRY * glTexCoord4svPROC) (const GLshort *v);
typedef void (APIENTRY * glTexCoordPointerPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glTexEnvfPROC) (GLenum target, GLenum pname, GLfloat param);
typedef void (APIENTRY * glTexEnvfvPROC) (GLenum target, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glTexEnviPROC) (GLenum target, GLenum pname, GLint param);
typedef void (APIENTRY * glTexEnvivPROC) (GLenum target, GLenum pname, const GLint *params);
typedef void (APIENTRY * glTexGendPROC) (GLenum coord, GLenum pname, GLdouble param);
typedef void (APIENTRY * glTexGendvPROC) (GLenum coord, GLenum pname, const GLdouble *params);
typedef void (APIENTRY * glTexGenfPROC) (GLenum coord, GLenum pname, GLfloat param);
typedef void (APIENTRY * glTexGenfvPROC) (GLenum coord, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glTexGeniPROC) (GLenum coord, GLenum pname, GLint param);
typedef void (APIENTRY * glTexGenivPROC) (GLenum coord, GLenum pname, const GLint *params);
typedef void (APIENTRY * glTexImage1DPROC) (GLenum target, GLint level, GLint internalformat, GLsizei width, GLint border, GLenum format, GLenum type, const GLvoid *pixels);
typedef void (APIENTRY * glTexImage2DPROC) (GLenum target, GLint level, GLint internalformat, GLsizei width, GLsizei height, GLint border, GLenum format, GLenum type, const GLvoid *pixels);
typedef void (APIENTRY * glTexParameterfPROC) (GLenum target, GLenum pname, GLfloat param);
typedef void (APIENTRY * glTexParameterfvPROC) (GLenum target, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glTexParameteriPROC) (GLenum target, GLenum pname, GLint param);
typedef void (APIENTRY * glTexParameterivPROC) (GLenum target, GLenum pname, const GLint *params);
typedef void (APIENTRY * glTexSubImage1DPROC) (GLenum target, GLint level, GLint xoffset, GLsizei width, GLenum format, GLenum type, const GLvoid *pixels);
typedef void (APIENTRY * glTexSubImage2DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLenum type, const GLvoid *pixels);
typedef void (APIENTRY * glTranslatedPROC) (GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glTranslatefPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glVertex2dPROC) (GLdouble x, GLdouble y);
typedef void (APIENTRY * glVertex2dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glVertex2fPROC) (GLfloat x, GLfloat y);
typedef void (APIENTRY * glVertex2fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glVertex2iPROC) (GLint x, GLint y);
typedef void (APIENTRY * glVertex2ivPROC) (const GLint *v);
typedef void (APIENTRY * glVertex2sPROC) (GLshort x, GLshort y);
typedef void (APIENTRY * glVertex2svPROC) (const GLshort *v);
typedef void (APIENTRY * glVertex3dPROC) (GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glVertex3dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glVertex3fPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glVertex3fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glVertex3iPROC) (GLint x, GLint y, GLint z);
typedef void (APIENTRY * glVertex3ivPROC) (const GLint *v);
typedef void (APIENTRY * glVertex3sPROC) (GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY * glVertex3svPROC) (const GLshort *v);
typedef void (APIENTRY * glVertex4dPROC) (GLdouble x, GLdouble y, GLdouble z, GLdouble w);
typedef void (APIENTRY * glVertex4dvPROC) (const GLdouble *v);
typedef void (APIENTRY * glVertex4fPROC) (GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glVertex4fvPROC) (const GLfloat *v);
typedef void (APIENTRY * glVertex4iPROC) (GLint x, GLint y, GLint z, GLint w);
typedef void (APIENTRY * glVertex4ivPROC) (const GLint *v);
typedef void (APIENTRY * glVertex4sPROC) (GLshort x, GLshort y, GLshort z, GLshort w);
typedef void (APIENTRY * glVertex4svPROC) (const GLshort *v);
typedef void (APIENTRY * glVertexPointerPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glViewportPROC) (GLint x, GLint y, GLsizei width, GLsizei height);

extern glAccumPROC glAccum;
extern glAlphaFuncPROC glAlphaFunc;
extern glAreTexturesResidentPROC glAreTexturesResident;
extern glArrayElementPROC glArrayElement;
extern glBeginPROC glBegin;
extern glBindTexturePROC glBindTexture;
extern glBitmapPROC glBitmap;
extern glBlendFuncPROC glBlendFunc;
extern glCallListPROC glCallList;
extern glCallListsPROC glCallLists;
extern glClearPROC glClear;
extern glClearAccumPROC glClearAccum;
extern glClearColorPROC glClearColor;
extern glClearDepthPROC glClearDepth;
extern glClearIndexPROC glClearIndex;
extern glClearStencilPROC glClearStencil;
extern glClipPlanePROC glClipPlane;
extern glColor3bPROC glColor3b;
extern glColor3bvPROC glColor3bv;
extern glColor3dPROC glColor3d;
extern glColor3dvPROC glColor3dv;
extern glColor3fPROC glColor3f;
extern glColor3fvPROC glColor3fv;
extern glColor3iPROC glColor3i;
extern glColor3ivPROC glColor3iv;
extern glColor3sPROC glColor3s;
extern glColor3svPROC glColor3sv;
extern glColor3ubPROC glColor3ub;
extern glColor3ubvPROC glColor3ubv;
extern glColor3uiPROC glColor3ui;
extern glColor3uivPROC glColor3uiv;
extern glColor3usPROC glColor3us;
extern glColor3usvPROC glColor3usv;
extern glColor4bPROC glColor4b;
extern glColor4bvPROC glColor4bv;
extern glColor4dPROC glColor4d;
extern glColor4dvPROC glColor4dv;
extern glColor4fPROC glColor4f;
extern glColor4fvPROC glColor4fv;
extern glColor4iPROC glColor4i;
extern glColor4ivPROC glColor4iv;
extern glColor4sPROC glColor4s;
extern glColor4svPROC glColor4sv;
extern glColor4ubPROC glColor4ub;
extern glColor4ubvPROC glColor4ubv;
extern glColor4uiPROC glColor4ui;
extern glColor4uivPROC glColor4uiv;
extern glColor4usPROC glColor4us;
extern glColor4usvPROC glColor4usv;
extern glColorMaskPROC glColorMask;
extern glColorMaterialPROC glColorMaterial;
extern glColorPointerPROC glColorPointer;
extern glCopyPixelsPROC glCopyPixels;
extern glCopyTexImage1DPROC glCopyTexImage1D;
extern glCopyTexImage2DPROC glCopyTexImage2D;
extern glCopyTexSubImage1DPROC glCopyTexSubImage1D;
extern glCopyTexSubImage2DPROC glCopyTexSubImage2D;
extern glCullFacePROC glCullFace;
extern glDeleteListsPROC glDeleteLists;
extern glDeleteTexturesPROC glDeleteTextures;
extern glDepthFuncPROC glDepthFunc;
extern glDepthMaskPROC glDepthMask;
extern glDepthRangePROC glDepthRange;
extern glDisablePROC glDisable;
extern glDisableClientStatePROC glDisableClientState;
extern glDrawArraysPROC glDrawArrays;
extern glDrawBufferPROC glDrawBuffer;
extern glDrawElementsPROC glDrawElements;
extern glDrawPixelsPROC glDrawPixels;
extern glEdgeFlagPROC glEdgeFlag;
extern glEdgeFlagPointerPROC glEdgeFlagPointer;
extern glEdgeFlagvPROC glEdgeFlagv;
extern glEnablePROC glEnable;
extern glEnableClientStatePROC glEnableClientState;
extern glEndPROC glEnd;
extern glEndListPROC glEndList;
extern glEvalCoord1dPROC glEvalCoord1d;
extern glEvalCoord1dvPROC glEvalCoord1dv;
extern glEvalCoord1fPROC glEvalCoord1f;
extern glEvalCoord1fvPROC glEvalCoord1fv;
extern glEvalCoord2dPROC glEvalCoord2d;
extern glEvalCoord2dvPROC glEvalCoord2dv;
extern glEvalCoord2fPROC glEvalCoord2f;
extern glEvalCoord2fvPROC glEvalCoord2fv;
extern glEvalMesh1PROC glEvalMesh1;
extern glEvalMesh2PROC glEvalMesh2;
extern glEvalPoint1PROC glEvalPoint1;
extern glEvalPoint2PROC glEvalPoint2;
extern glFeedbackBufferPROC glFeedbackBuffer;
extern glFinishPROC glFinish;
extern glFlushPROC glFlush;
extern glFogfPROC glFogf;
extern glFogfvPROC glFogfv;
extern glFogiPROC glFogi;
extern glFogivPROC glFogiv;
extern glFrontFacePROC glFrontFace;
extern glFrustumPROC glFrustum;
extern glGenListsPROC glGenLists;
extern glGenTexturesPROC glGenTextures;
extern glGetBooleanvPROC glGetBooleanv;
extern glGetClipPlanePROC glGetClipPlane;
extern glGetDoublevPROC glGetDoublev;
extern glGetErrorPROC glGetError;
extern glGetFloatvPROC glGetFloatv;
extern glGetIntegervPROC glGetIntegerv;
extern glGetLightfvPROC glGetLightfv;
extern glGetLightivPROC glGetLightiv;
extern glGetMapdvPROC glGetMapdv;
extern glGetMapfvPROC glGetMapfv;
extern glGetMapivPROC glGetMapiv;
extern glGetMaterialfvPROC glGetMaterialfv;
extern glGetMaterialivPROC glGetMaterialiv;
extern glGetPixelMapfvPROC glGetPixelMapfv;
extern glGetPixelMapuivPROC glGetPixelMapuiv;
extern glGetPixelMapusvPROC glGetPixelMapusv;
extern glGetPointervPROC glGetPointerv;
extern glGetPolygonStipplePROC glGetPolygonStipple;
extern glGetStringPROC glGetString;
extern glGetTexEnvfvPROC glGetTexEnvfv;
extern glGetTexEnvivPROC glGetTexEnviv;
extern glGetTexGendvPROC glGetTexGendv;
extern glGetTexGenfvPROC glGetTexGenfv;
extern glGetTexGenivPROC glGetTexGeniv;
extern glGetTexImagePROC glGetTexImage;
extern glGetTexLevelParameterfvPROC glGetTexLevelParameterfv;
extern glGetTexLevelParameterivPROC glGetTexLevelParameteriv;
extern glGetTexParameterfvPROC glGetTexParameterfv;
extern glGetTexParameterivPROC glGetTexParameteriv;
extern glHintPROC glHint;
extern glIndexMaskPROC glIndexMask;
extern glIndexPointerPROC glIndexPointer;
extern glIndexdPROC glIndexd;
extern glIndexdvPROC glIndexdv;
extern glIndexfPROC glIndexf;
extern glIndexfvPROC glIndexfv;
extern glIndexiPROC glIndexi;
extern glIndexivPROC glIndexiv;
extern glIndexsPROC glIndexs;
extern glIndexsvPROC glIndexsv;
extern glIndexubPROC glIndexub;
extern glIndexubvPROC glIndexubv;
extern glInitNamesPROC glInitNames;
extern glInterleavedArraysPROC glInterleavedArrays;
extern glIsEnabledPROC glIsEnabled;
extern glIsListPROC glIsList;
extern glIsTexturePROC glIsTexture;
extern glLightModelfPROC glLightModelf;
extern glLightModelfvPROC glLightModelfv;
extern glLightModeliPROC glLightModeli;
extern glLightModelivPROC glLightModeliv;
extern glLightfPROC glLightf;
extern glLightfvPROC glLightfv;
extern glLightiPROC glLighti;
extern glLightivPROC glLightiv;
extern glLineStipplePROC glLineStipple;
extern glLineWidthPROC glLineWidth;
extern glListBasePROC glListBase;
extern glLoadIdentityPROC glLoadIdentity;
extern glLoadMatrixdPROC glLoadMatrixd;
extern glLoadMatrixfPROC glLoadMatrixf;
extern glLoadNamePROC glLoadName;
extern glLogicOpPROC glLogicOp;
extern glMap1dPROC glMap1d;
extern glMap1fPROC glMap1f;
extern glMap2dPROC glMap2d;
extern glMap2fPROC glMap2f;
extern glMapGrid1dPROC glMapGrid1d;
extern glMapGrid1fPROC glMapGrid1f;
extern glMapGrid2dPROC glMapGrid2d;
extern glMapGrid2fPROC glMapGrid2f;
extern glMaterialfPROC glMaterialf;
extern glMaterialfvPROC glMaterialfv;
extern glMaterialiPROC glMateriali;
extern glMaterialivPROC glMaterialiv;
extern glMatrixModePROC glMatrixMode;
extern glMultMatrixdPROC glMultMatrixd;
extern glMultMatrixfPROC glMultMatrixf;
extern glNewListPROC glNewList;
extern glNormal3bPROC glNormal3b;
extern glNormal3bvPROC glNormal3bv;
extern glNormal3dPROC glNormal3d;
extern glNormal3dvPROC glNormal3dv;
extern glNormal3fPROC glNormal3f;
extern glNormal3fvPROC glNormal3fv;
extern glNormal3iPROC glNormal3i;
extern glNormal3ivPROC glNormal3iv;
extern glNormal3sPROC glNormal3s;
extern glNormal3svPROC glNormal3sv;
extern glNormalPointerPROC glNormalPointer;
extern glOrthoPROC glOrtho;
extern glPassThroughPROC glPassThrough;
extern glPixelMapfvPROC glPixelMapfv;
extern glPixelMapuivPROC glPixelMapuiv;
extern glPixelMapusvPROC glPixelMapusv;
extern glPixelStorefPROC glPixelStoref;
extern glPixelStoreiPROC glPixelStorei;
extern glPixelTransferfPROC glPixelTransferf;
extern glPixelTransferiPROC glPixelTransferi;
extern glPixelZoomPROC glPixelZoom;
extern glPointSizePROC glPointSize;
extern glPolygonModePROC glPolygonMode;
extern glPolygonOffsetPROC glPolygonOffset;
extern glPolygonStipplePROC glPolygonStipple;
extern glPopAttribPROC glPopAttrib;
extern glPopClientAttribPROC glPopClientAttrib;
extern glPopMatrixPROC glPopMatrix;
extern glPopNamePROC glPopName;
extern glPrioritizeTexturesPROC glPrioritizeTextures;
extern glPushAttribPROC glPushAttrib;
extern glPushClientAttribPROC glPushClientAttrib;
extern glPushMatrixPROC glPushMatrix;
extern glPushNamePROC glPushName;
extern glRasterPos2dPROC glRasterPos2d;
extern glRasterPos2dvPROC glRasterPos2dv;
extern glRasterPos2fPROC glRasterPos2f;
extern glRasterPos2fvPROC glRasterPos2fv;
extern glRasterPos2iPROC glRasterPos2i;
extern glRasterPos2ivPROC glRasterPos2iv;
extern glRasterPos2sPROC glRasterPos2s;
extern glRasterPos2svPROC glRasterPos2sv;
extern glRasterPos3dPROC glRasterPos3d;
extern glRasterPos3dvPROC glRasterPos3dv;
extern glRasterPos3fPROC glRasterPos3f;
extern glRasterPos3fvPROC glRasterPos3fv;
extern glRasterPos3iPROC glRasterPos3i;

extern glRasterPos3ivPROC glRasterPos3iv;
extern glRasterPos3sPROC glRasterPos3s;
extern glRasterPos3svPROC glRasterPos3sv;
extern glRasterPos4dPROC glRasterPos4d;
extern glRasterPos4dvPROC glRasterPos4dv;
extern glRasterPos4fPROC glRasterPos4f;
extern glRasterPos4fvPROC glRasterPos4fv;
extern glRasterPos4iPROC glRasterPos4i;
extern glRasterPos4ivPROC glRasterPos4iv;
extern glRasterPos4sPROC glRasterPos4s;
extern glRasterPos4svPROC glRasterPos4sv;
extern glReadBufferPROC glReadBuffer;
extern glReadPixelsPROC glReadPixels;
extern glRectdPROC glRectd;
extern glRectdvPROC glRectdv;
extern glRectfPROC glRectf;
extern glRectfvPROC glRectfv;
extern glRectiPROC glRecti;
extern glRectivPROC glRectiv;
extern glRectsPROC glRects;
extern glRectsvPROC glRectsv;
extern glRenderModePROC glRenderMode;
extern glRotatedPROC glRotated;
extern glRotatefPROC glRotatef;
extern glScaledPROC glScaled;
extern glScalefPROC glScalef;
extern glScissorPROC glScissor;
extern glSelectBufferPROC glSelectBuffer;
extern glShadeModelPROC glShadeModel;
extern glStencilFuncPROC glStencilFunc;
extern glStencilMaskPROC glStencilMask;
extern glStencilOpPROC glStencilOp;
extern glTexCoord1dPROC glTexCoord1d;
extern glTexCoord1dvPROC glTexCoord1dv;
extern glTexCoord1fPROC glTexCoord1f;
extern glTexCoord1fvPROC glTexCoord1fv;
extern glTexCoord1iPROC glTexCoord1i;
extern glTexCoord1ivPROC glTexCoord1iv;
extern glTexCoord1sPROC glTexCoord1s;
extern glTexCoord1svPROC glTexCoord1sv;
extern glTexCoord2dPROC glTexCoord2d;
extern glTexCoord2dvPROC glTexCoord2dv;
extern glTexCoord2fPROC glTexCoord2f;
extern glTexCoord2fvPROC glTexCoord2fv;
extern glTexCoord2iPROC glTexCoord2i;
extern glTexCoord2ivPROC glTexCoord2iv;
extern glTexCoord2sPROC glTexCoord2s;
extern glTexCoord2svPROC glTexCoord2sv;
extern glTexCoord3dPROC glTexCoord3d;
extern glTexCoord3dvPROC glTexCoord3dv;
extern glTexCoord3fPROC glTexCoord3f;
extern glTexCoord3fvPROC glTexCoord3fv;
extern glTexCoord3iPROC glTexCoord3i;
extern glTexCoord3ivPROC glTexCoord3iv;
extern glTexCoord3sPROC glTexCoord3s;
extern glTexCoord3svPROC glTexCoord3sv;
extern glTexCoord4dPROC glTexCoord4d;
extern glTexCoord4dvPROC glTexCoord4dv;
extern glTexCoord4fPROC glTexCoord4f;
extern glTexCoord4fvPROC glTexCoord4fv;
extern glTexCoord4iPROC glTexCoord4i;
extern glTexCoord4ivPROC glTexCoord4iv;
extern glTexCoord4sPROC glTexCoord4s;
extern glTexCoord4svPROC glTexCoord4sv;
extern glTexCoordPointerPROC glTexCoordPointer;
extern glTexEnvfPROC glTexEnvf;
extern glTexEnvfvPROC glTexEnvfv;
extern glTexEnviPROC glTexEnvi;
extern glTexEnvivPROC glTexEnviv;
extern glTexGendPROC glTexGend;
extern glTexGendvPROC glTexGendv;
extern glTexGenfPROC glTexGenf;
extern glTexGenfvPROC glTexGenfv;
extern glTexGeniPROC glTexGeni;
extern glTexGenivPROC glTexGeniv;
extern glTexImage1DPROC glTexImage1D;
extern glTexImage2DPROC glTexImage2D;
extern glTexParameterfPROC glTexParameterf;
extern glTexParameterfvPROC glTexParameterfv;
extern glTexParameteriPROC glTexParameteri;
extern glTexParameterivPROC glTexParameteriv;
extern glTexSubImage1DPROC glTexSubImage1D;
extern glTexSubImage2DPROC glTexSubImage2D;
extern glTranslatedPROC glTranslated;
extern glTranslatefPROC glTranslatef;
extern glVertex2dPROC glVertex2d;
extern glVertex2dvPROC glVertex2dv;
extern glVertex2fPROC glVertex2f;
extern glVertex2fvPROC glVertex2fv;
extern glVertex2iPROC glVertex2i;
extern glVertex2ivPROC glVertex2iv;
extern glVertex2sPROC glVertex2s;
extern glVertex2svPROC glVertex2sv;
extern glVertex3dPROC glVertex3d;
extern glVertex3dvPROC glVertex3dv;
extern glVertex3fPROC glVertex3f;
extern glVertex3fvPROC glVertex3fv;
extern glVertex3iPROC glVertex3i;
extern glVertex3ivPROC glVertex3iv;
extern glVertex3sPROC glVertex3s;
extern glVertex3svPROC glVertex3sv;
extern glVertex4dPROC glVertex4d;
extern glVertex4dvPROC glVertex4dv;
extern glVertex4fPROC glVertex4f;
extern glVertex4fvPROC glVertex4fv;
extern glVertex4iPROC glVertex4i;
extern glVertex4ivPROC glVertex4iv;
extern glVertex4sPROC glVertex4s;
extern glVertex4svPROC glVertex4sv;
extern glVertexPointerPROC glVertexPointer;
extern glViewportPROC glViewport;
*/
/* OpenGL 1.2 */

#define GL_RESCALE_NORMAL                                       0x803A
#define GL_CLAMP_TO_EDGE                                        0x812F
#define GL_MAX_ELEMENTS_VERTICES                                0x80E8
#define GL_MAX_ELEMENTS_INDICES                                 0x80E9
#define GL_BGR                                                  0x80E0
#define GL_BGRA                                                 0x80E1
#define GL_UNSIGNED_BYTE_3_3_2                                  0x8032
#define GL_UNSIGNED_BYTE_2_3_3_REV                              0x8362
#define GL_UNSIGNED_SHORT_5_6_5                                 0x8363
#define GL_UNSIGNED_SHORT_5_6_5_REV                             0x8364
#define GL_UNSIGNED_SHORT_4_4_4_4                               0x8033
#define GL_UNSIGNED_SHORT_4_4_4_4_REV                           0x8365
#define GL_UNSIGNED_SHORT_5_5_5_1                               0x8034
#define GL_UNSIGNED_SHORT_1_5_5_5_REV                           0x8366
#define GL_UNSIGNED_INT_8_8_8_8                                 0x8035
#define GL_UNSIGNED_INT_8_8_8_8_REV                             0x8367
#define GL_UNSIGNED_INT_10_10_10_2                              0x8036
#define GL_UNSIGNED_INT_2_10_10_10_REV                          0x8368
#define GL_LIGHT_MODEL_COLOR_CONTROL                            0x81F8
#define GL_SINGLE_COLOR                                         0x81F9
#define GL_SEPARATE_SPECULAR_COLOR                              0x81FA
#define GL_TEXTURE_MIN_LOD                                      0x813A
#define GL_TEXTURE_MAX_LOD                                      0x813B
#define GL_TEXTURE_BASE_LEVEL                                   0x813C
#define GL_TEXTURE_MAX_LEVEL                                    0x813D
#define GL_SMOOTH_POINT_SIZE_RANGE                              0x0B12
#define GL_SMOOTH_POINT_SIZE_GRANULARITY                        0x0B13
#define GL_SMOOTH_LINE_WIDTH_RANGE                              0x0B22
#define GL_SMOOTH_LINE_WIDTH_GRANULARITY                        0x0B23
#define GL_ALIASED_POINT_SIZE_RANGE                             0x846D
#define GL_ALIASED_LINE_WIDTH_RANGE                             0x846E
#define GL_PACK_SKIP_IMAGES                                     0x806B
#define GL_PACK_IMAGE_HEIGHT                                    0x806C
#define GL_UNPACK_SKIP_IMAGES                                   0x806D
#define GL_UNPACK_IMAGE_HEIGHT                                  0x806E
#define GL_TEXTURE_3D                                           0x806F
#define GL_PROXY_TEXTURE_3D                                     0x8070
#define GL_TEXTURE_DEPTH                                        0x8071
#define GL_TEXTURE_WRAP_R                                       0x8072
#define GL_MAX_3D_TEXTURE_SIZE                                  0x8073
#define GL_TEXTURE_BINDING_3D                                   0x806A
#define GL_COLOR_TABLE                                          0x80D0
#define GL_POST_CONVOLUTION_COLOR_TABLE                         0x80D1
#define GL_POST_COLOR_MATRIX_COLOR_TABLE                        0x80D2
#define GL_PROXY_COLOR_TABLE                                    0x80D3
#define GL_PROXY_POST_CONVOLUTION_COLOR_TABLE                   0x80D4
#define GL_PROXY_POST_COLOR_MATRIX_COLOR_TABLE                  0x80D5
#define GL_COLOR_TABLE_SCALE                                    0x80D6
#define GL_COLOR_TABLE_BIAS                                     0x80D7
#define GL_COLOR_TABLE_FORMAT                                   0x80D8
#define GL_COLOR_TABLE_WIDTH                                    0x80D9
#define GL_COLOR_TABLE_RED_SIZE                                 0x80DA
#define GL_COLOR_TABLE_GREEN_SIZE                               0x80DB
#define GL_COLOR_TABLE_BLUE_SIZE                                0x80DC
#define GL_COLOR_TABLE_ALPHA_SIZE                               0x80DD
#define GL_COLOR_TABLE_LUMINANCE_SIZE                           0x80DE
#define GL_COLOR_TABLE_INTENSITY_SIZE                           0x80DF
#define GL_CONVOLUTION_1D                                       0x8010
#define GL_CONVOLUTION_2D                                       0x8011
#define GL_SEPARABLE_2D                                         0x8012
#define GL_CONVOLUTION_BORDER_MODE                              0x8013
#define GL_CONVOLUTION_FILTER_SCALE                             0x8014
#define GL_CONVOLUTION_FILTER_BIAS                              0x8015
#define GL_REDUCE                                               0x8016
#define GL_CONVOLUTION_FORMAT                                   0x8017
#define GL_CONVOLUTION_WIDTH                                    0x8018
#define GL_CONVOLUTION_HEIGHT                                   0x8019
#define GL_MAX_CONVOLUTION_WIDTH                                0x801A
#define GL_MAX_CONVOLUTION_HEIGHT                               0x801B
#define GL_POST_CONVOLUTION_RED_SCALE                           0x801C
#define GL_POST_CONVOLUTION_GREEN_SCALE                         0x801D
#define GL_POST_CONVOLUTION_BLUE_SCALE                          0x801E
#define GL_POST_CONVOLUTION_ALPHA_SCALE                         0x801F
#define GL_POST_CONVOLUTION_RED_BIAS                            0x8020
#define GL_POST_CONVOLUTION_GREEN_BIAS                          0x8021
#define GL_POST_CONVOLUTION_BLUE_BIAS                           0x8022
#define GL_POST_CONVOLUTION_ALPHA_BIAS                          0x8023
#define GL_CONSTANT_BORDER                                      0x8151
#define GL_REPLICATE_BORDER                                     0x8153
#define GL_CONVOLUTION_BORDER_COLOR                             0x8154
#define GL_COLOR_MATRIX                                         0x80B1
#define GL_COLOR_MATRIX_STACK_DEPTH                             0x80B2
#define GL_MAX_COLOR_MATRIX_STACK_DEPTH                         0x80B3
#define GL_POST_COLOR_MATRIX_RED_SCALE                          0x80B4
#define GL_POST_COLOR_MATRIX_GREEN_SCALE                        0x80B5
#define GL_POST_COLOR_MATRIX_BLUE_SCALE                         0x80B6
#define GL_POST_COLOR_MATRIX_ALPHA_SCALE                        0x80B7
#define GL_POST_COLOR_MATRIX_RED_BIAS                           0x80B8
#define GL_POST_COLOR_MATRIX_GREEN_BIAS                         0x80B9
#define GL_POST_COLOR_MATRIX_BLUE_BIAS                          0x80BA
#define GL_POST_COLOR_MATRIX_ALPHA_BIAS                         0x80BB
#define GL_HISTOGRAM                                            0x8024
#define GL_PROXY_HISTOGRAM                                      0x8025
#define GL_HISTOGRAM_WIDTH                                      0x8026
#define GL_HISTOGRAM_FORMAT                                     0x8027
#define GL_HISTOGRAM_RED_SIZE                                   0x8028
#define GL_HISTOGRAM_GREEN_SIZE                                 0x8029
#define GL_HISTOGRAM_BLUE_SIZE                                  0x802A
#define GL_HISTOGRAM_ALPHA_SIZE                                 0x802B
#define GL_HISTOGRAM_LUMINANCE_SIZE                             0x802C
#define GL_HISTOGRAM_SINK                                       0x802D
#define GL_MINMAX                                               0x802E
#define GL_MINMAX_FORMAT                                        0x802F
#define GL_MINMAX_SINK                                          0x8030
#define GL_TABLE_TOO_LARGE                                      0x8031
#define GL_BLEND_EQUATION                                       0x8009
#define GL_MIN                                                  0x8007
#define GL_MAX                                                  0x8008
#define GL_FUNC_ADD                                             0x8006
#define GL_FUNC_SUBTRACT                                        0x800A
#define GL_FUNC_REVERSE_SUBTRACT                                0x800B
#define GL_BLEND_COLOR                                          0x8005
#define GL_CONSTANT_COLOR                                       0x8001
#define GL_ONE_MINUS_CONSTANT_COLOR                             0x8002
#define GL_CONSTANT_ALPHA                                       0x8003
#define GL_ONE_MINUS_CONSTANT_ALPHA                             0x8004

/* OpenGL 1.3 */

#define GL_TEXTURE0                                             0x84C0
#define GL_TEXTURE1                                             0x84C1
#define GL_TEXTURE2                                             0x84C2
#define GL_TEXTURE3                                             0x84C3
#define GL_TEXTURE4                                             0x84C4
#define GL_TEXTURE5                                             0x84C5
#define GL_TEXTURE6                                             0x84C6
#define GL_TEXTURE7                                             0x84C7
#define GL_TEXTURE8                                             0x84C8
#define GL_TEXTURE9                                             0x84C9
#define GL_TEXTURE10                                            0x84CA
#define GL_TEXTURE11                                            0x84CB
#define GL_TEXTURE12                                            0x84CC
#define GL_TEXTURE13                                            0x84CD
#define GL_TEXTURE14                                            0x84CE
#define GL_TEXTURE15                                            0x84CF
#define GL_TEXTURE16                                            0x84D0
#define GL_TEXTURE17                                            0x84D1
#define GL_TEXTURE18                                            0x84D2
#define GL_TEXTURE19                                            0x84D3
#define GL_TEXTURE20                                            0x84D4
#define GL_TEXTURE21                                            0x84D5
#define GL_TEXTURE22                                            0x84D6
#define GL_TEXTURE23                                            0x84D7
#define GL_TEXTURE24                                            0x84D8
#define GL_TEXTURE25                                            0x84D9
#define GL_TEXTURE26                                            0x84DA
#define GL_TEXTURE27                                            0x84DB
#define GL_TEXTURE28                                            0x84DC
#define GL_TEXTURE29                                            0x84DD
#define GL_TEXTURE30                                            0x84DE
#define GL_TEXTURE31                                            0x84DF
#define GL_ACTIVE_TEXTURE                                       0x84E0
#define GL_CLIENT_ACTIVE_TEXTURE                                0x84E1
#define GL_MAX_TEXTURE_UNITS                                    0x84E2

#define GL_NORMAL_MAP                                           0x8511
#define GL_REFLECTION_MAP                                       0x8512
#define GL_TEXTURE_CUBE_MAP                                     0x8513
#define GL_TEXTURE_BINDING_CUBE_MAP                             0x8514
#define GL_TEXTURE_CUBE_MAP_POSITIVE_X                          0x8515
#define GL_TEXTURE_CUBE_MAP_NEGATIVE_X                          0x8516
#define GL_TEXTURE_CUBE_MAP_POSITIVE_Y                          0x8517
#define GL_TEXTURE_CUBE_MAP_NEGATIVE_Y                          0x8518
#define GL_TEXTURE_CUBE_MAP_POSITIVE_Z                          0x8519
#define GL_TEXTURE_CUBE_MAP_NEGATIVE_Z                          0x851A
#define GL_PROXY_TEXTURE_CUBE_MAP                               0x851B
#define GL_MAX_CUBE_MAP_TEXTURE_SIZE                            0x851C

#define GL_COMPRESSED_ALPHA                                     0x84E9
#define GL_COMPRESSED_LUMINANCE                                 0x84EA
#define GL_COMPRESSED_LUMINANCE_ALPHA                           0x84EB
#define GL_COMPRESSED_INTENSITY                                 0x84EC
#define GL_COMPRESSED_RGB                                       0x84ED
#define GL_COMPRESSED_RGBA                                      0x84EE
#define GL_TEXTURE_COMPRESSION_HINT                             0x84EF
#define GL_TEXTURE_COMPRESSED_IMAGE_SIZE                        0x86A0
#define GL_TEXTURE_COMPRESSED                                   0x86A1
#define GL_NUM_COMPRESSED_TEXTURE_FORMATS                       0x86A2
#define GL_COMPRESSED_TEXTURE_FORMATS                           0x86A3

#define GL_MULTISAMPLE                                          0x809D
#define GL_SAMPLE_ALPHA_TO_COVERAGE                             0x809E
#define GL_SAMPLE_ALPHA_TO_ONE                                  0x809F
#define GL_SAMPLE_COVERAGE                                      0x80A0
#define GL_SAMPLE_BUFFERS                                       0x80A8
#define GL_SAMPLES                                              0x80A9
#define GL_SAMPLE_COVERAGE_VALUE                                0x80AA
#define GL_SAMPLE_COVERAGE_INVERT                               0x80AB
#define GL_MULTISAMPLE_BIT                                      0x20000000

#define GL_TRANSPOSE_MODELVIEW_MATRIX                           0x84E3
#define GL_TRANSPOSE_PROJECTION_MATRIX                          0x84E4
#define GL_TRANSPOSE_TEXTURE_MATRIX                             0x84E5
#define GL_TRANSPOSE_COLOR_MATRIX                               0x84E6

#define GL_COMBINE                                              0x8570
#define GL_COMBINE_RGB                                          0x8571
#define GL_COMBINE_ALPHA                                        0x8572
#define GL_SOURCE0_RGB                                          0x8580
#define GL_SOURCE1_RGB                                          0x8581
#define GL_SOURCE2_RGB                                          0x8582
#define GL_SOURCE0_ALPHA                                        0x8588
#define GL_SOURCE1_ALPHA                                        0x8589
#define GL_SOURCE2_ALPHA                                        0x858A
#define GL_OPERAND0_RGB                                         0x8590
#define GL_OPERAND1_RGB                                         0x8591
#define GL_OPERAND2_RGB                                         0x8592
#define GL_OPERAND0_ALPHA                                       0x8598
#define GL_OPERAND1_ALPHA                                       0x8599
#define GL_OPERAND2_ALPHA                                       0x859A
#define GL_RGB_SCALE                                            0x8573
#define GL_ADD_SIGNED                                           0x8574
#define GL_INTERPOLATE                                          0x8575
#define GL_SUBTRACT                                             0x84E7
#define GL_CONSTANT                                             0x8576
#define GL_PRIMARY_COLOR                                        0x8577
#define GL_PREVIOUS                                             0x8578
#define GL_DOT3_RGB                                             0x86AE
#define GL_DOT3_RGBA                                            0x86AF
#define GL_CLAMP_TO_BORDER                                      0x812D

/* OpenGL 1.4 */

#define GL_GENERATE_MIPMAP                                      0x8191
#define GL_GENERATE_MIPMAP_HINT                                 0x8192
#define GL_DEPTH_COMPONENT16                                    0x81A5
#define GL_DEPTH_COMPONENT24                                    0x81A6
#define GL_DEPTH_COMPONENT32                                    0x81A7
#define GL_TEXTURE_DEPTH_SIZE                                   0x884A
#define GL_DEPTH_TEXTURE_MODE                                   0x884B
#define GL_TEXTURE_COMPARE_MODE                                 0x884C
#define GL_TEXTURE_COMPARE_FUNC                                 0x884D
#define GL_COMPARE_R_TO_TEXTURE                                 0x884E
#define GL_FOG_COORDINATE_SOURCE                                0x8450
#define GL_FOG_COORDINATE                                       0x8451
#define GL_FRAGMENT_DEPTH                                       0x8452
#define GL_CURRENT_FOG_COORDINATE                               0x8453
#define GL_FOG_COORDINATE_ARRAY_TYPE                            0x8454
#define GL_FOG_COORDINATE_ARRAY_STRIDE                          0x8455
#define GL_FOG_COORDINATE_ARRAY_POINTER                         0x8456
#define GL_FOG_COORDINATE_ARRAY                                 0x8457
#define GL_POINT_SIZE_MIN                                       0x8126
#define GL_POINT_SIZE_MAX                                       0x8127
#define GL_POINT_FADE_THRESHOLD_SIZE                            0x8128
#define GL_POINT_DISTANCE_ATTENUATION                           0x8129
#define GL_COLOR_SUM                                            0x8458
#define GL_CURRENT_SECONDARY_COLOR                              0x8459
#define GL_SECONDARY_COLOR_ARRAY_SIZE                           0x845A
#define GL_SECONDARY_COLOR_ARRAY_TYPE                           0x845B
#define GL_SECONDARY_COLOR_ARRAY_STRIDE                         0x845C
#define GL_SECONDARY_COLOR_ARRAY_POINTER                        0x845D
#define GL_SECONDARY_COLOR_ARRAY                                0x845E
#define GL_BLEND_DST_RGB                                        0x80C8
#define GL_BLEND_SRC_RGB                                        0x80C9
#define GL_BLEND_DST_ALPHA                                      0x80CA
#define GL_BLEND_SRC_ALPHA                                      0x80CB
#define GL_INCR_WRAP                                            0x8507
#define GL_DECR_WRAP                                            0x8508
#define GL_TEXTURE_FILTER_CONTROL                               0x8500
#define GL_TEXTURE_LOD_BIAS                                     0x8501
#define GL_MAX_TEXTURE_LOD_BIAS                                 0x84FD
#define GL_GL_MIRRORED_REPEAT                                   0x8370

/*-------------------------------------------------------------------*/
/*------------EXTENSIONS---------------------------------------------*/
/*-------------------------------------------------------------------*/

/*-------------------------------------------------------------------*/
/*------------ARB_CUBE_MAP-------------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_NORMAL_MAP_ARB                                       0x8511
#define GL_REFLECTION_MAP_ARB                                   0x8512
#define GL_TEXTURE_CUBE_MAP_ARB                                 0x8513
#define GL_TEXTURE_BINDING_CUBE_MAP_ARB                         0x8514
#define GL_TEXTURE_CUBE_MAP_POSITIVE_X_ARB                      0x8515
#define GL_TEXTURE_CUBE_MAP_NEGATIVE_X_ARB                      0x8516
#define GL_TEXTURE_CUBE_MAP_POSITIVE_Y_ARB                      0x8517
#define GL_TEXTURE_CUBE_MAP_NEGATIVE_Y_ARB                      0x8518
#define GL_TEXTURE_CUBE_MAP_POSITIVE_Z_ARB                      0x8519
#define GL_TEXTURE_CUBE_MAP_NEGATIVE_Z_ARB                      0x851A
#define GL_PROXY_TEXTURE_CUBE_MAP_ARB                           0x851B
#define GL_MAX_CUBE_MAP_TEXTURE_SIZE_ARB                        0x851C

/*-------------------------------------------------------------------*/
/*------------SGIX_SHADOW--------------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_TEXTURE_COMPARE_SGIX                                  0x819A
#define GL_TEXTURE_COMPARE_OPERATOR_SGIX                         0x819B
#define GL_TEXTURE_LEQUAL_R_SGIX                                 0x819C
#define GL_TEXTURE_GEQUAL_R_SGIX                                 0x819D

/*-------------------------------------------------------------------*/
/*------------SGIX_DEPTH_TEXTURE-------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_DEPTH_COMPONENT16_SGIX                                0x81A5
#define GL_DEPTH_COMPONENT24_SGIX                                0x81A6
#define GL_DEPTH_COMPONENT32_SGIX                                0x81A7

/*-------------------------------------------------------------------*/
/*------------ARB_TEXTURE_ENV_COMBINE--------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_COMBINE_ARB                                          0x8570
#define GL_COMBINE_RGB_ARB                                      0x8571
#define GL_COMBINE_ALPHA_ARB                                    0x8572
#define GL_RGB_SCALE_ARB                                        0x8573
#define GL_ADD_SIGNED_ARB                                       0x8574
#define GL_INTERPOLATE_ARB                                      0x8575
#define GL_CONSTANT_ARB                                         0x8576
#define GL_PRIMARY_COLOR_ARB                                    0x8577
#define GL_PREVIOUS_ARB                                         0x8578
#define GL_SOURCE0_RGB_ARB                                      0x8580
#define GL_SOURCE1_RGB_ARB                                      0x8581
#define GL_SOURCE2_RGB_ARB                                      0x8582
#define GL_SOURCE0_ALPHA_ARB                                    0x8588
#define GL_SOURCE1_ALPHA_ARB                                    0x8589
#define GL_SOURCE2_ALPHA_ARB                                    0x858A
#define GL_OPERAND0_RGB_ARB                                     0x8590
#define GL_OPERAND1_RGB_ARB                                     0x8591
#define GL_OPERAND2_RGB_ARB                                     0x8592
#define GL_OPERAND0_ALPHA_ARB                                   0x8598
#define GL_OPERAND1_ALPHA_ARB                                   0x8599
#define GL_OPERAND2_ALPHA_ARB                                   0x859A

/*-------------------------------------------------------------------*/
/*------------ARB_TEXTURE_ENV_DOT3-----------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_DOT3_RGB_ARB                                         0x86AE
#define GL_DOT3_RGBA_ARB                                        0x86AF

/*-------------------------------------------------------------------*/
/*------------ARB_TEXTURE_BORDER_CLAMP-------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_CLAMP_TO_BORDER_ARB                                  0x812D

/*-------------------------------------------------------------------*/
/*------------ARB_TEXTURE_ENV_ADD------------------------------------*/
/*-------------------------------------------------------------------*/

/*-------------------------------------------------------------------*/
/*------------NV_VERTEX_ARRAY_RANGE2---------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_VERTEX_ARRAY_RANGE_WITHOUT_FLUSH_NV                  0x8533

/*-------------------------------------------------------------------*/
/*------------ARB_VERTEX_BUFFER_OBJECT----------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_ARRAY_BUFFER_ARB                             0x8892
#define GL_ELEMENT_ARRAY_BUFFER_ARB                     0x8893
#define GL_ARRAY_BUFFER_BINDING_ARB                     0x8894
#define GL_ELEMENT_ARRAY_BUFFER_BINDING_ARB             0x8895
#define GL_VERTEX_ARRAY_BUFFER_BINDING_ARB              0x8896
#define GL_NORMAL_ARRAY_BUFFER_BINDING_ARB              0x8897
#define GL_COLOR_ARRAY_BUFFER_BINDING_ARB               0x8898
#define GL_INDEX_ARRAY_BUFFER_BINDING_ARB               0x8899
#define GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING_ARB       0x889A
#define GL_EDGE_FLAG_ARRAY_BUFFER_BINDING_ARB           0x889B
#define GL_SECONDARY_COLOR_ARRAY_BUFFER_BINDING_ARB     0x889C
#define GL_FOG_COORDINATE_ARRAY_BUFFER_BINDING_ARB      0x889D
#define GL_WEIGHT_ARRAY_BUFFER_BINDING_ARB              0x889E
#define GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING_ARB       0x889F
#define GL_STREAM_DRAW_ARB                              0x88E0
#define GL_STREAM_READ_ARB                              0x88E1
#define GL_STREAM_COPY_ARB                              0x88E2
#define GL_STATIC_DRAW_ARB                              0x88E4
#define GL_STATIC_READ_ARB                              0x88E5
#define GL_STATIC_COPY_ARB                              0x88E6
#define GL_DYNAMIC_DRAW_ARB                             0x88E8
#define GL_DYNAMIC_READ_ARB                             0x88E9
#define GL_DYNAMIC_COPY_ARB                             0x88EA
#define GL_READ_ONLY_ARB                                0x88B8
#define GL_WRITE_ONLY_ARB                               0x88B9
#define GL_READ_WRITE_ARB                               0x88BA
#define GL_BUFFER_SIZE_ARB                              0x8764
#define GL_BUFFER_USAGE_ARB                             0x8765
#define GL_BUFFER_ACCESS_ARB                            0x88BB
#define GL_BUFFER_MAPPED_ARB                            0x88BC
#define GL_BUFFER_MAP_POINTER_ARB                       0x88BD

typedef int GLintptrARB;
typedef unsigned int GLsizeiptrARB;

/*-------------------------------------------------------------------*/
/*------------ARB_MULTISAMPLE----------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_MULTISAMPLE_ARB                                      0x809D
#define GL_SAMPLE_ALPHA_TO_COVERAGE_ARB                         0x809E
#define GL_SAMPLE_ALPHA_TO_ONE_ARB                              0x809F
#define GL_SAMPLE_COVERAGE_ARB                                  0x80A0
#define GL_SAMPLE_BUFFERS_ARB                                   0x80A8
#define GL_SAMPLES_ARB                                          0x80A9
#define GL_SAMPLE_COVERAGE_VALUE_ARB                            0x80AA
#define GL_SAMPLE_COVERAGE_INVERT_ARB                           0x80AB
#define GL_MULTISAMPLE_BIT_ARB                                  0x20000000

/*-------------------------------------------------------------------*/
/*------------NV_TEXTURE_SHADER--------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_TEXTURE_SHADER_NV                                    0x86DE
#define GL_RGBA_UNSIGNED_DOT_PRODUCT_MAPPING_NV                 0x86D9
#define GL_SHADER_OPERATION_NV                                  0x86DF
#define GL_CULL_MODES_NV                                        0x86E0
#define GL_OFFSET_TEXTURE_MATRIX_NV                             0x86E1
#define GL_OFFSET_TEXTURE_SCALE_NV                              0x86E2
#define GL_OFFSET_TEXTURE_BIAS_NV                               0x86E3
#define GL_PREVIOUS_TEXTURE_INPUT_NV                            0x86E4
#define GL_CONST_EYE_NV                                         0x86E5
#define GL_SHADER_CONSISTENT_NV                                 0x86DD
#define GL_PASS_THROUGH_NV                                      0x86E6
#define GL_CULL_FRAGMENT_NV                                     0x86E7
#define GL_OFFSET_TEXTURE_2D_NV                                 0x86E8
#define GL_OFFSET_TEXTURE_RECTANGLE_NV                          0x864C
#define GL_OFFSET_TEXTURE_RECTANGLE_SCALE_NV                    0x864D
#define GL_DEPENDENT_AR_TEXTURE_2D_NV                           0x86E9
#define GL_DEPENDENT_GB_TEXTURE_2D_NV                           0x86EA
#define GL_DOT_PRODUCT_NV                                       0x86EC
#define GL_DOT_PRODUCT_DEPTH_REPLACE_NV                         0x86ED
#define GL_DOT_PRODUCT_TEXTURE_2D_NV                            0x86EE
#define GL_DOT_PRODUCT_TEXTURE_RECTANGLE_NV                     0x864E
#define GL_DOT_PRODUCT_TEXTURE_CUBE_MAP_NV                      0x86F0
#define GL_DOT_PRODUCT_DIFFUSE_CUBE_MAP_NV                      0x86F1
#define GL_DOT_PRODUCT_REFLECT_CUBE_MAP_NV                      0x86F2
#define GL_DOT_PRODUCT_CONST_EYE_REFLECT_CUBE_MAP_NV            0x86F3
#define GL_HILO_NV                                              0x86F4
#define GL_DSDT_NV                                              0x86F5
#define GL_DSDT_MAG_NV                                          0x86F6
#define GL_DSDT_MAG_VIB_NV                                      0x86F7
#define GL_UNSIGNED_INT_S8_S8_8_8_NV                            0x86DA
#define GL_UNSIGNED_INT_8_8_S8_S8_REV_NV                        0x86DB
#define GL_SIGNED_RGBA_NV                                       0x86FB
#define GL_SIGNED_RGBA8_NV                                      0x86FC
#define GL_SIGNED_RGB_NV                                        0x86FE
#define GL_SIGNED_RGB8_NV                                       0x86FF
#define GL_SIGNED_LUMINANCE_NV                                  0x8701
#define GL_SIGNED_LUMINANCE8_NV                                 0x8702
#define GL_SIGNED_LUMINANCE_ALPHA_NV                            0x8703
#define GL_SIGNED_LUMINANCE8_ALPHA8_NV                          0x8704
#define GL_SIGNED_ALPHA_NV                                      0x8705
#define GL_SIGNED_ALPHA8_NV                                     0x8706
#define GL_SIGNED_INTENSITY_NV                                  0x8707
#define GL_SIGNED_INTENSITY8_NV                                 0x8708
#define GL_SIGNED_RGB_UNSIGNED_ALPHA_NV                         0x870C
#define GL_SIGNED_RGB8_UNSIGNED_ALPHA8_NV                       0x870D
#define GL_HILO16_NV                                            0x86F8
#define GL_SIGNED_HILO_NV                                       0x86F9
#define GL_SIGNED_HILO16_NV                                     0x86FA
#define GL_DSDT8_NV                                             0x8709
#define GL_DSDT8_MAG8_NV                                        0x870A
#define GL_DSDT_MAG_INTENSITY_NV                                0x86DC
#define GL_DSDT8_MAG8_INTENSITY8_NV                             0x870B
#define GL_HI_SCALE_NV                                          0x870E
#define GL_LO_SCALE_NV                                          0x870F
#define GL_DS_SCALE_NV                                          0x8710
#define GL_DT_SCALE_NV                                          0x8711
#define GL_MAGNITUDE_SCALE_NV                                   0x8712
#define GL_VIBRANCE_SCALE_NV                                    0x8713
#define GL_HI_BIAS_NV                                           0x8714
#define GL_LO_BIAS_NV                                           0x8715
#define GL_DS_BIAS_NV                                           0x8716
#define GL_DT_BIAS_NV                                           0x8717
#define GL_MAGNITUDE_BIAS_NV                                    0x8718
#define GL_VIBRANCE_BIAS_NV                                     0x8719
#define GL_TEXTURE_BORDER_VALUES_NV                             0x871A
#define GL_TEXTURE_HI_SIZE_NV                                   0x871B
#define GL_TEXTURE_LO_SIZE_NV                                   0x871C
#define GL_TEXTURE_DS_SIZE_NV                                   0x871D
#define GL_TEXTURE_DT_SIZE_NV                                   0x871E
#define GL_TEXTURE_MAG_SIZE_NV                                  0x871F

/*-------------------------------------------------------------------*/
/*------------GL_NV_TEXTURE_RECTANGLE--------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_TEXTURE_RECTANGLE_NV                                 0x84F5
#define GL_TEXTURE_BINDING_RECTANGLE_NV                         0x84F6
#define GL_PROXY_TEXTURE_RECTANGLE_NV                           0x84F7
#define GL_MAX_RECTANGLE_TEXTURE_SIZE_NV                        0x84F8

/*-------------------------------------------------------------------*/
/*------------NV_TEXTURE_ENV_COMBINE4--------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_COMBINE4_NV                                          0x8503
#define GL_SOURCE3_RGB_NV                                       0x8583
#define GL_SOURCE3_ALPHA_NV                                     0x858B
#define GL_OPERAND3_RGB_NV                                      0x8593
#define GL_OPERAND3_ALPHA_NV                                    0x859B

/*-------------------------------------------------------------------*/
/*------------NV_FOG_DISTANCE----------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_FOG_DISTANCE_MODE_NV                                 0x855A
#define GL_EYE_RADIAL_NV                                        0x855B
#define GL_EYE_PLANE_ABSOLUTE_NV                                0x855C

/*-------------------------------------------------------------------*/
/*------------EXT_TEXTURE_FILTER_ANISOTROPIC-------------------------*/
/*-------------------------------------------------------------------*/

#define GL_TEXTURE_MAX_ANISOTROPY_EXT                           0x84FE
#define GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT                       0x84FF

/*-------------------------------------------------------------------*/
/*------------SGIS_GENERATE_MIPMAP-----------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_GENERATE_MIPMAP_SGIS                                 0x8191
#define GL_GENERATE_MIPMAP_HINT_SGIS                            0x8192

/*-------------------------------------------------------------------*/
/*------------NV_TEXGEN_REFLECTION-----------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_NORMAL_MAP_NV                                        0x8511
#define GL_REFLECTION_MAP_NV                                    0x8512

/*-------------------------------------------------------------------*/
/*------------NV_TEXTURE_SHADER2-------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_DOT_PRODUCT_TEXTURE_3D_NV                            0x86EF
#define GL_HILO_NV                                              0x86F4
#define GL_DSDT_NV                                              0x86F5
#define GL_DSDT_MAG_NV                                          0x86F6
#define GL_DSDT_MAG_VIB_NV                                      0x86F7
#define GL_UNSIGNED_INT_S8_S8_8_8_NV                            0x86DA
#define GL_UNSIGNED_INT_8_8_S8_S8_REV_NV                        0x86DB
#define GL_SIGNED_RGBA_NV                                       0x86FB
#define GL_SIGNED_RGBA8_NV                                      0x86FC
#define GL_SIGNED_RGB_NV                                        0x86FE
#define GL_SIGNED_RGB8_NV                                       0x86FF
#define GL_SIGNED_LUMINANCE_NV                                  0x8701
#define GL_SIGNED_LUMINANCE8_NV                                 0x8702
#define GL_SIGNED_LUMINANCE_ALPHA_NV                            0x8703
#define GL_SIGNED_LUMINANCE8_ALPHA8_NV                          0x8704
#define GL_SIGNED_ALPHA_NV                                      0x8705
#define GL_SIGNED_ALPHA8_NV                                     0x8706
#define GL_SIGNED_INTENSITY_NV                                  0x8707
#define GL_SIGNED_INTENSITY8_NV                                 0x8708
#define GL_SIGNED_RGB_UNSIGNED_ALPHA_NV                         0x870C
#define GL_SIGNED_RGB8_UNSIGNED_ALPHA8_NV                       0x870D
#define GL_HILO16_NV                                            0x86F8
#define GL_SIGNED_HILO_NV                                       0x86F9
#define GL_SIGNED_HILO16_NV                                     0x86FA
#define GL_DSDT8_NV                                             0x8709
#define GL_DSDT8_MAG8_NV                                        0x870A
#define GL_DSDT_MAG_INTENSITY_NV                                0x86DC
#define GL_DSDT8_MAG8_INTENSITY8_NV                             0x870B

/*-------------------------------------------------------------------*/
/*------------NV_BLEND_SQUARE----------------------------------------*/
/*-------------------------------------------------------------------*/

/*-------------------------------------------------------------------*/
/*------------NV_LIGHT_MAX_EXPONENT----------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_MAX_SHININESS_NV                                     0x8504
#define GL_MAX_SPOT_EXPONENT_NV                                 0x8505

/*-------------------------------------------------------------------*/
/*------------NV_PACKED_DEPTH_STENCIL--------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_DEPTH_STENCIL_NV                                     0x84F9
#define GL_UNSIGNED_INT_24_8_NV                                 0x84FA

/*-------------------------------------------------------------------*/
/*------------EXT_ABGR-----------------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_ABGR_EXT                                             0x8000

/*-------------------------------------------------------------------*/
/*------------EXT_STENCIL_WRAP---------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_INCR_WRAP_EXT                                        0x8507
#define GL_DECR_WRAP_EXT                                        0x8508

/*-------------------------------------------------------------------*/
/*------------EXT_TEXTURE_LOD_BIAS-----------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_TEXTURE_FILTER_CONTROL_EXT                           0x8500
#define GL_TEXTURE_LOD_BIAS_EXT                                 0x8501
#define GL_MAX_TEXTURE_LOD_BIAS_EXT                             0x84FD

/*-------------------------------------------------------------------*/
/*------------NV_COPY_DEPTH_TO_COLOR---------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_DEPTH_STENCIL_TO_RGBA_NV                             0x886E
#define GL_DEPTH_STENCIL_TO_BGRA_NV                             0x886F

/*-------------------------------------------------------------------*/
/*------------ARB_TEXTURE_ENV_CROSSBAR-------------------------------*/
/*-------------------------------------------------------------------*/

/*-------------------------------------------------------------------*/
/*------------ATI_TEXTURE_MIRROR_ONCE--------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_MIRROR_CLAMP_ATI                                     0x8742
#define GL_MIRROR_CLAMP_TO_EDGE_ATI                             0x8743


/*-------------------------------------------------------------------*/
/*------------HP_OCCLUSION_TEST--------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_OCCLUSION_TEST_HP                                    0x8165;
#define GL_OCCLUSION_TEST_RESULT_HP                             0x8166;

/*-------------------------------------------------------------------*/
/*------------ATIX_POINT_SPRITES-------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_TEXTURE_POINT_MODE_ATIX                               0x60b0
#define	GL_TEXTURE_POINT_ONE_COORD_ATIX                          0x60b1
#define	GL_TEXTURE_POINT_SPRITE_ATIX                             0x60b2
#define GL_POINT_SPRITE_CULL_MODE_ATIX                           0x60b3
#define GL_POINT_SPRITE_CULL_CENTER_ATIX                         0x60b4
#define GL_POINT_SPRITE_CULL_CLIP_ATIX                           0x60b5

/*-------------------------------------------------------------------*/
/*------------ATIX_TEXTURE_ENV_ROUTE---------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_SECONDARY_COLOR_ATIX                                  0x8747
#define GL_TEXTURE_OUTPUT_RGB_ATIX                               0x8748
#define GL_TEXTURE_OUTPUT_ALPHA_ATIX                             0x8749

/*-------------------------------------------------------------------*/
/*------------NV_DEPTH_CLAMP-----------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_DEPTH_CLAMP_NV                                       0x864F

/*-------------------------------------------------------------------*/
/*------------NV_TEXTURE_SHADER3-------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_OFFSET_PROJECTIVE_TEXTURE_2D_NV                      0x8850
#define GL_OFFSET_PROJECTIVE_TEXTURE_2D_SCALE_NV                0x8851
#define GL_OFFSET_PROJECTIVE_TEXTURE_RECTANGLE_NV               0x8852
#define GL_OFFSET_PROJECTIVE_TEXTURE_RECTANGLE_SCALE_NV         0x8853
#define GL_OFFSET_HILO_TEXTURE_2D_NV                            0x8854
#define GL_OFFSET_HILO_TEXTURE_RECTANGLE_NV                     0x8855
#define GL_OFFSET_HILO_PROJECTIVE_TEXTURE_2D_NV                 0x8856
#define GL_OFFSET_HILO_PROJECTIVE_TEXTURE_RECTANGLE_NV          0x8857
#define GL_DEPENDENT_HILO_TEXTURE_2D_NV                         0x8858
#define GL_DEPENDENT_RGB_TEXTURE_3D_NV                          0x8859
#define GL_DEPENDENT_RGB_TEXTURE_CUBE_MAP_NV                    0x885A
#define GL_DOT_PRODUCT_PASS_THROUGH_NV                          0x885B
#define GL_DOT_PRODUCT_TEXTURE_1D_NV                            0x885C
#define GL_DOT_PRODUCT_AFFINE_DEPTH_REPLACE_NV                  0x885D
#define GL_HILO8_NV                                             0x885E
#define GL_SIGNED_HILO8_NV                                      0x885F
#define GL_FORCE_BLUE_TO_ONE_NV                                 0x8860

/*-------------------------------------------------------------------*/
/*------------NV_VERTEX_PROGRAM1_1-----------------------------------*/
/*-------------------------------------------------------------------*/


/*-------------------------------------------------------------------*/
/*------------ARB_TEXTURE_MIRRORED_REPEAT----------------------------*/
/*-------------------------------------------------------------------*/

#define GL_GL_MIRRORED_REPEAT_ARB                               0x8370

/*-------------------------------------------------------------------*/
/*------------ARB_SHADOW---------------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_TEXTURE_COMPARE_MODE_ARB                             0x884C
#define GL_TEXTURE_COMPARE_FUNC_ARB                             0x884D
#define GL_COMPARE_R_TO_TEXTURE_ARB                             0x884E

/*-------------------------------------------------------------------*/
/*------------ARB_SHADOW_AMBIENT-------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_TEXTURE_COMPARE_FAIL_VALUE_ARB                       0x80BF

/*-------------------------------------------------------------------*/
/*------------ARB_DEPTH_TEXTURE--------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_DEPTH_COMPONENT16_ARB                                0x81A5
#define GL_DEPTH_COMPONENT24_ARB                                0x81A6
#define GL_DEPTH_COMPONENT32_ARB                                0x81A7
#define GL_TEXTURE_DEPTH_SIZE_ARB                               0x884A
#define GL_DEPTH_TEXTURE_MODE_ARB                               0x884B

/*-------------------------------------------------------------------*/
/*------------ARB_WINDOW_POS-----------------------------------------*/
/*-------------------------------------------------------------------*/

/*-------------------------------------------------------------------*/
/*------------EXT_SHADOW_FUNCS---------------------------------------*/
/*-------------------------------------------------------------------*/


#define GL_MAX_ELEMENTS_VERTICES_EXT                            0x80E8
#define GL_MAX_ELEMENTS_INDICES_EXT                             0x80E9

/*-------------------------------------------------------------------*/
/*------------EXT_texture_compression_s3tc---------------------------*/
/*-------------------------------------------------------------------*/

#define GL_COMPRESSED_RGB_S3TC_DXT1_EXT                         0x83F0
#define GL_COMPRESSED_RGBA_S3TC_DXT1_EXT                        0x83F1
#define GL_COMPRESSED_RGBA_S3TC_DXT3_EXT                        0x83F2
#define GL_COMPRESSED_RGBA_S3TC_DXT5_EXT                        0x83F3

#define GL_STENCIL_TEST_TWO_SIDE_EXT                            0x8910
#define GL_ACTIVE_STENCIL_FACE_EXT                              0x8911

/*-------------------------------------------------------------------*/
/*------------GL_EXT_BGRA--------------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_BGR_EXT                                              0x80E0
#define GL_BGRA_EXT                                             0x80E1

/*-------------------------------------------------------------------*/
/*------------EXT_CULL_VERTEX----------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_CULL_VERTEX_EXT                                      0x81AA
#define GL_CULL_VERTEX_EYE_POSITION_EXT                         0x81AB
#define GL_CULL_VERTEX_OBJECT_POSITION_EXT                      0x81AC

typedef void (APIENTRY * glCullParameterfvEXTPROC) (GLenum pname, GLfloat *params);
typedef void (APIENTRY * glCullParameterdvEXTPROC) (GLenum pname, GLdouble *params);

extern glCullParameterfvEXTPROC glCullParameterfvEXT;
extern glCullParameterdvEXTPROC glCullParameterdvEXT;

/*-------------------------------------------------------------------*/
/*------------GL_ATI_POINT_CULL_MODE---------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_POINT_CULL_MODE_ATI                                  0x60b3
#define GL_POINT_CULL_CENTER_ATI                                0x60b4
#define GL_POINT_CLIP_ATI                                       0x60b5

/*-------------------------------------------------------------------*/
/*------------GL_EXT_SEPARATE_SPECULAR_COLOR-------------------------*/
/*-------------------------------------------------------------------*/

#define GL_LIGHT_MODEL_COLOR_CONTROL_EXT                        0x81F8
#define GL_SINGLE_COLOR_EXT                                     0x81F9
#define GL_SEPARATE_SPECULAR_COLOR_EXT                          0x81FA

/*-------------------------------------------------------------------*/
/*------------GL_NV_ELEMENT_ARRAY------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_ELEMENT_ARRAY_TYPE_NV                                0x8769
#define GL_ELEMENT_ARRAY_POINTER_NV                             0x876A

typedef void (APIENTRY * glElementPointerNVPROC) (GLenum type, const GLvoid *pointer);
typedef void (APIENTRY * glDrawElementArrayNVPROC) (GLenum mode, GLint first, GLsizei count);
typedef void (APIENTRY * glDrawRangeElementArrayNVPROC) (GLenum mode, GLuint start, GLuint end, GLint first, GLsizei count);
typedef void (APIENTRY * glMultiDrawElementArrayNVPROC) (GLenum mode, const GLint *first, const GLsizei *count, GLsizei primcount);
typedef void (APIENTRY * glMultiDrawRangeElementArrayNVPROC) (GLenum mode, GLuint start, GLuint end, const GLint *first, const GLsizei *count, GLsizei primcount);

extern glElementPointerNVPROC glElementPointerNV;
extern glDrawElementArrayNVPROC glDrawElementArrayNV;
extern glDrawRangeElementArrayNVPROC glDrawRangeElementArrayNV;
extern glMultiDrawElementArrayNVPROC glMultiDrawElementArrayNV;
extern glMultiDrawRangeElementArrayNVPROC glMultiDrawRangeElementArrayNV;

/*-------------------------------------------------------------------*/
/*------------GL_NV_FLOAT_BUFFER-------------------------------------*/
/*-------------------------------------------------------------------*/

#define GL_FLOAT_R_NV                                           0x8880
#define GL_FLOAT_RG_NV                                          0x8881
#define GL_FLOAT_RGB_NV                                         0x8882
#define GL_FLOAT_RGBA_NV                                        0x8883
#define GL_FLOAT_R32_NV                                         0x8885
#define GL_FLOAT_R16_NV                                         0x8884
#define GL_FLOAT_R32_NV                                         0x8885
#define GL_FLOAT_RG16_NV                                        0x8886
#define GL_FLOAT_RG32_NV                                        0x8887
#define GL_FLOAT_RGB16_NV                                       0x8888
#define GL_FLOAT_RGB32_NV                                       0x8889
#define GL_FLOAT_RGBA16_NV                                      0x888A
#define GL_FLOAT_RGBA32_NV                                      0x888B
#define GL_TEXTURE_FLOAT_COMPONENTS_NV                          0x888C
#define GL_FLOAT_CLEAR_COLOR_VALUE_NV                           0x888D
#define GL_FLOAT_RGBA_MODE_NV                                   0x888E

#ifdef _WIN32
#define WGL_FLOAT_COMPONENTS_NV                                 0x20B0
#define WGL_BIND_TO_TEXTURE_RECTANGLE_FLOAT_R_NV                0x20B1
#define WGL_BIND_TO_TEXTURE_RECTANGLE_FLOAT_RG_NV               0x20B2
#define WGL_BIND_TO_TEXTURE_RECTANGLE_FLOAT_RGB_NV              0x20B3
#define WGL_BIND_TO_TEXTURE_RECTANGLE_FLOAT_RGBA_NV             0x20B4
#define WGL_TEXTURE_FLOAT_R_NV                                  0x20B5
#define WGL_TEXTURE_FLOAT_RG_NV                                 0x20B6
#define WGL_TEXTURE_FLOAT_RGB_NV                                0x20B7
#define WGL_TEXTURE_FLOAT_RGBA_NV                               0x20B8
#endif /* _WIN32 */

/*-------------------------------------------------------------------*/
/*------------GL_NV_VERTEX_PROGRAM2----------------------------------*/
/*-------------------------------------------------------------------*/

/*-------------------------------------------------------------------*/
/*------------GL_ARB_FRAGMENT_PROGRAM--------------------------------*/
/*-------------------------------------------------------------------*/

/* no new entry points, all of ARB_vertex_program reused */

#define GL_FRAGMENT_PROGRAM_ARB                                 0x8804
#define GL_PROGRAM_ALU_INSTRUCTIONS_ARB                         0x8805
#define GL_PROGRAM_TEX_INSTRUCTIONS_ARB                         0x8806
#define GL_PROGRAM_TEX_INDIRECTIONS_ARB                         0x8807
#define GL_PROGRAM_NATIVE_ALU_INSTRUCTIONS_ARB                  0x8808
#define GL_PROGRAM_NATIVE_TEX_INSTRUCTIONS_ARB                  0x8809
#define GL_PROGRAM_NATIVE_TEX_INDIRECTIONS_ARB                  0x880A
#define GL_MAX_PROGRAM_ALU_INSTRUCTIONS_ARB                     0x880B
#define GL_MAX_PROGRAM_TEX_INSTRUCTIONS_ARB                     0x880C
#define GL_MAX_PROGRAM_TEX_INDIRECTIONS_ARB                     0x880D
#define GL_MAX_PROGRAM_NATIVE_ALU_INSTRUCTIONS_ARB              0x880E
#define GL_MAX_PROGRAM_NATIVE_TEX_INSTRUCTIONS_ARB              0x880F
#define GL_MAX_PROGRAM_NATIVE_TEX_INDIRECTIONS_ARB              0x8810
#define GL_MAX_TEXTURE_COORDS_ARB                               0x8871
#define GL_MAX_TEXTURE_IMAGE_UNITS_ARB                          0x8872

/*-------------------------------------------------------------------*/
/*------------GL_ATI_TEXT_FRAGMENT_SHADER----------------------------*/
/*-------------------------------------------------------------------*/

#define GL_TEXT_FRAGMENT_SHADER_ATI                             0x8200

/*-------------------------------------------------------------------*/
/*------------GL_EXT_textzre_env_combine-----------------------------*/
/*-------------------------------------------------------------------*/

#define GL_COMBINE_EXT                                          0x8570
#define GL_COMBINE_RGB_EXT                                      0x8571
#define GL_COMBINE_ALPHA_EXT                                    0x8572
#define GL_RGB_SCALE_EXT                                        0x8573
#define GL_ADD_SIGNED_EXT                                       0x8574
#define GL_INTERPOLATE_EXT                                      0x8575
#define GL_CONSTANT_EXT                                         0x8576
#define GL_PRIMARY_COLOR_EXT                                    0x8577
#define GL_PREVIOUS_EXT                                         0x8578
#define GL_SOURCE0_RGB_EXT                                      0x8580
#define GL_SOURCE1_RGB_EXT                                      0x8581
#define GL_SOURCE2_RGB_EXT                                      0x8582
#define GL_SOURCE0_ALPHA_EXT                                    0x8588
#define GL_SOURCE1_ALPHA_EXT                                    0x8589
#define GL_SOURCE2_ALPHA_EXT                                    0x858A
#define GL_OPERAND0_RGB_EXT                                     0x8590
#define GL_OPERAND1_RGB_EXT                                     0x8591
#define GL_OPERAND2_RGB_EXT                                     0x8592
#define GL_OPERAND0_ALPHA_EXT                                   0x8598
#define GL_OPERAND1_ALPHA_EXT                                   0x8599
#define GL_OPERAND2_ALPHA_EXT                                   0x859A

/*-------------------------------------------------------------------*/
/*------------END GL EXTENSIONS--------------------------------------*/
/*-------------------------------------------------------------------*/

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
/*------------WGL_ARB_BUFFER_REGION----------------------------------*/
/*-------------------------------------------------------------------*/

#define WGL_FRONT_COLOR_BUFFER_BIT_ARB                          0x00000001
#define WGL_BACK_COLOR_BUFFER_BIT_ARB                           0x00000002
#define WGL_DEPTH_BUFFER_BIT_ARB                                0x00000004
#define WGL_STENCIL_BUFFER_BIT_ARB                              0x00000008

typedef HANDLE (APIENTRY * wglCreateBufferRegionARBPROC) (HDC hDC, int iLayerPlane, UINT uType);
typedef VOID (APIENTRY * wglDeleteBufferRegionARBPROC) (HANDLE hRegion);
typedef BOOL (APIENTRY * wglSaveBufferRegionARBPROC) (HANDLE hRegion, int x, int y, int width, int height);
typedef BOOL (APIENTRY * wglRestoreBufferRegionARBPROC) (HANDLE hRegion, int x, int y, int width, int height, int xSrc, int ySrc);

extern wglCreateBufferRegionARBPROC wglCreateBufferRegionARB;
extern wglDeleteBufferRegionARBPROC wglDeleteBufferRegionARB;
extern wglSaveBufferRegionARBPROC wglSaveBufferRegionARB;
extern wglRestoreBufferRegionARBPROC wglRestoreBufferRegionARB;

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

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_RENDER_TEXTURE---------------------------------*/
/*-------------------------------------------------------------------*/

#define WGL_BIND_TO_TEXTURE_RGB_ARB                             0x2070
#define WGL_BIND_TO_TEXTURE_RGBA_ARB                            0x2071
#define WGL_TEXTURE_FORMAT_ARB                                  0x2072
#define WGL_TEXTURE_TARGET_ARB                                  0x2073
#define WGL_MIPMAP_TEXTURE_ARB                                  0x2074
#define WGL_TEXTURE_RGB_ARB                                     0x2075
#define WGL_TEXTURE_RGBA_ARB                                    0x2076
#define WGL_NO_TEXTURE_ARB                                      0x2077
#define WGL_TEXTURE_CUBE_MAP_ARB                                0x2078
#define WGL_TEXTURE_1D_ARB                                      0x2079
#define WGL_TEXTURE_2D_ARB                                      0x207A
#define WGL_NO_TEXTURE_ARB                                      0x2077
#define WGL_MIPMAP_LEVEL_ARB                                    0x207B
#define WGL_CUBE_MAP_FACE_ARB                                   0x207C
#define WGL_TEXTURE_CUBE_MAP_POSITIVE_X_ARB                     0x207D
#define WGL_TEXTURE_CUBE_MAP_NEGATIVE_X_ARB                     0x207E
#define WGL_TEXTURE_CUBE_MAP_POSITIVE_Y_ARB                     0x207F
#define WGL_TEXTURE_CUBE_MAP_NEGATIVE_Y_ARB                     0x2080
#define WGL_TEXTURE_CUBE_MAP_POSITIVE_Z_ARB                     0x2081
#define WGL_TEXTURE_CUBE_MAP_NEGATIVE_Z_ARB                     0x2082
#define WGL_FRONT_LEFT_ARB                                      0x2083
#define WGL_FRONT_RIGHT_ARB                                     0x2084
#define WGL_BACK_LEFT_ARB                                       0x2085
#define WGL_BACK_RIGHT_ARB                                      0x2086
#define WGL_AUX0_ARB                                            0x2087
#define WGL_AUX1_ARB                                            0x2088
#define WGL_AUX2_ARB                                            0x2089
#define WGL_AUX3_ARB                                            0x208A
#define WGL_AUX4_ARB                                            0x208B
#define WGL_AUX5_ARB                                            0x208C
#define WGL_AUX6_ARB                                            0x208D
#define WGL_AUX7_ARB                                            0x208E
#define WGL_AUX8_ARB                                            0x208F
#define WGL_AUX9_ARB                                            0x2090

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
/*------------WGL_NV_RENDER_DEPTH_TEXTURE----------------------------*/
/*-------------------------------------------------------------------*/

#define WGL_BIND_TO_TEXTURE_DEPTH_NV                            0x20A3
#define WGL_BIND_TO_TEXTURE_RECTANGLE_DEPTH_NV                  0x20A4
#define WGL_DEPTH_TEXTURE_FORMAT_NV                             0x20A5
#define WGL_TEXTURE_DEPTH_COMPONENT_NV                          0x20A6
#define WGL_NO_TEXTURE_ARB                                      0x2077
#define WGL_DEPTH_COMPONENT_NV                                  0x20A7

/*-------------------------------------------------------------------*/
/*------------WGL_NV_RENDER_TEXTURE_RECTANGLE-----------------------*/
/*-------------------------------------------------------------------*/

#define WGL_BIND_TO_TEXTURE_RECTANGLE_RGB_NV                    0x20A0
#define WGL_BIND_TO_TEXTURE_RECTANGLE_RGBA_NV                   0x20A1
#define WGL_TEXTURE_RECTANGLE_NV                                0x20A2

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
    bool GL_ARB_matrix_palette;
    bool GL_ARB_multisample;
    bool GL_ARB_multitexture;
	bool GL_ARB_occlusion_query;
    bool GL_ARB_point_parameters;
	bool GL_ARB_shader_objects;
    bool GL_ARB_shadow;
    bool GL_ARB_shadow_ambient;
    bool GL_ARB_texture_border_clamp;
    bool GL_ARB_texture_compression;
    bool GL_ARB_texture_cube_map;
    bool GL_ARB_texture_env_add;
    bool GL_ARB_texture_env_dot3;
    bool GL_ARB_texture_env_combine;
    bool GL_ARB_texture_env_crossbar;
    bool GL_ARB_texture_mirrored_repeat;
    bool GL_ARB_transpose_matrix;
    bool GL_ARB_vertex_blend;
    bool GL_ARB_vertex_buffer_object;
    bool GL_ARB_vertex_program;
	bool GL_ARB_vertex_shader;
    bool GL_ARB_window_pos;
    bool GL_EXT_abgr;
    bool GL_EXT_bgra;
    bool GL_EXT_blend_func_separate;
    bool GL_EXT_compiled_vertex_array;
    bool GL_EXT_cull_vertex;
    bool GL_EXT_fog_coord;
    bool GL_EXT_multi_draw_arrays;
    bool GL_EXT_point_parameters;
    bool GL_EXT_secondary_color;
    bool GL_EXT_separate_specular_color;
    bool GL_EXT_shadow_funcs;
    bool GL_EXT_stencil_two_side;
    bool GL_EXT_stencil_wrap;
    bool GL_EXT_texture_compression_s3tc;
    bool GL_EXT_texture_env_combine;
    bool GL_EXT_texture_filter_anisotropic;
    bool GL_EXT_texture_lod_bias;
    bool GL_EXT_vertex_shader;
    bool GL_EXT_vertex_weighting;
	bool GL_EXT_draw_range_elements;
	bool GL_ATI_draw_buffers;
    bool GL_ATI_element_array;
    bool GL_ATI_envmap_bumpmap;
    bool GL_ATI_fragment_shader;
	bool GL_ATI_map_object_buffer;
    bool GL_ATI_pn_triangles;
    bool GL_ATI_point_cull_mode;
    bool GL_ATI_separate_stencil;
    bool GL_ATI_text_fragment_shader;
    bool GL_ATI_texture_mirror_once;
    bool GL_ATI_vertex_array_object;
	bool GL_ATI_vertex_attrib_array_object;
    bool GL_ATI_vertex_streams;
    bool GL_ATIX_point_sprites;
    bool GL_ATIX_texture_env_route;
    bool GL_HP_occlusion_test;
    bool GL_NV_blend_square;
    bool GL_NV_copy_depth_to_color;
    bool GL_NV_depth_clamp;
    bool GL_NV_element_array;
    bool GL_NV_evaluators;
    bool GL_NV_fence;
    bool GL_NV_float_buffer;
    bool GL_NV_fog_distance;
    bool GL_NV_fragment_program;
	bool GL_NV_half_float;
    bool GL_NV_light_max_exponent;
    bool GL_NV_occlusion_query;
    bool GL_NV_packed_depth_stencil;
	bool GL_NV_pixel_data_range;
    bool GL_NV_point_sprite;
    bool GL_NV_primitive_restart;
    bool GL_NV_register_combiners;
    bool GL_NV_register_combiners2;
    bool GL_NV_texgen_reflection;
    bool GL_NV_texture_env_combine4;
    bool GL_NV_texture_rectangle;
    bool GL_NV_texture_shader;
    bool GL_NV_texture_shader2;
    bool GL_NV_texture_shader3;
    bool GL_NV_vertex_array_range;
    bool GL_NV_vertex_array_range2;
    bool GL_NV_vertex_program;
    bool GL_NV_vertex_program1_1;
    bool GL_NV_vertex_program2;
    bool GL_SGIS_generate_mipmap;
    bool GL_SGIX_shadow;
    bool GL_SGIX_depth_texture;
};

extern struct ExtensionTypes extgl_Extensions;
extern bool extgl_error;

typedef GLenum (APIENTRY * glGetErrorPROC) (void);
typedef const GLubyte * (APIENTRY * glGetStringPROC) (GLenum name);

extern glGetErrorPROC glGetError;
extern glGetStringPROC glGetString;


/* initializes everything, call this right after the rc is created. the function returns 0 if successful */
bool extgl_Initialize(JNIEnv *env, jobject gl_extensions);
bool extgl_Open(void);
#ifdef _WIN32
void extgl_InitWGL(JNIEnv *env, jobject ext_set);
#endif
#ifdef _X11
bool extgl_InitGLX(JNIEnv *env, jobject ext_set, Display *disp, int screen);
#endif
#ifdef _AGL
bool extgl_InitAGL(JNIEnv *env, jobject ext_set);
#endif
void *extgl_GetProcAddress(const char *name);
void extgl_Close(void);
void extgl_removeExtension(JNIEnv *env, jobject ext_set, const char *ext);

#define EXTGL_SANITY_CHECK(e,h,x) 	if (extgl_error) { \
						extgl_Extensions.x = 0; \
						printf("NOTICE: %s disabled because of missing driver symbols\n", #x); \
						extgl_error = false; \
						extgl_removeExtension(e, h, #x); \
					}
#ifdef __cplusplus
}
#endif

#endif /* __EXTGL_H__ */
