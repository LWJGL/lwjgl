/*
 * Copyright (c) 2002-2011 LWJGL Project
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
package org.lwjgl.test.opengl.sprites;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Random;
import javax.imageio.ImageIO;

import static org.lwjgl.opengl.EXTTransformFeedback.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.*;

/**
 * Sprite rendering demo. In this version we're doing the animation
 * computations on the GPU, using transform feedback and a vertex
 * shader, then rendering is performed in 2 passes, with depth testing
 * enabled:
 * 1) Sprites are rendered front-to-back, opaque fragments only, blending is disabled.
 * 2) Sprites are rendered back-to-front, transparent fragments only, blending is enabled.
 * Sorting is free, because we're animating double the amount of sprites rendered, the
 * first batch is sorted f2b, the second is sorted b2f. Ordering is achieved by modifying
 * the z-axis position of the sprites in the vertex shader.
 *
 * @author Spasi
 * @since 18/3/2011
 */
public final class SpriteShootout2P {

	private static final int SCREEN_WIDTH  = 800;
	private static final int SCREEN_HEIGHT = 600;

	private static final int ANIMATION_TICKS = 60;

	private boolean run       = true;
	private boolean render    = true;
	private boolean colorMask = true;
	private boolean animate   = true;
	private boolean smooth;
	private boolean vsync;

	private int ballSize  = 42;
	private int ballCount = 100 * 1000;

	private SpriteRenderer renderer;

	// OpenGL stuff
	private int texID;
	private int texBigID;
	private int texSmallID;

	private SpriteShootout2P() {
	}

	public static void main(String[] args) {
		try {
			new SpriteShootout2P().start();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	private void start() throws LWJGLException {
		try {
			initGL();

			renderer = new SpriteRendererTF();

			updateBalls(ballCount);
			run();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			destroy();
		}
	}

	private void initGL() throws LWJGLException {
		Display.setLocation((Display.getDisplayMode().getWidth() - SCREEN_WIDTH) / 2,
		                    (Display.getDisplayMode().getHeight() - SCREEN_HEIGHT) / 2);
		Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
		Display.setTitle("Sprite Shootout 2-pass");
		Display.create(new PixelFormat(0, 24, 0));
		//Display.create(new PixelFormat(), new ContextAttribs(4, 1).withProfileCompatibility(true).withDebug(true));
		//AMDDebugOutput.glDebugMessageCallbackAMD(new AMDDebugOutputCallback());

		final ContextCapabilities caps = GLContext.getCapabilities();
		if ( !(caps.OpenGL30 || (caps.OpenGL20 && caps.GL_EXT_transform_feedback)) )
			throw new RuntimeException("OpenGL 3.0 or 2.0 + EXT_transform_feedback is required for this demo.");

		// Setup viewport

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, SCREEN_WIDTH, 0, SCREEN_HEIGHT, -1.0, 1.0);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

		glClearColor(1.0f, 1.0f, 1.0f, 0.0f);

		// Create textures

		try {
			texSmallID = createTexture("res/ball_sm.png");
			texBigID = createTexture("res/ball.png");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		texID = texBigID;

		// Setup rendering state

		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);

		glEnable(GL_ALPHA_TEST);

		glColorMask(colorMask, colorMask, colorMask, false);
		glDepthMask(true);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LESS);
		glClearDepth(1.0f);

		// Setup geometry

		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);

