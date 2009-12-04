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
/*
 * Created by LWJGL.
 * User: spasi
 * Date: 2004-03-30
 * Time: 8:41:42 pm
 */

package org.lwjgl.test.opengl.shaders;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

public final class ShadersTest {

	private static DisplayMode displayMode;

	private static boolean run = true;

	private static final FloatBuffer vectorBuffer = BufferUtils.createFloatBuffer(4);

	private static Sphere sphere;

	private static Shader shader;

	private static float frameTime;

	private static float angle;
	private static float sin;
	private static int specularity = 4;

	private ShadersTest() {
	}

	public static void main(String[] args) {
		initialize(args);

		long frameStart;
		long lastFrameTime = 0;

		while ( run ) {
			if (!Display.isVisible() )
				Thread.yield();
			else {
				// This is the current frame time.
				frameStart = Sys.getTime();

				// How many seconds passed since last frame.
				frameTime = (float)((frameStart - lastFrameTime) / (double)Sys.getTimerResolution());

				lastFrameTime = frameStart;

				//angle += frameTime * 90.0f;
				angle += 0.1f;
				sin = (float)Math.sin(Math.toRadians(angle));

				handleIO();

				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

				if ( shader != null )
					shader.render();
				else
					renderObject();

				// Restore camera position.
				GL11.glPopMatrix();
				GL11.glPushMatrix();
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

		try {
			DisplayMode[] modes = Display.getAvailableDisplayModes();

			DisplayMode displayMode;

			displayMode = chooseMode(modes, 1024, 768);
			if ( displayMode == null )
				displayMode = chooseMode(modes, 800, 600);
			if ( displayMode == null )
				displayMode = chooseMode(modes, 640, 480);
			if ( displayMode == null )
				kill("Failed to set an appropriate display mode.");

			System.out.println("Setting display mode to: " + displayMode);
			Display.setDisplayMode(displayMode);
			Display.create(new PixelFormat(8, 24, 0), "UNI".equalsIgnoreCase(args[0]) ? new ContextAttribs(3, 1) : null);
			ShadersTest.displayMode = displayMode;
		} catch (LWJGLException e) {
			kill(e.getMessage());
		}

		if ( "NONE".equalsIgnoreCase(args[0]) ) {
			shader = null;
		} else if ( "VP".equalsIgnoreCase(args[0]) ) {
			if ( !GLContext.getCapabilities().GL_ARB_vertex_program )
				kill("The ARB_vertex_program extension is not supported.");

			shader = new ShaderVP("shaderVP.vp");
		} else if ( "FP".equalsIgnoreCase(args[0]) ) {
			if ( !GLContext.getCapabilities().GL_ARB_vertex_program )
				kill("The ARB_vertex_program extension is not supported.");
			if ( !GLContext.getCapabilities().GL_ARB_fragment_program )
				kill("The ARB_fragment_program extension is not supported.");

			shader = new ShaderFP("shaderFP.vp", "shaderFP.fp");
		} else if ( "VSH".equalsIgnoreCase(args[0]) ) {
			if ( !GLContext.getCapabilities().GL_ARB_vertex_shader )
				kill("The ARB_vertex_shader extension is not supported.");

			shader = new ShaderVSH("shaderVSH.vsh");
		} else if ( "FSH".equalsIgnoreCase(args[0]) ) {
			if ( !GLContext.getCapabilities().GL_ARB_vertex_shader )
				kill("The ARB_vertex_shader extension is not supported.");
			if ( !GLContext.getCapabilities().GL_ARB_fragment_shader )
				kill("The ARB_fragment_shader extension is not supported.");

			shader = new ShaderFSH("shaderFSH.vsh", "shaderFSH.fsh");
		} else if ("UNI".equalsIgnoreCase(args[0]) ) {
			if ( !GLContext.getCapabilities().OpenGL31 )
				kill("OpenGL version 3.1 is not supported.");

			shader = new ShaderUNI("shaderUNI.vsh");
		} else {
			argsError();
		}

		GL11.glViewport(0, 0, displayMode.getWidth(), displayMode.getHeight());

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(45, displayMode.getWidth() / (float)displayMode.getHeight(), 1.0f, 10.0f);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();

		// Setup camera position.
		GL11.glTranslatef(0.0f, 0.0f, -4.0f);
		GL11.glRotatef(15.0f, 1.0f, 0.0f, 0.0f);
		GL11.glPushMatrix();

		GL11.glClearDepth(1.0f);
		GL11.glDepthFunc(GL11.GL_LEQUAL);

		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);

		GL11.glFrontFace(GL11.GL_CCW);
		GL11.glPolygonMode(GL11.GL_FRONT, GL11.GL_FILL);

		GL11.glCullFace(GL11.GL_BACK);
		GL11.glEnable(GL11.GL_CULL_FACE);

		GL11.glAlphaFunc(GL11.GL_NOTEQUAL, 0.0f);
		GL11.glEnable(GL11.GL_ALPHA_TEST);

		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_BLEND);

		// Setup lighting for when we have fixed function fragment rendering.
		GL11.glShadeModel(GL11.GL_SMOOTH);

		if ( shader == null ) {
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_LIGHT0);
		}

