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
package org.lwjgl.test;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;

/**
 * Small class for testing that the Window is creatable
 * If this class can't run, LWJGL wont work!
 * 
 * @author Brian Matzon <brian@matzon.dk>
 */
public class WindowCreationTest {

  /**
   * Main entry point
   * 
   * @param args ignored params to app
   */
	public static void main(String[] args) {
    // get avaialble modes, and print out
    DisplayMode[] modes = Display.getAvailableDisplayModes();
    System.out.println("Found " + modes.length + " display modes");

    int x = 100, y = 100;
    boolean fullscreen = false;
    System.out.println("Moving to 100, 100");
    Display.setLocation(x, y);      
    
    // Create the actual window
    try {
      setDisplayMode();
      Display.create();
    } catch (Exception e) {
			e.printStackTrace();
      System.out.println("Unable to create window!, exiting...");
      System.exit(-1);
		}

    System.out.println("Window created");
    System.out.println("Width: " + Display.getDisplayMode().getWidth() + 
                       ", Height: " + Display.getDisplayMode().getHeight() + 
                       ", Bits per pixel: " + Display.getDisplayMode().getBitsPerPixel() + 
                       ", Frequency: " + Display.getDisplayMode().getFrequency() + 
                       ", Title: "+ Display.getTitle());
    
	Display.setVSyncEnabled(true);
	Display.setTitle("WindowCreationTest");
	float color = 0f;
	int direction = 1;
    // wait for user to close window
    while(!Display.isCloseRequested()) {
		GL11.glClearColor(color, color, color, 1f);
      GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	  color += direction*.05f;
	  if (color > 1f) {
		  color = 1f;
		  direction = -1*direction;
	  } else if (color < 0f) {
		  direction = -1*direction;
		  color = 0f;
	  }
      Display.update();
      try {
        Thread.sleep(100);
      } catch (Exception e) {
        e.printStackTrace();
      } 
      
      if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
       break; 
      }
      
      if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
      	Display.setLocation(x -= 10, y);
      }
      
      if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
        Display.setLocation(x += 10, y);
      }      
      
      if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
        Display.setLocation(x, y -= 10);
      }
      
      if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
        Display.setLocation(x, y += 10);
      }      
      
      if(Keyboard.isKeyDown(Keyboard.KEY_F)) {
        try {
        	Display.setFullscreen(fullscreen = !fullscreen);
        } catch (LWJGLException lwjgle) {
         lwjgle.printStackTrace(); 
        }
      }      
      
    }
    
    // nuke window and get out
    Display.destroy();
	}
  
  /**
   * Sets the display mode for fullscreen mode
   */
  protected static boolean setDisplayMode() {
    // get modes
    DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(640, 480, -1, -1, -1, -1, 60, 60);
    
    try {
      org.lwjgl.util.Display.setDisplayMode(dm, new String[] {
          "width=" + 640,
          "height=" + 480,
          "freq=" + 60,
          "bpp=" + org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel()
         }); 
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return false;
  }  
}
