/* 
 * Copyright (c) 2002-2010 LWJGL Project
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
package org.lwjgl.test;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.lwjgl.LWJGLException;
import org.lwjgl.util.WaveData;

/**
 * <br>
 * Test class WaveDataTest
 *
 * @author Brian Matzon <brian@matzon.dk>
 */
public class WaveDataTest {
	
	String filePath = "Footsteps.wav";

	/**
	 * Creates a new DisplayTest
	 */
	public WaveDataTest() {
	}

	/**
	 * Runs the tests
	 */
	public void executeTest() throws LWJGLException {
		executeCreationTest();
		executeBrokenCreationTest();
		executeMidStreamCreationTest();
	}


	private void executeCreationTest() {
		WaveData wd = WaveData.create(filePath);
		if(wd != null) {
			System.out.println("executeCreationTest::success");
		}
	}

	private void executeBrokenCreationTest() {
		WaveData wd = WaveData.create("");
		if(wd == null) {
			System.out.println("executeBrokenCreationTest::success");
		}
	}
	
	private void executeStreamCreationTest() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(filePath));
			WaveData wd = WaveData.create(ais);
			if(wd == null) {
				System.out.println("executeMidStreamCreationTest::success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	private void executeMidStreamCreationTest() {
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(WaveDataTest.class.getClassLoader().getResource(filePath));			
			int totalSize = ais.getFormat().getChannels() * (int) ais.getFrameLength() * ais.getFormat().getSampleSizeInBits() / 8;
			
			// skip 1/4 of the stream
			int skip = totalSize / 4;
			long skipped = ais.skip(skip);
			
			WaveData wd = WaveData.create(ais);
			if(wd == null) {
				System.out.println("executeMidStreamCreationTest::success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	   * Pause current thread for a specified time
	   * 
	   * @param time milliseconds to sleep
	   */
	private void pause(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException inte) {
			/* ignored */
		}
	}

	/**
	 * Tests the Sys class, and serves as basic usage test
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) throws LWJGLException {
		new WaveDataTest().executeTest();
		System.exit(0);
	}
}
