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

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.generator.*;

public interface ARB_shader_objects {
	/*
	* Accepted by the <pname> argument of GetHandleARB:
	*/
	public static final int GL_PROGRAM_OBJECT_ARB = 0x8B40;

	/*
	* Accepted by the <pname> parameter of GetObjectParameter{fi}vARB:
	*/
	public static final int GL_OBJECT_TYPE_ARB = 0x8B4E;
	public static final int GL_OBJECT_SUBTYPE_ARB = 0x8B4F;
	public static final int GL_OBJECT_DELETE_STATUS_ARB = 0x8B80;
	public static final int GL_OBJECT_COMPILE_STATUS_ARB = 0x8B81;
	public static final int GL_OBJECT_LINK_STATUS_ARB = 0x8B82;
	public static final int GL_OBJECT_VALIDATE_STATUS_ARB = 0x8B83;
	public static final int GL_OBJECT_INFO_LOG_LENGTH_ARB = 0x8B84;
	public static final int GL_OBJECT_ATTACHED_OBJECTS_ARB = 0x8B85;
	public static final int GL_OBJECT_ACTIVE_UNIFORMS_ARB = 0x8B86;
	public static final int GL_OBJECT_ACTIVE_UNIFORM_MAX_LENGTH_ARB = 0x8B87;
	public static final int GL_OBJECT_SHADER_SOURCE_LENGTH_ARB = 0x8B88;

	/*
	* Returned by the <params> parameter of GetObjectParameter{fi}vARB:
	*/
	public static final int GL_SHADER_OBJECT_ARB = 0x8B48;

	/*
	* Returned by the <type> parameter of GetActiveUniformARB:
	*/
	public static final int GL_FLOAT = 0x1406;
	public static final int GL_FLOAT_VEC2_ARB = 0x8B50;
	public static final int GL_FLOAT_VEC3_ARB = 0x8B51;
	public static final int GL_FLOAT_VEC4_ARB = 0x8B52;
	public static final int GL_INT = 0x1404;
	public static final int GL_INT_VEC2_ARB = 0x8B53;
	public static final int GL_INT_VEC3_ARB = 0x8B54;
	public static final int GL_INT_VEC4_ARB = 0x8B55;
	public static final int GL_BOOL_ARB = 0x8B56;
	public static final int GL_BOOL_VEC2_ARB = 0x8B57;
	public static final int GL_BOOL_VEC3_ARB = 0x8B58;
	public static final int GL_BOOL_VEC4_ARB = 0x8B59;
	public static final int GL_FLOAT_MAT2_ARB = 0x8B5A;
	public static final int GL_FLOAT_MAT3_ARB = 0x8B5B;
	public static final int GL_FLOAT_MAT4_ARB = 0x8B5C;
	public static final int GL_SAMPLER_1D_ARB = 0x8B5D;
	public static final int GL_SAMPLER_2D_ARB = 0x8B5E;
	public static final int GL_SAMPLER_3D_ARB = 0x8B5F;
	public static final int GL_SAMPLER_CUBE_ARB = 0x8B60;
	public static final int GL_SAMPLER_1D_SHADOW_ARB = 0x8B61;
	public static final int GL_SAMPLER_2D_SHADOW_ARB = 0x8B62;
	public static final int GL_SAMPLER_2D_RECT_ARB = 0x8B63;
	public static final int GL_SAMPLER_2D_RECT_SHADOW_ARB = 0x8B64;

	public void glDeleteObjectARB(@GLhandleARB int obj);

	public @GLhandleARB int glGetHandleARB(@GLenum int pname);

	public void glDetachObjectARB(@GLhandleARB int containerObj, @GLhandleARB int attachedObj);

	public @GLhandleARB int glCreateShaderObjectARB(@GLenum int shaderType);

	/**
	 * The ARB_shader_objects extension allows multiple, optionally null-terminated, source strings to define a shader program.
	 * <p/>
	 * This method uses just a single string, that should NOT be null-terminated.
	 *
	 * @param shaderObj
	 * @param string
	 */
	public void glShaderSourceARB(@GLhandleARB int shader, @Constant("1") @GLsizei int count,
			@Indirect
			@Check
			@Const
			@GLcharARB
			ByteBuffer string,
			@AutoSize("string")
			@Indirect
			@Const
			@GLint
			int length);

	public void glCompileShaderARB(@GLhandleARB int shaderObj);

	public @GLhandleARB int glCreateProgramObjectARB();

	public void glAttachObjectARB(@GLhandleARB int containerObj, @GLhandleARB int obj);

	public void glLinkProgramARB(@GLhandleARB int programObj);

	public void glUseProgramObjectARB(@GLhandleARB int programObj);