		vectorBuffer.clear();
		vectorBuffer.put(1.0f).put(1.0f).put(1.0f).put(1.0f);
		vectorBuffer.clear();
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, vectorBuffer);

		vectorBuffer.put(1.0f).put(1.0f).put(1.0f).put(1.0f);
		vectorBuffer.clear();
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, vectorBuffer);

		vectorBuffer.put(1.0f).put(1.0f).put(0.5f).put(1.0f);
		vectorBuffer.clear();
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, vectorBuffer);

		vectorBuffer.put(-1.0f / 3.0f).put(1.0f / 3.0f).put(1.0f / 3.0f).put(0.0f); // Infinite
		vectorBuffer.clear();
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, vectorBuffer);

		vectorBuffer.put(0.2f).put(0.2f).put(0.2f).put(1.0f);
		vectorBuffer.clear();
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, vectorBuffer);

		sphere = new Sphere();
	}

	private static void handleIO() {
		if ( Keyboard.getNumKeyboardEvents() != 0 ) {
			while ( Keyboard.next() ) {
				if ( Keyboard.getEventKeyState() )
					continue;

				switch ( Keyboard.getEventKey() ) {
					case Keyboard.KEY_EQUALS:
						if ( specularity < 8 )
							specularity++;
						break;
					case Keyboard.KEY_MINUS:
						if ( specularity > 1 )
							specularity--;
						break;
					case Keyboard.KEY_ESCAPE:
						run = false;
						break;
				}
			}
		}

		while ( Mouse.next() ) ;
	}

	static int getDisplayWidth() {
		return displayMode.getWidth();
	}

	static int getDisplayHeight() {
		return displayMode.getHeight();
	}

	static float getSin() {
		return sin;
	}

	static int getSpecularity() {
		return specularity;
	}

	static void renderObject() {
		GL11.glColor3b((byte)255, (byte)255, (byte)255);
		sphere.draw(1.0f, 32, 32);
	}

	private static DisplayMode chooseMode(DisplayMode[] modes, int width, int height) {
		DisplayMode bestMode = null;

		for ( int i = 0; i < modes.length; i++ ) {
			DisplayMode mode = modes[i];
			if ( mode.getWidth() == width && mode.getHeight() == height && mode.getFrequency() <= 85 ) {
				if ( bestMode == null || (mode.getBitsPerPixel() >= bestMode.getBitsPerPixel() && mode.getFrequency() > bestMode.getFrequency()) )
					bestMode = mode;
			}
		}

		return bestMode;
	}

	private static void cleanup() {
		// This is not necessary, just showing how to properly delete a program/shader.
		if ( shader != null )
			shader.cleanup();

		if ( Display.isCreated() )
			Display.destroy();
	}

	private static void argsError() {
		System.out.println("\nInvalid program arguments.");
		System.out.println("\nUsage: ShadersTest <shaderType>, where <shaderType> argument can be one of the following:\n");
		System.out.println("none\t- Use fixed function rendering.");
		System.out.println("vp\t- Use ARB_vertex_program (low-level) only.");
		System.out.println("vsh\t- Use ARB_vertex_shader (GLSL) only.");
		System.out.println("fp\t- Use ARB_vertex_program + ARB_fragment_program (low-level).");
		System.out.println("fsh\t- Use ARB_vertex_shader + ARB_fragment_shader (GLSL).");

		cleanup();
		System.exit(-1);
	}

	static void kill(String reason) {
		System.out.println("The ShaderTest program was terminated because an error occured.\n");
		System.out.println("Reason: " + (reason == null ? "Unknown" : reason));

		cleanup();
		System.exit(-1);
	}

	static void kill(String reason, Throwable t) {
		System.out.println("The ShaderTest program was terminated because an exception occured.\n");
		System.out.println("Reason: " + reason == null ? "Unknown" : reason);

		System.out.println("Exception message: " + t.getMessage());

		cleanup();
		System.exit(-1);
	}

}
