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
package org.lwjgl.util.applet;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.security.cert.Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

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

	/** Whether the installer has been called */
	public static boolean installed;
	
	/** Whether to hook uninstall rutine. Must be called prior to installation */
	public static boolean disableUninstall = false;
	
	/** Buffer used when copying files */
	private static final byte[] COPY_BUFFER = new byte[4096];

	/** Directory all lwjgl installations go into */
	public static String MASTER_INSTALL_DIR = ".lwjglinstall";
	
	/** Name of the native jar we're expected to load and install */
	public static final String 	NATIVES_PLATFORM_JAR = "/" + LWJGLUtil.getPlatformName() + "_natives.jar";	
	
	private LWJGLInstaller() {
		/* Unused */
	}

	/**
	 * Create a temporary installation of LWJGL.
	 * This will extract the relevant native files (for the platform) into
	 * the user's temp directory, and instruct the LWJGL subsystem to load its
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
			// Validate the certificates of the platform native jar
			HashMap files = (HashMap) 
			AccessController.doPrivileged(new PrivilegedExceptionAction() {
				public Object run() throws Exception {
					return validateCertificates();
				}
			});

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

			// extract natives from jar
			writeFiles(files, path);

			AccessController.doPrivileged(new PrivilegedAction() {
				public Object run() {
					System.setProperty("org.lwjgl.librarypath", path);
					// Make sure jinput knows about the new path too
					System.setProperty("net.java.games.input.librarypath", path);
					return null;
				}
			});
			
			installed = true;
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
	private static HashMap validateCertificates() throws Exception {
		InputStream is = LWJGLInstaller.class.getResourceAsStream(NATIVES_PLATFORM_JAR);
		if(is == null) {
			throw new Exception("Unable to open " + NATIVES_PLATFORM_JAR + ", which was expected to be on the classpath");
		}
		
		// get our certificate chain
		Certificate[] ownCerts = LWJGLInstaller.class.getProtectionDomain().getCodeSource().getCertificates();
		if(ownCerts == null || ownCerts.length == 0) {
			throw new Exception("Unable to get certificate chain for LWJGLInstaller");
		}
		
		// check that each of the entries in the jar is signed by same certificate as LWJGLInstaller
		HashMap files = new HashMap();
		JarInputStream jis = new JarInputStream(is);

		JarEntry native_entry = null;
		while((native_entry = jis.getNextJarEntry()) != null) {
			// skip directories and anything in directories
			// conveniently ignores the manifest
			if(native_entry.isDirectory() || native_entry.getName().indexOf('/') != -1) {
				continue;
			}

			// need to read the file, before the certificate is retrievable
			// since we dont want to do two reads, we store it in memory for later use
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			copyFile(jis, baos, false);
			files.put(native_entry.getName(), baos.toByteArray());				

			// now check the chain of an actual file 
			validateCertificateChain(ownCerts, native_entry.getCertificates());
		}

		return files;
	}

	/**
	 * Validates the certificate chain for a single file
	 * @param ownCerts Chain of certificates to check against
	 * @param native_certs Chain of certificates to check
	 * @return true if the chains match
	 */
	private static void validateCertificateChain(Certificate[] ownCerts, Certificate[] native_certs) throws Exception {
		if(native_certs == null)
			throw new Exception("Unable to validate certificate chain. Native entry did not have a certificate chain at all");
		
		if(ownCerts.length != native_certs.length)
			throw new Exception("Unable to validate certificate chain. Chain differs in length [" + ownCerts.length + " vs " + native_certs.length + "]");
		
		for(int i=0; i<ownCerts.length; i++) {
			if(!ownCerts[i].equals(native_certs[i])) {
				throw new Exception("Certificate mismatch: " + ownCerts[i] + " != " + native_certs[i]);
			}
		}
	}

	/**
	 * Extracts a file in the classpath to a specified dir
	 * 
	 * @param file File to extract
	 * @param path Path to extract to
	 */
	private static void writeFiles(final HashMap files, final String path) {
		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				try {
					for(Iterator i = files.keySet().iterator(); i.hasNext();) {
						String key = (String) i.next();
						File out = new File(path + File.separator + key);
						out.createNewFile();
						InputStream is = new ByteArrayInputStream((byte[]) files.get(key));
						OutputStream os = new BufferedOutputStream(new FileOutputStream(out));
						copyFile(is, os, true);
					}
				} catch (IOException ioe) {
					LWJGLUtil.log("Exception while extracting: " + ioe.getMessage());
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
	private static void copyFile(InputStream is, OutputStream os, boolean closeInput) throws IOException {
		int len;
		while ((len = is.read(COPY_BUFFER)) > 0) {
			os.write(COPY_BUFFER, 0, len);
		}
		
		if(closeInput) {
			is.close();
		}
		os.close();
	}

	/**
	 * Creates the temporary dir to store lwjgl files in.
	 * The temporary dir will be created in the users temp dir and
	 * called 'lwjgl-' and appended System.currentTimeMillis(). A watermark file
	 * called '.lwjglinstaller' will also be created in the directory.
	 * @return Name of temp directory or null if directory creation failed
	 */
	private static String createTemporaryDir(final String user_temp_dir) throws Exception {
		return (String) AccessController.doPrivileged(new PrivilegedExceptionAction() {
			public Object run() throws Exception {
				// create the temp directory
				File tempDir = new File(user_temp_dir + File.separator + ".lwjglinstall" + File.separator + System.currentTimeMillis());
				if(!tempDir.mkdirs()) {
					throw new IOException("Failed to create directory: " + tempDir);
				}

				// add the watermark file
				// TODO: Write some info to the file ?
				File watermark = new File(tempDir.getAbsolutePath() + File.separator + ".lwjglinstaller");
				watermark.createNewFile();
				watermark.deleteOnExit();
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
				File tempDir = new File(temp + File.separator + MASTER_INSTALL_DIR);
				File[] files = tempDir.listFiles(new FileFilter() {

					/*
					 * @see java.io.FileFilter#accept(java.io.File)
					 */
					public boolean accept(File pathname) {
						return isStale(pathname);
					}

					/**
					 * Checks whether the specified directory is an install directory.
					 * This is done by checking for the watermark file
					 * @param directory Directory to check
					 * @return true if the directory is an install directory
					 */
					private boolean isStale(File directory) {
						File installFile = new File(directory.getAbsolutePath() + File.separator + ".lwjglinstaller");
						return !installFile.exists();
					}

				});

				if (files != null) {
					// uninstall each of the dirs
					for (int i = 0; i < files.length; i++) {
						uninstall(files[i]);
					}
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
