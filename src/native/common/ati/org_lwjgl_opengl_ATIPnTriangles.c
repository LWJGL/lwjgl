/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glPNTrianglesiATIPROC) (GLenum pname, GLint param);
typedef void (APIENTRY *glPNTrianglesfATIPROC) (GLenum pname, GLfloat param);

static glPNTrianglesiATIPROC glPNTrianglesiATI;
static glPNTrianglesfATIPROC glPNTrianglesfATI;

static void JNICALL Java_org_lwjgl_opengl_ATIPnTriangles_glPNTrianglesiATI(JNIEnv *env, jclass clazz, jint pname, jint param) {
	glPNTrianglesiATI(pname, param);
}

static void JNICALL Java_org_lwjgl_opengl_ATIPnTriangles_glPNTrianglesfATI(JNIEnv *env, jclass clazz, jint pname, jfloat param) {
	glPNTrianglesfATI(pname, param);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATIPnTriangles_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glPNTrianglesiATI", "(II)V", (void *)&Java_org_lwjgl_opengl_ATIPnTriangles_glPNTrianglesiATI, "glPNTrianglesiATI", (void *)&glPNTrianglesiATI},
		{"glPNTrianglesfATI", "(IF)V", (void *)&Java_org_lwjgl_opengl_ATIPnTriangles_glPNTrianglesfATI, "glPNTrianglesfATI", (void *)&glPNTrianglesfATI}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
