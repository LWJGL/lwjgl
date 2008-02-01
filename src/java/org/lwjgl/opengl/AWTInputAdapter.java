/*
 * Copyright (c) 2002-2006 LWJGL Project
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

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * This is the static class for using LWJGL input (Mouse and Keyboard)
 * with an AWTGLCanvas.
 *
 * @author Elias Naur
 * @version $Revision: 2286 $
 * $Id: AWTCanvasImplementation.java 2286 2006-03-23 19:32:21Z matzon $
 */
public final class AWTInputAdapter {
	private static AWTCanvasInputImplementation awt_input;

	/**
	 * Create AWTInputAdapter with the specified AWTGLCanvas. Use
	 * update() to receive input.
	 *
	 * @param canvas The canvas to receive input from.
	 */
	public static synchronized void create(AWTGLCanvas canvas) throws LWJGLException {
		if (isCreated())
			throw new IllegalStateException("You need to destroy() the adapter.");
		awt_input = AWTGLCanvas.getImplementation().createInput(canvas);
		// Invoke Mouse.create(awt_input) and Keyboard.create(awt_input)
		try {
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				private void invokeCreate(Class input_class) throws Exception {
					Method create_method = input_class.getDeclaredMethod("create", new Class[]{InputImplementation.class});
					create_method.setAccessible(true);
					create_method.invoke(null, new Object[]{awt_input});
				}

				public Object run() throws Exception {
					invokeCreate(Mouse.class);
					invokeCreate(Keyboard.class);
					return null;
				}
			});
		} catch (PrivilegedActionException e) {
			Throwable cause = e.getCause();
			if (cause instanceof LWJGLException)
				throw (LWJGLException)cause;
			else
				throw new Error(cause);
		}
		awt_input.init();
	}

	public static synchronized boolean isCreated() {
		return awt_input != null;
	}

	public static synchronized void destroy() {
		if (isCreated()) {
			Mouse.destroy();
			Keyboard.destroy();
			awt_input.destroy();
			awt_input = null;
		}
	}
}
