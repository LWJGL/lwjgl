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
 * Time: 15:33:02
 * To change template for new interface use
 * Code Style | Class Templates options (Tools | IDE Options).
 */
package org.lwjgl.opengl.ext;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.CoreGL11Constants;
import org.lwjgl.opengl.VBOTracker;

public class EXTVertexShader {
	public static final int GL_VERTEX_SHADER_EXT                                    = 0x8780;
	public static final int GL_VERTEX_SHADER_BINDING_EXT                            = 0x8781;
	public static final int GL_OP_INDEX_EXT                                         = 0x8782;
	public static final int GL_OP_NEGATE_EXT                                        = 0x8783;
	public static final int GL_OP_DOT3_EXT                                          = 0x8784;
	public static final int GL_OP_DOT4_EXT                                          = 0x8785;
	public static final int GL_OP_MUL_EXT                                           = 0x8786;
	public static final int GL_OP_ADD_EXT                                           = 0x8787;
	public static final int GL_OP_MADD_EXT                                          = 0x8788;
	public static final int GL_OP_FRAC_EXT                                          = 0x8789;
	public static final int GL_OP_MAX_EXT                                           = 0x878A;
	public static final int GL_OP_MIN_EXT                                           = 0x878B;
	public static final int GL_OP_SET_GE_EXT                                        = 0x878C;
	public static final int GL_OP_SET_LT_EXT                                        = 0x878D;
	public static final int GL_OP_CLAMP_EXT                                         = 0x878E;
	public static final int GL_OP_FLOOR_EXT                                         = 0x878F;
	public static final int GL_OP_ROUND_EXT                                         = 0x8790;
	public static final int GL_OP_EXP_BASE_2_EXT                                    = 0x8791;
	public static final int GL_OP_LOG_BASE_2_EXT                                    = 0x8792;
	public static final int GL_OP_POWER_EXT                                         = 0x8793;
	public static final int GL_OP_RECIP_EXT                                         = 0x8794;
	public static final int GL_OP_RECIP_SQRT_EXT                                    = 0x8795;
	public static final int GL_OP_SUB_EXT                                           = 0x8796;
	public static final int GL_OP_CROSS_PRODUCT_EXT                                 = 0x8797;
	public static final int GL_OP_MULTIPLY_MATRIX_EXT                               = 0x8798;
	public static final int GL_OP_MOV_EXT                                           = 0x8799;
	public static final int GL_OUTPUT_VERTEX_EXT                                    = 0x879A;
	public static final int GL_OUTPUT_COLOR0_EXT                                    = 0x879B;
	public static final int GL_OUTPUT_COLOR1_EXT                                    = 0x879C;
	public static final int GL_OUTPUT_TEXTURE_COORD0_EXT                            = 0x879D;
	public static final int GL_OUTPUT_TEXTURE_COORD1_EXT                            = 0x879E;
	public static final int GL_OUTPUT_TEXTURE_COORD2_EXT                            = 0x879F;
	public static final int GL_OUTPUT_TEXTURE_COORD3_EXT                            = 0x87A0;
	public static final int GL_OUTPUT_TEXTURE_COORD4_EXT                            = 0x87A1;
	public static final int GL_OUTPUT_TEXTURE_COORD5_EXT                            = 0x87A2;
	public static final int GL_OUTPUT_TEXTURE_COORD6_EXT                            = 0x87A3;
	public static final int GL_OUTPUT_TEXTURE_COORD7_EXT                            = 0x87A4;
	public static final int GL_OUTPUT_TEXTURE_COORD8_EXT                            = 0x87A5;
	public static final int GL_OUTPUT_TEXTURE_COORD9_EXT                            = 0x87A6;
	public static final int GL_OUTPUT_TEXTURE_COORD10_EXT                           = 0x87A7;
	public static final int GL_OUTPUT_TEXTURE_COORD11_EXT                           = 0x87A8;
	public static final int GL_OUTPUT_TEXTURE_COORD12_EXT                           = 0x87A9;
	public static final int GL_OUTPUT_TEXTURE_COORD13_EXT                           = 0x87AA;
	public static final int GL_OUTPUT_TEXTURE_COORD14_EXT                           = 0x87AB;
	public static final int GL_OUTPUT_TEXTURE_COORD15_EXT                           = 0x87AC;
	public static final int GL_OUTPUT_TEXTURE_COORD16_EXT                           = 0x87AD;
	public static final int GL_OUTPUT_TEXTURE_COORD17_EXT                           = 0x87AE;
	public static final int GL_OUTPUT_TEXTURE_COORD18_EXT                           = 0x87AF;
	public static final int GL_OUTPUT_TEXTURE_COORD19_EXT                           = 0x87B0;
	public static final int GL_OUTPUT_TEXTURE_COORD20_EXT                           = 0x87B1;
	public static final int GL_OUTPUT_TEXTURE_COORD21_EXT                           = 0x87B2;
	public static final int GL_OUTPUT_TEXTURE_COORD22_EXT                           = 0x87B3;
	public static final int GL_OUTPUT_TEXTURE_COORD23_EXT                           = 0x87B4;
	public static final int GL_OUTPUT_TEXTURE_COORD24_EXT                           = 0x87B5;
	public static final int GL_OUTPUT_TEXTURE_COORD25_EXT                           = 0x87B6;
	public static final int GL_OUTPUT_TEXTURE_COORD26_EXT                           = 0x87B7;
	public static final int GL_OUTPUT_TEXTURE_COORD27_EXT                           = 0x87B8;
	public static final int GL_OUTPUT_TEXTURE_COORD28_EXT                           = 0x87B9;
	public static final int GL_OUTPUT_TEXTURE_COORD29_EXT                           = 0x87BA;
	public static final int GL_OUTPUT_TEXTURE_COORD30_EXT                           = 0x87BB;
	public static final int GL_OUTPUT_TEXTURE_COORD31_EXT                           = 0x87BC;
	public static final int GL_OUTPUT_FOG_EXT                                       = 0x87BD;
	public static final int GL_SCALAR_EXT                                           = 0x87BE;
	public static final int GL_VECTOR_EXT                                           = 0x87BF;
	public static final int GL_MATRIX_EXT                                           = 0x87C0;
	public static final int GL_VARIANT_EXT                                          = 0x87C1;
	public static final int GL_INVARIANT_EXT                                        = 0x87C2;
	public static final int GL_LOCAL_CONSTANT_EXT                                   = 0x87C3;
	public static final int GL_LOCAL_EXT                                            = 0x87C4;
	public static final int GL_MAX_VERTEX_SHADER_INSTRUCTIONS_EXT                   = 0x87C5;
	public static final int GL_MAX_VERTEX_SHADER_VARIANTS_EXT                       = 0x87C6;
	public static final int GL_MAX_VERTEX_SHADER_INVARIANTS_EXT                     = 0x87C7;
	public static final int GL_MAX_VERTEX_SHADER_LOCAL_CONSTANTS_EXT                = 0x87C8;
	public static final int GL_MAX_VERTEX_SHADER_LOCALS_EXT                         = 0x87C9;
	public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_INSTRUCTIONS_EXT         = 0x87CA;
	public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_VARIANTS_EXT             = 0x87CB;
	public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_INVARIANTS_EXT           = 0x87CC;
	public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_LOCAL_CONSTANTS_EXT      = 0x87CD;
	public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_LOCALS_EXT               = 0x87CE;
	public static final int GL_VERTEX_SHADER_INSTRUCTIONS_EXT                       = 0x87CF;
	public static final int GL_VERTEX_SHADER_VARIANTS_EXT                           = 0x87D0;
	public static final int GL_VERTEX_SHADER_INVARIANTS_EXT                         = 0x87D1;
	public static final int GL_VERTEX_SHADER_LOCAL_CONSTANTS_EXT                    = 0x87D2;
	public static final int GL_VERTEX_SHADER_LOCALS_EXT                             = 0x87D3;
	public static final int GL_VERTEX_SHADER_OPTIMIZED_EXT                          = 0x87D4;
	public static final int GL_X_EXT                                                = 0x87D5;
	public static final int GL_Y_EXT                                                = 0x87D6;
	public static final int GL_Z_EXT                                                = 0x87D7;
	public static final int GL_W_EXT                                                = 0x87D8;
	public static final int GL_NEGATIVE_X_EXT                                       = 0x87D9;
	public static final int GL_NEGATIVE_Y_EXT                                       = 0x87DA;
	public static final int GL_NEGATIVE_Z_EXT                                       = 0x87DB;
	public static final int GL_NEGATIVE_W_EXT                                       = 0x87DC;
	public static final int GL_ZERO_EXT                                             = 0x87DD;
	public static final int GL_ONE_EXT                                              = 0x87DE;
	public static final int GL_NEGATIVE_ONE_EXT                                     = 0x87DF;
	public static final int GL_NORMALIZED_RANGE_EXT                                 = 0x87E0;
	public static final int GL_FULL_RANGE_EXT                                       = 0x87E1;
	public static final int GL_CURRENT_VERTEX_EXT                                   = 0x87E2;
	public static final int GL_MVP_MATRIX_EXT                                       = 0x87E3;
	public static final int GL_VARIANT_VALUE_EXT                                    = 0x87E4;
	public static final int GL_VARIANT_DATATYPE_EXT                                 = 0x87E5;
	public static final int GL_VARIANT_ARRAY_STRIDE_EXT                             = 0x87E6;
	public static final int GL_VARIANT_ARRAY_TYPE_EXT                               = 0x87E7;
	public static final int GL_VARIANT_ARRAY_EXT                                    = 0x87E8;
	public static final int GL_VARIANT_ARRAY_POINTER_EXT                            = 0x87E9;
	public static final int GL_INVARIANT_VALUE_EXT                                  = 0x87EA;
	public static final int GL_INVARIANT_DATATYPE_EXT                               = 0x87EB;
	public static final int GL_LOCAL_CONSTANT_VALUE_EXT                             = 0x87EC;
	public static final int GL_LOCAL_CONSTANT_DATATYPE_EXT                          = 0x87ED;

