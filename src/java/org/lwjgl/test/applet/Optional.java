/* 
 * Copyright (c) 2006 LWJGL Project
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

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.devil.IL;
import org.lwjgl.devil.ILU;
import org.lwjgl.devil.ILUT;
import org.lwjgl.devil.ILinfo;
import org.lwjgl.fmod3.FMOD;
import org.lwjgl.fmod3.FMusic;
import org.lwjgl.fmod3.FMusicModule;
import org.lwjgl.fmod3.FSound;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.opengl.AWTGLCanvas;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.test.devil.BasicTest;
import org.lwjgl.test.fmod3.StreamPlayerMemory;
import org.lwjgl.util.WaveData;

public class Optional extends AWTGLCanvas implements Test {

	float	angle	= 0;
	
	FMusicModule module = null;
	
	public Optional() throws LWJGLException {
		
		try {
			FMOD.create();
		} catch (Exception e) {
			System.out.println("Unable to create FMOD.\nPlease make sure that FMOD is available on this system. Exception: " + e);
			return;
		}
		
		try {
			IL.create();
		} catch (Exception e) {
			System.out.println("Unable to load IL");
			return;
		}
		
		try {
			ILU.create();
		} catch (Exception e) {
			System.out.println("Unable to load ILU");
			return;
		}

		try {
			ILUT.create();
		} catch (Exception e) {
			System.out.println("Unable to load ILUT");
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

	private void playFMOD() {
		FSound.FSOUND_Init(44100, 32, 0);
		ByteBuffer data = getData("Missing_you.mod");
		module = FMusic.FMUSIC_LoadSongEx(data, FSound.FSOUND_LOADMEMORY, null);
		FMusic.FMUSIC_PlaySong(module);
	}

	private ByteBuffer getData(String filename) {
		ByteBuffer buffer = null;
		
		System.out.println("Attempting to load: " + filename);
		
		try {
			BufferedInputStream bis = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream(filename));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			int bufferLength = 4096;
			byte[] readBuffer = new byte[bufferLength];
			int read = -1;
			
			while ((read = bis.read(readBuffer, 0, bufferLength)) != -1) {
				baos.write(readBuffer, 0, read);
			}
			
			//done reading, close
			bis.close();

			// place it in a buffer
			buffer = ByteBuffer.allocateDirect(baos.size());
			buffer.order(ByteOrder.nativeOrder());
			buffer.put(baos.toByteArray());
			buffer.flip();
			System.out.println("loaded " + buffer.remaining() + " bytes");
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return buffer;
	}

	public void paintGL() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glMatrixMode(GL11.GL_PROJECTION_MATRIX);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 640, 0, 480, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW_MATRIX);

		GL11.glPushMatrix();
		GL11.glTranslatef(320, 240, 0.0f);
		GL11.glRotatef(angle, 0, 0, 1.0f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2i(-50, -50);
		GL11.glVertex2i(50, -50);
		GL11.glVertex2i(50, 50);
		GL11.glVertex2i(-50, 50);
		GL11.glEnd();
		GL11.glPopMatrix();

		angle += 1;

		try {
			swapBuffers();
		} catch (Exception e) {/*OK*/
		}
	}

	public void start() {
		playFMOD();
		testDevil();
	}

	private void testDevil() {
		IntBuffer im = BufferUtils.createIntBuffer(1);
		IL.ilGenImages(im);
		System.out.println("ilBindImage");
		IL.ilBindImage(im.get(0));
		IL.ilEnable(IL.IL_ORIGIN_SET);
		IL.ilOriginFunc(IL.IL_ORIGIN_UPPER_LEFT);
		System.out.println("error = " + ILU.iluErrorString(IL.ilGetError()));
		try {
			System.out.println("load lump = " + IL.ilLoadFromStream(getClass().getResourceAsStream("/ILtest.tga"), IL.IL_TGA));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("error = " + ILU.iluErrorString(IL.ilGetError()));
		int newIm = IL.ilCloneCurImage();
		IL.ilCopyImage(im.get(0));
		IL.ilBindImage(newIm);
		ByteBuffer buf = IL.ilGetData();
		System.out.println("ilGetData");
		System.out.println("error = " + ILU.iluErrorString(IL.ilGetError()));
		int limit = buf.limit();
		System.out.println("limit = " + limit);
		for (int i = 0; i < buf.limit(); i += 3) {
			System.out.println(buf.get(i) + " " + buf.get(i + 1) + " " + buf.get(i + 2));
		}

		System.out.println("current image = " + im.get(0) + " IL.ilGetInteger(IL.IL_ACTIVE_IMAGE) = "
				+ IL.ilGetInteger(IL.IL_ACTIVE_IMAGE));
		System.out.println("Version: " + IL.ilGetInteger(IL.IL_VERSION_NUM));
		System.out.println("error = " + ILU.iluErrorString(IL.ilGetError()));

		ILinfo info = new ILinfo();
		ILU.iluGetImageInfo(info);
		System.out.println("info.id         = " + info.id);
		System.out.println("info.width      = " + info.width);
		System.out.println("info.height     = " + info.height);
		System.out.println("info.depth      = " + info.depth);
		System.out.println("info.bpp        = " + info.bpp);
		System.out.println("info.sizeOfData = " + info.sizeOfData);
		System.out.println("info.format     = " + info.format);
		System.out.println("info.type       = " + info.type);
		System.out.println("info.origin     = " + info.origin);
		System.out.println("info.palType    = " + info.palType);
		System.out.println("info.palSize    = " + info.palSize);
		System.out.println("info.numNext    = " + info.numNext);
		System.out.println("info.numMips    = " + info.numMips);
		System.out.println("info.numLayers  = " + info.numLayers);
		System.out.println("error = " + ILU.iluErrorString(IL.ilGetError()));

		System.out.println("ILUT Vendor: " + ILUT.ilutGetString(ILUT.ILUT_VENDOR));
	}

	public void stop() {
		FMusic.FMUSIC_StopSong(module);
		FMusic.FMUSIC_FreeSong(module);
		FSound.FSOUND_Close();
		FMOD.destroy();
		ILUT.destroy();
		ILU.destroy();
		IL.destroy();
		
		module = null;
	}
}
