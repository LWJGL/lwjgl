package org.lwjgl.d3d;

public class Direct3DConstants {
    public static final int D3DADAPTER_DEFAULT = 0;
    public static final int D3DPRESENT_BACK_BUFFERS_MAX = 3;
    public static final int D3DENUM_WHQL_LEVEL = 0x00000002;
    public static final int MAX_DEVICE_IDENTIFIER_STRING = 512;
    //D3DDEVTYPE
    public static final int D3DDEVTYPE_HAL = 1;
    public static final int D3DDEVTYPE_NULLREF = 4;
    public static final int D3DDEVTYPE_REF = 2;
    public static final int D3DDEVTYPE_SW = 3;
    public static final int D3DDEVTYPE_FORCE_DWORD = 0xffffffff;
    //D3DFORMAT
    public static final int D3DFMT_UNKNOWN              =  0;
    public static final int D3DFMT_R8G8B8               = 20;
    public static final int D3DFMT_A8R8G8B8             = 21;
    public static final int D3DFMT_X8R8G8B8             = 22;
    public static final int D3DFMT_R5G6B5               = 23;
    public static final int D3DFMT_X1R5G5B5             = 24;
    public static final int D3DFMT_A1R5G5B5             = 25;
    public static final int D3DFMT_A4R4G4B4             = 26;
    public static final int D3DFMT_R3G3B2               = 27;
    public static final int D3DFMT_A8                   = 28;
    public static final int D3DFMT_A8R3G3B2             = 29;
    public static final int D3DFMT_X4R4G4B4             = 30;
    public static final int D3DFMT_A2B10G10R10          = 31;
    public static final int D3DFMT_G16R16               = 34;
    public static final int D3DFMT_A8P8                 = 40;
    public static final int D3DFMT_P8                   = 41;
    public static final int D3DFMT_L8                   = 50;
    public static final int D3DFMT_A8L8                 = 51;
    public static final int D3DFMT_A4L4                 = 52;
    public static final int D3DFMT_V8U8                 = 60;
    public static final int D3DFMT_L6V5U5               = 61;
    public static final int D3DFMT_X8L8V8U8             = 62;
    public static final int D3DFMT_Q8W8V8U8             = 63;
    public static final int D3DFMT_V16U16               = 64;
    public static final int D3DFMT_W11V11U10            = 65;
    public static final int D3DFMT_A2W10V10U10          = 67;
    public static final int D3DFMT_UYVY                 = MAKEFOURCC('U', 'Y', 'V', 'Y');
    public static final int D3DFMT_YUY2                 = MAKEFOURCC('Y', 'U', 'Y', '2');
    public static final int D3DFMT_DXT1                 = MAKEFOURCC('D', 'X', 'T', '1');
    public static final int D3DFMT_DXT2                 = MAKEFOURCC('D', 'X', 'T', '2');
    public static final int D3DFMT_DXT3                 = MAKEFOURCC('D', 'X', 'T', '3');
    public static final int D3DFMT_DXT4                 = MAKEFOURCC('D', 'X', 'T', '4');
    public static final int D3DFMT_DXT5                 = MAKEFOURCC('D', 'X', 'T', '5');
    public static final int D3DFMT_D16_LOCKABLE         = 70;
    public static final int D3DFMT_D32                  = 71;
    public static final int D3DFMT_D15S1                = 73;
    public static final int D3DFMT_D24S8                = 75;
    public static final int D3DFMT_D16                  = 80;
    public static final int D3DFMT_D24X8                = 77;
    public static final int D3DFMT_D24X4S4              = 79;
    public static final int D3DFMT_VERTEXDATA           =100;
    public static final int D3DFMT_INDEX16              =101;
    public static final int D3DFMT_INDEX32              =102;
    public static final int D3DFMT_FORCE_DWORD          =0x7fffffff;
    //D3DRESOURCETYPE
    public static final int D3DRTYPE_SURFACE = 1;
    public static final int D3DRTYPE_VOLUME = 2;
    public static final int D3DRTYPE_TEXTURE = 3;
    public static final int D3DRTYPE_VOLUMETEXTURE = 4;
    public static final int D3DRTYPE_CubeTexture = 5;
    public static final int D3DRTYPE_VERTEXBUFFER = 6;
    public static final int D3DRTYPE_INDEXBUFFER = 7;
    public static final int D3DRTYPE_FORCE_DWORD = 0x7fffffff;
    //D3DUSAGE
    public static final int D3DUSAGE_RENDERTARGET = 0x00000001;
    public static final int D3DUSAGE_DEPTHSTENCIL = 0x00000002;
    public static final int D3DUSAGE_AUTOGENMIPMAP = 0x00000400;
    public static final int D3DUSAGE_DMAP = 0x00004000;
    //The following usages are valid only for querying CheckDeviceFormat
    public static final int D3DUSAGE_QUERY_LEGACYBUMPMAP = 0x00008000;
    public static final int D3DUSAGE_QUERY_SRGBREAD = 0x00010000;
    public static final int D3DUSAGE_QUERY_FILTER = 0x00020000;
    public static final int D3DUSAGE_QUERY_SRGBWRITE = 0x00040000;
    public static final int D3DUSAGE_QUERY_POSTPIXELSHADER_BLENDING = 0x00080000;
    public static final int D3DUSAGE_QUERY_VERTEXTEXTURE = 0x00100000;
    public static final int D3DUSAGE_QUERY_WRAPANDMIP = 0x00200000;
    /* Usages for Vertex/Index buffers */
    public static final int D3DUSAGE_WRITEONLY = 0x00000008;
    public static final int D3DUSAGE_SOFTWAREPROCESSING = 0x00000010;
    public static final int D3DUSAGE_DONOTCLIP = 0x00000020;
    public static final int D3DUSAGE_POINTS = 0x00000040;
    public static final int D3DUSAGE_RTPATCHES = 0x00000080;
    public static final int D3DUSAGE_NPATCHES = 0x00000100;
    //D3DMULTISAMPLE_TYPE
    public static final int D3DMULTISAMPLE_NONE = 0;
    public static final int D3DMULTISAMPLE_NONMASKABLE  = 1;
    public static final int D3DMULTISAMPLE_2_SAMPLES = 2;
    public static final int D3DMULTISAMPLE_3_SAMPLES = 3;
    public static final int D3DMULTISAMPLE_4_SAMPLES = 4;
    public static final int D3DMULTISAMPLE_5_SAMPLES = 5;
    public static final int D3DMULTISAMPLE_6_SAMPLES = 6;
    public static final int D3DMULTISAMPLE_7_SAMPLES = 7;
    public static final int D3DMULTISAMPLE_8_SAMPLES = 8;
    public static final int D3DMULTISAMPLE_9__SAMPLES = 9;
    public static final int D3DMULTISAMPLE_10_SAMPLES = 10;
    public static final int D3DMULTISAMPLE_11_SAMPLES = 11;
    public static final int D3DMULTISAMPLE_12_SAMPLES = 12;
    public static final int D3DMULTISAMPLE_13_SAMPLES = 13;
    public static final int D3DMULTISAMPLE_14_SAMPLES = 14;
    public static final int D3DMULTISAMPLE_15_SAMPLES = 15;
    public static final int D3DMULTISAMPLE_16_SAMPLES = 16;
    public static final int D3DMULTISAMPLE_FORCE_DWORD = 0xffffffff;
    //D3DSWAPEFFECT
    public static final int D3DSWAPEFFECT_DISCARD = 1;
    public static final int D3DSWAPEFFECT_FLIP = 2;
    public static final int D3DSWAPEFFECT_COPY = 3;
    public static final int D3DSWAPEFFECT_FORCE_DWORD = 0xFFFFFFFF;
    //D3DPOOL
    public static final int D3DPOOL_DEFAULT = 0;
    public static final int D3DPOOL_MANAGED = 1;
    public static final int D3DPOOL_SYSTEMMEM = 2;
    public static final int D3DPOOL_SCRATCH = 3;
    public static final int D3DPOOL_FORCE_DWORD = 0x7fffffff;
    //D3DQUERYTYPE
    public static final int D3DQUERYTYPE_VCACHE = 4;
    public static final int D3DQUERYTYPE_ResourceManager = 5;
    public static final int D3DQUERYTYPE_VERTEXSTATS = 6;
    public static final int D3DQUERYTYPE_EVENT = 8;
    public static final int D3DQUERYTYPE_OCCLUSION = 9;
    public static final int D3DQUERYTYPE_TIMESTAMP = 10;
    public static final int D3DQUERYTYPE_TIMESTAMPDISJOINT = 11;
    public static final int D3DQUERYTYPE_TIMESTAMPFREQ = 12;
    public static final int D3DQUERYTYPE_PIPELINETIMINGS = 13;
    public static final int D3DQUERYTYPE_INTERFACETIMINGS = 14;
    public static final int D3DQUERYTYPE_VERTEXTIMINGS = 15;
    public static final int D3DQUERYTYPE_PIXELTIMINGS = 16;
    public static final int D3DQUERYTYPE_BANDWIDTHTIMINGS = 17;
    public static final int D3DQUERYTYPE_CACHEUTILIZATION = 18;
    //D3DSTATEBLOCKTYPE
    public static final int D3DSBT_ALL = 1;
    public static final int D3DSBT_PIXELSTATE = 2;
    public static final int D3DSBT_VERTEXSTATE = 2;
    public static final int D3DSBT_FORCE_DWORD = 0xffffffff;
    //D3DPRIMITIVETYPE
    public static final int D3DPT_POINTLIST = 1;
    public static final int D3DPT_LINELIST = 2;
    public static final int D3DPT_LINESTRIP = 3;
    public static final int D3DPT_TRIANGLELIST = 4;
    public static final int D3DPT_TRIANGLESTRIP = 5;
    public static final int D3DPT_TRIANGLEFAN = 6;
    public static final int D3DPT_FORCE_DWORD = 0x7fffffff;
    //D3DBASISTYPE
    public static final int D3DBASIS_BEZIER = 0;
    public static final int D3DBASIS_BSPLINE = 1;
    public static final int D3DBASIS_CATMULL_ROM = 2;
    public static final int D3DBASIS_FORCE_DWORD = 0x7fffffff;
    //D3DDEGREETYPE
    public static final int D3DDEGREE_LINEAR = 1;
    public static final int D3DDEGREE_QUADRATIC = 2;
    public static final int D3DDEGREE_CUBIC = 3;
    public static final int D3DDEGREE_QUINTIC = 5;
    public static final int D3DDEGREE_FORCE_DWORD = 0x7fffffff;
    //D3DBACKBUFFER_TYPE
    public static final int D3DBACKBUFFER_TYPE_MONO = 0;
    public static final int D3DBACKBUFFER_TYPE_FORCE_DWORD = 0x7fffffff;
    //D3DLIGHTTYPE
    public static final int D3DLIGHT_POINT = 1;
    public static final int D3DLIGHT_SPOT = 2;
    public static final int D3DLIGHT_DIRECTIONAL = 3;
    public static final int D3DLIGHT_FORCE_DWORD = 0x7fffffff;
    //D3DRENDERSTATETYPE
    public static final int D3DRS_ZENABLE = 7;
    public static final int D3DRS_FILLMODE = 8;
    public static final int D3DRS_SHADEMODE = 9;
    public static final int D3DRS_ZWRITEENABLE = 14;
    public static final int D3DRS_ALPHATESTENABLE = 15;
    public static final int D3DRS_LASTPIXEL = 16;
    public static final int D3DRS_SRCBLEND = 19;
    public static final int D3DRS_DESTBLEND = 20;
    public static final int D3DRS_CULLMODE = 22;
    public static final int D3DRS_ZFUNC = 23;
    public static final int D3DRS_ALPHAREF = 24;
    public static final int D3DRS_ALPHAFUNC = 25;
    public static final int D3DRS_DITHERENABLE = 26;
    public static final int D3DRS_ALPHABLENDENABLE = 27;
    public static final int D3DRS_FOGENABLE = 28;
    public static final int D3DRS_SPECULARENABLE = 29;
    public static final int D3DRS_FOGCOLOR = 34;
    public static final int D3DRS_FOGTABLEMODE = 35;
    public static final int D3DRS_FOGSTART = 36;
    public static final int D3DRS_FOGEND = 37;
    public static final int D3DRS_FOGDENSITY = 38;
    public static final int D3DRS_RANGEFOGENABLE = 48;
    public static final int D3DRS_STENCILENABLE = 52;
    public static final int D3DRS_STENCILFAIL = 53;
    public static final int D3DRS_STENCILZFAIL = 54;
    public static final int D3DRS_STENCILPASS = 55;
    public static final int D3DRS_STENCILFUNC = 56;
    public static final int D3DRS_STENCILREF = 57;
    public static final int D3DRS_STENCILMASK = 58;
    public static final int D3DRS_STENCILWRITEMASK = 59;
    public static final int D3DRS_TEXTUREFACTOR = 60;
    public static final int D3DRS_WRAP0 = 128;
    public static final int D3DRS_WRAP1 = 129;
    public static final int D3DRS_WRAP2 = 130;
    public static final int D3DRS_WRAP3 = 131;
    public static final int D3DRS_WRAP4 = 132;
    public static final int D3DRS_WRAP5 = 133;
    public static final int D3DRS_WRAP6 = 134;
    public static final int D3DRS_WRAP7 = 135;
    public static final int D3DRS_CLIPPING = 136;
    public static final int D3DRS_LIGHTING = 137;
    public static final int D3DRS_AMBIENT = 139;
    public static final int D3DRS_FOGVERTEXMODE = 140;
    public static final int D3DRS_COLORVERTEX = 141;
    public static final int D3DRS_LOCALVIEWER = 142;
    public static final int D3DRS_NORMALIZENORMALS = 143;
    public static final int D3DRS_DIFFUSEMATERIALSOURCE = 145;
    public static final int D3DRS_SPECULARMATERIALSOURCE = 146;
    public static final int D3DRS_AMBIENTMATERIALSOURCE = 147;
    public static final int D3DRS_EMISSIVEMATERIALSOURCE = 148;
    public static final int D3DRS_VERTEXBLEND = 151;
    public static final int D3DRS_CLIPPLANEENABLE = 152;
    public static final int D3DRS_POINTSIZE = 154;
    public static final int D3DRS_POINTSIZE_MIN = 155;
    public static final int D3DRS_POINTSPRITEENABLE = 156;
    public static final int D3DRS_POINTSCALEENABLE = 157;
    public static final int D3DRS_POINTSCALE_A = 158;
    public static final int D3DRS_POINTSCALE_B = 159;
    public static final int D3DRS_POINTSCALE_C = 160;
    public static final int D3DRS_MULTISAMPLEANTIALIAS = 161;
    public static final int D3DRS_MULTISAMPLEMASK = 162;
    public static final int D3DRS_PATCHEDGESTYLE = 163;
    public static final int D3DRS_DEBUGMONITORTOKEN = 165;
    public static final int D3DRS_POINTSIZE_MAX = 166;
    public static final int D3DRS_INDEXEDVERTEXBLENDENABLE = 167;
    public static final int D3DRS_COLORWRITEENABLE = 168;
    public static final int D3DRS_TWEENFACTOR = 170;
    public static final int D3DRS_BLENDOP = 171;
    public static final int D3DRS_POSITIONDEGREE = 172;
    public static final int D3DRS_NORMALDEGREE = 173;
    public static final int D3DRS_SCISSORTESTENABLE = 174;
    public static final int D3DRS_SLOPESCALEDEPTHBIAS = 175;
    public static final int D3DRS_ANTIALIASEDLINEENABLE = 176;
    public static final int D3DRS_MINTESSELLATIONLEVEL = 178;
    public static final int D3DRS_MAXTESSELLATIONLEVEL = 179;
    public static final int D3DRS_ADAPTIVETESS_X = 180;
    public static final int D3DRS_ADAPTIVETESS_Y = 181;
    public static final int D3DRS_ADAPTIVETESS_Z = 182;
    public static final int D3DRS_ADAPTIVETESS_W = 183;
    public static final int D3DRS_ENABLEADAPTIVETESSELLATION = 184;
    public static final int D3DRS_TWOSIDEDSTENCILMODE = 185;
    public static final int D3DRS_CCW_STENCILFAIL = 186;
    public static final int D3DRS_CCW_STENCILZFAIL = 187;
    public static final int D3DRS_CCW_STENCILPASS = 188;
    public static final int D3DRS_CCW_STENCILFUNC = 189;
    public static final int D3DRS_COLORWRITEENABLE1 = 190;
    public static final int D3DRS_COLORWRITEENABLE2 = 191;
    public static final int D3DRS_COLORWRITEENABLE3 = 192;
    public static final int D3DRS_BLENDFACTOR = 193;
    public static final int D3DRS_SRGBWRITEENABLE = 194;
    public static final int D3DRS_DEPTHBIAS = 195;
    public static final int D3DRS_WRAP8 = 198;
    public static final int D3DRS_WRAP9 = 199;
    public static final int D3DRS_WRAP10 = 200;
    public static final int D3DRS_WRAP11 = 201;
    public static final int D3DRS_WRAP12 = 202;
    public static final int D3DRS_WRAP13 = 203;
    public static final int D3DRS_WRAP14 = 204;
    public static final int D3DRS_WRAP15 = 205;
    public static final int D3DRS_SEPARATEALPHABLENDENABLE = 206;
    public static final int D3DRS_SRCBLENDALPHA = 207;
    public static final int D3DRS_DESTBLENDALPHA = 208;
    public static final int D3DRS_BLENDOPALPHA = 209;
    public static final int D3DRS_FORCE_DWORD = 0x7fffffff;
    //D3DSAMPLERSTATETYPE
    public static final int D3DSAMP_ADDRESSU = 1;
    public static final int D3DSAMP_ADDRESSV = 2;
    public static final int D3DSAMP_ADDRESSW = 3;
    public static final int D3DSAMP_BORDERCOLOR = 4;
    public static final int D3DSAMP_MAGFILTER = 5;
    public static final int D3DSAMP_MINFILTER = 6;
    public static final int D3DSAMP_MIPFILTER = 7;
    public static final int D3DSAMP_MIPMAPLODBIAS = 8;
    public static final int D3DSAMP_MAXMIPLEVEL = 9;
    public static final int D3DSAMP_MAXANISOTROPY = 10;
    public static final int D3DSAMP_SRGBTEXTURE = 11;
    public static final int D3DSAMP_ELEMENTINDEX = 12;
    public static final int D3DSAMP_DMAPOFFSET = 13;
    public static final int D3DSAMP_FORCE_DWORD = 0x7fffffff;
    //D3DTEXTURESTAGESTATETYPE
    public static final int D3DTSS_COLOROP = 1;
    public static final int D3DTSS_COLORARG1 = 2;
    public static final int D3DTSS_COLORARG2 = 3;
    public static final int D3DTSS_ALPHAOP = 4;
    public static final int D3DTSS_ALPHAARG1 = 5;
    public static final int D3DTSS_ALPHAARG2 = 6;
    public static final int D3DTSS_BUMPENVMAT00 = 7;
    public static final int D3DTSS_BUMPENVMAT01 = 8;
    public static final int D3DTSS_BUMPENVMAT10 = 9;
    public static final int D3DTSS_BUMPENVMAT11 = 10;
    public static final int D3DTSS_TEXCOORDINDEX = 11;
    public static final int D3DTSS_BUMPENVLSCALE = 22;
    public static final int D3DTSS_BUMPENVLOFFSET = 23;
    public static final int D3DTSS_TEXTURETRANSFORMFLAGS = 24;
    public static final int D3DTSS_COLORARG0 = 26;
    public static final int D3DTSS_ALPHAARG0 = 27;
    public static final int D3DTSS_RESULTARG = 28;
    public static final int D3DTSS_CONSTANT = 32;
    public static final int D3DTSS_FORCE_DWORD = 0x7fffffff;
    public static final int D3DTS_WORLD = 256;
    //D3DTRANSFORMSTATETYPE
    public static final int D3DTS_VIEW = 2;
    public static final int D3DTS_PROJECTION = 3;
    public static final int D3DTS_TEXTURE0 = 16;
    public static final int D3DTS_TEXTURE1 = 17;
    public static final int D3DTS_TEXTURE2 = 18;
    public static final int D3DTS_TEXTURE3 = 19;
    public static final int D3DTS_TEXTURE4 = 20;
    public static final int D3DTS_TEXTURE5 = 21;
    public static final int D3DTS_TEXTURE6 = 22;
    public static final int D3DTS_TEXTURE7 = 23;
    public static final int D3DTS_FORCE_DWORD = 0x7fffffff;
    //D3DTEXTUREFILTERTYPE
    public static final int D3DTEXF_NONE = 0;
    public static final int D3DTEXF_POINT = 1;
    public static final int D3DTEXF_LINEAR = 2;
    public static final int D3DTEXF_ANISOTROPIC = 3;
    public static final int D3DTEXF_PYRAMIDALQUAD = 6;
    public static final int D3DTEXF_GAUSSIANQUAD = 7;
    public static final int D3DTEXF_CONVOLUTIONMONO = 8;
    public static final int D3DTEXF_FORCE_DWORD = 0x7fffffff;
    //D3DSCANLINEORDERING
    public static final int D3DSCANLINEORDERING_PROGRESSIVE = 1;
    public static final int D3DSCANLINEORDERING_INTERLACED = 2;
    //D3DDISPLAYROTATION
    public static final int D3DDISPLAYROTATION_IDENTITY = 1;
    public static final int D3DDISPLAYROTATION_90 = 2;
    public static final int D3DDISPLAYROTATION_180 = 2;
    public static final int D3DDISPLAYROTATION_270 = 2;
    //D3DCUBEMAP_FACES
    public static final int D3DCUBEMAP_FACE_POSITIVE_X = 0;
    public static final int D3DCUBEMAP_FACE_NEGATIVE_X = 1;
    public static final int D3DCUBEMAP_FACE_POSITIVE_Y = 2;
    public static final int D3DCUBEMAP_FACE_NEGATIVE_Y = 3;
    public static final int D3DCUBEMAP_FACE_POSITIVE_Z = 4;
    public static final int D3DCUBEMAP_FACE_NEGATIVE_Z = 5;
    public static final int D3DCUBEMAP_FACE_FORCE_DWORD = 0xffffffff;
    //D3DCOMPOSERECTSOP 
    public static final int D3DCOMPOSERECTS_COPY = 1;
    public static final int D3DCOMPOSERECTS_OR = 2;
    public static final int D3DCOMPOSERECTS_AND = 3;
    public static final int D3DCOMPOSERECTS_NEG = 4;
    public static final int D3DCOMPOSERECTS_FORCE_DWORD = 0x7fffffff;
    //D3DCREATE
    public static final int D3DCREATE_FPU_PRESERVE                  = 0x00000002;
    public static final int D3DCREATE_MULTITHREADED                 = 0x00000004;
    public static final int D3DCREATE_PUREDEVICE                    = 0x00000010;
    public static final int D3DCREATE_SOFTWARE_VERTEXPROCESSING     = 0x00000020;
    public static final int D3DCREATE_HARDWARE_VERTEXPROCESSING     = 0x00000040;
    public static final int D3DCREATE_MIXED_VERTEXPROCESSING        = 0x00000080;
    public static final int D3DCREATE_DISABLE_DRIVER_MANAGEMENT     = 0x00000100;
    public static final int D3DCREATE_ADAPTERGROUP_DEVICE           = 0x00000200;
    public static final int D3DCREATE_DISABLE_DRIVER_MANAGEMENT_EX  = 0x00000400;
    public static final int D3DCREATE_NOWINDOWCHANGES               = 0x00000800;
    public static final int D3DCREATE_DISABLE_PSGP_THREADING        = 0x00002000;
    public static final int D3DCREATE_ENABLE_PRESENTSTATS           = 0x00004000;
    public static final int D3DCREATE_DISABLE_PRINTSCREEN           = 0x00008000;
    public static final int D3DCREATE_SCREENSAVER                   = 0x10000000;
    //D3DERR
    public static final int D3D_OK = 0;
    public static final int D3DERR_WRONGTEXTUREFORMAT = MAKE_D3DHRESULT(2072);
    public static final int D3DERR_UNSUPPORTEDCOLOROPERATION = MAKE_D3DHRESULT(2073);
    public static final int D3DERR_UNSUPPORTEDCOLORARG = MAKE_D3DHRESULT(2074);
    public static final int D3DERR_UNSUPPORTEDALPHAOPERATION = MAKE_D3DHRESULT(2075);
    public static final int D3DERR_UNSUPPORTEDALPHAARG = MAKE_D3DHRESULT(2076);
    public static final int D3DERR_TOOMANYOPERATIONS = MAKE_D3DHRESULT(2077);
    public static final int D3DERR_CONFLICTINGTEXTUREFILTER = MAKE_D3DHRESULT(2078);
    public static final int D3DERR_UNSUPPORTEDFACTORVALUE = MAKE_D3DHRESULT(2079);
    public static final int D3DERR_CONFLICTINGRENDERSTATE = MAKE_D3DHRESULT(2081);
    public static final int D3DERR_UNSUPPORTEDTEXTUREFILTER = MAKE_D3DHRESULT(2082);
    public static final int D3DERR_CONFLICTINGTEXTUREPALETTE = MAKE_D3DHRESULT(2086);
    public static final int D3DERR_DRIVERINTERNALERROR = MAKE_D3DHRESULT(2087);
    public static final int D3DERR_NOTFOUND = MAKE_D3DHRESULT(2150);
    public static final int D3DERR_MOREDATA = MAKE_D3DHRESULT(2151);
    public static final int D3DERR_DEVICELOST = MAKE_D3DHRESULT(2152);
    public static final int D3DERR_DEVICENOTRESET = MAKE_D3DHRESULT(2153);
    public static final int D3DERR_NOTAVAILABLE = MAKE_D3DHRESULT(2154);
    public static final int D3DERR_OUTOFVIDEOMEMORY = MAKE_D3DHRESULT(380);
    public static final int D3DERR_INVALIDDEVICE = MAKE_D3DHRESULT(2155);
    public static final int D3DERR_INVALIDCALL = MAKE_D3DHRESULT(2156);
    public static final int D3DERR_DRIVERINVALIDCALL = MAKE_D3DHRESULT(2157);
    public static final int D3DERR_WASSTILLDRAWING = MAKE_D3DHRESULT(540);
    public static final int D3DOK_NOAUTOGEN = MAKE_D3DSTATUS(2159);
    //D3DFVF
    public static final int D3DFVF_RESERVED0 = 0x001;
    public static final int D3DFVF_POSITION_MASK = 0x400E;
    public static final int D3DFVF_XYZ = 0x002;
    public static final int D3DFVF_XYZRHW = 0x004;
    public static final int D3DFVF_XYZB1 = 0x006;
    public static final int D3DFVF_XYZB2 = 0x008;
    public static final int D3DFVF_XYZB3 = 0x00a;
    public static final int D3DFVF_XYZB4 = 0x00c;
    public static final int D3DFVF_XYZB5 = 0x00e;
    public static final int D3DFVF_XYZW = 0x4002;
    public static final int D3DFVF_NORMAL = 0x010;
    public static final int D3DFVF_PSIZE = 0x020;
    public static final int D3DFVF_DIFFUSE = 0x040;
    public static final int D3DFVF_SPECULAR = 0x080;
    public static final int D3DFVF_TEXCOUNT_MASK = 0xf00;
    public static final int D3DFVF_TEXCOUNT_SHIFT = 8;
    public static final int D3DFVF_TEX0 = 0x000;
    public static final int D3DFVF_TEX1 = 0x100;
    public static final int D3DFVF_TEX2 = 0x200;
    public static final int D3DFVF_TEX3 = 0x300;
    public static final int D3DFVF_TEX4 = 0x400;
    public static final int D3DFVF_TEX5 = 0x500;
    public static final int D3DFVF_TEX6 = 0x600;
    public static final int D3DFVF_TEX7 = 0x700;
    public static final int D3DFVF_TEX8 = 0x800;
    public static final int D3DFVF_LASTBETA_UBYTE4 = 0x1000;
    public static final int D3DFVF_LASTBETA_D3DCOLOR = 0x8000;
    public static final int D3DFVF_RESERVED2 = 0x6000;
    //D3DCLEAR
    public static final int D3DCLEAR_TARGET = 0x00000001;
    public static final int D3DCLEAR_ZBUFFER = 0x00000002;
    public static final int D3DCLEAR_STENCIL = 0x00000004;
    //D3DCULL
    public static final int D3DCULL_NONE = 1;
    public static final int D3DCULL_CW = 2;
    public static final int D3DCULL_CCW = 3;
    public static final int D3DCULL_FORCE_DWORD = 0x7fffffff; /* force 32-bit size enum */
    
    //D3DX
    public static final double D3DX_PI = 3.141592654;
    public static final double D3DX_1BYPI = 0.318309886;
    
    private static int MAKEFOURCC(char ch0, char ch1, char ch2, char ch3) {
        return ((((int)ch0)) | (((int)ch1) << 8) | (((int)ch2) << 16) | (((int)ch3) << 24 ));
    }
    
    private static int MAKE_D3DHRESULT(int code) {
        return MAKE_HRESULT( 1, 0x876, code );
    }
    private static int MAKE_D3DSTATUS(int code) {
        return MAKE_HRESULT( 0, 0x876, code );
    }
    
    private static int MAKE_HRESULT(int sev, int fac, int code) {
        return (int)(((long)(sev)<<31) | ((long)(fac)<<16)  | ((long)(code)));
    }
}
