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

#ifdef _WIN32
#define WIN32_LEAN_AND_MEAN
#include <windows.h>
#endif

#include <stdio.h>
#include "extfmod3.h"

/** Instance of fmod  */
FMOD_INSTANCE * fmod_instance = NULL;

// jnienvs
JNIEnv *mixer_jnienv;
JNIEnv *stream_jnienv;

// FMusic cached fields
jmethodID music_instcallback;
jmethodID music_ordercallback;
jmethodID music_rowcallback;
jmethodID music_zxxcallback;
jclass fmusic;

// FSound cached fields
jmethodID sound_dspcallback;
jmethodID sound_stream_endcallback;
jmethodID sound_stream_synccallback;
jmethodID sound_stream_callback;
jmethodID sound_metadata_callback;
jclass fsound;

// size of dsp buffer (in bytes)
int fsound_dsp_buffer_size;

#ifdef _WIN32
/**
 * DLL entry point for Windows. Called when Java loads the .dll
 */
BOOL WINAPI DllMain( HINSTANCE hinstDLL, DWORD fdwReason, LPVOID lpvReserved) {
	return true;
}
#endif

/**
 * Creates and loads the FMOD instance
 *
 * @param path path to try to load dll
 */
void fmod_create(JNIEnv *env, char* path) {
  // try to create an instance using the supplied path
  fmod_instance = FMOD_CreateInstance(path);
  
  // if we got one, we need to locate and cache jni stuff used for callbacks
  if (fmod_instance != NULL) {
    
    // fmusic specific callbacks
    fmusic = (*env)->FindClass(env, "org/lwjgl/fmod3/FMusic");
    music_instcallback = (*env)->GetStaticMethodID(env, fmusic, "music_instcallback", "(JI)V");
    music_ordercallback = (*env)->GetStaticMethodID(env, fmusic, "music_ordercallback", "(JI)V");
    music_rowcallback = (*env)->GetStaticMethodID(env, fmusic, "music_rowcallback", "(JI)V");
    music_zxxcallback = (*env)->GetStaticMethodID(env, fmusic, "music_zxxcallback", "(JI)V");
		
    // fsound specefic callbacks
		fsound = (*env)->FindClass(env, "org/lwjgl/fmod3/FSound");
		sound_dspcallback = (*env)->GetStaticMethodID(env, fsound, "dsp_callback", "(JLjava/nio/ByteBuffer;Ljava/nio/ByteBuffer;I)Ljava/nio/ByteBuffer;");
		sound_stream_endcallback = (*env)->GetStaticMethodID(env, fsound, "end_callback", "(J)V");
		sound_stream_synccallback = (*env)->GetStaticMethodID(env, fsound, "sync_callback", "(JLjava/nio/ByteBuffer;I)V");
		sound_stream_callback = (*env)->GetStaticMethodID(env, fsound, "stream_callback", "(JLjava/nio/ByteBuffer;I)V");
		sound_metadata_callback = (*env)->GetStaticMethodID(env, fsound, "meta_callback", "(JLjava/nio/ByteBuffer;Ljava/nio/ByteBuffer;)V");
		
		// we need the size of the mix buffer, so we start out by caching it and
		// update it accordingly on changes
		switch(fmod_instance->FSOUND_GetMixer()) {
			case FSOUND_MIXER_AUTODETECT:
			case FSOUND_MIXER_BLENDMODE:
			case FSOUND_MIXER_QUALITY_AUTODETECT:
			case FSOUND_MIXER_QUALITY_FPU:
			case FSOUND_MIXER_MONO:
			case FSOUND_MIXER_QUALITY_MONO:
			case FSOUND_MIXER_MAX:
				fsound_dsp_buffer_size = 8;
				break;
			default:
				fsound_dsp_buffer_size = 4;
				break;
		}
  }
}

/**
 * Destroys the fmod instance
 */
void fmod_destroy() {
  if (fmod_instance != NULL) {
    FMOD_FreeInstance(fmod_instance);
  }
}
