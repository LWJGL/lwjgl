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
package org.lwjgl.test.openal;

import org.lwjgl.openal.eax.EAX;

/**
 * $Id$
 *
 * This test initializes EAX and tries to get and set some EAX values
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class EAXTest extends BasicTest {
    
    /**
     * Creates an instance of EAXTest
     */
    public EAXTest() {
        super();
    }

    /**
     * Runs the actual test, using supplied arguments
     */
    protected void execute(String[] args) {
        alInitialize();
        
        EAX eax = new EAX();
        try {
            eax.create();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.print("EAX supported...");
        
        //no errorchecking from now on, since our context is gone.
        //shutdown
        alc.makeContextCurrent(null);
        alc.destroyContext(context);
        alc.closeDevice(device);
        
        System.out.println("test done.");
    }
    
    /**
     * main entry point
     *
     * @param args String array containing arguments
     */
    public static void main(String[] args) {
        EAXTest eaxTest = new EAXTest();
        eaxTest.execute(args);
    }
}