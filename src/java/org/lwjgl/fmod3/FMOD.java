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
package org.lwjgl.fmod3;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.FloatBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;

/**
 * <br>
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class FMOD {

	/** Array of hashmaps for callbacks */
	private static HashMap[] callbacks = new HashMap[17];

	/** FMOD System level clear dsp unit */
	static FSoundDSPUnit fmodClearUnit;

	/** FMOD System level clip and copy dsp unit */
	static FSoundDSPUnit fmodClipAndCopyUnit;

	/** FMOD System level music dsp unit */
	static FSoundDSPUnit fmodMusicUnit;

	/** FMOD System level sound effects dsp unit */
	static FSoundDSPUnit fmodSFXUnit;

	/** FMOD System level FFT dsp unit */
	static FSoundDSPUnit fmodFFTUnit;

	/** FMOD System level FFT buffer */
	static FloatBuffer fmodFFTBuffer;  

	/** Type defining the music callback entries in callback hashmap array */
	public static final int FMUSIC_INSTCALLBACK = 0;

	/** Type defining the music callback entries in callback hashmap array */
	public static final int FMUSIC_ORDERCALLBACK = 1;

	/** Type defining the music callback entries in callback hashmap array */
	public static final int FMUSIC_ROWCALLBACK = 2;

	/** Type defining the music callback entries in callback hashmap array */
	public static final int FMUSIC_ZXXCALLBACK = 3;


	/** Type defining the dsp callback entries in callback hashmap array */
	public static final int FSOUND_DSPCALLBACK = 4;

	/** Type defining the stream callback entries in callback hashmap array */
	public static final int FSOUND_STREAMCALLBACK = 5;

	/** Type defining the alloc callback entries in callback hashmap array */
	public static final int FSOUND_ALLOCCALLBACK = 6;

	/** Type defining the realloc callback entries in callback hashmap array */
	public static final int FSOUND_REALLOCCALLBACK = 7;

	/** Type defining the free callback entries in callback hashmap array */
	public static final int FSOUND_FREECALLBACK = 8;

	/** Type defining the open callback entries in callback hashmap array */
	public static final int FSOUND_OPENCALLBACK = 9;

	/** Type defining the close callback entries in callback hashmap array */
	public static final int FSOUND_CLOSECALLBACK = 10;

	/** Type defining the metadata callback entries in callback hashmap array */
	public static final int FSOUND_METADATACALLBACK = 11;

	/** Type defining the read callback entries in callback hashmap array */
	public static final int FSOUND_READCALLBACK = 12;

	/** Type defining the seek callback entries in callback hashmap array */
	public static final int FSOUND_SEEKCALLBACK = 13;

	/** Type defining the tell callback entries in callback hashmap array */
	public static final int FSOUND_TELLCALLBACK = 14;

	/** Type defining the "end" callback entries in callback hashmap array */
	public static final int FSOUND_ENDCALLBACK = 15;

	/** Type defining the "sync" callback entries in callback hashmap array */
	public static final int FSOUND_SYNCCALLBACK = 16;  

	/** Have we been created? */
	protected static boolean created;  

	/** No errors */
	public static final int FMOD_ERR_NONE             = 0;

	/** Cannot call this command after FSOUND_Init.  Call FSOUND_Close first. */
	public static final int FMOD_ERR_BUSY             = 1;

	/** This command failed because FSOUND_Init was not called */
	public static final int FMOD_ERR_UNINITIALIZED    = 2;

	/** Error initializing output device. */
	public static final int FMOD_ERR_INIT             = 3;

	/** Error initializing output device, but more specifically, the output device is already in use and cannot be reused. */
	public static final int FMOD_ERR_ALLOCATED        = 4;

	/** Playing the sound failed. */
	public static final int FMOD_ERR_PLAY             = 5;

	/** Soundcard does not support the features needed for this soundsystem (16bit stereo output) */
	public static final int FMOD_ERR_OUTPUT_FORMAT    = 6;

	/** Error setting cooperative level for hardware. */
	public static final int FMOD_ERR_COOPERATIVELEVEL = 7;

	/** Error creating hardware sound buffer. */
	public static final int FMOD_ERR_CREATEBUFFER     = 8;

	/** File not found */
	public static final int FMOD_ERR_FILE_NOTFOUND    = 9;

	/** Unknown file format */
	public static final int FMOD_ERR_FILE_FORMAT      = 10;

	/** Error loading file */
	public static final int FMOD_ERR_FILE_BAD         = 11;

	/** Not enough memory */
	public static final int FMOD_ERR_MEMORY           = 12;

	/** The version number of this file format is not supported */
	public static final int FMOD_ERR_VERSION          = 13;

	/** An invalid parameter was passed to this function */
	public static final int FMOD_ERR_INVALID_PARAM    = 14;

	/** Tried to use an EAX command on a non EAX enabled channel or output. */
	public static final int FMOD_ERR_NO_EAX           = 15;

	/** Failed to allocate a new channel */
	public static final int FMOD_ERR_CHANNEL_ALLOC    = 17;

	/** Recording is not supported on this machine */
	public static final int FMOD_ERR_RECORD           = 18;

	/** Required Mediaplayer codec is not installed */
	public static final int FMOD_ERR_MEDIAPLAYER      = 19;

	/** An error occured trying to open the specified CD device */
	public static final int FMOD_ERR_CDDEVICE         = 20;

	/** Whether we have been initialized */
	private static boolean initialized;

	/** The native JNI library name */
	private static String JNI_LIBRARY_NAME = "lwjgl-fmod3";

	/** Version of FMOD */
	private static final String VERSION = "1.0-rc1";
	
	/** Current version of the JNI library */
	static final int JNI_VERSION = 1;	

	static {
		initialize();
	}  

	/**
	 * Initializes the FMOD binding
	 */
	static void initialize() {  
		if (initialized) {
			return;
		}
		initialized = true;

		loadLibrary(JNI_LIBRARY_NAME);

		// check for mismatch
		int nativeVersion = getNativeLibraryVersion();
		if (nativeVersion != JNI_VERSION) {
			throw new LinkageError(
					"Version mismatch: jar version is '" + JNI_VERSION + 
					"', native libary version is '" + nativeVersion + "'");
		}

		// Initialize callback hashmaps
		for(int i=0; i<callbacks.length; i++) {
			callbacks[i] = new HashMap();
		}
	}
	
	private static void loadLibrary(final String lib_name) {
		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				String library_path = System.getProperty("org.lwjgl.librarypath");
				if (library_path != null) {
					System.load(library_path + File.separator + 
						System.mapLibraryName(lib_name));
				} else {
					System.loadLibrary(lib_name);
				}
				return null;
			}
		});
	}
	
	/**
	 * Return the version of the FMOD3-LWJGL binding as a String.
	 */
	public static String getVersion() {
		return VERSION;
	}	

	/**
	 * Return the version of the native library
	 */
	private static native int getNativeLibraryVersion();  

	/**
	 * @return true if AL has been created
	 */
	public static boolean isCreated() {
		return created;
	}   

	/**
	 * Creates an FMOD instance.
	 */
	public static void create() throws FMODException {
		if (created) {
			return;
		}

		try {
			String libname;
			switch (LWJGLUtil.getPlatform()) {
				case LWJGLUtil.PLATFORM_WINDOWS:
					libname = "fmod.dll";
					break;
				case LWJGLUtil.PLATFORM_LINUX:
					libname = "libfmod.so";
					break;
				case LWJGLUtil.PLATFORM_MACOSX:
					libname = "static-ignored";
					break;
				default:
					throw new FMODException("Unknown platform: " + LWJGLUtil.getPlatform());
			}
			String[] fmodPaths = LWJGLUtil.getLibraryPaths("fmod", libname, FMOD.class.getClassLoader());
			LWJGLUtil.log("Found " + fmodPaths.length + " FMOD paths");
			nCreate(fmodPaths);
			created = true;
		} catch (LWJGLException le) {
			throw new FMODException(le.getMessage());
		}
	}

	/**
	 * Native method to create FMOD instance
	 */
	protected static native void nCreate(String[] paths);

	/**
	 * Exit cleanly by calling destroy.
	 */
	public static void destroy() {
		if(!created) {
			return;
		}

		FSound.FSOUND_Close();
		FMusic.FMUSIC_StopAllSongs();
		
		created = false;
		nDestroy();
	}

	/**
	 * Tries to locate FMOD from the JWS Library path
	 * This method exists because FMOD is loaded from native code, and as such
	 * is exempt from JWS library loading rutines. FMOD therefore always fails.
	 * We therefore invoke the protected method of the JWS classloader to see if it can
	 * locate it. 
	 * 
	 * @param libname Name of library to search for
	 * @return Absolute path to library if found, otherwise null
	 */
	private static String getPathFromJWS(String libname) {
		try {
			LWJGLUtil.log("getPathFromJWS: searching for: " + libname);
			Object o = FMOD.class.getClassLoader();
			Class c = o.getClass();
			Method findLibrary =
				c.getMethod("findLibrary", new Class[] { String.class });
			Object[] arguments = new Object[] { libname };
			return (String) findLibrary.invoke(o, arguments);

		} catch (Exception e) {
			LWJGLUtil.log("Failure locating FMOD using classloader:" + e);
		}
		return null;
	}  

	/**
	 * Native method the destroy the FMOD
	 */
	protected static native void nDestroy();  

	/**
	 * Registers a callback by its handle
	 * 
	 * @param handle Handle to native object being monitored
	 * @param callbackHandler Object to register as the call back handler
	 */
	static void registerCallback(int type, long handle, Object handled, Object callbackHandler) {
		Long callbackID = new Long(handle);
		ArrayList callbackList = (ArrayList) callbacks[type].get(callbackID);

		if (callbackList == null ) {
			if (callbackHandler == null) {
				LWJGLUtil.log("No callbackhandlers registered for handle: " + handle);
			} else {
				callbackList = new ArrayList();
				callbacks[type].put(callbackID, callbackList);
			}
		}

		// are we going to add or remove from the list?
		if(callbackHandler == null) {
			callbacks[type].remove(callbackID);
		} else {
			callbackList.add(new FMOD.WrappedCallback(handled, callbackHandler));
		}
	}

	/**
	 * Retrieves a call back handler by its native handle
	 * @param handle Handle to native object being monitored
	 * @return Call back handler, or null if no such handler
	 */
	static ArrayList getCallbacks(int type, long handle) {
		return (ArrayList) callbacks[type].get(new Long(handle));
	}

	/**
	 * Retrieves the errorcode desription for specified code
	 * 
	 * @param errorcode errorcode to lookup
	 * @return Description of errorcode
	 */
	public static native String FMOD_ErrorString(int errorcode);

	/**
	 * Wrapper class for a callback handler, and the object associated 
	 */
	static class WrappedCallback {
		Object handled;
		Object callback;
		WrappedCallback(Object handled, Object callback) {
			this.handled  = handled;
			this.callback = callback;
		}
	}
}
