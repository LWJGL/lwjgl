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

import org.lwjgl.BufferChecks;
import org.lwjgl.LWJGLException;

import java.nio.*;

public abstract class ARBBufferObject {

	/*
	 * Accepted by the <usage> parameter of BufferDataARB:
	 */
	public static final int GL_STREAM_DRAW_ARB = 0x88E0;
	public static final int GL_STREAM_READ_ARB = 0x88E1;
	public static final int GL_STREAM_COPY_ARB = 0x88E2;
	public static final int GL_STATIC_DRAW_ARB = 0x88E4;
	public static final int GL_STATIC_READ_ARB = 0x88E5;
	public static final int GL_STATIC_COPY_ARB = 0x88E6;
	public static final int GL_DYNAMIC_DRAW_ARB = 0x88E8;
	public static final int GL_DYNAMIC_READ_ARB = 0x88E9;
	public static final int GL_DYNAMIC_COPY_ARB = 0x88EA;

	/*
	 * Accepted by the <access> parameter of MapBufferARB:
	*/
	public static final int GL_READ_ONLY_ARB = 0x88B8;
	public static final int GL_WRITE_ONLY_ARB = 0x88B9;
	public static final int GL_READ_WRITE_ARB = 0x88BA;

	/*
	 * Accepted by the <pname> parameter of GetBufferParameterivARB:
	*/
	public static final int GL_BUFFER_SIZE_ARB = 0x8764;
	public static final int GL_BUFFER_USAGE_ARB = 0x8765;
	public static final int GL_BUFFER_ACCESS_ARB = 0x88BB;
	public static final int GL_BUFFER_MAPPED_ARB = 0x88BC;
	public static final int GL_BUFFER_MAP_POINTER_ARB = 0x88BD;

	static native void initNativeStubs() throws LWJGLException;

	public static void glBindBufferARB(int target, int buffer) {
		switch ( target ) {
			case ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB:
				VBOTracker.getVBOArrayStack().setState(buffer);
				break;
			case ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB:
				VBOTracker.getVBOElementStack().setState(buffer);
				break;
			case ARBPixelBufferObject.GL_PIXEL_PACK_BUFFER_ARB:
				VBOTracker.getPBOPackStack().setState(buffer);
				break;
			case ARBPixelBufferObject.GL_PIXEL_UNPACK_BUFFER_ARB:
				VBOTracker.getPBOUnpackStack().setState(buffer);
				break;
			default:
				throw new IllegalArgumentException("Unsupported VBO target " + target);
		}
		nglBindBufferARB(target, buffer);
	}

	private static native void nglBindBufferARB(int target, int buffer);

	public static void glDeleteBuffersARB(IntBuffer buffers) {
		for ( int i = buffers.position(); i < buffers.limit(); i++ ) {
			int buffer_handle = buffers.get(i);
			if ( VBOTracker.getVBOArrayStack().getState() == buffer_handle )
				VBOTracker.getVBOArrayStack().setState(0);
			if ( VBOTracker.getVBOElementStack().getState() == buffer_handle )
				VBOTracker.getVBOElementStack().setState(0);
			if ( VBOTracker.getPBOPackStack().getState() == buffer_handle )
				VBOTracker.getPBOPackStack().setState(0);
			if ( VBOTracker.getPBOUnpackStack().getState() == buffer_handle )
				VBOTracker.getPBOUnpackStack().setState(0);
		}
		BufferChecks.checkDirect(buffers);
		nglDeleteBuffersARB(buffers.remaining(), buffers, buffers.position());
	}

	private static native void nglDeleteBuffersARB(int n, IntBuffer buffers, int buffers_offset);

	public static void glGenBuffersARB(IntBuffer buffers) {
		BufferChecks.checkDirect(buffers);
		nglGenBuffersARB(buffers.remaining(), buffers, buffers.position());
	}

	private static native void nglGenBuffersARB(int n, IntBuffer buffers, int buffers_offset);

	public static native boolean glIsBufferARB(int buffer);

	public static void glBufferDataARB(int target, int size, ByteBuffer data, int usage) {
		BufferChecks.checkDirectOrNull(data);
		nglBufferDataARB(target, data != null ? data.remaining() : size, data, data != null ? data.position() : 0, usage);
	}

