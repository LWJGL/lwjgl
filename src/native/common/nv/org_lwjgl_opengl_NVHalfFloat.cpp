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

#include "org_lwjgl_opengl_NVHalfFloat.h"
#include "extgl.h"
#include "checkGLerror.h"

typedef unsigned short GLhalf;

typedef void (APIENTRY * glVertex2hNVPROC) (GLhalf x, GLhalf y);
typedef void (APIENTRY * glVertex2hvNVPROC) (const GLhalf *v);
typedef void (APIENTRY * glVertex3hNVPROC) (GLhalf x, GLhalf y, GLhalf z);
typedef void (APIENTRY * glVertex3hvNVPROC) (const GLhalf *v);
typedef void (APIENTRY * glVertex4hNVPROC) (GLhalf x, GLhalf y, GLhalf z, GLhalf w);
typedef void (APIENTRY * glVertex4hvNVPROC) (const GLhalf *v);
typedef void (APIENTRY * glNormal3hNVPROC) (GLhalf nx, GLhalf ny, GLhalf nz);
typedef void (APIENTRY * glNormal3hvNVPROC) (const GLhalf *v);
typedef void (APIENTRY * glColor3hNVPROC) (GLhalf red, GLhalf green, GLhalf blue);
typedef void (APIENTRY * glColor3hvNVPROC) (const GLhalf *v);
typedef void (APIENTRY * glColor4hNVPROC) (GLhalf red, GLhalf green, GLhalf blue, GLhalf alpha);
typedef void (APIENTRY * glColor4hvNVPROC) (const GLhalf *v);
typedef void (APIENTRY * glTexCoord1hNVPROC) (GLhalf s);
typedef void (APIENTRY * glTexCoord1hvNVPROC) (const GLhalf *v);
typedef void (APIENTRY * glTexCoord2hNVPROC) (GLhalf s, GLhalf t);
typedef void (APIENTRY * glTexCoord2hvNVPROC) (const GLhalf *v);
typedef void (APIENTRY * glTexCoord3hNVPROC) (GLhalf s, GLhalf t, GLhalf r);
typedef void (APIENTRY * glTexCoord3hvNVPROC) (const GLhalf *v);
typedef void (APIENTRY * glTexCoord4hNVPROC) (GLhalf s, GLhalf t, GLhalf r, GLhalf q);
typedef void (APIENTRY * glTexCoord4hvNVPROC) (const GLhalf *v);
typedef void (APIENTRY * glMultiTexCoord1hNVPROC) (GLenum target, GLhalf s);
typedef void (APIENTRY * glMultiTexCoord1hvNVPROC) (GLenum target, const GLhalf *v);
typedef void (APIENTRY * glMultiTexCoord2hNVPROC) (GLenum target, GLhalf s, GLhalf t);
typedef void (APIENTRY * glMultiTexCoord2hvNVPROC) (GLenum target, const GLhalf *v);
typedef void (APIENTRY * glMultiTexCoord3hNVPROC) (GLenum target, GLhalf s, GLhalf t, GLhalf r);
typedef void (APIENTRY * glMultiTexCoord3hvNVPROC) (GLenum target, const GLhalf *v);
typedef void (APIENTRY * glMultiTexCoord4hNVPROC) (GLenum target, GLhalf s, GLhalf t, GLhalf r, GLhalf q);
typedef void (APIENTRY * glMultiTexCoord4hvNVPROC) (GLenum target, const GLhalf *v);
typedef void (APIENTRY * glFogCoordhNVPROC) (GLhalf fog);
typedef void (APIENTRY * glFogCoordhvNVPROC) (const GLhalf *fog);
typedef void (APIENTRY * glSecondaryColor3hNVPROC) (GLhalf red, GLhalf green, GLhalf blue);
typedef void (APIENTRY * glSecondaryColor3hvNVPROC) (const GLhalf *v);
typedef void (APIENTRY * glVertexWeighthNVPROC) (GLhalf weight);
typedef void (APIENTRY * glVertexWeighthvNVPROC) (const GLhalf *weight);
typedef void (APIENTRY * glVertexAttrib1hNVPROC) (GLuint index, GLhalf x);
typedef void (APIENTRY * glVertexAttrib1hvNVPROC) (GLuint index, const GLhalf *v);
typedef void (APIENTRY * glVertexAttrib2hNVPROC) (GLuint index, GLhalf x, GLhalf y);
typedef void (APIENTRY * glVertexAttrib2hvNVPROC) (GLuint index, const GLhalf *v);
typedef void (APIENTRY * glVertexAttrib3hNVPROC) (GLuint index, GLhalf x, GLhalf y, GLhalf z);
typedef void (APIENTRY * glVertexAttrib3hvNVPROC) (GLuint index, const GLhalf *v);
typedef void (APIENTRY * glVertexAttrib4hNVPROC) (GLuint index, GLhalf x, GLhalf y, GLhalf z, GLhalf w);
typedef void (APIENTRY * glVertexAttrib4hvNVPROC) (GLuint index, const GLhalf *v);
typedef void (APIENTRY * glVertexAttribs1hvNVPROC) (GLuint index, GLsizei n, const GLhalf *v);
typedef void (APIENTRY * glVertexAttribs2hvNVPROC) (GLuint index, GLsizei n, const GLhalf *v);
typedef void (APIENTRY * glVertexAttribs3hvNVPROC) (GLuint index, GLsizei n, const GLhalf *v);
typedef void (APIENTRY * glVertexAttribs4hvNVPROC) (GLuint index, GLsizei n, const GLhalf *v);

