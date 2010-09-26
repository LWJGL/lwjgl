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
package org.lwjgl.openal;

import java.nio.IntBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.util.generator.*;
import org.lwjgl.util.generator.openal.ALenum;
import org.lwjgl.util.generator.openal.ALuint;
import org.lwjgl.util.generator.openal.ALvoid;

/**
 * <br>
 * This is the core OpenAL class. This class implements
 * AL.h version 1.1
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision: 2286 $
 * $Id: AL10.java 2286 2006-03-23 19:32:21Z matzon $
 */
public interface AL11 {

	/** Source buffer position information in seconds */
	public static final int AL_SEC_OFFSET 							= 0x1024;

	/** Source buffer position information in samples */
	public static final int AL_SAMPLE_OFFSET						= 0x1025;

	/** Source buffer position information in bytes */
	public static final int AL_BYTE_OFFSET							= 0x1026;

	/** Type of source: Buffer has been attached using AL_BUFFER */
	public static final int AL_STATIC								= 0x1028;

	/** Type of source: if one or more Buffers have been attached using alSourceQueueBuffers */
	public static final int AL_STREAMING							= 0x1029;

	/** Type of source: when it has the NULL buffer attached */
	public static final int AL_UNDETERMINED							= 0x1030;

	/** @see AL10#AL_INVALID_OPERATION */
	public static final int AL_ILLEGAL_COMMAND						= 0xA004;

	/** Speed of Sound in units per second */
	public static final int AL_SPEED_OF_SOUND						= 0xC003;

	public static final int AL_LINEAR_DISTANCE						= 0xD003;
	public static final int AL_LINEAR_DISTANCE_CLAMPED				= 0xD004;
	public static final int AL_EXPONENT_DISTANCE					= 0xD005;
	public static final int AL_EXPONENT_DISTANCE_CLAMPED			= 0xD006;

	/**
	 * Listener attributes are changed using the Listener group of commands.
	 *
	 * @param pname name of the attribute to be set
	 * @param v1 value value 1
	 * @param v2 value value 2
	 * @param v3 value value 3
	 */
	@ALvoid
	void alListener3i(@ALenum int pname, int v1, int v2, int v3);

	/**
	 * Listener state is maintained inside the AL implementation and can be queried in
	 * full.
	 *
	 * @param pname name of the attribute to be retrieved
	 * @param intdata Buffer to write ints to
	 */
	// TODO: What's the real minimum number of elements?
	@StripPostfix("intdata")
	@ALvoid
	void alGetListeneriv(@ALenum int pname, @OutParameter @Check("1") FloatBuffer intdata);

	/**
	 * Specifies the position and other properties as taken into account during
	 * sound processing.
	 *
	 * @param source Source to set property on
	 * @param pname property to set
	 * @param v1 value 1 of property
	 * @param v2 value 2 of property
	 * @param v3 value 3 of property
	 */
	@ALvoid
	void alSource3i(@ALuint int source, @ALenum int pname, int v1, int v2, int v3);

	/**
	 * Specifies the position and other properties as taken into account during
	 * sound processing.
	 *
	 * @param source Source to set property on
	 * @param pname property to set
	 * @param value IntBuffer containing value of property
	 */
	// TODO: What's the correct minimum value?
	@StripPostfix("value")
	@ALvoid
	void alSourceiv(@ALuint int source, @ALenum int pname, @Check("1") @Const IntBuffer value);

	/**
	 * This function sets a floating point property of a buffer.
	 * <i>note: There are no relevant buffer properties defined in OpenAL 1.1 which can be affected by
	 * this call, but this function may be used by OpenAL extensions.</i>
	 *
	 * @param buffer Buffer to set property on
	 * @param pname property to set
	 * @param value value of property
	 */
	@ALvoid
	void alBufferf(@ALuint int buffer, @ALenum int pname, float value);

	/**
	 * This function sets a floating point property of a buffer.
	 * <i>note: There are no relevant buffer properties defined in OpenAL 1.1 which can be affected by
	 * this call, but this function may be used by OpenAL extensions.</i>
	 *
	 * @param buffer Buffer to set property on
	 * @param pname property to set
	 * @param v1 value of property
	 * @param v2 value of property
	 * @param v3 value of property
	 */
	@ALvoid
	void alBuffer3f(@ALuint int buffer, @ALenum int pname, float v1, float v2, float v3);

