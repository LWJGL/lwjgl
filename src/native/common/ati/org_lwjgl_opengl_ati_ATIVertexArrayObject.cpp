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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ati.ATIVertexArrayObject
// ----------------------------------

#include "org_lwjgl_opengl_ati_ATIVertexArrayObject.h"
#include "extgl.h"
#include "checkGLerror.h"

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexArrayObject
 * Method:	nglNewObjectBufferATI
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ati_ATIVertexArrayObject_nglNewObjectBufferATI
	(JNIEnv * env, jclass clazz, jint size, jobject pPointer, jint pPointer_offset, jint usage)
{
	CHECK_EXISTS(glNewObjectBufferATI)
	GLvoid *pPointer_ptr = safeGetBufferAddress(env, pPointer, pPointer_offset);
	GLuint result = glNewObjectBufferATI(size, pPointer_ptr, usage);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexArrayObject
 * Method:	glIsObjectBufferATI
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_ati_ATIVertexArrayObject_glIsObjectBufferATI
	(JNIEnv * env, jclass clazz, jint buffer)
{
	CHECK_EXISTS(glIsObjectBufferATI)
	GLboolean result = glIsObjectBufferATI(buffer);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexArrayObject
 * Method:	nglUpdateObjectBufferATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexArrayObject_nglUpdateObjectBufferATI
	(JNIEnv * env, jclass clazz, jint buffer, jint offset, jint size, jobject pPointer, jint pPointer_offset, jint preserve)
{
	CHECK_EXISTS(glUpdateObjectBufferATI)
	GLvoid *pPointer_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pPointer) + pPointer_offset);
	glUpdateObjectBufferATI(buffer, offset, size, pPointer_ptr, preserve);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexArrayObject
 * Method:	nglGetObjectBufferfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexArrayObject_nglGetObjectBufferfvATI
	(JNIEnv * env, jclass clazz, jint buffer, jint pname, jobject pfParams, jint pfParams_offset)
{
	CHECK_EXISTS(glGetObjectBufferfvATI)
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glGetObjectBufferfvATI(buffer, pname, pfParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexArrayObject
 * Method:	nglGetObjectBufferivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexArrayObject_nglGetObjectBufferivATI
	(JNIEnv * env, jclass clazz, jint buffer, jint pname, jobject piParams, jint piParams_offset)
{
	CHECK_EXISTS(glGetObjectBufferivATI)
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetObjectBufferivATI(buffer, pname, piParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexArrayObject
 * Method:	glFreeObjectBufferATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexArrayObject_glFreeObjectBufferATI
	(JNIEnv * env, jclass clazz, jint buffer)
{
	CHECK_EXISTS(glFreeObjectBufferATI)
	glFreeObjectBufferATI(buffer);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexArrayObject
 * Method:	glArrayObjectATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexArrayObject_glArrayObjectATI
	(JNIEnv * env, jclass clazz, jint array, jint size, jint type, jint stride, jint buffer, jint offset)
{
	CHECK_EXISTS(glArrayObjectATI)
	glArrayObjectATI(array, size, type, stride, buffer, offset);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexArrayObject
 * Method:	nglGetArrayObjectfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexArrayObject_nglGetArrayObjectfvATI
	(JNIEnv * env, jclass clazz, jint array, jint pname, jobject pfParams, jint pfParams_offset)
{
	CHECK_EXISTS(glGetArrayObjectfvATI)
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset;
	glGetArrayObjectfvATI(array, pname, pfParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexArrayObject
 * Method:	nglGetArrayObjectivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexArrayObject_nglGetArrayObjectivATI
	(JNIEnv * env, jclass clazz, jint array, jint pname, jobject piParams, jint piParams_offset)
{
	CHECK_EXISTS(glGetArrayObjectivATI)
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetArrayObjectivATI(array, pname, piParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexArrayObject
 * Method:	glVariantArrayObjectATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexArrayObject_glVariantArrayObjectATI
	(JNIEnv * env, jclass clazz, jint id, jint type, jint stride, jint buffer, jint offset)
{
	CHECK_EXISTS(glVariantArrayObjectATI)
	glVariantArrayObjectATI(id, type, stride, buffer, offset);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexArrayObject
 * Method:	nglGetVariantArrayObjectfvATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexArrayObject_nglGetVariantArrayObjectfvATI
	(JNIEnv * env, jclass clazz, jint id, jint pname, jobject pfParams, jint pfParams_offset_offset)
{
	CHECK_EXISTS(glGetVariantArrayObjectfvATI)
	GLfloat *pfParams_ptr = (GLfloat *)env->GetDirectBufferAddress(pfParams) + pfParams_offset_offset;
	glGetVariantArrayObjectfvATI(id, pname, pfParams_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIVertexArrayObject
 * Method:	nglGetVariantArrayObjectivATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIVertexArrayObject_nglGetVariantArrayObjectivATI
	(JNIEnv * env, jclass clazz, jint id, jint pname, jobject piParams, jint piParams_offset)
{
	CHECK_EXISTS(glGetVariantArrayObjectivATI)
	GLint *piParams_ptr = (GLint *)env->GetDirectBufferAddress(piParams) + piParams_offset;
	glGetVariantArrayObjectivATI(id, pname, piParams_ptr);
	CHECK_GL_ERROR
}
