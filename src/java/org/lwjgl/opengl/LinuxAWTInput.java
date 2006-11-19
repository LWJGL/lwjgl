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

/**
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision: 2586 $
 * $Id: LinuxAWTGLCanvasPeerInfo.java 2586 2006-10-20 11:51:34Z elias_naur $
 */
final class LinuxAWTInput extends AbstractAWTInput {
	private final long display;
	private final long input_window;
	private final LinuxEvent event = new LinuxEvent();

	private long cached_window;
	private LinuxMouse cached_mouse;
//	private LinuxKeyboard cached_keyboard;
	private long blank_cursor = LinuxDisplay.None;
	private boolean input_grabbed;
	private boolean input_released;

	public LinuxAWTInput(AWTGLCanvas canvas) throws LWJGLException {
		super(canvas);
		LinuxDisplay.lockAWT();
		try {
			this.display = LinuxDisplay.openDisplay();
			this.input_window = createInputOnlyWindow(display, LinuxDisplay.nGetDefaultScreen(display));
		} finally {
			LinuxDisplay.unlockAWT();
		}
	}
	private static native long createInputOnlyWindow(long display, int screen);

	public synchronized void destroy() {
		super.destroy();
		LinuxDisplay.lockAWT();
		try {
			destroyCursor();
			LinuxDisplay.nDestroyWindow(display, input_window);
			LinuxDisplay.closeDisplay(display);
		} finally {
			LinuxDisplay.unlockAWT();
		}
	}

	private void ungrabInputLocked() {
		LinuxDisplay.lockAWT();
		try {
			ungrabInput();
		} finally {
			LinuxDisplay.unlockAWT();
		}
	}

	private void ungrabInput() {
		if (input_grabbed) {
//			LinuxDisplay.nUngrabKeyboard(display);
			LinuxDisplay.nUngrabPointer(display);
			LinuxDisplay.nSetRepeatMode(display, LinuxDisplay.AutoRepeatModeDefault);
			input_grabbed = false;
		}
	}

	private void grabInput(long window) {
		if (!input_grabbed) {
//			int res1 = LinuxDisplay.nGrabKeyboard(display, window);
			LinuxDisplay.nSetRepeatMode(display, LinuxDisplay.AutoRepeatModeOff);
			int res2 = LinuxDisplay.nGrabPointer(display, window, blank_cursor);
			if (/*res1 == LinuxDisplay.GrabSuccess && */res2 == LinuxDisplay.GrabSuccess)
				input_grabbed = true;
		}
	}

	private final void destroyCursor() {
		if (blank_cursor != LinuxDisplay.None)
			LinuxDisplay.nDestroyCursor(display, blank_cursor);
	}

	public synchronized void processInput(PeerInfo peer_info) {
		LinuxDisplay.lockAWT();
		try {
			LinuxPeerInfo linux_peer_info = (LinuxPeerInfo)peer_info;
			long new_window = linux_peer_info.getDrawable();
			if (cached_mouse == null || new_window != cached_window) {
				ungrabInput();
				cached_window = new_window;
				try {
					cached_mouse = new LinuxMouse(display, new_window, input_window);
//					cached_keyboard = new LinuxKeyboard(display, new_window);
				} catch (LWJGLException e) {
					LWJGLUtil.log("Failed to create input devices: " + e);
				}
				destroyCursor();
				this.blank_cursor = LinuxDisplay.nCreateBlankCursor(display, new_window);
			}
			checkFocus();
			if (!input_grabbed && shouldGrab())
				grabInput(new_window);
			update();
		} finally {
			LinuxDisplay.unlockAWT();
		}
	}
	
	public void destroyMouse() {
		ungrabInputLocked();
		super.destroyMouse();
	}

	private void checkFocus() {
		if (getCanvas().isFocusOwner()) {
			input_released = false;
		} else {
			input_released = true;
			ungrabInput();
		}
	}

	private boolean shouldGrab() {
		return !input_released && isGrabbed() && getMouseEventQueue() != null;
	}

	private void update() {
		while (LinuxEvent.getPending(display) > 0) {
			event.nextEvent(display);
			if (shouldGrab()) {
				long event_window = event.getWindow();
				boolean processed = event.filterEvent(event_window) ||
					cached_mouse.filterEvent(isGrabbed(), shouldGrab(), event);/* ||
						cached_keyboard.filterEvent(event) */
			}
		}
	}

	public synchronized void grabMouse(boolean grab) {
		if (grab != isGrabbed()) {
			super.grabMouse(grab);
			if (cached_mouse != null)
				cached_mouse.changeGrabbed(grab, shouldGrab());
			ungrabInputLocked();
		}
	}

/*	public synchronized void pollKeyboard(ByteBuffer keyDownBuffer) {
		if (isGrabbed()) {
			LinuxDisplay.lockAWT();
			try {
				if (cached_keyboard != null)
					cached_keyboard.poll(keyDownBuffer);
			} finally {
				LinuxDisplay.unlockAWT();
			}
		} else
			super.pollKeyboard(keyDownBuffer);
	}

	public synchronized void readKeyboard(ByteBuffer buffer) {
		if (isGrabbed()) {
			LinuxDisplay.lockAWT();
			try {
				if (cached_keyboard != null)
					cached_keyboard.read(buffer);
			} finally {
				LinuxDisplay.unlockAWT();
			}
		} else
			super.readKeyboard(buffer);
	}
*/
	public synchronized void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons) {
		if (isGrabbed()) {
			LinuxDisplay.lockAWT();
			try {
				if (cached_mouse != null)
					cached_mouse.poll(isGrabbed(), coord_buffer, buttons);
			} finally {
				LinuxDisplay.unlockAWT();
			}
		} else
			super.pollMouse(coord_buffer, buttons);
	}

	public synchronized void readMouse(ByteBuffer buffer) {
		if (isGrabbed()) {
			LinuxDisplay.lockAWT();
			try {
				if (cached_mouse != null)
					cached_mouse.read(buffer);
			} finally {
				LinuxDisplay.unlockAWT();
			}
		} else
			super.readMouse(buffer);
	}
}
