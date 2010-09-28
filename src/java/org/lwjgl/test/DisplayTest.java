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
package org.lwjgl.test;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * <br>
 * Test class for Display & DisplayMode
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class DisplayTest {

  /**
   * Creates a new DisplayTest
   */
  public DisplayTest() {
  }

  /**
   * Runs the tests
   */
  public void executeTest() throws LWJGLException {
    currentTest();
    queryModesTest();
    setDisplayModeTest();
    setDisplayConfigurationTest();
  }

  /**
   * Prints some info about the current mode
   */
  private void currentTest() {
    System.out.println("==== Test Current ====");

    System.out.println("Info about current:");
    System.out.println("Graphics card: " + Display.getAdapter() + ", version: " + Display.getVersion());
    System.out.println("Resolution: " +
        Display.getDisplayMode().getWidth()      + "x" +
        Display.getDisplayMode().getHeight()     + "x" +
        Display.getDisplayMode().getBitsPerPixel()      + "@" +
        Display.getDisplayMode().getFrequency()  + "Hz");
    System.out.println("---- Test Current ----");
  }

  /**
   * Tests querying for modes
   */
  private void queryModesTest() throws LWJGLException {
    DisplayMode[] modes = null;

    System.out.println("==== Test query ====");
    System.out.println("Retrieving available displaymodes");
    modes = Display.getAvailableDisplayModes();

    // no modes check
    if (modes == null) {
      System.out.println("FATAL: unable to find any modes!");
      System.exit(-1);
    }

    // write some info
    System.out.println("Found " + modes.length + " modes");
    System.out.println("The first 5 are:");
    for(int i=0;i<modes.length; i++) {
      System.out.println(modes[i]);
      if (i == 5) {
        break;
      }
    }
    System.out.println("---- Test query ----");
  }


  /**
   * Tests setting display modes
   */
  private void setDisplayModeTest() throws LWJGLException {
    DisplayMode mode = null;
    DisplayMode[] modes = null;

    System.out.println("==== Test setDisplayMode ====");
    System.out.println("Retrieving available displaymodes");
    modes = Display.getAvailableDisplayModes();

    // no modes check
    if (modes == null) {
      System.out.println("FATAL: unable to find any modes!");
      System.exit(-1);
    }

    // find a mode
    System.out.print("Looking for 640x480...");
	  for ( DisplayMode mode1 : modes ) {
		  if ( mode1.getWidth() == 640 &&
		       mode1.getHeight() == 480 ) {
			  mode = mode1;
			  System.out.println("found!");
			  break;
		  }
	  }

    // no mode check
    if (mode == null) {
      System.out.println("error\nFATAL: Unable to find basic mode.");
      System.exit(-1);
    }

    // change to mode, and wait a bit
    System.out.print("Changing to mode...");
    try {
      Display.setDisplayMode(mode);
      Display.setFullscreen(true);
      Display.create();
    } catch (Exception e) {
      System.out.println("error\nFATAL: Error setting mode");
      System.exit(-1);
    }
    System.out.println("done");

    System.out.println("Resolution: " +
        Display.getDisplayMode().getWidth()      + "x" +
        Display.getDisplayMode().getHeight()     + "x" +
        Display.getDisplayMode().getBitsPerPixel()      + "@" +
        Display.getDisplayMode().getFrequency()  + "Hz");

    pause(5000);

    // reset
    System.out.print("Resetting mode...");
    try {
        Display.setFullscreen(false);
    } catch (LWJGLException e) {
        e.printStackTrace();
    }
    System.out.println("done");

    System.out.println("---- Test setDisplayMode ----");
  }

  /**
   * Tests the DisplayConfiguration
   */
  private void setDisplayConfigurationTest() {
    System.out.println("==== Test setDisplayConfigurationTest ====");

    System.out.println("Testing normal setting");
    changeConfig(1.0f, 0f, 1f);

    System.out.println("Testing gamma settings");
    changeConfig(5.0f, 0f, 1f);
    changeConfig(0.5f, 0f, 1f);

    System.out.println("Testing brightness settings");
    changeConfig(1.0f, -1.0f, 1f);
    changeConfig(1.0f, -0.5f, 1f);
    changeConfig(1.0f, 0.5f, 1f);
    changeConfig(1.0f, 1.0f, 1f);

    System.out.println("Testing contrast settings");
    changeConfig(1.0f, 0f, 0f);
    changeConfig(1.0f, 0f, 0.5f);
    changeConfig(1.0f, 0f, 10000.0f);

    System.out.print("resetting...");
    try {
        Display.setFullscreen(false);
    } catch (LWJGLException e) {
        e.printStackTrace();
    }
    System.out.println("done");

    System.out.println("---- Test setDisplayConfigurationTest ----");
  }

  /**
   * Changes the Displat configuration
   *
   * @param gamma gamma value to change to
   * @param brightness brightness value to change to
   * @param contrast contrast value to change to
   */
  private void changeConfig(float gamma, float brightness, float contrast) {
    try {
      Display.setDisplayConfiguration(gamma, brightness, contrast);
      System.out.println("Configuration changed, gamma = " + gamma + " brightness = " + brightness + " contrast = " + contrast);
    } catch (Exception e) {
      System.out.println("Failed on: gamma = " + gamma + " brightness = " + brightness + " contrast = " + contrast);
    }
    pause(3000);
  }

  /**
   * Pause current thread for a specified time
   *
   * @param time milliseconds to sleep
   */
  private void pause(long time) {
	  int SLEEP_DELAY = 100;
	  for (int i = 0; i < time; i += SLEEP_DELAY) {
		  try {
			  Display.processMessages();
			  Thread.sleep(SLEEP_DELAY);
		  } catch (InterruptedException inte) {
		  }
	  }
  }

  /**
   * Tests the Sys class, and serves as basic usage test
   *
   * @param args ignored
   */
  public static void main(String[] args) throws LWJGLException {
    new DisplayTest().executeTest();
    System.exit(0);
  }
}
