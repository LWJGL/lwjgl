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
 * An AWT implementation of a LWJGL compatible Mouse event queue.
 * @author elias_naur
 */

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

final class MacOSXMouseEventQueue extends MouseEventQueue {
	private final IntBuffer delta_buffer = BufferUtils.createIntBuffer(2);

	private boolean skip_event;
	private static boolean is_grabbed;
	
	MacOSXMouseEventQueue(Component component) {
		super(component);
	}

	public void setGrabbed(boolean grab) {
		if (is_grabbed != grab) {
			super.setGrabbed(grab);
			warpCursor();
			grabMouse(grab);
		}
	}
	
	private static synchronized void grabMouse(boolean grab) {
		is_grabbed = grab;
		if (!grab)
			nGrabMouse(grab);
	}

	protected void resetCursorToCenter() {
		super.resetCursorToCenter();
		/* Clear accumulated deltas */
		getMouseDeltas(delta_buffer);
	}

	protected void updateDeltas(long nanos) {
		super.updateDeltas(nanos);
		synchronized ( this ) {
			getMouseDeltas(delta_buffer);
			int dx = delta_buffer.get(0);
			int dy = -delta_buffer.get(1);
			if (skip_event) {
				skip_event = false;
				nGrabMouse(isGrabbed());
				return;
			}
			if ( dx != 0 || dy != 0 ) {
				putMouseEventWithCoords((byte)-1, (byte)0, dx, dy, 0, nanos);
				addDelta(dx, dy);
			}
		}
	}

	void warpCursor() {
		synchronized (this) {
			// If we're going to warp the cursor position, we'll skip the next event to avoid bogus delta values
			skip_event = isGrabbed();
		}
		if (isGrabbed()) {
			Rectangle bounds = getComponent().getBounds();
			Point location_on_screen = getComponent().getLocationOnScreen();
			int x = location_on_screen.x + bounds.width/2;
			int y = location_on_screen.y + bounds.height/2;
			nWarpCursor(x, y);
		}
	}

	private static native void getMouseDeltas(IntBuffer delta_buffer);

	private static native void nWarpCursor(int x, int y);

	static native void nGrabMouse(boolean grab);
}