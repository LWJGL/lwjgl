/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetSeparableFilterPROC) (GLenum target, GLenum format, GLenum type, GLvoid * row, GLvoid * column, GLvoid * span);
typedef void (APIENTRY *glSeparableFilter2DPROC) (GLenum target, GLenum internalformat, GLsizei width, GLsizei height, GLenum format, GLenum type, const GLvoid * row, const GLvoid * column);
typedef void (APIENTRY *glGetConvolutionParameterivPROC) (GLenum target, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetConvolutionParameterfvPROC) (GLenum target, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetConvolutionFilterPROC) (GLenum target, GLenum format, GLenum type, GLvoid * image);
typedef void (APIENTRY *glCopyConvolutionFilter2DPROC) (GLenum target, GLenum internalformat, GLint x, GLint y, GLsizei width, GLsizei height);
typedef void (APIENTRY *glCopyConvolutionFilter1DPROC) (GLenum target, GLenum internalformat, GLint x, GLint y, GLsizei width);
typedef void (APIENTRY *glConvolutionParameterivPROC) (GLenum target, GLenum pname, const GLint * params);
typedef void (APIENTRY *glConvolutionParameteriPROC) (GLenum target, GLenum pname, GLint params);
typedef void (APIENTRY *glConvolutionParameterfvPROC) (GLenum target, GLenum pname, const GLfloat * params);
typedef void (APIENTRY *glConvolutionParameterfPROC) (GLenum target, GLenum pname, GLfloat params);
typedef void (APIENTRY *glConvolutionFilter2DPROC) (GLenum target, GLenum internalformat, GLsizei width, GLsizei height, GLenum format, GLenum type, GLvoid * image);
typedef void (APIENTRY *glConvolutionFilter1DPROC) (GLenum target, GLenum internalformat, GLsizei width, GLenum format, GLenum type, GLvoid * image);
typedef void (APIENTRY *glGetMinmaxParameterivPROC) (GLenum target, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetMinmaxParameterfvPROC) (GLenum target, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetMinmaxPROC) (GLenum target, GLboolean reset, GLenum format, GLenum types, GLvoid * values);
typedef void (APIENTRY *glResetMinmaxPROC) (GLenum target);
typedef void (APIENTRY *glMinmaxPROC) (GLenum target, GLenum internalformat, GLboolean sink);
typedef void (APIENTRY *glGetHistogramParameterivPROC) (GLenum target, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetHistogramParameterfvPROC) (GLenum target, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetHistogramPROC) (GLenum target, GLboolean reset, GLenum format, GLenum type, GLvoid * values);
typedef void (APIENTRY *glResetHistogramPROC) (GLenum target);
typedef void (APIENTRY *glHistogramPROC) (GLenum target, GLsizei width, GLenum internalformat, GLboolean sink);
typedef void (APIENTRY *glBlendColorPROC) (GLclampf red, GLclampf green, GLclampf blue, GLclampf alpha);
typedef void (APIENTRY *glBlendEquationPROC) (GLenum mode);
typedef void (APIENTRY *glGetColorTableParameterfvPROC) (GLenum target, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetColorTableParameterivPROC) (GLenum target, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetColorTablePROC) (GLenum target, GLenum format, GLenum type, GLvoid * data);
typedef void (APIENTRY *glCopyColorTablePROC) (GLenum target, GLenum internalformat, GLint x, GLint y, GLsizei width);
typedef void (APIENTRY *glCopyColorSubTablePROC) (GLenum target, GLsizei start, GLint x, GLint y, GLsizei width);
typedef void (APIENTRY *glColorTableParameterfvPROC) (GLenum target, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glColorTableParameterivPROC) (GLenum target, GLenum pname, GLint * params);
typedef void (APIENTRY *glColorSubTablePROC) (GLenum target, GLsizei start, GLsizei count, GLenum format, GLenum type, const GLvoid * data);
typedef void (APIENTRY *glColorTablePROC) (GLenum target, GLenum internalFormat, GLsizei width, GLenum format, GLenum type, const GLvoid * data);

