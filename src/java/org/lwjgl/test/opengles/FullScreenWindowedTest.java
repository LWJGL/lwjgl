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
package org.lwjgl.test.opengles;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengles.PixelFormat;
import org.lwjgl.opengles.PowerManagementEventException;
import org.lwjgl.util.vector.Vector2f;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengles.GLES20.*;
import static org.lwjgl.test.opengles.util.GLMatrix.*;

/**
 * Tests switching between windowed and fullscreen
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision: 3172 $
 *          $Id: FullScreenWindowedTest.java 3172 2008-12-28 19:30:43Z elias_naur $
 */
public class FullScreenWindowedTest {

	/** Intended deiplay mode */
	private DisplayMode mode;
	/** our quad moving around */
	private Vector2f    quadPosition;
	/** our quadVelocity */
	private Vector2f    quadVelocity;
	/** angle of quad */
	private float       angle;
	/** degrees to rotate per frame */
	private              float angleRotation = 1.0f;
	/** Max speed of all changable attributes */
	private static final float MAX_SPEED     = 20.0f;

	private static int buffer_id;
	private static int indices_buffer_id;

	private QuadRenderer renderer;

	/** Creates a FullScreenWindowedTest */
	public FullScreenWindowedTest() {
	}

	/** Executes the test */
	public void execute() {
		initialize();
		mainLoop();
		cleanup();
	}

	private void switchMode() throws LWJGLException {
		mode = findDisplayMode(1024, 600, Display.getDisplayMode().getBitsPerPixel());
		try {
			Display.setDisplayModeAndFullscreen(mode);
		} catch (PowerManagementEventException e) {
			e.printStackTrace();
		}
	}

