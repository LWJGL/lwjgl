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

import java.nio.IntBuffer;

import org.lwjgl.generator.*;

public interface NV_occlusion_query {
	public static final int GL_OCCLUSION_TEST_HP = 0x8165;
	public static final int GL_OCCLUSION_TEST_RESULT_HP = 0x8166;
	/* HP_occlusion_test */
	public static final int GL_PIXEL_COUNTER_BITS_NV = 0x8864;
	public static final int GL_CURRENT_OCCLUSION_QUERY_ID_NV = 0x8865;
	public static final int GL_PIXEL_COUNT_NV = 0x8866;
	public static final int GL_PIXEL_COUNT_AVAILABLE_NV = 0x8867;

	public void glGenOcclusionQueriesNV(@AutoSize("piIDs") @GLsizei int n, @GLuint IntBuffer piIDs);
	public void glDeleteOcclusionQueriesNV(@AutoSize("piIDs") @GLsizei int n, @Const @GLuint IntBuffer piIDs);

	public boolean glIsOcclusionQueryNV(@GLuint int id);

	public void glBeginOcclusionQueryNV(@GLuint int id);

	public void glEndOcclusionQueryNV();

	@StripPostfix("params")
	public void glGetOcclusionQueryuivNV(@GLuint int id, @GLenum int pname, @Check("4") @GLuint IntBuffer params);
	@StripPostfix("params")
	public void glGetOcclusionQueryivNV(@GLuint int id, @GLenum int pname, @Check("4") IntBuffer params);
}
