/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glPointParameterfvEXTPROC) (GLenum pname, const GLfloat * pfParams);
typedef void (APIENTRY *glPointParameterfEXTPROC) (GLenum pname, GLfloat param);

static glPointParameterfvEXTPROC glPointParameterfvEXT;
static glPointParameterfEXTPROC glPointParameterfEXT;

static void JNICALL Java_org_lwjgl_opengl_EXTPointParameters_nglPointParameterfvEXT(JNIEnv *env, jclass clazz, jint pname, jobject pfParams, jint pfParams_position) {
	const GLfloat *pfParams_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, pfParams)) + pfParams_position;
	glPointParameterfvEXT(pname, pfParams_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTPointParameters_glPointParameterfEXT(JNIEnv *env, jclass clazz, jint pname, jfloat param) {
	glPointParameterfEXT(pname, param);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTPointParameters_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglPointParameterfvEXT", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTPointParameters_nglPointParameterfvEXT, "glPointParameterfvEXT", (void *)&glPointParameterfvEXT},
		{"glPointParameterfEXT", "(IF)V", (void *)&Java_org_lwjgl_opengl_EXTPointParameters_glPointParameterfEXT, "glPointParameterfEXT", (void *)&glPointParameterfEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
