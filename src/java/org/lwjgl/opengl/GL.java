/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * GL.java Created on Aug 1, 2002 by foo
 */
package org.lwjgl.opengl;

import org.lwjgl.Sys;

/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * BaseGLConstants.java Created on Aug 1, 2002 by foo
 */
/**
 * The GL itself, with all supported extensions, based on OpenGL 1.4.
 * 
 * @author foo
 */
public class GL extends CoreGL14 implements GLConstants {

	static {
		System.loadLibrary(Sys.LIBRARY_NAME);
	}

	/**
	 * Constructor for GL.
	 */
	public GL(int colorBits, int alphaBits, int depthBits, int stencilBits) {
		super(colorBits, alphaBits, depthBits, stencilBits);
	}

	/**
	 * Determine which extensions are available
	 */
	private void determineAvailableExtensions() {
	}

	/* (non-Javadoc)
	 * @see org.lwjgl.opengl.BaseGL#init()
	 */
	protected void init() {
		super.init();
		
		// Right after creation we can go and find out what extensions are
		// available. We can't actually determine this beforehand of course
		// because the available extensions can only be determined when there's
		// an actual rendering context.
		determineAvailableExtensions();
	}

}
