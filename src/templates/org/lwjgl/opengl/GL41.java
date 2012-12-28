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
import org.lwjgl.util.generator.opengl.*;

import java.nio.*;

public interface GL41 {

	// ---------------------------------------------------------------------
	// ----------------------[ ARB_ES2_compatibility ]----------------------
	// ---------------------------------------------------------------------

	/**
	 * Accepted by the &lt;value&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetInteger64v, GetFloatv, and GetDoublev:
	 */
	int GL_SHADER_COMPILER                  = 0x8DFA,
		GL_NUM_SHADER_BINARY_FORMATS        = 0x8DF9,
		GL_MAX_VERTEX_UNIFORM_VECTORS       = 0x8DFB,
		GL_MAX_VARYING_VECTORS              = 0x8DFC,
		GL_MAX_FRAGMENT_UNIFORM_VECTORS     = 0x8DFD,
		GL_IMPLEMENTATION_COLOR_READ_TYPE   = 0x8B9A,
		GL_IMPLEMENTATION_COLOR_READ_FORMAT = 0x8B9B;

	/** Accepted by the &lt;type&gt; parameter of VertexAttribPointer: */
	int GL_FIXED = 0x140C;

	/**
	 * Accepted by the &lt;precisiontype&gt; parameter of
	 * GetShaderPrecisionFormat:
	 */
	int GL_LOW_FLOAT    = 0x8DF0,
		GL_MEDIUM_FLOAT = 0x8DF1,
		GL_HIGH_FLOAT   = 0x8DF2,
		GL_LOW_INT      = 0x8DF3,
		GL_MEDIUM_INT   = 0x8DF4,
		GL_HIGH_INT     = 0x8DF5;

	/** Accepted by the &lt;format&gt; parameter of most commands taking sized internal formats: */
	int GL_RGB565 = 0x8D62;

	void glReleaseShaderCompiler();

	void glShaderBinary(@AutoSize("shaders") @GLsizei int count, @Const @GLuint IntBuffer shaders,
	                    @GLenum int binaryformat, @Const @GLvoid ByteBuffer binary, @AutoSize("binary") @GLsizei int length);

	void glGetShaderPrecisionFormat(@GLenum int shadertype, @GLenum int precisiontype,
	                                @OutParameter @Check("2") IntBuffer range,
	                                @OutParameter @Check("1") IntBuffer precision);

	void glDepthRangef(float n, float f);

	void glClearDepthf(float d);

	// ----------------------------------------------------------------------
	// ----------------------[ ARB_get_program_binary ]----------------------
	// ----------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameter of ProgramParameteri and
	 * GetProgramiv:
	 */
	int GL_PROGRAM_BINARY_RETRIEVABLE_HINT = 0x8257;

	/** Accepted by the &lt;pname&gt; parameter of GetProgramiv: */
	int GL_PROGRAM_BINARY_LENGTH = 0x8741;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetInteger64v, GetFloatv and GetDoublev:
	 */
	int GL_NUM_PROGRAM_BINARY_FORMATS = 0x87FE,
		GL_PROGRAM_BINARY_FORMATS     = 0x87FF;

	void glGetProgramBinary(@GLuint int program, @AutoSize("binary") @GLsizei int bufSize,
	                        @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                        @Check("1") @GLenum IntBuffer binaryFormat,
	                        @OutParameter @GLvoid ByteBuffer binary);

	void glProgramBinary(@GLuint int program, @GLenum int binaryFormat, @Const @GLvoid ByteBuffer binary, @AutoSize("binary") @GLsizei int length);

	void glProgramParameteri(@GLuint int program, @GLenum int pname, int value);

	// ---------------------------------------------------------------------------
	// ----------------------[ ARB_separate_shader_objects ]----------------------
	// ---------------------------------------------------------------------------

	/** Accepted by &lt;stages&gt; parameter to UseProgramStages: */
	int GL_VERTEX_SHADER_BIT          = 0x00000001,
		GL_FRAGMENT_SHADER_BIT        = 0x00000002,
		GL_GEOMETRY_SHADER_BIT        = 0x00000004,
		GL_TESS_CONTROL_SHADER_BIT    = 0x00000008,
		GL_TESS_EVALUATION_SHADER_BIT = 0x00000010,
		GL_ALL_SHADER_BITS            = 0xFFFFFFFF;

