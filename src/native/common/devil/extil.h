#ifndef __EXTIL_H__
#define __EXTIL_H__

#include "devil-common.h"

extern bool extil_Open(JNIEnv *env, jobjectArray ilPaths);
extern void extil_Close(void);
extern void extil_InitializeClass(JNIEnv *env, jclass clazz, int num_functions, JavaMethodAndExtFunction *functions);

#endif