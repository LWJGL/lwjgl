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
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.generator.*;

public interface NV_vertex_program extends NV_program {
	/*
	Accepted by the <cap> parameter of Disable, Enable, and IsEnabled,
	and by the <pname> parameter of GetBooleanv, GetIntegerv, GetFloatv,
	and GetDoublev, and by the <target> parameter of BindProgramNV,
	ExecuteProgramNV, GetProgramParameter[df]vNV, GetTrackMatrixivNV,
	LoadProgramNV, ProgramParameter[s]4[df][v]NV, and TrackMatrixNV:
	*/
	public static final int GL_VERTEX_PROGRAM_NV = 0x8620;

	/*
	Accepted by the <cap> parameter of Disable, Enable, and IsEnabled,
	and by the <pname> parameter of GetBooleanv, GetIntegerv, GetFloatv,
	and GetDoublev:
	*/
	public static final int GL_VERTEX_PROGRAM_POINT_SIZE_NV = 0x8642;
	public static final int GL_VERTEX_PROGRAM_TWO_SIDE_NV = 0x8643;

	/*
	Accepted by the <target> parameter of ExecuteProgramNV and
	LoadProgramNV:
	*/
	public static final int GL_VERTEX_STATE_PROGRAM_NV = 0x8621;

	/*
	Accepted by the <pname> parameter of GetVertexAttrib[dfi]vNV:
	*/
	public static final int GL_ATTRIB_ARRAY_SIZE_NV = 0x8623;
	public static final int GL_ATTRIB_ARRAY_STRIDE_NV = 0x8624;
	public static final int GL_ATTRIB_ARRAY_TYPE_NV = 0x8625;
	public static final int GL_CURRENT_ATTRIB_NV = 0x8626;

	/*
	Accepted by the <pname> parameter of GetProgramParameterfvNV
	and GetProgramParameterdvNV:
	*/
	public static final int GL_PROGRAM_PARAMETER_NV = 0x8644;

	/*
	Accepted by the <pname> parameter of GetVertexAttribPointervNV:
	*/
	public static final int GL_ATTRIB_ARRAY_POINTER_NV = 0x8645;

	/*
	Accepted by the <pname> parameter of GetTrackMatrixivNV:
	*/
	public static final int GL_TRACK_MATRIX_NV = 0x8648;
	public static final int GL_TRACK_MATRIX_TRANSFORM_NV = 0x8649;

	/*
	Accepted by the <pname> parameter of GetBooleanv, GetIntegerv,
	GetFloatv, and GetDoublev:
	*/
	public static final int GL_MAX_TRACK_MATRIX_STACK_DEPTH_NV = 0x862E;
	public static final int GL_MAX_TRACK_MATRICES_NV = 0x862F;
	public static final int GL_CURRENT_MATRIX_STACK_DEPTH_NV = 0x8640;
	public static final int GL_CURRENT_MATRIX_NV = 0x8641;
	public static final int GL_VERTEX_PROGRAM_BINDING_NV = 0x864A;

	/*
	Accepted by the <matrix> parameter of TrackMatrixNV:
	*/
	public static final int GL_MODELVIEW_PROJECTION_NV = 0x8629;

	/*
	Accepted by the <matrix> parameter of TrackMatrixNV and by the
	<mode> parameter of MatrixMode:
	*/
	public static final int GL_MATRIX0_NV = 0x8630;
	public static final int GL_MATRIX1_NV = 0x8631;
	public static final int GL_MATRIX2_NV = 0x8632;
	public static final int GL_MATRIX3_NV = 0x8633;
	public static final int GL_MATRIX4_NV = 0x8634;
	public static final int GL_MATRIX5_NV = 0x8635;
	public static final int GL_MATRIX6_NV = 0x8636;
	public static final int GL_MATRIX7_NV = 0x8637;

	/*
	Accepted by the <transform> parameter of TrackMatrixNV:
	*/
	public static final int GL_IDENTITY_NV = 0x862A;
	public static final int GL_INVERSE_NV = 0x862B;
	public static final int GL_TRANSPOSE_NV = 0x862C;
	public static final int GL_INVERSE_TRANSPOSE_NV = 0x862D;

