import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.lwjgl.input.*;
 

public final class Game {
     static {
         try {
             DisplayMode[] modes = Display.getAvailableDisplayModes();
             System.out.println("Available display modes:");
             for (int i = 0; i < modes.length; i ++)
                 System.out.println(modes[i]);
             // For now let's just pick a mode we're certain to have
             Display.create(new DisplayMode(640, 480, 16, 60), true);
             System.out.println("Created display.");
         } catch (Exception e) {
             System.err.println("Failed to create display due to "+e);
             System.exit(1);
         }
     }
 
    public static final GL gl = new GL(16, 0, 16, 8);
    public static final GLU glu = new GLU(gl);
     static {
         try {
             gl.create();
             System.out.println("Created OpenGL.");
         } catch (Exception e) {
             System.err.println("Failed to create OpenGL due to "+e);
             System.exit(1);
         }
     }
 
    /** Is the game finished? */
     private static boolean finished;
 
    /** A rotating square! */
     private static float angle;
 
    /**
      * No construction allowed
      */
     private Game() {
     }
 
    public static void main(String[] arguments) {
         try {
             init();
             while (!finished) {
//                 Keyboard.poll();
                 mainLoop();
                 render();
                 gl.swapBuffers();
             }   
         } catch (Throwable t) {
             t.printStackTrace();
         } finally {
             cleanup();
         }
     }
 
    /**
      * All calculations are done in here
      */
     private static void mainLoop() {
         angle += 1f;
         if (angle > 360.0f)
             angle = 0.0f;
 
        Mouse.poll();
        if (Mouse.dx != 0 || Mouse.dy != 0)
            System.out.println("Mouse moved " + Mouse.dx + " " + Mouse.dy);
        for (int i = 0; i < 8; i++)
            if (Mouse.isButtonDown(i))
                System.out.println("Button " + i + " down");
/*        Keyboard.poll(); 
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
             finished = true;*/
        Keyboard.read();
        if (Keyboard.getNumKeyboardEvents() > 0) {
            Keyboard.next();
            if (Keyboard.key == Keyboard.KEY_ESCAPE && Keyboard.state)
                finished = true;
        }
     }
 
    /**
      * All rendering is done in here
      */
     private static void render() {
         gl.clear(GL.COLOR_BUFFER_BIT);
         gl.pushMatrix();
         gl.translatef(Display.getWidth() / 2, Display.getHeight() / 2, 0.0f);
         gl.rotatef(angle, 0, 0, 1.0f);
         gl.begin(GL.QUADS);
         gl.vertex2i(-50, -50);
         gl.vertex2i(50, -50);
         gl.vertex2i(50, 50);
         gl.vertex2i(-50, 50);
         gl.end();
         gl.popMatrix();
     }
 
    /**
      * Initialize
      */
     private static void init() throws Exception {
         Keyboard.create();
         Keyboard.enableBuffer();
         Mouse.create();
         // Go into orthographic projection mode.
         gl.matrixMode(GL.PROJECTION);
         gl.loadIdentity();
         glu.ortho2D(0, Display.getWidth(), 0, Display.getHeight());
         gl.matrixMode(GL.MODELVIEW);
         gl.loadIdentity();
         gl.viewport(0, 0, Display.getWidth(), Display.getHeight());
         // Fix the refresh rate to the display frequency.
         gl.wglSwapIntervalEXT(1);
     }
 
    /**
      * Cleanup
      */
     private static void cleanup() {
         Keyboard.destroy();
         Mouse.destroy();
         gl.destroy();
         Display.destroy();
     }
 }
