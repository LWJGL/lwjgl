/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glPointParameterfvARBPROC) (GLenum pname, GLfloat * pfParams);
typedef void (APIENTRY *glPointParameterfARBPROC) (GLenum pname, GLfloat param);

static glPointParameterfvARBPROC glPointParameterfvARB;
static glPointParameterfARBPROC glPointParameterfARB;

static void JNICALL Java_org_lwjgl_opengl_ARBPointParameters_nglPointParameterfvARB(JNIEnv *env, jclass clazz, jint pname, jobject pfParams, jint pfParams_position) {
	GLfloat *pfParams_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, pfParams)) + pfParams_position;
	glPointParameterfvARB(pname, pfParams_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBPointParameters_glPointParameterfARB(JNIEnv *env, jclass clazz, jint pname, jfloat param) {
	glPointParameterfARB(pname, param);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBPointParameters_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglPointParameterfvARB", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBPointParameters_nglPointParameterfvARB, "glPointParameterfvARB", (void *)&glPointParameterfvARB},
		{"glPointParameterfARB", "(IF)V", (void *)&Java_org_lwjgl_opengl_ARBPointParameters_glPointParameterfARB, "glPointParameterfARB", (void *)&glPointParameterfARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
