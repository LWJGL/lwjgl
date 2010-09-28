package org.lwjgl.test.applet;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GLContext;

import static org.lwjgl.opengl.ARBTransposeMatrix.*;
import static org.lwjgl.opengl.GL11.*;

public class GearsApplet extends Applet {

	/** The Canvas where the LWJGL Display is added */
	Canvas display_parent;

	/** Thread which runs the main game loop */
	Thread gameThread;

	/** is the game loop running */
	boolean running;

	/** variables used to rotate the view */
	private float view_rotx	= 20.0f;
	private float view_roty	= 30.0f;
	private float view_rotz;

	private int gear1;
	private int	gear2;
	private int	gear3;
	private float angle;

	boolean keyDown;

	private int prevMouseX, prevMouseY;
	private boolean mouseButtonDown;


	/**
	 * Once the Canvas is created its add notify method will call this method to
	 * start the LWJGL Display and game loop in another thread.
	 */
	public void startLWJGL() {
		gameThread = new Thread() {
			public void run() {
				running = true;
				try {
					Display.setParent(display_parent);
					//Display.setVSyncEnabled(true);
					Display.create();
					initGL();
				} catch (LWJGLException e) {
					e.printStackTrace();
				}
				gameLoop();
			}
		};
		gameThread.start();
	}


	/**
	 * Tell game loop to stop running, after which the LWJGL Display will be destoryed.
	 * The main thread will wait for the Display.destroy() to complete
	 */
	private void stopLWJGL() {
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void start() {

	}

	public void stop() {

	}

	/**
	 * Applet Destroy method will remove the canvas, before canvas is destroyed it will notify
	 * stopLWJGL() to stop main game loop and to destroy the Display
	 */
	public void destroy() {
		remove(display_parent);
		super.destroy();
		System.out.println("Clear up");
	}

	/**
	 * initialise applet by adding a canvas to it, this canvas will start the LWJGL Display and game loop
	 * in another thread. It will also stop the game loop and destroy the display on canvas removal when
	 * applet is destroyed.
	 */
	public void init() {
		setLayout(new BorderLayout());
		try {
			display_parent = new Canvas() {
				public void addNotify() {
					super.addNotify();
					startLWJGL();
				}
				public void removeNotify() {
					stopLWJGL();
					super.removeNotify();
				}
			};
			display_parent.setSize(getWidth(),getHeight());
			add(display_parent);
			display_parent.setFocusable(true);
			display_parent.requestFocus();
			display_parent.setIgnoreRepaint(true);
			//setResizable(true);
			setVisible(true);
		} catch (Exception e) {
			System.err.println(e);
			throw new RuntimeException("Unable to create display");
		}
	}

	public void gameLoop() {
		long startTime = System.currentTimeMillis() + 5000;
		long fps = 0;

		while(running) {
			angle += 2.0f;

			// draw the gears
			drawLoop();

			Display.update();

			if (startTime > System.currentTimeMillis()) {
				fps++;
			} else {
				long timeUsed = 5000 + (startTime - System.currentTimeMillis());
				startTime = System.currentTimeMillis() + 5000;
				System.out.println(fps + " frames 2 in " + timeUsed / 1000f + " seconds = "
						+ (fps / (timeUsed / 1000f)));
				fps = 0;
			}

			if (Mouse.isButtonDown(0)) {
				if (!mouseButtonDown) {
					prevMouseX = Mouse.getX();
					prevMouseY= Mouse.getY();
				}
				mouseButtonDown = true;
			}
			else {
				mouseButtonDown = false;
			}

			if (mouseButtonDown) {
				int x = Mouse.getX();
			    int y = Mouse.getY();

				float thetaY = 360.0f * ( (float)(x-prevMouseX)/(float)display_parent.getWidth());
			    float thetaX = 360.0f * ( (float)(prevMouseY-y)/(float)display_parent.getHeight());

			    prevMouseX = x;
			    prevMouseY = y;

			    view_rotx += thetaX;
			    view_roty += thetaY;
			}

			// F Key Pressed (i.e. released)
			if (keyDown && !Keyboard.isKeyDown(Keyboard.KEY_F)) {
				keyDown = false;

				try {
					if (Display.isFullscreen()) {
						Display.setFullscreen(false);
					}
					else {
						Display.setFullscreen(true);
					}
				} catch (LWJGLException e) {
					e.printStackTrace();
				}
			}
		}

		Display.destroy();
	}

	public void drawLoop() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glPushMatrix();

		glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
		glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
		glRotatef(view_rotz, 0.0f, 0.0f, 1.0f);

		glPushMatrix();
		glTranslatef(-3.0f, -2.0f, 0.0f);
		glRotatef(angle, 0.0f, 0.0f, 1.0f);
		glCallList(gear1);
		glPopMatrix();

		glPushMatrix();
		glTranslatef(3.1f, -2.0f, 0.0f);
		glRotatef(-2.0f * angle - 9.0f, 0.0f, 0.0f, 1.0f);
		glCallList(gear2);
		glPopMatrix();

		glPushMatrix();
		glTranslatef(-3.1f, 4.2f, 0.0f);
		glRotatef(-2.0f * angle - 25.0f, 0.0f, 0.0f, 1.0f);
		glCallList(gear3);
		glPopMatrix();

		glPopMatrix();
	}

