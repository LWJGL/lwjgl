package org.lwjgl.test.opengles.util;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

/**
 * Utility class that emulates immediate mode vertex data submission.
 * Can be used to create VBO data.
 */
public final class ImmediateModeBuffer {

	private FloatBuffer buffer;

	public ImmediateModeBuffer(final int startSize) {
		this.buffer = BufferUtils.createFloatBuffer(startSize);
	}

	private void checkSize(final int count) {
		while ( buffer.remaining() < count ) {
			final FloatBuffer newBuffer = BufferUtils.createFloatBuffer(buffer.capacity() << 1);
			buffer.flip();
			newBuffer.put(buffer);
			buffer = newBuffer;
		}
	}

	public FloatBuffer getBuffer() {
		buffer.flip();
		return buffer;
	}

	public void glVertex2f(final float x, final float y) {
		checkSize(2);
		buffer.put(x).put(y);
	}

	public void glVertex3f(final float x, final float y, final float z) {
		checkSize(3);
		buffer.put(x).put(y).put(z);
	}

	public void glVertex4f(final float x, final float y, final float z, final float w) {
		checkSize(4);
		buffer.put(x).put(y).put(z).put(w);
	}

	public void glNormal3f(final float x, final float y, final float z) {
		checkSize(3);
		buffer.put(x).put(y).put(z);
	}

	public void glTexCoord2f(final float s, final float t) {
		checkSize(2);
		buffer.put(s).put(t);
	}

}