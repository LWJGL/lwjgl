package org.lwjgl.test.openal;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.*;
import org.lwjgl.openal.*;
import org.lwjgl.opengl.*;
import org.lwjgl.input.*;

/**
 * $Id$
 * <br>
 * This test demonstrates OpenAL positioning Based on the example by Chad Armstrong 
 * (http://www.edenwaith.com/products/pige/tutorials/openal.php)
 * 
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
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
  private boolean finished = false;

  // OpenAL stuff
  // ===================================================

  /** OpenAL buffers */
  private IntBuffer soundBuffers = createIntBuffer(3);

  /** OpenAL sources */
  private IntBuffer soundSources = createIntBuffer(3);

  /** Position of listener */
  private FloatBuffer listenerPosition = createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });

  /** Velocity of listener */
  private FloatBuffer listenerVelocity = createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });

  /** Orientation of listener */
  private FloatBuffer listenerOrientation =
    createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f });

  /** Position of left sound */
  private FloatBuffer leftPosition = createFloatBuffer(3).put(new float[] { -2.0f, 0.0f, 0.0f });

  /** Velocity of left sound */
  private FloatBuffer leftVelocity = createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });

  /** Position of center sound */
  private FloatBuffer centerPosition = createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, -4.0f });

  /** Velocity of center sound */
  private FloatBuffer centerVelocity = createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });

  /** Position of right sound */
  private FloatBuffer rightPosition = createFloatBuffer(3).put(new float[] { 2.0f, 0.0f, 0.0f });

  /** Velocity of right sound */
  private FloatBuffer rightVelocity = createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });
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
    Sys.log("Setting up window");

    // calc center
    int centerX = (Display.getWidth() - WINDOW_WIDTH) / 2;
    int centerY = (Display.getHeight() - WINDOW_HEIGHT) / 2;

    // setup window
    Window.create("PositionTest", centerX, centerY, WINDOW_WIDTH, WINDOW_HEIGHT, Display.getDepth(), 0, 8, 0);
    // -----------------------------------------------------

    // Setup OpenGL
    // =====================================================
    Sys.log("Setting up OpenGL");

    GL.glViewport(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    GL.glMatrixMode(GL.GL_PROJECTION);
    GL.glLoadIdentity();
    GLU.gluPerspective(50.0, WINDOW_WIDTH / WINDOW_HEIGHT, 0.0, 50.0);
    GL.glMatrixMode(GL.GL_MODELVIEW);
    GL.glLoadIdentity();
    GL.glTranslatef(0.0f, 0.0f, -6.6f);
    GL.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    glut = this.new GLUT();

    Window.setVSyncEnabled(true);
    // -----------------------------------------------------

    // Setup OpenAL
    // =====================================================
    Sys.log("Setting up OpenAL");

    AL.create();

    listenerPosition.flip();
    listenerVelocity.flip();
    listenerOrientation.flip();

    leftPosition.flip();
    leftVelocity.flip();

    centerPosition.flip();
    centerVelocity.flip();

    rightPosition.flip();
    rightVelocity.flip();

    AL.alListener(AL.AL_POSITION, listenerPosition);
    AL.alListener(AL.AL_VELOCITY, listenerVelocity);
    AL.alListener(AL.AL_ORIENTATION, listenerOrientation);

    // creating buffers
    Sys.log("Creating buffers");
    AL.alGenBuffers(soundBuffers);
    soundBuffers.rewind();

    // creating sources
    AL.alGenSources(soundSources);
    soundSources.rewind();

    // load sound files (left, center, right).wav
    Sys.log("Loading soundfiles...");

    Sys.log("Loading left.wav");
    WaveData left = WaveData.create("left.wav");
    AL.alBufferData(soundBuffers.get(LEFT), left.format, left.data, left.data.capacity(), left.samplerate);
    AL.alSourcef(soundSources.get(LEFT), AL.AL_PITCH, 1.0f);
    AL.alSourcef(soundSources.get(LEFT), AL.AL_GAIN, 1.0f);
    AL.alSource(soundSources.get(LEFT), AL.AL_POSITION, leftPosition);
    AL.alSource(soundSources.get(LEFT), AL.AL_VELOCITY, leftVelocity);
    AL.alSourcei(soundSources.get(LEFT), AL.AL_BUFFER, soundBuffers.get(LEFT));
    AL.alSourcei(soundSources.get(LEFT), AL.AL_LOOPING, AL.AL_TRUE);

    Sys.log("Loading center.wav");
    WaveData center = WaveData.create("center.wav");
    AL.alBufferData(soundBuffers.get(CENTER), center.format, center.data, center.data.capacity(), center.samplerate);
    AL.alSourcef(soundSources.get(CENTER), AL.AL_PITCH, 1.0f);
    AL.alSourcef(soundSources.get(CENTER), AL.AL_GAIN, 1.0f);
    AL.alSource(soundSources.get(CENTER), AL.AL_POSITION, centerPosition);
    AL.alSource(soundSources.get(CENTER), AL.AL_VELOCITY, centerVelocity);
    AL.alSourcei(soundSources.get(CENTER), AL.AL_BUFFER, soundBuffers.get(CENTER));
    AL.alSourcei(soundSources.get(CENTER), AL.AL_LOOPING, AL.AL_TRUE);

    Sys.log("Loading right.wav");
    WaveData right = WaveData.create("right.wav");
    AL.alBufferData(soundBuffers.get(RIGHT), right.format, right.data, right.data.capacity(), right.samplerate);
    AL.alSourcef(soundSources.get(RIGHT), AL.AL_PITCH, 1.0f);
    AL.alSourcef(soundSources.get(RIGHT), AL.AL_GAIN, 1.0f);
    AL.alSource(soundSources.get(RIGHT), AL.AL_POSITION, rightPosition);
    AL.alSource(soundSources.get(RIGHT), AL.AL_VELOCITY, rightVelocity);
    AL.alSourcei(soundSources.get(RIGHT), AL.AL_BUFFER, soundBuffers.get(RIGHT));
    AL.alSourcei(soundSources.get(RIGHT), AL.AL_LOOPING, AL.AL_TRUE);

    Sys.log("Soundfiles loaded successfully");
    // -----------------------------------------------------

    // Setup Keyboard
    // =====================================================
    Sys.log("Setting up Keyboard");

    Keyboard.create();
    // -----------------------------------------------------
    
    // Setup Mouse
    // =====================================================
    Sys.log("Setting up Mouse");

    Mouse.create();
    // -----------------------------------------------------
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
    System.out.println("Press ESC to exit demo");

    Sys.log(
      "Listener position: "
        + listenerPosition.get(0)
        + ", "
        + listenerPosition.get(1)
        + ", "
        + listenerPosition.get(2));
    Sys.log("Left position: " + leftPosition.get(0) + ", " + leftPosition.get(1) + ", " + leftPosition.get(2));
    Sys.log("Center position: " + centerPosition.get(0) + ", " + centerPosition.get(1) + ", " + centerPosition.get(2));
    Sys.log("Right position: " + rightPosition.get(0) + ", " + rightPosition.get(1) + ", " + rightPosition.get(2));

    while (!finished) {
      // handle any input
      handleInput();

      // render the scene
      render();

      // allow window to process internal messages
      Window.update();

      // paint the content and flip buffer
      Window.paint();

      // start sound after first paint, since we don't want
      // the delay before something is painted on the screen
      if (firstRun) {
        firstRun = false;

        // start sounds with delays
        AL.alSourcePlay(soundSources.get(LEFT));
        pause(300);
        AL.alSourcePlay(soundSources.get(CENTER));
        pause(500);
        AL.alSourcePlay(soundSources.get(RIGHT));
      }
    }
  }

  /**
   * Handles any input
   */
  private void handleInput() {
    Mouse.poll();
    Keyboard.poll();

    // User wants to exit?
    finished = Window.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE);
    if (finished) {
      return;
    }

    boolean shift = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);

    // Test for play
    // ============================================
    if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
      AL.alSourcePlay(soundSources.get(LEFT));
      Sys.log("Playing left.wav");
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
      AL.alSourcePlay(soundSources.get(CENTER));
      Sys.log("Playing center.wav");
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_3)) {
      AL.alSourcePlay(soundSources.get(RIGHT));
      Sys.log("Playing right.wav");
    }
    // --------------------------------------------

    // Test for stop
    // ============================================
    if (Keyboard.isKeyDown(Keyboard.KEY_4)) {
      AL.alSourceStop(soundSources.get(LEFT));
      Sys.log("Stopped left.wav");
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_5)) {
      AL.alSourceStop(soundSources.get(CENTER));
      Sys.log("Stopped center.wav");
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_6)) {
      AL.alSourceStop(soundSources.get(RIGHT));
      Sys.log("Stopped right.wav");
    }
    // --------------------------------------------

    // Test for movement with keyboard
    // ============================================
    if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
      listenerPosition.put(0, listenerPosition.get(0) - 0.1f);
      AL.alListener(AL.AL_POSITION, listenerPosition);
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
      listenerPosition.put(0, listenerPosition.get(0) + 0.1f);
      AL.alListener(AL.AL_POSITION, listenerPosition);
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
      if (shift) {
        listenerPosition.put(1, listenerPosition.get(1) + 0.1f);
      } else {
        listenerPosition.put(2, listenerPosition.get(2) - 0.1f);
      }
      AL.alListener(AL.AL_POSITION, listenerPosition);
    }

    if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
      if (shift) {
        listenerPosition.put(1, listenerPosition.get(1) - 0.1f);
      } else {
        listenerPosition.put(2, listenerPosition.get(2) + 0.1f);
      }
      AL.alListener(AL.AL_POSITION, listenerPosition);
    }
    // --------------------------------------------
    
    // Test for movement with Mouse
    // ============================================
    listenerPosition.put(0, listenerPosition.get(0) + (0.01f * Mouse.dx));
    listenerPosition.put(1, listenerPosition.get(1) + (0.01f * Mouse.dy));
    if (Mouse.isButtonDown(0)) {
      listenerPosition.put(2, listenerPosition.get(2) - 0.1f);
    }
    if (Mouse.isButtonDown(1)) {
      listenerPosition.put(2, listenerPosition.get(2) + 0.1f);
    }
    
    AL.alListener(AL.AL_POSITION, listenerPosition);
    
  }

  /**
   * Render the scene
   */
  private void render() {
    GL.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
    GL.glPushMatrix();
    {
      GL.glRotatef(20.0f, 1.0f, 1.0f, 0.0f);

      GL.glPushMatrix();
      {
        GL.glTranslatef(leftPosition.get(0), leftPosition.get(1), leftPosition.get(2));
        GL.glColor3f(1.0f, 0.0f, 0.0f);
        glut.glutWireCube(0.5f);
      }
      GL.glPopMatrix();

      GL.glPushMatrix();
      {
        GL.glTranslatef(centerPosition.get(0), centerPosition.get(1), centerPosition.get(2));
        GL.glColor3f(0.0f, 0.0f, 1.0f);
        glut.glutWireCube(0.5f);
      }
      GL.glPopMatrix();

      GL.glPushMatrix();
      {
        GL.glTranslatef(rightPosition.get(0), rightPosition.get(1), rightPosition.get(2));
        GL.glColor3f(0.0f, 1.0f, 0.0f);
        glut.glutWireCube(0.5f);
      }
      GL.glPopMatrix();

      //the listener
      GL.glPushMatrix();
      {
        GL.glTranslatef(listenerPosition.get(0), listenerPosition.get(1), listenerPosition.get(2));
        GL.glColor3f(1.0f, 1.0f, 1.0f);
        glut.glutSolidCube(0.5f);
      }
      GL.glPopMatrix();
    }
    GL.glPopMatrix();
  }

  /**
   * Shutdown of demonstration
   */
  private void shutdown() {
    Sys.log("Shutting down Keyboard");
    Keyboard.destroy();
    
    Sys.log("Shutting down Mouse");
    Mouse.destroy();
    
    Sys.log("Shutting down OpenAL");
    AL.alSourceStop(soundSources);
    AL.alDeleteSources(soundSources);
    AL.alDeleteBuffers(soundBuffers);
    AL.destroy();

    Sys.log("Shutting down Window");
    Window.destroy();
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
      drawBox(size, GL.GL_LINE_LOOP);
    }

    public void glutSolidCube(float size) {
      drawBox(size, GL.GL_QUADS);
    }

    private void drawBox(float size, int type) {

      v[0][0] = v[1][0] = v[2][0] = v[3][0] = -size / 2;
      v[4][0] = v[5][0] = v[6][0] = v[7][0] = size / 2;
      v[0][1] = v[1][1] = v[4][1] = v[5][1] = -size / 2;
      v[2][1] = v[3][1] = v[6][1] = v[7][1] = size / 2;
      v[0][2] = v[3][2] = v[4][2] = v[7][2] = -size / 2;
      v[1][2] = v[2][2] = v[5][2] = v[6][2] = size / 2;

      for (int i = 5; i >= 0; i--) {
        GL.glBegin(type);
        GL.glNormal3f(n[i][0], n[i][1], n[i][2]);
        GL.glVertex3f(v[faces[i][0]][0], v[faces[i][0]][1], v[faces[i][0]][2]);
        GL.glVertex3f(v[faces[i][1]][0], v[faces[i][1]][1], v[faces[i][1]][2]);
        GL.glVertex3f(v[faces[i][2]][0], v[faces[i][2]][1], v[faces[i][2]][2]);
        GL.glVertex3f(v[faces[i][3]][0], v[faces[i][3]][1], v[faces[i][3]][2]);
        GL.glEnd();
      }

    }
  }
}