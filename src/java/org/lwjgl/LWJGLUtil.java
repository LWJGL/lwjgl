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
package org.lwjgl;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * $Id$
 * <p>
 * Internal library methods
 * </p>
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class LWJGLUtil {

  /**
   * Locates the paths required by a library.
   *
   * @param libNames List of library names to look for, in the form of Local Library name, Platform library name.
   *        At least 6 names must be passed. 2 for each supported platform in the following order: Windows, Linux, MacOSX.
   * @param classloader The classloader to ask for librariy paths
   * @return Paths to located libraries, if any
   */
  public static String[] getLibraryPaths(String[] libNames, ClassLoader classloader) throws LWJGLException {
		// need to pass path of possible locations of IL to native side
		List possible_paths = new ArrayList();

		String osName = System.getProperty("os.name");

		String libname;
		String platform_lib_name;
		if (osName.startsWith("Win")) {
			libname = libNames[0];
			platform_lib_name = libNames[1];
		} else if (osName.startsWith("Lin")) {
			libname = libNames[2];
			platform_lib_name = libNames[3];
		} else if (osName.startsWith("Mac")) {
			libname = libNames[4];
			platform_lib_name = libNames[5];
		} else {
			throw new LWJGLException("Unknown platform: " + osName);
		}

		// Add all possible paths from java.library.path
		StringTokenizer st = new StringTokenizer(System.getProperty("java.library.path"), File.pathSeparator);
		while (st.hasMoreTokens()) {
			String path = st.nextToken();
			possible_paths.add(path + File.separator + platform_lib_name);
		}

		String classloader_path = LWJGLUtil.getPathFromClassLoader(libname, classloader);
		if (classloader_path != null) {
			Sys.log("getPathFromClassLoader: Path found: " + classloader_path);
			possible_paths.add(classloader_path);
		}

	  String lwjgl_classloader_path = LWJGLUtil.getPathFromClassLoader("lwjgl", classloader);
		if (lwjgl_classloader_path != null) {
			Sys.log("getPathFromClassLoader: Path found: " + lwjgl_classloader_path);
			possible_paths.add(lwjgl_classloader_path.substring(0, lwjgl_classloader_path.lastIndexOf(File.separator))
					+ File.separator + platform_lib_name);
		}

		//add cwd path
		possible_paths.add(platform_lib_name);

		//create needed string array
		String[] paths = new String[possible_paths.size()];
		possible_paths.toArray(paths);

		return paths;
	}

	/**
	 * Tries to locate named library from the current ClassLoader
	 * This method exists because native libraries are loaded from native code, and as such
	 * is exempt from ClassLoader library loading rutines. It therefore always fails.
	 * We therefore invoke the protected method of the ClassLoader to see if it can
	 * locate it.
	 *
	 * @param libname Name of library to search for
   * @param classloader Classloader to use
	 * @return Absolute path to library if found, otherwise null
	 */
	public static String getPathFromClassLoader(String libname, ClassLoader classloader) {
		try {
			Sys.log("getPathFromClassLoader: searching for: " + libname);
			Object o = classloader;
			Class c = o.getClass();
			while (c != null) {
				try {
					Method findLibrary = c.getDeclaredMethod("findLibrary", new Class[] { String.class});
					findLibrary.setAccessible(true);
					Object[] arguments = new Object[] {libname};
					return (String) findLibrary.invoke(o, arguments);
				} catch (NoSuchMethodException e) {
					c = c.getSuperclass();
				}
			}
		} catch (Exception e) {
			Sys.log("Failure locating " + e + " using classloader:" + e);
		}
		return null;
	}
}
