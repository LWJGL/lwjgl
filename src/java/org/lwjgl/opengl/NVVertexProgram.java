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

public final class NVVertexProgram extends NVProgram {

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

  // ---------------------------

  public static void glExecuteProgramNV(int target, int id, FloatBuffer params) {
  	BufferChecks.checkBuffer(params);
    nglExecuteProgramNV(target, id, params, params.position());

  }

  private static native void nglExecuteProgramNV(int target, int id, FloatBuffer params, int paramsOffset);

  // ---------------------------

  // ---------------------------

  public static void glGetProgramParameterNV(int target, int index, int parameterName, FloatBuffer params) {
  	BufferChecks.checkBuffer(params);
  	nglGetProgramParameterfvNV(target, index, parameterName, params, params.position());

  }

  private static native void nglGetProgramParameterfvNV(
    int target,
    int index,
    int parameterName,
    FloatBuffer params,
    int paramsOffset);

  // ---------------------------

  // ---------------------------

  public static void glGetTrackMatrixNV(int target, int address, int parameterName, IntBuffer params) {
  	BufferChecks.checkBuffer(params);
    nglGetTrackMatrixivNV(target, address, parameterName, params, params.position());

  }

  private static native void nglGetTrackMatrixivNV(
    int target,
    int address,
    int parameterName,
    IntBuffer params,
    int paramsOffset);

  // ---------------------------

  // ---------------------------

  public static void glGetVertexAttribNV(int index, int parameterName, FloatBuffer params) {
  	BufferChecks.checkBuffer(params);
    nglGetVertexAttribfvNV(index, parameterName, params, params.position());

  }

  private static native void nglGetVertexAttribfvNV(int index, int parameterName, FloatBuffer params, int paramsOffset);

  // ---------------------------

  // ---------------------------

  public static void glGetVertexAttribNV(int index, int parameterName, IntBuffer params) {
  	BufferChecks.checkBuffer(params);
  	nglGetVertexAttribivNV(index, parameterName, params, params.position());

  }

  private static native void nglGetVertexAttribivNV(int index, int parameterName, IntBuffer params, int paramsOffset);

  // ---------------------------

  public static native ByteBuffer glGetVertexAttribPointerNV(int index, int parameterName, int size);

  public static native void glProgramParameter4fNV(int target, int index, float x, float y, float z, float w);

  // ---------------------------

  public static void glProgramParameters4NV(int target, int index, int count, FloatBuffer params) {

  	// Special case buffer check
  	if (params.remaining() < count * 4) {
  		throw new BufferOverflowException();
  	}

    nglProgramParameters4fvNV(target, index, count, params, params.position());

  }

  private static native void nglProgramParameters4fvNV(
    int target,
    int index,
    int count,
    FloatBuffer params,
    int paramsOffset);

  // ---------------------------

  public static native void glTrackMatrixNV(int target, int address, int matrix, int transform);

  public static void glVertexAttribPointerNV(int index, int size, boolean unsigned, int stride, ByteBuffer buffer) {

  	BufferChecks.ensureArrayVBOdisabled();

    nglVertexAttribPointerNV(
      index,
      size,
      unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE,
      stride,
      buffer,
      buffer.position());

  }

  public static void glVertexAttribPointerNV(int index, int size, boolean unsigned, int stride, ShortBuffer buffer) {

  	BufferChecks.ensureArrayVBOdisabled();

    nglVertexAttribPointerNV(
      index,
      size,
      unsigned ? GL11.GL_UNSIGNED_SHORT : GL11.GL_SHORT,
      stride,
      buffer,
      buffer.position() << 1);

  }

  public static void glVertexAttribPointerNV(int index, int size, int stride, FloatBuffer buffer) {

  	BufferChecks.ensureArrayVBOdisabled();

    nglVertexAttribPointerNV(index, size, GL11.GL_FLOAT, stride, buffer, buffer.position() << 2);

  }

