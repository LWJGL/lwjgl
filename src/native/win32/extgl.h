/*
** License Applicability. Except to the extent portions of this file are
** made subject to an alternative license as permitted in the SGI Free
** Software License B, Version 1.1 (the "License"), the contents of this
** file are subject only to the provisions of the License. You may not use
** this file except in compliance with the License. You may obtain a copy
** of the License at Silicon Graphics, Inc., attn: Legal Services, 1600
** Amphitheatre Parkway, Mountain View, CA 94043-1351, or at:
** 
** http://oss.sgi.com/projects/FreeB
** 
** Note that, as provided in the License, the Software is distributed on an
** "AS IS" basis, with ALL EXPRESS AND IMPLIED WARRANTIES AND CONDITIONS
** DISCLAIMED, INCLUDING, WITHOUT LIMITATION, ANY IMPLIED WARRANTIES AND
** CONDITIONS OF MERCHANTABILITY, SATISFACTORY QUALITY, FITNESS FOR A
** PARTICULAR PURPOSE, AND NON-INFRINGEMENT.
** 
** Original Code. The Original Code is: OpenGL Sample Implementation,
** Version 1.2.1, released January 26, 2000, developed by Silicon Graphics,
** Inc. The Original Code is Copyright (c) 1991-2000 Silicon Graphics, Inc.
** Copyright in any portions created by third parties is as indicated
** elsewhere herein. All Rights Reserved.
** 
** Additional Notice Provisions: This software was created using the
** OpenGL(R) version 1.2.1 Sample Implementation published by SGI, but has
** not been independently verified as being compliant with the OpenGL(R)
** version 1.2.1 Specification.
*/

/* 
    Parts copyright (c) 2001-2002 Lev Povalahev

    GL_draw_range_elements support added by Benjamin Karaban
  
    levp@gmx.net

    http://www.uni-karlsruhe.de/~uli2/
*/
                
#ifndef __EXTGL_H__
#define __EXTGL_H__

/*-----------------------------------------*/
/*-----------------------------------------*/

#if defined(_WIN32) && !defined(APIENTRY)
#define WIN32_LEAN_AND_MEAN 1
#include <windows.h>
#endif

#include <GL/gl.h>

#ifndef APIENTRY
#define APIENTRY
#endif

