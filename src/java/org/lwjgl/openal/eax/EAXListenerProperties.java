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
    
    public static final int NONE                                = 0;
    public static final int ALLPARAMETERS                       = 1;
    public static final int ROOM                                = 2;
    public static final int ROOMHF                              = 3;
    public static final int ROOMROLLOFFFACTOR                   = 4;
    public static final int DECAYTIME                           = 5;
    public static final int DECAYHFRATIO                        = 6;
    public static final int REFLECTIONS                         = 7;
    public static final int REFLECTIONSDELAY                    = 8;
    public static final int REVERB                              = 9;
    public static final int REVERBDELAY                         = 10;
    public static final int ENVIRONMENT                         = 11;
    public static final int ENVIRONMENTSIZE                     = 12;
    public static final int ENVIRONMENTDIFFUSION                = 13;
    public static final int AIRABSORPTIONHF                     = 14;
    public static final int FLAGS                               = 15;
                                                                
    /** changes take effect immediately */                      
    public static final int IMMEDIATE                           = 0x00000000;
                                                                
    /** changes take effect later */                            
    public static final int DEFERRED                            = 0x80000000;
    
    public static final int COMMITDEFERREDSETTINGS              = (NONE | IMMEDIATE);    
    
    /** reverberation decay time */
    public static final int FLAGS_DECAYTIMESCALE                = 0x00000001;
    
    /** reflection level */
    public static final int FLAGS_REFLECTIONSSCALE              = 0x00000002;
    
    /** initial reflection delay time */
    public static final int FLAGS_REFLECTIONSDELAYSCALE         = 0x00000004;

    /** reflections level */
    public static final int FLAGS_REVERBSCALE                   = 0x00000008;

    /** late reverberation delay time */
    public static final int FLAGS_REVERBDELAYSCALE              = 0x00000010;

    /** This flag limits high-frequency decay time according to air absorption. */
    public static final int FLAGS_DECAYHFLIMIT                  = 0x00000020;

    /** reserved future use */
    public static final int FLAGS_RESERVED                      = 0xFFFFFFC0;

    // property ranges and defaults:
    public static final int MINROOM                             = -10000;
    public static final int MAXROOM                             = 0;
    public static final int DEFAULTROOM                         = -1000;
                                                                
    public static final int MINROOMHF                           = -10000;
    public static final int MAXROOMHF                           = 0;
    public static final int DEFAULTROOMHF                       = -100;
                                                                
    public static final float MINROOMROLLOFFFACTOR              = 0.0f;
    public static final float MAXROOMROLLOFFFACTOR              = 10.0f;
    public static final float DEFAULTROOMROLLOFFFACTOR          = 0.0f;
                                                                
    public static final float MINDECAYTIME                      = 0.1f;
    public static final float MAXDECAYTIME                      = 20.0f;
    public static final float DEFAULTDECAYTIME                  = 1.49f;
                                                                
    public static final float MINDECAYHFRATIO                   = 0.1f;
    public static final float MAXDECAYHFRATIO                   = 2.0f;
    public static final float DEFAULTDECAYHFRATIO               = 0.83f;
                                                                
    public static final int MINREFLECTIONS                      = -10000;
    public static final int MAXREFLECTIONS                      = 1000;
    public static final int DEFAULTREFLECTIONS                  = -2602;
                                                                
    public static final float MINREFLECTIONSDELAY               = 0.0f;
    public static final float MAXREFLECTIONSDELAY               = 0.3f;
    public static final float DEFAULTREFLECTIONSDELAY           = 0.007f;
                                                                
    public static final int MINREVERB                           = -10000;
    public static final int MAXREVERB                           = 2000;
    public static final int DEFAULTREVERB                       = 200;
                                                                
    public static final float MINREVERBDELAY                    = 0.0f;
    public static final float MAXREVERBDELAY                    = 0.1f;
    public static final float DEFAULTREVERBDELAY                = 0.011f;
                                                                
    public static final int MINENVIRONMENT                      = 0;
    public static final int MAXENVIRONMENT                      = (EAX.ENVIRONMENT_COUNT-1);
    public static final int DEFAULTENVIRONMENT                  = EAX.ENVIRONMENT_GENERIC;
                                                                
    public static final float MINENVIRONMENTSIZE                = 1.0f;
    public static final float MAXENVIRONMENTSIZE                = 100.0f;
    public static final float DEFAULTENVIRONMENTSIZE            = 7.5f;
                                                                
    public static final float MINENVIRONMENTDIFFUSION           = 0.0f;
    public static final float MAXENVIRONMENTDIFFUSION           = 1.0f;
    public static final float DEFAULTENVIRONMENTDIFFUSION       = 1.0f;
                                                                
    public static final float MINAIRABSORPTIONHF                = -100.0f;
    public static final float MAXAIRABSORPTIONHF                = 0.0f;
    public static final float DEFAULTAIRABSORPTIONHF            = -5.0f;
                                                                
    public static final int DEFAULTFLAGS                        = (FLAGS_DECAYTIMESCALE |
                                                                    FLAGS_REFLECTIONSSCALE |
                                                                    FLAGS_REFLECTIONSDELAYSCALE |
                                                                    FLAGS_REVERBSCALE |
                                                                    FLAGS_REVERBDELAYSCALE |
                                                                    FLAGS_DECAYHFLIMIT);        

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
     * @param room room effect level at high frequencies to set to 
     */
    public void setRoomHF(int roomHF) {
        eaxListenerProperties.putInt(roomHF_offset, roomHF);
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
    public int getFlags() {
        return eaxListenerProperties.getInt(flags_offset);
    }
    
    /**
     * Sets the  modifier for behavior of properties
     *
     * @param flags modifier for behavior of properties to set to
     */
    public void setFlags(int flags) {
        eaxListenerProperties.putInt(flags_offset, flags);
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