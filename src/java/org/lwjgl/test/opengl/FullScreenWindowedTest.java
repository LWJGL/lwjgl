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

/**
 * $Id$
 *
 * Tests switching between windowed and fullscreen
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class FullScreenWindowedTest {

  /** Intended deiplay mode */
  private DisplayMode mode;

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

  /**
   * Creates a FullScreenWindowedTest
   */
  public FullScreenWindowedTest() {
  }

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
      Window.create("Test", 50, 50, mode.width, mode.height, mode.bpp, 0, 0, 0);

      glInit();

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
      Window.update();

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
  }

  /**
   * Processes keyboard input
   */
  private void processKeyboard() {
    Keyboard.poll();

    //check for fullscreen key
    if (Keyboard.isKeyDown(Keyboard.KEY_F)) {

      try {
        Keyboard.destroy();
        Window.destroy();

        Display.setDisplayMode(mode);
        Window.create("Test", mode.bpp, 0, 0, 0);

        glInit();

        Keyboard.create();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    //check for window key
    if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
      try {
        Keyboard.destroy();
        Window.destroy();

        Display.resetDisplayMode();
        Window.create("Test", 50, 50, mode.width, mode.height, mode.bpp, 0, 0, 0);

        glInit();

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

  /**
   *  Cleans up the test
   */
  private void cleanup() {
    Keyboard.destroy();
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

  /**
   * Initializes OGL
   */
  private void glInit() {
    // Go into orthographic projection mode.
    GL.glMatrixMode(GL.GL_PROJECTION);
    GL.glLoadIdentity();
    GLU.gluOrtho2D(0, mode.width, 0, mode.height);
    GL.glMatrixMode(GL.GL_MODELVIEW);
    GL.glLoadIdentity();
    GL.glViewport(0, 0, mode.width, mode.height);

    //set clear color to black
    GL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

    //sync frame (only works on windows)
    GLCaps.determineAvailableExtensions();
    if (GLCaps.WGL_EXT_swap_control) {
      GL.wglSwapIntervalEXT(1);
    }
  }

  /**
   * Test entry point
   */
  public static void main(String[] args) {
    System.out.println(
      "Change between fullscreen and windowed mode, by pressing F and W respectively");
    System.out.println(
      "Move quad using arrowkeys, and change rotation using +/-");
    FullScreenWindowedTest fswTest = new FullScreenWindowedTest();
    fswTest.execute();
  }
}