	public static native void glBeginVertexShaderEXT();
	public static native void glEndVertexShaderEXT();

	public static native void glBindVertexShaderEXT(int id);

	public static native int glGenVertexShadersEXT(int range);

	public static native void glDeleteVertexShaderEXT(int id);

	public static native void glShaderOp1EXT(int op, int res, int arg1);

	public static native void glShaderOp2EXT(int op, int res, int arg1, int arg2);

	public static native void glShaderOp3EXT(
		int op,
		int res,
		int arg1,
		int arg2,
		int arg3);

	public static native void glSwizzleEXT(
		int res,
		int in,
		int outX,
		int outY,
		int outZ,
		int outW);

	public static native void glWriteMaskEXT(
		int res,
		int in,
		int outX,
		int outY,
		int outZ,
		int outW);
	public static native void glInsertComponentEXT(int res, int src, int num);

	public static native void glExtractComponentEXT(int res, int src, int num);

	public static native int glGenSymbolsEXT(
		int dataType,
		int storageType,
		int range,
		int components);
	public static void glSetInvariantEXT(int id, boolean unsigned, ByteBuffer pAddr) {
		nglSetInvariantEXT(id, unsigned ? CoreGL11Constants.GL_UNSIGNED_BYTE : CoreGL11Constants.GL_BYTE, pAddr, pAddr.position());
	}
	public static void glSetInvariantEXT(int id, boolean unsigned, ShortBuffer pAddr) {
		nglSetInvariantEXT(id, unsigned ? CoreGL11Constants.GL_UNSIGNED_SHORT : CoreGL11Constants.GL_SHORT, pAddr, pAddr.position()<<1);
	}
	public static void glSetInvariantEXT(int id, FloatBuffer pAddr) {
		nglSetInvariantEXT(id, CoreGL11Constants.GL_FLOAT, pAddr, pAddr.position()<<2);
	}
	public static void glSetInvariantEXT(int id, boolean unsigned, IntBuffer pAddr) {
		nglSetInvariantEXT(id, unsigned ? CoreGL11Constants.GL_UNSIGNED_INT : CoreGL11Constants.GL_INT, pAddr, pAddr.position()<<2);
	}
	private static native void nglSetInvariantEXT(int id, int type, Buffer pAddr, int pAddr_offset);

