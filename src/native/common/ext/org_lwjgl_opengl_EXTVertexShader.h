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
// MACHINE GENERATED HEADER OF CLASS: org.lwjgl.opengl.EXTVertexShader
// ----------------------------------

#include <jni.h>

#ifndef _Included_org_lwjgl_opengl_EXTVertexShader
#define _Included_org_lwjgl_opengl_EXTVertexShader

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glBeginVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBeginVertexShaderEXT
	(JNIEnv *, jclass);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glEndVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glEndVertexShaderEXT
	(JNIEnv *, jclass);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glBindVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindVertexShaderEXT
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glGenVertexShadersEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glGenVertexShadersEXT
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glDeleteVertexShaderEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glDeleteVertexShaderEXT
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glShaderOp1EXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glShaderOp1EXT
	(JNIEnv *, jclass, jint, jint, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glShaderOp2EXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glShaderOp2EXT
	(JNIEnv *, jclass, jint, jint, jint, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glShaderOp3EXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glShaderOp3EXT
	(JNIEnv *, jclass, jint, jint, jint, jint, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glSwizzleEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glSwizzleEXT
	(JNIEnv *, jclass, jint, jint, jint, jint, jint, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glWriteMaskEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glWriteMaskEXT
	(JNIEnv *, jclass, jint, jint, jint, jint, jint, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glInsertComponentEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glInsertComponentEXT
	(JNIEnv *, jclass, jint, jint, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glExtractComponentEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glExtractComponentEXT
	(JNIEnv *, jclass, jint, jint, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glGenSymbolsEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glGenSymbolsEXT
	(JNIEnv *, jclass, jint, jint, jint, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglSetInvariantEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglSetInvariantEXT
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglSetLocalConstantEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglSetLocalConstantEXT
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantbvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantbvEXT
	(JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantsvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantsvEXT
	(JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantfvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantfvEXT
	(JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantivEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantivEXT
	(JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantubvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantubvEXT
	(JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantusvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantusvEXT
	(JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantuivEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantuivEXT
	(JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantPointerEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantPointerEXT
	(JNIEnv *, jclass, jint, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglVariantPointerEXTVBO
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantPointerEXTVBO
	(JNIEnv *, jclass, jint, jint, jint, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glEnableVariantClientStateEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glEnableVariantClientStateEXT
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glDisableVariantClientStateEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glDisableVariantClientStateEXT
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glBindLightParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindLightParameterEXT
	(JNIEnv *, jclass, jint, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glBindMaterialParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindMaterialParameterEXT
	(JNIEnv *, jclass, jint, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glBindTexGenParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindTexGenParameterEXT
	(JNIEnv *, jclass, jint, jint, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glBindTextureUnitParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindTextureUnitParameterEXT
	(JNIEnv *, jclass, jint, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glBindParameterEXT
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindParameterEXT
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glIsVariantEnabledEXT
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glIsVariantEnabledEXT
	(JNIEnv *, jclass, jint, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetVariantBooleanvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantBooleanvEXT
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetVariantIntegervEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantIntegervEXT
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetVariantFloatvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantFloatvEXT
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	glGetVariantPointerEXT
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glGetVariantPointerEXT
	(JNIEnv *, jclass, jint, jint, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetInvariantBooleanvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetInvariantBooleanvEXT
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetInvariantIntegervEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetInvariantIntegervEXT
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetInvariantFloatvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetInvariantFloatvEXT
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetLocalConstantBooleanvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetLocalConstantBooleanvEXT
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetLocalConstantIntegervEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetLocalConstantIntegervEXT
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.EXTVertexShader
 * Method:	nglGetLocalConstantFloatvEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetLocalConstantFloatvEXT
	(JNIEnv *, jclass, jint, jint, jobject, jint);

#ifdef __cplusplus
}
#endif

#endif
