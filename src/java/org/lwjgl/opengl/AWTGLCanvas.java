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

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;

/**
 * $Id$
 * <p>
 * An AWT rendering context.
 * <p>
 * @version $Revision$
 * @author $Author$
 */
public class AWTGLCanvas extends Canvas implements Drawable {
	private final static AWTCanvasImplementation implementation;

	static {
		Sys.initialize();
		String class_name;
		String OS_NAME = System.getProperty("os.name");
		if (OS_NAME.startsWith("Linux")) {
			class_name = "org.lwjgl.opengl.LinuxCanvasImplementation";
		} else if (OS_NAME.startsWith("Windows")) {
			class_name = "org.lwjgl.opengl.Win32CanvasImplementation";
		} else if (OS_NAME.startsWith("Mac")) {
			class_name = "org.lwjgl.opengl.DefaultCanvasImplementation";
		} else
			throw new IllegalStateException("The platform " + OS_NAME + " is not supported");
		try {
			Class impl_class = Class.forName(class_name);
			implementation = (AWTCanvasImplementation)impl_class.newInstance();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}

	/** The requested pixel format */
	private final PeerInfo peer_info;

	/** The drawable to share context with */
	private final Drawable drawable;
	
	/** Context handle */
	private Context context;
	
	/**
	 * This method should only be called internally.
	 */
	public Context getContext() {
		return context;
	}
	
	/**
	 * Constructor using the default PixelFormat.
	 */
	public AWTGLCanvas() throws LWJGLException {
		this(new PixelFormat());
	}
	
	/**
	 * Create an AWTGLCanvas with the requested PixelFormat on the default GraphicsDevice.
	 *
	 * @param pixelFormat The desired pixel format. May not be null
	 * @param device the device to create the canvas on.
	 */
	public AWTGLCanvas(PixelFormat pixel_format) throws LWJGLException {
		this(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(), pixel_format);
	}

	/**
	 * Create an AWTGLCanvas with the requested PixelFormat on the default GraphicsDevice.
	 *
	 * @param pixelFormat The desired pixel format. May not be null
	 * @param device the device to create the canvas on.
	 */
	public AWTGLCanvas(GraphicsDevice device, PixelFormat pixel_format) throws LWJGLException {
		this(device, pixel_format, null);
	}

	/**
	 * Create an AWTGLCanvas with the requested PixelFormat on the specified GraphicsDevice.
	 *
	 * @param device the device to create the canvas on.
	 * @param pixelFormat The desired pixel format. May not be null
	 * @param shared_drawable The Drawable to share context with
	 */
	public AWTGLCanvas(GraphicsDevice device, PixelFormat pixel_format, Drawable drawable) throws LWJGLException {
		super(implementation.findConfiguration(device, pixel_format));
		this.peer_info = implementation.createPeerInfo(this, pixel_format);
		this.drawable = drawable;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Canvas#addNotify()
	 */
	public void addNotify() {
		super.addNotify();
		try {
			createContext();
		} catch (LWJGLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Component#removeNotify()
	 */
	public void removeNotify() {
		try {
			destroyContext();
		} catch (LWJGLException e) {
			throw new RuntimeException(e);
		}
		super.removeNotify();
	} 
	
	/**
	 * Enable vsync
	 */
	public synchronized void setVSyncEnabled(boolean enabled) throws LWJGLException {
		if (context == null)
			throw new IllegalStateException("Canvas not yet displayable");
		context.setVSync(enabled);
	}
	
	/**
	 * Swap the canvas' buffer
	 */
	public synchronized void swapBuffers() throws LWJGLException {
		if (context == null)
			throw new IllegalStateException("Canvas not yet displayable");
		context.swapBuffers();
	}
	
	public synchronized void releaseContext() throws LWJGLException {
		if (context == null)
			throw new IllegalStateException("Canvas not yet displayable");
		if (context.isCurrent())
			Context.releaseCurrentContext();
	}
	
	/**
	 * Make the canvas' context current. It is highly recommended that the context
	 * is only made current inside the AWT thread (for example in an overridden paint()).
	 */
	public synchronized void makeCurrent() throws LWJGLException {
		if (context == null)
			throw new IllegalStateException("Canvas not yet displayable");
		context.makeCurrent();
	}
	
	/**
	 * Create the OpenGL context. This occurs when the component becomes displayable
	 * @throws LWJGLException
	 */
	private synchronized void createContext() throws LWJGLException {
		if (context == null)
			context = new Context(peer_info, drawable != null ? drawable.getContext() : null);
	}
	
	/**
	 * Destroy the OpenGL context. This happens when the component becomes undisplayable
	 */
	private synchronized void destroyContext() throws LWJGLException {
		context.forceDestroy();
		context = null;
	}

	/**
	 * Empty paint to avoid clearing
	 */
	public void paint(Graphics g) {
	}

	/**
	 * override update to avoid clearing
	 */
	public void update(Graphics g) {
		paint(g);
	}
}
