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

/**
 * $Id$
 *
 * This class implements the basic EAX extension constants.
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public interface BaseEAXConstants {
    public static final int DSPROPERTY_EAXLISTENER_NONE                 = 0;
    public static final int DSPROPERTY_EAXLISTENER_ALLPARAMETERS        = 1;
    public static final int DSPROPERTY_EAXLISTENER_ROOM                 = 2;
    public static final int DSPROPERTY_EAXLISTENER_ROOMHF               = 3;
    public static final int DSPROPERTY_EAXLISTENER_ROOMROLLOFFFACTOR    = 4;
    public static final int DSPROPERTY_EAXLISTENER_DECAYTIME            = 5;
    public static final int DSPROPERTY_EAXLISTENER_DECAYHFRATIO         = 6;
    public static final int DSPROPERTY_EAXLISTENER_REFLECTIONS          = 7;
    public static final int DSPROPERTY_EAXLISTENER_REFLECTIONSDELAY     = 8;
    public static final int DSPROPERTY_EAXLISTENER_REVERB               = 9;
    public static final int DSPROPERTY_EAXLISTENER_REVERBDELAY          = 10;
    public static final int DSPROPERTY_EAXLISTENER_ENVIRONMENT          = 11;
    public static final int DSPROPERTY_EAXLISTENER_ENVIRONMENTSIZE      = 12;
    public static final int DSPROPERTY_EAXLISTENER_ENVIRONMENTDIFFUSION = 13;
    public static final int DSPROPERTY_EAXLISTENER_AIRABSORPTIONHF      = 14;
    public static final int DSPROPERTY_EAXLISTENER_FLAGS                = 15;
    
    /** changes take effect immediately */
    public static final int DSPROPERTY_EAXLISTENER_IMMEDIATE            = 0x00000000;
    
    /** changes take effect later */
    public static final int DSPROPERTY_EAXLISTENER_DEFERRED             = 0x80000000;
    
    public static final int DSPROPERTY_EAXLISTENER_COMMITDEFERREDSETTINGS = 
                            (DSPROPERTY_EAXLISTENER_NONE | 
                            DSPROPERTY_EAXLISTENER_IMMEDIATE);
    
    public static final int ENVIRONMENT_GENERIC                         = 0;
    public static final int ENVIRONMENT_PADDEDCELL                      = 1;
    public static final int ENVIRONMENT_ROOM                            = 2;
    public static final int ENVIRONMENT_BATHROOM                        = 3;
    public static final int ENVIRONMENT_LIVINGROOM                      = 4;
    public static final int ENVIRONMENT_STONEROOM                       = 5;
    public static final int ENVIRONMENT_AUDITORIUM                      = 6;
    public static final int ENVIRONMENT_CONCERTHALL                     = 7;
    public static final int ENVIRONMENT_CAVE                            = 8;
    public static final int ENVIRONMENT_ARENA                           = 9;
    public static final int ENVIRONMENT_HANGAR                          = 10;
    public static final int ENVIRONMENT_CARPETEDHALLWAY                 = 11;
    public static final int ENVIRONMENT_HALLWAY                         = 12;
    public static final int ENVIRONMENT_STONECORRIDOR                   = 13;
    public static final int ENVIRONMENT_ALLEY                           = 14;
    public static final int ENVIRONMENT_FOREST                          = 15;
    public static final int ENVIRONMENT_CITY                            = 16;
    public static final int ENVIRONMENT_MOUNTAINS                       = 17;
    public static final int ENVIRONMENT_QUARRY                          = 18;
    public static final int ENVIRONMENT_PLAIN                           = 19;
    public static final int ENVIRONMENT_PARKINGLOT                      = 20;
    public static final int ENVIRONMENT_SEWERPIPE                       = 21;
    public static final int ENVIRONMENT_UNDERWATER                      = 22;
    public static final int ENVIRONMENT_DRUGGED                         = 23;
    public static final int ENVIRONMENT_DIZZY                           = 24;
    public static final int ENVIRONMENT_PSYCHOTIC                       = 25;
    public static final int ENVIRONMENT_COUNT                           = 26;
    
    /** reverberation decay time */
    public static final int EAXLISTENERFLAGS_DECAYTIMESCALE             = 0x00000001;
    
    /** reflection level */
    public static final int EAXLISTENERFLAGS_REFLECTIONSSCALE           = 0x00000002;
    
    /** initial reflection delay time */
    public static final int EAXLISTENERFLAGS_REFLECTIONSDELAYSCALE      = 0x00000004;

    /** reflections level */
    public static final int EAXLISTENERFLAGS_REVERBSCALE                = 0x00000008;

    /** late reverberation delay time */
    public static final int EAXLISTENERFLAGS_REVERBDELAYSCALE           = 0x00000010;

    /** This flag limits high-frequency decay time according to air absorption. */
    public static final int EAXLISTENERFLAGS_DECAYHFLIMIT               = 0x00000020;

    /** reserved future use */
    public static final int EAXLISTENERFLAGS_RESERVED                   = 0xFFFFFFC0;

    // property ranges and defaults:
    public static final int EAXLISTENER_MINROOM                         = -10000;
    public static final int EAXLISTENER_MAXROOM                         = 0;
    public static final int EAXLISTENER_DEFAULTROOM                     = -1000;
    
    public static final int EAXLISTENER_MINROOMHF                       = -10000;
    public static final int EAXLISTENER_MAXROOMHF                       = 0;
    public static final int EAXLISTENER_DEFAULTROOMHF                   = -100;
    
    public static final float EAXLISTENER_MINROOMROLLOFFFACTOR          = 0.0f;
    public static final float EAXLISTENER_MAXROOMROLLOFFFACTOR          = 10.0f;
    public static final float EAXLISTENER_DEFAULTROOMROLLOFFFACTOR      = 0.0f;
    
    public static final float EAXLISTENER_MINDECAYTIME                  = 0.1f;
    public static final float EAXLISTENER_MAXDECAYTIME                  = 20.0f;
    public static final float EAXLISTENER_DEFAULTDECAYTIME              = 1.49f;
    
    public static final float EAXLISTENER_MINDECAYHFRATIO               = 0.1f;
    public static final float EAXLISTENER_MAXDECAYHFRATIO               = 2.0f;
    public static final float EAXLISTENER_DEFAULTDECAYHFRATIO           = 0.83f;
    
    public static final int EAXLISTENER_MINREFLECTIONS                  = -10000;
    public static final int EAXLISTENER_MAXREFLECTIONS                  = 1000;
    public static final int EAXLISTENER_DEFAULTREFLECTIONS              = -2602;
    
    public static final float EAXLISTENER_MINREFLECTIONSDELAY           = 0.0f;
    public static final float EAXLISTENER_MAXREFLECTIONSDELAY           = 0.3f;
    public static final float EAXLISTENER_DEFAULTREFLECTIONSDELAY       = 0.007f;
    
    public static final int EAXLISTENER_MINREVERB                       = -10000;
    public static final int EAXLISTENER_MAXREVERB                       = 2000;
    public static final int EAXLISTENER_DEFAULTREVERB                   = 200;
    
    public static final float EAXLISTENER_MINREVERBDELAY                = 0.0f;
    public static final float EAXLISTENER_MAXREVERBDELAY                = 0.1f;
    public static final float EAXLISTENER_DEFAULTREVERBDELAY            = 0.011f;
    
    public static final int EAXLISTENER_MINENVIRONMENT                  = 0;
    public static final int EAXLISTENER_MAXENVIRONMENT                  = (ENVIRONMENT_COUNT-1);
    public static final int EAXLISTENER_DEFAULTENVIRONMENT              = ENVIRONMENT_GENERIC;
    
    public static final float EAXLISTENER_MINENVIRONMENTSIZE            = 1.0f;
    public static final float EAXLISTENER_MAXENVIRONMENTSIZE            = 100.0f;
    public static final float EAXLISTENER_DEFAULTENVIRONMENTSIZE        = 7.5f;
    
    public static final float EAXLISTENER_MINENVIRONMENTDIFFUSION       = 0.0f;
    public static final float EAXLISTENER_MAXENVIRONMENTDIFFUSION       = 1.0f;
    public static final float EAXLISTENER_DEFAULTENVIRONMENTDIFFUSION   = 1.0f;
    
    public static final float EAXLISTENER_MINAIRABSORPTIONHF            = -100.0f;
    public static final float EAXLISTENER_MAXAIRABSORPTIONHF            = 0.0f;
    public static final float EAXLISTENER_DEFAULTAIRABSORPTIONHF        = -5.0f;
    
    public static final int EAXLISTENER_DEFAULTFLAGS                    = 
                            (EAXLISTENERFLAGS_DECAYTIMESCALE |
                            EAXLISTENERFLAGS_REFLECTIONSSCALE |
                            EAXLISTENERFLAGS_REFLECTIONSDELAYSCALE |
                            EAXLISTENERFLAGS_REVERBSCALE |
                            EAXLISTENERFLAGS_REVERBDELAYSCALE |
                            EAXLISTENERFLAGS_DECAYHFLIMIT);
    
    //------------------------------------
    public static final int DSPROPERTY_EAXBUFFER_NONE                   = 0;
    public static final int DSPROPERTY_EAXBUFFER_ALLPARAMETERS          = 1;
    public static final int DSPROPERTY_EAXBUFFER_DIRECT                 = 2;
    public static final int DSPROPERTY_EAXBUFFER_DIRECTHF               = 3;
    public static final int DSPROPERTY_EAXBUFFER_ROOM                   = 4;
    public static final int DSPROPERTY_EAXBUFFER_ROOMHF                 = 5;
    public static final int DSPROPERTY_EAXBUFFER_ROOMROLLOFFFACTOR      = 6;
    public static final int DSPROPERTY_EAXBUFFER_OBSTRUCTION            = 7;
    public static final int DSPROPERTY_EAXBUFFER_OBSTRUCTIONLFRATIO     = 8;
    public static final int DSPROPERTY_EAXBUFFER_OCCLUSION              = 9;
    public static final int DSPROPERTY_EAXBUFFER_OCCLUSIONLFRATIO       = 10;
    public static final int DSPROPERTY_EAXBUFFER_OCCLUSIONROOMRATIO     = 11;
    public static final int DSPROPERTY_EAXBUFFER_OUTSIDEVOLUMEHF        = 12;
    public static final int DSPROPERTY_EAXBUFFER_AIRABSORPTIONFACTOR    = 13;
    public static final int DSPROPERTY_EAXBUFFER_FLAGS                  = 14;

    /** changes take effect immediately */
    public static final int DSPROPERTY_EAXBUFFER_IMMEDIATE              = 0x00000000;
    
    /** changes take effect later */
    public static final int DSPROPERTY_EAXBUFFER_DEFERRED               = 0x80000000;
    public static final int DSPROPERTY_EAXBUFFER_COMMITDEFERREDSETTINGS = 
                            (DSPROPERTY_EAXBUFFER_NONE | 
                            DSPROPERTY_EAXBUFFER_IMMEDIATE);

    /** affects DSPROPERTY_EAXBUFFER_DIRECTHF */
    public static final int EAXBUFFERFLAGS_DIRECTHFAUTO                 = 0x00000001;
    
    /** affects DSPROPERTY_EAXBUFFER_ROOM */
    public static final int EAXBUFFERFLAGS_ROOMAUTO                     = 0x00000002;
    
    /** affects DSPROPERTY_EAXBUFFER_ROOMHF */
    public static final int EAXBUFFERFLAGS_ROOMHFAUTO                   = 0x00000004;
    
    /** reserved future use */
    public static final int EAXBUFFERFLAGS_RESERVED                     = 0xFFFFFFF8;
    
    // property ranges and defaults:
    
    public static final int EAXBUFFER_MINDIRECT                         = -10000;
    public static final int EAXBUFFER_MAXDIRECT                         = 1000;
    public static final int EAXBUFFER_DEFAULTDIRECT                     = 0;
    
    public static final int EAXBUFFER_MINDIRECTHF                       = -10000;
    public static final int EAXBUFFER_MAXDIRECTHF                       = 0;
    public static final int EAXBUFFER_DEFAULTDIRECTHF                   = 0;
    
    public static final int EAXBUFFER_MINROOM                           = -10000;
    public static final int EAXBUFFER_MAXROOM                           = 1000;
    public static final int EAXBUFFER_DEFAULTROOM                       = 0;
    
    public static final int EAXBUFFER_MINROOMHF                         = -10000;
    public static final int EAXBUFFER_MAXROOMHF                         = 0;
    public static final int EAXBUFFER_DEFAULTROOMHF                     = 0;
    
    public static final float EAXBUFFER_MINROOMROLLOFFFACTOR            = 0.0f;
    public static final float EAXBUFFER_MAXROOMROLLOFFFACTOR            = 10.f;
    public static final float EAXBUFFER_DEFAULTROOMROLLOFFFACTOR        = 0.0f;
    
    public static final int EAXBUFFER_MINOBSTRUCTION                    = -10000;
    public static final int EAXBUFFER_MAXOBSTRUCTION                    = 0;
    public static final int EAXBUFFER_DEFAULTOBSTRUCTION                = 0;
    
    public static final float EAXBUFFER_MINOBSTRUCTIONLFRATIO           = 0.0f;
    public static final float EAXBUFFER_MAXOBSTRUCTIONLFRATIO           = 1.0f;
    public static final float EAXBUFFER_DEFAULTOBSTRUCTIONLFRATIO       = 0.0f;
    
    public static final int EAXBUFFER_MINOCCLUSION                      = -10000;
    public static final int EAXBUFFER_MAXOCCLUSION                      = 0;
    public static final int EAXBUFFER_DEFAULTOCCLUSION                  = 0;
    
    public static final float EAXBUFFER_MINOCCLUSIONLFRATIO             = 0.0f;
    public static final float EAXBUFFER_MAXOCCLUSIONLFRATIO             = 1.0f;
    public static final float EAXBUFFER_DEFAULTOCCLUSIONLFRATIO         = 0.25f;
    
    public static final float EAXBUFFER_MINOCCLUSIONROOMRATIO           = 0.0f;
    public static final float EAXBUFFER_MAXOCCLUSIONROOMRATIO           = 10.0f;
    public static final float EAXBUFFER_DEFAULTOCCLUSIONROOMRATIO       = 0.5f;
    
    public static final int EAXBUFFER_MINOUTSIDEVOLUMEHF                = -10000;
    public static final int EAXBUFFER_MAXOUTSIDEVOLUMEHF                = 0;
    public static final int EAXBUFFER_DEFAULTOUTSIDEVOLUMEHF            = 0;
    
    public static final float EAXBUFFER_MINAIRABSORPTIONFACTOR          = 0.0f;
    public static final float EAXBUFFER_MAXAIRABSORPTIONFACTOR          = 10.0f;
    public static final float EAXBUFFER_DEFAULTAIRABSORPTIONFACTOR      = 1.0f;
    
    public static final int EAXBUFFER_DEFAULTFLAGS                      = 
                            (EAXBUFFERFLAGS_DIRECTHFAUTO |
                            EAXBUFFERFLAGS_ROOMAUTO |
                            EAXBUFFERFLAGS_ROOMHFAUTO);
    
    // Single window material preset
    public static final int MATERIAL_SINGLEWINDOW                       = -2800;
    public static final float MATERIAL_SINGLEWINDOWLF                   = 0.71f;
    public static final float MATERIAL_SINGLEWINDOWROOMRATIO            = 0.43f;
    
    // Double window material preset
    public static final int MATERIAL_DOUBLEWINDOW                       = -5000;
    public static final float MATERIAL_DOUBLEWINDOWHF                   = 0.40f;
    public static final float MATERIAL_DOUBLEWINDOWROOMRATIO            = 0.24f;
    
    // Thin door material preset
    public static final int MATERIAL_THINDOOR                           = -1800;
    public static final float MATERIAL_THINDOORLF                       = 0.66f;
    public static final float MATERIAL_THINDOORROOMRATIO                = 0.66f;
    
    // Thick door material preset
    public static final int MATERIAL_THICKDOOR                          = -4400;
    public static final float MATERIAL_THICKDOORLF                      = 0.64f;
    public static final float MATERIAL_THICKDOORROOMRTATION             = 0.27f;
    
    // Wood wall material preset
    public static final int MATERIAL_WOODWALL                           = -4000;
    public static final float MATERIAL_WOODWALLLF                       = 0.50f;
    public static final float MATERIAL_WOODWALLROOMRATIO                = 0.30f;
    
    // Brick wall material preset
    public static final int MATERIAL_BRICKWALL                          = -5000;
    public static final float MATERIAL_BRICKWALLLF                      = 0.60f;
    public static final float MATERIAL_BRICKWALLROOMRATIO               = 0.24f;
    
    // Stone wall material preset
    public static final int MATERIAL_STONEWALL                          = -6000;
    public static final float MATERIAL_STONEWALLLF                      = 0.68f;
    public static final float MATERIAL_STONEWALLROOMRATIO               = 0.20f;
    
    // Curtain material preset
    public static final int MATERIAL_CURTAIN                            = -1200;
    public static final float MATERIAL_CURTAINLF                        = 0.15f;
    public static final float MATERIAL_CURTAINROOMRATIO                 = 1.00f;
}