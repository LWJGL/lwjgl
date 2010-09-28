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
package org.lwjgl;

import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.nio.LongBuffer;
import java.nio.DoubleBuffer;
import java.nio.ByteOrder;

/**
 * Utility class to cache thread local direct buffers so when we are passed a non-direct buffer,
 * we can put its contents into a cached direct buffer and use that at the native side instead.
 *
 * Internal class, don't use.
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision: 2762 $
 * $Id: BufferChecks.java 2762 2007-04-11 16:13:05Z elias_naur $
 */
public final class NondirectBufferWrapper {
	private static final int INITIAL_BUFFER_SIZE = 1;

	private static final ThreadLocal<CachedBuffers> thread_buffer = new ThreadLocal<CachedBuffers>() {
		protected CachedBuffers initialValue() {
			return new CachedBuffers(INITIAL_BUFFER_SIZE);
		}
	};

	private static CachedBuffers getCachedBuffers(int minimum_byte_size) {
		CachedBuffers buffers = thread_buffer.get();
		int current_byte_size = buffers.byte_buffer.capacity();
		if (minimum_byte_size > current_byte_size) {
			buffers = new CachedBuffers(minimum_byte_size);
			thread_buffer.set(buffers);
		}
		return buffers;
	}

	public static ByteBuffer wrapNoCopyBuffer(ByteBuffer buf, int size) {
		BufferChecks.checkBufferSize(buf, size);
		return wrapNoCopyDirect(buf);
	}

	public static ShortBuffer wrapNoCopyBuffer(ShortBuffer buf, int size) {
		BufferChecks.checkBufferSize(buf, size);
		return wrapNoCopyDirect(buf);
	}

	public static IntBuffer wrapNoCopyBuffer(IntBuffer buf, int size) {
		BufferChecks.checkBufferSize(buf, size);
		return wrapNoCopyDirect(buf);
	}

	public static LongBuffer wrapNoCopyBuffer(LongBuffer buf, int size) {
		BufferChecks.checkBufferSize(buf, size);
		return wrapNoCopyDirect(buf);
	}

	public static FloatBuffer wrapNoCopyBuffer(FloatBuffer buf, int size) {
		BufferChecks.checkBufferSize(buf, size);
		return wrapNoCopyDirect(buf);
	}

	public static DoubleBuffer wrapNoCopyBuffer(DoubleBuffer buf, int size) {
		BufferChecks.checkBufferSize(buf, size);
		return wrapNoCopyDirect(buf);
	}

	public static ByteBuffer wrapBuffer(ByteBuffer buf, int size) {
		BufferChecks.checkBufferSize(buf, size);
		return wrapDirect(buf);
	}

	public static ShortBuffer wrapBuffer(ShortBuffer buf, int size) {
		BufferChecks.checkBufferSize(buf, size);
		return wrapDirect(buf);
	}

	public static IntBuffer wrapBuffer(IntBuffer buf, int size) {
		BufferChecks.checkBufferSize(buf, size);
		return wrapDirect(buf);
	}

	public static LongBuffer wrapBuffer(LongBuffer buf, int size) {
		BufferChecks.checkBufferSize(buf, size);
		return wrapDirect(buf);
	}

	public static FloatBuffer wrapBuffer(FloatBuffer buf, int size) {
		BufferChecks.checkBufferSize(buf, size);
		return wrapDirect(buf);
	}

	public static DoubleBuffer wrapBuffer(DoubleBuffer buf, int size) {
		BufferChecks.checkBufferSize(buf, size);
		return wrapDirect(buf);
	}

	public static ByteBuffer wrapDirect(ByteBuffer buffer) {
		if (!buffer.isDirect())
			return doWrap(buffer);
		return buffer;
	}

	public static ShortBuffer wrapDirect(ShortBuffer buffer) {
		if (!buffer.isDirect())
			return doWrap(buffer);
		return buffer;
	}

	public static FloatBuffer wrapDirect(FloatBuffer buffer) {
		if (!buffer.isDirect())
			return doWrap(buffer);
		return buffer;
	}

	public static IntBuffer wrapDirect(IntBuffer buffer) {
		if (!buffer.isDirect())
			return doWrap(buffer);
		return buffer;
	}

	public static LongBuffer wrapDirect(LongBuffer buffer) {
		if (!buffer.isDirect())
			return doWrap(buffer);
		return buffer;
	}

	public static DoubleBuffer wrapDirect(DoubleBuffer buffer) {
		if (!buffer.isDirect())
			return doWrap(buffer);
		return buffer;
	}

