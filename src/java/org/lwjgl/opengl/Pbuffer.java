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

import org.lwjgl.Sys;
import org.lwjgl.LWJGLException;

import java.nio.IntBuffer;

/**
 * $Id$
 * <p/>
 * Pbuffer encapsulates an OpenGL pbuffer.
 * <p/>
 * Each instance of GL is only valid in the thread that creates it. In addition, only one instance of an OpenGL window or
 * Pbuffer may be the current GL context in any one thread. To make a GL instance the current context, use makeCurrent().
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */
public final class Pbuffer {

	/**
	 * Indicates that Pbuffers can be created.
	 */
	public static final int PBUFFER_SUPPORTED = 1 << 0;

	/**
	 * Indicates that Pbuffers can be used as render-textures.
	 */
	public static final int RENDER_TEXTURE_SUPPORTED = 1 << 1;

	/**
	 * Indicates that Pbuffers can be used as non-power-of-two render-textures.
	 */
	public static final int RENDER_TEXTURE_RECTANGLE_SUPPORTED = 1 << 2;

	/**
	 * Indicates that Pbuffers can be used as depth render-textures.
	 */
	public static final int RENDER_DEPTH_TEXTURE_SUPPORTED = 1 << 3;

	/**
	 * The render-to-texture mipmap level attribute.
	 */
	public static final int MIPMAP_LEVEL = RenderTexture.WGL_MIPMAP_LEVEL_ARB;

	/**
	 * The render-to-texture cube map face attribute.
	 */
	public static final int CUBE_MAP_FACE = RenderTexture.WGL_CUBE_MAP_FACE_ARB;

	/**
	 * The render-to-texture cube map positive X face value.
	 */
	public static final int TEXTURE_CUBE_MAP_POSITIVE_X = RenderTexture.WGL_TEXTURE_CUBE_MAP_POSITIVE_X_ARB;

	/**
	 * The render-to-texture cube map negative X face value.
	 */
	public static final int TEXTURE_CUBE_MAP_NEGATIVE_X = RenderTexture.WGL_TEXTURE_CUBE_MAP_NEGATIVE_X_ARB;

	/**
	 * The render-to-texture cube map positive Y face value.
	 */
	public static final int TEXTURE_CUBE_MAP_POSITIVE_Y = RenderTexture.WGL_TEXTURE_CUBE_MAP_POSITIVE_Y_ARB;

	/**
	 * The render-to-texture cube map negative Y face value.
	 */
	public static final int TEXTURE_CUBE_MAP_NEGATIVE_Y = RenderTexture.WGL_TEXTURE_CUBE_MAP_NEGATIVE_Y_ARB;

	/**
	 * The render-to-texture cube map positive Z face value.
	 */
	public static final int TEXTURE_CUBE_MAP_POSITIVE_Z = RenderTexture.WGL_TEXTURE_CUBE_MAP_POSITIVE_Z_ARB;

	/**
	 * The render-to-texture cube map negative Z face value.
	 */
	public static final int TEXTURE_CUBE_MAP_NEGATIVE_Z = RenderTexture.WGL_TEXTURE_CUBE_MAP_NEGATIVE_Z_ARB;

	/**
	 * The Pbuffer front left buffer.
	 */
	public static final int FRONT_LEFT_BUFFER = RenderTexture.WGL_FRONT_LEFT_ARB;

	/**
	 * The Pbuffer front right buffer.
	 */
	public static final int FRONT_RIGHT_BUFFER = RenderTexture.WGL_FRONT_RIGHT_ARB;

	/**
	 * The Pbuffer back left buffer.
	 */
	public static final int BACK_LEFT_BUFFER = RenderTexture.WGL_BACK_LEFT_ARB;

	/**
	 * The Pbuffer back right buffer.
	 */
	public static final int BACK_RIGHT_BUFFER = RenderTexture.WGL_BACK_RIGHT_ARB;

	/**
	 * The Pbuffer depth buffer.
	 */
	public static final int DEPTH_BUFFER = RenderTexture.WGL_DEPTH_COMPONENT_NV;

	/**
	 * Current Pbuffer
	 */
	private static Pbuffer currentBuffer;

	/**
	 * Handle to the native GL rendering context
	 */
	private final int handle;

	/**
	 * Width
	 */
	private final int width;

	/**
	 * Height
	 */
	private final int height;

	static {
		Sys.initialize();
	}

