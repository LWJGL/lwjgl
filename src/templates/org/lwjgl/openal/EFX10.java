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
package org.lwjgl.openal;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.util.generator.*;
import org.lwjgl.util.generator.openal.ALenum;
import org.lwjgl.util.generator.openal.ALsizei;
import org.lwjgl.util.generator.openal.ALuint;
import org.lwjgl.util.generator.openal.ALvoid;
import org.lwjgl.util.generator.Alternate;

/**
 * Implementation of the OpenAL extension ALC_EXT_EFX (version 1.0). Contains necessary fields,
 * methods and a range of supplementary fields containing minimum, maximum and default values of
 * the former fields.
 * <p>
 * On top of regular functions defined in the ALC_EXT_EFX, there are also several convenience
 * functions. Namely alGen... and alDelete... which do not take a Java buffer parameter and
 * automatically create or delete a single object, without the overhead of using a buffer.
 * <p>
 * For comments and specification of functions and fields, refer to the "Effects Extension Guide"
 * which is part of the OpenAL SDK and can be downloaded from:
 * http://connect.creativelabs.com/openal/Downloads/Forms/AllItems.aspx
 *
 * @author Ciardhubh <ciardhubh[at]ciardhubh.de>
 * @version $Revision$
 * $Id$
 */
public interface EFX10 {

    // ALC properties
    String ALC_EXT_EFX_NAME = "ALC_EXT_EFX";
    int ALC_EFX_MAJOR_VERSION = 0x20001;
    int ALC_EFX_MINOR_VERSION = 0x20002;
    int ALC_MAX_AUXILIARY_SENDS = 0x20003;

    // Listener properties
    int AL_METERS_PER_UNIT = 0x20004;

    // Source properties
    int AL_DIRECT_FILTER = 0x20005;
    int AL_AUXILIARY_SEND_FILTER = 0x20006;
    int AL_AIR_ABSORPTION_FACTOR = 0x20007;
    int AL_ROOM_ROLLOFF_FACTOR = 0x20008;
    int AL_CONE_OUTER_GAINHF = 0x20009;
    int AL_DIRECT_FILTER_GAINHF_AUTO = 0x2000A;
    int AL_AUXILIARY_SEND_FILTER_GAIN_AUTO = 0x2000B;
    int AL_AUXILIARY_SEND_FILTER_GAINHF_AUTO = 0x2000C;

    // Auxiliary effect slot properties
    int AL_EFFECTSLOT_EFFECT = 0x0001;
    int AL_EFFECTSLOT_GAIN = 0x0002;
    int AL_EFFECTSLOT_AUXILIARY_SEND_AUTO = 0x0003;
    // NULL auxiliary slot ID to disable a source send
    int AL_EFFECTSLOT_NULL = 0x0000;

