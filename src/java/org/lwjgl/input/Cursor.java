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

package org.lwjgl.input;

import org.lwjgl.Sys;

/**
 * $Id$
 *
 * A class representing a native cursor. Instances of this
 * class can be used with Mouse.setCursor(), if available.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

public class Cursor {

        static {
                System.loadLibrary(Sys.getLibraryName());
        }

	/**
	 * The native handle to the cursor
	 */
	private final int nativeHandle;

	/**
	 * Constructs a new Cursor, with the given parameters. Mouse must have been created before you can create
	 * Cursor objects. Cursor images are in ARGB format, but only one bit transparancy is guaranteed to be supported.
	 * So to maximize portability, lwjgl applications should only create cursor images with 0x00 or 0xff as alpha values.
	 * 
	 * @param width cursor image width
	 * @param height cursor image height
	 * @param xHotspot the x coordinate of the cursor hotspot
	 * @param yHotspot the y coordinate of the cursor hotspot
	 * @param numImages number of cursor images specified. Must be 1 if animations are not supported.
	 * @param cursorAddress the address of an int array containing the cursor image
	 * @param delayAddresses the address of animation frame delays, if numImages is greater than 1, else Sys.NULL
         * @throws Exception if the cursor could not be created for any reason
	 */
	public Cursor(int width, int height, int xHotspot, int yHotspot, int numImages, int imageAddress, int delayAddresses) throws Exception {
		assert Mouse.isCreated();
		nativeHandle = nCreateCursor(width, height, xHotspot, yHotspot, numImages, imageAddress, delayAddresses);
	}

	/**
	 * Destroy the native cursor. Cursor must not be current.
	 */
	public void destroy() {
		nDestroyCursor(nativeHandle);
	}

	/**
	 * Gets the native handle associated with the cursor object.
	 */
	public int getHandle() {
		return nativeHandle;
	}

	/**
	 * Native method to create a native cursor
	 */
	private static native int nCreateCursor(int width, int height, int xHotspot, int yHotspot, int numImages, int imageAddresses, int delayAddresses);

	/**
	 * Native method to destroy a native cursor
	 */
	private static native void nDestroyCursor(int handle);
}
