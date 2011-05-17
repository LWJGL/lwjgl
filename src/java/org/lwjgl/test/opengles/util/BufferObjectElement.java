package org.lwjgl.test.opengles.util;

import java.nio.Buffer;

import static org.lwjgl.opengles.GLES20.*;

public final class BufferObjectElement extends BufferObject {

	private static int boundBOElementArray;

	public BufferObjectElement(final int usage) {
		super(GL_ELEMENT_ARRAY_BUFFER, usage);
	}

	public BufferObjectElement(final int usage, final Buffer buffer) {
		super(GL_ELEMENT_ARRAY_BUFFER, usage, buffer);
	}

	public BufferObjectElement(final int usage, final int dataSize) {
		super(GL_ELEMENT_ARRAY_BUFFER, usage, dataSize);
	}

	public void enable() {
		if ( boundBOElementArray != ID ) {
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ID);
			boundBOElementArray = ID;
		}
	}

	public void disable() {
		boElementArrayDisable();
	}

	public static void boElementArrayDisable() {
		if ( boundBOElementArray != 0 ) {
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
			boundBOElementArray = 0;
		}
	}

}