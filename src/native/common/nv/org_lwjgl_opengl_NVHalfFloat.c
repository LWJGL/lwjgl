/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glVertexAttribs4hvNVPROC) (GLuint index, GLsizei n, const GLhalf * attribs);
typedef void (APIENTRY *glVertexAttribs3hvNVPROC) (GLuint index, GLsizei n, const GLhalf * attribs);
typedef void (APIENTRY *glVertexAttribs2hvNVPROC) (GLuint index, GLsizei n, const GLhalf * attribs);
typedef void (APIENTRY *glVertexAttribs1hvNVPROC) (GLuint index, GLsizei n, const GLhalf * attribs);
typedef void (APIENTRY *glVertexAttrib4hNVPROC) (GLuint index, GLhalf x, GLhalf y, GLhalf z, GLhalf w);
typedef void (APIENTRY *glVertexAttrib3hNVPROC) (GLuint index, GLhalf x, GLhalf y, GLhalf z);
typedef void (APIENTRY *glVertexAttrib2hNVPROC) (GLuint index, GLhalf x, GLhalf y);
typedef void (APIENTRY *glVertexAttrib1hNVPROC) (GLuint index, GLhalf x);
typedef void (APIENTRY *glSecondaryColor3hNVPROC) (GLhalf red, GLhalf green, GLhalf blue);
typedef void (APIENTRY *glFogCoordhNVPROC) (GLhalf fog);
typedef void (APIENTRY *glMultiTexCoord4hNVPROC) (GLenum target, GLhalf s, GLhalf t, GLhalf r, GLhalf q);
typedef void (APIENTRY *glMultiTexCoord3hNVPROC) (GLenum target, GLhalf s, GLhalf t, GLhalf r);
typedef void (APIENTRY *glMultiTexCoord2hNVPROC) (GLenum target, GLhalf s, GLhalf t);
typedef void (APIENTRY *glMultiTexCoord1hNVPROC) (GLenum target, GLhalf s);
typedef void (APIENTRY *glTexCoord4hNVPROC) (GLhalf s, GLhalf t, GLhalf r, GLhalf q);
typedef void (APIENTRY *glTexCoord3hNVPROC) (GLhalf s, GLhalf t, GLhalf r);
typedef void (APIENTRY *glTexCoord2hNVPROC) (GLhalf s, GLhalf t);
typedef void (APIENTRY *glTexCoord1hNVPROC) (GLhalf s);
typedef void (APIENTRY *glColor4hNVPROC) (GLhalf red, GLhalf green, GLhalf blue, GLhalf alpha);
typedef void (APIENTRY *glColor3hNVPROC) (GLhalf red, GLhalf green, GLhalf blue);
typedef void (APIENTRY *glNormal3hNVPROC) (GLhalf nx, GLhalf ny, GLhalf nz);
typedef void (APIENTRY *glVertex4hNVPROC) (GLhalf x, GLhalf y, GLhalf z, GLhalf w);
typedef void (APIENTRY *glVertex3hNVPROC) (GLhalf x, GLhalf y, GLhalf z);
typedef void (APIENTRY *glVertex2hNVPROC) (GLhalf x, GLhalf y);

