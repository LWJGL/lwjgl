/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
    
    static {
        try {
            System.loadLibrary(org.lwjgl.Sys.getLibraryName());
        } catch (UnsatisfiedLinkError ule) {
            System.out.println("Failed to load OpenAL library: " + org.lwjgl.Sys.getLibraryName());
            ule.printStackTrace();
        }
    }
    
    /** Creates a new instance of ALC */
    public ALC() {
    }
    /**
     * Returns strings related to the context.
     *
     * @param device ALCdevice to query
     * @param pname Property to get
     * @return String property from device
     */
    public native String        getString(ALCdevice device, int pname);

    /**
     * Returns integers related to the context.
     *
     * @param device ALCdevice to query
     * @param pname Property to get
     * @param size Size of destination buffer provided
     * @param integerdata address of ByteBuffer to write integers to
     */
    public native void          getIntegerv(ALCdevice device, int pname, int size, int integerdata);
    
    /**
     * Opens the named device. If null is specied, the implementation will 
     * provide an implementation specic default.
     *
     * @param devicename name of device to open
     * @return opened device, or null
     */
    public native ALCdevice     openDevice(String devicename);
    
    /**
     * Closes the supplied device.
     *
     * @param device ALCdevice to close
     */
    public native void          closeDevice(ALCdevice device);
    
    /**
     * Creates a context using a specified device.
     *
     * @param device ALCdevice to associate context to
     * @param attrList address of ByteBuffer to read attributes from
     * @return New context, or null if creation failed
     */
    public native ALCcontext    createContext(ALCdevice device, int attrList);
    
    /**
     * Makes the supplied context the current one
     *
     * @param context ALCcontext to make current
     * @return true if successfull, false if not
     */ 
    public native boolean       makeContextCurrent(ALCcontext context);

    /**
     * Tells a context to begin processing.
     *
     * @param context context that should begin processing
     */
    public native void          processContext(ALCcontext context);
    
    /**
     * Gets the current context
     *
     * @return Current ALCcontext
     */
    public native ALCcontext    getCurrentContext();

    /**
     * Retrives the device associated with the supplied context
     *
     * @param context ALCcontext to get device for
     * @param ALCdevice associated with context
     */
    public native ALCdevice     getContextsDevice(ALCcontext context);

    /**
     * Suspends processing on supplied context
     *
     * @param context ALCcontext to suspend
     */
    public native void          suspendContext(ALCcontext context);
    
    /**
     * Destroys supplied context
     *
     * @param context ALCcontext to Destroy
     */
    public native void          destroyContext(ALCcontext context);

    /**
     * Retrieves the current context error state.
     *
     * @param device ALDdevice associated with context
     * @return Errorcode from ALC statemachine
     */
    public native int           getError(ALCdevice device);
    
    /**
     * Query if a specified context extension is available.
     *
     * @param device device to query for extension
     * @param extName name of extension to find
     * @return true if extension is available, false if not
     */
    public native boolean       isExtensionPresent(ALCdevice device, String extName);
    
    /**
     * Retrieves the address of a specified context extension function.
     *
     * @param device device to query
     * @param extName name of extension to find
     * @return address of function
     */
    public native int           getProcAddress(ALCdevice device, String extName);
    
    /**
     * retrieves the enum value for a specified enumeration name.
     * 
     * @param device Device to query
     * @param enumName name of enum to find
     * @return value of enumeration
     */
    public native int           getEnumValue(ALCdevice device, String enumName);    
}