package org.lwjgl.test.opengles.util;

import java.nio.Buffer;

import static org.lwjgl.opengles.GLES20.*;

public final class BufferObjectArray extends BufferObject {

	private static int boundBOArray;

	public BufferObjectArray(final int usage) {
		super(GL_ARRAY_BUFFER, usage);
	}

	public BufferObjectArray(final int usage, final Buffer buffer) {
		super(GL_ARRAY_BUFFER, usage, buffer);
	}

	public BufferObjectArray(final int usage, final int dataSize) {
		super(GL_ARRAY_BUFFER, usage, dataSize);
	}

	public void enable() {
		if ( boundBOArray != ID ) {
			glBindBuffer(GL_ARRAY_BUFFER, ID);
			boundBOArray = ID;
		}
	}

	public void disable() {
		boArrayDisable();
	}

	public static void boArrayDisable() {
		if ( boundBOArray != 0 ) {
			glBindBuffer(GL_ARRAY_BUFFER, 0);
			boundBOArray = 0;
		}
	}

}