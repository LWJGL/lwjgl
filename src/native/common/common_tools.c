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

#include <jni.h>
#include <stdlib.h>

#ifdef WIN32
#include <wtypes.h>
#endif

#include "common_tools.h"

static bool debug = false;
static const char* VERSION = "0.94";
static JavaVM *jvm;

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
	return NewStringNative(env, VERSION);
}

bool isDebugEnabled(void) {
	return debug;
}

void setDebugEnabled(bool enable) {
	debug = enable;
}

void printfDebugJava(JNIEnv *env, const char *format, ...) {
	#define BUFFER_SIZE 4000
	static char buffer[BUFFER_SIZE];
	jstring str;
	jclass org_lwjgl_Sys_class;
	jmethodID log_method;
	va_list ap;
	va_start(ap, format);
	if (isDebugEnabled()) {
		#ifdef WIN32
		_vsnprintf(buffer, BUFFER_SIZE, format, ap);
		#else
		vsnprintf(buffer, BUFFER_SIZE, format, ap);
		#endif
		buffer[BUFFER_SIZE - 1] = '\0';
		str = (*env)->NewStringUTF(env, buffer);
		org_lwjgl_Sys_class = (*env)->FindClass(env, "org/lwjgl/Sys");
		log_method = (*env)->GetStaticMethodID(env, org_lwjgl_Sys_class, "log", "(Ljava/lang/String;)V");
		(*env)->CallStaticVoidMethod(env, org_lwjgl_Sys_class, log_method, str);
	}
	va_end(ap);
}

void printfDebug(const char *format, ...) {
	va_list ap;
	va_start(ap, format);
	if (isDebugEnabled())
		vfprintf(stderr, format, ap);
	va_end(ap);
}

int getElementCapacity(event_queue_t *queue) {
	return queue->limit - queue->position;
}

void initEventQueue(event_queue_t *queue, int event_size) {
	queue->position = 0;
	queue->limit = EVENT_BUFFER_SIZE;
	queue->event_size = event_size;
}

bool putEvent(event_queue_t *queue, jint *event) {
	int i;
	if (getElementCapacity(queue) < queue->event_size) {
		printfDebug("Event buffer overflow!\n");
		return false;
	}
	for (i = 0; i < queue->event_size; i++) {
		queue->input_event_buffer[queue->position] = event[i];
		queue->position++;
	}
	return true;
}

static void flip(event_queue_t *queue) {
	queue->limit = queue->position;
	queue->position = 0;
}

static void compact(event_queue_t *queue) {
	int new_position = 0;
	while (getElementCapacity(queue) > 0) {
		queue->input_event_buffer[new_position] = queue->input_event_buffer[queue->position];
		queue->position++;
		new_position++;
	}
	queue->position = new_position;
	queue->limit = EVENT_BUFFER_SIZE;
}

static void copyEvent(event_queue_t *queue, jint *output_event_buffer, int output_index) {
	int i;
	for (i = 0; i < queue->event_size; i++) {
		output_event_buffer[output_index] = queue->input_event_buffer[queue->position];
		queue->position++;
		output_index++;
	}
}

int copyEvents(event_queue_t *queue, jint *output_event_buffer, int buffer_size) {
	int num_events = 0;
	int index = 0;
	flip(queue);
	while (index + queue->event_size <= buffer_size && getElementCapacity(queue) >= queue->event_size) {
		copyEvent(queue, output_event_buffer, index);
		num_events++;
		index += queue->event_size;
	}
	compact(queue);
	return num_events;
}

static void throwGeneralException(JNIEnv * env, const char *exception_name, const char * err) {
	jclass cls;

	if ((*env)->ExceptionCheck(env) == JNI_TRUE)
		return; // The JVM crashes if we try to throw two exceptions from one native call
	cls = (*env)->FindClass(env, exception_name);
	(*env)->ThrowNew(env, cls, err);
	(*env)->DeleteLocalRef(env, cls);
}

void throwOpenALException(JNIEnv * env, const char * err) {
	throwGeneralException(env, "org/lwjgl/openal/OpenALException", err);
}

void throwFMODException(JNIEnv * env, const char * err) {
	throwGeneralException(env, "org/lwjgl/fmod3/FMODException", err);
}

void throwException(JNIEnv * env, const char * err) {
	throwGeneralException(env, "org/lwjgl/LWJGLException", err);
}

