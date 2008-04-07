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

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL10;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.WaveData;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * This test simulates a listener positioned in the center, and 
 * a source moving around the listener using the keyboard
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class MovingSoundTest extends BasicTest {
  
  public static float MOVEMENT = 50.00f;

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
			System.out.println("no argument supplied, assuming Footsteps.wav");
      args = new String[] {"Footsteps.wav"};
		}

    try {
        setDisplayMode();
        Display.create();
    } catch (Exception e) {
			e.printStackTrace();
		} 


		int lastError;
    Vector3f sourcePosition = new Vector3f();
    Vector3f listenerPosition = new Vector3f();
    
    //initialize keyboard
    try {
      Keyboard.create();
		} catch (Exception e) {
      e.printStackTrace();
      exit(-1);
		}

		//create 1 buffer and 1 source
		IntBuffer buffers = BufferUtils.createIntBuffer(1);
		IntBuffer sources = BufferUtils.createIntBuffer(1);

		// al generate buffers and sources
    buffers.position(0).limit(1);
		AL10.alGenBuffers(buffers);
		if ((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
			exit(lastError);
		}

    sources.position(0).limit(1);
    AL10.alGenSources(sources);
		if ((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
			exit(lastError);
		}

		//load wave data
		WaveData wavefile = WaveData.create(args[0]);

		//copy to buffers
    AL10.alBufferData(
			buffers.get(0),
			wavefile.format,
			wavefile.data,
			wavefile.samplerate);
		if ((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
			exit(lastError);
		}

		//unload file again
		wavefile.dispose();

		//set up source input
    AL10.alSourcei(sources.get(0), AL10.AL_BUFFER, buffers.get(0));
		if ((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
			exit(lastError);
		}

    AL10.alSourcef(sources.get(0), AL10.AL_REFERENCE_DISTANCE, 1024.0f);
    AL10.alSourcef(sources.get(0), AL10.AL_ROLLOFF_FACTOR, 0.5f);

		//lets loop the sound
    AL10.alSourcei(sources.get(0), AL10.AL_LOOPING, AL10.AL_TRUE);
		if ((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
			exit(lastError);
		}

		//play source 0
    AL10.alSourcePlay(sources.get(0));
		if ((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
			exit(lastError);
		}
    
    System.out.println("Move source with arrow keys\nMove listener with right shift and arrowkeys\nExit with ESC");

		while(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
      Display.update();
      
      Keyboard.poll();
      if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
        if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
          listenerPosition.x -= MOVEMENT;
          AL10.alListener3f(AL10.AL_POSITION, listenerPosition.x, listenerPosition.y, listenerPosition.z);
          System.out.println("listenerx: " + listenerPosition.x);
        } else {
          sourcePosition.x -= MOVEMENT;
          AL10.alSource3f(sources.get(0), AL10.AL_POSITION, sourcePosition.x, sourcePosition.y, sourcePosition.z);
          System.out.println("sourcex: " + sourcePosition.x);
        }
      }      
      if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
        if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
          listenerPosition.x += MOVEMENT;
          AL10.alListener3f(AL10.AL_POSITION, listenerPosition.x, listenerPosition.y, listenerPosition.z);
          System.out.println("listenerx: " + listenerPosition.x);
        } else {        
          sourcePosition.x += MOVEMENT;
          AL10.alSource3f(sources.get(0), AL10.AL_POSITION, sourcePosition.x, sourcePosition.y, sourcePosition.z);
          System.out.println("sourcex: " + sourcePosition.x);
        }
      }
      
      if(Display.isCloseRequested()) {
        break;
      }
     
      try {
        Thread.sleep(100);
			} catch (InterruptedException inte) {
			} 
    }

		//stop source 0
    AL10.alSourceStop(sources.get(0));
		if ((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
			exit(lastError);
		}

		//delete buffers and sources
    sources.position(0).limit(1);
    AL10.alDeleteSources(sources);
		if ((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
			exit(lastError);
		}

    buffers.position(0).limit(1);
    AL10.alDeleteBuffers(buffers);
		if ((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
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
		System.exit(0);
	}
}
