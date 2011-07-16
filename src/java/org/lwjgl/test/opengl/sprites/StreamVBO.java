/*
 * Copyright (c) 2002-2011 LWJGL Project
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
package org.lwjgl.test.opengl.sprites;

import org.lwjgl.LWJGLUtil;

import java.nio.ByteBuffer;

import static java.lang.Math.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * This class implements VBO orphaning, useful for streaming
 * dynamically generated geometry to the GPU. OpenGL 3.0 or
 * higher is required. See
 * {@url http://www.opengl.org/wiki/Buffer_Object_Streaming}
 * under "Buffer update" for details.
 *
 * @author Spasi
 */
public class StreamVBO {

	private final int  target;
	private final long size;
	private final int  padding;

	private int ID;

	private long cursor;

	public StreamVBO(final int target, final int size) {
		this(target, size, 64);
	}

	public StreamVBO(final int target, final int size, final int padding) {
		this.target = target;
		this.padding = padding;
		this.size = max(pad(size), padding);

		ID = glGenBuffers();

		glBindBuffer(target, ID);
		glBufferData(target, this.size, GL_STREAM_DRAW);
	}

	public int getTarget() {
		return target;
	}

	public int getID() {
		return ID;
	}

	public long getSize() {
		return size;
	}

	public int getPadding() {
		return padding;
	}

	public void bind() {
		glBindBuffer(target, ID);
	}

	public void init(final int offset, final ByteBuffer data) {
		glBufferSubData(target, offset, data);
	}

	public void unmap() {
		glUnmapBuffer(target);
	}

	public void destroy() {
		glBindBuffer(target, 0);
		glDeleteBuffers(ID);
	}

	public void reset() {
		// Orphan current buffer and allocate a new one
		glBufferData(target, size, GL_STREAM_DRAW);
		// Flush
		cursor = 0;
	}

	public ByteBuffer map(final int bytes) {
		return map(bytes, null);
	}

	public ByteBuffer map(final int bytes, final ByteBuffer old_buffer) {
		return doMap(pad(bytes), old_buffer);
	}

	private int pad(int size) {
		final int mod = size % padding;
		if ( mod == 0 )
			return size;

		return size + padding - mod;
	}

	private ByteBuffer doMap(final int bytes, final ByteBuffer old_buffer) {
		if ( LWJGLUtil.CHECKS && size < bytes )
			throw new IllegalArgumentException(Integer.toString(bytes));

		if ( size < cursor + bytes )
			reset();

		final ByteBuffer map = glMapBufferRange(target, cursor, bytes, GL_MAP_WRITE_BIT | GL_MAP_UNSYNCHRONIZED_BIT, old_buffer);
		cursor += bytes;
		return map;
	}

}