/*
 * Copyright (c) 2003 Lightweight Java Game Library Project
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
 * * Neither the name of 'Lightweight Java Game Library' nor the names of
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

import org.lwjgl.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.lwjgl.vector.Vector2f;

import java.nio.*;

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

  /** GL instance */
  private GLWindow gl;

  /** GLU instance */
  private GLU glu;

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
      gl = new GLWindow("Test", 50, 50, mode.width, mode.height, mode.bpp, 0, 0, 0);
//      gl = new GLWindow("Test", 50, 50, mode.width, mode.height, mode.bpp, 0, 0, 0);
      gl.create();
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
    while (!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)
      && !Window.isCloseRequested()) {
      // allow subsystem to get a chance to run too
      Window.tick();

      if (!Window.isMinimized()) {
        // check keyboard input
        processKeyboard();

        // do "game" logic, and render it
        logic();
        render();

        // paint window
        Window.paint();
      } else {

        // no need to render/paint if nothing has changed (ie. window dragged over)
        if (Window.isDirty()) {
          render();
          Window.paint();
        }

        // don't waste cpu time, sleep more
        try {
          Thread.sleep(100);
        } catch (InterruptedException inte) {
        }
      }
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
        Pbuffer.releaseContext();
        pbuffer.destroy();
        initPbuffer();
    }
    pbuffer.makeCurrent();
    // Pbuffer rendering
    //clear background
    GL.glClear(GL.GL_COLOR_BUFFER_BIT);

    // draw white quad
    GL.glPushMatrix();
    {
      GL.glTranslatef(quadPosition.x, quadPosition.y, 0);
      GL.glRotatef(angle, 0.0f, 0.0f, 1.0f);
      GL.glColor3f(1.0f, 1.0f, 1.0f);
      GL.glBegin(GL.GL_QUADS);
      {
        GL.glVertex2i(-50, -50);
        GL.glVertex2i(50, -50);
        GL.glVertex2i(50, 50);
        GL.glVertex2i(-50, 50);
      }
      GL.glEnd();
    }
    GL.glPopMatrix();
    GL.glCopyTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, 0, 0, 256, 256, 0);
    Pbuffer.releaseContext();

    // OpenGL window rendering
    GL.glClear(GL.GL_COLOR_BUFFER_BIT);
    // draw white quad
    GL.glPushMatrix();
    {
      GL.glTranslatef(quadPosition.x, quadPosition.y, 0);
      GL.glRotatef(angle, 0.0f, 0.0f, 1.0f);
      GL.glColor3f(1.0f, 1.0f, 0.0f);
      GL.glBegin(GL.GL_QUADS);
      {
        GL.glTexCoord2f(0f, 0f);
        GL.glVertex2i(-50, -50);
        GL.glTexCoord2f(1f, 0f);
        GL.glVertex2i(50, -50);
        GL.glTexCoord2f(1f, 1f);
        GL.glVertex2i(50, 50);
        GL.glTexCoord2f(0f, 1f);
        GL.glVertex2i(-50, 50);
      }
      GL.glEnd();
    }
    GL.glPopMatrix();
  }

  private void initPbuffer() {
      try {
          pbuffer = new Pbuffer(256, 256, mode.bpp, 0, 0, 0);
          pbuffer.makeCurrent();
          initGLState(256, 256, 0.5f);
          GL.glBindTexture(GL.GL_TEXTURE_2D, tex_handle);
          Pbuffer.releaseContext();
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
	Pbuffer.releaseContext();
	pbuffer.destroy();
        gl.destroy();

        Display.setDisplayMode(mode);
        gl = new GLWindow("Test", mode.bpp, 0, 0, 0);
        gl.create();
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
	Pbuffer.releaseContext();
	pbuffer.destroy();
        gl.destroy();

        Display.resetDisplayMode();
        gl = new GLWindow("Test", 50, 50, mode.width, mode.height, mode.bpp, 0, 0, 0);
        gl.create();
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
    GL.glDeleteTextures(buffer);
  }

  /**
   *  Cleans up the test
   */
  private void cleanup() {
    destroyTexture();
    Keyboard.destroy();
    Pbuffer.releaseContext();
    pbuffer.destroy();
    gl.destroy();
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
    GL.glMatrixMode(GL.GL_PROJECTION);
    GL.glLoadIdentity();
    GLU.gluOrtho2D(0, mode.width, 0, mode.height);
    GL.glMatrixMode(GL.GL_MODELVIEW);
    GL.glLoadIdentity();
    GL.glViewport(0, 0, width, height);

    //set clear color to black
    GL.glClearColor(color, color, color, 0.0f);
  }

  /**
   * Initializes OGL
   */
  private void glInit() {
    //sync frame (only works on windows)
    GLCaps.determineAvailableExtensions();
    if (GLCaps.WGL_EXT_swap_control) {
      GL.wglSwapIntervalEXT(1);
    }
    GL.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
    GL.glEnable(GL.GL_TEXTURE_2D);
    // Create shared texture
    IntBuffer buffer = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
    GL.glGenTextures(buffer);
    tex_handle = buffer.get(0);
    GL.glBindTexture(GL.GL_TEXTURE_2D, tex_handle);
    GL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
    GL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
    GL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
    GL.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
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
