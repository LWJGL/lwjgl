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
 * $Id$
 *
 * Simple java test program.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

package org.lwjgl.test.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GLContext;

import static org.lwjgl.opengl.ARBBufferObject.*;
import static org.lwjgl.opengl.ARBVertexBufferObject.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public final class VBOIndexTest {

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
			Display.create();
			System.out.println("Created OpenGL.");
		} catch (Exception e) {
			System.err.println("Failed to create OpenGL due to " + e);
			System.exit(1);
		}
	}

	/**
	 * Is the game finished?
	 */
	private static boolean finished;

	/**
	 * A rotating square!
	 */
	private static float angle;
	private static int buffer_id;
	private static int indices_buffer_id;
	private static FloatBuffer vertices;
	private static ByteBuffer mapped_buffer;
	private static FloatBuffer mapped_float_buffer;
	private static IntBuffer indices;
	private static ByteBuffer mapped_indices_buffer;
	private static IntBuffer mapped_indices_int_buffer;

	public static void main(String[] arguments) {
		try {
			init();
			while ( !finished ) {
				Display.update();

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

	/**
	 * All calculations are done in here
	 */
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

	/**
	 * All rendering is done in here
	 */
	private static void render() {
		glClear(GL_COLOR_BUFFER_BIT);
		glPushMatrix();
		glTranslatef(Display.getDisplayMode().getWidth() / 2, Display.getDisplayMode().getHeight() / 2, 0.0f);
		glRotatef(angle, 0, 0, 1.0f);


		ByteBuffer new_mapped_buffer = glMapBufferARB(GL_ARRAY_BUFFER_ARB,
		                                                              GL_WRITE_ONLY_ARB,
		                                                              mapped_buffer);
		if ( new_mapped_buffer != mapped_buffer )
			mapped_float_buffer = new_mapped_buffer.order(ByteOrder.nativeOrder()).asFloatBuffer();
		mapped_buffer = new_mapped_buffer;

		new_mapped_buffer = glMapBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB,
		                                                   GL_WRITE_ONLY_ARB,
		                                                   mapped_indices_buffer);
		if ( new_mapped_buffer != mapped_indices_buffer )
			mapped_indices_int_buffer = new_mapped_buffer.order(ByteOrder.nativeOrder()).asIntBuffer();
		mapped_indices_buffer = new_mapped_buffer;

		mapped_float_buffer.rewind();
		vertices.rewind();
		mapped_float_buffer.put(vertices);

		mapped_indices_int_buffer.rewind();
		indices.rewind();
		mapped_indices_int_buffer.put(indices);
		if ( glUnmapBufferARB(GL_ARRAY_BUFFER_ARB) &&
		     glUnmapBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB) ) {
			glDrawElements(GL_QUADS, 4, GL_UNSIGNED_INT, 0);
		}
		glPopMatrix();
	}

	/**
	 * Initialize
	 */
	private static void init() throws Exception {

		System.out.println("Timer resolution: " + Sys.getTimerResolution());
		// Go into orthographic projection mode.
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluOrtho2D(0, Display.getDisplayMode().getWidth(), 0, Display.getDisplayMode().getHeight());
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glViewport(0, 0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());
		if ( !GLContext.getCapabilities().GL_ARB_vertex_buffer_object ) {
			System.out.println("ARB VBO not supported!");
			System.exit(1);
		}
		IntBuffer int_buffer = ByteBuffer.allocateDirect(8).order(ByteOrder.nativeOrder()).asIntBuffer();
		glGenBuffersARB(int_buffer);
		buffer_id = int_buffer.get(0);
		indices_buffer_id = int_buffer.get(1);
		glBindBufferARB(GL_ARRAY_BUFFER_ARB, buffer_id);
		glBindBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB, indices_buffer_id);
		vertices = ByteBuffer.allocateDirect(2 * 4 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		vertices.put(-50).put(-50).put(50).put(-50).put(50).put(50).put(-50).put(50);
		vertices.rewind();
		indices = ByteBuffer.allocateDirect(4 * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
		indices.put(0).put(1).put(2).put(3);
		indices.rewind();
		glBufferDataARB(GL_ARRAY_BUFFER_ARB, 2 * 4 * 4, GL_STREAM_DRAW_ARB);
		glBufferDataARB(GL_ELEMENT_ARRAY_BUFFER_ARB, 4 * 4, GL_STREAM_DRAW_ARB);
		glEnableClientState(GL_VERTEX_ARRAY);
		glVertexPointer(2, GL_FLOAT, 0, 0);
	}

	/**
	 * Cleanup
	 */
	private static void cleanup() {
		IntBuffer int_buffer = ByteBuffer.allocateDirect(8).order(ByteOrder.nativeOrder()).asIntBuffer();
		int_buffer.put(0, buffer_id);
		int_buffer.put(1, indices_buffer_id);
		glDeleteBuffersARB(int_buffer);
		Display.destroy();
	}
}
