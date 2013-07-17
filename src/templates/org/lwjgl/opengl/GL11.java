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
package org.lwjgl.opengl;

import org.lwjgl.util.generator.*;
import org.lwjgl.util.generator.opengl.*;

import java.nio.*;

/**
 * The core OpenGL1.1 API.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 *          $Id$
 */
@DeprecatedGL
public interface GL11 {
	/* AccumOp */
	int GL_ACCUM = 0x0100;
	int GL_LOAD = 0x0101;
	int GL_RETURN = 0x0102;
	int GL_MULT = 0x0103;
	int GL_ADD = 0x0104;

	/* AlphaFunction */
	int GL_NEVER = 0x0200;
	int GL_LESS = 0x0201;
	int GL_EQUAL = 0x0202;
	int GL_LEQUAL = 0x0203;
	int GL_GREATER = 0x0204;
	int GL_NOTEQUAL = 0x0205;
	int GL_GEQUAL = 0x0206;
	int GL_ALWAYS = 0x0207;

	/* AttribMask */
	int GL_CURRENT_BIT = 0x00000001;
	int GL_POINT_BIT = 0x00000002;
	int GL_LINE_BIT = 0x00000004;
	int GL_POLYGON_BIT = 0x00000008;
	int GL_POLYGON_STIPPLE_BIT = 0x00000010;
	int GL_PIXEL_MODE_BIT = 0x00000020;
	int GL_LIGHTING_BIT = 0x00000040;
	int GL_FOG_BIT = 0x00000080;
	int GL_DEPTH_BUFFER_BIT = 0x00000100;
	int GL_ACCUM_BUFFER_BIT = 0x00000200;
	int GL_STENCIL_BUFFER_BIT = 0x00000400;
	int GL_VIEWPORT_BIT = 0x00000800;
	int GL_TRANSFORM_BIT = 0x00001000;
	int GL_ENABLE_BIT = 0x00002000;
	int GL_COLOR_BUFFER_BIT = 0x00004000;
	int GL_HINT_BIT = 0x00008000;
	int GL_EVAL_BIT = 0x00010000;
	int GL_LIST_BIT = 0x00020000;
	int GL_TEXTURE_BIT = 0x00040000;
	int GL_SCISSOR_BIT = 0x00080000;
	int GL_ALL_ATTRIB_BITS = 0x000fffff;

	/* BeginMode */
	int GL_POINTS = 0x0000;
	int GL_LINES = 0x0001;
	int GL_LINE_LOOP = 0x0002;
	int GL_LINE_STRIP = 0x0003;
	int GL_TRIANGLES = 0x0004;
	int GL_TRIANGLE_STRIP = 0x0005;
	int GL_TRIANGLE_FAN = 0x0006;
	int GL_QUADS = 0x0007;
	int GL_QUAD_STRIP = 0x0008;
	int GL_POLYGON = 0x0009;

	/* BlendingFactorDest */
	int GL_ZERO = 0;
	int GL_ONE = 1;
	int GL_SRC_COLOR = 0x0300;
	int GL_ONE_MINUS_SRC_COLOR = 0x0301;
	int GL_SRC_ALPHA = 0x0302;
	int GL_ONE_MINUS_SRC_ALPHA = 0x0303;
	int GL_DST_ALPHA = 0x0304;
	int GL_ONE_MINUS_DST_ALPHA = 0x0305;

	/* BlendingFactorSrc */
	/*      GL_ZERO */
	/*      GL_ONE */
	int GL_DST_COLOR = 0x0306;
	int GL_ONE_MINUS_DST_COLOR = 0x0307;
	int GL_SRC_ALPHA_SATURATE = 0x0308;
	int GL_CONSTANT_COLOR = 0x8001;
	int GL_ONE_MINUS_CONSTANT_COLOR = 0x8002;
	int GL_CONSTANT_ALPHA = 0x8003;
	int GL_ONE_MINUS_CONSTANT_ALPHA = 0x8004;

	/* Boolean */
	int GL_TRUE = 1;
	int GL_FALSE = 0;

	/* ClipPlaneName */
	int GL_CLIP_PLANE0 = 0x3000;
	int GL_CLIP_PLANE1 = 0x3001;
	int GL_CLIP_PLANE2 = 0x3002;
	int GL_CLIP_PLANE3 = 0x3003;
	int GL_CLIP_PLANE4 = 0x3004;
	int GL_CLIP_PLANE5 = 0x3005;

	/* DataType */
	int GL_BYTE = 0x1400;
	int GL_UNSIGNED_BYTE = 0x1401;
	int GL_SHORT = 0x1402;
	int GL_UNSIGNED_SHORT = 0x1403;
	int GL_INT = 0x1404;
	int GL_UNSIGNED_INT = 0x1405;
	int GL_FLOAT = 0x1406;
	int GL_2_BYTES = 0x1407;
	int GL_3_BYTES = 0x1408;
	int GL_4_BYTES = 0x1409;
	int GL_DOUBLE = 0x140A;

	/* DrawBufferMode */
	int GL_NONE = 0;
	int GL_FRONT_LEFT = 0x0400;
	int GL_FRONT_RIGHT = 0x0401;
	int GL_BACK_LEFT = 0x0402;
	int GL_BACK_RIGHT = 0x0403;
	int GL_FRONT = 0x0404;
	int GL_BACK = 0x0405;
	int GL_LEFT = 0x0406;
	int GL_RIGHT = 0x0407;
	int GL_FRONT_AND_BACK = 0x0408;
	int GL_AUX0 = 0x0409;
	int GL_AUX1 = 0x040A;
	int GL_AUX2 = 0x040B;
	int GL_AUX3 = 0x040C;

	/* ErrorCode */
	int GL_NO_ERROR = 0;
	int GL_INVALID_ENUM = 0x0500;
	int GL_INVALID_VALUE = 0x0501;
	int GL_INVALID_OPERATION = 0x0502;
	int GL_STACK_OVERFLOW = 0x0503;
	int GL_STACK_UNDERFLOW = 0x0504;
	int GL_OUT_OF_MEMORY = 0x0505;

	/* FeedBackMode */
	int GL_2D = 0x0600;
	int GL_3D = 0x0601;
	int GL_3D_COLOR = 0x0602;
	int GL_3D_COLOR_TEXTURE = 0x0603;
	int GL_4D_COLOR_TEXTURE = 0x0604;

	/* FeedBackToken */
	int GL_PASS_THROUGH_TOKEN = 0x0700;
	int GL_POINT_TOKEN = 0x0701;
	int GL_LINE_TOKEN = 0x0702;
	int GL_POLYGON_TOKEN = 0x0703;
	int GL_BITMAP_TOKEN = 0x0704;
	int GL_DRAW_PIXEL_TOKEN = 0x0705;
	int GL_COPY_PIXEL_TOKEN = 0x0706;
	int GL_LINE_RESET_TOKEN = 0x0707;

	/* FogMode */
	/*      GL_LINEAR */
	int GL_EXP = 0x0800;
	int GL_EXP2 = 0x0801;

	/* FrontFaceDirection */
	int GL_CW = 0x0900;
	int GL_CCW = 0x0901;

	/* GetMapTarget */
	int GL_COEFF = 0x0A00;
	int GL_ORDER = 0x0A01;
	int GL_DOMAIN = 0x0A02;

