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
import java.lang.reflect.*;

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
    
    /** ByteBuffer representing EAXBUFFERPROPERTIES */
    protected ByteBuffer eaxBufferProperties;
    
    /** size needed by ByteBuffer to contain EAXBUFFERPROPERTIES */
    protected static int EAXBUFFERPROPERTIES_SIZE;
    
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
    
    public static final int NONE                                = 0;
    public static final int ALLPARAMETERS                       = 1;
    public static final int DIRECT                              = 2;
    public static final int DIRECTHF                            = 3;
    public static final int ROOM                                = 4;
    public static final int ROOMHF                              = 5;
    public static final int ROOMROLLOFFFACTOR                   = 6;
    public static final int OBSTRUCTION                         = 7;
    public static final int OBSTRUCTIONLFRATIO                  = 8;
    public static final int OCCLUSION                           = 9;
    public static final int OCCLUSIONLFRATIO                    = 10;
    public static final int OCCLUSIONROOMRATIO                  = 11;
    public static final int OUTSIDEVOLUMEHF                     = 12;
    public static final int AIRABSORPTIONFACTOR                 = 13;
    public static final int FLAGS                               = 14;

    /** changes take effect immediately */
    public static final int IMMEDIATE                           = 0x00000000;
    
    /** changes take effect later */
    public static final int DEFERRED                            = 0x80000000;
    public static final int COMMITDEFERREDSETTINGS              = (NONE | IMMEDIATE);

    /** affects DSPROPERTY_EAXBUFFER_DIRECTHF */
    public static final int FLAGS_DIRECTHFAUTO                  = 0x00000001;
    
    /** affects DSPROPERTY_EAXBUFFER_ROOM */
    public static final int FLAGS_ROOMAUTO                      = 0x00000002;
    
    /** affects DSPROPERTY_EAXBUFFER_ROOMHF */
    public static final int FLAGS_ROOMHFAUTO                    = 0x00000004;
    
    /** reserved future use */
    public static final int FLAGS_RESERVED                      = 0xFFFFFFF8;
    
    // property ranges and defaults:
    
    public static final int MINDIRECT                           = -10000;
    public static final int MAXDIRECT                           = 1000;
    public static final int DEFAULTDIRECT                       = 0;
    
    public static final int MINDIRECTHF                         = -10000;
    public static final int MAXDIRECTHF                         = 0;
    public static final int DEFAULTDIRECTHF                     = 0;
    
    public static final int MINROOM                             = -10000;
    public static final int MAXROOM                             = 1000;
    public static final int DEFAULTROOM                         = 0;
    
    public static final int MINROOMHF                           = -10000;
    public static final int MAXROOMHF                           = 0;
    public static final int DEFAULTROOMHF                       = 0;
    
    public static final float MINROOMROLLOFFFACTOR              = 0.0f;
    public static final float MAXROOMROLLOFFFACTOR              = 10.f;
    public static final float DEFAULTROOMROLLOFFFACTOR          = 0.0f;
    
    public static final int MINOBSTRUCTION                      = -10000;
    public static final int MAXOBSTRUCTION                      = 0;
    public static final int DEFAULTOBSTRUCTION                  = 0;
    
    public static final float MINOBSTRUCTIONLFRATIO             = 0.0f;
    public static final float MAXOBSTRUCTIONLFRATIO             = 1.0f;
    public static final float DEFAULTOBSTRUCTIONLFRATIO         = 0.0f;
    
    public static final int MINOCCLUSION                        = -10000;
    public static final int MAXOCCLUSION                        = 0;
    public static final int DEFAULTOCCLUSION                    = 0;
    
    public static final float MINOCCLUSIONLFRATIO               = 0.0f;
    public static final float MAXOCCLUSIONLFRATIO               = 1.0f;
    public static final float DEFAULTOCCLUSIONLFRATIO           = 0.25f;
    
    public static final float MINOCCLUSIONROOMRATIO             = 0.0f;
    public static final float MAXOCCLUSIONROOMRATIO             = 10.0f;
    public static final float DEFAULTOCCLUSIONROOMRATIO         = 0.5f;
    
    public static final int MINOUTSIDEVOLUMEHF                  = -10000;
    public static final int MAXOUTSIDEVOLUMEHF                  = 0;
    public static final int DEFAULTOUTSIDEVOLUMEHF              = 0;
    
    public static final float MINAIRABSORPTIONFACTOR            = 0.0f;
    public static final float MAXAIRABSORPTIONFACTOR            = 10.0f;
    public static final float DEFAULTAIRABSORPTIONFACTOR        = 1.0f;
    
    public static final int DEFAULTFLAGS                        = (FLAGS_DIRECTHFAUTO |
                                                                    FLAGS_ROOMAUTO |
                                                                    FLAGS_ROOMHFAUTO);    
    
    static {
        System.loadLibrary(org.lwjgl.Sys.getLibraryName());
        EAXBUFFERPROPERTIES_SIZE = sizeOfEaxBufferProperties();
        assignOffsets();
    }
    
    /** 
     * Creates a new instance of EAXBufferProperties 
     *
     * @param eaxBufferProperties address of EAXBUFFERPROPERTIES
     */
    public EAXBufferProperties() {
        eaxBufferProperties = ByteBuffer.allocateDirect(EAXBUFFERPROPERTIES_SIZE);
        eaxBufferProperties.order(ByteOrder.nativeOrder());
    }
    
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
     * @param direct direct path level at high frequencies to set to
     */
    public void setDirectHF(int directHF) {
        eaxBufferProperties.putInt(directHF_offset, directHF);
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
     * @param room room effect level at high frequencies to set to 
     */
    public void setRoomHF(int roomHF) {
        eaxBufferProperties.putInt(roomHF_offset, roomHF);
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
     * Sets the  modifier for behavior of properties
     *
     * @param flags modifier for behavior of properties to set to
     */
    public void setFlags(int flags) {
        eaxBufferProperties.putInt(flags_offset, flags);
    }
    
    /**
     * Retrieves the address of this EAXBufferProperties
     */
    public int getAddress() {
        return Sys.getDirectBufferAddress(eaxBufferProperties);
    }
    
    /**
     * Retrieves the size of the containing ByteBuffer
     */
    public int getSize() {
        return EAXBUFFERPROPERTIES_SIZE;
    }
    
    /**
     * Retrieves the size of the EAXBUFFERPROPERTIES
     */
    protected static native int sizeOfEaxBufferProperties();
    
    /**
     * Sets the offsets to the fields
     */
    protected static native void assignOffsets();
}