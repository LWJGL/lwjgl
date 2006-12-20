/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ATIMapObjectBuffer {

	private ATIMapObjectBuffer() {
	}


	/**
	 * glMapObjectBufferATI maps a gl object buffer to a ByteBuffer. The oldBuffer argument can be
	 * null, in which case a new ByteBuffer will be created, pointing to the returned memory. If
	 * oldBuffer is non-null, it will be returned if it points to the same mapped memory, otherwise a
	 * new ByteBuffer is created.
	 * @param result_size   The size of the buffer area.
	 * @param old_buffer    A ByteBuffer. If this argument points to the same address and has the same capacity as the new mapping,
	 *                      it will be returned and no new buffer will be created.
	 * @return A ByteBuffer representing the mapped object buffer memory.
	 */
	public static java.nio.ByteBuffer glMapObjectBufferATI(int buffer, long result_size, java.nio.ByteBuffer old_buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_map_object_buffer_glMapObjectBufferATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		if (old_buffer != null)
			BufferChecks.checkDirect(old_buffer);
		java.nio.ByteBuffer __result = nglMapObjectBufferATI(buffer, result_size, old_buffer, function_pointer);
		return __result;
	}
	private static native java.nio.ByteBuffer nglMapObjectBufferATI(int buffer, long result_size, java.nio.ByteBuffer old_buffer, long function_pointer);

	public static void glUnmapObjectBufferATI(int buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_map_object_buffer_glUnmapObjectBufferATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglUnmapObjectBufferATI(buffer, function_pointer);
	}
	private static native void nglUnmapObjectBufferATI(int buffer, long function_pointer);
}
