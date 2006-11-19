/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glProgramEnvParameter4fvEXTPROC) (GLenum target, GLuint index, GLsizei count, const GLfloat * params);
typedef void (APIENTRY *glProgramLocalParameter4fvEXTPROC) (GLenum target, GLuint index, GLsizei count, const GLfloat * params);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTGpuProgramParameters_nglProgramEnvParameter4fvEXT(JNIEnv *env, jclass clazz, jint target, jint index, jint count, jobject params, jint params_position, jlong function_pointer) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glProgramEnvParameter4fvEXTPROC glProgramEnvParameter4fvEXT = (glProgramEnvParameter4fvEXTPROC)((intptr_t)function_pointer);
	glProgramEnvParameter4fvEXT(target, index, count, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTGpuProgramParameters_nglProgramLocalParameter4fvEXT(JNIEnv *env, jclass clazz, jint target, jint index, jint count, jobject params, jint params_position, jlong function_pointer) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glProgramLocalParameter4fvEXTPROC glProgramLocalParameter4fvEXT = (glProgramLocalParameter4fvEXTPROC)((intptr_t)function_pointer);
	glProgramLocalParameter4fvEXT(target, index, count, params_address);
}

