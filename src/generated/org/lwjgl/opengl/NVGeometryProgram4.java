/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVGeometryProgram4 {
	/**
	 * Accepted by the &lt;cap&gt; parameter of Disable, Enable, and IsEnabled, and by
	 * the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev:
	 */
	public static final int GL_GEOMETRY_PROGRAM_NV = 0x8c26;
	/**
	 *Accepted by the &lt;pname&gt; parameter of GetProgramivARB: 
	 */
	public static final int GL_MAX_PROGRAM_OUTPUT_VERTICES_NV = 0x8c27;
	public static final int GL_MAX_PROGRAM_TOTAL_OUTPUT_COMPONENTS_NV = 0x8c28;

	private NVGeometryProgram4() {
	}


	public static void glProgramVertexLimitNV(int target, int limit) {
		long function_pointer = GLContext.getCapabilities().NV_geometry_program4_glProgramVertexLimitNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglProgramVertexLimitNV(target, limit, function_pointer);
	}
	private static native void nglProgramVertexLimitNV(int target, int limit, long function_pointer);

	public static void glFramebufferTextureEXT(int target, int attachment, int texture, int level) {
		long function_pointer = GLContext.getCapabilities().NV_geometry_program4_glFramebufferTextureEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFramebufferTextureEXT(target, attachment, texture, level, function_pointer);
	}
	private static native void nglFramebufferTextureEXT(int target, int attachment, int texture, int level, long function_pointer);

	public static void glFramebufferTextureLayerEXT(int target, int attachment, int texture, int level, int layer) {
		long function_pointer = GLContext.getCapabilities().NV_geometry_program4_glFramebufferTextureLayerEXT_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFramebufferTextureLayerEXT(target, attachment, texture, level, layer, function_pointer);
	}
	private static native void nglFramebufferTextureLayerEXT(int target, int attachment, int texture, int level, int layer, long function_pointer);
}