	/**
	 * Accepted by the &lt;pname&gt; parameter of ProgramParameteri and
	 * GetProgramiv:
	 */
	int GL_PROGRAM_SEPARABLE = 0x8258;

	/** Accepted by &lt;type&gt; parameter to GetProgramPipelineiv: */
	int GL_ACTIVE_PROGRAM = 0x8259;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetInteger64v, GetFloatv, and GetDoublev:
	 */
	int GL_PROGRAM_PIPELINE_BINDING = 0x825A;

	void glUseProgramStages(@GLuint int pipeline, @GLbitfield int stages, @GLuint int program);

	void glActiveShaderProgram(@GLuint int pipeline, @GLuint int program);

	/** Single null-terminated source code string. */
	@StripPostfix(value = "string", postfix = "v")
	@GLuint
	int glCreateShaderProgramv(@GLenum int type, @Constant("1") @GLsizei int count, @NullTerminated @Check @Const @Indirect @GLchar ByteBuffer string);

	/** Multiple null-terminated source code strings, one after the other. */
	@Alternate(value = "glCreateShaderProgramv", nativeAlt = true)
	@StripPostfix(value = "strings", postfix = "v")
	@GLuint
	int glCreateShaderProgramv2(@GLenum int type, @GLsizei int count, @NullTerminated("count") @Check @Const @Indirect @GLchar @PointerArray("count") ByteBuffer strings);

	@Alternate(value = "glCreateShaderProgramv", nativeAlt = true)
	@StripPostfix(value = "strings", postfix = "v")
	@GLuint
	int glCreateShaderProgramv3(@GLenum int type, @Constant("strings.length") @GLsizei int count, @NullTerminated @Check("1") @PointerArray(value = "count") @Const @NativeType("GLchar") ByteBuffer[] strings);

	@Alternate("glCreateShaderProgramv")
	@StripPostfix(value = "string", postfix = "v")
	@GLuint
	int glCreateShaderProgramv(@GLenum int type, @Constant("1") @GLsizei int count, @NullTerminated CharSequence string);

	@Alternate(value = "glCreateShaderProgramv", nativeAlt = true, skipNative = true)
	@StripPostfix(value = "strings", postfix = "v")
	@GLuint
	int glCreateShaderProgramv2(@GLenum int type, @Constant("strings.length") @GLsizei int count,
	                            @Const @NullTerminated @PointerArray(value = "count") CharSequence[] strings);

	void glBindProgramPipeline(@GLuint int pipeline);

	void glDeleteProgramPipelines(@AutoSize("pipelines") @GLsizei int n, @Const @GLuint IntBuffer pipelines);

	@Alternate("glDeleteProgramPipelines")
	void glDeleteProgramPipelines(@Constant("1") @GLsizei int n, @Constant(value = "APIUtil.getInt(caps, pipeline)", keepParam = true) int pipeline);

	void glGenProgramPipelines(@AutoSize("pipelines") @GLsizei int n, @OutParameter @GLuint IntBuffer pipelines);

