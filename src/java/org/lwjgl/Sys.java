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

package org.lwjgl;

import java.io.IOException;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * $Id$
 *
 * System class (named Sys so as not to conflict with java.lang.System)
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public final class Sys {
	/** Debug level constants */
	public static final int DEBUG = 6;
	public static final int INFO = 5;
	public static final int WARN = 4;
	public static final int ERROR = 3;
	public static final int FATAL = 2;
	public static final int NONE = 1;

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
	
	/**
	 * Debug level. 
	 */
	public static final int debug_level;

	static {
		String debug_level_prop = System.getProperty("lwjgl.debuglevel", "NONE");
		int _debug = NONE;
		if (debug_level_prop.equals("DEBUG")) {
			_debug = DEBUG;
		} else if (debug_level_prop.equals("INFO")) {
			_debug = INFO;
		} else if (debug_level_prop.equals("WARN")) {
			_debug = WARN;
		} else if (debug_level_prop.equals("ERROR")) {
			_debug = ERROR;
		} else if (debug_level_prop.equals("FATAL")) {
			_debug = FATAL;
		} else if (debug_level_prop.equals("NONE")) {
			_debug = NONE;
		}
		debug_level = _debug;
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
	 * Prints the given message to System.err if atDebugLevel(debug_level)
	 * is true.
	 */
	public static void log(int debug_level, String msg) {
		if (atDebugLevel(debug_level))
			System.err.println(msg);
	}

	/**
	 * @return true if the debug level is greater than or equal to level
	 */
	public static boolean atDebugLevel(int level) {
		return debug_level >= level;
	}

	/**
	 * Initialization.
	 */
	private static void initialize() {
		System.loadLibrary(LIBRARY_NAME);
		setDebugLevel(debug_level);
		setTime(0);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				Display.resetDisplayMode();
				if (Keyboard.isCreated())
					Keyboard.destroy();
				if (Mouse.isCreated())
					Mouse.destroy();
				if (Controller.isCreated())
					Controller.destroy();
			}
		});
		
	}

	/**
         * Set the debug level of the native library
	 */
	private static native void setDebugLevel(int level);

	/**
	 * Obtains the number of ticks that the hires timer does in a second.
	 *
	 * @return timer resolution in ticks per second or 0 if no timer is present.
	 */
	public static native long getTimerResolution();
	
	/**
	 * Gets the current value of the hires timer, in ticks. When the Sys class is first loaded
	 * the hires timer is reset to 0. If no hires timer is present then this method will always
	 * return whatever value the timer was last set to.
	 *
	 * @return the current hires time, in ticks.
	 */
	public static native long getTime();
	
	/**
	 * Sets the hires timer to a new time, specified in ticks.
	 * 
	 * @param time The new time, in ticks
	 * @see #getTime()
	 * @see #getTimerResolution()
	 */
	public static native void setTime(long time);
	
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
	 * platform.
	 * 
	 * The alert should display the title and the message and then the current
	 * thread should block until the user dismisses the alert - typically with an
	 * OK button click.
	 * 
	 * It may be that the user's system has no windowing system installed for some
	 * reason, in which case this method may do nothing at all, or attempt to provide
	 * some console output.
	 *
	 * @param title The title of the alert. We suggest using the title of your game.
	 * @param message The message text for the alert.
	 */
	public static native void alert(String title, String message);
	
	/*
	 * Cas: this is just a debugging aid. The native code is also commented out.
	 *
	public static native int getDirectBufferAddress(Buffer buf);
	 */
	
	/**
	 * Open the system web browser and point it at the specified URL. It is recommended
	 * that this not be called whilst your game is running, but on application exit in
	 * a shutdown hook, as the screen resolution will not be reset when the browser is
	 * brought into view.
	 * 
	 * There is no guarantee that this will work, nor that we can detect if it has
	 * failed - hence we don't return success code or throw an Exception. This is just a
	 * best attempt at opening the URL given - don't rely on it to work!
	 * @param url The URL
	 */
	public static void openURL(String url) {
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Mac OS") || osName.startsWith("Windows")) {
			// Mac and Windows both do this nicely from native code.
			nOpenURL(url);
			return;
		}
		// Linux may as well resort to pure Java hackery, as there's no Linux native way of doing it
		// right anyway.

		String[] browsers = {"mozilla", "opera", "konqueror", "nautilus", "galeon", "netscape"};

		for (int i = 0; i < browsers.length; i ++) {				
			try {
				Runtime.getRuntime().exec(new String[] { browsers[i], url });
				break;
			} catch (IOException e) {
				// Ignore
				e.printStackTrace(System.err);
			}
		}
	}
	
	
	/*
	 * Where necessary, we use a native implementation of openURL.
	 */
	private static native void nOpenURL(String url);
} 
