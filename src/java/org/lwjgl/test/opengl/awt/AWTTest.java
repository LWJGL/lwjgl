/*
 * Copyright (c) 2002-2005 LWJGL Project
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

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.AWTGLCanvas;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.glu.GLU;

/**
 * <p>
 * Tests AWTGLCanvas functionality
 * <p>
 * @version $Revision$
 * @author $Author$
 * $Id$
 */
public class AWTTest extends Frame {

	/** AWT GL canvas */
	private AWTGLCanvas canvas0, canvas1;
	
	private	volatile float angle;

	/**
	 * C'tor
	 */
	public AWTTest() throws LWJGLException {
		setTitle("LWJGL AWT Canvas Test");
		setSize(640, 320);
		setLayout(new GridLayout(1, 2));
		add(canvas0 = new AWTGLCanvas() {
			int current_height;
			int current_width;
			public void paintGL() {
				try {
					if (getWidth() != current_width || getHeight() != current_height) {
						current_width = getWidth();
						current_height = getHeight();
						GL11.glViewport(0, 0, current_width, current_height);
					}
					GL11.glViewport(0, 0, getWidth(), getHeight());
					GL11.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
					GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
					GL11.glMatrixMode(GL11.GL_PROJECTION);
					GL11.glLoadIdentity();
					GLU.gluOrtho2D(0.0f, (float) getWidth(), 0.0f, (float) getHeight());
					GL11.glMatrixMode(GL11.GL_MODELVIEW);
					GL11.glPushMatrix();
					GL11.glColor3f(1f, 1f, 0f);
					GL11.glTranslatef(getWidth() / 2.0f, getHeight() / 2.0f, 0.0f);
					GL11.glRotatef(angle, 0f, 0f, 1.0f);
					GL11.glRectf(-50.0f, -50.0f, 50.0f, 50.0f);
					GL11.glPopMatrix();
					swapBuffers();
					repaint();
				} catch (LWJGLException e) {
					throw new RuntimeException(e);
				}
			}
		});
		canvas0.setBounds(0, 0, 320, 320);
		add(canvas1 = new AWTGLCanvas() {
			int current_height;
			int current_width;
			public void paintGL() {
				try {
					angle += 1.0f;
					if (getWidth() != current_width || getHeight() != current_height) {
						current_width = getWidth();
						current_height = getHeight();
						GL11.glViewport(0, 0, current_width, current_height);
					}
					GL11.glViewport(0, 0, getWidth(), getHeight());
					GL11.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
					GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
					GL11.glMatrixMode(GL11.GL_PROJECTION);
					GL11.glLoadIdentity();
					GLU.gluOrtho2D(0.0f, (float) getWidth(), 0.0f, (float) getHeight());
					GL11.glMatrixMode(GL11.GL_MODELVIEW);
					GL11.glPushMatrix();
					GL11.glTranslatef(getWidth() / 2.0f, getHeight() / 2.0f, 0.0f);
					GL11.glRotatef(2*angle, 0f, 0f, -1.0f);
					GL11.glRectf(-50.0f, -50.0f, 50.0f, 50.0f);
					GL11.glPopMatrix();
					swapBuffers();
					repaint();
				} catch (LWJGLException e) {
					throw new RuntimeException(e);
				}
			}
		});	
		canvas1.setBounds(320, 0, 320, 320);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
		setResizable(true);
		setVisible(true);
	}

	public static void main(String[] args) throws LWJGLException {
		new AWTTest();
	}
}
