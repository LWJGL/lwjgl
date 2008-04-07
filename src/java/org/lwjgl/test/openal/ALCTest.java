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
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC10;

/**
 *
 * This is a test for the ALC part of OpenAL
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class ALCTest extends BasicTest {

	/**
	 * Creates an instance of ALCTest
	 */
	public ALCTest() {
		super();
	}

	/**
	 * Runs the actual test, using supplied arguments
	 */
	protected void execute(String[] args) {
		//error stuff
		int lastError = ALC10.ALC_NO_ERROR;

		//create attribute list for context creation
		IntBuffer buffer = BufferUtils.createIntBuffer(7);

		if ((lastError = ALC10.alcGetError(AL.getDevice())) != ALC10.ALC_NO_ERROR) {
			System.out.println("ALC Error: " + ALC10.alcGetString(AL.getDevice(), lastError));
			System.exit(-1);
		}

		//query        
		System.out.println(
			"DEFAULT_DEVICE_SPECIFIER: "
				+ ALC10.alcGetString(AL.getDevice(), ALC10.ALC_DEFAULT_DEVICE_SPECIFIER));
		System.out.println(
			"DEVICE_SPECIFIER: " + ALC10.alcGetString(AL.getDevice(), ALC10.ALC_DEVICE_SPECIFIER));
		System.out.println("EXTENSIONS: " + ALC10.alcGetString(AL.getDevice(), ALC10.ALC_EXTENSIONS));

		//mo query
		buffer.rewind();
    buffer.position(0);
    	ALC10.alcGetInteger(AL.getDevice(), ALC10.ALC_MAJOR_VERSION, buffer);
    	ALC10.alcGetInteger(AL.getDevice(), ALC10.ALC_MINOR_VERSION, (IntBuffer) buffer.position(1));

		System.out.println("ALC_MAJOR_VERSION: " + buffer.get(0));
		System.out.println("ALC_MINOR_VERSION: " + buffer.get(1));

		//no check for ALC_ALL_ATTRIBUTES / ALC_ATTRIBUTES_SIZE since it 
		//is buggy on win32 - my dev platform

		//get an enumerstion value
		System.out.println(
			"Value of ALC_MAJOR_VERSION: "
				+ ALC10.alcGetEnumValue(AL.getDevice(), "ALC_MAJOR_VERSION"));

		alExit();
	}

	/**
	 * main entry point
	 *
	 * @param args String array containing arguments
	 */
	public static void main(String[] args) {
		ALCTest alcTest = new ALCTest();
		alcTest.execute(args);
		System.exit(0);
	}
}