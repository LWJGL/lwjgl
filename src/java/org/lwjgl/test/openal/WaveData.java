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

import org.lwjgl.openal.AL;

import java.nio.ByteBuffer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * $Id$
 *
 * Utitlity class for loading wavefiles.
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class WaveData {
	/** actual wave data */
	public final ByteBuffer data;

	/** format type of data */
	public final int format;

	/** sample rate of data */
	public final int samplerate;

	/**
	 * Creates a new WaveData
	 * 
	 * @param data actual wavedata
	 * @param format format of wave data
	 * @param samplerate sample rate of data
	 */
	private WaveData(ByteBuffer data, int format, int samplerate) {
		this.data = data;
		this.format = format;
		this.samplerate = samplerate;
	}

  /**
   * Disposes the wavedata
   */
  public void dispose() {
    data.clear();
  }

	/**
	 * Creates a WaveData container from the specified filename
	 * 
	 * @param filepath path to file (relative, and in classpath) 
	 * @return WaveData containing data, or null if a failure occured
	 */
	public static WaveData create(String filepath) {
    try {
      return create(
        AudioSystem.getAudioInputStream(
          new BufferedInputStream(WaveData.class.getClassLoader().getResourceAsStream(filepath))));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }    
	}
  
  /**
   * Creates a WaveData container from the specified bytes
   *
   * @param buffer array of bytes containing the complete wave file
   * @return WaveData containing data, or null if a failure occured
   */
  public static WaveData create(byte[] buffer) {
    try {
      return create(
        AudioSystem.getAudioInputStream(
          new BufferedInputStream(new ByteArrayInputStream(buffer))));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }  

	/**
	 * Creates a WaveData container from the specified stream
	 * 
	 * @param ais AudioInputStream to read from
	 * @return WaveData containing data, or null if a failure occured
	 */
	public static WaveData create(AudioInputStream ais) {
		//get format of data
		AudioFormat audioformat = ais.getFormat();

		// get channels
		int channels = 0;
		if (audioformat.getChannels() == 1) {
			if (audioformat.getSampleSizeInBits() == 8) {
				channels = AL.AL_FORMAT_MONO8;
			} else if (audioformat.getSampleSizeInBits() == 16) {
				channels = AL.AL_FORMAT_MONO16;
			} else {
				assert false : "Illegal sample size";
			}
		} else if (audioformat.getChannels() == 2) {
			if (audioformat.getSampleSizeInBits() == 8) {
				channels = AL.AL_FORMAT_STEREO8;
			} else if (audioformat.getSampleSizeInBits() == 16) {
				channels = AL.AL_FORMAT_STEREO16;
			} else {
				assert false : "Illegal sample size";
			}
		} else {
			assert false : "Only mono or stereo is supported";
		}

		//read data into buffer
		byte[] buf =
			new byte[audioformat.getChannels()
				* (int) ais.getFrameLength()
				* audioformat.getSampleSizeInBits()
				/ 8];
		int read = 0, total = 0;
		try {
			while ((read = ais.read(buf, total, buf.length - total)) != -1
				&& total < buf.length) {
				total += read;
			}
		} catch (IOException ioe) {
			return null;
		}

		//insert data into bytebuffer
		ByteBuffer buffer = ByteBuffer.allocateDirect(buf.length);
		buffer.put(buf);

    //create our result
		WaveData wavedata =
			new WaveData(buffer, channels, (int) audioformat.getSampleRate());

    //close stream
		try {
			ais.close();
		} catch (IOException ioe) {
		}

		return wavedata;
	}
}