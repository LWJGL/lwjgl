#include "tools.h"

void throwException(JNIEnv* env, const char* msg) {
	jclass cls = env->FindClass("java/lang/Exception");
	env->ThrowNew(cls, err);
	env->DeleteLocalRef(cls);
}
