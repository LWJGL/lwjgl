package org.lwjgl.opengl.glu;

import org.lwjgl.opengl.GL;

/**
 * PixelStoreState.java
 * 
 * 
 * Created 11-jan-2004
 * @author Erik Duijs
 */
class PixelStoreState extends Util implements GLUConstants {

	public int unpackRowLength = glGetIntegerv(GL.GL_UNPACK_ROW_LENGTH);
	public int unpackAlignment = glGetIntegerv(GL.GL_UNPACK_ALIGNMENT);
	public int unpackSkipRows = glGetIntegerv(GL.GL_UNPACK_SKIP_ROWS);
	public int unpackSkipPixels = glGetIntegerv(GL.GL_UNPACK_SKIP_PIXELS);
	public int packRowLength = glGetIntegerv(GL.GL_PACK_ROW_LENGTH);
	public int packAlignment = glGetIntegerv(GL.GL_PACK_ALIGNMENT);
	public int packSkipRows = glGetIntegerv(GL.GL_PACK_SKIP_ROWS);
	public int packSkipPixels = glGetIntegerv(GL.GL_PACK_SKIP_PIXELS);
	
	/**
	 * Constructor for PixelStoreState.
	 */
	public PixelStoreState() {
		super();
		load();
	}
	
	public void load() {
		unpackRowLength = glGetIntegerv(GL.GL_UNPACK_ROW_LENGTH);
		unpackAlignment = glGetIntegerv(GL.GL_UNPACK_ALIGNMENT);
		unpackSkipRows = glGetIntegerv(GL.GL_UNPACK_SKIP_ROWS);
		unpackSkipPixels = glGetIntegerv(GL.GL_UNPACK_SKIP_PIXELS);
		packRowLength = glGetIntegerv(GL.GL_PACK_ROW_LENGTH);
		packAlignment = glGetIntegerv(GL.GL_PACK_ALIGNMENT);
		packSkipRows = glGetIntegerv(GL.GL_PACK_SKIP_ROWS);
		packSkipPixels = glGetIntegerv(GL.GL_PACK_SKIP_PIXELS);
	}

	public void save() {
		GL.glPixelStorei(GL.GL_UNPACK_ROW_LENGTH, unpackRowLength);
		GL.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, unpackAlignment);
		GL.glPixelStorei(GL.GL_UNPACK_SKIP_ROWS, unpackSkipRows);
		GL.glPixelStorei(GL.GL_UNPACK_SKIP_PIXELS, unpackSkipPixels);
		GL.glPixelStorei(GL.GL_PACK_ROW_LENGTH, packRowLength);
		GL.glPixelStorei(GL.GL_PACK_ALIGNMENT, packAlignment);
		GL.glPixelStorei(GL.GL_PACK_SKIP_ROWS, packSkipRows);
		GL.glPixelStorei(GL.GL_PACK_SKIP_PIXELS, packSkipPixels);
	}

}
