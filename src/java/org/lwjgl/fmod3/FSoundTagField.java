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

import java.nio.ByteBuffer;

/**
 * This class defines attributes in a tag field
 * $Id$
 * <br>
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class FSoundTagField {

	/** Name of tagfield */
	String			name;

	/**  ByteBuffer that will point to the tagfield data */
	ByteBuffer	value;

	/** Length of tagfield data */
	int					length;

	/** Type of tagfield */
	int					type;
  
  /**
   * Creates a new FSoundTagField
   */
  public FSoundTagField() {
  }  

	/**
	 * Creates a new FSoundTagField
	 */
	public FSoundTagField(int type, String name) {
    this.type = type;
    this.name = name;
	}
  
	/**
	 * Sets the name value and type
   * @param name name of tagfield
	 * @param value value of tagfield
	 * @param type type of tagfield
	 */
	void set(String name, ByteBuffer value, int type) {
    this.name = name;
		this.value = value;
		this.length = value.capacity();
    this.type = type;
	}
  
	/**
	 * @return Returns the length.
	 */
	public int getLength() {
		return length;
	}
  
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
  
	/**
	 * @return Returns the type.
	 */
	public int getType() {
		return type;
	}
  
	/**
	 * @return Returns the value.
	 */
	public ByteBuffer getValue() {
		return value.asReadOnlyBuffer();
	}
  
  /**
   * @return value as a <b>trimmed</b> string
   */
  public String getValueAsString() {
    byte[] buffer = new byte[value.capacity()];
    value.get(buffer);
    value.rewind();
    return new String(buffer).trim();
  }  
}