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
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * <p>
 * The AppletLoader enables deployment of LWJGL to applets in an easy
 * and polished way. The loader will display a configurable logo and progressbar
 * while the relevant jars (generic and native) are downloaded from a specified source.
 * </p>
 * <p>
 * The downloaded are extracted to the users temporary directory - and if enabled, cached for 
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
 * </ul>
 * </p>
 * <p>
 * Additionally the following parameters can be supplied to tweak the behaviour of the AppletLoader.
 * <ul>
 * <li>al_version - [int or float] Version of deployment. If this is specified, the jars will be cached and 
 * reused if the version matches. If version doesn't match all of the files are reloaded.</li>
 * <li>al_bgcolor - [String] Hex formated color to use as background. <i>Default: ffffff</i>.</li>
 * <li>al_fgcolor - [String] Hex formated color to use as foreground. <i>Default: 000000</i>.</li>
 * <li>al_errorcolor - [String] Hex formated color to use as foreground color on error. <i>Default: ff0000</i>.</li>
 * <li>al_debug - [boolean] Whether to enable debug mode. <i>Default: false</i>.</li>
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
	protected Image		logo;

	/** progressbar to render while loading */
	protected Image		progressbar;
	
	/** offscreen image used */
	protected Image 	offscreen;
	
	/** background color of applet */
	protected Color		bgColor 	= Color.white;
	
	/** Color to write errors in */
	protected Color		errorColor 	= Color.red;

	/** color to write forground in */
	protected Color		fgColor 	= Color.black;	

	/** urls of the jars to download */
	protected URL[]		urlList;
	
	/** actual thread that does the loading */
	protected Thread	loaderThread;
	
	/** animation thread that renders our loaderscreen while loading */
	protected Thread 	animationThread;

	/** applet to load after all downloads are complete */
	protected Applet	lwjglApplet;
	
	/** whether a fatal error occured */
	protected boolean	fatalError;
	
	/** fatal error that occured */
	protected String	fatalErrorDescription;
	
	/** whether we're running in debug mode */
	protected boolean 	debugMode;
	
	/** String to display as a subtask */
	protected String	subtaskMessage = "";

	/** state of applet loader */
	protected int		state = STATE_INIT;
	
	/** generic error message to display on error */
	protected String[] 	genericErrorMessage = {	"An error occured while loading the applet.",
												"Plese contact support to resolve this issue.",
												"<placeholder for error message>"};
	
	/** whether a certificate refused error occured */
	protected boolean	certificateRefused;
	
	/** error message to display if user refuses to accept certicate*/
	protected String[] 	certificateRefusedMessage = { "Permissions for Applet Refused.",
												      "Please accept the permissions dialog to allow",
												      "the applet to continue the loading process."};
	
	/*
	 * @see java.applet.Applet#init()
	 */
	public void init() {
		
		state = STATE_INIT;
		
		// sanity check
		String[] requiredArgs = {"al_main", "al_logo", "al_progressbar", "al_jars"};
		for(int i=0; i<requiredArgs.length; i++) {
			if(getParameter(requiredArgs[i]) == null) {
				fatalErrorOccured("missing required applet parameter: " + requiredArgs[i]);
				return;				
			}
		}

		// whether to run in debug mode
		debugMode 		= getBooleanParameter("al_debug", false);
		
		// get colors of applet
		bgColor 		= getColor("al_bgcolor", Color.white);
		setBackground(bgColor);

		fgColor 		= getColor("al_fgcolor", Color.black);
		errorColor 		= getColor("al_errorcolor", Color.red);		

		// load logos
		logo 			= getImage("/" + getParameter("al_logo"));
		progressbar 	= getImage("/" + getParameter("al_progressbar"));
		
		//sanity check
		if(logo == null || progressbar == null) {
			fatalErrorOccured("Unable to load logo and progressbar images");
		}
	}

	/*
	 * @see java.applet.Applet#start()
	 */
	public void start() {
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
	
	/*
	 * @see java.applet.Applet#stop()
	 */
	public void stop() {
		if (lwjglApplet != null) {
			lwjglApplet.stop();
		}
		super.stop();
	}
	
	/*
	 * @see java.applet.Applet#destroy()
	 */
	public void destroy() {
		if (lwjglApplet != null) {
			lwjglApplet.destroy();
		}
		
		progressbar = null;
		logo 		= null;
		
		super.destroy();
	}
	
	/**
	 * Retrieves the applet that has been loaded. Usefull for liveconnect.
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
	public synchronized void paint(Graphics g) {
		
		// don't paint loader if applet loaded
		if(state == STATE_DONE) {
			return;
		}

		// create offscreen if missing
		if (offscreen == null) {
			offscreen = createImage(getWidth(), getHeight());
		}

		// draw everything onto an image before drawing to avoid flicker
		Graphics og = offscreen.getGraphics();
		FontMetrics fm = og.getFontMetrics();

		// set background color
		og.setColor(bgColor);
		og.fillRect(0, 0, getWidth(), getHeight());

		// get logo position so its in the middle of applet
		int x = 0, y = 0;
		
		if(logo != null && !fatalError) {
			x = (getWidth() - logo.getWidth(this)) / 2;
			y = (getHeight() - logo.getHeight(this)) / 2;
		}

		og.setColor(fgColor);
		String message = getDescriptionForState();

		// if we had a failure of some sort, notify the user
		if (fatalError) {
			String[] errorMessage = (certificateRefused) ? certificateRefusedMessage : genericErrorMessage;
			
			if (!certificateRefused) {
				errorMessage[errorMessage.length-1] = fatalErrorDescription;
			}			
			
			for(int i=0; i<errorMessage.length; i++) {
				int messageX = (getWidth() - fm.stringWidth(errorMessage[i])) / 2;
				int messageY = (getHeight() - (fm.getHeight() * errorMessage.length)) / 2;
				
				og.setColor(errorColor);
				og.drawString(errorMessage[i], messageX, messageY + i*fm.getHeight());
			}
		} else {
			og.setColor(fgColor);

			// draw logo
			og.drawImage(logo, x, y, null);
			
			// draw message
			int messageX = (getWidth() - fm.stringWidth(message)) / 2;
			int messageY = y + logo.getHeight(null) + 20;
			og.drawString(message, messageX, messageY);
			
			// draw subtaskmessage, if any
			if(subtaskMessage.length() > 0) {
				messageX = (getWidth() - fm.stringWidth(subtaskMessage)) / 2;
				og.drawString(subtaskMessage, messageX, messageY+20);
			}

			// draw loading bar, clipping it depending on percentage done
			int barSize = (progressbar.getWidth(this) * percentage) / 100;
			og.clipRect(0, 0, x + barSize, getHeight());
			og.drawImage(progressbar, x, y, null);
		}
		
		og.dispose();

		// finally draw it all
		g.drawImage(offscreen, 0, 0, null);
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
				return "Checking cache for existing files";
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
	 * Reads list of jars to download and adds the urls to urlList
	 * also finds out which OS you are on and adds appropriate native
	 * jar to the urlList
	 */
	protected void loadJarURLs() throws Exception {
		state = STATE_DETERMINING_PACKAGES;
		
		// jars to load
		String jarList = getParameter("al_jars");
		
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
			nativeJar = getParameter("al_windows");
		} else if (osName.startsWith("Linux") || osName.startsWith("FreeBSD") || osName.startsWith("SunOS")) {
			nativeJar = getParameter("al_linux");
		} else if (osName.startsWith("Mac")) {
			nativeJar = getParameter("al_mac");
		} else {
			fatalErrorOccured("OS (" + osName + ") not supported");
		}

		if (nativeJar == null) {
			fatalErrorOccured("no lwjgl natives files found");
		} else {
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
	 * 
	 */
	public void run() {
		
		state = STATE_CHECKING_CACHE;
		
 		percentage = 5;

		try {
			if(debugMode) {
				sleep(2000);
			}

			// parse the urls for the jars into the url list
			loadJarURLs();
			
			// get path where applet will be stored
			String path = (String) AccessController.doPrivileged(new PrivilegedExceptionAction() {
				public Object run() throws Exception {
					return System.getProperty("java.io.tmpdir") + File.separator + getParameter("al_title") + File.separator;
				}
			});
			
			File dir = new File(path);

			// create directory
			if (!dir.exists()) {
				dir.mkdir();
			}
			dir = new File(dir, "version");

			// if applet already available don't download anything
			boolean cacheAvailable = false;

			// version of applet
			String version = getParameter("al_version");
			float latestVersion = 0;
			
			// if applet version specifed, check if you have latest version of applet
			if (version != null) {

				latestVersion = Float.parseFloat(version);

				// if version file exists
				if (dir.exists()) {
					// compare to new version
					if (latestVersion <= readVersionFile(dir)) {
						cacheAvailable = true;
						percentage = 90;
						if(debugMode) {
							sleep(2000);
						}
					}
				}
			}

			// if jars not available or need updating download them
			if (!cacheAvailable) {
				// downloads jars from the server
				downloadJars(path);		// 10-65%

				// Extracts Native Files
				extractNatives(path);	// 65-85%

				// add version information once jars downloaded successfully
				if (version != null) {
					percentage = 90;
					writeVersionFile(dir, latestVersion);
				}
			}

			// add the downloaded jars and natives to classpath
			updateClassPath(path);

			// switch to LWJGL Applet
			switchApplet();

			state = STATE_DONE;		
		} catch (AccessControlException ace) {
			fatalErrorOccured(ace.getMessage());
			certificateRefused = true;
		} catch (Exception e) {
			fatalErrorOccured(e.getMessage());
		} finally {
			loaderThread = null;
			repaint();
		}
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

		Class[] parameters = new Class[] {URL.class};
		
		// modify class path by adding downloaded jars to it
		for (int i = 0; i < urlList.length; i++) {
			// get location of jar as a url
			URL u = new URL("file:" + path + getFileName(urlList[i]));
		
			// add to class path
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", parameters);
			method.setAccessible(true);
			method.invoke(getClass().getClassLoader(), new Object[] {u});
		}
		
		if(debugMode) {
			sleep(2000);
		}

		// add natives files path to native class path
		System.setProperty("org.lwjgl.librarypath", path + "natives");

		// Make sure jinput knows about the new path too
		System.setProperty("net.java.games.input.librarypath", path + "natives");
	}

	/**
	 * replace the current applet with the lwjgl applet
	 * using AppletStub and initialise and start it
	 */
	protected synchronized void switchApplet() throws Exception {
		
		state = STATE_SWITCHING_APPLET;
		percentage = 100;
		
		if(debugMode) {
			sleep(2000);
		}

		Class appletClass = Class.forName(getParameter("al_main"));
		lwjglApplet = (Applet) appletClass.newInstance();

		lwjglApplet.setStub(this);
		lwjglApplet.setSize(getWidth(), getHeight());

		setLayout(new BorderLayout());
		add(lwjglApplet);

		state = STATE_INITIALIZE_REAL_APPLET;
		lwjglApplet.init();

		state = STATE_START_REAL_APPLET;
		lwjglApplet.start();

		
		// fix for issue with applet not showing up in firefox
		setVisible(false);
		validate();
		setVisible(true);
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

		// calculate total size of jars to download
		for (int i = 0; i < urlList.length; i++) {
			urlconnection = urlList[i].openConnection();
			totalSizeDownload += urlconnection.getContentLength();
		}
		
		int initialPercentage = percentage = 10;

		// download each jar
		byte buffer[] = new byte[65536];
		for (int i = 0; i < urlList.length; i++) {
			if(debugMode) {
				sleep(2000);
			}
			
			urlconnection = urlList[i].openConnection();

			String currentFile = getFileName(urlList[i]);
			InputStream inputstream = getJarInputStream(currentFile, urlconnection);
			FileOutputStream fos = new FileOutputStream(path + currentFile);
			

			int bufferSize;
			while ((bufferSize = inputstream.read(buffer, 0, buffer.length)) != -1) {
				if(debugMode) {
					sleep(10);
				}
				fos.write(buffer, 0, bufferSize);
				currentSizeDownload += bufferSize;
				percentage = initialPercentage + ((currentSizeDownload * 55) / totalSizeDownload);
				subtaskMessage = "Retrieving: " + currentFile + " " + ((currentSizeDownload * 100) / totalSizeDownload) + "%";
			}
			
			inputstream.close();
			fos.close();
		}
		subtaskMessage = "";
	}

	/**
	 * Retrieves a jar files input stream. This method exists primarily to fix an Opera hang in getInputStream
	 * @param urlconnection connection to get input stream from
	 * @return InputStream or null if not possible
	 */
	private InputStream getJarInputStream(final String currentFile, final URLConnection urlconnection) throws Exception {
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
	 * This method will extract all file from the native jar and extract them
	 * to the subdirectory called "natives" in the local path, will also check
	 * to see if the native jar files is signed properly 
	 * 
	 * @param path base folder containing all downloaded jars
	 * @throws Exception if it fails to extract files
	 */
	protected void extractNatives(String path) throws Exception {
		
		state = STATE_EXTRACTING_PACKAGES;
		
		int initialPercentage = percentage;

		// get name of jar file with natives from urlList, it will be the last url
		String nativeJar = getFileName(urlList[urlList.length - 1]);

		// get the current certificate to compare against native files 
		Certificate[] certificate = AppletLoader.class.getProtectionDomain().getCodeSource().getCertificates();

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
			
			if(debugMode) {
				sleep(1000);
			}

			InputStream in = jarFile.getInputStream(jarFile.getEntry(entry.getName()));
			OutputStream out = new FileOutputStream(path + "natives" + File.separator + entry.getName());

			int bufferSize;
			byte buffer[] = new byte[65536];

			while ((bufferSize = in.read(buffer, 0, buffer.length)) != -1) {
				if(debugMode) {
					sleep(10);
				}
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
			DataInputStream datainputstream = new DataInputStream(getClass().getResourceAsStream(s));
			byte abyte0[] = new byte[datainputstream.available()];
			datainputstream.readFully(abyte0);
			datainputstream.close();
			return Toolkit.getDefaultToolkit().createImage(abyte0);
		} catch (Exception e) {
			/* */
		}
		return null;
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
	 * @param color Color to load
	 * @param defaultColor Default color to use if no color to load
	 * @return Color to use
	 */
	protected Color getColor(String color, Color defaultColor) {
		String param_color = getParameter(color);
		if (param_color != null) {
			return new Color(Integer.parseInt(param_color, 16));
		}
		return defaultColor;
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
	protected void fatalErrorOccured(String error) {
		fatalError = true;
		fatalErrorDescription = "Fatal error occured (" + state + "): " + error;
		System.out.println(fatalErrorDescription);
		repaint();
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
