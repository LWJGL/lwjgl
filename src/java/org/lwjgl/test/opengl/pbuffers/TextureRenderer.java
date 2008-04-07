/*
 * Copyright (c) 2002-2008 LWJGL Project
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
package org.lwjgl.test.opengl.pbuffers;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Pbuffer;

/**
 * Implementations of this class should create a pbuffer and implement "render-to-texture" accordingly.
 */
abstract class TextureRenderer {

	protected final int width;
	protected final int height;
	private final int texID;

	protected Pbuffer pbuffer;

	protected TextureRenderer(final int width, final int height, final int texID) {
		this.width = width;
		this.height = height;
		this.texID = texID;

		try {
			pbuffer = init(width, height, texID);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Create and initialize the pbuffer.
	 *
	 * @param width
	 * @param height
	 * @param texID
	 * @return
	 * @throws LWJGLException
	 */
	protected abstract Pbuffer init(int width, int height, int texID) throws LWJGLException;

	/**
	 * This will be called before rendering to the renderer. Implementations should setup the pbuffer context as necessary.
	 */
	void enable() {
		try {
			if ( pbuffer.isBufferLost() ) {
				System.out.println("Buffer contents lost - recreating the pbuffer");
				pbuffer.destroy();
				pbuffer = init(width, height, texID);
			}

			pbuffer.makeCurrent();
		} catch (LWJGLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Implementations should update the texture contents here.
	 */
	abstract void updateTexture();

	/**
	 * Clean-up resources held by the renderer
	 */
	final void destroy() {
		pbuffer.destroy();
	}

}