/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glProgramVertexLimitNVPROC) (GLenum target, GLint limit);
typedef void (APIENTRY *glFramebufferTextureEXTPROC) (GLenum target, GLenum attachment, GLuint texture, GLint level);
typedef void (APIENTRY *glFramebufferTextureLayerEXTPROC) (GLenum target, GLenum attachment, GLuint texture, GLint level, GLint layer);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVGeometryProgram4_nglProgramVertexLimitNV(JNIEnv *env, jclass clazz, jint target, jint limit, jlong function_pointer) {
	glProgramVertexLimitNVPROC glProgramVertexLimitNV = (glProgramVertexLimitNVPROC)((intptr_t)function_pointer);
	glProgramVertexLimitNV(target, limit);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVGeometryProgram4_nglFramebufferTextureEXT(JNIEnv *env, jclass clazz, jint target, jint attachment, jint texture, jint level, jlong function_pointer) {
	glFramebufferTextureEXTPROC glFramebufferTextureEXT = (glFramebufferTextureEXTPROC)((intptr_t)function_pointer);
	glFramebufferTextureEXT(target, attachment, texture, level);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_NVGeometryProgram4_nglFramebufferTextureLayerEXT(JNIEnv *env, jclass clazz, jint target, jint attachment, jint texture, jint level, jint layer, jlong function_pointer) {
	glFramebufferTextureLayerEXTPROC glFramebufferTextureLayerEXT = (glFramebufferTextureLayerEXTPROC)((intptr_t)function_pointer);
	glFramebufferTextureLayerEXT(target, attachment, texture, level, layer);
}

