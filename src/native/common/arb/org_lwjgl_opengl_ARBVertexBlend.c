/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glVertexBlendARBPROC) (GLint count);
typedef void (APIENTRY *glWeightPointerARBPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid * pPointer);
typedef void (APIENTRY *glWeightuivARBPROC) (GLint size, GLuint * pWeights);
typedef void (APIENTRY *glWeightusvARBPROC) (GLint size, GLushort * pWeights);
typedef void (APIENTRY *glWeightubvARBPROC) (GLint size, GLubyte * pWeights);
typedef void (APIENTRY *glWeightfvARBPROC) (GLint size, GLfloat * pWeights);
typedef void (APIENTRY *glWeightivARBPROC) (GLint size, GLint * pWeights);
typedef void (APIENTRY *glWeightsvARBPROC) (GLint size, GLshort * pWeights);
typedef void (APIENTRY *glWeightbvARBPROC) (GLint size, GLbyte * pWeights);

static glVertexBlendARBPROC glVertexBlendARB;
static glWeightPointerARBPROC glWeightPointerARB;
static glWeightuivARBPROC glWeightuivARB;
static glWeightusvARBPROC glWeightusvARB;
static glWeightubvARBPROC glWeightubvARB;
static glWeightfvARBPROC glWeightfvARB;
static glWeightivARBPROC glWeightivARB;
static glWeightsvARBPROC glWeightsvARB;
static glWeightbvARBPROC glWeightbvARB;

static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_glVertexBlendARB(JNIEnv *env, jclass clazz, jint count) {
	glVertexBlendARB(count);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightPointerARB(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jobject pPointer, jint pPointer_position) {
	const GLvoid *pPointer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pPointer)) + pPointer_position));
	glWeightPointerARB(size, type, stride, pPointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightPointerARBBO(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jint pPointer_buffer_offset) {
	const GLvoid *pPointer_address = ((const GLvoid *)offsetToPointer(pPointer_buffer_offset));
	glWeightPointerARB(size, type, stride, pPointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightuivARB(JNIEnv *env, jclass clazz, jint size, jobject pWeights, jint pWeights_position) {
	GLuint *pWeights_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, pWeights)) + pWeights_position;
	glWeightuivARB(size, pWeights_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightusvARB(JNIEnv *env, jclass clazz, jint size, jobject pWeights, jint pWeights_position) {
	GLushort *pWeights_address = ((GLushort *)(*env)->GetDirectBufferAddress(env, pWeights)) + pWeights_position;
	glWeightusvARB(size, pWeights_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightubvARB(JNIEnv *env, jclass clazz, jint size, jobject pWeights, jint pWeights_position) {
	GLubyte *pWeights_address = ((GLubyte *)(*env)->GetDirectBufferAddress(env, pWeights)) + pWeights_position;
	glWeightubvARB(size, pWeights_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightfvARB(JNIEnv *env, jclass clazz, jint size, jobject pWeights, jint pWeights_position) {
	GLfloat *pWeights_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, pWeights)) + pWeights_position;
	glWeightfvARB(size, pWeights_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightivARB(JNIEnv *env, jclass clazz, jint size, jobject pWeights, jint pWeights_position) {
	GLint *pWeights_address = ((GLint *)(*env)->GetDirectBufferAddress(env, pWeights)) + pWeights_position;
	glWeightivARB(size, pWeights_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightsvARB(JNIEnv *env, jclass clazz, jint size, jobject pWeights, jint pWeights_position) {
	GLshort *pWeights_address = ((GLshort *)(*env)->GetDirectBufferAddress(env, pWeights)) + pWeights_position;
	glWeightsvARB(size, pWeights_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightbvARB(JNIEnv *env, jclass clazz, jint size, jobject pWeights, jint pWeights_position) {
	GLbyte *pWeights_address = ((GLbyte *)(*env)->GetDirectBufferAddress(env, pWeights)) + pWeights_position;
	glWeightbvARB(size, pWeights_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBVertexBlend_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glVertexBlendARB", "(I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexBlend_glVertexBlendARB, "glVertexBlendARB", (void *)&glVertexBlendARB},
		{"nglWeightPointerARB", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightPointerARB, "glWeightPointerARB", (void *)&glWeightPointerARB},
		{"nglWeightPointerARBBO", "(IIII)V", (void *)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightPointerARBBO, "glWeightPointerARB", (void *)&glWeightPointerARB},
		{"nglWeightuivARB", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightuivARB, "glWeightuivARB", (void *)&glWeightuivARB},
		{"nglWeightusvARB", "(ILjava/nio/ShortBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightusvARB, "glWeightusvARB", (void *)&glWeightusvARB},
		{"nglWeightubvARB", "(ILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightubvARB, "glWeightubvARB", (void *)&glWeightubvARB},
		{"nglWeightfvARB", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightfvARB, "glWeightfvARB", (void *)&glWeightfvARB},
		{"nglWeightivARB", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightivARB, "glWeightivARB", (void *)&glWeightivARB},
		{"nglWeightsvARB", "(ILjava/nio/ShortBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightsvARB, "glWeightsvARB", (void *)&glWeightsvARB},
		{"nglWeightbvARB", "(ILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBVertexBlend_nglWeightbvARB, "glWeightbvARB", (void *)&glWeightbvARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
