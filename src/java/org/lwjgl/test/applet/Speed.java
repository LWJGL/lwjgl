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
import org.lwjgl.opengl.AWTGLCanvas;

import static org.lwjgl.opengl.GL11.*;

public class Speed extends AWTGLCanvas implements Test {

	private float	angle;
	private long startTime = System.currentTimeMillis() + 5000;
	private long fps;

	public Speed() throws LWJGLException {
	}

	public void paintGL() {
		glClear(GL_COLOR_BUFFER_BIT);
		glMatrixMode(GL_PROJECTION_MATRIX);
		glLoadIdentity();
		glOrtho(0, 640, 0, 480, 1, -1);
		glMatrixMode(GL_MODELVIEW_MATRIX);

		glPushMatrix();
		glTranslatef(320, 240, 0.0f);
		glRotatef(angle, 0, 0, 1.0f);
		glBegin(GL_QUADS);
		glVertex2i(-50, -50);
		glVertex2i(50, -50);
		glVertex2i(50, 50);
		glVertex2i(-50, 50);
		glEnd();
		glPopMatrix();

		angle += 1;

		try {
			swapBuffers();
			if (isVisible()) {
				Thread.yield(); // Helps input responsiveness on linux
				repaint();
			}
		} catch (Exception e) {/*OK*/
		}
		if (startTime > System.currentTimeMillis()) {
			fps++;
		} else {
			long timeUsed = 5000 + (startTime - System.currentTimeMillis());
			startTime = System.currentTimeMillis() + 5000;
			System.out.println(fps + " frames in " + timeUsed / 1000f + " seconds = "
					+ (fps / (timeUsed / 1000f)));
			fps = 0;
		}
	}

	public void start() {
	}

	public void stop() {
	}
}
