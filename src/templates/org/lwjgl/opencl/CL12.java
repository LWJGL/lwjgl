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
import java.nio.LongBuffer;

/** The core OpenCL 1.1 API */
public interface CL12 {

	/** Error Codes */
	int CL_COMPILE_PROGRAM_FAILURE        = -15,
		CL_LINKER_NOT_AVAILABLE           = -16,
		CL_LINK_PROGRAM_FAILURE           = -17,
		CL_DEVICE_PARTITION_FAILED        = -18,
		CL_KERNEL_ARG_INFO_NOT_AVAILABLE  = -19,
		CL_INVALID_IMAGE_DESCRIPTOR       = -65,
		CL_INVALID_COMPILER_OPTIONS       = -66,
		CL_INVALID_LINKER_OPTIONS         = -67,
		CL_INVALID_DEVICE_PARTITION_COUNT = -68;

	/** OpenCL Version */
	int CL_VERSION_1_2 = 1;

	/** cl_bool */
	int CL_BLOCKING     = CL10.CL_TRUE,
		CL_NON_BLOCKING = CL10.CL_FALSE;

	/** cl_device_type - bitfield */
	int CL_DEVICE_TYPE_CUSTOM = (1 << 4);

	/* cl_device_info */
	int CL_DEVICE_DOUBLE_FP_CONFIG            = 0x1032,
		CL_DEVICE_LINKER_AVAILABLE            = 0x103E,
		CL_DEVICE_BUILT_IN_KERNELS            = 0x103F,
		CL_DEVICE_IMAGE_MAX_BUFFER_SIZE       = 0x1040,
		CL_DEVICE_IMAGE_MAX_ARRAY_SIZE        = 0x1041,
		CL_DEVICE_PARENT_DEVICE               = 0x1042,
		CL_DEVICE_PARTITION_MAX_SUB_DEVICES   = 0x1043,
		CL_DEVICE_PARTITION_PROPERTIES        = 0x1044,
		CL_DEVICE_PARTITION_AFFINITY_DOMAIN   = 0x1045,
		CL_DEVICE_PARTITION_TYPE              = 0x1046,
		CL_DEVICE_REFERENCE_COUNT             = 0x1047,
		CL_DEVICE_PREFERRED_INTEROP_USER_SYNC = 0x1048,
		CL_DEVICE_PRINTF_BUFFER_SIZE          = 0x1049;

	/* cl_device_fp_config - bitfield */
	int CL_FP_CORRECTLY_ROUNDED_DIVIDE_SQRT = (1 << 7);

	/* cl_context_properties */
	int CL_CONTEXT_INTEROP_USER_SYNC = 0x1085;

	/* cl_device_partition_property */
	int CL_DEVICE_PARTITION_EQUALLY            = 0x1086,
		CL_DEVICE_PARTITION_BY_COUNTS          = 0x1087,
		CL_DEVICE_PARTITION_BY_COUNTS_LIST_END = 0x0,
		CL_DEVICE_PARTITION_BY_AFFINITY_DOMAIN = 0x1088;

	/* cl_device_affinity_domain */
	int CL_DEVICE_AFFINITY_DOMAIN_NUMA               = (1 << 0),
		CL_DEVICE_AFFINITY_DOMAIN_L4_CACHE           = (1 << 1),
		CL_DEVICE_AFFINITY_DOMAIN_L3_CACHE           = (1 << 2),
		CL_DEVICE_AFFINITY_DOMAIN_L2_CACHE           = (1 << 3),
		CL_DEVICE_AFFINITY_DOMAIN_L1_CACHE           = (1 << 4),
		CL_DEVICE_AFFINITY_DOMAIN_NEXT_PARTITIONABLE = (1 << 5);

	/* cl_mem_flags - bitfield */
	int CL_MEM_HOST_WRITE_ONLY = (1 << 7),
		CL_MEM_HOST_READ_ONLY  = (1 << 8),
		CL_MEM_HOST_NO_ACCESS  = (1 << 9);

	/* cl_mem_migration_flags - bitfield */
	int CL_MIGRATE_MEM_OBJECT_HOST              = (1 << 0),
		CL_MIGRATE_MEM_OBJECT_CONTENT_UNDEFINED = (1 << 1);