    // Effect parameters
    // Reverb
    int AL_REVERB_DENSITY = 0x0001;
    int AL_REVERB_DIFFUSION = 0x0002;
    int AL_REVERB_GAIN = 0x0003;
    int AL_REVERB_GAINHF = 0x0004;
    int AL_REVERB_DECAY_TIME = 0x0005;
    int AL_REVERB_DECAY_HFRATIO = 0x0006;
    int AL_REVERB_REFLECTIONS_GAIN = 0x0007;
    int AL_REVERB_REFLECTIONS_DELAY = 0x0008;
    int AL_REVERB_LATE_REVERB_GAIN = 0x0009;
    int AL_REVERB_LATE_REVERB_DELAY = 0x000A;
    int AL_REVERB_AIR_ABSORPTION_GAINHF = 0x000B;
    int AL_REVERB_ROOM_ROLLOFF_FACTOR = 0x000C;
    int AL_REVERB_DECAY_HFLIMIT = 0x000D;
    // EAX Reverb
    int AL_EAXREVERB_DENSITY = 0x0001;
    int AL_EAXREVERB_DIFFUSION = 0x0002;
    int AL_EAXREVERB_GAIN = 0x0003;
    int AL_EAXREVERB_GAINHF = 0x0004;
    int AL_EAXREVERB_GAINLF = 0x0005;
    int AL_EAXREVERB_DECAY_TIME = 0x0006;
    int AL_EAXREVERB_DECAY_HFRATIO = 0x0007;
    int AL_EAXREVERB_DECAY_LFRATIO = 0x0008;
    int AL_EAXREVERB_REFLECTIONS_GAIN = 0x0009;
    int AL_EAXREVERB_REFLECTIONS_DELAY = 0x000A;
    int AL_EAXREVERB_REFLECTIONS_PAN = 0x000B;
    int AL_EAXREVERB_LATE_REVERB_GAIN = 0x000C;
    int AL_EAXREVERB_LATE_REVERB_DELAY = 0x000D;
    int AL_EAXREVERB_LATE_REVERB_PAN = 0x000E;
    int AL_EAXREVERB_ECHO_TIME = 0x000F;
    int AL_EAXREVERB_ECHO_DEPTH = 0x0010;
    int AL_EAXREVERB_MODULATION_TIME = 0x0011;
    int AL_EAXREVERB_MODULATION_DEPTH = 0x0012;
    int AL_EAXREVERB_AIR_ABSORPTION_GAINHF = 0x0013;
    int AL_EAXREVERB_HFREFERENCE = 0x0014;
    int AL_EAXREVERB_LFREFERENCE = 0x0015;
    int AL_EAXREVERB_ROOM_ROLLOFF_FACTOR = 0x0016;
    int AL_EAXREVERB_DECAY_HFLIMIT = 0x0017;
    // Chorus
    int AL_CHORUS_WAVEFORM = 0x0001;
    int AL_CHORUS_PHASE = 0x0002;
    int AL_CHORUS_RATE = 0x0003;
    int AL_CHORUS_DEPTH = 0x0004;
    int AL_CHORUS_FEEDBACK = 0x0005;
    int AL_CHORUS_DELAY = 0x0006;
    // Distortion
    int AL_DISTORTION_EDGE = 0x0001;
    int AL_DISTORTION_GAIN = 0x0002;
    int AL_DISTORTION_LOWPASS_CUTOFF = 0x0003;
    int AL_DISTORTION_EQCENTER = 0x0004;
    int AL_DISTORTION_EQBANDWIDTH = 0x0005;
    // Echo
    int AL_ECHO_DELAY = 0x0001;
    int AL_ECHO_LRDELAY = 0x0002;
    int AL_ECHO_DAMPING = 0x0003;
    int AL_ECHO_FEEDBACK = 0x0004;
    int AL_ECHO_SPREAD = 0x0005;
    // Flanger
    int AL_FLANGER_WAVEFORM = 0x0001;
    int AL_FLANGER_PHASE = 0x0002;
    int AL_FLANGER_RATE = 0x0003;
    int AL_FLANGER_DEPTH = 0x0004;
    int AL_FLANGER_FEEDBACK = 0x0005;
    int AL_FLANGER_DELAY = 0x0006;
    // Frequency shifter
    int AL_FREQUENCY_SHIFTER_FREQUENCY = 0x0001;
    int AL_FREQUENCY_SHIFTER_LEFT_DIRECTION = 0x0002;
    int AL_FREQUENCY_SHIFTER_RIGHT_DIRECTION = 0x0003;
    // Vocal morpher
    int AL_VOCAL_MORPHER_PHONEMEA = 0x0001;
    int AL_VOCAL_MORPHER_PHONEMEA_COARSE_TUNING = 0x0002;
    int AL_VOCAL_MORPHER_PHONEMEB = 0x0003;
    int AL_VOCAL_MORPHER_PHONEMEB_COARSE_TUNING = 0x0004;
    int AL_VOCAL_MORPHER_WAVEFORM = 0x0005;
    int AL_VOCAL_MORPHER_RATE = 0x0006;
    // Pitch shifter
    int AL_PITCH_SHIFTER_COARSE_TUNE = 0x0001;
    int AL_PITCH_SHIFTER_FINE_TUNE = 0x0002;
    // Ring modulator
    int AL_RING_MODULATOR_FREQUENCY = 0x0001;
    int AL_RING_MODULATOR_HIGHPASS_CUTOFF = 0x0002;
    int AL_RING_MODULATOR_WAVEFORM = 0x0003;
    // Autowah
    int AL_AUTOWAH_ATTACK_TIME = 0x0001;
    int AL_AUTOWAH_RELEASE_TIME = 0x0002;
    int AL_AUTOWAH_RESONANCE = 0x0003;
    int AL_AUTOWAH_PEAK_GAIN = 0x0004;
    // Compressor
    int AL_COMPRESSOR_ONOFF = 0x0001;
    // Equalizer
    int AL_EQUALIZER_LOW_GAIN = 0x0001;
    int AL_EQUALIZER_LOW_CUTOFF = 0x0002;
    int AL_EQUALIZER_MID1_GAIN = 0x0003;
    int AL_EQUALIZER_MID1_CENTER = 0x0004;
    int AL_EQUALIZER_MID1_WIDTH = 0x0005;
    int AL_EQUALIZER_MID2_GAIN = 0x0006;
    int AL_EQUALIZER_MID2_CENTER = 0x0007;
    int AL_EQUALIZER_MID2_WIDTH = 0x0008;
    int AL_EQUALIZER_HIGH_GAIN = 0x0009;
    int AL_EQUALIZER_HIGH_CUTOFF = 0x000A;
    // Effect type
    int AL_EFFECT_FIRST_PARAMETER = 0x0000;
    int AL_EFFECT_LAST_PARAMETER = 0x8000;
    int AL_EFFECT_TYPE = 0x8001;
    // Effect types, used with AL_EFFECT_TYPE
    int AL_EFFECT_NULL = 0x0000;
    int AL_EFFECT_REVERB = 0x0001;
    int AL_EFFECT_CHORUS = 0x0002;
    int AL_EFFECT_DISTORTION = 0x0003;
    int AL_EFFECT_ECHO = 0x0004;
    int AL_EFFECT_FLANGER = 0x0005;
    int AL_EFFECT_FREQUENCY_SHIFTER = 0x0006;
    int AL_EFFECT_VOCAL_MORPHER = 0x0007;
    int AL_EFFECT_PITCH_SHIFTER = 0x0008;
    int AL_EFFECT_RING_MODULATOR = 0x0009;
    int AL_EFFECT_AUTOWAH = 0x000A;
    int AL_EFFECT_COMPRESSOR = 0x000B;
    int AL_EFFECT_EQUALIZER = 0x000C;
    int AL_EFFECT_EAXREVERB = 0x8000;

    // Filter properties
    // Lowpass
    int AL_LOWPASS_GAIN = 0x0001;
    int AL_LOWPASS_GAINHF = 0x0002;
    // Highpass
    int AL_HIGHPASS_GAIN = 0x0001;
    int AL_HIGHPASS_GAINLF = 0x0002;
    // Bandpass
    int AL_BANDPASS_GAIN = 0x0001;
    int AL_BANDPASS_GAINLF = 0x0002;
    int AL_BANDPASS_GAINHF = 0x0003;
    // Filter type
    int AL_FILTER_FIRST_PARAMETER = 0x0000;
    int AL_FILTER_LAST_PARAMETER = 0x8000;
    int AL_FILTER_TYPE = 0x8001;
    // Filter types, used with the AL_FILTER_TYPE property
    int AL_FILTER_NULL = 0x0000;
    int AL_FILTER_LOWPASS = 0x0001;
    int AL_FILTER_HIGHPASS = 0x0002;
    int AL_FILTER_BANDPASS = 0x0003;

