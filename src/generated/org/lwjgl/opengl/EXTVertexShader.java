/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTVertexShader {
	public static final int GL_VERTEX_SHADER_EXT = 0x8780;
	public static final int GL_VERTEX_SHADER_BINDING_EXT = 0x8781;
	public static final int GL_OP_INDEX_EXT = 0x8782;
	public static final int GL_OP_NEGATE_EXT = 0x8783;
	public static final int GL_OP_DOT3_EXT = 0x8784;
	public static final int GL_OP_DOT4_EXT = 0x8785;
	public static final int GL_OP_MUL_EXT = 0x8786;
	public static final int GL_OP_ADD_EXT = 0x8787;
	public static final int GL_OP_MADD_EXT = 0x8788;
	public static final int GL_OP_FRAC_EXT = 0x8789;
	public static final int GL_OP_MAX_EXT = 0x878a;
	public static final int GL_OP_MIN_EXT = 0x878b;
	public static final int GL_OP_SET_GE_EXT = 0x878c;
	public static final int GL_OP_SET_LT_EXT = 0x878d;
	public static final int GL_OP_CLAMP_EXT = 0x878e;
	public static final int GL_OP_FLOOR_EXT = 0x878f;
	public static final int GL_OP_ROUND_EXT = 0x8790;
	public static final int GL_OP_EXP_BASE_2_EXT = 0x8791;
	public static final int GL_OP_LOG_BASE_2_EXT = 0x8792;
	public static final int GL_OP_POWER_EXT = 0x8793;
	public static final int GL_OP_RECIP_EXT = 0x8794;
	public static final int GL_OP_RECIP_SQRT_EXT = 0x8795;
	public static final int GL_OP_SUB_EXT = 0x8796;
	public static final int GL_OP_CROSS_PRODUCT_EXT = 0x8797;
	public static final int GL_OP_MULTIPLY_MATRIX_EXT = 0x8798;
	public static final int GL_OP_MOV_EXT = 0x8799;
	public static final int GL_OUTPUT_VERTEX_EXT = 0x879a;
	public static final int GL_OUTPUT_COLOR0_EXT = 0x879b;
	public static final int GL_OUTPUT_COLOR1_EXT = 0x879c;
	public static final int GL_OUTPUT_TEXTURE_COORD0_EXT = 0x879d;
	public static final int GL_OUTPUT_TEXTURE_COORD1_EXT = 0x879e;
	public static final int GL_OUTPUT_TEXTURE_COORD2_EXT = 0x879f;
	public static final int GL_OUTPUT_TEXTURE_COORD3_EXT = 0x87a0;
	public static final int GL_OUTPUT_TEXTURE_COORD4_EXT = 0x87a1;
	public static final int GL_OUTPUT_TEXTURE_COORD5_EXT = 0x87a2;
	public static final int GL_OUTPUT_TEXTURE_COORD6_EXT = 0x87a3;
	public static final int GL_OUTPUT_TEXTURE_COORD7_EXT = 0x87a4;
	public static final int GL_OUTPUT_TEXTURE_COORD8_EXT = 0x87a5;
	public static final int GL_OUTPUT_TEXTURE_COORD9_EXT = 0x87a6;
	public static final int GL_OUTPUT_TEXTURE_COORD10_EXT = 0x87a7;
	public static final int GL_OUTPUT_TEXTURE_COORD11_EXT = 0x87a8;
	public static final int GL_OUTPUT_TEXTURE_COORD12_EXT = 0x87a9;
	public static final int GL_OUTPUT_TEXTURE_COORD13_EXT = 0x87aa;
	public static final int GL_OUTPUT_TEXTURE_COORD14_EXT = 0x87ab;
	public static final int GL_OUTPUT_TEXTURE_COORD15_EXT = 0x87ac;
	public static final int GL_OUTPUT_TEXTURE_COORD16_EXT = 0x87ad;
	public static final int GL_OUTPUT_TEXTURE_COORD17_EXT = 0x87ae;
	public static final int GL_OUTPUT_TEXTURE_COORD18_EXT = 0x87af;
	public static final int GL_OUTPUT_TEXTURE_COORD19_EXT = 0x87b0;
	public static final int GL_OUTPUT_TEXTURE_COORD20_EXT = 0x87b1;
	public static final int GL_OUTPUT_TEXTURE_COORD21_EXT = 0x87b2;
	public static final int GL_OUTPUT_TEXTURE_COORD22_EXT = 0x87b3;
	public static final int GL_OUTPUT_TEXTURE_COORD23_EXT = 0x87b4;
	public static final int GL_OUTPUT_TEXTURE_COORD24_EXT = 0x87b5;
	public static final int GL_OUTPUT_TEXTURE_COORD25_EXT = 0x87b6;
	public static final int GL_OUTPUT_TEXTURE_COORD26_EXT = 0x87b7;
	public static final int GL_OUTPUT_TEXTURE_COORD27_EXT = 0x87b8;
	public static final int GL_OUTPUT_TEXTURE_COORD28_EXT = 0x87b9;
	public static final int GL_OUTPUT_TEXTURE_COORD29_EXT = 0x87ba;
	public static final int GL_OUTPUT_TEXTURE_COORD30_EXT = 0x87bb;
	public static final int GL_OUTPUT_TEXTURE_COORD31_EXT = 0x87bc;
	public static final int GL_OUTPUT_FOG_EXT = 0x87bd;
	public static final int GL_SCALAR_EXT = 0x87be;
	public static final int GL_VECTOR_EXT = 0x87bf;
	public static final int GL_MATRIX_EXT = 0x87c0;
	public static final int GL_VARIANT_EXT = 0x87c1;
	public static final int GL_INVARIANT_EXT = 0x87c2;
	public static final int GL_LOCAL_CONSTANT_EXT = 0x87c3;
	public static final int GL_LOCAL_EXT = 0x87c4;
	public static final int GL_MAX_VERTEX_SHADER_INSTRUCTIONS_EXT = 0x87c5;
	public static final int GL_MAX_VERTEX_SHADER_VARIANTS_EXT = 0x87c6;
	public static final int GL_MAX_VERTEX_SHADER_INVARIANTS_EXT = 0x87c7;
	public static final int GL_MAX_VERTEX_SHADER_LOCAL_CONSTANTS_EXT = 0x87c8;
	public static final int GL_MAX_VERTEX_SHADER_LOCALS_EXT = 0x87c9;
	public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_INSTRUCTIONS_EXT = 0x87ca;
	public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_VARIANTS_EXT = 0x87cb;
	public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_INVARIANTS_EXT = 0x87cc;
	public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_LOCAL_CONSTANTS_EXT = 0x87cd;
	public static final int GL_MAX_OPTIMIZED_VERTEX_SHADER_LOCALS_EXT = 0x87ce;
	public static final int GL_VERTEX_SHADER_INSTRUCTIONS_EXT = 0x87cf;
	public static final int GL_VERTEX_SHADER_VARIANTS_EXT = 0x87d0;
	public static final int GL_VERTEX_SHADER_INVARIANTS_EXT = 0x87d1;
	public static final int GL_VERTEX_SHADER_LOCAL_CONSTANTS_EXT = 0x87d2;
	public static final int GL_VERTEX_SHADER_LOCALS_EXT = 0x87d3;
	public static final int GL_VERTEX_SHADER_OPTIMIZED_EXT = 0x87d4;
	public static final int GL_X_EXT = 0x87d5;
	public static final int GL_Y_EXT = 0x87d6;
	public static final int GL_Z_EXT = 0x87d7;
	public static final int GL_W_EXT = 0x87d8;
	public static final int GL_NEGATIVE_X_EXT = 0x87d9;
	public static final int GL_NEGATIVE_Y_EXT = 0x87da;
	public static final int GL_NEGATIVE_Z_EXT = 0x87db;
	public static final int GL_NEGATIVE_W_EXT = 0x87dc;
	public static final int GL_ZERO_EXT = 0x87dd;
	public static final int GL_ONE_EXT = 0x87de;
	public static final int GL_NEGATIVE_ONE_EXT = 0x87df;
	public static final int GL_NORMALIZED_RANGE_EXT = 0x87e0;
	public static final int GL_FULL_RANGE_EXT = 0x87e1;
	public static final int GL_CURRENT_VERTEX_EXT = 0x87e2;
	public static final int GL_MVP_MATRIX_EXT = 0x87e3;
	public static final int GL_VARIANT_VALUE_EXT = 0x87e4;
	public static final int GL_VARIANT_DATATYPE_EXT = 0x87e5;
	public static final int GL_VARIANT_ARRAY_STRIDE_EXT = 0x87e6;
	public static final int GL_VARIANT_ARRAY_TYPE_EXT = 0x87e7;
	public static final int GL_VARIANT_ARRAY_EXT = 0x87e8;
	public static final int GL_VARIANT_ARRAY_POINTER_EXT = 0x87e9;
	public static final int GL_INVARIANT_VALUE_EXT = 0x87ea;
	public static final int GL_INVARIANT_DATATYPE_EXT = 0x87eb;
	public static final int GL_LOCAL_CONSTANT_VALUE_EXT = 0x87ec;
	public static final int GL_LOCAL_CONSTANT_DATATYPE_EXT = 0x87ed;

	private EXTVertexShader() {
	}


	public static void glBeginVertexShaderEXT() {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glBeginVertexShaderEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBeginVertexShaderEXT(function_pointer);
	}
	private static native void nglBeginVertexShaderEXT(long function_pointer);

	public static void glEndVertexShaderEXT() {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glEndVertexShaderEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglEndVertexShaderEXT(function_pointer);
	}
	private static native void nglEndVertexShaderEXT(long function_pointer);

	public static void glBindVertexShaderEXT(int id) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glBindVertexShaderEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBindVertexShaderEXT(id, function_pointer);
	}
	private static native void nglBindVertexShaderEXT(int id, long function_pointer);

	public static int glGenVertexShadersEXT(int range) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glGenVertexShadersEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglGenVertexShadersEXT(range, function_pointer);
		return __result;
	}
	private static native int nglGenVertexShadersEXT(int range, long function_pointer);

	public static void glDeleteVertexShaderEXT(int id) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glDeleteVertexShaderEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDeleteVertexShaderEXT(id, function_pointer);
	}
	private static native void nglDeleteVertexShaderEXT(int id, long function_pointer);

	public static void glShaderOp1EXT(int op, int res, int arg1) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glShaderOp1EXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglShaderOp1EXT(op, res, arg1, function_pointer);
	}
	private static native void nglShaderOp1EXT(int op, int res, int arg1, long function_pointer);

	public static void glShaderOp2EXT(int op, int res, int arg1, int arg2) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glShaderOp2EXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglShaderOp2EXT(op, res, arg1, arg2, function_pointer);
	}
	private static native void nglShaderOp2EXT(int op, int res, int arg1, int arg2, long function_pointer);

	public static void glShaderOp3EXT(int op, int res, int arg1, int arg2, int arg3) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glShaderOp3EXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglShaderOp3EXT(op, res, arg1, arg2, arg3, function_pointer);
	}
	private static native void nglShaderOp3EXT(int op, int res, int arg1, int arg2, int arg3, long function_pointer);

	public static void glSwizzleEXT(int res, int in, int outX, int outY, int outZ, int outW) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glSwizzleEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglSwizzleEXT(res, in, outX, outY, outZ, outW, function_pointer);
	}
	private static native void nglSwizzleEXT(int res, int in, int outX, int outY, int outZ, int outW, long function_pointer);

	public static void glWriteMaskEXT(int res, int in, int outX, int outY, int outZ, int outW) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glWriteMaskEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglWriteMaskEXT(res, in, outX, outY, outZ, outW, function_pointer);
	}
	private static native void nglWriteMaskEXT(int res, int in, int outX, int outY, int outZ, int outW, long function_pointer);

	public static void glInsertComponentEXT(int res, int src, int num) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glInsertComponentEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglInsertComponentEXT(res, src, num, function_pointer);
	}
	private static native void nglInsertComponentEXT(int res, int src, int num, long function_pointer);

	public static void glExtractComponentEXT(int res, int src, int num) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glExtractComponentEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglExtractComponentEXT(res, src, num, function_pointer);
	}
	private static native void nglExtractComponentEXT(int res, int src, int num, long function_pointer);

	public static int glGenSymbolsEXT(int dataType, int storageType, int range, int components) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glGenSymbolsEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglGenSymbolsEXT(dataType, storageType, range, components, function_pointer);
		return __result;
	}
	private static native int nglGenSymbolsEXT(int dataType, int storageType, int range, int components, long function_pointer);

	public static void glSetInvariantEXT(int id, DoubleBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glSetInvariantEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglSetInvariantEXT(id, GL11.GL_DOUBLE, pAddr, pAddr.position() << 3, function_pointer);
	}
	public static void glSetInvariantEXT(int id, FloatBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glSetInvariantEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglSetInvariantEXT(id, GL11.GL_FLOAT, pAddr, pAddr.position() << 2, function_pointer);
	}
	public static void glSetInvariantEXT(int id, boolean unsigned, ByteBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glSetInvariantEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglSetInvariantEXT(id, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, pAddr, pAddr.position(), function_pointer);
	}
	public static void glSetInvariantEXT(int id, boolean unsigned, IntBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glSetInvariantEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglSetInvariantEXT(id, unsigned ? GL11.GL_UNSIGNED_INT : GL11.GL_INT, pAddr, pAddr.position() << 2, function_pointer);
	}
	public static void glSetInvariantEXT(int id, boolean unsigned, ShortBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glSetInvariantEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglSetInvariantEXT(id, unsigned ? GL11.GL_UNSIGNED_SHORT : GL11.GL_SHORT, pAddr, pAddr.position() << 1, function_pointer);
	}
	private static native void nglSetInvariantEXT(int id, int type, Buffer pAddr, int pAddr_position, long function_pointer);

	public static void glSetLocalConstantEXT(int id, DoubleBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glSetLocalConstantEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglSetLocalConstantEXT(id, GL11.GL_DOUBLE, pAddr, pAddr.position() << 3, function_pointer);
	}
	public static void glSetLocalConstantEXT(int id, FloatBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glSetLocalConstantEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglSetLocalConstantEXT(id, GL11.GL_FLOAT, pAddr, pAddr.position() << 2, function_pointer);
	}
	public static void glSetLocalConstantEXT(int id, boolean unsigned, ByteBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glSetLocalConstantEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglSetLocalConstantEXT(id, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, pAddr, pAddr.position(), function_pointer);
	}
	public static void glSetLocalConstantEXT(int id, boolean unsigned, IntBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glSetLocalConstantEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglSetLocalConstantEXT(id, unsigned ? GL11.GL_UNSIGNED_INT : GL11.GL_INT, pAddr, pAddr.position() << 2, function_pointer);
	}
	public static void glSetLocalConstantEXT(int id, boolean unsigned, ShortBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glSetLocalConstantEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglSetLocalConstantEXT(id, unsigned ? GL11.GL_UNSIGNED_SHORT : GL11.GL_SHORT, pAddr, pAddr.position() << 1, function_pointer);
	}
	private static native void nglSetLocalConstantEXT(int id, int type, Buffer pAddr, int pAddr_position, long function_pointer);

	public static void glVariantEXT(int id, ByteBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glVariantbvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglVariantbvEXT(id, pAddr, pAddr.position(), function_pointer);
	}
	private static native void nglVariantbvEXT(int id, ByteBuffer pAddr, int pAddr_position, long function_pointer);

	public static void glVariantEXT(int id, ShortBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glVariantsvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglVariantsvEXT(id, pAddr, pAddr.position(), function_pointer);
	}
	private static native void nglVariantsvEXT(int id, ShortBuffer pAddr, int pAddr_position, long function_pointer);

	public static void glVariantEXT(int id, IntBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glVariantivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglVariantivEXT(id, pAddr, pAddr.position(), function_pointer);
	}
	private static native void nglVariantivEXT(int id, IntBuffer pAddr, int pAddr_position, long function_pointer);

	public static void glVariantEXT(int id, FloatBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glVariantfvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglVariantfvEXT(id, pAddr, pAddr.position(), function_pointer);
	}
	private static native void nglVariantfvEXT(int id, FloatBuffer pAddr, int pAddr_position, long function_pointer);

	public static void glVariantEXT(int id, DoubleBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glVariantdvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglVariantdvEXT(id, pAddr, pAddr.position(), function_pointer);
	}
	private static native void nglVariantdvEXT(int id, DoubleBuffer pAddr, int pAddr_position, long function_pointer);

	public static void glVariantuEXT(int id, ByteBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glVariantubvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglVariantubvEXT(id, pAddr, pAddr.position(), function_pointer);
	}
	private static native void nglVariantubvEXT(int id, ByteBuffer pAddr, int pAddr_position, long function_pointer);

	public static void glVariantuEXT(int id, ShortBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glVariantusvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglVariantusvEXT(id, pAddr, pAddr.position(), function_pointer);
	}
	private static native void nglVariantusvEXT(int id, ShortBuffer pAddr, int pAddr_position, long function_pointer);

	public static void glVariantuEXT(int id, IntBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glVariantuivEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pAddr, 4);
		nglVariantuivEXT(id, pAddr, pAddr.position(), function_pointer);
	}
	private static native void nglVariantuivEXT(int id, IntBuffer pAddr, int pAddr_position, long function_pointer);

	public static void glVariantPointerEXT(int id, int stride, DoubleBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glVariantPointerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pAddr);
		GLChecks.getReferences(caps).EXT_vertex_shader_glVariantPointerEXT_pAddr = pAddr;
		nglVariantPointerEXT(id, GL11.GL_DOUBLE, stride, pAddr, pAddr.position() << 3, function_pointer);
	}
	public static void glVariantPointerEXT(int id, int stride, FloatBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glVariantPointerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pAddr);
		GLChecks.getReferences(caps).EXT_vertex_shader_glVariantPointerEXT_pAddr = pAddr;
		nglVariantPointerEXT(id, GL11.GL_FLOAT, stride, pAddr, pAddr.position() << 2, function_pointer);
	}
	public static void glVariantPointerEXT(int id, boolean unsigned, int stride, ByteBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glVariantPointerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pAddr);
		GLChecks.getReferences(caps).EXT_vertex_shader_glVariantPointerEXT_pAddr = pAddr;
		nglVariantPointerEXT(id, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, stride, pAddr, pAddr.position(), function_pointer);
	}
	public static void glVariantPointerEXT(int id, boolean unsigned, int stride, IntBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glVariantPointerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pAddr);
		GLChecks.getReferences(caps).EXT_vertex_shader_glVariantPointerEXT_pAddr = pAddr;
		nglVariantPointerEXT(id, unsigned ? GL11.GL_UNSIGNED_INT : GL11.GL_INT, stride, pAddr, pAddr.position() << 2, function_pointer);
	}
	public static void glVariantPointerEXT(int id, boolean unsigned, int stride, ShortBuffer pAddr) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glVariantPointerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pAddr);
		GLChecks.getReferences(caps).EXT_vertex_shader_glVariantPointerEXT_pAddr = pAddr;
		nglVariantPointerEXT(id, unsigned ? GL11.GL_UNSIGNED_SHORT : GL11.GL_SHORT, stride, pAddr, pAddr.position() << 1, function_pointer);
	}
	private static native void nglVariantPointerEXT(int id, int type, int stride, Buffer pAddr, int pAddr_position, long function_pointer);
	public static void glVariantPointerEXT(int id, int type, int stride, long pAddr_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glVariantPointerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOenabled(caps);
		nglVariantPointerEXTBO(id, type, stride, pAddr_buffer_offset, function_pointer);
	}
	private static native void nglVariantPointerEXTBO(int id, int type, int stride, long pAddr_buffer_offset, long function_pointer);

	public static void glEnableVariantClientStateEXT(int id) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glEnableVariantClientStateEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglEnableVariantClientStateEXT(id, function_pointer);
	}
	private static native void nglEnableVariantClientStateEXT(int id, long function_pointer);

	public static void glDisableVariantClientStateEXT(int id) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glDisableVariantClientStateEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglDisableVariantClientStateEXT(id, function_pointer);
	}
	private static native void nglDisableVariantClientStateEXT(int id, long function_pointer);

	public static int glBindLightParameterEXT(int light, int value) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glBindLightParameterEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglBindLightParameterEXT(light, value, function_pointer);
		return __result;
	}
	private static native int nglBindLightParameterEXT(int light, int value, long function_pointer);

	public static int glBindMaterialParameterEXT(int face, int value) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glBindMaterialParameterEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglBindMaterialParameterEXT(face, value, function_pointer);
		return __result;
	}
	private static native int nglBindMaterialParameterEXT(int face, int value, long function_pointer);

	public static int glBindTexGenParameterEXT(int unit, int coord, int value) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glBindTexGenParameterEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglBindTexGenParameterEXT(unit, coord, value, function_pointer);
		return __result;
	}
	private static native int nglBindTexGenParameterEXT(int unit, int coord, int value, long function_pointer);

	public static int glBindTextureUnitParameterEXT(int unit, int value) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glBindTextureUnitParameterEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglBindTextureUnitParameterEXT(unit, value, function_pointer);
		return __result;
	}
	private static native int nglBindTextureUnitParameterEXT(int unit, int value, long function_pointer);

	public static int glBindParameterEXT(int value) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glBindParameterEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		int __result = nglBindParameterEXT(value, function_pointer);
		return __result;
	}
	private static native int nglBindParameterEXT(int value, long function_pointer);

	public static boolean glIsVariantEnabledEXT(int id, int cap) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glIsVariantEnabledEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsVariantEnabledEXT(id, cap, function_pointer);
		return __result;
	}
	private static native boolean nglIsVariantEnabledEXT(int id, int cap, long function_pointer);

	public static void glGetVariantBooleanEXT(int id, int value, ByteBuffer pbData) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glGetVariantBooleanvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pbData, 4);
		nglGetVariantBooleanvEXT(id, value, pbData, pbData.position(), function_pointer);
	}
	private static native void nglGetVariantBooleanvEXT(int id, int value, ByteBuffer pbData, int pbData_position, long function_pointer);

	public static void glGetVariantIntegerEXT(int id, int value, IntBuffer pbData) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glGetVariantIntegervEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pbData, 4);
		nglGetVariantIntegervEXT(id, value, pbData, pbData.position(), function_pointer);
	}
	private static native void nglGetVariantIntegervEXT(int id, int value, IntBuffer pbData, int pbData_position, long function_pointer);

	public static void glGetVariantFloatEXT(int id, int value, FloatBuffer pbData) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glGetVariantFloatvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pbData, 4);
		nglGetVariantFloatvEXT(id, value, pbData, pbData.position(), function_pointer);
	}
	private static native void nglGetVariantFloatvEXT(int id, int value, FloatBuffer pbData, int pbData_position, long function_pointer);

	public static java.nio.ByteBuffer glGetVariantPointerEXT(int id, int value, int result_size) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glGetVariantPointervEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		java.nio.ByteBuffer __result = nglGetVariantPointervEXT(id, value, result_size, function_pointer);
		return __result;
	}
	private static native java.nio.ByteBuffer nglGetVariantPointervEXT(int id, int value, int result_size, long function_pointer);

	public static void glGetInvariantBooleanEXT(int id, int value, ByteBuffer pbData) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glGetInvariantBooleanvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pbData, 4);
		nglGetInvariantBooleanvEXT(id, value, pbData, pbData.position(), function_pointer);
	}
	private static native void nglGetInvariantBooleanvEXT(int id, int value, ByteBuffer pbData, int pbData_position, long function_pointer);

	public static void glGetInvariantIntegerEXT(int id, int value, IntBuffer pbData) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glGetInvariantIntegervEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pbData, 4);
		nglGetInvariantIntegervEXT(id, value, pbData, pbData.position(), function_pointer);
	}
	private static native void nglGetInvariantIntegervEXT(int id, int value, IntBuffer pbData, int pbData_position, long function_pointer);

	public static void glGetInvariantFloatEXT(int id, int value, FloatBuffer pbData) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glGetInvariantFloatvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pbData, 4);
		nglGetInvariantFloatvEXT(id, value, pbData, pbData.position(), function_pointer);
	}
	private static native void nglGetInvariantFloatvEXT(int id, int value, FloatBuffer pbData, int pbData_position, long function_pointer);

	public static void glGetLocalConstantBooleanEXT(int id, int value, ByteBuffer pbData) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glGetLocalConstantBooleanvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pbData, 4);
		nglGetLocalConstantBooleanvEXT(id, value, pbData, pbData.position(), function_pointer);
	}
	private static native void nglGetLocalConstantBooleanvEXT(int id, int value, ByteBuffer pbData, int pbData_position, long function_pointer);

	public static void glGetLocalConstantIntegerEXT(int id, int value, IntBuffer pbData) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glGetLocalConstantIntegervEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pbData, 4);
		nglGetLocalConstantIntegervEXT(id, value, pbData, pbData.position(), function_pointer);
	}
	private static native void nglGetLocalConstantIntegervEXT(int id, int value, IntBuffer pbData, int pbData_position, long function_pointer);

	public static void glGetLocalConstantFloatEXT(int id, int value, FloatBuffer pbData) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.EXT_vertex_shader_glGetLocalConstantFloatvEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(pbData, 4);
		nglGetLocalConstantFloatvEXT(id, value, pbData, pbData.position(), function_pointer);
	}
	private static native void nglGetLocalConstantFloatvEXT(int id, int value, FloatBuffer pbData, int pbData_position, long function_pointer);
}
