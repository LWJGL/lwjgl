/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extal.h"

typedef ALvoid (ALAPIENTRY *alEnablePROC) (ALint capability);
typedef ALvoid (ALAPIENTRY *alDisablePROC) (ALenum capability);
typedef ALboolean (ALAPIENTRY *alIsEnabledPROC) (ALenum capability);
typedef ALboolean (ALAPIENTRY *alGetBooleanPROC) (ALenum pname);
typedef ALint (ALAPIENTRY *alGetIntegerPROC) (ALenum pname);
typedef ALfloat (ALAPIENTRY *alGetFloatPROC) (ALenum pname);
typedef ALdouble (ALAPIENTRY *alGetDoublePROC) (ALenum pname);
typedef ALvoid (ALAPIENTRY *alGetIntegervPROC) (ALenum pname, ALint * data);
typedef ALvoid (ALAPIENTRY *alGetFloatvPROC) (ALenum pname, ALfloat * data);
typedef ALvoid (ALAPIENTRY *alGetDoublevPROC) (ALenum pname, ALdouble * data);
typedef ALubyte * (ALAPIENTRY *alGetStringPROC) (ALenum pname);
typedef ALenum (ALAPIENTRY *alGetErrorPROC) ();
typedef ALboolean (ALAPIENTRY *alIsExtensionPresentPROC) (ALubyte * fname);
typedef ALenum (ALAPIENTRY *alGetEnumValuePROC) (ALubyte * ename);
typedef ALvoid (ALAPIENTRY *alListeneriPROC) (ALenum pname, ALint value);
typedef ALvoid (ALAPIENTRY *alListenerfPROC) (ALenum pname, ALfloat value);
typedef ALvoid (ALAPIENTRY *alListenerfvPROC) (ALenum pname, const ALfloat * value);
typedef ALvoid (ALAPIENTRY *alListener3fPROC) (ALenum pname, ALfloat v1, ALfloat v2, ALfloat v3);
typedef void (ALAPIENTRY *alGetListeneriPROC) (ALenum pname, ALint* value);
typedef void (ALAPIENTRY *alGetListenerfPROC) (ALenum pname, ALfloat* value);
typedef ALvoid (ALAPIENTRY *alGetListenerfvPROC) (ALenum pname, ALfloat * floatdata);
typedef ALvoid (ALAPIENTRY *alGenSourcesPROC) (ALsizei n, ALuint * sources);
typedef ALvoid (ALAPIENTRY *alDeleteSourcesPROC) (ALsizei n, ALuint * sources);
typedef ALboolean (ALAPIENTRY *alIsSourcePROC) (ALuint id);
typedef ALvoid (ALAPIENTRY *alSourceiPROC) (ALuint source, ALenum pname, ALint value);
typedef ALvoid (ALAPIENTRY *alSourcefPROC) (ALuint source, ALenum pname, ALfloat value);
typedef ALvoid (ALAPIENTRY *alSourcefvPROC) (ALuint source, ALenum pname, const ALfloat * value);
typedef ALvoid (ALAPIENTRY *alSource3fPROC) (ALuint source, ALenum pname, ALfloat v1, ALfloat v2, ALfloat v3);
typedef ALvoid (ALAPIENTRY *alGetSourceiPROC) (ALuint source, ALenum pname, ALint* value);
typedef ALvoid (ALAPIENTRY *alGetSourcefPROC) (ALuint source, ALenum pname, ALfloat* value);
typedef ALvoid (ALAPIENTRY *alGetSourcefvPROC) (ALuint source, ALenum pname, ALfloat * floatdata);
typedef ALvoid (ALAPIENTRY *alSourcePlayvPROC) (ALsizei n, ALuint * sources);
typedef ALvoid (ALAPIENTRY *alSourcePausevPROC) (ALsizei n, ALuint * sources);
typedef ALvoid (ALAPIENTRY *alSourceStopvPROC) (ALsizei n, ALuint * sources);
typedef ALvoid (ALAPIENTRY *alSourceRewindvPROC) (ALsizei n, ALuint * sources);
typedef ALvoid (ALAPIENTRY *alSourcePlayPROC) (ALuint source);
typedef ALvoid (ALAPIENTRY *alSourcePausePROC) (ALuint source);
typedef ALvoid (ALAPIENTRY *alSourceStopPROC) (ALuint source);
typedef ALvoid (ALAPIENTRY *alSourceRewindPROC) (ALuint source);
typedef ALvoid (ALAPIENTRY *alGenBuffersPROC) (ALsizei n, ALuint * buffers);
typedef ALvoid (ALAPIENTRY *alDeleteBuffersPROC) (ALsizei n, ALuint * buffers);
typedef ALboolean (ALAPIENTRY *alIsBufferPROC) (ALuint buffer);
typedef ALvoid (ALAPIENTRY *alBufferDataPROC) (ALuint buffer, ALenum format, ALvoid * data, ALsizei size, ALsizei freq);
typedef ALvoid (ALAPIENTRY *alGetBufferiPROC) (ALuint buffer, ALenum pname, ALint* value);
typedef ALvoid (ALAPIENTRY *alGetBufferfPROC) (ALuint buffer, ALenum pname, ALfloat* value);
typedef ALvoid (ALAPIENTRY *alSourceQueueBuffersPROC) (ALuint source, ALsizei n, ALuint * buffers);
typedef ALvoid (ALAPIENTRY *alSourceUnqueueBuffersPROC) (ALuint source, ALsizei n, ALuint * buffers);
typedef ALvoid (ALAPIENTRY *alDistanceModelPROC) (ALenum value);
typedef ALvoid (ALAPIENTRY *alDopplerFactorPROC) (ALfloat value);
typedef ALvoid (ALAPIENTRY *alDopplerVelocityPROC) (ALfloat value);

