/*
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of
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
/*
 * Created by LWJGL.
 * User: spasi
 * Date: 02-15-2004
 * Time: 4:14:52 pm
 */

package org.lwjgl.opengl;

import java.nio.*;

public final class GL15 {

	// ----------------------------------------------------------------------
	// ---------------------- ARB_vertex_buffer_object ----------------------
	// ----------------------------------------------------------------------

	public static final int GL_ARRAY_BUFFER = 0x8892;
	public static final int GL_ELEMENT_ARRAY_BUFFER = 0x8893;
	public static final int GL_ARRAY_BUFFER_BINDING = 0x8894;
	public static final int GL_ELEMENT_ARRAY_BUFFER_BINDING = 0x8895;
	public static final int GL_VERTEX_ARRAY_BUFFER_BINDING = 0x8896;
	public static final int GL_NORMAL_ARRAY_BUFFER_BINDING = 0x8897;
	public static final int GL_COLOR_ARRAY_BUFFER_BINDING = 0x8898;
	public static final int GL_INDEX_ARRAY_BUFFER_BINDING = 0x8899;
	public static final int GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING = 0x889A;
	public static final int GL_EDGE_FLAG_ARRAY_BUFFER_BINDING = 0x889B;
	public static final int GL_SECONDARY_COLOR_ARRAY_BUFFER_BINDING = 0x889C;
	public static final int GL_FOG_COORDINATE_ARRAY_BUFFER_BINDING = 0x889D;
	public static final int GL_WEIGHT_ARRAY_BUFFER_BINDING = 0x889E;
	public static final int GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 0x889F;
	public static final int GL_STREAM_DRAW = 0x88E0;
	public static final int GL_STREAM_READ = 0x88E1;
	public static final int GL_STREAM_COPY = 0x88E2;
	public static final int GL_STATIC_DRAW = 0x88E4;
	public static final int GL_STATIC_READ = 0x88E5;
	public static final int GL_STATIC_COPY = 0x88E6;
	public static final int GL_DYNAMIC_DRAW = 0x88E8;
	public static final int GL_DYNAMIC_READ = 0x88E9;
	public static final int GL_DYNAMIC_COPY = 0x88EA;
	public static final int GL_READ_ONLY = 0x88B8;
	public static final int GL_WRITE_ONLY = 0x88B9;
	public static final int GL_READ_WRITE = 0x88BA;
	public static final int GL_BUFFER_SIZE = 0x8764;
	public static final int GL_BUFFER_USAGE = 0x8765;
	public static final int GL_BUFFER_ACCESS = 0x88BB;
	public static final int GL_BUFFER_MAPPED = 0x88BC;
	public static final int GL_BUFFER_MAP_POINTER = 0x88BD;

	public static void glBindBuffer(int target, int buffer) {
		switch ( target ) {
			case GL_ELEMENT_ARRAY_BUFFER:
				VBOTracker.getVBOElementStack().setState(buffer);
				break;
			case GL_ARRAY_BUFFER:
				VBOTracker.getVBOArrayStack().setState(buffer);
				break;
			default:
				throw new IllegalArgumentException("Unsupported VBO target " + target);
		}
		nglBindBuffer(target, buffer);
	}

	private static native void nglBindBuffer(int target, int buffer);

	public static void glDeleteBuffers(IntBuffer buffers) {
		for ( int i = buffers.position(); i < buffers.limit(); i++ ) {
			int buffer_handle = buffers.get(i);
			if ( VBOTracker.getVBOElementStack().getState() == buffer_handle )
				VBOTracker.getVBOElementStack().setState(0);
			if ( VBOTracker.getVBOArrayStack().getState() == buffer_handle )
				VBOTracker.getVBOArrayStack().setState(0);
		}
		nglDeleteBuffers(buffers.remaining(), buffers, buffers.position());
	}

	private static native void nglDeleteBuffers(int n, IntBuffer buffers, int buffers_offset);

	public static void glGenBuffers(IntBuffer buffers) {
		nglGenBuffers(buffers.remaining(), buffers, buffers.position());
	}

	private static native void nglGenBuffers(int n, IntBuffer buffers, int buffers_offset);

	public static native boolean glIsBuffer(int buffer);

	public static void glBufferData(int target, int size, ByteBuffer data, int usage) {
		nglBufferData(target, size, data, data != null ? data.position() : 0, usage);
	}

	public static void glBufferData(int target, int size, ShortBuffer data, int usage) {
		nglBufferData(target, size, data, data != null ? data.position() << 1 : 0, usage);
	}

	public static void glBufferData(int target, int size, FloatBuffer data, int usage) {
		nglBufferData(target, size, data, data != null ? data.position() << 2 : 0, usage);
	}

	public static void glBufferData(int target, int size, IntBuffer data, int usage) {
		nglBufferData(target, size, data, data != null ? data.position() << 2 : 0, usage);
	}

	private static native void nglBufferData(int target,
	                                            int size,
	                                            Buffer data,
	                                            int data_offset,
	                                            int usage);

	public static void glBufferSubData(int target, int offset, ByteBuffer data) {
		nglBufferSubData(target, offset, data.remaining(), data, data.position());
	}

	public static void glBufferSubData(int target, int offset, ShortBuffer data) {
		nglBufferSubData(target, offset, data.remaining() << 1, data, data.position() << 1);
	}

