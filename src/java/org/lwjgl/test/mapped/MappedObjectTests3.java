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
import org.lwjgl.util.mapped.MappedObject;
import org.lwjgl.util.mapped.MappedSet;
import org.lwjgl.util.mapped.MappedSet2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.lwjgl.util.mapped.MappedObject.*;

/** @author Riven */
@SuppressWarnings("static-access")
public class MappedObjectTests3 {

	static void testMappedBuffer() {
		int elementCount = 4;

		MappedSomething some = MappedSomething.malloc(elementCount);

		assert (some.data != some.data);

		long addr1 = MemoryUtil.getAddress(some.backingByteBuffer());

		ByteBuffer mapped = some.data; // creates new ByteBuffer instance
		long addr2 = MemoryUtil.getAddress(mapped);

		assert (addr2 - addr1 == 4);
		assert (mapped.capacity() == MappedSomething.SIZEOF - 4);

		{
			assert (some.shared == 0);
			assert (mapped.getInt(8) == 0);

			some.shared = 1234;

			assert (some.shared == 1234);
			assert (mapped.getInt(8) == 1234);
		}

		some.view++;
		mapped = some.data; // creates new ByteBuffer instance

		long addr3 = MemoryUtil.getAddress(mapped);
		assert (addr3 - addr1 == 4 + MappedSomething.SIZEOF);
		assert (addr3 - addr2 == 0 + MappedSomething.SIZEOF);
		assert (mapped.capacity() == MappedSomething.SIZEOF - 4);
	}

	static void testForeach() {
		int elementCount = 10;
		MappedSomething some = MappedSomething.malloc(elementCount);

		int i = 0;
		for ( MappedSomething item : foreach(some, elementCount / 2) ) {
			assert (item.view == i++);
		}
		assert (some.view == (elementCount / 2) - 1);
		System.out.println("current.view=" + some.view + ", not " + (elementCount / 2) + ", as you might expect");

		i = 0;
		for ( MappedSomething item : foreach(some) ) {
			assert (item.view == i++);
		}
		assert (some.view == elementCount - 1);
	}

	public static class Xyz extends MappedObject {

		int x, y, z;
	}

	static void testConstructor() {
		int capacity = 1024;
		ByteBuffer bb = ByteBuffer.allocateDirect(capacity).order(ByteOrder.nativeOrder());
		long address = MemoryUtil.getAddress(bb);

		MappedFloat mf = MappedFloat.map(address, capacity);

		assert (address == mf.baseAddress);

		assert (mf.value == 0.0f);
		mf.view = 1;
		assert (mf.value == 0.0f);
		mf.runViewConstructor();
		assert (mf.value == 4.0f);

		Xyz.malloc(3);
	}

	static void testMappedSet() {
		MappedVec2 vec2 = MappedVec2.malloc(3);
		MappedVec3 vec3 = MappedVec3.malloc(3);

		MappedSet2 set = MappedSet.create(vec2, vec3);

		assert (vec2.view == 0);
		assert (vec3.view == 0);

		set.view = 2;
		assert (vec2.view == 2);
		assert (vec3.view == 2);

		set.view = 0;
		assert (vec2.view == 0);
		assert (vec3.view == 0);

		set.next();
		assert (vec2.view == 1);
		assert (vec3.view == 1);
	}

}