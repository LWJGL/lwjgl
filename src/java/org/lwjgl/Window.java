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
 * cannot be changed programmatically
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
	
	/** The currently created window */
	private static Window currentWindow;
	
	/** Whether the window is currently created, ie. has a native peer */
	private static boolean created;

	/** The window's native data structure. On Win32 this is an HWND. */
	private static int handle;

	/** Whether the window is currently minimized */
	private static boolean minimized;
  
	/** Whether the window has focus */
	private static boolean focused = true;
	
	/** Whether the window has been asked to close by the user or underlying OS */
	private static boolean closeRequested;
	
	/** Whether the window is dirty, ie. needs painting */
	private static boolean dirty;
	
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
	protected Window(String title, int x, int y, int width, int height) {
		Window.title = title;
		Window.x = x;
		Window.y = y;
		Window.width = width;
		Window.height = height;
		
	}

	/**
	 * @return the width of the window
	 */
	public static int getWidth() {
		return width;
	}
	
	/**
	 * @return the height of the window
	 */
	public static int getHeight() {
		return height;
	}
	
	/**
	 * @return the title of the window
	 */
	public static String getTitle() {
		return title;
	}
	
	/**
	 * Set the title of the window. This may be ignored by the underlying OS.
	 * @param newTitle The new window title
	 */
	public static void setTitle(String newTitle) {
		assert isCreated();
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
		assert isCreated();

		boolean currentValue = closeRequested;
		closeRequested = false;
		return currentValue;
	}
	
	/**
	 * @return true if the window is minimized or otherwise not visible
	 */
	public static boolean isMinimized() {
		assert isCreated();
		return minimized;
	}
  
	/**
	 * @return true if window is focused
	 */
	public static boolean isFocused() {
		assert isCreated();
		return focused;
	}
	
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
		assert isCreated();
		return dirty;
	}
	
	/**
	 * Paint the window. This clears the dirty flag and swaps the buffers.
	 */
	public static void paint() {
		assert isCreated();
		dirty = false;
		currentWindow.doPaint();
	}

	protected abstract void doPaint();
	
	/**
	 * Create the window.
	 */
	public final void create() throws Exception {
		if (currentWindow != null)
			throw new RuntimeException("Only one LWJGL window may be instantiated at any one time.");
		doCreate();
		currentWindow = this;
		created = true;
  	}
	
	/**
	 * Create the window (derived classes).
	 * @throws Exception
	 */
	protected abstract void doCreate() throws Exception;
	
	/**
	 * Destroy the window.
	 */
	public final void destroy() {
		if (!created)
			return;
		doDestroy();
		currentWindow = null;
		created = false;
	}
	
	/**
	 * Destroy the window (derived classes)
	 */
	protected abstract void doDestroy();
	
	
	/**
	 * @return the native window handle
	 */
	public static int getHandle() {
		return handle;
	}
	
	/**
	 * @return true if the window's native peer has been created
	 */
	public static boolean isCreated() {
		return created;
	}
	
	/**
	 * 'Tick' the window. This must be called at least once per video frame
	 * to handle window close requests, moves, paints, etc.
	 */
	public static native void tick();

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Window["+title+"]";
	}

	/**
	 * @return the current window, or null, if there is no current window
	 */
	public static Window getCurrentWindow() {
		return currentWindow;
	}
}
