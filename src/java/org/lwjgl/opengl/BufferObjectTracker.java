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

import java.util.WeakHashMap;
import java.util.Map;

/** Track Vertex Buffer Objects by context. */
final class BufferObjectTracker {

	private static BufferObjectTracker current_tracker;

	private static final Map contextToTracker = new WeakHashMap(3, 1.0f);

	private final StateStack vbo_array_stack;
	private final StateStack vbo_element_stack;

	private final StateStack pbo_pack_stack;
	private final StateStack pbo_unpack_stack;

	private final StateStack attrib_stack;

	private BufferObjectTracker() {
		int stack_size = Math.max(1, Util.glGetInteger(GL11.GL_MAX_CLIENT_ATTRIB_STACK_DEPTH));

		vbo_array_stack = new StateStack(stack_size, 0);
		vbo_element_stack = new StateStack(stack_size, 0);

		pbo_pack_stack = new StateStack(stack_size, 0);
		pbo_unpack_stack = new StateStack(stack_size, 0);

		attrib_stack = new StateStack(stack_size, 0);
	}

	static StateStack getVBOArrayStack() {
		return current_tracker.vbo_array_stack;
	}

	static StateStack getVBOElementStack() {
		return current_tracker.vbo_element_stack;
	}

	static StateStack getPBOPackStack() {
		return current_tracker.pbo_pack_stack;
	}

	static StateStack getPBOUnpackStack() {
		return current_tracker.pbo_unpack_stack;
	}

	static StateStack getClientAttribStack() {
		return current_tracker.attrib_stack;
	}

	/**
	 * Called after a GLContext has been made current. This will set up the current VBO tracker.
	 *
	 * @param context
	 */
	static void setCurrent(Object context) {
		if ( context == null ) {
			current_tracker = null;
			return;
		}
		current_tracker = (BufferObjectTracker)contextToTracker.get(context);
		if ( current_tracker == null ) {
			current_tracker = new BufferObjectTracker();
			contextToTracker.put(context, current_tracker);
		}
	}
}
