/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glFogCoordPointerEXTPROC) (GLenum type, GLsizei stride, const GLvoid * data);
typedef void (APIENTRY *glFogCoordfEXTPROC) (GLfloat coord);

static glFogCoordPointerEXTPROC glFogCoordPointerEXT;
static glFogCoordfEXTPROC glFogCoordfEXT;

static void JNICALL Java_org_lwjgl_opengl_EXTFogCoord_nglFogCoordPointerEXT(JNIEnv *env, jclass clazz, jint type, jint stride, jobject data, jint data_position) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glFogCoordPointerEXT(type, stride, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTFogCoord_nglFogCoordPointerEXTBO(JNIEnv *env, jclass clazz, jint type, jint stride, jint data_buffer_offset) {
	const GLvoid *data_address = ((const GLvoid *)offsetToPointer(data_buffer_offset));
	glFogCoordPointerEXT(type, stride, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTFogCoord_glFogCoordfEXT(JNIEnv *env, jclass clazz, jfloat coord) {
	glFogCoordfEXT(coord);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTFogCoord_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglFogCoordPointerEXT", "(IILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTFogCoord_nglFogCoordPointerEXT, "glFogCoordPointerEXT", (void *)&glFogCoordPointerEXT},
		{"nglFogCoordPointerEXTBO", "(III)V", (void *)&Java_org_lwjgl_opengl_EXTFogCoord_nglFogCoordPointerEXTBO, "glFogCoordPointerEXT", (void *)&glFogCoordPointerEXT},
		{"glFogCoordfEXT", "(F)V", (void *)&Java_org_lwjgl_opengl_EXTFogCoord_glFogCoordfEXT, "glFogCoordfEXT", (void *)&glFogCoordfEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