	/* cl_mem_object_type */
	int CL_MEM_OBJECT_IMAGE2D_ARRAY  = 0x10F3,
		CL_MEM_OBJECT_IMAGE1D        = 0x10F4,
		CL_MEM_OBJECT_IMAGE1D_ARRAY  = 0x10F5,
		CL_MEM_OBJECT_IMAGE1D_BUFFER = 0x10F6;

	/* cl_image_info */
	int CL_IMAGE_ARRAY_SIZE     = 0x1117,
		CL_IMAGE_BUFFER         = 0x1118,
		CL_IMAGE_NUM_MIP_LEVELS = 0x1119,
		CL_IMAGE_NUM_SAMPLES    = 0x111A;

	/* cl_map_flags - bitfield */
	int CL_MAP_WRITE_INVALIDATE_REGION = (1 << 2);

	/* cl_program_info */
	int CL_PROGRAM_NUM_KERNELS  = 0x1167,
		CL_PROGRAM_KERNEL_NAMES = 0x1168;

	/* cl_program_build_info */
	int CL_PROGRAM_BINARY_TYPE = 0x1184;

	/* cl_program_binary_type */
	int CL_PROGRAM_BINARY_TYPE_NONE            = 0x0,
		CL_PROGRAM_BINARY_TYPE_COMPILED_OBJECT = 0x1,
		CL_PROGRAM_BINARY_TYPE_LIBRARY         = 0x2,
		CL_PROGRAM_BINARY_TYPE_EXECUTABLE      = 0x4;

	/* cl_kernel_info */
	int CL_KERNEL_ATTRIBUTES = 0x1195;

	/* cl_kernel_arg_info */
	int CL_KERNEL_ARG_ADDRESS_QUALIFIER = 0x1196,
		CL_KERNEL_ARG_ACCESS_QUALIFIER  = 0x1197,
		CL_KERNEL_ARG_TYPE_NAME         = 0x1198,
		CL_KERNEL_ARG_NAME              = 0x1199;

	/* cl_kernel_arg_address_qualifier */
	int CL_KERNEL_ARG_ADDRESS_GLOBAL   = 0x119A,
		CL_KERNEL_ARG_ADDRESS_LOCAL    = 0x119B,
		CL_KERNEL_ARG_ADDRESS_CONSTANT = 0x119C,
		CL_KERNEL_ARG_ADDRESS_PRIVATE  = 0x119D;

	/* cl_kernel_arg_access_qualifier */
	int CL_KERNEL_ARG_ACCESS_READ_ONLY  = 0x11A0,
		CL_KERNEL_ARG_ACCESS_WRITE_ONLY = 0x11A1,
		CL_KERNEL_ARG_ACCESS_READ_WRITE = 0x11A2,
		CL_KERNEL_ARG_ACCESS_NONE       = 0x11A3;

	/* cl_kernel_work_group_info */
	int CL_KERNEL_GLOBAL_WORK_SIZE = 0x11B5;

	/* cl_command_type */
	int CL_COMMAND_BARRIER             = 0x1205,
		CL_COMMAND_MIGRATE_MEM_OBJECTS = 0x1206,
		CL_COMMAND_FILL_BUFFER         = 0x1207,
		CL_COMMAND_FILL_IMAGE          = 0x1208;

	@Code(javaAfterNative = "\t\tif ( __result == CL10.CL_SUCCESS ) device.retain();")
	@cl_int
	int clRetainDevice(@PointerWrapper("cl_device_id") CLDevice device);

	/**
	 * Warning: LWJGL will not automatically release any objects associated with sub-devices.
	 * The user is responsible for tracking and releasing everything prior to calling this method.
	 *
	 * @param device the parent CLDevice
	 *
	 * @return the error code
	 */
	@Code(
		javaBeforeNative = "\t\tAPIUtil.releaseObjects(device);",
		javaAfterNative = "\t\tif ( __result == CL10.CL_SUCCESS ) device.release();"
	)
	@cl_int
	int clReleaseDevice(@PointerWrapper("cl_device_id") CLDevice device);

