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
 * This is the abstract class for a Window in LWJGL. LWJGL windows have some
 * peculiar characteristics:
 * 
 * - the window may be closeable by the user or operating system, and may be minimized
 * by the user or operating system
 * - only one window may ever be open at once
 * - the operating system may or may not be able to do fullscreen or windowed windows.
 * 
 * @author foo
 */

import org.lwjgl.Sys;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.LWJGLException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.HashSet;

public final class Display {

	/** The current display mode, if created */
	private static DisplayMode current_mode;

	static {
		Sys.initialize();
		current_mode = init();
		assert current_mode != null;
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				reset();
			}
		});
	}

	/** Timer for sync() */
	private static long timeNow, timeThen;

//	/** X coordinate of the window */
//	private static int x;

	/**
	 * Y coordinate of the window. Y in window coordinates is from the top of the display down,
	 * unlike GL, where it is typically at the bottom of the display.
	 */
//	private static int y;

	/** Title of the window */
	private static String title;

	/** Fullscreen */
	private static boolean fullscreen;

	/** VSync */
	private static boolean vsync;
	
	/** Tracks VBO state for the window context */
	private static VBOTracker vbo_tracker;
	
	/** A unique context object, so we can track different contexts between creates() and destroys() */
	private static Display context;
	
	/**
	 * Only constructed by ourselves
	 */
	private Display() {
	}

	/**
	 * Returns the entire list of display modes as an array, in no
	 * particular order. Any given mode is not guaranteed to be available and
	 * the only certain way to check is to call create() and make sure it works.
	 * Only non-palette-indexed modes are returned (ie. bpp will be 16, 24, or 32).
	 *
	 * @return an array of all display modes the system reckons it can handle.
	 */
	public static DisplayMode[] getAvailableDisplayModes() {
		DisplayMode[] unfilteredModes = nGetAvailableDisplayModes();

		if (unfilteredModes == null) {
			return new DisplayMode[0];
		}

		// We'll use a HashSet to filter out the duplicated modes
		HashSet modes = new HashSet(unfilteredModes.length);

		modes.addAll(Arrays.asList(unfilteredModes));
		DisplayMode[] filteredModes = new DisplayMode[modes.size()];
		modes.toArray(filteredModes);

		Sys.log("Removed " + (unfilteredModes.length - filteredModes.length) + " duplicate displaymodes");

		return filteredModes;
	}

	/**
	 * Native method for getting displaymodes
	 */
	private static native DisplayMode[] nGetAvailableDisplayModes();

	/**
	 * Return the current display mode, as set by setDisplayMode().
	 * @return The current display mode
	 */
	public static DisplayMode getDisplayMode() {
		return current_mode;
	}

	/**
	 * Set the current display mode. If no OpenGL context has been created, the given mode will apply to
	 * the context when create() is called, and no immediate mode switching will happen. If there is a
	 * context already, it will be resized according to the given mode. If the context is also a
	 * fullscreen context, the mode will also be switched immediately. The native cursor position
	 * is also reset.
	 *
	 * @param mode The new display mode to set
	 * @throws LWJGLException if the display mode could not be set
	 */
	public static void setDisplayMode(DisplayMode mode) throws LWJGLException {
		if (mode == null)
			throw new NullPointerException("mode must be non-null");
		current_mode = mode;
		if (isCreated()) {
			destroyWindow();
			if (fullscreen)
				switchDisplayMode(current_mode);
			createWindow();
		}
	}

	/**
	 * Create the native window peer from the given mode and fullscreen flag.
	 * A native context must exist, and it will be attached to the window.
	 */
	private static void createWindow() throws LWJGLException {
		nCreateWindow(current_mode, fullscreen);
		if (title != null)
			nSetTitle(title);
		initControls();
		nSetVSyncEnabled(vsync);
	}

	private static native void nCreateWindow(DisplayMode mode, boolean fullscreen) throws LWJGLException;

	private static void destroyWindow() {
		// Automatically destroy keyboard, mouse, and controller
		if (Mouse.isCreated()) {
			Mouse.destroy();
		}
		if (Keyboard.isCreated()) {
			Keyboard.destroy();
		}
		if (Controller.isCreated()) {
			Controller.destroy();
		}
		nDestroyWindow();
	}

	private static native void nDestroyWindow();

	private static native void switchDisplayMode(DisplayMode mode) throws LWJGLException;

	/**
	 * Reset the display mode to whatever it was when LWJGL was initialized.
	 * Fails silently.
	 */
	private static native void resetDisplayMode();

	/**
	 * Set the display configuration to the specified gamma, brightness and contrast.
	 * The configuration changes will be reset when resetDisplayMode is called.
	 *
	 * @param gamma The gamma value
	 * @param brightness The brightness value between -1.0 and 1.0, inclusive
	 * @param contrast The contrast, larger than 0.0.
	 */
	public static void setDisplayConfiguration(float gamma, float brightness, float contrast) throws LWJGLException {
		if (brightness < -1.0f || brightness > 1.0f)
			throw new IllegalArgumentException("Invalid brightness value");
		if (contrast < 0.0f)
			throw new IllegalArgumentException("Invalid contrast value");
		int rampSize = getGammaRampLength();
		if (rampSize == 0) {
			throw new LWJGLException("Display configuration not supported");
		}
		FloatBuffer gammaRamp = ByteBuffer.allocateDirect(rampSize*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		for (int i = 0; i < rampSize; i++) {
			float intensity = (float)i/(rampSize - 1);
			// apply gamma
			float rampEntry = (float)java.lang.Math.pow(intensity, gamma);
			// apply brightness
			rampEntry += brightness;
			// apply contrast
			rampEntry = (rampEntry - 0.5f)*contrast + 0.5f;
			// Clamp entry to [0, 1]
			if (rampEntry > 1.0f)
				rampEntry = 1.0f;
			else if (rampEntry < 0.0f)
				rampEntry = 0.0f;
			gammaRamp.put(i, rampEntry);
		}
		setGammaRamp(gammaRamp);
		Sys.log("Gamma set, gamma = " + gamma + ", brightness = " + brightness + ", contrast = " + contrast);
	}

	/**
	 * Return the length of the gamma ramp arrays. Returns 0 if gamma settings are
	 * unsupported.
	 *
	 * @return the length of each gamma ramp array, or 0 if gamma settings are unsupported.
	 */
	private static native int getGammaRampLength();

	/**
	 * Native method to set the gamma ramp.
	 */
	private static native void setGammaRamp(FloatBuffer gammaRamp) throws LWJGLException;

	/**
	 * Get the driver adapter string. This is a unique string describing the actual card's hardware, eg. "Geforce2", "PS2",
	 * "Radeon9700". If the adapter cannot be determined, this function returns null.
	 * @return a String
	 */
	public static native String getAdapter();

	/**
	 * Get the driver version. This is a vendor/adapter specific version string. If the version cannot be determined,
	 * this function returns null.
	 * @return a String
	 */
	public static native String getVersion();
	
	/**
	 * Synchronize the display to a capped frame rate.
	 * @param fps The desired frame rate, in frames per second
	 */
	public static void sync(int fps) {
		float frameTime = 1.0f / (float) fps;
		timeNow = Sys.getTime();
		while (timeNow > timeThen && (float) (timeNow - timeThen) / (float) Sys.getTimerResolution() < frameTime) {
			// This is a system-friendly way of allowing other stuff to use CPU if it wants to
			Thread.yield();
			timeNow = Sys.getTime();
		}
		timeThen = timeNow;
	}

	/**
	 * Initialize and return the current display mode.
	 */
	private static native DisplayMode init();

	/**
	 * @return the X coordinate of the window (always 0 for fullscreen)
	 */
/*	public static int getX() {
		if (!isCreated())
			throw new IllegalStateException("Cannot get X on uncreated window");
		return x;
	}
*/
	/**
	 * @return the Y coordinate of the window (always 0 for fullscreen)
	 */
/*	public static int getY() {
		if (!isCreated())
			throw new IllegalStateException("Cannot get Y on uncreated window");
		return y;
	}
*/
	
	/**
	 * @return the title of the window
	 */
	public static String getTitle() {
		if (!isCreated())
			throw new IllegalStateException("Cannot get title on uncreated window");
		return title;
	}

	/**
	 * Set the fullscreen mode of the context. If no context has been created through create(),
	 * the mode will apply when create() is called. If fullscreen is true, the context will become
	 * a fullscreen context and the display mode is switched to the mode given by getDisplayMode(). If
	 * fullscreen is false, the context will become a windowed context with the dimensions given in the
	 * mode returned by getDisplayMode(). The native cursor position is also reset.
	 *
	 * @param fullscreen Specify the fullscreen mode of the context.
	 */
	public static void setFullscreen(boolean fullscreen) throws LWJGLException {
		if (Display.fullscreen != fullscreen) {
			Display.fullscreen = fullscreen;
			if (!isCreated())
				return;
			destroyWindow();
			if (fullscreen) {
				try {
					switchDisplayMode(current_mode);
				} catch (LWJGLException e) {
					destroyContext();
					throw e;
				}
			} else {
				resetDisplayMode();
			}
			try {
				createWindow();
			} catch (LWJGLException e) {
				destroyContext();
				if (fullscreen)
					resetDisplayMode();
				throw e;
			}
		}
	}

	/**
	 * @return whether the Display is in fullscreen mode
	 */
	public static boolean isFullscreen() {
		return fullscreen;
	}

	/**
	 * Set the title of the window. This may be ignored by the underlying OS.
	 * @param newTitle The new window title
	 */
	public static void setTitle(String newTitle) {
		title = newTitle;
		if (isCreated())
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
	 * @return true if the window is visible, false if not
	 */
	public static boolean isVisible() {
		if (!isCreated())
			throw new IllegalStateException("Cannot determine minimized state of uncreated window");
		return nIsVisible();
	}

	private static native boolean nIsVisible();

	/**
	 * @return true if window is active, that is, the foreground display of the operating system.
	 */
	public static boolean isActive() {
		if (!isCreated())
			throw new IllegalStateException("Cannot determine focused state of uncreated window");
		return nIsActive();
	}

	private static native boolean nIsActive();

	/**
	 * Determine if the window's contents have been damaged by external events.
	 * If you are writing a straightforward game rendering loop and simply paint
	 * every frame regardless, you can ignore this flag altogether. If you are
	 * trying to be kind to other processes you can check this flag and only
	 * redraw when it returns true. The flag is cleared when update() or isDirty() is called.
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
		
		// We paint only when the window is visible or dirty
		if (isVisible() || isDirty()) {
			Util.checkGLError();
			swapBuffers();
		}
		
		nUpdate();

		// Poll the input devices while we're here
		if (Mouse.isCreated()) {
			Mouse.poll();
			Mouse.updateCursor();
		}
		if (Keyboard.isCreated()) {
			Keyboard.poll();
		}
		if (Controller.isCreated()) {
			Controller.poll();
		}
	}

	/**
	 * Swap double buffers.
	 */
	private static native void swapBuffers();
	
	/**
	 * Make the Display the current rendering context for GL calls. Also initialize native stubs.
	 * @throws LWJGLException If the context could not be made current
	 */
	public static synchronized void makeCurrent() throws LWJGLException {
		if (!isCreated())
			throw new IllegalStateException("No window created to make current");
		nMakeCurrent();
		GLContext.useContext(context);
	}
	
	/**
	 * Make the window the current rendering context for GL calls.
	 */
	private static native void nMakeCurrent() throws LWJGLException;

	/**
	 * Create the OpenGL context. If isFullscreen() is true or if windowed
	 * context are not supported on the platform, the display mode will be switched to the mode returned by
	 * getDisplayMode(), and a fullscreen context will be created. If isFullscreen() is false, a windowed context
	 * will be created with the dimensions given in the mode returned by getDisplayMode(). If a context can't be
	 * created with the given parameters, a LWJGLException will be thrown.
	 *
	 * <p>The window created will be set up in orthographic 2D projection, with 1:1 pixel ratio with GL coordinates.
	 *
	 * @throws LWJGLException
	 */
	public static void create() throws LWJGLException {
		create(new PixelFormat());
	}

	/**
	 * Create the OpenGL context with the given minimum parameters. If isFullscreen() is true or if windowed
	 * context are not supported on the platform, the display mode will be switched to the mode returned by
	 * getDisplayMode(), and a fullscreen context will be created. If isFullscreen() is false, a windowed context
	 * will be created with the dimensions given in the mode returned by getDisplayMode(). If a context can't be
	 * created with the given parameters, a LWJGLException will be thrown.
	 *
	 * <p>The window created will be set up in orthographic 2D projection, with 1:1 pixel ratio with GL coordinates.
	 *
	 * @param pixel_format Describes the minimum specifications the context must fulfill.
	 * @throws LWJGLException
	 */
	public static void create(PixelFormat pixel_format) throws LWJGLException {
		if (isCreated())
			throw new IllegalStateException("Only one LWJGL context may be instantiated at any one time.");
		if (fullscreen)
			switchDisplayMode(current_mode);
		try {
			GLContext.loadOpenGLLibrary();
			try {
				createContext(pixel_format);
				try {
					context = new Display();
					createWindow();
					makeCurrent();
					initContext();
				} catch (LWJGLException e) {
					destroyContext();
					context = null;
					throw e;
				}
			} catch (LWJGLException e) {
				GLContext.unloadOpenGLLibrary();
				throw e;
			}
		} catch (LWJGLException e) {
			if (fullscreen)
				resetDisplayMode();
			throw e;
		}
	}

	/**
	 * Create the native OpenGL context.
	 * @throws LWJGLException
	 */
	private static native void createContext(PixelFormat pixel_format) throws LWJGLException;

	private static native void destroyContext();

	private static void initContext() {
		// Put the window into orthographic projection mode with 1:1 pixel ratio.
		// We haven't used GLU here to do this to avoid an unnecessary dependency.
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0, (double) current_mode.getWidth(), 0.0, (double) current_mode.getHeight(), -1.0, 1.0);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glViewport(0, 0, current_mode.getWidth(), current_mode.getHeight());
	}

	private static void initControls() {
		// Automatically create mouse, keyboard and controller
		if (!Boolean.getBoolean("org.lwjgl.opengl.Display.noinput")) {
			if (!Mouse.isCreated() && !Boolean.getBoolean("org.lwjgl.opengl.Display.nomouse")) {
				try {
					Mouse.create();
					Mouse.enableBuffer();
				} catch (LWJGLException e) {
					if (Sys.DEBUG) {
						e.printStackTrace(System.err);
					} else {
						Sys.log("Failed to create Mouse: "+e);
					}
				}
			}
			if (!Keyboard.isCreated() && !Boolean.getBoolean("org.lwjgl.opengl.Display.nokeyboard")) {
				try {
					Keyboard.create();
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
			if (!Controller.isCreated() && !Boolean.getBoolean("org.lwjgl.opengl.Display.nocontroller")) {
				try {
					Controller.create();
				} catch (LWJGLException e) {
					if (Sys.DEBUG) {
						e.printStackTrace(System.err);
					} else {
						Sys.log("Failed to create Controller: "+e);
					}
				}
			}
		}
	}

	/**
	 * Destroy the Display. After this call, there will be no current GL rendering context,
	 * regardless of whether the Display was the current rendering context.
	 */
	public static synchronized void destroy() {
		if (!isCreated()) {
			return;
		}
		
		destroyWindow();
		destroyContext();
		GLContext.unloadOpenGLLibrary();
		context = null;
		try {
			GLContext.useContext(null);
		} catch (LWJGLException e) {
			// ignore exception
		}
		reset();
	}

	/*
	 * Reset display mode if fullscreen. This method is also called from the shutdown hook added in Sys
	 */
	private static void reset() {
		if (fullscreen)
			resetDisplayMode();
	}
	
	/**
	 * @return the unique DIsplay context (or null, if the Display has not been created)
	 */
	public static Object getContext() {
		return context;
	}

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
	 * Enable or disable vertical monitor synchronization. This call is a best-attempt at changing
	 * the vertical refresh synchronization of the monitor, and is not guaranteed to be successful.
	 * @param sync true to synchronize; false to ignore synchronization
	 */
	public static void setVSyncEnabled(boolean sync) {
		vsync = sync;
		if (isCreated())
			nSetVSyncEnabled(vsync);
	}

	private static native void nSetVSyncEnabled(boolean sync);

//	/**
//	 * Set the window's location. This is a no-op on fullscreen windows.
//	 * The window is clamped to remain entirely on the screen. If you attempt
//	 * to position the window such that it would extend off the screen, the window
//	 * is simply placed as close to the edge as possible.
//	 * @param x, y The new window location
//	 */
//	public static void setLocation(int x, int y) {
//		if (!isCreated())
//			throw new IllegalStateException("Cannot move uncreated window");
//		if (fullscreen) {
//			return;
//		}
//		Display.x = Math.max(0, Math.min(Display.getWidth() - Display.width, x));
//		Display.y = Math.max(0, Math.min(Display.getHeight() - Display.height, y));
//		nReshape(Display.x, Display.y, Display.width, Display.height);
//	}
//
//	
//	/**
//	 * Native method to reshape the window
//	 * @param x, y The new window location
//	 * @param width, height The new window dimensions
//	 */
//	private static native void nReshape(int x, int y, int width, int height);

}
