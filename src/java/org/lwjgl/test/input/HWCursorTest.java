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
package org.lwjgl.test.input;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 *
 * Tests switching between windowed and fullscreen - including hardware cursor test
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class HWCursorTest {

	/** The native cursor */
	private static Cursor[] cursors;

	/** The mouse cursor position */
	private static int mouse_x;
	private static int mouse_y;
	private static int mouse_btn;

	/**
	 * Executes the test
	 */
	public void execute() {
		initialize();

		mainLoop();

		cleanup();
	}

	/**
	 * Sets the display mode for fullscreen mode
	 */
	protected boolean setDisplayMode() {
		try {
			// get modes
			DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(640, 480, -1, -1, -1, -1, 60, 60);

			org.lwjgl.util.Display.setDisplayMode(dm, new String[] {
					"width=" + 640,
					"height=" + 480,
					"freq=" + 60,
					"bpp=" + org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel()
			});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Initializes the test
	 */
	private void initialize() {
		try {
			// start of in windowed mode
			setDisplayMode();
			Display.create();

			glInit();

			initNativeCursors();

		} catch (Exception e) {
			e.printStackTrace();
      System.exit(-1);
		}
	}

	private static void initNativeCursors() throws Exception {
		if ((Cursor.getCapabilities() & Cursor.CURSOR_ONE_BIT_TRANSPARENCY) == 0) {
			System.out.println("No HW cursor support!");
			System.exit(0);
		}

		cursors = new Cursor[3];

		int cursorImageCount = 1;
		int cursorWidth = Math.min(64, Cursor.getMaxCursorSize());
		int cursorHeight = cursorWidth;
		IntBuffer cursorImages;
		IntBuffer cursorDelays;


		// Create a single cursor
		// ==================================
		cursorImages = ByteBuffer.allocateDirect(cursorWidth*cursorHeight*cursorImageCount*4).order(ByteOrder.nativeOrder()).asIntBuffer();
		cursorDelays = null;
		for(int j=0; j<cursorWidth; j++) {
			for(int l=0; l<cursorHeight; l++) {
				cursorImages.put(0xffffffff);
			}
		}
		cursorImages.flip();
		cursors[0] = new Cursor(cursorWidth, cursorHeight, cursorWidth/2, cursorHeight/2, cursorImageCount, cursorImages, cursorDelays);
		// ----------------------------------

		// Create 3 piece animation
		// ==================================
		cursorImageCount = 3;
		cursorImages = ByteBuffer.allocateDirect(cursorWidth*cursorHeight*cursorImageCount*4).order(ByteOrder.nativeOrder()).asIntBuffer();
		cursorDelays = ByteBuffer.allocateDirect(cursorImageCount*4).order(ByteOrder.nativeOrder()).asIntBuffer();
		for(int i=0; i<cursorImageCount; i++) {

			// make a colored square with a chocolate center
			int offColor = 0x00000000;
			int onColor = 0xffff0000;

			// change color according to cursor
			if(i == 1) {
				onColor = 0xff00ff00;
			} else if (i == 2) {
				onColor = 0xff0000ff;
			}

			// calculate size of center
			int centerSize  = (cursorWidth / 5) * (i + 1);
			int centerLeft  = cursorWidth / 2 - centerSize / 2;
			int centerRight = cursorWidth / 2 + centerSize / 2;

			// go!
			for(int j=0; j<cursorWidth; j++) {
				for(int l=0; l<cursorHeight; l++) {
					if(j >= centerLeft && j < centerRight && l >= centerLeft && l < centerRight) {
						cursorImages.put(offColor);
					} else {
						cursorImages.put(onColor);
					}
				}
			}
		}
		cursorDelays.put(2000).put(2000).put(2000);
		cursorDelays.flip();
		cursorImages.flip();

		cursors[1] = new Cursor(cursorWidth, cursorHeight, cursorWidth/2, cursorHeight/2, cursorImageCount, cursorImages, cursorDelays);
		// ----------------------------------


		// Create a 20 piece animation
		// ==================================
		cursorImageCount = 20;
		cursorImages = ByteBuffer.allocateDirect(cursorWidth*cursorHeight*cursorImageCount*4).order(ByteOrder.nativeOrder()).asIntBuffer();
		cursorDelays = ByteBuffer.allocateDirect(cursorImageCount*4).order(ByteOrder.nativeOrder()).asIntBuffer();
		cursorDelays.put(
										 new int[] {
										 		100, 100, 100, 100, 100,
												100, 100, 100, 100, 100,
												100, 100, 100, 100, 100,
												100, 100, 100, 100, 100
										 });

		float step = 0xffffffff / 20.0f;
		for(int i=0; i<cursorImageCount; i++) {
			for(int j=0; j<cursorWidth; j++) {
				for(int l=0; l<cursorHeight; l++) {
					cursorImages.put((int)step);
				}
			}
			step += step;
		}
		cursorImages.flip();
		cursorDelays.flip();
		cursors[2] = new Cursor(cursorWidth, cursorHeight, cursorWidth/2, cursorHeight/2, cursorImageCount, cursorImages, cursorDelays);
		// ----------------------------------

		Mouse.setNativeCursor(cursors[0]);
	}

	/**
	 * Runs the main loop of the "test"
	 */
	private void mainLoop() {
		while (!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)
				&& !Display.isCloseRequested()) {
			// allow subsystem to get a chance to run too
			Display.update();

			if (Display.isVisible()) {
				// check keyboard input
				processKeyboard();
				processMouse();

				render();
			} else {

				// no need to render/paint if nothing has changed (ie. window dragged over)
				if (Display.isDirty()) {
					render();
				}

				// don't waste cpu time, sleep more
				try {
					Thread.sleep(100);
				} catch (InterruptedException inte) {
				}
			}
		}
	}

	/**
	 * Performs the logic
	 */
	private void render() {
		//clear background
		glClear(GL_COLOR_BUFFER_BIT);

		// draw white quad
		glPushMatrix();
		{
			glTranslatef(mouse_x, mouse_y, 0);
			glColor3f(1.0f, 1.0f, 1.0f);
			glBegin(GL_QUADS);
			{
				glColor3f(1.0f, 0.0f, 0.0f); glVertex2i(-50, -50);
				glColor3f(0.0f, 1.0f, 0.0f); glVertex2i(50, -50);
				glColor3f(0.0f, 0.0f, 1.0f); glVertex2i(50, 50);
				glColor3f(1.0f, 0.0f, 1.0f); glVertex2i(-50, 50);
			}
			glEnd();
		}
		glPopMatrix();
	}

	private void processMouse() {
		mouse_x = Mouse.getX();
		mouse_y = Mouse.getY();

		while(Mouse.next()) {
			int button = Mouse.getEventButton();
			if(button >= 0 && button < 3 && Mouse.getEventButtonState()) {
				mouse_btn = Mouse.getEventButton();
				switchCursor();
			}
		}
	}

	/**
	 * Processes keyboard input
	 */
	private void processKeyboard() {
		//check for fullscreen key
		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {

			try {
				try {
					Mouse.setNativeCursor(null);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
				for ( Cursor aCursor : cursors ) {
					aCursor.destroy();
				}
				Display.setFullscreen(true);

				glInit();

				initNativeCursors();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//check for window key
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			try {
				try {
					Mouse.setNativeCursor(null);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
				for ( Cursor cursor : cursors ) {
					cursor.destroy();
				}
				Display.setFullscreen(false);
				glInit();

				initNativeCursors();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
			try {
				Mouse.setNativeCursor(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
			switchCursor();
		}

		while(Keyboard.next()) {
			if(Keyboard.getEventKey() == Keyboard.KEY_SPACE && Keyboard.getEventKeyState()) {
				Mouse.setGrabbed(!Mouse.isGrabbed());
			}
		}
	}

	private void switchCursor() {
		try {
			Mouse.setNativeCursor(cursors[mouse_btn]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  Cleans up the test
	 */
	private void cleanup() {
		try {
			Mouse.setNativeCursor(null);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		for ( Cursor cursor : cursors ) {
			cursor.destroy();
		}
		Display.destroy();
	}

	/**
	 * Initializes OGL
	 */
	private void glInit() {
		// Go into orthographic projection mode.
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluOrtho2D(0, Display.getDisplayMode().getWidth(), 0, Display.getDisplayMode().getHeight());
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glViewport(0, 0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());

		//set clear color to black
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		//sync frame (only works on windows)
		Display.setVSyncEnabled(true);
	}

	/**
	 * Test entry point
	 */
	public static void main(String[] args) {
		System.out.println("Change between fullscreen and windowed mode, by pressing F and W respectively. Enable hw cursor with N and disable it with M.");
		HWCursorTest cursorTest = new HWCursorTest();
		cursorTest.execute();
		System.exit(0);
	}
}
