/*
 * Created on 18-03-2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.lwjgl.test;

import org.lwjgl.*;
import org.lwjgl.opengl.Window;

/**
 * @author Brian
 */
public class WindowCreationTest {

	public static void main(String[] args) {
    DisplayMode[] modes = Display.getAvailableDisplayModes();
    System.out.println("Found " + modes.length + " display modes");
    
    try {
      Window.create("WindowCreationTest", 50, 50, 320, 240, 16, 0, 0, 0);
    } catch (Exception e) {
			e.printStackTrace();
		}
    
    System.out.println("Display created");

    while(!Window.isCloseRequested()) {    
      Window.tick();
      try {
        Thread.sleep(100);
      } catch (Exception e) {
        e.printStackTrace();
      } 
    }
    
    Window.destroy();
	}
}
