/* 
 * Copyright (c) 2002-2004 LWJGL Project
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
 * * Neither the name of 'LWJGL' nor the names of 
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
package org.lwjgl.devil;

import org.lwjgl.LWJGLException;

/**
 * $Id$
 *
 * The DevIL ILUT API.
 * 
 * @author captainjester <captainjester@users.sourceforge.net>
 * @version $Revision$
 */
public class ILUT {
    /** Have we been created? */
    protected static boolean created;
  
    public static final int ILUT_VERSION_1_6_7 = 1;
    public static final int ILUT_VERSION = 167;


//     Attribute Bits
    public static final int ILUT_OPENGL_BIT = 0x00000001;
    public static final int ILUT_ALL_ATTRIB_BITS = 0x000FFFFF;


//     Error Types
    public static final int ILUT_INVALID_ENUM = 0x0501;
    public static final int ILUT_OUT_OF_MEMORY = 0x0502;
    public static final int ILUT_INVALID_VALUE = 0x0505;
    public static final int ILUT_ILLEGAL_OPERATION = 0x0506;
    public static final int ILUT_INVALID_PARAM = 0x0509;
    public static final int ILUT_COULD_NOT_OPEN_FILE = 0x050A;
    public static final int ILUT_STACK_OVERFLOW = 0x050E;
    public static final int ILUT_STACK_UNDERFLOW = 0x050F;
    public static final int ILUT_BAD_DIMENSIONS = 0x0511;
    public static final int ILUT_NOT_SUPPORTED = 0x0550;


//     State Definitions
    public static final int ILUT_PALETTE_MODE = 0x0600;
    public static final int ILUT_OPENGL_CONV = 0x0610;
    public static final int ILUT_MAXTEX_WIDTH = 0x0630;
    public static final int ILUT_MAXTEX_HEIGHT = 0x0631;
    public static final int ILUT_MAXTEX_DEPTH = 0x0632;
    public static final int ILUT_GL_USE_S3TC = 0x0634;
    public static final int ILUT_GL_GEN_S3TC = 0x0635;

//    This new state does automatic texture target detection
//    if enabled. Currently, only cubemap detection is supported.
//    if the current image is no cubemap, the 2d texture is chosen.
    public static final int ILUT_GL_AUTODETECT_TEXTURE_TARGET = 0x0807;

//  The different rendering api's...more to be added later?
    public static final int ILUT_OPENGL = 0;

    static {
        System.loadLibrary("ILU");
        System.loadLibrary("lwjgl-devil");
    }
    
    /**
     * @return true if DevIL has been created
     */
    public static boolean isCreated() {
      return created;
    }     
    public static native void initNativeStubs() throws LWJGLException;

    public static native boolean ilutRenderer(int renderer);

//  ImageLib Utility Toolkit Functions
    public static native boolean ilutDisable(int mode);
    public static native boolean ilutEnable(int mode);
    public static native boolean ilutGetBoolean(int mode);
//    public static native void ilutGetBooleanv(int mode, ILboolean *Param);
    public static native int ilutGetInteger(int mode);
//    public static native void ilutGetIntegerv(int mode, ILint *Param);
    public static native String ilutGetString(int stringName);
    public static native void ilutInit();
    public static native boolean ilutIsDisabled(int mode);
    public static native boolean ilutIsEnabled(int mode);
    public static native void ilutPopAttrib();
    public static native void ilutPushAttrib(int bits);
    public static native void ilutSetInteger(int Mode, int param);

//  ImageLib Utility Toolkit's OpenGL Functions
    public static native int ilutGLBindTexImage();
    public static native int ilutGLBindMipmaps();
    public static native boolean ilutGLBuildMipmaps();
    public static native int ilutGLLoadImage(String fileName);
    public static native boolean ilutGLScreen();
    public static native boolean ilutGLScreenie();
    public static native boolean ilutGLSaveImage(String fileName, int texID);
    public static native boolean ilutGLSetTex(int texID);
    public static native boolean ilutGLTexImage(int level);

    public static void create() throws LWJGLException {
        if (!created) {
            nCreate();
            ILUT.initNativeStubs();
            ILUT.ilutInit();
            ilutRenderer(ILUT_OPENGL);
            created = true;
        }
        
    }

    public static native void nCreate();
}
