#ifndef TOOLS_H
#define TOOLS_H

#include <JavaVM/jni.h>

extern void throwException(JNIEnv* env, const char* msg);
extern bool initLock(JNIEnv* env);
extern void destroyLock(void);
extern void lock(void);
extern void unlock(void);

#endif
