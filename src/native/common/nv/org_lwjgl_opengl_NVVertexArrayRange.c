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

// ----------------------------------
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.NVVertexArrayRange
// ----------------------------------

#include "extgl.h"
#include "common_tools.h"

typedef void (APIENTRY * glFlushVertexArrayRangeNVPROC) (void);
typedef void (APIENTRY * glVertexArrayRangeNVPROC) (GLsizei size, const GLvoid *pointer);

static glFlushVertexArrayRangeNVPROC glFlushVertexArrayRangeNV;
static glVertexArrayRangeNVPROC glVertexArrayRangeNV;

#ifdef _WIN32

typedef void * (APIENTRY * wglAllocateMemoryNVPROC) (GLsizei size, GLfloat readFrequency, GLfloat writeFrequency, GLfloat priority);
typedef void (APIENTRY * wglFreeMemoryNVPROC) (void *pointer);

static wglAllocateMemoryNVPROC wglAllocateMemoryNV;
static wglFreeMemoryNVPROC wglFreeMemoryNV;

#endif /* WIN32 */

#ifdef _X11

typedef void * (APIENTRY * glXAllocateMemoryNVPROC) (GLsizei size, GLfloat readFrequency, GLfloat writeFrequency, GLfloat priority);
typedef void (APIENTRY * glXFreeMemoryNVPROC) (void *pointer);

static glXAllocateMemoryNVPROC glXAllocateMemoryNV;
static glXFreeMemoryNVPROC glXFreeMemoryNV;

#endif /* X11 */

/*
 * Class:	org.lwjgl.opengl.NVVertexArrayRange
 * Method:	nglVertexArrayRangeNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexArrayRange_nglVertexArrayRangeNV
	(JNIEnv * env, jclass clazz, jint size, jobject pPointer, jint pPointer_offset)
{
	GLvoid *pPointer_ptr = (GLvoid *)((GLubyte *)(*env)->GetDirectBufferAddress(env, pPointer) + pPointer_offset);
	glVertexArrayRangeNV(size, pPointer_ptr);
}

/*
 * Class:	org.lwjgl.opengl.NVVertexArrayRange
 * Method:	glFlushVertexArrayRangeNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVVertexArrayRange_glFlushVertexArrayRangeNV
	(JNIEnv * env, jclass clazz)
{
	glFlushVertexArrayRangeNV();
}

#ifdef _X11
static jobject JNICALL Java_org_lwjgl_opengl_NVVertexArrayRange_glXAllocateMemoryNV
	(JNIEnv * env, jclass clazz, jint size, jfloat readFrequency, jfloat writeFrequency, jfloat priority)
{
	void *mem_address = glXAllocateMemoryNV((GLint) size, (GLfloat)readFrequency, (GLfloat)writeFrequency, (GLfloat)priority);
	return safeNewBuffer(env, mem_address, size);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexArrayRange_glXFreeMemoryNV
	(JNIEnv * env, jclass clazz, jobject pointer)
{
        void *address = (void *)(*env)->GetDirectBufferAddress(env, pointer);
        glXFreeMemoryNV(address);
}
#endif

#ifdef _WIN32
static jobject JNICALL Java_org_lwjgl_opengl_NVVertexArrayRange_wglAllocateMemoryNV
	(JNIEnv * env, jclass clazz, jint size, jfloat readFrequency, jfloat writeFrequency, jfloat priority)
{
        void *mem_address = wglAllocateMemoryNV((GLint)size, (GLfloat)readFrequency, (GLfloat)writeFrequency, (GLfloat)priority);
        return safeNewBuffer(env, mem_address, size);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexArrayRange_wglFreeMemoryNV
	(JNIEnv * env, jclass clazz, jobject pointer)
{
        void *address = (void *)(*env)->GetDirectBufferAddress(env, pointer);
        wglFreeMemoryNV(address);
}
#endif

#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVVertexArrayRange_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglVertexArrayRangeNV", "(ILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_NVVertexArrayRange_nglVertexArrayRangeNV, "glVertexArrayRangeNV", (void*)&glVertexArrayRangeNV},
		{"glFlushVertexArrayRangeNV", "()V", (void*)&Java_org_lwjgl_opengl_NVVertexArrayRange_glFlushVertexArrayRangeNV, "glFlushVertexArrayRangeNV", (void*)&glFlushVertexArrayRangeNV},
#ifdef _X11
		{"glXAllocateMemoryNV", "(IFFF)Ljava/nio/ByteBuffer;", (void*)&Java_org_lwjgl_opengl_NVVertexArrayRange_glXAllocateMemoryNV, "glXAllocateMemoryNV", (void*)&glXAllocateMemoryNV},
		{"glXFreeMemoryNV", "(Ljava/nio/ByteBuffer;)V", (void*)&Java_org_lwjgl_opengl_NVVertexArrayRange_glXFreeMemoryNV, "glXFreeMemoryNV", (void*)&glXFreeMemoryNV},
#endif
#ifdef _WIN32
		{"wglAllocateMemoryNV", "(IFFF)Ljava/nio/ByteBuffer;", (void*)&Java_org_lwjgl_opengl_NVVertexArrayRange_wglAllocateMemoryNV, "wglAllocateMemoryNV", (void*)&wglAllocateMemoryNV},
		{"wglFreeMemoryNV", "(Ljava/nio/ByteBuffer;)V", (void*)&Java_org_lwjgl_opengl_NVVertexArrayRange_wglFreeMemoryNV, "wglFreeMemoryNV", (void*)&wglFreeMemoryNV}
#endif
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
#ifdef __cplusplus
}
#endif

