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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import org.lwjgl.Display;
import org.lwjgl.DisplayMode;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Window;
import org.lwjgl.opengl.glu.GLU;

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

  /** The native cursor */
  private static Cursor[] cursor = null;

  /** The mouse cursor position */
  private static int mouse_x;
  private static int mouse_y;
  private static int mouse_btn = 0;

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

      glInit();

      Keyboard.create();
      Mouse.create();
      initNativeCursors();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void initNativeCursors() throws Exception {
      if ((Mouse.getNativeCursorCaps() & Mouse.CURSOR_ONE_BIT_TRANSPARENCY) == 0) {
          System.out.println("No HW cursor support!");
          System.exit(0);
      }

      cursor = new Cursor[3];
      
      // center
      mouse_x = 400;
      mouse_y = 300;
      
      int cursorImageCount = 1;
      int cursorWidth = Mouse.getMaxCursorSize();
      int cursorHeight = cursorWidth;
      IntBuffer cursorImages;
      IntBuffer cursorDelays;
      
      
      // Create a single cursor
      // ==================================
      cursorImages = ByteBuffer.allocateDirect(cursorWidth*cursorHeight*cursorImageCount*4).order(ByteOrder.nativeOrder()).asIntBuffer();
      cursorDelays = null;
      for(int j=0; j<cursorWidth; j++) {
        for(int l=0; l<cursorHeight; l++) {
        	cursorImages.put(0xffffffff);
        }
      }
      cursorImages.flip();
      cursor[0] = new Cursor(Mouse.getMaxCursorSize(), Mouse.getMaxCursorSize(), Mouse.getMaxCursorSize()/2, Mouse.getMaxCursorSize()/2, cursorImageCount, cursorImages, cursorDelays);
      // ----------------------------------
      
      // Create 3 piece animation
      // ==================================
      cursorImageCount = 3;
      cursorImages = ByteBuffer.allocateDirect(cursorWidth*cursorHeight*cursorImageCount*4).order(ByteOrder.nativeOrder()).asIntBuffer();
      cursorDelays = ByteBuffer.allocateDirect(cursorImageCount*4).order(ByteOrder.nativeOrder()).asIntBuffer();
      for(int i=0; i<cursorImageCount; i++) {
        
        // make a colored square with a chocolate center 
        int offColor = 0x00000000;        
        int onColor = 0xffff0000;
        
        // change color according to cursor
        if(i == 1) {
          onColor = 0xff00ff00;
        } else if (i == 2) {
          onColor = 0xff0000ff;
        }
        
        // calculate size of center
        int centerSize  = (cursorWidth / 5) * (i + 1);
        int centerLeft  = cursorWidth / 2 - centerSize / 2;
        int centerRight = cursorWidth / 2 + centerSize / 2;
        
        // go!
      	for(int j=0; j<cursorWidth; j++) {
      		for(int l=0; l<cursorHeight; l++) {
            if(j >= centerLeft && j < centerRight && l >= centerLeft && l < centerRight) {
              cursorImages.put(offColor);
            } else {
              cursorImages.put(onColor);
            }
      		}
      	}
      }
      cursorDelays.put(2000).put(2000).put(2000);
      cursorDelays.flip();
      cursorImages.flip();
      
      cursor[1] = new Cursor(Mouse.getMaxCursorSize(), Mouse.getMaxCursorSize(), Mouse.getMaxCursorSize()/2, Mouse.getMaxCursorSize()/2, cursorImageCount, cursorImages, cursorDelays);
      // ----------------------------------
      
      
      // Create a 20 piece animation
      // ==================================
      cursorImageCount = 20;
      cursorImages = ByteBuffer.allocateDirect(cursorWidth*cursorHeight*cursorImageCount*4).order(ByteOrder.nativeOrder()).asIntBuffer();
      cursorDelays = ByteBuffer.allocateDirect(cursorImageCount*4).order(ByteOrder.nativeOrder()).asIntBuffer();
      cursorDelays.put(
          new int[] { 
              100, 100, 100, 100, 100,
              100, 100, 100, 100, 100,
              100, 100, 100, 100, 100,
              100, 100, 100, 100, 100
          });
      
      float step = 0xffffffff / 20.0f;
      for(int i=0; i<cursorImageCount; i++) {
        for(int j=0; j<cursorWidth; j++) {
          for(int l=0; l<cursorHeight; l++) {
            cursorImages.put((int)step);
          }
        }
        step += step;
      }
      cursorImages.flip();
      cursorDelays.flip();
      cursor[2] = new Cursor(Mouse.getMaxCursorSize(), Mouse.getMaxCursorSize(), Mouse.getMaxCursorSize()/2, Mouse.getMaxCursorSize()/2, cursorImageCount, cursorImages, cursorDelays);      
      // ----------------------------------
      
      Mouse.setNativeCursor(cursor[0]);
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

        render();
      } else {

        // no need to render/paint if nothing has changed (ie. window dragged over)
        if (Window.isDirty()) {
          render();
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
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

    // draw white quad
    GL11.glPushMatrix();
    {
      GL11.glTranslatef(mouse_x, mouse_y, 0);
      GL11.glColor3f(1.0f, 1.0f, 1.0f);
      GL11.glBegin(GL11.GL_QUADS);
      {
        GL11.glColor3f(1.0f, 0.0f, 0.0f); GL11.glVertex2i(-50, -50);
        GL11.glColor3f(0.0f, 1.0f, 0.0f); GL11.glVertex2i(50, -50);
        GL11.glColor3f(0.0f, 0.0f, 1.0f); GL11.glVertex2i(50, 50);
        GL11.glColor3f(1.0f, 0.0f, 1.0f); GL11.glVertex2i(-50, 50);
      }
      GL11.glEnd();
    }
    GL11.glPopMatrix();
  }

  /**
   * Processes keyboard input
   */
  private void processKeyboard() {
    Keyboard.poll();
    Mouse.poll();

    if (Mouse.getDX() != 0 || Mouse.getDY() != 0) {
        mouse_x += Mouse.getDX() / 2;
        mouse_y += Mouse.getDY() / 2;
    }
    
    if(Mouse.isButtonDown(0)) {
    	mouse_btn = 0;
    } else if(Mouse.isButtonDown(1)) {
      mouse_btn = 1;
    } else if(Mouse.isButtonDown(2)) {
      mouse_btn = 2;
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
        for(int i=0; i<cursor.length; i++) {
        	cursor[i].destroy();
        }
        Mouse.destroy();
        Window.destroy();

        Display.setDisplayMode(mode);
        Window.create("Test", mode.bpp, 0, 0, 0, 0);
        
        glInit();

        Keyboard.create();
        initNativeCursors();
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
        for(int i=0; i<cursor.length; i++) {
          cursor[i].destroy();
        }
        Mouse.destroy();
        Window.destroy();

        Display.resetDisplayMode();
        Window.create("Test", 50, 50, mode.width, mode.height, mode.bpp, 0, 0, 0, 0);

        glInit();

        Keyboard.create();
        initNativeCursors();
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
            cursor[mouse_btn].resetAnimation();
            Mouse.setNativeCursor(cursor[mouse_btn]);
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
    for(int i=0; i<cursor.length; i++) {
      cursor[i].destroy();
    }
    Mouse.destroy();
    Display.resetDisplayMode();
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
    GL11.glMatrixMode(GL11.GL_PROJECTION);
    GL11.glLoadIdentity();
    GLU.gluOrtho2D(0, mode.width, 0, mode.height);
    GL11.glMatrixMode(GL11.GL_MODELVIEW);
    GL11.glLoadIdentity();
    GL11.glViewport(0, 0, mode.width, mode.height);

    //set clear color to black
    GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

    //sync frame (only works on windows)
    Window.setVSyncEnabled(true);
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
