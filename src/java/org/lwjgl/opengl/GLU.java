/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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

package org.lwjgl.opengl;

import org.lwjgl.Sys;

/**
 * $Id$
 *
 * GL Utilities library.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public class GLU implements GLUConstants {

	static {
		System.loadLibrary(Sys.getLibraryName());
	}

	/** Handle to GL */
	private final GL gl;

	/**
	 * Constructor for GLU.
	 */
	public GLU(GL gl) {
		this.gl = gl;
	}

	public native String errorString(int errCode);

	public native String getString(int name);

	public native void ortho2D(
		double left,
		double right,
		double bottom,
		double top);

	public native void perspective(
		double fovy,
		double aspect,
		double zNear,
		double zFar);

	public native void pickMatrix(
		double x,
		double y,
		double width,
		double height,
		int viewport /*int*/
	);

	public native void lookAt(
		double eyex,
		double eyey,
		double eyez,
		double centerx,
		double centery,
		double centerz,
		double upx,
		double upy,
		double upz);

	public native int project(
		double objx,
		double objy,
		double objz,
		int modelMatrix /*double*/
	, int projMatrix /*double*/
	, int viewport /*int*/
	, int winx /*double*/
	, int winy /*double*/
	, int winz /*double*/
	);

	public native int unProject(
		double winx,
		double winy,
		double winz,
		int modelMatrix /*double*/
	, int projMatrix /*double*/
	, int viewport /*int*/
	, int objx /*double*/
	, int objy /*double*/
	, int objz /*double*/
	);

	public native int scaleImage(
		int format,
		int widthin,
		int heightin,
		int typein,
		int datain /*void*/
	, int widthout, int heightout, int typeout, int dataout /*void*/
	);

	public native int build1DMipmaps(
		int target,
		int components,
		int width,
		int format,
		int type,
		int data /*void*/
	);

	public native int build2DMipmaps(
		int target,
		int components,
		int width,
		int height,
		int format,
		int type,
		int data /*void*/
	);
        
        /**
         * creates and returns a pointer to a new quadrics object. This
         * object must be referred to when calling quadrics rendering and control
         * functions.  A return value of zero means that there is not enough memory to
         * allocate the object
         *
         * @return adress to a new quadrics object
         */
        public native int newQuadric();
        
        /**
         * draws a cylinder oriented along the z axis. The base of the
         * cylinder is placed at z = 0, and the top at z=height. Like a sphere, a
         * cylinder is subdivided around the z axis into slices, and along the z axis
         * into stacks.
         *
         * Note that if topRadius is set to zero, then this routine will generate a
         * cone.
         *
         * If the orientation is set to GLU.OUTSIDE (with glu.quadricOrientation), then
         * any generated normals point away from the z axis. Otherwise, they point
         * toward the z axis.
         *
         * If texturing is turned on (with glu.quadricTexture), then texture
         * coordinates are generated so that t ranges linearly from 0.0 at z = 0 to
         * 1.0 at z = height, and s ranges from 0.0 at the +y axis, to 0.25 at the +x
         * axis, to 0.5 at the -y axis, to 0.75 at the -x axis, and back to 1.0 at the
         * +y axis.
         *
         * @param qobj  Specifies the quadrics object (created with glu.newQuadric).
         *
         * @param baseRadius  Specifies the radius of the cylinder at z = 0.
         *
         * @param topRadius   Specifies the radius of the cylinder at z = height.
         *
         * @param height      Specifies the height of the cylinder.
         *
         * @param slices      Specifies the number of subdivisions around the z axis.
         *
         * @param stacks      Specifies the number of subdivisions along the z axis.
         */
        public native void cylinder(
                int qobj,
                double baseRadius,
                double topRadius,
                double height,
                int slices,
                int stacks
        );
        
        /**
         * destroys the quadrics object and frees any memory used by
         * it.  Once glu.deleteQuadric has been called, the object cannot be used again.
         *
         * @param qobj pecifies the quadrics object to be destroyed (created with
	 * glu.newQuadric).
         */
        public native void deleteQuadric(
                int qobj
        );
        
        
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
        public native void disk(
                int target,
                double innerRadius,
                double outerRadius,
                int slices,
                int loops
        );

        /**
         * renders a partial disk on the z=0 plane.  A partial disk is
         * similar to a full disk, except that only the subset of the disk from
         * startAngle through startAngle + sweepAngle is included (where 0 degrees is
         * along the +y axis, 90 degrees along the +x axis, 180 along the -y axis, and
         * 270 along the -x axis).
         *
         * The partial disk has a radius	of outerRadius,	and contains a concentric
         * circular hole with a radius of innerRadius.  If innerRadius is zero, then
         * no hole is generated.  The partial disk is subdivided around the z axis
         * into slices (like pizza slices), and also about the z axis into rings (as
         * specified by slices and loops, respectively).
         *
         * With respect to orientation, the +z side of the partial disk is considered
         * to be outside (see gluQuadricOrientation). This means that if the
         * orientation is set to GLU_OUTSIDE, then any normals generated point along
         * the +z axis. Otherwise, they point along the -z axis.
         *
         * If texturing is turned on (with gluQuadricTexture), texture coordinates are
         * generated linearly such that where r=outerRadius, the value at (r, 0, 0) is
         * (1, 0.5), at (0, r, 0) it is (0.5, 1), at (-r, 0, 0) it is (0, 0.5), and at
         * (0, -r, 0) it is (0.5, 0).
         */
        public native void partialDisk(
                int target,
                double innerRadius,
                double outerRadius,
                int slices,
                int loops,
                double startAngle,
                double sweepAngle
        );

        /**
         * specifies the draw style for quadrics rendered with
         * qobj.  The legal values are as follows:
         *
         * GLU.FILL:       Quadrics are rendered with polygon primitives. The polygons
         *                 are drawn in a counterclockwise fashion with respect to
         *                 their normals (as defined with glu.quadricOrientation).
         *
         * GLU.LINE:       Quadrics are rendered as a set of lines.
         *
         * GLU.SILHOUETTE: Quadrics are rendered as a set of lines, except that edges
         * 		   separating coplanar faces will not be drawn.
         *
         * GLU.POINT:       Quadrics are rendered as a set of points.
         */
        public native void quadricDrawStyle(
                int target,
                int drawStyle
        );
        
        /**
         * specifies what kind	of normals are desired for quadrics
         * rendered with	qobj.  The legal values	are as follows:
         *
         * GLU.NONE:     No normals are generated.
         *
         * GLU.FLAT:     One normal is generated for every facet of a quadric.
         *
         * GLU.SMOOTH:   One normal is generated for every vertex of a quadric.  This
         *               is the default.
         */
        public native void quadricNormals(
                int target,
                int normals
        );
        
        /**
         * specifies what kind of orientation is desired for
         * quadrics rendered with qobj.	The orientation	values are as follows:
         *
         * GLU.OUTSIDE:  Quadrics are drawn with normals pointing outward.
         *
         * GLU.INSIDE:   Normals point inward. The default is GLU.OUTSIDE.
         *
         * Note that the interpretation of outward and inward depends on the quadric
         * being drawn.
         */
        public native void quadricOrientation(
                int target,
                int orientation
        );
        
        /**
         * specifies if texture coordinates should be generated for
         * quadrics rendered with qobj. If the value of textureCoords is true,
         * then texture coordinates are generated, and if textureCoords is false,
         * they are not.. The default is false.
         *
         * The manner in which texture coordinates are generated depends upon the
         * specific quadric rendered.
         */
        public native void quadricTexture(
                int target,
                boolean textureCoords
        );
        
        /**
         * draws a sphere of the given	radius centered	around the origin.
         * The sphere is subdivided around the z axis into slices and along the z axis
         * into stacks (similar to lines of longitude and latitude).
         *
         * If the orientation is set to GLU.OUTSIDE (with glu.quadricOrientation), then
         * any normals generated point away from the center of the sphere. Otherwise,
         * they point toward the center of the sphere.

         * If texturing is turned on (with glu.quadricTexture), then texture
         * coordinates are generated so that t ranges from 0.0 at z=-radius to 1.0 at
         * z=radius (t increases linearly along longitudinal lines), and s ranges from
         * 0.0 at the +y axis, to 0.25 at the +x axis, to 0.5 at the -y axis, to 0.75
         * at the -x axis, and back to 1.0 at the +y axis.
         */
        public native void sphere(
                int target,
                double radius,
                int slices,
                int stacks
        );
        
        
        public native void quadricCallback(
                int target,
                int type,
                String method
        );
        public native void quadricCallback(
                int target,
                int type,
                Object obj,
                String method
        );
        
}
