/* 
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
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
/*
 * Created by IntelliJ IDEA.
 * User: nj
 * Date: 12-08-2002
 * Time: 16:07:42
 * To change template for new interface use
 * Code Style | Class Templates options (Tools | IDE Options).
 */
package org.lwjgl.opengl.wgl;

public interface WGLPixelFormat
{
	public static final int WGL_WNUMBER_PIXEL_FORMATS_ARB                            = 0x2000;
	public static final int WGL_WDRAW_TO_WINDOW_ARB                                  = 0x2001;
	public static final int WGL_WDRAW_TO_BITMAP_ARB                                  = 0x2002;
	public static final int WGL_WACCELERATION_ARB                                    = 0x2003;
	public static final int WGL_WNEED_PALETTE_ARB                                    = 0x2004;
	public static final int WGL_WNEED_SYSTEM_PALETTE_ARB                             = 0x2005;
	public static final int WGL_WSWAP_LAYER_BUFFERS_ARB                              = 0x2006;
	public static final int WGL_WSWAP_METHOD_ARB                                     = 0x2007;
	public static final int WGL_WNUMBER_OVERLAYS_ARB                                 = 0x2008;
	public static final int WGL_WNUMBER_UNDERLAYS_ARB                                = 0x2009;
	public static final int WGL_WTRANSPARENT_ARB                                     = 0x200A;
	public static final int WGL_WTRANSPARENT_RED_VALUE_ARB                           = 0x2037;
	public static final int WGL_WTRANSPARENT_GREEN_VALUE_ARB                         = 0x2038;
	public static final int WGL_WTRANSPARENT_BLUE_VALUE_ARB                          = 0x2039;
	public static final int WGL_WTRANSPARENT_ALPHA_VALUE_ARB                         = 0x203A;
	public static final int WGL_WTRANSPARENT_INDEX_VALUE_ARB                         = 0x203B;
	public static final int WGL_WSHARE_DEPTH_ARB                                     = 0x200C;
	public static final int WGL_WSHARE_STENCIL_ARB                                   = 0x200D;
	public static final int WGL_WSHARE_ACCUM_ARB                                     = 0x200E;
	public static final int WGL_WSUPPORT_GDI_ARB                                     = 0x200F;
	public static final int WGL_WSUPPORT_OPENARB                                  = 0x2010;
	public static final int WGL_WDOUBLE_BUFFER_ARB                                   = 0x2011;
	public static final int WGL_WSTEREO_ARB                                          = 0x2012;
	public static final int WGL_WPIXEL_TYPE_ARB                                      = 0x2013;
	public static final int WGL_WCOLOR_BITS_ARB                                      = 0x2014;
	public static final int WGL_WRED_BITS_ARB                                        = 0x2015;
	public static final int WGL_WRED_SHIFT_ARB                                       = 0x2016;
	public static final int WGL_WGREEN_BITS_ARB                                      = 0x2017;
	public static final int WGL_WGREEN_SHIFT_ARB                                     = 0x2018;
	public static final int WGL_WBLUE_BITS_ARB                                       = 0x2019;
	public static final int WGL_WBLUE_SHIFT_ARB                                      = 0x201A;
	public static final int WGL_WALPHA_BITS_ARB                                      = 0x201B;
	public static final int WGL_WALPHA_SHIFT_ARB                                     = 0x201C;
	public static final int WGL_WACCUM_BITS_ARB                                      = 0x201D;
	public static final int WGL_WACCUM_RED_BITS_ARB                                  = 0x201E;
	public static final int WGL_WACCUM_GREEN_BITS_ARB                                = 0x201F;
	public static final int WGL_WACCUM_BLUE_BITS_ARB                                 = 0x2020;
	public static final int WGL_WACCUM_ALPHA_BITS_ARB                                = 0x2021;
	public static final int WGL_WDEPTH_BITS_ARB                                      = 0x2022;
	public static final int WGL_WSTENCIL_BITS_ARB                                    = 0x2023;
	public static final int WGL_WAUX_BUFFERS_ARB                                     = 0x2024;
	public static final int WGL_WNO_ACCELERATION_ARB                                 = 0x2025;
	public static final int WGL_WGENERIC_ACCELERATION_ARB                            = 0x2026;
	public static final int WGL_WFULL_ACCELERATION_ARB                               = 0x2027;
	public static final int WGL_WSWAP_EXCHANGE_ARB                                   = 0x2028;
	public static final int WGL_WSWAP_COPY_ARB                                       = 0x2029;
	public static final int WGL_WSWAP_UNDEFINED_ARB                                  = 0x202A;
	public static final int WGL_WTYPE_RGBA_ARB                                       = 0x202B;
	public static final int WGL_WTYPE_COLORINDEX_ARB                                 = 0x202C;
}
