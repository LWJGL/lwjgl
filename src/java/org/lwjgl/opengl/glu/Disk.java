package org.lwjgl.opengl.glu;

import org.lwjgl.opengl.GL;

/**
 * Disk.java
 * 
 * 
 * Created 23-dec-2003
 * @author Erik Duijs
 */
public class Disk extends Quadric implements GLUConstants {

	/**
	 * Constructor for Disk.
	 */
	public Disk() {
		super();
	}

    /**
     * renders a disk on the z = 0  plane.  The disk has a radius of
     * outerRadius, and contains a concentric circular hole with a radius of
     * innerRadius. If innerRadius is 0, then no hole is generated. The disk is
     * subdivided around the z axis into slices (like pizza slices), and also
     * about the z axis into rings (as specified by slices and loops,
     * respectively).
     *
     * With respect to orientation, the +z side of the disk is considered to be
     * "outside" (see glu.quadricOrientation).  This means that if the orientation
     * is set to GLU.OUTSIDE, then any normals generated point along the +z axis.
     * Otherwise, they point along the -z axis.
     *
     * If texturing is turned on (with glu.quadricTexture), texture coordinates are
     * generated linearly such that where r=outerRadius, the value at (r, 0, 0) is
     * (1, 0.5), at (0, r, 0) it is (0.5, 1), at (-r, 0, 0) it is (0, 0.5), and at
     * (0, -r, 0) it is (0.5, 0).
     */
	public void draw(float innerRadius, float outerRadius, int slices, int loops)
	{
	   float da, dr;

	   /* Normal vectors */
	   if (super.normals != GLU_NONE) {
	      if (super.orientation == GLU_OUTSIDE) {
		 GL.glNormal3f(0.0f, 0.0f, +1.0f);
	      }
	      else {
		 GL.glNormal3f(0.0f, 0.0f, -1.0f);
	      }
	   }
	
	   da = 2.0f * PI / slices;
	   dr = (outerRadius - innerRadius) /  loops;
	
	   switch (super.drawStyle) {
	   case GLU_FILL:
	      {
		 /* texture of a gluDisk is a cut out of the texture unit square
		  * x, y in [-outerRadius, +outerRadius]; s, t in [0, 1]
		  * (linear mapping)
		  */
		 float dtc = 2.0f * outerRadius;
		 float sa, ca;
		 float r1 = innerRadius;
		 int l;
		 for (l = 0; l < loops; l++) {
		    float r2 = r1 + dr;
		    if (super.orientation == GLU_OUTSIDE) {
		       int s;
		       GL.glBegin(GL.GL_QUAD_STRIP);
		       for (s = 0; s <= slices; s++) {
			  float a;
			  if (s == slices)
			     a = 0.0f;
			  else
			     a = s * da;
			  sa = sin(a);
			  ca = cos(a);
			  TXTR_COORD(0.5f + sa * r2 / dtc, 0.5f + ca * r2 / dtc);
			  GL.glVertex2f(r2 * sa, r2 * ca);
			  TXTR_COORD(0.5f + sa * r1 / dtc, 0.5f + ca * r1 / dtc);
			  GL.glVertex2f(r1 * sa, r1 * ca);
		       }
		       GL.glEnd();
		    }
		    else {
		       int s;
		       GL.glBegin(GL.GL_QUAD_STRIP);
		       for (s = slices; s >= 0; s--) {
			  float a;
			  if (s == slices)
			     a = 0.0f;
			  else
			     a = s * da;
			  sa = sin(a);
			  ca = cos(a);
			  TXTR_COORD(0.5f - sa * r2 / dtc, 0.5f + ca * r2 / dtc);
			  GL.glVertex2f(r2 * sa, r2 * ca);
			  TXTR_COORD(0.5f - sa * r1 / dtc, 0.5f + ca * r1 / dtc);
			  GL.glVertex2f(r1 * sa, r1 * ca);
		       }
		       GL.glEnd();
		    }
		    r1 = r2;
		 }
		 break;
	      }
	   case GLU_LINE:
	      {
		 int l, s;
		 /* draw loops */
		 for (l = 0; l <= loops; l++) {
		    float r = innerRadius + l * dr;
		    GL.glBegin(GL.GL_LINE_LOOP);
		    for (s = 0; s < slices; s++) {
		       float a = s * da;
		       GL.glVertex2f(r * sin(a), r * cos(a));
		    }
		    GL.glEnd();
		 }
		 /* draw spokes */
		 for (s = 0; s < slices; s++) {
		    float a = s * da;
		    float x = sin(a);
		    float y = cos(a);
		    GL.glBegin(GL.GL_LINE_STRIP);
		    for (l = 0; l <= loops; l++) {
		       float r = innerRadius + l * dr;
		       GL.glVertex2f(r * x, r * y);
		    }
		    GL.glEnd();
		 }
		 break;
	      }
	   case GLU_POINT:
	      {
		 int s;
		 GL.glBegin(GL.GL_POINTS);
		 for (s = 0; s < slices; s++) {
		    float a = s * da;
		    float x = sin(a);
		    float y = cos(a);
		    int l;
		    for (l = 0; l <= loops; l++) {
		       float r = innerRadius * l * dr;
		       GL.glVertex2f(r * x, r * y);
		    }
		 }
		 GL.glEnd();
		 break;
	      }
	   case GLU_SILHOUETTE:
	      {
		 if (innerRadius != 0.0) {
		    float a;
		    GL.glBegin(GL.GL_LINE_LOOP);
		    for (a = 0.0f; a < 2.0 * PI; a += da) {
		       float x = innerRadius * sin(a);
		       float y = innerRadius * cos(a);
		       GL.glVertex2f(x, y);
		    }
		    GL.glEnd();
		 }
		 {
		    float a;
		    GL.glBegin(GL.GL_LINE_LOOP);
		    for (a = 0; a < 2.0f * PI; a += da) {
		       float x = outerRadius * sin(a);
		       float y = outerRadius * cos(a);
		       GL.glVertex2f(x, y);
		    }
		    GL.glEnd();
		 }
		 break;
	      }
	   default:
	      return;
	   }
	}

}
