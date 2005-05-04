/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferChecks;

public final class EXTFogCoord {
	public static final int GL_FOG_COORDINATE_ARRAY_EXT = 0x8457;
	public static final int GL_FOG_COORDINATE_ARRAY_POINTER_EXT = 0x8456;
	public static final int GL_FOG_COORDINATE_ARRAY_STRIDE_EXT = 0x8455;
	public static final int GL_FOG_COORDINATE_ARRAY_TYPE_EXT = 0x8454;
	public static final int GL_CURRENT_FOG_COORDINATE_EXT = 0x8453;
	public static final int GL_FRAGMENT_DEPTH_EXT = 0x8452;
	public static final int GL_FOG_COORDINATE_EXT = 0x8451;
	public static final int GL_FOG_COORDINATE_SOURCE_EXT = 0x8450;

	private EXTFogCoord() {
	}


	public static void glFogCoordPointerEXT(int stride, FloatBuffer data) {
		long function_pointer = GLContext.getCapabilities().EXT_fog_coord_glFogCoordPointerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(data);
		GLChecks.getReferences().EXT_fog_coord_glFogCoordPointerEXT_data = data;
		nglFogCoordPointerEXT(GL11.GL_FLOAT, stride, data, data.position() << 2, function_pointer);
	}
	private static native void nglFogCoordPointerEXT(int type, int stride, Buffer data, int data_position, long function_pointer);
	public static void glFogCoordPointerEXT(int type, int stride, int data_buffer_offset) {
		long function_pointer = GLContext.getCapabilities().EXT_fog_coord_glFogCoordPointerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOenabled();
		nglFogCoordPointerEXTBO(type, stride, data_buffer_offset, function_pointer);
	}
	private static native void nglFogCoordPointerEXTBO(int type, int stride, int data_buffer_offset, long function_pointer);

	public static void glFogCoordfEXT(float coord) {
		long function_pointer = GLContext.getCapabilities().EXT_fog_coord_glFogCoordfEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFogCoordfEXT(coord, function_pointer);
	}
	private static native void nglFogCoordfEXT(float coord, long function_pointer);
}
