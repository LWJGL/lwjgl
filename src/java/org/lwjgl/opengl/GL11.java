/*
 * Copyright (c) 2002-2004 LWJGL Project
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
package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.BufferChecks;
import org.lwjgl.LWJGLException;

/**
 * $Id$
 *
 * The core OpenGL1.1 API.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

public final class GL11 {

	/* AccumOp */
	public static final int GL_ACCUM                          = 0x0100;
	public static final int GL_LOAD                           = 0x0101;
	public static final int GL_RETURN                         = 0x0102;
	public static final int GL_MULT                           = 0x0103;
	public static final int GL_ADD                            = 0x0104;

	/* AlphaFunction */
	public static final int GL_NEVER                          = 0x0200;
	public static final int GL_LESS                           = 0x0201;
	public static final int GL_EQUAL                          = 0x0202;
	public static final int GL_LEQUAL                         = 0x0203;
	public static final int GL_GREATER                        = 0x0204;
	public static final int GL_NOTEQUAL                       = 0x0205;
	public static final int GL_GEQUAL                         = 0x0206;
	public static final int GL_ALWAYS                         = 0x0207;

	/* AttribMask */
	public static final int GL_CURRENT_BIT                    = 0x00000001;
	public static final int GL_POINT_BIT                      = 0x00000002;
	public static final int GL_LINE_BIT                       = 0x00000004;
	public static final int GL_POLYGON_BIT                    = 0x00000008;
	public static final int GL_POLYGON_STIPPLE_BIT            = 0x00000010;
	public static final int GL_PIXEL_MODE_BIT                 = 0x00000020;
	public static final int GL_LIGHTING_BIT                   = 0x00000040;
	public static final int GL_FOG_BIT                        = 0x00000080;
	public static final int GL_DEPTH_BUFFER_BIT               = 0x00000100;
	public static final int GL_ACCUM_BUFFER_BIT               = 0x00000200;
	public static final int GL_STENCIL_BUFFER_BIT             = 0x00000400;
	public static final int GL_VIEWPORT_BIT                   = 0x00000800;
	public static final int GL_TRANSFORM_BIT                  = 0x00001000;
	public static final int GL_ENABLE_BIT                     = 0x00002000;
	public static final int GL_COLOR_BUFFER_BIT               = 0x00004000;
	public static final int GL_HINT_BIT                       = 0x00008000;
	public static final int GL_EVAL_BIT                       = 0x00010000;
	public static final int GL_LIST_BIT                       = 0x00020000;
	public static final int GL_TEXTURE_BIT                    = 0x00040000;
	public static final int GL_SCISSOR_BIT                    = 0x00080000;
	public static final int GL_ALL_ATTRIB_BITS                = 0x000fffff;

	/* BeginMode */
	public static final int GL_POINTS                         = 0x0000;
	public static final int GL_LINES                          = 0x0001;
	public static final int GL_LINE_LOOP                      = 0x0002;
	public static final int GL_LINE_STRIP                     = 0x0003;
	public static final int GL_TRIANGLES                      = 0x0004;
	public static final int GL_TRIANGLE_STRIP                 = 0x0005;
	public static final int GL_TRIANGLE_FAN                   = 0x0006;
	public static final int GL_QUADS                          = 0x0007;
	public static final int GL_QUAD_STRIP                     = 0x0008;
	public static final int GL_POLYGON                        = 0x0009;

	/* BlendingFactorDest */
	public static final int GL_ZERO                           = 0;
	public static final int GL_ONE                            = 1;
	public static final int GL_SRC_COLOR                      = 0x0300;
	public static final int GL_ONE_MINUS_SRC_COLOR            = 0x0301;
	public static final int GL_SRC_ALPHA                      = 0x0302;
	public static final int GL_ONE_MINUS_SRC_ALPHA            = 0x0303;
	public static final int GL_DST_ALPHA                      = 0x0304;
	public static final int GL_ONE_MINUS_DST_ALPHA            = 0x0305;

	/* BlendingFactorSrc */
	/*      GL_ZERO */
	/*      GL_ONE */
	public static final int GL_DST_COLOR                      = 0x0306;
	public static final int GL_ONE_MINUS_DST_COLOR            = 0x0307;
	public static final int GL_SRC_ALPHA_SATURATE             = 0x0308;
	public static final int GL_CONSTANT_COLOR                 = 0x8001;
	public static final int GL_ONE_MINUS_CONSTANT_COLOR       = 0x8002;
	public static final int GL_CONSTANT_ALPHA                 = 0x8003;
	public static final int GL_ONE_MINUS_CONSTANT_ALPHA       = 0x8004;

	/* Boolean */
	public static final int GL_TRUE                           = 1;
	public static final int GL_FALSE                          = 0;

	/* ClipPlaneName */
	public static final int GL_CLIP_PLANE0                    = 0x3000;
	public static final int GL_CLIP_PLANE1                    = 0x3001;
	public static final int GL_CLIP_PLANE2                    = 0x3002;
	public static final int GL_CLIP_PLANE3                    = 0x3003;
	public static final int GL_CLIP_PLANE4                    = 0x3004;
	public static final int GL_CLIP_PLANE5                    = 0x3005;

	/* DataType */
	public static final int GL_BYTE                           = 0x1400;
	public static final int GL_UNSIGNED_BYTE                  = 0x1401;
	public static final int GL_SHORT                          = 0x1402;
	public static final int GL_UNSIGNED_SHORT                 = 0x1403;
	public static final int GL_INT                            = 0x1404;
	public static final int GL_UNSIGNED_INT                   = 0x1405;
	public static final int GL_FLOAT                          = 0x1406;
	public static final int GL_2_BYTES                        = 0x1407;
	public static final int GL_3_BYTES                        = 0x1408;
	public static final int GL_4_BYTES                        = 0x1409;
	public static final int GL_DOUBLE                         = 0x140A;

	/* DrawBufferMode */
	public static final int GL_NONE                           = 0;
	public static final int GL_FRONT_LEFT                     = 0x0400;
	public static final int GL_FRONT_RIGHT                    = 0x0401;
	public static final int GL_BACK_LEFT                      = 0x0402;
	public static final int GL_BACK_RIGHT                     = 0x0403;
	public static final int GL_FRONT                          = 0x0404;
	public static final int GL_BACK                           = 0x0405;
	public static final int GL_LEFT                           = 0x0406;
	public static final int GL_RIGHT                          = 0x0407;
	public static final int GL_FRONT_AND_BACK                 = 0x0408;
	public static final int GL_AUX0                           = 0x0409;
	public static final int GL_AUX1                           = 0x040A;
	public static final int GL_AUX2                           = 0x040B;
	public static final int GL_AUX3                           = 0x040C;

	/* ErrorCode */
	public static final int GL_NO_ERROR                       = 0;
	public static final int GL_INVALID_ENUM                   = 0x0500;
	public static final int GL_INVALID_VALUE                  = 0x0501;
	public static final int GL_INVALID_OPERATION              = 0x0502;
	public static final int GL_STACK_OVERFLOW                 = 0x0503;
	public static final int GL_STACK_UNDERFLOW                = 0x0504;
	public static final int GL_OUT_OF_MEMORY                  = 0x0505;

	/* FeedBackMode */
	public static final int GL_2D                             = 0x0600;
	public static final int GL_3D                             = 0x0601;
	public static final int GL_3D_COLOR                       = 0x0602;
	public static final int GL_3D_COLOR_TEXTURE               = 0x0603;
	public static final int GL_4D_COLOR_TEXTURE               = 0x0604;

	/* FeedBackToken */
	public static final int GL_PASS_THROUGH_TOKEN             = 0x0700;
	public static final int GL_POINT_TOKEN                    = 0x0701;
	public static final int GL_LINE_TOKEN                     = 0x0702;
	public static final int GL_POLYGON_TOKEN                  = 0x0703;
	public static final int GL_BITMAP_TOKEN                   = 0x0704;
	public static final int GL_DRAW_PIXEL_TOKEN               = 0x0705;
	public static final int GL_COPY_PIXEL_TOKEN               = 0x0706;
	public static final int GL_LINE_RESET_TOKEN               = 0x0707;

	/* FogMode */
	/*      GL_LINEAR */
	public static final int GL_EXP                            = 0x0800;
	public static final int GL_EXP2                           = 0x0801;

	/* FrontFaceDirection */
	public static final int GL_CW                             = 0x0900;
	public static final int GL_CCW                            = 0x0901;

	/* GetMapTarget */
	public static final int GL_COEFF                          = 0x0A00;
	public static final int GL_ORDER                          = 0x0A01;
	public static final int GL_DOMAIN                         = 0x0A02;

	/* GetTarget */
	public static final int GL_CURRENT_COLOR                  = 0x0B00;
	public static final int GL_CURRENT_INDEX                  = 0x0B01;
	public static final int GL_CURRENT_NORMAL                 = 0x0B02;
	public static final int GL_CURRENT_TEXTURE_COORDS         = 0x0B03;
	public static final int GL_CURRENT_RASTER_COLOR           = 0x0B04;
	public static final int GL_CURRENT_RASTER_INDEX           = 0x0B05;
	public static final int GL_CURRENT_RASTER_TEXTURE_COORDS  = 0x0B06;
	public static final int GL_CURRENT_RASTER_POSITION        = 0x0B07;
	public static final int GL_CURRENT_RASTER_POSITION_VALID  = 0x0B08;
	public static final int GL_CURRENT_RASTER_DISTANCE        = 0x0B09;
	public static final int GL_POINT_SMOOTH                   = 0x0B10;
	public static final int GL_POINT_SIZE                     = 0x0B11;
	public static final int GL_POINT_SIZE_RANGE               = 0x0B12;
	public static final int GL_POINT_SIZE_GRANULARITY         = 0x0B13;
	public static final int GL_LINE_SMOOTH                    = 0x0B20;
	public static final int GL_LINE_WIDTH                     = 0x0B21;
	public static final int GL_LINE_WIDTH_RANGE               = 0x0B22;
	public static final int GL_LINE_WIDTH_GRANULARITY         = 0x0B23;
	public static final int GL_LINE_STIPPLE                   = 0x0B24;
	public static final int GL_LINE_STIPPLE_PATTERN           = 0x0B25;
	public static final int GL_LINE_STIPPLE_REPEAT            = 0x0B26;
	public static final int GL_LIST_MODE                      = 0x0B30;
	public static final int GL_MAX_LIST_NESTING               = 0x0B31;
	public static final int GL_LIST_BASE                      = 0x0B32;
	public static final int GL_LIST_INDEX                     = 0x0B33;
	public static final int GL_POLYGON_MODE                   = 0x0B40;
	public static final int GL_POLYGON_SMOOTH                 = 0x0B41;
	public static final int GL_POLYGON_STIPPLE                = 0x0B42;
	public static final int GL_EDGE_FLAG                      = 0x0B43;
	public static final int GL_CULL_FACE                      = 0x0B44;
	public static final int GL_CULL_FACE_MODE                 = 0x0B45;
	public static final int GL_FRONT_FACE                     = 0x0B46;
	public static final int GL_LIGHTING                       = 0x0B50;
	public static final int GL_LIGHT_MODEL_LOCAL_VIEWER       = 0x0B51;
	public static final int GL_LIGHT_MODEL_TWO_SIDE           = 0x0B52;
	public static final int GL_LIGHT_MODEL_AMBIENT            = 0x0B53;
	public static final int GL_SHADE_MODEL                    = 0x0B54;
	public static final int GL_COLOR_MATERIAL_FACE            = 0x0B55;
	public static final int GL_COLOR_MATERIAL_PARAMETER       = 0x0B56;
	public static final int GL_COLOR_MATERIAL                 = 0x0B57;
	public static final int GL_FOG                            = 0x0B60;
	public static final int GL_FOG_INDEX                      = 0x0B61;
	public static final int GL_FOG_DENSITY                    = 0x0B62;
	public static final int GL_FOG_START                      = 0x0B63;
	public static final int GL_FOG_END                        = 0x0B64;
	public static final int GL_FOG_MODE                       = 0x0B65;
	public static final int GL_FOG_COLOR                      = 0x0B66;
	public static final int GL_DEPTH_RANGE                    = 0x0B70;
	public static final int GL_DEPTH_TEST                     = 0x0B71;
	public static final int GL_DEPTH_WRITEMASK                = 0x0B72;
	public static final int GL_DEPTH_CLEAR_VALUE              = 0x0B73;
	public static final int GL_DEPTH_FUNC                     = 0x0B74;
	public static final int GL_ACCUM_CLEAR_VALUE              = 0x0B80;
	public static final int GL_STENCIL_TEST                   = 0x0B90;
	public static final int GL_STENCIL_CLEAR_VALUE            = 0x0B91;
	public static final int GL_STENCIL_FUNC                   = 0x0B92;
	public static final int GL_STENCIL_VALUE_MASK             = 0x0B93;
	public static final int GL_STENCIL_FAIL                   = 0x0B94;
	public static final int GL_STENCIL_PASS_DEPTH_FAIL        = 0x0B95;
	public static final int GL_STENCIL_PASS_DEPTH_PASS        = 0x0B96;
	public static final int GL_STENCIL_REF                    = 0x0B97;
	public static final int GL_STENCIL_WRITEMASK              = 0x0B98;
	public static final int GL_MATRIX_MODE                    = 0x0BA0;
	public static final int GL_NORMALIZE                      = 0x0BA1;
	public static final int GL_VIEWPORT                       = 0x0BA2;
	public static final int GL_MODELVIEW_STACK_DEPTH          = 0x0BA3;
	public static final int GL_PROJECTION_STACK_DEPTH         = 0x0BA4;
	public static final int GL_TEXTURE_STACK_DEPTH            = 0x0BA5;
	public static final int GL_MODELVIEW_MATRIX               = 0x0BA6;
	public static final int GL_PROJECTION_MATRIX              = 0x0BA7;
	public static final int GL_TEXTURE_MATRIX                 = 0x0BA8;
	public static final int GL_ATTRIB_STACK_DEPTH             = 0x0BB0;
	public static final int GL_CLIENT_ATTRIB_STACK_DEPTH      = 0x0BB1;
	public static final int GL_ALPHA_TEST                     = 0x0BC0;
	public static final int GL_ALPHA_TEST_FUNC                = 0x0BC1;
	public static final int GL_ALPHA_TEST_REF                 = 0x0BC2;
	public static final int GL_DITHER                         = 0x0BD0;
	public static final int GL_BLEND_DST                      = 0x0BE0;
	public static final int GL_BLEND_SRC                      = 0x0BE1;
	public static final int GL_BLEND                          = 0x0BE2;
	public static final int GL_LOGIC_OP_MODE                  = 0x0BF0;
	public static final int GL_INDEX_LOGIC_OP                 = 0x0BF1;
	public static final int GL_COLOR_LOGIC_OP                 = 0x0BF2;
	public static final int GL_AUX_BUFFERS                    = 0x0C00;
	public static final int GL_DRAW_BUFFER                    = 0x0C01;
	public static final int GL_READ_BUFFER                    = 0x0C02;
	public static final int GL_SCISSOR_BOX                    = 0x0C10;
	public static final int GL_SCISSOR_TEST                   = 0x0C11;
	public static final int GL_INDEX_CLEAR_VALUE              = 0x0C20;
	public static final int GL_INDEX_WRITEMASK                = 0x0C21;
	public static final int GL_COLOR_CLEAR_VALUE              = 0x0C22;
	public static final int GL_COLOR_WRITEMASK                = 0x0C23;
	public static final int GL_INDEX_MODE                     = 0x0C30;
	public static final int GL_RGBA_MODE                      = 0x0C31;
	public static final int GL_DOUBLEBUFFER                   = 0x0C32;
	public static final int GL_STEREO                         = 0x0C33;
	public static final int GL_RENDER_MODE                    = 0x0C40;
	public static final int GL_PERSPECTIVE_CORRECTION_HINT    = 0x0C50;
	public static final int GL_POINT_SMOOTH_HINT              = 0x0C51;
	public static final int GL_LINE_SMOOTH_HINT               = 0x0C52;
	public static final int GL_POLYGON_SMOOTH_HINT            = 0x0C53;
	public static final int GL_FOG_HINT                       = 0x0C54;
	public static final int GL_TEXTURE_GEN_S                  = 0x0C60;
	public static final int GL_TEXTURE_GEN_T                  = 0x0C61;
	public static final int GL_TEXTURE_GEN_R                  = 0x0C62;
	public static final int GL_TEXTURE_GEN_Q                  = 0x0C63;
	public static final int GL_PIXEL_MAP_I_TO_I               = 0x0C70;
	public static final int GL_PIXEL_MAP_S_TO_S               = 0x0C71;
	public static final int GL_PIXEL_MAP_I_TO_R               = 0x0C72;
	public static final int GL_PIXEL_MAP_I_TO_G               = 0x0C73;
	public static final int GL_PIXEL_MAP_I_TO_B               = 0x0C74;
	public static final int GL_PIXEL_MAP_I_TO_A               = 0x0C75;
	public static final int GL_PIXEL_MAP_R_TO_R               = 0x0C76;
	public static final int GL_PIXEL_MAP_G_TO_G               = 0x0C77;
	public static final int GL_PIXEL_MAP_B_TO_B               = 0x0C78;
	public static final int GL_PIXEL_MAP_A_TO_A               = 0x0C79;
	public static final int GL_PIXEL_MAP_I_TO_I_SIZE          = 0x0CB0;
	public static final int GL_PIXEL_MAP_S_TO_S_SIZE          = 0x0CB1;
	public static final int GL_PIXEL_MAP_I_TO_R_SIZE          = 0x0CB2;
	public static final int GL_PIXEL_MAP_I_TO_G_SIZE          = 0x0CB3;
	public static final int GL_PIXEL_MAP_I_TO_B_SIZE          = 0x0CB4;
	public static final int GL_PIXEL_MAP_I_TO_A_SIZE          = 0x0CB5;
	public static final int GL_PIXEL_MAP_R_TO_R_SIZE          = 0x0CB6;
	public static final int GL_PIXEL_MAP_G_TO_G_SIZE          = 0x0CB7;
	public static final int GL_PIXEL_MAP_B_TO_B_SIZE          = 0x0CB8;
	public static final int GL_PIXEL_MAP_A_TO_A_SIZE          = 0x0CB9;
	public static final int GL_UNPACK_SWAP_BYTES              = 0x0CF0;
	public static final int GL_UNPACK_LSB_FIRST               = 0x0CF1;
	public static final int GL_UNPACK_ROW_LENGTH              = 0x0CF2;
	public static final int GL_UNPACK_SKIP_ROWS               = 0x0CF3;
	public static final int GL_UNPACK_SKIP_PIXELS             = 0x0CF4;
	public static final int GL_UNPACK_ALIGNMENT               = 0x0CF5;
	public static final int GL_PACK_SWAP_BYTES                = 0x0D00;
	public static final int GL_PACK_LSB_FIRST                 = 0x0D01;
	public static final int GL_PACK_ROW_LENGTH                = 0x0D02;
	public static final int GL_PACK_SKIP_ROWS                 = 0x0D03;
	public static final int GL_PACK_SKIP_PIXELS               = 0x0D04;
	public static final int GL_PACK_ALIGNMENT                 = 0x0D05;
	public static final int GL_MAP_COLOR                      = 0x0D10;
	public static final int GL_MAP_STENCIL                    = 0x0D11;
	public static final int GL_INDEX_SHIFT                    = 0x0D12;
	public static final int GL_INDEX_OFFSET                   = 0x0D13;
	public static final int GL_RED_SCALE                      = 0x0D14;
	public static final int GL_RED_BIAS                       = 0x0D15;
	public static final int GL_ZOOM_X                         = 0x0D16;
	public static final int GL_ZOOM_Y                         = 0x0D17;
	public static final int GL_GREEN_SCALE                    = 0x0D18;
	public static final int GL_GREEN_BIAS                     = 0x0D19;
	public static final int GL_BLUE_SCALE                     = 0x0D1A;
	public static final int GL_BLUE_BIAS                      = 0x0D1B;
	public static final int GL_ALPHA_SCALE                    = 0x0D1C;
	public static final int GL_ALPHA_BIAS                     = 0x0D1D;
	public static final int GL_DEPTH_SCALE                    = 0x0D1E;
	public static final int GL_DEPTH_BIAS                     = 0x0D1F;
	public static final int GL_MAX_EVAL_ORDER                 = 0x0D30;
	public static final int GL_MAX_LIGHTS                     = 0x0D31;
	public static final int GL_MAX_CLIP_PLANES                = 0x0D32;
	public static final int GL_MAX_TEXTURE_SIZE               = 0x0D33;
	public static final int GL_MAX_PIXEL_MAP_TABLE            = 0x0D34;
	public static final int GL_MAX_ATTRIB_STACK_DEPTH         = 0x0D35;
	public static final int GL_MAX_MODELVIEW_STACK_DEPTH      = 0x0D36;
	public static final int GL_MAX_NAME_STACK_DEPTH           = 0x0D37;
	public static final int GL_MAX_PROJECTION_STACK_DEPTH     = 0x0D38;
	public static final int GL_MAX_TEXTURE_STACK_DEPTH        = 0x0D39;
	public static final int GL_MAX_VIEWPORT_DIMS              = 0x0D3A;
	public static final int GL_MAX_CLIENT_ATTRIB_STACK_DEPTH  = 0x0D3B;
	public static final int GL_SUBPIXEL_BITS                  = 0x0D50;
	public static final int GL_INDEX_BITS                     = 0x0D51;
	public static final int GL_RED_BITS                       = 0x0D52;
	public static final int GL_GREEN_BITS                     = 0x0D53;
	public static final int GL_BLUE_BITS                      = 0x0D54;
	public static final int GL_ALPHA_BITS                     = 0x0D55;
	public static final int GL_DEPTH_BITS                     = 0x0D56;
	public static final int GL_STENCIL_BITS                   = 0x0D57;
	public static final int GL_ACCUM_RED_BITS                 = 0x0D58;
	public static final int GL_ACCUM_GREEN_BITS               = 0x0D59;
	public static final int GL_ACCUM_BLUE_BITS                = 0x0D5A;
	public static final int GL_ACCUM_ALPHA_BITS               = 0x0D5B;
	public static final int GL_NAME_STACK_DEPTH               = 0x0D70;
	public static final int GL_AUTO_NORMAL                    = 0x0D80;
	public static final int GL_MAP1_COLOR_4                   = 0x0D90;
	public static final int GL_MAP1_INDEX                     = 0x0D91;
	public static final int GL_MAP1_NORMAL                    = 0x0D92;
	public static final int GL_MAP1_TEXTURE_COORD_1           = 0x0D93;
	public static final int GL_MAP1_TEXTURE_COORD_2           = 0x0D94;
	public static final int GL_MAP1_TEXTURE_COORD_3           = 0x0D95;
	public static final int GL_MAP1_TEXTURE_COORD_4           = 0x0D96;
	public static final int GL_MAP1_VERTEX_3                  = 0x0D97;
	public static final int GL_MAP1_VERTEX_4                  = 0x0D98;
	public static final int GL_MAP2_COLOR_4                   = 0x0DB0;
	public static final int GL_MAP2_INDEX                     = 0x0DB1;
	public static final int GL_MAP2_NORMAL                    = 0x0DB2;
	public static final int GL_MAP2_TEXTURE_COORD_1           = 0x0DB3;
	public static final int GL_MAP2_TEXTURE_COORD_2           = 0x0DB4;
	public static final int GL_MAP2_TEXTURE_COORD_3           = 0x0DB5;
	public static final int GL_MAP2_TEXTURE_COORD_4           = 0x0DB6;
	public static final int GL_MAP2_VERTEX_3                  = 0x0DB7;
	public static final int GL_MAP2_VERTEX_4                  = 0x0DB8;
	public static final int GL_MAP1_GRID_DOMAIN               = 0x0DD0;
	public static final int GL_MAP1_GRID_SEGMENTS             = 0x0DD1;
	public static final int GL_MAP2_GRID_DOMAIN               = 0x0DD2;
	public static final int GL_MAP2_GRID_SEGMENTS             = 0x0DD3;
	public static final int GL_TEXTURE_1D                     = 0x0DE0;
	public static final int GL_TEXTURE_2D                     = 0x0DE1;
	public static final int GL_FEEDBACK_BUFFER_POINTER        = 0x0DF0;
	public static final int GL_FEEDBACK_BUFFER_SIZE           = 0x0DF1;
	public static final int GL_FEEDBACK_BUFFER_TYPE           = 0x0DF2;
	public static final int GL_SELECTION_BUFFER_POINTER       = 0x0DF3;
	public static final int GL_SELECTION_BUFFER_SIZE          = 0x0DF4;

	/* GetTextureParameter */
	/*      GL_TEXTURE_MAG_FILTER */
	/*      GL_TEXTURE_MIN_FILTER */
	/*      GL_TEXTURE_WRAP_S */
	/*      GL_TEXTURE_WRAP_T */
	public static final int GL_TEXTURE_WIDTH                  = 0x1000;
	public static final int GL_TEXTURE_HEIGHT                 = 0x1001;
	public static final int GL_TEXTURE_INTERNAL_FORMAT        = 0x1003;
	public static final int GL_TEXTURE_BORDER_COLOR           = 0x1004;
	public static final int GL_TEXTURE_BORDER                 = 0x1005;
	/*      GL_TEXTURE_RED_SIZE */
	/*      GL_TEXTURE_GREEN_SIZE */
	/*      GL_TEXTURE_BLUE_SIZE */
	/*      GL_TEXTURE_ALPHA_SIZE */
	/*      GL_TEXTURE_LUMINANCE_SIZE */
	/*      GL_TEXTURE_INTENSITY_SIZE */
	/*      GL_TEXTURE_PRIORITY */
	/*      GL_TEXTURE_RESIDENT */

	/* HintMode */
	public static final int GL_DONT_CARE                      = 0x1100;
	public static final int GL_FASTEST                        = 0x1101;
	public static final int GL_NICEST                         = 0x1102;

	/* LightName */
	public static final int GL_LIGHT0                         = 0x4000;
	public static final int GL_LIGHT1                         = 0x4001;
	public static final int GL_LIGHT2                         = 0x4002;
	public static final int GL_LIGHT3                         = 0x4003;
	public static final int GL_LIGHT4                         = 0x4004;
	public static final int GL_LIGHT5                         = 0x4005;
	public static final int GL_LIGHT6                         = 0x4006;
	public static final int GL_LIGHT7                         = 0x4007;

	/* LightParameter */
	public static final int GL_AMBIENT                        = 0x1200;
	public static final int GL_DIFFUSE                        = 0x1201;
	public static final int GL_SPECULAR                       = 0x1202;
	public static final int GL_POSITION                       = 0x1203;
	public static final int GL_SPOT_DIRECTION                 = 0x1204;
	public static final int GL_SPOT_EXPONENT                  = 0x1205;
	public static final int GL_SPOT_CUTOFF                    = 0x1206;
	public static final int GL_CONSTANT_ATTENUATION           = 0x1207;
	public static final int GL_LINEAR_ATTENUATION             = 0x1208;
	public static final int GL_QUADRATIC_ATTENUATION          = 0x1209;

	/* ListMode */
	public static final int GL_COMPILE                        = 0x1300;
	public static final int GL_COMPILE_AND_EXECUTE            = 0x1301;

	/* LogicOp */
	public static final int GL_CLEAR                          = 0x1500;
	public static final int GL_AND                            = 0x1501;
	public static final int GL_AND_REVERSE                    = 0x1502;
	public static final int GL_COPY                           = 0x1503;
	public static final int GL_AND_INVERTED                   = 0x1504;
	public static final int GL_NOOP                           = 0x1505;
	public static final int GL_XOR                            = 0x1506;
	public static final int GL_OR                             = 0x1507;
	public static final int GL_NOR                            = 0x1508;
	public static final int GL_EQUIV                          = 0x1509;
	public static final int GL_INVERT                         = 0x150A;
	public static final int GL_OR_REVERSE                     = 0x150B;
	public static final int GL_COPY_INVERTED                  = 0x150C;
	public static final int GL_OR_INVERTED                    = 0x150D;
	public static final int GL_NAND                           = 0x150E;
	public static final int GL_SET                            = 0x150F;

	/* MaterialParameter */
	public static final int GL_EMISSION                       = 0x1600;
	public static final int GL_SHININESS                      = 0x1601;
	public static final int GL_AMBIENT_AND_DIFFUSE            = 0x1602;
	public static final int GL_COLOR_INDEXES                  = 0x1603;
	/*      GL_AMBIENT */
	/*      GL_DIFFUSE */
	/*      GL_SPECULAR */

	/* MatrixMode */
	public static final int GL_MODELVIEW                      = 0x1700;
	public static final int GL_PROJECTION                     = 0x1701;
	public static final int GL_TEXTURE                        = 0x1702;

	/* PixelCopyType */
	public static final int GL_COLOR                          = 0x1800;
	public static final int GL_DEPTH                          = 0x1801;
	public static final int GL_STENCIL                        = 0x1802;

	/* PixelFormat */
	public static final int GL_COLOR_INDEX                    = 0x1900;
	public static final int GL_STENCIL_INDEX                  = 0x1901;
	public static final int GL_DEPTH_COMPONENT                = 0x1902;
	public static final int GL_RED                            = 0x1903;
	public static final int GL_GREEN                          = 0x1904;
	public static final int GL_BLUE                           = 0x1905;
	public static final int GL_ALPHA                          = 0x1906;
	public static final int GL_RGB                            = 0x1907;
	public static final int GL_RGBA                           = 0x1908;
	public static final int GL_LUMINANCE                      = 0x1909;
	public static final int GL_LUMINANCE_ALPHA                = 0x190A;

	/* PixelType */
	public static final int GL_BITMAP                         = 0x1A00;
	/*      GL_BYTE */
	/*      GL_UNSIGNED_BYTE */
	/*      GL_SHORT */
	/*      GL_UNSIGNED_SHORT */
	/*      GL_INT */
	/*      GL_UNSIGNED_INT */
	/*      GL_FLOAT */

	/* PolygonMode */
	public static final int GL_POINT                          = 0x1B00;
	public static final int GL_LINE                           = 0x1B01;
	public static final int GL_FILL                           = 0x1B02;

	/* RenderingMode */
	public static final int GL_RENDER                         = 0x1C00;
	public static final int GL_FEEDBACK                       = 0x1C01;
	public static final int GL_SELECT                         = 0x1C02;

	/* ShadingModel */
	public static final int GL_FLAT                           = 0x1D00;
	public static final int GL_SMOOTH                         = 0x1D01;

	/* StencilOp */
	/*      GL_ZERO */
	public static final int GL_KEEP                           = 0x1E00;
	public static final int GL_REPLACE                        = 0x1E01;
	public static final int GL_INCR                           = 0x1E02;
	public static final int GL_DECR                           = 0x1E03;
	/*      GL_INVERT */

	/* StringName */
	public static final int GL_VENDOR                         = 0x1F00;
	public static final int GL_RENDERER                       = 0x1F01;
	public static final int GL_VERSION                        = 0x1F02;
	public static final int GL_EXTENSIONS                     = 0x1F03;

	/* TextureCoordName */
	public static final int GL_S                              = 0x2000;
	public static final int GL_T                              = 0x2001;
	public static final int GL_R                              = 0x2002;
	public static final int GL_Q                              = 0x2003;

	/* TexCoordPointerType */
	/*      GL_SHORT */
	/*      GL_INT */
	/*      GL_FLOAT */
	/*      GL_DOUBLE */

	/* TextureEnvMode */
	public static final int GL_MODULATE                       = 0x2100;
	public static final int GL_DECAL                          = 0x2101;
	/*      GL_BLEND */
	/*      GL_REPLACE */

	/* TextureEnvParameter */
	public static final int GL_TEXTURE_ENV_MODE               = 0x2200;
	public static final int GL_TEXTURE_ENV_COLOR              = 0x2201;

	/* TextureEnvTarget */
	public static final int GL_TEXTURE_ENV                    = 0x2300;

	/* TextureGenMode */
	public static final int GL_EYE_LINEAR                     = 0x2400;
	public static final int GL_OBJECT_LINEAR                  = 0x2401;
	public static final int GL_SPHERE_MAP                     = 0x2402;

	/* TextureGenParameter */
	public static final int GL_TEXTURE_GEN_MODE               = 0x2500;
	public static final int GL_OBJECT_PLANE                   = 0x2501;
	public static final int GL_EYE_PLANE                      = 0x2502;

	/* TextureMagFilter */
	public static final int GL_NEAREST                        = 0x2600;
	public static final int GL_LINEAR                         = 0x2601;

	/* TextureMinFilter */
	/*      GL_NEAREST */
	/*      GL_LINEAR */
	public static final int GL_NEAREST_MIPMAP_NEAREST         = 0x2700;
	public static final int GL_LINEAR_MIPMAP_NEAREST          = 0x2701;
	public static final int GL_NEAREST_MIPMAP_LINEAR          = 0x2702;
	public static final int GL_LINEAR_MIPMAP_LINEAR           = 0x2703;

	/* TextureParameterName */
	public static final int GL_TEXTURE_MAG_FILTER             = 0x2800;
	public static final int GL_TEXTURE_MIN_FILTER             = 0x2801;
	public static final int GL_TEXTURE_WRAP_S                 = 0x2802;
	public static final int GL_TEXTURE_WRAP_T                 = 0x2803;
	/*      GL_TEXTURE_BORDER_COLOR */
	/*      GL_TEXTURE_PRIORITY */

	/* TextureWrapMode */
	public static final int GL_CLAMP                          = 0x2900;
	public static final int GL_REPEAT                         = 0x2901;

	/* ClientAttribMask */
	public static final int GL_CLIENT_PIXEL_STORE_BIT         = 0x00000001;
	public static final int GL_CLIENT_VERTEX_ARRAY_BIT        = 0x00000002;
	public static final int GL_ALL_CLIENT_ATTRIB_BITS         = 0xffffffff;

	/* polygon_offset */
	public static final int GL_POLYGON_OFFSET_FACTOR          = 0x8038;
	public static final int GL_POLYGON_OFFSET_UNITS           = 0x2A00;
	public static final int GL_POLYGON_OFFSET_POINT           = 0x2A01;
	public static final int GL_POLYGON_OFFSET_LINE            = 0x2A02;
	public static final int GL_POLYGON_OFFSET_FILL            = 0x8037;

	/* texture */
	public static final int GL_ALPHA4                         = 0x803B;
	public static final int GL_ALPHA8                         = 0x803C;
	public static final int GL_ALPHA12                        = 0x803D;
	public static final int GL_ALPHA16                        = 0x803E;
	public static final int GL_LUMINANCE4                     = 0x803F;
	public static final int GL_LUMINANCE8                     = 0x8040;
	public static final int GL_LUMINANCE12                    = 0x8041;
	public static final int GL_LUMINANCE16                    = 0x8042;
	public static final int GL_LUMINANCE4_ALPHA4              = 0x8043;
	public static final int GL_LUMINANCE6_ALPHA2              = 0x8044;
	public static final int GL_LUMINANCE8_ALPHA8              = 0x8045;
	public static final int GL_LUMINANCE12_ALPHA4             = 0x8046;
	public static final int GL_LUMINANCE12_ALPHA12            = 0x8047;
	public static final int GL_LUMINANCE16_ALPHA16            = 0x8048;
	public static final int GL_INTENSITY                      = 0x8049;
	public static final int GL_INTENSITY4                     = 0x804A;
	public static final int GL_INTENSITY8                     = 0x804B;
	public static final int GL_INTENSITY12                    = 0x804C;
	public static final int GL_INTENSITY16                    = 0x804D;
	public static final int GL_R3_G3_B2                       = 0x2A10;
	public static final int GL_RGB4                           = 0x804F;
	public static final int GL_RGB5                           = 0x8050;
	public static final int GL_RGB8                           = 0x8051;
	public static final int GL_RGB10                          = 0x8052;
	public static final int GL_RGB12                          = 0x8053;
	public static final int GL_RGB16                          = 0x8054;
	public static final int GL_RGBA2                          = 0x8055;
	public static final int GL_RGBA4                          = 0x8056;
	public static final int GL_RGB5_A1                        = 0x8057;
	public static final int GL_RGBA8                          = 0x8058;
	public static final int GL_RGB10_A2                       = 0x8059;
	public static final int GL_RGBA12                         = 0x805A;
	public static final int GL_RGBA16                         = 0x805B;
	public static final int GL_TEXTURE_RED_SIZE               = 0x805C;
	public static final int GL_TEXTURE_GREEN_SIZE             = 0x805D;
	public static final int GL_TEXTURE_BLUE_SIZE              = 0x805E;
	public static final int GL_TEXTURE_ALPHA_SIZE             = 0x805F;
	public static final int GL_TEXTURE_LUMINANCE_SIZE         = 0x8060;
	public static final int GL_TEXTURE_INTENSITY_SIZE         = 0x8061;
	public static final int GL_PROXY_TEXTURE_1D               = 0x8063;
	public static final int GL_PROXY_TEXTURE_2D               = 0x8064;

	/* texture_object */
	public static final int GL_TEXTURE_PRIORITY               = 0x8066;
	public static final int GL_TEXTURE_RESIDENT               = 0x8067;
	public static final int GL_TEXTURE_BINDING_1D             = 0x8068;
	public static final int GL_TEXTURE_BINDING_2D             = 0x8069;

	/* vertex_array */
	public static final int GL_VERTEX_ARRAY                   = 0x8074;
	public static final int GL_NORMAL_ARRAY                   = 0x8075;
	public static final int GL_COLOR_ARRAY                    = 0x8076;
	public static final int GL_INDEX_ARRAY                    = 0x8077;
	public static final int GL_TEXTURE_COORD_ARRAY            = 0x8078;
	public static final int GL_EDGE_FLAG_ARRAY                = 0x8079;
	public static final int GL_VERTEX_ARRAY_SIZE              = 0x807A;
	public static final int GL_VERTEX_ARRAY_TYPE              = 0x807B;
	public static final int GL_VERTEX_ARRAY_STRIDE            = 0x807C;
	public static final int GL_NORMAL_ARRAY_TYPE              = 0x807E;
	public static final int GL_NORMAL_ARRAY_STRIDE            = 0x807F;
	public static final int GL_COLOR_ARRAY_SIZE               = 0x8081;
	public static final int GL_COLOR_ARRAY_TYPE               = 0x8082;
	public static final int GL_COLOR_ARRAY_STRIDE             = 0x8083;
	public static final int GL_INDEX_ARRAY_TYPE               = 0x8085;
	public static final int GL_INDEX_ARRAY_STRIDE             = 0x8086;
	public static final int GL_TEXTURE_COORD_ARRAY_SIZE       = 0x8088;
	public static final int GL_TEXTURE_COORD_ARRAY_TYPE       = 0x8089;
	public static final int GL_TEXTURE_COORD_ARRAY_STRIDE     = 0x808A;
	public static final int GL_EDGE_FLAG_ARRAY_STRIDE         = 0x808C;
	public static final int GL_VERTEX_ARRAY_POINTER           = 0x808E;
	public static final int GL_NORMAL_ARRAY_POINTER           = 0x808F;
	public static final int GL_COLOR_ARRAY_POINTER            = 0x8090;
	public static final int GL_INDEX_ARRAY_POINTER            = 0x8091;
	public static final int GL_TEXTURE_COORD_ARRAY_POINTER    = 0x8092;
	public static final int GL_EDGE_FLAG_ARRAY_POINTER        = 0x8093;
	public static final int GL_V2F                            = 0x2A20;
	public static final int GL_V3F                            = 0x2A21;
	public static final int GL_C4UB_V2F                       = 0x2A22;
	public static final int GL_C4UB_V3F                       = 0x2A23;
	public static final int GL_C3F_V3F                        = 0x2A24;
	public static final int GL_N3F_V3F                        = 0x2A25;
	public static final int GL_C4F_N3F_V3F                    = 0x2A26;
	public static final int GL_T2F_V3F                        = 0x2A27;
	public static final int GL_T4F_V4F                        = 0x2A28;
	public static final int GL_T2F_C4UB_V3F                   = 0x2A29;
	public static final int GL_T2F_C3F_V3F                    = 0x2A2A;
	public static final int GL_T2F_N3F_V3F                    = 0x2A2B;
	public static final int GL_T2F_C4F_N3F_V3F                = 0x2A2C;
	public static final int GL_T4F_C4F_N3F_V4F                = 0x2A2D;

	/* For compatibility with OpenGL v1.0 */
	public static final int GL_LOGIC_OP = GL_INDEX_LOGIC_OP;
	public static final int GL_TEXTURE_COMPONENTS = GL_TEXTURE_INTERNAL_FORMAT;

	private GL11() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glAccum(int op, float value);
	public static native void glAlphaFunc(int func, float ref);
	public static native void glClearColor(float red, float green, float blue, float alpha);
	public static native void glClearAccum(float red, float green, float blue, float alpha);
	public static native void glClear(int mask);

	// ---------------------------
	public static void glCallLists(ByteBuffer lists) {
		BufferChecks.checkDirect(lists);
		nglCallLists(lists.remaining(), GL_UNSIGNED_BYTE, lists, lists.position());
	}
	public static void glCallLists(ShortBuffer lists) {
		BufferChecks.checkDirect(lists);
		nglCallLists(lists.remaining(), GL_UNSIGNED_SHORT, lists, lists.position() << 1);
	}
	public static void glCallLists(IntBuffer lists) {
		BufferChecks.checkDirect(lists);
		nglCallLists(lists.remaining(), GL_UNSIGNED_INT, lists, lists.position() << 2);
	}
	private static native void nglCallLists(int n, int type, Buffer lists, int lists_offset);
	// ---------------------------

	public static native void glCallList(int list);
	public static native void glBlendFunc(int sfactor, int dfactor);

	// ---------------------------
	public static void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, ByteBuffer bitmap) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(bitmap, ((width + 7) / 8) * height);
		nglBitmap(width, height, xorig, yorig, xmove, ymove, bitmap, bitmap.position());
	}
	private static native void nglBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, ByteBuffer bitmap, int bitmap_offset);

	public static void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, int buffer_offect) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglBitmapBO(width, height, xorig, yorig, xmove, ymove, buffer_offect);
	}
	private static native void nglBitmapBO(int width, int height, float xorig, float yorig, float xmove, float ymove, int buffer_offset);
	// ---------------------------

	public static native void glBindTexture(int target, int texture);
	public static native void glBegin(int mode);
	public static native void glEnd();
	public static native void glArrayElement(int i);
	public static native void glClearDepth(double depth);
	public static native void glDeleteLists(int list, int range);

	public static void glDeleteTextures(IntBuffer textures) {
		BufferChecks.checkDirect(textures);
		nglDeleteTextures(textures.remaining(), textures, textures.position());
	}
	private static native void nglDeleteTextures(int n, IntBuffer textures, int textures_offset);

	public static native void glCullFace(int mode);
	public static native void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height);
	public static native void glCopyTexSubImage1D(int target, int level, int xoffset, int x, int y, int width);
	public static native void glCopyTexImage2D(int target, int level, int internalFormat, int x, int y, int width, int height, int border);
	public static native void glCopyTexImage1D(int target, int level, int internalFormat, int x, int y, int width, int border);
	public static native void glCopyPixels(int x, int y, int width, int height, int type);

	// ---------------------------
	public static void glColorPointer(int size, boolean unsigned, int stride, ByteBuffer pointer) {
		BufferChecks.checkDirect(pointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglColorPointer(size, unsigned ? GL_UNSIGNED_BYTE : GL_BYTE, stride, pointer, pointer.position());
	}
	public static void glColorPointer(int size, int stride, FloatBuffer pointer) {
		BufferChecks.checkDirect(pointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglColorPointer(size, GL_FLOAT, stride, pointer, pointer.position() << 2);
	}
	private static native void nglColorPointer(int size, int type, int stride, Buffer pointer, int pointer_offset);

	public static void glColorPointer(int size, int type, int stride, int buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglColorPointerVBO(size, type, stride, buffer_offset);
	}
	private static native void nglColorPointerVBO(int size, int type, int stride, int buffer_offset);
	// ---------------------------

	public static native void glColorMaterial(int face, int mode);
	public static native void glColorMask(boolean red, boolean green, boolean blue, boolean alpha);
	public static native void glColor3b(byte red, byte green, byte blue);
	public static native void glColor3f(float red, float green, float blue);
	public static native void glColor3ub(byte red, byte green, byte blue);
	public static native void glColor4b(byte red, byte green, byte blue, byte alpha);
	public static native void glColor4f(float red, float green, float blue, float alpha);
	public static native void glColor4ub(byte red, byte green, byte blue, byte alpha);

	public static void glClipPlane(int plane, DoubleBuffer equation) {
		BufferChecks.checkBuffer(equation, 4);
		nglClipPlane(plane, equation, equation.position() << 3);
	}
	private static native void nglClipPlane(int plane, DoubleBuffer equation, int equation_offset);

	public static native void glClearStencil(int s);
	public static native void glClearIndex(float c);
	public static native void glEvalPoint1(int i);
	public static native void glEvalPoint2(int i, int j);
	public static native void glEvalMesh1(int mode, int i1, int i2);
	public static native void glEvalMesh2(int mode, int i1, int i2, int j1, int j2);
	public static native void glEvalCoord1f(float u);
	public static native void glEvalCoord2f(float u, float v);
	public static native void glEnableClientState(int cap);
	public static native void glDisableClientState(int cap);
	public static native void glEnable(int cap);
	public static native void glDisable(int cap);

	// ---------------------------
	public static void glEdgeFlagPointer(int stride, ByteBuffer pointer) {
		BufferChecks.checkDirect(pointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglEdgeFlagPointer(stride, pointer, pointer.position());
	}
	private static native void nglEdgeFlagPointer(int stride, Buffer pointer, int pointer_offset);

	public static void glEdgeFlagPointer(int stride, int buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglEdgeFlagPointerVBO(stride, buffer_offset);
	}
	private static native void nglEdgeFlagPointerVBO(int stride, int buffer_offset);
	// ---------------------------

	public static native void glEdgeFlag(boolean flag);

	// ---------------------------
	public static void glDrawPixels(int width, int height, int format, int type, ByteBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, 1));
		nglDrawPixels(width, height, format, type, pixels, pixels.position());
	}
	public static void glDrawPixels(int width, int height, int format, int type, ShortBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, 1)>>1);
		nglDrawPixels(width, height, format, type, pixels, pixels.position() << 1);
	}
	public static void glDrawPixels(int width, int height, int format, int type, IntBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, 1)>>2);
		nglDrawPixels(width, height, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglDrawPixels(int width, int height, int format, int type, Buffer pixels, int pixels_offset);

	public static void glDrawPixels(int width, int height, int format, int type, int buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglDrawPixelsBO(width, height, format, type, buffer_offset);
	}
	private static native void nglDrawPixelsBO(int width, int height, int format, int type, int buffer_offset);
	// ---------------------------

	// ---------------------------
	public static void glDrawElements(int mode, ByteBuffer indices) {
		BufferChecks.checkDirect(indices);
		GLBufferChecks.ensureElementVBOdisabled();
		nglDrawElements(mode, indices.remaining(), GL_UNSIGNED_BYTE, indices, indices.position());
	}
	public static void glDrawElements(int mode, ShortBuffer indices) {
		BufferChecks.checkDirect(indices);
		GLBufferChecks.ensureElementVBOdisabled();
		nglDrawElements(mode, indices.remaining(), GL_UNSIGNED_SHORT, indices, indices.position() << 1);
	}
	public static void glDrawElements(int mode, IntBuffer indices) {
		BufferChecks.checkDirect(indices);
		GLBufferChecks.ensureElementVBOdisabled();
		nglDrawElements(mode, indices.remaining(), GL_UNSIGNED_INT, indices, indices.position() << 2);
	}
	private static native void nglDrawElements(int mode, int count, int type, Buffer indices, int indices_offset);

	public static void glDrawElements(int mode, int count, int type, int buffer_offset) {
		GLBufferChecks.ensureElementVBOenabled();
		nglDrawElementsVBO(mode, count, type, buffer_offset);
	}
	private static native void nglDrawElementsVBO(int mode, int count, int type, int buffer_offset);
	// ---------------------------

	public static native void glDrawBuffer(int mode);
	public static native void glDrawArrays(int mode, int first, int count);
	public static native void glDepthRange(double zNear, double zFar);
	public static native void glDepthMask(boolean flag);
	public static native void glDepthFunc(int func);

	public static void glFeedbackBuffer(int type, FloatBuffer buffer) {
		BufferChecks.checkDirect(buffer);
		nglFeedbackBuffer(buffer.remaining(), type, buffer, buffer.position());
	}
	private static native void nglFeedbackBuffer(int size, int type, FloatBuffer buffer, int buffer_offset);

	// ---------------------------
	public static void glGetPixelMap(int map, FloatBuffer values) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(values, 256);
		nglGetPixelMapfv(map, values, values.position());
	}
	private static native void nglGetPixelMapfv(int map, FloatBuffer values, int values_offset);

	public static void glGetPixelMapfv(int map, int buffer_offset) {
		GLBufferChecks.ensurePackPBOenabled();
		nglGetPixelMapfvBO(map, buffer_offset);
	}
	private static native void nglGetPixelMapfvBO(int map, int buffer_offset);
	// ---------------------------

	// ---------------------------
	public static void glGetPixelMap(int map, IntBuffer values) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(values, 256);
		nglGetPixelMapuiv(map, values, values.position());
	}
	private static native void nglGetPixelMapuiv(int map, IntBuffer values, int values_offset);

	public static void glGetPixelMapuiv(int map, int buffer_offset) {
		GLBufferChecks.ensurePackPBOenabled();
		nglGetPixelMapuivBO(map, buffer_offset);
	}
	private static native void nglGetPixelMapuivBO(int map, int buffer_offset);
	// ---------------------------

	// ---------------------------
	public static void glGetPixelMap(int map, ShortBuffer values) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(values, 256);
		nglGetPixelMapusv(map, values, values.position());
	}
	private static native void nglGetPixelMapusv(int map, ShortBuffer values, int values_offset);

	public static void glGetPixelMapusv(int map, int buffer_offset) {
		GLBufferChecks.ensurePackPBOenabled();
		nglGetPixelMapusvBO(map, buffer_offset);
	}
	private static native void nglGetPixelMapusvBO(int map, int buffer_offset);
	// ---------------------------

	public static void glGetMaterial(int face, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetMaterialfv(face, pname, params, params.position());
	}
	private static native void nglGetMaterialfv(int face, int pname, FloatBuffer params, int params_offset);

	public static void glGetMaterial(int face, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetMaterialiv(face, pname, params, params.position());
	}
	private static native void nglGetMaterialiv(int face, int pname, IntBuffer params, int params_offset);

	public static void glGetMap(int target, int query, FloatBuffer v) {
		BufferChecks.checkBuffer(v, 256);
		nglGetMapfv(target, query, v, v.position());
	}
	private static native void nglGetMapfv(int target, int query, FloatBuffer v, int v_offset);

	public static void glGetMap(int target, int query, IntBuffer v) {
		BufferChecks.checkBuffer(v, 256);
		nglGetMapiv(target, query, v, v.position());
	}
	private static native void nglGetMapiv(int target, int query, IntBuffer v, int v_offset);

	public static void glGetLight(int light, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetLightfv(light, pname, params, params.position());
	}
	private static native void nglGetLightfv(int light, int pname, FloatBuffer params, int params_offset);

	public static void glGetLight(int light, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetLightiv(light, pname, params, params.position());
	}
	private static native void nglGetLightiv(int light, int pname, IntBuffer params, int params_offset);

	public static native int glGetError();

	public static void glGetClipPlane(int plane, DoubleBuffer equation) {
		BufferChecks.checkBuffer(equation);
		nglGetClipPlane(plane, equation, equation.position());
	}
	private static native void nglGetClipPlane(int plane, DoubleBuffer equation, int equation_offset);

	public static void glGetBoolean(int pname, ByteBuffer params) {
		BufferChecks.checkBuffer(params, 16);
		nglGetBooleanv(pname, params, params.position());
	}
	private static native void nglGetBooleanv(int pname, ByteBuffer params, int params_offset);

	public static void glGetDouble(int pname, DoubleBuffer params) {
		BufferChecks.checkBuffer(params, 16);
		nglGetDoublev(pname, params, params.position());
	}
	private static native void nglGetDoublev(int pname, DoubleBuffer params, int params_offset);

	public static void glGetFloat(int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 16);
		nglGetFloatv(pname, params, params.position());
	}
	private static native void nglGetFloatv(int pname, FloatBuffer params, int params_offset);

	public static void glGetInteger(int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 16);
		nglGetIntegerv(pname, params, params.position());
	}
	private static native void nglGetIntegerv(int pname, IntBuffer params, int params_offset);

	public static void glGenTextures(IntBuffer textures) {
		BufferChecks.checkDirect(textures);
		nglGenTextures(textures.remaining(), textures, textures.position());
	}
	private static native void nglGenTextures(int n, IntBuffer textures, int textures_offset);

	public static native int glGenLists(int range);
	public static native void glFrustum(double left, double right, double bottom, double top, double zNear, double zFar);
	public static native void glFrontFace(int mode);
	public static native void glFogf(int pname, float param);
	public static native void glFogi(int pname, int param);

	public static void glFog(int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglFogfv(pname, params, params.position());
	}
	private static native void nglFogfv(int pname, FloatBuffer params, int params_offset);

	public static void glFog(int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglFogiv(pname, params, params.position());
	}
	private static native void nglFogiv(int pname, IntBuffer params, int params_offset);

	public static native void glFlush();
	public static native void glFinish();
	/**
	 * Fetch a pointer from OpenGL. Will return a ByteBuffer representing the pointer, where
	 * the size argument specifies the buffer size in bytes.
	 *
	 * @param size The size of the memory area pointed to. This is the size of the returned ByteBuffer.
	 * @return The ByteBuffer of the specified size pointing to the returned address.
	 */
	public static native ByteBuffer glGetPointerv(int pname, int size);
	public static native boolean glIsEnabled(int cap);

	// ---------------------------
	public static void glInterleavedArrays(int format, int stride, ByteBuffer pointer) {
		BufferChecks.checkDirect(pointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglInterleavedArrays(format, stride, pointer, pointer.position());
	}
	public static void glInterleavedArrays(int format, int stride, ShortBuffer pointer) {
		BufferChecks.checkDirect(pointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglInterleavedArrays(format, stride, pointer, pointer.position() << 1);
	}
	public static void glInterleavedArrays(int format, int stride, IntBuffer pointer) {
		BufferChecks.checkDirect(pointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglInterleavedArrays(format, stride, pointer, pointer.position() << 2);
	}
	public static void glInterleavedArrays(int format, int stride, FloatBuffer pointer) {
		BufferChecks.checkDirect(pointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglInterleavedArrays(format, stride, pointer, pointer.position() << 2);
	}
	private static native void nglInterleavedArrays(int format, int stride, Buffer pointer, int pointer_offset);

	public static void glInterleavedArrays(int format, int stride, int buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglInterleavedArraysVBO(format, stride, buffer_offset);
	}
	private static native void nglInterleavedArraysVBO(int format, int stride, int buffer_offset);
	// ---------------------------

	public static native void glInitNames();
	public static native void glHint(int target, int mode);

	public static void glGetTexParameter(int target, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetTexParameterfv(target, pname, params, params.position());
	}
	private static native void nglGetTexParameterfv(int target, int pname, FloatBuffer params, int params_offset);

	public static void glGetTexParameter(int target, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetTexParameteriv(target, pname, params, params.position());
	}
	private static native void nglGetTexParameteriv(int target, int pname, IntBuffer params, int params_offset);

	public static void glGetTexLevelParameter(int target, int level, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetTexLevelParameterfv(target, level, pname, params, params.position());
	}
	private static native void nglGetTexLevelParameterfv(int target, int level, int pname, FloatBuffer params, int params_offset);

	public static void glGetTexLevelParameter(int target, int level, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetTexLevelParameteriv(target, level, pname, params, params.position());
	}
	private static native void nglGetTexLevelParameteriv(int target, int level, int pname, IntBuffer params, int params_offset);

	// ---------------------------
	public static void glGetTexImage(int target, int level, int format, int type, ByteBuffer pixels) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, 1, 1, 1));
		nglGetTexImage(target, level, format, type, pixels, pixels.position());
	}
	public static void glGetTexImage(int target, int level, int format, int type, ShortBuffer pixels) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, 1, 1, 1)>>1);
		nglGetTexImage(target, level, format, type, pixels, pixels.position() << 1);
	}
	public static void glGetTexImage(int target, int level, int format, int type, IntBuffer pixels) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, 1, 1, 1)>>2);
		nglGetTexImage(target, level, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglGetTexImage(int target, int level, int format, int type, Buffer pixels, int pixels_offset);

	public static void glGetTexImage(int target, int level, int format, int type, int buffer_offset) {
		GLBufferChecks.ensurePackPBOenabled();
		nglGetTexImageBO(target, level, format, type, buffer_offset);
	}
	private static native void nglGetTexImageBO(int target, int level, int format, int type, int buffer_offset);
	// ---------------------------

	public static void glGetTexGen(int coord, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetTexGeniv(coord, pname, params, params.position());
	}
	private static native void nglGetTexGeniv(int coord, int pname, IntBuffer params, int params_offset);

	public static void glGetTexGen(int coord, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetTexGenfv(coord, pname, params, params.position());
	}
	private static native void nglGetTexGenfv(int coord, int pname, FloatBuffer params, int params_offset);

	public static void glGetTexEnv(int coord, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetTexEnviv(coord, pname, params, params.position());
	}
	private static native void nglGetTexEnviv(int coord, int pname, IntBuffer params, int params_offset);

	public static void glGetTexEnv(int coord, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglGetTexEnvfv(coord, pname, params, params.position());
	}
	private static native void nglGetTexEnvfv(int coord, int pname, FloatBuffer params, int params_offset);

	public static native String glGetString(int name);

	// ---------------------------
	public static void glGetPolygonStipple(ByteBuffer mask) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(mask, 1024);
		nglGetPolygonStipple(mask, mask.position());
	}
	private static native void nglGetPolygonStipple(ByteBuffer mask, int mask_offset);

	public static void glGetPolygonStipple(int buffer_offset) {
		GLBufferChecks.ensurePackPBOenabled();
		nglGetPolygonStippleBO(buffer_offset);
	}
	private static native void nglGetPolygonStippleBO(int buffer_offset);
	// ---------------------------

	public static native boolean glIsList(int list);
	public static native void glMaterialf(int face, int pname, float param);
	public static native void glMateriali(int face, int pname, int param);

	public static void glMaterial(int face, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglMaterialfv(face, pname, params, params.position());
	}
	private static native void nglMaterialfv(int face, int pname, FloatBuffer params, int params_offset);

	public static void glMaterial(int face, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglMaterialiv(face, pname, params, params.position());
	}
	private static native void nglMaterialiv(int face, int pname, IntBuffer params, int params_offset);

	public static native void glMapGrid1f(int un, float u1, float u2);
	public static native void glMapGrid2f(int un, float u1, float u2, int vn, float v1, float v2);

	public static void glMap2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points) {
		BufferChecks.checkDirect(points);
		// TODO: check buffer size valid
		nglMap2f(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, points, points.position());
	}
	private static native void nglMap2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points, int points_offset);

	public static void glMap1f(int target, float u1, float u2, int stride, int order, FloatBuffer points) {
		BufferChecks.checkDirect(points);
		// TODO: check buffer size valid
		nglMap1f(target, u1, u2, stride, order, points, points.position());
	}
	private static native void nglMap1f(int target, float u1, float u2, int stride, int order, FloatBuffer points, int points_offset);

	public static native void glLogicOp(int opcode);
	public static native void glLoadName(int name);

	public static void glLoadMatrix(FloatBuffer m) {
		BufferChecks.checkBuffer(m, 16);
		nglLoadMatrixf(m, m.position());
	}
	private static native void nglLoadMatrixf(FloatBuffer m, int m_offset);

	public static native void glLoadIdentity();
	public static native void glListBase(int base);
	public static native void glLineWidth(float width);
	public static native void glLineStipple(int factor, short pattern);
	public static native void glLightModelf(int pname, float param);
	public static native void glLightModeli(int pname, int param);

	public static void glLightModel(int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglLightModelfv( pname, params, params.position());
	}
	private static native void nglLightModelfv(int pname, FloatBuffer params, int params_offset);

	public static void glLightModel(int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglLightModeliv(pname, params, params.position());
	}
	private static native void nglLightModeliv(int pname, IntBuffer params, int params_offset);

	public static native void glLightf(int light, int pname, float param);
	public static native void glLighti(int light, int pname, int param);

	public static void glLight(int light, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglLightfv(light, pname, params, params.position());
	}
	private static native void nglLightfv(int light, int pname, FloatBuffer params, int params_offset);

	public static void glLight(int light, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglLightiv(light, pname, params, params.position());
	}
	private static native void nglLightiv(int light, int pname, IntBuffer params, int params_offset);

	public static native boolean glIsTexture(int texture);
	public static native void glMatrixMode(int mode);

	// ---------------------------
	public static void glPolygonStipple(ByteBuffer mask) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(mask, 1024);
		nglPolygonStipple(mask, mask.position());
	}
	private static native void nglPolygonStipple(ByteBuffer mask, int mask_offset);

	public static void glPolygonStipple(int buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglPolygonStippleBO(buffer_offset);
	}
	private static native void nglPolygonStippleBO(int buffer_offset);
	// ---------------------------

	public static native void glPolygonOffset(float factor, float units);
	public static native void glPolygonMode(int face, int mode);
	public static native void glPointSize(float size);
	public static native void glPixelZoom(float xfactor, float yfactor);
	public static native void glPixelTransferf(int pname, float param);
	public static native void glPixelTransferi(int pname, int param);
	public static native void glPixelStoref(int pname, float param);
	public static native void glPixelStorei(int pname, int param);

	// ---------------------------
	public static void glPixelMap(int map, FloatBuffer values) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkDirect(values);
		nglPixelMapfv(map, values.remaining(), values, values.position());
	}
	private static native void nglPixelMapfv(int map, int mapsize, FloatBuffer values, int values_offset);

	public static void glPixelMapfv(int map, int mapsize, int buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglPixelMapfvBO(map, mapsize, buffer_offset);
	}
	private static native void nglPixelMapfvBO(int map, int mapsize, int buffer_offset);
	// ---------------------------

	// ---------------------------
	public static void glPixelMap(int map, IntBuffer values) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkDirect(values);
		nglPixelMapuiv(map, values.remaining(), values, values.position());
	}
	private static native void nglPixelMapuiv(int map, int mapsize, IntBuffer values, int values_offset);

	public static void glPixelMapuiv(int map, int mapsize, int buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglPixelMapuivBO(map, mapsize, buffer_offset);
	}
	private static native void nglPixelMapuivBO(int map, int mapsize, int buffer_offset);
	// ---------------------------

	// ---------------------------
	public static void glPixelMap(int map, ShortBuffer values) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkDirect(values);
		nglPixelMapusv(map, values.remaining(), values, values.position());
	}
	private static native void nglPixelMapusv(int map, int mapsize, ShortBuffer values, int values_offset);

	public static void glPixelMapusv(int map, int mapsize, int buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglPixelMapusvBO(map, mapsize, buffer_offset);
	}
	private static native void nglPixelMapusvBO(int map, int mapsize, int buffer_offset);
	// ---------------------------

	public static native void glPassThrough(float token);
	public static native void glOrtho(double left, double right, double bottom, double top, double zNear, double zFar);

	// ---------------------------
	public static void glNormalPointer(int stride, ByteBuffer pointer) {
		BufferChecks.checkDirect(pointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglNormalPointer(GL_BYTE, stride, pointer, pointer.position());
	}
	public static void glNormalPointer(int stride, IntBuffer pointer) {
		BufferChecks.checkDirect(pointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglNormalPointer(GL_INT, stride, pointer, pointer.position() << 2);
	}
	public static void glNormalPointer(int stride, FloatBuffer pointer) {
		BufferChecks.checkDirect(pointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglNormalPointer(GL_FLOAT, stride, pointer, pointer.position() << 2);
	}
	private static native void nglNormalPointer(int type, int stride, Buffer pointer, int pointer_offset);

	public static void glNormalPointer(int type, int stride, int buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglNormalPointerVBO(type, stride, buffer_offset);
	}
	private static native void nglNormalPointerVBO(int type, int stride, int buffer_offset);
	// ---------------------------

	public static native void glNormal3b(byte nx, byte ny, byte nz);
	public static native void glNormal3f(float nx, float ny, float nz);
	public static native void glNormal3i(int nx, int ny, int nz);
	public static native void glNewList(int list, int mode);
	public static native void glEndList();

	public static void glMultMatrix(FloatBuffer m) {
		BufferChecks.checkBuffer(m, 16);
		nglMultMatrixf(m, m.position());
	}
	private static native void nglMultMatrixf(FloatBuffer m, int m_offset);

	public static native void glShadeModel(int mode);

	public static void glSelectBuffer(IntBuffer buffer) {
		BufferChecks.checkDirect(buffer);
		nglSelectBuffer(buffer.remaining(), buffer, buffer.position());
	}
	private static native void nglSelectBuffer(int size, IntBuffer buffer, int buffer_offset);

	public static native void glScissor(int x, int y, int width, int height);
	public static native void glScalef(float x, float y, float z);
	public static native void glRotatef(float angle, float x, float y, float z);
	public static native int glRenderMode(int mode);
	public static native void glRectf(float x1, float y1, float x2, float y2);
	public static native void glRecti(int x1, int y1, int x2, int y2);

	// ---------------------------
	public static void glReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer pixels) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, 1));
		nglReadPixels(x, y, width, height, format, type, pixels, pixels.position());
	}
	public static void glReadPixels(int x, int y, int width, int height, int format, int type, ShortBuffer pixels) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, 1)>>1);
		nglReadPixels(x, y, width, height, format, type, pixels, pixels.position() << 1);
	}
	public static void glReadPixels(int x, int y, int width, int height, int format, int type, IntBuffer pixels) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, 1)>>2);
		nglReadPixels(x, y, width, height, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglReadPixels(int x, int y, int width, int height, int format, int type, Buffer pixels, int pixels_offset);

	public static void glReadPixels(int x, int y, int width, int height, int format, int type, int buffer_offset) {
		GLBufferChecks.ensurePackPBOenabled();
		nglReadPixelsBO(x, y, width, height, format, type, buffer_offset);
	}
	private static native void nglReadPixelsBO(int x, int y, int width, int height, int format, int type, int buffer_offset);
	// ---------------------------

	public static native void glReadBuffer(int mode);
	public static native void glRasterPos2f(float x, float y);
	public static native void glRasterPos2i(int x, int y);
	public static native void glRasterPos3f(float x, float y, float z);
	public static native void glRasterPos3i(int x, int y, int z);
	public static native void glRasterPos4f(float x, float y, float z, float w);
	public static native void glRasterPos4i(int x, int y, int z, int w);
	public static native void glPushName(int name);
	public static native void glPopName();
	public static native void glPushMatrix();
	public static native void glPopMatrix();

	public static void glPushClientAttrib(int mask) {
		VBOTracker.getClientAttribStack().pushState();
		VBOTracker.getClientAttribStack().setState(mask);
		if ((mask & GL_CLIENT_VERTEX_ARRAY_BIT) != 0) {
			VBOTracker.getVBOArrayStack().pushState();
			VBOTracker.getVBOElementStack().pushState();
			VBOTracker.getPBOPackStack().pushState();
			VBOTracker.getPBOUnpackStack().pushState();
		}
		nglPushClientAttrib(mask);
	}
	private static native void nglPushClientAttrib(int mask);

	public static void glPopClientAttrib() {
		if ((VBOTracker.getClientAttribStack().popState() & GL_CLIENT_VERTEX_ARRAY_BIT) != 0) {
			VBOTracker.getVBOArrayStack().popState();
			VBOTracker.getVBOElementStack().popState();
			VBOTracker.getPBOPackStack().popState();
			VBOTracker.getPBOUnpackStack().popState();
		}
		nglPopClientAttrib();
	}
	private static native void nglPopClientAttrib();

	public static native void glPushAttrib(int mask);
	public static native void glPopAttrib();
	public static native void glStencilFunc(int func, int ref, int mask);

	// ---------------------------
	public static void glVertexPointer(int size, int stride, FloatBuffer pointer) {
		BufferChecks.checkDirect(pointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglVertexPointer(size, GL_FLOAT, stride, pointer, pointer.position() << 2);
	}
	public static void glVertexPointer(int size, int stride, IntBuffer pointer) {
		BufferChecks.checkDirect(pointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglVertexPointer(size, GL_INT, stride, pointer, pointer.position() << 2);
	}
	private static native void nglVertexPointer(int size, int type, int stride, Buffer pointer, int pointer_offset);

	public static void glVertexPointer(int size, int type, int stride, int buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglVertexPointerVBO(size, type, stride, buffer_offset);
	}
	private static native void nglVertexPointerVBO(int size, int type, int stride, int buffer_offset);
	// ---------------------------

	public static native void glVertex2f(float x, float y);
	public static native void glVertex2i(int x, int y);
	public static native void glVertex3f(float x, float y, float z);
	public static native void glVertex3i(int x, int y, int z);
	public static native void glVertex4f(float x, float y, float z, float w);
	public static native void glVertex4i(int x, int y, int z, int w);
	public static native void glTranslatef(float x, float y, float z);

	// ---------------------------
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ByteBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if ( pixels != null )
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage1DStorage(format, type, width, border));
		nglTexImage1D(target, level, internalformat, width, border, format, type, pixels, pixels != null ? pixels.position() : 0);
	}
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ShortBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if ( pixels != null )
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage1DStorage(format, type, width, border) >> 1);
		nglTexImage1D(target, level, internalformat, width, border, format, type, pixels, pixels != null ? pixels.position() << 1 : 0);
	}
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, IntBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if ( pixels != null )
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage1DStorage(format, type, width, border) >> 2);
		nglTexImage1D(target, level, internalformat, width, border, format, type, pixels, pixels != null ? pixels.position() << 2 : 0);
	}
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, FloatBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if ( pixels != null )
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage1DStorage(format, type, width, border) >> 2);
		nglTexImage1D(target, level, internalformat, width, border, format, type, pixels, pixels != null ? pixels.position() << 2 : 0);
	}
	private static native void nglTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, Buffer pixels, int pixels_offset);

	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, int buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglTexImage1DBO(target, level, internalformat, width, border, format, type, buffer_offset);
	}
	private static native void nglTexImage1DBO(int target, int level, int internalformat, int width, int border, int format, int type, int buffer_offset);
	// ---------------------------

	// ---------------------------
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if ( pixels != null )
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage2DStorage(format, type, width, height, border));
		nglTexImage2D(target, level, internalformat, width, height, border, format, type, pixels, pixels != null ? pixels.position() : 0);
	}
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ShortBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if ( pixels != null )
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage2DStorage(format, type, width, height, border) >> 1);
		nglTexImage2D(target, level, internalformat, width, height, border, format, type, pixels, pixels != null ? pixels.position() << 1 : 0);
	}
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, IntBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if ( pixels != null )
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage2DStorage(format, type, width, height, border) >> 2);
		nglTexImage2D(target, level, internalformat, width, height, border, format, type, pixels, pixels != null ? pixels.position() << 2 : 0);
	}
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, FloatBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if ( pixels != null )
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage2DStorage(format, type, width, height, border) >> 2);
		nglTexImage2D(target, level, internalformat, width, height, border, format, type, pixels, pixels != null ? pixels.position() << 2 : 0);
	}
	private static native void nglTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, Buffer pixels, int pixels_offset);

	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, int buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglTexImage2DBO(target, level, internalformat, width, height, border, format, type, buffer_offset);
	}
	private static native void nglTexImage2DBO(int target, int level, int internalformat, int width, int height, int border, int format, int type, int buffer_offset);
	// ---------------------------

	// ---------------------------
	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ByteBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, 1, 1));
		nglTexSubImage1D(target, level, xoffset, width, format, type, pixels, pixels.position());
	}
	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ShortBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, 1, 1) >> 1);
		nglTexSubImage1D(target, level, xoffset, width, format, type, pixels, pixels.position() << 1);
	}
	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, IntBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, 1, 1) >> 2);
		nglTexSubImage1D(target, level, xoffset, width, format, type, pixels, pixels.position() << 2);
	}
	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, FloatBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, 1, 1) >> 2);
		nglTexSubImage1D(target, level, xoffset, width, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, Buffer pixels, int pixels_offset);

	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, int buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglTexSubImage1DBO(target, level, xoffset, width, format, type, buffer_offset);
	}
	private static native void nglTexSubImage1DBO(int target, int level, int xoffset, int width, int format, int type, int buffer_offset);
	// ---------------------------

	// ---------------------------
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, 1));
		nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels, pixels.position());
	}
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ShortBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, 1)>>1);
		nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels, pixels.position() << 1);
	}
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, IntBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, 1)>>2);
		nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels, pixels.position() << 2);
	}
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, FloatBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(format, type, width, height, 1) >> 2);
		nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels, pixels.position() << 2);
	}
	private static native void nglTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, Buffer pixels, int pixels_offset);

	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, int buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglTexSubImage2DBO(target, level, xoffset, yoffset, width, height, format, type, buffer_offset);
	}
	private static native void nglTexSubImage2DBO(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, int buffer_offset);
	// ---------------------------

	public static native void glTexParameterf(int target, int pname, float param);
	public static native void glTexParameteri(int target, int pname, int param);

	public static void glTexParameter(int target, int pname, FloatBuffer param) {
		BufferChecks.checkBuffer(param);
		nglTexParameterfv(target, pname, param, param.position());
	}
	private static native void nglTexParameterfv(int target, int pname, FloatBuffer param, int param_position);

	public static void glTexParameter(int target, int pname, IntBuffer param) {
		BufferChecks.checkBuffer(param);
		nglTexParameteriv(target, pname, param, param.position());
	}
	private static native void nglTexParameteriv(int target, int pname, IntBuffer param, int param_position);

	public static native void glTexGenf(int coord, int pname, float param);

	public static void glTexGen(int coord, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglTexGenfv(coord, pname, params, params.position());
	}
	private static native void nglTexGenfv(int coord, int pname, FloatBuffer params, int params_offset);

	public static native void glTexGeni(int coord, int pname, int param);

	public static void glTexGen(int coord, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglTexGeniv(coord, pname, params, params.position());
	}
	private static native void nglTexGeniv(int coord, int pname, IntBuffer params, int params_offset);

	public static native void glTexEnvf(int target, int pname, float param);
	public static native void glTexEnvi(int target, int pname, int param);

	public static void glTexEnv(int target, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params);
		nglTexEnvfv(target, pname, params, params.position());
	}
	private static native void nglTexEnvfv(int target, int pname, FloatBuffer params, int params_offset);

	public static void glTexEnv(int target, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params);
		nglTexEnviv(target, pname, params, params.position());
	}
	private static native void nglTexEnviv(int target, int pname, IntBuffer params, int params_offset);

	public static void glTexCoordPointer(int size, int stride, FloatBuffer pointer) {
		BufferChecks.checkDirect(pointer);
		GLBufferChecks.ensureArrayVBOdisabled();
		nglTexCoordPointer(size, GL_FLOAT, stride, pointer, pointer.position() << 2);
	}
	private static native void nglTexCoordPointer(int size, int type, int stride, Buffer pointer, int pointer_offset);

	public static void glTexCoordPointer(int size, int type, int stride, int buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglTexCoordPointerVBO(size, type, stride, buffer_offset);
	}
	private static native void nglTexCoordPointerVBO(int size, int type, int stride, int buffer_offset);

	public static native void glTexCoord1f(float s);
	public static native void glTexCoord2f(float s, float t);
	public static native void glTexCoord3f(float s, float t, float r);
	public static native void glTexCoord4f(float s, float t, float r, float q);
	public static native void glStencilOp(int fail, int zfail, int zpass);
	public static native void glStencilMask(int mask);
	public static native void glViewport(int x, int y, int width, int height);
}