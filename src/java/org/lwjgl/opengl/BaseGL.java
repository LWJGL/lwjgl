/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
	
	/** The number of color bits */
	protected final int colorBits;
	
	/** The number of alpha bits */
	protected final int alphaBits;
	
	/** The number of depth bits */
	protected final int depthBits;
	
	/** The number of stencil bits */
	protected final int stencilBits;
	
	/**
	 * Constructor for BaseGL. The context is not created at this point;
	 * to create the GL you must call create().
	 *
	 * @param colorBits the number of color bits (eg. 16, 24, 32)
	 * @param alphaBits the number of alpha bits (eg. 0 or 8)
	 * @param depthBits the number of depth bits (eg. 16 or 24)
	 * @param stencilBits the number of stencil bits (eg. 0 or 8)
	 * @see #create()
	 */
	public BaseGL(int colorBits, int alphaBits, int depthBits, int stencilBits) {
		this.colorBits = colorBits;
		this.alphaBits = alphaBits;
		this.depthBits = depthBits;
		this.stencilBits = stencilBits;
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
		if (!nCreate(colorBits, alphaBits, depthBits, stencilBits))
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
	private native boolean nCreate(int colorBits, int alphaBits, int depthBits, int stencilBits);
	
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
}
