/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class GL11 {
	public static final int GL_TEXTURE_COMPONENTS = 0x1003;
	public static final int GL_LOGIC_OP = 0xbf1;
	public static final int GL_T4F_C4F_N3F_V4F = 0x2a2d;
	public static final int GL_T2F_C4F_N3F_V3F = 0x2a2c;
	public static final int GL_T2F_N3F_V3F = 0x2a2b;
	public static final int GL_T2F_C3F_V3F = 0x2a2a;
	public static final int GL_T2F_C4UB_V3F = 0x2a29;
	public static final int GL_T4F_V4F = 0x2a28;
	public static final int GL_T2F_V3F = 0x2a27;
	public static final int GL_C4F_N3F_V3F = 0x2a26;
	public static final int GL_N3F_V3F = 0x2a25;
	public static final int GL_C3F_V3F = 0x2a24;
	public static final int GL_C4UB_V3F = 0x2a23;
	public static final int GL_C4UB_V2F = 0x2a22;
	public static final int GL_V3F = 0x2a21;
	public static final int GL_V2F = 0x2a20;
	public static final int GL_EDGE_FLAG_ARRAY_POINTER = 0x8093;
	public static final int GL_TEXTURE_COORD_ARRAY_POINTER = 0x8092;
	public static final int GL_INDEX_ARRAY_POINTER = 0x8091;
	public static final int GL_COLOR_ARRAY_POINTER = 0x8090;
	public static final int GL_NORMAL_ARRAY_POINTER = 0x808f;
	public static final int GL_VERTEX_ARRAY_POINTER = 0x808e;
	public static final int GL_EDGE_FLAG_ARRAY_STRIDE = 0x808c;
	public static final int GL_TEXTURE_COORD_ARRAY_STRIDE = 0x808a;
	public static final int GL_TEXTURE_COORD_ARRAY_TYPE = 0x8089;
	public static final int GL_TEXTURE_COORD_ARRAY_SIZE = 0x8088;
	public static final int GL_INDEX_ARRAY_STRIDE = 0x8086;
	public static final int GL_INDEX_ARRAY_TYPE = 0x8085;
	public static final int GL_COLOR_ARRAY_STRIDE = 0x8083;
	public static final int GL_COLOR_ARRAY_TYPE = 0x8082;
	public static final int GL_COLOR_ARRAY_SIZE = 0x8081;
	public static final int GL_NORMAL_ARRAY_STRIDE = 0x807f;
	public static final int GL_NORMAL_ARRAY_TYPE = 0x807e;
	public static final int GL_VERTEX_ARRAY_STRIDE = 0x807c;
	public static final int GL_VERTEX_ARRAY_TYPE = 0x807b;
	public static final int GL_VERTEX_ARRAY_SIZE = 0x807a;
	public static final int GL_EDGE_FLAG_ARRAY = 0x8079;
	public static final int GL_TEXTURE_COORD_ARRAY = 0x8078;
	public static final int GL_INDEX_ARRAY = 0x8077;
	public static final int GL_COLOR_ARRAY = 0x8076;
	public static final int GL_NORMAL_ARRAY = 0x8075;
	public static final int GL_VERTEX_ARRAY = 0x8074;
	public static final int GL_TEXTURE_BINDING_2D = 0x8069;
	public static final int GL_TEXTURE_BINDING_1D = 0x8068;
	public static final int GL_TEXTURE_RESIDENT = 0x8067;
	public static final int GL_TEXTURE_PRIORITY = 0x8066;
	public static final int GL_PROXY_TEXTURE_2D = 0x8064;
	public static final int GL_PROXY_TEXTURE_1D = 0x8063;
	public static final int GL_TEXTURE_INTENSITY_SIZE = 0x8061;
	public static final int GL_TEXTURE_LUMINANCE_SIZE = 0x8060;
	public static final int GL_TEXTURE_ALPHA_SIZE = 0x805f;
	public static final int GL_TEXTURE_BLUE_SIZE = 0x805e;
	public static final int GL_TEXTURE_GREEN_SIZE = 0x805d;
	public static final int GL_TEXTURE_RED_SIZE = 0x805c;
	public static final int GL_RGBA16 = 0x805b;
	public static final int GL_RGBA12 = 0x805a;
	public static final int GL_RGB10_A2 = 0x8059;
	public static final int GL_RGBA8 = 0x8058;
	public static final int GL_RGB5_A1 = 0x8057;
	public static final int GL_RGBA4 = 0x8056;
	public static final int GL_RGBA2 = 0x8055;
	public static final int GL_RGB16 = 0x8054;
	public static final int GL_RGB12 = 0x8053;
	public static final int GL_RGB10 = 0x8052;
	public static final int GL_RGB8 = 0x8051;
	public static final int GL_RGB5 = 0x8050;
	public static final int GL_RGB4 = 0x804f;
	public static final int GL_R3_G3_B2 = 0x2a10;
	public static final int GL_INTENSITY16 = 0x804d;
	public static final int GL_INTENSITY12 = 0x804c;
	public static final int GL_INTENSITY8 = 0x804b;
	public static final int GL_INTENSITY4 = 0x804a;
	public static final int GL_INTENSITY = 0x8049;
	public static final int GL_LUMINANCE16_ALPHA16 = 0x8048;
	public static final int GL_LUMINANCE12_ALPHA12 = 0x8047;
	public static final int GL_LUMINANCE12_ALPHA4 = 0x8046;
	public static final int GL_LUMINANCE8_ALPHA8 = 0x8045;
	public static final int GL_LUMINANCE6_ALPHA2 = 0x8044;
	public static final int GL_LUMINANCE4_ALPHA4 = 0x8043;
	public static final int GL_LUMINANCE16 = 0x8042;
	public static final int GL_LUMINANCE12 = 0x8041;
	public static final int GL_LUMINANCE8 = 0x8040;
	public static final int GL_LUMINANCE4 = 0x803f;
	public static final int GL_ALPHA16 = 0x803e;
	public static final int GL_ALPHA12 = 0x803d;
	public static final int GL_ALPHA8 = 0x803c;
	public static final int GL_ALPHA4 = 0x803b;
	public static final int GL_POLYGON_OFFSET_FILL = 0x8037;
	public static final int GL_POLYGON_OFFSET_LINE = 0x2a02;
	public static final int GL_POLYGON_OFFSET_POINT = 0x2a01;
	public static final int GL_POLYGON_OFFSET_UNITS = 0x2a00;
	public static final int GL_POLYGON_OFFSET_FACTOR = 0x8038;
	public static final int GL_ALL_CLIENT_ATTRIB_BITS = 0xffffffff;
	public static final int GL_CLIENT_VERTEX_ARRAY_BIT = 0x2;
	public static final int GL_CLIENT_PIXEL_STORE_BIT = 0x1;
	public static final int GL_REPEAT = 0x2901;
	public static final int GL_CLAMP = 0x2900;
	public static final int GL_TEXTURE_WRAP_T = 0x2803;
	public static final int GL_TEXTURE_WRAP_S = 0x2802;
	public static final int GL_TEXTURE_MIN_FILTER = 0x2801;
	public static final int GL_TEXTURE_MAG_FILTER = 0x2800;
	public static final int GL_LINEAR_MIPMAP_LINEAR = 0x2703;
	public static final int GL_NEAREST_MIPMAP_LINEAR = 0x2702;
	public static final int GL_LINEAR_MIPMAP_NEAREST = 0x2701;
	public static final int GL_NEAREST_MIPMAP_NEAREST = 0x2700;
	public static final int GL_LINEAR = 0x2601;
	public static final int GL_NEAREST = 0x2600;
	public static final int GL_EYE_PLANE = 0x2502;
	public static final int GL_OBJECT_PLANE = 0x2501;
	public static final int GL_TEXTURE_GEN_MODE = 0x2500;
	public static final int GL_SPHERE_MAP = 0x2402;
	public static final int GL_OBJECT_LINEAR = 0x2401;
	public static final int GL_EYE_LINEAR = 0x2400;
	public static final int GL_TEXTURE_ENV = 0x2300;
	public static final int GL_TEXTURE_ENV_COLOR = 0x2201;
	public static final int GL_TEXTURE_ENV_MODE = 0x2200;
	public static final int GL_DECAL = 0x2101;
	public static final int GL_MODULATE = 0x2100;
	public static final int GL_Q = 0x2003;
	public static final int GL_R = 0x2002;
	public static final int GL_T = 0x2001;
	public static final int GL_S = 0x2000;
	public static final int GL_EXTENSIONS = 0x1f03;
	public static final int GL_VERSION = 0x1f02;
	public static final int GL_RENDERER = 0x1f01;
	public static final int GL_VENDOR = 0x1f00;
	public static final int GL_DECR = 0x1e03;
	public static final int GL_INCR = 0x1e02;
	public static final int GL_REPLACE = 0x1e01;
	public static final int GL_KEEP = 0x1e00;
	public static final int GL_SMOOTH = 0x1d01;
	public static final int GL_FLAT = 0x1d00;
	public static final int GL_SELECT = 0x1c02;
	public static final int GL_FEEDBACK = 0x1c01;
	public static final int GL_RENDER = 0x1c00;
	public static final int GL_FILL = 0x1b02;
	public static final int GL_LINE = 0x1b01;
	public static final int GL_POINT = 0x1b00;
	public static final int GL_BITMAP = 0x1a00;
	public static final int GL_LUMINANCE_ALPHA = 0x190a;
	public static final int GL_LUMINANCE = 0x1909;
	public static final int GL_RGBA = 0x1908;
	public static final int GL_RGB = 0x1907;
	public static final int GL_ALPHA = 0x1906;
	public static final int GL_BLUE = 0x1905;
	public static final int GL_GREEN = 0x1904;
	public static final int GL_RED = 0x1903;
	public static final int GL_DEPTH_COMPONENT = 0x1902;
	public static final int GL_STENCIL_INDEX = 0x1901;
	public static final int GL_COLOR_INDEX = 0x1900;
	public static final int GL_STENCIL = 0x1802;
	public static final int GL_DEPTH = 0x1801;
	public static final int GL_COLOR = 0x1800;
	public static final int GL_TEXTURE = 0x1702;
	public static final int GL_PROJECTION = 0x1701;
	public static final int GL_MODELVIEW = 0x1700;
	public static final int GL_COLOR_INDEXES = 0x1603;
	public static final int GL_AMBIENT_AND_DIFFUSE = 0x1602;
	public static final int GL_SHININESS = 0x1601;
	public static final int GL_EMISSION = 0x1600;
	public static final int GL_SET = 0x150f;
	public static final int GL_NAND = 0x150e;
	public static final int GL_OR_INVERTED = 0x150d;
	public static final int GL_COPY_INVERTED = 0x150c;
	public static final int GL_OR_REVERSE = 0x150b;
	public static final int GL_INVERT = 0x150a;
	public static final int GL_EQUIV = 0x1509;
	public static final int GL_NOR = 0x1508;
	public static final int GL_OR = 0x1507;
	public static final int GL_XOR = 0x1506;
	public static final int GL_NOOP = 0x1505;
	public static final int GL_AND_INVERTED = 0x1504;
	public static final int GL_COPY = 0x1503;
	public static final int GL_AND_REVERSE = 0x1502;
	public static final int GL_AND = 0x1501;
	public static final int GL_CLEAR = 0x1500;
	public static final int GL_COMPILE_AND_EXECUTE = 0x1301;
	public static final int GL_COMPILE = 0x1300;
	public static final int GL_QUADRATIC_ATTENUATION = 0x1209;
	public static final int GL_LINEAR_ATTENUATION = 0x1208;
	public static final int GL_CONSTANT_ATTENUATION = 0x1207;
	public static final int GL_SPOT_CUTOFF = 0x1206;
	public static final int GL_SPOT_EXPONENT = 0x1205;
	public static final int GL_SPOT_DIRECTION = 0x1204;
	public static final int GL_POSITION = 0x1203;
	public static final int GL_SPECULAR = 0x1202;
	public static final int GL_DIFFUSE = 0x1201;
	public static final int GL_AMBIENT = 0x1200;
	public static final int GL_LIGHT7 = 0x4007;
	public static final int GL_LIGHT6 = 0x4006;
	public static final int GL_LIGHT5 = 0x4005;
	public static final int GL_LIGHT4 = 0x4004;
	public static final int GL_LIGHT3 = 0x4003;
	public static final int GL_LIGHT2 = 0x4002;
	public static final int GL_LIGHT1 = 0x4001;
	public static final int GL_LIGHT0 = 0x4000;
	public static final int GL_NICEST = 0x1102;
	public static final int GL_FASTEST = 0x1101;
	public static final int GL_DONT_CARE = 0x1100;
	public static final int GL_TEXTURE_BORDER = 0x1005;
	public static final int GL_TEXTURE_BORDER_COLOR = 0x1004;
	public static final int GL_TEXTURE_INTERNAL_FORMAT = 0x1003;
	public static final int GL_TEXTURE_HEIGHT = 0x1001;
	public static final int GL_TEXTURE_WIDTH = 0x1000;
	public static final int GL_SELECTION_BUFFER_SIZE = 0xdf4;
	public static final int GL_SELECTION_BUFFER_POINTER = 0xdf3;
	public static final int GL_FEEDBACK_BUFFER_TYPE = 0xdf2;
	public static final int GL_FEEDBACK_BUFFER_SIZE = 0xdf1;
	public static final int GL_FEEDBACK_BUFFER_POINTER = 0xdf0;
	public static final int GL_TEXTURE_2D = 0xde1;
	public static final int GL_TEXTURE_1D = 0xde0;
	public static final int GL_MAP2_GRID_SEGMENTS = 0xdd3;
	public static final int GL_MAP2_GRID_DOMAIN = 0xdd2;
	public static final int GL_MAP1_GRID_SEGMENTS = 0xdd1;
	public static final int GL_MAP1_GRID_DOMAIN = 0xdd0;
	public static final int GL_MAP2_VERTEX_4 = 0xdb8;
	public static final int GL_MAP2_VERTEX_3 = 0xdb7;
	public static final int GL_MAP2_TEXTURE_COORD_4 = 0xdb6;
	public static final int GL_MAP2_TEXTURE_COORD_3 = 0xdb5;
	public static final int GL_MAP2_TEXTURE_COORD_2 = 0xdb4;
	public static final int GL_MAP2_TEXTURE_COORD_1 = 0xdb3;
	public static final int GL_MAP2_NORMAL = 0xdb2;
	public static final int GL_MAP2_INDEX = 0xdb1;
	public static final int GL_MAP2_COLOR_4 = 0xdb0;
	public static final int GL_MAP1_VERTEX_4 = 0xd98;
	public static final int GL_MAP1_VERTEX_3 = 0xd97;
	public static final int GL_MAP1_TEXTURE_COORD_4 = 0xd96;
	public static final int GL_MAP1_TEXTURE_COORD_3 = 0xd95;
	public static final int GL_MAP1_TEXTURE_COORD_2 = 0xd94;
	public static final int GL_MAP1_TEXTURE_COORD_1 = 0xd93;
	public static final int GL_MAP1_NORMAL = 0xd92;
	public static final int GL_MAP1_INDEX = 0xd91;
	public static final int GL_MAP1_COLOR_4 = 0xd90;
	public static final int GL_AUTO_NORMAL = 0xd80;
	public static final int GL_NAME_STACK_DEPTH = 0xd70;
	public static final int GL_ACCUM_ALPHA_BITS = 0xd5b;
	public static final int GL_ACCUM_BLUE_BITS = 0xd5a;
	public static final int GL_ACCUM_GREEN_BITS = 0xd59;
	public static final int GL_ACCUM_RED_BITS = 0xd58;
	public static final int GL_STENCIL_BITS = 0xd57;
	public static final int GL_DEPTH_BITS = 0xd56;
	public static final int GL_ALPHA_BITS = 0xd55;
	public static final int GL_BLUE_BITS = 0xd54;
	public static final int GL_GREEN_BITS = 0xd53;
	public static final int GL_RED_BITS = 0xd52;
	public static final int GL_INDEX_BITS = 0xd51;
	public static final int GL_SUBPIXEL_BITS = 0xd50;
	public static final int GL_MAX_CLIENT_ATTRIB_STACK_DEPTH = 0xd3b;
	public static final int GL_MAX_VIEWPORT_DIMS = 0xd3a;
	public static final int GL_MAX_TEXTURE_STACK_DEPTH = 0xd39;
	public static final int GL_MAX_PROJECTION_STACK_DEPTH = 0xd38;
	public static final int GL_MAX_NAME_STACK_DEPTH = 0xd37;
	public static final int GL_MAX_MODELVIEW_STACK_DEPTH = 0xd36;
	public static final int GL_MAX_ATTRIB_STACK_DEPTH = 0xd35;
	public static final int GL_MAX_PIXEL_MAP_TABLE = 0xd34;
	public static final int GL_MAX_TEXTURE_SIZE = 0xd33;
	public static final int GL_MAX_CLIP_PLANES = 0xd32;
	public static final int GL_MAX_LIGHTS = 0xd31;
	public static final int GL_MAX_EVAL_ORDER = 0xd30;
	public static final int GL_DEPTH_BIAS = 0xd1f;
	public static final int GL_DEPTH_SCALE = 0xd1e;
	public static final int GL_ALPHA_BIAS = 0xd1d;
	public static final int GL_ALPHA_SCALE = 0xd1c;
	public static final int GL_BLUE_BIAS = 0xd1b;
	public static final int GL_BLUE_SCALE = 0xd1a;
	public static final int GL_GREEN_BIAS = 0xd19;
	public static final int GL_GREEN_SCALE = 0xd18;
	public static final int GL_ZOOM_Y = 0xd17;
	public static final int GL_ZOOM_X = 0xd16;
	public static final int GL_RED_BIAS = 0xd15;
	public static final int GL_RED_SCALE = 0xd14;
	public static final int GL_INDEX_OFFSET = 0xd13;
	public static final int GL_INDEX_SHIFT = 0xd12;
	public static final int GL_MAP_STENCIL = 0xd11;
	public static final int GL_MAP_COLOR = 0xd10;
	public static final int GL_PACK_ALIGNMENT = 0xd05;
	public static final int GL_PACK_SKIP_PIXELS = 0xd04;
	public static final int GL_PACK_SKIP_ROWS = 0xd03;
	public static final int GL_PACK_ROW_LENGTH = 0xd02;
	public static final int GL_PACK_LSB_FIRST = 0xd01;
	public static final int GL_PACK_SWAP_BYTES = 0xd00;
	public static final int GL_UNPACK_ALIGNMENT = 0xcf5;
	public static final int GL_UNPACK_SKIP_PIXELS = 0xcf4;
	public static final int GL_UNPACK_SKIP_ROWS = 0xcf3;
	public static final int GL_UNPACK_ROW_LENGTH = 0xcf2;
	public static final int GL_UNPACK_LSB_FIRST = 0xcf1;
	public static final int GL_UNPACK_SWAP_BYTES = 0xcf0;
	public static final int GL_PIXEL_MAP_A_TO_A_SIZE = 0xcb9;
	public static final int GL_PIXEL_MAP_B_TO_B_SIZE = 0xcb8;
	public static final int GL_PIXEL_MAP_G_TO_G_SIZE = 0xcb7;
	public static final int GL_PIXEL_MAP_R_TO_R_SIZE = 0xcb6;
	public static final int GL_PIXEL_MAP_I_TO_A_SIZE = 0xcb5;
	public static final int GL_PIXEL_MAP_I_TO_B_SIZE = 0xcb4;
	public static final int GL_PIXEL_MAP_I_TO_G_SIZE = 0xcb3;
	public static final int GL_PIXEL_MAP_I_TO_R_SIZE = 0xcb2;
	public static final int GL_PIXEL_MAP_S_TO_S_SIZE = 0xcb1;
	public static final int GL_PIXEL_MAP_I_TO_I_SIZE = 0xcb0;
	public static final int GL_PIXEL_MAP_A_TO_A = 0xc79;
	public static final int GL_PIXEL_MAP_B_TO_B = 0xc78;
	public static final int GL_PIXEL_MAP_G_TO_G = 0xc77;
	public static final int GL_PIXEL_MAP_R_TO_R = 0xc76;
	public static final int GL_PIXEL_MAP_I_TO_A = 0xc75;
	public static final int GL_PIXEL_MAP_I_TO_B = 0xc74;
	public static final int GL_PIXEL_MAP_I_TO_G = 0xc73;
	public static final int GL_PIXEL_MAP_I_TO_R = 0xc72;
	public static final int GL_PIXEL_MAP_S_TO_S = 0xc71;
	public static final int GL_PIXEL_MAP_I_TO_I = 0xc70;
	public static final int GL_TEXTURE_GEN_Q = 0xc63;
	public static final int GL_TEXTURE_GEN_R = 0xc62;
	public static final int GL_TEXTURE_GEN_T = 0xc61;
	public static final int GL_TEXTURE_GEN_S = 0xc60;
	public static final int GL_FOG_HINT = 0xc54;
	public static final int GL_POLYGON_SMOOTH_HINT = 0xc53;
	public static final int GL_LINE_SMOOTH_HINT = 0xc52;
	public static final int GL_POINT_SMOOTH_HINT = 0xc51;
	public static final int GL_PERSPECTIVE_CORRECTION_HINT = 0xc50;
	public static final int GL_RENDER_MODE = 0xc40;
	public static final int GL_STEREO = 0xc33;
	public static final int GL_DOUBLEBUFFER = 0xc32;
	public static final int GL_RGBA_MODE = 0xc31;
	public static final int GL_INDEX_MODE = 0xc30;
	public static final int GL_COLOR_WRITEMASK = 0xc23;
	public static final int GL_COLOR_CLEAR_VALUE = 0xc22;
	public static final int GL_INDEX_WRITEMASK = 0xc21;
	public static final int GL_INDEX_CLEAR_VALUE = 0xc20;
	public static final int GL_SCISSOR_TEST = 0xc11;
	public static final int GL_SCISSOR_BOX = 0xc10;
	public static final int GL_READ_BUFFER = 0xc02;
	public static final int GL_DRAW_BUFFER = 0xc01;
	public static final int GL_AUX_BUFFERS = 0xc00;
	public static final int GL_COLOR_LOGIC_OP = 0xbf2;
	public static final int GL_INDEX_LOGIC_OP = 0xbf1;
	public static final int GL_LOGIC_OP_MODE = 0xbf0;
	public static final int GL_BLEND = 0xbe2;
	public static final int GL_BLEND_SRC = 0xbe1;
	public static final int GL_BLEND_DST = 0xbe0;
	public static final int GL_DITHER = 0xbd0;
	public static final int GL_ALPHA_TEST_REF = 0xbc2;
	public static final int GL_ALPHA_TEST_FUNC = 0xbc1;
	public static final int GL_ALPHA_TEST = 0xbc0;
	public static final int GL_CLIENT_ATTRIB_STACK_DEPTH = 0xbb1;
	public static final int GL_ATTRIB_STACK_DEPTH = 0xbb0;
	public static final int GL_TEXTURE_MATRIX = 0xba8;
	public static final int GL_PROJECTION_MATRIX = 0xba7;
	public static final int GL_MODELVIEW_MATRIX = 0xba6;
	public static final int GL_TEXTURE_STACK_DEPTH = 0xba5;
	public static final int GL_PROJECTION_STACK_DEPTH = 0xba4;
	public static final int GL_MODELVIEW_STACK_DEPTH = 0xba3;
	public static final int GL_VIEWPORT = 0xba2;
	public static final int GL_NORMALIZE = 0xba1;
	public static final int GL_MATRIX_MODE = 0xba0;
	public static final int GL_STENCIL_WRITEMASK = 0xb98;
	public static final int GL_STENCIL_REF = 0xb97;
	public static final int GL_STENCIL_PASS_DEPTH_PASS = 0xb96;
	public static final int GL_STENCIL_PASS_DEPTH_FAIL = 0xb95;
	public static final int GL_STENCIL_FAIL = 0xb94;
	public static final int GL_STENCIL_VALUE_MASK = 0xb93;
	public static final int GL_STENCIL_FUNC = 0xb92;
	public static final int GL_STENCIL_CLEAR_VALUE = 0xb91;
	public static final int GL_STENCIL_TEST = 0xb90;
	public static final int GL_ACCUM_CLEAR_VALUE = 0xb80;
	public static final int GL_DEPTH_FUNC = 0xb74;
	public static final int GL_DEPTH_CLEAR_VALUE = 0xb73;
	public static final int GL_DEPTH_WRITEMASK = 0xb72;
	public static final int GL_DEPTH_TEST = 0xb71;
	public static final int GL_DEPTH_RANGE = 0xb70;
	public static final int GL_FOG_COLOR = 0xb66;
	public static final int GL_FOG_MODE = 0xb65;
	public static final int GL_FOG_END = 0xb64;
	public static final int GL_FOG_START = 0xb63;
	public static final int GL_FOG_DENSITY = 0xb62;
	public static final int GL_FOG_INDEX = 0xb61;
	public static final int GL_FOG = 0xb60;
	public static final int GL_COLOR_MATERIAL = 0xb57;
	public static final int GL_COLOR_MATERIAL_PARAMETER = 0xb56;
	public static final int GL_COLOR_MATERIAL_FACE = 0xb55;
	public static final int GL_SHADE_MODEL = 0xb54;
	public static final int GL_LIGHT_MODEL_AMBIENT = 0xb53;
	public static final int GL_LIGHT_MODEL_TWO_SIDE = 0xb52;
	public static final int GL_LIGHT_MODEL_LOCAL_VIEWER = 0xb51;
	public static final int GL_LIGHTING = 0xb50;
	public static final int GL_FRONT_FACE = 0xb46;
	public static final int GL_CULL_FACE_MODE = 0xb45;
	public static final int GL_CULL_FACE = 0xb44;
	public static final int GL_EDGE_FLAG = 0xb43;
	public static final int GL_POLYGON_STIPPLE = 0xb42;
	public static final int GL_POLYGON_SMOOTH = 0xb41;
	public static final int GL_POLYGON_MODE = 0xb40;
	public static final int GL_LIST_INDEX = 0xb33;
	public static final int GL_LIST_BASE = 0xb32;
	public static final int GL_MAX_LIST_NESTING = 0xb31;
	public static final int GL_LIST_MODE = 0xb30;
	public static final int GL_LINE_STIPPLE_REPEAT = 0xb26;
	public static final int GL_LINE_STIPPLE_PATTERN = 0xb25;
	public static final int GL_LINE_STIPPLE = 0xb24;
	public static final int GL_LINE_WIDTH_GRANULARITY = 0xb23;
	public static final int GL_LINE_WIDTH_RANGE = 0xb22;
	public static final int GL_LINE_WIDTH = 0xb21;
	public static final int GL_LINE_SMOOTH = 0xb20;
	public static final int GL_POINT_SIZE_GRANULARITY = 0xb13;
	public static final int GL_POINT_SIZE_RANGE = 0xb12;
	public static final int GL_POINT_SIZE = 0xb11;
	public static final int GL_POINT_SMOOTH = 0xb10;
	public static final int GL_CURRENT_RASTER_DISTANCE = 0xb09;
	public static final int GL_CURRENT_RASTER_POSITION_VALID = 0xb08;
	public static final int GL_CURRENT_RASTER_POSITION = 0xb07;
	public static final int GL_CURRENT_RASTER_TEXTURE_COORDS = 0xb06;
	public static final int GL_CURRENT_RASTER_INDEX = 0xb05;
	public static final int GL_CURRENT_RASTER_COLOR = 0xb04;
	public static final int GL_CURRENT_TEXTURE_COORDS = 0xb03;
	public static final int GL_CURRENT_NORMAL = 0xb02;
	public static final int GL_CURRENT_INDEX = 0xb01;
	public static final int GL_CURRENT_COLOR = 0xb00;
	public static final int GL_DOMAIN = 0xa02;
	public static final int GL_ORDER = 0xa01;
	public static final int GL_COEFF = 0xa00;
	public static final int GL_CCW = 0x901;
	public static final int GL_CW = 0x900;
	public static final int GL_EXP2 = 0x801;
	public static final int GL_EXP = 0x800;
	public static final int GL_LINE_RESET_TOKEN = 0x707;
	public static final int GL_COPY_PIXEL_TOKEN = 0x706;
	public static final int GL_DRAW_PIXEL_TOKEN = 0x705;
	public static final int GL_BITMAP_TOKEN = 0x704;
	public static final int GL_POLYGON_TOKEN = 0x703;
	public static final int GL_LINE_TOKEN = 0x702;
	public static final int GL_POINT_TOKEN = 0x701;
	public static final int GL_PASS_THROUGH_TOKEN = 0x700;
	public static final int GL_4D_COLOR_TEXTURE = 0x604;
	public static final int GL_3D_COLOR_TEXTURE = 0x603;
	public static final int GL_3D_COLOR = 0x602;
	public static final int GL_3D = 0x601;
	public static final int GL_2D = 0x600;
	public static final int GL_OUT_OF_MEMORY = 0x505;
	public static final int GL_STACK_UNDERFLOW = 0x504;
	public static final int GL_STACK_OVERFLOW = 0x503;
	public static final int GL_INVALID_OPERATION = 0x502;
	public static final int GL_INVALID_VALUE = 0x501;
	public static final int GL_INVALID_ENUM = 0x500;
	public static final int GL_NO_ERROR = 0x0;
	public static final int GL_AUX3 = 0x40c;
	public static final int GL_AUX2 = 0x40b;
	public static final int GL_AUX1 = 0x40a;
	public static final int GL_AUX0 = 0x409;
	public static final int GL_FRONT_AND_BACK = 0x408;
	public static final int GL_RIGHT = 0x407;
	public static final int GL_LEFT = 0x406;
	public static final int GL_BACK = 0x405;
	public static final int GL_FRONT = 0x404;
	public static final int GL_BACK_RIGHT = 0x403;
	public static final int GL_BACK_LEFT = 0x402;
	public static final int GL_FRONT_RIGHT = 0x401;
	public static final int GL_FRONT_LEFT = 0x400;
	public static final int GL_NONE = 0x0;
	public static final int GL_DOUBLE = 0x140a;
	public static final int GL_4_BYTES = 0x1409;
	public static final int GL_3_BYTES = 0x1408;
	public static final int GL_2_BYTES = 0x1407;
	public static final int GL_FLOAT = 0x1406;
	public static final int GL_UNSIGNED_INT = 0x1405;
	public static final int GL_INT = 0x1404;
	public static final int GL_UNSIGNED_SHORT = 0x1403;
	public static final int GL_SHORT = 0x1402;
	public static final int GL_UNSIGNED_BYTE = 0x1401;
	public static final int GL_BYTE = 0x1400;
	public static final int GL_CLIP_PLANE5 = 0x3005;
	public static final int GL_CLIP_PLANE4 = 0x3004;
	public static final int GL_CLIP_PLANE3 = 0x3003;
	public static final int GL_CLIP_PLANE2 = 0x3002;
	public static final int GL_CLIP_PLANE1 = 0x3001;
	public static final int GL_CLIP_PLANE0 = 0x3000;
	public static final int GL_FALSE = 0x0;
	public static final int GL_TRUE = 0x1;
	public static final int GL_ONE_MINUS_CONSTANT_ALPHA = 0x8004;
	public static final int GL_CONSTANT_ALPHA = 0x8003;
	public static final int GL_ONE_MINUS_CONSTANT_COLOR = 0x8002;
	public static final int GL_CONSTANT_COLOR = 0x8001;
	public static final int GL_SRC_ALPHA_SATURATE = 0x308;
	public static final int GL_ONE_MINUS_DST_COLOR = 0x307;
	public static final int GL_DST_COLOR = 0x306;
	public static final int GL_ONE_MINUS_DST_ALPHA = 0x305;
	public static final int GL_DST_ALPHA = 0x304;
	public static final int GL_ONE_MINUS_SRC_ALPHA = 0x303;
	public static final int GL_SRC_ALPHA = 0x302;
	public static final int GL_ONE_MINUS_SRC_COLOR = 0x301;
	public static final int GL_SRC_COLOR = 0x300;
	public static final int GL_ONE = 0x1;
	public static final int GL_ZERO = 0x0;
	public static final int GL_POLYGON = 0x9;
	public static final int GL_QUAD_STRIP = 0x8;
	public static final int GL_QUADS = 0x7;
	public static final int GL_TRIANGLE_FAN = 0x6;
	public static final int GL_TRIANGLE_STRIP = 0x5;
	public static final int GL_TRIANGLES = 0x4;
	public static final int GL_LINE_STRIP = 0x3;
	public static final int GL_LINE_LOOP = 0x2;
	public static final int GL_LINES = 0x1;
	public static final int GL_POINTS = 0x0;
	public static final int GL_ALL_ATTRIB_BITS = 0xfffff;
	public static final int GL_SCISSOR_BIT = 0x80000;
	public static final int GL_TEXTURE_BIT = 0x40000;
	public static final int GL_LIST_BIT = 0x20000;
	public static final int GL_EVAL_BIT = 0x10000;
	public static final int GL_HINT_BIT = 0x8000;
	public static final int GL_COLOR_BUFFER_BIT = 0x4000;
	public static final int GL_ENABLE_BIT = 0x2000;
	public static final int GL_TRANSFORM_BIT = 0x1000;
	public static final int GL_VIEWPORT_BIT = 0x800;
	public static final int GL_STENCIL_BUFFER_BIT = 0x400;
	public static final int GL_ACCUM_BUFFER_BIT = 0x200;
	public static final int GL_DEPTH_BUFFER_BIT = 0x100;
	public static final int GL_FOG_BIT = 0x80;
	public static final int GL_LIGHTING_BIT = 0x40;
	public static final int GL_PIXEL_MODE_BIT = 0x20;
	public static final int GL_POLYGON_STIPPLE_BIT = 0x10;
	public static final int GL_POLYGON_BIT = 0x8;
	public static final int GL_LINE_BIT = 0x4;
	public static final int GL_POINT_BIT = 0x2;
	public static final int GL_CURRENT_BIT = 0x1;
	public static final int GL_ALWAYS = 0x207;
	public static final int GL_GEQUAL = 0x206;
	public static final int GL_NOTEQUAL = 0x205;
	public static final int GL_GREATER = 0x204;
	public static final int GL_LEQUAL = 0x203;
	public static final int GL_EQUAL = 0x202;
	public static final int GL_LESS = 0x201;
	public static final int GL_NEVER = 0x200;
	public static final int GL_ADD = 0x104;
	public static final int GL_MULT = 0x103;
	public static final int GL_RETURN = 0x102;
	public static final int GL_LOAD = 0x101;
	public static final int GL_ACCUM = 0x100;

	private GL11() {
	}

	static native void initNativeStubs() throws LWJGLException;

	public static native void glViewport(int x, int y, int width, int height);

	public static native void glStencilMask(int mask);

	public static native void glStencilOp(int fail, int zfail, int zpass);

	public static native void glTexCoord4f(float s, float t, float r, float q);

	public static native void glTexCoord3f(float s, float t, float r);

	public static native void glTexCoord2f(float s, float t);

	public static native void glTexCoord1f(float s);

	public static void glTexCoordPointer(int size, int stride, FloatBuffer pointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pointer);
		nglTexCoordPointer(size, GL11.GL_FLOAT, stride, pointer, pointer.position() << 2);
	}
	private static native void nglTexCoordPointer(int size, int type, int stride, Buffer pointer, int pointer_position);
	public static void glTexCoordPointer(int size, int type, int stride, int pointer_buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglTexCoordPointerBO(size, type, stride, pointer_buffer_offset);
	}
	private static native void nglTexCoordPointerBO(int size, int type, int stride, int pointer_buffer_offset);

	public static void glTexEnv(int target, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglTexEnviv(target, pname, params, params.position());
	}
	private static native void nglTexEnviv(int target, int pname, IntBuffer params, int params_position);

	public static void glTexEnv(int target, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglTexEnvfv(target, pname, params, params.position());
	}
	private static native void nglTexEnvfv(int target, int pname, FloatBuffer params, int params_position);

	public static native void glTexEnvi(int target, int pname, int param);

	public static native void glTexEnvf(int target, int pname, float param);

	public static void glTexGen(int coord, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglTexGeniv(coord, pname, params, params.position());
	}
	private static native void nglTexGeniv(int coord, int pname, IntBuffer params, int params_position);

	public static native void glTexGeni(int coord, int pname, int param);

	public static void glTexGen(int coord, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglTexGenfv(coord, pname, params, params.position());
	}
	private static native void nglTexGenfv(int coord, int pname, FloatBuffer params, int params_position);

	public static native void glTexGenf(int coord, int pname, float param);

	public static void glTexParameter(int target, int pname, IntBuffer param) {
		BufferChecks.checkBuffer(param, 4);
		nglTexParameteriv(target, pname, param, param.position());
	}
	private static native void nglTexParameteriv(int target, int pname, IntBuffer param, int param_position);

	public static void glTexParameter(int target, int pname, FloatBuffer param) {
		BufferChecks.checkBuffer(param, 4);
		nglTexParameterfv(target, pname, param, param.position());
	}
	private static native void nglTexParameterfv(int target, int pname, FloatBuffer param, int param_position);

	public static native void glTexParameteri(int target, int pname, int param);

	public static native void glTexParameterf(int target, int pname, float param);

	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ShortBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, width, height, 1));
		nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels, pixels.position() << 1);
	}
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, IntBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, width, height, 1));
		nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels, pixels.position() << 2);
	}
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, FloatBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, width, height, 1));
		nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels, pixels.position() << 2);
	}
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, width, height, 1));
		nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels, pixels.position());
	}
	private static native void nglTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, Buffer pixels, int pixels_position);
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, int pixels_buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglTexSubImage2DBO(target, level, xoffset, yoffset, width, height, format, type, pixels_buffer_offset);
	}
	private static native void nglTexSubImage2DBO(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, int pixels_buffer_offset);

	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ShortBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
		nglTexSubImage1D(target, level, xoffset, width, format, type, pixels, pixels.position() << 1);
	}
	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, IntBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
		nglTexSubImage1D(target, level, xoffset, width, format, type, pixels, pixels.position() << 2);
	}
	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, FloatBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
		nglTexSubImage1D(target, level, xoffset, width, format, type, pixels, pixels.position() << 2);
	}
	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ByteBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, width, 1, 1));
		nglTexSubImage1D(target, level, xoffset, width, format, type, pixels, pixels.position());
	}
	private static native void nglTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, Buffer pixels, int pixels_position);
	public static void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, int pixels_buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglTexSubImage1DBO(target, level, xoffset, width, format, type, pixels_buffer_offset);
	}
	private static native void nglTexSubImage1DBO(int target, int level, int xoffset, int width, int format, int type, int pixels_buffer_offset);

	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ShortBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if (pixels != null)
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage2DStorage(pixels, format, type, width, height, border));
		nglTexImage2D(target, level, internalformat, width, height, border, format, type, pixels, pixels != null ? pixels.position() << 1 : 0);
	}
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, IntBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if (pixels != null)
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage2DStorage(pixels, format, type, width, height, border));
		nglTexImage2D(target, level, internalformat, width, height, border, format, type, pixels, pixels != null ? pixels.position() << 2 : 0);
	}
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, FloatBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if (pixels != null)
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage2DStorage(pixels, format, type, width, height, border));
		nglTexImage2D(target, level, internalformat, width, height, border, format, type, pixels, pixels != null ? pixels.position() << 2 : 0);
	}
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if (pixels != null)
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage2DStorage(pixels, format, type, width, height, border));
		nglTexImage2D(target, level, internalformat, width, height, border, format, type, pixels, pixels != null ? pixels.position() : 0);
	}
	private static native void nglTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, Buffer pixels, int pixels_position);
	public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, int pixels_buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglTexImage2DBO(target, level, internalformat, width, height, border, format, type, pixels_buffer_offset);
	}
	private static native void nglTexImage2DBO(int target, int level, int internalformat, int width, int height, int border, int format, int type, int pixels_buffer_offset);

	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ShortBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if (pixels != null)
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage1DStorage(pixels, format, type, width, border));
		nglTexImage1D(target, level, internalformat, width, border, format, type, pixels, pixels != null ? pixels.position() << 1 : 0);
	}
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, IntBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if (pixels != null)
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage1DStorage(pixels, format, type, width, border));
		nglTexImage1D(target, level, internalformat, width, border, format, type, pixels, pixels != null ? pixels.position() << 2 : 0);
	}
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, FloatBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if (pixels != null)
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage1DStorage(pixels, format, type, width, border));
		nglTexImage1D(target, level, internalformat, width, border, format, type, pixels, pixels != null ? pixels.position() << 2 : 0);
	}
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ByteBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		if (pixels != null)
			BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateTexImage1DStorage(pixels, format, type, width, border));
		nglTexImage1D(target, level, internalformat, width, border, format, type, pixels, pixels != null ? pixels.position() : 0);
	}
	private static native void nglTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, Buffer pixels, int pixels_position);
	public static void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, int pixels_buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglTexImage1DBO(target, level, internalformat, width, border, format, type, pixels_buffer_offset);
	}
	private static native void nglTexImage1DBO(int target, int level, int internalformat, int width, int border, int format, int type, int pixels_buffer_offset);

	public static native void glTranslatef(float x, float y, float z);

	public static native void glVertex4i(int x, int y, int z, int w);

	public static native void glVertex4f(float x, float y, float z, float w);

	public static native void glVertex3i(int x, int y, int z);

	public static native void glVertex3f(float x, float y, float z);

	public static native void glVertex2i(int x, int y);

	public static native void glVertex2f(float x, float y);

	public static void glVertexPointer(int size, int stride, IntBuffer pointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pointer);
		nglVertexPointer(size, GL11.GL_INT, stride, pointer, pointer.position() << 2);
	}
	public static void glVertexPointer(int size, int stride, FloatBuffer pointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pointer);
		nglVertexPointer(size, GL11.GL_FLOAT, stride, pointer, pointer.position() << 2);
	}
	private static native void nglVertexPointer(int size, int type, int stride, Buffer pointer, int pointer_position);
	public static void glVertexPointer(int size, int type, int stride, int pointer_buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglVertexPointerBO(size, type, stride, pointer_buffer_offset);
	}
	private static native void nglVertexPointerBO(int size, int type, int stride, int pointer_buffer_offset);

	public static native void glStencilFunc(int func, int ref, int mask);

	public static native void glPopAttrib();

	public static native void glPushAttrib(int mask);

	public static void glPopClientAttrib() {
		BufferObjectTracker.popAttrib();
		nglPopClientAttrib();
	}
	private static native void nglPopClientAttrib();

	public static void glPushClientAttrib(int mask) {
		BufferObjectTracker.pushAttrib(mask);
		nglPushClientAttrib(mask);
	}
	private static native void nglPushClientAttrib(int mask);

	public static native void glPopMatrix();

	public static native void glPushMatrix();

	public static native void glPopName();

	public static native void glPushName(int name);

	public static native void glRasterPos4i(int x, int y, int z, int w);

	public static native void glRasterPos4f(float x, float y, float z, float w);

	public static native void glRasterPos3i(int x, int y, int z);

	public static native void glRasterPos3f(float x, float y, float z);

	public static native void glRasterPos2i(int x, int y);

	public static native void glRasterPos2f(float x, float y);

	public static native void glReadBuffer(int mode);

	public static void glReadPixels(int x, int y, int width, int height, int format, int type, ShortBuffer pixels) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, width, height, 1));
		nglReadPixels(x, y, width, height, format, type, pixels, pixels.position() << 1);
	}
	public static void glReadPixels(int x, int y, int width, int height, int format, int type, IntBuffer pixels) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, width, height, 1));
		nglReadPixels(x, y, width, height, format, type, pixels, pixels.position() << 2);
	}
	public static void glReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer pixels) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, width, height, 1));
		nglReadPixels(x, y, width, height, format, type, pixels, pixels.position());
	}
	private static native void nglReadPixels(int x, int y, int width, int height, int format, int type, Buffer pixels, int pixels_position);
	public static void glReadPixels(int x, int y, int width, int height, int format, int type, int pixels_buffer_offset) {
		GLBufferChecks.ensurePackPBOenabled();
		nglReadPixelsBO(x, y, width, height, format, type, pixels_buffer_offset);
	}
	private static native void nglReadPixelsBO(int x, int y, int width, int height, int format, int type, int pixels_buffer_offset);

	public static native void glRecti(int x1, int y1, int x2, int y2);

	public static native void glRectf(float x1, float y1, float x2, float y2);

	public static native int glRenderMode(int mode);

	public static native void glRotatef(float angle, float x, float y, float z);

	public static native void glScalef(float x, float y, float z);

	public static native void glScissor(int x, int y, int width, int height);

	public static void glSelectBuffer(IntBuffer buffer) {
		BufferChecks.checkDirect(buffer);
		nglSelectBuffer((buffer.remaining()), buffer, buffer.position());
	}
	private static native void nglSelectBuffer(int size, IntBuffer buffer, int buffer_position);

	public static native void glShadeModel(int mode);

	public static void glMultMatrix(FloatBuffer m) {
		BufferChecks.checkBuffer(m, 16);
		nglMultMatrixf(m, m.position());
	}
	private static native void nglMultMatrixf(FloatBuffer m, int m_position);

	public static native void glEndList();

	public static native void glNewList(int list, int mode);

	public static native void glNormal3i(int nx, int ny, int nz);

	public static native void glNormal3f(float nx, float ny, float nz);

	public static native void glNormal3b(byte nx, byte ny, byte nz);

	public static void glNormalPointer(int stride, IntBuffer pointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pointer);
		nglNormalPointer(GL11.GL_INT, stride, pointer, pointer.position() << 2);
	}
	public static void glNormalPointer(int stride, FloatBuffer pointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pointer);
		nglNormalPointer(GL11.GL_FLOAT, stride, pointer, pointer.position() << 2);
	}
	public static void glNormalPointer(int stride, ByteBuffer pointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pointer);
		nglNormalPointer(GL11.GL_BYTE, stride, pointer, pointer.position());
	}
	private static native void nglNormalPointer(int type, int stride, Buffer pointer, int pointer_position);
	public static void glNormalPointer(int type, int stride, int pointer_buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglNormalPointerBO(type, stride, pointer_buffer_offset);
	}
	private static native void nglNormalPointerBO(int type, int stride, int pointer_buffer_offset);

	public static native void glOrtho(double left, double right, double bottom, double top, double zNear, double zFar);

	public static native void glPassThrough(float token);

	public static void glPixelMapu(int map, ShortBuffer values) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkDirect(values);
		nglPixelMapusv(map, (values.remaining()), values, values.position());
	}
	private static native void nglPixelMapusv(int map, int mapsize, ShortBuffer values, int values_position);
	public static void glPixelMapusv(int map, int mapsize, int values_buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglPixelMapusvBO(map, mapsize, values_buffer_offset);
	}
	private static native void nglPixelMapusvBO(int map, int mapsize, int values_buffer_offset);

	public static void glPixelMapu(int map, IntBuffer values) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkDirect(values);
		nglPixelMapuiv(map, (values.remaining()), values, values.position());
	}
	private static native void nglPixelMapuiv(int map, int mapsize, IntBuffer values, int values_position);
	public static void glPixelMapuiv(int map, int mapsize, int values_buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglPixelMapuivBO(map, mapsize, values_buffer_offset);
	}
	private static native void nglPixelMapuivBO(int map, int mapsize, int values_buffer_offset);

	public static void glPixelMap(int map, FloatBuffer values) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkDirect(values);
		nglPixelMapfv(map, (values.remaining()), values, values.position());
	}
	private static native void nglPixelMapfv(int map, int mapsize, FloatBuffer values, int values_position);
	public static void glPixelMapfv(int map, int mapsize, int values_buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglPixelMapfvBO(map, mapsize, values_buffer_offset);
	}
	private static native void nglPixelMapfvBO(int map, int mapsize, int values_buffer_offset);

	public static native void glPixelStorei(int pname, int param);

	public static native void glPixelStoref(int pname, float param);

	public static native void glPixelTransferi(int pname, int param);

	public static native void glPixelTransferf(int pname, float param);

	public static native void glPixelZoom(float xfactor, float yfactor);

	public static native void glPointSize(float size);

	public static native void glPolygonMode(int face, int mode);

	public static native void glPolygonOffset(float factor, float units);

	public static void glPolygonStipple(ByteBuffer mask) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(mask, 1024);
		nglPolygonStipple(mask, mask.position());
	}
	private static native void nglPolygonStipple(ByteBuffer mask, int mask_position);
	public static void glPolygonStipple(int mask_buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglPolygonStippleBO(mask_buffer_offset);
	}
	private static native void nglPolygonStippleBO(int mask_buffer_offset);

	public static native void glMatrixMode(int mode);

	public static native boolean glIsTexture(int texture);

	public static void glLight(int light, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglLightiv(light, pname, params, params.position());
	}
	private static native void nglLightiv(int light, int pname, IntBuffer params, int params_position);

	public static void glLight(int light, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglLightfv(light, pname, params, params.position());
	}
	private static native void nglLightfv(int light, int pname, FloatBuffer params, int params_position);

	public static native void glLighti(int light, int pname, int param);

	public static native void glLightf(int light, int pname, float param);

	public static void glLightModel(int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglLightModeliv(pname, params, params.position());
	}
	private static native void nglLightModeliv(int pname, IntBuffer params, int params_position);

	public static void glLightModel(int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglLightModelfv(pname, params, params.position());
	}
	private static native void nglLightModelfv(int pname, FloatBuffer params, int params_position);

	public static native void glLightModeli(int pname, int param);

	public static native void glLightModelf(int pname, float param);

	public static native void glLineStipple(int factor, short pattern);

	public static native void glLineWidth(float width);

	public static native void glListBase(int base);

	public static native void glLoadIdentity();

	public static void glLoadMatrix(FloatBuffer m) {
		BufferChecks.checkBuffer(m, 16);
		nglLoadMatrixf(m, m.position());
	}
	private static native void nglLoadMatrixf(FloatBuffer m, int m_position);

	public static native void glLoadName(int name);

	public static native void glLogicOp(int opcode);

	public static void glMap1f(int target, float u1, float u2, int stride, int order, FloatBuffer points) {
		BufferChecks.checkDirect(points);
		nglMap1f(target, u1, u2, stride, order, points, points.position());
	}
	private static native void nglMap1f(int target, float u1, float u2, int stride, int order, FloatBuffer points, int points_position);

	public static void glMap2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points) {
		BufferChecks.checkDirect(points);
		nglMap2f(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, points, points.position());
	}
	private static native void nglMap2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points, int points_position);

	public static native void glMapGrid2f(int un, float u1, float u2, int vn, float v1, float v2);

	public static native void glMapGrid1f(int un, float u1, float u2);

	public static void glMaterial(int face, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglMaterialiv(face, pname, params, params.position());
	}
	private static native void nglMaterialiv(int face, int pname, IntBuffer params, int params_position);

	public static void glMaterial(int face, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglMaterialfv(face, pname, params, params.position());
	}
	private static native void nglMaterialfv(int face, int pname, FloatBuffer params, int params_position);

	public static native void glMateriali(int face, int pname, int param);

	public static native void glMaterialf(int face, int pname, float param);

	public static native boolean glIsList(int list);

	public static void glGetPolygonStipple(ByteBuffer mask) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(mask, 1024);
		nglGetPolygonStipple(mask, mask.position());
	}
	private static native void nglGetPolygonStipple(ByteBuffer mask, int mask_position);
	public static void glGetPolygonStipple(int mask_buffer_offset) {
		GLBufferChecks.ensurePackPBOenabled();
		nglGetPolygonStippleBO(mask_buffer_offset);
	}
	private static native void nglGetPolygonStippleBO(int mask_buffer_offset);

	public static native java.lang.String glGetString(int name);

	public static void glGetTexEnv(int coord, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetTexEnvfv(coord, pname, params, params.position());
	}
	private static native void nglGetTexEnvfv(int coord, int pname, FloatBuffer params, int params_position);

	public static void glGetTexEnv(int coord, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetTexEnviv(coord, pname, params, params.position());
	}
	private static native void nglGetTexEnviv(int coord, int pname, IntBuffer params, int params_position);

	public static void glGetTexGen(int coord, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetTexGenfv(coord, pname, params, params.position());
	}
	private static native void nglGetTexGenfv(int coord, int pname, FloatBuffer params, int params_position);

	public static void glGetTexGen(int coord, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetTexGeniv(coord, pname, params, params.position());
	}
	private static native void nglGetTexGeniv(int coord, int pname, IntBuffer params, int params_position);

	public static void glGetTexImage(int target, int level, int format, int type, ShortBuffer pixels) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
		nglGetTexImage(target, level, format, type, pixels, pixels.position() << 1);
	}
	public static void glGetTexImage(int target, int level, int format, int type, IntBuffer pixels) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
		nglGetTexImage(target, level, format, type, pixels, pixels.position() << 2);
	}
	public static void glGetTexImage(int target, int level, int format, int type, ByteBuffer pixels) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, 1, 1, 1));
		nglGetTexImage(target, level, format, type, pixels, pixels.position());
	}
	private static native void nglGetTexImage(int target, int level, int format, int type, Buffer pixels, int pixels_position);
	public static void glGetTexImage(int target, int level, int format, int type, int pixels_buffer_offset) {
		GLBufferChecks.ensurePackPBOenabled();
		nglGetTexImageBO(target, level, format, type, pixels_buffer_offset);
	}
	private static native void nglGetTexImageBO(int target, int level, int format, int type, int pixels_buffer_offset);

	public static void glGetTexLevelParameter(int target, int level, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetTexLevelParameteriv(target, level, pname, params, params.position());
	}
	private static native void nglGetTexLevelParameteriv(int target, int level, int pname, IntBuffer params, int params_position);

	public static void glGetTexLevelParameter(int target, int level, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetTexLevelParameterfv(target, level, pname, params, params.position());
	}
	private static native void nglGetTexLevelParameterfv(int target, int level, int pname, FloatBuffer params, int params_position);

	public static void glGetTexParameter(int target, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetTexParameteriv(target, pname, params, params.position());
	}
	private static native void nglGetTexParameteriv(int target, int pname, IntBuffer params, int params_position);

	public static void glGetTexParameter(int target, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetTexParameterfv(target, pname, params, params.position());
	}
	private static native void nglGetTexParameterfv(int target, int pname, FloatBuffer params, int params_position);

	public static native void glHint(int target, int mode);

	public static native void glInitNames();

	public static void glInterleavedArrays(int format, int stride, ShortBuffer pointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pointer);
		nglInterleavedArrays(format, stride, pointer, pointer.position() << 1);
	}
	public static void glInterleavedArrays(int format, int stride, IntBuffer pointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pointer);
		nglInterleavedArrays(format, stride, pointer, pointer.position() << 2);
	}
	public static void glInterleavedArrays(int format, int stride, FloatBuffer pointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pointer);
		nglInterleavedArrays(format, stride, pointer, pointer.position() << 2);
	}
	public static void glInterleavedArrays(int format, int stride, ByteBuffer pointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pointer);
		nglInterleavedArrays(format, stride, pointer, pointer.position());
	}
	private static native void nglInterleavedArrays(int format, int stride, Buffer pointer, int pointer_position);
	public static void glInterleavedArrays(int format, int stride, int pointer_buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglInterleavedArraysBO(format, stride, pointer_buffer_offset);
	}
	private static native void nglInterleavedArraysBO(int format, int stride, int pointer_buffer_offset);

	public static native boolean glIsEnabled(int cap);

	public static java.nio.ByteBuffer glGetPointer(int pname, int result_size) {
		java.nio.ByteBuffer __result = nglGetPointerv(pname, result_size);
		return __result;
	}
	private static native java.nio.ByteBuffer nglGetPointerv(int pname, int result_size);

	public static native void glFinish();

	public static native void glFlush();

	public static void glFog(int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglFogiv(pname, params, params.position());
	}
	private static native void nglFogiv(int pname, IntBuffer params, int params_position);

	public static void glFog(int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglFogfv(pname, params, params.position());
	}
	private static native void nglFogfv(int pname, FloatBuffer params, int params_position);

	public static native void glFogi(int pname, int param);

	public static native void glFogf(int pname, float param);

	public static native void glFrontFace(int mode);

	public static native void glFrustum(double left, double right, double bottom, double top, double zNear, double zFar);

	public static native int glGenLists(int range);

	public static void glGenTextures(IntBuffer textures) {
		BufferChecks.checkDirect(textures);
		nglGenTextures((textures.remaining()), textures, textures.position());
	}
	private static native void nglGenTextures(int n, IntBuffer textures, int textures_position);

	public static void glGetInteger(int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 16);
		nglGetIntegerv(pname, params, params.position());
	}
	private static native void nglGetIntegerv(int pname, IntBuffer params, int params_position);

	public static void glGetFloat(int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 16);
		nglGetFloatv(pname, params, params.position());
	}
	private static native void nglGetFloatv(int pname, FloatBuffer params, int params_position);

	public static void glGetDouble(int pname, DoubleBuffer params) {
		BufferChecks.checkBuffer(params, 16);
		nglGetDoublev(pname, params, params.position());
	}
	private static native void nglGetDoublev(int pname, DoubleBuffer params, int params_position);

	public static void glGetBoolean(int pname, ByteBuffer params) {
		BufferChecks.checkBuffer(params, 16);
		nglGetBooleanv(pname, params, params.position());
	}
	private static native void nglGetBooleanv(int pname, ByteBuffer params, int params_position);

	public static void glGetClipPlane(int plane, DoubleBuffer equation) {
		BufferChecks.checkBuffer(equation, 4);
		nglGetClipPlane(plane, equation, equation.position());
	}
	private static native void nglGetClipPlane(int plane, DoubleBuffer equation, int equation_position);

	public static native int glGetError();

	public static void glGetLight(int light, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetLightiv(light, pname, params, params.position());
	}
	private static native void nglGetLightiv(int light, int pname, IntBuffer params, int params_position);

	public static void glGetLight(int light, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetLightfv(light, pname, params, params.position());
	}
	private static native void nglGetLightfv(int light, int pname, FloatBuffer params, int params_position);

	public static void glGetMap(int target, int query, IntBuffer v) {
		BufferChecks.checkBuffer(v, 256);
		nglGetMapiv(target, query, v, v.position());
	}
	private static native void nglGetMapiv(int target, int query, IntBuffer v, int v_position);

	public static void glGetMap(int target, int query, FloatBuffer v) {
		BufferChecks.checkBuffer(v, 256);
		nglGetMapfv(target, query, v, v.position());
	}
	private static native void nglGetMapfv(int target, int query, FloatBuffer v, int v_position);

	public static void glGetMaterial(int face, int pname, IntBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetMaterialiv(face, pname, params, params.position());
	}
	private static native void nglGetMaterialiv(int face, int pname, IntBuffer params, int params_position);

	public static void glGetMaterial(int face, int pname, FloatBuffer params) {
		BufferChecks.checkBuffer(params, 4);
		nglGetMaterialfv(face, pname, params, params.position());
	}
	private static native void nglGetMaterialfv(int face, int pname, FloatBuffer params, int params_position);

	public static void glGetPixelMapu(int map, ShortBuffer values) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(values, 256);
		nglGetPixelMapusv(map, values, values.position());
	}
	private static native void nglGetPixelMapusv(int map, ShortBuffer values, int values_position);
	public static void glGetPixelMapusv(int map, int values_buffer_offset) {
		GLBufferChecks.ensurePackPBOenabled();
		nglGetPixelMapusvBO(map, values_buffer_offset);
	}
	private static native void nglGetPixelMapusvBO(int map, int values_buffer_offset);

	public static void glGetPixelMapu(int map, IntBuffer values) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(values, 256);
		nglGetPixelMapuiv(map, values, values.position());
	}
	private static native void nglGetPixelMapuiv(int map, IntBuffer values, int values_position);
	public static void glGetPixelMapuiv(int map, int values_buffer_offset) {
		GLBufferChecks.ensurePackPBOenabled();
		nglGetPixelMapuivBO(map, values_buffer_offset);
	}
	private static native void nglGetPixelMapuivBO(int map, int values_buffer_offset);

	public static void glGetPixelMap(int map, FloatBuffer values) {
		GLBufferChecks.ensurePackPBOdisabled();
		BufferChecks.checkBuffer(values, 256);
		nglGetPixelMapfv(map, values, values.position());
	}
	private static native void nglGetPixelMapfv(int map, FloatBuffer values, int values_position);
	public static void glGetPixelMapfv(int map, int values_buffer_offset) {
		GLBufferChecks.ensurePackPBOenabled();
		nglGetPixelMapfvBO(map, values_buffer_offset);
	}
	private static native void nglGetPixelMapfvBO(int map, int values_buffer_offset);

	public static void glFeedbackBuffer(int type, FloatBuffer buffer) {
		BufferChecks.checkDirect(buffer);
		nglFeedbackBuffer((buffer.remaining()), type, buffer, buffer.position());
	}
	private static native void nglFeedbackBuffer(int size, int type, FloatBuffer buffer, int buffer_position);

	public static native void glDepthFunc(int func);

	public static native void glDepthMask(boolean flag);

	public static native void glDepthRange(double zNear, double zFar);

	public static native void glDrawArrays(int mode, int first, int count);

	public static native void glDrawBuffer(int mode);

	public static void glDrawElements(int mode, IntBuffer indices) {
		GLBufferChecks.ensureElementVBOdisabled();
		BufferChecks.checkDirect(indices);
		nglDrawElements(mode, (indices.remaining()), GL11.GL_UNSIGNED_INT, indices, indices.position() << 2);
	}
	public static void glDrawElements(int mode, ShortBuffer indices) {
		GLBufferChecks.ensureElementVBOdisabled();
		BufferChecks.checkDirect(indices);
		nglDrawElements(mode, (indices.remaining()), GL11.GL_UNSIGNED_SHORT, indices, indices.position() << 1);
	}
	public static void glDrawElements(int mode, ByteBuffer indices) {
		GLBufferChecks.ensureElementVBOdisabled();
		BufferChecks.checkDirect(indices);
		nglDrawElements(mode, (indices.remaining()), GL11.GL_UNSIGNED_BYTE, indices, indices.position());
	}
	private static native void nglDrawElements(int mode, int count, int type, Buffer indices, int indices_position);
	public static void glDrawElements(int mode, int count, int type, int indices_buffer_offset) {
		GLBufferChecks.ensureElementVBOenabled();
		nglDrawElementsBO(mode, count, type, indices_buffer_offset);
	}
	private static native void nglDrawElementsBO(int mode, int count, int type, int indices_buffer_offset);

	public static void glDrawPixels(int width, int height, int format, int type, ShortBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, width, height, 1));
		nglDrawPixels(width, height, format, type, pixels, pixels.position() << 1);
	}
	public static void glDrawPixels(int width, int height, int format, int type, IntBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, width, height, 1));
		nglDrawPixels(width, height, format, type, pixels, pixels.position() << 2);
	}
	public static void glDrawPixels(int width, int height, int format, int type, ByteBuffer pixels) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(pixels, GLBufferChecks.calculateImageStorage(pixels, format, type, width, height, 1));
		nglDrawPixels(width, height, format, type, pixels, pixels.position());
	}
	private static native void nglDrawPixels(int width, int height, int format, int type, Buffer pixels, int pixels_position);
	public static void glDrawPixels(int width, int height, int format, int type, int pixels_buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglDrawPixelsBO(width, height, format, type, pixels_buffer_offset);
	}
	private static native void nglDrawPixelsBO(int width, int height, int format, int type, int pixels_buffer_offset);

	public static native void glEdgeFlag(boolean flag);

	public static void glEdgeFlagPointer(int stride, ByteBuffer pointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pointer);
		nglEdgeFlagPointer(stride, pointer, pointer.position());
	}
	private static native void nglEdgeFlagPointer(int stride, Buffer pointer, int pointer_position);
	public static void glEdgeFlagPointer(int stride, int pointer_buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglEdgeFlagPointerBO(stride, pointer_buffer_offset);
	}
	private static native void nglEdgeFlagPointerBO(int stride, int pointer_buffer_offset);

	public static native void glDisable(int cap);

	public static native void glEnable(int cap);

	public static native void glDisableClientState(int cap);

	public static native void glEnableClientState(int cap);

	public static native void glEvalCoord2f(float u, float v);

	public static native void glEvalCoord1f(float u);

	public static native void glEvalMesh2(int mode, int i1, int i2, int j1, int j2);

	public static native void glEvalMesh1(int mode, int i1, int i2);

	public static native void glEvalPoint2(int i, int j);

	public static native void glEvalPoint1(int i);

	public static native void glClearIndex(float c);

	public static native void glClearStencil(int s);

	public static void glClipPlane(int plane, DoubleBuffer equation) {
		BufferChecks.checkBuffer(equation, 4);
		nglClipPlane(plane, equation, equation.position());
	}
	private static native void nglClipPlane(int plane, DoubleBuffer equation, int equation_position);

	public static native void glColor4ub(byte red, byte green, byte blue, byte alpha);

	public static native void glColor4f(float red, float green, float blue, float alpha);

	public static native void glColor4b(byte red, byte green, byte blue, byte alpha);

	public static native void glColor3ub(byte red, byte green, byte blue);

	public static native void glColor3f(float red, float green, float blue);

	public static native void glColor3b(byte red, byte green, byte blue);

	public static native void glColorMask(boolean red, boolean green, boolean blue, boolean alpha);

	public static native void glColorMaterial(int face, int mode);

	public static void glColorPointer(int size, int stride, FloatBuffer pointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pointer);
		nglColorPointer(size, GL11.GL_FLOAT, stride, pointer, pointer.position() << 2);
	}
	public static void glColorPointer(int size, boolean unsigned, int stride, ByteBuffer pointer) {
		GLBufferChecks.ensureArrayVBOdisabled();
		BufferChecks.checkDirect(pointer);
		nglColorPointer(size, unsigned ? GL11.GL_UNSIGNED_BYTE : GL11.GL_BYTE, stride, pointer, pointer.position());
	}
	private static native void nglColorPointer(int size, int type, int stride, Buffer pointer, int pointer_position);
	public static void glColorPointer(int size, int type, int stride, int pointer_buffer_offset) {
		GLBufferChecks.ensureArrayVBOenabled();
		nglColorPointerBO(size, type, stride, pointer_buffer_offset);
	}
	private static native void nglColorPointerBO(int size, int type, int stride, int pointer_buffer_offset);

	public static native void glCopyPixels(int x, int y, int width, int height, int type);

	public static native void glCopyTexImage1D(int target, int level, int internalFormat, int x, int y, int width, int border);

	public static native void glCopyTexImage2D(int target, int level, int internalFormat, int x, int y, int width, int height, int border);

	public static native void glCopyTexSubImage1D(int target, int level, int xoffset, int x, int y, int width);

	public static native void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height);

	public static native void glCullFace(int mode);

	public static void glDeleteTextures(IntBuffer textures) {
		BufferChecks.checkDirect(textures);
		nglDeleteTextures((textures.remaining()), textures, textures.position());
	}
	private static native void nglDeleteTextures(int n, IntBuffer textures, int textures_position);

	public static native void glDeleteLists(int list, int range);

	public static native void glClearDepth(double depth);

	public static native void glArrayElement(int i);

	public static native void glEnd();

	public static native void glBegin(int mode);

	public static native void glBindTexture(int target, int texture);

	public static void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, ByteBuffer bitmap) {
		GLBufferChecks.ensureUnpackPBOdisabled();
		BufferChecks.checkBuffer(bitmap, (((width + 7)/8)*height));
		nglBitmap(width, height, xorig, yorig, xmove, ymove, bitmap, bitmap.position());
	}
	private static native void nglBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, ByteBuffer bitmap, int bitmap_position);
	public static void glBitmap(int width, int height, float xorig, float yorig, float xmove, float ymove, int bitmap_buffer_offset) {
		GLBufferChecks.ensureUnpackPBOenabled();
		nglBitmapBO(width, height, xorig, yorig, xmove, ymove, bitmap_buffer_offset);
	}
	private static native void nglBitmapBO(int width, int height, float xorig, float yorig, float xmove, float ymove, int bitmap_buffer_offset);

	public static native void glBlendFunc(int sfactor, int dfactor);

	public static native void glCallList(int list);

	public static void glCallLists(IntBuffer lists) {
		BufferChecks.checkDirect(lists);
		nglCallLists((lists.remaining()), GL11.GL_UNSIGNED_INT, lists, lists.position() << 2);
	}
	public static void glCallLists(ShortBuffer lists) {
		BufferChecks.checkDirect(lists);
		nglCallLists((lists.remaining()), GL11.GL_UNSIGNED_SHORT, lists, lists.position() << 1);
	}
	public static void glCallLists(ByteBuffer lists) {
		BufferChecks.checkDirect(lists);
		nglCallLists((lists.remaining()), GL11.GL_UNSIGNED_BYTE, lists, lists.position());
	}
	private static native void nglCallLists(int n, int type, Buffer lists, int lists_position);

	public static native void glClear(int mask);

	public static native void glClearAccum(float red, float green, float blue, float alpha);

	public static native void glClearColor(float red, float green, float blue, float alpha);

	public static native void glAlphaFunc(int func, float ref);

	public static native void glAccum(int op, float value);
}
