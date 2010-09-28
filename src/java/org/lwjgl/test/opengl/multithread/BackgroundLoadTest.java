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
package org.lwjgl.test.opengl.multithread;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.Sphere;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 * A test of loading textures in a background thread. This can be achieved in 2 ways:
 * <br>
 * a) A dummy Pbuffer is created and its context shares the rendering context.<br>
 * b) A SharedDrawable is used.<br>
 * <br>
 * When the test starts, there's no texture created and rendering is done with texturing disabled.
 * 2 seconds later a "dummy" texture is created in the background thread and texturing is enabled. This dummy texture
 * can by anything the developer wants to have as a placeholder while textures are being loaded.
 * Finally, 5 seconds later the "true" texture is loaded and displayed. This texture will change every 5 seconds after
 * that, until the test is terminated (ESC key).
 *
 * @author Spasi
 */
public final class BackgroundLoadTest {

	private static boolean run = true;

	private static BackgroundLoader backgroundLoader;

	private static Sphere sphere;

	private BackgroundLoadTest() {
	}

	public static void main(String[] args) {
		initialize(args);

		Util.checkGLError();

		try {
			backgroundLoader.start();
		} catch (LWJGLException e) {
			kill("Failed to start background thread.", e);
		}

		Util.checkGLError();

		while ( run ) {
			if ( !Display.isVisible() )
				Thread.yield();
			else {
				handleIO();

				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

				renderObject();

				Util.checkGLError();

				// Restore camera position.
				glPopMatrix();
				glPushMatrix();
			}

			Display.update();

			if ( Display.isCloseRequested() )
				break;
		}

		cleanup();
		System.exit(0);
	}

	private static void initialize(String[] args) {
		if ( args.length != 1 )
			argsError();

		DisplayMode displayMode = null;

		try {
			DisplayMode[] modes = Display.getAvailableDisplayModes();

			displayMode = chooseMode(modes, 1024, 768);
			if ( displayMode == null )
				displayMode = chooseMode(modes, 800, 600);
			if ( displayMode == null )
				displayMode = chooseMode(modes, 640, 480);
			if ( displayMode == null )
				kill("Failed to set an appropriate display mode.");

			System.out.println("Setting display mode to: " + displayMode);
			Display.setDisplayMode(displayMode);
			Display.setTitle("Background Loading Test");
			Display.create(new PixelFormat(8, 24, 0));
		} catch (LWJGLException e) {
			kill(e.getMessage());
		}

		glViewport(0, 0, displayMode.getWidth(), displayMode.getHeight());

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(45, displayMode.getWidth() / (float)displayMode.getHeight(), 1.0f, 10.0f);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		// Setup camera position.
		glTranslatef(0.0f, 0.0f, -4.0f);
		glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
		glPushMatrix();

		glClearDepth(1.0f);
		glDepthFunc(GL_LEQUAL);

		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);

