/*
 * Copyright (c) 2002-2004 LWJGL Project
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
/*
 * Note: 2X_BIT_ATI, 4X_BIT_ATI and 8X_BIT_ATI has been changed to X2_BIT_ATI, X4_BIT_ATI and X8_BIT_ATI
 * because variables cannot start with a number.
 *
 */
package org.lwjgl.opengl;

import java.nio.FloatBuffer;

import org.lwjgl.generator.*;

public interface ATI_fragment_shader {
	public static final int GL_FRAGMENT_SHADER_ATI = 0x8920;
	public static final int GL_REG_0_ATI = 0x8921;
	public static final int GL_REG_1_ATI = 0x8922;
	public static final int GL_REG_2_ATI = 0x8923;
	public static final int GL_REG_3_ATI = 0x8924;
	public static final int GL_REG_4_ATI = 0x8925;
	public static final int GL_REG_5_ATI = 0x8926;
	public static final int GL_REG_6_ATI = 0x8927;
	public static final int GL_REG_7_ATI = 0x8928;
	public static final int GL_REG_8_ATI = 0x8929;
	public static final int GL_REG_9_ATI = 0x892A;
	public static final int GL_REG_10_ATI = 0x892B;
	public static final int GL_REG_11_ATI = 0x892C;
	public static final int GL_REG_12_ATI = 0x892D;
	public static final int GL_REG_13_ATI = 0x892E;
	public static final int GL_REG_14_ATI = 0x892F;
	public static final int GL_REG_15_ATI = 0x8930;
	public static final int GL_REG_16_ATI = 0x8931;
	public static final int GL_REG_17_ATI = 0x8932;
	public static final int GL_REG_18_ATI = 0x8933;
	public static final int GL_REG_19_ATI = 0x8934;
	public static final int GL_REG_20_ATI = 0x8935;
	public static final int GL_REG_21_ATI = 0x8936;
	public static final int GL_REG_22_ATI = 0x8937;
	public static final int GL_REG_23_ATI = 0x8938;
	public static final int GL_REG_24_ATI = 0x8939;
	public static final int GL_REG_25_ATI = 0x893A;
	public static final int GL_REG_26_ATI = 0x893B;
	public static final int GL_REG_27_ATI = 0x893C;
	public static final int GL_REG_28_ATI = 0x893D;
	public static final int GL_REG_29_ATI = 0x893E;
	public static final int GL_REG_30_ATI = 0x893F;
	public static final int GL_REG_31_ATI = 0x8940;
	public static final int GL_CON_0_ATI = 0x8941;
	public static final int GL_CON_1_ATI = 0x8942;
	public static final int GL_CON_2_ATI = 0x8943;
	public static final int GL_CON_3_ATI = 0x8944;
	public static final int GL_CON_4_ATI = 0x8945;
	public static final int GL_CON_5_ATI = 0x8946;
	public static final int GL_CON_6_ATI = 0x8947;
	public static final int GL_CON_7_ATI = 0x8948;
	public static final int GL_CON_8_ATI = 0x8949;
	public static final int GL_CON_9_ATI = 0x894A;
	public static final int GL_CON_10_ATI = 0x894B;
	public static final int GL_CON_11_ATI = 0x894C;
	public static final int GL_CON_12_ATI = 0x894D;
	public static final int GL_CON_13_ATI = 0x894E;
	public static final int GL_CON_14_ATI = 0x894F;
	public static final int GL_CON_15_ATI = 0x8950;
	public static final int GL_CON_16_ATI = 0x8951;
	public static final int GL_CON_17_ATI = 0x8952;
	public static final int GL_CON_18_ATI = 0x8953;
	public static final int GL_CON_19_ATI = 0x8954;
	public static final int GL_CON_20_ATI = 0x8955;
	public static final int GL_CON_21_ATI = 0x8956;
	public static final int GL_CON_22_ATI = 0x8957;
	public static final int GL_CON_23_ATI = 0x8958;
	public static final int GL_CON_24_ATI = 0x8959;
	public static final int GL_CON_25_ATI = 0x895A;
	public static final int GL_CON_26_ATI = 0x895B;
	public static final int GL_CON_27_ATI = 0x895C;
	public static final int GL_CON_28_ATI = 0x895D;
	public static final int GL_CON_29_ATI = 0x895E;
	public static final int GL_CON_30_ATI = 0x895F;
	public static final int GL_CON_31_ATI = 0x8960;
	public static final int GL_MOV_ATI = 0x8961;
	public static final int GL_ADD_ATI = 0x8963;
	public static final int GL_MUL_ATI = 0x8964;
	public static final int GL_SUB_ATI = 0x8965;
	public static final int GL_DOT3_ATI = 0x8966;
	public static final int GL_DOT4_ATI = 0x8967;
	public static final int GL_MAD_ATI = 0x8968;
	public static final int GL_LERP_ATI = 0x8969;
	public static final int GL_CND_ATI = 0x896A;
	public static final int GL_CND0_ATI = 0x896B;
	public static final int GL_DOT2_ADD_ATI = 0x896C;
	public static final int GL_SECONDARY_INTERPOLATOR_ATI = 0x896D;
	public static final int GL_NUM_FRAGMENT_REGISTERS_ATI = 0x896E;
	public static final int GL_NUM_FRAGMENT_CONSTANTS_ATI = 0x896F;
	public static final int GL_NUM_PASSES_ATI = 0x8970;
	public static final int GL_NUM_INSTRUCTIONS_PER_PASS_ATI = 0x8971;
	public static final int GL_NUM_INSTRUCTIONS_TOTAL_ATI = 0x8972;
	public static final int GL_NUM_INPUT_INTERPOLATOR_COMPONENTS_ATI = 0x8973;
	public static final int GL_NUM_LOOPBACK_COMPONENTS_ATI = 0x8974;
	public static final int GL_COLOR_ALPHA_PAIRING_ATI = 0x8975;
	public static final int GL_SWIZZLE_STR_ATI = 0x8976;
	public static final int GL_SWIZZLE_STQ_ATI = 0x8977;
	public static final int GL_SWIZZLE_STR_DR_ATI = 0x8978;
	public static final int GL_SWIZZLE_STQ_DQ_ATI = 0x8979;
	public static final int GL_SWIZZLE_STRQ_ATI = 0x897A;
	public static final int GL_SWIZZLE_STRQ_DQ_ATI = 0x897B;
	public static final int GL_RED_BIT_ATI = 0x00000001;
	public static final int GL_GREEN_BIT_ATI = 0x00000002;
	public static final int GL_BLUE_BIT_ATI = 0x00000004;
	public static final int GL_X2_BIT_ATI = 0x00000001;
	public static final int GL_X4_BIT_ATI = 0x00000002;
	public static final int GL_X8_BIT_ATI = 0x00000004;
	public static final int GL_HALF_BIT_ATI = 0x00000008;
	public static final int GL_QUARTER_BIT_ATI = 0x00000010;
	public static final int GL_EIGHTH_BIT_ATI = 0x00000020;
	public static final int GL_SATURATE_BIT_ATI = 0x00000040;
	public static final int GL_COMP_BIT_ATI = 0x00000002;
	public static final int GL_NEGATE_BIT_ATI = 0x00000004;
	public static final int GL_BIAS_BIT_ATI = 0x00000008;

