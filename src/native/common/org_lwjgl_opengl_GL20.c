/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glBlendEquationSeparatePROC) (GLenum modeRGB, GLenum modeAlpha);
typedef void (APIENTRY *glStencilMaskSeparatePROC) (GLenum face, GLuint mask);
typedef void (APIENTRY *glStencilFuncSeparatePROC) (GLenum face, GLenum func, GLint ref, GLuint mask);
typedef void (APIENTRY *glStencilOpSeparatePROC) (GLenum face, GLenum sfail, GLenum dpfail, GLenum dppass);
typedef void (APIENTRY *glDrawBuffersPROC) (GLsizei size, const GLenum * buffers);
typedef GLint (APIENTRY *glGetAttribLocationPROC) (GLuint program, const GLchar * name);
typedef void (APIENTRY *glGetActiveAttribPROC) (GLuint program, GLuint index, GLsizei maxLength, GLsizei * length, GLint * size, GLenum * type, const GLchar * name);
typedef void (APIENTRY *glBindAttribLocationPROC) (GLuint program, GLuint index, const GLchar * name);
typedef void (APIENTRY *glGetVertexAttribPointervPROC) (GLuint index, GLenum pname, GLvoid ** pointer);
typedef void (APIENTRY *glGetVertexAttribivPROC) (GLuint index, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetVertexAttribfvPROC) (GLuint index, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glDisableVertexAttribArrayPROC) (GLuint index);
typedef void (APIENTRY *glEnableVertexAttribArrayPROC) (GLuint index);
typedef void (APIENTRY *glVertexAttribPointerPROC) (GLuint index, GLint size, GLenum type, GLboolean normalized, GLsizei stride, const GLvoid * buffer);
typedef void (APIENTRY *glVertexAttrib4NubPROC) (GLuint index, GLubyte x, GLubyte y, GLubyte z, GLubyte w);
typedef void (APIENTRY *glVertexAttrib4fPROC) (GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY *glVertexAttrib4sPROC) (GLuint index, GLshort x, GLshort y, GLshort z, GLshort w);
typedef void (APIENTRY *glVertexAttrib3fPROC) (GLuint index, GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY *glVertexAttrib3sPROC) (GLuint index, GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY *glVertexAttrib2fPROC) (GLuint index, GLfloat x, GLfloat y);
typedef void (APIENTRY *glVertexAttrib2sPROC) (GLuint index, GLshort x, GLshort y);
typedef void (APIENTRY *glVertexAttrib1fPROC) (GLuint index, GLfloat x);
typedef void (APIENTRY *glVertexAttrib1sPROC) (GLuint index, GLshort x);
typedef void (APIENTRY *glGetShaderSourcePROC) (GLuint shader, GLsizei maxLength, GLsizei * length, GLchar * source);
typedef void (APIENTRY *glGetUniformivPROC) (GLuint program, GLint location, GLint * params);
typedef void (APIENTRY *glGetUniformfvPROC) (GLuint program, GLint location, GLfloat * params);
typedef void (APIENTRY *glGetActiveUniformPROC) (GLuint program, GLuint index, GLsizei maxLength, GLsizei * length, GLsizei * size, GLenum * type, GLchar * name);
typedef GLint (APIENTRY *glGetUniformLocationPROC) (GLuint program, const GLchar * name);
typedef void (APIENTRY *glGetAttachedShadersPROC) (GLuint program, GLsizei maxCount, GLsizei * count, GLuint * shaders);
typedef void (APIENTRY *glGetProgramInfoLogPROC) (GLuint program, GLsizei maxLength, GLsizei * length, GLchar * infoLog);
typedef void (APIENTRY *glGetShaderInfoLogPROC) (GLuint shader, GLsizei maxLength, GLsizei * length, GLchar * infoLog);
typedef void (APIENTRY *glGetProgramivPROC) (GLuint program, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetProgramfvPROC) (GLuint program, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glGetShaderivPROC) (GLuint shader, GLenum pname, GLint * params);
typedef void (APIENTRY *glGetShaderfvPROC) (GLuint shader, GLenum pname, GLfloat * params);
typedef void (APIENTRY *glUniformMatrix4fvPROC) (GLint location, GLsizei count, GLboolean transpose, GLfloat * matrices);
typedef void (APIENTRY *glUniformMatrix3fvPROC) (GLint location, GLsizei count, GLboolean transpose, GLfloat * matrices);
typedef void (APIENTRY *glUniformMatrix2fvPROC) (GLint location, GLsizei count, GLboolean transpose, GLfloat * matrices);
typedef void (APIENTRY *glUniform4ivPROC) (GLint location, GLsizei count, GLint * values);
typedef void (APIENTRY *glUniform3ivPROC) (GLint location, GLsizei count, GLint * values);
typedef void (APIENTRY *glUniform2ivPROC) (GLint location, GLsizei count, GLint * values);
typedef void (APIENTRY *glUniform1ivPROC) (GLint location, GLsizei count, GLint * values);
typedef void (APIENTRY *glUniform4fvPROC) (GLint location, GLsizei count, GLfloat * values);
typedef void (APIENTRY *glUniform3fvPROC) (GLint location, GLsizei count, GLfloat * values);
typedef void (APIENTRY *glUniform2fvPROC) (GLint location, GLsizei count, GLfloat * values);
typedef void (APIENTRY *glUniform1fvPROC) (GLint location, GLsizei count, GLfloat * values);
typedef void (APIENTRY *glUniform4iPROC) (GLint location, GLint v0, GLint v1, GLint v2, GLint v3);
typedef void (APIENTRY *glUniform3iPROC) (GLint location, GLint v0, GLint v1, GLint v2);
typedef void (APIENTRY *glUniform2iPROC) (GLint location, GLint v0, GLint v1);
typedef void (APIENTRY *glUniform1iPROC) (GLint location, GLint v0);
typedef void (APIENTRY *glUniform4fPROC) (GLint location, GLfloat v0, GLfloat v1, GLfloat v2, GLfloat v3);
typedef void (APIENTRY *glUniform3fPROC) (GLint location, GLfloat v0, GLfloat v1, GLfloat v2);
typedef void (APIENTRY *glUniform2fPROC) (GLint location, GLfloat v0, GLfloat v1);
typedef void (APIENTRY *glUniform1fPROC) (GLint location, GLfloat v0);
typedef void (APIENTRY *glDeleteProgramPROC) (GLuint program);
typedef void (APIENTRY *glValidateProgramPROC) (GLuint program);
typedef void (APIENTRY *glUseProgramPROC) (GLuint program);
typedef void (APIENTRY *glLinkProgramPROC) (GLuint program);
typedef void (APIENTRY *glDetachShaderPROC) (GLuint program, GLuint shader);
typedef void (APIENTRY *glAttachShaderPROC) (GLuint program, GLuint shader);
typedef GLboolean (APIENTRY *glIsProgramPROC) (GLint program);
typedef GLint (APIENTRY *glCreateProgramPROC) ();
typedef void (APIENTRY *glDeleteShaderPROC) (GLuint shader);
typedef void (APIENTRY *glCompileShaderPROC) (GLuint shader);
typedef GLboolean (APIENTRY *glIsShaderPROC) (GLuint shader);
typedef GLint (APIENTRY *glCreateShaderPROC) (GLuint type);
typedef void (APIENTRY *glShaderSourcePROC) (GLuint shader, GLsizei count, const GLchar ** string, const GLint* length);

