/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glPointParameterivNVPROC) (GLenum pname, const GLint * params);
typedef void (APIENTRY *glPointParameteriNVPROC) (GLenum pname, GLint param);

static glPointParameterivNVPROC glPointParameterivNV;
static glPointParameteriNVPROC glPointParameteriNV;

static void JNICALL Java_org_lwjgl_opengl_NVPointSprite_nglPointParameterivNV(JNIEnv *env, jclass clazz, jint pname, jobject params, jint params_position) {
	const GLint *params_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glPointParameterivNV(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVPointSprite_glPointParameteriNV(JNIEnv *env, jclass clazz, jint pname, jint param) {
	glPointParameteriNV(pname, param);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVPointSprite_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglPointParameterivNV", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVPointSprite_nglPointParameterivNV, "glPointParameterivNV", (void *)&glPointParameterivNV},
		{"glPointParameteriNV", "(II)V", (void *)&Java_org_lwjgl_opengl_NVPointSprite_glPointParameteriNV, "glPointParameteriNV", (void *)&glPointParameteriNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
