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
import java.io.IOException;
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
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage:\n TagFieldTest <file>");
			
			// default to phero.mp3
			args = new String[] { "res\\phero.mp3"};
			System.out.println("Using default: " + args[0]);
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
      //scratch buffer
      IntBuffer scratch = BufferUtils.createIntBuffer(16);
      
			FSound.FSOUND_Stream_Play(0, stream);
      
      // find number of tags
      FSound.FSOUND_Stream_GetNumTagFields(stream, scratch);
      int tagCount = scratch.get(0);
      System.out.println("Found: " + tagCount + " tag fields");
      
      // print each name value pair
      FSoundTagField field;
      for(int i=0; i<tagCount; i++) {
      	field = new FSoundTagField();
        if(FSound.FSOUND_Stream_GetTagField(stream, i, field)) {
        	System.out.println("Field " + i + ": " + field.getName() + ", " + field.getType() + ", " + field.getValueAsString() + ", " + field.getLength());
        }
      }
      
      // trying to find the artist field
      System.out.println("Looking for tag...");
      field = new FSoundTagField(FSound.FSOUND_TAGFIELD_ID3V1, "ARTIST");
      if(FSound.FSOUND_Stream_FindTagField(stream, field)) {
      	System.out.println("Found field: " + field.getName() + ", " + field.getType() + ", " + field.getValueAsString() + ", " + field.getLength());
      }
      
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
}