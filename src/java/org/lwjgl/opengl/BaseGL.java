/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * BaseGL.java Created on Aug 1, 2002 by foo
 */
package org.lwjgl.opengl;

import org.lwjgl.Sys;

/**
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
 * @author foo
 */
abstract class BaseGL {
	
	static {
		System.loadLibrary(Sys.LIBRARY_NAME);
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
	 * @see create()
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
	 * @see org.lwjgl.Display#create()
	 */
	private native boolean nCreate(int colorBits, int alphaBits, int depthBits, int stencilBits);
	
	/**
	 * Destroy the GL context. Does nothing if the GL has not yet been created.
	 */
	public void destroy() {
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
	 * @see cleanup()
	 */
	public final void finalize() throws Throwable {
		super.finalize();
		
		destroy();
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
