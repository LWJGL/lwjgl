/* 
 * Copyright (c) 2002-2008 LWJGL Project
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
 * $Id: org_lwjgl_input_Keyboard.c 2385 2006-06-23 16:45:21Z elias_naur $
 *
 * @author elias_naue <elias_naur@users.sourceforge.net>
 * @version $Revision: 2385 $
 */

#include "common_tools.h"
#include "dinputhelper.h"
#include <jni.h>

HRESULT objectCallback(JNIEnv *env, jobject enumerator, jint object_type, const char *tszName) {
	jstring name;
	jclass enum_class;
	jmethodID nextObject_method;
	jboolean should_continue;

	name = NewStringNativeWithLength(env, tszName, strlen(tszName));
	if (name == NULL)
		return false;
	enum_class = (*env)->GetObjectClass(env, enumerator);
	if (enum_class == NULL)
		return false;
	nextObject_method = (*env)->GetMethodID(env, enum_class, "nextObject", "(ILjava/lang/String;)Z");
	if (nextObject_method == NULL)
		return false;
	should_continue = (*env)->CallBooleanMethod(env, enumerator, nextObject_method, object_type, name);
	(*env)->DeleteLocalRef(env, name);
	return should_continue == JNI_TRUE ? true : false;
}

