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

/**
 * $Id$
 *
 * GLU constants.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public interface GLUConstants {
	/* Errors: (return value 0 = no error) */
	public static final int INVALID_ENUM        = 100900;
	public static final int INVALID_VALUE       = 100901;
	public static final int OUT_OF_MEMORY       = 100902;
	public static final int INCOMPATIBLE_GL_VERSION     = 100903;
	
	/* StringName */
	public static final int VERSION             = 100800;
	public static final int EXTENSIONS          = 100801;
	
	/* Boolean */
	public static final int TRUE                = CoreGLConstants.TRUE;
	public static final int FALSE               = CoreGLConstants.FALSE;
	
	
	/****           Quadric constants               ****/
	
	/* QuadricNormal */
	public static final int SMOOTH              = 100000;
	public static final int FLAT                = 100001;
	public static final int NONE                = 100002;
	
	/* QuadricDrawStyle */
	public static final int POINT               = 100010;
	public static final int LINE                = 100011;
	public static final int FILL                = 100012;
	public static final int SILHOUETTE          = 100013;
	
	/* QuadricOrientation */
	public static final int OUTSIDE             = 100020;
	public static final int INSIDE              = 100021;
	
	/* Callback types: */
	/*      ERROR               = 100103 */
	
	
	/****           Tesselation constants           ****/
	
	public static final double TESS_MAX_COORD              = 1.0e150;
	
	/* TessProperty */
	public static final int TESS_WINDING_RULE           = 100140;
	public static final int TESS_BOUNDARY_ONLY          = 100141;
	public static final int TESS_TOLERANCE              = 100142;
	
	/* TessWinding */
	public static final int TESS_WINDING_ODD            = 100130;
	public static final int TESS_WINDING_NONZERO        = 100131;
	public static final int TESS_WINDING_POSITIVE       = 100132;
	public static final int TESS_WINDING_NEGATIVE       = 100133;
	public static final int TESS_WINDING_ABS_GEQ_TWO    = 100134;
	
	/* TessCallback */
	public static final int TESS_BEGIN          = 100100;  /* void (CALLBACK*)(GLenum    type)  */
	public static final int TESS_VERTEX         = 100101;  /* void (CALLBACK*)(void      *data) */
	public static final int TESS_END            = 100102;  /* void (CALLBACK*)(void)            */
	public static final int TESS_ERROR          = 100103;  /* void (CALLBACK*)(GLenum    errno) */
	public static final int TESS_EDGE_FLAG      = 100104;  /* void (CALLBACK*)(GLboolean boundaryEdge)  */
	public static final int TESS_COMBINE        = 100105;  /* void (CALLBACK*)(GLdouble  coords[3],
	                                                            void      *data[4],
	                                                            GLfloat   weight[4],
	                                                            void      **dataOut)     */
	public static final int TESS_BEGIN_DATA     = 100106;  /* void (CALLBACK*)(GLenum    type,  
	                                                            void      *polygon_data) */
	public static final int TESS_VERTEX_DATA    = 100107;  /* void (CALLBACK*)(void      *data, 
	                                                            void      *polygon_data) */
	public static final int TESS_END_DATA       = 100108;  /* void (CALLBACK*)(void      *polygon_data) */
	public static final int TESS_ERROR_DATA     = 100109;  /* void (CALLBACK*)(GLenum    errno, 
	                                                            void      *polygon_data) */
	public static final int TESS_EDGE_FLAG_DATA = 100110;  /* void (CALLBACK*)(GLboolean boundaryEdge,
	                                                            void      *polygon_data) */
	public static final int TESS_COMBINE_DATA   = 100111;  /* void (CALLBACK*)(GLdouble  coords[3],
	                                                            void      *data[4],
	                                                            GLfloat   weight[4],
	                                                            void      **dataOut,
	                                                            void      *polygon_data) */
	
	/* TessError */
	public static final int TESS_ERROR1     = 100151;
	public static final int TESS_ERROR2     = 100152;
	public static final int TESS_ERROR3     = 100153;
	public static final int TESS_ERROR4     = 100154;
	public static final int TESS_ERROR5     = 100155;
	public static final int TESS_ERROR6     = 100156;
	public static final int TESS_ERROR7     = 100157;
	public static final int TESS_ERROR8     = 100158;
	
	public static final int TESS_MISSING_BEGIN_POLYGON  = TESS_ERROR1;
	public static final int TESS_MISSING_BEGIN_CONTOUR  = TESS_ERROR2;
	public static final int TESS_MISSING_END_POLYGON    = TESS_ERROR3;
	public static final int TESS_MISSING_END_CONTOUR    = TESS_ERROR4;
	public static final int TESS_COORD_TOO_LARGE        = TESS_ERROR5;
	public static final int TESS_NEED_COMBINE_CALLBACK  = TESS_ERROR6;
	
	/****           NURBS constants                 ****/
	
	/* NurbsProperty */
	public static final int AUTO_LOAD_MATRIX    = 100200;
	public static final int CULLING             = 100201;
	public static final int SAMPLING_TOLERANCE  = 100203;
	public static final int DISPLAY_MODE        = 100204;
	public static final int PARAMETRIC_TOLERANCE        = 100202;
	public static final int SAMPLING_METHOD             = 100205;
	public static final int U_STEP                      = 100206;
	public static final int V_STEP                      = 100207;
	
	/* NurbsSampling */
	public static final int PATH_LENGTH                 = 100215;
	public static final int PARAMETRIC_ERROR            = 100216;
	public static final int DOMAIN_DISTANCE             = 100217;
	
	
	/* NurbsTrim */
	public static final int MAP1_TRIM_2         = 100210;
	public static final int MAP1_TRIM_3         = 100211;
	
	/* NurbsDisplay */
	/*      FILL                = 100012 */
	public static final int OUTLINE_POLYGON     = 100240;
	public static final int OUTLINE_PATCH       = 100241;
	
	/* NurbsCallback */
	/*      ERROR               = 100103 */
	
	/* NurbsErrors */
	public static final int NURBS_ERROR1        = 100251;
	public static final int NURBS_ERROR2        = 100252;
	public static final int NURBS_ERROR3        = 100253;
	public static final int NURBS_ERROR4        = 100254;
	public static final int NURBS_ERROR5        = 100255;
	public static final int NURBS_ERROR6        = 100256;
	public static final int NURBS_ERROR7        = 100257;
	public static final int NURBS_ERROR8        = 100258;
	public static final int NURBS_ERROR9        = 100259;
	public static final int NURBS_ERROR10       = 100260;
	public static final int NURBS_ERROR11       = 100261;
	public static final int NURBS_ERROR12       = 100262;
	public static final int NURBS_ERROR13       = 100263;
	public static final int NURBS_ERROR14       = 100264;
	public static final int NURBS_ERROR15       = 100265;
	public static final int NURBS_ERROR16       = 100266;
	public static final int NURBS_ERROR17       = 100267;
	public static final int NURBS_ERROR18       = 100268;
	public static final int NURBS_ERROR19       = 100269;
	public static final int NURBS_ERROR20       = 100270;
	public static final int NURBS_ERROR21       = 100271;
	public static final int NURBS_ERROR22       = 100272;
	public static final int NURBS_ERROR23       = 100273;
	public static final int NURBS_ERROR24       = 100274;
	public static final int NURBS_ERROR25       = 100275;
	public static final int NURBS_ERROR26       = 100276;
	public static final int NURBS_ERROR27       = 100277;
	public static final int NURBS_ERROR28       = 100278;
	public static final int NURBS_ERROR29       = 100279;
	public static final int NURBS_ERROR30       = 100280;
	public static final int NURBS_ERROR31       = 100281;
	public static final int NURBS_ERROR32       = 100282;
	public static final int NURBS_ERROR33       = 100283;
	public static final int NURBS_ERROR34       = 100284;
	public static final int NURBS_ERROR35       = 100285;
	public static final int NURBS_ERROR36       = 100286;
	public static final int NURBS_ERROR37       = 100287;
	
	/* Contours types -- obsolete! */
	public static final int CW          = 100120;
	public static final int CCW         = 100121;
	public static final int INTERIOR    = 100122;
	public static final int EXTERIOR    = 100123;
	public static final int UNKNOWN     = 100124;
	
	/* Names without "TESS_" prefix */
	public static final int BEGIN       = TESS_BEGIN;
	public static final int VERTEX      = TESS_VERTEX;
	public static final int END         = TESS_END;
	public static final int ERROR       = TESS_ERROR;
	public static final int EDGE_FLAG   = TESS_EDGE_FLAG;
}
