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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of
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

import org.lwjgl.Sys;
import org.lwjgl.openal.AL;
import java.nio.IntBuffer;

/**
 * $Id$
 *
 * Simple test for stresstesting OpenAL playing random samples ad nausea
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class StressTest extends BasicTest {

  /** Buffer containing sources */
  private IntBuffer sources;

  /** Buffer containing buffers */
  private IntBuffer buffers;

  /**
   * Creates an instance of StressTest
   */
  public StressTest() {
    super();
  }

  /**
   * Runs the actual test, using supplied arguments
   */
  protected void execute(String[] args) {

    alInitialize();

    createSources();

    createBuffers();

    try {
      loadSamples();
      runTest();
    } catch (Exception e) {
      e.printStackTrace();
    }


    alExit();
  }

  private void createSources() {
    sources = createIntBuffer(4);
    al.genSources(4, Sys.getDirectBufferAddress(sources));
    if (al.getError() != AL.NO_ERROR) {
      System.out.println("Unable to create 4 sources");
      alExit();
    }
  }

  private void createBuffers() {
    buffers = createIntBuffer(10);
    al.genBuffers(10, Sys.getDirectBufferAddress(buffers));
    if (al.getError() != AL.NO_ERROR) {
      System.out.println("Unable to create 10 buffers");
      al.deleteSources(4, Sys.getDirectBufferAddress(sources));
      alExit();
    }
  }

  private void loadSamples() throws Exception {
    al.getError();
    WaveData data = WaveData.create("ding.wav");
    for (int i = 1; i <= 10; i++) {
      al.bufferData(
        buffers.get(i - 1),
        data.format,
        Sys.getDirectBufferAddress(data.data),
        data.data.capacity(),
        data.samplerate);

      if (al.getError() != AL.NO_ERROR) {
        System.out.println("Failed to load " + i + ".wav into buffer");
        al.deleteSources(4, Sys.getDirectBufferAddress(sources));
        al.deleteBuffers(10, Sys.getDirectBufferAddress(buffers));
        alExit();
      }
    }
    data.dispose();
  }

  public void runTest() {
    int iterations = 0;
    int randomBuffer;
    int startSlot = 1;
    int nextSlot = startSlot;
    long startTime = System.currentTimeMillis();

    //mark background source as looping
    al.sourcei(sources.get(0), AL.LOOPING, AL.TRUE);

    //play background
    al.sourcei(sources.get(0), AL.BUFFER, buffers.get(0));
    al.sourcePlay(sources.get(0));

    while (System.currentTimeMillis() - startTime < (60000 * 60)) {

      randomBuffer = getRandomBuffer();
      System.out.println("random:" + randomBuffer);

      //stop source at slot
      al.sourceStop(sources.get(nextSlot));
      if (al.getError() != AL.NO_ERROR) {
        System.out.println("Error stopping source.");
      }
      System.out.println("Stopped source: " + nextSlot);

      //link source<->buffer
      al.sourcei(sources.get(nextSlot), AL.BUFFER, buffers.get(randomBuffer));
      if (al.getError() != AL.NO_ERROR) {
        System.out.println("Error linking buffer and source.");
      }
      System.out.println("linked source " + nextSlot + " with buffer " + randomBuffer);

      //start playing
      System.out.println("playing source " + nextSlot);
      al.sourcePlay(sources.get(nextSlot++));
      if (nextSlot == 4) {
        nextSlot = startSlot;
      }

      //pause
      try {
        Thread.sleep(500);
      } catch (InterruptedException inte) {
      }

      //debug info
      if ((++iterations % 10) == 0) {
        System.out.println("========================");
        System.out.println("MaxMemory: " + Runtime.getRuntime().maxMemory() / 1024);
        System.out.println("FreeMemory: " + Runtime.getRuntime().freeMemory() / 1024);
        System.out.println("TotalMemory: " + Runtime.getRuntime().totalMemory() / 1024);
        System.out.println("========================");
      }
    }

    //test done - ask for user input
    try {
      System.out.println("Test completed");
      System.out.println("========================");
      System.out.println("MaxMemory: " + Runtime.getRuntime().maxMemory() / 1024);
      System.out.println("FreeMemory: " + Runtime.getRuntime().freeMemory() / 1024);
      System.out.println("TotalMemory: " + Runtime.getRuntime().totalMemory() / 1024);
      System.out.println("========================");
      System.in.read();
    } catch (Exception e) {
    }

    //stop all sources
    for (int i = 0; i < 4; i++) {
      al.sourceStop(sources.get(i));
    }
    al.deleteSources(4, Sys.getDirectBufferAddress(sources));
    al.deleteBuffers(10, Sys.getDirectBufferAddress(buffers));
  }

  private int getRandomBuffer() {
    return (int) (Math.random() * 10.0);
  }

  /**
   * main entry point
   *
   * @param args String array containing arguments
   */
  public static void main(String[] args) {
    StressTest stressTest = new StressTest();
    stressTest.execute(args);
  }
}