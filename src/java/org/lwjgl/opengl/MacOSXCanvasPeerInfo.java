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

import java.awt.Canvas;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.Insets;
import java.awt.Container;
import java.awt.Component;
import java.awt.Point;
import java.awt.Window;
import java.nio.ByteBuffer;

import javax.swing.SwingUtilities;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;

/**
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @author kappaOne <one.kappa@gmail.com>
 * @version $Revision$
 * $Id$
 */
abstract class MacOSXCanvasPeerInfo extends MacOSXPeerInfo {
	private final AWTSurfaceLock awt_surface = new AWTSurfaceLock();
	public ByteBuffer window_handle;
	
	protected MacOSXCanvasPeerInfo(PixelFormat pixel_format, ContextAttribs attribs, boolean support_pbuffer) throws LWJGLException {
		super(pixel_format, attribs, true, true, support_pbuffer, true);
	}

	protected void initHandle(Canvas component) throws LWJGLException {
		boolean forceCALayer = true;
		boolean autoResizable = true; // set the CALayer to autoResize
		
		String javaVersion = System.getProperty("java.version");
		
		if (javaVersion.startsWith("1.5") || javaVersion.startsWith("1.6")) {
			// On Java 7 and newer CALayer mode is the only way to use OpenGL with AWT
			// therefore force it on all JVM's except for the older Java 5 and Java 6
			// where the older cocoaViewRef NSView method maybe be available.
			forceCALayer = false;
		}
		else if (javaVersion.startsWith("1.7")) {
			autoResizable = false;
		}
		
		Insets insets = getInsets(component);
		
		int top = insets != null ? insets.top : 0;
		int left = insets != null ? insets.left : 0;
		
		window_handle = nInitHandle(awt_surface.lockAndGetHandle(component), getHandle(), window_handle, forceCALayer, autoResizable, component.getX()-left, component.getY()-top);
		
		if (javaVersion.startsWith("1.7")) {
			// fix for CALayer position not covering Canvas due to a Java 7 bug
			// http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=7172187
			addComponentListener(component);
			
			if (SwingUtilities.getWindowAncestor(component.getParent()) != null) {
				Point componentPosition = SwingUtilities.convertPoint(component, component.getLocation(), null);
				Point parentPosition = SwingUtilities.convertPoint(component.getParent(), component.getLocation(), null);
				
				if (componentPosition.getX() == parentPosition.getX() && componentPosition.getY() == parentPosition.getY()) {
					insets = getWindowInsets(component);
					
					top = insets != null ? insets.top : 0;
					left = insets != null ? insets.left : 0;
					
					int x = (int)componentPosition.getX()-left;
					int y = (int)-componentPosition.getY()+top-component.getHeight();
					
					int width = component.getWidth();
					int height = component.getHeight();
					
					nSetLayerBounds(getHandle(), x, y, width, height);
				}
			}
		}
	}
	
	private void addComponentListener(final Canvas component) {
		
		ComponentListener[] components = component.getComponentListeners();
		
		// avoid adding duplicate listners by checking if one has already been added
		for (int i = 0; i < components.length; i++) {
			ComponentListener c = components[i];
			if (c.toString() == "CanvasPeerInfoListener") {
				return; // already contains the listner below return without adding
			}
		}
		
		ComponentListener comp = new ComponentListener() {
			public void componentHidden(ComponentEvent e) {
				
			}

			public void componentMoved(ComponentEvent e) {
				
				if (SwingUtilities.getWindowAncestor(component.getParent()) != null) {
					Point componentPosition = SwingUtilities.convertPoint(component, component.getLocation(), null);
					Point parentPosition = SwingUtilities.convertPoint(component.getParent(), component.getLocation(), null);
					
					if (componentPosition.getX() == parentPosition.getX() && componentPosition.getY() == parentPosition.getY()) {
						Insets insets = getWindowInsets(component);
						
						int top = insets != null ? insets.top : 0;
						int left = insets != null ? insets.left : 0;
						
						nSetLayerBounds(getHandle(), (int)componentPosition.getX()-left, (int)componentPosition.getY()-top, component.getWidth(), component.getHeight());
						return;
					}
				}
				
				Insets insets = getInsets(component);
				
				int top = insets != null ? insets.top : 0;
				int left = insets != null ? insets.left : 0;
				
				//nSetLayerPosition(getHandle(), component.getX() - left, component.getY() - top);
				nSetLayerBounds(getHandle(), component.getX() - left, component.getY() - top, component.getWidth(), component.getHeight());
			}

			public void componentResized(ComponentEvent e) {
				
				if (SwingUtilities.getWindowAncestor(component.getParent()) != null) {
					Point componentPosition = SwingUtilities.convertPoint(component, component.getLocation(), null);
					Point parentPosition = SwingUtilities.convertPoint(component.getParent(), component.getLocation(), null);
					
					if (componentPosition.getX() == parentPosition.getX() && componentPosition.getY() == parentPosition.getY()) {
						Insets insets = getWindowInsets(component);
						
						int top = insets != null ? insets.top : 0;
						int left = insets != null ? insets.left : 0;
						
						nSetLayerBounds(getHandle(), (int)componentPosition.getX()-left, (int)componentPosition.getY()-top, component.getWidth(), component.getHeight());
						return;
					}
				}
				
				Insets insets = getInsets(component);
				
				int top = insets != null ? insets.top : 0;
				int left = insets != null ? insets.left : 0;
				
				//nSetLayerPosition(getHandle(), component.getX() - left, component.getY() - top);
				nSetLayerBounds(getHandle(), component.getX() - left, component.getY() - top, component.getWidth(), component.getHeight());
			}

			public void componentShown(ComponentEvent e) {
				
			}
			
			public String toString() {
				return "CanvasPeerInfoListener";
			}
		};
		
		component.addComponentListener(comp);
	}
	
	private static native ByteBuffer nInitHandle(ByteBuffer surface_buffer, ByteBuffer peer_info_handle, ByteBuffer window_handle, boolean forceCALayer, boolean autoResizable, int x, int y) throws LWJGLException;

	private static native void nSetLayerPosition(ByteBuffer peer_info_handle, int x, int y);
	
	private static native void nSetLayerBounds(ByteBuffer peer_info_handle, int x, int y, int width, int height);
	
	protected void doUnlock() throws LWJGLException {
		awt_surface.unlock();
	}
	
	/**
	 * Return the Insets of the Window holding the Canvas
	 */
	private Insets getWindowInsets(Canvas canvas) {
		Container parent = canvas.getParent();

		while (parent != null) {
			if(parent instanceof Window || parent instanceof java.applet.Applet) {
				return parent.getInsets();
			}
			
			parent = parent.getParent();
		}
		
		// if no parent Window or Applet found, return null
		return null;
	}
	
	private Insets getInsets(Canvas component) {
		Component parent = component.getParent();

		while (parent != null) {
			if (parent instanceof Container) {
				return ((Container)parent).getInsets();
			}
			parent = parent.getParent();
		}
		
		return null;
	}
}
