/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * OpenGLException.java Created on Aug 1, 2002 by foo
 */
package org.lwjgl.opengl;

/**
 * Thrown by the debug build library of the LWJGL if any OpenGL operation
 * causes an error.
 * 
 * @author foo
 */
public class OpenGLException extends RuntimeException {

	/**
	 * Constructor for OpenGLException.
	 */
	public OpenGLException() {
		super();
	}

	/**
	 * Constructor for OpenGLException.
	 * @param message
	 */
	public OpenGLException(String message) {
		super(message);
	}

	/**
	 * Constructor for OpenGLException.
	 * @param message
	 * @param cause
	 */
	public OpenGLException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor for OpenGLException.
	 * @param cause
	 */
	public OpenGLException(Throwable cause) {
		super(cause);
	}

}
