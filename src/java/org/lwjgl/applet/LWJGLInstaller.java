/* 
 * Copyright (c) 2006 LWJGL Project
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
package org.lwjgl.applet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;

import org.lwjgl.LWJGLUtil;

/**
 * <p>
 * 
 * </p>
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class LWJGLInstaller {

	/** 
	 * Files to install for each supported platform
	 * @see org.lwjgl.LWJGLUtil#getPlatform() 
	 */
	public static final String[][]	PLATFORM_FILES = {
		{ "lwjgl", "lwjgl-fmod3", "lwjgl-devil", "openal", "fmod", "IL", "ILU", "ILUT", "jinput-osx"},
		{ "lwjgl", "lwjgl-fmod3", "lwjgl-devil", "openal", "fmod", "IL", "ILU", "ILUT", "jinput-linux"},
		{ "lwjgl", "lwjgl-fmod3", "lwjgl-devil", "OpenAL32", "fmod", "DevIL", "ILU", "ILUT", "jinput-dx8", "jinput-raw"}
	};

	/** Whether the installer has been called */
	public static boolean installed;
	
	/** Whether to hook uninstall rutine. Must be called prior to installation */
	public static boolean disableUninstall = false;
	
	/** Buffer used when copying files */
	private static final byte[] COPY_BUFFER = new byte[4096];
	
	private LWJGLInstaller() {
		/* Unused */
	}

	/**
	 * Create a temporary installation of LWJGL.
	 * This will extract the relevant native files (for the platform) into
	 * the users temp directory, and instruct the LWJGL subsystem to load its
	 * native files from there.
	 * The files required by the installer, are gotten from the classloader via its
	 * getResource command, and are assumed to in the path: /native/<win32|linux|macosx>/
	 * Any call to this method will also add a shutdown hook to the uninstall of the libraries
	 * Note: Due to the nature of native libraries, we cannot actually uninstall the currently
	 * loaded files, but rather the "last" installed. This means that the most recent install of LWJGL
	 * will always be present in the users temp dir.
	 * 
	 * @see java.lang.ClassLoader#getResource(String)
	 */
	public static void tempInstall() throws Exception {
		// only need to install once
		if (installed) {
			return;
		}

		try {
			// libraries to validate and install
			String[] libraries = PLATFORM_FILES[LWJGLUtil.getPlatform() - 1];

			// Validate the certificates of the native files
			validateCertificates();

			// install shutdown installer hook
			if(!disableUninstall) {
				AccessController.doPrivileged(new PrivilegedAction() {
					public Object run() {
						Runtime.getRuntime().addShutdownHook(new Thread() {
							public void run() {
								uninstall();
							}
						});
						return null;
					}
				});
			}

			// create a temporary dir for the native files
			String user_temp_dir = getPriviledgedString("java.io.tmpdir");
			final String path = createTemporaryDir(user_temp_dir);

			// extract natives
			for (int i = 0; i < libraries.length; i++) {
				String library = System.mapLibraryName(libraries[i]);
				extract(library, path);
			}

			AccessController.doPrivileged(new PrivilegedAction() {
				public Object run() {
					System.setProperty("org.lwjgl.librarypath", path);
					return null;
				}
			});
		} catch (Exception e) {
			LWJGLUtil.log("Failed extraction e = " + e.getMessage());
			uninstall();
			throw e;
		}
	}

	/**
	 * Validates the certificates of the native libraries.
	 * When installing native libraries, it is imperative that we also check the certficates.
     * The reson for this, is that a user may inject a malicious jar to the classpath
	 * before the "real" LWJGL jar, containing native libraries with unwanted code.
	 * By forcing all the native libraries to have the same certificate as the signed
	 * installer, we can also be sure that the native libraries indeed are correct.
	 * @throws Exception If we encounter a certificate mismatch
	 */
	private static void validateCertificates() throws Exception {
		/* TODO */
	}

	/**
	 * Extracts a file in the classpath to a specified dir
	 * 
	 * @param file File to extract
	 * @param path Path to extract to
	 */
	private static void extract(final String file, final String path) {
		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				// check for existing file, and get out
				File out = new File(path + File.separator + file);
				if (out.exists()) {
					return null;
				}

				// create the new file and copy it to its destination
				try {
					out.createNewFile();
					String in = "/native/" + LWJGLUtil.getPlatformName() + "/" + file;
					OutputStream os = new BufferedOutputStream(new FileOutputStream(out));
					InputStream is = new BufferedInputStream(getClass().getResourceAsStream(in));
					
					// Sanity check
					// ===========================================
					if (os == null) {
						LWJGLUtil.log("Unable to write to outputstream at " + out.getAbsolutePath());
						return null;
					}

					if (is == null) {
						LWJGLUtil.log("Unable to read classpath inputstream from " + in);
						return null;
					}
					// -------------------------------------------
					
					// copy the actual file
					copyFile(is, os);
				} catch (IOException ioe) {
					LWJGLUtil.log("Exception while extracting " + file + ": " + ioe.getMessage());
					return null;
				}
				return null;
			}
		});
	}

	/**
	 * Copies an inputstream to an outputstream
	 * @param is InputStream to read from
	 * @param os OutputStream to write to
	 * @throws IOException if the copy process fail in any way
	 */
	static void copyFile(InputStream is, OutputStream os) throws IOException {
		int len;
		while ((len = is.read(COPY_BUFFER)) > 0) {
			os.write(COPY_BUFFER, 0, len);
		}
		is.close();
		os.close();
	}

	/**
	 * Creates the temporary dir to store lwjgl files in.
	 * The temporary dir will be created in the users temp dir and
	 * called 'lwjgl-' and appended System.currentTimeMillis(). A watermark file
	 * called '.lwjglinstaller' will also be created in the directory.
	 * @return Name of temp directory or null if directory creation failed
	 */
	static String createTemporaryDir(final String user_temp_dir) throws Exception {
		return (String) AccessController.doPrivileged(new PrivilegedExceptionAction() {
			public Object run() {
				// create the temp directory
				File tempDir = new File(user_temp_dir + File.separator + "lwjgl-" + System.currentTimeMillis());
				if(!tempDir.mkdir()) {
					throw new IOException("Failed to create directory: " + tempDir);
				}

				// add the watermark file
				// TODO: Write some info to the file ?
				File watermark = new File(tempDir.getAbsolutePath() + File.separator + ".lwjglinstaller");
				watermark.createNewFile();
				return tempDir.getAbsolutePath();
			}
		});
	}
	

	/**
	 * Gets a property as a privileged action.
	 */
	private static String getPriviledgedString(final String property) {
		return (String) AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				return System.getProperty(property);
			}
		});
	}	

	/**
	 * Uninstalls any PREVIOUS installations
	 * We cannot uninstall the current installation, since the files are locked
	 * by the VM.
	 */
	private static void uninstall() {
		LWJGLUtil.log("running LWJGL uninstaller");
		
		// locate all installer dirs and uninstall them
		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				String temp = System.getProperty("java.io.tmpdir");
				File tempDir = new File(temp);
				File[] files = tempDir.listFiles(new FileFilter() {

					/*
					 * @see java.io.FileFilter#accept(java.io.File)
					 */
					public boolean accept(File pathname) {
						return pathname.getAbsolutePath().indexOf("lwjgl") != -1 && isInstallDirectory(pathname);
					}

					/**
					 * Checks whether the specified directory is an install directory.
					 * This is done by checking for the watermark file
					 * @param directory Directory to check
					 * @return true if the directory is an install directory
					 */
					private boolean isInstallDirectory(File directory) {
						File installFile = new File(directory.getAbsolutePath() + File.separator + ".lwjglinstaller");
						return installFile.exists();
					}

				});

				// uninstall each of the dirs
				for (int i = 0; i < files.length; i++) {
					uninstall(files[i]);
				}
				return null;
			}
		});
	}

	/**
	 * Uninstall LWJGL from a directory. This deletes all the files in the directory
	 * and deletes the directory too.
	 * @param file directory to uninstall LWJGL from
	 */
	private static void uninstall(File file) {
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			files[i].delete();
		}
		file.delete();
	}
}
