/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ATIPnTriangles {
	public static final int GL_PN_TRIANGLES_ATI = 0x87f0;
	public static final int GL_MAX_PN_TRIANGLES_TESSELATION_LEVEL_ATI = 0x87f1;
	public static final int GL_PN_TRIANGLES_POINT_MODE_ATI = 0x87f2;
	public static final int GL_PN_TRIANGLES_NORMAL_MODE_ATI = 0x87f3;
	public static final int GL_PN_TRIANGLES_TESSELATION_LEVEL_ATI = 0x87f4;
	public static final int GL_PN_TRIANGLES_POINT_MODE_LINEAR_ATI = 0x87f5;
	public static final int GL_PN_TRIANGLES_POINT_MODE_CUBIC_ATI = 0x87f6;
	public static final int GL_PN_TRIANGLES_NORMAL_MODE_LINEAR_ATI = 0x87f7;
	public static final int GL_PN_TRIANGLES_NORMAL_MODE_QUADRATIC_ATI = 0x87f8;

	private ATIPnTriangles() {
	}


	public static void glPNTrianglesfATI(int pname, float param) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_pn_triangles_glPNTrianglesfATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglPNTrianglesfATI(pname, param, function_pointer);
	}
	private static native void nglPNTrianglesfATI(int pname, float param, long function_pointer);

	public static void glPNTrianglesiATI(int pname, int param) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_pn_triangles_glPNTrianglesiATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglPNTrianglesiATI(pname, param, function_pointer);
	}
	private static native void nglPNTrianglesiATI(int pname, int param, long function_pointer);
}
