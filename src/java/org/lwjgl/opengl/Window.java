/*
 * Created on 27-Mar-2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
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

import java.util.HashSet;

public final class Window {

	static {
		System.loadLibrary(Sys.getLibraryName());
		/* 
		 * elias: Mac OS X hacks. We need to fetch the toolkit to acquire a Dock icon, a system menu 
		 * and to make windows behave normally. We also need to intercept the quit event from
		 * Swing. Luckily, Swing can be assumed to be present on Mac OS X. Because some of this
		 * is apple extensions to java, we need stub files to successfully compile on other platforms.
		 *
		 * Additionally, because of the way swing works, applications now need to do an
		 * explicit System.exit() to quit. Returning from the main thread is not enough any
		 * more.
		 *
		 * I've wasted a significant amount of time searching for an acceptable solution, without 
		 * finding a way to avoid Swing. AFAIK,
		 *
		 * 1. There's no way to acquire the Dock icon, system menu and normal window behaviour.
		 *    For that, you either need a proper bundled, native application or initialize Swing.
		 * 2. Even if there were a way around it, Swing is automatically started anyway if you
		 *    use Java Web Start.
		 * 3. Swing gains total control over the main event loop, so the native library need to
		 *    work around by maintaining an internal event queue. That's really boring stuff, indeed.
		 *
		 * I have posted a bug report to apple regarding the behaviour.
		 *
		 */
		if (Display.getPlatform() == Display.PLATFORM_AGL) {
			java.awt.Toolkit.getDefaultToolkit();
			new com.apple.eawt.Application().addApplicationListener(new com.apple.eawt.ApplicationAdapter() {
				public void handleQuit(com.apple.eawt.ApplicationEvent e) {
					e.setHandled(false);
					apple_quit = true;
				}
			});
		}
	}

	/** Special quit boolean set from the apple version */
	private static boolean apple_quit;

	/** Whether the window is currently created, ie. has a native peer */
	private static boolean created;

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

	/** Color bits */
	private static int color;

	/** Alpha bits */
	private static int alpha;

	/** Depth bits */
	private static int depth;

	/** Stencil bits */
	private static int stencil;

	/** Fullscreen */
	private static boolean fullscreen;

	/** Tracks VBO state for the window context */
	private static VBOTracker vbo_tracker;

	/**
	 * Construct a Window. Some OSs may not support non-fullscreen windows; in
	 * which case the window will be fullscreen regardless.
	 * 
	 * A fullscreen window MUST track changes to the display settings and change its
	 * width and height accordingly.
	 * 
	 * In this abstract base class, no actual window peer is constructed. This should be
	 * done in specialised derived classes.
	 * 
	 * Only one Window can be created() at a time; to create another Window you must
	 * first destroy() the first window.
	 * 
	 * The dimensions may be ignored if the window cannot be made non-
	 * fullscreen. The position may be ignored in either case.
	 *
	 * @param title The window's title
	 * @param x Position on x axis of top left corner of window.
	 * @param y Position on y axis of top left corner of window.
	 * @param width Width of window
	 * @param height Height of window
	 * @throws RuntimeException if you attempt to create more than one window at the same time
	 */
	private Window() {
	}

	/**
	 * @return the width of the window
	 */
	public static int getWidth() {
    assert isCreated() : "Cannot get width on uncreated window";    
		return width;
	}

	/**
	 * @return the height of the window
	 */
	public static int getHeight() {
    assert isCreated() : "Cannot get height on uncreated window";    
		return height;
	}

	/**
	 * @return the title of the window
	 */
	public static String getTitle() {
    assert isCreated() : "Cannot get title on uncreated window";
		return title;
	}
  
	/**
	 * @return whether this window is in fullscreen mode
	 */
	public static boolean isFullscreen() {
		assert isCreated() : "Cannot determine state of uncreated window";
		return fullscreen;
	}

	/**
	 * Set the title of the window. This may be ignored by the underlying OS.
	 * @param newTitle The new window title
	 */
	public static void setTitle(String newTitle) {
		assert isCreated() : "Cannot set title of uncreated window";
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
		assert isCreated()  : "Cannot determine state of uncreated window";
		boolean result = nIsCloseRequested() || apple_quit;
		apple_quit = false;
		return result;
	}

	private static native boolean nIsCloseRequested();

	/**
	 * @return true if the window is minimized or otherwise not visible
	 */
	public static boolean isMinimized() {
		assert isCreated()  : "Cannot determine state of uncreated window";
		return nIsMinimized();
	}

	private static native boolean nIsMinimized();

	/**
	 * @return true if window is focused
	 */
	public static boolean isFocused() {
		assert isCreated()  : "Cannot determine state of uncreated window";
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
		assert isCreated()  : "Cannot determine state of uncreated window";
		return nIsDirty();
	}

	private static native boolean nIsDirty();

	/**
	 * Paint the window. This clears the dirty flag and swaps the buffers.
	 */
	public static void paint() {
		assert isCreated()  : "Cannot paint uncreated window";
		swapBuffers();
	}

	/**
	 * Swap double buffers.
	 */
	private static native void swapBuffers();

	/**
	 * Create a fullscreen window. If the underlying OS does not
	 * support fullscreen mode, then a window will be created instead. If this
	 * fails too then an Exception will be thrown.
	 * 
	 * @param title The title of the window
	 * @param bpp Minimum bits per pixel
	 * @param alpha Minimum bits per pixel in alpha buffer
	 * @param depth Minimum bits per pixel in depth buffer
	 * @param stencil Minimum bits per pixel in stencil buffer
	 * @throws Exception if the window could not be created for any reason; typically because
	 * the minimum requirements could not be met satisfactorily
	 */
	public static void create(String title, int bpp, int alpha, int depth, int stencil) throws Exception {
		if (isCreated())
			throw new Exception("Only one LWJGL window may be instantiated at any one time.");
		Window.x = 0;
		Window.y = 0;
		Window.color = bpp;
		Window.alpha = alpha;
		Window.depth = depth;
		Window.stencil = stencil;
		Window.fullscreen = true;
		Window.title = title;
		Window.width = Display.getWidth();
		Window.height = Display.getHeight();
		createWindow();
	}

	/**
	 * Create a window. If the underlying OS does not have "floating" windows, then a fullscreen
	 * display will be created instead. If this fails too then an Exception will be thrown.
	 * If the window is created fullscreen, then its size may not match the specified size
	 * here.
	 * 
	 * @param title The title of the window
	 * @param x The position of the window on the x axis. May be ignored.
	 * @param y The position of the window on the y axis. May be ignored.
	 * @param width The width of the window's client area
	 * @param height The height of the window's client area
	 * @param bpp Minimum bits per pixel
	 * @param alpha Minimum bits per pixel in alpha buffer
	 * @param depth Minimum bits per pixel in depth buffer
	 * @param stencil Minimum bits per pixel in stencil buffer
	 * @throws Exception if the window could not be created for any reason; typically because
	 * the minimum requirements could not be met satisfactorily
	 */
	public static void create(String title, int x, int y, int width, int height, int bpp, int alpha, int depth, int stencil)
		throws Exception {
		if (isCreated())
			throw new Exception("Only one LWJGL window may be instantiated at any one time.");
		Window.x = x;
		Window.y = y;
		Window.width = width;
		Window.height = height;
		Window.color = bpp;
		Window.alpha = alpha;
		Window.depth = depth;
		Window.stencil = stencil;
		Window.fullscreen = false;
		Window.title = title;
		createWindow();
	}

	/**
	 * Create the native window peer.
	 * @throws Exception
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
		HashSet extensions)
		throws Exception;

	private static void createWindow() throws Exception {
		apple_quit = false;
		HashSet extensions = new HashSet();
		nCreate(title, x, y, width, height, fullscreen, color, alpha, depth, stencil, extensions);
		GLCaps.determineAvailableExtensions(extensions);
		created = true;
	}

	/**
	 * Destroy the window.
	 */
	public static void destroy() {
		if (!created)
			return;
		nDestroy();
		created = false;
	}

	/**
	 * Destroy the native window peer.
	 */
	private native static void nDestroy();

	/**
	 * @return true if the window's native peer has been created
	 */
	public static boolean isCreated() {
		return created;
	}

	/**
	 * Updates the windows internal state. This must be called at least once per video frame
	 * to handle window close requests, moves, paints, etc.
	 */
	public static native void update();
}
