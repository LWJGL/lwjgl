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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ATIVertexArrayObject
// ----------------------------------

#include "extgl.h"
#include "common_tools.h"

typedef GLuint (APIENTRY * glNewObjectBufferATIPROC) (GLsizei size, const GLvoid *pointer, GLenum usage);
typedef GLboolean (APIENTRY * glIsObjectBufferATIPROC) (GLuint buffer);
typedef void (APIENTRY * glUpdateObjectBufferATIPROC) (GLuint buffer, GLuint offset, GLsizei size, const GLvoid *pointer, GLenum preserve);
typedef void (APIENTRY * glGetObjectBufferfvATIPROC) (GLuint buffer, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetObjectBufferivATIPROC) (GLuint buffer, GLenum pname, GLint *params);
typedef void (APIENTRY * glFreeObjectBufferATIPROC) (GLuint buffer);
typedef void (APIENTRY * glArrayObjectATIPROC) (GLenum array, GLint size, GLenum type, GLsizei stride, GLuint buffer, GLuint offset);
typedef void (APIENTRY * glGetArrayObjectfvATIPROC) (GLenum array, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetArrayObjectivATIPROC) (GLenum array, GLenum pname, GLint *params);
typedef void (APIENTRY * glVariantArrayObjectATIPROC) (GLuint id, GLenum type, GLsizei stride, GLuint buffer, GLuint offset);
typedef void (APIENTRY * glGetVariantArrayObjectfvATIPROC) (GLuint id, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetVariantArrayObjectivATIPROC) (GLuint id, GLenum pname, GLint *params);

static glNewObjectBufferATIPROC glNewObjectBufferATI;
static glIsObjectBufferATIPROC glIsObjectBufferATI;
static glUpdateObjectBufferATIPROC glUpdateObjectBufferATI;
static glGetObjectBufferfvATIPROC glGetObjectBufferfvATI;
static glGetObjectBufferivATIPROC glGetObjectBufferivATI;
static glFreeObjectBufferATIPROC glFreeObjectBufferATI;
static glArrayObjectATIPROC glArrayObjectATI;
static glGetArrayObjectfvATIPROC glGetArrayObjectfvATI;
static glGetArrayObjectivATIPROC glGetArrayObjectivATI;
static glVariantArrayObjectATIPROC glVariantArrayObjectATI;
static glGetVariantArrayObjectfvATIPROC glGetVariantArrayObjectfvATI;
static glGetVariantArrayObjectivATIPROC glGetVariantArrayObjectivATI;

/*
 * Class:	org.lwjgl.opengl.ATIVertexArrayObject
 * Method:	nglNewObjectBufferATI
 */
