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
 * Created by LWJGL.
 * User: spasi
 * Date: 17 Дек 2003
 * Time: 3:58:43 рм
 */

package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public final class ARBVertexShader {

	/*
	* Accepted by the <shaderType> argument of CreateShaderObjectARB:
	*/
	public static final int GL_VERTEX_SHADER_ARB = 0x8B31;

	/*
	* Accepted by the <pname> parameter of GetBooleanv, GetIntegerv,
	* GetFloatv, and GetDoublev:
	*/
	public static final int GL_MAX_VERTEX_UNIFORM_COMPONENTS_ARB = 0x8B4A;
	public static final int GL_MAX_VARYING_FLOATS_ARB = 0x8B4B;
	public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS_ARB = 0x8B4C;
	public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS_ARB = 0x8B4D;

	/*
	* Accepted by the <pname> parameter GetObjectParameter{if}vARB:
	*/
	public static final int GL_OBJECT_ACTIVE_ATTRIBUTES_ARB = 0x8B89;
	public static final int GL_OBJECT_ACTIVE_ATTRIBUTE_MAX_LENGTH_ARB = 0x8B8A;


	// ---------------------------
	public static void glBindAttribLocationARB(int programObj, int index, ByteBuffer name) {
		if ( name.get(name.limit() - 1) != 0 ) {
			throw new RuntimeException("<name> must be a null-terminated string.");
		}
		nglBindAttribLocationARB(programObj, index, name, name.position());
	}

	private static native void nglBindAttribLocationARB(int programObj, int index, ByteBuffer name, int nameOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetActiveAttribARB(int programObj, int index,
	                                        IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		BufferChecks.checkBuffer(name);

		if ( length == null )
			nglGetActiveAttribARB(programObj, index, name.remaining(), null, -1,
			                      size, size.position(), type, type.position(), name, name.position());
		else {
			nglGetActiveAttribARB(programObj, index, name.remaining(), length, length.position(),
			                      size, size.position(), type, type.position(), name, name.position());
		}
	}

	private static native void nglGetActiveAttribARB(int programObj, int index, int maxLength,
	                                                 IntBuffer length, int lengthOffset,
	                                                 IntBuffer size, int sizeOffset,
	                                                 IntBuffer type, int typeOffset,
	                                                 ByteBuffer name, int nameOffset);
	// ---------------------------

	// ---------------------------
	public static int glGetAttribLocationARB(int programObj, ByteBuffer name) {
		if ( name.get(name.limit() - 1) != 0 ) {
			throw new RuntimeException("<name> must be a null-terminated string.");
		}
		return nglGetAttribLocationARB(programObj, name, name.position());
	}

	private static native int nglGetAttribLocationARB(int programObj, ByteBuffer name, int nameOffset);
	// ---------------------------

}