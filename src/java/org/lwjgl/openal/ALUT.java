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
 * This is the utility class for OpenAL. This class implements functions
 * in alut.h
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class ALUT {
    
    static {
        try {
            System.loadLibrary(org.lwjgl.Sys.getLibraryName());
        } catch (UnsatisfiedLinkError ule) {
            System.out.println("Failed to load OpenAL library: " + org.lwjgl.Sys.getLibraryName());
            ule.printStackTrace();
        }
    }
    
    /** Creates a new instance of ALUT */
    public ALUT() {
    }
    
    /**
     * Initializes the OpenAL engine
     *
     * @param args String array of arguments to engine
     */
    public native void                  init(String[] args);
    
    /**
     * Loads a wave file into memory
     *
     * @param file name of file to load (in current working directory)
     * @return ALUTLoadWAVData object containing information regarding wave data loaded
     */
    public native ALUTLoadWAVData       loadWAVFile(String file);
    
    /**
     * Loads a byte buffer into memory
     *
     * @param buffer buffer address containing file
     * @return ALUTLoadWAVData object containing information regarding wave data loaded
     */
    public native ALUTLoadWAVData       loadWAVMemory(int buffer);
    
    /**
     * Unloads the specified file from memory
     *
     * @param format OpenAL format specifier
     * @param data address of data (pointer)
     * @param size size of the data in bytes
     * @param freq frequency of the data
     */
    public native void                  unloadWAV(int format, int data, int size, int freq);
    
    /**
     * Deinitializes the OpenAL engine
     */
    public native void                  exit();
}