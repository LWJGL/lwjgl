/*
 * Copyright (c) 2002-2004 Light Weight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of
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
 * This is the abstract class for a Window in LWJGL. LWJGL windows have some
 * peculiar characteristics:
 *
 * - width and height are always fixed and cannot be changed
 * - the position of the window may or may not be programmable but once specified
 * cannot be changed programmatically
 * - the window may be closeable by the user or operating system, and may be minimized
 * by the user or operating system
 * - only one window may ever be open at once
 * - the operating system may or may not be able to do fullscreen or windowed windows.
 *
 * @author foo
 */

import org.lwjgl.Display;
import org.lwjgl.Sys;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.LWJGLException;

public final class Window {

	static {
		Sys.initialize();
	}

	/** X coordinate of the window */
	private static int x;

	/**
	 * Y coordinate of the window. Y in window coordinates is from the top of the display down,
	 * unlike GL, where it is typically at the bottom of the display.
	 */
	private static int y;

	/** Width of the window */
	private static int width;

	/** Height of the window */
	private static int height;

	/** Title of the window */
	private static String title;

	/** Fullscreen */
	private static boolean fullscreen;

	/** Tracks VBO state for the window context */
	private static VBOTracker vbo_tracker;

	/** A unique context object, so we can track different contexts between creates() and destroys() */
	private static Window context;

	/** Whether we created the Mouse */
	private static boolean createdMouse;

	/** Whether we created the Keyboard */
	private static boolean createdKeyboard;

	/** Whether we created the Controller */
	private static boolean createdController;

	/**
	 * Only constructed by ourselves
	 */
	private Window() {
	}

	/**
	 * @return the width of the window
	 */
	public static int getWidth() {
		if (!isCreated())
			throw new IllegalStateException("Cannot get width on uncreated window");
		return width;
	}

	/**
	 * @return the height of the window
	 */
	public static int getHeight() {
		if (!isCreated())
			throw new IllegalStateException("Cannot get height on uncreated window");
		return height;
	}

	/**
	 * @return the title of the window
	 */
	public static String getTitle() {
		if (!isCreated())
			throw new IllegalStateException("Cannot get title on uncreated window");
		return title;
	}

	/**
	 * @return whether this window is in fullscreen mode
	 */
	public static boolean isFullscreen() {
		if (!isCreated())
			throw new IllegalStateException("Cannot determine fullscreen state of uncreated window");
		return fullscreen;
	}

	/**
	 * Set the title of the window. This may be ignored by the underlying OS.
	 * @param newTitle The new window title
	 */
	public static void setTitle(String newTitle) {
		if (!isCreated())
			throw new IllegalStateException("Cannot set title on uncreated window");
		title = newTitle;
		nSetTitle(title);
	}

	/**
	 * Native implementation of setTitle(). This will read the window's title member
	 * and stash it in the native title of the window.
	 */
	private static native void nSetTitle(String title);

	/**
	 * @return true if the user or operating system has asked the window to close
	 */
	public static boolean isCloseRequested() {
		if (!isCreated())
			throw new IllegalStateException("Cannot determine close requested state of uncreated window");
		return nIsCloseRequested();
	}

	private static native boolean nIsCloseRequested();

	/**
	 * @return true if the window is minimized or otherwise not visible
	 */
	public static boolean isMinimized() {
		if (!isCreated())
			throw new IllegalStateException("Cannot determine minimized state of uncreated window");
		return nIsMinimized();
	}

	private static native boolean nIsMinimized();

	/**
	 * @return true if window is focused
	 */
	public static boolean isFocused() {
		if (!isCreated())
			throw new IllegalStateException("Cannot determine focused state of uncreated window");
		return nIsFocused();
	}

	private static native boolean nIsFocused();

	/**
	 * Minimize the game and allow the operating system's default display to become
	 * visible. It is the responsibility of LWJGL's native code to restore the display
	 * to its normal display settings.
	 *
	 * If the display is already minimized then this is a no-op.
	 */
	public static native void minimize();

