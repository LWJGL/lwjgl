/* 
 * Copyright (c) 2002-2004 LWJGL Project
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are 
 * met:
 * 
 * * Redistributions of source code must retain the above copyright 
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of 
 *   its contributors may be used to endorse or promote products derived 
 *   from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.LWJGLException;

public final class ARBVertexBlend {
	public static final int GL_MAX_VERTEX_UNITS_ARB                                 = 0x86A4;
	public static final int GL_ACTIVE_VERTEX_UNITS_ARB                              = 0x86A5;
	public static final int GL_WEIGHT_SUM_UNITY_ARB                                 = 0x86A6;
	public static final int GL_VERTEX_BLEND_ARB                                     = 0x86A7;
	public static final int GL_CURRENT_WEIGHT_ARB                                   = 0x86A8;
	public static final int GL_WEIGHT_ARRAY_TYPE_ARB                                = 0x86A9;
	public static final int GL_WEIGHT_ARRAY_STRIDE_ARB                              = 0x86AA;
	public static final int GL_WEIGHT_ARRAY_SIZE_ARB                                = 0x86AB;
	public static final int GL_WEIGHT_ARRAY_POINTER_ARB                             = 0x86AC;
	public static final int GL_WEIGHT_ARRAY_ARB                                     = 0x86AD;
	public static final int GL_MODELVIEW0_ARB                                       = 0x1700;
	public static final int GL_MODELVIEW1_ARB                                       = 0x850a;
	public static final int GL_MODELVIEW2_ARB                                       = 0x8722;
	public static final int GL_MODELVIEW3_ARB                                       = 0x8723;
	public static final int GL_MODELVIEW4_ARB                                       = 0x8724;
	public static final int GL_MODELVIEW5_ARB                                       = 0x8725;
	public static final int GL_MODELVIEW6_ARB                                       = 0x8726;
	public static final int GL_MODELVIEW7_ARB                                       = 0x8727;
	public static final int GL_MODELVIEW8_ARB                                       = 0x8728;
	public static final int GL_MODELVIEW9_ARB                                       = 0x8729;
	public static final int GL_MODELVIEW10_ARB                                      = 0x872A;
	public static final int GL_MODELVIEW11_ARB                                      = 0x872B;
	public static final int GL_MODELVIEW12_ARB                                      = 0x872C;
	public static final int GL_MODELVIEW13_ARB                                      = 0x872D;
	public static final int GL_MODELVIEW14_ARB                                      = 0x872E;
	public static final int GL_MODELVIEW15_ARB                                      = 0x872F;
	public static final int GL_MODELVIEW16_ARB                                      = 0x8730;
	public static final int GL_MODELVIEW17_ARB                                      = 0x8731;
	public static final int GL_MODELVIEW18_ARB                                      = 0x8732;
	public static final int GL_MODELVIEW19_ARB                                      = 0x8733;
	public static final int GL_MODELVIEW20_ARB                                      = 0x8734;
	public static final int GL_MODELVIEW21_ARB                                      = 0x8735;
	public static final int GL_MODELVIEW22_ARB                                      = 0x8736;
	public static final int GL_MODELVIEW23_ARB                                      = 0x8737;
	public static final int GL_MODELVIEW24_ARB                                      = 0x8738;
	public static final int GL_MODELVIEW25_ARB                                      = 0x8739;
	public static final int GL_MODELVIEW26_ARB                                      = 0x873A;
	public static final int GL_MODELVIEW27_ARB                                      = 0x873B;
	public static final int GL_MODELVIEW28_ARB                                      = 0x873C;
	public static final int GL_MODELVIEW29_ARB                                      = 0x873D;
	public static final int GL_MODELVIEW30_ARB                                      = 0x873E;
	public static final int GL_MODELVIEW31_ARB                                      = 0x873F;
	
	static native void initNativeStubs() throws LWJGLException;

	public static void glWeightARB(ByteBuffer pWeights) {
		BufferChecks.checkDirect(pWeights);
		nglWeightbvARB(pWeights.remaining(), pWeights, pWeights.position());
	}
	private static native void nglWeightbvARB(int size, ByteBuffer pWeights, int pWeights_offset);

	public static void glWeightARB(FloatBuffer pfWeights) {
		BufferChecks.checkDirect(pfWeights);
		nglWeightfvARB(pfWeights.remaining(), pfWeights, pfWeights.position());
	}
	private static native void nglWeightfvARB(int size, FloatBuffer pfWeights, int pfWeights_offset);

	public static void glWeightARB(IntBuffer piWeights) {
		BufferChecks.checkDirect(piWeights);
		nglWeightivARB(piWeights.remaining(), piWeights, piWeights.position());
	}
	private static native void nglWeightivARB(int size, IntBuffer piWeights, int piWeights_offset);

	public static void glWeightARB(ShortBuffer psWeights) {
		BufferChecks.checkDirect(psWeights);
		nglWeightsvARB(psWeights.remaining(), psWeights, psWeights.position());
	}
	private static native void nglWeightsvARB(int size, ShortBuffer psWeights, int psWeights_offset);

	public static void glWeightuARB(ByteBuffer pWeights) {
		BufferChecks.checkDirect(pWeights);
		nglWeightubvARB(pWeights.remaining(), pWeights, pWeights.position());
	}
	private static native void nglWeightubvARB(int size, ByteBuffer pWeights, int pWeights_offset);

	public static void glWeightuARB(IntBuffer piWeights) {
		BufferChecks.checkDirect(piWeights);
		nglWeightuivARB(piWeights.remaining(), piWeights, piWeights.position());
	}
	private static native void nglWeightuivARB(int size, IntBuffer piWeights, int piWeights_offset);

	public static void glWeightuARB(ShortBuffer psWeights) {
		BufferChecks.checkDirect(psWeights);
		nglWeightusvARB(psWeights.remaining(), psWeights, psWeights.position());
	}
	private static native void nglWeightusvARB(int size, ShortBuffer psWeights, int psWeights_offset);

	public static void glWeightPointerARB(int size, boolean unsigned, int stride, ByteBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		BufferChecks.ensureArrayVBOdisabled();
		nglWeightPointerARB(size, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, stride, pPointer, pPointer.position());
	}
	public static void glWeightPointerARB(int size, boolean unsigned, int stride, ShortBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		BufferChecks.ensureArrayVBOdisabled();
		nglWeightPointerARB(size, unsigned ? GL11.GL_UNSIGNED_SHORT : GL11.GL_SHORT, stride, pPointer, pPointer.position()<<1);
	}
	public static void glWeightPointerARB(int size, int stride, FloatBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		BufferChecks.ensureArrayVBOdisabled();
		nglWeightPointerARB(size, GL11.GL_FLOAT, stride, pPointer, pPointer.position()<<2);
	}
	public static void glWeightPointerARB(int size, boolean unsigned, int stride, IntBuffer pPointer) {
		BufferChecks.checkDirect(pPointer);
		BufferChecks.ensureArrayVBOdisabled();
		nglWeightPointerARB(size, unsigned ? GL11.GL_UNSIGNED_INT : GL11.GL_INT, stride, pPointer, pPointer.position()<<2);
	}
	private static native void nglWeightPointerARB(int size, int type, int stride, Buffer pPointer, int pPointer_offset);
	public static void glWeightPointerARB(int size, int type, int stride, int buffer_offset) {
		BufferChecks.ensureArrayVBOenabled();
		nglWeightPointerARBVBO(size, type, stride, buffer_offset);
	}
	private static native void nglWeightPointerARBVBO(int size, int type, int stride, int buffer_offset);
	public static native void glVertexBlendARB(int count);
}
