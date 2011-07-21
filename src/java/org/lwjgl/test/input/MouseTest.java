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
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 * <br>
 * Mouse test
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class MouseTest {
  /** Direction mouse has moved */
  private int direction;

  /** Last button pressed */
  private int lastButton;

  /** Last direction we scrolled in */
  private int lastScrollDirection = -1;

  /** Width of window */
  private static int WINDOW_WIDTH = 640;

  /** Height of window */
  private static int WINDOW_HEIGHT = 640;

  /** Triangle size */
  private Vector2f triangleSize = new Vector2f(120, 100);

  /** Triangle color */
  private Vector3f triangleColors[] = new Vector3f[] {
      new Vector3f(1,1,1),
      new Vector3f(1,0,0),
      new Vector3f(0,1,0),
      new Vector3f(0,0,1)
      };

  private Vector3f quadColors[] = new Vector3f[] {
      new Vector3f(1,1,1),
      new Vector3f(1,0,0),
      new Vector3f(0,1,0),
      new Vector3f(0,0,1)
  };

  /** Triangles to paint */
  private Vector2f[] triangles = {
      new Vector2f(WINDOW_WIDTH/2, WINDOW_HEIGHT - triangleSize.y),
      new Vector2f(triangleSize.y, WINDOW_HEIGHT/2),
      new Vector2f(WINDOW_WIDTH/2, triangleSize.y),
      new Vector2f(WINDOW_WIDTH-triangleSize.y, WINDOW_HEIGHT/2)
      };

  /** Whether the test is closing */
  private boolean closing;

  /** Fullscreen or not */
  public static final boolean FULLSCREEN = false;

  /** Creates a new instance of MouseTest */
  public MouseTest() {
  }

  private void initialize() {
    // create display and opengl
    setupDisplay();

    setupMouse();
    setupKeyboard();
  }

  /**
   * Setup display
   */
  private void setupDisplay() {
    try {
      setDisplayMode();
      Display.setFullscreen(FULLSCREEN);
      Display.setVSyncEnabled(true);
      Display.create();
      Mouse.setGrabbed(true);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }

    initializeOpenGL();
  }

  /**
   * Sets the display mode for fullscreen mode
   */
  protected boolean setDisplayMode() {
    // get modes
    DisplayMode dm = new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT);

    try {
      Display.setDisplayMode(dm);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return false;
  }

  /**
   * Initializes OpenGL
   *
   */
  private void initializeOpenGL() {
	  glMatrixMode(GL_PROJECTION);
	  glLoadIdentity();
	  gluOrtho2D(0, Display.getDisplayMode().getWidth(), 0, Display.getDisplayMode().getHeight());
	  glMatrixMode(GL_MODELVIEW);
	  glLoadIdentity();
	  glViewport(0, 0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());
    glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
  }

  /**
   * Executes the actual test
   */
  public void executeTest() {
    initialize();

    runTest();

    Display.destroy();
  }

  /**
   * Creates the mouse
   */
  private void setupMouse() {
  }

  /**
   * Creates the keyboard
   */
  private void setupKeyboard() {
  }

  /**
   * Runs the test
   */
  private void runTest() {
    // while not exiting
    while (!closing) {
      handleWindow();

      // secondary check
      if(!closing) {

        // poll and check keyboard and mouse
        handleKeyboard();
        handleMouse();

        // pause and continue if minimized
        if(!Display.isVisible()) {
          if(Display.isDirty()) {
            render();
          }
          pause(100);
          continue;
        }

        // render and flip
        logic();
        render();
      }
      Thread.yield();
    }
  }

  /**
   * Pauses the current thread for a specified time
   *
   * @param time milliseconds to pause
   */
  private void pause(long time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException inte) {
      inte.printStackTrace();
    }
  }

  /**
   * Handles the window
   */
  private void handleWindow() {
    Display.update();
    closing = Display.isCloseRequested();
  }

  /**
   * handles the mouse
   */
  private void handleMouse() {
  	readBufferedMouse();

  	  	Display.setTitle("x: " + Mouse.getX() + ", y: " + Mouse.getY() + ", [0]: " + Mouse.isButtonDown(0) + ", [1]: " + Mouse.isButtonDown(1) + ", [2]: " + Mouse.isButtonDown(2) + ", inside: " + Mouse.isInsideWindow());
  }

  /**
   * reads a mouse in buffered mode
   */
  private void readBufferedMouse() {
    // iterate all events, use the last button down
    while(Mouse.next()) {
      if(Mouse.getEventButton() != -1 && Mouse.getEventButtonState()) {
        lastButton = Mouse.getEventButton();
      }
    }

    updateState();
  }

  /**
   * Updates our "model"
   *
   */
  private void updateState() {
    direction = -1;

    int dx = Mouse.getDX();
    int dy = Mouse.getDY();
    int dw = Mouse.getDWheel();


    // get out if no movement
    if (dx == dy && dx == 0 && dw == 0) {
      return;
    }

    // determine direction moved
    // ============================
    if(dx > 0) {
      direction = 3;
    }

    if(dx < 0) {
      direction = 1;
    }

    if(dy > 0) {
      direction = 0;
    }

    if(dy < 0) {
      direction = 2;
    }

    // ----------------------------
    if(direction > -1) {

      // based on which button was last pushed, update model
      switch(lastButton) {
        case -1:
          break;
        case 1:
          triangleColors[direction].y = 1;
          break;
        case 2:
          triangleColors[direction].z = 1;
          break;
        case 3:
          triangleColors[direction].x = 1;
          triangleColors[direction].y = 1;
          triangleColors[direction].z = 1;
          break;
        case 0:   // fall through
        default:
          triangleColors[direction].x = 1;
          break;
      }
    }

    // get direction to update in
    if (dw > 0) {
      lastScrollDirection++;
    } else if (dw < 0) {
      lastScrollDirection--;
    } else if (dw == 0) {
      return;
    }

    // over/underflow
    if(lastScrollDirection < 0) {
      lastScrollDirection = 3;
    }
    if(lastScrollDirection > 3) {
      lastScrollDirection = 0;
    }

    // update colors
    quadColors[lastScrollDirection].x = (float) Math.random();
    quadColors[lastScrollDirection].y = (float) Math.random();
    quadColors[lastScrollDirection].z = (float) Math.random();
  }

  /**
   * Handles the keyboard
   */
  private void handleKeyboard() {

    while(Keyboard.next()) {
      // closing on ESCAPE
      if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState()) {
        closing = true;
      }

      if(Keyboard.getEventKey() == Keyboard.KEY_SPACE && Keyboard.getEventKeyState()) {
      	Mouse.setGrabbed(!Mouse.isGrabbed());
      }

      if(Keyboard.getEventKey() == Keyboard.KEY_R && Keyboard.getEventKeyState()) {
        	Display.setResizable(!Display.isResizable());
        }
    }
  }

  /**
   * Does the "model logic"
   */
  private void logic() {
    // "we fade to black"
    // ===========================================
	  for ( Vector3f color : triangleColors ) {
		  color.x -= 0.01;
		  color.y -= 0.01;
		  color.z -= 0.01;
	  }

	  for ( Vector3f color : quadColors ) {
		  color.x -= 0.01;
		  color.y -= 0.01;
		  color.z -= 0.01;
	  }
    // -------------------------------------------
  }

  /**
   * Render our triangles
   */
  private void render() {
    glClear(GL_COLOR_BUFFER_BIT);

    // for each triangle, render it at position, rotating degrees for each
    for(int i=0; i<triangles.length; i++) {
      glPushMatrix(); {
        glTranslatef(triangles[i].x, triangles[i].y, 0);
        glRotatef(i*90, 0, 0, 1);

        glColor3f(triangleColors[i].x, triangleColors[i].y, triangleColors[i].z);

        glBegin(GL_TRIANGLES); {
          glVertex2f(0, triangleSize.y);
          glVertex2f(-triangleSize.x, -triangleSize.y);
          glVertex2f(+triangleSize.x, -triangleSize.y);
        }
        glEnd();
      }
      glPopMatrix();
    }

    // paint quad in the middle (yes, wasting cpu cycles by not precalculating)
    glBegin(GL_QUADS); {
      glColor3f(quadColors[0].x, quadColors[0].y, quadColors[0].z);
      glVertex2f(WINDOW_WIDTH/2-triangleSize.x, WINDOW_HEIGHT/2-triangleSize.x);
      glColor3f(quadColors[1].x, quadColors[1].y, quadColors[1].z);
      glVertex2f(WINDOW_WIDTH/2+triangleSize.x, WINDOW_HEIGHT/2-triangleSize.x);
      glColor3f(quadColors[2].x, quadColors[2].y, quadColors[2].z);
      glVertex2f(WINDOW_WIDTH/2+triangleSize.x, WINDOW_HEIGHT/2+triangleSize.x);
      glColor3f(quadColors[3].x, quadColors[3].y, quadColors[3].z);
      glVertex2f(WINDOW_WIDTH/2-triangleSize.x, WINDOW_HEIGHT/2+triangleSize.x);
    }
    glEnd();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    MouseTest mt = new MouseTest();
    mt.executeTest();
    System.exit(0);
  }
}