    // Auxiliary effect slot object functions
    @ALvoid
    void alGenAuxiliaryEffectSlots(@AutoSize("auxiliaryeffectslots") @ALsizei int n, @OutParameter @ALuint IntBuffer auxiliaryeffectslots);

    @Alternate(value = "alGenAuxiliaryEffectSlots", nativeAlt = true)
    @ALvoid
    void alGenAuxiliaryEffectSlots2(@Constant("1") @ALsizei int n, @Result @ALuint int auxiliaryeffectslot);

    @ALvoid
    void alDeleteAuxiliaryEffectSlots(@AutoSize("auxiliaryeffectslots") @ALsizei int n, @OutParameter @ALuint IntBuffer auxiliaryeffectslots);

    @Alternate(value = "alDeleteAuxiliaryEffectSlots", nativeAlt = true)
    @ALvoid
    void alDeleteAuxiliaryEffectSlots2(@Constant("1") @ALsizei int n, @Indirect @ALuint int auxiliaryeffectslot);

    boolean alIsAuxiliaryEffectSlot(@ALuint int auxiliaryeffectslot);

    @ALvoid
    void alAuxiliaryEffectSloti(@ALuint int auxiliaryeffectslot, @ALenum int param, int value);

    @StripPostfix("values")
    @ALvoid
    void alAuxiliaryEffectSlotiv(@ALuint int auxiliaryeffectslot, @ALenum int param, @Check("1") @Const IntBuffer values);

    @ALvoid
    void alAuxiliaryEffectSlotf(@ALuint int auxiliaryeffectslot, @ALenum int param, float value);

    @StripPostfix("values")
    @ALvoid
    void alAuxiliaryEffectSlotfv(@ALuint int auxiliaryeffectslot, @ALenum int param, @Check("1") @Const FloatBuffer values);

    @ALvoid
    void alGetAuxiliaryEffectSloti(@ALuint int auxiliaryeffectslot, @ALenum int param, @Result int value);

    @StripPostfix("intdata")
    @ALvoid
    void alGetAuxiliaryEffectSlotiv(@ALuint int auxiliaryeffectslot, @ALenum int param, @OutParameter @Check("1") IntBuffer intdata);

    @ALvoid
    void alGetAuxiliaryEffectSlotf(@ALuint int auxiliaryeffectslot, @ALenum int param, @Result float value);

    @StripPostfix("floatdata")
    @ALvoid
    void alGetAuxiliaryEffectSlotfv(@ALuint int auxiliaryeffectslot, @ALenum int param, @OutParameter @Check("1") FloatBuffer floatdata);

    // Effect object functions
    @ALvoid
    void alGenEffects(@AutoSize("effects") @ALsizei int n, @OutParameter @ALuint IntBuffer effects);

    @Alternate(value = "alGenEffects", nativeAlt = true)
    @ALvoid
    void alGenEffects2(@Constant("1") @ALsizei int n, @Result @ALuint int effect);

    @ALvoid
    void alDeleteEffects(@AutoSize("effects") @ALsizei int n, @OutParameter @ALuint IntBuffer effects);

    @Alternate(value = "alDeleteEffects", nativeAlt = true)
    @ALvoid
    void alDeleteEffects2(@Constant("1") @ALsizei int n, @Indirect @ALuint int effect);

    boolean alIsEffect(@ALuint int effect);

    @ALvoid
    void alEffecti(@ALuint int effect, @ALenum int param, int value);

    @StripPostfix("values")
    @ALvoid
    void alEffectiv(@ALuint int effect, @ALenum int param, @Check("1") @Const IntBuffer values);

    @ALvoid
    void alEffectf(@ALuint int effect, @ALenum int param, float value);

    @StripPostfix("values")
    @ALvoid
    void alEffectfv(@ALuint int effect, @ALenum int param, @Check("1") @Const FloatBuffer values);

    @ALvoid
    void alGetEffecti(@ALuint int effect, @ALenum int param, @Result int value);

    @StripPostfix("intdata")
    @ALvoid
    void alGetEffectiv(@ALuint int effect, @ALenum int param, @OutParameter @Check("1") IntBuffer intdata);

    @ALvoid
    void alGetEffectf(@ALuint int effect, @ALenum int param, @Result float value);

    @StripPostfix("floatdata")
    @ALvoid
    void alGetEffectfv(@ALuint int effect, @ALenum int param, @OutParameter @Check("1") FloatBuffer floatdata);

    // Filter object functions
    @ALvoid
    void alGenFilters(@AutoSize("filters") @ALsizei int n, @OutParameter @ALuint IntBuffer filters);

    @Alternate(value = "alGenFilters", nativeAlt = true)
    @ALvoid
    void alGenFilters2(@Constant("1") @ALsizei int n, @Result @ALuint int filter);

    @ALvoid
    void alDeleteFilters(@AutoSize("filters") @ALsizei int n, @OutParameter @ALuint IntBuffer filters);

    @Alternate(value = "alDeleteFilters", nativeAlt = true)
    @ALvoid
    void alDeleteFilters2(@Constant("1") @ALsizei int n, @Indirect @ALuint int filter);

    boolean alIsFilter(@ALuint int filter);

    @ALvoid
    void alFilteri(@ALuint int filter, @ALenum int param, int value);

    @StripPostfix("values")
    @ALvoid
    void alFilteriv(@ALuint int filter, @ALenum int param, @Check("1") @Const IntBuffer values);

    @ALvoid
    void alFilterf(@ALuint int filter, @ALenum int param, float value);

    @StripPostfix("values")
    @ALvoid
    void alFilterfv(@ALuint int filter, @ALenum int param, @Check("1") @Const FloatBuffer values);

    @ALvoid
    void alGetFilteri(@ALuint int filter, @ALenum int param, @Result int value);

    @StripPostfix("intdata")
    @ALvoid
    void alGetFilteriv(@ALuint int filter, @ALenum int param, @OutParameter @Check("1") IntBuffer intdata);

    @ALvoid
    void alGetFilterf(@ALuint int filter, @ALenum int param, @Result float value);

