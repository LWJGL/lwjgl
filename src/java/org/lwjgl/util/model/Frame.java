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
package org.lwjgl.util.model;

import java.io.Serializable;

/**
 * The base class for animation frames.
 * @author $Author$
 * @version $Revision$
 * $Id$
 */
public abstract class Frame implements Serializable, Comparable {
	
	public static final long serialVersionUID = 1L;
	
	/** Frame time */
	private final float time;
	
	/** User-defined action to occur after this frame has been used. May be null */
	private final String action;
	
	/**
	 * C'tor
	 * @param time
	 * @param action
	 */
	public Frame(float time, String action) {
		this.time = time;
		this.action = action;
	}
	
	/**
	 * @return the frame time
	 */
	public final float getTime() {
		return time;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o == null) {
			return 0;
		}
		if (! (o instanceof Frame)) {
			return 0;
		}
		Frame f = (Frame) o;
		if (f.time == time) {
			return 0;
		} else if (f.time > time) {
			return 1;
		} else {
			return -1;
		}
	}
	
	/**
	 * Gets the user-defined animation action. This can be processed by whatever
	 * is animating the model to perform some special action after the frame is
	 * used. For example, you could use "stop" to stop the animation, or "rewind"
	 * to repeat the animation ad infinitum.
	 * @return String, or null, for no action
	 */
	public final String getAction() {
		return action;
	}
}