static glGetSeparableFilterPROC glGetSeparableFilter;
static glSeparableFilter2DPROC glSeparableFilter2D;
static glGetConvolutionParameterivPROC glGetConvolutionParameteriv;
static glGetConvolutionParameterfvPROC glGetConvolutionParameterfv;
static glGetConvolutionFilterPROC glGetConvolutionFilter;
static glCopyConvolutionFilter2DPROC glCopyConvolutionFilter2D;
static glCopyConvolutionFilter1DPROC glCopyConvolutionFilter1D;
static glConvolutionParameterivPROC glConvolutionParameteriv;
static glConvolutionParameteriPROC glConvolutionParameteri;
static glConvolutionParameterfvPROC glConvolutionParameterfv;
static glConvolutionParameterfPROC glConvolutionParameterf;
static glConvolutionFilter2DPROC glConvolutionFilter2D;
static glConvolutionFilter1DPROC glConvolutionFilter1D;
static glGetMinmaxParameterivPROC glGetMinmaxParameteriv;
static glGetMinmaxParameterfvPROC glGetMinmaxParameterfv;
static glGetMinmaxPROC glGetMinmax;
static glResetMinmaxPROC glResetMinmax;
static glMinmaxPROC glMinmax;
static glGetHistogramParameterivPROC glGetHistogramParameteriv;
static glGetHistogramParameterfvPROC glGetHistogramParameterfv;
static glGetHistogramPROC glGetHistogram;
static glResetHistogramPROC glResetHistogram;
static glHistogramPROC glHistogram;
static glBlendColorPROC glBlendColor;
static glBlendEquationPROC glBlendEquation;
static glGetColorTableParameterfvPROC glGetColorTableParameterfv;
static glGetColorTableParameterivPROC glGetColorTableParameteriv;
static glGetColorTablePROC glGetColorTable;
static glCopyColorTablePROC glCopyColorTable;
static glCopyColorSubTablePROC glCopyColorSubTable;
static glColorTableParameterfvPROC glColorTableParameterfv;
static glColorTableParameterivPROC glColorTableParameteriv;
static glColorSubTablePROC glColorSubTable;
static glColorTablePROC glColorTable;

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetSeparableFilter(JNIEnv *env, jclass clazz, jint target, jint format, jint type, jobject row, jint row_position, jobject column, jint column_position, jobject span, jint span_position) {
	GLvoid *row_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, row)) + row_position));
	GLvoid *column_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, column)) + column_position));
	GLvoid *span_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, span)) + span_position));
	glGetSeparableFilter(target, format, type, row_address, column_address, span_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetSeparableFilterBO(JNIEnv *env, jclass clazz, jint target, jint format, jint type, jint row_buffer_offset, jint column_buffer_offset, jint span_buffer_offset) {
	GLvoid *row_address = ((GLvoid *)offsetToPointer(row_buffer_offset));
	GLvoid *column_address = ((GLvoid *)offsetToPointer(column_buffer_offset));
	GLvoid *span_address = ((GLvoid *)offsetToPointer(span_buffer_offset));
	glGetSeparableFilter(target, format, type, row_address, column_address, span_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglSeparableFilter2D(JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height, jint format, jint type, jobject row, jint row_position, jobject column, jint column_position) {
	const GLvoid *row_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, row)) + row_position));
	const GLvoid *column_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, column)) + column_position));
	glSeparableFilter2D(target, internalformat, width, height, format, type, row_address, column_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglSeparableFilter2DBO(JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height, jint format, jint type, jint row_buffer_offset, jint column_buffer_offset) {
	const GLvoid *row_address = ((const GLvoid *)offsetToPointer(row_buffer_offset));
	const GLvoid *column_address = ((const GLvoid *)offsetToPointer(column_buffer_offset));
	glSeparableFilter2D(target, internalformat, width, height, format, type, row_address, column_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetConvolutionParameteriv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetConvolutionParameteriv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetConvolutionParameterfv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetConvolutionParameterfv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetConvolutionFilter(JNIEnv *env, jclass clazz, jint target, jint format, jint type, jobject image, jint image_position) {
	GLvoid *image_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, image)) + image_position));
	glGetConvolutionFilter(target, format, type, image_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetConvolutionFilterBO(JNIEnv *env, jclass clazz, jint target, jint format, jint type, jint image_buffer_offset) {
	GLvoid *image_address = ((GLvoid *)offsetToPointer(image_buffer_offset));
	glGetConvolutionFilter(target, format, type, image_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glCopyConvolutionFilter2D(JNIEnv *env, jclass clazz, jint target, jint internalformat, jint x, jint y, jint width, jint height) {
	glCopyConvolutionFilter2D(target, internalformat, x, y, width, height);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glCopyConvolutionFilter1D(JNIEnv *env, jclass clazz, jint target, jint internalformat, jint x, jint y, jint width) {
	glCopyConvolutionFilter1D(target, internalformat, x, y, width);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglConvolutionParameteriv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	const GLint *params_address = ((const GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glConvolutionParameteriv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glConvolutionParameteri(JNIEnv *env, jclass clazz, jint target, jint pname, jint params) {
	glConvolutionParameteri(target, pname, params);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglConvolutionParameterfv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	const GLfloat *params_address = ((const GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glConvolutionParameterfv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glConvolutionParameterf(JNIEnv *env, jclass clazz, jint target, jint pname, jfloat params) {
	glConvolutionParameterf(target, pname, params);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglConvolutionFilter2D(JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height, jint format, jint type, jobject image, jint image_position) {
	GLvoid *image_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, image)) + image_position));
	glConvolutionFilter2D(target, internalformat, width, height, format, type, image_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglConvolutionFilter2DBO(JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint height, jint format, jint type, jint image_buffer_offset) {
	GLvoid *image_address = ((GLvoid *)offsetToPointer(image_buffer_offset));
	glConvolutionFilter2D(target, internalformat, width, height, format, type, image_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglConvolutionFilter1D(JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint format, jint type, jobject image, jint image_position) {
	GLvoid *image_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, image)) + image_position));
	glConvolutionFilter1D(target, internalformat, width, format, type, image_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglConvolutionFilter1DBO(JNIEnv *env, jclass clazz, jint target, jint internalformat, jint width, jint format, jint type, jint image_buffer_offset) {
	GLvoid *image_address = ((GLvoid *)offsetToPointer(image_buffer_offset));
	glConvolutionFilter1D(target, internalformat, width, format, type, image_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetMinmaxParameteriv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetMinmaxParameteriv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetMinmaxParameterfv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetMinmaxParameterfv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetMinmax(JNIEnv *env, jclass clazz, jint target, jboolean reset, jint format, jint types, jobject values, jint values_position) {
	GLvoid *values_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, values)) + values_position));
	glGetMinmax(target, reset, format, types, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetMinmaxBO(JNIEnv *env, jclass clazz, jint target, jboolean reset, jint format, jint types, jint values_buffer_offset) {
	GLvoid *values_address = ((GLvoid *)offsetToPointer(values_buffer_offset));
	glGetMinmax(target, reset, format, types, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glResetMinmax(JNIEnv *env, jclass clazz, jint target) {
	glResetMinmax(target);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glMinmax(JNIEnv *env, jclass clazz, jint target, jint internalformat, jboolean sink) {
	glMinmax(target, internalformat, sink);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetHistogramParameteriv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetHistogramParameteriv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetHistogramParameterfv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetHistogramParameterfv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetHistogram(JNIEnv *env, jclass clazz, jint target, jboolean reset, jint format, jint type, jobject values, jint values_position) {
	GLvoid *values_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, values)) + values_position));
	glGetHistogram(target, reset, format, type, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetHistogramBO(JNIEnv *env, jclass clazz, jint target, jboolean reset, jint format, jint type, jint values_buffer_offset) {
	GLvoid *values_address = ((GLvoid *)offsetToPointer(values_buffer_offset));
	glGetHistogram(target, reset, format, type, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glResetHistogram(JNIEnv *env, jclass clazz, jint target) {
	glResetHistogram(target);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glHistogram(JNIEnv *env, jclass clazz, jint target, jint width, jint internalformat, jboolean sink) {
	glHistogram(target, width, internalformat, sink);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glBlendColor(JNIEnv *env, jclass clazz, jfloat red, jfloat green, jfloat blue, jfloat alpha) {
	glBlendColor(red, green, blue, alpha);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glBlendEquation(JNIEnv *env, jclass clazz, jint mode) {
	glBlendEquation(mode);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetColorTableParameterfv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetColorTableParameterfv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetColorTableParameteriv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetColorTableParameteriv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglGetColorTable(JNIEnv *env, jclass clazz, jint target, jint format, jint type, jobject data, jint data_position) {
	GLvoid *data_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glGetColorTable(target, format, type, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glCopyColorTable(JNIEnv *env, jclass clazz, jint target, jint internalformat, jint x, jint y, jint width) {
	glCopyColorTable(target, internalformat, x, y, width);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_glCopyColorSubTable(JNIEnv *env, jclass clazz, jint target, jint start, jint x, jint y, jint width) {
	glCopyColorSubTable(target, start, x, y, width);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglColorTableParameterfv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glColorTableParameterfv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglColorTableParameteriv(JNIEnv *env, jclass clazz, jint target, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glColorTableParameteriv(target, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglColorSubTable(JNIEnv *env, jclass clazz, jint target, jint start, jint count, jint format, jint type, jobject data, jint data_position) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glColorSubTable(target, start, count, format, type, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglColorSubTableBO(JNIEnv *env, jclass clazz, jint target, jint start, jint count, jint format, jint type, jint data_buffer_offset) {
	const GLvoid *data_address = ((const GLvoid *)offsetToPointer(data_buffer_offset));
	glColorSubTable(target, start, count, format, type, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglColorTable(JNIEnv *env, jclass clazz, jint target, jint internalFormat, jint width, jint format, jint type, jobject data, jint data_position) {
	const GLvoid *data_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, data)) + data_position));
	glColorTable(target, internalFormat, width, format, type, data_address);
}

static void JNICALL Java_org_lwjgl_opengl_ARBImaging_nglColorTableBO(JNIEnv *env, jclass clazz, jint target, jint internalFormat, jint width, jint format, jint type, jint data_buffer_offset) {
	const GLvoid *data_address = ((const GLvoid *)offsetToPointer(data_buffer_offset));
	glColorTable(target, internalFormat, width, format, type, data_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ARBImaging_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetSeparableFilter", "(IIILjava/nio/Buffer;ILjava/nio/Buffer;ILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetSeparableFilter, "glGetSeparableFilter", (void *)&glGetSeparableFilter},
		{"nglGetSeparableFilterBO", "(IIIIII)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetSeparableFilterBO, "glGetSeparableFilter", (void *)&glGetSeparableFilter},
		{"nglSeparableFilter2D", "(IIIIIILjava/nio/Buffer;ILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglSeparableFilter2D, "glSeparableFilter2D", (void *)&glSeparableFilter2D},
		{"nglSeparableFilter2DBO", "(IIIIIIII)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglSeparableFilter2DBO, "glSeparableFilter2D", (void *)&glSeparableFilter2D},
		{"nglGetConvolutionParameteriv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetConvolutionParameteriv, "glGetConvolutionParameteriv", (void *)&glGetConvolutionParameteriv},
		{"nglGetConvolutionParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetConvolutionParameterfv, "glGetConvolutionParameterfv", (void *)&glGetConvolutionParameterfv},
		{"nglGetConvolutionFilter", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetConvolutionFilter, "glGetConvolutionFilter", (void *)&glGetConvolutionFilter},
		{"nglGetConvolutionFilterBO", "(IIII)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetConvolutionFilterBO, "glGetConvolutionFilter", (void *)&glGetConvolutionFilter},
		{"glCopyConvolutionFilter2D", "(IIIIII)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_glCopyConvolutionFilter2D, "glCopyConvolutionFilter2D", (void *)&glCopyConvolutionFilter2D},
		{"glCopyConvolutionFilter1D", "(IIIII)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_glCopyConvolutionFilter1D, "glCopyConvolutionFilter1D", (void *)&glCopyConvolutionFilter1D},
		{"nglConvolutionParameteriv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglConvolutionParameteriv, "glConvolutionParameteriv", (void *)&glConvolutionParameteriv},
		{"glConvolutionParameteri", "(III)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_glConvolutionParameteri, "glConvolutionParameteri", (void *)&glConvolutionParameteri},
		{"nglConvolutionParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglConvolutionParameterfv, "glConvolutionParameterfv", (void *)&glConvolutionParameterfv},
		{"glConvolutionParameterf", "(IIF)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_glConvolutionParameterf, "glConvolutionParameterf", (void *)&glConvolutionParameterf},
		{"nglConvolutionFilter2D", "(IIIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglConvolutionFilter2D, "glConvolutionFilter2D", (void *)&glConvolutionFilter2D},
		{"nglConvolutionFilter2DBO", "(IIIIIII)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglConvolutionFilter2DBO, "glConvolutionFilter2D", (void *)&glConvolutionFilter2D},
		{"nglConvolutionFilter1D", "(IIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglConvolutionFilter1D, "glConvolutionFilter1D", (void *)&glConvolutionFilter1D},
		{"nglConvolutionFilter1DBO", "(IIIIII)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglConvolutionFilter1DBO, "glConvolutionFilter1D", (void *)&glConvolutionFilter1D},
		{"nglGetMinmaxParameteriv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetMinmaxParameteriv, "glGetMinmaxParameteriv", (void *)&glGetMinmaxParameteriv},
		{"nglGetMinmaxParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetMinmaxParameterfv, "glGetMinmaxParameterfv", (void *)&glGetMinmaxParameterfv},
		{"nglGetMinmax", "(IZIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetMinmax, "glGetMinmax", (void *)&glGetMinmax},
		{"nglGetMinmaxBO", "(IZIII)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetMinmaxBO, "glGetMinmax", (void *)&glGetMinmax},
		{"glResetMinmax", "(I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_glResetMinmax, "glResetMinmax", (void *)&glResetMinmax},
		{"glMinmax", "(IIZ)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_glMinmax, "glMinmax", (void *)&glMinmax},
		{"nglGetHistogramParameteriv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetHistogramParameteriv, "glGetHistogramParameteriv", (void *)&glGetHistogramParameteriv},
		{"nglGetHistogramParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetHistogramParameterfv, "glGetHistogramParameterfv", (void *)&glGetHistogramParameterfv},
		{"nglGetHistogram", "(IZIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetHistogram, "glGetHistogram", (void *)&glGetHistogram},
		{"nglGetHistogramBO", "(IZIII)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetHistogramBO, "glGetHistogram", (void *)&glGetHistogram},
		{"glResetHistogram", "(I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_glResetHistogram, "glResetHistogram", (void *)&glResetHistogram},
		{"glHistogram", "(IIIZ)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_glHistogram, "glHistogram", (void *)&glHistogram},
		{"glBlendColor", "(FFFF)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_glBlendColor, "glBlendColor", (void *)&glBlendColor},
		{"glBlendEquation", "(I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_glBlendEquation, "glBlendEquation", (void *)&glBlendEquation},
		{"nglGetColorTableParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetColorTableParameterfv, "glGetColorTableParameterfv", (void *)&glGetColorTableParameterfv},
		{"nglGetColorTableParameteriv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetColorTableParameteriv, "glGetColorTableParameteriv", (void *)&glGetColorTableParameteriv},
		{"nglGetColorTable", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglGetColorTable, "glGetColorTable", (void *)&glGetColorTable},
		{"glCopyColorTable", "(IIIII)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_glCopyColorTable, "glCopyColorTable", (void *)&glCopyColorTable},
		{"glCopyColorSubTable", "(IIIII)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_glCopyColorSubTable, "glCopyColorSubTable", (void *)&glCopyColorSubTable},
		{"nglColorTableParameterfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglColorTableParameterfv, "glColorTableParameterfv", (void *)&glColorTableParameterfv},
		{"nglColorTableParameteriv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglColorTableParameteriv, "glColorTableParameteriv", (void *)&glColorTableParameteriv},
		{"nglColorSubTable", "(IIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglColorSubTable, "glColorSubTable", (void *)&glColorSubTable},
		{"nglColorSubTableBO", "(IIIIII)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglColorSubTableBO, "glColorSubTable", (void *)&glColorSubTable},
		{"nglColorTable", "(IIIIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglColorTable, "glColorTable", (void *)&glColorTable},
		{"nglColorTableBO", "(IIIIII)V", (void *)&Java_org_lwjgl_opengl_ARBImaging_nglColorTableBO, "glColorTable", (void *)&glColorTable}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
