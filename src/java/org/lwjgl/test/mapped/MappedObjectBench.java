/*
 * Copyright (c) 2002-2011 LWJGL Project
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
package org.lwjgl.test.mapped;

import org.lwjgl.MemoryUtil;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static org.lwjgl.util.mapped.MappedHelper.*;

/** @author Riven */
@SuppressWarnings("static-access")
public class MappedObjectBench {

	static class InstanceVec3 {

		float x, y, z;

		@Override
		public String toString() {
			return "InstanceVec3[" + x + ", " + y + ", " + z + "]";
		}
	}

	static class ArrayVec3 {

		float[] a;
		int     i;

		@Override
		public String toString() {
			return "ArrayVec3[" + a[i * 3 + 0] + ", " + a[i * 3 + 1] + ", " + a[i * 3 + 2] + "]";
		}
	}

	static void benchmarkInstances() {
		final int runs = 64;
		final int iterations = 64 * 1024;

		InstanceVec3 vec1 = new InstanceVec3();
		InstanceVec3 vec2 = new InstanceVec3();
		InstanceVec3 vec3 = new InstanceVec3();

		long[] took = new long[runs];
		for ( int run = 0; run < runs; run++ ) {
			long t0 = System.nanoTime();
			for ( int iteration = 0; iteration < iterations; iteration++ ) {
				vec1.x = 13;
				vec1.y += vec1.y * vec1.x + 0.3f;
				vec1.z += vec2.y + vec1.x + 0.3f;
				vec2.z += vec2.y + vec1.x;
				vec3.z += vec2.z + vec1.y;
			}
			long t1 = System.nanoTime();
			took[run] = t1 - t0;
		}

		Arrays.sort(took);
		System.out.println("instance took: " + took[took.length / 2] / 1024 + "us");

		System.out.println(vec1);
		System.out.println(vec2);
		System.out.println(vec3);
	}

	static void benchmarkMapped() {
		final int runs = 64;
		final int iterations = 64 * 1024;

		ByteBuffer bb = ByteBuffer.allocateDirect(200);

		MappedVec3 vecs = MappedVec3.map(bb);

		MappedVec3 vec1 = vecs.dup();
		MappedVec3 vec2 = vecs.dup();
		MappedVec3 vec3 = vecs.dup();

		vec1.view = 0;
		vec2.view = 1;
		vec3.view = 2;

		long[] took = new long[runs];
		for ( int run = 0; run < runs; run++ ) {
			long t0 = System.nanoTime();
			for ( int iteration = 0; iteration < iterations; iteration += 2 ) {
				vec1.x = 13;
				vec1.y += vec1.y * vec1.x + 0.3f;
				vec1.z += vec2.y + vec1.x + 0.3f;
				vec2.z += vec2.y + vec1.x;
				vec3.z += vec2.z + vec1.y;

				vec1.x = 13;
				vec1.y += vec1.y * vec1.x + 0.3f;
				vec1.z += vec2.y + vec1.x + 0.3f;
				vec2.z += vec2.y + vec1.x;
				vec3.z += vec2.z + vec1.y;
			}
			long t1 = System.nanoTime();
			took[run] = t1 - t0;
		}

		Arrays.sort(took);
		System.out.println("mapped took: " + took[took.length / 2] / 1024 + "us");

		System.out.println(vec1);
		System.out.println(vec2);
		System.out.println(vec3);

		System.out.println(bb);
	}

