/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ATIVertexArrayObject {
	public static final int GL_ARRAY_OBJECT_OFFSET_ATI = 0x8767;
	public static final int GL_ARRAY_OBJECT_BUFFER_ATI = 0x8766;
	public static final int GL_OBJECT_BUFFER_USAGE_ATI = 0x8765;
	public static final int GL_OBJECT_BUFFER_SIZE_ATI = 0x8764;
	public static final int GL_DISCARD_ATI = 0x8763;
	public static final int GL_PRESERVE_ATI = 0x8762;
	public static final int GL_DYNAMIC_ATI = 0x8761;
	public static final int GL_STATIC_ATI = 0x8760;

	private ATIVertexArrayObject() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static void glGetVariantArrayObjectATI(int id, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetVariantArrayObjectivATI(id, pname, params, params.position());
	}
	private static native void nglGetVariantArrayObjectivATI(int id, int pname, IntBuffer params, int params_position);

	public static void glGetVariantArrayObjectATI(int id, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetVariantArrayObjectfvATI(id, pname, params, params.position());
	}
	private static native void nglGetVariantArrayObjectfvATI(int id, int pname, FloatBuffer params, int params_position);

	public static native void glVariantArrayObjectATI(int id, int type, int stride, int buffer, int offset);

	public static void glGetArrayObjectATI(int array, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetArrayObjectivATI(array, pname, params, params.position());
	}
	private static native void nglGetArrayObjectivATI(int array, int pname, IntBuffer params, int params_position);

	public static void glGetArrayObjectATI(int array, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetArrayObjectfvATI(array, pname, params, params.position());
	}
	private static native void nglGetArrayObjectfvATI(int array, int pname, FloatBuffer params, int params_position);

	public static native void glArrayObjectATI(int array, int size, int type, int stride, int buffer, int offset);

	public static native void glFreeObjectBufferATI(int buffer);

	public static void glGetObjectBufferATI(int buffer, int pname, IntBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetObjectBufferivATI(buffer, pname, params, params.position());
	}
	private static native void nglGetObjectBufferivATI(int buffer, int pname, IntBuffer params, int params_position);

	public static void glGetObjectBufferATI(int buffer, int pname, FloatBuffer params) {
		BufferChecks.checkDirect(params);
		nglGetObjectBufferfvATI(buffer, pname, params, params.position());
	}
	private static native void nglGetObjectBufferfvATI(int buffer, int pname, FloatBuffer params, int params_position);

	public static void glUpdateObjectBufferATI(int buffer, int offset, IntBuffer pPointer, int preserve) {
		BufferChecks.checkDirect(pPointer);
		nglUpdateObjectBufferATI(buffer, offset, (pPointer.remaining() << 2), pPointer, pPointer.position() << 2, preserve);
	}
	public static void glUpdateObjectBufferATI(int buffer, int offset, FloatBuffer pPointer, int preserve) {
		BufferChecks.checkDirect(pPointer);
		nglUpdateObjectBufferATI(buffer, offset, (pPointer.remaining() << 2), pPointer, pPointer.position() << 2, preserve);
	}
	public static void glUpdateObjectBufferATI(int buffer, int offset, ShortBuffer pPointer, int preserve) {
		BufferChecks.checkDirect(pPointer);
		nglUpdateObjectBufferATI(buffer, offset, (pPointer.remaining() << 1), pPointer, pPointer.position() << 1, preserve);
	}
	public static void glUpdateObjectBufferATI(int buffer, int offset, ByteBuffer pPointer, int preserve) {
		BufferChecks.checkDirect(pPointer);
		nglUpdateObjectBufferATI(buffer, offset, (pPointer.remaining()), pPointer, pPointer.position(), preserve);
	}
	private static native void nglUpdateObjectBufferATI(int buffer, int offset, int size, Buffer pPointer, int pPointer_position, int preserve);

	public static native boolean glIsObjectBufferATI(int buffer);

	public static int glNewObjectBufferATI(int size, int usage) {
		int __result = nglNewObjectBufferATI(size, null, 0, usage);
		return __result;
	}
	public static int glNewObjectBufferATI(IntBuffer pPointer, int usage) {
		BufferChecks.checkDirect(pPointer);
		int __result = nglNewObjectBufferATI((pPointer.remaining() << 2), pPointer, pPointer.position() << 2, usage);
		return __result;
	}
	public static int glNewObjectBufferATI(FloatBuffer pPointer, int usage) {
		BufferChecks.checkDirect(pPointer);
		int __result = nglNewObjectBufferATI((pPointer.remaining() << 2), pPointer, pPointer.position() << 2, usage);
		return __result;
	}
	public static int glNewObjectBufferATI(ShortBuffer pPointer, int usage) {
		BufferChecks.checkDirect(pPointer);
		int __result = nglNewObjectBufferATI((pPointer.remaining() << 1), pPointer, pPointer.position() << 1, usage);
		return __result;
	}
	public static int glNewObjectBufferATI(ByteBuffer pPointer, int usage) {
		BufferChecks.checkDirect(pPointer);
		int __result = nglNewObjectBufferATI((pPointer.remaining()), pPointer, pPointer.position(), usage);
		return __result;
	}
	private static native int nglNewObjectBufferATI(int size, Buffer pPointer, int pPointer_position, int usage);
}
