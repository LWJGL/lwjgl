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

import java.nio.IntBuffer;

import org.lwjgl.BufferChecks;
import org.lwjgl.LWJGLException;

/**
 * $Id$
 *
 * The DevIL ILU API.
 * 
 * @author captainjester <captainjester@users.sourceforge.net>
 * @version $Revision$
 */
public class ILU {
    /** Have we been created? */
    protected static boolean created;
  
    public static final int ILU_VERSION_1_6_7 =                  1;
    public static final int ILU_VERSION =                        167;

    public static final int ILU_FILTER =                         0x2600;
    public static final int ILU_NEAREST =                        0x2601;
    public static final int ILU_LINEAR =                         0x2602;
    public static final int ILU_BILINEAR =                       0x2603;
    public static final int ILU_SCALE_BOX =                      0x2604;
    public static final int ILU_SCALE_TRIANGLE =                 0x2605;
    public static final int ILU_SCALE_BELL =                     0x2606;
    public static final int ILU_SCALE_BSPLINE =                  0x2607;
    public static final int ILU_SCALE_LANCZOS3 =                 0x2608;
    public static final int ILU_SCALE_MITCHELL =                 0x2609;

//     Error types
    public static final int ILU_INVALID_ENUM =                   0x0501;
    public static final int ILU_OUT_OF_MEMORY =                  0x0502;
    public static final int ILU_INTERNAL_ERROR =                 0x0504;
    public static final int ILU_INVALID_VALUE =                  0x0505;
    public static final int ILU_ILLEGAL_OPERATION =              0x0506;
    public static final int ILU_INVALID_PARAM =                  0x0509;
    
//     Values
    public static final int ILU_PLACEMENT =                      0x0700;
    public static final int ILU_LOWER_LEFT =                     0x0701;
    public static final int ILU_LOWER_RIGHT =                    0x0702;
    public static final int ILU_UPPER_LEFT =                     0x0703;
    public static final int ILU_UPPER_RIGHT =                    0x0704;
    public static final int ILU_CENTER =                         0x0705;
    public static final int ILU_CONVOLUTION_MATRIX =             0x0710;
//    public static final int ILU_VERSION_NUM =                    IL_VERSION_NUM;
//    public static final int ILU_VENDOR =                         IL_VENDOR;

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
    
    public static native boolean iluAlienify();
    public static native boolean iluBlurAvg(int iter);
    public static native boolean iluBlurGaussian(int iter);
    public static native boolean iluBuildMipmaps();
    public static native int iluColoursUsed();
    public static native boolean iluCompareImage(int comp);
    public static native boolean iluContrast(float contrast);
    public static native boolean iluCrop(int xOff, int yOff, int zOff, int width, int height, int depth);
    public static native void  iluDeleteImage(int id);
    public static native boolean iluEdgeDetectE();
    public static native boolean iluEdgeDetectP();
    public static native boolean iluEdgeDetectS();
    public static native boolean iluEmboss();
    public static native boolean iluEnlargeCanvas(int width, int height, int depth);
    public static native boolean iluEnlargeImage(float xDim, float yDim, float zDim);
    public static native boolean iluEqualize();
    public static native String iluErrorString(int error);
    public static native boolean iluFlipImage();
    public static native boolean iluGammaCorrect(float gamma);
    public static native int iluGenImage();
    public static native void iluGetImageInfo(ILinfo info);
    public static native int iluGetInteger(int mode);
    public static void iluGetIntegerv(int mode, IntBuffer param) {
        BufferChecks.checkDirect(param);
        niluGetIntegerv(mode, param, param.position());
    }
    public static native void niluGetIntegerv(int mode, IntBuffer param, int param_offset);
    public static native String iluGetString(int stringName);
    public static native void iluImageParameter(int pName, int param);
    public static native void iluInit();
    public static native boolean iluInvertAlpha();
    public static native int iluLoadImage(String fileName);
    public static native boolean iluMirror();
    public static native boolean iluNegative();
    public static native boolean iluNoisify(float tolerance);
    public static native boolean iluPixelize(int pixSize);
    // TODO result placed in a pointer
//    public static native void iluRegionfv(ILpointf points[], int n);
    // TODO result placed in a pointer
//    public static native void iluRegioniv(ILpointi points[], int n);
    public static native boolean iluReplaceColour(byte red, byte green, byte blue, float tolerance);
    public static native boolean iluRotate(float angle);
    
    // TODO Not implemented in the native lib
//    public static native boolean iluRotate3D(float x, float y, float z, float Angle);
    public static native boolean iluSaturate1f(float saturation);
    public static native boolean iluSaturate4f(float r, float g, float b, float saturation);
    public static native boolean iluScale(int width, int height, int depth);
    public static native boolean iluScaleColours(float r, float g, float b);
    public static native boolean iluSharpen(float factor, int iter);
    public static native boolean iluSwapColours();
    public static native boolean iluWave(float angle);
    /**
     * 
     */
    public static void create() throws LWJGLException {
        if (!created) {
            nCreate();
            ILU.initNativeStubs();
            ILU.iluInit();
            created = true;
        }
        
    }

    public static native void nCreate();
    
    //DevIL lib allows both spellings of colour.
    //Will do the same this way.
    public static void iluColorsUsed() {
        iluColoursUsed();
    }
    public static void iluSwapColors() {
        iluSwapColours();
    }
    public static void iluReplaceColor(byte red, byte green, byte blue, float tolerance) {
        iluReplaceColour(red, green, blue, tolerance);
    }
    public static void iluScaleColors(float r, float g, float b) {
        iluScaleColours(r, g, b);
    }
}
