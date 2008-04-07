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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

/**
 * <br>
 * Performs a creation test, by creating and destroying OpenAL twice.
 * We cannot inherit from BasicTest since it follows another structure.
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class OpenALCreationTest {

	/**
	 * Creates an instance of OpenALCreationTest
	 */
	public OpenALCreationTest() {
	}

	public void alInitialize() {
		try {
			AL.create();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	public void alExit() {
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
	 * Runs the actual test, using supplied arguments
	 */
	protected void execute(String[] args) {
		int lastError;

		//initialize AL, using ALC
		System.out.print("initialize...");
		alInitialize();
		System.out.println("success");

		//do some audio
		executeAudioTest();

		//shutdown
		System.out.print("shutdown...");
		alExit();
		System.out.println("success");

		//initialize AL, using ALC
		System.out.print("initialize...");
		alInitialize();
		System.out.println("success");

		//do some audio
		executeAudioTest();

		//shutdown
		System.out.print("shutdown...");
		alExit();
		System.out.println("success");
	}

	/**
	 * Executes the audio test, which just plays some sound
	 */
	private void executeAudioTest() {
		int lastError;

		//create 1 buffer and 1 source
		IntBuffer buffers = createIntBuffer(1);
		IntBuffer sources = createIntBuffer(1);

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
		WaveData wavefile = WaveData.create("Footsteps.wav");

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

		//wait 5 secs
		try {
			System.out.print("Playing 'Footsteps.wav' for 2 seconds...");
			Thread.sleep(2000);
		} catch (InterruptedException inte) {
		}
		System.out.println("done");

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
	}

	/**
	 * main entry point
	 *
	 * @param args String array containing arguments
	 */
	public static void main(String[] args) {
		OpenALCreationTest oalCreationTest = new OpenALCreationTest();
		oalCreationTest.execute(args);
		System.exit(0);
	}
}
