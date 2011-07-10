/* ----------------------------------------------------------------------------
Copyright (c) 2001-2002, Lev Povalahev
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
	Lev Povalahev

	levp@gmx.net

	http://www.uni-karlsruhe.de/~uli2/

*/

#ifndef _EXTGL_WGL_H
#define _EXTGL_WGL_H

#include <windows.h>
#include "extgl.h"
#include "common_tools.h"

/*-------------------------------------------------------------------*/
/*------------WGL_EXT_EXTENSION_STRING-------------------------------*/
/*-------------------------------------------------------------------*/

typedef const char* (APIENTRY * wglGetExtensionsStringEXTPROC) ();

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_EXTENSION_STRING-------------------------------*/
/*-------------------------------------------------------------------*/

typedef const char* (APIENTRY * wglGetExtensionsStringARBPROC) (HDC hdc);

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

typedef BOOL (APIENTRY * wglBindTexImageARBPROC) (HPBUFFERARB hPbuffer, int iBuffer);
typedef BOOL (APIENTRY * wglReleaseTexImageARBPROC) (HPBUFFERARB hPbuffer, int iBuffer);
typedef BOOL (APIENTRY * wglSetPbufferAttribARBPROC) (HPBUFFERARB hPbuffer, const int *piAttribList);

/*-------------------------------------------------------------------*/
/*------------WGL_EXT_SWAP_CONTROL-----------------------------------*/
/*-------------------------------------------------------------------*/

typedef BOOL (APIENTRY * wglSwapIntervalEXTPROC) (int interval);
typedef int (APIENTRY * wglGetSwapIntervalEXTPROC) (void);

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_MAKE_CURRENT_READ------------------------------*/
/*-------------------------------------------------------------------*/

#define ERROR_INVALID_PIXEL_TYPE_ARB                            0x2043
#define ERROR_INCOMPATIBLE_DEVICE_CONTEXTS_ARB	                0x2054

typedef BOOL (APIENTRY * wglMakeContextCurrentARBPROC) (HDC hDrawDC, HDC hReadDC, HGLRC hglrc);
typedef HDC (APIENTRY * wglGetCurrentReadDCARBPROC) (void);

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_MULTISAMPLE------------------------------------*/
/*-------------------------------------------------------------------*/

#define WGL_SAMPLE_BUFFERS_ARB                                  0x2041
#define WGL_SAMPLES_ARB                                         0x2042

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_pixel_format_float ----------------------------*/
/*-------------------------------------------------------------------*/

#define WGL_TYPE_RGBA_FLOAT_ARB									0x21A0

/*-------------------------------------------------------------------*/
/*------------WGL_ATI_pixel_format_float ----------------------------*/
/*-------------------------------------------------------------------*/

#define WGL_TYPE_RGBA_FLOAT_ATI									0x21A0

/*------------------------------------------------------------------*/
/*------------ WGL_ARB_framebuffer_sRGB ----------------------------*/
/*------------------------------------------------------------------*/

#define WGL_FRAMEBUFFER_SRGB_CAPABLE_ARB						0x20A9

/*---------------------------------------------------------------------------*/
/*------------ WGL_EXT_pixel_format_packed_float ----------------------------*/
/*---------------------------------------------------------------------------*/

#define WGL_TYPE_RGBA_UNSIGNED_FLOAT_EXT              			0x20A8

/*----------------------------------------------------------------*/
/*------------ WGL_ARB_create_context ----------------------------*/
/*----------------------------------------------------------------*/

#define WGL_CONTEXT_MAJOR_VERSION_ARB							0x2091
#define WGL_CONTEXT_MINOR_VERSION_ARB							0x2092
#define WGL_CONTEXT_LAYER_PLANE_ARB								0x2093
#define WGL_CONTEXT_FLAGS_ARB									0x2094

#define WGL_CONTEXT_DEBUG_BIT_ARB								0x0001
#define WGL_CONTEXT_FORWARD_COMPATIBLE_BIT_ARB					0x0002

#define ERROR_INVALID_VERSION_ARB								0x2095

typedef HGLRC (APIENTRY * wglCreateContextAttribsARBPROC) (HDC hDC, HGLRC hShareContext, const int *attribList);

/*---------------------------------------------------------------------*/
/*------------ WGL_NV_multisample_coverage ----------------------------*/
/*---------------------------------------------------------------------*/

#define WGL_COVERAGE_SAMPLES_NV                                 0x2042
#define WGL_COLOR_SAMPLES_NV                                    0x20B9

/*--------------------------------------------------------------*/
/*------------ WGL_NV_present_video ----------------------------*/
/*--------------------------------------------------------------*/

