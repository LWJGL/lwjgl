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
 * Time: 15:30:22
 * To change template for new interface use
 * Code Style | Class Templates options (Tools | IDE Options).
 */
package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class NVEvaluators {
	public static final int GL_EVAL_2D_NV                                           = 0x86C0;
	public static final int GL_EVAL_TRIANGULAR_2D_NV                                = 0x86C1;
	public static final int GL_MAP_TESSELLATION_NV                                  = 0x86C2;
	public static final int GL_MAP_ATTRIB_U_ORDER_NV                                = 0x86C3;
	public static final int GL_MAP_ATTRIB_V_ORDER_NV                                = 0x86C4;
	public static final int GL_EVAL_FRACTIONAL_TESSELLATION_NV                      = 0x86C5;
	public static final int GL_EVAL_VERTEX_ATTRIB0_NV                               = 0x86C6;
	public static final int GL_EVAL_VERTEX_ATTRIB1_NV                               = 0x86C7;
	public static final int GL_EVAL_VERTEX_ATTRIB2_NV                               = 0x86C8;
	public static final int GL_EVAL_VERTEX_ATTRIB3_NV                               = 0x86C9;
	public static final int GL_EVAL_VERTEX_ATTRIB4_NV                               = 0x86CA;
	public static final int GL_EVAL_VERTEX_ATTRIB5_NV                               = 0x86CB;
	public static final int GL_EVAL_VERTEX_ATTRIB6_NV                               = 0x86CC;
	public static final int GL_EVAL_VERTEX_ATTRIB7_NV                               = 0x86CD;
	public static final int GL_EVAL_VERTEX_ATTRIB8_NV                               = 0x86CE;
	public static final int GL_EVAL_VERTEX_ATTRIB9_NV                               = 0x86CF;
	public static final int GL_EVAL_VERTEX_ATTRIB10_NV                              = 0x86D0;
	public static final int GL_EVAL_VERTEX_ATTRIB11_NV                              = 0x86D1;
	public static final int GL_EVAL_VERTEX_ATTRIB12_NV                              = 0x86D2;
	public static final int GL_EVAL_VERTEX_ATTRIB13_NV                              = 0x86D3;
	public static final int GL_EVAL_VERTEX_ATTRIB14_NV                              = 0x86D4;
	public static final int GL_EVAL_VERTEX_ATTRIB15_NV                              = 0x86D5;
	public static final int GL_MAX_MAP_TESSELLATION_NV                              = 0x86D6;
	public static final int GL_MAX_RATIONAL_EVAL_ORDER_NV                           = 0x86D7;

	public static void glGetMapControlPointsNV(int target, int index, int type, int ustride, int vstride, boolean packed, FloatBuffer pPoints) {
		// TODO:Check buffer size
		nglGetMapControlPointsNV(target, index, type, ustride, vstride, packed, pPoints, pPoints.position()<<2);
	}
	private static native void nglGetMapControlPointsNV(int target, int index, int type, int ustride, int vstride, boolean packed, Buffer pPoints, int pPoints_offset);
	public static void glMapControlPointsNV(int target, int index, int type, int ustride, int vstride, int uorder, int vorder, boolean packed, FloatBuffer pPoints) {
		// TODO:Check buffer size
		nglMapControlPointsNV(target, index, type, ustride, vstride, uorder, vorder, packed, pPoints, pPoints.position()<<2);
	}
	private static native void nglMapControlPointsNV(int target, int index, int type, int ustride, int vstride, int uorder, int vorder, boolean packed, Buffer pPoints, int pPoints_offset);
	public static void glMapParameterNV(int target, int pname, FloatBuffer pfParams) {
		BufferChecks.checkBuffer(pfParams);
		nglMapParameterfvNV(target, pname, pfParams, pfParams.position());
	}
	private static native void nglMapParameterfvNV(int target, int pname, FloatBuffer pfParams, int pfParams_offset);
	public static void glMapParameterNV(int target, int pname, IntBuffer piParams) {
		BufferChecks.checkBuffer(piParams);
		nglMapParameterivNV(target, pname, piParams, piParams.position());
	}
	private static native void nglMapParameterivNV(int target, int pname, IntBuffer piParams, int piParams_offset);

	public static void glGetMapParameterNV(int target, int pname, FloatBuffer pfParams) {
		BufferChecks.checkBuffer(pfParams);
		nglGetMapParameterfvNV(target, pname, pfParams, pfParams.position());
	}
	private static native void nglGetMapParameterfvNV(int target, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetMapParameterNV(int target, int pname, IntBuffer piParams) {
		BufferChecks.checkBuffer(piParams);
		nglGetMapParameterivNV(target, pname, piParams, piParams.position());
	}
	private static native void nglGetMapParameterivNV(int target, int pname, IntBuffer piParams, int piParams_offset);
	public static void glGetMapAttribParameterNV(int target, int index, int pname, FloatBuffer pfParams) {
		BufferChecks.checkBuffer(pfParams);
		nglGetMapAttribParameterfvNV(target, index, pname, pfParams, pfParams.position());
	}
	private static native void nglGetMapAttribParameterfvNV(int target, int index, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetMapAttribParameterNV(int target, int index, int pname, IntBuffer piParams) {
		BufferChecks.checkBuffer(piParams);
		nglGetMapAttribParameterivNV(target, index, pname, piParams, piParams.position());
	}
	private static native void nglGetMapAttribParameterivNV(int target, int index, int pname, IntBuffer piParams, int piParams_offset);
	public static native void glEvalMapsNV(int target, int mode);
}
