/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GLint (APIENTRY *glGetAttribLocationARBPROC) (GLhandleARB programObj, const GLcharARB * name);
typedef void (APIENTRY *glGetActiveAttribARBPROC) (GLhandleARB programObj, GLuint index, GLsizei maxLength, GLsizei * length, GLint * size, GLenum * type, GLcharARB * name);
typedef void (APIENTRY *glBindAttribLocationARBPROC) (GLhandleARB programObj, GLuint index, const GLcharARB * name);

JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_ARBVertexShader_nglGetAttribLocationARB(JNIEnv *env, jclass clazz, jint programObj, jobject name, jint name_position, jlong function_pointer) {
	const GLcharARB *name_address = ((const GLcharARB *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glGetAttribLocationARBPROC glGetAttribLocationARB = (glGetAttribLocationARBPROC)((intptr_t)function_pointer);
	GLint __result = glGetAttribLocationARB(programObj, name_address);
	return __result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBVertexShader_nglGetActiveAttribARB(JNIEnv *env, jclass clazz, jint programObj, jint index, jint maxLength, jobject length, jint length_position, jobject size, jint size_position, jobject type, jint type_position, jobject name, jint name_position, jlong function_pointer) {
	GLsizei *length_address = ((GLsizei *)safeGetBufferAddress(env, length)) + length_position;
	GLint *size_address = ((GLint *)(*env)->GetDirectBufferAddress(env, size)) + size_position;
	GLenum *type_address = ((GLenum *)(*env)->GetDirectBufferAddress(env, type)) + type_position;
	GLcharARB *name_address = ((GLcharARB *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glGetActiveAttribARBPROC glGetActiveAttribARB = (glGetActiveAttribARBPROC)((intptr_t)function_pointer);
	glGetActiveAttribARB(programObj, index, maxLength, length_address, size_address, type_address, name_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBVertexShader_nglBindAttribLocationARB(JNIEnv *env, jclass clazz, jint programObj, jint index, jobject name, jint name_position, jlong function_pointer) {
	const GLcharARB *name_address = ((const GLcharARB *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glBindAttribLocationARBPROC glBindAttribLocationARB = (glBindAttribLocationARBPROC)((intptr_t)function_pointer);
	glBindAttribLocationARB(programObj, index, name_address);
}

