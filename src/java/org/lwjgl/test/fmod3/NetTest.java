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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.fmod3.FMOD;
import org.lwjgl.fmod3.FMODException;
import org.lwjgl.fmod3.FSound;
import org.lwjgl.fmod3.FSoundStream;
import org.lwjgl.fmod3.callbacks.FSoundDSPCallback;
import org.lwjgl.fmod3.callbacks.FSoundMetaDataCallback;

/**
 * $Id$ <br>
 * 
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class NetTest {
  
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage:\n NetTest <url>");
      
      // default to monkeyradio.org
      args = new String[1];
      args[0] = "http://207.200.96.227:8038/";
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
    
    
    FSound.FSOUND_Stream_SetBufferSize(100);
    FSound.FSOUND_Stream_Net_SetBufferProperties(64000, 60, 80);

		System.out.println("Loading " + args[0]);
		FSoundStream stream = FSound.FSOUND_Stream_Open(args[0], FSound.FSOUND_NORMAL | FSound.FSOUND_NONBLOCKING, 0, 0);

    
		if (stream != null) {
      
      int channel = -1;
      boolean run = true;
      IntBuffer status = BufferUtils.createIntBuffer(4);
      while(run) {
        if(channel < 0) {
         channel = FSound.FSOUND_Stream_PlayEx(FSound.FSOUND_FREE, stream, null, true);
         FSound.FSOUND_SetPaused(channel, false);
         
         if (channel != -1) {
             FSound.FSOUND_Stream_Net_SetMetadataCallback(stream, new FSoundMetaDataCallback() {
							public void FSOUND_METADATACALLBACK(ByteBuffer name, ByteBuffer value) {
                byte[] charName = new byte[name.capacity()];
                name.get(charName);
                
                byte[] charValue = new byte[value.capacity()];
                value.get(charValue);
                
                System.out.println(new String(charName) + " = " + new String(charValue));
							}
             });
           }
        }
        
        int openstate = FSound.FSOUND_Stream_GetOpenState(stream);
        if ((openstate == -1) || (openstate == -3)) {
            System.out.println("\nERROR: failed to open stream!\n");
            System.out.println("SERVER: " + FSound.FSOUND_Stream_Net_GetLastServerStatus() + "\n");
            break;
        }
        
        FSound.FSOUND_Stream_Net_GetStatus(stream, status);
        
        System.out.println("Status: " + status.get(0) + ", buffer: " + status.get(1) + ", bitrate: " + status.get(2) + ", flags: " + status.get(3));
        
        pause(100);
      }
      
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
   * @param i
   */
  private static void pause(long i) {
    try {
      Thread.sleep(i);
    } catch (InterruptedException inte) {
    }
  }  
}