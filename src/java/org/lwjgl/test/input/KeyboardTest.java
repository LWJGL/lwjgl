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
package org.lwjgl.test.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 * <br>
 * Keyboard test
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class KeyboardTest {

  /** position of quad to draw */
  private Vector2f position = new Vector2f(320.0f, 240.0f);

  /** Display mode selected */
  private DisplayMode displayMode;

  /** Creates a new instance of MouseTest */
  public KeyboardTest() {
  }

  private void initialize() {
    // create display and opengl
    setupDisplay(false);

    try {
      Keyboard.create();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  /**
   * Sets the display mode for fullscreen mode
   */
  protected boolean setDisplayMode() {
    try {
		// get modes
		DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(640, 480, -1, -1, -1, -1, 60, 60);

      org.lwjgl.util.Display.setDisplayMode(dm, new String[] {
          "width=" + 640,
          "height=" + 480,
          "freq=" + 60,
          "bpp=" + org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel()
         });
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return false;
  }

  private void setupDisplay(boolean fullscreen) {
    try {
      setDisplayMode();
      Display.create();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }

    initializeOpenGL();
  }

  private void initializeOpenGL() {
	  glMatrixMode(GL_PROJECTION);
	  glLoadIdentity();
	  gluOrtho2D(0, Display.getDisplayMode().getWidth(), 0, Display.getDisplayMode().getHeight());
	  glMatrixMode(GL_MODELVIEW);
	  glLoadIdentity();
	  glViewport(0, 0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());
	  glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
  }

  public void executeTest() {
    initialize();

    createKeyboard();

    wiggleKeyboard();

    Keyboard.destroy();
    Display.destroy();
  }

  private void createKeyboard() {
    try {
      Keyboard.create();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  private void wiggleKeyboard() {

    while (!Display.isCloseRequested()) {
      Display.update();

      if (!Display.isVisible()) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException inte) {
          inte.printStackTrace();
        }
        continue;
      }

      //check keys, buffered
      Keyboard.poll();

      int count = Keyboard.getNumKeyboardEvents();
      while (Keyboard.next()) {
		  int character_code = ((int)Keyboard.getEventCharacter()) & 0xffff;
        System.out.println("Checking key:" + Keyboard.getKeyName(Keyboard.getEventKey()));
        System.out.println("Pressed:" + Keyboard.getEventKeyState());
        System.out.println("Key character code: 0x" + Integer.toHexString(character_code));
        System.out.println("Key character: " + Keyboard.getEventCharacter());
        System.out.println("Repeat event: " + Keyboard.isRepeatEvent());

        if (Keyboard.getEventKey() == Keyboard.KEY_R && Keyboard.getEventKeyState()) {
          Keyboard.enableRepeatEvents(!Keyboard.areRepeatEventsEnabled());
        }
        if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
          return;
        }
      }
      if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
        position.x += 1;
      }

      if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
        position.x -= 1;
      }

      if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
        position.y += 1;
      }

      if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
        position.y -= 1;
      }

      if (count > 0) {
        System.out.println();
      }

      if (position.x < 0) {
        position.x = 0;
      } else if (position.x > 640 - 60) {
        position.x = 640 - 60;
      }

      if (position.y < 0) {
        position.y = 0;
      } else if (position.y > 480 - 30) {
        position.y = 480 - 30;
      }

      render();

      Display.sync(60);
    }
  }

  private void render() {
    glClear(GL_COLOR_BUFFER_BIT);

    glBegin(GL_POLYGON);
    {
      float color = 1.0f;
      glColor3f(color, color, color);

      glVertex2f(position.x + 0.0f, position.y + 0.0f);
      glVertex2f(position.x + 0.0f, position.y + 30.0f);
      glVertex2f(position.x + 40.0f, position.y + 30.0f);
      glVertex2f(position.x + 60.0f, position.y + 15.f);
      glVertex2f(position.x + 40.0f, position.y + 0.0f);
    }
    glEnd();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    KeyboardTest kt = new KeyboardTest();
    kt.executeTest();
    System.exit(0);
  }
}
