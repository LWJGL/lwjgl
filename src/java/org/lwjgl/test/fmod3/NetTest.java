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
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.swing.BoxLayout;

import org.lwjgl.BufferUtils;
import org.lwjgl.fmod3.FMOD;
import org.lwjgl.fmod3.FMODException;
import org.lwjgl.fmod3.FSound;
import org.lwjgl.fmod3.FSoundStream;

/**
 * 
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$ <br>
 */
public class NetTest {
	
	/** Main frame */
	private Frame							frame;
	
	/** Whether we're initialized */
	private boolean						initialized;
	
	/** Audio thread */
	private Thread						fmodThread;
	
	/** Whether we're running */
	private volatile boolean	running;
	
	/** Channel we'll be playing on */
	private int								channel	= -1;
	
	/** Canvas with fancy stuff */
	public static Canvas			spectrumCanvas;
	
	/** Buffer containing the spectrum (512 floats) */
	public static FloatBuffer	spectrum;
	
	/** List of known urls (monkeyradio, di streams) */
	public static String[]		urls		= new String[] { "http://www.scenemusic.eu:8002/live.mp3", 
															 "http://basu.cockos.com:6969/"};
	
	/**
	 * Creates a new NetTest
	 * @param frame parent frame
	 */
	public NetTest(Frame frame) {
		this.frame = frame;
	}
	
	/**
	 * Disposes NetTest
	 */
	private void dispose() {
		if (initialized) {
			stop();
			FSound.FSOUND_Close();
			FMOD.destroy();
			System.exit(0);
		}
	}
	
	/**
	 * Plays the supplied URL
	 * 
	 * @param url URK to play
	 */
	protected void play(final String url) {
		// do initialize if needed
		if (!initialized) {
			frame.setTitle("Initializing...");
			if (!initialize()) {
				frame.setTitle("LWJGL Fmod streaming player");
				return;
			}
		}
		
		// if already running, stop
		if (fmodThread != null) {
			stop();
		}
		
		// actual audi thread that starts playing
		fmodThread = new Thread() {
			
			public void run() {
				// Open the url and prepare to play
				// ============================================
				frame.setTitle("Opening [" + url + "]");
				running = true;
				FSoundStream stream = FSound.FSOUND_Stream_Open(url, FSound.FSOUND_NORMAL | FSound.FSOUND_NONBLOCKING, 0, 0);
				// --------------------------------------------
				
				// With a stream in hand, loop untill we're told to stop
				// ============================================
				if (stream != null) {
					IntBuffer status = BufferUtils.createIntBuffer(4);
					while (running) {
						
						// get channel, if we haven't got one already
						if (channel < 0) {
							channel = FSound.FSOUND_Stream_PlayEx(FSound.FSOUND_FREE, stream, null, false);
						}
						
						// query open state of stream
						int openstate = FSound.FSOUND_Stream_GetOpenState(stream);
						if ((openstate == -1) || (openstate == -3)) {
							error("failed to open stream!: " + FSound.FSOUND_Stream_Net_GetLastServerStatus());
							break;
						}
						
						// get status of stream
						FSound.FSOUND_Stream_Net_GetStatus(stream, status);
						
						frame.setTitle("Playing [state: " + getNameFor(status.get(0)) + ", buffer: " + status.get(1)
													 + ", bitrate: " + status.get(2) + "]");
						
						// repaint spectrum
						spectrumCanvas.repaint();
						
						// dont use all resources!
						pause(25);
					}
					
					// exiting. spin on stop/close since we're non-blocking
					while (!FSound.FSOUND_Stream_Stop(stream)) {
						pause(10);
					}
					while (!FSound.FSOUND_Stream_Close(stream)) {
						pause(10);
					}
					channel = -1;
				} else {
					error("Unable to play: " + url + ". Error: " + FMOD.FMOD_ErrorString(FSound.FSOUND_GetError()));
				}
				// --------------------------------------------
				
				// we're done, nuke leftovers
				spectrumCanvas.repaint();
			}
		};
		fmodThread.start();
	}
	
