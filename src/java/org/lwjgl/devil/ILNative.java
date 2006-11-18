/* 
 * Copyright (c) 2002-2005 LWJGL Project
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
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;

/**
 * <p>
 * Native interface for DevIL
 * </p>
 * 
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
class ILNative {
	
	/** The native JNI library name */
	private static String JNI_LIBRARY_NAME = "lwjgl-devil";

	/** Version of IL */
	public static final String VERSION = "1.0beta3";
	
	/** Current version of the JNI library */
	static final int JNI_VERSION = 1;	

	private static void loadLibrary(final String lib_name) {
		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				String library_path = System.getProperty("org.lwjgl.librarypath");
				if (library_path != null) {
					System.load(library_path + File.separator + 
						System.mapLibraryName(lib_name));
				} else {
					System.loadLibrary(lib_name);
				}
				return null;
			}
		});
	}	

	static {
		loadLibrary(JNI_LIBRARY_NAME);
		
		// check for mismatch
		int nativeVersion = getNativeLibraryVersion();
		if (nativeVersion != JNI_VERSION) {
			throw new LinkageError(
					"Version mismatch: jar version is '" + JNI_VERSION + 
					"', native libary version is '" + nativeVersion + "'");
		}		
	}
	
	// IL
	// ===========================================================
	static native void initNativeStubsIL(Class clazz) throws LWJGLException;
	static native void resetNativeStubsIL(Class clazz);
	static native void nCreateIL(String[] ilPaths) throws LWJGLException;
	static native void nDestroyIL();
	private static native int getNativeLibraryVersion();  	

	static void createIL() throws LWJGLException {
		String[] illPaths = LWJGLUtil.getLibraryPaths(new String[]{
				"DevIL", "DevIL.dll",
				"IL", "libIL.so",
				"IL", "libIL.dylib"}, IL.class.getClassLoader());
		ILNative.nCreateIL(illPaths);
		
		try {
			ILNative.initNativeStubsIL(IL.class);
			IL.ilInit();
		} catch (LWJGLException e) {
			IL.destroy();
			throw e;
		}		
	}
	
	public static void destroyIL() {
		ILNative.resetNativeStubsIL(IL.class);
		ILNative.nDestroyIL();
	}
	// -----------------------------------------------------------
	
	
	// ILU
	// ===========================================================
	static native void initNativeStubsILU(Class clazz) throws LWJGLException;
	static native void resetNativeStubsILU(Class clazz);
	static native void nCreateILU(String[] iluPaths) throws LWJGLException;
	static native void nDestroyILU();
	
	static void createILU() throws LWJGLException {
		String[] iluPaths = LWJGLUtil.getLibraryPaths(new String[]{
				"ILU", "ILU.dll",
				"ILU", "libILU.so",
				"ILU", "libILU.dylib"}, ILU.class.getClassLoader());
		ILNative.nCreateILU(iluPaths);
		
		try {
			ILNative.initNativeStubsILU(ILU.class);
			ILU.iluInit();
		} catch (LWJGLException e) {
			ILU.destroy();
			throw e;
		}		
	}
	
	public static void destroyILU() {
		ILNative.resetNativeStubsILU(ILU.class);
		ILNative.nDestroyILU();
	}	
	// -----------------------------------------------------------	

	// ILU
	// ===========================================================
	static native void initNativeStubsILUT(Class clazz) throws LWJGLException;
	static native void resetNativeStubsILUT(Class clazz);
	static native void nCreateILUT(String[] ilutPaths) throws LWJGLException;
	static native void nDestroyILUT();
	
	static void createILUT() throws LWJGLException {
		String[] ilutPaths = LWJGLUtil.getLibraryPaths(new String[]{
				"ILUT", "ILUT.dll",
				"ILUT", "libILUT.so",
				"ILUT", "libILUT.dylib"}, ILUT.class.getClassLoader());
		ILNative.nCreateILUT(ilutPaths);
		
		try {
			ILNative.initNativeStubsILUT(ILUT.class);
			ILUT.ilutInit();
		} catch (LWJGLException e) {
			ILUT.destroy();
			throw e;
		}		
	}
	
	public static void destroyILUT() {
		ILNative.resetNativeStubsILUT(ILUT.class);
		ILNative.nDestroyILUT();
	}	
	// -----------------------------------------------------------	
}
