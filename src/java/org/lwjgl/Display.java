/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * Display.java Created on Aug 1, 2002 by foo
 */
package org.lwjgl;

/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * Display.java Created on Aug 1, 2002 by foo
 */
/**
 * Encapsulates everything you need for game display.
 * It must be created before any input devices are created.
 * The game display has NO mouse cursor or any other window decorations.
 * 
 * @author foo
 */
public final class Display {

	static {
		System.loadLibrary(Sys.LIBRARY_NAME);
	}

	/** Has the display been created? */
	private static boolean created;

	/** The current display mode, if created */
	private static DisplayMode mode;
	
	/** A pointer to the native display window */
	private static int handle;

	/**
	 * No construction allowed.
	 */
	private Display() {
		super();
	}

	/**
	 * Returns the entire list of display modes as an array, in no
	 * particular order. Any given mode is not guaranteed to be available and
	 * the only certain way to check is to call create() and make sure it works.
	 * Only non-palette-indexed modes are returned (ie. bpp will be 16, 24, or 32).
	 * 
	 * @return an array of all display modes the system reckons it can handle.
	 */
	public static native DisplayMode[] getAvailableDisplayModes();

	/**
	 * Create a display with the specified display mode. If the display is
	 * already created then no action is taken - the display must first be
	 * destroyed.
	 * 
	 * @param displayMode a display mode to choose
	 * @param fullscreen whether to create the display fullscreen
	 * @throws Exception if the display mode could not be set
	 * @see destroy()
	 */
	public static void setDisplayMode(
		DisplayMode displayMode,
		boolean fullscreen)
		throws Exception {

		if (created)
			return;

		if (!nCreate(displayMode.width,
			displayMode.height,
			displayMode.bpp,
			displayMode.freq,
			fullscreen))
			throw new Exception("Failed to set display mode to " + displayMode);

		created = true;
		mode = displayMode;
	}

	/**
	 * Native method to create the display. This will set the handle if it is
	 * successful.
	 * @return true if the display was successfully created
	 * @see create()
	 */
	private static native boolean nCreate(
		int width,
		int height,
		int bpp,
		int freq,
		boolean fullscreen);

	/**
	 * Destroy the display and return it to normal. If the display has not yet
	 * been created no action is taken.
	 */
	public static void destroy() {
		if (!created)
			return;
		nDestroy();
		created = false;
		mode = null;
	}
	
	/**
	 * Native method to destroy the display. This will reset the handle.
	 */
	private static native void nDestroy();

	/**
	 * @return the current display width.
	 * @throws AssertionError if the display has not been created yet.
	 */
	public static int getWidth() {
		assert created : "The display has not been created yet.";
		return mode.width;
	}

	/**
	 * @return the current display height.
	 * @throws AssertionError if the display has not been created yet.
	 */
	public static int getHeight() {
		assert created : "The display has not been created yet.";
		return mode.height;
	}

	/**
	 * @return the current display depth.
	 * @throws AssertionError if the display has not been created yet.
	 */
	public static int getDepth() {
		assert created : "The display has not been created yet.";
		return mode.bpp;
	}

	/**
	 * @return the current display frequency.
	 * @throws AssertionError if the display has not been created yet.
	 */
	public static int getFrequency() {
		assert created : "The display has not been created yet.";
		return mode.freq;
	}
	
	/**
	 * @return the current display mode, or null if the display is not yet created
	 * @throws AssertionError if the display has not been created yet.
	 */
	public static DisplayMode getDisplayMode() {
		assert created : "The display has not been created yet.";
		return mode;
	}
	
	/**
	 * @return the native handle
	 * @throws AssertionError if the display has not been created yet.
	 */
	public static int getHandle() {
		assert created : "The display has not been created yet.";
		return handle;
	}
	
	/**
	 * @return true if the display has been created
	 */
	public static boolean isCreated() {
		return created;
	}

}
