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
package org.lwjgl.openal.eax;

import org.lwjgl.Sys;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * $Id$
 *
 * This class encapsultaes the EAXLISTENERPROPERTIES struct
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class EAXListenerProperties {
    
    /** ByteBuffer representing EAXLISTENERPROPERTIES */
    protected ByteBuffer eaxListenerProperties;
    
    /** size needed by ByteBuffer to contain EAXLISTENERPROPERTIES */
    protected static int EAXLISTENERPROPERTIES_SIZE;
    
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
    
    static {
        System.loadLibrary(org.lwjgl.Sys.getLibraryName());        
        EAXLISTENERPROPERTIES_SIZE = sizeOfEaxListenerProperties();
        assignOffsets();
    }
    
    /** 
     * Creates a new instance of EAXBufferProperties 
     */
    public EAXListenerProperties() {
        eaxListenerProperties = ByteBuffer.allocateDirect(EAXLISTENERPROPERTIES_SIZE);
        eaxListenerProperties.order(ByteOrder.nativeOrder());
    }
    
    /**
     * Retireves the room effect level
     *
     * @return room effect level
     */
    public long getRoom() {
        return eaxListenerProperties.getLong(room_offset);
    }
    
    /**
     * Sets the room effect level
     *
     * @param room room effect level to set to
     */
    public void setRoom(long room) {
        eaxListenerProperties.putLong(room_offset, room);
    }
    
    /**
     * Retireves the room effect level at high frequencies
     *
     * @return room effect level at high frequencies
     */
    public long getRoomHF() {
        return eaxListenerProperties.getLong(roomHF_offset);
    }
    
    /**
     * Sets the room effect level at high frequencies
     *
     * @param room room effect level at high frequencies to set to 
     */
    public void setRoomHF(long roomHF) {
        eaxListenerProperties.putLong(roomHF_offset, roomHF);
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
    }
    
    /**
     * Retireves the early reflections level relative to room effect
     *
     * @return early reflections level relative to room effect
     */
    public long getReflections() {
        return eaxListenerProperties.getLong(reflections_offset);
    }
    
    /**
     * Sets the early reflections level relative to room effect
     *
     * @param reflections early reflections level relative to room effect to set to
     */
    public void setReflections(long reflections) {
        eaxListenerProperties.putLong(reflections_offset, reflections);
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
    }
    
    /**
     * Retireves the late reverberation level relative to room effect
     *
     * @return late reverberation level relative to room effect
     */
    public long getReverb() {
        return eaxListenerProperties.getLong(reverb_offset);
    }
    
    /**
     * Sets the late reverberation level relative to room effect
     *
     * @param reverb late reverberation level relative to room effect to set to
     */
    public void setReverb(long reverb) {
        eaxListenerProperties.putLong(reverb_offset, reverb);
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
    }
    
    /**
     * Retireves the listener properties
     *
     * @return listener properties
     */
    public long getEnvironment() {
        return eaxListenerProperties.getLong(environment_offset);
    }
    
    /**
     * Sets the listener properties
     *
     * @param environment listener properties to set to
     */
    public void setEnvironment(long environment) {
        eaxListenerProperties.putLong(environment_offset, environment);
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
    }
    
    /**
     * Retireves the modifier for behavior of properties
     *
     * @return modifier for behavior of properties
     */
    public long getFlags() {
        return eaxListenerProperties.getLong(flags_offset);
    }
    
    /**
     * Sets the  modifier for behavior of properties
     *
     * @param flags modifier for behavior of properties to set to
     */
    public void setFlags(long flags) {
        eaxListenerProperties.putLong(flags_offset, flags);
    }
    
    /**
     * Retrieves the address of this EAXBufferProperties
     */
    public int getAddress() {
        return Sys.getDirectBufferAddress(eaxListenerProperties);
    }
    
    /**
     * Retrieves the size of the containing ByteBuffer
     */
    public int getSize() {
        return EAXLISTENERPROPERTIES_SIZE;
    }    
    
    /**
     * Retrieves the size of the EAXLISTENERPROPERTIES
     */
    protected static native int sizeOfEaxListenerProperties();
    
    /**
     * Sets the offsets to the fields
     */
    protected static native void assignOffsets();
}