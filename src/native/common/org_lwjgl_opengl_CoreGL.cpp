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
 * Core OpenGL functions.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#ifdef _WIN32
#include <windows.h>
#endif

#include "org_lwjgl_opengl_CoreGL.h"
#include "checkGLerror.h"
#include "extgl.h"

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    accum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_accum(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glAccum((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    alphaFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_alphaFunc(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glAlphaFunc((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    clearColor
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_clearColor(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glClearColor((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    clearAccum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_clearAccum(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glClearAccum((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    clear
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_clear(JNIEnv * env, jobject obj, jint p0)
{
	glClear((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    callLists
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_callLists(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glCallLists((GLint) p0, (GLint) p1, (const void *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    callList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_callList(JNIEnv * env, jobject obj, jint p0)
{
	glCallList((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    blendFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_blendFunc(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glBlendFunc((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    blendColorEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_blendColorEXT(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glBlendColor((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    bitmap
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_bitmap(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2, jfloat p3, jfloat p4, jfloat p5, jint p6)
{
	glBitmap((GLint) p0, (GLint) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4, (GLfloat) p5, (const unsigned char *) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    bindTexture
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_bindTexture(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glBindTexture((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    begin
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_begin(JNIEnv * env, jobject obj, jint p0)
{
	glBegin((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    end
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_end(JNIEnv * env, jobject obj)
{
	glEnd();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    arrayElement
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_arrayElement(JNIEnv * env, jobject obj, jint p0)
{
	glArrayElement((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    areTexturesResident
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL_areTexturesResident(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	jboolean ret = (jboolean) glAreTexturesResident((GLint) p0, (const GLuint *) p1, (GLboolean *) p2);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    clearDepth
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_clearDepth(JNIEnv * env, jobject obj, jdouble p0)
{
	glClearDepth((GLdouble) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    deleteLists
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_deleteLists(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glDeleteLists((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    deleteTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_deleteTextures(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glDeleteTextures((GLint) p0, (const GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    cullFace
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_cullFace(JNIEnv * env, jobject obj, jint p0)
{
	glCullFace((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    copyTexSubImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_copyTexSubImage2D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glCopyTexSubImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    copyTexSubImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_copyTexSubImage1D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	glCopyTexSubImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    copyTexImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_copyTexImage2D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glCopyTexImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    copyTexImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_copyTexImage1D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glCopyTexImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    copyPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_copyPixels(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glCopyPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    colorPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_colorPointer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glColorPointer((GLint) p0, (GLint) p1, (GLint) p2, (const void *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    colorMaterial
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_colorMaterial(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glColorMaterial((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    colorMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_colorMask(JNIEnv * env, jobject obj, jboolean p0, jboolean p1, jboolean p2, jboolean p3)
{
	glColorMask((GLboolean) p0, (GLboolean) p1, (GLboolean) p2, (GLboolean) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3b(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2)
{
	glColor3b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glColor3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glColor3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glColor3i((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glColor3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3ub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3ub(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2)
{
	glColor3ub((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3ui
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3ui(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glColor3ui((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3us
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3us(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glColor3us((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color4b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color4b(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2, jbyte p3)
{
	glColor4b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color4d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glColor4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color4f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glColor4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color4i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glColor4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color4s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glColor4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color4ub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color4ub(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2, jbyte p3)
{
	glColor4ub((GLbyte) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color4ui
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color4ui(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glColor4ui((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color4us
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color4us(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glColor4us((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3bv
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3bv
  (JNIEnv *env, jobject obj, jint v)
{
	glColor3bv((signed char *)v);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3dv
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3dv
  (JNIEnv *env, jobject obj, jint v)
{
	glColor3dv((double *)v);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3fv
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3fv
  (JNIEnv *env, jobject obj, jint v)
{
	glColor3fv((float *)v);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3iv
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3iv
  (JNIEnv *env, jobject obj, jint v)
{
	glColor3iv((int *)v);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3sv
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3sv
  (JNIEnv *env, jobject obj, jint v)
{
	glColor3sv((short *)v);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3ubv
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3ubv
  (JNIEnv *env, jobject obj, jint v)
{
	glColor3ubv((unsigned char *)v);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3uiv
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3uiv
  (JNIEnv *env, jobject obj, jint v)
{
	glColor3uiv((unsigned int *)v);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color3usv
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color3usv
  (JNIEnv *env, jobject obj, jint v)
{
	glColor3usv((unsigned short *)v);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color4bv
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color4bv
  (JNIEnv *env, jobject obj, jint v)
{
	glColor4bv((signed char *)v);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color4dv
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color4dv
  (JNIEnv *env, jobject obj, jint v)
{
	glColor4dv((double *)v);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color4fv
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color4fv
  (JNIEnv *env, jobject obj, jint v)
{
	glColor4fv((float *)v);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color4iv
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color4iv
  (JNIEnv *env, jobject obj, jint v)
{
	glColor4iv((int *)v);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color4sv
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color4sv
  (JNIEnv *env, jobject obj, jint v)
{
	glColor4sv((short *)v);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color4ubv
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color4ubv
  (JNIEnv *env, jobject obj, jint v)
{
	glColor4ubv((unsigned char *)v);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    color4uiv
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_color4uiv
  (JNIEnv *env, jobject obj, jint v)
{
	glColor4uiv((unsigned int *)v);
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    clipPlane
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_clipPlane(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glClipPlane((GLint) p0, (const GLdouble *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    clearStencil
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_clearStencil(JNIEnv * env, jobject obj, jint p0)
{
	glClearStencil((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    clearIndex
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_clearIndex(JNIEnv * env, jobject obj, jfloat p0)
{
	glClearIndex((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    evalPoint1
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_evalPoint1(JNIEnv * env, jobject obj, jint p0)
{
	glEvalPoint1((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    evalPoint2
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_evalPoint2(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glEvalPoint2((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    evalMesh1
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_evalMesh1(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glEvalMesh1((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    evalMesh2
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_evalMesh2(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glEvalMesh2((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    evalCoord1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_evalCoord1d(JNIEnv * env, jobject obj, jdouble p0)
{
	glEvalCoord1d((GLdouble) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    evalCoord1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_evalCoord1f(JNIEnv * env, jobject obj, jfloat p0)
{
	glEvalCoord1f((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    evalCoord2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_evalCoord2d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glEvalCoord2d((GLdouble) p0, (GLdouble) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    evalCoord2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_evalCoord2f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glEvalCoord2f((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    evalCoord1dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_evalCoord1dv(JNIEnv * env, jobject obj, jint p0)
{
	glEvalCoord1dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    evalCoord1fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_evalCoord1fv(JNIEnv * env, jobject obj, jint p0)
{
	glEvalCoord1fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    evalCoord2dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_evalCoord2dv(JNIEnv * env, jobject obj, jint p0)
{
	glEvalCoord2dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    evalCoord2fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_evalCoord2fv(JNIEnv * env, jobject obj, jint p0)
{
	glEvalCoord2fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    enableClientState
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_enableClientState(JNIEnv * env, jobject obj, jint p0)
{
	glEnableClientState((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    disableClientState
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_disableClientState(JNIEnv * env, jobject obj, jint p0)
{
	glDisableClientState((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    enable
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_enable(JNIEnv * env, jobject obj, jint p0)
{
	glEnable((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    disable
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_disable(JNIEnv * env, jobject obj, jint p0)
{
	glDisable((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    edgeFlagPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_edgeFlagPointer(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glEdgeFlagPointer((GLint) p0, (const void *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    edgeFlag
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_edgeFlag(JNIEnv * env, jobject obj, jboolean p0)
{
	glEdgeFlag((GLboolean) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    edgeFlagv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_edgeFlagv(JNIEnv * env, jobject obj, jint p0)
{
	glEdgeFlagv((const unsigned char *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    drawPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_drawPixels(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glDrawPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (const void *) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    drawElements
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_drawElements(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glDrawElements((GLint) p0, (GLint) p1, (GLint) p2, (const void *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    drawBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_drawBuffer(JNIEnv * env, jobject obj, jint p0)
{
	glDrawBuffer((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    drawArrays
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_drawArrays(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glDrawArrays((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    depthRange
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_depthRange(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glDepthRange((GLdouble) p0, (GLdouble) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    depthMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_depthMask(JNIEnv * env, jobject obj, jboolean p0)
{
	glDepthMask((GLboolean) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    depthFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_depthFunc(JNIEnv * env, jobject obj, jint p0)
{
	glDepthFunc((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    feedbackBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_feedbackBuffer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glFeedbackBuffer((GLint) p0, (GLint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getPixelMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getPixelMapfv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetPixelMapfv((GLint) p0, (GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getPixelMapuiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getPixelMapuiv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetPixelMapuiv((GLint) p0, (GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getPixelMapusv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getPixelMapusv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetPixelMapusv((GLint) p0, (GLushort *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getMaterialfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getMaterialfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMaterialfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getMaterialiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getMaterialiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMaterialiv((GLint) p0, (GLint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getMapdv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getMapdv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMapdv((GLint) p0, (GLint) p1, (GLdouble *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getMapfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMapfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getMapiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getMapiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMapiv((GLint) p0, (GLint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getLightfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getLightfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetLightfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getLightiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getLightiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetLightiv((GLint) p0, (GLint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getError
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_CoreGL_getError(JNIEnv * env, jobject obj)
{
	jint ret = (jint) glGetError();
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getClipPlane
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getClipPlane(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetClipPlane((GLint) p0, (GLdouble *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getBooleanv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getBooleanv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetBooleanv((GLint) p0, (GLubyte *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getDoublev
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getDoublev(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetDoublev((GLint) p0, (GLdouble *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getFloatv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getFloatv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetFloatv((GLint) p0, (GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getIntegerv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getIntegerv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetIntegerv((GLint) p0, (GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    genTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_genTextures(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGenTextures((GLint) p0, (GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    genLists
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_CoreGL_genLists(JNIEnv * env, jobject obj, jint p0)
{
	jint ret = (jint) glGenLists((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    frustum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_frustum(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glFrustum((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    frontFace
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_frontFace(JNIEnv * env, jobject obj, jint p0)
{
	glFrontFace((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    fogf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_fogf(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glFogf((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    fogi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_fogi(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glFogi((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    fogfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_fogfv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glFogfv((GLint) p0, (const GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    fogiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_fogiv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glFogiv((GLint) p0, (const GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    flush
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_flush(JNIEnv * env, jobject obj)
{
	glFlush();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    finish
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_finish(JNIEnv * env, jobject obj)
{
	glFinish();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getPointerv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getPointerv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetPointerv((GLint) p0, (void **) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    isEnabled
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL_isEnabled(JNIEnv * env, jobject obj, jint p0)
{
	jboolean ret = (jboolean) glIsEnabled((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    interleavedArrays
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_interleavedArrays(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glInterleavedArrays((GLint) p0, (GLint) p1, (const void *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    initNames
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_initNames(JNIEnv * env, jobject obj)
{
	glInitNames();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    indexPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_indexPointer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glIndexPointer((GLint) p0, (GLint) p1, (const void *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    indexMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_indexMask(JNIEnv * env, jobject obj, jint p0)
{
	glIndexMask((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    indexd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_indexd(JNIEnv * env, jobject obj, jdouble p0)
{
	glIndexd((GLdouble) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    indexf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_indexf(JNIEnv * env, jobject obj, jfloat p0)
{
	glIndexf((GLfloat) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    indexi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_indexi(JNIEnv * env, jobject obj, jint p0)
{
	glIndexi((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    indexs
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_indexs(JNIEnv * env, jobject obj, jshort p0)
{
	glIndexs((GLshort) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    indexub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_indexub(JNIEnv * env, jobject obj, jbyte p0)
{
	glIndexub((GLbyte) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    indexdv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_indexdv(JNIEnv * env, jobject obj, jint p0)
{
	glIndexdv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    indexfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_indexfv(JNIEnv * env, jobject obj, jint p0)
{
	glIndexfv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    indexiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_indexiv(JNIEnv * env, jobject obj, jint p0)
{
	glIndexiv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    indexsv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_indexsv(JNIEnv * env, jobject obj, jint p0)
{
	glIndexsv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    indexubv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_indexubv(JNIEnv * env, jobject obj, jint p0)
{
	glIndexubv((const unsigned char *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    hint
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_hint(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glHint((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getTexParameterfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getTexParameterfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexParameterfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getTexParameteriv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getTexParameteriv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexParameteriv((GLint) p0, (GLint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getTexLevelParameterfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getTexLevelParameterfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glGetTexLevelParameterfv((GLint) p0, (GLint) p1, (GLint) p2, (GLfloat *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getTexLevelParameteriv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getTexLevelParameteriv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glGetTexLevelParameteriv((GLint) p0, (GLint) p1, (GLint) p2, (GLint *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getTexImage
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getTexImage(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glGetTexImage((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (void *) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getTexGendv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getTexGendv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexGendv((GLint) p0, (GLint) p1, (GLdouble *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getTexGenfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getTexGenfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexGenfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getTexGeniv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getTexGeniv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexGeniv((GLint) p0, (GLint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getTexEnvfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getTexEnvfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexEnvfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getTexEnviv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getTexEnviv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexEnviv((GLint) p0, (GLint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getString
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_CoreGL_getString(JNIEnv * env, jobject obj, jint p0)
{
	const GLubyte * string = glGetString((GLenum) p0);
	return env->NewStringUTF((const char *) string);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getPolygonStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getPolygonStipple(JNIEnv * env, jobject obj, jint p0)
{
	glGetPolygonStipple((GLubyte *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    isList
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL_isList(JNIEnv * env, jobject obj, jint p0)
{
	jboolean ret = (jboolean) glIsList((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    materialf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_materialf(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2)
{
	glMaterialf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    materiali
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_materiali(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glMateriali((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    materialfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_materialfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glMaterialfv((GLint) p0, (GLint) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    materialiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_materialiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glMaterialiv((GLint) p0, (GLint) p1, (const GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    mapGrid1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_mapGrid1d(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2)
{
	glMapGrid1d((GLint) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    mapGrid1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_mapGrid1f(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2)
{
	glMapGrid1f((GLint) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    mapGrid2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_mapGrid2d(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jint p3, jdouble p4, jdouble p5)
{
	glMapGrid2d((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLint) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    mapGrid2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_mapGrid2f(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jint p3, jfloat p4, jfloat p5)
{
	glMapGrid2f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLfloat) p4, (GLfloat) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    map2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_map2d(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jint p3, jint p4, jdouble p5, jdouble p6, jint p7, jint p8, jint p9)
{
	glMap2d((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLint) p3, (GLint) p4, (GLdouble) p5, (GLdouble) p6, (GLint) p7, (GLint) p8, (const GLdouble *) p9);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    map2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_map2f(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jint p3, jint p4, jfloat p5, jfloat p6, jint p7, jint p8, jint p9)
{
	glMap2f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLint) p4, (GLfloat) p5, (GLfloat) p6, (GLint) p7, (GLint) p8, (const GLfloat *) p9);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    map1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_map1d(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jint p3, jint p4, jint p5)
{
	glMap1d((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLint) p3, (GLint) p4, (const GLdouble *) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    map1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_map1f(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jint p3, jint p4, jint p5)
{
	glMap1f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLint) p4, (const GLfloat *) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    logicOp
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_logicOp(JNIEnv * env, jobject obj, jint p0)
{
	glLogicOp((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    loadName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_loadName(JNIEnv * env, jobject obj, jint p0)
{
	glLoadName((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    loadMatrixd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_loadMatrixd(JNIEnv * env, jobject obj, jint p0)
{
	glLoadMatrixd((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    loadMatrixf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_loadMatrixf(JNIEnv * env, jobject obj, jint p0)
{
	glLoadMatrixf((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    loadIdentity
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_loadIdentity(JNIEnv * env, jobject obj)
{
	glLoadIdentity();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    listBase
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_listBase(JNIEnv * env, jobject obj, jint p0)
{
	glListBase((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    lineWidth
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_lineWidth(JNIEnv * env, jobject obj, jfloat p0)
{
	glLineWidth((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    lineStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_lineStipple(JNIEnv * env, jobject obj, jint p0, jshort p1)
{
	glLineStipple((GLint) p0, (GLshort) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    lightModelf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_lightModelf(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glLightModelf((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    lightModeli
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_lightModeli(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glLightModeli((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    lightModelfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_lightModelfv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glLightModelfv((GLint) p0, (const GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    lightModeliv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_lightModeliv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glLightModeliv((GLint) p0, (const GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    lightf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_lightf(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2)
{
	glLightf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    lighti
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_lighti(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glLighti((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    lightfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_lightfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glLightfv((GLint) p0, (GLint) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    lightiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_lightiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glLightiv((GLint) p0, (GLint) p1, (const GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    isTexture
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL_isTexture(JNIEnv * env, jobject obj, jint p0)
{
	jboolean ret = (jboolean) glIsTexture((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    matrixMode
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_matrixMode(JNIEnv * env, jobject obj, jint p0)
{
	glMatrixMode((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    polygonStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_polygonStipple(JNIEnv * env, jobject obj, jint p0)
{
	glPolygonStipple((const unsigned char *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    polygonOffset
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_polygonOffset(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glPolygonOffset((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    polygonMode
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_polygonMode(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glPolygonMode((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    pointSize
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_pointSize(JNIEnv * env, jobject obj, jfloat p0)
{
	glPointSize((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    pixelZoom
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_pixelZoom(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glPixelZoom((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    pixelTransferf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_pixelTransferf(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glPixelTransferf((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    pixelTransferi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_pixelTransferi(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glPixelTransferi((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    pixelStoref
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_pixelStoref(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glPixelStoref((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    pixelStorei
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_pixelStorei(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glPixelStorei((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    pixelMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_pixelMapfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glPixelMapfv((GLint) p0, (GLint) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    pixelMapuiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_pixelMapuiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glPixelMapuiv((GLint) p0, (GLint) p1, (const GLuint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    pixelMapusv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_pixelMapusv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glPixelMapusv((GLint) p0, (GLint) p1, (const GLushort *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    passThrough
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_passThrough(JNIEnv * env, jobject obj, jfloat p0)
{
	glPassThrough((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    ortho
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_ortho(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glOrtho((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    normalPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_normalPointer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glNormalPointer((GLint) p0, (GLint) p1, (const void *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    normal3b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_normal3b(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2)
{
	glNormal3b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    normal3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_normal3d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glNormal3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    normal3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_normal3f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glNormal3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    normal3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_normal3i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glNormal3i((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    normal3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_normal3s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glNormal3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    normal3bv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_normal3bv(JNIEnv * env, jobject obj, jint p0)
{
	glNormal3bv((const GLbyte *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    normal3dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_normal3dv(JNIEnv * env, jobject obj, jint p0)
{
	glNormal3dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    normal3fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_normal3fv(JNIEnv * env, jobject obj, jint p0)
{
	glNormal3fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    normal3iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_normal3iv(JNIEnv * env, jobject obj, jint p0)
{
	glNormal3iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    normal3sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_normal3sv(JNIEnv * env, jobject obj, jint p0)
{
	glNormal3sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    newList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_newList(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glNewList((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    endList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_endList(JNIEnv * env, jobject obj)
{
	glEndList();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multMatrixd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multMatrixd(JNIEnv * env, jobject obj, jint p0)
{
	glMultMatrixd((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multMatrixf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multMatrixf(JNIEnv * env, jobject obj, jint p0)
{
	glMultMatrixf((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    prioritizeTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_prioritizeTextures(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glPrioritizeTextures((GLint) p0, (const GLuint *) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    shadeModel
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_shadeModel(JNIEnv * env, jobject obj, jint p0)
{
	glShadeModel((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    selectBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_selectBuffer(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glSelectBuffer((GLint) p0, (GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    scissor
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_scissor(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glScissor((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    scaled
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_scaled(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glScaled((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    scalef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_scalef(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glScalef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rotated
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rotated(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glRotated((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rotatef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rotatef(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRotatef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    renderMode
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_CoreGL_renderMode(JNIEnv * env, jobject obj, jint p0)
{
	jint ret = (jint) glRenderMode((GLint) p0);
	CHECK_GL_ERROR
	return ret;
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rectd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rectd(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glRectd((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rectf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rectf(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRectf((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    recti
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_recti(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glRecti((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rects
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rects(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glRects((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rectdv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rectdv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRectdv((const GLdouble *) p0, (const GLdouble *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rectfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rectfv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRectfv((const GLfloat *) p0, (const GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rectiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rectiv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRectiv((const GLint *) p0, (const GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rectsv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rectsv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRectsv((const GLshort *) p0, (const GLshort *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    readPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_readPixels(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glReadPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (void *) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    readBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_readBuffer(JNIEnv * env, jobject obj, jint p0)
{
	glReadBuffer((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos2d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glRasterPos2d((GLdouble) p0, (GLdouble) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos2f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glRasterPos2f((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos2i(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRasterPos2i((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos2s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos2s(JNIEnv * env, jobject obj, jshort p0, jshort p1)
{
	glRasterPos2s((GLshort) p0, (GLshort) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos3d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glRasterPos3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos3f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glRasterPos3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos3i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glRasterPos3i((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos3s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glRasterPos3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos4d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glRasterPos4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos4f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRasterPos4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos4i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glRasterPos4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos4s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glRasterPos4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos2dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos2dv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos2dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos2fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos2fv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos2fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos2iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos2iv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos2iv((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos2sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos2sv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos2sv((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos3dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos3dv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos3dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos3fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos3fv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos3fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos3iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos3iv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos3iv((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos3sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos3sv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos3sv((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos4dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos4dv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos4dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos4fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos4fv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos4fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos4iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos4iv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos4iv((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    rasterPos4sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_rasterPos4sv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos4sv((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    pushName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_pushName(JNIEnv * env, jobject obj, jint p0)
{
	glPushName((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    popName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_popName(JNIEnv * env, jobject obj)
{
	glPopName();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    pushMatrix
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_pushMatrix(JNIEnv * env, jobject obj)
{
	glPushMatrix();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    popMatrix
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_popMatrix(JNIEnv * env, jobject obj)
{
	glPopMatrix();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    pushClientAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_pushClientAttrib(JNIEnv * env, jobject obj, jint p0)
{
	glPushClientAttrib((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    popClientAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_popClientAttrib(JNIEnv * env, jobject obj)
{
	glPopClientAttrib();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    pushAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_pushAttrib(JNIEnv * env, jobject obj, jint p0)
{
	glPushAttrib((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    popAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_popAttrib(JNIEnv * env, jobject obj)
{
	glPopAttrib();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    stencilFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_stencilFunc(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glStencilFunc((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertexPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertexPointer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glVertexPointer((GLint) p0, (GLint) p1, (GLint) p2, (const void *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex2d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glVertex2d((GLdouble) p0, (GLdouble) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex2f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glVertex2f((GLfloat) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex2i(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertex2i((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex2s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex2s(JNIEnv * env, jobject obj, jshort p0, jshort p1)
{
	glVertex2s((GLshort) p0, (GLshort) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex3d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glVertex3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex3f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glVertex3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex3i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertex3i((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex3s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glVertex3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex4d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glVertex4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex4f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glVertex4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex4i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glVertex4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex4s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glVertex4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex2dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex2dv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex2dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex2fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex2fv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex2fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex2iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex2iv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex2iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex2sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex2sv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex2sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex3dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex3dv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex3dv((const GLdouble *) p0);

}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex3fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex3fv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex3fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex3iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex3iv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex3iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex3sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex3sv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex3sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex4dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex4dv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex4dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex4fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex4fv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex4fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex4iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex4iv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex4iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    vertex4sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_vertex4sv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex4sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    translated
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_translated(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glTranslated((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    translatef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_translatef(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glTranslatef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texSubImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texSubImage2D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8)
{
	glTexSubImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (const void *) p8);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texSubImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texSubImage1D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glTexSubImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (const void *) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texParameterf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texParameterf(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2)
{
	glTexParameterf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texParameteri
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texParameteri(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexParameteri((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texImage2D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8)
{
	glTexImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (const void *) p8);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texImage1D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glTexImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (const void *) p7);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texGend
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texGend(JNIEnv * env, jobject obj, jint p0, jint p1, jdouble p2)
{
	glTexGend((GLint) p0, (GLint) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texGenf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texGenf(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2)
{
	glTexGenf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texGeni
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texGeni(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexGeni((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texGendv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texGendv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexGendv((GLint) p0, (GLint) p1, (const GLdouble *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texGenfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texGenfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexGenfv((GLint) p0, (GLint) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texGeniv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texGeniv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexGeniv((GLint) p0, (GLint) p1, (const GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texEnvf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texEnvf(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2)
{
	glTexEnvf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texEnvi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texEnvi(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexEnvi((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texEnvfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texEnvfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexEnvfv((GLint) p0, (GLint) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texEnviv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texEnviv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexEnviv((GLint) p0, (GLint) p1, (const GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoordPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoordPointer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glTexCoordPointer((GLint) p0, (GLint) p1, (GLint) p2, (const void *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord1d(JNIEnv * env, jobject obj, jdouble p0)
{
	glTexCoord1d((GLdouble) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord1f(JNIEnv * env, jobject obj, jfloat p0)
{
	glTexCoord1f((GLfloat) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord1i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord1i(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord1i((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord1s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord1s(JNIEnv * env, jobject obj, jshort p0)
{
	glTexCoord1s((GLshort) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord2d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glTexCoord2d((GLdouble) p0, (GLdouble) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord2f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glTexCoord2f((GLfloat) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord2i(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glTexCoord2i((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord2s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord2s(JNIEnv * env, jobject obj, jshort p0, jshort p1)
{
	glTexCoord2s((GLshort) p0, (GLshort) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord3d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glTexCoord3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord3f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glTexCoord3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord3i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexCoord3i((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord3s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glTexCoord3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord4d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glTexCoord4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord4f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glTexCoord4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord4i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glTexCoord4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord4s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glTexCoord4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord1dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord1dv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord1dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord1fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord1fv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord1fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord1iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord1iv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord1iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord1sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord1sv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord1sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord2dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord2dv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord2dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord2fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord2fv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord2fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord2iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord2iv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord2iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord2sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord2sv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord2sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord3dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord3dv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord3dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord3fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord3fv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord3fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord3iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord3iv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord3iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord3sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord3sv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord3sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord4dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord4dv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord4dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord4fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord4fv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord4fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord4iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord4iv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord4iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texCoord4sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texCoord4sv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord4sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    stencilOp
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_stencilOp(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glStencilOp((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    stencilMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_stencilMask(JNIEnv * env, jobject obj, jint p0)
{
	glStencilMask((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    viewport
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_viewport(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glViewport((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    colorTable
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_colorTable
  (JNIEnv * env, jobject obj, jint target, jint internalFormat, jint width, jint format, jint type, jint data)
{
	glColorTable(target, internalFormat, width, format, type, (const void *) data);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    colorSubTable
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_colorSubTable
  (JNIEnv * env, jobject obj, jint target, jint start, jint count, jint format, jint type, jint data)
{
	glColorSubTable(target, start, count, format, type, (const void *) data);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getColorTable
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getColorTable
  (JNIEnv * env, jobject obj, jint target, jint format, jint type, jint data)
{
	glGetColorTable(target, format, type, (void *) data);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getColorTableParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getColorTableParameteriv
  (JNIEnv * env, jobject obj, jint target, jint pname, jint params)
{
	glGetColorTableParameteriv(target, pname, (int *)params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getColorTableParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getColorTableParameterfv
  (JNIEnv * env, jobject obj, jint target, jint pname, jint params)
{
	glGetColorTableParameterfv(target, pname, (float *)params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    colorTableParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_colorTableParameteriv
  (JNIEnv *env, jobject obj, jint target, jint pname, jint params)
{
	glColorTableParameteriv(target, pname, (int *)params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    colorTableParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_colorTableParameterfv
  (JNIEnv *env, jobject obj, jint target, jint pname, jint params)
{
	glColorTableParameterfv(target, pname, (float *)params);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    copyColorSubTable
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_copyColorSubTable
  (JNIEnv *env, jobject obj, jint target, jint start, jint x, jint y, jint width)
{
	glCopyColorSubTable(target, start, x, y, width);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    copyColorTable
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_copyColorTable
  (JNIEnv *env, jobject obj, jint target, jint internalformat, jint x, jint y, jint width)
{
	glCopyColorTable(target, internalformat, x, y, width);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    blendEquation
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_blendEquation
  (JNIEnv *env, jobject obj, jint mode)
{
	glBlendEquation(mode);
	CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    histogram
 * Signature: (IIIZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_histogram
  (JNIEnv *env, jobject obj, jint target, jint width, jint internalformat, jboolean sink)
{
	glHistogram(target, width, internalformat, sink);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    resetHistogram
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_resetHistogram
  (JNIEnv *env, jobject obj, jint target)
{
	glResetHistogram(target);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getHistogram
 * Signature: (IZIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getHistogram
  (JNIEnv *env, jobject obj, jint target, jboolean reset, jint format, jint type, jint values)
{
	glGetHistogram(target, reset, format, type, (void *)values);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getHistogramParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getHistogramParameterfv
  (JNIEnv *env, jobject obj, jint target, jint pname, jint params)
{
	glGetHistogramParameterfv(target, pname, (float *)params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getHistogramParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getHistogramParameteriv
  (JNIEnv *env, jobject obj, jint target, jint pname, jint params)
{
	glGetHistogramParameteriv(target, pname, (int *)params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    minmax
 * Signature: (IIZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_minmax
  (JNIEnv *env, jobject obj, jint target, jint internalformat, jboolean sink)
{
	glMinmax(target, internalformat, sink);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    resetMinmax
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_resetMinmax
  (JNIEnv *env, jobject obj, jint target)
{
	glResetMinmax(target);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getMinmax
 * Signature: (IZIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getMinmax
  (JNIEnv *env, jobject obj, jint target, jboolean reset, jint format, jint type, jint values)
{
	glGetMinmax(target, reset, format, type, (void *)values);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getMinmaxParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getMinmaxParameterfv
  (JNIEnv *env, jobject obj, jint target, jint pname, jint params)
{
	glGetMinmaxParameterfv(target, pname, (float *)params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getMinmaxParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getMinmaxParameteriv
  (JNIEnv *env, jobject obj, jint target, jint pname, jint params)
{
	glGetMinmaxParameteriv(target, pname, (int *)params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    convolutionFilter1D
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_convolutionFilter1D
  (JNIEnv *env, jobject obj, jint target, jint internalformat, jint width, jint format, jint type, jint image)
{
	glConvolutionFilter1D(target, internalformat, width, format, type, (void *)image);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    convolutionFilter2D
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_convolutionFilter2D
  (JNIEnv *env, jobject obj, jint target, jint internalformat, jint width, jint height, jint format, jint type, jint image)
{
	glConvolutionFilter2D(target, internalformat, width, height, format, type, (void *)image);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    convolutionParameterf
 * Signature: (IIF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_convolutionParameterf
  (JNIEnv *env, jobject obj, jint target, jint pname, jfloat params)
{
	glConvolutionParameterf(target, pname, params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    convolutionParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_convolutionParameterfv
  (JNIEnv *env, jobject obj, jint target, jint pname, jint params)
{
	glConvolutionParameterfv(target, pname, (float *)params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    convolutionParameteri
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_convolutionParameteri
  (JNIEnv *env, jobject obj, jint target, jint pname, jint params)
{
	glConvolutionParameteri(target, pname, params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    convolutionParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_convolutionParameteriv
  (JNIEnv *env, jobject obj, jint target, jint pname, jint params)
{
	glConvolutionParameteriv(target, pname, (int *)params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    copyConvolutionFilter1D
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_copyConvolutionFilter1D
  (JNIEnv *env, jobject obj, jint target, jint internalformat, jint x, jint y, jint width)
{
	glCopyConvolutionFilter1D(target, internalformat, x, y, width);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    copyConvolutionFilter2D
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_copyConvolutionFilter2D
  (JNIEnv *env, jobject obj, jint target, jint internalformat, jint x, jint y, jint width, jint height)
{
	glCopyConvolutionFilter2D(target, internalformat, x, y, width, height);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getConvolutionFilter
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getConvolutionFilter
  (JNIEnv *env, jobject obj, jint target, jint format, jint type, jint image)
{
	glGetConvolutionFilter(target, format, type, (void *)image);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getConvolutionParameterfv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getConvolutionParameterfv
  (JNIEnv *env, jobject obj, jint target, jint pname, jint params)
{
	glGetConvolutionParameterfv(target, pname, (float *)params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getConvolutionParameteriv
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getConvolutionParameteriv
  (JNIEnv *env, jobject obj, jint target, jint pname, jint params)
{
	glGetConvolutionParameteriv(target, pname, (int *)params);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    separableFilter2D
 * Signature: (IIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_separableFilter2D
  (JNIEnv *env, jobject obj, jint target, jint internalformat, jint width, jint height, jint format, jint type, jint row, jint column)
{
	glSeparableFilter2D(target, internalformat, width, height, format, type, (void *)row, (void *)column);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getSeparableFilter
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getSeparableFilter
  (JNIEnv *env, jobject obj, jint target, jint format, jint type, jint row, jint column, jint span)
{
	glGetSeparableFilter(target, format, type, (void *)row, (void *)column, (void *)span);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    drawRangeElements
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_drawRangeElements
  (JNIEnv *env, jobject obj, jint mode, jint start, jint end, jint count, jint type, jint indices)
{
	glDrawRangeElements(mode, start, end, count, type, (void *)indices);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texImage3D
 * Signature: (IIIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texImage3D
  (JNIEnv *env, jobject obj, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint format, jint type, jint pixels)
{
	glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, (void *)pixels);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    texSubImage3D
 * Signature: (IIIIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_texSubImage3D
  (JNIEnv *env, jobject obj, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint type, jint pixels)
{
	glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, (void *)pixels);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    copyTexSubImage3D
 * Signature: (IIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_copyTexSubImage3D
  (JNIEnv *env, jobject obj, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint x, jint y, jint width, jint height)
{
	glCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y, width, height);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    activeTexture
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_activeTexture
  (JNIEnv *env, jobject obj, jint texture)
{
	glActiveTexture(texture);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    clientActiveTexture
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_clientActiveTexture
  (JNIEnv *env, jobject obj, jint texture)
{
	glClientActiveTexture(texture);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    compressedTexImage1D
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_compressedTexImage1D
  (JNIEnv *env, jobject obj, jint target, jint level, jint internalformat, jint width, jint border, jint imagesize, jint data)
{
	glCompressedTexImage1D(target, level, internalformat, width, border, imagesize, (void *)data);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    compressedTexImage2D
 * Signature: (IIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_compressedTexImage2D
  (JNIEnv *env, jobject obj, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint imagesize, jint data)
{
	glCompressedTexImage2D(target, level, internalformat, width, height, border, imagesize, (void *)data);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    compressedTexImage3D
 * Signature: (IIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_compressedTexImage3D
  (JNIEnv *env, jobject obj, jint target, jint level, jint internalformat, jint width, jint height, jint depth, jint border, jint imagesize, jint data)
{
	glCompressedTexImage3D(target, level, internalformat, width, height, depth, border, imagesize, (void *)data);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    compressedTexSubImage1D
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_compressedTexSubImage1D
  (JNIEnv *env, jobject obj, jint target, jint level, jint xoffset, jint width, jint format, jint imagesize, jint data)
{
	glCompressedTexSubImage1D(target, level, xoffset, width, format, imagesize, (void *)data);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    compressedTexSubImage2D
 * Signature: (IIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_compressedTexSubImage2D
  (JNIEnv *env, jobject obj, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint imagesize, jint data)
{
	glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imagesize, (void *)data);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    compressedTexSubImage3D
 * Signature: (IIIIIIIIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_compressedTexSubImage3D
  (JNIEnv *env, jobject obj, jint target, jint level, jint xoffset, jint yoffset, jint zoffset, jint width, jint height, jint depth, jint format, jint imagesize, jint data)
{
	glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, imagesize, (void *)data);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    getCompressedTexImage
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_getCompressedTexImage
  (JNIEnv *env, jobject obj, jint target, jint lod, jint img)
{
	glGetCompressedTexImage(target, lod, (void *)img);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiDrawArrays
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiDrawArrays
  (JNIEnv *, jobject, jint mode, jint first, jint count, jint primcount) {
	glMultiDrawArrays(mode, (GLint *)first, (GLsizei *)count, primcount);
}
  
/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiDrawElements
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiDrawElements
  (JNIEnv *, jobject, jint mode, jint count, jint type, jint indices, jint primcount) {
	glMultiDrawElements(mode, (GLsizei *)count, type, (const void **)indices, primcount);
}
    
    
/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord1d
 * Signature: (ID)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord1d
  (JNIEnv *env, jobject obj, jint target, jdouble s)
{
	glMultiTexCoord1d(target, s);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord1dv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord1dv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord1dv(target, (double *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord1f
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord1f
  (JNIEnv *env, jobject obj, jint target, jfloat s)
{
	glMultiTexCoord1f(target, s);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord1fv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord1fv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord1fv(target, (float *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord1i
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord1i
  (JNIEnv *env, jobject obj, jint target, jint s)
{
	glMultiTexCoord1i(target, s);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord1iv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord1iv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord1iv(target, (int *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord1s
 * Signature: (IS)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord1s
  (JNIEnv *env, jobject obj, jint target, jshort s)
{
	glMultiTexCoord1s(target, s);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord1sv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord1sv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord1sv(target, (short *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord2d
 * Signature: (IDD)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord2d
  (JNIEnv *env, jobject obj, jint target, jdouble s, jdouble t)
{
	glMultiTexCoord2d(target, s, t);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord2dv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord2dv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord2dv(target, (double *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord2f
 * Signature: (IFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord2f
  (JNIEnv *env, jobject obj, jint target, jfloat s, jfloat t)
{
	glMultiTexCoord2f(target, s, t);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord2fv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord2fv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord2fv(target, (float *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord2i
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord2i
  (JNIEnv *env, jobject obj, jint target, jint s, jint t)
{
	glMultiTexCoord2i(target, s, t);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord2iv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord2iv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord2iv(target, (int *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord2s
 * Signature: (ISS)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord2s
  (JNIEnv *env, jobject obj, jint target, jshort s, jshort t)
{
	glMultiTexCoord2s(target, s, t);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord2sv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord2sv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord2sv(target, (short *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord3d
 * Signature: (IDDD)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord3d
  (JNIEnv *env, jobject obj, jint target, jdouble s, jdouble t, jdouble r)
{
	glMultiTexCoord3d(target, s, t, r);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord3dv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord3dv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord3dv(target, (double *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord3f
 * Signature: (IFFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord3f
  (JNIEnv *env, jobject obj, jint target, jfloat s, jfloat t, jfloat r)
{
	glMultiTexCoord3f(target, s, t, r);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord3fv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord3fv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord3fv(target, (float *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord3i
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord3i
  (JNIEnv *env, jobject obj, jint target, jint s, jint t, jint r)
{
	glMultiTexCoord3i(target, s, t, r);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord3iv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord3iv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord3iv(target, (int *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord3s
 * Signature: (ISSS)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord3s
  (JNIEnv *env, jobject obj, jint target, jshort s, jshort t, jshort r)
{
	glMultiTexCoord3s(target, s, t, r);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord3sv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord3sv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord3sv(target, (short *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord4d
 * Signature: (IDDDD)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord4d
  (JNIEnv *env, jobject obj, jint target, jdouble s, jdouble t, jdouble r, jdouble q)
{
	glMultiTexCoord4d(target, s, t, r, q);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord4dv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord4dv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord4dv(target, (double *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord4f
 * Signature: (IFFFF)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord4f
  (JNIEnv *env, jobject obj, jint target, jfloat s, jfloat t, jfloat r, jfloat q)
{
	glMultiTexCoord4f(target, s, t, r, q);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord4fv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord4fv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord4fv(target, (float *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord4i
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord4i
  (JNIEnv *env, jobject obj, jint target, jint s, jint t, jint r, jint q)
{
	glMultiTexCoord4i(target, s, t, r, q);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord4iv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord4iv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord4iv(target, (int *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord4s
 * Signature: (ISSSS)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord4s
  (JNIEnv *env, jobject obj, jint target, jshort s, jshort t, jshort r, jshort q)
{
	glMultiTexCoord4s(target, s, t, r, q);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multiTexCoord4sv
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multiTexCoord4sv
  (JNIEnv *env, jobject obj, jint target, jint v)
{
	glMultiTexCoord4sv(target, (short *)v);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    loadTransposeMatrixd
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_loadTransposeMatrixd
  (JNIEnv *env, jobject obj, jint m)
{
	glLoadTransposeMatrixd((double *)m);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    loadTransposeMatrixf
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_loadTransposeMatrixf
  (JNIEnv *env, jobject obj, jint m)
{
	glLoadTransposeMatrixf((float *)m);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multTransposeMatrixd
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multTransposeMatrixd
  (JNIEnv *env, jobject obj, jint m)
{
	glMultTransposeMatrixd((double *)m);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    multTransposeMatrixf
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_multTransposeMatrixf
  (JNIEnv *env, jobject obj, jint m)
{
	glMultTransposeMatrixf((float *)m);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    sampleCoverage
 * Signature: (FZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_sampleCoverage
  (JNIEnv *env, jobject obj, jfloat value, jboolean invert)
{
	glSampleCoverage(value, invert);
	CHECK_GL_ERROR
}

