/* 
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
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

import org.lwjgl.Sys;

/**
 * $Id$
 *
 * The base GL functionality (no actual GL methods).
 * 
 * Each instance of GL is only valid in the thread that creates it. 
 * In addition, only one instance may be the current GL context in any one
 * thread. To make a GL instance the current context, use makeCurrent().
 * 
 * This has been provided as a base class that we can use for either the
 * full GL1.4 specification or as a cut-down OpenGL embedded spec. (aka
 * a mini-driver).
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
abstract class BaseGL {
	
	static {
		System.loadLibrary(Sys.getLibraryName());
	}
	
	/** The current rendering context */
	private static BaseGL currentContext;
	
	/** Has the GL been created yet? */
	private boolean created;
	
	/** Handle to the native context */
	protected int handle;

	/** This GL will be valid for use in only one thread */
	protected Thread renderThread;
	
	/**
	 * Constructor for BaseGL. The context is not created at this point;
	 * to create the GL you must call create().
	 *
	 * @see #create()
	 */
	public BaseGL() {
	}
	
	/**
	 * Creates the GL, with the best match it can get for the specified
	 * parameters. The display must first have been created.
	 * 
	 * @throws Exception if the GL could not be created for some reason
	 */
	public final void create() throws Exception{
		if (created)
			return;
		if (!nCreate())
			throw new Exception("GL could not be created.");
		created = true;
		makeCurrent();
		init();
	}
	
	/**
	 * Override to provide any initialization code after creation.
	 */
	protected void init() {
	}
	
	/**
	 * Native method to create the GL. If successful then the native handle will
	 * be set. The GL is created on the current display, which must have been
	 * created by calling its setDisplayMode() method.
	 * 
	 * @return true if the GL was created successfully
	 * @see org.lwjgl.Display#create(org.lwjgl.DisplayMode, boolean)
	 */
	private native boolean nCreate();
	
	/**
	 * Destroy the GL context. Does nothing if the GL has not yet been created.
	 */
	public final void destroy() {
		if (!created)
			return;
		cleanup();
		created = false;
		renderThread = null;
		nDestroy();
	}
	
	/**
	 * Native method to destroy the GL context
	 */
	private native void nDestroy();
	
	/**
	 * Provide any cleanup in derived classes here. This method is called
	 * just before the native context is destroyed.
	 */
	public void cleanup() {
	}
	
	/**
	 * Finalizer, marked final. To perform specialized cleanup override the
	 * cleanup() method.
	 * 
	 * @see #cleanup()
	 */
	public final void finalize() throws Throwable {
		super.finalize();
		
		destroy();
	}
	
	/**
	 * Free the context from the current thread.
	 */
	public final void releaseContext() {
		assert created : "GL has not been created yet.";
		renderThread = null;
		currentContext = null;
		nReleaseContext();
	}
	
	/**
	 * Make this the current context for the current thread.
	 */
	public final void makeCurrent() {
		assert created : "GL has not been created yet.";
		renderThread = Thread.currentThread();
		currentContext = this;
		nMakeCurrent();
	}
	
	/**
	 * Swap the buffers
	 */
	public native void swapBuffers();
	
	/**
	 * Native method to free the context
	 */
	private native void nReleaseContext();

	/**
	 * Native method to make this the current thread
	 */
	private native void nMakeCurrent();
	
	/**
	 * @return true if this is the current rendering context and the correct
	 * thread
	 */
	public final boolean isValid() {
		return created
			&& currentContext == this
			&& Thread.currentThread() == renderThread;
	}

	/**
	 * Create a fullscreen display. If the display has already been created then this
	 * method is a no-op.
	 * 
	 * Alpha, depth, and stencil will be 0 bits.
	 * 
	 * @param title The title for the application
	 * @throws Exception if the window could not be created for any reason
	 * @see #destroy()
	 */
	public static void create(String title) throws Exception {
		create(title, 0, 0, 0);
	}

	/**
	 * Create a fullscreen display. If the display has already been created then this
	 * method is a no-op.
	 * 
	 * @param title The title for the application
	 * @param alpha Minimun number of alpha bits on the display
	 * @param depth Minimun number of depth bits on the display
	 * @param stencil Minimun number of stencil bits on the display
	 * @throws Exception if the window could not be created for any reason
	 * @see #destroy()
	 */
	public static void create(String title, int alpha, int depth, int stencil)
		throws Exception {
	
		if (Display.created) {
			return;
		}
	
		if (!nCreateFullscreen(title, alpha, depth, stencil)) {
			throw new Exception("Failed to create fullscreen display.");
		}
	
		Display.created = true;
	}

	/**
	 * Create a windowed display. If the display has already been created then this
	 * method is a no-op.
	 * 
	 * The window is not guaranteed to be positioned at (x, y). Nor is it guaranteed
	 * to have a drag bar, title bar, close button, or minimized button. It cannot be
	 * resized once created.
	 * 
	 * The window will have 0 bits alpha, depth, and stencil.
	 * 
	 * @param title The title for the application
	 * @param x, y The position of the window
	 * @param width, height The dimensions of the drawable area of the window
	 * @throws Exception if the window could not be created for any reason
	 * @see #destroy()
	 */
	public static void create(String title, int x, int y, int width, int height)
		throws Exception {
			
		create(title, x, y, width, height, 0, 0, 0);
			
	}

	/**
	 * Create a windowed display. If the display has already been created then this
	 * method is a no-op.
	 * 
	 * The window is not guaranteed to be positioned at (x, y). Nor is it guaranteed
	 * to have a drag bar, title bar, close button, or minimized button. It cannot be
	 * resized once created.
	 * 
	 * @param title The title for the application
	 * @param x, y The position of the window
	 * @param width, height The dimensions of the drawable area of the window
	 * @param alpha Minimun number of alpha bits on the display
	 * @param depth Minimun number of depth bits on the display
	 * @param stencil Minimun number of stencil bits on the display
	 * @throws Exception if the window could not be created for any reason
	 * @see #destroy()
	 */
	public static void create(String title, int x, int y, int width, int height, int alpha, int depth, int stencil)
		throws Exception {
	
		if (Display.created) {
			return;
		}
	
		if (!nCreateWindowed(title, x, y, width, height, alpha, depth, stencil)) {
			throw new Exception("Failed to create windowed display.");
		}
	
		Display.created = true;
	}

	/**
	 * Native method to create the display. This will set the handle if it is
	 * successful.
	 * @return true if the display was successfully created
	 * @see #create(org.lwjgl.DisplayMode, boolean)
	 */
	private static native boolean nCreateWindowed(
		String title, 
		int x, 
		int y, 
		int width,
		int height,
		int alpha_bits,
		int depth_bits,
		int stencil_bits);

	/**
	 * Native method to create the display. This will set the handle if it is
	 * successful.
	 * @return true if the display was successfully created
	 * @see #create(org.lwjgl.DisplayMode, boolean)
	 */
	private static native boolean nCreateFullscreen(
		String title,
		int alpha_bits,
		int depth_bits,
		int stencil_bits);

	/**
	 * Destroy the display and return it to normal. If the display has not yet
	 * been created no action is taken.
	 */
	public static void destroy() {
		if (!Display.created) {
			return;
		}
	
		nDestroy();
		Display.created = false;
		Display.mode = null;
	}

	/**
	 * Native method to destroy the display. This will reset the handle.
	 */
	private static native void nDestroy();

	/**
	 * Determines if the display is minimized. When the display is minimized it is
	 * effectively invisible, and you need perform no rendering in your game loop.
	 * On the native side, when the application is switched to some other application,
	 * the display window will minimize; when focus is regained, it will maximize and
	 * automatically gain focus and become the foreground window again.
	 * @return true if the display is minimized
	 */
	public static native boolean isMinimized();

	/**
	 * Determines if the user has requested that the application should close.
	 * When a user has requested that the application should shutdown, it is up to
	 * the application to perform the actual shutdown and cleanup of any allocated
	 * resources.
	 * 
	 * @return true if the user has requested that the application should close
	 */
	public static boolean isCloseRequested() {
		return Display.closeRequested;
	}

}
