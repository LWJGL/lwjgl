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

import org.lwjgl.util.generator.*;
import org.lwjgl.util.generator.Alternate;
import org.lwjgl.util.generator.opengl.*;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.sun.mirror.type.PrimitiveType;

public interface GL40 {

	// ----------------------------------------------------------------------
	// ----------------------[ ARB_draw_buffers_blend ]----------------------
	// ----------------------------------------------------------------------

	void glBlendEquationi(@GLuint int buf, @GLenum int mode);

	void glBlendEquationSeparatei(@GLuint int buf, @GLenum int modeRGB, @GLenum int modeAlpha);

	void glBlendFunci(@GLuint int buf, @GLenum int src, @GLenum int dst);

	void glBlendFuncSeparatei(@GLuint int buf, @GLenum int srcRGB, @GLenum int dstRGB, @GLenum int srcAlpha, @GLenum int dstAlpha);

	// -----------------------------------------------------------------
	// ----------------------[ ARB_draw_indirect ]----------------------
	// -----------------------------------------------------------------

	/**
	 * Accepted by the &lt;target&gt; parameters of BindBuffer, BufferData,
	 * BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData,
	 * GetBufferPointerv, MapBufferRange, FlushMappedBufferRange,
	 * GetBufferParameteriv, BindBufferRange, BindBufferBase, and
	 * CopyBufferSubData:
	 */
	int GL_DRAW_INDIRECT_BUFFER = 0x8F3F;

	/**
	 * Accepted by the &lt;value&gt; parameter of GetIntegerv, GetBooleanv, GetFloatv,
	 * and GetDoublev:
	 */
	int GL_DRAW_INDIRECT_BUFFER_BINDING = 0x8F43;

	void glDrawArraysIndirect(@GLenum int mode, @BufferObject(BufferKind.IndirectBO) @Check("4 * 4") @Const @GLvoid ByteBuffer indirect);

	@Alternate("glDrawArraysIndirect")
	void glDrawArraysIndirect(@GLenum int mode, @BufferObject(BufferKind.IndirectBO) @Check("4") @Const @GLvoid(PrimitiveType.Kind.INT) IntBuffer indirect);

	void glDrawElementsIndirect(@GLenum int mode, @GLenum int type, @BufferObject(BufferKind.IndirectBO) @Check("5 * 4") @Const @GLvoid ByteBuffer indirect);

	@Alternate("glDrawElementsIndirect")
	void glDrawElementsIndirect(@GLenum int mode, @GLenum int type, @BufferObject(BufferKind.IndirectBO) @Check("5") @Const @GLvoid(PrimitiveType.Kind.INT) IntBuffer indirect);

	// ---------------------------------------------------------------
	// ----------------------[ ARB_gpu_shader5 ]----------------------
	// ---------------------------------------------------------------

	/** Accepted by the &lt;pname&gt; parameter of GetProgramiv: */
	int GL_GEOMETRY_SHADER_INVOCATIONS = 0x887F;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv,
	 * GetDoublev, and GetInteger64v:
	 */
	int GL_MAX_GEOMETRY_SHADER_INVOCATIONS = 0x8E5A;
	int GL_MIN_FRAGMENT_INTERPOLATION_OFFSET = 0x8E5B;
	int GL_MAX_FRAGMENT_INTERPOLATION_OFFSET = 0x8E5C;
	int GL_FRAGMENT_INTERPOLATION_OFFSET_BITS = 0x8E5D;
	int GL_MAX_VERTEX_STREAMS = 0x8E71;

	// -------------------------------------------------------------------
	// ----------------------[ ARB_gpu_shader_fp64 ]----------------------
	// -------------------------------------------------------------------

	/**
	 * Returned in the &lt;type&gt; parameter of GetActiveUniform, and
	 * GetTransformFeedbackVarying:
	 */
	int GL_DOUBLE_VEC2 = 0x8FFC;
	int GL_DOUBLE_VEC3 = 0x8FFD;
	int GL_DOUBLE_VEC4 = 0x8FFE;
	int GL_DOUBLE_MAT2 = 0x8F46;
	int GL_DOUBLE_MAT3 = 0x8F47;
	int GL_DOUBLE_MAT4 = 0x8F48;
	int GL_DOUBLE_MAT2x3 = 0x8F49;
	int GL_DOUBLE_MAT2x4 = 0x8F4A;
	int GL_DOUBLE_MAT3x2 = 0x8F4B;
	int GL_DOUBLE_MAT3x4 = 0x8F4C;
	int GL_DOUBLE_MAT4x2 = 0x8F4D;
	int GL_DOUBLE_MAT4x3 = 0x8F4E;