// retrieves the locale-specific C string
char * GetStringNativeChars(JNIEnv *env, jstring jstr) { 
  jbyteArray bytes = 0; 
  jthrowable exc; 
  char *result = 0; 
  jclass jcls_str;
  jmethodID MID_String_getBytes;

  /* out of memory error? */ 
  if ((*env)->EnsureLocalCapacity(env, 2) < 0) { 
    return 0; 
  } 

  // aquire getBytes method
  jcls_str = (*env)->FindClass(env, "java/lang/String"); 
  MID_String_getBytes = (*env)->GetMethodID(env, jcls_str, "getBytes", "()[B"); 

  // get the bytes
  bytes = (jbyteArray) (*env)->CallObjectMethod(env, jstr, MID_String_getBytes); 
  exc = (*env)->ExceptionOccurred(env); 

  // if no exception occured while getting bytes - continue
  if (!exc) { 
    jint len = (*env)->GetArrayLength(env, bytes); 
    result = (char *) malloc(len + 1); 
    if (result == 0) { 
      throwGeneralException(env, "java/lang/OutOfMemoryError", NULL); 
      (*env)->DeleteLocalRef(env, bytes); 
      return 0; 
    } 
    (*env)->GetByteArrayRegion(env, bytes, 0, len, (jbyte *) result); 
    result[len] = 0; /* NULL-terminate */ 
  } else { 
    (*env)->DeleteLocalRef(env, exc); 
  } 
  (*env)->DeleteLocalRef(env, bytes); 
  return (char*) result;
}

// creates locale specific string
jstring NewStringNative(JNIEnv *env, const char *str) { 
  jclass jcls_str;
  jmethodID jmethod_str;
  jstring result; 
  jbyteArray bytes;
  int len; 
  if (str==NULL) { 
    return NULL; 
  } 
  
  jcls_str = (*env)->FindClass(env,"java/lang/String"); 
  jmethod_str = (*env)->GetMethodID(env,jcls_str, "<init>", "([B)V"); 

  bytes = 0; 

  if ((*env)->EnsureLocalCapacity(env,2) < 0) { 
    return NULL; /* out of memory error */ 
  } 
  len = strlen(str); 
  bytes = (*env)->NewByteArray(env,len); 
  if (bytes != NULL) { 
    (*env)->SetByteArrayRegion(env,bytes, 0, len, (jbyte *)str); 
    result = (jstring)(*env)->NewObject(env,jcls_str, jmethod_str, bytes); 
    (*env)->DeleteLocalRef(env,bytes); 
    return result; 
  } /* else fall through */ 
  return NULL; 
}

bool ext_InitializeFunctions(ExtGetProcAddressPROC gpa, int num_functions, ExtFunction *functions) {
	int i;
	void **ext_function_pointer_pointer;
	for (i = 0; i < num_functions; i++) {
		ExtFunction *function = functions + i;
		if (function->ext_function_name != NULL) {
			void *ext_func_pointer = gpa(function->ext_function_name);
			if (ext_func_pointer == NULL)
				return false;
			ext_function_pointer_pointer = function->ext_function_pointer;
			*ext_function_pointer_pointer = ext_func_pointer;
		}
	}
	return true;
}

void ext_InitializeClass(JNIEnv *env, jclass clazz, ExtGetProcAddressPROC gpa, int num_functions, JavaMethodAndExtFunction *functions) {
	JNINativeMethod *methods;
	JavaMethodAndExtFunction *function;
	void *ext_func_pointer;
	void **ext_function_pointer_pointer;
	JNINativeMethod *method;
	int i;
	if (clazz == NULL) {
		throwException(env, "Null class");
		return;
	}
	methods = (JNINativeMethod *)malloc(num_functions*sizeof(JNINativeMethod));
	for (i = 0; i < num_functions; i++) {
		function = functions + i;
		if (function->ext_function_name != NULL) {
			ext_func_pointer = gpa(function->ext_function_name);
			if (ext_func_pointer == NULL) {
				free(methods);
				throwException(env, "Missing driver symbols");
				return;
			}
			ext_function_pointer_pointer = function->ext_function_pointer;
			*ext_function_pointer_pointer = ext_func_pointer;
		}
		method = methods + i;
		method->name = function->method_name;
		method->signature = function->signature;
		method->fnPtr = function->method_pointer;
	}
	(*env)->RegisterNatives(env, clazz, methods, num_functions);
	free(methods);
}

bool getBooleanProperty(JNIEnv *env, const char* propertyName) {
  jstring property = NewStringNative(env, propertyName);
  jclass booleanClass = (*env)->FindClass(env, "java/lang/Boolean");
  jmethodID getBoolean = (*env)->GetStaticMethodID(env, booleanClass, "getBoolean", "(Ljava/lang/String;)Z");
  return (*env)->CallStaticBooleanMethod(env, booleanClass, getBoolean, property) ? true : false;
}

JavaVM *getJVM() {
	return jvm;
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
  jvm = vm;
  return JNI_VERSION_1_4;
}

JNIEXPORT void JNICALL JNI_OnUnload(JavaVM *vm, void *reserved) {
}
