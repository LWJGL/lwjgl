/*
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of
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

import org.lwjgl.Display;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Window;

/**
 * $Id$
 * 
 * Tests the windowing functions. ESCAPE quits.
 * 
 * @author $author$
 * @version $revision$
 */
public class BouncingWindowTest {
	public static void main(String[] args) {

		int x = 0, y = 0, dx = 1, dy = 1;
		try {
			Window.create("Bouncing Window Test", 0, 0, 64, 64, true, 16, 0, 16, 8, 0);
		} catch (LWJGLException e) {
			e.printStackTrace(System.err);
			System.exit(-1);
		}
		
		Window.setVSyncEnabled(true);

		float angle = 0.0f;
		float color = 0.0f;
		while (!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !Window.isCloseRequested()) {
			Window.update();
			x += dx;
			y += dy;
			if (x < 0) {
				x = 1;
				dx = 1;
			} else if (x >= Display.getWidth() - Window.getWidth()) {
				x = Display.getWidth() - Window.getWidth() - 1;
				dx = -1;
			}
			if (y < 0) {
				y = 1;
				dy = 1;
			} else if (y >= Display.getHeight() - Window.getHeight()) {
				y = Display.getHeight() - Window.getHeight() - 1;
				dy = -1;
			}
			
			Window.setLocation(x, y);
			angle += 1.0f;
			if (angle >= 360.0f) {
				angle = 0.0f;
			}
			color += 0.01f;
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			GL11.glPushMatrix();
			GL11.glTranslatef(Window.getWidth() / 2.0f, Window.getHeight() / 2.0f, 0.0f);
			GL11.glRotatef(angle, 0.0f, 0.0f, 1.0f);
			GL11.glColor3f(
					(float)Math.abs(Math.sin(color)), 
					(float)Math.abs(Math.cos(color)),  
					(float)Math.abs(Math.sin(color) * Math.cos(color))
					);
			GL11.glBegin(GL11.GL_QUADS);
			{
				GL11.glVertex2f(-20.0f, -20.0f);
				GL11.glVertex2f(20.0f, -20.0f);
				GL11.glVertex2f(20.0f, 20.0f);
				GL11.glVertex2f(-20.0f, 20.0f);
			}
			GL11.glEnd();
			GL11.glPopMatrix();
		}
		
	}
}
