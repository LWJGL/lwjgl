/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glVertexWeightPointerEXTPROC) (GLsizei size, GLenum type, GLsizei stride, const GLvoid * pPointer);
typedef void (APIENTRY *glVertexWeightfEXTPROC) (GLfloat weight);

static glVertexWeightPointerEXTPROC glVertexWeightPointerEXT;
static glVertexWeightfEXTPROC glVertexWeightfEXT;

static void JNICALL Java_org_lwjgl_opengl_EXTVertexWeighting_nglVertexWeightPointerEXT(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jobject pPointer, jint pPointer_position) {
	const GLvoid *pPointer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pPointer)) + pPointer_position));
	glVertexWeightPointerEXT(size, type, stride, pPointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexWeighting_nglVertexWeightPointerEXTBO(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jint pPointer_buffer_offset) {
	const GLvoid *pPointer_address = ((const GLvoid *)offsetToPointer(pPointer_buffer_offset));
	glVertexWeightPointerEXT(size, type, stride, pPointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexWeighting_glVertexWeightfEXT(JNIEnv *env, jclass clazz, jfloat weight) {
	glVertexWeightfEXT(weight);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexWeighting_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglVertexWeightPointerEXT", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexWeighting_nglVertexWeightPointerEXT, "glVertexWeightPointerEXT", (void *)&glVertexWeightPointerEXT},
		{"nglVertexWeightPointerEXTBO", "(IIII)V", (void *)&Java_org_lwjgl_opengl_EXTVertexWeighting_nglVertexWeightPointerEXTBO, "glVertexWeightPointerEXT", (void *)&glVertexWeightPointerEXT},
		{"glVertexWeightfEXT", "(F)V", (void *)&Java_org_lwjgl_opengl_EXTVertexWeighting_glVertexWeightfEXT, "glVertexWeightfEXT", (void *)&glVertexWeightfEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
