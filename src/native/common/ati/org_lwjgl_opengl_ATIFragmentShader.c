/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glSetFragmentShaderConstantATIPROC) (GLuint dst, const GLfloat * pfValue);
typedef void (APIENTRY *glAlphaFragmentOp3ATIPROC) (GLenum op, GLuint dst, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod, GLuint arg2, GLuint arg2Rep, GLuint arg2Mod, GLuint arg3, GLuint arg3Rep, GLuint arg3Mod);
typedef void (APIENTRY *glAlphaFragmentOp2ATIPROC) (GLenum op, GLuint dst, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod, GLuint arg2, GLuint arg2Rep, GLuint arg2Mod);
typedef void (APIENTRY *glAlphaFragmentOp1ATIPROC) (GLenum op, GLuint dst, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod);
typedef void (APIENTRY *glColorFragmentOp3ATIPROC) (GLenum op, GLuint dst, GLuint dstMask, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod, GLuint arg2, GLuint arg2Rep, GLuint arg2Mod, GLuint arg3, GLuint arg3Rep, GLuint arg3Mod);
typedef void (APIENTRY *glColorFragmentOp2ATIPROC) (GLenum op, GLuint dst, GLuint dstMask, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod, GLuint arg2, GLuint arg2Rep, GLuint arg2Mod);
typedef void (APIENTRY *glColorFragmentOp1ATIPROC) (GLenum op, GLuint dst, GLuint dstMask, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod);
typedef void (APIENTRY *glSampleMapATIPROC) (GLuint dst, GLuint interp, GLenum swizzle);
typedef void (APIENTRY *glPassTexCoordATIPROC) (GLuint dst, GLuint coord, GLenum swizzle);
typedef void (APIENTRY *glEndFragmentShaderATIPROC) ();
typedef void (APIENTRY *glBeginFragmentShaderATIPROC) ();
typedef void (APIENTRY *glDeleteFragmentShaderATIPROC) (GLuint id);
typedef void (APIENTRY *glBindFragmentShaderATIPROC) (GLuint id);
typedef GLuint (APIENTRY *glGenFragmentShadersATIPROC) (GLuint range);

static glSetFragmentShaderConstantATIPROC glSetFragmentShaderConstantATI;
static glAlphaFragmentOp3ATIPROC glAlphaFragmentOp3ATI;
static glAlphaFragmentOp2ATIPROC glAlphaFragmentOp2ATI;
static glAlphaFragmentOp1ATIPROC glAlphaFragmentOp1ATI;
static glColorFragmentOp3ATIPROC glColorFragmentOp3ATI;
static glColorFragmentOp2ATIPROC glColorFragmentOp2ATI;
static glColorFragmentOp1ATIPROC glColorFragmentOp1ATI;
static glSampleMapATIPROC glSampleMapATI;
static glPassTexCoordATIPROC glPassTexCoordATI;
static glEndFragmentShaderATIPROC glEndFragmentShaderATI;
static glBeginFragmentShaderATIPROC glBeginFragmentShaderATI;
static glDeleteFragmentShaderATIPROC glDeleteFragmentShaderATI;
static glBindFragmentShaderATIPROC glBindFragmentShaderATI;
static glGenFragmentShadersATIPROC glGenFragmentShadersATI;

static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_nglSetFragmentShaderConstantATI(JNIEnv *env, jclass clazz, jint dst, jobject pfValue, jint pfValue_position) {
	const GLfloat *pfValue_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, pfValue)) + pfValue_position;
	glSetFragmentShaderConstantATI(dst, pfValue_address);
}

static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glAlphaFragmentOp3ATI(JNIEnv *env, jclass clazz, jint op, jint dst, jint dstMod, jint arg1, jint arg1Rep, jint arg1Mod, jint arg2, jint arg2Rep, jint arg2Mod, jint arg3, jint arg3Rep, jint arg3Mod) {
	glAlphaFragmentOp3ATI(op, dst, dstMod, arg1, arg1Rep, arg1Mod, arg2, arg2Rep, arg2Mod, arg3, arg3Rep, arg3Mod);
}

static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glAlphaFragmentOp2ATI(JNIEnv *env, jclass clazz, jint op, jint dst, jint dstMod, jint arg1, jint arg1Rep, jint arg1Mod, jint arg2, jint arg2Rep, jint arg2Mod) {
	glAlphaFragmentOp2ATI(op, dst, dstMod, arg1, arg1Rep, arg1Mod, arg2, arg2Rep, arg2Mod);
}

static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glAlphaFragmentOp1ATI(JNIEnv *env, jclass clazz, jint op, jint dst, jint dstMod, jint arg1, jint arg1Rep, jint arg1Mod) {
	glAlphaFragmentOp1ATI(op, dst, dstMod, arg1, arg1Rep, arg1Mod);
}

static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glColorFragmentOp3ATI(JNIEnv *env, jclass clazz, jint op, jint dst, jint dstMask, jint dstMod, jint arg1, jint arg1Rep, jint arg1Mod, jint arg2, jint arg2Rep, jint arg2Mod, jint arg3, jint arg3Rep, jint arg3Mod) {
	glColorFragmentOp3ATI(op, dst, dstMask, dstMod, arg1, arg1Rep, arg1Mod, arg2, arg2Rep, arg2Mod, arg3, arg3Rep, arg3Mod);
}

