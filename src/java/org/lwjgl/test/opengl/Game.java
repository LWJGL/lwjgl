/*
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of
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

/**
 * $Id$
 *
 * Simple java test program.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

package org.lwjgl.test.opengl;

import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.lwjgl.input.*;

import java.nio.*; 

public final class Game {
	static {
		try {
      //find first display mode that allows us 640*480*16
			int mode = -1;
			DisplayMode[] modes = Display.getAvailableDisplayModes();
			for (int i = 0; i < modes.length; i++) {
				if (modes[i].width == 640
					&& modes[i].height == 480
					&& modes[i].bpp >= 16) {
					mode = i;
					break;
				}
			}

			//select above found displaymode
			Display.create(modes[mode], false, "LWJGL Game Example");
			System.out.println("Created display.");
		} catch (Exception e) {
			System.err.println("Failed to create display due to " + e);
			System.exit(1);
		}
	}
 
    public static final GL gl = new GL();
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
        if (Mouse.dx != 0 || Mouse.dy != 0 || Mouse.dwheel != 0)
            System.out.println("Mouse moved " + Mouse.dx + " " + Mouse.dy + " " + Mouse.dwheel);
        for (int i = 0; i < Mouse.buttonCount; i++)
            if (Mouse.isButtonDown(i))
                System.out.println("Button " + i + " down");
/*        Keyboard.poll(); 
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
             finished = true;*/
        Keyboard.read();
        for (int i = 0; i < Keyboard.getNumKeyboardEvents(); i++) {
            Keyboard.next();
            if (Keyboard.key == Keyboard.KEY_ESCAPE && Keyboard.state)
                finished = true;
            if (Keyboard.key == Keyboard.KEY_T && Keyboard.state)
                System.out.println("Current time: " + Sys.getTime());
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
         Sys.setTime(0);
         Sys.setProcessPriority(Sys.HIGH_PRIORITY);
         System.out.println("Timer resolution: " + Sys.getTimerResolution());
         // Go into orthographic projection mode.
         gl.matrixMode(GL.PROJECTION);
         gl.loadIdentity();
         glu.ortho2D(0, Display.getWidth(), 0, Display.getHeight());
         gl.matrixMode(GL.MODELVIEW);
         gl.loadIdentity();
         gl.viewport(0, 0, Display.getWidth(), Display.getHeight());
         ByteBuffer num_tex_units_buf = ByteBuffer.allocateDirect(4);
         num_tex_units_buf.order(ByteOrder.nativeOrder());
         int buf_addr = Sys.getDirectBufferAddress(num_tex_units_buf);
         gl.getIntegerv(GL.MAX_TEXTURE_UNITS_ARB, buf_addr);
         System.out.println("Number of texture units: " + num_tex_units_buf.getInt());
         // Fix the refresh rate to the display frequency.
//         gl.wglSwapIntervalEXT(1);
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
