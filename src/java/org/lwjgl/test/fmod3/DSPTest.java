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
import java.nio.ByteBuffer;

import org.lwjgl.fmod3.FMOD;
import org.lwjgl.fmod3.FMODException;
import org.lwjgl.fmod3.FSound;
import org.lwjgl.fmod3.FSoundDSPUnit;
import org.lwjgl.fmod3.FSoundStream;
import org.lwjgl.fmod3.callbacks.FSoundDSPCallback;
import org.lwjgl.fmod3.callbacks.FSoundStreamCallback;

/**
 * $Id$ <br>
 * 
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class DSPTest {
  
  public static int bytesPerSample; 
  public static int channels;
  
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage:\n DSPTest <file>");
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
      System.out.println("Creating dsp unit");
      FSoundDSPUnit unit = FSound.FSOUND_Stream_CreateDSP(stream, new DSPTest().new TestDspCallback("1"), 1);
      FSound.FSOUND_DSP_SetActive(unit, true);
      FSoundDSPUnit unit2 = FSound.FSOUND_Stream_CreateDSP(stream, new DSPTest().new TestDspCallback("2"), 0);
      FSound.FSOUND_DSP_SetActive(unit2, true);      
      System.out.println("Created: " + unit);
      System.out.println("Created: " + unit2);
      
      switch(FSound.FSOUND_GetMixer()) {
        case FSound.FSOUND_MIXER_AUTODETECT:
        case FSound.FSOUND_MIXER_BLENDMODE:
        case FSound.FSOUND_MIXER_QUALITY_AUTODETECT:
        case FSound.FSOUND_MIXER_QUALITY_FPU:
        case FSound.FSOUND_MIXER_MONO:
        case FSound.FSOUND_MIXER_QUALITY_MONO:
        case FSound.FSOUND_MIXER_MAX:
          bytesPerSample = 8;
          break;
        default:
          bytesPerSample = 4;
          break;
      }
      
      channels = FSound.FSOUND_Stream_GetMode(stream);
      if((channels & FSound.FSOUND_STEREO) == FSound.FSOUND_STEREO) {
       channels = 2; 
      } else {
       channels = 1; 
      }
      
      FSound.FSOUND_Stream_SetEndCallback(stream, new FSoundStreamCallback() {
				public void FSOUND_STREAMCALLBACK(FSoundStream stream, ByteBuffer buff, int len) {
          System.out.println("Done");
        }
      });
      
      FSound.FSOUND_Stream_SetSyncCallback(stream, new FSoundStreamCallback() {
        public void FSOUND_STREAMCALLBACK(FSoundStream stream, ByteBuffer buff, int len) {
          System.out.println("SYNCPOINT");
          try {
          	byte[] data = new byte[buff.capacity()];
          	buff.get(data);
          	System.out.println("Syncpoint @ " + len + ": " + new String(data));
          } catch (Exception e) {
          	e.printStackTrace();
          }
        }
      });
      
      
			FSound.FSOUND_Stream_Play(0, stream);

			System.out.println("Press enter to stop playing");
			try {
				System.in.read();
			} catch (IOException ioe) {
			}

			System.out.println("Done playing. Cleaning up");
			FSound.FSOUND_Stream_Stop(stream);
			FSound.FSOUND_Stream_Close(stream);
      FSound.FSOUND_DSP_Free(unit);
      FSound.FSOUND_DSP_Free(unit2);
		} else {
			System.out.println("Unable to play: " + args[0]);
			System.out.println("Error: " + FMOD.FMOD_ErrorString(FSound.FSOUND_GetError()));
		}

		FSound.FSOUND_Close();
		FMOD.destroy();
	}
  
  public class TestDspCallback implements FSoundDSPCallback {
    
    private String name;

    public TestDspCallback(String name) {
     this.name      = name; 
    }

    /* 
     * @see org.lwjgl.fmod3.callbacks.FSoundDSPCallback#FSOUND_DSPCALLBACK(java.nio.ByteBuffer, java.nio.ByteBuffer, int)
     */
    public ByteBuffer FSOUND_DSPCALLBACK(ByteBuffer originalbuffer, ByteBuffer newbuffer, int length) {
      short leftChannel;
      short rightChannel;
      
    	for(int i=0; i<length; i++) {
        leftChannel = originalbuffer.getShort();
        //rightChannel = originalbuffer.getShort();
    		
        // keep left, mute right channel
        newbuffer.putShort(leftChannel);
        //newbuffer.putShort(rightChannel);
    	}
    	newbuffer.rewind();
    	return newbuffer;
    }
  }  
}