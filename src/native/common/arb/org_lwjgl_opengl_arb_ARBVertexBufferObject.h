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
// MACHINE GENERATED HEADER OF CLASS: org.lwjgl.opengl.arb.ARBVertexBufferObject
// ----------------------------------

#include <jni.h>

#ifndef _Included_org_lwjgl_opengl_arb_ARBVertexBufferObject
#define _Included_org_lwjgl_opengl_arb_ARBVertexBufferObject

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	nglBindBufferARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_nglBindBufferARB
	(JNIEnv *, jclass, jint, jint);

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	nglDeleteBuffersARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_nglDeleteBuffersARB
	(JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	nglGenBuffersARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_nglGenBuffersARB
	(JNIEnv *, jclass, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	glIsBufferARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_glIsBufferARB
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	nglBufferDataARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_nglBufferDataARB
	(JNIEnv *, jclass, jint, jint, jobject, jint, jint);

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	nglBufferSubDataARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_nglBufferSubDataARB
	(JNIEnv *, jclass, jint, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	nglGetBufferSubDataARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_nglGetBufferSubDataARB
	(JNIEnv *, jclass, jint, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	glMapBufferARB
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_glMapBufferARB
	(JNIEnv *, jclass, jint, jint, jint, jobject);

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	glUnmapBufferARB
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_glUnmapBufferARB
	(JNIEnv *, jclass, jint);

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	nglGetBufferParameterivARB
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_nglGetBufferParameterivARB
	(JNIEnv *, jclass, jint, jint, jobject, jint);

/*
 * Class:	org.lwjgl.opengl.arb.ARBVertexBufferObject
 * Method:	glGetBufferPointerARB
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_arb_ARBVertexBufferObject_glGetBufferPointerARB
	(JNIEnv *, jclass, jint, jint, jint);

#ifdef __cplusplus
}
#endif

#endif
