/*
 * org_lwjgl_opengl_CoreGL11.cpp
 *
 * Win32 specific library
 *
 * (C) 2002 Shaven Puppy Ltd
 *
 * Created 10 August 2002 by foo
 *
 */

#include <windows.h>
#include "org_lwjgl_opengl_CoreGL11.h"
#include "extgl.h"

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glAccum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glAccum(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glAccum((GLint) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glAlphaFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glAlphaFunc(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glAlphaFunc((GLint) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glClearColor
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glClearColor(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glClearColor((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glClearAccum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glClearAccum(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glClearAccum((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glClear
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glClear(JNIEnv * env, jobject obj, jint p0)
{
	glClear((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCallLists
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glCallLists(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glCallLists((GLint) p0, (GLint) p1, (const void *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCallList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glCallList(JNIEnv * env, jobject obj, jint p0)
{
	glCallList((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glBlendFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glBlendFunc(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glBlendFunc((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glBlendColorEXT
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glBlendColorEXT(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glBlendColor((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glBitmap
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glBitmap(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2, jfloat p3, jfloat p4, jfloat p5, jint p6)
{
	glBitmap((GLint) p0, (GLint) p1, (GLfloat) p2, (GLfloat) p3, (GLfloat) p4, (GLfloat) p5, (const unsigned char *) p6);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glBindTexture
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glBindTexture(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glBindTexture((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glBegin
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glBegin(JNIEnv * env, jobject obj, jint p0)
{
	glBegin((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEnd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEnd(JNIEnv * env, jobject obj)
{
	glEnd();
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glArrayElement
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glArrayElement(JNIEnv * env, jobject obj, jint p0)
{
	glArrayElement((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glAreTexturesResident
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL11_glAreTexturesResident(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	return (jboolean) glAreTexturesResident((GLint) p0, (const GLuint *) p1, (GLboolean *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glClearDepth
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glClearDepth(JNIEnv * env, jobject obj, jdouble p0)
{
	glClearDepth((GLdouble) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDeleteLists
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDeleteLists(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glDeleteLists((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDeleteTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDeleteTextures(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glDeleteTextures((GLint) p0, (const GLuint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCullFace
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glCullFace(JNIEnv * env, jobject obj, jint p0)
{
	glCullFace((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCopyTexSubImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glCopyTexSubImage2D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glCopyTexSubImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCopyTexSubImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glCopyTexSubImage1D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5)
{
	glCopyTexSubImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCopyTexImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glCopyTexImage2D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glCopyTexImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCopyTexImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glCopyTexImage1D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glCopyTexImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glCopyPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glCopyPixels(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glCopyPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColorPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColorPointer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glColorPointer((GLint) p0, (GLint) p1, (GLint) p2, (const void *) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColorMaterial
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColorMaterial(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glColorMaterial((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColorMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColorMask(JNIEnv * env, jobject obj, jboolean p0, jboolean p1, jboolean p2, jboolean p3)
{
	glColorMask((GLboolean) p0, (GLboolean) p1, (GLboolean) p2, (GLboolean) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor3b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor3b(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2)
{
	glColor3b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor3d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glColor3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor3f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glColor3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor3i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glColor3i((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor3s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glColor3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor3ub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor3ub(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2)
{
	glColor3ub((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor3ui
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor3ui(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glColor3ui((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor3us
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor3us(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glColor3us((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor4b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor4b(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2, jbyte p3)
{
	glColor4b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor4d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glColor4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor4f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glColor4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor4i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glColor4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor4s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glColor4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor4ub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor4ub(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2, jbyte p3)
{
	glColor4ub((GLbyte) p0, (GLbyte) p1, (GLbyte) p2, (GLbyte) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor4ui
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor4ui(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glColor4ui((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glColor4us
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glColor4us(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glColor4us((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glClipPlane
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glClipPlane(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glClipPlane((GLint) p0, (const GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glClearStencil
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glClearStencil(JNIEnv * env, jobject obj, jint p0)
{
	glClearStencil((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glClearIndex
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glClearIndex(JNIEnv * env, jobject obj, jfloat p0)
{
	glClearIndex((GLfloat) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalPoint1
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalPoint1(JNIEnv * env, jobject obj, jint p0)
{
	glEvalPoint1((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalPoint2
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalPoint2(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glEvalPoint2((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalMesh1
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalMesh1(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glEvalMesh1((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalMesh2
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalMesh2(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glEvalMesh2((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalCoord1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalCoord1d(JNIEnv * env, jobject obj, jdouble p0)
{
	glEvalCoord1d((GLdouble) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalCoord1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalCoord1f(JNIEnv * env, jobject obj, jfloat p0)
{
	glEvalCoord1f((GLfloat) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalCoord2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalCoord2d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glEvalCoord2d((GLdouble) p0, (GLdouble) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalCoord2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalCoord2f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glEvalCoord2f((GLfloat) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalCoord1dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalCoord1dv(JNIEnv * env, jobject obj, jint p0)
{
	glEvalCoord1dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalCoord1fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalCoord1fv(JNIEnv * env, jobject obj, jint p0)
{
	glEvalCoord1fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalCoord2dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalCoord2dv(JNIEnv * env, jobject obj, jint p0)
{
	glEvalCoord2dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEvalCoord2fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEvalCoord2fv(JNIEnv * env, jobject obj, jint p0)
{
	glEvalCoord2fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEnableClientState
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEnableClientState(JNIEnv * env, jobject obj, jint p0)
{
	glEnableClientState((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDisableClientState
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDisableClientState(JNIEnv * env, jobject obj, jint p0)
{
	glDisableClientState((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEnable
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEnable(JNIEnv * env, jobject obj, jint p0)
{
	glEnable((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDisable
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDisable(JNIEnv * env, jobject obj, jint p0)
{
	glDisable((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEdgeFlagPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEdgeFlagPointer(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glEdgeFlagPointer((GLint) p0, (const void *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEdgeFlag
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEdgeFlag(JNIEnv * env, jobject obj, jboolean p0)
{
	glEdgeFlag((GLboolean) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEdgeFlagv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEdgeFlagv(JNIEnv * env, jobject obj, jint p0)
{
	glEdgeFlagv((const unsigned char *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDrawPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDrawPixels(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glDrawPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (const void *) p4);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDrawElements
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDrawElements(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glDrawElements((GLint) p0, (GLint) p1, (GLint) p2, (const void *) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDrawBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDrawBuffer(JNIEnv * env, jobject obj, jint p0)
{
	glDrawBuffer((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDrawArrays
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDrawArrays(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glDrawArrays((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDepthRange
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDepthRange(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glDepthRange((GLdouble) p0, (GLdouble) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDepthMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDepthMask(JNIEnv * env, jobject obj, jboolean p0)
{
	glDepthMask((GLboolean) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glDepthFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glDepthFunc(JNIEnv * env, jobject obj, jint p0)
{
	glDepthFunc((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFeedbackBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glFeedbackBuffer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glFeedbackBuffer((GLint) p0, (GLint) p1, (GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetPixelMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetPixelMapfv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetPixelMapfv((GLint) p0, (GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetPixelMapuiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetPixelMapuiv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetPixelMapuiv((GLint) p0, (GLuint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetPixelMapusv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetPixelMapusv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetPixelMapusv((GLint) p0, (GLushort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetMaterialfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetMaterialfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMaterialfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetMaterialiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetMaterialiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMaterialiv((GLint) p0, (GLint) p1, (GLint *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetMapdv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetMapdv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMapdv((GLint) p0, (GLint) p1, (GLdouble *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetMapfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMapfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetMapiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetMapiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetMapiv((GLint) p0, (GLint) p1, (GLint *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetLightfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetLightfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetLightfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetLightiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetLightiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetLightiv((GLint) p0, (GLint) p1, (GLint *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetError
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetError(JNIEnv * env, jobject obj)
{
	return (jint) glGetError();
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetClipPlane
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetClipPlane(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetClipPlane((GLint) p0, (GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetBooleanv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetBooleanv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetBooleanv((GLint) p0, (GLubyte *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetDoublev
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetDoublev(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetDoublev((GLint) p0, (GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetFloatv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetFloatv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetFloatv((GLint) p0, (GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetIntegerv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetIntegerv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetIntegerv((GLint) p0, (GLint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGenTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGenTextures(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGenTextures((GLint) p0, (GLuint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGenLists
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_CoreGL11_glGenLists(JNIEnv * env, jobject obj, jint p0)
{
	return (jint) glGenLists((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFrustum
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glFrustum(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glFrustum((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFrontFace
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glFrontFace(JNIEnv * env, jobject obj, jint p0)
{
	glFrontFace((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFogf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glFogf(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glFogf((GLint) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFogi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glFogi(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glFogi((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFogfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glFogfv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glFogfv((GLint) p0, (const GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFogiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glFogiv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glFogiv((GLint) p0, (const GLint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFlush
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glFlush(JNIEnv * env, jobject obj)
{
	glFlush();
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glFinish
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glFinish(JNIEnv * env, jobject obj)
{
	glFinish();
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetPointerv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetPointerv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glGetPointerv((GLint) p0, (void **) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIsEnabled
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL11_glIsEnabled(JNIEnv * env, jobject obj, jint p0)
{
	return (jboolean) glIsEnabled((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glInterleavedArrays
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glInterleavedArrays(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glInterleavedArrays((GLint) p0, (GLint) p1, (const void *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glInitNames
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glInitNames(JNIEnv * env, jobject obj)
{
	glInitNames();
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIndexPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glIndexPointer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glIndexPointer((GLint) p0, (GLint) p1, (const void *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIndexMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glIndexMask(JNIEnv * env, jobject obj, jint p0)
{
	glIndexMask((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIndexd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glIndexd(JNIEnv * env, jobject obj, jdouble p0)
{
	glIndexd((GLdouble) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIndexf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glIndexf(JNIEnv * env, jobject obj, jfloat p0)
{
	glIndexf((GLfloat) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIndexi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glIndexi(JNIEnv * env, jobject obj, jint p0)
{
	glIndexi((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIndexs
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glIndexs(JNIEnv * env, jobject obj, jshort p0)
{
	glIndexs((GLshort) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIndexub
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glIndexub(JNIEnv * env, jobject obj, jbyte p0)
{
	glIndexub((GLbyte) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIndexdv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glIndexdv(JNIEnv * env, jobject obj, jint p0)
{
	glIndexdv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIndexfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glIndexfv(JNIEnv * env, jobject obj, jint p0)
{
	glIndexfv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIndexiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glIndexiv(JNIEnv * env, jobject obj, jint p0)
{
	glIndexiv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIndexsv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glIndexsv(JNIEnv * env, jobject obj, jint p0)
{
	glIndexsv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIndexubv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glIndexubv(JNIEnv * env, jobject obj, jint p0)
{
	glIndexubv((const unsigned char *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glHint
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glHint(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glHint((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexParameterfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetTexParameterfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexParameterfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexParameteriv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetTexParameteriv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexParameteriv((GLint) p0, (GLint) p1, (GLint *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexLevelParameterfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetTexLevelParameterfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glGetTexLevelParameterfv((GLint) p0, (GLint) p1, (GLint) p2, (GLfloat *) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexLevelParameteriv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetTexLevelParameteriv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glGetTexLevelParameteriv((GLint) p0, (GLint) p1, (GLint) p2, (GLint *) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexImage
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetTexImage(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4)
{
	glGetTexImage((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (void *) p4);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexGendv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetTexGendv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexGendv((GLint) p0, (GLint) p1, (GLdouble *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexGenfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetTexGenfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexGenfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexGeniv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetTexGeniv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexGeniv((GLint) p0, (GLint) p1, (GLint *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexEnvfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetTexEnvfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexEnvfv((GLint) p0, (GLint) p1, (GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetTexEnviv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetTexEnviv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glGetTexEnviv((GLint) p0, (GLint) p1, (GLint *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetString
 */
JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetString(JNIEnv * env, jobject obj, jint p0)
{
	const GLubyte * string = glGetString((GLenum) p0);
	return env->NewStringUTF((const char *) string);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glGetPolygonStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glGetPolygonStipple(JNIEnv * env, jobject obj, jint p0)
{
	glGetPolygonStipple((GLubyte *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIsList
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL11_glIsList(JNIEnv * env, jobject obj, jint p0)
{
	return (jboolean) glIsList((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMaterialf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMaterialf(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2)
{
	glMaterialf((GLint) p0, (GLint) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMateriali
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMateriali(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glMateriali((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMaterialfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMaterialfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glMaterialfv((GLint) p0, (GLint) p1, (const GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMaterialiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMaterialiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glMaterialiv((GLint) p0, (GLint) p1, (const GLint *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMapGrid1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMapGrid1d(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2)
{
	glMapGrid1d((GLint) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMapGrid1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMapGrid1f(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2)
{
	glMapGrid1f((GLint) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMapGrid2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMapGrid2d(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jint p3, jdouble p4, jdouble p5)
{
	glMapGrid2d((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLint) p3, (GLdouble) p4, (GLdouble) p5);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMapGrid2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMapGrid2f(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jint p3, jfloat p4, jfloat p5)
{
	glMapGrid2f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLfloat) p4, (GLfloat) p5);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMap2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMap2d(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jint p3, jint p4, jdouble p5, jdouble p6, jint p7, jint p8, jint p9)
{
	glMap2d((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLint) p3, (GLint) p4, (GLdouble) p5, (GLdouble) p6, (GLint) p7, (GLint) p8, (const GLdouble *) p9);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMap2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMap2f(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jint p3, jint p4, jfloat p5, jfloat p6, jint p7, jint p8, jint p9)
{
	glMap2f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLint) p4, (GLfloat) p5, (GLfloat) p6, (GLint) p7, (GLint) p8, (const GLfloat *) p9);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMap1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMap1d(JNIEnv * env, jobject obj, jint p0, jdouble p1, jdouble p2, jint p3, jint p4, jint p5)
{
	glMap1d((GLint) p0, (GLdouble) p1, (GLdouble) p2, (GLint) p3, (GLint) p4, (const GLdouble *) p5);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMap1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMap1f(JNIEnv * env, jobject obj, jint p0, jfloat p1, jfloat p2, jint p3, jint p4, jint p5)
{
	glMap1f((GLint) p0, (GLfloat) p1, (GLfloat) p2, (GLint) p3, (GLint) p4, (const GLfloat *) p5);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLogicOp
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLogicOp(JNIEnv * env, jobject obj, jint p0)
{
	glLogicOp((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLoadName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLoadName(JNIEnv * env, jobject obj, jint p0)
{
	glLoadName((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLoadMatrixd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLoadMatrixd(JNIEnv * env, jobject obj, jint p0)
{
	glLoadMatrixd((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLoadMatrixf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLoadMatrixf(JNIEnv * env, jobject obj, jint p0)
{
	glLoadMatrixf((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLoadIdentity
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLoadIdentity(JNIEnv * env, jobject obj)
{
	glLoadIdentity();
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glListBase
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glListBase(JNIEnv * env, jobject obj, jint p0)
{
	glListBase((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLineWidth
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLineWidth(JNIEnv * env, jobject obj, jfloat p0)
{
	glLineWidth((GLfloat) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLineStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLineStipple(JNIEnv * env, jobject obj, jint p0, jshort p1)
{
	glLineStipple((GLint) p0, (GLshort) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLightModelf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLightModelf(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glLightModelf((GLint) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLightModeli
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLightModeli(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glLightModeli((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLightModelfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLightModelfv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glLightModelfv((GLint) p0, (const GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLightModeliv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLightModeliv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glLightModeliv((GLint) p0, (const GLint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLightf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLightf(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2)
{
	glLightf((GLint) p0, (GLint) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLighti
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLighti(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glLighti((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLightfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLightfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glLightfv((GLint) p0, (GLint) p1, (const GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glLightiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glLightiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glLightiv((GLint) p0, (GLint) p1, (const GLint *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glIsTexture
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_CoreGL11_glIsTexture(JNIEnv * env, jobject obj, jint p0)
{
	return (jboolean) glIsTexture((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMatrixMode
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMatrixMode(JNIEnv * env, jobject obj, jint p0)
{
	glMatrixMode((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPolygonStipple
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPolygonStipple(JNIEnv * env, jobject obj, jint p0)
{
	glPolygonStipple((const unsigned char *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPolygonOffset
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPolygonOffset(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glPolygonOffset((GLfloat) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPolygonMode
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPolygonMode(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glPolygonMode((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPointSize
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPointSize(JNIEnv * env, jobject obj, jfloat p0)
{
	glPointSize((GLfloat) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelZoom
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPixelZoom(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glPixelZoom((GLfloat) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelTransferf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPixelTransferf(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glPixelTransferf((GLint) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelTransferi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPixelTransferi(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glPixelTransferi((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelStoref
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPixelStoref(JNIEnv * env, jobject obj, jint p0, jfloat p1)
{
	glPixelStoref((GLint) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelStorei
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPixelStorei(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glPixelStorei((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelMapfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPixelMapfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glPixelMapfv((GLint) p0, (GLint) p1, (const GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelMapuiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPixelMapuiv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glPixelMapuiv((GLint) p0, (GLint) p1, (const GLuint *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPixelMapusv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPixelMapusv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glPixelMapusv((GLint) p0, (GLint) p1, (const GLushort *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPassThrough
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPassThrough(JNIEnv * env, jobject obj, jfloat p0)
{
	glPassThrough((GLfloat) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glOrtho
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glOrtho(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3, jdouble p4, jdouble p5)
{
	glOrtho((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3, (GLdouble) p4, (GLdouble) p5);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNormalPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNormalPointer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glNormalPointer((GLint) p0, (GLint) p1, (const void *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNormal3b
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNormal3b(JNIEnv * env, jobject obj, jbyte p0, jbyte p1, jbyte p2)
{
	glNormal3b((GLbyte) p0, (GLbyte) p1, (GLbyte) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNormal3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNormal3d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glNormal3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNormal3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNormal3f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glNormal3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNormal3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNormal3i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glNormal3i((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNormal3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNormal3s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glNormal3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNormal3bv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNormal3bv(JNIEnv * env, jobject obj, jint p0)
{
	glNormal3bv((const GLbyte *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNormal3dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNormal3dv(JNIEnv * env, jobject obj, jint p0)
{
	glNormal3dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNormal3fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNormal3fv(JNIEnv * env, jobject obj, jint p0)
{
	glNormal3fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNormal3iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNormal3iv(JNIEnv * env, jobject obj, jint p0)
{
	glNormal3iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNormal3sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNormal3sv(JNIEnv * env, jobject obj, jint p0)
{
	glNormal3sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glNewList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glNewList(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glNewList((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glEndList
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glEndList(JNIEnv * env, jobject obj)
{
	glEndList();
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMultMatrixd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMultMatrixd(JNIEnv * env, jobject obj, jint p0)
{
	glMultMatrixd((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glMultMatrixf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glMultMatrixf(JNIEnv * env, jobject obj, jint p0)
{
	glMultMatrixf((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPrioritizeTextures
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPrioritizeTextures(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glPrioritizeTextures((GLint) p0, (const GLuint *) p1, (const GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glShadeModel
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glShadeModel(JNIEnv * env, jobject obj, jint p0)
{
	glShadeModel((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glSelectBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glSelectBuffer(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glSelectBuffer((GLint) p0, (GLuint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glScissor
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glScissor(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glScissor((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glScaled
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glScaled(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glScaled((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glScalef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glScalef(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glScalef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRotated
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRotated(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glRotated((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRotatef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRotatef(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRotatef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRenderMode
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_CoreGL11_glRenderMode(JNIEnv * env, jobject obj, jint p0)
{
	return (jint) glRenderMode((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRectd
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRectd(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glRectd((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRectf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRectf(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRectf((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRecti
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRecti(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glRecti((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRects
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRects(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glRects((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRectdv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRectdv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRectdv((const GLdouble *) p0, (const GLdouble *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRectfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRectfv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRectfv((const GLfloat *) p0, (const GLfloat *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRectiv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRectiv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRectiv((const GLint *) p0, (const GLint *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRectsv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRectsv(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRectsv((const GLshort *) p0, (const GLshort *) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glReadPixels
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glReadPixels(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glReadPixels((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (void *) p6);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glReadBuffer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glReadBuffer(JNIEnv * env, jobject obj, jint p0)
{
	glReadBuffer((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos2d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glRasterPos2d((GLdouble) p0, (GLdouble) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos2f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glRasterPos2f((GLfloat) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos2i(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glRasterPos2i((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos2s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos2s(JNIEnv * env, jobject obj, jshort p0, jshort p1)
{
	glRasterPos2s((GLshort) p0, (GLshort) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos3d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glRasterPos3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos3f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glRasterPos3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos3i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glRasterPos3i((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos3s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glRasterPos3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos4d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glRasterPos4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos4f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glRasterPos4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos4i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glRasterPos4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos4s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glRasterPos4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos2dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos2dv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos2dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos2fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos2fv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos2fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos2iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos2iv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos2iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos2sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos2sv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos2sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos3dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos3dv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos3dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos3fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos3fv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos3fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos3iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos3iv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos3iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos3sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos3sv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos3sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos4dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos4dv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos4dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos4fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos4fv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos4fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos4iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos4iv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos4iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glRasterPos4sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glRasterPos4sv(JNIEnv * env, jobject obj, jint p0)
{
	glRasterPos4sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPushName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPushName(JNIEnv * env, jobject obj, jint p0)
{
	glPushName((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPopName
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPopName(JNIEnv * env, jobject obj)
{
	glPopName();
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPushMatrix
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPushMatrix(JNIEnv * env, jobject obj)
{
	glPushMatrix();
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPopMatrix
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPopMatrix(JNIEnv * env, jobject obj)
{
	glPopMatrix();
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPushClientAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPushClientAttrib(JNIEnv * env, jobject obj, jint p0)
{
	glPushClientAttrib((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPopClientAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPopClientAttrib(JNIEnv * env, jobject obj)
{
	glPopClientAttrib();
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPushAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPushAttrib(JNIEnv * env, jobject obj, jint p0)
{
	glPushAttrib((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glPopAttrib
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glPopAttrib(JNIEnv * env, jobject obj)
{
	glPopAttrib();
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glStencilFunc
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glStencilFunc(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glStencilFunc((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertexPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertexPointer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glVertexPointer((GLint) p0, (GLint) p1, (GLint) p2, (const void *) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex2d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glVertex2d((GLdouble) p0, (GLdouble) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex2f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glVertex2f((GLfloat) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex2i(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glVertex2i((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex2s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex2s(JNIEnv * env, jobject obj, jshort p0, jshort p1)
{
	glVertex2s((GLshort) p0, (GLshort) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex3d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glVertex3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex3f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glVertex3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex3i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glVertex3i((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex3s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glVertex3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex4d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glVertex4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex4f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glVertex4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex4i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glVertex4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex4s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glVertex4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex2dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex2dv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex2dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex2fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex2fv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex2fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex2iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex2iv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex2iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex2sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex2sv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex2sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex3dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex3dv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex3dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex3fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex3fv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex3fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex3iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex3iv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex3iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex3sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex3sv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex3sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex4dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex4dv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex4dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex4fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex4fv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex4fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex4iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex4iv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex4iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glVertex4sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glVertex4sv(JNIEnv * env, jobject obj, jint p0)
{
	glVertex4sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTranslated
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTranslated(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glTranslated((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTranslatef
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTranslatef(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glTranslatef((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexSubImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexSubImage2D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8)
{
	glTexSubImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (const void *) p8);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexSubImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexSubImage1D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6)
{
	glTexSubImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (const void *) p6);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexParameterf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexParameterf(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2)
{
	glTexParameterf((GLint) p0, (GLint) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexParameteri
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexParameteri(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexParameteri((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexImage2D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexImage2D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7, jint p8)
{
	glTexImage2D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (GLint) p7, (const void *) p8);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexImage1D
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexImage1D(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3, jint p4, jint p5, jint p6, jint p7)
{
	glTexImage1D((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3, (GLint) p4, (GLint) p5, (GLint) p6, (const void *) p7);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexGend
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexGend(JNIEnv * env, jobject obj, jint p0, jint p1, jdouble p2)
{
	glTexGend((GLint) p0, (GLint) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexGenf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexGenf(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2)
{
	glTexGenf((GLint) p0, (GLint) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexGeni
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexGeni(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexGeni((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexGendv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexGendv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexGendv((GLint) p0, (GLint) p1, (const GLdouble *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexGenfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexGenfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexGenfv((GLint) p0, (GLint) p1, (const GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexGeniv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexGeniv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexGeniv((GLint) p0, (GLint) p1, (const GLint *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexEnvf
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexEnvf(JNIEnv * env, jobject obj, jint p0, jint p1, jfloat p2)
{
	glTexEnvf((GLint) p0, (GLint) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexEnvi
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexEnvi(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexEnvi((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexEnvfv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexEnvfv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexEnvfv((GLint) p0, (GLint) p1, (const GLfloat *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexEnviv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexEnviv(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexEnviv((GLint) p0, (GLint) p1, (const GLint *) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoordPointer
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoordPointer(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glTexCoordPointer((GLint) p0, (GLint) p1, (GLint) p2, (const void *) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord1d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord1d(JNIEnv * env, jobject obj, jdouble p0)
{
	glTexCoord1d((GLdouble) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord1f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord1f(JNIEnv * env, jobject obj, jfloat p0)
{
	glTexCoord1f((GLfloat) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord1i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord1i(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord1i((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord1s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord1s(JNIEnv * env, jobject obj, jshort p0)
{
	glTexCoord1s((GLshort) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord2d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord2d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1)
{
	glTexCoord2d((GLdouble) p0, (GLdouble) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord2f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord2f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1)
{
	glTexCoord2f((GLfloat) p0, (GLfloat) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord2i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord2i(JNIEnv * env, jobject obj, jint p0, jint p1)
{
	glTexCoord2i((GLint) p0, (GLint) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord2s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord2s(JNIEnv * env, jobject obj, jshort p0, jshort p1)
{
	glTexCoord2s((GLshort) p0, (GLshort) p1);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord3d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord3d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2)
{
	glTexCoord3d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord3f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord3f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2)
{
	glTexCoord3f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord3i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord3i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glTexCoord3i((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord3s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord3s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2)
{
	glTexCoord3s((GLshort) p0, (GLshort) p1, (GLshort) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord4d
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord4d(JNIEnv * env, jobject obj, jdouble p0, jdouble p1, jdouble p2, jdouble p3)
{
	glTexCoord4d((GLdouble) p0, (GLdouble) p1, (GLdouble) p2, (GLdouble) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord4f
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord4f(JNIEnv * env, jobject obj, jfloat p0, jfloat p1, jfloat p2, jfloat p3)
{
	glTexCoord4f((GLfloat) p0, (GLfloat) p1, (GLfloat) p2, (GLfloat) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord4i
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord4i(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glTexCoord4i((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord4s
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord4s(JNIEnv * env, jobject obj, jshort p0, jshort p1, jshort p2, jshort p3)
{
	glTexCoord4s((GLshort) p0, (GLshort) p1, (GLshort) p2, (GLshort) p3);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord1dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord1dv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord1dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord1fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord1fv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord1fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord1iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord1iv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord1iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord1sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord1sv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord1sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord2dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord2dv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord2dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord2fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord2fv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord2fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord2iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord2iv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord2iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord2sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord2sv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord2sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord3dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord3dv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord3dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord3fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord3fv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord3fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord3iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord3iv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord3iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord3sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord3sv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord3sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord4dv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord4dv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord4dv((const GLdouble *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord4fv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord4fv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord4fv((const GLfloat *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord4iv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord4iv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord4iv((const GLint *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glTexCoord4sv
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glTexCoord4sv(JNIEnv * env, jobject obj, jint p0)
{
	glTexCoord4sv((const GLshort *) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glStencilOp
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glStencilOp(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2)
{
	glStencilOp((GLint) p0, (GLint) p1, (GLint) p2);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glStencilMask
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glStencilMask(JNIEnv * env, jobject obj, jint p0)
{
	glStencilMask((GLint) p0);
}

/*
 * Class:     org_lwjgl_opengl_CoreGL11
 * Method:    glViewport
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_CoreGL11_glViewport(JNIEnv * env, jobject obj, jint p0, jint p1, jint p2, jint p3)
{
	glViewport((GLint) p0, (GLint) p1, (GLint) p2, (GLint) p3);
}

