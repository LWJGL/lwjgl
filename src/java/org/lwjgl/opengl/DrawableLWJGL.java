package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;

/**
 * @author Spasi
 * @since 23 Απρ 2010
 */
interface DrawableLWJGL extends Drawable {

	/**
	 * [INTERNAL USE ONLY] Returns the Drawable's Context.
	 *
	 * @return the Drawable's Context
	 */
	Context getContext();

	/**
	 * [INTERNAL USE ONLY] Creates a new Context that is shared with the Drawable's Context.
	 *
	 * @return a Context shared with the Drawable's Context.
	 */
	Context createSharedContext() throws LWJGLException;

}