	/* GetTarget */
	int GL_CURRENT_COLOR = 0x0B00;
	int GL_CURRENT_INDEX = 0x0B01;
	int GL_CURRENT_NORMAL = 0x0B02;
	int GL_CURRENT_TEXTURE_COORDS = 0x0B03;
	int GL_CURRENT_RASTER_COLOR = 0x0B04;
	int GL_CURRENT_RASTER_INDEX = 0x0B05;
	int GL_CURRENT_RASTER_TEXTURE_COORDS = 0x0B06;
	int GL_CURRENT_RASTER_POSITION = 0x0B07;
	int GL_CURRENT_RASTER_POSITION_VALID = 0x0B08;
	int GL_CURRENT_RASTER_DISTANCE = 0x0B09;
	int GL_POINT_SMOOTH = 0x0B10;
	int GL_POINT_SIZE = 0x0B11;
	int GL_POINT_SIZE_RANGE = 0x0B12;
	int GL_POINT_SIZE_GRANULARITY = 0x0B13;
	int GL_LINE_SMOOTH = 0x0B20;
	int GL_LINE_WIDTH = 0x0B21;
	int GL_LINE_WIDTH_RANGE = 0x0B22;
	int GL_LINE_WIDTH_GRANULARITY = 0x0B23;
	int GL_LINE_STIPPLE = 0x0B24;
	int GL_LINE_STIPPLE_PATTERN = 0x0B25;
	int GL_LINE_STIPPLE_REPEAT = 0x0B26;
	int GL_LIST_MODE = 0x0B30;
	int GL_MAX_LIST_NESTING = 0x0B31;
	int GL_LIST_BASE = 0x0B32;
	int GL_LIST_INDEX = 0x0B33;
	int GL_POLYGON_MODE = 0x0B40;
	int GL_POLYGON_SMOOTH = 0x0B41;
	int GL_POLYGON_STIPPLE = 0x0B42;
	int GL_EDGE_FLAG = 0x0B43;
	int GL_CULL_FACE = 0x0B44;
	int GL_CULL_FACE_MODE = 0x0B45;
	int GL_FRONT_FACE = 0x0B46;
	int GL_LIGHTING = 0x0B50;
	int GL_LIGHT_MODEL_LOCAL_VIEWER = 0x0B51;
	int GL_LIGHT_MODEL_TWO_SIDE = 0x0B52;
	int GL_LIGHT_MODEL_AMBIENT = 0x0B53;
	int GL_SHADE_MODEL = 0x0B54;
	int GL_COLOR_MATERIAL_FACE = 0x0B55;
	int GL_COLOR_MATERIAL_PARAMETER = 0x0B56;
	int GL_COLOR_MATERIAL = 0x0B57;
	int GL_FOG = 0x0B60;
	int GL_FOG_INDEX = 0x0B61;
	int GL_FOG_DENSITY = 0x0B62;
	int GL_FOG_START = 0x0B63;
	int GL_FOG_END = 0x0B64;
	int GL_FOG_MODE = 0x0B65;
	int GL_FOG_COLOR = 0x0B66;
	int GL_DEPTH_RANGE = 0x0B70;
	int GL_DEPTH_TEST = 0x0B71;
	int GL_DEPTH_WRITEMASK = 0x0B72;
	int GL_DEPTH_CLEAR_VALUE = 0x0B73;
	int GL_DEPTH_FUNC = 0x0B74;
	int GL_ACCUM_CLEAR_VALUE = 0x0B80;
	int GL_STENCIL_TEST = 0x0B90;
	int GL_STENCIL_CLEAR_VALUE = 0x0B91;
	int GL_STENCIL_FUNC = 0x0B92;
	int GL_STENCIL_VALUE_MASK = 0x0B93;
	int GL_STENCIL_FAIL = 0x0B94;
	int GL_STENCIL_PASS_DEPTH_FAIL = 0x0B95;
	int GL_STENCIL_PASS_DEPTH_PASS = 0x0B96;
	int GL_STENCIL_REF = 0x0B97;
	int GL_STENCIL_WRITEMASK = 0x0B98;
	int GL_MATRIX_MODE = 0x0BA0;
	int GL_NORMALIZE = 0x0BA1;
	int GL_VIEWPORT = 0x0BA2;
	int GL_MODELVIEW_STACK_DEPTH = 0x0BA3;
	int GL_PROJECTION_STACK_DEPTH = 0x0BA4;
	int GL_TEXTURE_STACK_DEPTH = 0x0BA5;
	int GL_MODELVIEW_MATRIX = 0x0BA6;
	int GL_PROJECTION_MATRIX = 0x0BA7;
	int GL_TEXTURE_MATRIX = 0x0BA8;
	int GL_ATTRIB_STACK_DEPTH = 0x0BB0;
	int GL_CLIENT_ATTRIB_STACK_DEPTH = 0x0BB1;
	int GL_ALPHA_TEST = 0x0BC0;
	int GL_ALPHA_TEST_FUNC = 0x0BC1;
	int GL_ALPHA_TEST_REF = 0x0BC2;
	int GL_DITHER = 0x0BD0;
	int GL_BLEND_DST = 0x0BE0;
	int GL_BLEND_SRC = 0x0BE1;
	int GL_BLEND = 0x0BE2;
	int GL_LOGIC_OP_MODE = 0x0BF0;
	int GL_INDEX_LOGIC_OP = 0x0BF1;
	int GL_COLOR_LOGIC_OP = 0x0BF2;
	int GL_AUX_BUFFERS = 0x0C00;
	int GL_DRAW_BUFFER = 0x0C01;
	int GL_READ_BUFFER = 0x0C02;
	int GL_SCISSOR_BOX = 0x0C10;
	int GL_SCISSOR_TEST = 0x0C11;
	int GL_INDEX_CLEAR_VALUE = 0x0C20;
	int GL_INDEX_WRITEMASK = 0x0C21;
	int GL_COLOR_CLEAR_VALUE = 0x0C22;
	int GL_COLOR_WRITEMASK = 0x0C23;
	int GL_INDEX_MODE = 0x0C30;
	int GL_RGBA_MODE = 0x0C31;
	int GL_DOUBLEBUFFER = 0x0C32;
	int GL_STEREO = 0x0C33;
	int GL_RENDER_MODE = 0x0C40;
	int GL_PERSPECTIVE_CORRECTION_HINT = 0x0C50;
	int GL_POINT_SMOOTH_HINT = 0x0C51;
	int GL_LINE_SMOOTH_HINT = 0x0C52;
	int GL_POLYGON_SMOOTH_HINT = 0x0C53;
	int GL_FOG_HINT = 0x0C54;
	int GL_TEXTURE_GEN_S = 0x0C60;
	int GL_TEXTURE_GEN_T = 0x0C61;
	int GL_TEXTURE_GEN_R = 0x0C62;
	int GL_TEXTURE_GEN_Q = 0x0C63;
	int GL_PIXEL_MAP_I_TO_I = 0x0C70;
	int GL_PIXEL_MAP_S_TO_S = 0x0C71;
	int GL_PIXEL_MAP_I_TO_R = 0x0C72;
	int GL_PIXEL_MAP_I_TO_G = 0x0C73;
	int GL_PIXEL_MAP_I_TO_B = 0x0C74;
	int GL_PIXEL_MAP_I_TO_A = 0x0C75;
	int GL_PIXEL_MAP_R_TO_R = 0x0C76;
	int GL_PIXEL_MAP_G_TO_G = 0x0C77;
	int GL_PIXEL_MAP_B_TO_B = 0x0C78;
	int GL_PIXEL_MAP_A_TO_A = 0x0C79;
	int GL_PIXEL_MAP_I_TO_I_SIZE = 0x0CB0;
	int GL_PIXEL_MAP_S_TO_S_SIZE = 0x0CB1;
	int GL_PIXEL_MAP_I_TO_R_SIZE = 0x0CB2;
	int GL_PIXEL_MAP_I_TO_G_SIZE = 0x0CB3;
	int GL_PIXEL_MAP_I_TO_B_SIZE = 0x0CB4;
	int GL_PIXEL_MAP_I_TO_A_SIZE = 0x0CB5;
	int GL_PIXEL_MAP_R_TO_R_SIZE = 0x0CB6;
	int GL_PIXEL_MAP_G_TO_G_SIZE = 0x0CB7;
	int GL_PIXEL_MAP_B_TO_B_SIZE = 0x0CB8;
	int GL_PIXEL_MAP_A_TO_A_SIZE = 0x0CB9;
	int GL_UNPACK_SWAP_BYTES = 0x0CF0;
	int GL_UNPACK_LSB_FIRST = 0x0CF1;
	int GL_UNPACK_ROW_LENGTH = 0x0CF2;
	int GL_UNPACK_SKIP_ROWS = 0x0CF3;
	int GL_UNPACK_SKIP_PIXELS = 0x0CF4;
	int GL_UNPACK_ALIGNMENT = 0x0CF5;
	int GL_PACK_SWAP_BYTES = 0x0D00;
	int GL_PACK_LSB_FIRST = 0x0D01;
	int GL_PACK_ROW_LENGTH = 0x0D02;
	int GL_PACK_SKIP_ROWS = 0x0D03;
	int GL_PACK_SKIP_PIXELS = 0x0D04;
	int GL_PACK_ALIGNMENT = 0x0D05;
	int GL_MAP_COLOR = 0x0D10;
	int GL_MAP_STENCIL = 0x0D11;
	int GL_INDEX_SHIFT = 0x0D12;
	int GL_INDEX_OFFSET = 0x0D13;
	int GL_RED_SCALE = 0x0D14;
	int GL_RED_BIAS = 0x0D15;
	int GL_ZOOM_X = 0x0D16;
	int GL_ZOOM_Y = 0x0D17;
	int GL_GREEN_SCALE = 0x0D18;
	int GL_GREEN_BIAS = 0x0D19;
	int GL_BLUE_SCALE = 0x0D1A;
	int GL_BLUE_BIAS = 0x0D1B;
	int GL_ALPHA_SCALE = 0x0D1C;
	int GL_ALPHA_BIAS = 0x0D1D;
	int GL_DEPTH_SCALE = 0x0D1E;
	int GL_DEPTH_BIAS = 0x0D1F;
	int GL_MAX_EVAL_ORDER = 0x0D30;
	int GL_MAX_LIGHTS = 0x0D31;
	int GL_MAX_CLIP_PLANES = 0x0D32;
	int GL_MAX_TEXTURE_SIZE = 0x0D33;
	int GL_MAX_PIXEL_MAP_TABLE = 0x0D34;
	int GL_MAX_ATTRIB_STACK_DEPTH = 0x0D35;
	int GL_MAX_MODELVIEW_STACK_DEPTH = 0x0D36;
	int GL_MAX_NAME_STACK_DEPTH = 0x0D37;
	int GL_MAX_PROJECTION_STACK_DEPTH = 0x0D38;
	int GL_MAX_TEXTURE_STACK_DEPTH = 0x0D39;
	int GL_MAX_VIEWPORT_DIMS = 0x0D3A;
	int GL_MAX_CLIENT_ATTRIB_STACK_DEPTH = 0x0D3B;
	int GL_SUBPIXEL_BITS = 0x0D50;
	int GL_INDEX_BITS = 0x0D51;
	int GL_RED_BITS = 0x0D52;
	int GL_GREEN_BITS = 0x0D53;
	int GL_BLUE_BITS = 0x0D54;
	int GL_ALPHA_BITS = 0x0D55;
	int GL_DEPTH_BITS = 0x0D56;
	int GL_STENCIL_BITS = 0x0D57;
	int GL_ACCUM_RED_BITS = 0x0D58;
	int GL_ACCUM_GREEN_BITS = 0x0D59;
	int GL_ACCUM_BLUE_BITS = 0x0D5A;
	int GL_ACCUM_ALPHA_BITS = 0x0D5B;
	int GL_NAME_STACK_DEPTH = 0x0D70;
	int GL_AUTO_NORMAL = 0x0D80;
	int GL_MAP1_COLOR_4 = 0x0D90;
	int GL_MAP1_INDEX = 0x0D91;
	int GL_MAP1_NORMAL = 0x0D92;
	int GL_MAP1_TEXTURE_COORD_1 = 0x0D93;
	int GL_MAP1_TEXTURE_COORD_2 = 0x0D94;
	int GL_MAP1_TEXTURE_COORD_3 = 0x0D95;
	int GL_MAP1_TEXTURE_COORD_4 = 0x0D96;
	int GL_MAP1_VERTEX_3 = 0x0D97;
	int GL_MAP1_VERTEX_4 = 0x0D98;
	int GL_MAP2_COLOR_4 = 0x0DB0;
	int GL_MAP2_INDEX = 0x0DB1;
	int GL_MAP2_NORMAL = 0x0DB2;
	int GL_MAP2_TEXTURE_COORD_1 = 0x0DB3;
	int GL_MAP2_TEXTURE_COORD_2 = 0x0DB4;
	int GL_MAP2_TEXTURE_COORD_3 = 0x0DB5;
	int GL_MAP2_TEXTURE_COORD_4 = 0x0DB6;
	int GL_MAP2_VERTEX_3 = 0x0DB7;
	int GL_MAP2_VERTEX_4 = 0x0DB8;
	int GL_MAP1_GRID_DOMAIN = 0x0DD0;
	int GL_MAP1_GRID_SEGMENTS = 0x0DD1;
	int GL_MAP2_GRID_DOMAIN = 0x0DD2;
	int GL_MAP2_GRID_SEGMENTS = 0x0DD3;
	int GL_TEXTURE_1D = 0x0DE0;
	int GL_TEXTURE_2D = 0x0DE1;
	int GL_FEEDBACK_BUFFER_POINTER = 0x0DF0;
	int GL_FEEDBACK_BUFFER_SIZE = 0x0DF1;
	int GL_FEEDBACK_BUFFER_TYPE = 0x0DF2;
	int GL_SELECTION_BUFFER_POINTER = 0x0DF3;
	int GL_SELECTION_BUFFER_SIZE = 0x0DF4;

