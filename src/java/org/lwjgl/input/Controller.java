/* 
 * Copyright (c) 2002 Lightweight Java Game Library Project
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

package org.lwjgl.input;

import org.lwjgl.Sys;

/**
 * $Id$
 * <br>
 * A raw Controller interface. This can be used to poll the current state of a
 * controllers buttons, and axis positions. The axis positions
 * are returned as ints in the range -1000 to 1000.
 * 
 * No buffering is available.
 *
 * Currently n (native limits, currently 128 - might change) buttons, the x, y,
 * z axis is supported along with a POV (or HAT) and a slider, where the z axis
 * represents a throttle. In the future the controller may support more buttons
 * and axises and other features. but this is a platform issue.
 *
 * The Controller implementation currently only supports the first attached device.
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class Controller {

	static {
		initialize();
	}

	/** Has the controller been created? */
	private static boolean created;

	/** The controller buttons status */
	private static boolean[] buttons;

	/** X position, range -1000 to 1000 */
	public static int x = 0;
  
  /** X rotational position, range -1000 to 1000 */
  public static int rx = 0;
  
	/** Y position, range -1000 to 1000 */
	public static int y = 0;
  
  /** Y rotational position, range -1000 to 1000 */
  public static int ry = 0;
  
	/** Z position, range -1000 to 1000 */
	public static int z = 0;

  /** Z rotational position, range -1000 to 1000 */
  public static int rz = 0;

	/** Position of Point of View from -1 to 27000 (360 degrees) */
	public static int pov;
  
  /** Slider position, range -1000 to 1000 */
  public static int slider = 0;  

	/** Constant specifying centered POV */
	public static final int POV_CENTER = -1;

	/** Constant specifying nortward POV */
	public static final int POV_NORTH = 0;

	/** Constant specifying southward POV */
	public static final int POV_SOUTH = 18000;

	/** Constant specifying eastward POV */
	public static final int POV_EAST = 27000;

	/** Constant specifying westward POV */
	public static final int POV_WEST = 9000;

	/* Controller capabilities */
	public static int buttonCount = -1;
  public static boolean hasXAxis = false;
  public static boolean hasRXAxis = false;
  public static boolean hasYAxis = false;
  public static boolean hasRYAxis = false;
  public static boolean hasZAxis = false;
  public static boolean hasRZAxis = false;
	public static boolean hasPOV = false;
  public static boolean hasSlider = false;

	/**
	 * Controller cannot be constructed.
	 */
	private Controller() {
	}

	/**
	 * Static initialization
	 */
	private static void initialize() {
		System.loadLibrary(Sys.getLibraryName());
		initIDs();
	}

	/**
	 * "Create" the controller. The display must first have been created.
	 * @throws Exception if the controller could not be created for any reason
	 */
	public static void create() throws Exception {
		if (created) {
			return;
		}

		if (!nCreate()) {
			throw new Exception("The controller could not be created.");
		}
		created = true;
	}

	/**
	 * "Destroy" the controller
	 */
	public static void destroy() {
		if (!created) {
			return;
		}

		created = false;
		nDestroy();
	}

	/**
	 * Polls the controller.
	 */
	public static void poll() {
		assert created : "The controller has not been created.";
		nPoll();
	}

	/**
	 * See if a particular button is down.
	 * 
	 * @param button The index of the button you wish to test (0..buttonCount)
	 * @return true if the specified button is down
	 * @see #buttonCount
	 */
	public static boolean isButtonDown(int button) {
		assert created : "The controller has not been created.";
		return buttons[button];
	}

	/**
	 * Native method to poll the controller
	 */
	private static native void nPoll();

	/**
	 * Native method to create the controller
	 * 
	 * @return true if the controller was created
	 */
	private static native boolean nCreate();

	/**
	* Native method the destroy the controller
	*/
	private static native void nDestroy();

	/**
	 * Register fields with the native library
	 */
	private static native void initIDs();
}