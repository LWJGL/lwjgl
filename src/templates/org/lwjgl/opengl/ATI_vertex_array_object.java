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

import org.lwjgl.generator.*;

public interface ATI_vertex_array_object {
	public static final int GL_STATIC_ATI = 0x8760;
	public static final int GL_DYNAMIC_ATI = 0x8761;
	public static final int GL_PRESERVE_ATI = 0x8762;
	public static final int GL_DISCARD_ATI = 0x8763;
	public static final int GL_OBJECT_BUFFER_SIZE_ATI = 0x8764;
	public static final int GL_OBJECT_BUFFER_USAGE_ATI = 0x8765;
	public static final int GL_ARRAY_OBJECT_BUFFER_ATI = 0x8766;
	public static final int GL_ARRAY_OBJECT_OFFSET_ATI = 0x8767;

	@GenerateAutos
	public @GLuint int glNewObjectBufferATI(@AutoSize("pPointer") @GLsizei int size,
			@Const
			@GLbyte
			@GLshort
			@GLint
			@GLfloat
			Buffer pPointer, @GLenum int usage);

	public boolean glIsObjectBufferATI(@GLuint int buffer);

	public void glUpdateObjectBufferATI(@GLuint int buffer, @GLuint int offset, @AutoSize("pPointer") @GLsizei int size,
			@Const
			@GLbyte
			@GLshort
			@GLint
			@GLfloat
			Buffer pPointer, @GLenum int preserve);

	@StripPostfix("params")
	public void glGetObjectBufferfvATI(@GLuint int buffer, @GLenum int pname, @Check FloatBuffer params);
	@StripPostfix("params")
	public void glGetObjectBufferivATI(@GLuint int buffer, @GLenum int pname, @Check IntBuffer params);

	public void glFreeObjectBufferATI(@GLuint int buffer);

	public void glArrayObjectATI(@GLenum int array, int size, @GLenum int type, @GLsizei int stride, @GLuint int buffer, @GLuint int offset);

	@StripPostfix("params")
	public void glGetArrayObjectfvATI(@GLenum int array, @GLenum int pname, @Check("4") FloatBuffer params);
	@StripPostfix("params")
	public void glGetArrayObjectivATI(@GLenum int array, @GLenum int pname, @Check("4") IntBuffer params);

	public void glVariantArrayObjectATI(@GLuint int id, @GLenum int type, @GLsizei int stride, @GLuint int buffer, @GLuint int offset);

	@StripPostfix("params")
	public void glGetVariantArrayObjectfvATI(@GLuint int id, @GLenum int pname, @Check("4") FloatBuffer params);
	@StripPostfix("params")
	public void glGetVariantArrayObjectivATI(@GLuint int id, @GLenum int pname, @Check("4") IntBuffer params);
}