static alEnablePROC alEnable;
static alDisablePROC alDisable;
static alIsEnabledPROC alIsEnabled;
static alGetBooleanPROC alGetBoolean;
static alGetIntegerPROC alGetInteger;
static alGetFloatPROC alGetFloat;
static alGetDoublePROC alGetDouble;
static alGetIntegervPROC alGetIntegerv;
static alGetFloatvPROC alGetFloatv;
static alGetDoublevPROC alGetDoublev;
static alGetStringPROC alGetString;
static alGetErrorPROC alGetError;
static alIsExtensionPresentPROC alIsExtensionPresent;
static alGetEnumValuePROC alGetEnumValue;
static alListeneriPROC alListeneri;
static alListenerfPROC alListenerf;
static alListenerfvPROC alListenerfv;
static alListener3fPROC alListener3f;
static alGetListeneriPROC alGetListeneri;
static alGetListenerfPROC alGetListenerf;
static alGetListenerfvPROC alGetListenerfv;
static alGenSourcesPROC alGenSources;
static alDeleteSourcesPROC alDeleteSources;
static alIsSourcePROC alIsSource;
static alSourceiPROC alSourcei;
static alSourcefPROC alSourcef;
static alSourcefvPROC alSourcefv;
static alSource3fPROC alSource3f;
static alGetSourceiPROC alGetSourcei;
static alGetSourcefPROC alGetSourcef;
static alGetSourcefvPROC alGetSourcefv;
static alSourcePlayvPROC alSourcePlayv;
static alSourcePausevPROC alSourcePausev;
static alSourceStopvPROC alSourceStopv;
static alSourceRewindvPROC alSourceRewindv;
static alSourcePlayPROC alSourcePlay;
static alSourcePausePROC alSourcePause;
static alSourceStopPROC alSourceStop;
static alSourceRewindPROC alSourceRewind;
static alGenBuffersPROC alGenBuffers;
static alDeleteBuffersPROC alDeleteBuffers;
static alIsBufferPROC alIsBuffer;
static alBufferDataPROC alBufferData;
static alGetBufferiPROC alGetBufferi;
static alGetBufferfPROC alGetBufferf;
static alSourceQueueBuffersPROC alSourceQueueBuffers;
static alSourceUnqueueBuffersPROC alSourceUnqueueBuffers;
static alDistanceModelPROC alDistanceModel;
static alDopplerFactorPROC alDopplerFactor;
static alDopplerVelocityPROC alDopplerVelocity;

