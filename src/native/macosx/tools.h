#ifndef TOOLS_H
#define TOOLS_H

#include <JavaVM/jni.h>
#include <Carbon/Carbon.h>

#define lock() {lockLWJGL();
#define unlock() unlockLWJGL();}

extern void throwException(JNIEnv* env, const char* msg);
extern bool registerHandler(JNIEnv* env, WindowRef win_ref, EventHandlerProcPtr func, UInt32 event_kind);
extern bool initLock(JNIEnv* env);
extern void destroyLock(void);
extern void lockLWJGL(void);
extern void unlockLWJGL(void);

#endif
