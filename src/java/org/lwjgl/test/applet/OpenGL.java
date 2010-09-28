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
package org.lwjgl.test.applet;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.AWTGLCanvas;

import static org.lwjgl.opengl.GL11.*;

public class OpenGL extends AWTGLCanvas implements Test {

	float	angle;
	float x;
	float y;

	public OpenGL() throws LWJGLException {
	}

	public void initGL() {
		glMatrixMode(GL_PROJECTION_MATRIX);
		glLoadIdentity();
		glOrtho(0, 640, 0, 480, 1, -1);
		x = 320;
		y = 240;
		glMatrixMode(GL_MODELVIEW_MATRIX);
		setVSyncEnabled(true);
	}

	public void paintGL() {
		glClear(GL_COLOR_BUFFER_BIT);

		glPushMatrix();
		glTranslatef(x, y, 0.0f);
		glRotatef(angle, 0, 0, 1.0f);
		glBegin(GL_QUADS);
		glVertex2i(-50, -50);
		glVertex2i(50, -50);
		glVertex2i(50, 50);
		glVertex2i(-50, 50);
		glEnd();
		glPopMatrix();

		angle += 1;

		if (Mouse.isCreated()) {
			Mouse.poll();
			while (Mouse.next()) {
				x += Mouse.getEventDX();
				y += Mouse.getEventDY();
			}
		}
		if (Keyboard.isCreated()) {
			Keyboard.poll();
		}
		while (Keyboard.isCreated() && Keyboard.next()) {
			if (Keyboard.getEventKey() != Keyboard.KEY_NONE) {
				String key_name = Keyboard.getKeyName(Keyboard.getEventKey());
				if (Keyboard.getEventKeyState()) {
					switch (Keyboard.getEventKey()) {
						case Keyboard.KEY_G:
							Mouse.setGrabbed(!Mouse.isGrabbed());
							break;
						default:
							break;
					}
					System.out.println("Pressed: " + key_name);
				} else
					System.out.println("Released: " + key_name);
			}
			if (Keyboard.getEventCharacter() != Keyboard.CHAR_NONE)
				System.out.println("Typed: " + Keyboard.getEventCharacter());
		}
		if (Keyboard.isCreated()) {
			if (Keyboard.isKeyDown(Keyboard.KEY_UP))
				y += 5;
			else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
				y -= 5;
			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
				x -= 5;
			else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
				x += 5;
		}
		try {
			swapBuffers();
			if (isVisible()) {
				Thread.yield(); // Helps input responsiveness on linux
				repaint();
			}
		} catch (Exception e) {/*OK*/
		}
	}

	public void start() {
	}

	public void stop() {
	}
}
