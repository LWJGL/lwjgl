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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.IntBuffer;

import org.lwjgl.openal.eax.*;
import org.lwjgl.openal.*;
import org.lwjgl.Sys;

/**
 * $Id$
 *
 * This test initializes EAX and tries to get and set some EAX values
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class EAXTest extends BasicTest {

  /** OpenAL buffers */
  private IntBuffer soundBuffers = createIntBuffer(1);

  /** OpenAL sources */
  private IntBuffer soundSources = createIntBuffer(1);
  
  /** Listener EAX property object */
  private EAXListenerProperties listenerProperties;
  
  /** Buffer EAX property object */
  private EAXBufferProperties bufferProperties;

  /**
   * Creates an instance of EAXTest
   */
  public EAXTest() {
    super();
  }

  /**
   * Runs the actual test, using supplied arguments
   */
  protected void execute(String[] args) {
    
    if(!AL.isCreated()) {
      System.out.println("Unable to continue with EAXTest, since OpenAL could not be initialized.");
      return;
    }
    
    try {
      System.out.print("Testing EAX support...");
      EAX.create();
      System.out.println("supported!");
    } catch (Exception e) {
      System.out.println("not supported!");
    }
    
    // continue with test if EAX is supported
    if (EAX.isCreated() && AL.isCreated() && initialize()) {
      runTest();
      
      // kill sources and buffers
      AL.alSourceStop(soundSources.get(0));
      AL.alDeleteSources(soundSources);
      AL.alDeleteBuffers(soundBuffers);
      
      EAX.destroy();
    }

    //shutdown
    alExit();
  }

  private boolean initialize() {
    // creating buffers
    Sys.log("Creating buffers");
    AL.alGenBuffers(soundBuffers);
    soundBuffers.rewind();

    // creating sources
    Sys.log("Creating sources");
    AL.alGenSources(soundSources);
    soundSources.rewind();

    // load sound files (left, center, right).wav
    Sys.log("Loading Footsteps.wav");
    WaveData footsteps = WaveData.create("Footsteps.wav");
    AL.alBufferData(soundBuffers.get(0), footsteps.format, footsteps.data, footsteps.data.capacity(), footsteps.samplerate);
    AL.alSourcef(soundSources.get(0), AL.AL_PITCH, 1.0f);
    AL.alSourcef(soundSources.get(0), AL.AL_GAIN, 1.0f);
    AL.alSourcei(soundSources.get(0), AL.AL_BUFFER, soundBuffers.get(0));
    AL.alSourcei(soundSources.get(0), AL.AL_LOOPING, AL.AL_TRUE);
    
    // create eax property objects
    listenerProperties = new EAXListenerProperties();
    bufferProperties = new EAXBufferProperties();
    
    // set buffer to work on source 0
    bufferProperties.setCurrentSource(soundSources.get(0));
    
    return AL.alGetError() == AL.AL_NO_ERROR;
  }

  /**
   * Runs the actual EAXTest
   */
  private void runTest() {
    boolean running = true;
    char key;
    
    assert AL.alIsBuffer(soundBuffers.get(0)) : "Failed to validate buffer";

    // handle menu
    do {
      printMenu();
      key = readInput();

      switch (key) {
        // play sound
        case '1' : {
          AL.alSourcePlay(soundSources.get(0));
          break;
        }

        // stop sound
        case '2' : {
          AL.alSourceStop(soundSources.get(0));
          break;
        }

        // add reverb
        case '3' : {
          listenerProperties.setEnvironment(EAX.EAX_ENVIRONMENT_HANGAR);
          break;
        }

          // remove reverb
        case '4' : {
          listenerProperties.setEnvironment(EAX.EAX_ENVIRONMENT_GENERIC);
          break;
        }

          // add occlusion
        case '5' : {
          bufferProperties.setOcclusion(bufferProperties.getOcclusion()-5000);
          break;
        }

          // remove occlusion
        case '6' : {
          bufferProperties.setOcclusion(bufferProperties.getOcclusion()+5000);
          break;
        }

          // add obstruction
        case '7' : {
          bufferProperties.setObstruction(bufferProperties.getObstruction()-5000);
          break;
        }

        // remove obstruction
        case '8' : {
          bufferProperties.setObstruction(bufferProperties.getObstruction()+5000);
          break;
        }

        // commit eax values
        case 'c' : {
          bufferProperties.commit();
          listenerProperties.commit();
          break;
        }

        // toggle autocommit
        case 't' : {
          bufferProperties.setAutoCommit(!bufferProperties.isAutoCommitEnabled());
          listenerProperties.setAutoCommit(!listenerProperties.isAutoCommitEnabled());
          System.out.println("\n[Buffer] Auto Commit is now: " + bufferProperties.isAutoCommitEnabled());
          System.out.println("\n[Listen] Auto Commit is now: " + listenerProperties.isAutoCommitEnabled());
          break;
        }
        
        // show current eax values
        case 's' : {
          System.out.println(bufferProperties);
          System.out.println(listenerProperties);
          break;
        }
        
        // load current eax values
        case 'l' : {
          bufferProperties.loadEAXValues();
          listenerProperties.loadEAXValues();
          break;
        }
        
        // reset to default values
        case 'r' : {
          bufferProperties.reset();
          listenerProperties.reset();
          break;
        }

        // quit demo
        case 'q' : {
          running = false;
          break;
        }
      }
    } while (running);
  }

  private void printMenu() {
    System.out.println("");
    System.out.println("EAXTest menu, please select an option from the following list:");
    System.out.println("1: Play looping sound");
    System.out.println("2: Stop looping sound");
    System.out.println("3: Add reverberation effect");
    System.out.println("4: Remove reverberation effect");
    System.out.println("5: Add occlusion effect");
    System.out.println("6: Remove occlusion effect");
    System.out.println("7: Add obstruction effect");
    System.out.println("8: Remove obstruction effect");

    System.out.println("C: Commit current eax values");
    System.out.println("L: Load current values");    
    System.out.println("T: Toggle autocomit");
    System.out.println("R: Reset to inital values");
    System.out.println("S: Show current values");
    
    System.out.println("Q: Quit demo");
    System.out.print("Input: ");
  }

  private char readInput() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
      return br.readLine().toLowerCase().charAt(0);
    } catch (IOException ioe) {
      return 'q';
    }
  }

  /**
   * main entry point
   *
   * @param args String array containing arguments
   */
  public static void main(String[] args) {
    EAXTest eaxTest = new EAXTest();
    eaxTest.execute(args);
  }
}