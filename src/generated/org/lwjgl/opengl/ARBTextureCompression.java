/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBTextureCompression {
	public static final int GL_COMPRESSED_ALPHA_ARB = 0x84e9;
	public static final int GL_COMPRESSED_LUMINANCE_ARB = 0x84ea;
	public static final int GL_COMPRESSED_LUMINANCE_ALPHA_ARB = 0x84eb;
	public static final int GL_COMPRESSED_INTENSITY_ARB = 0x84ec;
	public static final int GL_COMPRESSED_RGB_ARB = 0x84ed;
	public static final int GL_COMPRESSED_RGBA_ARB = 0x84ee;
	public static final int GL_TEXTURE_COMPRESSION_HINT_ARB = 0x84ef;
	public static final int GL_TEXTURE_IMAGE_SIZE_ARB = 0x86a0;
	public static final int GL_TEXTURE_COMPRESSED_ARB = 0x86a1;
	public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS_ARB = 0x86a2;
	public static final int GL_COMPRESSED_TEXTURE_FORMATS_ARB = 0x86a3;

	private ARBTextureCompression() {
	}


	public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, ByteBuffer pData) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_texture_compression_glCompressedTexImage1DARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(pData);
		nglCompressedTexImage1DARB(target, level, internalformat, width, border, (pData.remaining()), pData, pData.position(), function_pointer);
	}
	private static native void nglCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, ByteBuffer pData, int pData_position, long function_pointer);
	public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, long pData_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_texture_compression_glCompressedTexImage1DARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglCompressedTexImage1DARBBO(target, level, internalformat, width, border, imageSize, pData_buffer_offset, function_pointer);
	}
	private static native void nglCompressedTexImage1DARBBO(int target, int level, int internalformat, int width, int border, int imageSize, long pData_buffer_offset, long function_pointer);

	public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, ByteBuffer pData) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_texture_compression_glCompressedTexImage2DARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(pData);
		nglCompressedTexImage2DARB(target, level, internalformat, width, height, border, (pData.remaining()), pData, pData.position(), function_pointer);
	}
	private static native void nglCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, ByteBuffer pData, int pData_position, long function_pointer);
	public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, long pData_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_texture_compression_glCompressedTexImage2DARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglCompressedTexImage2DARBBO(target, level, internalformat, width, height, border, imageSize, pData_buffer_offset, function_pointer);
	}
	private static native void nglCompressedTexImage2DARBBO(int target, int level, int internalformat, int width, int height, int border, int imageSize, long pData_buffer_offset, long function_pointer);

	public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, ByteBuffer pData) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_texture_compression_glCompressedTexImage3DARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(pData);
		nglCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, (pData.remaining()), pData, pData.position(), function_pointer);
	}
	private static native void nglCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ByteBuffer pData, int pData_position, long function_pointer);
	public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, long pData_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_texture_compression_glCompressedTexImage3DARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglCompressedTexImage3DARBBO(target, level, internalformat, width, height, depth, border, imageSize, pData_buffer_offset, function_pointer);
	}
	private static native void nglCompressedTexImage3DARBBO(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, long pData_buffer_offset, long function_pointer);

	public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int format, ByteBuffer pData) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_texture_compression_glCompressedTexSubImage1DARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(pData);
		nglCompressedTexSubImage1DARB(target, level, xoffset, width, format, (pData.remaining()), pData, pData.position(), function_pointer);
	}
	private static native void nglCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int format, int imageSize, ByteBuffer pData, int pData_position, long function_pointer);
	public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int format, int imageSize, long pData_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_texture_compression_glCompressedTexSubImage1DARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglCompressedTexSubImage1DARBBO(target, level, xoffset, width, format, imageSize, pData_buffer_offset, function_pointer);
	}
	private static native void nglCompressedTexSubImage1DARBBO(int target, int level, int xoffset, int width, int format, int imageSize, long pData_buffer_offset, long function_pointer);

	public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer pData) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_texture_compression_glCompressedTexSubImage2DARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(pData);
		nglCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, format, (pData.remaining()), pData, pData.position(), function_pointer);
	}
	private static native void nglCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, ByteBuffer pData, int pData_position, long function_pointer);
	public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, long pData_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_texture_compression_glCompressedTexSubImage2DARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglCompressedTexSubImage2DARBBO(target, level, xoffset, yoffset, width, height, format, imageSize, pData_buffer_offset, function_pointer);
	}
	private static native void nglCompressedTexSubImage2DARBBO(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, long pData_buffer_offset, long function_pointer);

	public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, ByteBuffer pData) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_texture_compression_glCompressedTexSubImage3DARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOdisabled(caps);
		BufferChecks.checkDirect(pData);
		nglCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, format, (pData.remaining()), pData, pData.position(), function_pointer);
	}
	private static native void nglCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, ByteBuffer pData, int pData_position, long function_pointer);
	public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, long pData_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_texture_compression_glCompressedTexSubImage3DARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureUnpackPBOenabled(caps);
		nglCompressedTexSubImage3DARBBO(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imageSize, pData_buffer_offset, function_pointer);
	}
	private static native void nglCompressedTexSubImage3DARBBO(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int imageSize, long pData_buffer_offset, long function_pointer);

	public static void glGetCompressedTexImageARB(int target, int lod, ByteBuffer pImg) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_texture_compression_glGetCompressedTexImageARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOdisabled(caps);
		BufferChecks.checkDirect(pImg);
		nglGetCompressedTexImageARB(target, lod, pImg, pImg.position(), function_pointer);
	}
	private static native void nglGetCompressedTexImageARB(int target, int lod, ByteBuffer pImg, int pImg_position, long function_pointer);
	public static void glGetCompressedTexImageARB(int target, int lod, long pImg_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_texture_compression_glGetCompressedTexImageARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensurePackPBOenabled(caps);
		nglGetCompressedTexImageARBBO(target, lod, pImg_buffer_offset, function_pointer);
	}
	private static native void nglGetCompressedTexImageARBBO(int target, int lod, long pImg_buffer_offset, long function_pointer);
}
