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

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

/**
 * Oops. Forgot to document this one.
 *
 * @author Kevin Glass
 */
public class TestControllers extends JPanel {
	public static int total;

	private JTextField[] values;
	private JTextField[] names;
	private Controller controller;
	private int buttonCount;
	private int itemCount;

	public TestControllers(int index) {
		controller = Controllers.getController(index);
		setLayout(null);

		buttonCount = controller.getButtonCount();
		itemCount = controller.getButtonCount() + controller.getAxisCount() + 2;
		values = new JTextField[itemCount];
		names = new JTextField[itemCount];

		for (int i=0;i<controller.getButtonCount();i++) {
			names[i] = new JTextField();
			names[i].setEditable(false);
			names[i].setBounds(0,i*30,100,30);
			names[i].setText(controller.getButtonName(i));
			add(names[i]);
			values[i] = new JTextField();
			values[i].setEditable(false);
			values[i].setBounds(100,i*30,100,30);
			add(values[i]);
		}
		for (int i=buttonCount;i<buttonCount+controller.getAxisCount();i++) {
			names[i] = new JTextField();
			names[i].setEditable(false);
			names[i].setBounds(0,i*30,100,30);
			names[i].setText(controller.getAxisName(i-buttonCount));
			add(names[i]);
			values[i] = new JTextField();
			values[i].setEditable(false);
			values[i].setBounds(100,i*30,100,30);
			add(values[i]);
		}

		int i = itemCount - 2;
		names[i] = new JTextField();
		names[i].setEditable(false);
		names[i].setBounds(0,i*30,100,30);
		names[i].setText("POV X");
		add(names[i]);
		values[i] = new JTextField();
		values[i].setEditable(false);
		values[i].setBounds(100,i*30,100,30);
		add(values[i]);

		i = itemCount - 1;
		names[i] = new JTextField();
		names[i].setEditable(false);
		names[i].setBounds(0,i*30,100,30);
		names[i].setText("POV Y");
		add(names[i]);
		values[i] = new JTextField();
		values[i].setEditable(false);
		values[i].setBounds(100,i*30,100,30);
		add(values[i]);

		total++;

		setPreferredSize(new Dimension(200,30*itemCount));
		JFrame frame = new JFrame(controller.getName());
		frame.setContentPane(new JScrollPane(this));
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				total--;
				if (total == 0) {
					System.exit(0);
				}
			}
		});
		frame.setSize(230,400);
		frame.setLocation(index*30,index*30);
		frame.setVisible(true);
	}

	public void updateDetails() {
		for (int i=0;i<controller.getButtonCount();i++) {
			values[i].setText(""+controller.isButtonPressed(i));
		}
		for (int i=buttonCount;i<buttonCount+controller.getAxisCount();i++) {
			values[i].setText(""+controller.getAxisValue(i-buttonCount));
		}

		values[itemCount-2].setText(""+controller.getPovX());
		values[itemCount-1].setText(""+controller.getPovY());
	}

	public static void main(String[] argv) {
		try {
			Controllers.create();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		int count = Controllers.getControllerCount();
		System.out.println(count+" Controllers Found");
		for (int i=0;i<count;i++) {
			Controller controller = Controllers.getController(i);
			System.out.println(controller.getName());
		}

		if (count == 0) {
			System.exit(0);
		}

		TestControllers[] controllerWindows = new TestControllers[count];
		for (int i=0;i<count;i++) {
			controllerWindows[i] = new TestControllers(i);
		}

		boolean running = true;
		while (running) {
			try { Thread.sleep(100); } catch (Exception e) {};

			Controllers.poll();

			while (Controllers.next()) {
				System.out.println("Event Fired: ");
				System.out.println("\t"+Controllers.getEventNanoseconds());
				System.out.println("\t"+Controllers.getEventSource()+":"+Controllers.getEventControlIndex()+":"+Controllers.isEventButton());
				System.out.println("\t"+Controllers.isEventXAxis()+":"+Controllers.isEventYAxis());
			}

			for (int i=0;i<count;i++) {
				controllerWindows[i].updateDetails();
			}
		}
	}
}
