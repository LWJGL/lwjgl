/*
* Copyright (c) 2002 Lightweight Java Game Library Project
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

// ----------------------------------
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.nv.NVVertexArrayRange
// ----------------------------------

#include "org_lwjgl_opengl_nv_NVVertexArrayRange.h"
#include "extgl.h"
#include "checkGLerror.h"

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

void extgl_InitNVVertexArrayRange(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_vertex_array_range)
		return;
	glFlushVertexArrayRangeNV = (glFlushVertexArrayRangeNVPROC) extgl_GetProcAddress("glFlushVertexArrayRangeNV");
	glVertexArrayRangeNV = (glVertexArrayRangeNVPROC) extgl_GetProcAddress("glVertexArrayRangeNV");
#ifdef _WIN32
	wglAllocateMemoryNV = (wglAllocateMemoryNVPROC) extgl_GetProcAddress("wglAllocateMemoryNV");
	wglFreeMemoryNV = (wglFreeMemoryNVPROC) extgl_GetProcAddress("wglFreeMemoryNV");
#endif /* WIN32 */
#ifdef _X11
	glXAllocateMemoryNV = (glXAllocateMemoryNVPROC) extgl_GetProcAddress("glXAllocateMemoryNV");
	glXFreeMemoryNV = (glXFreeMemoryNVPROC) extgl_GetProcAddress("glXFreeMemoryNV");
#endif /* X11 */
	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_vertex_array_range)
}
 
/*
 * Class:	org.lwjgl.opengl.nv.NVVertexArrayRange
 * Method:	nglVertexArrayRangeNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexArrayRange_nglVertexArrayRangeNV
	(JNIEnv * env, jclass clazz, jint size, jobject pPointer, jint pPointer_offset)
{
	CHECK_EXISTS(glVertexArrayRangeNV)
	GLvoid *pPointer_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pPointer) + pPointer_offset);
	glVertexArrayRangeNV(size, pPointer_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexArrayRange
 * Method:	glFlushVertexArrayRangeNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexArrayRange_glFlushVertexArrayRangeNV
	(JNIEnv * env, jclass clazz)
{
	CHECK_EXISTS(glFlushVertexArrayRangeNV)
	glFlushVertexArrayRangeNV();
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexArrayRange
 * Method:	glXAllocateMemoryNV
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_nv_NVVertexArrayRange_glXAllocateMemoryNV
	(JNIEnv * env, jclass clazz, jint size, jfloat readFrequency, jfloat writeFrequency, jfloat priority)
{
#ifdef _X11
	CHECK_EXISTS(glXAllocateMemoryNV)
	void *mem_address = glXAllocateMemoryNV((GLint) size, (GLfloat)readFrequency, (GLfloat)writeFrequency, (GLfloat)priority);
	return safeNewBuffer(env, mem_address, size);
#else
	CHECK_EXISTS(NULL)
	return NULL;
#endif
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexArrayRange
 * Method:	glXFreeMemoryNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexArrayRange_glXFreeMemoryNV
	(JNIEnv * env, jclass clazz, jobject pointer)
{
#ifdef _X11
        void *address = (void *)env->GetDirectBufferAddress(pointer);
        glXFreeMemoryNV(address);
#else
        CHECK_EXISTS(NULL)
#endif
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexArrayRange
 * Method:	wglAllocateMemoryNV
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_nv_NVVertexArrayRange_wglAllocateMemoryNV
	(JNIEnv * env, jclass clazz, jint size, jfloat readFrequency, jfloat writeFrequency, jfloat priority)
{
#ifdef _WIN32
        CHECK_EXISTS(wglAllocateMemoryNV)
        void *mem_address = wglAllocateMemoryNV((GLint)size, (GLfloat)readFrequency, (GLfloat)writeFrequency, (GLfloat)priority);
        return safeNewBuffer(env, mem_address, size);
#else
        CHECK_EXISTS(NULL)
        return NULL;
#endif
}

/*
 * Class:	org.lwjgl.opengl.nv.NVVertexArrayRange
 * Method:	wglFreeMemoryNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_nv_NVVertexArrayRange_wglFreeMemoryNV
	(JNIEnv * env, jclass clazz, jobject pointer)
{
#ifdef _WIN32
        CHECK_EXISTS(wglFreeMemoryNV)
        void *address = (void *)env->GetDirectBufferAddress(pointer);
        wglFreeMemoryNV(address);
#else
        CHECK_EXISTS(NULL)
#endif
}
