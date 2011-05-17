package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;

/**
 * [INTERNAL USE ONLY]
 *
 * @author Spasi
 */
interface DrawableLWJGL extends Drawable {

	void setPixelFormat(PixelFormatLWJGL pf) throws LWJGLException;

	PixelFormatLWJGL getPixelFormat();

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

	void checkGLError();

	void setSwapInterval(int swap_interval);

	void swapBuffers() throws LWJGLException;

	void initContext(final float r, final float g, final float b);

}