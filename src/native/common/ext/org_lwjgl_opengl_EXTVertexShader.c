/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetLocalConstantFloatvEXTPROC) (GLuint id, GLenum value, GLfloat * pbData);
typedef void (APIENTRY *glGetLocalConstantIntegervEXTPROC) (GLuint id, GLenum value, GLint * pbData);
typedef void (APIENTRY *glGetLocalConstantBooleanvEXTPROC) (GLuint id, GLenum value, GLbyte * pbData);
typedef void (APIENTRY *glGetInvariantFloatvEXTPROC) (GLuint id, GLenum value, GLfloat * pbData);
typedef void (APIENTRY *glGetInvariantIntegervEXTPROC) (GLuint id, GLenum value, GLint * pbData);
typedef void (APIENTRY *glGetInvariantBooleanvEXTPROC) (GLuint id, GLenum value, GLbyte * pbData);
typedef void (APIENTRY *glGetVariantPointervEXTPROC) (GLuint id, GLenum value, GLvoid ** pbData);
typedef void (APIENTRY *glGetVariantFloatvEXTPROC) (GLuint id, GLenum value, GLfloat * pbData);
typedef void (APIENTRY *glGetVariantIntegervEXTPROC) (GLuint id, GLenum value, GLint * pbData);
typedef void (APIENTRY *glGetVariantBooleanvEXTPROC) (GLuint id, GLenum value, GLbyte * pbData);
typedef GLboolean (APIENTRY *glIsVariantEnabledEXTPROC) (GLuint id, GLenum cap);
typedef GLuint (APIENTRY *glBindParameterEXTPROC) (GLenum value);
typedef GLuint (APIENTRY *glBindTextureUnitParameterEXTPROC) (GLenum unit, GLenum value);
typedef GLuint (APIENTRY *glBindTexGenParameterEXTPROC) (GLenum unit, GLenum coord, GLenum value);
typedef GLuint (APIENTRY *glBindMaterialParameterEXTPROC) (GLenum face, GLenum value);
typedef GLuint (APIENTRY *glBindLightParameterEXTPROC) (GLenum light, GLenum value);
typedef void (APIENTRY *glDisableVariantClientStateEXTPROC) (GLuint id);
typedef void (APIENTRY *glEnableVariantClientStateEXTPROC) (GLuint id);
typedef void (APIENTRY *glVariantPointerEXTPROC) (GLuint id, GLenum type, GLuint stride, GLvoid * pAddr);
typedef void (APIENTRY *glVariantuivEXTPROC) (GLuint id, GLuint * pAddr);
typedef void (APIENTRY *glVariantusvEXTPROC) (GLuint id, GLushort * pAddr);
typedef void (APIENTRY *glVariantubvEXTPROC) (GLuint id, GLubyte * pAddr);
typedef void (APIENTRY *glVariantfvEXTPROC) (GLuint id, GLfloat * pAddr);
typedef void (APIENTRY *glVariantivEXTPROC) (GLuint id, GLint * pAddr);
typedef void (APIENTRY *glVariantsvEXTPROC) (GLuint id, GLshort * pAddr);
typedef void (APIENTRY *glVariantbvEXTPROC) (GLuint id, GLbyte * pAddr);
typedef void (APIENTRY *glSetLocalConstantEXTPROC) (GLuint id, GLenum type, GLvoid * pAddr);
typedef void (APIENTRY *glSetInvariantEXTPROC) (GLuint id, GLenum type, GLvoid * pAddr);
typedef GLuint (APIENTRY *glGenSymbolsEXTPROC) (GLenum dataType, GLenum storageType, GLenum range, GLuint components);
typedef void (APIENTRY *glExtractComponentEXTPROC) (GLuint res, GLuint src, GLuint num);
typedef void (APIENTRY *glInsertComponentEXTPROC) (GLuint res, GLuint src, GLuint num);
typedef void (APIENTRY *glWriteMaskEXTPROC) (GLuint res, GLuint in, GLenum outX, GLenum outY, GLenum outZ, GLenum outW);
typedef void (APIENTRY *glSwizzleEXTPROC) (GLuint res, GLuint in, GLenum outX, GLenum outY, GLenum outZ, GLenum outW);
typedef void (APIENTRY *glShaderOp3EXTPROC) (GLenum op, GLuint res, GLuint arg1, GLuint arg2, GLuint arg3);
typedef void (APIENTRY *glShaderOp2EXTPROC) (GLenum op, GLuint res, GLuint arg1, GLuint arg2);
typedef void (APIENTRY *glShaderOp1EXTPROC) (GLenum op, GLuint res, GLuint arg1);
typedef void (APIENTRY *glDeleteVertexShaderEXTPROC) (GLuint id);
typedef GLuint (APIENTRY *glGenVertexShadersEXTPROC) (GLuint range);
typedef void (APIENTRY *glBindVertexShaderEXTPROC) (GLuint id);
typedef void (APIENTRY *glEndVertexShaderEXTPROC) ();
typedef void (APIENTRY *glBeginVertexShaderEXTPROC) ();

