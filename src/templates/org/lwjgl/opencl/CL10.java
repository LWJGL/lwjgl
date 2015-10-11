/*
 * Copyright (c) 2002-2010 LWJGL Project
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

/** The core OpenCL 1.0 API */
public interface CL10 {

	/** Error Codes */
	int CL_SUCCESS = 0,
		CL_DEVICE_NOT_FOUND = -1,
		CL_DEVICE_NOT_AVAILABLE = -2,
		CL_COMPILER_NOT_AVAILABLE = -3,
		CL_MEM_OBJECT_ALLOCATION_FAILURE = -4,
		CL_OUT_OF_RESOURCES = -5,
		CL_OUT_OF_HOST_MEMORY = -6,
		CL_PROFILING_INFO_NOT_AVAILABLE = -7,
		CL_MEM_COPY_OVERLAP = -8,
		CL_IMAGE_FORMAT_MISMATCH = -9,
		CL_IMAGE_FORMAT_NOT_SUPPORTED = -10,
		CL_BUILD_PROGRAM_FAILURE = -11,
		CL_MAP_FAILURE = -12,

		CL_INVALID_VALUE = -30,
		CL_INVALID_DEVICE_TYPE = -31,
		CL_INVALID_PLATFORM = -32,
		CL_INVALID_DEVICE = -33,
		CL_INVALID_CONTEXT = -34,
		CL_INVALID_QUEUE_PROPERTIES = -35,
		CL_INVALID_COMMAND_QUEUE = -36,
		CL_INVALID_HOST_PTR = -37,
		CL_INVALID_MEM_OBJECT = -38,
		CL_INVALID_IMAGE_FORMAT_DESCRIPTOR = -39,
		CL_INVALID_IMAGE_SIZE = -40,
		CL_INVALID_SAMPLER = -41,
		CL_INVALID_BINARY = -42,
		CL_INVALID_BUILD_OPTIONS = -43,
		CL_INVALID_PROGRAM = -44,
		CL_INVALID_PROGRAM_EXECUTABLE = -45,
		CL_INVALID_KERNEL_NAME = -46,
		CL_INVALID_KERNEL_DEFINITION = -47,
		CL_INVALID_KERNEL = -48,
		CL_INVALID_ARG_INDEX = -49,
		CL_INVALID_ARG_VALUE = -50,
		CL_INVALID_ARG_SIZE = -51,
		CL_INVALID_KERNEL_ARGS = -52,
		CL_INVALID_WORK_DIMENSION = -53,
		CL_INVALID_WORK_GROUP_SIZE = -54,
		CL_INVALID_WORK_ITEM_SIZE = -55,
		CL_INVALID_GLOBAL_OFFSET = -56,
		CL_INVALID_EVENT_WAIT_LIST = -57,
		CL_INVALID_EVENT = -58,
		CL_INVALID_OPERATION = -59,
		CL_INVALID_GL_OBJECT = -60,
		CL_INVALID_BUFFER_SIZE = -61,
		CL_INVALID_MIP_LEVEL = -62,
		CL_INVALID_GLOBAL_WORK_SIZE = -63;

	/** OpenCL Version */
	int CL_VERSION_1_0 = 1;

	/** cl_bool */
	int CL_FALSE = 0,
		CL_TRUE = 1;

	/** cl_platform_info */
	int CL_PLATFORM_PROFILE = 0x0900,
		CL_PLATFORM_VERSION = 0x0901,
		CL_PLATFORM_NAME = 0x0902,
		CL_PLATFORM_VENDOR = 0x0903,
		CL_PLATFORM_EXTENSIONS = 0x0904;

	/** cl_device_type - bitfield */
	int CL_DEVICE_TYPE_DEFAULT = (1 << 0),
		CL_DEVICE_TYPE_CPU = (1 << 1),
		CL_DEVICE_TYPE_GPU = (1 << 2),
		CL_DEVICE_TYPE_ACCELERATOR = (1 << 3),
		CL_DEVICE_TYPE_ALL = 0xFFFFFFFF;

	/** cl_device_info */
	int CL_DEVICE_TYPE = 0x1000,
		CL_DEVICE_VENDOR_ID = 0x1001,
		CL_DEVICE_MAX_COMPUTE_UNITS = 0x1002,
		CL_DEVICE_MAX_WORK_ITEM_DIMENSIONS = 0x1003,
		CL_DEVICE_MAX_WORK_GROUP_SIZE = 0x1004,
		CL_DEVICE_MAX_WORK_ITEM_SIZES = 0x1005,
		CL_DEVICE_PREFERRED_VECTOR_WIDTH_CHAR = 0x1006,
		CL_DEVICE_PREFERRED_VECTOR_WIDTH_SHORT = 0x1007,
		CL_DEVICE_PREFERRED_VECTOR_WIDTH_ = 0x1008,
		CL_DEVICE_PREFERRED_VECTOR_WIDTH_LONG = 0x1009,
		CL_DEVICE_PREFERRED_VECTOR_WIDTH_FLOAT = 0x100A,
		CL_DEVICE_PREFERRED_VECTOR_WIDTH_DOUBLE = 0x100B,
		CL_DEVICE_MAX_CLOCK_FREQUENCY = 0x100C,
		CL_DEVICE_ADDRESS_BITS = 0x100D,
		CL_DEVICE_MAX_READ_IMAGE_ARGS = 0x100E,
		CL_DEVICE_MAX_WRITE_IMAGE_ARGS = 0x100F,
		CL_DEVICE_MAX_MEM_ALLOC_SIZE = 0x1010,
		CL_DEVICE_IMAGE2D_MAX_WIDTH = 0x1011,
		CL_DEVICE_IMAGE2D_MAX_HEIGHT = 0x1012,
		CL_DEVICE_IMAGE3D_MAX_WIDTH = 0x1013,
		CL_DEVICE_IMAGE3D_MAX_HEIGHT = 0x1014,
		CL_DEVICE_IMAGE3D_MAX_DEPTH = 0x1015,
		CL_DEVICE_IMAGE_SUPPORT = 0x1016,
		CL_DEVICE_MAX_PARAMETER_SIZE = 0x1017,
		CL_DEVICE_MAX_SAMPLERS = 0x1018,
		CL_DEVICE_MEM_BASE_ADDR_ALIGN = 0x1019,
		CL_DEVICE_MIN_DATA_TYPE_ALIGN_SIZE = 0x101A,
		CL_DEVICE_SINGLE_FP_CONFIG = 0x101B,
		CL_DEVICE_GLOBAL_MEM_CACHE_TYPE = 0x101C,
		CL_DEVICE_GLOBAL_MEM_CACHELINE_SIZE = 0x101D,
		CL_DEVICE_GLOBAL_MEM_CACHE_SIZE = 0x101E,
		CL_DEVICE_GLOBAL_MEM_SIZE = 0x101F,
		CL_DEVICE_MAX_CONSTANT_BUFFER_SIZE = 0x1020,
		CL_DEVICE_MAX_CONSTANT_ARGS = 0x1021,
		CL_DEVICE_LOCAL_MEM_TYPE = 0x1022,
		CL_DEVICE_LOCAL_MEM_SIZE = 0x1023,
		CL_DEVICE_ERROR_CORRECTION_SUPPORT = 0x1024,
		CL_DEVICE_PROFILING_TIMER_RESOLUTION = 0x1025,
		CL_DEVICE_ENDIAN_LITTLE = 0x1026,
		CL_DEVICE_AVAILABLE = 0x1027,
		CL_DEVICE_COMPILER_AVAILABLE = 0x1028,
		CL_DEVICE_EXECUTION_CAPABILITIES = 0x1029,
		CL_DEVICE_QUEUE_PROPERTIES = 0x102A,
		CL_DEVICE_NAME = 0x102B,
		CL_DEVICE_VENDOR = 0x102C,
		CL_DRIVER_VERSION = 0x102D,
		CL_DEVICE_PROFILE = 0x102E,
		CL_DEVICE_VERSION = 0x102F,
		CL_DEVICE_EXTENSIONS = 0x1030,
		CL_DEVICE_PLATFORM = 0x1031;
	/* 0x1032 reserved for CL_DEVICE_DOUBLE_FP_CONFIG */
	/* 0x1033 reserved for CL_DEVICE_HALF_FP_CONFIG */

