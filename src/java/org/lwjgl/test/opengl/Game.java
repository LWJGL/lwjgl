/* 
 * Copyright (c) 2002-2004 LWJGL Project
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

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.Util;

public final class Game {
	static {
		try {
			//find first display mode that allows us 640*480*16
			int mode = -1;
			DisplayMode[] modes = Display.getAvailableDisplayModes();
			for (int i = 0; i < modes.length; i++) {
				if (modes[i].getWidth() == 640 && modes[i].getHeight() == 480 && modes[i].getBitsPerPixel() >= 16) {
					mode = i;
					break;
				}
			}
			if (mode != -1) {
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
	/** Is the game finished? */
	private static boolean	finished;
	/** A rotating square! */
	private static float	angle;

	/**
	 * No construction allowed
	 */
	private Game() {
	}
	public static void main(String[] arguments) {
		try {
			init();
			while (!finished) {
				if (!Display.isVisible()) {
					Thread.sleep(200);
				} else if (Display.isCloseRequested()) {
					finished = true;
				} else {
					mainLoop();
					render();
				}
				Display.update();
			}
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			cleanup();
		}
	}
	/**
	 * All calculations are done in here
	 */
	private static void mainLoop() {
		angle += 1f;
		if (angle > 360.0f) angle = 0.0f;
		if (Mouse.getDX() != 0 || Mouse.getDY() != 0 || Mouse.getDWheel() != 0)
			System.out.println("Mouse moved " + Mouse.getDX() + " " + Mouse.getDY() + " " + Mouse.getDWheel());
		for (int i = 0; i < Mouse.getButtonCount(); i++)
			if (Mouse.isButtonDown(i)) System.out.println("Button " + i + " down");
		for (int i = 0; i < Keyboard.getNumKeyboardEvents(); i++) {
			Keyboard.next();
			if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState()) finished = true;
			if (Keyboard.getEventKey() == Keyboard.KEY_T && Keyboard.getEventKeyState())
				System.out.println("Current time: " + Sys.getTime());
		}
	}
	/**
	 * All rendering is done in here
	 */
	private static void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glPushMatrix();
		GL11.glTranslatef(Display.getDisplayMode().getWidth() / 2, Display.getDisplayMode().getHeight() / 2, 0.0f);
		GL11.glRotatef(angle, 0, 0, 1.0f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2i(-50, -50);
		GL11.glVertex2i(50, -50);
		GL11.glVertex2i(50, 50);
		GL11.glVertex2i(-50, 50);
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	/**
	 * Initialize
	 */
	private static void init()
		throws Exception {
		Sys.setProcessPriority(Sys.HIGH_PRIORITY);
		System.out.println("Timer resolution: " + Sys.getTimerResolution());
		System.out.println("Number of texture units: " + Util.glGetInteger(GL13.GL_MAX_TEXTURE_UNITS));
	}
	/**
	 * Cleanup
	 */
	private static void cleanup() {
		Display.destroy();
	}
}
