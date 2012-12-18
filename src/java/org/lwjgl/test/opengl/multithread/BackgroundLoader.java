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
/*
 * Created by LWJGL.
 * User: spasi
 * Date: 2004-03-30
 * Time: 8:41:42 pm
 */
package org.lwjgl.test.opengl.multithread;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Drawable;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.GLSync;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import java.nio.ByteBuffer;
import java.util.concurrent.locks.ReentrantLock;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.*;

abstract class BackgroundLoader {

	private static final int WIDTH  = 32;
	private static final int HEIGHT = 32;

	// CPU synchronization
	private final ReentrantLock lock = new ReentrantLock();
	// GPU synchronization
	private GLSync fence;

	private Drawable drawable;

	private boolean running;

	private ByteBuffer texture;
	private int        texID;

	protected BackgroundLoader() {
		running = true;
		texture = BufferUtils.createByteBuffer(WIDTH * HEIGHT * 3);
	}

	abstract Drawable getDrawable() throws LWJGLException;

	void cleanup() {
		running = false;
	}

	void start() throws LWJGLException {
		// The shared context must be created on the main thread.
		drawable = getDrawable();

		new Thread(new Runnable() {
			public void run() {
				System.out.println("-- Background Thread started --");

				System.out.println("** Sleeping, no texture created yet **");

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				try {
					// Make the shared context current in the worker thread
					drawable.makeCurrent();
				} catch (LWJGLException e) {
					throw new RuntimeException(e);
				}

				System.out.println("** Drawable created **");

				// Create a "dummy" texture while we wait for texture IO
				createCheckerTexture(Color.RED, Color.WHITE, 2);

				lock.lock();

				texID = glGenTextures();
				glBindTexture(GL_TEXTURE_2D, texID);
				glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, WIDTH, HEIGHT, 0, GL_RGB, GL_UNSIGNED_BYTE, texture);

				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

				glBindTexture(GL_TEXTURE_2D, 0);

				// OpenGL commands from different contexts may be executed in any order. So we need a way to synchronize
				final boolean useFences = GLContext.getCapabilities().OpenGL32;

				if ( useFences )
					fence = glFenceSync(GL_SYNC_GPU_COMMANDS_COMPLETE, 0);
				else
					glFlush(); // Best we can do without fences. This will force rendering on the main thread to happen after we upload the texture.

				lock.unlock();

				System.out.println("** Dummy texture created **");

				long lastTextureCreated = System.currentTimeMillis(); // Delay first texture creation
				int count = 0;
				while ( running ) {
					long time = System.currentTimeMillis();
					if ( time - lastTextureCreated < 5000 ) { // Update the texture every 5 seconds
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						continue;
					}

					// Create the "true" texture
					if ( count % 2 == 0 )
						createGradientTexture(Color.RED, Color.BLUE);
					else
						createGradientTexture(Color.GREEN, Color.YELLOW);

					lock.lock();

					glBindTexture(GL_TEXTURE_2D, texID);
					glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, WIDTH, HEIGHT, 0, GL_RGB, GL_UNSIGNED_BYTE, texture);

					glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
					glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

					glBindTexture(GL_TEXTURE_2D, 0);

					if ( useFences )
						fence = glFenceSync(GL_SYNC_GPU_COMMANDS_COMPLETE, 0);
					else
						glFlush();

					lock.unlock();

					System.out.println("** Created new gradient texture **");

					lastTextureCreated = System.currentTimeMillis();
					count++;
				}

				drawable.destroy();

				System.out.println("-- Background Thread finished --");
			}
		}).start();
	}

	int getTexID() {
		lock.lock();
		try {
			if ( fence != null ) {
				glWaitSync(fence, 0, GL_TIMEOUT_IGNORED);
				fence = null;
			}
			return texID;
		} finally {
			lock.unlock();
		}
	}

	private void createCheckerTexture(final ReadableColor a, final ReadableColor b, final int size) {
		int i = 0;
		for ( int y = 0; y < HEIGHT; y++ ) {
			for ( int x = 0; x < WIDTH; x++ ) {
				ReadableColor c = (x / size) % 2 == 0 ? ((y / size) % 2 == 0 ? a : b) : ((y / size) % 2 == 0 ? b : a);
				texture.put(i + 0, c.getRedByte());
				texture.put(i + 1, c.getGreenByte());
				texture.put(i + 2, c.getBlueByte());
				i += 3;
			}
		}
	}

	private void createGradientTexture(final ReadableColor a, final ReadableColor b) {
		float l = 0.0f;
		int i = 0;
		for ( int y = 0; y < HEIGHT; y++ ) {
			for ( int x = 0; x < WIDTH; x++ ) {
				texture.put(i + 0, lerp(a.getRed(), b.getRed(), l));
				texture.put(i + 1, lerp(a.getGreen(), b.getGreen(), l));
				texture.put(i + 2, lerp(a.getBlue(), b.getBlue(), l));
				i += 3;
			}
			l += (1.0f / (HEIGHT - 1));
		}
	}

	private static byte lerp(final int a, final int b, final float l) {
		return (byte)Math.round(((1.0f - l) * a + l * b));
	}

}