/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glFreeMemoryNVPROC) (GLvoid * pointer);
typedef GLvoid * (APIENTRY *glAllocateMemoryNVPROC) (GLint size, GLfloat readFrequency, GLfloat writeFrequency, GLfloat priority);
typedef void (APIENTRY *glFlushVertexArrayRangeNVPROC) ();
typedef void (APIENTRY *glVertexArrayRangeNVPROC) (GLsizei size, const GLvoid * pPointer);

static glFreeMemoryNVPROC glFreeMemoryNV;
static glAllocateMemoryNVPROC glAllocateMemoryNV;
static glFlushVertexArrayRangeNVPROC glFlushVertexArrayRangeNV;
static glVertexArrayRangeNVPROC glVertexArrayRangeNV;

static void JNICALL Java_org_lwjgl_opengl_NVVertexArrayRange_nglFreeMemoryNV(JNIEnv *env, jclass clazz, jobject pointer, jint pointer_position) {
	GLvoid *pointer_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pointer)) + pointer_position));
	glFreeMemoryNV(pointer_address);
}

static jobject JNICALL Java_org_lwjgl_opengl_NVVertexArrayRange_glAllocateMemoryNV(JNIEnv *env, jclass clazz, jint size, jfloat readFrequency, jfloat writeFrequency, jfloat priority, jint result_size) {
	GLvoid * __result = glAllocateMemoryNV(size, readFrequency, writeFrequency, priority);
	return safeNewBuffer(env, __result, result_size);
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexArrayRange_glFlushVertexArrayRangeNV(JNIEnv *env, jclass clazz) {
	glFlushVertexArrayRangeNV();
}

static void JNICALL Java_org_lwjgl_opengl_NVVertexArrayRange_nglVertexArrayRangeNV(JNIEnv *env, jclass clazz, jint size, jobject pPointer, jint pPointer_position) {
	const GLvoid *pPointer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pPointer)) + pPointer_position));
	glVertexArrayRangeNV(size, pPointer_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVVertexArrayRange_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
#ifdef _WIN32
		{"nglFreeMemoryNV", "(Ljava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexArrayRange_nglFreeMemoryNV, "wglFreeMemoryNV", (void *)&glFreeMemoryNV},
#endif
#ifdef _X11
		{"nglFreeMemoryNV", "(Ljava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexArrayRange_nglFreeMemoryNV, "glXFreeMemoryNV", (void *)&glFreeMemoryNV},
#endif
#ifdef _WIN32
		{"glAllocateMemoryNV", "(IFFFI)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengl_NVVertexArrayRange_glAllocateMemoryNV, "wglAllocateMemoryNV", (void *)&glAllocateMemoryNV},
#endif
#ifdef _X11
		{"glAllocateMemoryNV", "(IFFFI)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengl_NVVertexArrayRange_glAllocateMemoryNV, "glXAllocateMemoryNV", (void *)&glAllocateMemoryNV},
#endif
		{"glFlushVertexArrayRangeNV", "()V", (void *)&Java_org_lwjgl_opengl_NVVertexArrayRange_glFlushVertexArrayRangeNV, "glFlushVertexArrayRangeNV", (void *)&glFlushVertexArrayRangeNV},
		{"nglVertexArrayRangeNV", "(ILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_NVVertexArrayRange_nglVertexArrayRangeNV, "glVertexArrayRangeNV", (void *)&glVertexArrayRangeNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
