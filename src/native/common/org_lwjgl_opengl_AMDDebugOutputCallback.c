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
 * JNI implementation of the AMD_debug_output function callback.
 *
 * @author Spasi
 */

#include <jni.h>
#include "common_tools.h"
#include "extgl.h"
#include "org_lwjgl_opengl_AMDDebugOutputCallback.h"

static void APIENTRY debugOutputCallback(GLuint id, GLenum category, GLenum severity, GLsizei length, const GLchar* message, GLvoid* userParam) {
    jclass callback_class;
	jmethodID callback_method;
	JNIEnv *env = getThreadEnv();
	if (env != NULL && !(*env)->ExceptionOccurred(env)) {
		callback_class = (*env)->FindClass(env, "org/lwjgl/opengl/AMDDebugOutputUtil");
		if ( callback_class != NULL ) {
			callback_method = (*env)->GetStaticMethodID(env, callback_class, "messageCallback", "(IIILjava/lang/String;Ljava/nio/ByteBuffer;)V");
			if ( callback_method != NULL ) {
				(*env)->CallStaticVoidMethod(env, callback_class, callback_method,
                            (jint)id,
				            (jint)category,
				            (jint)severity,
                            NewStringNativeWithLength(env, message, length),
				            NULL
                );
			}
		}
	}
}

JNIEXPORT jlong JNICALL Java_org_lwjgl_opengl_AMDDebugOutputCallback_getFunctionPointer(JNIEnv *env, jclass clazz) {
    return (jlong)(intptr_t)&debugOutputCallback;
}