	/** cl_device_fp_config - bitfield */
	int CL_FP_DENORM = (1 << 0),
		CL_FP_INF_NAN = (1 << 1),
		CL_FP_ROUND_TO_NEAREST = (1 << 2),
		CL_FP_ROUND_TO_ZERO = (1 << 3),
		CL_FP_ROUND_TO_INF = (1 << 4),
		CL_FP_FMA = (1 << 5);

	/** cl_device_mem_cache_type */
	int CL_NONE = 0x0,
		CL_READ_ONLY_CACHE = 0x1,
		CL_READ_WRITE_CACHE = 0x2;

	/** cl_device_local_mem_type */
	int CL_LOCAL = 0x1,
		CL_GLOBAL = 0x2;

	/** cl_device_exec_capabilities - bitfield */
	int CL_EXEC_KERNEL = (1 << 0),
		CL_EXEC_NATIVE_KERNEL = (1 << 1);

	/** cl_command_queue_properties - bitfield */
	int CL_QUEUE_OUT_OF_ORDER_EXEC_MODE_ENABLE = (1 << 0),
		CL_QUEUE_PROFILING_ENABLE = (1 << 1);

	/** cl_context_info */
	int CL_CONTEXT_REFERENCE_COUNT = 0x1080,
		CL_CONTEXT_DEVICES = 0x1081,
		CL_CONTEXT_PROPERTIES = 0x1082;

	/** cl_context_info + cl_context_properties */
	int CL_CONTEXT_PLATFORM = 0x1084;

	/** cl_command_queue_info */
	int CL_QUEUE_CONTEXT = 0x1090,
		CL_QUEUE_DEVICE = 0x1091,
		CL_QUEUE_REFERENCE_COUNT = 0x1092,
		CL_QUEUE_PROPERTIES = 0x1093;

	/** cl_mem_flags - bitfield */
	int CL_MEM_READ_WRITE = (1 << 0),
		CL_MEM_WRITE_ONLY = (1 << 1),
		CL_MEM_READ_ONLY = (1 << 2),
		CL_MEM_USE_HOST_PTR = (1 << 3),
		CL_MEM_ALLOC_HOST_PTR = (1 << 4),
		CL_MEM_COPY_HOST_PTR = (1 << 5);

	/** cl_channel_order */
	int CL_R = 0x10B0,
		CL_A = 0x10B1,
		CL_RG = 0x10B2,
		CL_RA = 0x10B3,
		CL_RGB = 0x10B4,
		CL_RGBA = 0x10B5,
		CL_BGRA = 0x10B6,
		CL_ARGB = 0x10B7,
		CL_INTENSITY = 0x10B8,
		CL_LUMINANCE = 0x10B9;

	/** cl_channel_type */
	int CL_SNORM_INT8 = 0x10D0,
		CL_SNORM_INT16 = 0x10D1,
		CL_UNORM_INT8 = 0x10D2,
		CL_UNORM_INT16 = 0x10D3,
		CL_UNORM_SHORT_565 = 0x10D4,
		CL_UNORM_SHORT_555 = 0x10D5,
		CL_UNORM_INT_101010 = 0x10D6,
		CL_SIGNED_INT8 = 0x10D7,
		CL_SIGNED_INT16 = 0x10D8,
		CL_SIGNED_INT32 = 0x10D9,
		CL_UNSIGNED_INT8 = 0x10DA,
		CL_UNSIGNED_INT16 = 0x10DB,
		CL_UNSIGNED_INT32 = 0x10DC,
		CL_HALF_FLOAT = 0x10DD,
		CL_FLOAT = 0x10DE;

	/** cl_mem_object_type */
	int CL_MEM_OBJECT_BUFFER = 0x10F0,
		CL_MEM_OBJECT_IMAGE2D = 0x10F1,
		CL_MEM_OBJECT_IMAGE3D = 0x10F2;

	/** cl_mem_info */
	int CL_MEM_TYPE = 0x1100,
		CL_MEM_FLAGS = 0x1101,
		CL_MEM_SIZE = 0x1102,
		CL_MEM_HOST_PTR = 0x1103,
		CL_MEM_MAP_COUNT = 0x1104,
		CL_MEM_REFERENCE_COUNT = 0x1105,
		CL_MEM_CONTEXT = 0x1106;

	/** cl_image_info */
	int CL_IMAGE_FORMAT = 0x1110,
		CL_IMAGE_ELEMENT_SIZE = 0x1111,
		CL_IMAGE_ROW_PITCH = 0x1112,
		CL_IMAGE_SLICE_PITCH = 0x1113,
		CL_IMAGE_WIDTH = 0x1114,
		CL_IMAGE_HEIGHT = 0x1115,
		CL_IMAGE_DEPTH = 0x1116;

	/** cl_addressing_mode */
	int CL_ADDRESS_NONE = 0x1130,
		CL_ADDRESS_CLAMP_TO_EDGE = 0x1131,
		CL_ADDRESS_CLAMP = 0x1132,
		CL_ADDRESS_REPEAT = 0x1133;

	/** cl_filter_mode */
	int CL_FILTER_NEAREST = 0x1140,
		CL_FILTER_LINEAR = 0x1141;

	/** cl_sampler_info */
	int CL_SAMPLER_REFERENCE_COUNT = 0x1150,
		CL_SAMPLER_CONTEXT = 0x1151,
		CL_SAMPLER_NORMALIZED_COORDS = 0x1152,
		CL_SAMPLER_ADDRESSING_MODE = 0x1153,
		CL_SAMPLER_FILTER_MODE = 0x1154;

