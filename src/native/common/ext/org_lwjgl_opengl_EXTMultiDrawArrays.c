/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glMultiDrawArraysEXTPROC) (GLenum mode, GLint * piFirst, GLsizei * piCount, GLint primcount);

static glMultiDrawArraysEXTPROC glMultiDrawArraysEXT;

static void JNICALL Java_org_lwjgl_opengl_EXTMultiDrawArrays_nglMultiDrawArraysEXT(JNIEnv *env, jclass clazz, jint mode, jobject piFirst, jint piFirst_position, jobject piCount, jint piCount_position, jint primcount) {
	GLint *piFirst_address = ((GLint *)(*env)->GetDirectBufferAddress(env, piFirst)) + piFirst_position;
	GLsizei *piCount_address = ((GLsizei *)(*env)->GetDirectBufferAddress(env, piCount)) + piCount_position;
	glMultiDrawArraysEXT(mode, piFirst_address, piCount_address, primcount);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTMultiDrawArrays_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglMultiDrawArraysEXT", "(ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;II)V", (void *)&Java_org_lwjgl_opengl_EXTMultiDrawArrays_nglMultiDrawArraysEXT, "glMultiDrawArraysEXT", (void *)&glMultiDrawArraysEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
