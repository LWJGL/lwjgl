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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import org.lwjgl.Sys;
import org.lwjgl.LWJGLErrorException;

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
	/** First element to display */
	private CursorElement[] cursors = null;
	
	/** Index into list of cursors */
	private int index = -1;
	
	/**
	 * Constructs a new Cursor, with the given parameters. Mouse must have been created before you can create
	 * Cursor objects. Cursor images are in ARGB format, but only one bit transparancy is guaranteed to be supported.
	 * So to maximize portability, lwjgl applications should only create cursor images with 0x00 or 0xff as alpha values.
	 * The constructor will copy the images and delays, so there's no need to keep them around.
	 *
	 * @param width cursor image width
	 * @param height cursor image height
	 * @param xHotspot the x coordinate of the cursor hotspot
	 * @param yHotspot the y coordinate of the cursor hotspot
	 * @param numImages number of cursor images specified. Must be 1 if animations are not supported.
	 * @param images A buffer containing the images. The origin is at the lower left corner, like OpenGL.
	 * @param delays An int buffer of animation frame delays, if numImages is greater than 1, else null
	 * @throws LWJGLErrorException if the cursor could not be created for any reason
	 */	
	public Cursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLErrorException {
		if (!Mouse.isCreated())
			throw new IllegalStateException("Mouse must be created before creating cursor objects");
		if (width*height*numImages > images.remaining())
			throw new IllegalArgumentException("width*height*numImages > images.remaining()");
		if (delays != null && numImages > delays.remaining())
			throw new IllegalArgumentException("delays != null && numImages > delays.remaining()");
		if (xHotspot >= width || xHotspot < 0)
			throw new IllegalArgumentException("xHotspot > width || xHotspot < 0");
		if (yHotspot >= height || yHotspot < 0)
			throw new IllegalArgumentException("yHotspot > height || yHotspot < 0");
		
		Sys.initialize();
		
		// Hmm 
		yHotspot = height - 1 - yHotspot;		
		
		// create cursor (or cursors if multiple images supplied)
		createCursors(width, height, xHotspot, yHotspot, numImages, images, delays);
	}
	
	/**
	 * Creates the actual cursor, using a platform specific class
	 */
	private void createCursors(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLErrorException {
		// create copy and flip images to match ogl
		IntBuffer images_copy = ByteBuffer.allocateDirect(images.remaining()*4).order(ByteOrder.nativeOrder()).asIntBuffer();
		flipImages(width, height, numImages, images, images_copy);
		
		// create our cursor elements
		cursors = new CursorElement[numImages];
		for(int i=0; i<numImages; i++) {
			cursors[i] = new CursorElement();
			cursors[i].cursorHandle = nCreateCursor(width, height, xHotspot, yHotspot, 1, images_copy, images_copy.position());
			cursors[i].delay = (delays != null) ? delays.get(i) : 0;
			cursors[i].timeout = System.currentTimeMillis();
			
			// offset to next image
			images_copy.position(width*height*(i+1));
		}
		
		// set index
		index = 0;
	}
 
	
	/**
	 * Flips the images so they're oriented according to opengl
	 * 
	 * @param width Width of image
	 * @param height Height of images 
	 * @param numImages How many images to flip
	 * @param images Source images
	 * @param images_copy Destination images
	 */
	private static void flipImages(int width, int height, int numImages, IntBuffer images, IntBuffer images_copy) {
		for (int i = 0; i < numImages; i++) {
			int start_index = i*width*height;
			flipImage(width, height, start_index, images, images_copy);
		}
	}

	/**
	 * @param width Width of image
	 * @param height Height of images 
	 * @param start_index index into source buffer to copy to
	 * @param images Source images
	 * @param images_copy Destination images
	 */
	private static void flipImage(int width, int height, int start_index, IntBuffer images, IntBuffer images_copy) {
		for (int y = 0; y < height>>1; y++) {
			int index_y_1 = y*width + start_index;
			int index_y_2 = (height - y - 1)*width + start_index;
			for (int x = 0; x < width; x++) {
				int index1 = index_y_1 + x;
				int index2 = index_y_2 + x;
				int temp_pixel = images.get(index1 + images.position());
				images_copy.put(index1, images.get(index2 + images.position()));
				images_copy.put(index2, temp_pixel);
			}
		}
	}	
	
	/**
	 * Gets the native handle associated with the cursor object.
	 */
	public long getHandle() {
		return cursors[index].cursorHandle;
	}
	
	/**
	 * Destroy the native cursor. Cursor must not be current.
	 */
	public void destroy() {
		for(int i=0; i<cursors.length; i++) {
			nDestroyCursor(cursors[i].cursorHandle);
		}
	}

	/**
	 * Sets the timout property to the time it should be changed
	 */
	protected void setTimeout() {
		cursors[index].timeout = System.currentTimeMillis() + cursors[index].delay;
	}

	/**
	 * Determines whether this cursor has timed out
	 * @return true if the this cursor has timed out, false if not
	 */
	protected boolean hasTimedOut() {
		return cursors.length > 1 && cursors[index].timeout < System.currentTimeMillis();
	}

	/** 
	 * Changes to the next cursor 
	 */
	protected void nextCursor() {
		index = ++index % cursors.length;
	}

	/** 
	 * Resets the index of the cursor animation to the first in the list. 
	 */
	public void resetAnimation() {
		index = 0;
	}
	
	/**
	 * Native method to create a native cursor
	 */
	private static native long nCreateCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, int images_offset);

	/**
	 * Native method to destroy a native cursor
	 */
	private static native void nDestroyCursor(long cursorHandle);	
	
	/**
	 * A single cursor element, used when animating
	 */
	protected class CursorElement {
		/** Handle to cursor */
		long cursorHandle;
		
		/** How long a delay this element should have */
		long delay;
		
		/** Absolute time this element times out */
		long timeout;
	}
}