	/** cl_map_flags - bitfield */
	int CL_MAP_READ = (1 << 0),
		CL_MAP_WRITE = (1 << 1);

	/** cl_program_info */
	int CL_PROGRAM_REFERENCE_COUNT = 0x1160,
		CL_PROGRAM_CONTEXT = 0x1161,
		CL_PROGRAM_NUM_DEVICES = 0x1162,
		CL_PROGRAM_DEVICES = 0x1163,
		CL_PROGRAM_SOURCE = 0x1164,
		CL_PROGRAM_BINARY_SIZES = 0x1165,
		CL_PROGRAM_BINARIES = 0x1166;

	/** cl_program_build_info */
	int CL_PROGRAM_BUILD_STATUS = 0x1181,
		CL_PROGRAM_BUILD_OPTIONS = 0x1182,
		CL_PROGRAM_BUILD_LOG = 0x1183;

	/** cl_build_status */
	int CL_BUILD_SUCCESS = 0,
		CL_BUILD_NONE = -1,
		CL_BUILD_ERROR = -2,
		CL_BUILD_IN_PROGRESS = -3;

	/** cl_kernel_info */
	int CL_KERNEL_FUNCTION_NAME = 0x1190,
		CL_KERNEL_NUM_ARGS = 0x1191,
		CL_KERNEL_REFERENCE_COUNT = 0x1192,
		CL_KERNEL_CONTEXT = 0x1193,
		CL_KERNEL_PROGRAM = 0x1194;

	/** cl_kernel_work_group_info */
	int CL_KERNEL_WORK_GROUP_SIZE = 0x11B0,
		CL_KERNEL_COMPILE_WORK_GROUP_SIZE = 0x11B1,
		CL_KERNEL_LOCAL_MEM_SIZE = 0x11B2;

	/** cl_event_info */
	int CL_EVENT_COMMAND_QUEUE = 0x11D0,
		CL_EVENT_COMMAND_TYPE = 0x11D1,
		CL_EVENT_REFERENCE_COUNT = 0x11D2,
		CL_EVENT_COMMAND_EXECUTION_STATUS = 0x11D3;

	/** cl_command_type */
	int CL_COMMAND_NDRANGE_KERNEL = 0x11F0,
		CL_COMMAND_TASK = 0x11F1,
		CL_COMMAND_NATIVE_KERNEL = 0x11F2,
		CL_COMMAND_READ_BUFFER = 0x11F3,
		CL_COMMAND_WRITE_BUFFER = 0x11F4,
		CL_COMMAND_COPY_BUFFER = 0x11F5,
		CL_COMMAND_READ_IMAGE = 0x11F6,
		CL_COMMAND_WRITE_IMAGE = 0x11F7,
		CL_COMMAND_COPY_IMAGE = 0x11F8,
		CL_COMMAND_COPY_IMAGE_TO_BUFFER = 0x11F9,
		CL_COMMAND_COPY_BUFFER_TO_IMAGE = 0x11FA,
		CL_COMMAND_MAP_BUFFER = 0x11FB,
		CL_COMMAND_MAP_IMAGE = 0x11FC,
		CL_COMMAND_UNMAP_MEM_OBJECT = 0x11FD,
		CL_COMMAND_MARKER = 0x11FE,
		CL_COMMAND_ACQUIRE_GL_OBJECTS = 0x11FF,
		CL_COMMAND_RELEASE_GL_OBJECTS = 0x1200;

	/** command execution status */
	int CL_COMPLETE = 0x0,
		CL_RUNNING = 0x1,
		CL_SUBMITTED = 0x2,
		CL_QUEUED = 0x3;

	/** cl_profiling_info */
	int CL_PROFILING_COMMAND_QUEUED = 0x1280,
		CL_PROFILING_COMMAND_SUBMIT = 0x1281,
		CL_PROFILING_COMMAND_START = 0x1282,
		CL_PROFILING_COMMAND_END = 0x1283;

	/* Platform API */

	@Code(
		javaBeforeNative = "\t\tif ( num_platforms == null ) num_platforms = APIUtil.getBufferInt();",
		javaAfterNative = "\t\tif ( __result == CL_SUCCESS && platforms != null ) CLPlatform.registerCLPlatforms(platforms, num_platforms);"
	)
	@cl_int
	int clGetPlatformIDs(@AutoSize(value = "platforms", canBeNull = true) @cl_uint int num_entries,
	                     @OutParameter @Check(canBeNull = true) @NativeType("cl_platform_id") PointerBuffer platforms,
	                     @OutParameter @Check(value = "1", canBeNull = true) @cl_uint IntBuffer num_platforms);