	@Code(javaAfterNative = "\t\tif ( __result == CL10.CL_SUCCESS && out_devices != null ) in_device.registerSubCLDevices(out_devices);")
	@cl_int
	int clCreateSubDevices(
		@PointerWrapper("cl_device_id") CLDevice in_device,
		@NullTerminated @Const @NativeType("cl_device_partition_property") LongBuffer properties,
		@AutoSize(value = "out_devices", canBeNull = true) @cl_uint int num_devices,
		@OutParameter @Check(canBeNull = true) @NativeType("cl_device_id") PointerBuffer out_devices,
		@OutParameter @Check(value = "1", canBeNull = true) @cl_uint IntBuffer num_devices_ret);

	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_mem", params = "context")
	CLMem clCreateImage(@PointerWrapper("cl_context") CLContext context,
	                    @NativeType("cl_mem_flags") long flags,
	                    @Check("2 * 4") @Const @NativeType("cl_image_format") ByteBuffer image_format,
	                    @Check("3 * 4 + 7 * PointerBuffer.getPointerSize()") @Const @NativeType("cl_image_desc") ByteBuffer image_desc,
	                    @Check
	                    @cl_byte
	                    @cl_short
	                    @cl_int
	                    @cl_float Buffer host_ptr,
	                    @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_program", params = "context")
	CLProgram clCreateProgramWithBuiltInKernels(@PointerWrapper("cl_context") CLContext context,
	                                            @AutoSize("device_list") @cl_uint int num_devices,
	                                            @Check("1") @Const @NativeType("cl_device_id") PointerBuffer device_list,
	                                            @Check @Const @cl_char ByteBuffer kernel_names,
	                                            @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Alternate("clCreateProgramWithBuiltInKernels")
	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_program", params = "context")
	CLProgram clCreateProgramWithBuiltInKernels(@PointerWrapper("cl_context") CLContext context,
	                                            @AutoSize("device_list") @cl_uint int num_devices,
	                                            @Check("1") @Const @NativeType("cl_device_id") PointerBuffer device_list,
	                                            CharSequence kernel_names,
	                                            @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	/** Single null-terminated header include name. */
	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = CallbackUtil.createGlobalRef(pfn_notify);\n" +
		                   "\t\tif ( pfn_notify != null ) pfn_notify.setContext(program.getParent());",
		// Check if we need to delete the GlobalRef.
		javaFinally = "\t\t\tCallbackUtil.checkCallback(__result, user_data);"
	)
	@cl_int
	int clCompileProgram(@PointerWrapper("cl_program") CLProgram program,
	                     @AutoSize(value = "device_list", canBeNull = true) @cl_uint int num_devices,
	                     @Check(canBeNull = true) @Const @NativeType("cl_device_id") PointerBuffer device_list,
	                     @Check @NullTerminated @Const @cl_char ByteBuffer options,
	                     @Constant("1") @cl_uint int num_input_headers,
	                     @Check("1") @Const @NativeType("cl_program") PointerBuffer input_header,
	                     @NullTerminated @Check @Const @cl_char @Indirect ByteBuffer header_include_name,
	                     @PointerWrapper(value = "cl_program_callback", canBeNull = true) CLCompileProgramCallback pfn_notify,
	                     @Constant("user_data") @PointerWrapper("void *") long user_data);

	/** Multiple null-terminated header include names, one after the other. */
	@Alternate(value = "clCompileProgram", nativeAlt = true, javaAlt = true)
	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = CallbackUtil.createGlobalRef(pfn_notify);\n" +
		                   "\t\tif ( pfn_notify != null ) pfn_notify.setContext(program.getParent());",
		// Check if we need to delete the GlobalRef.
		javaFinally = "\t\t\tCallbackUtil.checkCallback(__result, user_data);"
	)
	@cl_int
	int clCompileProgramMulti(@PointerWrapper("cl_program") CLProgram program,
	                          @AutoSize(value = "device_list", canBeNull = true) @cl_uint int num_devices,
	                          @Check(canBeNull = true) @Const @NativeType("cl_device_id") PointerBuffer device_list,
	                          @Check @NullTerminated @Const @cl_char ByteBuffer options,
	                          @AutoSize("input_headers") @cl_uint int num_input_headers,
	                          @Check("1") @Const @NativeType("cl_program") PointerBuffer input_headers,
	                          @NullTerminated("input_headers.remaining()") @Check @Const @Indirect @cl_char @PointerArray("num_input_headers") ByteBuffer header_include_names,
	                          @PointerWrapper(value = "cl_program_callback", canBeNull = true) CLCompileProgramCallback pfn_notify,
	                          @Constant("user_data") @PointerWrapper("void *") long user_data);

	@Alternate(value = "clCompileProgram", nativeAlt = true)
	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = CallbackUtil.createGlobalRef(pfn_notify);\n" +
		                   "\t\tif ( pfn_notify != null ) pfn_notify.setContext(program.getParent());",
		// Check if we need to delete the GlobalRef.
		javaFinally = "\t\t\tCallbackUtil.checkCallback(__result, user_data);"
	)
	@cl_int
	int clCompileProgram3(@PointerWrapper("cl_program") CLProgram program,
	                      @AutoSize(value = "device_list", canBeNull = true) @cl_uint int num_devices,
	                      @Check(canBeNull = true) @Const @NativeType("cl_device_id") PointerBuffer device_list,
	                      @Check @NullTerminated @Const @cl_char ByteBuffer options,
	                      @Constant("header_include_names.length") @cl_uint int num_input_headers,
	                      @Check("header_include_names.length") @Const @NativeType("cl_program") PointerBuffer input_headers,
	                      @NullTerminated @Check("1") @PointerArray(value = "num_input_headers") @Const @NativeType("cl_char") ByteBuffer[] header_include_names,
	                      @PointerWrapper(value = "cl_program_callback", canBeNull = true) CLCompileProgramCallback pfn_notify,
	                      @Constant("user_data") @PointerWrapper("void *") long user_data);

