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
import org.lwjgl.opengl.arb.*;
import org.lwjgl.input.*;

import java.nio.*;

public final class VBOIndexTest {
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
			if (mode != -1) {
				//select above found displaymode
				System.out.println("Setting display mode to "+modes[mode]);
				Display.setDisplayMode(modes[mode]);
				System.out.println("Created display.");
			}
		} catch (Exception e) {
			System.err.println("Failed to create display due to " + e);
		}
	}
 
     static {
         try {
             Window.create("LWJGL Game Example", 16, 0, 0,0);
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
	private static int buffer_id;
	private static int indices_buffer_id;
	private static FloatBuffer vertices;
	private static ByteBuffer mapped_buffer = null;
	private static FloatBuffer mapped_float_buffer = null;
	private static IntBuffer indices;
	private static ByteBuffer mapped_indices_buffer = null;
	private static IntBuffer mapped_indices_int_buffer = null;
 
    public static void main(String[] arguments) {
         try {
             init();
             while (!finished) {
             	 Window.update();
             	 
             	 if (Window.isMinimized())
             	 	Thread.sleep(200);
             	 else if (Window.isCloseRequested())
             	 	System.exit(0);
             	 
                 Keyboard.poll();
                 mainLoop();
                 render();
                 Window.paint();
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
       GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
       GL11.glPushMatrix();
       GL11.glTranslatef(Display.getWidth() / 2, Display.getHeight() / 2, 0.0f);
       GL11.glRotatef(angle, 0, 0, 1.0f);


	ByteBuffer new_mapped_buffer = ARBVertexBufferObject.glMapBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, ARBVertexBufferObject.GL_WRITE_ONLY_ARB, 2*4*4, mapped_buffer);
	if (new_mapped_buffer != mapped_buffer)
		mapped_float_buffer = new_mapped_buffer.order(ByteOrder.nativeOrder()).asFloatBuffer();
	mapped_buffer = new_mapped_buffer;

        new_mapped_buffer = ARBVertexBufferObject.glMapBufferARB(ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB, ARBVertexBufferObject.GL_WRITE_ONLY_ARB, 4*4, mapped_indices_buffer);
	if (new_mapped_buffer != mapped_indices_buffer)
		mapped_indices_int_buffer = new_mapped_buffer.order(ByteOrder.nativeOrder()).asIntBuffer();

	mapped_float_buffer.rewind();
	vertices.rewind();
	mapped_float_buffer.put(vertices);

        mapped_indices_int_buffer.rewind();
        indices.rewind();
        mapped_indices_int_buffer.put(indices);
	if (ARBVertexBufferObject.glUnmapBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB) && 
            ARBVertexBufferObject.glUnmapBufferARB(ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB)) {
		GL11.glDrawElements(GL11.GL_QUADS, 4, GL11.GL_UNSIGNED_INT, 0);
        }
       GL11.glPopMatrix();
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
       GL11.glMatrixMode(GL11.GL_PROJECTION);
       GL11.glLoadIdentity();
         GLU.gluOrtho2D(0, Display.getWidth(), 0, Display.getHeight());
       GL11.glMatrixMode(GL11.GL_MODELVIEW);
       GL11.glLoadIdentity();
       GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
	if (!GLCaps.GL_ARB_vertex_buffer_object) {
		System.out.println("ARB VBO not supported!");
		System.exit(1);
	}
	IntBuffer int_buffer = ByteBuffer.allocateDirect(8).order(ByteOrder.nativeOrder()).asIntBuffer();
	ARBVertexBufferObject.glGenBuffersARB(int_buffer);
	buffer_id = int_buffer.get(0);
        indices_buffer_id = int_buffer.get(1);
	ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, buffer_id);
	ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB, indices_buffer_id);
	vertices = ByteBuffer.allocateDirect(2*4*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
	vertices.put(-50).put(-50).put(50).put(-50).put(50).put(50).put(-50).put(50);
        vertices.rewind();
        indices = ByteBuffer.allocateDirect(4*4).order(ByteOrder.nativeOrder()).asIntBuffer();
        indices.put(0).put(1).put(2).put(3);
        indices.rewind();
	ARBVertexBufferObject.glBufferDataARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, 2*4*4, (ByteBuffer)null, ARBVertexBufferObject.GL_STREAM_DRAW_ARB);
	ARBVertexBufferObject.glBufferDataARB(ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB, 4*4, (ByteBuffer)null, ARBVertexBufferObject.GL_STREAM_DRAW_ARB);
	GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
	GL11.glVertexPointer(2, GL11.GL_FLOAT, 0, 0);
     }
 
    /**
      * Cleanup
      */
     private static void cleanup() {
	IntBuffer int_buffer = ByteBuffer.allocateDirect(8).order(ByteOrder.nativeOrder()).asIntBuffer();
	int_buffer.put(0, buffer_id);
        int_buffer.put(1, indices_buffer_id);
	ARBVertexBufferObject.glDeleteBuffersARB(int_buffer);
         Keyboard.destroy();
         Mouse.destroy();
         Window.destroy();
         try {
         	Display.resetDisplayMode();
         } catch (Exception e) {
         	e.printStackTrace();
         }
     }
 }
