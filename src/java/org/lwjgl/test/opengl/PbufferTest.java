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
package org.lwjgl.test.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import org.lwjgl.Display;
import org.lwjgl.DisplayMode;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Pbuffer;
import org.lwjgl.opengl.Window;
import org.lwjgl.opengl.glu.GLU;
import org.lwjgl.util.vector.Vector2f;

/**
 * $Id$
 *
 * Tests Pbuffers
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */
public class PbufferTest {

  /** Intended deiplay mode */
  private DisplayMode mode;

  /** our quad moving around */
  private Vector2f quadPosition;

  /** our quadVelocity */
  private Vector2f quadVelocity;

  /** angle of quad */
  private float angle;

  /** degrees to rotate per frame */
  private float angleRotation = 1.0f;

  /** Max speed of all changable attributes */
  private static final float MAX_SPEED = 20.0f;

  /** Pbuffer instance */
  private static Pbuffer pbuffer;

  /** The shared texture */
  private static int tex_handle;

  /**
   * Executes the test
   */
  public void execute() {
    initialize();

    mainLoop();

    cleanup();
  }

  /**
   * Initializes the test
   */
  private void initialize() {
    try {
      //find displaymode
      mode = findDisplayMode(800, 600, 16);

      // start of in windowed mode
      Window.create("Test", 50, 50, mode.width, mode.height, mode.bpp, 0, 0, 0, 0);
//      gl = new GLWindow("Test", 50, 50, mode.width, mode.height, mode.bpp, 0, 0, 0);
      if ((Pbuffer.getPbufferCaps() & Pbuffer.PBUFFER_SUPPORTED) == 0) {
          System.out.println("No Pbuffer support!");
          System.exit(1);
      }
      System.out.println("Pbuffer support detected");

      glInit();
      initPbuffer();

      Keyboard.create();

      quadPosition = new Vector2f(100f, 100f);
      quadVelocity = new Vector2f(1.0f, 1.0f);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

	/**
	 * Runs the main loop of the "test"
	 */
	private void mainLoop() {
		while (!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !Window.isCloseRequested()) {
			if (Window.isVisible()) {
				// check keyboard input
				processKeyboard();
				// do "game" logic, and render it
				logic();
				render();
			} else {
				// no need to render/paint if nothing has changed (ie. window
				// dragged over)
				if (Window.isDirty()) {
					render();
				}
				// don't waste cpu time, sleep more
				try {
					Thread.sleep(100);
				} catch (InterruptedException inte) {
				}
			}
			// Update window
			Window.update();
		}
	}

  /**
   * Performs the logic
   */
  private void logic() {
    angle += angleRotation;
    if (angle > 90.0f) {
      angle = 0.0f;
    }

    quadPosition.x += quadVelocity.x;
    quadPosition.y += quadVelocity.y;

    //check colision with vertical border border
    if (quadPosition.x + 50 >= mode.width || quadPosition.x - 50 <= 0) {
      quadVelocity.x *= -1;
    }

    //check collision with horizontal border
    if (quadPosition.y + 50 >= mode.height || quadPosition.y - 50 <= 0) {
      quadVelocity.y *= -1;
    }
  }

  private void render() {
    if (pbuffer.isBufferLost()) {
        System.out.println("Buffer contents lost - will recreate the buffer");
        pbuffer.destroy();
        initPbuffer();
    }
    pbuffer.makeCurrent();
    // Pbuffer rendering
    //clear background
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

    // draw white quad
    GL11.glPushMatrix();
    {
      GL11.glTranslatef(quadPosition.x, quadPosition.y, 0);
      GL11.glRotatef(angle, 0.0f, 0.0f, 1.0f);
      GL11.glColor3f(1.0f, 1.0f, 1.0f);
      GL11.glBegin(GL11.GL_QUADS);
      {
        GL11.glVertex2i(-50, -50);
        GL11.glVertex2i(50, -50);
        GL11.glVertex2i(50, 50);
        GL11.glVertex2i(-50, 50);
      }
      GL11.glEnd();
    }
    GL11.glPopMatrix();
    GL11.glCopyTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, 0, 0, 512, 512, 0);
    Window.makeCurrent();

    // OpenGL window rendering
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    // draw white quad
    GL11.glPushMatrix();
    {
      GL11.glTranslatef(quadPosition.x, quadPosition.y, 0);
      GL11.glRotatef(angle, 0.0f, 0.0f, 1.0f);
      GL11.glColor3f(1.0f, 1.0f, 0.0f);
      GL11.glBegin(GL11.GL_QUADS);
      {
        GL11.glTexCoord2f(0f, 0f);
        GL11.glVertex2i(-50, -50);
        GL11.glTexCoord2f(1f, 0f);
        GL11.glVertex2i(50, -50);
        GL11.glTexCoord2f(1f, 1f);
        GL11.glVertex2i(50, 50);
        GL11.glTexCoord2f(0f, 1f);
        GL11.glVertex2i(-50, 50);
      }
      GL11.glEnd();
    }
    GL11.glPopMatrix();
  }

  private void initPbuffer() {
      try {
          pbuffer = new Pbuffer(512, 512, mode.bpp, 0, 0, 0, 0, null);
          pbuffer.makeCurrent();
          initGLState(256, 256, 0.5f);
          GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex_handle);
          Window.makeCurrent();
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  /**
   * Processes keyboard input
   */
  private void processKeyboard() {
    Keyboard.poll();

    //check for fullscreen key
    if (Keyboard.isKeyDown(Keyboard.KEY_F)) {

      try {
        destroyTexture();
        Keyboard.destroy();
        pbuffer.destroy();
        Window.destroy();

        Display.setDisplayMode(mode);
        Window.create("Test", mode.bpp, 0, 0, 0, 0);
        glInit();
        initPbuffer();

        Keyboard.create();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    //check for window key
    if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
      try {
        destroyTexture();
        Keyboard.destroy();
        pbuffer.destroy();
        Window.destroy();

        Display.resetDisplayMode();
        Window.create("Test", 50, 50, mode.width, mode.height, mode.bpp, 0, 0, 0, 0);
        glInit();
        initPbuffer();


        Keyboard.create();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    //check for speed changes
    if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
      quadVelocity.y += 0.1f;
    }
    if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
      quadVelocity.y -= 0.1f;
    }
    if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
      quadVelocity.x += 0.1f;
    }
    if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
      quadVelocity.x -= 0.1f;
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_ADD)) {
      angleRotation += 0.1f;
    }
    if (Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT)) {
      angleRotation -= 0.1f;
    }