	/**
	 * Restore the game and hide the operating system away. It is the responsibility of
	 * LWJGL's native code to restore the display to its game display settings.
	 *
	 * If the display is not minimized then this is a no-op/
	 */
	public static native void restore();

	/**
	 * Determine if the window's contents have been damaged by external events.
	 * If you are writing a straightforward game rendering loop and simply paint
	 * every frame regardless, you can ignore this flag altogether. If you are
	 * trying to be kind to other processes you can check this flag and only
	 * redraw when it returns true. The flag is cleared when you call paint().
	 *
	 * @return true if the window has been damaged by external changes
	 * and needs to repaint itself
	 */
	public static boolean isDirty() {
		if (!isCreated())
			throw new IllegalStateException("Cannot determine dirty state of uncreated window");
		return nIsDirty();
	}

	private static native boolean nIsDirty();

	/**
	 * Update the window. This processes operating system events, and if the window is visible
	 * clears the dirty flag and swaps the buffers.
	 * @throws OpenGLException if an OpenGL error has occured since the last call to GL11.glGetError()
	 */
	public static void update() {
		if (!isCreated())
			throw new IllegalStateException("Cannot update uncreated window");
		nUpdate();
		if ((isDirty() && !isMinimized()) || (isFocused() && !isMinimized())) {
			Util.checkGLError();
			swapBuffers();
		}
		// Poll the input devices while we're here
		if (Mouse.isCreated()) {
			Mouse.poll();
			if (Mouse.isBuffered()) {
				Mouse.read();
			}
			Mouse.updateCursor();
		}
		if (Keyboard.isCreated()) {
			Keyboard.poll();
			if (Keyboard.isBuffered()) {
				Keyboard.read();
			}
		}
		if (Controller.isCreated()) {
			Controller.poll();
			if (Controller.isBuffered()) {
				Controller.read();
			}
		}
	}

	/**
	 * Swap double buffers.
	 */
	private static native void swapBuffers();

	/**
	 * Make the Window the current rendering context for GL calls.
	 */
	public static synchronized void makeCurrent() {
		if (!isCreated())
			throw new IllegalStateException("No window created to make current");
		nMakeCurrent();
		GLContext.useContext(context);
	}

	/**
	 * Make the window the current rendering context for GL calls.
	 */
	private static native void nMakeCurrent();

	/**
	 * Create a fullscreen window that matches the current display depth. Default common values are chosen
	 * for common OpenGL rendering operations: you will get at least a 16-bit depth buffer, an 8 bit stencil
	 * buffer, probably no alpha buffer, and probably no multisampling.
	 * <p>The window created will be set up in orthographic 2D projection, with 1:1 pixel ratio with GL coordinates.
	 * @param title
	 * @throws LWJGLException
	 */
	public static void create(String title) throws LWJGLException {
		create(title, Display.getDepth(), 0, 16, 8, 0);
	}

	/**
	 * Create a fullscreen window. If the underlying OS does not
	 * support fullscreen mode, then a window will be created instead. If this
	 * fails too then an LWJGLException will be thrown.
	 * <p>The window created will be set up in orthographic 2D projection, with 1:1 pixel ratio with GL coordinates.
	 * @param title The title of the window
	 * @param bpp Minimum bits per pixel
	 * @param alpha Minimum bits per pixel in alpha buffer
	 * @param depth Minimum bits per pixel in depth buffer
	 * @param stencil Minimum bits per pixel in stencil buffer
	 * @throws LWJGLException if the window could not be created for any reason; typically because
	 * the minimum requirements could not be met satisfactorily
	 */
	public static void create(String title, int bpp, int alpha, int depth, int stencil) throws LWJGLException {
		create(title, bpp, alpha, depth, stencil, 0);
	}

