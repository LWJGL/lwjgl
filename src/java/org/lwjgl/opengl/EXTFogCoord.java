/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

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

	static native void initNativeStubs() throws LWJGLException;

	public static void glFogCoordPointerEXT(int stride, FloatBuffer data) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(data);
		nglFogCoordPointerEXT(GL11.GL_FLOAT, stride, data, data.position() << 2);
	}
	private static native void nglFogCoordPointerEXT(int type, int stride, Buffer data, int data_position);
	public static void glFogCoordPointerEXT(int type, int stride, int data_buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglFogCoordPointerEXTBO(type, stride, data_buffer_offset);
	}
	private static native void nglFogCoordPointerEXTBO(int type, int stride, int data_buffer_offset);

	public static native void glFogCoordfEXT(float coord);
}
