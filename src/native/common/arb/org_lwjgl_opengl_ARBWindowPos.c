/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glWindowPos3sARBPROC) (GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY *glWindowPos3iARBPROC) (GLint x, GLint y, GLint z);
typedef void (APIENTRY *glWindowPos3fARBPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY *glWindowPos2sARBPROC) (GLshort x, GLshort y);
typedef void (APIENTRY *glWindowPos2iARBPROC) (GLint x, GLint y);
typedef void (APIENTRY *glWindowPos2fARBPROC) (GLfloat x, GLfloat y);

static glWindowPos3sARBPROC glWindowPos3sARB;
static glWindowPos3iARBPROC glWindowPos3iARB;
static glWindowPos3fARBPROC glWindowPos3fARB;
static glWindowPos2sARBPROC glWindowPos2sARB;
static glWindowPos2iARBPROC glWindowPos2iARB;
static glWindowPos2fARBPROC glWindowPos2fARB;

static void JNICALL Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos3sARB(JNIEnv *env, jclass clazz, jshort x, jshort y, jshort z) {
	glWindowPos3sARB(x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos3iARB(JNIEnv *env, jclass clazz, jint x, jint y, jint z) {
	glWindowPos3iARB(x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos3fARB(JNIEnv *env, jclass clazz, jfloat x, jfloat y, jfloat z) {
	glWindowPos3fARB(x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos2sARB(JNIEnv *env, jclass clazz, jshort x, jshort y) {
	glWindowPos2sARB(x, y);
}

static void JNICALL Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos2iARB(JNIEnv *env, jclass clazz, jint x, jint y) {
	glWindowPos2iARB(x, y);
}

static void JNICALL Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos2fARB(JNIEnv *env, jclass clazz, jfloat x, jfloat y) {
	glWindowPos2fARB(x, y);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBWindowPos_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glWindowPos3sARB", "(SSS)V", (void *)&Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos3sARB, "glWindowPos3sARB", (void *)&glWindowPos3sARB},
		{"glWindowPos3iARB", "(III)V", (void *)&Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos3iARB, "glWindowPos3iARB", (void *)&glWindowPos3iARB},
		{"glWindowPos3fARB", "(FFF)V", (void *)&Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos3fARB, "glWindowPos3fARB", (void *)&glWindowPos3fARB},
		{"glWindowPos2sARB", "(SS)V", (void *)&Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos2sARB, "glWindowPos2sARB", (void *)&glWindowPos2sARB},
		{"glWindowPos2iARB", "(II)V", (void *)&Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos2iARB, "glWindowPos2iARB", (void *)&glWindowPos2iARB},
		{"glWindowPos2fARB", "(FF)V", (void *)&Java_org_lwjgl_opengl_ARBWindowPos_glWindowPos2fARB, "glWindowPos2fARB", (void *)&glWindowPos2fARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