    //throttle
    if (quadVelocity.x < -MAX_SPEED) {
      quadVelocity.x = -MAX_SPEED;
    }
    if (quadVelocity.x > MAX_SPEED) {
      quadVelocity.x = MAX_SPEED;
    }
    if (quadVelocity.y < -MAX_SPEED) {
      quadVelocity.y = -MAX_SPEED;
    }
    if (quadVelocity.y > MAX_SPEED) {
      quadVelocity.y = MAX_SPEED;
    }

    if (angleRotation < 0.0f) {
      angleRotation = 0.0f;
    }
    if (angleRotation > MAX_SPEED) {
      angleRotation = MAX_SPEED;
    }
  }

  private void destroyTexture() {
    IntBuffer buffer = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
    buffer.put(0, tex_handle);
    GL11.glDeleteTextures(buffer);
  }

  /**
   *  Cleans up the test
   */
  private void cleanup() {
    destroyTexture();
    Keyboard.destroy();
    pbuffer.destroy();
    Window.destroy();
  }

  /**
   * Retrieves a displaymode, if one such is available
   *
   * @param width Required width
   * @param height Required height
   * @param bpp Minimum required bits per pixel
   * @return
   */
  private DisplayMode findDisplayMode(int width, int height, int bpp) {
    DisplayMode[] modes = Display.getAvailableDisplayModes();
    for (int i = 0; i < modes.length; i++) {
      if (modes[i].width == width
        && modes[i].height == height
        && modes[i].bpp >= bpp) {
        return modes[i];
      }
    }
    return null;
  }

  private void initGLState(int width, int height, float color) {
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glLoadIdentity();
    GLU.gluOrtho2D(0, mode.width, 0, mode.height);
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glLoadIdentity();
    GL11.glViewport(0, 0, width, height);

    //set clear color to black
    GL11.glClearColor(color, color, color, 0.0f);
  }

  /**
   * Initializes OGL
   */
  private void glInit() {
    //sync frame (only works on windows)
    Window.setVSyncEnabled(true);
    
    GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_REPLACE);
    GL11.glEnable(GL11.GL_TEXTURE_2D);
    // Create shared texture
    IntBuffer buffer = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
    GL11.glGenTextures(buffer);
    tex_handle = buffer.get(0);
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex_handle);
    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
    initGLState(mode.width, mode.height, 0f);
  }

  /**
   * Test entry point
   */
  public static void main(String[] args) {
    System.out.println(
      "Change between fullscreen and windowed mode, by pressing F and W respectively");
    System.out.println("Move quad using arrowkeys, and change rotation using +/-");
    PbufferTest fswTest = new PbufferTest();
    fswTest.execute();
  }
}
