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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ATISeparateStencil
// ----------------------------------

#include "extgl.h"
#include "checkGLerror.h"

typedef void (APIENTRY * glStencilOpSeparateATIPROC) (GLenum face, GLenum sfail, GLenum dpfail, GLenum dppass);
typedef void (APIENTRY * glStencilFuncSeparateATIPROC) (GLenum frontfunc, GLenum backfunc, GLint ref, GLuint mask);

static glStencilOpSeparateATIPROC glStencilOpSeparateATI;
static glStencilFuncSeparateATIPROC glStencilFuncSeparateATI;

/*
 * Class:	org.lwjgl.opengl.ATISeparateStencil
 * Method:	glStencilOpSeparateATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATISeparateStencil_glStencilOpSeparateATI
	(JNIEnv * env, jclass clazz, jint face, jint sfail, jint dpfail, jint dppass)
{
	glStencilOpSeparateATI(face, sfail, dpfail, dppass);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ATISeparateStencil
 * Method:	glStencilFuncSeparateATI
 */
static void JNICALL Java_org_lwjgl_opengl_ATISeparateStencil_glStencilFuncSeparateATI
	(JNIEnv * env, jclass clazz, jint frontfunc, jint backfunc, jint ref, jint mask)
{
	glStencilFuncSeparateATI(frontfunc, backfunc, ref, mask);
	CHECK_GL_ERROR
}

void extgl_InitATISeparateStencil(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndGLFunction functions[] = {
		{"glStencilOpSeparateATI", "(IIII)V", (void*)&Java_org_lwjgl_opengl_ATISeparateStencil_glStencilOpSeparateATI, "glStencilOpSeparateATI", (void**)&glStencilOpSeparateATI},
		{"glStencilFuncSeparateATI", "(IIII)V", (void*)&Java_org_lwjgl_opengl_ATISeparateStencil_glStencilFuncSeparateATI, "glStencilFuncSeparateATI", (void**)&glStencilFuncSeparateATI}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = extgl_ResetClass(env, "org/lwjgl/opengl/ATISeparateStencil");
	if (extgl_Extensions.GL_ATI_separate_stencil)
		extgl_InitializeClass(env, clazz, ext_set, "GL_ATI_separate_stencil", num_functions, functions);
}
