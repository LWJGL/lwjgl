/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
 
/**
 * $Id$
 *
 * GLU library.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include "org_lwjgl_opengl_GLU.h"
#include "checkGLerror.h"
#include <GL/glu.h>

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    getString
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_GLU_getString(JNIEnv * env, jobject obj, jint p0)
{
	const char * msg = (const char *) gluGetString((GLint) p0);
	jstring ret = env->NewStringUTF(msg);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    errorString
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_GLU_errorString(JNIEnv * env, jobject obj, jint p0)
{
	const GLubyte * msg = gluErrorString((GLint) p0);
	jstring ret = env->NewStringUTF((const char *) msg);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    ortho2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_ortho2D(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	gluOrtho2D((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    perspective
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_perspective(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	gluPerspective((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    pickMatrix
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_pickMatrix(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jint p4)
{
	gluPickMatrix((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLint *) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    lookAt
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_lookAt(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5, jdouble p6, jdouble p7, jdouble p8)
{
	gluLookAt((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5, (GLdouble) p6, (GLdouble) p7, (GLdouble) p8);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    project
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GLU_project(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8)
{
	jint ret = (jint) gluProject((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (const GLdouble *) p3, (const GLdouble *) p4, (const GLint *) p5, (GLdouble *) p6, (GLdouble *) p7, (GLdouble *) p8);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    unProject
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GLU_unProject(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8)
{
	jint ret = (jint) gluUnProject((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (const GLdouble *) p3, (const GLdouble *) p4, (const GLint *) p5, (GLdouble *) p6, (GLdouble *) p7, (GLdouble *) p8);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    scaleImage
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GLU_scaleImage(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8)
{
	jint ret = (jint) gluScaleImage((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (const void *) p4, (GLint) p5, (GLint) p6, (GLint) p7, (void *) p8);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    build1DMipmaps
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GLU_build1DMipmaps(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	jint ret = (jint) gluBuild1DMipmaps((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (const void *) p5);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    build2DMipmaps
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GLU_build2DMipmaps(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	jint ret = (jint) gluBuild2DMipmaps((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (const void *) p6);
	CHECK_GL_ERROR
	return ret;
}

