/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVRegisterCombiners {
	public static final int GL_REGISTER_COMBINERS_NV = 0x8522;
	public static final int GL_COMBINER0_NV = 0x8550;
	public static final int GL_COMBINER1_NV = 0x8551;
	public static final int GL_COMBINER2_NV = 0x8552;
	public static final int GL_COMBINER3_NV = 0x8553;
	public static final int GL_COMBINER4_NV = 0x8554;
	public static final int GL_COMBINER5_NV = 0x8555;
	public static final int GL_COMBINER6_NV = 0x8556;
	public static final int GL_COMBINER7_NV = 0x8557;
	public static final int GL_VARIABLE_A_NV = 0x8523;
	public static final int GL_VARIABLE_B_NV = 0x8524;
	public static final int GL_VARIABLE_C_NV = 0x8525;
	public static final int GL_VARIABLE_D_NV = 0x8526;
	public static final int GL_VARIABLE_E_NV = 0x8527;
	public static final int GL_VARIABLE_F_NV = 0x8528;
	public static final int GL_VARIABLE_G_NV = 0x8529;
	public static final int GL_CONSTANT_COLOR0_NV = 0x852a;
	public static final int GL_CONSTANT_COLOR1_NV = 0x852b;
	public static final int GL_PRIMARY_COLOR_NV = 0x852c;
	public static final int GL_SECONDARY_COLOR_NV = 0x852d;
	public static final int GL_SPARE0_NV = 0x852e;
	public static final int GL_SPARE1_NV = 0x852f;
	public static final int GL_UNSIGNED_IDENTITY_NV = 0x8536;
	public static final int GL_UNSIGNED_INVERT_NV = 0x8537;
	public static final int GL_EXPAND_NORMAL_NV = 0x8538;
	public static final int GL_EXPAND_NEGATE_NV = 0x8539;
	public static final int GL_HALF_BIAS_NORMAL_NV = 0x853a;
	public static final int GL_HALF_BIAS_NEGATE_NV = 0x853b;
	public static final int GL_SIGNED_IDENTITY_NV = 0x853c;
	public static final int GL_SIGNED_NEGATE_NV = 0x853d;
	public static final int GL_E_TIMES_F_NV = 0x8531;
	public static final int GL_SPARE0_PLUS_SECONDARY_COLOR_NV = 0x8532;
	public static final int GL_SCALE_BY_TWO_NV = 0x853e;
	public static final int GL_SCALE_BY_FOUR_NV = 0x853f;
	public static final int GL_SCALE_BY_ONE_HALF_NV = 0x8540;
	public static final int GL_BIAS_BY_NEGATIVE_ONE_HALF_NV = 0x8541;
	public static final int GL_DISCARD_NV = 0x8530;
	public static final int GL_COMBINER_INPUT_NV = 0x8542;
	public static final int GL_COMBINER_MAPPING_NV = 0x8543;
	public static final int GL_COMBINER_COMPONENT_USAGE_NV = 0x8544;
	public static final int GL_COMBINER_AB_DOT_PRODUCT_NV = 0x8545;
	public static final int GL_COMBINER_CD_DOT_PRODUCT_NV = 0x8546;
	public static final int GL_COMBINER_MUX_SUM_NV = 0x8547;
	public static final int GL_COMBINER_SCALE_NV = 0x8548;
	public static final int GL_COMBINER_BIAS_NV = 0x8549;
	public static final int GL_COMBINER_AB_OUTPUT_NV = 0x854a;
	public static final int GL_COMBINER_CD_OUTPUT_NV = 0x854b;
	public static final int GL_COMBINER_SUM_OUTPUT_NV = 0x854c;
	public static final int GL_NUM_GENERAL_COMBINERS_NV = 0x854e;
	public static final int GL_COLOR_SUM_CLAMP_NV = 0x854f;
	public static final int GL_MAX_GENERAL_COMBINERS_NV = 0x854d;

	private NVRegisterCombiners() {
	}


	public static void glCombinerParameterfNV(int pname, float param) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_register_combiners_glCombinerParameterfNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglCombinerParameterfNV(pname, param, function_pointer);
	}
	private static native void nglCombinerParameterfNV(int pname, float param, long function_pointer);

	public static void glCombinerParameterNV(int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_register_combiners_glCombinerParameterfvNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglCombinerParameterfvNV(pname, params, params.position(), function_pointer);
	}
	private static native void nglCombinerParameterfvNV(int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glCombinerParameteriNV(int pname, int param) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_register_combiners_glCombinerParameteriNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglCombinerParameteriNV(pname, param, function_pointer);
	}
	private static native void nglCombinerParameteriNV(int pname, int param, long function_pointer);

	public static void glCombinerParameterNV(int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_register_combiners_glCombinerParameterivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglCombinerParameterivNV(pname, params, params.position(), function_pointer);
	}
	private static native void nglCombinerParameterivNV(int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glCombinerInputNV(int stage, int portion, int variable, int input, int mapping, int componentUsage) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_register_combiners_glCombinerInputNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglCombinerInputNV(stage, portion, variable, input, mapping, componentUsage, function_pointer);
	}
	private static native void nglCombinerInputNV(int stage, int portion, int variable, int input, int mapping, int componentUsage, long function_pointer);

	public static void glCombinerOutputNV(int stage, int portion, int abOutput, int cdOutput, int sumOutput, int scale, int bias, boolean abDotProduct, boolean cdDotProduct, boolean muxSum) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_register_combiners_glCombinerOutputNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglCombinerOutputNV(stage, portion, abOutput, cdOutput, sumOutput, scale, bias, abDotProduct, cdDotProduct, muxSum, function_pointer);
	}
	private static native void nglCombinerOutputNV(int stage, int portion, int abOutput, int cdOutput, int sumOutput, int scale, int bias, boolean abDotProduct, boolean cdDotProduct, boolean muxSum, long function_pointer);

	public static void glFinalCombinerInputNV(int variable, int input, int mapping, int componentUsage) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_register_combiners_glFinalCombinerInputNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglFinalCombinerInputNV(variable, input, mapping, componentUsage, function_pointer);
	}
	private static native void nglFinalCombinerInputNV(int variable, int input, int mapping, int componentUsage, long function_pointer);

	public static void glGetCombinerInputParameterNV(int stage, int portion, int variable, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_register_combiners_glGetCombinerInputParameterfvNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetCombinerInputParameterfvNV(stage, portion, variable, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetCombinerInputParameterfvNV(int stage, int portion, int variable, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetCombinerInputParameterNV(int stage, int portion, int variable, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_register_combiners_glGetCombinerInputParameterivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetCombinerInputParameterivNV(stage, portion, variable, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetCombinerInputParameterivNV(int stage, int portion, int variable, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetCombinerOutputParameterNV(int stage, int portion, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_register_combiners_glGetCombinerOutputParameterfvNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetCombinerOutputParameterfvNV(stage, portion, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetCombinerOutputParameterfvNV(int stage, int portion, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetCombinerOutputParameterNV(int stage, int portion, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_register_combiners_glGetCombinerOutputParameterivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetCombinerOutputParameterivNV(stage, portion, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetCombinerOutputParameterivNV(int stage, int portion, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetFinalCombinerInputParameterNV(int variable, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_register_combiners_glGetFinalCombinerInputParameterfvNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetFinalCombinerInputParameterfvNV(variable, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetFinalCombinerInputParameterfvNV(int variable, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetFinalCombinerInputParameterNV(int variable, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_register_combiners_glGetFinalCombinerInputParameterivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetFinalCombinerInputParameterivNV(variable, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetFinalCombinerInputParameterivNV(int variable, int pname, IntBuffer params, int params_position, long function_pointer);
}
