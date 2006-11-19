/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ATIDrawBuffers {
	/**
	 * Accepted by the &lt;pname&gt; parameters of GetIntegerv, GetFloatv,
	 * and GetDoublev:
	 */
	public static final int GL_MAX_DRAW_BUFFERS_ATI = 0x8824;
	public static final int GL_DRAW_BUFFER0_ATI = 0x8825;
	public static final int GL_DRAW_BUFFER1_ATI = 0x8826;
	public static final int GL_DRAW_BUFFER2_ATI = 0x8827;
	public static final int GL_DRAW_BUFFER3_ATI = 0x8828;
	public static final int GL_DRAW_BUFFER4_ATI = 0x8829;
	public static final int GL_DRAW_BUFFER5_ATI = 0x882a;
	public static final int GL_DRAW_BUFFER6_ATI = 0x882b;
	public static final int GL_DRAW_BUFFER7_ATI = 0x882c;
	public static final int GL_DRAW_BUFFER8_ATI = 0x882d;
	public static final int GL_DRAW_BUFFER9_ATI = 0x882e;
	public static final int GL_DRAW_BUFFER10_ATI = 0x882f;
	public static final int GL_DRAW_BUFFER11_ATI = 0x8830;
	public static final int GL_DRAW_BUFFER12_ATI = 0x8831;
	public static final int GL_DRAW_BUFFER13_ATI = 0x8832;
	public static final int GL_DRAW_BUFFER14_ATI = 0x8833;
	public static final int GL_DRAW_BUFFER15_ATI = 0x8834;

	private ATIDrawBuffers() {
	}


	public static void glDrawBuffersATI(IntBuffer buffers) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ATI_draw_buffers_glDrawBuffersATI_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(buffers);
		nglDrawBuffersATI((buffers.remaining()), buffers, buffers.position(), function_pointer);
	}
	private static native void nglDrawBuffersATI(int size, IntBuffer buffers, int buffers_position, long function_pointer);
}
