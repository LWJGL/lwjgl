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

import org.lwjgl.generator.*;
import org.lwjgl.BufferChecks;
import org.lwjgl.LWJGLException;

import java.nio.*;

public interface GL20 {
	// ------------------------------------------------------------------
	// ----------------------[ ARB_shading_language_100 ]----------------------
	// ------------------------------------------------------------------

	/*
	* Accepted by the <name> parameter of GetString:
	*/
	public static final int GL_SHADING_LANGUAGE_VERSION = 0x8B8C;

	// ------------------------------------------------------------------
	// ----------------------[ ARB_shader_objects ]----------------------
	// ------------------------------------------------------------------

	/*
	* Accepted by the <pname> argument of GetInteger:
	*/
	public static final int GL_CURRENT_PROGRAM = 0x8B8D;

	/*
	* Accepted by the <pname> parameter of GetObjectParameter{fi}vARB:
	*/
	public static final int GL_SHADER_TYPE = 0x8B4F;
	public static final int GL_DELETE_STATUS = 0x8B80;
	public static final int GL_COMPILE_STATUS = 0x8B81;
	public static final int GL_LINK_STATUS = 0x8B82;
	public static final int GL_VALIDATE_STATUS = 0x8B83;
	public static final int GL_INFO_LOG_LENGTH = 0x8B84;
	public static final int GL_ATTACHED_SHADERS = 0x8B85;
	public static final int GL_ACTIVE_UNIFORMS = 0x8B86;
	public static final int GL_ACTIVE_UNIFORM_MAX_LENGTH = 0x8B87;
	public static final int GL_ACTIVE_ATTRIBUTES = 0x8B89;
	public static final int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 0x8B8A;
	public static final int GL_SHADER_SOURCE_LENGTH = 0x8B88;

	/*
	* Returned by the <params> parameter of GetObjectParameter{fi}vARB:
	*/
	public static final int GL_SHADER_OBJECT = 0x8B48;

	/*
	* Returned by the <type> parameter of GetActiveUniformARB:
	*/
	public static final int GL_FLOAT_VEC2 = 0x8B50;
	public static final int GL_FLOAT_VEC3 = 0x8B51;
	public static final int GL_FLOAT_VEC4 = 0x8B52;
	public static final int GL_INT_VEC2 = 0x8B53;
	public static final int GL_INT_VEC3 = 0x8B54;
	public static final int GL_INT_VEC4 = 0x8B55;
	public static final int GL_BOOL = 0x8B56;
	public static final int GL_BOOL_VEC2 = 0x8B57;
	public static final int GL_BOOL_VEC3 = 0x8B58;
	public static final int GL_BOOL_VEC4 = 0x8B59;
	public static final int GL_FLOAT_MAT2 = 0x8B5A;
	public static final int GL_FLOAT_MAT3 = 0x8B5B;
	public static final int GL_FLOAT_MAT4 = 0x8B5C;
	public static final int GL_SAMPLER_1D = 0x8B5D;
	public static final int GL_SAMPLER_2D = 0x8B5E;
	public static final int GL_SAMPLER_3D = 0x8B5F;
	public static final int GL_SAMPLER_CUBE = 0x8B60;
	public static final int GL_SAMPLER_1D_SHADOW = 0x8B61;
	public static final int GL_SAMPLER_2D_SHADOW = 0x8B62;

	/**
	 * The ARB_shader_objects extension allows multiple, optionally null-terminated, source strings to define a shader program.
	 * <p/>
	 * This method uses just a single string, that should NOT be null-terminated.
	 *
	 * @param shader
	 * @param string
	 */
	public void glShaderSource(@GLuint int shader, @Constant("1") @GLsizei int count,
			@Indirect
			@Check
			@Const
			@GLchar
			ByteBuffer string,
			@AutoSize("string")
			@Indirect
			@Const
			@GLint
			int length);

	public int glCreateShader(@GLuint int type);

	public boolean glIsShader(@GLuint int shader);

	public void glCompileShader(@GLuint int shader);

	public void glDeleteShader(@GLuint int shader);

	public int glCreateProgram();

	public boolean glIsProgram(int program);

	public void glAttachShader(@GLuint int program, @GLuint int shader);

	public void glDetachShader(@GLuint int program, @GLuint int shader);

	public void glLinkProgram(@GLuint int program);

	public void glUseProgram(@GLuint int program);

	public void glValidateProgram(@GLuint int program);

	public void glDeleteProgram(@GLuint int program);

	public void glUniform1f(int location, float v0);

	public void glUniform2f(int location, float v0, float v1);

	public void glUniform3f(int location, float v0, float v1, float v2);

