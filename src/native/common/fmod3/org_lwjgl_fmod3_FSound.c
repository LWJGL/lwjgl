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
#include "org_lwjgl_fmod3_FSound.h"
#include "extfmod3.h"

void * F_CALLBACKAPI fsound_dspcallback(void *originalbuffer, void *newbuffer, int length, void *userdata);
signed char F_CALLBACKAPI fsound_stream_endcallback(FSOUND_STREAM *stream, void *buff, int len, void *param);
signed char F_CALLBACKAPI fsound_stream_synccallback(FSOUND_STREAM *stream, void *buff, int len, void *param);
signed char F_CALLBACKAPI fsound_stream_callback(FSOUND_STREAM *stream, void *buff, int len, void *param);
signed char F_CALLBACKAPI fsound_metadata_callback(char *name,char *value,void *userdata);

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_Close
* Signature: ()V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1Close(JNIEnv * env, jclass clazz) {
  fmod_instance->FSOUND_Close();
}


/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_File_SetCallbacks
* Signature: ()V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1File_1SetCallbacks(JNIEnv * env, jclass clazz) {
  throwFMODException(env, "missing implementation");
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_Init
* Signature: (III)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1Init
(JNIEnv *env, jclass clazz, jint mixrate, jint channels, jint flags) {
  return fmod_instance->FSOUND_Init(mixrate, channels, flags);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetBufferSize
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetBufferSize(JNIEnv * env, jclass clazz, jint len_ms) {
  return fmod_instance->FSOUND_SetBufferSize(len_ms);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetDriver
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetDriver(JNIEnv * env, jclass clazz, jint driver) { 
  return fmod_instance->FSOUND_SetDriver(driver);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetHWND
* Signature: ()Z
*/
//JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetHWND(JNIEnv * env, jclass clazz) { 
//  throwFMODException(env, "missing implementation");
//  return false;
//}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetMaxHardwareChannels
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetMaxHardwareChannels(JNIEnv * env, jclass clazz, jint max) { 
  return fmod_instance->FSOUND_SetMaxHardwareChannels(max);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetMinHardwareChannels
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetMinHardwareChannels(JNIEnv * env, jclass clazz, jint min) {
  return fmod_instance->FSOUND_SetMinHardwareChannels(min);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetMixer
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetMixer(JNIEnv * env, jclass clazz, jint mixer) {
  jboolean result = fmod_instance->FSOUND_SetMixer(mixer);
  
  // if we successfully changed mixer - update cached size field
  if(result) {
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
  
  return result;
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetOutput
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetOutput(JNIEnv * env, jclass clazz, jint outputtype) {
  return fmod_instance->FSOUND_SetOutput(outputtype); 
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetPanSeperation
* Signature: (F)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetPanSeperation(JNIEnv * env, jclass clazz, jfloat pansep) {
  fmod_instance->FSOUND_SetPanSeperation(pansep);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetSFXMasterVolume
* Signature: (I)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetSFXMasterVolume(JNIEnv * env, jclass clazz, jint volume) { 
  fmod_instance->FSOUND_SetSFXMasterVolume(volume);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetSpeakerMode
* Signature: (I)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetSpeakerMode(JNIEnv * env, jclass clazz, jint speakermode) {
  fmod_instance->FSOUND_SetSpeakerMode(speakermode);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_Update
* Signature: ()V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1Update(JNIEnv * env, jclass clazz) { 
  fmod_instance->FSOUND_Update();
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetCPUUsage
* Signature: ()F
*/
JNIEXPORT jfloat JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetCPUUsage(JNIEnv * env, jclass clazz) {
  return fmod_instance->FSOUND_GetCPUUsage(); 
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetChannelsPlaying
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetChannelsPlaying(JNIEnv * env, jclass clazz) { 
  return fmod_instance->FSOUND_GetChannelsPlaying();
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetDriver
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetDriver(JNIEnv * env, jclass clazz) {
  return fmod_instance->FSOUND_GetDriver(); 
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetDriverCaps
* Signature: (ILjava/nio/IntBuffer;)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1GetDriverCaps(JNIEnv * env, jclass clazz, jint id, jobject buffer, jint offset) {
	unsigned int* caps = offset + (unsigned int *) (*env)->GetDirectBufferAddress(env, buffer);
	return fmod_instance->FSOUND_GetDriverCaps(id, caps);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetDriverName
* Signature: (I)Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetDriverName(JNIEnv * env, jclass clazz, jint id) { 
  return NewStringNative(env, (const char *) fmod_instance->FSOUND_GetDriverName(id));
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetError
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetError(JNIEnv * env, jclass clazz) {
  return fmod_instance->FSOUND_GetError();
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetMaxSamples
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetMaxSamples(JNIEnv * env, jclass clazz) { 
  return fmod_instance->FSOUND_GetMaxSamples();
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetMaxChannels
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetMaxChannels(JNIEnv * env, jclass clazz) {
  return fmod_instance->FSOUND_GetMaxChannels(); 
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetMemoryStats
* Signature: (Ljava/nio/IntBuffer;)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1GetMemoryStats(JNIEnv * env, jclass clazz, jobject buffer, jint offset) {
	unsigned int * memory = offset + (unsigned int *) (*env)->GetDirectBufferAddress(env, buffer);
	fmod_instance->FSOUND_GetMemoryStats(&memory[0], &memory[1]);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetNumDrivers
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetNumDrivers(JNIEnv * env, jclass clazz) { 
  return fmod_instance->FSOUND_GetNumDrivers();
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetNumHWChannels
* Signature: (Ljava/nio/IntBuffer;)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1GetNumHWChannels(JNIEnv * env, jclass clazz, jobject buffer, jint offset) {
	int * memory = offset + (int *) (*env)->GetDirectBufferAddress(env, buffer);
	return fmod_instance->FSOUND_GetNumHWChannels(&memory[0], &memory[1], &memory[2]);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetOutput
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetOutput(JNIEnv * env, jclass clazz) {
  return fmod_instance->FSOUND_GetOutput();
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetOutputRate
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetOutputRate(JNIEnv * env, jclass clazz) {
  return fmod_instance->FSOUND_GetOutputRate(); 
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetSFXMasterVolume
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetSFXMasterVolume(JNIEnv * env, jclass clazz) {
  return fmod_instance->FSOUND_GetSFXMasterVolume(); 
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetVersion
* Signature: ()F
*/
JNIEXPORT jfloat JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetVersion(JNIEnv * env, jclass clazz) { 
  return fmod_instance->FSOUND_GetVersion();
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_Alloc
* Signature: (IIIIIII)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1Alloc(JNIEnv * env, jclass clazz, jint index, jint length, jint mode, jint deffreq, jint defvol, jint defpan, jint defpri) { 
  return (long) fmod_instance->FSOUND_Sample_Alloc(index, length, mode, deffreq, defvol, defpan, defpri);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_Free
* Signature: (J)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1Free(JNIEnv * env, jclass clazz, jlong handle) { 
	fmod_instance->FSOUND_Sample_Free((FSOUND_SAMPLE*) handle);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_Get
* Signature: (I)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1Get(JNIEnv * env, jclass clazz, jint sampno) { 
  return (jlong) fmod_instance->FSOUND_Sample_Get(sampno);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_GetDefaults
* Signature: (JLjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1GetDefaults(JNIEnv * env, jclass clazz, jlong sptr, jobject deffreq, jint deffreqOffset, jobject defvol, jint defvolOffset, jobject defpan, jint defpanOffset, jobject defpri, jint defpriOffset) { 
	int * nDeffreq = (deffreq != NULL) ? deffreqOffset + (int *) (*env)->GetDirectBufferAddress(env, deffreq) : NULL;
	int * nDefvol 	= (defvol != NULL) ? defvolOffset + (int *) (*env)->GetDirectBufferAddress(env, defvol) : NULL;
	int * nDefpan 	= (defpan != NULL) ? defpanOffset + (int *) (*env)->GetDirectBufferAddress(env, defpan) : NULL;
	int * nDefpri 	= (defpri != NULL) ? defpriOffset + (int *) (*env)->GetDirectBufferAddress(env, defpri) : NULL;

  return fmod_instance->FSOUND_Sample_GetDefaults((FSOUND_SAMPLE *) sptr, nDeffreq, nDefvol, nDefpan, nDefpri);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_GetDefaultsEx
* Signature: (JLjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1GetDefaultsEx(JNIEnv * env, jclass clazz, jlong sptr, jobject deffreq, jint deffreqOffset, jobject defvol, jint defvolOffset, jobject defpan, jint defpanOffset, jobject defpri, jint defpriOffset, 
jobject varfreq, jint varfreqOffset, jobject varvol, jint varvolOffset, jobject varpan, jint varpanOffset) { 
	int * nDeffreq = (deffreq != NULL) ? deffreqOffset + (int *) (*env)->GetDirectBufferAddress(env, deffreq) : NULL;
	int * nDefvol 	= (defvol != NULL) ? defvolOffset + (int *) (*env)->GetDirectBufferAddress(env, defvol) : NULL;
	int * nDefpan 	= (defpan != NULL) ? defpanOffset + (int *) (*env)->GetDirectBufferAddress(env, defpan) : NULL;
	int * nDefpri 	= (defpri != NULL) ? defpriOffset + (int *) (*env)->GetDirectBufferAddress(env, defpri) : NULL;
	int * nVarfreq 	= (varfreq != NULL) ? varfreqOffset + (int *) (*env)->GetDirectBufferAddress(env, varfreq) : NULL;
	int * nVarvol 	= (varvol != NULL) ? varvolOffset + (int *) (*env)->GetDirectBufferAddress(env, varvol) : NULL;
	int * nVarpan 	= (varpan != NULL) ? varpanOffset + (int *) (*env)->GetDirectBufferAddress(env, varpan) : NULL;

  return fmod_instance->FSOUND_Sample_GetDefaultsEx((FSOUND_SAMPLE *) sptr, nDeffreq, nDefvol, nDefpan, nDefpri, nVarfreq, nVarvol, nVarpan);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_GetLength
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1GetLength(JNIEnv * env, jclass clazz, jlong sptr) { 
  return fmod_instance->FSOUND_Sample_GetLength((FSOUND_SAMPLE *) sptr);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_GetLoopPoints
* Signature: (JLjava/nio/IntBuffer;ILjava/nio/IntBuffer;I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1GetLoopPoints(JNIEnv * env, jclass clazz, jlong sptr, jobject loopstart, jint loopstartOffset, jobject loopend, jint loopendOffset) { 
	int * nLoopstart = (loopstart != NULL) ? loopstartOffset + (int *) (*env)->GetDirectBufferAddress(env, loopstart) : NULL;
	int * nLoopend 	= (loopend != NULL) ? loopendOffset + (int *) (*env)->GetDirectBufferAddress(env, loopend) : NULL;
  return fmod_instance->FSOUND_Sample_GetLoopPoints((FSOUND_SAMPLE *) sptr, nLoopstart, nLoopend);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_GetMinMaxDistance
* Signature: (JLjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1GetMinMaxDistance(JNIEnv * env, jclass clazz, jlong sptr, jobject min, jint minOffset, jobject max, jint maxOffset) { 
	float * nMin = (min != NULL) ? minOffset + (float *) (*env)->GetDirectBufferAddress(env, min) : NULL;
	float * nMax 	= (max != NULL) ? maxOffset + (float *) (*env)->GetDirectBufferAddress(env, max) : NULL;
  return fmod_instance->FSOUND_Sample_GetMinMaxDistance((FSOUND_SAMPLE *) sptr, nMin, nMax);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_GetMode
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1GetMode(JNIEnv * env, jclass clazz, jlong sptr) { 
  return fmod_instance->FSOUND_Sample_GetMode((FSOUND_SAMPLE *) sptr);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_GetName
* Signature: (J)Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1GetName(JNIEnv * env, jclass clazz, jlong sptr) { 
  return NewStringNative(env, fmod_instance->FSOUND_Sample_GetName((FSOUND_SAMPLE *) sptr));
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_Load
* Signature: (ILjava/nio/ByteBuffer;IIII)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1Load__ILjava_nio_ByteBuffer_2III(JNIEnv * env, jclass clazz, jint index, jobject data, jint inputmode, jint offset, jint length) { 
	const char * nData = offset + (const char *) (*env)->GetDirectBufferAddress(env, data);
  return (long) fmod_instance->FSOUND_Sample_Load(index, nData, inputmode, 0, length);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_Load
* Signature: (ILjava/lang/String;III)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1Load__ILjava_lang_String_2III(JNIEnv * env, jclass clazz, jint index, jstring name, jint inputmode, jint offset, jint length) { 
  jlong result;
  char* nName = GetStringNativeChars(env, name);
  result = (jlong) fmod_instance->FSOUND_Sample_Load(index, nName, inputmode, offset, length);
  free(nName);
  return result;
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_Lock
* Signature: (JIILorg/lwjgl/fmod_instance/FSoundSampleLock;)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1Lock(JNIEnv * env, jclass clazz, jlong sptr, jint offset, jint length, jobject sampleLock) { 
	void * ptr1;
	void * ptr2;
	unsigned int len1;
	unsigned int len2;
	signed char result;
	result = fmod_instance->FSOUND_Sample_Lock((FSOUND_SAMPLE *) sptr, offset, length, &ptr1, &ptr2, &len1, &len2);
	
	// if we got true, prime lock
	if(result) {
		// get class & fields
		jclass lock = (*env)->GetObjectClass(env, sampleLock);
		jfieldID objPtr1 = (*env)->GetFieldID(env, lock, "ptr1", "Ljava/nio/ByteBuffer");
		jfieldID objPtr2 = (*env)->GetFieldID(env, lock, "ptr2", "Ljava/nio/ByteBuffer");
		jfieldID objLen1 = (*env)->GetFieldID(env, lock, "len1", "I");
		jfieldID objLen2 = (*env)->GetFieldID(env, lock, "len2", "I");
		
		// set buffers
		if(len1 > 0) {
			(*env)->SetObjectField(env, sampleLock, objPtr1, (*env)->NewDirectByteBuffer(env, ptr1, len1));
		}

		if(len2 > 0) {
			(*env)->SetObjectField(env, sampleLock, objPtr2, (*env)->NewDirectByteBuffer(env, ptr2, len2));
		}
		
		// set lengths
		(*env)->SetIntField(env, sampleLock, objLen1, len1);
		(*env)->SetIntField(env, sampleLock, objLen2, len2);
	}
	
  return result;
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_SetDefaults
* Signature: (JIIII)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1SetDefaults(JNIEnv * env, jclass clazz, jlong sptr, jint deffreq, jint defvol, jint defpan, jint defpri) { 
	return fmod_instance->FSOUND_Sample_SetDefaults((FSOUND_SAMPLE *) sptr, deffreq, defvol, defpan, defpri);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_SetDefaultsEx
* Signature: (JIIIIIII)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1SetDefaultsEx(JNIEnv * env, jclass clazz, jlong sptr, jint deffreq, jint defvol, jint defpan, jint defpri, jint varfreq, jint varvol, jint varpan) {
	return fmod_instance->FSOUND_Sample_SetDefaultsEx((FSOUND_SAMPLE *) sptr, deffreq, defvol, defpan, defpri, varfreq, varvol, varpan);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_SetMaxPlaybacks
* Signature: (JI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1SetMaxPlaybacks(JNIEnv * env, jclass clazz, jlong sptr, jint max) {
  return fmod_instance->FSOUND_Sample_SetMaxPlaybacks((FSOUND_SAMPLE*) sptr, max);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_SetMinMaxDistance
* Signature: (JFF)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1SetMinMaxDistance(JNIEnv * env, jclass clazz, jlong sptr, jfloat min, jfloat max) { 
  return fmod_instance->FSOUND_Sample_SetMinMaxDistance((FSOUND_SAMPLE*) sptr, min, max);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_SetMode
* Signature: (JI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1SetMode(JNIEnv * env, jclass clazz, jlong sptr, jint mode) { 
  return fmod_instance->FSOUND_Sample_SetMode((FSOUND_SAMPLE*) sptr, mode);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_SetLoopPoints
* Signature: (JII)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1SetLoopPoints(JNIEnv * env, jclass clazz, jlong sptr, jint loopstart, jint loopend) { 
  return fmod_instance->FSOUND_Sample_SetLoopPoints((FSOUND_SAMPLE*) sptr, loopstart, loopend);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_Unlock
* Signature: (JILorg/lwjgl/fmod_instance/FSoundSampleLock;)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1Unlock(JNIEnv * env, jclass clazz, jlong sptr, jobject ptr1, jobject ptr2, jint len1, jint len2) { 
  return fmod_instance->FSOUND_Sample_Unlock((FSOUND_SAMPLE *) sptr, (*env)->GetDirectBufferAddress(env, ptr1), (*env)->GetDirectBufferAddress(env, ptr2), len1, len2);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Sample_Upload
* Signature: (JLjava/nio/ByteBuffer;II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Sample_1Upload(JNIEnv * env, jclass clazz, jlong sptr, jobject data, jint dataOffset, jint mode) { 
	void * nData = dataOffset + (char *) (*env)->GetDirectBufferAddress(env, data);
	return fmod_instance->FSOUND_Sample_Upload((FSOUND_SAMPLE *) sptr, nData, mode);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_PlaySound
* Signature: (IJ)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1PlaySound(JNIEnv * env, jclass clazz, jint channel, jlong sptr) {
  return fmod_instance->FSOUND_PlaySound(channel, (FSOUND_SAMPLE*) sptr);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_PlaySoundEx
* Signature: (IJJZ)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1PlaySoundEx(JNIEnv * env, jclass clazz, jint channel, jlong sptr, jobject dsp, jboolean startpaused) {
	FSOUND_DSPUNIT* nDsp = (FSOUND_DSPUNIT*) (*env)->GetDirectBufferAddress(env, dsp);
  return fmod_instance->FSOUND_PlaySoundEx(channel, (FSOUND_SAMPLE*) sptr, nDsp, startpaused);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_StopSound
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1StopSound(JNIEnv * env, jclass clazz, jint channel) {
  return fmod_instance->FSOUND_StopSound(channel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetFrequency
* Signature: (II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetFrequency(JNIEnv * env, jclass clazz, jint channel, jint freq) {
  return fmod_instance->FSOUND_SetFrequency(channel, freq);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetLevels
* Signature: (IIIIIII)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetLevels(JNIEnv * env, jclass clazz, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7) {
  //XBOX only - snowballs chance in hell we will get to support that!
  return false;
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetLoopMode
* Signature: (II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetLoopMode(JNIEnv * env, jclass clazz, jint channel, jint loopmode) {
  return fmod_instance->FSOUND_SetLoopMode(channel, loopmode);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetMute
* Signature: (IZ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetMute(JNIEnv * env, jclass clazz, jint channel, jboolean mute) {
  return fmod_instance->FSOUND_SetMute(channel, mute);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetPan
* Signature: (II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetPan(JNIEnv * env, jclass clazz, jint channel, jint pan) {
  return fmod_instance->FSOUND_SetPan(channel, pan);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetPaused
* Signature: (IZ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetPaused(JNIEnv * env, jclass clazz, jint channel, jboolean paused) {
  return fmod_instance->FSOUND_SetPaused(channel, paused);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetPriority
* Signature: (II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetPriority(JNIEnv * env, jclass clazz, jint channel, jint priority) {
  return fmod_instance->FSOUND_SetPriority(channel, priority);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetReserved
* Signature: (IZ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetReserved(JNIEnv * env, jclass clazz, jint channel, jboolean reserved) {
  return fmod_instance->FSOUND_SetReserved(channel, reserved);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetSurround
* Signature: (IZ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetSurround(JNIEnv * env, jclass clazz, jint channel, jboolean surround) {
  return fmod_instance->FSOUND_SetSurround(channel, surround);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetVolume
* Signature: (II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetVolume(JNIEnv * env, jclass clazz, jint channel, jint vol) {
  return fmod_instance->FSOUND_SetVolume(channel, vol);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetVolumeAbsolute
* Signature: (II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetVolumeAbsolute(JNIEnv * env, jclass clazz, jint channel, jint vol) {
  return fmod_instance->FSOUND_SetVolumeAbsolute(channel, vol);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetVolume
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetVolume(JNIEnv * env, jclass clazz, jint channel) {
  return fmod_instance->FSOUND_GetVolume(channel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetAmplitude
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetAmplitude(JNIEnv * env, jclass clazz, jint channel) {
  return fmod_instance->FSOUND_GetAmplitude(channel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_3D_SetAttributes
* Signature: (ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_13D_1SetAttributes(JNIEnv * env, jclass clazz, jint channel, jobject pos, jint posOffset, jobject vel, jint velOffset) {
	float* nPos = posOffset + (float *) (*env)->GetDirectBufferAddress(env, pos);
	float* nVel = velOffset + (float *) (*env)->GetDirectBufferAddress(env, vel);
  return fmod_instance->FSOUND_3D_SetAttributes(channel, nPos, nVel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_3D_SetMinMaxDistance
* Signature: (III)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_13D_1SetMinMaxDistance(JNIEnv * env, jclass clazz, jint channel, jfloat min, jfloat max) {
  return fmod_instance->FSOUND_3D_SetMinMaxDistance(channel, min, max);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_SetCurrentPosition
* Signature: (II)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1SetCurrentPosition(JNIEnv * env, jclass clazz, jint channel, jint offset) {
  return fmod_instance->FSOUND_SetCurrentPosition(channel, offset);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetCurrentPosition
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetCurrentPosition(JNIEnv * env, jclass clazz, jint channel) {
  return fmod_instance->FSOUND_GetCurrentPosition(channel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_GetCurrentSample
* Signature: (I)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1GetCurrentSample(JNIEnv * env, jclass clazz, jint channel) { 
  return (jlong) fmod_instance->FSOUND_GetCurrentSample(channel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_GetCurrentLevels
* Signature: (ILjava/nio/FloatBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1GetCurrentLevels(JNIEnv * env, jclass clazz, jint channel , jobject l_rBuffer, jint l_rBufferOffset) {
	float* nL_R = l_rBufferOffset + (float *) (*env)->GetDirectBufferAddress(env, l_rBuffer);
  return fmod_instance->FSOUND_GetCurrentLevels(channel, &nL_R[0], &nL_R[1]);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetFrequency
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetFrequency(JNIEnv * env, jclass clazz, jint channel) {
  return fmod_instance->FSOUND_GetFrequency(channel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetLoopMode
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetLoopMode(JNIEnv * env, jclass clazz, jint channel) {
  return fmod_instance->FSOUND_GetLoopMode(channel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetMixer
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetMixer(JNIEnv * env, jclass clazz) {
  return fmod_instance->FSOUND_GetMixer();
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetMute
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetMute(JNIEnv * env, jclass clazz, jint channel) {
  return fmod_instance->FSOUND_GetMute(channel);  
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetNumSubChannels
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetNumSubChannels(JNIEnv * env, jclass clazz, jint channel) {
  return fmod_instance->FSOUND_GetNumSubChannels(channel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetPan
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetPan(JNIEnv * env, jclass clazz, jint channel) {
  return fmod_instance->FSOUND_GetPan(channel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetPaused
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetPaused(JNIEnv * env, jclass clazz, jint channel) {
  return fmod_instance->FSOUND_GetPaused(channel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetPriority
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetPriority(JNIEnv * env, jclass clazz, jint channel) {
  return fmod_instance->FSOUND_GetPriority(channel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetReserved
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetReserved(JNIEnv * env, jclass clazz, jint channel) {
  return fmod_instance->FSOUND_GetReserved(channel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetSubChannel
* Signature: (II)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetSubChannel(JNIEnv * env, jclass clazz, jint channel, jint subchannel) {
  return fmod_instance->FSOUND_GetSubChannel(channel, subchannel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_GetSurround
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1GetSurround(JNIEnv * env, jclass clazz, jint channel) {
  return fmod_instance->FSOUND_GetSurround(channel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_IsPlaying
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1IsPlaying(JNIEnv * env, jclass clazz, jint channel) {
  return fmod_instance->FSOUND_IsPlaying(channel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_3D_GetAttributes
* Signature: (ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_13D_1GetAttributes(JNIEnv * env, jclass clazz, jint channel, jobject pos, jint posOffset, jobject vel, jint velOffset) {
	float* nPos = posOffset + (float *) (*env)->GetDirectBufferAddress(env, pos);
	float* nVel = velOffset + (float *) (*env)->GetDirectBufferAddress(env, vel);
  return fmod_instance->FSOUND_3D_GetAttributes(channel, nPos, nVel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_3D_GetMinMaxDistance
* Signature: (ILjava/nio/FloatBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_13D_1GetMinMaxDistance(JNIEnv * env, jclass clazz, jint channel, jobject minmax, jint minmaxOffset) {
	float* nMinMax = minmaxOffset + (float *) (*env)->GetDirectBufferAddress(env, minmax);
  return fmod_instance->FSOUND_3D_GetMinMaxDistance(channel, &nMinMax[0], &nMinMax[1]);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_3D_Listener_GetAttributes
* Signature: (Ljava/nio/FloatBuffer;ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;ILjava/nio/FloatBuffer;I)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_13D_1Listener_1GetAttributes(JNIEnv * env, jclass clazz, jobject pos, jint posOffset, jobject vel, jint velOffset, jobject fx, jint fxOffset, jobject fy, jint fyOffset, jobject fz, jint fzOffset, jobject tx, jint txOffset, jobject ty, jint tyOffset, jobject tz, jint tzOffset) {
	float* nPos 	= (pos != NULL) ? posOffset + (float *) (*env)->GetDirectBufferAddress(env, pos) 	: NULL;
	float* nVel 	= (vel != NULL) 	? velOffset 	+ (float *) (*env)->GetDirectBufferAddress(env, vel) 		: NULL;
	float* nFx 		= (fx != NULL) 	? fxOffset 	+ (float *) (*env)->GetDirectBufferAddress(env, fx) 		: NULL;
	float* nFy 		= (fy != NULL) 	? fyOffset 	+ (float *) (*env)->GetDirectBufferAddress(env, fy) 		: NULL;
	float* nFz 		= (fz != NULL) 	? fzOffset 	+ (float *) (*env)->GetDirectBufferAddress(env, fz) 		: NULL;
	float* nTx 		= (tx != NULL) 	? txOffset 	+ (float *) (*env)->GetDirectBufferAddress(env, tx) 		: NULL;
	float* nTy 		= (ty != NULL) 	? tyOffset 	+ (float *) (*env)->GetDirectBufferAddress(env, ty) 		: NULL;
	float* nTz 		= (tz != NULL) 	? tzOffset 	+ (float *) (*env)->GetDirectBufferAddress(env, tz) 		: NULL;
	fmod_instance->FSOUND_3D_Listener_GetAttributes(nPos, nVel, nFx, nFy, nFz, nTx, nTy, nTz);	
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_3D_Listener_SetAttributes
* Signature: (Ljava/nio/FloatBuffer;ILjava/nio/FloatBuffer;IFFFFFF)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_13D_1Listener_1SetAttributes(JNIEnv * env, jclass clazz, jobject pos, jint posOffset, jobject vel, jint velOffset, jfloat fx, jfloat fy, jfloat fz, jfloat tx, jfloat ty, jfloat tz) {
	float* nPos = posOffset + (float *) (*env)->GetDirectBufferAddress(env, pos);
	float* nVel = velOffset + (float *) (*env)->GetDirectBufferAddress(env, vel);
  fmod_instance->FSOUND_3D_Listener_SetAttributes(nPos, nVel, fx, fy, fz, tx, ty, tz);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_3D_Listener_SetCurrent
* Signature: (II)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_13D_1Listener_1SetCurrent(JNIEnv * env, jclass clazz, jint current, jint numlisteners) {
  fmod_instance->FSOUND_3D_Listener_SetCurrent(current, numlisteners);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_3D_SetDistanceFactor
* Signature: (F)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_13D_1SetDistanceFactor(JNIEnv * env, jclass clazz, jfloat scale) {
  fmod_instance->FSOUND_3D_SetDistanceFactor(scale);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_3D_SetDopplerFactor
* Signature: (F)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_13D_1SetDopplerFactor(JNIEnv * env, jclass clazz, jfloat scale) {
  fmod_instance->FSOUND_3D_SetDopplerFactor(scale);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_3D_SetRolloffFactor
* Signature: (F)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_13D_1SetRolloffFactor(JNIEnv * env, jclass clazz, jfloat scale) {
  fmod_instance->FSOUND_3D_SetRolloffFactor(scale);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_Open
* Signature: (Ljava/nio/ByteBuffer;IIII)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1Open__Ljava_nio_ByteBuffer_2III(JNIEnv * env, jclass clazz, jobject data, jint mode, jint offset, jint length) {
  const char *streamData = offset + (char *) (*env)->GetDirectBufferAddress(env, data);
  return (jlong) fmod_instance->FSOUND_Stream_Open(streamData, mode, 0, length);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_Open
* Signature: (Ljava/lang/String;III)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1Open__Ljava_lang_String_2III(JNIEnv * env, jclass clazz, jstring name, jint mode, jint offset, jint length) {
  jlong result;
  char* filename = GetStringNativeChars(env, name);
  result = (jlong) fmod_instance->FSOUND_Stream_Open(filename, mode, offset, length);
  free(filename);
  return result;
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_Play
* Signature: (IJ)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1Play(JNIEnv * env, jclass clazz, jint channel, jlong handle) {
  return fmod_instance->FSOUND_Stream_Play(channel, (FSOUND_STREAM*) handle);
}

/*
 * Class:     org_lwjgl_fmod3_FSound
 * Method:    nFSOUND_Stream_PlayEx
 * Signature: (IJJZ)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1PlayEx(JNIEnv * env, jclass clazz, jint channel, jlong stream, jobject dsp, jboolean startpaused) {
	FSOUND_DSPUNIT* nDsp = (dsp != NULL) ? (FSOUND_DSPUNIT*) (*env)->GetDirectBufferAddress(env, dsp) : NULL;
  return fmod_instance->FSOUND_Stream_PlayEx(channel, (FSOUND_STREAM*) stream, nDsp, startpaused);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_Stop
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1Stop(JNIEnv * env, jclass clazz, jlong handle) {
  return fmod_instance->FSOUND_Stream_Stop((FSOUND_STREAM*) handle);
}


/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_Close
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1Close(JNIEnv * env, jclass clazz, jlong handle) {
  return fmod_instance->FSOUND_Stream_Close((FSOUND_STREAM*) handle);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_GetNumSubStreams
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1GetNumSubStreams(JNIEnv * env, jclass clazz, jlong handle) {
  return fmod_instance->FSOUND_Stream_GetNumSubStreams((FSOUND_STREAM*) handle);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_SetSubStream
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1SetSubStream(JNIEnv * env, jclass clazz, jlong handle, jint index) {
  return fmod_instance->FSOUND_Stream_SetSubStream((FSOUND_STREAM*) handle, index);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_AddSyncPoint
* Signature: (JILjava/lang/String;)J
*/
JNIEXPORT jobject JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1AddSyncPoint(JNIEnv * env, jclass clazz, jlong stream, jint pcmOffset, jstring name) {
	char * nName = GetStringNativeChars(env, name);
	FSOUND_SYNCPOINT * result = fmod_instance->FSOUND_Stream_AddSyncPoint((FSOUND_STREAM*) stream, pcmOffset, (void *) nName);
	free(nName);
	return safeNewBuffer(env, result, 0);;
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_Create
* Signature: (III)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1Create(JNIEnv * env, jclass clazz, jint p1, jint p2, jint p3) {
  //XXX
  throwFMODException(env, "missing implementation");
  return 0;
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_CreateDSP
* Signature: (JI)J
*/
JNIEXPORT jobject JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1CreateDSP(JNIEnv * env, jclass clazz, jlong stream, jint priority, jobject dspID) {
	long * nDspID = (long *) (*env)->GetDirectBufferAddress(env, dspID);
	FSOUND_DSPUNIT* dsp = fmod_instance->FSOUND_Stream_CreateDSP((FSOUND_STREAM*) stream, fsound_dspcallback, priority, nDspID);
	return safeNewBuffer(env, dsp, 0);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_DeleteSyncPoint
* Signature: (J)Z
*/
/* Commented out, conflicting declaration with the header */
/*JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1DeleteSyncPoint(JNIEnv * env, jclass clazz, jlong point) {
  return fmod_instance->FSOUND_Stream_DeleteSyncPoint((FSOUND_SYNCPOINT*) point);
}
*/
/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_FindTagField
* Signature: (JILjava/lang/String;Lorg/lwjgl/fmod_instance/FSoundTagField;)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1FindTagField(JNIEnv * env, jclass clazz, jlong stream, jint type, jstring name, jobject tagField) {
	char * nName = GetStringNativeChars(env, name);
	bool result = false;
	void* value;
	jint length;

	if(fmod_instance->FSOUND_Stream_FindTagField((FSOUND_STREAM*) stream, type, nName, &value, &length)) {

		// get set method and call it
		jclass cls = (*env)->GetObjectClass(env, tagField);
		jmethodID mid = (*env)->GetMethodID(env, cls, "set", "(Ljava/lang/String;Ljava/nio/ByteBuffer;I)V");
		(*env)->CallVoidMethod(env, tagField, mid, name, safeNewBuffer(env, value, length), type);
		result = true;
	}
	free(nName);
	return result;
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_GetLength
* Signature: (J)Z
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1GetLength(JNIEnv * env, jclass clazz, jlong stream) {
  return fmod_instance->FSOUND_Stream_GetLength((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_GetLengthMs
* Signature: (J)Z
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1GetLengthMs(JNIEnv * env, jclass clazz, jlong stream) {
  return fmod_instance->FSOUND_Stream_GetLengthMs((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_GetMode
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1GetMode(JNIEnv * env, jclass clazz, jlong stream) {
  return fmod_instance->FSOUND_Stream_GetMode((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_GetNumSyncPoints
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1GetNumSyncPoints(JNIEnv * env, jclass clazz, jlong stream) {
  return fmod_instance->FSOUND_Stream_GetNumSyncPoints((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_GetNumTagFields
* Signature: (JLjava/nio/IntBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1GetNumTagFields(JNIEnv * env, jclass clazz, jlong stream, jobject num, jint numOffset) {
		int* nNum = numOffset + (int*) (*env)->GetDirectBufferAddress(env, num);
		return fmod_instance->FSOUND_Stream_GetNumTagFields((FSOUND_STREAM*) stream, &nNum[0]);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_GetOpenState
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1GetOpenState(JNIEnv * env, jclass clazz, jlong stream) {
  return fmod_instance->FSOUND_Stream_GetOpenState((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_GetPosition
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1GetPosition(JNIEnv * env, jclass clazz, jlong stream) {
  return fmod_instance->FSOUND_Stream_GetPosition((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_GetSample
* Signature: (J)J
*/
JNIEXPORT jlong JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1GetSample(JNIEnv * env, jclass clazz, jlong stream) {
  return (jlong) fmod_instance->FSOUND_Stream_GetSample((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_GetSyncPoint
* Signature: (JI)J
*/
JNIEXPORT jobject JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1GetSyncPoint(JNIEnv * env, jclass clazz, jlong stream, jint index) {
	FSOUND_SYNCPOINT * point = fmod_instance->FSOUND_Stream_GetSyncPoint((FSOUND_STREAM*) stream, index);
	return safeNewBuffer(env, point, 0);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_GetSyncPointInfo
* Signature: (JLjava/nio/IntBuffer;I)Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1GetSyncPointInfo(JNIEnv * env, jclass clazz, jobject point, jobject pcmOffset, jint pcmOffsetPosition) {
	FSOUND_SYNCPOINT * nPoint = (FSOUND_SYNCPOINT *) (*env)->GetDirectBufferAddress(env, point);
	unsigned int * nPcmOffset = pcmOffsetPosition + (unsigned int *) (*env)->GetDirectBufferAddress(env, pcmOffset);
	char * result = fmod_instance->FSOUND_Stream_GetSyncPointInfo(nPoint, nPcmOffset);
	if(result != NULL) {
		return NewStringNative(env, result);
	}
	return NULL;
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_GetTagField
* Signature: (JILorg/lwjgl/fmod_instance/FSoundTagField;)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1GetTagField(JNIEnv * env, jclass clazz, jlong stream, jint num, jobject tagField) {
	jint type;
	char* name;
	void* value;
	jint length;

	if(fmod_instance->FSOUND_Stream_GetTagField((FSOUND_STREAM*) stream, num, &type, &name, &value, &length)) {
		// create string instance of name
		jstring nName = NewStringNative(env, name);

		// get set method and call it
		jclass cls = (*env)->GetObjectClass(env, tagField);
		jmethodID mid = (*env)->GetMethodID(env, cls, "set", "(Ljava/lang/String;Ljava/nio/ByteBuffer;I)V");
		(*env)->CallVoidMethod(env, tagField, mid, nName, safeNewBuffer(env, value, length), type);
		return true;
	}
	return false;
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_GetTime
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1GetTime(JNIEnv * env, jclass clazz, jlong stream) {
  return fmod_instance->FSOUND_Stream_GetTime((FSOUND_STREAM*) stream);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_Net_GetBufferProperties
* Signature: (Ljava/nio/IntBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1Net_1GetBufferProperties(JNIEnv * env, jclass clazz, jobject status, jint statusOffset) {
		int* nStatus = statusOffset + (int*) (*env)->GetDirectBufferAddress(env, status);
		return fmod_instance->FSOUND_Stream_Net_GetBufferProperties(&nStatus[0], &nStatus[1], &nStatus[2]);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_Stream_Net_GetBufferProperties
* Signature: ()Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1Stream_1Net_1GetLastServerStatus(JNIEnv * env, jclass clazz) {
  return NewStringNative(env, fmod_instance->FSOUND_Stream_Net_GetLastServerStatus());
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_Net_GetStatus
* Signature: (JLjava/nio/IntBuffer;I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1Net_1GetStatus(JNIEnv * env, jclass clazz, jlong stream, jobject status, jint statusOffset) {
		int* nStatus = statusOffset + (int*) (*env)->GetDirectBufferAddress(env, status);
		return fmod_instance->FSOUND_Stream_Net_GetStatus((FSOUND_STREAM*) stream, &nStatus[0], &nStatus[1], &nStatus[2], (unsigned int *) &nStatus[3]);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_Stream_Net_SetBufferProperties
* Signature: (III)Ljava/lang/String;
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1Stream_1Net_1SetBufferProperties(JNIEnv * env, jclass clazz, jint buffersize, jint prebuffer_percent, jint rebuffer_percent) {
		return fmod_instance->FSOUND_Stream_Net_SetBufferProperties(buffersize, prebuffer_percent, rebuffer_percent);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_Net_SetMetadataCallback
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1Net_1SetMetadataCallback(JNIEnv * env, jclass clazz, jlong stream) {
	return fmod_instance->FSOUND_Stream_Net_SetMetadataCallback((FSOUND_STREAM*) stream, fsound_metadata_callback, (void*) stream);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_Stream_Net_SetProxy
* Signature: (Ljava/lang/String;)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1Stream_1Net_1SetProxy(JNIEnv * env, jclass clazz, jstring proxy) {
  char * proxyString = GetStringNativeChars(env, proxy);
  jboolean result = fmod_instance->FSOUND_Stream_Net_SetProxy(proxyString);
  free(proxyString);
  return result;
}



/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_Stream_SetBufferSize
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1Stream_1SetBufferSize(JNIEnv * env, jclass clazz, jint ms) {
  return fmod_instance->FSOUND_Stream_SetBufferSize(ms);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_SetEndCallback
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1SetEndCallback(JNIEnv * env, jclass clazz, jlong stream) {
	return fmod_instance->FSOUND_Stream_SetEndCallback((FSOUND_STREAM*) stream, fsound_stream_endcallback, 0);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_SetLoopCount
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1SetLoopCount(JNIEnv * env, jclass clazz, jlong stream, jint count) {
  return fmod_instance->FSOUND_Stream_SetLoopCount((FSOUND_STREAM*) stream, count);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_SetLoopPoints
* Signature: (JII)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1SetLoopPoints(JNIEnv * env, jclass clazz, jlong stream, jint loopstartpcm, jint loopendpcm) {
  return fmod_instance->FSOUND_Stream_SetLoopPoints((FSOUND_STREAM*) stream, loopstartpcm, loopendpcm);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_SetMode
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1SetMode(JNIEnv * env, jclass clazz, jlong stream, jint mode) {
  return fmod_instance->FSOUND_Stream_SetMode((FSOUND_STREAM*) stream, mode);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_SetPosition
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1SetPosition(JNIEnv * env, jclass clazz, jlong stream, jint position) {
  return fmod_instance->FSOUND_Stream_SetPosition((FSOUND_STREAM*) stream, position);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_SetSubStreamSentence
* Signature: (JLjava/nio/IntBuffer;I)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1SetSubStreamSentence(JNIEnv * env, jclass clazz, jlong p1, jobject p2, jint p3) {
  //XXX
  throwFMODException(env, "missing implementation");
  return false;
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_Stream_SetSyncCallback
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1Stream_1SetSyncCallback(JNIEnv * env, jclass clazz, jlong stream) {
  return fmod_instance->FSOUND_Stream_SetSyncCallback((FSOUND_STREAM*) stream, fsound_stream_synccallback, 0);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Stream_SetTime
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Stream_1SetTime(JNIEnv * env, jclass clazz, jlong stream, jint ms) {
  return fmod_instance->FSOUND_Stream_SetTime((FSOUND_STREAM*) stream, ms);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_CD_Eject
* Signature: (C)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1CD_1OpenTray(JNIEnv * env, jclass clazz, jchar drive, jboolean open) {
#ifdef __APPLE__ && __MACH__
  return false;
#else
  return fmod_instance->FSOUND_CD_OpenTray(drive, open);
#endif
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_CD_GetNumTracks
* Signature: (C)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1CD_1GetNumTracks(JNIEnv * env, jclass clazz, jchar drive) {
  return fmod_instance->FSOUND_CD_GetNumTracks(drive);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_CD_GetPaused
* Signature: (C)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1CD_1GetPaused(JNIEnv * env, jclass clazz, jchar drive) {
  return fmod_instance->FSOUND_CD_GetPaused(drive);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_CD_GetTrack
* Signature: (C)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1CD_1GetTrack(JNIEnv * env, jclass clazz, jchar drive) {
  return fmod_instance->FSOUND_CD_GetTrack(drive);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_CD_GetTrackLength
* Signature: (CI)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1CD_1GetTrackLength(JNIEnv * env, jclass clazz, jchar drive, jint track) {
  return fmod_instance->FSOUND_CD_GetTrackLength(drive, track);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_CD_GetTrackTime
* Signature: (C)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1CD_1GetTrackTime(JNIEnv * env, jclass clazz, jchar drive) {
  return fmod_instance->FSOUND_CD_GetTrackTime(drive);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_CD_Play
* Signature: (CI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1CD_1Play(JNIEnv * env, jclass clazz, jchar drive, jint track) {
  return fmod_instance->FSOUND_CD_Play(drive, track);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_CD_SetPaused
* Signature: (CZ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1CD_1SetPaused(JNIEnv * env, jclass clazz, jchar drive, jboolean paused) {
  return fmod_instance->FSOUND_CD_SetPaused(drive, paused);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_CD_SetPlayMode
* Signature: (CI)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1CD_1SetPlayMode(JNIEnv * env, jclass clazz, jchar drive, jint mode) {
  fmod_instance->FSOUND_CD_SetPlayMode(drive, mode);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_CD_SetTrackTime
* Signature: (CI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1CD_1SetTrackTime(JNIEnv * env, jclass clazz, jchar drive, jint ms) {
  return fmod_instance->FSOUND_CD_SetTrackTime(drive, ms);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_CD_SetVolume
* Signature: (CI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1CD_1SetVolume(JNIEnv * env, jclass clazz, jchar drive, jint volume) {
  return fmod_instance->FSOUND_CD_SetVolume(drive, volume);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_CD_Stop
* Signature: (C)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1CD_1Stop(JNIEnv * env, jclass clazz, jchar drive) {
  return fmod_instance->FSOUND_CD_Stop(drive);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_DSP_ClearMixBuffer
* Signature: ()V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1DSP_1ClearMixBuffer(JNIEnv * env, jclass clazz) {
  fmod_instance->FSOUND_DSP_ClearMixBuffer();
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_DSP_Create
* Signature: (I)J
*/
/* Commented out, conflicting declaration with the header */
/*
JNIEXPORT jobject JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1DSP_1Create(JNIEnv * env, jclass clazz, jint priority, jobject dspID) {
	long * nDspID = (long *) (*env)->GetDirectBufferAddress(env, dspID);
  FSOUND_DSPUNIT* dsp = fmod_instance->FSOUND_DSP_Create(fsound_dspcallback, priority, nDspID);
	return safeNewBuffer(env, dsp, 0);
}
*/
/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_DSP_Free
* Signature: (J)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1DSP_1Free(JNIEnv * env, jclass clazz, jobject dsp) {
	FSOUND_DSPUNIT* nDsp = (FSOUND_DSPUNIT*) (*env)->GetDirectBufferAddress(env, dsp);
  fmod_instance->FSOUND_DSP_Free(nDsp);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_DSP_SetActive
* Signature: (JZ)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1DSP_1SetActive(JNIEnv * env, jclass clazz, jobject dsp, jboolean active) {
	FSOUND_DSPUNIT* nDsp = (FSOUND_DSPUNIT*) (*env)->GetDirectBufferAddress(env, dsp);
  fmod_instance->FSOUND_DSP_SetActive(nDsp, active);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_DSP_GetActive
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1DSP_1GetActive(JNIEnv * env, jclass clazz, jobject dsp) {
	FSOUND_DSPUNIT* nDsp = (FSOUND_DSPUNIT*) (*env)->GetDirectBufferAddress(env, dsp);
  return fmod_instance->FSOUND_DSP_GetActive(nDsp);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_DSP_GetBufferLength
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1DSP_1GetBufferLength(JNIEnv * env, jclass clazz) {
  return fmod_instance->FSOUND_DSP_GetBufferLength();
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_DSP_GetBufferLengthTotal
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1DSP_1GetBufferLengthTotal(JNIEnv * env, jclass clazz) {
  return fmod_instance->FSOUND_DSP_GetBufferLengthTotal();
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_DSP_SetPriority
* Signature: (JI)V
*/
JNIEXPORT void JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1DSP_1SetPriority(JNIEnv * env, jclass clazz, jobject dsp, jint priority) {
	FSOUND_DSPUNIT* nDsp = (FSOUND_DSPUNIT*) (*env)->GetDirectBufferAddress(env, dsp);
  fmod_instance->FSOUND_DSP_SetPriority(nDsp, priority);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_DSP_GetPriority
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1DSP_1GetPriority(JNIEnv * env, jclass clazz, jobject dsp) {
	FSOUND_DSPUNIT* nDsp = (FSOUND_DSPUNIT*) (*env)->GetDirectBufferAddress(env, dsp);
  return fmod_instance->FSOUND_DSP_GetPriority(nDsp);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_DSP_GetClearUnit
* Signature: ()J
*/
JNIEXPORT jobject JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1DSP_1GetClearUnit(JNIEnv * env, jclass clazz) {
	FSOUND_DSPUNIT* dsp = fmod_instance->FSOUND_DSP_GetClearUnit();
	return safeNewBuffer(env, dsp, 0);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_DSP_GetClipAndCopyUnit
* Signature: ()J
*/
JNIEXPORT jobject JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1DSP_1GetClipAndCopyUnit(JNIEnv * env, jclass clazz) {
	FSOUND_DSPUNIT* dsp = fmod_instance->FSOUND_DSP_GetClipAndCopyUnit();
	return safeNewBuffer(env, dsp, 0);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_DSP_GetMusicUnit
* Signature: ()J
*/
JNIEXPORT jobject JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1DSP_1GetMusicUnit(JNIEnv * env, jclass clazz) {
	FSOUND_DSPUNIT* dsp = fmod_instance->FSOUND_DSP_GetMusicUnit();
	return safeNewBuffer(env, dsp, 0);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_DSP_GetSFXUnit
* Signature: ()J
*/
JNIEXPORT jobject JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1DSP_1GetSFXUnit(JNIEnv * env, jclass clazz) {
	FSOUND_DSPUNIT* dsp = fmod_instance->FSOUND_DSP_GetSFXUnit();
	return safeNewBuffer(env, dsp, 0);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_DSP_GetFFTUnit
* Signature: ()J
*/
JNIEXPORT jobject JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1DSP_1GetFFTUnit(JNIEnv * env, jclass clazz) {
	FSOUND_DSPUNIT* dsp = fmod_instance->FSOUND_DSP_GetFFTUnit();
	return safeNewBuffer(env, dsp, 0);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_DSP_GetSpectrum
* Signature: ()Ljava/nio/FloatBuffer;
*/
JNIEXPORT jobject JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1DSP_1GetSpectrum(JNIEnv * env, jclass clazz) {
  return (*env)->NewDirectByteBuffer(env, fmod_instance->FSOUND_DSP_GetSpectrum(), (512 * sizeof(float)));
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_DSP_MixBuffers
* Signature: (Ljava/nio/ByteBuffer;ILjava/nio/ByteBuffer;IIIIII)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1DSP_1MixBuffers(JNIEnv * env, jclass clazz, jobject destBuffer, jint destBufferOffset, jobject srcBuffer, jint srcBufferOffset, jint len, jint freq, jint vol, jint pan, jint mode) {
	void * nDestBuffer = destBufferOffset + (char *) (*env)->GetDirectBufferAddress(env, destBuffer);
	void * nSrcBuffer = srcBufferOffset + (char *) (*env)->GetDirectBufferAddress(env, srcBuffer);
	return fmod_instance->FSOUND_DSP_MixBuffers(nDestBuffer, nSrcBuffer, len, freq, vol, pan, mode);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_FX_Disable
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1FX_1Disable(JNIEnv * env, jclass clazz, jint channel) {
  return fmod_instance->FSOUND_FX_Disable(channel);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_FX_Enable
* Signature: (II)I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1FX_1Enable(JNIEnv * env, jclass clazz, jint channel, jint fx) {
  return fmod_instance->FSOUND_FX_Enable(channel, fx);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_FX_SetChorus
* Signature: (IFFFFIFI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1FX_1SetChorus(JNIEnv * env, jclass clazz, jint fxid, jfloat WetDryMix, jfloat Depth, jfloat Feedback, jfloat Frequency, jint Waveform, jfloat Delay, jint Phase) {
  return fmod_instance->FSOUND_FX_SetChorus(fxid, WetDryMix, Depth, Feedback, Frequency, Waveform, Delay, Phase);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_FX_SetCompressor
* Signature: (IFFFFFF)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1FX_1SetCompressor(JNIEnv * env, jclass clazz, jint fxid, jfloat Gain, jfloat Attack, jfloat Release, jfloat Threshold, jfloat Ratio, jfloat Predelay) {
  return fmod_instance->FSOUND_FX_SetCompressor(fxid, Gain, Attack, Release, Threshold, Ratio, Predelay);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_FX_SetDistortion
* Signature: (IFFFFF)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1FX_1SetDistortion(JNIEnv * env, jclass clazz, jint fxid, jfloat Gain, jfloat Edge, jfloat PostEQCenterFrequency, jfloat PostEQBandwidth, jfloat PreLowpassCutoff) {
  return fmod_instance->FSOUND_FX_SetDistortion(fxid, Gain, Edge, PostEQCenterFrequency, PostEQBandwidth, PreLowpassCutoff);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_FX_SetEcho
* Signature: (IFFFFI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1FX_1SetEcho(JNIEnv * env, jclass clazz, jint fxid, jfloat WetDryMix, jfloat Feedback, jfloat LeftDelay, jfloat RightDelay, jint PanDelay) {
  return fmod_instance->FSOUND_FX_SetEcho(fxid, WetDryMix, Feedback, LeftDelay, RightDelay, PanDelay);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_FX_SetFlanger
* Signature: (IFFFFIFI)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1FX_1SetFlanger(JNIEnv * env, jclass clazz, jint fxid, jfloat WetDryMix, jfloat Depth, jfloat Feedback, jfloat Frequency, jint Waveform, jfloat Delay, jint Phase) {
  return fmod_instance->FSOUND_FX_SetFlanger(fxid, WetDryMix, Depth, Feedback, Frequency, Waveform, Delay, Phase);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_FX_SetGargle
* Signature: (III)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1FX_1SetGargle(JNIEnv * env, jclass clazz, jint fxid, jint RateHz, jint WaveShape) {
  return fmod_instance->FSOUND_FX_SetGargle(fxid, RateHz, WaveShape);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_FX_SetI3DL2Reverb
* Signature: (IIIFFFIFIFFFF)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1FX_1SetI3DL2Reverb(JNIEnv * env, jclass clazz, 
  jint fxid, jint Room, jint RoomHF, jfloat RoomRolloffFactor, jfloat DecayTime, 
  jfloat DecayHFRation, jint Reflections, jfloat ReflectionsDelay, jint Reverb, 
  jfloat ReverbDelay, jfloat Diffusion, jfloat Density, jfloat HFReference) {
  return fmod_instance->FSOUND_FX_SetI3DL2Reverb(
  fxid, Room, RoomHF, RoomRolloffFactor, DecayTime, 
  DecayHFRation, Reflections, ReflectionsDelay, Reverb, 
  ReverbDelay, Diffusion, Density, HFReference);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_FX_SetParamEQ
* Signature: (IFFF)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1FX_1SetParamEQ(JNIEnv * env, jclass clazz, jint fxid, jfloat Center, jfloat Bandwidth, jfloat Gain) {
  return fmod_instance->FSOUND_FX_SetParamEQ(fxid, Center, Bandwidth, Gain);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_FX_SetWavesReverb
* Signature: (IFFFF)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1FX_1SetWavesReverb(JNIEnv * env, jclass clazz, jint fxid, jfloat InGain, jfloat ReverbMix, jfloat ReverbTime, jfloat HighFreqRTRatio) {
  return fmod_instance->FSOUND_FX_SetWavesReverb(fxid, InGain, ReverbMix, ReverbTime, HighFreqRTRatio);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_Record_GetDriver
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1Record_1GetDriver(JNIEnv * env, jclass clazz) {
  return fmod_instance->FSOUND_Record_GetDriver();
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_Record_GetDriverName
* Signature: (I)Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1Record_1GetDriverName(JNIEnv * env, jclass clazz, jint driver) {
  return NewStringNative(env, (const char *)fmod_instance->FSOUND_Record_GetDriverName(driver));
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_Record_GetNumDrivers
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1Record_1GetNumDrivers(JNIEnv * env, jclass clazz) {
  return fmod_instance->FSOUND_Record_GetNumDrivers();
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_Record_GetPosition
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1Record_1GetPosition(JNIEnv * env, jclass clazz) {
  return fmod_instance->FSOUND_Record_GetPosition();
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_Record_SetDriver
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1Record_1SetDriver(JNIEnv * env, jclass clazz, jint outputtype) {
  return fmod_instance->FSOUND_Record_SetDriver(outputtype);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Record_StartSample
* Signature: (JZ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Record_1StartSample(JNIEnv * env, jclass clazz, jlong sample, jboolean loop) {
  return fmod_instance->FSOUND_Record_StartSample((FSOUND_SAMPLE *) sample, loop);
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    FSOUND_Record_Stop
* Signature: ()Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_FSOUND_1Record_1Stop(JNIEnv * env, jclass clazz) {
  return fmod_instance->FSOUND_Record_Stop();
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Reverb_SetProperties
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Reverb_1SetProperties(JNIEnv * env, jclass clazz, jlong prop) {
	throwFMODException(env, "missing implementation");
  //return fmod_instance->FSOUND_Reverb_SetProperties((FSOUND_REVERB_PROPERTIES*) prop);
	return false;
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Reverb_GetProperties
* Signature: (J)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Reverb_1GetProperties(JNIEnv * env, jclass clazz, jlong prop) {
	throwFMODException(env, "missing implementation");
  //return fmod_instance->FSOUND_Reverb_GetProperties((FSOUND_REVERB_PROPERTIES*) prop);
	return false;
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Reverb_SetChannelProperties
* Signature: (IJ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Reverb_1SetChannelProperties(JNIEnv * env, jclass clazz, jint channel, jlong prop) {
	throwFMODException(env, "missing implementation");
  //return fmod_instance->FSOUND_Reverb_SetChannelProperties(channel, (FSOUND_REVERB_CHANNELPROPERTIES*) prop);
	return false;
}

/*
* Class:     org_lwjgl_fmod3_FSound
* Method:    nFSOUND_Reverb_GetChannelProperties
* Signature: (IJ)Z
*/
JNIEXPORT jboolean JNICALL Java_org_lwjgl_fmod3_FSound_nFSOUND_1Reverb_1GetChannelProperties(JNIEnv * env, jclass clazz, jint channel, jlong prop) {
	throwFMODException(env, "missing implementation");
  //return fmod_instance->FSOUND_Reverb_GetChannelProperties(channel, (FSOUND_REVERB_CHANNELPROPERTIES*) prop);
	return false;
}

/**
 * This attaches the mixer thread as a daemon thread, and sets its
 * priority to max value
 */
void attachStreamThread() {
  jclass threadClass;
  jmethodID currentThread;
  jobject myThread;
  jfieldID highPriority;
  jint highPriorityValue;
  jmethodID priority;
  JavaVM* jvm = getJVM();
  (*jvm)->AttachCurrentThreadAsDaemon(jvm, (void*)&stream_jnienv, NULL);
  
  // set to high priority
  // ==============================
  // get current thread
  threadClass = (*stream_jnienv)->FindClass(stream_jnienv, "java/lang/Thread");
  currentThread = (*stream_jnienv)->GetStaticMethodID(stream_jnienv, threadClass, "currentThread", "()Ljava/lang/Thread;");
  myThread = (*stream_jnienv)->CallStaticObjectMethod(stream_jnienv, threadClass, currentThread);
  
  // get value of high priority
  highPriority = (*stream_jnienv)->GetStaticFieldID(stream_jnienv, threadClass, "MAX_PRIORITY", "I");
  highPriorityValue = (*stream_jnienv)->GetStaticIntField(stream_jnienv, threadClass, highPriority);
  
  // call set priority
  priority = (*stream_jnienv)->GetMethodID(stream_jnienv, threadClass, "setPriority", "(I)V");
  (*stream_jnienv)->CallVoidMethod(stream_jnienv, myThread, priority, highPriorityValue);
  // ------------------------------
}

// FSound callbacks
// =======================================
void * F_CALLBACKAPI fsound_dspcallback(void *originalbuffer, void *newbuffer, int length, void *userdata) {
	int size;
	jobject origBuffer;
	jobject newBuffer;
	jobject resultBuffer;
  
  if (mixer_jnienv == NULL) { attachMixerThread(); }
	size = length * fsound_dsp_buffer_size;
	origBuffer = (*mixer_jnienv)->NewDirectByteBuffer(mixer_jnienv, originalbuffer, size);
	newBuffer = (*mixer_jnienv)->NewDirectByteBuffer(mixer_jnienv, newbuffer, size);
	resultBuffer = (*mixer_jnienv)->CallStaticObjectMethod(mixer_jnienv, fsound, sound_dspcallback, (jlong) *((long *)userdata), origBuffer, newBuffer, (jint) length);
	return (*mixer_jnienv)->GetDirectBufferAddress(mixer_jnienv, resultBuffer);
}

signed char F_CALLBACKAPI fsound_stream_endcallback(FSOUND_STREAM *stream, void *buff, int len, void *param) {
  if (stream_jnienv == NULL) { attachStreamThread(); }
	(*stream_jnienv)->CallStaticVoidMethod(stream_jnienv, fsound, sound_stream_endcallback, (jlong) stream);
	return true;
}

signed char F_CALLBACKAPI fsound_stream_synccallback(FSOUND_STREAM *stream, void *buff, int len, void *param) {
  int length;
  if (stream_jnienv == NULL) { attachStreamThread(); }
	length = strlen((const char *) buff);
	(*stream_jnienv)->CallStaticVoidMethod(stream_jnienv, fsound, sound_stream_synccallback, (jlong) stream, safeNewBuffer(stream_jnienv, buff, length), (jint) len);
	return true;
}

signed char F_CALLBACKAPI fsound_stream_callback(FSOUND_STREAM *stream, void *buff, int len, void *param) {
  if (stream_jnienv == NULL) { attachStreamThread(); }
	(*stream_jnienv)->CallStaticVoidMethod(stream_jnienv, fsound, sound_stream_callback, (jlong) stream, safeNewBuffer(stream_jnienv, buff, len), (jint) len);
	return true;
}

signed char F_CALLBACKAPI fsound_metadata_callback(char *name, char *value, void *userdata) {
	if (stream_jnienv == NULL) { attachStreamThread(); }
	(*stream_jnienv)->CallStaticVoidMethod(stream_jnienv, fsound, sound_metadata_callback, (jlong) userdata, safeNewBuffer(stream_jnienv, name, strlen(name)), safeNewBuffer(stream_jnienv, value, strlen(value)));
	return true;
}
