/* 
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
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
/*
 * Created by IntelliJ IDEA.
 * User: nj
 * Date: 12-08-2002
 * Time: 15:26:52
 * To change template for new interface use
 * Code Style | Class Templates options (Tools | IDE Options).
 */
package org.lwjgl.opengl;

import java.nio.IntBuffer;

public final class NVFence {
	public static final int GL_ALL_COMPLETED_NV                                     = 0x84F2;
	public static final int GL_FENCE_STATUS_NV                                      = 0x84F3;
	public static final int GL_FENCE_CONDITION_NV                                   = 0x84F4;

	public static void glGenFencesNV(IntBuffer piFences) {
		nglGenFencesNV(piFences.remaining(), piFences, piFences.position());
	}
	private static native void nglGenFencesNV(int n, IntBuffer piFences, int piFences_offset);
	public static void glDeleteFencesNV(IntBuffer piFences) {
		nglDeleteFencesNV(piFences.remaining(), piFences, piFences.position());
	}
	private static native void nglDeleteFencesNV(int n, IntBuffer piFences, int piFences_offset);

	public static native void glSetFenceNV(int fence, int condition);

	public static native boolean glTestFenceNV(int fence);

	public static native void glFinishFenceNV(int fence);

	public static native boolean glIsFenceNV(int fence);

	public static void glGetFenceNV(int fence, int pname, IntBuffer piParams) {
		BufferChecks.checkBuffer(piParams);
		nglGetFenceivNV(fence, pname, piParams, piParams.position());
	}
	private static native void nglGetFenceivNV(int fence, int pname, IntBuffer piParams, int piParams_offset);
}