	/** Initializes the test */
	private void initialize() {
		try {
			//find displaymode
			switchMode();

			quadPosition = new Vector2f(100f, 100f);
			quadVelocity = new Vector2f(1.0f, 1.0f);

			reinit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void reinit() throws LWJGLException {
		Display.create(new PixelFormat());
		glInit();
		renderer = new QuadRenderer();
	}

	/** Runs the main loop of the "test" */
	private void mainLoop() {
		while ( !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !Display.isCloseRequested() ) {
			if ( Display.isVisible() ) {
				// check keyboard input
				processKeyboard();
				// do "game" logic, and render it
				logic();
				render();
			} else {
				// no need to render/paint if nothing has changed (ie. window
				// dragged over)
				if ( Display.isDirty() ) {
					render();
				}
				// don't waste cpu time, sleep more
				try {
					Thread.sleep(100);
				} catch (InterruptedException inte) {
				}
			}
			// Update window
			try {
				Display.update();
				Display.sync(60);
			} catch (PowerManagementEventException e) {
				e.printStackTrace();
			}
		}
	}

	/** Performs the logic */
	private void logic() {
		angle += angleRotation;
		if ( angle > 90.0f ) {
			angle = 0.0f;
		}
		quadPosition.x += quadVelocity.x;
		quadPosition.y += quadVelocity.y;
		//check colision with vertical border border
		if ( quadPosition.x + 50 >= mode.getWidth() || quadPosition.x - 50 <= 0 ) {
			quadVelocity.x *= -1;
		}
		//check collision with horizontal border
		if ( quadPosition.y + 50 >= mode.getHeight() || quadPosition.y - 50 <= 0 ) {
			quadVelocity.y *= -1;
		}
	}

	private void render() {
		//clear background
		glClear(GL_COLOR_BUFFER_BIT);
		// draw white quad
		glPushMatrix();
		{
			glTranslatef(quadPosition.x, quadPosition.y, 0);
			glRotatef(angle, 0.0f, 0.0f, 1.0f);

			renderer.setMVPUniform();

			glDrawElements(GL_TRIANGLE_STRIP, 4, GL_UNSIGNED_INT, 0);
		}
		glPopMatrix();
	}

	/** Processes keyboard input */
	private void processKeyboard() {
		//check for fullscreen key
		if ( Keyboard.isKeyDown(Keyboard.KEY_F) ) {
			try {
				cleanup();

				switchMode();

				reinit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//check for window key
		if ( Keyboard.isKeyDown(Keyboard.KEY_W) ) {
			try {
				cleanup();

				mode = new DisplayMode(800, 480);
				Display.setDisplayModeAndFullscreen(mode);

				reinit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//check for speed changes
		if ( Keyboard.isKeyDown(Keyboard.KEY_UP) ) {
			quadVelocity.y += 0.1f;
		}
		if ( Keyboard.isKeyDown(Keyboard.KEY_DOWN) ) {
			quadVelocity.y -= 0.1f;
		}
		if ( Keyboard.isKeyDown(Keyboard.KEY_RIGHT) ) {
			quadVelocity.x += 0.1f;
		}
		if ( Keyboard.isKeyDown(Keyboard.KEY_LEFT) ) {
			quadVelocity.x -= 0.1f;
		}
		if ( Keyboard.isKeyDown(Keyboard.KEY_ADD) ) {
			angleRotation += 0.1f;
		}
		if ( Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT) ) {
			angleRotation -= 0.1f;
		}
		//throttle
		if ( quadVelocity.x < -MAX_SPEED ) {
			quadVelocity.x = -MAX_SPEED;
		}
		if ( quadVelocity.x > MAX_SPEED ) {
			quadVelocity.x = MAX_SPEED;
		}
		if ( quadVelocity.y < -MAX_SPEED ) {
			quadVelocity.y = -MAX_SPEED;
		}
		if ( quadVelocity.y > MAX_SPEED ) {
			quadVelocity.y = MAX_SPEED;
		}
		if ( angleRotation < 0.0f ) {
			angleRotation = 0.0f;
		}
		if ( angleRotation > MAX_SPEED ) {
			angleRotation = MAX_SPEED;
		}
	}

	/** Cleans up the test */
	private void cleanup() {
		renderer.cleanup();

		IntBuffer int_buffer = BufferUtils.createIntBuffer(2);
		int_buffer.put(0, buffer_id);
		int_buffer.put(1, indices_buffer_id);

		glDeleteBuffers(int_buffer);

		Display.destroy();
	}

	/**
	 * Retrieves a displaymode, if one such is available
	 *
	 * @param width  Required width
	 * @param height Required height
	 * @param bpp    Minimum required bits per pixel
	 *
	 * @return
	 */
	private static DisplayMode findDisplayMode(int width, int height, int bpp) throws LWJGLException {
		DisplayMode[] modes = Display.getAvailableDisplayModes();
		for ( int i = 0; i < modes.length; i++ ) {
			if ( modes[i].getWidth() == width && modes[i].getHeight() == height && modes[i].getBitsPerPixel() >= bpp && modes[i].getFrequency() <= 60 ) {
				return modes[i];
			}
		}
		return Display.getDesktopDisplayMode();
	}

	/** Initializes OGL */
	private void glInit() {
		// Go into orthographic projection mode.
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, mode.getWidth(), 0, mode.getHeight(), -1.0f, 1.0f);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glViewport(0, 0, mode.getWidth(), mode.getHeight());
		//set clear color to black
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		//sync frame (only works on windows)
		Display.setVSyncEnabled(true);

		final IntBuffer int_buffer = BufferUtils.createIntBuffer(2);
		glGenBuffers(int_buffer);
		buffer_id = int_buffer.get(0);
		indices_buffer_id = int_buffer.get(1);

		glBindBuffer(GL_ARRAY_BUFFER, buffer_id);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indices_buffer_id);

		final FloatBuffer vertices = BufferUtils.createFloatBuffer(2 * 4);
		vertices
			.put(-50).put(-50)
			.put(50).put(-50)
			.put(-50).put(50)
			.put(50).put(50);
		vertices.rewind();

		final IntBuffer indices = BufferUtils.createIntBuffer(4);
		indices.put(0).put(1).put(2).put(3);
		indices.rewind();

		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
	}

	/** Test entry point */
	public static void main(String[] args) {
		System.out.println("Change between fullscreen and windowed mode, by pressing F and W respectively");
		System.out.println("Move quad using arrowkeys, and change rotation using +/-");
		FullScreenWindowedTest fswTest = new FullScreenWindowedTest();
		fswTest.execute();
		System.exit(0);
	}
}
