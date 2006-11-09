/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class EXTTextureArray {
	/**
	 * Accepted by the &lt;target&gt; parameter of TexParameteri, TexParameteriv,
	 * TexParameterf, TexParameterfv, and BindTexture:
	 */
	public static final int GL_TEXTURE_1D_ARRAY_EXT = 0x8c18;
	public static final int GL_TEXTURE_2D_ARRAY_EXT = 0x8c1a;
	/**
	 * Accepted by the &lt;target&gt; parameter of TexImage3D, TexSubImage3D,
	 * CopyTexSubImage3D, CompressedTexImage3D, and CompressedTexSubImage3D:
	 */
	public static final int GL_PROXY_TEXTURE_2D_ARRAY_EXT = 0x8c1b;
	/**
	 * Accepted by the &lt;target&gt; parameter of TexImage2D, TexSubImage2D,
	 * CopyTexImage2D, CopyTexSubImage2D, CompressedTexImage2D, and
	 * CompressedTexSubImage2D:
	 */
	public static final int GL_PROXY_TEXTURE_1D_ARRAY_EXT = 0x8c19;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetDoublev, GetIntegerv
	 * and GetFloatv:
	 */
	public static final int GL_TEXTURE_BINDING_1D_ARRAY_EXT = 0x8c1c;
	public static final int GL_TEXTURE_BINDING_2D_ARRAY_EXT = 0x8c1d;
	public static final int GL_MAX_ARRAY_TEXTURE_LAYERS_EXT = 0x88ff;
	/**
	 * Accepted by the &lt;param&gt; parameter of TexParameterf, TexParameteri,
	 * TexParameterfv, and TexParameteriv when the &lt;pname&gt; parameter is
	 * TEXTURE_COMPARE_MODE_ARB:
	 */
	public static final int GL_COMPARE_REF_DEPTH_TO_TEXTURE_EXT = 0x884e;
	/**
	 * Accepted by the &lt;pname&gt; parameter of
	 * GetFramebufferAttachmentParameterivEXT:
	 */
	public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER_EXT = 0x8cd4;
	/**
	 *Returned by the &lt;type&gt; parameter of GetActiveUniform: 
	 */
	public static final int GL_SAMPLER_1D_ARRAY_EXT = 0x8dc0;
	public static final int GL_SAMPLER_2D_ARRAY_EXT = 0x8dc1;
	public static final int GL_SAMPLER_1D_ARRAY_SHADOW_EXT = 0x8dc3;
	public static final int GL_SAMPLER_2D_ARRAY_SHADOW_EXT = 0x8dc4;

	private EXTTextureArray() {
	}


	public static void glFramebufferTextureLayerEXT(int target, int attachment, int texture, int level, int layer) {
		long function_pointer = GLContext.getCapabilities().EXT_texture_array_glFramebufferTextureLayerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFramebufferTextureLayerEXT(target, attachment, texture, level, layer, function_pointer);
	}
	private static native void nglFramebufferTextureLayerEXT(int target, int attachment, int texture, int level, int layer, long function_pointer);
}
