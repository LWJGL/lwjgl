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
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;

final class MacOSXGLCanvas extends Canvas implements HierarchyBoundsListener {
	private boolean context_update;
	private boolean canvas_created;
	private boolean dirty;

	public MacOSXGLCanvas() {
		setFocusTraversalKeysEnabled(false);
		/* Input methods are not enabled in fullscreen anyway, so disable always */
		enableInputMethods(false);
		addHierarchyBoundsListener(this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		synchronized (this) {
			dirty = true;
			if (!canvas_created) {
				((MacOSXDisplay)Display.getImplementation()).setView(this);
				canvas_created = true;
				setUpdate();
				notify();
			}
		}
	}

	public boolean isDirty() {
		boolean result;
		synchronized (this) {
			result = dirty;
			dirty = false;
		}
		return result;
	}
	
	public void waitForCanvasCreated() {
		synchronized (this) {
			while (!canvas_created) {
				try {
					wait();
				} catch (InterruptedException e) {
					// ignore
				}
			}
		}
	}
	
	public boolean shouldUpdateContext() {
		boolean should_update;
		synchronized (this) {
			should_update = context_update;
			context_update = false;
		}
		return should_update;
	}
	
	private synchronized void setUpdate() {
		context_update = true;
	}

	public void ancestorResized(HierarchyEvent e) {
		setUpdate();
	}

	public void ancestorMoved(HierarchyEvent e) {
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
}
