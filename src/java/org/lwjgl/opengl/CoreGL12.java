/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * CoreGL12.java Created on Aug 9, 2002 by foo
 */
package org.lwjgl.opengl;

import org.lwjgl.Sys;

/**
 * 
 * @author foo
 */
public class CoreGL12 extends CoreGL11 implements CoreGL12Constants {

	static {
		System.loadLibrary(Sys.LIBRARY_NAME);
	}

	/**
	 * Constructor for CoreGL12.
	 * @param colorBits
	 * @param alphaBits
	 * @param depthBits
	 * @param stencilBits
	 */
	public CoreGL12(
		int colorBits,
		int alphaBits,
		int depthBits,
		int stencilBits) {
		super(colorBits, alphaBits, depthBits, stencilBits);
	}

}
