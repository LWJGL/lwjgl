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
package org.lwjgl.test.input;

import org.lwjgl.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

import java.nio.*;

/**
 * $Id$
 *
 * Tests switching between windowed and fullscreen - including hardware cursor test
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class HWCursorTest {

  /** Intended deiplay mode */
  private DisplayMode mode;

  /** GL instance */
  private GL gl;

  /** GLU instance */
  private GLU glu;

  /** The native cursor */
  private static Cursor cursor = null;

  /** The mouse cursor position */
  private static int mouse_x;
  private static int mouse_y;

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
      gl.create();
      glu = new GLU(gl);

      glInit();

      Keyboard.create();
      initNativeCursor();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void initNativeCursor() {
      try {
          Mouse.create();
      } catch (Exception e) {
          e.printStackTrace();
      }
      if ((Mouse.getNativeCursorCaps() & Mouse.CURSOR_ONE_BIT_TRANSPARANCY) == 0) {
          System.out.println("No HW cursor support!");
          System.exit(0);
      }
      System.out.println("Maximum native cursor size: " + Mouse.getMaxCursorSize() + ", min size: " + Mouse.getMinCursorSize());
      mouse_x = mouse_y = 0;
      int num_images = 3;
      int image_size = Mouse.getMaxCursorSize()*Mouse.getMaxCursorSize();
      IntBuffer cursor_images = ByteBuffer.allocateDirect(num_images*image_size*4).order(ByteOrder.nativeOrder()).asIntBuffer();
      IntBuffer delays = ByteBuffer.allocateDirect(num_images*4).order(ByteOrder.nativeOrder()).asIntBuffer();
      delays.put(0, 500);
      delays.put(1, 500);
      delays.put(2, 500);
      int color_scale = 255/Mouse.getMaxCursorSize();
      int bit_mask = 0x81000000;
      for (int j = 0; j < image_size; j++) {
          if (j % 4 == 0)
              bit_mask = (~bit_mask) & 0x81000000;
          int color = (j*color_scale/Mouse.getMaxCursorSize()) << 16;
          cursor_images.put(0*image_size + j, 0x00000020 | color | bit_mask);
      }
      for (int j = 0; j < image_size; j++) {
          if (j % 4 == 0)
              bit_mask = (~bit_mask) & 0x81000000;
          int color = (j*color_scale/Mouse.getMaxCursorSize()) << 8;
          cursor_images.put(1*image_size + j, 0x00000020 | color | bit_mask);
      }
      for (int j = 0; j < image_size; j++) {
          if (j % 4 == 0)
              bit_mask = (~bit_mask) & 0x81000000;
          int color = (j*color_scale/Mouse.getMaxCursorSize());
          cursor_images.put(2*image_size + j, 0x00000020 | color | bit_mask);
      }
      try {
          if ((Mouse.getNativeCursorCaps() | Mouse.CURSOR_ANIMATION) == 0)
              num_images = 1;
          cursor = new Cursor(Mouse.getMaxCursorSize(), Mouse.getMaxCursorSize(), Mouse.getMaxCursorSize()/2, Mouse.getMaxCursorSize()/2, num_images, Sys.getDirectBufferAddress(cursor_images), Sys.getDirectBufferAddress(delays));
          Mouse.setNativeCursor(cursor);
      } catch (Exception e) {
          e.printStackTrace();
          System.exit(1);
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
  private void render() {
    //clear background
    gl.clear(GL.COLOR_BUFFER_BIT);

    // draw white quad
    gl.pushMatrix();
    {
      gl.translatef(mouse_x, 600 - mouse_y, 0);
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
  }

  /**
   * Processes keyboard input
   */
  private void processKeyboard() {
    Keyboard.poll();
    Mouse.poll();

    if (Mouse.dx != 0 || Mouse.dy != 0) {
        mouse_x += Mouse.dx;
        mouse_y += Mouse.dy;
        System.out.println("mouse_x " + mouse_x + " mouse_y " + mouse_y);
    }

    //check for fullscreen key
    if (Keyboard.isKeyDown(Keyboard.KEY_F)) {

      try {
        Keyboard.destroy();
        try {
            Mouse.setNativeCursor(null);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        cursor.destroy();
        Mouse.destroy();
        gl.destroy();

        Display.setDisplayMode(mode);
        gl = new GL("Test", mode.bpp, 0, 0, 0);
        gl.create();
        glu = new GLU(gl);

        glInit();

        Keyboard.create();
        initNativeCursor();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    //check for window key
    if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
      try {
        Keyboard.destroy();
        try {
            Mouse.setNativeCursor(null);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        cursor.destroy();
        Mouse.destroy();
        gl.destroy();

        Display.resetDisplayMode();
        gl = new GL("Test", 50, 50, mode.width, mode.height, mode.bpp, 0, 0, 0);
        gl.create();
        glu = new GLU(gl);

        glInit();

        Keyboard.create();
        initNativeCursor();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
        try {
            Mouse.setNativeCursor(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
        try {
            Mouse.setNativeCursor(cursor);
            mouse_x = mouse_y = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  }

  /**
   *  Cleans up the test
   */
  private void cleanup() {
    Keyboard.destroy();
    try {
        Mouse.setNativeCursor(null);
    } catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
    }
    cursor.destroy();
    Mouse.destroy();
    Display.resetDisplayMode();
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

  /**
   * Initializes OGL
   */
  private void glInit() {
    // Go into orthographic projection mode.
    gl.determineAvailableExtensions();
    gl.matrixMode(GL.PROJECTION);
    gl.loadIdentity();
    glu.ortho2D(0, mode.width, 0, mode.height);
    gl.matrixMode(GL.MODELVIEW);
    gl.loadIdentity();
    gl.viewport(0, 0, mode.width, mode.height);

    //set clear color to black
    gl.clearColor(0.0f, 0.0f, 0.0f, 0.0f);

    //sync frame (only works on windows)
    if (GL.WGL_EXT_swap_control) {
      GL.wglSwapIntervalEXT(1);
    }
  }

  /**
   * Test entry point
   */
  public static void main(String[] args) {
    System.out.println(
      "Change between fullscreen and windowed mode, by pressing F and W respectively. Enable hw cursor with N and disable it with M.");
    System.out.println(
      "Move quad using arrowkeys, and change rotation using +/-");
    HWCursorTest cursorTest = new HWCursorTest();
    cursorTest.execute();
  }
}