	public void glUniform4f(int location, float v0, float v1, float v2, float v3);

	public void glUniform1i(int location, int v0);

	public void glUniform2i(int location, int v0, int v1);

	public void glUniform3i(int location, int v0, int v1, int v2);

	public void glUniform4i(int location, int v0, int v1, int v2, int v3);
	
	@StripPostfix("values")
	public void glUniform1fv(int location, @AutoSize("values") @GLsizei int count, FloatBuffer values);
	@StripPostfix("values")
	public void glUniform2fv(int location, @AutoSize(value="values", expression=" >> 1") @GLsizei int count, FloatBuffer values);
	@StripPostfix("values")
	public void glUniform3fv(int location, @AutoSize(value="values", expression=" / 3") @GLsizei int count, FloatBuffer values);
	@StripPostfix("values")
	public void glUniform4fv(int location, @AutoSize(value="values", expression=" >> 2") @GLsizei int count, FloatBuffer values);

	@StripPostfix("values")
	public void glUniform1iv(int location, @AutoSize("values") @GLsizei int count, IntBuffer values);
	@StripPostfix("values")
	public void glUniform2iv(int location, @AutoSize(value="values", expression=" >> 1") @GLsizei int count, IntBuffer values);
	@StripPostfix("values")
	public void glUniform3iv(int location, @AutoSize(value="values", expression=" / 3") @GLsizei int count, IntBuffer values);
	@StripPostfix("values")
	public void glUniform4iv(int location, @AutoSize(value="values", expression=" >> 2") @GLsizei int count, IntBuffer values);

	@StripPostfix("matrices")
	public void glUniformMatrix2fv(int location, @AutoSize(value="matrices", expression=" >> 2") @GLsizei int count,
			boolean transpose, FloatBuffer matrices);
	@StripPostfix("matrices")
	public void glUniformMatrix3fv(int location, @AutoSize(value="matrices", expression=" / (3 * 3)") @GLsizei int count,
			boolean transpose, FloatBuffer matrices);
	@StripPostfix("matrices")
	public void glUniformMatrix4fv(int location, @AutoSize(value="matrices", expression=" >> 4") @GLsizei int count,
			boolean transpose, FloatBuffer matrices);

	@StripPostfix("params")
	public void glGetShaderfv(@GLuint int shader, @GLenum int pname, @Check FloatBuffer params);
	@StripPostfix("params")
	public void glGetShaderiv(@GLuint int shader, @GLenum int pname, @Check IntBuffer params);
	
	@StripPostfix("params")
	public void glGetProgramfv(@GLuint int program, @GLenum int pname, @Check FloatBuffer params);
	@StripPostfix("params")
	public void glGetProgramiv(@GLuint int program, @GLenum int pname, @Check IntBuffer params);

	public void glGetShaderInfoLog(@GLuint int shader, @AutoSize("infoLog") @GLsizei int maxLength,
			@GLsizei
			@Check(value="1", canBeNull=true)
			IntBuffer length,
			@GLchar
			ByteBuffer infoLog);

	public void glGetProgramInfoLog(@GLuint int program, @AutoSize("infoLog") @GLsizei int maxLength,
			@GLsizei
			@Check(value="1", canBeNull=true)
			IntBuffer length,
			@GLchar
			ByteBuffer infoLog);

	public void glGetAttachedShaders(@GLuint int program, @AutoSize("shaders") @GLsizei int maxCount,
			@GLsizei
			@Check(value="1", canBeNull=true)
			IntBuffer count,
			@GLuint
			IntBuffer shaders);
	
	/**
	 * Returns the location of the uniform with the specified name. The ByteBuffer should contain the uniform name as a
	 * <b>null-terminated</b> string.
	 *
	 * @param program
	 * @param name
	 *
	 * @return
	 */
	public int glGetUniformLocation(@GLuint int program, @NullTerminated @Check("1") @Const @GLchar ByteBuffer name);

	public void glGetActiveUniform(@GLuint int program, @GLuint int index, @AutoSize("name") @GLsizei int maxLength,
			@Check(value="1", canBeNull=true)
			@GLsizei
			IntBuffer length,
			@Check
			@GLsizei
			IntBuffer size,
			@Check
			@GLenum
			IntBuffer type,
			@GLchar
			ByteBuffer name);

	@StripPostfix("params")
	public void glGetUniformfv(@GLuint int program, int location, @Check FloatBuffer params);
	@StripPostfix("params")
	public void glGetUniformiv(@GLuint int program, int location, @Check IntBuffer params);
	
	public void glGetShaderSource(@GLuint int shader, @AutoSize("source") @GLsizei int maxLength,
			@Check(value="1", canBeNull=true)
			@GLsizei
			IntBuffer length,
			@GLchar
			ByteBuffer source);

