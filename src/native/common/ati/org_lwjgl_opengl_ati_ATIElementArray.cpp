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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ati.ATIElementArray
// ----------------------------------

#include "org_lwjgl_opengl_ati_ATIElementArray.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glElementPointerATIPROC) (GLenum type, const GLvoid *pointer);
typedef void (APIENTRY * glDrawElementArrayATIPROC) (GLenum mode, GLsizei count);
typedef void (APIENTRY * glDrawRangeElementArrayATIPROC) (GLenum mode, GLuint start, GLuint end, GLsizei count);

static glElementPointerATIPROC glElementPointerATI;
static glDrawElementArrayATIPROC glDrawElementArrayATI;
static glDrawRangeElementArrayATIPROC glDrawRangeElementArrayATI;

void extgl_InitATIElementArray(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ATI_element_array)
		return;
	glElementPointerATI = (glElementPointerATIPROC) extgl_GetProcAddress("glElementPointerATI");
	glDrawElementArrayATI = (glDrawElementArrayATIPROC) extgl_GetProcAddress("glDrawElementArrayATI");
	glDrawRangeElementArrayATI = (glDrawRangeElementArrayATIPROC) extgl_GetProcAddress("glDrawRangeElementArrayATI");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ATI_element_array)
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIElementArray
 * Method:	nglElementPointerATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIElementArray_nglElementPointerATI
	(JNIEnv * env, jclass clazz, jint type, jobject pPointer, jint pPointer_offset)
{
	CHECK_EXISTS(glElementPointerATI)
	GLvoid *pPointer_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pPointer) + pPointer_offset);
	glElementPointerATI(type, pPointer_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIElementArray
 * Method:	nglElementPointerATIVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIElementArray_nglElementPointerATIVBO
	(JNIEnv * env, jclass clazz, jint type, jint buffer_offset)
{
	CHECK_EXISTS(glElementPointerATI)
	glElementPointerATI(type, (GLvoid *)buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIElementArray
 * Method:	glDrawElementArrayATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIElementArray_glDrawElementArrayATI
	(JNIEnv * env, jclass clazz, jint mode, jint count)
{
	CHECK_EXISTS(glDrawElementArrayATI)
	glDrawElementArrayATI(mode, count);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ati.ATIElementArray
 * Method:	glDrawRangeElementArrayATI
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ati_ATIElementArray_glDrawRangeElementArrayATI
	(JNIEnv * env, jclass clazz, jint mode, jint start, jint end, jint count)
{
	CHECK_EXISTS(glDrawRangeElementArrayATI)
	glDrawRangeElementArrayATI(mode, start, end, count);
	CHECK_GL_ERROR
}
