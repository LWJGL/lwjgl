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
// IMPLEMENTATION OF NATIVE METHODS FOR CLASS: org.lwjgl.opengl.NVHalfFloat
// ----------------------------------

#include "extgl.h"


typedef unsigned short GLhalf;

typedef void (APIENTRY * glVertex2hNVPROC) (GLhalf x, GLhalf y);
typedef void (APIENTRY * glVertex3hNVPROC) (GLhalf x, GLhalf y, GLhalf z);
typedef void (APIENTRY * glVertex4hNVPROC) (GLhalf x, GLhalf y, GLhalf z, GLhalf w);
typedef void (APIENTRY * glNormal3hNVPROC) (GLhalf nx, GLhalf ny, GLhalf nz);
typedef void (APIENTRY * glColor3hNVPROC) (GLhalf red, GLhalf green, GLhalf blue);
typedef void (APIENTRY * glColor4hNVPROC) (GLhalf red, GLhalf green, GLhalf blue, GLhalf alpha);
typedef void (APIENTRY * glTexCoord1hNVPROC) (GLhalf s);
typedef void (APIENTRY * glTexCoord2hNVPROC) (GLhalf s, GLhalf t);
typedef void (APIENTRY * glTexCoord3hNVPROC) (GLhalf s, GLhalf t, GLhalf r);
typedef void (APIENTRY * glTexCoord4hNVPROC) (GLhalf s, GLhalf t, GLhalf r, GLhalf q);
typedef void (APIENTRY * glMultiTexCoord1hNVPROC) (GLenum target, GLhalf s);
typedef void (APIENTRY * glMultiTexCoord2hNVPROC) (GLenum target, GLhalf s, GLhalf t);
typedef void (APIENTRY * glMultiTexCoord3hNVPROC) (GLenum target, GLhalf s, GLhalf t, GLhalf r);
typedef void (APIENTRY * glMultiTexCoord4hNVPROC) (GLenum target, GLhalf s, GLhalf t, GLhalf r, GLhalf q);
typedef void (APIENTRY * glFogCoordhNVPROC) (GLhalf fog);
typedef void (APIENTRY * glSecondaryColor3hNVPROC) (GLhalf red, GLhalf green, GLhalf blue);
typedef void (APIENTRY * glVertexAttrib1hNVPROC) (GLuint index, GLhalf x);
typedef void (APIENTRY * glVertexAttrib2hNVPROC) (GLuint index, GLhalf x, GLhalf y);
typedef void (APIENTRY * glVertexAttrib3hNVPROC) (GLuint index, GLhalf x, GLhalf y, GLhalf z);
typedef void (APIENTRY * glVertexAttrib4hNVPROC) (GLuint index, GLhalf x, GLhalf y, GLhalf z, GLhalf w);
typedef void (APIENTRY * glVertexAttribs1hvNVPROC) (GLuint index, GLsizei n, const GLhalf *v);
typedef void (APIENTRY * glVertexAttribs2hvNVPROC) (GLuint index, GLsizei n, const GLhalf *v);
typedef void (APIENTRY * glVertexAttribs3hvNVPROC) (GLuint index, GLsizei n, const GLhalf *v);
typedef void (APIENTRY * glVertexAttribs4hvNVPROC) (GLuint index, GLsizei n, const GLhalf *v);

