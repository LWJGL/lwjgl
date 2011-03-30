/*******************************************************************************
 * Copyright (c) 2011 LWJGL Project and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html, and under the terms of the 
 * BSD license, see http://lwjgl.org/license.php for details.
 *
 * Contributors:
 *    Jens von Pilgrim - initial implementation
 ******************************************************************************/
package org.lwjgl;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * Helper class for retrieving plugin relative path of native LWJGL
 * libraries and setting the path in the system properties. 
 *
 * @author 	Jens von Pilgrim
 * @since 	Jan 30, 2011
 */
public class LibraryPathUtil {

	public static final String LWJGL_SYSTEM_PROPERTY = "org.lwjgl.librarypath";

	public static String[] NATIVEPATH = new String[] { "windows", "macosx",
			"linux", "solaris" };

	/**
	 * Returns plugin relative path to native libraries according to
	 * current operating system.
	 * 
	 * @return
	 * @throws OSNotSupportedException
	 */
	public static String getRelativeLWJGLLibraryPath()
			throws OSNotSupportedException {
		int iOS = -1;
		String osname = System.getProperty("os.name").toLowerCase();
		String osarch = System.getProperty("os.arch").toLowerCase();

		// applied patch by Carlo Salinari: ignore osarch on windows
		// see http://lwjgl.org/forum/index.php/topic,3726.0.html
		if (osname.startsWith("windows")) {
			iOS = 0;
		} else if (osname.startsWith("mac")) {
			iOS = 1;
		} else if (osname.startsWith("linux")) {
			iOS = 2;
		} else if (osname.startsWith("solaris")) {
			iOS = 3;
		}

		if (iOS >= 0 && iOS < NATIVEPATH.length) {
			String base = "native" + File.separator + NATIVEPATH[iOS];
			return base;
		} else {
			throw new OSNotSupportedException(osname, osarch);
		}
	}

	/**
	 * Returns absolute path of native LWJGL libraries according to
	 * current operating system, this path is also set as 
	 * system property.
	 * 
	 * @param context
	 * @return
	 * @throws OSNotSupportedException
	 * @throws IOException
	 */
	public static String getLWJGLLibraryPath(BundleContext context)
			throws OSNotSupportedException, IOException {
		String base = getRelativeLWJGLLibraryPath();
		Bundle fragment = context.getBundle();

		URL url = FileLocator.resolve(fragment.getEntry(base));
		File fileDir = new File(url.getPath());
		String path = fileDir.getPath();

		System.setProperty(LWJGL_SYSTEM_PROPERTY, path);
		return path;
	}

}