	/**
	 * Create a fullscreen window. If the underlying OS does not
	 * support fullscreen mode, then a window will be created instead. If this
	 * fails too then an LWJGLException will be thrown.
	 * <p>The window created will be set up in orthographic 2D projection, with 1:1 pixel ratio with GL coordinates.
	 * @param title The title of the window
	 * @param bpp Minimum bits per pixel
	 * @param alpha Minimum bits per pixel in alpha buffer
	 * @param depth Minimum bits per pixel in depth buffer
	 * @param stencil Minimum bits per pixel in stencil buffer
	 * @param samples Minimum samples in multisample buffer (corresponds to GL_SAMPLES_ARB in GL_ARB_multisample spec).
			  Pass 0 to disable multisampling. This parameter is ignored if GL_ARB_multisample is not supported.
	 * @throws LWJGLException if the window could not be created for any reason; typically because
	 * the minimum requirements could not be met satisfactorily
	 */
	public static void create(String title, int bpp, int alpha, int depth, int stencil, int samples) throws LWJGLException {
		if (isCreated())
			throw new IllegalStateException("Only one LWJGL window may be instantiated at any one time.");
		Window.fullscreen = true;
		Window.x = 0;
		Window.y = 0;
		Window.width = Display.getWidth();
		Window.height = Display.getHeight();
		Window.title = title;
		createWindow(bpp, alpha, depth, stencil, samples);
	}

	/**
	 * Create a window. If the underlying OS does not have "floating" windows, then a fullscreen
	 * display will be created instead. If this fails too then an LWJGLException will be thrown.
	 * If the window is created fullscreen, then its size may not match the specified size
	 * here.
	 * <p>The window created will be set up in orthographic 2D projection, with 1:1 pixel ratio with GL coordinates.
	 * @param title The title of the window
	 * @param x The position of the window on the x axis. May be ignored.
	 * @param y The position of the window on the y axis. May be ignored.
	 * @param width The width of the window's client area
	 * @param height The height of the window's client area
	 * @param bpp Minimum bits per pixel
	 * @param alpha Minimum bits per pixel in alpha buffer
	 * @param depth Minimum bits per pixel in depth buffer
	 * @throws LWJGLException if the window could not be created for any reason; typically because
	 * the minimum requirements could not be met satisfactorily
	 */
	public static void create(String title, int x, int y, int width, int height, int bpp, int alpha, int depth, int stencil) throws LWJGLException {
		create(title, x, y, width, height, bpp, alpha, depth, stencil, 0);
	}

	/**
	 * Create a window. If the underlying OS does not have "floating" windows, then a fullscreen
	 * display will be created instead. If this fails too then an LWJGLException will be thrown.
	 * If the window is created fullscreen, then its size may not match the specified size
	 * here.
	 * <p>The window created will be set up in orthographic 2D projection, with 1:1 pixel ratio with GL coordinates.
	 * @param title The title of the window
	 * @param x The position of the window on the x axis. May be ignored.
	 * @param y The position of the window on the y axis. May be ignored.
	 * @param width The width of the window's client area
	 * @param height The height of the window's client area
	 * @param bpp Minimum bits per pixel
	 * @param alpha Minimum bits per pixel in alpha buffer
	 * @param depth Minimum bits per pixel in depth buffer
	 * @param stencil Minimum bits per pixel in stencil buffer
	 * @param samples Minimum samples in multisample buffer (corresponds to GL_SAMPLES_ARB in GL_ARB_multisample spec).
			  Pass 0 to disable multisampling. This parameter is ignored if GL_ARB_multisample is not supported.
	 * @throws LWJGLException if the window could not be created for any reason; typically because
	 * the minimum requirements could not be met satisfactorily
	 */
	public static void create(String title, int x, int y, int width, int height, int bpp, int alpha, int depth, int stencil, int samples)
		throws LWJGLException {
		if (isCreated())
			throw new IllegalStateException("Only one LWJGL window may be instantiated at any one time.");
		Window.fullscreen = false;
		Window.x = x;
		Window.y = y;
		Window.width = width;
		Window.height = height;
		Window.title = title;
		createWindow(bpp, alpha, depth, stencil, samples);
	}

	/**
	 * Create the native window peer.
	 * @throws LWJGLException
	 */
	private static native void nCreate(
		String title,
		int x,
		int y,
		int width,
		int height,
		boolean fullscreen,
		int bpp,
		int alpha,
		int depth,
		int stencil,
		int samples)
		throws LWJGLException;