static glVertex2hNVPROC glVertex2hNV;
static glVertex3hNVPROC glVertex3hNV;
static glVertex4hNVPROC glVertex4hNV;
static glNormal3hNVPROC glNormal3hNV;
static glColor3hNVPROC glColor3hNV;
static glColor4hNVPROC glColor4hNV;
static glTexCoord1hNVPROC glTexCoord1hNV;
static glTexCoord2hNVPROC glTexCoord2hNV;
static glTexCoord3hNVPROC glTexCoord3hNV;
static glTexCoord4hNVPROC glTexCoord4hNV;
static glMultiTexCoord1hNVPROC glMultiTexCoord1hNV;
static glMultiTexCoord2hNVPROC glMultiTexCoord2hNV;
static glMultiTexCoord3hNVPROC glMultiTexCoord3hNV;
static glMultiTexCoord4hNVPROC glMultiTexCoord4hNV;
static glFogCoordhNVPROC glFogCoordhNV;
static glSecondaryColor3hNVPROC glSecondaryColor3hNV;
static glVertexAttrib1hNVPROC glVertexAttrib1hNV;
static glVertexAttrib2hNVPROC glVertexAttrib2hNV;
static glVertexAttrib3hNVPROC glVertexAttrib3hNV;
static glVertexAttrib4hNVPROC glVertexAttrib4hNV;
static glVertexAttribs1hvNVPROC glVertexAttribs1hvNV;
static glVertexAttribs2hvNVPROC glVertexAttribs2hvNV;
static glVertexAttribs3hvNVPROC glVertexAttribs3hvNV;
static glVertexAttribs4hvNVPROC glVertexAttribs4hvNV;

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glVertex2hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertex2hNV
	(JNIEnv * env, jclass clazz, jshort x, jshort y)
{
	glVertex2hNV(x, y);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glVertex3hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertex3hNV
	(JNIEnv * env, jclass clazz, jshort x, jshort y, jshort z)
{
	glVertex3hNV(x, y, z);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glVertex4hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertex4hNV
	(JNIEnv * env, jclass clazz, jshort x, jshort y, jshort z, jshort w)
{
	glVertex4hNV(x, y, z, w);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glNormal3hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glNormal3hNV
	(JNIEnv * env, jclass clazz, jshort nx, jshort ny, jshort nz)
{
	glNormal3hNV(nx, ny, nz);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glColor3hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glColor3hNV
	(JNIEnv * env, jclass clazz, jshort red, jshort green, jshort blue)
{
	glColor3hNV(red, green, blue);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glColor4hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glColor4hNV
	(JNIEnv * env, jclass clazz, jshort red, jshort green, jshort blue, jshort alpha)
{
	glColor4hNV(red, green, blue, alpha);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glTexCoord1hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord1hNV
	(JNIEnv * env, jclass clazz, jshort s)
{
	glTexCoord1hNV(s);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glTexCoord2hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord2hNV
	(JNIEnv * env, jclass clazz, jshort s, jshort t)
{
	glTexCoord2hNV(s, t);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glTexCoord3hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord3hNV
	(JNIEnv * env, jclass clazz, jshort s, jshort t, jshort r)
{
	glTexCoord3hNV(s, t, r);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glTexCoord4hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord4hNV
	(JNIEnv * env, jclass clazz, jshort s, jshort t, jshort r, jshort q)
{
	glTexCoord4hNV(s, t, r, q);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glMultiTexCoord1hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord1hNV
	(JNIEnv * env, jclass clazz, jint target, jshort s)
{
	glMultiTexCoord1hNV(target, s);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glMultiTexCoord2hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord2hNV
	(JNIEnv * env, jclass clazz, jint target, jshort s, jshort t)
{
	glMultiTexCoord2hNV(target, s, t);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glMultiTexCoord3hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord3hNV
	(JNIEnv * env, jclass clazz, jint target, jshort s, jshort t, jshort r)
{
	glMultiTexCoord3hNV(target, s, t, r);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glMultiTexCoord4hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord4hNV
	(JNIEnv * env, jclass clazz, jint target, jshort s, jshort t, jshort r, jshort q)
{
	glMultiTexCoord4hNV(target, s, t, r, q);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glFogCoordhNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glFogCoordhNV
	(JNIEnv * env, jclass clazz, jshort fog)
{
	glFogCoordhNV(fog);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glSecondaryColor3hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glSecondaryColor3hNV
	(JNIEnv * env, jclass clazz, jshort red, jshort green, jshort blue)
{
	glSecondaryColor3hNV(red, green, blue);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glVertexAttrib1hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib1hNV
	(JNIEnv * env, jclass clazz, jint index, jshort x)
{
	glVertexAttrib1hNV(index, x);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glVertexAttrib2hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib2hNV
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y)
{
	glVertexAttrib2hNV(index, x, y);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glVertexAttrib3hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib3hNV
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y, jshort z)
{
	glVertexAttrib3hNV(index, x, y, z);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glVertexAttrib4hNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib4hNV
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y, jshort z, jshort w)
{
	glVertexAttrib4hNV(index, x, y, z, w);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	nglVertexAttribs1hvNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs1hvNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject attribs, jint attribsOffset)
{
	GLushort *attribs_ptr = (GLushort *)env->GetDirectBufferAddress(attribs) + attribsOffset;
	glVertexAttribs1hvNV(index, n, attribs_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	nglVertexAttribs2hvNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs2hvNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject attribs, jint attribsOffset)
{
	GLushort *attribs_ptr = (GLushort *)env->GetDirectBufferAddress(attribs) + attribsOffset;
	glVertexAttribs2hvNV(index, n, attribs_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	nglVertexAttribs3hvNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs3hvNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject attribs, jint attribsOffset)
{
	GLushort *attribs_ptr = (GLushort *)env->GetDirectBufferAddress(attribs) + attribsOffset;
	glVertexAttribs3hvNV(index, n, attribs_ptr);
	
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	nglVertexAttribs4hvNV
 */
static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs4hvNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject attribs, jint attribsOffset)
{
	GLushort *attribs_ptr = (GLushort *)env->GetDirectBufferAddress(attribs) + attribsOffset;
	glVertexAttribs4hvNV(index, n, attribs_ptr);
	
}

void extgl_InitNVHalfFloat(JNIEnv *env, jobject ext_set)
{
	JavaMethodAndExtFunction functions[] = {
		{"glVertex2hNV", "(SS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glVertex2hNV, "glVertex2hNV", (void**)&glVertex2hNV},
		{"glVertex3hNV", "(SSS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glVertex3hNV, "glVertex3hNV", (void**)&glVertex3hNV},
		{"glVertex4hNV", "(SSSS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glVertex4hNV, "glVertex4hNV", (void**)&glVertex4hNV},
		{"glNormal3hNV", "(SSS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glNormal3hNV, "glNormal3hNV", (void**)&glNormal3hNV},
		{"glColor3hNV", "(SSS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glColor3hNV, "glColor3hNV", (void**)&glColor3hNV},
		{"glColor4hNV", "(SSSS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glColor4hNV, "glColor4hNV", (void**)&glColor4hNV},
		{"glTexCoord1hNV", "(S)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord1hNV, "glTexCoord1hNV", (void**)&glTexCoord1hNV},
		{"glTexCoord2hNV", "(SS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord2hNV, "glTexCoord2hNV", (void**)&glTexCoord2hNV},
		{"glTexCoord3hNV", "(SSS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord3hNV, "glTexCoord3hNV", (void**)&glTexCoord3hNV},
		{"glTexCoord4hNV", "(SSSS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord4hNV, "glTexCoord4hNV", (void**)&glTexCoord4hNV},
		{"glMultiTexCoord1hNV", "(IS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord1hNV, "glMultiTexCoord1hNV", (void**)&glMultiTexCoord1hNV},
		{"glMultiTexCoord2hNV", "(ISS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord2hNV, "glMultiTexCoord2hNV", (void**)&glMultiTexCoord2hNV},
		{"glMultiTexCoord3hNV", "(ISSS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord3hNV, "glMultiTexCoord3hNV", (void**)&glMultiTexCoord3hNV},
		{"glMultiTexCoord4hNV", "(ISSSS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord4hNV, "glMultiTexCoord4hNV", (void**)&glMultiTexCoord4hNV},
		{"glFogCoordhNV", "(S)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glFogCoordhNV, "glFogCoordhNV", (void**)&glFogCoordhNV},
		{"glSecondaryColor3hNV", "(SSS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glSecondaryColor3hNV, "glSecondaryColor3hNV", (void**)&glSecondaryColor3hNV},
		{"glVertexAttrib1hNV", "(IS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib1hNV, "glVertexAttrib1hNV", (void**)&glVertexAttrib1hNV},
		{"glVertexAttrib2hNV", "(ISS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib2hNV, "glVertexAttrib2hNV", (void**)&glVertexAttrib2hNV},
		{"glVertexAttrib3hNV", "(ISSS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib3hNV, "glVertexAttrib3hNV", (void**)&glVertexAttrib3hNV},
		{"glVertexAttrib4hNV", "(ISSSS)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib4hNV, "glVertexAttrib4hNV", (void**)&glVertexAttrib4hNV},
		{"nglVertexAttribs1hvNV", "(IILjava/nio/ShortBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs1hvNV, "glVertexAttribs1hvNV", (void**)&glVertexAttribs1hvNV},
		{"nglVertexAttribs2hvNV", "(IILjava/nio/ShortBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs2hvNV, "glVertexAttribs2hvNV", (void**)&glVertexAttribs2hvNV},
		{"nglVertexAttribs3hvNV", "(IILjava/nio/ShortBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs3hvNV, "glVertexAttribs3hvNV", (void**)&glVertexAttribs3hvNV},
		{"nglVertexAttribs4hvNV", "(IILjava/nio/ShortBuffer;I)V", (void*)&Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs4hvNV, "glVertexAttribs4hvNV", (void**)&glVertexAttribs4hvNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	jclass clazz = ext_ResetClass(env, "org/lwjgl/opengl/NVHalfFloat");
	if (extgl_Extensions.GL_NV_half_float)
		extgl_InitializeClass(env, clazz, ext_set, "GL_NV_half_float", num_functions, functions);
}
