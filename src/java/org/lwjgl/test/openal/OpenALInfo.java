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
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALC11;
import org.lwjgl.openal.ALCdevice;

/**
 *
 * idea from openal-info
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision: 2983 $
 * $Id$
 */
public class OpenALInfo {

	/**
	 * Creates an instance of OpenALInfo
	 */
	public OpenALInfo() {
	}

	/**
	 * Runs the actual test, using supplied arguments
	 */
	protected void execute(String[] args) {
		try {
			AL.create(null, -1, 60, false);
			checkForErrors();
		} catch (LWJGLException le) {
			die("Init", le.getMessage());
		}
		
		printALCInfo();
		printALInfo();
		
		checkForErrors();
	}
	
	private void printALCInfo() {
		IntBuffer version = BufferUtils.createIntBuffer(2);
		ALCdevice device;
		
		if(ALC10.alcIsExtensionPresent(null, "ALC_ENUMERATION_EXT")) {
	        if(ALC10.alcIsExtensionPresent(null, "ALC_ENUMERATE_ALL_EXT")) {
	            printDevices(ALC11.ALC_ALL_DEVICES_SPECIFIER, "playback");
	        } else {
	            printDevices(ALC10.ALC_DEVICE_SPECIFIER, "playback");
	        }
	        printDevices(ALC11.ALC_CAPTURE_DEVICE_SPECIFIER, "capture");
	    } else {
	      System.out.println("No device enumeration available");
		}
		
		device = ALC10.alcGetContextsDevice(ALC10.alcGetCurrentContext());
		checkForErrors();
		
	    System.out.println("Default device: " + ALC10.alcGetString(device, ALC10.ALC_DEFAULT_DEVICE_SPECIFIER));

	    System.out.println("Default capture device: " + ALC10.alcGetString(device, ALC11.ALC_CAPTURE_DEFAULT_DEVICE_SPECIFIER));

    	ALC10.alcGetInteger(AL.getDevice(), ALC10.ALC_MAJOR_VERSION, version);
    	ALC10.alcGetInteger(AL.getDevice(), ALC10.ALC_MINOR_VERSION, (IntBuffer) version.position(1));
	    checkForErrors();

	    System.out.println("ALC version: " + (int)version.get(0) + "." + (int)version.get(1));

	    System.out.println("ALC extensions:");
	    String[] extensions = ALC10.alcGetString(device, ALC10.ALC_EXTENSIONS).split(" ");
		for(int i=0; i<extensions.length; i++) {
			if(extensions[i].trim().length() == 0) {
				continue;
			}
			System.out.println("    " + extensions[i]);
		}
	    checkForErrors();
	}
	
	private void printALInfo() {
	    System.out.println("OpenAL vendor string: " + AL10.alGetString(AL10.AL_VENDOR));
	    System.out.println("OpenAL renderer string: " + AL10.alGetString(AL10.AL_RENDERER));
	    System.out.println("OpenAL version string: " + AL10.alGetString(AL10.AL_VERSION));
	    System.out.println("AL extensions:");
	    String[] extensions = AL10.alGetString(AL10.AL_EXTENSIONS).split(" ");
		for(int i=0; i<extensions.length; i++) {
			if(extensions[i].trim().length() == 0) {
				continue;
			}
			System.out.println("    " + extensions[i]);
		}
	    checkForErrors();		
	}
	
	private void printDevices(int which, String kind) {
		String[] devices = ALC10.alcGetString(null, which).split("\0");
		checkForErrors();
		
		System.out.println("Available " + kind + " devices: ");
		for(int i=0; i<devices.length; i++) {
			System.out.println("    " + devices[i]);
		}
	}

	private void die(String kind, String description) {
		System.out.println(kind + " error " + description + " occured");
	}
	
	private void checkForErrors() {
		{
			ALCdevice device = ALC10.alcGetContextsDevice(ALC10.alcGetCurrentContext());
			int error = ALC10.alcGetError(device);
			if(error != ALC10.ALC_NO_ERROR) {
				die("ALC", ALC10.alcGetString(device, error));
			}
		}
		{
			int error = AL10.alGetError();
			if(error != AL10.AL_NO_ERROR) {
				die("AL", AL10.alGetString(error));
			}
		}		
	}
	
	/**
	 * main entry point
	 *
	 * @param args String array containing arguments
	 */
	public static void main(String[] args) {
		OpenALInfo openalInfo = new OpenALInfo();
		openalInfo.execute(args);
		System.exit(0);
	}
}