	/* GetTextureParameter */
	/*      GL_TEXTURE_MAG_FILTER */
	/*      GL_TEXTURE_MIN_FILTER */
	/*      GL_TEXTURE_WRAP_S */
	/*      GL_TEXTURE_WRAP_T */
	int GL_TEXTURE_WIDTH = 0x1000;
	int GL_TEXTURE_HEIGHT = 0x1001;
	int GL_TEXTURE_INTERNAL_FORMAT = 0x1003;
	int GL_TEXTURE_BORDER_COLOR = 0x1004;
	int GL_TEXTURE_BORDER = 0x1005;
	/*      GL_TEXTURE_RED_SIZE */
	/*      GL_TEXTURE_GREEN_SIZE */
	/*      GL_TEXTURE_BLUE_SIZE */
	/*      GL_TEXTURE_ALPHA_SIZE */
	/*      GL_TEXTURE_LUMINANCE_SIZE */
	/*      GL_TEXTURE_INTENSITY_SIZE */
	/*      GL_TEXTURE_PRIORITY */
	/*      GL_TEXTURE_RESIDENT */

	/* HintMode */
	int GL_DONT_CARE = 0x1100;
	int GL_FASTEST = 0x1101;
	int GL_NICEST = 0x1102;

	/* LightName */
	int GL_LIGHT0 = 0x4000;
	int GL_LIGHT1 = 0x4001;
	int GL_LIGHT2 = 0x4002;
	int GL_LIGHT3 = 0x4003;
	int GL_LIGHT4 = 0x4004;
	int GL_LIGHT5 = 0x4005;
	int GL_LIGHT6 = 0x4006;
	int GL_LIGHT7 = 0x4007;

	/* LightParameter */
	int GL_AMBIENT = 0x1200;
	int GL_DIFFUSE = 0x1201;
	int GL_SPECULAR = 0x1202;
	int GL_POSITION = 0x1203;
	int GL_SPOT_DIRECTION = 0x1204;
	int GL_SPOT_EXPONENT = 0x1205;
	int GL_SPOT_CUTOFF = 0x1206;
	int GL_CONSTANT_ATTENUATION = 0x1207;
	int GL_LINEAR_ATTENUATION = 0x1208;
	int GL_QUADRATIC_ATTENUATION = 0x1209;

	/* ListMode */
	int GL_COMPILE = 0x1300;
	int GL_COMPILE_AND_EXECUTE = 0x1301;

	/* LogicOp */
	int GL_CLEAR = 0x1500;
	int GL_AND = 0x1501;
	int GL_AND_REVERSE = 0x1502;
	int GL_COPY = 0x1503;
	int GL_AND_INVERTED = 0x1504;
	int GL_NOOP = 0x1505;
	int GL_XOR = 0x1506;
	int GL_OR = 0x1507;
	int GL_NOR = 0x1508;
	int GL_EQUIV = 0x1509;
	int GL_INVERT = 0x150A;
	int GL_OR_REVERSE = 0x150B;
	int GL_COPY_INVERTED = 0x150C;
	int GL_OR_INVERTED = 0x150D;
	int GL_NAND = 0x150E;
	int GL_SET = 0x150F;

	/* MaterialParameter */
	int GL_EMISSION = 0x1600;
	int GL_SHININESS = 0x1601;
	int GL_AMBIENT_AND_DIFFUSE = 0x1602;
	int GL_COLOR_INDEXES = 0x1603;
	/*      GL_AMBIENT */
	/*      GL_DIFFUSE */
	/*      GL_SPECULAR */

	/* MatrixMode */
	int GL_MODELVIEW = 0x1700;
	int GL_PROJECTION = 0x1701;
	int GL_TEXTURE = 0x1702;

	/* PixelCopyType */
	int GL_COLOR = 0x1800;
	int GL_DEPTH = 0x1801;
	int GL_STENCIL = 0x1802;

	/* PixelFormat */
	int GL_COLOR_INDEX = 0x1900;
	int GL_STENCIL_INDEX = 0x1901;
	int GL_DEPTH_COMPONENT = 0x1902;
	int GL_RED = 0x1903;
	int GL_GREEN = 0x1904;
	int GL_BLUE = 0x1905;
	int GL_ALPHA = 0x1906;
	int GL_RGB = 0x1907;
	int GL_RGBA = 0x1908;
	int GL_LUMINANCE = 0x1909;
	int GL_LUMINANCE_ALPHA = 0x190A;

