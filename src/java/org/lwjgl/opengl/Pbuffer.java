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

import org.lwjgl.*;

/**
 * $Id$
 *
 * Pbuffer encapsulates an OpenGL pbuffer.
 *
 * Each instance of GL is only valid in the thread that creates it. 
 * In addition, only one instance of an OpenGL window or Pbuffer may be
 * the current GL context in any one thread. To make a GL instance the 
 * current context, use makeCurrent().
 * 
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */
public class Pbuffer {
	public final static int PBUFFER_SUPPORTED = 1;
		
	static {
		System.loadLibrary(Sys.getLibraryName());
	}
	
	/** Handle to the native GL rendering context */
	protected final int handle;

	/** Current Pbuffer */
	private static Pbuffer currentBuffer = null;
	
	/**
	 * Construct an instance of a Pbuffer. If this fails then an Exception will be thrown.
	 * The buffer is single-buffered.
	 *
	 * NOTE: An OpenGL window must be created before a Pbuffer can be created. The Pbuffer will
	 * have its own context that shares display lists and textures with the OpenGL window context,
	 * but it will have its own OpenGL state. Therefore, state changes to a pbuffer will not be seen
	 * in the window context and vice versa.
	 *
	 * NOTE: Some OpenGL implementations requires the shared contexts to use the same pixel format.
	 * So if possible, use the same bpp, alpha, depth and stencil values used to create the main window.
	 *  
	 * @param width Pbuffer width
	 * @param height Pbuffer height
	 * @param bpp Minimum bits per pixel
	 * @param alpha Minimum bits per pixel in alpha buffer
	 * @param depth Minimum bits per pixel in depth buffer
	 * @param stencil Minimum bits per pixel in stencil buffer
	 */
	public Pbuffer(int width, int height, int bpp, int alpha, int depth, int stencil) throws Exception {
		handle = nCreate(width, height, bpp, alpha, depth, stencil);
	}

	/**
	 * Method to release the current Pbuffer context and make the OpenGL window current.
	 */
	public static void releaseContext() {
		currentBuffer = null;
		nReleaseContext();
	}

	/**
	 * Method to test for validity of the buffer. If this function returns true,
	 * the buffer contents is lost. The buffer can still be used, but the results are undefined.
	 * The application is expected to release the buffer if needed, destroy it and recreate a new
	 * buffer.
	 *
	 * @return true if the buffer is lost and destroyed, false if the buffer is valid.
	 */
	public boolean isBufferLost() {
		return nIsBufferLost(handle);
	}

	/**
	 * Native method to test for buffer integrity
	 */
	private native static boolean nIsBufferLost(int handle);

	/**
	 * Native method to release the context.
	 */
	private native static void nReleaseContext();

	/**
	 * Method to make the Pbuffer context current. All subsequent OpenGL
	 * calls will go to this buffer.
	 */
	public void makeCurrent() {
		currentBuffer = this;
		nMakeCurrent(handle);
	}

	/**
	 * Native method to make a pbuffer current.
	 */
	private native static void nMakeCurrent(int handle);

	/**
	 * Gets the Pbuffer capabilities. Only the flag PBUFFER_SUPPORTED is
	 * available, and indicates that Pbuffers can be created.
	 *
	 * @return a bitmask of Pbuffer capabilities.
	 */
	public static native int getPbufferCaps();
	
	/**
	 * Native method to create a Pbuffer
	 */
	private native static int nCreate(
		int width,
		int height,
		int bpp,
		int alpha,
		int depth,
		int stencil) throws Exception;
	
	/**
	 * Destroys the Pbuffer. The buffer must not be current.
	 */
	public void destroy() {
		assert currentBuffer != this : "Pbuffers must not be current when releasing it";
		nDestroy(handle);
	}

	/**
	 * Natively destroy any GL-related stuff
	 */
	private native static void nDestroy(int handle);
}
