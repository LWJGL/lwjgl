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
import org.lwjgl.util.generator.Alternate;
import org.lwjgl.util.generator.opengl.GLenum;
import org.lwjgl.util.generator.opengl.GLreturn;
import org.lwjgl.util.generator.opengl.GLuint;

import java.nio.IntBuffer;

public interface APPLE_object_purgeable {

	/**
	 * Accepted by the &lt;option&gt; parameter of ObjectPurgeable, and returned
	 * by ObjectPurgeable:
	 */
	int GL_RELEASED_APPLE = 0x8A19;
	int GL_VOLATILE_APPLE = 0x8A1A;

	/**
	 * Accepted by the &lt;option&gt; parameters of ObjectUnpurgeable, and
	 * returned by ObjectUnpurgeable:
	 */
	int GL_RETAINED_APPLE = 0x8A1B;
	int GL_UNDEFINED_APPLE = 0x8A1C;

	/** Accepted by the &lt;pname&gt; parameters of GetObjectParameteriv: */
	int GL_PURGEABLE_APPLE = 0x8A1D;

	/**
	 * Accepted by the &lt;objectType&gt; parameters of ObjectPurgeableAPPLE,
	 * ObjectUnpurgeableAPPLE and GetObjectParameteriv:
	 */
	int GL_BUFFER_OBJECT_APPLE = 0x85B3;

	@GLenum
	int glObjectPurgeableAPPLE(@GLenum int objectType, @GLuint int name, @GLenum int option);

	@GLenum
	int glObjectUnpurgeableAPPLE(@GLenum int objectType, @GLuint int name, @GLenum int option);

	@StripPostfix("params")
	void glGetObjectParameterivAPPLE(@GLenum int objectType, @GLuint int name, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	@Alternate("glGetObjectParameterivAPPLE")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetObjectParameterivAPPLE2(@GLenum int objectType, @GLuint int name, @GLenum int pname, @OutParameter IntBuffer params);

}