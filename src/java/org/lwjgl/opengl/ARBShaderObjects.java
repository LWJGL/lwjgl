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
 * Created by LWJGL.
 * User: spasi
 * Date: 2003-12-16
 * Time: 21:58:02
 */

package org.lwjgl.opengl;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public final class ARBShaderObjects {

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
	public static final int GL_FLOAT_VEC2_ARB = 0x8B50;
	public static final int GL_FLOAT_VEC3_ARB = 0x8B51;
	public static final int GL_FLOAT_VEC4_ARB = 0x8B52;
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

	public static native void glDeleteObjectARB(int obj);

	public static native int glGetHandleARB(int pname);

	public static native void glDetachObjectARB(int containerObj, int attachedObj);

	public static native int glCreateShaderObjectARB(int shaderType);

	// ---------------------------
	/**
	 * The ARB_shader_objects extension allows multiple, optionally null-terminated, source strings to define a shader program.
	 * <p/>
	 * This method uses just a single string, that should NOT be null-terminated.
	 *
	 * @param shaderObj
	 * @param string
	 */
	public static void glShaderSourceARB(int shaderObj, ByteBuffer string) {
		initShaderSource(1);
		setShaderString(0, string, string.position(), string.remaining());

		nglShaderSourceARB(shaderObj);
	}

	/**
	 * The ARB_shader_objects extension allows multiple, optionally null-terminated, source strings to define a shader program.
	 * <p/>
	 * This method uses an array of strings, that should NOT be null-terminated.
	 *
	 * @param shaderObj
	 * @param strings
	 */
	public static void glShaderSourceARB(int shaderObj, ByteBuffer[] strings) {
		initShaderSource(strings.length);
		for ( int i = 0; i < strings.length; i++ )
			setShaderString(i, strings[i], strings[i].position(), strings[i].remaining());

		nglShaderSourceARB(shaderObj);
	}

	private static native void initShaderSource(int count);

	private static native void setShaderString(int index, ByteBuffer string, int stringOffset, int stringLength);

	private static native void nglShaderSourceARB(int shaderObj);
	// ---------------------------

	public static native void glCompileShaderARB(int shaderObj);

	public static native int glCreateProgramObjectARB();

	public static native void glAttachObjectARB(int containerObj, int obj);

	public static native void glLinkProgramARB(int programObj);

	public static native void glUseProgramObjectARB(int programObj);

	public static native void glValidateProgramARB(int programObj);

	public static native void glUniform1fARB(int location, float v0);

	public static native void glUniform2fARB(int location, float v0, float v1);

	public static native void glUniform3fARB(int location, float v0, float v1, float v2);

	public static native void glUniform4fARB(int location, float v0, float v1, float v2, float v3);

	public static native void glUniform1iARB(int location, int v0);

	public static native void glUniform2iARB(int location, int v0, int v1);

	public static native void glUniform3iARB(int location, int v0, int v1, int v2);

	public static native void glUniform4iARB(int location, int v0, int v1, int v2, int v3);

	// ---------------------------
	public static void glUniform1ARB(int location, FloatBuffer values) {
		nglUniform1fvARB(location, values.remaining(), values, values.position());
	}

	private static native void nglUniform1fvARB(int location, int count, FloatBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniform2ARB(int location, FloatBuffer values) {
		nglUniform2fvARB(location, values.remaining() >> 1, values, values.position());
	}

	private static native void nglUniform2fvARB(int location, int count, FloatBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniform3ARB(int location, FloatBuffer values) {
		nglUniform3fvARB(location, values.remaining() / 3, values, values.position());
	}

	private static native void nglUniform3fvARB(int location, int count, FloatBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniform4ARB(int location, FloatBuffer values) {
		nglUniform4fvARB(location, values.remaining() >> 2, values, values.position());
	}

	private static native void nglUniform4fvARB(int location, int count, FloatBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniform1ARB(int location, IntBuffer values) {
		nglUniform1ivARB(location, values.remaining(), values, values.position());
	}

	private static native void nglUniform1ivARB(int location, int count, IntBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniform2ARB(int location, IntBuffer values) {
		nglUniform2ivARB(location, values.remaining() >> 1, values, values.position());
	}

	private static native void nglUniform2ivARB(int location, int count, IntBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniform3ARB(int location, IntBuffer values) {
		nglUniform3ivARB(location, values.remaining() / 3, values, values.position());
	}

	private static native void nglUniform3ivARB(int location, int count, IntBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniform4ARB(int location, IntBuffer values) {
		nglUniform4ivARB(location, values.remaining() >> 2, values, values.position());
	}

	private static native void nglUniform4ivARB(int location, int count, IntBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniformMatrix2ARB(int location, boolean transpose, FloatBuffer matrices) {
		nglUniformMatrix2fvARB(location, matrices.remaining() >> 2, transpose, matrices, matrices.position());
	}

	private static native void nglUniformMatrix2fvARB(int location, int count, boolean transpose,
	                                                  FloatBuffer matrices, int matricesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniformMatrix3ARB(int location, boolean transpose, FloatBuffer matrices) {
		nglUniformMatrix3fvARB(location, matrices.remaining() / (3 * 3), transpose, matrices, matrices.position());
	}

	private static native void nglUniformMatrix3fvARB(int location, int count, boolean transpose,
	                                                  FloatBuffer matrices, int matricesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniformMatrix4ARB(int location, boolean transpose, FloatBuffer matrices) {
		nglUniformMatrix4fvARB(location, matrices.remaining() >> 4, transpose, matrices, matrices.position());
	}

	private static native void nglUniformMatrix4fvARB(int location, int count, boolean transpose,
	                                                  FloatBuffer matrices, int matricesOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetObjectParameterARB(int obj, int pname, FloatBuffer params) {
		nglGetObjectParameterfvARB(obj, pname, params, params.position());
	}

	private static native void nglGetObjectParameterfvARB(int obj, int pname, FloatBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetObjectParameterARB(int obj, int pname, IntBuffer params) {
		nglGetObjectParameterivARB(obj, pname, params, params.position());
	}

	private static native void nglGetObjectParameterivARB(int obj, int pname, IntBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetInfoLogARB(int obj, IntBuffer length, ByteBuffer infoLog) {
		if ( length == null )
			nglGetInfoLogARB(obj, infoLog.remaining(), null, -1, infoLog, infoLog.position());
		else {
			if ( length.remaining() == 0 )
				throw new BufferOverflowException();

			nglGetInfoLogARB(obj, infoLog.remaining(), length, length.position(), infoLog, infoLog.position());
		}
	}

	private static native void nglGetInfoLogARB(int obj, int maxLength,
	                                            IntBuffer length, int lengthOffset,
	                                            ByteBuffer infoLog, int infoLogOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetAttachedObjectsARB(int containerObj, IntBuffer count, IntBuffer obj) {
		if ( count == null )
			nglGetAttachedObjectsARB(containerObj, obj.remaining(), null, -1, obj, obj.position());
		else {
			if ( count.remaining() == 0 )
				throw new BufferOverflowException();

			nglGetAttachedObjectsARB(containerObj, obj.remaining(), count, count.position(), obj, obj.position());
		}
	}

	private static native void nglGetAttachedObjectsARB(int containerObj, int maxCount,
	                                                    IntBuffer count, int countOffset, IntBuffer obj, int objOffset);
	// ---------------------------

	// ---------------------------
	/**
	 * Returns the location of the uniform with the specified name. The ByteBuffer should contain the uniform name as a
	 * <b>null-terminated</b> string.
	 *
	 * @param programObj
	 * @param name
	 *
	 * @return
	 */
	public static int glGetUniformLocationARB(int programObj, ByteBuffer name) {
		// TODO: How do we check that the string is null-terminated?
		return nglGetUniformLocationARB(programObj, name, name.position());
	}

	private static native int nglGetUniformLocationARB(int programObj, ByteBuffer name, int nameOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetActiveUniformARB(int programObj, int index,
	                                         IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		if ( size.remaining() == 0 )
			throw new BufferOverflowException();

		if ( type.remaining() == 0 )
			throw new BufferOverflowException();

		if ( length == null )
			nglGetActiveUniformARB(programObj, index, name.remaining(), null, -1,
			                       size, size.position(), type, type.position(), name, name.position());
		else {
			if ( length.remaining() == 0 )
				throw new BufferOverflowException();

			nglGetActiveUniformARB(programObj, index, name.remaining(), length, length.position(),
			                       size, size.position(), type, type.position(), name, name.position());
		}
	}

	private static native void nglGetActiveUniformARB(int programObj, int index, int maxLength,
	                                                  IntBuffer length, int lengthOffset,
	                                                  IntBuffer size, int sizeOffset,
	                                                  IntBuffer type, int typeOffset,
	                                                  ByteBuffer name, int nameOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetUniformARB(int programObj, int location, FloatBuffer params) {
		nglGetUniformfvARB(programObj, location, params, params.position());
	}

	private static native void nglGetUniformfvARB(int programObj, int location, FloatBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetUniformARB(int programObj, int location, IntBuffer params) {
		nglGetUniformivARB(programObj, location, params, params.position());
	}

	private static native void nglGetUniformivARB(int programObj, int location, IntBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetShaderSourceARB(int obj, IntBuffer length, ByteBuffer source) {
		if ( length == null )
			nglGetShaderSourceARB(obj, source.remaining(), null, -1, source, source.position());
		else {
			nglGetShaderSourceARB(obj, source.remaining(), length, length.position(), source, source.position());
		}
	}

	private static native void nglGetShaderSourceARB(int obj, int maxLength,
	                                                 IntBuffer length, int lengthOffset, ByteBuffer source, int sourceOffset);
	// ---------------------------

}