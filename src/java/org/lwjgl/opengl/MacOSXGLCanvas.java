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

/**
 * The AWT compatible Canvas for Mac OS X.
 * @author elias_naur
 */

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

final class MacOSXGLCanvas extends Canvas implements ComponentListener, HierarchyListener {

	private int width;
	private int height;
	private boolean context_update;
	private boolean canvas_painted;
	private boolean dirty;

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		synchronized ( this ) {
			dirty = true;
			canvas_painted = true;
		}
	}

	/**
	  * This initializes the canvas and binds the context to it.
	  */
	public void initializeCanvas() {
		setFocusTraversalKeysEnabled(false);
		/* Input methods are not enabled in fullscreen anyway, so disable always */
		enableInputMethods(false);
		addComponentListener(this);
		addHierarchyListener(this);
//		((MacOSXDisplay)Display.getImplementation()).setView(this);
		setUpdate();
	}

	public boolean syncCanvasPainted() {
		boolean result;
		synchronized (this) {
			result = canvas_painted;
			canvas_painted = false;
		}
		return result;
	}

	public boolean syncIsDirty() {
		boolean result;
		synchronized ( this ) {
			result = dirty;
			dirty = false;
		}
		return result;
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
			width = getWidth();
			height = getHeight();
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
	}

	public void componentMoved(ComponentEvent e) {
		setUpdate();
	}

	public void setLocation(int x, int y) {
		super.setLocation(x, y);
		setUpdate();
	}

	public void setLocation(Point p) {
		super.setLocation(p);
		setUpdate();
	}

	public void setSize(Dimension d) {
		super.setSize(d);
		setUpdate();
	}

	public void setSize(int width, int height) {
		super.setSize(width, height);
		setUpdate();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		setUpdate();
	}

	public void hierarchyChanged(HierarchyEvent e) {
		setUpdate();
	}
}
