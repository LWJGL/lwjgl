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
import org.lwjgl.PointerBuffer;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.mapped.MappedObject;
import org.lwjgl.util.mapped.Pointer;

import java.io.File;
import java.nio.ByteBuffer;

/** @author Riven */
@SuppressWarnings("static-access")
public class MappedObjectTests4 {

	public static void testLWJGL() throws Exception {
		System.out.println(new File(System.getProperty("java.library.path")).getCanonicalPath());
		Display.create();
	}

	public static void testLocalView() {
		MappedFloat mapped1 = MappedFloat.malloc(5);
		MappedFloat mapped2 = MappedFloat.malloc(5);

		testLocalView(mapped1);
		testLocalView(mapped1, mapped2);

		MappedSomething some = MappedSomething.malloc(5);
		testLocalView(some);
	}

	private static void testLocalView(MappedFloat mapped1) {
		final MappedFloat[] array = mapped1.asArray();

		assert (array.length == 5);

		int l = 10 * array.length;
		for ( int localView1 = 0; localView1 < 5; localView1++ ) {
			array[localView1].value = localView1 * 5;
			array[localView1].value *= 2.0f;
		}
		System.out.println();
		float x = 0.0f;
		for ( int localView1 = 0; localView1 < 5; localView1++ ) {
			System.out.println("[" + localView1 + "] =>" + array[localView1].value);
			x += array[localView1].value;
		}
		System.out.println("x = " + x);
	}

	private static void testLocalView(MappedFloat mo1, MappedFloat mo2) {
		final MappedFloat[] array1 = mo1.<MappedFloat>asArray();
		for ( int v1 = 0; v1 < 5; v1++ ) {
			array1[v1].value = (float)Math.random();
			array1[v1].value *= 2.0f;
		}
		final MappedFloat[] array2 = mo2.<MappedFloat>asArray();
		for ( int v2 = 0; v2 < 5; v2++ ) {
			array2[v2].value = (float)Math.random();
			array2[v2].value *= 2.0f;
		}

		System.out.println();

		for ( int v1 = 0; v1 < 5; v1++ ) {
			System.out.println("[" + v1 + "] =>" + array1[v1].value);
		}
		for ( int v2 = 0; v2 < 5; v2++ ) {
			System.out.println("[" + v2 + "] =>" + array2[v2].value);
		}
	}

	private static void testLocalView(MappedSomething some) {
		final MappedSomething[] array = some.asArray();

		assert (array.length == 5);

		final long baseAddress = MemoryUtil.getAddress(some.backingByteBuffer());
		for ( int i = 0; i < array.length; i++ ) {
			ByteBuffer data = array[i].data;

			assert (data.capacity() == (64 - 4));
			assert (MemoryUtil.getAddress(data) == baseAddress + i * MappedSomething.SIZEOF + 4);
		}
	}

	public static class MappedPointer extends MappedObject {

		int foo;
		@Pointer long pointer;
		int bar;

	}

	public static void testPointer() {
		MappedPointer data = MappedPointer.malloc(100);

		assert (data.backingByteBuffer().capacity() == 100 * (4 + 4 + PointerBuffer.getPointerSize()));

		for ( int i = 0; i < 100; i++ ) {
			data.view = i;

			data.foo = i;
			data.pointer = i * 1000;
			data.bar = i * 2;
		}

		for ( int i = 0; i < 100; i++ ) {
			data.view = i;

			assert (data.foo == i);
			assert (data.pointer == i * 1000);
			assert (data.bar == i * 2);
		}
	}

}