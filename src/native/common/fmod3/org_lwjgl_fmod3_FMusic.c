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
 
#include "org_lwjgl_fmod3_FMusic.h"
#include "extfmod3.h"

// callback
void F_CALLBACKAPI fmusic_instcallback(FMUSIC_MODULE *mod, unsigned char param);
void F_CALLBACKAPI fmusic_ordercallback(FMUSIC_MODULE *mod, unsigned char param);
void F_CALLBACKAPI fmusic_rowcallback(FMUSIC_MODULE *mod, unsigned char param);
void F_CALLBACKAPI fmusic_zxxcallback(FMUSIC_MODULE *mod, unsigned char param);

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_LoadSong
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1LoadSong(JNIEnv *env, jclass clazz, jstring name) {
  jlong result;
  char* filename = GetStringNativeChars(env, name);
  result = (jlong) fmod_instance->FMUSIC_LoadSong(filename);
  free(filename);
  return result;
}

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_LoadSongEx
 * Signature: (Ljava/nio/ByteBuffer;IIILjava/nio/IntBuffer;I)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1LoadSongEx__Ljava_nio_ByteBuffer_2IIILjava_nio_IntBuffer_2II
  (JNIEnv *env, jclass clazz, jobject data, jint offset, jint length, jint mode, jobject sampleList, jint sampleListOffset, jint samplelistnum){
    int *sampleData = NULL;
    const char *songData = offset + (char *) (*env)->GetDirectBufferAddress(env, data);
    if(sampleList != NULL) {
      sampleData = sampleListOffset + (int *) (*env)->GetDirectBufferAddress(env, sampleList);
    }
    
    return (jlong) fmod_instance->FMUSIC_LoadSongEx(songData, 0, length, mode, sampleData, samplelistnum);
  }
  