	public void glValidateProgramARB(@GLhandleARB int programObj);

	public void glUniform1fARB(int location, float v0);

	public void glUniform2fARB(int location, float v0, float v1);

	public void glUniform3fARB(int location, float v0, float v1, float v2);

	public void glUniform4fARB(int location, float v0, float v1, float v2, float v3);

	public void glUniform1iARB(int location, int v0);

	public void glUniform2iARB(int location, int v0, int v1);

	public void glUniform3iARB(int location, int v0, int v1, int v2);

	public void glUniform4iARB(int location, int v0, int v1, int v2, int v3);

	@StripPostfix("values")
	public void glUniform1fvARB(int location, @AutoSize("values") @GLsizei int count, FloatBuffer values);
	@StripPostfix("values")
	public void glUniform2fvARB(int location, @AutoSize(value="values", expression=" >> 1") @GLsizei int count, FloatBuffer values);
	@StripPostfix("values")
	public void glUniform3fvARB(int location, @AutoSize(value="values", expression=" / 3") @GLsizei int count, FloatBuffer values);
	@StripPostfix("values")
	public void glUniform4fvARB(int location, @AutoSize(value="values", expression=" >> 2") @GLsizei int count, FloatBuffer values);

	@StripPostfix("values")
	public void glUniform1ivARB(int location, @AutoSize(value="values") @GLsizei int count, IntBuffer values);
	@StripPostfix("values")
	public void glUniform2ivARB(int location, @AutoSize(value="values", expression=" >> 1") @GLsizei int count, IntBuffer values);
	@StripPostfix("values")
	public void glUniform3ivARB(int location, @AutoSize(value="values", expression=" / 3") @GLsizei int count, IntBuffer values);
	@StripPostfix("values")
	public void glUniform4ivARB(int location, @AutoSize(value="values", expression=" >> 2") @GLsizei int count, IntBuffer values);

	@StripPostfix("matrices")
	public void glUniformMatrix2fvARB(int location, @AutoSize(value="matrices", expression=" >> 2") @GLsizei int count, boolean transpose, FloatBuffer matrices);
	@StripPostfix("matrices")
	public void glUniformMatrix3fvARB(int location, @AutoSize(value="matrices", expression=" / (3 * 3)") @GLsizei int count, boolean transpose, FloatBuffer matrices);
	@StripPostfix("matrices")
	public void glUniformMatrix4fvARB(int location, @AutoSize(value="matrices", expression=" >> 4") @GLsizei int count, boolean transpose, FloatBuffer matrices);

	@StripPostfix("params")
	public void glGetObjectParameterfvARB(@GLhandleARB int obj, @GLenum int pname, @Check FloatBuffer params);
	@StripPostfix("params")
	public void glGetObjectParameterivARB(@GLhandleARB int obj, @GLenum int pname, @Check IntBuffer params);

	public void glGetInfoLogARB(@GLhandleARB int obj, @AutoSize("infoLog") @GLsizei int maxLength,
			@Check(value="1", canBeNull=true)
			@GLsizei IntBuffer length,
			@GLcharARB
			ByteBuffer infoLog);

	public void glGetAttachedObjectsARB(@GLhandleARB int containerObj, @AutoSize("obj") @GLsizei int maxCount,
			@Check(value="1", canBeNull=true)
			@GLsizei
			IntBuffer count,
			@GLhandleARB
			IntBuffer obj);

	/**
	 * Returns the location of the uniform with the specified name. The ByteBuffer should contain the uniform name as a <b>null-terminated</b> string.
	 *
	 * @param programObj
	 * @param name
	 *
	 * @return
	 */
	public int glGetUniformLocationARB(@GLhandleARB int programObj, @NullTerminated @Const @GLcharARB ByteBuffer name);

	public void glGetActiveUniformARB(@GLhandleARB int programObj, @GLuint int index, @AutoSize("name") @GLsizei int maxLength,
			@Check(value="1", canBeNull=true)
			@GLsizei
			IntBuffer length,
			@Check("1")
			IntBuffer size,
			@Check("1")
			@GLenum
			IntBuffer type,
			@GLcharARB
			ByteBuffer name);

	@StripPostfix("params")
	public void glGetUniformfvARB(@GLhandleARB int programObj, int location, @Check FloatBuffer params);
	@StripPostfix("params")
	public void glGetUniformivARB(@GLhandleARB int programObj, int location, @Check IntBuffer params);

	public void glGetShaderSourceARB(@GLhandleARB int obj, @AutoSize("source") @GLsizei int maxLength,
			@Check(value="1", canBeNull=true)
			@GLsizei
			IntBuffer length,
			@GLcharARB
			ByteBuffer source);
}
