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
package org.lwjgl.openal;

/**
 * $Id$
 *
 * This is the OpenAL Test.
 * This class will eventually test *all* apects of OpenAL...
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class OpenALTest {
    
    /**
     * Creates an instance of OpenALTest
     */
    public OpenALTest() {
    }
    
    /**
     * main entry point
     *
     * @param args String array containing arguments
     */
    public static void main(String[] args) {
        //create OpenAL instance (and util lib)
        AL al           = new AL();
        ALUT alut       = new ALUT();
        
        /* buffers */
        int[] buffers   = new int[1];
        
        /* sources */
        int[] sources   = new int[1];
        
        /* initialize */
        alut.init(args);
        
        /* create buffers and sources */
        al.genBuffers(1, buffers);
        al.genSources(1, sources);
        
        /* load data */
        ALUTLoadWAVFile file = alut.loadWAVFile("footsteps.wav");
        
        /* copy to buffers */
        al.bufferData(buffers[0], file.format, file.data, file.size, file.freq);
        
        /* unload file again */
        alut.unloadWAV(file.format, file.data, file.size, file.freq);
        
        /* set up source input */
        al.sourcei(sources[0], AL.BUFFER, buffers[0]);
        
        /* lets loop the sound */
        al.sourcei(sources[0], AL.LOOPING, AL.TRUE);
        
        /* play source 0 */
        al.sourcePlay(sources[0]);
        
        System.out.println("will exit in 5 seconds (so we don't crash if weird stuff has happened with file...)\n");
        for(int i=0; i<5; i++) {
            try {
                System.out.println(5-i);
                Thread.sleep(1000);
            } catch(InterruptedException inte) {
            }
        }
        
        /* stop source 0 */
        al.sourceStop(sources[0]);
        
        /* delete buffers and sources */
        al.deleteSources(1, sources);
        al.deleteBuffers(1, buffers);
        
        /* shutdown */
        alut.exit();
    }
}