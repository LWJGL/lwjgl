/* 
 * Copyright (c) 2002 Lightweight Java Game Library Project
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

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;


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

	/**
	 * Enables a feature of the OpenAL driver.
	 *
	 * @param capability name of a capability to enable
	 */
	public static native void alEnable(int capability);

	/**
	 * Disables a feature of the OpenAL driver.
	 *
	 * @param capability name of a capability to disable
	 */
	public static native void alDisable(int capability);

	/**
	 * Checks if a specific feature is enabled in the OpenAL driver.
	 *
	 * @param capability name of a capability to check
	 * @return true if named feature is enabled
	 */
	public static native boolean alIsEnabled(int capability);

	/**
	 * Hinting for implementation
	 * NOTE: This method is a NOP, but is provided for completeness.
	 *
	 * @param target FIXME
	 * @param mode FIXME
	 */
	public static native void alHint(int target, int mode);

	/**
	 * Returns a boolean OpenAL state.
	 *
	 * @return boolean state described by pname will be returned.
	 */
	public static native boolean alGetBoolean(int pname);

	/**
	  * Returns an int OpenAL state.
	  *
	  * @return int state described by pname will be returned.
	  */
	public static native int alGetInteger(int pname);

	/**
	  * Returns a float OpenAL state.
	  *
	  * @return float state described by pname will be returned.
	  */
	public static native float alGetFloat(int pname);

	/**
	  * Returns a double OpenAL state.
	  *
	  * @return double state described by pname will be returned.
	  */
	public static native double alGetDouble(int pname);

	/**
	  * Returns a boolean OpenAL state.
	  *
	  * @param pname state to be queried
	  * @param data Buffer to place the booleans in
	  */
	public static native void alGetBooleanv(int pname, ByteBuffer data);

	/**
	  * Returns an integer OpenAL state.
	  *
	  * @param pname state to be queried
	  * @param data Buffer to place the integers in
	  */
	public static native void alGetIntegerv(int pname, IntBuffer data);

	/**
	  * Returns a floating point OpenAL state.
	  *
	  * @param pname state to be queried
	  * @param data Buffer to place the floats in
	  */
	public static native void alGetFloatv(int pname, FloatBuffer data);

	/**
	  * Returns a double OpenAL state.
	  *
	  * @param pname state to be queried
	  * @param data Buffer to place the floats in
	  */
	public static native void alGetDoublev(int pname, DoubleBuffer data);

	/**
	 * Retrieve an OpenAL string property.
	 *
	 * @param pname The property to be returned
	 * @return OpenAL String property
	 */
	public static native String alGetString(int pname);

	/**
	 * Retrieve the current error state and then clears the error state.
	 *
	 * @return current error state
	 */
	public static native int alGetError();

	/**
	 * Test if a specific extension is available for the OpenAL driver.
	 *
	 * @param fname String describing the desired extension
	 * @return true if extension is available, false if not
	 */
	public static native boolean alIsExtensionPresent(String fname);

	/**
	 * Returns the enumeration value of an OpenAL enum described by a string.
	 *
	 * @param ename String describing an OpenAL enum
	 * @return Actual int for the described enumeration name
	 */
	public static native int alGetEnumValue(String ename);

	/**
	 * Sets an integer property of the listener
	 *
	 * @param pname name of the attribute to be set
	 * @param value value to set the attribute to
	 */
	public static native void alListeneri(int pname, int value);

	/**
	 * Sets a floating point property of the listener
	 *
	 * @param pname name of the attribute to be set
	 * @param value floating point value to set the attribute to
	 */
	public static native void alListenerf(int pname, float value);
  
  /**
   * Sets a floating point property of the listener
   *
   * @param pname name of the attribute to be set
   * @param v1 value value 1
   * @param v2 value value 2
   * @param v3 float value 3
   */
  public static native void alListener3f(int pname, float v1, float v2, float v3);
  

	/**
	 * Sets a floating point vector property of the listener
	 *
	 * @param pname name of the attribute to be set
	 * @param floatdata Buffer to read floats from
	 */
	public static native void alListenerfv(int pname, FloatBuffer floatdata);

	/**
	 * Gets an integer property of the listener.
	 *
	 * @param pname name of the attribute to be retrieved
	 * @param integerdata Buffer to write integer to
	 */
	public static native void alGetListeneri(int pname, IntBuffer integerdata);

	/**
	 * Gets a floating point property of the listener.
	 *
	 * @param pname name of the attribute to be retrieved
	 * @param floatdata Buffer to write float to
	 */
	public static native void alGetListenerf(int pname, FloatBuffer floatdata);

	/**
	 * Retrieves a floating point vector property of the listener.
	 *
	 * @param pname name of the attribute to be retrieved
	 * @param floatdata Buffer to write floats to
	 */
	public static native void alGetListenerfv(int pname, FloatBuffer floatdata);

	/**
	 * Generate one or more sources.
	 *
	 * @param n number of sources to generate
	 * @param sources array holding sources
	 */
	public static native void alGenSources(int n, IntBuffer sources);

	/**
	 * Delete one or more sources.
	 *
	 * @param n Number of sources to delete
	 * @param source Source array to delete from
	 */
	public static native void alDeleteSources(int n, IntBuffer source);

	/**
	 * Tests if a source is valid.
	 *
	 * @param id id of source to be testes for validity
	 * @return true if id is valid, false if not
	 */
	public static native boolean alIsSource(int id);

	/**
	 * Set an integer property of a source.
	 *
	 * @param source Source to det property on
	 * @param pname property to set
	 * @param value value of property
	 */
	public static native void alSourcei(int source, int pname, int value);

	/**
	 * Set a floating point property of a source.
	 *
	 * @param source Source to det property on
	 * @param pname property to set
	 * @param value value of property
	 */
	public static native void alSourcef(int source, int pname, float value);
  
  /**
   * Sets a source property requiring three floating point values.
   *
   * @param source Source to set property on
   * @param pname property to set
   * @param v1 value 1 of property
   * @param v2 value 2 of property
   * @param v3 value 3 of property
   */
  public static native void alSource3f(
    int source,
    int pname,
    float v1,
    float v2,
    float v3);
  

	/**
	 * Sets a floating point vector property of a source.
	 *
	 * @param source source whichs attribute is being set
	 * @param pname name of the attribute being set 
	 * @param floatdata Buffer to read floats from
	 */
	public static native void alSourcefv(int source, int pname, FloatBuffer floatdata);

	/**
	 * Retrieves an integer property of a source.
	 *
	 * @param source source to get property from
	 * @param pname name of property
	 * @param integerdata Buffer to write integer to
	 */
	public static native void alGetSourcei(int source, int pname, IntBuffer integerdata);

	/**
	 * Retrieves a floating point property of a source.
	 *
	 * @param source source to get property from
	 * @param pname name of property
	 * @param floatdata Buffer to write float to
	 */
	public static native void alGetSourcef(int source, int pname, FloatBuffer floatdata);

	/**
	 * Gets a floating point vector property from a Source object.
	 *
	 * @param source Source to get property from
	 * @param pname property to get
	 * @param floatdata Buffer to write floats to
	 */
	public static native void alGetSourcefv(int source, int pname, FloatBuffer floatdata);

	/**
	 * Plays a set of sources.
	 *
	 * @param n number of sources to play
	 * @param sources array of sources to play
	 */
	public static native void alSourcePlayv(int n, IntBuffer sources);

	/**
	 * Pauses a set of sources.
	 *
	 * @param n number of sources to pause
	 * @param sources array of sources to pause
	 */
	public static native void alSourcePausev(int n, IntBuffer sources);

	/**
	 * Stops a set of sources.
	 *
	 * @param n number of sources to stop
	 * @param sources array of sources to stop
	 */
	public static native void alSourceStopv(int n, IntBuffer sources);

	/**
	 * Rewinds a set of sources.
	 *
	 * @param n number of sources to rewind
	 * @param sources array of sources to rewind
	 */
	public static native void alSourceRewindv(int n, IntBuffer sources);

	/**
	 * Play a source.
	 *
	 * @param source Source to play
	 */
	public static native void alSourcePlay(int source);

	/**
	 * Pauses a source.
	 *
	 * @param source Source to pause
	 */
	public static native void alSourcePause(int source);

	/**
	 * Stops a source.
	 *
	 * @param source Source to stop
	 */
	public static native void alSourceStop(int source);

	/**
	 * Rewinds a source.
	 *
	 * @param source Source to rewind
	 */
	public static native void alSourceRewind(int source);

	/**
	 * Generate one or more buffers.
	 *
	 * @param n number of buffers to generate
	 * @param buffers holding buffers
	 */
	public static native void alGenBuffers(int n, IntBuffer buffers);

	/**
	 * Delete one or more buffers.
	 *
	 * @param n Number of buffers to delete
	 * @param buffers Buffer to delete from
	 */
	public static native void alDeleteBuffers(int n, IntBuffer buffers);

	/**
	 * Tests if buffer is valid.
	 *
	 * @param buffer buffer to be tested for validity
	 * @return true if supplied buffer is valid, false if not
	 */
	public static native boolean alIsBuffer(int buffer);

	/**
	 * Fill a buffer with audio data.
	 *
	 * @param buffer Buffer to fill
	 * @param format format sound data is in
	 * @param data location of data 
	 * @param size size of data segment
	 * @param freq frequency of data
	 */
	public static native void alBufferData(
		int buffer,
		int format,
    ByteBuffer data,
		int size,
		int freq);

	/**
	 * Retrieves an integer property from a buffer.
	 *
	 * @param buffer buffer to get property from
	 * @param pname name of property to retrieve
	 * @param integerdata Buffer to write integer to
	 */
	public static native void alGetBufferi(int buffer, int pname, IntBuffer integerdata);

	/**
	 * Retrieves a floating point property from a buffer.
	 *
	 * @param buffer buffer to get property from
	 * @param pname name of property to retrieve
	 * @param floatdata Buffer to write float to
	 */
	public static native void alGetBufferf(int buffer, int pname, FloatBuffer floatdata);

	/**
	 * Queues a set of buffers on a source.
	 *
	 * @param source source to queue buffers onto
	 * @param n number of buffers to be queued
	 * @param buffers buffers to be queued
	 */
	public static native void alSourceQueueBuffers(int source, int n, IntBuffer buffers);

	/**
	 * Unqueues a set of buffers attached to a source.
	 *
	 * @param source source to unqueue buffers from
	 * @param n number of buffers to be unqueued
	 * @param buffers buffers to be unqueued
	 */
	public static native void alSourceUnqueueBuffers(int source, int n, IntBuffer buffers);

	/**
	 * Selects the OpenAL distance model.
	 *
	 * @param value distance model to be set
	 */
	public static native void alDistanceModel(int value);

	/**
	 * Selects the OpenAL Doppler factor value.
	 *
	 * @param value Doppler scale value to set
	 */
	public static native void alDopplerFactor(float value);

	/**
	 * Selects the OpenAL Doppler velocity value.
	 *
	 * @param value Doppler velocity value to set
	 */
	public static native void alDopplerVelocity(float value);
}