#ifdef __cplusplus
extern "C" {
#endif

#define GL_VERSION_1_2                              1
#define GL_VERSION_1_3                              1
#define GL_ARB_imaging                              1

/* 1.2 and 1.3 enumerants */

#define GL_RESCALE_NORMAL                                       0x803A
#define GL_CLAMP_TO_EDGE                                        0x812F
#define GL_MAX_ELEMENTS_VERTICES                                0x80E8
#define GL_MAX_ELEMENTS_INDICES                                 0x80E9
#define GL_BGR                                                  0x80E0
#define GL_BGRA                                                 0x80E1
#define GL_UNSIGNED_BYTE_3_3_2                                  0x8032
#define GL_UNSIGNED_BYTE_2_3_3_REV                              0x8362
#define GL_UNSIGNED_SHORT_5_6_5                                 0x8363
#define GL_UNSIGNED_SHORT_5_6_5_REV                             0x8364
#define GL_UNSIGNED_SHORT_4_4_4_4                               0x8033
#define GL_UNSIGNED_SHORT_4_4_4_4_REV                           0x8365
#define GL_UNSIGNED_SHORT_5_5_5_1                               0x8034
#define GL_UNSIGNED_SHORT_1_5_5_5_REV                           0x8366
#define GL_UNSIGNED_INT_8_8_8_8                                 0x8035
#define GL_UNSIGNED_INT_8_8_8_8_REV                             0x8367
#define GL_UNSIGNED_INT_10_10_10_2                              0x8036
#define GL_UNSIGNED_INT_2_10_10_10_REV                          0x8368
#define GL_LIGHT_MODEL_COLOR_CONTROL                            0x81F8
#define GL_SINGLE_COLOR                                         0x81F9
#define GL_SEPARATE_SPECULAR_COLOR                              0x81FA
#define GL_TEXTURE_MIN_LOD                                      0x813A
#define GL_TEXTURE_MAX_LOD                                      0x813B
#define GL_TEXTURE_BASE_LEVEL                                   0x813C
#define GL_TEXTURE_MAX_LEVEL                                    0x813D
#define GL_SMOOTH_POINT_SIZE_RANGE                              0x0B12
#define GL_SMOOTH_POINT_SIZE_GRANULARITY                        0x0B13
#define GL_SMOOTH_LINE_WIDTH_RANGE                              0x0B22
#define GL_SMOOTH_LINE_WIDTH_GRANULARITY                        0x0B23
#define GL_ALIASED_POINT_SIZE_RANGE                             0x846D
#define GL_ALIASED_LINE_WIDTH_RANGE                             0x846E
#define GL_PACK_SKIP_IMAGES                                     0x806B
#define GL_PACK_IMAGE_HEIGHT                                    0x806C
#define GL_UNPACK_SKIP_IMAGES                                   0x806D
#define GL_UNPACK_IMAGE_HEIGHT                                  0x806E
#define GL_TEXTURE_3D                                           0x806F
#define GL_PROXY_TEXTURE_3D                                     0x8070
#define GL_TEXTURE_DEPTH                                        0x8071
#define GL_TEXTURE_WRAP_R                                       0x8072
#define GL_MAX_3D_TEXTURE_SIZE                                  0x8073
#define GL_TEXTURE_BINDING_3D                                   0x806A

#define GL_COLOR_TABLE                                          0x80D0
#define GL_POST_CONVOLUTION_COLOR_TABLE                         0x80D1
#define GL_POST_COLOR_MATRIX_COLOR_TABLE                        0x80D2
#define GL_PROXY_COLOR_TABLE                                    0x80D3
#define GL_PROXY_POST_CONVOLUTION_COLOR_TABLE                   0x80D4
#define GL_PROXY_POST_COLOR_MATRIX_COLOR_TABLE                  0x80D5
#define GL_COLOR_TABLE_SCALE                                    0x80D6
#define GL_COLOR_TABLE_BIAS                                     0x80D7
#define GL_COLOR_TABLE_FORMAT                                   0x80D8
#define GL_COLOR_TABLE_WIDTH                                    0x80D9
#define GL_COLOR_TABLE_RED_SIZE                                 0x80DA
#define GL_COLOR_TABLE_GREEN_SIZE                               0x80DB
#define GL_COLOR_TABLE_BLUE_SIZE                                0x80DC
#define GL_COLOR_TABLE_ALPHA_SIZE                               0x80DD
#define GL_COLOR_TABLE_LUMINANCE_SIZE                           0x80DE
#define GL_COLOR_TABLE_INTENSITY_SIZE                           0x80DF

#define GL_CONVOLUTION_1D                                       0x8010
#define GL_CONVOLUTION_2D                                       0x8011
#define GL_SEPARABLE_2D                                         0x8012
#define GL_CONVOLUTION_BORDER_MODE                              0x8013
#define GL_CONVOLUTION_FILTER_SCALE                             0x8014
#define GL_CONVOLUTION_FILTER_BIAS                              0x8015
#define GL_REDUCE                                               0x8016
#define GL_CONVOLUTION_FORMAT                                   0x8017
#define GL_CONVOLUTION_WIDTH                                    0x8018
#define GL_CONVOLUTION_HEIGHT                                   0x8019
#define GL_MAX_CONVOLUTION_WIDTH                                0x801A
#define GL_MAX_CONVOLUTION_HEIGHT                               0x801B
#define GL_POST_CONVOLUTION_RED_SCALE                           0x801C
#define GL_POST_CONVOLUTION_GREEN_SCALE                         0x801D
#define GL_POST_CONVOLUTION_BLUE_SCALE                          0x801E
#define GL_POST_CONVOLUTION_ALPHA_SCALE                         0x801F
#define GL_POST_CONVOLUTION_RED_BIAS                            0x8020
#define GL_POST_CONVOLUTION_GREEN_BIAS                          0x8021
#define GL_POST_CONVOLUTION_BLUE_BIAS                           0x8022
#define GL_POST_CONVOLUTION_ALPHA_BIAS                          0x8023
#define GL_CONSTANT_BORDER                                      0x8151
#define GL_REPLICATE_BORDER                                     0x8153
#define GL_CONVOLUTION_BORDER_COLOR                             0x8154
#define GL_COLOR_MATRIX                                         0x80B1
#define GL_COLOR_MATRIX_STACK_DEPTH                             0x80B2
#define GL_MAX_COLOR_MATRIX_STACK_DEPTH                         0x80B3
#define GL_POST_COLOR_MATRIX_RED_SCALE                          0x80B4
#define GL_POST_COLOR_MATRIX_GREEN_SCALE                        0x80B5
#define GL_POST_COLOR_MATRIX_BLUE_SCALE                         0x80B6
#define GL_POST_COLOR_MATRIX_ALPHA_SCALE                        0x80B7
#define GL_POST_COLOR_MATRIX_RED_BIAS                           0x80B8
#define GL_POST_COLOR_MATRIX_GREEN_BIAS                         0x80B9
#define GL_POST_COLOR_MATRIX_BLUE_BIAS                          0x80BA
#define GL_POST_COLOR_MATRIX_ALPHA_BIAS                         0x80BB
#define GL_HISTOGRAM                                            0x8024
#define GL_PROXY_HISTOGRAM                                      0x8025
#define GL_HISTOGRAM_WIDTH                                      0x8026
#define GL_HISTOGRAM_FORMAT                                     0x8027
#define GL_HISTOGRAM_RED_SIZE                                   0x8028
#define GL_HISTOGRAM_GREEN_SIZE                                 0x8029
#define GL_HISTOGRAM_BLUE_SIZE                                  0x802A
#define GL_HISTOGRAM_ALPHA_SIZE                                 0x802B
#define GL_HISTOGRAM_LUMINANCE_SIZE                             0x802C
#define GL_HISTOGRAM_SINK                                       0x802D
#define GL_MINMAX                                               0x802E
#define GL_MINMAX_FORMAT                                        0x802F
#define GL_MINMAX_SINK                                          0x8030
#define GL_TABLE_TOO_LARGE                                      0x8031
#define GL_BLEND_EQUATION                                       0x8009
#define GL_MIN                                                  0x8007
#define GL_MAX                                                  0x8008
#define GL_FUNC_ADD                                             0x8006
#define GL_FUNC_SUBTRACT                                        0x800A
#define GL_FUNC_REVERSE_SUBTRACT                                0x800B
#define GL_BLEND_COLOR                                          0x8005
#define GL_CONSTANT_COLOR                                       0x8001
#define GL_ONE_MINUS_CONSTANT_COLOR                             0x8002
#define GL_CONSTANT_ALPHA                                       0x8003
#define GL_ONE_MINUS_CONSTANT_ALPHA                             0x8004

#define GL_TEXTURE0                                             0x84C0
#define GL_TEXTURE1                                             0x84C1
#define GL_TEXTURE2                                             0x84C2
#define GL_TEXTURE3                                             0x84C3
#define GL_TEXTURE4                                             0x84C4
#define GL_TEXTURE5                                             0x84C5
#define GL_TEXTURE6                                             0x84C6
#define GL_TEXTURE7                                             0x84C7
#define GL_TEXTURE8                                             0x84C8
#define GL_TEXTURE9                                             0x84C9
#define GL_TEXTURE10                                            0x84CA
#define GL_TEXTURE11                                            0x84CB
#define GL_TEXTURE12                                            0x84CC
#define GL_TEXTURE13                                            0x84CD
#define GL_TEXTURE14                                            0x84CE
#define GL_TEXTURE15                                            0x84CF
#define GL_TEXTURE16                                            0x84D0
#define GL_TEXTURE17                                            0x84D1
#define GL_TEXTURE18                                            0x84D2
#define GL_TEXTURE19                                            0x84D3
#define GL_TEXTURE20                                            0x84D4
#define GL_TEXTURE21                                            0x84D5
#define GL_TEXTURE22                                            0x84D6
#define GL_TEXTURE23                                            0x84D7
#define GL_TEXTURE24                                            0x84D8
#define GL_TEXTURE25                                            0x84D9
#define GL_TEXTURE26                                            0x84DA
#define GL_TEXTURE27                                            0x84DB
#define GL_TEXTURE28                                            0x84DC
#define GL_TEXTURE29                                            0x84DD
#define GL_TEXTURE30                                            0x84DE
#define GL_TEXTURE31                                            0x84DF
#define GL_ACTIVE_TEXTURE                                       0x84E0
#define GL_CLIENT_ACTIVE_TEXTURE                                0x84E1
#define GL_MAX_TEXTURE_UNITS                                    0x84E2

#define GL_NORMAL_MAP                                           0x8511
#define GL_REFLECTION_MAP                                       0x8512
#define GL_TEXTURE_CUBE_MAP                                     0x8513
#define GL_TEXTURE_BINDING_CUBE_MAP                             0x8514
#define GL_TEXTURE_CUBE_MAP_POSITIVE_X                          0x8515
#define GL_TEXTURE_CUBE_MAP_NEGATIVE_X                          0x8516
#define GL_TEXTURE_CUBE_MAP_POSITIVE_Y                          0x8517
#define GL_TEXTURE_CUBE_MAP_NEGATIVE_Y                          0x8518
#define GL_TEXTURE_CUBE_MAP_POSITIVE_Z                          0x8519
#define GL_TEXTURE_CUBE_MAP_NEGATIVE_Z                          0x851A
#define GL_PROXY_TEXTURE_CUBE_MAP                               0x851B
#define GL_MAX_CUBE_MAP_TEXTURE_SIZE                            0x851C

#define GL_COMPRESSED_ALPHA                                     0x84E9
#define GL_COMPRESSED_LUMINANCE                                 0x84EA
#define GL_COMPRESSED_LUMINANCE_ALPHA                           0x84EB
#define GL_COMPRESSED_INTENSITY                                 0x84EC
#define GL_COMPRESSED_RGB                                       0x84ED
#define GL_COMPRESSED_RGBA                                      0x84EE
#define GL_TEXTURE_COMPRESSION_HINT                             0x84EF
#define GL_TEXTURE_COMPRESSED_IMAGE_SIZE                        0x86A0
#define GL_TEXTURE_COMPRESSED                                   0x86A1
#define GL_NUM_COMPRESSED_TEXTURE_FORMATS                       0x86A2
#define GL_COMPRESSED_TEXTURE_FORMATS                           0x86A3

#define GL_MULTISAMPLE                                          0x809D
#define GL_SAMPLE_ALPHA_TO_COVERAGE                             0x809E
#define GL_SAMPLE_ALPHA_TO_ONE                                  0x809F
#define GL_SAMPLE_COVERAGE                                      0x80A0
#define GL_SAMPLE_BUFFERS                                       0x80A8
#define GL_SAMPLES                                              0x80A9
#define GL_SAMPLE_COVERAGE_VALUE                                0x80AA
#define GL_SAMPLE_COVERAGE_INVERT                               0x80AB
#define GL_MULTISAMPLE_BIT                                      0x20000000

#define GL_TRANSPOSE_MODELVIEW_MATRIX                           0x84E3
#define GL_TRANSPOSE_PROJECTION_MATRIX                          0x84E4
#define GL_TRANSPOSE_TEXTURE_MATRIX                             0x84E5
#define GL_TRANSPOSE_COLOR_MATRIX                               0x84E6

#define GL_COMBINE                                              0x8570
#define GL_COMBINE_RGB                                          0x8571
#define GL_COMBINE_ALPHA                                        0x8572
#define GL_SOURCE0_RGB                                          0x8580
#define GL_SOURCE1_RGB                                          0x8581
#define GL_SOURCE2_RGB                                          0x8582
#define GL_SOURCE0_ALPHA                                        0x8588
#define GL_SOURCE1_ALPHA                                        0x8589
#define GL_SOURCE2_ALPHA                                        0x858A
#define GL_OPERAND0_RGB                                         0x8590
#define GL_OPERAND1_RGB                                         0x8591
#define GL_OPERAND2_RGB                                         0x8592
#define GL_OPERAND0_ALPHA                                       0x8598
#define GL_OPERAND1_ALPHA                                       0x8599
#define GL_OPERAND2_ALPHA                                       0x859A
#define GL_RGB_SCALE                                            0x8573
#define GL_ADD_SIGNED                                           0x8574
#define GL_INTERPOLATE                                          0x8575
#define GL_SUBTRACT                                             0x84E7
#define GL_CONSTANT                                             0x8576
#define GL_PRIMARY_COLOR                                        0x8577
#define GL_PREVIOUS                                             0x8578

#define GL_DOT3_RGB                                             0x86AE
#define GL_DOT3_RGBA                                            0x86AF

#define GL_CLAMP_TO_BORDER                                      0x812D


/* 1.2 functions */

typedef void (APIENTRY * glDrawRangeElementsPROC) (GLenum mode, GLuint start, GLuint end, GLsizei count, GLenum type, const GLvoid *indices );
typedef void (APIENTRY * glTexImage3DPROC) (GLenum target, GLint level, GLint internalFormat, GLsizei width, GLsizei height, GLsizei depth, GLint border, GLenum format, GLenum type, const GLvoid *pixels );
typedef void (APIENTRY * glTexSubImage3DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLsizei width, GLsizei height, GLsizei depth, GLenum format, GLenum type, const GLvoid *pixels);
typedef void (APIENTRY * glCopyTexSubImage3DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLint x, GLint y, GLsizei width, GLsizei height );
typedef void (APIENTRY * glColorTablePROC) (GLenum target, GLenum internalformat, GLsizei width, GLenum format, GLenum type, const GLvoid *table );
typedef void (APIENTRY * glColorSubTablePROC) (GLenum target, GLsizei start, GLsizei count, GLenum format, GLenum type, const GLvoid *data );
typedef void (APIENTRY * glColorTableParameterivPROC) (GLenum target, GLenum pname, const GLint *params);
typedef void (APIENTRY * glColorTableParameterfvPROC) (GLenum target, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glCopyColorSubTablePROC) (GLenum target, GLsizei start, GLint x, GLint y, GLsizei width );
typedef void (APIENTRY * glCopyColorTablePROC) (GLenum target, GLenum internalformat, GLint x, GLint y, GLsizei width );
typedef void (APIENTRY * glGetColorTablePROC) (GLenum target, GLenum format, GLenum type, GLvoid *table );
typedef void (APIENTRY * glGetColorTableParameterfvPROC) (GLenum target, GLenum pname, GLfloat *params );
typedef void (APIENTRY * glGetColorTableParameterivPROC) (GLenum target, GLenum pname, GLint *params );
typedef void (APIENTRY * glBlendEquationPROC) (GLenum mode );
typedef void (APIENTRY * glBlendColorPROC) (GLclampf red, GLclampf green, GLclampf blue, GLclampf alpha );
typedef void (APIENTRY * glHistogramPROC) (GLenum target, GLsizei width, GLenum internalformat, GLboolean sink );
typedef void (APIENTRY * glResetHistogramPROC) (GLenum target );
typedef void (APIENTRY * glGetHistogramPROC) (GLenum target, GLboolean reset, GLenum format, GLenum type, GLvoid *values );
typedef void (APIENTRY * glGetHistogramParameterfvPROC) (GLenum target, GLenum pname, GLfloat *params );
typedef void (APIENTRY * glGetHistogramParameterivPROC) (GLenum target, GLenum pname, GLint *params );
typedef void (APIENTRY * glMinmaxPROC) (GLenum target, GLenum internalformat, GLboolean sink );
typedef void (APIENTRY * glResetMinmaxPROC) (GLenum target );
typedef void (APIENTRY * glGetMinmaxPROC) (GLenum target, GLboolean reset, GLenum format, GLenum types, GLvoid *values );
typedef void (APIENTRY * glGetMinmaxParameterfvPROC) (GLenum target, GLenum pname, GLfloat *params );
typedef void (APIENTRY * glGetMinmaxParameterivPROC) (GLenum target, GLenum pname, GLint *params );
typedef void (APIENTRY * glConvolutionFilter1DPROC) (GLenum target, GLenum internalformat, GLsizei width, GLenum format, GLenum type, const GLvoid *image );
typedef void (APIENTRY * glConvolutionFilter2DPROC) (GLenum target, GLenum internalformat, GLsizei width, GLsizei height, GLenum format, GLenum type, const GLvoid *image );
typedef void (APIENTRY * glConvolutionParameterfPROC) (GLenum target, GLenum pname, GLfloat params );
typedef void (APIENTRY * glConvolutionParameterfvPROC) (GLenum target, GLenum pname, const GLfloat *params );
typedef void (APIENTRY * glConvolutionParameteriPROC) (GLenum target, GLenum pname, GLint params );
typedef void (APIENTRY * glConvolutionParameterivPROC) (GLenum target, GLenum pname, const GLint *params );
typedef void (APIENTRY * glCopyConvolutionFilter1DPROC) (GLenum target, GLenum internalformat, GLint x, GLint y, GLsizei width );
typedef void (APIENTRY * glCopyConvolutionFilter2DPROC) (GLenum target, GLenum internalformat, GLint x, GLint y, GLsizei width, GLsizei height);
typedef void (APIENTRY * glGetConvolutionFilterPROC) (GLenum target, GLenum format, GLenum type, GLvoid *image );
typedef void (APIENTRY * glGetConvolutionParameterfvPROC) (GLenum target, GLenum pname, GLfloat *params );
typedef void (APIENTRY * glGetConvolutionParameterivPROC) (GLenum target, GLenum pname, GLint *params );
typedef void (APIENTRY * glSeparableFilter2DPROC) (GLenum target, GLenum internalformat, GLsizei width, GLsizei height, GLenum format, GLenum type, const GLvoid *row, const GLvoid *column );
typedef void (APIENTRY * glGetSeparableFilterPROC) (GLenum target, GLenum format, GLenum type, GLvoid *row, GLvoid *column, GLvoid *span );

/* 1.3 functions */

typedef void (APIENTRY * glActiveTexturePROC) (GLenum texture );
typedef void (APIENTRY * glClientActiveTexturePROC) (GLenum texture );
typedef void (APIENTRY * glCompressedTexImage1DPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLint border, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glCompressedTexImage2DPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLint border, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glCompressedTexImage3DPROC) (GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLsizei depth, GLint border, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glCompressedTexSubImage1DPROC) (GLenum target, GLint level, GLint xoffset, GLsizei width, GLenum format, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glCompressedTexSubImage2DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glCompressedTexSubImage3DPROC) (GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint zoffset, GLsizei width, GLsizei height, GLsizei depth, GLenum format, GLsizei imageSize, const GLvoid *data );
typedef void (APIENTRY * glGetCompressedTexImagePROC) (GLenum target, GLint lod, GLvoid *img );
typedef void (APIENTRY * glMultiTexCoord1dPROC) (GLenum target, GLdouble s );
typedef void (APIENTRY * glMultiTexCoord1dvPROC) (GLenum target, const GLdouble *v );
typedef void (APIENTRY * glMultiTexCoord1fPROC) (GLenum target, GLfloat s );
typedef void (APIENTRY * glMultiTexCoord1fvPROC) (GLenum target, const GLfloat *v );
typedef void (APIENTRY * glMultiTexCoord1iPROC) (GLenum target, GLint s );
typedef void (APIENTRY * glMultiTexCoord1ivPROC) (GLenum target, const GLint *v );
typedef void (APIENTRY * glMultiTexCoord1sPROC) (GLenum target, GLshort s );
typedef void (APIENTRY * glMultiTexCoord1svPROC) (GLenum target, const GLshort *v );
typedef void (APIENTRY * glMultiTexCoord2dPROC) (GLenum target, GLdouble s, GLdouble t );
typedef void (APIENTRY * glMultiTexCoord2dvPROC) (GLenum target, const GLdouble *v );
typedef void (APIENTRY * glMultiTexCoord2fPROC) (GLenum target, GLfloat s, GLfloat t );
typedef void (APIENTRY * glMultiTexCoord2fvPROC) (GLenum target, const GLfloat *v );
typedef void (APIENTRY * glMultiTexCoord2iPROC) (GLenum target, GLint s, GLint t );
typedef void (APIENTRY * glMultiTexCoord2ivPROC) (GLenum target, const GLint *v );
typedef void (APIENTRY * glMultiTexCoord2sPROC) (GLenum target, GLshort s, GLshort t );
typedef void (APIENTRY * glMultiTexCoord2svPROC) (GLenum target, const GLshort *v );
typedef void (APIENTRY * glMultiTexCoord3dPROC) (GLenum target, GLdouble s, GLdouble t, GLdouble r );
typedef void (APIENTRY * glMultiTexCoord3dvPROC) (GLenum target, const GLdouble *v );
typedef void (APIENTRY * glMultiTexCoord3fPROC) (GLenum target, GLfloat s, GLfloat t, GLfloat r );
typedef void (APIENTRY * glMultiTexCoord3fvPROC) (GLenum target, const GLfloat *v );
typedef void (APIENTRY * glMultiTexCoord3iPROC) (GLenum target, GLint s, GLint t, GLint r );
typedef void (APIENTRY * glMultiTexCoord3ivPROC) (GLenum target, const GLint *v );
typedef void (APIENTRY * glMultiTexCoord3sPROC) (GLenum target, GLshort s, GLshort t, GLshort r );
typedef void (APIENTRY * glMultiTexCoord3svPROC) (GLenum target, const GLshort *v );
typedef void (APIENTRY * glMultiTexCoord4dPROC) (GLenum target, GLdouble s, GLdouble t, GLdouble r, GLdouble q );
typedef void (APIENTRY * glMultiTexCoord4dvPROC) (GLenum target, const GLdouble *v );
typedef void (APIENTRY * glMultiTexCoord4fPROC) (GLenum target, GLfloat s, GLfloat t, GLfloat r, GLfloat q );
typedef void (APIENTRY * glMultiTexCoord4fvPROC) (GLenum target, const GLfloat *v );
typedef void (APIENTRY * glMultiTexCoord4iPROC) (GLenum target, GLint s, GLint t, GLint r, GLint q );
typedef void (APIENTRY * glMultiTexCoord4ivPROC) (GLenum target, const GLint *v );
typedef void (APIENTRY * glMultiTexCoord4sPROC) (GLenum target, GLshort s, GLshort t, GLshort r, GLshort q );
typedef void (APIENTRY * glMultiTexCoord4svPROC) (GLenum target, const GLshort *v );
typedef void (APIENTRY * glLoadTransposeMatrixdPROC) (const GLdouble m[16] );
typedef void (APIENTRY * glLoadTransposeMatrixfPROC) (const GLfloat m[16] );
typedef void (APIENTRY * glMultTransposeMatrixdPROC) (const GLdouble m[16] );
typedef void (APIENTRY * glMultTransposeMatrixfPROC) (const GLfloat m[16] );
typedef void (APIENTRY * glSampleCoveragePROC) (GLclampf value, GLboolean invert );

extern glBlendColorPROC glBlendColor;
extern glBlendEquationPROC glBlendEquation;
extern glDrawRangeElementsPROC glDrawRangeElements;
extern glColorTablePROC glColorTable;
extern glColorTableParameterfvPROC glColorTableParameterfv;
extern glColorTableParameterivPROC glColorTableParameteriv;
extern glCopyColorTablePROC glCopyColorTable;
extern glGetColorTablePROC glGetColorTable;
extern glGetColorTableParameterfvPROC glGetColorTableParameterfv;
extern glGetColorTableParameterivPROC glGetColorTableParameteriv;
extern glColorSubTablePROC glColorSubTable;
extern glCopyColorSubTablePROC glCopyColorSubTable;
extern glConvolutionFilter1DPROC glConvolutionFilter1D;
extern glConvolutionFilter2DPROC glConvolutionFilter2D;
extern glConvolutionParameterfPROC glConvolutionParameterf;
extern glConvolutionParameterfvPROC glConvolutionParameterfv;
extern glConvolutionParameteriPROC glConvolutionParameteri;
extern glConvolutionParameterivPROC glConvolutionParameteriv;
extern glCopyConvolutionFilter1DPROC glCopyConvolutionFilter1D;
extern glCopyConvolutionFilter2DPROC glCopyConvolutionFilter2D;
extern glGetConvolutionFilterPROC glGetConvolutionFilter;
extern glGetConvolutionParameterfvPROC glGetConvolutionParameterfv;
extern glGetConvolutionParameterivPROC glGetConvolutionParameteriv;
extern glGetSeparableFilterPROC glGetSeparableFilter;
extern glSeparableFilter2DPROC glSeparableFilter2D;
extern glGetHistogramPROC glGetHistogram;
extern glGetHistogramParameterfvPROC glGetHistogramParameterfv;
extern glGetHistogramParameterivPROC glGetHistogramParameteriv;
extern glGetMinmaxPROC glGetMinmax;
extern glGetMinmaxParameterfvPROC glGetMinmaxParameterfv;
extern glGetMinmaxParameterivPROC glGetMinmaxParameteriv;
extern glHistogramPROC glHistogram;
extern glMinmaxPROC glMinmax;
extern glResetHistogramPROC glResetHistogram;
extern glResetMinmaxPROC glResetMinmax;
extern glTexImage3DPROC glTexImage3D;
extern glTexSubImage3DPROC glTexSubImage3D;
extern glCopyTexSubImage3DPROC glCopyTexSubImage3D;

/* 1.3 */

extern glActiveTexturePROC glActiveTexture;
extern glClientActiveTexturePROC glClientActiveTexture;
extern glMultiTexCoord1dPROC glMultiTexCoord1d;
extern glMultiTexCoord1dvPROC glMultiTexCoord1dv;
extern glMultiTexCoord1fPROC glMultiTexCoord1f;
extern glMultiTexCoord1fvPROC glMultiTexCoord1fv;
extern glMultiTexCoord1iPROC glMultiTexCoord1i;
extern glMultiTexCoord1ivPROC glMultiTexCoord1iv;
extern glMultiTexCoord1sPROC glMultiTexCoord1s;
extern glMultiTexCoord1svPROC glMultiTexCoord1sv;
extern glMultiTexCoord2dPROC glMultiTexCoord2d;
extern glMultiTexCoord2dvPROC glMultiTexCoord2dv;
extern glMultiTexCoord2fPROC glMultiTexCoord2f;
extern glMultiTexCoord2fvPROC glMultiTexCoord2fv;
extern glMultiTexCoord2iPROC glMultiTexCoord2i;
extern glMultiTexCoord2ivPROC glMultiTexCoord2iv;
extern glMultiTexCoord2sPROC glMultiTexCoord2s;
extern glMultiTexCoord2svPROC glMultiTexCoord2sv;
extern glMultiTexCoord3dPROC glMultiTexCoord3d;
extern glMultiTexCoord3dvPROC glMultiTexCoord3dv;
extern glMultiTexCoord3fPROC glMultiTexCoord3f;
extern glMultiTexCoord3fvPROC glMultiTexCoord3fv;
extern glMultiTexCoord3iPROC glMultiTexCoord3i;
extern glMultiTexCoord3ivPROC glMultiTexCoord3iv;
extern glMultiTexCoord3sPROC glMultiTexCoord3s;
extern glMultiTexCoord3svPROC glMultiTexCoord3sv;
extern glMultiTexCoord4dPROC glMultiTexCoord4d;
extern glMultiTexCoord4dvPROC glMultiTexCoord4dv;
extern glMultiTexCoord4fPROC glMultiTexCoord4f;
extern glMultiTexCoord4fvPROC glMultiTexCoord4fv;
extern glMultiTexCoord4iPROC glMultiTexCoord4i;
extern glMultiTexCoord4ivPROC glMultiTexCoord4iv;
extern glMultiTexCoord4sPROC glMultiTexCoord4s;
extern glMultiTexCoord4svPROC glMultiTexCoord4sv;
extern glLoadTransposeMatrixfPROC glLoadTransposeMatrixf;
extern glLoadTransposeMatrixdPROC glLoadTransposeMatrixd;
extern glMultTransposeMatrixfPROC glMultTransposeMatrixf;
extern glMultTransposeMatrixdPROC glMultTransposeMatrixd;
extern glCompressedTexImage3DPROC glCompressedTexImage3D;
extern glCompressedTexImage2DPROC glCompressedTexImage2D;
extern glCompressedTexImage1DPROC glCompressedTexImage1D;
extern glCompressedTexSubImage3DPROC glCompressedTexSubImage3D;
extern glCompressedTexSubImage2DPROC glCompressedTexSubImage2D;
extern glCompressedTexSubImage1DPROC glCompressedTexSubImage1D;
extern glGetCompressedTexImagePROC glGetCompressedTexImage;
extern glSampleCoveragePROC glSampleCoverage;

/*-------------------------------------------------------------------*/
/*------------EXTENSIONS---------------------------------------------*/
/*-------------------------------------------------------------------*/

/*-------------------------------------------------------------------*/
/*------------ARB_MULTITEXTURE---------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_multitexture
#define GL_ARB_multitexture 1
#define GL_TEXTURE0_ARB                                         0x84C0
#define GL_TEXTURE1_ARB                                         0x84C1
#define GL_TEXTURE2_ARB                                         0x84C2
#define GL_TEXTURE3_ARB                                         0x84C3
#define GL_TEXTURE4_ARB                                         0x84C4
#define GL_TEXTURE5_ARB                                         0x84C5
#define GL_TEXTURE6_ARB                                         0x84C6
#define GL_TEXTURE7_ARB                                         0x84C7
#define GL_TEXTURE8_ARB                                         0x84C8
#define GL_TEXTURE9_ARB                                         0x84C9
#define GL_TEXTURE10_ARB                                        0x84CA
#define GL_TEXTURE11_ARB                                        0x84CB
#define GL_TEXTURE12_ARB                                        0x84CC
#define GL_TEXTURE13_ARB                                        0x84CD
#define GL_TEXTURE14_ARB                                        0x84CE
#define GL_TEXTURE15_ARB                                        0x84CF
#define GL_TEXTURE16_ARB                                        0x84D0
#define GL_TEXTURE17_ARB                                        0x84D1
#define GL_TEXTURE18_ARB                                        0x84D2
#define GL_TEXTURE19_ARB                                        0x84D3
#define GL_TEXTURE20_ARB                                        0x84D4
#define GL_TEXTURE21_ARB                                        0x84D5
#define GL_TEXTURE22_ARB                                        0x84D6
#define GL_TEXTURE23_ARB                                        0x84D7
#define GL_TEXTURE24_ARB                                        0x84D8
#define GL_TEXTURE25_ARB                                        0x84D9
#define GL_TEXTURE26_ARB                                        0x84DA
#define GL_TEXTURE27_ARB                                        0x84DB
#define GL_TEXTURE28_ARB                                        0x84DC
#define GL_TEXTURE29_ARB                                        0x84DD
#define GL_TEXTURE30_ARB                                        0x84DE
#define GL_TEXTURE31_ARB                                        0x84DF
#define GL_ACTIVE_TEXTURE_ARB                                   0x84E0
#define GL_CLIENT_ACTIVE_TEXTURE_ARB                            0x84E1
#define GL_MAX_TEXTURE_UNITS_ARB                                0x84E2

extern glActiveTexturePROC glActiveTextureARB;
extern glClientActiveTexturePROC glClientActiveTextureARB;
extern glMultiTexCoord1dPROC glMultiTexCoord1dARB;
extern glMultiTexCoord1dvPROC glMultiTexCoord1dvARB;
extern glMultiTexCoord1fPROC glMultiTexCoord1fARB;
extern glMultiTexCoord1fvPROC glMultiTexCoord1fvARB;
extern glMultiTexCoord1iPROC glMultiTexCoord1iARB;
extern glMultiTexCoord1ivPROC glMultiTexCoord1ivARB;
extern glMultiTexCoord1sPROC glMultiTexCoord1sARB;
extern glMultiTexCoord1svPROC glMultiTexCoord1svARB;
extern glMultiTexCoord2dPROC glMultiTexCoord2dARB;
extern glMultiTexCoord2dvPROC glMultiTexCoord2dvARB;
extern glMultiTexCoord2fPROC glMultiTexCoord2fARB;
extern glMultiTexCoord2fvPROC glMultiTexCoord2fvARB;
extern glMultiTexCoord2iPROC glMultiTexCoord2iARB;
extern glMultiTexCoord2ivPROC glMultiTexCoord2ivARB;
extern glMultiTexCoord2sPROC glMultiTexCoord2sARB;
extern glMultiTexCoord2svPROC glMultiTexCoord2svARB;
extern glMultiTexCoord3dPROC glMultiTexCoord3dARB;
extern glMultiTexCoord3dvPROC glMultiTexCoord3dvARB;
extern glMultiTexCoord3fPROC glMultiTexCoord3fARB;
extern glMultiTexCoord3fvPROC glMultiTexCoord3fvARB;
extern glMultiTexCoord3iPROC glMultiTexCoord3iARB;
extern glMultiTexCoord3ivPROC glMultiTexCoord3ivARB;
extern glMultiTexCoord3sPROC glMultiTexCoord3sARB;
extern glMultiTexCoord3svPROC glMultiTexCoord3svARB;
extern glMultiTexCoord4dPROC glMultiTexCoord4dARB;
extern glMultiTexCoord4dvPROC glMultiTexCoord4dvARB;
extern glMultiTexCoord4fPROC glMultiTexCoord4fARB;
extern glMultiTexCoord4fvPROC glMultiTexCoord4fvARB;
extern glMultiTexCoord4iPROC glMultiTexCoord4iARB;
extern glMultiTexCoord4ivPROC glMultiTexCoord4ivARB;
extern glMultiTexCoord4sPROC glMultiTexCoord4sARB;
extern glMultiTexCoord4svPROC glMultiTexCoord4svARB;

#endif /* GL_ARB_multitexture */

/*-------------------------------------------------------------------*/
/*------------ARB_TRANSPOSE_MATRIX-----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_transpose_matrix
#define GL_ARB_transpose_matrix 1

#define GL_TRANSPOSE_MODELVIEW_MATRIX_ARB                       0x84E3
#define GL_TRANSPOSE_PROJECTION_MATRIX_ARB                      0x84E4
#define GL_TRANSPOSE_TEXTURE_MATRIX_ARB                         0x84E5
#define GL_TRANSPOSE_COLOR_MATRIX_ARB                           0x84E6

extern glLoadTransposeMatrixfPROC glLoadTransposeMatrixfARB;
extern glLoadTransposeMatrixdPROC glLoadTransposeMatrixdARB;
extern glMultTransposeMatrixfPROC glMultTransposeMatrixfARB;
extern glMultTransposeMatrixdPROC glMultTransposeMatrixdARB;

#endif /* GL_ARB_transpose_matrix */

/*-------------------------------------------------------------------*/
/*------------ARB_TEXTURE_COMPRESSION--------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_texture_compression
#define GL_ARB_texture_compression 1

#define GL_COMPRESSED_ALPHA_ARB                                 0x84E9
#define GL_COMPRESSED_LUMINANCE_ARB                             0x84EA
#define GL_COMPRESSED_LUMINANCE_ALPHA_ARB                       0x84EB
#define GL_COMPRESSED_INTENSITY_ARB                             0x84EC
#define GL_COMPRESSED_RGB_ARB                                   0x84ED
#define GL_COMPRESSED_RGBA_ARB                                  0x84EE
#define GL_TEXTURE_COMPRESSION_HINT_ARB                         0x84EF
#define GL_TEXTURE_IMAGE_SIZE_ARB                               0x86A0
#define GL_TEXTURE_COMPRESSED_ARB                               0x86A1
#define GL_NUM_COMPRESSED_TEXTURE_FORMATS_ARB                   0x86A2
#define GL_COMPRESSED_TEXTURE_FORMATS_ARB                       0x86A3

extern glCompressedTexImage3DPROC glCompressedTexImage3DARB;
extern glCompressedTexImage2DPROC glCompressedTexImage2DARB;
extern glCompressedTexImage1DPROC glCompressedTexImage1DARB; 
extern glCompressedTexSubImage3DPROC glCompressedTexSubImage3DARB;
extern glCompressedTexSubImage2DPROC glCompressedTexSubImage2DARB;
extern glCompressedTexSubImage1DPROC glCompressedTexSubImage1DARB;
extern glGetCompressedTexImagePROC glGetCompressedTexImageARB;

#endif /* GL_ARB_texture_compression */

/*-------------------------------------------------------------------*/
/*------------ARB_CUBE_MAP-------------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_texture_cube_map
#define GL_ARB_texture_cube_map 1

#define GL_NORMAL_MAP_ARB                                       0x8511
#define GL_REFLECTION_MAP_ARB                                   0x8512
#define GL_TEXTURE_CUBE_MAP_ARB                                 0x8513
#define GL_TEXTURE_BINDING_CUBE_MAP_ARB                         0x8514
#define GL_TEXTURE_CUBE_MAP_POSITIVE_X_ARB                      0x8515
#define GL_TEXTURE_CUBE_MAP_NEGATIVE_X_ARB                      0x8516
#define GL_TEXTURE_CUBE_MAP_POSITIVE_Y_ARB                      0x8517
#define GL_TEXTURE_CUBE_MAP_NEGATIVE_Y_ARB                      0x8518
#define GL_TEXTURE_CUBE_MAP_POSITIVE_Z_ARB                      0x8519
#define GL_TEXTURE_CUBE_MAP_NEGATIVE_Z_ARB                      0x851A
#define GL_PROXY_TEXTURE_CUBE_MAP_ARB                           0x851B
#define GL_MAX_CUBE_MAP_TEXTURE_SIZE_ARB                        0x851C

#endif /* GL_ARB_texture_cube_map */

/*-------------------------------------------------------------------*/
/*------------SGIX_SHADOW--------------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_SGIX_shadow
#define GL_SGIX_shadow 1

#define GL_TEXTURE_COMPARE_SGIX                                 0x819A
#define GL_TEXTURE_COMPARE_OPERATOR_SGIX                        0x819B
#define GL_TEXTURE_LEQUAL_R_SGIX                                0x819C
#define GL_TEXTURE_GEQUAL_R_SGIX                                0x819D

#endif /* GL_SGIX_shadow */

/*-------------------------------------------------------------------*/
/*------------SGIX_DEPTH_TEXTURE-------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_SGIX_depth_texture
#define GL_SGIX_depth_texture 1

#define GL_DEPTH_COMPONENT16_SGIX                               0x81A5
#define GL_DEPTH_COMPONENT24_SGIX                               0x81A6
#define GL_DEPTH_COMPONENT32_SGIX                               0x81A7

#endif /* GL_SGIX_depth_texture */

/*-------------------------------------------------------------------*/
/*------------EXT_COMPILED_VERTEX_ARRAY------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_EXT_compiled_vertex_array
#define GL_EXT_compiled_vertex_array 1

#define GL_ARRAY_ELEMENT_LOCK_FIRST_EXT                         0x81A8
#define GL_ARRAY_ELEMENT_LOCK_COUNT_EXT                         0x81A9

typedef void (APIENTRY * glLockArraysEXTPROC) (GLint first, GLsizei count);
typedef void (APIENTRY * glUnlockArraysEXTPROC) ();

extern glLockArraysEXTPROC glLockArraysEXT;
extern glUnlockArraysEXTPROC glUnlockArraysEXT;

#endif /* GL_EXT_compiled_vertex_array */

/*-------------------------------------------------------------------*/
/*------------ARB_TEXTURE_ENV_COMBINE--------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_texture_env_combine
#define GL_ARB_texture_env_combine 1

#define GL_COMBINE_ARB                                          0x8570
#define GL_COMBINE_RGB_ARB                                      0x8571
#define GL_COMBINE_ALPHA_ARB                                    0x8572
#define GL_RGB_SCALE_ARB                                        0x8573
#define GL_ADD_SIGNED_ARB                                       0x8574
#define GL_INTERPOLATE_ARB                                      0x8575
#define GL_CONSTANT_ARB                                         0x8576
#define GL_PRIMARY_COLOR_ARB                                    0x8577
#define GL_PREVIOUS_ARB                                         0x8578
#define GL_SOURCE0_RGB_ARB                                      0x8580
#define GL_SOURCE1_RGB_ARB                                      0x8581
#define GL_SOURCE2_RGB_ARB                                      0x8582
#define GL_SOURCE0_ALPHA_ARB                                    0x8588
#define GL_SOURCE1_ALPHA_ARB                                    0x8589
#define GL_SOURCE2_ALPHA_ARB                                    0x858A
#define GL_OPERAND0_RGB_ARB                                     0x8590
#define GL_OPERAND1_RGB_ARB                                     0x8591
#define GL_OPERAND2_RGB_ARB                                     0x8592
#define GL_OPERAND0_ALPHA_ARB                                   0x8598
#define GL_OPERAND1_ALPHA_ARB                                   0x8599
#define GL_OPERAND2_ALPHA_ARB                                   0x859A

#endif /* GL_ARB_texture_env_combine */

/*-------------------------------------------------------------------*/
/*------------ARB_TEXTURE_ENV_DOT3-----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_texture_env_dot3
#define GL_ARB_texture_env_dot3 1

#define GL_DOT3_RGB_ARB                                         0x86AE
#define GL_DOT3_RGBA_ARB                                        0x86AF

#endif /* GL_ARB_texture_env_dot3 */

/*-------------------------------------------------------------------*/
/*------------ARB_TEXTURE_BORDER_CLAMP-------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_texture_border_clamp
#define GL_ARB_texture_border_clamp 1

#define GL_CLAMP_TO_BORDER_ARB                                  0x812D

#endif /* GL_ARB_texture_border_clamp */

/*-------------------------------------------------------------------*/
/*------------ARB_TEXTURE_ENV_ADD------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_texture_env_add
#define GL_ARB_texture_env_add 1


#endif /* GL_ARB_texture_env_add */

/*-------------------------------------------------------------------*/
/*------------EXT_SECONDARY_COLOR------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_EXT_secondary_color
#define GL_EXT_secondary_color 1

#define GL_COLOR_SUM_EXT                                        0x8458
#define GL_CURRENT_SECONDARY_COLOR_EXT                          0x8459
#define GL_SECONDARY_COLOR_ARRAY_SIZE_EXT                       0x845A
#define GL_SECONDARY_COLOR_ARRAY_TYPE_EXT                       0x845B
#define GL_SECONDARY_COLOR_ARRAY_STRIDE_EXT                     0x845C
#define GL_SECONDARY_COLOR_ARRAY_POINTER_EXT                    0x845D
#define GL_SECONDARY_COLOR_ARRAY_EXT                            0x845E

typedef void (APIENTRY * glSecondaryColor3bEXTPROC) (GLbyte red, GLbyte green, GLbyte blue);
typedef void (APIENTRY * glSecondaryColor3bvEXTPROC) (const GLbyte *v);
typedef void (APIENTRY * glSecondaryColor3dEXTPROC) (GLdouble red, GLdouble green, GLdouble blue);
typedef void (APIENTRY * glSecondaryColor3dvEXTPROC) (const GLdouble *v);
typedef void (APIENTRY * glSecondaryColor3fEXTPROC) (GLfloat red, GLfloat green, GLfloat blue);
typedef void (APIENTRY * glSecondaryColor3fvEXTPROC) (const GLfloat *v);
typedef void (APIENTRY * glSecondaryColor3iEXTPROC) (GLint red, GLint green, GLint blue);
typedef void (APIENTRY * glSecondaryColor3ivEXTPROC) (const GLint *v);
typedef void (APIENTRY * glSecondaryColor3sEXTPROC) (GLshort red, GLshort green, GLshort blue);
typedef void (APIENTRY * glSecondaryColor3svEXTPROC) (const GLshort *v);
typedef void (APIENTRY * glSecondaryColor3ubEXTPROC) (GLubyte red, GLubyte green, GLubyte blue);
typedef void (APIENTRY * glSecondaryColor3ubvEXTPROC) (const GLubyte *v);
typedef void (APIENTRY * glSecondaryColor3uiEXTPROC) (GLuint red, GLuint green, GLuint blue);
typedef void (APIENTRY * glSecondaryColor3uivEXTPROC) (const GLuint *v);
typedef void (APIENTRY * glSecondaryColor3usEXTPROC) (GLushort red, GLushort green, GLushort blue);
typedef void (APIENTRY * glSecondaryColor3usvEXTPROC) (const GLushort *v);
typedef void (APIENTRY * glSecondaryColorPointerEXTPROC) (GLint size, GLenum type, GLsizei stride, GLvoid *pointer);

extern glSecondaryColor3bEXTPROC glSecondaryColor3bEXT;
extern glSecondaryColor3bvEXTPROC glSecondaryColor3bvEXT;
extern glSecondaryColor3dEXTPROC glSecondaryColor3dEXT;
extern glSecondaryColor3dvEXTPROC glSecondaryColor3dvEXT;
extern glSecondaryColor3fEXTPROC glSecondaryColor3fEXT;
extern glSecondaryColor3fvEXTPROC glSecondaryColor3fvEXT;
extern glSecondaryColor3iEXTPROC glSecondaryColor3iEXT;
extern glSecondaryColor3ivEXTPROC glSecondaryColor3ivEXT;
extern glSecondaryColor3sEXTPROC glSecondaryColor3sEXT;
extern glSecondaryColor3svEXTPROC glSecondaryColor3svEXT;
extern glSecondaryColor3ubEXTPROC glSecondaryColor3ubEXT;
extern glSecondaryColor3ubvEXTPROC glSecondaryColor3ubvEXT;
extern glSecondaryColor3uiEXTPROC glSecondaryColor3uiEXT;
extern glSecondaryColor3uivEXTPROC glSecondaryColor3uivEXT;
extern glSecondaryColor3usEXTPROC glSecondaryColor3usEXT;
extern glSecondaryColor3usvEXTPROC glSecondaryColor3usvEXT;
extern glSecondaryColorPointerEXTPROC glSecondaryColorPointerEXT;

#endif /* GL_EXT_secondary_color */

/*-------------------------------------------------------------------*/
/*------------EXT_FOG_COORD------------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_EXT_fog_coord
#define GL_EXT_fog_coord 1

#define GL_FOG_COORDINATE_SOURCE_EXT                            0x8450
#define GL_FOG_COORDINATE_EXT                                   0x8451
#define GL_FRAGMENT_DEPTH_EXT                                   0x8452
#define GL_CURRENT_FOG_COORDINATE_EXT                           0x8453
#define GL_FOG_COORDINATE_ARRAY_TYPE_EXT                        0x8454
#define GL_FOG_COORDINATE_ARRAY_STRIDE_EXT                      0x8455
#define GL_FOG_COORDINATE_ARRAY_POINTER_EXT                     0x8456
#define GL_FOG_COORDINATE_ARRAY_EXT                             0x8457

typedef void (APIENTRY * glFogCoordfEXTPROC) (GLfloat coord);
typedef void (APIENTRY * glFogCoordfvEXTPROC) (const GLfloat *coord);
typedef void (APIENTRY * glFogCoorddEXTPROC) (GLdouble coord);
typedef void (APIENTRY * glFogCoorddvEXTPROC) (const GLdouble *coord);
typedef void (APIENTRY * glFogCoordPointerEXTPROC) (GLenum type, GLsizei stride, const GLvoid *pointer);

extern glFogCoordfEXTPROC glFogCoordfEXT;
extern glFogCoordfvEXTPROC glFogCoordfvEXT;
extern glFogCoorddEXTPROC glFogCoorddEXT;
extern glFogCoorddvEXTPROC glFogCoorddvEXT;
extern glFogCoordPointerEXTPROC glFogCoordPointerEXT;

#endif /* GL_EXT_fog_coord */

/*-------------------------------------------------------------------*/
/*------------NV_VERTEX_ARRAY_RANGE----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_vertex_array_range
#define GL_NV_vertex_array_range 1

#define GL_VERTEX_ARRAY_RANGE_NV                                0x851D
#define GL_VERTEX_ARRAY_RANGE_LENGTH_NV                         0x851E
#define GL_VERTEX_ARRAY_RANGE_VALID_NV                          0x851F
#define GL_MAX_VERTEX_ARRAY_RANGE_ELEMENT_NV                    0x8520
#define GL_VERTEX_ARRAY_RANGE_POINTER_NV                        0x8521

typedef void (APIENTRY * glFlushVertexArrayRangeNVPROC) (void);
typedef void (APIENTRY * glVertexArrayRangeNVPROC) (GLsizei size, const GLvoid *pointer);

extern glFlushVertexArrayRangeNVPROC glFlushVertexArrayRangeNV;
extern glVertexArrayRangeNVPROC glVertexArrayRangeNV;

#ifdef _WIN32

typedef void * (APIENTRY * wglAllocateMemoryNVPROC) (GLsizei size, GLfloat readFrequency, GLfloat writeFrequency, GLfloat priority);
typedef void (APIENTRY * wglFreeMemoryNVPROC) (void *pointer);

extern wglAllocateMemoryNVPROC wglAllocateMemoryNV;
extern wglFreeMemoryNVPROC wglFreeMemoryNV;

#endif /* WIN32 */

#endif /* GL_NV_vertex_array_range */

/*-------------------------------------------------------------------*/
/*------------NV_VERTEX_ARRAY_RANGE2---------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_vertex_array_range2
#define GL_NV_vertex_array_range2 1

#define GL_VERTEX_ARRAY_RANGE_WITHOUT_FLUSH_NV                  0x8533

#endif /* GL_NV_vertex_array_range2 */

/*-------------------------------------------------------------------*/
/*------------EXT_POINT_PARAMETERS-----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_EXT_point_parameters
#define GL_EXT_point_parameters 1

#define GL_POINT_SIZE_MIN_EXT                                   0x8126
#define GL_POINT_SIZE_MAX_EXT                                   0x8127
#define GL_POINT_FADE_THRESHOLD_SIZE_EXT                        0x8128
#define GL_DISTANCE_ATTENUATION_EXT                             0x8129

typedef void (APIENTRY * glPointParameterfEXTPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glPointParameterfvEXTPROC) (GLenum pname, const GLfloat *params);

extern glPointParameterfEXTPROC glPointParameterfEXT;
extern glPointParameterfvEXTPROC glPointParameterfvEXT;

#endif /* GL_EXT_point_parameters */

/*-------------------------------------------------------------------*/
/*------------NV_REGISTER_COMBINERS----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_register_combiners
#define GL_NV_register_combiners 1

#define GL_REGISTER_COMBINERS_NV                                0x8522
#define GL_COMBINER0_NV                                         0x8550
#define GL_COMBINER1_NV                                         0x8551
#define GL_COMBINER2_NV                                         0x8552
#define GL_COMBINER3_NV                                         0x8553
#define GL_COMBINER4_NV                                         0x8554
#define GL_COMBINER5_NV                                         0x8555
#define GL_COMBINER6_NV                                         0x8556
#define GL_COMBINER7_NV                                         0x8557
#define GL_VARIABLE_A_NV                                        0x8523
#define GL_VARIABLE_B_NV                                        0x8524
#define GL_VARIABLE_C_NV                                        0x8525
#define GL_VARIABLE_D_NV                                        0x8526
#define GL_VARIABLE_E_NV                                        0x8527
#define GL_VARIABLE_F_NV                                        0x8528
#define GL_VARIABLE_G_NV                                        0x8529
#define GL_CONSTANT_COLOR0_NV                                   0x852A
#define GL_CONSTANT_COLOR1_NV                                   0x852B
#define GL_PRIMARY_COLOR_NV                                     0x852C
#define GL_SECONDARY_COLOR_NV                                   0x852D
#define GL_SPARE0_NV                                            0x852E
#define GL_SPARE1_NV                                            0x852F
#define GL_UNSIGNED_IDENTITY_NV                                 0x8536
#define GL_UNSIGNED_INVERT_NV                                   0x8537
#define GL_EXPAND_NORMAL_NV                                     0x8538
#define GL_EXPAND_NEGATE_NV                                     0x8539
#define GL_HALF_BIAS_NORMAL_NV                                  0x853A
#define GL_HALF_BIAS_NEGATE_NV                                  0x853B
#define GL_SIGNED_IDENTITY_NV                                   0x853C
#define GL_SIGNED_NEGATE_NV                                     0x853D
#define GL_E_TIMES_F_NV                                         0x8531
#define GL_SPARE0_PLUS_SECONDARY_COLOR_NV                       0x8532
#define GL_SCALE_BY_TWO_NV                                      0x853E
#define GL_SCALE_BY_FOUR_NV                                     0x853F
#define GL_SCALE_BY_ONE_HALF_NV                                 0x8540
#define GL_BIAS_BY_NEGATIVE_ONE_HALF_NV                         0x8541
#define GL_DISCARD_NV                                           0x8530
#define GL_COMBINER_INPUT_NV                                    0x8542
#define GL_COMBINER_MAPPING_NV                                  0x8543
#define GL_COMBINER_COMPONENT_USAGE_NV                          0x8544
#define GL_COMBINER_AB_DOT_PRODUCT_NV                           0x8545
#define GL_COMBINER_CD_DOT_PRODUCT_NV                           0x8546
#define GL_COMBINER_MUX_SUM_NV                                  0x8547
#define GL_COMBINER_SCALE_NV                                    0x8548
#define GL_COMBINER_BIAS_NV                                     0x8549
#define GL_COMBINER_AB_OUTPUT_NV                                0x854A
#define GL_COMBINER_CD_OUTPUT_NV                                0x854B
#define GL_COMBINER_SUM_OUTPUT_NV                               0x854C
#define GL_NUM_GENERAL_COMBINERS_NV                             0x854E
#define GL_COLOR_SUM_CLAMP_NV                                   0x854F
#define GL_MAX_GENERAL_COMBINERS_NV                             0x854D

typedef void (APIENTRY * glCombinerParameterfvNVPROC) (GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glCombinerParameterfNVPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glCombinerParameterivNVPROC) (GLenum pname, const GLint *params);
typedef void (APIENTRY * glCombinerParameteriNVPROC) (GLenum pname, GLint param);
typedef void (APIENTRY * glCombinerInputNVPROC) (GLenum stage, GLenum portion, GLenum variable, GLenum input, GLenum mapping, GLenum componentUsage);
typedef void (APIENTRY * glCombinerOutputNVPROC) (GLenum stage, GLenum portion, GLenum abOutput, GLenum cdOutput, GLenum sumOutput, GLenum scale, GLenum bias, GLboolean abDotProduct, GLboolean cdDotProduct, GLboolean muxSum);
typedef void (APIENTRY * glFinalCombinerInputNVPROC) (GLenum variable, GLenum input, GLenum mapping, GLenum componentUsage);
typedef void (APIENTRY * glGetCombinerInputParameterfvNVPROC) (GLenum stage, GLenum portion, GLenum variable, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetCombinerInputParameterivNVPROC) (GLenum stage, GLenum portion, GLenum variable, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetCombinerOutputParameterfvNVPROC) (GLenum stage, GLenum portion, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetCombinerOutputParameterivNVPROC) (GLenum stage, GLenum portion, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetFinalCombinerInputParameterfvNVPROC) (GLenum variable, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetFinalCombinerInputParameterivNVPROC) (GLenum variable, GLenum pname, GLint *params);

extern glCombinerParameterfvNVPROC glCombinerParameterfvNV;
extern glCombinerParameterfNVPROC  glCombinerParameterfNV;
extern glCombinerParameterivNVPROC glCombinerParameterivNV;
extern glCombinerParameteriNVPROC glCombinerParameteriNV;
extern glCombinerInputNVPROC glCombinerInputNV;
extern glCombinerOutputNVPROC glCombinerOutputNV;
extern glFinalCombinerInputNVPROC glFinalCombinerInputNV;
extern glGetCombinerInputParameterfvNVPROC glGetCombinerInputParameterfvNV;
extern glGetCombinerInputParameterivNVPROC glGetCombinerInputParameterivNV;
extern glGetCombinerOutputParameterfvNVPROC glGetCombinerOutputParameterfvNV;
extern glGetCombinerOutputParameterivNVPROC glGetCombinerOutputParameterivNV;
extern glGetFinalCombinerInputParameterfvNVPROC glGetFinalCombinerInputParameterfvNV;
extern glGetFinalCombinerInputParameterivNVPROC glGetFinalCombinerInputParameterivNV;

#endif /* GL_NV_register_combiners */

/*-------------------------------------------------------------------*/
/*------------ARB_MULTISAMPLE----------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_multisample
#define GL_ARB_multisample 1

#define GL_MULTISAMPLE_ARB                                      0x809D
#define GL_SAMPLE_ALPHA_TO_COVERAGE_ARB                         0x809E
#define GL_SAMPLE_ALPHA_TO_ONE_ARB                              0x809F
#define GL_SAMPLE_COVERAGE_ARB                                  0x80A0
#define GL_SAMPLE_BUFFERS_ARB                                   0x80A8
#define GL_SAMPLES_ARB                                          0x80A9
#define GL_SAMPLE_COVERAGE_VALUE_ARB                            0x80AA
#define GL_SAMPLE_COVERAGE_INVERT_ARB                           0x80AB
#define GL_MULTISAMPLE_BIT_ARB                                  0x20000000

extern glSampleCoveragePROC glSampleCoverageARB;

#endif /* GL_ARB_multisample */

/*-------------------------------------------------------------------*/
/*------------NV_TEXTURE_SHADER--------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_texture_shader
#define GL_NV_texture_shader 1

#define GL_TEXTURE_SHADER_NV                                    0x86DE
#define GL_RGBA_UNSIGNED_DOT_PRODUCT_MAPPING_NV                 0x86D9
#define GL_SHADER_OPERATION_NV                                  0x86DF
#define GL_CULL_MODES_NV                                        0x86E0
#define GL_OFFSET_TEXTURE_MATRIX_NV                             0x86E1
#define GL_OFFSET_TEXTURE_SCALE_NV                              0x86E2
#define GL_OFFSET_TEXTURE_BIAS_NV                               0x86E3
#define GL_PREVIOUS_TEXTURE_INPUT_NV                            0x86E4
#define GL_CONST_EYE_NV                                         0x86E5
#define GL_SHADER_CONSISTENT_NV                                 0x86DD
#define GL_PASS_THROUGH_NV                                      0x86E6
#define GL_CULL_FRAGMENT_NV                                     0x86E7
#define GL_OFFSET_TEXTURE_2D_NV                                 0x86E8
#define GL_OFFSET_TEXTURE_RECTANGLE_NV                          0x864C
#define GL_OFFSET_TEXTURE_RECTANGLE_SCALE_NV                    0x864D
#define GL_DEPENDENT_AR_TEXTURE_2D_NV                           0x86E9
#define GL_DEPENDENT_GB_TEXTURE_2D_NV                           0x86EA
#define GL_DOT_PRODUCT_NV                                       0x86EC
#define GL_DOT_PRODUCT_DEPTH_REPLACE_NV                         0x86ED
#define GL_DOT_PRODUCT_TEXTURE_2D_NV                            0x86EE
#define GL_DOT_PRODUCT_TEXTURE_RECTANGLE_NV                     0x864E
#define GL_DOT_PRODUCT_TEXTURE_CUBE_MAP_NV                      0x86F0
#define GL_DOT_PRODUCT_DIFFUSE_CUBE_MAP_NV                      0x86F1
#define GL_DOT_PRODUCT_REFLECT_CUBE_MAP_NV                      0x86F2
#define GL_DOT_PRODUCT_CONST_EYE_REFLECT_CUBE_MAP_NV            0x86F3
#define GL_HILO_NV                                              0x86F4
#define GL_DSDT_NV                                              0x86F5
#define GL_DSDT_MAG_NV                                          0x86F6
#define GL_DSDT_MAG_VIB_NV                                      0x86F7
#define GL_UNSIGNED_INT_S8_S8_8_8_NV                            0x86DA
#define GL_UNSIGNED_INT_8_8_S8_S8_REV_NV                        0x86DB
#define GL_SIGNED_RGBA_NV                                       0x86FB
#define GL_SIGNED_RGBA8_NV                                      0x86FC
#define GL_SIGNED_RGB_NV                                        0x86FE
#define GL_SIGNED_RGB8_NV                                       0x86FF
#define GL_SIGNED_LUMINANCE_NV                                  0x8701
#define GL_SIGNED_LUMINANCE8_NV                                 0x8702
#define GL_SIGNED_LUMINANCE_ALPHA_NV                            0x8703
#define GL_SIGNED_LUMINANCE8_ALPHA8_NV                          0x8704
#define GL_SIGNED_ALPHA_NV                                      0x8705
#define GL_SIGNED_ALPHA8_NV                                     0x8706
#define GL_SIGNED_INTENSITY_NV                                  0x8707
#define GL_SIGNED_INTENSITY8_NV                                 0x8708
#define GL_SIGNED_RGB_UNSIGNED_ALPHA_NV                         0x870C
#define GL_SIGNED_RGB8_UNSIGNED_ALPHA8_NV                       0x870D
#define GL_HILO16_NV                                            0x86F8
#define GL_SIGNED_HILO_NV                                       0x86F9
#define GL_SIGNED_HILO16_NV                                     0x86FA
#define GL_DSDT8_NV                                             0x8709
#define GL_DSDT8_MAG8_NV                                        0x870A
#define GL_DSDT_MAG_INTENSITY_NV                                0x86DC
#define GL_DSDT8_MAG8_INTENSITY8_NV                             0x870B
#define GL_HI_SCALE_NV                                          0x870E
#define GL_LO_SCALE_NV                                          0x870F
#define GL_DS_SCALE_NV                                          0x8710
#define GL_DT_SCALE_NV                                          0x8711
#define GL_MAGNITUDE_SCALE_NV                                   0x8712
#define GL_VIBRANCE_SCALE_NV                                    0x8713
#define GL_HI_BIAS_NV                                           0x8714
#define GL_LO_BIAS_NV                                           0x8715
#define GL_DS_BIAS_NV                                           0x8716
#define GL_DT_BIAS_NV                                           0x8717
#define GL_MAGNITUDE_BIAS_NV                                    0x8718
#define GL_VIBRANCE_BIAS_NV                                     0x8719
#define GL_TEXTURE_BORDER_VALUES_NV                             0x871A
#define GL_TEXTURE_HI_SIZE_NV                                   0x871B
#define GL_TEXTURE_LO_SIZE_NV                                   0x871C
#define GL_TEXTURE_DS_SIZE_NV                                   0x871D
#define GL_TEXTURE_DT_SIZE_NV                                   0x871E
#define GL_TEXTURE_MAG_SIZE_NV                                  0x871F

#endif /* GL_NV_texture_shader */

/*-------------------------------------------------------------------*/
/*------------GL_NV_TEXTURE_RECTANGLE--------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_texture_rectangle
#define GL_NV_texture_rectangle 1

#define GL_TEXTURE_RECTANGLE_NV                                 0x84F5
#define GL_TEXTURE_BINDING_RECTANGLE_NV                         0x84F6
#define GL_PROXY_TEXTURE_RECTANGLE_NV                           0x84F7
#define GL_MAX_RECTANGLE_TEXTURE_SIZE_NV                        0x84F8

#endif /* GL_NV_texture_recrangle */

/*-------------------------------------------------------------------*/
/*------------NV_TEXTURE_ENV_COMBINE4--------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_texture_env_combine4
#define GL_NV_texture_env_combine4 1

#define GL_COMBINE4_NV                                          0x8503
#define GL_SOURCE3_RGB_NV                                       0x8583
#define GL_SOURCE3_ALPHA_NV                                     0x858B
#define GL_OPERAND3_RGB_NV                                      0x8593
#define GL_OPERAND3_ALPHA_NV                                    0x859B

#endif /* GL_NV_texture_env_combine */

/*-------------------------------------------------------------------*/
/*------------NV_FOG_DISTANCE----------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_fog_distance
#define GL_NV_fog_distance 1

#define GL_FOG_DISTANCE_MODE_NV                                 0x855A
#define GL_EYE_RADIAL_NV                                        0x855B
#define GL_EYE_PLANE_ABSOLUTE_NV                                0x855C

#endif /* GL_NV_fog_distance */

/*-------------------------------------------------------------------*/
/*------------EXT_TEXTURE_FILTER_ANISOTROPIC-------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_EXT_texture_filter_anisotropic
#define GL_EXT_texture_filter_anisotropic 1

#define GL_TEXTURE_MAX_ANISOTROPY_EXT                           0x84FE
#define GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT                       0x84FF

#endif /* GL_EXT_texture_filter_anisotropic */

/*-------------------------------------------------------------------*/
/*------------SGIS_GENERATE_MIPMAP-----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_SGIS_generate_mipmap
#define GL_SGIS_generate_mipmap 1

#define GL_GENERATE_MIPMAP_SGIS                                 0x8191
#define GL_GENERATE_MIPMAP_HINT_SGIS                            0x8192

#endif /* GL_SGIS_generate_mipmap */

/*-------------------------------------------------------------------*/
/*------------NV_TEXGEN_REFLECTION-----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_texgen_reflection
#define GL_NV_texgen_reflection 1

#define GL_NORMAL_MAP_NV                                        0x8511
#define GL_REFLECTION_MAP_NV                                    0x8512

#endif /* GL_NV_texgen_reflection */

/*-------------------------------------------------------------------*/
/*------------EXT_VERTEX_WEIGHTING-----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_EXT_vertex_weighting
#define GL_EXT_vertex_weighting 1

#define GL_MODELVIEW0_STACK_DEPTH_EXT                           0x0BA3  /* alias to GL_MODELVIEW_STACK_DEPTH */
#define GL_MODELVIEW1_STACK_DEPTH_EXT                           0x8502
#define GL_MODELVIEW0_MATRIX_EXT                                0x0BA6  /* alias to GL_MODELVIEW_MATRIX */
#define GL_MODELVIEW1_MATRIX_EXT                                0x8506
#define GL_VERTEX_WEIGHTING_EXT                                 0x8509
#define GL_MODELVIEW0_EXT                                       0x1700  /* alias to GL_MODELVIEW */
#define GL_MODELVIEW1_EXT                                       0x850A
#define GL_CURRENT_VERTEX_WEIGHT_EXT                            0x850B
#define GL_VERTEX_WEIGHT_ARRAY_EXT                              0x850C
#define GL_VERTEX_WEIGHT_ARRAY_SIZE_EXT                         0x850D
#define GL_VERTEX_WEIGHT_ARRAY_TYPE_EXT                         0x850E
#define GL_VERTEX_WEIGHT_ARRAY_STRIDE_EXT                       0x850F
#define GL_VERTEX_WEIGHT_ARRAY_POINTER_EXT                      0x8510

typedef void (APIENTRY * glVertexWeightfEXTPROC) (GLfloat weight);
typedef void (APIENTRY * glVertexWeightfvEXTPROC) (const GLfloat *weight);
typedef void (APIENTRY * glVertexWeightPointerEXTPROC) (GLsizei size, GLenum type, GLsizei stride, const GLvoid *pointer);

extern glVertexWeightfEXTPROC glVertexWeightfEXT;
extern glVertexWeightfvEXTPROC glVertexWeightfvEXT;
extern glVertexWeightPointerEXTPROC glVertexWeightPointerEXT;

#endif /* GL_EXT_vertex_weighting */

/*-------------------------------------------------------------------*/
/*------------NV_VERTEX_PROGRAM--------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_vertex_program
#define GL_NV_vertex_program 1

#define GL_VERTEX_PROGRAM_NV                                    0x8620
#define GL_VERTEX_PROGRAM_POINT_SIZE_NV                         0x8642
#define GL_VERTEX_PROGRAM_TWO_SIDE_NV                           0x8643
#define GL_VERTEX_STATE_PROGRAM_NV                              0x8621
#define GL_ATTRIB_ARRAY_SIZE_NV                                 0x8623
#define GL_ATTRIB_ARRAY_STRIDE_NV                               0x8624
#define GL_ATTRIB_ARRAY_TYPE_NV                                 0x8625
#define GL_CURRENT_ATTRIB_NV                                    0x8626
#define GL_PROGRAM_PARAMETER_NV                                 0x8644
#define GL_ATTRIB_ARRAY_POINTER_NV                              0x8645
#define GL_PROGRAM_TARGET_NV                                    0x8646
#define GL_PROGRAM_LENGTH_NV                                    0x8627
#define GL_PROGRAM_RESIDENT_NV                                  0x8647
#define GL_PROGRAM_STRING_NV                                    0x8628
#define GL_TRACK_MATRIX_NV                                      0x8648
#define GL_TRACK_MATRIX_TRANSFORM_NV                            0x8649
#define GL_MAX_TRACK_MATRIX_STACK_DEPTH_NV                      0x862E
#define GL_MAX_TRACK_MATRICES_NV                                0x862F
#define GL_CURRENT_MATRIX_STACK_DEPTH_NV                        0x8640
#define GL_CURRENT_MATRIX_NV                                    0x8641
#define GL_VERTEX_PROGRAM_BINDING_NV                            0x864A
#define GL_PROGRAM_ERROR_POSITION_NV                            0x864B
#define GL_MODELVIEW_PROJECTION_NV                              0x8629
#define GL_MATRIX0_NV                                           0x8630
#define GL_MATRIX1_NV                                           0x8631
#define GL_MATRIX2_NV                                           0x8632
#define GL_MATRIX3_NV                                           0x8633
#define GL_MATRIX4_NV                                           0x8634
#define GL_MATRIX5_NV                                           0x8635
#define GL_MATRIX6_NV                                           0x8636
#define GL_MATRIX7_NV                                           0x8637
#define GL_IDENTITY_NV                                          0x862A
#define GL_INVERSE_NV                                           0x862B
#define GL_TRANSPOSE_NV                                         0x862C
#define GL_INVERSE_TRANSPOSE_NV                                 0x862D
#define GL_VERTEX_ATTRIB_ARRAY0_NV                              0x8650
#define GL_VERTEX_ATTRIB_ARRAY1_NV                              0x8651
#define GL_VERTEX_ATTRIB_ARRAY2_NV                              0x8652
#define GL_VERTEX_ATTRIB_ARRAY3_NV                              0x8653
#define GL_VERTEX_ATTRIB_ARRAY4_NV                              0x8654
#define GL_VERTEX_ATTRIB_ARRAY5_NV                              0x8655
#define GL_VERTEX_ATTRIB_ARRAY6_NV                              0x8656
#define GL_VERTEX_ATTRIB_ARRAY7_NV                              0x8657
#define GL_VERTEX_ATTRIB_ARRAY8_NV                              0x8658
#define GL_VERTEX_ATTRIB_ARRAY9_NV                              0x8659
#define GL_VERTEX_ATTRIB_ARRAY10_NV                             0x865A
#define GL_VERTEX_ATTRIB_ARRAY11_NV                             0x865B
#define GL_VERTEX_ATTRIB_ARRAY12_NV                             0x865C
#define GL_VERTEX_ATTRIB_ARRAY13_NV                             0x865D
#define GL_VERTEX_ATTRIB_ARRAY14_NV                             0x865E
#define GL_VERTEX_ATTRIB_ARRAY15_NV                             0x865F
#define GL_MAP1_VERTEX_ATTRIB0_4_NV                             0x8660
#define GL_MAP1_VERTEX_ATTRIB1_4_NV                             0x8661
#define GL_MAP1_VERTEX_ATTRIB2_4_NV                             0x8662
#define GL_MAP1_VERTEX_ATTRIB3_4_NV                             0x8663
#define GL_MAP1_VERTEX_ATTRIB4_4_NV                             0x8664
#define GL_MAP1_VERTEX_ATTRIB5_4_NV                             0x8665
#define GL_MAP1_VERTEX_ATTRIB6_4_NV                             0x8666
#define GL_MAP1_VERTEX_ATTRIB7_4_NV                             0x8667
#define GL_MAP1_VERTEX_ATTRIB8_4_NV                             0x8668
#define GL_MAP1_VERTEX_ATTRIB9_4_NV                             0x8669
#define GL_MAP1_VERTEX_ATTRIB10_4_NV                            0x866A
#define GL_MAP1_VERTEX_ATTRIB11_4_NV                            0x866B
#define GL_MAP1_VERTEX_ATTRIB12_4_NV                            0x866C
#define GL_MAP1_VERTEX_ATTRIB13_4_NV                            0x866D
#define GL_MAP1_VERTEX_ATTRIB14_4_NV                            0x866E
#define GL_MAP1_VERTEX_ATTRIB15_4_NV                            0x866F
#define GL_MAP2_VERTEX_ATTRIB0_4_NV                             0x8670
#define GL_MAP2_VERTEX_ATTRIB1_4_NV                             0x8671
#define GL_MAP2_VERTEX_ATTRIB2_4_NV                             0x8672
#define GL_MAP2_VERTEX_ATTRIB3_4_NV                             0x8673
#define GL_MAP2_VERTEX_ATTRIB4_4_NV                             0x8674
#define GL_MAP2_VERTEX_ATTRIB5_4_NV                             0x8675
#define GL_MAP2_VERTEX_ATTRIB6_4_NV                             0x8676
#define GL_MAP2_VERTEX_ATTRIB7_4_NV                             0x8677
#define GL_MAP2_VERTEX_ATTRIB8_4_NV                             0x8678
#define GL_MAP2_VERTEX_ATTRIB9_4_NV                             0x8679
#define GL_MAP2_VERTEX_ATTRIB10_4_NV                            0x867A
#define GL_MAP2_VERTEX_ATTRIB11_4_NV                            0x867B
#define GL_MAP2_VERTEX_ATTRIB12_4_NV                            0x867C
#define GL_MAP2_VERTEX_ATTRIB13_4_NV                            0x867D
#define GL_MAP2_VERTEX_ATTRIB14_4_NV                            0x867E
#define GL_MAP2_VERTEX_ATTRIB15_4_NV                            0x867F

typedef void (APIENTRY * glBindProgramNVPROC) (GLenum target, GLuint id);
typedef void (APIENTRY * glDeleteProgramsNVPROC) (GLsizei n, const GLuint *ids);
typedef void (APIENTRY * glExecuteProgramNVPROC) (GLenum target, GLuint id, const GLfloat *params);
typedef void (APIENTRY * glGenProgramsNVPROC) (GLsizei n, GLuint *ids);
typedef GLboolean (APIENTRY * glAreProgramsResidentNVPROC) (GLsizei n, const GLuint *ids, GLboolean *residences);
typedef void (APIENTRY * glRequestResidentProgramsNVPROC) (GLsizei n, GLuint *ids);
typedef void (APIENTRY * glGetProgramParameterfvNVPROC) (GLenum target, GLuint index, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetProgramParameterdvNVPROC) (GLenum target, GLuint index, GLenum pname, GLdouble *params);
typedef void (APIENTRY * glGetProgramivNVPROC) (GLuint id, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetProgramStringNVPROC) (GLuint id, GLenum pname, GLubyte *program);
typedef void (APIENTRY * glGetTrackMatrixivNVPROC) (GLenum target, GLuint address, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetVertexAttribdvNVPROC) (GLuint index, GLenum pname, GLdouble *params);
typedef void (APIENTRY * glGetVertexAttribfvNVPROC) (GLuint index, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetVertexAttribivNVPROC) (GLuint index, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetVertexAttribPointervNVPROC) (GLuint index, GLenum pname, GLvoid **pointer);
typedef GLboolean (APIENTRY * glIsProgramNVPROC) (GLuint id);
typedef void (APIENTRY * glLoadProgramNVPROC) (GLenum target, GLuint id, GLsizei len, const GLubyte *program);
typedef void (APIENTRY * glProgramParameter4fNVPROC) (GLenum target, GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glProgramParameter4dNVPROC) (GLenum target, GLuint index, GLdouble x, GLdouble y, GLdouble z, GLdouble w);
typedef void (APIENTRY * glProgramParameter4dvNVPROC) (GLenum target, GLuint index, const GLdouble *params);
typedef void (APIENTRY * glProgramParameter4fvNVPROC) (GLenum target, GLuint index, const GLfloat *params);
typedef void (APIENTRY * glProgramParameters4dvNVPROC) (GLenum target, GLuint index, GLuint num, const GLdouble *params);
typedef void (APIENTRY * glProgramParameters4fvNVPROC) (GLenum target, GLuint index, GLuint num, const GLfloat *params);
typedef void (APIENTRY * glTrackMatrixNVPROC) (GLenum target, GLuint address, GLenum matrix, GLenum transform);
typedef void (APIENTRY * glVertexAttribPointerNVPROC) (GLuint index, GLint size, GLenum type, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glVertexAttrib1sNVPROC) (GLuint index, GLshort x);
typedef void (APIENTRY * glVertexAttrib1fNVPROC) (GLuint index, GLfloat x);
typedef void (APIENTRY * glVertexAttrib1dNVPROC) (GLuint index, GLdouble x);
typedef void (APIENTRY * glVertexAttrib2sNVPROC) (GLuint index, GLshort x, GLshort y);
typedef void (APIENTRY * glVertexAttrib2fNVPROC) (GLuint index, GLfloat x, GLfloat y);
typedef void (APIENTRY * glVertexAttrib2dNVPROC) (GLuint index, GLdouble x, GLdouble y);
typedef void (APIENTRY * glVertexAttrib3sNVPROC) (GLuint index, GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY * glVertexAttrib3fNVPROC) (GLuint index, GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glVertexAttrib3dNVPROC) (GLuint index, GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glVertexAttrib4sNVPROC) (GLuint index, GLshort x, GLshort y, GLshort z, GLshort w);
typedef void (APIENTRY * glVertexAttrib4fNVPROC) (GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glVertexAttrib4dNVPROC) (GLuint index, GLdouble x, GLdouble y, GLdouble z, GLdouble w);
typedef void (APIENTRY * glVertexAttrib4ubNVPROC) (GLuint index, GLubyte x, GLubyte y, GLubyte z, GLubyte w);
typedef void (APIENTRY * glVertexAttrib1svNVPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib1fvNVPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib1dvNVPROC) (GLuint index, const GLdouble *v);
typedef void (APIENTRY * glVertexAttrib2svNVPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib2fvNVPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib2dvNVPROC) (GLuint index, const GLdouble *v);
typedef void (APIENTRY * glVertexAttrib3svNVPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib3fvNVPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib3dvNVPROC) (GLuint index, const GLdouble *v);
typedef void (APIENTRY * glVertexAttrib4svNVPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib4fvNVPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib4dvNVPROC) (GLuint index, const GLdouble *v);
typedef void (APIENTRY * glVertexAttrib4ubvNVPROC) (GLuint index, const GLubyte *v);
typedef void (APIENTRY * glVertexAttribs1svNVPROC) (GLuint index, GLsizei n, const GLshort *v);
typedef void (APIENTRY * glVertexAttribs1fvNVPROC) (GLuint index, GLsizei n, const GLfloat *v);
typedef void (APIENTRY * glVertexAttribs1dvNVPROC) (GLuint index, GLsizei n, const GLdouble *v);
typedef void (APIENTRY * glVertexAttribs2svNVPROC) (GLuint index, GLsizei n, const GLshort *v);
typedef void (APIENTRY * glVertexAttribs2fvNVPROC) (GLuint index, GLsizei n, const GLfloat *v);
typedef void (APIENTRY * glVertexAttribs2dvNVPROC) (GLuint index, GLsizei n, const GLdouble *v);
typedef void (APIENTRY * glVertexAttribs3svNVPROC) (GLuint index, GLsizei n, const GLshort *v);
typedef void (APIENTRY * glVertexAttribs3fvNVPROC) (GLuint index, GLsizei n, const GLfloat *v);
typedef void (APIENTRY * glVertexAttribs3dvNVPROC) (GLuint index, GLsizei n, const GLdouble *v);
typedef void (APIENTRY * glVertexAttribs4svNVPROC) (GLuint index, GLsizei n, const GLshort *v);
typedef void (APIENTRY * glVertexAttribs4fvNVPROC) (GLuint index, GLsizei n, const GLfloat *v);
typedef void (APIENTRY * glVertexAttribs4dvNVPROC) (GLuint index, GLsizei n, const GLdouble *v);
typedef void (APIENTRY * glVertexAttribs4ubvNVPROC) (GLuint index, GLsizei n, const GLubyte *v);

extern glBindProgramNVPROC glBindProgramNV;
extern glDeleteProgramsNVPROC glDeleteProgramsNV;
extern glExecuteProgramNVPROC glExecuteProgramNV;
extern glGenProgramsNVPROC glGenProgramsNV;
extern glAreProgramsResidentNVPROC glAreProgramsResidentNV;
extern glRequestResidentProgramsNVPROC glRequestResidentProgramsNV;
extern glGetProgramParameterfvNVPROC glGetProgramParameterfvNV;
extern glGetProgramParameterdvNVPROC glGetProgramParameterdvNV;
extern glGetProgramivNVPROC glGetProgramivNV;
extern glGetProgramStringNVPROC glGetProgramStringNV;
extern glGetTrackMatrixivNVPROC glGetTrackMatrixivNV;
extern glGetVertexAttribdvNVPROC glGetVertexAttribdvNV;
extern glGetVertexAttribfvNVPROC glGetVertexAttribfvNV;
extern glGetVertexAttribivNVPROC glGetVertexAttribivNV;
extern glGetVertexAttribPointervNVPROC glGetVertexAttribPointervNV;
extern glIsProgramNVPROC glIsProgramNV;
extern glLoadProgramNVPROC glLoadProgramNV;
extern glProgramParameter4fNVPROC glProgramParameter4fNV;
extern glProgramParameter4dNVPROC glProgramParameter4dNV;
extern glProgramParameter4dvNVPROC glProgramParameter4dvNV;
extern glProgramParameter4fvNVPROC glProgramParameter4fvNV;
extern glProgramParameters4dvNVPROC glProgramParameters4dvNV;
extern glProgramParameters4fvNVPROC glProgramParameters4fvNV;
extern glTrackMatrixNVPROC glTrackMatrixNV;
extern glVertexAttribPointerNVPROC glVertexAttribPointerNV;
extern glVertexAttrib1sNVPROC glVertexAttrib1sNV;
extern glVertexAttrib1fNVPROC glVertexAttrib1fNV;
extern glVertexAttrib1dNVPROC glVertexAttrib1dNV;
extern glVertexAttrib2sNVPROC glVertexAttrib2sNV;
extern glVertexAttrib2fNVPROC glVertexAttrib2fNV;
extern glVertexAttrib2dNVPROC glVertexAttrib2dNV;
extern glVertexAttrib3sNVPROC glVertexAttrib3sNV;
extern glVertexAttrib3fNVPROC glVertexAttrib3fNV;
extern glVertexAttrib3dNVPROC glVertexAttrib3dNV;
extern glVertexAttrib4sNVPROC glVertexAttrib4sNV;
extern glVertexAttrib4fNVPROC glVertexAttrib4fNV;
extern glVertexAttrib4dNVPROC glVertexAttrib4dNV;
extern glVertexAttrib4ubNVPROC glVertexAttrib4ubNV;
extern glVertexAttrib1svNVPROC glVertexAttrib1svNV;
extern glVertexAttrib1fvNVPROC glVertexAttrib1fvNV;
extern glVertexAttrib1dvNVPROC glVertexAttrib1dvNV;
extern glVertexAttrib2svNVPROC glVertexAttrib2svNV;
extern glVertexAttrib2fvNVPROC glVertexAttrib2fvNV;
extern glVertexAttrib2dvNVPROC glVertexAttrib2dvNV;
extern glVertexAttrib3svNVPROC glVertexAttrib3svNV;
extern glVertexAttrib3fvNVPROC glVertexAttrib3fvNV;
extern glVertexAttrib3dvNVPROC glVertexAttrib3dvNV;
extern glVertexAttrib4svNVPROC glVertexAttrib4svNV;
extern glVertexAttrib4fvNVPROC glVertexAttrib4fvNV;
extern glVertexAttrib4dvNVPROC glVertexAttrib4dvNV;
extern glVertexAttrib4ubvNVPROC glVertexAttrib4ubvNV;
extern glVertexAttribs1svNVPROC glVertexAttribs1svNV;
extern glVertexAttribs1fvNVPROC glVertexAttribs1fvNV;
extern glVertexAttribs1dvNVPROC glVertexAttribs1dvNV;
extern glVertexAttribs2svNVPROC glVertexAttribs2svNV;
extern glVertexAttribs2fvNVPROC glVertexAttribs2fvNV;
extern glVertexAttribs2dvNVPROC glVertexAttribs2dvNV;
extern glVertexAttribs3svNVPROC glVertexAttribs3svNV;
extern glVertexAttribs3fvNVPROC glVertexAttribs3fvNV;
extern glVertexAttribs3dvNVPROC glVertexAttribs3dvNV;
extern glVertexAttribs4svNVPROC glVertexAttribs4svNV;
extern glVertexAttribs4fvNVPROC glVertexAttribs4fvNV;
extern glVertexAttribs4dvNVPROC glVertexAttribs4dvNV;
extern glVertexAttribs4ubvNVPROC glVertexAttribs4ubvNV;

#endif /* GL_NV_vertex_program */

/*-------------------------------------------------------------------*/
/*------------NV_FENCE-----------------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_fence
#define GL_NV_fance 1

#define GL_ALL_COMPLETED_NV                                     0x84F2
#define GL_FENCE_STATUS_NV                                      0x84F3
#define GL_FENCE_CONDITION_NV                                   0x84F4

typedef void (APIENTRY * glGenFencesNVPROC) (GLsizei n, GLuint *fences);
typedef void (APIENTRY * glDeleteFencesNVPROC) (GLsizei n, const GLuint *fences);
typedef void (APIENTRY * glSetFenceNVPROC) (GLuint fence, GLenum condition);
typedef GLboolean (APIENTRY * glTestFenceNVPROC) (GLuint fence);
typedef void (APIENTRY * glFinishFenceNVPROC) (GLuint fence);
typedef GLboolean (APIENTRY * glIsFenceNVPROC) (GLuint fence);
typedef void (APIENTRY * glGetFenceivNVPROC) (GLuint fence, GLenum pname, GLint *params);

extern glGenFencesNVPROC glGenFencesNV;
extern glDeleteFencesNVPROC glDeleteFencesNV;
extern glSetFenceNVPROC glSetFenceNV;
extern glTestFenceNVPROC glTestFenceNV;
extern glFinishFenceNVPROC glFinishFenceNV;
extern glIsFenceNVPROC glIsFenceNV;
extern glGetFenceivNVPROC glGetFenceivNV;

#endif /* GL_NV_fence */

/*-------------------------------------------------------------------*/
/*------------NV_TEXTURE_SHADER2-------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_texture_shader2
#define GL_NV_texture_shader2

#define GL_DOT_PRODUCT_TEXTURE_3D_NV                            0x86EF
#define GL_HILO_NV                                              0x86F4
#define GL_DSDT_NV                                              0x86F5
#define GL_DSDT_MAG_NV                                          0x86F6
#define GL_DSDT_MAG_VIB_NV                                      0x86F7
#define GL_UNSIGNED_INT_S8_S8_8_8_NV                            0x86DA
#define GL_UNSIGNED_INT_8_8_S8_S8_REV_NV                        0x86DB
#define GL_SIGNED_RGBA_NV                                       0x86FB
#define GL_SIGNED_RGBA8_NV                                      0x86FC
#define GL_SIGNED_RGB_NV                                        0x86FE
#define GL_SIGNED_RGB8_NV                                       0x86FF
#define GL_SIGNED_LUMINANCE_NV                                  0x8701
#define GL_SIGNED_LUMINANCE8_NV                                 0x8702
#define GL_SIGNED_LUMINANCE_ALPHA_NV                            0x8703
#define GL_SIGNED_LUMINANCE8_ALPHA8_NV                          0x8704
#define GL_SIGNED_ALPHA_NV                                      0x8705
#define GL_SIGNED_ALPHA8_NV                                     0x8706
#define GL_SIGNED_INTENSITY_NV                                  0x8707
#define GL_SIGNED_INTENSITY8_NV                                 0x8708
#define GL_SIGNED_RGB_UNSIGNED_ALPHA_NV                         0x870C
#define GL_SIGNED_RGB8_UNSIGNED_ALPHA8_NV                       0x870D
#define GL_HILO16_NV                                            0x86F8
#define GL_SIGNED_HILO_NV                                       0x86F9
#define GL_SIGNED_HILO16_NV                                     0x86FA
#define GL_DSDT8_NV                                             0x8709
#define GL_DSDT8_MAG8_NV                                        0x870A
#define GL_DSDT_MAG_INTENSITY_NV                                0x86DC
#define GL_DSDT8_MAG8_INTENSITY8_NV                             0x870B 

#endif /* GL_NV_texture_shader2 */

/*-------------------------------------------------------------------*/
/*------------NV_BLEND_SQUARE----------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_blend_square
#define GL_NV_blend_square 1

#endif /* GL_NV_blend_square */

/*-------------------------------------------------------------------*/
/*------------NV_LIGHT_MAX_EXPONENT----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_light_max_exponent
#define GL_NV_light_max_exponent 1

#define GL_MAX_SHININESS_NV                                     0x8504
#define GL_MAX_SPOT_EXPONENT_NV                                 0x8505

#endif /* GL_NV_light_max_exponent */

/*-------------------------------------------------------------------*/
/*------------NV_PACKED_DEPTH_STENCIL--------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_packed_depth_stencil
#define GL_NV_packed_depth_stencil 1

#define GL_DEPTH_STENCIL_NV                                     0x84F9
#define GL_UNSIGNED_INT_24_8_NV                                 0x84FA

#endif /* GL_NV_packed_depth_stencil */

/*-------------------------------------------------------------------*/
/*------------NV_REGISTER_COMBINERS2---------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_register_combiners2
#define GL_NV_register_combiners2

#define GL_PER_STAGE_CONSTANTS_NV                               0x8535

typedef void (APIENTRY * glCombinerStageParameterfvNVPROC) (GLenum stage, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glGetCombinerStageParameterfvNVPROC) (GLenum stage, GLenum pname, GLfloat *params);

extern glCombinerStageParameterfvNVPROC glCombinerStageParameterfvNV;
extern glGetCombinerStageParameterfvNVPROC glGetCombinerStageParameterfvNV;

#endif /* GL_NV_register_combiners2 */

/*-------------------------------------------------------------------*/
/*------------EXT_ABGR-----------------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_EXT_abgr
#define GL_EXT_abgr 1

#define GL_ABGR_EXT                                             0x8000

#endif /* GL_EXT_abgr */

/*-------------------------------------------------------------------*/
/*------------EXT_STENCIL_WRAP---------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_EXT_stencil_wrap
#define GL_EXT_stencil_wrap 1

#define GL_INCR_WRAP_EXT             0x8507
#define GL_DECR_WRAP_EXT             0x8508

#endif /* GL_EXT_stencil_wrap */

/*-------------------------------------------------------------------*/
/*------------EXT_TEXTURE_LOD_BIAS-----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_EXT_texture_lod_bias
#define GL_EXT_texture_lod_bias 1

#define GL_TEXTURE_FILTER_CONTROL_EXT                           0x8500
#define GL_TEXTURE_LOD_BIAS_EXT                                 0x8501
#define GL_MAX_TEXTURE_LOD_BIAS_EXT                             0x84FD

#endif /* GL_EXT_texture_lod_bias */

/*-------------------------------------------------------------------*/
/*------------NV_EVALUATORS------------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_evaluators
#define GL_NV_evaluators 1

#define GL_EVAL_2D_NV                                           0x86C0
#define GL_EVAL_TRIANGULAR_2D_NV                                0x86C1
#define GL_MAP_TESSELLATION_NV                                  0x86C2
#define GL_MAP_ATTRIB_U_ORDER_NV                                0x86C3
#define GL_MAP_ATTRIB_V_ORDER_NV                                0x86C4
#define GL_EVAL_FRACTIONAL_TESSELLATION_NV                      0x86C5
#define GL_EVAL_VERTEX_ATTRIB0_NV                               0x86C6
#define GL_EVAL_VERTEX_ATTRIB1_NV                               0x86C7
#define GL_EVAL_VERTEX_ATTRIB2_NV                               0x86C8
#define GL_EVAL_VERTEX_ATTRIB3_NV                               0x86C9
#define GL_EVAL_VERTEX_ATTRIB4_NV                               0x86CA
#define GL_EVAL_VERTEX_ATTRIB5_NV                               0x86CB
#define GL_EVAL_VERTEX_ATTRIB6_NV                               0x86CC
#define GL_EVAL_VERTEX_ATTRIB7_NV                               0x86CD
#define GL_EVAL_VERTEX_ATTRIB8_NV                               0x86CE
#define GL_EVAL_VERTEX_ATTRIB9_NV                               0x86CF
#define GL_EVAL_VERTEX_ATTRIB10_NV                              0x86D0
#define GL_EVAL_VERTEX_ATTRIB11_NV                              0x86D1
#define GL_EVAL_VERTEX_ATTRIB12_NV                              0x86D2
#define GL_EVAL_VERTEX_ATTRIB13_NV                              0x86D3
#define GL_EVAL_VERTEX_ATTRIB14_NV                              0x86D4
#define GL_EVAL_VERTEX_ATTRIB15_NV                              0x86D5
#define GL_MAX_MAP_TESSELLATION_NV                              0x86D6
#define GL_MAX_RATIONAL_EVAL_ORDER_NV                           0x86D7

typedef void (APIENTRY * glMapControlPointsNVPROC) (GLenum target, GLuint index, GLenum type, GLsizei ustride, GLsizei vstride, GLint uorder, GLint vorder, GLboolean packed, const GLvoid *points);
typedef void (APIENTRY * glMapParameterivNVPROC) (GLenum target, GLenum pname, const GLint *params);
typedef void (APIENTRY * glMapParameterfvNVPROC) (GLenum target, GLenum pname, const GLfloat *params);
typedef void (APIENTRY * glGetMapControlPointsNVPROC) (GLenum target, GLuint index, GLenum type, GLsizei ustride, GLsizei vstride, GLboolean packed, GLvoid *points);
typedef void (APIENTRY * glGetMapParameterivNVPROC) (GLenum target, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetMapParameterfvNVPROC) (GLenum target, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetMapAttribParameterivNVPROC) (GLenum target, GLuint index, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetMapAttribParameterfvNVPROC) (GLenum target, GLuint index, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glEvalMapsNVPROC) (GLenum target, GLenum mode);

extern glMapControlPointsNVPROC glMapControlPointsNV;
extern glMapParameterivNVPROC glMapParameterivNV;
extern glMapParameterfvNVPROC glMapParameterfvNV;
extern glGetMapControlPointsNVPROC glGetMapControlPointsNV;
extern glGetMapParameterivNVPROC glGetMapParameterivNV;
extern glGetMapParameterfvNVPROC glGetMapParameterfvNV;
extern glGetMapAttribParameterivNVPROC glGetMapAttribParameterivNV;
extern glGetMapAttribParameterfvNVPROC glGetMapAttribParameterfvNV;
extern glEvalMapsNVPROC glEvalMapsNV;

#endif /* GL_NV_evaluators */

/*-------------------------------------------------------------------*/
/*------------NV_COPY_DEPTH_TO_COLOR---------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_copy_depth_to_color
#define GL_NV_copy_depth_to_color 1

#define GL_DEPTH_STENCIL_TO_RGBA_NV                             0x886E
#define GL_DEPTH_STENCIL_TO_BGRA_NV                             0x886F

#endif /* GL_NV_copy_depth_to_color */

/*-------------------------------------------------------------------*/
/*------------ATI_PN_TRIANGLES---------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ATI_pn_triangles
#define GL_ATI_pn_triangles 1

#define GL_PN_TRIANGLES_ATI                                     0x87F0
#define GL_MAX_PN_TRIANGLES_TESSELATION_LEVEL_ATI               0x87F1
#define GL_PN_TRIANGLES_POINT_MODE_ATI                          0x87F2
#define GL_PN_TRIANGLES_NORMAL_MODE_ATI                         0x87F3
#define GL_PN_TRIANGLES_TESSELATION_LEVEL_ATI                   0x87F4
#define GL_PN_TRIANGLES_POINT_MODE_LINEAR_ATI                   0x87F5
#define GL_PN_TRIANGLES_POINT_MODE_CUBIC_ATI                    0x87F6
#define GL_PN_TRIANGLES_NORMAL_MODE_LINEAR_ATI                  0x87F7
#define GL_PN_TRIANGLES_NORMAL_MODE_QUADRATIC_ATI               0x87F8

typedef void (APIENTRY * glPNTrianglesiATIPROC) (GLenum pname, GLint param);
typedef void (APIENTRY * glPNTrianglesfATIPROC) (GLenum pname, GLfloat param);

extern glPNTrianglesiATIPROC glPNTrianglesiATI;
extern glPNTrianglesfATIPROC glPNTrianglesfATI;

#endif /* GL_ATI_pn_triangles */

/*-------------------------------------------------------------------*/
/*------------ARB_POINT_PARAMETERS-----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_point_parameters
#define GL_ARB_point_parameters 1

#define GL_POINT_SIZE_MIN_ARB                                   0x8126
#define GL_POINT_SIZE_MAX_ARB                                   0x8127
#define GL_POINT_FADE_THRESHOLD_SIZE_ARB                        0x8128
#define GL_POINT_DISTANCE_ATTENUATION_ARB                       0x8129

typedef void (APIENTRY * glPointParameterfARBPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glPointParameterfvARBPROC) (GLenum pname, GLfloat *params);

extern glPointParameterfARBPROC glPointParameterfARB;
extern glPointParameterfvARBPROC glPointParameterfvARB;

#endif /* GL_ARB_point_parameters */

/*-------------------------------------------------------------------*/
/*------------ARB_TEXTURE_ENV_CROSSBAR-------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_texture_env_crossbar
#define GL_ARB_texture_env_crossbar 1

#endif

/*-------------------------------------------------------------------*/
/*------------ARB_VERTEX_BLEND---------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_vertex_blend
#define GL_ARB_vertex_blend 1

#define GL_MAX_VERTEX_UNITS_ARB                                 0x86A4
#define GL_ACTIVE_VERTEX_UNITS_ARB                              0x86A5
#define GL_WEIGHT_SUM_UNITY_ARB                                 0x86A6      
#define GL_VERTEX_BLEND_ARB                                     0x86A7
#define GL_CURRENT_WEIGHT_ARB                                   0x86A8
#define GL_WEIGHT_ARRAY_TYPE_ARB                                0x86A9
#define GL_WEIGHT_ARRAY_STRIDE_ARB                              0x86AA
#define GL_WEIGHT_ARRAY_SIZE_ARB                                0x86AB
#define GL_WEIGHT_ARRAY_POINTER_ARB                             0x86AC
#define GL_WEIGHT_ARRAY_ARB                                     0x86AD
#define GL_MODELVIEW0_ARB                                       0x1700
#define GL_MODELVIEW1_ARB                                       0x850a
#define GL_MODELVIEW2_ARB                                       0x8722
#define GL_MODELVIEW3_ARB                                       0x8723
#define GL_MODELVIEW4_ARB                                       0x8724
#define GL_MODELVIEW5_ARB                                       0x8725
#define GL_MODELVIEW6_ARB                                       0x8726
#define GL_MODELVIEW7_ARB                                       0x8727
#define GL_MODELVIEW8_ARB                                       0x8728
#define GL_MODELVIEW9_ARB                                       0x8729
#define GL_MODELVIEW10_ARB                                      0x872A
#define GL_MODELVIEW11_ARB                                      0x872B
#define GL_MODELVIEW12_ARB                                      0x872C
#define GL_MODELVIEW13_ARB                                      0x872D
#define GL_MODELVIEW14_ARB                                      0x872E
#define GL_MODELVIEW15_ARB                                      0x872F
#define GL_MODELVIEW16_ARB                                      0x8730
#define GL_MODELVIEW17_ARB                                      0x8731
#define GL_MODELVIEW18_ARB                                      0x8732
#define GL_MODELVIEW19_ARB                                      0x8733
#define GL_MODELVIEW20_ARB                                      0x8734
#define GL_MODELVIEW21_ARB                                      0x8735
#define GL_MODELVIEW22_ARB                                      0x8736
#define GL_MODELVIEW23_ARB                                      0x8737
#define GL_MODELVIEW24_ARB                                      0x8738
#define GL_MODELVIEW25_ARB                                      0x8739
#define GL_MODELVIEW26_ARB                                      0x873A
#define GL_MODELVIEW27_ARB                                      0x873B
#define GL_MODELVIEW28_ARB                                      0x873C
#define GL_MODELVIEW29_ARB                                      0x873D
#define GL_MODELVIEW30_ARB                                      0x873E
#define GL_MODELVIEW31_ARB                                      0x873F

typedef void (APIENTRY * glWeightbvARBPROC) (GLint size, GLbyte *weights);
typedef void (APIENTRY * glWeightsvARBPROC) (GLint size, GLshort *weights);
typedef void (APIENTRY * glWeightivARBPROC) (GLint size, GLint *weights);
typedef void (APIENTRY * glWeightfvARBPROC) (GLint size, GLfloat *weights);
typedef void (APIENTRY * glWeightdvARBPROC) (GLint size, GLdouble *weights);
typedef void (APIENTRY * glWeightubvARBPROC) (GLint size, GLubyte *weights);
typedef void (APIENTRY * glWeightusvARBPROC) (GLint size, GLushort *weights);
typedef void (APIENTRY * glWeightuivARBPROC) (GLint size, GLuint *weights);
typedef void (APIENTRY * glWeightPointerARBPROC) (GLint size, GLenum type, GLsizei stride, GLvoid *pointer);
typedef void (APIENTRY * glVertexBlendARBPROC) (GLint count);

extern glWeightbvARBPROC glWeightbvARB;
extern glWeightsvARBPROC glWeightsvARB;
extern glWeightivARBPROC glWeightivARB;
extern glWeightfvARBPROC glWeightfvARB;
extern glWeightdvARBPROC glWeightdvARB;
extern glWeightubvARBPROC glWeightubvARB;
extern glWeightusvARBPROC glWeightusvARB;
extern glWeightuivARBPROC glWeightuivARB;
extern glWeightPointerARBPROC glWeightPointerARB;
extern glVertexBlendARBPROC glVertexBlendARB;

#endif /* GL_ARB_vertex_blend */

/*-------------------------------------------------------------------*/
/*------------EXT_MULTI_DRAW_ARRAYS----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_EXT_multi_draw_arrays
#define GL_EXT_multi_draw_arrays 1

typedef void (APIENTRY * glMultiDrawArraysEXTPROC) (GLenum mode, GLint *first, GLsizei *count, GLsizei primcount);
typedef void (APIENTRY * glMultiDrawElementsEXTPROC) (GLenum mode, GLsizei *count, GLenum type, const GLvoid **indices, GLsizei primcount);

extern glMultiDrawArraysEXTPROC glMultiDrawArraysEXT;
extern glMultiDrawElementsEXTPROC glMultiDrawElementsEXT;

#endif /* GL_EXT_multi_draw_arrays */

/*-------------------------------------------------------------------*/
/*------------ARB_MATRIX_PALETTE-------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_matrix_palette
#define GL_ARB_matrix_palette 1

#define GL_MATRIX_PALETTE_ARB                                   0x8840
#define GL_MAX_MATRIX_PALETTE_STACK_DEPTH_ARB                   0x8841
#define GL_MAX_PALETTE_MATRICES_ARB                             0x8842
#define GL_CURRENT_PALETTE_MATRIX_ARB                           0x8843
#define GL_MATRIX_INDEX_ARRAY_ARB                               0x8844
#define GL_CURRENT_MATRIX_INDEX_ARB                             0x8845
#define GL_MATRIX_INDEX_ARRAY_SIZE_ARB                          0x8846
#define GL_MATRIX_INDEX_ARRAY_TYPE_ARB                          0x8847
#define GL_MATRIX_INDEX_ARRAY_STRIDE_ARB                        0x8848
#define GL_MATRIX_INDEX_ARRAY_POINTER_ARB                       0x8849

typedef void (APIENTRY * glCurrentPaletteMatrixARBPROC) (GLint index);
typedef void (APIENTRY * glMatrixIndexubvARBPROC) (GLint size, GLubyte *indices);
typedef void (APIENTRY * glMatrixIndexusvARBPROC) (GLint size, GLushort *indices);
typedef void (APIENTRY * glMatrixIndexuivARBPROC) (GLint size, GLuint *indices);
typedef void (APIENTRY * glMatrixIndexPointerARBPROC) (GLint size, GLenum type, GLsizei stride, GLvoid *pointer);

extern glCurrentPaletteMatrixARBPROC glCurrentPaletteMatrixARB;
extern glMatrixIndexubvARBPROC glMatrixIndexubvARB;
extern glMatrixIndexusvARBPROC glMatrixIndexusvARB;
extern glMatrixIndexuivARBPROC glMatrixIndexuivARB;
extern glMatrixIndexPointerARBPROC glMatrixIndexPointerARB;

#endif /* GL_ARB_matrix_palette */

/*-------------------------------------------------------------------*/
/*------------EXT_VERTEX_SHADER--------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_EXT_vertex_shader
#define GL_EXT_vertex_shader 1

#define GL_VERTEX_SHADER_EXT                                    0x8780
#define GL_VERTEX_SHADER_BINDING_EXT                            0x8781
#define GL_OP_INDEX_EXT                                         0x8782
#define GL_OP_NEGATE_EXT                                        0x8783
#define GL_OP_DOT3_EXT                                          0x8784
#define GL_OP_DOT4_EXT                                          0x8785
#define GL_OP_MUL_EXT                                           0x8786
#define GL_OP_ADD_EXT                                           0x8787
#define GL_OP_MADD_EXT                                          0x8788
#define GL_OP_FRAC_EXT                                          0x8789
#define GL_OP_MAX_EXT                                           0x878A
#define GL_OP_MIN_EXT                                           0x878B
#define GL_OP_SET_GE_EXT                                        0x878C
#define GL_OP_SET_LT_EXT                                        0x878D
#define GL_OP_CLAMP_EXT                                         0x878E
#define GL_OP_FLOOR_EXT                                         0x878F
#define GL_OP_ROUND_EXT                                         0x8790
#define GL_OP_EXP_BASE_2_EXT                                    0x8791
#define GL_OP_LOG_BASE_2_EXT                                    0x8792
#define GL_OP_POWER_EXT                                         0x8793
#define GL_OP_RECIP_EXT                                         0x8794
#define GL_OP_RECIP_SQRT_EXT                                    0x8795
#define GL_OP_SUB_EXT                                           0x8796
#define GL_OP_CROSS_PRODUCT_EXT                                 0x8797
#define GL_OP_MULTIPLY_MATRIX_EXT                               0x8798
#define GL_OP_MOV_EXT                                           0x8799
#define GL_OUTPUT_VERTEX_EXT                                    0x879A
#define GL_OUTPUT_COLOR0_EXT                                    0x879B
#define GL_OUTPUT_COLOR1_EXT                                    0x879C
#define GL_OUTPUT_TEXTURE_COORD0_EXT                            0x879D
#define GL_OUTPUT_TEXTURE_COORD1_EXT                            0x879E
#define GL_OUTPUT_TEXTURE_COORD2_EXT                            0x879F
#define GL_OUTPUT_TEXTURE_COORD3_EXT                            0x87A0
#define GL_OUTPUT_TEXTURE_COORD4_EXT                            0x87A1
#define GL_OUTPUT_TEXTURE_COORD5_EXT                            0x87A2
#define GL_OUTPUT_TEXTURE_COORD6_EXT                            0x87A3
#define GL_OUTPUT_TEXTURE_COORD7_EXT                            0x87A4
#define GL_OUTPUT_TEXTURE_COORD8_EXT                            0x87A5
#define GL_OUTPUT_TEXTURE_COORD9_EXT                            0x87A6
#define GL_OUTPUT_TEXTURE_COORD10_EXT                           0x87A7
#define GL_OUTPUT_TEXTURE_COORD11_EXT                           0x87A8
#define GL_OUTPUT_TEXTURE_COORD12_EXT                           0x87A9
#define GL_OUTPUT_TEXTURE_COORD13_EXT                           0x87AA
#define GL_OUTPUT_TEXTURE_COORD14_EXT                           0x87AB
#define GL_OUTPUT_TEXTURE_COORD15_EXT                           0x87AC
#define GL_OUTPUT_TEXTURE_COORD16_EXT                           0x87AD
#define GL_OUTPUT_TEXTURE_COORD17_EXT                           0x87AE
#define GL_OUTPUT_TEXTURE_COORD18_EXT                           0x87AF
#define GL_OUTPUT_TEXTURE_COORD19_EXT                           0x87B0
#define GL_OUTPUT_TEXTURE_COORD20_EXT                           0x87B1
#define GL_OUTPUT_TEXTURE_COORD21_EXT                           0x87B2
#define GL_OUTPUT_TEXTURE_COORD22_EXT                           0x87B3
#define GL_OUTPUT_TEXTURE_COORD23_EXT                           0x87B4
#define GL_OUTPUT_TEXTURE_COORD24_EXT                           0x87B5
#define GL_OUTPUT_TEXTURE_COORD25_EXT                           0x87B6
#define GL_OUTPUT_TEXTURE_COORD26_EXT                           0x87B7
#define GL_OUTPUT_TEXTURE_COORD27_EXT                           0x87B8
#define GL_OUTPUT_TEXTURE_COORD28_EXT                           0x87B9
#define GL_OUTPUT_TEXTURE_COORD29_EXT                           0x87BA
#define GL_OUTPUT_TEXTURE_COORD30_EXT                           0x87BB
#define GL_OUTPUT_TEXTURE_COORD31_EXT                           0x87BC
#define GL_OUTPUT_FOG_EXT                                       0x87BD
#define GL_SCALAR_EXT                                           0x87BE
#define GL_VECTOR_EXT                                           0x87BF
#define GL_MATRIX_EXT                                           0x87C0
#define GL_VARIANT_EXT                                          0x87C1
#define GL_INVARIANT_EXT                                        0x87C2
#define GL_LOCAL_CONSTANT_EXT                                   0x87C3
#define GL_LOCAL_EXT                                            0x87C4
#define GL_MAX_VERTEX_SHADER_INSTRUCTIONS_EXT                   0x87C5
#define GL_MAX_VERTEX_SHADER_VARIANTS_EXT                       0x87C6
#define GL_MAX_VERTEX_SHADER_INVARIANTS_EXT                     0x87C7
#define GL_MAX_VERTEX_SHADER_LOCAL_CONSTANTS_EXT                0x87C8
#define GL_MAX_VERTEX_SHADER_LOCALS_EXT                         0x87C9
#define GL_MAX_OPTIMIZED_VERTEX_SHADER_INSTRUCTIONS_EXT         0x87CA
#define GL_MAX_OPTIMIZED_VERTEX_SHADER_VARIANTS_EXT             0x87CB
#define GL_MAX_OPTIMIZED_VERTEX_SHADER_INVARIANTS_EXT           0x87CC
#define GL_MAX_OPTIMIZED_VERTEX_SHADER_LOCAL_CONSTANTS_EXT      0x87CD
#define GL_MAX_OPTIMIZED_VERTEX_SHADER_LOCALS_EXT               0x87CE
#define GL_VERTEX_SHADER_INSTRUCTIONS_EXT                       0x87CF
#define GL_VERTEX_SHADER_VARIANTS_EXT                           0x87D0
#define GL_VERTEX_SHADER_INVARIANTS_EXT                         0x87D1
#define GL_VERTEX_SHADER_LOCAL_CONSTANTS_EXT                    0x87D2
#define GL_VERTEX_SHADER_LOCALS_EXT                             0x87D3
#define GL_VERTEX_SHADER_OPTIMIZED_EXT                          0x87D4
#define GL_X_EXT                                                0x87D5
#define GL_Y_EXT                                                0x87D6
#define GL_Z_EXT                                                0x87D7
#define GL_W_EXT                                                0x87D8
#define GL_NEGATIVE_X_EXT                                       0x87D9
#define GL_NEGATIVE_Y_EXT                                       0x87DA
#define GL_NEGATIVE_Z_EXT                                       0x87DB
#define GL_NEGATIVE_W_EXT                                       0x87DC
#define GL_ZERO_EXT                                             0x87DD
#define GL_ONE_EXT                                              0x87DE
#define GL_NEGATIVE_ONE_EXT                                     0x87DF
#define GL_NORMALIZED_RANGE_EXT                                 0x87E0
#define GL_FULL_RANGE_EXT                                       0x87E1
#define GL_CURRENT_VERTEX_EXT                                   0x87E2
#define GL_MVP_MATRIX_EXT                                       0x87E3
#define GL_VARIANT_VALUE_EXT                                    0x87E4
#define GL_VARIANT_DATATYPE_EXT                                 0x87E5
#define GL_VARIANT_ARRAY_STRIDE_EXT                             0x87E6
#define GL_VARIANT_ARRAY_TYPE_EXT                               0x87E7
#define GL_VARIANT_ARRAY_EXT                                    0x87E8
#define GL_VARIANT_ARRAY_POINTER_EXT                            0x87E9
#define GL_INVARIANT_VALUE_EXT                                  0x87EA
#define GL_INVARIANT_DATATYPE_EXT                               0x87EB
#define GL_LOCAL_CONSTANT_VALUE_EXT                             0x87EC
#define GL_LOCAL_CONSTANT_DATATYPE_EXT                          0x87ED

typedef void (APIENTRY * glBeginVertexShaderEXTPROC) ();
typedef void (APIENTRY * glEndVertexShaderEXTPROC) ();
typedef void (APIENTRY * glBindVertexShaderEXTPROC) (GLuint id);
typedef GLuint (APIENTRY * glGenVertexShadersEXTPROC) (GLuint range);
typedef void (APIENTRY * glDeleteVertexShaderEXTPROC) (GLuint id);
typedef void (APIENTRY * glShaderOp1EXTPROC) (GLenum op, GLuint res, GLuint arg1);
typedef void (APIENTRY * glShaderOp2EXTPROC) (GLenum op, GLuint res, GLuint arg1, GLuint arg2);
typedef void (APIENTRY * glShaderOp3EXTPROC) (GLenum op, GLuint res, GLuint arg1, GLuint arg2, GLuint arg3);
typedef void (APIENTRY * glSwizzleEXTPROC) (GLuint res, GLuint in, GLenum outX, GLenum outY, GLenum outZ, GLenum outW);
typedef void (APIENTRY * glWriteMaskEXTPROC) (GLuint res, GLuint in, GLenum outX, GLenum outY, GLenum outZ, GLenum outW);
typedef void (APIENTRY * glInsertComponentEXTPROC) (GLuint res, GLuint src, GLuint num);
typedef void (APIENTRY * glExtractComponentEXTPROC) (GLuint res, GLuint src, GLuint num);
typedef GLuint (APIENTRY * glGenSymbolsEXTPROC) (GLenum dataType, GLenum storageType, GLenum range, GLuint components);
typedef void (APIENTRY * glSetInvariantEXTPROC) (GLuint id, GLenum type, GLvoid *addr);
typedef void (APIENTRY * glSetLocalConstantEXTPROC) (GLuint id, GLenum type, GLvoid *addr);
typedef void (APIENTRY * glVariantbvEXTPROC) (GLuint id, GLbyte *addr);
typedef void (APIENTRY * glVariantsvEXTPROC) (GLuint id, GLshort *addr);
typedef void (APIENTRY * glVariantivEXTPROC) (GLuint id, GLint *addr);
typedef void (APIENTRY * glVariantfvEXTPROC) (GLuint id, GLfloat *addr);
typedef void (APIENTRY * glVariantdvEXTPROC) (GLuint id, GLdouble *addr);
typedef void (APIENTRY * glVariantubvEXTPROC) (GLuint id, GLubyte *addr);
typedef void (APIENTRY * glVariantusvEXTPROC) (GLuint id, GLushort *addr);
typedef void (APIENTRY * glVariantuivEXTPROC) (GLuint id, GLuint *addr);
typedef void (APIENTRY * glVariantPointerEXTPROC) (GLuint id, GLenum type, GLuint stride, GLvoid *addr);
typedef void (APIENTRY * glEnableVariantClientStateEXTPROC) (GLuint id);
typedef void (APIENTRY * glDisableVariantClientStateEXTPROC) (GLuint id);
typedef GLuint (APIENTRY * glBindLightParameterEXTPROC) (GLenum light, GLenum value);
typedef GLuint (APIENTRY * glBindMaterialParameterEXTPROC) (GLenum face, GLenum value);
typedef GLuint (APIENTRY * glBindTexGenParameterEXTPROC) (GLenum unit, GLenum coord, GLenum value);
typedef GLuint (APIENTRY * glBindTextureUnitParameterEXTPROC) (GLenum unit, GLenum value);
typedef GLuint (APIENTRY * glBindParameterEXTPROC) (GLenum value);
typedef GLboolean (APIENTRY * glIsVariantEnabledEXTPROC) (GLuint id, GLenum cap);
typedef void (APIENTRY * glGetVariantBooleanvEXTPROC) (GLuint id, GLenum value, GLboolean *data);
typedef void (APIENTRY * glGetVariantIntegervEXTPROC) (GLuint id, GLenum value, GLint *data);
typedef void (APIENTRY * glGetVariantFloatvEXTPROC) (GLuint id, GLenum value, GLfloat *data);
typedef void (APIENTRY * glGetVariantPointervEXTPROC) (GLuint id, GLenum value, GLvoid **data);
typedef void (APIENTRY * glGetInvariantBooleanvEXTPROC) (GLuint id, GLenum value, GLboolean *data);
typedef void (APIENTRY * glGetInvariantIntegervEXTPROC) (GLuint id, GLenum value, GLint *data);
typedef void (APIENTRY * glGetInvariantFloatvEXTPROC) (GLuint id, GLenum value, GLfloat *data);
typedef void (APIENTRY * glGetLocalConstantBooleanvEXTPROC) (GLuint id, GLenum value, GLboolean *data);
typedef void (APIENTRY * glGetLocalConstantIntegervEXTPROC) (GLuint id, GLenum value, GLint *data);
typedef void (APIENTRY * glGetLocalConstantFloatvEXTPROC) (GLuint id, GLenum value, GLfloat *data);

extern glBeginVertexShaderEXTPROC glBeginVertexShaderEXT;
extern glEndVertexShaderEXTPROC glEndVertexShaderEXT;
extern glBindVertexShaderEXTPROC glBindVertexShaderEXT;
extern glGenVertexShadersEXTPROC glGenVertexShadersEXT;
extern glDeleteVertexShaderEXTPROC glDeleteVertexShaderEXT;
extern glShaderOp1EXTPROC glShaderOp1EXT;
extern glShaderOp2EXTPROC glShaderOp2EXT;
extern glShaderOp3EXTPROC glShaderOp3EXT;
extern glSwizzleEXTPROC glSwizzleEXT;
extern glWriteMaskEXTPROC glWriteMaskEXT;
extern glInsertComponentEXTPROC glInsertComponentEXT;
extern glExtractComponentEXTPROC glExtractComponentEXT;
extern glGenSymbolsEXTPROC glGenSymbolsEXT;
extern glSetInvariantEXTPROC glSetInvariantEXT;
extern glSetLocalConstantEXTPROC glSetLocalConstantEXT;
extern glVariantbvEXTPROC glVariantbvEXT;
extern glVariantsvEXTPROC glVariantsvEXT;
extern glVariantivEXTPROC glVariantivEXT;
extern glVariantfvEXTPROC glVariantfvEXT;
extern glVariantdvEXTPROC glVariantdvEXT;
extern glVariantubvEXTPROC glVariantubvEXT;
extern glVariantusvEXTPROC glVariantusvEXT;
extern glVariantuivEXTPROC glVariantuivEXT;
extern glVariantPointerEXTPROC glVariantPointerEXT;
extern glEnableVariantClientStateEXTPROC glEnableVariantClientStateEXT;
extern glDisableVariantClientStateEXTPROC glDisableVariantClientStateEXT;
extern glBindLightParameterEXTPROC glBindLightParameterEXT;
extern glBindMaterialParameterEXTPROC glBindMaterialParameterEXT;
extern glBindTexGenParameterEXTPROC glBindTexGenParameterEXT;
extern glBindTextureUnitParameterEXTPROC glBindTextureUnitParameterEXT;
extern glBindParameterEXTPROC glBindParameterEXT;
extern glIsVariantEnabledEXTPROC glIsVariantEnabledEXT;
extern glGetVariantBooleanvEXTPROC glGetVariantBooleanvEXT;
extern glGetVariantIntegervEXTPROC glGetVariantIntegervEXT;
extern glGetVariantFloatvEXTPROC glGetVariantFloatvEXT;
extern glGetVariantPointervEXTPROC glGetVariantPointervEXT;
extern glGetInvariantBooleanvEXTPROC glGetInvariantBooleanvEXT;
extern glGetInvariantIntegervEXTPROC glGetInvariantIntegervEXT;
extern glGetInvariantFloatvEXTPROC glGetInvariantFloatvEXT;
extern glGetLocalConstantBooleanvEXTPROC glGetLocalConstantBooleanvEXT;
extern glGetLocalConstantIntegervEXTPROC glGetLocalConstantIntegervEXT;
extern glGetLocalConstantFloatvEXTPROC glGetLocalConstantFloatvEXT;

#endif /* GL_EXT_vertex_shader */

/*-------------------------------------------------------------------*/
/*------------ATI_ENVMAP_BUMPMAP-------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ATI_envmap_bumpmap
#define GL_ATI_envmap_bumpmap 1

#define GL_BUMP_ROT_MATRIX_ATI                                  0x8775
#define GL_BUMP_ROT_MATRIX_SIZE_ATI                             0x8776
#define GL_BUMP_NUM_TEX_UNITS_ATI                               0x8777
#define GL_BUMP_TEX_UNITS_ATI                                   0x8778
#define GL_DUDV_ATI                                             0x8779
#define GL_DU8DV8_ATI                                           0x877A
#define GL_BUMP_ENVMAP_ATI                                      0x877B
#define GL_BUMP_TARGET_ATI                                      0x877C

typedef void (APIENTRY * glTexBumpParameterivATIPROC) (GLenum pname, GLint *param);
typedef void (APIENTRY * glTexBumpParameterfvATIPROC) (GLenum pname, GLfloat *param);
typedef void (APIENTRY * glGetTexBumpParameterivATIPROC) (GLenum pname, GLint *param);
typedef void (APIENTRY * glGetTexBumpParameterfvATIPROC) (GLenum pname, GLfloat *param);

extern glTexBumpParameterivATIPROC glTexBumpParameterivATI;
extern glTexBumpParameterfvATIPROC glTexBumpParameterfvATI;
extern glGetTexBumpParameterivATIPROC glGetTexBumpParameterivATI;
extern glGetTexBumpParameterfvATIPROC glGetTexBumpParameterfvATI;

#endif /* GL_ATI_envmap_bumpmap */

/*-------------------------------------------------------------------*/
/*------------ATI_FRAGMENT_SHADER------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ATI_fragment_shader
#define GL_ATI_fragment_shader 1

#define GL_FRAGMENT_SHADER_ATI                                  0x8920
#define GL_REG_0_ATI                                            0x8921
#define GL_REG_1_ATI                                            0x8922
#define GL_REG_2_ATI                                            0x8923
#define GL_REG_3_ATI                                            0x8924
#define GL_REG_4_ATI                                            0x8925
#define GL_REG_5_ATI                                            0x8926
#define GL_REG_6_ATI                                            0x8927
#define GL_REG_7_ATI                                            0x8928
#define GL_REG_8_ATI                                            0x8929
#define GL_REG_9_ATI                                            0x892A
#define GL_REG_10_ATI                                           0x892B
#define GL_REG_11_ATI                                           0x892C
#define GL_REG_12_ATI                                           0x892D
#define GL_REG_13_ATI                                           0x892E
#define GL_REG_14_ATI                                           0x892F
#define GL_REG_15_ATI                                           0x8930
#define GL_REG_16_ATI                                           0x8931
#define GL_REG_17_ATI                                           0x8932
#define GL_REG_18_ATI                                           0x8933
#define GL_REG_19_ATI                                           0x8934
#define GL_REG_20_ATI                                           0x8935
#define GL_REG_21_ATI                                           0x8936
#define GL_REG_22_ATI                                           0x8937
#define GL_REG_23_ATI                                           0x8938
#define GL_REG_24_ATI                                           0x8939
#define GL_REG_25_ATI                                           0x893A
#define GL_REG_26_ATI                                           0x893B
#define GL_REG_27_ATI                                           0x893C
#define GL_REG_28_ATI                                           0x893D
#define GL_REG_29_ATI                                           0x893E
#define GL_REG_30_ATI                                           0x893F
#define GL_REG_31_ATI                                           0x8940
#define GL_CON_0_ATI                                            0x8941
#define GL_CON_1_ATI                                            0x8942
#define GL_CON_2_ATI                                            0x8943
#define GL_CON_3_ATI                                            0x8944
#define GL_CON_4_ATI                                            0x8945
#define GL_CON_5_ATI                                            0x8946
#define GL_CON_6_ATI                                            0x8947
#define GL_CON_7_ATI                                            0x8948
#define GL_CON_8_ATI                                            0x8949
#define GL_CON_9_ATI                                            0x894A
#define GL_CON_10_ATI                                           0x894B
#define GL_CON_11_ATI                                           0x894C
#define GL_CON_12_ATI                                           0x894D
#define GL_CON_13_ATI                                           0x894E
#define GL_CON_14_ATI                                           0x894F
#define GL_CON_15_ATI                                           0x8950
#define GL_CON_16_ATI                                           0x8951
#define GL_CON_17_ATI                                           0x8952
#define GL_CON_18_ATI                                           0x8953
#define GL_CON_19_ATI                                           0x8954
#define GL_CON_20_ATI                                           0x8955
#define GL_CON_21_ATI                                           0x8956
#define GL_CON_22_ATI                                           0x8957
#define GL_CON_23_ATI                                           0x8958
#define GL_CON_24_ATI                                           0x8959
#define GL_CON_25_ATI                                           0x895A
#define GL_CON_26_ATI                                           0x895B
#define GL_CON_27_ATI                                           0x895C
#define GL_CON_28_ATI                                           0x895D
#define GL_CON_29_ATI                                           0x895E
#define GL_CON_30_ATI                                           0x895F
#define GL_CON_31_ATI                                           0x8960
#define GL_MOV_ATI                                              0x8961
#define GL_ADD_ATI                                              0x8963
#define GL_MUL_ATI                                              0x8964
#define GL_SUB_ATI                                              0x8965
#define GL_DOT3_ATI                                             0x8966
#define GL_DOT4_ATI                                             0x8967
#define GL_MAD_ATI                                              0x8968
#define GL_LERP_ATI                                             0x8969
#define GL_CND_ATI                                              0x896A
#define GL_CND0_ATI                                             0x896B
#define GL_DOT2_ADD_ATI                                         0x896C
#define GL_SECONDARY_INTERPOLATOR_ATI                           0x896D
#define GL_NUM_FRAGMENT_REGISTERS_ATI                           0x896E
#define GL_NUM_FRAGMENT_CONSTANTS_ATI                           0x896F
#define GL_NUM_PASSES_ATI                                       0x8970
#define GL_NUM_INSTRUCTIONS_PER_PASS_ATI                        0x8971
#define GL_NUM_INSTRUCTIONS_TOTAL_ATI                           0x8972
#define GL_NUM_INPUT_INTERPOLATOR_COMPONENTS_ATI                0x8973
#define GL_NUM_LOOPBACK_COMPONENTS_ATI                          0x8974
#define GL_COLOR_ALPHA_PAIRING_ATI                              0x8975
#define GL_SWIZZLE_STR_ATI                                      0x8976
#define GL_SWIZZLE_STQ_ATI                                      0x8977
#define GL_SWIZZLE_STR_DR_ATI                                   0x8978
#define GL_SWIZZLE_STQ_DQ_ATI                                   0x8979
#define GL_SWIZZLE_STRQ_ATI                                     0x897A
#define GL_SWIZZLE_STRQ_DQ_ATI                                  0x897B
#define GL_RED_BIT_ATI                                          0x00000001
#define GL_GREEN_BIT_ATI                                        0x00000002
#define GL_BLUE_BIT_ATI                                         0x00000004
#define GL_2X_BIT_ATI                                           0x00000001
#define GL_4X_BIT_ATI                                           0x00000002
#define GL_8X_BIT_ATI                                           0x00000004
#define GL_HALF_BIT_ATI                                         0x00000008
#define GL_QUARTER_BIT_ATI                                      0x00000010
#define GL_EIGHTH_BIT_ATI                                       0x00000020
#define GL_SATURATE_BIT_ATI                                     0x00000040
#define GL_COMP_BIT_ATI                                         0x00000002
#define GL_NEGATE_BIT_ATI                                       0x00000004
#define GL_BIAS_BIT_ATI                                         0x00000008

typedef GLuint (APIENTRY * glGenFragmentShadersATIPROC) (GLuint range);
typedef void (APIENTRY * glBindFragmentShaderATIPROC) (GLuint id);
typedef void (APIENTRY * glDeleteFragmentShaderATIPROC) (GLuint id);
typedef void (APIENTRY * glBeginFragmentShaderATIPROC) (GLvoid);
typedef void (APIENTRY * glEndFragmentShaderATIPROC) (GLvoid);
typedef void (APIENTRY * glPassTexCoordATIPROC) (GLuint dst, GLuint coord, GLenum swizzle);
typedef void (APIENTRY * glSampleMapATIPROC) (GLuint dst, GLuint interp, GLenum swizzle);
typedef void (APIENTRY * glColorFragmentOp1ATIPROC) (GLenum op, GLuint dst, GLuint dstMask, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod);
typedef void (APIENTRY * glColorFragmentOp2ATIPROC) (GLenum op, GLuint dst, GLuint dstMask, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod, GLuint arg2, GLuint arg2Rep, GLuint arg2Mod);
typedef void (APIENTRY * glColorFragmentOp3ATIPROC) (GLenum op, GLuint dst, GLuint dstMask, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod, GLuint arg2, GLuint arg2Rep, GLuint arg2Mod, GLuint arg3, GLuint arg3Rep, GLuint arg3Mod);
typedef void (APIENTRY * glAlphaFragmentOp1ATIPROC) (GLenum op, GLuint dst, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod);
typedef void (APIENTRY * glAlphaFragmentOp2ATIPROC) (GLenum op, GLuint dst, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod, GLuint arg2, GLuint arg2Rep, GLuint arg2Mod);
typedef void (APIENTRY * glAlphaFragmentOp3ATIPROC) (GLenum op, GLuint dst, GLuint dstMod, GLuint arg1, GLuint arg1Rep, GLuint arg1Mod, GLuint arg2, GLuint arg2Rep, GLuint arg2Mod, GLuint arg3, GLuint arg3Rep, GLuint arg3Mod);
typedef void (APIENTRY * glSetFragmentShaderConstantATIPROC) (GLuint dst, const GLfloat *value);

extern glGenFragmentShadersATIPROC glGenFragmentShadersATI;
extern glBindFragmentShaderATIPROC glBindFragmentShaderATI;
extern glDeleteFragmentShaderATIPROC glDeleteFragmentShaderATI;
extern glBeginFragmentShaderATIPROC glBeginFragmentShaderATI;
extern glEndFragmentShaderATIPROC glEndFragmentShaderATI;
extern glPassTexCoordATIPROC glPassTexCoordATI;
extern glSampleMapATIPROC glSampleMapATI;
extern glColorFragmentOp1ATIPROC glColorFragmentOp1ATI;
extern glColorFragmentOp2ATIPROC glColorFragmentOp2ATI;
extern glColorFragmentOp3ATIPROC glColorFragmentOp3ATI;
extern glAlphaFragmentOp1ATIPROC glAlphaFragmentOp1ATI;
extern glAlphaFragmentOp2ATIPROC glAlphaFragmentOp2ATI;
extern glAlphaFragmentOp3ATIPROC glAlphaFragmentOp3ATI;
extern glSetFragmentShaderConstantATIPROC glSetFragmentShaderConstantATI;

#endif /* GL_ATI_fragment_shader */

/*-------------------------------------------------------------------*/
/*------------ATI_TEXTURE_MIRROR_ONCE--------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ATI_texture_mirror_once
#define GL_ATI_texture_mirror_once 1

#define GL_MIRROR_CLAMP_ATI                                     0x8742
#define GL_MIRROR_CLAMP_TO_EDGE_ATI                             0x8743

#endif

/*-------------------------------------------------------------------*/
/*------------ATI_ELEMENT_ARRAY--------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ATI_element_array
#define GL_ATI_element_array 1

#define GL_ELEMENT_ARRAY_ATI                                    0x8768
#define GL_ELEMENT_ARRAY_TYPE_ATI                               0x8769
#define GL_ELEMENT_ARRAY_POINTER_ATI                            0x876A

typedef void (APIENTRY * glElementPointerATIPROC) (GLenum type, const GLvoid *pointer);
typedef void (APIENTRY * glDrawElementArrayATIPROC) (GLenum mode, GLsizei count);
typedef void (APIENTRY * glDrawRangeElementArrayATIPROC) (GLenum mode, GLuint start, GLuint end, GLsizei count);

extern glElementPointerATIPROC glElementPointerATI;
extern glDrawElementArrayATIPROC glDrawElementArrayATI;
extern glDrawRangeElementArrayATIPROC glDrawRangeElementArrayATI;

#endif /* GL_ATI_element_array */

/*-------------------------------------------------------------------*/
/*------------ATI_VERTEX_STREAMS-------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ATI_vertex_streams 
#define GL_ATI_vertex_streams 1

#define GL_MAX_VERTEX_STREAMS_ATI                               0x876B  
#define GL_VERTEX_SOURCE_ATI                                    0x876C
#define GL_VERTEX_STREAM0_ATI                                   0x876D
#define GL_VERTEX_STREAM1_ATI                                   0x876E
#define GL_VERTEX_STREAM2_ATI                                   0x876F
#define GL_VERTEX_STREAM3_ATI                                   0x8770
#define GL_VERTEX_STREAM4_ATI                                   0x8771
#define GL_VERTEX_STREAM5_ATI                                   0x8772
#define GL_VERTEX_STREAM6_ATI                                   0x8773
#define GL_VERTEX_STREAM7_ATI                                   0x8774

typedef void (APIENTRY * glClientActiveVertexStreamATIPROC) (GLenum stream);
typedef void (APIENTRY * glVertexBlendEnviATIPROC) (GLenum pname, GLint param);
typedef void (APIENTRY * glVertexBlendEnvfATIPROC) (GLenum pname, GLfloat param);
typedef void (APIENTRY * glVertexStream2sATIPROC) (GLenum stream, GLshort x, GLshort y);
typedef void (APIENTRY * glVertexStream2svATIPROC) (GLenum stream, const GLshort *v);
typedef void (APIENTRY * glVertexStream2iATIPROC) (GLenum stream, GLint x, GLint y);
typedef void (APIENTRY * glVertexStream2ivATIPROC) (GLenum stream, const GLint *v);
typedef void (APIENTRY * glVertexStream2fATIPROC) (GLenum stream, GLfloat x, GLfloat y);
typedef void (APIENTRY * glVertexStream2fvATIPROC) (GLenum stream, const GLfloat *v);
typedef void (APIENTRY * glVertexStream2dATIPROC) (GLenum stream, GLdouble x, GLdouble y);
typedef void (APIENTRY * glVertexStream2dvATIPROC) (GLenum stream, const GLdouble *v);
typedef void (APIENTRY * glVertexStream3sATIPROC) (GLenum stream, GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY * glVertexStream3svATIPROC) (GLenum stream, const GLshort *v);
typedef void (APIENTRY * glVertexStream3iATIPROC) (GLenum stream, GLint x, GLint y, GLint z);
typedef void (APIENTRY * glVertexStream3ivATIPROC) (GLenum stream, const GLint *v);
typedef void (APIENTRY * glVertexStream3fATIPROC) (GLenum stream, GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glVertexStream3fvATIPROC) (GLenum stream, const GLfloat *v);
typedef void (APIENTRY * glVertexStream3dATIPROC) (GLenum stream, GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glVertexStream3dvATIPROC) (GLenum stream, const GLdouble *v);
typedef void (APIENTRY * glVertexStream4sATIPROC) (GLenum stream, GLshort x, GLshort y, GLshort z, GLshort w);
typedef void (APIENTRY * glVertexStream4svATIPROC) (GLenum stream, const GLshort *v);
typedef void (APIENTRY * glVertexStream4iATIPROC) (GLenum stream, GLint x, GLint y, GLint z, GLint w);
typedef void (APIENTRY * glVertexStream4ivATIPROC) (GLenum stream, const GLint *v);
typedef void (APIENTRY * glVertexStream4fATIPROC) (GLenum stream, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glVertexStream4fvATIPROC) (GLenum stream, const GLfloat *v);
typedef void (APIENTRY * glVertexStream4dATIPROC) (GLenum stream, GLdouble x, GLdouble y, GLdouble z, GLdouble w);
typedef void (APIENTRY * glVertexStream4dvATIPROC) (GLenum stream, const GLdouble *v);
typedef void (APIENTRY * glNormalStream3bATIPROC) (GLenum stream, GLbyte x, GLbyte y, GLbyte z);
typedef void (APIENTRY * glNormalStream3bvATIPROC) (GLenum stream, const GLbyte *v);
typedef void (APIENTRY * glNormalStream3sATIPROC) (GLenum stream, GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY * glNormalStream3svATIPROC) (GLenum stream, const GLshort *v);
typedef void (APIENTRY * glNormalStream3iATIPROC) (GLenum stream, GLint x, GLint y, GLint z);
typedef void (APIENTRY * glNormalStream3ivATIPROC) (GLenum stream, const GLint *v);
typedef void (APIENTRY * glNormalStream3fATIPROC) (GLenum stream, GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glNormalStream3fvATIPROC) (GLenum stream, const GLfloat *v);
typedef void (APIENTRY * glNormalStream3dATIPROC) (GLenum stream, GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glNormalStream3dvATIPROC) (GLenum stream, const GLdouble *v);

extern glClientActiveVertexStreamATIPROC glClientActiveVertexStreamATI;
extern glVertexBlendEnviATIPROC glVertexBlendEnviATI;
extern glVertexBlendEnvfATIPROC glVertexBlendEnvfATI;
extern glVertexStream2sATIPROC glVertexStream2sATI;
extern glVertexStream2svATIPROC glVertexStream2svATI;
extern glVertexStream2iATIPROC glVertexStream2iATI;
extern glVertexStream2ivATIPROC glVertexStream2ivATI;
extern glVertexStream2fATIPROC glVertexStream2fATI;
extern glVertexStream2fvATIPROC glVertexStream2fvATI;
extern glVertexStream2dATIPROC glVertexStream2dATI;
extern glVertexStream2dvATIPROC glVertexStream2dvATI;
extern glVertexStream3sATIPROC glVertexStream3sATI;
extern glVertexStream3svATIPROC glVertexStream3svATI;
extern glVertexStream3iATIPROC glVertexStream3iATI;
extern glVertexStream3ivATIPROC glVertexStream3ivATI;
extern glVertexStream3fATIPROC glVertexStream3fATI;
extern glVertexStream3fvATIPROC glVertexStream3fvATI;
extern glVertexStream3dATIPROC glVertexStream3dATI;
extern glVertexStream3dvATIPROC glVertexStream3dvATI;
extern glVertexStream4sATIPROC glVertexStream4sATI;
extern glVertexStream4svATIPROC glVertexStream4svATI;
extern glVertexStream4iATIPROC glVertexStream4iATI;
extern glVertexStream4ivATIPROC glVertexStream4ivATI;
extern glVertexStream4fATIPROC glVertexStream4fATI;
extern glVertexStream4fvATIPROC glVertexStream4fvATI;
extern glVertexStream4dATIPROC glVertexStream4dATI;
extern glVertexStream4dvATIPROC glVertexStream4dvATI;
extern glNormalStream3bATIPROC glNormalStream3bATI;
extern glNormalStream3bvATIPROC glNormalStream3bvATI;
extern glNormalStream3sATIPROC glNormalStream3sATI;
extern glNormalStream3svATIPROC glNormalStream3svATI;
extern glNormalStream3iATIPROC glNormalStream3iATI;
extern glNormalStream3ivATIPROC glNormalStream3ivATI;
extern glNormalStream3fATIPROC glNormalStream3fATI;
extern glNormalStream3fvATIPROC glNormalStream3fvATI;
extern glNormalStream3dATIPROC glNormalStream3dATI;
extern glNormalStream3dvATIPROC glNormalStream3dvATI;

#endif /* GL_ATI_vertex_streams */

/*-------------------------------------------------------------------*/
/*------------ATI_VERTEX_ARRAY_OBJECT--------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ATI_vertex_array_object
#define GL_ATI_vertex_array_object 1

#define GL_STATIC_ATI                                           0x8760
#define GL_DYNAMIC_ATI                                          0x8761
#define GL_PRESERVE_ATI                                         0x8762
#define GL_DISCARD_ATI                                          0x8763
#define GL_OBJECT_BUFFER_SIZE_ATI                               0x8764
#define GL_OBJECT_BUFFER_USAGE_ATI                              0x8765
#define GL_ARRAY_OBJECT_BUFFER_ATI                              0x8766
#define GL_ARRAY_OBJECT_OFFSET_ATI                              0x8767

typedef GLuint (APIENTRY * glNewObjectBufferATIPROC) (GLsizei size, const GLvoid *pointer, GLenum usage);
typedef GLboolean (APIENTRY * glIsObjectBufferATIPROC) (GLuint buffer);
typedef void (APIENTRY * glUpdateObjectBufferATIPROC) (GLuint buffer, GLuint offset, GLsizei size, const GLvoid *pointer, GLenum preserve);
typedef void (APIENTRY * glGetObjectBufferfvATIPROC) (GLuint buffer, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetObjectBufferivATIPROC) (GLuint buffer, GLenum pname, GLint *params);
typedef void (APIENTRY * glFreeObjectBufferATIPROC) (GLuint buffer);
typedef void (APIENTRY * glArrayObjectATIPROC) (GLenum array, GLint size, GLenum type, GLsizei stride, GLuint buffer, GLuint offset);
typedef void (APIENTRY * glGetArrayObjectfvATIPROC) (GLenum array, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetArrayObjectivATIPROC) (GLenum array, GLenum pname, GLint *params);
typedef void (APIENTRY * glVariantArrayObjectATIPROC) (GLuint id, GLenum type, GLsizei stride, GLuint buffer, GLuint offset);
typedef void (APIENTRY * glGetVariantArrayObjectfvATIPROC) (GLuint id, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetVariantArrayObjectivATIPROC) (GLuint id, GLenum pname, GLint *params);

extern glNewObjectBufferATIPROC glNewObjectBufferATI;
extern glIsObjectBufferATIPROC glIsObjectBufferATI;
extern glUpdateObjectBufferATIPROC glUpdateObjectBufferATI;
extern glGetObjectBufferfvATIPROC glGetObjectBufferfvATI;
extern glGetObjectBufferivATIPROC glGetObjectBufferivATI;
extern glFreeObjectBufferATIPROC glFreeObjectBufferATI;
extern glArrayObjectATIPROC glArrayObjectATI;
extern glGetArrayObjectfvATIPROC glGetArrayObjectfvATI;
extern glGetArrayObjectivATIPROC glGetArrayObjectivATI;
extern glVariantArrayObjectATIPROC glVariantArrayObjectATI;
extern glGetVariantArrayObjectfvATIPROC glGetVariantArrayObjectfvATI;
extern glGetVariantArrayObjectivATIPROC glGetVariantArrayObjectivATI;

#endif /* GL_ATI_vertex_array_object */

/*-------------------------------------------------------------------*/
/*------------HP_OCCLUSION_TEST--------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_HP_occlusion_test
#define GL_HP_occlusion_test 1

#define GL_OCCLUSION_TEST_HP                                    0x8165;
#define GL_OCCLUSION_TEST_RESULT_HP                             0x8166;

#endif /* GL_HP_occlusion_test */

/*-------------------------------------------------------------------*/
/*------------ATIX_POINT_SPRITES-------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ATIX_point_sprites
#define GL_ATIX_point_sprites 1

#define GL_TEXTURE_POINT_MODE_ATIX                              0x60b0
#define	GL_TEXTURE_POINT_ONE_COORD_ATIX                         0x60b1
#define	GL_TEXTURE_POINT_SPRITE_ATIX                            0x60b2
#define GL_POINT_SPRITE_CULL_MODE_ATIX                          0x60b3
#define GL_POINT_SPRITE_CULL_CENTER_ATIX                        0x60b4
#define GL_POINT_SPRITE_CULL_CLIP_ATIX                          0x60b5

#endif /* GL_ATIX_point_sprites */

/*-------------------------------------------------------------------*/
/*------------ATIX_TEXTURE_ENV_ROUTE---------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ATIX_texture_env_route
#define GL_ATIX_texture_env_route 1

#define GL_SECONDARY_COLOR_ATIX                                 0x8747
#define GL_TEXTURE_OUTPUT_RGB_ATIX                              0x8748
#define GL_TEXTURE_OUTPUT_ALPHA_ATIX                            0x8749

#endif /* GL_ATIX_texture_env_route */

/*-------------------------------------------------------------------*/
/*------------NV_DEPTH_CLAMP-----------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_depth_clamp
#define GL_NV_depth_clamp 1

#define GL_DEPTH_CLAMP_NV                                       0x864F

#endif /* GL_NV_depth_clamp */

/*-------------------------------------------------------------------*/
/*------------NV_OCCLUSION_QUERY-------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_occlusion_query
#define GL_NV_occlusion_query 1

#ifndef GL_HP_occlusion_test
#define GL_OCCLUSION_TEST_HP                                    0x8165
#define GL_OCCLUSION_TEST_RESULT_HP                             0x8166
#endif /* GL_HP_occlusion_test */
#define GL_PIXEL_COUNTER_BITS_NV                                0x8864
#define GL_CURRENT_OCCLUSION_QUERY_ID_NV                        0x8865
#define GL_PIXEL_COUNT_NV                                       0x8866
#define GL_PIXEL_COUNT_AVAILABLE_NV                             0x8867

typedef void (APIENTRY * glGenOcclusionQueriesNVPROC) (GLsizei n, GLuint *ids);
typedef void (APIENTRY * glDeleteOcclusionQueriesNVPROC) (GLsizei n, const GLuint *ids);
typedef GLboolean (APIENTRY * glIsOcclusionQueryNVPROC) (GLuint id);
typedef void (APIENTRY * glBeginOcclusionQueryNVPROC) (GLuint id);
typedef void (APIENTRY * glEndOcclusionQueryNVPROC) (void);
typedef void (APIENTRY * glGetOcclusionQueryivNVPROC) (GLuint id, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetOcclusionQueryuivNVPROC) (GLuint id, GLenum pname, GLuint *params);

extern glGenOcclusionQueriesNVPROC glGenOcclusionQueriesNV;
extern glDeleteOcclusionQueriesNVPROC glDeleteOcclusionQueriesNV;
extern glIsOcclusionQueryNVPROC glIsOcclusionQueryNV;
extern glBeginOcclusionQueryNVPROC glBeginOcclusionQueryNV;
extern glEndOcclusionQueryNVPROC glEndOcclusionQueryNV;
extern glGetOcclusionQueryivNVPROC glGetOcclusionQueryivNV;
extern glGetOcclusionQueryuivNVPROC glGetOcclusionQueryuivNV;

#endif /* GL_NV_occlusion_query */

/*-------------------------------------------------------------------*/
/*------------NV_POINT_SPRITE----------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_point_sprite
#define GL_NV_point_sprite 1

#define GL_POINT_SPRITE_NV                                      0x8861
#define GL_COORD_REPLACE_NV                                     0x8862
#define GL_POINT_SPRITE_R_MODE_NV                               0x8863

typedef void (APIENTRY * glPointParameteriNVPROC) (GLenum pname, GLint param);
typedef void (APIENTRY * glPointParameterivNVPROC) (GLenum pname, const GLint *params);

extern glPointParameteriNVPROC glPointParameteriNV;
extern glPointParameterivNVPROC glPointParameterivNV;

#endif /* GL_NV_point_sprite */

/*-------------------------------------------------------------------*/
/*------------NV_TEXTURE_SHADER3-------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_texture_shader3
#define GL_NV_texture_shader3 1

#define GL_OFFSET_PROJECTIVE_TEXTURE_2D_NV                      0x8850
#define GL_OFFSET_PROJECTIVE_TEXTURE_2D_SCALE_NV                0x8851
#define GL_OFFSET_PROJECTIVE_TEXTURE_RECTANGLE_NV               0x8852
#define GL_OFFSET_PROJECTIVE_TEXTURE_RECTANGLE_SCALE_NV         0x8853
#define GL_OFFSET_HILO_TEXTURE_2D_NV                            0x8854
#define GL_OFFSET_HILO_TEXTURE_RECTANGLE_NV                     0x8855
#define GL_OFFSET_HILO_PROJECTIVE_TEXTURE_2D_NV                 0x8856
#define GL_OFFSET_HILO_PROJECTIVE_TEXTURE_RECTANGLE_NV          0x8857
#define GL_DEPENDENT_HILO_TEXTURE_2D_NV                         0x8858
#define GL_DEPENDENT_RGB_TEXTURE_3D_NV                          0x8859
#define GL_DEPENDENT_RGB_TEXTURE_CUBE_MAP_NV                    0x885A
#define GL_DOT_PRODUCT_PASS_THROUGH_NV                          0x885B
#define GL_DOT_PRODUCT_TEXTURE_1D_NV                            0x885C
#define GL_DOT_PRODUCT_AFFINE_DEPTH_REPLACE_NV                  0x885D
#define GL_HILO8_NV                                             0x885E
#define GL_SIGNED_HILO8_NV                                      0x885F
#define GL_FORCE_BLUE_TO_ONE_NV                                 0x8860

#endif /* GL_NV_texture_shader3 */

/*-------------------------------------------------------------------*/
/*------------NV_VERTEX_PROGRAM1_1-----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_NV_vertex_program1_1
#define GL_NV_vertex_program1_1

#endif /* GL_NV_vertex_program1_1 */

/*-------------------------------------------------------------------*/
/*------------ARB_TEXTURE_MIRRORED_REPEAT----------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_texture_mirrored_repeat
#define GL_ARB_texture_mirrored_repeat 1

#define GL_GL_MIRRORED_REPEAT_ARB                               0x8370

#endif /* GL_ARB_texture_mirrored_repeat */

/*-------------------------------------------------------------------*/
/*------------ARB_SHADOW---------------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_shadow
#define GL_ARB_shadow 1

#define GL_TEXTURE_COMPARE_MODE_ARB                             0x884C
#define GL_TEXTURE_COMPARE_FUNC_ARB                             0x884D
#define GL_COMPARE_R_TO_TEXTURE_ARB                             0x884E

#endif /* GL_ARB_shadow */

/*-------------------------------------------------------------------*/
/*------------ARB_SHADOW_AMBIENT-------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_shadow_ambient
#define GL_ARB_shadow_ambient 1

#define GL_TEXTURE_COMPARE_FAIL_VALUE_ARB                       0x80BF 

#endif /* GL_ARB_shadow_ambient */

/*-------------------------------------------------------------------*/
/*------------ARB_DEPTH_TEXTURE--------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_depth_texture
#define GL_ARB_depth_texture 1

#define GL_DEPTH_COMPONENT16_ARB                                0x81A5
#define GL_DEPTH_COMPONENT24_ARB                                0x81A6
#define GL_DEPTH_COMPONENT32_ARB                                0x81A7
#define GL_TEXTURE_DEPTH_SIZE_ARB                               0x884A
#define GL_DEPTH_TEXTURE_MODE_ARB                               0x884B

#endif /* GL_ARB_depth_texture */

/*-------------------------------------------------------------------*/
/*------------ARB_WINDOW_POS-----------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_window_pos
#define GL_ARB_window_pos 1

typedef void (APIENTRY * glWindowPos2dARBPROC) (GLdouble x, GLdouble y);
typedef void (APIENTRY * glWindowPos2fARBPROC) (GLfloat x, GLfloat y);
typedef void (APIENTRY * glWindowPos2iARBPROC) (GLint x, GLint y);
typedef void (APIENTRY * glWindowPos2sARBPROC) (GLshort x, GLshort y);
typedef void (APIENTRY * glWindowPos2dvARBPROC) (const GLdouble *p);
typedef void (APIENTRY * glWindowPos2fvARBPROC) (const GLfloat *p);
typedef void (APIENTRY * glWindowPos2ivARBPROC) (const GLint *p);
typedef void (APIENTRY * glWindowPos2svARBPROC) (const GLshort *p);
typedef void (APIENTRY * glWindowPos3dARBPROC) (GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glWindowPos3fARBPROC) (GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glWindowPos3iARBPROC) (GLint x, GLint y, GLint z);
typedef void (APIENTRY * glWindowPos3sARBPROC) (GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY * glWindowPos3dvARBPROC) (const GLdouble *p);
typedef void (APIENTRY * glWindowPos3fvARBPROC) (const GLfloat *p);
typedef void (APIENTRY * glWindowPos3ivARBPROC) (const GLint *p);
typedef void (APIENTRY * glWindowPos3svARBPROC) (const GLshort *p);

extern glWindowPos2dARBPROC glWindowPos2dARB;
extern glWindowPos2fARBPROC glWindowPos2fARB;
extern glWindowPos2iARBPROC glWindowPos2iARB;
extern glWindowPos2sARBPROC glWindowPos2sARB;
extern glWindowPos2dvARBPROC glWindowPos2dvARB;
extern glWindowPos2fvARBPROC glWindowPos2fvARB;
extern glWindowPos2ivARBPROC glWindowPos2ivARB;
extern glWindowPos2svARBPROC glWindowPos2svARB;
extern glWindowPos3dARBPROC glWindowPos3dARB;
extern glWindowPos3fARBPROC glWindowPos3fARB;
extern glWindowPos3iARBPROC glWindowPos3iARB;
extern glWindowPos3sARBPROC glWindowPos3sARB;
extern glWindowPos3dvARBPROC glWindowPos3dvARB;
extern glWindowPos3fvARBPROC glWindowPos3fvARB;
extern glWindowPos3ivARBPROC glWindowPos3ivARB;
extern glWindowPos3svARBPROC glWindowPos3svARB;

#endif /* GL_ARB_window_pos */

/*-------------------------------------------------------------------*/
/*------------EXT_SHADOW_FUNCS---------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_EXT_shadow_funcs
#define GL_EXT_shadow_funcs 1

#endif /* GL_EXT_shadow_funcs */


/*-------------------------------------------------------------------*/
/*------------EXT_draw_range_elements--------------------------------*/
/*-------------------------------------------------------------------*/


#ifndef GL_EXT_draw_range_elements
#define GL_EXT_draw_range_elements 1

typedef void (APIENTRY * glDrawRangeElementsEXTPROC) ( GLenum mode, GLuint start, GLuint end, GLsizei count, GLenum type, const GLvoid *indices);

extern glDrawRangeElementsEXTPROC glDrawRangeElementsEXT;

#define GL_MAX_ELEMENTS_VERTICES_EXT                            0x80E8
#define GL_MAX_ELEMENTS_INDICES_EXT                             0x80E9

#endif

/*-------------------------------------------------------------------*/
/*------------EXT_texture_compression_s3tc---------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_EXT_texture_compression_s3tc
#define GL_EXT_texture_compression_s3tc 1

#define GL_COMPRESSED_RGB_S3TC_DXT1_EXT                         0x83F0
#define GL_COMPRESSED_RGBA_S3TC_DXT1_EXT                        0x83F1
#define GL_COMPRESSED_RGBA_S3TC_DXT3_EXT                        0x83F2
#define GL_COMPRESSED_RGBA_S3TC_DXT5_EXT                        0x83F3

#endif /* GL_EXT_texture_compression_s3tc */

/*-------------------------------------------------------------------*/
/*------------EXT_stencil_two_side-----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_EXT_stencil_two_side
#define GL_EXT_stencil_two_side 1

typedef void (APIENTRY * glActiveStencilFaceEXTPROC) (GLenum face);

extern glActiveStencilFaceEXTPROC glActiveStencilFaceEXT;

#define GL_STENCIL_TEST_TWO_SIDE_EXT                            0x8910
#define GL_ACTIVE_STENCIL_FACE_EXT                              0x8911

#endif /* GL_EXT_stencil_two_side */

/*-------------------------------------------------------------------*/
/*------------ARB_vertex_program-------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef GL_ARB_vertex_program
#define GL_ARB_vertex_program 1

typedef void (APIENTRY * glVertexAttrib1sARBPROC) (GLuint index, GLshort x);
typedef void (APIENTRY * glVertexAttrib1fARBPROC) (GLuint index, GLfloat x);
typedef void (APIENTRY * glVertexAttrib1dARBPROC) (GLuint index, GLdouble x);
typedef void (APIENTRY * glVertexAttrib2sARBPROC) (GLuint index, GLshort x, GLshort y);
typedef void (APIENTRY * glVertexAttrib2fARBPROC) (GLuint index, GLfloat x, GLfloat y);
typedef void (APIENTRY * glVertexAttrib2dARBPROC) (GLuint index, GLdouble x, GLdouble y);
typedef void (APIENTRY * glVertexAttrib3sARBPROC) (GLuint index, GLshort x, GLshort y, GLshort z);
typedef void (APIENTRY * glVertexAttrib3fARBPROC) (GLuint index, GLfloat x, GLfloat y, GLfloat z);
typedef void (APIENTRY * glVertexAttrib3dARBPROC) (GLuint index, GLdouble x, GLdouble y, GLdouble z);
typedef void (APIENTRY * glVertexAttrib4sARBPROC) (GLuint index, GLshort x, GLshort y, GLshort z, GLshort w);
typedef void (APIENTRY * glVertexAttrib4fARBPROC) (GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glVertexAttrib4dARBPROC) (GLuint index, GLdouble x, GLdouble y, GLdouble z, GLdouble w);
typedef void (APIENTRY * glVertexAttrib4NubARBPROC) (GLuint index, GLubyte x, GLubyte y, GLubyte z, GLubyte w);
typedef void (APIENTRY * glVertexAttrib1svARBPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib1fvARBPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib1dvARBPROC) (GLuint index, const GLdouble *v);
typedef void (APIENTRY * glVertexAttrib2svARBPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib2fvARBPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib2dvARBPROC) (GLuint index, const GLdouble *v);
typedef void (APIENTRY * glVertexAttrib3svARBPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib3fvARBPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib3dvARBPROC) (GLuint index, const GLdouble *v);
typedef void (APIENTRY * glVertexAttrib4bvARBPROC) (GLuint index, const GLbyte *v);
typedef void (APIENTRY * glVertexAttrib4svARBPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib4ivARBPROC) (GLuint index, const GLint *v);
typedef void (APIENTRY * glVertexAttrib4ubvARBPROC) (GLuint index, const GLubyte *v);
typedef void (APIENTRY * glVertexAttrib4usvARBPROC) (GLuint index, const GLushort *v);
typedef void (APIENTRY * glVertexAttrib4uivARBPROC) (GLuint index, const GLuint *v);
typedef void (APIENTRY * glVertexAttrib4fvARBPROC) (GLuint index, const GLfloat *v);
typedef void (APIENTRY * glVertexAttrib4dvARBPROC) (GLuint index, const GLdouble *v);
typedef void (APIENTRY * glVertexAttrib4NbvARBPROC) (GLuint index, const GLbyte *v);
typedef void (APIENTRY * glVertexAttrib4NsvARBPROC) (GLuint index, const GLshort *v);
typedef void (APIENTRY * glVertexAttrib4NivARBPROC) (GLuint index, const GLint *v);
typedef void (APIENTRY * glVertexAttrib4NubvARBPROC) (GLuint index, const GLubyte *v);
typedef void (APIENTRY * glVertexAttrib4NusvARBPROC) (GLuint index, const GLushort *v);
typedef void (APIENTRY * glVertexAttrib4NuivARBPROC) (GLuint index, const GLuint *v);
typedef void (APIENTRY * glVertexAttribPointerARBPROC) (GLuint index, GLint size, GLenum type, GLboolean normalized, GLsizei stride, const GLvoid *pointer);
typedef void (APIENTRY * glEnableVertexAttribArrayARBPROC) (GLuint index);
typedef void (APIENTRY * glDisableVertexAttribArrayARBPROC) (GLuint index);
typedef void (APIENTRY * glProgramStringARBPROC) (GLenum target, GLenum format, GLsizei len, const GLvoid *string); 
typedef void (APIENTRY * glBindProgramARBPROC) (GLenum target, GLuint program);
typedef void (APIENTRY * glDeleteProgramsARBPROC) (GLsizei n, const GLuint *programs);
typedef void (APIENTRY * glGenProgramsARBPROC) (GLsizei n, GLuint *programs);
typedef void (APIENTRY * glProgramEnvParameter4dARBPROC) (GLenum target, GLuint index, GLdouble x, GLdouble y, GLdouble z, GLdouble w);
typedef void (APIENTRY * glProgramEnvParameter4dvARBPROC) (GLenum target, GLuint index, const GLdouble *params);
typedef void (APIENTRY * glProgramEnvParameter4fARBPROC) (GLenum target, GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glProgramEnvParameter4fvARBPROC) (GLenum target, GLuint index, const GLfloat *params);
typedef void (APIENTRY * glProgramLocalParameter4dARBPROC) (GLenum target, GLuint index, GLdouble x, GLdouble y, GLdouble z, GLdouble w);
typedef void (APIENTRY * glProgramLocalParameter4dvARBPROC) (GLenum target, GLuint index, const GLdouble *params);
typedef void (APIENTRY * glProgramLocalParameter4fARBPROC) (GLenum target, GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w);
typedef void (APIENTRY * glProgramLocalParameter4fvARBPROC) (GLenum target, GLuint index, const GLfloat *params);
typedef void (APIENTRY * glGetProgramEnvParameterdvARBPROC) (GLenum target, GLuint index, GLdouble *params);
typedef void (APIENTRY * glGetProgramEnvParameterfvARBPROC) (GLenum target, GLuint index, GLfloat *params);
typedef void (APIENTRY * glGetProgramLocalParameterdvARBPROC) (GLenum target, GLuint index, GLdouble *params);
typedef void (APIENTRY * glGetProgramLocalParameterfvARBPROC) (GLenum target, GLuint index, GLfloat *params);
typedef void (APIENTRY * glGetProgramivARBPROC) (GLenum target, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetProgramStringARBPROC) (GLenum target, GLenum pname, GLvoid *string);
typedef void (APIENTRY * glGetVertexAttribdvARBPROC) (GLuint index, GLenum pname, GLdouble *params);
typedef void (APIENTRY * glGetVertexAttribfvARBPROC) (GLuint index, GLenum pname, GLfloat *params);
typedef void (APIENTRY * glGetVertexAttribivARBPROC) (GLuint index, GLenum pname, GLint *params);
typedef void (APIENTRY * glGetVertexAttribPointervARBPROC) (GLuint index, GLenum pname, GLvoid **pointer);
typedef GLboolean (APIENTRY * glIsProgramARBPROC) (GLuint program);

extern glVertexAttrib1sARBPROC glVertexAttrib1sARB;
extern glVertexAttrib1fARBPROC glVertexAttrib1fARB;
extern glVertexAttrib1dARBPROC glVertexAttrib1dARB;
extern glVertexAttrib2sARBPROC glVertexAttrib2sARB;
extern glVertexAttrib2fARBPROC glVertexAttrib2fARB;
extern glVertexAttrib2dARBPROC glVertexAttrib2dARB;
extern glVertexAttrib3sARBPROC glVertexAttrib3sARB;
extern glVertexAttrib3fARBPROC glVertexAttrib3fARB;
extern glVertexAttrib3dARBPROC glVertexAttrib3dARB;
extern glVertexAttrib4sARBPROC glVertexAttrib4sARB;
extern glVertexAttrib4fARBPROC glVertexAttrib4fARB;
extern glVertexAttrib4dARBPROC glVertexAttrib4dARB;
extern glVertexAttrib4NubARBPROC glVertexAttrib4NubARB;
extern glVertexAttrib1svARBPROC glVertexAttrib1svARB;
extern glVertexAttrib1fvARBPROC glVertexAttrib1fvARB;
extern glVertexAttrib1dvARBPROC glVertexAttrib1dvARB;
extern glVertexAttrib2svARBPROC glVertexAttrib2svARB;
extern glVertexAttrib2fvARBPROC glVertexAttrib2fvARB;
extern glVertexAttrib2dvARBPROC glVertexAttrib2dvARB;
extern glVertexAttrib3svARBPROC glVertexAttrib3svARB;
extern glVertexAttrib3fvARBPROC glVertexAttrib3fvARB;
extern glVertexAttrib3dvARBPROC glVertexAttrib3dvARB;
extern glVertexAttrib4bvARBPROC glVertexAttrib4bvARB;
extern glVertexAttrib4svARBPROC glVertexAttrib4svARB;
extern glVertexAttrib4ivARBPROC glVertexAttrib4ivARB;
extern glVertexAttrib4ubvARBPROC glVertexAttrib4ubvARB;
extern glVertexAttrib4usvARBPROC glVertexAttrib4usvARB;
extern glVertexAttrib4uivARBPROC glVertexAttrib4uivARB;
extern glVertexAttrib4fvARBPROC glVertexAttrib4fvARB;
extern glVertexAttrib4dvARBPROC glVertexAttrib4dvARB;
extern glVertexAttrib4NbvARBPROC glVertexAttrib4NbvARB;
extern glVertexAttrib4NsvARBPROC glVertexAttrib4NsvARB;
extern glVertexAttrib4NivARBPROC glVertexAttrib4NivARB;
extern glVertexAttrib4NubvARBPROC glVertexAttrib4NubvARB;
extern glVertexAttrib4NusvARBPROC glVertexAttrib4NusvARB;
extern glVertexAttrib4NuivARBPROC glVertexAttrib4NuivARB;
extern glVertexAttribPointerARBPROC glVertexAttribPointerARB;
extern glEnableVertexAttribArrayARBPROC glEnableVertexAttribArrayARB;
extern glDisableVertexAttribArrayARBPROC glDisableVertexAttribArrayARB;
extern glProgramStringARBPROC glProgramStringARB;
extern glBindProgramARBPROC glBindProgramARB;
extern glDeleteProgramsARBPROC glDeleteProgramsARB;
extern glGenProgramsARBPROC glGenProgramsARB;
extern glProgramEnvParameter4dARBPROC glProgramEnvParameter4dARB;
extern glProgramEnvParameter4dvARBPROC glProgramEnvParameter4dvARB;
extern glProgramEnvParameter4fARBPROC glProgramEnvParameter4fARB;
extern glProgramEnvParameter4fvARBPROC glProgramEnvParameter4fvARB;
extern glProgramLocalParameter4dARBPROC glProgramLocalParameter4dARB;
extern glProgramLocalParameter4dvARBPROC glProgramLocalParameter4dvARB;
extern glProgramLocalParameter4fARBPROC glProgramLocalParameter4fARB;
extern glProgramLocalParameter4fvARBPROC glProgramLocalParameter4fvARB;
extern glGetProgramEnvParameterdvARBPROC glGetProgramEnvParameterdvARB;
extern glGetProgramEnvParameterfvARBPROC glGetProgramEnvParameterfvARB;
extern glGetProgramLocalParameterdvARBPROC glGetProgramLocalParameterdvARB;
extern glGetProgramLocalParameterfvARBPROC glGetProgramLocalParameterfvARB;
extern glGetProgramivARBPROC glGetProgramivARB;
extern glGetProgramStringARBPROC glGetProgramStringARB;
extern glGetVertexAttribdvARBPROC glGetVertexAttribdvARB;
extern glGetVertexAttribfvARBPROC glGetVertexAttribfvARB;
extern glGetVertexAttribivARBPROC glGetVertexAttribivARB;
extern glGetVertexAttribPointervARBPROC glGetVertexAttribPointervARB;
extern glIsProgramARBPROC glIsProgramARB;

#define GL_VERTEX_PROGRAM_ARB                                   0x8620
#define GL_VERTEX_PROGRAM_POINT_SIZE_ARB                        0x8642
#define GL_VERTEX_PROGRAM_TWO_SIDE_ARB                          0x8643
#define GL_COLOR_SUM_ARB                                        0x8458
#define GL_PROGRAM_FORMAT_ASCII_ARB                             0x8875
#define GL_VERTEX_ATTRIB_ARRAY_ENABLED_ARB                      0x8622
#define GL_VERTEX_ATTRIB_ARRAY_SIZE_ARB                         0x8623
#define GL_VERTEX_ATTRIB_ARRAY_STRIDE_ARB                       0x8624
#define GL_VERTEX_ATTRIB_ARRAY_TYPE_ARB                         0x8625
#define GL_VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB                   0x886A
#define GL_CURRENT_VERTEX_ATTRIB_ARB                            0x8626
#define GL_VERTEX_ATTRIB_ARRAY_POINTER_ARB                      0x8645
#define GL_PROGRAM_LENGTH_ARB                                   0x8627
#define GL_PROGRAM_FORMAT_ARB                                   0x8876
#define GL_PROGRAM_BINDING_ARB                                  0x8677
#define GL_PROGRAM_INSTRUCTIONS_ARB                             0x88A0
#define GL_MAX_PROGRAM_INSTRUCTIONS_ARB                         0x88A1
#define GL_PROGRAM_NATIVE_INSTRUCTIONS_ARB                      0x88A2
#define GL_MAX_PROGRAM_NATIVE_INSTRUCTIONS_ARB                  0x88A3
#define GL_PROGRAM_TEMPORARIES_ARB                              0x88A4
#define GL_MAX_PROGRAM_TEMPORARIES_ARB                          0x88A5
#define GL_PROGRAM_NATIVE_TEMPORARIES_ARB                       0x88A6
#define GL_MAX_PROGRAM_NATIVE_TEMPORARIES_ARB                   0x88A7
#define GL_PROGRAM_PARAMETERS_ARB                               0x88A8
#define GL_MAX_PROGRAM_PARAMETERS_ARB                           0x88A9
#define GL_PROGRAM_NATIVE_PARAMETERS_ARB                        0x88AA
#define GL_MAX_PROGRAM_NATIVE_PARAMETERS_ARB                    0x88AB
#define GL_PROGRAM_ATTRIBS_ARB                                  0x88AC
#define GL_MAX_PROGRAM_ATTRIBS_ARB                              0x88AD
#define GL_PROGRAM_NATIVE_ATTRIBS_ARB                           0x88AE
#define GL_MAX_PROGRAM_NATIVE_ATTRIBS_ARB                       0x88AF
#define GL_PROGRAM_ADDRESS_REGISTERS_ARB                        0x88B0
#define GL_MAX_PROGRAM_ADDRESS_REGISTERS_ARB                    0x88B1
#define GL_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB                 0x88B2
#define GL_MAX_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB             0x88B3
#define GL_MAX_PROGRAM_LOCAL_PARAMETERS_ARB                     0x88B4
#define GL_MAX_PROGRAM_ENV_PARAMETERS_ARB                       0x88B5
#define GL_PROGRAM_UNDER_NATIVE_LIMITS_ARB                      0x88B6
#define GL_PROGRAM_STRING_ARB                                   0x8628
#define GL_PROGRAM_ERROR_POSITION_ARB                           0x864B
#define GL_CURRENT_MATRIX_ARB                                   0x8641
#define GL_TRANSPOSE_CURRENT_MATRIX_ARB                         0x88B7
#define GL_CURRENT_MATRIX_STACK_DEPTH_ARB                       0x8640
#define GL_MAX_VERTEX_ATTRIBS_ARB                               0x8869
#define GL_MAX_PROGRAM_MATRICES_ARB                             0x862F
#define GL_MAX_PROGRAM_MATRIX_STACK_DEPTH_ARB                   0x862E
#define GL_PROGRAM_ERROR_STRING_ARB                             0x8874
#define GL_MATRIX0_ARB                                          0x88C0
#define GL_MATRIX1_ARB                                          0x88C1
#define GL_MATRIX2_ARB                                          0x88C2
#define GL_MATRIX3_ARB                                          0x88C3
#define GL_MATRIX4_ARB                                          0x88C4
#define GL_MATRIX5_ARB                                          0x88C5
#define GL_MATRIX6_ARB                                          0x88C6
#define GL_MATRIX7_ARB                                          0x88C7
#define GL_MATRIX8_ARB                                          0x88C8
#define GL_MATRIX9_ARB                                          0x88C9
#define GL_MATRIX10_ARB                                         0x88CA
#define GL_MATRIX11_ARB                                         0x88CB
#define GL_MATRIX12_ARB                                         0x88CC
#define GL_MATRIX13_ARB                                         0x88CD
#define GL_MATRIX14_ARB                                         0x88CE
#define GL_MATRIX15_ARB                                         0x88CF
#define GL_MATRIX16_ARB                                         0x88D0
#define GL_MATRIX17_ARB                                         0x88D1
#define GL_MATRIX18_ARB                                         0x88D2
#define GL_MATRIX19_ARB                                         0x88D3
#define GL_MATRIX20_ARB                                         0x88D4
#define GL_MATRIX21_ARB                                         0x88D5
#define GL_MATRIX22_ARB                                         0x88D6
#define GL_MATRIX23_ARB                                         0x88D7
#define GL_MATRIX24_ARB                                         0x88D8
#define GL_MATRIX25_ARB                                         0x88D9
#define GL_MATRIX26_ARB                                         0x88DA
#define GL_MATRIX27_ARB                                         0x88DB
#define GL_MATRIX28_ARB                                         0x88DC
#define GL_MATRIX29_ARB                                         0x88DD
#define GL_MATRIX30_ARB                                         0x88DE
#define GL_MATRIX31_ARB                                         0x88DF

#endif /* GL_ARB_vertex_program */

/*-------------------------------------------------------------------*/
/*------------END GL EXTENSIONS--------------------------------------*/
/*-------------------------------------------------------------------*/

/*-------------------------------------------------------------------*/
/*------------WGL EXTENSIONS HERE------------------------------------*/
/*-------------------------------------------------------------------*/

#ifdef _WIN32

/*-------------------------------------------------------------------*/
/*------------WGL_EXT_EXTENSION_STRING-------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef WGL_EXT_extensions_string
#define WGL_EXT_extensions_string 1

typedef const char* (APIENTRY * wglGetExtensionsStringEXTPROC) ();

extern wglGetExtensionsStringEXTPROC wglGetExtensionsStringEXT;

#endif /* WGL_EXT_extensions_string */

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_BUFFER_REGION----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef WGL_ARB_buffer_region
#define WGL_ARB_buffer_region 1

#define WGL_FRONT_COLOR_BUFFER_BIT_ARB                          0x00000001
#define WGL_BACK_COLOR_BUFFER_BIT_ARB                           0x00000002
#define WGL_DEPTH_BUFFER_BIT_ARB                                0x00000004
#define WGL_STENCIL_BUFFER_BIT_ARB                              0x00000008

typedef HANDLE (APIENTRY * wglCreateBufferRegionARBPROC) (HDC hDC, int iLayerPlane, UINT uType);
typedef VOID (APIENTRY * wglDeleteBufferRegionARBPROC) (HANDLE hRegion);
typedef BOOL (APIENTRY * wglSaveBufferRegionARBPROC) (HANDLE hRegion, int x, int y, int width, int height);
typedef BOOL (APIENTRY * wglRestoreBufferRegionARBPROC) (HANDLE hRegion, int x, int y, int width, int height, int xSrc, int ySrc);

extern wglCreateBufferRegionARBPROC wglCreateBufferRegionARB;
extern wglDeleteBufferRegionARBPROC wglDeleteBufferRegionARB;
extern wglSaveBufferRegionARBPROC wglSaveBufferRegionARB;
extern wglRestoreBufferRegionARBPROC wglRestoreBufferRegionARB;

#endif /* WGL_ARB_buffer_region */

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_EXTENSION_STRING-------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef WGL_ARB_extensions_string
#define WGL_ARB_extensions_string 1

typedef const char* (APIENTRY * wglGetExtensionsStringARBPROC) (HDC hdc);

extern wglGetExtensionsStringARBPROC wglGetExtensionsStringARB;

#endif /* WGL_ARB_extensions_string */

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_PBUFFER----------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef WGL_ARB_pbuffer
#define WGL_ARB_pbuffer 1

#define WGL_DRAW_TO_PBUFFER_ARB                                 0x202D
#define WGL_DRAW_TO_PBUFFER_ARB                                 0x202D
#define WGL_MAX_PBUFFER_PIXELS_ARB                              0x202E
#define WGL_MAX_PBUFFER_WIDTH_ARB                               0x202F
#define WGL_MAX_PBUFFER_HEIGHT_ARB                              0x2030
#define WGL_PBUFFER_LARGEST_ARB                                 0x2033
#define WGL_PBUFFER_WIDTH_ARB                                   0x2034
#define WGL_PBUFFER_HEIGHT_ARB                                  0x2035
#define WGL_PBUFFER_LOST_ARB                                    0x2036

DECLARE_HANDLE(HPBUFFERARB);

typedef HPBUFFERARB (APIENTRY * wglCreatePbufferARBPROC) (HDC hDC, int iPixelFormat, int iWidth, int iHeight, const int *piAttribList);
typedef HDC (APIENTRY * wglGetPbufferDCARBPROC) (HPBUFFERARB hPbuffer);
typedef int (APIENTRY * wglReleasePbufferDCARBPROC) (HPBUFFERARB hPbuffer, HDC hDC);
typedef BOOL (APIENTRY * wglDestroyPbufferARBPROC) (HPBUFFERARB hPbuffer);
typedef BOOL (APIENTRY * wglQueryPbufferARBPROC) (HPBUFFERARB hPbuffer, int iAttribute, int *piValue);

extern wglCreatePbufferARBPROC wglCreatePbufferARB;
extern wglGetPbufferDCARBPROC wglGetPbufferDCARB;
extern wglReleasePbufferDCARBPROC wglReleasePbufferDCARB;
extern wglDestroyPbufferARBPROC wglDestroyPbufferARB;
extern wglQueryPbufferARBPROC wglQueryPbufferARB;

#endif /* WGL_ARB_pbuffer */

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_PIXEL_FORMAT-----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef WGL_ARB_pixel_format
#define WGL_ARB_pixel_format 1

#define WGL_NUMBER_PIXEL_FORMATS_ARB                            0x2000
#define WGL_DRAW_TO_WINDOW_ARB                                  0x2001
#define WGL_DRAW_TO_BITMAP_ARB                                  0x2002
#define WGL_ACCELERATION_ARB                                    0x2003
#define WGL_NEED_PALETTE_ARB                                    0x2004
#define WGL_NEED_SYSTEM_PALETTE_ARB                             0x2005
#define WGL_SWAP_LAYER_BUFFERS_ARB                              0x2006
#define WGL_SWAP_METHOD_ARB                                     0x2007
#define WGL_NUMBER_OVERLAYS_ARB                                 0x2008
#define WGL_NUMBER_UNDERLAYS_ARB                                0x2009
#define WGL_TRANSPARENT_ARB                                     0x200A
#define WGL_TRANSPARENT_RED_VALUE_ARB                           0x2037
#define WGL_TRANSPARENT_GREEN_VALUE_ARB                         0x2038
#define WGL_TRANSPARENT_BLUE_VALUE_ARB                          0x2039
#define WGL_TRANSPARENT_ALPHA_VALUE_ARB                         0x203A
#define WGL_TRANSPARENT_INDEX_VALUE_ARB                         0x203B
#define WGL_SHARE_DEPTH_ARB                                     0x200C
#define WGL_SHARE_STENCIL_ARB                                   0x200D
#define WGL_SHARE_ACCUM_ARB                                     0x200E
#define WGL_SUPPORT_GDI_ARB                                     0x200F
#define WGL_SUPPORT_OPENGL_ARB                                  0x2010
#define WGL_DOUBLE_BUFFER_ARB                                   0x2011
#define WGL_STEREO_ARB                                          0x2012
#define WGL_PIXEL_TYPE_ARB                                      0x2013
#define WGL_COLOR_BITS_ARB                                      0x2014
#define WGL_RED_BITS_ARB                                        0x2015
#define WGL_RED_SHIFT_ARB                                       0x2016
#define WGL_GREEN_BITS_ARB                                      0x2017
#define WGL_GREEN_SHIFT_ARB                                     0x2018
#define WGL_BLUE_BITS_ARB                                       0x2019
#define WGL_BLUE_SHIFT_ARB                                      0x201A
#define WGL_ALPHA_BITS_ARB                                      0x201B
#define WGL_ALPHA_SHIFT_ARB                                     0x201C
#define WGL_ACCUM_BITS_ARB                                      0x201D
#define WGL_ACCUM_RED_BITS_ARB                                  0x201E
#define WGL_ACCUM_GREEN_BITS_ARB                                0x201F
#define WGL_ACCUM_BLUE_BITS_ARB                                 0x2020
#define WGL_ACCUM_ALPHA_BITS_ARB                                0x2021
#define WGL_DEPTH_BITS_ARB                                      0x2022
#define WGL_STENCIL_BITS_ARB                                    0x2023
#define WGL_AUX_BUFFERS_ARB                                     0x2024
#define WGL_NO_ACCELERATION_ARB                                 0x2025
#define WGL_GENERIC_ACCELERATION_ARB                            0x2026
#define WGL_FULL_ACCELERATION_ARB                               0x2027
#define WGL_SWAP_EXCHANGE_ARB                                   0x2028
#define WGL_SWAP_COPY_ARB                                       0x2029
#define WGL_SWAP_UNDEFINED_ARB                                  0x202A
#define WGL_TYPE_RGBA_ARB                                       0x202B
#define WGL_TYPE_COLORINDEX_ARB                                 0x202C

typedef BOOL (APIENTRY * wglGetPixelFormatAttribivARBPROC) (HDC hdc, int iPixelFormat, int iLayerPlane, UINT nAttributes, const int *piAttributes, int *piValues);
typedef BOOL (APIENTRY * wglGetPixelFormatAttribfvARBPROC) (HDC hdc, int iPixelFormat, int iLayerPlane, UINT nAttributes, const int *piAttributes, FLOAT *pfValues);
typedef BOOL (APIENTRY * wglChoosePixelFormatARBPROC) (HDC hdc, const int *piAttribIList, const FLOAT *pfAttribFList, UINT nMaxFormats, int *piFormats, UINT *nNumFormats);

extern wglGetPixelFormatAttribivARBPROC wglGetPixelFormatAttribivARB;
extern wglGetPixelFormatAttribfvARBPROC wglGetPixelFormatAttribfvARB;
extern wglChoosePixelFormatARBPROC wglChoosePixelFormatARB;

#endif /* WGL_ARB_pixel_format */

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_RENDER_TEXTURE---------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef WGL_ARB_render_texture
#define WGL_ARB_render_texture 1

#define WGL_BIND_TO_TEXTURE_RGB_ARB                             0x2070
#define WGL_BIND_TO_TEXTURE_RGBA_ARB                            0x2071
#define WGL_TEXTURE_FORMAT_ARB                                  0x2072
#define WGL_TEXTURE_TARGET_ARB                                  0x2073
#define WGL_MIPMAP_TEXTURE_ARB                                  0x2074
#define WGL_TEXTURE_RGB_ARB                                     0x2075
#define WGL_TEXTURE_RGBA_ARB                                    0x2076
#define WGL_NO_TEXTURE_ARB                                      0x2077
#define WGL_TEXTURE_CUBE_MAP_ARB                                0x2078
#define WGL_TEXTURE_1D_ARB                                      0x2079
#define WGL_TEXTURE_2D_ARB                                      0x207A
#define WGL_NO_TEXTURE_ARB                                      0x2077
#define WGL_MIPMAP_LEVEL_ARB                                    0x207B
#define WGL_CUBE_MAP_FACE_ARB                                   0x207C
#define WGL_TEXTURE_CUBE_MAP_POSITIVE_X_ARB                     0x207D
#define WGL_TEXTURE_CUBE_MAP_NEGATIVE_X_ARB                     0x207E
#define WGL_TEXTURE_CUBE_MAP_POSITIVE_Y_ARB                     0x207F
#define WGL_TEXTURE_CUBE_MAP_NEGATIVE_Y_ARB                     0x2080
#define WGL_TEXTURE_CUBE_MAP_POSITIVE_Z_ARB                     0x2081
#define WGL_TEXTURE_CUBE_MAP_NEGATIVE_Z_ARB                     0x2082
#define WGL_FRONT_LEFT_ARB                                      0x2083
#define WGL_FRONT_RIGHT_ARB                                     0x2084
#define WGL_BACK_LEFT_ARB                                       0x2085
#define WGL_BACK_RIGHT_ARB                                      0x2086
#define WGL_AUX0_ARB                                            0x2087
#define WGL_AUX1_ARB                                            0x2088
#define WGL_AUX2_ARB                                            0x2089
#define WGL_AUX3_ARB                                            0x208A
#define WGL_AUX4_ARB                                            0x208B
#define WGL_AUX5_ARB                                            0x208C
#define WGL_AUX6_ARB                                            0x208D
#define WGL_AUX7_ARB                                            0x208E
#define WGL_AUX8_ARB                                            0x208F
#define WGL_AUX9_ARB                                            0x2090

typedef BOOL (APIENTRY * wglBindTexImageARBPROC) (HPBUFFERARB hPbuffer, int iBuffer);
typedef BOOL (APIENTRY * wglReleaseTexImageARBPROC) (HPBUFFERARB hPbuffer, int iBuffer);
typedef BOOL (APIENTRY * wglSetPbufferAttribARBPROC) (HPBUFFERARB hPbuffer, const int *piAttribList);

extern wglBindTexImageARBPROC wglBindTexImageARB;
extern wglReleaseTexImageARBPROC wglReleaseTexImageARB;
extern wglSetPbufferAttribARBPROC wglSetPbufferAttribARB;

#endif /*WGL_ARB_render_texture */

/*-------------------------------------------------------------------*/
/*------------WGL_EXT_SWAP_CONTROL-----------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef WGL_EXT_swap_control
#define WGL_EXT_swap_control 1

typedef BOOL (APIENTRY * wglSwapIntervalEXTPROC) (int interval);
typedef int (APIENTRY * wglGetSwapIntervalEXTPROC) (void);

extern wglSwapIntervalEXTPROC wglSwapIntervalEXT;
extern wglGetSwapIntervalEXTPROC wglGetSwapIntervalEXT;

#endif /* WGL_EXT_swap_control */

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_MAKE_CURRENT_READ------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef WGL_ARB_make_current_read
#define WGL_ARB_make_current_read 1

#define ERROR_INVALID_PIXEL_TYPE_ARB                            0x2043
#define ERROR_INCOMPATIBLE_DEVICE_CONTEXTS_ARB	                0x2054

typedef BOOL (APIENTRY * wglMakeContextCurrentARBPROC) (HDC hDrawDC, HDC hReadDC, HGLRC hglrc);
typedef HDC (APIENTRY * wglGetCurrentReadDCARBPROC) (void);

extern wglMakeContextCurrentARBPROC wglMakeContextCurrentARB;
extern wglGetCurrentReadDCARBPROC wglGetCurrentReadDCARB;

#endif /* WGL_ARB_make_current_read */

/*-------------------------------------------------------------------*/
/*------------WGL_ARB_MULTISAMPLE------------------------------------*/
/*-------------------------------------------------------------------*/

#ifndef WGL_ARB_multisample
#define WGL_ARB_multisample 1

#define WGL_SAMPLE_BUFFERS_ARB                                  0x2041
#define WGL_SAMPLES_ARB                                         0x2042

#endif /* WGL_ARB_multisample */

/*-------------------------------------------------------------------*/
/*------------END WGL EXTENSIONS-------------------------------------*/
/*-------------------------------------------------------------------*/

#endif /* WIN32 */

/* helper stuff */

/* I use int here because C does not know bool */

#ifdef _WIN32

struct WGLExtensionTypes
{
    int EXT_extensions_string;
    int ARB_buffer_region;
    int ARB_extensions_string;
    int ARB_pbuffer;
    int ARB_pixel_format;
    int ARB_render_texture;
    int EXT_swap_control;
    int ARB_make_current_read;
    int ARB_multisample;
};

#endif /* WIN32 */

struct ExtensionTypes
{
#ifdef _WIN32 /* WGL extensions */   
    struct WGLExtensionTypes wgl;
#endif /* WIN32 */
    int ARB_imaging;
    int ARB_matrix_palette;
    int ARB_multisample;
    int ARB_multitexture;
    int ARB_point_parameters;
    int ARB_texture_compression;
    int ARB_texture_env_add;
    int ARB_texture_env_dot3;
    int ARB_texture_env_combine;
    int ARB_texture_env_crossbar;
    int ARB_texture_border_clamp;
    int ARB_texture_cube_map;
    int ARB_transpose_matrix;
    int ARB_vertex_blend;
    int EXT_abgr;
    int EXT_compiled_vertex_array;
    int EXT_fog_coord;
    int EXT_multi_draw_arrays;
    int EXT_point_parameters;
    int EXT_secondary_color;
    int EXT_stencil_wrap;
    int EXT_texture_compression_s3tc;
    int EXT_texture_filter_anisotropic;
    int EXT_texture_lod_bias;
    int EXT_vertex_shader;
    int EXT_vertex_weighting;
    int EXT_draw_range_elements;
    int ATI_element_array;
    int ATI_envmap_bumpmap;
    int ATI_fragment_shader;
    int ATI_pn_triangles;
    int ATI_texture_mirror_once;
    int ATI_vertex_array_object;
    int ATI_vertex_streams;
    int ATIX_point_sprites;
    int ATIX_texture_env_route;
    int HP_occlusion_test;
    int NV_blend_square;
    int NV_copy_depth_to_color;
    int NV_depth_clamp;
    int NV_evaluators;
    int NV_fence;
    int NV_fog_distance;
    int NV_light_max_exponent;
    int NV_occlusion_query;
    int NV_packed_depth_stencil;
    int NV_point_sprite;
    int NV_register_combiners;
    int NV_register_combiners2;
    int NV_texgen_reflection;
    int NV_texture_env_combine4;
    int NV_texture_rectangle;
    int NV_texture_shader;
    int NV_texture_shader2;
    int NV_texture_shader3;
    int NV_vertex_array_range;
    int NV_vertex_array_range2;
    int NV_vertex_program;
    int NV_vertex_program1_1;
    int SGIS_generate_mipmap;
    int SGIX_shadow;
    int SGIX_depth_texture;
    int OpenGL12;
    int OpenGL13;
    int OpenGL14;
    int ARB_texture_mirrored_repeat;
    int ARB_depth_texture;
    int ARB_shadow_ambient;
    int ARB_shadow;
    int ARB_window_pos;
    int EXT_shadow_funcs;
    int EXT_stencil_two_side;
    int ARB_vertex_program;
};

extern struct ExtensionTypes SupportedExtensions;

/* initializes everything, call this right after the rc is created. the function returns 0 if successful */
int glInitialize();

/* WGL stuff */

#ifdef _WIN32

/* sets the DC */

void _wglSetDC(HDC dc);

#endif /* WIN32 */

/* helpers end */

#ifdef __cplusplus
}
#endif

#endif /* __EXTGL_H__ */