/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef GLint (APIENTRY *glGetAttribLocationARBPROC) (GLhandleARB programObj, const GLcharARB * name);
typedef void (APIENTRY *glGetActiveAttribARBPROC) (GLhandleARB programObj, GLuint index, GLsizei maxLength, GLsizei * length, GLint * size, GLenum * type, GLcharARB * name);
typedef void (APIENTRY *glBindAttribLocationARBPROC) (GLhandleARB programObj, GLuint index, const GLcharARB * name);

static glGetAttribLocationARBPROC glGetAttribLocationARB;
static glGetActiveAttribARBPROC glGetActiveAttribARB;
static glBindAttribLocationARBPROC glBindAttribLocationARB;

static jint JNICALL Java_org_lwjgl_opengl_ARBVertexShader_nglGetAttribLocationARB(JNIEnv *env, jclass clazz, jint programObj, jobject name, jint name_position) {
	const GLcharARB *name_address = ((const GLcharARB *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	GLint __result = glGetAttribLocationARB(programObj, name_address);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexShader_nglGetActiveAttribARB(JNIEnv *env, jclass clazz, jint programObj, jint index, jint maxLength, jobject length, jint length_position, jobject size, jint size_position, jobject type, jint type_position, jobject name, jint name_position) {
	GLsizei *length_address = ((GLsizei *)safeGetBufferAddress(env, length)) + length_position;
	GLint *size_address = ((GLint *)(*env)->GetDirectBufferAddress(env, size)) + size_position;
	GLenum *type_address = ((GLenum *)(*env)->GetDirectBufferAddress(env, type)) + type_position;
	GLcharARB *name_address = ((GLcharARB *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glGetActiveAttribARB(programObj, index, maxLength, length_address, size_address, type_address, name_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexShader_nglBindAttribLocationARB(JNIEnv *env, jclass clazz, jint programObj, jint index, jobject name, jint name_position) {
	const GLcharARB *name_address = ((const GLcharARB *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glBindAttribLocationARB(programObj, index, name_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBVertexShader_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetAttribLocationARB", "(ILjava/nio/ByteBuffer;I)I", (void *)&Java_org_lwjgl_opengl_ARBVertexShader_nglGetAttribLocationARB, "glGetAttribLocationARB", (void *)&glGetAttribLocationARB},
		{"nglGetActiveAttribARB", "(IIILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexShader_nglGetActiveAttribARB, "glGetActiveAttribARB", (void *)&glGetActiveAttribARB},
		{"nglBindAttribLocationARB", "(IILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexShader_nglBindAttribLocationARB, "glBindAttribLocationARB", (void *)&glBindAttribLocationARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
