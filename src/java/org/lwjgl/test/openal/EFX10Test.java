/* 
 * Copyright (c) 2002-2010 LWJGL Project
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
package org.lwjgl.test.openal;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCcontext;
import org.lwjgl.openal.ALCdevice;
import org.lwjgl.openal.EFX10;
import org.lwjgl.openal.EFXUtil;
import org.lwjgl.util.WaveData;

/**
 * Class with a few examples testing and demonstrating the use of the OpenAL extension ALC_EXT_EFX.
 * <p>
 * This class is not compatible with the LWJGL debug build (lwjgl-debug.jar), as the debug build
 * throws exceptions instead of alGetError checks. The redundant exception handling code was not
 * added in order to keep these examples simple.
 *
 * @author Ciardhubh <ciardhubh[at]ciardhubh.de>
 * @version $Revision$
 * $Id$
 */
public final class EFX10Test {

    public static void main(final String[] args) throws Exception {
        silentTests();
        playbackTest();
        efxUtilTest();
    }

    /**
     * Loads OpenAL and makes sure ALC_EXT_EFX is supported.
     */
    private static void setupEfx() throws Exception {
        // Load and create OpenAL
        if (!AL.isCreated()) {
            AL.create();
        }
        // Query for Effect Extension
        if (!ALC10.alcIsExtensionPresent(AL.getDevice(), EFX10.ALC_EXT_EFX_NAME)) {
            throw new Exception("No ALC_EXT_EFX supported by driver.");
        }
        System.out.println("ALC_EXT_EFX found.");
    }

