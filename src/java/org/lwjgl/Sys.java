/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * $Id$
 *
 * System class (named Sys so as not to conflict with java.lang.System)
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public final class Sys {

	/** Low process priority. @see #setProcessPriority() */
	public static final int LOW_PRIORITY = -1;

	/**
	 * Normal process priority. This priority equates to the priority that the
	 * JVM has when it is started up normally. Note that if the JVM is started
	 * inside a process which is already a different priority then this will not
	 * be the initial priority.
	 * 
	 * @see #setProcessPriority()
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
	 * @see #setProcessPriority()
	 */
	public static final int REALTIME_PRIORITY = 2;

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
		setTime(0);
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
	
} 
