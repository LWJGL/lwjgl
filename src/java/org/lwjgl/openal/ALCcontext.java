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

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * $Id$
 *
 * Wrapper class, to make ALC contexts behave like the orginal api.
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
final class ALCcontext {
    
    /** address of actual context */
    final int context;
    
    /** 
     * Creates a new instance of ALCcontext 
     * 
     * @param context address of actual context
     */
    ALCcontext(int context) {
        this.context = context;
    }
    
    static Buffer createAttributeList(int contextFrequency, int contextRefresh, int contextSynchronized) {
      ByteBuffer attribList = ByteBuffer.allocateDirect(7*4).order(ByteOrder.nativeOrder());
      
      attribList.putInt(ALC.ALC_FREQUENCY);
      attribList.putInt(contextFrequency);
      attribList.putInt(ALC.ALC_REFRESH);
      attribList.putInt(contextRefresh);
      attribList.putInt(ALC.ALC_SYNC);
      attribList.putInt(contextSynchronized);
      attribList.putInt(0); //terminating int
      
      return attribList;
    }
}