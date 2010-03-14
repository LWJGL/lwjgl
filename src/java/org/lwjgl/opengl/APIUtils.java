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

import org.lwjgl.BufferUtils;

import java.nio.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/** @author spasi */
final class APIUtils {

	private static final int INITIAL_BUFFER_SIZE = 256;
	private static final int INITIAL_LENGTHS_SIZE = 4;

	private static final int BUFFERS_SIZE = 32;

	private static final ThreadLocal arrayTL = new ThreadLocal() {
		protected Object initialValue() { return new char[INITIAL_BUFFER_SIZE]; }
	};

	private static final ThreadLocal bufferTL = new ThreadLocal() {
		protected Object initialValue() { return BufferUtils.createByteBuffer(INITIAL_BUFFER_SIZE); }
	};

	private static final ThreadLocal lengthsTL = new ThreadLocal() {
		protected Object initialValue() { return BufferUtils.createIntBuffer(INITIAL_LENGTHS_SIZE); }
	};

	private static final ThreadLocal infiniteSeqTL = new ThreadLocal() {
		protected Object initialValue() { return new InfiniteCharSequence(); }
	};

	private static final ThreadLocal buffersTL = new ThreadLocal() {
		protected Object initialValue() { return new Buffers(); }
	};

	private static CharsetEncoder encoder = Charset.forName("US-ASCII").newEncoder();

	private APIUtils() {
	}

	private static char[] getArray(final int size) {
		char[] array = (char[])arrayTL.get();

		if ( array.length < size ) {
			int sizeNew = array.length << 1;
			while ( sizeNew < size )
				sizeNew <<= 1;

			array = new char[size];
			arrayTL.set(array);
		}

		return array;
	}

	static ByteBuffer getBufferByte(final int size) {
		ByteBuffer buffer = (ByteBuffer)bufferTL.get();

		if ( buffer.capacity() < size ) {
			int sizeNew = buffer.capacity() << 1;
			while ( sizeNew < size )
				sizeNew <<= 1;

			buffer = BufferUtils.createByteBuffer(size);
			bufferTL.set(buffer);
		} else
			buffer.clear();

		return buffer;
	}

	private static ByteBuffer getBufferByteOffset(final int size) {
		ByteBuffer buffer = (ByteBuffer)bufferTL.get();

		if ( buffer.capacity() < size ) {
			int sizeNew = buffer.capacity() << 1;
			while ( sizeNew < size )
				sizeNew <<= 1;

			final ByteBuffer bufferNew = BufferUtils.createByteBuffer(size);
			bufferNew.put(buffer);
			bufferTL.set(buffer = bufferNew);
		} else {
			buffer.position(buffer.limit());
			buffer.limit(buffer.capacity());
		}

		return buffer;
	}

	static ShortBuffer getBufferShort() { return ((Buffers)buffersTL.get()).shorts; }

	static IntBuffer getBufferInt() { return ((Buffers)buffersTL.get()).ints; }

	static LongBuffer getBufferLong() { return ((Buffers)buffersTL.get()).longs; }

	static FloatBuffer getBufferFloat() { return ((Buffers)buffersTL.get()).floats; }

	static DoubleBuffer getBufferDouble() { return ((Buffers)buffersTL.get()).doubles; }

	static IntBuffer getLengths() {
		return getLengths(1);
	}

	static IntBuffer getLengths(final int size) {
		IntBuffer lengths = (IntBuffer)lengthsTL.get();

		if ( lengths.capacity() < size ) {
			int sizeNew = lengths.capacity();
			while ( sizeNew < size )
				sizeNew <<= 1;

			lengths = BufferUtils.createIntBuffer(size);
			lengthsTL.set(lengths);
		} else
			lengths.clear();

		return lengths;
	}

	private static InfiniteCharSequence getInfiniteSeq() {
		return (InfiniteCharSequence)infiniteSeqTL.get();
	}

	private static void encode(final ByteBuffer buffer, final CharSequence string) {
		final InfiniteCharSequence infiniteSeq = getInfiniteSeq();
		infiniteSeq.setString(string);
		encoder.encode(infiniteSeq.buffer, buffer, true);
	}

	/**
	 * Reads a byte string from the specified buffer.
	 *
	 * @param buffer
	 *
	 * @return the buffer as a String.
	 */
	static String getString(final ByteBuffer buffer) {
		final int length = buffer.remaining();
		final char[] charArray = getArray(length);

		for ( int i = buffer.position(); i < buffer.limit(); i++ )
			charArray[i - buffer.position()] = (char)buffer.get(i);

		return new String(charArray, 0, length);
	}