static void JNICALL Java_org_lwjgl_openal_AL10_nalEnable(JNIEnv *env, jclass clazz, jint capability) {
	alEnable(capability);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalDisable(JNIEnv *env, jclass clazz, jint capability) {
	alDisable(capability);
}

static jboolean JNICALL Java_org_lwjgl_openal_AL10_nalIsEnabled(JNIEnv *env, jclass clazz, jint capability) {
	ALboolean __result = alIsEnabled(capability);
	return __result;
}

static jboolean JNICALL Java_org_lwjgl_openal_AL10_nalGetBoolean(JNIEnv *env, jclass clazz, jint pname) {
	ALboolean __result = alGetBoolean(pname);
	return __result;
}

static jint JNICALL Java_org_lwjgl_openal_AL10_nalGetInteger(JNIEnv *env, jclass clazz, jint pname) {
	ALint __result = alGetInteger(pname);
	return __result;
}

static jfloat JNICALL Java_org_lwjgl_openal_AL10_nalGetFloat(JNIEnv *env, jclass clazz, jint pname) {
	ALfloat __result = alGetFloat(pname);
	return __result;
}

static jdouble JNICALL Java_org_lwjgl_openal_AL10_nalGetDouble(JNIEnv *env, jclass clazz, jint pname) {
	ALdouble __result = alGetDouble(pname);
	return __result;
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalGetIntegerv(JNIEnv *env, jclass clazz, jint pname, jobject data, jint data_position) {
	ALint *data_address = ((ALint *)(*env)->GetDirectBufferAddress(env, data)) + data_position;
	alGetIntegerv(pname, data_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalGetFloatv(JNIEnv *env, jclass clazz, jint pname, jobject data, jint data_position) {
	ALfloat *data_address = ((ALfloat *)(*env)->GetDirectBufferAddress(env, data)) + data_position;
	alGetFloatv(pname, data_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalGetDoublev(JNIEnv *env, jclass clazz, jint pname, jobject data, jint data_position) {
	ALdouble *data_address = ((ALdouble *)(*env)->GetDirectBufferAddress(env, data)) + data_position;
	alGetDoublev(pname, data_address);
}

static jobject JNICALL Java_org_lwjgl_openal_AL10_alGetString(JNIEnv *env, jclass clazz, jint pname) {
	ALubyte * __result = alGetString(pname);
	return NewStringNativeUnsigned(env, __result);
}

static jint JNICALL Java_org_lwjgl_openal_AL10_alGetError(JNIEnv *env, jclass clazz) {
	ALenum __result = alGetError();
	return __result;
}

static jboolean JNICALL Java_org_lwjgl_openal_AL10_nalIsExtensionPresent(JNIEnv *env, jclass clazz, jobject fname) {
	ALubyte *fname_address = ((ALubyte *)GetStringNativeChars(env, fname));
	ALboolean __result = alIsExtensionPresent(fname_address);
	free(fname_address);
	return __result;
}

static jint JNICALL Java_org_lwjgl_openal_AL10_nalGetEnumValue(JNIEnv *env, jclass clazz, jobject ename) {
	ALubyte *ename_address = ((ALubyte *)GetStringNativeChars(env, ename));
	ALenum __result = alGetEnumValue(ename_address);
	free(ename_address);
	return __result;
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalListeneri(JNIEnv *env, jclass clazz, jint pname, jint value) {
	alListeneri(pname, value);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalListenerf(JNIEnv *env, jclass clazz, jint pname, jfloat value) {
	alListenerf(pname, value);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalListenerfv(JNIEnv *env, jclass clazz, jint pname, jobject value, jint value_position) {
	const ALfloat *value_address = ((const ALfloat *)(*env)->GetDirectBufferAddress(env, value)) + value_position;
	alListenerfv(pname, value_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalListener3f(JNIEnv *env, jclass clazz, jint pname, jfloat v1, jfloat v2, jfloat v3) {
	alListener3f(pname, v1, v2, v3);
}

static jint JNICALL Java_org_lwjgl_openal_AL10_nalGetListeneri(JNIEnv *env, jclass clazz, jint pname) {
	ALint __result;
	alGetListeneri(pname, &__result);
	return __result;
}

static jfloat JNICALL Java_org_lwjgl_openal_AL10_nalGetListenerf(JNIEnv *env, jclass clazz, jint pname) {
	ALfloat __result;
	alGetListenerf(pname, &__result);
	return __result;
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalGetListenerfv(JNIEnv *env, jclass clazz, jint pname, jobject floatdata, jint floatdata_position) {
	ALfloat *floatdata_address = ((ALfloat *)(*env)->GetDirectBufferAddress(env, floatdata)) + floatdata_position;
	alGetListenerfv(pname, floatdata_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalGenSources(JNIEnv *env, jclass clazz, jint n, jobject sources, jint sources_position) {
	ALuint *sources_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, sources)) + sources_position;
	alGenSources(n, sources_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalDeleteSources(JNIEnv *env, jclass clazz, jint n, jobject sources, jint sources_position) {
	ALuint *sources_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, sources)) + sources_position;
	alDeleteSources(n, sources_address);
}

static jboolean JNICALL Java_org_lwjgl_openal_AL10_nalIsSource(JNIEnv *env, jclass clazz, jint id) {
	ALboolean __result = alIsSource(id);
	return __result;
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcei(JNIEnv *env, jclass clazz, jint source, jint pname, jint value) {
	alSourcei(source, pname, value);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcef(JNIEnv *env, jclass clazz, jint source, jint pname, jfloat value) {
	alSourcef(source, pname, value);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcefv(JNIEnv *env, jclass clazz, jint source, jint pname, jobject value, jint value_position) {
	const ALfloat *value_address = ((const ALfloat *)(*env)->GetDirectBufferAddress(env, value)) + value_position;
	alSourcefv(source, pname, value_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSource3f(JNIEnv *env, jclass clazz, jint source, jint pname, jfloat v1, jfloat v2, jfloat v3) {
	alSource3f(source, pname, v1, v2, v3);
}

static jint JNICALL Java_org_lwjgl_openal_AL10_nalGetSourcei(JNIEnv *env, jclass clazz, jint source, jint pname) {
	ALint __result;
	alGetSourcei(source, pname, &__result);
	return __result;
}

static jfloat JNICALL Java_org_lwjgl_openal_AL10_nalGetSourcef(JNIEnv *env, jclass clazz, jint source, jint pname) {
	ALfloat __result;
	alGetSourcef(source, pname, &__result);
	return __result;
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalGetSourcefv(JNIEnv *env, jclass clazz, jint source, jint pname, jobject floatdata, jint floatdata_position) {
	ALfloat *floatdata_address = ((ALfloat *)(*env)->GetDirectBufferAddress(env, floatdata)) + floatdata_position;
	alGetSourcefv(source, pname, floatdata_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcePlayv(JNIEnv *env, jclass clazz, jint n, jobject sources, jint sources_position) {
	ALuint *sources_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, sources)) + sources_position;
	alSourcePlayv(n, sources_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcePausev(JNIEnv *env, jclass clazz, jint n, jobject sources, jint sources_position) {
	ALuint *sources_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, sources)) + sources_position;
	alSourcePausev(n, sources_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceStopv(JNIEnv *env, jclass clazz, jint n, jobject sources, jint sources_position) {
	ALuint *sources_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, sources)) + sources_position;
	alSourceStopv(n, sources_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceRewindv(JNIEnv *env, jclass clazz, jint n, jobject sources, jint sources_position) {
	ALuint *sources_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, sources)) + sources_position;
	alSourceRewindv(n, sources_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcePlay(JNIEnv *env, jclass clazz, jint source) {
	alSourcePlay(source);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourcePause(JNIEnv *env, jclass clazz, jint source) {
	alSourcePause(source);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceStop(JNIEnv *env, jclass clazz, jint source) {
	alSourceStop(source);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceRewind(JNIEnv *env, jclass clazz, jint source) {
	alSourceRewind(source);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalGenBuffers(JNIEnv *env, jclass clazz, jint n, jobject buffers, jint buffers_position) {
	ALuint *buffers_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	alGenBuffers(n, buffers_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalDeleteBuffers(JNIEnv *env, jclass clazz, jint n, jobject buffers, jint buffers_position) {
	ALuint *buffers_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	alDeleteBuffers(n, buffers_address);
}

static jboolean JNICALL Java_org_lwjgl_openal_AL10_nalIsBuffer(JNIEnv *env, jclass clazz, jint buffer) {
	ALboolean __result = alIsBuffer(buffer);
	return __result;
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalBufferData(JNIEnv *env, jclass clazz, jint buffer, jint format, jobject data, jint data_position, jint size, jint freq) {
	ALvoid *data_address = ((ALvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	alBufferData(buffer, format, data_address, size, freq);
}

static jint JNICALL Java_org_lwjgl_openal_AL10_nalGetBufferi(JNIEnv *env, jclass clazz, jint buffer, jint pname) {
	ALint __result;
	alGetBufferi(buffer, pname, &__result);
	return __result;
}

static jfloat JNICALL Java_org_lwjgl_openal_AL10_nalGetBufferf(JNIEnv *env, jclass clazz, jint buffer, jint pname) {
	ALfloat __result;
	alGetBufferf(buffer, pname, &__result);
	return __result;
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceQueueBuffers(JNIEnv *env, jclass clazz, jint source, jint n, jobject buffers, jint buffers_position) {
	ALuint *buffers_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	alSourceQueueBuffers(source, n, buffers_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalSourceUnqueueBuffers(JNIEnv *env, jclass clazz, jint source, jint n, jobject buffers, jint buffers_position) {
	ALuint *buffers_address = ((ALuint *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	alSourceUnqueueBuffers(source, n, buffers_address);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalDistanceModel(JNIEnv *env, jclass clazz, jint value) {
	alDistanceModel(value);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalDopplerFactor(JNIEnv *env, jclass clazz, jfloat value) {
	alDopplerFactor(value);
}

static void JNICALL Java_org_lwjgl_openal_AL10_nalDopplerVelocity(JNIEnv *env, jclass clazz, jfloat value) {
	alDopplerVelocity(value);
}

JNIEXPORT void JNICALL Java_org_lwjgl_openal_AL10_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nalEnable", "(I)V", (void *)&Java_org_lwjgl_openal_AL10_nalEnable, "alEnable", (void *)&alEnable},
		{"nalDisable", "(I)V", (void *)&Java_org_lwjgl_openal_AL10_nalDisable, "alDisable", (void *)&alDisable},
		{"nalIsEnabled", "(I)Z", (void *)&Java_org_lwjgl_openal_AL10_nalIsEnabled, "alIsEnabled", (void *)&alIsEnabled},
		{"nalGetBoolean", "(I)Z", (void *)&Java_org_lwjgl_openal_AL10_nalGetBoolean, "alGetBoolean", (void *)&alGetBoolean},
		{"nalGetInteger", "(I)I", (void *)&Java_org_lwjgl_openal_AL10_nalGetInteger, "alGetInteger", (void *)&alGetInteger},
		{"nalGetFloat", "(I)F", (void *)&Java_org_lwjgl_openal_AL10_nalGetFloat, "alGetFloat", (void *)&alGetFloat},
		{"nalGetDouble", "(I)D", (void *)&Java_org_lwjgl_openal_AL10_nalGetDouble, "alGetDouble", (void *)&alGetDouble},
		{"nalGetIntegerv", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalGetIntegerv, "alGetIntegerv", (void *)&alGetIntegerv},
		{"nalGetFloatv", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalGetFloatv, "alGetFloatv", (void *)&alGetFloatv},
		{"nalGetDoublev", "(ILjava/nio/DoubleBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalGetDoublev, "alGetDoublev", (void *)&alGetDoublev},
		{"alGetString", "(I)Ljava/lang/String;", (void *)&Java_org_lwjgl_openal_AL10_alGetString, "alGetString", (void *)&alGetString},
		{"alGetError", "()I", (void *)&Java_org_lwjgl_openal_AL10_alGetError, "alGetError", (void *)&alGetError},
		{"nalIsExtensionPresent", "(Ljava/lang/String;)Z", (void *)&Java_org_lwjgl_openal_AL10_nalIsExtensionPresent, "alIsExtensionPresent", (void *)&alIsExtensionPresent},
		{"nalGetEnumValue", "(Ljava/lang/String;)I", (void *)&Java_org_lwjgl_openal_AL10_nalGetEnumValue, "alGetEnumValue", (void *)&alGetEnumValue},
		{"nalListeneri", "(II)V", (void *)&Java_org_lwjgl_openal_AL10_nalListeneri, "alListeneri", (void *)&alListeneri},
		{"nalListenerf", "(IF)V", (void *)&Java_org_lwjgl_openal_AL10_nalListenerf, "alListenerf", (void *)&alListenerf},
		{"nalListenerfv", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalListenerfv, "alListenerfv", (void *)&alListenerfv},
		{"nalListener3f", "(IFFF)V", (void *)&Java_org_lwjgl_openal_AL10_nalListener3f, "alListener3f", (void *)&alListener3f},
		{"nalGetListeneri", "(I)I", (void *)&Java_org_lwjgl_openal_AL10_nalGetListeneri, "alGetListeneri", (void *)&alGetListeneri},
		{"nalGetListenerf", "(I)F", (void *)&Java_org_lwjgl_openal_AL10_nalGetListenerf, "alGetListenerf", (void *)&alGetListenerf},
		{"nalGetListenerfv", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalGetListenerfv, "alGetListenerfv", (void *)&alGetListenerfv},
		{"nalGenSources", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalGenSources, "alGenSources", (void *)&alGenSources},
		{"nalDeleteSources", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalDeleteSources, "alDeleteSources", (void *)&alDeleteSources},
		{"nalIsSource", "(I)Z", (void *)&Java_org_lwjgl_openal_AL10_nalIsSource, "alIsSource", (void *)&alIsSource},
		{"nalSourcei", "(III)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourcei, "alSourcei", (void *)&alSourcei},
		{"nalSourcef", "(IIF)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourcef, "alSourcef", (void *)&alSourcef},
		{"nalSourcefv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourcefv, "alSourcefv", (void *)&alSourcefv},
		{"nalSource3f", "(IIFFF)V", (void *)&Java_org_lwjgl_openal_AL10_nalSource3f, "alSource3f", (void *)&alSource3f},
		{"nalGetSourcei", "(II)I", (void *)&Java_org_lwjgl_openal_AL10_nalGetSourcei, "alGetSourcei", (void *)&alGetSourcei},
		{"nalGetSourcef", "(II)F", (void *)&Java_org_lwjgl_openal_AL10_nalGetSourcef, "alGetSourcef", (void *)&alGetSourcef},
		{"nalGetSourcefv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalGetSourcefv, "alGetSourcefv", (void *)&alGetSourcefv},
		{"nalSourcePlayv", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourcePlayv, "alSourcePlayv", (void *)&alSourcePlayv},
		{"nalSourcePausev", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourcePausev, "alSourcePausev", (void *)&alSourcePausev},
		{"nalSourceStopv", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourceStopv, "alSourceStopv", (void *)&alSourceStopv},
		{"nalSourceRewindv", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourceRewindv, "alSourceRewindv", (void *)&alSourceRewindv},
		{"nalSourcePlay", "(I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourcePlay, "alSourcePlay", (void *)&alSourcePlay},
		{"nalSourcePause", "(I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourcePause, "alSourcePause", (void *)&alSourcePause},
		{"nalSourceStop", "(I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourceStop, "alSourceStop", (void *)&alSourceStop},
		{"nalSourceRewind", "(I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourceRewind, "alSourceRewind", (void *)&alSourceRewind},
		{"nalGenBuffers", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalGenBuffers, "alGenBuffers", (void *)&alGenBuffers},
		{"nalDeleteBuffers", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalDeleteBuffers, "alDeleteBuffers", (void *)&alDeleteBuffers},
		{"nalIsBuffer", "(I)Z", (void *)&Java_org_lwjgl_openal_AL10_nalIsBuffer, "alIsBuffer", (void *)&alIsBuffer},
		{"nalBufferData", "(IILjava/nio/Buffer;III)V", (void *)&Java_org_lwjgl_openal_AL10_nalBufferData, "alBufferData", (void *)&alBufferData},
		{"nalGetBufferi", "(II)I", (void *)&Java_org_lwjgl_openal_AL10_nalGetBufferi, "alGetBufferi", (void *)&alGetBufferi},
		{"nalGetBufferf", "(II)F", (void *)&Java_org_lwjgl_openal_AL10_nalGetBufferf, "alGetBufferf", (void *)&alGetBufferf},
		{"nalSourceQueueBuffers", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourceQueueBuffers, "alSourceQueueBuffers", (void *)&alSourceQueueBuffers},
		{"nalSourceUnqueueBuffers", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_openal_AL10_nalSourceUnqueueBuffers, "alSourceUnqueueBuffers", (void *)&alSourceUnqueueBuffers},
		{"nalDistanceModel", "(I)V", (void *)&Java_org_lwjgl_openal_AL10_nalDistanceModel, "alDistanceModel", (void *)&alDistanceModel},
		{"nalDopplerFactor", "(F)V", (void *)&Java_org_lwjgl_openal_AL10_nalDopplerFactor, "alDopplerFactor", (void *)&alDopplerFactor},
		{"nalDopplerVelocity", "(F)V", (void *)&Java_org_lwjgl_openal_AL10_nalDopplerVelocity, "alDopplerVelocity", (void *)&alDopplerVelocity}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extal_InitializeClass(env, clazz, num_functions, functions);
}
