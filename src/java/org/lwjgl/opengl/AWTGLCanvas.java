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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.Sys;

/**
 * <p>
 * An AWT rendering context.
 * <p>
 * @version $Revision$
 * @author $Author$
 * $Id$
 */
public class AWTGLCanvas extends Canvas implements Drawable, ComponentListener, HierarchyListener {

	private static final long serialVersionUID = 1L;
	
	private final static AWTCanvasImplementation implementation;
	private boolean update_context;
	private Object SYNC_LOCK = new Object();

	/** The requested pixel format */
	private final PixelFormat pixel_format;

	/** The drawable to share context with */
	private final Drawable drawable;
	
	/** Context handle */
	private PeerInfo peer_info;
	private Context context;

	/**
	 * re-entry counter for support for re-entrant
	 * redrawing in paint(). It happens when using dialog boxes.
	 */
	private int reentry_count;

	/** Tracks whether initGL() needs to be called */
	private boolean first_run;

	/** Track the input adapter, if any */
	private volatile AWTCanvasInputImplementation awt_input;

	static {
		Sys.initialize();
		String class_name;
		switch (LWJGLUtil.getPlatform()) {
			case LWJGLUtil.PLATFORM_LINUX:
				class_name = "org.lwjgl.opengl.LinuxCanvasImplementation";
				break;
			case LWJGLUtil.PLATFORM_WINDOWS:
				class_name = "org.lwjgl.opengl.WindowsCanvasImplementation";
				break;
			case LWJGLUtil.PLATFORM_MACOSX:
				class_name = "org.lwjgl.opengl.MacOSXCanvasImplementation";
				break;
			default:
				throw new IllegalStateException("Unsupported platform");
		}
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

	/**
	 * Used from AWTInputAdapter
	 */
	static AWTCanvasImplementation getImplementation() {
		return implementation;
	}

	void setInput(AWTCanvasInputImplementation awt_input) {
		this.awt_input = awt_input;
	}

	private void setUpdate() {
		synchronized(SYNC_LOCK) {
			update_context = true;
		}
	}

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
		if (pixel_format == null)
			throw new NullPointerException("Pixel format must be non-null");
		addHierarchyListener(this);
		addComponentListener(this);
		this.drawable = drawable;
		this.pixel_format = pixel_format;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Canvas#addNotify()
	 */
	public void addNotify() {
		super.addNotify();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Component#removeNotify()
	 */
	public void removeNotify() {
		destroyContext();
		super.removeNotify();
	} 
	
	/**
	 * Set swap interval.
	 */
	public void setSwapInterval(int swap_interval) {
		synchronized(SYNC_LOCK) {
			if (context == null)
				throw new IllegalStateException("Canvas not yet displayable");
			Context.setSwapInterval(swap_interval);
		}
	}
	
	/**
	 * Enable vsync
	 */
	public void setVSyncEnabled(boolean enabled) {
		setSwapInterval(enabled ? 1 : 0);
	}
	
	/**
	 * Swap the canvas' buffer
	 */
	public void swapBuffers() throws LWJGLException {
		synchronized(SYNC_LOCK) {
			if (context == null)
				throw new IllegalStateException("Canvas not yet displayable");
			Context.swapBuffers();
		}
	}
	
	public void releaseContext() throws LWJGLException {
		synchronized(SYNC_LOCK) {
			if (context == null)
				throw new IllegalStateException("Canvas not yet displayable");
			if (context.isCurrent())
				Context.releaseCurrentContext();
		}
	}
	
	/**
	 * Make the canvas' context current. It is highly recommended that the context
	 * is only made current inside the AWT thread (for example in an overridden paintGL()).
	 */
	public void makeCurrent() throws LWJGLException {
		synchronized(SYNC_LOCK) {
			if (context == null)
				throw new IllegalStateException("Canvas not yet displayable");
			context.makeCurrent();
		}
	}
	
	/**
	 * Destroy the OpenGL context. This happens when the component becomes undisplayable
	 */
	private void destroyContext() {
		synchronized(SYNC_LOCK) {
			try {
				if (context != null) {
					context.forceDestroy();
					context = null;
					reentry_count = 0;
					peer_info.destroy();
					peer_info = null;
				}
			} catch (LWJGLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Override this to do initialising of the context.
	 * It will be called once from paint(), immediately after 
	 * the context is created and made current.
	 */
	protected void initGL() {
	}

	/**
	 * Override this to do painting
	 */
	protected void paintGL() {
	}

	/**
	 * The default paint() operation makes the context current and calls paintGL() which should
	 * be overridden to do GL operations.
	 */
	public final void paint(Graphics g) {
		synchronized (SYNC_LOCK) {
			try {
				if (peer_info == null) {
					this.peer_info = implementation.createPeerInfo(this, pixel_format);
				}
				peer_info.lockAndGetHandle();
				try {
					if (context == null) {
						this.context = new Context(peer_info, drawable != null ? drawable.getContext() : null);
						first_run = true;
					}

					if (reentry_count == 0)
						context.makeCurrent();
					reentry_count++;
					try {
						if (update_context) {
							context.update();
							update_context = false;
						}
						AWTCanvasInputImplementation current_input = awt_input;
						if (current_input != null)
							current_input.processInput(peer_info);
						if (first_run) {
							first_run = false;
							initGL();
						}
						paintGL();
					} finally {
						reentry_count--;
						if (reentry_count == 0)
							Context.releaseCurrentContext();
					}
				} finally {
					peer_info.unlock();
				}
			} catch (LWJGLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * override update to avoid clearing
	 */
	public void update(Graphics g) {
		paint(g);
	}

	public void componentShown(ComponentEvent e) {
	}

	public void componentHidden(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		setUpdate();
	}

	public void componentMoved(ComponentEvent e) {
		setUpdate();
	}

	public void setLocation(int x, int y) {
		super.setLocation(x, y);
		setUpdate();
	}

	public void setLocation(Point p) {
		super.setLocation(p);
		setUpdate();
	}

	public void setSize(Dimension d) {
		super.setSize(d);
		setUpdate();
	}

	public void setSize(int width, int height) {
		super.setSize(width, height);
		setUpdate();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		setUpdate();
	}

	public void hierarchyChanged(HierarchyEvent e) {
		setUpdate();
	}
}
