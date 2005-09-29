/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glMultiDrawArraysEXTPROC) (GLenum mode, GLint * piFirst, GLsizei * piCount, GLint primcount);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTMultiDrawArrays_nglMultiDrawArraysEXT(JNIEnv *env, jclass clazz, jint mode, jobject piFirst, jint piFirst_position, jobject piCount, jint piCount_position, jint primcount, jlong function_pointer) {
	GLint *piFirst_address = ((GLint *)(*env)->GetDirectBufferAddress(env, piFirst)) + piFirst_position;
	GLsizei *piCount_address = ((GLsizei *)(*env)->GetDirectBufferAddress(env, piCount)) + piCount_position;
	glMultiDrawArraysEXTPROC glMultiDrawArraysEXT = (glMultiDrawArraysEXTPROC)((intptr_t)function_pointer);
	glMultiDrawArraysEXT(mode, piFirst_address, piCount_address, primcount);
}

