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
package org.lwjgl.opengl;

import java.nio.IntBuffer;
import java.nio.ByteBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.BufferUtils;

import java.awt.Cursor;
import java.awt.Point;

/**
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision: 2586 $
 * $Id: LinuxAWTGLCanvasPeerInfo.java 2586 2006-10-20 11:51:34Z elias_naur $
 */
final class WindowsAWTInput extends AbstractAWTInput {
	private final static int WS_CHILD = 0x40000000;
	private final Cursor blank_cursor;
	private Cursor cached_cursor;

	private long cached_hwnd;
	private WindowsMouse cached_mouse;
	private WindowsKeyboard cached_keyboard;
	private boolean has_grabbed;

	public WindowsAWTInput(AWTGLCanvas canvas) throws LWJGLException {
		super(canvas);
		int w = AWTUtil.getMinCursorSize();
		int h = AWTUtil.getMinCursorSize();
		blank_cursor = AWTUtil.createCursor(w, h, 0, 0, 1, BufferUtils.createIntBuffer(w*h), null);
	}

	public synchronized void destroyMouse() {
		if (cached_mouse != null) {
			grab(false);
			cached_mouse.destroy();
		}
		super.destroyMouse();
	}

	public synchronized void processInput(PeerInfo peer_info) {
		WindowsPeerInfo windows_peerinfo = (WindowsPeerInfo)peer_info;
		long hwnd = windows_peerinfo.getHwnd();
		try {
			hwnd = findTopLevelWindow(hwnd);
			if (cached_mouse == null || hwnd != cached_hwnd) {
				has_grabbed = false;
				cached_hwnd = hwnd;
				if (cached_mouse != null)
					cached_mouse.destroy();
				WindowsDirectInput dinput = WindowsDisplay.createDirectInput();
				cached_mouse = new WindowsMouse(dinput, hwnd);
				cached_keyboard = new WindowsKeyboard(dinput, hwnd);
			}
			if (isGrabbed() && getCanvas().getCursor() != blank_cursor) {
				cached_cursor = getCanvas().getCursor();
				/**
				 * For some reason, DirectInput won't let us blank the cursor
				 * with the EXCLUSIVE access mode, so we'll work around it with a
				 * custom blank cursor
				 */
				getCanvas().setCursor(blank_cursor);
			}
			grab(isGrabbed());
		} catch (LWJGLException e) {
			LWJGLUtil.log("Failed to create windows mouse: " + e);
		}
	}
	private static native int getWindowStyles(long hwnd) throws LWJGLException;
	private static native long getParentWindow(long hwnd);

	private static long findTopLevelWindow(long hwnd) throws LWJGLException {
		int window_styles = getWindowStyles(hwnd);
		while ((window_styles & WS_CHILD) != 0) {
			hwnd = getParentWindow(hwnd);
			window_styles = getWindowStyles(hwnd);
		}
		return hwnd;
	}

	private void grab(boolean grab) {
		if (has_grabbed != grab) {
			cached_mouse.grab(grab);
			has_grabbed = grab;
			if (!grab)
				getCanvas().setCursor(cached_cursor);
		}
	}

	public synchronized void grabMouse(boolean grab) {
		if (grab != isGrabbed()) {
			super.grabMouse(grab);
			/* Only ungrab since grabbing can only occur in processInput
			 * when the hwnd is guaranteed valid
			 */
			if (cached_mouse != null && !grab)
				grab(grab);
		}
	}

	public synchronized void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons) {
		if (isGrabbed()) {
			if (cached_mouse != null)
				cached_mouse.poll(coord_buffer, buttons);
		} else
			super.pollMouse(coord_buffer, buttons);
	}

	public synchronized void readMouse(ByteBuffer buffer) {
		if (isGrabbed()) {
			if (cached_mouse != null)
				cached_mouse.read(buffer);
		} else
			super.readMouse(buffer);
	}

	public synchronized void readKeyboard(ByteBuffer buffer) {
		if (isGrabbed()) {
			if (cached_keyboard != null)
				cached_keyboard.read(buffer);
		} else
			super.readKeyboard(buffer);
	}

	public synchronized void pollKeyboard(ByteBuffer keyDownBuffer) {
		if (isGrabbed()) {
			if (cached_keyboard != null)
				cached_keyboard.poll(keyDownBuffer);
		} else
			super.pollKeyboard(keyDownBuffer);
	}
}