static glVertex2hNVPROC glVertex2hNV;
static glVertex2hvNVPROC glVertex2hvNV;
static glVertex3hNVPROC glVertex3hNV;
static glVertex3hvNVPROC glVertex3hvNV;
static glVertex4hNVPROC glVertex4hNV;
static glVertex4hvNVPROC glVertex4hvNV;
static glNormal3hNVPROC glNormal3hNV;
static glNormal3hvNVPROC glNormal3hvNV;
static glColor3hNVPROC glColor3hNV;
static glColor3hvNVPROC glColor3hvNV;
static glColor4hNVPROC glColor4hNV;
static glColor4hvNVPROC glColor4hvNV;
static glTexCoord1hNVPROC glTexCoord1hNV;
static glTexCoord1hvNVPROC glTexCoord1hvNV;
static glTexCoord2hNVPROC glTexCoord2hNV;
static glTexCoord2hvNVPROC glTexCoord2hvNV;
static glTexCoord3hNVPROC glTexCoord3hNV;
static glTexCoord3hvNVPROC glTexCoord3hvNV;
static glTexCoord4hNVPROC glTexCoord4hNV;
static glTexCoord4hvNVPROC glTexCoord4hvNV;
static glMultiTexCoord1hNVPROC glMultiTexCoord1hNV;
static glMultiTexCoord1hvNVPROC glMultiTexCoord1hvNV;
static glMultiTexCoord2hNVPROC glMultiTexCoord2hNV;
static glMultiTexCoord2hvNVPROC glMultiTexCoord2hvNV;
static glMultiTexCoord3hNVPROC glMultiTexCoord3hNV;
static glMultiTexCoord3hvNVPROC glMultiTexCoord3hvNV;
static glMultiTexCoord4hNVPROC glMultiTexCoord4hNV;
static glMultiTexCoord4hvNVPROC glMultiTexCoord4hvNV;
static glFogCoordhNVPROC glFogCoordhNV;
static glFogCoordhvNVPROC glFogCoordhvNV;
static glSecondaryColor3hNVPROC glSecondaryColor3hNV;
static glSecondaryColor3hvNVPROC glSecondaryColor3hvNV;
static glVertexWeighthNVPROC glVertexWeighthNV;
static glVertexWeighthvNVPROC glVertexWeighthvNV;
static glVertexAttrib1hNVPROC glVertexAttrib1hNV;
static glVertexAttrib1hvNVPROC glVertexAttrib1hvNV;
static glVertexAttrib2hNVPROC glVertexAttrib2hNV;
static glVertexAttrib2hvNVPROC glVertexAttrib2hvNV;
static glVertexAttrib3hNVPROC glVertexAttrib3hNV;
static glVertexAttrib3hvNVPROC glVertexAttrib3hvNV;
static glVertexAttrib4hNVPROC glVertexAttrib4hNV;
static glVertexAttrib4hvNVPROC glVertexAttrib4hvNV;
static glVertexAttribs1hvNVPROC glVertexAttribs1hvNV;
static glVertexAttribs2hvNVPROC glVertexAttribs2hvNV;
static glVertexAttribs3hvNVPROC glVertexAttribs3hvNV;
static glVertexAttribs4hvNVPROC glVertexAttribs4hvNV;

