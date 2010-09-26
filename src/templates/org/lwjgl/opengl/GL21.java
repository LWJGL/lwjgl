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

import org.lwjgl.util.generator.AutoSize;
import org.lwjgl.util.generator.opengl.GLsizei;
import org.lwjgl.util.generator.StripPostfix;

import java.nio.FloatBuffer;

public interface GL21 {

	// ------------------------------------------------------------------
	// --------------------------[ GLSL 1.20 ]---------------------------
	// ------------------------------------------------------------------

	/** Returned by the &lt;type&gt; parameter of GetActiveAttribARB. */
	int GL_FLOAT_MAT2x3 = 0x8B65;
	int GL_FLOAT_MAT2x4 = 0x8B66;
	int GL_FLOAT_MAT3x2 = 0x8B67;
	int GL_FLOAT_MAT3x4 = 0x8B68;
	int GL_FLOAT_MAT4x2 = 0x8B69;
	int GL_FLOAT_MAT4x3 = 0x8B6A;

	@StripPostfix("matrices")
	void glUniformMatrix2x3fv(int location, @AutoSize(value = "matrices", expression = " / (2 * 3)") @GLsizei int count,
	                          boolean transpose, FloatBuffer matrices);

	@StripPostfix("matrices")
	void glUniformMatrix3x2fv(int location, @AutoSize(value = "matrices", expression = " / (3 * 2)") @GLsizei int count,
	                          boolean transpose, FloatBuffer matrices);

	@StripPostfix("matrices")
	void glUniformMatrix2x4fv(int location, @AutoSize(value = "matrices", expression = " >> 3") @GLsizei int count,
	                          boolean transpose, FloatBuffer matrices);

	@StripPostfix("matrices")
	void glUniformMatrix4x2fv(int location, @AutoSize(value = "matrices", expression = " >> 3") @GLsizei int count,
	                          boolean transpose, FloatBuffer matrices);

	@StripPostfix("matrices")
	void glUniformMatrix3x4fv(int location, @AutoSize(value = "matrices", expression = " / (3 * 4)") @GLsizei int count,
	                          boolean transpose, FloatBuffer matrices);

	@StripPostfix("matrices")
	void glUniformMatrix4x3fv(int location, @AutoSize(value = "matrices", expression = " / (4 * 3)") @GLsizei int count,
	                          boolean transpose, FloatBuffer matrices);

	// ------------------------------------------------------------------
	// -------------------[ ARB_pixel_buffer_object ]--------------------
	// ------------------------------------------------------------------

	/**
	 * Accepted by the &lt;target&gt; parameters of BindBuffer, BufferData,
	 * BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData,
	 * GetBufferParameteriv, and GetBufferPointerv.
	 */
	int GL_PIXEL_PACK_BUFFER = 0x88EB;
	int GL_PIXEL_UNPACK_BUFFER = 0x88EC;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev.
	 */
	int GL_PIXEL_PACK_BUFFER_BINDING = 0x88ED;
	int GL_PIXEL_UNPACK_BUFFER_BINDING = 0x88EF;

	// ------------------------------------------------------------------
	// ----------------------[ EXT_texture_sRGB ]------------------------
	// ------------------------------------------------------------------

	/**
	 * Accepted by the &lt;internalformat&gt; parameter of TexImage1D, TexImage2D,
	 * TexImage3D, CopyTexImage1D, CopyTexImage2D.
	 */
	int GL_SRGB = 0x8C40;
	int GL_SRGB8 = 0x8C41;
	int GL_SRGB_ALPHA = 0x8C42;
	int GL_SRGB8_ALPHA8 = 0x8C43;
	int GL_SLUMINANCE_ALPHA = 0x8C44;
	int GL_SLUMINANCE8_ALPHA8 = 0x8C45;
	int GL_SLUMINANCE = 0x8C46;
	int GL_SLUMINANCE8 = 0x8C47;
	int GL_COMPRESSED_SRGB = 0x8C48;
	int GL_COMPRESSED_SRGB_ALPHA = 0x8C49;
	int GL_COMPRESSED_SLUMINANCE = 0x8C4A;
	int GL_COMPRESSED_SLUMINANCE_ALPHA = 0x8C4B;

	// ------------------------------------------------------------------
	// -----------------------[ Misc additions ]-------------------------
	// ------------------------------------------------------------------

	/** Accepted by the &lt;pname&gt; parameter of GetIntegerv and GetFloatv. */
	int GL_CURRENT_RASTER_SECONDARY_COLOR = 0x845F;

}