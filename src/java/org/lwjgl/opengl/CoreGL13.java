/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * CoreGL13.java Created on Aug 9, 2002 by foo
 */
package org.lwjgl.opengl;

import org.lwjgl.Sys;

/**
 * 
 * @author foo
 */
public class CoreGL13 extends CoreGL12 implements CoreGL13Constants {

	static {
		System.loadLibrary(Sys.LIBRARY_NAME);
	}

	/**
	 * Constructor for CoreGL13.
	 * @param colorBits
	 * @param alphaBits
	 * @param depthBits
	 * @param stencilBits
	 */
	public CoreGL13(
		int colorBits,
		int alphaBits,
		int depthBits,
		int stencilBits) {
		super(colorBits, alphaBits, depthBits, stencilBits);
	}

}
