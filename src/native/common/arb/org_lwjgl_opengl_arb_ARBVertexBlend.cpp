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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.arb.ARBVertexBlend
// ----------------------------------

#include "org_lwjgl_opengl_arb_ARBVertexBlend.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glWeightbvARBPROC) (GLint size, GLbyte *weights);
typedef void (APIENTRY * glWeightsvARBPROC) (GLint size, GLshort *weights);
typedef void (APIENTRY * glWeightivARBPROC) (GLint size, GLint *weights);
typedef void (APIENTRY * glWeightfvARBPROC) (GLint size, GLfloat *weights);
typedef void (APIENTRY * glWeightubvARBPROC) (GLint size, GLubyte *weights);
typedef void (APIENTRY * glWeightusvARBPROC) (GLint size, GLushort *weights);
typedef void (APIENTRY * glWeightuivARBPROC) (GLint size, GLuint *weights);
typedef void (APIENTRY * glWeightPointerARBPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glVertexBlendARBPROC) (GLint count);

static glWeightbvARBPROC glWeightbvARB;
static glWeightsvARBPROC glWeightsvARB;
static glWeightivARBPROC glWeightivARB;
static glWeightfvARBPROC glWeightfvARB;
static glWeightubvARBPROC glWeightubvARB;
static glWeightusvARBPROC glWeightusvARB;
static glWeightuivARBPROC glWeightuivARB;
static glWeightPointerARBPROC glWeightPointerARB;
static glVertexBlendARBPROC glVertexBlendARB;

void extgl_InitARBVertexBlend(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_ARB_vertex_blend)
		return;
	glWeightbvARB = (glWeightbvARBPROC) extgl_GetProcAddress("glWeightbvARB");
	glWeightsvARB = (glWeightsvARBPROC) extgl_GetProcAddress("glWeightsvARB");
	glWeightivARB = (glWeightivARBPROC) extgl_GetProcAddress("glWeightivARB");
	glWeightfvARB = (glWeightfvARBPROC) extgl_GetProcAddress("glWeightfvARB");
	glWeightubvARB = (glWeightubvARBPROC) extgl_GetProcAddress("glWeightubvARB");
	glWeightusvARB = (glWeightusvARBPROC) extgl_GetProcAddress("glWeightusvARB");
	glWeightuivARB = (glWeightuivARBPROC) extgl_GetProcAddress("glWeightuivARB");
	glWeightPointerARB = (glWeightPointerARBPROC) extgl_GetProcAddress("glWeightPointerARB");
	glVertexBlendARB = (glVertexBlendARBPROC) extgl_GetProcAddress("glVertexBlendARB");
	EXTGL_SANITY_CHECK(env, ext_set, GL_ARB_vertex_blend)
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBlend
 * Method:	nglWeightbvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBlend_nglWeightbvARB
	(JNIEnv * env, jclass clazz, jint size, jobject pWeights, jint pWeights_offset)
{
	CHECK_EXISTS(glWeightbvARB)
	GLbyte *pWeights_ptr = (GLbyte *)env->GetDirectBufferAddress(pWeights) + pWeights_offset;
	glWeightbvARB(size, pWeights_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBlend
 * Method:	nglWeightfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBlend_nglWeightfvARB
	(JNIEnv * env, jclass clazz, jint size, jobject pfWeights, jint pfWeights_offset)
{
	CHECK_EXISTS(glWeightfvARB)
	GLfloat *pfWeights_ptr = (GLfloat *)env->GetDirectBufferAddress(pfWeights) + pfWeights_offset;
	glWeightfvARB(size, pfWeights_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBlend
 * Method:	nglWeightivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBlend_nglWeightivARB
	(JNIEnv * env, jclass clazz, jint size, jobject piWeights, jint piWeights_offset)
{
	CHECK_EXISTS(glWeightivARB)
	GLint *piWeights_ptr = (GLint *)env->GetDirectBufferAddress(piWeights) + piWeights_offset;
	glWeightivARB(size, piWeights_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBlend
 * Method:	nglWeightsvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBlend_nglWeightsvARB
	(JNIEnv * env, jclass clazz, jint size, jobject psWeights, jint psWeights_offset)
{
	CHECK_EXISTS(glWeightsvARB)
	GLshort *psWeights_ptr = (GLshort *)env->GetDirectBufferAddress(psWeights) + psWeights_offset;
	glWeightsvARB(size, psWeights_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBlend
 * Method:	nglWeightubvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBlend_nglWeightubvARB
	(JNIEnv * env, jclass clazz, jint size, jobject pWeights, jint pWeights_offset)
{
	CHECK_EXISTS(glWeightubvARB)
	GLubyte *pWeights_ptr = (GLubyte *)env->GetDirectBufferAddress(pWeights) + pWeights_offset;
	glWeightubvARB(size, pWeights_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBlend
 * Method:	nglWeightuivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBlend_nglWeightuivARB
	(JNIEnv * env, jclass clazz, jint size, jobject piWeights, jint piWeights_offset)
{
	CHECK_EXISTS(glWeightuivARB)
	GLuint *piWeights_ptr = (GLuint *)env->GetDirectBufferAddress(piWeights) + piWeights_offset;
	glWeightuivARB(size, piWeights_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBlend
 * Method:	nglWeightusvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBlend_nglWeightusvARB
	(JNIEnv * env, jclass clazz, jint size, jobject psWeights, jint psWeights_offset)
{
	CHECK_EXISTS(glWeightusvARB)
	GLushort *psWeights_ptr = (GLushort *)env->GetDirectBufferAddress(psWeights) + psWeights_offset;
	glWeightusvARB(size, psWeights_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBlend
 * Method:	nglWeightPointerARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBlend_nglWeightPointerARB
	(JNIEnv * env, jclass clazz, jint size, jint type, jint stride, jobject pPointer, jint pPointer_offset)
{
	CHECK_EXISTS(glWeightPointerARB)
	GLvoid *pPointer_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pPointer) + pPointer_offset);
	glWeightPointerARB(size, type, stride, pPointer_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBlend
 * Method:	nglWeightPointerARBVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBlend_nglWeightPointerARBVBO
	(JNIEnv * env, jclass clazz, jint size, jint type, jint stride, jint buffer_offset)
{
	CHECK_EXISTS(glWeightPointerARB)
	glWeightPointerARB(size, type, stride, (GLvoid *)buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBlend
 * Method:	glVertexBlendARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBlend_glVertexBlendARB
	(JNIEnv * env, jclass clazz, jint count)
{
	CHECK_EXISTS(glVertexBlendARB)
	glVertexBlendARB(count);
	CHECK_GL_ERROR
}
