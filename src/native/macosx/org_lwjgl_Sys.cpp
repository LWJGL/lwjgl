/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
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
 * OSX system library.
 *
 * @author Gregory Pierce <me@gregorypierce.com>
 * @version $Revision$
 */

#include "org_lwjgl_Sys.h"

/*
 * Class:     org_lwjgl_Sys
 * Method:    getDirectBufferAddress
 * Signature: (Ljava/nio/Buffer;)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_Sys_getDirectBufferAddress
  (JNIEnv * env, jclass clazz, jobject buf)
{
	return (jint) env->GetDirectBufferAddress(buf);
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    createDirectBuffer
 * Signature: (II)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_Sys_createDirectBuffer
  (JNIEnv * env, jclass clazz, jint address, jint length)
{
	return env->NewDirectByteBuffer((void *)address, length);
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    getTimerResolution
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_Sys_getTimerResolution
  (JNIEnv * env, jclass clazz)
{
      return 0L;
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    getTime
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_Sys_getTime
  (JNIEnv * env, jclass clazz)
{
      return 0L;
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    setTime
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Sys_setTime
  (JNIEnv * env, jclass clazz, jlong startTime)
{
}

/*
 * Class:     org_lwjgl_Sys
 * Method:    setProcessPriority
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Sys_setProcessPriority
  (JNIEnv * env, jclass clazz, jint priority)
{
}
