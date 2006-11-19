/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class GL21 {
	/**
	 * Accepted by the &lt;target&gt; parameters of BindBuffer, BufferData,
	 * BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData,
	 * GetBufferParameteriv, and GetBufferPointerv:
	 */
	public static final int GL_PIXEL_PACK_BUFFER = 0x88eb;
	public static final int GL_PIXEL_UNPACK_BUFFER = 0x88ec;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	public static final int GL_PIXEL_PACK_BUFFER_BINDING = 0x88ed;
	public static final int GL_PIXEL_UNPACK_BUFFER_BINDING = 0x88ef;
	/**
	 * Accepted by the &lt;internalformat&gt; parameter of TexImage1D, TexImage2D,
	 * TexImage3D, CopyTexImage1D, CopyTexImage2D.
	 */
	public static final int GL_SRGB = 0x8c40;
	public static final int GL_SRGB8 = 0x8c41;
	public static final int GL_SRGB_ALPHA = 0x8c42;
	public static final int GL_SRGB8_ALPHA8 = 0x8c43;
	public static final int GL_SLUMINANCE_ALPHA = 0x8c44;
	public static final int GL_SLUMINANCE8_ALPHA8 = 0x8c45;
	public static final int GL_SLUMINANCE = 0x8c46;
	public static final int GL_SLUMINANCE8 = 0x8c47;
	public static final int GL_COMPRESSED_SRGB = 0x8c48;
	public static final int GL_COMPRESSED_SRGB_ALPHA = 0x8c49;
	public static final int GL_COMPRESSED_SLUMINANCE = 0x8c4a;
	public static final int GL_COMPRESSED_SLUMINANCE_ALPHA = 0x8c4b;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetIntegerv and GetFloatv.
	 */
	public static final int GL_CURRENT_RASTER_SECONDARY_COLOR = 0x845f;

	private GL21() {
	}


	public static void glUniformMatrix2x3(int location, boolean transpose, FloatBuffer matrices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL21_glUniformMatrix2x3fv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix2x3fv(location, (matrices.remaining()) / (2 * 3), transpose, matrices, matrices.position(), function_pointer);
	}
	private static native void nglUniformMatrix2x3fv(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position, long function_pointer);

	public static void glUniformMatrix3x2(int location, boolean transpose, FloatBuffer matrices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL21_glUniformMatrix3x2fv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix3x2fv(location, (matrices.remaining()) / (3 * 2), transpose, matrices, matrices.position(), function_pointer);
	}
	private static native void nglUniformMatrix3x2fv(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position, long function_pointer);

	public static void glUniformMatrix2x4(int location, boolean transpose, FloatBuffer matrices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL21_glUniformMatrix2x4fv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix2x4fv(location, (matrices.remaining()) >> 3, transpose, matrices, matrices.position(), function_pointer);
	}
	private static native void nglUniformMatrix2x4fv(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position, long function_pointer);

	public static void glUniformMatrix4x2(int location, boolean transpose, FloatBuffer matrices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL21_glUniformMatrix4x2fv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix4x2fv(location, (matrices.remaining()) >> 3, transpose, matrices, matrices.position(), function_pointer);
	}
	private static native void nglUniformMatrix4x2fv(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position, long function_pointer);

	public static void glUniformMatrix3x4(int location, boolean transpose, FloatBuffer matrices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL21_glUniformMatrix3x4fv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix3x4fv(location, (matrices.remaining()) / (3 * 4), transpose, matrices, matrices.position(), function_pointer);
	}
	private static native void nglUniformMatrix3x4fv(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position, long function_pointer);

	public static void glUniformMatrix4x3(int location, boolean transpose, FloatBuffer matrices) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL21_glUniformMatrix4x3fv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(matrices);
		nglUniformMatrix4x3fv(location, (matrices.remaining()) / (4 * 3), transpose, matrices, matrices.position(), function_pointer);
	}
	private static native void nglUniformMatrix4x3fv(int location, int count, boolean transpose, FloatBuffer matrices, int matrices_position, long function_pointer);
}
