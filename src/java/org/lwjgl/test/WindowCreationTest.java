/*
 * Created on 18-03-2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.lwjgl.test;

import org.lwjgl.*;

/**
 * @author Brian
 */
public class WindowCreationTest {

	public static void main(String[] args) {
    DisplayMode[] modes = Display.getAvailableDisplayModes();
    System.out.println("Found " + modes.length + " display modes");
    
    //find 640*480*32*100
    DisplayMode mode = modes[0];
    for(int i=0;i<modes.length;i++) {
      if(modes[i].width == 640 &&
          modes[i].height == 480 &&
          modes[i].bpp == 16 &&
          mode.freq <= modes[i].freq) {
            mode = modes[i];
          }
    }
    
    if(mode == null) {
      System.out.println("Unable to find displaymode with following features: 640*480*16*60");
      System.exit(-1);
    }
    
    System.out.println("mode: " + mode);
    
    try {
      Display.create(mode, 0, 0, 0, false, "WindowCreationTest");
    } catch (Exception e) {
			e.printStackTrace();
		}
    
    System.out.println("Display created");
    
    Display.destroy();
	}
}