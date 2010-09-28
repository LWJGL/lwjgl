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
package org.lwjgl.test.openal;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.WaveData;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 * <br>
 * This test demonstrates OpenAL positioning Based on the example by Chad Armstrong
 * (http://www.edenwaith.com/products/pige/tutorials/openal.php)
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class PositionTest extends BasicTest {

  /** *Small* glut implementation :) */
  private GLUT glut;

  /** Width of window */
  public static final int WINDOW_WIDTH = 640;

  /** Height of window */
  public static final int WINDOW_HEIGHT = 480;

  /** LEFT enumeration */
  public static final int LEFT = 0;

  /** CENTER enumeration */
  public static final int CENTER = 1;

  /** RIGHT enumeration */
  public static final int RIGHT = 2;

  /** Whether the demo is done */
  private boolean finished;

  /** Whether in pause mode */
  private boolean pauseMode;

  // OpenAL stuff
  // ===================================================

  /** OpenAL buffers */
  private IntBuffer soundBuffers = BufferUtils.createIntBuffer(3);

  /** OpenAL sources */
  private IntBuffer soundSources = BufferUtils.createIntBuffer(3);

  /** Position of listener */
  private FloatBuffer listenerPosition = createFloatBuffer(new float[] { 0.0f, 0.0f, 0.0f });

  /** Velocity of listener */
  private FloatBuffer listenerVelocity = createFloatBuffer(new float[] { 0.0f, 0.0f, 0.0f });

  /** Orientation of listener */
  private FloatBuffer listenerOrientation =
    createFloatBuffer(new float[] { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f });

  /** Position of left sound */
  private FloatBuffer leftPosition = createFloatBuffer(new float[] { -2.0f, 0.0f, 0.0f });

  /** Velocity of left sound */
  private FloatBuffer leftVelocity = createFloatBuffer(new float[] { 0.0f, 0.0f, 0.0f });

  /** Position of center sound */
  private FloatBuffer centerPosition = createFloatBuffer(new float[] { 0.0f, 0.0f, -4.0f });

  /** Velocity of center sound */
  private FloatBuffer centerVelocity = createFloatBuffer(new float[] { 0.0f, 0.0f, 0.0f });

  /** Position of right sound */
  private FloatBuffer rightPosition = createFloatBuffer(new float[] { 2.0f, 0.0f, 0.0f });

  /** Velocity of right sound */
  private FloatBuffer rightVelocity = createFloatBuffer(new float[] { 0.0f, 0.0f, 0.0f });
  // ---------------------------------------------------

  /**
   * Runs the actual test, using supplied arguments
   */
  protected void execute(String[] args) {
    // Setup needed stuff
    try {
      setup();
    } catch (Exception e) {
      System.out.println("Error setting up demonstration: ");
      e.printStackTrace();
      System.exit(-1);
    }

    // run the actual demonstration
    run();

    // shutdown
    shutdown();
  }

  /**
   * Performs setup of demonstration
   */
  private void setup() throws Exception {

    // Setup Window
    // =====================================================
    LWJGLUtil.log("Setting up window");

    // calc center
    int centerX = (Display.getDisplayMode().getWidth() - WINDOW_WIDTH) / 2;
    int centerY = (Display.getDisplayMode().getHeight() - WINDOW_HEIGHT) / 2;

    // setup window
    setDisplayMode();
    Display.create();
    // -----------------------------------------------------

    // Setup OpenGL
    // =====================================================
    LWJGLUtil.log("Setting up OpenGL");

    glViewport(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(50.0f, (float) WINDOW_WIDTH / WINDOW_HEIGHT, 0.0f, 50.0f);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    glTranslatef(0.0f, 0.0f, -6.6f);
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    glut = this.new GLUT();

    Display.setVSyncEnabled(true);
    // -----------------------------------------------------

    // Setup OpenAL
    // =====================================================
    LWJGLUtil.log("Setting up OpenAL");

    alListener(AL_POSITION, listenerPosition);
    alListener(AL_VELOCITY, listenerVelocity);
    alListener(AL_ORIENTATION, listenerOrientation);

    // creating buffers
    LWJGLUtil.log("Creating buffers");
    alGenBuffers(soundBuffers);
    soundBuffers.rewind();

    // creating sources
    alGenSources(soundSources);
    soundSources.rewind();

    // load sound files (left, center, right).wav
    LWJGLUtil.log("Loading soundfiles...");

    LWJGLUtil.log("Loading left.wav");
    WaveData left = WaveData.create("left.wav");
    alBufferData(soundBuffers.get(LEFT), left.format, left.data, left.samplerate);
    alSourcef(soundSources.get(LEFT), AL_PITCH, 1.0f);
    alSourcef(soundSources.get(LEFT), AL_GAIN, 1.0f);
    alSource(soundSources.get(LEFT), AL_POSITION, leftPosition);
    alSource(soundSources.get(LEFT), AL_VELOCITY, leftVelocity);
    alSourcei(soundSources.get(LEFT), AL_BUFFER, soundBuffers.get(LEFT));
    alSourcei(soundSources.get(LEFT), AL_LOOPING, AL_TRUE);

    LWJGLUtil.log("Loading center.wav");
    WaveData center = WaveData.create("center.wav");
    alBufferData(soundBuffers.get(CENTER), center.format, center.data, center.samplerate);
    alSourcef(soundSources.get(CENTER), AL_PITCH, 1.0f);
    alSourcef(soundSources.get(CENTER), AL_GAIN, 1.0f);
    alSource(soundSources.get(CENTER), AL_POSITION, centerPosition);
    alSource(soundSources.get(CENTER), AL_VELOCITY, centerVelocity);
    alSourcei(soundSources.get(CENTER), AL_BUFFER, soundBuffers.get(CENTER));
    alSourcei(soundSources.get(CENTER), AL_LOOPING, AL_TRUE);

    LWJGLUtil.log("Loading right.wav");
    WaveData right = WaveData.create("right.wav");
    alBufferData(soundBuffers.get(RIGHT), right.format, right.data, right.samplerate);
    alSourcef(soundSources.get(RIGHT), AL_PITCH, 1.0f);
    alSourcef(soundSources.get(RIGHT), AL_GAIN, 1.0f);
    alSource(soundSources.get(RIGHT), AL_POSITION, rightPosition);
    alSource(soundSources.get(RIGHT), AL_VELOCITY, rightVelocity);
    alSourcei(soundSources.get(RIGHT), AL_BUFFER, soundBuffers.get(RIGHT));
    alSourcei(soundSources.get(RIGHT), AL_LOOPING, AL_TRUE);

    LWJGLUtil.log("Soundfiles loaded successfully");
    // -----------------------------------------------------

    Mouse.setGrabbed(true);
  }

  /**
   * Runs the actual demonstration
   */
  private void run() {
    boolean firstRun = true;

    System.out.println("Press 1/4 (left), 2/5 (center) or 3/6 (right) to toggle sound");
    System.out.println("Press LEFT/RIGHT to move along x axis");
    System.out.println("Press SHIFT and either UP/DOWN to move along y axis");
    System.out.println("Press UP/DOWN to move along z axis");
    System.out.println("Move along the x and y axis with the mouse");
    System.out.println("Press LEFT or RIGHT mouse button to move along z axis");
    System.out.println("Press ESC to exit demo");

    LWJGLUtil.log(
      "Listener position: "
        + listenerPosition.get(0)
        + ", "
        + listenerPosition.get(1)
        + ", "
        + listenerPosition.get(2));
    LWJGLUtil.log("Left position: " + leftPosition.get(0) + ", " + leftPosition.get(1) + ", " + leftPosition.get(2));
    LWJGLUtil.log("Center position: " + centerPosition.get(0) + ", " + centerPosition.get(1) + ", " + centerPosition.get(2));
    LWJGLUtil.log("Right position: " + rightPosition.get(0) + ", " + rightPosition.get(1) + ", " + rightPosition.get(2));

    while (!finished) {
      // handle any input
      handleInput();

      // allow window to process internal messages
      Display.update();

      // render and paint if !minimized and not dirty
      if(Display.isVisible()) {
        render();
      } else {
        // sleeeeeep
        pause(100);
      }

      // act on pause mode
      paused(!(Display.isVisible() || Display.isActive()));

      // start sound after first paint, since we don't want
      // the delay before something is painted on the screen
      if (firstRun && !pauseMode) {
        firstRun = false;

        // start sounds with delays
        startSounds();
      }
    }
  }

  /**
   * Starts playing the sounds at different times
   */
  private void startSounds() {
    alSourcePlay(soundSources.get(LEFT));
    pause(300);
    alSourcePlay(soundSources.get(CENTER));
    pause(500);
    alSourcePlay(soundSources.get(RIGHT));
  }

  /**
   * Handles any changes in pause mode
   *
   * @param paused Which pause mode to enter
   */
  private void paused(boolean paused) {
    // if requesting pause, and not paused - pause and stop sound
    if(paused && !pauseMode) {
      pauseMode = true;
      alSourcePause(soundSources);
      System.out.println("pauseMode = true");
    }

    // else go out of pause mode and start sounds
    else if(!paused && pauseMode) {
      pauseMode = false;
      startSounds();
      System.out.println("pauseMode = false");
    }
  }

  /**
   * Handles any input
   */
  private void handleInput() {
    // User wants to exit?
    finished = Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE);
    if (finished) {
      return;
    }

    boolean shift = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);

    // Test for play
    // ============================================
    if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
      alSourcePlay(soundSources.get(LEFT));
      LWJGLUtil.log("Playing left.wav");
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
      alSourcePlay(soundSources.get(CENTER));
      LWJGLUtil.log("Playing center.wav");
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_3)) {
      alSourcePlay(soundSources.get(RIGHT));
      LWJGLUtil.log("Playing right.wav");
    }
    // --------------------------------------------

    // Test for stop
    // ============================================
    if (Keyboard.isKeyDown(Keyboard.KEY_4)) {
      alSourceStop(soundSources.get(LEFT));
      LWJGLUtil.log("Stopped left.wav");
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_5)) {
      alSourceStop(soundSources.get(CENTER));
      LWJGLUtil.log("Stopped center.wav");
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_6)) {
      alSourceStop(soundSources.get(RIGHT));
      LWJGLUtil.log("Stopped right.wav");
    }
    // --------------------------------------------

    // Test for movement with keyboard
    // ============================================
    if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
      listenerPosition.put(0, listenerPosition.get(0) - 0.1f);
      alListener(AL_POSITION, listenerPosition);
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
      listenerPosition.put(0, listenerPosition.get(0) + 0.1f);
      alListener(AL_POSITION, listenerPosition);
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
      if (shift) {
        listenerPosition.put(1, listenerPosition.get(1) + 0.1f);
      } else {
        listenerPosition.put(2, listenerPosition.get(2) - 0.1f);
      }
      alListener(AL_POSITION, listenerPosition);
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
      if (shift) {
        listenerPosition.put(1, listenerPosition.get(1) - 0.1f);
      } else {
        listenerPosition.put(2, listenerPosition.get(2) + 0.1f);
      }
      alListener(AL_POSITION, listenerPosition);
    }
    // --------------------------------------------

    // Test for movement with Mouse
    // ============================================
    listenerPosition.put(0, listenerPosition.get(0) + (0.01f * Mouse.getDX()));
    listenerPosition.put(1, listenerPosition.get(1) + (0.01f * Mouse.getDY()));
    if (Mouse.isButtonDown(0)) {
      listenerPosition.put(2, listenerPosition.get(2) - 0.1f);
    }
    if (Mouse.isButtonDown(1)) {
      listenerPosition.put(2, listenerPosition.get(2) + 0.1f);
    }

    alListener(AL_POSITION, listenerPosition);

    // empty mouse buffer
    while(Mouse.next());
  }

  /**
   * Render the scene
   */
  private void render() {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glPushMatrix();
    {
      glRotatef(20.0f, 1.0f, 1.0f, 0.0f);

      // left
      glPushMatrix();
      {
        glTranslatef(leftPosition.get(0), leftPosition.get(1), leftPosition.get(2));
        glColor3f(1.0f, 0.0f, 0.0f);
        glut.glutWireCube(0.5f);
      }
      glPopMatrix();

      // center
      glPushMatrix();
      {
        glTranslatef(centerPosition.get(0), centerPosition.get(1), centerPosition.get(2));
        glColor3f(0.0f, 0.0f, 1.0f);
        glut.glutWireCube(0.5f);
      }
      glPopMatrix();

      // right
      glPushMatrix();
      {
        glTranslatef(rightPosition.get(0), rightPosition.get(1), rightPosition.get(2));
        glColor3f(0.0f, 1.0f, 0.0f);
        glut.glutWireCube(0.5f);
      }
      glPopMatrix();

      // listener
      glPushMatrix();
      {
        glTranslatef(listenerPosition.get(0), listenerPosition.get(1), listenerPosition.get(2));
        glColor3f(1.0f, 1.0f, 1.0f);
        glut.glutSolidCube(0.5f);
      }
      glPopMatrix();
    }
    glPopMatrix();
  }

  /**
   * Shutdown of demonstration
   */
  private void shutdown() {
    LWJGLUtil.log("Shutting down OpenAL");
    alSourceStop(soundSources);
    alDeleteSources(soundSources);
    alDeleteBuffers(soundBuffers);
    AL.destroy();

    LWJGLUtil.log("Shutting down Window");
    Display.destroy();
  }

  /**
   * main entry point
   *
   * @param args
   *          String array containing arguments
   */
  public static void main(String[] args) {
    PositionTest positionTest = new PositionTest();
    positionTest.execute(args);
    System.exit(0);
  }

  /**
   * Minute implementation of GLUT: <br>COPYRIGHT:
   *
   * The OpenGL Utility Toolkit distribution for Win32 (Windows NT & Windows
   * 95) contains source code modified from the original source code for GLUT
   * version 3.3 which was developed by Mark J. Kilgard. The original source
   * code for GLUT is Copyright 1997 by Mark J. Kilgard. GLUT for Win32 is
   * Copyright 1997 by Nate Robins and is not in the public domain, but it is
   * freely distributable without licensing fees. It is provided without
   * guarantee or warrantee expressed or implied. It was ported with the
   * permission of Mark J. Kilgard by Nate Robins.
   *
   * THIS SOURCE CODE IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EITHER
   * EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
   * OR MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.
   */
  class GLUT {

    float n[][] = new float[][] { { -1.0f, 0.0f, 0.0f }, {
        0.0f, 1.0f, 0.0f }, {
        1.0f, 0.0f, 0.0f }, {
        0.0f, -1.0f, 0.0f }, {
        0.0f, 0.0f, 1.0f }, {
        0.0f, 0.0f, -1.0f }
    };

    int faces[][] = new int[][] { { 0, 1, 2, 3 }, {
        3, 2, 6, 7 }, {
        7, 6, 5, 4 }, {
        4, 5, 1, 0 }, {
        5, 6, 2, 1 }, {
        7, 4, 0, 3 }
    };
    float v[][] = new float[8][3];

    public void glutWireCube(float size) {
      drawBox(size, GL_LINE_LOOP);
    }

    public void glutSolidCube(float size) {
      drawBox(size, GL_QUADS);
    }

    private void drawBox(float size, int type) {

      v[0][0] = v[1][0] = v[2][0] = v[3][0] = -size / 2;
      v[4][0] = v[5][0] = v[6][0] = v[7][0] = size / 2;
      v[0][1] = v[1][1] = v[4][1] = v[5][1] = -size / 2;
      v[2][1] = v[3][1] = v[6][1] = v[7][1] = size / 2;
      v[0][2] = v[3][2] = v[4][2] = v[7][2] = -size / 2;
      v[1][2] = v[2][2] = v[5][2] = v[6][2] = size / 2;

      for (int i = 5; i >= 0; i--) {
        glBegin(type);
        glNormal3f(n[i][0], n[i][1], n[i][2]);
        glVertex3f(v[faces[i][0]][0], v[faces[i][0]][1], v[faces[i][0]][2]);
        glVertex3f(v[faces[i][1]][0], v[faces[i][1]][1], v[faces[i][1]][2]);
        glVertex3f(v[faces[i][2]][0], v[faces[i][2]][1], v[faces[i][2]][2]);
        glVertex3f(v[faces[i][3]][0], v[faces[i][3]][1], v[faces[i][3]][2]);
        glEnd();
      }

    }
  }
}