	public static void glSetLocalConstantEXT(int id, boolean unsigned, ByteBuffer pAddr) {
		nglSetLocalConstantEXT(id, unsigned ? CoreGL11Constants.GL_UNSIGNED_BYTE : CoreGL11Constants.GL_BYTE, pAddr, pAddr.position());
	}
	public static void glSetLocalConstantEXT(int id, boolean unsigned, ShortBuffer pAddr) {
		nglSetLocalConstantEXT(id, unsigned ? CoreGL11Constants.GL_UNSIGNED_SHORT : CoreGL11Constants.GL_SHORT, pAddr, pAddr.position()<<1);
	}
	public static void glSetLocalConstantEXT(int id, FloatBuffer pAddr) {
		nglSetLocalConstantEXT(id, CoreGL11Constants.GL_FLOAT, pAddr, pAddr.position()<<2);
	}
	public static void glSetLocalConstantEXT(int id, boolean unsigned, IntBuffer pAddr) {
		nglSetLocalConstantEXT(id, unsigned ? CoreGL11Constants.GL_UNSIGNED_INT : CoreGL11Constants.GL_INT, pAddr, pAddr.position()<<2);
	}
	private static native void nglSetLocalConstantEXT(int id, int type, Buffer pAddr, int pAddr_offset);