static glGetLocalConstantFloatvEXTPROC glGetLocalConstantFloatvEXT;
static glGetLocalConstantIntegervEXTPROC glGetLocalConstantIntegervEXT;
static glGetLocalConstantBooleanvEXTPROC glGetLocalConstantBooleanvEXT;
static glGetInvariantFloatvEXTPROC glGetInvariantFloatvEXT;
static glGetInvariantIntegervEXTPROC glGetInvariantIntegervEXT;
static glGetInvariantBooleanvEXTPROC glGetInvariantBooleanvEXT;
static glGetVariantPointervEXTPROC glGetVariantPointervEXT;
static glGetVariantFloatvEXTPROC glGetVariantFloatvEXT;
static glGetVariantIntegervEXTPROC glGetVariantIntegervEXT;
static glGetVariantBooleanvEXTPROC glGetVariantBooleanvEXT;
static glIsVariantEnabledEXTPROC glIsVariantEnabledEXT;
static glBindParameterEXTPROC glBindParameterEXT;
static glBindTextureUnitParameterEXTPROC glBindTextureUnitParameterEXT;
static glBindTexGenParameterEXTPROC glBindTexGenParameterEXT;
static glBindMaterialParameterEXTPROC glBindMaterialParameterEXT;
static glBindLightParameterEXTPROC glBindLightParameterEXT;
static glDisableVariantClientStateEXTPROC glDisableVariantClientStateEXT;
static glEnableVariantClientStateEXTPROC glEnableVariantClientStateEXT;
static glVariantPointerEXTPROC glVariantPointerEXT;
static glVariantuivEXTPROC glVariantuivEXT;
static glVariantusvEXTPROC glVariantusvEXT;
static glVariantubvEXTPROC glVariantubvEXT;
static glVariantfvEXTPROC glVariantfvEXT;
static glVariantivEXTPROC glVariantivEXT;
static glVariantsvEXTPROC glVariantsvEXT;
static glVariantbvEXTPROC glVariantbvEXT;
static glSetLocalConstantEXTPROC glSetLocalConstantEXT;
static glSetInvariantEXTPROC glSetInvariantEXT;
static glGenSymbolsEXTPROC glGenSymbolsEXT;
static glExtractComponentEXTPROC glExtractComponentEXT;
static glInsertComponentEXTPROC glInsertComponentEXT;
static glWriteMaskEXTPROC glWriteMaskEXT;
static glSwizzleEXTPROC glSwizzleEXT;
static glShaderOp3EXTPROC glShaderOp3EXT;
static glShaderOp2EXTPROC glShaderOp2EXT;
static glShaderOp1EXTPROC glShaderOp1EXT;
static glDeleteVertexShaderEXTPROC glDeleteVertexShaderEXT;
static glGenVertexShadersEXTPROC glGenVertexShadersEXT;
static glBindVertexShaderEXTPROC glBindVertexShaderEXT;
static glEndVertexShaderEXTPROC glEndVertexShaderEXT;
static glBeginVertexShaderEXTPROC glBeginVertexShaderEXT;

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetLocalConstantFloatvEXT(JNIEnv *env, jclass clazz, jint id, jint value, jobject pbData, jint pbData_position) {
	GLfloat *pbData_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, pbData)) + pbData_position;
	glGetLocalConstantFloatvEXT(id, value, pbData_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetLocalConstantIntegervEXT(JNIEnv *env, jclass clazz, jint id, jint value, jobject pbData, jint pbData_position) {
	GLint *pbData_address = ((GLint *)(*env)->GetDirectBufferAddress(env, pbData)) + pbData_position;
	glGetLocalConstantIntegervEXT(id, value, pbData_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetLocalConstantBooleanvEXT(JNIEnv *env, jclass clazz, jint id, jint value, jobject pbData, jint pbData_position) {
	GLbyte *pbData_address = ((GLbyte *)(*env)->GetDirectBufferAddress(env, pbData)) + pbData_position;
	glGetLocalConstantBooleanvEXT(id, value, pbData_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetInvariantFloatvEXT(JNIEnv *env, jclass clazz, jint id, jint value, jobject pbData, jint pbData_position) {
	GLfloat *pbData_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, pbData)) + pbData_position;
	glGetInvariantFloatvEXT(id, value, pbData_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetInvariantIntegervEXT(JNIEnv *env, jclass clazz, jint id, jint value, jobject pbData, jint pbData_position) {
	GLint *pbData_address = ((GLint *)(*env)->GetDirectBufferAddress(env, pbData)) + pbData_position;
	glGetInvariantIntegervEXT(id, value, pbData_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetInvariantBooleanvEXT(JNIEnv *env, jclass clazz, jint id, jint value, jobject pbData, jint pbData_position) {
	GLbyte *pbData_address = ((GLbyte *)(*env)->GetDirectBufferAddress(env, pbData)) + pbData_position;
	glGetInvariantBooleanvEXT(id, value, pbData_address);
}

static jobject JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantPointervEXT(JNIEnv *env, jclass clazz, jint id, jint value, jint result_size) {
	GLvoid * __result;
	glGetVariantPointervEXT(id, value, &__result);
	return safeNewBuffer(env, __result, result_size);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantFloatvEXT(JNIEnv *env, jclass clazz, jint id, jint value, jobject pbData, jint pbData_position) {
	GLfloat *pbData_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, pbData)) + pbData_position;
	glGetVariantFloatvEXT(id, value, pbData_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantIntegervEXT(JNIEnv *env, jclass clazz, jint id, jint value, jobject pbData, jint pbData_position) {
	GLint *pbData_address = ((GLint *)(*env)->GetDirectBufferAddress(env, pbData)) + pbData_position;
	glGetVariantIntegervEXT(id, value, pbData_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantBooleanvEXT(JNIEnv *env, jclass clazz, jint id, jint value, jobject pbData, jint pbData_position) {
	GLbyte *pbData_address = ((GLbyte *)(*env)->GetDirectBufferAddress(env, pbData)) + pbData_position;
	glGetVariantBooleanvEXT(id, value, pbData_address);
}

static jboolean JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glIsVariantEnabledEXT(JNIEnv *env, jclass clazz, jint id, jint cap) {
	GLboolean __result = glIsVariantEnabledEXT(id, cap);
	return __result;
}

static jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindParameterEXT(JNIEnv *env, jclass clazz, jint value) {
	GLuint __result = glBindParameterEXT(value);
	return __result;
}

static jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindTextureUnitParameterEXT(JNIEnv *env, jclass clazz, jint unit, jint value) {
	GLuint __result = glBindTextureUnitParameterEXT(unit, value);
	return __result;
}

static jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindTexGenParameterEXT(JNIEnv *env, jclass clazz, jint unit, jint coord, jint value) {
	GLuint __result = glBindTexGenParameterEXT(unit, coord, value);
	return __result;
}

static jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindMaterialParameterEXT(JNIEnv *env, jclass clazz, jint face, jint value) {
	GLuint __result = glBindMaterialParameterEXT(face, value);
	return __result;
}

static jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindLightParameterEXT(JNIEnv *env, jclass clazz, jint light, jint value) {
	GLuint __result = glBindLightParameterEXT(light, value);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glDisableVariantClientStateEXT(JNIEnv *env, jclass clazz, jint id) {
	glDisableVariantClientStateEXT(id);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glEnableVariantClientStateEXT(JNIEnv *env, jclass clazz, jint id) {
	glEnableVariantClientStateEXT(id);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantPointerEXT(JNIEnv *env, jclass clazz, jint id, jint type, jint stride, jobject pAddr, jint pAddr_position) {
	GLvoid *pAddr_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pAddr)) + pAddr_position));
	glVariantPointerEXT(id, type, stride, pAddr_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantPointerEXTBO(JNIEnv *env, jclass clazz, jint id, jint type, jint stride, jint pAddr_buffer_offset) {
	GLvoid *pAddr_address = ((GLvoid *)offsetToPointer(pAddr_buffer_offset));
	glVariantPointerEXT(id, type, stride, pAddr_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantuivEXT(JNIEnv *env, jclass clazz, jint id, jobject pAddr, jint pAddr_position) {
	GLuint *pAddr_address = ((GLuint *)(*env)->GetDirectBufferAddress(env, pAddr)) + pAddr_position;
	glVariantuivEXT(id, pAddr_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantusvEXT(JNIEnv *env, jclass clazz, jint id, jobject pAddr, jint pAddr_position) {
	GLushort *pAddr_address = ((GLushort *)(*env)->GetDirectBufferAddress(env, pAddr)) + pAddr_position;
	glVariantusvEXT(id, pAddr_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantubvEXT(JNIEnv *env, jclass clazz, jint id, jobject pAddr, jint pAddr_position) {
	GLubyte *pAddr_address = ((GLubyte *)(*env)->GetDirectBufferAddress(env, pAddr)) + pAddr_position;
	glVariantubvEXT(id, pAddr_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantfvEXT(JNIEnv *env, jclass clazz, jint id, jobject pAddr, jint pAddr_position) {
	GLfloat *pAddr_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, pAddr)) + pAddr_position;
	glVariantfvEXT(id, pAddr_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantivEXT(JNIEnv *env, jclass clazz, jint id, jobject pAddr, jint pAddr_position) {
	GLint *pAddr_address = ((GLint *)(*env)->GetDirectBufferAddress(env, pAddr)) + pAddr_position;
	glVariantivEXT(id, pAddr_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantsvEXT(JNIEnv *env, jclass clazz, jint id, jobject pAddr, jint pAddr_position) {
	GLshort *pAddr_address = ((GLshort *)(*env)->GetDirectBufferAddress(env, pAddr)) + pAddr_position;
	glVariantsvEXT(id, pAddr_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglVariantbvEXT(JNIEnv *env, jclass clazz, jint id, jobject pAddr, jint pAddr_position) {
	GLbyte *pAddr_address = ((GLbyte *)(*env)->GetDirectBufferAddress(env, pAddr)) + pAddr_position;
	glVariantbvEXT(id, pAddr_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglSetLocalConstantEXT(JNIEnv *env, jclass clazz, jint id, jint type, jobject pAddr, jint pAddr_position) {
	GLvoid *pAddr_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pAddr)) + pAddr_position));
	glSetLocalConstantEXT(id, type, pAddr_address);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_nglSetInvariantEXT(JNIEnv *env, jclass clazz, jint id, jint type, jobject pAddr, jint pAddr_position) {
	GLvoid *pAddr_address = ((GLvoid *)(((char *)(*env)->GetDirectBufferAddress(env, pAddr)) + pAddr_position));
	glSetInvariantEXT(id, type, pAddr_address);
}

static jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glGenSymbolsEXT(JNIEnv *env, jclass clazz, jint dataType, jint storageType, jint range, jint components) {
	GLuint __result = glGenSymbolsEXT(dataType, storageType, range, components);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glExtractComponentEXT(JNIEnv *env, jclass clazz, jint res, jint src, jint num) {
	glExtractComponentEXT(res, src, num);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glInsertComponentEXT(JNIEnv *env, jclass clazz, jint res, jint src, jint num) {
	glInsertComponentEXT(res, src, num);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glWriteMaskEXT(JNIEnv *env, jclass clazz, jint res, jint in, jint outX, jint outY, jint outZ, jint outW) {
	glWriteMaskEXT(res, in, outX, outY, outZ, outW);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glSwizzleEXT(JNIEnv *env, jclass clazz, jint res, jint in, jint outX, jint outY, jint outZ, jint outW) {
	glSwizzleEXT(res, in, outX, outY, outZ, outW);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glShaderOp3EXT(JNIEnv *env, jclass clazz, jint op, jint res, jint arg1, jint arg2, jint arg3) {
	glShaderOp3EXT(op, res, arg1, arg2, arg3);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glShaderOp2EXT(JNIEnv *env, jclass clazz, jint op, jint res, jint arg1, jint arg2) {
	glShaderOp2EXT(op, res, arg1, arg2);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glShaderOp1EXT(JNIEnv *env, jclass clazz, jint op, jint res, jint arg1) {
	glShaderOp1EXT(op, res, arg1);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glDeleteVertexShaderEXT(JNIEnv *env, jclass clazz, jint id) {
	glDeleteVertexShaderEXT(id);
}

static jint JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glGenVertexShadersEXT(JNIEnv *env, jclass clazz, jint range) {
	GLuint __result = glGenVertexShadersEXT(range);
	return __result;
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBindVertexShaderEXT(JNIEnv *env, jclass clazz, jint id) {
	glBindVertexShaderEXT(id);
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glEndVertexShaderEXT(JNIEnv *env, jclass clazz) {
	glEndVertexShaderEXT();
}

static void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_glBeginVertexShaderEXT(JNIEnv *env, jclass clazz) {
	glBeginVertexShaderEXT();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTVertexShader_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetLocalConstantFloatvEXT", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetLocalConstantFloatvEXT, "glGetLocalConstantFloatvEXT", (void *)&glGetLocalConstantFloatvEXT},
		{"nglGetLocalConstantIntegervEXT", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetLocalConstantIntegervEXT, "glGetLocalConstantIntegervEXT", (void *)&glGetLocalConstantIntegervEXT},
		{"nglGetLocalConstantBooleanvEXT", "(IILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetLocalConstantBooleanvEXT, "glGetLocalConstantBooleanvEXT", (void *)&glGetLocalConstantBooleanvEXT},
		{"nglGetInvariantFloatvEXT", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetInvariantFloatvEXT, "glGetInvariantFloatvEXT", (void *)&glGetInvariantFloatvEXT},
		{"nglGetInvariantIntegervEXT", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetInvariantIntegervEXT, "glGetInvariantIntegervEXT", (void *)&glGetInvariantIntegervEXT},
		{"nglGetInvariantBooleanvEXT", "(IILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetInvariantBooleanvEXT, "glGetInvariantBooleanvEXT", (void *)&glGetInvariantBooleanvEXT},
		{"nglGetVariantPointervEXT", "(III)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantPointervEXT, "glGetVariantPointervEXT", (void *)&glGetVariantPointervEXT},
		{"nglGetVariantFloatvEXT", "(IILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantFloatvEXT, "glGetVariantFloatvEXT", (void *)&glGetVariantFloatvEXT},
		{"nglGetVariantIntegervEXT", "(IILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantIntegervEXT, "glGetVariantIntegervEXT", (void *)&glGetVariantIntegervEXT},
		{"nglGetVariantBooleanvEXT", "(IILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglGetVariantBooleanvEXT, "glGetVariantBooleanvEXT", (void *)&glGetVariantBooleanvEXT},
		{"glIsVariantEnabledEXT", "(II)Z", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glIsVariantEnabledEXT, "glIsVariantEnabledEXT", (void *)&glIsVariantEnabledEXT},
		{"glBindParameterEXT", "(I)I", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glBindParameterEXT, "glBindParameterEXT", (void *)&glBindParameterEXT},
		{"glBindTextureUnitParameterEXT", "(II)I", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glBindTextureUnitParameterEXT, "glBindTextureUnitParameterEXT", (void *)&glBindTextureUnitParameterEXT},
		{"glBindTexGenParameterEXT", "(III)I", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glBindTexGenParameterEXT, "glBindTexGenParameterEXT", (void *)&glBindTexGenParameterEXT},
		{"glBindMaterialParameterEXT", "(II)I", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glBindMaterialParameterEXT, "glBindMaterialParameterEXT", (void *)&glBindMaterialParameterEXT},
		{"glBindLightParameterEXT", "(II)I", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glBindLightParameterEXT, "glBindLightParameterEXT", (void *)&glBindLightParameterEXT},
		{"glDisableVariantClientStateEXT", "(I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glDisableVariantClientStateEXT, "glDisableVariantClientStateEXT", (void *)&glDisableVariantClientStateEXT},
		{"glEnableVariantClientStateEXT", "(I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glEnableVariantClientStateEXT, "glEnableVariantClientStateEXT", (void *)&glEnableVariantClientStateEXT},
		{"nglVariantPointerEXT", "(IIILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantPointerEXT, "glVariantPointerEXT", (void *)&glVariantPointerEXT},
		{"nglVariantPointerEXTBO", "(IIII)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantPointerEXTBO, "glVariantPointerEXT", (void *)&glVariantPointerEXT},
		{"nglVariantuivEXT", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantuivEXT, "glVariantuivEXT", (void *)&glVariantuivEXT},
		{"nglVariantusvEXT", "(ILjava/nio/ShortBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantusvEXT, "glVariantusvEXT", (void *)&glVariantusvEXT},
		{"nglVariantubvEXT", "(ILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantubvEXT, "glVariantubvEXT", (void *)&glVariantubvEXT},
		{"nglVariantfvEXT", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantfvEXT, "glVariantfvEXT", (void *)&glVariantfvEXT},
		{"nglVariantivEXT", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantivEXT, "glVariantivEXT", (void *)&glVariantivEXT},
		{"nglVariantsvEXT", "(ILjava/nio/ShortBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantsvEXT, "glVariantsvEXT", (void *)&glVariantsvEXT},
		{"nglVariantbvEXT", "(ILjava/nio/ByteBuffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglVariantbvEXT, "glVariantbvEXT", (void *)&glVariantbvEXT},
		{"nglSetLocalConstantEXT", "(IILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglSetLocalConstantEXT, "glSetLocalConstantEXT", (void *)&glSetLocalConstantEXT},
		{"nglSetInvariantEXT", "(IILjava/nio/Buffer;I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_nglSetInvariantEXT, "glSetInvariantEXT", (void *)&glSetInvariantEXT},
		{"glGenSymbolsEXT", "(IIII)I", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glGenSymbolsEXT, "glGenSymbolsEXT", (void *)&glGenSymbolsEXT},
		{"glExtractComponentEXT", "(III)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glExtractComponentEXT, "glExtractComponentEXT", (void *)&glExtractComponentEXT},
		{"glInsertComponentEXT", "(III)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glInsertComponentEXT, "glInsertComponentEXT", (void *)&glInsertComponentEXT},
		{"glWriteMaskEXT", "(IIIIII)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glWriteMaskEXT, "glWriteMaskEXT", (void *)&glWriteMaskEXT},
		{"glSwizzleEXT", "(IIIIII)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glSwizzleEXT, "glSwizzleEXT", (void *)&glSwizzleEXT},
		{"glShaderOp3EXT", "(IIIII)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glShaderOp3EXT, "glShaderOp3EXT", (void *)&glShaderOp3EXT},
		{"glShaderOp2EXT", "(IIII)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glShaderOp2EXT, "glShaderOp2EXT", (void *)&glShaderOp2EXT},
		{"glShaderOp1EXT", "(III)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glShaderOp1EXT, "glShaderOp1EXT", (void *)&glShaderOp1EXT},
		{"glDeleteVertexShaderEXT", "(I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glDeleteVertexShaderEXT, "glDeleteVertexShaderEXT", (void *)&glDeleteVertexShaderEXT},
		{"glGenVertexShadersEXT", "(I)I", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glGenVertexShadersEXT, "glGenVertexShadersEXT", (void *)&glGenVertexShadersEXT},
		{"glBindVertexShaderEXT", "(I)V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glBindVertexShaderEXT, "glBindVertexShaderEXT", (void *)&glBindVertexShaderEXT},
		{"glEndVertexShaderEXT", "()V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glEndVertexShaderEXT, "glEndVertexShaderEXT", (void *)&glEndVertexShaderEXT},
		{"glBeginVertexShaderEXT", "()V", (void *)&Java_org_lwjgl_opengl_EXTVertexShader_glBeginVertexShaderEXT, "glBeginVertexShaderEXT", (void *)&glBeginVertexShaderEXT}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
