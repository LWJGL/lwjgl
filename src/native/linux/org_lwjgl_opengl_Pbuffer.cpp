#include <stdlib.h>
#include "org_lwjgl_opengl_Pbuffer.h"
#include "extgl.h"
#include "Window.h"
#include "common_tools.h"

typedef struct _PbufferInfo {
	GLXPbuffer buffer;
	GLXContext context;
} PbufferInfo;

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    nIsBufferLost
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_Pbuffer_nIsBufferLost
  (JNIEnv *env, jclass clazz, jint handle)
{
	// The buffer is never lost, because of the GLX_PRESERVED_CONTENTS flag
	return JNI_FALSE;
}

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    isPbufferSupported
 * Signature: ()Z
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Pbuffer_getPbufferCaps
  (JNIEnv *env, jclass clazz)
{
	// Only support thw GLX 1.3 Pbuffers and ignore the GLX_SGIX_pbuffer extension
	return extgl_Extensions.GLX13 ? org_lwjgl_opengl_Pbuffer_PBUFFER_SUPPORTED : 0;
}

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    nCreate
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_Pbuffer_nCreate
  (JNIEnv *env, jclass clazz, jint width, jint height, jint bpp, jint alpha, jint depth, jint stencil)
{
	int bpe = convertToBPE(bpp);
	const int attrib_list[] = {GLX_RENDER_TYPE, GLX_RGBA_BIT,
				   GLX_DOUBLEBUFFER, False,
				   GLX_RED_SIZE, bpe,
				   GLX_GREEN_SIZE, bpe,
				   GLX_BLUE_SIZE, bpe,
				   GLX_ALPHA_SIZE, alpha,
				   GLX_DEPTH_SIZE, depth,
				   GLX_STENCIL_SIZE, stencil,
				   GLX_DRAWABLE_TYPE, GLX_PBUFFER_BIT,
				   None};
	int num_configs;
	GLXFBConfig *configs = glXChooseFBConfig(getCurrentDisplay(), getCurrentScreen(), attrib_list, &num_configs);
	if (num_configs == 0) {
		XFree(configs);
		throwException(env, "No matching pixel format");
		return -1;
	}
	int max;
	glXGetFBConfigAttrib(getCurrentDisplay(), configs[0], GLX_MAX_PBUFFER_WIDTH, &max);
	if (max < width) {
		XFree(configs);
		throwException(env, "Width too large");
		return -1;
	}
	glXGetFBConfigAttrib(getCurrentDisplay(), configs[0], GLX_MAX_PBUFFER_HEIGHT, &max);
	if (max < height) {
		XFree(configs);
		throwException(env, "Height too large");
		return -1;
	}
        GLXContext context = glXCreateNewContext(getCurrentDisplay(), configs[0], GLX_RGBA_TYPE, getCurrentContext(), True);
        if (context == NULL) {
                XFree(configs);
                throwException(env, "Could not create a GLX context");
                return false;
        }
        if (glXIsDirect(getCurrentDisplay(), context) == False) {
                glXDestroyContext(getCurrentDisplay(), context);
                XFree(configs);
                throwException(env, "Could not create a direct GLX context");
                return false;
        }
	const int buffer_attribs[] = {GLX_PBUFFER_WIDTH, width,
				      GLX_PBUFFER_HEIGHT, height,
				      GLX_PRESERVED_CONTENTS, True,
				      GLX_LARGEST_PBUFFER, False};

	GLXPbuffer buffer = glXCreatePbuffer(getCurrentDisplay(), configs[0], buffer_attribs);
	XFree(configs);
	PbufferInfo *buffer_info = (PbufferInfo *)malloc(sizeof(PbufferInfo));
	buffer_info->buffer = buffer;
	buffer_info->context = context;
	return (jint)buffer_info;
}

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    nReleaseContext
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nReleaseContext
  (JNIEnv *env, jclass clazz)
{
	makeCurrent();
}

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    nMakeCurrent
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nMakeCurrent
  (JNIEnv *env, jclass clazz, jint handle)
{
	PbufferInfo *buffer_info = (PbufferInfo *)handle;
	GLXPbuffer buffer = buffer_info->buffer;
	GLXContext context = buffer_info->context;
	if (glXMakeContextCurrent(getCurrentDisplay(), buffer, buffer, context) == False) {
#ifdef _DEBUG
		printf("Could not make pbuffer current");
#endif
	}
}

/*
 * Class:     org_lwjgl_opengl_Pbuffer
 * Method:    nDestroyGL
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_Pbuffer_nDestroy
  (JNIEnv *env, jclass clazz, jint handle)
{
	PbufferInfo *buffer_info = (PbufferInfo *)handle;
	GLXPbuffer buffer = buffer_info->buffer;
	GLXContext context = buffer_info->context;
	glXDestroyPbuffer(getCurrentDisplay(), buffer);
	glXDestroyContext(getCurrentDisplay(), context);
	free(buffer_info);
}