	public static void glBufferDataARB(int target, int size, ShortBuffer data, int usage) {
		BufferChecks.checkDirectOrNull(data);
		nglBufferDataARB(target, data != null ? data.remaining() << 1 : size, data, data != null ? data.position() << 1 : 0, usage);
	}

	public static void glBufferDataARB(int target, int size, FloatBuffer data, int usage) {
		BufferChecks.checkDirectOrNull(data);
		nglBufferDataARB(target, data != null ? data.remaining() << 2 : size, data, data != null ? data.position() << 2 : 0, usage);
	}

	public static void glBufferDataARB(int target, int size, IntBuffer data, int usage) {
		BufferChecks.checkDirectOrNull(data);
		nglBufferDataARB(target, data != null ? data.remaining() << 2 : size, data, data != null ? data.position() << 2 : 0, usage);
	}

	private static native void nglBufferDataARB(int target, int size, Buffer data, int data_offset, int usage);

	public static void glBufferSubDataARB(int target, int offset, ByteBuffer data) {
		BufferChecks.checkDirect(data);
		nglBufferSubDataARB(target, offset, data.remaining(), data, data.position());
	}

	public static void glBufferSubDataARB(int target, int offset, ShortBuffer data) {
		BufferChecks.checkDirect(data);
		nglBufferSubDataARB(target, offset, data.remaining() << 1, data, data.position() << 1);
	}

	public static void glBufferSubDataARB(int target, int offset, FloatBuffer data) {
		BufferChecks.checkDirect(data);
		nglBufferSubDataARB(target, offset, data.remaining() << 2, data, data.position() << 2);
	}

	public static void glBufferSubDataARB(int target, int offset, IntBuffer data) {
		BufferChecks.checkDirect(data);
		nglBufferSubDataARB(target, offset, data.remaining() << 2, data, data.position() << 2);
	}

	private static native void nglBufferSubDataARB(int target, int offset, int size, Buffer data, int data_offset);

	public static void glGetBufferSubDataARB(int target, int offset, ByteBuffer data) {
		BufferChecks.checkDirect(data);
		nglGetBufferSubDataARB(target, offset, data.remaining(), data, data.position());
	}

	public static void glGetBufferSubDataARB(int target, int offset, ShortBuffer data) {
		BufferChecks.checkDirect(data);
		nglGetBufferSubDataARB(target, offset, data.remaining() << 1, data, data.position() << 1);
	}

	public static void glGetBufferSubDataARB(int target, int offset, IntBuffer data) {
		BufferChecks.checkDirect(data);
		nglGetBufferSubDataARB(target, offset, data.remaining() << 2, data, data.position() << 2);
	}

	public static void glGetBufferSubDataARB(int target, int offset, FloatBuffer data) {
		BufferChecks.checkDirect(data);
		nglGetBufferSubDataARB(target, offset, data.remaining() << 2, data, data.position() << 2);
	}

	private static native void nglGetBufferSubDataARB(int target, int offset, int size, Buffer data, int data_offset);

	/**
	 * glMapBufferARB maps a gl vertex buffer buffer to a ByteBuffer. The oldBuffer argument can be null,
	 * in which case a new ByteBuffer will be created, pointing to the returned memory. If oldBuffer is non-null,
	 * it will be returned if it points to the same mapped memory, otherwise a new ByteBuffer is created. That
	 * way, an application will normally use glMapBufferARB like this:
	 * <p/>
	 * ByteBuffer mapped_buffer; mapped_buffer = glMapBufferARB(..., ..., ..., null); ... // Another map on the same buffer mapped_buffer = glMapBufferARB(..., ..., ..., mapped_buffer);
	 *
	 * @param size      The size of the buffer area.
	 * @param oldBuffer A ByteBuffer. If this argument points to the same address as the new mapping, it will be returned and no new buffer will be created. In that case, size is ignored.
	 *
	 * @return A ByteBuffer representing the mapped buffer memory.
	 */
	public static native ByteBuffer glMapBufferARB(int target, int access, int size, ByteBuffer oldBuffer);

	public static native boolean glUnmapBufferARB(int target);

	public static void glGetBufferParameterARB(int target, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetBufferParameterivARB(target, pname, params, params.position());
	}

	private static native void nglGetBufferParameterivARB(int target, int pname, IntBuffer params, int params_offset);

	public static native ByteBuffer glGetBufferPointerARB(int target, int pname, int size);
}
