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

#include <stdlib.h>
#include "common_tools.h"

static bool debug = false;
static const char* VERSION = "0.9pre";
JavaVM *jvm;

void initAttribList(attrib_list_t *list) {
	list->current_index = 0;
}

void putAttrib(attrib_list_t *list, int attrib) {
	if (list->current_index == ATTRIB_LIST_SIZE) {
		printfDebug("Ignoring attrib %d: attrib list size too small", attrib);
		return;
	}
	list->attribs[list->current_index] = attrib;
	list->current_index++;
}

jstring getVersionString(JNIEnv *env) {
	return env->NewStringUTF(VERSION);
}

bool isDebugEnabled(void) {
	return debug;
}

void setDebugEnabled(bool enable) {
	debug = enable;
}

void printfDebug(const char *format, ...) {
	va_list ap;
	va_start(ap, format);
	if (isDebugEnabled())
		vfprintf(stderr, format, ap);
	va_end(ap);
}

static void incListStart(event_queue_t *queue) {
	queue->list_start = (queue->list_start + 1)%EVENT_BUFFER_SIZE;
}

void initEventQueue(event_queue_t *event_queue) {
	event_queue->list_start = 0;
	event_queue->list_end = 0;
}

void putEventElement(event_queue_t *queue, unsigned char byte) {
	int next_index = (queue->list_end + 1)%EVENT_BUFFER_SIZE;
	if (next_index == queue->list_start) {
		printfDebug("Event buffer overflow!\n");
		return;
	}
	queue->input_event_buffer[queue->list_end] = byte;
	queue->list_end = next_index;
}

static bool hasMoreEvents(event_queue_t *queue) {
	return queue->list_start != queue->list_end;
}

static void copyEvent(event_queue_t *queue, unsigned char *output_event_buffer, int output_index, int event_size) {
	for (int i = 0; i < event_size; i++) {
		output_event_buffer[output_index] = queue->input_event_buffer[queue->list_start];
                incListStart(queue);
		output_index++;
	}
}

int copyEvents(event_queue_t *event_queue, unsigned char *output_event_buffer, int buffer_size, int event_size) {
	int num_events = 0;
	int index = 0;
	while (index + event_size <= buffer_size && hasMoreEvents(event_queue)) {
		copyEvent(event_queue, output_event_buffer, index, event_size);
		num_events++;
		index += event_size;
	}
	return num_events;
}

static void throwGeneralException(JNIEnv * env, const char *exception_name, const char * err) {
	if (env->ExceptionCheck() == JNI_TRUE)
		return; // The JVM crashes if we try to throw two exceptions from one native call
	jclass cls = env->FindClass(exception_name);
	env->ThrowNew(cls, err);
	env->DeleteLocalRef(cls);
}

void throwOpenALException(JNIEnv * env, const char * err) {
	throwGeneralException(env, "org/lwjgl/openal/OpenALException", err);
}

void throwFMODException(JNIEnv * env, const char * err) {
	throwGeneralException(env, "org/lwjgl/fmod/FMODException", err);
}

void throwException(JNIEnv * env, const char * err) {
	throwGeneralException(env, "org/lwjgl/LWJGLException", err);
}

bool ext_InitializeFunctions(ExtGetProcAddressPROC gpa, int num_functions, ExtFunction *functions) {
	for (int i = 0; i < num_functions; i++) {
		ExtFunction *function = functions + i;
		if (function->ext_function_name != NULL) {
			void *ext_func_pointer = gpa(function->ext_function_name);
			if (ext_func_pointer == NULL)
				return false;
			void **ext_function_pointer_pointer = function->ext_function_pointer;
			*ext_function_pointer_pointer = ext_func_pointer;
		}
	}
	return true;
}

bool ext_InitializeClass(JNIEnv *env, jclass clazz, ExtGetProcAddressPROC gpa, int num_functions, JavaMethodAndExtFunction *functions) {
	if (clazz == NULL) {
		throwException(env, "Null class");
		return false;
	}
	JNINativeMethod *methods = (JNINativeMethod *)malloc(num_functions*sizeof(JNINativeMethod));
	for (int i = 0; i < num_functions; i++) {
		JavaMethodAndExtFunction *function = functions + i;
		if (function->ext_function_name != NULL) {
			void *ext_func_pointer = gpa(function->ext_function_name);
			if (ext_func_pointer == NULL) {
				free(methods);
				throwException(env, "Missing driver symbols");
				return false;
			}
			void **ext_function_pointer_pointer = function->ext_function_pointer;
			*ext_function_pointer_pointer = ext_func_pointer;
		}
		JNINativeMethod *method = methods + i;
		method->name = function->method_name;
		method->signature = function->signature;
		method->fnPtr = function->method_pointer;
	}
	jint result = env->RegisterNatives(clazz, methods, num_functions);
	free(methods);
	if (result != 0) {
		return false;
	} else
		return true;
}

bool getBooleanProperty(JNIEnv *env, const char* propertyName) {
  jstring property = env->NewStringUTF(propertyName);
  jclass booleanClass = env->FindClass("java/lang/Boolean");
  jmethodID getBoolean = env->GetStaticMethodID(booleanClass, "getBoolean", "(Ljava/lang/String;)Z");
  return env->CallStaticBooleanMethod(booleanClass, getBoolean, property)? true : false;
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
  jvm = vm;
  return JNI_VERSION_1_4;
}
JNIEXPORT void JNICALL JNI_OnUnload(JavaVM *vm, void *reserved) {
}
