/*
	checkALerror.h

	Author:		C. Prince
	Created:	8 November 2001

	Error checking for OpenAL bindings
*/

#ifndef _CHECKALERROR_H_INCLUDED_
#define _CHECKALERROR_H_INCLUDED_

#include <jni.h>
#include "extal.h"
#include "common_tools.h"

#define CHECK_AL_ERROR \
	{ \
		if (isDebugEnabled()) { \
			int err = alGetError(); \
			if (err != AL_NO_ERROR) { \
				jclass cls = env->FindClass("org/lwjgl/openal/OpenALException"); \
				env->ThrowNew(cls, (const char*) alGetString(err)); \
				env->DeleteLocalRef(cls); \
			} \
		} \
	}
/* only available if deviceaddress is specified in method */
#define CHECK_ALC_ERROR \
	{ \
		if (isDebugEnabled()) { \
			int err = alcGetError((ALCdevice*) deviceaddress); \
			if (err != AL_NO_ERROR) { \
				jclass cls = env->FindClass("org/lwjgl/openal/OpenALException"); \
				env->ThrowNew(cls, (const char*) alcGetString((ALCdevice*) deviceaddress, err)); \
				env->DeleteLocalRef(cls); \
			} \
		} \
	}

#endif /* _CHECKALERROR_H_INCLUDED_ */
