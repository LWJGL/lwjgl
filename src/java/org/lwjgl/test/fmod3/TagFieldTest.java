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

import java.io.File;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.fmod3.FMOD;
import org.lwjgl.fmod3.FMODException;
import org.lwjgl.fmod3.FSound;
import org.lwjgl.fmod3.FSoundStream;
import org.lwjgl.fmod3.FSoundTagField;

/**
 * $Id$ <br>
 * 
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class TagFieldTest {

	/** Scratch buffer */
	static IntBuffer	scratch	= BufferUtils.createIntBuffer(16);

  /**
   * Main entry point
   * 
   * @param args arguments for app
   */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage:\n TagFieldTest <file|dir>");

			// default to .
			args = new String[] { "."};
			System.out.println("Using default: " + args[0]);
		}

		// initialize fmod
		try {
			System.out.println("Initializing FMOD");
			FMOD.create();

			if (!FSound.FSOUND_Init(44100, 32, 0)) {
				System.out.println("Failed to initialize FMOD");
				System.out.println("Error: " + FMOD.FMOD_ErrorString(FSound.FSOUND_GetError()));
				FMOD.destroy();
				return;
			}
		} catch (FMODException fmode) {
			fmode.printStackTrace();
			return;
		}

		// scan the supplied arg 
    System.out.println("Starting recursive scan from: " + args[0]);
		scanPath(new File(args[0]));

		// shutdown
		FSound.FSOUND_Close();
		FMOD.destroy();
	}

  /**
   * Scans a path for files
   * 
   * @param path Path to scan
   */
	private static void scanPath(File path) {
		// if we got supplied a file - scan it
		if (path.isFile()) {
			scanFile(path);
			return;
		}

		// if we got a dir scan it for files and subfolders
		if (path.isDirectory()) {
			File[] files = path.listFiles();
			if (files != null) {
				// scan each entry, recursively calling scanPath
				for (int i = 0; i < files.length; i++) {
					scanPath(files[i]);
				}
			}
			return;
		}

		System.out.println("Ignoring: " + path.getAbsolutePath());
	}

	/**
	 * Scans a file for tag fields
	 * @param file file to scan for tags
	 */
	private static void scanFile(File file) {
		// if we got an mp3 or an ogg file, open it
		if (file.getName().indexOf(".mp3") != -1 || file.getName().indexOf(".ogg") != -1) {
			System.out.println("Opening " + file.getAbsolutePath());
			FSoundStream stream = FSound.FSOUND_Stream_Open(file.getAbsolutePath(), FSound.FSOUND_NORMAL, 0, 0);
      if(stream == null) {
      	System.out.println("Unable to open file: " + FMOD.FMOD_ErrorString(FSound.FSOUND_GetError()));
        return;
      }
			FSound.FSOUND_Stream_GetNumTagFields(stream, scratch);
			int tagCount = scratch.get(0);

			// print each name value pair
			if (tagCount > 0) {
				System.out.println("The following list of tags were found:");
				FSoundTagField field;
				for (int i = 0; i < tagCount; i++) {
					field = new FSoundTagField();
					if (FSound.FSOUND_Stream_GetTagField(stream, i, field)) {
						System.out.println("  " + field.getName() + " = '" + field.getValueAsString() + "'");
					}
				}
			} else {
        System.out.println("Unable to locates any tags");
      }
      System.out.println();
      FSound.FSOUND_Stream_Close(stream);
		}
	}
}