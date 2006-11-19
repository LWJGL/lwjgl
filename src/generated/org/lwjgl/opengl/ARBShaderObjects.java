/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBShaderObjects {
	/**
	 * Accepted by the &lt;pname&gt; argument of GetHandleARB:
	 */
	public static final int GL_PROGRAM_OBJECT_ARB = 0x8b40;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetObjectParameter{fi}vARB:
	 */
	public static final int GL_OBJECT_TYPE_ARB = 0x8b4e;
	public static final int GL_OBJECT_SUBTYPE_ARB = 0x8b4f;
	public static final int GL_OBJECT_DELETE_STATUS_ARB = 0x8b80;
	public static final int GL_OBJECT_COMPILE_STATUS_ARB = 0x8b81;
	public static final int GL_OBJECT_LINK_STATUS_ARB = 0x8b82;
	public static final int GL_OBJECT_VALIDATE_STATUS_ARB = 0x8b83;
	public static final int GL_OBJECT_INFO_LOG_LENGTH_ARB = 0x8b84;
	public static final int GL_OBJECT_ATTACHED_OBJECTS_ARB = 0x8b85;
	public static final int GL_OBJECT_ACTIVE_UNIFORMS_ARB = 0x8b86;
	public static final int GL_OBJECT_ACTIVE_UNIFORM_MAX_LENGTH_ARB = 0x8b87;
	public static final int GL_OBJECT_SHADER_SOURCE_LENGTH_ARB = 0x8b88;
	/**
	 * Returned by the &lt;params&gt; parameter of GetObjectParameter{fi}vARB:
	 */
	public static final int GL_SHADER_OBJECT_ARB = 0x8b48;
	/**
	 * Returned by the &lt;type&gt; parameter of GetActiveUniformARB:
	 */
	public static final int GL_FLOAT = 0x1406;
	public static final int GL_FLOAT_VEC2_ARB = 0x8b50;
	public static final int GL_FLOAT_VEC3_ARB = 0x8b51;
	public static final int GL_FLOAT_VEC4_ARB = 0x8b52;
	public static final int GL_INT = 0x1404;
	public static final int GL_INT_VEC2_ARB = 0x8b53;
	public static final int GL_INT_VEC3_ARB = 0x8b54;
	public static final int GL_INT_VEC4_ARB = 0x8b55;
	public static final int GL_BOOL_ARB = 0x8b56;
	public static final int GL_BOOL_VEC2_ARB = 0x8b57;
	public static final int GL_BOOL_VEC3_ARB = 0x8b58;
	public static final int GL_BOOL_VEC4_ARB = 0x8b59;
	public static final int GL_FLOAT_MAT2_ARB = 0x8b5a;
	public static final int GL_FLOAT_MAT3_ARB = 0x8b5b;
	public static final int GL_FLOAT_MAT4_ARB = 0x8b5c;
	public static final int GL_SAMPLER_1D_ARB = 0x8b5d;
	public static final int GL_SAMPLER_2D_ARB = 0x8b5e;
	public static final int GL_SAMPLER_3D_ARB = 0x8b5f;
	public static final int GL_SAMPLER_CUBE_ARB = 0x8b60;
	public static final int GL_SAMPLER_1D_SHADOW_ARB = 0x8b61;
	public static final int GL_SAMPLER_2D_SHADOW_ARB = 0x8b62;
	public static final int GL_SAMPLER_2D_RECT_ARB = 0x8b63;
	public static final int GL_SAMPLER_2D_RECT_SHADOW_ARB = 0x8b64;

	private ARBShaderObjects() {
	}


	public static void glDeleteObjectARB(int obj) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glDeleteObjectARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDeleteObjectARB(obj, function_pointer);
	}
	private static native void nglDeleteObjectARB(int obj, long function_pointer);

	public static int glGetHandleARB(int pname) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glGetHandleARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglGetHandleARB(pname, function_pointer);
		return __result;
	}
	private static native int nglGetHandleARB(int pname, long function_pointer);

	public static void glDetachObjectARB(int containerObj, int attachedObj) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glDetachObjectARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDetachObjectARB(containerObj, attachedObj, function_pointer);
	}
	private static native void nglDetachObjectARB(int containerObj, int attachedObj, long function_pointer);

	public static int glCreateShaderObjectARB(int shaderType) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glCreateShaderObjectARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglCreateShaderObjectARB(shaderType, function_pointer);
		return __result;
	}
	private static native int nglCreateShaderObjectARB(int shaderType, long function_pointer);

	/**
	 * The ARB_shader_objects extension allows multiple, optionally null-terminated, source strings to define a shader program.
	 * <p/>
	 * This method uses just a single string, that should NOT be null-terminated.
	 */
	public static void glShaderSourceARB(int shader, ByteBuffer string) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glShaderSourceARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(string);
		nglShaderSourceARB(shader, 1, string, string.position(), (string.remaining()), function_pointer);
	}
	private static native void nglShaderSourceARB(int shader, int count, ByteBuffer string, int string_position, int length, long function_pointer);

	public static void glCompileShaderARB(int shaderObj) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glCompileShaderARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglCompileShaderARB(shaderObj, function_pointer);
	}
	private static native void nglCompileShaderARB(int shaderObj, long function_pointer);

	public static int glCreateProgramObjectARB() {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glCreateProgramObjectARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglCreateProgramObjectARB(function_pointer);
		return __result;
	}
	private static native int nglCreateProgramObjectARB(long function_pointer);

	public static void glAttachObjectARB(int containerObj, int obj) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glAttachObjectARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglAttachObjectARB(containerObj, obj, function_pointer);
	}
	private static native void nglAttachObjectARB(int containerObj, int obj, long function_pointer);

	public static void glLinkProgramARB(int programObj) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glLinkProgramARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglLinkProgramARB(programObj, function_pointer);
	}
	private static native void nglLinkProgramARB(int programObj, long function_pointer);

	public static void glUseProgramObjectARB(int programObj) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUseProgramObjectARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUseProgramObjectARB(programObj, function_pointer);
	}
	private static native void nglUseProgramObjectARB(int programObj, long function_pointer);

	public static void glValidateProgramARB(int programObj) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glValidateProgramARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglValidateProgramARB(programObj, function_pointer);
	}
	private static native void nglValidateProgramARB(int programObj, long function_pointer);

	public static void glUniform1fARB(int location, float v0) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform1fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform1fARB(location, v0, function_pointer);
	}
	private static native void nglUniform1fARB(int location, float v0, long function_pointer);

	public static void glUniform2fARB(int location, float v0, float v1) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform2fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform2fARB(location, v0, v1, function_pointer);
	}
	private static native void nglUniform2fARB(int location, float v0, float v1, long function_pointer);

	public static void glUniform3fARB(int location, float v0, float v1, float v2) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform3fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform3fARB(location, v0, v1, v2, function_pointer);
	}
	private static native void nglUniform3fARB(int location, float v0, float v1, float v2, long function_pointer);

	public static void glUniform4fARB(int location, float v0, float v1, float v2, float v3) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform4fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform4fARB(location, v0, v1, v2, v3, function_pointer);
	}
	private static native void nglUniform4fARB(int location, float v0, float v1, float v2, float v3, long function_pointer);

	public static void glUniform1iARB(int location, int v0) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform1iARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform1iARB(location, v0, function_pointer);
	}
	private static native void nglUniform1iARB(int location, int v0, long function_pointer);

	public static void glUniform2iARB(int location, int v0, int v1) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform2iARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform2iARB(location, v0, v1, function_pointer);
	}
	private static native void nglUniform2iARB(int location, int v0, int v1, long function_pointer);

	public static void glUniform3iARB(int location, int v0, int v1, int v2) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform3iARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform3iARB(location, v0, v1, v2, function_pointer);
	}
	private static native void nglUniform3iARB(int location, int v0, int v1, int v2, long function_pointer);

	public static void glUniform4iARB(int location, int v0, int v1, int v2, int v3) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform4iARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform4iARB(location, v0, v1, v2, v3, function_pointer);
	}
	private static native void nglUniform4iARB(int location, int v0, int v1, int v2, int v3, long function_pointer);

	public static void glUniform1ARB(int location, FloatBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform1fvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform1fvARB(location, (values.remaining()), values, values.position(), function_pointer);
	}
	private static native void nglUniform1fvARB(int location, int count, FloatBuffer values, int values_position, long function_pointer);

	public static void glUniform2ARB(int location, FloatBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform2fvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform2fvARB(location, (values.remaining()) >> 1, values, values.position(), function_pointer);
	}
	private static native void nglUniform2fvARB(int location, int count, FloatBuffer values, int values_position, long function_pointer);

	public static void glUniform3ARB(int location, FloatBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform3fvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform3fvARB(location, (values.remaining()) / 3, values, values.position(), function_pointer);
	}
	private static native void nglUniform3fvARB(int location, int count, FloatBuffer values, int values_position, long function_pointer);

	public static void glUniform4ARB(int location, FloatBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform4fvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform4fvARB(location, (values.remaining()) >> 2, values, values.position(), function_pointer);
	}
	private static native void nglUniform4fvARB(int location, int count, FloatBuffer values, int values_position, long function_pointer);

	public static void glUniform1ARB(int location, IntBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform1ivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform1ivARB(location, (values.remaining()), values, values.position(), function_pointer);
	}
	private static native void nglUniform1ivARB(int location, int count, IntBuffer values, int values_position, long function_pointer);

	public static void glUniform2ARB(int location, IntBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform2ivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform2ivARB(location, (values.remaining()) >> 1, values, values.position(), function_pointer);
	}
	private static native void nglUniform2ivARB(int location, int count, IntBuffer values, int values_position, long function_pointer);

	public static void glUniform3ARB(int location, IntBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform3ivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform3ivARB(location, (values.remaining()) / 3, values, values.position(), function_pointer);
	}
	private static native void nglUniform3ivARB(int location, int count, IntBuffer values, int values_position, long function_pointer);

	public static void glUniform4ARB(int location, IntBuffer values) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniform4ivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(values);
		nglUniform4ivARB(location, (values.remaining()) >> 2, values, values.position(), function_pointer);
	}
	private static native void nglUniform4ivARB(int location, int count, IntBuffer values, int values_position, long function_pointer);

	public static void glUniformMatrix2ARB(int location, boolean transpose, FloatBuffer matrices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniformMatrix2fvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix2fvARB(location, (matrices.remaining()) >> 2, transpose, matrices, matrices.position(), function_pointer);
	}
	private static native void nglUniformMatrix2fvARB(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position, long function_pointer);

	public static void glUniformMatrix3ARB(int location, boolean transpose, FloatBuffer matrices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniformMatrix3fvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix3fvARB(location, (matrices.remaining()) / (3 * 3), transpose, matrices, matrices.position(), function_pointer);
	}
	private static native void nglUniformMatrix3fvARB(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position, long function_pointer);

	public static void glUniformMatrix4ARB(int location, boolean transpose, FloatBuffer matrices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glUniformMatrix4fvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix4fvARB(location, (matrices.remaining()) >> 4, transpose, matrices, matrices.position(), function_pointer);
	}
	private static native void nglUniformMatrix4fvARB(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position, long function_pointer);

	public static void glGetObjectParameterARB(int obj, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glGetObjectParameterfvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetObjectParameterfvARB(obj, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetObjectParameterfvARB(int obj, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetObjectParameterARB(int obj, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glGetObjectParameterivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetObjectParameterivARB(obj, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetObjectParameterivARB(int obj, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetInfoLogARB(int obj, IntBuffer length, ByteBuffer infoLog) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glGetInfoLogARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkDirect(infoLog);
		nglGetInfoLogARB(obj, (infoLog.remaining()), length, length != null ? length.position() : 0, infoLog, infoLog.position(), function_pointer);
	}
	private static native void nglGetInfoLogARB(int obj, int maxLength, IntBuffer length, int length_position, ByteBuffer infoLog, int infoLog_position, long function_pointer);

	public static void glGetAttachedObjectsARB(int containerObj, IntBuffer count, IntBuffer obj) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glGetAttachedObjectsARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (count != null)
			BufferChecks.checkBuffer(count, 1);
		BufferChecks.checkDirect(obj);
		nglGetAttachedObjectsARB(containerObj, (obj.remaining()), count, count != null ? count.position() : 0, obj, obj.position(), function_pointer);
	}
	private static native void nglGetAttachedObjectsARB(int containerObj, int maxCount, IntBuffer count, int count_position, IntBuffer obj, int obj_position, long function_pointer);

	/**
	 * Returns the location of the uniform with the specified name. The ByteBuffer should contain the uniform name as a <b>null-terminated</b> string.
	 * @param programObj
	 * @param name
	 */
	public static int glGetUniformLocationARB(int programObj, ByteBuffer name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glGetUniformLocationARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(name);
		BufferChecks.checkNullTerminated(name);
		int __result = nglGetUniformLocationARB(programObj, name, name.position(), function_pointer);
		return __result;
	}
	private static native int nglGetUniformLocationARB(int programObj, ByteBuffer name, int name_position, long function_pointer);

	public static void glGetActiveUniformARB(int programObj, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glGetActiveUniformARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkBuffer(size, 1);
		BufferChecks.checkBuffer(type, 1);
		BufferChecks.checkDirect(name);
		nglGetActiveUniformARB(programObj, index, (name.remaining()), length, length != null ? length.position() : 0, size, size.position(), type, type.position(), name, name.position(), function_pointer);
	}
	private static native void nglGetActiveUniformARB(int programObj, int index, int maxLength, IntBuffer length, int length_position, IntBuffer size, int size_position, IntBuffer type, int type_position, ByteBuffer name, int name_position, long function_pointer);

	public static void glGetUniformARB(int programObj, int location, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glGetUniformfvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetUniformfvARB(programObj, location, params, params.position(), function_pointer);
	}
	private static native void nglGetUniformfvARB(int programObj, int location, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetUniformARB(int programObj, int location, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glGetUniformivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetUniformivARB(programObj, location, params, params.position(), function_pointer);
	}
	private static native void nglGetUniformivARB(int programObj, int location, IntBuffer params, int params_position, long function_pointer);

	public static void glGetShaderSourceARB(int obj, IntBuffer length, ByteBuffer source) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_shader_objects_glGetShaderSourceARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkDirect(source);
		nglGetShaderSourceARB(obj, (source.remaining()), length, length != null ? length.position() : 0, source, source.position(), function_pointer);
	}
	private static native void nglGetShaderSourceARB(int obj, int maxLength, IntBuffer length, int length_position, ByteBuffer source, int source_position, long function_pointer);
}
