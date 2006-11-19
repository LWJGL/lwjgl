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

import java.awt.Cursor;
import java.awt.Robot;

/**
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision: 2586 $
 * $Id: LinuxAWTGLCanvasPeerInfo.java 2586 2006-10-20 11:51:34Z elias_naur $
 */
abstract class AbstractAWTInput implements AWTCanvasInputImplementation {
	private AWTGLCanvas canvas;
	private Robot robot;

	private KeyboardEventQueue keyboard_queue;
	private MouseEventQueue mouse_queue;
	private volatile boolean grab;

	protected AbstractAWTInput(AWTGLCanvas canvas) {
		this.canvas = canvas;
		this.robot = AWTUtil.createRobot(canvas);
	}

	protected synchronized MouseEventQueue getMouseEventQueue() {
		return mouse_queue;
	}

	public synchronized void grabMouse(boolean grab) {
		this.grab = grab;
		if (mouse_queue != null)
			mouse_queue.setGrabbed(grab);
	}

	protected boolean isGrabbed() {
		return grab;
	}

	protected final AWTGLCanvas getCanvas() {
		return canvas;
	}

	public final void init() {
		canvas.setInput(this);
	}

	public synchronized void destroy() {
		canvas.setInput(null);
		canvas = null;
		if (mouse_queue != null)
			mouse_queue.unregister();
		if (keyboard_queue != null)
			keyboard_queue.unregister();
	}

	public final int getWidth() {
		return canvas.getWidth();
	}

	public final int getHeight() {
		return canvas.getHeight();
	}

	public boolean hasWheel() {
		return AWTUtil.hasWheel();
	}

	public int getButtonCount() {
		return AWTUtil.getButtonCount();
	}

	public void createMouse() throws LWJGLException {
		mouse_queue = createMouseQueue();
		mouse_queue.register();
	}

	protected MouseEventQueue createMouseQueue() {
		return new MouseEventQueue(getCanvas());
	}

	public void destroyMouse() {
		mouse_queue.unregister();
	}

	public int getNativeCursorCapabilities() {
		return AWTUtil.getNativeCursorCapabilities();
	}

	public void setCursorPosition(int x, int y) {
		AWTUtil.setCursorPosition(canvas, robot, x, y);
	}

	public void setNativeCursor(Object handle) throws LWJGLException {
		canvas.setCursor((Cursor)handle);
	}

	public int getMinCursorSize() {
		return AWTUtil.getMinCursorSize();
	}

	public int getMaxCursorSize() {
		return AWTUtil.getMaxCursorSize();
	}

	public synchronized void createKeyboard() throws LWJGLException {
		keyboard_queue = new KeyboardEventQueue(canvas);
		keyboard_queue.register();
	}

	public synchronized void destroyKeyboard() {
		if (keyboard_queue != null)
			keyboard_queue.unregister();
	}

	public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
		return AWTUtil.createCursor(width, height, xHotspot, yHotspot, numImages, images, delays);
	}

	public void destroyCursor(Object cursor_handle) {
	}

	public synchronized void readMouse(ByteBuffer buffer) {
		mouse_queue.copyEvents(buffer);
	}

	public synchronized void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons) {
		mouse_queue.poll(coord_buffer, buttons);
	}

	public synchronized void readKeyboard(ByteBuffer buffer) {
		keyboard_queue.copyEvents(buffer);
	}

	public synchronized void pollKeyboard(ByteBuffer keyDownBuffer) {
		keyboard_queue.poll(keyDownBuffer);
	}
}
