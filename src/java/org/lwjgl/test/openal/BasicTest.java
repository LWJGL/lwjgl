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

import org.lwjgl.openal.AL;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;

/**
 * $Id$
 *
 * This is a basic test, which contains the most used stuff
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public abstract class BasicTest {

  /**
   * Creates an instance of PlayTest
   */
  public BasicTest() {
    try {
      AL.create();
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
  }

  /**
   * Shutdowns OpenAL
   */
  protected void alExit() {
    AL.destroy();
  }

  /**
   * Creates an integer buffer to hold specified ints
   * - strictly a utility method
   *
   * @param size how many int to contain
   * @return created IntBuffer
   */
  protected IntBuffer createIntBuffer(int size) {
    ByteBuffer temp = ByteBuffer.allocateDirect(4 * size);
    temp.order(ByteOrder.nativeOrder());

    return temp.asIntBuffer();
  }

  /**
   * Creates a float buffer to hold specified floats
   * - strictly a utility method
   *
   * @param size how many floats to contain
   * @return created FloatBuffer
   */
  protected FloatBuffer createFloatBuffer(int size) {
    ByteBuffer temp = ByteBuffer.allocateDirect(4 * size);
    temp.order(ByteOrder.nativeOrder());

    return temp.asFloatBuffer();
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
    System.out.println("OpenAL Error: " + AL.alGetString(error));
    alExit();
    System.exit(-1);
  }
}