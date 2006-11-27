/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class GL13 {
	public static final int GL_TEXTURE0 = 0x84c0;
	public static final int GL_TEXTURE1 = 0x84c1;
	public static final int GL_TEXTURE2 = 0x84c2;
	public static final int GL_TEXTURE3 = 0x84c3;
	public static final int GL_TEXTURE4 = 0x84c4;
	public static final int GL_TEXTURE5 = 0x84c5;
	public static final int GL_TEXTURE6 = 0x84c6;
	public static final int GL_TEXTURE7 = 0x84c7;
	public static final int GL_TEXTURE8 = 0x84c8;
	public static final int GL_TEXTURE9 = 0x84c9;
	public static final int GL_TEXTURE10 = 0x84ca;
	public static final int GL_TEXTURE11 = 0x84cb;
	public static final int GL_TEXTURE12 = 0x84cc;
	public static final int GL_TEXTURE13 = 0x84cd;
	public static final int GL_TEXTURE14 = 0x84ce;
	public static final int GL_TEXTURE15 = 0x84cf;
	public static final int GL_TEXTURE16 = 0x84d0;
	public static final int GL_TEXTURE17 = 0x84d1;
	public static final int GL_TEXTURE18 = 0x84d2;
	public static final int GL_TEXTURE19 = 0x84d3;
	public static final int GL_TEXTURE20 = 0x84d4;
	public static final int GL_TEXTURE21 = 0x84d5;
	public static final int GL_TEXTURE22 = 0x84d6;
	public static final int GL_TEXTURE23 = 0x84d7;
	public static final int GL_TEXTURE24 = 0x84d8;
	public static final int GL_TEXTURE25 = 0x84d9;
	public static final int GL_TEXTURE26 = 0x84da;
	public static final int GL_TEXTURE27 = 0x84db;
	public static final int GL_TEXTURE28 = 0x84dc;
	public static final int GL_TEXTURE29 = 0x84dd;
	public static final int GL_TEXTURE30 = 0x84de;
	public static final int GL_TEXTURE31 = 0x84df;
	public static final int GL_ACTIVE_TEXTURE = 0x84e0;
	public static final int GL_CLIENT_ACTIVE_TEXTURE = 0x84e1;
	public static final int GL_MAX_TEXTURE_UNITS = 0x84e2;
	public static final int GL_NORMAL_MAP = 0x8511;
	public static final int GL_REFLECTION_MAP = 0x8512;
	public static final int GL_TEXTURE_CUBE_MAP = 0x8513;
	public static final int GL_TEXTURE_BINDING_CUBE_MAP = 0x8514;
	public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_X = 0x8515;
	public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 0x8516;
	public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 0x8517;
	public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 0x8518;
	public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 0x8519;
	public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 0x851a;
	public static final int GL_PROXY_TEXTURE_CUBE_MAP = 0x851b;
	public static final int GL_MAX_CUBE_MAP_TEXTURE_SIZE = 0x851c;
	public static final int GL_COMPRESSED_ALPHA = 0x84e9;
	public static final int GL_COMPRESSED_LUMINANCE = 0x84ea;
	public static final int GL_COMPRESSED_LUMINANCE_ALPHA = 0x84eb;
	public static final int GL_COMPRESSED_INTENSITY = 0x84ec;
	public static final int GL_COMPRESSED_RGB = 0x84ed;
	public static final int GL_COMPRESSED_RGBA = 0x84ee;
	public static final int GL_TEXTURE_COMPRESSION_HINT = 0x84ef;
	public static final int GL_TEXTURE_COMPRESSED_IMAGE_SIZE = 0x86a0;
	public static final int GL_TEXTURE_COMPRESSED = 0x86a1;
	public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS = 0x86a2;
	public static final int GL_COMPRESSED_TEXTURE_FORMATS = 0x86a3;
	public static final int GL_MULTISAMPLE = 0x809d;
	public static final int GL_SAMPLE_ALPHA_TO_COVERAGE = 0x809e;
	public static final int GL_SAMPLE_ALPHA_TO_ONE = 0x809f;
	public static final int GL_SAMPLE_COVERAGE = 0x80a0;
	public static final int GL_SAMPLE_BUFFERS = 0x80a8;
	public static final int GL_SAMPLES = 0x80a9;
	public static final int GL_SAMPLE_COVERAGE_VALUE = 0x80aa;
	public static final int GL_SAMPLE_COVERAGE_INVERT = 0x80ab;
	public static final int GL_MULTISAMPLE_BIT = 0x20000000;
	public static final int GL_TRANSPOSE_MODELVIEW_MATRIX = 0x84e3;
	public static final int GL_TRANSPOSE_PROJECTION_MATRIX = 0x84e4;
	public static final int GL_TRANSPOSE_TEXTURE_MATRIX = 0x84e5;
	public static final int GL_TRANSPOSE_COLOR_MATRIX = 0x84e6;
	public static final int GL_COMBINE = 0x8570;
	public static final int GL_COMBINE_RGB = 0x8571;
	public static final int GL_COMBINE_ALPHA = 0x8572;
	public static final int GL_SOURCE0_RGB = 0x8580;
	public static final int GL_SOURCE1_RGB = 0x8581;
	public static final int GL_SOURCE2_RGB = 0x8582;
	public static final int GL_SOURCE0_ALPHA = 0x8588;
	public static final int GL_SOURCE1_ALPHA = 0x8589;
	public static final int GL_SOURCE2_ALPHA = 0x858a;
	public static final int GL_OPERAND0_RGB = 0x8590;
	public static final int GL_OPERAND1_RGB = 0x8591;
	public static final int GL_OPERAND2_RGB = 0x8592;
	public static final int GL_OPERAND0_ALPHA = 0x8598;
	public static final int GL_OPERAND1_ALPHA = 0x8599;
	public static final int GL_OPERAND2_ALPHA = 0x859a;
	public static final int GL_RGB_SCALE = 0x8573;
	public static final int GL_ADD_SIGNED = 0x8574;
	public static final int GL_INTERPOLATE = 0x8575;
	public static final int GL_SUBTRACT = 0x84e7;
	public static final int GL_CONSTANT = 0x8576;
	public static final int GL_PRIMARY_COLOR = 0x8577;
	public static final int GL_PREVIOUS = 0x8578;
	public static final int GL_DOT3_RGB = 0x86ae;
	public static final int GL_DOT3_RGBA = 0x86af;
	public static final int GL_CLAMP_TO_BORDER = 0x812d;

	private GL13() {
	}


	public static void glActiveTexture(int texture) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glActiveTexture_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglActiveTexture(texture, function_pointer);
	}
	private static native void nglActiveTexture(int texture, long function_pointer);

	public static void glClientActiveTexture(int texture) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glClientActiveTexture_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglClientActiveTexture(texture, function_pointer);
	}
	private static native void nglClientActiveTexture(int texture, long function_pointer);

	public static void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, ByteBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glCompressedTexImage1D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(data);
		nglCompressedTexImage1D(target, level, internalformat, width, border, (data.remaining()), data, data.position(), function_pointer);
	}
	private static native void nglCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, Buffer data, int data_position, long function_pointer);
	public static void glCompressedTexImage1D(int target, int level, int internalformat, int width, int border, int imageSize, long data_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glCompressedTexImage1D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglCompressedTexImage1DBO(target, level, internalformat, width, border, imageSize, data_buffer_offset, function_pointer);
	}
	private static native void nglCompressedTexImage1DBO(int target, int level, int internalformat, int width, int border, int imageSize, long data_buffer_offset, long function_pointer);

	public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, ByteBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glCompressedTexImage2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(data);
		nglCompressedTexImage2D(target, level, internalformat, width, height, border, (data.remaining()), data, data.position(), function_pointer);
	}
	private static native void nglCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, ByteBuffer data, int data_position, long function_pointer);
	public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, long data_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glCompressedTexImage2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglCompressedTexImage2DBO(target, level, internalformat, width, height, border, imageSize, data_buffer_offset, function_pointer);
	}
	private static native void nglCompressedTexImage2DBO(int target, int level, int internalformat, int width, int height, int border, int imageSize, long data_buffer_offset, long function_pointer);

	public static void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, ByteBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glCompressedTexImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(data);
		nglCompressedTexImage3D(target, level, internalformat, width, height, depth, border, (data.remaining()), data, data.position(), function_pointer);
	}
	private static native void nglCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ByteBuffer data, int data_position, long function_pointer);
	public static void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, long data_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glCompressedTexImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglCompressedTexImage3DBO(target, level, internalformat, width, height, depth, border, imageSize, data_buffer_offset, function_pointer);
	}
	private static native void nglCompressedTexImage3DBO(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, long data_buffer_offset, long function_pointer);

	public static void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, ByteBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glCompressedTexSubImage1D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(data);
		nglCompressedTexSubImage1D(target, level, xoffset, width, format, (data.remaining()), data, data.position(), function_pointer);
	}
	private static native void nglCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, ByteBuffer data, int data_position, long function_pointer);
	public static void glCompressedTexSubImage1D(int target, int level, int xoffset, int width, int format, int imageSize, long data_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glCompressedTexSubImage1D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglCompressedTexSubImage1DBO(target, level, xoffset, width, format, imageSize, data_buffer_offset, function_pointer);
	}
	private static native void nglCompressedTexSubImage1DBO(int target, int level, int xoffset, int width, int format, int imageSize, long data_buffer_offset, long function_pointer);

	public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glCompressedTexSubImage2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(data);
		nglCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, (data.remaining()), data, data.position(), function_pointer);
	}
	private static native void nglCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, ByteBuffer data, int data_position, long function_pointer);
	public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, long data_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glCompressedTexSubImage2D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglCompressedTexSubImage2DBO(target, level, xoffset, yoffset, width, height, format, imageSize, data_buffer_offset, function_pointer);
	}
	private static native void nglCompressedTexSubImage2DBO(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, long data_buffer_offset, long function_pointer);

	public static void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, ByteBuffer data) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glCompressedTexSubImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(data);
		nglCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, (data.remaining()), data, data.position(), function_pointer);
	}
	private static native void nglCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, ByteBuffer data, int data_position, long function_pointer);
	public static void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, long data_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glCompressedTexSubImage3D_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglCompressedTexSubImage3DBO(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, data_buffer_offset, function_pointer);
	}
	private static native void nglCompressedTexSubImage3DBO(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, long data_buffer_offset, long function_pointer);

	public static void glGetCompressedTexImage(int target, int lod, ByteBuffer img) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glGetCompressedTexImage_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(img);
		nglGetCompressedTexImage(target, lod, img, img.position(), function_pointer);
	}
	public static void glGetCompressedTexImage(int target, int lod, IntBuffer img) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glGetCompressedTexImage_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(img);
		nglGetCompressedTexImage(target, lod, img, img.position() << 2, function_pointer);
	}
	public static void glGetCompressedTexImage(int target, int lod, ShortBuffer img) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glGetCompressedTexImage_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(img);
		nglGetCompressedTexImage(target, lod, img, img.position() << 1, function_pointer);
	}
	private static native void nglGetCompressedTexImage(int target, int lod, Buffer img, int img_position, long function_pointer);
	public static void glGetCompressedTexImage(int target, int lod, long img_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glGetCompressedTexImage_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOenabled(caps);
		nglGetCompressedTexImageBO(target, lod, img_buffer_offset, function_pointer);
	}
	private static native void nglGetCompressedTexImageBO(int target, int lod, long img_buffer_offset, long function_pointer);

	public static void glMultiTexCoord1f(int target, float s) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glMultiTexCoord1f_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord1f(target, s, function_pointer);
	}
	private static native void nglMultiTexCoord1f(int target, float s, long function_pointer);

	public static void glMultiTexCoord1d(int target, double s) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glMultiTexCoord1d_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord1d(target, s, function_pointer);
	}
	private static native void nglMultiTexCoord1d(int target, double s, long function_pointer);

	public static void glMultiTexCoord2f(int target, float s, float t) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glMultiTexCoord2f_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord2f(target, s, t, function_pointer);
	}
	private static native void nglMultiTexCoord2f(int target, float s, float t, long function_pointer);

	public static void glMultiTexCoord2d(int target, double s, double t) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glMultiTexCoord2d_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord2d(target, s, t, function_pointer);
	}
	private static native void nglMultiTexCoord2d(int target, double s, double t, long function_pointer);

	public static void glMultiTexCoord3f(int target, float s, float t, float r) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glMultiTexCoord3f_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord3f(target, s, t, r, function_pointer);
	}
	private static native void nglMultiTexCoord3f(int target, float s, float t, float r, long function_pointer);

	public static void glMultiTexCoord3d(int target, double s, double t, double r) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glMultiTexCoord3d_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord3d(target, s, t, r, function_pointer);
	}
	private static native void nglMultiTexCoord3d(int target, double s, double t, double r, long function_pointer);

	public static void glMultiTexCoord4f(int target, float s, float t, float r, float q) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glMultiTexCoord4f_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord4f(target, s, t, r, q, function_pointer);
	}
	private static native void nglMultiTexCoord4f(int target, float s, float t, float r, float q, long function_pointer);

	public static void glMultiTexCoord4d(int target, double s, double t, double r, double q) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glMultiTexCoord4d_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglMultiTexCoord4d(target, s, t, r, q, function_pointer);
	}
	private static native void nglMultiTexCoord4d(int target, double s, double t, double r, double q, long function_pointer);

	public static void glLoadTransposeMatrix(FloatBuffer m) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glLoadTransposeMatrixf_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(m, 16);
		nglLoadTransposeMatrixf(m, m.position(), function_pointer);
	}
	private static native void nglLoadTransposeMatrixf(FloatBuffer m, int m_position, long function_pointer);

	public static void glLoadTransposeMatrix(DoubleBuffer m) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glLoadTransposeMatrixd_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(m, 16);
		nglLoadTransposeMatrixd(m, m.position(), function_pointer);
	}
	private static native void nglLoadTransposeMatrixd(DoubleBuffer m, int m_position, long function_pointer);

	public static void glMultTransposeMatrix(FloatBuffer m) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glMultTransposeMatrixf_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(m, 16);
		nglMultTransposeMatrixf(m, m.position(), function_pointer);
	}
	private static native void nglMultTransposeMatrixf(FloatBuffer m, int m_position, long function_pointer);

	public static void glMultTransposeMatrix(DoubleBuffer m) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glMultTransposeMatrixd_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(m, 16);
		nglMultTransposeMatrixd(m, m.position(), function_pointer);
	}
	private static native void nglMultTransposeMatrixd(DoubleBuffer m, int m_position, long function_pointer);

	public static void glSampleCoverage(float value, boolean invert) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.GL13_glSampleCoverage_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglSampleCoverage(value, invert, function_pointer);
	}
	private static native void nglSampleCoverage(float value, boolean invert, long function_pointer);
}
