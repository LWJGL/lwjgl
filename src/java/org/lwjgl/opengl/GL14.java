/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class GL14 {
	public static final int GL_GL_MIRRORED_REPEAT = 0x8370;
	public static final int GL_MAX_TEXTURE_LOD_BIAS = 0x84fd;
	public static final int GL_TEXTURE_LOD_BIAS = 0x8501;
	public static final int GL_TEXTURE_FILTER_CONTROL = 0x8500;
	public static final int GL_DECR_WRAP = 0x8508;
	public static final int GL_INCR_WRAP = 0x8507;
	public static final int GL_BLEND_SRC_ALPHA = 0x80cb;
	public static final int GL_BLEND_DST_ALPHA = 0x80ca;
	public static final int GL_BLEND_SRC_RGB = 0x80c9;
	public static final int GL_BLEND_DST_RGB = 0x80c8;
	public static final int GL_SECONDARY_COLOR_ARRAY = 0x845e;
	public static final int GL_SECONDARY_COLOR_ARRAY_POINTER = 0x845d;
	public static final int GL_SECONDARY_COLOR_ARRAY_STRIDE = 0x845c;
	public static final int GL_SECONDARY_COLOR_ARRAY_TYPE = 0x845b;
	public static final int GL_SECONDARY_COLOR_ARRAY_SIZE = 0x845a;
	public static final int GL_CURRENT_SECONDARY_COLOR = 0x8459;
	public static final int GL_COLOR_SUM = 0x8458;
	public static final int GL_POINT_DISTANCE_ATTENUATION = 0x8129;
	public static final int GL_POINT_FADE_THRESHOLD_SIZE = 0x8128;
	public static final int GL_POINT_SIZE_MAX = 0x8127;
	public static final int GL_POINT_SIZE_MIN = 0x8126;
	public static final int GL_FOG_COORDINATE_ARRAY = 0x8457;
	public static final int GL_FOG_COORDINATE_ARRAY_POINTER = 0x8456;
	public static final int GL_FOG_COORDINATE_ARRAY_STRIDE = 0x8455;
	public static final int GL_FOG_COORDINATE_ARRAY_TYPE = 0x8454;
	public static final int GL_CURRENT_FOG_COORDINATE = 0x8453;
	public static final int GL_FRAGMENT_DEPTH = 0x8452;
	public static final int GL_FOG_COORDINATE = 0x8451;
	public static final int GL_FOG_COORDINATE_SOURCE = 0x8450;
	public static final int GL_COMPARE_R_TO_TEXTURE = 0x884e;
	public static final int GL_TEXTURE_COMPARE_FUNC = 0x884d;
	public static final int GL_TEXTURE_COMPARE_MODE = 0x884c;
	public static final int GL_DEPTH_TEXTURE_MODE = 0x884b;
	public static final int GL_TEXTURE_DEPTH_SIZE = 0x884a;
	public static final int GL_DEPTH_COMPONENT32 = 0x81a7;
	public static final int GL_DEPTH_COMPONENT24 = 0x81a6;
	public static final int GL_DEPTH_COMPONENT16 = 0x81a5;
	public static final int GL_GENERATE_MIPMAP_HINT = 0x8192;
	public static final int GL_GENERATE_MIPMAP = 0x8191;

	private GL14() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glWindowPos3i(int x, int y, int z);

	public static native void glWindowPos3f(float x, float y, float z);

	public static native void glWindowPos2i(int x, int y);

	public static native void glWindowPos2f(float x, float y);

	public static native void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha);

	public static void glSecondaryColorPointer(int size, int stride, FloatBuffer data) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(data);
		nglSecondaryColorPointer(size, GL11.GL_FLOAT, stride, data, data.position() << 2);
	}
	public static void glSecondaryColorPointer(int size, boolean unsigned, int stride, ByteBuffer data) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(data);
		nglSecondaryColorPointer(size, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, stride, data, data.position());
	}
	private static native void nglSecondaryColorPointer(int size, int type, int stride, Buffer data, int data_position);
	public static void glSecondaryColorPointer(int size, int type, int stride, int data_buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglSecondaryColorPointerBO(size, type, stride, data_buffer_offset);
	}
	private static native void nglSecondaryColorPointerBO(int size, int type, int stride, int data_buffer_offset);

	public static native void glSecondaryColor3ub(byte red, byte green, byte blue);

	public static native void glSecondaryColor3f(float red, float green, float blue);

	public static native void glSecondaryColor3b(byte red, byte green, byte blue);

	public static void glPointParameter(int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglPointParameterfv(pname, params, params.position());
	}
	private static native void nglPointParameterfv(int pname, FloatBuffer params, int params_position);

	public static void glPointParameter(int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglPointParameteriv(pname, params, params.position());
	}
	private static native void nglPointParameteriv(int pname, IntBuffer params, int params_position);

	public static native void glPointParameterf(int pname, float param);

	public static native void glPointParameteri(int pname, int param);

	public static void glMultiDrawArrays(int mode, IntBuffer piFirst, IntBuffer piCount) {
		BufferChecks.checkDirect(piFirst);
		BufferChecks.checkDirect(piCount);
		if (piFirst.remaining() != piCount.remaining()) {
			throw new IllegalArgumentException("piFirst.remaining() != piCount.remaining()");
		}
		nglMultiDrawArrays(mode, piFirst, piFirst.position(), piCount, piCount.position(), (piFirst.remaining()));
	}
	private static native void nglMultiDrawArrays(int mode, IntBuffer piFirst, int piFirst_position, IntBuffer piCount, int piCount_position, int primcount);

	public static void glFogCoordPointer(int stride, FloatBuffer data) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(data);
		nglFogCoordPointer(GL11.GL_FLOAT, stride, data, data.position() << 2);
	}
	private static native void nglFogCoordPointer(int type, int stride, Buffer data, int data_position);
	public static void glFogCoordPointer(int type, int stride, int data_buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglFogCoordPointerBO(type, stride, data_buffer_offset);
	}
	private static native void nglFogCoordPointerBO(int type, int stride, int data_buffer_offset);

	public static native void glFogCoordf(float coord);

	public static native void glBlendColor(float red, float green, float blue, float alpha);

	public static native void glBlendEquation(int mode);
}
