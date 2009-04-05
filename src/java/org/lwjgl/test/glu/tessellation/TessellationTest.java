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
package org.lwjgl.test.glu.tessellation;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.GLUtessellator;

public class TessellationTest {
	private GLUtessellator tesselator;
	
	void init()
	{
		// Create a new tessellation object 
		tesselator = GLU.gluNewTess();
	
		// Set callback functions
		TessCallback callback = new TessCallback();
		tesselator.gluTessCallback(GLU.GLU_TESS_VERTEX, callback);
		tesselator.gluTessCallback(GLU.GLU_TESS_BEGIN, callback);
		tesselator.gluTessCallback(GLU.GLU_TESS_END, callback);
		tesselator.gluTessCallback(GLU.GLU_TESS_COMBINE, callback);
	}

	void setWindingRule(int windingRule)
	{
		// Set the winding rule
		tesselator.gluTessProperty(GLU.GLU_TESS_WINDING_RULE, windingRule); 
	}
	 
	void renderContour(double obj_data[][], int num_vertices)
	{
		for (int x = 0; x < num_vertices; x++) //loop through the vertices
		{
			tesselator.gluTessVertex(obj_data[x], 0, new VertexData(obj_data[x])); //store the vertex
		}
	}
	
	void beginPolygon()
	{
		tesselator.gluTessBeginPolygon(null);
	}

	void endPolygon()
	{
		tesselator.gluTessEndPolygon();
	}
	
	void beginContour()
	{
		tesselator.gluTessBeginContour();
	}

	void endContour()
	{
		tesselator.gluTessEndContour();
	}
	
	void end()
	{
		tesselator.gluDeleteTess();
	}

	private void createDisplay() throws LWJGLException {
		int width = 300;
		int height = 300;
		
		Display.setDisplayMode(new DisplayMode(width,height));
		Display.create();
		Display.setVSyncEnabled(true);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);        
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);                    
        
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
        GL11.glClearDepth(1);                                       
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	private void loop() {
		while (true) {
			render();
			Display.update();
			Display.sync(100);
			
			if (Display.isCloseRequested()) {
				System.exit(0);
			}
		}
	}
	
	private void render() {
		GL11.glTranslatef(150,125,0);
		
		GL11.glScalef(50,50,1);
		// first polygon: a star-5 vertices and color information
		double star[][] = { {0.6f,  -0.1f, 0f, 1.0f, 1.0f, 1.0f},
                {1.35f, 1.4f, 0f, 1.0f, 1.0f, 1.0f},
                {2.1f,  -0.1f, 0f, 1.0f, 1.0f, 1.0f},
                {0.6f, 0.9f, 0f, 1.0f, 1.0f, 1.0f},
                {2.1f, 0.9f, 0f, 1.0f, 1.0f, 1.0f} };

		//second polygon: a quad-4 vertices; first contour
		double quad[][] = { {0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f},
		                {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f},
		                {1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f},
		                {0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f} };
		
		//second polygon: a triangle-3 vertices; second contour
		double tri[][] = {{0.3f, 0.3f, 0.0f, 0.0f, 0.0f, 0.0f},
		               {0.7f, 0.3f, 0.0f, 0.0f, 0.0f, 0.0f},
		               {0.5f, 0.7f, 0.0f, 0.0f, 0.0f, 0.0f} };

		// render the first polygon: the textured star
		
		// set winding rule to positive
		setWindingRule(GLU.GLU_TESS_WINDING_POSITIVE);
		beginPolygon();
		beginContour();
		renderContour(star, 5);
		endContour();
		endPolygon();

		// render the second polygon: triangle cut out of a quad

		GL11.glTranslatef(-2,0,0);
		// set winding rule to odd
		setWindingRule(GLU.GLU_TESS_WINDING_ODD);
		// begin the new polygon
		beginPolygon();
		beginContour();
		renderContour(quad, 4);
		endContour();
		beginContour();
		renderContour(tri, 3);
		endContour();
		endPolygon();
		// delete the tess object
		end();
	}
	
	private void start() throws LWJGLException {
		createDisplay();
		init();
		loop();
	}
	
	public static void main(String[] argv) throws LWJGLException {
		TessellationTest test = new TessellationTest();
		test.start();
	}
}