	protected void initGL() {
		try {
			// setup ogl
			FloatBuffer pos = BufferUtils.createFloatBuffer(4).put(new float[] { 5.0f, 5.0f, 10.0f, 0.0f});
			FloatBuffer red = BufferUtils.createFloatBuffer(4).put(new float[] { 0.8f, 0.1f, 0.0f, 1.0f});
			FloatBuffer green = BufferUtils.createFloatBuffer(4).put(new float[] { 0.0f, 0.8f, 0.2f, 1.0f});
			FloatBuffer blue = BufferUtils.createFloatBuffer(4).put(new float[] { 0.2f, 0.2f, 1.0f, 1.0f});

			pos.flip();
			red.flip();
			green.flip();
			blue.flip();

			glLight(GL_LIGHT0, GL_POSITION, pos);
			glEnable(GL_CULL_FACE);
			glEnable(GL_LIGHTING);
			glEnable(GL_LIGHT0);
			glEnable(GL_DEPTH_TEST);

			/* make the gears */
			gear1 = glGenLists(1);
			glNewList(gear1, GL_COMPILE);
			glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, red);
			gear(1.0f, 4.0f, 1.0f, 20, 0.7f);
			glEndList();

			gear2 = glGenLists(1);
			glNewList(gear2, GL_COMPILE);
			glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, green);
			gear(0.5f, 2.0f, 2.0f, 10, 0.7f);
			glEndList();