	/*
	Accepted by the <array> parameter of EnableClientState and
	DisableClientState, by the <cap> parameter of IsEnabled, and by
	the <pname> parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	GetDoublev:
	*/
	public static final int GL_VERTEX_ATTRIB_ARRAY0_NV = 0x8650;
	public static final int GL_VERTEX_ATTRIB_ARRAY1_NV = 0x8651;
	public static final int GL_VERTEX_ATTRIB_ARRAY2_NV = 0x8652;
	public static final int GL_VERTEX_ATTRIB_ARRAY3_NV = 0x8653;
	public static final int GL_VERTEX_ATTRIB_ARRAY4_NV = 0x8654;
	public static final int GL_VERTEX_ATTRIB_ARRAY5_NV = 0x8655;
	public static final int GL_VERTEX_ATTRIB_ARRAY6_NV = 0x8656;
	public static final int GL_VERTEX_ATTRIB_ARRAY7_NV = 0x8657;
	public static final int GL_VERTEX_ATTRIB_ARRAY8_NV = 0x8658;
	public static final int GL_VERTEX_ATTRIB_ARRAY9_NV = 0x8659;
	public static final int GL_VERTEX_ATTRIB_ARRAY10_NV = 0x865A;
	public static final int GL_VERTEX_ATTRIB_ARRAY11_NV = 0x865B;
	public static final int GL_VERTEX_ATTRIB_ARRAY12_NV = 0x865C;
	public static final int GL_VERTEX_ATTRIB_ARRAY13_NV = 0x865D;
	public static final int GL_VERTEX_ATTRIB_ARRAY14_NV = 0x865E;
	public static final int GL_VERTEX_ATTRIB_ARRAY15_NV = 0x865F;

	/*
	Accepted by the <target> parameter of GetMapdv, GetMapfv, GetMapiv,
	Map1d and Map1f and by the <cap> parameter of Enable, Disable, and
	IsEnabled, and by the <pname> parameter of GetBooleanv, GetIntegerv,
	GetFloatv, and GetDoublev:
	*/
	public static final int GL_MAP1_VERTEX_ATTRIB0_4_NV = 0x8660;
	public static final int GL_MAP1_VERTEX_ATTRIB1_4_NV = 0x8661;
	public static final int GL_MAP1_VERTEX_ATTRIB2_4_NV = 0x8662;
	public static final int GL_MAP1_VERTEX_ATTRIB3_4_NV = 0x8663;
	public static final int GL_MAP1_VERTEX_ATTRIB4_4_NV = 0x8664;
	public static final int GL_MAP1_VERTEX_ATTRIB5_4_NV = 0x8665;
	public static final int GL_MAP1_VERTEX_ATTRIB6_4_NV = 0x8666;
	public static final int GL_MAP1_VERTEX_ATTRIB7_4_NV = 0x8667;
	public static final int GL_MAP1_VERTEX_ATTRIB8_4_NV = 0x8668;
	public static final int GL_MAP1_VERTEX_ATTRIB9_4_NV = 0x8669;
	public static final int GL_MAP1_VERTEX_ATTRIB10_4_NV = 0x866A;
	public static final int GL_MAP1_VERTEX_ATTRIB11_4_NV = 0x866B;
	public static final int GL_MAP1_VERTEX_ATTRIB12_4_NV = 0x866C;
	public static final int GL_MAP1_VERTEX_ATTRIB13_4_NV = 0x866D;
	public static final int GL_MAP1_VERTEX_ATTRIB14_4_NV = 0x866E;
	public static final int GL_MAP1_VERTEX_ATTRIB15_4_NV = 0x866F;

	/*
	Accepted by the <target> parameter of GetMapdv, GetMapfv, GetMapiv,
	Map2d and Map2f and by the <cap> parameter of Enable, Disable, and
	IsEnabled, and by the <pname> parameter of GetBooleanv, GetIntegerv,
	GetFloatv, and GetDoublev:
	*/
	public static final int GL_MAP2_VERTEX_ATTRIB0_4_NV = 0x8670;
	public static final int GL_MAP2_VERTEX_ATTRIB1_4_NV = 0x8671;
	public static final int GL_MAP2_VERTEX_ATTRIB2_4_NV = 0x8672;
	public static final int GL_MAP2_VERTEX_ATTRIB3_4_NV = 0x8673;
	public static final int GL_MAP2_VERTEX_ATTRIB4_4_NV = 0x8674;
	public static final int GL_MAP2_VERTEX_ATTRIB5_4_NV = 0x8675;
	public static final int GL_MAP2_VERTEX_ATTRIB6_4_NV = 0x8676;
	public static final int GL_MAP2_VERTEX_ATTRIB7_4_NV = 0x8677;
	public static final int GL_MAP2_VERTEX_ATTRIB8_4_NV = 0x8678;
	public static final int GL_MAP2_VERTEX_ATTRIB9_4_NV = 0x8679;
	public static final int GL_MAP2_VERTEX_ATTRIB10_4_NV = 0x867A;
	public static final int GL_MAP2_VERTEX_ATTRIB11_4_NV = 0x867B;
	public static final int GL_MAP2_VERTEX_ATTRIB12_4_NV = 0x867C;
	public static final int GL_MAP2_VERTEX_ATTRIB13_4_NV = 0x867D;
	public static final int GL_MAP2_VERTEX_ATTRIB14_4_NV = 0x867E;
	public static final int GL_MAP2_VERTEX_ATTRIB15_4_NV = 0x867F;

