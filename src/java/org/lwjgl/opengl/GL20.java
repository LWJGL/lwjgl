/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class GL20 {
	public static final int GL_BLEND_EQUATION_ALPHA = 0x883d;
	public static final int GL_BLEND_EQUATION_RGB = 0x8009;
	public static final int GL_STENCIL_BACK_WRITEMASK = 0x8ca5;
	public static final int GL_STENCIL_BACK_VALUE_MASK = 0x8ca4;
	public static final int GL_STENCIL_BACK_REF = 0x8ca3;
	public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS = 0x8803;
	public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL = 0x8802;
	public static final int GL_STENCIL_BACK_FAIL = 0x8801;
	public static final int GL_STENCIL_BACK_FUNC = 0x8800;
	public static final int GL_UPPER_LEFT = 0x8ca2;
	public static final int GL_LOWER_LEFT = 0x8ca1;
	public static final int GL_POINT_SPRITE_COORD_ORIGIN = 0x8ca0;
	public static final int GL_COORD_REPLACE = 0x8862;
	public static final int GL_POINT_SPRITE = 0x8861;
	public static final int GL_DRAW_BUFFER15 = 0x8834;
	public static final int GL_DRAW_BUFFER14 = 0x8833;
	public static final int GL_DRAW_BUFFER13 = 0x8832;
	public static final int GL_DRAW_BUFFER12 = 0x8831;
	public static final int GL_DRAW_BUFFER11 = 0x8830;
	public static final int GL_DRAW_BUFFER10 = 0x882f;
	public static final int GL_DRAW_BUFFER9 = 0x882e;
	public static final int GL_DRAW_BUFFER8 = 0x882d;
	public static final int GL_DRAW_BUFFER7 = 0x882c;
	public static final int GL_DRAW_BUFFER6 = 0x882b;
	public static final int GL_DRAW_BUFFER5 = 0x882a;
	public static final int GL_DRAW_BUFFER4 = 0x8829;
	public static final int GL_DRAW_BUFFER3 = 0x8828;
	public static final int GL_DRAW_BUFFER2 = 0x8827;
	public static final int GL_DRAW_BUFFER1 = 0x8826;
	public static final int GL_DRAW_BUFFER0 = 0x8825;
	public static final int GL_MAX_DRAW_BUFFERS = 0x8824;
	public static final int GL_FRAGMENT_SHADER_DERIVATIVE_HINT = 0x8b8b;
	public static final int GL_MAX_FRAGMENT_UNIFORM_COMPONENTS = 0x8b49;
	public static final int GL_FRAGMENT_SHADER = 0x8b30;
	public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER = 0x8645;
	public static final int GL_CURRENT_VERTEX_ATTRIB = 0x8626;
	public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED = 0x886a;
	public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE = 0x8625;
	public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE = 0x8624;
	public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE = 0x8623;
	public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED = 0x8622;
	public static final int GL_VERTEX_PROGRAM_TWO_SIDE = 0x8643;
	public static final int GL_VERTEX_PROGRAM_POINT_SIZE = 0x8642;
	public static final int GL_MAX_TEXTURE_COORDS = 0x8871;
	public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = 0x8b4d;
	public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS = 0x8b4c;
	public static final int GL_MAX_TEXTURE_IMAGE_UNITS = 0x8872;
	public static final int GL_MAX_VERTEX_ATTRIBS = 0x8869;
	public static final int GL_MAX_VARYING_FLOATS = 0x8b4b;
	public static final int GL_MAX_VERTEX_UNIFORM_COMPONENTS = 0x8b4a;
	public static final int GL_VERTEX_SHADER = 0x8b31;
	public static final int GL_SAMPLER_2D_SHADOW = 0x8b62;
	public static final int GL_SAMPLER_1D_SHADOW = 0x8b61;
	public static final int GL_SAMPLER_CUBE = 0x8b60;
	public static final int GL_SAMPLER_3D = 0x8b5f;
	public static final int GL_SAMPLER_2D = 0x8b5e;
	public static final int GL_SAMPLER_1D = 0x8b5d;
	public static final int GL_FLOAT_MAT4 = 0x8b5c;
	public static final int GL_FLOAT_MAT3 = 0x8b5b;
	public static final int GL_FLOAT_MAT2 = 0x8b5a;
	public static final int GL_BOOL_VEC4 = 0x8b59;
	public static final int GL_BOOL_VEC3 = 0x8b58;
	public static final int GL_BOOL_VEC2 = 0x8b57;
	public static final int GL_BOOL = 0x8b56;
	public static final int GL_INT_VEC4 = 0x8b55;
	public static final int GL_INT_VEC3 = 0x8b54;
	public static final int GL_INT_VEC2 = 0x8b53;
	public static final int GL_FLOAT_VEC4 = 0x8b52;
	public static final int GL_FLOAT_VEC3 = 0x8b51;
	public static final int GL_FLOAT_VEC2 = 0x8b50;
	public static final int GL_SHADER_OBJECT = 0x8b48;
	public static final int GL_SHADER_SOURCE_LENGTH = 0x8b88;
	public static final int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 0x8b8a;
	public static final int GL_ACTIVE_ATTRIBUTES = 0x8b89;
	public static final int GL_ACTIVE_UNIFORM_MAX_LENGTH = 0x8b87;
	public static final int GL_ACTIVE_UNIFORMS = 0x8b86;
	public static final int GL_ATTACHED_SHADERS = 0x8b85;
	public static final int GL_INFO_LOG_LENGTH = 0x8b84;
	public static final int GL_VALIDATE_STATUS = 0x8b83;
	public static final int GL_LINK_STATUS = 0x8b82;
	public static final int GL_COMPILE_STATUS = 0x8b81;
	public static final int GL_DELETE_STATUS = 0x8b80;
	public static final int GL_SHADER_TYPE = 0x8b4f;
	public static final int GL_CURRENT_PROGRAM = 0x8b8d;
	public static final int GL_SHADING_LANGUAGE_VERSION = 0x8b8c;

	private GL20() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glBlendEquationSeparate(int modeRGB, int modeAlpha);

	public static native void glStencilMaskSeparate(int face, int mask);

	public static native void glStencilFuncSeparate(int face, int func, int ref, int mask);

	public static native void glStencilOpSeparate(int face, int sfail, int dpfail, int dppass);

	public static void glDrawBuffers(IntBuffer buffers) {
		BufferChecks.checkDirect(buffers);
		nglDrawBuffers((buffers.remaining()), buffers, buffers.position());
	}
	private static native void nglDrawBuffers(int size, IntBuffer buffers, int buffers_position);

	public static int glGetAttribLocation(int program, ByteBuffer name) {
		BufferChecks.checkDirect(name);
		BufferChecks.checkNullTerminated(name);
		int __result = nglGetAttribLocation(program, name, name.position());
		return __result;
	}
	private static native int nglGetAttribLocation(int program, ByteBuffer name, int name_position);

	public static void glGetActiveAttrib(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkBuffer(size, 1);
		BufferChecks.checkBuffer(type, 1);
		BufferChecks.checkDirect(name);
		nglGetActiveAttrib(program, index, (name.remaining()), length, length != null ? length.position() : 0, size, size.position(), type, type.position(), name, name.position());
	}
	private static native void nglGetActiveAttrib(int program, int index, int maxLength, IntBuffer length, int length_position, IntBuffer size, int size_position, IntBuffer type, int type_position, ByteBuffer name, int name_position);

	public static void glBindAttribLocation(int program, int index, ByteBuffer name) {
		BufferChecks.checkDirect(name);
		BufferChecks.checkNullTerminated(name);
		nglBindAttribLocation(program, index, name, name.position());
	}
	private static native void nglBindAttribLocation(int program, int index, ByteBuffer name, int name_position);

	public static java.nio.ByteBuffer glGetVertexAttribPointer(int index, int pname, int result_size) {
		java.nio.ByteBuffer __result = nglGetVertexAttribPointerv(index, pname, result_size);
		return __result;
	}
	private static native java.nio.ByteBuffer nglGetVertexAttribPointerv(int index, int pname, int result_size);

	public static void glGetVertexAttrib(int index, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetVertexAttribiv(index, pname, params, params.position());
	}
	private static native void nglGetVertexAttribiv(int index, int pname, IntBuffer params, int params_position);

	public static void glGetVertexAttrib(int index, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetVertexAttribfv(index, pname, params, params.position());
	}
	private static native void nglGetVertexAttribfv(int index, int pname, FloatBuffer params, int params_position);

	public static native void glDisableVertexAttribArray(int index);

	public static native void glEnableVertexAttribArray(int index);

	public static void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(buffer);
		nglVertexAttribPointer(index, size, unsigned ? GL11.GL_UNSIGNED_INT : GL11.GL_INT, normalized, stride, buffer, buffer.position() << 2);
	}
	public static void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(buffer);
		nglVertexAttribPointer(index, size, unsigned ? GL11.GL_UNSIGNED_SHORT : GL11.GL_SHORT, normalized, stride, buffer, buffer.position() << 1);
	}
	public static void glVertexAttribPointer(int index, int size, boolean normalized, int stride, FloatBuffer buffer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(buffer);
		nglVertexAttribPointer(index, size, GL11.GL_FLOAT, normalized, stride, buffer, buffer.position() << 2);
	}
	public static void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(buffer);
		nglVertexAttribPointer(index, size, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, normalized, stride, buffer, buffer.position());
	}
	private static native void nglVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, Buffer buffer, int buffer_position);
	public static void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, int buffer_buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglVertexAttribPointerBO(index, size, type, normalized, stride, buffer_buffer_offset);
	}
	private static native void nglVertexAttribPointerBO(int index, int size, int type, boolean normalized, int stride, int buffer_buffer_offset);

	public static native void glVertexAttrib4Nub(int index, byte x, byte y, byte z, byte w);

	public static native void glVertexAttrib4f(int index, float x, float y, float z, float w);

	public static native void glVertexAttrib4s(int index, short x, short y, short z, short w);

	public static native void glVertexAttrib3f(int index, float x, float y, float z);

	public static native void glVertexAttrib3s(int index, short x, short y, short z);

	public static native void glVertexAttrib2f(int index, float x, float y);

	public static native void glVertexAttrib2s(int index, short x, short y);

	public static native void glVertexAttrib1f(int index, float x);

	public static native void glVertexAttrib1s(int index, short x);

	public static void glGetShaderSource(int shader, IntBuffer length, ByteBuffer source) {
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkDirect(source);
		nglGetShaderSource(shader, (source.remaining()), length, length != null ? length.position() : 0, source, source.position());
	}
	private static native void nglGetShaderSource(int shader, int maxLength, IntBuffer length, int length_position, ByteBuffer source, int source_position);

	public static void glGetUniform(int program, int location, IntBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetUniformiv(program, location, params, params.position());
	}
	private static native void nglGetUniformiv(int program, int location, IntBuffer params, int params_position);

	public static void glGetUniform(int program, int location, FloatBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetUniformfv(program, location, params, params.position());
	}
	private static native void nglGetUniformfv(int program, int location, FloatBuffer params, int params_position);

	public static void glGetActiveUniform(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkDirect(size);
		BufferChecks.checkDirect(type);
		BufferChecks.checkDirect(name);
		nglGetActiveUniform(program, index, (name.remaining()), length, length != null ? length.position() : 0, size, size.position(), type, type.position(), name, name.position());
	}
	private static native void nglGetActiveUniform(int program, int index, int maxLength, IntBuffer length, int length_position, IntBuffer size, int size_position, IntBuffer type, int type_position, ByteBuffer name, int name_position);

	/**
	 * Returns the location of the uniform with the specified name. The ByteBuffer should contain the uniform name as a
	 * <b>null-terminated</b> string.
	 * @param program
	 * @param name
	 * @return
	 */
	public static int glGetUniformLocation(int program, ByteBuffer name) {
		BufferChecks.checkBuffer(name, 1);
		BufferChecks.checkNullTerminated(name);
		int __result = nglGetUniformLocation(program, name, name.position());
		return __result;
	}
	private static native int nglGetUniformLocation(int program, ByteBuffer name, int name_position);

	public static void glGetAttachedShaders(int program, IntBuffer count, IntBuffer shaders) {
		if (count != null)
			BufferChecks.checkBuffer(count, 1);
		BufferChecks.checkDirect(shaders);
		nglGetAttachedShaders(program, (shaders.remaining()), count, count != null ? count.position() : 0, shaders, shaders.position());
	}
	private static native void nglGetAttachedShaders(int program, int maxCount, IntBuffer count, int count_position, IntBuffer shaders, int shaders_position);

	public static void glGetProgramInfoLog(int program, IntBuffer length, ByteBuffer infoLog) {
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkDirect(infoLog);
		nglGetProgramInfoLog(program, (infoLog.remaining()), length, length != null ? length.position() : 0, infoLog, infoLog.position());
	}
	private static native void nglGetProgramInfoLog(int program, int maxLength, IntBuffer length, int length_position, ByteBuffer infoLog, int infoLog_position);

	public static void glGetShaderInfoLog(int shader, IntBuffer length, ByteBuffer infoLog) {
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkDirect(infoLog);
		nglGetShaderInfoLog(shader, (infoLog.remaining()), length, length != null ? length.position() : 0, infoLog, infoLog.position());
	}
	private static native void nglGetShaderInfoLog(int shader, int maxLength, IntBuffer length, int length_position, ByteBuffer infoLog, int infoLog_position);

	public static void glGetProgram(int program, int pname, IntBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetProgramiv(program, pname, params, params.position());
	}
	private static native void nglGetProgramiv(int program, int pname, IntBuffer params, int params_position);

	public static void glGetProgram(int program, int pname, FloatBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetProgramfv(program, pname, params, params.position());
	}
	private static native void nglGetProgramfv(int program, int pname, FloatBuffer params, int params_position);

	public static void glGetShader(int shader, int pname, IntBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetShaderiv(shader, pname, params, params.position());
	}
	private static native void nglGetShaderiv(int shader, int pname, IntBuffer params, int params_position);

	public static void glGetShader(int shader, int pname, FloatBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetShaderfv(shader, pname, params, params.position());
	}
	private static native void nglGetShaderfv(int shader, int pname, FloatBuffer params, int params_position);

	public static void glUniformMatrix4(int location, boolean transpose, FloatBuffer matrices) {
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix4fv(location, (matrices.remaining()) >> 4, transpose, matrices, matrices.position());
	}
	private static native void nglUniformMatrix4fv(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position);

	public static void glUniformMatrix3(int location, boolean transpose, FloatBuffer matrices) {
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix3fv(location, (matrices.remaining()) / (3 * 3), transpose, matrices, matrices.position());
	}
	private static native void nglUniformMatrix3fv(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position);

	public static void glUniformMatrix2(int location, boolean transpose, FloatBuffer matrices) {
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix2fv(location, (matrices.remaining()) >> 2, transpose, matrices, matrices.position());
	}
	private static native void nglUniformMatrix2fv(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position);

	public static void glUniform4(int location, IntBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform4iv(location, (values.remaining()) >> 2, values, values.position());
	}
	private static native void nglUniform4iv(int location, int count, IntBuffer values, int values_position);

	public static void glUniform3(int location, IntBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform3iv(location, (values.remaining()) / 3, values, values.position());
	}
	private static native void nglUniform3iv(int location, int count, IntBuffer values, int values_position);

	public static void glUniform2(int location, IntBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform2iv(location, (values.remaining()) >> 1, values, values.position());
	}
	private static native void nglUniform2iv(int location, int count, IntBuffer values, int values_position);

	public static void glUniform1(int location, IntBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform1iv(location, (values.remaining()), values, values.position());
	}
	private static native void nglUniform1iv(int location, int count, IntBuffer values, int values_position);

	public static void glUniform4(int location, FloatBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform4fv(location, (values.remaining()) >> 2, values, values.position());
	}
	private static native void nglUniform4fv(int location, int count, FloatBuffer values, int values_position);

	public static void glUniform3(int location, FloatBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform3fv(location, (values.remaining()) / 3, values, values.position());
	}
	private static native void nglUniform3fv(int location, int count, FloatBuffer values, int values_position);

	public static void glUniform2(int location, FloatBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform2fv(location, (values.remaining()) >> 1, values, values.position());
	}
	private static native void nglUniform2fv(int location, int count, FloatBuffer values, int values_position);

	public static void glUniform1(int location, FloatBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform1fv(location, (values.remaining()), values, values.position());
	}
	private static native void nglUniform1fv(int location, int count, FloatBuffer values, int values_position);

	public static native void glUniform4i(int location, int v0, int v1, int v2, int v3);

	public static native void glUniform3i(int location, int v0, int v1, int v2);

	public static native void glUniform2i(int location, int v0, int v1);

	public static native void glUniform1i(int location, int v0);

	public static native void glUniform4f(int location, float v0, float v1, float v2, float v3);

	public static native void glUniform3f(int location, float v0, float v1, float v2);

	public static native void glUniform2f(int location, float v0, float v1);

	public static native void glUniform1f(int location, float v0);

	public static native void glDeleteProgram(int program);

	public static native void glValidateProgram(int program);

	public static native void glUseProgram(int program);

	public static native void glLinkProgram(int program);

	public static native void glDetachShader(int program, int shader);

	public static native void glAttachShader(int program, int shader);

	public static native boolean glIsProgram(int program);

	public static native int glCreateProgram();

	public static native void glDeleteShader(int shader);

	public static native void glCompileShader(int shader);

	public static native boolean glIsShader(int shader);

	public static native int glCreateShader(int type);

	/**
	 * The ARB_shader_objects extension allows multiple, optionally null-terminated, source strings to define a shader program.
	 * <p/>
	 * This method uses just a single string, that should NOT be null-terminated.
	 * @param shader
	 * @param string
	 */
	public static void glShaderSource(int shader, ByteBuffer string) {
		BufferChecks.checkDirect(string);
		nglShaderSource(shader, 1, string, string.position(), (string.remaining()));
	}
	private static native void nglShaderSource(int shader, int count, ByteBuffer string, int string_position, int length);
}
