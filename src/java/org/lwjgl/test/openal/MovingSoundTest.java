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
import org.lwjgl.openal.eax.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL;

import java.nio.IntBuffer;

/**
 * $Id$
 *
 * This test simulates a listener positioned in the center, and 
 * a source moving around the listener using the keyboard
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class MovingSoundTest extends BasicTest {
  
  public static float MOVEMENT = 50.00f;
  private GL gl = new GL("Moving Sound Test", 100, 100, 320, 240, 32, 0 ,0 ,0);

	/**
	 * Creates an instance of MovingSoundTest
	 */
	public MovingSoundTest() {
		super();
	}

	/**
	 * Runs the actual test, using supplied arguments
	 */
	protected void execute(String[] args) {
		if (args.length < 1) {
			System.out.println("please specify filename to play");
			return;
		}

    try {
      gl.create();
    } catch (Exception e) {
			e.printStackTrace();
		} 


		int lastError;
    float sourcex = 0.0f, sourcey = 0.0f, sourcez = 0.0f;
    float listenerx = 0.0f, listenery = 0.0f, listenerz = 0.0f;
    boolean eaxApplied = false;
    IntBuffer Env = null;
    EAXBufferProperties eaxBufferProp = null;
    
    //initialize keyboard
    try {
      Keyboard.create();
		} catch (Exception e) {
      e.printStackTrace();
      exit(-1);
		}

		//create 1 buffer and 1 source
		IntBuffer buffers = createIntBuffer(1);
		IntBuffer sources = createIntBuffer(1);

		// al generate buffers and sources
		al.genBuffers(1, Sys.getDirectBufferAddress(buffers));
		if ((lastError = al.getError()) != AL.NO_ERROR) {
			exit(lastError);
		}

		al.genSources(1, Sys.getDirectBufferAddress(sources));
		if ((lastError = al.getError()) != AL.NO_ERROR) {
			exit(lastError);
		}

		//load wave data
		WaveData wavefile = WaveData.create(args[0]);

		//copy to buffers
		al.bufferData(
			buffers.get(0),
			wavefile.format,
			Sys.getDirectBufferAddress(wavefile.data),
			wavefile.data.capacity(),
			wavefile.samplerate);
		if ((lastError = al.getError()) != AL.NO_ERROR) {
			exit(lastError);
		}

		//unload file again
		wavefile.dispose();

		//set up source input
		al.sourcei(sources.get(0), AL.BUFFER, buffers.get(0));
		if ((lastError = al.getError()) != AL.NO_ERROR) {
			exit(lastError);
		}

    al.sourcef(sources.get(0), AL.REFERENCE_DISTANCE, 1024.0f);
    al.sourcef(sources.get(0), AL.ROLLOFF_FACTOR, 0.5f);

		//lets loop the sound
		al.sourcei(sources.get(0), AL.LOOPING, AL.TRUE);
		if ((lastError = al.getError()) != AL.NO_ERROR) {
			exit(lastError);
		}

		//play source 0
		al.sourcePlay(sources.get(0));
		if ((lastError = al.getError()) != AL.NO_ERROR) {
			exit(lastError);
		}
    
    //setup EAX if possible
    EAX eax = null;
    if (al.isExtensionPresent("EAX")) {
      eax = new EAX();
      try {
          eax.create();
          Env = createIntBuffer(1);
          eaxBufferProp = new EAXBufferProperties();          
      } catch (Exception e) {
        eax = null;
      }
    }    
    
    System.out.println("Move source with arrow keys\nMove listener with right shift and arrowkeys\nEnable EAX effect by pressing e (if available)\nExit with ESC");

		while(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
      gl.tick();
      
      Keyboard.poll();
      if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
        if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
          listenerx -= MOVEMENT;
          al.listener3f(AL.POSITION, listenerx, listenery, listenerz);
          System.out.println("listenerx: " + listenerx);
        } else {
          sourcex -= MOVEMENT;
          al.source3f(sources.get(0), AL.POSITION, sourcex, sourcey, sourcez);
          System.out.println("sourcex: " + sourcex);
        }
      }      
      if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
        if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
          listenerx += MOVEMENT;
          al.listener3f(AL.POSITION, listenerx, listenery, listenerz);
          System.out.println("listenerx: " + listenerx);
        } else {        
          sourcex += MOVEMENT;
          al.source3f(sources.get(0), AL.POSITION, sourcex, sourcey, sourcez);
          System.out.println("sourcex: " + sourcex);
        }
      }
      
      if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
        if(eax != null) {
          if(eaxApplied) {
            Env.put(0, EAX.ENVIRONMENT_GENERIC);
            eax.eaxSet( EAX.LISTENER_GUID,
                        EAXListenerProperties.ENVIRONMENT,
                        0,
                        Sys.getDirectBufferAddress(Env),
                        4);
          } else {
            Env.put(0, EAX.ENVIRONMENT_HANGAR);
            eax.eaxSet( EAX.LISTENER_GUID,
                        EAXListenerProperties.ENVIRONMENT,
                        0, 
                        Sys.getDirectBufferAddress(Env), 
                        4);
          }
          eaxApplied = !eaxApplied;
        }
      }
      
      if(gl.isCloseRequested()) {
        break;
      }
     
      try {
        Thread.sleep(100);
			} catch (InterruptedException inte) {
			} 
    }

		//stop source 0
		al.sourceStop(sources.get(0));
		if ((lastError = al.getError()) != AL.NO_ERROR) {
			exit(lastError);
		}

		//delete buffers and sources
		al.deleteSources(1, Sys.getDirectBufferAddress(sources));
		if ((lastError = al.getError()) != AL.NO_ERROR) {
			exit(lastError);
		}

		al.deleteBuffers(1, Sys.getDirectBufferAddress(buffers));
		if ((lastError = al.getError()) != AL.NO_ERROR) {
			exit(lastError);
		}

		//shutdown
		alExit();
	}

	/**
	 * main entry point
	 *
	 * @param args String array containing arguments
	 */
	public static void main(String[] args) {
		MovingSoundTest movingSoundTest = new MovingSoundTest();
		movingSoundTest.execute(args);
	}
}
