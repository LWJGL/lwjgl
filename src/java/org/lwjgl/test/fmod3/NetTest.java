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

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.fmod3.FMOD;
import org.lwjgl.fmod3.FMODException;
import org.lwjgl.fmod3.FSound;
import org.lwjgl.fmod3.FSoundStream;

/**
 * $Id$ <br>
 * 
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class NetTest {

  private Frame frame;
  private boolean initialized;
  private Thread fmodThread;
  private volatile boolean running;
  private int channel = -1;
  private boolean paused = true;
  
  public NetTest(Frame frame) {
    this.frame = frame;
  }
  
	public static void main(String[] args) {
    
    final Frame frame = new Frame("LWJGL Fmod streaming player");    
    final NetTest netTest = new NetTest(frame);
    final boolean playing = false;
    
    final TextField txtField;
    final Button  btnPlay;
    final Button btnStop;
    
    // main panel
    Panel panel = new Panel();
    panel.setLayout(new FlowLayout());
    panel.add(new Label("URL:"));
    panel.add(txtField = new TextField("http://207.200.96.227:8038/", 60));
    panel.add(btnPlay = new Button("Play"));
    panel.add(btnStop = new Button("Stop"));
    panel.setBackground(new Color(0x99, 0x99, 0x99));
    
    frame.add(panel);
    frame.pack();
    
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
       frame.dispose(); 
       netTest.dispose();
      }
    });
    
    btnPlay.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { 
      	netTest.play(txtField.getText()); 
      }
    });
    
    btnStop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { 
      	netTest.stop();
      }
    });

    // setup frame
    int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - frame.getWidth()) / 2;
    int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - frame.getHeight()) / 2;
    frame.setLocation(x, y);
    frame.setResizable(false);
    frame.setVisible(true);
	}
  
  /**
	 * 
	 */
	private void dispose() {
    if(initialized) {
      stop();
    	FSound.FSOUND_Close();
    	FMOD.destroy();
    }
	}

	/**
	 * 
	 */
	protected void pause() {
    if(running && channel > 0) {
    	FSound.FSOUND_SetPaused(channel, paused = !paused);
      
      if(paused) {
      	frame.setTitle("LWJGL Fmod streaming player: Paused");
      }
    }
	}

	/**
	 * 
	 */
	protected void play(final String url) {
    if(!initialized) {
    	frame.setTitle("Initializing...");
      if(!initialize()) {
        frame.setTitle("LWJGL Fmod streaming player");
        return;
      }
    }
    
    if(fmodThread != null) {
      stop();
    }
    
    
    fmodThread = new Thread() {
    	public void run() {
        frame.setTitle("Opening [" + url + "]");
    		running = true;
    		FSoundStream stream = FSound.FSOUND_Stream_Open(url, FSound.FSOUND_NORMAL | FSound.FSOUND_NONBLOCKING, 0, 0);    
    		
    		if (stream != null) {
    			IntBuffer status = BufferUtils.createIntBuffer(4);
    			while(running) {
    				if(channel < 0) {
    					channel = FSound.FSOUND_Stream_PlayEx(FSound.FSOUND_FREE, stream, null, true);
    					FSound.FSOUND_SetPaused(channel, false);
              paused = false;
    				}
    				
    				int openstate = FSound.FSOUND_Stream_GetOpenState(stream);
    				if ((openstate == -1) || (openstate == -3)) {
    					error("failed to open stream!: " + FSound.FSOUND_Stream_Net_GetLastServerStatus());
    					break;
    				}
    				
    				FSound.FSOUND_Stream_Net_GetStatus(stream, status);
    				
            if(!paused) {
            	frame.setTitle("Playing [state: " + getNameFor(status.get(0)) + ", buffer: " + status.get(1) + ", bitrate: " + status.get(2) + "]");
            }
    				pause(10);
    			}
    			
    			while(!FSound.FSOUND_Stream_Stop(stream)) {
    				pause(10);
          }
    			while(!FSound.FSOUND_Stream_Close(stream)) {
    				pause(10);
          }
          channel = -1;
    		} else {
    			error("Unable to play: " + url + ". Error: " + FMOD.FMOD_ErrorString(FSound.FSOUND_GetError()));
    		}          
    	}
    };
    fmodThread.start();
	}

	/**
	 * @param i
	 * @return
	 */
	protected String getNameFor(int i) {
    switch(i) {
      case FSound.FSOUND_STREAM_NET_NOTCONNECTED:
        return "FSOUND_STREAM_NET_NOTCONNECTED";
      case FSound.FSOUND_STREAM_NET_CONNECTING:
        return "FSOUND_STREAM_NET_CONNECTING";
      case FSound.FSOUND_STREAM_NET_BUFFERING:
        return "FSOUND_STREAM_NET_BUFFERING";
      case FSound.FSOUND_STREAM_NET_READY:
        return "FSOUND_STREAM_NET_READY";
      case FSound.FSOUND_STREAM_NET_ERROR:
        return "FSOUND_STREAM_NET_ERROR";
    }
		return "";
	}

	/**
	 * 
	 */
	protected void stop() {
    if(frame.isVisible()) {
    	frame.setTitle("LWJGL Fmod streaming player: stopping...");
    }
    
    running = false;
    if(fmodThread != null) {
      try {
        fmodThread.join();
      } catch (InterruptedException inte) {
      }
      fmodThread = null;
    }
    
    if(frame.isVisible()) {
    	frame.setTitle("LWJGL Fmod streaming player");
    }
	}
  
  protected boolean initialize() {
    if(!initialized) {
      try {
        FMOD.create();
      } catch (FMODException fmode) {
        error(fmode.getMessage());
        return false;
      }      
      
      if (!FSound.FSOUND_Init(44100, 32, 0)) {
        error("Failed to initialize FMOD: " + FMOD.FMOD_ErrorString(FSound.FSOUND_GetError()));
        return false;
      }
      
      FSound.FSOUND_Stream_SetBufferSize(500);
      FSound.FSOUND_Stream_Net_SetBufferProperties(64000, 60, 80);
      initialized = true;
    }
    return initialized;
  }

	/**
	 * @param string
	 */
	private void error(String string) {
    final Dialog dialog = new Dialog(frame, "Error", true);
    dialog.add(new Label(string));
    dialog.pack();
    
    dialog.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
       dialog.dispose(); 
      }
    });
    
    // setup dialog
    int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - dialog.getWidth()) / 2;
    int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - dialog.getHeight()) / 2;
    dialog.setLocation(x, y);
    dialog.setVisible(true);  
	}

	/**
   * @param i
   */
  private static void pause(long i) {
    try {
      Thread.sleep(i);
    } catch (InterruptedException inte) {
    }
  }  
}