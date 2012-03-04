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

#ifndef EXTGL_GLX_H
#define EXTGL_GLX_H

#include <X11/Xutil.h>
#include "extgl.h"

/*
 * Names for attributes to lwjgl_glXGetConfig.
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
 * Error return values from lwjgl_glXGetConfig.  Success is indicated by
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
 * Generic "don't care" value for lwjgl_glX ChooseFBConfig attributes (except
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

/* lwjgl_glXCreateGLXPbuffer attributes */
#define GLX_PRESERVED_CONTENTS          0x801B
#define GLX_LARGEST_PBUFFER             0x801C
#define GLX_PBUFFER_HEIGHT              0x8040  /* New for GLX 1.3 */
#define GLX_PBUFFER_WIDTH               0x8041  /* New for GLX 1.3 */
#define GLX_PRESERVED_CONTENTS_SGIX     GLX_PRESERVED_CONTENTS
#define GLX_LARGEST_PBUFFER_SGIX        GLX_LARGEST_PBUFFER

/* lwjgl_glXQueryGLXPBuffer attributes */
#define GLX_WIDTH                       0x801D
#define GLX_HEIGHT                      0x801E
#define GLX_EVENT_MASK                  0x801F
#define GLX_WIDTH_SGIX                  GLX_WIDTH
#define GLX_HEIGHT_SGIX                 GLX_HEIGHT
#define GLX_EVENT_MASK_SGIX             GLX_EVENT_MASK

/* lwjgl_glXCreateNewContext render_type attribute values */
#define GLX_RGBA_TYPE                   0x8014
#define GLX_COLOR_INDEX_TYPE            0x8015
#define GLX_RGBA_TYPE_SGIX              GLX_RGBA_TYPE
#define GLX_COLOR_INDEX_TYPE_SGIX       GLX_COLOR_INDEX_TYPE

/* lwjgl_glXQueryContext attributes */
/* #define GLX_FBCONFIG_ID                0x8013 */
/* #define GLX_RENDER_TYPE                0x8011 */
#define GLX_SCREEN                      0x800C

/* lwjgl_glXSelectEvent event mask bits */
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
 * Extension return values from lwjgl_glXGetConfig.  These are also
 * accepted as parameter values for lwjgl_glXChooseVisual.
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
 * Names for attributes to lwjgl_glXGetClientString.
 */
#define GLX_VENDOR                      0x1
#define GLX_VERSION                     0x2
#define GLX_EXTENSIONS                  0x3

/*
 * Names for attributes to lwjgl_glXQueryContextInfoEXT.
 */
#define GLX_SHARE_CONTEXT_EXT           0x800A  /* id of share context */
#define GLX_VISUAL_ID_EXT               0x800B  /* id of context's visual */
#define GLX_SCREEN_EXT                  0x800C  /* screen number */

/* NV_float_buffer */
#define GLX_FLOAT_COMPONENTS_NV         0x20B0

/* GLX_ARB_fbconfig_float  */
#define GLX_RGBA_FLOAT_TYPE             0x20B9
#define GLX_RGBA_FLOAT_BIT              0x0004

/* GLX_ARB_fbconfig_float  */
#define GLX_RGBA_UNSIGNED_FLOAT_TYPE_EXT	0x20B1
#define GLX_RGBA_UNSIGNED_FLOAT_BIT_EXT		0x00000008

/* GLX_ARB_framebuffer_sRGB  */
#define GLX_FRAMEBUFFER_SRGB_CAPABLE_ARB	0x20B2

/* GLX_ARB_create_context  */
#define GLX_CONTEXT_MAJOR_VERSION_ARB	0x2091
#define GLX_CONTEXT_MINOR_VERSION_ARB	0x2092
#define GLX_CONTEXT_FLAGS_ARB			0x2094

#define GLX_CONTEXT_DEBUG_BIT_ARB				0x0001
#define GLX_CONTEXT_FORWARD_COMPATIBLE_BIT_ARB	0x0002

/* GLX_NV_multisample_coverage  */
#define GLX_COVERAGE_SAMPLES_NV         100001
#define GLX_COLOR_SAMPLES_NV            0x20B3

/* GLX_NV_present_video  */
#define GLX_NUM_VIDEO_SLOTS_NV          0x20F0

/* GLX_NV_video_capture */
#define GLX_DEVICE_ID_NV                0x20CD
#define GLX_NUM_VIDEO_CAPTURE_SLOTS_NV  0x20CF
#define GLX_UNIQUE_ID_NV                0x20CE

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

/* GLX_SGI_swap_control */
typedef void (APIENTRY * glXSwapIntervalSGIPROC)(int interval);

/* GLX_EXT_swap_control */
typedef void (APIENTRY * glXSwapIntervalEXTPROC)(Display *dpy, GLXDrawable drawable, int interval);

/* GLX_ARB_create_context */
typedef GLXContext (APIENTRY * glXCreateContextAttribsARBPROC) (Display *dpy, GLXFBConfig config, GLXContext share_context, Bool direct, const int *attrib_list);

/* GLX_NV_present_video */
typedef unsigned int * (APIENTRY * glXEnumerateVideoDevicesNVPROC) (Display *dpy, int screen, int *nelements);
typedef int (APIENTRY * glXBindVideoDeviceNVPROC) (Display *dpy, unsigned int video_slot, unsigned int video_device, const int *attrib_list);