static glBlendEquationSeparatePROC glBlendEquationSeparate;
static glStencilMaskSeparatePROC glStencilMaskSeparate;
static glStencilFuncSeparatePROC glStencilFuncSeparate;
static glStencilOpSeparatePROC glStencilOpSeparate;
static glDrawBuffersPROC glDrawBuffers;
static glGetAttribLocationPROC glGetAttribLocation;
static glGetActiveAttribPROC glGetActiveAttrib;
static glBindAttribLocationPROC glBindAttribLocation;
static glGetVertexAttribPointervPROC glGetVertexAttribPointerv;
static glGetVertexAttribivPROC glGetVertexAttribiv;
static glGetVertexAttribfvPROC glGetVertexAttribfv;
static glDisableVertexAttribArrayPROC glDisableVertexAttribArray;
static glEnableVertexAttribArrayPROC glEnableVertexAttribArray;
static glVertexAttribPointerPROC glVertexAttribPointer;
static glVertexAttrib4NubPROC glVertexAttrib4Nub;
static glVertexAttrib4fPROC glVertexAttrib4f;
static glVertexAttrib4sPROC glVertexAttrib4s;
static glVertexAttrib3fPROC glVertexAttrib3f;
static glVertexAttrib3sPROC glVertexAttrib3s;
static glVertexAttrib2fPROC glVertexAttrib2f;
static glVertexAttrib2sPROC glVertexAttrib2s;
static glVertexAttrib1fPROC glVertexAttrib1f;
static glVertexAttrib1sPROC glVertexAttrib1s;
static glGetShaderSourcePROC glGetShaderSource;
static glGetUniformivPROC glGetUniformiv;
static glGetUniformfvPROC glGetUniformfv;
static glGetActiveUniformPROC glGetActiveUniform;
static glGetUniformLocationPROC glGetUniformLocation;
static glGetAttachedShadersPROC glGetAttachedShaders;
static glGetProgramInfoLogPROC glGetProgramInfoLog;
static glGetShaderInfoLogPROC glGetShaderInfoLog;
static glGetProgramivPROC glGetProgramiv;
static glGetProgramfvPROC glGetProgramfv;
static glGetShaderivPROC glGetShaderiv;
static glGetShaderfvPROC glGetShaderfv;
static glUniformMatrix4fvPROC glUniformMatrix4fv;
static glUniformMatrix3fvPROC glUniformMatrix3fv;
static glUniformMatrix2fvPROC glUniformMatrix2fv;
static glUniform4ivPROC glUniform4iv;
static glUniform3ivPROC glUniform3iv;
static glUniform2ivPROC glUniform2iv;
static glUniform1ivPROC glUniform1iv;
static glUniform4fvPROC glUniform4fv;
static glUniform3fvPROC glUniform3fv;
static glUniform2fvPROC glUniform2fv;
static glUniform1fvPROC glUniform1fv;
static glUniform4iPROC glUniform4i;
static glUniform3iPROC glUniform3i;
static glUniform2iPROC glUniform2i;
static glUniform1iPROC glUniform1i;
static glUniform4fPROC glUniform4f;
static glUniform3fPROC glUniform3f;
static glUniform2fPROC glUniform2f;
static glUniform1fPROC glUniform1f;
static glDeleteProgramPROC glDeleteProgram;
static glValidateProgramPROC glValidateProgram;
static glUseProgramPROC glUseProgram;
static glLinkProgramPROC glLinkProgram;
static glDetachShaderPROC glDetachShader;
static glAttachShaderPROC glAttachShader;
static glIsProgramPROC glIsProgram;
static glCreateProgramPROC glCreateProgram;
static glDeleteShaderPROC glDeleteShader;
static glCompileShaderPROC glCompileShader;
static glIsShaderPROC glIsShader;
static glCreateShaderPROC glCreateShader;
static glShaderSourcePROC glShaderSource;