  public static void glVertexAttribPointerNV(int index, int size, boolean unsigned, int stride, IntBuffer buffer) {

  	BufferChecks.ensureArrayVBOdisabled();

    nglVertexAttribPointerNV(
      index,
      size,
      unsigned ? GL11.GL_UNSIGNED_INT : GL11.GL_INT,
      stride,
      buffer,
      buffer.position() << 2);

  }

  private static native void nglVertexAttribPointerNV(
    int index,
    int size,
    int type,
    int stride,
    Buffer buffer,
    int bufferOffset);

  // ---------------------------

  public static void glVertexAttribPointerNV(int index, int size, int type, int stride, int bufferOffset) {

  	BufferChecks.ensureArrayVBOenabled();

    nglVertexAttribPointerNVVBO(index, size, type, stride, bufferOffset);

  }

  private static native void nglVertexAttribPointerNVVBO(int index, int size, int type, int stride, int bufferOffset);

  // ---------------------------

  public static native void glVertexAttrib1sNV(int index, short x);

  public static native void glVertexAttrib1fNV(int index, float x);

  public static native void glVertexAttrib2sNV(int index, short x, short y);

  public static native void glVertexAttrib2fNV(int index, float x, float y);

  public static native void glVertexAttrib3sNV(int index, short x, short y, short z);

  public static native void glVertexAttrib3fNV(int index, float x, float y, float z);

  public static native void glVertexAttrib4sNV(int index, short x, short y, short z, short w);

  public static native void glVertexAttrib4fNV(int index, float x, float y, float z, float w);

  public static native void glVertexAttrib4ubNV(int index, byte x, byte y, byte z, byte w);

  public static void glVertexAttribs1NV(int index, int n, ShortBuffer v) {
    nglVertexAttribs1svNV(index, n, v, v.position());
  }
  private static native void nglVertexAttribs1svNV(int index, int n, ShortBuffer v, int v_offset);

  public static void glVertexAttribs1NV(int index, int n, FloatBuffer v) {
    nglVertexAttribs1fvNV(index, n, v, v.position());
  }
  private static native void nglVertexAttribs1fvNV(int index, int n, FloatBuffer v, int v_offset);

  public static void glVertexAttribs2NV(int index, int n, ShortBuffer v) {
    nglVertexAttribs2svNV(index, n, v, v.position());
  }
  private static native void nglVertexAttribs2svNV(int index, int n, ShortBuffer v, int v_offset);

  public static void glVertexAttribs2NV(int index, int n, FloatBuffer v) {
    nglVertexAttribs2fvNV(index, n, v, v.position());
  }
  private static native void nglVertexAttribs2fvNV(int index, int n, FloatBuffer v, int v_offset);

  public static void glVertexAttribs3NV(int index, int n, ShortBuffer v) {
    nglVertexAttribs3svNV(index, n, v, v.position());
  }
  private static native void nglVertexAttribs3svNV(int index, int n, ShortBuffer v, int v_offset);

  public static void glVertexAttribs3NV(int index, int n, FloatBuffer v) {
    nglVertexAttribs3fvNV(index, n, v, v.position());
  }
  private static native void nglVertexAttribs3fvNV(int index, int n, FloatBuffer v, int v_offset);

  public static void glVertexAttribs4NV(int index, int n, ShortBuffer v) {
    nglVertexAttribs4svNV(index, n, v, v.position());
  }
  private static native void nglVertexAttribs4svNV(int index, int n, ShortBuffer v, int v_offset);

  public static void glVertexAttribs4NV(int index, int n, FloatBuffer v) {
    nglVertexAttribs4fvNV(index, n, v, v.position());
  }
  private static native void nglVertexAttribs4fvNV(int index, int n, FloatBuffer v, int v_offset);

  public static void glVertexAttribs4uNV(int index, int n, ByteBuffer v) {
    nglVertexAttribs4ubvNV(index, n, v, v.position());
  }
  private static native void nglVertexAttribs4ubvNV(int index, int n, ByteBuffer v, int v_offset);
}
