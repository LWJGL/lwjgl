/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTCompiledVertexArray {
	public static final int GL_ARRAY_ELEMENT_LOCK_COUNT_EXT = 0x81a9;
	public static final int GL_ARRAY_ELEMENT_LOCK_FIRST_EXT = 0x81a8;

	private EXTCompiledVertexArray() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glUnlockArraysEXT();

	public static native void glLockArraysEXT(int first, int count);
}
