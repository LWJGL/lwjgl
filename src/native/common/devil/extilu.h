#ifndef __EXTILU_H__
#define __EXTILU_H__

#include "devil-common.h"

extern bool extilu_Open(JNIEnv *env, jobjectArray ilPaths);
extern void extilu_Close(void);
extern void extilu_InitializeClass(JNIEnv *env, jclass clazz, int num_functions, JavaMethodAndExtFunction *functions);

#endif

