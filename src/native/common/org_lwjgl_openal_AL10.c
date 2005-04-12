/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extal.h"

typedef ALvoid (ALAPIENTRY *alDopplerVelocityPROC) (ALfloat value);
typedef ALvoid (ALAPIENTRY *alDopplerFactorPROC) (ALfloat value);
typedef ALvoid (ALAPIENTRY *alDistanceModelPROC) (ALenum value);
typedef ALvoid (ALAPIENTRY *alSourceUnqueueBuffersPROC) (ALuint source, ALsizei n, ALuint * buffers);
typedef ALvoid (ALAPIENTRY *alSourceQueueBuffersPROC) (ALuint source, ALsizei n, ALuint * buffers);
typedef ALvoid (ALAPIENTRY *alGetBufferfPROC) (ALuint buffer, ALenum pname, ALfloat* value);
typedef ALvoid (ALAPIENTRY *alGetBufferiPROC) (ALuint buffer, ALenum pname, ALint* value);
typedef ALvoid (ALAPIENTRY *alBufferDataPROC) (ALuint buffer, ALenum format, ALvoid * data, ALsizei size, ALsizei freq);
typedef ALboolean (ALAPIENTRY *alIsBufferPROC) (ALuint buffer);
typedef ALvoid (ALAPIENTRY *alDeleteBuffersPROC) (ALsizei n, ALuint * buffers);
typedef ALvoid (ALAPIENTRY *alGenBuffersPROC) (ALsizei n, ALuint * buffers);
typedef ALvoid (ALAPIENTRY *alSourceRewindPROC) (ALuint source);
typedef ALvoid (ALAPIENTRY *alSourceStopPROC) (ALuint source);
typedef ALvoid (ALAPIENTRY *alSourcePausePROC) (ALuint source);
typedef ALvoid (ALAPIENTRY *alSourcePlayPROC) (ALuint source);
typedef ALvoid (ALAPIENTRY *alSourceRewindvPROC) (ALsizei n, ALuint * sources);
typedef ALvoid (ALAPIENTRY *alSourceStopvPROC) (ALsizei n, ALuint * sources);
typedef ALvoid (ALAPIENTRY *alSourcePausevPROC) (ALsizei n, ALuint * sources);
typedef ALvoid (ALAPIENTRY *alSourcePlayvPROC) (ALsizei n, ALuint * sources);
typedef ALvoid (ALAPIENTRY *alGetSourcefvPROC) (ALuint source, ALenum pname, ALfloat * floatdata);
typedef ALvoid (ALAPIENTRY *alGetSourcefPROC) (ALuint source, ALenum pname, ALfloat* value);
typedef ALvoid (ALAPIENTRY *alGetSourceiPROC) (ALuint source, ALenum pname, ALint* value);
typedef ALvoid (ALAPIENTRY *alSource3fPROC) (ALuint source, ALenum pname, ALfloat v1, ALfloat v2, ALfloat v3);
typedef ALvoid (ALAPIENTRY *alSourcefvPROC) (ALuint source, ALenum pname, ALfloat * value);
typedef ALvoid (ALAPIENTRY *alSourcefPROC) (ALuint source, ALenum pname, ALfloat value);
typedef ALvoid (ALAPIENTRY *alSourceiPROC) (ALuint source, ALenum pname, ALint value);
typedef ALboolean (ALAPIENTRY *alIsSourcePROC) (ALuint id);
typedef ALvoid (ALAPIENTRY *alDeleteSourcesPROC) (ALsizei n, ALuint * sources);
typedef ALvoid (ALAPIENTRY *alGenSourcesPROC) (ALsizei n, ALuint * sources);
typedef ALvoid (ALAPIENTRY *alGetListenerfvPROC) (ALenum pname, ALfloat * floatdata);
typedef ALfloat (ALAPIENTRY *alGetListenerfPROC) (ALenum pname);
typedef ALint (ALAPIENTRY *alGetListeneriPROC) (ALenum pname);
typedef ALvoid (ALAPIENTRY *alListener3fPROC) (ALenum pname, ALfloat v1, ALfloat v2, ALfloat v3);
typedef ALvoid (ALAPIENTRY *alListenerfvPROC) (ALenum pname, ALfloat * value);
typedef ALvoid (ALAPIENTRY *alListenerfPROC) (ALenum pname, ALfloat value);
typedef ALvoid (ALAPIENTRY *alListeneriPROC) (ALenum pname, ALint value);
typedef ALenum (ALAPIENTRY *alGetEnumValuePROC) (ALubyte * ename);
typedef ALboolean (ALAPIENTRY *alIsExtensionPresentPROC) (ALubyte * fname);
typedef ALenum (ALAPIENTRY *alGetErrorPROC) ();
typedef ALubyte * (ALAPIENTRY *alGetStringPROC) (ALenum pname);
typedef ALvoid (ALAPIENTRY *alGetFloatvPROC) (ALenum pname, ALfloat * data);
typedef ALvoid (ALAPIENTRY *alGetIntegervPROC) (ALenum pname, ALint * data);
typedef ALfloat (ALAPIENTRY *alGetFloatPROC) (ALenum pname);
typedef ALint (ALAPIENTRY *alGetIntegerPROC) (ALenum pname);
typedef ALboolean (ALAPIENTRY *alGetBooleanPROC) (ALenum pname);
typedef ALboolean (ALAPIENTRY *alIsEnabledPROC) (ALenum capability);
typedef ALvoid (ALAPIENTRY *alDisablePROC) (ALenum capability);
typedef ALvoid (ALAPIENTRY *alEnablePROC) (ALint capability);

