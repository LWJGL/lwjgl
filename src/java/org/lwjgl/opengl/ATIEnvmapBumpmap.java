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

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;

public final class ATIEnvmapBumpmap {
	public static final int GL_BUMP_ROT_MATRIX_ATI                                  = 0x8775;
	public static final int GL_BUMP_ROT_MATRIX_SIZE_ATI                             = 0x8776;
	public static final int GL_BUMP_NUM_TEX_UNITS_ATI                               = 0x8777;
	public static final int GL_BUMP_TEX_UNITS_ATI                                   = 0x8778;
	public static final int GL_DUDV_ATI                                             = 0x8779;
	public static final int GL_DU8DV8_ATI                                           = 0x877A;
	public static final int GL_BUMP_ENVMAP_ATI                                      = 0x877B;
	public static final int GL_BUMP_TARGET_ATI                                      = 0x877C;

	static native void initNativeStubs() throws LWJGLException;

	public static void glTexBumpParameterATI(int pname, FloatBuffer pfParam) {
		BufferChecks.checkBuffer(pfParam);
		nglTexBumpParameterfvATI(pname, pfParam, pfParam.position());
	}
	private static native void nglTexBumpParameterfvATI(int pname, FloatBuffer pfParam, int pfParam_offset);

	public static void glTexBumpParameterATI(int pname, IntBuffer piParam) {
		BufferChecks.checkBuffer(piParam);
		nglTexBumpParameterivATI(pname, piParam, piParam.position());
	}
	private static native void nglTexBumpParameterivATI(int pname, IntBuffer piParam, int piParam_offset);

	public static void glGetTexBumpParameterATI(int pname, FloatBuffer pfParam) {
		BufferChecks.checkBuffer(pfParam);
		nglGetTexBumpParameterfvATI(pname, pfParam, pfParam.position());
	}
	private static native void nglGetTexBumpParameterfvATI(int pname, FloatBuffer pfParam, int pfParam_offset);

	public static void glGetTexBumpParameterATI(int pname, IntBuffer piParam) {
		BufferChecks.checkBuffer(piParam);
		nglGetTexBumpParameterivATI(pname, piParam, piParam.position());
	}
	private static native void nglGetTexBumpParameterivATI(int pname, IntBuffer piParam, int piParam_offset);
}
