/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetVertexAttribArrayObjectivATIPROC) (GLuint index, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetVertexAttribArrayObjectfvATIPROC) (GLuint index, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glVertexAttribArrayObjectATIPROC) (GLuint index, GLint size, GLenum type, GLboolean normalized, GLsizei stride, GLuint buffer, GLuint offset);

static glGetVertexAttribArrayObjectivATIPROC glGetVertexAttribArrayObjectivATI;
static glGetVertexAttribArrayObjectfvATIPROC glGetVertexAttribArrayObjectfvATI;
static glVertexAttribArrayObjectATIPROC glVertexAttribArrayObjectATI;

static void JNICALL Java_org_lwjgl_opengl_ATIVertexAttribArrayObject_nglGetVertexAttribArrayObjectivATI(JNIEnv *env, jclass clazz, jint index, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetVertexAttribArrayObjectivATI(index, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ATIVertexAttribArrayObject_nglGetVertexAttribArrayObjectfvATI(JNIEnv *env, jclass clazz, jint index, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetVertexAttribArrayObjectfvATI(index, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ATIVertexAttribArrayObject_glVertexAttribArrayObjectATI(JNIEnv *env, jclass clazz, jint index, jint size, jint type, jboolean normalized, jint stride, jint buffer, jint offset) {
	glVertexAttribArrayObjectATI(index, size, type, normalized, stride, buffer, offset);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIVertexAttribArrayObject_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetVertexAttribArrayObjectivATI", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ATIVertexAttribArrayObject_nglGetVertexAttribArrayObjectivATI, "glGetVertexAttribArrayObjectivATI", (void *)&glGetVertexAttribArrayObjectivATI},
		{"nglGetVertexAttribArrayObjectfvATI", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ATIVertexAttribArrayObject_nglGetVertexAttribArrayObjectfvATI, "glGetVertexAttribArrayObjectfvATI", (void *)&glGetVertexAttribArrayObjectfvATI},
		{"glVertexAttribArrayObjectATI", "(IIIZIII)V", (void *)&Java_org_lwjgl_opengl_ATIVertexAttribArrayObject_glVertexAttribArrayObjectATI, "glVertexAttribArrayObjectATI", (void *)&glVertexAttribArrayObjectATI}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
