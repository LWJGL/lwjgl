/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetProgramNamedParameterfvNVPROC) (GLuint id, GLsizei length, const GLubyte * name, GLfloat * params);
typedef void (APIENTRY *glProgramNamedParameter4fNVPROC) (GLuint id, GLsizei length, const GLubyte * name, GLfloat x, GLfloat y, GLfloat z, GLfloat w);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVFragmentProgram_nglGetProgramNamedParameterfvNV(JNIEnv *env, jclass clazz, jint id, jint length, jobject name, jint name_position, jobject params, jint params_position, jlong function_pointer) {
	const GLubyte *name_address = ((const GLubyte *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetProgramNamedParameterfvNVPROC glGetProgramNamedParameterfvNV = (glGetProgramNamedParameterfvNVPROC)((intptr_t)function_pointer);
	glGetProgramNamedParameterfvNV(id, length, name_address, params_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVFragmentProgram_nglProgramNamedParameter4fNV(JNIEnv *env, jclass clazz, jint id, jint length, jobject name, jint name_position, jfloat x, jfloat y, jfloat z, jfloat w, jlong function_pointer) {
	const GLubyte *name_address = ((const GLubyte *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glProgramNamedParameter4fNVPROC glProgramNamedParameter4fNV = (glProgramNamedParameter4fNVPROC)((intptr_t)function_pointer);
	glProgramNamedParameter4fNV(id, length, name_address, x, y, z, w);
}

