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
import org.lwjgl.util.generator.opencl.cl_int;
import org.lwjgl.util.generator.opencl.cl_uint;
import org.lwjgl.util.generator.opencl.cl_void;
import org.lwjgl.util.generator.opencl.size_t;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/** The core OpenCL 1.0 OpenGL interrop functionality. */
public interface CL10GL {

	/** cl_gl_object_type */
	int CL_GL_OBJECT_BUFFER = 0x2000,
		CL_GL_OBJECT_TEXTURE2D = 0x2001,
		CL_GL_OBJECT_TEXTURE3D = 0x2002,
		CL_GL_OBJECT_RENDERBUFFER = 0x2003;

	/** cl_gl_texture_info */
	int CL_GL_TEXTURE_TARGET = 0x2004,
		CL_GL_MIPMAP_LEVEL = 0x2005;

	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_mem", params = "context")
	CLMem clCreateFromGLBuffer(@PointerWrapper("cl_context") CLContext context,
	                           @NativeType("cl_mem_flags") long flags,
	                           @NativeType("GLuint") int bufobj,
	                           @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_mem", params = "context")
	CLMem clCreateFromGLTexture2D(@PointerWrapper("cl_context") CLContext context,
	                              @NativeType("cl_mem_flags") long flags,
	                              @NativeType("GLenum") int target,
	                              @NativeType("GLint") int miplevel,
	                              @NativeType("GLuint") int texture,
	                              @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_mem", params = "context")
	CLMem clCreateFromGLTexture3D(@PointerWrapper("cl_context") CLContext context,
	                              @NativeType("cl_mem_flags") long flags,
	                              @NativeType("GLenum") int target,
	                              @NativeType("GLint") int miplevel,
	                              @NativeType("GLuint") int texture,
	                              @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@Check(value = "errcode_ret", canBeNull = true)
	@PointerWrapper(value = "cl_mem", params = "context")
	CLMem clCreateFromGLRenderbuffer(@PointerWrapper("cl_context") CLContext context,
	                                 @NativeType("cl_mem_flags") long flags,
	                                 @NativeType("GLuint") int renderbuffer,
	                                 @OutParameter @Check(value = "1", canBeNull = true) @cl_int IntBuffer errcode_ret);

	@cl_int
	int clGetGLObjectInfo(@PointerWrapper("cl_mem") CLMem memobj,
	                      @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_gl_object_type") IntBuffer gl_object_type,
	                      @OutParameter @Check(value = "1", canBeNull = true) @NativeType("GLuint") IntBuffer gl_object_name);

	@cl_int
	int clGetGLTextureInfo(@PointerWrapper("cl_mem") CLMem memobj,
	                       @NativeType("cl_gl_texture_info") int param_name,
	                       @AutoSize(value = "param_value", canBeNull = true) @size_t long param_value_size,
	                       @OutParameter @Check(canBeNull = true) @cl_void ByteBuffer param_value,
	                       @OutParameter @Check(value = "1", canBeNull = true) @NativeType("size_t") PointerBuffer param_value_size_ret);

	@Code(javaAfterNative = "\t\tif ( __result == CL10.CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueAcquireGLObjects(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                              @AutoSize("mem_objects") @cl_uint int num_objects,
	                              @Check("1") @Const @NativeType("cl_mem") PointerBuffer mem_objects,
	                              @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                              @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                              @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Alternate("clEnqueueAcquireGLObjects")
	@Code(javaAfterNative = "\t\tif ( __result == CL10.CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueAcquireGLObjects(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                              @Constant("1") @cl_uint int num_objects,
	                              @Constant(value = "APIUtil.getPointer(mem_object)", keepParam = true) CLMem mem_object,
	                              @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                              @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                              @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Code(javaAfterNative = "\t\tif ( __result == CL10.CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueReleaseGLObjects(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                              @AutoSize("mem_objects") @cl_uint int num_objects,
	                              @Check("1") @Const @NativeType("cl_mem") PointerBuffer mem_objects,
	                              @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                              @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                              @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);

	@Alternate("clEnqueueReleaseGLObjects")
	@Code(javaAfterNative = "\t\tif ( __result == CL10.CL_SUCCESS ) command_queue.registerCLEvent(event);")
	@cl_int
	int clEnqueueReleaseGLObjects(@PointerWrapper("cl_command_queue") CLCommandQueue command_queue,
	                              @Constant("1") @cl_uint int num_objects,
	                              @Constant(value = "APIUtil.getPointer(mem_object)", keepParam = true) CLMem mem_object,
	                              @AutoSize(value = "event_wait_list", canBeNull = true) @cl_uint int num_events_in_wait_list,
	                              @Check(canBeNull = true) @Const @NativeType("cl_event") PointerBuffer event_wait_list,
	                              @OutParameter @Check(value = "1", canBeNull = true) @NativeType("cl_event") PointerBuffer event);
}