	private static void createWindow(int bpp, int alpha, int depth, int stencil, int samples) throws LWJGLException {
		nCreate(title, x, y, width, height, fullscreen, bpp, alpha, depth, stencil, samples);
		context = new Window();
		makeCurrent();

		// Put the window into orthographic projection mode with 1:1 pixel ratio.
		// We haven't used GLU here to do this to avoid an unnecessary dependency.
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0, (double) width, 0.0, (double) height, -1.0, 1.0);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glViewport(0, 0, width, height);

		// Automatically create mouse, keyboard and controller
		if (!Mouse.isCreated()) {
			try {
				Mouse.create();
				createdMouse = true;
				Mouse.enableBuffer();
			} catch (LWJGLException e) {
				if (Sys.DEBUG) {
					e.printStackTrace(System.err);
				} else {
					Sys.log("Failed to create Mouse: "+e);
				}
			}
		}
		if (!Keyboard.isCreated()) {
			try {
				Keyboard.create();
				createdKeyboard = true;
				Keyboard.enableBuffer();
				Keyboard.enableTranslation();
			} catch (LWJGLException e) {
				if (Sys.DEBUG) {
					e.printStackTrace(System.err);
				} else {
					Sys.log("Failed to create Keyboard: "+e);
				}
			}
		}
		if (!Controller.isCreated()) {
			try {
				Controller.create();
				createdController = true;
			} catch (LWJGLException e) {
				if (Sys.DEBUG) {
					e.printStackTrace(System.err);
				} else {
					Sys.log("Failed to create Controller: "+e);
				}
			}
		}
	}

	/**
	 * Destroy the Window. After this call, there will be no current GL rendering context,
	 * regardless of whether the Window was the current rendering context.
	 */
	public static synchronized void destroy() {
		if (context == null) {
			return;
		}

		// Automatically destroy keyboard, mouse, and controller
		if (createdMouse && Mouse.isCreated()) {
			Mouse.destroy();
			createdMouse = false;
		}
		if (createdKeyboard && Keyboard.isCreated()) {
			Keyboard.destroy();
			createdKeyboard = false;
		}
		if (createdController && Controller.isCreated()) {
			Controller.destroy();
			createdController = false;
		}

		makeCurrent();
		nDestroy();
		context = null;
	}

	/**
	 * @return the unique Window context (or null, if the Window has not been created)
	 */
	public static Object getContext() {
		return context;
	}

	/**
	 * Destroy the native window peer.
	 */
	private static native void nDestroy();

	/**
	 * @return true if the window's native peer has been created
	 */
	public static boolean isCreated() {
		return context != null;
	}

	/**
	 * Updates the windows internal state. This must be called at least once per video frame
	 * to handle window close requests, moves, paints, etc.
	 */
	private static native void nUpdate();

	/**
	 * Determines to the best of the platform's ability whether monitor vysnc is enabled on
	 * this window. The failsafe assumption is that when vsync cannot be determined, this
	 * method returns false, and you should rely on using a hires timer to throttle your
	 * framerate rather than relying on monitor sync (even if monitor sync is actually working).
	 * Therefore you can guarantee that if we return true from this method that we're pretty
	 * certain vsync is enabled.
	 * @return boolean
	 */
	public static boolean isVSyncEnabled() {
		if (!isCreated())
			throw new IllegalStateException("Cannot determine vsync state of uncreated window");
		return nIsVSyncEnabled();
	}

	private static native boolean nIsVSyncEnabled();

	/**
	 * Enable or disable vertical monitor synchronization. This call is a best-attempt at changing
	 * the vertical refresh synchronization of the monitor, and is not guaranteed to be successful.
	 * To check whether the call <em>might</em> have been successful, call isVSyncEnabled().
	 * @param sync true to synchronize; false to ignore synchronization
	 */
	public static void setVSyncEnabled(boolean sync) {
		if (!isCreated())
			throw new IllegalStateException("Cannot set vsync state of uncreated window");
		nSetVSyncEnabled(sync);
	}

	private static native void nSetVSyncEnabled(boolean sync);
}