    @StripPostfix("floatdata")
    @ALvoid
    void alGetFilterfv(@ALuint int filter, @ALenum int param, @OutParameter @Check("1") FloatBuffer floatdata);

    // Source property value ranges and defaults
    float AL_MIN_AIR_ABSORPTION_FACTOR = 0.0f;
    float AL_MAX_AIR_ABSORPTION_FACTOR = 10.0f;
    float AL_DEFAULT_AIR_ABSORPTION_FACTOR = 0.0f;
    float AL_MIN_ROOM_ROLLOFF_FACTOR = 0.0f;
    float AL_MAX_ROOM_ROLLOFF_FACTOR = 10.0f;
    float AL_DEFAULT_ROOM_ROLLOFF_FACTOR = 0.0f;
    float AL_MIN_CONE_OUTER_GAINHF = 0.0f;
    float AL_MAX_CONE_OUTER_GAINHF = 1.0f;
    float AL_DEFAULT_CONE_OUTER_GAINHF = 1.0f;
    int AL_MIN_DIRECT_FILTER_GAINHF_AUTO = AL10.AL_FALSE;
    int AL_MAX_DIRECT_FILTER_GAINHF_AUTO = AL10.AL_TRUE;
    int AL_DEFAULT_DIRECT_FILTER_GAINHF_AUTO = AL10.AL_TRUE;
    int AL_MIN_AUXILIARY_SEND_FILTER_GAIN_AUTO = AL10.AL_FALSE;
    int AL_MAX_AUXILIARY_SEND_FILTER_GAIN_AUTO = AL10.AL_TRUE;
    int AL_DEFAULT_AUXILIARY_SEND_FILTER_GAIN_AUTO = AL10.AL_TRUE;
    int AL_MIN_AUXILIARY_SEND_FILTER_GAINHF_AUTO = AL10.AL_FALSE;
    int AL_MAX_AUXILIARY_SEND_FILTER_GAINHF_AUTO = AL10.AL_TRUE;
    int AL_DEFAULT_AUXILIARY_SEND_FILTER_GAINHF_AUTO = AL10.AL_TRUE;

    // Listener property value ranges and defaults
    float AL_MIN_METERS_PER_UNIT = Float.MIN_VALUE;
    float AL_MAX_METERS_PER_UNIT = Float.MAX_VALUE;
    float AL_DEFAULT_METERS_PER_UNIT = 1.0f;

