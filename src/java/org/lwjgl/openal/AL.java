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

import org.lwjgl.Sys;

/**
 * $Id$
 *
 * This is the OpenAL class. It extends the latest core.
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class AL extends CoreAL {
  
  /** ALC instance. */
  protected ALC alc;

  /** ALCdevice instance. */
  protected ALCdevice device;

  /** Current ALCcontext. */
  protected ALCcontext context;
  
  /** 
   * String that requests a certain device or device configuration. 
   * If null is specified, the implementation will provide an 
   * implementation specific default. */
  protected String deviceArguments;
  
  /** Frequency for mixing output buffer, in units of Hz. */
  protected int contextFrequency = -1;
  
  /** Refresh intervalls, in units of Hz. */
  protected int contextRefresh = -1;
  
  /** Flag, indicating a synchronous context. */
  protected int contextSynchronized = ALC.FALSE;

  /**
   * Creates an OpenAL instance. The empty constructor will cause OpenAL to
   * open the default device, and create a context using default values. 
   */
  public AL() {
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
	public AL(String deviceArguments, int contextFrequency, int contextRefresh, boolean contextSynchronized) {
    this.deviceArguments = deviceArguments;
    this.contextFrequency = contextFrequency;
    this.contextRefresh = contextRefresh;
    this.contextSynchronized = contextSynchronized ? ALC.TRUE : ALC.FALSE;
	}
  
  
  /**
	 * @see org.lwjgl.openal.BaseAL#create()
	 */
	public void create() throws OpenALException {
		super.create();
    
    alc = new ALC(this);
    alc.create();
    
    device = alc.openDevice(deviceArguments);

    //check if doing default values or not
    if (contextFrequency == -1) {
      context = alc.createContext(device.device, 0);    
    } else {
    context = alc.createContext(device.device, 
                Sys.getDirectBufferAddress(
                  ALCcontext.createAttributeList(contextFrequency, contextRefresh, contextSynchronized)));
    }

                  
    alc.makeContextCurrent(context.context);     
	}
  
  /**
   * Retrieves the AL Context class 
   */
  public ALC getALC() {
    return alc;
  }
}