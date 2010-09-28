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
package org.lwjgl.test.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 *
 * Tests switching between windowed and fullscreen
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class FullScreenWindowedTest {
	/** Intended deiplay mode */
	private DisplayMode			mode;
	/** our quad moving around */
	private Vector2f			quadPosition;
	/** our quadVelocity */
	private Vector2f			quadVelocity;
	/** angle of quad */
	private float				angle;
	/** degrees to rotate per frame */
	private float				angleRotation	= 1.0f;
	/** Max speed of all changable attributes */
	private static final float	MAX_SPEED		= 20.0f;

	/**
	 * Creates a FullScreenWindowedTest
	 */
	public FullScreenWindowedTest() {
	}
	/**
	 * Executes the test
	 */
	public void execute() {
		initialize();
		mainLoop();
		cleanup();
	}

	private void switchMode() throws LWJGLException {
		mode = findDisplayMode(800, 600, Display.getDisplayMode().getBitsPerPixel());
		Display.setDisplayModeAndFullscreen(mode);
	}

	/**
	 * Initializes the test
	 */
	private void initialize() {
		try {
			//find displaymode
			switchMode();
			// start of in windowed mode
			Display.create();
			glInit();
			quadPosition = new Vector2f(100f, 100f);
			quadVelocity = new Vector2f(1.0f, 1.0f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Runs the main loop of the "test"
	 */
	private void mainLoop() {
		while (!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !Display.isCloseRequested()) {
			if (Display.isVisible()) {
				// check keyboard input
				processKeyboard();
				// do "game" logic, and render it
				logic();
				render();
			} else {
				// no need to render/paint if nothing has changed (ie. window
				// dragged over)
				if (Display.isDirty()) {
					render();
				}
				// don't waste cpu time, sleep more
				try {
					Thread.sleep(100);
				} catch (InterruptedException inte) {
				}
			}
			// Update window
			Display.update();
		}
	}
	/**
	 * Performs the logic
	 */
	private void logic() {
		angle += angleRotation;
		if (angle > 90.0f) {
			angle = 0.0f;
		}
		quadPosition.x += quadVelocity.x;
		quadPosition.y += quadVelocity.y;
		//check colision with vertical border border
		if (quadPosition.x + 50 >= mode.getWidth() || quadPosition.x - 50 <= 0) {
			quadVelocity.x *= -1;
		}
		//check collision with horizontal border
		if (quadPosition.y + 50 >= mode.getHeight() || quadPosition.y - 50 <= 0) {
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
			glColor3f(1.0f, 1.0f, 1.0f);
			glBegin(GL_QUADS);
			{
				glVertex2i(-50, -50);
				glVertex2i(50, -50);
				glVertex2i(50, 50);
				glVertex2i(-50, 50);
			}
			glEnd();
		}
		glPopMatrix();
	}
	/**
	 * Processes keyboard input
	 */
	private void processKeyboard() {
		//check for fullscreen key
		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			try {
				switchMode();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//check for window key
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			try {
				mode = new DisplayMode(640, 480);
				Display.setDisplayModeAndFullscreen(mode);
				glInit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//check for speed changes
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			quadVelocity.y += 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			quadVelocity.y -= 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			quadVelocity.x += 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			quadVelocity.x -= 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ADD)) {
			angleRotation += 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT)) {
			angleRotation -= 0.1f;
		}
		//throttle
		if (quadVelocity.x < -MAX_SPEED) {
			quadVelocity.x = -MAX_SPEED;
		}
		if (quadVelocity.x > MAX_SPEED) {
			quadVelocity.x = MAX_SPEED;
		}
		if (quadVelocity.y < -MAX_SPEED) {
			quadVelocity.y = -MAX_SPEED;
		}
		if (quadVelocity.y > MAX_SPEED) {
			quadVelocity.y = MAX_SPEED;
		}
		if (angleRotation < 0.0f) {
			angleRotation = 0.0f;
		}
		if (angleRotation > MAX_SPEED) {
			angleRotation = MAX_SPEED;
		}

		while ( Mouse.next() );
	}
	/**
	 * Cleans up the test
	 */
	private void cleanup() {
		Display.destroy();
	}

	/**
	 * Retrieves a displaymode, if one such is available
	 *
	 * @param width
	 *            Required width
	 * @param height
	 *            Required height
	 * @param bpp
	 *            Minimum required bits per pixel
	 * @return
	 */
	private DisplayMode findDisplayMode(int width, int height, int bpp) throws LWJGLException {
		DisplayMode[] modes = Display.getAvailableDisplayModes();
		for ( DisplayMode mode : modes ) {
			if ( mode.getWidth() == width && mode.getHeight() == height && mode.getBitsPerPixel() >= bpp && mode.getFrequency() <= 60 ) {
				return mode;
			}
		}
		return Display.getDesktopDisplayMode();
	}
	/**
	 * Initializes OGL
	 */
	private void glInit() {
		// Go into orthographic projection mode.
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluOrtho2D(0, mode.getWidth(), 0, mode.getHeight());
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glViewport(0, 0, mode.getWidth(), mode.getHeight());
		//set clear color to black
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		//sync frame (only works on windows)
		Display.setVSyncEnabled(true);
	}
	/**
	 * Test entry point
	 */
	public static void main(String[] args) {
		System.out.println("Change between fullscreen and windowed mode, by pressing F and W respectively");
		System.out.println("Move quad using arrowkeys, and change rotation using +/-");
		FullScreenWindowedTest fswTest = new FullScreenWindowedTest();
		fswTest.execute();
		System.exit(0);
	}
}
