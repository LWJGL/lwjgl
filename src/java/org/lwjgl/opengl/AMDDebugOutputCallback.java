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

import org.lwjgl.PointerWrapperAbstract;

/**
 * Instances of this class are needed to use the callback functionality of the AMD_debug_output extension.
 * A debug context must be current before creating instances of this class. Users of this class may provide
 * implementations of the {@code Handler} interface to receive notifications. The same {@code Handler}
 * instance may be used by different contexts but it is not recommended. Handler notifications are synchronized.
 *
 * @author Spasi
 */
public final class AMDDebugOutputCallback extends PointerWrapperAbstract {

	/** Severity levels. */
	private static final int GL_DEBUG_SEVERITY_HIGH_AMD = 0x9146,
		GL_DEBUG_SEVERITY_MEDIUM_AMD = 0x9147,
		GL_DEBUG_SEVERITY_LOW_AMD = 0x9148;

	/** Categories */
	private static final int GL_DEBUG_CATEGORY_API_ERROR_AMD = 0x9149,
		GL_DEBUG_CATEGORY_WINDOW_SYSTEM_AMD = 0x914A,
		GL_DEBUG_CATEGORY_DEPRECATION_AMD = 0x914B,
		GL_DEBUG_CATEGORY_UNDEFINED_BEHAVIOR_AMD = 0x914C,
		GL_DEBUG_CATEGORY_PERFORMANCE_AMD = 0x914D,
		GL_DEBUG_CATEGORY_SHADER_COMPILER_AMD = 0x914E,
		GL_DEBUG_CATEGORY_APPLICATION_AMD = 0x914F,
		GL_DEBUG_CATEGORY_OTHER_AMD = 0x9150;

	private static final long CALLBACK_POINTER;

	static {
		long pointer = 0;
		try {
			// Call reflectively so that we can compile this class for the Generator.
			pointer = (Long)Class.forName("org.lwjgl.opengl.CallbackUtil").getDeclaredMethod("getDebugOutputCallbackAMD").invoke(null);
		} catch (Exception e) {
			// ignore
		}
		CALLBACK_POINTER = pointer;
	}

	private final Handler handler;

	/**
	 * Creates an AMDDebugOutputCallback with a default callback handler.
	 * The default handler will simply print the message on System.err.
	 */
	public AMDDebugOutputCallback() {
		this(new Handler() {
			public void handleMessage(final int id, final int category, final int severity, final String message) {
				System.err.println("[LWJGL] AMD_debug_output message");
				System.err.println("\tID: " + id);

				String description;
				switch ( category ) {
					case GL_DEBUG_CATEGORY_API_ERROR_AMD:
						description = "API ERROR";
						break;
					case GL_DEBUG_CATEGORY_WINDOW_SYSTEM_AMD:
						description = "WINDOW SYSTEM";
						break;
					case GL_DEBUG_CATEGORY_DEPRECATION_AMD:
						description = "DEPRECATION";
						break;
					case GL_DEBUG_CATEGORY_UNDEFINED_BEHAVIOR_AMD:
						description = "UNDEFINED BEHAVIOR";
						break;
					case GL_DEBUG_CATEGORY_PERFORMANCE_AMD:
						description = "PERFORMANCE";
						break;
					case GL_DEBUG_CATEGORY_SHADER_COMPILER_AMD:
						description = "SHADER COMPILER";
						break;
					case GL_DEBUG_CATEGORY_APPLICATION_AMD:
						description = "APPLICATION";
						break;
					case GL_DEBUG_CATEGORY_OTHER_AMD:
						description = "OTHER";
						break;
					default:
						description = "Unknown (" + Integer.toHexString(category) + ")";
				}
				System.err.println("\tCategory: " + description);

				switch ( severity ) {
					case GL_DEBUG_SEVERITY_HIGH_AMD:
						description = "HIGH";
						break;
					case GL_DEBUG_SEVERITY_MEDIUM_AMD:
						description = "MEDIUM";
						break;
					case GL_DEBUG_SEVERITY_LOW_AMD:
						description = "LOW";
						break;
					default:
						description = "Unknown (" + Integer.toHexString(category) + ")";
				}
				System.err.println("\tSeverity: " + description);

				System.err.println("\tMessage: " + message);
			}
		});
	}

	/**
	 * Creates an AMDDebugOutputCallback with the specified callback handler.
	 * The handler's {@code handleMessage} method will be called whenever
	 * debug output is generated by the GL.
	 *
	 * @param handler the callback handler
	 */
	public AMDDebugOutputCallback(final Handler handler) {
		super(CALLBACK_POINTER);

		this.handler = handler;
	}

	Handler getHandler() {
		return handler;
	}

	/** Implementations of this interface can be used to receive AMD_debug_output notifications. */
	public interface Handler {

		/**
		 * This method will be called when an AMD_debug_output message is generated.
		 *
		 * @param id       the message ID
		 * @param category the message category
		 * @param severity the message severity
		 * @param message  the string representation of the message.
		 */
		void handleMessage(int id, int category, int severity, String message);

	}

}
