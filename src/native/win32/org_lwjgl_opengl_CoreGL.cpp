/*
 * org_lwjgl_opengl_CoreGL.cpp
 *
 * Win32 specific library
 *
 * (C) 2002 Shaven Puppy Ltd
 *
 * Created 10 August 2002 by foo
 *
 */

#include <windows.h>
#include "org_lwjgl_opengl_CoreGL.h"
#include "extgl.h"
#include "checkGLerror.h"

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glAccum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glAccum(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glAccum((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glAlphaFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glAlphaFunc(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glAlphaFunc((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glClearColor
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glClearColor(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glClearColor((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glClearAccum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glClearAccum(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glClearAccum((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glClear
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glClear(JNIEnv * env, jobject obj, jint p0)
{
	glClear((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCallLists
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCallLists(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glCallLists((GLint) p0, (GLint) p1, (const void *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCallList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCallList(JNIEnv * env, jobject obj, jint p0)
{
	glCallList((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glBlendFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glBlendFunc(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glBlendFunc((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glBlendColorEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glBlendColorEXT(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glBlendColor((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glBitmap
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glBitmap(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2, jfloat p3, jfloat p4, jfloat p5, jint p6)
{
	glBitmap((GLint) p0, (GLint) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4, (GLfloat) p5, (const unsigned char *) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glBindTexture
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glBindTexture(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glBindTexture((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glBegin
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glBegin(JNIEnv * env, jobject obj, jint p0)
{
	glBegin((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEnd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEnd(JNIEnv * env, jobject obj)
{
	glEnd();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glArrayElement
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glArrayElement(JNIEnv * env, jobject obj, jint p0)
{
	glArrayElement((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glAreTexturesResident
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL_glAreTexturesResident(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	return (jboolean) glAreTexturesResident((GLint) p0, (const GLuint *) p1, (GLboolean *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glClearDepth
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glClearDepth(JNIEnv * env, jobject obj, jdouble p0)
{
	glClearDepth((GLdouble) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDeleteLists
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDeleteLists(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glDeleteLists((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDeleteTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDeleteTextures(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glDeleteTextures((GLint) p0, (const GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCullFace
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCullFace(JNIEnv * env, jobject obj, jint p0)
{
	glCullFace((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCopyTexSubImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCopyTexSubImage2D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glCopyTexSubImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCopyTexSubImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCopyTexSubImage1D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	glCopyTexSubImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCopyTexImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCopyTexImage2D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glCopyTexImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCopyTexImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCopyTexImage1D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glCopyTexImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glCopyPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glCopyPixels(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glCopyPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColorPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColorPointer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glColorPointer((GLint) p0, (GLint) p1, (GLint) p2, (const void *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColorMaterial
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColorMaterial(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glColorMaterial((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColorMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColorMask(JNIEnv * env, jobject obj, jboolean p0, jboolean p1, jboolean p2, jboolean p3)
{
	glColorMask((GLboolean) p0, (GLboolean) p1, (GLboolean) p2, (GLboolean) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3b(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2)
{
	glColor3b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glColor3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glColor3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glColor3i((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glColor3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3ub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3ub(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2)
{
	glColor3ub((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3ui
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3ui(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glColor3ui((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor3us
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor3us(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glColor3us((GLshort) p0, (GLshort) p1, (GLshort) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4b(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2, jbyte p3)
{
	glColor4b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glColor4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glColor4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glColor4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glColor4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4ub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4ub(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2, jbyte p3)
{
	glColor4ub((GLbyte) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4ui
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4ui(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glColor4ui((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glColor4us
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glColor4us(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glColor4us((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glClipPlane
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glClipPlane(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glClipPlane((GLint) p0, (const GLdouble *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glClearStencil
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glClearStencil(JNIEnv * env, jobject obj, jint p0)
{
	glClearStencil((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glClearIndex
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glClearIndex(JNIEnv * env, jobject obj, jfloat p0)
{
	glClearIndex((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalPoint1
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalPoint1(JNIEnv * env, jobject obj, jint p0)
{
	glEvalPoint1((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalPoint2
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalPoint2(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glEvalPoint2((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalMesh1
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalMesh1(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glEvalMesh1((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalMesh2
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalMesh2(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glEvalMesh2((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalCoord1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalCoord1d(JNIEnv * env, jobject obj, jdouble p0)
{
	glEvalCoord1d((GLdouble) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalCoord1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalCoord1f(JNIEnv * env, jobject obj, jfloat p0)
{
	glEvalCoord1f((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalCoord2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalCoord2d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glEvalCoord2d((GLdouble) p0, (GLdouble) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalCoord2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalCoord2f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glEvalCoord2f((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalCoord1dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalCoord1dv(JNIEnv * env, jobject obj, jint p0)
{
	glEvalCoord1dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalCoord1fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalCoord1fv(JNIEnv * env, jobject obj, jint p0)
{
	glEvalCoord1fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalCoord2dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalCoord2dv(JNIEnv * env, jobject obj, jint p0)
{
	glEvalCoord2dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEvalCoord2fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEvalCoord2fv(JNIEnv * env, jobject obj, jint p0)
{
	glEvalCoord2fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEnableClientState
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEnableClientState(JNIEnv * env, jobject obj, jint p0)
{
	glEnableClientState((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDisableClientState
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDisableClientState(JNIEnv * env, jobject obj, jint p0)
{
	glDisableClientState((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEnable
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEnable(JNIEnv * env, jobject obj, jint p0)
{
	glEnable((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDisable
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDisable(JNIEnv * env, jobject obj, jint p0)
{
	glDisable((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEdgeFlagPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEdgeFlagPointer(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glEdgeFlagPointer((GLint) p0, (const void *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEdgeFlag
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEdgeFlag(JNIEnv * env, jobject obj, jboolean p0)
{
	glEdgeFlag((GLboolean) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEdgeFlagv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEdgeFlagv(JNIEnv * env, jobject obj, jint p0)
{
	glEdgeFlagv((const unsigned char *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDrawPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDrawPixels(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glDrawPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (const void *) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDrawElements
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDrawElements(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glDrawElements((GLint) p0, (GLint) p1, (GLint) p2, (const void *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDrawBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDrawBuffer(JNIEnv * env, jobject obj, jint p0)
{
	glDrawBuffer((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDrawArrays
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDrawArrays(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glDrawArrays((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDepthRange
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDepthRange(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glDepthRange((GLdouble) p0, (GLdouble) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDepthMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDepthMask(JNIEnv * env, jobject obj, jboolean p0)
{
	glDepthMask((GLboolean) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glDepthFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glDepthFunc(JNIEnv * env, jobject obj, jint p0)
{
	glDepthFunc((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFeedbackBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFeedbackBuffer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glFeedbackBuffer((GLint) p0, (GLint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetPixelMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetPixelMapfv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetPixelMapfv((GLint) p0, (GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetPixelMapuiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetPixelMapuiv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetPixelMapuiv((GLint) p0, (GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetPixelMapusv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetPixelMapusv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetPixelMapusv((GLint) p0, (GLushort *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetMaterialfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetMaterialfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMaterialfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetMaterialiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetMaterialiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMaterialiv((GLint) p0, (GLint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetMapdv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetMapdv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMapdv((GLint) p0, (GLint) p1, (GLdouble *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetMapfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMapfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetMapiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetMapiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMapiv((GLint) p0, (GLint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetLightfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetLightfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetLightfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetLightiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetLightiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetLightiv((GLint) p0, (GLint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetError
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_CoreGL_glGetError(JNIEnv * env, jobject obj)
{
	return (jint) glGetError();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetClipPlane
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetClipPlane(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetClipPlane((GLint) p0, (GLdouble *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetBooleanv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetBooleanv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetBooleanv((GLint) p0, (GLubyte *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetDoublev
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetDoublev(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetDoublev((GLint) p0, (GLdouble *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetFloatv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetFloatv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetFloatv((GLint) p0, (GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetIntegerv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetIntegerv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetIntegerv((GLint) p0, (GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGenTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGenTextures(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGenTextures((GLint) p0, (GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGenLists
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_CoreGL_glGenLists(JNIEnv * env, jobject obj, jint p0)
{
	return (jint) glGenLists((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFrustum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFrustum(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glFrustum((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFrontFace
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFrontFace(JNIEnv * env, jobject obj, jint p0)
{
	glFrontFace((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFogf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFogf(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glFogf((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFogi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFogi(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glFogi((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFogfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFogfv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glFogfv((GLint) p0, (const GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFogiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFogiv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glFogiv((GLint) p0, (const GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFlush
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFlush(JNIEnv * env, jobject obj)
{
	glFlush();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glFinish
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glFinish(JNIEnv * env, jobject obj)
{
	glFinish();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetPointerv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetPointerv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetPointerv((GLint) p0, (void **) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIsEnabled
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL_glIsEnabled(JNIEnv * env, jobject obj, jint p0)
{
	return (jboolean) glIsEnabled((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glInterleavedArrays
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glInterleavedArrays(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glInterleavedArrays((GLint) p0, (GLint) p1, (const void *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glInitNames
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glInitNames(JNIEnv * env, jobject obj)
{
	glInitNames();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexPointer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glIndexPointer((GLint) p0, (GLint) p1, (const void *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexMask(JNIEnv * env, jobject obj, jint p0)
{
	glIndexMask((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexd(JNIEnv * env, jobject obj, jdouble p0)
{
	glIndexd((GLdouble) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexf(JNIEnv * env, jobject obj, jfloat p0)
{
	glIndexf((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexi(JNIEnv * env, jobject obj, jint p0)
{
	glIndexi((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexs
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexs(JNIEnv * env, jobject obj, jshort p0)
{
	glIndexs((GLshort) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexub(JNIEnv * env, jobject obj, jbyte p0)
{
	glIndexub((GLbyte) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexdv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexdv(JNIEnv * env, jobject obj, jint p0)
{
	glIndexdv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexfv(JNIEnv * env, jobject obj, jint p0)
{
	glIndexfv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexiv(JNIEnv * env, jobject obj, jint p0)
{
	glIndexiv((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexsv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexsv(JNIEnv * env, jobject obj, jint p0)
{
	glIndexsv((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIndexubv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glIndexubv(JNIEnv * env, jobject obj, jint p0)
{
	glIndexubv((const unsigned char *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glHint
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glHint(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glHint((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexParameterfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexParameterfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexParameterfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexParameteriv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexParameteriv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexParameteriv((GLint) p0, (GLint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexLevelParameterfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexLevelParameterfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glGetTexLevelParameterfv((GLint) p0, (GLint) p1, (GLint) p2, (GLfloat *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexLevelParameteriv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexLevelParameteriv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glGetTexLevelParameteriv((GLint) p0, (GLint) p1, (GLint) p2, (GLint *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexImage
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexImage(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glGetTexImage((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (void *) p4);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexGendv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexGendv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexGendv((GLint) p0, (GLint) p1, (GLdouble *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexGenfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexGenfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexGenfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexGeniv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexGeniv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexGeniv((GLint) p0, (GLint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexEnvfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexEnvfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexEnvfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetTexEnviv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetTexEnviv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexEnviv((GLint) p0, (GLint) p1, (GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetString
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_CoreGL_glGetString(JNIEnv * env, jobject obj, jint p0)
{
	const GLubyte * string = glGetString((GLenum) p0);
	return env->NewStringUTF((const char *) string);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glGetPolygonStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glGetPolygonStipple(JNIEnv * env, jobject obj, jint p0)
{
	glGetPolygonStipple((GLubyte *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIsList
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL_glIsList(JNIEnv * env, jobject obj, jint p0)
{
	return (jboolean) glIsList((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMaterialf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMaterialf(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2)
{
	glMaterialf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMateriali
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMateriali(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glMateriali((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMaterialfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMaterialfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glMaterialfv((GLint) p0, (GLint) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMaterialiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMaterialiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glMaterialiv((GLint) p0, (GLint) p1, (const GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMapGrid1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMapGrid1d(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2)
{
	glMapGrid1d((GLint) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMapGrid1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMapGrid1f(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2)
{
	glMapGrid1f((GLint) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMapGrid2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMapGrid2d(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jint p3, jdouble p4, jdouble p5)
{
	glMapGrid2d((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLint) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMapGrid2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMapGrid2f(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jint p3, jfloat p4, jfloat p5)
{
	glMapGrid2f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLfloat) p4, (GLfloat) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMap2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMap2d(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jint p3, jint p4, jdouble p5, jdouble p6, jint p7, jint p8, jint p9)
{
	glMap2d((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLint) p3, (GLint) p4, (GLdouble) p5, (GLdouble) p6, (GLint) p7, (GLint) p8, (const GLdouble *) p9);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMap2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMap2f(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jint p3, jint p4, jfloat p5, jfloat p6, jint p7, jint p8, jint p9)
{
	glMap2f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLint) p4, (GLfloat) p5, (GLfloat) p6, (GLint) p7, (GLint) p8, (const GLfloat *) p9);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMap1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMap1d(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jint p3, jint p4, jint p5)
{
	glMap1d((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLint) p3, (GLint) p4, (const GLdouble *) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMap1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMap1f(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jint p3, jint p4, jint p5)
{
	glMap1f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLint) p4, (const GLfloat *) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLogicOp
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLogicOp(JNIEnv * env, jobject obj, jint p0)
{
	glLogicOp((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLoadName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLoadName(JNIEnv * env, jobject obj, jint p0)
{
	glLoadName((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLoadMatrixd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLoadMatrixd(JNIEnv * env, jobject obj, jint p0)
{
	glLoadMatrixd((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLoadMatrixf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLoadMatrixf(JNIEnv * env, jobject obj, jint p0)
{
	glLoadMatrixf((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLoadIdentity
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLoadIdentity(JNIEnv * env, jobject obj)
{
	glLoadIdentity();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glListBase
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glListBase(JNIEnv * env, jobject obj, jint p0)
{
	glListBase((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLineWidth
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLineWidth(JNIEnv * env, jobject obj, jfloat p0)
{
	glLineWidth((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLineStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLineStipple(JNIEnv * env, jobject obj, jint p0, jshort p1)
{
	glLineStipple((GLint) p0, (GLshort) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLightModelf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLightModelf(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glLightModelf((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLightModeli
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLightModeli(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glLightModeli((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLightModelfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLightModelfv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glLightModelfv((GLint) p0, (const GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLightModeliv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLightModeliv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glLightModeliv((GLint) p0, (const GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLightf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLightf(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2)
{
	glLightf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLighti
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLighti(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glLighti((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLightfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLightfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glLightfv((GLint) p0, (GLint) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glLightiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glLightiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glLightiv((GLint) p0, (GLint) p1, (const GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glIsTexture
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL_glIsTexture(JNIEnv * env, jobject obj, jint p0)
{
	return (jboolean) glIsTexture((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMatrixMode
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMatrixMode(JNIEnv * env, jobject obj, jint p0)
{
	glMatrixMode((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPolygonStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPolygonStipple(JNIEnv * env, jobject obj, jint p0)
{
	glPolygonStipple((const unsigned char *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPolygonOffset
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPolygonOffset(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glPolygonOffset((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPolygonMode
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPolygonMode(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glPolygonMode((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPointSize
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPointSize(JNIEnv * env, jobject obj, jfloat p0)
{
	glPointSize((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelZoom
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelZoom(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glPixelZoom((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelTransferf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelTransferf(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glPixelTransferf((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelTransferi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelTransferi(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glPixelTransferi((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelStoref
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelStoref(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glPixelStoref((GLint) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelStorei
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelStorei(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glPixelStorei((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelMapfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glPixelMapfv((GLint) p0, (GLint) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelMapuiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelMapuiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glPixelMapuiv((GLint) p0, (GLint) p1, (const GLuint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPixelMapusv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPixelMapusv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glPixelMapusv((GLint) p0, (GLint) p1, (const GLushort *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPassThrough
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPassThrough(JNIEnv * env, jobject obj, jfloat p0)
{
	glPassThrough((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glOrtho
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glOrtho(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glOrtho((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormalPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormalPointer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glNormalPointer((GLint) p0, (GLint) p1, (const void *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormal3b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormal3b(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2)
{
	glNormal3b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormal3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormal3d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glNormal3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormal3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormal3f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glNormal3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormal3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormal3i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glNormal3i((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormal3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormal3s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glNormal3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormal3bv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormal3bv(JNIEnv * env, jobject obj, jint p0)
{
	glNormal3bv((const GLbyte *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormal3dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormal3dv(JNIEnv * env, jobject obj, jint p0)
{
	glNormal3dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormal3fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormal3fv(JNIEnv * env, jobject obj, jint p0)
{
	glNormal3fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormal3iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormal3iv(JNIEnv * env, jobject obj, jint p0)
{
	glNormal3iv((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNormal3sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNormal3sv(JNIEnv * env, jobject obj, jint p0)
{
	glNormal3sv((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glNewList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glNewList(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glNewList((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glEndList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glEndList(JNIEnv * env, jobject obj)
{
	glEndList();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultMatrixd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultMatrixd(JNIEnv * env, jobject obj, jint p0)
{
	glMultMatrixd((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glMultMatrixf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glMultMatrixf(JNIEnv * env, jobject obj, jint p0)
{
	glMultMatrixf((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPrioritizeTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPrioritizeTextures(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glPrioritizeTextures((GLint) p0, (const GLuint *) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glShadeModel
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glShadeModel(JNIEnv * env, jobject obj, jint p0)
{
	glShadeModel((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glSelectBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glSelectBuffer(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glSelectBuffer((GLint) p0, (GLuint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glScissor
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glScissor(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glScissor((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glScaled
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glScaled(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glScaled((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glScalef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glScalef(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glScalef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRotated
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRotated(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glRotated((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRotatef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRotatef(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRotatef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRenderMode
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_CoreGL_glRenderMode(JNIEnv * env, jobject obj, jint p0)
{
	return (jint) glRenderMode((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRectd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRectd(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glRectd((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRectf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRectf(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRectf((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRecti
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRecti(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glRecti((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRects
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRects(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glRects((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRectdv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRectdv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRectdv((const GLdouble *) p0, (const GLdouble *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRectfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRectfv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRectfv((const GLfloat *) p0, (const GLfloat *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRectiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRectiv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRectiv((const GLint *) p0, (const GLint *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRectsv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRectsv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRectsv((const GLshort *) p0, (const GLshort *) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glReadPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glReadPixels(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glReadPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (void *) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glReadBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glReadBuffer(JNIEnv * env, jobject obj, jint p0)
{
	glReadBuffer((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos2d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glRasterPos2d((GLdouble) p0, (GLdouble) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos2f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glRasterPos2f((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos2i(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRasterPos2i((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos2s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos2s(JNIEnv * env, jobject obj, jshort p0, jshort p1)
{
	glRasterPos2s((GLshort) p0, (GLshort) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos3d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glRasterPos3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos3f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glRasterPos3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos3i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glRasterPos3i((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos3s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glRasterPos3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos4d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glRasterPos4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos4f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRasterPos4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos4i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glRasterPos4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos4s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glRasterPos4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos2dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos2dv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos2dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos2fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos2fv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos2fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos2iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos2iv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos2iv((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos2sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos2sv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos2sv((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos3dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos3dv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos3dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos3fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos3fv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos3fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos3iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos3iv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos3iv((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos3sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos3sv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos3sv((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos4dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos4dv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos4dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos4fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos4fv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos4fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos4iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos4iv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos4iv((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glRasterPos4sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glRasterPos4sv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos4sv((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPushName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPushName(JNIEnv * env, jobject obj, jint p0)
{
	glPushName((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPopName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPopName(JNIEnv * env, jobject obj)
{
	glPopName();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPushMatrix
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPushMatrix(JNIEnv * env, jobject obj)
{
	glPushMatrix();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPopMatrix
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPopMatrix(JNIEnv * env, jobject obj)
{
	glPopMatrix();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPushClientAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPushClientAttrib(JNIEnv * env, jobject obj, jint p0)
{
	glPushClientAttrib((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPopClientAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPopClientAttrib(JNIEnv * env, jobject obj)
{
	glPopClientAttrib();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPushAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPushAttrib(JNIEnv * env, jobject obj, jint p0)
{
	glPushAttrib((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glPopAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glPopAttrib(JNIEnv * env, jobject obj)
{
	glPopAttrib();
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glStencilFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glStencilFunc(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glStencilFunc((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertexPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertexPointer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glVertexPointer((GLint) p0, (GLint) p1, (GLint) p2, (const void *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex2d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glVertex2d((GLdouble) p0, (GLdouble) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex2f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glVertex2f((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex2i(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertex2i((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex2s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex2s(JNIEnv * env, jobject obj, jshort p0, jshort p1)
{
	glVertex2s((GLshort) p0, (GLshort) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex3d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glVertex3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex3f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glVertex3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex3i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertex3i((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex3s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glVertex3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex4d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glVertex4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex4f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glVertex4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex4i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glVertex4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex4s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glVertex4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex2dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex2dv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex2dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex2fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex2fv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex2fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex2iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex2iv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex2iv((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex2sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex2sv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex2sv((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex3dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex3dv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex3dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex3fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex3fv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex3fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex3iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex3iv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex3iv((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex3sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex3sv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex3sv((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex4dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex4dv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex4dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex4fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex4fv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex4fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex4iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex4iv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex4iv((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glVertex4sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glVertex4sv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex4sv((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTranslated
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTranslated(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glTranslated((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTranslatef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTranslatef(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glTranslatef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexSubImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexSubImage2D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8)
{
	glTexSubImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (const void *) p8);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexSubImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexSubImage1D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glTexSubImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (const void *) p6);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexParameterf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexParameterf(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2)
{
	glTexParameterf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexParameteri
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexParameteri(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexParameteri((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexImage2D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8)
{
	glTexImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (const void *) p8);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexImage1D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glTexImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (const void *) p7);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexGend
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexGend(JNIEnv * env, jobject obj, jint p0, jint p1, jdouble p2)
{
	glTexGend((GLint) p0, (GLint) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexGenf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexGenf(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2)
{
	glTexGenf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexGeni
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexGeni(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexGeni((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexGendv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexGendv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexGendv((GLint) p0, (GLint) p1, (const GLdouble *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexGenfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexGenfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexGenfv((GLint) p0, (GLint) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexGeniv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexGeniv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexGeniv((GLint) p0, (GLint) p1, (const GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexEnvf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexEnvf(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2)
{
	glTexEnvf((GLint) p0, (GLint) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexEnvi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexEnvi(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexEnvi((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexEnvfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexEnvfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexEnvfv((GLint) p0, (GLint) p1, (const GLfloat *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexEnviv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexEnviv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexEnviv((GLint) p0, (GLint) p1, (const GLint *) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoordPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoordPointer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glTexCoordPointer((GLint) p0, (GLint) p1, (GLint) p2, (const void *) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord1d(JNIEnv * env, jobject obj, jdouble p0)
{
	glTexCoord1d((GLdouble) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord1f(JNIEnv * env, jobject obj, jfloat p0)
{
	glTexCoord1f((GLfloat) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord1i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord1i(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord1i((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord1s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord1s(JNIEnv * env, jobject obj, jshort p0)
{
	glTexCoord1s((GLshort) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord2d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glTexCoord2d((GLdouble) p0, (GLdouble) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord2f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glTexCoord2f((GLfloat) p0, (GLfloat) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord2i(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glTexCoord2i((GLint) p0, (GLint) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord2s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord2s(JNIEnv * env, jobject obj, jshort p0, jshort p1)
{
	glTexCoord2s((GLshort) p0, (GLshort) p1);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord3d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glTexCoord3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord3f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glTexCoord3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord3i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexCoord3i((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord3s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glTexCoord3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord4d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glTexCoord4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord4f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glTexCoord4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord4i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glTexCoord4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord4s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glTexCoord4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord1dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord1dv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord1dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord1fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord1fv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord1fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord1iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord1iv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord1iv((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord1sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord1sv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord1sv((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord2dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord2dv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord2dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord2fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord2fv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord2fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord2iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord2iv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord2iv((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord2sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord2sv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord2sv((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord3dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord3dv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord3dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord3fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord3fv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord3fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord3iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord3iv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord3iv((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord3sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord3sv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord3sv((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord4dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord4dv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord4dv((const GLdouble *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord4fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord4fv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord4fv((const GLfloat *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord4iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord4iv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord4iv((const GLint *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glTexCoord4sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glTexCoord4sv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord4sv((const GLshort *) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glStencilOp
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glStencilOp(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glStencilOp((GLint) p0, (GLint) p1, (GLint) p2);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glStencilMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glStencilMask(JNIEnv * env, jobject obj, jint p0)
{
	glStencilMask((GLint) p0);
	CHECK_GL_ERROR
}

/*
 * Class:     org_lwjgl_opengl_CoreGL
 * Method:    glViewport
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL_glViewport(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glViewport((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
	CHECK_GL_ERROR
}

