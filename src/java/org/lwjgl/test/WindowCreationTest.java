/*
 * Created on 18-03-2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.lwjgl.test;

import org.lwjgl.*;
import org.lwjgl.opengl.GL;

/**
 * @author Brian
 */
public class WindowCreationTest {

	public static void main(String[] args) {
    GL gl = null;
    
    DisplayMode[] modes = Display.getAvailableDisplayModes();
    System.out.println("Found " + modes.length + " display modes");
    
    try {
      gl = new GL("WindowCreationTest", 50, 50, 320, 240, 32, 0, 0, 0);
    } catch (Exception e) {
			e.printStackTrace();
		}
    
    try {
      gl.create();
    } catch (Exception e) {
			e.printStackTrace();
		}

    System.out.println("Display created");

    while(!gl.isCloseRequested()) {    
      gl.tick();
      try {
        Thread.sleep(100);
      } catch (Exception e) {
        e.printStackTrace();
      } 
    }
    
    gl.destroy();
	}
}