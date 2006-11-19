/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTFogCoord {
	public static final int GL_FOG_COORDINATE_SOURCE_EXT = 0x8450;
	public static final int GL_FOG_COORDINATE_EXT = 0x8451;
	public static final int GL_FRAGMENT_DEPTH_EXT = 0x8452;
	public static final int GL_CURRENT_FOG_COORDINATE_EXT = 0x8453;
	public static final int GL_FOG_COORDINATE_ARRAY_TYPE_EXT = 0x8454;
	public static final int GL_FOG_COORDINATE_ARRAY_STRIDE_EXT = 0x8455;
	public static final int GL_FOG_COORDINATE_ARRAY_POINTER_EXT = 0x8456;
	public static final int GL_FOG_COORDINATE_ARRAY_EXT = 0x8457;

	private EXTFogCoord() {
	}


	public static void glFogCoordfEXT(float coord) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_fog_coord_glFogCoordfEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFogCoordfEXT(coord, function_pointer);
	}
	private static native void nglFogCoordfEXT(float coord, long function_pointer);

	public static void glFogCoorddEXT(double coord) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_fog_coord_glFogCoorddEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFogCoorddEXT(coord, function_pointer);
	}
	private static native void nglFogCoorddEXT(double coord, long function_pointer);

	public static void glFogCoordPointerEXT(int stride, DoubleBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_fog_coord_glFogCoordPointerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(data);
		GLChecks.getReferences(caps).EXT_fog_coord_glFogCoordPointerEXT_data = data;
		nglFogCoordPointerEXT(GL11.GL_DOUBLE, stride, data, data.position() << 3, function_pointer);
	}
	public static void glFogCoordPointerEXT(int stride, FloatBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_fog_coord_glFogCoordPointerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(data);
		GLChecks.getReferences(caps).EXT_fog_coord_glFogCoordPointerEXT_data = data;
		nglFogCoordPointerEXT(GL11.GL_FLOAT, stride, data, data.position() << 2, function_pointer);
	}
	private static native void nglFogCoordPointerEXT(int type, int stride, Buffer data, int data_position, long function_pointer);
	public static void glFogCoordPointerEXT(int type, int stride, long data_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_fog_coord_glFogCoordPointerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOenabled(caps);
		nglFogCoordPointerEXTBO(type, stride, data_buffer_offset, function_pointer);
	}
	private static native void nglFogCoordPointerEXTBO(int type, int stride, long data_buffer_offset, long function_pointer);
}