/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_LoadSongEx
 * Signature: (Ljava/nio/ByteBuffer;IIILjava/nio/IntBuffer;I)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1LoadSongEx__Ljava_lang_String_2IIILjava_nio_IntBuffer_2II
  (JNIEnv *env, jclass clazz, jstring name, jint offset, jint length, jint mode, jobject sampleList, jint sampleListOffset, jint samplelistnum){
    jlong result;
	int *sampleData = NULL;
    char* filename = GetStringNativeChars(env, name);
    if(sampleList != NULL) {
      sampleData = sampleListOffset + (int *) (*env)->GetDirectBufferAddress(env, sampleList);
    }
    result = (jlong) fmod_instance->FMUSIC_LoadSongEx(filename, offset, length, mode, sampleData, samplelistnum);
	free(filename);
	return result;
  }
  

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetOpenState
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetOpenState
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetOpenState((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_FreeSong
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1FreeSong
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_FreeSong((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_PlaySong
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1PlaySong
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_PlaySong((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_StopSong
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1StopSong
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_StopSong((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    FMUSIC_StopAllSongs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FMusic_FMUSIC_1StopAllSongs
  (JNIEnv *env, jclass clazz){
    fmod_instance->FMUSIC_StopAllSongs();
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_SetZxxCallback
 * Signature: (JLorg/lwjgl/fmod_instance/FMusicCallback;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1SetZxxCallback(JNIEnv *env, jclass clazz, jlong module){
  return fmod_instance->FMUSIC_SetZxxCallback((FMUSIC_MODULE*)module, fmusic_zxxcallback);
}

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_SetRowCallback
 * Signature: (JLorg/lwjgl/fmod_instance/FMusicCallback;I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1SetRowCallback(JNIEnv *env, jclass clazz, jlong module, jint row) {
  return fmod_instance->FMUSIC_SetRowCallback((FMUSIC_MODULE*)module, fmusic_rowcallback, row);
}

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_SetOrderCallback
 * Signature: (JLorg/lwjgl/fmod_instance/FMusicCallback;I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1SetOrderCallback(JNIEnv *env, jclass clazz, jlong module, jint order) {
    return fmod_instance->FMUSIC_SetOrderCallback((FMUSIC_MODULE*)module, fmusic_ordercallback, order);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_SetInstCallback
 * Signature: (JLorg/lwjgl/fmod_instance/FMusicCallback;I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1SetInstCallback(JNIEnv *env, jclass clazz, jlong module, jint inst){
  return fmod_instance->FMUSIC_SetInstCallback((FMUSIC_MODULE*)module, fmusic_instcallback, inst);
}

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_SetSample
 * Signature: (JIJ)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1SetSample
  (JNIEnv *env, jclass clazz, jlong module, jint sampno, jlong sample){
    return fmod_instance->FMUSIC_SetSample((FMUSIC_MODULE *) module, sampno, (FSOUND_SAMPLE *) sample);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_SetUserData
 * Signature: (JLjava/nio/ByteBuffer;I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1SetUserData(JNIEnv *env, jclass clazz, jlong module, jobject data, jint offset) {
  void *userdata = offset + (char*) (*env)->GetDirectBufferAddress(env, data);
  return fmod_instance->FMUSIC_SetUserData((FMUSIC_MODULE *) module, userdata);
}

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_OptimizeChannels
 * Signature: (JII)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1OptimizeChannels
  (JNIEnv *env, jclass clazz, jlong module, jint max, jint min){
    return fmod_instance->FMUSIC_OptimizeChannels((FMUSIC_MODULE *) module, max, min);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    FMUSIC_SetReverb
 * Signature: (Z)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_FMUSIC_1SetReverb
  (JNIEnv *env, jclass clazz, jboolean reverb){
    return fmod_instance->FMUSIC_SetReverb(reverb);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_SetLooping
 * Signature: (JZ)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1SetLooping
  (JNIEnv *env, jclass clazz, jlong module, jboolean looping){
    return fmod_instance->FMUSIC_SetLooping((FMUSIC_MODULE *) module, looping);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_SetOrder
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1SetOrder
  (JNIEnv *env, jclass clazz, jlong module, jint order){
    return fmod_instance->FMUSIC_SetOrder((FMUSIC_MODULE *) module, order);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_SetPaused
 * Signature: (JZ)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1SetPaused
  (JNIEnv *env, jclass clazz, jlong module, jboolean paused){
    return fmod_instance->FMUSIC_SetPaused((FMUSIC_MODULE *) module, paused);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_SetMasterVolume
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1SetMasterVolume
  (JNIEnv *env, jclass clazz, jlong module, jint volume){
    return fmod_instance->FMUSIC_SetMasterVolume((FMUSIC_MODULE *) module, volume);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_SetMasterSpeed
 * Signature: (JF)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1SetMasterSpeed
  (JNIEnv *env, jclass clazz, jlong module, jfloat speed){
    return fmod_instance->FMUSIC_SetMasterSpeed((FMUSIC_MODULE *) module, speed);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_SetPanSeperation
 * Signature: (JF)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1SetPanSeperation
  (JNIEnv *env, jclass clazz, jlong module, jfloat pan){
    return fmod_instance->FMUSIC_SetPanSeperation((FMUSIC_MODULE *) module, pan);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetName
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetName
  (JNIEnv *env, jclass clazz, jlong module) {
    const char * name = fmod_instance->FMUSIC_GetName((FMUSIC_MODULE *) module);
    return NewStringNativeWithLength(env, name, strlen(name));
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetType
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetType
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetType((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetNumOrders
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetNumOrders
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetNumOrders((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetNumPatterns
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetNumPatterns
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetNumPatterns((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetNumInstruments
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetNumInstruments
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetNumInstruments((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetNumSamples
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetNumSamples
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetNumSamples((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetNumChannels
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetNumChannels
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetNumChannels((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetSample
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetSample
  (JNIEnv *env, jclass clazz, jlong module, jint sampleno){
    return (jlong) fmod_instance->FMUSIC_GetSample((FMUSIC_MODULE *) module, sampleno);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetPatternLength
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetPatternLength
  (JNIEnv *env, jclass clazz, jlong module, jint orderno){
    return fmod_instance->FMUSIC_GetPatternLength((FMUSIC_MODULE *) module, orderno);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_IsFinished
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1IsFinished
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_IsFinished((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_IsPlaying
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1IsPlaying
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_IsPlaying((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetMasterVolume
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetMasterVolume
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetMasterVolume((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetGlobalVolume
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetGlobalVolume
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetGlobalVolume((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetOrder
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetOrder
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetOrder((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetPattern
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetPattern
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetPattern((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetSpeed
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetSpeed
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetSpeed((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetBPM
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetBPM
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetBPM((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetRow
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetRow
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetRow((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetPaused
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetPaused
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetPaused((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetTime
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetTime
  (JNIEnv *env, jclass clazz, jlong module){
    return fmod_instance->FMUSIC_GetTime((FMUSIC_MODULE *) module);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetRealChannel
 * Signature: (JI)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetRealChannel
  (JNIEnv *env, jclass clazz, jlong module, jint modchannel){
    return fmod_instance->FMUSIC_GetRealChannel((FMUSIC_MODULE *) module, modchannel);
  }

/*
 * Class:     org_lwjgl_fmod3_FMusic
 * Method:    nFMUSIC_GetUserData
 * Signature: (J)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_fmod3_FMusic_nFMUSIC_1GetUserData(JNIEnv *env, jclass clazz, jlong module, jint capacity) {
  void* data = (void*) fmod_instance->FMUSIC_GetUserData((FMUSIC_MODULE *) module);
  return (*env)->NewDirectByteBuffer(env, data, capacity);
}

/**
 * This attaches the mixer thread as a daemon thread, and sets its
 * priority to max value
 */
void attachMixerThread() {
  jclass threadClass;
  jmethodID currentThread;
  jobject myThread;
  jfieldID highPriority;
  jint highPriorityValue;
  jmethodID priority;
  JavaVM *jvm = getJVM();
  (*jvm)->AttachCurrentThreadAsDaemon(jvm, (void*)&mixer_jnienv, NULL);
  
  // set to high priority
  // ==============================
  // get current thread
  threadClass = (*mixer_jnienv)->FindClass(mixer_jnienv, "java/lang/Thread");
  currentThread = (*mixer_jnienv)->GetStaticMethodID(mixer_jnienv, threadClass, "currentThread", "()Ljava/lang/Thread;");
  myThread = (*mixer_jnienv)->CallStaticObjectMethod(mixer_jnienv, threadClass, currentThread);
  
  // get value of high priority
  highPriority = (*mixer_jnienv)->GetStaticFieldID(mixer_jnienv, threadClass, "MAX_PRIORITY", "I");
  highPriorityValue = (*mixer_jnienv)->GetStaticIntField(mixer_jnienv, threadClass, highPriority);
  
  // call set priority
  priority = (*mixer_jnienv)->GetMethodID(mixer_jnienv, threadClass, "setPriority", "(I)V");
  (*mixer_jnienv)->CallVoidMethod(mixer_jnienv, myThread, priority, highPriorityValue);
  // ------------------------------
}

// FMusic callbacks
// =======================================
void F_CALLBACKAPI fmusic_instcallback(FMUSIC_MODULE *mod, unsigned char param) {
  if (mixer_jnienv == NULL) { attachMixerThread(); }
  (*mixer_jnienv)->CallStaticVoidMethod(mixer_jnienv, fmusic, music_instcallback, (jlong) mod, (jint) param);
}

void F_CALLBACKAPI fmusic_ordercallback(FMUSIC_MODULE *mod, unsigned char param) {
  if (mixer_jnienv == NULL) { attachMixerThread(); }
  (*mixer_jnienv)->CallStaticVoidMethod(mixer_jnienv, fmusic, music_ordercallback, (jlong) mod, (jint) param);
}

void F_CALLBACKAPI fmusic_rowcallback(FMUSIC_MODULE *mod, unsigned char param) {
  if (mixer_jnienv == NULL) { attachMixerThread(); }
  (*mixer_jnienv)->CallStaticVoidMethod(mixer_jnienv, fmusic, music_rowcallback, (jlong) mod, (jint) param);
}

void F_CALLBACKAPI fmusic_zxxcallback(FMUSIC_MODULE *mod, unsigned char param) {
  if (mixer_jnienv == NULL) { attachMixerThread(); }
  (*mixer_jnienv)->CallStaticVoidMethod(mixer_jnienv, fmusic, music_zxxcallback, (jlong) mod, (jint) param);
}
// ------------------------------------------
