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

import org.lwjgl.Window;
import org.lwjgl.Sys;
import org.lwjgl.Display;

/**
 * $Id$
 *
 * A visible GL context. Can either be windowed or fullscreen.
 * 
 * Each instance of GLWindow is only valid in the thread that creates it. 
 * In addition, only one instance may be created at any one time.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public class GLWindow extends Window {
	
	/** Has the GL been created yet? */
	private boolean created;
	
	/** Handle to the native GL rendering context */
	protected int handle;
	
	/** Color bits */
	protected final int color;
	
	/** Alpha bits */
	protected final int alpha;
	
	/** Depth bits */
	protected final int depth;
	
	/** Stencil bits */
	protected final int stencil;
	
	private int x, y;
	
	/** Fullscreen */
	protected final boolean fullscreen;

	static {
		System.loadLibrary(Sys.getLibraryName());
	}

	/**
	 * Construct a windowed context. If the underlying OS does not
	 * support windowed mode, then the width and height must match the current
	 * display resolution, or an Exception will be thrown. Otherwise a fullscreen
	 * window will be created.
	 * 
	 * @param title The title of the window
	 * @param x The position of the window on the x axis. May be ignored.
	 * @param y The position of the window on the y axis. May be ignored.
	 * @param width The width of the window's client area
	 * @param height The height of the window's client area
	 * @param bpp Require colour bits
	 * @param alpha Required alpha bits
	 * @param depth Required depth bits
	 * @param stencil Required stencil bits
	 */
	public GLWindow(String title, int x, int y, int width, int height, int bpp, int alpha, int depth, int stencil) {
		super(title, x, y, width, height);
		
		this.x = x;
		this.y = y;
		this.color = bpp;
		this.alpha = alpha;
		this.depth = depth;
		this.stencil = stencil;
		this.fullscreen = false;
	}

	/**
	 * Construct a fullscreen context. If the underlying OS does not
	 * support fullscreen mode, then a window will be created instead. If this
	 * fails too then an Exception will be thrown.
	 * 
	 * @param title The title of the window
	 * @param bpp Minimum bits per pixel
	 * @param alpha Minimum bits per pixel in alpha buffer
	 * @param depth Minimum bits per pixel in depth buffer
	 * @param stencil Minimum bits per pixel in stencil buffer
	 */
	public GLWindow(String title, int bpp, int alpha, int depth, int stencil) {
		super(title, 0, 0, Display.getWidth(), Display.getHeight());
		
		this.x = 0;
		this.y = 0;
		this.color = bpp;
		this.alpha = alpha;
		this.depth = depth;
		this.stencil = stencil;
		this.fullscreen = true;

	}
	
	protected void doCreate() throws Exception {
		nCreate(getTitle(), x, y, getWidth(), getHeight(), color, alpha, depth, stencil, fullscreen);
	}

	protected void doPaint() {
		swapBuffers();
	}

        /**
         * Swap the buffers.
         */
        private native void swapBuffers();
	
	/**
	 * Native method to create a windowed GL
	 */
	private native void nCreate(
		String title,
		int x,
		int y,
		int width,
		int height,
		int bpp,
		int alpha,
		int depth,
		int stencil,
		boolean fullscreen) throws Exception;
	
	/* (non-Javadoc)
	 * @see org.lwjgl.Window#doDestroy()
	 */
	protected void doDestroy() {
		nDestroyGL();
	}

	/**
	 * Natively destroy the context
	 */
	private native void nDestroyGL();
}
