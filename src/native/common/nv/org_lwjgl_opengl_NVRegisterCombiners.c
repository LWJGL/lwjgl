/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetFinalCombinerInputParameterivNVPROC) (GLenum variable, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetFinalCombinerInputParameterfvNVPROC) (GLenum variable, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetCombinerOutputParameterivNVPROC) (GLenum stage, GLenum portion, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetCombinerOutputParameterfvNVPROC) (GLenum stage, GLenum portion, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetCombinerInputParameterivNVPROC) (GLenum stage, GLenum portion, GLenum variable, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetCombinerInputParameterfvNVPROC) (GLenum stage, GLenum portion, GLenum variable, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glFinalCombinerInputNVPROC) (GLenum variable, GLenum input, GLenum mapping, GLenum componentUsage);
typedef void (APIENTRY *glCombinerOutputNVPROC) (GLenum stage, GLenum portion, GLenum abOutput, GLenum cdOutput, GLenum sumOutput, GLenum scale, GLenum bias, GLboolean abDotProduct, GLboolean cdDotProduct, GLboolean muxSum);
typedef void (APIENTRY *glCombinerInputNVPROC) (GLenum stage, GLenum portion, GLenum variable, GLenum input, GLenum mapping, GLenum componentUsage);
typedef void (APIENTRY *glCombinerParameterivNVPROC) (GLenum pname, const GLint * params);
typedef void (APIENTRY *glCombinerParameteriNVPROC) (GLenum pname, GLint param);
typedef void (APIENTRY *glCombinerParameterfvNVPROC) (GLenum pname, const GLfloat * params);
typedef void (APIENTRY *glCombinerParameterfNVPROC) (GLenum pname, GLfloat param);

static glGetFinalCombinerInputParameterivNVPROC glGetFinalCombinerInputParameterivNV;
static glGetFinalCombinerInputParameterfvNVPROC glGetFinalCombinerInputParameterfvNV;
static glGetCombinerOutputParameterivNVPROC glGetCombinerOutputParameterivNV;
static glGetCombinerOutputParameterfvNVPROC glGetCombinerOutputParameterfvNV;
static glGetCombinerInputParameterivNVPROC glGetCombinerInputParameterivNV;
static glGetCombinerInputParameterfvNVPROC glGetCombinerInputParameterfvNV;
static glFinalCombinerInputNVPROC glFinalCombinerInputNV;
static glCombinerOutputNVPROC glCombinerOutputNV;
static glCombinerInputNVPROC glCombinerInputNV;
static glCombinerParameterivNVPROC glCombinerParameterivNV;
static glCombinerParameteriNVPROC glCombinerParameteriNV;
static glCombinerParameterfvNVPROC glCombinerParameterfvNV;
static glCombinerParameterfNVPROC glCombinerParameterfNV;

