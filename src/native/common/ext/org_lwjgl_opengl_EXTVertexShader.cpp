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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.EXTVertexShader
// ----------------------------------

#include "extgl.h"
#include "common_tools.h"

typedef void (APIENTRY * glBeginVertexShaderEXTPROC) ();
typedef void (APIENTRY * glEndVertexShaderEXTPROC) ();
typedef void (APIENTRY * glBindVertexShaderEXTPROC) (GLuint id);
typedef GLuint (APIENTRY * glGenVertexShadersEXTPROC) (GLuint range);
typedef void (APIENTRY * glDeleteVertexShaderEXTPROC) (GLuint id);
typedef void (APIENTRY * glShaderOp1EXTPROC) (GLenum op, GLuint res, GLuint arg1);
typedef void (APIENTRY * glShaderOp2EXTPROC) (GLenum op, GLuint res, GLuint arg1, GLuint arg2);
typedef void (APIENTRY * glShaderOp3EXTPROC) (GLenum op, GLuint res, GLuint arg1, GLuint arg2, GLuint arg3);
typedef void (APIENTRY * glSwizzleEXTPROC) (GLuint res, GLuint in, GLenum outX, GLenum outY, GLenum outZ, GLenum outW);
typedef void (APIENTRY * glWriteMaskEXTPROC) (GLuint res, GLuint in, GLenum outX, GLenum outY, GLenum outZ, GLenum outW);
typedef void (APIENTRY * glInsertComponentEXTPROC) (GLuint res, GLuint src, GLuint num);
typedef void (APIENTRY * glExtractComponentEXTPROC) (GLuint res, GLuint src, GLuint num);
typedef GLuint (APIENTRY * glGenSymbolsEXTPROC) (GLenum dataType, GLenum storageType, GLenum range, GLuint components);
typedef void (APIENTRY * glSetInvariantEXTPROC) (GLuint id, GLenum type, GLvoid *addr);
typedef void (APIENTRY * glSetLocalConstantEXTPROC) (GLuint id, GLenum type, GLvoid *addr);
typedef void (APIENTRY * glVariantbvEXTPROC) (GLuint id, GLbyte *addr);
typedef void (APIENTRY * glVariantsvEXTPROC) (GLuint id, GLshort *addr);
typedef void (APIENTRY * glVariantivEXTPROC) (GLuint id, GLint *addr);
typedef void (APIENTRY * glVariantfvEXTPROC) (GLuint id, GLfloat *addr);
typedef void (APIENTRY * glVariantubvEXTPROC) (GLuint id, GLubyte *addr);
typedef void (APIENTRY * glVariantusvEXTPROC) (GLuint id, GLushort *addr);
typedef void (APIENTRY * glVariantuivEXTPROC) (GLuint id, GLuint *addr);
typedef void (APIENTRY * glVariantPointerEXTPROC) (GLuint id, GLenum type, GLuint stride, GLvoid *addr);
typedef void (APIENTRY * glEnableVariantClientStateEXTPROC) (GLuint id);
typedef void (APIENTRY * glDisableVariantClientStateEXTPROC) (GLuint id);
typedef GLuint (APIENTRY * glBindLightParameterEXTPROC) (GLenum light, GLenum value);
typedef GLuint (APIENTRY * glBindMaterialParameterEXTPROC) (GLenum face, GLenum value);
typedef GLuint (APIENTRY * glBindTexGenParameterEXTPROC) (GLenum unit, GLenum coord, GLenum value);
typedef GLuint (APIENTRY * glBindTextureUnitParameterEXTPROC) (GLenum unit, GLenum value);
typedef GLuint (APIENTRY * glBindParameterEXTPROC) (GLenum value);
typedef GLboolean (APIENTRY * glIsVariantEnabledEXTPROC) (GLuint id, GLenum cap);
typedef void (APIENTRY * glGetVariantBooleanvEXTPROC) (GLuint id, GLenum value, GLboolean *data);
typedef void (APIENTRY * glGetVariantIntegervEXTPROC) (GLuint id, GLenum value, GLint *data);
typedef void (APIENTRY * glGetVariantFloatvEXTPROC) (GLuint id, GLenum value, GLfloat *data);
typedef void (APIENTRY * glGetVariantPointervEXTPROC) (GLuint id, GLenum value, GLvoid **data);
typedef void (APIENTRY * glGetInvariantBooleanvEXTPROC) (GLuint id, GLenum value, GLboolean *data);
typedef void (APIENTRY * glGetInvariantIntegervEXTPROC) (GLuint id, GLenum value, GLint *data);
typedef void (APIENTRY * glGetInvariantFloatvEXTPROC) (GLuint id, GLenum value, GLfloat *data);
typedef void (APIENTRY * glGetLocalConstantBooleanvEXTPROC) (GLuint id, GLenum value, GLboolean *data);
typedef void (APIENTRY * glGetLocalConstantIntegervEXTPROC) (GLuint id, GLenum value, GLint *data);
typedef void (APIENTRY * glGetLocalConstantFloatvEXTPROC) (GLuint id, GLenum value, GLfloat *data);

