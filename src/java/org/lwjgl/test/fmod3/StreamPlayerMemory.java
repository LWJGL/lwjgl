/* 
 * Copyright (c) 2002-2004 LWJGL Project
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
package org.lwjgl.test.fmod3;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.lwjgl.fmod3.FMOD;
import org.lwjgl.fmod3.FMODException;
import org.lwjgl.fmod3.FSound;
import org.lwjgl.fmod3.FSoundStream;

/**
 * $Id$ <br>
 * 
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class StreamPlayerMemory {
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage:\n StreamPlayerMemory <file>");
			
			// default to phero.mp3
			args = new String[] { "phero2.ogg"};
			System.out.println("Using default: " + args[0]);
		}
		
		try {
			FMOD.create();
		} catch (FMODException fmode) {
			fmode.printStackTrace();
			System.exit(0);
		}
		
		System.out.println("Initializing FMOD");
		if (!FSound.FSOUND_Init(44100, 32, 0)) {
			System.out.println("Failed to initialize FMOD");
			System.out.println("Error: " + FMOD.FMOD_ErrorString(FSound.FSOUND_GetError()));
			System.exit(0);
		}
		
		ByteBuffer data = getData(args[0]);
		FSoundStream stream = FSound.FSOUND_Stream_Open(data, FSound.FSOUND_LOADMEMORY, 0, data.capacity());
		
		if (stream != null) {
			FSound.FSOUND_Stream_Play(0, stream);
			
			// busy wait until done
			int length = FSound.FSOUND_Stream_GetLengthMs(stream);
			String time = ((length / 1000) / 60) + "m " + ((length / 1000) % 60) + "s";
			System.out.println("Waiting " + time + ", for song to finish");
			
			try {
				Thread.sleep(length);
			} catch (InterruptedException inte) {
			}
			
			FSound.FSOUND_Stream_Stop(stream);
			FSound.FSOUND_Stream_Close(stream);
		} else {
			System.out.println("Unable to play: " + args[0]);
			System.out.println("Error: " + FMOD.FMOD_ErrorString(FSound.FSOUND_GetError()));
		}
		
		FSound.FSOUND_Close();
		FMOD.destroy();
		System.exit(0);
	}
	
	/**
	 * Reads the file into a ByteBuffer
	 * 
	 * @param filename Name of file to load
	 * @return ByteBuffer containing file data
	 */
	static protected ByteBuffer getData(String filename) {
		ByteBuffer buffer = null;
		
		System.out.println("Attempting to load: " + filename);
		
		try {
			BufferedInputStream bis = new BufferedInputStream(StreamPlayerMemory.class.getClassLoader()
																												.getResourceAsStream(filename));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			int bufferLength = 4096;
			byte[] readBuffer = new byte[bufferLength];
			int read = -1;
			
			while ((read = bis.read(readBuffer, 0, bufferLength)) != -1) {
				baos.write(readBuffer, 0, read);
			}
			
			//done reading, close
			bis.close();

      // place it in a buffer
			buffer = ByteBuffer.allocateDirect(baos.size());
			buffer.order(ByteOrder.nativeOrder());
			buffer.put(baos.toByteArray());
			buffer.flip();
			System.out.println("loaded " + buffer.remaining() + " bytes");
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return buffer;
	}
}