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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ARBMatrixPalette
// ----------------------------------

#include "org_lwjgl_opengl_ARBMatrixPalette.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glCurrentPaletteMatrixARBPROC) (GLint index);
typedef void (APIENTRY * glMatrixIndexubvARBPROC) (GLint size, GLubyte *indices);
typedef void (APIENTRY * glMatrixIndexusvARBPROC) (GLint size, GLushort *indices);
typedef void (APIENTRY * glMatrixIndexuivARBPROC) (GLint size, GLuint *indices);
typedef void (APIENTRY * glMatrixIndexPointerARBPROC) (GLint size, GLenum type, GLsizei stride, GLvoid *pointer);

static glCurrentPaletteMatrixARBPROC glCurrentPaletteMatrixARB;
static glMatrixIndexubvARBPROC glMatrixIndexubvARB;
static glMatrixIndexusvARBPROC glMatrixIndexusvARB;
static glMatrixIndexuivARBPROC glMatrixIndexuivARB;
static glMatrixIndexPointerARBPROC glMatrixIndexPointerARB;

void extgl_InitARBMatrixPalette(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_matrix_palette)
		return;
	glCurrentPaletteMatrixARB = (glCurrentPaletteMatrixARBPROC) extgl_GetProcAddress("glCurrentPaletteMatrixARB");
	glMatrixIndexubvARB = (glMatrixIndexubvARBPROC) extgl_GetProcAddress("glMatrixIndexubvARB");
	glMatrixIndexusvARB = (glMatrixIndexusvARBPROC) extgl_GetProcAddress("glMatrixIndexusvARB");
	glMatrixIndexuivARB = (glMatrixIndexuivARBPROC) extgl_GetProcAddress("glMatrixIndexuivARB");
	glMatrixIndexPointerARB = (glMatrixIndexPointerARBPROC) extgl_GetProcAddress("glMatrixIndexPointerARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_matrix_palette)
}

/*
 * Class:	org.lwjgl.opengl.ARBMatrixPalette
 * Method:	glCurrentPaletteMatrixARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBMatrixPalette_glCurrentPaletteMatrixARB
	(JNIEnv * env, jclass clazz, jint index)
{
	CHECK_EXISTS(glCurrentPaletteMatrixARB)
	glCurrentPaletteMatrixARB(index);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMatrixPalette
 * Method:	nglMatrixIndexPointerARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBMatrixPalette_nglMatrixIndexPointerARB
	(JNIEnv * env, jclass clazz, jint size, jint type, jint stride, jobject pPointer, jint pPointer_offset)
{
	CHECK_EXISTS(glMatrixIndexPointerARB)
	GLvoid *pPointer_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pPointer) + pPointer_offset);
	glMatrixIndexPointerARB(size, type, stride, pPointer_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMatrixPalette
 * Method:	nglMatrixIndexPointerARBVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBMatrixPalette_nglMatrixIndexPointerARBVBO
	(JNIEnv * env, jclass clazz, jint size, jint type, jint stride, jint buffer_offset)
{
	CHECK_EXISTS(glMatrixIndexPointerARB)
	glMatrixIndexPointerARB(size, type, stride, (GLvoid *)buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMatrixPalette
 * Method:	nglMatrixIndexubvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBMatrixPalette_nglMatrixIndexubvARB
	(JNIEnv * env, jclass clazz, jint size, jobject pIndices, jint pIndices_offset)
{
	CHECK_EXISTS(glMatrixIndexubvARB)
	GLubyte *pIndices_ptr = (GLubyte *)env->GetDirectBufferAddress(pIndices) + pIndices_offset;
	glMatrixIndexubvARB(size, pIndices_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMatrixPalette
 * Method:	nglMatrixIndexuivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBMatrixPalette_nglMatrixIndexuivARB
	(JNIEnv * env, jclass clazz, jint size, jobject piIndices, jint piIndices_offset)
{
	CHECK_EXISTS(glMatrixIndexuivARB)
	GLuint *piIndices_ptr = (GLuint *)env->GetDirectBufferAddress(piIndices) + piIndices_offset;
	glMatrixIndexuivARB(size, piIndices_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ARBMatrixPalette
 * Method:	nglMatrixIndexusvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBMatrixPalette_nglMatrixIndexusvARB
	(JNIEnv * env, jclass clazz, jint size, jobject psIndices, jint psIndices_offset)
{
	CHECK_EXISTS(glMatrixIndexusvARB)
	GLushort *psIndices_ptr = (GLushort *)env->GetDirectBufferAddress(psIndices) + psIndices_offset;
	glMatrixIndexusvARB(size, psIndices_ptr);
	CHECK_GL_ERROR
}