	public static void glVariantEXT(int id, ByteBuffer pAddr) {
		nglVariantbvEXT(id, pAddr, pAddr.position());
	}
	private static native void nglVariantbvEXT(int id, ByteBuffer pAddr, int pAddr_offset);

	public static void glVariantEXT(int id, ShortBuffer psAddr) {
		nglVariantsvEXT(id, psAddr, psAddr.position());
	}
	private static native void nglVariantsvEXT(int id, ShortBuffer psAddr, int psAddr_offset);

	public static void glVariantEXT(int id, FloatBuffer pfAddr) {
		nglVariantfvEXT(id, pfAddr, pfAddr.position());
	}
	private static native void nglVariantfvEXT(int id, FloatBuffer pfAddr, int pfAddr_offset);

	public static void glVariantEXT(int id, IntBuffer piAddr) {
		nglVariantivEXT(id, piAddr, piAddr.position());
	}
	private static native void nglVariantivEXT(int id, IntBuffer piAddr, int piAddr_offset);

	public static void glVariantuEXT(int id, ByteBuffer pAddr) {
		nglVariantubvEXT(id, pAddr, pAddr.position());
	}
	private static native void nglVariantubvEXT(int id, ByteBuffer pAddr, int pAddr_offset);

	public static void glVariantuEXT(int id, ShortBuffer psAddr) {
		nglVariantusvEXT(id, psAddr, psAddr.position());
	}
	private static native void nglVariantusvEXT(int id, ShortBuffer psAddr, int psAddr_offset);

	public static void glVariantuEXT(int id, IntBuffer piAddr) {
		nglVariantuivEXT(id, piAddr, piAddr.position());
	}
	private static native void nglVariantuivEXT(int id, IntBuffer piAddr, int piAddr_offset);
	public static void glVariantPointerEXT(int id, boolean unsigned, int stride, ByteBuffer pAddr) {
		assert VBOTracker.getVBOArrayStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglVariantPointerEXT(id, unsigned ? CoreGL11Constants.GL_UNSIGNED_BYTE : CoreGL11Constants.GL_BYTE, stride, pAddr, pAddr.position());
	}
	public static void glVariantPointerEXT(int id, boolean unsigned, int stride, ShortBuffer pAddr) {
		assert VBOTracker.getVBOArrayStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglVariantPointerEXT(id, unsigned ? CoreGL11Constants.GL_UNSIGNED_SHORT : CoreGL11Constants.GL_SHORT, stride, pAddr, pAddr.position()<<1);
	}
	public static void glVariantPointerEXT(int id, int stride, FloatBuffer pAddr) {
		assert VBOTracker.getVBOArrayStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglVariantPointerEXT(id, CoreGL11Constants.GL_FLOAT, stride, pAddr, pAddr.position()<<2);
	}
	public static void glVariantPointerEXT(int id, boolean unsigned, int stride, IntBuffer pAddr) {
		assert VBOTracker.getVBOArrayStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglVariantPointerEXT(id, unsigned ? CoreGL11Constants.GL_UNSIGNED_INT : CoreGL11Constants.GL_INT, stride, pAddr, pAddr.position()<<2);
	}
	private static native void nglVariantPointerEXT(int id, int type, int stride, Buffer pAddr, int pAddr_offset);
	public static void glVariantPointerEXT(int id, int type, int stride, int buffer_offset) {
		assert VBOTracker.getVBOArrayStack().getState() != 0: "Cannot use int offsets when VBO is disabled";
		nglVariantPointerEXTVBO(id, type, stride, buffer_offset);
	}
	private static native void nglVariantPointerEXTVBO(int id, int type, int stride, int buffer_offset);
	public static native void glEnableVariantClientStateEXT(int id);

