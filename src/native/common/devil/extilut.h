#ifndef __EXTILUT_H__
#define __EXTILUT_H__

#include "devil-common.h"

extern bool extilut_Open(JNIEnv *env, jobjectArray ilPaths);
extern void extilut_Close(void);
extern void extilut_InitializeClass(JNIEnv *env, jclass clazz, int num_functions, JavaMethodAndExtFunction *functions);

#endif

