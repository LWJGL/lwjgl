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
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALC11;
import org.lwjgl.openal.ALCdevice;
import org.lwjgl.openal.OpenALException;

/**
 *
 * This is a test for the ALC part of OpenAL
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision: 2286 $
 * $Id: ALCTest.java 2286 2006-03-23 19:32:21Z matzon $
 */
public class ALCCaptureTest extends BasicTest {

	/**
	 * Creates an instance of ALCTest
	 */
	public ALCCaptureTest() {
		super();
	}

	/**
	 * Runs the actual test, using supplied arguments
	 */
	protected void execute(String[] args) {
		int lastError = ALC10.ALC_NO_ERROR;
		IntBuffer sampleCount = BufferUtils.createIntBuffer(1);
		
		int state = AL10.AL_PLAYING;
		int FMT = AL10.AL_FORMAT_MONO16;
		int FMTSIZE  = 16/8;
		int FREQ = 44100;
		int TIME = 5;
		int SAMPS = (FREQ * TIME);
		ByteBuffer buf = BufferUtils.createByteBuffer(SAMPS * FMTSIZE);		

		// check that capture is available
		if(!ALC10.alcIsExtensionPresent(AL.getDevice(), "ALC_EXT_CAPTURE")) {
			throw new OpenALException("ALC_EXT_CAPTURE extension not available");
		}
		
		// get list of devices
		String[] captureDevices = ALC10.alcGetString(null, ALC11.ALC_CAPTURE_DEVICE_SPECIFIER).split("\0");
		System.out.println("Available Capture Devices: ");
		for(int i=0; i<captureDevices.length; i++) {
			System.out.println(i + ": " + captureDevices[i]);
		}
		
		String defaultRecorder = ALC10.alcGetString(AL.getDevice(), ALC11.ALC_CAPTURE_DEFAULT_DEVICE_SPECIFIER);
		System.out.println("Default capture device: " + defaultRecorder);
		
		ALCdevice device = ALC11.alcCaptureOpenDevice(null, FREQ, FMT, SAMPS);

		if(device != null) {
			// record some sound
			// =====================================
			System.out.print("Recording using " + ALC10.alcGetString(device, ALC11.ALC_CAPTURE_DEVICE_SPECIFIER) + "...");
			ALC11.alcCaptureStart(device);
			while (sampleCount.get(0) < SAMPS) {
				pause(1000);
				ALC10.alcGetInteger(device, ALC11.ALC_CAPTURE_SAMPLES, sampleCount);
			}
			System.out.println("done");
			ALC11.alcCaptureStop(device);

			// capure the samples
			ALC11.alcCaptureSamples(device, buf, SAMPS);
			ALC11.alcCaptureCloseDevice(device);
			// -------------------------------------
			
			// play back recording
			// ===================
	        IntBuffer buffers = BufferUtils.createIntBuffer(1);
	        IntBuffer sources = BufferUtils.createIntBuffer(1);        
	        
	        buffers.position(0).limit(1);
	        AL10.alGenBuffers(buffers);

	        sources.position(0).limit(1);
	        AL10.alGenSources(sources);

	        System.out.print("Playing...");

		    AL10.alBufferData(buffers.get(0), FMT, buf, FREQ);
		    AL10.alSourcei(sources.get(0), AL10.AL_BUFFER, buffers.get(0));
		    AL10.alSourcei(sources.get(0), AL10.AL_LOOPING, AL10.AL_FALSE);
		    AL10.alSourcePlay(sources.get(0));

		    while (state == AL10.AL_PLAYING)
		    {
		        pause(100);
		        state = AL10.alGetSourcei(sources.get(0), AL10.AL_SOURCE_STATE);
		    }

		    System.out.println("done");

		    AL10.alDeleteSources(sources);
		    AL10.alDeleteBuffers(buffers);
		}		

		alExit();
	}

	/**
	 * main entry point
	 *
	 * @param args String array containing arguments
	 */
	public static void main(String[] args) {
		ALCCaptureTest alcTest = new ALCCaptureTest();
		alcTest.execute(args);
		System.exit(0);
	}
}