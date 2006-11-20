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
package org.lwjgl.openal;

import java.util.Vector;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.Sys;

/**
 * <br>
 * This is the OpenAL class. It extends the latest core.
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public final class AL {
	/** ALCdevice instance. */
	protected static ALCdevice device;

	/** Current ALCcontext. */
	protected static ALCcontext context;

	/** Have we been created? */
	private static boolean created;
	
	static {
		Sys.initialize();
	}

	private AL() {
	}

	/**
	 * Native method to create AL instance
	 * 
	 * @param oalPaths Array of strings containing paths to search for OpenAL library
	 */
	private static native void nCreate(String oalPath) throws LWJGLException;
	
	/**
	 * Native method to create AL instance from the Mac OS X 10.4 OpenAL framework.
	 * It is only defined in the Mac OS X native library.
	 * 
	 * @param oalPaths Array of strings containing paths to search for OpenAL library
	 */
	private static native void nCreateDefault() throws LWJGLException;

	/**
	 * Native method the destroy the AL
	 */
	private static native void nDestroy();
	
	/**
	 * @return true if AL has been created
	 */
	public static boolean isCreated() {
		return created;
	}	
	
	/**
	 * Creates an OpenAL instance. Using this constructor will cause OpenAL to
	 * open the device using supplied device argument, and create a context using the context values
	 * supplied. 
	 * 
	 * @param deviceArguments Arguments supplied to native device
	 * @param contextFrequency Frequency for mixing output buffer, in units of Hz (Common values include 11025, 22050, and 44100).
	 * @param contextRefresh Refresh intervalls, in units of Hz.
	 * @param contextSynchronized Flag, indicating a synchronous context.* 
	 */	
	public static void create(String deviceArguments, int contextFrequency, int contextRefresh, boolean contextSynchronized) 
		throws LWJGLException {
		create(deviceArguments, contextFrequency, contextRefresh, contextSynchronized, true);
	}
	
	/**
	 * @param openDevice Whether to automatically open the device
	 * @see #create(String, int, int, boolean)
	 */
	static void create(String deviceArguments, int contextFrequency, int contextRefresh, boolean contextSynchronized, boolean openDevice)
		throws LWJGLException {
			
		if (created)
			throw new IllegalStateException("Only one OpenAL context may be instantiated at any one time.");
		String[] oalPaths = LWJGLUtil.getLibraryPaths(new String[]{
                                                  "OpenAL32", "OpenAL32.dll",
                                                  "openal", "libopenal.so",
                                                  "openal", "openal.dylib"}, AL.class.getClassLoader());
		LWJGLUtil.log("Found " + oalPaths.length + " OpenAL paths");
		for (int i = 0; i < oalPaths.length; i++) {
			try {
				nCreate(oalPaths[i]);
				created = true;
				init(deviceArguments, contextFrequency, contextRefresh, contextSynchronized, openDevice);
				break;
			} catch (LWJGLException e) {
				LWJGLUtil.log("Failed to load " + oalPaths[i] + ": " + e.getMessage());
			}
		}
		if (!created && LWJGLUtil.getPlatform() == LWJGLUtil.PLATFORM_MACOSX) {
			// Try to load OpenAL from the framework instead
			nCreateDefault();
			created = true;
			init(deviceArguments, contextFrequency, contextRefresh, contextSynchronized, openDevice);
		}
		if (!created)
			throw new LWJGLException("Could not locate OpenAL library.");
	}

	private static void init(String deviceArguments, int contextFrequency, int contextRefresh, boolean contextSynchronized, boolean openDevice) throws LWJGLException {
		try {
			AL10.initNativeStubs();
			ALC.initNativeStubs();

			if(openDevice) {
				device = ALC.alcOpenDevice(deviceArguments);
				if (device == null)
					throw new LWJGLException("Could not open ALC device");
	
				if (contextFrequency == -1) {
					context = ALC.alcCreateContext(device.device, null);
				} else {
					context = ALC.alcCreateContext(device.device,
							ALCcontext.createAttributeList(contextFrequency, contextRefresh, 
								contextSynchronized ? ALC.ALC_TRUE : ALC.ALC_FALSE));
				}
				ALC.alcMakeContextCurrent(context.context);
			}
		} catch (LWJGLException e) {
			destroy();
			throw e;
		}
	}

	/**
	 * Creates an OpenAL instance. The empty create will cause OpenAL to
	 * open the default device, and create a context using default values.
	 * This method used to use default values that the OpenAL implementation
	 * chose but this produces unexpected results on some systems; so now
	 * it defaults to 44100Hz mixing @ 60Hz refresh. 
	 */
	public static void create() throws LWJGLException {
		create(null, 44100, 60, false);
	}

	/**
	 * Exit cleanly by calling destroy.
	 */
	public static void destroy() {
		if (context != null) {
			ALC.alcDestroyContext(context.context);
			context = null;
		}
		if (device != null) {
			ALC.alcCloseDevice(device.device);
			device = null;
		}
		resetNativeStubs(AL10.class);
		resetNativeStubs(ALC.class);

		if (created)
			nDestroy();
		created = false;
	}

	private static native void resetNativeStubs(Class clazz);
	
	/**
	 * Gets a handle to the current openAL context. The handle is "opaque" right now
	 * because, realistically, there is nothing you can actually do with it. If it turns
	 * out that there are useful things you can do with it then it'll return an instance
	 * of ALCcontext (which will have to become a public class)
	 * @return an opaque handle to the AL context.
	 */
	public static Object getContext() {
		return context;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String[] getImplementations() throws LWJGLException, OpenALException {
		if(AL.isCreated()) {
			throw new OpenALException("Cannot enumerate devices if AL has already been created");
		}

		Vector availableDevices 	= new Vector();

		try {
			// init
			create(null, 44100, 60, false, false);
			
			// check for extension
			if(!ALC.alcIsExtensionPresent("ALC_ENUMERATION_EXT")) {
				throw new OpenALException("ALC_ENUMERATION_EXT extension not available");
			}
			
			// get list of published devices
			String[] publishedDevices = ALC.ngetImplementations();

			// run through them and verify
			for (int i = 0; i < publishedDevices.length; i++) {
				availableDevices.add(publishedDevices[i]);
			}
		} finally {
			destroy();
		}
		
		String[] available = new String[availableDevices.size()];
		availableDevices.copyInto(available);
		
		return available;
	}
}
