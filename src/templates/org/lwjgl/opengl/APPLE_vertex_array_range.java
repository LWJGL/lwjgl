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

import org.lwjgl.util.generator.AutoSize;
import org.lwjgl.util.generator.opengl.GLenum;
import org.lwjgl.util.generator.opengl.GLsizei;
import org.lwjgl.util.generator.opengl.GLvoid;

import java.nio.ByteBuffer;

public interface APPLE_vertex_array_range {

	/**
	 * Accepted by the &lt;cap&gt; parameter of EnableClientState, DisableClientState,
	 * and IsEnabled:
	 */
	int GL_VERTEX_ARRAY_RANGE_APPLE = 0x851D;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv,
	 * and GetDoublev:
	 */

	int GL_VERTEX_ARRAY_RANGE_LENGTH_APPLE = 0x851E;
	int GL_MAX_VERTEX_ARRAY_RANGE_ELEMENT_APPLE = 0x8520;

	/** Accepted by the &lt;pname&gt; parameter of GetPointerv: */

	int GL_VERTEX_ARRAY_RANGE_POINTER_APPLE = 0x8521;

	/**
	 * Accepted by the &lt;pname&gt; parameter of VertexArrayParameteriAPPLE,
	 * GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev:
	 */

	int GL_VERTEX_ARRAY_STORAGE_HINT_APPLE = 0x851F;

	/** Accepted by the &lt;param&gt; parameter of VertexArrayParameteriAPPLE: */

	int GL_STORAGE_CACHED_APPLE = 0x85BE;
	int GL_STORAGE_SHARED_APPLE = 0x85BF;

	/** Accepted by the &lt;object&gt; parameter of TestObjectAPPLE and FinishObjectAPPLE: */
	int GL_DRAW_PIXELS_APPLE = 0x8A0A;
	int GL_FENCE_APPLE = 0x8A0B;

	void glVertexArrayRangeAPPLE(@AutoSize("pointer") @GLsizei int length, @GLvoid ByteBuffer pointer);

	void glFlushVertexArrayRangeAPPLE(@AutoSize("pointer") @GLsizei int length, @GLvoid ByteBuffer pointer);

	void glVertexArrayParameteriAPPLE(@GLenum int pname, int param);

}