	/**
	 * @return enum name for supplied stream open state
	 */
	protected String getNameFor(int i) {
		switch (i) {
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
	 * Stops the playing
	 */
	protected void stop() {
		// update title
		if (frame.isVisible()) {
			frame.setTitle("LWJGL Fmod streaming player: stopping...");
		}
		
		// nuke the thread, but wait for it to finish!
		running = false;
		if (fmodThread != null) {
			try {
				fmodThread.join();
			} catch (InterruptedException inte) {
			}
			fmodThread = null;
		}
		
		// update title
		if (frame.isVisible()) {
			frame.setTitle("LWJGL Fmod streaming player");
		}
	}
	
	/**
	 * Initializes the test
	 * 
	 * @return true if initialization was successfull
	 */
	protected boolean initialize() {
		if (!initialized) {
			// create FMOD first!
			try {
				FMOD.create();
			} catch (FMODException fmode) {
				error(fmode.getMessage());
				fmode.printStackTrace();
				return false;
			}
			
			// initialize fsound
			if (!FSound.FSOUND_Init(44100, 32, 0)) {
				error("Failed to initialize FMOD: " + FMOD.FMOD_ErrorString(FSound.FSOUND_GetError()));
				return false;
			}
			
			// get fft unit and activate it (else, we can't get the spectrum)
			FSound.FSOUND_DSP_SetActive(FSound.FSOUND_DSP_GetFFTUnit(), true);
			
			// get the spectrum
			spectrum = FSound.FSOUND_DSP_GetSpectrum();
			
			// setup buffers
			FSound.FSOUND_Stream_SetBufferSize(100);
			FSound.FSOUND_Stream_Net_SetBufferProperties(64000, 95, 95);
			
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
	 * Pause calling thread for i milliseconds
	 * 
	 * @param i how long time to pause
	 */
	private static void pause(long i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException inte) {
		}
	}
	
	/**
	 * Executes the NetTest
	 * @param args
	 */
	public static void main(String[] args) {
		
		// UI and instance stuff
		final Frame frame = new Frame("LWJGL Fmod streaming player");
		final Choice choice;
		final Button btnPlay;
		final Button btnStop;
		final boolean playing = false;
		final NetTest netTest = new NetTest(frame);
		
		// main panel
		frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
		final Panel panel[] = { new Panel(), new Panel()};
		panel[0].setLayout(new FlowLayout());
		panel[0].add(new Label("URL:"));
		panel[0].add(choice = new Choice());
		panel[0].add(btnPlay = new Button("Play"));
		panel[0].add(btnStop = new Button("Stop"));
		panel[1].add(spectrumCanvas = netTest.new SpectrumCanvas());
		panel[0].setBackground(new Color(0x99, 0x99, 0x99));
		panel[1].setBackground(new Color(0x99, 0x99, 0x99));
		
		// add list of known urls
		for (int i = 0; i < urls.length; i++) {
			choice.add(urls[i]);
		}
		
		// add spectrum panel, set size and pack
		frame.add(panel[0]);
		spectrumCanvas.setSize(512, 100);
		frame.pack();
		
		// Add listeners
		// =====================================================
		frame.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				frame.dispose();
				netTest.dispose();
			}
		});
		
		btnPlay.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.add(panel[1]);
				frame.pack();
				netTest.play(choice.getSelectedItem());
			}
		});
		
		btnStop.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.remove(panel[1]);
				frame.pack();
				netTest.stop();
			}
		});
		// -----------------------------------------------------
		
		// setup frame
		int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - frame.getWidth()) / 2;
		int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - frame.getHeight()) / 2;
		frame.setLocation(x, y);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	/**
	 * Simplish spectrum
	 * @author Brian Matzon <brian@matzon.dk>
	 * @version $Revision$
	 */
	class SpectrumCanvas extends Canvas {
		
		/** Offscreen image for that flickerless feel! (TM) */
		Image					bufferImage;
		
		/** Graphics context for buffer */
		Graphics2D		bufferGraphics;
		
		/** Background color */
		Color					bgColor	= new Color(0x99, 0x99, 0x99);
		
		/** Gradient paint */
		GradientPaint	gp;
		
		/**
		 * Called to paint the canvas
		 */
		public void paint(Graphics g) {
			// create offscreen
			if (bufferImage == null) {
				gp = new GradientPaint(0, 0, Color.RED, 0, getHeight(), Color.GREEN);
				bufferImage = createImage(getWidth(), getHeight());
				bufferGraphics = (Graphics2D) bufferImage.getGraphics();
			}
			
			// clear old
			bufferGraphics.setColor(bgColor);
			bufferGraphics.fillRect(0, 0, getWidth(), getHeight());
			
			// if we have a spectrum, draw it
			if (spectrum != null && NetTest.this.fmodThread != null) {
				int x = 0;
				
				// for each spectrum value, draw a line according to its 
				// value (times 4, since we want higher frequencies to show up)
				bufferGraphics.setPaint(gp);
				for (int i = 0; i < 256; i++) {
					int height = (int) (getHeight() * 4.0 * spectrum.get(i));
					bufferGraphics.fillRect(x, getHeight() - height, 2, height);
					x += 2;
				}
			}
			
			// map offscreen onscreen
			g.drawImage(bufferImage, 0, 0, this);
		}
		
		/**
		 * Just draw the sucker instead of default update
		 */
		public void update(Graphics g) {
			paint(g);
		}
	}
}