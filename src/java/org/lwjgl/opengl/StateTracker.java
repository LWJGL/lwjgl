/*
 * Copyright (c) 2002-2004 LWJGL Project
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

import java.nio.IntBuffer;

/** Track Vertex Buffer Objects by context. */
final class StateTracker {
	private final StateStack vbo_array_stack;
	private final StateStack vbo_element_stack;

	private final StateStack pbo_pack_stack;
	private final StateStack pbo_unpack_stack;

	private final ReferencesStack references_stack;
	
	private final StateStack attrib_stack;

	StateTracker() {
		int stack_size = Math.max(1, Util.glGetInteger(GL11.GL_MAX_CLIENT_ATTRIB_STACK_DEPTH));

		vbo_array_stack = new StateStack(stack_size, 0);
		vbo_element_stack = new StateStack(stack_size, 0);

		pbo_pack_stack = new StateStack(stack_size, 0);
		pbo_unpack_stack = new StateStack(stack_size, 0);

		references_stack = new ReferencesStack(stack_size);

		attrib_stack = new StateStack(stack_size, 0);
	}

	static void popAttrib() {
		if ((getClientAttribStack().popState() & GL11.GL_CLIENT_VERTEX_ARRAY_BIT) != 0) {
			getVBOArrayStack().popState();
			getVBOElementStack().popState();
			getPBOPackStack().popState();
			getPBOUnpackStack().popState();
			getReferencesStack().popState();
		}
	}
	
	static void pushAttrib(int mask) {
		getClientAttribStack().pushState();
		getClientAttribStack().setState(mask);
		if ((mask & GL11.GL_CLIENT_VERTEX_ARRAY_BIT) != 0) {
			getVBOArrayStack().pushState();
			getVBOElementStack().pushState();
			getPBOPackStack().pushState();
			getPBOUnpackStack().pushState();
			getReferencesStack().pushState();
		}
	}

	static void deleteBuffers(IntBuffer buffers) {
		for (int i = buffers.position(); i < buffers.limit(); i++) {
			int buffer_handle = buffers.get(i);
			if (getVBOArrayStack().getState() == buffer_handle)
				getVBOArrayStack().setState(0);
			if (getVBOElementStack().getState() == buffer_handle)
				getVBOElementStack().setState(0);
			if (getPBOPackStack().getState() == buffer_handle)
				getPBOPackStack().setState(0);
			if (getPBOUnpackStack().getState() == buffer_handle)
				getPBOUnpackStack().setState(0);
		}
	}

	static void bindBuffer(int target, int buffer) {
		switch ( target ) {
			case ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB:
				getVBOArrayStack().setState(buffer);
				break;
			case ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB:
				getVBOElementStack().setState(buffer);
				break;
			case ARBPixelBufferObject.GL_PIXEL_PACK_BUFFER_ARB:
				getPBOPackStack().setState(buffer);
				break;
			case ARBPixelBufferObject.GL_PIXEL_UNPACK_BUFFER_ARB:
				getPBOUnpackStack().setState(buffer);
				break;
			default:
				throw new IllegalArgumentException("Unsupported VBO target " + target);
		}
	}

	static StateStack getVBOArrayStack() {
		return GLContext.getCapabilities().tracker.vbo_array_stack;
	}

	static StateStack getVBOElementStack() {
		return GLContext.getCapabilities().tracker.vbo_element_stack;
	}

	static StateStack getPBOPackStack() {
		return GLContext.getCapabilities().tracker.pbo_pack_stack;
	}

	static StateStack getPBOUnpackStack() {
		return GLContext.getCapabilities().tracker.pbo_unpack_stack;
	}

	static ReferencesStack getReferencesStack() {
		return GLContext.getCapabilities().tracker.references_stack;
	}
	
	private static StateStack getClientAttribStack() {
		return GLContext.getCapabilities().tracker.attrib_stack;
	}
}
