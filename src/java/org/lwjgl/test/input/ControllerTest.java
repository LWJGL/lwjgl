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
package org.lwjgl.test.input;

import java.awt.*;
import java.awt.event.*;
import org.lwjgl.input.Controller;

/**
 * $Id$
 * <br>
 * Controller test, hwich shows a awt window, printing Controller state
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class ControllerTest extends Panel {

	private int counter = 0;

	public Thread animationThread;

	/** Creates a new instance of ControllerTest */
	public ControllerTest() {
		try {
			Controller.create();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		animationThread = new Thread() {
			public void run() {
				while (true) {
					paint(getGraphics());

					try {
						Thread.sleep(250);
					} catch (InterruptedException inte) {
						inte.printStackTrace();
					}
				}
			}
		};
		animationThread.setDaemon(true);
	}

	public void paint(Graphics g) {
		if (g == null) {
			return;
		}

		g.setColor(Color.white);
		g.fillRect(0, 0, 640, 480);

		int y = 100;
		int x = 100;
		Controller.poll();

		g.setColor(Color.blue);
		g.drawString("Buttoncount: " + Controller.buttonCount, x, y);
		y += 20;
		g.drawString("-----------------------------------------------", x, y);
		y += 20;
		g.drawString("x  : " + Controller.x, x, y);
		y += 20;
		g.drawString("y  : " + Controller.y, x, y);
		y += 20;
		if (Controller.hasZAxis) {
			g.drawString("z  : " + Controller.z, x, y);
			y += 20;
		}
		if (Controller.hasPOV) {
			g.drawString("pov: " + Controller.pov, x, y);
			y += 20;
		}

		//paint buttons
		g.drawString("btn: ", x, y);
		x += g.getFontMetrics().stringWidth("btn: ");
		for (int i = 0; i < Controller.buttonCount; i++) {
			if (Controller.isButtonDown(i)) {
				g.drawString(i + ", ", x, y);
				x += 15;
			}
		}
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void disposing() {
		Controller.destroy();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		final ControllerTest p = new ControllerTest();
		final Frame f = new Frame();
		f.setLayout(null);
		f.setSize(640, 480);
		f.setLocation(100, 100);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				f.hide();
				p.disposing();
				f.dispose();
			}
		});

		p.setSize(640, 480);
		p.setLocation(0, 0);
		p.setBackground(Color.RED);

		f.add(p);
		f.show();
		p.animationThread.start();
	}
}