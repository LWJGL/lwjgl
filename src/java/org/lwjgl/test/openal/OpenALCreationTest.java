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
 * * Neither the name of 'Lightweight Java Game Library' nor the names of
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

/**
 * $Id$
 * <br>
 * Performs a creation test, by creating and destroying OpenAL twice.
 * We cannot inherit from BasicTest since it follows another structure.
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
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
		System.out.println("OpenAL Error: " + AL.alGetString(error));
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
    AL.alGenBuffers(1, buffers);
		if ((lastError = AL.alGetError()) != AL.AL_NO_ERROR) {
			exit(lastError);
		}

    AL.alGenSources(1, sources);
		if ((lastError = AL.alGetError()) != AL.AL_NO_ERROR) {
			exit(lastError);
		}

		//load wave data
		WaveData wavefile = WaveData.create("Footsteps.wav");

		//copy to buffers
    AL.alBufferData(
			buffers.get(0),
			wavefile.format,
			wavefile.data,
			wavefile.data.capacity(),
			wavefile.samplerate);
		if ((lastError = AL.alGetError()) != AL.AL_NO_ERROR) {
			exit(lastError);
		}

		//unload file again
		wavefile.dispose();

		//set up source input
    AL.alSourcei(sources.get(0), AL.AL_BUFFER, buffers.get(0));
		if ((lastError = AL.alGetError()) != AL.AL_NO_ERROR) {
			exit(lastError);
		}

		//lets loop the sound
    AL.alSourcei(sources.get(0), AL.AL_LOOPING, AL.AL_TRUE);
		if ((lastError = AL.alGetError()) != AL.AL_NO_ERROR) {
			exit(lastError);
		}

		//play source 0
    AL.alSourcePlay(sources.get(0));
		if ((lastError = AL.alGetError()) != AL.AL_NO_ERROR) {
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
    AL.alSourceStop(sources.get(0));
		if ((lastError = AL.alGetError()) != AL.AL_NO_ERROR) {
			exit(lastError);
		}

		//delete buffers and sources
    AL.alDeleteSources(1, sources);
		if ((lastError = AL.alGetError()) != AL.AL_NO_ERROR) {
			exit(lastError);
		}

    AL.alDeleteBuffers(1, buffers);
		if ((lastError = AL.alGetError()) != AL.AL_NO_ERROR) {
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
	}
}