package org.lwjgl.input;

import org.lwjgl.Display;
import org.lwjgl.Sys;

/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * Joystick.java Created on Aug 14, 2002 by foo
 */
/**
 * A raw Joystick interface. This can be used to poll the current state of the
 * joystick buttons, and determine the joystick position. The joystick position
 * is returned as floats in the range -1.0f to 1.0f.
 * 
 * No buffering is available.
 * 
 * Up to 8 buttons are available. A scrolly wheel or paddle, if present, is the z
 * value. This will be in the range of 0.0f to 1.0f.
 * 
 * @author foo
 */
public class Joystick {

	static {
		initialize();
	}
	
	/** Has the joystick been created? */
	private static boolean created;
	
	/** The joystick buttons status from the last poll */
	private static final boolean[] button = new boolean[8];
	
	/** X position, range -1.0f to 1.0f */
	public static float x;
	
	/** Y position, range -1.0f to 1.0f */
	public static float y;
	
	/** Z position, range 0.0f to 1.0f */
	public static float z;

	/**
	 * Joystick cannot be constructed.
	 */
	private Joystick() {
	}
	
	/**
	 * Static initialization
	 */
	private static void initialize() {
		System.loadLibrary(Sys.LIBRARY_NAME);
		initIDs();
	}
	
	/**
	 * Register fields with the native library
	 */
	private static native void initIDs();
	
	/**
	 * "Create" the joystick. The display must first have been created.
	 * @throw Exception if the joystick could not be created for any reason
	 */
	public static void create() throws Exception {
		if (created)
			return;
		if (!Display.isCreated())
			throw new Exception("The display has not yet been created.");
		if (!nCreate())
			throw new Exception("The joystick could not be created.");
		created = true;
	}
	
	/**
	 * Native method to create the joystick
	 * 
	 * @return true if the joystick was created
	 */
	private static native boolean nCreate();

	/**
	 * "Destroy" the joystick
	 */
	public static void destroy() {
		if (!created)
			return;
		created = false;
		nDestroy();
	}
	
	/**
	 * Native method the destroy the joystick
	 */
	private static native void nDestroy();

	/**
	 * Polls the joystick.
	 */
	public static void poll() {
		assert created : "The joystick has not been created.";
		nPoll();
	}		
	
	/**
	 * Native method to poll the joystick
	 */
	private static native void nPoll();
	
	/**
	 * Queries the number of buttons the joystick has
	 * @return the number of buttons the joystick has
	 */
	public static int getNumButtons() {
		assert created : "The joystick has not been created.";
		return nGetNumButtons();
	}
	
	/**
	 * Native implementation of getNumButtons()
	 */
	private static native int nGetNumButtons();
	
	/**
	 * Queries whether the joystick has a Z value
	 * @return true if the joystick has a Z value
	 */
	public static boolean hasZValue() {
		assert created : "The joystick has not been created.";
		return nHasZValue();
	}
	
	/**
	 * Native implementation of hasZValue()
	 */
	private static native boolean nHasZValue();
}
