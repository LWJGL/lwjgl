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
package org.lwjgl.test;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

/**
 * Small class for testing that the Window is creatable
 * If this class can't run, LWJGL wont work!
 *
 * @author Brian Matzon <brian@matzon.dk>
 */
public class WindowCreationTest {

	/** Locatable modes */
	private DisplayMode[]	located_modes;

	/** Fixed selectable modes */
	private DisplayMode[]	fixed_modes = new DisplayMode[10];


	/** Window position x */
	private int						window_x;

	/** Window position y */
	private int						window_y;

	/** Color being cleared to */
	private float					color				= 0f;

	/** Direction moving clearing color */
	private int						direction		= 1;

	/** Whether we're running */
	private boolean				running;

	/** Whether we're in fullscreen mode */
	private boolean				fullscreen;

	/**
	 * Initializes the test
	 * @return true if initialization was successfull
	 */
	public boolean initialize() {
		try {
			// get available modes, and print out
			located_modes = Display.getAvailableDisplayModes();
			System.out.println("Found " + located_modes.length + " display modes");

			// get 640x480, 800x600, 1024x768 modes
			findFixedModes();

			// create default windowed display 640*480 @ 100, 100
			setDefaultDisplayMode();

			window_x = window_y = 100;
			Display.setLocation(window_x, window_y);

			Display.create();
			return true;
		} catch (LWJGLException le) {
			le.printStackTrace();
		}
		return false;
	}

	/** Locate fixed modes */
	private void findFixedModes() {
		// get 640*480 modes
		fixed_modes[0] = getDisplayMode(640, 480, 16, -1);
		fixed_modes[1] = getDisplayMode(640, 480, 24, -1);
		fixed_modes[2] = getDisplayMode(640, 480, 32, -1);

		// get 800*600*16*60
		fixed_modes[3] = getDisplayMode(800, 600, 16, -1);
		fixed_modes[4] = getDisplayMode(800, 600, 24, -1);
		fixed_modes[5] = getDisplayMode(800, 600, 32, -1);

		// get 1024*768*16*60
		fixed_modes[6] = getDisplayMode(1024, 768, 16, -1);
		fixed_modes[7] = getDisplayMode(1024, 768, 24, -1);
		fixed_modes[8] = getDisplayMode(1024, 768, 32, -1);
	}

	/**
	 * Executes the test
	 */
	private void execute() {
		running = true;

		// wait for user to close window
		while (!Display.isCloseRequested() && running) {

			// handle input accordingly
			handleInput();

			// render something
			render();

			// update display as needed
			Display.update();

			// no need to run at full speed
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Destroys any resources used while running test
	 */
	public void destroy() {
		// nuke window and get out
		Display.destroy();
	}

	/**
	 * Handles the input
	 */
	private void handleInput() {
		while (Keyboard.next()) {

			// we only want key down events
			if (!Keyboard.getEventKeyState()) {
				continue;
			}

			// check for exit
			if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
				running = false;
			}

			// check for listing of modes
			if (Keyboard.getEventKey() == Keyboard.KEY_L) {
				for(int i=0;i<fixed_modes.length; i++) {
					System.out.println("[" + i + "]: " + fixed_modes[i]);
				}
			}

			// check for display mode
			// ================================
			if (Keyboard.getEventKey() == Keyboard.KEY_0) { setMode(0); }
			if (Keyboard.getEventKey() == Keyboard.KEY_1) { setMode(1); }
			if (Keyboard.getEventKey() == Keyboard.KEY_2) {	setMode(2); }
			if (Keyboard.getEventKey() == Keyboard.KEY_3) {	setMode(3); }
			if (Keyboard.getEventKey() == Keyboard.KEY_4) {	setMode(4); }
			if (Keyboard.getEventKey() == Keyboard.KEY_5) {	setMode(5); }
			if (Keyboard.getEventKey() == Keyboard.KEY_6) {	setMode(6); }
			if (Keyboard.getEventKey() == Keyboard.KEY_7) {	setMode(7); }
			if (Keyboard.getEventKey() == Keyboard.KEY_8) {	setMode(8); }
			// --------------------------------

			// check for window move
			// ================================
			if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
				if (!Display.isFullscreen()) {
					Display.setLocation(window_x -= 10, window_y);
				}
			}

			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
				if (!Display.isFullscreen()) {
					Display.setLocation(window_x += 10, window_y);
				}
			}

			if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
				if (!Display.isFullscreen()) {
					Display.setLocation(window_x, window_y -= 10);
				}
			}

