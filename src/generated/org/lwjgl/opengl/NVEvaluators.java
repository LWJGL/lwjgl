/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVEvaluators {
	public static final int GL_EVAL_2D_NV = 0x86c0;
	public static final int GL_EVAL_TRIANGULAR_2D_NV = 0x86c1;
	public static final int GL_MAP_TESSELLATION_NV = 0x86c2;
	public static final int GL_MAP_ATTRIB_U_ORDER_NV = 0x86c3;
	public static final int GL_MAP_ATTRIB_V_ORDER_NV = 0x86c4;
	public static final int GL_EVAL_FRACTIONAL_TESSELLATION_NV = 0x86c5;
	public static final int GL_EVAL_VERTEX_ATTRIB0_NV = 0x86c6;
	public static final int GL_EVAL_VERTEX_ATTRIB1_NV = 0x86c7;
	public static final int GL_EVAL_VERTEX_ATTRIB2_NV = 0x86c8;
	public static final int GL_EVAL_VERTEX_ATTRIB3_NV = 0x86c9;
	public static final int GL_EVAL_VERTEX_ATTRIB4_NV = 0x86ca;
	public static final int GL_EVAL_VERTEX_ATTRIB5_NV = 0x86cb;
	public static final int GL_EVAL_VERTEX_ATTRIB6_NV = 0x86cc;
	public static final int GL_EVAL_VERTEX_ATTRIB7_NV = 0x86cd;
	public static final int GL_EVAL_VERTEX_ATTRIB8_NV = 0x86ce;
	public static final int GL_EVAL_VERTEX_ATTRIB9_NV = 0x86cf;
	public static final int GL_EVAL_VERTEX_ATTRIB10_NV = 0x86d0;
	public static final int GL_EVAL_VERTEX_ATTRIB11_NV = 0x86d1;
	public static final int GL_EVAL_VERTEX_ATTRIB12_NV = 0x86d2;
	public static final int GL_EVAL_VERTEX_ATTRIB13_NV = 0x86d3;
	public static final int GL_EVAL_VERTEX_ATTRIB14_NV = 0x86d4;
	public static final int GL_EVAL_VERTEX_ATTRIB15_NV = 0x86d5;
	public static final int GL_MAX_MAP_TESSELLATION_NV = 0x86d6;
	public static final int GL_MAX_RATIONAL_EVAL_ORDER_NV = 0x86d7;

	private NVEvaluators() {
	}


	public static void glGetMapControlPointsNV(int target, int index, int type, int ustride, int vstride, boolean packed, FloatBuffer pPoints) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_evaluators_glGetMapControlPointsNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPoints);
		nglGetMapControlPointsNV(target, index, type, ustride, vstride, packed, pPoints, pPoints.position() << 2, function_pointer);
	}
	private static native void nglGetMapControlPointsNV(int target, int index, int type, int ustride, int vstride, boolean packed, Buffer pPoints, int pPoints_position, long function_pointer);

	public static void glMapControlPointsNV(int target, int index, int type, int ustride, int vstride, int uorder, int vorder, boolean packed, FloatBuffer pPoints) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_evaluators_glMapControlPointsNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(pPoints);
		nglMapControlPointsNV(target, index, type, ustride, vstride, uorder, vorder, packed, pPoints, pPoints.position() << 2, function_pointer);
	}
	private static native void nglMapControlPointsNV(int target, int index, int type, int ustride, int vstride, int uorder, int vorder, boolean packed, Buffer pPoints, int pPoints_position, long function_pointer);

	public static void glMapParameterNV(int target, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_evaluators_glMapParameterfvNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglMapParameterfvNV(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglMapParameterfvNV(int target, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glMapParameterNV(int target, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_evaluators_glMapParameterivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglMapParameterivNV(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglMapParameterivNV(int target, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetMapParameterNV(int target, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_evaluators_glGetMapParameterfvNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetMapParameterfvNV(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetMapParameterfvNV(int target, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetMapParameterNV(int target, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_evaluators_glGetMapParameterivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetMapParameterivNV(target, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetMapParameterivNV(int target, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glGetMapAttribParameterNV(int target, int index, int pname, FloatBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_evaluators_glGetMapAttribParameterfvNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetMapAttribParameterfvNV(target, index, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetMapAttribParameterfvNV(int target, int index, int pname, FloatBuffer params, int params_position, long function_pointer);

	public static void glGetMapAttribParameterNV(int target, int index, int pname, IntBuffer params) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_evaluators_glGetMapAttribParameterivNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(params, 4);
		nglGetMapAttribParameterivNV(target, index, pname, params, params.position(), function_pointer);
	}
	private static native void nglGetMapAttribParameterivNV(int target, int index, int pname, IntBuffer params, int params_position, long function_pointer);

	public static void glEvalMapsNV(int target, int mode) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_evaluators_glEvalMapsNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglEvalMapsNV(target, mode, function_pointer);
	}
	private static native void nglEvalMapsNV(int target, int mode, long function_pointer);
}
