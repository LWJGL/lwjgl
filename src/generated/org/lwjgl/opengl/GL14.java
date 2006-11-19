/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class GL14 {
	public static final int GL_GENERATE_MIPMAP = 0x8191;
	public static final int GL_GENERATE_MIPMAP_HINT = 0x8192;
	public static final int GL_DEPTH_COMPONENT16 = 0x81a5;
	public static final int GL_DEPTH_COMPONENT24 = 0x81a6;
	public static final int GL_DEPTH_COMPONENT32 = 0x81a7;
	public static final int GL_TEXTURE_DEPTH_SIZE = 0x884a;
	public static final int GL_DEPTH_TEXTURE_MODE = 0x884b;
	public static final int GL_TEXTURE_COMPARE_MODE = 0x884c;
	public static final int GL_TEXTURE_COMPARE_FUNC = 0x884d;
	public static final int GL_COMPARE_R_TO_TEXTURE = 0x884e;
	public static final int GL_FOG_COORDINATE_SOURCE = 0x8450;
	public static final int GL_FOG_COORDINATE = 0x8451;
	public static final int GL_FRAGMENT_DEPTH = 0x8452;
	public static final int GL_CURRENT_FOG_COORDINATE = 0x8453;
	public static final int GL_FOG_COORDINATE_ARRAY_TYPE = 0x8454;
	public static final int GL_FOG_COORDINATE_ARRAY_STRIDE = 0x8455;
	public static final int GL_FOG_COORDINATE_ARRAY_POINTER = 0x8456;
	public static final int GL_FOG_COORDINATE_ARRAY = 0x8457;
	public static final int GL_POINT_SIZE_MIN = 0x8126;
	public static final int GL_POINT_SIZE_MAX = 0x8127;
	public static final int GL_POINT_FADE_THRESHOLD_SIZE = 0x8128;
	public static final int GL_POINT_DISTANCE_ATTENUATION = 0x8129;
	public static final int GL_COLOR_SUM = 0x8458;
	public static final int GL_CURRENT_SECONDARY_COLOR = 0x8459;
	public static final int GL_SECONDARY_COLOR_ARRAY_SIZE = 0x845a;
	public static final int GL_SECONDARY_COLOR_ARRAY_TYPE = 0x845b;
	public static final int GL_SECONDARY_COLOR_ARRAY_STRIDE = 0x845c;
	public static final int GL_SECONDARY_COLOR_ARRAY_POINTER = 0x845d;
	public static final int GL_SECONDARY_COLOR_ARRAY = 0x845e;
	public static final int GL_BLEND_DST_RGB = 0x80c8;
	public static final int GL_BLEND_SRC_RGB = 0x80c9;
	public static final int GL_BLEND_DST_ALPHA = 0x80ca;
	public static final int GL_BLEND_SRC_ALPHA = 0x80cb;
	public static final int GL_INCR_WRAP = 0x8507;
	public static final int GL_DECR_WRAP = 0x8508;
	public static final int GL_TEXTURE_FILTER_CONTROL = 0x8500;
	public static final int GL_TEXTURE_LOD_BIAS = 0x8501;
	public static final int GL_MAX_TEXTURE_LOD_BIAS = 0x84fd;
	public static final int GL_GL_MIRRORED_REPEAT = 0x8370;

	private GL14() {
	}


	public static void glBlendEquation(int mode) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glBlendEquation_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendEquation(mode, function_pointer);
	}
	private static native void nglBlendEquation(int mode, long function_pointer);

	public static void glBlendColor(float red, float green, float blue, float alpha) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glBlendColor_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendColor(red, green, blue, alpha, function_pointer);
	}
	private static native void nglBlendColor(float red, float green, float blue, float alpha, long function_pointer);

	public static void glFogCoordf(float coord) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glFogCoordf_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFogCoordf(coord, function_pointer);
	}
	private static native void nglFogCoordf(float coord, long function_pointer);

	public static void glFogCoordd(double coord) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glFogCoordd_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFogCoordd(coord, function_pointer);
	}
	private static native void nglFogCoordd(double coord, long function_pointer);

	public static void glFogCoordPointer(int stride, DoubleBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glFogCoordPointer_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(data);
		GLChecks.getReferences(caps).GL14_glFogCoordPointer_data = data;
		nglFogCoordPointer(GL11.GL_DOUBLE, stride, data, data.position() << 3, function_pointer);
	}
	public static void glFogCoordPointer(int stride, FloatBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glFogCoordPointer_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(data);
		GLChecks.getReferences(caps).GL14_glFogCoordPointer_data = data;
		nglFogCoordPointer(GL11.GL_FLOAT, stride, data, data.position() << 2, function_pointer);
	}
	private static native void nglFogCoordPointer(int type, int stride, Buffer data, int data_position, long function_pointer);
	public static void glFogCoordPointer(int type, int stride, long data_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glFogCoordPointer_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOenabled(caps);
		nglFogCoordPointerBO(type, stride, data_buffer_offset, function_pointer);
	}
	private static native void nglFogCoordPointerBO(int type, int stride, long data_buffer_offset, long function_pointer);

	public static void glMultiDrawArrays(int mode, IntBuffer piFirst, IntBuffer piCount) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glMultiDrawArrays_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (piFirst.remaining() != piCount.remaining()) {
			throw new IllegalArgumentException("piFirst.remaining() != piCount.remaining()");
		}
		BufferChecks.checkDirect(piFirst);
		BufferChecks.checkDirect(piCount);
		nglMultiDrawArrays(mode, piFirst, piFirst.position(), piCount, piCount.position(), (piFirst.remaining()), function_pointer);
	}
	private static native void nglMultiDrawArrays(int mode, IntBuffer piFirst, int piFirst_position, IntBuffer piCount, int piCount_position, int primcount, long function_pointer);

	public static void glPointParameteri(int pname, int param) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glPointParameteri_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglPointParameteri(pname, param, function_pointer);
	}
	private static native void nglPointParameteri(int pname, int param, long function_pointer);

	public static void glPointParameterf(int pname, float param) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glPointParameterf_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglPointParameterf(pname, param, function_pointer);
	}
	private static native void nglPointParameterf(int pname, float param, long function_pointer);

	public static void glPointParameter(int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glPointParameteriv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglPointParameteriv(pname, params, params.position(), function_pointer);
	}
	private static native void nglPointParameteriv(int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glPointParameter(int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glPointParameterfv_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglPointParameterfv(pname, params, params.position(), function_pointer);
	}
	private static native void nglPointParameterfv(int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glSecondaryColor3b(byte red, byte green, byte blue) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glSecondaryColor3b_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglSecondaryColor3b(red, green, blue, function_pointer);
	}
	private static native void nglSecondaryColor3b(byte red, byte green, byte blue, long function_pointer);

	public static void glSecondaryColor3f(float red, float green, float blue) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glSecondaryColor3f_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglSecondaryColor3f(red, green, blue, function_pointer);
	}
	private static native void nglSecondaryColor3f(float red, float green, float blue, long function_pointer);

	public static void glSecondaryColor3d(double red, double green, double blue) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glSecondaryColor3d_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglSecondaryColor3d(red, green, blue, function_pointer);
	}
	private static native void nglSecondaryColor3d(double red, double green, double blue, long function_pointer);

	public static void glSecondaryColor3ub(byte red, byte green, byte blue) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glSecondaryColor3ub_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglSecondaryColor3ub(red, green, blue, function_pointer);
	}
	private static native void nglSecondaryColor3ub(byte red, byte green, byte blue, long function_pointer);

	public static void glSecondaryColorPointer(int size, int stride, DoubleBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glSecondaryColorPointer_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(data);
		nglSecondaryColorPointer(size, GL11.GL_DOUBLE, stride, data, data.position() << 3, function_pointer);
	}
	public static void glSecondaryColorPointer(int size, int stride, FloatBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glSecondaryColorPointer_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(data);
		nglSecondaryColorPointer(size, GL11.GL_FLOAT, stride, data, data.position() << 2, function_pointer);
	}
	public static void glSecondaryColorPointer(int size, boolean unsigned, int stride, ByteBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glSecondaryColorPointer_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(data);
		nglSecondaryColorPointer(size, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, stride, data, data.position(), function_pointer);
	}
	private static native void nglSecondaryColorPointer(int size, int type, int stride, Buffer data, int data_position, long function_pointer);
	public static void glSecondaryColorPointer(int size, int type, int stride, long data_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glSecondaryColorPointer_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOenabled(caps);
		nglSecondaryColorPointerBO(size, type, stride, data_buffer_offset, function_pointer);
	}
	private static native void nglSecondaryColorPointerBO(int size, int type, int stride, long data_buffer_offset, long function_pointer);

	public static void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glBlendFuncSeparate_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha, function_pointer);
	}
	private static native void nglBlendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha, long function_pointer);

	public static void glWindowPos2f(float x, float y) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glWindowPos2f_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglWindowPos2f(x, y, function_pointer);
	}
	private static native void nglWindowPos2f(float x, float y, long function_pointer);

	public static void glWindowPos2d(double x, double y) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glWindowPos2d_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglWindowPos2d(x, y, function_pointer);
	}
	private static native void nglWindowPos2d(double x, double y, long function_pointer);

	public static void glWindowPos2i(int x, int y) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glWindowPos2i_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglWindowPos2i(x, y, function_pointer);
	}
	private static native void nglWindowPos2i(int x, int y, long function_pointer);

	public static void glWindowPos3f(float x, float y, float z) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glWindowPos3f_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglWindowPos3f(x, y, z, function_pointer);
	}
	private static native void nglWindowPos3f(float x, float y, float z, long function_pointer);

	public static void glWindowPos3d(double x, double y, double z) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glWindowPos3d_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglWindowPos3d(x, y, z, function_pointer);
	}
	private static native void nglWindowPos3d(double x, double y, double z, long function_pointer);

	public static void glWindowPos3i(int x, int y, int z) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL14_glWindowPos3i_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglWindowPos3i(x, y, z, function_pointer);
	}
	private static native void nglWindowPos3i(int x, int y, int z, long function_pointer);
}
