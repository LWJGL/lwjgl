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
package org.lwjgl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.nio.LongBuffer;

/**
 * <p>A class to check buffer boundaries in general. If there is unsufficient space
 * in the buffer when the call is made then a buffer overflow would otherwise
 * occur and cause unexpected behaviour, a crash, or worse, a security risk.
 * </p>
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */
public class BufferChecks {
	/** Static methods only! */
	private BufferChecks() {
	}

	/**
	 * Default buffer size for most buffer checks.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 4;

	/**
	 * Helper methods to ensure a function pointer is not-null (0)
	 */
	public static void checkFunctionAddress(long pointer) {
		if (pointer == 0) {
			throw new IllegalStateException("Function is not supported");
		}
	}

	/**
	 * Helper methods to ensure a ByteBuffer is null-terminated
	 */
	public static void checkNullTerminated(ByteBuffer buf) {
		if (buf.get(buf.limit() - 1) != 0) {
			throw new IllegalArgumentException("Missing null termination");
		}
	}

	public static void checkNotNull(Object o) {
		if (o == null)
			throw new IllegalArgumentException("Null argument");
	}

	/**
	 * Helper methods to ensure a buffer is direct or null.
	 */
	public static void checkDirectOrNull(ByteBuffer buf) {
		if (buf != null) {
			checkDirect(buf);
		}
	}

	public static void checkDirectOrNull(ShortBuffer buf) {
		if (buf != null) {
			checkDirect(buf);
		}
	}

	public static void checkDirectOrNull(IntBuffer buf) {
		if (buf != null) {
			checkDirect(buf);
		}
	}

	public static void checkDirectOrNull(LongBuffer buf) {
		if (buf != null) {
			checkDirect(buf);
		}
	}

	public static void checkDirectOrNull(FloatBuffer buf) {
		if (buf != null) {
			checkDirect(buf);
		}
	}

	public static void checkDirectOrNull(DoubleBuffer buf) {
		if (buf != null) {
			checkDirect(buf);
		}
	}

	/**
	 * Helper methods to ensure a buffer is direct (and, implicitly, non-null).
	 */
	public static void checkDirectBuffer(Buffer buf) {
		if (buf instanceof FloatBuffer)
			checkDirect((FloatBuffer)buf);
		else if (buf instanceof ByteBuffer)
			checkDirect((ByteBuffer)buf);
		else if (buf instanceof ShortBuffer)
			checkDirect((ShortBuffer)buf);
		else if (buf instanceof IntBuffer)
			checkDirect((IntBuffer)buf);
		else if (buf instanceof LongBuffer)
			checkDirect((LongBuffer)buf);
		else if (buf instanceof DoubleBuffer)
			checkDirect((DoubleBuffer)buf);
		else
			throw new IllegalStateException("Unsupported buffer type");
	}

	public static void checkDirect(ByteBuffer buf) {
		if (!buf.isDirect()) {
			throw new IllegalArgumentException("ByteBuffer is not direct");
		}
	}

	public static void checkDirect(ShortBuffer buf) {
		if (!buf.isDirect()) {
			throw new IllegalArgumentException("ShortBuffer is not direct");
		}
	}

	public static void checkDirect(IntBuffer buf) {
		if (!buf.isDirect()) {
			throw new IllegalArgumentException("IntBuffer is not direct");
		}
	}

	public static void checkDirect(LongBuffer buf) {
		if (!buf.isDirect()) {
			throw new IllegalArgumentException("LongBuffer is not direct");
		}
	}

	public static void checkDirect(FloatBuffer buf) {
		if (!buf.isDirect()) {
			throw new IllegalArgumentException("FloatBuffer is not direct");
		}
	}

	public static void checkDirect(DoubleBuffer buf) {
		if (!buf.isDirect()) {
			throw new IllegalArgumentException("DoubleBuffer is not direct");
		}
	}

	/**
	 * Helper method to ensure a buffer is big enough to receive data from a
	 * glGet* operation.
	 *
	 * @param buf
	 *            The buffer to check
	 * @param size
	 * 			  The minimum buffer size
	 * @throws IllegalArgumentException
	 */
	private static void checkBufferSize(Buffer buf, int size) {
		if (buf.remaining() < size) {
			throw new IllegalArgumentException("Number of remaining buffer elements is " + buf.remaining() + ", must be at least " + size);
		}
	}

	public static void checkBuffer(ByteBuffer buf, int size) {
		checkBufferSize(buf, size);
		checkDirect(buf);
	}

	public static void checkBuffer(ShortBuffer buf, int size) {
		checkBufferSize(buf, size);
		checkDirect(buf);
	}

	public static void checkBuffer(IntBuffer buf, int size) {
		checkBufferSize(buf, size);
		checkDirect(buf);
	}

	public static void checkBuffer(LongBuffer buf, int size) {
		checkBufferSize(buf, size);
		checkDirect(buf);
	}

	public static void checkBuffer(FloatBuffer buf, int size) {
		checkBufferSize(buf, size);
		checkDirect(buf);
	}

	public static void checkBuffer(DoubleBuffer buf, int size) {
		checkBufferSize(buf, size);
		checkDirect(buf);
	}

	/**
	 * Helper methods to ensure a buffer is big enough to receive data from a
	 * glGet* operation. To avoid unnecessarily complex buffer size checking
	 * we've just set the bar artificially high and insist that any receiving
	 * buffer has at least 4 remaining().
	 *
	 * @param buf
	 *            The buffer to check
	 * @throws IllegalArgumentException
	 */
	public static void checkBuffer(ByteBuffer buf) {
		checkBuffer(buf, DEFAULT_BUFFER_SIZE);
	}

	public static void checkBuffer(ShortBuffer buf) {
		checkBuffer(buf, DEFAULT_BUFFER_SIZE);
	}

	public static void checkBuffer(IntBuffer buf) {
		checkBuffer(buf, DEFAULT_BUFFER_SIZE);
	}

	public static void checkBuffer(LongBuffer buf) {
		checkBuffer(buf, DEFAULT_BUFFER_SIZE);
	}

	public static void checkBuffer(FloatBuffer buf) {
		checkBuffer(buf, DEFAULT_BUFFER_SIZE);
	}

	public static void checkBuffer(DoubleBuffer buf) {
		checkBuffer(buf, DEFAULT_BUFFER_SIZE);
	}
}