	/**
	 * This function sets a floating point property of a buffer.
	 * <i>note: There are no relevant buffer properties defined in OpenAL 1.1 which can be affected by
	 * this call, but this function may be used by OpenAL extensions.</i>
	 *
	 * @param buffer Buffer to set property on
	 * @param pname property to set
	 * @param value FloatBuffer containing value of property
	 */
	// TODO: What's the correct minimum value?
	@StripPostfix("value")
	@ALvoid
	void alBufferfv(@ALuint int buffer, @ALenum int pname, @Check("1") @Const FloatBuffer value);

	/**
	 * This function sets an integer property of a buffer.
	 * <i>note: There are no relevant buffer properties defined in OpenAL 1.1 which can be affected by
	 * this call, but this function may be used by OpenAL extensions.</i>
	 *
	 * @param buffer Buffer to set property on
	 * @param pname property to set
	 * @param value value of property
	 */
	@ALvoid
	void alBufferi(@ALuint int buffer, @ALenum int pname, int value);

	/**
	 * This function sets an integer property of a buffer.
	 * <i>note: There are no relevant buffer properties defined in OpenAL 1.1 which can be affected by
	 * this call, but this function may be used by OpenAL extensions.</i>
	 *
	 * @param buffer Buffer to set property on
	 * @param pname property to set
	 * @param v1 value of property
	 * @param v2 value of property
	 * @param v3 value of property
	 */
	@ALvoid
	void alBuffer3i(@ALuint int buffer, @ALenum int pname, int v1, int v2, int v3);

	/**
	 * This function sets an integer property of a buffer.
	 * <i>note: There are no relevant buffer properties defined in OpenAL 1.1 which can be affected by
	 * this call, but this function may be used by OpenAL extensions.</i>
	 *
	 * @param buffer Buffer to set property on
	 * @param pname property to set
	 * @param value IntBuffer containing value of property
	 */
	// TODO: What's the correct minimum value?
	@StripPostfix("value")
	@ALvoid
	void alBufferiv(@ALuint int buffer, @ALenum int pname, @Check("1") @Const IntBuffer value);

	/**
	 * This function retrieves an integer property of a buffer.
	 * <i>note: There are no relevant buffer properties defined in OpenAL 1.1 which can be affected by
	 * this call, but this function may be used by OpenAL extensions.</i>
	 *
	 * @param buffer Buffer to get property from
	 * @param pname name of property
	 * @return int
	 */
	@ALvoid
	void alGetBufferi(@ALuint int buffer, @ALenum int pname, @Result int value);

	/**
	 * This function retrieves an integer property of a buffer.
	 *
	 * @param buffer Buffer to get property from
	 * @param pname name of property
	 */
	// TODO: What's the correct minimum value?
	@StripPostfix("values")
	@ALvoid
	void alGetBufferiv(@ALuint int buffer, @ALenum int pname, @OutParameter @Check("1") IntBuffer values);

	/**
	 * This function retrieves a floating point property of a buffer.
	 * <i>note: There are no relevant buffer properties defined in OpenAL 1.1 which can be affected by
	 * this call, but this function may be used by OpenAL extensions.</i>
	 *
	 * @param buffer Buffer to get property from
	 * @param pname name of property
	 * @return floating point property
	 */
	@ALvoid
	void alGetBufferf(@ALuint int buffer, @ALenum int pname, @Result float value);

	/**
	 * This function retrieves a floating point property of a buffer.
	 * <i>note: There are no relevant buffer properties defined in OpenAL 1.1 which can be affected by
	 * this call, but this function may be used by OpenAL extensions.</i>
	 *
	 * @param buffer Buffer to get property from
	 * @param pname name of property
	 */
	// TODO: What's the correct minimum value?
	@StripPostfix("values")
	@ALvoid
	void alGetBufferfv(@ALuint int buffer, @ALenum int pname, @OutParameter @Check("1") FloatBuffer values);

	/**
	 * <p>
	 * AL_SPEED_OF_SOUND allows the application to change the reference (propagation)
	 * speed used in the Doppler calculation. The source and listener velocities should be
	 * expressed in the same units as the speed of sound.
	 * </p>
	 * <p>
	 * A negative or zero value will result in an AL_INVALID_VALUE error, and the
	 * command is ignored. The default value is 343.3 (appropriate for velocity units of meters
	 * and air as the propagation medium). The current setting can be queried using
	 * alGetFloat{v} and AL_SPEED_OF_SOUND.
	 * Distance and velocity units are completely independent of one another (so you could use
	 * different units for each if desired).
	 * </p>
	 *
	 * @param value distance model to be set
	 */
	@ALvoid
	void alSpeedOfSound(float value);
}
