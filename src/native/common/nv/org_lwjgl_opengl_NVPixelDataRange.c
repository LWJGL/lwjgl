/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glFlushPixelDataRangeNVPROC) (GLenum target);
typedef void (APIENTRY *glPixelDataRangeNVPROC) (GLenum target, GLsizei length, GLvoid * data);

static glFlushPixelDataRangeNVPROC glFlushPixelDataRangeNV;
static glPixelDataRangeNVPROC glPixelDataRangeNV;

static void JNICALL Java_org_lwjgl_opengl_NVPixelDataRange_glFlushPixelDataRangeNV(JNIEnv *env, jclass clazz, jint target) {
	glFlushPixelDataRangeNV(target);
}

static void JNICALL Java_org_lwjgl_opengl_NVPixelDataRange_nglPixelDataRangeNV(JNIEnv *env, jclass clazz, jint target, jint length, jobject data, jint data_position) {
	GLvoid *data_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glPixelDataRangeNV(target, length, data_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVPixelDataRange_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glFlushPixelDataRangeNV", "(I)V", (void *)&Java_org_lwjgl_opengl_NVPixelDataRange_glFlushPixelDataRangeNV, "glFlushPixelDataRangeNV", (void *)&glFlushPixelDataRangeNV},
		{"nglPixelDataRangeNV", "(IILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_NVPixelDataRange_nglPixelDataRangeNV, "glPixelDataRangeNV", (void *)&glPixelDataRangeNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
