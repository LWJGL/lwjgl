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
 * * Neither the name of 'Lightweight Java Game Library' nor the names of
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

import org.lwjgl.Display;
import org.lwjgl.DisplayMode;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Window;
import org.lwjgl.opengl.glu.GLU;
import org.lwjgl.vector.Vector2f;

/**
 * $Id$
 * <br>
 * Mouse test
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class MouseCreationTest {
		/** position of quad to draw */
	private Vector2f position = new Vector2f(320.0f, 240.0f);
  
  /** Display mode selected */
  private DisplayMode displayMode;

	/** Creates a new instance of MouseTest */
	public MouseCreationTest() {
	}

	private void initialize(boolean fullscreen) {
    //  find first display mode that allows us 640*480*16
    DisplayMode[] modes = Display.getAvailableDisplayModes();
    for (int i = 0; i < modes.length; i++) {
      if (modes[i].width == 640
        && modes[i].height == 480
        && modes[i].bpp >= 16) {
        displayMode = modes[i];
        break;
      }
    }    
    
    try {
      if(fullscreen) {
        Display.setDisplayMode(displayMode);
        Window.create("MouseCreationTest", 16, 0, 0, 0);
      } else {
        Window.create("MouseCreationTest", 50, 50, 640, 480, 16, 0, 0, 0);
      }

    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }

    initializeOpenGL();    
	}
  
	private void initializeOpenGL() {
    GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    GLU.gluOrtho2D(0.0f, 640f, 0f, 480f);
	}

	public void executeTest() {
		initialize(false);

		System.out.println("Test ready:\n");
    
    // windowed mode
		System.out.println("=========== WINDOWED MODE ==============");
    for(int i=0; i<2; i++) {
      System.out.println("Test " + (i+1) + ":");
      createMouse();
      wiggleMouse();
      destroyMouse();
      System.out.println("");
    }
    
    // recreate display in fullscreen mode
    System.out.print("Destroying display...");
    
    System.out.println("success");
    
    System.out.print("Entering fullscreen mode...");
    try {
      Window.destroy();
      initialize(true);
      Display.setDisplayMode(displayMode);
    } catch (Exception e) {
			e.printStackTrace();
		}
    System.out.println("success");
    
    
    // fullscreen mode
    System.out.println("=========== FULLSCREEN MODE ==============");
    for(int i=0; i<2; i++) {
      System.out.println("Test " + (i+3) + ":");
      createMouse();
      wiggleMouse();
      destroyMouse();
      System.out.println("");
    }
    
    System.out.println("Test completed successfully!");
    System.out.print("Shutting down...");
    Display.resetDisplayMode();
    Mouse.destroy();
    Window.destroy();
    System.out.println("shutdown complete");
	}

	private void createMouse() {
		System.out.print("Creating mouse...");
		try {
			Mouse.create();
		} catch (Exception e) {
			System.out.println("failed");
			System.exit(-1);
		}
		System.out.println("success");
	}

	private void wiggleMouse() {
		System.out.print("Please move the mouse around");

		long statustime = Sys.getTime();
		long endtime = Sys.getTime() + Sys.getTimerResolution() * 5;

		while (Sys.getTime() < endtime) {
      Window.update();

			Mouse.poll();

			position.x += Mouse.getDX();
			position.y += Mouse.getDY();
      
      if(position.x<0) {
        position.x = 0;
      } else if (position.x>640-60) {
        position.x = 640-60;
      }
      
      if(position.y < 0) {
        position.y = 0;
      } else if (position.y>480-30) {
        position.y = 480-30;
      }      

			render();

			Window.paint();

			if (Sys.getTime() - statustime > Sys.getTimerResolution()) {
				System.out.print(".");
				statustime = Sys.getTime();
			}
		}
		System.out.println("thank you");
	}
  
  private void destroyMouse() {
    System.out.print("Destroying mouse...");
    Mouse.destroy();
    System.out.print("success");
  }

	private void render() {
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

    GL11.glBegin(GL11.GL_POLYGON);
    {
      float color = 1.0f;
      int buttonDown = 0;
      
      for(int i=0;i<Mouse.getButtonCount(); i++) {
        if(Mouse.isButtonDown(i)) {
          color = (1.0f / Mouse.getButtonCount()) * (i+1); 
          break; 
        }
      }
      GL11.glColor3f(color, color, color);
      
      GL11.glVertex2f(position.x + 0.0f, position.y + 0.0f);
      GL11.glVertex2f(position.x + 0.0f, position.y + 30.0f);
      GL11.glVertex2f(position.x + 40.0f, position.y + 30.0f);
      GL11.glVertex2f(position.x + 60.0f, position.y + 15.f);
      GL11.glVertex2f(position.x + 40.0f, position.y + 0.0f);
    }
    GL11.glEnd();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		MouseCreationTest mt = new MouseCreationTest();
		mt.executeTest();
	}
}
