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

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.util.generator.*;

public interface ARB_vertex_shader {
	/*
	 * Accepted by the <shaderType> argument of CreateShaderObjectARB and
	 * returned by the <params> parameter of GetObjectParameter{if}vARB:
	*/
	int GL_VERTEX_SHADER_ARB = 0x8B31;

	/*
	 * Accepted by the <pname> parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	*/
	int GL_MAX_VERTEX_UNIFORM_COMPONENTS_ARB = 0x8B4A;
	int GL_MAX_VARYING_FLOATS_ARB = 0x8B4B;
	int GL_MAX_VERTEX_ATTRIBS_ARB = 0x8869;
	int GL_MAX_TEXTURE_IMAGE_UNITS_ARB = 0x8872;
	int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS_ARB = 0x8B4C;
	int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS_ARB = 0x8B4D;
	int GL_MAX_TEXTURE_COORDS_ARB = 0x8871;

	/*
	 * Accepted by the <cap> parameter of Disable, Enable, and IsEnabled, and
	 * by the <pname> parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev:
	*/
	int GL_VERTEX_PROGRAM_POINT_SIZE_ARB = 0x8642;
	int GL_VERTEX_PROGRAM_TWO_SIDE_ARB = 0x8643;

	/*
	* Accepted by the <pname> parameter GetObjectParameter{if}vARB:
	*/
	int GL_OBJECT_ACTIVE_ATTRIBUTES_ARB = 0x8B89;
	int GL_OBJECT_ACTIVE_ATTRIBUTE_MAX_LENGTH_ARB = 0x8B8A;

	/*
	 * Accepted by the <pname> parameter of GetVertexAttrib{dfi}vARB:
	*/
	int GL_VERTEX_ATTRIB_ARRAY_ENABLED_ARB = 0x8622;
	int GL_VERTEX_ATTRIB_ARRAY_SIZE_ARB = 0x8623;
	int GL_VERTEX_ATTRIB_ARRAY_STRIDE_ARB = 0x8624;
	int GL_VERTEX_ATTRIB_ARRAY_TYPE_ARB = 0x8625;
	int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB = 0x886A;
	int GL_CURRENT_VERTEX_ATTRIB_ARB = 0x8626;

	/*
	 * Accepted by the <pname> parameter of GetVertexAttribPointervARB:
	*/
	int GL_VERTEX_ATTRIB_ARRAY_POINTER_ARB = 0x8645;

	/*
	 * Returned by the <type> parameter of GetActiveAttribARB:
	*/
	int GL_FLOAT = 0x1406;
	int GL_FLOAT_VEC2_ARB = 0x8B50;
	int GL_FLOAT_VEC3_ARB = 0x8B51;
	int GL_FLOAT_VEC4_ARB = 0x8B52;
	int GL_FLOAT_MAT2_ARB = 0x8B5A;
	int GL_FLOAT_MAT3_ARB = 0x8B5B;
	int GL_FLOAT_MAT4_ARB = 0x8B5C;

	void glBindAttribLocationARB(@GLhandleARB int programObj, @GLuint int index, @NullTerminated @Const @GLcharARB ByteBuffer name);

	// ---------------------------
	void glGetActiveAttribARB(@GLhandleARB int programObj, @GLuint int index,
			@AutoSize("name")
			@GLsizei
			int maxLength,
			@Check(value="1", canBeNull=true)
			@GLsizei
			IntBuffer length,
			@Check("1")
			IntBuffer size,
			@Check("1")
			@GLenum
			IntBuffer type,
			@GLcharARB
			ByteBuffer name);

	int glGetAttribLocationARB(@GLhandleARB int programObj, @NullTerminated @Const @GLcharARB ByteBuffer name);
}
