/*
 * Copyright (c) 2002-2008 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.lwjgl.opencl;

import org.lwjgl.PointerBuffer;
import org.lwjgl.util.generator.*;
import org.lwjgl.util.generator.opencl.*;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/** The core OpenCL 1.1 API */
public interface CL11 {

	/** Error Codes */
	int CL_MISALIGNED_SUB_BUFFER_OFFSET = -13,
		CL_EXEC_STATUS_ERROR_FOR_EVENTS_IN_WAIT_LIST = -14,
		CL_INVALID_PROPERTY = -64;

	/** OpenCL Version */
	int CL_VERSION_1_1 = 1;

	/** cl_device_info */
	int CL_DEVICE_PREFERRED_VECTOR_WIDTH_HALF = 0x1034,
		CL_DEVICE_HOST_UNIFIED_MEMORY = 0x1035,
		CL_DEVICE_NATIVE_VECTOR_WIDTH_CHAR = 0x1036,
		CL_DEVICE_NATIVE_VECTOR_WIDTH_SHORT = 0x1037,
		CL_DEVICE_NATIVE_VECTOR_WIDTH_INT = 0x1038,
		CL_DEVICE_NATIVE_VECTOR_WIDTH_LONG = 0x1039,
		CL_DEVICE_NATIVE_VECTOR_WIDTH_FLOAT = 0x103A,
		CL_DEVICE_NATIVE_VECTOR_WIDTH_DOUBLE = 0x103B,
		CL_DEVICE_NATIVE_VECTOR_WIDTH_HALF = 0x103C,
		CL_DEVICE_OPENCL_C_VERSION = 0x103D;

	/** cl_device_fp_config - bitfield */
	int CL_FP_SOFT_FLOAT = (1 << 6);

	/** cl_context_info */
	int CL_CONTEXT_NUM_DEVICES = 0x1083;

	/** cl_channel_order */
	int CL_Rx = 0x10BA,
		CL_RGx = 0x10BB,
		CL_RGBx = 0x10BC;

	/** cl_mem_info */
	int CL_MEM_ASSOCIATED_MEMOBJECT = 0x1107,
		CL_MEM_OFFSET = 0x1108;

	/** cl_addressing_mode */
	int CL_ADDRESS_MIRRORED_REPEAT = 0x1134;

	/** cl_kernel_work_group_info */
	int CL_KERNEL_PREFERRED_WORK_GROUP_SIZE_MULTIPLE = 0x11B3,
		CL_KERNEL_PRIVATE_MEM_SIZE = 0x11B4;

	/** cl_event_info */
	int CL_EVENT_CONTEXT = 0x11D4;

	/** cl_command_type */
	int CL_COMMAND_READ_BUFFER_RECT = 0x1201,
		CL_COMMAND_WRITE_BUFFER_RECT = 0x1202,
		CL_COMMAND_COPY_BUFFER_RECT = 0x1203,
		CL_COMMAND_USER = 0x1204;

	/** cl_buffer_create_type */
	int CL_BUFFER_CREATE_TYPE_REGION = 0x1220;

	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_mem", factory = "CLMem.create", params = "buffer.getParent()")
	CLMem clCreateSubBuffer(@PointerWrapper("cl_mem") CLMem buffer,
	                        @NativeType("cl_mem_flags") long flags,
	                        @NativeType("cl_buffer_create_type") int buffer_create_type,
	                        @Const @Check("2 * PointerBuffer.getPointerSize()") @NativeType("cl_void") ByteBuffer buffer_create_info,
	                        @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = CallbackUtil.createGlobalRef(pfn_notify);",
		// Check if we need to delete the GlobalRef.
		javaFinally = "\t\t\tCallbackUtil.checkCallback(__result, user_data);"
	)
	@cl_int
	int clSetMemObjectDestructorCallback(@PointerWrapper("cl_mem") CLMem memobj,
	                                     @PointerWrapper("cl_mem_object_destructor_callback") CLMemObjectDestructorCallback pfn_notify,
	                                     @Constant("user_data") @PointerWrapper("void *") long user_data);

