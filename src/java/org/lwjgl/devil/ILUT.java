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
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;

/**
 * $Id$
 * <p>
 * The DevIL ILUT API.
 * </p>
 * 
 * @author captainjester <captainjester@users.sourceforge.net>
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class ILUT {

		// Attribute Bits
    public static final int ILUT_OPENGL_BIT = 0x00000001;
    public static final int ILUT_ALL_ATTRIB_BITS = 0x000FFFFF;


		// Error Types
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


		// State Definitions
    public static final int ILUT_PALETTE_MODE = 0x0600;
    public static final int ILUT_OPENGL_CONV = 0x0610;
    public static final int ILUT_MAXTEX_WIDTH = 0x0630;
    public static final int ILUT_MAXTEX_HEIGHT = 0x0631;
    public static final int ILUT_MAXTEX_DEPTH = 0x0632;
    public static final int ILUT_GL_USE_S3TC = 0x0634;
    public static final int ILUT_GL_GEN_S3TC = 0x0635;
    public static final int ILUT_GL_AUTODETECT_TEXTURE_TARGET = 0x0807;

		//  The different rendering api's...more to be added later?
    public static final int ILUT_OPENGL = 0;
		
		public static final int ILUT_VENDOR = IL.IL_VENDOR;
		public static final int ILUT_VERSION_NUM = IL.IL_VERSION_NUM;
		
		/** Have we been created? */
    protected static boolean created;
    
		public static native boolean ilutRenderer(int renderer);
    public static native boolean ilutDisable(int mode);
    public static native boolean ilutEnable(int mode);
    public static native boolean ilutGetBoolean(int mode);
    public static native int ilutGetInteger(int mode);
    public static native String ilutGetString(int stringName);
    private static native void ilutInit();
    public static native boolean ilutIsDisabled(int mode);
    public static native boolean ilutIsEnabled(int mode);
    public static native void ilutPopAttrib();
    public static native void ilutPushAttrib(int bits);
    public static native void ilutSetInteger(int Mode, int param);
		// public static native void ilutGetBooleanv(int mode, ILboolean *Param);
		// public static native void ilutGetIntegerv(int mode, ILint *Param);

		// ImageLib Utility Toolkit's OpenGL Functions
    public static native int ilutGLBindTexImage();
    public static native int ilutGLBindMipmaps();
    public static native boolean ilutGLBuildMipmaps();
    public static native int ilutGLLoadImage(String fileName);
    public static native boolean ilutGLScreen();
    public static native boolean ilutGLScreenie();
    public static native boolean ilutGLSaveImage(String fileName, int texID);
    public static native boolean ilutGLSetTex(int texID);
    public static native boolean ilutGLTexImage(int level);
		
    /**
     * @return true if ILUT has been created
     */
    public static boolean isCreated() {
      return created;
    }     
    
    /**
     * Creates a new instance of ILUT. Cannot be created unless IL has been created.
     */
    public static void create() throws LWJGLException {
				if(!IL.isCreated()) {
						throw new LWJGLException("Cannot create ILUT without having created IL instance");
				}
				
				String[] ilutPaths = getILUTPaths();
				nCreate(ilutPaths);
				created = true;

				try {
					ILUT.initNativeStubs();
					ILUT.ilutInit();
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
			resetNativeStubs(ILUT.class);
			if (created) {
				nDestroy();
			}
			created = false;
		}

		/**
		 * Native method to create ILUT instance
		 * 
		 * @param iluPaths Array of strings containing paths to search for ILUT library
		 */
		protected static native void nCreate(String[] ilutPaths) throws LWJGLException;

		/**
		 * Native method the destroy the ILUT
		 */
		protected static native void nDestroy();

		private static String[] getILUTPaths() throws LWJGLException {
			// need to pass path of possible locations of IL to native side
			List possible_paths = new ArrayList();

			String osName = System.getProperty("os.name");

			String libname;
			String platform_lib_name;
			if (osName.startsWith("Win")) {
				libname = "ILUT";
				platform_lib_name = "ILUT.dll";
			} else if (osName.startsWith("Lin")) {
				libname = "ILUT";
				platform_lib_name = "ILUT.so";
			} else if (osName.startsWith("Mac")) {
				libname = "ILUT";
				platform_lib_name = "ILUT.dylib";
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
			String[] ilutPaths = new String[possible_paths.size()];
			possible_paths.toArray(ilutPaths);

			return ilutPaths;
		}

		/**
		 * Tries to locate ILUT from the current ClassLoader
		 * This method exists because ILUT is loaded from native code, and as such
		 * is exempt from ClassLoader library loading rutines. ILUT therefore always fails.
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
				Sys.log("Failure locating ILUT using classloader:" + e);
			}
			return null;
		}
}
