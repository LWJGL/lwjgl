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
 * Time: 15:20:54
 * To change template for new interface use
 * Code Style | Class Templates options (Tools | IDE Options).
 */
package org.lwjgl.opengl.nv;

public interface NVRegisterCombiners
{
  public static final int REGISTER_COMBINERS_NV                                = 0x8522;
  public static final int COMBINER0_NV                                         = 0x8550;
  public static final int COMBINER1_NV                                         = 0x8551;
  public static final int COMBINER2_NV                                         = 0x8552;
  public static final int COMBINER3_NV                                         = 0x8553;
  public static final int COMBINER4_NV                                         = 0x8554;
  public static final int COMBINER5_NV                                         = 0x8555;
  public static final int COMBINER6_NV                                         = 0x8556;
  public static final int COMBINER7_NV                                         = 0x8557;
  public static final int VARIABLE_A_NV                                        = 0x8523;
  public static final int VARIABLE_B_NV                                        = 0x8524;
  public static final int VARIABLE_C_NV                                        = 0x8525;
  public static final int VARIABLE_D_NV                                        = 0x8526;
  public static final int VARIABLE_E_NV                                        = 0x8527;
  public static final int VARIABLE_F_NV                                        = 0x8528;
  public static final int VARIABLE_G_NV                                        = 0x8529;
  public static final int CONSTANT_COLOR0_NV                                   = 0x852A;
  public static final int CONSTANT_COLOR1_NV                                   = 0x852B;
  public static final int PRIMARY_COLOR_NV                                     = 0x852C;
  public static final int SECONDARY_COLOR_NV                                   = 0x852D;
  public static final int SPARE0_NV                                            = 0x852E;
  public static final int SPARE1_NV                                            = 0x852F;
  public static final int UNSIGNED_IDENTITY_NV                                 = 0x8536;
  public static final int UNSIGNED_INVERT_NV                                   = 0x8537;
  public static final int EXPAND_NORMAL_NV                                     = 0x8538;
  public static final int EXPAND_NEGATE_NV                                     = 0x8539;
  public static final int HALF_BIAS_NORMAL_NV                                  = 0x853A;
  public static final int HALF_BIAS_NEGATE_NV                                  = 0x853B;
  public static final int SIGNED_IDENTITY_NV                                   = 0x853C;
  public static final int SIGNED_NEGATE_NV                                     = 0x853D;
  public static final int E_TIMES_F_NV                                         = 0x8531;
  public static final int SPARE0_PLUS_SECONDARY_COLOR_NV                       = 0x8532;
  public static final int SCALE_BY_TWO_NV                                      = 0x853E;
  public static final int SCALE_BY_FOUR_NV                                     = 0x853F;
  public static final int SCALE_BY_ONE_HALF_NV                                 = 0x8540;
  public static final int BIAS_BY_NEGATIVE_ONE_HALF_NV                         = 0x8541;
  public static final int DISCARD_NV                                           = 0x8530;
  public static final int COMBINER_INPUT_NV                                    = 0x8542;
  public static final int COMBINER_MAPPING_NV                                  = 0x8543;
  public static final int COMBINER_COMPONENT_USAGE_NV                          = 0x8544;
  public static final int COMBINER_AB_DOT_PRODUCT_NV                           = 0x8545;
  public static final int COMBINER_CD_DOT_PRODUCT_NV                           = 0x8546;
  public static final int COMBINER_MUX_SUM_NV                                  = 0x8547;
  public static final int COMBINER_SCALE_NV                                    = 0x8548;
  public static final int COMBINER_BIAS_NV                                     = 0x8549;
  public static final int COMBINER_AB_OUTPUT_NV                                = 0x854A;
  public static final int COMBINER_CD_OUTPUT_NV                                = 0x854B;
  public static final int COMBINER_SUM_OUTPUT_NV                               = 0x854C;
  public static final int NUM_GENERAL_COMBINERS_NV                             = 0x854E;
  public static final int COLOR_SUM_CLAMP_NV                                   = 0x854F;
  public static final int MAX_GENERAL_COMBINERS_NV                             = 0x854D;
}
