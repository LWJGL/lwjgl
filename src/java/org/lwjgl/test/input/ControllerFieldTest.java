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
package org.lwjgl.test.input;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.opengl.Window;


/**
 * $Id$
 * <br>
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class ControllerFieldTest {
  
  private Panel panel;
  private Frame frame;
  private Label[] labels;
  
	/**
	 * 
	 */
	private void executeTest() {
    initialize();
    
    if(frame != null) {
    	run();
    }
    destroy();
	}
  
  /**
	 * 
	 */
	private void destroy() {
    Window.destroy();
	}

	/**
	 * 
	 */
	private void run() {
    frame.show();
    
    String buttons;
    while(frame.isVisible()) {
      buttons = "";
      Window.update();
      
      labels[0].setText("" + Controller.getX());
      labels[1].setText("" + Controller.getRx());
      labels[2].setText("" + Controller.getY());
      labels[3].setText("" + Controller.getRy());
      labels[4].setText("" + Controller.getZ());
      labels[5].setText("" + Controller.getRz());
      labels[6].setText("" + Controller.getPov());
      labels[7].setText("" + Controller.getSlider());
      
      for(int i=0; i<Controller.getButtonCount(); i++) {
        buttons += (Controller.isButtonDown(i)) ? "" + i + ", ": "";
      }      
      labels[17].setText(buttons);
    }
	}

	/**
	 * 
	 */
	private void initialize() {
    
    try {
     Window.create("debug", 10, 10, 10, 10, 16, 0, 0, 0);
    } catch (LWJGLException lwjgle) {
    	lwjgle.printStackTrace();
      return;
    }
    
    if(!Controller.isCreated()) {
      System.out.println("Unable to aquire controller - exiting");
      return;
    }
    
    // create our panel, with lots of field names | value
    panel = new Panel();
    panel.setLayout(new GridLayout(0,2));
    
    String[] fields = new String[] {
        "x", "rx", "y", "ry",
        "z", "rz", "pov", "slider",
        "buttonCount", "hasXAxis", "hasRXAxis",
        "hasYAxis", "hasRYAxis", "hasZAxis", 
				"hasRZAxis", "hasPOV", "hasSlider", "buttons"        
    };
    labels = new Label[fields.length];
    
    for(int i=0 ;i<fields.length; i++) {
      panel.add(new Label(fields[i]));
      panel.add(new Label(""));
      labels[i] = (Label) panel.getComponent((i*2)+1);
    }
    
    frame = new Frame("ControllerFieldTest");
    frame.setBounds(100, 100, 640, 480);
    frame.add(panel);
    
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        frame.hide();
        frame.dispose();
      }
    });
    
    frame.pack();
    
    // set initial fields
    labels[8].setText("" + Controller.getButtonCount());
    labels[9].setText("" + Controller.hasXAxis());
    labels[10].setText("" + Controller.hasRXAxis());
    labels[11].setText("" + Controller.hasYAxis());
    labels[12].setText("" + Controller.hasRYAxis());
    labels[13].setText("" + Controller.hasZAxis());
    labels[14].setText("" + Controller.hasRZAxis());
    labels[15].setText("" + Controller.hasPOV());
    labels[16].setText("" + Controller.hasSlider());
	}

	public static void main(String[] args) {
    ControllerFieldTest test = new ControllerFieldTest();
    test.executeTest();
  }  
}
