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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.EXTDrawRangeElements
// ----------------------------------

#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glDrawRangeElementsEXTPROC) ( GLenum mode, GLuint start, GLuint end, GLsizei count, GLenum type, const GLvoid *indices);

static glDrawRangeElementsEXTPROC glDrawRangeElementsEXT;

/*
 * Class:	org.lwjgl.opengl.EXTDrawRangeElements
 * Method:	nglDrawRangeElementsEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTDrawRangeElements_nglDrawRangeElementsEXT
	(JNIEnv * env, jclass clazz, jint mode, jint start, jint end, jint count, jint type, jobject pIndices, jint pIndices_offset)
{
	GLvoid *pIndices_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pIndices) + pIndices_offset);
	glDrawRangeElementsEXT(mode, start, end, count, type, pIndices_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.EXTDrawRangeElements
 * Method:	nglDrawRangeElementsEXTVBO
 */
static void JNICALL Java_org_lwjgl_opengl_EXTDrawRangeElements_nglDrawRangeElementsEXTVBO
	(JNIEnv * env, jclass clazz, jint mode, jint start, jint end, jint count, jint type, jint buffer_offset)
{
	glDrawRangeElementsEXT(mode, start, end, count, type, (GLvoid *)buffer_offset);
	CHECK_GL_ERROR
}

void extgl_InitEXTDrawRangeElements(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndGLFunction functions[] = {
		{"nglDrawRangeElementsEXT", "(IIIIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTDrawRangeElements_nglDrawRangeElementsEXT, "glDrawRangeElementsEXT", (void**)&glDrawRangeElementsEXT},
		{"nglDrawRangeElementsEXTVBO", "(IIIIII)V", (void*)&Java_org_lwjgl_opengl_EXTDrawRangeElements_nglDrawRangeElementsEXTVBO, NULL, NULL}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = extgl_ResetClass(env, "org/lwjgl/opengl/EXTDrawRangeElements");
	if (extgl_Extensions.GL_EXT_draw_range_elements)
		extgl_InitializeClass(env, clazz, ext_set, "GL_EXT_draw_range_elements", num_functions, functions);
}