	@cl_int
	int clGetPlatformInfo(@PointerWrapper(value = "cl_platform_id", canBeNull = true) CLPlatform platform,
	                      @NativeType("cl_platform_info") int param_name,
	                      @AutoSize(value = "param_value", canBeNull = true) @size_t long param_value_size,
	                      @OutParameter @Check(canBeNull = true) @cl_void ByteBuffer param_value,
	                      @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	@Code(
		javaBeforeNative = "\t\telse\n" +
		                   "\t\t\tnum_devices = APIUtil.getBufferInt();",
		javaAfterNative = "\t\tif ( __result == CL_SUCCESS && devices != null ) platform.registerCLDevices(devices, num_devices);"
	)
	@cl_int
	int clGetDeviceIDs(@PointerWrapper("cl_platform_id") CLPlatform platform,
	                   @NativeType("cl_device_type") long device_type,
	                   @AutoSize(value = "devices", canBeNull = true) @cl_uint int num_entries,
	                   @OutParameter @Check(canBeNull = true) @NativeType("cl_device_id") PointerBuffer devices,
	                   @OutParameter @Check(value = "1", canBeNull = true) @cl_uint IntBuffer num_devices);

	@cl_int
	int clGetDeviceInfo(@PointerWrapper("cl_device_id") CLDevice device,
	                    @NativeType("cl_device_info") int param_name,
	                    @AutoSize(value = "param_value", canBeNull = true) @size_t long param_value_size,
	                    @OutParameter @Check(canBeNull = true) @cl_void ByteBuffer param_value,
	                    @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	/** LWJGL requires CL_CONTEXT_PLATFORM to be present in the cl_context_properties buffer. */
	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = pfn_notify == null || pfn_notify.isCustom() ? 0 : CallbackUtil.createGlobalRef(pfn_notify);",
		// Associate context with the GlobalRef, so we can delete it later.
		javaFinally = "\t\t\tif ( __result != null ) __result.setContextCallback(user_data);"
	)
	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_context", params = "APIUtil.getCLPlatform(properties)")
	CLContext clCreateContext(@NullTerminated @Check("3") @Const @NativeType("cl_context_properties") PointerBuffer properties,
	                          @AutoSize("devices") @cl_uint int num_devices,
	                          @Check("1") @Const @NativeType("cl_device_id") PointerBuffer devices,
	                          @PointerWrapper(value = "cl_create_context_callback", canBeNull = true) CLContextCallback pfn_notify,
	                          @Constant("user_data") @PointerWrapper("void *") long user_data,
	                          @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	/** LWJGL requires CL_CONTEXT_PLATFORM to be present in the cl_context_properties buffer. */
	@Alternate("clCreateContext")
	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = pfn_notify == null || pfn_notify.isCustom() ? 0 : CallbackUtil.createGlobalRef(pfn_notify);",
		// Associate context with the GlobalRef, so we can delete it later.
		javaFinally = "\t\t\tif ( __result != null ) __result.setContextCallback(user_data);"
	)
	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_context", params = "APIUtil.getCLPlatform(properties)")
	CLContext clCreateContext(@NullTerminated @Check("3") @Const @NativeType("cl_context_properties") PointerBuffer properties,
	                          @Constant("1") @cl_uint int num_devices,
	                          @Constant(value = "APIUtil.getPointer(device)", keepParam = true) CLDevice device,
	                          @PointerWrapper(value = "cl_create_context_callback", canBeNull = true) CLContextCallback pfn_notify,
	                          @Constant("user_data") @PointerWrapper("void *") long user_data,
	                          @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	/** LWJGL requires CL_CONTEXT_PLATFORM to be present in the cl_context_properties buffer. */
	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = pfn_notify == null || pfn_notify.isCustom() ? 0 : CallbackUtil.createGlobalRef(pfn_notify);",
		// Associate context with the GlobalRef, so we can delete it later.
		javaFinally = "\t\t\tif ( __result != null ) __result.setContextCallback(user_data);"
	)
	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_context", params = "APIUtil.getCLPlatform(properties)")
	CLContext clCreateContextFromType(@NullTerminated @Check("3") @Const @NativeType("cl_context_properties") PointerBuffer properties,
	                                  @NativeType("cl_device_type") long device_type,
	                                  @PointerWrapper(value = "cl_create_context_callback", canBeNull = true) CLContextCallback pfn_notify,
	                                  @Constant("user_data") @PointerWrapper("void *") long user_data,
	                                  @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) context.retain();")
	@cl_int
	int clRetainContext(@PointerWrapper("cl_context") CLContext context);

	@Code(
		javaBeforeNative = "\t\tAPIUtil.releaseObjects(context);",
		javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) context.releaseImpl();"
	)
	@cl_int
	int clReleaseContext(@PointerWrapper("cl_context") CLContext context);

	@Code(
		javaBeforeNative = "\t\tif ( param_value_size_ret == null && APIUtil.isDevicesParam(param_name) ) param_value_size_ret = APIUtil.getBufferPointer();",
		javaAfterNative = "\t\tif ( __result == CL_SUCCESS && param_value != null && APIUtil.isDevicesParam(param_name) ) context.getParent().registerCLDevices(param_value, param_value_size_ret);"
	)
	@cl_int
	int clGetContextInfo(@PointerWrapper("cl_context") CLContext context,
	                     @NativeType("cl_context_info") int param_name,
	                     @AutoSize(value = "param_value", canBeNull = true) @size_t long param_value_size,
	                     @OutParameter @Check(canBeNull = true) @cl_void ByteBuffer param_value,
	                     @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_command_queue", params = "context, device")
	CLCommandQueue clCreateCommandQueue(@PointerWrapper("cl_context") CLContext context,
	                                    @PointerWrapper("cl_device_id") CLDevice device,
	                                    @NativeType("cl_command_queue_properties") long properties,
	                                    @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) command_queue.retain();")
	@cl_int
	int clRetainCommandQueue(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue);

	@Code(
		javaBeforeNative = "\t\tAPIUtil.releaseObjects(command_queue);",
		javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) command_queue.release();"
	)
	@cl_int
	int clReleaseCommandQueue(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue);

