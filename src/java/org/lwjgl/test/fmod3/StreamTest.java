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
package org.lwjgl.test.fmod3;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.fmod3.FMOD;
import org.lwjgl.fmod3.FMODException;
import org.lwjgl.fmod3.FSound;

/**
 * $Id$ <br>
 * 
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class StreamTest {

  public static void main(String[] args) {
    try {
      FMOD.create();
    } catch (FMODException fmode) {
      fmode.printStackTrace();
      return;
    }

    IntBuffer caps = BufferUtils.createIntBuffer(1);
    FSound.FSOUND_SetOutput(FSound.FSOUND_OUTPUT_DSOUND);
    
    int count = FSound.FSOUND_GetNumDrivers();
    System.out.println("Found: " + count + " drivers");
    for(int i=0;i<count; i++) {
        FSound.FSOUND_GetDriverCaps(i, caps);
        System.out.println(i + " (" + FSound.FSOUND_GetDriverName(i) + ") = " + caps.get(0));
    }
    
    // init
    if (!FSound.FSOUND_Init(44100, 32, 0)) {
      System.out.println("Failed to initialize FMOD");
      System.out.println("Error: " + FMOD.FMOD_ErrorString(FSound.FSOUND_GetError()));
      return;
    }
    
    IntBuffer mem = BufferUtils.createIntBuffer(2);
    FSound.FSOUND_GetMemoryStats(mem);
    System.out.println("Allocated: " + mem.get(0) + ", Max: " + mem.get(1));
    
    IntBuffer hwChannels = BufferUtils.createIntBuffer(3);
    FSound.FSOUND_GetNumHWChannels(hwChannels);
    System.out.println("2d: " + hwChannels.get(0) + ", 3d: " + hwChannels.get(1) + ", total: " + hwChannels.get(2));

    FSound.FSOUND_Close();
    FMOD.destroy();
  }
}