static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glColorFragmentOp2ATI(JNIEnv *env, jclass clazz, jint op, jint dst, jint dstMask, jint dstMod, jint arg1, jint arg1Rep, jint arg1Mod, jint arg2, jint arg2Rep, jint arg2Mod) {
	glColorFragmentOp2ATI(op, dst, dstMask, dstMod, arg1, arg1Rep, arg1Mod, arg2, arg2Rep, arg2Mod);
}

static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glColorFragmentOp1ATI(JNIEnv *env, jclass clazz, jint op, jint dst, jint dstMask, jint dstMod, jint arg1, jint arg1Rep, jint arg1Mod) {
	glColorFragmentOp1ATI(op, dst, dstMask, dstMod, arg1, arg1Rep, arg1Mod);
}

static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glSampleMapATI(JNIEnv *env, jclass clazz, jint dst, jint interp, jint swizzle) {
	glSampleMapATI(dst, interp, swizzle);
}

static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glPassTexCoordATI(JNIEnv *env, jclass clazz, jint dst, jint coord, jint swizzle) {
	glPassTexCoordATI(dst, coord, swizzle);
}

static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glEndFragmentShaderATI(JNIEnv *env, jclass clazz) {
	glEndFragmentShaderATI();
}

static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glBeginFragmentShaderATI(JNIEnv *env, jclass clazz) {
	glBeginFragmentShaderATI();
}

static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glDeleteFragmentShaderATI(JNIEnv *env, jclass clazz, jint id) {
	glDeleteFragmentShaderATI(id);
}

static void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glBindFragmentShaderATI(JNIEnv *env, jclass clazz, jint id) {
	glBindFragmentShaderATI(id);
}

static jint JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_glGenFragmentShadersATI(JNIEnv *env, jclass clazz, jint range) {
	GLuint __result = glGenFragmentShadersATI(range);
	return __result;
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIFragmentShader_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglSetFragmentShaderConstantATI", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ATIFragmentShader_nglSetFragmentShaderConstantATI, "glSetFragmentShaderConstantATI", (void *)&glSetFragmentShaderConstantATI},
		{"glAlphaFragmentOp3ATI", "(IIIIIIIIIIII)V", (void *)&Java_org_lwjgl_opengl_ATIFragmentShader_glAlphaFragmentOp3ATI, "glAlphaFragmentOp3ATI", (void *)&glAlphaFragmentOp3ATI},
		{"glAlphaFragmentOp2ATI", "(IIIIIIIII)V", (void *)&Java_org_lwjgl_opengl_ATIFragmentShader_glAlphaFragmentOp2ATI, "glAlphaFragmentOp2ATI", (void *)&glAlphaFragmentOp2ATI},
		{"glAlphaFragmentOp1ATI", "(IIIIII)V", (void *)&Java_org_lwjgl_opengl_ATIFragmentShader_glAlphaFragmentOp1ATI, "glAlphaFragmentOp1ATI", (void *)&glAlphaFragmentOp1ATI},
		{"glColorFragmentOp3ATI", "(IIIIIIIIIIIII)V", (void *)&Java_org_lwjgl_opengl_ATIFragmentShader_glColorFragmentOp3ATI, "glColorFragmentOp3ATI", (void *)&glColorFragmentOp3ATI},
		{"glColorFragmentOp2ATI", "(IIIIIIIIII)V", (void *)&Java_org_lwjgl_opengl_ATIFragmentShader_glColorFragmentOp2ATI, "glColorFragmentOp2ATI", (void *)&glColorFragmentOp2ATI},
		{"glColorFragmentOp1ATI", "(IIIIIII)V", (void *)&Java_org_lwjgl_opengl_ATIFragmentShader_glColorFragmentOp1ATI, "glColorFragmentOp1ATI", (void *)&glColorFragmentOp1ATI},
		{"glSampleMapATI", "(III)V", (void *)&Java_org_lwjgl_opengl_ATIFragmentShader_glSampleMapATI, "glSampleMapATI", (void *)&glSampleMapATI},
		{"glPassTexCoordATI", "(III)V", (void *)&Java_org_lwjgl_opengl_ATIFragmentShader_glPassTexCoordATI, "glPassTexCoordATI", (void *)&glPassTexCoordATI},
		{"glEndFragmentShaderATI", "()V", (void *)&Java_org_lwjgl_opengl_ATIFragmentShader_glEndFragmentShaderATI, "glEndFragmentShaderATI", (void *)&glEndFragmentShaderATI},
		{"glBeginFragmentShaderATI", "()V", (void *)&Java_org_lwjgl_opengl_ATIFragmentShader_glBeginFragmentShaderATI, "glBeginFragmentShaderATI", (void *)&glBeginFragmentShaderATI},
		{"glDeleteFragmentShaderATI", "(I)V", (void *)&Java_org_lwjgl_opengl_ATIFragmentShader_glDeleteFragmentShaderATI, "glDeleteFragmentShaderATI", (void *)&glDeleteFragmentShaderATI},
		{"glBindFragmentShaderATI", "(I)V", (void *)&Java_org_lwjgl_opengl_ATIFragmentShader_glBindFragmentShaderATI, "glBindFragmentShaderATI", (void *)&glBindFragmentShaderATI},
		{"glGenFragmentShadersATI", "(I)I", (void *)&Java_org_lwjgl_opengl_ATIFragmentShader_glGenFragmentShadersATI, "glGenFragmentShadersATI", (void *)&glGenFragmentShadersATI}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
