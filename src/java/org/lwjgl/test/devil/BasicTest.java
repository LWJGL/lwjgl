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
package org.lwjgl.test.devil;

import org.lwjgl.devil.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
/**
 * $Id$
 * <p>The core DevIL API.</p>
 * 
 * @author Mark Bernard <captainjester@users.sourceforge.net>
 * @version $Revision$
 */
public class BasicTest {
    public static void main(String args[]) {
        try {
            IL.create();
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("ilGenImages");
        IntBuffer im =  ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
        IL.ilGenImages(1, im);
        System.out.println("ilBindImage");
        IL.ilBindImage(im.get(0));
        System.out.println("ilLoadImage");
        IL.ilLoadImage("F:/Apps/Java/eclipse/workspace/LWJGL/res/spaceinvaders/alien2.gif");
        System.out.println("ilGetError");
        int err = IL.ilGetError();
        System.out.println("err = " + err + " IL_NO_ERROR = " + IL.IL_NO_ERROR);
        ByteBuffer buf = IL.ilGetData();
        int limit = buf.limit();
        System.out.println("limit = " + limit);
        
        System.out.println("Version: " + IL.ilGetInteger(IL.IL_VERSION_NUM));
    }
}
