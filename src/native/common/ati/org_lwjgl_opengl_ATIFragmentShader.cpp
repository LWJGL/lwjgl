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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ATIFragmentShader
// ----------------------------------

#include "extgl.h"


typedef GLuint (APIENTRY * glGenFragmentShadersATIPROC) (GLuint range);
typedef void (APIENTRY * glBindFragmentShaderATIPROC) (GLuint id);
typedef void (APIENTRY * glDeleteFragmentShaderATIPROC) (GLuint id);
typedef void (APIENTRY * glBeginFragmentShaderATIPROC) (GLvoid);
typedef void (APIENTRY * glEndFragmentShaderATIPROC) (GLvoid);
typedef void (APIENTRY * glPassTexCoordATIPROC) (GLuint dst, GLuint coord, GLenum swizzle);
typedef void (APIENTRY * glSampleMapATIPROC) (GLuint dst, GLuint interp, GLenum swizzle);
typedef void (APIENTRY * glColorFragmentOp1ATIPROC) (GLenum op, GLuint dst, GLuint dstMask, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod);
typedef void (APIENTRY * glColorFragmentOp2ATIPROC) (GLenum op, GLuint dst, GLuint dstMask, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod, GLuint arg2, GLuint arg2Rep, GLuint arg2Mod);
typedef void (APIENTRY * glColorFragmentOp3ATIPROC) (GLenum op, GLuint dst, GLuint dstMask, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod, GLuint arg2, GLuint arg2Rep, GLuint arg2Mod, GLuint arg3, GLuint arg3Rep, GLuint arg3Mod);
typedef void (APIENTRY * glAlphaFragmentOp1ATIPROC) (GLenum op, GLuint dst, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod);
typedef void (APIENTRY * glAlphaFragmentOp2ATIPROC) (GLenum op, GLuint dst, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod, GLuint arg2, GLuint arg2Rep, GLuint arg2Mod);
typedef void (APIENTRY * glAlphaFragmentOp3ATIPROC) (GLenum op, GLuint dst, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod, GLuint arg2, GLuint arg2Rep, GLuint arg2Mod, GLuint arg3, GLuint arg3Rep, GLuint arg3Mod);
typedef void (APIENTRY * glSetFragmentShaderConstantATIPROC) (GLuint dst, const GLfloat *value);

static glGenFragmentShadersATIPROC glGenFragmentShadersATI;
static glBindFragmentShaderATIPROC glBindFragmentShaderATI;
static glDeleteFragmentShaderATIPROC glDeleteFragmentShaderATI;
static glBeginFragmentShaderATIPROC glBeginFragmentShaderATI;
static glEndFragmentShaderATIPROC glEndFragmentShaderATI;
static glPassTexCoordATIPROC glPassTexCoordATI;
static glSampleMapATIPROC glSampleMapATI;
static glColorFragmentOp1ATIPROC glColorFragmentOp1ATI;
static glColorFragmentOp2ATIPROC glColorFragmentOp2ATI;
static glColorFragmentOp3ATIPROC glColorFragmentOp3ATI;
static glAlphaFragmentOp1ATIPROC glAlphaFragmentOp1ATI;
static glAlphaFragmentOp2ATIPROC glAlphaFragmentOp2ATI;
static glAlphaFragmentOp3ATIPROC glAlphaFragmentOp3ATI;
static glSetFragmentShaderConstantATIPROC glSetFragmentShaderConstantATI;

/*
 * Class:	org.lwjgl.opengl.ATIFragmentShader
 * Method:	glGenFragmentShadersATI
 */
