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

import java.nio.*;

public interface GL20 {
	// ------------------------------------------------------------------
	// -------------------[ ARB_shading_language_100 ]-------------------
	// ------------------------------------------------------------------

	/** Accepted by the &lt;name&gt; parameter of GetString: */
	int GL_SHADING_LANGUAGE_VERSION = 0x8B8C;

	// ------------------------------------------------------------------
	// ----------------------[ ARB_shader_objects ]----------------------
	// ------------------------------------------------------------------

	/** Accepted by the &lt;pname&gt; argument of GetInteger: */
	int GL_CURRENT_PROGRAM = 0x8B8D;

	/** Accepted by the &lt;pname&gt; parameter of GetObjectParameter{fi}vARB: */
	int GL_SHADER_TYPE = 0x8B4F;
	int GL_DELETE_STATUS = 0x8B80;
	int GL_COMPILE_STATUS = 0x8B81;
	int GL_LINK_STATUS = 0x8B82;
	int GL_VALIDATE_STATUS = 0x8B83;
	int GL_INFO_LOG_LENGTH = 0x8B84;
	int GL_ATTACHED_SHADERS = 0x8B85;
	int GL_ACTIVE_UNIFORMS = 0x8B86;
	int GL_ACTIVE_UNIFORM_MAX_LENGTH = 0x8B87;
	int GL_ACTIVE_ATTRIBUTES = 0x8B89;
	int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 0x8B8A;
	int GL_SHADER_SOURCE_LENGTH = 0x8B88;

	/** Returned by the &lt;params&gt; parameter of GetObjectParameter{fi}vARB: */
	int GL_SHADER_OBJECT = 0x8B48;

	/** Returned by the &lt;type&gt; parameter of GetActiveUniformARB: */
	int GL_FLOAT_VEC2 = 0x8B50;
	int GL_FLOAT_VEC3 = 0x8B51;
	int GL_FLOAT_VEC4 = 0x8B52;
	int GL_INT_VEC2 = 0x8B53;
	int GL_INT_VEC3 = 0x8B54;
	int GL_INT_VEC4 = 0x8B55;
	int GL_BOOL = 0x8B56;
	int GL_BOOL_VEC2 = 0x8B57;
	int GL_BOOL_VEC3 = 0x8B58;
	int GL_BOOL_VEC4 = 0x8B59;
	int GL_FLOAT_MAT2 = 0x8B5A;
	int GL_FLOAT_MAT3 = 0x8B5B;
	int GL_FLOAT_MAT4 = 0x8B5C;
	int GL_SAMPLER_1D = 0x8B5D;
	int GL_SAMPLER_2D = 0x8B5E;
	int GL_SAMPLER_3D = 0x8B5F;
	int GL_SAMPLER_CUBE = 0x8B60;
	int GL_SAMPLER_1D_SHADOW = 0x8B61;
	int GL_SAMPLER_2D_SHADOW = 0x8B62;

	/**
	 * The ARB_shader_objects extension allows multiple, optionally null-terminated, source strings to define a shader program.
	 * <p/>
	 * This method uses just a single string, that should NOT be null-terminated.
	 *
	 * @param shader
	 * @param string
	 */
	void glShaderSource(@GLuint int shader, @Constant("1") @GLsizei int count,
	                    @Indirect @Const @GLchar @Check ByteBuffer string,
	                    @AutoSize("string") @Indirect @Const @GLint int length);

	@Alternate("glShaderSource")
	void glShaderSource2(@GLuint int shader, @Constant("1") @GLsizei int count, CharSequence string, @Constant("string.length()") @Indirect @Const int length);

	@Alternate(value = "glShaderSource", nativeAlt = true)
	void glShaderSource3(@GLuint int shader, @Constant("strings.length") @GLsizei int count,
	                     @Const @PointerArray(value = "count", lengths = "length") CharSequence[] strings,
	                     @Constant("APIUtil.getLengths(caps, strings)") @Const IntBuffer length);

	int glCreateShader(@GLuint int type);

	boolean glIsShader(@GLuint int shader);

