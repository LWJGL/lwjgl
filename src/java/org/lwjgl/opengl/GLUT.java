/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * GLUT.java Created on Aug 9, 2002 by foo
 */
package org.lwjgl.opengl;

/**
 * GLUT - more GL utilities.
 * 
 * @author foo
 */
public class GLUT implements GLUTConstants {
	
	/** The GL */
	private final GL gl;

	/**
	 * Constructor for GLUT.
	 */
	public GLUT(GL gl) {
		this.gl = gl;
	}

}