			gear3 = glGenLists(1);
			glNewList(gear3, GL_COMPILE);
			glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, blue);
			gear(1.3f, 2.0f, 0.5f, 10, 0.7f);
			glEndList();
			glEnable(GL_NORMALIZE);
			glMatrixMode(GL_PROJECTION);

			System.err.println("LWJGL: " + Sys.getVersion() + " / " + LWJGLUtil.getPlatformName());
			System.err.println("GL_VENDOR: " + glGetString(GL_VENDOR));
			System.err.println("GL_RENDERER: " + glGetString(GL_RENDERER));
			System.err.println("GL_VERSION: " + glGetString(GL_VERSION));
			System.err.println();
			System.err.println("glLoadTransposeMatrixfARB() supported: " + GLContext.getCapabilities().GL_ARB_transpose_matrix);

			if (!GLContext.getCapabilities().GL_ARB_transpose_matrix) {
				// --- not using extensions
				glLoadIdentity();
			} else {
				// --- using extensions
				final FloatBuffer identityTranspose = BufferUtils.createFloatBuffer(16).put(
						new float[] { 1, 0, 0, 0, 0, 1, 0, 0,
							0, 0, 1, 0, 0, 0, 0, 1});
				identityTranspose.flip();
				glLoadTransposeMatrixARB(identityTranspose);
			}
			float h = (float) display_parent.getHeight() / (float) display_parent.getWidth();
			glFrustum(-1.0f, 1.0f, -h, h, 5.0f, 60.0f);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			glTranslatef(0.0f, 0.0f, -40.0f);
		} catch (Exception e) {
			System.err.println(e);
			running = false;
		}
	}

	/**
	 * Draw a gear wheel.  You'll probably want to call this function when
	 * building a display list since we do a lot of trig here.
	 *
	 * @param inner_radius radius of hole at center
	 * @param outer_radius radius at center of teeth
	 * @param width width of gear
	 * @param teeth number of teeth
	 * @param tooth_depth depth of tooth
	 */
	private void gear(float inner_radius, float outer_radius, float width, int teeth, float tooth_depth) {
		int i;
		float r0, r1, r2;
		float angle, da;
		float u, v, len;

		r0 = inner_radius;
		r1 = outer_radius - tooth_depth / 2.0f;
		r2 = outer_radius + tooth_depth / 2.0f;
		da = 2.0f * (float) Math.PI / teeth / 4.0f;
		glShadeModel(GL_FLAT);
		glNormal3f(0.0f, 0.0f, 1.0f);
		/* draw front face */
		glBegin(GL_QUAD_STRIP);
		for (i = 0; i <= teeth; i++) {
			angle = i * 2.0f * (float) Math.PI / teeth;
			glVertex3f(r0 * (float) Math.cos(angle), r0 * (float) Math.sin(angle), width * 0.5f);
			glVertex3f(r1 * (float) Math.cos(angle), r1 * (float) Math.sin(angle), width * 0.5f);
			if (i < teeth) {
				glVertex3f(r0 * (float) Math.cos(angle), r0 * (float) Math.sin(angle), width * 0.5f);
				glVertex3f(r1 * (float) Math.cos(angle + 3.0f * da), r1 * (float) Math.sin(angle + 3.0f * da),
						width * 0.5f);
			}
		}
		glEnd();

		/* draw front sides of teeth */
		glBegin(GL_QUADS);
		for (i = 0; i < teeth; i++) {
			angle = i * 2.0f * (float) Math.PI / teeth;
			glVertex3f(r1 * (float) Math.cos(angle), r1 * (float) Math.sin(angle), width * 0.5f);
			glVertex3f(r2 * (float) Math.cos(angle + da), r2 * (float) Math.sin(angle + da), width * 0.5f);
			glVertex3f(r2 * (float) Math.cos(angle + 2.0f * da), r2 * (float) Math.sin(angle + 2.0f * da), width * 0.5f);
			glVertex3f(r1 * (float) Math.cos(angle + 3.0f * da), r1 * (float) Math.sin(angle + 3.0f * da), width * 0.5f);
		}
		glEnd();

		/* draw back face */
		glBegin(GL_QUAD_STRIP);
		for (i = 0; i <= teeth; i++) {
			angle = i * 2.0f * (float) Math.PI / teeth;
			glVertex3f(r1 * (float) Math.cos(angle), r1 * (float) Math.sin(angle), -width * 0.5f);
			glVertex3f(r0 * (float) Math.cos(angle), r0 * (float) Math.sin(angle), -width * 0.5f);
			glVertex3f(r1 * (float) Math.cos(angle + 3 * da), r1 * (float) Math.sin(angle + 3 * da), -width * 0.5f);
			glVertex3f(r0 * (float) Math.cos(angle), r0 * (float) Math.sin(angle), -width * 0.5f);
		}
		glEnd();

		/* draw back sides of teeth */
		glBegin(GL_QUADS);
		for (i = 0; i < teeth; i++) {
			angle = i * 2.0f * (float) Math.PI / teeth;
			glVertex3f(r1 * (float) Math.cos(angle + 3 * da), r1 * (float) Math.sin(angle + 3 * da), -width * 0.5f);
			glVertex3f(r2 * (float) Math.cos(angle + 2 * da), r2 * (float) Math.sin(angle + 2 * da), -width * 0.5f);
			glVertex3f(r2 * (float) Math.cos(angle + da), r2 * (float) Math.sin(angle + da), -width * 0.5f);
			glVertex3f(r1 * (float) Math.cos(angle), r1 * (float) Math.sin(angle), -width * 0.5f);
		}
		glEnd();

		/* draw outward faces of teeth */
		glBegin(GL_QUAD_STRIP);
		for (i = 0; i < teeth; i++) {
			angle = i * 2.0f * (float) Math.PI / teeth;
			glVertex3f(r1 * (float) Math.cos(angle), r1 * (float) Math.sin(angle), width * 0.5f);
			glVertex3f(r1 * (float) Math.cos(angle), r1 * (float) Math.sin(angle), -width * 0.5f);
			u = r2 * (float) Math.cos(angle + da) - r1 * (float) Math.cos(angle);
			v = r2 * (float) Math.sin(angle + da) - r1 * (float) Math.sin(angle);
			len = (float) Math.sqrt(u * u + v * v);
			u /= len;
			v /= len;
			glNormal3f(v, -u, 0.0f);
			glVertex3f(r2 * (float) Math.cos(angle + da), r2 * (float) Math.sin(angle + da), width * 0.5f);
			glVertex3f(r2 * (float) Math.cos(angle + da), r2 * (float) Math.sin(angle + da), -width * 0.5f);
			glNormal3f((float) Math.cos(angle), (float) Math.sin(angle), 0.0f);
			glVertex3f(r2 * (float) Math.cos(angle + 2 * da), r2 * (float) Math.sin(angle + 2 * da), width * 0.5f);
			glVertex3f(r2 * (float) Math.cos(angle + 2 * da), r2 * (float) Math.sin(angle + 2 * da), -width * 0.5f);
			u = r1 * (float) Math.cos(angle + 3 * da) - r2 * (float) Math.cos(angle + 2 * da);
			v = r1 * (float) Math.sin(angle + 3 * da) - r2 * (float) Math.sin(angle + 2 * da);
			glNormal3f(v, -u, 0.0f);
			glVertex3f(r1 * (float) Math.cos(angle + 3 * da), r1 * (float) Math.sin(angle + 3 * da), width * 0.5f);
			glVertex3f(r1 * (float) Math.cos(angle + 3 * da), r1 * (float) Math.sin(angle + 3 * da), -width * 0.5f);
			glNormal3f((float) Math.cos(angle), (float) Math.sin(angle), 0.0f);
		}
		glVertex3f(r1 * (float) Math.cos(0), r1 * (float) Math.sin(0), width * 0.5f);
		glVertex3f(r1 * (float) Math.cos(0), r1 * (float) Math.sin(0), -width * 0.5f);
		glEnd();

		glShadeModel(GL_SMOOTH);
		/* draw inside radius cylinder */
		glBegin(GL_QUAD_STRIP);
		for (i = 0; i <= teeth; i++) {
			angle = i * 2.0f * (float) Math.PI / teeth;
			glNormal3f(-(float) Math.cos(angle), -(float) Math.sin(angle), 0.0f);
			glVertex3f(r0 * (float) Math.cos(angle), r0 * (float) Math.sin(angle), -width * 0.5f);
			glVertex3f(r0 * (float) Math.cos(angle), r0 * (float) Math.sin(angle), width * 0.5f);
		}
		glEnd();
	}
}