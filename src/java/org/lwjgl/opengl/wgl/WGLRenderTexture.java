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
 * Time: 16:08:41
 * To change template for new interface use
 * Code Style | Class Templates options (Tools | IDE Options).
 */
package org.lwjgl.opengl.wgl;

public interface WGLRenderTexture
{
  public static final int WBIND_TO_TEXTURE_RGB_ARB                             = 0x2070;
  public static final int WBIND_TO_TEXTURE_RGBA_ARB                            = 0x2071;
  public static final int WTEXTURE_FORMAT_ARB                                  = 0x2072;
  public static final int WTEXTURE_TARGET_ARB                                  = 0x2073;
  public static final int WMIPMAP_TEXTURE_ARB                                  = 0x2074;
  public static final int WTEXTURE_RGB_ARB                                     = 0x2075;
  public static final int WTEXTURE_RGBA_ARB                                    = 0x2076;
  public static final int WNO_TEXTURE_ARB                                      = 0x2077;
  public static final int WTEXTURE_CUBE_MAP_ARB                                = 0x2078;
  public static final int WTEXTURE_1D_ARB                                      = 0x2079;
  public static final int WTEXTURE_2D_ARB                                      = 0x207A;
  public static final int WMIPMAP_LEVEL_ARB                                    = 0x207B;
  public static final int WCUBE_MAP_FACE_ARB                                   = 0x207C;
  public static final int WTEXTURE_CUBE_MAP_POSITIVE_X_ARB                     = 0x207D;
  public static final int WTEXTURE_CUBE_MAP_NEGATIVE_X_ARB                     = 0x207E;
  public static final int WTEXTURE_CUBE_MAP_POSITIVE_Y_ARB                     = 0x207F;
  public static final int WTEXTURE_CUBE_MAP_NEGATIVE_Y_ARB                     = 0x2080;
  public static final int WTEXTURE_CUBE_MAP_POSITIVE_Z_ARB                     = 0x2081;
  public static final int WTEXTURE_CUBE_MAP_NEGATIVE_Z_ARB                     = 0x2082;
  public static final int WFRONT_LEFT_ARB                                      = 0x2083;
  public static final int WFRONT_RIGHT_ARB                                     = 0x2084;
  public static final int WBACK_LEFT_ARB                                       = 0x2085;
  public static final int WBACK_RIGHT_ARB                                      = 0x2086;
  public static final int WAUX0_ARB                                            = 0x2087;
  public static final int WAUX1_ARB                                            = 0x2088;
  public static final int WAUX2_ARB                                            = 0x2089;
  public static final int WAUX3_ARB                                            = 0x208A;
  public static final int WAUX4_ARB                                            = 0x208B;
  public static final int WAUX5_ARB                                            = 0x208C;
  public static final int WAUX6_ARB                                            = 0x208D;
  public static final int WAUX7_ARB                                            = 0x208E;
  public static final int WAUX8_ARB                                            = 0x208F;
  public static final int WAUX9_ARB                                            = 0x2090;

}