	// ------------------------------------------------------------------
	// ----------------------[ ARB_vertex_program ]----------------------
	// ------------------------------------------------------------------

	public void glVertexAttrib1s(@GLuint int index, short x);

	public void glVertexAttrib1f(@GLuint int index, float x);

	public void glVertexAttrib2s(@GLuint int index, short x, short y);

	public void glVertexAttrib2f(@GLuint int index, float x, float y);

	public void glVertexAttrib3s(@GLuint int index, short x, short y, short z);

	public void glVertexAttrib3f(@GLuint int index, float x, float y, float z);

	public void glVertexAttrib4s(@GLuint int index, short x, short y, short z, short w);

	public void glVertexAttrib4f(@GLuint int index, float x, float y, float z, float w);

	public void glVertexAttrib4Nub(@GLuint int index, @GLubyte byte x, @GLubyte byte y, @GLubyte byte z, @GLubyte byte w);

	public void glVertexAttribPointer(@GLuint int index, int size, @AutoType("buffer") @GLenum int type, boolean normalized, @GLsizei int stride,
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
			Buffer buffer);

	public void glEnableVertexAttribArray(@GLuint int index);
	public void glDisableVertexAttribArray(@GLuint int index);

	@StripPostfix("params")
	public void glGetVertexAttribfv(@GLuint int index, @GLenum int pname, @Check("4") FloatBuffer params);
	@StripPostfix("params")
	public void glGetVertexAttribiv(@GLuint int index, @GLenum int pname, @Check("4") IntBuffer params);
	
	@StripPostfix("pointer")
	public void glGetVertexAttribPointerv(@GLuint int index, @GLenum int pname, @Result @GLvoid ByteBuffer pointer);

	// -----------------------------------------------------------------
	// ----------------------[ ARB_vertex_shader ]----------------------
	// -----------------------------------------------------------------

	/*
	* Accepted by the <shaderType> argument of CreateShader and
	* returned by the <params> parameter of GetShader{if}v:
	*/
	public static final int GL_VERTEX_SHADER = 0x8B31;

	/*
	* Accepted by the <pname> parameter of GetBooleanv, GetIntegerv,
	* GetFloatv, and GetDoublev:
	*/
	public static final int GL_MAX_VERTEX_UNIFORM_COMPONENTS = 0x8B4A;
	public static final int GL_MAX_VARYING_FLOATS = 0x8B4B;
	public static final int GL_MAX_VERTEX_ATTRIBS = 0x8869;
	public static final int GL_MAX_TEXTURE_IMAGE_UNITS = 0x8872;
	public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS = 0x8B4C;
	public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = 0x8B4D;
	public static final int GL_MAX_TEXTURE_COORDS = 0x8871;

	/*
	* Accepted by the <cap> parameter of Disable, Enable, and IsEnabled, and
	* by the <pname> parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	* GetDoublev:
	*/
	public static final int GL_VERTEX_PROGRAM_POINT_SIZE = 0x8642;
	public static final int GL_VERTEX_PROGRAM_TWO_SIDE = 0x8643;

	/*
	* Accepted by the <pname> parameter of GetVertexAttrib{dfi}vARB:
	*/
	public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED = 0x8622;
	public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE = 0x8623;
	public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE = 0x8624;
	public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE = 0x8625;
	public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED = 0x886A;
	public static final int GL_CURRENT_VERTEX_ATTRIB = 0x8626;

	/*
	* Accepted by the <pname> parameter of GetVertexAttribPointervARB:
	*/
	public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER = 0x8645;

	public void glBindAttribLocation(@GLuint int program, @GLuint int index, @NullTerminated @Const @GLchar ByteBuffer name);
	
	public void glGetActiveAttrib(@GLuint int program, @GLuint int index, @AutoSize("name") @GLsizei int maxLength,
			@Check(value="1", canBeNull=true)
			@GLsizei
			IntBuffer length,
			@Check("1")
			IntBuffer size,
			@Check("1")
			@GLenum
			IntBuffer type,
			@Const
			@GLchar
			ByteBuffer name);

	public int glGetAttribLocation(@GLuint int program, @NullTerminated @Const @GLchar ByteBuffer name);

	// -------------------------------------------------------------------
	// ----------------------[ ARB_fragment_shader ]----------------------
	// -------------------------------------------------------------------

	/*
	* Accepted by the <shaderType> argument of CreateShader and
	* returned by the <params> parameter of GetShader{fi}vARB:
	*/
	public static final int GL_FRAGMENT_SHADER = 0x8B30;

