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
import org.lwjgl.util.mapped.MappedHelper;

import java.nio.ByteBuffer;

/** @author Riven */
@SuppressWarnings("static-access")
public class MappedObjectTests1 {

	static class Test {

		int value;
	}

	static void testViewField() {
		Test test = new Test();
		test.value = 13;
		test.value += 1;
		test.value++;
		System.out.println(test);

		ByteBuffer bb = ByteBuffer.allocateDirect(200);
		MappedFloat vecs = MappedFloat.map(bb);

		// verify 'malloc', SIZEOF and capacity()
		{
			MappedFloat vecs1 = MappedFloat.malloc(1234);

			assert (vecs1.getSizeof() == MappedFloat.SIZEOF);
			assert (vecs1.getSizeof() * 1234 == vecs1.backingByteBuffer().capacity());
			assert (MappedFloat.SIZEOF * 1234 == vecs1.backingByteBuffer().capacity());
			assert(vecs1.capacity() == vecs1.backingByteBuffer().capacity() / MappedFloat.SIZEOF);

			ByteBuffer buf = ByteBuffer.allocateDirect(200);
			buf.position(10 * MappedFloat.SIZEOF);

			MappedFloat vecs2 = MappedFloat.map(buf);
			assert(vecs2.capacity() == (vecs2.backingByteBuffer().capacity() / MappedFloat.SIZEOF) - 10);
		}

		// manipulate 'mapped.value'
		{
			assert (vecs.value == 0.0f); // 4.0 is set in constructor, but runViewConstructor is not called
			vecs.value = 1.1f;
			assert (vecs.value == 1.1f);
		}

		// manipulate 'view' with assignment
		{
			assert (vecs.view == 0);
			vecs.view = 1;
			assert (vecs.view == 1);
			assert (vecs.value != 1.1f); // old view
			vecs.view = 0;
		}

		// manipulate 'view' with iinc
		{
			assert (vecs.view == 0);
			vecs.view++;
			assert (vecs.view == 1);
			assert (vecs.value != 1.1f); // old view
			vecs.view = 0;
		}

		// manipulate 'view' with next()
		{
			assert (vecs.view == 0);
			vecs.next();
			assert (vecs.view == 1);
			assert (vecs.value != 1.1f); // old view
			vecs.view = 0;
		}

		// test bounds checking
		{
			assert (vecs.view == 0);
			try {
				vecs.view = 49;
				assert vecs.view == 49;
				vecs.view = 0;
				vecs.view = 50;
				System.out.println("org.lwjgl.util.mapped.Checks is false or there is a bug in bounds checking.");
				vecs.view = 0;
			} catch (IndexOutOfBoundsException e) {
				// expected, ignore
			}

			assert (vecs.view == 0);

			try {
				vecs.view = 10;
				MappedFloat vecs2 = vecs.slice();
				vecs.view = 0;

				vecs2.view = 39;
				assert vecs2.view == 39;
				vecs2.view = 40;
				System.out.println("org.lwjgl.util.mapped.Checks is false or there is a bug in bounds checking.");
			} catch (IndexOutOfBoundsException e) {
				// expected, ignore
			}

			try {
				ByteBuffer posTest = ByteBuffer.allocateDirect(200);
				posTest.position(10 * MappedFloat.SIZEOF); // position > 0

				MappedFloat vecs2 = MappedFloat.map(posTest);
				vecs2.view = 39;
				assert vecs2.view == 39;
				vecs2.view = 40;
				System.out.println("org.lwjgl.util.mapped.Checks is false or there is a bug in bounds checking.");
			} catch (IndexOutOfBoundsException e) {
				// expected, ignore
			}
		}

		// test newBuffer
		{
			long addr1 = MemoryUtil.getAddress(bb);
			ByteBuffer bb2 = MappedHelper.newBuffer(addr1, bb.capacity());
			long addr2 = MemoryUtil.getAddress(bb);

			System.out.println(bb);
			System.out.println(bb2);
			System.out.println(addr1);
			System.out.println(addr2);
		}

		// test 'copy'
		{
			vecs.value = 13.37f;
			MappedFloat dec2 = vecs.dup();
			dec2.view = 1;
			System.out.println(vecs);
			System.out.println(dec2);
			vecs.copyTo(dec2);
			System.out.println(vecs);
			System.out.println(dec2);
			assert (dec2.value == 13.37f);

			vecs.value = 73.31f;
			vecs.copyRange(dec2, 1);
			assert (dec2.value == 73.31f);
		}
	}
}
