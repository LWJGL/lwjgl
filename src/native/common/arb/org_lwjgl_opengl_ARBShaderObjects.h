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
// MACHINE GENERATED HEADER OF CLASS: org.lwjgl.opengl.ARBShaderObjects
// ----------------------------------

#include <jni.h>

#ifndef _Included_org_lwjgl_opengl_ARBShaderObjects
#define _Included_org_lwjgl_opengl_ARBShaderObjects

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glDeleteObjectARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glDeleteObjectARB
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glGetHandleARB
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glGetHandleARB
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glDetachObjectARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glDetachObjectARB
	(JNIEnv *, jclass, jint, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glCreateShaderObjectARB
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glCreateShaderObjectARB
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	initShaderSource
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_initShaderSource
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	setShaderString
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_setShaderString
	(JNIEnv *, jclass, jint, jobject, jint, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglShaderSourceARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglShaderSourceARB
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glCompileShaderARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glCompileShaderARB
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glCreateProgramObjectARB
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glCreateProgramObjectARB
	(JNIEnv *, jclass);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glAttachObjectARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glAttachObjectARB
	(JNIEnv *, jclass, jint, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glLinkProgramARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glLinkProgramARB
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUseProgramObjectARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUseProgramObjectARB
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glValidateProgramARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glValidateProgramARB
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform1fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform1fARB
	(JNIEnv *, jclass, jint, jfloat);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform2fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform2fARB
	(JNIEnv *, jclass, jint, jfloat, jfloat);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform3fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform3fARB
	(JNIEnv *, jclass, jint, jfloat, jfloat, jfloat);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform4fARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform4fARB
	(JNIEnv *, jclass, jint, jfloat, jfloat, jfloat, jfloat);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform1iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform1iARB
	(JNIEnv *, jclass, jint, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform2iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform2iARB
	(JNIEnv *, jclass, jint, jint, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform3iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform3iARB
	(JNIEnv *, jclass, jint, jint, jint, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	glUniform4iARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_glUniform4iARB
	(JNIEnv *, jclass, jint, jint, jint, jint, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform1fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1fvARB
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform2fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2fvARB
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform3fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3fvARB
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform4fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4fvARB
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform1ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform1ivARB
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform2ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform2ivARB
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform3ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform3ivARB
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniform4ivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniform4ivARB
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniformMatrix2fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix2fvARB
	(JNIEnv *, jclass, jint, jint, jboolean, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniformMatrix3fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix3fvARB
	(JNIEnv *, jclass, jint, jint, jboolean, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglUniformMatrix4fvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglUniformMatrix4fvARB
	(JNIEnv *, jclass, jint, jint, jboolean, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetObjectParameterfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetObjectParameterfvARB
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetObjectParameterivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetObjectParameterivARB
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetInfoLogARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetInfoLogARB
	(JNIEnv *, jclass, jint, jint, jobject, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetAttachedObjectsARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetAttachedObjectsARB
	(JNIEnv *, jclass, jint, jint, jobject, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetUniformLocationARB
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformLocationARB
	(JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetActiveUniformARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetActiveUniformARB
	(JNIEnv *, jclass, jint, jint, jint, jobject, jint, jobject, jint, jobject, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetUniformfvARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformfvARB
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetUniformivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetUniformivARB
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.ARBShaderObjects
 * Method:	nglGetShaderSourceARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBShaderObjects_nglGetShaderSourceARB
	(JNIEnv *, jclass, jint, jint, jobject, jint, jobject, jint);

#ifdef __cplusplus
}
#endif

#endif
