/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * Sys.java Created on Aug 1, 2002 by foo
 */
package org.lwjgl;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * Sys.java Created on Aug 1, 2002 by foo
 */
/**
 * System class (named Sys so as not to conflict with java.lang.Sys)
 * 
 * @author foo
 */
public final class Sys {

	static {
		initialize();
	}
	
	/** The native library name */
	public static final String LIBRARY_NAME = "lwjgl";

	/**
	 * No constructor for Sys.
	 */
	private Sys() {
	}
	
	/**
	 * Initialization.
	 */
	private static void initialize() {
		System.loadLibrary(LIBRARY_NAME);
		setTime(0.0f);
	}		

	/**
	 * Gets the address of a buffer. If the address cannot be obtained for any reason
	 * then this method returns 0.
	 * 
	 * @param buffer The buffer for which you want the
	 * @return the address of the direct buffer passed in
	 */
	public static native int getDirectBufferAddress(Buffer buffer);

	/**
	 * Create a direct byte buffer at the specified address with the specified
	 * capacity. Note that no actual memory allocation is performed. The returned
	 * direct byte buffer is in native endian order.
	 * 
	 * @param address The address of the buffer
	 * @param length The length in bytes that the buffer should have
	 * @return a direct ByteBuffer
	 * @throws IllegalArgumentException if address &lt;1 or length &lt;1
	 */
	public static native ByteBuffer createDirectBuffer(int address, int length)
		throws IllegalArgumentException;

	/**
	 * Obtains the order of resolution of the hires system timer. The returned resolution
	 * <i>n</i> describes the worst-case resolution in fractions of a second <i>1/10^n</i>.
	 * For example, a system timer which ticks every 1/5000th of a second will return 3
	 * to indicate that the resolution is at least as good as 1/1000th of a second.
	 * The reason for this simplistic measurement of resolution is to cut down on
	 * the temptation to code to too many specific timer resolutions arbitrarily.
	 * If no hires timer is available then this method returns -1. Any system incapable of
	 * a resolution of at least 3 is deemed not to have a hires timer and so this method will
	 * never return 0, 1, or 2. Furthermore this method will never advertise a
	 * resolution which cannot be accurately measured by a float.
	 *
	 * @return the order of resolution or -1 if no timer is present.
	 */
	public static native int getTimerResolution();
	
	/**
	 * Gets the current value of the hires timer, in seconds. When the Sys class is first loaded
	 * the hires timer is reset to 0.0f. If no hires timer is present then this method will always
	 * return whatever value the timer was last set to.
	 * 
	 * Because the time is returned as a float, after a certain length of time the
	 * hires timer can no longer represent time to the accuracy claimed by getTimerResolution().
	 * Therefore the time is guaranteed only to be accurate for up to 100 seconds.
	 * After 100 seconds has elapsed it is advisable to reset the timer. In reality
	 * you should reset the timer every iteration of your game loop and simply use the
	 * elapsed time for the iteration to increment your own, possibly more accurate
	 * timers.
	 *
	 * @return the current hires time, in seconds.
	 */
	public static native float getTime();
	
	/**
	 * Sets the hires timer to a new time. This time may be rounded by the available
	 * hires timer resolution; if the hires timer resolution is 3, and you specify
	 * a time of 0.0002f then the time will in fact be set to 0.0f.
	 * @param time the new time, in seconds
	 * @see #getTime()
	 * @see #getTimerResolution()
	 */
	public static native void setTime(float time);
	
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
	
} 
