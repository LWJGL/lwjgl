#ifndef TOOLS_H
#define TOOLS_H

#include <JavaVM/jni.h>
#include <CoreFoundation/CoreFoundation.h>

#define lock() {lockLWJGL();
#define unlock() unlockLWJGL();}

extern bool getDictLong(CFDictionaryRef dict, CFStringRef key, long *key_value);

#endif
