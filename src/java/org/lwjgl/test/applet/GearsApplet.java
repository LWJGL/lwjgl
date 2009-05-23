package org.lwjgl.test.applet;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ARBTransposeMatrix;
import org.lwjgl.opengl.Display;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class GearsApplet extends Applet {
	
	Canvas display_parent;
	Thread gameThread;
	
	boolean running = false;
	
	private float	view_rotx	= 20.0f;
	private float	view_roty	= 30.0f;
	private float	view_rotz	= 0.0f;
	private int		gear1;
	private int		gear2;
	private int		gear3;
	private float	angle			= 0.0f;
	
	
	boolean keyDown = false;
	
	public void destroy() {
		remove(display_parent);
		super.destroy();
		System.out.println("Clear up");
	}
	
	private void destroyLWJGL() {
		stopApplet();
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
	
	public void startApplet() {
		gameThread = new Thread() {
			public void run() {
				running = true;
				try {
					System.out.println("display_parent.isDisplayable() = " + display_parent.isDisplayable());
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

	public void stopApplet() {
		running = false;
	}

	public void init() {
		setLayout(new BorderLayout());
		try {
			display_parent = new Canvas() {
				public final void addNotify() {
					super.addNotify();
					startLWJGL();
				}
				public final void removeNotify() {
					destroyLWJGL();
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
/*				System.out.println(fps + " frames 2 in " + (float) (timeUsed / 1000f) + " seconds = "
						+ (fps / (timeUsed / 1000f)));*/
				fps = 0;
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
				view_roty += .1f;
			else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
				view_roty -= .1f;
			
			if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
				keyDown = true;
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
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glPushMatrix();
		GL11.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
		GL11.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(view_rotz, 0.0f, 0.0f, 1.0f);
		GL11.glPushMatrix();
		GL11.glTranslatef(-3.0f, -2.0f, 0.0f);
		GL11.glRotatef(angle, 0.0f, 0.0f, 1.0f);
		GL11.glCallList(gear1);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(3.1f, -2.0f, 0.0f);
		GL11.glRotatef(-2.0f * angle - 9.0f, 0.0f, 0.0f, 1.0f);
		GL11.glCallList(gear2);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(-3.1f, 4.2f, 0.0f);
		GL11.glRotatef(-2.0f * angle - 25.0f, 0.0f, 0.0f, 1.0f);
		GL11.glCallList(gear3);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	protected void initGL() {
		try {
			// setup ogl
			FloatBuffer pos = FloatBuffer.wrap(new float[] { 5.0f, 5.0f, 10.0f, 0.0f});
			FloatBuffer red = FloatBuffer.wrap(new float[] { 0.8f, 0.1f, 0.0f, 1.0f});
			FloatBuffer green = FloatBuffer.wrap(new float[] { 0.0f, 0.8f, 0.2f, 1.0f});
			FloatBuffer blue = FloatBuffer.wrap(new float[] { 0.2f, 0.2f, 1.0f, 1.0f});
			GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, pos);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_LIGHT0);
			GL11.glEnable(GL11.GL_DEPTH_TEST);

			/* make the gears */
			gear1 = GL11.glGenLists(1);
			GL11.glNewList(gear1, GL11.GL_COMPILE);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, red);
			gear(1.0f, 4.0f, 1.0f, 20, 0.7f);
			GL11.glEndList();
			gear2 = GL11.glGenLists(1);
			GL11.glNewList(gear2, GL11.GL_COMPILE);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, green);
			gear(0.5f, 2.0f, 2.0f, 10, 0.7f);
			GL11.glEndList();
			gear3 = GL11.glGenLists(1);
			GL11.glNewList(gear3, GL11.GL_COMPILE);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, blue);
			gear(1.3f, 2.0f, 0.5f, 10, 0.7f);
			GL11.glEndList();
			GL11.glEnable(GL11.GL_NORMALIZE);
			GL11.glMatrixMode(GL11.GL_PROJECTION);
/*			System.err.println("GL_VENDOR: " + GL11.glGetString(GL11.GL_VENDOR));
			System.err.println("GL_RENDERER: " + GL11.glGetString(GL11.GL_RENDERER));
			System.err.println("GL_VERSION: " + GL11.glGetString(GL11.GL_VERSION));
			System.err.println();
			System.err.println("glLoadTransposeMatrixfARB() supported: " + GLContext.getCapabilities().GL_ARB_transpose_matrix);*/
			if (!GLContext.getCapabilities().GL_ARB_transpose_matrix) {
				// --- not using extensions
				GL11.glLoadIdentity();
			} else {
				// --- using extensions
				final FloatBuffer identityTranspose = BufferUtils.createFloatBuffer(16).put(
						new float[] { 1, 0, 0, 0, 0, 1, 0, 0,
							0, 0, 1, 0, 0, 0, 0, 1});
				identityTranspose.flip();
				ARBTransposeMatrix.glLoadTransposeMatrixARB(identityTranspose);
			}
			float h = (float) 300 / (float) 300;
			GL11.glFrustum(-1.0f, 1.0f, -h, h, 5.0f, 60.0f);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();
			GL11.glTranslatef(0.0f, 0.0f, -40.0f);
		} catch (Exception e) {
			System.err.println(e);
			stopApplet();
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
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glNormal3f(0.0f, 0.0f, 1.0f);
		/* draw front face */
		GL11.glBegin(GL11.GL_QUAD_STRIP);
		for (i = 0; i <= teeth; i++) {
			angle = i * 2.0f * (float) Math.PI / teeth;
			GL11.glVertex3f(r0 * (float) Math.cos(angle), r0 * (float) Math.sin(angle), width * 0.5f);
			GL11.glVertex3f(r1 * (float) Math.cos(angle), r1 * (float) Math.sin(angle), width * 0.5f);
			if (i < teeth) {
				GL11.glVertex3f(r0 * (float) Math.cos(angle), r0 * (float) Math.sin(angle), width * 0.5f);
				GL11.glVertex3f(r1 * (float) Math.cos(angle + 3.0f * da), r1 * (float) Math.sin(angle + 3.0f * da),
						width * 0.5f);
			}
		}
		GL11.glEnd();

		/* draw front sides of teeth */
		GL11.glBegin(GL11.GL_QUADS);
		for (i = 0; i < teeth; i++) {
			angle = i * 2.0f * (float) Math.PI / teeth;
			GL11.glVertex3f(r1 * (float) Math.cos(angle), r1 * (float) Math.sin(angle), width * 0.5f);
			GL11.glVertex3f(r2 * (float) Math.cos(angle + da), r2 * (float) Math.sin(angle + da), width * 0.5f);
			GL11.glVertex3f(r2 * (float) Math.cos(angle + 2.0f * da), r2 * (float) Math.sin(angle + 2.0f * da), width * 0.5f);
			GL11.glVertex3f(r1 * (float) Math.cos(angle + 3.0f * da), r1 * (float) Math.sin(angle + 3.0f * da), width * 0.5f);
		}
		GL11.glEnd();

		/* draw back face */
		GL11.glBegin(GL11.GL_QUAD_STRIP);
		for (i = 0; i <= teeth; i++) {
			angle = i * 2.0f * (float) Math.PI / teeth;
			GL11.glVertex3f(r1 * (float) Math.cos(angle), r1 * (float) Math.sin(angle), -width * 0.5f);
			GL11.glVertex3f(r0 * (float) Math.cos(angle), r0 * (float) Math.sin(angle), -width * 0.5f);
			GL11.glVertex3f(r1 * (float) Math.cos(angle + 3 * da), r1 * (float) Math.sin(angle + 3 * da), -width * 0.5f);
			GL11.glVertex3f(r0 * (float) Math.cos(angle), r0 * (float) Math.sin(angle), -width * 0.5f);
		}
		GL11.glEnd();

		/* draw back sides of teeth */
		GL11.glBegin(GL11.GL_QUADS);
		for (i = 0; i < teeth; i++) {
			angle = i * 2.0f * (float) Math.PI / teeth;
			GL11.glVertex3f(r1 * (float) Math.cos(angle + 3 * da), r1 * (float) Math.sin(angle + 3 * da), -width * 0.5f);
			GL11.glVertex3f(r2 * (float) Math.cos(angle + 2 * da), r2 * (float) Math.sin(angle + 2 * da), -width * 0.5f);
			GL11.glVertex3f(r2 * (float) Math.cos(angle + da), r2 * (float) Math.sin(angle + da), -width * 0.5f);
			GL11.glVertex3f(r1 * (float) Math.cos(angle), r1 * (float) Math.sin(angle), -width * 0.5f);
		}
		GL11.glEnd();

		/* draw outward faces of teeth */
		GL11.glBegin(GL11.GL_QUAD_STRIP);
		for (i = 0; i < teeth; i++) {
			angle = i * 2.0f * (float) Math.PI / teeth;
			GL11.glVertex3f(r1 * (float) Math.cos(angle), r1 * (float) Math.sin(angle), width * 0.5f);
			GL11.glVertex3f(r1 * (float) Math.cos(angle), r1 * (float) Math.sin(angle), -width * 0.5f);
			u = r2 * (float) Math.cos(angle + da) - r1 * (float) Math.cos(angle);
			v = r2 * (float) Math.sin(angle + da) - r1 * (float) Math.sin(angle);
			len = (float) Math.sqrt(u * u + v * v);
			u /= len;
			v /= len;
			GL11.glNormal3f(v, -u, 0.0f);
			GL11.glVertex3f(r2 * (float) Math.cos(angle + da), r2 * (float) Math.sin(angle + da), width * 0.5f);
			GL11.glVertex3f(r2 * (float) Math.cos(angle + da), r2 * (float) Math.sin(angle + da), -width * 0.5f);
			GL11.glNormal3f((float) Math.cos(angle), (float) Math.sin(angle), 0.0f);
			GL11.glVertex3f(r2 * (float) Math.cos(angle + 2 * da), r2 * (float) Math.sin(angle + 2 * da), width * 0.5f);
			GL11.glVertex3f(r2 * (float) Math.cos(angle + 2 * da), r2 * (float) Math.sin(angle + 2 * da), -width * 0.5f);
			u = r1 * (float) Math.cos(angle + 3 * da) - r2 * (float) Math.cos(angle + 2 * da);
			v = r1 * (float) Math.sin(angle + 3 * da) - r2 * (float) Math.sin(angle + 2 * da);
			GL11.glNormal3f(v, -u, 0.0f);
			GL11.glVertex3f(r1 * (float) Math.cos(angle + 3 * da), r1 * (float) Math.sin(angle + 3 * da), width * 0.5f);
			GL11.glVertex3f(r1 * (float) Math.cos(angle + 3 * da), r1 * (float) Math.sin(angle + 3 * da), -width * 0.5f);
			GL11.glNormal3f((float) Math.cos(angle), (float) Math.sin(angle), 0.0f);
		}
		GL11.glVertex3f(r1 * (float) Math.cos(0), r1 * (float) Math.sin(0), width * 0.5f);
		GL11.glVertex3f(r1 * (float) Math.cos(0), r1 * (float) Math.sin(0), -width * 0.5f);
		GL11.glEnd();

		GL11.glShadeModel(GL11.GL_SMOOTH);
		/* draw inside radius cylinder */
		GL11.glBegin(GL11.GL_QUAD_STRIP);
		for (i = 0; i <= teeth; i++) {
			angle = i * 2.0f * (float) Math.PI / teeth;
			GL11.glNormal3f(-(float) Math.cos(angle), -(float) Math.sin(angle), 0.0f);
			GL11.glVertex3f(r0 * (float) Math.cos(angle), r0 * (float) Math.sin(angle), -width * 0.5f);
			GL11.glVertex3f(r0 * (float) Math.cos(angle), r0 * (float) Math.sin(angle), width * 0.5f);
		}
		GL11.glEnd();
	}
}
