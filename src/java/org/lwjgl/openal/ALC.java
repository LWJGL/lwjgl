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
 * * Neither the name of 'Lightweight Java Game Library' nor the names of
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
package org.lwjgl.openal;

/**
 * $Id$
 *
 * This is the context class for OpenAL. This class implements functions
 * in alc.h
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class ALC {
	/** Has the ALC object been created? */
	protected static boolean created;
  
  /** Parent AL instance */
  protected AL al = null; 

	/** Bad value */
	public static final int INVALID = -1;

	/** Boolean False */
	public static final int FALSE = 0;

	/** Boolean True */
	public static final int TRUE = 1;

	/** Errors: No Error */
	public static final int NO_ERROR = FALSE;

	public static final int MAJOR_VERSION = 0x1000;
	public static final int MINOR_VERSION = 0x1001;
	public static final int ATTRIBUTES_SIZE = 0x1002;
	public static final int ALL_ATTRIBUTES = 0x1003;

	public static final int DEFAULT_DEVICE_SPECIFIER = 0x1004;
	public static final int DEVICE_SPECIFIER = 0x1005;
	public static final int EXTENSIONS = 0x1006;

	public static final int FREQUENCY = 0x1007;
	public static final int REFRESH = 0x1008;
	public static final int SYNC = 0x1009;

	/** The device argument does not name a valid device */
	public static final int INVALID_DEVICE = 0xA001;

	/** The context argument does not name a valid context */
	public static final int INVALID_CONTEXT = 0xA002;

	/**
	 * A function was called at inappropriate time, or in an inappropriate way, 
	 * causing an illegal state. This can be an incompatible ALenum, object ID,
	 * and/or function.
	 */
	public static final int INVALID_ENUM = 0xA003;

	/**
	 * Illegal value passed as an argument to an AL call.
	 * Applies to parameter values, but not to enumerations.
	 */
	public static final int INVALID_VALUE = 0xA004;

	/**
	 * A function could not be completed, because there is not enough 
	 * memory available.
	 */
	public static final int OUT_OF_MEMORY = 0xA005;

	static {
		initialize();
	}

	/** Creates a new instance of ALC */
  protected ALC(AL al) {
    this.al = al;
  }

	/**
	* Override to provide any initialization code after creation.
	*/
	protected void init() {
	}

	/**
	 * Static initialization
	 */
	private static void initialize() {
		System.loadLibrary(org.lwjgl.Sys.getLibraryName());
	}

	/**
	* Creates the ALC instance
	* 
	* @throws Exception if a failiure occured in the ALC creation process
	*/
  protected void create() throws OpenALException {
		if (created) {
			return;
		}

		if (!nCreate()) {
			throw new OpenALException("ALC instance could not be created.");
		}
		init();
    created = true;
	}

	/**
	 * Native method to create ALC instance
	 * 
	 * @return true if the ALC creation process succeeded
	 */
	protected native boolean nCreate();

	/**
	 * Calls whatever destruction rutines that are needed
	 */
  protected void destroy() {
		if (!created) {
			return;
		}
		created = false;
		nDestroy();
	}

	/**
	 * Native method the destroy the ALC
	 */
	protected native void nDestroy();

  /**
   * Returns strings related to the context.
   *
   * @param pname Property to get
   * @return String property from device
   */
	public String getString(int pname) {
    return nGetString(al.device.device, pname);
	}
  
  native String nGetString(int device, int pname);

	/**
	 * Returns integers related to the context.
	 *
	 * @param pname Property to get
	 * @param size Size of destination buffer provided
	 * @param integerdata address of ByteBuffer to write integers to
	 */
	public void getIntegerv(int pname, int size, int integerdata) {
      nGetIntegerv(al.device.device, pname, size, integerdata);
		}
    
  native void nGetIntegerv(int device, int pname, int size, int integerdata);    

	/**
	 * Opens the named device. If null is specied, the implementation will
	 * provide an implementation specic default.
	 *
	 * @param devicename name of device to open
	 * @return opened device, or null
	 */
	native ALCdevice openDevice(String devicename);

	/**
	 * Closes the supplied device.
	 *
	 * @param device address of native device to close
	 */
	native void closeDevice(int device);

	/**
	 * Creates a context using a specified device.
	 *
	 * @param device address of device to associate context to
	 * @param attrList address of ByteBuffer to read attributes from
	 * @return New context, or null if creation failed
	 */
	native ALCcontext createContext(int device, int attrList);

	/**
	 * Makes the supplied context the current one
	 *
	 * @param context address of context to make current
	 * @return true if successfull, false if not
	 */
	native boolean makeContextCurrent(int context);

  /**
   * Tells a context to begin processing.
   */
  public void processContext() {
    nProcessContext(al.context.context);
  }
  
  native void nProcessContext(int context);

	/**
	 * Gets the current context
	 *
	 * @return Current ALCcontext
	 */
	native ALCcontext getCurrentContext();

	/**
	 * Retrives the device associated with the supplied context
	 *
	 * @param context address of context to get device for
	 * @param ALCdevice associated with context
	 */
	native ALCdevice getContextsDevice(int context);

	/**
	 * Suspends processing on supplied context
	 *
	 * @param context address of context to suspend
	 */
	native void suspendContext(int context);

	/**
	 * Destroys supplied context
	 *
	 * @param context address of context to Destroy
	 */
	native void destroyContext(int context);

	/**
	 * Retrieves the current context error state.
	 *
	 * @return Errorcode from ALC statemachine
	 */
  public int getError() {
    return nGetError(al.device.device);
  }
  
	native int nGetError(int device);

	/**
	 * Query if a specified context extension is available.
	 *
	 * @param extName name of extension to find
	 * @return true if extension is available, false if not
	 */
  public boolean isExtensionPresent(String extName) {
    return nIsExtensionPresent(al.device.device, extName);
  }
  
	native boolean nIsExtensionPresent(int device, String extName);

	/**
	 * retrieves the enum value for a specified enumeration name.
	 *
	 * @param enumName name of enum to find
	 * @return value of enumeration
	 */
  public int getEnumValue(String enumName) {
    return nGetEnumValue(al.device.device, enumName);
  }
  
	native int nGetEnumValue(int device, String enumName);
}