		glFrontFace(GL_CCW);
		glPolygonMode(GL_FRONT, GL_FILL);

		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);

		glAlphaFunc(GL_GREATER, 0.0f);
		glEnable(GL_ALPHA_TEST);

		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glDisable(GL_BLEND);

		glShadeModel(GL_SMOOTH);

		final FloatBuffer vectorBuffer = BufferUtils.createFloatBuffer(4);

		vectorBuffer.clear();
		vectorBuffer.put(0, 1.0f).put(1, 1.0f).put(2, 1.0f).put(3, 1.0f);
		glLight(GL_LIGHT0, GL_DIFFUSE, vectorBuffer);

		vectorBuffer.put(0, 1.0f).put(1, 1.0f).put(2, 1.0f).put(3, 1.0f);
		glLight(GL_LIGHT0, GL_AMBIENT, vectorBuffer);

		vectorBuffer.put(0, 1.0f).put(1, 1.0f).put(2, 0.5f).put(3, 1.0f);
		glLight(GL_LIGHT0, GL_SPECULAR, vectorBuffer);

		vectorBuffer.put(0, -1.0f / 3.0f).put(1, 1.0f / 3.0f).put(2, 1.0f / 3.0f).put(3, 0.0f); // Infinite
		glLight(GL_LIGHT0, GL_POSITION, vectorBuffer);

		vectorBuffer.put(0, 0.2f).put(1, 0.2f).put(2, 0.2f).put(3, 1.0f);
		glLightModel(GL_LIGHT_MODEL_AMBIENT, vectorBuffer);

		glEnable(GL_LIGHT0);
		glEnable(GL_LIGHTING);

		sphere = new Sphere();

		if ( "PB".equalsIgnoreCase(args[0]) ) {
			backgroundLoader = new BackgroundLoader() {
				Drawable getDrawable() throws LWJGLException {
					return new Pbuffer(2, 2, new PixelFormat(8, 24, 0), Display.getDrawable());
				}
			};
		} else if ( "SD".equalsIgnoreCase(args[0]) ) {
			backgroundLoader = new BackgroundLoader() {
				Drawable getDrawable() throws LWJGLException {
					return new SharedDrawable(Display.getDrawable());
				}
			};
		} else {
			argsError();
		}
	}

	private static void handleIO() {
		if ( Keyboard.getNumKeyboardEvents() != 0 ) {
			while ( Keyboard.next() ) {
				if ( Keyboard.getEventKeyState() )
					continue;

				switch ( Keyboard.getEventKey() ) {
					case Keyboard.KEY_ESCAPE:
						run = false;
						break;
				}
			}
		}

		while ( Mouse.next() ) ;
	}

	static void renderObject() {
		glColor3f(1.0f, 1.0f, 1.0f);

		int texID = backgroundLoader.getTexID();
		if ( texID == 0 ) {
			sphere.setTextureFlag(false);
			glDisable(GL_TEXTURE_2D);
		} else {
			sphere.setTextureFlag(true);
			glEnable(GL_TEXTURE_2D);
			glBindTexture(GL_TEXTURE_2D, texID);
		}

		sphere.draw(1.0f, 32, 32);

		if ( texID != 0 ) { // Unbind so we can update from the background thread.
			glBindTexture(GL_TEXTURE_2D, 0);
			glDisable(GL_TEXTURE_2D);
		}
	}

	private static DisplayMode chooseMode(DisplayMode[] modes, int width, int height) {
		DisplayMode bestMode = null;

		for ( DisplayMode mode : modes ) {
			if ( mode.getWidth() == width && mode.getHeight() == height && mode.getFrequency() <= 85 ) {
				if ( bestMode == null || (mode.getBitsPerPixel() >= bestMode.getBitsPerPixel() && mode.getFrequency() > bestMode.getFrequency()) )
					bestMode = mode;
			}
		}

		return bestMode;
	}

	private static void cleanup() {
		backgroundLoader.cleanup();

		Thread.yield(); // Let background thread finish.

		if ( Display.isCreated() )
			Display.destroy();
	}

	private static void argsError() {
		System.out.println("\nInvalid program arguments.");
		System.out.println("\nUsage: BackgroundLoadTest <testType>, where <testType> argument can be one of the following:\n");
		System.out.println("PB\t- Use a Pbuffer context for the background thread.");
		System.out.println("SD\t- Use a SharedDrawable context for the background thread.");

		cleanup();
		System.exit(-1);
	}

	static void kill(String reason) {
		System.out.println("The BackgroundLoadTest program was terminated because an error occured.\n");
		System.out.println("Reason: " + (reason == null ? "Unknown" : reason));

		cleanup();
		System.exit(-1);
	}

	static void kill(String reason, Throwable t) {
		System.out.println("The BackgroundLoadTest program was terminated because an exception occured.\n");
		System.out.println("Reason: " + (reason == null ? "Unknown" : reason));

		System.out.println("Exception message: " + t.getMessage());

		cleanup();
		System.exit(-1);
	}

}