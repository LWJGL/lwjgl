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

import org.lwjgl.BufferChecks;
import org.lwjgl.LWJGLException;

import java.nio.*;

public final class GL20 {

	private GL20() {
	}

	static native void initNativeStubs() throws LWJGLException;

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

	// ---------------------------
	/**
	 * The ARB_shader_objects extension allows multiple, optionally null-terminated, source strings to define a shader program.
	 * <p/>
	 * This method uses just a single string, that should NOT be null-terminated.
	 *
	 * @param shader
	 * @param string
	 */
	public static void glShaderSource(int shader, ByteBuffer string) {
		BufferChecks.checkDirect(string);

		initShaderSource(1);
		setShaderString(0, string, string.position(), string.remaining());

		nglShaderSource(shader);
	}

	/**
	 * The ARB_shader_objects extension allows multiple, optionally null-terminated, source strings to define a shader program.
	 * <p/>
	 * This method uses an array of strings, that should NOT be null-terminated.
	 *
	 * @param shader
	 * @param strings
	 */
	public static void glShaderSource(int shader, ByteBuffer[] strings) {
		if ( strings == null || strings.length == 0 )
			throw new IllegalArgumentException("Invalid shader string array.");

		initShaderSource(strings.length);
		for ( int i = 0; i < strings.length; i++ ) {
			BufferChecks.checkDirect(strings[i]);
			setShaderString(i, strings[i], strings[i].position(), strings[i].remaining());
		}

		nglShaderSource(shader);
	}

	private static native void initShaderSource(int count);

	private static native void setShaderString(int index, ByteBuffer string, int stringOffset, int stringLength);

	private static native void nglShaderSource(int shader);
	// ---------------------------

	public static native int glCreateShader(int type);

	public static native boolean glIsShader(int shader);

	public static native void glCompileShader(int shader);

	public static native void glDeleteShader(int shader);

	public static native int glCreateProgram();

	public static native boolean glIsProgram(int program);

	public static native void glAttachShader(int program, int shader);

	public static native void glDetachShader(int program, int shader);

	public static native void glLinkProgram(int program);

	public static native void glUseProgram(int program);

	public static native void glValidateProgram(int program);

	public static native void glDeleteProgram(int program);

	public static native void glUniform1f(int location, float v0);

	public static native void glUniform2f(int location, float v0, float v1);

	public static native void glUniform3f(int location, float v0, float v1, float v2);

	public static native void glUniform4f(int location, float v0, float v1, float v2, float v3);

	public static native void glUniform1i(int location, int v0);

	public static native void glUniform2i(int location, int v0, int v1);

	public static native void glUniform3i(int location, int v0, int v1, int v2);

	public static native void glUniform4i(int location, int v0, int v1, int v2, int v3);

	// ---------------------------
	public static void glUniform1(int location, FloatBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform1fv(location, values.remaining(), values, values.position());
	}

