/*
	checkGLerror.h

	Author:		C. Prince
	Created:	8 November 2001

	Error checking for OpenGL bindings
*/

#ifndef _CHECKGLERROR_H_INCLUDED_
#define _CHECKGLERROR_H_INCLUDED_

#ifdef _DEBUG

#include <jni.h>
#include <GL/glu.h>

#define CHECK_GL_ERROR \
	{ \
		int err = glGetError(); \
		if (err != GL_NO_ERROR) { \
			jclass cls = (*env)->FindClass(env, "org/lwjgl/opengl/OpenGLException"); \
			(*env)->ThrowNew(env, cls, (const char *)gluErrorString(err)); \
			(*env)->DeleteLocalRef(env, cls); \
		} \
	}

#else

#define CHECK_GL_ERROR

#endif /* _DEBUG */

#endif /* _CHECKGLERROR_H_INCLUDED_ */
