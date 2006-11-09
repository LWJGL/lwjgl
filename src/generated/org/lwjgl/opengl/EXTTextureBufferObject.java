/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTTextureBufferObject {
	/**
	 * Accepted by the &lt;target&gt; parameter of BindBuffer, BufferData,
	 * BufferSubData, MapBuffer, BindTexture, UnmapBuffer, GetBufferSubData,
	 * GetBufferParameteriv, GetBufferPointerv, and TexBufferEXT, and
	 * the &lt;pname&gt; parameter of GetBooleanv, GetDoublev, GetFloatv, and
	 * GetIntegerv:
	 */
	public static final int GL_TEXTURE_BUFFER_EXT = 0x8c2a;
	/**
	 * Accepted by the &lt;pname&gt; parameters of GetBooleanv, GetDoublev,
	 * GetFloatv, and GetIntegerv:
	 */
	public static final int GL_MAX_TEXTURE_BUFFER_SIZE_EXT = 0x8c2b;
	public static final int GL_TEXTURE_BINDING_BUFFER_EXT = 0x8c2c;
	public static final int GL_TEXTURE_BUFFER_DATA_STORE_BINDING_EXT = 0x8c2d;
	public static final int GL_TEXTURE_BUFFER_FORMAT_EXT = 0x8c2e;

	private EXTTextureBufferObject() {
	}


	public static void glTexBufferEXT(int target, int internalformat, int buffer) {
		long function_pointer = GLContext.getCapabilities().EXT_texture_buffer_object_glTexBufferEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglTexBufferEXT(target, internalformat, buffer, function_pointer);
	}
	private static native void nglTexBufferEXT(int target, int internalformat, int buffer, long function_pointer);
}
