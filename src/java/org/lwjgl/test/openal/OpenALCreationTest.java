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

import org.lwjgl.Sys;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCcontext;
import org.lwjgl.openal.ALCdevice;

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

	/** OpenAL instance */
	protected AL al;

	/** OpenAL Context instance */
	protected ALC alc;

	/** OpenAL context */
	protected ALCcontext context;

	/** OpenAL device */
	protected ALCdevice device;

	/**
	 * Creates an instance of OpenALCreationTest
	 */
	public OpenALCreationTest() {
		al = new AL();
		alc = new ALC();
	}

	public void alInitialize() {
		try {
			al.create();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		try {
			alc.create();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		//  get default device
		device = alc.openDevice(null);
		if (device == null) {
			throw new RuntimeException("Error creating device");
		}

		//create context (no attributes specified)
		context = alc.createContext(device, 0);
		if (context == null) {
			throw new RuntimeException("Error creating context");
		}

		//make context current
		alc.makeContextCurrent(context);
		if (alc.getError(device) != ALC.NO_ERROR) {
			throw new RuntimeException("An error occurred while making context current");
		}
	}

	public void alExit() {
		//Get active context
		context = alc.getCurrentContext();

		//Get device for active context
		device = alc.getContextsDevice(context);

		//Disable context
		alc.makeContextCurrent(null);

		//Release context(s)
		alc.destroyContext(context);

		//Close device
		alc.closeDevice(device);

    // destroy al/c
		alc.destroy();
		al.destroy();
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
		System.out.println("OpenAL Error: " + al.getString(error));
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
		al.genBuffers(1, Sys.getDirectBufferAddress(buffers));
		if ((lastError = al.getError()) != AL.NO_ERROR) {
			exit(lastError);
		}

		al.genSources(1, Sys.getDirectBufferAddress(sources));
		if ((lastError = al.getError()) != AL.NO_ERROR) {
			exit(lastError);
		}

		//load wave data
		WaveData wavefile = WaveData.create("Footsteps.wav");

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

		//wait 5 secs
		try {
			System.out.print("Playing 'Footsteps.wav' for 2 seconds...");
			Thread.sleep(2000);
		} catch (InterruptedException inte) {
		}
		System.out.println("done");

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