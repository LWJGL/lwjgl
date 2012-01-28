/*
 * Copyright (c) 2002-2012 LWJGL Project
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

import org.lwjgl.util.generator.opengl.GLenum;
import org.lwjgl.util.generator.opengl.GLuint;

public interface AMD_stencil_operation_extended {

	/**
	 * Accepted by the &lt;sfail&gt;, &lt;dpfail&gt; and &lt;dppass&gt; parameters of StencilOp
	 * and StencilOpSeparate:
	 */
	int GL_SET_AMD           = 0x874A,
		GL_AND               = 0x1501,
		GL_XOR               = 0x1506,
		GL_OR                = 0x1507,
		GL_NOR               = 0x1508,
		GL_EQUIV             = 0x1509,
		GL_NAND              = 0x150E,
		GL_REPLACE_VALUE_AMD = 0x874B;

	/**
	 * Accepted by the &lt;param&gt; parameter of GetIntegerv, GetFloatv, GetBooleanv
	 * GetDoublev and GetInteger64v:
	 */
	int GL_STENCIL_OP_VALUE_AMD      = 0x874C,
		GL_STENCIL_BACK_OP_VALUE_AMD = 0x874D;

	void glStencilOpValueAMD(@GLenum int face, @GLuint int value);

}