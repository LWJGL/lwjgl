/* 
 * Copyright (c) 2004-2005 Covalent Software Ltd
 * All rights reserved.
 */
package org.lwjgl.test.opengl.awt;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.AWTGLCanvas;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.glu.GLU;

/**
 * $Id$
 * <p>
 * Tests AWTGLCanvas functionality
 * <p>
 * @version $Revision$
 * @author $Author$
 */
public class AWTTest extends Frame {

	/** AWT GL canvas */
	private AWTGLCanvas canvas0, canvas1;
	
	private	float angle;

	/**
	 * C'tor
	 */
	public AWTTest() throws LWJGLException {
		setTitle("LWJGL AWT Canvas Test");
		setSize(640, 320);
		setLayout(null);
		add(canvas0 = new AWTGLCanvas() {
			public void paintGL() {
				try {
					makeCurrent();
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
				} catch (LWJGLException e) {
					throw new RuntimeException(e);
				}
			}
		});
		canvas0.setBounds(0, 0, 320, 320);
		add(canvas1 = new AWTGLCanvas() {
			public void paintGL() {
				try {
					makeCurrent();
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
				} catch (LWJGLException e) {
					throw new RuntimeException(e);
				}
			}
		});	
		canvas1.setBounds(320, 0, 320, 320);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setResizable(true);
		setVisible(true);
		
		new Thread() {
			{
				setDaemon(true);
			}
			public void run() {
				for (;;) {
					angle += 1.0f;
					canvas0.repaint();
					canvas1.repaint();
					try {
						sleep(20);
					} catch (InterruptedException e) {
						break;
					}
				}
			}
		}.start();
	}

	public static void main(String[] args) throws LWJGLException {
		new AWTTest();
	}
}