    /**
     * Runs a series of API calls similar to the tutorials in the Effects Extension Guide of the
     * OpenAL SDK. Nothing is played in this method.
     */
    private static void silentTests() throws Exception {
        setupEfx();

        final ALCdevice device = AL.getDevice();

        // Create context (only necessary if LWJGL context isn't sufficient, done as example)
        final IntBuffer contextAttribList = BufferUtils.createIntBuffer(8);
        contextAttribList.put(ALC10.ALC_FREQUENCY);
        contextAttribList.put(44100);
        contextAttribList.put(ALC10.ALC_REFRESH);
        contextAttribList.put(60);
        contextAttribList.put(ALC10.ALC_SYNC);
        contextAttribList.put(ALC10.ALC_FALSE);
        contextAttribList.rewind();
        // ALC_MAX_AUXILIARY_SENDS won't go above compile-time max. Set to compile-time max if
        // greater.
        contextAttribList.put(EFX10.ALC_MAX_AUXILIARY_SENDS);
        contextAttribList.put(2);
        final ALCcontext newContext = ALC10.alcCreateContext(device, contextAttribList);
        if (newContext == null) {
            throw new Exception("Failed to create context.");
        }
        final int contextCurResult = ALC10.alcMakeContextCurrent(newContext);
        if (contextCurResult == ALC10.ALC_FALSE) {
            throw new Exception("Failed to make context current.");
        }

        // Query EFX ALC values
        System.out.println("AL_VERSION: " + AL10.alGetString(AL10.AL_VERSION));
        final IntBuffer buff = BufferUtils.createIntBuffer(1);
        ALC10.alcGetInteger(device, EFX10.ALC_EFX_MAJOR_VERSION, buff);
        System.out.println("ALC_EFX_MAJOR_VERSION: " + buff.get(0));
        ALC10.alcGetInteger(device, EFX10.ALC_EFX_MINOR_VERSION, buff);
        System.out.println("ALC_EFX_MINOR_VERSION: " + buff.get(0));
        ALC10.alcGetInteger(device, EFX10.ALC_MAX_AUXILIARY_SENDS, buff);
        final int maxAuxSends = buff.get(0);
        System.out.println("ALC_MAX_AUXILIARY_SENDS: " + maxAuxSends);


        // Try to create 4 Auxiliary Effect Slots
        int numAuxSlots = 0;
        final int[] auxEffectSlots = new int[4]; // try more to test
        AL10.alGetError();
        for (numAuxSlots = 0; numAuxSlots < 4; numAuxSlots++) {
            auxEffectSlots[numAuxSlots] = EFX10.alGenAuxiliaryEffectSlots();
            if (AL10.alGetError() != AL10.AL_NO_ERROR) {
                break;
            }
        }
        System.out.println("Created " + numAuxSlots + " aux effect slots.");

        // Try to create 2 Effects
        int numEffects = 0;
        final int[] effects = new int[2];
        for (numEffects = 0; numEffects < 2; numEffects++) {
            effects[numEffects] = EFX10.alGenEffects();
            if (AL10.alGetError() != AL10.AL_NO_ERROR) {
                break;
            }
        }
        System.out.println("Created " + numEffects + " effects.");

        // Set first Effect Type to Reverb and change Decay Time
        AL10.alGetError();
        if (EFX10.alIsEffect(effects[0])) {
            EFX10.alEffecti(effects[0], EFX10.AL_EFFECT_TYPE, EFX10.AL_EFFECT_REVERB);
            if (AL10.alGetError() != AL10.AL_NO_ERROR) {
                System.out.println("Reverb effect not supported.");
            } else {
                EFX10.alEffectf(effects[0], EFX10.AL_REVERB_DECAY_TIME, 5.0f);
                System.out.println("Reverb effect created.");
            }
        } else {
            throw new Exception("First effect not a valid effect.");
        }

        // Set second Effect Type to Flanger and change Phase
        AL10.alGetError();
        if (EFX10.alIsEffect(effects[1])) {
            EFX10.alEffecti(effects[1], EFX10.AL_EFFECT_TYPE, EFX10.AL_EFFECT_FLANGER);
            if (AL10.alGetError() != AL10.AL_NO_ERROR) {
                System.out.println("Flanger effect not support.");
            } else {
                EFX10.alEffecti(effects[1], EFX10.AL_FLANGER_PHASE, 180);
                System.out.println("Flanger effect created.");
            }
        } else {
            throw new Exception("Second effect not a valid effect.");
        }

        // Try to create a Filter
        AL10.alGetError();
        final int filter = EFX10.alGenFilters();
        if (AL10.alGetError() != AL10.AL_NO_ERROR) {
            throw new Exception("Failed to create filter.");
        }
        System.out.println("Generated a filter.");
        if (EFX10.alIsFilter(filter)) {
            // Set Filter type to Low-Pass and set parameters
            EFX10.alFilteri(filter, EFX10.AL_FILTER_TYPE, EFX10.AL_FILTER_LOWPASS);
            if (AL10.alGetError() != AL10.AL_NO_ERROR) {
                System.out.println("Low pass filter not supported.");
            } else {
                EFX10.alFilterf(filter, EFX10.AL_LOWPASS_GAIN, 0.5f);
                EFX10.alFilterf(filter, EFX10.AL_LOWPASS_GAINHF, 0.5f);
                System.out.println("Low pass filter created.");
            }
        }

        // Attach Effect to Auxiliary Effect Slot
        AL10.alGetError();
        EFX10.alAuxiliaryEffectSloti(auxEffectSlots[0], EFX10.AL_EFFECTSLOT_EFFECT, effects[0]);
        if (AL10.alGetError() != AL10.AL_NO_ERROR) {
            throw new Exception("Failed to attach effect to aux effect slot.");
        }
        System.out.println("Successfully loaded effect into effect slot.");

        // Configure Source Auxiliary Effect Slot Sends
        final int source = AL10.alGenSources();
        // Set Source Send 0 to feed auxEffectSlots[0] without filtering
        AL11.alSource3i(source, EFX10.AL_AUXILIARY_SEND_FILTER, auxEffectSlots[0], 0,
                EFX10.AL_FILTER_NULL);
        if (AL10.alGetError() != AL10.AL_NO_ERROR) {
            throw new Exception("Failed to configure Source Send 0");
        }
        System.out.println("Linked aux effect slot to soutce slot 0");
        // Set Source Send 1 to feed uiEffectSlot[1] with filter filter
        AL11.alSource3i(source, EFX10.AL_AUXILIARY_SEND_FILTER, auxEffectSlots[1], 1, filter);
        if (AL10.alGetError() != AL10.AL_NO_ERROR) {
            // e.g. if only 1 send per source is available
            throw new Exception("Failed to configure Source Send 1");
        }
        System.out.println("Linked aux effect slot to soutce slot 1");
        // Disable Send 0
        AL11.alSource3i(source, EFX10.AL_AUXILIARY_SEND_FILTER, EFX10.AL_EFFECTSLOT_NULL, 0,
                EFX10.AL_FILTER_NULL);
        if (AL10.alGetError() != AL10.AL_NO_ERROR) {
            throw new Exception("Failed to disable Source Send 0");
        }
        System.out.println("Disabled source send 0");
        // Disable Send 1
        AL11.alSource3i(source, EFX10.AL_AUXILIARY_SEND_FILTER, EFX10.AL_EFFECTSLOT_NULL, 1,
                EFX10.AL_FILTER_NULL);
        if (AL10.alGetError() != AL10.AL_NO_ERROR) {
            throw new Exception("Failed to disable Source Send 1");
        }
        System.out.println("Disabled source send 1");


        // Filter 'source', a generated Source
        AL10.alSourcei(source, EFX10.AL_DIRECT_FILTER, filter);
        if (AL10.alGetError() == AL10.AL_NO_ERROR) {
            {
                System.out.println("Successfully applied a direct path filter");
                // Remove filter from 'source'
                AL10.alSourcei(source, EFX10.AL_DIRECT_FILTER, EFX10.AL_FILTER_NULL);
                if (AL10.alGetError() == AL10.AL_NO_ERROR) {
                    System.out.println("Successfully removed direct filter");
                }
            }
            // Filter the Source send 0 from 'source' to Auxiliary Effect Slot auxEffectSlot[0]
            // using Filter uiFilter[0]
            AL11.alSource3i(source, EFX10.AL_AUXILIARY_SEND_FILTER, auxEffectSlots[0], 0, filter);
            if (AL10.alGetError() == AL10.AL_NO_ERROR) {
                {
                    System.out.println("Successfully applied aux send filter");
                    // Remove Filter from Source Auxiliary Send
                    AL11.alSource3i(source, EFX10.AL_AUXILIARY_SEND_FILTER, auxEffectSlots[0], 0,
                            EFX10.AL_FILTER_NULL);
                    if (AL10.alGetError() == AL10.AL_NO_ERROR) {
                        System.out.println("Successfully removed filter");
                    }
                }
            }
        }

        // Set Source Cone Outer Gain HF value
        AL10.alSourcef(source, EFX10.AL_CONE_OUTER_GAINHF, 0.5f);
        if (AL10.alGetError() == AL10.AL_NO_ERROR) {
            System.out.println("Successfully set cone outside gain filter");
        }

        // Set distance units to be in feet
        AL10.alListenerf(EFX10.AL_METERS_PER_UNIT, 0.3f);
        if (AL10.alGetError() == AL10.AL_NO_ERROR) {
            System.out.println("Successfully set distance units");
        }

        // Cleanup
        final IntBuffer auxEffectSlotsBuf = (IntBuffer) BufferUtils.createIntBuffer(
                auxEffectSlots.length).put(auxEffectSlots).rewind();
        EFX10.alDeleteAuxiliaryEffectSlots(auxEffectSlotsBuf);
        final IntBuffer effectsBuf = (IntBuffer) BufferUtils.createIntBuffer(
                effects.length).put(effects).rewind();
        EFX10.alDeleteEffects(effectsBuf);
        EFX10.alDeleteFilters(filter);
        AL.destroy();
    }

