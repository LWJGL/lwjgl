/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glWindowPos3iPROC) (GLint x, GLint y, GLint z);
typedef void (APIENTRY *glWindowPos3fPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY *glWindowPos2iPROC) (GLint x, GLint y);
typedef void (APIENTRY *glWindowPos2fPROC) (GLfloat x, GLfloat y);
typedef void (APIENTRY *glBlendFuncSeparatePROC) (GLenum sfactorRGB, GLenum dfactorRGB, GLenum sfactorAlpha, GLenum dfactorAlpha);
typedef void (APIENTRY *glSecondaryColorPointerPROC) (GLint size, GLenum type, GLsizei stride, const GLvoid * data);
typedef void (APIENTRY *glSecondaryColor3ubPROC) (GLubyte red, GLubyte green, GLubyte blue);
typedef void (APIENTRY *glSecondaryColor3fPROC) (GLfloat red, GLfloat green, GLfloat blue);
typedef void (APIENTRY *glSecondaryColor3bPROC) (GLbyte red, GLbyte green, GLbyte blue);
typedef void (APIENTRY *glPointParameterfvPROC) (GLenum pname, GLfloat * params);
typedef void (APIENTRY *glPointParameterivPROC) (GLenum pname, GLint * params);
typedef void (APIENTRY *glPointParameterfPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY *glPointParameteriPROC) (GLenum pname, GLint param);
typedef void (APIENTRY *glMultiDrawArraysPROC) (GLenum mode, GLint * piFirst, GLsizei * piCount, GLsizei primcount);
typedef void (APIENTRY *glFogCoordPointerPROC) (GLenum type, GLsizei stride, const GLvoid * data);
typedef void (APIENTRY *glFogCoordfPROC) (GLfloat coord);
typedef void (APIENTRY *glBlendColorPROC) (GLclampf red, GLclampf green, GLclampf blue, GLclampf alpha);
typedef void (APIENTRY *glBlendEquationPROC) (GLenum mode);

static glWindowPos3iPROC glWindowPos3i;
static glWindowPos3fPROC glWindowPos3f;
static glWindowPos2iPROC glWindowPos2i;
static glWindowPos2fPROC glWindowPos2f;
static glBlendFuncSeparatePROC glBlendFuncSeparate;
static glSecondaryColorPointerPROC glSecondaryColorPointer;
static glSecondaryColor3ubPROC glSecondaryColor3ub;
static glSecondaryColor3fPROC glSecondaryColor3f;
static glSecondaryColor3bPROC glSecondaryColor3b;
static glPointParameterfvPROC glPointParameterfv;
static glPointParameterivPROC glPointParameteriv;
static glPointParameterfPROC glPointParameterf;
static glPointParameteriPROC glPointParameteri;
static glMultiDrawArraysPROC glMultiDrawArrays;
static glFogCoordPointerPROC glFogCoordPointer;
static glFogCoordfPROC glFogCoordf;
static glBlendColorPROC glBlendColor;
static glBlendEquationPROC glBlendEquation;

