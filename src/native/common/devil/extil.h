#ifndef __EXTIL_H__
#define __EXTIL_H__

#include <jni.h>
#include <stdio.h>
#include <string.h>
#include <IL/il.h>
#include <IL/ilu.h>
#include "org_lwjgl_devil_IL.h"
#include "org_lwjgl_devil_ILU.h"
#include "common_tools.h"


/*-----------------------------------------*/
/*-----------------------------------------*/

#if defined(_WIN32) && !defined(APIENTRY)
#define WIN32_LEAN_AND_MEAN 1
#include <windows.h>

#endif

#define __ilext_h_
#define __ILEXT_H_
#define __il_h_
#define __IL_H__

#include <string.h>

#ifndef APIENTRY
#define APIENTRY
#endif

#include "common_tools.h"

#ifdef __cplusplus
extern "C" {
#endif

/* initializes everything, call this right after the rc is created. the function returns 0 if successful */
extern bool extil_Open(JNIEnv *env);
extern void extil_Close(void);
extern void extil_InitializeClass(JNIEnv *env, jclass clazz, int num_functions, JavaMethodAndExtFunction *functions);
extern bool extil_InitializeFunctions(int num_functions, ExtFunction *functions);

#ifdef __cplusplus
}
#endif

#endif /* __EXTIL_H__ */
