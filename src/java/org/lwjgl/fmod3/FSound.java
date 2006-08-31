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
package org.lwjgl.fmod3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.fmod3.callbacks.FSoundCloseCallback;
import org.lwjgl.fmod3.callbacks.FSoundDSPCallback;
import org.lwjgl.fmod3.callbacks.FSoundMetaDataCallback;
import org.lwjgl.fmod3.callbacks.FSoundOpenCallback;
import org.lwjgl.fmod3.callbacks.FSoundReadCallback;
import org.lwjgl.fmod3.callbacks.FSoundSeekCallback;
import org.lwjgl.fmod3.callbacks.FSoundStreamCallback;
import org.lwjgl.fmod3.callbacks.FSoundTellCallback;

/**
 * <br>
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class FSound {

	/** CE/PS2/GC Only - Non interpolating/low quality mixer. */
	public static final int	FSOUND_MIXER_AUTODETECT											= 0;

	/** Removed / obsolete. */
	public static final int	FSOUND_MIXER_BLENDMODE											= 1;

	/** Removed / obsolete. */
	public static final int	FSOUND_MIXER_MMXP5													= 2;

	/** Removed / obsolete. */
	public static final int	FSOUND_MIXER_MMXP6													= 3;

	/** All platforms - Autodetect the fastest quality mixer based on your cpu. */
	public static final int	FSOUND_MIXER_QUALITY_AUTODETECT							= 4;

	/** Win32/Linux only - Interpolating/volume ramping FPU mixer.  */
	public static final int	FSOUND_MIXER_QUALITY_FPU										= 5;

	/** Win32/Linux only - Interpolating/volume ramping P5 MMX mixer. */
	public static final int	FSOUND_MIXER_QUALITY_MMXP5									= 6;

	/** Win32/Linux only - Interpolating/volume ramping ppro+ MMX mixer. */
	public static final int	FSOUND_MIXER_QUALITY_MMXP6									= 7;

	/** CE/PS2/GC only - MONO non interpolating/low quality mixer. For speed */
	public static final int	FSOUND_MIXER_MONO														= 8;

	/** CE/PS2/GC only - MONO Interpolating mixer.  For speed */
	public static final int	FSOUND_MIXER_QUALITY_MONO										= 9;

	public static final int	FSOUND_MIXER_MAX														= 10;

	/** NoSound driver, all calls to this succeed but do nothing. */
	public static final int	FSOUND_OUTPUT_NOSOUND												= 0;

	/** Windows Multimedia driver. */
	public static final int	FSOUND_OUTPUT_WINMM													= 1;

	/** DirectSound driver.  You need this to get EAX or EAX2 support, or FX api support. */
	public static final int	FSOUND_OUTPUT_DSOUND												= 2;

	/** A3D driver.  not supported any more. */
	public static final int	FSOUND_OUTPUT_A3D														= 3;

	/** Linux/Unix OSS (Open Sound System) driver, i.e. the kernel sound drivers. */
	public static final int	FSOUND_OUTPUT_OSS														= 4;

	/** Linux/Unix ESD (Enlightment Sound Daemon) driver.*/
	public static final int	FSOUND_OUTPUT_ESD														= 5;

	/** Linux Alsa driver. */
	public static final int	FSOUND_OUTPUT_ALSA													= 6;

	/** Low latency ASIO driver */
	public static final int	FSOUND_OUTPUT_ASIO													= 7;

	/** Xbox driver */
	public static final int	FSOUND_OUTPUT_XBOX													= 8;

	/** PlayStation 2 driver */
	public static final int	FSOUND_OUTPUT_PS2														= 9;

	/** Mac SoundMager driver */
	public static final int	FSOUND_OUTPUT_MAC														= 10;

	/** Gamecube driver */
	public static final int	FSOUND_OUTPUT_GC														= 11;

	/** This is the same As nosound, but the sound generation is driven by FSOUND_Update   */
	public static final int	FSOUND_OUTPUT_NOSOUND_NONREALTIME						= 12;

	/** DSP CLEAR unit - done first */
	public static final int	FSOUND_DSP_DEFAULTPRIORITY_CLEARUNIT				= 0;

	/** DSP SFX unit - done second */
	public static final int	FSOUND_DSP_DEFAULTPRIORITY_SFXUNIT					= 100;

	/** DSP MUSIC unit - done third */
	public static final int	FSOUND_DSP_DEFAULTPRIORITY_MUSICUNIT				= 200;

	/** User priority, use this as reference */
	public static final int	FSOUND_DSP_DEFAULTPRIORITY_USER							= 300;

	/** This reads data for FSOUND_DSP_GetSpectrum, so it comes after user units */
	public static final int	FSOUND_DSP_DEFAULTPRIORITY_FFTUNIT					= 900;

	/** DSP CLIP AND COPY unit - last */
	public static final int	FSOUND_DSP_DEFAULTPRIORITY_CLIPANDCOPYUNIT	= 1000;

	/** This driver supports hardware accelerated 3d sound. */
	public static final int	FSOUND_CAPS_HARDWARE												= 0x1;

	/** This driver supports EAX2 reverb */
	public static final int	FSOUND_CAPS_EAX2														= 0x2;

	/** This driver supports EAX3 reverb */
	public static final int	FSOUND_CAPS_EAX3														= 0x10;

	/** For non looping samples. */
	public static final int	FSOUND_LOOP_OFF															= 0x00000001;

	/** For forward looping samples. */
	public static final int	FSOUND_LOOP_NORMAL													= 0x00000002;

	/** For bidirectional looping samples.  (no effect if in hardware). */
	public static final int	FSOUND_LOOP_BIDI														= 0x00000004;

	/** For 8 bit samples. */
	public static final int	FSOUND_8BITS																= 0x00000008;

	/** For 16 bit samples. */
	public static final int	FSOUND_16BITS																= 0x00000010;

	/** For mono samples. */
	public static final int	FSOUND_MONO																	= 0x00000020;

	/** For stereo samples. */
	public static final int	FSOUND_STEREO																= 0x00000040;

	/** For user created source data containing unsigned samples. */
	public static final int	FSOUND_UNSIGNED															= 0x00000080;

	/** For user created source data containing signed data. */
	public static final int	FSOUND_SIGNED																= 0x00000100;

	/** For user created source data stored as delta values. */
	public static final int	FSOUND_DELTA																= 0x00000200;

	/** For user created source data stored using IT214 compression. */
	public static final int	FSOUND_IT214																= 0x00000400;

	/** For user created source data stored using IT215 compression. */
	public static final int	FSOUND_IT215																= 0x00000800;

	/** Attempts to make samples use 3d hardware acceleration. (if the card supports it) */
	public static final int	FSOUND_HW3D																	= 0x00001000;

	/** Tells software (not hardware) based sample not to be included in 3d processing. */
	public static final int	FSOUND_2D																		= 0x00002000;

	/** For a streamimg sound where you feed the data to it. */
	public static final int	FSOUND_STREAMABLE														= 0x00004000;

	/** "name" will be interpreted as a pointer to data for streaming and samples. */
	public static final int	FSOUND_LOADMEMORY														= 0x00008000;

	/** Will ignore file format and treat as raw pcm. */
	public static final int	FSOUND_LOADRAW															= 0x00010000;

	/** For FSOUND_Stream_Open - for accurate FSOUND_Stream_GetLengthMs/FSOUND_Stream_SetTime.  WARNING, see FSOUND_Stream_Open for inital opening time performance issues. */
	public static final int	FSOUND_MPEGACCURATE													= 0x00020000;

	/** For forcing stereo streams and samples to be mono - needed if using FSOUND_HW3D and stereo data - incurs a small speed hit for streams */
	public static final int	FSOUND_FORCEMONO														= 0x00040000;

	/** 2D hardware sounds.  allows hardware specific effects */
	public static final int	FSOUND_HW2D																	= 0x00080000;

	/** Allows DX8 FX to be played back on a sound.  Requires DirectX 8 - Note these sounds cannot be played more than once, be 8 bit, be less than a certain size, or have a changing frequency */
	public static final int	FSOUND_ENABLEFX															= 0x00100000;

	/** For FMODCE only - decodes mpeg streams using a lower quality decode, but faster execution */
	public static final int	FSOUND_MPEGHALFRATE													= 0x00200000;

	/** Contents are stored compressed as IMA ADPCM */
	public static final int	FSOUND_IMAADPCM															= 0x00400000;

	/** For PS2 only - Contents are compressed as Sony VAG format */
	public static final int	FSOUND_VAG																	= 0x00800000;

	/** For FSOUND_Stream_Open/FMUSIC_LoadSong - Causes stream or music to open in the background and not block the foreground app.  See FSOUND_Stream_GetOpenState or FMUSIC_GetOpenState to determine when it IS ready. */
	public static final int	FSOUND_NONBLOCKING													= 0x01000000;

	/** For Gamecube only - Contents are compressed as Gamecube DSP-ADPCM format */
	public static final int	FSOUND_GCADPCM															= 0x02000000;

	/** For PS2 and Gamecube only - Contents are interleaved into a multi-channel (more than stereo) format */
	public static final int	FSOUND_MULTICHANNEL													= 0x04000000;

	/** For PS2 only - Sample/Stream is forced to use hardware voices 00-23 */
	public static final int	FSOUND_USECORE0															= 0x08000000;

	/** For PS2 only - Sample/Stream is forced to use hardware voices 24-47 */
	public static final int	FSOUND_USECORE1															= 0x10000000;

	/** For PS2 only - "name" will be interpreted as a pointer to data for streaming and samples.  The address provided will be an IOP address */
	public static final int	FSOUND_LOADMEMORYIOP												= 0x20000000;

	/** Skips id3v2 etc tag checks when opening a stream, to reduce seek/read overhead when opening files (helps with CD performance) */
	public static final int	FSOUND_IGNORETAGS														= 0x40000000;

	/** Specifies an internet stream */
	public static final int	FSOUND_STREAM_NET														= 0x80000000;

	public static final int	FSOUND_NORMAL																= (FSOUND_16BITS | FSOUND_SIGNED | FSOUND_MONO);

	/* Starts from the current track and plays to end of CD. */
	public static final int	FSOUND_CD_PLAYCONTINUOUS										= 0;

	/* Plays the specified track then stops. */
	public static final int	FSOUND_CD_PLAYONCE													= 1;

	/* Plays the specified track looped, forever until stopped manually. */
	public static final int	FSOUND_CD_PLAYLOOPED												= 2;

	/* Plays tracks in random order */
	public static final int	FSOUND_CD_PLAYRANDOM												= 3;

	/* value to play on any free channel, or to allocate a sample in a free sample slot. */
	public static final int	FSOUND_FREE																	= -1;

	/* value to allocate a sample that is NOT managed by FSOUND or placed in a sample slot. */
	public static final int	FSOUND_UNMANAGED														= -2;

	/* for a channel index , this flag will affect ALL channels available!  Not supported by every function. */
	public static final int	FSOUND_ALL																	= -3;

	/* value for FSOUND_SetPan so that stereo sounds are not played at half volume.  See FSOUND_SetPan for more on this. */
	public static final int	FSOUND_STEREOPAN														= -1;

	/* special 'channel' ID for all channel based functions that want to alter the global FSOUND software mixing output channel */
	public static final int	FSOUND_SYSTEMCHANNEL												= -1000;

	/* special 'sample' ID for all sample based functions that want to alter the global FSOUND software mixing output sample */
	public static final int	FSOUND_SYSTEMSAMPLE													= -1000;

	/* 'EnvSize' affects reverberation decay time */
	public static final int	FSOUND_REVERB_FLAGS_DECAYTIMESCALE					= 0x00000001;

	/* 'EnvSize' affects reflection level */
	public static final int	FSOUND_REVERB_FLAGS_REFLECTIONSSCALE				= 0x00000002;

	/* 'EnvSize' affects initial reflection delay time */
	public static final int	FSOUND_REVERB_FLAGS_REFLECTIONSDELAYSCALE		= 0x00000004;

	/* 'EnvSize' affects reflections level */
	public static final int	FSOUND_REVERB_FLAGS_REVERBSCALE							= 0x00000008;

	/* 'EnvSize' affects late reverberation delay time */
	public static final int	FSOUND_REVERB_FLAGS_REVERBDELAYSCALE				= 0x00000010;

	/* AirAbsorptionHF affects DecayHFRatio */
	public static final int	FSOUND_REVERB_FLAGS_DECAYHFLIMIT						= 0x00000020;

	/* 'EnvSize' affects echo time */
	public static final int	FSOUND_REVERB_FLAGS_ECHOTIMESCALE						= 0x00000040;

	/* 'EnvSize' affects modulation time */
	public static final int	FSOUND_REVERB_FLAGS_MODULATIONTIMESCALE			= 0x00000080;

	/* PS2 Only - Reverb is applied to CORE0 (hw voices 0-23) */
	public static final int	FSOUND_REVERB_FLAGS_CORE0										= 0x00000100;

	/* PS2 Only - Reverb is applied to CORE1 (hw voices 24-47) */
	public static final int	FSOUND_REVERB_FLAGS_CORE1										= 0x00000200;

	public static final int	FSOUND_REVERB_FLAGS_DEFAULT									= (FSOUND_REVERB_FLAGS_DECAYTIMESCALE
																																					| FSOUND_REVERB_FLAGS_REFLECTIONSSCALE
																																					| FSOUND_REVERB_FLAGS_REFLECTIONSDELAYSCALE
																																					| FSOUND_REVERB_FLAGS_REVERBSCALE
																																					| FSOUND_REVERB_FLAGS_REVERBDELAYSCALE
																																					| FSOUND_REVERB_FLAGS_DECAYHFLIMIT
																																					| FSOUND_REVERB_FLAGS_CORE0 | FSOUND_REVERB_FLAGS_CORE1);

  /** A vorbis comment */
  public static final int FSOUND_TAGFIELD_VORBISCOMMENT = 0;
  
  /** Part of an ID3v1 tag */
  public static final int FSOUND_TAGFIELD_ID3V1 = 1;
  
  /** An ID3v2 frame */
  public static final int FSOUND_TAGFIELD_ID3V2 = 2;
  
  /** A SHOUTcast header line */
  public static final int FSOUND_TAGFIELD_SHOUTCAST = 3;
  
  /** An Icecast header line */
  public static final int FSOUND_TAGFIELD_ICECAST = 4;
  
  /** An Advanced Streaming Format header line */
  public static final int FSOUND_TAGFIELD_ASF = 5;  
  
  /** Stream hasn't connected yet */
  public static final int FSOUND_STREAM_NET_NOTCONNECTED = 0;
  
  /** Stream is connecting to remote host */
  public static final int FSOUND_STREAM_NET_CONNECTING = 1;
  
  /** Stream is buffering data */
  public static final int FSOUND_STREAM_NET_BUFFERING = 2;
  
  /** Stream is ready to play */
  public static final int FSOUND_STREAM_NET_READY = 3;
  
  /** Stream has suffered a fatal error */
  public static final int FSOUND_STREAM_NET_ERROR = 4;
  
  public static final int FSOUND_FX_CHORUS 				= 0;
  public static final int FSOUND_FX_COMPRESSOR 			= 1;
  public static final int FSOUND_FX_DISTORTION			= 2;
  public static final int FSOUND_FX_ECHO				= 3;
  public static final int FSOUND_FX_FLANGER				= 4;
  public static final int FSOUND_FX_GARGLE				= 5;
  public static final int FSOUND_FX_I3DL2REVERB			= 6;
  public static final int FSOUND_FX_PARAMEQ				= 7;
  public static final int FSOUND_FX_WAVES_REVERB		= 8;
  public static final int FSOUND_FX_MAX					= 9;
  
  /** Dolby Digital Output (XBOX or PC only). */
  public static final int FSOUND_SPEAKERMODE_DOLBYDIGITAL			= 0;
  
  /** The speakers are headphones. */
  public static final int FSOUND_SPEAKERMODE_HEADPHONES				= 1;
  
  /** The speakers are monaural. */
  public static final int FSOUND_SPEAKERMODE_MONO					= 2;
  
  /** The speakers are quadraphonic.  */
  public static final int FSOUND_SPEAKERMODE_QUAD					= 3;
  
  /** The speakers are stereo (default value). */
  public static final int FSOUND_SPEAKERMODE_STEREO					= 4;
  
  /** The speakers are surround sound. */
  public static final int FSOUND_SPEAKERMODE_SURROUND				= 5;
  
  /** DTS output (XBOX only). */
  public static final int FSOUND_SPEAKERMODE_DTS					= 6;
  
  /** Dolby Prologic 2.  Playstation 2 and Gamecube only.  PlayStation 2 doesnt support interior panning, but supports 48 voices simultaneously. */
  public static final int FSOUND_SPEAKERMODE_PROLOGIC2				= 7;
    
  /* Dolby Prologic 2.  Playstation 2 and Gamecube only.  PlayStation 2 does support interior panning, but only supports 24 voices simultaneously. */
  public static final int FSOUND_SPEAKERMODE_PROLOGIC2_INTERIOR		= 8;
  
  /** Win32 only - Causes MIDI playback to force software decoding. */
  public static final int FSOUND_INIT_USEDEFAULTMIDISYNTH     		= 0x0001;
  
  /** Win32 only - For DirectSound output - sound is not muted when window is out of focus. */
  public static final int FSOUND_INIT_GLOBALFOCUS             		= 0x0002;
  
  /** Win32 only - For DirectSound output - Allows FSOUND_FX api to be used on global software mixer output! (use FSOUND_SYSTEMCHANNEL as channel id) */
  public static final int FSOUND_INIT_ENABLESYSTEMCHANNELFX   		= 0x0004;
  
  /** This latency adjusts FSOUND_GetCurrentLevels, but incurs a small cpu and memory hit */
  public static final int FSOUND_INIT_ACCURATEVULEVELS        		= 0x0008;
  
  /** PS2 only   - Disable reverb on CORE 0 to regain SRAM */
  public static final int FSOUND_INIT_PS2_DISABLECORE0REVERB  		= 0x0010;
  
  /** PS2 only   - Disable reverb on CORE 1 to regain SRAM */
  public static final int FSOUND_INIT_PS2_DISABLECORE1REVERB  		= 0x0020;
  
  /** PS2 only   - By default FMOD uses DMA CH0 for mixing, CH1 for uploads, this flag swaps them around */
  public static final int FSOUND_INIT_PS2_SWAPDMACORES        		= 0x0040;
  
  /** Callbacks are not latency adjusted, and are called at mix time.  Also information functions are immediate */
  public static final int FSOUND_INIT_DONTLATENCYADJUST       		= 0x0080;
  
  /** GC only    - Initializes GC audio libraries */
  public static final int FSOUND_INIT_GC_INITLIBS             		= 0x0100;
  
  /** Turns off fmod streamer thread, and makes streaming update from FSOUND_Update called by the user */
  public static final int FSOUND_INIT_STREAM_FROM_MAIN_THREAD 		= 0x0200;
  
  /** PS2 only   - Turns on volume ramping system to remove hardware clicks. */
  public static final int FSOUND_INIT_PS2_USEVOLUMERAMPING    		= 0x0400;
  
  /** Win32 only - For DirectSound output.  3D commands are batched together and executed at FSOUND_Update. */
  public static final int FSOUND_INIT_DSOUND_DEFERRED         		= 0x0800;
  
  /** Win32 only - For DirectSound output.  FSOUND_HW3D buffers use a slightly higher quality algorithm when 3d hardware acceleration is not present. */
  public static final int FSOUND_INIT_DSOUND_HRTF_LIGHT       		= 0x1000;
  
  /** Win32 only - For DirectSound output.  FSOUND_HW3D buffers use full quality 3d playback when 3d hardware acceleration is not present. */
  public static final int FSOUND_INIT_DSOUND_HRTF_FULL        		= 0x2000;
  
  public static final int FSOUND_PROTOCOL_SHOUTCAST  				= 0x00000001;
  public static final int FSOUND_PROTOCOL_ICECAST    				= 0x00000002;
  public static final int FSOUND_PROTOCOL_HTTP      				= 0x00000004;
  public static final int FSOUND_FORMAT_MPEG         				= 0x00010000;
  public static final int FSOUND_FORMAT_OGGVORBIS    				= 0x00020000; 
  
  

  // Pre Initialization / Initialization / Enumeration
  // ======================================================
	/**
	 * Shuts down the WHOLE FMOD Sound System
	 * <p>
	 * <b>Remarks</b>
	 * This also closes down the sample management system, freeing all MANAGED samples loaded (unless they were allocated with the FSOUND_UNMANAGED flag).
	 * Streams are not freed. You must close them yourself.
	 * CD Tracks are stopped.
	 * </p>
	 */
	public static native void FSOUND_Close();

  /**
   * Specify user callbacks for FMOD's internal file manipulation functions.
   * If ANY of these parameters are NULL, then FMOD will switch back to its own file routines.
   * You can replace this with memory routines (ie name can be cast to a memory address for example, then open sets up
   * a handle based on this information), or alternate file routines, ie a WAD file reader.
   * <p>
   * <b>Remarks</b>
   * Memory loader FMOD functions are not affected, such as FMUSIC_LoadSongMemory etc.
   * WARNING : This function is dangerous in the wrong hands. You must return the right values, and each command must work properly, or FMOD will not function, or it may even crash if you give it invalid data.
   * You must support SEEK_SET, SEEK_CUR and SEEK_END properly, or FMOD will not work properly. See standard I/O help files on how these work under fseek().
   * Read the documentation in REMARKS and do exactly what it says. See the "simple" example for how it is used properly.
   * The MIDI loader does not support user file callbacks. For WAD type data structures with embedded MIDI files FMUSIC_LoadSongMemory will have to be used.
   * --------------
   * PlayStation 2 NOTE! This function takes IOP function pointers, not EE pointers! It is for custom IOP file systems not EE based ones.
   * This function can only be called after FSOUND_Init on PlayStation 2, not before
   * </p>
   * @param open Open callback
   * @param close Close callback
   * @param read Read callback
   * @param seek Seek callback
   * @param tell Tell callback
   */
	public static void FSOUND_File_SetCallbacks(
      FSoundOpenCallback open, FSoundCloseCallback close, FSoundReadCallback read,
      FSoundSeekCallback seek, FSoundTellCallback tell) {
    
    if(open != null && close != null && read != null && seek != null && tell != null) {
    	FMOD.registerCallback(FMOD.FSOUND_OPENCALLBACK, -1, null, open);
    	FMOD.registerCallback(FMOD.FSOUND_CLOSECALLBACK, -1, null, open);
    	FMOD.registerCallback(FMOD.FSOUND_READCALLBACK, -1, null, open);
    	FMOD.registerCallback(FMOD.FSOUND_SEEKCALLBACK, -1, null, open);
    	FMOD.registerCallback(FMOD.FSOUND_TELLCALLBACK, -1, null, open);
    	
    	nFSOUND_File_SetCallbacks();
    } else {
    	throw new IllegalArgumentException("Cannot supply null callback");
    }
  }
  private static native void nFSOUND_File_SetCallbacks();

	/**
	 * Initializes the FMOD Sound System.
	 * <p>
	 * <b>Remarks</b>
	 * You do not have control over how many hardware channels are available to you. In a lot of
	 * cases it may be 0 (the sound card does not have the ability to supply hardware channels).
	 * This is why it is usually a good idea to supply FSOUND_Init with a good number of software
	 * channels to fall back onto, for example 32.
	 * Hardware channels are 3D hardware channels only. There is no benefit in supporting hardware
	 * for 2d playback of sound effects. With todays machines and FMOD's superior mixing routines,
	 * FMOD's software engine can sometimes be faster than the driver's hardware support! 
	 * 
	 * @param mixrate Output rate in hz between 4000 and 65535. Any thing outside this will cause
	 * the function to fail and return false.
	 * PS2 Note. Only rates of 24000 and 48000 are supported.
	 * SmartPhone Note. Use 22050 or the operating system may crash outside of the control of fmod.
	 * @param channels Maximum number of SOFTWARE channels available.
	 * The number of HARDWARE channels is autodetected. The total number of channels available (hardware and software) after initialization can be found with FSOUND_GetMaxChannels. 
	 * Having a large number of maxchannels does not adversely affect cpu usage, but it means it has the POTENTIAL to mix a large number of channels, which can have an adverse effect on cpu usage.
	 * 1024 is the highest number that can be set. Anything higher will return an error.
	 * @param flags See FSOUND_INIT_FLAGS. Controls some global or initialization time aspects of playback
	 * @return On success, true is returned. On failure, false is returned
	 */
	public static native boolean FSOUND_Init(int mixrate, int channels, int flags);

	/**
	 * Sets the FMOD internal mixing buffer size. 
	 * It is configurable because low buffersizes use less memory, but are more instable.
	 * More importantly, increasing buffer size will increase sound output stability, but
	 * on the other hand increases latency, and to some extent, CPU usage.
	 * FMOD chooses the most optimal size by default for best stability, depending on the
	 * output type - and if the drivers are emulated or not (NT). 
	 * It is not recommended changing this value unless you really need to. You may get worse 
	 * performance than the default settings chosen by FMOD.
	 * <p>
	 * <b>Remarks</b>
	 * This function cannot be called after FMOD is already activated with FSOUND_Init.
	 * It must be called before FSOUND_Init, or after FSOUND_Close.
	 * ---------
	 * The buffersize seting defaults to 50ms if it is not called for DSOUND. 
	 * It defaults to 200ms for Windows Multimedia wave-out or for emulated DirectSound drivers (such as NT drivers).
	 * When the output is FSOUND_OUTPUT_ASIO the buffersize is ignored. The buffersize should be configured using the ASIO driver which can be done with the supplied asioconfig.exe in the FMOD SDK.
	 * ---------
	 * Buffer sizes lower than 50 are clamped at 50.
	 * Buffer sizes are also rounded DOWN to the nearest multiple of 25. This is because FMOD mixes in blocks of 25ms.
	 * Due to this buffersize command latency on software channels will be between 25 and 50ms on average (37.5ms) when the buffersize is set to 50.
	 * ---------
	 * Macintosh, PlayStation 2 and GameCube do not support this as they already achieve minimal latency and are forced to 25ms.
	 *
	 * @param len_ms buffer size in milliseconds. 
	 * @return On success, true is returned. On failure, (ie if FMOD is already active) false is returned
	 */
	public static native boolean FSOUND_SetBufferSize(int len_ms);

	/**
	 * Selects a soundcard driver.
	 * It is used when an output mode has enumerated more than one output device, and you need to select between them.
	 * <p>
	 * <b>Remarks</b>
	 * This function cannot be called after FMOD is already activated with FSOUND_Init.
	 * It must be called before FSOUND_Init, or after FSOUND_Close.   
	 * </p>
	 * @param driver Driver number to select. 0 will select the DEFAULT sound driver. 
	 * &gt;0 will select an INVALID driver which will case the DEVICE to be set 
	 * to a null (nosound) driver.
	 * &lt;0 Selects other valid drivers that can be listed with FSOUND_GetDriverName.
	 * @return On success, TRUE is returned. On failure, (ie if FMOD is already active) FALSE is returned.
	 */
	public static native boolean FSOUND_SetDriver(int driver);

	/**
	 * This is an optional function to set the window handle of the application
	 * you are writing, so Directsound can tell if it is in focus or not.
	 * <p>
	 * <b>Remarks</b>
	 * This function cannot be called after FMOD is already activated with FSOUND_Init.
	 * It must be called before FSOUND_Init, or after FSOUND_Close.
	 * ---------
	 * FMOD uses GetForegroundWindow if this function is not called.
	 * @return On success, TRUE is returned. On failure, FALSE is returned.
	 */
  //public static native boolean FSOUND_SetHWND();

	/**
	 * This sets the maximum allocatable channels on a hardware card. FMOD automatically 
	 * detects and allocates the maximum number of 3d hardware channels, so calling this 
	 * will limit that number if it becomes too much
	 * <p>
	 * <b>Remarks</b>
	 * This function cannot be called after FMOD is already activated with FSOUND_Init.
	 * It must be called before FSOUND_Init, or after FSOUND_Close.
	 * ---------
	 * This function has nothing to do with FSOUND_SetMinHardwareChannels, in that this is not a function that forces FMOD into software mixing if a card has a certain number of channels.
	 * This function only sets a limit on hardware channels, so if you card has 96 hardware channels, and you set FSOUND_SetMaxHardwareChannels(10), then you will only have 10 hardware channels to use.
	 * @param max maximum number of hardware channels to allocate, even if the soundcard supports more
	 * @return On success, TRUE is returned. On failure, FALSE is returned
	 */
	public static native boolean FSOUND_SetMaxHardwareChannels(int max);

	//public static native boolean FSOUND_FSOUND_SetMemorySystem(FSoundCallback callback);

	/**
	 * This sets the minimum allowable hardware channels before FMOD drops back to 100 percent software.
	 * This is helpful for minimum spec cards, and not having to guess how many hardware channels
	 * they might have. This way you can guarantee and assume a certain number of channels for
	 * your application and place them all in FSOUND_HW3D without fear of the playsound failing
	 * because it runs out of channels on a low spec card.
	 * <p>
	 * <b>Remarks</b>
	 * As an example, if you set your minimum to 16, you can now safely guarantee that 16 sounds can be played at once that are created with FSOUND_HW3D. 
	 * This way if you do come across a card that only supports 4 channels, it will just drop back to playing ALL sounds in software mode. 
	 * It may sound worse, but at least it doesnt fail on the playsound. (which could sound even worse!)   
	 * ---------
	 * @param min minimum number of hardware channels allowable on a card before it uses the software engine 1004562604f the time
	 * @return On success, TRUE is returned. On failure, FALSE is returned
	 */
	public static native boolean FSOUND_SetMinHardwareChannels(int min);

	/**
	 * Sets a digital mixer type.
	 * <p>
	 * <b>Remarks</b>
	 * This function cannot be called after FMOD is already activated with FSOUND_Init.
	 * It must be called before FSOUND_Init, or after FSOUND_Close.
	 * This function does not nescessarily need to be called, autodetection will select the 
	 * fastest mixer for your machine. It is here if you need to test all mixer types for 
	 * debugging purposes, or a mixer has a feature that the autodetected one doesnt.
	 * (ie low quality mixers or volume ramping)
	 * @param mixer mixer type, see FSOUND_MIXERTYPES for valid parameters and descriptions
	 * @return On success, TRUE is returned. On failure, (ie if FMOD is already active) FALSE is returned
	 */
	public static native boolean FSOUND_SetMixer(int mixer);

	/**
	 * Sets up the soundsystem output mode
	 * <p>
	 * <b>Remarks</b>
	 * This function cannot be called after FMOD is already activated with FSOUND_Init.
	 * It must be called before FSOUND_Init, or after FSOUND_Close.
	 * -------
	 * Under Windows NT - Waveout is FASTER than DirectSound, achieves LOWER latency, AND
	 * is LESS buggy. DirectSound under NT is achieved by emulating waveout, and therefore is
	 * inferior to waveout. Use WAVEOUT under NT.
	 * Under Windows 9x and W2K - DirectSound is faster than waveout and can achieve lower latency.
	 * Use DIRECTSOUND under Win9x and W2K.
	 * -------
	 * If you dont call FSOUND_SetOutput, FMOD will now autodetect DSOUND or WINMM based on the operating system.
	 * </p>
	 * @param output The output system to be used. See FSOUND_OUTPUTTYPES for valid parameters and descriptions. -1 Is autodetect based on operating system
	 * @return On success, TRUE is returned. On failure, (ie if FMOD is already active) FALSE is returned
	 */
	public static native boolean FSOUND_SetOutput(int output);

  // ------------------------------------------------------------
  
  // Global runtime update fucntions
  // ============================================================
  
	/**
	 * Sets the master pan seperation for 2d sound effects
	 * @param pansep The pan scalar. 1.0 means full pan seperation, 0 means mono
	 */
	public static native void FSOUND_SetPanSeperation(float pansep);

	/**
	 * Sets the master volume for any sound effects played. Does not affect music or CD output.
	 * @param volume The volume to set. Valid ranges are from 0 (silent) to 255 (full volume)
	 */
	public static native void FSOUND_SetSFXMasterVolume(int volume);

	/**
	 * Sets the mode for the users speaker setup
	 * <p>
	 * <b>Remarks</b>
	 * Note - Only reliably works with FSOUND_OUTPUT_DSOUND or FSOUND_OUTPUT_XBOX output modes. Other output modes will only interpret FSOUND_SPEAKERMODE_MONO and set everything else to be stereo.
	 * ----------------------------------
	 * To get true 5.1 dolby digital or DTS output you will need a soundcard that can encode it, and a receiver that can decode it. 
	 * If not the results can be unpredictable.
	 * ----------------------------------
	 * Calling this will reset the pan separation setting. It sets it to 0 if FSOUND_SPEAKERMODE_MONO is chosen, and 1 otherwise. 
	 * You will need to reset the pan separation if required afterwards.
	 * Note that some soundcard drivers may ignore this call.
	 * ----------------------------------
	 * XBOX only - This function MUST be called before FSOUND_Init to change the default speaker mode. To change on the fly, you must close down FMOD with FSOUND_Close then re-initialize it with FSOUND_Init.
	 * If it is called after FSOUND_Init, only headphone speakermode is interpreted to switch headphone mode on and off.
	 * ----------------------------------
	 * PlayStation 2 only - This function must be called before playing sounds. Calling this after playing a sound will not make that existing sound work in Prologic 2.
	 *
	 * @param speakermode enum describing the users speaker setup
	 */
	public static native void FSOUND_SetSpeakerMode(int speakermode);

	/**
	 * This updates the 3d sound engine and DMA engine (only on some platforms), and should be called once a game frame.
	 * This function will also update the software mixer if you have selected FSOUND_OUTPUT_NOSOUND_NONREALTIME as your output mode
	 */
	public static native void FSOUND_Update();

  // -------------------------------------------------------------
  
  // Global runtime informaton functions
  // =============================================================
  /**
   * Returns in percent of cpu time the amount of cpu usage that FSOUND/FMUSIC mixing is taking
   * <p>
   * <b>Remarks</b>
   * This value represents the cpu usage used by streams, the software mixer, and subsequent calls to dsound waveout etc.
   * MIDI playback is not counted as it is performed by directx.
   * </p>
   * 
   * @return percent of cpu time the amount of cpu usage that FSOUND/FMUSIC mixing is taking
   */
  public static native float FSOUND_GetCPUUsage();
  
  /**
   * Returns the number of active channels in FSOUND, or ones that are playing
   * @return number of active channels in FSOUND, or ones that are playing
   */
  public static native int FSOUND_GetChannelsPlaying();

  /**
   * Returns the currently selected driver number. Drivers are enumerated when selecting a driver 
   * with FSOUND_SetDriver or other driver related functions such as FSOUND_GetNumDrivers or 
   * FSOUND_GetDriverName
   * @return currently selected driver number.
   */
  public static native int FSOUND_GetDriver();

  /**
   * Returns information on capabilities of the current output mode
   *  
   * @param driverid Enumerated driver ID. This must be in a valid range delimited by FSOUND_GetNumDrivers
   * @param caps IntBuffer to have the caps bits stored
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static boolean FSOUND_GetDriverCaps(int driverid, IntBuffer caps) {
    return nFSOUND_GetDriverCaps(driverid, caps, caps.position());
  }
  private static native boolean nFSOUND_GetDriverCaps(int driverid, IntBuffer caps, int offset);

  /**
   * Returns the name of the selected driver. Drivers are enumerated when selecting a driver with
   * FSOUND_SetDriver or other driver related functions such as FSOUND_GetNumDrivers or 
   * FSOUND_GetDriver
   * <p>
   * <b>Remarks</b>
   * If no driver is selected, the default driver is used.
   * </p>
   * 
   * @param driverid Enumerated driver ID. This must be in a valid range delimited by FSOUND_GetNumDrivers
   * @return On success, a String containing the name of the specified device is returned. 
   * The number of drivers enumerated can be found with FSOUND_GetNumDrivers. On failure, NULL is returned.
   */
  public static native String FSOUND_GetDriverName(int driverid);

  /**
   * Returns an error code set by FMOD
   * 
   * @return error code, see FMOD_ERRORS
   */
  public static native int FSOUND_GetError();

  /**
   * Returns the current maximum index for a sample. This figure grows as you allocate more
   * samples (in blocks)
   * @return Maximum sample index
   */
  public static native int FSOUND_GetMaxSamples();

  /**
   * Returns the total number of channels allocated
   * @return Number of channels allocated
   */
  public static native int FSOUND_GetMaxChannels();

  /**
   * Returns information on the memory usage of fmod. This is useful for determining a fixed memory size to 
   * make FMOD work within for fixed memory machines such as pocketpc and consoles
   * <p>
   * <b>Remarks</b>
   * Note that if using FSOUND_SetMemorySystem, the memory usage will be slightly higher than without it, as fmod has to have a small amount of memory overhead to manage the available memory.
   * </p>
   * @param currentallocated_maxallocated IntBuffer to store Currently allocated memory at time of call and
   * Maximum allocated memory since FSOUND_Init or FSOUND_SetMemorySystem
   */
  public static void FSOUND_GetMemoryStats(IntBuffer currentallocated_maxallocated) {
    nFSOUND_GetMemoryStats(currentallocated_maxallocated, currentallocated_maxallocated.position());
  }
  private static native void nFSOUND_GetMemoryStats(IntBuffer currentallocated_maxallocated, int offset);

  /**
   * Returns the number of sound cards or devices enumerated for the current output type. (Direct
   * Sound, WaveOut etc
   * @return Total number of enumerated sound devices
   */
  public static native int FSOUND_GetNumDrivers();

  /**
   * Returns the number of available hardware mixed 2d and 3d channels
   * @param twoD_threeD_channels_total IntBuffer to store number of available hardware mixed 2d channels, 
   * number of available hardware mixed 3d channels and the total (Usually num2d + num3d, but on some 
   * platforms like PS2 and GameCube, this will be the same as num2d and num3d (and not the sum) because 2d and 3d voices share the same pool)
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static boolean FSOUND_GetNumHWChannels(IntBuffer twoD_threeD_channels_total) {
    return nFSOUND_GetNumHWChannels(twoD_threeD_channels_total, twoD_threeD_channels_total.position());
  }
  private static native boolean nFSOUND_GetNumHWChannels(IntBuffer twoD_threeD_channels_total, int offset);

  /**
   * Returns the current id to the output type.
   * See FSOUND_OUTPUTTYPES for valid parameters and descriptions
   * @return id to output type
   */
  public static native int FSOUND_GetOutput();

  //public static native int FSOUND_GetOutPutHandle();
  
  /**
   * Returns the current mixing rate 
   * @return Currently set output rate in Hz
   */
  public static native int FSOUND_GetOutputRate();

  /**
   * Returns the master volume for any sound effects played.
   * It specifically has SFX in the function name, as it does not affect music or CD volume.
   * This must also be altered with FMUSIC_SetMasterVolume
   * @return On success, the SFX master volume is returned. Valid ranges are from 0 (silent) to 255 (full volume)
   */
  public static native int FSOUND_GetSFXMasterVolume();

  /**
   * Returns the FMOD version number
   * <p>
   * <b>Remarks</b>
   * Use this to compare the header you are using against the compiled DLL version to make sure your
   * DLL is up to date.
   * </p>
   * @return FMOD version number
   */
  public static native float FSOUND_GetVersion();
  
  // ----------------------------------------------------------
  
  // Sample functions 
  // ==========================================================
	/**
	 * Allocates a new empty sample. Used if you want to create a sample from scratch and fill the databuffer with your own data (using FSOUND_Sample_Lock or FSOUND_Sample_Upload), instead of just loading a file with FSOUND_Sample_Load.
	 * <p>
	 * <b>Remarks</b>
	 * FMOD has a sample management system that holds onto any samples loaded or allocated, and
	 * frees them all when you call FSOUND_Close. It takes the hassle out of having to keep hold
	 * of a lot of sample handles and remember to free them all at the end of your application.
	 * It is basically an expandle array of handles that holds each sample until FMOD closes down where it does
	 * a cleanup. FSOUND_UNMANAGED can be used NOT to use the sample management system.
	 * ------------
	 * FSOUND_Sample_Alloc is only nescessary for lower level operations with sample data. Usually 
	 * FSOUND_Load does the work for you. lower level operations mean such things as uploading data from memory or 
	 * your own compressed data for example.
	 * You can create a new sample from scratch by doing the following operations
	 * 1. Allocate a new sample with FSOUND_Sample_Alloc
	 * 2. Write data to the sample buffer with FSOUND_Sample_Lock and FSOUND_Sample_Unlock, or 
	 * FSOUND_Sample_Upload.
	 * Note FSOUND_Sample_Lock only returns a pointer to the sample data, whereas 
	 * FSOUND_Sample_Upload does a copy from data you give it, with format conversion to the 
	 * correct format.
	 * 
	 * @param index Sample pool index. See remarks for more on the sample pool.
	 * 0 or above - The absolute index into fsounds sample pool. The pool will grow as 
	 * the index gets larger. If a slot is already used it will be replaced.
	 * FSOUND_FREE - Let FSOUND select an arbitrary sample slot. 
	 * FSOUND_UNMANAGED - Dont have fsound free this sample upon FSOUND_Close
	 * @param length The length in of the sample buffer in SAMPLES
	 * @param mode Bitfield describing various characteristics of the sample. Valid parameters are 
	 * described in FSOUND_MODES
	 * @param deffreq Default frequency for this sample
	 * @param defvol Default volume for this sample
	 * @param defpan Default pan for this sample
	 * @param defpri Default priority for this sample
	 * @return On success, a reference to an allocated sample is returned. On failure, NULL is returned
	 */
	public static FSoundSample FSOUND_Sample_Alloc(int index, int length, int mode, int deffreq, int defvol, int defpan, int defpri) {
		long result = nFSOUND_Sample_Alloc(index, length, mode, deffreq, defvol, defpan, defpri);
		return (result != 0) ? new FSoundSample(result, null) : null;
	}
	private static native long nFSOUND_Sample_Alloc(int index, int length, int mode, int deffreq, int defvol, int defpan, int defpri);

	/**
	 * Removes a sample from memory and makes its slot available again
	 * @param sample sample definition to be freed
	 */
	public static void FSOUND_Sample_Free(FSoundSample sample) {
		sample.release();
		nFSOUND_Sample_Free(sample.sampleHandle);
	}
	private static native void nFSOUND_Sample_Free(long sample);

	/**
	 * Returns a reference to a managed sample based on the index passed
	 * <p>
	 * <b>Remarks</b>
	 * Samples that are not created with FSOUND_UNMANAGED are stored in a table inside FMOD.
	 * This way when FMOD can free all samples when FSOUND_Close is called and the user doesnt have to worry about cleaning up memory.
	 * 
	 * @param sampno index in the sample management pool of the requested sample
	 * @return Reference to a sample
	 */
	public static FSoundSample FSOUND_Sample_Get(int sampno) {
		long result = nFSOUND_Sample_Get(sampno);
		return (result != 0) ? new FSoundSample(result, null) : null;
	}

	private static native long nFSOUND_Sample_Get(int sampno);

	/**
	 * Returns the default volume, frequency, pan and priority values for the specified sample
	 * <p>
	 * <b>Remarks</b>
	 * Passing NULL in any of these parameters will result in the value being ignored
	 * </p>
	 * 
	 * @param sample sample to get the default information from
	 * @param deffreq IntBuffer to be filled with the sample default frequency. Can be NULL
	 * @param defvol IntBuffer to be filled with the sample default volume. Can be NULL
	 * @param defpan IntBuffer to be filled with the sample default pan. Can be NULL
	 * @param defpri IntBuffer to be filled with the sample default priority. Can be NULL
	 * @return On success, TRUE is returned. On failure, FALSE is returned
	 */
	public static boolean FSOUND_Sample_GetDefaults(FSoundSample sample, IntBuffer deffreq, IntBuffer defvol,
			IntBuffer defpan, IntBuffer defpri) {
		return nFSOUND_Sample_GetDefaults(sample.sampleHandle, deffreq, (deffreq == null) ? 0 : deffreq.position(), defvol,
				(defvol == null) ? 0 : defvol.position(), defpan, (defpan == null) ? 0 : defpan.position(), defpri,
				(defpri == null) ? 0 : defpri.position());
	}

	private static native boolean nFSOUND_Sample_GetDefaults(long sample, IntBuffer deffreq, int deffreqOffset,
			IntBuffer defvol, int defvolOffset, IntBuffer defpan, int defpanOffset, IntBuffer defpri, int defpriOffset);

	/**
	 * Returns the default volume, frequency, pan, priority and random playback variations for the specified sample
	 * <p>
	 * <b>Remarks</b>
	 * Passing NULL in any of these parameters will result in the value being ignored
	 * </p>
	 * 
	 * @param sample sample to get the default information from
	 * @param deffreq IntBuffer to be filled with the sample default frequency. Can be NULL
	 * @param defvol IntBuffer to be filled with the sample default volume. Can be NULL
	 * @param defpan IntBuffer to be filled with the sample default pan. Can be NULL
	 * @param defpri IntBuffer to be filled with the sample default priority. Can be NULL
	 * @param varfreq IntBuffer to be filled with the sample random frequency variance. Can be NULL
	 * @param varvol IntBuffer to be filled with the sample random volume variance. Can be NULL
	 * @param varpan IntBuffer to be filled with the sample random pan variance. Can be NULL.
	 * @return On success, TRUE is returned. On failure, FALSE is returned
	 */
	public static boolean FSOUND_Sample_GetDefaultsEx(FSoundSample sample, IntBuffer deffreq, IntBuffer defvol,
			IntBuffer defpan, IntBuffer defpri, IntBuffer varfreq, IntBuffer varvol, IntBuffer varpan) {
		return nFSOUND_Sample_GetDefaultsEx(sample.sampleHandle, deffreq, (deffreq == null) ? 0 : deffreq.position(),
				defvol, (defvol == null) ? 0 : defvol.position(), defpan, (defpan == null) ? 0 : defpan.position(), defpri,
				(defpri == null) ? 0 : defpri.position(), varfreq, (varfreq == null) ? 0 : varfreq.position(), varvol,
				(varvol == null) ? 0 : varvol.position(), varpan, (varpan == null) ? 0 : varpan.position());
	}
	private static native boolean nFSOUND_Sample_GetDefaultsEx(long sample, IntBuffer deffreq, int deffreqOffset,
			IntBuffer defvol, int defvolOffset, IntBuffer defpan, int defpanOffset, IntBuffer defpri, int defpriOffset,
			IntBuffer varfreq, int varfreqOffset, IntBuffer varvol, int varvolOffset, IntBuffer varpan, int varpanOffset);

	/**
	 * Returns the length of the sample in SAMPLES
	 * @param sample sample to get the length from
	 * @return On success, the length of sample in SAMPLES is returned. On failure, 0 is returned
	 */
	public static int FSOUND_Sample_GetLength(FSoundSample sample) {
		return nFSOUND_Sample_GetLength(sample.sampleHandle);
	}
	private static native int nFSOUND_Sample_GetLength(long sample);
  
  /**
   * Returns the start and end positions of the specified sample loop
   * in SAMPLES (not bytes)
   * <p>
   * <b>Remarks</b>
   * Passing NULL in any of these parameters will result in the value being ignored.
   * @param sample sample to get the loop point information from
   * @param loopstart IntBuffer to be filled with the sample loop start point. Can be NULL
   * @param loopend IntBuffer to be filled with the sample loop end point. Can be NULL
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static int FSOUND_Sample_GetLoopPoints(FSoundSample sample, IntBuffer loopstart, IntBuffer loopend) {
  	return nFSOUND_Sample_GetLoopPoints(sample.sampleHandle, loopstart, (loopstart == null) ? 0 : loopstart.position(), loopend, (loopend == null) ? 0 : loopend.position());
  }
  private static native int nFSOUND_Sample_GetLoopPoints(long sample, IntBuffer loopstart, int loopstartOffset, IntBuffer loopend, int loopendOffset);
  
  /**
   * Get the minimum and maximum audible distance for a sample
   * <p>
   * <b>Remarks</b>
   * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. 
   * See FSOUND_3D_SetDistanceFactor for more on this.
   * The default units for minimum and maximum distances are 1.0 and 1000000000.0f.
   * Volume drops off at mindistance / distance.
   *
   * @param sample sample to get the distance information from
   * @param min FloatBuffer to be filled with the sample loop start point. Can be NULL
   * @param max FloatBuffer to be filled with the sample loop end point. Can be NULL
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static int FSOUND_Sample_GetMinMaxDistance(FSoundSample sample, FloatBuffer min, FloatBuffer max) {
    return nFSOUND_Sample_GetMinMaxDistance(sample.sampleHandle, min, (min == null) ? 0 : min.position(), max, (max == null) ? 0 : max.position());
  }
  private static native int nFSOUND_Sample_GetMinMaxDistance(long sample, FloatBuffer min, int minOffset, FloatBuffer max, int maxOffset);

  /**
   * Returns a bitfield containing information about the specified sample. 
   * The values can be bitwise AND'ed with the values contained in FSOUND_MODES to see if certain criteria are true or not. 
   * Information that can be retrieved from the same in this field are loop type, bitdepth and stereo/mono.
   *
   * @param sample sample to get the mode information from
   * @return On success, the sample mode is returned. On failure, 0 is returned.
   */
  public static int FSOUND_Sample_GetMode(FSoundSample sample) {
    return nFSOUND_Sample_GetMode(sample.sampleHandle);
  }
  private static native int nFSOUND_Sample_GetMode(long sample);
  
  /**
   * Returns a string containing the sample's name
   *
   * @param sample sample to get the loop point information from
   * @return On success, the name of the sample is returned. On failure, NULL is returned.
   */
  public static String FSOUND_Sample_GetName(FSoundSample sample) {
    return nFSOUND_Sample_GetName(sample.sampleHandle);
  }
  private static native String nFSOUND_Sample_GetName(long sample);  
  
  /**
   * Loads and decodes a static soundfile into memory.
   * This includes such files as .WAV, .MP2, .MP3, .OGG, .RAW and others.
   * <p>
   * <b>Remarks
   * FMOD has a sample management system that holds onto any samples loaded or allocated, and frees them all when you call FSOUND_Close. 
   * It takes the hassle out of having to keep hold of a lot of sample handles and remember to free them all at the end of your application.
   * It is basically an expandle array of handles that holds each sample until FMOD closes down where it does a cleanup. 
   * FSOUND_UNMANAGED can be used so FMOD does NOT use the sample management system. You have to make sure they are freed yourself.
   * --------
   * Specify FSOUND_LOADMEMORY to load a file from a memory image. 
   * The pointer you pass to name must be the actual image of the data you want to load.
   * The length parameter is to be filled out if FSOUND_LOADMEMORY is specified, otherwise if you do not specify memory loading, can be safely ignored and should be set to 0.
   * --------
   * Compressed formats are expanded into memory. If the file is quite large, it could take a while to load.
   * --------
   * If FSOUND_8BITS is specified and the file decodes to 16bit normally, FMOD will downgrade the sample to 8bit.
   * --------
   * On PlayStation 2, the name_or_data pointer and length variables must be 16 byte aligned, for DMA reasons.
   * --------
   * Note that FSOUND_NONBLOCKING is NOT supported with this function.
   * </p> 
   * @param index Sample pool index. See remarks for more on the sample pool.
   * 0 or above - The absolute index into the sample pool. The pool will grow as the index gets larger. If a slot is already used it will be replaced.
   * FSOUND_FREE - Let FSOUND select an arbitrary sample slot.
   * FSOUND_UNMANAGED - Dont have this sample managed within fsounds sample management system
   * @param data ByteBuffer of memory image to load.
   * @param inputmode Description of the data format, OR in the bits defined in FSOUND_MODES to describe the data being loaded.
   * @return On success, a sample is returned. On failure, NULL is returned.
   */
  public static FSoundSample FSOUND_Sample_Load(int index, ByteBuffer data, int inputmode) {
    long result = nFSOUND_Sample_Load(index, data, inputmode, data.position(), data.remaining());
    if(result != 0) {
     return new FSoundSample(result, data); 
    }
    return null;
  }
  
  /**
   * @see #FSOUND_Sample_Load(int, ByteBuffer, int, int, int)
   * @param index Sample pool index. See remarks for more on the sample pool.
   * 0 or above - The absolute index into the sample pool. The pool will grow as the index gets larger. If a slot is already used it will be replaced.
   * FSOUND_FREE - Let FSOUND select an arbitrary sample slot.
   * FSOUND_UNMANAGED - Dont have this sample managed within fsounds sample management system
   * @param name Name of sound file.
   * @param inputmode Description of the data format, OR in the bits defined in FSOUND_MODES to describe the data being loaded.
   * @param offset Optional. 0 by default. If > 0, this value is used to specify an offset in a file, so fmod will seek before opening. length must also be specified if this value is used.
   * @param length Optional. 0 by default. If > 0, this value is used to specify the length of a memory block when using FSOUND_LOADMEMORY, or it is the length of a file or file segment if the offset parameter is used. On PlayStation 2 this must be 16 byte aligned for memory loading.
   * @return On success, a sample is returned. On failure, NULL is returned.
   */
  public static FSoundSample FSOUND_Sample_Load(int index, String name, int inputmode, int offset, int length) {
    long result = nFSOUND_Sample_Load(index, name, inputmode, offset, length);
    if(result != 0) {
     return new FSoundSample(result, null); 
    }
    return null;
  }  
  private static native long nFSOUND_Sample_Load(int index, ByteBuffer data, int inputmode, int offset, int length);    
  private static native long nFSOUND_Sample_Load(int index, String name, int inputmode, int offset, int length);
  
  /**
   * Returns a reference to the beginning of the sample data for a sample.
   * Data written must be signed.
   * <b>NOTE:</b> This method creates 2 direct buffers on the native side, that maps to 
   * the sample data. Calling this method excessively might hurt performance.
   * <p>
   * <b>Remarks</b>
   * You must always unlock the data again after you have finished with it, using FSOUND_Sample_Unlock.
   * For PCM based samples, data must be signed 8 or 16bit. For compressed samples such as those created with FSOUND_IMAADPCM, FSOUND_VAG, FSOUND_GCADPCM, the data must be in its original compressed format.
   * On PlayStation 2, with FSOUND_HW2D or FSOUND_HW3D based samples, this function does not return a readable or writable buffer, it returns the SPU2 address of the sample. To send data to it you must call FSOUND_SendData.
   * On GameCube, with FSOUND_HW2D or FSOUND_HW3D based samples, this function will not return the data contained within the sample. It is for upload purposes only.
   * </p>
   *
   * @param sample sample definition
   * @param offset Offset in BYTES to the position you want to lock in the sample buffer.
   * @param length Number of BYTES you want to lock in the sample buffer.
   * @param lock lock object to contain lock info
   * @return On success, true is is returned. On failure, false is returned.
   */
  public static boolean FSOUND_Sample_Lock(FSoundSample sample, int offset, int length, FSoundSampleLock lock) {
    // reset before entering lock
    lock.set(null, null, 0, 0);
    
    return nFSOUND_Sample_Lock(sample.sampleHandle, offset, length, lock);
  }
  private static native boolean nFSOUND_Sample_Lock(long sample, int offset, int length, FSoundSampleLock lock);
  
  /**
   * Sets a sample's default attributes, so when it is played it uses these values without having to specify them later.
   *
   * @param sample sample to have its attributes set
   * @param deffreq Default sample frequency. The value here is specified in hz. -1 to ignore.
   * @param defvol Default sample volume. This is a value from 0 to 255. -1 to ignore.
   * @param defpan Default sample pan position. This is a value from 0 to 255 or FSOUND_STEREOPAN.
   * @param defpri Default sample priority. This is a value from 0 to 255. -1 to ignore. 
   *
   * @return On success, true is is returned. On failure, false is returned.
   */
  public static boolean FSOUND_Sample_SetDefaults(FSoundSample sample, int deffreq, int defvol, int defpan, int defpri) {
    return nFSOUND_Sample_SetDefaults(sample.sampleHandle, deffreq, defvol, defpan, defpri);
  }
  private static native boolean nFSOUND_Sample_SetDefaults(long sample, int deffreq, int defvol, int defpan, int defpri);
  
  /**
   * Sets a sample's default attributes, so when it is played it uses these values without having to specify them later.
   * <p>
   * <b>Remarks</b>
   * Frequency, volume and pan variation values specify a +/- variation to the 
   * specified default frequency, volume and pan values i.e. with deffreq=44100, 
   * varfreq=2000 the actual frequency value used will be in the range 42100 -> 46100.
   * </p>
   * 
   * @param sample sample to have its attributes set
   * @param deffreq Default sample frequency. The value here is specified in hz. -1 to ignore.
   * @param defvol Default sample volume. This is a value from 0 to 255. -1 to ignore.
   * @param defpan Default sample pan position. This is a value from 0 to 255 or FSOUND_STEREOPAN.
   * @param defpri Default sample priority. This is a value from 0 to 255. -1 to ignore.
   * @param varfreq Frequency variation in hz to apply to deffreq each time this sample is played. -1 to ignore.
   * @param varvol Volume variation to apply to defvol each time this sample is played. -1 to ignore.
   * @param varpan Pan variation to apply to defpan each time this sample is played. -1 to ignore. 
   *
   * @return On success, true is is returned. On failure, false is returned.
   */
  public static boolean FSOUND_Sample_SetDefaultsEx(FSoundSample sample, int deffreq, int defvol, int defpan, int defpri, int varfreq, int varvol, int varpan) {
    return nFSOUND_Sample_SetDefaultsEx(sample.sampleHandle, deffreq, defvol, defpan, defpri, varfreq, varvol, varpan);
  }
  private static native boolean nFSOUND_Sample_SetDefaultsEx(long sample, int deffreq, int defvol, int defpan, int defpri, int varfreq, int varvol, int varpan);
    
  /**
   * Sets the maximum number of times a sample can play back at once
   *
   * @param sample sample to have its playback behaviour changed
   * @param max maximum number of times a sample can play back at once
   * @return On success, true is is returned. On failure, false is returned.
   */
  public static boolean FSOUND_Sample_SetMaxPlaybacks(FSoundSample sample, int max) {
    return nFSOUND_Sample_SetMaxPlaybacks(sample.sampleHandle, max);
  }
  private static native boolean nFSOUND_Sample_SetMaxPlaybacks(long sample, int max);

  /**
   * Sets the minimum and maximum audible distance for a sample.
   * MinDistance is the minimum distance that the sound emitter will cease to continue growing 
   * louder at (as it approaches the listener). Within the mindistance it stays at the constant loudest volume possible. Outside of this mindistance it begins to attenuate.
   * MaxDistance is the distance a sound stops attenuating at. Beyond this point it will stay at the volume it would be at maxdistance units from the listener and will not attenuate any more.
   * MinDistance is useful to give the impression that the sound is loud or soft in 3d space. An example of this is a small quiet object, such as a bumblebee, which you could set a mindistance of to 0.1 for example, which would cause it to attenuate quickly and dissapear when only a few meters away from the listener.
   * Another example is a jumbo jet, which you could set to a mindistance of 100.0, which would keep the sound volume at max until the listener was 100 meters away, then it would be hundreds of meters more before it would fade out.
   * -------
   * In summary, increase the mindistance of a sound to make it 'louder' in a 3d world, and 
   * decrease it to make it 'quieter' in a 3d world.
   * maxdistance is effectively obsolete unless you need the sound to stop fading out at a certain point. Do not adjust this from the default if you dont need to.
   * Some people have the confusion that maxdistance is the point the sound will fade out to, this is not the case
   * <p>
   * <b>Remarks</b>
   *  A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. 
   *  See FSOUND_3D_SetDistanceFactor for more on this.
   *  The default units for minimum and maximum distances are 1.0 and 1000000000.0f.
   *  Volume drops off at mindistance / distance.
   * </p>
   *
   * @param sample sample to have its minimum and maximum distance set
   * @param min The samples minimum volume distance in "units". See remarks for more on units.
   * @param max The samples maximum volume distance in "units". See remarks for more on units.
   * @return On success, true is is returned. On failure, false is returned.
   */
  public static boolean FSOUND_Sample_SetMinMaxDistance(FSoundSample sample, float min, float max) {
    return nFSOUND_Sample_SetMinMaxDistance(sample.sampleHandle, min, max);
  }
  private static native boolean nFSOUND_Sample_SetMinMaxDistance(long sample, float min, float max);

  /**
   * Sets a sample's mode. This can only be FSOUND_LOOP_OFF,FSOUND_LOOP_NORMAL, FSOUND_LOOP_BIDI or FSOUND_2D.
   * You cannot change the description of the contents of a sample or its location. FSOUND_2D will be ignored on the Win32 platform if FSOUND_HW3D was used to create the sample.
   * 
   * <p>
   * <b>Remarks</b>
   * Only the following modes are accepted, others will be filtered out. 
   * FSOUND_LOOP_BIDI, FSOUND_LOOP_NORMAL, FSOUND_LOOP_OFF, FSOUND_2D.
   * Normally FSOUND_2D is accepted only if the sound is software mixed. If this is not set, the mode is set for the sample to be 3D processed.
   * -------------------
   * On Playstation 2, XBox and GameCube, FSOUND_HW2D and FSOUND_HW3D are supported, so you can change between the 2 at runtime.
   * -------------------
   * On Windows, samples created with FSOUND_HW3D or FSOUND_HW2D do not support FSOUND_LOOP_BIDI. This is a limitation of Direct X.   * </p>
   *
   * @param sample sample to have the mode set
   * @param mode mode bits to set from FSOUND_MODES
   * @return On success, true is is returned. On failure, false is returned.
   */
  public static boolean FSOUND_Sample_SetMode(FSoundSample sample, int mode) {
    return nFSOUND_Sample_SetMode(sample.sampleHandle, mode);
  }
  private static native boolean nFSOUND_Sample_SetMode(long sample, int mode);  
  
  /**
   * Sets a sample's loop points, specified in SAMPLES, not bytes
   * <p>
   * <b>Remarks</b>
   * Samples created with FSOUND_HW3D and FSOUND_HW2D under the FSOUND_OUTPUT_DSOUND output mode do not support this function. 
   * Loop points set on such a sample with be ignored, and the sample will loop in its entirety. This is a limitation of DirectSound.
   * On XBOX, GameCube and Playstation 2 hardware voices using compressed data (ie XADPCM, VAG or GCADPCM), these values will not be sample accurate, but will be rounded to the nearest compression block size.
   * On PlayStation 2, the loopend is ignored. The hardware cannot change the end address, so the loopend is always equivalent to length - 1 no matter what you set.   *
   * @param sample sample to have its loop points set
   * @param loopstart The starting position of the sample loop
   * @param loopend The end position of the sample loop 
   * @return On success, true is is returned. On failure, false is returned.
   */
  public static boolean nFSOUND_Sample_SetLoopPoints(FSoundSample sample, int loopstart, int loopend) {
    return nFSOUND_Sample_SetLoopPoints(sample.sampleHandle, loopstart, loopend);
  }
  private static native boolean nFSOUND_Sample_SetLoopPoints(long sample, int loopstart, int loopend);    
  
  /**
   * Releases previous sample data lock from FSOUND_Sample_Lock
   *
   * @param sample sample definition
   * @param lock lock object that contains lock info
   * @return On success, true is is returned. On failure, false is returned.
   */
  public static boolean FSOUND_Sample_Unlock(FSoundSample sample, FSoundSampleLock lock) {
    return nFSOUND_Sample_Unlock(sample.sampleHandle, lock.getPtr1(), lock.getPtr2(), lock.getLen1(), lock.getLen2());
  }
  private static native boolean nFSOUND_Sample_Unlock(long sample, ByteBuffer ptr1, ByteBuffer ptr2, int len1, int len2);  


  /**
   * This function uploads new sound data from memory to a preallocated/existing sample and does conversion based on the specified source mode.
   * If sample data already exists at this handle then it is replaced with the new data being uploaded
   * <p>
   * <b>Remarks</b>
   * Note that on PlayStation 2 the source data address is an IOP address not an EE address. 
   * To get data from EE RAM to the sample you must allocate some IOP memory, dma it to IOP memory then call upload. There are helper functions in fmodps2.h to achieve this.
   * </p>
   *
   * @param sample the destination sample
   * @param srcdata ByteBuffer to the source data to be uploaded. On PlayStation 2 this is an IOP address not an EE address.
   * @param mode Description of the source data format. Bitwise OR in these bits to describe the data being passed in.
   * See FSOUND_MODES for valid parameters and descriptions.
   * FSOUND_HW3D, FSOUND_HW2D and FSOUND_LOOP modes are ignored, the mode describes the source format, not the destination format. 
   *
   * @return On success, true is is returned. On failure, false is returned.
   */
  public static boolean FSOUND_Sample_Upload(FSoundSample sample, ByteBuffer srcdata, int mode) {
    return nFSOUND_Sample_Upload(sample.sampleHandle, srcdata, srcdata.position(), mode);
  }
  private static native boolean nFSOUND_Sample_Upload(long sample, ByteBuffer srcdata, int srcdataOffset, int mode);
  // ----------------------------------------------------------
  
  // Channel functions
  // ==========================================================
  /**
   * Plays a sample in a specified channel, using the sample's default frequency, volume
   * and pan settings.
   * <p>
   * <b>Remarks</b>
   * If you play a FSOUND_HW3D declared sample with this function, then the position and velocity
   * are set to those of the listener. Other attributes such as volume, frequency and pan are taken
   * from the sample's default volume, frequency, pan etc.
   * ----------
   * The channel handle :
   * The return value is reference counted. This stops the user from updating a stolen channel.
   * Basically it means the only sound you can change the attributes (ie volume/pan/frequency/3d position) for are the one you specifically called playsound for. If another sound steals that channel, and you keep trying to change its attributes (ie volume/pan/frequency/3d position), it will do nothing.
   * This is great if you have sounds being updated from tasks and you just forget about it.
   * You can keep updating the sound attributes and if another task steals that channel, your original task wont change the attributes of the new sound!!!
   * The lower 12 bits contain the channel number. (yes this means a 4096 channel limit for FMOD :)
   * The upper 19 bits contain the reference count.
   * The top 1 bit is the sign bit.
   * ie
   * S RRRRRRRRRRRRRRRRRRR CCCCCCCCCCCC
   * ----------
   * Remember if not using FSOUND_FREE, then the channel pool is split up into software and hardware channels.
   * Software channels occupy the first n indicies specified by the value passed into FSOUND_Init.
   * Hardware channels occupy the next n indicies after this, and can be a variable amount, depending on the hardware.
   * Use FSOUND_GetNumHardwareChannels to query how many channels are available in hardware.
   * </p>
   *
   * @param channel 0+ 
   * The absolute channel number in the channel pool. 
   * Remember software channels come first, followed by hardware channels. 
   * You cannot play a software sample on a hardware channel and vice versa.
   * FSOUND_FREE 
   * Chooses a free channel to play in. If all channels are used then it
   * selects a channel with a sample playing that has an EQUAL or LOWER priority 
   * than the sample to be played.
   * FSOUND_ALL
   * Passing this will cause ALL channels to play. (note this will make things
   * VERY noisy!)
   * If FSOUND_ALL is used the last channel success flag will be returned.
   * @param sample to be played
   *
   * @return On success, the channel handle that was selected is returned. On failure, -1 is returned.
   */
  public static int FSOUND_PlaySound(int channel, FSoundSample sample) {
    return nFSOUND_PlaySound(channel, sample.sampleHandle);
  }
  private static native int nFSOUND_PlaySound(int channel, long sample);

  /**
   * Extended featured version of FSOUND_PlaySound.
   * New functionality includes the ability to start the sound paused. 
   * This allows attributes of a channel to be set freely before the sound actually starts playing, until FSOUND_SetPaused(FALSE) is used.
   * Also added is the ability to associate the channel to a specified DSP unit. This allows the user to 'group' channels into seperate DSP units, which allows effects to be inserted between these 'groups', and allow various things like having one group affected by reverb (wet mix) and another group of channels unaffected (dry). 
   * This is useful to seperate things like music from being affected by DSP effects, while other sound effects are.
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this will cause ALL channels to play. (note this could make things VERY noisy!)
   * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.
   * ----------
   * The channel handle :
   * The return value is reference counted. This stops the user from updating a stolen channel.
   * This means the only sound you can change the attributes (ie volume/pan/frequency/3d position) for are the 
   * one you specifically called playsound for. If another sound steals that channel, and you keep trying to 
   * change its attributes (ie volume/pan/frequency/3d position), it will do nothing.
   * This is great if you have sounds being updated from tasks and you just forget about it.
   * You can keep updating the sound attributes and if another task steals that channel, your original task 
   * wont change the attributes of the new sound!!!
   * The lower 12 bits contain the channel number. (yes this means a 4096 channel limit for FMOD :)
   * The upper 19 bits contain the reference count.
   * The top 1 bit is the sign bit.
   * ie
   * S RRRRRRRRRRRRRRRRRRR CCCCCCCCCCCC
   * ----------
   * Remember if not using FSOUND_FREE, then the channel pool is split up into software and hardware channels.
   * Software channels occupy the first n indicies specified by the value passed into FSOUND_Init.
   * Hardware channels occupy the next n indicies after this, and can be a variable amount, depending on the hardware.
   * Use FSOUND_GetNumHardwareChannels to query how many channels are available in hardware.
   * ----------
   * If you attach a sound to a DSP unit (for grouping purposes), the callback for the DSP unit will be overwritten with fmod's internal mixer callback, so the callback the user supplied is rendered obsolete and is not called.
   * Also, do not attach sounds to system DSP units, the assignment will be ignored if you do.   
   * </p>
   *
   * @param channel 0+ 
   * The absolute channel number in the channel pool. 
   * Remember software channels come first, followed by hardware channels. 
   * You cannot play a software sample on a hardware channel and vice versa.
   * FSOUND_FREE 
   * Chooses a free channel to play in. If all channels are used then it
   * selects a channel with a sample playing that has an EQUAL or LOWER priority 
   * than the sample to be played.
   * FSOUND_ALL
   * Passing this will cause ALL channels to play. (note this will make things
   * VERY noisy!)
   * If FSOUND_ALL is used the last channel success flag will be returned.
   * @param sample to be played
   * @param dspunit Optional. NULL by default. Pointer to a dsp unit to attach the channel to for channel grouping. Only attach a sound to a user created DSP unit, and not a system DSP unit.
   * @param startpaused Start the sound paused or not. Pausing the sound allows attributes to be set before the sound starts 
   * @return On success, the channel handle that was selected is returned. On failure, -1 is returned.
   */
  public static int nFSOUND_PlaySoundEx(int channel, FSoundSample sample, FSoundDSPUnit dspunit, boolean startpaused) {
    return nFSOUND_PlaySoundEx(channel, sample.sampleHandle, dspunit.dspHandle, startpaused);
  }
  private static native int nFSOUND_PlaySoundEx(int channel, long sample, ByteBuffer dspunit, boolean startpaused);  
  
  /**
   * Stops a specified sound channel from playing, and frees it up for re-use
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this will cause ALL channels to stop.
   * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.
   * </p>
   *
   * @param channel The channel number/handle to stop. FSOUND_ALL can also be used (see remarks)
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static native boolean FSOUND_StopSound(int channel);  
  
  /**
   * Sets a channels frequency or playback rate, in Hz.
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported here. Passing this will set ALL channels to specified frequency.
   * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.
   * Negative frequencies make the sound play backwards, so FSOUND_SetCurrentPosition would be needed to set the sound to the right position. 
   * </p>
   *
   * @param channel The channel number/handle to stop. FSOUND_ALL can also be used (see remarks)
   * @param freq The frequency to set. Valid ranges are from 100 to 705600, and -100 to -705600 
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static native boolean FSOUND_SetFrequency(int channel, int freq);  
  
  /**
   * XBox Only - For surround sound systems, this function allows each surround speaker level to be set individually for this channel
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this will set the pan of ALL channels available.
   * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.
   * ----------
   * FSOUND_SYSTEMCHANNEL is supported. You can set the mix levels for the FMOD software engine, and ALL software mixed sounds will be affected.   * </p>
   *
   * @param channel The channel number/handle to change the output levels for. FSOUND_ALL and FSOUND_SYSTEMCHANNEL can also be used (see remarks)
   * @param frontleft Value from 0 to 255 inclusive, specifying a linear level for the front left speaker.
   * @param center Value from 0 to 255 inclusive, specifying a linear level for the center.
   * @param frontright Value from 0 to 255 inclusive, specifying a linear level for the front right speaker.
   * @param backleft Value from 0 to 255 inclusive, specifying a linear level for the back left speaker.
   * @param backright Value from 0 to 255 inclusive, specifying a linear level for the back right speaker.
   * @param lfe Value from 0 to 255 inclusive, specifying a linear level for the subwoofer speaker.
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static native boolean FSOUND_SetLevels(
      int channel, int frontleft, int center, int frontright, int backleft, int backright, int lfe);
  
  /**
   * Sets the loop mode for a particular CHANNEL, not sample
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this will set loop modes for all channels available.
   * Note, this does not work for hardware sounds played on hardware channels while they are playing. The function has to be called when the channel is paused.
   * Software based sounds do not have this limitation, and can have their loop mode changed during playback, but for compatibility it is best to use the pause method, else you may get different behaviour if hardware voices do not exist.
   * </p>
   * @param channel The channel number/handle to change the output levels for. FSOUND_ALL and FSOUND_SYSTEMCHANNEL can also be used (see remarks)
   * @param loopmode The loopmode to set. This can be FSOUND_LOOP_NORMAL, FSOUND_LOOP_BIDI or FSOUND_LOOP_OFF.
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static native boolean FSOUND_SetLoopMode(int channel, int loopmode);  
  
  /**
   * Mutes and un-mutes a channel
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this will mute/unmute ALL channels available.
   * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.
   * </p>
   * @param channel The channel number/handle to change the output levels for. FSOUND_ALL and FSOUND_SYSTEMCHANNEL can also be used (see remarks)
   * @param mute Toggle value - TRUE mutes out the channel, FALSE reenables it.
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static native boolean FSOUND_SetMute(int channel, boolean mute);
  
  /**
   * Sets a channels pan position linearly
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this will set the pan of ALL channels available.
   * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.
   * ----------
   * Important : If you are playing a STEREO sample, and using normal middle panning, it will only come out at half the volume 
   * they are supposed to. To avoid this use FSOUND_STEREO pan.
   * Panning works in the following manner:
   * full left : 100to left, 0to right
   * full right : 0to left, 100to right
   * middle : 71to left, 71to right
   * FMOD Uses 'constant power' panning. The center position is 71 4738960n each channel as it keeps an even RMS output level when
   * moving the sound from left to right. Placing 50 4738960n each channel for a middle position is incorrect.
   * The pan graph for constant power panning resembles a curve instead of straight lines.   * </p>
   * @param channel The channel number/handle to change the output levels for. FSOUND_ALL and FSOUND_SYSTEMCHANNEL can also be used (see remarks)
   * @param pan The panning position for this channel to set.
   * parameters are:
   * - from 0 (full left) to 255 (full right)
   * - FSOUND_STEREOPAN. This is meant for stereo samples, but will work on mono 
   * samples as well. It makes both left and right FULL volume instead of 50/50
   * as middle panning does. See remarks section for more information on this 
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static native boolean FSOUND_SetPan(int channel, int pan);  
  
  /**
   * Pauses or unpauses a sound channel
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this will pause/unpause ALL channels available.
   * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.
   * </p>
   * @param channel The channel number/handle to change the output levels for. FSOUND_ALL and FSOUND_SYSTEMCHANNEL can also be used (see remarks)
   * @param paused TRUE pauses this channel, FALSE unpauses it. 
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static native boolean FSOUND_SetPaused(int channel, boolean paused); 
  
  /**
   * Sets a channels priority. Higher priority means it is less likely to get discarded when
   * FSOUND_FREE is used to select a channel, when all channels are being used, and one has to 
   * be rejected. If a channel has an equal priority then it will be replaced.
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this will pause/unpause ALL channels available.
   * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.
   * </p>
   * @param channel The channel number/handle to change the output levels for. FSOUND_ALL and FSOUND_SYSTEMCHANNEL can also be used (see remarks)
   * @param priority The priority to set. Valid ranges are from 0 (lowest) to 255 (highest)  
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static native boolean FSOUND_SetPriority(int channel, int priority);  
  
  /**
   * This sets the reserved status of a channel. Reserving a channel is related to setting its
   * priority, but reserving a channel means it can NEVER be stolen by a channel request. It 
   * could be thought of as an extra high priority, but is different in that reserved channels do
   * not steal from each other, whereas channels with equal priorities do (unless there are 
   * channels with lower priorities that it can steal from). If all channels were reserved and 
   * another request for came in for a channel, it would simply fail and the sound would not be 
   * played.
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this will pause/unpause ALL channels available.
   * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.
   * </p>
   * @param channel The channel number/handle to change the priority for. 
   * FSOUND_ALL can also be used (see remarks).
   * FSOUND_FREE is NOT accepted.
   * @param reserved Reserved flag. Values accepted are TRUE, to reserve a channel, and FALSE to 
   * un-reserve a channel.  
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static native boolean FSOUND_SetReserved(int channel, boolean reserved);  

  /**
   * Sets a channels surround sound status. This surround sound is a fake dolby trick that 
   * effectively pans the channel to the center, but inverts the waveform in one speaker to
   * make it sound fuller or spacier, or like it is coming out of space between the 2 speakers.
   * Panning is ignored while surround is in effect.
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this will pause/unpause ALL channels available.
   * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.
   * </p>
   * @param channel The channel number/handle to change the surround for. FSOUND_ALL can also be used (see remarks).
   * @param surround Toggle value - TRUE enables surround sound on the channel, FALSE disables it. 
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static native boolean FSOUND_SetSurround(int channel, boolean surround);  
  
  /**
   * Sets a channels volume linearly.
   * This function IS affected by FSOUND_SetSFXMasterVolume.
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this will pause/unpause ALL channels available.
   * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.
   * </p>
   * @param channel The channel number/handle to change the volume for. FSOUND_ALL can also be used (see remarks)
   * @param vol The volume to set. Valid ranges are from 0 (silent) to 255 (full volume) 
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static native boolean FSOUND_SetVolume(int channel, int vol);  

  /**
   * Sets a channels volume linearly.
   * This function is NOT affected by master volume.
   * This function is used when you want to quiet everything down using FSOUND_SetSFXMasterVolume, but make 
   * a channel prominent.
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this will set the absolute volume of ALL channels available.
   * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.
   * -------------
   * A good example of this function being used for a game needing a voice over.
   * If all the background sounds were too loud and drowned out the voice over, there is no way to
   * feasibly go through all the sfx channels and lower the background noise volumes (some might be allocated by music). 
   * Simply lower the background noise with FSOUND_SetSFXMasterVolume, and use FSOUND_SetVolumeAbsolute to bring 
   * up the volume of the voice over to full, and you will get one channel standing out amongst the rest.   * </p>
   * @param channel The channel number/handle to change the volume for. FSOUND_ALL can also be used (see remarks)
   * @param vol The volume to set. Valid ranges are from 0 (silent) to 255 (full volume) 
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static native boolean FSOUND_SetVolumeAbsolute(int channel, int vol);  
  
  /**
   * Returns the linear volume of the specified channel between 0 and 255
   * @param channel Channel to get volume from
   * @return On success, the following values are returned : 0 = silent to 255 = full volume.
   * On failure, 0 is returned. To quailfy if this is a real error, call FSOUND_GetError.
   */
  public static native int FSOUND_GetVolume(int channel);  
  
  /**
   * Returns the volume of the channel based on all combinations of set volume, mastervolume and 3d position.
   * Works on software and hardware voices.
   * <p>
   * <b>Remarks</b>
   * This is not the same as FSOUND_GetCurrentLevels, as that function takes the actual waveform data into account.
   * This function simply gives a final volume based on 3d position and volume settings.
   * </p>
   * @param channel Channel to get amplitude from
   * @return On success, the following values are returned : 0 = silent to 255 = full volume.
   * On failure, 0 is returned. To quailfy if this is a real error, call FSOUND_GetError.
   */
  public static native int FSOUND_GetAmplitude(int channel);   

  /**
   * This updates the position and velocity of a 3d sound playing on a channel
   * <p>
   * <b>Remarks</b>
   * FSOUND treats +X as right, +Y as up, and +Z as forwards.
   * ---------
   * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. 
   * See FSOUND_3D_SetDistanceFactor for more on this.
   * ---------
   * FSOUND vectors expect 3 floats representing x y and z in that order. I.e. a typical definition
   * </p>
   * @param channel Channel you want to apply 3d positioning to.
   * @param pos Pointer to a position vector (xyz float triplet) of the emitter in world space, measured in distance units.
   * This can be NULL to ignore it.
   * @param vel Pointer to a velocity vector (xyz float triplet), of the emitter measured in distance units PER SECOND.
   * This can be NULL to ignore it.
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static boolean FSOUND_3D_SetAttributes(int channel, FloatBuffer pos, FloatBuffer vel) {
    return nFSOUND_3D_SetAttributes(channel, pos, (pos != null) ? pos.position() : 0, vel, (vel != null) ? vel.position() : 0);
  }
  private static native boolean nFSOUND_3D_SetAttributes(int channel, FloatBuffer pos, int posOffset, FloatBuffer vel, int velOffset);  
  
  /**
   * Sets the minimum and maximum audible distance for a channel.
   * MinDistance is the minimum distance that the sound emitter will cease to continue growing 
   * louder at (as it approaches the listener). Within the mindistance it stays at the constant loudest volume possible. Outside of this mindistance it begins to attenuate.
   * MaxDistance is the distance a sound stops attenuating at. Beyond this point it will stay at the volume it would be at maxdistance units from the listener and will not attenuate any more.
   * MinDistance is useful to give the impression that the sound is loud or soft in 3d space. An example of this is a small quiet object, such as a bumblebee, which you could set a mindistance of to 0.1 for example, which would cause it to attenuate quickly and dissapear when only a few meters away from the listener.
   * Another example is a jumbo jet, which you could set to a mindistance of 100.0, which would keep the sound volume at max until the listener was 100 meters away, then it would be hundreds of meters more before it would fade out.
   * -------
   * In summary, increase the mindistance of a sound to make it 'louder' in a 3d world, and 
   * decrease it to make it 'quieter' in a 3d world.
   * maxdistance is effectively obsolete unless you need the sound to stop fading out at a certain point. Do not adjust this from the default if you dont need to.
   * Some people have the confusion that maxdistance is the point the sound will fade out to, this is not the case.
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this will set the min/max distance on ALL channels available.
   * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. 
   * See FSOUND_3D_SetDistanceFactor for more on this.
   * The default units for minimum and maximum distances are 1.0 and 1000000000.0f.
   * Volume drops off at mindistance / distance.
   * To define the min and max distance per sound and not per channel use FSOUND_Sample_SetMinMaxDistance.
   * </p>
   * @param channel The channel to have its minimum and maximum distance set.
   * @param min The channels minimum volume distance in "units". See remarks for more on units.
   * @param max The channels maximum volume distance in "units". See remarks for more on units.
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static native boolean FSOUND_3D_SetMinMaxDistance(int channel, float min, float max); 
  
  /**
   * Sets the current position of the sound in SAMPLES not bytes
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this set the current position for the sound on ALL channels available.
   * On XBOX, GameCube and Playstation 2 hardware voices using compressed data (ie XADPCM, VAG or GCADPCM), 
   * this value will not be sample accurate, but will be rounded to the nearest compression block size. 
   * </p>
   * @param channel The channel number/handle to have its offset or position set.
   * @param offset The offset in SAMPLES from the start of the sound for the position to be set to.
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static native boolean FSOUND_SetCurrentPosition(int channel, int offset);  
  
  /**
   * Returns the current playcursor position of the specified channel
   * @param channel Channel number/handle to get the current position from.
   * @return On success, the play cursor position in SAMPLES is returned for the specified channel.
   * On failure, 0 is returned.
   */
  public static native int FSOUND_GetCurrentPosition(int channel); 
  
  /**
   * Returns the current sample being played on the specified channel
   * <p>
   * <b>Remarks</b>
   * Note that current sample does not return to NULL when a sound has ended.
   * </p>
   * @param channel Channel number/handle to get the currently playing sample from.
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static FSoundSample FSOUND_GetCurrentSample(int channel) {
    long result = nFSOUND_GetCurrentSample(channel);
    if(result != 0) {
     return new FSoundSample(result, null); 
    }
    return null;
  }
  private static native long nFSOUND_GetCurrentSample(int channel);  
  
  /**
   * Returns a left and right VU/Level reading at the current position of the specified channel.
   * Levels are are only supported for software channels.
   * <p>
   * <b>Remarks</b>
   * By default this function is only point sampled and not latency adjusted (it will appear to trigger ahead of when you hear the sound).
   * To fix this and get a 'perfect' set of levels in realtime, use FSOUND_INIT_ACCURATEVULEVELS with FSOUND_Init.
   * -------------------
   * To get an overall VU reading for all sounds, add all VU values for each channel together, and then clip at 1.0. 
   * Another (harder) way is to write a dsp unit that reads from the mixbuffer being passed into it.
   * Note: A true 'VU' should be smoothed, but in case people were after more accuracy than a smoothed value, it was decided to return the raw amplitude, and let the user smooth the result in their own way.
   * </p>
   * @param channel Channel number/handle to retrieve left and right level from.
   * @param l_r FloatBuffer to store left and right level, each between 0 and 1.
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static boolean FSOUND_GetCurrentLevels(int channel, FloatBuffer l_r) {
    return nFSOUND_GetCurrentLevels(channel, l_r, l_r.position());
  }
  private static native boolean nFSOUND_GetCurrentLevels(int channel, FloatBuffer l_r, int l_rOffset);  
  
  /**
   * Returns the frequency in HZ of the specified channel
   * @param channel The number/handle to get the frequency from.
   * @return On success, the frequency in HZ of the specified channel is returned.
   * On failure, 0 is returned. To quailfy if this is a real error, call FSOUND_GetError.
   */
  public static native int FSOUND_GetFrequency(int channel);
  
  /**
   * Gets the loop mode for a particular channel
   * <p>
   * <b>Remarks</b>
   * This works for all channel types, whereas setting it will not work.
   * </p>
   * @param channel The channel number/handle to get the loop mode from.
   * @return On success, the loop mode is returned. On failure, 0 is returned.
   */
  public static native int FSOUND_GetLoopMode(int channel);
  
  /**
   * Returns the currently used mixer type
   * @return FSOUND_GetMixer returns a defenition from FSOUND_MIXERTYPES. See FSOUND_MIXERTYPES for valid parameters and descriptions.
   */
  public static native int FSOUND_GetMixer();
  
  /**
   * Returns if the channel specified is muted or not
   * @param channel The channel number/handle to get the mute status from
   * @return TRUE - The channel has mute turned ON. FALSE - The channel has mute turned OFF
   */
  public static native boolean FSOUND_GetMute(int channel);
  
  /**
   * This function returns the number of sub-channels stored in a multi-channel channel handle, which is only possible when playing back a multichannel .FSB file.
   * <p>
   * <b>Remarks</b>
   * A multichannel sound, only possible with the .FSB format, can contain multiple subchannels. When a multichannel sound is played, multiple channels are allocated at the same time.
   * For example, a 8 sounds/streams can be interleaved into a multichannel FSB. This function would return 8, as 8 real hardware/software voices are used during playback.
   * FSOUND_GetSubChannel can be used to get access to the secondary channels.
   * </p>
   * @param channel The value returned by FSOUND_Stream_Play, FSOUND_Stream_PlayEx, FSOUND_PlaySound, FSOUND_PlaySoundEx. 
   * @return On success, the number of subchannels is returned. On failure, 0 is returned.
   */
  public static native int FSOUND_GetNumSubChannels(int channel);
  
  /**
   * Returns the linear pan position of the specified channel between 0 and 255
   * @param channel The channel number/handle to get the pan from. 
   * @return On success, the following values are returned : 0 = full left to 128 = middle to 255 = full right, FSOUND_STEREOPAN
   * On failure, 0 is returned. To quailfy if this is a real error, call FSOUND_GetError.
   */
  public static native int FSOUND_GetPan(int channel);  
  
  /**
   * Gets current pause status of the channel
   * <p>
   * <b>Remarks</b>
   *  This function is useful for games that have a pause mode, and you dont want the sounds
   *  to continue playing, but you would like them to continue on from where they left off 
   * when you unpause.
   * </p>
   * @param channel The channel number/handle to get the paused status from. 
   * @return TRUE - The channel is currently paused.
   * FALSE - The channel is running.
   */
  public static native boolean FSOUND_GetPaused(int channel);  
  
  /**
   * Gets a sound channels priority. Priority is used to determine if soundeffects should 
   * replace other sound effects when the channel limit has been reached. See 
   * FSOUND_SetPriority for more information.
   * @param channel The channel number/handle to get the priority from. 
   * @return On success, the priority of the channel is returned. Ranges between 0 and 255.
   * On failure, 0 is returned. To quailfy if this is a real error, call FSOUND_GetError.
   */
  public static native int FSOUND_GetPriority(int channel);
  
  /**
   * Gets a sound channels reserved status. priority is used to determine if soundeffects should muscle
   * out other sound effects when the channel limit has been reached.
   * @param channel The channel number/handle to get the reserved status from. 
   * @return TRUE Channel is reserved and cannot be selected.
   * FALSE Channel is reserved and can be selected.
   */
  public static native int FSOUND_GetReserved(int channel); 
  
  /**
   * This function returns a channel handle from a subchannel within a multichannel FSB file, so that it can be maniuplated seperately, instead of controlling the whole multichannel array with the parent channel that the user retrieves from FSOUND_PlaySound etc.
   * <p>
   * <b>Remarks</b>
   * A multichannel sound, only possible with the .FSB format, can contain multiple subchannels. When a multichannel sound is played, multiple channels are allocated at the same time.
   * Normally you can just use the parent handle, and things like FSOUND_SetVolume will affect all subchannels at the same time. With this function, you can get access to the raw subchannels to allow manipulation of each voice seperately within the multichannel array.
   * For example, a 8 sounds/streams can be interleaved into a multichannel FSB. If you specified a subchannel of 7, it would return a channel handle to the last channel in the multichannel array.
   * A subchannel index of 0 is the parent channel, and the same as the voice passed in is a parameter.
   * The number of subchannels within a multichannel voice can be determined with FSOUND_GetNumSubChannels.
   * </p>
   * 
   * @param channel The value returned by FSOUND_Stream_Play, FSOUND_Stream_PlayEx, FSOUND_PlaySound, FSOUND_PlaySoundEx.
   * @param subchannel Offset from the parent channel into the multichannel array.  
   * @return On success, a raw channel handle is returned. On failure, -1 is returned.
   */
  public static native int FSOUND_GetSubChannel(int channel, int subchannel);
  
  /**
   * Returns the surround sound status of the specified channel.
   * <p>
   * <b>Remarks</b>
   * Surround sound only works on software channels.
   * </p>
   * @param channel The channel number/handle to get the surround sound status from 
   * @return On success, TRUE is returned meaning the channel has surround sound turned ON
   * On failure, FALSE is returned meaning the channel has surround sound turned OFF
   */
  public static native int FSOUND_GetSurround(int channel);   
  
  /**
   * Returns if the channel is currently playing or not.
   * @param channel Channel number/handle to get the playing status from. 
   * @return TRUE channel is currently active and playing. FALSE channel is currently idle.
   */
  public static native boolean FSOUND_IsPlaying(int channel);
  
  /**
   * <p>
   * <b>Remarks</b>
   * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. 
   * See FSOUND_3D_SetDistanceFactor for more on this.
   * </p>
   * 
   * @param channel Channel you want to get 3d information from
   * @param pos Pointer to a position vector (xyz float triplet) of the emitter in world space, measured in distance units.
   * This can be NULL to ignore it.
   * @param vel Pointer to a velocity vector (xyz float triplet), of the emitter measured in distance units PER SECOND.
   * This can be NULL to ignore it. 
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static boolean FSOUND_3D_GetAttributes(int channel, FloatBuffer pos, FloatBuffer vel) {
    return nFSOUND_3D_GetAttributes(channel, pos, (pos != null) ? pos.position() : 0, vel, (vel != null) ? vel.position() : 0);
  }
  private static native boolean nFSOUND_3D_GetAttributes(int channel, FloatBuffer pos, int posOffset, FloatBuffer vel, int velOffset);  
  
  /**
   * Returns the current min and max distance for a channel
   * 
   * @param channel Channel number/handle to retrieve min and max distance from.
   * @param minmax FloatBuffer to store min/max -distance.
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static boolean FSOUND_3D_GetMinMaxDistance(int channel, FloatBuffer minmax) {
    return nFSOUND_3D_GetMinMaxDistance(channel, minmax, (minmax != null) ? minmax.position() : 0);
  }
  private static native boolean nFSOUND_3D_GetMinMaxDistance(int channel, FloatBuffer minmax, int minmaxOffset);  
  // ----------------------------------------------------------
  
  // 3D sound functions
  // ==========================================================
  /**
   * This retreives the position, velocity and orientation of a 3d sound listener
   * <p>
   * <b>Remarks</b>
   * FSOUND treats +X as right, +Y as up, and +Z as forwards. (left handed)
   * To map to your own coordinate system, flip and exchange these values. For example if you wanted to use right handed coordinates, you would negate the Z value of your own direction vector.
   * Orientation vectors are expected to be of UNIT length. This means the magnitude of the vector should be 1.0f.
   * ---------
   * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. 
   * See FSOUND_3D_SetDistanceFactor for more on this.
   * ---------
   * Please remember to use units PER SECOND, NOT PER FRAME as this is a common mistake. 
   * Do not just use (pos - lastpos) from the last frame's data for velocity, as this is not correct. 
   * You need to time compensate it so it is given in units per SECOND.
   * You could alter your pos - lastpos calculation to something like this. 
   * vel = (pos-lastpos) / (time taken since last frame in seconds). 
   * I.e. at 60fps the formula would look like this 
   * vel = (pos-lastpos) / 0.0166667.
   * </p>
   * 
   * @param pos Pointer to a position vector (xyz float triplet), of the listener in world space, 
   * measured in distance units.
   * This can be NULL to ignore it.
   * @param vel Pointer to a velocity vector (xyz float triplet), of the listener measured in 
   * distance units PER SECOND.
   * This can be NULL to ignore it.
   * @param fx pointer to x component of a FORWARD unit length orientation vector
   * This can be NULL to ignore it.
   * @param fy pointer to y component of a FORWARD unit length orientation vector
   * This can be NULL to ignore it.
   * @param fz pointer to z component of a FORWARD unit length orientation vector
   * This can be NULL to ignore it.
   * @param tx pointer to x component of a TOP or upwards facing unit length orientation vector
   * This can be NULL to ignore it.
   * @param ty pointer to y component of a TOP or upwards facing unit length orientation vector
   * This can be NULL to ignore it.
   * @param tz pointer to z component of a TOP or upwards facing unit length orientation vector
   * This can be NULL to ignore it. 
   */
  public static void FSOUND_3D_Listener_GetAttributes(
    FloatBuffer pos, FloatBuffer vel, FloatBuffer fx, FloatBuffer fy, FloatBuffer fz,
    FloatBuffer tx, FloatBuffer ty, FloatBuffer tz) {
  	nFSOUND_3D_Listener_GetAttributes(
  			pos, (pos != null) ? pos.position() : 0,
  			vel, (vel != null) ? vel.position() : 0, 
  			fx,  (fx != null)  ? fx.position()  : 0, 
  			fy,  (fy != null)  ? fy.position()  : 0, 
  			fz,  (fz != null)  ? fz.position()  : 0, 
  			tx,  (tx != null)  ? tx.position()  : 0, 
  			ty,  (ty != null)  ? ty.position()  : 0, 
  			tz,  (tz != null)  ? tz.position()  : 0);
  }
  private static native void nFSOUND_3D_Listener_GetAttributes(
    FloatBuffer pos, int posOffset,  FloatBuffer vel, int velOffset, 
		FloatBuffer fx, int fxOffset, FloatBuffer fy, int fyOffset, FloatBuffer fz, int fzOffset,
    FloatBuffer tx, int txOffset, FloatBuffer ty, int tyOffset, FloatBuffer tz, int tzOffset);
  
  /**
   * This updates the position, velocity and orientation of a 3d sound listener
   * <p>
   * <b>Remarks</b>
   * FSOUND treats +X as right, +Y as up, and +Z as forwards. (left handed)
   * To map to your own coordinate system, flip and exchange these values. For example if you wanted to use
   * right handed coordinates, you would negate the Z value of your own direction vector.
   * Orientation vectors are expected to be of UNIT length. This means the magnitude of the vector
   * should be 1.0f.
   * ---------
   * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. 
   * See FSOUND_3D_SetDistanceFactor for more on this.
   * ---------
   * Please remember to use units PER SECOND, NOT PER FRAME as this is a common mistake. 
   * Do not just use (pos - lastpos) from the last frame's data for velocity, as this is not 
   * correct. You need to time compensate it so it is given in units per SECOND.
   * You could alter your pos - lastpos calculation to something like this. 
   * vel = (pos-lastpos) / (time taken since last frame in seconds). Ie at 60fps the formula
   * would look like this vel = (pos-lastpos) / 0.0166667.
   * </p>
   * 
   * @param pos Pointer to a position vector (xyz float triplet), of the listener in world space, measured in distance units.
   * This can be NULL to ignore it.
   * @param vel Pointer to a velocity vector (xyz float triplet), of the listener measured in distance units PER SECOND.
   * This can be NULL to ignore it.
   * @param fx x component of a FORWARD unit length orientation vector
   * @param fy y component of a FORWARD unit length orientation vector
   * @param fz z component of a FORWARD unit length orientation vector
   * @param tx x component of a TOP or upwards facing unit length orientation vector
   * @param ty y component of a TOP or upwards facing unit length orientation vector
   * @param tz z component of a TOP or upwards facing unit length orientation vector
   */
  public static void FSOUND_3D_Listener_SetAttributes(
    FloatBuffer pos, FloatBuffer vel, 
    float fx, float fy, float fz, float tx, float ty, float tz) {
   
   nFSOUND_3D_Listener_SetAttributes(
     pos, (pos != null) ? pos.position() : 0,
     vel, (vel != null) ? vel.position() : 0, 
     fx, fy, fz, tx, ty, tz);
  }
  private static native void nFSOUND_3D_Listener_SetAttributes(
    FloatBuffer pos, int posOffset,  FloatBuffer vel, int velOffset, 
		float fx, float fy, float fz, float tx, float ty, float tz);
  
  /**
   * Sets the current listener number and number of listeners, if the user wants to simulate multiple listeners at once. 
   * This is usually for the case in a game where there is a splitscreen and multiple players playing the game at once
   * <p>
   * <b>Remarks</b>
   * Only affects FSOUND_3D_Listener_SetAttributes and FSOUND_3D_Listener_GetAttributes.
   * Setting more than 1 listener will turn off doppler and cause all panning to be ignored and 3d sound will come from the center (mono).
   * -------------
   * For WIN32 FSOUND_HW3D based sounds, channels must have their attributes set after this function is called, otherwise unexpected audible results may occur.
   * For example you cannot update your channels with FSOUND_3D_SetAttributes, call FSOUND_3D_Listener_SetCurrent, and then call FSOUND_Update and expect all the voices to update correctly. 
   * The correct order is to call FSOUND_3D_Listener_SetCurrent first, then update all channels with FSOUND_3D_SetAttributes, then call FSOUND_Update. 
   * This is due to DirectSound not supporting multiple listeners, so FMOD has to do inverse transforms on the positions to simulate it with one listener, at the time FSOUND_3D_SetAttributes is called.
   * </p>
   *
   * @param current Current listener number. Listener commands following this function call will affect this listener number. (default: 0)
   * @param numlisteners Number of listeners active. (default: 1) 
   */
  public static native void FSOUND_3D_Listener_SetCurrent(int current, int numlisteners);
  
  /**
   * Sets FMOD's 3d engine relative distance factor, compared to 1.0 meters. It equates to 
   * 'how many units per meter' does your engine have
   * <p>
   * <b>Remarks</b>
   * By default this value is set at 1.0, or meters
   * </p>
   *
   * @param scale 1.0 = 1 meter units. If you are using feet then scale would equal 3.28.
   */
  public static native void FSOUND_3D_SetDistanceFactor(float scale);  
  
  /**
   * Sets the doppler shift scale factor. 
   * <p>
   * <b>Remarks</b>
   * This is a general scaling factor for how much the pitch varies due to doppler shifting.
   * Increasing the value above 1.0 exaggerates the effect, whereas lowering it reduces the effect.
   * 0 removes the effect all together.
   * FMOD's speed of sound at a DopplerFactor of 1.0 is 340 m/s.
   * </p>
   *
   * @param scale Doppler shift scale. Default value for FSOUND is 1.0f
   */
  public static native void FSOUND_3D_SetDopplerFactor(float scale);  
  
  /**
   * Sets the global attenuation rolloff factor.
   * Normally volume for a sample will scale at 1 / distance. This gives a logarithmic attenuation of volume as the source gets further away (or closer).
   * Setting this value makes the sound drop off faster or slower. The higher the value, the faster volume will fall off. 
   * The lower the value, the slower it will fall off.
   * For example a rolloff factor of 1 will simulate the real world, where as a value of 2 will make sounds attenuate 2 times quicker
   * <p>
   * <b>Remarks</b>
   * ---------
   * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. 
   * By default this is set to meters which is a distance scale of 1.0. 
   * See FSOUND_3D_SetDistanceFactor for more on this.
   * ---------
   * The default rolloff factor is 1.0.   
   * </p>
   *
   * @param rolloff The rolloff factor to set for this sample. Valid ranges are 0 to 10.
   */
  public static native void FSOUND_3D_SetRolloffFactor(float rolloff);
  // ----------------------------------------------------------
  
  // Stream functions
  // ==========================================================
  /**
   * Opens an audio file/url/cd ready for streaming. 
   * This opens the file in preparation for playback in real-time, without needing to decode the whole file into memory first
   * <p>
   * <b>Remarks</b>
   * WAV support supports windows codec compressed WAV files.
   * --------------
   * FSOUND_MPEGACCURATE is to be used cautiously. To open a file with this mode turned on, it has to scan the whole MP3 first. This can take several seconds if the file is big, or the harddisk/cpu is slow.
   * A way to speed up this process would be to load the compressed mp3 into memory first, and use the FSOUND_LOADMEMORY flag with this function.
   * --------------
   * NOTE : Internet stream limitations
   * - URLs must start with "http://".
   * - The only supported formats for HTTP streams are MP3 (must have .mp3 extension) and OggVorbis (must have .ogg extension).
   * --------------
   * FSB streaming is not supported if the format from FSBank is 'Retain original format'. On PC platforms, only PCM and ADPCM FSB files are allowed.
   * --------------
   * Note, on PlayStation 2 you cannot use FSOUND_LOADMEMORY, you may use FSOUND_LOADMEMORYIOP though.
   * --------------
   * When opening with the FSOUND_NONBLOCKING flag, this function always succeeds at the point of being called. 
   * It will always return a valid channel handle, even though the file might fail to open. To determine any error in non blocking mode use FSOUND_Stream_GetOpenState.
   * --------------
   * NOTE: CDDA Streaming (Win32 only!)
   * To open a CD for CDDA streaming, specify the drive letter of a CD drive e.g. FSOUND_Stream_Open("d:", 0, 0, 0); FSOUND_Stream_Open will create a stream with multiple substreams, one for each CD track. Use FSOUND_Stream_SetSubStream to select which CD track to play.
   * A number of options can be passed to FSOUND_Stream_Open along with the drive letter. They are :
   * ? e.g. FSOUND_Stream_Open("d:*?", 0, 0, 0); This option will cause a tag field called "CD_DEVICE_INFO" to be attached to the stream. This tag field contains information on the specified CD device.
   * ! e.g. FSOUND_Stream_Open("d:*!", 0, 0, 0); This option will cause the stream to be opened in "quick open" mode. When a stream is opened in this mode, calls to FSOUND_Stream_SetSubStream will return immediately making it quick to select each substream in turn and get the length of each CD track. Note that a stream in quick open mode cannot be played! Use quick open mode to get track lengths and then re-open the stream without quick open mode to actually play it.
   * j e.g. FSOUND_Stream_Open("d:*j", 0, 0, 0); This option turns jitter correction OFF.
   * Options can be combined like so: FSOUND_Stream_Open("d:*?!j", 0, 0, 0);
   * If a nonblocking CDDA stream fails to open, a tag field called "CD_ERROR" will be attached to the stream. This tag field contains a textual description of why the stream failed to open.
   * NOTE: FMOD will always try to use native NTSCSI support to communicate with CD devices before trying to use ASPI. If FMOD is using ASPI then it can only access the first CD device it finds.
   * </p>
   * 
   * @param name Name of the file to open, or pointer to data if FSOUND_LOADMEMORY is used.
   * @param mode Simple description of how to play the file. For all formats except raw PCM,
   * FSOUND_LOOP*, FSOUND_HW3D, FSOUND_HW2D, FSOUND_2D, FSOUND_LOADMEMORY, FSOUND_LOADRAW, FSOUND_MPEGACCURATE, FSOUND_NONBLOCKING flags are the only ones supported.
   * @param offset Optional. 0 by default. If > 0, this value is used to specify an offset in a file, so fmod will seek before opening. length must also be specified if this value is used.
   * @param length Optional. 0 by default. If > 0, this value is used to specify the length of a memory block when using FSOUND_LOADMEMORY, or it is the length of a file or file segment if the offset parameter is used. On PlayStation 2 this must be 16 byte aligned for memory loading.
   * @return On success, a reference to an opened stream is returned. On failure, NULL is returned. 
   */
  public static FSoundStream FSOUND_Stream_Open(String name, int mode, int offset, int length) {
    long result = nFSOUND_Stream_Open(name, mode, offset, length);
    if(result != 0) {
     return new FSoundStream(result, null); 
    }
    return null;    
  }
  
  /**
   * @see #FSOUND_Stream_Open(String, int, int, int)
   * @param data data when FSOUND_LOADMEMORY is used.
   * @param mode Simple description of how to play the file. For all formats except raw PCM,
   * FSOUND_LOOP*, FSOUND_HW3D, FSOUND_HW2D, FSOUND_2D, FSOUND_LOADMEMORY, FSOUND_LOADRAW, FSOUND_MPEGACCURATE, FSOUND_NONBLOCKING flags are the only ones supported.
   * @return On success, a reference to an opened stream is returned. On failure, NULL is returned. 
   */  
  public static FSoundStream FSOUND_Stream_Open(ByteBuffer data, int mode) {
    long result = nFSOUND_Stream_Open(data, mode, data.position(), data.remaining());
    if(result != 0) {
     return new FSoundStream(result, data); 
    }
    return null;
  }
  private static native long nFSOUND_Stream_Open(ByteBuffer data, int mode, int offset, int length);    
  private static native long nFSOUND_Stream_Open(String name, int mode, int offset, int length);
  
  /**
   * Starts a pre-opened stream playing
   * <p>
   * <b>Remarks</b>
   * When a stream starts to play, it inherits a special high priority (256). 
   * It cannot be rejected by other sound effect channels in the normal fashion as the user can never set a priority above 255 normally. 
   * --------------
   * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.
   * --------------
   * FSB streaming is not supported if the format from FSBank is 'Retain original format'. On PC platforms, only PCM and ADPCM FSB files are allowed.
   * --------------
   * FSOUND_STEREOPAN is recommended for stereo streams if you call FSOUND_SetPan. This puts the left and right channel to full volume.
   * Otherwise a normal pan will give half volume for left and right. See FSOUND_SetPan for more information on this.
   * --------------
   * You can use normal channel based commands (such as FSOUND_SetVolume etc) on the return handle, as it is a channel handle.
   * </p>
   *
   * @param channel 0+ The channel index in the channel pool. This must not exceed the maximum number of channels allocated with FSOUND_Init 
   * FSOUND_FREE
   * Chooses a free channel to play in. If all channels are used then it
   * selects a channel with a sample playing that has a lower priority than the 
   * sample to be played.
   * @param stream FSoundStream to be played. 
   * @return On success, the channel handle the stream is playing in is returned. On failure, -1 is returned. 
   */
  public static int FSOUND_Stream_Play(int channel, FSoundStream stream) {
    return nFSOUND_Stream_Play(channel, stream.streamHandle);
  }
  private static native int nFSOUND_Stream_Play(int channel, long streamhandle);
  
  /**
   * Stops a stream from playing
   * <p>
   * <b>Remarks</b>
   * The stream is still prepared and sitting in memory ready to go. Use FSOUND_Stream_Close on the stream to completely remove it.
   * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready
   * </p>
   *
   * @param stream FSoundStream to be stopped 
   * @return On success, TRUE is returned. On failure, FALSE is returned. 
   */
  public static boolean FSOUND_Stream_Stop(FSoundStream stream) {
    return nFSOUND_Stream_Stop(stream.streamHandle);
  }
  private static native boolean nFSOUND_Stream_Stop(long streamhandle);   
  
  /**
   * Shuts down and releases an FSOUND stream
   * <p>
   * <b>Remarks</b>
   * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.
   * The only exception to this rule is for internet streams - this function will successfully close an internet stream that has been opened with FSOUND_NONBLOCKING before that stream is ready.
   * </p>
   *
   * @param stream FSoundStream to be closed  
   * @return On success, TRUE is returned. On failure, FALSE is returned. 
   */
  public static boolean FSOUND_Stream_Close(FSoundStream stream) {
	stream.release();
    return nFSOUND_Stream_Close(stream.streamHandle);
  }
  private static native boolean nFSOUND_Stream_Close(long streamhandle);
  
  /**
   * Returns the number of substreams inside a multi-stream FSB bank file
   *
   * @param stream FSoundStream to get substream count from  
   * @return On success, the number of FSB substreams is returned. On failure, 0 is returned.
   */
  public static int FSOUND_Stream_GetNumSubStreams(FSoundStream stream) {
    return nFSOUND_Stream_GetNumSubStreams(stream.streamHandle);
  }
  private static native int nFSOUND_Stream_GetNumSubStreams(long streamhandle);  
  
  /**
   * Seeks a stream to the substream inside a multi-stream FSB bank file, specified by its index
   * <p>
   * <b>Remarks</b>
   * A stream will stop if this function is called, as it needs to seek and flush the buffer.
   * Indicies for this function are generated as user friendly constants when compiling the FSB bank, and are available in the relevant generated header file.
   * --------------
   * If the stream has been opened with FSOUND_NONBLOCKING, this function will ALWAYS succeed, but puts the stream back into a non-ready state. You then have to poll after calling this to make sure the stream is ready.
   * You can either do this by calling FSOUND_Stream_Play repeatedly/once a frame until it is succeeds, or FSOUND_Stream_GetOpenState.
   * </p>
   * @param stream to have its position set
   * @param index The index of the stream within the FSB file   
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static int FSOUND_Stream_SetSubStream(FSoundStream stream, int index) {
    return nFSOUND_Stream_SetSubStream(stream.streamHandle, index);
  }
  private static native int nFSOUND_Stream_SetSubStream(long streamhandle, int index);
  
  /**
   * Adds a user synchronization callback point into a stream
   * <p>
   * <b>Remarks</b>
   * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready   
   * </p>
   * @param stream The stream to add a sync point to.
   * @param pcmoffset Offset in SAMPLES (not bytes).
   * @param name The name of the syncpoint, which will be passed into the sync callback when it is triggered.
   * @return On success, a sync point handle is returned. On failure, NULL is returned.
   */
  public static FSoundSyncPoint FSOUND_Stream_AddSyncPoint(FSoundStream stream, int pcmoffset, String name) {
   ByteBuffer result = nFSOUND_Stream_AddSyncPoint(stream.streamHandle, pcmoffset, name);
   if(result != null) {
    return new FSoundSyncPoint(result);
   }
   return null;
  }
  private static native ByteBuffer nFSOUND_Stream_AddSyncPoint(long streamhandle, int pcmoffset, String name);  
  
  /**
   * Creates a user definable stream file ready for playing. The stream is serviced through a callback
   * <p>
   * <b>Remarks</b>
   * This method only supports SIGNED RAW streams to be written to the buffer supplied by the callback. 
   * They can be 8 or 16 bit, mono or stereo.
   * 'lenbytes' may be rounded down to the nearest sample alignment in bytes. Ie if you specified 1001 bytes for a 16bit stereo sample stream, len would return 1000 in the callback. (250 samples * 4 bytes per sample)
   * PlayStation 2 IMPORTANT! : if FSOUND_SendData is NOT called from the stream callback the IOP will hang because it is waiting for this command to be executed before it can unlock its buffer.
   * </p>
   * @param callbackHandler FSoundStreamCallback to be called back
   * @param lenbytes Size of the data in BYTES the callback will require to be written to the buffer.
   * @param mode Description of the raw sample data being opened. see FSOUND_MODES for a description of these modes.
   * @param samplerate Rate of playback. Be careful you dont set the sample rate too high so that the stream servicer (ie the harddisk) may not keep up.
   * @return On success, a sync point handle is returned. On failure, NULL is returned.
   */
  public static FSoundStream FSOUND_Stream_Create(FSoundStreamCallback callbackHandler, int lenbytes, int mode, int samplerate) {
   FSoundStream stream = null;
   long result = nFSOUND_Stream_Create(lenbytes, mode, samplerate);
   if(result != 0) {
    stream = new FSoundStream(result, null);
   	FMOD.registerCallback(FMOD.FSOUND_STREAMCALLBACK, stream.streamHandle, stream, callbackHandler);
   }
   return stream;
  }
  private static native long nFSOUND_Stream_Create(int lenbytes, int mode, int samplerate);  
  
  /**
   * Allows the user to add a custom DSP unit to a stream
   * <p>
   * <b>Remarks</b>
   * The priority for a stream DSP unit is not related to the priorities specified in fmod.h.
   * The priorities are anything fom 0 onwards, and ALWAYS come after data is read/decoded for the stream   * </p>
   * 
   * @param stream The stream to have a DSP attached to.
   * @param callback A standard FSoundDSPCallback callback
   * @param priority The priority, or position within the streams DSP chain to place the unit.
   * @return On success, a handle to the FSoundDSPUnit is returned. All DSP functions are performable on this. On failure, null is returned
   */
  public static FSoundDSPUnit FSOUND_Stream_CreateDSP(FSoundStream stream, FSoundDSPCallback callback, int priority) {
   ByteBuffer dspID     = (ByteBuffer) BufferUtils.createByteBuffer(8).putLong(FSoundDSPUnit.getNextId()).flip(); 
   FSoundDSPUnit unit = null;
   
   ByteBuffer dspHandle = nFSOUND_Stream_CreateDSP(stream.streamHandle, priority, dspID);
   
   if(dspHandle != null) {
    unit = new FSoundDSPUnit(dspHandle, dspID);
    FMOD.registerCallback(FMOD.FSOUND_DSPCALLBACK, dspID.getLong(0), unit, callback);
   }
   return unit;
  }
  private static native ByteBuffer nFSOUND_Stream_CreateDSP(long streamHandle, int priority, ByteBuffer dspID);
  
  /**
   * Removes a user synchronization callback point from a stream.
   * 
   * @param point The sync point to remove
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static boolean FSOUND_Stream_DeleteSyncPoint(FSoundSyncPoint point) {
   return nFSOUND_Stream_DeleteSyncPoint(point.syncpointHandle);
  }
  private static native boolean nFSOUND_Stream_DeleteSyncPoint(ByteBuffer syncpointHandle);  
  
  /**
   * Find a tag field associated with an open stream by name and type
   * 
   * @param stream The stream to get the tag field from.
   * @param field FSoundTagField to find (using type, name)
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static boolean FSOUND_Stream_FindTagField(FSoundStream stream, FSoundTagField field) {
   return nFSOUND_Stream_FindTagField(stream.streamHandle, field.type, field.name, field);
  }
  private static native boolean nFSOUND_Stream_FindTagField(long streamHandle, int type, String name, FSoundTagField field);  

  /**
   * Returns the size of the stream in BYTES
   * <p>
   * <b>Remarks</b>
   * Position functions for streams work in bytes not samples.
   * -----
   * This function is not supported for URL based streams over the internet.
   * </p>
   * @param stream The stream to have its length returned
   * @return On success, the size of the stream in BYTES is returned. On failure, 0 is returned.
   */
  public static int FSOUND_Stream_GetLength(FSoundStream stream) {
   return nFSOUND_Stream_GetLength(stream.streamHandle);
  }
  private static native int nFSOUND_Stream_GetLength(long streamHandle);  
  
  /**
   * Returns the size of the stream in MILLISECONDS
   * <p>
   * <b>Remarks</b>
   * FSOUND_MPEGACCURATE will need to be used with mp3 files that use VBR encoding for more accuracy
   * </p>
   * @param stream The stream to have its its total duration returned.
   * @return On success, the size of the stream in MILLISECONDS is returned. On failure, 0 is returned.
   */
  public static int FSOUND_Stream_GetLengthMs(FSoundStream stream) {
   return nFSOUND_Stream_GetLengthMs(stream.streamHandle);
  }
  private static native int nFSOUND_Stream_GetLengthMs(long streamHandle);
  
  /**
   * Retrieves the mode of the stream
   * <p>
   * <b>Remarks</b>
   * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.
   * </p>
   * @param stream The stream to get the mode from
   * @return mode of stream
   */
  public static int FSOUND_Stream_GetMode(FSoundStream stream) {
   return nFSOUND_Stream_GetMode(stream.streamHandle);
  }
  private static native int nFSOUND_Stream_GetMode(long streamHandle);  
  
  /**
   * Returns the number of substreams inside a multi-stream FSB bank file
   *  
   * @param stream stream to query
   * @return On success, the number of FSB substreams is returned. On failure, 0 is returned
   */
  public static int nFSOUND_Stream_GetNumSyncPoints(FSoundStream stream) {
   return nFSOUND_Stream_GetNumSyncPoints(stream.streamHandle);
  }
  private static native int nFSOUND_Stream_GetNumSyncPoints(long streamHandle);  
  
  /**
   * Get the number of tag fields associated with the specified stream
   *  
   * @param stream stream to query
   * @param num IntBuffer that will receive the nubmer of tag fields associated with the specified stream.
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static boolean FSOUND_Stream_GetNumTagFields(FSoundStream stream, IntBuffer num) {
   return nFSOUND_Stream_GetNumTagFields(stream.streamHandle, num, num.position());
  }
  private static native boolean nFSOUND_Stream_GetNumTagFields(long streamHandle, IntBuffer buffer, int offset);  
  
  /**
   * If a stream is opened with FSOUND_NONBLOCKING, this function returns the state of the opening stream
   * <p>
   * <b>Remarks</b>
   * A blocking stream will return NULL from FSOUND_Stream_Open so a return value of -3 is redundant in this case.
   * A blocking stream will always return 0 if it is not NULL.
   * </p>
   * @param stream to get the open state from.
   * @return 0 = stream is opened and ready. 
   * -1 = stream handle passed in is invalid.
   * -2 = stream is still opening or performing a SetSubStream command.
   * -3 = stream failed to open. (file not found, out of memory or other error).
   * -4 = connecting to remote host (internet streams only)
   * -5 = stream is buffering data (internet streams only)
   */
  public static int FSOUND_Stream_GetOpenState(FSoundStream stream) {
   return nFSOUND_Stream_GetOpenState(stream.streamHandle);
  }
  private static native int nFSOUND_Stream_GetOpenState(long streamHandle); 

  /**
   * Returns the current FILE position of the stream of the stream in BYTES
   * <p>
   * <b>Remarks</b>
   * Position functions for streams work in bytes not samples.
   * Position information is also based on the current file position, not the actual playing
   * position, so if the stream is only updated every 100ms, then the position will only be
   * updated every 100ms.
   * -----
   * This function is not supported for URL based streams over the internet or CDDA streams
   * </p>
   * @param stream to have its position returned
   * @return On success, the current stream's position in BYTES is returned. On failure, 0 is returned. 
   */
  public static int FSOUND_Stream_GetPosition(FSoundStream stream) {
   return nFSOUND_Stream_GetPosition(stream.streamHandle);
  }
  private static native int nFSOUND_Stream_GetPosition(long streamHandle);   
  
  /**
   * Returns the FSOUND_SAMPLE definition that the stream uses internally. 
   * You can use this to get a variety of information like the songs name, default speed and more.
   * @param stream to have its internal sample pointer returned.
   * @return On success, a handle to the FSOUND_SAMPLE definition is returned. On failure, 0 is returned 
   */
  public static FSoundSample FSOUND_Stream_GetSample(FSoundStream stream) {
    long result = nFSOUND_Stream_GetSample(stream.streamHandle);
    if(result != 0) {
      return new FSoundSample(result, null); 
    }
    return null;
  }
  private static native long nFSOUND_Stream_GetSample(long streamHandle);  
  
  /**
   * Obtains a sync point by index. This is useful when you havent created your own, ie it came from a wav file
   * <p>
   * <b>Remarks</b>
   * Points are loaded in order of offset, so the index will represent the smallest point to the largest.   * </p>
   * @param stream to have its position returned
   * @param index The sync point offset into the stream 
   * @return On success, a handle to a sync point is returned. On failure, NULL is returned. 
   */
  public static FSoundSyncPoint FSOUND_Stream_GetSyncPoint(FSoundStream stream, int index) {
    ByteBuffer result = nFSOUND_Stream_GetSyncPoint(stream.streamHandle, index);
    if(result != null) {
      return new FSoundSyncPoint(result); 
    }
    return null;
  }
  private static native ByteBuffer nFSOUND_Stream_GetSyncPoint(long streamHandle, int index);
  
  /**
   * Retrieves the name and pcm offset in samples for a specified sync point
   * <p>
   * <b>Remarks</b>
   * Convert samples to time by dividing the PCM value by the default samplerate. This would give you the value in seconds. Multiply by 1000 to get milliseconds.   
   * @param point handle to the sync point to retrieve information from
   * @param pcmoffset An IntBuffer that will receive the sync point offset in pcm SAMPLES. A value of NULL will be ignored 
   * @return On success, the name of the syncpoint is returned as a string. On failure, NULL is returned. 
   */
  public static String FSOUND_Stream_GetSyncPointInfo(FSoundSyncPoint point, IntBuffer pcmoffset) {
    return nFSOUND_Stream_GetSyncPointInfo(point.syncpointHandle, pcmoffset, (pcmoffset != null) ? pcmoffset.position() : 0);
  }
  private static native String nFSOUND_Stream_GetSyncPointInfo(ByteBuffer pointHandle, IntBuffer pcmoffset, int bufferOffset);  
  
  /**
   * Get a tag field associated with an open stream
   * <p>
   * <b>Remarks</b>
   * If this function returns successfully, "value" will contain a pointer to a piece of tag-field-specific data - do not assume it will always point to a null-terminated ASCII string.
   * @param stream The stream to get the tag field from.
   * @param num The number of the tag field to retrieve.
   * @param field TagField to receive data
   * @return On success, TRUE is returned. On failure, FALSE is returned. 
   */
  public static boolean FSOUND_Stream_GetTagField(FSoundStream stream, int num, FSoundTagField field) {
    return nFSOUND_Stream_GetTagField(stream.streamHandle, num, field);
  }
  private static native boolean nFSOUND_Stream_GetTagField(long streamHandle, int num, FSoundTagField field);  
    
  /**
   * Returns the current time offset in stream in milliseconds.
   * <p>
   * <b>Remarks</b>
   * FSOUND_MPEGACCURATE will need to be used with mp3 files that use VBR encoding for more accuracy
   * </p>
   * @param stream to get the currently playing time offset
   * @return On success, the current stream's position in milliseconds is returned. On failure, 0 is returned. 
   */
  public static int FSOUND_Stream_GetTime(FSoundStream stream) {
    return nFSOUND_Stream_GetTime(stream.streamHandle);
  }
  private static native int nFSOUND_Stream_GetTime(long streamHandle);  

  /**
   * Gets buffer size and thresholds that will be used when opening new internet streams
   * <p>
   * <b>Remarks</b>
   * This function returns the values that will be used for subsequent internet stream opens. Internet streams that already exist may have different values.
   * </p>
   * @param values IntBuffer to hold 3 int values:
   * buffersize size in bytes of the streaming buffer.
   * prebuffer_percent how much to prebuffer when a stream is first opened. Values are expressed as a percentage from 1 to 99.
   * rebuffer_percent how much to rebuffer after a stream has suffered a buffer underrun. Values are expressed as a percentage from 1 to 99.
   * @return On success, TRUE is returned. On failure, FALSE is returned.
 
   */
  public static boolean FSOUND_Stream_Net_GetBufferProperties(IntBuffer values) {
    return nFSOUND_Stream_Net_GetBufferProperties(values, values.position());
  }
  private static native boolean nFSOUND_Stream_Net_GetBufferProperties(IntBuffer values, int offset);
  
  /**
   * This function returns a String representing the last HTTP status line that was received when connecting to an internet stream
   * <p>
   * <b>Remarks</b>
   * The result of this function should be used for informational purposes only. 
   * This function provides no facility to discover which internet stream the last HTTP status pertains to when there are multiple internet streams open.
   * </p>
   * @return last HTTP status line that was received
   */
  public static native String FSOUND_Stream_Net_GetLastServerStatus();
  
  /**
   * Get various status information for an internet stream
   * 
   * @param stream to get status information on
   * @param values IntBuffer to hold 4 int values:
   * status variable that will receive a status value. See FSOUND_STREAM_NET_STATUS.
   * bufferused variable that will receive the percentage of the read buffer that is currently in use.
   * bitrate variable that will receive the current bitrate of the stream.
   * flags variable that will receive a flags field describing protocol and format information. See FSOUND_STATUS_FLAGS. 
   *
   * @return On success, TRUE is returned. On failure, FALSE is returned. 
   */
  public static boolean FSOUND_Stream_Net_GetStatus(FSoundStream stream, IntBuffer values) {
    return nFSOUND_Stream_Net_GetStatus(stream.streamHandle, values, values.position());
  }
  private static native boolean nFSOUND_Stream_Net_GetStatus(long streamHandle, IntBuffer values, int offset);
  
  /**
   * Sets buffer size and thresholds to use when opening new internet streams
   * <p>
   * <b>Remarks</b>
   * Call this function before FSOUND_Stream_Open. This function has no effect on internet streams that are already open
   * </p>
   * @param buffersize Size in bytes of the streaming buffer. Make it bigger to avoid buffer underruns. (Default = 64000)
   * @param prebuffer_percent How much to prebuffer when a stream is first opened. Values are expressed as a percentage from 1 to 99. (Default = 95)
   * @param rebuffer_percent How much to rebuffer after a stream has suffered a buffer underrun. Values are expressed as a percentage from 1 to 99. (Default = 95)
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static native boolean FSOUND_Stream_Net_SetBufferProperties(int buffersize, int prebuffer_percent, int rebuffer_percent);
  
  /**
   * Set a metadata callback for an internet stream
   * <p>
   * <b>Remarks</b>
   * The supplied metadata callback function will be called each time the specified internet stream receives a chunk of metadata.
   * Do not do any time-consuming processing in a metadata callback function or network subsystem performance may degrade.
   * Do not attempt to modify or free any memory passed to a metadata callback function.
   * </p>
   * @param stream to set the metadata callback for
   * @param callback metadata callback to attach to this stream 
   *
   * @return On success, TRUE is returned. On failure, FALSE is returned. 
   */
  public static boolean FSOUND_Stream_Net_SetMetadataCallback(FSoundStream stream, FSoundMetaDataCallback callback) {
    FMOD.registerCallback(FMOD.FSOUND_METADATACALLBACK, stream.streamHandle, stream, callback);
    return nFSOUND_Stream_Net_SetMetadataCallback(stream.streamHandle);
  }
  private static native boolean nFSOUND_Stream_Net_SetMetadataCallback(long streamHandle);
  
  /**
   * Set a proxy server to use for all subsequent internet connections
   * @param proxy The name of a proxy server in host:port format e.g. www.fmod.org:8888 (defaults to port 80 if no port is specified).
   * Basic authentication is supported. To use it, this parameter must be in user:password@host:port format e.g. bob:sekrit123@www.fmod.org:8888
   * Set this parameter to NULL if no proxy is required 
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static native boolean FSOUND_Stream_Net_SetProxy(String proxy);  
  
  /**
   * Extended featured version of FSOUND_Stream_Play.
   * Added functionality includes the ability to start the stream paused. This allows attributes
   * of a stream channel to be set freely before the stream actually starts playing, until FSOUND_SetPaused(FALSE) is used.
   * Also added is the ability to associate the stream channel to a specified DSP unit. This allows
   * the user to 'group' channels into seperate DSP units, which allows effects to be inserted
   * between these 'groups', and allow various things like having one group affected by reverb (wet mix) and another group of 
   * channels unaffected (dry). This is useful to seperate things like music from being affected
   * by DSP effects, while other sound effects are.
   * <p>
   * <b>Remarks</b>
   * When a stream starts to play, it inherits a special high priority (256). 
   * It cannot be rejected by other sound effect channels in the normal fashion as the user can never set a priority above 255 normally. 
   * --------
   * FSOUND_STEREOPAN is recommended for stereo streams if you call FSOUND_SetPan. This puts the left and right channel to full volume.
   * Otherwise a normal pan will give half volume for left and right. See FSOUND_SetPan for more information on this.
   * --------
   * You can use normal channel based commands (such as FSOUND_SetVolume etc) on the return handle, as it is a channel handle.   
   * </p>
   * 
   * @param channel 0+ The absolute channel number in the channel pool. 
   * Remember software channels come first, followed by hardware channels. 
   * You cannot play a software sample on a hardware channel and vice versa.
   * FSOUND_FREE
   * Chooses a free channel to play in. If all channels are used then it
   * selects a channel with a sample playing that has an EQUAL or LOWER priority 
   * than the sample to be played.
   * @param stream already opened stream to be played
   * @param dspunit dsp unit to attach the channel to
   * @param paused Start the stream paused or not. Pausing the stream channel allows attributes to be set before it is unpaused
   * @return On success, a channel handle the stream is playing in is returned. On failure, -1 is returned. 
   */
  public static int FSOUND_Stream_PlayEx(int channel, FSoundStream stream, FSoundDSPUnit dspunit, boolean paused) {
    int res = nFSOUND_Stream_PlayEx(channel, stream.streamHandle, (dspunit != null) ? dspunit.dspHandle : null, paused);
    return res;
  }
  private static native int nFSOUND_Stream_PlayEx(int channel, long stream, ByteBuffer dspunit, boolean paused);  

  /**
   * Sets the internal file buffersize for audio streaming of data for the NEXT stream opened with FSOUND_Stream_Open. 
   * Larger values will consume more memory (see remarks), whereas smaller values may be subject to large delays in disk access, especially from CDROM.
   * <p>
   * <b>Remarks</b>
   * The default setting is 200ms. Under Windows CE it is default to 100ms.
   * To calculate memory usage for a stream buffer, it is a simple matter of calculating sizebytes = streambuffersize * sample rate / 1000 * (bitdepth / 8) * numchannels * 2, where numchannels is 1 for mono,
   * or 2 for stereo files. It is multiplied by 2 because FSOUND stream buffers are double buffers.
   * Note this function does not affect user created streams, as the buffer size is specified in FSOUND_Stream_Create.
   * </p>
   * 
   * @param ms Time in milliseconds between stream updates. FMOD tries to access the disk and 
   * decompress data every period specified. Values less than 50 result in an error  
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static native boolean FSOUND_Stream_SetBufferSize(int ms);
  
  /**
   * Sets a callback function for when a stream has ended
   * <p>
   * <b>Remarks</b>
   * Only calls back when a stream stops. (not when a looping stream reaches its end point)
   * Note it uses a FSOUND_STREAMCALLBACK function callback. This is normally for user streams but for 
   * the sake of re-usability this prototype is used. 'buff' and 'length' are NULL and 0 in this case
   * when the callback occurs. The return value can be TRUE or FALSE it is ignored.
   * -----------
   * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.
   * </p>
   * @param stream to set the metadata callback for
   * @param callback FSoundStreamCallback callback to attach to this stream 
   * @return On success, TRUE is returned. On failure, FALSE is returned. 
   */
  public static boolean FSOUND_Stream_SetEndCallback(FSoundStream stream, FSoundStreamCallback callback) {
    FMOD.registerCallback(FMOD.FSOUND_ENDCALLBACK, stream.streamHandle, stream, callback);
    return nFSOUND_Stream_SetEndCallback(stream.streamHandle);
  }
  private static native boolean nFSOUND_Stream_SetEndCallback(long streamHandle);  
  
  /**
   * Sets the stream to loop the number of times specified by the user. If not called it loops forever
   * <p>
   * <b>Remarks</b>
   * This specifies how many loops, not how many times to play the sound back. Therefore when you specify 0, you will hear the sound once, if you specify 1, you will hear the sound twice, and so on.   
   * </p>
   * 
   * @param stream already opened stream to be played
   * @param count Number of times to loop. 0 would be similar to having FSOUND_LOOP_OFF set. &gt;0 is infinity 
   * @return On success, a channel handle the stream is playing in is returned. On failure, -1 is returned. 
   */
  public static int FSOUND_Stream_SetLoopCount(FSoundStream stream, int count) {
    return nFSOUND_Stream_SetLoopCount(stream.streamHandle, count);
  }
  private static native int nFSOUND_Stream_SetLoopCount(long stream, int count);  
  
  /**
   * Sets the loop points for a stream
   * <p>
   * <b>Remarks</b>
   * For streams, setting looppoints is reasonably accurate but should not be assumed to be perfectly sample accurate in all cases.
   * It depends on the compression format in some cases as seek positions need to be rounded to the nearest compression block offset.
   * FSOUND_MPEGACCURATE will need to be used with mp3 files that use VBR encoding for more accuracy.
   * You cannot call this function wile the stream is playing, it has to be stopped   
   * </p>
   * 
   * @param stream already opened stream to be played
   * @param loopstart The start of the loop, specified in PCM SAMPLES.
   * @param loopend The end of the loop, specified in PCM SAMPLES. 
   * @return On success, TRUE is returned. On failure, FALSE is returned. 
   */
  public static int FSOUND_Stream_SetLoopPoints(FSoundStream stream, int loopstart, int loopend) {
    return nFSOUND_Stream_SetLoopPoints(stream.streamHandle, loopstart, loopend);
  }
  private static native int nFSOUND_Stream_SetLoopPoints(long stream, int loopstart, int loopend);
  
  /**
   * Set a streams mode
   * <p>
   * <b>Remarks</b>
   * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.
   * Only the following modes are accepted, others will be filtered out.
   * FSOUND_LOOP_BIDI, FSOUND_LOOP_NORMAL, FSOUND_LOOP_OFF, FSOUND_2D. FSOUND_LOOP_BIDI is treated as FSOUND_LOOP_NORMAL. FSOUND_2D is accepted only if the sound is not hardware.
   * On playstation 2, FSOUND_HW3D and FSOUND_HW2D modes are accepted
   * </p>
   * 
   * @param stream to have the mode set
   * @param mode The mode bits to set from FSOUND_MODES
   * @return On success, TRUE is returned. On failure, FALSE is returned. 
   */
  public static int FSOUND_Stream_SetMode(FSoundStream stream, int mode) {
    return nFSOUND_Stream_SetMode(stream.streamHandle, mode);
  }
  private static native int nFSOUND_Stream_SetMode(long stream, int mode);
  
  /**
   * Set a streams mode
   * <p>
   * <b>Remarks</b>
   * Position functions for streams talk in bytes and NOT samples.
   * The reason for not taking the header into account is people usually want to know the offset to seek to relative to the start of their data (ie as they see it in soundforge or whatever), not from offset 0 which is almost meaningless if you dont know the format.
   * --------------
   * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.   * </p>
   * </p>
   * 
   * @param stream to have its position set
   * @param position Offset in bytes from start of actual sound data (not including any header)
   * @return On success, TRUE is returned. On failure, FALSE is returned. 
   */
  public static int FSOUND_Stream_SetPosition(FSoundStream stream, int position) {
    return nFSOUND_Stream_SetPosition(stream.streamHandle, position);
  }
  private static native int nFSOUND_Stream_SetPosition(long stream, int position);
  
  /**
   * This function allows the user to describe the playback order of a list of substreams. The substreams will be played back in order seamlessly.
   * <p>
   * <b>Remarks</b>
   * This feature only works with FSB files that have multiple streams stored within it.
   * To remove any sentence, simply call this function with NULL and 0.
   * FMOD copies the list from the supplied pointer. Once the pointer is used, the caller can discard the original array.
   * This function will fail if the stream is playing. The stream must be stopped for it to work.
   * ------------
   * If the stream is opened with FSOUND_NONBLOCKING, and the stream is not ready (it is still opening), then this function will return FALSE.
   * When it is ready, it will return TRUE, but after this call the stream is put back into a non-ready state, because it is asynchronously seeking again.
   * You then have to poll after calling this to make sure the stream is ready.
   * You can either do this by calling FSOUND_Stream_Play repeatedly/once a frame until it is succeeds, or FSOUND_Stream_GetOpenState.   * </p>
   * 
   * @param stream stream to have its position returned.
   * @param sentencelist IntBuffer describing a list of substream indicies to play back.
   * @return On success, TRUE is returned. On failure, FALSE is returned. 
   */
  public static int FSOUND_Stream_SetSubStreamSentence(FSoundStream stream, IntBuffer sentencelist) {
    return nFSOUND_Stream_SetSubStreamSentence(stream.streamHandle, sentencelist, sentencelist.position());
  }
  private static native int nFSOUND_Stream_SetSubStreamSentence(long stream, IntBuffer sentencelist, int offset);  

  /**
   * Sets a callback function for when a stream passes over a WAV tag/marker. These are markers that
   * a sound editing program such as Sound Forge can drop into the actual wave data. FMOD will
   * trigger callbacks with these markers when the stream plays, and pass in the string through the callback that the marker contains
   * <p>
   * <b>Remarks</b>
   * Note it uses a FSOUND_STREAMCALLBACK function callback. This is normally for user streams but for 
   * the sake of re-usability this prototype is used. 'buff' is a null terminated string provided by 
   * the marker. 'len' is the offset in samples that the marker was set at.
   * The return value can be TRUE or FALSE, it is ignored.
   * -----------
   * Note you can save a WAV out using an MP3 wav codec (and then just rename the WAV to MP3 if you like) to get 
   * sync marker support for compressed MP3 files. FMOD will pick up on this and read the markers out.
   * --------------
   * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.
   * </p>
   * @param stream to set the SyncCallback callback for
   * @param callback FSoundStreamCallback callback to attach to this stream 
   * @return On success, TRUE is returned. On failure, FALSE is returned. 
   */
  public static boolean FSOUND_Stream_SetSyncCallback(FSoundStream stream, FSoundStreamCallback callback) {
    FMOD.registerCallback(FMOD.FSOUND_SYNCCALLBACK, stream.streamHandle, stream, callback);
    return FSOUND_Stream_SetSyncCallback(stream.streamHandle);
  }
  private static native boolean FSOUND_Stream_SetSyncCallback(long streamHandle);
  
  /**
   * Sets the current stream's FILE position in MILLISECONDS
   * <p>
   * <b>Remarks</b>
   * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.
   * FSOUND_MPEGACCURATE will need to be used with mp3 files that use VBR encoding for more accuracy.
   * </p>
   * 
   * @param stream to have its position set
   * @param ms Time in milliseconds to seek to.
   * @return On success, TRUE is returned. On failure, FALSE is returned. 
   */
  public static int FSOUND_Stream_SetTime(FSoundStream stream, int ms) {
    return nFSOUND_Stream_SetTime(stream.streamHandle, ms);
  }
  private static native int nFSOUND_Stream_SetTime(long stream, int ms);  
  // ----------------------------------------------------------
  
  // CD functions
  // ==========================================================
  /**
   * Opens/Closes the CD tray
   *
   * @param drive The drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example. 
   * @param open If open is set to true, the CD tray will be opened. If open is set to false, the CD tray will be closed. 
   * @return On success, true is is returned. On failure, false is returned.
   */
  public static native boolean FSOUND_CD_OpenTray(char drive, boolean open);
  
  /**
   * Returns the number of tracks on the currently inserted CD
   *
   * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example. 
   * @return On success, the number of CD tracks on the currently inserted is returned. On failure, 0 is returned
   */
  public static native int FSOUND_CD_GetNumTracks(char drive);  
  
  /**
   * Gets the pause status of the current CD audio track
   *
   * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example. 
   * @return If the track is currently paused, TRUE is returned. if the track is currently not paused, FALSE is returned.
   */
  public static native boolean FSOUND_CD_GetPaused(char drive);
  
  /**
   * Returns the currently playing CD track number
   *
   * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example. 
   * @return On success, the CD track number currently playing is returned. (starts from 1) On failure, 0 is returned
   */
  public static native int FSOUND_CD_GetTrack(char drive);
  
  /**
   * Gets the track length of a CD
   *
   * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
   * @param track The CD track number to query the length of. (starts from 1)  
   * @return On success, the length of the current track in milliseconds is returned. On failure, 0 is returned.
   */
  public static native int FSOUND_CD_GetTrackLength(char drive, int track);    
  
  /**
   * Returns the current track time playing on a CD
   * <p>
   * <b>Remarks</b>
   * This is easily one of the slowest functions in the FMOD API. Please use it sparingly. 
   * It seems like it shouldnt take long, but because of windows MCI API it does, and not just a little bit of time, it takes a LOT. 
   * It seems to poll the CD driver and cause a large delay upon completion of the command. 
   * Different algorithms were used to try and emulate this function such as simply using a timer, but this was very inaccurate, especially when pausing/unpausing a lot.
   * </p> 
   *
   * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
   * @return On success, the position of the current playing track in milliseconds is returned. On failure, 0 is returned
   */
  public static native int FSOUND_CD_GetTrackTime(char drive);
  
  /**
   * Plays a CD Audio track
   * <p>
   * <b>Remarks</b>
   * See FSOUND_CD_SetPlayMode for information on how to control playback of a CD track.
   * FSOUND's CD Playback system, is a non intrusive, non polling system. 
   * This may not mean much to a lot of people, but a polling player (take the windows default CD player) will consistantly poll the CD device to update its status, which causes other applications to jerk, or pause consistantly. 
   * This would be inexcusable in a game, to have the game halt or jerk every second to few seconds or so. 
   * FSOUND uses timing and prediction to loop tracks and update the status of the CD, and never touches the CD device during playback, for TRUE 0% cpu usage.   
   * </p>
   * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
   * @param track The CD track number to query the length of. (starts from 1)
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static native boolean FSOUND_CD_Play(char drive, int track);    
  
  /**
   * Sets the pause status of the currently playing CD audio track
   * 
   * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
   * @param paused TRUE to pause track, FALSE to unpause track 
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static native boolean FSOUND_CD_SetPaused(char drive, boolean paused);    
  
  /**
   * Sets the playback mode of the CD
   * 
   * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
   * @param mode See FSOUND_CDPLAYMODES for a list of valid parameters to send to this function 
   */
  public static native void FSOUND_CD_SetPlayMode(char drive, int mode);      
  
  /**
   * Performs a seek within a track specified by milliseconds
   * <p>
   * <b>Remarks</b>
   * This function will start the track if it is not playing
   * </p>
   * 
   * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
   * @param ms Time to seek into the current track in milliseconds
   * @return On success, TRUE is returned. On failure, FALSE is returned. 
   */
  public static native boolean FSOUND_CD_SetTrackTime(char drive, int ms);
  
  /**
   * Sets the volume of the playing CD audio
   * 
   * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
   * @param volume An integer value from 0-255. 0 being the lowest volume, 255 being the highest (full).
   * @return On success, TRUE is returned. On failure, FALSE is returned. 
   */
  public static native boolean FSOUND_CD_SetVolume(char drive, int volume);        
  
  /**
   * Stops the currently playing CD audio track
   * 
   * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
   * @return On success, TRUE is returned. On failure, FALSE is returned. 
   */
  public static native boolean FSOUND_CD_Stop(char drive);   
  // ----------------------------------------------------------
  
  // DSP functions
  // ==========================================================
  /**
   * Clears the mixbuffer, especially handy if you are doing a large file operation which 
   * halts the system. 
   * You might try and stop all the sounds, but if you do your file operation straight after
   * this, it will not have a chance to flush the mixbuffer normally, so this function is called.
   * It stops the effect of stuttering looping sound while your file operation happens.
   * <p>
   * <b>Remarks</b>
   * The best way to do it is like this. Turn off the sfx and music DSP units, clear the mix buffer,
   * then when the operation that halts the machine is done, just re-enable the sfx and music DSP units.
   * Disabling these units stops the timer trying to get 1 or 2 more mixes in during the file operation,
   * which will cause more stuttering.
   * ie.
   * FSOUND_DSP_SetActive(FSOUND_DSP_GetSFXUnit(), FALSE);
   * FSOUND_DSP_SetActive(FSOUND_DSP_GetMusicUnit(), FALSE);
   * FSOUND_DSP_ClearMixBuffer();
   * //
   * // maching halting operation here
   * //
   * FSOUND_DSP_SetActive(FSOUND_DSP_GetSFXUnit(), TRUE);
   * FSOUND_DSP_SetActive(FSOUND_DSP_GetMusicUnit(), TRUE);
   * </p>
   */
  public static native void FSOUND_DSP_ClearMixBuffer();
  
  /**
   * Creates a DSP unit, and places it in the DSP chain position specified by the priority
   * parameter. Read the remarks section carefully for issues regarding DSP units.
   * DSP units are freed with FSOUND_DSP_Free
   * <p>
   * <b>Remarks</b>
   * A dsp unit is NOT ACTIVE by default. You have to activate it with FSOUND_DSP_SetActive
   * ---------------------------------------------------------------------------------------
   * Priorities and default system units.
   * ---------------------------------------------------------------------------------------
   * A note on priorities. FSOUND processes DSP units in order of priority. A 0 priority
   * unit gets processed first, a 1 priority unit gets processed next, and so on. 
   * FSOUND actually uses these DSP units to mix its sound effects and music! Yes, you have
   * access to them (be careful!). It is possible to totally remove, replace or deactivate 
   * all of FSOUND's system units so that it does nothing at all!
   * FSOUND has preinstalled default system units at the following priority locations:
   * FSOUND_DSP_DEFAULTPRIORITY_CLEARUNIT (priority 0) - Clear Unit. This unit clears out 
   * the mixbuffer for the next units to mix into. You can disable this unit and replace
   * it with something other than a clearer, such as a scaler, which fades down the mix
   * buffer instead of clearing it, to produce a very rough echo effect.
   * FSOUND_DSP_DEFAULTPRIORITY_SFXUNIT (priority 100) - SFX Unit. This unit mixes sound 
   * effect channels into the mix buffer, which was previously cleared with the Clear 
   * Unit. 
   * FSOUND_DSP_DEFAULTPRIORITY_MUSICUNIT (priority 200) - Music Unit. This unit mixes all 
   * music channels into the mix buffer, which was previously mixed into with the SFX 
   * Unit. 
   * FSOUND_DSP_DEFAULTPRIORITY_CLIPANDCOPYUNIT (priority 1000) - Clip and Copy Unit. This 
   * unit takes the finally mixed buffer, and clips it to the output stream size (if it
   * needs to), and then sends it off to the sound device. It is done last. If this is
   * disabled you will hear no sound.
   * ---------------------------------------------------------------------------------------
   * Buffer Lengths.
   * ---------------------------------------------------------------------------------------
   * The 'length' value of the DSP callback is roughly 20ms worth of data.
   * Use FSOUND_DSP_GetBufferLength to get the exact callback length.
   * ---------------------------------------------------------------------------------------
   * Buffer Widths
   * ---------------------------------------------------------------------------------------
   * Remember that FSOUND uses different buffer types depending on what type of mixer it is.
   * You will have to compensate for this by writing different routines depending on the 
   * mixer type (ie mmx or non mmx), just like FSOUND does. 
   * Currently there are the 3 types of mixers and their buffer sizes.
   * You can get the type of mixer being used by calling the FSOUND_GetMixer function.
   * You may want to check on this inside your callback, or set up a function pointer system,
   * whatever you think is suitable (it costs nothing to do a FSOUND_GetMixer every time).
   * - FSOUND_MIXER_BLENDMODE : This buffer is a stereo, signed 32bit buffer (8 bytes per 
   * sample). The data is in integer format.
   * Data written to this buffer is not clipped and passed to the output stream until the 
   * very end of the chain (the clip and copy unit). For this type of mixer, you dont 
   * have to worry about clipping becuase FSOUND does this for you.
   * - FSOUND_MIXER_QUALITY_FPU / FSOUND_MIXER_QUALITY_FPU_VOLUMERAMP: This buffer is also a 
   * stereo, signed 32bit buffer (8 bytes per sample). This data is in floating point 
   * format.
   * The same clip and copy rules apply here as for the above mixer.
   * - Any MMX based mixer : This buffer is a stereo, signed 16bit buffer (4 bytes per sample).
   * When writing to this buffer, you must make sure the result does not overflow this 
   * signed 16bit range.
   * If you add data into to this buffer, make sure it is clipped to a signed 16bit range
   * before writing it back. FSOUND only copies this data to the output stream, it does
   * not clip it.
   * ---------------------------------------------------------------------------------------
   * Speed
   * ---------------------------------------------------------------------------------------
   * DSP Units are processed then and there, inside the mixing routine. Remember to make
   * your process as FAST as possible, or the output device's play cursor will catch up to
   * FSOUND's write cursor while your routine takes its time to complete, and make it start 
   * to break up. 
   * So basically, if it isnt fast, then FSOUND will not be able to send the data to the
   * output device in time for the next mixer update, and the result will be corrupted sound.
   * FSOUND_DSP_MixBuffers is available now, so if you need to mix some raw data into the output 
   * buffer quickly, you can use FSOUND's own optimized mixer directly to do it!
   * Finally, you can see how your routine affects cpu usage, by using FSOUND_GetCPUUsage.
   * The cpu usage returned by this function includes any time spent in DSP units as well.
   * (this function times everything). If you are really bored, you can see how much FSOUND's
   * system units take cpu-wise, by turning them on and off and seeing how they affect 
   * performance.
   * </p>
   * @param callbackHandler This is a reference to your DSP Unit callback, of type FSOUND_DSPCALLBACK.
   * The prototype for a callback is declared in the following fashion.
   * Callbacks must return a pointer to the buffer you work on, so that
   * the next dsp unit can work on it. 
   * See the definition of FSOUND_DSPCALLBACK for more.
   * @param priority Order in the priority chain. Valid numbers are 0 to 1000, 0 being
   * highest priority (first), with 1000 being lowest priority (last).
   * Note that FSOUNDs soundeffects mixers and copy routines are considered
   * part of this DSP unit chain which you can play with. 
   * @return On success, a new valid DSP unit is returned. On failure, NULL is returned.
   */
  public static FSoundDSPUnit FSOUND_DSP_Create(FSoundDSPCallback callbackHandler, int priority) {
  	FSoundDSPUnit dspUnit = null;
    ByteBuffer handle = nFSOUND_DSP_Create(priority);
    if(handle != null) {
      ByteBuffer dspID     = (ByteBuffer) BufferUtils.createByteBuffer(8).putLong(FSoundDSPUnit.getNextId()).flip(); 
    	dspUnit= new FSoundDSPUnit(handle, dspID);
    	FMOD.registerCallback(FMOD.FSOUND_DSPCALLBACK, dspID.getLong(0), dspUnit, callbackHandler);
    }
    return dspUnit;
  }
  private static native ByteBuffer nFSOUND_DSP_Create(int priority);
  
  /**
   * Frees and removes a DSP unit from the DSP chain
   * 
   * @param unit DSP unit to be freed 
   */
  public static void FSOUND_DSP_Free(FSoundDSPUnit unit) {
  	nFSOUND_DSP_Free(unit.dspHandle);
  	FMOD.registerCallback(FMOD.FSOUND_DSPCALLBACK, unit.dspTrackingID.get(0), unit, null);
  }
  private static native void nFSOUND_DSP_Free(ByteBuffer dspUnitHandle);  
  
  /**
   * Allows the user to toggle a DSP unit on or off
   * <p>
   * <b>Remarks</b>
   * It is possible to toggle on and off FSOUNDs internal DSP units, though not recommended
   * </p>
   * @param unit DSP unit to have its active flag changed
   * @param active Flag to say whether DSP unit should be rendered active or inactive. valid values are TRUE or FALSE  
   */
  public static void FSOUND_DSP_SetActive(FSoundDSPUnit unit, boolean active) {
   nFSOUND_DSP_SetActive(unit.dspHandle, active);
  }
  private static native void nFSOUND_DSP_SetActive(ByteBuffer dspUnitHandle, boolean active);  
  
  /**
   * Returns if a DSP unit is active or not
   * <p>
   * <b>Remarks</b>
   * It is possible to toggle on and off FSOUNDs internal DSP units, though not recommended
   * </p>
   * @param unit DSP unit to have its active flag returned
   * @return On success, TRUE is returned. On failure, FALSE is returned 
   */
  public static boolean FSOUND_DSP_GetActive(FSoundDSPUnit unit) {
   return nFSOUND_DSP_GetActive(unit.dspHandle);
  }
  private static native boolean nFSOUND_DSP_GetActive(ByteBuffer dspUnitHandle);  
  
  /**
   * Returns the buffer lenth passed by the DSP system to DSP unit callbacks, so you can allocate memory etc 
   * using this data
   * <p>
   * <b>Remarks</b>
   * Remember this is samples not bytes. To convert to bytes you 
   * will have to multiply by 4 for mmx mixers, 8 for other mixers.
   * (a stereo 16bit sample = 4 bytes, and a stereo 32bit sample (ie fpu) = 8 bytes)
   * </p>
   * @return The size of the DSP unit buffer in SAMPLES (not bytes)
   */
  public static native int FSOUND_DSP_GetBufferLength();  
  
  /**
   * This is the total size in samples (not bytes) of the FSOUND mix buffer. This is affected
   * by FSOUND_SetBufferSize.
   * <p>
   * <b>Remarks</b>
   * Remember this is samples not bytes. To convert to bytes you 
   * will have to multiply by 4 for mmx mixers, 8 for other mixers.
   * (a stereo 16bit sample = 4 bytes, and a stereo 32bit sample (ie fpu) = 8 bytes)
   * </p>
   * @return The size of the FSOUND mixing buffer in SAMPLES (not bytes).
   */
  public static native int FSOUND_DSP_GetBufferLengthTotal();
  
  /**
   * Changes a DSP Unit's priority position in the DSP chain
   * <p>
   * <b>Remarks</b>
   * DSP units with the same priority as a previous unit already in the chain will be placed
   * AFTER all like priority units
   * </p>
   * @param unit DSP unit to have its priority changed
   * @param priority Order in the priority chain. Valid numbers are 0 to 1000, 0 being highest priority (first), with 1000 being lowest priority (last).
   */
  public static void FSOUND_DSP_SetPriority(FSoundDSPUnit unit, int priority) {
   nFSOUND_DSP_SetPriority(unit.dspHandle, priority);
  }
  private static native void nFSOUND_DSP_SetPriority(ByteBuffer dspUnitHandle, int priority);  
  
  /**
   * Returns the priority status in the DSP chain, of a specified unit.
   * <p>
   * <b>Remarks</b>
   * DSP units with the same priority as a previous unit already in the chain will be placed
   * AFTER all like priority units
   * </p>
   * @param unit DSP unit to get priority value from
   * @return On success, the priority of the unit, from 0 to 1000. On failure, -1 is returned.
   */
  public static int FSOUND_DSP_GetPriority(FSoundDSPUnit unit) {
   return nFSOUND_DSP_GetPriority(unit.dspHandle);
  }
  private static native int nFSOUND_DSP_GetPriority(ByteBuffer dspUnitHandle);
  
  /**
   * Returns a reference to FSOUND's system DSP clear unit
   * <p>
   * <b>Remarks</b>
   * The FSOUND clear DSP unit simply sets the mix buffer to 0, silencing it
   * </p>
   * @return reference to the DSP unit
   */
  public static FSoundDSPUnit FSOUND_DSP_GetClearUnit() {
   if(FMOD.fmodClearUnit == null) {
   	FMOD.fmodClearUnit = new FSoundDSPUnit(nFSOUND_DSP_GetClearUnit());
   }
   return FMOD.fmodClearUnit;
  }
  private static native ByteBuffer nFSOUND_DSP_GetClearUnit();  
  
  /**
   * Returns a reference to FSOUND's system Clip and Copy DSP unit
   * <p>
   * <b>Remarks</b>
   * The FSOUND ClipAndCopy DSP unit clips the 32bit buffer down to fit the soundcard's 16bit stereo output, and sends it off to the hardware.
   * </p>
   * @return reference to the DSP unit
   */
  public static FSoundDSPUnit FSOUND_DSP_GetClipAndCopyUnit() {
   if(FMOD.fmodClipAndCopyUnit == null) {
    FMOD.fmodClipAndCopyUnit = new FSoundDSPUnit(nFSOUND_DSP_GetClipAndCopyUnit());
   }
   return FMOD.fmodClipAndCopyUnit;
  }
  private static native ByteBuffer nFSOUND_DSP_GetClipAndCopyUnit();  
  
  /**
   * Returns a reference to FSOUND's system DSP Music mixer unit
   * <p>
   * <b>Remarks</b>
   * The FSOUND Music DSP executes the FMUSIC engine and mixes the sounds spawned by the music player
   * </p>
   * @return reference to the DSP unit
   */
  public static FSoundDSPUnit FSOUND_DSP_GetMusicUnit() {
   if(FMOD.fmodMusicUnit == null) {
    FMOD.fmodMusicUnit = new FSoundDSPUnit(nFSOUND_DSP_GetMusicUnit());
   }
   return FMOD.fmodMusicUnit;
  }
  private static native ByteBuffer nFSOUND_DSP_GetMusicUnit();  
  
  /**
   * Returns a reference to FSOUND's system DSP SFX mixer unit
   * <p>
   * <b>Remarks</b>
   * The FSOUND SFX DSP unit mixes sound effects together spawned by the user
   * </p>
   * @return reference to the DSP unit
   */
  public static FSoundDSPUnit FSOUND_DSP_GetSFXUnit() {
   if(FMOD.fmodSFXUnit == null) {
    FMOD.fmodSFXUnit = new FSoundDSPUnit(nFSOUND_DSP_GetSFXUnit());
   }
   return FMOD.fmodSFXUnit;
  }
  private static native ByteBuffer nFSOUND_DSP_GetSFXUnit();  
  
  /**
   * Returns a reference to FSOUND's system DSP FFT processing unit
   * <p>
   * <b>Remarks</b>
   * The FSOUND FFT DSP executes the FFT engine to allow FSOUND_DSP_GetSpectrum to be used.
   * The FFT unit is off by default, due to the cpu expense incurred in running. Turn it on to use FSOUND_DSP_GetSpectrum
   * </p>
   * @return reference to the DSP unit
   */
  public static FSoundDSPUnit FSOUND_DSP_GetFFTUnit() {
   if(FMOD.fmodFFTUnit == null) {
    FMOD.fmodFFTUnit = new FSoundDSPUnit(nFSOUND_DSP_GetFFTUnit());
   }
   return FMOD.fmodFFTUnit;
  }
  private static native ByteBuffer nFSOUND_DSP_GetFFTUnit();  
  
  /**
   * Function to return a FloatBuffer to the current spectrum buffer. The buffer contains 512 floating
   *  point values that represent each frequency band's amplitude for the current FMOD SoundSystem
   * mixing buffer. The range of frequencies covered by the spectrum is 1 to the nyquist frequency
   * or half of the output rate. So if the output rate is 44100, then frequencies provided are up
   * to 22050. (entry 511)
   * <p>
   * <b>Remarks</b>
   * Note that hardware sounds, MIDI, files do not register on the spectrum graph as they are not run through FMODs DSP system.
   * Note that to use this you have to turn on the FSOUND FFT DSP unit. This is achieved by calling FSOUND_DSP_GetFFTUnit, then using FSOUND_DSP_SetActive to turn it on.
   * </p>
   * @return FloatBuffer containing 512 floats
   */
  public static FloatBuffer FSOUND_DSP_GetSpectrum() {
    return nFSOUND_DSP_GetSpectrum().order(ByteOrder.nativeOrder()).asFloatBuffer();
   }
   private static native ByteBuffer nFSOUND_DSP_GetSpectrum();
   
   /**
    * Allows the user to mix their own data from one buffer to another, using FSOUNDs optimized
    * mixer routines. This was mainly provided for DSP routines, though it can be used for 
    * anything.
    * <p>
    * <b>Remarks</b>
    * 'destbuffer' should always the format of the mixing output buffer, as it will use the mixer
    * currently running to do the mixing.
    * For MMX it is 16bit stereo, so it is 4 bytes per output sample (word left, word right)
    * For Standard Blend mode it is 32bit stereo, so it is 8 bytes per output sample (left dword, right dword)
    * For FPU mixer it is 32bit float stereo, so it is 8 bytes per output sample (left float, right float)
    * FSOUND_GetMixer can be used to determine which mixer is being used.
    * </p>
    * @param destbuffer Pointer to a buffer to have the data mixed into.
    * @param srcbuffer Pointer to the source buffer to be mixed in.
    * @param len Amount to mix in SAMPLES.
    * @param freq Speed to mix data to output buffer. Remember if you mix at a rate
    * different than the output rate, the buffer lengths will have to be
    * different to compensate. Ie if the output rate is 44100 and you supply
    * a value of 88200 to FSOUND_DSP_MixBuffers, you will only need a destbuffer
    * that is half the size of srcbuffer. If you supply a value of 22050 then
    * you will need a destbuffer that is twice as big as srcbuffer. If they
    * are both the same size then it will only mix half of the data.
    * @param vol volume scalar value of mix. Valid values are 0 (silence) to 255
    * (full volume). See FSOUND_SetVolume for more information.
    * @param pan pan value for data being mixed. Valid values are 0 (full left), 128
    * (middle), 255 (full right) and FSOUND_STEREOPAN. See FSOUND_SetPan for
    * more information.
    * @param mode Bit settings to describe the source buffer. Valid values are found in
    * FSOUND_MODES, but only 8/16bit and stereo/mono flags are interpreted, other flags are ignored. 
    * @return On success, TRUE is returned. On failure, FALSE is returned.
    */
   public static boolean FSOUND_DSP_MixBuffers(
   		ByteBuffer destbuffer, ByteBuffer srcbuffer, int len, int freq, int vol, int pan, int mode) {
   	return nFSOUND_DSP_MixBuffers(
   			destbuffer, destbuffer.position(), srcbuffer, srcbuffer.position(), len, freq, vol, pan, mode);
   }
   private static native boolean nFSOUND_DSP_MixBuffers(
   		ByteBuffer destbuffer, int destBufferOffset, ByteBuffer srcbuffer, int srcBufferOffset, 
			int len, int freq, int vol, int pan, int mode);
  // ----------------------------------------------------------
  
  // FX functions
  // ==========================================================
  /**
   * Disables effect processing for ALL effects on the specified channel
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this will disable fx on ALL channels available.
   * This command can only be issued while the channel is paused or stopped.
   * </p>
   * 
   * @param channel Channel number/handle to disable all fx for 
   * @return On success, TRUE is returned. On failure, FALSE is returned 
   */
  public static native boolean FSOUND_FX_Disable(int channel);  
  
  /**
   * Enables effect processing for the specified channel. This command continues to add effects to a channel (up to 16) until FSOUND_FX_Disable is called.
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported. Passing this will enable fx on ALL channels available.
   * This command can only be issued while the channel is paused.
   * If an effect is not enabled, then it will not be affected by its corresponding FSOUND_FX_Set functions.
   * This function must be played after a paused PlaySoundEx (ie FSOUND_PlaySoundEx(FSOUND_FREE, sound, NULL, TRUE)), and before
   * the FSOUND_SetPaused(FALSE) so that the hardware can get the resource before it starts playing.
   * A total of 16 FX per channel is allowed, any more will result in an error. FX are reset to 0 after a sound is stopped or played. (but as above, before the unpausing of a play-paused sound).
   * Warning : This function is expensive to call as it has to set up fx buffers etc. It is best to call it once, reserve the channel then reuse the channel index when calling playsound without calling it again.
   * Note : Channels with FX enabled sounds cannot have their frequency changed.
   * </p>
   * 
   * @param channel Channel number/handle to disable all fx for 
   * @param fxtype A single fx enum value to enable certain effects. 
   * @return On success, an FX id is returned. On failure, -1 is returned 
   */
  public static native int FSOUND_FX_Enable(int channel, int fxtype);  
  
  /**
   * Sets the parameters for the chorus effect on a particular channel
   * <p>
   * <b>Remarks</b>
   * Make sure you have enabled this effect with FSOUND_FX_CHORUS before using this function.   
   * </p>
   * 
   * @param fxid fx handle generated by FSOUND_FX_Enable, to set chorus parameters for. 
   * @param WetDryMix Ratio of wet (processed) signal to dry (unprocessed) signal. Must be in the range from 0 through 100 (all wet). 
   * @param Depth Percentage by which the delay time is modulated by the low-frequency oscillator, in hundredths of a percentage point. Must be in the range from 0 through 100. The default value is 25. 
   * @param Feedback Percentage of output signal to feed back into the effects input, in the range from -99 to 99. The default value is 0. 
   * @param Frequency Frequency of the LFO, in the range from 0 to 10. The default value is 0. 
   * @param Waveform Waveform of the LFO. Defined values are 0 triangle. 1 sine. By default, the waveform is a sine. 
   * @param Delay Number of milliseconds the input is delayed before it is played back, in the range from 0 to 20. The default value is 0 ms.
   * @param Phase Phase differential between left and right LFOs, in the range from 0 through 4. Possible values are defined as follows: 
   * 0 -180 degrees
   * 1 - 90 degrees
   * 2 0 degrees
   * 3 90 degrees
   * 4 180 degrees
   * @return On success, TRUE is returned. On failure, FALSE is returned.
 
   */
  public static native boolean FSOUND_FX_SetChorus(
    int fxid, float WetDryMix, float Depth, float Feedback, float Frequency, int Waveform, float Delay, int Phase);
  
  /**
   * Sets the parameters for the compressor effect on a particular channel
   * <p>
   * <b>Remarks</b>
   * Make sure you have enabled this effect with FSOUND_FX_COMPRESSOR before using this function   
   * </p>
   * 
   * @param fxid fx handle generated by FSOUND_FX_Enable, to set compressor parameters for.
   * @param Gain Output gain of signal after compression, in the range from -60 to 60. The default value is 0 dB. 
   * @param Attack Time before compression reaches its full value, in the range from 0.01 to 500. The default value is 0.01 ms. 
   * @param Release Speed at which compression is stopped after input drops below fThreshold, in the range from 50 to 3000. The default value is 50 ms. 
   * @param Threshold Point at which compression begins, in decibels, in the range from -60 to 0. The default value is -10 dB. 
   * @param Ratio Compression ratio, in the range from 1 to 100. The default value is 10, which means 10:1 compression. 
   * @param Predelay Time after lThreshold is reached before attack phase is started, in milliseconds, in the range from 0 to 4. The default value is 0 ms.  
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static native boolean FSOUND_FX_SetCompressor(
    int fxid, float Gain, float Attack, float Release, float Threshold, float Ratio, float Predelay);
  
  /**
   * Sets the parameters for the distortion effect on a particular channel
   * <p>
   * <b>Remarks</b>
   * Make sure you have enabled this effect with FSOUND_FX_DISTORTION before using this function   
   * </p>
   * 
   * @param fxid fx handle generated by FSOUND_FX_Enable, to set distortion parameters for.
   * @param Gain Amount of signal change after distortion, in the range from -60 through 0. The default value is 0 dB. 
   * @param Edge Percentage of distortion intensity, in the range in the range from 0 through 100. The default value is 50 percent. 
   * @param PostEQCenterFrequency Center frequency of harmonic content addition, in the range from 100 through 8000. The default value is 4000 Hz. 
   * @param PostEQBandwidth Width of frequency band that determines range of harmonic content addition, in the range from 100 through 8000. The default value is 4000 Hz. 
   * @param PreLowpassCutoff Filter cutoff for high-frequency harmonics attenuation, in the range from 100 through 8000. The default value is 4000 Hz.  
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static native boolean FSOUND_FX_SetDistortion(
    int fxid, float Gain, float Edge, float PostEQCenterFrequency, float PostEQBandwidth, float PreLowpassCutoff);
  
  /**
   * Sets the parameters for the echo effect on a particular channel
   * <p>
   * <b>Remarks</b>
   * Make sure you have enabled this effect with FSOUND_FX_Enable and FSOUND_FX_ECHO before using this function.   
   * </p>
   * 
   * @param fxid fx handle generated by FSOUND_FX_Enable, to set echo parameters for.
   * @param WetDryMix Ratio of wet (processed) signal to dry (unprocessed) signal. Must be in the range from 0 through 100 (all wet). 
   * @param Feedback Percentage of output fed back into input, in the range from 0 through 100. The default value is 0. 
   * @param LeftDelay Delay for left channel, in milliseconds, in the range from 1 through 2000. The default value is 333 ms. 
   * @param RightDelay Delay for right channel, in milliseconds, in the range from 1 through 2000. The default value is 333 ms. 
   * @param PanDelay Value that specifies whether to swap left and right delays with each successive echo. The default value is FALSE, meaning no swap. Possible values are defined as TRUE or FALSE. 
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static native boolean FSOUND_FX_SetEcho(
    int fxid, float WetDryMix, float Feedback, float LeftDelay, float RightDelay, int PanDelay);  
  
  /**
   * Sets the parameters for the echo effect on a particular channel
   * <p>
   * <b>Remarks</b>
   * Make sure you have enabled this effect with FSOUND_FX_Enable and FSOUND_FX_FLANGER before using this function.   
   * </p>
   * 
   * @param fxid fx handle generated by FSOUND_FX_Enable, to set flanger parameters for.
   * @param WetDryMix Ratio of wet (processed) signal to dry (unprocessed) signal. Must be in the range from 0 through 100 (all wet). 
   * @param Depth Percentage by which the delay time is modulated by the low-frequency oscillator (LFO), in hundredths of a percentage point. Must be in the range from 0 through 100. The default value is 25. 
   * @param Feedback Percentage of output signal to feed back into the effects input, in the range from -99 to 99. The default value is 0. 
   * @param Frequency Frequency of the LFO, in the range from 0 to 10. The default value is 0. 
   * @param Waveform Waveform of the LFO. By default, the waveform is a sine. Possible values are defined as follows: 
   * 0 - Triangle. 
   * 1 - Sine.
   * @param Delay Number of milliseconds the input is delayed before it is played back, in the range from 0 to 4. The default value is 0 ms. 
   * @param Phase Phase differential between left and right LFOs, in the range from 0 through 4. Possible values are defined as follows: 
   * 0 -180 degrees
   * 1 - 90 degrees
   * 2 0 degrees
   * 3 90 degrees
   * 4 180 degrees 
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static native boolean FSOUND_FX_SetFlanger(
    int fxid, float WetDryMix, float Depth, float Feedback, float Frequency, int Waveform, float Delay, int Phase);    

  /**
   * Sets the parameters for the echo effect on a particular channel
   * <p>
   * <b>Remarks</b>
   * Make sure you have enabled this effect with FSOUND_FX_Enable and FSOUND_FX_GARGLE before using this function.   
   * </p>
   * 
   * @param fxid fx handle generated by FSOUND_FX_Enable, to set gargle parameters for.
   * @param RateHz Rate of modulation, in Hertz. Must be in the range from 1 through 1000.
   * @param WaveShape Shape of the modulation wave. The following values are defined.
   * 0 - Triangular wave.
   * 1 - Square wave.
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static native boolean FSOUND_FX_SetGargle(int fxid, int RateHz, int WaveShape);  
  
  /**
   * Sets the parameters for the I3DL2 Reverb effect on a particular channel
   * <p>
   * <b>Remarks</b>
   * Make sure you have enabled this effect with FSOUND_FX_Enable and FSOUND_FX_I3DL2REVERB before using this function.   
   * </p>
   * 
   * @param fxid fx handle generated by FSOUND_FX_Enable, to set I3DL2 Reverb parameters for.
   * @param Room Attenuation of the room effect, in millibels (mB), in the range from -10000 to 0. The default value is -1000 mB.
   * @param RoomHF Attenuation of the room high-frequency effect, in mB, in the range from -10000 to 0. The default value is 0 mB.
   * @param RoomRolloffFactor Rolloff factor for the reflected signals, in the range from 0 to 10. The default value is 0.0. The rolloff factor for the direct path is controlled by the listener.
   * @param DecayTime Decay time, in seconds, in the range from .1 to 20. The default value is 1.49 seconds.
   * @param DecayHFRatio Ratio of the decay time at high frequencies to the decay time at low frequencies, in the range from 0.1 to 2. The default value is 0.83.
   * @param Reflections Attenuation of early reflections relative to lRoom, in mB, in the range from -10000 to 1000. The default value is -2602 mB.
   * @param ReflectionsDelay Delay time of the first reflection relative to the direct path, in seconds, in the range from 0 to 0.3. The default value is 0.007 seconds.
   * @param Reverb Attenuation of late reverberation relative to lRoom, in mB, in the range from -10000 to 2000. The default value is 200 mB.
   * @param ReverbDelay Time limit between the early reflections and the late reverberation relative to the time of the first reflection, in seconds, in the range from 0 to 0.1. The default value is 0.011 seconds.
   * @param Diffusion Echo density in the late reverberation decay, in percent, in the range from 0 to 100. The default value is 100.0 percent.
   * @param Density Modal density in the late reverberation decay, in percent, in the range from 0 to 100. The default value is 100.0 percent.
   * @param HFReference Reference high frequency, in hertz, in the range from 20 to 20000. The default value is 5000.0 Hz.
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static native boolean FSOUND_FX_SetI3DL2Reverb(
    int fxid, int Room, int RoomHF, float RoomRolloffFactor, float DecayTime,
		float DecayHFRatio, int Reflections, float ReflectionsDelay, int Reverb,
		float ReverbDelay, float Diffusion, float Density, float HFReference);  
  
  /**
   * Sets the parameters for the I3DL2 Reverb effect on a particular channel
   * <p>
   * <b>Remarks</b>
   * Make sure you have enabled this effect with FSOUND_FX_Enable and FSOUND_FX_PARAMEQ before using this function.   
   * </p>
   * 
   * @param fxid fx handle generated by FSOUND_FX_Enable, to set ParamEQ parameters for.
   * @param Center Center frequency, in hertz, in the range from 80 to 16000. This value cannot exceed one-third of the frequency of the buffer. Default is 8000.
   * @param Bandwidth Bandwidth, in semitones, in the range from 1 to 36. Default is 12.
   * @param Gain Gain, in the range from -15 to 15. Default is 0.
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static native boolean FSOUND_FX_SetParamEQ(int fxid, float Center, float Bandwidth, float Gain);  
    
  /**
   * Sets the parameters for the Waves Reverb effect on a particular channel
   * <p>
   * <b>Remarks</b>
   * Make sure you have enabled this effect with FSOUND_FX_Enable and FSOUND_WAVES_REVERB before using this function.   
   * </p>
   * 
   * @param fxid fx handle generated by FSOUND_FX_Enable, to set ParamEQ parameters for.
   * @param InGain Input gain of signal, in decibels (dB), in the range from -96 through 0. The default value is 0 dB.
   * @param ReverbMix Reverb mix, in dB, in the range from -96 through 0. The default value is 0 dB.
   * @param ReverbTime Reverb time, in milliseconds, in the range from .001 through 3000. The default value is 1000.
   * @param HighFreqRTRatio In the range from .001 through .999. The default value is 0.001.
   * @return On success, TRUE is returned. On failure, FALSE is returned.
   */
  public static native boolean FSOUND_FX_SetWavesReverb(int fxid, float InGain, float ReverbMix, float ReverbTime, float HighFreqRTRatio);  
  // ----------------------------------------------------------
  
  // Recording functions
  // ==========================================================
  /**
   * Returns the currently selected recording driver number. Drivers are enumerated when selecting a driver 
   * with FSOUND_Record_SetDriver or other driver related functions such as FSOUND_Record_GetNumDrivers or 
   * FSOUND_Record_GetDriverName
   * 
   * @return Currently selected driver id 
   */
  public static native int FSOUND_Record_GetDriver();  
  
  /**
   * Returns the name of the selected recording driver. Drivers are enumerated when selecting a driver with
   * FSOUND_Record_SetDriver or other driver related functions such as FSOUND_Record_GetNumDrivers or FSOUND_Record_GetDriver
   * 
   * @param id Enumerated driver ID. This must be in a valid range delimited by FSOUND_Record_GetNumDrivers,
   * @return On success, a string containing the name of the specified device is returned. 
   * The number of drivers enumerated can be found with FSOUND_Record_GetNumDrivers. On failure, NULL is returned 
   */
  public static native String FSOUND_Record_GetDriverName(int id);
  
  /**
   * Returns the number of sound cards or devices enumerated for the current input type. (Direct
   * Sound, WaveOut etc.)
   * 
   * @return Total number of enumerated sound devices 
   */
  public static native int FSOUND_Record_GetNumDrivers();   
  
  /**
   * Gets the position in the sample buffer that has been recorded to
   * <p>
   * <b>Remarks</b>
   * Note. This is not the 'recording cursor', but rather the latest point that the input has been copied to your sample
   * </p>
   * 
   * @return On success, the offset in SAMPLES, for the record buffer that the input device has just written up to is returned.
   * On failure (recording device hasnt been started), -1 is returned.
   */
  public static native int FSOUND_Record_GetPosition();  
  
  /**
   * Returns the name of the selected recording driver. Drivers are enumerated when selecting a driver with
   * FSOUND_Record_SetDriver or other driver related functions such as FSOUND_Record_GetNumDrivers or FSOUND_Record_GetDriver
   * 
   * @param driverno Recording driver number to select. &gt;=0 will select the DEFAULT recording sound driver. &lt;0 Selects other valid drivers that can be listed with FSOUND_Record_GetDriverName.
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static native boolean FSOUND_Record_SetDriver(int driverno);  

  /**
   * Starts recording into a predefined sample using the sample's default playback rate as the recording rate
   * <p>
   * <b>Remarks</b>
   * If you want to play back the sample at the same time is is recording, you will have to play the sound and try and keep it just behind the recording cursor.
   * Under FSOUND_OUTPUT_OSS mode, it is single duplex, so playback will stop when recording is in progress! Try FSOUND_OUTPUT_ALSA for full duplex as they have better drivers in this respect.
   * -------------
   * The recording/playback rates are slightly innacurate and are not identical (ie 44100.0 for playback, 44100.1 for recording), so one could possibly be faster or slower than the other. In this case the recording and the playback cursor could overlap, and the output will sound corrupted. 
   * To counter this you might adjust the playback frequency of the channel you are playing the record sample on while it plays, using FSOUND_GetCurrentPosition and FSOUND_Record_GetPosition as calibration points.
   * In the recording sample there is an example of trying to play back sound as it records, and the mechanism to try and keep the 2 cursors a safe distance from each other is employed.
   * </p>
   * @param sample The sample to record into.
   * @param loop TRUE or FALSE flag whether the recorder should keep recording once it has hit the end,
   * and start from the start again, therefore creating a continuous recording session into that
   * sample buffer. Looping the recording buffer is good for realtime processing of recorded
   * information, as you can record and playback the sample at the same time.
   * @return TRUE or FALSE flag whether the recorder should keep recording once it has hit the end, 
   * and start from the start again, therefore creating a continuous recording session into that 
   * sample buffer. Looping the recording buffer is good for realtime processing of recorded
   * information, as you can record and playback the sample at the same time
   */
  public static boolean FSOUND_Record_StartSample(FSoundSample sample, boolean loop) {
    return nFSOUND_Record_StartSample(sample.sampleHandle, loop);
  }
  private static native boolean nFSOUND_Record_StartSample(long sampleHandle, boolean loop);  
  
  /**
   * Halts recording to the specified sample
   * 
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static native boolean FSOUND_Record_Stop();  
  // ----------------------------------------------------------
  
  // Reverb functions
  // ==========================================================
  /**
   * Sets hardware reverb parameters for advanced tuning. 
   * The best way to modify these is to set everything to use pre-defined presets given in the header, and then start modifying values
   * <p>
   * <b>Remarks</b>
   * You must be using FSOUND_OUTPUT_DSOUND as the output mode for this to work. 
   * In dsound, the reverb will only work if you have an EAX compatible soundcard such as the SBLive, and your sample/stream was created with the FSOUND_HW3D flag.
   * For GameCube, use FSOUND_AUXFX_xxx api
   * </p>
   * @param reverb reference to a FSoundReverbProperties.
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static boolean FSOUND_Reverb_SetProperties(FSoundReverbProperties reverb) {
    return nFSOUND_Reverb_SetProperties(reverb.reverbHandle);
  }
  private static native boolean nFSOUND_Reverb_SetProperties(long reverbHandle);    
  
  /**
   * Returns the current hardware reverb environment. 
   * The best way to modify these is to set everything to use pre-defined presets given in the header, and then start modifying values
   * <p>
   * <b>Remarks</b>
   * These values are only relevant if you are in DSOUND mode with an EAX3 compatible soundcard, or XBOX and PS2
   * </p>
   * @param reverb reference to a FSoundReverbProperties.
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static boolean FSOUND_Reverb_GetProperties(FSoundReverbProperties reverb) {
    return nFSOUND_Reverb_GetProperties(reverb.reverbHandle);
  }
  private static native boolean nFSOUND_Reverb_GetProperties(long reverbHandle);  
  
  /**
   * Sets the channel specific reverb properties for hardware, including wet/dry mix (room size), and things like obstruction and occlusion properties
   * <p>
   * <b>Remarks</b>
   * FSOUND_ALL is supported here. Passing this will set ALL channels to specified reverb properties.
   * If FSOUND_ALL is used the last channel success flag will be returned. This return value not useful in most circumstances.
   * -----------------
   * Under Win32, you must be using FSOUND_OUTPUT_DSOUND as the output mode for this to work. 
   * In DSound, the reverb will only work if you have an EAX compatible soundcard such as the SBLive, and your sample/stream was created with the FSOUND_HW3D flag.
   * -----------------
   * On PlayStation2, the 'Room' parameter is the only parameter supported. The hardware only allows 'on' or 'off', so the reverb will be off when 'Room' is -10000 and on for every other value.
   * -----------------
   * On XBox, it is possible to apply reverb to 2d voices using this function. By default reverb is turned off for 2d voices.
   * If this 2d voice was being positioned in a 5.1 array with the xbox only function FSOUND_SetLevels, then calling this function will disable that capability in favour of enabling reverb for the 2d voice.
   * It is a limitation of the xbox hardware that only one of the other of these features can be executed at one time.
   * </p>
   * @param channel The channel to have its reverb properties changed. FSOUND_ALL can also be used (see remarks)
   * @param reverb reference to a FSoundReverbChannelProperties.
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static boolean FSOUND_Reverb_SetChannelProperties(int channel, FSoundReverbChannelProperties reverb) {
    return nFSOUND_Reverb_SetChannelProperties(channel, reverb.reverbHandle);
  }
  private static native boolean nFSOUND_Reverb_SetChannelProperties(int channel, long reverbHandle);
  
  /**
   * This function gets the current reverb properties for this channel
   * @param channel The channel to have its reverb mix returned
   * @param reverb reference to a FSoundReverbChannelProperties.
   * @return On success, TRUE is returned. On failure, FALSE is returned
   */
  public static boolean FSOUND_Reverb_GetChannelProperties(int channel, FSoundReverbChannelProperties reverb) {
    return nFSOUND_Reverb_GetChannelProperties(channel, reverb.reverbHandle);
  }
  private static native boolean nFSOUND_Reverb_GetChannelProperties(int channel, long reverbHandle);  
  // ----------------------------------------------------------
  
  // Callbacks
  // ==========================================================
  /**
   * This is the callback rutine called by the native implementation whenever a
   * register callback is notified.
   * 
   * @param handle Handle to native object being monitored
   * @param param parameter passed to callback
   */
  private static ByteBuffer dsp_callback(long dsp_id, ByteBuffer originalbuffer, ByteBuffer newbuffer, int length) {
    // we got a callback - notify everybody
    ArrayList handlers = FMOD.getCallbacks(FMOD.FSOUND_DSPCALLBACK, dsp_id);
    FMOD.WrappedCallback wCallback = (FMOD.WrappedCallback) handlers.get((int)0);
    FSoundDSPCallback callback = (FSoundDSPCallback) wCallback.callback;
    return callback.FSOUND_DSPCALLBACK(originalbuffer, newbuffer, length);
  }
  
  /**
   * This is the callback rutine called by the native implementation whenever a
   * register callback is notified.
   * 
   * @param handle Handle to native object being monitored
   * @param param parameter passed to callback
   */
  private static void stream_callback(long streamHandle, ByteBuffer buff, int length) {
    // we got a callback - notify everybody
    ArrayList handlers = FMOD.getCallbacks(FMOD.FSOUND_STREAMCALLBACK, streamHandle);
    for(int i=0; i<handlers.size(); i++) {
      FMOD.WrappedCallback wCallback = (FMOD.WrappedCallback) handlers.get(i);   
      FSoundStreamCallback callback = (FSoundStreamCallback) wCallback.callback;  
      callback.FSOUND_STREAMCALLBACK((FSoundStream) wCallback.handled, buff, length);
    }
  }
  
  /**
   * This is the callback rutine called by the native implementation whenever a
   * register callback is notified.
   * 
   * @param handle Handle to native object being monitored
   * @param param parameter passed to callback
   */
  private static void end_callback(long streamHandle) {
    // we got a callback - notify everybody
  	ArrayList handlers = FMOD.getCallbacks(FMOD.FSOUND_ENDCALLBACK, streamHandle);
  	for(int i=0; i<handlers.size(); i++) {
  		FMOD.WrappedCallback wCallback = (FMOD.WrappedCallback) handlers.get(i);   
  		FSoundStreamCallback callback = (FSoundStreamCallback) wCallback.callback;  
  		callback.FSOUND_STREAMCALLBACK((FSoundStream) wCallback.handled, null, 0);
  	}
  }
  
  /**
   * This is the callback rutine called by the native implementation whenever a
   * register callback is notified.
   * 
   * @param handle Handle to native object being monitored
   * @param param parameter passed to callback
   */
  private static void sync_callback(long streamHandle, ByteBuffer buff, int lenght) {
    // we got a callback - notify everybody
    ArrayList handlers = FMOD.getCallbacks(FMOD.FSOUND_SYNCCALLBACK, streamHandle);
    for(int i=0; i<handlers.size(); i++) {
      FMOD.WrappedCallback wCallback = (FMOD.WrappedCallback) handlers.get(i);   
      FSoundStreamCallback callback = (FSoundStreamCallback) wCallback.callback;  
      callback.FSOUND_STREAMCALLBACK((FSoundStream) wCallback.handled, buff, lenght);
    }
  }
  
  /**
   * This is the callback rutine called by the native implementation whenever a
   * register callback is notified.
   * 
   * @param handle Handle to native object being monitored
   * @param param parameter passed to callback
   */
  private static int open_callback(String name) {
    // we got a callback - notify THE handler
    ArrayList handlers = FMOD.getCallbacks(FMOD.FSOUND_OPENCALLBACK, -1);
    FMOD.WrappedCallback wCallback = (FMOD.WrappedCallback) handlers.get(0);   
    FSoundOpenCallback callback = (FSoundOpenCallback) wCallback.callback;  
    return callback.FSOUND_OPENCALLBACK(name);
  }  
  
  /**
   * This is the callback rutine called by the native implementation whenever a
   * register callback is notified.
   * 
   * @param handle Handle to native object being monitored
   * @param param parameter passed to callback
   */
  private static void close_callback(int handle) {
    // we got a callback - notify THE handler
    ArrayList handlers = FMOD.getCallbacks(FMOD.FSOUND_CLOSECALLBACK, -1);
    FMOD.WrappedCallback wCallback = (FMOD.WrappedCallback) handlers.get(0);   
    FSoundCloseCallback callback = (FSoundCloseCallback) wCallback.callback;  
    callback.FSOUND_CLOSECALLBACK(handle);
  }  
  
  /**
   * This is the callback rutine called by the native implementation whenever a
   * register callback is notified.
   * 
   * @param handle Handle to native object being monitored
   * @param param parameter passed to callback
   */
  private static int read_callback(ByteBuffer buffer, int size, int handle) {
    // we got a callback - notify THE handler
    ArrayList handlers = FMOD.getCallbacks(FMOD.FSOUND_READCALLBACK, -1);
    FMOD.WrappedCallback wCallback = (FMOD.WrappedCallback) handlers.get(0);   
    FSoundReadCallback callback = (FSoundReadCallback) wCallback.callback;  
    return callback.FSOUND_READCALLBACK(buffer, size, handle);
  }  
  
  /**
   * This is the callback rutine called by the native implementation whenever a
   * register callback is notified.
   * 
   * @param handle Handle to native object being monitored
   * @param param parameter passed to callback
   */
  private static int seek_callback(int handle, int pos, int mode) {
    // we got a callback - notify THE handler
    ArrayList handlers = FMOD.getCallbacks(FMOD.FSOUND_SEEKCALLBACK, -1);
    FMOD.WrappedCallback wCallback = (FMOD.WrappedCallback) handlers.get(0);   
    FSoundSeekCallback callback = (FSoundSeekCallback) wCallback.callback;  
    return callback.FSOUND_SEEKCALLBACK(handle, pos, mode);
  } 
  
  /**
   * This is the callback rutine called by the native implementation whenever a
   * register callback is notified.
   * 
   * @param handle Handle to native object being monitored
   * @param param parameter passed to callback
   */
  private static int tell_callback(int handle) {
    // we got a callback - notify THE handler
    ArrayList handlers = FMOD.getCallbacks(FMOD.FSOUND_TELLCALLBACK, -1);
    FMOD.WrappedCallback wCallback = (FMOD.WrappedCallback) handlers.get(0);   
    FSoundTellCallback callback = (FSoundTellCallback) wCallback.callback;  
    return callback.FSOUND_TELLCALLBACK(handle);
  }  
  
  /**
   * This is the callback rutine called by the native implementation whenever a
   * register callback is notified.
   * 
   * @param handle Handle to native object being monitored
   * @param param parameter passed to callback
   */
  private static void meta_callback(long streamHandle, ByteBuffer name, ByteBuffer value) {
    // we got a callback - notify everybody
    ArrayList handlers = FMOD.getCallbacks(FMOD.FSOUND_METADATACALLBACK, streamHandle);
    for(int i=0; i<handlers.size(); i++) {
      FMOD.WrappedCallback wCallback = (FMOD.WrappedCallback) handlers.get(i);   
      FSoundMetaDataCallback callback = (FSoundMetaDataCallback) wCallback.callback;  
      callback.FSOUND_METADATACALLBACK(name, value);
    }
  }  
  // ----------------------------------------------------------  
}