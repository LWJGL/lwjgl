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

import org.lwjgl.Display;
import org.lwjgl.Sys;

/**
 * $Id$
 * <br>
 * Test class for Sys
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class SysTest {
  
  /**
   * Creates a new SysTest
   */
  public SysTest() {
  }
  
  /**
   * Runs the tests
   */
  public void executeTest() {
  	testAlert();
  	testDebug();
    testTimer();
    testPriority();
    testUrl();
  }
  
  /**
   * Tests debug mode
   */
  private void testDebug() {
    System.out.println("==== Test Debug ====");
    if (Sys.DEBUG) {
      Sys.log("Debug is enabled, you should now see output from LWJGL during the following tests.");
    } else {
      System.out.println("Debug is not enabled. Please set the org.lwjgl.Sys.debug property to true to enable debugging");
      System.out.println("Example:\n  java -Dorg.lwjgl.Sys.debug=true ...");
      System.out.println("You will not see any debug output in the following tests.");
    }    
    
    // get some display modes, to force some debug info
    Display.getAvailableDisplayModes();
    
    System.out.println("---- Test Debug ----\n");
  }
  
  /**
   * Tests the timer
   */
  private void testTimer() {
    long resolution = Sys.getTimerResolution();
    long time = Sys.getTime();
    
    System.out.println("==== Test Timer ====");
    System.out.println("Resolution of timer (ticks per second): " + resolution);
    System.out.println("Current time: " + time);
    System.out.println("Sleeping for 2 seconds, using Thread.sleep()");
  
    pause(2000);
  
    time = Sys.getTime();
    System.out.println("Current time: " + time);
    System.out.println("Actually slept for: " + (time / (float) resolution) + " seconds");
    System.out.println("---- Test Timer ----\n"); 
  }
  
  /**
   * Tests the priority
   */
  private void testPriority() {
    System.out.println("==== Test Priority ====");
    
    busyWait(Sys.LOW_PRIORITY,      5, "Busy waiting in low priority...");
    busyWait(Sys.NORMAL_PRIORITY,   5, "Busy waiting in normal priority...");
    busyWait(Sys.HIGH_PRIORITY,     5, "Busy waiting in high priority...");
    busyWait(Sys.REALTIME_PRIORITY, 5, "Busy waiting in realtime priority (may lag the computer!)...");
    
    // reset
    System.out.println("Resetting to normal");
    Sys.setProcessPriority(Sys.NORMAL_PRIORITY);

    System.out.println("---- Test Priority ----\n"); 
  }  
  
  /**
   * Tests the alert
   */
  private void testAlert() {
    System.out.println("==== Test Alert ====");
    
    System.out.println("Opening native alert window");
    Sys.alert("SysTest", "Hello World!");
    
    System.out.println("---- Test Alert ----\n"); 
  }    
  
  /**
   * Tests the openUrl
   */
  private void testUrl() {
    System.out.println("==== Test URL ====");
    
    System.out.println("Opening a browser window to http://www.lwjgl.org");
    Sys.openURL("http://www.lwjgl.org");
    
    System.out.println("---- Test URL ----\n"); 
  }  
  
  /**
   * Busy waits for a specified number of seconds
   * 
   * @param priority Priority to busy wait in
   * @param seconds Number of seconds to busy wait
   * @param message Message to print to user
   */
  private void busyWait(int priority, int seconds, String message) {
    long future = Sys.getTime() + (Sys.getTimerResolution() * seconds);
    
    System.out.print(message);
    Sys.setProcessPriority(priority);
    
    // waste some cycles
    while (Sys.getTime() < future) {
    }
    
    System.out.println("done");
  }
  
  /**
   * Pause current thread for a specified time
   * 
   * @param time milliseconds to sleep
   */
  private void pause(long time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException inte) {
    }
  }
  
  /**
   * Tests the Sys class, and serves as basic usage test
   * 
   * @param args ignored
   */
  public static void main(String[] args) {
    new SysTest().executeTest();
  }
}