	/* PixelType */
	int GL_BITMAP = 0x1A00;
	/*      GL_BYTE */
	/*      GL_UNSIGNED_BYTE */
	/*      GL_SHORT */
	/*      GL_UNSIGNED_SHORT */
	/*      GL_INT */
	/*      GL_UNSIGNED_INT */
	/*      GL_FLOAT */

	/* PolygonMode */
	int GL_POINT = 0x1B00;
	int GL_LINE = 0x1B01;
	int GL_FILL = 0x1B02;

	/* RenderingMode */
	int GL_RENDER = 0x1C00;
	int GL_FEEDBACK = 0x1C01;
	int GL_SELECT = 0x1C02;

	/* ShadingModel */
	int GL_FLAT = 0x1D00;
	int GL_SMOOTH = 0x1D01;

	/* StencilOp */
	/*      GL_ZERO */
	int GL_KEEP = 0x1E00;
	int GL_REPLACE = 0x1E01;
	int GL_INCR = 0x1E02;
	int GL_DECR = 0x1E03;
	/*      GL_INVERT */

	/* StringName */
	int GL_VENDOR = 0x1F00;
	int GL_RENDERER = 0x1F01;
	int GL_VERSION = 0x1F02;
	int GL_EXTENSIONS = 0x1F03;

	/* TextureCoordName */
	int GL_S = 0x2000;
	int GL_T = 0x2001;
	int GL_R = 0x2002;
	int GL_Q = 0x2003;

	/* TexCoordPointerType */
	/*      GL_SHORT */
	/*      GL_INT */
	/*      GL_FLOAT */
	/*      GL_DOUBLE */

	/* TextureEnvMode */
	int GL_MODULATE = 0x2100;
	int GL_DECAL = 0x2101;
	/*      GL_BLEND */
	/*      GL_REPLACE */

	/* TextureEnvParameter */
	int GL_TEXTURE_ENV_MODE = 0x2200;
	int GL_TEXTURE_ENV_COLOR = 0x2201;

	/* TextureEnvTarget */
	int GL_TEXTURE_ENV = 0x2300;

	/* TextureGenMode */
	int GL_EYE_LINEAR = 0x2400;
	int GL_OBJECT_LINEAR = 0x2401;
	int GL_SPHERE_MAP = 0x2402;

	/* TextureGenParameter */
	int GL_TEXTURE_GEN_MODE = 0x2500;
	int GL_OBJECT_PLANE = 0x2501;
	int GL_EYE_PLANE = 0x2502;

	/* TextureMagFilter */
	int GL_NEAREST = 0x2600;
	int GL_LINEAR = 0x2601;

	/* TextureMinFilter */
	/*      GL_NEAREST */
	/*      GL_LINEAR */
	int GL_NEAREST_MIPMAP_NEAREST = 0x2700;
	int GL_LINEAR_MIPMAP_NEAREST = 0x2701;
	int GL_NEAREST_MIPMAP_LINEAR = 0x2702;
	int GL_LINEAR_MIPMAP_LINEAR = 0x2703;

	/* TextureParameterName */
	int GL_TEXTURE_MAG_FILTER = 0x2800;
	int GL_TEXTURE_MIN_FILTER = 0x2801;
	int GL_TEXTURE_WRAP_S = 0x2802;
	int GL_TEXTURE_WRAP_T = 0x2803;
	/*      GL_TEXTURE_BORDER_COLOR */
	/*      GL_TEXTURE_PRIORITY */

	/* TextureWrapMode */
	int GL_CLAMP = 0x2900;
	int GL_REPEAT = 0x2901;

	/* ClientAttribMask */
	int GL_CLIENT_PIXEL_STORE_BIT = 0x00000001;
	int GL_CLIENT_VERTEX_ARRAY_BIT = 0x00000002;
	int GL_ALL_CLIENT_ATTRIB_BITS = 0xffffffff;

	/* polygon_offset */
	int GL_POLYGON_OFFSET_FACTOR = 0x8038;
	int GL_POLYGON_OFFSET_UNITS = 0x2A00;
	int GL_POLYGON_OFFSET_POINT = 0x2A01;
	int GL_POLYGON_OFFSET_LINE = 0x2A02;
	int GL_POLYGON_OFFSET_FILL = 0x8037;

	/* texture */
	int GL_ALPHA4 = 0x803B;
	int GL_ALPHA8 = 0x803C;
	int GL_ALPHA12 = 0x803D;
	int GL_ALPHA16 = 0x803E;
	int GL_LUMINANCE4 = 0x803F;
	int GL_LUMINANCE8 = 0x8040;
	int GL_LUMINANCE12 = 0x8041;
	int GL_LUMINANCE16 = 0x8042;
	int GL_LUMINANCE4_ALPHA4 = 0x8043;
	int GL_LUMINANCE6_ALPHA2 = 0x8044;
	int GL_LUMINANCE8_ALPHA8 = 0x8045;
	int GL_LUMINANCE12_ALPHA4 = 0x8046;
	int GL_LUMINANCE12_ALPHA12 = 0x8047;
	int GL_LUMINANCE16_ALPHA16 = 0x8048;
	int GL_INTENSITY = 0x8049;
	int GL_INTENSITY4 = 0x804A;
	int GL_INTENSITY8 = 0x804B;
	int GL_INTENSITY12 = 0x804C;
	int GL_INTENSITY16 = 0x804D;
	int GL_R3_G3_B2 = 0x2A10;
	int GL_RGB4 = 0x804F;
	int GL_RGB5 = 0x8050;
	int GL_RGB8 = 0x8051;
	int GL_RGB10 = 0x8052;
	int GL_RGB12 = 0x8053;
	int GL_RGB16 = 0x8054;
	int GL_RGBA2 = 0x8055;
	int GL_RGBA4 = 0x8056;
	int GL_RGB5_A1 = 0x8057;
	int GL_RGBA8 = 0x8058;
	int GL_RGB10_A2 = 0x8059;
	int GL_RGBA12 = 0x805A;
	int GL_RGBA16 = 0x805B;
	int GL_TEXTURE_RED_SIZE = 0x805C;
	int GL_TEXTURE_GREEN_SIZE = 0x805D;
	int GL_TEXTURE_BLUE_SIZE = 0x805E;
	int GL_TEXTURE_ALPHA_SIZE = 0x805F;
	int GL_TEXTURE_LUMINANCE_SIZE = 0x8060;
	int GL_TEXTURE_INTENSITY_SIZE = 0x8061;
	int GL_PROXY_TEXTURE_1D = 0x8063;
	int GL_PROXY_TEXTURE_2D = 0x8064;

	/* texture_object */
	int GL_TEXTURE_PRIORITY = 0x8066;
	int GL_TEXTURE_RESIDENT = 0x8067;
	int GL_TEXTURE_BINDING_1D = 0x8068;
	int GL_TEXTURE_BINDING_2D = 0x8069;

	/* vertex_array */
	int GL_VERTEX_ARRAY = 0x8074;
	int GL_NORMAL_ARRAY = 0x8075;
	int GL_COLOR_ARRAY = 0x8076;
	int GL_INDEX_ARRAY = 0x8077;
	int GL_TEXTURE_COORD_ARRAY = 0x8078;
	int GL_EDGE_FLAG_ARRAY = 0x8079;
	int GL_VERTEX_ARRAY_SIZE = 0x807A;
	int GL_VERTEX_ARRAY_TYPE = 0x807B;
	int GL_VERTEX_ARRAY_STRIDE = 0x807C;
	int GL_NORMAL_ARRAY_TYPE = 0x807E;
	int GL_NORMAL_ARRAY_STRIDE = 0x807F;
	int GL_COLOR_ARRAY_SIZE = 0x8081;
	int GL_COLOR_ARRAY_TYPE = 0x8082;
	int GL_COLOR_ARRAY_STRIDE = 0x8083;
	int GL_INDEX_ARRAY_TYPE = 0x8085;
	int GL_INDEX_ARRAY_STRIDE = 0x8086;
	int GL_TEXTURE_COORD_ARRAY_SIZE = 0x8088;
	int GL_TEXTURE_COORD_ARRAY_TYPE = 0x8089;
	int GL_TEXTURE_COORD_ARRAY_STRIDE = 0x808A;
	int GL_EDGE_FLAG_ARRAY_STRIDE = 0x808C;
	int GL_VERTEX_ARRAY_POINTER = 0x808E;
	int GL_NORMAL_ARRAY_POINTER = 0x808F;
	int GL_COLOR_ARRAY_POINTER = 0x8090;
	int GL_INDEX_ARRAY_POINTER = 0x8091;
	int GL_TEXTURE_COORD_ARRAY_POINTER = 0x8092;
	int GL_EDGE_FLAG_ARRAY_POINTER = 0x8093;
	int GL_V2F = 0x2A20;
	int GL_V3F = 0x2A21;
	int GL_C4UB_V2F = 0x2A22;
	int GL_C4UB_V3F = 0x2A23;
	int GL_C3F_V3F = 0x2A24;
	int GL_N3F_V3F = 0x2A25;
	int GL_C4F_N3F_V3F = 0x2A26;
	int GL_T2F_V3F = 0x2A27;
	int GL_T4F_V4F = 0x2A28;
	int GL_T2F_C4UB_V3F = 0x2A29;
	int GL_T2F_C3F_V3F = 0x2A2A;
	int GL_T2F_N3F_V3F = 0x2A2B;
	int GL_T2F_C4F_N3F_V3F = 0x2A2C;
	int GL_T4F_C4F_N3F_V4F = 0x2A2D;

