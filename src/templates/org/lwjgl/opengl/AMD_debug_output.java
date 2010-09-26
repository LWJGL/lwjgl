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
package org.lwjgl.opengl;

import org.lwjgl.util.generator.*;
import org.lwjgl.util.generator.PointerWrapper;
import org.lwjgl.util.generator.Alias;
import org.lwjgl.util.generator.Alternate;
import org.lwjgl.util.generator.opengl.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

@Alias(value = "AMDX_debug_output", postfix = "X")
public interface AMD_debug_output {

	/** Tokens accepted by GetIntegerv: */
	int GL_MAX_DEBUG_MESSAGE_LENGTH_AMD = 0x9143,
		GL_MAX_DEBUG_LOGGED_MESSAGES_AMD = 0x9144,
		GL_DEBUG_LOGGED_MESSAGES_AMD = 0x9145;

	/**
	 * Tokens accepted by DebugMessageEnableAMD, GetDebugMessageLogAMD,
	 * DebugMessageInsertAMD, and DEBUGPROCAMD callback function
	 * for &lt;severity&gt;:
	 */
	int GL_DEBUG_SEVERITY_HIGH_AMD = 0x9146,
		GL_DEBUG_SEVERITY_MEDIUM_AMD = 0x9147,
		GL_DEBUG_SEVERITY_LOW_AMD = 0x9148;

	/**
	 * Tokens accepted by DebugMessageEnableAMD, GetDebugMessageLogAMD,
	 * and DEBUGPROCAMD callback function for &lt;category&gt;:
	 */
	int GL_DEBUG_CATEGORY_API_ERROR_AMD = 0x9149,
		GL_DEBUG_CATEGORY_WINDOW_SYSTEM_AMD = 0x914A,
		GL_DEBUG_CATEGORY_DEPRECATION_AMD = 0x914B,
		GL_DEBUG_CATEGORY_UNDEFINED_BEHAVIOR_AMD = 0x914C,
		GL_DEBUG_CATEGORY_PERFORMANCE_AMD = 0x914D,
		GL_DEBUG_CATEGORY_SHADER_COMPILER_AMD = 0x914E,
		GL_DEBUG_CATEGORY_APPLICATION_AMD = 0x914F,
		GL_DEBUG_CATEGORY_OTHER_AMD = 0x9150;

	void glDebugMessageEnableAMD(@GLenum int category, @GLenum int severity, @AutoSize(value = "ids", canBeNull = true) @GLsizei int count, @Check(canBeNull = true) @Const @GLuint IntBuffer ids, boolean enabled);

	void glDebugMessageInsertAMD(@GLenum int category, @GLenum int severity, @GLuint int id, @AutoSize("buf") @GLsizei int length, @Const @GLchar ByteBuffer buf);

	@Alternate("glDebugMessageInsertAMD")
	void glDebugMessageInsertAMD(@GLenum int category, @GLenum int severity, @GLuint int id, @Constant("buf.length()") @GLsizei int length, CharSequence buf);

	/**
	 * The {@code AMDDebugOutputCallback.Handler} implementation passed to this method will be used for
	 * AMD_debug_output messages. If callback is null, any previously registered handler for the current
	 * thread will be unregistered and stop receiving messages.
	 *
	 * @param callback  the callback function to use
	 */
	@Code(
		// Create a GlobalRef to the callback object and register it with the current context.
		javaBeforeNative = "\t\tlong userParam = callback == null ? 0 : CallbackUtil.createGlobalRef(callback.getHandler());\n" +
		                   "\t\tCallbackUtil.registerContextCallbackAMD(userParam);"
	)
	void glDebugMessageCallbackAMD(@PointerWrapper(value = "GLDEBUGPROCAMD", canBeNull = true) AMDDebugOutputCallback callback,
	                               @Constant("userParam") @PointerWrapper("GLvoid *") long userParam);

	@GLuint
	int glGetDebugMessageLogAMD(@GLuint int count,
	                            @AutoSize(value = "messageLog", canBeNull = true) @GLsizei int logSize,
	                            @Check(value = "count", canBeNull = true) @GLenum IntBuffer categories,
	                            @Check(value = "count", canBeNull = true) @GLuint IntBuffer severities,
	                            @Check(value = "count", canBeNull = true) @GLuint IntBuffer ids,
	                            @Check(value = "count", canBeNull = true) @GLsizei IntBuffer lengths,
	                            @Check(canBeNull = true) @OutParameter @GLchar ByteBuffer messageLog);

}