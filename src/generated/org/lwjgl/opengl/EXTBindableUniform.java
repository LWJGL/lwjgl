/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTBindableUniform {
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv,
	 * and GetDoublev:
	 */
	public static final int GL_MAX_VERTEX_BINDABLE_UNIFORMS_EXT = 0x8de2;
	public static final int GL_MAX_FRAGMENT_BINDABLE_UNIFORMS_EXT = 0x8de3;
	public static final int GL_MAX_GEOMETRY_BINDABLE_UNIFORMS_EXT = 0x8de4;
	public static final int GL_MAX_BINDABLE_UNIFORM_SIZE_EXT = 0x8ded;
	public static final int GL_UNIFORM_BUFFER_BINDING_EXT = 0x8def;
	/**
	 * Accepted by the &lt;target&gt; parameters of BindBuffer, BufferData,
	 * BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData, and
	 * GetBufferPointerv:
	 */
	public static final int GL_UNIFORM_BUFFER_EXT = 0x8dee;

	private EXTBindableUniform() {
	}


	public static void glUniformBufferEXT(int program, int location, int buffer) {
		long function_pointer = GLContext.getCapabilities().EXT_bindable_uniform_glUniformBufferEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUniformBufferEXT(program, location, buffer, function_pointer);
	}
	private static native void nglUniformBufferEXT(int program, int location, int buffer, long function_pointer);

	public static int glGetUniformBufferSizeEXT(int program, int location) {
		long function_pointer = GLContext.getCapabilities().EXT_bindable_uniform_glGetUniformBufferSizeEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglGetUniformBufferSizeEXT(program, location, function_pointer);
		return __result;
	}
	private static native int nglGetUniformBufferSizeEXT(int program, int location, long function_pointer);

	public static long glGetUniformOffsetEXT(int program, int location) {
		long function_pointer = GLContext.getCapabilities().EXT_bindable_uniform_glGetUniformOffsetEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		long __result = nglGetUniformOffsetEXT(program, location, function_pointer);
		return __result;
	}
	private static native long nglGetUniformOffsetEXT(int program, int location, long function_pointer);
}
