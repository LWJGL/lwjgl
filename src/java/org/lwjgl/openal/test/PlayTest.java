/*
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of
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
package org.lwjgl.openal.test;

import org.lwjgl.Sys;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALUTLoadWAVData;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * $Id$
 *
 * This is a basic play test
 * Yes, over zealous use of getError ;)
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class PlayTest extends BasicTest {
    
    /**
     * Creates an instance of PlayTest
     */
    public PlayTest() {
        super();
    }

    /**
     * Runs the actual test, using supplied arguments
     */
    protected void execute(String[] args) {
        if(args.length < 1) {
            System.out.println("please specify filename to play");
            return;
        }        
        
        int lastError;
        
        //initialize AL, using ALC
        alInitialize();
        
        //create 1 buffer and 1 source
        ByteBuffer buffers = ByteBuffer.allocateDirect(4);
        buffers.order(ByteOrder.nativeOrder());
        
        ByteBuffer sources = ByteBuffer.allocateDirect(4);
        sources.order(ByteOrder.nativeOrder());
        
        // al generate buffers and sources
        al.genBuffers(1, Sys.getDirectBufferAddress(buffers));
        if((lastError = al.getError()) != AL.NO_ERROR) {
            exit(lastError);
        }

        al.genSources(1, Sys.getDirectBufferAddress(sources));
        if((lastError = al.getError()) != AL.NO_ERROR) {
            exit(lastError);
        }
        
        //load wave data
        ALUTLoadWAVData file = alut.loadWAVFile(args[0]);
        if((lastError = al.getError()) != AL.NO_ERROR) {
            exit(lastError);
        }
        
        
        //copy to buffers
        al.bufferData(buffers.getInt(0), file.format, file.data, file.size, file.freq);
        if((lastError = al.getError()) != AL.NO_ERROR) {
            exit(lastError);
        }        
        
        //unload file again
        alut.unloadWAV(file.format, file.data, file.size, file.freq);        
        if((lastError = al.getError()) != AL.NO_ERROR) {
            exit(lastError);
        }        
        
        //set up source input
        al.sourcei(sources.getInt(0), AL.BUFFER, buffers.getInt(0));
        if((lastError = al.getError()) != AL.NO_ERROR) {
            exit(lastError);
        }        
        
        //lets loop the sound
        al.sourcei(sources.getInt(0), AL.LOOPING, AL.TRUE);
        if((lastError = al.getError()) != AL.NO_ERROR) {
            exit(lastError);
        }        
        
        //play source 0
        al.sourcePlay(sources.getInt(0));
        if((lastError = al.getError()) != AL.NO_ERROR) {
            exit(lastError);
        }        
        
        //wait 5 secs
        try {
            System.out.println("Waiting 5 seconds for sound to complete");
            Thread.sleep(5000);
        } catch (InterruptedException inte) {
        }
        
        //stop source 0
        al.sourceStop(sources.getInt(0));
        if((lastError = al.getError()) != AL.NO_ERROR) {
            exit(lastError);
        }        
        
        //delete buffers and sources
        al.deleteSources(1, Sys.getDirectBufferAddress(sources));
        if((lastError = al.getError()) != AL.NO_ERROR) {
            exit(lastError);
        }
        
        al.deleteBuffers(1, Sys.getDirectBufferAddress(buffers));
        if((lastError = al.getError()) != AL.NO_ERROR) {
            exit(lastError);
        }        
        
        //no errorchecking from now on, since our context is gone.
        //shutdown
        alc.makeContextCurrent(null);
        alc.destroyContext(context);
        alc.closeDevice(device);
    }
    
    /**
     * main entry point
     *+
     * @param args String array containing arguments
     */
    public static void main(String[] args) {
        PlayTest playTest = new PlayTest();
        playTest.execute(args);
    }
}