	/**
	 * Returns a buffer containing the specified string as bytes.
	 *
	 * @param string
	 *
	 * @return the String as a ByteBuffer
	 */
	static ByteBuffer getBuffer(final CharSequence string) {
		final ByteBuffer buffer = getBufferByte(string.length());

		encode(buffer, string);

		buffer.flip();
		return buffer;
	}

	/**
	 * Returns a buffer containing the specified string as bytes, starting at the specified offset.
	 *
	 * @param string
	 *
	 * @return the String as a ByteBuffer
	 */
	static ByteBuffer getBuffer(final CharSequence string, final int offset) {
		final ByteBuffer buffer = getBufferByteOffset(offset + string.length());

		encode(buffer, string);

		buffer.flip();
		return buffer;
	}

	/**
	 * Returns a buffer containing the specified string as bytes, including null-termination.
	 *
	 * @param string
	 *
	 * @return the String as a ByteBuffer
	 */
	static ByteBuffer getBufferNT(final CharSequence string) {
		final ByteBuffer buffer = getBufferByte(string.length() + 1);

		encode(buffer, string);

		buffer.put((byte)0);
		buffer.flip();
		return buffer;
	}

	static int getTotalLength(final CharSequence[] strings) {
		int length = 0;
		for ( int i = 0; i < strings.length; i++ )
			length += strings[i].length();

		return length;
	}

	/**
	 * Returns a buffer containing the specified strings as bytes.
	 *
	 * @param strings
	 *
	 * @return the Strings as a ByteBuffer
	 */
	static ByteBuffer getBuffer(final CharSequence[] strings) {
		final ByteBuffer buffer = getBufferByte(getTotalLength(strings));

		final InfiniteCharSequence infiniteSeq = getInfiniteSeq();
		for ( int i = 0; i < strings.length; i++ ) {
			infiniteSeq.setString(strings[i]);
			encoder.encode(infiniteSeq.buffer, buffer, true);
		}
		infiniteSeq.clear();

		buffer.flip();
		return buffer;
	}

	/**
	 * Returns a buffer containing the specified strings as bytes, including null-termination.
	 *
	 * @param strings
	 *
	 * @return the Strings as a ByteBuffer
	 */
	static ByteBuffer getBufferNT(final CharSequence[] strings) {
		final ByteBuffer buffer = getBufferByte(getTotalLength(strings) + strings.length);

		final InfiniteCharSequence infiniteSeq = getInfiniteSeq();
		for ( int i = 0; i < strings.length; i++ ) {
			infiniteSeq.setString(strings[i]);
			encoder.encode(infiniteSeq.buffer, buffer, true);
			buffer.put((byte)0);
		}
		infiniteSeq.clear();

		buffer.flip();
		return buffer;
	}

	/**
	 * Returns a buffer containing the lengths of the specified strings.
	 *
	 * @param strings
	 *
	 * @return the String lengths in an IntBuffer
	 */
	static IntBuffer getLengths(final CharSequence[] strings) {
		IntBuffer buffer = getLengths(strings.length);

		for ( int i = 0; i < strings.length; i++ )
			buffer.put(strings[i].length());

		buffer.flip();
		return buffer;
	}

	/**
	 * A mutable CharSequence with very large initial length. We can wrap this in a re-usable CharBuffer for decoding.
	 * We cannot subclass CharBuffer because of {@link CharBuffer#toString(int,int)}.
	 */
	private static class InfiniteCharSequence implements CharSequence {

		final CharBuffer buffer;

		CharSequence string;

		InfiniteCharSequence() {
			buffer = CharBuffer.wrap(this);
		}

		void setString(final CharSequence string) {
			this.string = string;
			this.buffer.position(0);
			this.buffer.limit(string.length());
		}

		void clear() {
			this.string = null;
		}

		public int length() {
			return Integer.MAX_VALUE;
		}

		public char charAt(final int index) {
			return string.charAt(index);
		}

		public CharSequence subSequence(final int start, final int end) {
			return string.subSequence(start, end);
		}
	}

	private static class Buffers {

		final ShortBuffer shorts;
		final IntBuffer ints;
		final LongBuffer longs;

		final FloatBuffer floats;
		final DoubleBuffer doubles;

		Buffers() {
			shorts = BufferUtils.createShortBuffer(BUFFERS_SIZE);
			ints = BufferUtils.createIntBuffer(BUFFERS_SIZE);
			longs = BufferUtils.createLongBuffer(BUFFERS_SIZE);

			floats = BufferUtils.createFloatBuffer(BUFFERS_SIZE);
			doubles = BufferUtils.createDoubleBuffer(BUFFERS_SIZE);
		}

	}

}