	public @GLuint int glGenFragmentShadersATI(@GLuint int range);

	public void glBindFragmentShaderATI(@GLuint int id);

	public void glDeleteFragmentShaderATI(@GLuint int id);

	public void glBeginFragmentShaderATI();

	public void glEndFragmentShaderATI();

	public void glPassTexCoordATI(@GLuint int dst, @GLuint int coord, @GLenum int swizzle);

	public void glSampleMapATI(@GLuint int dst, @GLuint int interp, @GLenum int swizzle);

	public void glColorFragmentOp1ATI(@GLenum int op, @GLuint int dst, @GLuint int dstMask, @GLuint int dstMod, @GLuint int arg1, @GLuint int arg1Rep, @GLuint int arg1Mod);

	public void glColorFragmentOp2ATI(@GLenum int op, @GLuint int dst, @GLuint int dstMask, @GLuint int dstMod, @GLuint int arg1, @GLuint int arg1Rep, @GLuint int arg1Mod, @GLuint int arg2, @GLuint int arg2Rep, @GLuint int arg2Mod);

	public void glColorFragmentOp3ATI(@GLenum int op, @GLuint int dst, @GLuint int dstMask, @GLuint int dstMod, @GLuint int arg1, @GLuint int arg1Rep, @GLuint int arg1Mod, @GLuint int arg2, @GLuint int arg2Rep, @GLuint int arg2Mod, @GLuint int arg3, @GLuint int arg3Rep, @GLuint int arg3Mod);

	public void glAlphaFragmentOp1ATI(@GLenum int op, @GLuint int dst, @GLuint int dstMod, @GLuint int arg1, @GLuint int arg1Rep, @GLuint int arg1Mod);

	public void glAlphaFragmentOp2ATI(@GLenum int op, @GLuint int dst, @GLuint int dstMod, @GLuint int arg1, @GLuint int arg1Rep, @GLuint int arg1Mod, @GLuint int arg2, @GLuint int arg2Rep, @GLuint int arg2Mod);

	public void glAlphaFragmentOp3ATI(@GLenum int op, @GLuint int dst, @GLuint int dstMod, @GLuint int arg1, @GLuint int arg1Rep, @GLuint int arg1Mod, @GLuint int arg2, @GLuint int arg2Rep, @GLuint int arg2Mod, @GLuint int arg3, @GLuint int arg3Rep, @GLuint int arg3Mod);

	// TODO:is the @Check correct?
	public void glSetFragmentShaderConstantATI(@GLuint int dst, @Check("4") @Const FloatBuffer pfValue);
}
