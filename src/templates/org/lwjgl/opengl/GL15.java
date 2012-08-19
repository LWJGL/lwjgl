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
import org.lwjgl.util.generator.opengl.*;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public interface GL15 {
	// ----------------------------------------------------------------------
	// ---------------------- ARB_vertex_buffer_object ----------------------
	// ----------------------------------------------------------------------

	int GL_ARRAY_BUFFER = 0x8892;
	int GL_ELEMENT_ARRAY_BUFFER = 0x8893;
	int GL_ARRAY_BUFFER_BINDING = 0x8894;
	int GL_ELEMENT_ARRAY_BUFFER_BINDING = 0x8895;
	int GL_VERTEX_ARRAY_BUFFER_BINDING = 0x8896;
	int GL_NORMAL_ARRAY_BUFFER_BINDING = 0x8897;
	int GL_COLOR_ARRAY_BUFFER_BINDING = 0x8898;
	int GL_INDEX_ARRAY_BUFFER_BINDING = 0x8899;
	int GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING = 0x889A;
	int GL_EDGE_FLAG_ARRAY_BUFFER_BINDING = 0x889B;
	int GL_SECONDARY_COLOR_ARRAY_BUFFER_BINDING = 0x889C;
	int GL_FOG_COORDINATE_ARRAY_BUFFER_BINDING = 0x889D;
	int GL_WEIGHT_ARRAY_BUFFER_BINDING = 0x889E;
	int GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 0x889F;
	int GL_STREAM_DRAW = 0x88E0;
	int GL_STREAM_READ = 0x88E1;
	int GL_STREAM_COPY = 0x88E2;
	int GL_STATIC_DRAW = 0x88E4;
	int GL_STATIC_READ = 0x88E5;
	int GL_STATIC_COPY = 0x88E6;
	int GL_DYNAMIC_DRAW = 0x88E8;
	int GL_DYNAMIC_READ = 0x88E9;
	int GL_DYNAMIC_COPY = 0x88EA;
	int GL_READ_ONLY = 0x88B8;
	int GL_WRITE_ONLY = 0x88B9;
	int GL_READ_WRITE = 0x88BA;
	int GL_BUFFER_SIZE = 0x8764;
	int GL_BUFFER_USAGE = 0x8765;
	int GL_BUFFER_ACCESS = 0x88BB;
	int GL_BUFFER_MAPPED = 0x88BC;
	int GL_BUFFER_MAP_POINTER = 0x88BD;

	int GL_FOG_COORD_SRC = GL14.GL_FOG_COORDINATE_SOURCE;
	int GL_FOG_COORD = GL14.GL_FOG_COORDINATE;
	int GL_CURRENT_FOG_COORD = GL14.GL_CURRENT_FOG_COORDINATE;
	int GL_FOG_COORD_ARRAY_TYPE = GL14.GL_FOG_COORDINATE_ARRAY_TYPE;
	int GL_FOG_COORD_ARRAY_STRIDE = GL14.GL_FOG_COORDINATE_ARRAY_STRIDE;
	int GL_FOG_COORD_ARRAY_POINTER = GL14.GL_FOG_COORDINATE_ARRAY_POINTER;
	int GL_FOG_COORD_ARRAY = GL14.GL_FOG_COORDINATE_ARRAY;
	int GL_FOG_COORD_ARRAY_BUFFER_BINDING = GL_FOG_COORDINATE_ARRAY_BUFFER_BINDING;
	int GL_SRC0_RGB = GL13.GL_SOURCE0_RGB;
	int GL_SRC1_RGB = GL13.GL_SOURCE1_RGB;
	int GL_SRC2_RGB = GL13.GL_SOURCE2_RGB;
	int GL_SRC0_ALPHA = GL13.GL_SOURCE0_ALPHA;
	int GL_SRC1_ALPHA = GL13.GL_SOURCE1_ALPHA;
	int GL_SRC2_ALPHA = GL13.GL_SOURCE2_ALPHA;

	@Code("		StateTracker.bindBuffer(caps, target, buffer);")
	void glBindBuffer(@GLenum int target, @GLuint int buffer);

	void glDeleteBuffers(@AutoSize("buffers") @GLsizei int n, @Const @GLuint IntBuffer buffers);

	@Alternate("glDeleteBuffers")
	void glDeleteBuffers(@Constant("1") @GLsizei int n, @Constant(value = "APIUtil.getInt(caps, buffer)", keepParam = true) int buffer);

	void glGenBuffers(@AutoSize("buffers") @GLsizei int n, @OutParameter @GLuint IntBuffer buffers);

	@Alternate("glGenBuffers")
	@GLreturn("buffers")
	void glGenBuffers2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer buffers);

	boolean glIsBuffer(@GLuint int buffer);

	@GenerateAutos
	void glBufferData(@GLenum int target, @AutoSize("data") @GLsizeiptr long size,
	                  @Check
	                  @Const
	                  @GLbyte
	                  @GLshort
	                  @GLint
	                  @GLfloat
	                  @GLdouble Buffer data, @GLenum int usage);

	void glBufferSubData(@GLenum int target, @GLintptr long offset, @AutoSize("data") @GLsizeiptr long size,
	                     @Check
	                     @Const
	                     @GLbyte
	                     @GLshort
	                     @GLint
	                     @GLfloat
	                     @GLdouble Buffer data);

	void glGetBufferSubData(@GLenum int target, @GLintptr long offset, @AutoSize("data") @GLsizeiptr long size,
	                        @OutParameter
	                        @Check
	                        @GLbyte
	                        @GLshort
	                        @GLint
	                        @GLfloat
	                        @GLdouble Buffer data);

	/**
	 * glMapBuffer maps a GL buffer object to a ByteBuffer. The old_buffer argument can be null,
	 * in which case a new ByteBuffer will be created, pointing to the returned memory. If old_buffer is non-null,
	 * it will be returned if it points to the same mapped memory and has the same capacity as the buffer object,
	 * otherwise a new ByteBuffer is created. That way, an application will normally use glMapBuffer like this:
	 * <p/>
	 * ByteBuffer mapped_buffer; mapped_buffer = glMapBuffer(..., ..., null); ... // Another map on the same buffer mapped_buffer = glMapBuffer(..., ..., mapped_buffer);
	 * <p/>
	 * Only ByteBuffers returned from this method are to be passed as the old_buffer argument. User-created ByteBuffers cannot be reused.
	 * <p/>
	 * The version of this method without an explicit length argument calls glGetBufferParameter internally to
	 * retrieve the current buffer object size, which may cause a pipeline flush and reduce application performance.
	 * <p/>
	 * The version of this method with an explicit length argument is a fast alternative to the one without. No GL call
	 * is made to retrieve the buffer object size, so the user is responsible for tracking and using the appropriate length.<br>
	 * Security warning: The length argument should match the buffer object size. Reading from or writing to outside
	 * the memory region that corresponds to the mapped buffer object will cause native crashes.
	 *
	 * @param old_buffer A ByteBuffer. If this argument points to the same address and has the same capacity as the new mapping, it will be returned and no new buffer will be created.
	 *
	 * @return A ByteBuffer representing the mapped buffer memory.
	 */
	@CachedResult
	@GLvoid
	@AutoSize("GLChecks.getBufferObjectSize(caps, target)")
	ByteBuffer glMapBuffer(@GLenum int target, @GLenum int access);

	boolean glUnmapBuffer(@GLenum int target);

	@StripPostfix("params")
	void glGetBufferParameteriv(@GLenum int target, @GLenum int pname, @OutParameter @Check("4") IntBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetBufferParameteri} instead. */
	@Alternate("glGetBufferParameteriv")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GL15", method = "glGetBufferParameteri")
	@Deprecated
	void glGetBufferParameteriv2(@GLenum int target, @GLenum int pname, @OutParameter IntBuffer params);

	@Alternate("glGetBufferParameteriv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetBufferParameteriv3(@GLenum int target, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("pointer")
	@AutoSize("GLChecks.getBufferObjectSize(caps, target)")
	void glGetBufferPointerv(@GLenum int target, @GLenum int pname, @OutParameter @Result @GLvoid ByteBuffer pointer);

	// -----------------------------------------------------------------
	// ---------------------- ARB_occlusion_query ----------------------
	// -----------------------------------------------------------------

	/**
	 * Accepted by the &lt;target&gt; parameter of BeginQuery, EndQuery,
	 * and GetQueryiv:
	 */
	int GL_SAMPLES_PASSED = 0x8914;

	/** Accepted by the &lt;pname&gt; parameter of GetQueryiv: */
	int GL_QUERY_COUNTER_BITS = 0x8864;
	int GL_CURRENT_QUERY = 0x8865;

	/**
	 * Accepted by the &lt;pname&gt; parameter of GetQueryObjectiv and
	 * GetQueryObjectuiv:
	 */
	int GL_QUERY_RESULT = 0x8866;
	int GL_QUERY_RESULT_AVAILABLE = 0x8867;

	void glGenQueries(@AutoSize("ids") @GLsizei int n, @OutParameter @GLuint IntBuffer ids);

	@Alternate("glGenQueries")
	@GLreturn("ids")
	void glGenQueries2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer ids);

	void glDeleteQueries(@AutoSize("ids") @GLsizei int n, @GLuint IntBuffer ids);

	@Alternate("glDeleteQueries")
	void glDeleteQueries(@Constant("1") @GLsizei int n, @Constant(value = "APIUtil.getInt(caps, id)", keepParam = true) int id);

	boolean glIsQuery(@GLuint int id);

	void glBeginQuery(@GLenum int target, @GLuint int id);

	void glEndQuery(@GLenum int target);

	@StripPostfix("params")
	void glGetQueryiv(@GLenum int target, @GLenum int pname, @OutParameter @Check("1") IntBuffer params);

	/** @deprecated Will be removed in 3.0. Use {@link #glGetQueryi} instead. */
	@Alternate("glGetQueryiv")
	@GLreturn("params")
	@StripPostfix("params")
	@Reuse(value = "GL15", method = "glGetQueryi")
	@Deprecated
	void glGetQueryiv2(@GLenum int target, @GLenum int pname, @OutParameter IntBuffer params);

	@Alternate("glGetQueryiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetQueryiv3(@GLenum int target, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetQueryObjectiv(@GLenum int id, @GLenum int pname, @OutParameter @Check("1") @GLint IntBuffer params);

	@Alternate("glGetQueryObjectiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetQueryObjectiv2(@GLenum int id, @GLenum int pname, @OutParameter @GLint IntBuffer params);

	@StripPostfix("params")
	void glGetQueryObjectuiv(@GLenum int id, @GLenum int pname, @OutParameter @Check("1") @GLuint IntBuffer params);

	@Alternate("glGetQueryObjectuiv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetQueryObjectuiv2(@GLenum int id, @GLenum int pname, @OutParameter @GLuint IntBuffer params);
}
