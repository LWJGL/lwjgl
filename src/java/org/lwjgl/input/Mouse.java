package org.lwjgl.input;

import org.lwjgl.Display;
import org.lwjgl.Sys;

/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * Mouse.java Created on Aug 1, 2002 by foo
 */
/**
 * A raw Mouse interface. This can be used to poll the current state of the
 * mouse buttons, and determine the mouse movement delta since the last poll.
 * No buffering is available.
 * 
 * Up to 8 buttons are available. The scrolly wheel, if present, is the z
 * value.
 * 
 * @author foo
 */
public class Mouse {

	static {
		initialize();
	}
	
	/** Has the mouse been created? */
	private static boolean created;
	
	/** The mouse buttons status from the last poll */
	private static boolean[] button = new boolean[8];
	
	/** Delta X */
	public static int dx;
	
	/** Delta Y */
	public static int dy;
	
	/** Delta Z */
	public static int dz;

	/**
	 * Mouse cannot be constructed.
	 */
	private Mouse() {
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
	 * "Create" the mouse. The display must first have been created.
	 * @throw Exception if the mouse could not be created for any reason
	 */
	public static void create() throws Exception {
		if (created)
			return;
		if (!Display.isCreated())
			throw new Exception("The display has not yet been created.");
		if (!nCreate())
			throw new Exception("The mouse could not be created.");
		created = true;
	}
	
	/**
	 * Native method to create the mouse
	 * 
	 * @return true if the mouse was created
	 */
	private static native boolean nCreate();

	/**
	 * "Destroy" the mouse
	 */
	public static void destroy() {
		if (!created)
			return;
		created = false;
		nDestroy();
	}
	
	/**
	 * Native method the destroy the mouse
	 */
	private static native void nDestroy();

	/**
	 * Polls the mouse.
	 */
	public static void poll() {
		assert created : "The mouse has not been created.";
		nPoll();
	}		
	
	/**
	 * Native method to poll the mouse
	 */
	private static native void nPoll();
}
