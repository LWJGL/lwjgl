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

#include "org_lwjgl_Display.h"
#include <JavaVM/jni.h>
#include "RenderingContext.h"

RenderingContext * renderingContext;

/*
 * Class:     org_lwjgl_Display
 * Method:    getAvailableDisplayModes
 * Signature: ()[Lorg/lwjgl/DisplayMode;
 */
JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_Display_getAvailableDisplayModes
  (JNIEnv * env, jclass clazz)
{

      printf("Getting default display mode - 1024x768x32");

      // Allocate an array of DisplayModes big enough
      jclass displayModeClass = env->FindClass("org/lwjgl/DisplayMode");

      jobjectArray ret = env->NewObjectArray(1, displayModeClass, NULL);
      jmethodID displayModeConstructor = env->GetMethodID(displayModeClass, "<init>", "(IIIIIII)V");

      jobject displayMode = env->NewObject(displayModeClass, displayModeConstructor,
                                   1024, 768, 32, 0,
                                   0,0,0 );

      env->SetObjectArrayElement( ret, 0, displayMode );


      return ret;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    nCreate
 * Signature: (IIIIZ)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_Display_nCreate
  (JNIEnv * env, jclass clazz, jint width, jint height, jint bpp, jint freq, jboolean debug)
{
      renderingContext = new RenderingContext();

      renderingContext->createDisplay( width, height, bpp, freq );


      jfieldID fid_handle = env->GetStaticFieldID(clazz, "handle", "I");
      env->SetStaticIntField(clazz, fid_handle, (jint) renderingContext->windowPtr );

      printf("Display created\n");

      return JNI_TRUE;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    nDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Display_nDestroy
  (JNIEnv * env, jclass clazz)
{
#ifdef _DEBUG
      printf("Destroying display\n");
#endif
      
      renderingContext->destroyDisplay();

        
#ifdef _DEBUG
      printf("Destroyed display\n");
#endif
}