	public static ByteBuffer wrapNoCopyDirect(ByteBuffer buffer) {
		if (!buffer.isDirect())
			return doNoCopyWrap(buffer);
		return buffer;
	}

	public static ShortBuffer wrapNoCopyDirect(ShortBuffer buffer) {
		if (!buffer.isDirect())
			return doNoCopyWrap(buffer);
		return buffer;
	}

	public static FloatBuffer wrapNoCopyDirect(FloatBuffer buffer) {
		if (!buffer.isDirect())
			return doNoCopyWrap(buffer);
		return buffer;
	}

	public static IntBuffer wrapNoCopyDirect(IntBuffer buffer) {
		if (!buffer.isDirect())
			return doNoCopyWrap(buffer);
		return buffer;
	}

	public static LongBuffer wrapNoCopyDirect(LongBuffer buffer) {
		if (!buffer.isDirect())
			return doNoCopyWrap(buffer);
		return buffer;
	}

	public static DoubleBuffer wrapNoCopyDirect(DoubleBuffer buffer) {
		if (!buffer.isDirect())
			return doNoCopyWrap(buffer);
		return buffer;
	}

	public static void copy(ByteBuffer src, ByteBuffer dst) {
		if (dst != null && !dst.isDirect()) {
			int saved_position = dst.position();
			dst.put(src);
			dst.position(saved_position);
		}
	}

	public static void copy(ShortBuffer src, ShortBuffer dst) {
		if (dst != null && !dst.isDirect()) {
			int saved_position = dst.position();
			dst.put(src);
			dst.position(saved_position);
		}
	}

	public static void copy(IntBuffer src, IntBuffer dst) {
		if (dst != null && !dst.isDirect()) {
			int saved_position = dst.position();
			dst.put(src);
			dst.position(saved_position);
		}
	}

	public static void copy(FloatBuffer src, FloatBuffer dst) {
		if (dst != null && !dst.isDirect()) {
			int saved_position = dst.position();
			dst.put(src);
			dst.position(saved_position);
		}
	}

	public static void copy(LongBuffer src, LongBuffer dst) {
		if (dst != null && !dst.isDirect()) {
			int saved_position = dst.position();
			dst.put(src);
			dst.position(saved_position);
		}
	}

	public static void copy(DoubleBuffer src, DoubleBuffer dst) {
		if (dst != null && !dst.isDirect()) {
			int saved_position = dst.position();
			dst.put(src);
			dst.position(saved_position);
		}
	}

	private static ByteBuffer doNoCopyWrap(ByteBuffer buffer) {
		ByteBuffer direct_buffer = lookupBuffer(buffer);
		direct_buffer.limit(buffer.limit());
		direct_buffer.position(buffer.position());
		return direct_buffer;
	}

	private static ShortBuffer doNoCopyWrap(ShortBuffer buffer) {
		ShortBuffer direct_buffer = lookupBuffer(buffer);
		direct_buffer.limit(buffer.limit());
		direct_buffer.position(buffer.position());
		return direct_buffer;
	}

	private static IntBuffer doNoCopyWrap(IntBuffer buffer) {
		IntBuffer direct_buffer = lookupBuffer(buffer);
		direct_buffer.limit(buffer.limit());
		direct_buffer.position(buffer.position());
		return direct_buffer;
	}

	private static FloatBuffer doNoCopyWrap(FloatBuffer buffer) {
		FloatBuffer direct_buffer = lookupBuffer(buffer);
		direct_buffer.limit(buffer.limit());
		direct_buffer.position(buffer.position());
		return direct_buffer;
	}

	private static LongBuffer doNoCopyWrap(LongBuffer buffer) {
		LongBuffer direct_buffer = lookupBuffer(buffer);
		direct_buffer.limit(buffer.limit());
		direct_buffer.position(buffer.position());
		return direct_buffer;
	}

	private static DoubleBuffer doNoCopyWrap(DoubleBuffer buffer) {
		DoubleBuffer direct_buffer = lookupBuffer(buffer);
		direct_buffer.limit(buffer.limit());
		direct_buffer.position(buffer.position());
		return direct_buffer;
	}

	private static ByteBuffer lookupBuffer(ByteBuffer buffer) {
		return getCachedBuffers(buffer.remaining()).byte_buffer;
	}

