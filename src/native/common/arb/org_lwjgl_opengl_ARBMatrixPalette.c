/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glMatrixIndexuivARBPROC) (GLint size, GLuint * pIndices);
typedef void (APIENTRY *glMatrixIndexusvARBPROC) (GLint size, GLushort * pIndices);
typedef void (APIENTRY *glMatrixIndexubvARBPROC) (GLint size, GLubyte * pIndices);
typedef void (APIENTRY *glMatrixIndexPointerARBPROC) (GLint size, GLenum type, GLsizei stride, GLvoid * pPointer);
typedef void (APIENTRY *glCurrentPaletteMatrixARBPROC) (GLint index);

static glMatrixIndexuivARBPROC glMatrixIndexuivARB;
static glMatrixIndexusvARBPROC glMatrixIndexusvARB;
static glMatrixIndexubvARBPROC glMatrixIndexubvARB;
static glMatrixIndexPointerARBPROC glMatrixIndexPointerARB;
static glCurrentPaletteMatrixARBPROC glCurrentPaletteMatrixARB;

static void JNICALL Java_org_lwjgl_opengl_ARBMatrixPalette_nglMatrixIndexuivARB(JNIEnv *env, jclass clazz, jint size, jobject pIndices, jint pIndices_position) {
	GLuint *pIndices_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, pIndices)) + pIndices_position;
	glMatrixIndexuivARB(size, pIndices_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMatrixPalette_nglMatrixIndexusvARB(JNIEnv *env, jclass clazz, jint size, jobject pIndices, jint pIndices_position) {
	GLushort *pIndices_address = ((GLushort *)(*env)->GetDirectBufferAddress(env, pIndices)) + pIndices_position;
	glMatrixIndexusvARB(size, pIndices_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMatrixPalette_nglMatrixIndexubvARB(JNIEnv *env, jclass clazz, jint size, jobject pIndices, jint pIndices_position) {
	GLubyte *pIndices_address = ((GLubyte *)(*env)->GetDirectBufferAddress(env, pIndices)) + pIndices_position;
	glMatrixIndexubvARB(size, pIndices_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMatrixPalette_nglMatrixIndexPointerARB(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jobject pPointer, jint pPointer_position) {
	GLvoid *pPointer_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pPointer)) + pPointer_position));
	glMatrixIndexPointerARB(size, type, stride, pPointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMatrixPalette_nglMatrixIndexPointerARBBO(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jint pPointer_buffer_offset) {
	GLvoid *pPointer_address = ((GLvoid *)offsetToPointer(pPointer_buffer_offset));
	glMatrixIndexPointerARB(size, type, stride, pPointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBMatrixPalette_glCurrentPaletteMatrixARB(JNIEnv *env, jclass clazz, jint index) {
	glCurrentPaletteMatrixARB(index);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBMatrixPalette_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglMatrixIndexuivARB", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBMatrixPalette_nglMatrixIndexuivARB, "glMatrixIndexuivARB", (void *)&glMatrixIndexuivARB},
		{"nglMatrixIndexusvARB", "(ILjava/nio/ShortBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBMatrixPalette_nglMatrixIndexusvARB, "glMatrixIndexusvARB", (void *)&glMatrixIndexusvARB},
		{"nglMatrixIndexubvARB", "(ILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBMatrixPalette_nglMatrixIndexubvARB, "glMatrixIndexubvARB", (void *)&glMatrixIndexubvARB},
		{"nglMatrixIndexPointerARB", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBMatrixPalette_nglMatrixIndexPointerARB, "glMatrixIndexPointerARB", (void *)&glMatrixIndexPointerARB},
		{"nglMatrixIndexPointerARBBO", "(IIII)V", (void *)&Java_org_lwjgl_opengl_ARBMatrixPalette_nglMatrixIndexPointerARBBO, "glMatrixIndexPointerARB", (void *)&glMatrixIndexPointerARB},
		{"glCurrentPaletteMatrixARB", "(I)V", (void *)&Java_org_lwjgl_opengl_ARBMatrixPalette_glCurrentPaletteMatrixARB, "glCurrentPaletteMatrixARB", (void *)&glCurrentPaletteMatrixARB}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
