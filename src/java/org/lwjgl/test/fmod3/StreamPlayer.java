/* 
 * Copyright (c) 2004 LWJGL Project
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
import java.io.File;
import java.io.IOException;
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
public class StreamPlayer {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage:\n StreamPlayer <file>");
			return;
		}

		File file = new File(args[0]);
		if (!file.exists()) {
			System.out.println("No such file: " + args[0]);
			return;
		}

		try {
			FMOD.create();
		} catch (FMODException fmode) {
			fmode.printStackTrace();
			return;
		}

		System.out.println("Initializing FMOD");
		if (!FSound.FSOUND_Init(44100, 32, 0)) {
			System.out.println("Failed to initialize FMOD");
			System.out.println("Error: " + FMOD.FMOD_ErrorString(FSound.FSOUND_GetError()));
			return;
		}

		System.out.println("Loading " + args[0]);
		FSoundStream stream = FSound.FSOUND_Stream_Open(args[0], FSound.FSOUND_NORMAL, 0, 0);

		if (stream != null) {
			FSound.FSOUND_Stream_Play(0, stream);

			System.out.println("Press enter to stop playing");
			try {
				System.in.read();
			} catch (IOException ioe) {
			}

			System.out.println("Done playing. Cleaning up");
			FSound.FSOUND_Stream_Stop(stream);
			FSound.FSOUND_Stream_Close(stream);
		} else {
			System.out.println("Unable to play: " + args[0]);
			System.out.println("Error: " + FMOD.FMOD_ErrorString(FSound.FSOUND_GetError()));
		}

		FSound.FSOUND_Close();
		FMOD.destroy();
	}

	/**
	 * Reads the file into a ByteBuffer
	 * 
	 * @param filename
	 *          Name of file to load
	 * @return ByteBuffer containing file data
	 */
	static protected ByteBuffer getData(String filename) {
		ByteBuffer buffer = null;

		System.out.println("Attempting to load: " + filename);

		try {
			BufferedInputStream bis = new BufferedInputStream(StreamPlayer.class.getClassLoader().getResourceAsStream(filename));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			int bufferLength = 4096;
			byte[] readBuffer = new byte[bufferLength];
			int read = -1;

			while ((read = bis.read(readBuffer, 0, bufferLength)) != -1) {
				baos.write(readBuffer, 0, read);
			}

			//done reading, close
			bis.close();

			// if ogg vorbis data, we need to pass it unmodified to alBufferData
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

	/**
	 * Creates a ByteBuffer buffer to hold specified bytes - strictly a utility
	 * method
	 * 
	 * @param size
	 *          how many bytes to contain
	 * @return created ByteBuffer
	 */
	protected static ByteBuffer createByteBuffer(int size) {
		ByteBuffer temp = ByteBuffer.allocateDirect(4 * size);
		temp.order(ByteOrder.nativeOrder());
		return temp;
	}
}