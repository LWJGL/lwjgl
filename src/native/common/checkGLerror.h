/*
	checkGLerror.h

	Author:		C. Prince
	Created:	8 November 2001

	Error checking for OpenGL bindings
*/

#ifndef _CHECKGLERROR_H_INCLUDED_
#define _CHECKGLERROR_H_INCLUDED_

#include <jni.h>
#include "extgl.h"
#include "common_tools.h"

#define CHECK_GL_ERROR \
	{ \
		if (ISDEBUGENABLED()) { \
			int err = glGetError(); \
			if (err != GL_NO_ERROR) { \
				jclass cls = env->FindClass("org/lwjgl/opengl/OpenGLException"); \
				jmethodID ctor = env->GetMethodID(cls, "<init>", "(I)V"); \
			        jthrowable exception = (jthrowable)env->NewObject(cls, ctor, err); \
				env->Throw(exception); \
				env->DeleteLocalRef(cls); \
			} \
		} \
	}

#endif /* _CHECKGLERROR_H_INCLUDED_ */
