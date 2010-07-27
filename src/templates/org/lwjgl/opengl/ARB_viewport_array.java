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

import org.lwjgl.util.generator.*;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

@Extension(postfix = "")
public interface ARB_viewport_array {

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv,
	 * GetDoublev and GetInteger64v:
	 */
	int GL_MAX_VIEWPORTS = 0x825B,
		GL_VIEWPORT_SUBPIXEL_BITS = 0x825C,
		GL_VIEWPORT_BOUNDS_RANGE = 0x825D,
		GL_LAYER_PROVOKING_VERTEX = 0x825E,
		GL_VIEWPORT_INDEX_PROVOKING_VERTEX = 0x825F;

	/** Accepted by the &lt;pname&gt; parameter of GetIntegeri_v: */
	int GL_SCISSOR_BOX = 0x0C10;

	/** Accepted by the &lt;pname&gt; parameter of GetFloati_v: */
	int GL_VIEWPORT = 0x0BA2;

	/** Accepted by the &lt;pname&gt; parameter of GetDoublei_v: */
	int GL_DEPTH_RANGE = 0x0B70;

	/** Accepted by the &lt;pname&gt; parameter of Enablei, Disablei, and IsEnabledi: */
	int GL_SCISSOR_TEST = 0x0C11;

	/**
	 * Returned in the &lt;data&gt; parameter from a Get query with a &lt;pname&gt; of
	 * LAYER_PROVOKING_VERTEX or VIEWPORT_INDEX_PROVOKING_VERTEX:
	 */
	int GL_FIRST_VERTEX_CONVENTION = 0x8E4D,
		GL_LAST_VERTEX_CONVENTION = 0x8E4E,
		GL_PROVOKING_VERTEX = 0x8E4F,
		GL_UNDEFINED_VERTEX = 0x8260;

	@StripPostfix("v")
	void glViewportArrayv(@GLuint int first, @AutoSize(value = "v", expression = " >> 2") @GLsizei int count, @Const FloatBuffer v);

	void glViewportIndexedf(@GLuint int index, float x, float y, float w, float h);

	@StripPostfix("v")
	void glViewportIndexedfv(@GLuint int index, @Check("4") @Const FloatBuffer v);

	@StripPostfix("v")
	void glScissorArrayv(@GLuint int first, @AutoSize(value = "v", expression = " >> 2") @GLsizei int count, @Const IntBuffer v);

	void glScissorIndexed(@GLuint int index, int left, int bottom, @GLsizei int width, @GLsizei int height);

	@StripPostfix("v")
	void glScissorIndexedv(@GLuint int index, @Check("4") @Const IntBuffer v);

	@StripPostfix("v")
	void glDepthRangeArrayv(@GLuint int first, @AutoSize(value = "v", expression = " >> 1") @GLsizei int count, @Const @GLclampd DoubleBuffer v);

	void glDepthRangeIndexed(@GLuint int index, @GLclampd double n, @GLclampd double f);

	@StripPostfix("data")
	void glGetFloati_v(@GLenum int target, @GLuint int index, @Check @OutParameter FloatBuffer data);

	@Alternate("glGetFloati_v")
	@GLreturn("data")
	@StripPostfix("data")
	void glGetFloati_v2(@GLenum int target, @GLuint int index, @OutParameter FloatBuffer data);

	@StripPostfix("data")
	void glGetDoublei_v(@GLenum int target, @GLuint int index, @Check @OutParameter DoubleBuffer data);

	@Alternate("glGetDoublei_v")
	@GLreturn("data")
	@StripPostfix("data")
	void glGetDoublei_v2(@GLenum int target, @GLuint int index, @OutParameter DoubleBuffer data);

	@StripPostfix(value = "v", extension = "EXT")
	void glGetIntegerIndexedivEXT(@GLenum int target, @GLuint int index, @Check @OutParameter IntBuffer v);

	@Alternate("glGetIntegerIndexedivEXT")
	@GLreturn("v")
	@StripPostfix(value = "v", extension = "EXT")
	void glGetIntegerIndexedivEXT2(@GLenum int target, @GLuint int index, @OutParameter IntBuffer v);

	void glEnableIndexedEXT(@GLenum int target, @GLuint int index);

	void glDisableIndexedEXT(@GLenum int target, @GLuint int index);

	boolean glIsEnabledIndexedEXT(@GLenum int target, @GLuint int index);

}