	void glUniform1d(int location, double x);

	void glUniform2d(int location, double x, double y);

	void glUniform3d(int location, double x, double y, double z);

	void glUniform4d(int location, double x, double y, double z, double w);

	@StripPostfix("value")
	void glUniform1dv(int location, @AutoSize("value") @GLsizei int count, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glUniform2dv(int location, @AutoSize(value = "value", expression = " >> 1") @GLsizei int count, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glUniform3dv(int location, @AutoSize(value = "value", expression = " / 3") @GLsizei int count, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glUniform4dv(int location, @AutoSize(value = "value", expression = " >> 2") @GLsizei int count, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glUniformMatrix2dv(int location, @AutoSize(value = "value", expression = " >> 2") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glUniformMatrix3dv(int location, @AutoSize(value = "value", expression = " / (3 * 3)") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glUniformMatrix4dv(int location, @AutoSize(value = "value", expression = " >> 4") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glUniformMatrix2x3dv(int location, @AutoSize(value = "value", expression = " / (2 * 3)") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glUniformMatrix2x4dv(int location, @AutoSize(value = "value", expression = " >> 3") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glUniformMatrix3x2dv(int location, @AutoSize(value = "value", expression = " / (3 * 2)") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glUniformMatrix3x4dv(int location, @AutoSize(value = "value", expression = " / (3 * 4)") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glUniformMatrix4x2dv(int location, @AutoSize(value = "value", expression = " >> 3") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glUniformMatrix4x3dv(int location, @AutoSize(value = "value", expression = " / (4 * 3)") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("params")
	void glGetUniformdv(@GLuint int program, int location, @OutParameter @Check DoubleBuffer params);

	// ------------------------------------------------------------------
	// ----------------------[ ARB_sample_shading ]----------------------
	// ------------------------------------------------------------------

	/**
	 * Accepted by the &lt;cap&gt; parameter of Enable, Disable, and IsEnabled,
	 * and by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv,
	 * and GetDoublev:
	 */
	int GL_SAMPLE_SHADING = 0x8C36;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetDoublev,
	 * GetIntegerv, and GetFloatv:
	 */
	int GL_MIN_SAMPLE_SHADING_VALUE = 0x8C37;

	void glMinSampleShading(float value);

	// ---------------------------------------------------------------------
	// ----------------------[ ARB_shader_subroutine ]----------------------
	// ---------------------------------------------------------------------

	/** Accepted by the &lt;pname&gt; parameter of GetProgramStageiv: */
	int GL_ACTIVE_SUBROUTINES = 0x8DE5;
	int GL_ACTIVE_SUBROUTINE_UNIFORMS = 0x8DE6;
	int GL_ACTIVE_SUBROUTINE_UNIFORM_LOCATIONS = 0x8E47;
	int GL_ACTIVE_SUBROUTINE_MAX_LENGTH = 0x8E48;
	int GL_ACTIVE_SUBROUTINE_UNIFORM_MAX_LENGTH = 0x8E49;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, GetDoublev, and GetInteger64v:
	 */
	int GL_MAX_SUBROUTINES = 0x8DE7;
	int GL_MAX_SUBROUTINE_UNIFORM_LOCATIONS = 0x8DE8;

	/** Accepted by the &lt;pname&gt; parameter of GetActiveSubroutineUniformiv: */
	int GL_NUM_COMPATIBLE_SUBROUTINES = 0x8E4A;
	int GL_COMPATIBLE_SUBROUTINES = 0x8E4B;
	int GL_UNIFORM_SIZE = GL31.GL_UNIFORM_SIZE;
	int GL_UNIFORM_NAME_LENGTH = GL31.GL_UNIFORM_NAME_LENGTH;

	int glGetSubroutineUniformLocation(@GLuint int program, @GLenum int shadertype, @Const @NullTerminated ByteBuffer name);

	@Alternate("glGetSubroutineUniformLocation")
	int glGetSubroutineUniformLocation(@GLuint int program, @GLenum int shadertype, @NullTerminated CharSequence name);

	@GLuint
	int glGetSubroutineIndex(@GLuint int program, @GLenum int shadertype, @Const @NullTerminated ByteBuffer name);

	@Alternate("glGetSubroutineIndex")
	@GLuint
	int glGetSubroutineIndex(@GLuint int program, @GLenum int shadertype, @NullTerminated CharSequence name);

	@StripPostfix("values")
	void glGetActiveSubroutineUniformiv(@GLuint int program, @GLenum int shadertype, @GLuint int index, @GLenum int pname,
	                                    @OutParameter @Check("1") IntBuffer values);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetActiveSubroutineUniformi} instead. */
	@Alternate("glGetActiveSubroutineUniformiv")
	@GLreturn("values")
	@StripPostfix("values")
	@Reuse(value = "GL40", method = "glGetActiveSubroutineUniformi")
	@Deprecated
	void glGetActiveSubroutineUniformiv2(@GLuint int program, @GLenum int shadertype, @GLuint int index, @GLenum int pname,
	                                     @OutParameter IntBuffer values);

	@Alternate("glGetActiveSubroutineUniformiv")
	@GLreturn("values")
	@StripPostfix(value = "values", postfix = "v")
	void glGetActiveSubroutineUniformiv3(@GLuint int program, @GLenum int shadertype, @GLuint int index, @GLenum int pname,
	                                     @OutParameter IntBuffer values);

	void glGetActiveSubroutineUniformName(@GLuint int program, @GLenum int shadertype, @GLuint int index, @AutoSize("name") @GLsizei int bufsize,
	                                      @OutParameter @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                                      @OutParameter @GLchar ByteBuffer name);

	@Alternate("glGetActiveSubroutineUniformName")
	@GLreturn(value = "name", maxLength = "bufsize")
	void glGetActiveSubroutineUniformName2(@GLuint int program, @GLenum int shadertype, @GLuint int index, @GLsizei int bufsize,
	                                       @OutParameter @Constant("MemoryUtil.getAddress0(name_length)") @GLsizei IntBuffer length,
	                                       @OutParameter @GLchar ByteBuffer name);

	void glGetActiveSubroutineName(@GLuint int program, @GLenum int shadertype, @GLuint int index, @AutoSize("name") @GLsizei int bufsize,
	                               @OutParameter @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                               @OutParameter @GLchar ByteBuffer name);

	@Alternate("glGetActiveSubroutineName")
	@GLreturn(value = "name", maxLength = "bufsize")
	void glGetActiveSubroutineName2(@GLuint int program, @GLenum int shadertype, @GLuint int index, @GLsizei int bufsize,
	                                @OutParameter @Constant("MemoryUtil.getAddress0(name_length)") @GLsizei IntBuffer length,
	                                @OutParameter @GLchar ByteBuffer name);

	@StripPostfix("indices")
	void glUniformSubroutinesuiv(@GLenum int shadertype, @AutoSize("indices") @GLsizei int count, @Const @GLuint IntBuffer indices);

	@StripPostfix("params")
	void glGetUniformSubroutineuiv(@GLenum int shadertype, int location, @Check("1") @OutParameter @GLuint IntBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetUniformSubroutineui} instead. */
	@Alternate("glGetUniformSubroutineuiv")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GL40", method = "glGetUniformSubroutineui")
	@Deprecated
	void glGetUniformSubroutineuiv2(@GLenum int shadertype, int location, @OutParameter @GLuint IntBuffer params);

	@Alternate("glGetUniformSubroutineuiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetUniformSubroutineuiv3(@GLenum int shadertype, int location, @OutParameter @GLuint IntBuffer params);

	@StripPostfix("values")
	void glGetProgramStageiv(@GLuint int program, @GLenum int shadertype, @GLenum int pname, @Check("1") @OutParameter IntBuffer values);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetProgramStagei} instead. */
	@Alternate("glGetProgramStageiv")
	@GLreturn("values")
	@StripPostfix("values")
	@Reuse(value = "GL40", method = "glGetProgramStagei")
	@Deprecated
	void glGetProgramStageiv2(@GLuint int program, @GLenum int shadertype, @GLenum int pname, @OutParameter IntBuffer values);

	@Alternate("glGetProgramStageiv")
	@GLreturn("values")
	@StripPostfix(value = "values", postfix = "v")
	void glGetProgramStageiv3(@GLuint int program, @GLenum int shadertype, @GLenum int pname, @OutParameter IntBuffer values);

	// -----------------------------------------------------------------------
	// ----------------------[ ARB_tessellation_shader ]----------------------
	// -----------------------------------------------------------------------

	/**
	 * Accepted by the &lt;mode&gt; parameter of Begin and all vertex array functions
	 * that implicitly call Begin:
	 */
	int GL_PATCHES = 0xE;

	/**
	 * Accepted by the &lt;pname&gt; parameter of PatchParameteri, GetBooleanv,
	 * GetDoublev, GetFloatv, GetIntegerv, and GetInteger64v:
	 */
	int GL_PATCH_VERTICES = 0x8E72;

	/**
	 * Accepted by the &lt;pname&gt; parameter of PatchParameterfv, GetBooleanv,
	 * GetDoublev, GetFloatv, and GetIntegerv, and GetInteger64v:
	 */
	int GL_PATCH_DEFAULT_INNER_LEVEL = 0x8E73;
	int GL_PATCH_DEFAULT_OUTER_LEVEL = 0x8E74;

	/** Accepted by the &lt;pname&gt; parameter of GetProgramiv: */
	int GL_TESS_CONTROL_OUTPUT_VERTICES = 0x8E75;
	int GL_TESS_GEN_MODE = 0x8E76;
	int GL_TESS_GEN_SPACING = 0x8E77;
	int GL_TESS_GEN_VERTEX_ORDER = 0x8E78;
	int GL_TESS_GEN_POINT_MODE = 0x8E79;

	/** Returned by GetProgramiv when &lt;pname&gt; is TESS_GEN_MODE: */
	int GL_ISOLINES = 0x8E7A;

	/** Returned by GetProgramiv when &lt;pname&gt; is TESS_GEN_SPACING: */
	int GL_FRACTIONAL_ODD = 0x8E7B;
	int GL_FRACTIONAL_EVEN = 0x8E7C;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetDoublev, GetFloatv,
	 * GetIntegerv, and GetInteger64v:
	 */
	int GL_MAX_PATCH_VERTICES = 0x8E7D;
	int GL_MAX_TESS_GEN_LEVEL = 0x8E7E;
	int GL_MAX_TESS_CONTROL_UNIFORM_COMPONENTS = 0x8E7F;
	int GL_MAX_TESS_EVALUATION_UNIFORM_COMPONENTS = 0x8E80;
	int GL_MAX_TESS_CONTROL_TEXTURE_IMAGE_UNITS = 0x8E81;
	int GL_MAX_TESS_EVALUATION_TEXTURE_IMAGE_UNITS = 0x8E82;
	int GL_MAX_TESS_CONTROL_OUTPUT_COMPONENTS = 0x8E83;
	int GL_MAX_TESS_PATCH_COMPONENTS = 0x8E84;
	int GL_MAX_TESS_CONTROL_TOTAL_OUTPUT_COMPONENTS = 0x8E85;
	int GL_MAX_TESS_EVALUATION_OUTPUT_COMPONENTS = 0x8E86;
	int GL_MAX_TESS_CONTROL_UNIFORM_BLOCKS = 0x8E89;
	int GL_MAX_TESS_EVALUATION_UNIFORM_BLOCKS = 0x8E8A;
	int GL_MAX_TESS_CONTROL_INPUT_COMPONENTS = 0x886C;
	int GL_MAX_TESS_EVALUATION_INPUT_COMPONENTS = 0x886D;
	int GL_MAX_COMBINED_TESS_CONTROL_UNIFORM_COMPONENTS = 0x8E1E;
	int GL_MAX_COMBINED_TESS_EVALUATION_UNIFORM_COMPONENTS = 0x8E1F;

	/** Accepted by the &lt;pname&gt; parameter of GetActiveUniformBlockiv: */
	int GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_CONTROL_SHADER = 0x84F0;
	int GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_EVALUATION_SHADER = 0x84F1;

	/**
	 * Accepted by the &lt;type&gt; parameter of CreateShader and returned by the
	 * &lt;params&gt; parameter of GetShaderiv:
	 */
	int GL_TESS_EVALUATION_SHADER = 0x8E87;
	int GL_TESS_CONTROL_SHADER = 0x8E88;

	void glPatchParameteri(@GLenum int pname, int value);

	@StripPostfix("values")
	void glPatchParameterfv(@GLenum int pname, @Check("4") @Const FloatBuffer values);

	// --------------------------------------------------------------------------
	// ----------------------[ ARB_texture_cube_map_array ]----------------------
	// --------------------------------------------------------------------------

	/**
	 * Accepted by the &lt;target&gt; parameter of TexParameteri, TexParameteriv,
	 * TexParameterf, TexParameterfv, BindTexture, and GenerateMipmap:
	 * <p/>
	 * Accepted by the &lt;target&gt; parameter of TexImage3D, TexSubImage3D,
	 * CompressedTeximage3D, CompressedTexSubImage3D and CopyTexSubImage3D:
	 * <p/>
	 * Accepted by the &lt;tex&gt; parameter of GetTexImage:
	 */
	int GL_TEXTURE_CUBE_MAP_ARRAY = 0x9009;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetDoublev,
	 * GetIntegerv and GetFloatv:
	 */
	int GL_TEXTURE_BINDING_CUBE_MAP_ARRAY = 0x900A;

	/**
	 * Accepted by the &lt;target&gt; parameter of TexImage3D, TexSubImage3D,
	 * CompressedTeximage3D, CompressedTexSubImage3D and CopyTexSubImage3D:
	 */
	int GL_PROXY_TEXTURE_CUBE_MAP_ARRAY = 0x900B;

	/** Returned by the &lt;type&gt; parameter of GetActiveUniform: */
	int GL_SAMPLER_CUBE_MAP_ARRAY = 0x900C;
	int GL_SAMPLER_CUBE_MAP_ARRAY_SHADOW = 0x900D;
	int GL_INT_SAMPLER_CUBE_MAP_ARRAY = 0x900E;
	int GL_UNSIGNED_INT_SAMPLER_CUBE_MAP_ARRAY = 0x900F;

	// ------------------------------------------------------------------
	// ----------------------[ ARB_texture_gather ]----------------------
	// ------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	int GL_MIN_PROGRAM_TEXTURE_GATHER_OFFSET_ARB = 0x8E5E;
	int GL_MAX_PROGRAM_TEXTURE_GATHER_OFFSET_ARB = 0x8E5F;
	int GL_MAX_PROGRAM_TEXTURE_GATHER_COMPONENTS_ARB = 0x8F9F;

	// -----------------------------------------------------------------------
	// ----------------------[ ARB_transform_feedback2 ]----------------------
	// -----------------------------------------------------------------------

	/** Accepted by the &lt;target&gt; parameter of BindTransformFeedback: */
	int GL_TRANSFORM_FEEDBACK = 0x8E22;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetDoublev, GetIntegerv,
	 * and GetFloatv:
	 */
	int GL_TRANSFORM_FEEDBACK_PAUSED = 0x8E23;
	int GL_TRANSFORM_FEEDBACK_ACTIVE = 0x8E24;
	int GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED = GL_TRANSFORM_FEEDBACK_PAUSED;
	int GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE = GL_TRANSFORM_FEEDBACK_ACTIVE;
	int GL_TRANSFORM_FEEDBACK_BINDING = 0x8E25;

	void glBindTransformFeedback(@GLenum int target, @GLuint int id);

	void glDeleteTransformFeedbacks(@AutoSize("ids") @GLsizei int n, @Const @GLuint IntBuffer ids);

	@Alternate("glDeleteTransformFeedbacks")
	void glDeleteTransformFeedbacks(@Constant("1") @GLsizei int n, @Constant(value = "APIUtil.getInt(caps, id)", keepParam = true) int id);

	void glGenTransformFeedbacks(@AutoSize("ids") @GLsizei int n, @OutParameter @GLuint IntBuffer ids);

	@Alternate("glGenTransformFeedbacks")
	@GLreturn("ids")
	void glGenTransformFeedbacks2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer ids);

	boolean glIsTransformFeedback(@GLuint int id);

	void glPauseTransformFeedback();

	void glResumeTransformFeedback();

	void glDrawTransformFeedback(@GLenum int mode, @GLuint int id);

	// -----------------------------------------------------------------------
	// ----------------------[ ARB_transform_feedback3 ]----------------------
	// -----------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetDoublev, GetIntegerv,
	 * and GetFloatv:
	 */
	int GL_MAX_TRANSFORM_FEEDBACK_BUFFERS = 0x8E70;

	void glDrawTransformFeedbackStream(@GLenum int mode, @GLuint int id, @GLuint int stream);

	void glBeginQueryIndexed(@GLenum int target, @GLuint int index, @GLuint int id);

	void glEndQueryIndexed(@GLenum int target, @GLuint int index);

	@StripPostfix("params")
	void glGetQueryIndexediv(@GLenum int target, @GLuint int index, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetQueryIndexedi} instead. */
	@Alternate("glGetQueryIndexediv")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GL40", method = "glGetQueryIndexedi")
	@Deprecated
	void glGetQueryIndexediv2(@GLenum int target, @GLuint int index, @GLenum int pname, @OutParameter IntBuffer params);

	@Alternate("glGetQueryIndexediv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetQueryIndexediv3(@GLenum int target, @GLuint int index, @GLenum int pname, @OutParameter IntBuffer params);

}