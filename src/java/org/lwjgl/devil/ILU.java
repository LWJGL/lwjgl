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

import java.io.File;
import java.lang.reflect.Method;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.lwjgl.BufferChecks;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;

/**
 * $Id$
 * <p>
 * The DevIL ILU API.
 * </p>
 * 
 * @author captainjester <captainjester@users.sourceforge.net>
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class ILU {
		
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

		// Error types
    public static final int ILU_INVALID_ENUM =                   0x0501;
    public static final int ILU_OUT_OF_MEMORY =                  0x0502;
    public static final int ILU_INTERNAL_ERROR =                 0x0504;
    public static final int ILU_INVALID_VALUE =                  0x0505;
    public static final int ILU_ILLEGAL_OPERATION =              0x0506;
    public static final int ILU_INVALID_PARAM =                  0x0509;
    
		// Values
    public static final int ILU_PLACEMENT =                      0x0700;
    public static final int ILU_LOWER_LEFT =                     0x0701;
    public static final int ILU_LOWER_RIGHT =                    0x0702;
    public static final int ILU_UPPER_LEFT =                     0x0703;
    public static final int ILU_UPPER_RIGHT =                    0x0704;
    public static final int ILU_CENTER =                         0x0705;
    public static final int ILU_CONVOLUTION_MATRIX =             0x0710;
    public static final int ILU_VERSION_NUM =                    IL.IL_VERSION_NUM;
    public static final int ILU_VENDOR =                         IL.IL_VENDOR;

    /** Have we been created? */
    protected static boolean created;

    /**
     * @return true if ILU has been created
     */
    public static boolean isCreated() {
      return created;
    }     

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
    private static native void niluGetIntegerv(int mode, IntBuffer param, int param_offset);
    public static native String iluGetString(int stringName);
    public static native void iluImageParameter(int pName, int param);
    private static native void iluInit();
    public static native boolean iluInvertAlpha();
    public static native int iluLoadImage(String fileName);
    public static native boolean iluMirror();
    public static native boolean iluNegative();
    public static native boolean iluNoisify(float tolerance);
    public static native boolean iluPixelize(int pixSize);
    public static native boolean iluReplaceColour(byte red, byte green, byte blue, float tolerance);
    public static native boolean iluRotate(float angle);
    public static native boolean iluSaturate1f(float saturation);
    public static native boolean iluSaturate4f(float r, float g, float b, float saturation);
    public static native boolean iluScale(int width, int height, int depth);
    public static native boolean iluScaleColours(float r, float g, float b);
    public static native boolean iluSharpen(float factor, int iter);
    public static native boolean iluSwapColours();
    public static native boolean iluWave(float angle);

		// public static native void iluRegionfv(ILpointf points[], int n);
		// public static native void iluRegioniv(ILpointi points[], int n);
		// public static native boolean iluRotate3D(float x, float y, float z, float Angle);
    
    /* DevIL lib allows both spellings of colour. We support that too */
		// ========================================================================
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
		// ------------------------------------------------------------------------
		
    /**
     * Creates a new instance of ILU. Cannot be created unless IL has been created.
     */
    public static void create() throws LWJGLException {
				if(!IL.isCreated()) {
						throw new LWJGLException("Cannot create ILU without having created IL instance");
				}
				
				String[] iluPaths = getILUPaths();
				nCreate(iluPaths);
				created = true;

				try {
					ILU.initNativeStubs();
					ILU.iluInit();
					created = true;
				} catch (LWJGLException e) {
					destroy();
					throw e;
				}				
    }

		private static native void initNativeStubs() throws LWJGLException;

		private static native void resetNativeStubs(Class clazz);

		/**
		 * Exit cleanly by calling destroy.
		 */
		public static void destroy() {
			resetNativeStubs(ILU.class);
			if (created) {
				nDestroy();
			}
			created = false;
		}

		/**
		 * Native method to create ILU instance
		 * 
		 * @param iluPaths Array of strings containing paths to search for ILU library
		 */
		protected static native void nCreate(String[] iluPaths) throws LWJGLException;

		/**
		 * Native method the destroy the ILU
		 */
		protected static native void nDestroy();

		private static String[] getILUPaths() throws LWJGLException {
			// need to pass path of possible locations of ILU to native side
			List possible_paths = new ArrayList();

			String osName = System.getProperty("os.name");

			String libname;
			String platform_lib_name;
			if (osName.startsWith("Win")) {
				libname = "ILU";
				platform_lib_name = "ILU.dll";
			} else if (osName.startsWith("Lin")) {
				libname = "ILU";
				platform_lib_name = "libILU.so";
			} else if (osName.startsWith("Mac")) {
				libname = "ILU";
				platform_lib_name = "libILU.dylib";
			} else {
				throw new LWJGLException("Unknown platform: " + osName);
			}

			// Add all possible paths from java.library.path
			String java_library_path = System.getProperty("java.library.path");
			StringTokenizer st = new StringTokenizer(System.getProperty("java.library.path"), File.pathSeparator);
			while (st.hasMoreTokens()) {
				String path = st.nextToken();
				possible_paths.add(path + File.separator + platform_lib_name);
			}

			String classloader_path = getPathFromClassLoader(libname);
			if (classloader_path != null) {
				Sys.log("getPathFromClassLoader: Path found: " + classloader_path);
				possible_paths.add(classloader_path);
			}
			String lwjgl_classloader_path = getPathFromClassLoader("lwjgl");
			if (lwjgl_classloader_path != null) {
				Sys.log("getPathFromClassLoader: Path found: " + lwjgl_classloader_path);
				possible_paths.add(lwjgl_classloader_path.substring(0, lwjgl_classloader_path.lastIndexOf(File.separator))
						+ File.separator + platform_lib_name);
			}

			//add cwd path
			possible_paths.add(platform_lib_name);
			
			//create needed string array
			String[] iluPaths = new String[possible_paths.size()];
			possible_paths.toArray(iluPaths);

			return iluPaths;
		}

		/**
		 * Tries to locate ILU from the current ClassLoader
		 * This method exists because ILU is loaded from native code, and as such
		 * is exempt from ClassLoader library loading rutines. ILU therefore always fails.
		 * We therefore invoke the protected method of the ClassLoader to see if it can
		 * locate it. 
		 * 
		 * @param libname Name of library to search for
		 * @return Absolute path to library if found, otherwise null
		 */
		private static String getPathFromClassLoader(String libname) {
			try {
				Sys.log("getPathFromClassLoader: searching for: " + libname);
				Object o = IL.class.getClassLoader();
				Class c = o.getClass();
				while (c != null) {
					try {
						Method findLibrary = c.getDeclaredMethod("findLibrary", new Class[] { String.class});
						findLibrary.setAccessible(true);
						Object[] arguments = new Object[] { libname};
						return (String) findLibrary.invoke(o, arguments);
					} catch (NoSuchMethodException e) {
						c = c.getSuperclass();
					}
				}
			} catch (Exception e) {
				Sys.log("Failure locating ILU using classloader:" + e);
			}
			return null;
		}
}