	@Code(javaAfterNative = "\t\tif ( __result == CL10.CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueReadBufferRect(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                            @PointerWrapper("cl_mem") CLMem buffer,
	                            @cl_bool int blocking_read,
	                            @Const @Check("3") @NativeType("size_t") PointerBuffer buffer_offset,
	                            @Const @Check("3") @NativeType("size_t") PointerBuffer host_offset,
	                            @Const @Check("3") @NativeType("size_t") PointerBuffer region,
	                            @size_t long buffer_row_pitch,
	                            @size_t long buffer_slice_pitch,
	                            @size_t long host_row_pitch,
	                            @size_t long host_slice_pitch,
	                            @OutParameter @Check("CLChecks.calculateBufferRectSize(host_offset, region, host_row_pitch, host_slice_pitch)")
	                            @cl_byte
	                            @cl_short
	                            @cl_int
	                            @cl_long
	                            @cl_float
	                            @cl_double Buffer ptr,
	                            @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                            @Const @Check(canBeNull = true) @NativeType("cl_event") PointerBuffer event_wait_list,
	                            @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Code(javaAfterNative = "\t\tif ( __result == CL10.CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueWriteBufferRect(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                             @PointerWrapper("cl_mem") CLMem buffer,
	                             @cl_bool int blocking_write,
	                             @Const @Check("3") @NativeType("size_t") PointerBuffer buffer_offset,
	                             @Const @Check("3") @NativeType("size_t") PointerBuffer host_offset,
	                             @Const @Check("3") @NativeType("size_t") PointerBuffer region,
	                             @size_t long buffer_row_pitch,
	                             @size_t long buffer_slice_pitch,
	                             @size_t long host_row_pitch,
	                             @size_t long host_slice_pitch,
	                             @Const @Check("CLChecks.calculateBufferRectSize(host_offset, region, host_row_pitch, host_slice_pitch)")
	                             @cl_byte
	                             @cl_short
	                             @cl_int
	                             @cl_long
	                             @cl_float
	                             @cl_double Buffer ptr,
	                             @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                             @Const @Check(canBeNull = true) @NativeType("cl_event") PointerBuffer event_wait_list,
	                             @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Code(javaAfterNative = "\t\tif ( __result == CL10.CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueCopyBufferRect(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                            @PointerWrapper("cl_mem") CLMem src_buffer,
	                            @PointerWrapper("cl_mem") CLMem dst_buffer,
	                            @Const @Check("3") @NativeType("size_t") PointerBuffer src_origin,
	                            @Const @Check("3") @NativeType("size_t") PointerBuffer dst_origin,
	                            @Const @Check("3") @NativeType("size_t") PointerBuffer region,
	                            @size_t long src_row_pitch,
	                            @size_t long src_slice_pitch,
	                            @size_t long dst_row_pitch,
	                            @size_t long dst_slice_pitch,
	                            @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                            @Const @Check(canBeNull = true) @NativeType("cl_event") PointerBuffer event_wait_list,
	                            @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_event", params = "context")
	CLEvent clCreateUserEvent(@PointerWrapper("cl_context") CLContext context,
	                          @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@cl_int
	int clSetUserEventStatus(@PointerWrapper("cl_event") CLEvent event,
	                         @cl_int int execution_status);

	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = CallbackUtil.createGlobalRef(pfn_notify);\n" +
		                   "\t\tpfn_notify.setRegistry(event.getParentRegistry());",
		// Check if we need to delete the GlobalRef.
		javaFinally = "\t\t\tCallbackUtil.checkCallback(__result, user_data);"
	)
	@cl_int
	int clSetEventCallback(@PointerWrapper("cl_event") CLEvent event,
	                       @cl_int int command_exec_callback_type,
	                       @PointerWrapper("cl_event_callback") CLEventCallback pfn_notify,
	                       @Constant("user_data") @PointerWrapper("void *") long user_data);

}