    // Effect parameter ranges and defaults
    // Reverb
    float AL_REVERB_MIN_DENSITY = 0.0f;
    float AL_REVERB_MAX_DENSITY = 1.0f;
    float AL_REVERB_DEFAULT_DENSITY = 1.0f;
    float AL_REVERB_MIN_DIFFUSION = 0.0f;
    float AL_REVERB_MAX_DIFFUSION = 1.0f;
    float AL_REVERB_DEFAULT_DIFFUSION = 1.0f;
    float AL_REVERB_MIN_GAIN = 0.0f;
    float AL_REVERB_MAX_GAIN = 1.0f;
    float AL_REVERB_DEFAULT_GAIN = 0.32f;
    float AL_REVERB_MIN_GAINHF = 0.0f;
    float AL_REVERB_MAX_GAINHF = 1.0f;
    float AL_REVERB_DEFAULT_GAINHF = 0.89f;
    float AL_REVERB_MIN_DECAY_TIME = 0.1f;
    float AL_REVERB_MAX_DECAY_TIME = 20.0f;
    float AL_REVERB_DEFAULT_DECAY_TIME = 1.49f;
    float AL_REVERB_MIN_DECAY_HFRATIO = 0.1f;
    float AL_REVERB_MAX_DECAY_HFRATIO = 2.0f;
    float AL_REVERB_DEFAULT_DECAY_HFRATIO = 0.83f;
    float AL_REVERB_MIN_REFLECTIONS_GAIN = 0.0f;
    float AL_REVERB_MAX_REFLECTIONS_GAIN = 3.16f;
    float AL_REVERB_DEFAULT_REFLECTIONS_GAIN = 0.05f;
    float AL_REVERB_MIN_REFLECTIONS_DELAY = 0.0f;
    float AL_REVERB_MAX_REFLECTIONS_DELAY = 0.3f;
    float AL_REVERB_DEFAULT_REFLECTIONS_DELAY = 0.007f;
    float AL_REVERB_MIN_LATE_REVERB_GAIN = 0.0f;
    float AL_REVERB_MAX_LATE_REVERB_GAIN = 10.0f;
    float AL_REVERB_DEFAULT_LATE_REVERB_GAIN = 1.26f;
    float AL_REVERB_MIN_LATE_REVERB_DELAY = 0.0f;
    float AL_REVERB_MAX_LATE_REVERB_DELAY = 0.1f;
    float AL_REVERB_DEFAULT_LATE_REVERB_DELAY = 0.011f;
    float AL_REVERB_MIN_AIR_ABSORPTION_GAINHF = 0.892f;
    float AL_REVERB_MAX_AIR_ABSORPTION_GAINHF = 1.0f;
    float AL_REVERB_DEFAULT_AIR_ABSORPTION_GAINHF = 0.994f;
    float AL_REVERB_MIN_ROOM_ROLLOFF_FACTOR = 0.0f;
    float AL_REVERB_MAX_ROOM_ROLLOFF_FACTOR = 10.0f;
    float AL_REVERB_DEFAULT_ROOM_ROLLOFF_FACTOR = 0.0f;
    int AL_REVERB_MIN_DECAY_HFLIMIT = AL10.AL_FALSE;
    int AL_REVERB_MAX_DECAY_HFLIMIT = AL10.AL_TRUE;
    int AL_REVERB_DEFAULT_DECAY_HFLIMIT = AL10.AL_TRUE;
    // EAX reverb
    float AL_EAXREVERB_MIN_DENSITY = 0.0f;
    float AL_EAXREVERB_MAX_DENSITY = 1.0f;
    float AL_EAXREVERB_DEFAULT_DENSITY = 1.0f;
    float AL_EAXREVERB_MIN_DIFFUSION = 0.0f;
    float AL_EAXREVERB_MAX_DIFFUSION = 1.0f;
    float AL_EAXREVERB_DEFAULT_DIFFUSION = 1.0f;
    float AL_EAXREVERB_MIN_GAIN = 0.0f;
    float AL_EAXREVERB_MAX_GAIN = 1.0f;
    float AL_EAXREVERB_DEFAULT_GAIN = 0.32f;
    float AL_EAXREVERB_MIN_GAINHF = 0.0f;
    float AL_EAXREVERB_MAX_GAINHF = 1.0f;
    float AL_EAXREVERB_DEFAULT_GAINHF = 0.89f;
    float AL_EAXREVERB_MIN_GAINLF = 0.0f;
    float AL_EAXREVERB_MAX_GAINLF = 1.0f;
    float AL_EAXREVERB_DEFAULT_GAINLF = 1.0f;
    float AL_EAXREVERB_MIN_DECAY_TIME = 0.1f;
    float AL_EAXREVERB_MAX_DECAY_TIME = 20.0f;
    float AL_EAXREVERB_DEFAULT_DECAY_TIME = 1.49f;
    float AL_EAXREVERB_MIN_DECAY_HFRATIO = 0.1f;
    float AL_EAXREVERB_MAX_DECAY_HFRATIO = 2.0f;
    float AL_EAXREVERB_DEFAULT_DECAY_HFRATIO = 0.83f;
    float AL_EAXREVERB_MIN_DECAY_LFRATIO = 0.1f;
    float AL_EAXREVERB_MAX_DECAY_LFRATIO = 2.0f;
    float AL_EAXREVERB_DEFAULT_DECAY_LFRATIO = 1.0f;
    float AL_EAXREVERB_MIN_REFLECTIONS_GAIN = 0.0f;
    float AL_EAXREVERB_MAX_REFLECTIONS_GAIN = 3.16f;
    float AL_EAXREVERB_DEFAULT_REFLECTIONS_GAIN = 0.05f;
    float AL_EAXREVERB_MIN_REFLECTIONS_DELAY = 0.0f;
    float AL_EAXREVERB_MAX_REFLECTIONS_DELAY = 0.3f;
    float AL_EAXREVERB_DEFAULT_REFLECTIONS_DELAY = 0.007f;
    float AL_EAXREVERB_DEFAULT_REFLECTIONS_PAN_XYZ = 0.0f;
    float AL_EAXREVERB_MIN_LATE_REVERB_GAIN = 0.0f;
    float AL_EAXREVERB_MAX_LATE_REVERB_GAIN = 10.0f;
    float AL_EAXREVERB_DEFAULT_LATE_REVERB_GAIN = 1.26f;
    float AL_EAXREVERB_MIN_LATE_REVERB_DELAY = 0.0f;
    float AL_EAXREVERB_MAX_LATE_REVERB_DELAY = 0.1f;
    float AL_EAXREVERB_DEFAULT_LATE_REVERB_DELAY = 0.011f;
    float AL_EAXREVERB_DEFAULT_LATE_REVERB_PAN_XYZ = 0.0f;
    float AL_EAXREVERB_MIN_ECHO_TIME = 0.075f;
    float AL_EAXREVERB_MAX_ECHO_TIME = 0.25f;
    float AL_EAXREVERB_DEFAULT_ECHO_TIME = 0.25f;
    float AL_EAXREVERB_MIN_ECHO_DEPTH = 0.0f;
    float AL_EAXREVERB_MAX_ECHO_DEPTH = 1.0f;
    float AL_EAXREVERB_DEFAULT_ECHO_DEPTH = 0.0f;
    float AL_EAXREVERB_MIN_MODULATION_TIME = 0.04f;
    float AL_EAXREVERB_MAX_MODULATION_TIME = 4.0f;
    float AL_EAXREVERB_DEFAULT_MODULATION_TIME = 0.25f;
    float AL_EAXREVERB_MIN_MODULATION_DEPTH = 0.0f;
    float AL_EAXREVERB_MAX_MODULATION_DEPTH = 1.0f;
    float AL_EAXREVERB_DEFAULT_MODULATION_DEPTH = 0.0f;
    float AL_EAXREVERB_MIN_AIR_ABSORPTION_GAINHF = 0.892f;
    float AL_EAXREVERB_MAX_AIR_ABSORPTION_GAINHF = 1.0f;
    float AL_EAXREVERB_DEFAULT_AIR_ABSORPTION_GAINHF = 0.994f;
    float AL_EAXREVERB_MIN_HFREFERENCE = 1000.0f;
    float AL_EAXREVERB_MAX_HFREFERENCE = 20000.0f;
    float AL_EAXREVERB_DEFAULT_HFREFERENCE = 5000.0f;
    float AL_EAXREVERB_MIN_LFREFERENCE = 20.0f;
    float AL_EAXREVERB_MAX_LFREFERENCE = 1000.0f;
    float AL_EAXREVERB_DEFAULT_LFREFERENCE = 250.0f;
    float AL_EAXREVERB_MIN_ROOM_ROLLOFF_FACTOR = 0.0f;
    float AL_EAXREVERB_MAX_ROOM_ROLLOFF_FACTOR = 10.0f;
    float AL_EAXREVERB_DEFAULT_ROOM_ROLLOFF_FACTOR = 0.0f;
    int AL_EAXREVERB_MIN_DECAY_HFLIMIT = AL10.AL_FALSE;
    int AL_EAXREVERB_MAX_DECAY_HFLIMIT = AL10.AL_TRUE;
    int AL_EAXREVERB_DEFAULT_DECAY_HFLIMIT = AL10.AL_TRUE;
    // Chorus
    int AL_CHORUS_WAVEFORM_SINUSOID = 0;
    int AL_CHORUS_WAVEFORM_TRIANGLE = 1;
    int AL_CHORUS_MIN_WAVEFORM = 0;
    int AL_CHORUS_MAX_WAVEFORM = 1;
    int AL_CHORUS_DEFAULT_WAVEFORM = 1;
    int AL_CHORUS_MIN_PHASE = -180;
    int AL_CHORUS_MAX_PHASE = 180;
    int AL_CHORUS_DEFAULT_PHASE = 90;
    float AL_CHORUS_MIN_RATE = 0.0f;
    float AL_CHORUS_MAX_RATE = 10.0f;
    float AL_CHORUS_DEFAULT_RATE = 1.1f;
    float AL_CHORUS_MIN_DEPTH = 0.0f;
    float AL_CHORUS_MAX_DEPTH = 1.0f;
    float AL_CHORUS_DEFAULT_DEPTH = 0.1f;
    float AL_CHORUS_MIN_FEEDBACK = -1.0f;
    float AL_CHORUS_MAX_FEEDBACK = 1.0f;
    float AL_CHORUS_DEFAULT_FEEDBACK = 0.25f;
    float AL_CHORUS_MIN_DELAY = 0.0f;
    float AL_CHORUS_MAX_DELAY = 0.016f;
    float AL_CHORUS_DEFAULT_DELAY = 0.016f;
    // Distortion
    float AL_DISTORTION_MIN_EDGE = 0.0f;
    float AL_DISTORTION_MAX_EDGE = 1.0f;
    float AL_DISTORTION_DEFAULT_EDGE = 0.2f;
    float AL_DISTORTION_MIN_GAIN = 0.01f;
    float AL_DISTORTION_MAX_GAIN = 1.0f;
    float AL_DISTORTION_DEFAULT_GAIN = 0.05f;
    float AL_DISTORTION_MIN_LOWPASS_CUTOFF = 80.0f;
    float AL_DISTORTION_MAX_LOWPASS_CUTOFF = 24000.0f;
    float AL_DISTORTION_DEFAULT_LOWPASS_CUTOFF = 8000.0f;
    float AL_DISTORTION_MIN_EQCENTER = 80.0f;
    float AL_DISTORTION_MAX_EQCENTER = 24000.0f;
    float AL_DISTORTION_DEFAULT_EQCENTER = 3600.0f;
    float AL_DISTORTION_MIN_EQBANDWIDTH = 80.0f;
    float AL_DISTORTION_MAX_EQBANDWIDTH = 24000.0f;
    float AL_DISTORTION_DEFAULT_EQBANDWIDTH = 3600.0f;
    // Echo
    float AL_ECHO_MIN_DELAY = 0.0f;
    float AL_ECHO_MAX_DELAY = 0.207f;
    float AL_ECHO_DEFAULT_DELAY = 0.1f;
    float AL_ECHO_MIN_LRDELAY = 0.0f;
    float AL_ECHO_MAX_LRDELAY = 0.404f;
    float AL_ECHO_DEFAULT_LRDELAY = 0.1f;
    float AL_ECHO_MIN_DAMPING = 0.0f;
    float AL_ECHO_MAX_DAMPING = 0.99f;
    float AL_ECHO_DEFAULT_DAMPING = 0.5f;
    float AL_ECHO_MIN_FEEDBACK = 0.0f;
    float AL_ECHO_MAX_FEEDBACK = 1.0f;
    float AL_ECHO_DEFAULT_FEEDBACK = 0.5f;
    float AL_ECHO_MIN_SPREAD = -1.0f;
    float AL_ECHO_MAX_SPREAD = 1.0f;
    float AL_ECHO_DEFAULT_SPREAD = -1.0f;
    // Flanger
    int AL_FLANGER_WAVEFORM_SINUSOID = 0;
    int AL_FLANGER_WAVEFORM_TRIANGLE = 1;
    int AL_FLANGER_MIN_WAVEFORM = 0;
    int AL_FLANGER_MAX_WAVEFORM = 1;
    int AL_FLANGER_DEFAULT_WAVEFORM = 1;
    int AL_FLANGER_MIN_PHASE = -180;
    int AL_FLANGER_MAX_PHASE = 180;
    int AL_FLANGER_DEFAULT_PHASE = 0;
    float AL_FLANGER_MIN_RATE = 0.0f;
    float AL_FLANGER_MAX_RATE = 10.0f;
    float AL_FLANGER_DEFAULT_RATE = 0.27f;
    float AL_FLANGER_MIN_DEPTH = 0.0f;
    float AL_FLANGER_MAX_DEPTH = 1.0f;
    float AL_FLANGER_DEFAULT_DEPTH = 1.0f;
    float AL_FLANGER_MIN_FEEDBACK = -1.0f;
    float AL_FLANGER_MAX_FEEDBACK = 1.0f;
    float AL_FLANGER_DEFAULT_FEEDBACK = -0.5f;
    float AL_FLANGER_MIN_DELAY = 0.0f;
    float AL_FLANGER_MAX_DELAY = 0.004f;
    float AL_FLANGER_DEFAULT_DELAY = 0.002f;
    // Frequency shifter
    float AL_FREQUENCY_SHIFTER_MIN_FREQUENCY = 0.0f;
    float AL_FREQUENCY_SHIFTER_MAX_FREQUENCY = 24000.0f;
    float AL_FREQUENCY_SHIFTER_DEFAULT_FREQUENCY = 0.0f;
    int AL_FREQUENCY_SHIFTER_MIN_LEFT_DIRECTION = 0;
    int AL_FREQUENCY_SHIFTER_MAX_LEFT_DIRECTION = 2;
    int AL_FREQUENCY_SHIFTER_DEFAULT_LEFT_DIRECTION = 0;
    int AL_FREQUENCY_SHIFTER_DIRECTION_DOWN = 0;
    int AL_FREQUENCY_SHIFTER_DIRECTION_UP = 1;
    int AL_FREQUENCY_SHIFTER_DIRECTION_OFF = 2;
    int AL_FREQUENCY_SHIFTER_MIN_RIGHT_DIRECTION = 0;
    int AL_FREQUENCY_SHIFTER_MAX_RIGHT_DIRECTION = 2;
    int AL_FREQUENCY_SHIFTER_DEFAULT_RIGHT_DIRECTION = 0;
    // Vocal morpher
    int AL_VOCAL_MORPHER_MIN_PHONEMEA = 0;
    int AL_VOCAL_MORPHER_MAX_PHONEMEA = 29;
    int AL_VOCAL_MORPHER_DEFAULT_PHONEMEA = 0;
    int AL_VOCAL_MORPHER_MIN_PHONEMEA_COARSE_TUNING = -24;
    int AL_VOCAL_MORPHER_MAX_PHONEMEA_COARSE_TUNING = 24;
    int AL_VOCAL_MORPHER_DEFAULT_PHONEMEA_COARSE_TUNING = 0;
    int AL_VOCAL_MORPHER_MIN_PHONEMEB = 0;
    int AL_VOCAL_MORPHER_MAX_PHONEMEB = 29;
    int AL_VOCAL_MORPHER_DEFAULT_PHONEMEB = 10;
    int AL_VOCAL_MORPHER_MIN_PHONEMEB_COARSE_TUNING = -24;
    int AL_VOCAL_MORPHER_MAX_PHONEMEB_COARSE_TUNING = 24;
    int AL_VOCAL_MORPHER_DEFAULT_PHONEMEB_COARSE_TUNING = 0;
    int AL_VOCAL_MORPHER_PHONEME_A = 0;
    int AL_VOCAL_MORPHER_PHONEME_E = 1;
    int AL_VOCAL_MORPHER_PHONEME_I = 2;
    int AL_VOCAL_MORPHER_PHONEME_O = 3;
    int AL_VOCAL_MORPHER_PHONEME_U = 4;
    int AL_VOCAL_MORPHER_PHONEME_AA = 5;
    int AL_VOCAL_MORPHER_PHONEME_AE = 6;
    int AL_VOCAL_MORPHER_PHONEME_AH = 7;
    int AL_VOCAL_MORPHER_PHONEME_AO = 8;
    int AL_VOCAL_MORPHER_PHONEME_EH = 9;
    int AL_VOCAL_MORPHER_PHONEME_ER = 10;
    int AL_VOCAL_MORPHER_PHONEME_IH = 11;
    int AL_VOCAL_MORPHER_PHONEME_IY = 12;
    int AL_VOCAL_MORPHER_PHONEME_UH = 13;
    int AL_VOCAL_MORPHER_PHONEME_UW = 14;
    int AL_VOCAL_MORPHER_PHONEME_B = 15;
    int AL_VOCAL_MORPHER_PHONEME_D = 16;
    int AL_VOCAL_MORPHER_PHONEME_F = 17;
    int AL_VOCAL_MORPHER_PHONEME_G = 18;
    int AL_VOCAL_MORPHER_PHONEME_J = 19;
    int AL_VOCAL_MORPHER_PHONEME_K = 20;
    int AL_VOCAL_MORPHER_PHONEME_L = 21;
    int AL_VOCAL_MORPHER_PHONEME_M = 22;
    int AL_VOCAL_MORPHER_PHONEME_N = 23;
    int AL_VOCAL_MORPHER_PHONEME_P = 24;
    int AL_VOCAL_MORPHER_PHONEME_R = 25;
    int AL_VOCAL_MORPHER_PHONEME_S = 26;
    int AL_VOCAL_MORPHER_PHONEME_T = 27;
    int AL_VOCAL_MORPHER_PHONEME_V = 28;
    int AL_VOCAL_MORPHER_PHONEME_Z = 29;
    int AL_VOCAL_MORPHER_WAVEFORM_SINUSOID = 0;
    int AL_VOCAL_MORPHER_WAVEFORM_TRIANGLE = 1;
    int AL_VOCAL_MORPHER_WAVEFORM_SAWTOOTH = 2;
    int AL_VOCAL_MORPHER_MIN_WAVEFORM = 0;
    int AL_VOCAL_MORPHER_MAX_WAVEFORM = 2;
    int AL_VOCAL_MORPHER_DEFAULT_WAVEFORM = 0;
    float AL_VOCAL_MORPHER_MIN_RATE = 0.0f;
    float AL_VOCAL_MORPHER_MAX_RATE = 10.0f;
    float AL_VOCAL_MORPHER_DEFAULT_RATE = 1.41f;
    // Pitch shifter
    int AL_PITCH_SHIFTER_MIN_COARSE_TUNE = -12;
    int AL_PITCH_SHIFTER_MAX_COARSE_TUNE = 12;
    int AL_PITCH_SHIFTER_DEFAULT_COARSE_TUNE = 12;
    int AL_PITCH_SHIFTER_MIN_FINE_TUNE = -50;
    int AL_PITCH_SHIFTER_MAX_FINE_TUNE = 50;
    int AL_PITCH_SHIFTER_DEFAULT_FINE_TUNE = 0;
    // Ring modulator
    float AL_RING_MODULATOR_MIN_FREQUENCY = 0.0f;
    float AL_RING_MODULATOR_MAX_FREQUENCY = 8000.0f;
    float AL_RING_MODULATOR_DEFAULT_FREQUENCY = 440.0f;
    float AL_RING_MODULATOR_MIN_HIGHPASS_CUTOFF = 0.0f;
    float AL_RING_MODULATOR_MAX_HIGHPASS_CUTOFF = 24000.0f;
    float AL_RING_MODULATOR_DEFAULT_HIGHPASS_CUTOFF = 800.0f;
    int AL_RING_MODULATOR_SINUSOID = 0;
    int AL_RING_MODULATOR_SAWTOOTH = 1;
    int AL_RING_MODULATOR_SQUARE = 2;
    int AL_RING_MODULATOR_MIN_WAVEFORM = 0;
    int AL_RING_MODULATOR_MAX_WAVEFORM = 2;
    int AL_RING_MODULATOR_DEFAULT_WAVEFORM = 0;
    // Autowah
    float AL_AUTOWAH_MIN_ATTACK_TIME = 0.0001f;
    float AL_AUTOWAH_MAX_ATTACK_TIME = 1.0f;
    float AL_AUTOWAH_DEFAULT_ATTACK_TIME = 0.06f;
    float AL_AUTOWAH_MIN_RELEASE_TIME = 0.0001f;
    float AL_AUTOWAH_MAX_RELEASE_TIME = 1.0f;
    float AL_AUTOWAH_DEFAULT_RELEASE_TIME = 0.06f;
    float AL_AUTOWAH_MIN_RESONANCE = 2.0f;
    float AL_AUTOWAH_MAX_RESONANCE = 1000.0f;
    float AL_AUTOWAH_DEFAULT_RESONANCE = 1000.0f;
    float AL_AUTOWAH_MIN_PEAK_GAIN = 0.00003f;
    float AL_AUTOWAH_MAX_PEAK_GAIN = 31621.0f;
    float AL_AUTOWAH_DEFAULT_PEAK_GAIN = 11.22f;
    // Compressor
    int AL_COMPRESSOR_MIN_ONOFF = 0;
    int AL_COMPRESSOR_MAX_ONOFF = 1;
    int AL_COMPRESSOR_DEFAULT_ONOFF = 1;
    // Equalizer
    float AL_EQUALIZER_MIN_LOW_GAIN = 0.126f;
    float AL_EQUALIZER_MAX_LOW_GAIN = 7.943f;
    float AL_EQUALIZER_DEFAULT_LOW_GAIN = 1.0f;
    float AL_EQUALIZER_MIN_LOW_CUTOFF = 50.0f;
    float AL_EQUALIZER_MAX_LOW_CUTOFF = 800.0f;
    float AL_EQUALIZER_DEFAULT_LOW_CUTOFF = 200.0f;
    float AL_EQUALIZER_MIN_MID1_GAIN = 0.126f;
    float AL_EQUALIZER_MAX_MID1_GAIN = 7.943f;
    float AL_EQUALIZER_DEFAULT_MID1_GAIN = 1.0f;
    float AL_EQUALIZER_MIN_MID1_CENTER = 200.0f;
    float AL_EQUALIZER_MAX_MID1_CENTER = 3000.0f;
    float AL_EQUALIZER_DEFAULT_MID1_CENTER = 500.0f;
    float AL_EQUALIZER_MIN_MID1_WIDTH = 0.01f;
    float AL_EQUALIZER_MAX_MID1_WIDTH = 1.0f;
    float AL_EQUALIZER_DEFAULT_MID1_WIDTH = 1.0f;
    float AL_EQUALIZER_MIN_MID2_GAIN = 0.126f;
    float AL_EQUALIZER_MAX_MID2_GAIN = 7.943f;
    float AL_EQUALIZER_DEFAULT_MID2_GAIN = 1.0f;
    float AL_EQUALIZER_MIN_MID2_CENTER = 1000.0f;
    float AL_EQUALIZER_MAX_MID2_CENTER = 8000.0f;
    float AL_EQUALIZER_DEFAULT_MID2_CENTER = 3000.0f;
    float AL_EQUALIZER_MIN_MID2_WIDTH = 0.01f;
    float AL_EQUALIZER_MAX_MID2_WIDTH = 1.0f;
    float AL_EQUALIZER_DEFAULT_MID2_WIDTH = 1.0f;
    float AL_EQUALIZER_MIN_HIGH_GAIN = 0.126f;
    float AL_EQUALIZER_MAX_HIGH_GAIN = 7.943f;
    float AL_EQUALIZER_DEFAULT_HIGH_GAIN = 1.0f;
    float AL_EQUALIZER_MIN_HIGH_CUTOFF = 4000.0f;
    float AL_EQUALIZER_MAX_HIGH_CUTOFF = 16000.0f;
    float AL_EQUALIZER_DEFAULT_HIGH_CUTOFF = 6000.0f;

