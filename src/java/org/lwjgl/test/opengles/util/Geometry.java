package org.lwjgl.test.opengles.util;

import org.lwjgl.test.opengles.util.BufferObjectArray;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengles.GLES20.*;

public class Geometry {

	protected BufferObjectArray bo;

	protected final List<DrawCommand> drawCommands = new ArrayList<DrawCommand>(4);

	public Geometry() {
	}

	public Geometry(final FloatBuffer buffer) {
		update(buffer);
	}

	public void update(final FloatBuffer buffer) {
		if ( bo != null )
			destroy();

		bo = new BufferObjectArray(GL_STATIC_DRAW, buffer);
	}

	public void bind() {
		bo.enable();
	}

	public void draw() {
		for ( DrawCommand command : drawCommands )
			command.draw();
	}

	public void destroy() {
		bo.destroy();
		bo = null;

		drawCommands.clear();
	}

	public int addDrawCommand(final int mode, final int first, final int count) {
		drawCommands.add(new DrawCommand(mode, first, count));
		return count;
	}

	public static float sin(final float r) {
		return (float)Math.sin(r);
	}

	public static float cos(final float r) {
		return (float)Math.cos(r);
	}

	protected static class DrawCommand {

		private int mode;
		private int first;
		private int count;

		private DrawCommand(final int mode, final int first, final int count) {
			this.mode = mode;
			this.first = first;
			this.count = count;
		}

		void draw() {
			glDrawArrays(mode, first, count);
		}

	}

}