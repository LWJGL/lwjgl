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

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

/**
 *
 * This is a basic play test
 * Yes, over zealous use of getError ;)
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class PlayTestMemory extends BasicTest {
    
  private boolean usingVorbis;
  
    /**
     * Creates an instance of PlayTestMemory
     */
    public PlayTestMemory() {
        super();
    }

    /**
     * Runs the actual test, using supplied arguments
     */
    protected void execute(String[] args) {
        if(args.length < 1) {
          System.out.println("no argument supplied, assuming Footsteps.wav");
          args = new String[] {"Footsteps.wav"};
        }        
        
        if(args[0].endsWith(".ogg")) {
          System.out.print("Attempting to load Ogg Vorbis file, checking for extension...");
          if(AL10.alIsExtensionPresent("AL_EXT_vorbis")) {
            System.out.println("found");
            usingVorbis = true;
          } else {
            System.out.println("not supported");
            alExit();
            System.exit(-1);
          }
        }
        
        int lastError;
        
        //create 1 buffer and 1 source
        IntBuffer buffers = BufferUtils.createIntBuffer(1);
        IntBuffer sources = BufferUtils.createIntBuffer(1);        
        
        // al generate buffers and sources
        buffers.position(0).limit(1);
        AL10.alGenBuffers(buffers);
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }

        sources.position(0).limit(1);
        AL10.alGenSources(sources);
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }
        
        //load wave data
        ByteBuffer filebuffer = getData(args[0]);
        if(filebuffer == null) {
            System.out.println("Error loading file: " + args[0]);
            System.exit(-1);
        }
        
        System.out.println("loaded " + filebuffer.capacity());
        
        //ALUTLoadWAVData file = alut.loadWAVMemory(Sys.getDirectBufferAddress(filebuffer));
        if(usingVorbis) {
          // pass directly to buffer data
          AL10.alBufferData(buffers.get(0), AL10.AL_FORMAT_VORBIS_EXT, filebuffer, -1);
          filebuffer.clear();
        } else {
          // load wave data from buffer
          WaveData wavefile = WaveData.create(filebuffer.array());

          //copy to buffers
          AL10.alBufferData(buffers.get(0), wavefile.format, wavefile.data, wavefile.samplerate);
        
          //unload file again
          wavefile.dispose();        
        }
        
        // check for errors
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }
  
        
        //set up source input            
        AL10.alSourcei(sources.get(0), AL10.AL_BUFFER, buffers.get(0));
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }        
        
        //lets loop the sound
        AL10.alSourcei(sources.get(0), AL10.AL_LOOPING, AL10.AL_TRUE);
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }        
        
        //play source 0
        AL10.alSourcePlay(sources.get(0));
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }        
        
        //wait 5 secs
        try {
            System.out.println("Waiting 5 seconds for sound to complete");
            Thread.sleep(5000);
        } catch (InterruptedException inte) {
        }
        
        //stop source 0
        AL10.alSourceStop(sources.get(0));
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }        
        
        //delete buffers and sources
        sources.position(0).limit(1);
        AL10.alDeleteSources(sources);
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }
        
        buffers.position(0).limit(1);
        AL10.alDeleteBuffers(buffers);
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }        
        
        //no errorchecking from now on, since our context is gone.
        alExit();
    }
    
    /**
     * Reads the file into a ByteBuffer
     *
     * @param filename Name of file to load
     * @return ByteBuffer containing file data
     */
    protected ByteBuffer getData(String filename) {
        ByteBuffer buffer = null;

        System.out.println("Attempting to load: " + filename);
        
        try {
            BufferedInputStream bis = new BufferedInputStream(WaveData.class.getClassLoader().getResourceAsStream(filename));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            
            int bufferLength = 4096;
            byte[] readBuffer = new byte[bufferLength];
            int read = -1;
            
            while((read = bis.read(readBuffer, 0, bufferLength)) != -1) {
                baos.write(readBuffer, 0, read);
            }
            
            //done reading, close
            bis.close();
            
            // if ogg vorbis data, we need to pass it unmodified to alBufferData
            if(usingVorbis) {
              buffer = ByteBuffer.allocateDirect(baos.size());
            } else {
              buffer = ByteBuffer.allocate(baos.size());
            }
            buffer.order(ByteOrder.nativeOrder());
            buffer.put(baos.toByteArray());
            buffer.rewind();
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return buffer;
    }    
    
    /**
     * main entry point
     *
     * @param args String array containing arguments
     */
    public static void main(String[] args) {
        PlayTestMemory playTestMemory = new PlayTestMemory();
        playTestMemory.execute(args);
        System.exit(0);
    }
}
