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
package org.lwjgl.opengl;

/**
 * A Cocoa implementation of a LWJGL compatible Mouse.
 * @author mojang
 * @author kappaOne <one.kappa@gmail.com>
 */

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.input.Mouse;
import org.lwjgl.LWJGLException;

import java.lang.reflect.*;
import java.lang.Integer;
import java.lang.Long;

import org.lwjgl.BufferUtils;

final class MacOSXNativeMouse extends EventQueue {
    private static final int WHEEL_SCALE = 120;
    private static final int NUM_BUTTONS = 3;
    
    private ByteBuffer window_handle;
    private MacOSXDisplay display;
    
    private boolean grabbed;
    
    /** The accumulated mouse deltas returned by poll() */
    private float accum_dx;
    private float accum_dy;
    private int accum_dz;
    
    /** The last mouse position */
    private float last_x;
    private float last_y;
    
    /** Saved control key state for ctrl-click right button emulation */
    private boolean saved_control_state;
    
    private final ByteBuffer event = ByteBuffer.allocate(Mouse.EVENT_SIZE);
    private IntBuffer delta_buffer = BufferUtils.createIntBuffer(2);
    private int skip_event;
    
    private final byte[] buttons = new byte[NUM_BUTTONS];
    
	MacOSXNativeMouse(MacOSXDisplay display, ByteBuffer window_handle) {
        super(Mouse.EVENT_SIZE);
        this.display = display;
        this.window_handle = window_handle;
	}
    
    private native void nSetCursorPosition(ByteBuffer window_handle, int x, int y);

	public static native void nGrabMouse(boolean grab);

	private native void nRegisterMouseListener(ByteBuffer window_handle);
    
	private native void nUnregisterMouseListener(ByteBuffer window_handle);
	
	private static native long nCreateCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, int images_offset, IntBuffer delays, int delays_offset) throws LWJGLException;
	
	private static native void nDestroyCursor(long cursor_handle);
	
	private static native void nSetCursor(long cursor_handle) throws LWJGLException;
    
	public synchronized void register() {
		nRegisterMouseListener(window_handle);
	}
	
	public static long createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
		try {
			return nCreateCursor(width, height, xHotspot, yHotspot, numImages, images, images.position(), delays, delays != null ? delays.position() : -1);
		} catch (LWJGLException e) {
			throw e;
		}
	}
	
	public static void destroyCursor(long cursor_handle) {
		nDestroyCursor(cursor_handle);
	}
	
	public static void setCursor(long cursor_handle) throws LWJGLException {
		try {
			nSetCursor(cursor_handle);
		} catch (LWJGLException e) {
			throw e;
		}
	}
	
    public synchronized void setCursorPosition(int x, int y) {
    		nSetCursorPosition(window_handle, x, y);
    }
    
    public synchronized void unregister() {
        nUnregisterMouseListener(window_handle);
    }
    
    public synchronized void setGrabbed(boolean grabbed) {
        this.grabbed = grabbed;
        nGrabMouse(grabbed);
        skip_event = 1;
        accum_dx = accum_dy = 0;
    }
    
    public synchronized boolean isGrabbed() {
        return grabbed;
    }
    
    protected void resetCursorToCenter() {
        clearEvents();
        accum_dx = accum_dy = 0;
        if (display != null) {
            last_x = display.getWidth() / 2;
            last_y = display.getHeight() / 2;
        }
    }
    
	private void putMouseEvent(byte button, byte state, int dz, long nanos) {
		if (grabbed)
			putMouseEventWithCoords(button, state, 0, 0, dz, nanos);
		else
			putMouseEventWithCoords(button, state, (int)last_x, (int)last_y, dz, nanos);
	}

	protected void putMouseEventWithCoords(byte button, byte state, int coord1, int coord2, int dz, long nanos) {
		event.clear();
		event.put(button).put(state).putInt(coord1).putInt(coord2).putInt(dz).putLong(nanos);
		event.flip();
		putEvent(event);
	}

    public synchronized void poll(IntBuffer coord_buffer, ByteBuffer buttons_buffer) {
        if (grabbed) {
            coord_buffer.put(0, (int)accum_dx);
            coord_buffer.put(1, (int)accum_dy);
        } else {
            coord_buffer.put(0, (int)last_x);
            coord_buffer.put(1, (int)last_y);
        }
        coord_buffer.put(2, accum_dz);
        accum_dx = accum_dy = accum_dz = 0;
        int old_position = buttons_buffer.position();
        buttons_buffer.put(buttons, 0, buttons.length);
        buttons_buffer.position(old_position);
    }
    
	private void setCursorPos(float x, float y, long nanos) {
		if ( grabbed )
			return;
		float dx = x - last_x;
		float dy = y - last_y;
		addDelta(dx, dy);
		last_x = x;
		last_y = y;
		putMouseEventWithCoords((byte)-1, (byte)0, (int)x, (int)y, 0, nanos);
	}
    
    protected void addDelta(float dx, float dy) {
		accum_dx += dx;
		accum_dy += -dy;
	}
    
	public synchronized void setButton(int button, int state, long nanos) {
		buttons[button] = (byte)state;
		putMouseEvent((byte)button, (byte)state, 0, nanos);
	}
    
	public synchronized void mouseMoved(float x, float y, float dx, float dy, float dz, long nanos) {
		if (skip_event > 0) {
			skip_event--;
			if (skip_event == 0) {
				last_x = x;
				last_y = y;
			}
			return;
		}
		
		if ( dz != 0 ) { // if scroll wheel event
			// if no vertical wheel events, then map the horizontal wheel event to it
			if (dy == 0) dy = dx;

			int wheel_amount = (int)(dy * WHEEL_SCALE);
			accum_dz += wheel_amount;
			putMouseEvent((byte)-1, (byte)0, wheel_amount, nanos);
		}
		else if (grabbed) {
			if ( dx != 0 || dy != 0 ) {
				putMouseEventWithCoords((byte)-1, (byte)0, (int)dx, (int)-dy, 0, nanos);
				addDelta(dx, dy);
			}
		} else {
			setCursorPos(x, y, nanos);
		}
	}
}
