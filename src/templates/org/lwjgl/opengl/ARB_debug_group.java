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

import org.lwjgl.util.generator.*;
import org.lwjgl.util.generator.opengl.GLchar;
import org.lwjgl.util.generator.opengl.GLenum;
import org.lwjgl.util.generator.opengl.GLsizei;
import org.lwjgl.util.generator.opengl.GLuint;

import java.nio.ByteBuffer;

public interface ARB_debug_group {

	/**
	 * Tokens accepted or provided by the &lt;type&gt; parameters of
	 * DebugMessageControl, DebugMessageInsertand DEBUGPROC,
	 * and the &lt;types&gt; parameter of GetDebugMessageLog:
	 */
	int GL_DEBUG_TYPE_MARKER = 0x8268;

	/**
	 * Tokens accepted or provided by the &lt;type&gt; parameters of
	 * DebugMessageControl and DEBUGPROC, and the &lt;types&gt; parameter of
	 * GetDebugMessageLog:
	 */
	int GL_DEBUG_TYPE_PUSH_GROUP = 0x8269,
		GL_DEBUG_TYPE_POP_GROUP  = 0x826A;

	/**
	 * Tokens accepted or provided by the &lt;severity&gt; parameters of
	 * DebugMessageControl, DebugMessageInsert and DEBUGPROC
	 * callback functions, and the &lt;severities&gt; parameter of
	 * GetDebugMessageLog:
	 */
	int GL_DEBUG_SEVERITY_NOTIFICATION = 0x826B;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, GetDoublev, and GetInteger64v:
	 */
	int GL_MAX_DEBUG_GROUP_STACK_DEPTH = 0x826C,
		GL_DEBUG_GROUP_STACK_DEPTH     = 0x826D;

	/** Returned by GetError: */
	int GL_STACK_UNDERFLOW = 0x0504,
		GL_STACK_OVERFLOW  = 0x0503;

	@Reuse("GL43")
	void glPushDebugGroup(@GLenum int source, @GLuint int id, @AutoSize("message") @GLsizei int length,
	                      @Const @GLchar ByteBuffer message);

	@Reuse("GL43")
	@Alternate("glPushDebugGroup")
	void glPushDebugGroup(@GLenum int source, @GLuint int id, @Constant("message.length()") @GLsizei int length,
	                      CharSequence message);

	@Reuse("GL43")
	void glPopDebugGroup();

}