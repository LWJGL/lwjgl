/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public class ARBProgram {
	/**
	 * Accepted by the &lt;format&gt; parameter of ProgramStringARB:
	 */
	public static final int GL_PROGRAM_FORMAT_ASCII_ARB = 0x8875;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetProgramivARB:
	 */
	public static final int GL_PROGRAM_LENGTH_ARB = 0x8627;
	public static final int GL_PROGRAM_FORMAT_ARB = 0x8876;
	public static final int GL_PROGRAM_BINDING_ARB = 0x8677;
	public static final int GL_PROGRAM_INSTRUCTIONS_ARB = 0x88a0;
	public static final int GL_MAX_PROGRAM_INSTRUCTIONS_ARB = 0x88a1;
	public static final int GL_PROGRAM_NATIVE_INSTRUCTIONS_ARB = 0x88a2;
	public static final int GL_MAX_PROGRAM_NATIVE_INSTRUCTIONS_ARB = 0x88a3;
	public static final int GL_PROGRAM_TEMPORARIES_ARB = 0x88a4;
	public static final int GL_MAX_PROGRAM_TEMPORARIES_ARB = 0x88a5;
	public static final int GL_PROGRAM_NATIVE_TEMPORARIES_ARB = 0x88a6;
	public static final int GL_MAX_PROGRAM_NATIVE_TEMPORARIES_ARB = 0x88a7;
	public static final int GL_PROGRAM_PARAMETERS_ARB = 0x88a8;
	public static final int GL_MAX_PROGRAM_PARAMETERS_ARB = 0x88a9;
	public static final int GL_PROGRAM_NATIVE_PARAMETERS_ARB = 0x88aa;
	public static final int GL_MAX_PROGRAM_NATIVE_PARAMETERS_ARB = 0x88ab;
	public static final int GL_PROGRAM_ATTRIBS_ARB = 0x88ac;
	public static final int GL_MAX_PROGRAM_ATTRIBS_ARB = 0x88ad;
	public static final int GL_PROGRAM_NATIVE_ATTRIBS_ARB = 0x88ae;
	public static final int GL_MAX_PROGRAM_NATIVE_ATTRIBS_ARB = 0x88af;
	public static final int GL_MAX_PROGRAM_LOCAL_PARAMETERS_ARB = 0x88b4;
	public static final int GL_MAX_PROGRAM_ENV_PARAMETERS_ARB = 0x88b5;
	public static final int GL_PROGRAM_UNDER_NATIVE_LIMITS_ARB = 0x88b6;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetProgramStringARB:
	 */
	public static final int GL_PROGRAM_STRING_ARB = 0x8628;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv,
	 * GetFloatv, and GetDoublev:
	 */
	public static final int GL_PROGRAM_ERROR_POSITION_ARB = 0x864b;
	public static final int GL_CURRENT_MATRIX_ARB = 0x8641;
	public static final int GL_TRANSPOSE_CURRENT_MATRIX_ARB = 0x88b7;
	public static final int GL_CURRENT_MATRIX_STACK_DEPTH_ARB = 0x8640;
	public static final int GL_MAX_PROGRAM_MATRICES_ARB = 0x862f;
	public static final int GL_MAX_PROGRAM_MATRIX_STACK_DEPTH_ARB = 0x862e;
	/**
	 * Accepted by the &lt;name&gt; parameter of GetString:
	 */
	public static final int GL_PROGRAM_ERROR_STRING_ARB = 0x8874;
	/**
	 * Accepted by the &lt;mode&gt; parameter of MatrixMode:
	 */
	public static final int GL_MATRIX0_ARB = 0x88c0;
	public static final int GL_MATRIX1_ARB = 0x88c1;
	public static final int GL_MATRIX2_ARB = 0x88c2;
	public static final int GL_MATRIX3_ARB = 0x88c3;
	public static final int GL_MATRIX4_ARB = 0x88c4;
	public static final int GL_MATRIX5_ARB = 0x88c5;
	public static final int GL_MATRIX6_ARB = 0x88c6;
	public static final int GL_MATRIX7_ARB = 0x88c7;
	public static final int GL_MATRIX8_ARB = 0x88c8;
	public static final int GL_MATRIX9_ARB = 0x88c9;
	public static final int GL_MATRIX10_ARB = 0x88ca;
	public static final int GL_MATRIX11_ARB = 0x88cb;
	public static final int GL_MATRIX12_ARB = 0x88cc;
	public static final int GL_MATRIX13_ARB = 0x88cd;
	public static final int GL_MATRIX14_ARB = 0x88ce;
	public static final int GL_MATRIX15_ARB = 0x88cf;
	public static final int GL_MATRIX16_ARB = 0x88d0;
	public static final int GL_MATRIX17_ARB = 0x88d1;
	public static final int GL_MATRIX18_ARB = 0x88d2;
	public static final int GL_MATRIX19_ARB = 0x88d3;
	public static final int GL_MATRIX20_ARB = 0x88d4;
	public static final int GL_MATRIX21_ARB = 0x88d5;
	public static final int GL_MATRIX22_ARB = 0x88d6;
	public static final int GL_MATRIX23_ARB = 0x88d7;
	public static final int GL_MATRIX24_ARB = 0x88d8;
	public static final int GL_MATRIX25_ARB = 0x88d9;
	public static final int GL_MATRIX26_ARB = 0x88da;
	public static final int GL_MATRIX27_ARB = 0x88db;
	public static final int GL_MATRIX28_ARB = 0x88dc;
	public static final int GL_MATRIX29_ARB = 0x88dd;
	public static final int GL_MATRIX30_ARB = 0x88de;
	public static final int GL_MATRIX31_ARB = 0x88df;


	public static void glProgramStringARB(int target, int format, ByteBuffer string) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glProgramStringARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(string);
		nglProgramStringARB(target, format, (string.remaining()), string, string.position(), function_pointer);
	}
	private static native void nglProgramStringARB(int target, int format, int length, Buffer string, int string_position, long function_pointer);

	public static void glBindProgramARB(int target, int program) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glBindProgramARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBindProgramARB(target, program, function_pointer);
	}
	private static native void nglBindProgramARB(int target, int program, long function_pointer);

	public static void glDeleteProgramsARB(IntBuffer programs) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glDeleteProgramsARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(programs);
		nglDeleteProgramsARB((programs.remaining()), programs, programs.position(), function_pointer);
	}
	private static native void nglDeleteProgramsARB(int n, IntBuffer programs, int programs_position, long function_pointer);

	public static void glGenProgramsARB(IntBuffer programs) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glGenProgramsARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(programs);
		nglGenProgramsARB((programs.remaining()), programs, programs.position(), function_pointer);
	}
	private static native void nglGenProgramsARB(int n, IntBuffer programs, int programs_position, long function_pointer);

	public static void glProgramEnvParameter4fARB(int target, int index, float x, float y, float z, float w) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glProgramEnvParameter4fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglProgramEnvParameter4fARB(target, index, x, y, z, w, function_pointer);
	}
	private static native void nglProgramEnvParameter4fARB(int target, int index, float x, float y, float z, float w, long function_pointer);

	public static void glProgramEnvParameter4dARB(int target, int index, double x, double y, double z, double w) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glProgramEnvParameter4dARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglProgramEnvParameter4dARB(target, index, x, y, z, w, function_pointer);
	}
	private static native void nglProgramEnvParameter4dARB(int target, int index, double x, double y, double z, double w, long function_pointer);

	public static void glProgramEnvParameter4ARB(int target, int index, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glProgramEnvParameter4fvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglProgramEnvParameter4fvARB(target, index, params, params.position(), function_pointer);
	}
	private static native void nglProgramEnvParameter4fvARB(int target, int index, FloatBuffer params, int params_position, long function_pointer);

	public static void glProgramEnvParameter4ARB(int target, int index, DoubleBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glProgramEnvParameter4dvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglProgramEnvParameter4dvARB(target, index, params, params.position(), function_pointer);
	}
	private static native void nglProgramEnvParameter4dvARB(int target, int index, DoubleBuffer params, int params_position, long function_pointer);

	public static void glProgramLocalParameter4fARB(int target, int index, float x, float y, float z, float w) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glProgramLocalParameter4fARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglProgramLocalParameter4fARB(target, index, x, y, z, w, function_pointer);
	}
	private static native void nglProgramLocalParameter4fARB(int target, int index, float x, float y, float z, float w, long function_pointer);

	public static void glProgramLocalParameter4dARB(int target, int index, double x, double y, double z, double w) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glProgramLocalParameter4dARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglProgramLocalParameter4dARB(target, index, x, y, z, w, function_pointer);
	}
	private static native void nglProgramLocalParameter4dARB(int target, int index, double x, double y, double z, double w, long function_pointer);

	public static void glProgramLocalParameter4ARB(int target, int index, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glProgramLocalParameter4fvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglProgramLocalParameter4fvARB(target, index, params, params.position(), function_pointer);
	}
	private static native void nglProgramLocalParameter4fvARB(int target, int index, FloatBuffer params, int params_position, long function_pointer);

	public static void glProgramLocalParameter4ARB(int target, int index, DoubleBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glProgramLocalParameter4dvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglProgramLocalParameter4dvARB(target, index, params, params.position(), function_pointer);
	}
	private static native void nglProgramLocalParameter4dvARB(int target, int index, DoubleBuffer params, int params_position, long function_pointer);

	public static void glGetProgramEnvParameterARB(int target, int index, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glGetProgramEnvParameterfvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetProgramEnvParameterfvARB(target, index, params, params.position(), function_pointer);
	}
	private static native void nglGetProgramEnvParameterfvARB(int target, int index, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetProgramEnvParameterARB(int target, int index, DoubleBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glGetProgramEnvParameterdvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetProgramEnvParameterdvARB(target, index, params, params.position(), function_pointer);
	}
	private static native void nglGetProgramEnvParameterdvARB(int target, int index, DoubleBuffer params, int params_position, long function_pointer);

	public static void glGetProgramLocalParameterARB(int target, int index, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glGetProgramLocalParameterfvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetProgramLocalParameterfvARB(target, index, params, params.position(), function_pointer);
	}
	private static native void nglGetProgramLocalParameterfvARB(int target, int index, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetProgramLocalParameterARB(int target, int index, DoubleBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glGetProgramLocalParameterdvARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetProgramLocalParameterdvARB(target, index, params, params.position(), function_pointer);
	}
	private static native void nglGetProgramLocalParameterdvARB(int target, int index, DoubleBuffer params, int params_position, long function_pointer);

	public static void glGetProgramARB(int target, int parameterName, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glGetProgramivARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetProgramivARB(target, parameterName, params, params.position(), function_pointer);
	}
	private static native void nglGetProgramivARB(int target, int parameterName, IntBuffer params, int params_position, long function_pointer);

	public static void glGetProgramStringARB(int target, int parameterName, ByteBuffer paramString) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glGetProgramStringARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(paramString);
		nglGetProgramStringARB(target, parameterName, paramString, paramString.position(), function_pointer);
	}
	private static native void nglGetProgramStringARB(int target, int parameterName, Buffer paramString, int paramString_position, long function_pointer);

	public static boolean glIsProgramARB(int program) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.ARB_program_glIsProgramARB_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		boolean __result = nglIsProgramARB(program, function_pointer);
		return __result;
	}
	private static native boolean nglIsProgramARB(int program, long function_pointer);
}
