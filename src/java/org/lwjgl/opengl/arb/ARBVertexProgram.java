/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
 * Time: 16:02:30
 * To change template for new interface use
 * Code Style | Class Templates options (Tools | IDE Options).
 */
package org.lwjgl.opengl.arb;

public interface ARBVertexProgram
{
  public static final int VERTEX_PROGRAM_ARB                                   = 0x8620;
  public static final int VERTEX_PROGRAM_POINT_SIZE_ARB                        = 0x8642;
  public static final int VERTEX_PROGRAM_TWO_SIDE_ARB                          = 0x8643;
  public static final int COLOR_SUM_ARB                                        = 0x8458;
  public static final int PROGRAM_FORMAT_ASCII_ARB                             = 0x8875;
  public static final int VERTEX_ATTRIB_ARRAY_ENABLED_ARB                      = 0x8622;
  public static final int VERTEX_ATTRIB_ARRAY_SIZE_ARB                         = 0x8623;
  public static final int VERTEX_ATTRIB_ARRAY_STRIDE_ARB                       = 0x8624;
  public static final int VERTEX_ATTRIB_ARRAY_TYPE_ARB                         = 0x8625;
  public static final int VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB                   = 0x886A;
  public static final int CURRENT_VERTEX_ATTRIB_ARB                            = 0x8626;
  public static final int VERTEX_ATTRIB_ARRAY_POINTER_ARB                      = 0x8645;
  public static final int PROGRAM_LENGTH_ARB                                   = 0x8627;
  public static final int PROGRAM_FORMAT_ARB                                   = 0x8876;
  public static final int PROGRAM_BINDING_ARB                                  = 0x8677;
  public static final int PROGRAM_INSTRUCTIONS_ARB                             = 0x88A0;
  public static final int MAX_PROGRAM_INSTRUCTIONS_ARB                         = 0x88A1;
  public static final int PROGRAM_NATIVE_INSTRUCTIONS_ARB                      = 0x88A2;
  public static final int MAX_PROGRAM_NATIVE_INSTRUCTIONS_ARB                  = 0x88A3;
  public static final int PROGRAM_TEMPORARIES_ARB                              = 0x88A4;
  public static final int MAX_PROGRAM_TEMPORARIES_ARB                          = 0x88A5;
  public static final int PROGRAM_NATIVE_TEMPORARIES_ARB                       = 0x88A6;
  public static final int MAX_PROGRAM_NATIVE_TEMPORARIES_ARB                   = 0x88A7;
  public static final int PROGRAM_PARAMETERS_ARB                               = 0x88A8;
  public static final int MAX_PROGRAM_PARAMETERS_ARB                           = 0x88A9;
  public static final int PROGRAM_NATIVE_PARAMETERS_ARB                        = 0x88AA;
  public static final int MAX_PROGRAM_NATIVE_PARAMETERS_ARB                    = 0x88AB;
  public static final int PROGRAM_ATTRIBS_ARB                                  = 0x88AC;
  public static final int MAX_PROGRAM_ATTRIBS_ARB                              = 0x88AD;
  public static final int PROGRAM_NATIVE_ATTRIBS_ARB                           = 0x88AE;
  public static final int MAX_PROGRAM_NATIVE_ATTRIBS_ARB                       = 0x88AF;
  public static final int PROGRAM_ADDRESS_REGISTERS_ARB                        = 0x88B0;
  public static final int MAX_PROGRAM_ADDRESS_REGISTERS_ARB                    = 0x88B1;
  public static final int PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB                 = 0x88B2;
  public static final int MAX_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB             = 0x88B3;
  public static final int MAX_PROGRAM_LOCAL_PARAMETERS_ARB                     = 0x88B4;
  public static final int MAX_PROGRAM_ENV_PARAMETERS_ARB                       = 0x88B5;
  public static final int PROGRAM_UNDER_NATIVE_LIMITS_ARB                      = 0x88B6;
  public static final int PROGRAM_STRING_ARB                                   = 0x8628;
  public static final int PROGRAM_ERROR_POSITION_ARB                           = 0x864B;
  public static final int CURRENT_MATRIX_ARB                                   = 0x8641;
  public static final int TRANSPOSE_CURRENT_MATRIX_ARB                         = 0x88B7;
  public static final int CURRENT_MATRIX_STACK_DEPTH_ARB                       = 0x8640;
  public static final int MAX_VERTEX_ATTRIBS_ARB                               = 0x8869;
  public static final int MAX_PROGRAM_MATRICES_ARB                             = 0x862F;
  public static final int MAX_PROGRAM_MATRIX_STACK_DEPTH_ARB                   = 0x862E;
  public static final int PROGRAM_ERROR_STRING_ARB                             = 0x8874;
  public static final int MATRIX0_ARB                                          = 0x88C0;
  public static final int MATRIX1_ARB                                          = 0x88C1;
  public static final int MATRIX2_ARB                                          = 0x88C2;
  public static final int MATRIX3_ARB                                          = 0x88C3;
  public static final int MATRIX4_ARB                                          = 0x88C4;
  public static final int MATRIX5_ARB                                          = 0x88C5;
  public static final int MATRIX6_ARB                                          = 0x88C6;
  public static final int MATRIX7_ARB                                          = 0x88C7;
  public static final int MATRIX8_ARB                                          = 0x88C8;
  public static final int MATRIX9_ARB                                          = 0x88C9;
  public static final int MATRIX10_ARB                                         = 0x88CA;
  public static final int MATRIX11_ARB                                         = 0x88CB;
  public static final int MATRIX12_ARB                                         = 0x88CC;
  public static final int MATRIX13_ARB                                         = 0x88CD;
  public static final int MATRIX14_ARB                                         = 0x88CE;
  public static final int MATRIX15_ARB                                         = 0x88CF;
  public static final int MATRIX16_ARB                                         = 0x88D0;
  public static final int MATRIX17_ARB                                         = 0x88D1;
  public static final int MATRIX18_ARB                                         = 0x88D2;
  public static final int MATRIX19_ARB                                         = 0x88D3;
  public static final int MATRIX20_ARB                                         = 0x88D4;
  public static final int MATRIX21_ARB                                         = 0x88D5;
  public static final int MATRIX22_ARB                                         = 0x88D6;
  public static final int MATRIX23_ARB                                         = 0x88D7;
  public static final int MATRIX24_ARB                                         = 0x88D8;
  public static final int MATRIX25_ARB                                         = 0x88D9;
  public static final int MATRIX26_ARB                                         = 0x88DA;
  public static final int MATRIX27_ARB                                         = 0x88DB;
  public static final int MATRIX28_ARB                                         = 0x88DC;
  public static final int MATRIX29_ARB                                         = 0x88DD;
  public static final int MATRIX30_ARB                                         = 0x88DE;
  public static final int MATRIX31_ARB                                         = 0x88DF;
}