	/* For compatibility with OpenGL v1.0 */
	int GL_LOGIC_OP = GL_INDEX_LOGIC_OP;
	int GL_TEXTURE_COMPONENTS = GL_TEXTURE_INTERNAL_FORMAT;

	@DeprecatedGL
	void glAccum(@GLenum int op, float value);

	@DeprecatedGL
	void glAlphaFunc(@GLenum int func, float ref);

	void glClearColor(float red, float green, float blue, float alpha);

	@DeprecatedGL
	void glClearAccum(float red, float green, float blue, float alpha);

	void glClear(@GLbitfield int mask);

	@DeprecatedGL
	void glCallLists(@AutoSize("lists") @GLsizei int n, @AutoType("lists") @GLenum int type,
	                 @Const
	                 @GLubyte
	                 @GLushort
	                 @GLuint Buffer lists);

	@DeprecatedGL
	void glCallList(@GLuint int list);

	void glBlendFunc(@GLenum int sfactor, @GLenum int dfactor);

	@DeprecatedGL
	void glBitmap(@GLsizei int width, @GLsizei int height, float xorig, float yorig, float xmove, float ymove,
	              @BufferObject(BufferKind.UnpackPBO)
	              @Check(value = "(((width + 7)/8)*height)", canBeNull = true)
	              @Const
	              @GLubyte ByteBuffer bitmap);

	void glBindTexture(@GLenum int target, @GLuint int texture);

	@DeprecatedGL
	void glPrioritizeTextures(@AutoSize("textures") @GLsizei int n,
	                          @Const
	                          @GLuint IntBuffer textures,
	                          @Const
	                          @Check("textures.remaining()")
	                          FloatBuffer priorities);

	@DeprecatedGL
	boolean glAreTexturesResident(@AutoSize("textures") @GLsizei int n,
	                              @Const
	                              @GLuint IntBuffer textures,
	                              @Check("textures.remaining()")
	                              @GLboolean ByteBuffer residences);

	@NoErrorCheck
	@DeprecatedGL
	@Code("\t\tif ( ContextCapabilities.DEBUG ) StateTracker.setBeginEnd(caps, true);")
	void glBegin(@GLenum int mode);

	@DeprecatedGL
	@Code("\t\tif ( ContextCapabilities.DEBUG ) StateTracker.setBeginEnd(caps, false);")
	void glEnd();

	@NoErrorCheck
	void glArrayElement(int i);

	void glClearDepth(double depth);

	@DeprecatedGL
	void glDeleteLists(@GLuint int list, @GLsizei int range);

	void glDeleteTextures(@AutoSize("textures") @GLsizei int n, @Const @GLuint IntBuffer textures);

	@Alternate("glDeleteTextures")
	void glDeleteTextures(@Constant("1") @GLsizei int n, @Constant(value = "APIUtil.getInt(caps, texture)", keepParam = true) int texture);

	void glCullFace(@GLenum int mode);

	void glCopyTexSubImage2D(@GLenum int target, int level, int xoffset, int yoffset, int x, int y, @GLsizei int width, @GLsizei int height);

	void glCopyTexSubImage1D(@GLenum int target, int level, int xoffset, int x, int y, @GLsizei int width);

	void glCopyTexImage2D(@GLenum int target, int level, int internalFormat, int x, int y, @GLsizei int width, @GLsizei int height, int border);

	void glCopyTexImage1D(@GLenum int target, int level, int internalFormat, int x, int y, @GLsizei int width, int border);

	void glCopyPixels(int x, int y, int width, int height, int type);

	@DeprecatedGL
	void glColorPointer(int size, @AutoType("pointer") @GLenum int type, @GLsizei int stride,
	                    @CachedReference
	                    @Check
	                    @BufferObject(BufferKind.ArrayVBO)
	                    @Const
	                    @GLfloat
	                    @GLdouble
	                    @GLubyte
	                    @GLbyte Buffer pointer);

	@DeprecatedGL
	@Alternate("glColorPointer")
	void glColorPointer(int size, @GLenum int type, @GLsizei int stride,
	                    @CachedReference
	                    @Check
	                    @BufferObject(BufferKind.ArrayVBO)
	                    @Const ByteBuffer pointer);

	@DeprecatedGL
	void glColorMaterial(@GLenum int face, @GLenum int mode);

	void glColorMask(boolean red, boolean green, boolean blue, boolean alpha);

	@NoErrorCheck
	@DeprecatedGL
	void glColor3b(byte red, byte green, byte blue);

	@NoErrorCheck
	@DeprecatedGL
	void glColor3f(float red, float green, float blue);

	@NoErrorCheck
	@DeprecatedGL
	void glColor3d(double red, double green, double blue);

	@NoErrorCheck
	@DeprecatedGL
	void glColor3ub(@GLubyte byte red, @GLubyte byte green, @GLubyte byte blue);

	@NoErrorCheck
	@DeprecatedGL
	void glColor4b(byte red, byte green, byte blue, byte alpha);

	@NoErrorCheck
	@DeprecatedGL
	void glColor4f(float red, float green, float blue, float alpha);

	@NoErrorCheck
	@DeprecatedGL
	void glColor4d(double red, double green, double blue, double alpha);

	@NoErrorCheck
	@DeprecatedGL
	void glColor4ub(@GLubyte byte red, @GLubyte byte green, @GLubyte byte blue, @GLubyte byte alpha);

	void glClipPlane(@GLenum int plane, @Check("4") @Const DoubleBuffer equation);

	void glClearStencil(int s);

	// This function is only used in indexed color mode
	//	void glClearIndex(float c);

	@DeprecatedGL
	void glEvalPoint1(int i);

	@DeprecatedGL
	void glEvalPoint2(int i, int j);

	@DeprecatedGL
	void glEvalMesh1(@GLenum int mode, int i1, int i2);

	@DeprecatedGL
	void glEvalMesh2(@GLenum int mode, int i1, int i2, int j1, int j2);

	@DeprecatedGL
	void glEvalCoord1f(float u);

	@DeprecatedGL
	void glEvalCoord1d(double u);

	@DeprecatedGL
	void glEvalCoord2f(float u, float v);

	@DeprecatedGL
	void glEvalCoord2d(double u, double v);

	@DeprecatedGL
	void glEnableClientState(@GLenum int cap);

	@DeprecatedGL
	void glDisableClientState(@GLenum int cap);

	void glEnable(@GLenum int cap);

	void glDisable(@GLenum int cap);

	@DeprecatedGL
	void glEdgeFlagPointer(int stride,
	                       @CachedReference
	                       @BufferObject(BufferKind.ArrayVBO)
	                       @Check
	                       @Const
	                       @GLbyte Buffer pointer);

	@DeprecatedGL
	void glEdgeFlag(boolean flag);

	@DeprecatedGL
	void glDrawPixels(@GLsizei int width, @GLsizei int height, @GLenum int format, @GLenum int type,
	                  @Check("GLChecks.calculateImageStorage(pixels, format, type, width, height, 1)")
	                  @BufferObject(BufferKind.UnpackPBO)
	                  @Const
	                  @GLbyte
	                  @GLshort
	                  @GLint Buffer pixels);

	void glDrawElements(@GLenum int mode, @AutoSize("indices") @GLsizei int count, @AutoType("indices") @GLenum int type,
	                    @BufferObject(BufferKind.ElementVBO)
	                    @Const
	                    @GLubyte
	                    @GLushort
	                    @GLuint Buffer indices);

