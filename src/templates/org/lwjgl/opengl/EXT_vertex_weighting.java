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
import java.nio.FloatBuffer;

import org.lwjgl.generator.*;

public interface EXT_vertex_weighting {
	public static final int GL_MODELVIEW0_STACK_DEPTH_EXT = 0x0BA3;
	public static final int GL_MODELVIEW1_STACK_DEPTH_EXT = 0x8502;
	public static final int GL_MODELVIEW0_MATRIX_EXT = 0x0BA6;
	public static final int GL_MODELVIEW1_MATRIX_EXT = 0x8506;
	public static final int GL_VERTEX_WEIGHTING_EXT = 0x8509;
	public static final int GL_MODELVIEW0_EXT = 0x1700;
	public static final int GL_MODELVIEW1_EXT = 0x850A;
	public static final int GL_CURRENT_VERTEX_WEIGHT_EXT = 0x850B;
	public static final int GL_VERTEX_WEIGHT_ARRAY_EXT = 0x850C;
	public static final int GL_VERTEX_WEIGHT_ARRAY_SIZE_EXT = 0x850D;
	public static final int GL_VERTEX_WEIGHT_ARRAY_TYPE_EXT = 0x850E;
	public static final int GL_VERTEX_WEIGHT_ARRAY_STRIDE_EXT = 0x850F;
	public static final int GL_VERTEX_WEIGHT_ARRAY_POINTER_EXT = 0x8510;

	public void glVertexWeightfEXT(float weight);

	public void glVertexWeightPointerEXT(@GLsizei int size, @AutoType("pPointer") @GLenum int type, @GLsizei int stride,
			@BufferObject(BufferKind.ArrayVBO)
			@Check
			@Const
			@GLfloat
			Buffer pPointer);
}