	@cl_int
	int clGetCommandQueueInfo(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                          @NativeType("cl_command_queue_info") int param_name,
	                          @AutoSize(value = "param_value", canBeNull = true) @size_t long param_value_size,
	                          @OutParameter @Check(canBeNull = true) @cl_void ByteBuffer param_value,
	                          @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	@GenerateAutos
	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_mem", params = "context")
	CLMem clCreateBuffer(@PointerWrapper("cl_context") CLContext context,
	                     @NativeType("cl_mem_flags") long flags,
	                     @AutoSize("host_ptr") @size_t long size,
	                     @cl_byte
	                     @cl_short
	                     @cl_int
	                     @cl_long
	                     @cl_float
	                     @cl_double Buffer host_ptr,
	                     @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueReadBuffer(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                        @PointerWrapper("cl_mem") CLMem buffer,
	                        @cl_bool int blocking_read,
	                        @size_t long offset,
	                        @AutoSize("ptr") @size_t long size,
	                        @OutParameter
	                        @cl_byte
	                        @cl_short
	                        @cl_int
	                        @cl_long
	                        @cl_float
	                        @cl_double Buffer ptr,
	                        @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                        @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                        @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueWriteBuffer(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                         @PointerWrapper("cl_mem") CLMem buffer,
	                         @cl_bool int blocking_write,
	                         @size_t long offset,
	                         @AutoSize("ptr") @size_t long size,
	                         @Const
	                         @cl_byte
	                         @cl_short
	                         @cl_int
	                         @cl_long
	                         @cl_float
	                         @cl_double Buffer ptr,
	                         @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                         @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                         @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueCopyBuffer(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                        @PointerWrapper("cl_mem") CLMem src_buffer,
	                        @PointerWrapper("cl_mem") CLMem dst_buffer,
	                        @size_t long src_offset,
	                        @size_t long dst_offset,
	                        @size_t long size,
	                        @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                        @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                        @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Code(javaAfterNative = "\t\tif ( __result != null ) command_queue.registerCLEvent(event);")
	@Check(value = "errcode_ret", canBeNull = true)
	@cl_void
	@AutoSize("size")
	ByteBuffer clEnqueueMapBuffer(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                              @PointerWrapper("cl_mem") CLMem buffer,
	                              @cl_bool int blocking_map,
	                              @NativeType("cl_map_flags") long map_flags,
	                              @size_t long offset,
	                              @size_t long size,
	                              @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                              @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                              @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event,
	                              @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_mem", params = "context")
	CLMem clCreateImage2D(@PointerWrapper("cl_context") CLContext context,
	                      @NativeType("cl_mem_flags") long flags,
	                      @Check("2 * 4") @Const @NativeType("cl_image_format") ByteBuffer image_format,
	                      @size_t long image_width,
	                      @size_t long image_height,
	                      @size_t long image_row_pitch,
	                      @Check(value = "CLChecks.calculateImage2DSize(host_ptr, image_format, image_width, image_height, image_row_pitch)", canBeNull = true)
	                      @cl_byte
	                      @cl_short
	                      @cl_int
	                      @cl_float Buffer host_ptr,
	                      @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_mem", params = "context")
	CLMem clCreateImage3D(@PointerWrapper("cl_context") CLContext context,
	                      @NativeType("cl_mem_flags") long flags,
	                      @Check("2 * 4") @Const @NativeType("cl_image_format") ByteBuffer image_format,
	                      @size_t long image_width,
	                      @size_t long image_height,
	                      @size_t long image_depth,
	                      @size_t long image_row_pitch,
	                      @size_t long image_slice_pitch,
	                      @Check(value = "CLChecks.calculateImage3DSize(host_ptr, image_format, image_width, image_height, image_height, image_row_pitch, image_slice_pitch)", canBeNull = true)
	                      @cl_byte
	                      @cl_short
	                      @cl_int
	                      @cl_float Buffer host_ptr,
	                      @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@cl_int
	int clGetSupportedImageFormats(@PointerWrapper("cl_context") CLContext context,
	                               @NativeType("cl_mem_flags") long flags,
	                               @NativeType("cl_mem_object_type") int image_type,
	                               @AutoSize(value = "image_formats", expression = " / (2 * 4)", canBeNull = true) @cl_uint int num_entries,
	                               @OutParameter @Check(canBeNull = true) @NativeType("cl_image_format") ByteBuffer image_formats,
	                               @OutParameter @Check(value = "1", canBeNull = true) @cl_uint IntBuffer num_image_formats);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueReadImage(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                       @PointerWrapper("cl_mem") CLMem image,
	                       @cl_bool int blocking_read,
	                       @Check("3") @Const @NativeType("size_t") PointerBuffer origin,
	                       @Check("3") @Const @NativeType("size_t") PointerBuffer region,
	                       @size_t long row_pitch,
	                       @size_t long slice_pitch,
	                       @OutParameter @Check("CLChecks.calculateImageSize(region, row_pitch, slice_pitch)")
	                       @cl_byte
	                       @cl_short
	                       @cl_int
	                       @cl_float Buffer ptr,
	                       @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                       @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                       @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueWriteImage(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                        @PointerWrapper("cl_mem") CLMem image,
	                        @cl_bool int blocking_write,
	                        @Check("3") @Const @NativeType("size_t") PointerBuffer origin,
	                        @Check("3") @Const @NativeType("size_t") PointerBuffer region,
	                        @size_t long input_row_pitch,
	                        @size_t long input_slice_pitch,
	                        @Check("CLChecks.calculateImageSize(region, input_row_pitch, input_slice_pitch)") @Const
	                        @cl_byte
	                        @cl_short
	                        @cl_int
	                        @cl_float Buffer ptr,
	                        @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                        @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                        @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueCopyImage(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                       @PointerWrapper("cl_mem") CLMem src_image,
	                       @PointerWrapper("cl_mem") CLMem dst_image,
	                       @Check("3") @Const @NativeType("size_t") PointerBuffer src_origin,
	                       @Check("3") @Const @NativeType("size_t") PointerBuffer dst_origin,
	                       @Check("3") @Const @NativeType("size_t") PointerBuffer region,
	                       @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                       @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                       @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueCopyImageToBuffer(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                               @PointerWrapper("cl_mem") CLMem src_image,
	                               @PointerWrapper("cl_mem") CLMem dst_buffer,
	                               @Check("3") @Const @NativeType("size_t") PointerBuffer src_origin,
	                               @Check("3") @Const @NativeType("size_t") PointerBuffer region,
	                               @size_t long dst_offset,
	                               @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                               @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                               @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueCopyBufferToImage(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                               @PointerWrapper("cl_mem") CLMem src_buffer,
	                               @PointerWrapper("cl_mem") CLMem dst_image,
	                               @size_t long src_offset,
	                               @Check("3") @Const @NativeType("size_t") PointerBuffer dst_origin,
	                               @Check("3") @Const @NativeType("size_t") PointerBuffer region,
	                               @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                               @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                               @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Code(javaAfterNative = "\t\tif ( __result != null ) command_queue.registerCLEvent(event);")
	@Check(value = "errcode_ret", canBeNull = true)
	@cl_void
	@AutoSize(value = "extcl_CalculateImageSize(region_address, *image_row_pitch_address, image_slice_pitch_address == NULL ? 0 : *image_slice_pitch_address)", isNative = true)
	ByteBuffer clEnqueueMapImage(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                             @PointerWrapper("cl_mem") CLMem image,
	                             @cl_bool int blocking_map,
	                             @NativeType("cl_map_flags") long map_flags,
	                             @Check("3") @Const @NativeType("size_t") PointerBuffer origin,
	                             @Check("3") @Const @NativeType("size_t") PointerBuffer region,
	                             @OutParameter @Check("1") @NativeType("size_t") PointerBuffer image_row_pitch,
	                             @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer image_slice_pitch,
	                             @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                             @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                             @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event,
	                             @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@cl_int
	int clGetImageInfo(@PointerWrapper("cl_mem") CLMem image,
	                   @NativeType("cl_image_info") int param_name,
	                   @AutoSize(value = "param_value", canBeNull = true) @size_t long param_value_size,
	                   @OutParameter @Check(canBeNull = true) @cl_void ByteBuffer param_value,
	                   @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) memobj.retain();")
	@cl_int
	int clRetainMemObject(@PointerWrapper("cl_mem") CLMem memobj);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) memobj.release();")
	@cl_int
	int clReleaseMemObject(@PointerWrapper("cl_mem") CLMem memobj);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueUnmapMemObject(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                            @PointerWrapper("cl_mem") CLMem memobj,
	                            @Check @cl_void ByteBuffer mapped_ptr,
	                            @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                            @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                            @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@cl_int
	int clGetMemObjectInfo(@PointerWrapper("cl_mem") CLMem memobj,
	                       @NativeType("cl_mem_info") int param_name,
	                       @AutoSize(value = "param_value", canBeNull = true) @size_t long param_value_size,
	                       @OutParameter @Check(canBeNull = true) @cl_void ByteBuffer param_value,
	                       @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_sampler", params = "context")
	CLSampler clCreateSampler(@PointerWrapper("cl_context") CLContext context,
	                          @cl_bool int normalized_coords,
	                          @NativeType("cl_addressing_mode") int addressing_mode,
	                          @NativeType("cl_filter_mode") int filter_mode,
	                          @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) sampler.retain();")
	@cl_int
	int clRetainSampler(@PointerWrapper("cl_sampler") CLSampler sampler);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) sampler.release();")
	@cl_int
	int clReleaseSampler(@PointerWrapper("cl_sampler") CLSampler sampler);

	@cl_int
	int clGetSamplerInfo(@PointerWrapper("cl_sampler") CLSampler sampler,
	                     @NativeType("cl_sampler_info") int param_name,
	                     @AutoSize(value = "param_value", canBeNull = true) @size_t long param_value_size,
	                     @OutParameter @Check(canBeNull = true) @cl_void ByteBuffer param_value,
	                     @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	// ------[ clCreateProgramWithSource ]------

	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_program", params = "context")
	CLProgram clCreateProgramWithSource(@PointerWrapper("cl_context") CLContext context,
	                                    @Constant("1") @cl_uint int count,
	                                    @Check @Indirect @Const @cl_char ByteBuffer string,
	                                    @AutoSize("string") @Indirect @Const @size_t long lengths,
	                                    @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Alternate(value = "clCreateProgramWithSource", nativeAlt = true)
	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_program", params = "context")
	CLProgram clCreateProgramWithSource2(@PointerWrapper("cl_context") CLContext context,
	                                     @AutoSize("lengths") @cl_uint int count,
	                                     @Check("APIUtil.getSize(lengths)") @PointerArray(value = "count", lengths = "lengths") @Const @NativeType("cl_char") ByteBuffer strings,
	                                     @Check("1") @Const @NativeType("size_t") PointerBuffer lengths,
	                                     @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Alternate(value = "clCreateProgramWithSource", nativeAlt = true)
	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_program", params = "context")
	CLProgram clCreateProgramWithSource3(@PointerWrapper("cl_context") CLContext context,
	                                     @Constant("strings.length") @cl_uint int count,
	                                     @Check("1") @PointerArray(value = "count") @Const @NativeType("cl_char") ByteBuffer[] strings,
	                                     @Constant("APIUtil.getLengths(strings)") @Const @NativeType("size_t") PointerBuffer lengths,
	                                     @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Alternate("clCreateProgramWithSource")
	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_program", params = "context")
	CLProgram clCreateProgramWithSource(@PointerWrapper("cl_context") CLContext context,
	                                    @Constant("1") @cl_uint int count,
	                                    CharSequence string,
	                                    @Constant("string.length()") @Indirect @Const @size_t long lengths,
	                                    @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Alternate(value = "clCreateProgramWithSource", nativeAlt = true)
	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_program", params = "context")
	CLProgram clCreateProgramWithSource4(@PointerWrapper("cl_context") CLContext context,
	                                     @Constant("strings.length") @cl_uint int count,
	                                     @Const @PointerArray(value = "count", lengths = "lengths") CharSequence[] strings,
	                                     @Constant("APIUtil.getLengths(strings)") @Const @NativeType("size_t") PointerBuffer lengths,
	                                     @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	// ------[ clCreateProgramWithBinary ]------

	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_program", params = "context")
	CLProgram clCreateProgramWithBinary(@PointerWrapper("cl_context") CLContext context,
	                                    @Constant("1") @cl_uint int num_devices,
	                                    @Const @Indirect @PointerWrapper("cl_device_id") CLDevice device,
	                                    @AutoSize("binary") @Const @Indirect @size_t long lengths,
	                                    @Const @Indirect @cl_uchar ByteBuffer binary,
	                                    @OutParameter @Check("1") @cl_int IntBuffer binary_status,
	                                    @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Alternate(value = "clCreateProgramWithBinary", nativeAlt = true)
	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_program", params = "context")
	CLProgram clCreateProgramWithBinary2(@PointerWrapper("cl_context") CLContext context,
	                                     @AutoSize("device_list") @cl_uint int num_devices,
	                                     @Check("1") @Const @NativeType("cl_device_id") PointerBuffer device_list,
	                                     @Check("device_list.remaining()") @Const @NativeType("size_t") PointerBuffer lengths,
	                                     @Check("APIUtil.getSize(lengths)") @PointerArray(value = "num_devices", lengths = "lengths") @Const @NativeType("cl_uchar") ByteBuffer binaries,
	                                     @OutParameter @Check("device_list.remaining()") @cl_int IntBuffer binary_status,
	                                     @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Alternate(value = "clCreateProgramWithBinary", nativeAlt = true)
	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_program", params = "context")
	CLProgram clCreateProgramWithBinary3(@PointerWrapper("cl_context") CLContext context,
	                                     @Constant("binaries.length") @cl_uint int num_devices,
	                                     @Check("binaries.length") @Const @NativeType("cl_device_id") PointerBuffer device_list,
	                                     @Constant("APIUtil.getLengths(binaries)") @Const @NativeType("size_t") PointerBuffer lengths,
	                                     @Check("1") @PointerArray("num_devices") @Const @NativeType("cl_uchar") ByteBuffer[] binaries,
	                                     @OutParameter @Check("binaries.length") @cl_int IntBuffer binary_status,
	                                     @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) program.retain();")
	@cl_int
	int clRetainProgram(@PointerWrapper("cl_program") CLProgram program);

	@Code(
		javaBeforeNative = "\t\tAPIUtil.releaseObjects(program);",
		javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) program.release();"
	)
	@cl_int
	int clReleaseProgram(@PointerWrapper("cl_program") CLProgram program);

	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = CallbackUtil.createGlobalRef(pfn_notify);\n" +
		                   "\t\tif ( pfn_notify != null ) pfn_notify.setContext(program.getParent());",
		// Check if we need to delete the GlobalRef.
		javaFinally = "\t\t\tCallbackUtil.checkCallback(__result, user_data);"
	)
	@cl_int
	int clBuildProgram(@PointerWrapper("cl_program") CLProgram program,
	                   @AutoSize(value = "device_list", canBeNull = true) @cl_uint int num_devices,
	                   @Check(canBeNull = true) @Const @NativeType("cl_device_id") PointerBuffer device_list,
	                   @Check @NullTerminated @Const @cl_char ByteBuffer options,
	                   @PointerWrapper(value = "cl_program_callback", canBeNull = true) CLBuildProgramCallback pfn_notify,
	                   @Constant("user_data") @PointerWrapper("void *") long user_data);

	@Alternate("clBuildProgram")
	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = CallbackUtil.createGlobalRef(pfn_notify);\n" +
		                   "\t\tif ( pfn_notify != null ) pfn_notify.setContext(program.getParent());",
		// Check if we need to delete the GlobalRef.
		javaFinally = "\t\t\tCallbackUtil.checkCallback(__result, user_data);"
	)
	@cl_int
	int clBuildProgram(@PointerWrapper("cl_program") CLProgram program,
	                   @AutoSize(value = "device_list", canBeNull = true) @cl_uint int num_devices,
	                   @Check(canBeNull = true) @Const @NativeType("cl_device_id") PointerBuffer device_list,
	                   @NullTerminated @Const CharSequence options,
	                   @PointerWrapper(value = "cl_program_callback", canBeNull = true) CLBuildProgramCallback pfn_notify,
	                   @Constant("user_data") @PointerWrapper("void *") long user_data);

	@Alternate("clBuildProgram")
	@Code(
		tryBlock = true,
		// Create a GlobalRef to the callback object.
		javaBeforeNative = "\t\tlong user_data = CallbackUtil.createGlobalRef(pfn_notify);\n" +
		                   "\t\tif ( pfn_notify != null ) pfn_notify.setContext(program.getParent());",
		// Check if we need to delete the GlobalRef.
		javaFinally = "\t\t\tCallbackUtil.checkCallback(__result, user_data);"
	)
	@cl_int
	int clBuildProgram(@PointerWrapper("cl_program") CLProgram program,
	                   @Constant("1") @cl_uint int num_devices,
	                   @Constant(value = "APIUtil.getPointer(device)", keepParam = true) CLDevice device,
	                   @NullTerminated @Const CharSequence options,
	                   @PointerWrapper(value = "cl_program_callback", canBeNull = true) CLBuildProgramCallback pfn_notify,
	                   @Constant("user_data") @PointerWrapper("void *") long user_data);

	@cl_int
	int clUnloadCompiler();

	@cl_int
	int clGetProgramInfo(@PointerWrapper("cl_program") CLProgram program,
	                     @NativeType("cl_program_info") int param_name,
	                     @AutoSize(value = "param_value", canBeNull = true) @size_t long param_value_size,
	                     @OutParameter @Check(canBeNull = true) @cl_void ByteBuffer param_value,
	                     @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	/**
	 * This method can be used to get program binaries. The binary for each device (in the
	 * order returned by <code>CL_PROGRAM_DEVICES</code>) will be written sequentially to
	 * the <code>param_value</code> buffer. The buffer size must be big enough to hold
	 * all the binaries, as returned by <code>CL_PROGRAM_BINARY_SIZES</code>.
	 *
	 * @param program              the program
	 * @param param_value          the buffers where the binaries will be written to.
	 * @param param_value_size_ret optional size result
	 *
	 * @return the error code
	 */
	@Alternate(value = "clGetProgramInfo", nativeAlt = true)
	@cl_int
	int clGetProgramInfo2(@PointerWrapper("cl_program") CLProgram program,
	                      @Constant("CL_PROGRAM_BINARIES") @NativeType("cl_program_info") int param_name,
	                      @Constant(value = "sizes_len * sizeof(cl_uchar *)", isNative = true) @size_t long param_value_size,
	                      @Constant("sizes.remaining()") @Helper(passToNative = true) @size_t long sizes_len,
	                      @Helper(passToNative = true) @Check("1") @Const @NativeType("size_t") PointerBuffer sizes,
	                      @OutParameter @Check("APIUtil.getSize(sizes)") @PointerArray(value = "sizes_len", lengths = "sizes") @NativeType("cl_uchar") ByteBuffer param_value,
	                      @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	/**
	 * This method can be used to get program binaries. The binary for each device (in the
	 * order returned by <code>CL_PROGRAM_DEVICES</code>) will be written to the corresponding
	 * slot of the <code>param_value</code> array. The size of each buffer must be big enough to
	 * hold the corresponding binary, as returned by <code>CL_PROGRAM_BINARY_SIZES</code>.
	 *
	 * @param program              the program
	 * @param param_value          the buffers where the binaries will be written to.
	 * @param param_value_size_ret optional size result
	 *
	 * @return the error code
	 */
	@Alternate(value = "clGetProgramInfo", nativeAlt = true)
	@cl_int
	int clGetProgramInfo3(@PointerWrapper("cl_program") CLProgram program,
	                      @Constant("CL_PROGRAM_BINARIES") @NativeType("cl_program_info") int param_name,
	                      @Constant(value = "param_value_len * sizeof(cl_uchar *)", isNative = true) @size_t long param_value_size,
	                      @Constant("param_value.length") @Helper(passToNative = true) @size_t long param_value_len,
	                      @PointerArray("param_value_len") @NativeType("cl_uchar") ByteBuffer[] param_value,
	                      @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	@cl_int
	int clGetProgramBuildInfo(@PointerWrapper("cl_program") CLProgram program,
	                          @PointerWrapper("cl_device_id") CLDevice device,
	                          @NativeType("cl_program_build_info") int param_name,
	                          @AutoSize(value = "param_value", canBeNull = true) @size_t long param_value_size,
	                          @OutParameter @Check(canBeNull = true) @cl_void ByteBuffer param_value,
	                          @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_kernel", params = "program")
	CLKernel clCreateKernel(@PointerWrapper("cl_program") CLProgram program,
	                        @NullTerminated @Const @cl_char ByteBuffer kernel_name,
	                        @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Alternate("clCreateKernel")
	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_kernel", params = "program")
	CLKernel clCreateKernel(@PointerWrapper("cl_program") CLProgram program,
	                        @NullTerminated @Const CharSequence kernel_name,
	                        @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS && kernels != null ) program.registerCLKernels(kernels);")
	@cl_int
	int clCreateKernelsInProgram(@PointerWrapper("cl_program") CLProgram program,
	                             @AutoSize(value = "kernels", canBeNull = true) @cl_uint int num_kernels,
	                             @OutParameter @Check(canBeNull = true) @NativeType("cl_kernel") PointerBuffer kernels,
	                             @OutParameter @Check(value = "1", canBeNull = true) @cl_uint IntBuffer num_kernels_ret);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) kernel.retain();")
	@cl_int
	int clRetainKernel(@PointerWrapper("cl_kernel") CLKernel kernel);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) kernel.release();")
	@cl_int
	int clReleaseKernel(@PointerWrapper("cl_kernel") CLKernel kernel);

	@GenerateAutos
	@cl_int
	int clSetKernelArg(@PointerWrapper("cl_kernel") CLKernel kernel,
	                   @cl_uint int arg_index,
	                   @AutoSize("arg_value") @size_t long arg_size,
	                   @Const
	                   @cl_byte
	                   @cl_short
	                   @cl_int
	                   @cl_long
	                   @cl_float
	                   @cl_double Buffer arg_value);

	@Alternate("clSetKernelArg")
	@cl_int
	int clSetKernelArg(@PointerWrapper("cl_kernel") CLKernel kernel,
	                   @cl_uint int arg_index,
	                   @Constant("PointerBuffer.getPointerSize()") @size_t long arg_size,
	                   @Check(canBeNull = true) @Const
	                   @Constant(value = "APIUtil.getPointerSafe(arg_value)", keepParam = true) CLObject arg_value);

	// This is used by CLKernelUtil. Assumes arg_value.position() == 0.

	@Alternate("clSetKernelArg")
	@Private
	@cl_int
	int clSetKernelArg3(@PointerWrapper("cl_kernel") CLKernel kernel,
	                    @cl_uint int arg_index,
	                    @size_t long arg_size,
	                    @Constant(value = "MemoryUtil.getAddress0(arg_value)", keepParam = true) Buffer arg_value);

	@cl_int
	int clGetKernelInfo(@PointerWrapper("cl_kernel") CLKernel kernel,
	                    @NativeType("cl_kernel_info") int param_name,
	                    @AutoSize(value = "param_value", canBeNull = true) @size_t long param_value_size,
	                    @OutParameter @Check(canBeNull = true) @cl_void ByteBuffer param_value,
	                    @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	@cl_int
	int clGetKernelWorkGroupInfo(@PointerWrapper("cl_kernel") CLKernel kernel,
	                             @PointerWrapper(value = "cl_device_id", canBeNull = true) CLDevice device,
	                             @NativeType("cl_kernel_work_group_info") int param_name,
	                             @AutoSize(value = "param_value", canBeNull = true) @size_t long param_value_size,
	                             @OutParameter @Check(canBeNull = true) @cl_void ByteBuffer param_value,
	                             @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueNDRangeKernel(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                           @PointerWrapper("cl_kernel") CLKernel kernel,
	                           @cl_uint int work_dim,
	                           @Check(value = "work_dim", canBeNull = true) @Const @NativeType("size_t") PointerBuffer global_work_offset,
	                           @Check(value = "work_dim", canBeNull = true) @Const @NativeType("size_t") PointerBuffer global_work_size,
	                           @Check(value = "work_dim", canBeNull = true) @Const @NativeType("size_t") PointerBuffer local_work_size,
	                           @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                           @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                           @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueTask(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                  @PointerWrapper("cl_kernel") CLKernel kernel,
	                  @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                  @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                  @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	/**
	 * Enqueues a native kernel to the specified command queue. The <code>mem_list</code> parameter
	 * can be used to pass a list of <code>CLMem</code> objects that will be mapped to global memory space and
	 * exposed as a <code>ByteBuffer</code> array in the <code>CLNativeKernel#execute</code> method. The
	 * <code>sizes</code> parameter will be used to allocate direct <code>ByteBuffer</code>s with the correct
	 * capacities. The user is responsible for passing appropriate values to avoid crashes.
	 *
	 * @param command_queue   the command queue
	 * @param user_func       the native kernel
	 * @param mem_list        the CLMem objects
	 * @param sizes           the CLMem object sizes
	 * @param event_wait_list the event wait list
	 * @param event           the queue event
	 *
	 * @return the error code
	 */
	@Code(
		tryBlock = true,
		// Build the args buffer and create a GlobalRef to the user_func object.
		javaBeforeNative = "\t\tlong user_func_ref = CallbackUtil.createGlobalRef(user_func);\n" +
		                   "\t\tByteBuffer args = APIUtil.getNativeKernelArgs(user_func_ref, mem_list, sizes);",
		// Register CLEvent
		javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) command_queue.registerCLEvent(event);",
		// Check if we need to delete the GlobalRef.
		javaFinally = "\t\t\tCallbackUtil.checkCallback(__result, user_func_ref);",
		nativeAfterVars = "\tvoid **args_mem_loc = num_mem_objects == 0 ? NULL : (void **)malloc(num_mem_objects * sizeof(void *));",
		nativeBeforeCall = "\t_ptr_i = 0;\n" +
		                   "\twhile ( _ptr_i < num_mem_objects ) {\n" +
		                   "\t\targs_mem_loc[_ptr_i] = (cl_void *)((char *)args_address + (12 + 4 + _ptr_i * (4 + sizeof(void *))));\n" +
		                   "\t\t_ptr_i++;\n" +
		                   "\t}",
		nativeAfterCall = "\tfree(args_mem_loc);"
	)
	@cl_int
	int clEnqueueNativeKernel(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                          @PointerWrapper("cl_native_kernel_func") CLNativeKernel user_func,
	                          @Constant("MemoryUtil.getAddress0(args)") @cl_void ByteBuffer args,
	                          @AutoSize("args") @size_t long cb_args,
	                          @Constant("mem_list == null ? 0 : mem_list.length") @cl_uint int num_mem_objects,
	                          @Check(value = "1", canBeNull = true) @PointerArray("num_mem_objects") @Const @NativeType("cl_mem") CLMem[] mem_list,
	                          @Check(value = "mem_list.length", canBeNull = true) @Helper long[] sizes,
	                          @Constant(value = "(const void**)args_mem_loc", isNative = true) @Const @Indirect @cl_void ByteBuffer args_mem_loc,
	                          @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                          @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                          @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@cl_int
	int clWaitForEvents(@AutoSize("event_list") @cl_uint int num_events,
	                    @Check("1") @Const @NativeType("cl_event") PointerBuffer event_list);

	@Alternate("clWaitForEvents")
	@cl_int
	int clWaitForEvents(@Constant("1") @cl_uint int num_events,
	                    @Constant(value = "APIUtil.getPointer(event)", keepParam = true) CLEvent event);

	@cl_int
	int clGetEventInfo(@PointerWrapper("cl_event") CLEvent event,
	                   @NativeType("cl_event_info") int param_name,
	                   @AutoSize(value = "param_value", canBeNull = true) @size_t long param_value_size,
	                   @OutParameter @Check(canBeNull = true) @cl_void ByteBuffer param_value,
	                   @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) event.retain();")
	@cl_int
	int clRetainEvent(@PointerWrapper("cl_event") CLEvent event);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) event.release();")
	@cl_int
	int clReleaseEvent(@PointerWrapper("cl_event") CLEvent event);

	@Code(javaAfterNative = "\t\tif ( __result == CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueMarker(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                    @OutParameter @Check("1") @NativeType("cl_event") PointerBuffer event);

	@cl_int
	int clEnqueueBarrier(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue);

	@cl_int
	int clEnqueueWaitForEvents(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                           @AutoSize("event_list") @cl_uint int num_events,
	                           @Check("1") @Const @NativeType("cl_event") PointerBuffer event_list);

	@Alternate("clEnqueueWaitForEvents")
	@cl_int
	int clEnqueueWaitForEvents(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                           @Constant("1") @cl_uint int num_events,
	                           @Constant(value = "APIUtil.getPointer(event)", keepParam = true) CLEvent event);

	@cl_int
	int clGetEventProfilingInfo(@PointerWrapper("cl_event") CLEvent event,
	                            @NativeType("cl_profiling_info") int param_name,
	                            @AutoSize(value = "param_value", canBeNull = true) @size_t long param_value_size,
	                            @OutParameter @Check(canBeNull = true) @cl_void ByteBuffer param_value,
	                            @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	@cl_int
	int clFlush(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue);

	@cl_int
	int clFinish(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue);

	@Private
	@PointerWrapper("void *")
	CLFunctionAddress clGetExtensionFunctionAddress(@NullTerminated @Const @cl_char ByteBuffer func_name);

	@Alternate("clGetExtensionFunctionAddress")
	@Private
	@PointerWrapper("void *")
	CLFunctionAddress clGetExtensionFunctionAddress(@NullTerminated CharSequence func_name);

}