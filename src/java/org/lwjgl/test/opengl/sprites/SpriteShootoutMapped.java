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
import org.lwjgl.util.mapped.MappedObject;
import org.lwjgl.util.mapped.MappedObjectClassLoader;
import org.lwjgl.util.mapped.MappedObjectTransformer;
import org.lwjgl.util.mapped.MappedType;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;
import javax.imageio.ImageIO;

import static org.lwjgl.opengl.EXTTransformFeedback.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Sprite rendering demo. Three implementations are supported:
 * a) CPU animation + BufferData VBO update.
 * b) CPU animation + MapBufferRange VBO update.
 * c) GPU animation using transform feedback with a vertex shader.
 *
 * @author Spasi
 * @since 18/3/2011
 */
public final class SpriteShootoutMapped {

	static final int SCREEN_WIDTH  = 800;
	static final int SCREEN_HEIGHT = 600;

	private static final int ANIMATION_TICKS = 60;

	private boolean run       = true;
	private boolean render    = true;
	private boolean colorMask = true;
	private boolean animate   = true;
	private boolean smooth;
	private boolean vsync;

	int ballSize  = 42;
	int ballCount = 100 * 1000;

	private SpriteRenderer renderer;

	// OpenGL stuff
	private int texID;
	private int texBigID;
	private int texSmallID;

	long animateTime;

	private SpriteShootoutMapped() {
	}

