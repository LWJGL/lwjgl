/* 
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
package org.lwjgl.openal;

import java.io.File;
import java.util.StringTokenizer;
import java.lang.reflect.Method;

import org.lwjgl.Sys;
import org.lwjgl.Display;

/**
 * $Id$
 * <br>
 * The base AL functionality (no actual AL methods).
 *
 * This has been provided as a base class that we can use for either the
 * full AL 1.0 specification or as a cut-down OpenAL embedded spec. (aka
 * a mini-driver).
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public abstract class BaseAL {
	/** Have we been created? */
	protected static boolean created;

	static {
		initialize();
	}

	/**
	 * Override to provide any initialization code after creation.
	 */
	protected static void init() throws OpenALException {
	}

	/**
	 * Static initialization
	 */
	private static void initialize() {
		System.loadLibrary(org.lwjgl.Sys.getLibraryName());
	}

	/**
	 * Creates the AL instance
	 * 
	 * @throws Exception if a failiure occured in the AL creation process
	 */
	public static void create() throws OpenALException {
		if (created) {
			return;
		}

		// need to pass path of possible locations of OAL to native side
		String libpath = System.getProperty("java.library.path");
		String seperator = System.getProperty("path.separator");
		String libname;
		String jwsLibname;

		// libname is hardcoded atm - this will change in a near future...
		switch (Display.getPlatform()) {
			case Display.PLATFORM_WGL:
				libname = "lwjglaudio.dll";
				jwsLibname = "lwjglaudio";
				break;
			case Display.PLATFORM_GLX:
				libname = "libopenal.so";
				jwsLibname = "openal";
				break;
			case Display.PLATFORM_AGL:
				libname = "openal.dylib";
				jwsLibname = "openal";
				break;
			default:
				throw new OpenALException("Unknown platform");
		}
		
		String jwsPath = getPathFromJWS(jwsLibname);
		if (jwsPath != null) {
			libpath += seperator
				+ jwsPath.substring(0, jwsPath.lastIndexOf(File.separator));
		}

		StringTokenizer st = new StringTokenizer(libpath, seperator);

		//create needed string array
		String[] oalPaths = new String[st.countTokens() + 1];

		//build paths
		for (int i = 0; i < oalPaths.length - 1; i++) {
			oalPaths[i] = st.nextToken() + File.separator + libname;
		}

		//add cwd path
		oalPaths[oalPaths.length - 1] = libname;
		nCreate(oalPaths);

		init();
		created = true;
	}
	
	/**
	 * Tries to locate OpenAL from the JWS Library path
	 * This method exists because OpenAL is loaded from native code, and as such
	 * is exempt from JWS library loading rutines. OpenAL therefore always fails.
	 * We therefore invoke the protected method of the JWS classloader to see if it can
	 * locate it. 
	 * 
	 * @param libname Name of library to search for
	 * @return Absolute path to library if found, otherwise null
	 */
	private static String getPathFromJWS(String libname) {
		try {			

			if(Sys.DEBUG) {			
				System.out.println("JWS Classloader looking for: " + libname);
			}
			
			Object o = BaseAL.class.getClassLoader();
			Class c = o.getClass();
			Method findLibrary =
				c.getMethod("findLibrary", new Class[] { String.class });
			Object[] arguments = new Object[] { libname };
			return (String) findLibrary.invoke(o, arguments);

		} catch (Exception e) {
			if(Sys.DEBUG) {
				System.out.println("Failure locating OpenAL using classloader:");
				e.printStackTrace();
			}
		}
		return null;
	}	

	/**
	 * Native method to create AL instance
	 * 
	 * @param oalPaths Array of strings containing paths to search for OpenAL library
	 * @return true if the AL creation process succeeded
	 */
	protected static native void nCreate(String[] oalPaths) throws OpenALException;

	/**
	 * Calls whatever destruction rutines that are needed
	 */
	public static void destroy() {
		if (!created) {
			return;
		}
		created = false;
		nDestroy();
	}

	/**
	 * Native method the destroy the AL
	 */
	protected static native void nDestroy();
	
	
	/**
	 * @return true if AL has been created
	 */
	public static boolean isCreated() {
		return created;
	}
}
