/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glUnmapObjectBufferATIPROC) (GLuint buffer);
typedef GLvoid * (APIENTRY *glMapObjectBufferATIPROC) (GLuint buffer);

static glUnmapObjectBufferATIPROC glUnmapObjectBufferATI;
static glMapObjectBufferATIPROC glMapObjectBufferATI;

static void JNICALL Java_org_lwjgl_opengl_ATIMapObjectBuffer_glUnmapObjectBufferATI(JNIEnv *env, jclass clazz, jint buffer) {
	glUnmapObjectBufferATI(buffer);
}

static jobject JNICALL Java_org_lwjgl_opengl_ATIMapObjectBuffer_nglMapObjectBufferATI(JNIEnv *env, jclass clazz, jint buffer, jint result_size, jobject old_buffer) {
	GLvoid * __result = glMapObjectBufferATI(buffer);
	return safeNewBufferCached(env, __result, result_size, old_buffer);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIMapObjectBuffer_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glUnmapObjectBufferATI", "(I)V", (void *)&Java_org_lwjgl_opengl_ATIMapObjectBuffer_glUnmapObjectBufferATI, "glUnmapObjectBufferATI", (void *)&glUnmapObjectBufferATI},
		{"nglMapObjectBufferATI", "(IILjava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;", (void *)&Java_org_lwjgl_opengl_ATIMapObjectBuffer_nglMapObjectBufferATI, "glMapObjectBufferATI", (void *)&glMapObjectBufferATI}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
