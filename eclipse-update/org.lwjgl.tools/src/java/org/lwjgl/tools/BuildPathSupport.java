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
package org.lwjgl.tools;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IAccessRule;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.lwjgl.LibraryPathUtil;
import org.lwjgl.OSNotSupportedException;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;

/**
 * This class resolves the actual LWJGL library path, that is the location
 * of the jars in the org.lwjgl plugin.
 * 
 * Following the "monkey sees, monkey does"-rule, this code was copied and
 * modified from the plugin org.eclipse.jdt.junit . This code is published
 * under the EPL and (c) by IBM and others. 
 * 
 * @see org.eclipse.jdt.internal.ui.wizards.buildpaths.BuildPathSupport
 * @see org.eclipse.jdt.internal.junit.buildpath.BuildPathSupport
 */
public class BuildPathSupport {
	/**
	* Logger for this class
	*/
	private static final Logger log = Logger.getLogger(BuildPathSupport.class.getName());

	public static class LWJGLPluginDescription {
		private final String strBundleId;

		private Bundle bundle = null;

		public LWJGLPluginDescription(String bundleId) {
			strBundleId = bundleId;

		}

		public Bundle getBundle() {
			if (bundle == null)
				bundle = Platform.getBundle(strBundleId);
			return bundle;
		}

		public String getBundleId() {
			return strBundleId;
		}

	}

	public static final LWJGLPluginDescription LWJGL_PLUGIN = new LWJGLPluginDescription(
			"org.lwjgl"); //$NON-NLS-1$

	public static final LWJGLPluginDescription LWJGL_SRC_PLUGIN = new LWJGLPluginDescription(
			"org.lwjgl.source"); //$NON-NLS-1$

	public static final LWJGLPluginDescription LWJGL_DOC_PLUGIN = new LWJGLPluginDescription(
			"org.lwjgl.doc"); //$NON-NLS-1$

	public static final String[] JAR_FILES = { "lwjgl.jar", "lwjgl_util.jar",
			"lwjgl_util_applet.jar", "jinput.jar" };

	public static final String[] SRC_FILES = { "lwjglsrc.zip",
			"lwjgl_utilsrc.zip", "lwjgl_util_applet.zip", null };

	public static final String[] DOC_FILES = { "doc.zip", "doc.zip", "doc.zip",
			"doc.zip" };

	public static String[] NATIVEPATH = new String[] { "windows", "macosx",
			"linux", "solaris" };
	
	

	public static IPath getBundleLocation(LWJGLPluginDescription pluginDesc) {
		Bundle bundle = pluginDesc.getBundle();
		if (bundle == null)
			return null;

		URL local = null;
		try {
			local = FileLocator.toFileURL(bundle.getEntry("/")); //$NON-NLS-1$
		} catch (IOException e) {
			return null;
		}
		String fullPath = new File(local.getPath()).getAbsolutePath();
		return Path.fromOSString(fullPath);
	}
	
	

	/**
	 * 
	 * /Devel/Applications/Eclipse3.4/plugins/org.lwjgl.source_2.0.1/src/org.lwjgl_2.0.1/lwjglsrc.zip
	 * /Devel/Applications/Eclipse3.4/plugins/org.lwjgl.source_2.0.1/src/org.lwjgl_2.0.1/lwjgl_utilsrc.zip
	 * /Devel/Applications/Eclipse3.4/plugins/org.lwjgl.source_2.0.1/src/org.lwjgl_2.0.1/lwjgl_util_applet.zip
	 * 
	 * 
	 * @param pluginDesc
	 * @return
	 */
	public static IPath getSourceLocation(String filename) {
		if (filename==null)
			return null;
		Bundle bundleSrc = LWJGL_SRC_PLUGIN.getBundle();
		if (bundleSrc == null)
			return null;

		String version = (String) bundleSrc.getHeaders().get(
				Constants.BUNDLE_VERSION);
		if (version == null) {
			return null;
		}

		String bundlePath = getURL(bundleSrc);
		if (bundlePath == null) {
			return null;
		}

		File bundleLoc = new File(bundlePath);
		if (bundleLoc.isDirectory()) {
			String fullPath = bundleLoc.getAbsolutePath() + File.separator
					+ "src" + File.separator + LWJGL_PLUGIN.getBundleId() + '_'
					+ version + File.separator + filename;
			return Path.fromOSString(fullPath);
		} else if (bundleLoc.isFile()) {
			return Path.fromOSString(bundleLoc.getAbsolutePath());
		}

		return null;
	}