    /**
     * Plays a sound with various effects applied to it.
     */
    private static void playbackTest() throws Exception {
        setupEfx();

        // Create a source and buffer audio data
        final int source = AL10.alGenSources();
        final int buffer = AL10.alGenBuffers();
        WaveData waveFile = WaveData.create("Footsteps.wav");
        if (waveFile == null) {
            System.out.println("Failed to load Footsteps.wav! Skipping playback test.");
            AL.destroy();
            return;
        }
        AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
        waveFile.dispose();
        AL10.alSourcei(source, AL10.AL_BUFFER, buffer);
        AL10.alSourcei(source, AL10.AL_LOOPING, AL10.AL_TRUE);

        System.out.println("Playing sound unaffected by EFX ...");
        AL10.alSourcePlay(source);
        Thread.sleep(7500);

        // Add reverb effect
        final int effectSlot = EFX10.alGenAuxiliaryEffectSlots();
        final int reverbEffect = EFX10.alGenEffects();
        EFX10.alEffecti(reverbEffect, EFX10.AL_EFFECT_TYPE, EFX10.AL_EFFECT_REVERB);
        EFX10.alEffectf(reverbEffect, EFX10.AL_REVERB_DECAY_TIME, 5.0f);
        EFX10.alAuxiliaryEffectSloti(effectSlot, EFX10.AL_EFFECTSLOT_EFFECT, reverbEffect);
        AL11.alSource3i(source, EFX10.AL_AUXILIARY_SEND_FILTER, effectSlot, 0,
                EFX10.AL_FILTER_NULL);

        System.out.println("Playing sound with reverb ...");
        AL10.alSourcePlay(source);
        Thread.sleep(7500);

        // Add low-pass filter directly to source
        final int filter = EFX10.alGenFilters();
        EFX10.alFilteri(filter, EFX10.AL_FILTER_TYPE, EFX10.AL_FILTER_LOWPASS);
        EFX10.alFilterf(filter, EFX10.AL_LOWPASS_GAIN, 0.5f);
        EFX10.alFilterf(filter, EFX10.AL_LOWPASS_GAINHF, 0.5f);
        AL10.alSourcei(source, EFX10.AL_DIRECT_FILTER, filter);

        System.out.println("Playing sound with reverb and direct low pass filter ...");
        AL10.alSourcePlay(source);
        Thread.sleep(7500);
        AL10.alSourcei(source, EFX10.AL_DIRECT_FILTER, EFX10.AL_FILTER_NULL);

        // Add low-pass filter to source send
        //AL11.alSource3i(source, EFX10.AL_AUXILIARY_SEND_FILTER, effectSlot, 0, filter);
        //
        //System.out.println("Playing sound with reverb and aux send low pass filter ...");
        //AL10.alSourcePlay(source);
        //Thread.sleep(7500);

        // Cleanup
        AL11.alSource3i(source, EFX10.AL_AUXILIARY_SEND_FILTER, EFX10.AL_EFFECTSLOT_NULL, 0,
                EFX10.AL_FILTER_NULL);
        EFX10.alAuxiliaryEffectSloti(effectSlot, EFX10.AL_EFFECTSLOT_EFFECT, EFX10.AL_EFFECT_NULL);
        EFX10.alDeleteEffects(reverbEffect);
        EFX10.alDeleteFilters(filter);

        // Echo effect
        final int echoEffect = EFX10.alGenEffects();
        EFX10.alEffecti(echoEffect, EFX10.AL_EFFECT_TYPE, EFX10.AL_EFFECT_ECHO);
        //EFX10.alEffectf(echoEffect, EFX10.AL_ECHO_DELAY, 5.0f);
        EFX10.alAuxiliaryEffectSloti(effectSlot, EFX10.AL_EFFECTSLOT_EFFECT, echoEffect);
        AL11.alSource3i(source, EFX10.AL_AUXILIARY_SEND_FILTER, effectSlot, 0,
                EFX10.AL_FILTER_NULL);

        System.out.println("Playing sound with echo effect ...");
        AL10.alSourcePlay(source);
        Thread.sleep(7500);

        AL.destroy();
    }