DECLARE_HANDLE(HVIDEOOUTPUTDEVICENV);

#define WGL_NUM_VIDEO_SLOTS_NV                                  0x20F0

typedef int (APIENTRY * wglEnumerateVideoDevicesNVPROC) (HDC hDc, HVIDEOOUTPUTDEVICENV *phDeviceList);
typedef BOOL (APIENTRY * wglBindVideoDeviceNVPROC) (HDC hDc, unsigned int uVideoSlot, HVIDEOOUTPUTDEVICENV hVideoDevice, const int *piAttribList);
typedef BOOL (APIENTRY * wglQueryCurrentContextNVPROC) (int iAttribute, int *piValue);

/*--------------------------------------------------------------*/
/*------------ WGL_NV_video_capture ----------------------------*/
/*--------------------------------------------------------------*/

DECLARE_HANDLE(HVIDEOINPUTDEVICENV);

#define WGL_NUM_VIDEO_SLOTS_NV                                  0x20F0

typedef BOOL (APIENTRY * wglBindVideoCaptureDeviceNVPROC) (UINT uVideoSlot, HVIDEOINPUTDEVICENV hDevice);
typedef UINT (APIENTRY * wglEnumerateVideoCaptureDevicesNVPROC) (HDC hDc, HVIDEOINPUTDEVICENV *phDeviceList);
typedef BOOL (APIENTRY * wglLockVideoCaptureDeviceNVPROC) (HDC hDc, HVIDEOINPUTDEVICENV hDevice);
typedef BOOL (APIENTRY * wglQueryVideoCaptureDeviceNVPROC) (HDC hDc, HVIDEOINPUTDEVICENV hDevice, int iAttribute, int *piValue);
typedef BOOL (APIENTRY * wglReleaseVideoCaptureDeviceNVPROC) (HDC hDc, HVIDEOINPUTDEVICENV hDevice);

/*---------------------------------------------------------------------*/

typedef struct {
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
    bool WGL_ARB_pixel_format_float;
    bool WGL_ATI_pixel_format_float;
    bool WGL_ARB_framebuffer_sRGB;
	bool WGL_EXT_pixel_format_packed_float;
    bool WGL_ARB_create_context;
    bool WGL_NV_multisample_coverage;
    bool WGL_NV_present_video;
    bool WGL_NV_video_capture;

	wglGetExtensionsStringEXTPROC wglGetExtensionsStringEXT;

	wglGetExtensionsStringARBPROC wglGetExtensionsStringARB;

	wglCreatePbufferARBPROC wglCreatePbufferARB;
	wglGetPbufferDCARBPROC wglGetPbufferDCARB;
	wglReleasePbufferDCARBPROC wglReleasePbufferDCARB;
	wglDestroyPbufferARBPROC wglDestroyPbufferARB;
	wglQueryPbufferARBPROC wglQueryPbufferARB;

	wglGetPixelFormatAttribivARBPROC wglGetPixelFormatAttribivARB;
	wglGetPixelFormatAttribfvARBPROC wglGetPixelFormatAttribfvARB;
	wglChoosePixelFormatARBPROC wglChoosePixelFormatARB;

	wglBindTexImageARBPROC wglBindTexImageARB;
	wglReleaseTexImageARBPROC wglReleaseTexImageARB;
	wglSetPbufferAttribARBPROC wglSetPbufferAttribARB;

	wglSwapIntervalEXTPROC wglSwapIntervalEXT;
	wglGetSwapIntervalEXTPROC wglGetSwapIntervalEXT;

	wglMakeContextCurrentARBPROC wglMakeContextCurrentARB;
	wglGetCurrentReadDCARBPROC wglGetCurrentReadDCARB;

	wglCreateContextAttribsARBPROC wglCreateContextAttribsARB;

	wglEnumerateVideoDevicesNVPROC wglEnumerateVideoDevicesNV;
	wglBindVideoDeviceNVPROC wglBindVideoDeviceNV;
	wglQueryCurrentContextNVPROC wglQueryCurrentContextNV;

	wglBindVideoCaptureDeviceNVPROC wglBindVideoCaptureDeviceNV;
	wglEnumerateVideoCaptureDevicesNVPROC wglEnumerateVideoCaptureDevicesNV;
	wglLockVideoCaptureDeviceNVPROC wglLockVideoCaptureDeviceNV;
	wglQueryVideoCaptureDeviceNVPROC wglQueryVideoCaptureDeviceNV;
	wglReleaseVideoCaptureDeviceNVPROC wglReleaseVideoCaptureDeviceNV;
} WGLExtensions;

extern void extgl_InitWGL(WGLExtensions *extensions);


#endif
