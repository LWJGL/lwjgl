/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTGpuShader4 {
	/**
	 * Accepted by the &lt;pname&gt; parameters of GetVertexAttribdv,
	 * GetVertexAttribfv, GetVertexAttribiv, GetVertexAttribIivEXT, and
	 * GetVertexAttribIuivEXT:
	 */
	public static final int GL_VERTEX_ATTRIB_ARRAY_INTEGER_EXT = 0x88fd;
	/**
	 *Returned by the &lt;type&gt; parameter of GetActiveUniform: 
	 */
	public static final int GL_SAMPLER_1D_ARRAY_EXT = 0x8dc0;
	public static final int GL_SAMPLER_2D_ARRAY_EXT = 0x8dc1;
	public static final int GL_SAMPLER_BUFFER_EXT = 0x8dc2;
	public static final int GL_SAMPLER_1D_ARRAY_SHADOW_EXT = 0x8dc3;
	public static final int GL_SAMPLER_2D_ARRAY_SHADOW_EXT = 0x8dc4;
	public static final int GL_SAMPLER_CUBE_SHADOW_EXT = 0x8dc5;
	public static final int GL_UNSIGNED_INT = 0x1405;
	public static final int GL_UNSIGNED_INT_VEC2_EXT = 0x8dc6;
	public static final int GL_UNSIGNED_INT_VEC3_EXT = 0x8dc7;
	public static final int GL_UNSIGNED_INT_VEC4_EXT = 0x8dc8;
	public static final int GL_INT_SAMPLER_1D_EXT = 0x8dc9;
	public static final int GL_INT_SAMPLER_2D_EXT = 0x8dca;
	public static final int GL_INT_SAMPLER_3D_EXT = 0x8dcb;
	public static final int GL_INT_SAMPLER_CUBE_EXT = 0x8dcc;
	public static final int GL_INT_SAMPLER_2D_RECT_EXT = 0x8dcd;
	public static final int GL_INT_SAMPLER_1D_ARRAY_EXT = 0x8dce;
	public static final int GL_INT_SAMPLER_2D_ARRAY_EXT = 0x8dcf;
	public static final int GL_INT_SAMPLER_BUFFER_EXT = 0x8dd0;
	public static final int GL_UNSIGNED_INT_SAMPLER_1D_EXT = 0x8dd1;
	public static final int GL_UNSIGNED_INT_SAMPLER_2D_EXT = 0x8dd2;
	public static final int GL_UNSIGNED_INT_SAMPLER_3D_EXT = 0x8dd3;
	public static final int GL_UNSIGNED_INT_SAMPLER_CUBE_EXT = 0x8dd4;
	public static final int GL_UNSIGNED_INT_SAMPLER_2D_RECT_EXT = 0x8dd5;
	public static final int GL_UNSIGNED_INT_SAMPLER_1D_ARRAY_EXT = 0x8dd6;
	public static final int GL_UNSIGNED_INT_SAMPLER_2D_ARRAY_EXT = 0x8dd7;
	public static final int GL_UNSIGNED_INT_SAMPLER_BUFFER_EXT = 0x8dd8;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv,
	 * and GetDoublev:
	 */
	public static final int GL_MIN_PROGRAM_TEXEL_OFFSET_EXT = 0x8904;
	public static final int GL_MAX_PROGRAM_TEXEL_OFFSET_EXT = 0x8905;

	private EXTGpuShader4() {
	}


	public static void glVertexAttribI1iEXT(int index, int x) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI1iEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttribI1iEXT(index, x, function_pointer);
	}
	private static native void nglVertexAttribI1iEXT(int index, int x, long function_pointer);

	public static void glVertexAttribI2iEXT(int index, int x, int y) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI2iEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttribI2iEXT(index, x, y, function_pointer);
	}
	private static native void nglVertexAttribI2iEXT(int index, int x, int y, long function_pointer);

	public static void glVertexAttribI3iEXT(int index, int x, int y, int z) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI3iEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttribI3iEXT(index, x, y, z, function_pointer);
	}
	private static native void nglVertexAttribI3iEXT(int index, int x, int y, int z, long function_pointer);

	public static void glVertexAttribI4iEXT(int index, int x, int y, int z, int w) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI4iEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttribI4iEXT(index, x, y, z, w, function_pointer);
	}
	private static native void nglVertexAttribI4iEXT(int index, int x, int y, int z, int w, long function_pointer);

	public static void glVertexAttribI1uiEXT(int index, int x) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI1uiEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttribI1uiEXT(index, x, function_pointer);
	}
	private static native void nglVertexAttribI1uiEXT(int index, int x, long function_pointer);

	public static void glVertexAttribI2uiEXT(int index, int x, int y) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI2uiEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttribI2uiEXT(index, x, y, function_pointer);
	}
	private static native void nglVertexAttribI2uiEXT(int index, int x, int y, long function_pointer);

	public static void glVertexAttribI3uiEXT(int index, int x, int y, int z) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI3uiEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttribI3uiEXT(index, x, y, z, function_pointer);
	}
	private static native void nglVertexAttribI3uiEXT(int index, int x, int y, int z, long function_pointer);

	public static void glVertexAttribI4uiEXT(int index, int x, int y, int z, int w) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI4uiEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexAttribI4uiEXT(index, x, y, z, w, function_pointer);
	}
	private static native void nglVertexAttribI4uiEXT(int index, int x, int y, int z, int w, long function_pointer);

	public static void glVertexAttribI1EXT(int index, IntBuffer v) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI1ivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(v, 1);
		nglVertexAttribI1ivEXT(index, v, v.position(), function_pointer);
	}
	private static native void nglVertexAttribI1ivEXT(int index, IntBuffer v, int v_position, long function_pointer);

	public static void glVertexAttribI2EXT(int index, IntBuffer v) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI2ivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(v, 2);
		nglVertexAttribI2ivEXT(index, v, v.position(), function_pointer);
	}
	private static native void nglVertexAttribI2ivEXT(int index, IntBuffer v, int v_position, long function_pointer);

	public static void glVertexAttribI3EXT(int index, IntBuffer v) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI3ivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(v, 3);
		nglVertexAttribI3ivEXT(index, v, v.position(), function_pointer);
	}
	private static native void nglVertexAttribI3ivEXT(int index, IntBuffer v, int v_position, long function_pointer);

	public static void glVertexAttribI4EXT(int index, IntBuffer v) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI4ivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(v, 4);
		nglVertexAttribI4ivEXT(index, v, v.position(), function_pointer);
	}
	private static native void nglVertexAttribI4ivEXT(int index, IntBuffer v, int v_position, long function_pointer);

	public static void glVertexAttribI1uEXT(int index, IntBuffer v) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI1uivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(v, 1);
		nglVertexAttribI1uivEXT(index, v, v.position(), function_pointer);
	}
	private static native void nglVertexAttribI1uivEXT(int index, IntBuffer v, int v_position, long function_pointer);

	public static void glVertexAttribI2uEXT(int index, IntBuffer v) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI2uivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(v, 2);
		nglVertexAttribI2uivEXT(index, v, v.position(), function_pointer);
	}
	private static native void nglVertexAttribI2uivEXT(int index, IntBuffer v, int v_position, long function_pointer);

	public static void glVertexAttribI3uEXT(int index, IntBuffer v) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI3uivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(v, 3);
		nglVertexAttribI3uivEXT(index, v, v.position(), function_pointer);
	}
	private static native void nglVertexAttribI3uivEXT(int index, IntBuffer v, int v_position, long function_pointer);

	public static void glVertexAttribI4uEXT(int index, IntBuffer v) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI4uivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(v, 4);
		nglVertexAttribI4uivEXT(index, v, v.position(), function_pointer);
	}
	private static native void nglVertexAttribI4uivEXT(int index, IntBuffer v, int v_position, long function_pointer);

	public static void glVertexAttribI4EXT(int index, ByteBuffer v) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI4bvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(v, 4);
		nglVertexAttribI4bvEXT(index, v, v.position(), function_pointer);
	}
	private static native void nglVertexAttribI4bvEXT(int index, ByteBuffer v, int v_position, long function_pointer);

	public static void glVertexAttribI4EXT(int index, ShortBuffer v) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI4svEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(v, 4);
		nglVertexAttribI4svEXT(index, v, v.position(), function_pointer);
	}
	private static native void nglVertexAttribI4svEXT(int index, ShortBuffer v, int v_position, long function_pointer);

	public static void glVertexAttribI4uEXT(int index, ByteBuffer v) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI4ubvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(v, 4);
		nglVertexAttribI4ubvEXT(index, v, v.position(), function_pointer);
	}
	private static native void nglVertexAttribI4ubvEXT(int index, ByteBuffer v, int v_position, long function_pointer);

	public static void glVertexAttribI4uEXT(int index, ShortBuffer v) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribI4usvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(v, 4);
		nglVertexAttribI4usvEXT(index, v, v.position(), function_pointer);
	}
	private static native void nglVertexAttribI4usvEXT(int index, ShortBuffer v, int v_position, long function_pointer);

	public static void glVertexAttribIPointerEXT(int index, int size, int type, int stride, ByteBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribIPointerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).EXT_gpu_shader4_glVertexAttribIPointerEXT_buffer = buffer;
		nglVertexAttribIPointerEXT(index, size, type, stride, buffer, buffer.position(), function_pointer);
	}
	public static void glVertexAttribIPointerEXT(int index, int size, int type, int stride, IntBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribIPointerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).EXT_gpu_shader4_glVertexAttribIPointerEXT_buffer = buffer;
		nglVertexAttribIPointerEXT(index, size, type, stride, buffer, buffer.position() << 2, function_pointer);
	}
	public static void glVertexAttribIPointerEXT(int index, int size, int type, int stride, ShortBuffer buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribIPointerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(buffer);
		GLChecks.getReferences(caps).EXT_gpu_shader4_glVertexAttribIPointerEXT_buffer = buffer;
		nglVertexAttribIPointerEXT(index, size, type, stride, buffer, buffer.position() << 1, function_pointer);
	}
	private static native void nglVertexAttribIPointerEXT(int index, int size, int type, int stride, Buffer buffer, int buffer_position, long function_pointer);
	public static void glVertexAttribIPointerEXT(int index, int size, int type, int stride, long buffer_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glVertexAttribIPointerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOenabled(caps);
		nglVertexAttribIPointerEXTBO(index, size, type, stride, buffer_buffer_offset, function_pointer);
	}
	private static native void nglVertexAttribIPointerEXTBO(int index, int size, int type, int stride, long buffer_buffer_offset, long function_pointer);

	public static void glGetVertexAttribIEXT(int index, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glGetVertexAttribIivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetVertexAttribIivEXT(index, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetVertexAttribIivEXT(int index, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetVertexAttribIuEXT(int index, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glGetVertexAttribIuivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetVertexAttribIuivEXT(index, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetVertexAttribIuivEXT(int index, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glUniform1uiEXT(int location, int v0) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glUniform1uiEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform1uiEXT(location, v0, function_pointer);
	}
	private static native void nglUniform1uiEXT(int location, int v0, long function_pointer);

	public static void glUniform2uiEXT(int location, int v0, int v1) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glUniform2uiEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform2uiEXT(location, v0, v1, function_pointer);
	}
	private static native void nglUniform2uiEXT(int location, int v0, int v1, long function_pointer);

	public static void glUniform3uiEXT(int location, int v0, int v1, int v2) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glUniform3uiEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform3uiEXT(location, v0, v1, v2, function_pointer);
	}
	private static native void nglUniform3uiEXT(int location, int v0, int v1, int v2, long function_pointer);

	public static void glUniform4uiEXT(int location, int v0, int v1, int v2, int v3) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glUniform4uiEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniform4uiEXT(location, v0, v1, v2, v3, function_pointer);
	}
	private static native void nglUniform4uiEXT(int location, int v0, int v1, int v2, int v3, long function_pointer);

	public static void glUniform1uEXT(int location, IntBuffer value) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glUniform1uivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(value);
		nglUniform1uivEXT(location, (value.remaining()), value, value.position(), function_pointer);
	}
	private static native void nglUniform1uivEXT(int location, int count, IntBuffer value, int value_position, long function_pointer);

	public static void glUniform2uEXT(int location, IntBuffer value) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glUniform2uivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(value);
		nglUniform2uivEXT(location, (value.remaining()) >> 1, value, value.position(), function_pointer);
	}
	private static native void nglUniform2uivEXT(int location, int count, IntBuffer value, int value_position, long function_pointer);

	public static void glUniform3uEXT(int location, IntBuffer value) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glUniform3uivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(value);
		nglUniform3uivEXT(location, (value.remaining()) / 3, value, value.position(), function_pointer);
	}
	private static native void nglUniform3uivEXT(int location, int count, IntBuffer value, int value_position, long function_pointer);

	public static void glUniform4uEXT(int location, IntBuffer value) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glUniform4uivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(value);
		nglUniform4uivEXT(location, (value.remaining()) >> 2, value, value.position(), function_pointer);
	}
	private static native void nglUniform4uivEXT(int location, int count, IntBuffer value, int value_position, long function_pointer);

	public static void glGetUniformuEXT(int program, int location, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glGetUniformuivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(params);
		nglGetUniformuivEXT(program, location, params, params.position(), function_pointer);
	}
	private static native void nglGetUniformuivEXT(int program, int location, IntBuffer params, int params_position, long function_pointer);

	public static void glBindFragDataLocationEXT(int program, int colorNumber, ByteBuffer name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glBindFragDataLocationEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(name);
		BufferChecks.checkNullTerminated(name);
		nglBindFragDataLocationEXT(program, colorNumber, name, name.position(), function_pointer);
	}
	private static native void nglBindFragDataLocationEXT(int program, int colorNumber, ByteBuffer name, int name_position, long function_pointer);

	public static int glGetFragDataLocationEXT(int program, ByteBuffer name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_gpu_shader4_glGetFragDataLocationEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(name);
		BufferChecks.checkNullTerminated(name);
		int __result = nglGetFragDataLocationEXT(program, name, name.position(), function_pointer);
		return __result;
	}
	private static native int nglGetFragDataLocationEXT(int program, ByteBuffer name, int name_position, long function_pointer);
}