    /**
     * Checks OpenAL for every EFX 1.0 effect and filter and prints the result to the console.
     */
    private static void efxUtilTest() throws Exception {
        setupEfx();

        System.out.println();
        System.out.println("Checking supported effects ...");
        if (EFXUtil.isEffectSupported(EFX10.AL_EFFECT_NULL)) {
            System.out.println("AL_EFFECT_NULL is supported.");
        } else {
            System.out.println("AL_EFFECT_NULL is NOT supported.");
        }
        if (EFXUtil.isEffectSupported(EFX10.AL_EFFECT_EAXREVERB)) {
            System.out.println("AL_EFFECT_EAXREVERB is supported.");
        } else {
            System.out.println("AL_EFFECT_EAXREVERB is NOT supported.");
        }
        if (EFXUtil.isEffectSupported(EFX10.AL_EFFECT_REVERB)) {
            System.out.println("AL_EFFECT_REVERB is supported.");
        } else {
            System.out.println("AL_EFFECT_REVERB is NOT supported.");
        }
        if (EFXUtil.isEffectSupported(EFX10.AL_EFFECT_CHORUS)) {
            System.out.println("AL_EFFECT_CHORUS is supported.");
        } else {
            System.out.println("AL_EFFECT_CHORUS is NOT supported.");
        }
        if (EFXUtil.isEffectSupported(EFX10.AL_EFFECT_DISTORTION)) {
            System.out.println("AL_EFFECT_DISTORTION is supported.");
        } else {
            System.out.println("AL_EFFECT_DISTORTION is NOT supported.");
        }
        if (EFXUtil.isEffectSupported(EFX10.AL_EFFECT_ECHO)) {
            System.out.println("AL_EFFECT_ECHO is supported.");
        } else {
            System.out.println("AL_EFFECT_ECHO is NOT supported.");
        }
        if (EFXUtil.isEffectSupported(EFX10.AL_EFFECT_FLANGER)) {
            System.out.println("AL_EFFECT_FLANGER is supported.");
        } else {
            System.out.println("AL_EFFECT_FLANGER is NOT supported.");
        }
        if (EFXUtil.isEffectSupported(EFX10.AL_EFFECT_FREQUENCY_SHIFTER)) {
            System.out.println("AL_EFFECT_FREQUENCY_SHIFTER is supported.");
        } else {
            System.out.println("AL_EFFECT_FREQUENCY_SHIFTER is NOT supported.");
        }
        if (EFXUtil.isEffectSupported(EFX10.AL_EFFECT_VOCAL_MORPHER)) {
            System.out.println("AL_EFFECT_VOCAL_MORPHER is supported.");
        } else {
            System.out.println("AL_EFFECT_VOCAL_MORPHER is NOT supported.");
        }
        if (EFXUtil.isEffectSupported(EFX10.AL_EFFECT_PITCH_SHIFTER)) {
            System.out.println("AL_EFFECT_PITCH_SHIFTER is supported.");
        } else {
            System.out.println("AL_EFFECT_PITCH_SHIFTER is NOT supported.");
        }
        if (EFXUtil.isEffectSupported(EFX10.AL_EFFECT_RING_MODULATOR)) {
            System.out.println("AL_EFFECT_RING_MODULATOR is supported.");
        } else {
            System.out.println("AL_EFFECT_RING_MODULATOR is NOT supported.");
        }
        if (EFXUtil.isEffectSupported(EFX10.AL_EFFECT_AUTOWAH)) {
            System.out.println("AL_EFFECT_AUTOWAH is supported.");
        } else {
            System.out.println("AL_EFFECT_AUTOWAH is NOT supported.");
        }
        if (EFXUtil.isEffectSupported(EFX10.AL_EFFECT_COMPRESSOR)) {
            System.out.println("AL_EFFECT_COMPRESSOR is supported.");
        } else {
            System.out.println("AL_EFFECT_COMPRESSOR is NOT supported.");
        }
        if (EFXUtil.isEffectSupported(EFX10.AL_EFFECT_EQUALIZER)) {
            System.out.println("AL_EFFECT_EQUALIZER is supported.");
        } else {
            System.out.println("AL_EFFECT_EQUALIZER is NOT supported.");
        }

        System.out.println();
        System.out.println("Checking supported filters ...");
        if (EFXUtil.isFilterSupported(EFX10.AL_FILTER_NULL)) {
            System.out.println("AL_FILTER_NULL is supported.");
        } else {
            System.out.println("AL_FILTER_NULL is NOT supported.");
        }
        if (EFXUtil.isFilterSupported(EFX10.AL_FILTER_LOWPASS)) {
            System.out.println("AL_FILTER_LOWPASS is supported.");
        } else {
            System.out.println("AL_FILTER_LOWPASS is NOT supported.");
        }
        if (EFXUtil.isFilterSupported(EFX10.AL_FILTER_HIGHPASS)) {
            System.out.println("AL_FILTER_HIGHPASS is supported.");
        } else {
            System.out.println("AL_FILTER_HIGHPASS is NOT supported.");
        }
        if (EFXUtil.isFilterSupported(EFX10.AL_FILTER_BANDPASS)) {
            System.out.println("AL_FILTER_BANDPASS is supported.");
        } else {
            System.out.println("AL_FILTER_BANDPASS is NOT supported.");
        }
    }
}