			if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
				if (!Display.isFullscreen()) {
					Display.setLocation(window_x, window_y += 10);
				}
			}
			// --------------------------------

			// check for fullscreen
			if (Keyboard.getEventKey() == Keyboard.KEY_F) {
				try {
					if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ||
							Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
						System.out.println("Performing Display.destroy()/create() cycle");
						DisplayMode saved_mode = Display.getDisplayMode();
						Display.destroy();
						Display.setDisplayMode(saved_mode);
						Display.setFullscreen(fullscreen = !fullscreen);
						Display.create();
					} else
						Display.setFullscreen(fullscreen = !fullscreen);
				} catch (LWJGLException lwjgle) {
					lwjgle.printStackTrace();
				}
			}
		}
	}

	private void setMode(int mode) {
		if(fixed_modes[mode] == null) {
			System.out.println("Unable to set mode. Not valid: " + mode);
			return;
		}

		try {
			Display.setDisplayMode(fixed_modes[mode]);
		} catch (LWJGLException le) {
			le.printStackTrace();
			System.out.println("Exception while setting mode: " + fixed_modes[mode]);
		}
	}

	/**
	 *
	 */
	private void render() {
		glClearColor(color, color, color, 1f);
		glClear(GL_COLOR_BUFFER_BIT);

		color += direction * .05f;

		if (color > 1f) {
			color = 1f;
			direction = -1 * direction;
		} else if (color < 0f) {
			direction = -1 * direction;
			color = 0f;
		}
	}

	/**
	 * Main entry point
	 *
	 * @param args ignored params to app
	 */
	public static void main(String[] args) throws LWJGLException {

		System.out.println("The following keys are available:\n" +
		                   "ESCAPE:\t\tExit test\n" +
		                   "ARROW Keys:\tMove window when in non-fullscreen mode\n" +
		                   "L:\t\tList selectable display modes\n" +
		                   "0-8:\t\tSelection of display modes\n" +
		                   "F:\t\tToggle fullscreen\n" +
		                   "SHIFT-F:\tToggle fullscreen with Display.destroy()/create() cycle");

		WindowCreationTest wct = new WindowCreationTest();
		if (wct.initialize()) {
			wct.execute();
			wct.destroy();
		}
		System.exit(0);
	}

	/**
	 * Sets the display mode for fullscreen mode
	 */
	protected boolean setDefaultDisplayMode() {
		try {
			// get modes
			DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(640, 480, -1, -1, -1, -1, -1, -1);

			org.lwjgl.util.Display.setDisplayMode(dm, new String[] { "width=" + 640, "height=" + 480, "freq=" + 60,
					"bpp=" + org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel()});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Gets a specific display mode
	 */
	private DisplayMode getDisplayMode(int width, int height, int bpp, int freq) {
		DisplayMode[] dm = null;
		try {
			dm = org.lwjgl.util.Display.getAvailableDisplayModes(width, height, width, height, bpp, bpp, freq, freq);
			if(dm == null || dm.length == 0) {
				System.out.println("Problem retrieving mode with " + width + "x" + height + "x" + bpp + "@" + freq);
			}
		} catch (LWJGLException le) {
			le.printStackTrace();
			System.out.println("Problem retrieving mode with " + width + "x" + height + "x" + bpp + "@" + freq);
		}
		return (dm != null && dm.length != 0) ? dm[0] : null;
	}
}
