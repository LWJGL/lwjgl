/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBShaderObjects {
	public static final int GL_SAMPLER_2D_RECT_SHADOW_ARB = 0x8b64;
	public static final int GL_SAMPLER_2D_RECT_ARB = 0x8b63;
	public static final int GL_SAMPLER_2D_SHADOW_ARB = 0x8b62;
	public static final int GL_SAMPLER_1D_SHADOW_ARB = 0x8b61;
	public static final int GL_SAMPLER_CUBE_ARB = 0x8b60;
	public static final int GL_SAMPLER_3D_ARB = 0x8b5f;
	public static final int GL_SAMPLER_2D_ARB = 0x8b5e;
	public static final int GL_SAMPLER_1D_ARB = 0x8b5d;
	public static final int GL_FLOAT_MAT4_ARB = 0x8b5c;
	public static final int GL_FLOAT_MAT3_ARB = 0x8b5b;
	public static final int GL_FLOAT_MAT2_ARB = 0x8b5a;
	public static final int GL_BOOL_VEC4_ARB = 0x8b59;
	public static final int GL_BOOL_VEC3_ARB = 0x8b58;
	public static final int GL_BOOL_VEC2_ARB = 0x8b57;
	public static final int GL_BOOL_ARB = 0x8b56;
	public static final int GL_INT_VEC4_ARB = 0x8b55;
	public static final int GL_INT_VEC3_ARB = 0x8b54;
	public static final int GL_INT_VEC2_ARB = 0x8b53;
	public static final int GL_INT = 0x1404;
	public static final int GL_FLOAT_VEC4_ARB = 0x8b52;
	public static final int GL_FLOAT_VEC3_ARB = 0x8b51;
	public static final int GL_FLOAT_VEC2_ARB = 0x8b50;
	public static final int GL_FLOAT = 0x1406;
	public static final int GL_SHADER_OBJECT_ARB = 0x8b48;
	public static final int GL_OBJECT_SHADER_SOURCE_LENGTH_ARB = 0x8b88;
	public static final int GL_OBJECT_ACTIVE_UNIFORM_MAX_LENGTH_ARB = 0x8b87;
	public static final int GL_OBJECT_ACTIVE_UNIFORMS_ARB = 0x8b86;
	public static final int GL_OBJECT_ATTACHED_OBJECTS_ARB = 0x8b85;
	public static final int GL_OBJECT_INFO_LOG_LENGTH_ARB = 0x8b84;
	public static final int GL_OBJECT_VALIDATE_STATUS_ARB = 0x8b83;
	public static final int GL_OBJECT_LINK_STATUS_ARB = 0x8b82;
	public static final int GL_OBJECT_COMPILE_STATUS_ARB = 0x8b81;
	public static final int GL_OBJECT_DELETE_STATUS_ARB = 0x8b80;
	public static final int GL_OBJECT_SUBTYPE_ARB = 0x8b4f;
	public static final int GL_OBJECT_TYPE_ARB = 0x8b4e;
	public static final int GL_PROGRAM_OBJECT_ARB = 0x8b40;

	private ARBShaderObjects() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glGetShaderSourceARB(int obj, IntBuffer length, ByteBuffer source) {
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkDirect(source);
		nglGetShaderSourceARB(obj, (source.remaining()), length, length != null ? length.position() : 0, source, source.position());
	}
	private static native void nglGetShaderSourceARB(int obj, int maxLength, IntBuffer length, int length_position, ByteBuffer source, int source_position);

	public static void glGetUniformARB(int programObj, int location, IntBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetUniformivARB(programObj, location, params, params.position());
	}
	private static native void nglGetUniformivARB(int programObj, int location, IntBuffer params, int params_position);

	public static void glGetUniformARB(int programObj, int location, FloatBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetUniformfvARB(programObj, location, params, params.position());
	}
	private static native void nglGetUniformfvARB(int programObj, int location, FloatBuffer params, int params_position);

	public static void glGetActiveUniformARB(int programObj, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkBuffer(size, 1);
		BufferChecks.checkBuffer(type, 1);
		BufferChecks.checkDirect(name);
		nglGetActiveUniformARB(programObj, index, (name.remaining()), length, length != null ? length.position() : 0, size, size.position(), type, type.position(), name, name.position());
	}
	private static native void nglGetActiveUniformARB(int programObj, int index, int maxLength, IntBuffer length, int length_position, IntBuffer size, int size_position, IntBuffer type, int type_position, ByteBuffer name, int name_position);

	/**
	 * Returns the location of the uniform with the specified name. The ByteBuffer should contain the uniform name as a <b>null-terminated</b> string.
	 * @param programObj
	 * @param name
	 * @return
	 */
	public static int glGetUniformLocationARB(int programObj, ByteBuffer name) {
		BufferChecks.checkDirect(name);
		BufferChecks.checkNullTerminated(name);
		int __result = nglGetUniformLocationARB(programObj, name, name.position());
		return __result;
	}
	private static native int nglGetUniformLocationARB(int programObj, ByteBuffer name, int name_position);

	public static void glGetAttachedObjectsARB(int containerObj, IntBuffer count, IntBuffer obj) {
		if (count != null)
			BufferChecks.checkBuffer(count, 1);
		BufferChecks.checkDirect(obj);
		nglGetAttachedObjectsARB(containerObj, (obj.remaining()), count, count != null ? count.position() : 0, obj, obj.position());
	}
	private static native void nglGetAttachedObjectsARB(int containerObj, int maxCount, IntBuffer count, int count_position, IntBuffer obj, int obj_position);

	public static void glGetInfoLogARB(int obj, IntBuffer length, ByteBuffer infoLog) {
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkDirect(infoLog);
		nglGetInfoLogARB(obj, (infoLog.remaining()), length, length != null ? length.position() : 0, infoLog, infoLog.position());
	}
	private static native void nglGetInfoLogARB(int obj, int maxLength, IntBuffer length, int length_position, ByteBuffer infoLog, int infoLog_position);

	public static void glGetObjectParameterARB(int obj, int pname, IntBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetObjectParameterivARB(obj, pname, params, params.position());
	}
	private static native void nglGetObjectParameterivARB(int obj, int pname, IntBuffer params, int params_position);

	public static void glGetObjectParameterARB(int obj, int pname, FloatBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetObjectParameterfvARB(obj, pname, params, params.position());
	}
	private static native void nglGetObjectParameterfvARB(int obj, int pname, FloatBuffer params, int params_position);

	public static void glUniformMatrix4ARB(int location, boolean transpose, FloatBuffer matrices) {
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix4fvARB(location, (matrices.remaining()) >> 4, transpose, matrices, matrices.position());
	}
	private static native void nglUniformMatrix4fvARB(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position);

	public static void glUniformMatrix3ARB(int location, boolean transpose, FloatBuffer matrices) {
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix3fvARB(location, (matrices.remaining()) / (3 * 3), transpose, matrices, matrices.position());
	}
	private static native void nglUniformMatrix3fvARB(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position);

	public static void glUniformMatrix2ARB(int location, boolean transpose, FloatBuffer matrices) {
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix2fvARB(location, (matrices.remaining()) >> 2, transpose, matrices, matrices.position());
	}
	private static native void nglUniformMatrix2fvARB(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position);

	public static void glUniform4ARB(int location, IntBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform4ivARB(location, (values.remaining()) >> 2, values, values.position());
	}
	private static native void nglUniform4ivARB(int location, int count, IntBuffer values, int values_position);

	public static void glUniform3ARB(int location, IntBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform3ivARB(location, (values.remaining()) / 3, values, values.position());
	}
	private static native void nglUniform3ivARB(int location, int count, IntBuffer values, int values_position);

	public static void glUniform2ARB(int location, IntBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform2ivARB(location, (values.remaining()) >> 1, values, values.position());
	}
	private static native void nglUniform2ivARB(int location, int count, IntBuffer values, int values_position);

	public static void glUniform1ARB(int location, IntBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform1ivARB(location, (values.remaining()), values, values.position());
	}
	private static native void nglUniform1ivARB(int location, int count, IntBuffer values, int values_position);

	public static void glUniform4ARB(int location, FloatBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform4fvARB(location, (values.remaining()) >> 2, values, values.position());
	}
	private static native void nglUniform4fvARB(int location, int count, FloatBuffer values, int values_position);

	public static void glUniform3ARB(int location, FloatBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform3fvARB(location, (values.remaining()) / 3, values, values.position());
	}
	private static native void nglUniform3fvARB(int location, int count, FloatBuffer values, int values_position);

	public static void glUniform2ARB(int location, FloatBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform2fvARB(location, (values.remaining()) >> 1, values, values.position());
	}
	private static native void nglUniform2fvARB(int location, int count, FloatBuffer values, int values_position);

	public static void glUniform1ARB(int location, FloatBuffer values) {
		BufferChecks.checkDirect(values);
		nglUniform1fvARB(location, (values.remaining()), values, values.position());
	}
	private static native void nglUniform1fvARB(int location, int count, FloatBuffer values, int values_position);

	public static native void glUniform4iARB(int location, int v0, int v1, int v2, int v3);

	public static native void glUniform3iARB(int location, int v0, int v1, int v2);

	public static native void glUniform2iARB(int location, int v0, int v1);

	public static native void glUniform1iARB(int location, int v0);

	public static native void glUniform4fARB(int location, float v0, float v1, float v2, float v3);

	public static native void glUniform3fARB(int location, float v0, float v1, float v2);

	public static native void glUniform2fARB(int location, float v0, float v1);

	public static native void glUniform1fARB(int location, float v0);

	public static native void glValidateProgramARB(int programObj);

	public static native void glUseProgramObjectARB(int programObj);

	public static native void glLinkProgramARB(int programObj);

	public static native void glAttachObjectARB(int containerObj, int obj);

	public static native int glCreateProgramObjectARB();

	public static native void glCompileShaderARB(int shaderObj);

	/**
	 * The ARB_shader_objects extension allows multiple, optionally null-terminated, source strings to define a shader program.
	 * <p/>
	 * This method uses just a single string, that should NOT be null-terminated.
	 * @param shaderObj
	 * @param string
	 */
	public static void glShaderSourceARB(int shader, ByteBuffer string) {
		BufferChecks.checkDirect(string);
		nglShaderSourceARB(shader, 1, string, string.position(), (string.remaining()));
	}
	private static native void nglShaderSourceARB(int shader, int count, ByteBuffer string, int string_position, int length);

	public static native int glCreateShaderObjectARB(int shaderType);

	public static native void glDetachObjectARB(int containerObj, int attachedObj);

	public static native int glGetHandleARB(int pname);

	public static native void glDeleteObjectARB(int obj);
}