static jint JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glGenFragmentShadersATI
	(JNIEnv * env, jclass clazz, jint range)
{
	GLuint result = glGenFragmentShadersATI(range);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ATIFragmentShader
 * Method:	glBindFragmentShaderATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glBindFragmentShaderATI
	(JNIEnv * env, jclass clazz, jint id)
{
	glBindFragmentShaderATI(id);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIFragmentShader
 * Method:	glDeleteFragmentShaderATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glDeleteFragmentShaderATI
	(JNIEnv * env, jclass clazz, jint id)
{
	glDeleteFragmentShaderATI(id);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIFragmentShader
 * Method:	glBeginFragmentShaderATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glBeginFragmentShaderATI
	(JNIEnv * env, jclass clazz)
{
	glBeginFragmentShaderATI();
	
}

/*
 * Class:	org.lwjgl.opengl.ATIFragmentShader
 * Method:	glEndFragmentShaderATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glEndFragmentShaderATI
	(JNIEnv * env, jclass clazz)
{
	glEndFragmentShaderATI();
	
}

/*
 * Class:	org.lwjgl.opengl.ATIFragmentShader
 * Method:	glPassTexCoordATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glPassTexCoordATI
	(JNIEnv * env, jclass clazz, jint dst, jint coord, jint swizzle)
{
	glPassTexCoordATI(dst, coord, swizzle);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIFragmentShader
 * Method:	glSampleMapATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glSampleMapATI
	(JNIEnv * env, jclass clazz, jint dst, jint interp, jint swizzle)
{
	glSampleMapATI(dst, interp, swizzle);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIFragmentShader
 * Method:	glColorFragmentOp1ATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glColorFragmentOp1ATI
	(JNIEnv * env, jclass clazz, jint op, jint dst, jint dstMask, jint dstMod, jint arg1, jint arg1Rep, jint arg1Mod)
{
	glColorFragmentOp1ATI(op, dst, dstMask, dstMod, arg1, arg1Rep, arg1Mod);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIFragmentShader
 * Method:	glColorFragmentOp2ATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glColorFragmentOp2ATI
	(JNIEnv * env, jclass clazz, jint op, jint dst, jint dstMask, jint dstMod, jint arg1, jint arg1Rep, jint arg1Mod, jint arg2, jint arg2Rep, jint arg2Mod)
{
	glColorFragmentOp2ATI(op, dst, dstMask, dstMod, arg1, arg1Rep, arg1Mod, arg2, arg2Rep, arg2Mod);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIFragmentShader
 * Method:	glColorFragmentOp3ATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glColorFragmentOp3ATI
	(JNIEnv * env, jclass clazz, jint op, jint dst, jint dstMask, jint dstMod, jint arg1, jint arg1Rep, jint arg1Mod, jint arg2, jint arg2Rep, jint arg2Mod, jint arg3, jint arg3Rep, jint arg3Mod)
{
	glColorFragmentOp3ATI(op, dst, dstMask, dstMod, arg1, arg1Rep, arg1Mod, arg2, arg2Rep, arg2Mod, arg3, arg3Rep, arg3Mod);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIFragmentShader
 * Method:	glAlphaFragmentOp1ATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glAlphaFragmentOp1ATI
	(JNIEnv * env, jclass clazz, jint op, jint dst, jint dstMod, jint arg1, jint arg1Rep, jint arg1Mod)
{
	glAlphaFragmentOp1ATI(op, dst, dstMod, arg1, arg1Rep, arg1Mod);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIFragmentShader
 * Method:	glAlphaFragmentOp2ATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glAlphaFragmentOp2ATI
	(JNIEnv * env, jclass clazz, jint op, jint dst, jint dstMod, jint arg1, jint arg1Rep, jint arg1Mod, jint arg2, jint arg2Rep, jint arg2Mod)
{
	glAlphaFragmentOp2ATI(op, dst, dstMod, arg1, arg1Rep, arg1Mod, arg2, arg2Rep, arg2Mod);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIFragmentShader
 * Method:	glAlphaFragmentOp3ATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glAlphaFragmentOp3ATI
	(JNIEnv * env, jclass clazz, jint op, jint dst, jint dstMod, jint arg1, jint arg1Rep, jint arg1Mod, jint arg2, jint arg2Rep, jint arg2Mod, jint arg3, jint arg3Rep, jint arg3Mod)
{
	glAlphaFragmentOp3ATI(op, dst, dstMod, arg1, arg1Rep, arg1Mod, arg2, arg2Rep, arg2Mod, arg3, arg3Rep, arg3Mod);
	
}

/*
 * Class:	org.lwjgl.opengl.ATIFragmentShader
 * Method:	nglSetFragmentShaderConstantATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_nglSetFragmentShaderConstantATI
	(JNIEnv * env, jclass clazz, jint dst, jobject pfValue, jint pfValue_offset)
{
	GLfloat *pfValue_ptr = (GLfloat *)env->GetDirectBufferAddress(pfValue) + pfValue_offset;
	glSetFragmentShaderConstantATI(dst, pfValue_ptr);
	
}

void extgl_InitATIFragmentShader(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndExtFunction functions[] = {
		{"glGenFragmentShadersATI", "(I)I", (void*)&Java_org_lwjgl_opengl_ATIFragmentShader_glGenFragmentShadersATI, "glGenFragmentShadersATI", (void**)&glGenFragmentShadersATI},
		{"glBindFragmentShaderATI", "(I)V", (void*)&Java_org_lwjgl_opengl_ATIFragmentShader_glBindFragmentShaderATI, "glBindFragmentShaderATI", (void**)&glBindFragmentShaderATI},
		{"glDeleteFragmentShaderATI", "(I)V", (void*)&Java_org_lwjgl_opengl_ATIFragmentShader_glDeleteFragmentShaderATI, "glDeleteFragmentShaderATI", (void**)&glDeleteFragmentShaderATI},
		{"glBeginFragmentShaderATI", "()V", (void*)&Java_org_lwjgl_opengl_ATIFragmentShader_glBeginFragmentShaderATI, "glBeginFragmentShaderATI", (void**)&glBeginFragmentShaderATI},
		{"glEndFragmentShaderATI", "()V", (void*)&Java_org_lwjgl_opengl_ATIFragmentShader_glEndFragmentShaderATI, "glEndFragmentShaderATI", (void**)&glEndFragmentShaderATI},
		{"glPassTexCoordATI", "(III)V", (void*)&Java_org_lwjgl_opengl_ATIFragmentShader_glPassTexCoordATI, "glPassTexCoordATI", (void**)&glPassTexCoordATI},
		{"glSampleMapATI", "(III)V", (void*)&Java_org_lwjgl_opengl_ATIFragmentShader_glSampleMapATI, "glSampleMapATI", (void**)&glSampleMapATI},
		{"glColorFragmentOp1ATI", "(IIIIIII)V", (void*)&Java_org_lwjgl_opengl_ATIFragmentShader_glColorFragmentOp1ATI, "glColorFragmentOp1ATI", (void**)&glColorFragmentOp1ATI},
		{"glColorFragmentOp2ATI", "(IIIIIIIIII)V", (void*)&Java_org_lwjgl_opengl_ATIFragmentShader_glColorFragmentOp2ATI, "glColorFragmentOp2ATI", (void**)&glColorFragmentOp2ATI},
		{"glColorFragmentOp3ATI", "(IIIIIIIIIIIII)V", (void*)&Java_org_lwjgl_opengl_ATIFragmentShader_glColorFragmentOp3ATI, "glColorFragmentOp3ATI", (void**)&glColorFragmentOp3ATI},
		{"glAlphaFragmentOp1ATI", "(IIIIII)V", (void*)&Java_org_lwjgl_opengl_ATIFragmentShader_glAlphaFragmentOp1ATI, "glAlphaFragmentOp1ATI", (void**)&glAlphaFragmentOp1ATI},
		{"glAlphaFragmentOp2ATI", "(IIIIIIIII)V", (void*)&Java_org_lwjgl_opengl_ATIFragmentShader_glAlphaFragmentOp2ATI, "glAlphaFragmentOp2ATI", (void**)&glAlphaFragmentOp2ATI},
		{"glAlphaFragmentOp3ATI", "(IIIIIIIIIIII)V", (void*)&Java_org_lwjgl_opengl_ATIFragmentShader_glAlphaFragmentOp3ATI, "glAlphaFragmentOp3ATI", (void**)&glAlphaFragmentOp3ATI},
		{"nglSetFragmentShaderConstantATI", "(ILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_ATIFragmentShader_nglSetFragmentShaderConstantATI, "glSetFragmentShaderConstantATI", (void**)&glSetFragmentShaderConstantATI}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = ext_ResetClass(env, "org/lwjgl/opengl/ATIFragmentShader");
	if (extgl_Extensions.GL_ATI_fragment_shader)
		extgl_InitializeClass(env, clazz, ext_set, "GL_ATI_fragment_shader", num_functions, functions);
}