	void glCompileShader(@GLuint int shader);

	void glDeleteShader(@GLuint int shader);

	int glCreateProgram();

	boolean glIsProgram(int program);

	void glAttachShader(@GLuint int program, @GLuint int shader);

	void glDetachShader(@GLuint int program, @GLuint int shader);

	void glLinkProgram(@GLuint int program);

	void glUseProgram(@GLuint int program);

	void glValidateProgram(@GLuint int program);

	void glDeleteProgram(@GLuint int program);

	void glUniform1f(int location, float v0);

	void glUniform2f(int location, float v0, float v1);

	void glUniform3f(int location, float v0, float v1, float v2);

	void glUniform4f(int location, float v0, float v1, float v2, float v3);

	void glUniform1i(int location, int v0);

	void glUniform2i(int location, int v0, int v1);

	void glUniform3i(int location, int v0, int v1, int v2);

	void glUniform4i(int location, int v0, int v1, int v2, int v3);

	@StripPostfix("values")
	void glUniform1fv(int location, @AutoSize("values") @GLsizei int count, @Const FloatBuffer values);

	@StripPostfix("values")
	void glUniform2fv(int location, @AutoSize(value = "values", expression = " >> 1") @GLsizei int count, @Const FloatBuffer values);

	@StripPostfix("values")
	void glUniform3fv(int location, @AutoSize(value = "values", expression = " / 3") @GLsizei int count, @Const FloatBuffer values);

	@StripPostfix("values")
	void glUniform4fv(int location, @AutoSize(value = "values", expression = " >> 2") @GLsizei int count, @Const FloatBuffer values);

	@StripPostfix("values")
	void glUniform1iv(int location, @AutoSize("values") @GLsizei int count, @Const IntBuffer values);

	@StripPostfix("values")
	void glUniform2iv(int location, @AutoSize(value = "values", expression = " >> 1") @GLsizei int count, @Const IntBuffer values);

	@StripPostfix("values")
	void glUniform3iv(int location, @AutoSize(value = "values", expression = " / 3") @GLsizei int count, @Const IntBuffer values);

	@StripPostfix("values")
	void glUniform4iv(int location, @AutoSize(value = "values", expression = " >> 2") @GLsizei int count, @Const IntBuffer values);

	@StripPostfix("matrices")
	void glUniformMatrix2fv(int location, @AutoSize(value = "matrices", expression = " >> 2") @GLsizei int count,
	                        boolean transpose, @Const FloatBuffer matrices);

	@StripPostfix("matrices")
	void glUniformMatrix3fv(int location, @AutoSize(value = "matrices", expression = " / (3 * 3)") @GLsizei int count,
	                        boolean transpose, @Const FloatBuffer matrices);

	@StripPostfix("matrices")
	void glUniformMatrix4fv(int location, @AutoSize(value = "matrices", expression = " >> 4") @GLsizei int count,
	                        boolean transpose, @Const FloatBuffer matrices);

	@StripPostfix("params")
	void glGetShaderiv(@GLuint int shader, @GLenum int pname, @OutParameter @Check IntBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetShaderi} instead. */
	@Alternate("glGetShaderiv")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GL20", method = "glGetShaderi")
	@Deprecated
	void glGetShaderiv2(@GLuint int shader, @GLenum int pname, @OutParameter IntBuffer params);

