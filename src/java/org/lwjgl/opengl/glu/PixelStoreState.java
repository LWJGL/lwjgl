package org.lwjgl.opengl.glu;

import org.lwjgl.opengl.GL11;

/**
 * PixelStoreState.java
 *
 *
 * Created 11-jan-2004
 * @author Erik Duijs
 */
class PixelStoreState extends Util {

	public int unpackRowLength = glGetIntegerv(GL11.GL_UNPACK_ROW_LENGTH);
	public int unpackAlignment = glGetIntegerv(GL11.GL_UNPACK_ALIGNMENT);
	public int unpackSkipRows = glGetIntegerv(GL11.GL_UNPACK_SKIP_ROWS);
	public int unpackSkipPixels = glGetIntegerv(GL11.GL_UNPACK_SKIP_PIXELS);
	public int packRowLength = glGetIntegerv(GL11.GL_PACK_ROW_LENGTH);
	public int packAlignment = glGetIntegerv(GL11.GL_PACK_ALIGNMENT);
	public int packSkipRows = glGetIntegerv(GL11.GL_PACK_SKIP_ROWS);
	public int packSkipPixels = glGetIntegerv(GL11.GL_PACK_SKIP_PIXELS);

	/**
	 * Constructor for PixelStoreState.
	 */
	PixelStoreState() {
		super();
		load();
	}

	public void load() {
		unpackRowLength = glGetIntegerv(GL11.GL_UNPACK_ROW_LENGTH);
		unpackAlignment = glGetIntegerv(GL11.GL_UNPACK_ALIGNMENT);
		unpackSkipRows = glGetIntegerv(GL11.GL_UNPACK_SKIP_ROWS);
		unpackSkipPixels = glGetIntegerv(GL11.GL_UNPACK_SKIP_PIXELS);
		packRowLength = glGetIntegerv(GL11.GL_PACK_ROW_LENGTH);
		packAlignment = glGetIntegerv(GL11.GL_PACK_ALIGNMENT);
		packSkipRows = glGetIntegerv(GL11.GL_PACK_SKIP_ROWS);
		packSkipPixels = glGetIntegerv(GL11.GL_PACK_SKIP_PIXELS);
	}

	public void save() {
		GL11.glPixelStorei(GL11.GL_UNPACK_ROW_LENGTH, unpackRowLength);
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, unpackAlignment);
		GL11.glPixelStorei(GL11.GL_UNPACK_SKIP_ROWS, unpackSkipRows);
		GL11.glPixelStorei(GL11.GL_UNPACK_SKIP_PIXELS, unpackSkipPixels);
		GL11.glPixelStorei(GL11.GL_PACK_ROW_LENGTH, packRowLength);
		GL11.glPixelStorei(GL11.GL_PACK_ALIGNMENT, packAlignment);
		GL11.glPixelStorei(GL11.GL_PACK_SKIP_ROWS, packSkipRows);
		GL11.glPixelStorei(GL11.GL_PACK_SKIP_PIXELS, packSkipPixels);
	}

}
