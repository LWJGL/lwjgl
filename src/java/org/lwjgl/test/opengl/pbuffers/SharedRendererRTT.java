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
package org.lwjgl.test.opengl.pbuffers;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Pbuffer;
import org.lwjgl.opengl.RenderTexture;

final class SharedRendererRTT extends TextureRenderer {

	SharedRendererRTT(final int width, final int height, final int texID) {
		super(width, height, texID);
	}

	protected Pbuffer init(final int width, final int height, final int texID) {
		Pbuffer pbuffer = null;

		try {
			final RenderTexture rt = new RenderTexture(true, false, false, false, RenderTexture.RENDER_TEXTURE_2D, 0);
			pbuffer = Pbuffer.createPbufferUsingDisplayContext(width, height, rt);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return pbuffer;
	}

	void enable() {
		super.enable();

		// Change the current state, since we're sharing the display context.
		PbufferTest.initGLState(width, height, 0.5f);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		// Release the texture before rendering.
		pbuffer.releaseTexImage(Pbuffer.BACK_LEFT_BUFFER);
	}

	void updateTexture() {
		// Bind the texture after rendering.
		pbuffer.bindTexImage(Pbuffer.BACK_LEFT_BUFFER);

		// Restore the display state.
		PbufferTest.initGLState(800, 600, 0.0f);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

}