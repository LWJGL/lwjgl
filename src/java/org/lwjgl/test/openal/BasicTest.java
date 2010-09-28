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
package org.lwjgl.test.openal;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC10;
import org.lwjgl.opengl.DisplayMode;

/**
 *
 * This is a basic test, which contains the most used stuff
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public abstract class BasicTest {

  /**
   * Creates an instance of PlayTest
   */
  protected BasicTest() {
    try {
    	AL.create();

    	System.out.println("Default device: " + ALC10.alcGetString(null, ALC10.ALC_DEFAULT_DEVICE_SPECIFIER));

    	if(ALC10.alcIsExtensionPresent(null, "ALC_ENUMERATION_EXT")) {
    		String[] devices = ALC10.alcGetString(null, ALC10.ALC_DEVICE_SPECIFIER).split("\0");
			System.out.println("Available devices: ");
    		for(int i=0; i<devices.length; i++) {
    			System.out.println(i +": " + devices[i]);
    		}
    	}
    } catch (Exception e) {
    	System.out.println("Unable to create OpenAL.\nPlease make sure that OpenAL is available on this system. Exception: " + e);
    	return;
    }
  }

  /**
   * Shutdowns OpenAL
   */
  protected void alExit() {
    if(AL.isCreated()) {
      AL.destroy();
    }
  }

  /**
   * Creates a float buffer to hold specified float array
   * - strictly a utility method
   *
   * @param array to hold
   * @return created FloatBuffer
   */
  protected FloatBuffer createFloatBuffer(float[] data) {
    FloatBuffer temp = BufferUtils.createFloatBuffer(data.length).put(data);
    temp.flip();
    return temp;
  }

  /**
   * Pauses the invoking thread for specified milliseconds
   *
   * @param time Milliseconds to sleep
   */
  protected void pause(long time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException inte) {
    }
  }

  /**
   * Exits the test NOW, printing errorcode to stdout
   *
   * @param error Error code causing exit
   */
  protected void exit(int error) {
    System.out.println("OpenAL Error: " + AL10.alGetString(error));
    alExit();
    System.exit(-1);
  }

  /**
   * Sets the display mode for fullscreen mode
   */
  protected boolean setDisplayMode() {
    try {
		// get modes
		DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(640, 480, -1, -1, -1, -1, 60, 60);

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