static void JNICALL Java_org_lwjgl_opengl_GL14_glWindowPos3i(JNIEnv *env, jclass clazz, jint x, jint y, jint z) {
	glWindowPos3i(x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_glWindowPos3f(JNIEnv *env, jclass clazz, jfloat x, jfloat y, jfloat z) {
	glWindowPos3f(x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_glWindowPos2i(JNIEnv *env, jclass clazz, jint x, jint y) {
	glWindowPos2i(x, y);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_glWindowPos2f(JNIEnv *env, jclass clazz, jfloat x, jfloat y) {
	glWindowPos2f(x, y);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_glBlendFuncSeparate(JNIEnv *env, jclass clazz, jint sfactorRGB, jint dfactorRGB, jint sfactorAlpha, jint dfactorAlpha) {
	glBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_nglSecondaryColorPointer(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jobject data, jint data_position) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glSecondaryColorPointer(size, type, stride, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_nglSecondaryColorPointerBO(JNIEnv *env, jclass clazz, jint size, jint type, jint stride, jint data_buffer_offset) {
	const GLvoid *data_address = ((const GLvoid *)offsetToPointer(data_buffer_offset));
	glSecondaryColorPointer(size, type, stride, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_glSecondaryColor3ub(JNIEnv *env, jclass clazz, jbyte red, jbyte green, jbyte blue) {
	glSecondaryColor3ub(red, green, blue);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_glSecondaryColor3f(JNIEnv *env, jclass clazz, jfloat red, jfloat green, jfloat blue) {
	glSecondaryColor3f(red, green, blue);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_glSecondaryColor3b(JNIEnv *env, jclass clazz, jbyte red, jbyte green, jbyte blue) {
	glSecondaryColor3b(red, green, blue);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_nglPointParameterfv(JNIEnv *env, jclass clazz, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glPointParameterfv(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_nglPointParameteriv(JNIEnv *env, jclass clazz, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glPointParameteriv(pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_glPointParameterf(JNIEnv *env, jclass clazz, jint pname, jfloat param) {
	glPointParameterf(pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_glPointParameteri(JNIEnv *env, jclass clazz, jint pname, jint param) {
	glPointParameteri(pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_nglMultiDrawArrays(JNIEnv *env, jclass clazz, jint mode, jobject piFirst, jint piFirst_position, jobject piCount, jint piCount_position, jint primcount) {
	GLint *piFirst_address = ((GLint *)(*env)->GetDirectBufferAddress(env, piFirst)) + piFirst_position;
	GLsizei *piCount_address = ((GLsizei *)(*env)->GetDirectBufferAddress(env, piCount)) + piCount_position;
	glMultiDrawArrays(mode, piFirst_address, piCount_address, primcount);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_nglFogCoordPointer(JNIEnv *env, jclass clazz, jint type, jint stride, jobject data, jint data_position) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glFogCoordPointer(type, stride, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_nglFogCoordPointerBO(JNIEnv *env, jclass clazz, jint type, jint stride, jint data_buffer_offset) {
	const GLvoid *data_address = ((const GLvoid *)offsetToPointer(data_buffer_offset));
	glFogCoordPointer(type, stride, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_glFogCoordf(JNIEnv *env, jclass clazz, jfloat coord) {
	glFogCoordf(coord);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_glBlendColor(JNIEnv *env, jclass clazz, jfloat red, jfloat green, jfloat blue, jfloat alpha) {
	glBlendColor(red, green, blue, alpha);
}

static void JNICALL Java_org_lwjgl_opengl_GL14_glBlendEquation(JNIEnv *env, jclass clazz, jint mode) {
	glBlendEquation(mode);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL14_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glWindowPos3i", "(III)V", (void *)&Java_org_lwjgl_opengl_GL14_glWindowPos3i, "glWindowPos3i", (void *)&glWindowPos3i},
		{"glWindowPos3f", "(FFF)V", (void *)&Java_org_lwjgl_opengl_GL14_glWindowPos3f, "glWindowPos3f", (void *)&glWindowPos3f},
		{"glWindowPos2i", "(II)V", (void *)&Java_org_lwjgl_opengl_GL14_glWindowPos2i, "glWindowPos2i", (void *)&glWindowPos2i},
		{"glWindowPos2f", "(FF)V", (void *)&Java_org_lwjgl_opengl_GL14_glWindowPos2f, "glWindowPos2f", (void *)&glWindowPos2f},
		{"glBlendFuncSeparate", "(IIII)V", (void *)&Java_org_lwjgl_opengl_GL14_glBlendFuncSeparate, "glBlendFuncSeparate", (void *)&glBlendFuncSeparate},
		{"nglSecondaryColorPointer", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL14_nglSecondaryColorPointer, "glSecondaryColorPointer", (void *)&glSecondaryColorPointer},
		{"nglSecondaryColorPointerBO", "(IIII)V", (void *)&Java_org_lwjgl_opengl_GL14_nglSecondaryColorPointerBO, "glSecondaryColorPointer", (void *)&glSecondaryColorPointer},
		{"glSecondaryColor3ub", "(BBB)V", (void *)&Java_org_lwjgl_opengl_GL14_glSecondaryColor3ub, "glSecondaryColor3ub", (void *)&glSecondaryColor3ub},
		{"glSecondaryColor3f", "(FFF)V", (void *)&Java_org_lwjgl_opengl_GL14_glSecondaryColor3f, "glSecondaryColor3f", (void *)&glSecondaryColor3f},
		{"glSecondaryColor3b", "(BBB)V", (void *)&Java_org_lwjgl_opengl_GL14_glSecondaryColor3b, "glSecondaryColor3b", (void *)&glSecondaryColor3b},
		{"nglPointParameterfv", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL14_nglPointParameterfv, "glPointParameterfv", (void *)&glPointParameterfv},
		{"nglPointParameteriv", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL14_nglPointParameteriv, "glPointParameteriv", (void *)&glPointParameteriv},
		{"glPointParameterf", "(IF)V", (void *)&Java_org_lwjgl_opengl_GL14_glPointParameterf, "glPointParameterf", (void *)&glPointParameterf},
		{"glPointParameteri", "(II)V", (void *)&Java_org_lwjgl_opengl_GL14_glPointParameteri, "glPointParameteri", (void *)&glPointParameteri},
		{"nglMultiDrawArrays", "(ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;II)V", (void *)&Java_org_lwjgl_opengl_GL14_nglMultiDrawArrays, "glMultiDrawArrays", (void *)&glMultiDrawArrays},
		{"nglFogCoordPointer", "(IILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL14_nglFogCoordPointer, "glFogCoordPointer", (void *)&glFogCoordPointer},
		{"nglFogCoordPointerBO", "(III)V", (void *)&Java_org_lwjgl_opengl_GL14_nglFogCoordPointerBO, "glFogCoordPointer", (void *)&glFogCoordPointer},
		{"glFogCoordf", "(F)V", (void *)&Java_org_lwjgl_opengl_GL14_glFogCoordf, "glFogCoordf", (void *)&glFogCoordf},
		{"glBlendColor", "(FFFF)V", (void *)&Java_org_lwjgl_opengl_GL14_glBlendColor, "glBlendColor", (void *)&glBlendColor},
		{"glBlendEquation", "(I)V", (void *)&Java_org_lwjgl_opengl_GL14_glBlendEquation, "glBlendEquation", (void *)&glBlendEquation}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
