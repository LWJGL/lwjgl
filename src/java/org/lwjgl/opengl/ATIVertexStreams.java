/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ATIVertexStreams {
	public static final int GL_VERTEX_STREAM7_ATI = 0x8774;
	public static final int GL_VERTEX_STREAM6_ATI = 0x8773;
	public static final int GL_VERTEX_STREAM5_ATI = 0x8772;
	public static final int GL_VERTEX_STREAM4_ATI = 0x8771;
	public static final int GL_VERTEX_STREAM3_ATI = 0x8770;
	public static final int GL_VERTEX_STREAM2_ATI = 0x876f;
	public static final int GL_VERTEX_STREAM1_ATI = 0x876e;
	public static final int GL_VERTEX_STREAM0_ATI = 0x876d;
	public static final int GL_VERTEX_SOURCE_ATI = 0x876c;
	public static final int GL_MAX_VERTEX_STREAMS_ATI = 0x876b;

	private ATIVertexStreams() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glVertexBlendEnviATI(int pname, int param);

	public static native void glVertexBlendEnvfATI(int pname, float param);

	public static native void glClientActiveVertexStreamATI(int stream);

	public static native void glNormalStream3sATI(int stream, short x, short y, short z);

	public static native void glNormalStream3iATI(int stream, int x, int y, int z);

	public static native void glNormalStream3fATI(int stream, float x, float y, float z);

	public static native void glNormalStream3bATI(int stream, byte x, byte y, byte z);

	public static native void glVertexStream4sATI(int stream, short x, short y, short z, short w);

	public static native void glVertexStream4iATI(int stream, int x, int y, int z, int w);

	public static native void glVertexStream4fATI(int stream, float x, float y, float z, float w);

	public static native void glVertexStream3sATI(int stream, short x, short y, short z);

	public static native void glVertexStream3iATI(int stream, int x, int y, int z);

	public static native void glVertexStream3fATI(int stream, float x, float y, float z);

	public static native void glVertexStream2sATI(int stream, short x, short y);

	public static native void glVertexStream2iATI(int stream, int x, int y);

	public static native void glVertexStream2fATI(int stream, float x, float y);
}
