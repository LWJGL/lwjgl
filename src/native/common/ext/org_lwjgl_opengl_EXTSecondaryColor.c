/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glSecondaryColorPointerEXTPROC) (GLint size, GLenum type, GLsizei stride, GLvoid * pPointer);
typedef void (APIENTRY *glSecondaryColor3ubEXTPROC) (GLubyte red, GLubyte green, GLubyte blue);
typedef void (APIENTRY *glSecondaryColor3fEXTPROC) (GLfloat red, GLfloat green, GLfloat blue);
typedef void (APIENTRY *glSecondaryColor3bEXTPROC) (GLbyte red, GLbyte green, GLbyte blue);

static glSecondaryColorPointerEXTPROC glSecondaryColorPointerEXT;
static glSecondaryColor3ubEXTPROC glSecondaryColor3ubEXT;
static glSecondaryColor3fEXTPROC glSecondaryColor3fEXT;
static glSecondaryColor3bEXTPROC glSecondaryColor3bEXT;

static void JNICALL Java_org_lwjgl_opengl_EXTSecondaryColor_nglSecondaryColorPointerEXT(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jobject pPointer, jint pPointer_position) {
	GLvoid *pPointer_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pPointer)) + pPointer_position));
	glSecondaryColorPointerEXT(size, type, stride, pPointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTSecondaryColor_nglSecondaryColorPointerEXTBO(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jint pPointer_buffer_offset) {
	GLvoid *pPointer_address = ((GLvoid *)offsetToPointer(pPointer_buffer_offset));
	glSecondaryColorPointerEXT(size, type, stride, pPointer_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTSecondaryColor_glSecondaryColor3ubEXT(JNIEnv *env, jclass clazz, jbyte red, jbyte green, jbyte blue) {
	glSecondaryColor3ubEXT(red, green, blue);
}

static void JNICALL Java_org_lwjgl_opengl_EXTSecondaryColor_glSecondaryColor3fEXT(JNIEnv *env, jclass clazz, jfloat red, jfloat green, jfloat blue) {
	glSecondaryColor3fEXT(red, green, blue);
}

static void JNICALL Java_org_lwjgl_opengl_EXTSecondaryColor_glSecondaryColor3bEXT(JNIEnv *env, jclass clazz, jbyte red, jbyte green, jbyte blue) {
	glSecondaryColor3bEXT(red, green, blue);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTSecondaryColor_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglSecondaryColorPointerEXT", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTSecondaryColor_nglSecondaryColorPointerEXT, "glSecondaryColorPointerEXT", (void *)&glSecondaryColorPointerEXT},
		{"nglSecondaryColorPointerEXTBO", "(IIII)V", (void *)&Java_org_lwjgl_opengl_EXTSecondaryColor_nglSecondaryColorPointerEXTBO, "glSecondaryColorPointerEXT", (void *)&glSecondaryColorPointerEXT},
		{"glSecondaryColor3ubEXT", "(BBB)V", (void *)&Java_org_lwjgl_opengl_EXTSecondaryColor_glSecondaryColor3ubEXT, "glSecondaryColor3ubEXT", (void *)&glSecondaryColor3ubEXT},
		{"glSecondaryColor3fEXT", "(FFF)V", (void *)&Java_org_lwjgl_opengl_EXTSecondaryColor_glSecondaryColor3fEXT, "glSecondaryColor3fEXT", (void *)&glSecondaryColor3fEXT},
		{"glSecondaryColor3bEXT", "(BBB)V", (void *)&Java_org_lwjgl_opengl_EXTSecondaryColor_glSecondaryColor3bEXT, "glSecondaryColor3bEXT", (void *)&glSecondaryColor3bEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
