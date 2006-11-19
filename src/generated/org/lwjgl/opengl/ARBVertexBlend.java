/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBVertexBlend {
	public static final int GL_MAX_VERTEX_UNITS_ARB = 0x86a4;
	public static final int GL_ACTIVE_VERTEX_UNITS_ARB = 0x86a5;
	public static final int GL_WEIGHT_SUM_UNITY_ARB = 0x86a6;
	public static final int GL_VERTEX_BLEND_ARB = 0x86a7;
	public static final int GL_CURRENT_WEIGHT_ARB = 0x86a8;
	public static final int GL_WEIGHT_ARRAY_TYPE_ARB = 0x86a9;
	public static final int GL_WEIGHT_ARRAY_STRIDE_ARB = 0x86aa;
	public static final int GL_WEIGHT_ARRAY_SIZE_ARB = 0x86ab;
	public static final int GL_WEIGHT_ARRAY_POINTER_ARB = 0x86ac;
	public static final int GL_WEIGHT_ARRAY_ARB = 0x86ad;
	public static final int GL_MODELVIEW0_ARB = 0x1700;
	public static final int GL_MODELVIEW1_ARB = 0x850a;
	public static final int GL_MODELVIEW2_ARB = 0x8722;
	public static final int GL_MODELVIEW3_ARB = 0x8723;
	public static final int GL_MODELVIEW4_ARB = 0x8724;
	public static final int GL_MODELVIEW5_ARB = 0x8725;
	public static final int GL_MODELVIEW6_ARB = 0x8726;
	public static final int GL_MODELVIEW7_ARB = 0x8727;
	public static final int GL_MODELVIEW8_ARB = 0x8728;
	public static final int GL_MODELVIEW9_ARB = 0x8729;
	public static final int GL_MODELVIEW10_ARB = 0x872a;
	public static final int GL_MODELVIEW11_ARB = 0x872b;
	public static final int GL_MODELVIEW12_ARB = 0x872c;
	public static final int GL_MODELVIEW13_ARB = 0x872d;
	public static final int GL_MODELVIEW14_ARB = 0x872e;
	public static final int GL_MODELVIEW15_ARB = 0x872f;
	public static final int GL_MODELVIEW16_ARB = 0x8730;
	public static final int GL_MODELVIEW17_ARB = 0x8731;
	public static final int GL_MODELVIEW18_ARB = 0x8732;
	public static final int GL_MODELVIEW19_ARB = 0x8733;
	public static final int GL_MODELVIEW20_ARB = 0x8734;
	public static final int GL_MODELVIEW21_ARB = 0x8735;
	public static final int GL_MODELVIEW22_ARB = 0x8736;
	public static final int GL_MODELVIEW23_ARB = 0x8737;
	public static final int GL_MODELVIEW24_ARB = 0x8738;
	public static final int GL_MODELVIEW25_ARB = 0x8739;
	public static final int GL_MODELVIEW26_ARB = 0x873a;
	public static final int GL_MODELVIEW27_ARB = 0x873b;
	public static final int GL_MODELVIEW28_ARB = 0x873c;
	public static final int GL_MODELVIEW29_ARB = 0x873d;
	public static final int GL_MODELVIEW30_ARB = 0x873e;
	public static final int GL_MODELVIEW31_ARB = 0x873f;

	private ARBVertexBlend() {
	}


	public static void glWeightARB(ByteBuffer pWeights) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_blend_glWeightbvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pWeights);
		nglWeightbvARB((pWeights.remaining()), pWeights, pWeights.position(), function_pointer);
	}
	private static native void nglWeightbvARB(int size, ByteBuffer pWeights, int pWeights_position, long function_pointer);

	public static void glWeightARB(ShortBuffer pWeights) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_blend_glWeightsvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pWeights);
		nglWeightsvARB((pWeights.remaining()), pWeights, pWeights.position(), function_pointer);
	}
	private static native void nglWeightsvARB(int size, ShortBuffer pWeights, int pWeights_position, long function_pointer);

	public static void glWeightARB(IntBuffer pWeights) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_blend_glWeightivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pWeights);
		nglWeightivARB((pWeights.remaining()), pWeights, pWeights.position(), function_pointer);
	}
	private static native void nglWeightivARB(int size, IntBuffer pWeights, int pWeights_position, long function_pointer);

	public static void glWeightARB(FloatBuffer pWeights) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_blend_glWeightfvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pWeights);
		nglWeightfvARB((pWeights.remaining()), pWeights, pWeights.position(), function_pointer);
	}
	private static native void nglWeightfvARB(int size, FloatBuffer pWeights, int pWeights_position, long function_pointer);

	public static void glWeightARB(DoubleBuffer pWeights) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_blend_glWeightdvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pWeights);
		nglWeightdvARB((pWeights.remaining()), pWeights, pWeights.position(), function_pointer);
	}
	private static native void nglWeightdvARB(int size, DoubleBuffer pWeights, int pWeights_position, long function_pointer);

	public static void glWeightuARB(ByteBuffer pWeights) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_blend_glWeightubvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pWeights);
		nglWeightubvARB((pWeights.remaining()), pWeights, pWeights.position(), function_pointer);
	}
	private static native void nglWeightubvARB(int size, ByteBuffer pWeights, int pWeights_position, long function_pointer);

	public static void glWeightuARB(ShortBuffer pWeights) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_blend_glWeightusvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pWeights);
		nglWeightusvARB((pWeights.remaining()), pWeights, pWeights.position(), function_pointer);
	}
	private static native void nglWeightusvARB(int size, ShortBuffer pWeights, int pWeights_position, long function_pointer);

	public static void glWeightuARB(IntBuffer pWeights) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_blend_glWeightuivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pWeights);
		nglWeightuivARB((pWeights.remaining()), pWeights, pWeights.position(), function_pointer);
	}
	private static native void nglWeightuivARB(int size, IntBuffer pWeights, int pWeights_position, long function_pointer);

	public static void glWeightPointerARB(int size, int stride, DoubleBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_blend_glWeightPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pPointer);
		GLChecks.getReferences(caps).ARB_vertex_blend_glWeightPointerARB_pPointer = pPointer;
		nglWeightPointerARB(size, GL11.GL_DOUBLE, stride, pPointer, pPointer.position() << 3, function_pointer);
	}
	public static void glWeightPointerARB(int size, int stride, FloatBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_blend_glWeightPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pPointer);
		GLChecks.getReferences(caps).ARB_vertex_blend_glWeightPointerARB_pPointer = pPointer;
		nglWeightPointerARB(size, GL11.GL_FLOAT, stride, pPointer, pPointer.position() << 2, function_pointer);
	}
	public static void glWeightPointerARB(int size, boolean unsigned, int stride, ByteBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_blend_glWeightPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pPointer);
		GLChecks.getReferences(caps).ARB_vertex_blend_glWeightPointerARB_pPointer = pPointer;
		nglWeightPointerARB(size, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, stride, pPointer, pPointer.position(), function_pointer);
	}
	public static void glWeightPointerARB(int size, boolean unsigned, int stride, IntBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_blend_glWeightPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pPointer);
		GLChecks.getReferences(caps).ARB_vertex_blend_glWeightPointerARB_pPointer = pPointer;
		nglWeightPointerARB(size, unsigned ? GL11.GL_UNSIGNED_INT : GL11.GL_INT, stride, pPointer, pPointer.position() << 2, function_pointer);
	}
	public static void glWeightPointerARB(int size, boolean unsigned, int stride, ShortBuffer pPointer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_blend_glWeightPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOdisabled(caps);
		BufferChecks.checkDirect(pPointer);
		GLChecks.getReferences(caps).ARB_vertex_blend_glWeightPointerARB_pPointer = pPointer;
		nglWeightPointerARB(size, unsigned ? GL11.GL_UNSIGNED_SHORT : GL11.GL_SHORT, stride, pPointer, pPointer.position() << 1, function_pointer);
	}
	private static native void nglWeightPointerARB(int size, int type, int stride, Buffer pPointer, int pPointer_position, long function_pointer);
	public static void glWeightPointerARB(int size, int type, int stride, long pPointer_buffer_offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_blend_glWeightPointerARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		GLChecks.ensureArrayVBOenabled(caps);
		nglWeightPointerARBBO(size, type, stride, pPointer_buffer_offset, function_pointer);
	}
	private static native void nglWeightPointerARBBO(int size, int type, int stride, long pPointer_buffer_offset, long function_pointer);

	public static void glVertexBlendARB(int count) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_vertex_blend_glVertexBlendARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglVertexBlendARB(count, function_pointer);
	}
	private static native void nglVertexBlendARB(int count, long function_pointer);
}
