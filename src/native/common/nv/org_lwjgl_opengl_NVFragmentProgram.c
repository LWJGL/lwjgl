/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetProgramNamedParameterfvNVPROC) (GLuint id, GLsizei length, const GLubyte * name, GLfloat * params);
typedef void (APIENTRY *glProgramNamedParameter4fNVPROC) (GLuint id, GLsizei length, const GLubyte * name, GLfloat x, GLfloat y, GLfloat z, GLfloat w);

static glGetProgramNamedParameterfvNVPROC glGetProgramNamedParameterfvNV;
static glProgramNamedParameter4fNVPROC glProgramNamedParameter4fNV;

static void JNICALL Java_org_lwjgl_opengl_NVFragmentProgram_nglGetProgramNamedParameterfvNV(JNIEnv *env, jclass clazz, jint id, jint length, jobject name, jint name_position, jobject params, jint params_position) {
	const GLubyte *name_address = ((const GLubyte *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetProgramNamedParameterfvNV(id, length, name_address, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVFragmentProgram_nglProgramNamedParameter4fNV(JNIEnv *env, jclass clazz, jint id, jint length, jobject name, jint name_position, jfloat x, jfloat y, jfloat z, jfloat w) {
	const GLubyte *name_address = ((const GLubyte *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glProgramNamedParameter4fNV(id, length, name_address, x, y, z, w);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVFragmentProgram_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetProgramNamedParameterfvNV", "(IILjava/nio/ByteBuffer;ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVFragmentProgram_nglGetProgramNamedParameterfvNV, "glGetProgramNamedParameterfvNV", (void *)&glGetProgramNamedParameterfvNV},
		{"nglProgramNamedParameter4fNV", "(IILjava/nio/ByteBuffer;IFFFF)V", (void *)&Java_org_lwjgl_opengl_NVFragmentProgram_nglProgramNamedParameter4fNV, "glProgramNamedParameter4fNV", (void *)&glProgramNamedParameter4fNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
