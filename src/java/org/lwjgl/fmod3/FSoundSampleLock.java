/* 
 * Copyright (c) 2004 LWJGL Project
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

import java.nio.ByteBuffer;

/**
 * $Id$
 * <br>
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class FSoundSampleLock {

  /** ByteBuffer that will point to the first part of the locked data */
	private ByteBuffer	ptr1;

  /** 
   * ByteBuffer that will point to the second part of the locked data. 
   * This will be null if the data locked hasnt wrapped at the end of the buffer
   */
	private ByteBuffer	ptr2;

  /** Length of data in BYTES that was locked for ptr1 */
	private int					len1;

  /** 
   * Length of data in BYTES that was locked for ptr2. 
   * This will be 0 if the data locked hasnt wrapped at the end of the buffer
   */
	private int					len2;

  /**
   * Creates a new FSoundSampleLock
   */
	public FSoundSampleLock() {
	}
  
  /**
   * Creates a new SampleLock
   * 
   * @param ptr1 ByteBuffer that will point to the first part of the locked data
   * @param ptr2 ByteBuffer that will point to the second part of the locked data
   * @param len1 Length of data in BYTES that was locked for ptr1
   * @param len2 Length of data in BYTES that was locked for ptr2
   */
  void set(ByteBuffer ptr1, ByteBuffer ptr2, int len1, int len2) {
    this.ptr1 = ptr1;
    this.ptr2 = ptr2;
    this.len1 = len1;
    this.len2 = len2;
  }  
	/**
	 * @return Returns the len1.
	 */
	public int getLen1() {
		return len1;
	}
	/**
	 * @return Returns the len2.
	 */
	public int getLen2() {
		return len2;
	}
	/**
	 * @return Returns the ptr1.
	 */
	public ByteBuffer getPtr1() {
		return ptr1;
	}
	/**
	 * @return Returns the ptr2.
	 */
	public ByteBuffer getPtr2() {
		return ptr2;
	}
}