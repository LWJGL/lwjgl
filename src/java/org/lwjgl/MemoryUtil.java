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
package org.lwjgl;

import java.lang.reflect.Field;
import java.nio.*;

/**
 * [INTERNAL USE ONLY]
 * <p/>
 * This class provides utility methods for passing buffer addresses to JNI API calls.
 *
 * @author Spasi
 */
public final class MemoryUtil {

	private static final Accessor memUtil;

	static {
		Accessor util;
		try {
			// Depends on java.nio.Buffer#address and sun.misc.Unsafe
			util = loadAccessor("org.lwjgl.MemoryUtilSun$AccessorUnsafe");
		} catch (Exception e0) {
			try {
				// Depends on java.nio.Buffer#address and sun.reflect.FieldAccessor
				util = loadAccessor("org.lwjgl.MemoryUtilSun$AccessorReflectFast");
			} catch (Exception e1) {
				try {
					// Depends on java.nio.Buffer#address
					util = new AccessorReflect();
				} catch (Exception e2) {
					LWJGLUtil.log("Unsupported JVM detected, this will likely result in low performance. Please inform LWJGL developers.");
					util = new AccessorJNI();
				}
			}
		}

		LWJGLUtil.log("MemoryUtil Accessor: " + util.getClass().getSimpleName());
		memUtil = util;

		/*
		BENCHMARK RESULTS - Oracle Server VM:

		Unsafe: 4ns
		ReflectFast: 8ns
		Reflect: 10ns
		JNI: 82ns

		BENCHMARK RESULTS - Oracle Client VM:

		Unsafe: 5ns
		ReflectFast: 81ns
		Reflect: 85ns
		JNI: 87ns

		On non-Oracle VMs, Unsafe should be the fastest implementation as well. In the absence
		of Unsafe, performance will depend on how reflection and JNI are implemented. For now
		we'll go with what we see on the Oracle VM (that is, we'll prefer reflection over JNI).
		 */
	}

	private MemoryUtil() {
	}

	public static String wrap(final String test) {
		return "MemoryUtil.getAddress(" + test + ")";
	}

	/**
	 * Returns the memory address of the specified buffer. [INTERNAL USE ONLY]
	 *
	 * @param buffer the buffer
	 *
	 * @return the memory address
	 */
	public static long getAddress0(Buffer buffer) { return memUtil.getAddress(buffer); }

	public static long getAddress0Safe(Buffer buffer) { return buffer == null ? 0L : memUtil.getAddress(buffer); }

	public static long getAddress0(PointerBuffer buffer) { return memUtil.getAddress(buffer.getBuffer()); }

	public static long getAddress0Safe(PointerBuffer buffer) { return buffer == null ? 0L : memUtil.getAddress(buffer.getBuffer()); }

	// --- [ API utilities ] ---

	public static long getAddress(ByteBuffer buffer) { return getAddress(buffer, buffer.position()); }

	public static long getAddress(ByteBuffer buffer, int position) { return getAddress0(buffer) + position; }

	public static long getAddress(ShortBuffer buffer) { return getAddress(buffer, buffer.position()); }

	public static long getAddress(ShortBuffer buffer, int position) { return getAddress0(buffer) + (position << 1); }

	public static long getAddress(CharBuffer buffer) { return getAddress(buffer, buffer.position()); }

	public static long getAddress(CharBuffer buffer, int position) { return getAddress0(buffer) + (position << 1); }

	public static long getAddress(IntBuffer buffer) { return getAddress(buffer, buffer.position()); }

	public static long getAddress(IntBuffer buffer, int position) { return getAddress0(buffer) + (position << 2); }

	public static long getAddress(FloatBuffer buffer) { return getAddress(buffer, buffer.position()); }

	public static long getAddress(FloatBuffer buffer, int position) { return getAddress0(buffer) + (position << 2); }

	public static long getAddress(LongBuffer buffer) { return getAddress(buffer, buffer.position()); }

	public static long getAddress(LongBuffer buffer, int position) { return getAddress0(buffer) + (position << 3); }

	public static long getAddress(DoubleBuffer buffer) { return getAddress(buffer, buffer.position()); }

	public static long getAddress(DoubleBuffer buffer, int position) { return getAddress0(buffer) + (position << 3); }

	public static long getAddress(PointerBuffer buffer) { return getAddress(buffer, buffer.position()); }

	public static long getAddress(PointerBuffer buffer, int position) { return getAddress0(buffer) + (position * PointerBuffer.getPointerSize()); }

	// --- [ API utilities - Safe ] ---

	public static long getAddressSafe(ByteBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }

	public static long getAddressSafe(ByteBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }

	public static long getAddressSafe(ShortBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }

	public static long getAddressSafe(ShortBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }

	public static long getAddressSafe(CharBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }

	public static long getAddressSafe(CharBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }

	public static long getAddressSafe(IntBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }

	public static long getAddressSafe(IntBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }

	public static long getAddressSafe(FloatBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }

	public static long getAddressSafe(FloatBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }

	public static long getAddressSafe(LongBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }

	public static long getAddressSafe(LongBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }

	public static long getAddressSafe(DoubleBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }

	public static long getAddressSafe(DoubleBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }

	public static long getAddressSafe(PointerBuffer buffer) { return buffer == null ? 0L : getAddress(buffer); }

	public static long getAddressSafe(PointerBuffer buffer, int position) { return buffer == null ? 0L : getAddress(buffer, position); }

	interface Accessor {

		long getAddress(Buffer buffer);

	}

	private static Accessor loadAccessor(final String className) throws Exception {
		return (Accessor)Class.forName(className).newInstance();
	}

	/** Default implementation. */
	private static class AccessorJNI implements Accessor {

		public long getAddress(final Buffer buffer) {
			return BufferUtils.getBufferAddress(buffer);
		}

	}

	/** Implementation using reflection on ByteBuffer. */
	private static class AccessorReflect implements Accessor {

		private final Field address;

		AccessorReflect() {
			try {
				address = getAddressField();
			} catch (NoSuchFieldException e) {
				throw new UnsupportedOperationException(e);
			}
			address.setAccessible(true);
		}

		public long getAddress(final Buffer buffer) {
			try {
				return address.getLong(buffer);
			} catch (IllegalAccessException e) {
				// cannot happen
				return 0L;
			}
		}

	}

	static Field getAddressField() throws NoSuchFieldException {
		return getDeclaredFieldRecursive(ByteBuffer.class, "address");
	}

	private static Field getDeclaredFieldRecursive(Class<?> type, final String fieldName) throws NoSuchFieldException {
		while ( type != null ) {
			try {
				return type.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				type = type.getSuperclass();
			}
		}

		throw new NoSuchFieldException(fieldName + " does not exist in " + type.getSimpleName() + " or any of its superclasses.");
	}

}