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

import org.lwjgl.fmod3.FMOD;
import org.lwjgl.fmod3.FMODException;
import org.lwjgl.fmod3.FMusic;
import org.lwjgl.fmod3.FMusicModule;
import org.lwjgl.fmod3.FSound;

/**
 * <br>
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class MusicPlayer {
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage:\n MusicPlayer <file>");
			
			// default to Missing_you.mod
			args = new String[] { "res\\Missing_you.mod"};
			System.out.println("Using default: " + args[0]);
		}
		
		File file = new File(args[0]);
		if (!file.exists()) {
			System.out.println("No such file: " + args[0]);
			System.exit(0);
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
			return;
		}
		
		System.out.println("Loading " + args[0]);
		
		// using name (path)
		FMusicModule module = FMusic.FMUSIC_LoadSong(args[0]);
		
		if (module != null) {
			System.out.println("Loaded. Playing module of type: " + FMusic.FMUSIC_GetType(module));
			FMusic.FMUSIC_PlaySong(module);
			
			System.out.println("Press enter to stop playing");
			try {
				System.in.read();
			} catch (IOException ioe) {
			}
			FMusic.FMUSIC_StopSong(module);
			
			System.out.println("Done playing. Cleaning up");
			FMusic.FMUSIC_FreeSong(module);
		} else {
			System.out.println("Unable to play: " + args[0]);
			System.out.println("Error: " + FMOD.FMOD_ErrorString(FSound.FSOUND_GetError()));
		}
		
		FSound.FSOUND_Close();
		FMOD.destroy();
		System.exit(0);
	}
}