	@Alternate("clCompileProgram")
	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = CallbackUtil.createGlobalRef(pfn_notify);\n" +
		                   "\t\tif ( pfn_notify != null ) pfn_notify.setContext(program.getParent());",
		// Check if we need to delete the GlobalRef.
		javaFinally = "\t\t\tCallbackUtil.checkCallback(__result, user_data);"
	)
	@cl_int
	int clCompileProgram(@PointerWrapper("cl_program") CLProgram program,
	                     @AutoSize(value = "device_list", canBeNull = true) @cl_uint int num_devices,
	                     @Check(canBeNull = true) @Const @NativeType("cl_device_id") PointerBuffer device_list,
	                     @NullTerminated CharSequence options,
	                     @Constant("1") @cl_uint int num_input_headers,
	                     @Check("1") @Const @NativeType("cl_program") PointerBuffer input_header,
	                     @NullTerminated @Const CharSequence header_include_name,
	                     @PointerWrapper(value = "cl_program_callback", canBeNull = true) CLCompileProgramCallback pfn_notify,
	                     @Constant("user_data") @PointerWrapper("void *") long user_data);

	@Alternate(value = "clCompileProgram", nativeAlt = true, skipNative = true)
	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = CallbackUtil.createGlobalRef(pfn_notify);\n" +
		                   "\t\tif ( pfn_notify != null ) pfn_notify.setContext(program.getParent());",
		// Check if we need to delete the GlobalRef.
		javaFinally = "\t\t\tCallbackUtil.checkCallback(__result, user_data);"
	)
	@cl_int
	int clCompileProgramMulti(@PointerWrapper("cl_program") CLProgram program,
	                          @AutoSize(value = "device_list", canBeNull = true) @cl_uint int num_devices,
	                          @Check(canBeNull = true) @Const @NativeType("cl_device_id") PointerBuffer device_list,
	                          @NullTerminated CharSequence options,
	                          @AutoSize("input_header") @cl_uint int num_input_headers,
	                          @Check("1") @Const @NativeType("cl_program") PointerBuffer input_header,
	                          @NullTerminated @PointerArray(value = "num_input_headers") @Const CharSequence[] header_include_name,
	                          @PointerWrapper(value = "cl_program_callback", canBeNull = true) CLCompileProgramCallback pfn_notify,
	                          @Constant("user_data") @PointerWrapper("void *") long user_data);

	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = CallbackUtil.createGlobalRef(pfn_notify);\n" +
		                   "\t\tif ( pfn_notify != null ) pfn_notify.setContext(context);",
		// Check if we need to delete the GlobalRef.
		javaFinally = "\t\t\tCallbackUtil.checkCallback(errcode_ret.get(errcode_ret.position()), user_data);"
	)
	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_program", params = "context")
	CLProgram clLinkProgram(@PointerWrapper("cl_context") CLContext context,
	                        @AutoSize(value = "device_list", canBeNull = true) @cl_uint int num_devices,
	                        @Check(canBeNull = true) @Const @NativeType("cl_device_id") PointerBuffer device_list,
	                        @NullTerminated @Check @Const @cl_char ByteBuffer options,
	                        @AutoSize("input_programs") @cl_uint int num_input_programs,
	                        @Check @Const @NativeType("cl_program") PointerBuffer input_programs,
	                        @PointerWrapper(value = "cl_program_callback", canBeNull = true) CLLinkProgramCallback pfn_notify,
	                        @Constant("user_data") @PointerWrapper("void *") long user_data,
	                        @OutParameter @Check("1") @cl_int IntBuffer errcode_ret);

	@Alternate("clLinkProgram")
	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = CallbackUtil.createGlobalRef(pfn_notify);\n" +
		                   "\t\tif ( pfn_notify != null ) pfn_notify.setContext(context);",
		// Check if we need to delete the GlobalRef.
		javaFinally = "\t\t\tCallbackUtil.checkCallback(errcode_ret.get(errcode_ret.position()), user_data);"
	)
	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_program", params = "context")
	CLProgram clLinkProgram(@PointerWrapper("cl_context") CLContext context,
	                        @AutoSize(value = "device_list", canBeNull = true) @cl_uint int num_devices,
	                        @Check(canBeNull = true) @Const @NativeType("cl_device_id") PointerBuffer device_list,
	                        @NullTerminated CharSequence options,
	                        @AutoSize("input_programs") @cl_uint int num_input_programs,
	                        @Check @Const @NativeType("cl_program") PointerBuffer input_programs,
	                        @PointerWrapper(value = "cl_program_callback", canBeNull = true) CLLinkProgramCallback pfn_notify,
	                        @Constant("user_data") @PointerWrapper("void *") long user_data,
	                        @OutParameter @Check("1") @cl_int IntBuffer errcode_ret);

	@cl_int
	int clUnloadPlatformCompiler(@PointerWrapper("cl_platform_id") CLPlatform platform);

	@cl_int
	int clGetKernelArgInfo(@PointerWrapper("cl_kernel") CLKernel kernel,
	                       @cl_uint int arg_indx,
	                       @NativeType("cl_kernel_arg_info") int param_name,
	                       @AutoSize(value = "param_value", canBeNull = true) @size_t long param_value_size,
	                       @OutParameter @Check(canBeNull = true) @cl_void ByteBuffer param_value,
	                       @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	@cl_int
	int clEnqueueFillBuffer(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                        @PointerWrapper("cl_mem") CLMem buffer,
	                        @Check @Const @cl_void ByteBuffer pattern,
	                        @AutoSize("pattern") @size_t long pattern_size,
	                        @size_t long offset,
	                        @size_t long size,
	                        @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                        @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                        @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@cl_int
	int clEnqueueFillImage(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                       @PointerWrapper("cl_mem") CLMem image,
	                       @Check("4 * 4") @Const @cl_void ByteBuffer fill_color,
	                       @Check("3") @Const @NativeType("size_t") PointerBuffer origin,
	                       @Check("3") @Const @NativeType("size_t") PointerBuffer region,
	                       @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                       @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                       @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@cl_int
	int clEnqueueMigrateMemObjects(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                               @AutoSize("mem_objects") @cl_uint int num_mem_objects,
	                               @Check @Const @NativeType("cl_mem") PointerBuffer mem_objects,
	                               @NativeType("cl_mem_migration_flags") long flags,
	                               @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                               @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                               @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@cl_int
	int clEnqueueMarkerWithWaitList(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                                @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                                @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                                @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@cl_int
	int clEnqueueBarrierWithWaitList(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                                 @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                                 @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                                 @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Optional(reason = "Missing from AMD CL 1.2 preview drivers.")
	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = CallbackUtil.createGlobalRef(pfn_notify);",
		// Check if we need to delete the GlobalRef.
		javaFinally = "\t\t\tcontext.setPrintfCallback(user_data, __result);"
	)
	@cl_int
	int clSetPrintfCallback(@PointerWrapper("cl_context") CLContext context,
	                        @PointerWrapper("cl_printf_callback") CLPrintfCallback pfn_notify,
	                        @Constant("user_data") @PointerWrapper("void *") long user_data);

	@Private
	@PointerWrapper("void *")
	CLFunctionAddress clGetExtensionFunctionAddressForPlatform(@PointerWrapper("cl_platform_id") CLPlatform platform,
	                                                           @Check("1") @Const @cl_char ByteBuffer func_name);

	@Alternate("clGetExtensionFunctionAddressForPlatform")
	@Private
	@PointerWrapper("void *")
	CLFunctionAddress clGetExtensionFunctionAddressForPlatform(@PointerWrapper("cl_platform_id") CLPlatform platform,
	                                                           CharSequence func_name);

}