	@Alternate("glGenProgramPipelines")
	@GLreturn("pipelines")
	void glGenProgramPipelines2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer pipelines);

	boolean glIsProgramPipeline(@GLuint int pipeline);

	@StripPostfix("params")
	void glGetProgramPipelineiv(@GLuint int pipeline, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	@Alternate("glGetProgramPipelineiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetProgramPipelineiv2(@GLuint int pipeline, @GLenum int pname, @OutParameter IntBuffer params);

	void glProgramUniform1i(@GLuint int program, int location, int v0);

	void glProgramUniform2i(@GLuint int program, int location, int v0, int v1);

	void glProgramUniform3i(@GLuint int program, int location, int v0, int v1, int v2);

	void glProgramUniform4i(@GLuint int program, int location, int v0, int v1, int v2, int v3);

	void glProgramUniform1f(@GLuint int program, int location, float v0);

	void glProgramUniform2f(@GLuint int program, int location, float v0, float v1);

	void glProgramUniform3f(@GLuint int program, int location, float v0, float v1, float v2);

	void glProgramUniform4f(@GLuint int program, int location, float v0, float v1, float v2, float v3);

	void glProgramUniform1d(@GLuint int program, int location, double v0);

	void glProgramUniform2d(@GLuint int program, int location, double v0, double v1);

	void glProgramUniform3d(@GLuint int program, int location, double v0, double v1, double v2);

	void glProgramUniform4d(@GLuint int program, int location, double v0, double v1, double v2, double v3);

	@StripPostfix("value")
	void glProgramUniform1iv(@GLuint int program, int location, @AutoSize("value") @GLsizei int count, @Const IntBuffer value);

	@StripPostfix("value")
	void glProgramUniform2iv(@GLuint int program, int location, @AutoSize(value = "value", expression = " >> 1") @GLsizei int count, @Const IntBuffer value);

	@StripPostfix("value")
	void glProgramUniform3iv(@GLuint int program, int location, @AutoSize(value = "value", expression = " / 3") @GLsizei int count, @Const IntBuffer value);

	@StripPostfix("value")
	void glProgramUniform4iv(@GLuint int program, int location, @AutoSize(value = "value", expression = " >> 2") @GLsizei int count, @Const IntBuffer value);

	@StripPostfix("value")
	void glProgramUniform1fv(@GLuint int program, int location, @AutoSize("value") @GLsizei int count, @Const FloatBuffer value);

	@StripPostfix("value")
	void glProgramUniform2fv(@GLuint int program, int location, @AutoSize(value = "value", expression = " >> 1") @GLsizei int count, @Const FloatBuffer value);

	@StripPostfix("value")
	void glProgramUniform3fv(@GLuint int program, int location, @AutoSize(value = "value", expression = " / 3") @GLsizei int count, @Const FloatBuffer value);

	@StripPostfix("value")
	void glProgramUniform4fv(@GLuint int program, int location, @AutoSize(value = "value", expression = " >> 2") @GLsizei int count, @Const FloatBuffer value);

	@StripPostfix("value")
	void glProgramUniform1dv(@GLuint int program, int location, @AutoSize("value") @GLsizei int count, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glProgramUniform2dv(@GLuint int program, int location, @AutoSize(value = "value", expression = " >> 1") @GLsizei int count, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glProgramUniform3dv(@GLuint int program, int location, @AutoSize(value = "value", expression = " / 3") @GLsizei int count, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glProgramUniform4dv(@GLuint int program, int location, @AutoSize(value = "value", expression = " >> 2") @GLsizei int count, @Const DoubleBuffer value);

	void glProgramUniform1ui(@GLuint int program, int location, int v0);

	void glProgramUniform2ui(@GLuint int program, int location, int v0, int v1);

	void glProgramUniform3ui(@GLuint int program, int location, int v0, int v1, int v2);

	void glProgramUniform4ui(@GLuint int program, int location, int v0, int v1, int v2, int v3);

	@StripPostfix("value")
	void glProgramUniform1uiv(@GLuint int program, int location, @AutoSize("value") @GLsizei int count, @Const IntBuffer value);

	@StripPostfix("value")
	void glProgramUniform2uiv(@GLuint int program, int location, @AutoSize(value = "value", expression = " >> 1") @GLsizei int count, @Const IntBuffer value);

	@StripPostfix("value")
	void glProgramUniform3uiv(@GLuint int program, int location, @AutoSize(value = "value", expression = " / 3") @GLsizei int count, @Const IntBuffer value);

	@StripPostfix("value")
	void glProgramUniform4uiv(@GLuint int program, int location, @AutoSize(value = "value", expression = " >> 2") @GLsizei int count, @Const IntBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix2fv(@GLuint int program, int location, @AutoSize(value = "value", expression = " >> 2") @GLsizei int count, boolean transpose, @Const FloatBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix3fv(@GLuint int program, int location, @AutoSize(value = "value", expression = " / (3 * 3)") @GLsizei int count, boolean transpose, @Const FloatBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix4fv(@GLuint int program, int location, @AutoSize(value = "value", expression = " >> 4") @GLsizei int count, boolean transpose, @Const FloatBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix2dv(@GLuint int program, int location, @AutoSize(value = "value", expression = " >> 2") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix3dv(@GLuint int program, int location, @AutoSize(value = "value", expression = " / (3 * 3)") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix4dv(@GLuint int program, int location, @AutoSize(value = "value", expression = " >> 4") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix2x3fv(@GLuint int program, int location,
	                                 @AutoSize(value = "value", expression = " / (2 * 3)") @GLsizei int count, boolean transpose, @Const FloatBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix3x2fv(@GLuint int program, int location,
	                                 @AutoSize(value = "value", expression = " / (3 * 2)") @GLsizei int count, boolean transpose, @Const FloatBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix2x4fv(@GLuint int program, int location,
	                                 @AutoSize(value = "value", expression = " >> 3") @GLsizei int count, boolean transpose, @Const FloatBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix4x2fv(@GLuint int program, int location,
	                                 @AutoSize(value = "value", expression = " >> 3") @GLsizei int count, boolean transpose, @Const FloatBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix3x4fv(@GLuint int program, int location,
	                                 @AutoSize(value = "value", expression = " / (3 * 4)") @GLsizei int count, boolean transpose, @Const FloatBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix4x3fv(@GLuint int program, int location,
	                                 @AutoSize(value = "value", expression = " / (4 * 3)") @GLsizei int count, boolean transpose, @Const FloatBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix2x3dv(@GLuint int program, int location,
	                                 @AutoSize(value = "value", expression = " / (2 * 3)") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix3x2dv(@GLuint int program, int location,
	                                 @AutoSize(value = "value", expression = " / (3 * 2)") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix2x4dv(@GLuint int program, int location,
	                                 @AutoSize(value = "value", expression = " >> 3") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix4x2dv(@GLuint int program, int location,
	                                 @AutoSize(value = "value", expression = " >> 3") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix3x4dv(@GLuint int program, int location,
	                                 @AutoSize(value = "value", expression = " / (3 * 4)") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	@StripPostfix("value")
	void glProgramUniformMatrix4x3dv(@GLuint int program, int location,
	                                 @AutoSize(value = "value", expression = " / (4 * 3)") @GLsizei int count, boolean transpose, @Const DoubleBuffer value);

	void glValidateProgramPipeline(@GLuint int pipeline);

	void glGetProgramPipelineInfoLog(@GLuint int pipeline, @AutoSize("infoLog") @GLsizei int bufSize,
	                                 @OutParameter @Check(value = "1", canBeNull = true) @GLsizei IntBuffer length,
	                                 @OutParameter @GLchar ByteBuffer infoLog);

	@Alternate("glGetProgramPipelineInfoLog")
	@GLreturn(value = "infoLog", maxLength = "bufSize")
	void glGetProgramPipelineInfoLog2(@GLuint int pipeline, @GLsizei int bufSize,
	                                  @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(infoLog_length)") IntBuffer length,
	                                  @OutParameter @GLchar ByteBuffer infoLog);

	// -----------------------------------------------------------------------
	// ----------------------[ ARB_vertex_attrib_64bit ]----------------------
	// -----------------------------------------------------------------------

	/** Returned in the &lt;type&gt; parameter of GetActiveAttrib: */
	int GL_DOUBLE_VEC2   = 0x8FFC;
	int GL_DOUBLE_VEC3   = 0x8FFD;
	int GL_DOUBLE_VEC4   = 0x8FFE;
	int GL_DOUBLE_MAT2   = 0x8F46;
	int GL_DOUBLE_MAT3   = 0x8F47;
	int GL_DOUBLE_MAT4   = 0x8F48;
	int GL_DOUBLE_MAT2x3 = 0x8F49;
	int GL_DOUBLE_MAT2x4 = 0x8F4A;
	int GL_DOUBLE_MAT3x2 = 0x8F4B;
	int GL_DOUBLE_MAT3x4 = 0x8F4C;
	int GL_DOUBLE_MAT4x2 = 0x8F4D;
	int GL_DOUBLE_MAT4x3 = 0x8F4E;

	void glVertexAttribL1d(@GLuint int index, double x);

	void glVertexAttribL2d(@GLuint int index, double x, double y);

	void glVertexAttribL3d(@GLuint int index, double x, double y, double z);

	void glVertexAttribL4d(@GLuint int index, double x, double y, double z, double w);

	@StripPostfix("v")
	void glVertexAttribL1dv(@GLuint int index, @Const @Check("1") DoubleBuffer v);

	@StripPostfix("v")
	void glVertexAttribL2dv(@GLuint int index, @Const @Check("2") DoubleBuffer v);

	@StripPostfix("v")
	void glVertexAttribL3dv(@GLuint int index, @Const @Check("3") DoubleBuffer v);

	@StripPostfix("v")
	void glVertexAttribL4dv(@GLuint int index, @Const @Check("4") DoubleBuffer v);

	void glVertexAttribLPointer(@GLuint int index, int size, @Constant("GL11.GL_DOUBLE") @GLenum int type, @GLsizei int stride,
	                            @CachedReference(index = "index", name = "glVertexAttribPointer_buffer")
	                            @BufferObject(BufferKind.ArrayVBO)
	                            @Check @Const @GLdouble Buffer pointer);

	@StripPostfix("params")
	void glGetVertexAttribLdv(@GLuint int index, @GLenum int pname, @OutParameter @Check("4") DoubleBuffer params);

	// ------------------------------------------------------------------
	// ----------------------[ ARB_viewport_array ]----------------------
	// ------------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv,
	 * GetDoublev and GetInteger64v:
	 */
	int GL_MAX_VIEWPORTS                   = 0x825B,
		GL_VIEWPORT_SUBPIXEL_BITS          = 0x825C,
		GL_VIEWPORT_BOUNDS_RANGE           = 0x825D,
		GL_LAYER_PROVOKING_VERTEX          = 0x825E,
		GL_VIEWPORT_INDEX_PROVOKING_VERTEX = 0x825F;

	/** Accepted by the &lt;pname&gt; parameter of GetIntegeri_v: */
	int GL_SCISSOR_BOX = 0x0C10;

	/** Accepted by the &lt;pname&gt; parameter of GetFloati_v: */
	int GL_VIEWPORT = 0x0BA2;

	/** Accepted by the &lt;pname&gt; parameter of GetDoublei_v: */
	int GL_DEPTH_RANGE = 0x0B70;

	/** Accepted by the &lt;pname&gt; parameter of Enablei, Disablei, and IsEnabledi: */
	int GL_SCISSOR_TEST = 0x0C11;

	/**
	 * Returned in the &lt;data&gt; parameter from a Get query with a &lt;pname&gt; of
	 * LAYER_PROVOKING_VERTEX or VIEWPORT_INDEX_PROVOKING_VERTEX:
	 */
	int GL_FIRST_VERTEX_CONVENTION = 0x8E4D,
		GL_LAST_VERTEX_CONVENTION  = 0x8E4E,
		GL_PROVOKING_VERTEX        = 0x8E4F,
		GL_UNDEFINED_VERTEX        = 0x8260;

	@StripPostfix("v")
	void glViewportArrayv(@GLuint int first, @AutoSize(value = "v", expression = " >> 2") @GLsizei int count, @Const FloatBuffer v);

	void glViewportIndexedf(@GLuint int index, float x, float y, float w, float h);

	@StripPostfix("v")
	void glViewportIndexedfv(@GLuint int index, @Check("4") @Const FloatBuffer v);

	@StripPostfix("v")
	void glScissorArrayv(@GLuint int first, @AutoSize(value = "v", expression = " >> 2") @GLsizei int count, @Const IntBuffer v);

	void glScissorIndexed(@GLuint int index, int left, int bottom, @GLsizei int width, @GLsizei int height);

	@StripPostfix("v")
	void glScissorIndexedv(@GLuint int index, @Check("4") @Const IntBuffer v);

	@StripPostfix("v")
	void glDepthRangeArrayv(@GLuint int first, @AutoSize(value = "v", expression = " >> 1") @GLsizei int count, @Const DoubleBuffer v);

	void glDepthRangeIndexed(@GLuint int index, double n, double f);

	@StripPostfix("data")
	void glGetFloati_v(@GLenum int target, @GLuint int index, @Check @OutParameter FloatBuffer data);

	@Alternate("glGetFloati_v")
	@GLreturn("data")
	@StripPostfix("data")
	void glGetFloati_v2(@GLenum int target, @GLuint int index, @OutParameter FloatBuffer data);

	@StripPostfix("data")
	void glGetDoublei_v(@GLenum int target, @GLuint int index, @Check @OutParameter DoubleBuffer data);

	@Alternate("glGetDoublei_v")
	@GLreturn("data")
	@StripPostfix("data")
	void glGetDoublei_v2(@GLenum int target, @GLuint int index, @OutParameter DoubleBuffer data);

}