/* GLX_NV_video_capture */
typedef XID GLXVideoCaptureDeviceNV;
typedef int (APIENTRY * glXBindVideoCaptureDeviceNVPROC) (Display *dpy, unsigned int video_capture_slot, GLXVideoCaptureDeviceNV device);
typedef GLXVideoCaptureDeviceNV * (APIENTRY * glXEnumerateVideoCaptureDevicesNVPROC) (Display *dpy, int screen, int *nelements);
typedef void (APIENTRY * glXLockVideoCaptureDeviceNVPROC) (Display *dpy, GLXVideoCaptureDeviceNV device);
typedef int (APIENTRY * glXQueryVideoCaptureDeviceNVPROC) (Display *dpy, GLXVideoCaptureDeviceNV device, int attribute, int *value);
typedef void (APIENTRY * glXReleaseVideoCaptureDeviceNVPROC) (Display *dpy, GLXVideoCaptureDeviceNV device);

typedef struct {
    bool GLX12;
    bool GLX13;
	bool GLX14;
/*    bool GLX_EXT_visual_info;
    bool GLX_EXT_visual_rating;*/
    bool GLX_SGI_swap_control;
    bool GLX_EXT_swap_control;
    bool GLX_ARB_multisample;
	bool GLX_ARB_fbconfig_float;
	bool GLX_EXT_fbconfig_packed_float;
	bool GLX_ARB_framebuffer_sRGB;
	bool GLX_ARB_create_context;
	bool GLX_NV_multisample_coverage;
	bool GLX_NV_present_video;
	bool GLX_NV_video_capture;
} GLXExtensions;

/* Add _ to global symbols to avoid symbol clash with the OpenGL library */
extern glXGetFBConfigsPROC lwjgl_glXGetFBConfigs;
extern glXChooseFBConfigPROC lwjgl_glXChooseFBConfig;
extern glXGetFBConfigAttribPROC lwjgl_glXGetFBConfigAttrib;
extern glXGetVisualFromFBConfigPROC lwjgl_glXGetVisualFromFBConfig;
extern glXCreateWindowPROC lwjgl_glXCreateWindow;
extern glXDestroyWindowPROC lwjgl_glXDestroyWindow;
extern glXCreatePixmapPROC lwjgl_glXCreatePixmap;
extern glXDestroyPixmapPROC lwjgl_glXDestroyPixmap;
extern glXCreatePbufferPROC lwjgl_glXCreatePbuffer;
extern glXDestroyPbufferPROC lwjgl_glXDestroyPbuffer;
extern glXQueryDrawablePROC lwjgl_glXQueryDrawable;
extern glXCreateNewContextPROC lwjgl_glXCreateNewContext;
extern glXMakeContextCurrentPROC lwjgl_glXMakeContextCurrent;
extern glXGetCurrentReadDrawablePROC lwjgl_glXGetCurrentReadDrawable;
extern glXGetCurrentDisplayPROC lwjgl_glXGetCurrentDisplay;
extern glXQueryContextPROC lwjgl_glXQueryContext;
extern glXSelectEventPROC lwjgl_glXSelectEvent;
extern glXGetSelectedEventPROC lwjgl_glXGetSelectedEvent;

extern glXChooseVisualPROC lwjgl_glXChooseVisual;
extern glXCopyContextPROC lwjgl_glXCopyContext;
extern glXCreateContextPROC lwjgl_glXCreateContext;
extern glXCreateGLXPixmapPROC lwjgl_glXCreateGLXPixmap;
extern glXDestroyContextPROC lwjgl_glXDestroyContext;
extern glXDestroyGLXPixmapPROC lwjgl_glXDestroyGLXPixmap;
extern glXGetConfigPROC lwjgl_glXGetConfig;
extern glXGetCurrentContextPROC lwjgl_glXGetCurrentContext;
extern glXGetCurrentDrawablePROC lwjgl_glXGetCurrentDrawable;
extern glXIsDirectPROC lwjgl_glXIsDirect;
extern glXMakeCurrentPROC lwjgl_glXMakeCurrent;
extern glXQueryExtensionPROC lwjgl_glXQueryExtension;
extern glXQueryVersionPROC lwjgl_glXQueryVersion;
extern glXSwapBuffersPROC lwjgl_glXSwapBuffers;
extern glXUseXFontPROC lwjgl_glXUseXFont;
extern glXWaitGLPROC lwjgl_glXWaitGL;
extern glXWaitXPROC lwjgl_glXWaitX;
extern glXGetClientStringPROC lwjgl_glXGetClientString;
extern glXQueryServerStringPROC lwjgl_glXQueryServerString;
extern glXQueryExtensionsStringPROC lwjgl_glXQueryExtensionsString;

extern glXSwapIntervalSGIPROC lwjgl_glXSwapIntervalSGI;
extern glXSwapIntervalEXTPROC lwjgl_glXSwapIntervalEXT;

extern glXCreateContextAttribsARBPROC lwjgl_glXCreateContextAttribsARB;

/* GLX_NV_present_video */
extern glXEnumerateVideoDevicesNVPROC lwjgl_glXEnumerateVideoDevicesNV;
extern glXBindVideoDeviceNVPROC lwjgl_glXBindVideoDeviceNV;

/* GLX_NV_video_capture */
extern glXBindVideoCaptureDeviceNVPROC lwjgl_glXBindVideoCaptureDeviceNV;
extern glXEnumerateVideoCaptureDevicesNVPROC lwjgl_glXEnumerateVideoCaptureDevicesNV;
extern glXLockVideoCaptureDeviceNVPROC lwjgl_glXLockVideoCaptureDeviceNV;
extern glXQueryVideoCaptureDeviceNVPROC lwjgl_glXQueryVideoCaptureDeviceNV;
extern glXReleaseVideoCaptureDeviceNVPROC lwjgl_glXReleaseVideoCaptureDeviceNV;

extern bool extgl_InitGLX(Display *disp, int screen, GLXExtensions *extension_flags);

#endif
