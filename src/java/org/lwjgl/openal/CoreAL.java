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
 * This is the core OpenAL class. This class implements 
 * AL.h version 1.0
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class CoreAL extends BaseAL implements BaseALConstants {
    
    /** Creates a new instance of CoreAL */
    public CoreAL() {
    }
    
    /**
     * Retrieve the current error state and then clears the error state.
     *
     * @return current error state
     */
    public native int           getError();
    
    /**
     * Retrieve an OpenAL string property.
     *
     * @param param The property to be returned
     * @return OpenAL String property
     */
    public native String        getString(int param);
    
    /**
     * Generate one or more buffers.
     *
     * @param n number of buffers to generate
     * @param buffers array holding buffers
     */
    public native void          genBuffers(int n, int[] buffers);
    
    /**
     * Generate one or more sources.
     *
     * @param n number of sources to generate
     * @param sources array holding sources
     */
    public native void          genSources(int n, int[] sources);
    
    /**
     * Fill a buffer with audio data.
     *
     * @param buffer Buffer to fill
     * @param format format sound data is in
     * @param data location of data (pointer)
     * @param size size of data segment
     * @param freq frequency of data
     */
    public native void          bufferData(int buffer, int format, int data, int size, int freq);
    
    /**
     * Set an integer property of a source.
     *
     * @param source Source to det property on
     * @param param property to set
     * @param value value of property
     */
    public native void          sourcei(int source, int param, int value);
    
    /**
     * Play a source.
     *
     * @param source Source to play
     */
    public native void          sourcePlay(int source);
    
    /**
     * Stops a source.
     *
     * @param source Source to stop
     */
    public native void          sourceStop(int source);
    
    /**
     * Delete one or more sources.
     *
     * @param n Number of sources to delete
     * @param source Source array to delete from
     */
    public native void          deleteSources(int n, int[] source);
    
    
    /**
     * Delete one or more buffers.
     *
     * @param n Number of buffers to delete
     * @param buffers Buffer array to delete from
     */
    public native void          deleteBuffers(int n, int[] buffers);
}
