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
package org.lwjgl.opengl;

public interface NV_texture_shader {
	public static final int GL_TEXTURE_SHADER_NV = 0x86DE;
	public static final int GL_RGBA_UNSIGNED_DOT_PRODUCT_MAPPING_NV = 0x86D9;
	public static final int GL_SHADER_OPERATION_NV = 0x86DF;
	public static final int GL_CULL_MODES_NV = 0x86E0;
	public static final int GL_OFFSET_TEXTURE_MATRIX_NV = 0x86E1;
	public static final int GL_OFFSET_TEXTURE_SCALE_NV = 0x86E2;
	public static final int GL_OFFSET_TEXTURE_BIAS_NV = 0x86E3;
	public static final int GL_PREVIOUS_TEXTURE_INPUT_NV = 0x86E4;
	public static final int GL_CONST_EYE_NV = 0x86E5;
	public static final int GL_SHADER_CONSISTENT_NV = 0x86DD;
	public static final int GL_PASS_THROUGH_NV = 0x86E6;
	public static final int GL_CULL_FRAGMENT_NV = 0x86E7;
	public static final int GL_OFFSET_TEXTURE_2D_NV = 0x86E8;
	public static final int GL_OFFSET_TEXTURE_RECTANGLE_NV = 0x864C;
	public static final int GL_OFFSET_TEXTURE_RECTANGLE_SCALE_NV = 0x864D;
	public static final int GL_DEPENDENT_AR_TEXTURE_2D_NV = 0x86E9;
	public static final int GL_DEPENDENT_GB_TEXTURE_2D_NV = 0x86EA;
	public static final int GL_DOT_PRODUCT_NV = 0x86EC;
	public static final int GL_DOT_PRODUCT_DEPTH_REPLACE_NV = 0x86ED;
	public static final int GL_DOT_PRODUCT_TEXTURE_2D_NV = 0x86EE;
	public static final int GL_DOT_PRODUCT_TEXTURE_RECTANGLE_NV = 0x864E;
	public static final int GL_DOT_PRODUCT_TEXTURE_CUBE_MAP_NV = 0x86F0;
	public static final int GL_DOT_PRODUCT_DIFFUSE_CUBE_MAP_NV = 0x86F1;
	public static final int GL_DOT_PRODUCT_REFLECT_CUBE_MAP_NV = 0x86F2;
	public static final int GL_DOT_PRODUCT_CONST_EYE_REFLECT_CUBE_MAP_NV = 0x86F3;
	public static final int GL_HILO_NV = 0x86F4;
	public static final int GL_DSDT_NV = 0x86F5;
	public static final int GL_DSDT_MAG_NV = 0x86F6;
	public static final int GL_DSDT_MAG_VIB_NV = 0x86F7;
	public static final int GL_UNSIGNED_INT_S8_S8_8_8_NV = 0x86DA;
	public static final int GL_UNSIGNED_INT_8_8_S8_S8_REV_NV = 0x86DB;
	public static final int GL_SIGNED_RGBA_NV = 0x86FB;
	public static final int GL_SIGNED_RGBA8_NV = 0x86FC;
	public static final int GL_SIGNED_RGB_NV = 0x86FE;
	public static final int GL_SIGNED_RGB8_NV = 0x86FF;
	public static final int GL_SIGNED_LUMINANCE_NV = 0x8701;
	public static final int GL_SIGNED_LUMINANCE8_NV = 0x8702;
	public static final int GL_SIGNED_LUMINANCE_ALPHA_NV = 0x8703;
	public static final int GL_SIGNED_LUMINANCE8_ALPHA8_NV = 0x8704;
	public static final int GL_SIGNED_ALPHA_NV = 0x8705;
	public static final int GL_SIGNED_ALPHA8_NV = 0x8706;
	public static final int GL_SIGNED_INTENSITY_NV = 0x8707;
	public static final int GL_SIGNED_INTENSITY8_NV = 0x8708;
	public static final int GL_SIGNED_RGB_UNSIGNED_ALPHA_NV = 0x870C;
	public static final int GL_SIGNED_RGB8_UNSIGNED_ALPHA8_NV = 0x870D;
	public static final int GL_HILO16_NV = 0x86F8;
	public static final int GL_SIGNED_HILO_NV = 0x86F9;
	public static final int GL_SIGNED_HILO16_NV = 0x86FA;
	public static final int GL_DSDT8_NV = 0x8709;
	public static final int GL_DSDT8_MAG8_NV = 0x870A;
	public static final int GL_DSDT_MAG_INTENSITY_NV = 0x86DC;
	public static final int GL_DSDT8_MAG8_INTENSITY8_NV = 0x870B;
	public static final int GL_HI_SCALE_NV = 0x870E;
	public static final int GL_LO_SCALE_NV = 0x870F;
	public static final int GL_DS_SCALE_NV = 0x8710;
	public static final int GL_DT_SCALE_NV = 0x8711;
	public static final int GL_MAGNITUDE_SCALE_NV = 0x8712;
	public static final int GL_VIBRANCE_SCALE_NV = 0x8713;
	public static final int GL_HI_BIAS_NV = 0x8714;
	public static final int GL_LO_BIAS_NV = 0x8715;
	public static final int GL_DS_BIAS_NV = 0x8716;
	public static final int GL_DT_BIAS_NV = 0x8717;
	public static final int GL_MAGNITUDE_BIAS_NV = 0x8718;
	public static final int GL_VIBRANCE_BIAS_NV = 0x8719;
	public static final int GL_TEXTURE_BORDER_VALUES_NV = 0x871A;
	public static final int GL_TEXTURE_HI_SIZE_NV = 0x871B;
	public static final int GL_TEXTURE_LO_SIZE_NV = 0x871C;
	public static final int GL_TEXTURE_DS_SIZE_NV = 0x871D;
	public static final int GL_TEXTURE_DT_SIZE_NV = 0x871E;
	public static final int GL_TEXTURE_MAG_SIZE_NV = 0x871F;
}
