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

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/** @author spasi */
final class StringUtils {

	private static final int INITIAL_BUFFER_SIZE = 256;
	private static final int INITIAL_LENGTHS_SIZE = 4;

	private static final ThreadLocal arrayTL = new ThreadLocal() {
		protected Object initialValue() {
			return new char[INITIAL_BUFFER_SIZE];
		}
	};

	private static final ThreadLocal bufferTL = new ThreadLocal() {
		protected Object initialValue() {
			return BufferUtils.createByteBuffer(INITIAL_BUFFER_SIZE);
		}
	};

	private static final ThreadLocal lengthsTL = new ThreadLocal() {
		protected Object initialValue() {
			return BufferUtils.createIntBuffer(INITIAL_LENGTHS_SIZE);
		}
	};

	private StringUtils() {
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

	static ByteBuffer getBuffer(final int size) {
		ByteBuffer buffer = (ByteBuffer)bufferTL.get();

		if ( buffer.capacity() < size ) {
			int sizeNew = buffer.capacity() << 1;
			while ( sizeNew < size )
				sizeNew <<= 1;

			buffer = BufferUtils.createByteBuffer(size);
			bufferTL.set(buffer);
		}

		buffer.clear();
		return buffer;
	}

	private static ByteBuffer getBufferOffset(final int size) {
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

	static IntBuffer getLengths(final int size) {
		IntBuffer lengths = (IntBuffer)lengthsTL.get();

		if ( lengths.capacity() < size ) {
			int sizeNew = lengths.capacity();
			while ( sizeNew < size )
				sizeNew <<= 1;

			lengths = BufferUtils.createIntBuffer(size);
			lengthsTL.set(lengths);
		}

		lengths.clear();
		return lengths;
	}

	/*
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
		final ByteBuffer buffer = getBuffer(string.length());

		for ( int i = 0; i < string.length(); i++ )
			buffer.put((byte)string.charAt(i));

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
	static ByteBuffer getBufferOffset(final CharSequence string, final int offset) {
		final ByteBuffer buffer = getBufferOffset(offset + string.length());

		for ( int i = 0; i < string.length(); i++ )
			buffer.put((byte)string.charAt(i));

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
		final ByteBuffer buffer = getBuffer(string.length() + 1);

		for ( int i = 0; i < string.length(); i++ )
			buffer.put((byte)string.charAt(i));

		buffer.put((byte)0);
		buffer.flip();
		return buffer;
	}

	/**
	 * Returns a buffer containing the specified strings as bytes.
	 *
	 * @param strings
	 *
	 * @return the Strings as a ByteBuffer
	 */
	static ByteBuffer getBuffer(final CharSequence[] strings) {
		int length = 0;
		for ( int i = 0; i < strings.length; i++ )
			length += strings[i].length();

		final ByteBuffer buffer = getBuffer(length);

		for ( int i = 0; i < strings.length; i++ ) {
			final CharSequence string = strings[i];
			for ( int j = 0; j < string.length(); j++ )
				buffer.put((byte)string.charAt(i));
		}

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
		int length = 0;
		for ( int i = 0; i < strings.length; i++ )
			length += strings[i].length() + 1;

		final ByteBuffer buffer = getBuffer(length);

		for ( int i = 0; i < strings.length; i++ ) {
			final CharSequence string = strings[i];
			for ( int j = 0; j < string.length(); j++ )
				buffer.put((byte)string.charAt(i));
			buffer.put((byte)0);
		}

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

}