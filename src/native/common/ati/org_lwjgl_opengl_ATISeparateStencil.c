/* MACHINE GENERATED FILE, DO NOT EDIT */

#include <jni.h>
#include "extgl.h"

typedef void (APIENTRY *glStencilFuncSeparateATIPROC) (GLenum frontfunc, GLenum backfunc, GLint ref, GLuint mask);
typedef void (APIENTRY *glStencilOpSeparateATIPROC) (GLenum face, GLenum sfail, GLenum dpfail, GLenum dppass);

static glStencilFuncSeparateATIPROC glStencilFuncSeparateATI;
static glStencilOpSeparateATIPROC glStencilOpSeparateATI;

static void JNICALL Java_org_lwjgl_opengl_ATISeparateStencil_glStencilFuncSeparateATI(JNIEnv *env, jclass clazz, jint frontfunc, jint backfunc, jint ref, jint mask) {
	glStencilFuncSeparateATI(frontfunc, backfunc, ref, mask);
}

static void JNICALL Java_org_lwjgl_opengl_ATISeparateStencil_glStencilOpSeparateATI(JNIEnv *env, jclass clazz, jint face, jint sfail, jint dpfail, jint dppass) {
	glStencilOpSeparateATI(face, sfail, dpfail, dppass);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_ATISeparateStencil_initNativeStubs(JNIEnv *env, jclass clazz) {
	JavaMethodAndExtFunction functions[] = {
		{"glStencilFuncSeparateATI", "(IIII)V", (void *)&Java_org_lwjgl_opengl_ATISeparateStencil_glStencilFuncSeparateATI, "glStencilFuncSeparateATI", (void *)&glStencilFuncSeparateATI},
		{"glStencilOpSeparateATI", "(IIII)V", (void *)&Java_org_lwjgl_opengl_ATISeparateStencil_glStencilOpSeparateATI, "glStencilOpSeparateATI", (void *)&glStencilOpSeparateATI}
	};
	int num_functions = NUMFUNCTIONS(functions);
	extgl_InitializeClass(env, clazz, num_functions, functions);
}
