/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glGetTexBumpParameterivATIPROC) (GLenum pname, GLint * param);
typedef void (APIENTRY *glGetTexBumpParameterfvATIPROC) (GLenum pname, GLfloat * param);
typedef void (APIENTRY *glTexBumpParameterivATIPROC) (GLenum pname, GLint * param);
typedef void (APIENTRY *glTexBumpParameterfvATIPROC) (GLenum pname, GLfloat * param);

static glGetTexBumpParameterivATIPROC glGetTexBumpParameterivATI;
static glGetTexBumpParameterfvATIPROC glGetTexBumpParameterfvATI;
static glTexBumpParameterivATIPROC glTexBumpParameterivATI;
static glTexBumpParameterfvATIPROC glTexBumpParameterfvATI;

static void JNICALL Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglGetTexBumpParameterivATI(JNIEnv *env, jclass clazz, jint pname, jobject param, jint param_position) {
	GLint *param_address = ((GLint *)(*env)->GetDirectBufferAddress(env, param)) + param_position;
	glGetTexBumpParameterivATI(pname, param_address);
}

static void JNICALL Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglGetTexBumpParameterfvATI(JNIEnv *env, jclass clazz, jint pname, jobject param, jint param_position) {
	GLfloat *param_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, param)) + param_position;
	glGetTexBumpParameterfvATI(pname, param_address);
}

static void JNICALL Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglTexBumpParameterivATI(JNIEnv *env, jclass clazz, jint pname, jobject param, jint param_position) {
	GLint *param_address = ((GLint *)(*env)->GetDirectBufferAddress(env, param)) + param_position;
	glTexBumpParameterivATI(pname, param_address);
}

static void JNICALL Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglTexBumpParameterfvATI(JNIEnv *env, jclass clazz, jint pname, jobject param, jint param_position) {
	GLfloat *param_address = ((GLfloat *)(*env)->GetDirectBufferAddress(env, param)) + param_position;
	glTexBumpParameterfvATI(pname, param_address);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIEnvmapBumpmap_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"nglGetTexBumpParameterivATI", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglGetTexBumpParameterivATI, "glGetTexBumpParameterivATI", (void *)&glGetTexBumpParameterivATI},
		{"nglGetTexBumpParameterfvATI", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglGetTexBumpParameterfvATI, "glGetTexBumpParameterfvATI", (void *)&glGetTexBumpParameterfvATI},
		{"nglTexBumpParameterivATI", "(ILjava/nio/IntBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglTexBumpParameterivATI, "glTexBumpParameterivATI", (void *)&glTexBumpParameterivATI},
		{"nglTexBumpParameterfvATI", "(ILjava/nio/FloatBuffer;I)V", (void *)&Java_org_lwjgl_opengl_ATIEnvmapBumpmap_nglTexBumpParameterfvATI, "glTexBumpParameterfvATI", (void *)&glTexBumpParameterfvATI}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
