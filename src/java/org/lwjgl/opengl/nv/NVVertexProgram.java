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
 * Time: 15:26:24
 * To change template for new interface use
 * Code Style | Class Templates options (Tools | IDE Options).
 */
package org.lwjgl.opengl.nv;

public interface NVVertexProgram
{
  public static final int VERTEX_PROGRAM_NV                                    = 0x8620;
  public static final int VERTEX_PROGRAM_POINT_SIZE_NV                         = 0x8642;
  public static final int VERTEX_PROGRAM_TWO_SIDE_NV                           = 0x8643;
  public static final int VERTEX_STATE_PROGRAM_NV                              = 0x8621;
  public static final int ATTRIB_ARRAY_SIZE_NV                                 = 0x8623;
  public static final int ATTRIB_ARRAY_STRIDE_NV                               = 0x8624;
  public static final int ATTRIB_ARRAY_TYPE_NV                                 = 0x8625;
  public static final int CURRENT_ATTRIB_NV                                    = 0x8626;
  public static final int PROGRAM_PARAMETER_NV                                 = 0x8644;
  public static final int ATTRIB_ARRAY_POINTER_NV                              = 0x8645;
  public static final int PROGRAM_TARGET_NV                                    = 0x8646;
  public static final int PROGRAM_LENGTH_NV                                    = 0x8627;
  public static final int PROGRAM_RESIDENT_NV                                  = 0x8647;
  public static final int PROGRAM_STRING_NV                                    = 0x8628;
  public static final int TRACK_MATRIX_NV                                      = 0x8648;
  public static final int TRACK_MATRIX_TRANSFORM_NV                            = 0x8649;
  public static final int MAX_TRACK_MATRIX_STACK_DEPTH_NV                      = 0x862E;
  public static final int MAX_TRACK_MATRICES_NV                                = 0x862F;
  public static final int CURRENT_MATRIX_STACK_DEPTH_NV                        = 0x8640;
  public static final int CURRENT_MATRIX_NV                                    = 0x8641;
  public static final int VERTEX_PROGRAM_BINDING_NV                            = 0x864A;
  public static final int PROGRAM_ERROR_POSITION_NV                            = 0x864B;
  public static final int MODELVIEW_PROJECTION_NV                              = 0x8629;
  public static final int MATRIX0_NV                                           = 0x8630;
  public static final int MATRIX1_NV                                           = 0x8631;
  public static final int MATRIX2_NV                                           = 0x8632;
  public static final int MATRIX3_NV                                           = 0x8633;
  public static final int MATRIX4_NV                                           = 0x8634;
  public static final int MATRIX5_NV                                           = 0x8635;
  public static final int MATRIX6_NV                                           = 0x8636;
  public static final int MATRIX7_NV                                           = 0x8637;
  public static final int IDENTITY_NV                                          = 0x862A;
  public static final int INVERSE_NV                                           = 0x862B;
  public static final int TRANSPOSE_NV                                         = 0x862C;
  public static final int INVERSE_TRANSPOSE_NV                                 = 0x862D;
  public static final int VERTEX_ATTRIB_ARRAY0_NV                              = 0x8650;
  public static final int VERTEX_ATTRIB_ARRAY1_NV                              = 0x8651;
  public static final int VERTEX_ATTRIB_ARRAY2_NV                              = 0x8652;
  public static final int VERTEX_ATTRIB_ARRAY3_NV                              = 0x8653;
  public static final int VERTEX_ATTRIB_ARRAY4_NV                              = 0x8654;
  public static final int VERTEX_ATTRIB_ARRAY5_NV                              = 0x8655;
  public static final int VERTEX_ATTRIB_ARRAY6_NV                              = 0x8656;
  public static final int VERTEX_ATTRIB_ARRAY7_NV                              = 0x8657;
  public static final int VERTEX_ATTRIB_ARRAY8_NV                              = 0x8658;
  public static final int VERTEX_ATTRIB_ARRAY9_NV                              = 0x8659;
  public static final int VERTEX_ATTRIB_ARRAY10_NV                             = 0x865A;
  public static final int VERTEX_ATTRIB_ARRAY11_NV                             = 0x865B;
  public static final int VERTEX_ATTRIB_ARRAY12_NV                             = 0x865C;
  public static final int VERTEX_ATTRIB_ARRAY13_NV                             = 0x865D;
  public static final int VERTEX_ATTRIB_ARRAY14_NV                             = 0x865E;
  public static final int VERTEX_ATTRIB_ARRAY15_NV                             = 0x865F;
  public static final int MAP1_VERTEX_ATTRIB0_4_NV                             = 0x8660;
  public static final int MAP1_VERTEX_ATTRIB1_4_NV                             = 0x8661;
  public static final int MAP1_VERTEX_ATTRIB2_4_NV                             = 0x8662;
  public static final int MAP1_VERTEX_ATTRIB3_4_NV                             = 0x8663;
  public static final int MAP1_VERTEX_ATTRIB4_4_NV                             = 0x8664;
  public static final int MAP1_VERTEX_ATTRIB5_4_NV                             = 0x8665;
  public static final int MAP1_VERTEX_ATTRIB6_4_NV                             = 0x8666;
  public static final int MAP1_VERTEX_ATTRIB7_4_NV                             = 0x8667;
  public static final int MAP1_VERTEX_ATTRIB8_4_NV                             = 0x8668;
  public static final int MAP1_VERTEX_ATTRIB9_4_NV                             = 0x8669;
  public static final int MAP1_VERTEX_ATTRIB10_4_NV                            = 0x866A;
  public static final int MAP1_VERTEX_ATTRIB11_4_NV                            = 0x866B;
  public static final int MAP1_VERTEX_ATTRIB12_4_NV                            = 0x866C;
  public static final int MAP1_VERTEX_ATTRIB13_4_NV                            = 0x866D;
  public static final int MAP1_VERTEX_ATTRIB14_4_NV                            = 0x866E;
  public static final int MAP1_VERTEX_ATTRIB15_4_NV                            = 0x866F;
  public static final int MAP2_VERTEX_ATTRIB0_4_NV                             = 0x8670;
  public static final int MAP2_VERTEX_ATTRIB1_4_NV                             = 0x8671;
  public static final int MAP2_VERTEX_ATTRIB2_4_NV                             = 0x8672;
  public static final int MAP2_VERTEX_ATTRIB3_4_NV                             = 0x8673;
  public static final int MAP2_VERTEX_ATTRIB4_4_NV                             = 0x8674;
  public static final int MAP2_VERTEX_ATTRIB5_4_NV                             = 0x8675;
  public static final int MAP2_VERTEX_ATTRIB6_4_NV                             = 0x8676;
  public static final int MAP2_VERTEX_ATTRIB7_4_NV                             = 0x8677;
  public static final int MAP2_VERTEX_ATTRIB8_4_NV                             = 0x8678;
  public static final int MAP2_VERTEX_ATTRIB9_4_NV                             = 0x8679;
  public static final int MAP2_VERTEX_ATTRIB10_4_NV                            = 0x867A;
  public static final int MAP2_VERTEX_ATTRIB11_4_NV                            = 0x867B;
  public static final int MAP2_VERTEX_ATTRIB12_4_NV                            = 0x867C;
  public static final int MAP2_VERTEX_ATTRIB13_4_NV                            = 0x867D;
  public static final int MAP2_VERTEX_ATTRIB14_4_NV                            = 0x867E;
  public static final int MAP2_VERTEX_ATTRIB15_4_NV                            = 0x867F;
}