static jint JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglNewObjectBufferATI
	(JNIEnv * env, jclass clazz, jint size, jobject pPointer, jint pPointer_offset, jint usage)
{
	GLvoid *pPointer_ptr = safeGetBufferAddress(env, pPointer, pPointer_offset);
	GLuint result = glNewObjectBufferATI(size, pPointer_ptr, usage);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexArrayObject
 * Method:	glIsObjectBufferATI
 */
static jboolean JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_glIsObjectBufferATI
	(JNIEnv * env, jclass clazz, jint buffer)
{
	GLboolean result = glIsObjectBufferATI(buffer);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexArrayObject
 * Method:	nglUpdateObjectBufferATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglUpdateObjectBufferATI
	(JNIEnv * env, jclass clazz, jint buffer, jint offset, jint size, jobject pPointer, jint pPointer_offset, jint preserve)
{
	GLvoid *pPointer_ptr = (GLvoid *)((GLubyte *)(*env)->GetDirectBufferAddress(env, pPointer) + pPointer_offset);
	glUpdateObjectBufferATI(buffer, offset, size, pPointer_ptr, preserve);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexArrayObject
 * Method:	nglGetObjectBufferfvATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetObjectBufferfvATI
	(JNIEnv * env, jclass clazz, jint buffer, jint pname, jobject pfParams, jint pfParams_offset)
{
	GLfloat *pfParams_ptr = (GLfloat *)(*env)->GetDirectBufferAddress(env, pfParams) + pfParams_offset;
	glGetObjectBufferfvATI(buffer, pname, pfParams_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexArrayObject
 * Method:	nglGetObjectBufferivATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetObjectBufferivATI
	(JNIEnv * env, jclass clazz, jint buffer, jint pname, jobject piParams, jint piParams_offset)
{
	GLint *piParams_ptr = (GLint *)(*env)->GetDirectBufferAddress(env, piParams) + piParams_offset;
	glGetObjectBufferivATI(buffer, pname, piParams_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexArrayObject
 * Method:	glFreeObjectBufferATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_glFreeObjectBufferATI
	(JNIEnv * env, jclass clazz, jint buffer)
{
	glFreeObjectBufferATI(buffer);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexArrayObject
 * Method:	glArrayObjectATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_glArrayObjectATI
	(JNIEnv * env, jclass clazz, jint array, jint size, jint type, jint stride, jint buffer, jint offset)
{
	glArrayObjectATI(array, size, type, stride, buffer, offset);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexArrayObject
 * Method:	nglGetArrayObjectfvATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetArrayObjectfvATI
	(JNIEnv * env, jclass clazz, jint array, jint pname, jobject pfParams, jint pfParams_offset)
{
	GLfloat *pfParams_ptr = (GLfloat *)(*env)->GetDirectBufferAddress(env, pfParams) + pfParams_offset;
	glGetArrayObjectfvATI(array, pname, pfParams_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexArrayObject
 * Method:	nglGetArrayObjectivATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetArrayObjectivATI
	(JNIEnv * env, jclass clazz, jint array, jint pname, jobject piParams, jint piParams_offset)
{
	GLint *piParams_ptr = (GLint *)(*env)->GetDirectBufferAddress(env, piParams) + piParams_offset;
	glGetArrayObjectivATI(array, pname, piParams_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexArrayObject
 * Method:	glVariantArrayObjectATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_glVariantArrayObjectATI
	(JNIEnv * env, jclass clazz, jint id, jint type, jint stride, jint buffer, jint offset)
{
	glVariantArrayObjectATI(id, type, stride, buffer, offset);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexArrayObject
 * Method:	nglGetVariantArrayObjectfvATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetVariantArrayObjectfvATI
	(JNIEnv * env, jclass clazz, jint id, jint pname, jobject pfParams, jint pfParams_offset_offset)
{
	GLfloat *pfParams_ptr = (GLfloat *)(*env)->GetDirectBufferAddress(env, pfParams) + pfParams_offset_offset;
	glGetVariantArrayObjectfvATI(id, pname, pfParams_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIVertexArrayObject
 * Method:	nglGetVariantArrayObjectivATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetVariantArrayObjectivATI
	(JNIEnv * env, jclass clazz, jint id, jint pname, jobject piParams, jint piParams_offset)
{
	GLint *piParams_ptr = (GLint *)(*env)->GetDirectBufferAddress(env, piParams) + piParams_offset;
	glGetVariantArrayObjectivATI(id, pname, piParams_ptr);
	
}

#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIVertexArrayObject_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglNewObjectBufferATI", "(ILjava/nio/Buffer;II)I", (void*)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglNewObjectBufferATI, "glNewObjectBufferATI", (void*)&glNewObjectBufferATI},
		{"glIsObjectBufferATI", "(I)Z", (void*)&Java_org_lwjgl_opengl_ATIVertexArrayObject_glIsObjectBufferATI, "glIsObjectBufferATI", (void*)&glIsObjectBufferATI},
		{"nglUpdateObjectBufferATI", "(IIILjava/nio/Buffer;II)V", (void*)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglUpdateObjectBufferATI, "glUpdateObjectBufferATI", (void*)&glUpdateObjectBufferATI},
		{"nglGetObjectBufferfvATI", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetObjectBufferfvATI, "glGetObjectBufferfvATI", (void*)&glGetObjectBufferfvATI},
		{"nglGetObjectBufferivATI", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetObjectBufferivATI, "glGetObjectBufferivATI", (void*)&glGetObjectBufferivATI},
		{"glFreeObjectBufferATI", "(I)V", (void*)&Java_org_lwjgl_opengl_ATIVertexArrayObject_glFreeObjectBufferATI, "glFreeObjectBufferATI", (void*)&glFreeObjectBufferATI},
		{"glArrayObjectATI", "(IIIIII)V", (void*)&Java_org_lwjgl_opengl_ATIVertexArrayObject_glArrayObjectATI, "glArrayObjectATI", (void*)&glArrayObjectATI},
		{"nglGetArrayObjectfvATI", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetArrayObjectfvATI, "glGetArrayObjectfvATI", (void*)&glGetArrayObjectfvATI},
		{"nglGetArrayObjectivATI", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetArrayObjectivATI, "glGetArrayObjectivATI", (void*)&glGetArrayObjectivATI},
		{"glVariantArrayObjectATI", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_ATIVertexArrayObject_glVariantArrayObjectATI, "glVariantArrayObjectATI", (void*)&glVariantArrayObjectATI},
		{"nglGetVariantArrayObjectfvATI", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetVariantArrayObjectfvATI, "glGetVariantArrayObjectfvATI", (void*)&glGetVariantArrayObjectfvATI},
		{"nglGetVariantArrayObjectivATI", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ATIVertexArrayObject_nglGetVariantArrayObjectivATI, "glGetVariantArrayObjectivATI", (void*)&glGetVariantArrayObjectivATI}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
#ifdef __cplusplus
}
#endif