	@Alternate("glDrawElements")
	void glDrawElements(@GLenum int mode, @GLsizei int count, @GLenum int type, @BufferObject(BufferKind.ElementVBO) @Const @Check("count") ByteBuffer indices);

	void glDrawBuffer(@GLenum int mode);

	void glDrawArrays(@GLenum int mode, int first, @GLsizei int count);

	void glDepthRange(double zNear, double zFar);

	void glDepthMask(boolean flag);

	void glDepthFunc(@GLenum int func);

	@DeprecatedGL
	void glFeedbackBuffer(@AutoSize("buffer") @GLsizei int size, @GLenum int type, FloatBuffer buffer);

	@StripPostfix("values")
	@DeprecatedGL
	void glGetPixelMapfv(@GLenum int map, @OutParameter @Check("256") @BufferObject(BufferKind.PackPBO) FloatBuffer values);

	@StripPostfix("values")
	@DeprecatedGL
	void glGetPixelMapuiv(@GLenum int map, @OutParameter @Check("256") @BufferObject(BufferKind.PackPBO) @GLuint IntBuffer values);

	@StripPostfix("values")
	@DeprecatedGL
	void glGetPixelMapusv(@GLenum int map, @OutParameter @Check("256") @BufferObject(BufferKind.PackPBO) @GLushort ShortBuffer values);

	@StripPostfix("params")
	@DeprecatedGL
	void glGetMaterialfv(@GLenum int face, @GLenum int pname, @OutParameter @Check("4") FloatBuffer params);

	@StripPostfix("params")
	@DeprecatedGL
	void glGetMaterialiv(@GLenum int face, @GLenum int pname, @OutParameter @Check("4") IntBuffer params);

	@StripPostfix("v")
	@DeprecatedGL
	void glGetMapfv(@GLenum int target, @GLenum int query, @OutParameter @Check("256") FloatBuffer v);

	@StripPostfix("v")
	@DeprecatedGL
	void glGetMapdv(@GLenum int target, @GLenum int query, @OutParameter @Check("256") DoubleBuffer v);

	@StripPostfix("v")
	@DeprecatedGL
	void glGetMapiv(@GLenum int target, @GLenum int query, @OutParameter @Check("256") IntBuffer v);

	@StripPostfix("params")
	@DeprecatedGL
	void glGetLightfv(@GLenum int light, @GLenum int pname, @OutParameter @Check("4") FloatBuffer params);

	@StripPostfix("params")
	@DeprecatedGL
	void glGetLightiv(@GLenum int light, @GLenum int pname, @OutParameter @Check("4") IntBuffer params);

	@NoErrorCheck
	int glGetError();

	void glGetClipPlane(@GLenum int plane, @OutParameter @Check("4") DoubleBuffer equation);

	@StripPostfix("params")
	void glGetBooleanv(@GLenum int pname, @OutParameter @Check("16") @GLboolean ByteBuffer params);

	@Alternate("glGetBooleanv")
	@GLreturn("params")
	@StripPostfix("params")
	void glGetBooleanv2(@GLenum int pname, @OutParameter @GLboolean ByteBuffer params);

	@StripPostfix("params")
	void glGetDoublev(@GLenum int pname, @OutParameter @Check("16") DoubleBuffer params);

	@Alternate("glGetDoublev")
	@GLreturn("params")
	@StripPostfix("params")
	void glGetDoublev2(@GLenum int pname, @OutParameter DoubleBuffer params);

	@StripPostfix("params")
	void glGetFloatv(@GLenum int pname, @OutParameter @Check("16") FloatBuffer params);

	@Alternate("glGetFloatv")
	@GLreturn("params")
	@StripPostfix("params")
	void glGetFloatv2(@GLenum int pname, @OutParameter FloatBuffer params);

	@StripPostfix("params")
	void glGetIntegerv(@GLenum int pname, @OutParameter @Check("16") IntBuffer params);

	@Alternate("glGetIntegerv")
	@GLreturn("params")
	@StripPostfix("params")
	void glGetIntegerv2(@GLenum int pname, @OutParameter IntBuffer params);

	void glGenTextures(@AutoSize("textures") @GLsizei int n, @OutParameter @GLuint IntBuffer textures);

	@Alternate("glGenTextures")
	@GLreturn("textures")
	void glGenTextures2(@Constant("1") @GLsizei int n, @OutParameter @GLuint IntBuffer textures);

	@GLuint
	@DeprecatedGL
	int glGenLists(@GLsizei int range);

	@DeprecatedGL
	void glFrustum(double left, double right, double bottom, double top, double zNear, double zFar);

	void glFrontFace(@GLenum int mode);

	@DeprecatedGL
	void glFogf(@GLenum int pname, float param);

	@DeprecatedGL
	void glFogi(@GLenum int pname, int param);

	@StripPostfix("params")
	@DeprecatedGL
	void glFogfv(@GLenum int pname, @Check("4") @Const FloatBuffer params);

	@StripPostfix("params")
	@DeprecatedGL
	void glFogiv(@GLenum int pname, @Check("4") @Const IntBuffer params);

	void glFlush();

	void glFinish();

	@StripPostfix("result")
	void glGetPointerv(@GLenum int pname, @Result @GLvoid ByteBuffer result);

	boolean glIsEnabled(@GLenum int cap);

	void glInterleavedArrays(@GLenum int format, @GLsizei int stride,
	                         @BufferObject(BufferKind.ArrayVBO)
	                         @Check
	                         @Const
	                         @GLbyte
	                         @GLshort
	                         @GLint
	                         @GLfloat
	                         @GLdouble Buffer pointer);

	@DeprecatedGL
	void glInitNames();

	void glHint(@GLenum int target, @GLenum int mode);

	@StripPostfix("params")
	void glGetTexParameterfv(@GLenum int target, @GLenum int pname, @OutParameter @Check("4") FloatBuffer params);

