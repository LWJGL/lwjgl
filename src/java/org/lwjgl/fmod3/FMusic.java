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
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.fmod3.callbacks.FMusicCallback;

/**
 * <br>
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class FMusic {
  
  /** No song being played */
  public static final int FMUSIC_TYPE_NONE = 0;
  
  /** Protracker / Fasttracker */
  public static final int FMUSIC_TYPE_MOD = 1; 
   
  /** ScreamTracker 3 */
  public static final int FMUSIC_TYPE_S3M = 2; 
   
  /** FastTracker 2 */
  public static final int FMUSIC_TYPE_XM = 3; 
   
  /** Impulse Tracker */
  public static final int FMUSIC_TYPE_IT = 4;
   
  /** MIDI file */
  public static final int FMUSIC_TYPE_MIDI = 5;
   
  /** FMOD Sample Bank file */
  public static final int FMUSIC_TYPE_FSB = 6;  
  
  /**
   * To load a module or bank with a given filename. FMUSIC Supports loading of 
   * - .MOD (protracker/fasttracker modules)
   * - .S3M (screamtracker 3 modules)
   * - .XM (fasttracker 2 modules)
   * - .IT (impulse tracker modules)
   * - .MID (MIDI files)
   * - .RMI (MIDI files)
   * - .SGT (DirectMusic segment files)
   * - .FSB (FMOD Sample Bank files)
   * 
   * @param name Filename of module to load
   * @return On success, a FMusicModule instance is returned. On failure, Null is returned
   */
  public static FMusicModule FMUSIC_LoadSong(String name) {
  	long result = nFMUSIC_LoadSong(name);
    if(result != FMUSIC_TYPE_NONE) {
     return new FMusicModule(result); 
    }
    return null;
  }
  private static native long nFMUSIC_LoadSong(String name);
  
  /**
   * To load a module or bank with a given filename. FMUSIC Supports loading of 
   * - .MOD (protracker/fasttracker modules)
   * - .S3M (screamtracker 3 modules)
   * - .XM (fasttracker 2 modules)
   * - .IT (impulse tracker modules)
   * - .MID (MIDI files)
   * - .RMI (MIDI files)
   * - .SGT (DirectMusic segment files)
   * - .FSB (FMOD Sample Bank files)
   * <p>
   * <i>
   * Loading a song from a memory handle is dangerous in one respect, if the data is corrupted or truncated, then FMUSIC could crash internally trying to load it.
   * On PlayStation 2 the data and length pointer must be 16 byte aligned for DMA purposes.
   * The samplelist and samplelistnum parameters are useful for limiting the amount of data fmod loads. This feature is for the FSB format only. It is especially useful if you have a bank of sounds and want to randomize the loading a bit by telling which sounds to load with random values, and consequently which not to load.
   * On PlayStation 2, samplelistnum has a limit of 1536 entries.
   * </i>
   * </p>
   * 
   * @param data containing song to load. On PlayStation 2 data must be 16 byte aligned if loading from memory
   * @param offset Optional. 0 by default. If > 0, this value is used to specify an offset in a file, so fmod will seek before opening
   * @param length Optional. 0 by default. If > 0, this value is used to specify the length of a memory block when using FSOUND_LOADMEMORY, or it is the length of a file or file segment if the offset parameter is used. On PlayStation 2 this must be 16 byte aligned for memory loading
   * @param mode Mode for opening song. With module files, only FSOUND_LOADMEMORY, FSOUND_NONBLOCKING, FSOUND_LOOP_NORMAL, or FSOUND_LOOP_OFF are supported. For FSB files, FSOUND_2D, FSOUND_HW3D, FSOUND_FORCEMONO also work
   * @param sampleList Optional. Buffer of sample indicies to load. Leave as Null if you want all samples to be loaded (default behaviour). See Remarks for more on this
   * @return On success, a FMusicModule instance is returned. On failure, Null is returned
   */
  public static FMusicModule FMUSIC_LoadSongEx(ByteBuffer data, int offset, int length, int mode, IntBuffer sampleList) {
    long result = nFMUSIC_LoadSongEx(data, data.position(), offset, length, mode, (sampleList != null) ? sampleList : null, (sampleList != null) ? sampleList.position() : 0, (sampleList != null) ? sampleList.remaining() : 0);
    if(result != FMUSIC_TYPE_NONE) {
     return new FMusicModule(result); 
    }
    return null;
  }
  
  /**
   * @see #FMUSIC_LoadSongEx(ByteBuffer, int, int, int, IntBuffer)
   * @param name of song
   * @param offset Optional. 0 by default. If > 0, this value is used to specify an offset in a file, so fmod will seek before opening
   * @param length Optional. 0 by default. If > 0, this value is used to specify the length of a memory block when using FSOUND_LOADMEMORY, or it is the length of a file or file segment if the offset parameter is used. On PlayStation 2 this must be 16 byte aligned for memory loading
   * @param mode Mode for opening song. With module files, only FSOUND_LOADMEMORY, FSOUND_NONBLOCKING, FSOUND_LOOP_NORMAL, or FSOUND_LOOP_OFF are supported. For FSB files, FSOUND_2D, FSOUND_HW3D, FSOUND_FORCEMONO also work
   * @param sampleList Optional. Buffer of sample indicies to load. Leave as Null if you want all samples to be loaded (default behaviour). See Remarks for more on this
   * @return On success, a FMusicModule instance is returned. On failure, Null is returned
   */
  public static FMusicModule FMUSIC_LoadSongEx(String name, int offset, int length, int mode, IntBuffer sampleList) {
    long result = nFMUSIC_LoadSongEx(name, offset, length, mode, (sampleList != null) ? sampleList : null, (sampleList != null) ? sampleList.position() : 0, (sampleList != null) ? sampleList.remaining() : 0);

    if(result != FMUSIC_TYPE_NONE) {
     return new FMusicModule(result); 
    }
    return null;
  }  
  private static native long nFMUSIC_LoadSongEx(ByteBuffer data, int dataOffset, int offset, int length, int mode, IntBuffer sampleList, int bufferOffset, int remaining);
  private static native long nFMUSIC_LoadSongEx(String name, int offset, int length, int mode, IntBuffer sampleList, int bufferOffset, int remaining);
  
  /**
   * If a mod is opened with FSOUND_NONBLOCKING, this function returns the state of the opening mod.
   * @param module Module to get the open state from
   * @return 0 = mod is opened and ready. -1 = mod handle passed in is invalid. -2 = mod is still opening -3 = mod failed to open. (file not found, out of memory or other error).
   */
  public static int FMUSIC_GetOpenState(FMusicModule module) {
   return nFMUSIC_GetOpenState(module.moduleHandle);
  }
  private static native int nFMUSIC_GetOpenState(long module);
  
  /**
   * Frees memory allocated for a song and removes it from the FMUSIC system
   * @param module Module to be freed
   * @return On success, true is returned. On failure, false is returned
   */
  public static boolean FMUSIC_FreeSong(FMusicModule module) {
    // when freeing a song, we automatically deregister any callbacks
    FMOD.registerCallback(FMOD.FMUSIC_INSTCALLBACK, module.moduleHandle, null, null);
    FMOD.registerCallback(FMOD.FMUSIC_ORDERCALLBACK, module.moduleHandle, null, null);
    FMOD.registerCallback(FMOD.FMUSIC_ROWCALLBACK, module.moduleHandle, null, null);
    FMOD.registerCallback(FMOD.FMUSIC_ZXXCALLBACK, module.moduleHandle, null, null);
    return nFMUSIC_FreeSong(module.moduleHandle); 
  }
  private static native boolean nFMUSIC_FreeSong(long module);
  
  /**
   * Starts a song playing
   * @param module Module to be played
   * @return true if module succeeded playing. false if module failed playing
   */
  public static boolean FMUSIC_PlaySong(FMusicModule module) {
    return nFMUSIC_PlaySong(module.moduleHandle); 
  }
  private static native boolean nFMUSIC_PlaySong(long module);
  
  /**
   * Stops a song from playing.
   * @param module Module to be stopped
   * @return On success, true is returned. On failure, false is returned
   */
  public static boolean FMUSIC_StopSong(FMusicModule module) {
    return nFMUSIC_StopSong(module.moduleHandle); 
  }
  private static native boolean nFMUSIC_StopSong(long module);
  
  /**
   * Stops all songs from playing. This is useful if you have multiple songs playing at once and
   * want a quick way to stop them
   */
  public static native void FMUSIC_StopAllSongs();

  /**
   * Sets a user callback for any Zxx commands encountered in an S3M, XM or IT file.
   * <p>
   * <b>Remarks</b>
   * The value passed into the param parameter of the callback is the xx value specified in the Zxx
   * command by the musician
   * ------------  
   * It is important to note that this callback will be called from directly WITHIN the
   * mixer / music update thread, therefore it is imperative that whatever you do from this
   * callback be extremely efficient. If the routine takes too long then breakups in the sound
   * will occur, or it will basically stop mixing until you return from the function.
   * This sort of function is usually best for just setting a flag, or do some simple variable
   * manipulation, and then exiting, letting your main thread do what it needs to based on these
   * flags or variables.
   * ------------
   * This callback is LATENCY adjusted, so that the callback happens when you HEAR the sound, not when it is mixed, for accurate synchronization.
   * Use FSOUND_INIT_DONTLATENCYADJUST if you want it to be called back at mix time, which is useful if you want to control the music interactively.
   * ------------
   * Note : This function is not supported with the MIDI format.
   * @param module Module to set the callback for
   * @param callback The callback function you supply to get called upon execution of a Zxx command
   * @return On success, true is returned. On failure, false is returned
   */
  public static boolean FMUSIC_SetZxxCallback(FMusicModule module, FMusicCallback callback) {
    FMOD.registerCallback(FMOD.FMUSIC_ZXXCALLBACK, module.moduleHandle, module, callback);
    return nFMUSIC_SetZxxCallback(module.moduleHandle);
  }
  private static native boolean nFMUSIC_SetZxxCallback(long module);
  
  /**
   * Sets a user callback to occur on every row divisible by the rowstep parameter, played from a MOD, S3M, XM or IT file.
   * <p>
   * <b>Remarks</b>
   * It is important to note that this callback will be called from directly WITHIN the
   * mixer / music update thread, therefore it is imperative that whatever you do from this
   * callback be extremely efficient. If the routine takes too long then breakups in the sound
   * will occur, or it will basically stop mixing until you return from the function.
   * This sort of function is usually best for just setting a flag, or do some simple variable
   * manipulation, and then exiting, letting your main thread do what it needs to based on these
   * flags or variables.
   * ------------
   * This callback is LATENCY adjusted, so that the callback happens when you HEAR the sound, not when it is mixed, for accurate synchronization.
   * Use FSOUND_INIT_DONTLATENCYADJUST if you want it to be called back at mix time, which is useful if you want to control the music interactively.
   * ------------
   * Note : This function is not supported with the MIDI format.
   * @param module Module to set the callback for
   * @param callback The callback function you supply to get called
   * @param rowstep Call the callback every multiple of this number of rows
   * @return On success, true is returned. On failure, false is returned
   */
  public static boolean FMUSIC_SetRowCallback(FMusicModule module, FMusicCallback callback, int rowstep) {
    FMOD.registerCallback(FMOD.FMUSIC_ROWCALLBACK, module.moduleHandle, module, callback);
    return nFMUSIC_SetRowCallback(module.moduleHandle, rowstep);
  }
  private static native boolean nFMUSIC_SetRowCallback(long module, int rowstep);
  
  /**
   * Sets a user callback to occur on every order divisible by the orderstep parameter, played from a MOD, S3M, XM or IT file
   * <p>
   * <b>Remarks</b>
   * It is important to note that this callback will be called from directly WITHIN the
   * mixer / music update thread, therefore it is imperative that whatever you do from this
   * callback be extremely efficient. If the routine takes too long then breakups in the sound
   * will occur, or it will basically stop mixing until you return from the function.
   * This sort of function is usually best for just setting a flag, or do some simple variable
   * manipulation, and then exiting, letting your main thread do what it needs to based on these
   * flags or variables.
   * ------------
   * This callback is LATENCY adjusted, so that the callback happens when you HEAR the sound, not when it is mixed, for accurate synchronization.
   * Use FSOUND_INIT_DONTLATENCYADJUST if you want it to be called back at mix time, which is useful if you want to control the music interactively.
   * ------------
   * Note : This function is not supported with the MIDI format.
   * @param module Module to set the callback for
   * @param callback The callback function you supply to get called
   * @param orderstep Call the callback every multiple of this number of orders
   * @return On success, true is returned. On failure, false is returned
   */
  public static boolean FMUSIC_SetOrderCallback(FMusicModule module, FMusicCallback callback, int orderstep) {
    FMOD.registerCallback(FMOD.FMUSIC_ORDERCALLBACK, module.moduleHandle, module, callback);
    return nFMUSIC_SetOrderCallback(module.moduleHandle, orderstep); 
  }
  private static native boolean nFMUSIC_SetOrderCallback(long module, int orderstep);
  
  /**
   * Sets a user callback to occur every time a instrument is played, triggered from a MOD, S3M, XM or IT file.
   * <p>
   * <b>Remarks</b>
   * It is important to note that this callback will be called from directly WITHIN the
   * mixer / music update thread, therefore it is imperative that whatever you do from this
   * callback be extremely efficient. If the routine takes too long then breakups in the sound
   * will occur, or it will basically stop mixing until you return from the function.
   * This sort of function is usually best for just setting a flag, or do some simple variable
   * manipulation, and then exiting, letting your main thread do what it needs to based on these
   * flags or variables.
   * ------------
   * This callback is LATENCY adjusted, so that the callback happens when you HEAR the sound, not when it is mixed, for accurate synchronization.
   * Use FSOUND_INIT_DONTLATENCYADJUST if you want it to be called back at mix time, which is useful if you want to control the music interactively.
   * ------------
   * Note : This function is not supported with the MIDI format.
   * @param module Module set the callback for
   * @param callback The callback function you supply to get called
   * @param instrument Call the callback when this instrument number is triggered
   * @return On success, true is returned. On failure, false is returned
   */
  public static boolean FMUSIC_SetInstCallback(FMusicModule module, FMusicCallback callback, int instrument) {
    FMOD.registerCallback(FMOD.FMUSIC_INSTCALLBACK, module.moduleHandle, module, callback);
    return nFMUSIC_SetInstCallback(module.moduleHandle, instrument);
  }
  private static native boolean nFMUSIC_SetInstCallback(long module, int instrument);
  
  /**
   * Replaces a mod's sample with a sample definition specified.
   * <p>
   * <b>Remarks</b>
   * Because of the instrument nature of some formats like XM, this function lists all the samples in order of instruments and their subsamples.
   * ie if instrument 1 has 2 samples and instrument 2 contains 3 samples, then sampno in this case would be 0 and 1 for instrument 1's samples, and 2,3 & 4 for instrument 2's samples. 
   * ------------
   * FMOD does not free the existing mod sample that you may be overwriting. If you do overwrite an existing handle, it may be lost, and you may incur a memory leak. It is a good idea to free the existing sample first before overwriting it.
   * ------------
   * Important: For PlayStation 2, this function has to do a blocking query to the IOP, and can take significantly more time than a standard non blocking fmod function. This means it is best to cache the pointers for samples while loading, and not call this function in realtime.
   * ------------
   * This function is not supported with the MIDI format.
   * </p>
   * @param module Module to set the sample for.
   * @param sampno index to sample inside module
   * @param sptr sample definition to replace mod sample
   * @return On success, true is returned. On failure, false is returned
   */
  public static boolean FMUSIC_SetSample(FMusicModule module, int sampno, FSoundSample sptr) {
    return nFMUSIC_SetSample(module.moduleHandle, sampno, sptr.sampleHandle); 
  }
  private static native boolean nFMUSIC_SetSample(long module, int sampno, long sptr);
  
  /**
   * Sets a user defined value to store with the music file to be retrieved later.
   * @param module Module to set user data for
   * @param userdata Value to store with music object
   * @return On success, true is returned. On failure, false is returned
   */
  public static boolean FMUSIC_SetUserData(FMusicModule module, ByteBuffer userdata) {
    return nFMUSIC_SetUserData(module.moduleHandle, userdata, userdata.position());
  }
  private static native boolean nFMUSIC_SetUserData(long module, ByteBuffer userdata, int offset);
  
  /**
   * This function helps with channel usage. If you are desperate for channels, and you are prepared to 
   * let the music routines drop a few channels, then calling this function can help.
   * It basically doesnt try to play any new sounds if a certain channel limit is being played (including sound effects),
   * and the new sound is below a certain specified volume.
   * ie.
   * You set it to maxchannels = 16, and minvolume = 0x10. 
   * In this case, the mod will play normally as long as the total number of channels being played inclusing sound effefcts is below 16
   * (see FSOUND_GetChannelsPlaying).
   * If the number of channels playing exceeds 16 (through a change in the music, or extra sound effects
   * are spawned, then sounds with a musician specified volume of less than 0x10 will be ignored.
   * The volume is based on volume column/default volume/volume set commands in the mod. master volume,
   * envelope volumes etc are not taken into account (this gives more control over how it will work from the
   * tracker).
   * <p>
   * <b>Remarks</b>
   * maxchannels will default to the number of channels allocated by FSOUND, so this will never happen
   * by default.
   * minvolume will default to 0, so it will always succeed by default.
   * To see how many channels are currently being MIXED, use FSOUND_GetChannelsPlaying.
   * As a musician mentioned to me once, most of his default volumes are set fairly high, and any low end 
   * volumes are usually echoes etc, and can afford to be dropped.
   * ------------
   * Note : This function is not supported with the MIDI format.
   * </p>
   * @param module Module to set channel/volume optimization settings
   * @param maxchannels Channel count to be mixed before fmusic starts to drop channels from the song
   * @param minvolume If maxchannels is exceeded, then music channels with volumes below this value will not be played. Range is 0-64. This is the value the tracker displays. All trackers use 0-64
   * @return On success, true is returned. On failure, false is returned
   */
  public static boolean FMUSIC_OptimizeChannels(FMusicModule module, int maxchannels, int minvolume) {
    return nFMUSIC_OptimizeChannels(module.moduleHandle, maxchannels, minvolume); 
  }
  private static native boolean nFMUSIC_OptimizeChannels(long module, int maxchannels, int minvolume);
  
  /**
   * Turns on reverb for MIDI/RMI files.
   * <p>
   * <b>Remarks</b>
   * Reverb may be enabled through software emulation in the future for MOD based formats.
   * </p>
   * @param reverb Set to true to turn MIDI reverb on, false to turn MIDI reverb off
   * @return On success, true is returned. On failure, false is returned
   */
  public static native boolean FMUSIC_SetReverb(boolean reverb);
  
  /**
   * Sets looping mode for midi and mod files
   * <p>
   * <b>Remarks</b>
   * Defaults to TRUE. To disable looping you must call this function using FALSE as the parameter.
   * For midi files this only takes effect before FMUSIC_PlaySong is called. For mod files this 
   * can be called at any time including during playback.
   * </p> 
   * @param module Module to set looping for
   * @param looping Set to true to make it loop forever, or false to only have it play once
   * @return On success, true is returned. On failure, false is returned
   */
  public static boolean FMUSIC_SetLooping(FMusicModule module, boolean looping) {
    return nFMUSIC_SetLooping(module.moduleHandle, looping);
  }
  private static native boolean nFMUSIC_SetLooping(long module, boolean looping);
  
  /**
   * Sets a songs order position / current playing position.
   * <p>
   * <b>Remarks</b>
   * Note : This function is not supported with the MIDI format.
   * </p> 
   * @param module Module to have its order changed
   * @param order Order number to jump to
   * @return On success, true is returned. On failure, false is returned
   */
  public static boolean FMUSIC_SetOrder(FMusicModule module, int order) {
    return nFMUSIC_SetOrder(module.moduleHandle, order); 
  }
  private static native boolean nFMUSIC_SetOrder(long module, int order);
  
  /**
   * Pauses a song
   * @param module Module to be paused/unpaused
   * @param pause true - song should be PAUSED, false - song should be UNPAUSED
   * @return On success, true is returned. On failure, false is returned
   */
  public static boolean FMUSIC_SetPaused(FMusicModule module, boolean pause) {
    return nFMUSIC_SetPaused(module.moduleHandle, pause); 
  }
  private static native boolean nFMUSIC_SetPaused(long module, boolean pause);
  
  /**
   * Sets a songs master volume.
   * @param module Module to have its master volume set
   * @param volume value from 0-256. 0 = silence, 256 = full volume
   * @return On success, true is returned. On failure, false is returned
   */
  public static boolean FMUSIC_SetMasterVolume(FMusicModule module, int volume) {
    return nFMUSIC_SetMasterVolume(module.moduleHandle, volume); 
  }
  private static native boolean nFMUSIC_SetMasterVolume(long module, int volume);
  
  /**
   * Sets a songs master speed scale, so that the song can be sped up or slowed down.
   * @param module Module to have its speed scale set
   * @param speed Speed scale for song. 1.0 is default. Minimum is 0 (stopped), maximum is 10.0
   * @return On success, true is returned. On failure, false is returned
   */
  public static boolean FMUSIC_SetMasterSpeed(FMusicModule module, float speed) {
    return nFMUSIC_SetMasterSpeed(module.moduleHandle, speed); 
  }
  private static native boolean nFMUSIC_SetMasterSpeed(long module, float speed);
  
  /**
   * Sets the master pan seperation for a module
   * @param module Module to set pan seperation for
   * @param pansep The pan scale. 1.0 means full pan seperation, 0 means mono
   * @return On success, true is returned. On failure, false is returned
   */
  public static boolean FMUSIC_SetPanSeperation(FMusicModule module, float pansep) {
    return nFMUSIC_SetPanSeperation(module.moduleHandle, pansep); 
  }
  private static native boolean nFMUSIC_SetPanSeperation(long module, float pansep);
  
  /**
   * Returns the name of the song set by the composer. With MIDI format, the filename is returned
   * @param module Module to retrieve name from
   * @return On success, the name of the song is returned. On failure, Null is returned
   */
  public static String FMUSIC_GetName(FMusicModule module) {
    return nFMUSIC_GetName(module.moduleHandle); 
  }
  private static native String nFMUSIC_GetName(long module);
  
  /**
   * Returns the format type a song
   * @param module Module to retrieve type from
   * @return FMusicType constant, FMUSIC_TYPE_NONE on failure
   */
  public static int FMUSIC_GetType(FMusicModule module) {
    return nFMUSIC_GetType(module.moduleHandle); 
  }
  private static native int nFMUSIC_GetType(long module);
  
  /**
   * Returns the number of orders in this song
   * @param module Module to retrieve number of orders from
   * @return On success, the number of orders in this song is returned. On failure, 0 is returned
   */
  public static int FMUSIC_GetNumOrders(FMusicModule module) {
    return nFMUSIC_GetNumOrders(module.moduleHandle); 
  }
  private static native int nFMUSIC_GetNumOrders(long module);
  
  /**
   * Returns the number of patterns contained in this song.
   * @param module Module to retrieve number of patterns from
   * @return On success, the number of patterns contained in this song is returned. On failure, 0 is returned   
   */
  public static int FMUSIC_GetNumPatterns(FMusicModule module) {
    return nFMUSIC_GetNumPatterns(module.moduleHandle); 
  }
  private static native int nFMUSIC_GetNumPatterns(long module);
  
  /**
   * Returns the number of instruments contained in this song.
   * @param module Module to retrieve number of instruments from
   * @return On success, the number of instruments contained in this song is returned. On failure, 0 is returned.
   */
  public static int FMUSIC_GetNumInstruments(FMusicModule module) {
    return nFMUSIC_GetNumInstruments(module.moduleHandle); 
  }
  private static native int nFMUSIC_GetNumInstruments(long module);
  
  /**
   * Returns the number of samples contained in this song.
   * @param module Module to retrieve number of samples
   * @return Number of samples contained in this song. On failure, 0 is returned.
   */
  public static int FMUSIC_GetNumSamples(FMusicModule module) {
    return nFMUSIC_GetNumSamples(module.moduleHandle);
  }
  private static native int nFMUSIC_GetNumSamples(long module);
  
  /**
   * Returns the number of channels within this songs pattern data
   * @param module Module to retrieve number of channels from
   * @return Number of channels within this songs pattern data. On failure, 0 is returned.
   */
  public static int FMUSIC_GetNumChannels(FMusicModule module) {
    return nFMUSIC_GetNumChannels(module.moduleHandle);
  }
  private static native int nFMUSIC_GetNumChannels(long module);
  
  /**
   * Returns a reference to a sample inside a module. 
   * Once you have access to the module's sample, you can do a lot of things 
   * to it, including locking and modifying the data within; using the 
   * FSOUND_Sample_ functionality
   * <p>
   * <b>Remarks</b>
   * Because of the instrument nature of some formats like XM, this
   * function lists all the samples in order of instruments and their subsamples.
   * ie if instrument 1 has 2 samples and instrument 2 contains 3 samples, then 
   * sampno in this case would be 0 and 1 for instrument 1's samples, and 2,3 & 4
   * for instrument 2's samples.
   * </p> 
   * @param module Module to retrieve a sample handle from
   * @param sampno index to sample inside module
   * @return On success, a valid sample is returned. On failure, Null is returned.
   */
  public static FSoundSample FMUSIC_GetSample(FMusicModule module, int sampno) {
    long result = nFMUSIC_GetSample(module.moduleHandle, sampno);
    if(result != 0) {
    	return new FSoundSample(result);
    }
    return null;
  }  
  private static native long nFMUSIC_GetSample(long module, int sampno);
  
  /**
   * Returns the the length in rows of the pattern for the specified order number
   * @param module Module to get pattern lenght from
   * @param orderno pattern at specified order
   * @return On success, the songs pattern length at the specified order is returned. On failure, 0 is returned
   */
  public static int FMUSIC_GetPatternLength(FMusicModule module, int orderno) {
    return nFMUSIC_GetPatternLength(module.moduleHandle, orderno);
  }
  private static native int nFMUSIC_GetPatternLength(long module, int orderno);
  
  /**
   * Returns whether the song has completed playing, or when the last order has finished playing.
   * This stays set even if the song loops.
   * @param module Module that you want check if finished or not
   * @return true if module has finished playing. false if module has not finished playing.
   */
  public static boolean FMUSIC_IsFinished(FMusicModule module) {
    return nFMUSIC_IsFinished(module.moduleHandle);
  }
  private static native boolean nFMUSIC_IsFinished(long module);
  
  /**
   * Returns whether the song is currently playing or not.
   * @param module Module to retrieve name from
   * @return true Song is playing. false Song is stopped.
   */
  public static boolean FMUSIC_IsPlaying(FMusicModule module) {
   return nFMUSIC_IsPlaying(module.moduleHandle); 
  }
  private static native boolean nFMUSIC_IsPlaying(long module);
  
  /**
   * Returns the song's current master volume
   * @param module Module to retrieve song master volume from
   * @return On success, the song's current master volume, from 0 (silence) to 256 (full volume) is returned. On failure, -1 is returned.
   */
  public static int FMUSIC_GetMasterVolume(FMusicModule module) {
    return nFMUSIC_GetMasterVolume(module.moduleHandle);
  }
  private static native int nFMUSIC_GetMasterVolume(long module);
  
  /**
   * Returns the song's current global volume
   * <p>
   * <b>Remarks</b>
   * GLOBAL volume is not the same as MASTER volume. GLOBAL volume is an internal
   * overall volume which can be altered by the song itself (ie there might be commands
   * to fade in a particular part of the song by scaling all the volumes in the song 
   * up slowly from nothing).
   * GLOBAL volume is different to MASTER volume in that the song can modify without
   * your permission, whereas MASTER volume is an overall scalar that you can control.
   * For general use, MASTER volume is more useful, but you may want to reset a song's
   * GLOBAL volume at certain times in the song. (for example the song might have faded 
   * out by using GLOBAL volume and you want to reset it)
   * </p>
   * @param module Module to retrieve song global volume from
   * @return Songs current global volume, from 0 (silence) to the maximum value determined by the music format. Global volume 
   * maximums are different in respect to each format, they range from 64 to 256. On failure, -1 is returned.
   */
  public static int FMUSIC_GetGlobalVolume(FMusicModule module) {
   return nFMUSIC_GetGlobalVolume(module.moduleHandle); 
  }
  private static native int nFMUSIC_GetGlobalVolume(long module);
  
  /**
   * Returns the song's current order number
   * @param module Module to retrieve current order number from
   * @return On success, the song's current order number is returned.On failure, -1 is returned
   */
  public static int FMUSIC_GetOrder(FMusicModule module) {
   return nFMUSIC_GetOrder(module.moduleHandle); 
  }
  private static native int nFMUSIC_GetOrder(long module);
  
  /**
   * Returns the song's current pattern number
   * @param module Module to retrieve current pattern number from
   * @return On success, The song's current pattern number is returned. On failure, -1 is returned
   */
  public static int FMUSIC_GetPattern(FMusicModule module) {
   return nFMUSIC_GetPattern(module.moduleHandle); 
  }
  private static native int nFMUSIC_GetPattern(long module);
  
  /**
   * Returns the song's current speed.
   * @param module Module to retrieve current song speed from
   * @return On success, The song's current speed is returned. On failure, -1 is returned
   */
  public static int FMUSIC_GetSpeed(FMusicModule module) {
   return nFMUSIC_GetSpeed(module.moduleHandle); 
  }
  private static native int nFMUSIC_GetSpeed(long module);
  
  /**
   * Returns the song's current BPM.
   * @param module Module to retrieve current song BPM from
   * @return On success, song's current BPM is returned. On failure, -1 is returned
   */
  public static int FMUSIC_GetBPM(FMusicModule module) {
    return nFMUSIC_GetBPM(module.moduleHandle);
  }
  private static native int nFMUSIC_GetBPM(long module);
  
  /**
   * Returns the song's current row number
   * <p>
   * <b>Remarks</b>
   * This value is latency adjusted by default, and returns the number you are hearing, not the 'mix-time' value.
   * Use FSOUND_INIT_DONTLATENCYADJUST if you want the value at mix time, which is useful if you want to control the music interactively, or from a DSP callback.
   * </p>
   * @param module Module to retrieve current row from
   * @return On success, the song's current row number is returned. On failure, -1 is returned
   */
  public static int FMUSIC_GetRow(FMusicModule module) {
   return nFMUSIC_GetRow(module.moduleHandle); 
  }
  private static native int nFMUSIC_GetRow(long module);
  
  /**
   * Returns whether song is currently paused or not
   * @param module Module to get paused flag from
   * @return On success, true is returned to say the song is currently paused. On failure, false is returned to say the song is NOT currently paused
   */
  public static boolean FMUSIC_GetPaused(FMusicModule module) {
  	return nFMUSIC_GetPaused(module.moduleHandle);
  }
  private static native boolean nFMUSIC_GetPaused(long module);
  
  /**
   * Returns the time in milliseconds since the song was started. This is useful for
   * synchronizing purposes becuase it will be exactly the same every time, and it is 
   * reliably retriggered upon starting the song. Trying to synchronize using other 
   * windows timers can lead to varying results, and inexact performance. This fixes that
   * problem by actually using the number of samples sent to the soundcard as a reference
   * <p>
   * <b>Remarks</b>
   * This value is latency adjusted by default, and returns the number you are hearing, not the 'mix-time' value.
   * Use FSOUND_INIT_DONTLATENCYADJUST if you want the value at mix time, which is useful if you want to control the music interactively, or from a DSP callback
   * </p>
   * @param module Module to the song to get time from
   * @return On success, the time played in milliseconds is returned. On failure, -1 is returned
   */
  public static int FMUSIC_GetTime(FMusicModule module) {
   return nFMUSIC_GetTime(module.moduleHandle);
  }
  private static native int nFMUSIC_GetTime(long module);
  
  /**
   * Returns the real FSOUND channel playing based on the mod's FMUSIC channel
   * <p>
   * <b>Remarks</b>
   * Note FMUSIC mod playback only allocates a real channel on a mod channel the first time an instrument is played.
   * NNA's will not register. This function only returns the primary real channel for the mod channel.
   * </p>
   * @param module Module to the song
   * @param modchannel channel index, to query the real channel from
   * @return On success, the channel index for the respective mod channel is returned. On failure, -1 is returned
   */
  public static int FMUSIC_GetRealChannel(FMusicModule module, int modchannel) {
   return nFMUSIC_GetRealChannel(module.moduleHandle, modchannel);
  }
  private static native int nFMUSIC_GetRealChannel(long module, int modchannel);
  
  /**
   * Retrieves the data set by FMUSIC_SetUserData
   * @param module Module to get the open state from
   * @return On success, userdata set by FMUSIC_SetUserData is returned. On failure, Null is returned.
   */
  public static ByteBuffer FMUSIC_GetUserData(FMusicModule module, int capacity) {
    ByteBuffer buffer = nFMUSIC_GetUserData(module.moduleHandle, capacity);
    buffer.order(ByteOrder.nativeOrder());
    return buffer;
  }
  private static native ByteBuffer nFMUSIC_GetUserData(long module, int capacity);  
  
  /**
   * This is the callback rutine called by the native implementation whenever a
   * register callback is notified.
   * 
   * @param handle Handle to native object being monitored
   * @param param parameter passed to callback
   */
  public static void music_instcallback(long modulehandle, int param) {
    // we got a callback - notify everybody
    ArrayList handlers = FMOD.getCallbacks(FMOD.FMUSIC_INSTCALLBACK, modulehandle);
    for(int i=0; i<handlers.size(); i++) {
    	FMOD.WrappedCallback wCallback = (FMOD.WrappedCallback) handlers.get(i);   
    	FMusicCallback callback = (FMusicCallback) wCallback.callback;  
    	callback.FMUSIC_CALLBACK((FMusicModule) wCallback.handled, param);
    }
  }
  
  /**
   * This is the callback rutine called by the native implementation whenever a
   * register callback is notified.
   * 
   * @param handle Handle to native object being monitored
   * @param param parameter passed to callback
   */
  public static void music_ordercallback(long modulehandle, int param) {
    // we got a callback - notify everybody
    ArrayList handlers = FMOD.getCallbacks(FMOD.FMUSIC_ORDERCALLBACK, modulehandle);
    for(int i=0; i<handlers.size(); i++) {
      FMOD.WrappedCallback wCallback = (FMOD.WrappedCallback) handlers.get(i);   
      FMusicCallback callback = (FMusicCallback) wCallback.callback;  
      callback.FMUSIC_CALLBACK((FMusicModule) wCallback.handled, param);
    }
  }
  
  /**
   * This is the callback rutine called by the native implementation whenever a
   * register callback is notified.
   * 
   * @param handle Handle to native object being monitored
   * @param param parameter passed to callback
   */
  public static void music_rowcallback(long modulehandle, int param) {
    // we got a callback - notify everybody
    ArrayList handlers = FMOD.getCallbacks(FMOD.FMUSIC_ROWCALLBACK, modulehandle);
    for(int i=0; i<handlers.size(); i++) {
      FMOD.WrappedCallback wCallback = (FMOD.WrappedCallback) handlers.get(i);   
      FMusicCallback callback = (FMusicCallback) wCallback.callback;  
      callback.FMUSIC_CALLBACK((FMusicModule) wCallback.handled, param);
    }
  }   
  
  /**
   * This is the callback rutine called by the native implementation whenever a
   * register callback is notified.
   * 
   * @param handle Handle to native object being monitored
   * @param param parameter passed to callback
   */
  public static void music_zxxcallback(long modulehandle, int param) {
    // we got a callback - notify everybody
    ArrayList handlers = FMOD.getCallbacks(FMOD.FMUSIC_ZXXCALLBACK, modulehandle);
    for(int i=0; i<handlers.size(); i++) {
      FMOD.WrappedCallback wCallback = (FMOD.WrappedCallback) handlers.get(i);   
      FMusicCallback callback = (FMusicCallback) wCallback.callback;  
      callback.FMUSIC_CALLBACK((FMusicModule) wCallback.handled, param);
    }
  }   
}