	/**
	 * Construct an instance of a Pbuffer. If this fails then an LWJGLException will be thrown. The buffer is single-buffered.
	 * <p/>
	 * NOTE: An OpenGL window must be created before a Pbuffer can be created. The Pbuffer will have its own context that shares
	 * display lists and textures with the OpenGL window context, but it will have its own OpenGL state. Therefore, state changes
	 * to a pbuffer will not be seen in the window context and vice versa.
	 * <p/>
	 * NOTE: Some OpenGL implementations requires the shared contexts to use the same pixel format. So if possible, use the same
	 * bpp, alpha, depth and stencil values used to create the main window.
	 * <p/>
	 * The renderTexture parameter defines the necessary state for enabling render-to-texture. When this parameter is null,
	 * render-to-texture is not available. Before using render-to-texture, the Pbuffer capabilities must be queried to ensure that
	 * it is supported.
	 *
	 * @param width         Pbuffer width
	 * @param height        Pbuffer height
	 * @param pixel_format  Minimum Pbuffer context properties
	 * @param renderTexture
	 */
	public Pbuffer(int width, int height, PixelFormat pixel_format, RenderTexture renderTexture) throws LWJGLException {
		this.width = width;
		this.height = height;

		if ( renderTexture == null )
			handle = nCreate(width, height, pixel_format, null, null);
		else
			handle = nCreate(width, height, pixel_format,
			                 renderTexture.pixelFormatCaps,
			                 renderTexture.pBufferAttribs);
	}

	/**
	 * Method to test for validity of the buffer. If this function returns true, the buffer contents is lost. The buffer can still
	 * be used, but the results are undefined. The application is expected to release the buffer if needed, destroy it and recreate
	 * a new buffer.
	 *
	 * @return true if the buffer is lost and destroyed, false if the buffer is valid.
	 */
	public synchronized boolean isBufferLost() {
		return nIsBufferLost(handle);
	}

	/**
	 * Native method to test for buffer integrity
	 */
	private static native boolean nIsBufferLost(int handle);

	/**
	 * Method to make the Pbuffer context current. All subsequent OpenGL calls will go to this buffer.
	 */
	public synchronized void makeCurrent() {
		currentBuffer = this;
		nMakeCurrent(handle);
		VBOTracker.setCurrent(this);
	}

	/**
	 * Native method to make a pbuffer current.
	 */
	private static native void nMakeCurrent(int handle);

	/**
	 * Gets the Pbuffer capabilities.
	 *
	 * @return a bitmask of Pbuffer capabilities.
	 */
	public static native int getPbufferCaps();

	/**
	 * Native method to create a Pbuffer
	 */
	private static native int nCreate(int width, int height, PixelFormat pixel_format,
	                                  IntBuffer pixelFormatCaps,
	                                  IntBuffer pBufferAttribs) throws LWJGLException;

	/**
	 * Destroys the Pbuffer. After this call, there will be no valid GL rendering context - regardless of whether this Pbuffer was
	 * the current rendering context or not.
	 */
	public synchronized void destroy() {
		makeCurrent();
		int error = GL11.glGetError();
		VBOTracker.remove(this);
		nDestroy(handle);
		if (error != GL11.GL_NO_ERROR)
			throw new OpenGLException(error);
	}

	/**
	 * Natively destroy any GL-related stuff
	 */
	private static native void nDestroy(int handle);

	// -----------------------------------------------------------------------------------------
	// ------------------------------- Render-to-Texture Methods -------------------------------
	// -----------------------------------------------------------------------------------------

	/**
	 * Sets a render-to-texture attribute.
	 * <p/>
	 * The attrib parameter can be one of MIPMAP_LEVEL and CUBE_MAP_FACE. When the attrib parameter is CUBE_MAP_FACE then the value
	 * parameter can be on of the following:
	 * <p/>
	 * TEXTURE_CUBE_MAP_POSITIVE_X TEXTURE_CUBE_MAP_NEGATIVE_X TEXTURE_CUBE_MAP_POSITIVE_Y TEXTURE_CUBE_MAP_NEGATIVE_Y
	 * TEXTURE_CUBE_MAP_POSITIVE_Z TEXTURE_CUBE_MAP_NEGATIVE_Z
	 *
	 * @param attrib
	 * @param value
	 */
	public synchronized void setAttrib(int attrib, int value) {
		nSetAttrib(handle, attrib, value);
	}

	private static native void nSetAttrib(int handle, int attrib, int value);

	/**
	 * Binds the currently bound texture to the buffer specified. The buffer can be one of the following:
	 * <p/>
	 * FRONT_LEFT_BUFFER FRONT_RIGHT_BUFFER BACK_LEFT_BUFFER BACK_RIGHT_BUFFER DEPTH_BUFFER
	 *
	 * @param buffer
	 */
	public synchronized void bindTexImage(int buffer) {
		nBindTexImage(handle, buffer);
	}

	private static native void nBindTexImage(int handle, int buffer);

	/**
	 * Releases the currently bound texture from the buffer specified.
	 *
	 * @param buffer
	 */
	public synchronized void releaseTexImage(int buffer) {
		nReleaseTexImage(handle, buffer);
	}

	private static native void nReleaseTexImage(int handle, int buffer);

	/**
	 * @return Returns the height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return Returns the width.
	 */
	public int getWidth() {
		return width;
	}


}
