/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glMultTransposeMatrixfARBPROC) (GLfloat * pfMtx);
typedef void (APIENTRY *glLoadTransposeMatrixfARBPROC) (GLfloat * pfMtx);

static glMultTransposeMatrixfARBPROC glMultTransposeMatrixfARB;
static glLoadTransposeMatrixfARBPROC glLoadTransposeMatrixfARB;

static void JNICALL Java_org_lwjgl_opengl_ARBTransposeMatrix_nglMultTransposeMatrixfARB(JNIEnv *env, jclass clazz, jobject pfMtx, jint pfMtx_position) {
	GLfloat *pfMtx_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, pfMtx)) + pfMtx_position;
	glMultTransposeMatrixfARB(pfMtx_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBTransposeMatrix_nglLoadTransposeMatrixfARB(JNIEnv *env, jclass clazz, jobject pfMtx, jint pfMtx_position) {
	GLfloat *pfMtx_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, pfMtx)) + pfMtx_position;
	glLoadTransposeMatrixfARB(pfMtx_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBTransposeMatrix_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglMultTransposeMatrixfARB", "(Ljava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBTransposeMatrix_nglMultTransposeMatrixfARB, "glMultTransposeMatrixfARB", (void *)&glMultTransposeMatrixfARB},
		{"nglLoadTransposeMatrixfARB", "(Ljava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBTransposeMatrix_nglLoadTransposeMatrixfARB, "glLoadTransposeMatrixfARB", (void *)&glLoadTransposeMatrixfARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
