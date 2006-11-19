/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class ARBDrawBuffers {
	/**
	 * Accepted by the &lt;pname&gt; parameters of GetIntegerv, GetFloatv,
	 * and GetDoublev:
	 */
	public static final int GL_MAX_DRAW_BUFFERS_ARB = 0x8824;
	public static final int GL_DRAW_BUFFER0_ARB = 0x8825;
	public static final int GL_DRAW_BUFFER1_ARB = 0x8826;
	public static final int GL_DRAW_BUFFER2_ARB = 0x8827;
	public static final int GL_DRAW_BUFFER3_ARB = 0x8828;
	public static final int GL_DRAW_BUFFER4_ARB = 0x8829;
	public static final int GL_DRAW_BUFFER5_ARB = 0x882a;
	public static final int GL_DRAW_BUFFER6_ARB = 0x882b;
	public static final int GL_DRAW_BUFFER7_ARB = 0x882c;
	public static final int GL_DRAW_BUFFER8_ARB = 0x882d;
	public static final int GL_DRAW_BUFFER9_ARB = 0x882e;
	public static final int GL_DRAW_BUFFER10_ARB = 0x882f;
	public static final int GL_DRAW_BUFFER11_ARB = 0x8830;
	public static final int GL_DRAW_BUFFER12_ARB = 0x8831;
	public static final int GL_DRAW_BUFFER13_ARB = 0x8832;
	public static final int GL_DRAW_BUFFER14_ARB = 0x8833;
	public static final int GL_DRAW_BUFFER15_ARB = 0x8834;

	private ARBDrawBuffers() {
	}


	public static void glDrawBuffersARB(IntBuffer buffers) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_draw_buffers_glDrawBuffersARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(buffers);
		nglDrawBuffersARB((buffers.remaining()), buffers, buffers.position(), function_pointer);
	}
	private static native void nglDrawBuffersARB(int size, IntBuffer buffers, int buffers_position, long function_pointer);
}