void extgl_InitNVHalfFloat(JNIEnv *env, jobject ext_set)
{
	if (!extgl_Extensions.GL_NV_half_float)
		return;

	glVertex2hNV = (glVertex2hNVPROC) extgl_GetProcAddress("glVertex2hNV");
	glVertex2hvNV = (glVertex2hvNVPROC) extgl_GetProcAddress("glVertex2hvNV");
	glVertex3hNV = (glVertex3hNVPROC) extgl_GetProcAddress("glVertex3hNV");
	glVertex3hvNV = (glVertex3hvNVPROC) extgl_GetProcAddress("glVertex3hvNV");
	glVertex4hNV = (glVertex4hNVPROC) extgl_GetProcAddress("glVertex4hNV");
	glVertex4hvNV = (glVertex4hvNVPROC) extgl_GetProcAddress("glVertex4hvNV");
	glNormal3hNV = (glNormal3hNVPROC) extgl_GetProcAddress("glNormal3hNV");
	glNormal3hvNV = (glNormal3hvNVPROC) extgl_GetProcAddress("glNormal3hvNV");
	glColor3hNV = (glColor3hNVPROC) extgl_GetProcAddress("glColor3hNV");
	glColor3hvNV = (glColor3hvNVPROC) extgl_GetProcAddress("glColor3hvNV");
	glColor4hNV = (glColor4hNVPROC) extgl_GetProcAddress("glColor4hNV");
	glColor4hvNV = (glColor4hvNVPROC) extgl_GetProcAddress("glColor4hvNV");
	glTexCoord1hNV = (glTexCoord1hNVPROC) extgl_GetProcAddress("glTexCoord1hNV");
	glTexCoord1hvNV = (glTexCoord1hvNVPROC) extgl_GetProcAddress("glTexCoord1hvNV");
	glTexCoord2hNV = (glTexCoord2hNVPROC) extgl_GetProcAddress("glTexCoord2hNV");
	glTexCoord2hvNV = (glTexCoord2hvNVPROC) extgl_GetProcAddress("glTexCoord2hvNV");
	glTexCoord3hNV = (glTexCoord3hNVPROC) extgl_GetProcAddress("glTexCoord3hNV");
	glTexCoord3hvNV = (glTexCoord3hvNVPROC) extgl_GetProcAddress("glTexCoord3hvNV");
	glTexCoord4hNV = (glTexCoord4hNVPROC) extgl_GetProcAddress("glTexCoord4hNV");
	glTexCoord4hvNV = (glTexCoord4hvNVPROC) extgl_GetProcAddress("glTexCoord4hvNV");
	glMultiTexCoord1hNV = (glMultiTexCoord1hNVPROC) extgl_GetProcAddress("glMultiTexCoord1hNV");
	glMultiTexCoord1hvNV = (glMultiTexCoord1hvNVPROC) extgl_GetProcAddress("glMultiTexCoord1hvNV");
	glMultiTexCoord2hNV = (glMultiTexCoord2hNVPROC) extgl_GetProcAddress("glMultiTexCoord2hNV");
	glMultiTexCoord2hvNV = (glMultiTexCoord2hvNVPROC) extgl_GetProcAddress("glMultiTexCoord2hvNV");
	glMultiTexCoord3hNV = (glMultiTexCoord3hNVPROC) extgl_GetProcAddress("glMultiTexCoord3hNV");
	glMultiTexCoord3hvNV = (glMultiTexCoord3hvNVPROC) extgl_GetProcAddress("glMultiTexCoord3hvNV");
	glMultiTexCoord4hNV = (glMultiTexCoord4hNVPROC) extgl_GetProcAddress("glMultiTexCoord4hNV");
	glMultiTexCoord4hvNV = (glMultiTexCoord4hvNVPROC) extgl_GetProcAddress("glMultiTexCoord4hvNV");
	glFogCoordhNV = (glFogCoordhNVPROC) extgl_GetProcAddress("glFogCoordhNV");
	glFogCoordhvNV = (glFogCoordhvNVPROC) extgl_GetProcAddress("glFogCoordhvNV");
	glSecondaryColor3hNV = (glSecondaryColor3hNVPROC) extgl_GetProcAddress("glSecondaryColor3hNV");
	glSecondaryColor3hvNV = (glSecondaryColor3hvNVPROC) extgl_GetProcAddress("glSecondaryColor3hvNV");
	glVertexWeighthNV = (glVertexWeighthNVPROC) extgl_GetProcAddress("glVertexWeighthNV");
	glVertexWeighthvNV = (glVertexWeighthvNVPROC) extgl_GetProcAddress("glVertexWeighthvNV");
	glVertexAttrib1hNV = (glVertexAttrib1hNVPROC) extgl_GetProcAddress("glVertexAttrib1hNV");
	glVertexAttrib1hvNV = (glVertexAttrib1hvNVPROC) extgl_GetProcAddress("glVertexAttrib1hvNV");
	glVertexAttrib2hNV = (glVertexAttrib2hNVPROC) extgl_GetProcAddress("glVertexAttrib2hNV");
	glVertexAttrib2hvNV = (glVertexAttrib2hvNVPROC) extgl_GetProcAddress("glVertexAttrib2hvNV");
	glVertexAttrib3hNV = (glVertexAttrib3hNVPROC) extgl_GetProcAddress("glVertexAttrib3hNV");
	glVertexAttrib3hvNV = (glVertexAttrib3hvNVPROC) extgl_GetProcAddress("glVertexAttrib3hvNV");
	glVertexAttrib4hNV = (glVertexAttrib4hNVPROC) extgl_GetProcAddress("glVertexAttrib4hNV");
	glVertexAttrib4hvNV = (glVertexAttrib4hvNVPROC) extgl_GetProcAddress("glVertexAttrib4hvNV");
	glVertexAttribs1hvNV = (glVertexAttribs1hvNVPROC) extgl_GetProcAddress("glVertexAttribs1hvNV");
	glVertexAttribs2hvNV = (glVertexAttribs2hvNVPROC) extgl_GetProcAddress("glVertexAttribs2hvNV");
	glVertexAttribs3hvNV = (glVertexAttribs3hvNVPROC) extgl_GetProcAddress("glVertexAttribs3hvNV");
	glVertexAttribs4hvNV = (glVertexAttribs4hvNVPROC) extgl_GetProcAddress("glVertexAttribs4hvNV");

	EXTGL_SANITY_CHECK(env, ext_set, GL_NV_half_float)
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glVertex2hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertex2hNV
	(JNIEnv * env, jclass clazz, jshort x, jshort y)
{
	CHECK_EXISTS(glVertex2hNV)
	glVertex2hNV(x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glVertex3hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertex3hNV
	(JNIEnv * env, jclass clazz, jshort x, jshort y, jshort z)
{
	CHECK_EXISTS(glVertex3hNV)
	glVertex3hNV(x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glVertex4hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertex4hNV
	(JNIEnv * env, jclass clazz, jshort x, jshort y, jshort z, jshort w)
{
	CHECK_EXISTS(glVertex4hNV)
	glVertex4hNV(x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glNormal3hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glNormal3hNV
	(JNIEnv * env, jclass clazz, jshort nx, jshort ny, jshort nz)
{
	CHECK_EXISTS(glNormal3hNV)
	glNormal3hNV(nx, ny, nz);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glColor3hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glColor3hNV
	(JNIEnv * env, jclass clazz, jshort red, jshort green, jshort blue)
{
	CHECK_EXISTS(glColor3hNV)
	glColor3hNV(red, green, blue);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glColor4hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glColor4hNV
	(JNIEnv * env, jclass clazz, jshort red, jshort green, jshort blue, jshort alpha)
{
	CHECK_EXISTS(glColor4hNV)
	glColor4hNV(red, green, blue, alpha);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glTexCoord1hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord1hNV
	(JNIEnv * env, jclass clazz, jshort s)
{
	CHECK_EXISTS(glTexCoord1hNV)
	glTexCoord1hNV(s);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glTexCoord2hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord2hNV
	(JNIEnv * env, jclass clazz, jshort s, jshort t)
{
	CHECK_EXISTS(glTexCoord2hNV)
	glTexCoord2hNV(s, t);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glTexCoord3hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord3hNV
	(JNIEnv * env, jclass clazz, jshort s, jshort t, jshort r)
{
	CHECK_EXISTS(glTexCoord3hNV)
	glTexCoord3hNV(s, t, r);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glTexCoord4hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord4hNV
	(JNIEnv * env, jclass clazz, jshort s, jshort t, jshort r, jshort q)
{
	CHECK_EXISTS(glTexCoord4hNV)
	glTexCoord4hNV(s, t, r, q);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glMultiTexCoord1hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord1hNV
	(JNIEnv * env, jclass clazz, jint target, jshort s)
{
	CHECK_EXISTS(glMultiTexCoord1hNV)
	glMultiTexCoord1hNV(target, s);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glMultiTexCoord2hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord2hNV
	(JNIEnv * env, jclass clazz, jint target, jshort s, jshort t)
{
	CHECK_EXISTS(glMultiTexCoord2hNV)
	glMultiTexCoord2hNV(target, s, t);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glMultiTexCoord3hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord3hNV
	(JNIEnv * env, jclass clazz, jint target, jshort s, jshort t, jshort r)
{
	CHECK_EXISTS(glMultiTexCoord3hNV)
	glMultiTexCoord3hNV(target, s, t, r);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glMultiTexCoord4hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord4hNV
	(JNIEnv * env, jclass clazz, jint target, jshort s, jshort t, jshort r, jshort q)
{
	CHECK_EXISTS(glMultiTexCoord4hNV)
	glMultiTexCoord4hNV(target, s, t, r, q);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glFogCoordhNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glFogCoordhNV
	(JNIEnv * env, jclass clazz, jshort fog)
{
	CHECK_EXISTS(glFogCoordhNV)
	glFogCoordhNV(fog);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glSecondaryColor3hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glSecondaryColor3hNV
	(JNIEnv * env, jclass clazz, jshort red, jshort green, jshort blue)
{
	CHECK_EXISTS(glSecondaryColor3hNV)
	glSecondaryColor3hNV(red, green, blue);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glVertexWeighthNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertexWeighthNV
	(JNIEnv * env, jclass clazz, jshort weight)
{
	CHECK_EXISTS(glVertexWeighthNV)
	glVertexWeighthNV(weight);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glVertexAttrib1hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib1hNV
	(JNIEnv * env, jclass clazz, jint index, jshort x)
{
	CHECK_EXISTS(glVertexAttrib1hNV)
	glVertexAttrib1hNV(index, x);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glVertexAttrib2hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib2hNV
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y)
{
	CHECK_EXISTS(glVertexAttrib2hNV)
	glVertexAttrib2hNV(index, x, y);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glVertexAttrib3hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib3hNV
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y, jshort z)
{
	CHECK_EXISTS(glVertexAttrib3hNV)
	glVertexAttrib3hNV(index, x, y, z);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	glVertexAttrib4hNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib4hNV
	(JNIEnv * env, jclass clazz, jint index, jshort x, jshort y, jshort z, jshort w)
{
	CHECK_EXISTS(glVertexAttrib4hNV)
	glVertexAttrib4hNV(index, x, y, z, w);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	nglVertexAttribs1hvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs1hvNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject attribs, jint attribsOffset)
{
	CHECK_EXISTS(glVertexAttribs1hvNV)
	GLushort *attribs_ptr = (GLushort *)env->GetDirectBufferAddress(attribs) + attribsOffset;
	glVertexAttribs1hvNV(index, n, attribs_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	nglVertexAttribs2hvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs2hvNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject attribs, jint attribsOffset)
{
	CHECK_EXISTS(glVertexAttribs2hvNV)
	GLushort *attribs_ptr = (GLushort *)env->GetDirectBufferAddress(attribs) + attribsOffset;
	glVertexAttribs2hvNV(index, n, attribs_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	nglVertexAttribs3hvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs3hvNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject attribs, jint attribsOffset)
{
	CHECK_EXISTS(glVertexAttribs3hvNV)
	GLushort *attribs_ptr = (GLushort *)env->GetDirectBufferAddress(attribs) + attribsOffset;
	glVertexAttribs3hvNV(index, n, attribs_ptr);
	CHECK_GL_ERROR
}

/*
 * Class:	org.lwjgl.opengl.NVHalfFloat
 * Method:	nglVertexAttribs4hvNV
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs4hvNV
	(JNIEnv * env, jclass clazz, jint index, jint n, jobject attribs, jint attribsOffset)
{
	CHECK_EXISTS(glVertexAttribs4hvNV)
	GLushort *attribs_ptr = (GLushort *)env->GetDirectBufferAddress(attribs) + attribsOffset;
	glVertexAttribs4hvNV(index, n, attribs_ptr);
	CHECK_GL_ERROR
}
