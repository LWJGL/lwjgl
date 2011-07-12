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

import java.nio.ByteBuffer;

/** @author Riven */
@SuppressWarnings("static-access")
public class MappedObjectTests2 {

	static void testWriteFieldAccess(MappedVec3 vecs) {
		// write access results into a transform-time IllegalAccessException

		System.out.println(vecs.baseAddress); // test read-access

		System.out.println(vecs.viewAddress); // test read-access

		System.out.println(vecs.align); // test read-access

		System.out.println(MappedVec3.SIZEOF); // test read-access
	}

	static void testFields() {
		ByteBuffer bb = ByteBuffer.allocateDirect(200);
		MappedVec3 vecs = MappedVec3.map(bb);

		testWriteFieldAccess(vecs);

		vecs.x = 13.13f;
		vecs.y = 14.14f;
		vecs.z = 15.15f;

		System.out.println(vecs.viewAddress);

		assert (vecs.x == 13.13f);
		assert (vecs.y == 14.14f);
		assert (vecs.z == 15.15f);

		vecs.view = 0;

		assert (vecs.x == 13.13f);
		assert (vecs.y == 14.14f);
		assert (vecs.z == 15.15f);

		System.out.println(vecs);
		vecs.view = 1;
		System.out.println(vecs);

		assert (vecs.x == 0.0f);
		assert (vecs.y == 0.0f);
		assert (vecs.z == 0.0f);

		// now it becomes weird: offsets and strides

		vecs.view = 1;
		vecs.x = 0.1234f;
		vecs.view = 0;

		// test stride & sizeof
		{
			long a1 = vecs.viewAddress;
			vecs.view = 1;
			long a2 = vecs.viewAddress;
			assert (a2 - a1 == MappedVec3.SIZEOF);
			assert (a2 - a1 == vecs.stride);
			vecs.view = 0;
		}

		int newStride = 16;

		MappedVec3.configure(vecs, newStride, +4);
		assert (vecs.z == 0.1234f); // vecs[1].x ended up in vecs[0].z due to 1float offset

		MappedVec3.configure(vecs, newStride, +8);
		assert (vecs.z == 0.0000f); // vecs[1].z ended up in vecs[0].z due to 2float offset

		// test new stride
		{
			long a1 = vecs.viewAddress;
			vecs.view = 1;
			long a2 = vecs.viewAddress;
			assert (a2 - a1 == newStride);
			vecs.view = 0;
		}

		// example: GPU => VBO => VTN
		{
			MappedVec3 v = MappedVec3.map(bb);
			MappedVec2 t = MappedVec2.map(bb);
			MappedVec3 n = MappedVec3.map(bb);

			int stride = MappedVec3.SIZEOF + MappedVec2.SIZEOF + MappedVec3.SIZEOF;
			assert (stride == 32);

			MappedVec3.configure(v, stride, 0);
			MappedVec2.configure(t, stride, MappedVec3.SIZEOF);
			MappedVec3.configure(n, stride, MappedVec3.SIZEOF + MappedVec2.SIZEOF);
		}
	}

}