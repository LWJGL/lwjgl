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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.ext.EXTVertexShader
// ----------------------------------

#include "org_lwjgl_opengl_ext_EXTVertexShader.h"
#include "extgl.h"
#include "checkGLerror.h"

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glBeginVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glBeginVertexShaderEXT
	(JNIEnv * env, jclass clazz)
{
	CHECK_EXISTS(glBeginVertexShaderEXT)
	glBeginVertexShaderEXT();
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glEndVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glEndVertexShaderEXT
	(JNIEnv * env, jclass clazz)
{
	CHECK_EXISTS(glEndVertexShaderEXT)
	glEndVertexShaderEXT();
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glBindVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glBindVertexShaderEXT
	(JNIEnv * env, jclass clazz, jint id)
{
	CHECK_EXISTS(glBindVertexShaderEXT)
	glBindVertexShaderEXT(id);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glGenVertexShadersEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glGenVertexShadersEXT
	(JNIEnv * env, jclass clazz, jint range)
{
	CHECK_EXISTS(glGenVertexShadersEXT)
	GLuint result = glGenVertexShadersEXT(range);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glDeleteVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glDeleteVertexShaderEXT
	(JNIEnv * env, jclass clazz, jint id)
{
	CHECK_EXISTS(glDeleteVertexShaderEXT)
	glDeleteVertexShaderEXT(id);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glShaderOp1EXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glShaderOp1EXT
	(JNIEnv * env, jclass clazz, jint op, jint res, jint arg1)
{
	CHECK_EXISTS(glShaderOp1EXT)
	glShaderOp1EXT(op, res, arg1);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glShaderOp2EXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glShaderOp2EXT
	(JNIEnv * env, jclass clazz, jint op, jint res, jint arg1, jint arg2)
{
	CHECK_EXISTS(glShaderOp2EXT)
	glShaderOp2EXT(op, res, arg1, arg2);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glShaderOp3EXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glShaderOp3EXT
	(JNIEnv * env, jclass clazz, jint op, jint res, jint arg1, jint arg2, jint arg3)
{
	CHECK_EXISTS(glShaderOp3EXT)
	glShaderOp3EXT(op, res, arg1, arg2, arg3);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glSwizzleEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glSwizzleEXT
	(JNIEnv * env, jclass clazz, jint res, jint in, jint outX, jint outY, jint outZ, jint outW)
{
	CHECK_EXISTS(glSwizzleEXT)
	glSwizzleEXT(res, in, outX, outY, outZ, outW);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glWriteMaskEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glWriteMaskEXT
	(JNIEnv * env, jclass clazz, jint res, jint in, jint outX, jint outY, jint outZ, jint outW)
{
	CHECK_EXISTS(glWriteMaskEXT)
	glWriteMaskEXT(res, in, outX, outY, outZ, outW);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glInsertComponentEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glInsertComponentEXT
	(JNIEnv * env, jclass clazz, jint res, jint src, jint num)
{
	CHECK_EXISTS(glInsertComponentEXT)
	glInsertComponentEXT(res, src, num);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glExtractComponentEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glExtractComponentEXT
	(JNIEnv * env, jclass clazz, jint res, jint src, jint num)
{
	CHECK_EXISTS(glExtractComponentEXT)
	glExtractComponentEXT(res, src, num);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glGenSymbolsEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glGenSymbolsEXT
	(JNIEnv * env, jclass clazz, jint dataType, jint storageType, jint range, jint components)
{
	CHECK_EXISTS(glGenSymbolsEXT)
	GLuint result = glGenSymbolsEXT(dataType, storageType, range, components);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglSetInvariantEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglSetInvariantEXT
	(JNIEnv * env, jclass clazz, jint id, jint type, jobject pAddr, jint pAddr_offset)
{
	CHECK_EXISTS(glSetInvariantEXT)
	GLvoid *pAddr_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pAddr) + pAddr_offset);
	glSetInvariantEXT(id, type, pAddr_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglSetLocalConstantEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglSetLocalConstantEXT
	(JNIEnv * env, jclass clazz, jint id, jint type, jobject pAddr, jint pAddr_offset)
{
	CHECK_EXISTS(glSetLocalConstantEXT)
	GLvoid *pAddr_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pAddr) + pAddr_offset);
	glSetLocalConstantEXT(id, type, pAddr_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglVariantbvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglVariantbvEXT
	(JNIEnv * env, jclass clazz, jint id, jobject pAddr, jint pAddr_offset)
{
	CHECK_EXISTS(glVariantbvEXT)
	GLbyte *pAddr_ptr = (GLbyte *)env->GetDirectBufferAddress(pAddr) + pAddr_offset;
	glVariantbvEXT(id, pAddr_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglVariantsvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglVariantsvEXT
	(JNIEnv * env, jclass clazz, jint id, jobject psAddr, jint psAddr_offset)
{
	CHECK_EXISTS(glVariantsvEXT)
	GLshort *psAddr_ptr = (GLshort *)env->GetDirectBufferAddress(psAddr) + psAddr_offset;
	glVariantsvEXT(id, psAddr_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglVariantfvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglVariantfvEXT
	(JNIEnv * env, jclass clazz, jint id, jobject pfAddr, jint pfAddr_offset)
{
	CHECK_EXISTS(glVariantfvEXT)
	GLfloat *pfAddr_ptr = (GLfloat *)env->GetDirectBufferAddress(pfAddr) + pfAddr_offset;
	glVariantfvEXT(id, pfAddr_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglVariantivEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglVariantivEXT
	(JNIEnv * env, jclass clazz, jint id, jobject piAddr, jint piAddr_offset)
{
	CHECK_EXISTS(glVariantivEXT)
	GLint *piAddr_ptr = (GLint *)env->GetDirectBufferAddress(piAddr) + piAddr_offset;
	glVariantivEXT(id, piAddr_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglVariantubvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglVariantubvEXT
	(JNIEnv * env, jclass clazz, jint id, jobject pAddr, jint pAddr_offset)
{
	CHECK_EXISTS(glVariantubvEXT)
	GLubyte *pAddr_ptr = (GLubyte *)env->GetDirectBufferAddress(pAddr) + pAddr_offset;
	glVariantubvEXT(id, pAddr_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglVariantusvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglVariantusvEXT
	(JNIEnv * env, jclass clazz, jint id, jobject psAddr, jint psAddr_offset)
{
	CHECK_EXISTS(glVariantusvEXT)
	GLushort *psAddr_ptr = (GLushort *)env->GetDirectBufferAddress(psAddr) + psAddr_offset;
	glVariantusvEXT(id, psAddr_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglVariantuivEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglVariantuivEXT
	(JNIEnv * env, jclass clazz, jint id, jobject piAddr, jint piAddr_offset)
{
	CHECK_EXISTS(glVariantuivEXT)
	GLuint *piAddr_ptr = (GLuint *)env->GetDirectBufferAddress(piAddr) + piAddr_offset;
	glVariantuivEXT(id, piAddr_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglVariantPointerEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglVariantPointerEXT
	(JNIEnv * env, jclass clazz, jint id, jint type, jint stride, jobject pAddr, jint pAddr_offset)
{
	CHECK_EXISTS(glVariantPointerEXT)
	GLvoid *pAddr_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pAddr) + pAddr_offset);
	glVariantPointerEXT(id, type, stride, pAddr_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglVariantPointerEXTVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglVariantPointerEXTVBO
	(JNIEnv * env, jclass clazz, jint id, jint type, jint stride, jint buffer_offset)
{
	CHECK_EXISTS(glVariantPointerEXT)
	glVariantPointerEXT(id, type, stride, (GLvoid *)buffer_offset);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glEnableVariantClientStateEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glEnableVariantClientStateEXT
	(JNIEnv * env, jclass clazz, jint id)
{
	CHECK_EXISTS(glEnableVariantClientStateEXT)
	glEnableVariantClientStateEXT(id);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glDisableVariantClientStateEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glDisableVariantClientStateEXT
	(JNIEnv * env, jclass clazz, jint id)
{
	CHECK_EXISTS(glDisableVariantClientStateEXT)
	glDisableVariantClientStateEXT(id);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glBindLightParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glBindLightParameterEXT
	(JNIEnv * env, jclass clazz, jint light, jint value)
{
	CHECK_EXISTS(glBindLightParameterEXT)
	GLuint result = glBindLightParameterEXT(light, value);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glBindMaterialParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glBindMaterialParameterEXT
	(JNIEnv * env, jclass clazz, jint face, jint value)
{
	CHECK_EXISTS(glBindMaterialParameterEXT)
	GLuint result = glBindMaterialParameterEXT(face, value);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glBindTexGenParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glBindTexGenParameterEXT
	(JNIEnv * env, jclass clazz, jint unit, jint coord, jint value)
{
	CHECK_EXISTS(glBindTexGenParameterEXT)
	GLuint result = glBindTexGenParameterEXT(unit, coord, value);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glBindTextureUnitParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glBindTextureUnitParameterEXT
	(JNIEnv * env, jclass clazz, jint unit, jint value)
{
	CHECK_EXISTS(glBindTextureUnitParameterEXT)
	GLuint result = glBindTextureUnitParameterEXT(unit, value);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glBindParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glBindParameterEXT
	(JNIEnv * env, jclass clazz, jint value)
{
	CHECK_EXISTS(glBindParameterEXT)
	GLuint result = glBindParameterEXT(value);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glIsVariantEnabledEXT
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glIsVariantEnabledEXT
	(JNIEnv * env, jclass clazz, jint id, jint cap)
{
	CHECK_EXISTS(glIsVariantEnabledEXT)
	GLboolean result = glIsVariantEnabledEXT(id, cap);
	CHECK_GL_ERROR
	return result;
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglGetVariantBooleanvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglGetVariantBooleanvEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject pbData, jint pbData_offset)
{
	CHECK_EXISTS(glGetVariantBooleanvEXT)
	GLubyte *pbData_ptr = (GLubyte *)env->GetDirectBufferAddress(pbData) + pbData_offset;
	glGetVariantBooleanvEXT(id, value, pbData_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglGetVariantIntegervEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglGetVariantIntegervEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject piData, jint piData_offset)
{
	CHECK_EXISTS(glGetVariantIntegervEXT)
	GLint *piData_ptr = (GLint *)env->GetDirectBufferAddress(piData) + piData_offset;
	glGetVariantIntegervEXT(id, value, piData_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglGetVariantFloatvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglGetVariantFloatvEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject pfData, jint pfData_offset)
{
	CHECK_EXISTS(glGetVariantFloatvEXT)
	GLfloat *pfData_ptr = (GLfloat *)env->GetDirectBufferAddress(pfData) + pfData_offset;
	glGetVariantFloatvEXT(id, value, pfData_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	glGetVariantPointerEXT
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_glGetVariantPointerEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jint size)
{
        CHECK_EXISTS(glGetVariantPointervEXT)
        void *address;
        glGetVariantPointervEXT((GLuint)id, (GLuint)value, &address);
        CHECK_GL_ERROR
        return safeNewBuffer(env, address, size);
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglGetInvariantBooleanvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglGetInvariantBooleanvEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject pbData, jint pbData_offset)
{
	CHECK_EXISTS(glGetInvariantBooleanvEXT)
	GLubyte *pbData_ptr = (GLubyte *)env->GetDirectBufferAddress(pbData) + pbData_offset;
	glGetInvariantBooleanvEXT(id, value, pbData_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglGetInvariantIntegervEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglGetInvariantIntegervEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject piData, jint piData_offset)
{
	CHECK_EXISTS(glGetInvariantIntegervEXT)
	GLint *piData_ptr = (GLint *)env->GetDirectBufferAddress(piData) + piData_offset;
	glGetInvariantIntegervEXT(id, value, piData_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglGetInvariantFloatvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglGetInvariantFloatvEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject pfData, jint pfData_offset)
{
	CHECK_EXISTS(glGetInvariantFloatvEXT)
	GLfloat *pfData_ptr = (GLfloat *)env->GetDirectBufferAddress(pfData) + pfData_offset;
	glGetInvariantFloatvEXT(id, value, pfData_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglGetLocalConstantBooleanvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglGetLocalConstantBooleanvEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject pbData, jint pbData_offset)
{
	CHECK_EXISTS(glGetLocalConstantBooleanvEXT)
	GLubyte *pbData_ptr = (GLubyte *)env->GetDirectBufferAddress(pbData) + pbData_offset;
	glGetLocalConstantBooleanvEXT(id, value, pbData_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglGetLocalConstantIntegervEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglGetLocalConstantIntegervEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject piData, jint piData_offset)
{
	CHECK_EXISTS(glGetLocalConstantIntegervEXT)
	GLint *piData_ptr = (GLint *)env->GetDirectBufferAddress(piData) + piData_offset;
	glGetLocalConstantIntegervEXT(id, value, piData_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.ext.EXTVertexShader
 * Method:	nglGetLocalConstantFloatvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ext_EXTVertexShader_nglGetLocalConstantFloatvEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject pfData, jint pfData_offset)
{
	CHECK_EXISTS(glGetLocalConstantFloatvEXT)
	GLfloat *pfData_ptr = (GLfloat *)env->GetDirectBufferAddress(pfData) + pfData_offset;
	glGetLocalConstantFloatvEXT(id, value, pfData_ptr);
	CHECK_GL_ERROR
}
