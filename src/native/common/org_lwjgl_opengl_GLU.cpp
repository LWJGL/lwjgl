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
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#include "org_lwjgl_opengl_GLU.h"
#include "extgl.h"
#include "checkGLerror.h"

#include "callbacks/GLUQuadricCallbacks.h"

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    getString
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_GLU_gluGetString(JNIEnv * env, jclass clazz, jint p0)
{
	const char * msg = (const char *) gluGetString((GLint) p0);
	jstring ret = env->NewStringUTF(msg);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    errorString
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_GLU_gluErrorString(JNIEnv * env, jclass clazz, jint p0)
{
	const GLubyte * msg = gluErrorString((GLint) p0);
	jstring ret = env->NewStringUTF((const char *) msg);
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    ortho2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_gluOrtho2D(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	gluOrtho2D((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    perspective
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_gluPerspective(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	gluPerspective((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    pickMatrix
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_gluPickMatrix(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jobject buffer)
{
	GLint *address = (GLint *)env->GetDirectBufferAddress(buffer);
	gluPickMatrix((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, address);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    lookAt
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_gluLookAt(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5, jdouble p6, jdouble p7, jdouble p8)
{
	gluLookAt((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5, (GLdouble) p6, (GLdouble) p7, (GLdouble) p8);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    project
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GLU_gluProject(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jobject buffer, jobject buffer2, jobject buffer3, jobject win_buffer)
{
	const GLdouble *address = (const GLdouble *)env->GetDirectBufferAddress(buffer);
	const GLdouble *address2 = (const GLdouble *)env->GetDirectBufferAddress(buffer2);
	const GLint *address3 = (const GLint *)env->GetDirectBufferAddress(buffer3);
	GLdouble *win_address = (GLdouble *)env->GetDirectBufferAddress(win_buffer);
	jint ret = (jint) gluProject((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, address, address2, address3, win_address, win_address + 1, win_address + 2);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    unProject
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GLU_gluUnProject(JNIEnv * env, jclass clazz, jdouble p0, jdouble p1, jdouble p2, jobject buffer, jobject buffer2, jobject buffer3, jobject obj_buffer)
{
	const GLdouble *address = (const GLdouble *)env->GetDirectBufferAddress(buffer);
	const GLdouble *address2 = (const GLdouble *)env->GetDirectBufferAddress(buffer2);
	const GLint *address3 = (const GLint *)env->GetDirectBufferAddress(buffer3);
	GLdouble *obj_address = (GLdouble *)env->GetDirectBufferAddress(obj_buffer);
	jint ret = (jint) gluUnProject((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, address, address2, address3, obj_address, obj_address + 1, obj_address + 2);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    scaleImage
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GLU_gluScaleImage(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jobject buffer, jint p5, jint p6, jint p7, jobject buffer2)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	void *address2 = (void *)env->GetDirectBufferAddress(buffer2);
	jint ret = (jint) gluScaleImage((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, address, (GLint) p5, (GLint) p6, (GLint) p7, address2);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    build1DMipmaps
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GLU_gluBuild1DMipmaps(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	jint ret = (jint) gluBuild1DMipmaps((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, address);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    build2DMipmaps
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_GLU_gluBuild2DMipmaps(JNIEnv * env, jclass clazz, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jobject buffer)
{
	const void *address = (const void *)env->GetDirectBufferAddress(buffer);
	jint ret = (jint) gluBuild2DMipmaps((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, address);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    newQuadric
 */
JNIEXPORT jobject JNICALL Java_org_lwjgl_opengl_GLU_gluNewQuadric(JNIEnv * env, jclass clazz)
{
	GLUquadricObj *ret = gluNewQuadric();
	CHECK_GL_ERROR
	return env->NewDirectByteBuffer(ret, 0);
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    cylinder
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_gluCylinder(JNIEnv * env, jclass clazz, jobject quad, jdouble baseRadius, jdouble topRadius, jdouble height, jint slices, jint stacks)
{
	GLUquadricObj *address = (GLUquadricObj *)env->GetDirectBufferAddress(quad);
	gluCylinder(address, (GLdouble) baseRadius, (GLdouble) topRadius, (GLdouble) height, (GLint) slices, (GLint) stacks);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    deleteQuadric
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_gluDeleteQuadric(JNIEnv * env, jclass clazz, jobject quad)
{
	GLUquadricObj *address = (GLUquadricObj *)env->GetDirectBufferAddress(quad);
	gluDeleteQuadric(address);
	GLUQuadricCallbacks::clear();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    disk
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_gluDisk
  (JNIEnv * env, jclass clazz, jobject quad, jdouble innerRadius, jdouble outerRadius, jint slices, jint loops)
{
	GLUquadricObj *address = (GLUquadricObj *)env->GetDirectBufferAddress(quad);
	gluDisk(address, (GLdouble) innerRadius, (GLdouble) outerRadius, (GLint) slices, (GLint) loops);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    partialDisk
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_gluPartialDisk
  (JNIEnv * env, jclass clazz, jobject quad, jdouble innerRadius, jdouble outerRadius, 
  jint slices, jint loops, jdouble startAngle, jdouble sweepAngle)
{
	GLUquadricObj *address = (GLUquadricObj *)env->GetDirectBufferAddress(quad);
	gluPartialDisk(address, (GLdouble) innerRadius, (GLdouble) outerRadius,
	               (GLint) slices, (GLint) loops, (GLdouble) startAngle, (GLdouble) sweepAngle);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    quadricDrawStyle
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_gluQuadricDrawStyle
  (JNIEnv * env, jclass clazz, jobject quad, jint drawStyle)
{
	GLUquadricObj *address = (GLUquadricObj *)env->GetDirectBufferAddress(quad);
	gluQuadricDrawStyle(address, (GLenum) drawStyle);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    quadricNormals
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_gluQuadricNormals
  (JNIEnv * env, jclass clazz, jobject quad, jint normals)
{
	GLUquadricObj *address = (GLUquadricObj *)env->GetDirectBufferAddress(quad);
	gluQuadricNormals(address, (GLenum) normals);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    quadricOrientation
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_gluQuadricOrientation
  (JNIEnv * env, jclass clazz, jobject quad, jint orientation)
{
	GLUquadricObj *address = (GLUquadricObj *)env->GetDirectBufferAddress(quad);
	gluQuadricOrientation(address, (GLenum) orientation);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    quadricTexture
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_gluQuadricTexture
  (JNIEnv * env, jclass clazz, jobject quad, jboolean textureCoords)
{
	GLUquadricObj *address = (GLUquadricObj *)env->GetDirectBufferAddress(quad);
	gluQuadricTexture(address, (GLboolean) textureCoords);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    sphere
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_gluSphere
  (JNIEnv * env, jclass clazz, jobject quad, jdouble radius, jint slices, jint stacks)
{
	GLUquadricObj *address = (GLUquadricObj *)env->GetDirectBufferAddress(quad);
	gluSphere(address, (GLdouble) radius, (GLint) slices, (GLint) stacks);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    quadricCallback
 * Signature: (IILjava/lang/String;)V
 */
/*JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_gluQuadricCallback__IILjava_lang_String_2
  (JNIEnv * env, jclass clazz, jobject quad, jint type, jstring method)
{
	GLUquadricObj *address = (GLUquadricObj *)env->GetDirectBufferAddress(quad);
	GLUQuadricCallbacks::set(address, 
	                         new JavaMethod(env, obj, env->GetStringUTFChars(method, 0)),
	                         type);
	CHECK_GL_ERROR  
}
*/
/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    quadricCallback
 * Signature: (IILjava/lang/Object;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_gluQuadricCallback__IILjava_lang_Object_2Ljava_lang_String_2
  (JNIEnv * env, jclass clazz, jobject quad, jint type, jobject target, jstring method)
{
	GLUquadricObj *address = (GLUquadricObj *)env->GetDirectBufferAddress(quad);
	GLUQuadricCallbacks::set(address, 
	                         new JavaMethod(env, target, env->GetStringUTFChars(method, 0)),
	                         type);
	CHECK_GL_ERROR
}