	@Alternate("glGetTexParameterfv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetTexParameterfv2(@GLenum int target, @GLenum int pname, @OutParameter FloatBuffer params);

	@StripPostfix("params")
	void glGetTexParameteriv(@GLenum int target, @GLenum int pname, @OutParameter @Check("4") IntBuffer params);

	@Alternate("glGetTexParameteriv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetTexParameteriv2(@GLenum int target, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetTexLevelParameterfv(@GLenum int target, int level, @GLenum int pname, @OutParameter @Check("4") FloatBuffer params);

	@Alternate("glGetTexLevelParameterfv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetTexLevelParameterfv2(@GLenum int target, int level, @GLenum int pname, @OutParameter FloatBuffer params);

	@StripPostfix("params")
	void glGetTexLevelParameteriv(@GLenum int target, int level, @GLenum int pname, @OutParameter @Check("4") IntBuffer params);

	@Alternate("glGetTexLevelParameteriv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetTexLevelParameteriv2(@GLenum int target, int level, @GLenum int pname, @OutParameter IntBuffer params);

	void glGetTexImage(@GLenum int target, int level, @GLenum int format, @GLenum int type,
	                   @OutParameter
	                   @BufferObject(BufferKind.PackPBO)
	                   @Check("GLChecks.calculateImageStorage(pixels, format, type, 1, 1, 1)")
	                   @GLbyte
	                   @GLshort
	                   @GLint
	                   @GLfloat
	                   @GLdouble Buffer pixels);

	@StripPostfix("params")
	@DeprecatedGL
	void glGetTexGeniv(@GLenum int coord, @GLenum int pname, @OutParameter @Check("4") IntBuffer params);

	@Alternate("glGetTexGeniv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	@DeprecatedGL
	void glGetTexGeniv2(@GLenum int coord, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	@DeprecatedGL
	void glGetTexGenfv(@GLenum int coord, @GLenum int pname, @OutParameter @Check("4") FloatBuffer params);

	@Alternate("glGetTexGenfv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	@DeprecatedGL
	void glGetTexGenfv2(@GLenum int coord, @GLenum int pname, @OutParameter FloatBuffer params);

	@StripPostfix("params")
	@DeprecatedGL
	void glGetTexGendv(@GLenum int coord, @GLenum int pname, @OutParameter @Check("4") DoubleBuffer params);

	@Alternate("glGetTexGendv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	@DeprecatedGL
	void glGetTexGendv2(@GLenum int coord, @GLenum int pname, @OutParameter DoubleBuffer params);

	@StripPostfix("params")
	void glGetTexEnviv(@GLenum int coord, @GLenum int pname, @OutParameter @Check("4") IntBuffer params);

	@Alternate("glGetTexEnviv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetTexEnviv2(@GLenum int coord, @GLenum int pname, @OutParameter IntBuffer params);

	@StripPostfix("params")
	void glGetTexEnvfv(@GLenum int coord, @GLenum int pname, @OutParameter @Check("4") FloatBuffer params);

	@Alternate("glGetTexEnvfv")
	@GLreturn("params")
	@StripPostfix(value = "params", postfix = "v")
	void glGetTexEnvfv2(@GLenum int coord, @GLenum int pname, @OutParameter FloatBuffer params);

	@Const
	String glGetString(int name);

	@DeprecatedGL
	void glGetPolygonStipple(@OutParameter @BufferObject(BufferKind.PackPBO) @Check("128") @GLubyte ByteBuffer mask);

	@DeprecatedGL
	boolean glIsList(@GLuint int list);

	@DeprecatedGL
	void glMaterialf(@GLenum int face, @GLenum int pname, float param);

	@DeprecatedGL
	void glMateriali(@GLenum int face, @GLenum int pname, int param);

	@StripPostfix("params")
	@DeprecatedGL
	void glMaterialfv(@GLenum int face, @GLenum int pname, @Check("4") @Const FloatBuffer params);

	@StripPostfix("params")
	@DeprecatedGL
	void glMaterialiv(@GLenum int face, @GLenum int pname, @Check("4") @Const IntBuffer params);

	@DeprecatedGL
	void glMapGrid1f(int un, float u1, float u2);

	@DeprecatedGL
	void glMapGrid1d(int un, double u1, double u2);

	@DeprecatedGL
	void glMapGrid2f(int un, float u1, float u2, int vn, float v1, float v2);

	@DeprecatedGL
	void glMapGrid2d(int un, double u1, double u2, int vn, double v1, double v2);

	// TODO: check buffer size valid

	@DeprecatedGL
	void glMap2f(@GLenum int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, @Check @Const FloatBuffer points);

	@DeprecatedGL
	void glMap2d(@GLenum int target, double u1, double u2, int ustride, int uorder, double v1, double v2, int vstride, int vorder, @Check @Const DoubleBuffer points);

	// TODO: check buffer size valid

	@DeprecatedGL
	void glMap1f(@GLenum int target, float u1, float u2, int stride, int order, @Check @Const FloatBuffer points);

	@DeprecatedGL
	void glMap1d(@GLenum int target, double u1, double u2, int stride, int order, @Check @Const DoubleBuffer points);

	void glLogicOp(@GLenum int opcode);

	@DeprecatedGL
	void glLoadName(@GLuint int name);

	@StripPostfix("m")
	@DeprecatedGL
	void glLoadMatrixf(@Check("16") @Const FloatBuffer m);

	@StripPostfix("m")
	@DeprecatedGL
	void glLoadMatrixd(@Check("16") @Const DoubleBuffer m);

	@DeprecatedGL
	void glLoadIdentity();

	@DeprecatedGL
	void glListBase(@GLuint int base);

	void glLineWidth(float width);

	@DeprecatedGL
	void glLineStipple(int factor, @GLushort short pattern);

	@DeprecatedGL
	void glLightModelf(@GLenum int pname, float param);

	@DeprecatedGL
	void glLightModeli(@GLenum int pname, int param);

	@StripPostfix("params")
	@DeprecatedGL
	void glLightModelfv(@GLenum int pname, @Check("4") @Const FloatBuffer params);

	@StripPostfix("params")
	@DeprecatedGL
	void glLightModeliv(@GLenum int pname, @Check("4") @Const IntBuffer params);

	@DeprecatedGL
	void glLightf(@GLenum int light, @GLenum int pname, float param);

	@DeprecatedGL
	void glLighti(@GLenum int light, @GLenum int pname, int param);

	@StripPostfix("params")
	@DeprecatedGL
	void glLightfv(@GLenum int light, @GLenum int pname, @Check("4") @Const FloatBuffer params);

	@StripPostfix("params")
	@DeprecatedGL
	void glLightiv(@GLenum int light, @GLenum int pname, @Check("4") @Const IntBuffer params);

	boolean glIsTexture(@GLuint int texture);

	@DeprecatedGL
	void glMatrixMode(@GLenum int mode);

	@DeprecatedGL
	void glPolygonStipple(@BufferObject(BufferKind.UnpackPBO) @Check("128") @Const @GLubyte ByteBuffer mask);

	void glPolygonOffset(float factor, float units);

	void glPolygonMode(@GLenum int face, @GLenum int mode);

	void glPointSize(float size);

	@DeprecatedGL
	void glPixelZoom(float xfactor, float yfactor);

	@DeprecatedGL
	void glPixelTransferf(@GLenum int pname, float param);

	@DeprecatedGL
	void glPixelTransferi(@GLenum int pname, int param);

	void glPixelStoref(@GLenum int pname, float param);

	void glPixelStorei(@GLenum int pname, int param);

	@StripPostfix("values")
	@DeprecatedGL
	void glPixelMapfv(@GLenum int map, @AutoSize("values") @GLsizei int mapsize, @BufferObject(BufferKind.UnpackPBO) @Const FloatBuffer values);

	@StripPostfix("values")
	@DeprecatedGL
	void glPixelMapuiv(@GLenum int map, @AutoSize("values") @GLsizei int mapsize, @BufferObject(BufferKind.UnpackPBO) @Const @GLuint IntBuffer values);

	@StripPostfix("values")
	@DeprecatedGL
	void glPixelMapusv(@GLenum int map, @AutoSize("values") @GLsizei int mapsize, @BufferObject(BufferKind.UnpackPBO) @Const @GLushort ShortBuffer values);

	@DeprecatedGL
	void glPassThrough(float token);

	@DeprecatedGL
	void glOrtho(double left, double right, double bottom, double top, double zNear, double zFar);

	@DeprecatedGL
	void glNormalPointer(@AutoType("pointer") @GLenum int type, @GLsizei int stride,
	                     @CachedReference
	                     @BufferObject(BufferKind.ArrayVBO)
	                     @Check
	                     @Const
	                     @GLint
	                     @GLbyte
	                     @GLfloat
	                     @GLdouble Buffer pointer);

	@DeprecatedGL
	@Alternate("glNormalPointer")
	void glNormalPointer(@GLenum int type, @GLsizei int stride,
	                     @CachedReference
	                     @BufferObject(BufferKind.ArrayVBO)
	                     @Check
	                     @Const ByteBuffer pointer);

	@NoErrorCheck
	@DeprecatedGL
	void glNormal3b(byte nx, byte ny, byte nz);

	@NoErrorCheck
	@DeprecatedGL
	void glNormal3f(float nx, float ny, float nz);

	@NoErrorCheck
	@DeprecatedGL
	void glNormal3d(double nx, double ny, double nz);

	@NoErrorCheck
	@DeprecatedGL
	void glNormal3i(int nx, int ny, int nz);

	@DeprecatedGL
	void glNewList(@GLuint int list, @GLenum int mode);

	@DeprecatedGL
	void glEndList();

	@StripPostfix("m")
	@DeprecatedGL
	void glMultMatrixf(@Check("16") @Const FloatBuffer m);

	@StripPostfix("m")
	@DeprecatedGL
	void glMultMatrixd(@Check("16") @Const DoubleBuffer m);

	void glShadeModel(@GLenum int mode);

	@DeprecatedGL
	void glSelectBuffer(@AutoSize("buffer") @GLsizei int size, @GLuint IntBuffer buffer);

	void glScissor(int x, int y, @GLsizei int width, @GLsizei int height);

	@DeprecatedGL
	void glScalef(float x, float y, float z);

	@DeprecatedGL
	void glScaled(double x, double y, double z);

	@DeprecatedGL
	void glRotatef(float angle, float x, float y, float z);

	@DeprecatedGL
	void glRotated(double angle, double x, double y, double z);

	@DeprecatedGL
	int glRenderMode(@GLenum int mode);

	@DeprecatedGL
	void glRectf(float x1, float y1, float x2, float y2);

	@DeprecatedGL
	void glRectd(double x1, double y1, double x2, double y2);

	@DeprecatedGL
	void glRecti(int x1, int y1, int x2, int y2);

	void glReadPixels(int x, int y, @GLsizei int width, @GLsizei int height, @GLenum int format, @GLenum int type,
	                  @OutParameter
	                  @BufferObject(BufferKind.PackPBO)
	                  @Check("GLChecks.calculateImageStorage(pixels, format, type, width, height, 1)")
	                  @GLbyte
	                  @GLshort
	                  @GLint
	                  @GLfloat
	                  @GLdouble Buffer pixels);

	void glReadBuffer(@GLenum int mode);

	@DeprecatedGL
	void glRasterPos2f(float x, float y);

	@DeprecatedGL
	void glRasterPos2d(double x, double y);

	@DeprecatedGL
	void glRasterPos2i(int x, int y);

	@DeprecatedGL
	void glRasterPos3f(float x, float y, float z);

	@DeprecatedGL
	void glRasterPos3d(double x, double y, double z);

	@DeprecatedGL
	void glRasterPos3i(int x, int y, int z);

	@DeprecatedGL
	void glRasterPos4f(float x, float y, float z, float w);

	@DeprecatedGL
	void glRasterPos4d(double x, double y, double z, double w);

	@DeprecatedGL
	void glRasterPos4i(int x, int y, int z, int w);

	@DeprecatedGL
	void glPushName(@GLuint int name);

	@DeprecatedGL
	void glPopName();

	@DeprecatedGL
	void glPushMatrix();

	@DeprecatedGL
	void glPopMatrix();

	@Code("		StateTracker.pushAttrib(caps, mask);")
	@DeprecatedGL
	void glPushClientAttrib(@GLbitfield int mask);

	@Code("		StateTracker.popAttrib(caps);")
	@DeprecatedGL
	void glPopClientAttrib();

	@DeprecatedGL
	void glPushAttrib(@GLbitfield int mask);

	@DeprecatedGL
	void glPopAttrib();

	void glStencilFunc(@GLenum int func, int ref, @GLuint int mask);

	@DeprecatedGL
	void glVertexPointer(int size, @AutoType("pointer") @GLenum int type, @GLsizei int stride,
	                     @CachedReference
	                     @BufferObject(BufferKind.ArrayVBO)
	                     @Check
	                     @Const
	                     @GLshort
	                     @GLint
	                     @GLfloat
	                     @GLdouble Buffer pointer);

	@DeprecatedGL
	@Alternate("glVertexPointer")
	void glVertexPointer(int size, @GLenum int type, @GLsizei int stride,
	                     @CachedReference
	                     @BufferObject(BufferKind.ArrayVBO)
	                     @Check
	                     @Const ByteBuffer pointer);

	@NoErrorCheck
	@DeprecatedGL
	void glVertex2f(float x, float y);

	@NoErrorCheck
	@DeprecatedGL
	void glVertex2d(double x, double y);

	@NoErrorCheck
	@DeprecatedGL
	void glVertex2i(int x, int y);

	@NoErrorCheck
	@DeprecatedGL
	void glVertex3f(float x, float y, float z);

	@NoErrorCheck
	@DeprecatedGL
	void glVertex3d(double x, double y, double z);

	@NoErrorCheck
	@DeprecatedGL
	void glVertex3i(int x, int y, int z);

	@NoErrorCheck
	@DeprecatedGL
	void glVertex4f(float x, float y, float z, float w);

	@NoErrorCheck
	@DeprecatedGL
	void glVertex4d(double x, double y, double z, double w);

	@NoErrorCheck
	@DeprecatedGL
	void glVertex4i(int x, int y, int z, int w);

	@DeprecatedGL
	void glTranslatef(float x, float y, float z);

	@DeprecatedGL
	void glTranslated(double x, double y, double z);

	void glTexImage1D(@GLenum int target, int level, int internalformat, @GLsizei int width, int border, @GLenum int format, @GLenum int type,
	                  @BufferObject(BufferKind.UnpackPBO)
	                  @Check(value = "GLChecks.calculateTexImage1DStorage(pixels, format, type, width)", canBeNull = true)
	                  @Const
	                  @GLbyte
	                  @GLshort
	                  @GLint
	                  @GLfloat
	                  @GLdouble Buffer pixels);

	void glTexImage2D(@GLenum int target, int level, int internalformat, int width, int height, int border, @GLenum int format, @GLenum int type,
	                  @BufferObject(BufferKind.UnpackPBO)
	                  @Check(value = "GLChecks.calculateTexImage2DStorage(pixels, format, type, width, height)", canBeNull = true)
	                  @Const
	                  @GLbyte
	                  @GLshort
	                  @GLint
	                  @GLfloat
	                  @GLdouble Buffer pixels);

	void glTexSubImage1D(@GLenum int target, int level, int xoffset, @GLsizei int width, @GLenum int format, @GLenum int type,
	                     @BufferObject(BufferKind.UnpackPBO)
	                     @Check("GLChecks.calculateImageStorage(pixels, format, type, width, 1, 1)")
	                     @Const
	                     @GLbyte
	                     @GLshort
	                     @GLint
	                     @GLfloat
	                     @GLdouble Buffer pixels);

	void glTexSubImage2D(@GLenum int target, int level, int xoffset, int yoffset, @GLsizei int width, @GLsizei int height, @GLenum int format, @GLenum int type,
	                     @BufferObject(BufferKind.UnpackPBO)
	                     @Check("GLChecks.calculateImageStorage(pixels, format, type, width, height, 1)")
	                     @Const
	                     @GLbyte
	                     @GLshort
	                     @GLint
	                     @GLfloat
	                     @GLdouble Buffer pixels);

	void glTexParameterf(@GLenum int target, @GLenum int pname, float param);

	void glTexParameteri(@GLenum int target, @GLenum int pname, int param);

	@StripPostfix("param")
	void glTexParameterfv(@GLenum int target, @GLenum int pname, @Check("4") @Const FloatBuffer param);

	@StripPostfix("param")
	void glTexParameteriv(@GLenum int target, @GLenum int pname, @Check("4") @Const IntBuffer param);

	@DeprecatedGL
	void glTexGenf(@GLenum int coord, @GLenum int pname, float param);

	@DeprecatedGL
	void glTexGend(@GLenum int coord, @GLenum int pname, double param);

	@StripPostfix("params")
	@DeprecatedGL
	void glTexGenfv(@GLenum int coord, @GLenum int pname, @Check("4") @Const FloatBuffer params);

	@StripPostfix("params")
	@DeprecatedGL
	void glTexGendv(@GLenum int coord, @GLenum int pname, @Check("4") @Const DoubleBuffer params);

	@DeprecatedGL
	void glTexGeni(@GLenum int coord, @GLenum int pname, int param);

	@StripPostfix("params")
	@DeprecatedGL
	void glTexGeniv(@GLenum int coord, @GLenum int pname, @Check("4") @Const IntBuffer params);

	void glTexEnvf(@GLenum int target, @GLenum int pname, float param);

	void glTexEnvi(@GLenum int target, @GLenum int pname, int param);

	@StripPostfix("params")
	void glTexEnvfv(@GLenum int target, @GLenum int pname, @Check("4") @Const FloatBuffer params);

	@StripPostfix("params")
	void glTexEnviv(@GLenum int target, @GLenum int pname, @Check("4") @Const IntBuffer params);

	@DeprecatedGL
	void glTexCoordPointer(int size, @AutoType("pointer") @GLenum int type, @GLsizei int stride,
	                       @CachedReference(index = "StateTracker.getReferences(caps).glClientActiveTexture", name = "glTexCoordPointer_buffer")
	                       @BufferObject(BufferKind.ArrayVBO)
	                       @Check
	                       @Const
	                       @GLint
	                       @GLshort
	                       @GLfloat
	                       @GLdouble Buffer pointer);

	@DeprecatedGL
	@Alternate("glTexCoordPointer")
	void glTexCoordPointer(int size, @GLenum int type, @GLsizei int stride,
	                       @CachedReference(index = "StateTracker.getReferences(caps).glClientActiveTexture", name = "glTexCoordPointer_buffer")
	                       @BufferObject(BufferKind.ArrayVBO)
	                       @Check
	                       @Const ByteBuffer pointer);

	@NoErrorCheck
	@DeprecatedGL
	void glTexCoord1f(float s);

	@NoErrorCheck
	@DeprecatedGL
	void glTexCoord1d(double s);

	@NoErrorCheck
	@DeprecatedGL
	void glTexCoord2f(float s, float t);

	@NoErrorCheck
	@DeprecatedGL
	void glTexCoord2d(double s, double t);

	@NoErrorCheck
	@DeprecatedGL
	void glTexCoord3f(float s, float t, float r);

	@NoErrorCheck
	@DeprecatedGL
	void glTexCoord3d(double s, double t, double r);

	@NoErrorCheck
	@DeprecatedGL
	void glTexCoord4f(float s, float t, float r, float q);

	@NoErrorCheck
	@DeprecatedGL
	void glTexCoord4d(double s, double t, double r, double q);

	void glStencilOp(@GLenum int fail, @GLenum int zfail, @GLenum int zpass);

	void glStencilMask(@GLuint int mask);

	void glViewport(int x, int y, @GLsizei int width, @GLsizei int height);
}
