/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * GLUT.java Created on Aug 9, 2002 by foo
 */
package org.lwjgl.opengl;

import org.lwjgl.Sys;

/**
 * GLUT - more GL utilities.
 * 
 * @author foo
 */
public class GLUT implements GLUTConstants {
	
	static {
		System.loadLibrary(Sys.LIBRARY_NAME);
	}

	/** The GL */
	private final GL gl;

	/**
	 * Constructor for GLUT.
	 */
	public GLUT(GL gl) {
		this.gl = gl;
	}

}
