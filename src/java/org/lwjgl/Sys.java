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
package org.lwjgl;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 * $Id$
 *
 * System class (named Sys so as not to conflict with java.lang.System)
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public final class Sys {
	public static final String VERSION = "0.93";

	/** Low process priority. @see #setProcessPriority() */
	public static final int LOW_PRIORITY = -1;

	/**
	 * Normal process priority. This priority equates to the priority that the
	 * JVM has when it is started up normally. Note that if the JVM is started
	 * inside a process which is already a different priority then this will not
	 * be the initial priority.
	 * 
	 * @see #setProcessPriority(int)
	 */
	public static final int NORMAL_PRIORITY = 0;

	/** High process priority. @see #setProcessPriority() */
	public static final int HIGH_PRIORITY = 1;

	/**
	 * Realtime priority. Use at your own risk. This will set the java process
	 * priority to the highest priority the OS will normally allow. It is likely
	 * that this puts it at a higher priority than many OS critical tasks, such
	 * as disk writes or mouse input and the like. Hence it is quite possible to
	 * completely freeze your machine if you have an errant thread.
	 * 
	 * This priority is <strong>not</strong> recommended for gaming applications.
	 * 
	 * @see #setProcessPriority(int)
	 */
	public static final int REALTIME_PRIORITY = 2;

	/** The native library name */
	private static String LIBRARY_NAME = "lwjgl";

	/** The platform adapter class name */
	private static String PLATFORM;
	
	/**
	 * Debug flag.
	 */
	public static final boolean DEBUG = Boolean.getBoolean("org.lwjgl.Sys.debug");

	private static boolean initialized = false;

	static {
		initialize();
	}

	/**
	 * @return the name of the native library to load
	 */
	public static String getLibraryName() {
		return LIBRARY_NAME;
	}

	/**
	 * No constructor for Sys.
	 */
	private Sys() {
	}

	/**
	 * Prints the given message to System.err if isDebugEnabled()
	 * is true.
	 */
	public static void log(String msg) {
		if (Sys.DEBUG) {
			System.err.println(msg);
		}
	}

	/**
	 * Initialization.
	 */
	public static void initialize() {
		if (initialized)
			return;
		initialized = true;
		System.loadLibrary(LIBRARY_NAME);
		String native_version = getNativeLibraryVersion();
		if (!native_version.equals(VERSION))
			throw new LinkageError("Version mismatch: jar version is '" + VERSION + 
                                                        "', native libary version is '" + native_version + "'");
		setDebug(DEBUG);

		PLATFORM = System.getProperty("org.lwjgl.Sys.platform", "org.lwjgl.SwingAdapter");

	}

	/**
	 * Return the version of the native library
	 */
	private static native String getNativeLibraryVersion();

	/**
	 * Set the debug level of the native library
	 */
	private static native void setDebug(boolean debug);

	/**
	 * Obtains the number of ticks that the hires timer does in a second.
	 *
	 * @return timer resolution in ticks per second or 0 if no timer is present.
	 */
	public static native long getTimerResolution();
	
	/**
	 * Gets the current value of the hires timer, in ticks. When the Sys class is first loaded
	 * the hires timer is reset to 0. If no hires timer is present then this method will always
	 * return 0.<p><strong>NOTEZ BIEN</strong> that the hires timer WILL wrap around. 
	 *
	 * @return the current hires time, in ticks (always >= 0)
	 */
	public static long getTime() {
		return ngetTime() & 0x7FFFFFFFFFFFFFFFL;
	}
	private static native long ngetTime();
	
	/**
	 * Set the process priority in a system independent way. Because of the various
	 * differences in operating systems this might or might not have any effect or
	 * the correct effect.
	 * 
	 * The default process priority is NORMAL_PRIORITY.
	 * 
	 * REALTIME_PRIORITY processes should theoretically be the maximum priority of
	 * any process on the system and may have side effects on I/O and other fundamental
	 * operating system functions - use with caution.
	 * 
	 * It is unlikely that any games will want to change the priority of the Java
	 * process; but there are some other applications for this library which require
	 * process priority adjustments, such as in soft-realtime graphics rendering
	 * for broadcast television.
	 * 
	 * @param priority a priority class, which will be one of REALTIME_PRIORITY,
	 * HIGH_PRIORITY, NORMAL_PRIORITY, or LOW_PRIORITY.
	 */
	public static native void setProcessPriority(int priority);
	
	/**
	 * Attempt to display a modal alert to the user. This method should be used
	 * when a game fails to initialize properly or crashes out losing its display
	 * in the process. It is provided because AWT may not be available on the target
	 * platform, although on Mac and Linux and other platforms supporting AWT we
	 * delegate the task to AWT instead of doing it ourselves.
	 * <p>
	 * The alert should display the title and the message and then the current
	 * thread should block until the user dismisses the alert - typically with an
	 * OK button click.
	 * <p>
	 * It may be that the user's system has no windowing system installed for some
	 * reason, in which case this method may do nothing at all, or attempt to provide
	 * some console output.
	 *
	 * @param title The title of the alert. We suggest using the title of your game.
	 * @param message The message text for the alert.
	 */
	public static void alert(String title, String message) {
		boolean grabbed = Mouse.isGrabbed();
		if (grabbed) {
			Mouse.setGrabbed(false);
		}
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Windows")) {
			nAlert(title, message);
		} else {
			try {
				PlatformAdapter adapter = (PlatformAdapter) Class.forName(PLATFORM).newInstance(); // This avoids a Jet error message
				adapter.alert(title, message);
			} catch (Exception e) {
				Sys.log("Unable to display alert using: " + PLATFORM);
			}
		}
		if (grabbed) {
			Mouse.setGrabbed(true);
		}
	}

	private static native void nAlert(String title, String message);
	
	/**
	 * Open the system web browser and point it at the specified URL. It is recommended
	 * that this not be called whilst your game is running, but on application exit in
	 * a shutdown hook, as the screen resolution will not be reset when the browser is
	 * brought into view.
	 * <p>
	 * There is no guarantee that this will work, nor that we can detect if it has
	 * failed - hence we don't return success code or throw an Exception. This is just a
	 * best attempt at opening the URL given - don't rely on it to work!
	 * <p>
	 * @param url The URL
	 * @return false if we are CERTAIN the call has failed
	 */
	public static boolean openURL(String url) {
		// Attempt to use Webstart if we have it available
		try {
			// Lookup the javax.jnlp.BasicService object
			Class serviceManagerClass = Class.forName("javax.jnlp.ServiceManager");
			Method lookupMethod = serviceManagerClass.getMethod("lookup", new Class[] {String.class});
			Object basicService = lookupMethod.invoke(serviceManagerClass, new Object[] {"javax.jnlp.BasicService"});
			Class basicServiceClass = Class.forName("javax.jnlp.BasicService");
			Method showDocumentMethod = basicServiceClass.getMethod("showDocument", new Class[] {URL.class});
			try {
				Boolean ret = (Boolean) showDocumentMethod.invoke(basicService, new Object[] {new URL(URLEncoder.encode(url, "utf8"))});
				return ret.booleanValue();
			} catch (MalformedURLException e) {
				e.printStackTrace(System.err);
				return false;
			}
		} catch (Exception ue) {
			return Display.getImplementation().openURL(url);
		}
	}

	/**
	 * Get the contents of the system clipboard. The system might not have a
	 * clipboard (particularly if it doesn't even have a keyboard) in which case
	 * we return null. Otherwise we return a String, which may be the empty
	 * string "".
	 * 
	 * @return a String, or null if there is no system clipboard.
	 */
	public static String getClipboard() {
		try {
			PlatformAdapter adapter = (PlatformAdapter) Class.forName(PLATFORM).newInstance(); // This avoids a Jet error message
			return adapter.getClipboard();
		} catch (Exception e) {
			Sys.log("Unable to get clipboard contents: " + e);
			// ignore exception and use native implementation
			return nGetClipboard();
		}
	}
	
	private static native String nGetClipboard();
} 