		Util.checkGLError();
	}

	private static int createTexture(final String path) throws IOException {
		final BufferedImage img = ImageIO.read(SpriteShootout2P.class.getClassLoader().getResource(path));

		final int w = img.getWidth();
		final int h = img.getHeight();

		final ByteBuffer buffer = readImage(img);

		final int texID = glGenTextures();

		glBindTexture(GL_TEXTURE_2D, texID);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w, h, 0, GL_BGRA, GL_UNSIGNED_BYTE, buffer);

		return texID;
	}

	private static ByteBuffer readImage(final BufferedImage img) throws IOException {
		final Raster raster = img.getRaster();

		final int bands = raster.getNumBands();

		final int w = img.getWidth();
		final int h = img.getHeight();

		final int size = w * h * bands;

		final byte[] pixels = new byte[size];
		raster.getDataElements(0, 0, w, h, pixels);

		final ByteBuffer pbuffer = BufferUtils.createByteBuffer(size);

		if ( bands == 4 ) {
			for ( int i = 0; i < (w * h * 4); i += 4 ) {
				// Pre-multiply alpha
				final float a = unpackUByte01(pixels[i + 3]);
				pbuffer.put(packUByte01(unpackUByte01(pixels[i + 2]) * a));
				pbuffer.put(packUByte01(unpackUByte01(pixels[i + 1]) * a));
				pbuffer.put(packUByte01(unpackUByte01(pixels[i + 0]) * a));
				pbuffer.put(pixels[i + 3]);
			}
		} else if ( bands == 3 ) {
			for ( int i = 0; i < (w * h * 3); i += 3 ) {
				pbuffer.put(pixels[i + 2]);
				pbuffer.put(pixels[i + 1]);
				pbuffer.put(pixels[i + 0]);
			}
		} else
			pbuffer.put(pixels, 0, size);

		pbuffer.flip();

		return pbuffer;
	}

	private static float unpackUByte01(final byte x) {
		return (x & 0xFF) / 255.0f;
	}

	private static byte packUByte01(final float x) {
		return (byte)(x * 255.0f);
	}

	private void updateBalls(final int count) {
		System.out.println("NUMBER OF BALLS: " + count);
		renderer.updateBalls(ballCount);
	}

	private void run() {
		long startTime = System.currentTimeMillis() + 5000;
		long fps = 0;

		long time = Sys.getTime();
		final int ticksPerUpdate = (int)(Sys.getTimerResolution() / ANIMATION_TICKS);

		renderer.render(false, true, 0);

		while ( run ) {
			Display.processMessages();
			handleInput();

			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			final long currTime = Sys.getTime();
			final int delta = (int)(currTime - time);
			if ( smooth || delta >= ticksPerUpdate ) {
				renderer.render(render, animate, delta);
				time = currTime;
			} else
				renderer.render(render, false, 0);

			Display.update(false);
			//Display.sync(60);

			if ( startTime > System.currentTimeMillis() ) {
				fps++;
			} else {
				long timeUsed = 5000 + (startTime - System.currentTimeMillis());
				startTime = System.currentTimeMillis() + 5000;
				System.out.println("FPS: " + (Math.round(fps / (timeUsed / 1000.0) * 10) / 10.0) + ", Balls: " + ballCount);
				fps = 0;
			}
		}
	}

	private void handleInput() {
		if ( Display.isCloseRequested() )
			run = false;

		while ( Keyboard.next() ) {
			if ( Keyboard.getEventKeyState() )
				continue;

			switch ( Keyboard.getEventKey() ) {
				case Keyboard.KEY_1:
				case Keyboard.KEY_2:
				case Keyboard.KEY_3:
				case Keyboard.KEY_4:
				case Keyboard.KEY_5:
				case Keyboard.KEY_6:
				case Keyboard.KEY_7:
				case Keyboard.KEY_8:
				case Keyboard.KEY_9:
				case Keyboard.KEY_0:
					ballCount = 1 << (Keyboard.getEventKey() - Keyboard.KEY_1);
					updateBalls(ballCount);
					break;
				case Keyboard.KEY_ADD:
				case Keyboard.KEY_SUBTRACT:
					int mult;
					if ( Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) ) {
						mult = 1000;
						if ( Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL) )
							mult *= 5;
					} else if ( Keyboard.isKeyDown(Keyboard.KEY_LMENU) || Keyboard.isKeyDown(Keyboard.KEY_RMENU) )
						mult = 100;
					else if ( Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL) )
						mult = 10;
					else
						mult = 1;
					if ( Keyboard.getEventKey() == Keyboard.KEY_SUBTRACT )
						mult = -mult;
					ballCount += mult * 100;
					if ( ballCount <= 0 )
						ballCount = 1;
					updateBalls(ballCount);
					break;
				case Keyboard.KEY_ESCAPE:
					run = false;
					break;
				case Keyboard.KEY_A:
					animate = !animate;
					System.out.println("Animation is now " + (animate ? "on" : "off") + ".");
					break;
				case Keyboard.KEY_C:
					colorMask = !colorMask;
					glColorMask(colorMask, colorMask, colorMask, false);
					System.out.println("Color mask is now " + (colorMask ? "on" : "off") + ".");
					// Disable alpha test when color mask is off, else we get no benefit.
					if ( colorMask ) {
						glEnable(GL_BLEND);
						glEnable(GL_ALPHA_TEST);
					} else {
						glDisable(GL_BLEND);
						glDisable(GL_ALPHA_TEST);
					}
					break;
				case Keyboard.KEY_R:
					render = !render;
					System.out.println("Rendering is now " + (render ? "on" : "off") + ".");
					break;
				case Keyboard.KEY_S:
					smooth = !smooth;
					System.out.println("Smooth animation is now " + (smooth ? "on" : "off") + ".");
					break;
				case Keyboard.KEY_T:
					if ( texID == texBigID ) {
						texID = texSmallID;
						ballSize = 16;
					} else {
						texID = texBigID;
						ballSize = 42;
					}
					renderer.updateBallSize();
					glBindTexture(GL_TEXTURE_2D, texID);
					System.out.println("Now using the " + (texID == texBigID ? "big" : "small") + " texture.");
					break;
				case Keyboard.KEY_V:
					vsync = !vsync;
					Display.setVSyncEnabled(vsync);
					System.out.println("VSYNC is now " + (vsync ? "enabled" : "disabled") + ".");
					break;
			}
		}

		while ( Mouse.next() ) ;
	}

	private void destroy() {
		Display.destroy();
	}

	private abstract class SpriteRenderer {

		protected int progID;

		protected void createPrograms(final int vshID) {
			// Opaque pass

			final int fshID = glCreateShader(GL_FRAGMENT_SHADER);
			glShaderSource(fshID, "uniform sampler2D COLOR_MAP;\n" +
			                      "void main(void) {\n" +
			                      "     gl_FragColor = texture2D(COLOR_MAP, gl_PointCoord);\n" +
			                      "}");
			glCompileShader(fshID);
			if ( glGetShader(fshID, GL_COMPILE_STATUS) == GL_FALSE ) {
				System.out.println(glGetShaderInfoLog(fshID, glGetShader(fshID, GL_INFO_LOG_LENGTH)));
				throw new RuntimeException("Failed to compile fragment shader.");
			}

			progID = glCreateProgram();
			glAttachShader(progID, vshID);
			glAttachShader(progID, fshID);
			glLinkProgram(progID);
			if ( glGetProgram(progID, GL_LINK_STATUS) == GL_FALSE ) {
				System.out.println(glGetProgramInfoLog(progID, glGetProgram(progID, GL_INFO_LOG_LENGTH)));
				throw new RuntimeException("Failed to link shader program.");
			}

			glUseProgram(progID);
			glUniform1i(glGetUniformLocation(progID, "COLOR_MAP"), 0);

			updateBallSize();

			glEnableClientState(GL_VERTEX_ARRAY);
		}

		public void updateBallSize() {
			glPointSize(ballSize);
		}

		protected abstract void updateBalls(final int count);

		protected abstract void render(boolean render, boolean animate, int delta);

	}

	private class SpriteRendererTF extends SpriteRenderer {

		private int progIDTF;
		private int ballSizeLoc;
		private int deltaLoc;

		private int[] tfVBO = new int[2];
		private int currVBO;

		private int depthVBO;
		private int depthLoc;

		SpriteRendererTF() {
			System.out.println("Shootout Implementation: TF GPU animation & 2-pass rendering");

			// Transform-feedback program

			int vshID = glCreateShader(GL_VERTEX_SHADER);
			glShaderSource(vshID, "#version 130\n" +
			                      "const float WIDTH = " + SCREEN_WIDTH + ";\n" +
			                      "const float HEIGHT = " + SCREEN_HEIGHT + ";\n" +
			                      "uniform float ballSize;\n" + // ballSize / 2
			                      "uniform float delta;\n" +
			                      "void main(void) {\n" +
			                      "     vec4 anim = gl_Vertex;\n" +
			                      "     anim.xy = anim.xy + anim.zw * delta;\n" +
			                      "     vec2 animC = clamp(anim.xy, vec2(ballSize), vec2(WIDTH - ballSize, HEIGHT - ballSize));\n" +
			                      "     if ( anim.x != animC.x ) anim.z = -anim.z;\n" +
			                      "     if ( anim.y != animC.y ) anim.w = -anim.w;\n" +
			                      "     gl_Position = vec4(animC, anim.zw);\n" +
			                      "}");
			glCompileShader(vshID);
			if ( glGetShader(vshID, GL_COMPILE_STATUS) == GL_FALSE ) {
				System.out.println(glGetShaderInfoLog(vshID, glGetShader(vshID, GL_INFO_LOG_LENGTH)));
				throw new RuntimeException("Failed to compile vertex shader.");
			}

			progIDTF = glCreateProgram();
			glAttachShader(progIDTF, vshID);
			glTransformFeedbackVaryings(progIDTF, new CharSequence[] { "gl_Position" }, GL_SEPARATE_ATTRIBS);
			glLinkProgram(progIDTF);
			if ( glGetProgram(progIDTF, GL_LINK_STATUS) == GL_FALSE ) {
				System.out.println(glGetProgramInfoLog(progIDTF, glGetProgram(progIDTF, GL_INFO_LOG_LENGTH)));
				throw new RuntimeException("Failed to link shader program.");
			}

			glUseProgram(progIDTF);

			ballSizeLoc = glGetUniformLocation(progIDTF, "ballSize");
			deltaLoc = glGetUniformLocation(progIDTF, "delta");

			glUniform1f(ballSizeLoc, ballSize * 0.5f);

			// -----------------

			vshID = glCreateShader(GL_VERTEX_SHADER);
			glShaderSource(vshID, "#version 130\n" +
			                      "in float depth;\n" +
			                      "void main(void) {\n" +
			                      "     gl_Position = gl_ModelViewProjectionMatrix * vec4(gl_Vertex.xy, depth, gl_Vertex.w);\n" +
			                      "}");
			glCompileShader(vshID);
			if ( glGetShader(vshID, GL_COMPILE_STATUS) == GL_FALSE ) {
				System.out.println(glGetShaderInfoLog(vshID, glGetShader(vshID, GL_INFO_LOG_LENGTH)));
				throw new RuntimeException("Failed to compile vertex shader.");
			}

			createPrograms(vshID);

			depthLoc = glGetAttribLocation(progID, "depth");

			// -----------------
		}

		public void updateBallSize() {
			glUseProgram(progIDTF);
			glUniform1f(ballSizeLoc, ballSize * 0.5f);

			super.updateBallSize();
		}

		public void updateBalls(final int count) {
			// Depth data

			final FloatBuffer depths = BufferUtils.createFloatBuffer(count * 2);
			final float depthStep = 1.9f / count;
			float depth = Float.parseFloat("0x1.fffffep-1");
			// Front-to-back
			for ( int i = 0; i < count; i++ ) {
				depths.put(depth);
				depth -= depthStep;
			}
			// Back-to-front
			for ( int i = 0; i < count; i++ )
				depths.put(depths.get(count - 1 - i));
			depths.flip();

			if ( depthVBO != 0 )
				glDeleteBuffers(depthVBO);

			depthVBO = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER, depthVBO);
			glBufferData(GL_ARRAY_BUFFER, depths, GL_STATIC_DRAW);

			glEnableVertexAttribArray(depthLoc);
			glVertexAttribPointer(depthLoc, 1, GL_FLOAT, false, 0, 0);

			// Animation data

			final FloatBuffer transform = BufferUtils.createFloatBuffer(count * 2 * 4);
			// Front-to-back
			final Random random = new Random();
			for ( int i = 0; i < count; i++ ) {
				transform.put((int)(random.nextFloat() * (SCREEN_WIDTH - ballSize) + ballSize * 0.5f));
				transform.put((int)(random.nextFloat() * (SCREEN_HEIGHT - ballSize) + ballSize * 0.5f));
				transform.put(random.nextFloat() * 0.4f - 0.2f);
				transform.put(random.nextFloat() * 0.4f - 0.2f);
			}
			// Back-to-front
			for ( int i = 0; i < count; i++ ) {
				final int offset = (count - 1 - i) * 4;
				transform.put(transform.get(offset + 0));
				transform.put(transform.get(offset + 1));
				transform.put(transform.get(offset + 2));
				transform.put(transform.get(offset + 3));
			}
			transform.flip();

			if ( tfVBO[0] != 0 ) {
				for ( int i = 0; i < tfVBO.length; i++ )
					glDeleteBuffers(tfVBO[i]);
			}

			for ( int i = 0; i < tfVBO.length; i++ ) {
				tfVBO[i] = glGenBuffers();
				glBindBuffer(GL_TRANSFORM_FEEDBACK_BUFFER, tfVBO[i]);
				glBufferData(GL_TRANSFORM_FEEDBACK_BUFFER, transform, GL_STATIC_DRAW);
			}

			glBindBuffer(GL_ARRAY_BUFFER, tfVBO[0]);
			glVertexPointer(2, GL_FLOAT, (4 * 4), 0);
		}

		public void render(final boolean render, final boolean animate, final int delta) {
			if ( animate ) {
				glDisableVertexAttribArray(depthLoc);

				final int vbo = currVBO;
				currVBO = 1 - currVBO;

				glUseProgram(progIDTF);
				glUniform1f(deltaLoc, delta);

				glBindBuffer(GL_ARRAY_BUFFER, tfVBO[vbo]);
				glVertexPointer(4, GL_FLOAT, 0, 0);

				glEnable(GL_RASTERIZER_DISCARD);
				if ( GLContext.getCapabilities().OpenGL30 ) {
					glBindBufferBase(GL_TRANSFORM_FEEDBACK_BUFFER, 0, tfVBO[1 - vbo]);

					glBeginTransformFeedback(GL_POINTS);
					glDrawArrays(GL_POINTS, 0, ballCount * 2);
					glEndTransformFeedback();
				} else {
					glBindBufferBaseEXT(GL_TRANSFORM_FEEDBACK_BUFFER_EXT, 0, tfVBO[1 - vbo]);

					glBeginTransformFeedbackEXT(GL_POINTS);
					glDrawArrays(GL_POINTS, 0, ballCount * 2);
					glEndTransformFeedbackEXT();
				}
				glDisable(GL_RASTERIZER_DISCARD);

				glUseProgram(progID);
				glVertexPointer(2, GL_FLOAT, (4 * 4), 0);

				glEnableVertexAttribArray(depthLoc);
			}

			if ( render ) {
				// Render front-to-back opaque pass
				glAlphaFunc(GL_EQUAL, 1.0f);
				glDisable(GL_BLEND);
				glDrawArrays(GL_POINTS, 0, ballCount);
				glEnable(GL_BLEND);

				// Render back-to-front transparent pass
				glAlphaFunc(GL_GREATER, 0.0f); // Fragments with alpha == 1.0 are early-depth-rejected.
				glDepthMask(false);
				glDrawArrays(GL_POINTS, ballCount, ballCount);
				glDepthMask(true);
			}
		}

	}

}