	static void benchmarkIndirectArray() {
		final int runs = 64;
		final int iterations = 64 * 1024;

		float[] bb = new float[200];

		ArrayVec3 vec1 = new ArrayVec3();
		ArrayVec3 vec2 = new ArrayVec3();
		ArrayVec3 vec3 = new ArrayVec3();

		vec1.a = bb;
		vec2.a = bb;
		vec3.a = bb;

		vec1.i = 0;
		vec2.i = 1;
		vec3.i = 2;

		long[] took = new long[runs];
		for ( int run = 0; run < runs; run++ ) {
			long t0 = System.nanoTime();
			for ( int iteration = 0; iteration < iterations; iteration++ ) {
				vec1.a[vec1.i * 3 + 0] = 13;
				vec1.a[vec1.i * 3 + 1] += vec1.a[vec1.i * 3 + 1] * vec1.a[vec1.i * 3 + 0] + 0.3f;
				vec1.a[vec1.i * 3 + 2] += vec2.a[vec2.i * 3 + 1] + vec1.a[vec1.i * 3 + 0] + 0.3f;
				vec2.a[vec2.i * 3 + 2] += vec2.a[vec2.i * 3 + 1] + vec1.a[vec1.i * 3 + 0];
				vec3.a[vec3.i * 3 + 2] += vec2.a[vec2.i * 3 + 2] + vec2.a[vec2.i * 3 + 1];
			}
			long t1 = System.nanoTime();
			took[run] = t1 - t0;
		}

		Arrays.sort(took);
		System.out.println("array took: " + took[took.length / 2] / 1024 + "us");

		System.out.println(vec1);
		System.out.println(vec2);
		System.out.println(vec3);

		System.out.println(bb);
	}

	static void benchmarkDirectArray() {
		final int runs = 64;
		final int iterations = 64 * 1024;

		float[] bb = new float[200];

		long[] took = new long[runs];
		for ( int run = 0; run < runs; run++ ) {
			long t0 = System.nanoTime();
			for ( int iteration = 0; iteration < iterations; iteration++ ) {
				bb[1 * 3 + 0] = 13;
				bb[1 * 3 + 1] += bb[1 * 3 + 1] * bb[1 * 3 + 0] + 0.3f;
				bb[1 * 3 + 2] += bb[2 * 3 + 1] + bb[1 * 3 + 0] + 0.3f;
				bb[2 * 3 + 2] += bb[2 * 3 + 1] + bb[1 * 3 + 0];
				bb[3 * 3 + 2] += bb[2 * 3 + 2] + bb[2 * 3 + 1];
			}
			long t1 = System.nanoTime();
			took[run] = t1 - t0;
		}

		Arrays.sort(took);
		System.out.println("array2 took: " + took[took.length / 2] / 1024 + "us");

		System.out.println(bb);
	}

	static void benchmarkUnsafe() {
		final int runs = 64;
		final int iterations = 64 * 1024;

		ByteBuffer bb = ByteBuffer.allocateDirect(200);
		long addr = MemoryUtil.getAddress(bb);

		long[] took = new long[runs];
		for ( int run = 0; run < runs; run++ ) {
			long t0 = System.nanoTime();
			for ( int iteration = 0; iteration < iterations; iteration++ ) {
				fput(13, addr + (1 * 3 + 0) * 4);
				fput(fget(addr + (1 * 3 + 1) * 4) + fget(addr + (1 * 3 + 1) * 4) * fget(addr + (1 * 3 + 0) * 4) + 0.3f, addr + (1 * 3 + 1) * 4);
				fput(fget(addr + (1 * 3 + 2) * 4) + fget(addr + (2 * 3 + 1) * 4) + fget(addr + (1 * 3 + 0) * 4) + 0.3f, addr + (1 * 3 + 2) * 4);
				fput(fget(addr + (2 * 3 + 2) * 4) + fget(addr + (2 * 3 + 1) * 4) + fget(addr + (1 * 3 + 0) * 4), addr + (2 * 3 + 2) * 4);
				fput(fget(addr + (3 * 3 + 2) * 4) + fget(addr + (2 * 3 + 2) * 4) + fget(addr + (2 * 3 + 1) * 4), addr + (3 * 3 + 2) * 4);
			}
			long t1 = System.nanoTime();
			took[run] = t1 - t0;
		}

		Arrays.sort(took);
		System.out.println("unsafe took: " + took[took.length / 2] / 1024 + "us");

		System.out.println(bb);
	}

}