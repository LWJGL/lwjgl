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
package org.lwjgl.util.model.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

/**
 * $Id$
 * A 3D sprite!
 * @author $Author$
 * @version $Revision$
 */
public class Sprite3D extends ModelRenderer {
	
	/** Current position */
	private final Vector3f position = new Vector3f();
	
	/** Current orientation (axis/angle) */
	private final Vector3f axis = new Vector3f();
	private float angle;
	
	/*
	 * Recognised animation actions
	 */
	private static final String ANIM_HIDE = "hide";
	private static final String ANIM_REWIND = "rewind";
	private static final String ANIM_GOTO = "goto ";
	
	/**
	 * C'tor
	 */
	public Sprite3D() {
		addAnimationEventListener(new AnimationEventListener() {
			public void receiveAnimationEvent(ModelRenderer src, String action) {
				if (action.equals(ANIM_HIDE)) {
					setVisible(false);
				} else if (action.equals(ANIM_REWIND)) {
					rewind();
				} else if (action.startsWith(ANIM_GOTO)) {
					setAnimation(action.substring(ANIM_GOTO.length()));
				}
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see org.lwjgl.util.model.renderer.ModelRenderer#renderFrame()
	 */
	protected void renderFrame() {
		// TODO: rotation
		GL11.glPushMatrix();
		GL11.glTranslatef(position.getX(), position.getY(), position.getY());
		super.renderFrame();
		GL11.glPopMatrix();
	}
	
	/**
	 * @return Returns the position.
	 */
	public Vector3f getPosition() {
		return position;
	}
}