static alDopplerVelocityPROC alDopplerVelocity;
static alDopplerFactorPROC alDopplerFactor;
static alDistanceModelPROC alDistanceModel;
static alSourceUnqueueBuffersPROC alSourceUnqueueBuffers;
static alSourceQueueBuffersPROC alSourceQueueBuffers;
static alGetBufferfPROC alGetBufferf;
static alGetBufferiPROC alGetBufferi;
static alBufferDataPROC alBufferData;
static alIsBufferPROC alIsBuffer;
static alDeleteBuffersPROC alDeleteBuffers;
static alGenBuffersPROC alGenBuffers;
static alSourceRewindPROC alSourceRewind;
static alSourceStopPROC alSourceStop;
static alSourcePausePROC alSourcePause;
static alSourcePlayPROC alSourcePlay;
static alSourceRewindvPROC alSourceRewindv;
static alSourceStopvPROC alSourceStopv;
static alSourcePausevPROC alSourcePausev;
static alSourcePlayvPROC alSourcePlayv;
static alGetSourcefvPROC alGetSourcefv;
static alGetSourcefPROC alGetSourcef;
static alGetSourceiPROC alGetSourcei;
static alSource3fPROC alSource3f;
static alSourcefvPROC alSourcefv;
static alSourcefPROC alSourcef;
static alSourceiPROC alSourcei;
static alIsSourcePROC alIsSource;
static alDeleteSourcesPROC alDeleteSources;
static alGenSourcesPROC alGenSources;
static alGetListenerfvPROC alGetListenerfv;
static alGetListenerfPROC alGetListenerf;
static alGetListeneriPROC alGetListeneri;
static alListener3fPROC alListener3f;
static alListenerfvPROC alListenerfv;
static alListenerfPROC alListenerf;
static alListeneriPROC alListeneri;
static alGetEnumValuePROC alGetEnumValue;
static alIsExtensionPresentPROC alIsExtensionPresent;
static alGetErrorPROC alGetError;
static alGetStringPROC alGetString;
static alGetFloatvPROC alGetFloatv;
static alGetIntegervPROC alGetIntegerv;
static alGetFloatPROC alGetFloat;
static alGetIntegerPROC alGetInteger;
static alGetBooleanPROC alGetBoolean;
static alIsEnabledPROC alIsEnabled;
static alDisablePROC alDisable;
static alEnablePROC alEnable;

