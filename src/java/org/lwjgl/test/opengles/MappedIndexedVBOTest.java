/*
 * Copyright (c) 2002-2008 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * $Id: VBOIndexTest.java 2983 2008-04-07 18:36:09Z matzon $
 *
 * Simple java test program.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision: 2983 $
 */

package org.lwjgl.test.opengles;

import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengles.GLContext;
import org.lwjgl.opengles.PixelFormat;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengles.GLES20.*;
import static org.lwjgl.opengles.OESMapbuffer.*;
import static org.lwjgl.test.opengles.util.GLMatrix.*;

public final class MappedIndexedVBOTest {

	static {
		try {
			//find first display mode that allows us 640*480*16
			int mode = -1;
			DisplayMode[] modes = Display.getAvailableDisplayModes();
			for ( int i = 0; i < modes.length; i++ ) {
				if ( modes[i].getWidth() == 640
				     && modes[i].getHeight() == 480
				     && modes[i].getBitsPerPixel() >= 16 ) {
					mode = i;
					break;
				}
			}
			if ( mode != -1 ) {
				//select above found displaymode
				System.out.println("Setting display mode to " + modes[mode]);
				Display.setDisplayMode(modes[mode]);
				System.out.println("Created display.");
			}
		} catch (Exception e) {
			System.err.println("Failed to create display due to " + e);
		}
	}

	static {
		try {
			Display.create(new PixelFormat());
			System.out.println("Created OpenGL.");

			if ( !GLContext.getCapabilities().GL_OES_mapbuffer ) {
				System.out.println("GL_OES_mapbuffer is not supported, quitting!");
				System.exit(0);
			}
		} catch (Exception e) {
			System.err.println("Failed to create OpenGL due to " + e);
			System.exit(1);
		}
	}

	/** Is the game finished? */
	private static boolean finished;

	/** A rotating square! */
	private static float       angle;
	private static int         buffer_id;
	private static int         indices_buffer_id;
	private static FloatBuffer vertices;
	private static ByteBuffer  mapped_buffer;
	private static FloatBuffer mapped_float_buffer;
	private static IntBuffer   indices;
	private static ByteBuffer  mapped_indices_buffer;
	private static IntBuffer   mapped_indices_int_buffer;

	private static QuadRenderer renderer;

	public static void main(String[] arguments) {
		try {
			init();
			while ( !finished ) {
				Display.update();
				Display.sync(30);

				if ( !Display.isVisible() )
					Thread.sleep(200);
				else if ( Display.isCloseRequested() )
					System.exit(0);

				mainLoop();
				render();
			}
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			cleanup();
		}
		System.exit(0);
	}

	/** All calculations are done in here */
	private static void mainLoop() {
		angle += 1f;
		if ( angle > 360.0f )
			angle = 0.0f;

		if ( Mouse.getDX() != 0 || Mouse.getDY() != 0 || Mouse.getDWheel() != 0 )
			System.out.println("Mouse moved " + Mouse.getDX() + " " + Mouse.getDY() + " " + Mouse.getDWheel());
		for ( int i = 0; i < Mouse.getButtonCount(); i++ )
			if ( Mouse.isButtonDown(i) )
				System.out.println("Button " + i + " down");
		if ( Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) )
			finished = true;
		for ( int i = 0; i < Keyboard.getNumKeyboardEvents(); i++ ) {
			Keyboard.next();
			if ( Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState() )
				finished = true;
			if ( Keyboard.getEventKey() == Keyboard.KEY_T && Keyboard.getEventKeyState() )
				System.out.println("Current time: " + Sys.getTime());
		}
	}

	/** All rendering is done in here */
	private static void render() {
		glClear(GL_COLOR_BUFFER_BIT);

		glPushMatrix();
		glTranslatef(Display.getDisplayMode().getWidth() / 2, Display.getDisplayMode().getHeight() / 2, 0.0f);
		glRotatef(angle, 0, 0, 1.0f);

		renderer.setMVPUniform();

		ByteBuffer new_mapped_buffer = glMapBufferOES(GL_ARRAY_BUFFER, GL_WRITE_ONLY_OES, mapped_buffer);
		if ( new_mapped_buffer != mapped_buffer ) {
			mapped_buffer = new_mapped_buffer;
			mapped_float_buffer = new_mapped_buffer.asFloatBuffer();
		}

		new_mapped_buffer = glMapBufferOES(GL_ELEMENT_ARRAY_BUFFER, GL_WRITE_ONLY_OES, mapped_indices_buffer);
		if ( new_mapped_buffer != mapped_indices_buffer ) {
			mapped_indices_buffer = new_mapped_buffer;
			mapped_indices_int_buffer = new_mapped_buffer.asIntBuffer();
		}

		mapped_float_buffer.rewind();
		vertices.rewind();
		mapped_float_buffer.put(vertices);

		mapped_indices_int_buffer.rewind();
		indices.rewind();
		mapped_indices_int_buffer.put(indices);
		if ( glUnmapBufferOES(GL_ARRAY_BUFFER) &&
		     glUnmapBufferOES(GL_ELEMENT_ARRAY_BUFFER) ) {
			glDrawElements(GL_TRIANGLE_STRIP, 4, GL_UNSIGNED_INT, 0);
		}

		glPopMatrix();
	}

	/** Initialize */
	private static void init() throws Exception {
		System.out.println("Timer resolution: " + Sys.getTimerResolution());

		// Go into orthographic projection mode.
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getDisplayMode().getWidth(), 0, Display.getDisplayMode().getHeight(), -1.0f, 1.0f);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glViewport(0, 0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());

		final IntBuffer int_buffer = BufferUtils.createIntBuffer(2);
		glGenBuffers(int_buffer);
		buffer_id = int_buffer.get(0);
		indices_buffer_id = int_buffer.get(1);

		glBindBuffer(GL_ARRAY_BUFFER, buffer_id);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indices_buffer_id);

		vertices = BufferUtils.createFloatBuffer(2 * 4);
		vertices
			.put(-50).put(-50)
			.put(50).put(-50)
			.put(-50).put(50)
			.put(50).put(50);
		vertices.rewind();

		indices = BufferUtils.createIntBuffer(4);
		indices.put(0).put(1).put(2).put(3);
		indices.rewind();

		glBufferData(GL_ARRAY_BUFFER, 2 * 4 * 4, GL_STREAM_DRAW);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, 4 * 4, GL_STREAM_DRAW);

		renderer = new QuadRenderer();
	}

	/** Cleanup */
	private static void cleanup() {
		renderer.cleanup();

		IntBuffer int_buffer = BufferUtils.createIntBuffer(2);
		int_buffer.put(0, buffer_id);
		int_buffer.put(1, indices_buffer_id);

		glDeleteBuffers(int_buffer);

		Display.destroy();
	}

}