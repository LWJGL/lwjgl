/*
 * Created on 27-Mar-2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.lwjgl;

/**
 * This is the abstract base class for a Window in LWJGL. LWJGL windows have some
 * peculiar characteristics:
 * 
 * - width and height are always fixed and cannot be changed
 * - the position of the window may or may not be programmable but once specified
 * cannot be changed
 * - the window may be closeable by the user or operating system, and may be minimized
 * by the user or operating system
 * - only one window may ever be open at once
 * - the operating system may or may not be able to do fullscreen or windowed windows.
 * 
 * @author foo
 */
public abstract class Window {

	static {
		System.loadLibrary(Sys.getLibraryName());
	}
	
	/** Whether we have a window already */
	private static boolean created;

	/** The window's native data structure. On Win32 this is an HWND. */
	private int handle;

	/** Whether the window is currently minimized */
	private boolean minimized;
	
	/** Whether the window has been asked to close by the user or underlying OS */
	private boolean closeRequested;
	
	/** Whether the window is dirty, ie. needs painting */
	private boolean dirty;
	
	/** X coordinate of the window */
	private int x;
	
	/**
	 * Y coordinate of the window. Y in window coordinates is from the top of the display down,
	 * unlike GL, where it is typically at the bottom of the display.
	 */
	private int y;
	
	/** Width of the window */
	private final int width;
	
	/** Height of the window */
	private final int height;
	
	/** Title of the window */
	private String title;
	
	/**
	 * Construct a Window. Some OSs may not support non-fullscreen windows; in
	 * which case the window will be fullscreen regardless.
	 * 
	 * In this abstract base class, no actual window peer is constructed. This should be
	 * done in specialised derived classes.
	 * 
	 * Only one Window can be constructed at a time; to create another Window you must
	 * first destroy() the first window.
	 *
	 * @param title The window's title
	 * @param x, y, width, height The position and dimensions of the client area of
	 * the window. The dimensions may be ignored if the window cannot be made non-
	 * fullscreen. The position may be ignored in either case.
	 * @throws RuntimeException if you attempt to create more than one window at the same time
	 */
	protected Window(String title, int x, int y, int width, int height) {
		if (created)
			throw new RuntimeException("Only one LWJGL window may be instantiated at any one time.");
		this.title = title;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		created = true;
	}

	/**
	 * @return the width of the window
	 */
	public final int getWidth() {
		return width;
	}
	
	/**
	 * @return the height of the window
	 */
	public final int getHeight() {
		return height;
	}
	
	/**
	 * @return the title of the window
	 */
	public final String getTitle() {
		return title;
	}
	
	/**
	 * Set the title of the window. This may be ignored by the underlying OS.
	 * @param newTitle The new window title
	 */
	public final void setTitle(String newTitle) {
		title = newTitle;
		nSetTitle();
	}
	
	/**
	 * Native implementation of setTitle(). This will read the window's title member
	 * and stash it in the native title of the window.
	 */
	private native void nSetTitle();

	/**
	 * @return true if the user or operating system has asked the window to close
	 */
	public final boolean isCloseRequested() {
		return closeRequested;
	}
	
	/**
	 * @return true if the window is minimized or otherwise not visible
	 */
	public final boolean isMinimized() {
		return minimized;
	}
	
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
	public final boolean isDirty() {
		return dirty;
	}
	
	/**
	 * Paint the window. This clears the dirty flag and swaps the buffers.
	 */
	public final void paint() {
		dirty = false;
		swapBuffers();
	}
	
	/**
	 * Swap the buffers.
	 */
	private native void swapBuffers();
	
	/**
	 * Destroy the window.
	 */
	public final void destroy() {
		created = false;
		nDestroy();
	}
	
	/**
	 * Natively destroy the window
	 */
	private native void nDestroy();
	
	/**
	 * @return the native window handle
	 */
	public final int getHandle() {
		return handle;
	}
	
	/**
	 * 'Tick' the window. This must be called at least once per video frame
	 * to handle window close requests, moves, paints, etc.
	 */
	public final native void tick();

}
