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
import org.lwjgl.BufferUtils;

/**
 * $Id$
 *
 * This class encapsultaes the EAXBUFFERPROPERTIES struct. Since longs 
 * are 64 bit in Java and "typically" 32 on other platforms, we cheat by reading an
 * int when reading a long field.
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class EAXBufferProperties {
	
	/** Whether auto commit has been anabled */
	private boolean autoCommit = true;
	
	/** Current source being operated on	*/
	private int currentSource = -1;

	/** ByteBuffer representing EAXBUFFERPROPERTIES */
	protected ByteBuffer eaxBufferProperties;

	/** size needed by ByteBuffer to contain EAXBUFFERPROPERTIES */
	protected static int EAXBUFFERPROPERTIES_SIZE;
	
	// EAX values and offsets
	// ========================================================

	/** direct path level offset */
	protected static int direct_offset;

	/** direct path level at high frequencies offset */
	protected static int directHF_offset;

	/** room effect level offset */
	protected static int room_offset;

	/** room effect level at high frequencies offset */
	protected static int roomHF_offset;

	/** like DS3D flRolloffFactor but for room effect offset */
	protected static int roomRolloffFactor_offset;

	/** main obstruction control (attenuation at high frequencies) offset */
	protected static int obstruction_offset;

	/** obstruction low-frequency level re. main control offset */
	protected static int obstructionLFRatio_offset;

	/** main occlusion control (attenuation at high frequencies) offset */
	protected static int occlusion_offset;

	/** occlusion low-frequency level re. main control offset */
	protected static int occlusionLFRatio_offset;

	/** occlusion room effect level re. main control offset */
	protected static int occlusionRoomRatio_offset;

	/** outside sound cone level at high frequencies offset */
	protected static int outsideVolumeHF_offset;

	/** multiplies DSPROPERTY_EAXLISTENER_AIRABSORPTIONHF offset */
	protected static int airAbsorptionFactor_offset;

	/** modifies the behavior of properties offset */
	protected static int flags_offset;

	public static final int EAXBUFFER_NONE = 0;
	public static final int EAXBUFFER_ALLPARAMETERS = 1;
	public static final int EAXBUFFER_DIRECT = 2;
	public static final int EAXBUFFER_DIRECTHF = 3;
	public static final int EAXBUFFER_ROOM = 4;
	public static final int EAXBUFFER_ROOMHF = 5;
	public static final int EAXBUFFER_ROOMROLLOFFFACTOR = 6;
	public static final int EAXBUFFER_OBSTRUCTION = 7;
	public static final int EAXBUFFER_OBSTRUCTIONLFRATIO = 8;
	public static final int EAXBUFFER_OCCLUSION = 9;
	public static final int EAXBUFFER_OCCLUSIONLFRATIO = 10;
	public static final int EAXBUFFER_OCCLUSIONROOMRATIO = 11;
	public static final int EAXBUFFER_OUTSIDEVOLUMEHF = 12;
	public static final int EAXBUFFER_AIRABSORPTIONFACTOR = 13;
	public static final int EAXBUFFER_FLAGS = 14;

	/** changes take effect immediately */
	public static final int EAXBUFFER_IMMEDIATE = 0x00000000;

	/** changes take effect later */
	public static final int EAXBUFFER_DEFERRED = 0x80000000;
	public static final int EAXBUFFER_COMMITDEFERREDSETTINGS =
		(EAXBUFFER_NONE | EAXBUFFER_IMMEDIATE);

	/** affects DSPROPERTY_EAXBUFFER_DIRECTHF */
	public static final int EAXBUFFER_FLAGS_DIRECTHFAUTO = 0x00000001;

	/** affects DSPROPERTY_EAXBUFFER_ROOM */
	public static final int EAXBUFFER_FLAGS_ROOMAUTO = 0x00000002;

	/** affects DSPROPERTY_EAXBUFFER_ROOMHF */
	public static final int EAXBUFFER_FLAGS_ROOMHFAUTO = 0x00000004;

	/** reserved future use */
	public static final int EAXBUFFER_FLAGS_RESERVED = 0xFFFFFFF8;

	// property ranges and defaults:

	public static final int EAXBUFFER_MINDIRECT = -10000;
	public static final int EAXBUFFER_MAXDIRECT = 1000;
	public static final int EAXBUFFER_DEFAULTDIRECT = 0;

	public static final int EAXBUFFER_MINDIRECTHF = -10000;
	public static final int EAXBUFFER_MAXDIRECTHF = 0;
	public static final int EAXBUFFER_DEFAULTDIRECTHF = 0;

	public static final int EAXBUFFER_MINROOM = -10000;
	public static final int EAXBUFFER_MAXROOM = 1000;
	public static final int EAXBUFFER_DEFAULTROOM = 0;

	public static final int EAXBUFFER_MINROOMHF = -10000;
	public static final int EAXBUFFER_MAXROOMHF = 0;
	public static final int EAXBUFFER_DEFAULTROOMHF = 0;

	public static final float EAXBUFFER_MINROOMROLLOFFFACTOR = 0.0f;
	public static final float EAXBUFFER_MAXROOMROLLOFFFACTOR = 10.f;
	public static final float EAXBUFFER_DEFAULTROOMROLLOFFFACTOR = 0.0f;

	public static final int EAXBUFFER_MINOBSTRUCTION = -10000;
	public static final int EAXBUFFER_MAXOBSTRUCTION = 0;
	public static final int EAXBUFFER_DEFAULTOBSTRUCTION = 0;

	public static final float EAXBUFFER_MINOBSTRUCTIONLFRATIO = 0.0f;
	public static final float EAXBUFFER_MAXOBSTRUCTIONLFRATIO = 1.0f;
	public static final float EAXBUFFER_DEFAULTOBSTRUCTIONLFRATIO = 0.0f;

	public static final int EAXBUFFER_MINOCCLUSION = -10000;
	public static final int EAXBUFFER_MAXOCCLUSION = 0;
	public static final int EAXBUFFER_DEFAULTOCCLUSION = 0;

	public static final float EAXBUFFER_MINOCCLUSIONLFRATIO = 0.0f;
	public static final float EAXBUFFER_MAXOCCLUSIONLFRATIO = 1.0f;
	public static final float EAXBUFFER_DEFAULTOCCLUSIONLFRATIO = 0.25f;

	public static final float EAXBUFFER_MINOCCLUSIONROOMRATIO = 0.0f;
	public static final float EAXBUFFER_MAXOCCLUSIONROOMRATIO = 10.0f;
	public static final float EAXBUFFER_DEFAULTOCCLUSIONROOMRATIO = 0.5f;

	public static final int EAXBUFFER_MINOUTSIDEVOLUMEHF = -10000;
	public static final int EAXBUFFER_MAXOUTSIDEVOLUMEHF = 0;
	public static final int EAXBUFFER_DEFAULTOUTSIDEVOLUMEHF = 0;

	public static final float EAXBUFFER_MINAIRABSORPTIONFACTOR = 0.0f;
	public static final float EAXBUFFER_MAXAIRABSORPTIONFACTOR = 10.0f;
	public static final float EAXBUFFER_DEFAULTAIRABSORPTIONFACTOR = 1.0f;

	public static final int EAXBUFFER_DEFAULTFLAGS =
		(EAXBUFFER_FLAGS_DIRECTHFAUTO
			| EAXBUFFER_FLAGS_ROOMAUTO
			| EAXBUFFER_FLAGS_ROOMHFAUTO);
	
	// -------------------------------------------------------

	static {
		Sys.initialize();
		EAXBUFFERPROPERTIES_SIZE = sizeOfEaxBufferProperties();
		assignOffsets();
	}
	
	/**
	 * Creates a new EAXBufferProperties object 
	 */
	public EAXBufferProperties() {
		eaxBufferProperties = BufferUtils.createByteBuffer(EAXBUFFERPROPERTIES_SIZE);
		eaxBufferProperties.order(ByteOrder.nativeOrder());
	}
	
	/**
	 * Sets the current source on which to perform effects
	 * 
	 * @param source Source on which to perform effects
	 */
	public void setCurrentSource(int source) {
		currentSource = source;
	}
	
	/**
	 * Retrieves the current source
	 * 
	 * @return Source on which effects are being performed
	 */
	public int getCurrentSource() {
		return currentSource;
	}
	
	/**
	 * Loads current EAX values from current source
	 */
	public void loadEAXValues() {
		EAX20.eaxGet(
				EAX20.BUFFER_GUID,
				EAXBUFFER_ALLPARAMETERS,
				currentSource,
				eaxBufferProperties,
				EAXBUFFERPROPERTIES_SIZE);			
	}
	
	/**
	 * Resets this buffer property to default values
	 */
	public void reset() {
		boolean commitValue = autoCommit;
		
		// disable autocommit
		autoCommit = false;
		
		// set values
		setDirect(EAXBUFFER_DEFAULTDIRECT);
		setDirectHF(EAXBUFFER_DEFAULTDIRECTHF);
		setRoom(EAXBUFFER_DEFAULTROOM);
		setRoomHF(EAXBUFFER_DEFAULTROOMHF);
		setRoomRolloffFactor(EAXBUFFER_DEFAULTROOMROLLOFFFACTOR);
		setObstruction(EAXBUFFER_DEFAULTOBSTRUCTION);
		setObstructionLFRatio(EAXBUFFER_DEFAULTOBSTRUCTIONLFRATIO);
		setOcclusion(EAXBUFFER_DEFAULTOCCLUSION);
		setOcclusionLFRatio(EAXBUFFER_DEFAULTOCCLUSIONLFRATIO);
		setOcclusionRoomRatio(EAXBUFFER_DEFAULTOCCLUSIONROOMRATIO);
		setOutsideVolumeHF(EAXBUFFER_DEFAULTOUTSIDEVOLUMEHF);
		setAirAbsorptionFactor(EAXBUFFER_DEFAULTAIRABSORPTIONFACTOR);
		setFlags(EAXBUFFER_DEFAULTFLAGS);
		
		// reset auto commit
		autoCommit = commitValue;
	}
	
	
	/**
	 * Commits any changes
	 */
	public void commit() {
		// call eaxSet with Buffer guid, setting all parameters
		EAX20.eaxSet(
				EAX20.BUFFER_GUID, EAXBUFFER_ALLPARAMETERS, 
				currentSource, eaxBufferProperties, EAXBUFFERPROPERTIES_SIZE);			
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
	public static int getSize() {
		return EAXBUFFERPROPERTIES_SIZE;
	}
	
	/**
	 * Returns a String representation of the EAXBufferProperties object
	 * 
	 * @return String representation of the EAXBufferProperties object
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("EAXBufferProperties [");

		sb.append("lDirect = ");
		sb.append(getDirect());
		sb.append(", ");
		
		sb.append("lDirectHF = ");
		sb.append(getDirectHF());
		sb.append(", ");
		
		sb.append("lRoom = ");
		sb.append(getRoom());
		sb.append(", ");
		
		sb.append("lRoomHF = ");
		sb.append(getRoomHF());
		sb.append(", ");
		
		sb.append("flRoomRolloffFactor = ");
		sb.append(getRoomRolloffFactor());
		sb.append(", ");
		
		sb.append("lObstruction = ");
		sb.append(getObstruction());
		sb.append(", ");
		
		sb.append("flObstructionLFRatio = ");
		sb.append(getObstructionLFRatio());
		sb.append(", ");
		
		sb.append("lOcclusion = ");
		sb.append(getOcclusion());
		sb.append(", ");
		
		sb.append("flOcclusionLFRatio = ");
		sb.append(getOcclusionLFRatio());
		sb.append(", ");
		
		sb.append("flOcclusionRoomRatio = ");
		sb.append(getOcclusionRoomRatio());
		sb.append(", ");
		
		sb.append("lOutsideVolumeHF = ");
		sb.append(getOutsideVolumeHF());
		sb.append(", ");
		
		sb.append("flAirAbsorptionFactor = ");
		sb.append(getAirAbsorptionFactor());
		sb.append(", ");
		
		sb.append("dwFlags = ");
		sb.append(getFlags());
		
		sb.append("]");
		return sb.toString();
	}	

	// Getters and Setters of struct
	// ==========================================================================
	
	/**
	 * Retireves the direct path level
	 *
	 * @return direct path level
	 */
	public int getDirect() {
		return eaxBufferProperties.getInt(direct_offset);
	}

	/**
	 * Sets the direct path level
	 *
	 * @param direct direct path level to set to
	 */
	public void setDirect(int direct) {
		eaxBufferProperties.putInt(direct_offset, direct);
		autoCommit();
	}

	/**
	 * Retireves the direct path level at high frequencies
	 *
	 * @return direct path level at high frequencies
	 */
	public int getDirectHF() {
		return eaxBufferProperties.getInt(directHF_offset);
	}

	/**
	 * Sets the direct path level at high frequencies
	 *
	 * @param directHF direct path level at high frequencies to set to
	 */
	public void setDirectHF(int directHF) {
		eaxBufferProperties.putInt(directHF_offset, directHF);
		autoCommit();
	}

	/**
	 * Retireves the room effect level
	 *
	 * @return room effect level
	 */
	public int getRoom() {
		return eaxBufferProperties.getInt(room_offset);
	}

	/**
	 * Sets the room effect level
	 *
	 * @param room room effect level to set to
	 */
	public void setRoom(int room) {
		eaxBufferProperties.putInt(room_offset, room);
		autoCommit();
	}

	/**
	 * Retireves the room effect level at high frequencies
	 *
	 * @return room effect level at high frequencies
	 */
	public int getRoomHF() {
		return eaxBufferProperties.getInt(roomHF_offset);
	}

	/**
	 * Sets the room effect level at high frequencies
	 *
	 * @param roomHF room effect level at high frequencies to set to 
	 */
	public void setRoomHF(int roomHF) {
		eaxBufferProperties.putInt(roomHF_offset, roomHF);
		autoCommit();
	}

	/**
	 * Retireves the DS3D flRolloffFactor for room effect
	 *
	 * @return DS3D flRolloffFactor for room effect
	 */
	public float getRoomRolloffFactor() {
		return eaxBufferProperties.getFloat(roomRolloffFactor_offset);
	}

	/**
	 * Sets the DS3D flRolloffFactor for room effect
	 *
	 * @param roomRolloffFactor DS3D flRolloffFactor for room effect to set to
	 */
	public void setRoomRolloffFactor(float roomRolloffFactor) {
		eaxBufferProperties.putFloat(roomRolloffFactor_offset, roomRolloffFactor);
		autoCommit();
	}

	/**
	 * Retireves the main obstruction control (attenuation at high frequencies)
	 *
	 * @return main obstruction control (attenuation at high frequencies)
	 */
	public int getObstruction() {
		return eaxBufferProperties.getInt(obstruction_offset);
	}

	/**
	 * Sets the main obstruction control (attenuation at high frequencies)
	 *
	 * @param obstruction main obstruction control (attenuation at high frequencies) to set to
	 */
	public void setObstruction(int obstruction) {
		eaxBufferProperties.putInt(obstruction_offset, obstruction);
		autoCommit();
	}

	/**
	 * Retireves the obstruction low-frequency level re. main control
	 *
	 * @return obstruction low-frequency level re. main control
	 */
	public float getObstructionLFRatio() {
		return eaxBufferProperties.getFloat(obstructionLFRatio_offset);
	}

	/**
	 * Sets the obstruction low-frequency level re. main control
	 *
	 * @param obstructionLFRatio obstruction low-frequency level re. main control to set to
	 */
	public void setObstructionLFRatio(float obstructionLFRatio) {
		eaxBufferProperties.putFloat(obstructionLFRatio_offset, obstructionLFRatio);
		autoCommit();
	}

	/**
	 * Retireves the main occlusion control (attenuation at high frequencies)
	 *
	 * @return main occlusion control (attenuation at high frequencies)
	 */
	public int getOcclusion() {
		return eaxBufferProperties.getInt(occlusion_offset);
	}

	/**
	 * Sets the main occlusion control (attenuation at high frequencies)
	 *
	 * @param occlusion main occlusion control (attenuation at high frequencies) to set to
	 */
	public void setOcclusion(int occlusion) {
		eaxBufferProperties.putInt(occlusion_offset, occlusion);
		autoCommit();
	}

	/**
	 * Retireves the occlusion low-frequency level re. main control
	 *
	 * @return occlusion low-frequency level re. main control
	 */
	public float getOcclusionLFRatio() {
		return eaxBufferProperties.getFloat(occlusionLFRatio_offset);
	}

	/**
	 * Sets the occlusion low-frequency level re. main control
	 *
	 * @param occlusionLFRatio occlusion low-frequency level re. main control to set to
	 */
	public void setOcclusionLFRatio(float occlusionLFRatio) {
		eaxBufferProperties.putFloat(occlusionLFRatio_offset, occlusionLFRatio);
		autoCommit();
	}

	/**
	 * Retireves the occlusion room effect level re. main control
	 *
	 * @return occlusion room effect level re. main control
	 */
	public float getOcclusionRoomRatio() {
		return eaxBufferProperties.getFloat(occlusionRoomRatio_offset);
	}

	/**
	 * Sets the OcclusionRoomRatio
	 *
	 * @param occlusionRoomRatio OcclusionRoomRatio to set to
	 */
	public void setOcclusionRoomRatio(float occlusionRoomRatio) {
		eaxBufferProperties.putFloat(occlusionRoomRatio_offset, occlusionRoomRatio);
		autoCommit();
	}

	/**
	 * Retireves the OutsideVolumeHF
	 *
	 * @return OutsideVolumeHF
	 */
	public int getOutsideVolumeHF() {
		return eaxBufferProperties.getInt(outsideVolumeHF_offset);
	}

	/**
	 * Sets the OutsideVolumeHF
	 *
	 * @param outsideVolumeHF OutsideVolumeHF to set to
	 */
	public void setOutsideVolumeHF(int outsideVolumeHF) {
		eaxBufferProperties.putInt(outsideVolumeHF_offset, outsideVolumeHF);
		autoCommit();
	}

	/**
	 * Retireves the multiplier for DSPROPERTY_EAXLISTENER_AIRABSORPTIONHF
	 *
	 * @return multiplier for DSPROPERTY_EAXLISTENER_AIRABSORPTIONHF
	 */
	public float getAirAbsorptionFactor() {
		return eaxBufferProperties.getFloat(airAbsorptionFactor_offset);
	}

	/**
	 * Sets the multiplier for DSPROPERTY_EAXLISTENER_AIRABSORPTIONHF
	 *
	 * @param airAbsorptionFactor multiplier for DSPROPERTY_EAXLISTENER_AIRABSORPTIONHF to set to
	 */
	public void setAirAbsorptionFactor(float airAbsorptionFactor) {
		eaxBufferProperties.putFloat(airAbsorptionFactor_offset, airAbsorptionFactor);
		autoCommit();
	}

	/**
	 * Retireves the modifier for behavior of properties
	 *
	 * @return modifier for behavior of properties
	 */
	public int getFlags() {
		return eaxBufferProperties.getInt(flags_offset);
	}

	/**
	 * Sets the	modifier for behavior of properties
	 *
	 * @param flags modifier for behavior of properties to set to
	 */
	public void setFlags(int flags) {
		eaxBufferProperties.putInt(flags_offset, flags);
		autoCommit();
	}
	
	// --------------------------------------------------------------------------
	
	/**
	 * Retrieves the size of the EAXBUFFERPROPERTIES
	 */
	protected static native int sizeOfEaxBufferProperties();

	/**
	 * Sets the offsets to the fields
	 */
	protected static native void assignOffsets();	
}
