package org.lwjgl.input;

import java.nio.ByteBuffer;

import org.lwjgl.Display;
import org.lwjgl.Sys;

/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * Keyboard Created on Aug 1, 2002 by foo
 */
/**
 * A raw Keyboard interface. This can be used to poll the current state of the
 * keys, or read all the keyboard presses / releases since the last read.
 * Buffering must be explicitly enabled; the size of the buffer is determined
 * by the native implementation at its discretion.
 * 
 * @author foo
 */
public class Keyboard {

	static {
		initialize();
	}
	
	/** Has the keyboard been created? */
	private static boolean created;
	
	/** The keys status from the last poll */
	private static final ByteBuffer keyDownBuffer = ByteBuffer.allocateDirect(256);
	
	/** Address of the keyDown buffer */
	private static final int keyDownAddress = Sys.getDirectBufferAddress(keyDownBuffer);
	
	/**
	 * The key events from the last read: a sequence of pairs of key number,
	 * followed by state.
	 */
	private static ByteBuffer readBuffer;
	
	/** Address of the read buffer */
	private static int readBufferAddress;
	
	/**
	 * Mouse cannot be constructed.
	 */
	private Keyboard() {
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
	 * "Create" the keyboard. The display must first have been created.
	 * @throw Exception if the keyboard could not be created for any reason
	 */
	public static void create() throws Exception {
		if (created)
			return;
		if (!Display.isCreated())
			throw new Exception("The display has not yet been created.");
		if (!nCreate())
			throw new Exception("The keyboard could not be created.");
		created = true;
	}
	
	/**
	 * Native method to create the keyboard
	 * 
	 * @return true if the keyboard was created
	 */
	private static native boolean nCreate();

	/**
	 * "Destroy" the keyboard
	 */
	public static void destroy() {
		if (!created)
			return;
		created = false;
		nDestroy();
	}
	
	/**
	 * Native method the destroy the keyboard
	 */
	private static native void nDestroy();

	/**
	 * Polls the keyboard.
	 */
	public static void poll() {
		assert created : "The keyboard has not been created.";
		nPoll(keyDownAddress);
	}
	
	/**
	 * Native method to poll the keyboard.
	 * 
	 * @param keyDownBufferAddress the address of a 256-byte buffer to place
	 * key states in.
	 */
	private static native void nPoll(int keyDownBufferAddress);
	
	/**
	 * Reads the keyboard
	 */
	public static void read() {
		assert created : "The keyboard has not been created.";
		nRead(readBufferAddress);
	}
	
	/**
	 * Native method to read the keyboard buffer
	 * 
	 * @param readBufferAddress the address of the keyboard buffer
	 */
	private static native void nRead(int readBufferAddress);
	
	/**
	 * Enable keyboard buffering. Must be called after the keyboard is created.
	 * @return the size of the keyboard buffer in events, or 0 if no buffering
	 * can be enabled for any reason
	 */
	public static int enableBuffer() {
		assert created : "The keyboard has not been created.";
		return nEnableBuffer();
	}
	
	/**
	 * Native method to enable the buffer
	 * @return the size of the buffer allocated, in events (1 event is 2 bytes),
	 * or 0 if no buffer can be allocated
	 */
	private static native int nEnableBuffer();

}
