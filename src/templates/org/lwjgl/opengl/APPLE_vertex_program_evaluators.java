/*
 * Copyright (c) 2002-2008 LWJGL Project
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

import org.lwjgl.util.generator.Check;
import org.lwjgl.util.generator.Const;
import org.lwjgl.util.generator.opengl.GLenum;
import org.lwjgl.util.generator.opengl.GLuint;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public interface APPLE_vertex_program_evaluators {

	/**
	 * Accepted by the &lt;pname&gt; parameter of EnableVertexAttribAPPLE,
	 * DisableVertexAttribAPPLE, and IsVertexAttribEnabledAPPLE.
	 */
	int GL_VERTEX_ATTRIB_MAP1_APPLE = 0x8A00;
	int GL_VERTEX_ATTRIB_MAP2_APPLE = 0x8A01;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetVertexAttribdvARB,
	 * GetVertexAttribfvARB, and GetVertexAttribivARB.
	 */
	int GL_VERTEX_ATTRIB_MAP1_SIZE_APPLE = 0x8A02;
	int GL_VERTEX_ATTRIB_MAP1_COEFF_APPLE = 0x8A03;
	int GL_VERTEX_ATTRIB_MAP1_ORDER_APPLE = 0x8A04;
	int GL_VERTEX_ATTRIB_MAP1_DOMAIN_APPLE = 0x8A05;
	int GL_VERTEX_ATTRIB_MAP2_SIZE_APPLE = 0x8A06;
	int GL_VERTEX_ATTRIB_MAP2_COEFF_APPLE = 0x8A07;
	int GL_VERTEX_ATTRIB_MAP2_ORDER_APPLE = 0x8A08;
	int GL_VERTEX_ATTRIB_MAP2_DOMAIN_APPLE = 0x8A09;

	void glEnableVertexAttribAPPLE(@GLuint int index, @GLenum int pname);

	void glDisableVertexAttribAPPLE(@GLuint int index, @GLenum int pname);

	boolean glIsVertexAttribEnabledAPPLE(@GLuint int index, @GLenum int pname);

	void glMapVertexAttrib1dAPPLE(@GLuint int index, @GLuint int size, double u1, double u2,
	                              int stride, int order, @Check @Const DoubleBuffer points);

	void glMapVertexAttrib1fAPPLE(@GLuint int index, @GLuint int size, float u1, float u2,
	                              int stride, int order, @Check @Const FloatBuffer points);

	void glMapVertexAttrib2dAPPLE(@GLuint int index, @GLuint int size, double u1, double u2,
	                              int ustride, int uorder, double v1, double v2, int vstride, int vorder,
	                              @Check @Const DoubleBuffer points);

	void glMapVertexAttrib2fAPPLE(@GLuint int index, @GLuint int size, float u1, float u2,
	                              int ustride, int uorder, float v1, float v2, int vstride, int vorder,
	                              @Check @Const FloatBuffer points);

}