    // Filter parameter ranges and defaults
    // Lowpass
    float LOWPASS_MIN_GAIN = 0.0f;
    float LOWPASS_MAX_GAIN = 1.0f;
    float LOWPASS_DEFAULT_GAIN = 1.0f;
    float LOWPASS_MIN_GAINHF = 0.0f;
    float LOWPASS_MAX_GAINHF = 1.0f;
    float LOWPASS_DEFAULT_GAINHF = 1.0f;
    // Highpass
    float HIGHPASS_MIN_GAIN = 0.0f;
    float HIGHPASS_MAX_GAIN = 1.0f;
    float HIGHPASS_DEFAULT_GAIN = 1.0f;
    float HIGHPASS_MIN_GAINLF = 0.0f;
    float HIGHPASS_MAX_GAINLF = 1.0f;
    float HIGHPASS_DEFAULT_GAINLF = 1.0f;
    // Bandpass
    float BANDPASS_MIN_GAIN = 0.0f;
    float BANDPASS_MAX_GAIN = 1.0f;
    float BANDPASS_DEFAULT_GAIN = 1.0f;
    float BANDPASS_MIN_GAINHF = 0.0f;
    float BANDPASS_MAX_GAINHF = 1.0f;
    float BANDPASS_DEFAULT_GAINHF = 1.0f;
    float BANDPASS_MIN_GAINLF = 0.0f;
    float BANDPASS_MAX_GAINLF = 1.0f;
    float BANDPASS_DEFAULT_GAINLF = 1.0f;
}
