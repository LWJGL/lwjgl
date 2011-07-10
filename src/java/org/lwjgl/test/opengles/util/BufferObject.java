package org.lwjgl.test.opengles.util;

import java.nio.*;

import static org.lwjgl.opengles.GLES20.*;
import static org.lwjgl.opengles.OESMapbuffer.*;

abstract class BufferObject implements GLObject {

	protected final int ID;

	protected int target;
	protected int usage;

	/** The BufferObject data size in bytes. */
	private int size;

	protected BufferObject(final int type, final int usage) {
		this.ID = glGenBuffers();
		this.target = type;
		this.usage = usage;
	}

	protected BufferObject(final int type, final int usage, final Buffer buffer) {
		this(type, usage);
		setData(buffer);
	}

	protected BufferObject(final int type, final int usage, final int dataSize) {
		this(type, usage);
		setData(dataSize);
	}

	public final int getID() {
		return ID;
	}

	public void destroy() {
		glDeleteBuffers(ID);
	}

	public final int getTarget() {
		return target;
	}

	public final int getUsage() {
		return usage;
	}

	public final int getSize() {
		return size;
	}

	public abstract void enable();

	public abstract void disable();

	public final void setData(final Buffer buffer) {
		enable();

		if ( buffer instanceof ByteBuffer ) {
			glBufferData(target, (ByteBuffer)buffer, usage);
			size = buffer.remaining();
		} else if ( buffer instanceof ShortBuffer ) {
			glBufferData(target, (ShortBuffer)buffer, usage);
			size = buffer.remaining() << 1;
		} else if ( buffer instanceof IntBuffer ) {
			glBufferData(target, (IntBuffer)buffer, usage);
			size = buffer.remaining() << 2;
		} else if ( buffer instanceof FloatBuffer ) {
			glBufferData(target, (FloatBuffer)buffer, usage);
			size = buffer.remaining() << 2;
		}

		disable();
	}

	public final void setData(final int dataSize) {
		enable();

		glBufferData(target, dataSize, usage);
		size = dataSize;

		disable();
	}

	public final ByteBuffer map(final int access, final ByteBuffer oldBuffer) {
		return glMapBufferOES(target, access, oldBuffer);
	}

	public final ByteBuffer map(final int access, final int length, final ByteBuffer oldBuffer) {
		return glMapBufferOES(target, access, length, oldBuffer);
	}

	public final boolean unmap() {
		return glUnmapBufferOES(target);
	}

}