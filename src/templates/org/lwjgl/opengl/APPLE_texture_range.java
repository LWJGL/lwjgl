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
import org.lwjgl.util.generator.opengl.GLenum;
import org.lwjgl.util.generator.opengl.GLsizei;
import org.lwjgl.util.generator.opengl.GLvoid;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public interface APPLE_texture_range {

	/**
	 * Accepted by the <pname> parameters of TexParameteri, TexParameterf,
	 * TexParameteriv, TexParameterfv, GetTexParameteriv, and
	 * GetTexParameterfv:
	 */
	int GL_TEXTURE_STORAGE_HINT_APPLE = 0x85BC;

	/**
	 * Accepted by the <param> parameters of TexParameteri, TexParameterf,
	 * TexParameteriv, and TexParameterfv:
	 */
	int GL_STORAGE_PRIVATE_APPLE = 0x85BD;
	int GL_STORAGE_CACHED_APPLE = 0x85BE;
	int GL_STORAGE_SHARED_APPLE = 0x85BF;

	/**
	 * Accepted by the <pname> parameters of GetTexParameteriv and
	 * GetTexParameterfv:
	 */
	int GL_TEXTURE_RANGE_LENGTH_APPLE = 0x85B7;

	/** Accepted by the <pname> parameters of GetTexParameterPointerv: */
	int GL_TEXTURE_RANGE_POINTER_APPLE = 0x85B8;

	void glTextureRangeAPPLE(@GLenum int target, @AutoSize("pointer") @GLsizei int length, @GLvoid ByteBuffer pointer);

	void glGetTexParameterPointervAPPLE(@GLenum int target, @GLenum int pname, @Result @GLvoid Buffer params);

}