	public static native void glDisableVariantClientStateEXT(int id);

	public static native int glBindLightParameterEXT(int light, int value);

	public static native int glBindMaterialParameterEXT(int face, int value);

	public static native int glBindTexGenParameterEXT(int unit, int coord, int value);

	public static native int glBindTextureUnitParameterEXT(int unit, int value);

	public static native int glBindParameterEXT(int value);
	public static native boolean glIsVariantEnabledEXT(int id, int cap);

	public static void glGetVariantBooleanEXT(int id, int value, ByteBuffer pbData) {
		nglGetVariantBooleanvEXT(id, value, pbData, pbData.position());
	}
	private static native void nglGetVariantBooleanvEXT(int id, int value, ByteBuffer pbData, int pbData_offset);
	public static void glGetVariantIntegerEXT(int id, int value, IntBuffer piData) {
		nglGetVariantIntegervEXT(id, value, piData, piData.position());
	}
	private static native void nglGetVariantIntegervEXT(int id, int value, IntBuffer piData, int piData_offset);

	public static void glGetVariantFloatEXT(int id, int value, FloatBuffer pfData) {
		nglGetVariantFloatvEXT(id, value, pfData, pfData.position());
	}
	private static native void nglGetVariantFloatvEXT(int id, int value, FloatBuffer pfData, int pfData_offset);

	public static native ByteBuffer glGetVariantPointerEXT(int id, int value, int size);
	public static void glGetInvariantBooleanEXT(int id, int value, ByteBuffer pbData) {
		nglGetInvariantBooleanvEXT(id, value, pbData, pbData.position());
	}
	private static native void nglGetInvariantBooleanvEXT(int id, int value, ByteBuffer pbData, int pbData_offset);

	public static void glGetInvariantIntegerEXT(int id, int value, IntBuffer piData) {
		nglGetInvariantIntegervEXT(id, value, piData, piData.position());
	}
	private static native void nglGetInvariantIntegervEXT(int id, int value, IntBuffer piData, int piData_offset);

	public static void glGetInvariantFloatEXT(int id, int value, FloatBuffer pfData) {
		nglGetInvariantFloatvEXT(id, value, pfData, pfData.position());
	}
	private static native void nglGetInvariantFloatvEXT(int id, int value, FloatBuffer pfData, int pfData_offset);

	public static void glGetLocalConstantBooleanEXT(int id, int value, ByteBuffer pbData) {
		nglGetLocalConstantBooleanvEXT(id, value, pbData, pbData.position());
	}
	private static native void nglGetLocalConstantBooleanvEXT(int id, int value, ByteBuffer pbData, int pbData_offset);

	public static void glGetLocalConstantIntegerEXT(int id, int value, IntBuffer piData) {
		nglGetLocalConstantIntegervEXT(id, value, piData, piData.position());
	}
	private static native void nglGetLocalConstantIntegervEXT(int id, int value, IntBuffer piData, int piData_offset);

	public static void glGetLocalConstantFloatEXT(int id, int value, FloatBuffer pfData) {
		nglGetLocalConstantFloatvEXT(id, value, pfData, pfData.position());
	}
	private static native void nglGetLocalConstantFloatvEXT(int id, int value, FloatBuffer pfData, int pfData_offset);
}
