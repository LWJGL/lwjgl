#include "tools.h"
#include <CoreServices/CoreServices.h>

MPCriticalRegionID critical_region;

void throwException(JNIEnv* env, const char* msg) {
	jclass cls = env->FindClass("java/lang/Exception");
	env->ThrowNew(cls, msg);
}

bool initLock(JNIEnv* env) {
	OSStatus err = MPCreateCriticalRegion(&critical_region);
	if (err != noErr) {
		throwException(env, "Could not init lock");
		return false;
	}
	return true;
}

void destroyLock(void) {
	OSStatus err = MPDeleteCriticalRegion(critical_region);
	if (err != noErr) {
#ifdef _DEBUG
		printf("Could not delete lock\n");
#endif
	}
}

void lockLWJGL(void) {
	OSStatus err = MPEnterCriticalRegion(critical_region, kDurationForever);
	if (err != noErr) {
#ifdef _DEBUG
		printf("Could not lock\n");
#endif
	}
}

void unlockLWJGL(void) {
	OSStatus err = MPExitCriticalRegion(critical_region);
	if (err != noErr) {
#ifdef _DEBUG
		printf("Could not unlock\n");
#endif
	}
}
