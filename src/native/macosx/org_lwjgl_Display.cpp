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

static int numberForKey( CFDictionaryRef desc, CFStringRef key )
{
    CFNumberRef			value;
    int				num = 0;

    if ( (value = CFDictionaryGetValue(desc, key)) == NULL )
    {
        return 0;
    }

    CFNumberGetValue( value, kCFNumberIntType, &num );
    return num;
}

/*
 * Class:     org_lwjgl_Display
 * Method:    getAvailableDisplayModes
 * Signature: ()[Lorg/lwjgl/DisplayMode;
 */
JNIEXPORT jobjectArray JNICALL Java_org_lwjgl_Display_getAvailableDisplayModes
  (JNIEnv * env, jclass clazz)
{
      CGDirectDisplayID * displays = renderingContext->enumerateDisplays();
      CFArrayRef modeList = renderingContext->enumerateDisplayModes( displays[0] );

      // count the display modes
      //
      int cnt = CFArrayGetCount( modeList );

      // Allocate an array of DisplayModes big enough
      jclass displayModeClass = env->FindClass("org/lwjgl/DisplayMode");

      // Note the * 16 - this is because we are manufacturing available alpha/depth/stencil combos.
      jobjectArray ret = env->NewObjectArray(cnt * 16, displayModeClass, NULL);
      jmethodID displayModeConstructor = env->GetMethodID(displayModeClass, "<init>", "(IIIIIII)V");

      CFDictionaryRef mode;
      for ( int i=0; i< cnt; i++ )
      {
          mode = CFArrayGetValueAtIndex( modeList, i );

          int width = numberForKey( mode, kCGDisplayWidth );
          int height = numberForKey( mode, kCGDisplayHeight );
          int bpp = numberForKey( mode, kCGDisplayBitsPerPixel );
          int refreshRate = numberForKey( mode, kCGDisplayRefreshRate );

          if ( bpp <= 8 )
          {
              continue;
          }
          else
          {
              jobject displayMode;

              for ( int depthBits = 0; depthBits <= 24; depthBits += 8 )
              {
                  for ( int stencilBits = 0; stencilBits <= 8; stencilBits += 8 )
                  {
                      for ( int alphaBits = 0; alphaBits <= 8; alphaBits += 8 )
                      {
                          displayMode = env->NewObject(displayModeClass, displayModeConstructor,
                                                       width, height, bpp, refreshRate,
                                                       alphaBits, depthBits, stencilBits );

                          env->SetObjectArrayElement( ret, i, displayMode );
                      }
                  }
              }
          }
      }      
}

/*
 * Class:     org_lwjgl_Display
 * Method:    nCreate
 * Signature: (IIIIZ)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_Display_nCreate
  (JNIEnv * env, jclass clazz, jint width, jint height, jint bpp, jint freq, jboolean debug)
{
#ifdef _DEBUG
	printf("Creating display: size %dx%d %dhz %dbpp...\n", width, height, freq, bpp);
#endif      

      renderingContext = new RenderingContext();

      renderingContext->createDisplay( width, height, bpp, freq );


      jfieldID fid_handle = env->GetStaticFieldID(clazz, "handle", "I");
      env->SetStaticIntField(clazz, fid_handle, (jint) renderingContext->windowPtr );

#ifdef _DEBUG
      printf("Display created\n");
#endif      

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



