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
  private GL gl;

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
      gl = new GL("Test", 50, 50, mode.width, mode.height, mode.bpp, 0, 0, 0);
//      gl = new GL("Test", 50, 50, mode.width, mode.height, mode.bpp, 0, 0, 0);
      gl.create();
      glu = new GLU(gl);
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
      && !gl.isCloseRequested()) {
      // allow subsystem to get a chance to run too
      gl.tick();

      if (!gl.isMinimized()) {
        // check keyboard input
        processKeyboard();

        // do "game" logic, and render it
        logic();
        render();

        // paint window
        gl.paint();
      } else {

        // no need to render/paint if nothing has changed (ie. window dragged over)
        if (gl.isDirty()) {
          render();
          gl.paint();
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
    gl.clear(GL.COLOR_BUFFER_BIT);

    // draw white quad
    gl.pushMatrix();
    {
      gl.translatef(quadPosition.x, quadPosition.y, 0);
      gl.rotated(angle, 0.0f, 0.0f, 1.0f);
      gl.color3f(1.0f, 1.0f, 1.0f);
      gl.begin(GL.QUADS);
      {
        gl.vertex2i(-50, -50);
        gl.vertex2i(50, -50);
        gl.vertex2i(50, 50);
        gl.vertex2i(-50, 50);
      }
      gl.end();
    }
    gl.popMatrix();
    gl.copyTexImage2D(GL.TEXTURE_2D, 0, GL.RGB, 0, 0, 256, 256, 0);
    Pbuffer.releaseContext();

    // OpenGL window rendering
    gl.clear(GL.COLOR_BUFFER_BIT);
    // draw white quad
    gl.pushMatrix();
    {
      gl.translatef(quadPosition.x, quadPosition.y, 0);
      gl.rotated(angle, 0.0f, 0.0f, 1.0f);
      gl.color3f(1.0f, 1.0f, 0.0f);
      gl.begin(GL.QUADS);
      {
        gl.texCoord2f(0f, 0f);
        gl.vertex2i(-50, -50);
        gl.texCoord2f(1f, 0f);
        gl.vertex2i(50, -50);
        gl.texCoord2f(1f, 1f);
        gl.vertex2i(50, 50);
        gl.texCoord2f(0f, 1f);
        gl.vertex2i(-50, 50);
      }
      gl.end();
    }
    gl.popMatrix();
  }

  private void initPbuffer() {
      try {
          pbuffer = new Pbuffer(256, 256, mode.bpp, 0, 0, 0);
          pbuffer.makeCurrent();
          initGLState(256, 256, 0.5f);
          gl.bindTexture(GL.TEXTURE_2D, tex_handle);
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
        gl = new GL("Test", mode.bpp, 0, 0, 0);
        gl.create();
        glInit();
        initPbuffer();
        glu = new GLU(gl);

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
        gl = new GL("Test", 50, 50, mode.width, mode.height, mode.bpp, 0, 0, 0);
        gl.create();
        glInit();
        initPbuffer();
        glu = new GLU(gl);


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
    gl.deleteTextures(1, Sys.getDirectBufferAddress(buffer));
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
    gl.matrixMode(GL.PROJECTION);
    gl.loadIdentity();
    glu.ortho2D(0, mode.width, 0, mode.height);
    gl.matrixMode(GL.MODELVIEW);
    gl.loadIdentity();
    gl.viewport(0, 0, width, height);

    //set clear color to black
    gl.clearColor(color, color, color, 0.0f);
  }

  /**
   * Initializes OGL
   */
  private void glInit() {
    // Go into orthographic projection mode.
    gl.determineAvailableExtensions();
    //sync frame (only works on windows)
    if (GL.WGL_EXT_swap_control) {
      GL.wglSwapIntervalEXT(1);
    }
    gl.texEnvf(GL.TEXTURE_ENV, GL.TEXTURE_ENV_MODE, GL.REPLACE);
    gl.enable(GL.TEXTURE_2D);
    // Create shared texture
    IntBuffer buffer = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
    gl.genTextures(1, Sys.getDirectBufferAddress(buffer));
    tex_handle = buffer.get(0);
    gl.bindTexture(GL.TEXTURE_2D, tex_handle);
    gl.texParameteri(GL.TEXTURE_2D, GL.TEXTURE_WRAP_S, GL.CLAMP);
    gl.texParameteri(GL.TEXTURE_2D, GL.TEXTURE_WRAP_T, GL.CLAMP);
    gl.texParameteri(GL.TEXTURE_2D, GL.TEXTURE_MIN_FILTER, GL.LINEAR);
    gl.texParameteri(GL.TEXTURE_2D, GL.TEXTURE_MAG_FILTER, GL.LINEAR);
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