	public static void main(String[] args) {
		MappedObjectTransformer.register(Pixel4b.class);
		MappedObjectTransformer.register(Pixel3b.class);
		MappedObjectTransformer.register(Sprite.class);
		MappedObjectTransformer.register(SpriteRender.class);

		if ( MappedObjectClassLoader.fork(SpriteShootoutMapped.class, args) )
			return;

		try {
			new SpriteShootoutMapped().start();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	private void start() throws LWJGLException {
		try {
			initGL();

			final ContextCapabilities caps = GLContext.getCapabilities();
			if ( !true && (caps.OpenGL30 || caps.GL_EXT_transform_feedback) )
				renderer = new SpriteRendererTF();
			else if ( true && caps.GL_ARB_map_buffer_range )
				renderer = new SpriteRendererMapped();
			else
				renderer = new SpriteRendererPlain();

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
		Display.setTitle("Sprite Shootout");
		Display.create();
		//Display.create(new PixelFormat(), new ContextAttribs(4, 1).withProfileCompatibility(true).withDebug(true));
		//AMDDebugOutput.glDebugMessageCallbackAMD(new AMDDebugOutputCallback());

		if ( !GLContext.getCapabilities().OpenGL20 )
			throw new RuntimeException("OpenGL 2.0 is required for this demo.");

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
		glAlphaFunc(GL_GREATER, 0.0f);

		glColorMask(colorMask, colorMask, colorMask, false);
		glDepthMask(false);
		glDisable(GL_DEPTH_TEST);

		// Setup geometry

		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);

		Util.checkGLError();
	}

	private static int createTexture(final String path) throws IOException {
		final BufferedImage img = ImageIO.read(SpriteShootoutMapped.class.getClassLoader().getResource(path));

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

	public static class Pixel4b extends MappedObject {

		public byte r, g, b, a;

	}

	@MappedType(align = 3)
	public static class Pixel3b extends MappedObject {

		public byte r, g, b;

	}

	private static ByteBuffer readImage(final BufferedImage img) throws IOException {
		final Raster raster = img.getRaster();

		final int bands = raster.getNumBands();

		final int w = img.getWidth();
		final int h = img.getHeight();

		final int count = w * h;

		final byte[] pixels = new byte[count * bands];
		raster.getDataElements(0, 0, w, h, pixels);

		if ( bands == 4 ) {
			Pixel4b p = Pixel4b.malloc(count);

			int b = 0;
			for ( int i = 0; i < count; i++, b += 4 ) {
				// Pre-multiply alpha
				final float a = unpackUByte01(pixels[b + 3]);

				p.view = i;
				p.r = packUByte01(unpackUByte01(pixels[b + 2]) * a);
				p.g = packUByte01(unpackUByte01(pixels[b + 1]) * a);
				p.b = packUByte01(unpackUByte01(pixels[b + 0]) * a);
				p.a = pixels[b + 3];
			}

			return p.backingByteBuffer();
		} else if ( bands == 3 ) {
			Pixel3b p = Pixel3b.malloc(count);

			int b = 0;
			for ( int i = 0; i < count; i++, b += 3 ) {
				p.view = i;
				p.r = pixels[b + 2];
				p.g = pixels[b + 1];
				p.b = pixels[b + 0];
			}

			return p.backingByteBuffer();
		} else {
			ByteBuffer p = BufferUtils.createByteBuffer(count * bands);
			p.put(pixels, 0, p.capacity());
			p.flip();
			return p;
		}

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

			glClear(GL_COLOR_BUFFER_BIT);

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
				System.out.println("Animation: " + animateTime / fps);
				animateTime = 0;
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
					if ( Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) )
						mult = 1000;
					else if ( Keyboard.isKeyDown(Keyboard.KEY_LMENU) || Keyboard.isKeyDown(Keyboard.KEY_RMENU) )
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

	public static class Sprite extends MappedObject {

		public float dx, x;
		public float dy, y;

	}

	public static class SpriteRender extends MappedObject {

		public float x, y;

	}

	private abstract class SpriteRenderer {

		protected Sprite sprites;

		protected int spriteCount;

		protected int vshID;
		protected int progID;

		protected void createProgram() {
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

		public abstract void updateBalls(int count);

		protected abstract void render(boolean render, boolean animate, int delta);

	}

	private abstract class SpriteRendererBatched extends SpriteRenderer {

		protected static final int BALLS_PER_BATCH = 10 * 1000;

		SpriteRendererBatched() {
			vshID = glCreateShader(GL_VERTEX_SHADER);
			glShaderSource(vshID, "void main(void) {\n" +
			                      "     gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n" +
			                      "}");
			glCompileShader(vshID);
			if ( glGetShader(vshID, GL_COMPILE_STATUS) == GL_FALSE ) {
				System.out.println(glGetShaderInfoLog(vshID, glGetShader(vshID, GL_INFO_LOG_LENGTH)));
				throw new RuntimeException("Failed to compile vertex shader.");
			}

			createProgram();
		}

		public void updateBalls(final int count) {
			final Random random = new Random();

			final Sprite newSprites = Sprite.malloc(count);
			if ( sprites != null ) {
				sprites.view = 0;
				sprites.copyRange(newSprites, Math.min(count, spriteCount));
			}

			if ( count > spriteCount ) {
				for ( int i = spriteCount; i < count; i++ ) {
					newSprites.view = i;

					newSprites.x = (int)(random.nextFloat() * (SCREEN_WIDTH - ballSize) + ballSize * 0.5f);
					newSprites.y = (int)(random.nextFloat() * (SCREEN_HEIGHT - ballSize) + ballSize * 0.5f);
					newSprites.dx = random.nextFloat() * 0.4f - 0.2f;
					newSprites.dy = random.nextFloat() * 0.4f - 0.2f;
				}
			}

			sprites = newSprites;
			spriteCount = count;
		}

		protected void animate(
			final Sprite sprite,
			final SpriteRender spriteRender,
			final int ballSize, final int ballIndex, final int batchSize, final int delta
		) {
			final float ballRadius = ballSize * 0.5f;
			final float boundW = SCREEN_WIDTH - ballRadius;
			final float boundH = SCREEN_HEIGHT - ballRadius;

			final Sprite[] sprites = sprite.asArray();
			final SpriteRender[] spritesRender = spriteRender.asArray();
			for ( int b = ballIndex, r = 0, len = (ballIndex + batchSize); b < len; b++, r++ ) {
				float dx = sprites[b].dx;
				float x = sprites[b].x;

				x += dx * delta;
				if ( x < ballRadius ) {
					x = ballRadius;
					dx = -dx;
				} else if ( x > boundW ) {
					x = boundW;
					dx = -dx;
				}

				sprites[b].dx = dx;
				sprites[b].x = x;
				spritesRender[r].x = x;

				float dy = sprites[b].dy;
				float y = sprites[b].y;

				y += dy * delta;
				if ( y < ballRadius ) {
					y = ballRadius;
					dy = -dy;
				} else if ( y > boundH ) {
					y = boundH;
					dy = -dy;
				}

				sprites[b].dy = dy;
				sprites[b].y = y;
				spritesRender[r].y = y;
			}
		}

	}

	private class SpriteRendererPlain extends SpriteRendererBatched {

		private final int DATA_PER_BATCH = BALLS_PER_BATCH * 2 * 4; // balls * 2 floats * 4 bytes

		protected int[] animVBO;

		private SpriteRender spritesRender;

		SpriteRendererPlain() {
			System.out.println("Shootout Implementation: CPU animation & BufferData");
			spritesRender = SpriteRender.malloc(BALLS_PER_BATCH);
		}

		public void updateBalls(final int count) {
			super.updateBalls(count);

			final int batchCount = count / BALLS_PER_BATCH + (count % BALLS_PER_BATCH == 0 ? 0 : 1);
			if ( animVBO != null && batchCount == animVBO.length )
				return;

			final int[] newAnimVBO = new int[batchCount];
			if ( animVBO != null ) {
				System.arraycopy(animVBO, 0, newAnimVBO, 0, Math.min(animVBO.length, newAnimVBO.length));
				for ( int i = newAnimVBO.length; i < animVBO.length; i++ )
					glDeleteBuffers(animVBO[i]);
			}
			for ( int i = animVBO == null ? 0 : animVBO.length; i < newAnimVBO.length; i++ ) {
				newAnimVBO[i] = glGenBuffers();
				glBindBuffer(GL_ARRAY_BUFFER, newAnimVBO[i]);
			}

			animVBO = newAnimVBO;
		}

		public void render(final boolean render, final boolean animate, final int delta) {
			int batchSize = Math.min(ballCount, BALLS_PER_BATCH);
			int ballIndex = 0;
			int batchIndex = 0;
			while ( ballIndex < ballCount ) {
				glBindBuffer(GL_ARRAY_BUFFER, animVBO[batchIndex]);

				if ( animate )
					animate(ballIndex, batchSize, delta);

				if ( render ) {
					glVertexPointer(2, GL_FLOAT, 0, 0);
					glDrawArrays(GL_POINTS, 0, batchSize);
				}

				ballIndex += batchSize;
				batchSize = Math.min(ballCount - ballIndex, BALLS_PER_BATCH);
				batchIndex++;
			}
		}

		private void animate(final int ballIndex, final int batchSize, final int delta) {
			animate(
				sprites, spritesRender,
				ballSize, ballIndex, batchSize, delta
			);

			glBufferData(GL_ARRAY_BUFFER, DATA_PER_BATCH, GL_STREAM_DRAW);
			glBufferSubData(GL_ARRAY_BUFFER, 0, spritesRender.backingByteBuffer());
		}

	}

	private class SpriteRendererMapped extends SpriteRendererBatched {

		private StreamVBO animVBO;

		SpriteRendererMapped() {
			System.out.println("Shootout Implementation: CPU animation & MapBufferRange");
		}

		public void updateBalls(final int count) {
			super.updateBalls(count);

			if ( animVBO != null )
				animVBO.destroy();

			animVBO = new StreamVBO(GL_ARRAY_BUFFER, ballCount * (2 * 4));
		}

		public void render(final boolean render, final boolean animate, final int delta) {
			int batchSize = Math.min(ballCount, BALLS_PER_BATCH);
			int ballIndex = 0;
			while ( ballIndex < ballCount ) {
				if ( animate ) {
					final ByteBuffer buffer = animVBO.map(batchSize * (2 * 4));

					long t0 = System.nanoTime();
					animate(sprites, SpriteRender.<SpriteRender>map(buffer), ballSize, ballIndex, batchSize, delta);
					long t1 = System.nanoTime();

					animateTime += t1 - t0;

					animVBO.unmap();
				}

				if ( render ) {
					glVertexPointer(2, GL_FLOAT, 0, ballIndex * (2 * 4));
					glDrawArrays(GL_POINTS, 0, batchSize);
				}

				ballIndex += batchSize;
				batchSize = Math.min(ballCount - ballIndex, BALLS_PER_BATCH);
			}
		}

	}

	private class SpriteRendererTF extends SpriteRenderer {

		private int progIDTF;
		private int ballSizeLoc;
		private int deltaLoc;

		private int[] tfVBO = new int[2];
		private int currVBO;

		SpriteRendererTF() {
			System.out.println("Shootout Implementation: TF GPU animation");

			// Transform-feedback program

			final int vshID = glCreateShader(GL_VERTEX_SHADER);
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

			this.vshID = glCreateShader(GL_VERTEX_SHADER);
			glShaderSource(this.vshID, "void main(void) {\n" +
			                           "     gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n" +
			                           "}");
			glCompileShader(this.vshID);
			if ( glGetShader(this.vshID, GL_COMPILE_STATUS) == GL_FALSE ) {
				System.out.println(glGetShaderInfoLog(this.vshID, glGetShader(this.vshID, GL_INFO_LOG_LENGTH)));
				throw new RuntimeException("Failed to compile vertex shader.");
			}

			createProgram();
		}

		public void updateBallSize() {
			glUseProgram(progIDTF);
			glUniform1f(ballSizeLoc, ballSize * 0.5f);

			glUseProgram(progID);
			super.updateBallSize();
		}

		private void doUpdateBalls(final int count) {
			final Random random = new Random();

			final Sprite newSprites = Sprite.malloc(count);
			if ( sprites != null ) {
				sprites.view = 0;
				sprites.copyRange(newSprites, Math.min(count, spriteCount));
			}

			if ( count > spriteCount ) {
				for ( int i = spriteCount; i < count; i++ ) {
					newSprites.view = i;

					newSprites.x = (int)(random.nextFloat() * (SCREEN_WIDTH - ballSize) + ballSize * 0.5f);
					newSprites.y = (int)(random.nextFloat() * (SCREEN_HEIGHT - ballSize) + ballSize * 0.5f);
					newSprites.dx = random.nextFloat() * 0.4f - 0.2f;
					newSprites.dy = random.nextFloat() * 0.4f - 0.2f;
				}
			}

			sprites = newSprites;
			spriteCount = count;
		}

		public void updateBalls(final int count) {
			if ( tfVBO[0] != 0 ) {
				// Fetch current animation state
				glGetBufferSubData(GL_TRANSFORM_FEEDBACK_BUFFER, 0, sprites.backingByteBuffer());
			}

			doUpdateBalls(count);

			if ( tfVBO[0] != 0 ) {
				for ( int i = 0; i < tfVBO.length; i++ )
					glDeleteBuffers(tfVBO[i]);
			}

			for ( int i = 0; i < tfVBO.length; i++ ) {
				tfVBO[i] = glGenBuffers();
				glBindBuffer(GL_TRANSFORM_FEEDBACK_BUFFER, tfVBO[i]);
				glBufferData(GL_TRANSFORM_FEEDBACK_BUFFER, sprites.backingByteBuffer(), GL_STATIC_DRAW);
			}

			glBindBuffer(GL_ARRAY_BUFFER, tfVBO[0]);
			glVertexPointer(2, GL_FLOAT, (4 * 4), 0);
		}

		public void render(final boolean render, final boolean animate, final int delta) {
			if ( animate ) {
				glUseProgram(progIDTF);
				glUniform1f(deltaLoc, delta);

				final int vbo = currVBO;
				currVBO = 1 - currVBO;

				glBindBuffer(GL_ARRAY_BUFFER, tfVBO[vbo]);
				glVertexPointer(4, GL_FLOAT, 0, 0);

				glEnable(GL_RASTERIZER_DISCARD);
				if ( GLContext.getCapabilities().OpenGL30 ) {
					glBindBufferBase(GL_TRANSFORM_FEEDBACK_BUFFER, 0, tfVBO[1 - vbo]);

					glBeginTransformFeedback(GL_POINTS);
					glDrawArrays(GL_POINTS, 0, ballCount);
					glEndTransformFeedback();
				} else {
					glBindBufferBaseEXT(GL_TRANSFORM_FEEDBACK_BUFFER_EXT, 0, tfVBO[1 - vbo]);

					glBeginTransformFeedbackEXT(GL_POINTS);
					glDrawArrays(GL_POINTS, 0, ballCount);
					glEndTransformFeedbackEXT();
				}
				glDisable(GL_RASTERIZER_DISCARD);

				glUseProgram(progID);
				glVertexPointer(2, GL_FLOAT, (4 * 4), 0);
			}

			if ( render )
				glDrawArrays(GL_POINTS, 0, ballCount);
		}

	}

}