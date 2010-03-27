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
package org.lwjgl.opengl;

public interface APPLE_packed_pixels {

	/**
	 * Accepted by the &lt;type&gt; parameter of DrawPixels, ReadPixels, TexImage1D,
	 * TexImage2D, GetTexImage, TexImage3D, TexSubImage1D,
	 * TexSubImage2D, TexSubImage3D, GetHistogram, GetMinmax,
	 * ConvolutionFilter1D, ConvolutionFilter2D, ConvolutionFilter3D,
	 * GetConvolutionFilter, SeparableFilter2D, SeparableFilter3D,
	 * GetSeparableFilter, ColorTable, GetColorTable, TexImage4DSGIS,
	 * and TexSubImage4DSGIS:
	 */
	int GL_UNSIGNED_BYTE_3_3_2 = 0x8032;
	int GL_UNSIGNED_BYTE_2_3_3_REV = 0x8362;
	int GL_UNSIGNED_SHORT_5_6_5 = 0x8363;
	int GL_UNSIGNED_SHORT_5_6_5_REV = 0x8364;
	int GL_UNSIGNED_SHORT_4_4_4_4 = 0x8033;
	int GL_UNSIGNED_SHORT_4_4_4_4_REV = 0x8365;
	int GL_UNSIGNED_SHORT_5_5_5_1 = 0x8034;
	int GL_UNSIGNED_SHORT_1_5_5_5_REV = 0x8366;
	int GL_UNSIGNED_INT_8_8_8_8 = 0x8035;
	int GL_UNSIGNED_INT_8_8_8_8_REV = 0x8367;
	int GL_UNSIGNED_INT_10_10_10_2 = 0x8036;
	int GL_UNSIGNED_INT_2_10_10_10_REV = 0x8368;

}