static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetFinalCombinerInputParameterivNV(JNIEnv *env, jclass clazz, jint variable, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetFinalCombinerInputParameterivNV(variable, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetFinalCombinerInputParameterfvNV(JNIEnv *env, jclass clazz, jint variable, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetFinalCombinerInputParameterfvNV(variable, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerOutputParameterivNV(JNIEnv *env, jclass clazz, jint stage, jint portion, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetCombinerOutputParameterivNV(stage, portion, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerOutputParameterfvNV(JNIEnv *env, jclass clazz, jint stage, jint portion, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetCombinerOutputParameterfvNV(stage, portion, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerInputParameterivNV(JNIEnv *env, jclass clazz, jint stage, jint portion, jint variable, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetCombinerInputParameterivNV(stage, portion, variable, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerInputParameterfvNV(JNIEnv *env, jclass clazz, jint stage, jint portion, jint variable, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetCombinerInputParameterfvNV(stage, portion, variable, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_glFinalCombinerInputNV(JNIEnv *env, jclass clazz, jint variable, jint input, jint mapping, jint componentUsage) {
	glFinalCombinerInputNV(variable, input, mapping, componentUsage);
}

static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerOutputNV(JNIEnv *env, jclass clazz, jint stage, jint portion, jint abOutput, jint cdOutput, jint sumOutput, jint scale, jint bias, jboolean abDotProduct, jboolean cdDotProduct, jboolean muxSum) {
	glCombinerOutputNV(stage, portion, abOutput, cdOutput, sumOutput, scale, bias, abDotProduct, cdDotProduct, muxSum);
}

static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerInputNV(JNIEnv *env, jclass clazz, jint stage, jint portion, jint variable, jint input, jint mapping, jint componentUsage) {
	glCombinerInputNV(stage, portion, variable, input, mapping, componentUsage);
}

static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglCombinerParameterivNV(JNIEnv *env, jclass clazz, jint pname, jobject params, jint params_position) {
	const GLint *params_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glCombinerParameterivNV(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerParameteriNV(JNIEnv *env, jclass clazz, jint pname, jint param) {
	glCombinerParameteriNV(pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_nglCombinerParameterfvNV(JNIEnv *env, jclass clazz, jint pname, jobject params, jint params_position) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glCombinerParameterfvNV(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerParameterfNV(JNIEnv *env, jclass clazz, jint pname, jfloat param) {
	glCombinerParameterfNV(pname, param);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVRegisterCombiners_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetFinalCombinerInputParameterivNV", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetFinalCombinerInputParameterivNV, "glGetFinalCombinerInputParameterivNV", (void *)&glGetFinalCombinerInputParameterivNV},
		{"nglGetFinalCombinerInputParameterfvNV", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetFinalCombinerInputParameterfvNV, "glGetFinalCombinerInputParameterfvNV", (void *)&glGetFinalCombinerInputParameterfvNV},
		{"nglGetCombinerOutputParameterivNV", "(IIILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerOutputParameterivNV, "glGetCombinerOutputParameterivNV", (void *)&glGetCombinerOutputParameterivNV},
		{"nglGetCombinerOutputParameterfvNV", "(IIILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerOutputParameterfvNV, "glGetCombinerOutputParameterfvNV", (void *)&glGetCombinerOutputParameterfvNV},
		{"nglGetCombinerInputParameterivNV", "(IIIILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerInputParameterivNV, "glGetCombinerInputParameterivNV", (void *)&glGetCombinerInputParameterivNV},
		{"nglGetCombinerInputParameterfvNV", "(IIIILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglGetCombinerInputParameterfvNV, "glGetCombinerInputParameterfvNV", (void *)&glGetCombinerInputParameterfvNV},
		{"glFinalCombinerInputNV", "(IIII)V", (void *)&Java_org_lwjgl_opengl_NVRegisterCombiners_glFinalCombinerInputNV, "glFinalCombinerInputNV", (void *)&glFinalCombinerInputNV},
		{"glCombinerOutputNV", "(IIIIIIIZZZ)V", (void *)&Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerOutputNV, "glCombinerOutputNV", (void *)&glCombinerOutputNV},
		{"glCombinerInputNV", "(IIIIII)V", (void *)&Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerInputNV, "glCombinerInputNV", (void *)&glCombinerInputNV},
		{"nglCombinerParameterivNV", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglCombinerParameterivNV, "glCombinerParameterivNV", (void *)&glCombinerParameterivNV},
		{"glCombinerParameteriNV", "(II)V", (void *)&Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerParameteriNV, "glCombinerParameteriNV", (void *)&glCombinerParameteriNV},
		{"nglCombinerParameterfvNV", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVRegisterCombiners_nglCombinerParameterfvNV, "glCombinerParameterfvNV", (void *)&glCombinerParameterfvNV},
		{"glCombinerParameterfNV", "(IF)V", (void *)&Java_org_lwjgl_opengl_NVRegisterCombiners_glCombinerParameterfNV, "glCombinerParameterfNV", (void *)&glCombinerParameterfNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
