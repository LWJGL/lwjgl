#include <CoreServices/CoreServices.h>
#include "tools.h"
#include "common_tools.h"

MPCriticalRegionID critical_region;

bool registerHandler(JNIEnv* env, WindowRef win_ref, EventHandlerProcPtr func, UInt32 event_class, UInt32 event_kind) {
	EventTypeSpec event_type;
	EventHandlerUPP handler_upp = NewEventHandlerUPP(func);
	event_type.eventClass = event_class;
	event_type.eventKind  = event_kind;
	OSStatus err = InstallWindowEventHandler(win_ref, handler_upp, 1, &event_type, NULL, NULL);
	DisposeEventHandlerUPP(handler_upp);
	if (noErr != err) {
		throwException(env, "Could not register window event handler");
		return true;
	}
        return false;
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