	public void glExecuteProgramNV(@GLenum int target, @GLuint int id, @Check("4") @Const FloatBuffer params);

	@StripPostfix("params")
	public void glGetProgramParameterfvNV(@GLenum int target, @GLuint int index, @GLenum int parameterName, @Check("4") FloatBuffer params);

	@StripPostfix("params")
	public void glGetTrackMatrixivNV(@GLenum int target, @GLuint int address, @GLenum int parameterName, @Check("4") IntBuffer params);

	@StripPostfix("params")
	public void glGetVertexAttribfvNV(@GLuint int index, @GLenum int parameterName, @Check("4") FloatBuffer params);
	@StripPostfix("params")
	public void glGetVertexAttribivNV(@GLuint int index, @GLenum int parameterName, @Check("4") IntBuffer params);

	@StripPostfix("pointer")
	public void glGetVertexAttribPointervNV(@GLuint int index, @GLenum int parameterName, @Result @GLvoid ByteBuffer pointer);

	public void glProgramParameter4fNV(@GLenum int target, @GLuint int index, float x, float y, float z, float w);

	@StripPostfix("params")
	public void glProgramParameters4fvNV(@GLenum int target, @GLuint int index, @AutoSize(value="params", expression=" >> 2") @GLuint int count,
			@Const
			FloatBuffer params);

	public void glTrackMatrixNV(@GLenum int target, @GLuint int address, @GLenum int matrix, @GLenum int transform);

	public void glVertexAttribPointerNV(@GLuint int index, int size, @GLenum int type, @GLsizei int stride,
			@BufferObject(BufferKind.ArrayVBO)
			@Check
			@Const
			@GLbyte
			@GLubyte
			@GLshort
			@GLushort
			@GLint
			@GLuint
			@GLfloat
			Buffer buffer);

	public void glVertexAttrib1sNV(@GLuint int index, short x);

	public void glVertexAttrib1fNV(@GLuint int index, float x);

	public void glVertexAttrib2sNV(@GLuint int index, short x, short y);

	public void glVertexAttrib2fNV(@GLuint int index, float x, float y);

	public void glVertexAttrib3sNV(@GLuint int index, short x, short y, short z);

	public void glVertexAttrib3fNV(@GLuint int index, float x, float y, float z);

	public void glVertexAttrib4sNV(@GLuint int index, short x, short y, short z, short w);

	public void glVertexAttrib4fNV(@GLuint int index, float x, float y, float z, float w);

	public void glVertexAttrib4ubNV(@GLuint int index, @GLubyte byte x, @GLubyte byte y, @GLubyte byte z, @GLubyte byte w);

	@StripPostfix("v")
	public void glVertexAttribs1svNV(@GLuint int index, @AutoSize("v") @GLsizei int n, @Const ShortBuffer v);
	@StripPostfix("v")
	public void glVertexAttribs1fvNV(@GLuint int index, @AutoSize("v") @GLsizei int n, @Const FloatBuffer v);

	@StripPostfix("v")
	public void glVertexAttribs2svNV(@GLuint int index, @AutoSize(value="v", expression=" >> 1") @GLsizei int n, @Const ShortBuffer v);
	@StripPostfix("v")
	public void glVertexAttribs2fvNV(@GLuint int index, @AutoSize(value="v", expression=" >> 1") @GLsizei int n, @Const FloatBuffer v);
	
	@StripPostfix("v")
	public void glVertexAttribs3svNV(@GLuint int index, @AutoSize(value="v", expression=" / 3") @GLsizei int n, @Const ShortBuffer v);
	@StripPostfix("v")
	public void glVertexAttribs3fvNV(@GLuint int index, @AutoSize(value="v", expression=" / 3") @GLsizei int n, @Const FloatBuffer v);

	@StripPostfix("v")
	public void glVertexAttribs4svNV(@GLuint int index, @AutoSize(value="v", expression=" >> 2") @GLsizei int n, @Const ShortBuffer v);
	@StripPostfix("v")
	public void glVertexAttribs4fvNV(@GLuint int index, @AutoSize(value="v", expression=" >> 2") @GLsizei int n, @Const FloatBuffer v);
}
