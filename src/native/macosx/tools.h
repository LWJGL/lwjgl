#ifndef TOOLS_H
#define TOOLS_H

#include <JavaVM/jni.h>

extern void throwException(JNIEnv* env, const char* msg);

#endif
