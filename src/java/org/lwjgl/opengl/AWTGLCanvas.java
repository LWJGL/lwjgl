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
public class AWTGLCanvas extends Canvas {

	static {
		System.loadLibrary("jawt");
		Sys.initialize();
	}

	/** The requested pixel format */
	private PixelFormat pixelFormat;
	
	/** Context handle */
	private long context;
	
	/**
	 * Constructor using the default PixelFormat.
	 */
	public AWTGLCanvas() {
		this(new PixelFormat());
	}
	
	/**
	 * Create an AWTGLCanvas with the requested PixelFormat. Construction is always
	 * successful, however, when the time comes to actually realise the component on the
	 * screen 
	 * @param pixelFormat The desired pixel format. May not be null
	 */
	public AWTGLCanvas(PixelFormat pixelFormat) {
		if (pixelFormat == null) {
			throw new IllegalArgumentException("Pixel format may not be null");
		}
		this.pixelFormat = pixelFormat;
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
		super.removeNotify();
		try {
			destroyContext();
		} catch (LWJGLException e) {
			throw new RuntimeException(e);
		}
	} 
	
	/**
	 * Create the OpenGL context. This occurs when the component becomes displayable
	 * @throws LWJGLException
	 */
	private synchronized void createContext() throws LWJGLException {
		nCreateContext();
	}
	private native void nCreateContext() throws LWJGLException;
	
	/**
	 * Destroy the OpenGL context. This occurs when the component is no longer displayable.
	 */
	private synchronized void destroyContext() throws LWJGLException {
	}
	private native void nDestroyContext() throws LWJGLException;
	
	/* (non-Javadoc)
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	public synchronized final void paint(Graphics g) {
		try {
			nPaint();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	private native void nPaint() throws Exception;

	
	/**
	 * Paint callback from native code
	 */
	private final void cPaint() {
		try {
			GLContext.useContext(this);
		} catch (LWJGLException e) {
			throw new RuntimeException(e);
		}
		doPaint();
	}
	
	/**
	 * Do painting. Override this method to call GL commands.
	 */
	protected void doPaint() {
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Canvas#update(java.awt.Graphics)
	 */
	public void update(Graphics g) {
		paint(g);
	}
}
