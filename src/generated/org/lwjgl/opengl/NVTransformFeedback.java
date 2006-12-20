/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class NVTransformFeedback {
	/**
	 * Accepted by the &lt;target&gt; parameters of BindBuffer, BufferData,
	 * BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData,
	 * GetBufferPointerv, BindBufferRangeNV, BindBufferOffsetNV and
	 * BindBufferBaseNV:
	 */
	public static final int GL_TRANSFORM_FEEDBACK_BUFFER_NV = 0x8c8e;
	/**
	 * Accepted by the &lt;param&gt; parameter of GetIntegerIndexedvEXT and
	 * GetBooleanIndexedvEXT:
	 */
	public static final int GL_TRANSFORM_FEEDBACK_BUFFER_START_NV = 0x8c84;
	public static final int GL_TRANSFORM_FEEDBACK_BUFFER_SIZE_NV = 0x8c85;
	public static final int GL_TRANSFORM_FEEDBACK_RECORD_NV = 0x8c86;
	/**
	 * Accepted by the &lt;param&gt; parameter of GetIntegerIndexedvEXT and
	 * GetBooleanIndexedvEXT, and by the &lt;pname&gt; parameter of GetBooleanv,
	 * GetDoublev, GetIntegerv, and GetFloatv:
	 */
	public static final int GL_TRANSFORM_FEEDBACK_BUFFER_BINDING_NV = 0x8c8f;
	/**
	 * Accepted by the &lt;bufferMode&gt; parameter of TransformFeedbackAttribsNV and
	 * TransformFeedbackVaryingsNV:
	 */
	public static final int GL_INTERLEAVED_ATTRIBS_NV = 0x8c8c;
	public static final int GL_SEPARATE_ATTRIBS_NV = 0x8c8d;
	/**
	 * Accepted by the &lt;target&gt; parameter of BeginQuery, EndQuery, and
	 * GetQueryiv:
	 */
	public static final int GL_PRIMITIVES_GENERATED_NV = 0x8c87;
	public static final int GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN_NV = 0x8c88;
	/**
	 * Accepted by the &lt;cap&gt; parameter of Enable, Disable, and IsEnabled, and by
	 * the &lt;pname&gt; parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev:
	 */
	public static final int GL_RASTERIZER_DISCARD_NV = 0x8c89;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetDoublev, GetIntegerv,
	 * and GetFloatv:
	 */
	public static final int GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS_NV = 0x8c8a;
	public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS_NV = 0x8c8b;
	public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS_NV = 0x8c80;
	public static final int GL_TRANSFORM_FEEDBACK_ATTRIBS_NV = 0x8c7e;
	/**
	 *Accepted by the &lt;pname&gt; parameter of GetProgramiv: 
	 */
	public static final int GL_ACTIVE_VARYINGS_NV = 0x8c81;
	public static final int GL_ACTIVE_VARYING_MAX_LENGTH_NV = 0x8c82;
	public static final int GL_TRANSFORM_FEEDBACK_VARYINGS_NV = 0x8c83;
	/**
	 * Accepted by the &lt;pname&gt; parameter of GetBooleanv, GetDoublev, GetIntegerv,
	 * GetFloatv, and GetProgramiv:
	 */
	public static final int GL_TRANSFORM_FEEDBACK_BUFFER_MODE_NV = 0x8c7f;
	/**
	 *Accepted by the &lt;attribs&gt; parameter of TransformFeedbackAttribsNV: 
	 */
	public static final int GL_BACK_PRIMARY_COLOR_NV = 0x8c77;
	public static final int GL_BACK_SECONDARY_COLOR_NV = 0x8c78;
	public static final int GL_TEXTURE_COORD_NV = 0x8c79;
	public static final int GL_CLIP_DISTANCE_NV = 0x8c7a;
	public static final int GL_VERTEX_ID_NV = 0x8c7b;
	public static final int GL_PRIMITIVE_ID_NV = 0x8c7c;
	public static final int GL_GENERIC_ATTRIB_NV = 0x8c7d;

	private NVTransformFeedback() {
	}


	public static void glBindBufferRangeNV(int target, int index, int buffer, long offset, long size) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_transform_feedback_glBindBufferRangeNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBindBufferRangeNV(target, index, buffer, offset, size, function_pointer);
	}
	private static native void nglBindBufferRangeNV(int target, int index, int buffer, long offset, long size, long function_pointer);

	public static void glBindBufferOffsetNV(int target, int index, int buffer, long offset) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_transform_feedback_glBindBufferOffsetNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBindBufferOffsetNV(target, index, buffer, offset, function_pointer);
	}
	private static native void nglBindBufferOffsetNV(int target, int index, int buffer, long offset, long function_pointer);

	public static void glBindBufferBaseNV(int target, int index, int buffer) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_transform_feedback_glBindBufferBaseNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBindBufferBaseNV(target, index, buffer, function_pointer);
	}
	private static native void nglBindBufferBaseNV(int target, int index, int buffer, long function_pointer);

	public static void glTransformFeedbackAttribsNV(IntBuffer attribs, int bufferMode) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_transform_feedback_glTransformFeedbackAttribsNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(attribs);
		nglTransformFeedbackAttribsNV((attribs.remaining()), attribs, attribs.position(), bufferMode, function_pointer);
	}
	private static native void nglTransformFeedbackAttribsNV(int count, IntBuffer attribs, int attribs_position, int bufferMode, long function_pointer);

	public static void glTransformFeedbackVaryingsNV(int program, IntBuffer locations, int bufferMode) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_transform_feedback_glTransformFeedbackVaryingsNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(locations);
		nglTransformFeedbackVaryingsNV(program, (locations.remaining()), locations, locations.position(), bufferMode, function_pointer);
	}
	private static native void nglTransformFeedbackVaryingsNV(int program, int count, IntBuffer locations, int locations_position, int bufferMode, long function_pointer);

	public static void glBeginTransformFeedbackNV(int primitiveMode) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_transform_feedback_glBeginTransformFeedbackNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBeginTransformFeedbackNV(primitiveMode, function_pointer);
	}
	private static native void nglBeginTransformFeedbackNV(int primitiveMode, long function_pointer);

	public static void glEndTransformFeedbackNV() {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_transform_feedback_glEndTransformFeedbackNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglEndTransformFeedbackNV(function_pointer);
	}
	private static native void nglEndTransformFeedbackNV(long function_pointer);

	public static int glGetVaryingLocationNV(int program, ByteBuffer name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_transform_feedback_glGetVaryingLocationNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(name);
		BufferChecks.checkNullTerminated(name);
		int __result = nglGetVaryingLocationNV(program, name, name.position(), function_pointer);
		return __result;
	}
	private static native int nglGetVaryingLocationNV(int program, ByteBuffer name, int name_position, long function_pointer);

	public static void glGetActiveVaryingNV(int program, int index, IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_transform_feedback_glGetActiveVaryingNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(length, 1);
		BufferChecks.checkBuffer(size, 1);
		BufferChecks.checkBuffer(type, 1);
		BufferChecks.checkDirect(name);
		nglGetActiveVaryingNV(program, index, (name.remaining()), length, length.position(), size, size.position(), type, type.position(), name, name.position(), function_pointer);
	}
	private static native void nglGetActiveVaryingNV(int program, int index, int bufSize, IntBuffer length, int length_position, IntBuffer size, int size_position, IntBuffer type, int type_position, ByteBuffer name, int name_position, long function_pointer);

	public static void glActiveVaryingNV(int program, ByteBuffer name) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_transform_feedback_glActiveVaryingNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkDirect(name);
		BufferChecks.checkNullTerminated(name);
		nglActiveVaryingNV(program, name, name.position(), function_pointer);
	}
	private static native void nglActiveVaryingNV(int program, ByteBuffer name, int name_position, long function_pointer);

	public static void glGetTransformFeedbackVaryingNV(int program, int index, IntBuffer location) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.NV_transform_feedback_glGetTransformFeedbackVaryingNV_pointer;
		BufferChecks.checkFunctionAddress(function_pointer);
		BufferChecks.checkBuffer(location, 1);
		nglGetTransformFeedbackVaryingNV(program, index, location, location.position(), function_pointer);
	}
	private static native void nglGetTransformFeedbackVaryingNV(int program, int index, IntBuffer location, int location_position, long function_pointer);
}
