/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glFramebufferTextureLayerEXTPROC) (GLenum target, GLenum attachment, GLuint texture, GLint level, GLint layer);

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_EXTTextureArray_nglFramebufferTextureLayerEXT(JNIEnv *env, jclass clazz, jint target, jint attachment, jint texture, jint level, jint layer, jlong function_pointer) {
	glFramebufferTextureLayerEXTPROC glFramebufferTextureLayerEXT = (glFramebufferTextureLayerEXTPROC)((intptr_t)function_pointer);
	glFramebufferTextureLayerEXT(target, attachment, texture, level, layer);
}

