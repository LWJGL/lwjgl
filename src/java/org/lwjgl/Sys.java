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

import org.lwjgl.input.Mouse;

/**
 * $Id$
 * <p>
 * System class (named Sys so as not to conflict with java.lang.System)
 * </p>
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public final class Sys {

  /** Current version of library */
	public static final String VERSION = "0.95";

	/** The native library name */
	private static final String LIBRARY_NAME = "lwjgl";

	/** Debug flag. */
	public static final boolean DEBUG = Boolean.getBoolean("org.lwjgl.Sys.debug");
	
  	/** OS Name */
	private final static String OS_NAME = System.getProperty("os.name");

	/** The implementation instance to delegate platform specific behavior to */
	private final static SysImplementation implementation;
	
	/*
	 * Timer calibration. The hires timers on some systems - notably SpeedStep chips,
	 * HyperThreaded chips, or dual-processor/cores, are notoriously inaccurate. We
	 * therefore need to calibrate the hires timer with a lowres timer that is known
	 * to keep much more accurate time. System.currentTimeMillis() keeps accurate time
	 * down to a worst-case resolution of 50ms, which is good enough for us as it means
	 * at worst we will have 3-4 frames at the wrong rate if a hires timer resolution
	 * change occurs.
	 */

	/** Master control: override all our clever recalibration */
	private static final boolean OVERRIDE_CALIBRATION = Boolean.getBoolean("org.lwjgl.Sys.overrideCalibration");
	
	/** Turn on debugging for calibration */
	private static final boolean DEBUG_CALIBRATION = Boolean.getBoolean("org.lwjgl.Sys.debugCalibration");
	
	/**
	 * Timer calibration error, expressed as % variance either side of the baseline in the
	 * system property org.lwjhgl.Sys.timerError. 35.0% is the default.
	 */
	private static final double UPPER_TIMER_ERROR = 1.0 + Double.parseDouble(System.getProperty("org.lwjgl.Sys.timerError", "35.0")) / 100.0;
	private static final double LOWER_TIMER_ERROR = 1.0 - Double.parseDouble(System.getProperty("org.lwjgl.Sys.timerError", "35.0")) / 100.0;
	
	/** Short term recalibration threshold */
	private static final long SHORT_RECALIBRATION_THRESHOLD = Long.parseLong(System.getProperty("org.lwjgl.Sys.shortRecalibrationThreshold", "50"));

	/** Long term recalibration threshold */
	private static final long LONG_RECALIBRATION_THRESHOLD = Long.parseLong(System.getProperty("org.lwjgl.Sys.longRecalibrationThreshold", "1000"));
	
	/** Last recalibration timer value, in milliseconds */
	private static long recalibrationThen;
	
	/** Current recalibration timer value, in milliseconds */
	private static long recalibrationNow;
	
	/** Hires time when we started counting recalibration time */
	private static long hiresRecalibrationThen;

	/** Last lowres timer time, in milliseconds */
	private static long lowresThen;
	
	/** Current lowres timer time, in milliseconds */
	private static long lowresNow;
	
	/** Last hires timer time, in ticks */
	private static long hiresThen;
	
	/** Current hires timer time, in ticks */
	private static long hiresNow;
	
	/** Baseline hires resolution */
	private static long defaultResolution;
	
	/** Current hires resolution */
	private static long currentResolution;
  
	static {
		implementation = createImplementation();
		System.loadLibrary(LIBRARY_NAME);
		String native_version = implementation.getNativeLibraryVersion();
		if (!native_version.equals(VERSION))
			throw new LinkageError("Version mismatch: jar version is '" + VERSION +
                             "', native libary version is '" + native_version + "'");
		implementation.setDebug(DEBUG);
	}

	private static SysImplementation createImplementation() {
		String class_name;
		if (OS_NAME.startsWith("Linux")) {
			class_name = "org.lwjgl.LinuxSysImplementation";
		} else if (OS_NAME.startsWith("Windows")) {
			class_name = "org.lwjgl.Win32SysImplementation";
		} else if (OS_NAME.startsWith("Mac")) {
			class_name = "org.lwjgl.MacOSXSysImplementation";
		} else
			throw new IllegalStateException("The platform " + OS_NAME + " is not supported");
		try {
			Class impl_class = Class.forName(class_name);
			return (SysImplementation)impl_class.newInstance();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
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
	 * Initialization. This is just a dummy method to trigger the static constructor.
	 */
	public static void initialize() {
	}

	/**
	 * Obtains the number of ticks that the hires timer does in a second. This method is fast;
	 * it should be called as frequently as possible, as it recalibrates the timer.
	 *
	 * @return timer resolution in ticks per second or 0 if no timer is present.
	 */
	public static long getTimerResolution() {
		
		// Check for explicit override
		if (OVERRIDE_CALIBRATION) {
			return implementation.getTimerResolution();
		}
		
		// Firstly make sure we have at least some notion of a baseline
		if (defaultResolution == 0L) {
			defaultResolution = implementation.getTimerResolution();
			currentResolution = defaultResolution;
			if (DEBUG_CALIBRATION) {
				System.err.println("Initial timer calibration "+defaultResolution+" ticks per second");
			}
		}
		
		lowresNow = System.currentTimeMillis() & 0x7FFFFFFFFFFFFFFFL;
		hiresNow = getTime();
		if (lowresNow > lowresThen + SHORT_RECALIBRATION_THRESHOLD && hiresNow > hiresThen) {
			// We've had a lowres timer update. We can use this to calibrate the hires timer
			// resolution.
			double millis = lowresNow - lowresThen;
			double ticks = hiresNow - hiresThen;
			lowresThen = lowresNow;
			hiresThen = hiresNow;
			double ticksPerSecond = 1000.0 * ticks / millis;
			// If the ticksPerSecond we've calcuated is out by more than the error % from
			// the baseline resolution, we will discreetly replace the returned timer resolution
			// by our calculated. If this persists for more than a second or so we will adjust
			// the baseline resolution to the average that we've calculated over the second.
			double errorRatio = ticksPerSecond / (double) defaultResolution;
			if (errorRatio < LOWER_TIMER_ERROR || errorRatio > UPPER_TIMER_ERROR) {
				// We're outside the acceptable range so we will use the newly calculated ticks
				// per second. If we were already using recalibrated time, we start counting
				// the ticks that pass over a certain length of time. If that duration is exceeded
				// and we're still using recalibrated time, we permanently recalibrate.
				
				long tempResolution = (long) ticksPerSecond;
				
				if (DEBUG_CALIBRATION) {
					System.err.println("Temporary recalibration to "+tempResolution+" ticks per second");
				}
				
				if (currentResolution == defaultResolution) {
					// Start timing
					hiresRecalibrationThen = hiresNow;
					recalibrationThen = lowresNow;
					recalibrationNow = lowresNow;
				} else {
					// Continue counting ticks
					long ticksSinceRecalibration = hiresNow - hiresRecalibrationThen;
					if (ticksSinceRecalibration < 0) {
						// Bah. Wrapped timer, so forget it this time and start over.
						hiresRecalibrationThen = hiresNow;
						recalibrationThen = lowresNow;
					} else {
						recalibrationNow = lowresNow;
						long totalMillisSinceRecalibrationStarted = recalibrationNow - recalibrationThen;
						if (totalMillisSinceRecalibrationStarted > LONG_RECALIBRATION_THRESHOLD) {
							// Ok, we've now been operating at a different resolution for long enough.
							// Let's choose a new default resolution based on the average we've
							// calculated since it first started needing recalibrating
							long totalTicksSinceRecalibrationStarted = hiresNow - hiresRecalibrationThen;
							if (totalTicksSinceRecalibrationStarted > 0) {
								defaultResolution = (long) (1000.0 * totalTicksSinceRecalibrationStarted / totalMillisSinceRecalibrationStarted);
								tempResolution = defaultResolution;
								if (DEBUG_CALIBRATION) {
									System.err.println("Permanent recalibration to "+defaultResolution+" ticks per second");
								}
							} else {
								// Bah. Once again, a wrapped timer.
								hiresRecalibrationThen = hiresNow;
								recalibrationThen = lowresNow;
							}
						}
					}
				}

				// Temporarily change current resolution to the recently calculated resolution
				currentResolution = tempResolution;
			} else {
				currentResolution = defaultResolution;
			}
		}
		
		return currentResolution;
	}

	/**
	 * Gets the current value of the hires timer, in ticks. When the Sys class is first loaded
	 * the hires timer is reset to 0. If no hires timer is present then this method will always
	 * return 0.<p><strong>NOTEZ BIEN</strong> that the hires timer WILL wrap around.
	 *
	 * @return the current hires time, in ticks (always >= 0)
	 */
	public static long getTime() {
		return implementation.getTime() & 0x7FFFFFFFFFFFFFFFL;
	}

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
		if (title == null)
			title = "";
		if (message == null)
			message = "";
		implementation.alert(title, message);
		if (grabbed) {
			Mouse.setGrabbed(true);
		}
	}

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
	 * @param url The URL. Ensure that the URL is properly encoded.
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
				Boolean ret = (Boolean) showDocumentMethod.invoke(basicService, new Object[] {new URL(url)});
				return ret.booleanValue();
			} catch (MalformedURLException e) {
				e.printStackTrace(System.err);
				return false;
			}
		} catch (Exception ue) {
			return implementation.openURL(url);
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
		return implementation.getClipboard();
	}
}