static glBeginVertexShaderEXTPROC glBeginVertexShaderEXT;
static glEndVertexShaderEXTPROC glEndVertexShaderEXT;
static glBindVertexShaderEXTPROC glBindVertexShaderEXT;
static glGenVertexShadersEXTPROC glGenVertexShadersEXT;
static glDeleteVertexShaderEXTPROC glDeleteVertexShaderEXT;
static glShaderOp1EXTPROC glShaderOp1EXT;
static glShaderOp2EXTPROC glShaderOp2EXT;
static glShaderOp3EXTPROC glShaderOp3EXT;
static glSwizzleEXTPROC glSwizzleEXT;
static glWriteMaskEXTPROC glWriteMaskEXT;
static glInsertComponentEXTPROC glInsertComponentEXT;
static glExtractComponentEXTPROC glExtractComponentEXT;
static glGenSymbolsEXTPROC glGenSymbolsEXT;
static glSetInvariantEXTPROC glSetInvariantEXT;
static glSetLocalConstantEXTPROC glSetLocalConstantEXT;
static glVariantbvEXTPROC glVariantbvEXT;
static glVariantsvEXTPROC glVariantsvEXT;
static glVariantivEXTPROC glVariantivEXT;
static glVariantfvEXTPROC glVariantfvEXT;
static glVariantubvEXTPROC glVariantubvEXT;
static glVariantusvEXTPROC glVariantusvEXT;
static glVariantuivEXTPROC glVariantuivEXT;
static glVariantPointerEXTPROC glVariantPointerEXT;
static glEnableVariantClientStateEXTPROC glEnableVariantClientStateEXT;
static glDisableVariantClientStateEXTPROC glDisableVariantClientStateEXT;
static glBindLightParameterEXTPROC glBindLightParameterEXT;
static glBindMaterialParameterEXTPROC glBindMaterialParameterEXT;
static glBindTexGenParameterEXTPROC glBindTexGenParameterEXT;
static glBindTextureUnitParameterEXTPROC glBindTextureUnitParameterEXT;
static glBindParameterEXTPROC glBindParameterEXT;
static glIsVariantEnabledEXTPROC glIsVariantEnabledEXT;
static glGetVariantBooleanvEXTPROC glGetVariantBooleanvEXT;
static glGetVariantIntegervEXTPROC glGetVariantIntegervEXT;
static glGetVariantFloatvEXTPROC glGetVariantFloatvEXT;
static glGetVariantPointervEXTPROC glGetVariantPointervEXT;
static glGetInvariantBooleanvEXTPROC glGetInvariantBooleanvEXT;
static glGetInvariantIntegervEXTPROC glGetInvariantIntegervEXT;
static glGetInvariantFloatvEXTPROC glGetInvariantFloatvEXT;
static glGetLocalConstantBooleanvEXTPROC glGetLocalConstantBooleanvEXT;
static glGetLocalConstantIntegervEXTPROC glGetLocalConstantIntegervEXT;
static glGetLocalConstantFloatvEXTPROC glGetLocalConstantFloatvEXT;

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glBeginVertexShaderEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBeginVertexShaderEXT
	(JNIEnv * env, jclass clazz)
{
	glBeginVertexShaderEXT();
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glEndVertexShaderEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glEndVertexShaderEXT
	(JNIEnv * env, jclass clazz)
{
	glEndVertexShaderEXT();
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glBindVertexShaderEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindVertexShaderEXT
	(JNIEnv * env, jclass clazz, jint id)
{
	glBindVertexShaderEXT(id);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glGenVertexShadersEXT
 */
static jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glGenVertexShadersEXT
	(JNIEnv * env, jclass clazz, jint range)
{
	GLuint result = glGenVertexShadersEXT(range);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glDeleteVertexShaderEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glDeleteVertexShaderEXT
	(JNIEnv * env, jclass clazz, jint id)
{
	glDeleteVertexShaderEXT(id);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glShaderOp1EXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glShaderOp1EXT
	(JNIEnv * env, jclass clazz, jint op, jint res, jint arg1)
{
	glShaderOp1EXT(op, res, arg1);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glShaderOp2EXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glShaderOp2EXT
	(JNIEnv * env, jclass clazz, jint op, jint res, jint arg1, jint arg2)
{
	glShaderOp2EXT(op, res, arg1, arg2);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glShaderOp3EXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glShaderOp3EXT
	(JNIEnv * env, jclass clazz, jint op, jint res, jint arg1, jint arg2, jint arg3)
{
	glShaderOp3EXT(op, res, arg1, arg2, arg3);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glSwizzleEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glSwizzleEXT
	(JNIEnv * env, jclass clazz, jint res, jint in, jint outX, jint outY, jint outZ, jint outW)
{
	glSwizzleEXT(res, in, outX, outY, outZ, outW);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glWriteMaskEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glWriteMaskEXT
	(JNIEnv * env, jclass clazz, jint res, jint in, jint outX, jint outY, jint outZ, jint outW)
{
	glWriteMaskEXT(res, in, outX, outY, outZ, outW);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glInsertComponentEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glInsertComponentEXT
	(JNIEnv * env, jclass clazz, jint res, jint src, jint num)
{
	glInsertComponentEXT(res, src, num);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glExtractComponentEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glExtractComponentEXT
	(JNIEnv * env, jclass clazz, jint res, jint src, jint num)
{
	glExtractComponentEXT(res, src, num);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glGenSymbolsEXT
 */
static jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glGenSymbolsEXT
	(JNIEnv * env, jclass clazz, jint dataType, jint storageType, jint range, jint components)
{
	GLuint result = glGenSymbolsEXT(dataType, storageType, range, components);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglSetInvariantEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglSetInvariantEXT
	(JNIEnv * env, jclass clazz, jint id, jint type, jobject pAddr, jint pAddr_offset)
{
	GLvoid *pAddr_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pAddr) + pAddr_offset);
	glSetInvariantEXT(id, type, pAddr_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglSetLocalConstantEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglSetLocalConstantEXT
	(JNIEnv * env, jclass clazz, jint id, jint type, jobject pAddr, jint pAddr_offset)
{
	GLvoid *pAddr_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pAddr) + pAddr_offset);
	glSetLocalConstantEXT(id, type, pAddr_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantbvEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantbvEXT
	(JNIEnv * env, jclass clazz, jint id, jobject pAddr, jint pAddr_offset)
{
	GLbyte *pAddr_ptr = (GLbyte *)env->GetDirectBufferAddress(pAddr) + pAddr_offset;
	glVariantbvEXT(id, pAddr_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantsvEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantsvEXT
	(JNIEnv * env, jclass clazz, jint id, jobject psAddr, jint psAddr_offset)
{
	GLshort *psAddr_ptr = (GLshort *)env->GetDirectBufferAddress(psAddr) + psAddr_offset;
	glVariantsvEXT(id, psAddr_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantfvEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantfvEXT
	(JNIEnv * env, jclass clazz, jint id, jobject pfAddr, jint pfAddr_offset)
{
	GLfloat *pfAddr_ptr = (GLfloat *)env->GetDirectBufferAddress(pfAddr) + pfAddr_offset;
	glVariantfvEXT(id, pfAddr_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantivEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantivEXT
	(JNIEnv * env, jclass clazz, jint id, jobject piAddr, jint piAddr_offset)
{
	GLint *piAddr_ptr = (GLint *)env->GetDirectBufferAddress(piAddr) + piAddr_offset;
	glVariantivEXT(id, piAddr_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantubvEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantubvEXT
	(JNIEnv * env, jclass clazz, jint id, jobject pAddr, jint pAddr_offset)
{
	GLubyte *pAddr_ptr = (GLubyte *)env->GetDirectBufferAddress(pAddr) + pAddr_offset;
	glVariantubvEXT(id, pAddr_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantusvEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantusvEXT
	(JNIEnv * env, jclass clazz, jint id, jobject psAddr, jint psAddr_offset)
{
	GLushort *psAddr_ptr = (GLushort *)env->GetDirectBufferAddress(psAddr) + psAddr_offset;
	glVariantusvEXT(id, psAddr_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantuivEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantuivEXT
	(JNIEnv * env, jclass clazz, jint id, jobject piAddr, jint piAddr_offset)
{
	GLuint *piAddr_ptr = (GLuint *)env->GetDirectBufferAddress(piAddr) + piAddr_offset;
	glVariantuivEXT(id, piAddr_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantPointerEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantPointerEXT
	(JNIEnv * env, jclass clazz, jint id, jint type, jint stride, jobject pAddr, jint pAddr_offset)
{
	GLvoid *pAddr_ptr = (GLvoid *)((GLubyte *)env->GetDirectBufferAddress(pAddr) + pAddr_offset);
	glVariantPointerEXT(id, type, stride, pAddr_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantPointerEXTVBO
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantPointerEXTVBO
	(JNIEnv * env, jclass clazz, jint id, jint type, jint stride, jint buffer_offset)
{
	glVariantPointerEXT(id, type, stride, (GLvoid *)buffer_offset);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glEnableVariantClientStateEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glEnableVariantClientStateEXT
	(JNIEnv * env, jclass clazz, jint id)
{
	glEnableVariantClientStateEXT(id);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glDisableVariantClientStateEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glDisableVariantClientStateEXT
	(JNIEnv * env, jclass clazz, jint id)
{
	glDisableVariantClientStateEXT(id);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glBindLightParameterEXT
 */
static jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindLightParameterEXT
	(JNIEnv * env, jclass clazz, jint light, jint value)
{
	GLuint result = glBindLightParameterEXT(light, value);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glBindMaterialParameterEXT
 */
static jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindMaterialParameterEXT
	(JNIEnv * env, jclass clazz, jint face, jint value)
{
	GLuint result = glBindMaterialParameterEXT(face, value);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glBindTexGenParameterEXT
 */
static jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindTexGenParameterEXT
	(JNIEnv * env, jclass clazz, jint unit, jint coord, jint value)
{
	GLuint result = glBindTexGenParameterEXT(unit, coord, value);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glBindTextureUnitParameterEXT
 */
static jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindTextureUnitParameterEXT
	(JNIEnv * env, jclass clazz, jint unit, jint value)
{
	GLuint result = glBindTextureUnitParameterEXT(unit, value);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glBindParameterEXT
 */
static jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindParameterEXT
	(JNIEnv * env, jclass clazz, jint value)
{
	GLuint result = glBindParameterEXT(value);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glIsVariantEnabledEXT
 */
static jboolean JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glIsVariantEnabledEXT
	(JNIEnv * env, jclass clazz, jint id, jint cap)
{
	GLboolean result = glIsVariantEnabledEXT(id, cap);
	
	return result;
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetVariantBooleanvEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantBooleanvEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject pbData, jint pbData_offset)
{
	GLubyte *pbData_ptr = (GLubyte *)env->GetDirectBufferAddress(pbData) + pbData_offset;
	glGetVariantBooleanvEXT(id, value, pbData_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetVariantIntegervEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantIntegervEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject piData, jint piData_offset)
{
	GLint *piData_ptr = (GLint *)env->GetDirectBufferAddress(piData) + piData_offset;
	glGetVariantIntegervEXT(id, value, piData_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetVariantFloatvEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantFloatvEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject pfData, jint pfData_offset)
{
	GLfloat *pfData_ptr = (GLfloat *)env->GetDirectBufferAddress(pfData) + pfData_offset;
	glGetVariantFloatvEXT(id, value, pfData_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glGetVariantPointerEXT
 */
static jobject JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glGetVariantPointerEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jint size)
{
        void *address;
        glGetVariantPointervEXT((GLuint)id, (GLuint)value, &address);
        
        return safeNewBuffer(env, address, size);
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetInvariantBooleanvEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetInvariantBooleanvEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject pbData, jint pbData_offset)
{
	GLubyte *pbData_ptr = (GLubyte *)env->GetDirectBufferAddress(pbData) + pbData_offset;
	glGetInvariantBooleanvEXT(id, value, pbData_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetInvariantIntegervEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetInvariantIntegervEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject piData, jint piData_offset)
{
	GLint *piData_ptr = (GLint *)env->GetDirectBufferAddress(piData) + piData_offset;
	glGetInvariantIntegervEXT(id, value, piData_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetInvariantFloatvEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetInvariantFloatvEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject pfData, jint pfData_offset)
{
	GLfloat *pfData_ptr = (GLfloat *)env->GetDirectBufferAddress(pfData) + pfData_offset;
	glGetInvariantFloatvEXT(id, value, pfData_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetLocalConstantBooleanvEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetLocalConstantBooleanvEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject pbData, jint pbData_offset)
{
	GLubyte *pbData_ptr = (GLubyte *)env->GetDirectBufferAddress(pbData) + pbData_offset;
	glGetLocalConstantBooleanvEXT(id, value, pbData_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetLocalConstantIntegervEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetLocalConstantIntegervEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject piData, jint piData_offset)
{
	GLint *piData_ptr = (GLint *)env->GetDirectBufferAddress(piData) + piData_offset;
	glGetLocalConstantIntegervEXT(id, value, piData_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetLocalConstantFloatvEXT
 */
static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetLocalConstantFloatvEXT
	(JNIEnv * env, jclass clazz, jint id, jint value, jobject pfData, jint pfData_offset)
{
	GLfloat *pfData_ptr = (GLfloat *)env->GetDirectBufferAddress(pfData) + pfData_offset;
	glGetLocalConstantFloatvEXT(id, value, pfData_ptr);
	
}

void extgl_InitEXTVertexShader(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndExtFunction functions[] = {
		{"glBeginVertexShaderEXT", "()V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glBeginVertexShaderEXT, "glBeginVertexShaderEXT", (void**)&glBeginVertexShaderEXT},
		{"glEndVertexShaderEXT", "()V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glEndVertexShaderEXT, "glEndVertexShaderEXT", (void**)&glEndVertexShaderEXT},
		{"glBindVertexShaderEXT", "(I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glBindVertexShaderEXT, "glBindVertexShaderEXT", (void**)&glBindVertexShaderEXT},
		{"glGenVertexShadersEXT", "(I)I", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glGenVertexShadersEXT, "glGenVertexShadersEXT", (void**)&glGenVertexShadersEXT},
		{"glDeleteVertexShaderEXT", "(I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glDeleteVertexShaderEXT, "glDeleteVertexShaderEXT", (void**)&glDeleteVertexShaderEXT},
		{"glShaderOp1EXT", "(III)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glShaderOp1EXT, "glShaderOp1EXT", (void**)&glShaderOp1EXT},
		{"glShaderOp2EXT", "(IIII)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glShaderOp2EXT, "glShaderOp2EXT", (void**)&glShaderOp2EXT},
		{"glShaderOp3EXT", "(IIIII)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glShaderOp3EXT, "glShaderOp3EXT", (void**)&glShaderOp3EXT},
		{"glSwizzleEXT", "(IIIIII)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glSwizzleEXT, "glSwizzleEXT", (void**)&glSwizzleEXT},
		{"glWriteMaskEXT", "(IIIIII)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glWriteMaskEXT, "glWriteMaskEXT", (void**)&glWriteMaskEXT},
		{"glInsertComponentEXT", "(III)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glInsertComponentEXT, "glInsertComponentEXT", (void**)&glInsertComponentEXT},
		{"glExtractComponentEXT", "(III)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glExtractComponentEXT, "glExtractComponentEXT", (void**)&glExtractComponentEXT},
		{"glGenSymbolsEXT", "(IIII)I", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glGenSymbolsEXT, "glGenSymbolsEXT", (void**)&glGenSymbolsEXT},
		{"nglSetInvariantEXT", "(IILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglSetInvariantEXT, "glSetInvariantEXT", (void**)&glSetInvariantEXT},
		{"nglSetLocalConstantEXT", "(IILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglSetLocalConstantEXT, "glSetLocalConstantEXT", (void**)&glSetLocalConstantEXT},
		{"nglVariantbvEXT", "(ILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantbvEXT, "glVariantbvEXT", (void**)&glVariantbvEXT},
		{"nglVariantsvEXT", "(ILjava/nio/ShortBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantsvEXT, "glVariantsvEXT", (void**)&glVariantsvEXT},
		{"nglVariantfvEXT", "(ILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantfvEXT, "glVariantfvEXT", (void**)&glVariantfvEXT},
		{"nglVariantivEXT", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantivEXT, "glVariantivEXT", (void**)&glVariantivEXT},
		{"nglVariantubvEXT", "(ILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantubvEXT, "glVariantubvEXT", (void**)&glVariantubvEXT},
		{"nglVariantusvEXT", "(ILjava/nio/ShortBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantusvEXT, "glVariantusvEXT", (void**)&glVariantusvEXT},
		{"nglVariantuivEXT", "(ILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantuivEXT, "glVariantuivEXT", (void**)&glVariantuivEXT},
		{"nglVariantPointerEXT", "(IIILjava/nio/Buffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantPointerEXT, "glVariantPointerEXT", (void**)&glVariantPointerEXT},
		{"nglVariantPointerEXTVBO", "(IIII)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantPointerEXTVBO, NULL, NULL},
		{"glEnableVariantClientStateEXT", "(I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glEnableVariantClientStateEXT, "glEnableVariantClientStateEXT", (void**)&glEnableVariantClientStateEXT},
		{"glDisableVariantClientStateEXT", "(I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glDisableVariantClientStateEXT, "glDisableVariantClientStateEXT", (void**)&glDisableVariantClientStateEXT},
		{"glBindLightParameterEXT", "(II)I", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glBindLightParameterEXT, "glBindLightParameterEXT", (void**)&glBindLightParameterEXT},
		{"glBindMaterialParameterEXT", "(II)I", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glBindMaterialParameterEXT, "glBindMaterialParameterEXT", (void**)&glBindMaterialParameterEXT},
		{"glBindTexGenParameterEXT", "(III)I", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glBindTexGenParameterEXT, "glBindTexGenParameterEXT", (void**)&glBindTexGenParameterEXT},
		{"glBindTextureUnitParameterEXT", "(II)I", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glBindTextureUnitParameterEXT, "glBindTextureUnitParameterEXT", (void**)&glBindTextureUnitParameterEXT},
		{"glBindParameterEXT", "(I)I", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glBindParameterEXT, "glBindParameterEXT", (void**)&glBindParameterEXT},
		{"glIsVariantEnabledEXT", "(II)Z", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glIsVariantEnabledEXT, "glIsVariantEnabledEXT", (void**)&glIsVariantEnabledEXT},
		{"nglGetVariantBooleanvEXT", "(IILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantBooleanvEXT, "glGetVariantBooleanvEXT", (void**)&glGetVariantBooleanvEXT},
		{"nglGetVariantIntegervEXT", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantIntegervEXT, "glGetVariantIntegervEXT", (void**)&glGetVariantIntegervEXT},
		{"nglGetVariantFloatvEXT", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantFloatvEXT, "glGetVariantFloatvEXT", (void**)&glGetVariantFloatvEXT},
		{"glGetVariantPointerEXT", "(III)Ljava/nio/ByteBuffer;", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_glGetVariantPointerEXT, "glGetVariantPointervEXT", (void**)&glGetVariantPointervEXT},
		{"nglGetInvariantBooleanvEXT", "(IILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetInvariantBooleanvEXT, "glGetInvariantBooleanvEXT", (void**)&glGetInvariantBooleanvEXT},
		{"nglGetInvariantIntegervEXT", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetInvariantIntegervEXT, "glGetInvariantIntegervEXT", (void**)&glGetInvariantIntegervEXT},
		{"nglGetInvariantFloatvEXT", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetInvariantFloatvEXT, "glGetInvariantFloatvEXT", (void**)&glGetInvariantFloatvEXT},
		{"nglGetLocalConstantBooleanvEXT", "(IILjava/nio/ByteBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetLocalConstantBooleanvEXT, "glGetLocalConstantBooleanvEXT", (void**)&glGetLocalConstantBooleanvEXT},
		{"nglGetLocalConstantIntegervEXT", "(IILjava/nio/IntBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetLocalConstantIntegervEXT, "glGetLocalConstantIntegervEXT", (void**)&glGetLocalConstantIntegervEXT},
		{"nglGetLocalConstantFloatvEXT", "(IILjava/nio/FloatBuffer;I)V", (void*)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetLocalConstantFloatvEXT, "glGetLocalConstantFloatvEXT", (void**)&glGetLocalConstantFloatvEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = ext_ResetClass(env, "org/lwjgl/opengl/EXTVertexShader");
	if (extgl_Extensions.GL_EXT_vertex_shader)
		extgl_InitializeClass(env, clazz, ext_set, "GL_EXT_vertex_shader", num_functions, functions);
}
