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
     * Enables a feature of the OpenAL driver.
     *
     * @param capability name of a capability to enable
     */
    public native void          enable(int capability);

    /**
     * Disables a feature of the OpenAL driver.
     *
     * @param capability name of a capability to disable
     */    
    public native void          disable(int capability);
    
    /**
     * Checks if a specific feature is enabled in the OpenAL driver.
     *
     * @param capability name of a capability to check
     * @return true if named feature is enabled
     */
    public native boolean       isEnabled(int capability);
    
    /**
     * Returns a boolean OpenAL state.
     *
     * @param parameter state to be queried
     * @return boolean state described by pname will be returned.
     */
    public native boolean       getBoolean(int pname);
    
   /**
     * Returns an int OpenAL state.
     *
     * @param parameter state to be queried
     * @return int state described by pname will be returned.
     */    
    public native int           getInteger(int pname);
    
   /**
     * Returns a float OpenAL state.
     *
     * @param parameter state to be queried
     * @return float state described by pname will be returned.
     */    
    public native float         getFloat(int pname);
    
   /**
     * Returns a double OpenAL state.
     *
     * @param parameter state to be queried
     * @return double state described by pname will be returned.
     */    
    public native double        getDouble(int pname);
    
    /**
     * Retrieve an OpenAL string property.
     *
     * @param param The property to be returned
     * @return OpenAL String property
     */
    public native String        getString(int param);    
    
    /**
     * Retrieve the current error state and then clears the error state.
     *
     * @return current error state
     */
    public native int           getError();

    /**
     * Test if a specific extension is available for the OpenAL driver.
     *
     * @param fname String describing the desired extension
     * @return true if extension is available, false if not
     */
    public native boolean       isExtensionPresent(String fname);
    
    /**
     * Returns the address of an OpenAL extension function.
     *
     * @param fname String containing the function name
     * @return int specifying offset of extension
     */
    public native int           getProcAddress(String fname);
    
    /**
     * Returns the enumeration value of an OpenAL enum described by a string.
     *
     * @param ename String describing an OpenAL enum
     * @return Actual int for the described enumeration name
     */
    public native int           getEnumValue(String ename);
    
    /**
     * Sets an integer property of the listener
     *
     * @param pname name of the attribute to be set
     * @param integer value to set the attribute to
     */
    public native void          listeneri(int pname, int value);
    
    /**
     * Sets a floating point property of the listener
     *
     * @param pname name of the attribute to be set
     * @param value floating point value to set the attribute to
     */    
    public native void          listenerf(int pname, float value);
    
    /**
     * Sets a floating point property of the listener
     *
     * @param pname name of the attribute to be set
     * @param v1 value value 1
     * @param v2 value value 2
     * @param v3 float value 3
     */     
    public native void          listener3f(int pname, float v1, float v2, float v3); 
    
    /**
     * Gets an integer property of the listener.
     *
     * @param pname name of the attribute to be retrieved
     * @return integer value of property
     */
    public native int          getListeneri(int pname);
    
    /**
     * Gets a floating point property of the listener.
     *
     * @param pname name of the attribute to be retrieved
     * @return floating point value of property
     */    
    public native float        getListenerf(int pname);
    
    /**
     * Generate one or more sources.
     *
     * @param n number of sources to generate
     * @param sources array holding sources
     */
    public native void          genSources(int n, int[] sources);
  
    /**
     * Delete one or more sources.
     *
     * @param n Number of sources to delete
     * @param source Source array to delete from
     */
    public native void          deleteSources(int n, int[] source);

    /**
     * Tests if a source is valid.
     *
     * @param id id of source to be testes for validity
     * @return true if id is valid, false if not
     */
    public native boolean       isSource(int id);
    
    /**
     * Set an integer property of a source.
     *
     * @param source Source to det property on
     * @param param property to set
     * @param value value of property
     */
    public native void          sourcei(int source, int param, int value);
    
    /**
     * Set a floating point property of a source.
     *
     * @param source Source to det property on
     * @param param property to set
     * @param value value of property
     */    
    public native void          sourcef(int source, int param, float value); 
    
    /**
     * Sets a source property requiring three floating point values.
     *
     * @param source Source to det property on
     * @param param property to set
     * @param v1 value 1 of property
     * @param v2 value 2 of property
     * @param v3 value 3 of property
     */
    public native void          source3f(int source, int param, float v1, float v2, float v3);
    
    /**
     * Retrieves an integer property of a source.
     *
     * @param source source to get property from
     * @param pname name of property
     * @return integer value of pname
     */
    public native int           getSourcei(int source, int param);
    
    /**
     * Retrieves a floating point property of a source.
     *
     * @param source source to get property from
     * @param pname name of property
     * @return integer value of pname
     */    
    public native float         getSourcef(int source, int param);
    
    /**
     * Plays a set of sources.
     *
     * @param n number of sources to play
     * @param source array of sources to play
     */
    public native void          sourcePlayv(int n, int[] sources);

    /**
     * Pauses a set of sources.
     *
     * @param n number of sources to pause
     * @param source array of sources to pause
     */
    public native void          sourcePausev(int n, int[] sources);

    /**
     * Stops a set of sources.
     *
     * @param n number of sources to stop
     * @param source array of sources to stop
     */
    public native void          sourceStopv(int n, int[] sources);
    
    /**
     * Rewinds a set of sources.
     *
     * @param n number of sources to rewind
     * @param source array of sources to rewind
     */
    public native void          sourceRewindv(int n, int[] sources);
        
    /**
     * Play a source.
     *
     * @param source Source to play
     */
    public native void          sourcePlay(int source);

    /**
     * Pauses a source.
     *
     * @param source Source to pause
     */
    public native void          sourcePause(int source);
    
    /**
     * Stops a source.
     *
     * @param source Source to stop
     */
    public native void          sourceStop(int source);
    
    /**
     * Rewinds a source.
     *
     * @param source Source to rewind
     */    
    public native void          sourceRewind(int source);    

    /**
     * Generate one or more buffers.
     *
     * @param n number of buffers to generate
     * @param buffers array holding buffers
     */
    public native void          genBuffers(int n, int[] buffers);
    
    /**
     * Delete one or more buffers.
     *
     * @param n Number of buffers to delete
     * @param buffers Buffer array to delete from
     */
    public native void          deleteBuffers(int n, int[] buffers);    
    
    /**
     * Tests if buffer is valid.
     *
     * @param buffer buffer to be tested for validity
     * @return true if supplied buffer is valid, false if not
     */
    public native boolean       isBuffer(int buffer);
    
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
     * Queues a set of buffers on a source.
     *
     * @param source source to queue buffers onto
     * @param n number of buffers to be queued
     * @param buffers buffers to be queued
     */
    public native void          sourceQueueBuffers(int source, int n, int[] buffers);
    
    /**
     * Unqueues a set of buffers attached to a source.
     *
     * @param source source to unqueue buffers from
     * @param n number of buffers to be unqueued
     * @param buffers buffers to be unqueued
     */    
    public native void          sourceUnqueueBuffers(int source, int n, int[] buffers);
    
    /**
     * Selects the OpenAL distance model.
     *
     * @param value distance model to be set
     */
    public native void          distanceModel(int value);
    
    /**
     * Selects the OpenAL Doppler factor value.
     *
     * @param value Doppler scale value to set
     */
    public native void          dopplerFactor(float value);
    
    /**
     * Selects the OpenAL Doppler velocity value.
     *
     * @param value Doppler velocity value to set
     */
    public native void          dopplerVelocity(float value);
    
    //non implemented methods:
    //===========================================
    //public native void          getBooleanv(int pname, boolean* data);
    //public native void          fetIntegerv(int pname, int* data);
    //public native void          getFloatv(int pname, float* data);
    //public native void          getDoublev(int pname, double* data);
    //public native float         listenerfv(int pname, float* values); 
    //public native void          getListener3f(int pname, ALfloat* v1, ALfloat* v2, ALfloat* v3); 
    //public native void          getListenerfv(int pname, ALfloat* values); 
    //public native void          hint(int target, int mode);
    //public native void          sourcefv(int source, int param, ALfloat* values); 
    //public native void          getSource3f(int source,  int param, ALfloat* v1, ALfloat* v2, ALfloat* v3 ;
    //public native void          getSourcefv(int source, int param, ALfloat* values);
    //public native void          getBufferi(int buffer, int param, ALint*   value);
    //public native void          getBufferf(int buffer, int param, ALfloat* value);        
}