static void JNICALL Java_org_lwjgl_openal_AL10_nalDopplerVelocity(JNIEnv *env, jclass clazz, jfloat value) {
	alDopplerVelocity(value);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalDopplerFactor(JNIEnv *env, jclass clazz, jfloat value) {
	alDopplerFactor(value);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalDistanceModel(JNIEnv *env, jclass clazz, jint value) {
	alDistanceModel(value);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceUnqueueBuffers(JNIEnv *env, jclass clazz, jint source, jint n, jobject buffers, jint buffers_position) {
	ALuint *buffers_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	alSourceUnqueueBuffers(source, n, buffers_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceQueueBuffers(JNIEnv *env, jclass clazz, jint source, jint n, jobject buffers, jint buffers_position) {
	ALuint *buffers_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	alSourceQueueBuffers(source, n, buffers_address);
}

static jfloat JNICALL Java_org_lwjgl_openal_AL10_nalGetBufferf(JNIEnv *env, jclass clazz, jint buffer, jint pname) {
	ALfloat __result;
	alGetBufferf(buffer, pname, &__result);
	return __result;
}

static jint JNICALL Java_org_lwjgl_openal_AL10_nalGetBufferi(JNIEnv *env, jclass clazz, jint buffer, jint pname) {
	ALint __result;
	alGetBufferi(buffer, pname, &__result);
	return __result;
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalBufferData(JNIEnv *env, jclass clazz, jint buffer, jint format, jobject data, jint data_position, jint size, jint freq) {
	ALvoid *data_address = ((ALvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	alBufferData(buffer, format, data_address, size, freq);
}

static jboolean JNICALL Java_org_lwjgl_openal_AL10_nalIsBuffer(JNIEnv *env, jclass clazz, jint buffer) {
	ALboolean __result = alIsBuffer(buffer);
	return __result;
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalDeleteBuffers(JNIEnv *env, jclass clazz, jint n, jobject buffers, jint buffers_position) {
	ALuint *buffers_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	alDeleteBuffers(n, buffers_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalGenBuffers(JNIEnv *env, jclass clazz, jint n, jobject buffers, jint buffers_position) {
	ALuint *buffers_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	alGenBuffers(n, buffers_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceRewind(JNIEnv *env, jclass clazz, jint source) {
	alSourceRewind(source);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceStop(JNIEnv *env, jclass clazz, jint source) {
	alSourceStop(source);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcePause(JNIEnv *env, jclass clazz, jint source) {
	alSourcePause(source);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcePlay(JNIEnv *env, jclass clazz, jint source) {
	alSourcePlay(source);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceRewindv(JNIEnv *env, jclass clazz, jint n, jobject sources, jint sources_position) {
	ALuint *sources_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, sources)) + sources_position;
	alSourceRewindv(n, sources_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceStopv(JNIEnv *env, jclass clazz, jint n, jobject sources, jint sources_position) {
	ALuint *sources_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, sources)) + sources_position;
	alSourceStopv(n, sources_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcePausev(JNIEnv *env, jclass clazz, jint n, jobject sources, jint sources_position) {
	ALuint *sources_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, sources)) + sources_position;
	alSourcePausev(n, sources_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcePlayv(JNIEnv *env, jclass clazz, jint n, jobject sources, jint sources_position) {
	ALuint *sources_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, sources)) + sources_position;
	alSourcePlayv(n, sources_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalGetSourcefv(JNIEnv *env, jclass clazz, jint source, jint pname, jobject floatdata, jint floatdata_position) {
	ALfloat *floatdata_address = ((ALfloat *)(*env)->GetDirectBufferAddress(env, floatdata)) + floatdata_position;
	alGetSourcefv(source, pname, floatdata_address);
}

static jfloat JNICALL Java_org_lwjgl_openal_AL10_nalGetSourcef(JNIEnv *env, jclass clazz, jint source, jint pname) {
	ALfloat __result;
	alGetSourcef(source, pname, &__result);
	return __result;
}

static jint JNICALL Java_org_lwjgl_openal_AL10_nalGetSourcei(JNIEnv *env, jclass clazz, jint source, jint pname) {
	ALint __result;
	alGetSourcei(source, pname, &__result);
	return __result;
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSource3f(JNIEnv *env, jclass clazz, jint source, jint pname, jfloat v1, jfloat v2, jfloat v3) {
	alSource3f(source, pname, v1, v2, v3);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcefv(JNIEnv *env, jclass clazz, jint source, jint pname, jobject value, jint value_position) {
	ALfloat *value_address = ((ALfloat *)(*env)->GetDirectBufferAddress(env, value)) + value_position;
	alSourcefv(source, pname, value_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcef(JNIEnv *env, jclass clazz, jint source, jint pname, jfloat value) {
	alSourcef(source, pname, value);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcei(JNIEnv *env, jclass clazz, jint source, jint pname, jint value) {
	alSourcei(source, pname, value);
}

static jboolean JNICALL Java_org_lwjgl_openal_AL10_nalIsSource(JNIEnv *env, jclass clazz, jint id) {
	ALboolean __result = alIsSource(id);
	return __result;
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalDeleteSources(JNIEnv *env, jclass clazz, jint n, jobject sources, jint sources_position) {
	ALuint *sources_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, sources)) + sources_position;
	alDeleteSources(n, sources_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalGenSources(JNIEnv *env, jclass clazz, jint n, jobject sources, jint sources_position) {
	ALuint *sources_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, sources)) + sources_position;
	alGenSources(n, sources_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalGetListenerfv(JNIEnv *env, jclass clazz, jint pname, jobject floatdata, jint floatdata_position) {
	ALfloat *floatdata_address = ((ALfloat *)(*env)->GetDirectBufferAddress(env, floatdata)) + floatdata_position;
	alGetListenerfv(pname, floatdata_address);
}

static jfloat JNICALL Java_org_lwjgl_openal_AL10_nalGetListenerf(JNIEnv *env, jclass clazz, jint pname) {
	ALfloat __result = alGetListenerf(pname);
	return __result;
}

static jint JNICALL Java_org_lwjgl_openal_AL10_nalGetListeneri(JNIEnv *env, jclass clazz, jint pname) {
	ALint __result = alGetListeneri(pname);
	return __result;
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalListener3f(JNIEnv *env, jclass clazz, jint pname, jfloat v1, jfloat v2, jfloat v3) {
	alListener3f(pname, v1, v2, v3);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalListenerfv(JNIEnv *env, jclass clazz, jint pname, jobject value, jint value_position) {
	ALfloat *value_address = ((ALfloat *)(*env)->GetDirectBufferAddress(env, value)) + value_position;
	alListenerfv(pname, value_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalListenerf(JNIEnv *env, jclass clazz, jint pname, jfloat value) {
	alListenerf(pname, value);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalListeneri(JNIEnv *env, jclass clazz, jint pname, jint value) {
	alListeneri(pname, value);
}

static jint JNICALL Java_org_lwjgl_openal_AL10_nalGetEnumValue(JNIEnv *env, jclass clazz, jobject ename) {
	ALubyte *ename_address = ((ALubyte *)GetStringNativeChars(env, ename));
	ALenum __result = alGetEnumValue(ename_address);
	free(ename_address);
	return __result;
}

static jboolean JNICALL Java_org_lwjgl_openal_AL10_nalIsExtensionPresent(JNIEnv *env, jclass clazz, jobject fname) {
	ALubyte *fname_address = ((ALubyte *)GetStringNativeChars(env, fname));
	ALboolean __result = alIsExtensionPresent(fname_address);
	free(fname_address);
	return __result;
}

static jint JNICALL Java_org_lwjgl_openal_AL10_alGetError(JNIEnv *env, jclass clazz) {
	ALenum __result = alGetError();
	return __result;
}

static jobject JNICALL Java_org_lwjgl_openal_AL10_alGetString(JNIEnv *env, jclass clazz, jint pname) {
	ALubyte * __result = alGetString(pname);
	return NewStringNative(env, __result);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalGetFloatv(JNIEnv *env, jclass clazz, jint pname, jobject data, jint data_position) {
	ALfloat *data_address = ((ALfloat *)(*env)->GetDirectBufferAddress(env, data)) + data_position;
	alGetFloatv(pname, data_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalGetIntegerv(JNIEnv *env, jclass clazz, jint pname, jobject data, jint data_position) {
	ALint *data_address = ((ALint *)(*env)->GetDirectBufferAddress(env, data)) + data_position;
	alGetIntegerv(pname, data_address);
}

static jfloat JNICALL Java_org_lwjgl_openal_AL10_nalGetFloat(JNIEnv *env, jclass clazz, jint pname) {
	ALfloat __result = alGetFloat(pname);
	return __result;
}

static jint JNICALL Java_org_lwjgl_openal_AL10_nalGetInteger(JNIEnv *env, jclass clazz, jint pname) {
	ALint __result = alGetInteger(pname);
	return __result;
}

static jboolean JNICALL Java_org_lwjgl_openal_AL10_nalGetBoolean(JNIEnv *env, jclass clazz, jint pname) {
	ALboolean __result = alGetBoolean(pname);
	return __result;
}

static jboolean JNICALL Java_org_lwjgl_openal_AL10_nalIsEnabled(JNIEnv *env, jclass clazz, jint capability) {
	ALboolean __result = alIsEnabled(capability);
	return __result;
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalDisable(JNIEnv *env, jclass clazz, jint capability) {
	alDisable(capability);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalEnable(JNIEnv *env, jclass clazz, jint capability) {
	alEnable(capability);
}

JNIEXPORT void JNICALL Java_org_lwjgl_openal_AL10_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nalDopplerVelocity", "(F)V", (void *)&Java_org_lwjgl_openal_AL10_nalDopplerVelocity, "alDopplerVelocity", (void *)&alDopplerVelocity},
		{"nalDopplerFactor", "(F)V", (void *)&Java_org_lwjgl_openal_AL10_nalDopplerFactor, "alDopplerFactor", (void *)&alDopplerFactor},
		{"nalDistanceModel", "(I)V", (void *)&Java_org_lwjgl_openal_AL10_nalDistanceModel, "alDistanceModel", (void *)&alDistanceModel},
		{"nalSourceUnqueueBuffers", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourceUnqueueBuffers, "alSourceUnqueueBuffers", (void *)&alSourceUnqueueBuffers},
		{"nalSourceQueueBuffers", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourceQueueBuffers, "alSourceQueueBuffers", (void *)&alSourceQueueBuffers},
		{"nalGetBufferf", "(II)F", (void *)&Java_org_lwjgl_openal_AL10_nalGetBufferf, "alGetBufferf", (void *)&alGetBufferf},
		{"nalGetBufferi", "(II)I", (void *)&Java_org_lwjgl_openal_AL10_nalGetBufferi, "alGetBufferi", (void *)&alGetBufferi},
		{"nalBufferData", "(IILjava/nio/Buffer;III)V", (void *)&Java_org_lwjgl_openal_AL10_nalBufferData, "alBufferData", (void *)&alBufferData},
		{"nalIsBuffer", "(I)Z", (void *)&Java_org_lwjgl_openal_AL10_nalIsBuffer, "alIsBuffer", (void *)&alIsBuffer},
		{"nalDeleteBuffers", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalDeleteBuffers, "alDeleteBuffers", (void *)&alDeleteBuffers},
		{"nalGenBuffers", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalGenBuffers, "alGenBuffers", (void *)&alGenBuffers},
		{"nalSourceRewind", "(I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourceRewind, "alSourceRewind", (void *)&alSourceRewind},
		{"nalSourceStop", "(I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourceStop, "alSourceStop", (void *)&alSourceStop},
		{"nalSourcePause", "(I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourcePause, "alSourcePause", (void *)&alSourcePause},
		{"nalSourcePlay", "(I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourcePlay, "alSourcePlay", (void *)&alSourcePlay},
		{"nalSourceRewindv", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourceRewindv, "alSourceRewindv", (void *)&alSourceRewindv},
		{"nalSourceStopv", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourceStopv, "alSourceStopv", (void *)&alSourceStopv},
		{"nalSourcePausev", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourcePausev, "alSourcePausev", (void *)&alSourcePausev},
		{"nalSourcePlayv", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourcePlayv, "alSourcePlayv", (void *)&alSourcePlayv},
		{"nalGetSourcefv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalGetSourcefv, "alGetSourcefv", (void *)&alGetSourcefv},
		{"nalGetSourcef", "(II)F", (void *)&Java_org_lwjgl_openal_AL10_nalGetSourcef, "alGetSourcef", (void *)&alGetSourcef},
		{"nalGetSourcei", "(II)I", (void *)&Java_org_lwjgl_openal_AL10_nalGetSourcei, "alGetSourcei", (void *)&alGetSourcei},
		{"nalSource3f", "(IIFFF)V", (void *)&Java_org_lwjgl_openal_AL10_nalSource3f, "alSource3f", (void *)&alSource3f},
		{"nalSourcefv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourcefv, "alSourcefv", (void *)&alSourcefv},
		{"nalSourcef", "(IIF)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourcef, "alSourcef", (void *)&alSourcef},
		{"nalSourcei", "(III)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourcei, "alSourcei", (void *)&alSourcei},
		{"nalIsSource", "(I)Z", (void *)&Java_org_lwjgl_openal_AL10_nalIsSource, "alIsSource", (void *)&alIsSource},
		{"nalDeleteSources", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalDeleteSources, "alDeleteSources", (void *)&alDeleteSources},
		{"nalGenSources", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalGenSources, "alGenSources", (void *)&alGenSources},
		{"nalGetListenerfv", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalGetListenerfv, "alGetListenerfv", (void *)&alGetListenerfv},
		{"nalGetListenerf", "(I)F", (void *)&Java_org_lwjgl_openal_AL10_nalGetListenerf, "alGetListenerf", (void *)&alGetListenerf},
		{"nalGetListeneri", "(I)I", (void *)&Java_org_lwjgl_openal_AL10_nalGetListeneri, "alGetListeneri", (void *)&alGetListeneri},
		{"nalListener3f", "(IFFF)V", (void *)&Java_org_lwjgl_openal_AL10_nalListener3f, "alListener3f", (void *)&alListener3f},
		{"nalListenerfv", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalListenerfv, "alListenerfv", (void *)&alListenerfv},
		{"nalListenerf", "(IF)V", (void *)&Java_org_lwjgl_openal_AL10_nalListenerf, "alListenerf", (void *)&alListenerf},
		{"nalListeneri", "(II)V", (void *)&Java_org_lwjgl_openal_AL10_nalListeneri, "alListeneri", (void *)&alListeneri},
		{"nalGetEnumValue", "(Ljava/lang/String;)I", (void *)&Java_org_lwjgl_openal_AL10_nalGetEnumValue, "alGetEnumValue", (void *)&alGetEnumValue},
		{"nalIsExtensionPresent", "(Ljava/lang/String;)Z", (void *)&Java_org_lwjgl_openal_AL10_nalIsExtensionPresent, "alIsExtensionPresent", (void *)&alIsExtensionPresent},
		{"alGetError", "()I", (void *)&Java_org_lwjgl_openal_AL10_alGetError, "alGetError", (void *)&alGetError},
		{"alGetString", "(I)Ljava/lang/String;", (void *)&Java_org_lwjgl_openal_AL10_alGetString, "alGetString", (void *)&alGetString},
		{"nalGetFloatv", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalGetFloatv, "alGetFloatv", (void *)&alGetFloatv},
		{"nalGetIntegerv", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalGetIntegerv, "alGetIntegerv", (void *)&alGetIntegerv},
		{"nalGetFloat", "(I)F", (void *)&Java_org_lwjgl_openal_AL10_nalGetFloat, "alGetFloat", (void *)&alGetFloat},
		{"nalGetInteger", "(I)I", (void *)&Java_org_lwjgl_openal_AL10_nalGetInteger, "alGetInteger", (void *)&alGetInteger},
		{"nalGetBoolean", "(I)Z", (void *)&Java_org_lwjgl_openal_AL10_nalGetBoolean, "alGetBoolean", (void *)&alGetBoolean},
		{"nalIsEnabled", "(I)Z", (void *)&Java_org_lwjgl_openal_AL10_nalIsEnabled, "alIsEnabled", (void *)&alIsEnabled},
		{"nalDisable", "(I)V", (void *)&Java_org_lwjgl_openal_AL10_nalDisable, "alDisable", (void *)&alDisable},
		{"nalEnable", "(I)V", (void *)&Java_org_lwjgl_openal_AL10_nalEnable, "alEnable", (void *)&alEnable}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extal_InitializeClass(env, clazz, num_functions, functions);
}
