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
 * The AWT compatible Canvas for Mac OS X.
 * @author elias_naur
 */

import java.awt.Canvas;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

final class MacOSXCanvasListener implements ComponentListener, HierarchyListener {
	private final Canvas canvas;
	private int width;
	private int height;
	private boolean context_update;
	private boolean resized;

	MacOSXCanvasListener(Canvas canvas) {
		this.canvas = canvas;
		canvas.addComponentListener(this);
		canvas.addHierarchyListener(this);
		setUpdate();
	}

	public void disableListeners() {
		// Mac OS X applets will hang in Display.destroy() when parented when removing the listeners directly
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				canvas.removeComponentListener(MacOSXCanvasListener.this);
				canvas.removeHierarchyListener(MacOSXCanvasListener.this);
			}
		});
	}

	public boolean syncShouldUpdateContext() {
		boolean should_update;
		synchronized ( this ) {
			should_update = context_update;
			context_update = false;
		}
		return should_update;
	}

	private synchronized void setUpdate() {
		synchronized ( this ) {
			width = canvas.getWidth();
			height = canvas.getHeight();
			context_update = true;
		}
	}

	public int syncGetWidth() {
		synchronized ( this ) {
			return width;
		}
	}

	public int syncGetHeight() {
		synchronized ( this ) {
			return height;
		}
	}

	public void componentShown(ComponentEvent e) {
	}

	public void componentHidden(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		setUpdate();
		resized = true;
	}

	public void componentMoved(ComponentEvent e) {
		setUpdate();
	}

	public void hierarchyChanged(HierarchyEvent e) {
		setUpdate();
	}
	
	public boolean wasResized() {
		if (resized) {
			resized = false;
			return true;
		}
		
		return false;
	}
}
