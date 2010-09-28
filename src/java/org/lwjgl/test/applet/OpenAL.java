/*
 * Copyright (c) 2002-2008 LWJGL Project
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
package org.lwjgl.test.applet;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.opengl.AWTGLCanvas;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.WaveData;

import static org.lwjgl.opengl.GL11.*;

public class OpenAL extends AWTGLCanvas implements Test {

	float	angle;

	// create 1 buffer and 1 source
    IntBuffer buffers = BufferUtils.createIntBuffer(1);
    IntBuffer sources = BufferUtils.createIntBuffer(1);

	public OpenAL() throws LWJGLException {

		try {
			AL.create();
		} catch (Exception e) {
			System.out.println("Unable to create OpenAL.\nPlease make sure that OpenAL is available on this system. Exception: " + e);
			return;
		}

		Thread t = new Thread() {

			public void run() {
				while (true) {
					if (isVisible())
						repaint();
					Display.sync(60);
				}
			}
		};
		t.setDaemon(true);
		t.start();
	}

	private void playOpenAL() {
		int lastError;

        // al generate buffers and sources
        buffers.position(0).limit(1);
        AL10.alGenBuffers(buffers);
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }

        sources.position(0).limit(1);
        AL10.alGenSources(sources);
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }

      // load wave data from buffer
      WaveData wavefile = WaveData.create(getClass().getClassLoader().getResourceAsStream("Footsteps.wav"));

      //copy to buffers
      AL10.alBufferData(buffers.get(0), wavefile.format, wavefile.data, wavefile.samplerate);

      //unload file again
      wavefile.dispose();

        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }

        //set up source input
        AL10.alSourcei(sources.get(0), AL10.AL_BUFFER, buffers.get(0));
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }

        //lets loop the sound
        AL10.alSourcei(sources.get(0), AL10.AL_LOOPING, AL10.AL_TRUE);
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }

        //play source 0
        AL10.alSourcePlay(sources.get(0));
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }
	}

	private void exit(int error) {
	    System.out.println("OpenAL Error: " + AL10.alGetString(error));
	}

	public void paintGL() {
		glClear(GL_COLOR_BUFFER_BIT);
		glMatrixMode(GL_PROJECTION_MATRIX);
		glLoadIdentity();
		glOrtho(0, 640, 0, 480, 1, -1);
		glMatrixMode(GL_MODELVIEW_MATRIX);

		glPushMatrix();
		glTranslatef(320, 240, 0.0f);
		glRotatef(angle, 0, 0, 1.0f);
		glBegin(GL_QUADS);
		glVertex2i(-50, -50);
		glVertex2i(50, -50);
		glVertex2i(50, 50);
		glVertex2i(-50, 50);
		glEnd();
		glPopMatrix();

		angle += 1;

		try {
			swapBuffers();
		} catch (Exception e) {/*OK*/
		}
	}

	public void start() {
		playOpenAL();
	}

	public void stop() {
		int lastError;

        //stop source 0
        AL10.alSourceStop(sources.get(0));
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }

        //delete buffers and sources
        sources.position(0).limit(1);
        AL10.alDeleteSources(sources);
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }

        buffers.position(0).limit(1);
        AL10.alDeleteBuffers(buffers);
        if((lastError = AL10.alGetError()) != AL10.AL_NO_ERROR) {
            exit(lastError);
        }

        AL.destroy();
	}
}
