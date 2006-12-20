/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class GL20 {
	/**
	 * Accepted by the &lt;name&gt; parameter of GetString:
	 */
	public static final int GL_SHADING_LANGUAGE_VERSION = 0x8b8c;
	/**
	 * Accepted by the &lt;pname&gt; argument of GetInteger:
	 */
	public static final int GL_CURRENT_PROGRAM = 0x8b8d;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetObjectParameter{fi}vARB:
	 */
	public static final int GL_SHADER_TYPE = 0x8b4f;
	public static final int GL_DELETE_STATUS = 0x8b80;
	public static final int GL_COMPILE_STATUS = 0x8b81;
	public static final int GL_LINK_STATUS = 0x8b82;
	public static final int GL_VALIDATE_STATUS = 0x8b83;
	public static final int GL_INFO_LOG_LENGTH = 0x8b84;
	public static final int GL_ATTACHED_SHADERS = 0x8b85;
	public static final int GL_ACTIVE_UNIFORMS = 0x8b86;
	public static final int GL_ACTIVE_UNIFORM_MAX_LENGTH = 0x8b87;
	public static final int GL_ACTIVE_ATTRIBUTES = 0x8b89;
	public static final int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 0x8b8a;
	public static final int GL_SHADER_SOURCE_LENGTH = 0x8b88;
	/**
	 * Returned by the &lt;params&gt; parameter of GetObjectParameter{fi}vARB:
	 */
	public static final int GL_SHADER_OBJECT = 0x8b48;
	/**
	 * Returned by the &lt;type&gt; parameter of GetActiveUniformARB:
	 */
	public static final int GL_FLOAT_VEC2 = 0x8b50;
	public static final int GL_FLOAT_VEC3 = 0x8b51;
	public static final int GL_FLOAT_VEC4 = 0x8b52;
	public static final int GL_INT_VEC2 = 0x8b53;
	public static final int GL_INT_VEC3 = 0x8b54;
	public static final int GL_INT_VEC4 = 0x8b55;
	public static final int GL_BOOL = 0x8b56;
	public static final int GL_BOOL_VEC2 = 0x8b57;
	public static final int GL_BOOL_VEC3 = 0x8b58;
	public static final int GL_BOOL_VEC4 = 0x8b59;
	public static final int GL_FLOAT_MAT2 = 0x8b5a;
	public static final int GL_FLOAT_MAT3 = 0x8b5b;
	public static final int GL_FLOAT_MAT4 = 0x8b5c;
	public static final int GL_SAMPLER_1D = 0x8b5d;
	public static final int GL_SAMPLER_2D = 0x8b5e;
	public static final int GL_SAMPLER_3D = 0x8b5f;
	public static final int GL_SAMPLER_CUBE = 0x8b60;
	public static final int GL_SAMPLER_1D_SHADOW = 0x8b61;
	public static final int GL_SAMPLER_2D_SHADOW = 0x8b62;
	/**
	 * Accepted by the &lt;shaderType&gt; argument of CreateShader and
	 * returned by the &lt;params&gt; parameter of GetShader{if}v:
	 */
	public static final int GL_VERTEX_SHADER = 0x8b31;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	public static final int GL_MAX_VERTEX_UNIFORM_COMPONENTS = 0x8b4a;
	public static final int GL_MAX_VARYING_FLOATS = 0x8b4b;
	public static final int GL_MAX_VERTEX_ATTRIBS = 0x8869;
	public static final int GL_MAX_TEXTURE_IMAGE_UNITS = 0x8872;
	public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS = 0x8b4c;
	public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = 0x8b4d;
	public static final int GL_MAX_TEXTURE_COORDS = 0x8871;
	/**
	 * Accepted by the &lt;cap&gt; parameter of Disable, Enable, and IsEnabled, and
	 * by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev:
	 */
	public static final int GL_VERTEX_PROGRAM_POINT_SIZE = 0x8642;
	public static final int GL_VERTEX_PROGRAM_TWO_SIDE = 0x8643;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetVertexAttrib{dfi}vARB:
	 */
	public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED = 0x8622;
	public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE = 0x8623;
	public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE = 0x8624;
	public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE = 0x8625;
	public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED = 0x886a;
	public static final int GL_CURRENT_VERTEX_ATTRIB = 0x8626;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetVertexAttribPointervARB:
	 */
	public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER = 0x8645;
	/**
	 * Accepted by the &lt;shaderType&gt; argument of CreateShader and
	 * returned by the &lt;params&gt; parameter of GetShader{fi}vARB:
	 */
	public static final int GL_FRAGMENT_SHADER = 0x8b30;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	public static final int GL_MAX_FRAGMENT_UNIFORM_COMPONENTS = 0x8b49;
	/**
	 * Accepted by the &lt;target&gt; parameter of Hint and the &lt;pname&gt; parameter of
	 * GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev:
	 */
	public static final int GL_FRAGMENT_SHADER_DERIVATIVE_HINT = 0x8b8b;
	/**
	 * Accepted by the &lt;pname&gt; parameters of GetIntegerv, GetFloatv,
	 * and GetDoublev:
	 */
	public static final int GL_MAX_DRAW_BUFFERS = 0x8824;
	public static final int GL_DRAW_BUFFER0 = 0x8825;
	public static final int GL_DRAW_BUFFER1 = 0x8826;
	public static final int GL_DRAW_BUFFER2 = 0x8827;
	public static final int GL_DRAW_BUFFER3 = 0x8828;
	public static final int GL_DRAW_BUFFER4 = 0x8829;
	public static final int GL_DRAW_BUFFER5 = 0x882a;
	public static final int GL_DRAW_BUFFER6 = 0x882b;
	public static final int GL_DRAW_BUFFER7 = 0x882c;
	public static final int GL_DRAW_BUFFER8 = 0x882d;
	public static final int GL_DRAW_BUFFER9 = 0x882e;
	public static final int GL_DRAW_BUFFER10 = 0x882f;
	public static final int GL_DRAW_BUFFER11 = 0x8830;
	public static final int GL_DRAW_BUFFER12 = 0x8831;
	public static final int GL_DRAW_BUFFER13 = 0x8832;
	public static final int GL_DRAW_BUFFER14 = 0x8833;
	public static final int GL_DRAW_BUFFER15 = 0x8834;
	/**
	 * Accepted by the &lt;cap&gt; parameter of Enable, Disable, and IsEnabled, by
	 * the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev, and by the &lt;target&gt; parameter of TexEnvi, TexEnviv,
	 * TexEnvf, TexEnvfv, GetTexEnviv, and GetTexEnvfv:
	 */
	public static final int GL_POINT_SPRITE = 0x8861;
	/**
	 * When the &lt;target&gt; parameter of TexEnvf, TexEnvfv, TexEnvi, TexEnviv,
	 * GetTexEnvfv, or GetTexEnviv is POINT_SPRITE, then the value of
	 * &lt;pname&gt; may be:
	 */
	public static final int GL_COORD_REPLACE = 0x8862;
	/**
	 * Accepted by the &lt;pname&gt; parameter of PointParameter{if}vARB, and the
	 * &lt;pname&gt; of Get:
	 */
	public static final int GL_POINT_SPRITE_COORD_ORIGIN = 0x8ca0;
	/**
	 * Accepted by the &lt;param&gt; parameter of PointParameter{if}vARB:
	 */
	public static final int GL_LOWER_LEFT = 0x8ca1;
	public static final int GL_UPPER_LEFT = 0x8ca2;
	public static final int GL_STENCIL_BACK_FUNC = 0x8800;
	public static final int GL_STENCIL_BACK_FAIL = 0x8801;
	public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL = 0x8802;
	public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS = 0x8803;
	public static final int GL_STENCIL_BACK_REF = 0x8ca3;
	public static final int GL_STENCIL_BACK_VALUE_MASK = 0x8ca4;
	public static final int GL_STENCIL_BACK_WRITEMASK = 0x8ca5;
	public static final int GL_BLEND_EQUATION_RGB = 0x8009;
	public static final int GL_BLEND_EQUATION_ALPHA = 0x883d;

	private GL20() {
	}


	/**
	 * The ARB_shader_objects extension allows multiple, optionally null-terminated, source strings to define a shader program.
	 * <p/>
	 * This method uses just a single string, that should NOT be null-terminated.
	 * @param shader
	 * @param string
	 */
	public static void glShaderSource(int shader, ByteBuffer string) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glShaderSource_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(string);
		nglShaderSource(shader, 1, string, string.position(), (string.remaining()), function_pointer);
	}
	private static native void nglShaderSource(int shader, int count, ByteBuffer string, int string_position, int length, long function_pointer);

	public static int glCreateShader(int type) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glCreateShader_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglCreateShader(type, function_pointer);
		return __result;
	}
	private static native int nglCreateShader(int type, long function_pointer);

	public static boolean glIsShader(int shader) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glIsShader_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsShader(shader, function_pointer);
		return __result;
	}
	private static native boolean nglIsShader(int shader, long function_pointer);

	public static void glCompileShader(int shader) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glCompileShader_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglCompileShader(shader, function_pointer);
	}
	private static native void nglCompileShader(int shader, long function_pointer);

	public static void glDeleteShader(int shader) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glDeleteShader_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDeleteShader(shader, function_pointer);
	}
	private static native void nglDeleteShader(int shader, long function_pointer);

	public static int glCreateProgram() {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glCreateProgram_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglCreateProgram(function_pointer);
		return __result;
	}
	private static native int nglCreateProgram(long function_pointer);

	public static boolean glIsProgram(int program) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glIsProgram_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsProgram(program, function_pointer);
		return __result;
	}
	private static native boolean nglIsProgram(int program, long function_pointer);

	public static void glAttachShader(int program, int shader) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glAttachShader_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglAttachShader(program, shader, function_pointer);
	}
	private static native void nglAttachShader(int program, int shader, long function_pointer);

	public static void glDetachShader(int program, int shader) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glDetachShader_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDetachShader(program, shader, function_pointer);
	}
	private static native void nglDetachShader(int program, int shader, long function_pointer);

	public static void glLinkProgram(int program) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glLinkProgram_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglLinkProgram(program, function_pointer);
	}
	private static native void nglLinkProgram(int program, long function_pointer);

	public static void glUseProgram(int program) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUseProgram_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUseProgram(program, function_pointer);
	}
	private static native void nglUseProgram(int program, long function_pointer);

	public static void glValidateProgram(int program) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glValidateProgram_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglValidateProgram(program, function_pointer);
	}
	private static native void nglValidateProgram(int program, long function_pointer);

	public static void glDeleteProgram(int program) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glDeleteProgram_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDeleteProgram(program, function_pointer);
	}
	private static native void nglDeleteProgram(int program, long function_pointer);

	public static void glUniform1f(int location, float v0) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform1f_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform1f(location, v0, function_pointer);
	}
	private static native void nglUniform1f(int location, float v0, long function_pointer);

	public static void glUniform2f(int location, float v0, float v1) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform2f_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform2f(location, v0, v1, function_pointer);
	}
	private static native void nglUniform2f(int location, float v0, float v1, long function_pointer);

	public static void glUniform3f(int location, float v0, float v1, float v2) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform3f_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform3f(location, v0, v1, v2, function_pointer);
	}
	private static native void nglUniform3f(int location, float v0, float v1, float v2, long function_pointer);

	public static void glUniform4f(int location, float v0, float v1, float v2, float v3) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform4f_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform4f(location, v0, v1, v2, v3, function_pointer);
	}
	private static native void nglUniform4f(int location, float v0, float v1, float v2, float v3, long function_pointer);

	public static void glUniform1i(int location, int v0) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform1i_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform1i(location, v0, function_pointer);
	}
	private static native void nglUniform1i(int location, int v0, long function_pointer);

	public static void glUniform2i(int location, int v0, int v1) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform2i_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform2i(location, v0, v1, function_pointer);
	}
	private static native void nglUniform2i(int location, int v0, int v1, long function_pointer);

	public static void glUniform3i(int location, int v0, int v1, int v2) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform3i_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform3i(location, v0, v1, v2, function_pointer);
	}
	private static native void nglUniform3i(int location, int v0, int v1, int v2, long function_pointer);

	public static void glUniform4i(int location, int v0, int v1, int v2, int v3) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform4i_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform4i(location, v0, v1, v2, v3, function_pointer);
	}
	private static native void nglUniform4i(int location, int v0, int v1, int v2, int v3, long function_pointer);

	public static void glUniform1(int location, FloatBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform1fv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform1fv(location, (values.remaining()), values, values.position(), function_pointer);
	}
	private static native void nglUniform1fv(int location, int count, FloatBuffer values, int values_position, long function_pointer);

	public static void glUniform2(int location, FloatBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform2fv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform2fv(location, (values.remaining()) >> 1, values, values.position(), function_pointer);
	}
	private static native void nglUniform2fv(int location, int count, FloatBuffer values, int values_position, long function_pointer);

	public static void glUniform3(int location, FloatBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform3fv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform3fv(location, (values.remaining()) / 3, values, values.position(), function_pointer);
	}
	private static native void nglUniform3fv(int location, int count, FloatBuffer values, int values_position, long function_pointer);

	public static void glUniform4(int location, FloatBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform4fv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform4fv(location, (values.remaining()) >> 2, values, values.position(), function_pointer);
	}
	private static native void nglUniform4fv(int location, int count, FloatBuffer values, int values_position, long function_pointer);

	public static void glUniform1(int location, IntBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform1iv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform1iv(location, (values.remaining()), values, values.position(), function_pointer);
	}
	private static native void nglUniform1iv(int location, int count, IntBuffer values, int values_position, long function_pointer);

	public static void glUniform2(int location, IntBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform2iv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform2iv(location, (values.remaining()) >> 1, values, values.position(), function_pointer);
	}
	private static native void nglUniform2iv(int location, int count, IntBuffer values, int values_position, long function_pointer);

	public static void glUniform3(int location, IntBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform3iv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform3iv(location, (values.remaining()) / 3, values, values.position(), function_pointer);
	}
	private static native void nglUniform3iv(int location, int count, IntBuffer values, int values_position, long function_pointer);

	public static void glUniform4(int location, IntBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniform4iv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform4iv(location, (values.remaining()) >> 2, values, values.position(), function_pointer);
	}
	private static native void nglUniform4iv(int location, int count, IntBuffer values, int values_position, long function_pointer);

	public static void glUniformMatrix2(int location, boolean transpose, FloatBuffer matrices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniformMatrix2fv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix2fv(location, (matrices.remaining()) >> 2, transpose, matrices, matrices.position(), function_pointer);
	}
	private static native void nglUniformMatrix2fv(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position, long function_pointer);

	public static void glUniformMatrix3(int location, boolean transpose, FloatBuffer matrices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniformMatrix3fv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix3fv(location, (matrices.remaining()) / (3 * 3), transpose, matrices, matrices.position(), function_pointer);
	}
	private static native void nglUniformMatrix3fv(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position, long function_pointer);

	public static void glUniformMatrix4(int location, boolean transpose, FloatBuffer matrices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glUniformMatrix4fv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix4fv(location, (matrices.remaining()) >> 4, transpose, matrices, matrices.position(), function_pointer);
	}
	private static native void nglUniformMatrix4fv(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position, long function_pointer);

	public static void glGetShader(int shader, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetShaderiv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetShaderiv(shader, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetShaderiv(int shader, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetProgram(int program, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetProgramiv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetProgramiv(program, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetProgramiv(int program, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetShaderInfoLog(int shader, IntBuffer length, ByteBuffer infoLog) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetShaderInfoLog_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkDirect(infoLog);
		nglGetShaderInfoLog(shader, (infoLog.remaining()), length, length != null ? length.position() : 0, infoLog, infoLog.position(), function_pointer);
	}
	private static native void nglGetShaderInfoLog(int shader, int maxLength, IntBuffer length, int length_position, ByteBuffer infoLog, int infoLog_position, long function_pointer);

	public static void glGetProgramInfoLog(int program, IntBuffer length, ByteBuffer infoLog) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetProgramInfoLog_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkDirect(infoLog);
		nglGetProgramInfoLog(program, (infoLog.remaining()), length, length != null ? length.position() : 0, infoLog, infoLog.position(), function_pointer);
	}
	private static native void nglGetProgramInfoLog(int program, int maxLength, IntBuffer length, int length_position, ByteBuffer infoLog, int infoLog_position, long function_pointer);

	public static void glGetAttachedShaders(int program, IntBuffer count, IntBuffer shaders) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetAttachedShaders_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (count != null)
			BufferChecks.checkBuffer(count, 1);
		BufferChecks.checkDirect(shaders);
		nglGetAttachedShaders(program, (shaders.remaining()), count, count != null ? count.position() : 0, shaders, shaders.position(), function_pointer);
	}
	private static native void nglGetAttachedShaders(int program, int maxCount, IntBuffer count, int count_position, IntBuffer shaders, int shaders_position, long function_pointer);

	/**
	 * Returns the location of the uniform with the specified name. The ByteBuffer should contain the uniform name as a
	 * <b>null-terminated</b> string.
	 * @param program
	 * @param name
	 * @return
	 */
	public static int glGetUniformLocation(int program, ByteBuffer name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetUniformLocation_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(name, 1);
		BufferChecks.checkNullTerminated(name);
		int __result = nglGetUniformLocation(program, name, name.position(), function_pointer);
		return __result;
	}
	private static native int nglGetUniformLocation(int program, ByteBuffer name, int name_position, long function_pointer);

	public static void glGetActiveUniform(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetActiveUniform_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkDirect(size);
		BufferChecks.checkDirect(type);
		BufferChecks.checkDirect(name);
		nglGetActiveUniform(program, index, (name.remaining()), length, length != null ? length.position() : 0, size, size.position(), type, type.position(), name, name.position(), function_pointer);
	}
	private static native void nglGetActiveUniform(int program, int index, int maxLength, IntBuffer length, int length_position, IntBuffer size, int size_position, IntBuffer type, int type_position, ByteBuffer name, int name_position, long function_pointer);

	public static void glGetUniform(int program, int location, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetUniformfv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetUniformfv(program, location, params, params.position(), function_pointer);
	}
	private static native void nglGetUniformfv(int program, int location, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetUniform(int program, int location, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetUniformiv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetUniformiv(program, location, params, params.position(), function_pointer);
	}
	private static native void nglGetUniformiv(int program, int location, IntBuffer params, int params_position, long function_pointer);

	public static void glGetShaderSource(int shader, IntBuffer length, ByteBuffer source) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetShaderSource_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkDirect(source);
		nglGetShaderSource(shader, (source.remaining()), length, length != null ? length.position() : 0, source, source.position(), function_pointer);
	}
	private static native void nglGetShaderSource(int shader, int maxLength, IntBuffer length, int length_position, ByteBuffer source, int source_position, long function_pointer);

	public static void glVertexAttrib1s(int index, short x) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttrib1s_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib1s(index, x, function_pointer);
	}
	private static native void nglVertexAttrib1s(int index, short x, long function_pointer);

	public static void glVertexAttrib1f(int index, float x) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttrib1f_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib1f(index, x, function_pointer);
	}
	private static native void nglVertexAttrib1f(int index, float x, long function_pointer);

	public static void glVertexAttrib1d(int index, double x) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttrib1d_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib1d(index, x, function_pointer);
	}
	private static native void nglVertexAttrib1d(int index, double x, long function_pointer);

	public static void glVertexAttrib2s(int index, short x, short y) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttrib2s_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib2s(index, x, y, function_pointer);
	}
	private static native void nglVertexAttrib2s(int index, short x, short y, long function_pointer);

	public static void glVertexAttrib2f(int index, float x, float y) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttrib2f_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib2f(index, x, y, function_pointer);
	}
	private static native void nglVertexAttrib2f(int index, float x, float y, long function_pointer);

	public static void glVertexAttrib2d(int index, double x, double y) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttrib2d_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib2d(index, x, y, function_pointer);
	}
	private static native void nglVertexAttrib2d(int index, double x, double y, long function_pointer);

	public static void glVertexAttrib3s(int index, short x, short y, short z) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttrib3s_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib3s(index, x, y, z, function_pointer);
	}
	private static native void nglVertexAttrib3s(int index, short x, short y, short z, long function_pointer);

	public static void glVertexAttrib3f(int index, float x, float y, float z) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttrib3f_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib3f(index, x, y, z, function_pointer);
	}
	private static native void nglVertexAttrib3f(int index, float x, float y, float z, long function_pointer);

	public static void glVertexAttrib3d(int index, double x, double y, double z) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttrib3d_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib3d(index, x, y, z, function_pointer);
	}
	private static native void nglVertexAttrib3d(int index, double x, double y, double z, long function_pointer);

	public static void glVertexAttrib4s(int index, short x, short y, short z, short w) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttrib4s_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib4s(index, x, y, z, w, function_pointer);
	}
	private static native void nglVertexAttrib4s(int index, short x, short y, short z, short w, long function_pointer);

	public static void glVertexAttrib4f(int index, float x, float y, float z, float w) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttrib4f_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib4f(index, x, y, z, w, function_pointer);
	}
	private static native void nglVertexAttrib4f(int index, float x, float y, float z, float w, long function_pointer);

	public static void glVertexAttrib4d(int index, double x, double y, double z, double w) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttrib4d_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib4d(index, x, y, z, w, function_pointer);
	}
	private static native void nglVertexAttrib4d(int index, double x, double y, double z, double w, long function_pointer);

	public static void glVertexAttrib4Nub(int index, byte x, byte y, byte z, byte w) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttrib4Nub_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib4Nub(index, x, y, z, w, function_pointer);
	}
	private static native void nglVertexAttrib4Nub(int index, byte x, byte y, byte z, byte w, long function_pointer);

	public static void glVertexAttribPointer(int index, int size, boolean normalized, int stride, DoubleBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttribPointer_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).GL20_glVertexAttribPointer_buffer = buffer;
		nglVertexAttribPointer(index, size, GL11.GL_DOUBLE, normalized, stride, buffer, buffer.position() << 3, function_pointer);
	}
	public static void glVertexAttribPointer(int index, int size, boolean normalized, int stride, FloatBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttribPointer_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).GL20_glVertexAttribPointer_buffer = buffer;
		nglVertexAttribPointer(index, size, GL11.GL_FLOAT, normalized, stride, buffer, buffer.position() << 2, function_pointer);
	}
	public static void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttribPointer_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).GL20_glVertexAttribPointer_buffer = buffer;
		nglVertexAttribPointer(index, size, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, normalized, stride, buffer, buffer.position(), function_pointer);
	}
	public static void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttribPointer_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).GL20_glVertexAttribPointer_buffer = buffer;
		nglVertexAttribPointer(index, size, unsigned ? GL11.GL_UNSIGNED_INT : GL11.GL_INT, normalized, stride, buffer, buffer.position() << 2, function_pointer);
	}
	public static void glVertexAttribPointer(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttribPointer_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).GL20_glVertexAttribPointer_buffer = buffer;
		nglVertexAttribPointer(index, size, unsigned ? GL11.GL_UNSIGNED_SHORT : GL11.GL_SHORT, normalized, stride, buffer, buffer.position() << 1, function_pointer);
	}
	private static native void nglVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, Buffer buffer, int buffer_position, long function_pointer);
	public static void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long buffer_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glVertexAttribPointer_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOenabled(caps);
		nglVertexAttribPointerBO(index, size, type, normalized, stride, buffer_buffer_offset, function_pointer);
	}
	private static native void nglVertexAttribPointerBO(int index, int size, int type, boolean normalized, int stride, long buffer_buffer_offset, long function_pointer);

	public static void glEnableVertexAttribArray(int index) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glEnableVertexAttribArray_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglEnableVertexAttribArray(index, function_pointer);
	}
	private static native void nglEnableVertexAttribArray(int index, long function_pointer);

	public static void glDisableVertexAttribArray(int index) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glDisableVertexAttribArray_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDisableVertexAttribArray(index, function_pointer);
	}
	private static native void nglDisableVertexAttribArray(int index, long function_pointer);

	public static void glGetVertexAttrib(int index, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetVertexAttribfv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetVertexAttribfv(index, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetVertexAttribfv(int index, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetVertexAttrib(int index, int pname, DoubleBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetVertexAttribdv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetVertexAttribdv(index, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetVertexAttribdv(int index, int pname, DoubleBuffer params, int params_position, long function_pointer);

	public static void glGetVertexAttrib(int index, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetVertexAttribiv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetVertexAttribiv(index, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetVertexAttribiv(int index, int pname, IntBuffer params, int params_position, long function_pointer);

	public static java.nio.ByteBuffer glGetVertexAttribPointer(int index, int pname, long result_size) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetVertexAttribPointerv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		java.nio.ByteBuffer __result = nglGetVertexAttribPointerv(index, pname, result_size, function_pointer);
		return __result;
	}
	private static native java.nio.ByteBuffer nglGetVertexAttribPointerv(int index, int pname, long result_size, long function_pointer);

	public static void glBindAttribLocation(int program, int index, ByteBuffer name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glBindAttribLocation_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(name);
		BufferChecks.checkNullTerminated(name);
		nglBindAttribLocation(program, index, name, name.position(), function_pointer);
	}
	private static native void nglBindAttribLocation(int program, int index, ByteBuffer name, int name_position, long function_pointer);

	public static void glGetActiveAttrib(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetActiveAttrib_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkBuffer(size, 1);
		BufferChecks.checkBuffer(type, 1);
		BufferChecks.checkDirect(name);
		nglGetActiveAttrib(program, index, (name.remaining()), length, length != null ? length.position() : 0, size, size.position(), type, type.position(), name, name.position(), function_pointer);
	}
	private static native void nglGetActiveAttrib(int program, int index, int maxLength, IntBuffer length, int length_position, IntBuffer size, int size_position, IntBuffer type, int type_position, ByteBuffer name, int name_position, long function_pointer);

	public static int glGetAttribLocation(int program, ByteBuffer name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glGetAttribLocation_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(name);
		BufferChecks.checkNullTerminated(name);
		int __result = nglGetAttribLocation(program, name, name.position(), function_pointer);
		return __result;
	}
	private static native int nglGetAttribLocation(int program, ByteBuffer name, int name_position, long function_pointer);

	public static void glDrawBuffers(IntBuffer buffers) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glDrawBuffers_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(buffers);
		nglDrawBuffers((buffers.remaining()), buffers, buffers.position(), function_pointer);
	}
	private static native void nglDrawBuffers(int size, IntBuffer buffers, int buffers_position, long function_pointer);

	public static void glStencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glStencilOpSeparate_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglStencilOpSeparate(face, sfail, dpfail, dppass, function_pointer);
	}
	private static native void nglStencilOpSeparate(int face, int sfail, int dpfail, int dppass, long function_pointer);

	public static void glStencilFuncSeparate(int face, int func, int ref, int mask) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glStencilFuncSeparate_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglStencilFuncSeparate(face, func, ref, mask, function_pointer);
	}
	private static native void nglStencilFuncSeparate(int face, int func, int ref, int mask, long function_pointer);

	public static void glStencilMaskSeparate(int face, int mask) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glStencilMaskSeparate_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglStencilMaskSeparate(face, mask, function_pointer);
	}
	private static native void nglStencilMaskSeparate(int face, int mask, long function_pointer);

	public static void glBlendEquationSeparate(int modeRGB, int modeAlpha) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL20_glBlendEquationSeparate_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendEquationSeparate(modeRGB, modeAlpha, function_pointer);
	}
	private static native void nglBlendEquationSeparate(int modeRGB, int modeAlpha, long function_pointer);
}
