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
package org.lwjgl.test.opengl.awt;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.GridLayout;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

/**
 * <p>
 * Tests Display.setParent()
 * <p>
 * @version $Revision$
 * @author $Author$
 * $Id$
 */
public class DisplayParentTest extends Frame {
	public DisplayParentTest() throws LWJGLException {
		setTitle("LWJGL Display Parent Test");
		setSize(640, 320);
		setLayout(new GridLayout(1, 2));
		final Canvas display_parent = new Canvas();
		display_parent.setFocusable(true);
		display_parent.setIgnoreRepaint(true);
		add(display_parent);
/*		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Display.destroy();
				dispose();
			}
		});*/
		setResizable(true);
		setVisible(true);
		Display.setParent(display_parent);
		Display.setVSyncEnabled(true);
		Display.create();
		float angle = 0f;

		while (isVisible()) {
			angle += 1.0f;
			int width;
			int height;
			if (!Display.isFullscreen()) {
				width = display_parent.getWidth();
				height = display_parent.getHeight();
			} else {
				width = Display.getDisplayMode().getWidth();
				height = Display.getDisplayMode().getHeight();
			}
			GL11.glViewport(0, 0, width, height);
			GL11.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GLU.gluOrtho2D(0.0f, (float) width, 0.0f, (float) height);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glPushMatrix();
			GL11.glTranslatef(width / 2.0f, height / 2.0f, 0.0f);
			GL11.glRotatef(2*angle, 0f, 0f, -1.0f);
			GL11.glRectf(-50.0f, -50.0f, 50.0f, 50.0f);
			GL11.glPopMatrix();
			Display.update();
			while(Keyboard.next()) {
				// closing on ESCAPE
				if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState()) {
					Display.destroy();
					dispose();
					break;
				}

				if(Keyboard.getEventKey() == Keyboard.KEY_SPACE && Keyboard.getEventKeyState()) {
					Mouse.setGrabbed(!Mouse.isGrabbed());
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_F && Keyboard.getEventKeyState()) {
					Display.setFullscreen(!Display.isFullscreen());
				}
			}
/*			while (Mouse.next()) {
System.out.println("				Mouse.getEventX() = " + 				Mouse.getEventX() + " | Mouse.getEventY() = " + Mouse.getEventY());
			}*/
		}
		System.exit(0);
	}

	public static void main(String[] args) throws LWJGLException {
		new DisplayParentTest();
	}
}
