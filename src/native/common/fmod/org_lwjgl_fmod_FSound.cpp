/* 
* Copyright (c) 2002-2004 Lightweight Java Game Library Project
* All rights reserved.
* 
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are 
* met:
* 
* * Redistributions of source code must retain the above copyright 
*	 notice, this list of conditions and the following disclaimer.
*
* * Redistributions in binary form must reproduce the above copyright
*	 notice, this list of conditions and the following disclaimer in the
*	 documentation and/or other materials provided with the distribution.
*
* * Neither the name of 'Lightweight Java Game Library' nor the names of 
*	 its contributors may be used to endorse or promote products derived 
*	 from this software without specific prior written permission.
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
#include "org_lwjgl_fmod_FSound.h"
#include "extfmod.h"

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_Close
* Signature: ()V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1Close(JNIEnv * env, jclass clazz) {
  fmod->FSOUND_Close();
}


/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_File_SetCallbacks
* Signature: ()V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1File_1SetCallbacks(JNIEnv * env, jclass clazz) {
  throwFMODException(env, "missing implementation");
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_Init
* Signature: (III)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1Init
(JNIEnv *env, jclass clazz, jint mixrate, jint channels, jint flags) {
  return fmod->FSOUND_Init(mixrate, channels, flags);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetBufferSize
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetBufferSize(JNIEnv * env, jclass clazz, jint len_ms) {
  return fmod->FSOUND_SetBufferSize(len_ms);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetDriver
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetDriver(JNIEnv * env, jclass clazz, jint driver) { 
  return fmod->FSOUND_SetDriver(driver);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetHWND
* Signature: ()Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetHWND(JNIEnv * env, jclass clazz) { 
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetMaxHardwareChannels
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetMaxHardwareChannels(JNIEnv * env, jclass clazz, jint max) { 
  return fmod->FSOUND_SetMaxHardwareChannels(max);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetMinHardwareChannels
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetMinHardwareChannels(JNIEnv * env, jclass clazz, jint min) {
  return fmod->FSOUND_SetMinHardwareChannels(min);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetMixer
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetMixer(JNIEnv * env, jclass clazz, jint mixer) {
  return fmod->FSOUND_SetMixer(mixer);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetOutput
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetOutput(JNIEnv * env, jclass clazz, jint outputtype) {
  return fmod->FSOUND_SetOutput(outputtype); 
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetPanSeperation
* Signature: (F)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetPanSeperation(JNIEnv * env, jclass clazz, jfloat pansep) {
  return fmod->FSOUND_SetPanSeperation(pansep);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetSFXMasterVolume
* Signature: (I)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetSFXMasterVolume(JNIEnv * env, jclass clazz, jint volume) { 
  fmod->FSOUND_SetSFXMasterVolume(volume);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetSpeakerMode
* Signature: (I)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetSpeakerMode(JNIEnv * env, jclass clazz, jint speakermode) {
  fmod->FSOUND_SetSpeakerMode(speakermode);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_Update
* Signature: ()V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1Update(JNIEnv * env, jclass clazz) { 
  fmod->FSOUND_Update();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetCPUUsage
* Signature: ()F
*/
JNIEXPORT jfloat JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetCPUUsage(JNIEnv * env, jclass clazz) {
  return fmod->FSOUND_GetCPUUsage(); 
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetChannelsPlaying
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetChannelsPlaying(JNIEnv * env, jclass clazz) { 
  return fmod->FSOUND_GetChannelsPlaying();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetDriver
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetDriver(JNIEnv * env, jclass clazz) {
  return fmod->FSOUND_GetDriver(); 
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetDriverCaps
* Signature: (ILjava/nio/IntBuffer;)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetDriverCaps(JNIEnv * env, jclass clazz, jint, jobject) {
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetDriverName
* Signature: (I)Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetDriverName(JNIEnv * env, jclass clazz, jint id) { 
  return env->NewStringUTF((const char *) fmod->FSOUND_GetDriverName(id));
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetError
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetError(JNIEnv * env, jclass clazz) {
  return fmod->FSOUND_GetError();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetMaxSamples
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetMaxSamples(JNIEnv * env, jclass clazz) { 
  return fmod->FSOUND_GetMaxSamples();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetMaxChannels
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetMaxChannels(JNIEnv * env, jclass clazz) {
  return fmod->FSOUND_GetMaxChannels(); 
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetMemoryStats
* Signature: (Ljava/nio/IntBuffer;)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetMemoryStats(JNIEnv * env, jclass clazz, jobject) {
  throwFMODException(env, "missing implementation"); 
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetNumDrivers
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetNumDrivers(JNIEnv * env, jclass clazz) { 
  return fmod->FSOUND_GetNumDrivers();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetNumHWChannels
* Signature: (Ljava/nio/IntBuffer;)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetNumHWChannels(JNIEnv * env, jclass clazz, jobject) {
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetOutput
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetOutput(JNIEnv * env, jclass clazz) {
  return fmod->FSOUND_GetOutput();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetOutputRate
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetOutputRate(JNIEnv * env, jclass clazz) {
  return fmod->FSOUND_GetOutputRate(); 
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetSFXMasterVolume
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetSFXMasterVolume(JNIEnv * env, jclass clazz) {
  return fmod->FSOUND_GetSFXMasterVolume(); 
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetVersion
* Signature: ()F
*/
JNIEXPORT jfloat JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetVersion(JNIEnv * env, jclass clazz) { 
  return fmod->FSOUND_GetVersion();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_Alloc
* Signature: (IIIIIII)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1Alloc(JNIEnv * env, jclass clazz, jint, jint, jint, jint, jint, jint, jint) { 
  throwFMODException(env, "missing implementation");
  return 0;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_Free
* Signature: (J)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1Free(JNIEnv * env, jclass clazz, jlong) { 
  //XXX
  throwFMODException(env, "missing implementation");
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_Get
* Signature: (I)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1Get(JNIEnv * env, jclass clazz, jint sampno) { 
  return (jlong) fmod->FSOUND_Sample_Get(sampno);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_GetDefaults
* Signature: (JLjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1GetDefaults(JNIEnv * env, jclass clazz, jlong, jobject, jint, jobject, jint, jobject, jint, jobject, jint) { 
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_GetDefaultsEx
* Signature: (JLjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1GetDefaultsEx(JNIEnv * env, jclass clazz, jlong, jobject, jint, jobject, jint, jobject, jint, jobject, jint, jobject, jint, jobject, jint, jobject, jint) { 
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_GetLength
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1GetLength(JNIEnv * env, jclass clazz, jlong sptr) { 
  return fmod->FSOUND_Sample_GetLength((FSOUND_SAMPLE *) sptr);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_GetLoopPoints
* Signature: (JLjava/nio/IntBuffer;ILjava/nio/IntBuffer;I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1GetLoopPoints(JNIEnv * env, jclass clazz, jlong, jobject, jint, jobject, jint) { 
  //XXX
  throwFMODException(env, "missing implementation");
  return 0;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_GetMinMaxDistance
* Signature: (JLjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1GetMinMaxDistance(JNIEnv * env, jclass clazz, jlong, jobject, jint, jobject, jint) { 
  //XXX
  throwFMODException(env, "missing implementation");
  return 0;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_GetMode
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1GetMode(JNIEnv * env, jclass clazz, jlong sptr) { 
  return fmod->FSOUND_Sample_GetMode((FSOUND_SAMPLE *) sptr);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_GetName
* Signature: (J)Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1GetName(JNIEnv * env, jclass clazz, jlong sptr) { 
  return env->NewStringUTF(fmod->FSOUND_Sample_GetName((FSOUND_SAMPLE *) sptr));
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_Load
* Signature: (ILjava/nio/ByteBuffer;IIII)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1Load__ILjava_nio_ByteBuffer_2IIII(JNIEnv * env, jclass clazz, jint, jobject, jint, jint, jint, jint) { 
  //XXX
  throwFMODException(env, "missing implementation");
  return 0;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_Load
* Signature: (ILjava/lang/String;III)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1Load__ILjava_lang_String_2III(JNIEnv * env, jclass clazz, jint, jstring, jint, jint, jint) { 
  //XX
  return 0;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_Lock
* Signature: (JIILorg/lwjgl/fmod/FSoundSampleLock;)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1Lock(JNIEnv * env, jclass clazz, jlong, jint, jint, jobject) { 
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_SetDefaults
* Signature: (JIIII)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1SetDefaults(JNIEnv * env, jclass clazz, jlong, jint, jint, jint, jint) { 
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_SetDefaultsEx
* Signature: (JIIIIIII)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1SetDefaultsEx(JNIEnv * env, jclass clazz, jlong, jint, jint, jint, jint, jint, jint, jint) {
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_SetMaxPlaybacks
* Signature: (JI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1SetMaxPlaybacks(JNIEnv * env, jclass clazz, jlong sptr, jint max) {
  return fmod->FSOUND_Sample_SetMaxPlaybacks((FSOUND_SAMPLE*) sptr, max);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_SetMinMaxDistance
* Signature: (JFF)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1SetMinMaxDistance(JNIEnv * env, jclass clazz, jlong sptr, jfloat min, jfloat max) { 
  return fmod->FSOUND_Sample_SetMinMaxDistance((FSOUND_SAMPLE*) sptr, min, max);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_SetMode
* Signature: (JI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1SetMode(JNIEnv * env, jclass clazz, jlong sptr, jint mode) { 
  return fmod->FSOUND_Sample_SetMode((FSOUND_SAMPLE*) sptr, mode);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_SetLoopPoints
* Signature: (JII)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1SetLoopPoints(JNIEnv * env, jclass clazz, jlong sptr, jint loopstart, jint loopend) { 
  return fmod->FSOUND_Sample_SetLoopPoints((FSOUND_SAMPLE*) sptr, loopstart, loopend);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_Unlock
* Signature: (JILorg/lwjgl/fmod/FSoundSampleLock;)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1Unlock(JNIEnv * env, jclass clazz, jlong, jint, jobject) { 
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Sample_Upload
* Signature: (JLjava/nio/ByteBuffer;II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Sample_1Upload(JNIEnv * env, jclass clazz, jlong, jobject, jint, jint) { 
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_PlaySound
* Signature: (IJ)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1PlaySound(JNIEnv * env, jclass clazz, jint channel, jlong sptr) {
  return fmod->FSOUND_PlaySound(channel, (FSOUND_SAMPLE*) sptr);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_PlaySoundEx
* Signature: (IJJZ)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1PlaySoundEx(JNIEnv * env, jclass clazz, jint channel, jlong sptr, jlong dsp, jboolean startpaused) {
  return fmod->FSOUND_PlaySoundEx(channel, (FSOUND_SAMPLE*) sptr, (FSOUND_DSPUNIT*) dsp, startpaused);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_StopSound
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1StopSound(JNIEnv * env, jclass clazz, jint channel) {
  return fmod->FSOUND_StopSound(channel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetFrequency
* Signature: (II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetFrequency(JNIEnv * env, jclass clazz, jint channel, jint freq) {
  return fmod->FSOUND_SetFrequency(channel, freq);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetLevels
* Signature: (IIIIIII)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetLevels(JNIEnv * env, jclass clazz, jint, jint, jint, jint, jint, jint, jint) {
  //XBOX only
  //XXX
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetLoopMode
* Signature: (II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetLoopMode(JNIEnv * env, jclass clazz, jint channel, jint loopmode) {
  return fmod->FSOUND_SetLoopMode(channel, loopmode);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetMute
* Signature: (IZ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetMute(JNIEnv * env, jclass clazz, jint channel, jboolean mute) {
  return fmod->FSOUND_SetMute(channel, mute);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetPan
* Signature: (II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetPan(JNIEnv * env, jclass clazz, jint channel, jint pan) {
  return fmod->FSOUND_SetPan(channel, pan);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetPaused
* Signature: (IZ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetPaused(JNIEnv * env, jclass clazz, jint channel, jboolean paused) {
  return fmod->FSOUND_SetPaused(channel, paused);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetPriority
* Signature: (II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetPriority(JNIEnv * env, jclass clazz, jint channel, jint priority) {
  return fmod->FSOUND_SetPriority(channel, priority);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetReserved
* Signature: (IZ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetReserved(JNIEnv * env, jclass clazz, jint channel, jboolean reserved) {
  return fmod->FSOUND_SetReserved(channel, reserved);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetSurround
* Signature: (IZ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetSurround(JNIEnv * env, jclass clazz, jint channel, jboolean surround) {
  return fmod->FSOUND_SetSurround(channel, surround);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetVolume
* Signature: (II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetVolume(JNIEnv * env, jclass clazz, jint channel, jint vol) {
  return fmod->FSOUND_SetVolume(channel, vol);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetVolumeAbsolute
* Signature: (II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetVolumeAbsolute(JNIEnv * env, jclass clazz, jint channel, jint vol) {
  return fmod->FSOUND_SetVolumeAbsolute(channel, vol);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetVolume
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetVolume(JNIEnv * env, jclass clazz, jint channel) {
  return fmod->FSOUND_GetVolume(channel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetAmplitude
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetAmplitude(JNIEnv * env, jclass clazz, jint channel) {
  return fmod->FSOUND_GetAmplitude(channel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_3D_SetAttributes
* Signature: (ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_13D_1SetAttributes(JNIEnv * env, jclass clazz, jint, jobject, jint, jobject, jint) {
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_3D_SetMinMaxDistance
* Signature: (III)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_13D_1SetMinMaxDistance(JNIEnv * env, jclass clazz, jint channel, jint min, jint max) {
  return fmod->FSOUND_3D_SetMinMaxDistance(channel, min, max);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_SetCurrentPosition
* Signature: (II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1SetCurrentPosition(JNIEnv * env, jclass clazz, jint channel, jint offset) {
  return fmod->FSOUND_SetCurrentPosition(channel, offset);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetCurrentPosition
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetCurrentPosition(JNIEnv * env, jclass clazz, jint channel) {
  return fmod->FSOUND_GetCurrentPosition(channel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_GetCurrentSample
* Signature: (I)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1GetCurrentSample(JNIEnv * env, jclass clazz, jint channel) { 
  return (jlong) fmod->FSOUND_GetCurrentSample(channel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_GetCurrentLevels
* Signature: (ILjava/nio/FloatBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1GetCurrentLevels(JNIEnv * env, jclass clazz, jint, jobject, jint) {
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetFrequency
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetFrequency(JNIEnv * env, jclass clazz, jint channel) {
  return fmod->FSOUND_GetFrequency(channel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetLoopMode
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetLoopMode(JNIEnv * env, jclass clazz, jint channel) {
  return fmod->FSOUND_GetLoopMode(channel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetMixer
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetMixer(JNIEnv * env, jclass clazz) {
  return fmod->FSOUND_GetMixer();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetMute
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetMute(JNIEnv * env, jclass clazz, jint channel) {
  return fmod->FSOUND_GetMute(channel);  
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetNumSubChannels
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetNumSubChannels(JNIEnv * env, jclass clazz, jint channel) {
  return fmod->FSOUND_GetNumSubChannels(channel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetPan
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetPan(JNIEnv * env, jclass clazz, jint channel) {
  return fmod->FSOUND_GetPan(channel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetPaused
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetPaused(JNIEnv * env, jclass clazz, jint channel) {
  return fmod->FSOUND_GetPaused(channel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetPriority
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetPriority(JNIEnv * env, jclass clazz, jint channel) {
  return fmod->FSOUND_GetPriority(channel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetReserved
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetReserved(JNIEnv * env, jclass clazz, jint channel) {
  return fmod->FSOUND_GetReserved(channel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetSubChannel
* Signature: (II)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetSubChannel(JNIEnv * env, jclass clazz, jint channel, jint subchannel) {
  return fmod->FSOUND_GetSubChannel(channel, subchannel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_GetSurround
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1GetSurround(JNIEnv * env, jclass clazz, jint channel) {
  return fmod->FSOUND_GetSurround(channel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_IsPlaying
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1IsPlaying(JNIEnv * env, jclass clazz, jint channel) {
  return fmod->FSOUND_IsPlaying(channel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_3D_GetAttributes
* Signature: (ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_13D_1GetAttributes(JNIEnv * env, jclass clazz, jint, jobject, jint, jobject, jint) {
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_3D_GetMinMaxDistance
* Signature: (ILjava/nio/FloatBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_13D_1GetMinMaxDistance(JNIEnv * env, jclass clazz, jint, jobject, jint) {
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_3D_Listener_GetAttributes
* Signature: (Ljava/nio/FloatBuffer;ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;I)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_13D_1Listener_1GetAttributes(JNIEnv * env, jclass clazz, jobject, jint, jobject, jint, jobject, jint, jobject, jint, jobject, jint, jobject, jint, jobject, jint, jobject, jint) {
  //XXX
  throwFMODException(env, "missing implementation");
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_3D_Listener_SetAttributes
* Signature: (Ljava/nio/FloatBuffer;ILjava/nio/FloatBuffer;IFFFFFF)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_13D_1Listener_1SetAttributes(JNIEnv * env, jclass clazz, jobject, jint, jobject, jint, jfloat, jfloat, jfloat, jfloat, jfloat, jfloat) {
  //XXX
  throwFMODException(env, "missing implementation");
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_3D_Listener_SetCurrent
* Signature: (II)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_13D_1Listener_1SetCurrent(JNIEnv * env, jclass clazz, jint current, jint numlisteners) {
  fmod->FSOUND_3D_Listener_SetCurrent(current, numlisteners);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_3D_SetDistanceFactor
* Signature: (F)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_13D_1SetDistanceFactor(JNIEnv * env, jclass clazz, jfloat scale) {
  fmod->FSOUND_3D_SetDistanceFactor(scale);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_3D_SetDopplerFactor
* Signature: (F)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_13D_1SetDopplerFactor(JNIEnv * env, jclass clazz, jfloat scale) {
  return fmod->FSOUND_3D_SetDopplerFactor(scale);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_3D_SetRolloffFactor
* Signature: (F)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_13D_1SetRolloffFactor(JNIEnv * env, jclass clazz, jfloat scale) {
  return fmod->FSOUND_3D_SetRolloffFactor(scale);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_Open
* Signature: (Ljava/nio/ByteBuffer;IIII)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1Open__Ljava_nio_ByteBuffer_2IIII(JNIEnv * env, jclass clazz, jobject data, jint dataOffset, jint mode, jint offset, jint length) {
  const char *streamData = dataOffset + (char *) env->GetDirectBufferAddress(data);
  return (jlong) fmod->FSOUND_Stream_Open(streamData, mode, offset, length);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_Open
* Signature: (Ljava/lang/String;III)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1Open__Ljava_lang_String_2III(JNIEnv * env, jclass clazz, jstring name, jint mode, jint offset, jint length) {
  const char* filename = (const char*) (env->GetStringUTFChars(name, 0));
  return (jlong) fmod->FSOUND_Stream_Open(filename, mode, offset, length);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_Play
* Signature: (IJ)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1Play(JNIEnv * env, jclass clazz, jint channel, jlong handle) {
  return fmod->FSOUND_Stream_Play(channel, (FSOUND_STREAM*) handle);
}

/*
 * Class:     org_lwjgl_fmod_FSound
 * Method:    nFSOUND_Stream_PlayEx
 * Signature: (IJJZ)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1PlayEx(JNIEnv * env, jclass clazz, jint channel, jlong stream, jlong dsp, jboolean startpaused) {
  return fmod->FSOUND_Stream_PlayEx(channel, (FSOUND_STREAM*) stream, (FSOUND_DSPUNIT*) dsp, startpaused);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_Stop
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1Stop(JNIEnv * env, jclass clazz, jlong handle) {
  return fmod->FSOUND_Stream_Stop((FSOUND_STREAM*) handle);
}


/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_Close
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1Close(JNIEnv * env, jclass clazz, jlong handle) {
  return fmod->FSOUND_Stream_Close((FSOUND_STREAM*) handle);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_GetNumSubStreams
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1GetNumSubStreams(JNIEnv * env, jclass clazz, jlong handle) {
  return fmod->FSOUND_Stream_GetNumSubStreams((FSOUND_STREAM*) handle);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_SetSubStream
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1SetSubStream(JNIEnv * env, jclass clazz, jlong handle, jint index) {
  return fmod->FSOUND_Stream_SetSubStream((FSOUND_STREAM*) handle, index);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_AddSyncPoint
* Signature: (JILjava/lang/String;)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1AddSyncPoint(JNIEnv * env, jclass clazz, jlong, jint, jstring) {
  //XXX
  throwFMODException(env, "missing implementation");
  return 0;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_Create
* Signature: (III)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1Create(JNIEnv * env, jclass clazz, jint, jint, jint) {
  //XXX
  throwFMODException(env, "missing implementation");
  return 0;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_CreateDSP
* Signature: (JI)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1CreateDSP(JNIEnv * env, jclass clazz, jlong, jint) {
  //XXX
  throwFMODException(env, "missing implementation");
  return 0;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_DeleteSyncPoint
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1DeleteSyncPoint(JNIEnv * env, jclass clazz, jlong point) {
  return fmod->FSOUND_Stream_DeleteSyncPoint((FSOUND_SYNCPOINT*) point);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_FindTagField
* Signature: (JILjava/lang/String;Lorg/lwjgl/fmod/FSoundTagField;)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1FindTagField(JNIEnv * env, jclass clazz, jlong, jint, jstring, jobject) {
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_GetLength
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1GetLength(JNIEnv * env, jclass clazz, jlong stream) {
  return fmod->FSOUND_Stream_GetLength((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_GetLengthMs
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1GetLengthMs(JNIEnv * env, jclass clazz, jlong stream) {
  return fmod->FSOUND_Stream_GetLengthMs((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_GetMode
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1GetMode(JNIEnv * env, jclass clazz, jlong stream) {
  return fmod->FSOUND_Stream_GetMode((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_GetNumSyncPoints
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1GetNumSyncPoints(JNIEnv * env, jclass clazz, jlong stream) {
  return fmod->FSOUND_Stream_GetNumSyncPoints((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_GetNumTagFields
* Signature: (JLjava/nio/IntBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1GetNumTagFields(JNIEnv * env, jclass clazz, jlong, jobject, jint) {
  // XXX
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_GetOpenState
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1GetOpenState(JNIEnv * env, jclass clazz, jlong stream) {
  return fmod->FSOUND_Stream_GetOpenState((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_GetPosition
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1GetPosition(JNIEnv * env, jclass clazz, jlong stream) {
  return fmod->FSOUND_Stream_GetPosition((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_GetSample
* Signature: (J)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1GetSample(JNIEnv * env, jclass clazz, jlong stream) {
  return (jlong) fmod->FSOUND_Stream_GetSample((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_GetSyncPoint
* Signature: (JI)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1GetSyncPoint(JNIEnv * env, jclass clazz, jlong stream, jint index) {
  return (jlong) fmod->FSOUND_Stream_GetSyncPoint((FSOUND_STREAM*) stream, index);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_GetSyncPointInfo
* Signature: (JLjava/nio/IntBuffer;I)Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1GetSyncPointInfo(JNIEnv * env, jclass clazz, jlong, jobject, jint) {
  //XXX
  throwFMODException(env, "missing implementation");
  return NULL;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_GetTagField
* Signature: (JILorg/lwjgl/fmod/FSoundTagField;)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1GetTagField(JNIEnv * env, jclass clazz, jlong, jint, jobject) {
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_GetTime
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1GetTime(JNIEnv * env, jclass clazz, jlong stream) {
  return fmod->FSOUND_Stream_GetTime((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_Net_GetBufferProperties
* Signature: (Ljava/nio/IntBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1Net_1GetBufferProperties(JNIEnv * env, jclass clazz, jobject, jint) {
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_Stream_Net_GetBufferProperties
* Signature: ()Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1Stream_1Net_1GetLastServerStatus(JNIEnv * env, jclass clazz) {
  return env->NewStringUTF(fmod->FSOUND_Stream_Net_GetLastServerStatus());
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_Net_GetStatus
* Signature: (JLjava/nio/IntBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1Net_1GetStatus(JNIEnv * env, jclass clazz, jlong, jobject, jint) {
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_Stream_Net_SetBufferProperties
* Signature: (III)Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1Stream_1Net_1SetBufferProperties(JNIEnv * env, jclass clazz, jint, jint, jint) {
  //XXX
  throwFMODException(env, "missing implementation");
  return NULL;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_Net_SetMetadataCallback
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1Net_1SetMetadataCallback(JNIEnv * env, jclass clazz, jlong) {
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_Stream_Net_SetProxy
* Signature: (Ljava/lang/String;)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1Stream_1Net_1SetProxy(JNIEnv * env, jclass clazz, jstring proxy) {
  const char * proxyString = env->GetStringUTFChars(proxy, 0);
  jboolean result = fmod->FSOUND_Stream_Net_SetProxy(proxyString);
  env->ReleaseStringUTFChars(proxy, proxyString);
  return result;
}



/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_Stream_SetBufferSize
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1Stream_1SetBufferSize(JNIEnv * env, jclass clazz, jint ms) {
  return fmod->FSOUND_Stream_SetBufferSize(ms);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_SetEndCallback
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1SetEndCallback(JNIEnv * env, jclass clazz, jlong) {
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_SetLoopCount
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1SetLoopCount(JNIEnv * env, jclass clazz, jlong stream, jint count) {
  return fmod->FSOUND_Stream_SetLoopCount((FSOUND_STREAM*) stream, count);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_SetLoopPoints
* Signature: (JII)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1SetLoopPoints(JNIEnv * env, jclass clazz, jlong stream, jint loopstartpcm, jint loopendpcm) {
  return fmod->FSOUND_Stream_SetLoopPoints((FSOUND_STREAM*) stream, loopstartpcm, loopendpcm);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_SetMode
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1SetMode(JNIEnv * env, jclass clazz, jlong stream, jint mode) {
  return fmod->FSOUND_Stream_SetMode((FSOUND_STREAM*) stream, mode);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_SetPosition
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1SetPosition(JNIEnv * env, jclass clazz, jlong stream, jint position) {
  return fmod->FSOUND_Stream_SetPosition((FSOUND_STREAM*) stream, position);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_SetSubStreamSentence
* Signature: (JLjava/nio/IntBuffer;I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1SetSubStreamSentence(JNIEnv * env, jclass clazz, jlong, jobject, jint) {
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_Stream_SetSyncCallback
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1Stream_1SetSyncCallback(JNIEnv * env, jclass clazz, jlong) {
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Stream_SetTime
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Stream_1SetTime(JNIEnv * env, jclass clazz, jlong stream, jint ms) {
  return fmod->FSOUND_Stream_SetTime((FSOUND_STREAM*) stream, ms);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_CD_Eject
* Signature: (C)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1CD_1Eject(JNIEnv * env, jclass clazz, jchar drive) {
  return fmod->FSOUND_CD_Eject(drive);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_CD_GetNumTracks
* Signature: (C)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1CD_1GetNumTracks(JNIEnv * env, jclass clazz, jchar drive) {
  return fmod->FSOUND_CD_GetNumTracks(drive);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_CD_GetPaused
* Signature: (C)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1CD_1GetPaused(JNIEnv * env, jclass clazz, jchar drive) {
  return fmod->FSOUND_CD_GetPaused(drive);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_CD_GetTrack
* Signature: (C)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1CD_1GetTrack(JNIEnv * env, jclass clazz, jchar drive) {
  return fmod->FSOUND_CD_GetTrack(drive);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_CD_GetTrackLength
* Signature: (CI)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1CD_1GetTrackLength(JNIEnv * env, jclass clazz, jchar drive, jint track) {
  return fmod->FSOUND_CD_GetTrackLength(drive, track);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_CD_GetTrackTime
* Signature: (C)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1CD_1GetTrackTime(JNIEnv * env, jclass clazz, jchar drive) {
  return fmod->FSOUND_CD_GetTrackTime(drive);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_CD_Play
* Signature: (CI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1CD_1Play(JNIEnv * env, jclass clazz, jchar drive, jint track) {
  return fmod->FSOUND_CD_Play(drive, track);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_CD_SetPaused
* Signature: (CZ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1CD_1SetPaused(JNIEnv * env, jclass clazz, jchar drive, jboolean paused) {
  return fmod->FSOUND_CD_SetPaused(drive, paused);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_CD_SetPlayMode
* Signature: (CI)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1CD_1SetPlayMode(JNIEnv * env, jclass clazz, jchar drive, jint mode) {
  return fmod->FSOUND_CD_SetPlayMode(drive, mode);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_CD_SetTrackTime
* Signature: (CI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1CD_1SetTrackTime(JNIEnv * env, jclass clazz, jchar drive, jint ms) {
  return fmod->FSOUND_CD_SetTrackTime(drive, ms);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_CD_SetVolume
* Signature: (CI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1CD_1SetVolume(JNIEnv * env, jclass clazz, jchar drive, jint volume) {
  return fmod->FSOUND_CD_SetVolume(drive, volume);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_CD_Stop
* Signature: (C)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1CD_1Stop(JNIEnv * env, jclass clazz, jchar drive) {
  return fmod->FSOUND_CD_Stop(drive);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_DSP_ClearMixBuffer
* Signature: ()V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1DSP_1ClearMixBuffer(JNIEnv * env, jclass clazz) {
  fmod->FSOUND_DSP_ClearMixBuffer();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_DSP_Create
* Signature: (I)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1DSP_1Create(JNIEnv * env, jclass clazz, jint priority) {
  //XXX
  //return (jlong) fmod->FSOUND_DSP_Create(fmod_dsp_callback, priority, NULL);
  throwFMODException(env, "missing implementation");
  return 0;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_DSP_Free
* Signature: (J)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1DSP_1Free(JNIEnv * env, jclass clazz, jlong dsp) {
  fmod->FSOUND_DSP_Free((FSOUND_DSPUNIT*) dsp);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_DSP_SetActive
* Signature: (JZ)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1DSP_1SetActive(JNIEnv * env, jclass clazz, jlong dsp, jboolean active) {
  fmod->FSOUND_DSP_SetActive((FSOUND_DSPUNIT*) dsp, active);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_DSP_GetActive
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1DSP_1GetActive(JNIEnv * env, jclass clazz, jlong dsp) {
  return fmod->FSOUND_DSP_GetActive((FSOUND_DSPUNIT*) dsp);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_DSP_GetBufferLength
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1DSP_1GetBufferLength(JNIEnv * env, jclass clazz) {
  return fmod->FSOUND_DSP_GetBufferLength();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_DSP_GetBufferLengthTotal
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1DSP_1GetBufferLengthTotal(JNIEnv * env, jclass clazz) {
  return fmod->FSOUND_DSP_GetBufferLengthTotal();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_DSP_SetPriority
* Signature: (JI)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1DSP_1SetPriority(JNIEnv * env, jclass clazz, jlong dsp, jint priority) {
  fmod->FSOUND_DSP_SetPriority((FSOUND_DSPUNIT*) dsp, priority);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_DSP_GetPriority
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1DSP_1GetPriority(JNIEnv * env, jclass clazz, jlong dsp) {
  return fmod->FSOUND_DSP_GetPriority((FSOUND_DSPUNIT*) dsp);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_DSP_GetClearUnit
* Signature: ()J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1DSP_1GetClearUnit(JNIEnv * env, jclass clazz) {
  return (jlong) fmod->FSOUND_DSP_GetClearUnit();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_DSP_GetClipAndCopyUnit
* Signature: ()J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1DSP_1GetClipAndCopyUnit(JNIEnv * env, jclass clazz) {
  return (jlong) fmod->FSOUND_DSP_GetClipAndCopyUnit();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_DSP_GetMusicUnit
* Signature: ()J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1DSP_1GetMusicUnit(JNIEnv * env, jclass clazz) {
  return (jlong) fmod->FSOUND_DSP_GetMusicUnit();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_DSP_GetSFXUnit
* Signature: ()J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1DSP_1GetSFXUnit(JNIEnv * env, jclass clazz) {
  return (jlong) fmod->FSOUND_DSP_GetSFXUnit();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_DSP_GetFFTUnit
* Signature: ()J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1DSP_1GetFFTUnit(JNIEnv * env, jclass clazz) {
  return (jlong) fmod->FSOUND_DSP_GetFFTUnit();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_DSP_GetSpectrum
* Signature: ()Ljava/nio/FloatBuffer;
*/
JNIEXPORT jobject JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1DSP_1GetSpectrum(JNIEnv * env, jclass clazz) {
  return env->NewDirectByteBuffer(fmod->FSOUND_DSP_GetSpectrum(), (512 * sizeof(float)));
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_DSP_MixBuffers
* Signature: (Ljava/nio/ByteBuffer;ILjava/nio/ByteBuffer;IIIIII)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1DSP_1MixBuffers(JNIEnv * env, jclass clazz, jobject, jint, jobject, jint, jint, jint, jint, jint, jint) {
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_FX_Disable
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1FX_1Disable(JNIEnv * env, jclass clazz, jint channel) {
  return fmod->FSOUND_FX_Disable(channel);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_FX_Enable
* Signature: (II)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1FX_1Enable(JNIEnv * env, jclass clazz, jint channel, jint fx) {
  return fmod->FSOUND_FX_Enable(channel, fx);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_FX_SetChorus
* Signature: (IFFFFIFI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1FX_1SetChorus(JNIEnv * env, jclass clazz, jint fxid, jfloat WetDryMix, jfloat Depth, jfloat Feedback, jfloat Frequency, jint Waveform, jfloat Delay, jint Phase) {
  return fmod->FSOUND_FX_SetChorus(fxid, WetDryMix, Depth, Feedback, Frequency, Waveform, Delay, Phase);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_FX_SetCompressor
* Signature: (IFFFFFF)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1FX_1SetCompressor(JNIEnv * env, jclass clazz, jint fxid, jfloat Gain, jfloat Attack, jfloat Release, jfloat Threshold, jfloat Ratio, jfloat Predelay) {
  return fmod->FSOUND_FX_SetCompressor(fxid, Gain, Attack, Release, Threshold, Ratio, Predelay);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_FX_SetDistortion
* Signature: (IFFFFF)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1FX_1SetDistortion(JNIEnv * env, jclass clazz, jint fxid, jfloat Gain, jfloat Edge, jfloat PostEQCenterFrequency, jfloat PostEQBandwidth, jfloat PreLowpassCutoff) {
  return fmod->FSOUND_FX_SetDistortion(fxid, Gain, Edge, PostEQCenterFrequency, PostEQBandwidth, PreLowpassCutoff);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_FX_SetEcho
* Signature: (IFFFFI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1FX_1SetEcho(JNIEnv * env, jclass clazz, jint fxid, jfloat WetDryMix, jfloat Feedback, jfloat LeftDelay, jfloat RightDelay, jint PanDelay) {
  return fmod->FSOUND_FX_SetEcho(fxid, WetDryMix, Feedback, LeftDelay, RightDelay, PanDelay);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_FX_SetFlanger
* Signature: (IFFFFIFI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1FX_1SetFlanger(JNIEnv * env, jclass clazz, jint fxid, jfloat WetDryMix, jfloat Depth, jfloat Feedback, jfloat Frequency, jint Waveform, jfloat Delay, jint Phase) {
  return fmod->FSOUND_FX_SetFlanger(fxid, WetDryMix, Depth, Feedback, Frequency, Waveform, Delay, Phase);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_FX_SetGargle
* Signature: (III)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1FX_1SetGargle(JNIEnv * env, jclass clazz, jint fxid, jint RateHz, jint WaveShape) {
  return fmod->FSOUND_FX_SetGargle(fxid, RateHz, WaveShape);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_FX_SetI3DL2Reverb
* Signature: (IIIFFFIFIFFFF)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1FX_1SetI3DL2Reverb(JNIEnv * env, jclass clazz, 
  jint fxid, jint Room, jint RoomHF, jfloat RoomRolloffFactor, jfloat DecayTime, 
  jfloat DecayHFRation, jint Reflections, jfloat ReflectionsDelay, jint Reverb, 
  jfloat ReverbDelay, jfloat Diffusion, jfloat Density, jfloat HFReference) {
  return fmod->FSOUND_FX_SetI3DL2Reverb(
  fxid, Room, RoomHF, RoomRolloffFactor, DecayTime, 
  DecayHFRation, Reflections, ReflectionsDelay, Reverb, 
  ReverbDelay, Diffusion, Density, HFReference);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_FX_SetParamEQ
* Signature: (IFFF)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1FX_1SetParamEQ(JNIEnv * env, jclass clazz, jint fxid, jfloat Center, jfloat Bandwidth, jfloat Gain) {
  return fmod->FSOUND_FX_SetParamEQ(fxid, Center, Bandwidth, Gain);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_FX_SetWavesReverb
* Signature: (IFFFF)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1FX_1SetWavesReverb(JNIEnv * env, jclass clazz, jint fxid, jfloat InGain, jfloat ReverbMix, jfloat ReverbTime, jfloat HighFreqRTRatio) {
  return fmod->FSOUND_FX_SetWavesReverb(fxid, InGain, ReverbMix, ReverbTime, HighFreqRTRatio);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_Record_GetDriver
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1Record_1GetDriver(JNIEnv * env, jclass clazz) {
  return fmod->FSOUND_Record_GetDriver();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_Record_GetDriverName
* Signature: (I)Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1Record_1GetDriverName(JNIEnv * env, jclass clazz, jint driver) {
  return env->NewStringUTF((const char *)fmod->FSOUND_Record_GetDriverName(driver));
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_Record_GetNumDrivers
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1Record_1GetNumDrivers(JNIEnv * env, jclass clazz) {
  return fmod->FSOUND_Record_GetNumDrivers();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_Record_GetPosition
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1Record_1GetPosition(JNIEnv * env, jclass clazz) {
  return fmod->FSOUND_Record_GetPosition();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_Record_SetDriver
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1Record_1SetDriver(JNIEnv * env, jclass clazz, jint outputtype) {
  return fmod->FSOUND_Record_SetDriver(outputtype);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Record_StartSample
* Signature: (JZ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Record_1StartSample(JNIEnv * env, jclass clazz, jlong sample, jboolean loop) {
  return fmod->FSOUND_Record_StartSample((FSOUND_SAMPLE *) sample, loop);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    FSOUND_Record_Stop
* Signature: ()Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_FSOUND_1Record_1Stop(JNIEnv * env, jclass clazz) {
  return fmod->FSOUND_Record_Stop();
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Reverb_SetProperties
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Reverb_1SetProperties(JNIEnv * env, jclass clazz, jlong prop) {
  return fmod->FSOUND_Reverb_SetProperties((FSOUND_REVERB_PROPERTIES*) prop);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Reverb_GetProperties
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Reverb_1GetProperties(JNIEnv * env, jclass clazz, jlong prop) {
  return fmod->FSOUND_Reverb_GetProperties((FSOUND_REVERB_PROPERTIES*) prop);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Reverb_SetChannelProperties
* Signature: (IJ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Reverb_1SetChannelProperties(JNIEnv * env, jclass clazz, jint channel, jlong prop) {
  return fmod->FSOUND_Reverb_SetChannelProperties(channel, (FSOUND_REVERB_CHANNELPROPERTIES*) prop);
}

/*
* Class:     org_lwjgl_fmod_FSound
* Method:    nFSOUND_Reverb_GetChannelProperties
* Signature: (IJ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod_FSound_nFSOUND_1Reverb_1GetChannelProperties(JNIEnv * env, jclass clazz, jint channel, jlong prop) {
  return fmod->FSOUND_Reverb_GetChannelProperties(channel, (FSOUND_REVERB_CHANNELPROPERTIES*) prop);
}
