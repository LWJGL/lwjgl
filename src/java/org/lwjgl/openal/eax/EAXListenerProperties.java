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
package org.lwjgl.openal.eax;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.lwjgl.Sys;

/**
 * $Id$
 *
 * This class encapsultaes the EAXLISTENERPROPERTIES struct
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class EAXListenerProperties {
	
	/** Whether auto commit has been anabled */
	private boolean autoCommit = true;	

	/** ByteBuffer representing EAXLISTENERPROPERTIES */
	protected ByteBuffer eaxListenerProperties;

	/** size needed by ByteBuffer to contain EAXLISTENERPROPERTIES */
	protected static int EAXLISTENERPROPERTIES_SIZE;
	
	// EAX values and offsets
	// ========================================================	

	/** room effect level offset */
	protected static int room_offset;

	/** room effect level at high frequencies offset */
	protected static int roomHF_offset;

	/**like DS3D flRolloffFactor but for room effect offset */
	protected static int roomRolloffFactor_offset;

	/** reverberation decay time at low frequencies offset */
	protected static int decayTime_offset;

	/** high-frequency to low-frequency decay time ratio offset */
	protected static int decayHFRatio_offset;

	/** early reflections level relative to room effect offset */
	protected static int reflections_offset;

	/** initial reflection delay time offset */
	protected static int reflectionsDelay_offset;

	/** late reverberation level relative to room effect offset */
	protected static int reverb_offset;

	/** late reverberation delay time relative to initial reflection offset */
	protected static int reverbDelay_offset;

	/** sets all listener properties offset */
	protected static int environment_offset;

	/** environment size in meters offset */
	protected static int environmentSize_offset;

	/** environment diffusion offset */
	protected static int environmentDiffusion_offset;

	/** change in level per meter at 5 kHz offset */
	protected static int airAbsorptionHF_offset;

	/** modifies the behavior of properties offset */
	protected static int flags_offset;

	public static final int EAXLISTENER_NONE = 0;
	public static final int EAXLISTENER_ALLPARAMETERS = 1;
	public static final int EAXLISTENER_ROOM = 2;
	public static final int EAXLISTENER_ROOMHF = 3;
	public static final int EAXLISTENER_ROOMROLLOFFFACTOR = 4;
	public static final int EAXLISTENER_DECAYTIME = 5;
	public static final int EAXLISTENER_DECAYHFRATIO = 6;
	public static final int EAXLISTENER_REFLECTIONS = 7;
	public static final int EAXLISTENER_REFLECTIONSDELAY = 8;
	public static final int EAXLISTENER_REVERB = 9;
	public static final int EAXLISTENER_REVERBDELAY = 10;
	public static final int EAXLISTENER_ENVIRONMENT = 11;
	public static final int EAXLISTENER_ENVIRONMENTSIZE = 12;
	public static final int EAXLISTENER_ENVIRONMENTDIFFUSION = 13;
	public static final int EAXLISTENER_AIRABSORPTIONHF = 14;
	public static final int EAXLISTENER_FLAGS = 15;

	/** changes take effect immediately */
	public static final int EAXLISTENER_IMMEDIATE = 0x00000000;

	/** changes take effect later */
	public static final int EAXLISTENER_DEFERRED = 0x80000000;

	public static final int EAXLISTENER_COMMITDEFERREDSETTINGS =
		(EAXLISTENER_NONE | EAXLISTENER_IMMEDIATE);

	/** reverberation decay time */
	public static final int EAXLISTENERFLAGS_DECAYTIMESCALE = 0x00000001;

	/** reflection level */
	public static final int EAXLISTENERFLAGS_REFLECTIONSSCALE = 0x00000002;

	/** initial reflection delay time */
	public static final int EAXLISTENERFLAGS_REFLECTIONSDELAYSCALE = 0x00000004;

	/** reflections level */
	public static final int EAXLISTENERFLAGS_REVERBSCALE = 0x00000008;

	/** late reverberation delay time */
	public static final int EAXLISTENERFLAGS_REVERBDELAYSCALE = 0x00000010;

	/** This flag limits high-frequency decay time according to air absorption. */
	public static final int EAXLISTENERFLAGS_DECAYHFLIMIT = 0x00000020;

	/** reserved future use */
	public static final int EAXLISTENERFLAGS_RESERVED = 0xFFFFFFC0;

	// property ranges and defaults:
	public static final int EAXLISTENER_MINROOM = -10000;
	public static final int EAXLISTENER_MAXROOM = 0;
	public static final int EAXLISTENER_DEFAULTROOM = -1000;

	public static final int EAXLISTENER_MINROOMHF = -10000;
	public static final int EAXLISTENER_MAXROOMHF = 0;
	public static final int EAXLISTENER_DEFAULTROOMHF = -100;

	public static final float EAXLISTENER_MINROOMROLLOFFFACTOR = 0.0f;
	public static final float EAXLISTENER_MAXROOMROLLOFFFACTOR = 10.0f;
	public static final float EAXLISTENER_DEFAULTROOMROLLOFFFACTOR = 0.0f;

	public static final float EAXLISTENER_MINDECAYTIME = 0.1f;
	public static final float EAXLISTENER_MAXDECAYTIME = 20.0f;
	public static final float EAXLISTENER_DEFAULTDECAYTIME = 1.49f;

	public static final float EAXLISTENER_MINDECAYHFRATIO = 0.1f;
	public static final float EAXLISTENER_MAXDECAYHFRATIO = 2.0f;
	public static final float EAXLISTENER_DEFAULTDECAYHFRATIO = 0.83f;

	public static final int EAXLISTENER_MINREFLECTIONS = -10000;
	public static final int EAXLISTENER_MAXREFLECTIONS = 1000;
	public static final int EAXLISTENER_DEFAULTREFLECTIONS = -2602;

	public static final float EAXLISTENER_MINREFLECTIONSDELAY = 0.0f;
	public static final float EAXLISTENER_MAXREFLECTIONSDELAY = 0.3f;
	public static final float EAXLISTENER_DEFAULTREFLECTIONSDELAY = 0.007f;

	public static final int EAXLISTENER_MINREVERB = -10000;
	public static final int EAXLISTENER_MAXREVERB = 2000;
	public static final int EAXLISTENER_DEFAULTREVERB = 200;

	public static final float EAXLISTENER_MINREVERBDELAY = 0.0f;
	public static final float EAXLISTENER_MAXREVERBDELAY = 0.1f;
	public static final float EAXLISTENER_DEFAULTREVERBDELAY = 0.011f;

	public static final int EAXLISTENER_MINENVIRONMENT = 0;
	public static final int EAXLISTENER_MAXENVIRONMENT =
		(EAX20.EAX_ENVIRONMENT_COUNT - 1);
	public static final int EAXLISTENER_DEFAULTENVIRONMENT =
		EAX20.EAX_ENVIRONMENT_GENERIC;

	public static final float EAXLISTENER_MINENVIRONMENTSIZE = 1.0f;
	public static final float EAXLISTENER_MAXENVIRONMENTSIZE = 100.0f;
	public static final float EAXLISTENER_DEFAULTENVIRONMENTSIZE = 7.5f;

	public static final float EAXLISTENER_MINENVIRONMENTDIFFUSION = 0.0f;
	public static final float EAXLISTENER_MAXENVIRONMENTDIFFUSION = 1.0f;
	public static final float EAXLISTENER_DEFAULTENVIRONMENTDIFFUSION = 1.0f;

	public static final float EAXLISTENER_MINAIRABSORPTIONHF = -100.0f;
	public static final float EAXLISTENER_MAXAIRABSORPTIONHF = 0.0f;
	public static final float EAXLISTENER_DEFAULTAIRABSORPTIONHF = -5.0f;

	public static final int EAXLISTENER_DEFAULTFLAGS =
		(EAXLISTENERFLAGS_DECAYTIMESCALE
			| EAXLISTENERFLAGS_REFLECTIONSSCALE
			| EAXLISTENERFLAGS_REFLECTIONSDELAYSCALE
			| EAXLISTENERFLAGS_REVERBSCALE
			| EAXLISTENERFLAGS_REVERBDELAYSCALE
			| EAXLISTENERFLAGS_DECAYHFLIMIT);
	
	// -------------------------------------------------------
		
	static {
		Sys.initialize();
		EAXLISTENERPROPERTIES_SIZE = sizeOfEaxListenerProperties();
		assignOffsets();
	}

	public EAXListenerProperties() {
		eaxListenerProperties =
			ByteBuffer.allocateDirect(EAXLISTENERPROPERTIES_SIZE);
		eaxListenerProperties.order(ByteOrder.nativeOrder());
	}
	
	/**
	 * Loads current EAX values
	 */
	public void loadEAXValues() {
		EAX20.eaxGet(
				EAX20.LISTENER_GUID,
				EAXLISTENER_ALLPARAMETERS,
				0,
				eaxListenerProperties,
				EAXLISTENERPROPERTIES_SIZE	);			
	}	

	/**
	 * Resets this buffer property to default values
	 */
	public void reset() {
		boolean commitValue = autoCommit;
		
		// disable autocommit
		autoCommit = false;
		
		// set values
		setRoom(EAXLISTENER_DEFAULTROOM);
		setRoomHF(EAXLISTENER_DEFAULTROOMHF);
		setRoomRolloffFactor(EAXLISTENER_DEFAULTROOMROLLOFFFACTOR);
		setDecayTime(EAXLISTENER_DEFAULTDECAYTIME);
		setDecayTimeHFRatio(EAXLISTENER_DEFAULTDECAYHFRATIO);
		setReflections(EAXLISTENER_DEFAULTREFLECTIONS);
		setReflectionsDelay(EAXLISTENER_DEFAULTREFLECTIONSDELAY);
		setReverb(EAXLISTENER_DEFAULTREVERB);
		setReverbDelay(EAXLISTENER_DEFAULTREVERBDELAY);
		setEnvironment(EAXLISTENER_DEFAULTENVIRONMENT);
		setEnvironmentSize(EAXLISTENER_DEFAULTENVIRONMENTSIZE);
		setEnvironmentDiffusion(EAXLISTENER_DEFAULTENVIRONMENTDIFFUSION);
		setAirAbsorptionFactor(EAXLISTENER_DEFAULTAIRABSORPTIONHF);
		setFlags(EAXLISTENER_DEFAULTFLAGS);
		
		// reset auto commit
		autoCommit = commitValue;
	}
	
	
	/**
	 * Commits any changes
	 */
	public void commit() {
		// call eaxSet with Listener guid, setting all parameters
		EAX20.eaxSet(
				EAX20.LISTENER_GUID, EAXLISTENER_ALLPARAMETERS | EAXLISTENER_IMMEDIATE, 
				0, eaxListenerProperties, EAXLISTENERPROPERTIES_SIZE);			
	}
	
	/**
	 * Tests whether auto commit is enabled or not
	 * 
	 * @return true if auto commit is inabled
	 */
	public boolean isAutoCommitEnabled() {
		return autoCommit;
	}
	
	/**
	 * Enabled or disables auto commit
	 * 
	 * @param enabled True to enable, false to disable
	 */
	public void setAutoCommit(boolean enabled) {
		autoCommit = enabled;
	}
	
	/**
	 * Performs auto commit, if enabled
	 */
	private final void autoCommit() {
		if(autoCommit) {
			commit();
		}
	}

	/**
	 * Retrieves the size of the containing ByteBuffer
	 */
	public int getSize() {
		return EAXLISTENERPROPERTIES_SIZE;
	}
	
	/**
	 * Returns a String representation of the EAXBufferProperties object
	 * 
	 * @return String representation of the EAXBufferProperties object
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("EAXListenerProperties [");

		sb.append("lRoom = ");
		sb.append(getRoom());
		sb.append(", ");
		
		sb.append("lRoomHF = ");
		sb.append(getRoomHF());
		sb.append(", ");
		
		sb.append("flRoomRolloffFactor = ");
		sb.append(getRoomRolloffFactor());
		sb.append(", ");
		
		sb.append("flDecayTime = ");
		sb.append(getDecayTime());
		sb.append(", ");
		
		sb.append("flDecayHFRatio = ");
		sb.append(getDecayTimeHFRatio());
		sb.append(", ");
		
		sb.append("lReflections = ");
		sb.append(getReflections());
		sb.append(", ");
		
		sb.append("flReflectionsDelay = ");
		sb.append(getReflectionsDelay());
		sb.append(", ");
		
		sb.append("lReverb = ");
		sb.append(getReverb());
		sb.append(", ");
		
		sb.append("flReverbDelay = ");
		sb.append(getReverbDelay());
		sb.append(", ");
		
		sb.append("dwEnvironment = ");
		sb.append(getEnvironment());
		sb.append(", ");
		
		sb.append("flEnvironmentSize = ");
		sb.append(getEnvironmentSize());
		sb.append(", ");
		
		sb.append("flEnvironmentDiffusion = ");
		sb.append(getEnvironmentDiffusion());
		sb.append(", ");
		
		sb.append("flAirAbsorptionHF = ");
		sb.append(getAirAbsorptionHF());
		sb.append(", ");
		
		sb.append("dwFlags = ");
		sb.append(getFlags());
		
		sb.append("]");
		return sb.toString();
	}	

	// Getters and Setters of struct
	// ==========================================================================

	/**
	 * Retireves the room effect level
	 *
	 * @return room effect level
	 */
	public int getRoom() {
		return eaxListenerProperties.getInt(room_offset);
	}

	/**
	 * Sets the room effect level
	 *
	 * @param room room effect level to set to
	 */
	public void setRoom(int room) {
		eaxListenerProperties.putInt(room_offset, room);
		autoCommit();
	}

	/**
	 * Retireves the room effect level at high frequencies
	 *
	 * @return room effect level at high frequencies
	 */
	public int getRoomHF() {
		return eaxListenerProperties.getInt(roomHF_offset);
	}

	/**
	 * Sets the room effect level at high frequencies
	 *
	 * @param roomHF room effect level at high frequencies to set to 
	 */
	public void setRoomHF(int roomHF) {
		eaxListenerProperties.putInt(roomHF_offset, roomHF);
		autoCommit();
	}

	/**
	 * Retireves the DS3D flRolloffFactor for room effect
	 *
	 * @return DS3D flRolloffFactor for room effect
	 */
	public float getRoomRolloffFactor() {
		return eaxListenerProperties.getFloat(roomRolloffFactor_offset);
	}

	/**
	 * Sets the DS3D flRolloffFactor for room effect
	 *
	 * @param roomRolloffFactor DS3D flRolloffFactor for room effect to set to
	 */
	public void setRoomRolloffFactor(float roomRolloffFactor) {
		eaxListenerProperties.putFloat(roomRolloffFactor_offset, roomRolloffFactor);
		autoCommit();
	}

	/**
	 * Retireves the reverberation decay time at low frequencies
	 *
	 * @return reverberation decay time at low frequencies
	 */
	public float getDecayTime() {
		return eaxListenerProperties.getFloat(decayTime_offset);
	}

	/**
	 * Sets the reverberation decay time at low frequencies
	 *
	 * @param decayTime reverberation decay time at low frequencies to set to
	 */
	public void setDecayTime(float decayTime) {
		eaxListenerProperties.putFloat(decayTime_offset, decayTime);
		autoCommit();
	}

	/**
	 * Retireves the high-frequency to low-frequency decay time ratio
	 *
	 * @return high-frequency to low-frequency decay time ratio
	 */
	public float getDecayTimeHFRatio() {
		return eaxListenerProperties.getFloat(decayHFRatio_offset);
	}

	/**
	 * Sets the high-frequency to low-frequency decay time ratio
	 *
	 * @param decayTimeHFRatio high-frequency to low-frequency decay time ratio to set to
	 */
	public void setDecayTimeHFRatio(float decayTimeHFRatio) {
		eaxListenerProperties.putFloat(decayHFRatio_offset, decayTimeHFRatio);
		autoCommit();
	}

	/**
	 * Retireves the early reflections level relative to room effect
	 *
	 * @return early reflections level relative to room effect
	 */
	public int getReflections() {
		return eaxListenerProperties.getInt(reflections_offset);
	}

	/**
	 * Sets the early reflections level relative to room effect
	 *
	 * @param reflections early reflections level relative to room effect to set to
	 */
	public void setReflections(int reflections) {
		eaxListenerProperties.putInt(reflections_offset, reflections);
		autoCommit();
	}

	/**
	 * Retireves the initial reflection delay time
	 *
	 * @return initial reflection delay time
	 */
	public float getReflectionsDelay() {
		return eaxListenerProperties.getFloat(reflectionsDelay_offset);
	}

	/**
	 * Sets the initial reflection delay time
	 *
	 * @param reflectionsDelay initial reflection delay time to set to
	 */
	public void setReflectionsDelay(float reflectionsDelay) {
		eaxListenerProperties.putFloat(reflectionsDelay_offset, reflectionsDelay);
		autoCommit();
	}

	/**
	 * Retireves the late reverberation level relative to room effect
	 *
	 * @return late reverberation level relative to room effect
	 */
	public int getReverb() {
		return eaxListenerProperties.getInt(reverb_offset);
	}

	/**
	 * Sets the late reverberation level relative to room effect
	 *
	 * @param reverb late reverberation level relative to room effect to set to
	 */
	public void setReverb(int reverb) {
		eaxListenerProperties.putInt(reverb_offset, reverb);
		autoCommit();
	}

	/**
	 * Retireves the late reverberation delay time relative to initial reflection
	 *
	 * @return late reverberation delay time relative to initial reflection
	 */
	public float getReverbDelay() {
		return eaxListenerProperties.getFloat(reverbDelay_offset);
	}

	/**
	 * Sets the late reverberation delay time relative to initial reflection
	 *
	 * @param reverbDelay late reverberation delay time relative to initial reflection
	 */
	public void setReverbDelay(float reverbDelay) {
		eaxListenerProperties.putFloat(reverbDelay_offset, reverbDelay);
		autoCommit();
	}

	/**
	 * Retireves the listener properties
	 *
	 * @return listener properties
	 */
	public int getEnvironment() {
		return eaxListenerProperties.getInt(environment_offset);
	}

	/**
	 * Sets the listener properties
	 *
	 * @param environment listener properties to set to
	 */
	public void setEnvironment(int environment) {
		eaxListenerProperties.putInt(environment_offset, environment);
		
		// we need to handle environment differently than all other
		// values on auto commit, since it cannot be single handely set
		// using ALLPARAMETERS
		//
		// To set the environment specifically we need to pass
		// only the environment value and its size. Also pass the
		// EAXLISTENER_ENVIRONMENT value (and since we're committing IMMEDIATE too)
		if (autoCommit) {
			EAX20.eaxSet(
					EAX20.LISTENER_GUID, EAXLISTENER_ENVIRONMENT | EAXLISTENER_IMMEDIATE, 
					0, eaxListenerProperties.position(environment_offset), 4);
			
			// rewind buffer
			eaxListenerProperties.rewind();
		}
	}

	/**
	 * Retireves the environment size in meters
	 *
	 * @return environment size in meters
	 */
	public float getEnvironmentSize() {
		return eaxListenerProperties.getFloat(environmentSize_offset);
	}

	/**
	 * Sets the environment size in meters
	 *
	 * @param environmentSize environment size in meters to set to
	 */
	public void setEnvironmentSize(float environmentSize) {
		eaxListenerProperties.putFloat(environmentSize_offset, environmentSize);
		
		// we need to handle environment size differently than all other
		// values on auto commit, since it cannot be single handely set
		// using ALLPARAMETERS
		//
		// To set the environment size specifically we need to pass
		// only the environment size value and its size. Also pass the
		// EAXLISTENER_ENVIRONMENTSIZE value (and since we're committing IMMEDIATE too)
		if (autoCommit) {
			EAX20.eaxSet(
					EAX20.LISTENER_GUID, EAXLISTENER_ENVIRONMENTSIZE | EAXLISTENER_IMMEDIATE, 
					0, eaxListenerProperties.position(environmentSize_offset), 4);
			
			// rewind buffer
			eaxListenerProperties.rewind();
		}
	}

	/**
	 * Retireves the environment diffusion
	 *
	 * @return environment diffusion
	 */
	public float getEnvironmentDiffusion() {
		return eaxListenerProperties.getFloat(environmentDiffusion_offset);
	}

	/**
	 * Sets the environment diffusion
	 *
	 * @param environmentDiffusion environment diffusion to set to
	 */
	public void setEnvironmentDiffusion(float environmentDiffusion) {
		eaxListenerProperties.putFloat(environmentDiffusion_offset, environmentDiffusion);
		autoCommit();
	}

	/**
	 * Retireves the change in level per meter at 5 kHz
	 *
	 * @return change in level per meter at 5 kHz
	 */
	public float getAirAbsorptionHF() {
		return eaxListenerProperties.getFloat(airAbsorptionHF_offset);
	}

	/**
	 * Sets the change in level per meter at 5 kHz
	 *
	 * @param airAbsorptionHF change in level per meter at 5 kHz to set to
	 */
	public void setAirAbsorptionFactor(float airAbsorptionHF) {
		eaxListenerProperties.putFloat(airAbsorptionHF_offset, airAbsorptionHF);
		autoCommit();
	}

	/**
	 * Retireves the modifier for behavior of properties
	 *
	 * @return modifier for behavior of properties
	 */
	public int getFlags() {
		return eaxListenerProperties.getInt(flags_offset);
	}

	/**
	 * Sets the	modifier for behavior of properties
	 *
	 * @param flags modifier for behavior of properties to set to
	 */
	public void setFlags(int flags) {
		eaxListenerProperties.putInt(flags_offset, flags);
		autoCommit();
	}

	// --------------------------------------------------------------------------

	/**
	 * Retrieves the size of the EAXLISTENERPROPERTIES
	 */
	protected static native int sizeOfEaxListenerProperties();

	/**
	 * Sets the offsets to the fields
	 */
	protected static native void assignOffsets();
}
