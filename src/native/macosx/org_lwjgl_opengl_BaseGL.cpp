/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are 
 * met:
 * 
 * * Redistributions of source code must retain the above copyright 
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
 *   its contributors may be used to endorse or promote products derived 
 *   from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
/**
 * $Id$
 *
 * Base OSX functionality for GL.
 *
 * @author Gregory Pierce <me@gregorypierce.com>
 * @version $Revision$
 */

#include "extgl.h"
#include "org_lwjgl_opengl_BaseGL.h"
#include "RenderingContext.h"

extern RenderingContext * renderingContext;

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    nCreate
 * Signature: (IIII)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_BaseGL_nCreate
  (JNIEnv * env, jobject obj, jint colorBits, jint alphaBits, jint depthBits, jint stencilBits)
{
      AGLPixelFormat 				fmt;
      GLboolean      				ok;
      GLint         			 	attrib[] = { AGL_RGBA, AGL_NONE };
      
      /* Choose an rgb pixel format */
      fmt = aglChoosePixelFormat(NULL, 0, attrib);
      if(fmt == NULL)
      {
          return JNI_FALSE;
      }

      /* Create an AGL context */
      renderingContext->aglContext = aglCreateContext(fmt, NULL);
      if( renderingContext->aglContext == NULL)
      {
          return JNI_FALSE;
      }

      /* Attach the window to the context */
      ok = aglSetDrawable(renderingContext->aglContext, GetWindowPort(renderingContext->windowPtr) );
      if(!ok)
      {
          return JNI_FALSE;
      }

      /* Make the context the current context */
      ok = aglSetCurrentContext(renderingContext->aglContext);
      if(!ok)
      {
          return JNI_FALSE;
      }

      /* Pixel format is no longer needed */
      aglDestroyPixelFormat(fmt);
      
      return JNI_TRUE;
}

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_nDestroy
  (JNIEnv * env, jobject obj)
{
      // clear out the current rendering context
      //
      aglSetCurrentContext( NULL );

      // destroy the context
      //
      aglDestroyContext( renderingContext->aglContext );
}

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    swapBuffers
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_swapBuffers(JNIEnv * env, jobject obj)
{
    // swap the rendering buffer
    //
    aglSwapBuffers( renderingContext->aglContext );
}

/*
 * Class:     org_lwjgl_opengl_BaseGL
 * Method:    nMakeCurrent
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_nMakeCurrent
  (JNIEnv * env, jobject obj)
{
      // make the current context the one we have stored
      //
      aglSetCurrentContext( renderingContext->aglContext );
}

/*
 *  * Class:     org_lwjgl_opengl_BaseGL
 *   * Method:    nFreeContext
 *    * Signature: ()V
 *     */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_BaseGL_nReleaseContext
  (JNIEnv *, jobject)
{
      // release the context
      //
      aglSetCurrentContext( NULL );
}
