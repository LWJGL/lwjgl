/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * CoreGL14.java Created on Aug 1, 2002 by foo
 */
package org.lwjgl.opengl;

/**
 * The core OpenGL1.4 API, with no extensions.
 * 
 * @author foo
 */
public class CoreGL14 extends BaseGL implements CoreGL14Constants {
	
	/**
	 * Constructor for CoreGL14.
	 */
	public CoreGL14(
		int colorBits,
		int alphaBits,
		int depthBits,
		int stencilBits) {
		super(colorBits, alphaBits, depthBits, stencilBits);
	}

}


