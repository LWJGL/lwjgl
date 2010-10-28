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

import java.nio.ByteBuffer;

/**
 * Wrapper class for X11 events.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision: 2286 $
 * $Id: LinuxPeerInfo.java 2286 2006-03-23 19:32:21Z matzon $
 */
final class LinuxEvent {
	public static final int FocusIn         = 9;
	public static final int FocusOut        = 10;
	public static final int KeyPress        = 2;
	public static final int KeyRelease      = 3;
	public static final int ButtonPress     = 4;
	public static final int ButtonRelease	= 5;
	public static final int MotionNotify	= 6;
	public static final int EnterNotify		= 7;
	public static final int LeaveNotify		= 8;
	public static final int UnmapNotify     = 18;
	public static final int MapNotify       = 19;
	public static final int Expose          = 12;
	public static final int ClientMessage   = 33;

	private final ByteBuffer event_buffer;

	LinuxEvent() {
		this.event_buffer = createEventBuffer();
	}
	private static native ByteBuffer createEventBuffer();

	public void copyFrom(LinuxEvent event) {
		int pos = event_buffer.position();
		int event_pos = event.event_buffer.position();
		event_buffer.put(event.event_buffer);
		event_buffer.position(pos);
		event.event_buffer.position(event_pos);
	}

	public static native int getPending(long display);

	public void sendEvent(long display, long window, boolean propagate, long event_mask) {
		nSendEvent(event_buffer, display, window, propagate, event_mask);
	}
	private static native void nSendEvent(ByteBuffer event_buffer, long display, long window, boolean propagate, long event_mask);

	public boolean filterEvent(long window) {
		return nFilterEvent(event_buffer, window);
	}
	private static native boolean nFilterEvent(ByteBuffer event_buffer, long window);

	public void nextEvent(long display) {
		nNextEvent(display, event_buffer);
	}
	private static native void nNextEvent(long display, ByteBuffer event_buffer);

	public int getType() {
		return nGetType(event_buffer);
	}
	private static native int nGetType(ByteBuffer event_buffer);

	public long getWindow() {
		return nGetWindow(event_buffer);
	}
	private static native long nGetWindow(ByteBuffer event_buffer);

	public void setWindow(long window) {
		nSetWindow(event_buffer, window);
	}
	private static native void nSetWindow(ByteBuffer event_buffer, long window);

	/* Focus methods */

	public int getFocusMode() {
		return nGetFocusMode(event_buffer);
	}
	private static native int nGetFocusMode(ByteBuffer event_buffer);

	public int getFocusDetail() {
		return nGetFocusDetail(event_buffer);
	}
	private static native int nGetFocusDetail(ByteBuffer event_buffer);

	/* ClientMessage methods */

	public long getClientMessageType() {
		return nGetClientMessageType(event_buffer);
	}
	private static native long nGetClientMessageType(ByteBuffer event_buffer);

	public int getClientData(int index) {
		return nGetClientData(event_buffer, index);
	}
	private static native int nGetClientData(ByteBuffer event_buffer, int index);

	public int getClientFormat() {
		return nGetClientFormat(event_buffer);
	}
	private static native int nGetClientFormat(ByteBuffer event_buffer);

	/* Button methods */

	public long getButtonTime() {
		return nGetButtonTime(event_buffer);
	}
	private static native long nGetButtonTime(ByteBuffer event_buffer);

	public int getButtonState() {
		return nGetButtonState(event_buffer);
	}
	private static native int nGetButtonState(ByteBuffer event_buffer);

	public int getButtonType() {
		return nGetButtonType(event_buffer);
	}
	private static native int nGetButtonType(ByteBuffer event_buffer);

	public int getButtonButton() {
		return nGetButtonButton(event_buffer);
	}
	private static native int nGetButtonButton(ByteBuffer event_buffer);

	public long getButtonRoot() {
		return nGetButtonRoot(event_buffer);
	}
	private static native long nGetButtonRoot(ByteBuffer event_buffer);

	public int getButtonXRoot() {
		return nGetButtonXRoot(event_buffer);
	}
	private static native int nGetButtonXRoot(ByteBuffer event_buffer);

	public int getButtonYRoot() {
		return nGetButtonYRoot(event_buffer);
	}
	private static native int nGetButtonYRoot(ByteBuffer event_buffer);

	public int getButtonX() {
		return nGetButtonX(event_buffer);
	}
	private static native int nGetButtonX(ByteBuffer event_buffer);

	public int getButtonY() {
		return nGetButtonY(event_buffer);
	}
	private static native int nGetButtonY(ByteBuffer event_buffer);

	/* Key methods */

	public long getKeyAddress() {
		return nGetKeyAddress(event_buffer);
	}
	private static native long nGetKeyAddress(ByteBuffer event_buffer);

	public long getKeyTime() {
		return nGetKeyTime(event_buffer);
	}
	private static native int nGetKeyTime(ByteBuffer event_buffer);

	public int getKeyType() {
		return nGetKeyType(event_buffer);
	}
	private static native int nGetKeyType(ByteBuffer event_buffer);

	public int getKeyKeyCode() {
		return nGetKeyKeyCode(event_buffer);
	}
	private static native int nGetKeyKeyCode(ByteBuffer event_buffer);

	public int getKeyState() {
		return nGetKeyState(event_buffer);
	}
	private static native int nGetKeyState(ByteBuffer event_buffer);
}