	/*
	* Accepted by the <pname> parameter of GetBooleanv, GetIntegerv,
	* GetFloatv, and GetDoublev:
	*/
	public static final int GL_MAX_FRAGMENT_UNIFORM_COMPONENTS = 0x8B49;

	/*
	* Accepted by the <target> parameter of Hint and the <pname> parameter of
	* GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev:
	*/
	public static final int GL_FRAGMENT_SHADER_DERIVATIVE_HINT = 0x8B8B;

	// ----------------------------------------------------------------
	// ----------------------[ ARB_draw_buffers ]----------------------
	// ----------------------------------------------------------------

	/*
	* Accepted by the <pname> parameters of GetIntegerv, GetFloatv,
	* and GetDoublev:
	*/
	public static final int GL_MAX_DRAW_BUFFERS = 0x8824;
	public static final int GL_DRAW_BUFFER0 = 0x8825;
	public static final int GL_DRAW_BUFFER1 = 0x8826;
	public static final int GL_DRAW_BUFFER2 = 0x8827;
	public static final int GL_DRAW_BUFFER3 = 0x8828;
	public static final int GL_DRAW_BUFFER4 = 0x8829;
	public static final int GL_DRAW_BUFFER5 = 0x882A;
	public static final int GL_DRAW_BUFFER6 = 0x882B;
	public static final int GL_DRAW_BUFFER7 = 0x882C;
	public static final int GL_DRAW_BUFFER8 = 0x882D;
	public static final int GL_DRAW_BUFFER9 = 0x882E;
	public static final int GL_DRAW_BUFFER10 = 0x882F;
	public static final int GL_DRAW_BUFFER11 = 0x8830;
	public static final int GL_DRAW_BUFFER12 = 0x8831;
	public static final int GL_DRAW_BUFFER13 = 0x8832;
	public static final int GL_DRAW_BUFFER14 = 0x8833;
	public static final int GL_DRAW_BUFFER15 = 0x8834;

	public void glDrawBuffers(@AutoSize("buffers") @GLsizei int size, @Const @GLenum IntBuffer buffers);

	// ----------------------------------------------------------------
	// ----------------------[ ARB_point_sprite ]----------------------
	// ----------------------------------------------------------------

	/*
	* Accepted by the <cap> parameter of Enable, Disable, and IsEnabled, by
	* the <pname> parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	* GetDoublev, and by the <target> parameter of TexEnvi, TexEnviv,
	* TexEnvf, TexEnvfv, GetTexEnviv, and GetTexEnvfv:
	*/
	public static final int GL_POINT_SPRITE = 0x8861;

	/*
	* When the <target> parameter of TexEnvf, TexEnvfv, TexEnvi, TexEnviv,
	* GetTexEnvfv, or GetTexEnviv is POINT_SPRITE, then the value of
	* <pname> may be:
	*/
	public static final int GL_COORD_REPLACE = 0x8862;

	/*
	 * Accepted by the <pname> parameter of PointParameter{if}vARB, and the
	 * <pname> of Get:
	*/
	public static final int GL_POINT_SPRITE_COORD_ORIGIN = 0x8CA0;

	/*
	* Accepted by the <param> parameter of PointParameter{if}vARB:
	*/
	public static final int GL_LOWER_LEFT = 0x8CA1;
	public static final int GL_UPPER_LEFT = 0x8CA2;

	// -----------------------------------------------------------------
	// ----------------------[ Two-Sided Stencil ]----------------------
	// -----------------------------------------------------------------

	public static final int GL_STENCIL_BACK_FUNC = 0x8800;
	public static final int GL_STENCIL_BACK_FAIL = 0x8801;
	public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL = 0x8802;
	public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS = 0x8803;
	public static final int GL_STENCIL_BACK_REF = 0x8CA3;
	public static final int GL_STENCIL_BACK_VALUE_MASK = 0x8CA4;
	public static final int GL_STENCIL_BACK_WRITEMASK = 0x8CA5;

	public void glStencilOpSeparate(@GLenum int face, @GLenum int sfail, @GLenum int dpfail, @GLenum int dppass);

	public void glStencilFuncSeparate(@GLenum int face, @GLenum int func, int ref, @GLuint int mask);

	public void glStencilMaskSeparate(@GLenum int face, @GLuint int mask);

	// -------------------------------------------------------------
	// ----------------------[ EXT_blend_equation_separate ]----------------------
	// -------------------------------------------------------------

	public static final int GL_BLEND_EQUATION_RGB = 0x8009;
	public static final int GL_BLEND_EQUATION_ALPHA = 0x883D;

	public void glBlendEquationSeparate(@GLenum int modeRGB, @GLenum int modeAlpha);
}
