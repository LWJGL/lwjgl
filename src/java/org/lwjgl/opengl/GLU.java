/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * GLU.java Created on Aug 9, 2002 by foo
 */
package org.lwjgl.opengl;

import org.lwjgl.Sys;

/**
 * GL Utilities library.
 * 
 * @author foo
 */
public class GLU implements GLUConstants {
	
	static {
		System.loadLibrary(Sys.LIBRARY_NAME);
	}

	/** Handle to GL */
	private final GL gl;

	/**
	 * Constructor for GLU.
	 */
	public GLU(GL gl) {
		this.gl = gl;
	}

}
