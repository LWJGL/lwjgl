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
package org.lwjgl.test.fmod3;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.fmod3.FMOD;
import org.lwjgl.fmod3.FMODException;
import org.lwjgl.fmod3.FMusic;
import org.lwjgl.fmod3.FMusicModule;
import org.lwjgl.fmod3.FSound;
import org.lwjgl.fmod3.callbacks.FMusicCallback;

/**
 * $Id$ <br>
 * 
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class SyncTest {
  
  /** Path to file to play */
  private String filePath;
  
  /** Module instance loaded */
  private FMusicModule module;
  
  /** Whether test is running */
  private boolean running = true;
  
  /** Current row */
  private int row;
  
  /** Current order */
  private int order;
  
  /** Number of orders in the song */
  private int numOrders;
  
	/**
	 * Creates a new SyncTest
	 * @param string
	 */
	public SyncTest(String filePath) {
    this.filePath = filePath;
    
    // create thread to exit when a key has been pressed
    Thread t = new Thread() {
      public void run() {
        try {
         System.in.read();
        } catch (IOException ioe) {
        }
        running = false;
      }      
    };
    t.setDaemon(true);
    t.start();
	}

  /**
   * 
   * @param args
   */
	public static void main(String[] args) {

    // check for file existance
    File file = new File(args[0]);
    if (!file.exists()) {
      System.out.println("No such file: " + args[0]);
      return;
    }
    
    // initialize FMOD
    try {
      System.out.println("Initializing FMOD");
			FMOD.create();
		} catch (FMODException fmode) {
			fmode.printStackTrace();
			return;
		}
    
    // start actual test
    SyncTest sync = new SyncTest(args[0]);
    sync.executeTest();
	}
  
  /**
   * Executes the test
   */
  public void executeTest() {
    // do setup
    setup();
    
    // if we have a module - get going
    if (module != null) {
      // high priority... - might otherwise skip
      Sys.setProcessPriority(Sys.HIGH_PRIORITY);
      
      // go go go!
    	run();
    }
    
    // we're done - clean up
    destroy();
  }
  
  /**
   * Setup FMOD
   */
  private void setup() {
    if (!FSound.FSOUND_Init(44100, 32, 0)) {
      System.out.println("Failed to initialize FMOD");
      System.out.println("Error: " + FMOD.FMOD_ErrorString(FSound.FSOUND_GetError()));
      return;
    }
    
    // load module
    System.out.println("Loading " + filePath);
    module = FMusic.FMUSIC_LoadSong(filePath); 
    
    if (module == null) {
     System.out.println("Unable to load " + filePath + ": " + FMOD.FMOD_ErrorString(FSound.FSOUND_GetError()));
     return;
    }
    
    // get number of orders
    numOrders = FMusic.FMUSIC_GetNumOrders(module);
    
    // install order callback
    FMusic.FMUSIC_SetOrderCallback(module, new FMusicCallback() {
      public void FMUSIC_CALLBACK(FMusicModule module, int param) {
        order = param;
      }
    }, 1);
    
    // install row callback
    // will be called once PER CHANNEL! - but since we only set the row
    // no harm is done :)
    FMusic.FMUSIC_SetRowCallback(module, new FMusicCallback() {
      public void FMUSIC_CALLBACK(FMusicModule module, int param) {
        row = param;
      }
    }, 1);
    
    // try to add some userdata
    ByteBuffer buffer = BufferUtils.createByteBuffer(24);
    buffer.putInt(1).putInt(2).putInt(3).putInt(4).putInt(5).putInt(6).rewind();
    FMusic.FMUSIC_SetUserData(module, buffer);
  }
  
  /**
   * Update status of module - spew it out in console
   */
  private void update() {
    // mark as not running when finished
    if(FMusic.FMUSIC_IsFinished(module)) {
      running = false;
    }

    int patternLength = FMusic.FMUSIC_GetPatternLength(module, order);
    int time = FMusic.FMUSIC_GetTime(module) / 1000;
    int time2 = FMusic.FMUSIC_GetTime(module) % 1000 / 10;
    double cpu = Math.round(FSound.FSOUND_GetCPUUsage() * 100.0) / 100.0;
    
    System.out.println("O: " +
        ((order < 10) ? "0" : "") + order + "/" + numOrders + " | R: " 
        + ((row < 10) ? "0" : "") + row + "/" + patternLength + " | T: "
        + time + "." 
        + ((time2 < 10) ? "0" : "") + time2 + " | BPM: " + FMusic.FMUSIC_GetBPM(module) + " | Play: " + FMusic.FMUSIC_IsFinished(module) 
        + " | C: " + FSound.FSOUND_GetChannelsPlaying() + " | CPU: " + cpu);    
  }
  
  /**
   * Runs the actual test - that is, play | update ad nausea 
   */
  private void run() {
    // play
    FMusic.FMUSIC_PlaySong(module);
    
    // loop, printing update, if we actually changed row
    int lastRow = row;
    while(running) {
      if(lastRow != row) {
        lastRow = row;        
      	update();
      } else {
      	pause(5);
      }
    }
  }
  
  /**
	 * @param i
	 */
	private void pause(long i) {
    try {
    	Thread.sleep(i);
    } catch (InterruptedException inte) {
    }
	}

	// clean up our own mess
  private void destroy() {
    if(module != null) {
      // retrieve userdata
      ByteBuffer buffer = FMusic.FMUSIC_GetUserData(module, 24);
      
      // should contain 1,2,3,4,5,6
      for(int i=0; i<6; i++) {
      	System.out.println(buffer.getInt());
      }      
      
      FMusic.FMUSIC_FreeSong(module);
    }
    FSound.FSOUND_Close();
    FMOD.destroy();     
  }
}