	private static native void nglUniform1fv(int location, int count, FloatBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniform2(int location, FloatBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform2fv(location, values.remaining() >> 1, values, values.position());
	}

	private static native void nglUniform2fv(int location, int count, FloatBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniform3(int location, FloatBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform3fv(location, values.remaining() / 3, values, values.position());
	}

	private static native void nglUniform3fv(int location, int count, FloatBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniform4(int location, FloatBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform4fv(location, values.remaining() >> 2, values, values.position());
	}

	private static native void nglUniform4fv(int location, int count, FloatBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniform1(int location, IntBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform1iv(location, values.remaining(), values, values.position());
	}

	private static native void nglUniform1iv(int location, int count, IntBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniform2(int location, IntBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform2iv(location, values.remaining() >> 1, values, values.position());
	}

	private static native void nglUniform2iv(int location, int count, IntBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniform3(int location, IntBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform3iv(location, values.remaining() / 3, values, values.position());
	}

	private static native void nglUniform3iv(int location, int count, IntBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniform4(int location, IntBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform4iv(location, values.remaining() >> 2, values, values.position());
	}

	private static native void nglUniform4iv(int location, int count, IntBuffer values, int valuesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniformMatrix2(int location, boolean transpose, FloatBuffer matrices) {
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix2fv(location, matrices.remaining() >> 2, transpose, matrices, matrices.position());
	}

	private static native void nglUniformMatrix2fv(int location, int count, boolean transpose,
	                                               FloatBuffer matrices, int matricesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniformMatrix3(int location, boolean transpose, FloatBuffer matrices) {
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix3fv(location, matrices.remaining() / (3 * 3), transpose, matrices, matrices.position());
	}

	private static native void nglUniformMatrix3fv(int location, int count, boolean transpose,
	                                               FloatBuffer matrices, int matricesOffset);
	// ---------------------------

	// ---------------------------
	public static void glUniformMatrix4(int location, boolean transpose, FloatBuffer matrices) {
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix4fv(location, matrices.remaining() >> 4, transpose, matrices, matrices.position());
	}

	private static native void nglUniformMatrix4fv(int location, int count, boolean transpose,
	                                               FloatBuffer matrices, int matricesOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetShader(int shader, int pname, FloatBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetShaderfv(shader, pname, params, params.position());
	}

	private static native void nglGetShaderfv(int shader, int pname, FloatBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetShader(int shader, int pname, IntBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetShaderiv(shader, pname, params, params.position());
	}

	private static native void nglGetShaderiv(int shader, int pname, IntBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetProgram(int program, int pname, FloatBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetProgramfv(program, pname, params, params.position());
	}

	private static native void nglGetProgramfv(int program, int pname, FloatBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetProgram(int program, int pname, IntBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetProgramiv(program, pname, params, params.position());
	}

	private static native void nglGetProgramiv(int program, int pname, IntBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetShaderInfoLog(int shader, IntBuffer length, ByteBuffer infoLog) {
		BufferChecks.checkDirect(infoLog);
		if ( length == null ) {
			nglGetShaderInfoLog(shader, infoLog.remaining(), null, -1, infoLog, infoLog.position());
		} else {
			BufferChecks.checkBuffer(length, 1);
			nglGetShaderInfoLog(shader, infoLog.remaining(), length, length.position(), infoLog, infoLog.position());
		}
	}

	private static native void nglGetShaderInfoLog(int shader, int maxLength,
	                                               IntBuffer length, int lengthOffset,
	                                               ByteBuffer infoLog, int infoLogOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetProgramInfoLog(int program, IntBuffer length, ByteBuffer infoLog) {
		BufferChecks.checkDirect(infoLog);
		if ( length == null ) {
			nglGetProgramInfoLog(program, infoLog.remaining(), null, -1, infoLog, infoLog.position());
		} else {
			BufferChecks.checkBuffer(length, 1);
			nglGetProgramInfoLog(program, infoLog.remaining(), length, length.position(), infoLog, infoLog.position());
		}
	}

	private static native void nglGetProgramInfoLog(int program, int maxLength,
	                                                IntBuffer length, int lengthOffset,
	                                                ByteBuffer infoLog, int infoLogOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetAttachedShaders(int program, IntBuffer count, IntBuffer shaders) {
		if ( count == null )
			nglGetAttachedShaders(program, shaders.remaining(), null, -1, shaders, shaders.position());
		else {
			if ( count.remaining() == 0 )
				throw new BufferOverflowException();

			nglGetAttachedShaders(program, shaders.remaining(), count, count.position(), shaders, shaders.position());
		}
	}

	private static native void nglGetAttachedShaders(int program, int maxCount,
	                                                 IntBuffer count, int countOffset, IntBuffer shaders, int shadersOffset);
	// ---------------------------

	// ---------------------------
	/**
	 * Returns the location of the uniform with the specified name. The ByteBuffer should contain the uniform name as a
	 * <b>null-terminated</b> string.
	 *
	 * @param program
	 * @param name
	 *
	 * @return
	 */
	public static int glGetUniformLocation(int program, ByteBuffer name) {
		// TODO: How do we check that the string is null-terminated?
		return nglGetUniformLocation(program, name, name.position());
	}

	private static native int nglGetUniformLocation(int program, ByteBuffer name, int nameOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetActiveUniform(int program, int index,
	                                      IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		if ( size.remaining() == 0 )
			throw new BufferOverflowException();

		if ( type.remaining() == 0 )
			throw new BufferOverflowException();

		if ( length == null )
			nglGetActiveUniform(program, index, name.remaining(), null, -1,
			                    size, size.position(), type, type.position(), name, name.position());
		else {
			if ( length.remaining() == 0 )
				throw new BufferOverflowException();

			nglGetActiveUniform(program, index, name.remaining(), length, length.position(),
			                    size, size.position(), type, type.position(), name, name.position());
		}
	}

	private static native void nglGetActiveUniform(int program, int index, int maxLength,
	                                               IntBuffer length, int lengthOffset,
	                                               IntBuffer size, int sizeOffset,
	                                               IntBuffer type, int typeOffset,
	                                               ByteBuffer name, int nameOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetUniform(int program, int location, FloatBuffer params) {
		nglGetUniformfv(program, location, params, params.position());
	}

	private static native void nglGetUniformfv(int program, int location, FloatBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetUniform(int program, int location, IntBuffer params) {
		nglGetUniformiv(program, location, params, params.position());
	}

	private static native void nglGetUniformiv(int program, int location, IntBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetShaderSource(int shader, IntBuffer length, ByteBuffer source) {
		if ( length == null )
			nglGetShaderSource(shader, source.remaining(), null, -1, source, source.position());
		else {
			nglGetShaderSource(shader, source.remaining(), length, length.position(), source, source.position());
		}
	}

	private static native void nglGetShaderSource(int shader, int maxLength,
	                                              IntBuffer length, int lengthOffset, ByteBuffer source, int sourceOffset);
	// ---------------------------

	// ------------------------------------------------------------------
	// ----------------------[ ARB_vertex_program ]----------------------
	// ------------------------------------------------------------------

	public static native void glVertexAttrib1s(int index, short x);

	public static native void glVertexAttrib1f(int index, float x);

	public static native void glVertexAttrib2s(int index, short x, short y);

	public static native void glVertexAttrib2f(int index, float x, float y);

	public static native void glVertexAttrib3s(int index, short x, short y, short z);

	public static native void glVertexAttrib3f(int index, float x, float y, float z);

	public static native void glVertexAttrib4s(int index, short x, short y, short z, short w);

	public static native void glVertexAttrib4f(int index, float x, float y, float z, float w);

	public static native void glVertexAttrib4Nub(int index, byte x, byte y, byte z, byte w);

	public static void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		nglVertexAttribPointer(index, size, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, normalized, stride, buffer, buffer.position());
	}

	public static void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		nglVertexAttribPointer(index, size, unsigned ? GL11.GL_UNSIGNED_SHORT : GL11.GL_SHORT, normalized, stride, buffer, buffer.position() << 1);
	}

	public static void glVertexAttribPointer(int index, int size, boolean normalized, int stride, FloatBuffer buffer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		nglVertexAttribPointer(index, size, GL11.GL_FLOAT, normalized, stride, buffer, buffer.position() << 2);
	}

	public static void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		nglVertexAttribPointer(index, size, unsigned ? GL11.GL_UNSIGNED_INT : GL11.GL_INT, normalized, stride, buffer, buffer.position() << 2);
	}

	private static native void nglVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, Buffer buffer, int bufferOffset);

	// ---------------------------
	public static void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, int bufferOffset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglVertexAttribPointerVBO(index, size, type, normalized, stride, bufferOffset);
	}

	private static native void nglVertexAttribPointerVBO(int index, int size, int type, boolean normalized, int stride, int bufferOffset);
	// ---------------------------

	public static native void glEnableVertexAttribArray(int index);

	public static native void glDisableVertexAttribArray(int index);

	// ---------------------------
	public static void glGetVertexAttrib(int index, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetVertexAttribfv(index, pname, params, params.position());
	}

	private static native void nglGetVertexAttribfv(int index, int pname, FloatBuffer params, int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetVertexAttrib(int index, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetVertexAttribiv(index, pname, params, params.position());
	}

	private static native void nglGetVertexAttribiv(int index, int pname, IntBuffer params, int paramsOffset);
	// ---------------------------

	public static native ByteBuffer glGetVertexAttribPointer(int index, int pname, int size);

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

	// ---------------------------
	public static void glBindAttribLocation(int program, int index, ByteBuffer name) {
		BufferChecks.checkDirect(name);
		if ( name.get(name.limit() - 1) != 0 ) {
			throw new IllegalArgumentException("<name> must be a null-terminated string.");
		}
		nglBindAttribLocation(program, index, name, name.position());
	}

	private static native void nglBindAttribLocation(int program, int index, ByteBuffer name, int nameOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetActiveAttrib(int program, int index,
	                                     IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		BufferChecks.checkDirect(name);
		BufferChecks.checkDirect(size);
		BufferChecks.checkDirect(type);

		if ( length == null ) {
			nglGetActiveAttrib(program, index, name.remaining(), null, -1,
			                   size, size.position(), type, type.position(), name, name.position());
		} else {
			BufferChecks.checkDirect(length);
			nglGetActiveAttrib(program, index, name.remaining(), length, length.position(),
			                   size, size.position(), type, type.position(), name, name.position());
		}
	}

	private static native void nglGetActiveAttrib(int program, int index, int maxLength,
	                                              IntBuffer length, int lengthOffset,
	                                              IntBuffer size, int sizeOffset,
	                                              IntBuffer type, int typeOffset,
	                                              ByteBuffer name, int nameOffset);
	// ---------------------------

	// ---------------------------
	public static int glGetAttribLocation(int program, ByteBuffer name) {
		BufferChecks.checkDirect(name);
		if ( name.get(name.limit() - 1) != 0 ) {
			throw new IllegalArgumentException("<name> must be a null-terminated string.");
		}
		return nglGetAttribLocation(program, name, name.position());
	}

	private static native int nglGetAttribLocation(int program, ByteBuffer name, int nameOffset);
	// ---------------------------

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

	// ---------------------------
	public static void glDrawBuffers(IntBuffer buffers) {
		BufferChecks.checkBuffer(buffers, 1);
		nglDrawBuffers(buffers.remaining(), buffers, buffers.position());
	}

	private static native void nglDrawBuffers(int size, IntBuffer buffers, int buffersOffset);
	// ---------------------------

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

	public static native void glStencilOpSeparate(int face, int sfail, int dpfail, int dppass);

	public static native void glStencilFuncSeparate(int face, int func, int ref, int mask);

	public static native void glStencilMaskSeparate(int face, int mask);

	// -------------------------------------------------------------
	// ----------------------[ EXT_blend_equation_separate ]----------------------
	// -------------------------------------------------------------

	public static final int GL_BLEND_EQUATION_RGB = 0x8009;
	public static final int GL_BLEND_EQUATION_ALPHA = 0x883D;

	public static native void glBlendEquationSeparate(int modeRGB, int modeAlpha);

}