	/**
	 * jar:file:/Devel/Applications/Eclipse3.4/plugins/org.lwjgl.doc_2.0.1/doc.zip!/javadoc
	 * 
	 * @param pluginDesc
	 * @param filename 
	 * @return
	 */
	public static String getJavadocLocation(String filename) {
		if (filename==null)
			return null;
		
		Bundle bundleDoc = LWJGL_DOC_PLUGIN.getBundle();
		if (bundleDoc == null)
			return null;

		String version = (String) bundleDoc.getHeaders().get(
				Constants.BUNDLE_VERSION);
		if (version == null) {
			return null;
		}

		String bundlePath = getURL(bundleDoc);
		if (bundlePath == null) {
			return null;
		}

		File bundleLoc = new File(bundlePath);
		if (bundleLoc.isDirectory()) {
			String fullPath = "jar:file:" + bundleLoc.getAbsolutePath()
					+ File.separator +  filename + "!"
					+ File.separator + "javadoc" + File.separator;
			return fullPath;
		} else if (bundleLoc.isFile()) {
			return bundleLoc.getAbsolutePath();
		}

		return null;
	}
	
	public static String getNativeLocation() {
		
		String basePath;
		try {
			basePath = LibraryPathUtil.getRelativeLWJGLLibraryPath();
		} catch (OSNotSupportedException ex) {
			log.warning(ex.toString()); //$NON-NLS-1$
			return null;
		}
		
		Bundle bundle = LWJGL_PLUGIN.getBundle();
		if (bundle == null)
			return null;

		String bundlePath = getURL(bundle);
		if (bundlePath == null) {
			return null;
		}

		File bundleLoc = new File(bundlePath);
		if (bundleLoc.isDirectory()) {
			String fullPath = bundleLoc.getAbsolutePath()
					+ File.separator + basePath;
			return fullPath;
		} else if (bundleLoc.isFile()) {
			return null;
		}

		return null;
	}

	private static String getURL(Bundle bundle) {
		try {
			URL fileURL = FileLocator.toFileURL(bundle.getEntry("/")); //$NON-NLS-1$
			if (fileURL != null) {
				return fileURL.getFile();
			}
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public static IClasspathEntry getLWJGLClasspathEntry() {
		return JavaCore
				.newContainerEntry(LWJGLClasspathContainerInitializer.LWJGL_LIBRARY_PATH);
	}

	public static IClasspathEntry[] getLWJGLLibraryEntries() {
		IPath bundleBase = getBundleLocation(LWJGL_PLUGIN);
		if (bundleBase != null) {
			IClasspathEntry[] entries = new IClasspathEntry[JAR_FILES.length];
			for (int i = 0; i < JAR_FILES.length; i++) {
				IPath jarLocation = bundleBase.append(JAR_FILES[i]); //$NON-NLS-1$
				IPath srcLocation = getSourceLocation(SRC_FILES[i]);
				String nativeLocation = getNativeLocation();
				String javadocLocation = getJavadocLocation(DOC_FILES[i]);
				IAccessRule[] accessRules = {};
				IClasspathAttribute[] attributes = { // 
						JavaCore
								.newClasspathAttribute(
										IClasspathAttribute.JAVADOC_LOCATION_ATTRIBUTE_NAME,
										javadocLocation),
						JavaCore.newClasspathAttribute(
								JavaRuntime.CLASSPATH_ATTR_LIBRARY_PATH_ENTRY,
								nativeLocation) };

				// return JavaCore.newClasspathAttribute(JavaRuntime.CLASSPATH_ATTR_LIBRARY_PATH_ENTRY, dialog.getNativeLibraryPath());

				entries[i] = JavaCore.newLibraryEntry(jarLocation, srcLocation,
						null, accessRules, attributes, false);
			}
			return entries;

		}
		return null;
	}
}
