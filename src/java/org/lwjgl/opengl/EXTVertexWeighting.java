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
 * Time: 15:25:57
 * To change template for new interface use
 * Code Style | Class Templates options (Tools | IDE Options).
 */
package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.FloatBuffer;


public class EXTVertexWeighting {
	public static final int GL_MODELVIEW0_STACK_DEPTH_EXT                           = 0x0BA3;  /* alias to MODELVIEW_STACK_DEPTH */
	public static final int GL_MODELVIEW1_STACK_DEPTH_EXT                           = 0x8502;
	public static final int GL_MODELVIEW0_MATRIX_EXT                                = 0x0BA6;  /* alias to MODELVIEW_MATRIX */
	public static final int GL_MODELVIEW1_MATRIX_EXT                                = 0x8506;
	public static final int GL_VERTEX_WEIGHTING_EXT                                 = 0x8509;
	public static final int GL_MODELVIEW0_EXT                                       = 0x1700;  /* alias to MODELVIEW */
	public static final int GL_MODELVIEW1_EXT                                       = 0x850A;
	public static final int GL_CURRENT_VERTEX_WEIGHT_EXT                            = 0x850B;
	public static final int GL_VERTEX_WEIGHT_ARRAY_EXT                              = 0x850C;
	public static final int GL_VERTEX_WEIGHT_ARRAY_SIZE_EXT                         = 0x850D;
	public static final int GL_VERTEX_WEIGHT_ARRAY_TYPE_EXT                         = 0x850E;
	public static final int GL_VERTEX_WEIGHT_ARRAY_STRIDE_EXT                       = 0x850F;
	public static final int GL_VERTEX_WEIGHT_ARRAY_POINTER_EXT                      = 0x8510;

	public static native void glVertexWeightfEXT(float weight);

	public static void glVertexWeightPointerEXT(int size, int stride, FloatBuffer pPointer) {
		BufferChecks.ensureArrayVBOdisabled();
		nglVertexWeightPointerEXT(size, GL11.GL_FLOAT, stride, pPointer, pPointer.position()<<2);
	}
	private static native void nglVertexWeightPointerEXT(int size, int type, int stride, Buffer pPointer, int pPointer_offset);
	public static void glVertexWeightPointerEXT(int size, int type, int stride, int buffer_offset) {
		BufferChecks.ensureArrayVBOenabled();
		nglVertexWeightPointerEXTVBO(size, type, stride, buffer_offset);
	}
	private static native void nglVertexWeightPointerEXTVBO(int size, int type, int stride, int buffer_offset);
}