	@Alternate("glGetShaderiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetShaderiv3(@GLuint int shader, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetProgramiv(@GLuint int program, @GLenum int pname, @OutParameter @Check IntBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetProgrami} instead. */
	@Alternate("glGetProgramiv")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GL20", method = "glGetProgrami")
	@Deprecated
	void glGetProgramiv2(@GLuint int program, @GLenum int pname, @OutParameter IntBuffer params);

	@Alternate("glGetProgramiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetProgramiv3(@GLuint int program, @GLenum int pname, @OutParameter IntBuffer params);

	void glGetShaderInfoLog(@GLuint int shader, @AutoSize("infoLog") @GLsizei int maxLength,
	                        @OutParameter @GLsizei @Check(value = "1", canBeNull = true) IntBuffer length,
	                        @OutParameter @GLchar ByteBuffer infoLog);

	@Alternate("glGetShaderInfoLog")
	@GLreturn(value = "infoLog", maxLength = "maxLength")
	void glGetShaderInfoLog2(@GLuint int shader, @GLsizei int maxLength,
	                         @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(infoLog_length)") IntBuffer length,
	                         @OutParameter @GLchar ByteBuffer infoLog);

	void glGetProgramInfoLog(@GLuint int program, @AutoSize("infoLog") @GLsizei int maxLength,
	                         @OutParameter @GLsizei @Check(value = "1", canBeNull = true) IntBuffer length,
	                         @OutParameter @GLchar ByteBuffer infoLog);

	@Alternate("glGetProgramInfoLog")
	@GLreturn(value = "infoLog", maxLength = "maxLength")
	void glGetProgramInfoLog2(@GLuint int program, @GLsizei int maxLength,
	                          @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(infoLog_length)") IntBuffer length,
	                          @OutParameter @GLchar ByteBuffer infoLog);

	void glGetAttachedShaders(@GLuint int program, @AutoSize("shaders") @GLsizei int maxCount,
	                          @OutParameter @GLsizei @Check(value = "1", canBeNull = true) IntBuffer count,
	                          @OutParameter @GLuint IntBuffer shaders);

	/**
	 * Returns the location of the uniform with the specified name. The ByteBuffer should contain the uniform name as a
	 * <b>null-terminated</b> string.
	 *
	 * @param program
	 * @param name
	 */
	int glGetUniformLocation(@GLuint int program, @NullTerminated @Check("1") @Const @GLchar ByteBuffer name);

	@Alternate("glGetUniformLocation")
	int glGetUniformLocation(@GLuint int program, @NullTerminated CharSequence name);

	void glGetActiveUniform(@GLuint int program, @GLuint int index, @AutoSize("name") @GLsizei int maxLength,
	                        @OutParameter @GLsizei @Check(value = "1", canBeNull = true) IntBuffer length,
	                        @OutParameter @GLsizei @Check("1") IntBuffer size,
	                        @OutParameter @GLenum @Check("1") IntBuffer type,
	                        @OutParameter @GLchar ByteBuffer name);

	/** Overloads glGetActiveUniform. This version returns both size and type in the sizeType buffer (at .position() and .position() + 1). */
	@Alternate("glGetActiveUniform")
	@GLreturn(value = "name", maxLength = "maxLength")
	void glGetActiveUniform2(@GLuint int program, @GLuint int index, @GLsizei int maxLength,
	                         @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(name_length)") IntBuffer length,
	                         @OutParameter @Check("2") IntBuffer sizeType,
	                         @OutParameter @GLenum @Constant("MemoryUtil.getAddress(sizeType, sizeType.position() + 1)") IntBuffer type,
	                         @OutParameter @GLchar ByteBuffer name);

	/** Overloads glGetActiveUniformARB. This version returns only the uniform name. */
	@Alternate(value = "glGetActiveUniform", javaAlt = true)
	@GLreturn(value = "name", maxLength = "maxLength")
	void glGetActiveUniform(@GLuint int program, @GLuint int index, @GLsizei int maxLength,
	                        @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress0(APIUtil.getBufferInt(caps)), MemoryUtil.getAddress(APIUtil.getBufferInt(caps), 1)") IntBuffer length,
	                        @OutParameter @GLchar ByteBuffer name);

	/** Overloads glGetActiveUniform. This version returns only the uniform size. */
	@Alternate(value = "glGetActiveUniform", javaAlt = true)
	@GLreturn(value = "size")
	void glGetActiveUniformSize(@GLuint int program, @GLuint int index, @Constant("1") @GLsizei int maxLength,
	                            @OutParameter @GLsizei @Constant("0L") IntBuffer length,
	                            @OutParameter IntBuffer size,
	                            @OutParameter @GLenum @Constant("MemoryUtil.getAddress(size, 1)") IntBuffer type, // Reuse size buffer and ignore
	                            @OutParameter @GLchar @Constant("APIUtil.getBufferByte0(caps)") ByteBuffer name);

	/** Overloads glGetActiveUniform. This version returns only the uniform type. */
	@Alternate(value = "glGetActiveUniform", javaAlt = true)
	@GLreturn(value = "type")
	void glGetActiveUniformType(@GLuint int program, @GLuint int index, @Constant("0") @GLsizei int maxLength,
	                            @OutParameter @GLsizei @Constant("0L") IntBuffer length,
	                            @OutParameter @Constant("MemoryUtil.getAddress(type, 1)") IntBuffer size, // Reuse type buffer and ignore
	                            @OutParameter @GLenum IntBuffer type,
	                            @OutParameter @GLchar @Constant("APIUtil.getBufferByte0(caps)") ByteBuffer name);

	@StripPostfix("params")
	void glGetUniformfv(@GLuint int program, int location, @OutParameter @Check FloatBuffer params);

	@StripPostfix("params")
	void glGetUniformiv(@GLuint int program, int location, @OutParameter @Check IntBuffer params);

	void glGetShaderSource(@GLuint int shader, @AutoSize("source") @GLsizei int maxLength,
	                       @OutParameter @GLsizei @Check(value = "1", canBeNull = true) IntBuffer length,
	                       @OutParameter @GLchar ByteBuffer source);

	@Alternate("glGetShaderSource")
	@GLreturn(value = "source", maxLength = "maxLength")
	void glGetShaderSource2(@GLuint int shader, @GLsizei int maxLength,
	                        @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(source_length)") IntBuffer length,
	                        @OutParameter @GLchar ByteBuffer source);

	// ------------------------------------------------------------------
	// ----------------------[ ARB_vertex_program ]----------------------
	// ------------------------------------------------------------------

	@NoErrorCheck
	void glVertexAttrib1s(@GLuint int index, short x);

	@NoErrorCheck
	void glVertexAttrib1f(@GLuint int index, float x);

	@NoErrorCheck
	void glVertexAttrib1d(@GLuint int index, double x);

	@NoErrorCheck
	void glVertexAttrib2s(@GLuint int index, short x, short y);

	@NoErrorCheck
	void glVertexAttrib2f(@GLuint int index, float x, float y);

	@NoErrorCheck
	void glVertexAttrib2d(@GLuint int index, double x, double y);

	@NoErrorCheck
	void glVertexAttrib3s(@GLuint int index, short x, short y, short z);

	@NoErrorCheck
	void glVertexAttrib3f(@GLuint int index, float x, float y, float z);

	@NoErrorCheck
	void glVertexAttrib3d(@GLuint int index, double x, double y, double z);

	@NoErrorCheck
	void glVertexAttrib4s(@GLuint int index, short x, short y, short z, short w);

	@NoErrorCheck
	void glVertexAttrib4f(@GLuint int index, float x, float y, float z, float w);

	@NoErrorCheck
	void glVertexAttrib4d(@GLuint int index, double x, double y, double z, double w);

	@NoErrorCheck
	void glVertexAttrib4Nub(@GLuint int index, @GLubyte byte x, @GLubyte byte y, @GLubyte byte z, @GLubyte byte w);

	void glVertexAttribPointer(@GLuint int index, int size, @AutoType("buffer") @GLenum int type, boolean normalized, @GLsizei int stride,
	                           @CachedReference(index = "index", name = "glVertexAttribPointer_buffer")
	                           @BufferObject(BufferKind.ArrayVBO)
	                           @Check
	                           @Const
	                           @GLubyte
	                           @GLbyte
	                           @GLshort
	                           @GLushort
	                           @GLint
	                           @GLuint
	                           @GLfloat
	                           @GLdouble Buffer buffer);

	@Alternate("glVertexAttribPointer")
	void glVertexAttribPointer(@GLuint int index, int size, @GLenum int type, boolean normalized, @GLsizei int stride,
	                           @CachedReference(index = "index", name = "glVertexAttribPointer_buffer")
	                           @BufferObject(BufferKind.ArrayVBO)
	                           @Check
	                           @Const ByteBuffer buffer);

	void glEnableVertexAttribArray(@GLuint int index);

	void glDisableVertexAttribArray(@GLuint int index);

	@StripPostfix("params")
	void glGetVertexAttribfv(@GLuint int index, @GLenum int pname, @OutParameter @Check("4") FloatBuffer params);

	@StripPostfix("params")
	void glGetVertexAttribdv(@GLuint int index, @GLenum int pname, @OutParameter @Check("4") DoubleBuffer params);

	@StripPostfix("params")
	void glGetVertexAttribiv(@GLuint int index, @GLenum int pname, @OutParameter @Check("4") IntBuffer params);

	@StripPostfix("pointer")
	void glGetVertexAttribPointerv(@GLuint int index, @GLenum int pname, @Result @GLvoid ByteBuffer pointer);

	@Alternate(value = "glGetVertexAttribPointerv", nativeAlt = true)
	@StripPostfix("pointer")
	void glGetVertexAttribPointerv2(@GLuint int index, @GLenum int pname, @OutParameter @Check("PointerBuffer.getPointerSize()") @GLvoid ByteBuffer pointer);

	// -----------------------------------------------------------------
	// ----------------------[ ARB_vertex_shader ]----------------------
	// -----------------------------------------------------------------

	/**
	 * Accepted by the &lt;shaderType&gt; argument of CreateShader and
	 * returned by the &lt;params&gt; parameter of GetShader{if}v:
	 */
	int GL_VERTEX_SHADER = 0x8B31;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	int GL_MAX_VERTEX_UNIFORM_COMPONENTS = 0x8B4A;
	int GL_MAX_VARYING_FLOATS = 0x8B4B;
	int GL_MAX_VERTEX_ATTRIBS = 0x8869;
	int GL_MAX_TEXTURE_IMAGE_UNITS = 0x8872;
	int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS = 0x8B4C;
	int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = 0x8B4D;
	int GL_MAX_TEXTURE_COORDS = 0x8871;

	/**
	 * Accepted by the &lt;cap&gt; parameter of Disable, Enable, and IsEnabled, and
	 * by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev:
	 */
	int GL_VERTEX_PROGRAM_POINT_SIZE = 0x8642;
	int GL_VERTEX_PROGRAM_TWO_SIDE = 0x8643;

	/** Accepted by the &lt;pname&gt; parameter of GetVertexAttrib{dfi}vARB: */
	int GL_VERTEX_ATTRIB_ARRAY_ENABLED = 0x8622;
	int GL_VERTEX_ATTRIB_ARRAY_SIZE = 0x8623;
	int GL_VERTEX_ATTRIB_ARRAY_STRIDE = 0x8624;
	int GL_VERTEX_ATTRIB_ARRAY_TYPE = 0x8625;
	int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED = 0x886A;
	int GL_CURRENT_VERTEX_ATTRIB = 0x8626;

	/** Accepted by the &lt;pname&gt; parameter of GetVertexAttribPointervARB: */
	int GL_VERTEX_ATTRIB_ARRAY_POINTER = 0x8645;

	void glBindAttribLocation(@GLuint int program, @GLuint int index, @NullTerminated @Const @GLchar ByteBuffer name);

	@Alternate("glBindAttribLocation")
	void glBindAttribLocation(@GLuint int program, @GLuint int index, @NullTerminated CharSequence name);

	void glGetActiveAttrib(@GLuint int program, @GLuint int index, @AutoSize("name") @GLsizei int maxLength,
	                       @OutParameter @GLsizei @Check(value = "1", canBeNull = true) IntBuffer length,
	                       @OutParameter @Check("1") IntBuffer size,
	                       @OutParameter @GLenum @Check("1") IntBuffer type,
	                       @OutParameter @GLchar ByteBuffer name);

	/** Overloads glGetActiveAttrib. This version returns both size and type in the sizeType buffer (at .position() and .position() + 1). */
	@Alternate("glGetActiveAttrib")
	@GLreturn(value = "name", maxLength = "maxLength")
	void glGetActiveAttrib2(@GLuint int program, @GLuint int index, @GLsizei int maxLength,
	                        @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(name_length)") IntBuffer length,
	                        @OutParameter @Check("2") IntBuffer sizeType,
	                        @OutParameter @GLenum @Constant("MemoryUtil.getAddress(sizeType, sizeType.position() + 1)") IntBuffer type,
	                        @OutParameter @GLchar ByteBuffer name);

	/** Overloads glGetActiveAttrib. This version returns only the attrib name. */
	@Alternate(value = "glGetActiveAttrib", javaAlt = true)
	@GLreturn(value = "name", maxLength = "maxLength")
	void glGetActiveAttrib(@GLuint int program, @GLuint int index, @GLsizei int maxLength,
	                       @OutParameter @GLsizei @Constant("MemoryUtil.getAddress0(name_length), MemoryUtil.getAddress0(APIUtil.getBufferInt(caps)), MemoryUtil.getAddress(APIUtil.getBufferInt(caps), 1)") IntBuffer length,
	                       @OutParameter @GLchar ByteBuffer name);

	/** Overloads glGetActiveAttribARB. This version returns only the attrib size. */
	@Alternate(value = "glGetActiveAttrib", javaAlt = true)
	@GLreturn(value = "size")
	void glGetActiveAttribSize(@GLuint int program, @GLuint int index, @Constant("0") @GLsizei int maxLength,
	                           @OutParameter @GLsizei @Constant("0L") IntBuffer length,
	                           @OutParameter IntBuffer size,
	                           @OutParameter @GLenum @Constant("MemoryUtil.getAddress(size, 1)") IntBuffer type, // Reuse size buffer and ignore
	                           @OutParameter @GLchar @Constant("APIUtil.getBufferByte0(caps)") ByteBuffer name);

	/** Overloads glGetActiveAttrib. This version returns only the attrib type. */
	@Alternate(value = "glGetActiveAttrib", javaAlt = true)
	@GLreturn(value = "type")
	void glGetActiveAttribType(@GLuint int program, @GLuint int index, @Constant("0") @GLsizei int maxLength,
	                           @OutParameter @GLsizei @Constant("0L") IntBuffer length,
	                           @OutParameter @Constant("MemoryUtil.getAddress(type, 1)") IntBuffer size, // Reuse type buffer and ignore
	                           @OutParameter @GLenum IntBuffer type,
	                           @OutParameter @GLchar @Constant("APIUtil.getBufferByte0(caps)") ByteBuffer name);

	int glGetAttribLocation(@GLuint int program, @NullTerminated @Const @GLchar ByteBuffer name);

	@Alternate("glGetAttribLocation")
	int glGetAttribLocation(@GLuint int program, @NullTerminated CharSequence name);

	// -------------------------------------------------------------------
	// ----------------------[ ARB_fragment_shader ]----------------------
	// -------------------------------------------------------------------

	/**
	 * Accepted by the &lt;shaderType&gt; argument of CreateShader and
	 * returned by the &lt;params&gt; parameter of GetShader{fi}vARB:
	 */
	int GL_FRAGMENT_SHADER = 0x8B30;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	int GL_MAX_FRAGMENT_UNIFORM_COMPONENTS = 0x8B49;

	/**
	 * Accepted by the &lt;target&gt; parameter of Hint and the &lt;pname&gt; parameter of
	 * GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev:
	 */
	int GL_FRAGMENT_SHADER_DERIVATIVE_HINT = 0x8B8B;

	// ----------------------------------------------------------------
	// ----------------------[ ARB_draw_buffers ]----------------------
	// ----------------------------------------------------------------

	/**
	 * Accepted by the &lt;pname&gt; parameters of GetIntegerv, GetFloatv,
	 * and GetDoublev:
	 */
	int GL_MAX_DRAW_BUFFERS = 0x8824;
	int GL_DRAW_BUFFER0 = 0x8825;
	int GL_DRAW_BUFFER1 = 0x8826;
	int GL_DRAW_BUFFER2 = 0x8827;
	int GL_DRAW_BUFFER3 = 0x8828;
	int GL_DRAW_BUFFER4 = 0x8829;
	int GL_DRAW_BUFFER5 = 0x882A;
	int GL_DRAW_BUFFER6 = 0x882B;
	int GL_DRAW_BUFFER7 = 0x882C;
	int GL_DRAW_BUFFER8 = 0x882D;
	int GL_DRAW_BUFFER9 = 0x882E;
	int GL_DRAW_BUFFER10 = 0x882F;
	int GL_DRAW_BUFFER11 = 0x8830;
	int GL_DRAW_BUFFER12 = 0x8831;
	int GL_DRAW_BUFFER13 = 0x8832;
	int GL_DRAW_BUFFER14 = 0x8833;
	int GL_DRAW_BUFFER15 = 0x8834;

	void glDrawBuffers(@AutoSize("buffers") @GLsizei int size, @Const @GLenum IntBuffer buffers);

	@Alternate("glDrawBuffers")
	void glDrawBuffers(@Constant("1") @GLsizei int size, @Constant(value = "APIUtil.getInt(caps, buffer)", keepParam = true) int buffer);

	// ----------------------------------------------------------------
	// ----------------------[ ARB_point_sprite ]----------------------
	// ----------------------------------------------------------------

	/**
	 * Accepted by the &lt;cap&gt; parameter of Enable, Disable, and IsEnabled, by
	 * the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev, and by the &lt;target&gt; parameter of TexEnvi, TexEnviv,
	 * TexEnvf, TexEnvfv, GetTexEnviv, and GetTexEnvfv:
	 */
	int GL_POINT_SPRITE = 0x8861;

	/**
	 * When the &lt;target&gt; parameter of TexEnvf, TexEnvfv, TexEnvi, TexEnviv,
	 * GetTexEnvfv, or GetTexEnviv is POINT_SPRITE, then the value of
	 * &lt;pname&gt; may be:
	 */
	int GL_COORD_REPLACE = 0x8862;

	/**
	 * Accepted by the &lt;pname&gt; parameter of PointParameter{if}vARB, and the
	 * &lt;pname&gt; of Get:
	 */
	int GL_POINT_SPRITE_COORD_ORIGIN = 0x8CA0;

	/** Accepted by the &lt;param&gt; parameter of PointParameter{if}vARB: */
	int GL_LOWER_LEFT = 0x8CA1;
	int GL_UPPER_LEFT = 0x8CA2;

	// -----------------------------------------------------------------
	// ----------------------[ Two-Sided Stencil ]----------------------
	// -----------------------------------------------------------------

	int GL_STENCIL_BACK_FUNC = 0x8800;
	int GL_STENCIL_BACK_FAIL = 0x8801;
	int GL_STENCIL_BACK_PASS_DEPTH_FAIL = 0x8802;
	int GL_STENCIL_BACK_PASS_DEPTH_PASS = 0x8803;
	int GL_STENCIL_BACK_REF = 0x8CA3;
	int GL_STENCIL_BACK_VALUE_MASK = 0x8CA4;
	int GL_STENCIL_BACK_WRITEMASK = 0x8CA5;

	void glStencilOpSeparate(@GLenum int face, @GLenum int sfail, @GLenum int dpfail, @GLenum int dppass);

	void glStencilFuncSeparate(@GLenum int face, @GLenum int func, int ref, @GLuint int mask);

	void glStencilMaskSeparate(@GLenum int face, @GLuint int mask);

	// -------------------------------------------------------------
	// ----------------------[ EXT_blend_equation_separate ]----------------------
	// -------------------------------------------------------------

	int GL_BLEND_EQUATION_RGB = 0x8009;
	int GL_BLEND_EQUATION_ALPHA = 0x883D;

	void glBlendEquationSeparate(@GLenum int modeRGB, @GLenum int modeAlpha);
}
