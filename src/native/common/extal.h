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

#ifndef _AL_TEST_H
#define _AL_TEST_H

#ifdef _WIN32
#include <windows.h>
#endif
#include <AL/altypes.h>
#include <AL/alctypes.h>

#ifdef __cplusplus
extern "C" {
#endif

#ifdef _WIN32
 #ifdef _OPENAL32LIB
  #define ALCAPI __declspec(dllexport)
 #else
  #define ALCAPI __declspec(dllimport)
 #endif

 typedef struct ALCdevice_struct ALCdevice;
 typedef struct ALCcontext_struct ALCcontext;

 #define ALCAPIENTRY __cdecl
#else
 #ifdef TARGET_OS_MAC
  #if TARGET_OS_MAC
 
   #pragma export on
  #endif
 #endif
 #define ALCAPI
 #define ALCAPIENTRY

/** ALC boolean type. */
typedef char ALCboolean;

/** ALC 8bit signed byte. */
typedef char ALCbyte;

/** ALC 8bit unsigned byte. */
typedef unsigned char ALCubyte;

/** ALC 16bit signed short integer type. */
typedef short ALCshort;

/** ALC 16bit unsigned short integer type. */
typedef unsigned short ALCushort;

/** ALC 32bit unsigned integer type. */
typedef unsigned ALCuint;

/** ALC 32bit signed integer type. */
typedef int ALCint;

/** ALC 32bit floating point type. */
typedef float ALCfloat;

/** ALC 64bit double point type. */
typedef double ALCdouble;

/** ALC 32bit type. */
typedef unsigned int ALCsizei;

/** ALC void type */
typedef void ALCvoid;

#endif

#ifdef _WIN32
 #ifdef _OPENAL32LIB
  #define ALAPI __declspec(dllexport)
 #else
  #define ALAPI __declspec(dllimport)
 #endif
 #define ALAPIENTRY __cdecl
 #define AL_CALLBACK
#else
 #ifdef TARGET_OS_MAC
  #if TARGET_OS_MAC
   #pragma export on
  #endif
 #endif
 #define ALAPI
 #define ALAPIENTRY
 #define AL_CALLBACK
#endif

#ifdef _WIN32
DEFINE_GUID(DSPROPSETID_EAX20_ListenerProperties, 
    0x306a6a8, 
    0xb224, 
    0x11d2, 
    0x99, 0xe5, 0x0, 0x0, 0xe8, 0xd8, 0xc7, 0x22);

DEFINE_GUID(DSPROPSETID_EAX20_BufferProperties, 
    0x306a6a7, 
    0xb224, 
    0x11d2, 
    0x99, 0xe5, 0x0, 0x0, 0xe8, 0xd8, 0xc7, 0x22);
#endif

#define INITGUID
#define OPENAL

int InitializeOpenAL(JNIEnv *env, jobjectArray oalPaths);
void DeInitializeOpenAL();

//alc
typedef ALCubyte*   (ALCAPIENTRY *alcGetStringPROC)(ALCdevice *device,ALCenum param);
typedef ALCvoid     (ALCAPIENTRY *alcGetIntegervPROC)(ALCdevice *device,ALCenum param,ALCsizei size,ALCint *data);
typedef ALCdevice*  (ALCAPIENTRY *alcOpenDevicePROC)(ALCubyte *deviceName);
typedef ALCvoid     (ALCAPIENTRY *alcCloseDevicePROC)(ALCdevice *device);
typedef ALCcontext* (ALCAPIENTRY *alcCreateContextPROC)(ALCdevice *device,ALCint *attrList);
typedef ALCboolean  (ALCAPIENTRY *alcMakeContextCurrentPROC)(ALCcontext *context);
typedef ALCvoid	    (ALCAPIENTRY *alcProcessContextPROC)(ALCcontext *context);
typedef ALCcontext* (ALCAPIENTRY *alcGetCurrentContextPROC)(ALCvoid);
typedef ALCdevice*  (ALCAPIENTRY *alcGetContextsDevicePROC)(ALCcontext *context);
typedef ALCvoid	    (ALCAPIENTRY *alcSuspendContextPROC)(ALCcontext *context);
typedef ALCvoid     (ALCAPIENTRY *alcDestroyContextPROC)(ALCcontext *context);
typedef ALCenum	    (ALCAPIENTRY *alcGetErrorPROC)(ALCdevice *device);
typedef ALCboolean  (ALCAPIENTRY *alcIsExtensionPresentPROC)(ALCdevice *device,ALCubyte *extName);
typedef ALCvoid*    (ALCAPIENTRY *alcGetProcAddressPROC)(ALCdevice *device,ALCubyte *funcName);
typedef ALCenum	    (ALCAPIENTRY *alcGetEnumValuePROC)(ALCdevice *device,ALCubyte *enumName);

//al
typedef ALvoid	    (ALAPIENTRY *alEnablePROC)( ALenum capability );
typedef ALvoid	    (ALAPIENTRY *alDisablePROC)( ALenum capability ); 
typedef ALboolean   (ALAPIENTRY *alIsEnabledPROC)( ALenum capability ); 
typedef ALvoid	    (ALAPIENTRY *alHintPROC)( ALenum target, ALenum mode );
typedef ALboolean   (ALAPIENTRY *alGetBooleanPROC)( ALenum param );
typedef ALint		(ALAPIENTRY *alGetIntegerPROC)( ALenum param );
typedef ALfloat	    (ALAPIENTRY *alGetFloatPROC)( ALenum param );
typedef ALdouble	(ALAPIENTRY *alGetDoublePROC)( ALenum param );
typedef ALvoid	    (ALAPIENTRY *alGetBooleanvPROC)( ALenum param, ALboolean* data );
typedef ALvoid	    (ALAPIENTRY *alGetIntegervPROC)( ALenum param, ALint* data );
typedef ALvoid	    (ALAPIENTRY *alGetFloatvPROC)( ALenum param, ALfloat* data );
typedef ALvoid	    (ALAPIENTRY *alGetDoublevPROC)( ALenum param, ALdouble* data );
typedef ALubyte*	(ALAPIENTRY *alGetStringPROC)( ALenum param );
typedef ALenum	    (ALAPIENTRY *alGetErrorPROC)( ALvoid );
typedef ALboolean   (ALAPIENTRY *alIsExtensionPresentPROC)( ALubyte* fname );
typedef ALvoid*	    (ALAPIENTRY *alGetProcAddressPROC)( ALubyte* fname );
typedef ALenum	    (ALAPIENTRY *alGetEnumValuePROC)( ALubyte* ename );
typedef ALvoid	    (ALAPIENTRY *alListeneriPROC)( ALenum param, ALint value );
typedef ALvoid	    (ALAPIENTRY *alListenerfPROC)( ALenum param, ALfloat value );
typedef ALvoid	    (ALAPIENTRY *alListener3fPROC)( ALenum param, ALfloat v1, ALfloat v2, ALfloat v3 ); 
typedef ALvoid	    (ALAPIENTRY *alListenerfvPROC)( ALenum param, ALfloat* values ); 
typedef ALvoid	    (ALAPIENTRY *alGetListeneriPROC)( ALenum param, ALint* value );
typedef ALvoid	    (ALAPIENTRY *alGetListenerfPROC)( ALenum param, ALfloat* value );
typedef ALvoid	    (ALAPIENTRY *alGetListener3fPROC)( ALenum param, ALfloat* v1, ALfloat* v2, ALfloat* v3 ); 
typedef ALvoid	    (ALAPIENTRY *alGetListenerfvPROC)( ALenum param, ALfloat* values ); 
typedef ALvoid	    (ALAPIENTRY *alGenSourcesPROC)( ALsizei n, ALuint* sources ); 
typedef ALvoid	    (ALAPIENTRY *alDeleteSourcesPROC)( ALsizei n, ALuint* sources );
typedef ALboolean   (ALAPIENTRY *alIsSourcePROC)( ALuint id ); 
typedef ALvoid	    (ALAPIENTRY *alSourceiPROC)( ALuint source, ALenum param, ALint value ); 
typedef ALvoid	    (ALAPIENTRY *alSourcefPROC)( ALuint source, ALenum param, ALfloat value ); 
typedef ALvoid	    (ALAPIENTRY *alSource3fPROC)( ALuint source, ALenum param, ALfloat v1, ALfloat v2, ALfloat v3 );
typedef ALvoid	    (ALAPIENTRY *alSourcefvPROC)( ALuint source, ALenum param, ALfloat* values ); 
typedef ALvoid	    (ALAPIENTRY *alGetSourceiPROC)( ALuint source,  ALenum param, ALint* value );
typedef ALvoid	    (ALAPIENTRY *alGetSourcefPROC)( ALuint source,  ALenum param, ALfloat* value );
typedef ALvoid	    (ALAPIENTRY *alGetSource3fPROC)( ALuint source,  ALenum param, ALfloat* v1, ALfloat* v2, ALfloat* v3 );
typedef ALvoid	    (ALAPIENTRY *alGetSourcefvPROC)( ALuint source, ALenum param, ALfloat* values );
typedef ALvoid	    (ALAPIENTRY *alSourcePlayvPROC)( ALsizei n, ALuint *sources );
typedef ALvoid	    (ALAPIENTRY *alSourcePausevPROC)( ALsizei n, ALuint *sources );
typedef ALvoid	    (ALAPIENTRY *alSourceStopvPROC)( ALsizei n, ALuint *sources );
typedef ALvoid	    (ALAPIENTRY *alSourceRewindvPROC)(ALsizei n,ALuint *sources);
typedef ALvoid	    (ALAPIENTRY *alSourcePlayPROC)( ALuint source );
typedef ALvoid	    (ALAPIENTRY *alSourcePausePROC)( ALuint source );
typedef ALvoid	    (ALAPIENTRY *alSourceStopPROC)( ALuint source );
typedef ALvoid	    (ALAPIENTRY *alSourceRewindPROC)( ALuint source );
typedef ALvoid 	    (ALAPIENTRY *alGenBuffersPROC)( ALsizei n, ALuint* buffers );
typedef ALvoid	    (ALAPIENTRY *alDeleteBuffersPROC)( ALsizei n, ALuint* buffers );
typedef ALboolean   (ALAPIENTRY *alIsBufferPROC)( ALuint buffer );
typedef ALvoid	    (ALAPIENTRY *alBufferDataPROC)( ALuint   buffer,
										 ALenum   format,
										 ALvoid*  data,
										 ALsizei  size,
										 ALsizei  freq );
typedef ALvoid	    (ALAPIENTRY *alGetBufferiPROC)( ALuint buffer, ALenum param, ALint*   value );
typedef ALvoid	    (ALAPIENTRY *alGetBufferfPROC)( ALuint buffer, ALenum param, ALfloat* value );
typedef ALvoid	    (ALAPIENTRY *alSourceQueueBuffersPROC)( ALuint source, ALsizei n, ALuint* buffers );
typedef ALvoid	    (ALAPIENTRY *alSourceUnqueueBuffersPROC)( ALuint source, ALsizei n, ALuint* buffers );
typedef ALvoid	    (ALAPIENTRY *alDistanceModelPROC)( ALenum value );
typedef ALvoid	    (ALAPIENTRY *alDopplerFactorPROC)( ALfloat value );
typedef ALvoid	    (ALAPIENTRY *alDopplerVelocityPROC)( ALfloat value );

extern alcGetStringPROC alcGetString;
extern alcGetIntegervPROC alcGetIntegerv;
extern alcOpenDevicePROC alcOpenDevice;
extern alcCloseDevicePROC alcCloseDevice;
extern alcCreateContextPROC alcCreateContext;
extern alcMakeContextCurrentPROC alcMakeContextCurrent;
extern alcProcessContextPROC alcProcessContext;
extern alcGetCurrentContextPROC alcGetCurrentContext;
extern alcGetContextsDevicePROC alcGetContextsDevice;
extern alcSuspendContextPROC alcSuspendContext;
extern alcDestroyContextPROC alcDestroyContext;
extern alcGetErrorPROC alcGetError;
extern alcIsExtensionPresentPROC alcIsExtensionPresent;
extern alcGetProcAddressPROC alcGetProcAddress;
extern alcGetEnumValuePROC alcGetEnumValue;

extern alEnablePROC alEnable;
extern alDisablePROC alDisable;
extern alIsEnabledPROC alIsEnabled;
extern alHintPROC alHint;
extern alGetBooleanPROC alGetBoolean;
extern alGetIntegerPROC alGetInteger;
extern alGetFloatPROC alGetFloat;
extern alGetDoublePROC alGetDouble;
extern alGetBooleanvPROC alGetBooleanv;
extern alGetIntegervPROC alGetIntegerv;
extern alGetFloatvPROC alGetFloatv;
extern alGetDoublevPROC alGetDoublev;
extern alGetStringPROC alGetString;
extern alGetErrorPROC alGetError;
extern alIsExtensionPresentPROC alIsExtensionPresent;
extern alGetProcAddressPROC alGetProcAddress;
extern alGetEnumValuePROC alGetEnumValue;
extern alListeneriPROC alListeneri;
extern alListenerfPROC alListenerf;
extern alListener3fPROC alListener3f;
extern alListenerfvPROC alListenerfv; 
extern alGetListeneriPROC alGetListeneri;
extern alGetListenerfPROC alGetListenerf;
extern alGetListener3fPROC alGetListener3f;
extern alGetListenerfvPROC alGetListenerfv;
extern alGenSourcesPROC alGenSources;
extern alDeleteSourcesPROC alDeleteSources;
extern alIsSourcePROC alIsSource;
extern alSourceiPROC alSourcei;
extern alSourcefPROC alSourcef;
extern alSource3fPROC alSource3f;
extern alSourcefvPROC alSourcefv;
extern alGetSourceiPROC alGetSourcei;
extern alGetSourcefPROC alGetSourcef;
extern alGetSource3fPROC alGetSource3f;
extern alGetSourcefvPROC alGetSourcefv;
extern alSourcePlayvPROC alSourcePlayv;
extern alSourcePausevPROC alSourcePausev;
extern alSourceStopvPROC alSourceStopv;
extern alSourceRewindvPROC alSourceRewindv;
extern alSourcePlayPROC alSourcePlay;
extern alSourcePausePROC alSourcePause;
extern alSourceStopPROC alSourceStop;
extern alSourceRewindPROC alSourceRewind;
extern alGenBuffersPROC alGenBuffers;
extern alDeleteBuffersPROC alDeleteBuffers;
extern alIsBufferPROC alIsBuffer;
extern alBufferDataPROC alBufferData;
extern alGetBufferiPROC alGetBufferi;
extern alGetBufferfPROC alGetBufferf;
extern alSourceQueueBuffersPROC alSourceQueueBuffers;
extern alSourceUnqueueBuffersPROC alSourceUnqueueBuffers;
extern alDistanceModelPROC alDistanceModel;
extern alDopplerFactorPROC alDopplerFactor;
extern alDopplerVelocityPROC alDopplerVelocity;

#ifdef _WIN32
typedef ALenum (*EAXSet)(const GUID*, ALuint, ALuint, ALvoid*, ALuint);
typedef ALenum (*EAXGet)(const GUID*, ALuint, ALuint, ALvoid*, ALuint);

extern EAXSet  eaxSet;
extern EAXGet  eaxGet;
#endif

#ifdef __cplusplus
}
#endif

#endif
