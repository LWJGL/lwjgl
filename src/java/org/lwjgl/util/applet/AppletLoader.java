/*
 * Copyright (c) 2002-2008 LWJGL Project
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

import java.applet.Applet;
import java.applet.AppletStub;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.JarURLConnection;
import java.net.SocketPermission;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.PrivilegedExceptionAction;
import java.security.SecureClassLoader;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;

import sun.security.util.SecurityConstants;

/**
 * <p>
 * The AppletLoader enables deployment of LWJGL to applets in an easy
 * and polished way. The loader will display a configurable logo and progressbar
 * while the relevant jars (generic and native) are downloaded from a specified source.
 * </p>
 * <p>
 * The downloaded jars are extracted to the users temporary directory - and if enabled, cached for
 * faster loading in future uses.
 * </p>
 * <p>
 * The following applet parameters are required:
 * <ul>
 * <li>al_main - [String] Full package and class the applet to instantiate and display when loaded.</li>
 * <li>al_logo - [String Path of of the logo resource to paint while loading.</li>
 * <li>al_progressbar - [String] Path of the progressbar resource to paint on top of the logo, width clipped by percentage.</li>
 * <li>al_jars - [String] Comma seperated list of jars to download.</li>
 * <li>al_windows - [String] Jar containing native files for windows.</li>
 * <li>al_linux - [String] Jar containing native files for linux.</li>
 * <li>al_mac - [String] Jar containing native files for mac.</li>
 * <li>al_solaris - [String] Jar containing native files for solaris.</li>
 * <li>al_freebsd - [String] Jar containing native files for freebsd.</li>
 * </ul>
 * </p>
 * <p>
 * Additionally the following parameters can be supplied to tweak the behaviour of the AppletLoader.
 * <ul>
 * <li>al_version - [int or float] Version of deployment. If this is specified, the jars will be cached and
 * reused if the version matches. If version doesn't match all of the files are reloaded.</li>
 * <li>al_cache - [boolean] Whether to use cache system. <i>Default: true</i>.</li>
 * <li>al_debug - [boolean] Whether to enable debug mode. <i>Default: false</i>.</li>
 * <li>al_prepend_host - [boolean] Whether to limit caching to this domain, disable if your applet is hosted on multple domains and needs to share the cache. <i>Default: true</i>.</li>
 * <ul>
 * <li>al_windows64 - [String] If specified it will be used instead of al_windows on 64bit windows systems.</li>
 * <li>al_windows32 - [String] If specifed it will be used instead of al_windows on 32bit windows systems.</li>
 * <li>al_linux64 - [String] If specifed it will be used instead of al_linux on 64bit linux systems.</li>
 * <li>al_linux32 - [String] If specifed it will be used instead of al_linux on 32bit linux systems.</li>
 * <ul>
 * <li>boxbgcolor - [String] any String AWT color ("red", "blue", etc), RGB (0-255) or hex formated color (#RRGGBB) to use as background. <i>Default: #ffffff</i>.</li>
 * <li>boxfgcolor - [String] any String AWT color ("red", "blue", etc), RGB (0-255) or hex formated color (#RRGGBB) to use as foreground. <i>Default: #000000</i>.</li>
 * </ul>
 * </p>
 * @author kappaOne
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class AppletLoader extends Applet implements Runnable, AppletStub {

	/** initializing */
	public static final int STATE_INIT 						= 1;

	/** determining which packages that are required */
	public static final int STATE_DETERMINING_PACKAGES 		= 2;

	/** checking for already downloaded files */
	public static final int STATE_CHECKING_CACHE 			= 3;

	/** downloading packages */
	public static final int STATE_DOWNLOADING 				= 4;

	/** extracting packages */
	public static final int STATE_EXTRACTING_PACKAGES 		= 5;

	/** updating the classpath */
	public static final int STATE_UPDATING_CLASSPATH 		= 6;

	/** switching to real applet */
	public static final int STATE_SWITCHING_APPLET 			= 7;

	/** initializing real applet */
	public static final int STATE_INITIALIZE_REAL_APPLET	= 8;

	/** stating real applet */
	public static final int STATE_START_REAL_APPLET 		= 9;

	/** done */
	public static final int STATE_DONE 						= 10;

	/** used to calculate length of progress bar */
	protected int		percentage;

	/** current size of download in bytes */
	protected int		currentSizeDownload;

	/** total size of download in bytes */
	protected int		totalSizeDownload;

	/** current size of extracted in bytes */
	protected int		currentSizeExtract;

	/** total size of extracted in bytes */
	protected int		totalSizeExtract;

	/** logo to be shown while loading */
	protected Image		logo, logoBuffer;

	/** progressbar to render while loading */
	protected Image		progressbar, progressbarBuffer;

	/** offscreen image used */
	protected Image 	offscreen;

	/** set to true while painting is done */
	protected boolean 	painting;

	/** background color of applet */
	protected Color		bgColor 	= Color.white;

	/** color to write foreground in */
	protected Color		fgColor 	= Color.black;

	/** urls of the jars to download */
	protected URL[]		urlList;

	/** classLoader used to add downloaded jars to the classpath */
	protected ClassLoader classLoader;

	/** actual thread that does the loading */
	protected Thread	loaderThread;

	/** animation thread that renders our load screen while loading */
	protected Thread 	animationThread;

	/** applet to load after all downloads are complete */
	protected Applet	lwjglApplet;

	/** whether a fatal error occured */
	protected boolean	fatalError;

	/** whether we're running in debug mode */
	protected boolean 	debugMode;

	/** whether to prepend host to cache path */
	protected boolean 	prependHost;

	/** Used to store file names with lastModified time */
	protected HashMap<String, Long> 	filesLastModified;

	/** Sizes of files to download */
	protected int[] 	fileSizes;

	/** whether to use caching system, only download files that have changed */
	protected boolean 	cacheEnabled;

	/** String to display as a subtask */
	protected String	subtaskMessage = "";

	/** state of applet loader */
	protected int		state = STATE_INIT;

	/** whether lzma is supported */
	protected boolean 	lzmaSupported;

	/** whether pack200 is supported */
	protected boolean 	pack200Supported;

	/** generic error message to display on error */
	protected String[] 	genericErrorMessage = {	"An error occured while loading the applet.",
												"Please contact support to resolve this issue.",
												"<placeholder for error message>"};

	/** whether a certificate refused error occured */
	protected boolean	certificateRefused;

	/** error message to display if user refuses to accept certicate*/
	protected String[] 	certificateRefusedMessage = { "Permissions for Applet Refused.",
												      "Please accept the permissions dialog to allow",
												      "the applet to continue the loading process."};

	/** have natives been loaded by another instance of this applet */
	protected static boolean natives_loaded;

	/*
	 * @see java.applet.Applet#init()
	 */
	public void init() {
		state = STATE_INIT;
		
		// sanity check
		String[] requiredArgs = {"al_main", "al_logo", "al_progressbar", "al_jars"};
		for ( String requiredArg : requiredArgs ) {
			if ( getParameter(requiredArg) == null ) {
				fatalErrorOccured("missing required applet parameter: " + requiredArg, null);
				return;
			}
		}

		// whether to use cache system
		cacheEnabled	= getBooleanParameter("al_cache", true);

		// whether to run in debug mode
		debugMode 		= getBooleanParameter("al_debug", false);

		// whether to prepend host to cache path
		prependHost 	= getBooleanParameter("al_prepend_host", true);

		// get colors of applet
		bgColor 		= getColor("boxbgcolor", Color.white);
		setBackground(bgColor);
		fgColor 		= getColor("boxfgcolor", Color.black);

		// load logos, if value is "" then skip
		if (getParameter("al_logo").length() > 0) {
			logo 		= getImage(getParameter("al_logo"));
		}
		if (getParameter("al_progressbar").length() > 0) {
			progressbar = getImage(getParameter("al_progressbar"));
		}

		// check for lzma support
		try {
			Class.forName("LZMA.LzmaInputStream");
			lzmaSupported = true;
		} catch (Throwable e) {
			/* no lzma support */
		}

		// check pack200 support
		try {
			java.util.jar.Pack200.class.getSimpleName();
			pack200Supported = true;
		} catch (Throwable e) {
			/* no pack200 support */
		}
	}

	/**
	 * Generates a stacktrace in the form of a string
	 * @param exception Exception to make stacktrace of
	 * @return Stacktrace of exception in the form of a string
	 */
	private static String generateStacktrace(Exception exception) {
		Writer result = new StringWriter();
		PrintWriter printWriter = new PrintWriter(result);
		exception.printStackTrace(printWriter);
		return result.toString();
	}

	/*
	 * @see java.applet.Applet#start()
	 */
	public void start() {
		if (lwjglApplet != null) {
			lwjglApplet.start();
		}
		else {
			if(loaderThread == null && !fatalError) {
				loaderThread = new Thread(this);
				loaderThread.setName("AppletLoader.loaderThread");
				loaderThread.start();

				animationThread = new Thread() {
					public void run() {
						while(loaderThread != null) {
							repaint();
							AppletLoader.this.sleep(100);
						}
						animationThread = null;
					}
				};
				animationThread.setName("AppletLoader.animationthread");
				animationThread.start();
			}
		}
	}

	/*
	 * @see java.applet.Applet#stop()
	 */
	public void stop() {
		if (lwjglApplet != null) {
			lwjglApplet.stop();
		}
	}

	/*
	 * @see java.applet.Applet#destroy()
	 */
	public void destroy() {
		if (lwjglApplet != null) {
			lwjglApplet.destroy();
		}
	}

	/**
	 * Clean up resources
	 */
	protected void cleanUp() {
		progressbar = null;
		logo 		= null;

		logoBuffer = null;
		progressbarBuffer = null;

		offscreen = null;
	}

	/**
	 * Retrieves the applet that has been loaded. Useful for liveconnect.
	 */
	public Applet getApplet() {
		return lwjglApplet;
	}

	/**
	 * Transfers the call of AppletResize from the stub to the lwjglApplet.
	 */
	public void appletResize(int width, int height) {
		resize(width, height);
	}

	/*
	 * @see java.awt.Container#update(java.awt.Graphics)
	 */
	public final void update(Graphics g) {
		paint(g);
	}

	/*
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		// don't paint loader if applet loaded
		if(state == STATE_DONE) {
			return;
		}

		// create offscreen if missing
		if (offscreen == null) {
			offscreen = createImage(getWidth(), getHeight());

			// create buffers for animated gifs
			if (logo != null) {
				logoBuffer = createImage(logo.getWidth(null), logo.getHeight(null));
				// add image observer, it will notify when next animated gif frame is ready
				offscreen.getGraphics().drawImage(logo, 0, 0, this);
				// in case image is not animated fill image buffer once
				imageUpdate(logo, ImageObserver.FRAMEBITS, 0, 0, 0, 0);
			}

			if (progressbar != null) {
				progressbarBuffer = createImage(progressbar.getWidth(null), progressbar.getHeight(null));
				// add image observer, it will notify when next animated gif frame is ready
				offscreen.getGraphics().drawImage(progressbar, 0, 0, this);
				// in case image is not animated fill image buffer once
				imageUpdate(progressbar, ImageObserver.FRAMEBITS, 0, 0, 0, 0);
			}
		}

		// draw everything onto an image before drawing to avoid flicker
		Graphics og = offscreen.getGraphics();
		FontMetrics fm = og.getFontMetrics();

		// clear background color
		og.setColor(bgColor);
		og.fillRect(0, 0, offscreen.getWidth(null), offscreen.getHeight(null));

		og.setColor(fgColor);
		String message = getDescriptionForState();

		// if we had a failure of some sort, notify the user
		if (fatalError) {
			String[] errorMessage = (certificateRefused) ? certificateRefusedMessage : genericErrorMessage;

			for(int i=0; i<errorMessage.length; i++) {
				if(errorMessage[i] != null) {
					int messageX = (offscreen.getWidth(null) - fm.stringWidth(errorMessage[i])) / 2;
					int messageY = (offscreen.getHeight(null) - (fm.getHeight() * errorMessage.length)) / 2;

					og.drawString(errorMessage[i], messageX, messageY + i*fm.getHeight());
				}
			}
		} else {
			og.setColor(fgColor);

			painting = true;

			// get position at the middle of the offscreen buffer
			int x = offscreen.getWidth(null)/2;
			int y = offscreen.getHeight(null)/2;

			// draw logo
			if (logo != null) {
				og.drawImage(logoBuffer, x-logo.getWidth(null)/2, y-logo.getHeight(null)/2, this);
			}

			// draw message
			int messageX = (offscreen.getWidth(null) - fm.stringWidth(message)) / 2;
			int messageY = y + 20;

			if (logo != null) messageY += logo.getHeight(null)/2;
			else if (progressbar != null) messageY += progressbar.getHeight(null)/2;

			og.drawString(message, messageX, messageY);

			// draw subtaskmessage, if any
			if(subtaskMessage.length() > 0) {
				messageX = (offscreen.getWidth(null) - fm.stringWidth(subtaskMessage)) / 2;
				og.drawString(subtaskMessage, messageX, messageY+20);
			}

			// draw loading bar, clipping it depending on percentage done
			if (progressbar != null) {
				int barSize = (progressbar.getWidth(null) * percentage) / 100;
				og.clipRect(x-progressbar.getWidth(null)/2, 0, barSize, offscreen.getHeight(null));
				og.drawImage(progressbarBuffer, x-progressbar.getWidth(null)/2, y-progressbar.getHeight(null)/2, this);
			}

			painting = false;
		}

		og.dispose();

		// finally draw it all centred
		g.drawImage(offscreen, (getWidth() - offscreen.getWidth(null))/2, (getHeight() - offscreen.getHeight(null))/2, null);
	}

	/**
	 * When an animated gif frame is ready to be drawn the ImageObserver
	 * will call this method.
	 *
	 * The Image frame is copied into a buffer, which is then drawn.
	 * This is done to prevent image tearing on gif animations.
	 */
	public boolean imageUpdate(Image img, int flag, int x, int y, int width, int height) {

		// finish with this ImageObserver
		if (state == STATE_DONE) return false;

		// if image frame is ready to be drawn and is currently not being painted
		if (flag == ImageObserver.FRAMEBITS && !painting) {
			Image buffer;

			// select which buffer to fill
			if (img == logo) buffer = logoBuffer;
			else buffer = progressbarBuffer;

			Graphics g = buffer.getGraphics();

			// clear background on buffer
			g.setColor(bgColor);
			g.fillRect(0, 0, buffer.getWidth(null), buffer.getHeight(null));

			// buffer background is cleared, so draw logo under progressbar
			if (img == progressbar && logo != null) {
				g.drawImage(logoBuffer, progressbar.getWidth(null)/2-logo.getWidth(null)/2,
										progressbar.getHeight(null)/2-logo.getHeight(null)/2, null);
			}

			g.drawImage(img, 0, 0, this);
			g.dispose();

			repaint();
		}

		return true;
	}

	/**
	 * @return string describing the state of the loader
	 */
	protected String getDescriptionForState() {
		switch (state) {
			case STATE_INIT:
				return "Initializing loader";
			case STATE_DETERMINING_PACKAGES:
				return "Determining packages to load";
			case STATE_CHECKING_CACHE:
				return "Calculating download size";
			case STATE_DOWNLOADING:
				return "Downloading packages";
			case STATE_EXTRACTING_PACKAGES:
				return "Extracting downloaded packages";
			case STATE_UPDATING_CLASSPATH:
				return "Updating classpath";
			case STATE_SWITCHING_APPLET:
				return "Switching applet";
			case STATE_INITIALIZE_REAL_APPLET:
				return "Initializing real applet";
			case STATE_START_REAL_APPLET:
				return "Starting real applet";
			case STATE_DONE:
				return "Done loading";
			default:
				return "unknown state";
		}
	}

	/**
	 * Trims the passed file string based on the available capabilities
	 * @param file string of files to be trimmed
	 * @return trimmed string based on capabilities of client
	 */
	protected String trimExtensionByCapabilities(String file) {
		if (!pack200Supported) {
			file = file.replaceAll(".pack", "");
		}

		if (!lzmaSupported) {
			file = file.replaceAll(".lzma", "");
		}
		return file;
	}

	/**
	 * Reads list of jars to download and adds the urls to urlList
	 * also finds out which OS you are on and adds appropriate native
	 * jar to the urlList
	 */
	protected void loadJarURLs() throws Exception {
		state = STATE_DETERMINING_PACKAGES;

		// jars to load
		String jarList = getParameter("al_jars");

		jarList = trimExtensionByCapabilities(jarList);

		StringTokenizer jar = new StringTokenizer(jarList, ", ");

		int jarCount = jar.countTokens() + 1;

		urlList = new URL[jarCount];

		URL path = getCodeBase();

		// set jars urls
		for (int i = 0; i < jarCount - 1; i++) {
			urlList[i] = new URL(path, jar.nextToken());
		}

		// native jar url
		String osName 		= System.getProperty("os.name");
		String nativeJar 	= null;

		if (osName.startsWith("Win")) {

			// check if arch specific natives have been specified
			if (System.getProperty("os.arch").endsWith("64")) {
				nativeJar = getParameter("al_windows64");
			} else {
				nativeJar = getParameter("al_windows32");
			}

			if (nativeJar == null) {
				nativeJar = getParameter("al_windows");
			}

		} else if (osName.startsWith("Linux")) {

			// check if arch specific natives have been specified
			if (System.getProperty("os.arch").endsWith("64")) {
				nativeJar = getParameter("al_linux64");
			} else {
				nativeJar = getParameter("al_linux32");
			}

			if (nativeJar == null) {
				nativeJar = getParameter("al_linux");
			}

		} else if (osName.startsWith("Mac")) {
			nativeJar = getParameter("al_mac");
		} else if (osName.startsWith("Solaris") || osName.startsWith("SunOS")) {
			nativeJar = getParameter("al_solaris");
		} else if (osName.startsWith("FreeBSD")) {
			nativeJar = getParameter("al_freebsd");
		} else {
			fatalErrorOccured("OS (" + osName + ") not supported", null);
		}

		if (nativeJar == null) {
			fatalErrorOccured("no lwjgl natives files found", null);
		} else {
			nativeJar = trimExtensionByCapabilities(nativeJar);
			urlList[jarCount - 1] = new URL(path, nativeJar);
		}
	}

	/**
	 * 4 steps
	 *
	 * 1) check version of applet and decide whether to download jars
	 * 2) download the jars
	 * 3) extract natives
	 * 4) add to jars to class path
	 * 5) switch applets
	 */
	public void run() {
		state = STATE_CHECKING_CACHE;

 		percentage = 5;

 		try {
			debug_sleep(2000);

			// parse the urls for the jars into the url list
			loadJarURLs();

			// get path where applet will be stored
			String path = AccessController.doPrivileged(new PrivilegedExceptionAction<String>() {
				public String run() throws Exception {

					// we append the code base to avoid naming collisions with al_title
					String codebase = "";
					if(prependHost) {
						codebase = getCodeBase().getHost();
						if(codebase == null || codebase.length() == 0) {
							codebase = "localhost";
						}
						codebase += File.separator;
					}
					return getCacheDir() + File.separator + codebase + getParameter("al_title") + File.separator;
				}
			});

			File dir = new File(path);

			// create directory
			if (!dir.exists()) {
				dir.mkdirs();
			}

			File versionFile = new File(dir, "version");

			// if specified applet version already available don't download anything
			boolean versionAvailable = false;

			// version of applet
			String version = getParameter("al_version");
			float latestVersion = 0;

			// if applet version specifed, check if you have latest version of applet
			if (version != null) {

				latestVersion = Float.parseFloat(version);

				// if version file exists
				if (versionFile.exists()) {
					// compare to new version
					if (latestVersion != readVersionFile(versionFile)) {
						versionAvailable = true;
						percentage = 90;

						if(debugMode) {
							System.out.println("Loading Cached Applet Version " + latestVersion);
						}
						debug_sleep(2000);
					}
				}
			}

			// if jars not available or need updating download them
			if (!versionAvailable) {
				// get jars file sizes and check cache
				getJarInfo(dir);		// 5-15%

				// downloads jars from the server
				downloadJars(path);		// 15-55%

				// Extract Pack and LZMA files
				extractJars(path);		// 55-65%

				// Extracts Native Files
				extractNatives(path);	// 65-85%

				// save version information once jars downloaded successfully
				if (version != null) {
					percentage = 90;
					writeVersionFile(versionFile, latestVersion);
				}

				// save file names with last modified info once downloaded successfully
				writeCacheFile(new File(dir, "cache"), filesLastModified);
			}

			// add the downloaded jars and natives to classpath
			updateClassPath(path);

			// switch to LWJGL Applet
			switchApplet();

			state = STATE_DONE;
			// clean up resources
			cleanUp();
		} catch (AccessControlException ace) {
			fatalErrorOccured(ace.getMessage(), ace);
			certificateRefused = true;
		} catch (Exception e) {
			fatalErrorOccured("This occurred while '" + getDescriptionForState() + "'", e);
		} finally {
			loaderThread = null;
		}
	}
	
	/**
	 * get path to the lwjgl cache directory
	 * 
	 * @return path to the lwjgl cache directory
	 */
	protected String getCacheDir() {
		String cacheDir = System.getProperty("deployment.user.cachedir");
		
		if (cacheDir == null || System.getProperty("os.name").startsWith("Win")) {
			cacheDir = System.getProperty("java.io.tmpdir");
		}
		
		return cacheDir + File.separator + "lwjglcache";
	}

	/**
	 * read the current version file
	 *
	 * @param file the file to read
	 * @return the version value of saved file
	 * @throws Exception if it fails to read value
	 */
	protected float readVersionFile(File file) throws Exception {
		DataInputStream dis = new DataInputStream(new FileInputStream(file));
		float version = dis.readFloat();
		dis.close();
		return version;
	}

	/**
	 * write out version file of applet
	 *
	 * @param file the file to write out to
	 * @param version the version of the applet as a float
	 * @throws Exception if it fails to write file
	 */
	protected void writeVersionFile(File file, float version) throws Exception {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
		dos.writeFloat(version);
		dos.close();
	}

	/**
	 * read the current cache file
	 *
	 * @param file the file to read
	 * @return the hashmap containing the files names and lastModified times
	 * @throws Exception if it fails to read hashmap
	 */
	@SuppressWarnings("unchecked")
	protected HashMap<String, Long> readCacheFile(File file) throws Exception {
		ObjectInputStream dis = new ObjectInputStream(new FileInputStream(file));
		HashMap<String, Long> hashMap = (HashMap<String, Long>)dis.readObject();
		dis.close();
		return hashMap;
	}

	/**
	 * write out cache file of applet
	 *
	 * @param file the file to write out to
	 * @param filesLastModified the hashmap containing files names and lastModified times
	 * @throws Exception if it fails to write file
	 */
	protected void writeCacheFile(File file, HashMap<String, Long> filesLastModified) throws Exception {
		ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream(file));
		dos.writeObject(filesLastModified);
		dos.close();
	}

	/**
	 * Edits the ClassPath at runtime to include the jars
	 * that have just been downloaded and then adds the
	 * lwjgl natives folder property.
	 *
	 * @param path location where applet is stored
	 * @throws Exception if it fails to add classpath
	 */
	protected void updateClassPath(String path) throws Exception {

		state = STATE_UPDATING_CLASSPATH;

		percentage = 95;

		URL[] urls = new URL[urlList.length];

		for (int i = 0; i < urlList.length; i++) {
			urls[i] = new URL("file:" + path + getJarName(urlList[i]));
		}

		// add downloaded jars to the classpath with required permissions
		classLoader = new URLClassLoader(urls) {
			protected PermissionCollection getPermissions (CodeSource codesource) {
				PermissionCollection perms = null;

				try {
					// getPermissions from original classloader is important as it checks for signed jars and shows any security dialogs needed
					Method method = SecureClassLoader.class.getDeclaredMethod("getPermissions", new Class[] { CodeSource.class });
					method.setAccessible(true);
					perms = (PermissionCollection)method.invoke(getClass().getClassLoader(), new Object[] {codesource});

					String host = getCodeBase().getHost();

			        if (host != null && (host.length() > 0)) {
			        	// add permission for downloaded jars to access host they were from
			        	perms.add(new SocketPermission(host, SecurityConstants.SOCKET_CONNECT_ACCEPT_ACTION));
			        }
			        else if ( "file".equals(codesource.getLocation().getProtocol()) ) {
			        	// if running locally add file permission
			        	String path = codesource.getLocation().getFile().replace('/', File.separatorChar);
			            perms.add(new FilePermission(path, SecurityConstants.FILE_READ_ACTION));
			        }

		        } catch (Exception e) {
					e.printStackTrace();
				}

		        return perms;
		    }
		};

		debug_sleep(2000);

		// unload natives loaded by a previous instance of this lwjgl applet
		unloadNatives(path);

		// add natives files path to native class path
		System.setProperty("org.lwjgl.librarypath", path + "natives");

		// Make sure jinput knows about the new path too
		System.setProperty("net.java.games.input.librarypath", path + "natives");

		// set the library path, useful for non lwjgl natives
		System.setProperty("java.library.path", path + "natives");

		// mark natives as loaded
		natives_loaded = true;
	}

	/**
	 * Unload natives loaded by a different classloader.
	 *
	 * Due to limitations of the jvm, native files can only
	 * be loaded once and only be used by the classloader
	 * they were loaded from.
	 *
	 * Due to the way applets on plugin1 work, one jvm must
	 * be used for all applets. We need to use multiple
	 * classloaders in the same jvm due to LWJGL's static
	 * nature. I order to solve this we simply remove the
	 * natives from a previous classloader allowing a new
	 * classloader to use those natives in the same jvm.
	 *
	 * This method will only attempt to unload natives from a
	 * previous classloader if it detects that the natives have
	 * been loaded in the same jvm.
	 *
	 * @param nativePath directory where natives are stored
	 */
	private void unloadNatives(String nativePath) {

		// check whether natives have been loaded into this jvm
		if (!natives_loaded) {
			return;
		}

		try {
			Field field = ClassLoader.class.getDeclaredField("loadedLibraryNames");
			field.setAccessible(true);
			Vector libs = (Vector) field.get(getClass().getClassLoader());

			String path = new File(nativePath).getCanonicalPath();

			for (int i = 0; i < libs.size(); i++) {
				String s = (String) libs.get(i);

				// if a native from the nativePath directory is loaded, unload it
				if (s.startsWith(path)) {
					libs.remove(i);
					i--;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * replace the current applet with the lwjgl applet
	 * using AppletStub and initialise and start it
	 */
	protected void switchApplet() throws Exception {

		state = STATE_SWITCHING_APPLET;
		percentage = 100;

		debug_sleep(2000);

		Class appletClass = classLoader.loadClass(getParameter("al_main"));
		lwjglApplet = (Applet) appletClass.newInstance();

		lwjglApplet.setStub(this);
		lwjglApplet.setSize(getWidth(), getHeight());

		setLayout(new BorderLayout());
		add(lwjglApplet);
		validate();

		state = STATE_INITIALIZE_REAL_APPLET;
		lwjglApplet.init();

		state = STATE_START_REAL_APPLET;
		lwjglApplet.start();
	}

	/**
	 * This method will get the files sizes of the files to download.
	 * It wil further get the lastModified time of files
	 * and save it in a hashmap, if cache is enabled it will mark
	 * those files that have not changed since last download to not
	 * redownloaded.
	 *
	 * @param dir - location to read cache file from
	 * @throws Exception - if fails to get infomation
	 */
	protected void getJarInfo(File dir) throws Exception {

		filesLastModified = new HashMap<String, Long>();

		// store file sizes and mark which files not to download
		fileSizes = new int[urlList.length];

		URLConnection urlconnection;

		File cacheFile = new File(dir, "cache");

		// if cache file exists, load it
		if (cacheFile.exists()) {
			filesLastModified = readCacheFile(cacheFile);
		}

		// calculate total size of jars to download
		for (int i = 0; i < urlList.length; i++) {
			urlconnection = urlList[i].openConnection();
			urlconnection.setDefaultUseCaches(false);
			if (urlconnection instanceof HttpURLConnection) {
				((HttpURLConnection) urlconnection).setRequestMethod("HEAD");
			}

			fileSizes[i] = urlconnection.getContentLength();

			long lastModified = urlconnection.getLastModified();
			String fileName = getFileName(urlList[i]);


			if (cacheEnabled && lastModified != 0 &&
					filesLastModified.containsKey(fileName)) {
				long savedLastModified = filesLastModified.get(fileName);

				// if lastModifed time is the same, don't redownload
				if (savedLastModified == lastModified) {
					fileSizes[i] = -2; // mark it to not redownload
				}
			}

			if (fileSizes[i] >= 0) {
				totalSizeDownload += fileSizes[i];
			}

			// put key and value in the hashmap
			filesLastModified.put(fileName, lastModified);

			// update progress bar
			percentage = 5 + (int)(10 * i/(float)urlList.length);
		}
	}

	/**
	 * Will download the jars from the server using the list of urls
	 * in urlList, while at the same time updating progress bar
	 *
	 * @param path location of the directory to save to
	 * @throws Exception if download fails
	 */
	protected void downloadJars(String path) throws Exception {

		state = STATE_DOWNLOADING;

		URLConnection urlconnection;

		int initialPercentage = percentage = 15;

		// download each jar
		byte buffer[] = new byte[65536];
		for (int i = 0; i < urlList.length; i++) {

			// skip file if marked as -2 (already downloaded and not changed)
			if (fileSizes[i] == -2) continue;

			int unsuccessfulAttempts = 0;
			int maxUnsuccessfulAttempts = 3;
			boolean downloadFile = true;

			// download the jar a max of 3 times
			while(downloadFile) {
				downloadFile = false;

				debug_sleep(2000);

				urlconnection = urlList[i].openConnection();

				if (urlconnection instanceof HttpURLConnection) {
					urlconnection.setRequestProperty("Cache-Control", "no-cache");
					urlconnection.connect();
		        }

				String currentFile = getFileName(urlList[i]);
				InputStream inputstream = getJarInputStream(currentFile, urlconnection);
				FileOutputStream fos = new FileOutputStream(path + currentFile);


				int bufferSize;
				long downloadStartTime = System.currentTimeMillis();
				int downloadedAmount = 0;
				int fileSize = 0;
				String downloadSpeedMessage = "";

				while ((bufferSize = inputstream.read(buffer, 0, buffer.length)) != -1) {
					debug_sleep(10);
					fos.write(buffer, 0, bufferSize);
					currentSizeDownload += bufferSize;
					fileSize += bufferSize;
					percentage = initialPercentage + ((currentSizeDownload * 45) / totalSizeDownload);
					subtaskMessage = "Retrieving: " + currentFile + " " + ((currentSizeDownload * 100) / totalSizeDownload) + "%";

					downloadedAmount += bufferSize;
					long timeLapse = System.currentTimeMillis() - downloadStartTime;
					// update only if a second or more has passed
					if (timeLapse >= 1000) {
						// get kb/s, nice that bytes/millis is same as kilobytes/seconds
						float downloadSpeed = (float) downloadedAmount / timeLapse;
						// round to two decimal places
						downloadSpeed = ((int)(downloadSpeed*100))/100f;
						// set current speed message
						downloadSpeedMessage = " - " + downloadSpeed + " KB/sec";
						// reset downloaded amount
						downloadedAmount = 0;
						// reset start time
						downloadStartTime = System.currentTimeMillis();
					}

					subtaskMessage += downloadSpeedMessage;
				}

				inputstream.close();
				fos.close();

				// download complete, verify if it was successful
				if (urlconnection instanceof HttpURLConnection) {
	                if (fileSize == fileSizes[i]) {
	                	// successful download
	                }
	                else if (fileSizes[i] <= 0) {
                        // If contentLength for fileSizes[i] <= 0, we don't know if the download
                        // is complete. We're going to guess the download is complete.
                    }
                    else {
                    	unsuccessfulAttempts++;
                		// download failed try again
                    	if (unsuccessfulAttempts < maxUnsuccessfulAttempts) {
                    		downloadFile = true;
                    		currentSizeDownload -= fileSize; // reset progress bar
                    	}
                    	else {
                    		// retry attempts exhasted, download failed
                    		throw new Exception("failed to download " + currentFile);
                    	}
                    }
	            }
			}
		}
		subtaskMessage = "";
	}

	/**
	 * Retrieves a jar files input stream. This method exists primarily to fix an Opera hang in getInputStream
	 * @param urlconnection connection to get input stream from
	 * @return InputStream or null if not possible
	 */
	protected InputStream getJarInputStream(final String currentFile, final URLConnection urlconnection) throws Exception {
		final InputStream[] is = new InputStream[1];

		// try to get the input stream 3 times.
		// Wait at most 5 seconds before interrupting the thread
		for (int j = 0; j < 3 && is[0] == null; j++) {
			Thread t = new Thread() {
				public void run() {
					try {
						is[0] = urlconnection.getInputStream();
					} catch (IOException e) {
						/* ignored */
					}
				}
			};
			t.setName("JarInputStreamThread");
			t.start();

			int iterationCount = 0;
			while(is[0] == null && iterationCount++ < 5) {
				try {
					t.join(1000);
				} catch (InterruptedException inte) {
					/* ignored */
				}
			}

			if(is[0] == null) {
				try {
					t.interrupt();
					t.join();
				} catch (InterruptedException inte) {
					/* ignored */
				}
			}
		}

		if(is[0] == null) {
			throw new Exception("Unable to get input stream for " + currentFile);
		}


		return is[0];
	}

	/**
	 *  Extract LZMA File
	 *  @param in Input path to pack file
	 *  @param out output path to resulting file
	 *  @throws Exception if any errors occur
	 */
	protected void extractLZMA(String in, String out) throws Exception {

		File f = new File(in);
		FileInputStream fileInputHandle = new FileInputStream(f);

		// use reflection to avoid hard dependency
		Class<?> clazz = Class.forName( "LZMA.LzmaInputStream" );
		Constructor constructor = clazz.getDeclaredConstructor(InputStream.class);
		InputStream inputHandle = (InputStream) constructor.newInstance(fileInputHandle);

		OutputStream outputHandle;
		outputHandle = new FileOutputStream(out);

		byte [] buffer = new byte [1<<14];

		int ret = inputHandle.read(buffer);
		while (ret >= 1) {
			outputHandle.write(buffer,0,ret);
			ret = inputHandle.read(buffer);
		}

		inputHandle.close();
		outputHandle.close();

		outputHandle = null;
		inputHandle = null;

		// delete LZMA file, as it is no longer needed
		f.delete();
	}

	/**
	 *  Extract Pack File
	 *  @param in Input path to pack file
	 *  @param out output path to resulting file
	 *  @throws Exception if any errors occur
	 */
	protected void extractPack(String in, String out) throws Exception {
		File f = new File(in);
	    FileOutputStream fostream = new FileOutputStream(out);
	    JarOutputStream jostream = new JarOutputStream(fostream);

	    Pack200.Unpacker unpacker = Pack200.newUnpacker();
	    unpacker.unpack(f, jostream);
	    jostream.close();

	    // delete pack file as its no longer needed
	    f.delete();
	}

	/**
	 *  Extract all jars from any lzma/pack files
	 *
	 *  @param path output path
	 *  @throws Exception if any errors occur
	 */
	protected void extractJars(String path) throws Exception {
		state = STATE_EXTRACTING_PACKAGES;

		float increment = (float) 10.0 / urlList.length;
		// extract all lzma and pack.lzma files
		for (int i = 0; i < urlList.length; i++) {

			// if file has not changed, skip it
			if (fileSizes[i] == -2) continue;

			percentage = 55 + (int) (increment * (i+1));
			String filename = getFileName(urlList[i]);

			if (filename.endsWith(".pack.lzma")) {
				subtaskMessage = "Extracting: " + filename + " to " + filename.replaceAll(".lzma", "");
				debug_sleep(1000);
				extractLZMA(path + filename, path + filename.replaceAll(".lzma", ""));

				subtaskMessage = "Extracting: " + filename.replaceAll(".lzma", "") + " to " + filename.replaceAll(".pack.lzma", "");
				debug_sleep(1000);
				extractPack(path + filename.replaceAll(".lzma", ""), path + filename.replaceAll(".pack.lzma", ""));
			}
			else if (filename.endsWith(".pack")) {
				subtaskMessage = "Extracting: " + filename + " to " + filename.replace(".pack", "");
				debug_sleep(1000);
				extractPack(path + filename, path + filename.replace(".pack", ""));
			}
			else if (filename.endsWith(".lzma")) {
				subtaskMessage = "Extracting: " + filename + " to " + filename.replace(".lzma", "");
				debug_sleep(1000);
				extractLZMA(path + filename, path + filename.replace(".lzma", ""));
			}
		}
	}

	/**
	 * This method will extract all file from the native jar and extract them
	 * to the subdirectory called "natives" in the local path, will also check
	 * to see if the native jar files is signed properly
	 *
	 * @param path base folder containing all downloaded jars
	 * @throws Exception if it fails to extract files
	 */
	protected void extractNatives(String path) throws Exception {

		// if no new native jar was downloaded, no extracting needed
		if (fileSizes[fileSizes.length-1] == -2) {
			return;
		}

		state = STATE_EXTRACTING_PACKAGES;

		int initialPercentage = percentage;

		// get name of jar file with natives from urlList, it will be the last url
		String nativeJar = getJarName(urlList[urlList.length - 1]);

		// get the current certificate to compare against native files
		Certificate[] certificate = AppletLoader.class.getProtectionDomain().getCodeSource().getCertificates();

		// workaround for bug where cached applet loader does not have certificates!?
		if (certificate == null) {
			URL location = AppletLoader.class.getProtectionDomain().getCodeSource().getLocation();

			// manually load the certificate
			JarURLConnection jurl = (JarURLConnection) (new URL("jar:" + location.toString() + "!/org/lwjgl/util/applet/AppletLoader.class").openConnection());
			jurl.setDefaultUseCaches(true);
			certificate = jurl.getCertificates();
		}

		// create native folder
		File nativeFolder = new File(path + "natives");
		if (!nativeFolder.exists()) {
			nativeFolder.mkdir();
		}

		// open jar file
		JarFile jarFile = new JarFile(path + nativeJar, true);

		// get list of files in jar
		Enumeration entities = jarFile.entries();

		totalSizeExtract = 0;

		// calculate the size of the files to extract for progress bar
		while (entities.hasMoreElements()) {
			JarEntry entry = (JarEntry) entities.nextElement();

			// skip directories and anything in directories
			// conveniently ignores the manifest
			if (entry.isDirectory() || entry.getName().indexOf('/') != -1) {
				continue;
			}
			totalSizeExtract += entry.getSize();
		}

		currentSizeExtract = 0;

		// reset point to begining by getting list of file again
		entities = jarFile.entries();

		// extract all files from the jar
		while (entities.hasMoreElements()) {
			JarEntry entry = (JarEntry) entities.nextElement();

			// skip directories and anything in directories
			// conveniently ignores the manifest
			if (entry.isDirectory() || entry.getName().indexOf('/') != -1) {
				continue;
			}

			// check if native file already exists if so delete it to make room for new one
			// useful when using the reload button on the browser
			File f = new File(path + "natives" + File.separator + entry.getName());
			if (f.exists()) {
				if (!f.delete()) {
					continue; // unable to delete file, it is in use, skip extracting it
				}
			}

			debug_sleep(1000);

			InputStream in = jarFile.getInputStream(jarFile.getEntry(entry.getName()));
			OutputStream out = new FileOutputStream(path + "natives" + File.separator + entry.getName());

			int bufferSize;
			byte buffer[] = new byte[65536];

			while ((bufferSize = in.read(buffer, 0, buffer.length)) != -1) {
				debug_sleep(10);
				out.write(buffer, 0, bufferSize);
				currentSizeExtract += bufferSize;

				// update progress bar
				percentage = initialPercentage + ((currentSizeExtract * 20) / totalSizeExtract);
				subtaskMessage = "Extracting: " + entry.getName() + " " + ((currentSizeExtract * 100) / totalSizeExtract) + "%";
			}

			// validate if the certificate for native file is correct
			validateCertificateChain(certificate, entry.getCertificates());

			in.close();
			out.close();
		}
		subtaskMessage = "";

		jarFile.close();

		// delete native jar as it is no longer needed
		File f = new File(path + nativeJar);
		f.delete();
	}

	/**
	 * Validates the certificate chain for a single file
	 *
	 * @param ownCerts Chain of certificates to check against
	 * @param native_certs Chain of certificates to check
	 */
	protected static void validateCertificateChain(Certificate[] ownCerts, Certificate[] native_certs) throws Exception {
		if (native_certs == null)
			throw new Exception("Unable to validate certificate chain. Native entry did not have a certificate chain at all");

		if (ownCerts.length != native_certs.length)
			throw new Exception("Unable to validate certificate chain. Chain differs in length [" + ownCerts.length + " vs " + native_certs.length + "]");

		for (int i = 0; i < ownCerts.length; i++) {
			if (!ownCerts[i].equals(native_certs[i])) {
				throw new Exception("Certificate mismatch: " + ownCerts[i] + " != " + native_certs[i]);
			}
		}
	}

	/**
	 * Get Image from path provided
	 *
	 * @param s location of the image
	 * @return the Image file
	 */
	protected Image getImage(String s) {
		try {
			URL url = Thread.currentThread().getContextClassLoader().getResource("/"+s);

			// if image not found in jar, look outside it
			if (url == null) {
				url = new URL(getCodeBase(), s);
			}

			Image image = super.getImage(url);

			// wait for image to load
			MediaTracker tracker = new MediaTracker(this);
	        tracker.addImage(image, 0);
	        tracker.waitForAll();

	        // if no errors return image
	        if (!tracker.isErrorAny()) {
	        	return image;
	        }
		} catch (Exception e) {
			/* */
		}

		// show error as image could not be loaded
		fatalErrorOccured("Unable to load logo and progressbar images", null);
		return null;
	}


	/**
	 * Get jar name from URL.
	 *
	 * @param url Get jar file name from this url
	 * @return file name as string
	 */
	protected String getJarName(URL url) {
		String fileName = url.getFile();

		if (fileName.endsWith(".pack.lzma")) {
			fileName = fileName.replaceAll(".pack.lzma", "");
		} else if (fileName.endsWith(".pack")) {
			fileName = fileName.replaceAll(".pack", "");
		} else if (fileName.endsWith(".lzma")) {
			fileName = fileName.replaceAll(".lzma", "");
		}

		return fileName.substring(fileName.lastIndexOf('/') + 1);
	}

	/**
	 * Get file name portion of URL.
	 *
	 * @param url Get file name from this url
	 * @return file name as string
	 */
	protected String getFileName(URL url) {
		String fileName = url.getFile();
		return fileName.substring(fileName.lastIndexOf('/') + 1);
	}

	/**
	 * Retrieves the color
	 *
	 * @param param Color to load
	 * @param defaultColor Default color to use if no color to load
	 * @return Color to use
	 */
	protected Color getColor(String param, Color defaultColor) {
		String color = getParameter(param);

		if (color == null) return defaultColor;

		// Check if RGB format
        if (color.indexOf(",") != -1) {
            StringTokenizer st = new StringTokenizer(color, ",");

            // We've got three components for the color
            try {
            	return new Color(Integer.parseInt(st.nextToken().trim()),
    							 Integer.parseInt(st.nextToken().trim()),
    							 Integer.parseInt(st.nextToken().trim()));
            } catch (Exception e) {
                // failed to parse
            	return defaultColor;
            }
        }

        // Check & decode if the color is in hexadecimal color format (i.e. #808000)
        try {
        	return Color.decode(color);
        } catch (NumberFormatException e) {
        	// ignore exception
        }

        // Get the color by name if it exists
        try {
        	return (Color)Color.class.getField(color).get(null);
        } catch (Exception e) {
        	return defaultColor;
        }
	}

	/**
	 * Retrieves the boolean value for the applet
	 * @param name Name of parameter
	 * @param defaultValue default value to return if no such parameter
	 * @return value of parameter or defaultValue
	 */
	protected boolean getBooleanParameter(String name, boolean defaultValue) {
		String parameter = getParameter(name);
		if(parameter != null) {
			return Boolean.parseBoolean(parameter);
		}
		return defaultValue;
	}

	/**
	 * Sets the state of the loaded and prints some debug information
	 *
	 * @param error Error message to print
	 */
	protected void fatalErrorOccured(String error, Exception e) {
		fatalError = true;
		genericErrorMessage[genericErrorMessage.length-1] = error;
		System.out.println(error);
		if(e != null) {
			System.out.println(e.getMessage());
			System.out.println(generateStacktrace(e));
		}
		repaint();
	}

	/**
	 * Utility method for sleeping
	 * Will only really sleep if debug has been enabled
	 * @param ms milliseconds to sleep
	 */
	protected void debug_sleep(long ms) {
		if(debugMode) {
			sleep(ms);
		}
	}

	/**
	 * Utility method for sleeping
	 * @param ms milliseconds to sleep
	 */
	protected void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
			/* ignored */
		}
	}

}