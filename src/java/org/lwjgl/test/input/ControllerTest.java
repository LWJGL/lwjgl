/*
 * Copyright (c) 2002-2004 Lightweight Java Game Library Project
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

import org.lwjgl.input.Controller;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Window;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * $Id$
 * <br>
 * Controller test
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class ControllerTest {
  
  /** Controller fuzz */
  public static final int FUZZ = 200;
  
  /** Direction controller has moved */
  private int direction;
  
  /** Last button pressed */
  private int lastButton = 0;
  
  /** Last direction we scrolled in */
  private int lastScrollDirection = -1;
  
  /** Width of window */
  private static int WINDOW_WIDTH = 640;
  
  /** Height of window */
  private static int WINDOW_HEIGHT = 640;
  
  /** Triangle size (in ½) */
  private Vector2f triangleSize = new Vector2f(120, 100);
  
  /** Triangle color */
  private Vector3f triangleColor[] = new Vector3f[] { 
      new Vector3f(1,1,1),
      new Vector3f(1,0,0),
      new Vector3f(0,1,0),
      new Vector3f(0,0,1)
      };
  
  private Vector3f quadColor[] = new Vector3f[] {
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
  private boolean closing = false;
  
  /** Fullscreen or not */
  public static final boolean FULLSCREEN = false;
  
  /** Creates a new instance of ControllerTest */
  public ControllerTest() {
  }

  private void initialize() {
    // create display and opengl
    setupDisplay();
  }
  
  /**
   * Setup display
   */
  private void setupDisplay() {
    try {
      if (FULLSCREEN) {
        Window.create("ControllerTest", 16, 0, 0, 0, 0);
      } else {
        Window.create("ControllerTest", 50, 50, WINDOW_WIDTH, WINDOW_HEIGHT, 16, 0, 0, 0, 0);
      }
      Window.setVSyncEnabled(true);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }

    initializeOpenGL();    
  }

  /**
   * Initializes OpenGL
   *
   */
  private void initializeOpenGL() {
    GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
  }

  /**
   * Executes the actual test
   */
  public void executeTest() {
    initialize();

    runTest();

    Window.destroy();
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
        handleController();
        
        
        // pause and continue if minimized
        if(!Window.isVisible()) {
          pause(100);
          render();
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
    Window.update();
    closing = Window.isCloseRequested();
  }
  
  /**
   * handles the controller
   */
  private void handleController() {
    readController();
  }

  /**
   * Reads the controller
   */
  private void readController() {
    // get last button down
    for(int i=0;i<Controller.getButtonCount(); i++) {
      if(Controller.isButtonDown(i)) {
        lastButton = i;
      }
    }
    
    updateState();
  }
  
  /**
   * Updates our "model"
   */
  private void updateState() {
    direction = -1;

    // get out if no movement
    if (Controller.getX() == Controller.getY() && Controller.getX() == 0) {
      if(!Controller.hasPOV()) {
        return;
      }
    }
    
    // determine direction moved
    // ============================
    if(Controller.getX() > FUZZ) {
      direction = 3;
    }
    
    if(Controller.getX() < -FUZZ) {
      direction = 1;
    }
    
    if(Controller.getY() > FUZZ) {
      direction = 2;
    }
    
    if(Controller.getY() < -FUZZ) {
      direction = 0;
    }
    // ----------------------------
    
    if(direction > -1) {

      // based on which button was last pushed, update model
      switch(lastButton) {
        case -1:
          break;
        case 1:
          triangleColor[direction].y = 1;
          break;
        case 2:
          triangleColor[direction].z = 1;
          break;
        case 3:
          triangleColor[direction].x = 1;
          triangleColor[direction].y = 1;
          triangleColor[direction].z = 1;
          break;
        case 0:   // fall through
        default:
          triangleColor[direction].x = 1;
          break;
      }
    }
    
    
    if(Controller.hasPOV()) {
      // get direction to update in
      switch(Controller.getPov()) {
        case Controller.POV_CENTER:
          return;
        case Controller.POV_SOUTH:
        case Controller.POV_EAST:
          lastScrollDirection++;
          break;
        case Controller.POV_NORTH:
        case Controller.POV_WEST:
          lastScrollDirection--;
          break;
      }
      
      // over/underflow
      if(lastScrollDirection < 0) {
        lastScrollDirection = 3;
      }
      if(lastScrollDirection > 3) {
        lastScrollDirection = 0;
      }
      
      // update colors
      quadColor[lastScrollDirection].x = (float) Math.random();
      quadColor[lastScrollDirection].y = (float) Math.random();
      quadColor[lastScrollDirection].z = (float) Math.random();
    }
  }
  
  /**
   * Handles the keyboard
   */
  private void handleKeyboard() {
    // closing on ESCAPE
    if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
      closing = true;
    }
  }
  
  /**
   * Does the "model logic"
   */
  private void logic() {
    // "we fade to black"
    // ===========================================
    for(int i=0; i<triangleColor.length; i++) {
      triangleColor[i].x -= 0.01;
      triangleColor[i].y -= 0.01;
      triangleColor[i].z -= 0.01;
    }   
  
    for(int i=0; i<quadColor.length; i++) {
      quadColor[i].x -= 0.01;
      quadColor[i].y -= 0.01;
      quadColor[i].z -= 0.01;
    }   
    // -------------------------------------------
  }
  
  /**
   * Render our triangles
   */
  private void render() {
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

    // for each triangle, render it at position, rotating degrees for each
    for(int i=0; i<triangles.length; i++) {
      GL11.glPushMatrix(); {
        GL11.glTranslatef(triangles[i].x, triangles[i].y, 0);
        GL11.glRotatef(i*90, 0, 0, 1);
        
        GL11.glColor3f(triangleColor[i].x, triangleColor[i].y, triangleColor[i].z);
        
        GL11.glBegin(GL11.GL_TRIANGLES); {
          GL11.glVertex2f(0, triangleSize.y);
          GL11.glVertex2f(-triangleSize.x, -triangleSize.y);
          GL11.glVertex2f(+triangleSize.x, -triangleSize.y);
        }
        GL11.glEnd();
      }
      GL11.glPopMatrix();
    }
    
    // paint quad in the middle (yes, wasting cpu cycles by not precalculating)
    GL11.glBegin(GL11.GL_QUADS); {
      GL11.glColor3f(quadColor[0].x, quadColor[0].y, quadColor[0].z);
      GL11.glVertex2f(WINDOW_WIDTH/2-triangleSize.x, WINDOW_HEIGHT/2-triangleSize.x);
      GL11.glColor3f(quadColor[1].x, quadColor[1].y, quadColor[1].z);
      GL11.glVertex2f(WINDOW_WIDTH/2+triangleSize.x, WINDOW_HEIGHT/2-triangleSize.x);
      GL11.glColor3f(quadColor[2].x, quadColor[2].y, quadColor[2].z);
      GL11.glVertex2f(WINDOW_WIDTH/2+triangleSize.x, WINDOW_HEIGHT/2+triangleSize.x);
      GL11.glColor3f(quadColor[3].x, quadColor[3].y, quadColor[3].z);
      GL11.glVertex2f(WINDOW_WIDTH/2-triangleSize.x, WINDOW_HEIGHT/2+triangleSize.x);
    }
    GL11.glEnd();  
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    ControllerTest ct = new ControllerTest();
    ct.executeTest();
  }
}
