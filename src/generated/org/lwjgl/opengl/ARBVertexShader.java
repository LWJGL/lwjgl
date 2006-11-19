/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBVertexShader {
	/**
	 * Accepted by the &lt;shaderType&gt; argument of CreateShaderObjectARB and
	 * returned by the &lt;params&gt; parameter of GetObjectParameter{if}vARB:
	 */
	public static final int GL_VERTEX_SHADER_ARB = 0x8b31;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	public static final int GL_MAX_VERTEX_UNIFORM_COMPONENTS_ARB = 0x8b4a;
	public static final int GL_MAX_VARYING_FLOATS_ARB = 0x8b4b;
	public static final int GL_MAX_VERTEX_ATTRIBS_ARB = 0x8869;
	public static final int GL_MAX_TEXTURE_IMAGE_UNITS_ARB = 0x8872;
	public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS_ARB = 0x8b4c;
	public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS_ARB = 0x8b4d;
	public static final int GL_MAX_TEXTURE_COORDS_ARB = 0x8871;
	/**
	 * Accepted by the &lt;cap&gt; parameter of Disable, Enable, and IsEnabled, and
	 * by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev:
	 */
	public static final int GL_VERTEX_PROGRAM_POINT_SIZE_ARB = 0x8642;
	public static final int GL_VERTEX_PROGRAM_TWO_SIDE_ARB = 0x8643;
	/**
	 * Accepted by the &lt;pname&gt; parameter GetObjectParameter{if}vARB:
	 */
	public static final int GL_OBJECT_ACTIVE_ATTRIBUTES_ARB = 0x8b89;
	public static final int GL_OBJECT_ACTIVE_ATTRIBUTE_MAX_LENGTH_ARB = 0x8b8a;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetVertexAttrib{dfi}vARB:
	 */
	public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED_ARB = 0x8622;
	public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE_ARB = 0x8623;
	public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE_ARB = 0x8624;
	public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE_ARB = 0x8625;
	public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB = 0x886a;
	public static final int GL_CURRENT_VERTEX_ATTRIB_ARB = 0x8626;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetVertexAttribPointervARB:
	 */
	public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER_ARB = 0x8645;
	/**
	 * Returned by the &lt;type&gt; parameter of GetActiveAttribARB:
	 */
	public static final int GL_FLOAT = 0x1406;
	public static final int GL_FLOAT_VEC2_ARB = 0x8b50;
	public static final int GL_FLOAT_VEC3_ARB = 0x8b51;
	public static final int GL_FLOAT_VEC4_ARB = 0x8b52;
	public static final int GL_FLOAT_MAT2_ARB = 0x8b5a;
	public static final int GL_FLOAT_MAT3_ARB = 0x8b5b;
	public static final int GL_FLOAT_MAT4_ARB = 0x8b5c;

	private ARBVertexShader() {
	}


	public static void glVertexAttrib1sARB(int index, short v0) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttrib1sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib1sARB(index, v0, function_pointer);
	}
	private static native void nglVertexAttrib1sARB(int index, short v0, long function_pointer);

	public static void glVertexAttrib1fARB(int index, float v0) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttrib1fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib1fARB(index, v0, function_pointer);
	}
	private static native void nglVertexAttrib1fARB(int index, float v0, long function_pointer);

	public static void glVertexAttrib1dARB(int index, double v0) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttrib1dARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib1dARB(index, v0, function_pointer);
	}
	private static native void nglVertexAttrib1dARB(int index, double v0, long function_pointer);

	public static void glVertexAttrib2sARB(int index, short v0, short v1) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttrib2sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib2sARB(index, v0, v1, function_pointer);
	}
	private static native void nglVertexAttrib2sARB(int index, short v0, short v1, long function_pointer);

	public static void glVertexAttrib2fARB(int index, float v0, float v1) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttrib2fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib2fARB(index, v0, v1, function_pointer);
	}
	private static native void nglVertexAttrib2fARB(int index, float v0, float v1, long function_pointer);

	public static void glVertexAttrib2dARB(int index, double v0, double v1) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttrib2dARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib2dARB(index, v0, v1, function_pointer);
	}
	private static native void nglVertexAttrib2dARB(int index, double v0, double v1, long function_pointer);

	public static void glVertexAttrib3sARB(int index, short v0, short v1, short v2) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttrib3sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib3sARB(index, v0, v1, v2, function_pointer);
	}
	private static native void nglVertexAttrib3sARB(int index, short v0, short v1, short v2, long function_pointer);

	public static void glVertexAttrib3fARB(int index, float v0, float v1, float v2) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttrib3fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib3fARB(index, v0, v1, v2, function_pointer);
	}
	private static native void nglVertexAttrib3fARB(int index, float v0, float v1, float v2, long function_pointer);

	public static void glVertexAttrib3dARB(int index, double v0, double v1, double v2) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttrib3dARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib3dARB(index, v0, v1, v2, function_pointer);
	}
	private static native void nglVertexAttrib3dARB(int index, double v0, double v1, double v2, long function_pointer);

	public static void glVertexAttrib4sARB(int index, short v0, short v1, short v2, short v3) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttrib4sARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib4sARB(index, v0, v1, v2, v3, function_pointer);
	}
	private static native void nglVertexAttrib4sARB(int index, short v0, short v1, short v2, short v3, long function_pointer);

	public static void glVertexAttrib4fARB(int index, float v0, float v1, float v2, float v3) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttrib4fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib4fARB(index, v0, v1, v2, v3, function_pointer);
	}
	private static native void nglVertexAttrib4fARB(int index, float v0, float v1, float v2, float v3, long function_pointer);

	public static void glVertexAttrib4dARB(int index, double v0, double v1, double v2, double v3) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttrib4dARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib4dARB(index, v0, v1, v2, v3, function_pointer);
	}
	private static native void nglVertexAttrib4dARB(int index, double v0, double v1, double v2, double v3, long function_pointer);

	public static void glVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttrib4NubARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttrib4NubARB(index, x, y, z, w, function_pointer);
	}
	private static native void nglVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w, long function_pointer);

	public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, DoubleBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).ARB_vertex_shader_glVertexAttribPointerARB_buffer = buffer;
		nglVertexAttribPointerARB(index, size, GL11.GL_DOUBLE, normalized, stride, buffer, buffer.position() << 3, function_pointer);
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, FloatBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).ARB_vertex_shader_glVertexAttribPointerARB_buffer = buffer;
		nglVertexAttribPointerARB(index, size, GL11.GL_FLOAT, normalized, stride, buffer, buffer.position() << 2, function_pointer);
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).ARB_vertex_shader_glVertexAttribPointerARB_buffer = buffer;
		nglVertexAttribPointerARB(index, size, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, normalized, stride, buffer, buffer.position(), function_pointer);
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).ARB_vertex_shader_glVertexAttribPointerARB_buffer = buffer;
		nglVertexAttribPointerARB(index, size, unsigned ? GL11.GL_UNSIGNED_INT : GL11.GL_INT, normalized, stride, buffer, buffer.position() << 2, function_pointer);
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).ARB_vertex_shader_glVertexAttribPointerARB_buffer = buffer;
		nglVertexAttribPointerARB(index, size, unsigned ? GL11.GL_UNSIGNED_SHORT : GL11.GL_SHORT, normalized, stride, buffer, buffer.position() << 1, function_pointer);
	}
	private static native void nglVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, Buffer buffer, int buffer_position, long function_pointer);
	public static void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, long buffer_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glVertexAttribPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOenabled(caps);
		nglVertexAttribPointerARBBO(index, size, type, normalized, stride, buffer_buffer_offset, function_pointer);
	}
	private static native void nglVertexAttribPointerARBBO(int index, int size, int type, boolean normalized, int stride, long buffer_buffer_offset, long function_pointer);

	public static void glEnableVertexAttribArrayARB(int index) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glEnableVertexAttribArrayARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglEnableVertexAttribArrayARB(index, function_pointer);
	}
	private static native void nglEnableVertexAttribArrayARB(int index, long function_pointer);

	public static void glDisableVertexAttribArrayARB(int index) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glDisableVertexAttribArrayARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDisableVertexAttribArrayARB(index, function_pointer);
	}
	private static native void nglDisableVertexAttribArrayARB(int index, long function_pointer);

	public static void glBindAttribLocationARB(int programObj, int index, ByteBuffer name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glBindAttribLocationARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(name);
		BufferChecks.checkNullTerminated(name);
		nglBindAttribLocationARB(programObj, index, name, name.position(), function_pointer);
	}
	private static native void nglBindAttribLocationARB(int programObj, int index, ByteBuffer name, int name_position, long function_pointer);

	public static void glGetActiveAttribARB(int programObj, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glGetActiveAttribARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (length != null)
			BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkBuffer(size, 1);
		BufferChecks.checkBuffer(type, 1);
		BufferChecks.checkDirect(name);
		nglGetActiveAttribARB(programObj, index, (name.remaining()), length, length != null ? length.position() : 0, size, size.position(), type, type.position(), name, name.position(), function_pointer);
	}
	private static native void nglGetActiveAttribARB(int programObj, int index, int maxLength, IntBuffer length, int length_position, IntBuffer size, int size_position, IntBuffer type, int type_position, ByteBuffer name, int name_position, long function_pointer);

	public static int glGetAttribLocationARB(int programObj, ByteBuffer name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glGetAttribLocationARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(name);
		BufferChecks.checkNullTerminated(name);
		int __result = nglGetAttribLocationARB(programObj, name, name.position(), function_pointer);
		return __result;
	}
	private static native int nglGetAttribLocationARB(int programObj, ByteBuffer name, int name_position, long function_pointer);

	public static void glGetVertexAttribARB(int index, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glGetVertexAttribfvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetVertexAttribfvARB(index, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetVertexAttribfvARB(int index, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetVertexAttribARB(int index, int pname, DoubleBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glGetVertexAttribdvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetVertexAttribdvARB(index, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetVertexAttribdvARB(int index, int pname, DoubleBuffer params, int params_position, long function_pointer);

	public static void glGetVertexAttribARB(int index, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glGetVertexAttribivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetVertexAttribivARB(index, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetVertexAttribivARB(int index, int pname, IntBuffer params, int params_position, long function_pointer);

	public static java.nio.ByteBuffer glGetVertexAttribPointerARB(int index, int pname, int result_size) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_shader_glGetVertexAttribPointervARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		java.nio.ByteBuffer __result = nglGetVertexAttribPointervARB(index, pname, result_size, function_pointer);
		return __result;
	}
	private static native java.nio.ByteBuffer nglGetVertexAttribPointervARB(int index, int pname, int result_size, long function_pointer);
}