static glVertexAttribs4hvNVPROC glVertexAttribs4hvNV;
static glVertexAttribs3hvNVPROC glVertexAttribs3hvNV;
static glVertexAttribs2hvNVPROC glVertexAttribs2hvNV;
static glVertexAttribs1hvNVPROC glVertexAttribs1hvNV;
static glVertexAttrib4hNVPROC glVertexAttrib4hNV;
static glVertexAttrib3hNVPROC glVertexAttrib3hNV;
static glVertexAttrib2hNVPROC glVertexAttrib2hNV;
static glVertexAttrib1hNVPROC glVertexAttrib1hNV;
static glSecondaryColor3hNVPROC glSecondaryColor3hNV;
static glFogCoordhNVPROC glFogCoordhNV;
static glMultiTexCoord4hNVPROC glMultiTexCoord4hNV;
static glMultiTexCoord3hNVPROC glMultiTexCoord3hNV;
static glMultiTexCoord2hNVPROC glMultiTexCoord2hNV;
static glMultiTexCoord1hNVPROC glMultiTexCoord1hNV;
static glTexCoord4hNVPROC glTexCoord4hNV;
static glTexCoord3hNVPROC glTexCoord3hNV;
static glTexCoord2hNVPROC glTexCoord2hNV;
static glTexCoord1hNVPROC glTexCoord1hNV;
static glColor4hNVPROC glColor4hNV;
static glColor3hNVPROC glColor3hNV;
static glNormal3hNVPROC glNormal3hNV;
static glVertex4hNVPROC glVertex4hNV;
static glVertex3hNVPROC glVertex3hNV;
static glVertex2hNVPROC glVertex2hNV;

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs4hvNV(JNIEnv *env, jclass clazz, jint index, jint n, jobject attribs, jint attribs_position) {
	const GLhalf *attribs_address = ((const GLhalf *)(*env)->GetDirectBufferAddress(env, attribs)) + attribs_position;
	glVertexAttribs4hvNV(index, n, attribs_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs3hvNV(JNIEnv *env, jclass clazz, jint index, jint n, jobject attribs, jint attribs_position) {
	const GLhalf *attribs_address = ((const GLhalf *)(*env)->GetDirectBufferAddress(env, attribs)) + attribs_position;
	glVertexAttribs3hvNV(index, n, attribs_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs2hvNV(JNIEnv *env, jclass clazz, jint index, jint n, jobject attribs, jint attribs_position) {
	const GLhalf *attribs_address = ((const GLhalf *)(*env)->GetDirectBufferAddress(env, attribs)) + attribs_position;
	glVertexAttribs2hvNV(index, n, attribs_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs1hvNV(JNIEnv *env, jclass clazz, jint index, jint n, jobject attribs, jint attribs_position) {
	const GLhalf *attribs_address = ((const GLhalf *)(*env)->GetDirectBufferAddress(env, attribs)) + attribs_position;
	glVertexAttribs1hvNV(index, n, attribs_address);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib4hNV(JNIEnv *env, jclass clazz, jint index, jshort x, jshort y, jshort z, jshort w) {
	glVertexAttrib4hNV(index, x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib3hNV(JNIEnv *env, jclass clazz, jint index, jshort x, jshort y, jshort z) {
	glVertexAttrib3hNV(index, x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib2hNV(JNIEnv *env, jclass clazz, jint index, jshort x, jshort y) {
	glVertexAttrib2hNV(index, x, y);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib1hNV(JNIEnv *env, jclass clazz, jint index, jshort x) {
	glVertexAttrib1hNV(index, x);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glSecondaryColor3hNV(JNIEnv *env, jclass clazz, jshort red, jshort green, jshort blue) {
	glSecondaryColor3hNV(red, green, blue);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glFogCoordhNV(JNIEnv *env, jclass clazz, jshort fog) {
	glFogCoordhNV(fog);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord4hNV(JNIEnv *env, jclass clazz, jint target, jshort s, jshort t, jshort r, jshort q) {
	glMultiTexCoord4hNV(target, s, t, r, q);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord3hNV(JNIEnv *env, jclass clazz, jint target, jshort s, jshort t, jshort r) {
	glMultiTexCoord3hNV(target, s, t, r);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord2hNV(JNIEnv *env, jclass clazz, jint target, jshort s, jshort t) {
	glMultiTexCoord2hNV(target, s, t);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord1hNV(JNIEnv *env, jclass clazz, jint target, jshort s) {
	glMultiTexCoord1hNV(target, s);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord4hNV(JNIEnv *env, jclass clazz, jshort s, jshort t, jshort r, jshort q) {
	glTexCoord4hNV(s, t, r, q);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord3hNV(JNIEnv *env, jclass clazz, jshort s, jshort t, jshort r) {
	glTexCoord3hNV(s, t, r);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord2hNV(JNIEnv *env, jclass clazz, jshort s, jshort t) {
	glTexCoord2hNV(s, t);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord1hNV(JNIEnv *env, jclass clazz, jshort s) {
	glTexCoord1hNV(s);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glColor4hNV(JNIEnv *env, jclass clazz, jshort red, jshort green, jshort blue, jshort alpha) {
	glColor4hNV(red, green, blue, alpha);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glColor3hNV(JNIEnv *env, jclass clazz, jshort red, jshort green, jshort blue) {
	glColor3hNV(red, green, blue);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glNormal3hNV(JNIEnv *env, jclass clazz, jshort nx, jshort ny, jshort nz) {
	glNormal3hNV(nx, ny, nz);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertex4hNV(JNIEnv *env, jclass clazz, jshort x, jshort y, jshort z, jshort w) {
	glVertex4hNV(x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertex3hNV(JNIEnv *env, jclass clazz, jshort x, jshort y, jshort z) {
	glVertex3hNV(x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_glVertex2hNV(JNIEnv *env, jclass clazz, jshort x, jshort y) {
	glVertex2hNV(x, y);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVHalfFloat_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglVertexAttribs4hvNV", "(IILjava/nio/ShortBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs4hvNV, "glVertexAttribs4hvNV", (void *)&glVertexAttribs4hvNV},
		{"nglVertexAttribs3hvNV", "(IILjava/nio/ShortBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs3hvNV, "glVertexAttribs3hvNV", (void *)&glVertexAttribs3hvNV},
		{"nglVertexAttribs2hvNV", "(IILjava/nio/ShortBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs2hvNV, "glVertexAttribs2hvNV", (void *)&glVertexAttribs2hvNV},
		{"nglVertexAttribs1hvNV", "(IILjava/nio/ShortBuffer;I)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_nglVertexAttribs1hvNV, "glVertexAttribs1hvNV", (void *)&glVertexAttribs1hvNV},
		{"glVertexAttrib4hNV", "(ISSSS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib4hNV, "glVertexAttrib4hNV", (void *)&glVertexAttrib4hNV},
		{"glVertexAttrib3hNV", "(ISSS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib3hNV, "glVertexAttrib3hNV", (void *)&glVertexAttrib3hNV},
		{"glVertexAttrib2hNV", "(ISS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib2hNV, "glVertexAttrib2hNV", (void *)&glVertexAttrib2hNV},
		{"glVertexAttrib1hNV", "(IS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glVertexAttrib1hNV, "glVertexAttrib1hNV", (void *)&glVertexAttrib1hNV},
		{"glSecondaryColor3hNV", "(SSS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glSecondaryColor3hNV, "glSecondaryColor3hNV", (void *)&glSecondaryColor3hNV},
		{"glFogCoordhNV", "(S)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glFogCoordhNV, "glFogCoordhNV", (void *)&glFogCoordhNV},
		{"glMultiTexCoord4hNV", "(ISSSS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord4hNV, "glMultiTexCoord4hNV", (void *)&glMultiTexCoord4hNV},
		{"glMultiTexCoord3hNV", "(ISSS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord3hNV, "glMultiTexCoord3hNV", (void *)&glMultiTexCoord3hNV},
		{"glMultiTexCoord2hNV", "(ISS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord2hNV, "glMultiTexCoord2hNV", (void *)&glMultiTexCoord2hNV},
		{"glMultiTexCoord1hNV", "(IS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glMultiTexCoord1hNV, "glMultiTexCoord1hNV", (void *)&glMultiTexCoord1hNV},
		{"glTexCoord4hNV", "(SSSS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord4hNV, "glTexCoord4hNV", (void *)&glTexCoord4hNV},
		{"glTexCoord3hNV", "(SSS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord3hNV, "glTexCoord3hNV", (void *)&glTexCoord3hNV},
		{"glTexCoord2hNV", "(SS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord2hNV, "glTexCoord2hNV", (void *)&glTexCoord2hNV},
		{"glTexCoord1hNV", "(S)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glTexCoord1hNV, "glTexCoord1hNV", (void *)&glTexCoord1hNV},
		{"glColor4hNV", "(SSSS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glColor4hNV, "glColor4hNV", (void *)&glColor4hNV},
		{"glColor3hNV", "(SSS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glColor3hNV, "glColor3hNV", (void *)&glColor3hNV},
		{"glNormal3hNV", "(SSS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glNormal3hNV, "glNormal3hNV", (void *)&glNormal3hNV},
		{"glVertex4hNV", "(SSSS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glVertex4hNV, "glVertex4hNV", (void *)&glVertex4hNV},
		{"glVertex3hNV", "(SSS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glVertex3hNV, "glVertex3hNV", (void *)&glVertex3hNV},
		{"glVertex2hNV", "(SS)V", (void *)&Java_org_lwjgl_opengl_NVHalfFloat_glVertex2hNV, "glVertex2hNV", (void *)&glVertex2hNV}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