static void JNICALL Java_org_lwjgl_opengl_GL20_glBlendEquationSeparate(JNIEnv *env, jclass clazz, jint modeRGB, jint modeAlpha) {
	glBlendEquationSeparate(modeRGB, modeAlpha);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glStencilMaskSeparate(JNIEnv *env, jclass clazz, jint face, jint mask) {
	glStencilMaskSeparate(face, mask);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glStencilFuncSeparate(JNIEnv *env, jclass clazz, jint face, jint func, jint ref, jint mask) {
	glStencilFuncSeparate(face, func, ref, mask);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glStencilOpSeparate(JNIEnv *env, jclass clazz, jint face, jint sfail, jint dpfail, jint dppass) {
	glStencilOpSeparate(face, sfail, dpfail, dppass);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglDrawBuffers(JNIEnv *env, jclass clazz, jint size, jobject buffers, jint buffers_position) {
	const GLenum *buffers_address = ((const GLenum *)(*env)->GetDirectBufferAddress(env, buffers)) + buffers_position;
	glDrawBuffers(size, buffers_address);
}

static jint JNICALL Java_org_lwjgl_opengl_GL20_nglGetAttribLocation(JNIEnv *env, jclass clazz, jint program, jobject name, jint name_position) {
	const GLchar *name_address = ((const GLchar *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	GLint __result = glGetAttribLocation(program, name_address);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetActiveAttrib(JNIEnv *env, jclass clazz, jint program, jint index, jint maxLength, jobject length, jint length_position, jobject size, jint size_position, jobject type, jint type_position, jobject name, jint name_position) {
	GLsizei *length_address = ((GLsizei *)safeGetBufferAddress(env, length)) + length_position;
	GLint *size_address = ((GLint *)(*env)->GetDirectBufferAddress(env, size)) + size_position;
	GLenum *type_address = ((GLenum *)(*env)->GetDirectBufferAddress(env, type)) + type_position;
	const GLchar *name_address = ((const GLchar *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glGetActiveAttrib(program, index, maxLength, length_address, size_address, type_address, name_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglBindAttribLocation(JNIEnv *env, jclass clazz, jint program, jint index, jobject name, jint name_position) {
	const GLchar *name_address = ((const GLchar *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glBindAttribLocation(program, index, name_address);
}

static jobject JNICALL Java_org_lwjgl_opengl_GL20_nglGetVertexAttribPointerv(JNIEnv *env, jclass clazz, jint index, jint pname, jint result_size) {
	GLvoid * __result;
	glGetVertexAttribPointerv(index, pname, &__result);
	return safeNewBuffer(env, __result, result_size);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetVertexAttribiv(JNIEnv *env, jclass clazz, jint index, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetVertexAttribiv(index, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetVertexAttribfv(JNIEnv *env, jclass clazz, jint index, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetVertexAttribfv(index, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glDisableVertexAttribArray(JNIEnv *env, jclass clazz, jint index) {
	glDisableVertexAttribArray(index);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glEnableVertexAttribArray(JNIEnv *env, jclass clazz, jint index) {
	glEnableVertexAttribArray(index);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglVertexAttribPointer(JNIEnv *env, jclass clazz, jint index, jint size, jint type, jboolean normalized, jint stride, jobject buffer, jint buffer_position) {
	const GLvoid *buffer_address = ((const GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, buffer)) + buffer_position));
	glVertexAttribPointer(index, size, type, normalized, stride, buffer_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglVertexAttribPointerBO(JNIEnv *env, jclass clazz, jint index, jint size, jint type, jboolean normalized, jint stride, jint buffer_buffer_offset) {
	const GLvoid *buffer_address = ((const GLvoid *)offsetToPointer(buffer_buffer_offset));
	glVertexAttribPointer(index, size, type, normalized, stride, buffer_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glVertexAttrib4Nub(JNIEnv *env, jclass clazz, jint index, jbyte x, jbyte y, jbyte z, jbyte w) {
	glVertexAttrib4Nub(index, x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glVertexAttrib4f(JNIEnv *env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z, jfloat w) {
	glVertexAttrib4f(index, x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glVertexAttrib4s(JNIEnv *env, jclass clazz, jint index, jshort x, jshort y, jshort z, jshort w) {
	glVertexAttrib4s(index, x, y, z, w);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glVertexAttrib3f(JNIEnv *env, jclass clazz, jint index, jfloat x, jfloat y, jfloat z) {
	glVertexAttrib3f(index, x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glVertexAttrib3s(JNIEnv *env, jclass clazz, jint index, jshort x, jshort y, jshort z) {
	glVertexAttrib3s(index, x, y, z);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glVertexAttrib2f(JNIEnv *env, jclass clazz, jint index, jfloat x, jfloat y) {
	glVertexAttrib2f(index, x, y);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glVertexAttrib2s(JNIEnv *env, jclass clazz, jint index, jshort x, jshort y) {
	glVertexAttrib2s(index, x, y);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glVertexAttrib1f(JNIEnv *env, jclass clazz, jint index, jfloat x) {
	glVertexAttrib1f(index, x);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glVertexAttrib1s(JNIEnv *env, jclass clazz, jint index, jshort x) {
	glVertexAttrib1s(index, x);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetShaderSource(JNIEnv *env, jclass clazz, jint shader, jint maxLength, jobject length, jint length_position, jobject source, jint source_position) {
	GLsizei *length_address = ((GLsizei *)safeGetBufferAddress(env, length)) + length_position;
	GLchar *source_address = ((GLchar *)(*env)->GetDirectBufferAddress(env, source)) + source_position;
	glGetShaderSource(shader, maxLength, length_address, source_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetUniformiv(JNIEnv *env, jclass clazz, jint program, jint location, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetUniformiv(program, location, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetUniformfv(JNIEnv *env, jclass clazz, jint program, jint location, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetUniformfv(program, location, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetActiveUniform(JNIEnv *env, jclass clazz, jint program, jint index, jint maxLength, jobject length, jint length_position, jobject size, jint size_position, jobject type, jint type_position, jobject name, jint name_position) {
	GLsizei *length_address = ((GLsizei *)safeGetBufferAddress(env, length)) + length_position;
	GLsizei *size_address = ((GLsizei *)(*env)->GetDirectBufferAddress(env, size)) + size_position;
	GLenum *type_address = ((GLenum *)(*env)->GetDirectBufferAddress(env, type)) + type_position;
	GLchar *name_address = ((GLchar *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	glGetActiveUniform(program, index, maxLength, length_address, size_address, type_address, name_address);
}

static jint JNICALL Java_org_lwjgl_opengl_GL20_nglGetUniformLocation(JNIEnv *env, jclass clazz, jint program, jobject name, jint name_position) {
	const GLchar *name_address = ((const GLchar *)(*env)->GetDirectBufferAddress(env, name)) + name_position;
	GLint __result = glGetUniformLocation(program, name_address);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetAttachedShaders(JNIEnv *env, jclass clazz, jint program, jint maxCount, jobject count, jint count_position, jobject shaders, jint shaders_position) {
	GLsizei *count_address = ((GLsizei *)safeGetBufferAddress(env, count)) + count_position;
	GLuint *shaders_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, shaders)) + shaders_position;
	glGetAttachedShaders(program, maxCount, count_address, shaders_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetProgramInfoLog(JNIEnv *env, jclass clazz, jint program, jint maxLength, jobject length, jint length_position, jobject infoLog, jint infoLog_position) {
	GLsizei *length_address = ((GLsizei *)safeGetBufferAddress(env, length)) + length_position;
	GLchar *infoLog_address = ((GLchar *)(*env)->GetDirectBufferAddress(env, infoLog)) + infoLog_position;
	glGetProgramInfoLog(program, maxLength, length_address, infoLog_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetShaderInfoLog(JNIEnv *env, jclass clazz, jint shader, jint maxLength, jobject length, jint length_position, jobject infoLog, jint infoLog_position) {
	GLsizei *length_address = ((GLsizei *)safeGetBufferAddress(env, length)) + length_position;
	GLchar *infoLog_address = ((GLchar *)(*env)->GetDirectBufferAddress(env, infoLog)) + infoLog_position;
	glGetShaderInfoLog(shader, maxLength, length_address, infoLog_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetProgramiv(JNIEnv *env, jclass clazz, jint program, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetProgramiv(program, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetProgramfv(JNIEnv *env, jclass clazz, jint program, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetProgramfv(program, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetShaderiv(JNIEnv *env, jclass clazz, jint shader, jint pname, jobject params, jint params_position) {
	GLint *params_address = ((GLint *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetShaderiv(shader, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglGetShaderfv(JNIEnv *env, jclass clazz, jint shader, jint pname, jobject params, jint params_position) {
	GLfloat *params_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, params)) + params_position;
	glGetShaderfv(shader, pname, params_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniformMatrix4fv(JNIEnv *env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matrices_position) {
	GLfloat *matrices_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, matrices)) + matrices_position;
	glUniformMatrix4fv(location, count, transpose, matrices_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniformMatrix3fv(JNIEnv *env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matrices_position) {
	GLfloat *matrices_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, matrices)) + matrices_position;
	glUniformMatrix3fv(location, count, transpose, matrices_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniformMatrix2fv(JNIEnv *env, jclass clazz, jint location, jint count, jboolean transpose, jobject matrices, jint matrices_position) {
	GLfloat *matrices_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, matrices)) + matrices_position;
	glUniformMatrix2fv(location, count, transpose, matrices_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform4iv(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLint *values_address = ((GLint *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform4iv(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform3iv(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLint *values_address = ((GLint *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform3iv(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform2iv(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLint *values_address = ((GLint *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform2iv(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform1iv(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLint *values_address = ((GLint *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform1iv(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform4fv(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLfloat *values_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform4fv(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform3fv(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLfloat *values_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform3fv(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform2fv(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLfloat *values_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform2fv(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglUniform1fv(JNIEnv *env, jclass clazz, jint location, jint count, jobject values, jint values_position) {
	GLfloat *values_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, values)) + values_position;
	glUniform1fv(location, count, values_address);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform4i(JNIEnv *env, jclass clazz, jint location, jint v0, jint v1, jint v2, jint v3) {
	glUniform4i(location, v0, v1, v2, v3);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform3i(JNIEnv *env, jclass clazz, jint location, jint v0, jint v1, jint v2) {
	glUniform3i(location, v0, v1, v2);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform2i(JNIEnv *env, jclass clazz, jint location, jint v0, jint v1) {
	glUniform2i(location, v0, v1);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform1i(JNIEnv *env, jclass clazz, jint location, jint v0) {
	glUniform1i(location, v0);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform4f(JNIEnv *env, jclass clazz, jint location, jfloat v0, jfloat v1, jfloat v2, jfloat v3) {
	glUniform4f(location, v0, v1, v2, v3);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform3f(JNIEnv *env, jclass clazz, jint location, jfloat v0, jfloat v1, jfloat v2) {
	glUniform3f(location, v0, v1, v2);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform2f(JNIEnv *env, jclass clazz, jint location, jfloat v0, jfloat v1) {
	glUniform2f(location, v0, v1);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glUniform1f(JNIEnv *env, jclass clazz, jint location, jfloat v0) {
	glUniform1f(location, v0);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glDeleteProgram(JNIEnv *env, jclass clazz, jint program) {
	glDeleteProgram(program);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glValidateProgram(JNIEnv *env, jclass clazz, jint program) {
	glValidateProgram(program);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glUseProgram(JNIEnv *env, jclass clazz, jint program) {
	glUseProgram(program);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glLinkProgram(JNIEnv *env, jclass clazz, jint program) {
	glLinkProgram(program);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glDetachShader(JNIEnv *env, jclass clazz, jint program, jint shader) {
	glDetachShader(program, shader);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glAttachShader(JNIEnv *env, jclass clazz, jint program, jint shader) {
	glAttachShader(program, shader);
}

static jboolean JNICALL Java_org_lwjgl_opengl_GL20_glIsProgram(JNIEnv *env, jclass clazz, jint program) {
	GLboolean __result = glIsProgram(program);
	return __result;
}

static jint JNICALL Java_org_lwjgl_opengl_GL20_glCreateProgram(JNIEnv *env, jclass clazz) {
	GLint __result = glCreateProgram();
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glDeleteShader(JNIEnv *env, jclass clazz, jint shader) {
	glDeleteShader(shader);
}

static void JNICALL Java_org_lwjgl_opengl_GL20_glCompileShader(JNIEnv *env, jclass clazz, jint shader) {
	glCompileShader(shader);
}

static jboolean JNICALL Java_org_lwjgl_opengl_GL20_glIsShader(JNIEnv *env, jclass clazz, jint shader) {
	GLboolean __result = glIsShader(shader);
	return __result;
}

static jint JNICALL Java_org_lwjgl_opengl_GL20_glCreateShader(JNIEnv *env, jclass clazz, jint type) {
	GLint __result = glCreateShader(type);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_GL20_nglShaderSource(JNIEnv *env, jclass clazz, jint shader, jint count, jobject string, jint string_position, jint length) {
	const GLchar *string_address = ((const GLchar *)(*env)->GetDirectBufferAddress(env, string)) + string_position;
	glShaderSource(shader, count, &string_address, &length);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GL20_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glBlendEquationSeparate", "(II)V", (void *)&Java_org_lwjgl_opengl_GL20_glBlendEquationSeparate, "glBlendEquationSeparate", (void *)&glBlendEquationSeparate},
		{"glStencilMaskSeparate", "(II)V", (void *)&Java_org_lwjgl_opengl_GL20_glStencilMaskSeparate, "glStencilMaskSeparate", (void *)&glStencilMaskSeparate},
		{"glStencilFuncSeparate", "(IIII)V", (void *)&Java_org_lwjgl_opengl_GL20_glStencilFuncSeparate, "glStencilFuncSeparate", (void *)&glStencilFuncSeparate},
		{"glStencilOpSeparate", "(IIII)V", (void *)&Java_org_lwjgl_opengl_GL20_glStencilOpSeparate, "glStencilOpSeparate", (void *)&glStencilOpSeparate},
		{"nglDrawBuffers", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglDrawBuffers, "glDrawBuffers", (void *)&glDrawBuffers},
		{"nglGetAttribLocation", "(ILjava/nio/ByteBuffer;I)I", (void *)&Java_org_lwjgl_opengl_GL20_nglGetAttribLocation, "glGetAttribLocation", (void *)&glGetAttribLocation},
		{"nglGetActiveAttrib", "(IIILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglGetActiveAttrib, "glGetActiveAttrib", (void *)&glGetActiveAttrib},
		{"nglBindAttribLocation", "(IILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglBindAttribLocation, "glBindAttribLocation", (void *)&glBindAttribLocation},
		{"nglGetVertexAttribPointerv", "(III)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengl_GL20_nglGetVertexAttribPointerv, "glGetVertexAttribPointerv", (void *)&glGetVertexAttribPointerv},
		{"nglGetVertexAttribiv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglGetVertexAttribiv, "glGetVertexAttribiv", (void *)&glGetVertexAttribiv},
		{"nglGetVertexAttribfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglGetVertexAttribfv, "glGetVertexAttribfv", (void *)&glGetVertexAttribfv},
		{"glDisableVertexAttribArray", "(I)V", (void *)&Java_org_lwjgl_opengl_GL20_glDisableVertexAttribArray, "glDisableVertexAttribArray", (void *)&glDisableVertexAttribArray},
		{"glEnableVertexAttribArray", "(I)V", (void *)&Java_org_lwjgl_opengl_GL20_glEnableVertexAttribArray, "glEnableVertexAttribArray", (void *)&glEnableVertexAttribArray},
		{"nglVertexAttribPointer", "(IIIZILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglVertexAttribPointer, "glVertexAttribPointer", (void *)&glVertexAttribPointer},
		{"nglVertexAttribPointerBO", "(IIIZII)V", (void *)&Java_org_lwjgl_opengl_GL20_nglVertexAttribPointerBO, "glVertexAttribPointer", (void *)&glVertexAttribPointer},
		{"glVertexAttrib4Nub", "(IBBBB)V", (void *)&Java_org_lwjgl_opengl_GL20_glVertexAttrib4Nub, "glVertexAttrib4Nub", (void *)&glVertexAttrib4Nub},
		{"glVertexAttrib4f", "(IFFFF)V", (void *)&Java_org_lwjgl_opengl_GL20_glVertexAttrib4f, "glVertexAttrib4f", (void *)&glVertexAttrib4f},
		{"glVertexAttrib4s", "(ISSSS)V", (void *)&Java_org_lwjgl_opengl_GL20_glVertexAttrib4s, "glVertexAttrib4s", (void *)&glVertexAttrib4s},
		{"glVertexAttrib3f", "(IFFF)V", (void *)&Java_org_lwjgl_opengl_GL20_glVertexAttrib3f, "glVertexAttrib3f", (void *)&glVertexAttrib3f},
		{"glVertexAttrib3s", "(ISSS)V", (void *)&Java_org_lwjgl_opengl_GL20_glVertexAttrib3s, "glVertexAttrib3s", (void *)&glVertexAttrib3s},
		{"glVertexAttrib2f", "(IFF)V", (void *)&Java_org_lwjgl_opengl_GL20_glVertexAttrib2f, "glVertexAttrib2f", (void *)&glVertexAttrib2f},
		{"glVertexAttrib2s", "(ISS)V", (void *)&Java_org_lwjgl_opengl_GL20_glVertexAttrib2s, "glVertexAttrib2s", (void *)&glVertexAttrib2s},
		{"glVertexAttrib1f", "(IF)V", (void *)&Java_org_lwjgl_opengl_GL20_glVertexAttrib1f, "glVertexAttrib1f", (void *)&glVertexAttrib1f},
		{"glVertexAttrib1s", "(IS)V", (void *)&Java_org_lwjgl_opengl_GL20_glVertexAttrib1s, "glVertexAttrib1s", (void *)&glVertexAttrib1s},
		{"nglGetShaderSource", "(IILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglGetShaderSource, "glGetShaderSource", (void *)&glGetShaderSource},
		{"nglGetUniformiv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglGetUniformiv, "glGetUniformiv", (void *)&glGetUniformiv},
		{"nglGetUniformfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglGetUniformfv, "glGetUniformfv", (void *)&glGetUniformfv},
		{"nglGetActiveUniform", "(IIILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglGetActiveUniform, "glGetActiveUniform", (void *)&glGetActiveUniform},
		{"nglGetUniformLocation", "(ILjava/nio/ByteBuffer;I)I", (void *)&Java_org_lwjgl_opengl_GL20_nglGetUniformLocation, "glGetUniformLocation", (void *)&glGetUniformLocation},
		{"nglGetAttachedShaders", "(IILjava/nio/IntBuffer;ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglGetAttachedShaders, "glGetAttachedShaders", (void *)&glGetAttachedShaders},
		{"nglGetProgramInfoLog", "(IILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglGetProgramInfoLog, "glGetProgramInfoLog", (void *)&glGetProgramInfoLog},
		{"nglGetShaderInfoLog", "(IILjava/nio/IntBuffer;ILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglGetShaderInfoLog, "glGetShaderInfoLog", (void *)&glGetShaderInfoLog},
		{"nglGetProgramiv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglGetProgramiv, "glGetProgramiv", (void *)&glGetProgramiv},
		{"nglGetProgramfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglGetProgramfv, "glGetProgramfv", (void *)&glGetProgramfv},
		{"nglGetShaderiv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglGetShaderiv, "glGetShaderiv", (void *)&glGetShaderiv},
		{"nglGetShaderfv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglGetShaderfv, "glGetShaderfv", (void *)&glGetShaderfv},
		{"nglUniformMatrix4fv", "(IIZLjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglUniformMatrix4fv, "glUniformMatrix4fv", (void *)&glUniformMatrix4fv},
		{"nglUniformMatrix3fv", "(IIZLjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglUniformMatrix3fv, "glUniformMatrix3fv", (void *)&glUniformMatrix3fv},
		{"nglUniformMatrix2fv", "(IIZLjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglUniformMatrix2fv, "glUniformMatrix2fv", (void *)&glUniformMatrix2fv},
		{"nglUniform4iv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglUniform4iv, "glUniform4iv", (void *)&glUniform4iv},
		{"nglUniform3iv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglUniform3iv, "glUniform3iv", (void *)&glUniform3iv},
		{"nglUniform2iv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglUniform2iv, "glUniform2iv", (void *)&glUniform2iv},
		{"nglUniform1iv", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglUniform1iv, "glUniform1iv", (void *)&glUniform1iv},
		{"nglUniform4fv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglUniform4fv, "glUniform4fv", (void *)&glUniform4fv},
		{"nglUniform3fv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglUniform3fv, "glUniform3fv", (void *)&glUniform3fv},
		{"nglUniform2fv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglUniform2fv, "glUniform2fv", (void *)&glUniform2fv},
		{"nglUniform1fv", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_GL20_nglUniform1fv, "glUniform1fv", (void *)&glUniform1fv},
		{"glUniform4i", "(IIIII)V", (void *)&Java_org_lwjgl_opengl_GL20_glUniform4i, "glUniform4i", (void *)&glUniform4i},
		{"glUniform3i", "(IIII)V", (void *)&Java_org_lwjgl_opengl_GL20_glUniform3i, "glUniform3i", (void *)&glUniform3i},
		{"glUniform2i", "(III)V", (void *)&Java_org_lwjgl_opengl_GL20_glUniform2i, "glUniform2i", (void *)&glUniform2i},
		{"glUniform1i", "(II)V", (void *)&Java_org_lwjgl_opengl_GL20_glUniform1i, "glUniform1i", (void *)&glUniform1i},
		{"glUniform4f", "(IFFFF)V", (void *)&Java_org_lwjgl_opengl_GL20_glUniform4f, "glUniform4f", (void *)&glUniform4f},
		{"glUniform3f", "(IFFF)V", (void *)&Java_org_lwjgl_opengl_GL20_glUniform3f, "glUniform3f", (void *)&glUniform3f},
		{"glUniform2f", "(IFF)V", (void *)&Java_org_lwjgl_opengl_GL20_glUniform2f, "glUniform2f", (void *)&glUniform2f},
		{"glUniform1f", "(IF)V", (void *)&Java_org_lwjgl_opengl_GL20_glUniform1f, "glUniform1f", (void *)&glUniform1f},
		{"glDeleteProgram", "(I)V", (void *)&Java_org_lwjgl_opengl_GL20_glDeleteProgram, "glDeleteProgram", (void *)&glDeleteProgram},
		{"glValidateProgram", "(I)V", (void *)&Java_org_lwjgl_opengl_GL20_glValidateProgram, "glValidateProgram", (void *)&glValidateProgram},
		{"glUseProgram", "(I)V", (void *)&Java_org_lwjgl_opengl_GL20_glUseProgram, "glUseProgram", (void *)&glUseProgram},
		{"glLinkProgram", "(I)V", (void *)&Java_org_lwjgl_opengl_GL20_glLinkProgram, "glLinkProgram", (void *)&glLinkProgram},
		{"glDetachShader", "(II)V", (void *)&Java_org_lwjgl_opengl_GL20_glDetachShader, "glDetachShader", (void *)&glDetachShader},
		{"glAttachShader", "(II)V", (void *)&Java_org_lwjgl_opengl_GL20_glAttachShader, "glAttachShader", (void *)&glAttachShader},
		{"glIsProgram", "(I)Z", (void *)&Java_org_lwjgl_opengl_GL20_glIsProgram, "glIsProgram", (void *)&glIsProgram},
		{"glCreateProgram", "()I", (void *)&Java_org_lwjgl_opengl_GL20_glCreateProgram, "glCreateProgram", (void *)&glCreateProgram},
		{"glDeleteShader", "(I)V", (void *)&Java_org_lwjgl_opengl_GL20_glDeleteShader, "glDeleteShader", (void *)&glDeleteShader},
		{"glCompileShader", "(I)V", (void *)&Java_org_lwjgl_opengl_GL20_glCompileShader, "glCompileShader", (void *)&glCompileShader},
		{"glIsShader", "(I)Z", (void *)&Java_org_lwjgl_opengl_GL20_glIsShader, "glIsShader", (void *)&glIsShader},
		{"glCreateShader", "(I)I", (void *)&Java_org_lwjgl_opengl_GL20_glCreateShader, "glCreateShader", (void *)&glCreateShader},
		{"nglShaderSource", "(IILjava/nio/ByteBuffer;II)V", (void *)&Java_org_lwjgl_opengl_GL20_nglShaderSource, "glShaderSource", (void *)&glShaderSource}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
