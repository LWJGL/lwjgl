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
 * This class is used by the alutLoadWAVFile method. Since we
 * cannot modify values supplied to the method (since JNI is pass by value)
 * we return this object, which encapsulates the file loaded. Use this class
 * when unloading using 'alutUnloadWAV'.
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class ALUTLoadWAVFile {
    
    /* format of file */
    public final int format;
    
    /* pointer to data allocated */
    public final int data;
    
    /* size of data allocated */
    public final int size;
    
    /* frequency of sound data */
    public final int freq;
    
    /* whether or not to loop */
    public final boolean loop;
    
    /**
     * Creates an ALUTLoadWAVFile object with specified properties
     *
     * @param format OpenAL format specifier
     * @param data address of data (pointer)
     * @param size size of the data in bytes
     * @param freq frequency of the data
     * @param loop looping indicator for the WAV data
     */
    public ALUTLoadWAVFile(int format, int data, int size, int freq, boolean loop) {
        this.format     = format;
        this.data       = data;
        this.size       = size;
        this.freq       = freq;
        this.loop       = loop;
    }
}