/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferChecks;

public final class ARBVertexShader {
	public static final int GL_FLOAT_MAT4_ARB = 0x8b5c;
	public static final int GL_FLOAT_MAT3_ARB = 0x8b5b;
	public static final int GL_FLOAT_MAT2_ARB = 0x8b5a;
	public static final int GL_FLOAT_VEC4_ARB = 0x8b52;
	public static final int GL_FLOAT_VEC3_ARB = 0x8b51;
	public static final int GL_FLOAT_VEC2_ARB = 0x8b50;
	public static final int GL_FLOAT = 0x1406;
	public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER_ARB = 0x8645;
	public static final int GL_CURRENT_VERTEX_ATTRIB_ARB = 0x8626;
	public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB = 0x886a;
	public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE_ARB = 0x8625;
	public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE_ARB = 0x8624;
	public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE_ARB = 0x8623;
	public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED_ARB = 0x8622;
	public static final int GL_OBJECT_ACTIVE_ATTRIBUTE_MAX_LENGTH_ARB = 0x8b8a;
	public static final int GL_OBJECT_ACTIVE_ATTRIBUTES_ARB = 0x8b89;
	public static final int GL_VERTEX_PROGRAM_TWO_SIDE_ARB = 0x8643;
	public static final int GL_VERTEX_PROGRAM_POINT_SIZE_ARB = 0x8642;
	public static final int GL_MAX_TEXTURE_COORDS_ARB = 0x8871;
	public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS_ARB = 0x8b4d;
	public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS_ARB = 0x8b4c;
	public static final int GL_MAX_TEXTURE_IMAGE_UNITS_ARB = 0x8872;
	public static final int GL_MAX_VERTEX_ATTRIBS_ARB = 0x8869;
	public static final int GL_MAX_VARYING_FLOATS_ARB = 0x8b4b;
	public static final int GL_MAX_VERTEX_UNIFORM_COMPONENTS_ARB = 0x8b4a;
	public static final int GL_VERTEX_SHADER_ARB = 0x8b31;

	private ARBVertexShader() {
	}


	public static int glGetAttribLocationARB(int programObj, ByteBuffer name) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_shader_glGetAttribLocationARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(name);
		BufferChecks.checkNullTerminated(name);
		int __result = nglGetAttribLocationARB(programObj, name, name.position(), function_pointer);
		return __result;
	}
	private static native int nglGetAttribLocationARB(int programObj, ByteBuffer name, int name_position, long function_pointer);

	public static void glGetActiveAttribARB(int programObj, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_shader_glGetActiveAttribARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkBuffer(size, 1);
		BufferChecks.checkBuffer(type, 1);
		BufferChecks.checkDirect(name);
		nglGetActiveAttribARB(programObj, index, (name.remaining()), length, length != null ? length.position() : 0, size, size.position(), type, type.position(), name, name.position(), function_pointer);
	}
	private static native void nglGetActiveAttribARB(int programObj, int index, int maxLength, IntBuffer length, int length_position, IntBuffer size, int size_position, IntBuffer type, int type_position, ByteBuffer name, int name_position, long function_pointer);

	public static void glBindAttribLocationARB(int programObj, int index, ByteBuffer name) {
		long function_pointer = GLContext.getCapabilities().ARB_vertex_shader_glBindAttribLocationARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(name);
		BufferChecks.checkNullTerminated(name);
		nglBindAttribLocationARB(programObj, index, name, name.position(), function_pointer);
	}
	private static native void nglBindAttribLocationARB(int programObj, int index, ByteBuffer name, int name_position, long function_pointer);
}