	private static ByteBuffer doWrap(ByteBuffer buffer) {
		ByteBuffer direct_buffer = lookupBuffer(buffer);
		direct_buffer.clear();
		int saved_position = buffer.position();
		direct_buffer.put(buffer);
		buffer.position(saved_position);
		direct_buffer.flip();
		return direct_buffer;
	}

	private static ShortBuffer lookupBuffer(ShortBuffer buffer) {
		CachedBuffers buffers = getCachedBuffers(buffer.remaining()*2);
		return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.short_buffer_little : buffers.short_buffer_big;
	}

	private static ShortBuffer doWrap(ShortBuffer buffer) {
		ShortBuffer direct_buffer = lookupBuffer(buffer);
		direct_buffer.clear();
		int saved_position = buffer.position();
		direct_buffer.put(buffer);
		buffer.position(saved_position);
		direct_buffer.flip();
		return direct_buffer;
	}

	private static FloatBuffer lookupBuffer(FloatBuffer buffer) {
		CachedBuffers buffers = getCachedBuffers(buffer.remaining()*4);
		return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.float_buffer_little : buffers.float_buffer_big;
	}

	private static FloatBuffer doWrap(FloatBuffer buffer) {
		FloatBuffer direct_buffer = lookupBuffer(buffer);
		direct_buffer.clear();
		int saved_position = buffer.position();
		direct_buffer.put(buffer);
		buffer.position(saved_position);
		direct_buffer.flip();
		return direct_buffer;
	}

	private static IntBuffer lookupBuffer(IntBuffer buffer) {
		CachedBuffers buffers = getCachedBuffers(buffer.remaining()*4);
		return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.int_buffer_little : buffers.int_buffer_big;
	}

	private static IntBuffer doWrap(IntBuffer buffer) {
		IntBuffer direct_buffer = lookupBuffer(buffer);
		direct_buffer.clear();
		int saved_position = buffer.position();
		direct_buffer.put(buffer);
		buffer.position(saved_position);
		direct_buffer.flip();
		return direct_buffer;
	}

	private static LongBuffer lookupBuffer(LongBuffer buffer) {
		CachedBuffers buffers = getCachedBuffers(buffer.remaining()*8);
		return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.long_buffer_little : buffers.long_buffer_big;
	}

	private static LongBuffer doWrap(LongBuffer buffer) {
		LongBuffer direct_buffer = lookupBuffer(buffer);
		direct_buffer.clear();
		int saved_position = buffer.position();
		direct_buffer.put(buffer);
		buffer.position(saved_position);
		direct_buffer.flip();
		return direct_buffer;
	}

	private static DoubleBuffer lookupBuffer(DoubleBuffer buffer) {
		CachedBuffers buffers = getCachedBuffers(buffer.remaining()*8);
		return buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffers.double_buffer_little : buffers.double_buffer_big;
	}

	private static DoubleBuffer doWrap(DoubleBuffer buffer) {
		DoubleBuffer direct_buffer = lookupBuffer(buffer);
		direct_buffer.clear();
		int saved_position = buffer.position();
		direct_buffer.put(buffer);
		buffer.position(saved_position);
		direct_buffer.flip();
		return direct_buffer;
	}

	private static final class CachedBuffers {
		private final ByteBuffer byte_buffer;
		private final ShortBuffer short_buffer_big;
		private final IntBuffer int_buffer_big;
		private final FloatBuffer float_buffer_big;
		private final LongBuffer long_buffer_big;
		private final DoubleBuffer double_buffer_big;
		private final ShortBuffer short_buffer_little;
		private final IntBuffer int_buffer_little;
		private final FloatBuffer float_buffer_little;
		private final LongBuffer long_buffer_little;
		private final DoubleBuffer double_buffer_little;

		private CachedBuffers(int size) {
			this.byte_buffer = ByteBuffer.allocateDirect(size);
			this.short_buffer_big = byte_buffer.asShortBuffer();
			this.int_buffer_big = byte_buffer.asIntBuffer();
			this.float_buffer_big = byte_buffer.asFloatBuffer();
			this.long_buffer_big = byte_buffer.asLongBuffer();
			this.double_buffer_big = byte_buffer.asDoubleBuffer();
			this.byte_buffer.order(ByteOrder.LITTLE_ENDIAN);
			this.short_buffer_little = byte_buffer.asShortBuffer();
			this.int_buffer_little = byte_buffer.asIntBuffer();
			this.float_buffer_little = byte_buffer.asFloatBuffer();
			this.long_buffer_little = byte_buffer.asLongBuffer();
			this.double_buffer_little = byte_buffer.asDoubleBuffer();
		}
	}
}
