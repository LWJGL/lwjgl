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

#ifndef _EXT_FMOD_H
#define _EXT_FMOD_H

#include <jni.h>
#include "../common_tools.h"

#include "fmoddyn.h"
#include "fmod_errors.h"

void fmod_create(JNIEnv *env, char*);
void fmod_destroy();

extern FMOD_INSTANCE * fmod_instance;

// Setup for callback. The callbacks don't have access to a JNIEnv pointer, so we have to provide
// one. Unfortunately we cannot cache one, since JNIEnv er thread local copies. We can however
// aquire one, using AttachCurrent<ThreadAsDaemon>. However we need a VM instance for that.
// so we supply 1 VM instance for use by threads (in common_tools) (VM instances are shared across threads), and
// 1 JNIEnv pointer per thread. At this time, 2 threads have been identified - the Stream thread
// and the mixer thread.
extern JNIEnv *mixer_jnienv;
extern JNIEnv *stream_jnienv;

// FMusic cached fields
extern jmethodID music_instcallback;
extern jmethodID music_ordercallback;
extern jmethodID music_rowcallback;
extern jmethodID music_zxxcallback;
extern jclass fmusic;

#endif