	public static void glBufferSubData(int target, int offset, FloatBuffer data) {
		nglBufferSubData(target, offset, data.remaining() << 2, data, data.position() << 2);
	}

	public static void glBufferSubData(int target, int offset, IntBuffer data) {
		nglBufferSubData(target, offset, data.remaining() << 2, data, data.position() << 2);
	}

	private static native void nglBufferSubData(int target,
	                                               int offset,
	                                               int size,
	                                               Buffer data,
	                                               int data_offset);

	public static void glGetBufferSubData(int target, int offset, ByteBuffer data) {
		nglGetBufferSubData(target, offset, data.remaining(), data, data.position());
	}

	public static void glGetBufferSubData(int target, int offset, ShortBuffer data) {
		nglGetBufferSubData(target, offset, data.remaining() << 1, data, data.position() << 1);
	}

	public static void glGetBufferSubData(int target, int offset, IntBuffer data) {
		nglGetBufferSubData(target, offset, data.remaining() << 2, data, data.position() << 2);
	}

	public static void glGetBufferSubData(int target, int offset, FloatBuffer data) {
		nglGetBufferSubData(target, offset, data.remaining() << 2, data, data.position() << 2);
	}

	private static native void nglGetBufferSubData(int target,
	                                                  int offset,
	                                                  int size,
	                                                  Buffer data,
	                                                  int data_offset);

	/**
	 * glMapBuffer maps a gl vertex buffer buffer to a ByteBuffer. The oldBuffer argument can be
	 * null, in which case a new ByteBuffer will be created, pointing to the returned memory. If
	 * oldBuffer is non-null, it will be returned if it points to the same mapped memory, otherwise a
	 * new ByteBuffer is created. That way, an application will normally use glMapBuffer like this:
	 * <p/>
	 * ByteBuffer mapped_buffer; mapped_buffer = glMapBuffer(..., ..., ..., null); ... // Another
	 * map on the same buffer mapped_buffer = glMapBuffer(..., ..., ..., mapped_buffer);
	 *
	 * @param size      The size of the buffer area.
	 * @param oldBuffer A ByteBuffer. If this argument points to the same address as the new mapping,
	 *                  it will be returned and no new buffer will be created. In that case, size is
	 *                  ignored.
	 *
	 * @return A ByteBuffer representing the mapped buffer memory.
	 */
	public static native ByteBuffer glMapBuffer(int target,
	                                               int access,
	                                               int size,
	                                               ByteBuffer oldBuffer);

	public static native boolean glUnmapBuffer(int target);

	public static void glGetBufferParameter(int target, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetBufferParameteriv(target, pname, params, params.position());
	}

	private static native void nglGetBufferParameteriv(int target,
	                                                      int pname,
	                                                      IntBuffer params,
	                                                      int params_offset);

	public static native ByteBuffer glGetBufferPointer(int target, int pname, int size);

	// -----------------------------------------------------------------
	// ---------------------- ARB_occlusion_query ----------------------
	// -----------------------------------------------------------------

	/*
	* Accepted by the <target> parameter of BeginQuery, EndQuery,
	* and GetQueryiv:
	*/
	public static final int GL_SAMPLES_PASSED = 0x8914;

	/*
	Accepted by the <pname> parameter of GetQueryiv:
	*/
	public static final int GL_QUERY_COUNTER_BITS = 0x8864;
	public static final int GL_CURRENT_QUERY = 0x8865;

	/*
	Accepted by the <pname> parameter of GetQueryObjectiv and
	GetQueryObjectuiv:
	*/
	public static final int GL_QUERY_RESULT = 0x8866;
	public static final int GL_QUERY_RESULT_AVAILABLE = 0x8867;

	// ---------------------------
	public static void glGenQueries(IntBuffer ids) {
		nglGenQueries(ids.remaining(), ids, ids.position());
	}

	private static native void nglGenQueries(int n, IntBuffer ids, int idsOffset);
	// ---------------------------

	// ---------------------------
	public static void glDeleteQueries(IntBuffer ids) {
		nglDeleteQueries(ids.remaining(), ids, ids.position());
	}

	private static native void nglDeleteQueries(int n, IntBuffer ids, int idsOffset);
	// ---------------------------

	public static native boolean glIsQuery(int id);

	public static native void glBeginQuery(int target, int id);

	public static native void glEndQuery(int target);

	// ---------------------------
	public static void glGetQuery(int target, int pname, IntBuffer params) {
		nglGetQueryiv(target, pname, params, params.position());
	}

	private static native void nglGetQueryiv(int target,
	                                            int pname,
	                                            IntBuffer params,
	                                            int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetQueryObjecti(int id, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetQueryObjectiv(id, pname, params, params.position());
	}

	private static native void nglGetQueryObjectiv(int id,
	                                                  int pname,
	                                                  IntBuffer params,
	                                                  int paramsOffset);
	// ---------------------------

	// ---------------------------
	public static void glGetQueryObjectui(int id, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetQueryObjectuiv(id, pname, params, params.position());
	}

	private static native void nglGetQueryObjectuiv(int id,
	                                                   int pname,
	                                                   IntBuffer params,
	                                                   int paramsOffset);
	// ---------------------------
	
}
