/* 
 * Copyright (c) 2002-2004 LWJGL Project
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are 
 * met:
 * 
 * * Redistributions of source code must retain the above copyright 
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of 
 *   its contributors may be used to endorse or promote products derived 
 *   from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
/**
 * $Id$
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#ifndef _COMMON_TOOLS_H
#define _COMMON_TOOLS_H

#include <jni.h>
#include <string.h>
#include <stdlib.h>
#include "org_lwjgl_Sys.h"

#define EVENT_BUFFER_SIZE 256
#define ATTRIB_LIST_SIZE (256)

typedef struct {
	int event_size;
	int position;
	int limit;
	jint input_event_buffer[EVENT_BUFFER_SIZE];
} event_queue_t;

typedef struct {
	int current_index;
	int attribs[ATTRIB_LIST_SIZE];
} attrib_list_t;

#ifndef __cplusplus
#ifndef bool
typedef enum {false, true} bool;
#endif
#endif

#ifdef _WIN32
#define inline __inline
#endif

static inline void * safeGetBufferAddress(JNIEnv *env, jobject buffer, int offset) {
	if (buffer != NULL) {
#ifdef __cplusplus
		return (void *)((char *)env->GetDirectBufferAddress(buffer) + offset);
#else
		return (void *)((char *)(*env)->GetDirectBufferAddress(env, buffer) + offset);
#endif
	} else
		return NULL;
}

static inline jobject safeNewBuffer(JNIEnv *env, void *p, int size) {
	if (p != NULL) {
#ifdef __cplusplus
		return env->NewDirectByteBuffer(p, size);
#else
		return (*env)->NewDirectByteBuffer(env, p, size);
#endif
	} else
		return NULL;
}

static inline const void *offsetToPointer(jint offset) {
	return (const char *)NULL + offset;
}

typedef void *(* ExtGetProcAddressPROC) (const char *func_name);
typedef struct {
	char *method_name;
	char *signature;
	void *method_pointer;

	char *ext_function_name;
	void **ext_function_pointer;
} JavaMethodAndExtFunction;

typedef struct {
	char *ext_function_name;
	void **ext_function_pointer;
} ExtFunction;

#define NUMFUNCTIONS(x) (sizeof(x)/sizeof(JavaMethodAndExtFunction));

#ifdef __cplusplus
extern "C" {
#endif

extern JavaVM *getJVM();
extern void initAttribList(attrib_list_t *list);
extern void putAttrib(attrib_list_t *list, int attrib);

extern bool isDebugEnabled(void);
extern jstring getVersionString(JNIEnv *env);
extern void initEventQueue(event_queue_t *event_queue, int event_size);
extern int copyEvents(event_queue_t *event_queue, jint *output_event_buffer, int buffer_size);
extern bool putEvent(event_queue_t *queue, jint *event);
extern void throwException(JNIEnv *env, const char *msg);
extern void throwOpenALException(JNIEnv * env, const char * err);
extern void throwFMODException(JNIEnv * env, const char * err);
extern void setDebugEnabled(bool enable);
extern void printfDebugJava(JNIEnv *env, const char *format, ...);
extern void printfDebug(const char *format, ...);
extern bool getBooleanProperty(JNIEnv *env, const char* propertyName);
extern char * GetStringNativeChars(JNIEnv *env, jstring jstr);
extern jstring NewStringNative(JNIEnv *env, const char *str);

extern void ext_InitializeClass(JNIEnv *env, jclass clazz, ExtGetProcAddressPROC gpa, int num_functions, JavaMethodAndExtFunction *functions);
extern bool ext_InitializeFunctions(ExtGetProcAddressPROC gpa, int num_functions, ExtFunction *functions);

#ifdef __cplusplus
}
#endif

#endif

