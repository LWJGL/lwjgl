/* Copyright (c) 2001-2002 Lev Povalahev

   levp@gmx.net
  
   http://www.uni-karlsruhe.de/~uli2/

*/                         

/* following extensions are supported:

GL_ARB_imaging
GL_ARB_depth_texture
GL_ARB_matrix_palette
GL_ARB_multisample
GL_ARB_multitexture
GL_ARB_point_parameters
GL_ARB_shadow
GL_ARB_shadow_ambient
GL_ARB_texture_compression
GL_ARB_texture_env_add
GL_ARB_texture_env_dot3
GL_ARB_texture_env_combine
GL_ARB_texture_env_crossbar
GL_ARB_texture_border_clamp
GL_ARB_texture_cube_map
GL_ARB_texture_mirrored_repeat
GL_ARB_transpose_matrix
GL_ARB_vertex_blend
GL_ARB_vertex_program
GL_ARB_window_pos
GL_EXT_abgr
GL_EXT_compiled_vertex_array
GL_EXT_draw_range_elements
GL_EXT_fog_coord
GL_EXT_multi_draw_arrays
GL_EXT_point_parameters
GL_EXT_secondary_color
GL_EXT_stencil_wrap
GL_EXT_texture_compression_s3tc
GL_EXT_texture_filter_anisotropic
GL_EXT_texture_lod_bias
GL_EXT_vertex_shader
GL_EXT_vertex_weighting
GL_ATI_element_array
GL_ATI_envmap_bumpmap
GL_ATI_fragment_shader
GL_ATI_pn_triangles
GL_ATI_texture_mirror_once
GL_ATI_vertex_array_object;
GL_ATI_vertex_streams
GL_ATIX_point_sprites
GL_ATIX_texture_env_route
GL_HP_occlusion_test
GL_NV_blend_square
GL_NV_copy_depth_to_color
GL_NV_depth_clamp
GL_NV_evaluators
GL_NV_fence
GL_NV_fog_distance
GL_NV_light_max_exponent
GL_NV_occlusion_query
GL_NV_packed_depth_stencil
GL_NV_point_sprite
GL_NV_register_combiners
GL_NV_register_combiners2
GL_NV_texgen_reflection
GL_NV_texture_env_combine4
GL_NV_texture_rectangle
GL_NV_texture_shader
GL_NV_texture_shader2
GL_NV_texture_shader3
GL_NV_vertex_array_range
GL_NV_vertex_array_range2
GL_NV_vertex_program
GL_NV_vertex_program1_1
GL_SGIS_generate_mipmap
GL_SGIX_shadow
GL_SGIX_depth_texture
WGL_ARB_buffer_region
WGL_ARB_extensions_string
WGL_ARB_pbuffer
WGL_ARB_pixel_format
WGL_ARB_render_texture 
WGL_EXT_extensions_string

*/  
     
       
#include "./extgl.h"
#include <stdio.h>

/* function variables */

/*-------------------------------------*/
/* WGL stuff */
/*-------------------------------------*/

#ifdef _WIN32

/* WGL_EXT_etxension_string */

wglGetExtensionsStringEXTPROC wglGetExtensionsStringEXT = NULL;

/* WGL_ARB_buffer_region */

wglCreateBufferRegionARBPROC wglCreateBufferRegionARB = NULL;
wglDeleteBufferRegionARBPROC wglDeleteBufferRegionARB = NULL;
wglSaveBufferRegionARBPROC wglSaveBufferRegionARB = NULL;
wglRestoreBufferRegionARBPROC wglRestoreBufferRegionARB = NULL;

/* WGL_ARB_extension_string */

wglGetExtensionsStringARBPROC wglGetExtensionsStringARB = NULL;

/* WGL_ARB_pbuffer */

wglCreatePbufferARBPROC wglCreatePbufferARB = NULL;
wglGetPbufferDCARBPROC wglGetPbufferDCARB = NULL;
wglReleasePbufferDCARBPROC wglReleasePbufferDCARB = NULL;
wglDestroyPbufferARBPROC wglDestroyPbufferARB = NULL;
wglQueryPbufferARBPROC wglQueryPbufferARB = NULL;

/* WGL_ARB_pixel_format */

wglGetPixelFormatAttribivARBPROC wglGetPixelFormatAttribivARB = NULL;
wglGetPixelFormatAttribfvARBPROC wglGetPixelFormatAttribfvARB = NULL;
wglChoosePixelFormatARBPROC wglChoosePixelFormatARB = NULL;

/* WGL_ARB_render_texture */

wglBindTexImageARBPROC wglBindTexImageARB = NULL;
wglReleaseTexImageARBPROC wglReleaseTexImageARB = NULL;
wglSetPbufferAttribARBPROC wglSetPbufferAttribARB = NULL;

/* WGL_EXT_swap_control */

wglSwapIntervalEXTPROC wglSwapIntervalEXT = NULL;
wglGetSwapIntervalEXTPROC wglGetSwapIntervalEXT = NULL;

/* WGL_ARB_make_current_read */

wglMakeContextCurrentARBPROC wglMakeContextCurrentARB = NULL;
wglGetCurrentReadDCARBPROC wglGetCurrentReadDCARB = NULL;

/* VAR */

wglAllocateMemoryNVPROC wglAllocateMemoryNV = NULL;
wglFreeMemoryNVPROC wglFreeMemoryNV = NULL;



#endif /* WIN32 */

/*-------------------------------------*/
/*---WGL STUFF END---------------------*/
/*-------------------------------------*/

glBlendColorPROC glBlendColor = NULL;
glBlendEquationPROC glBlendEquation = NULL;
glDrawRangeElementsPROC glDrawRangeElements = NULL;
glColorTablePROC glColorTable = NULL;
glColorTableParameterfvPROC glColorTableParameterfv = NULL;
glColorTableParameterivPROC glColorTableParameteriv = NULL;
glCopyColorTablePROC glCopyColorTable = NULL;
glGetColorTablePROC glGetColorTable = NULL;
glGetColorTableParameterfvPROC glGetColorTableParameterfv = NULL;
glGetColorTableParameterivPROC glGetColorTableParameteriv = NULL;
glColorSubTablePROC glColorSubTable = NULL;
glCopyColorSubTablePROC glCopyColorSubTable = NULL;
glConvolutionFilter1DPROC glConvolutionFilter1D = NULL;
glConvolutionFilter2DPROC glConvolutionFilter2D = NULL;
glConvolutionParameterfPROC glConvolutionParameterf = NULL;
glConvolutionParameterfvPROC glConvolutionParameterfv = NULL;
glConvolutionParameteriPROC glConvolutionParameteri = NULL;
glConvolutionParameterivPROC glConvolutionParameteriv = NULL;
glCopyConvolutionFilter1DPROC glCopyConvolutionFilter1D = NULL;
glCopyConvolutionFilter2DPROC glCopyConvolutionFilter2D = NULL;
glGetConvolutionFilterPROC glGetConvolutionFilter = NULL;
glGetConvolutionParameterfvPROC glGetConvolutionParameterfv = NULL;
glGetConvolutionParameterivPROC glGetConvolutionParameteriv = NULL;
glGetSeparableFilterPROC glGetSeparableFilter = NULL;
glSeparableFilter2DPROC glSeparableFilter2D = NULL;
glGetHistogramPROC glGetHistogram = NULL;
glGetHistogramParameterfvPROC glGetHistogramParameterfv = NULL;
glGetHistogramParameterivPROC glGetHistogramParameteriv = NULL;
glGetMinmaxPROC glGetMinmax = NULL;
glGetMinmaxParameterfvPROC glGetMinmaxParameterfv = NULL;
glGetMinmaxParameterivPROC glGetMinmaxParameteriv = NULL;
glHistogramPROC glHistogram = NULL;
glMinmaxPROC glMinmax = NULL;
glResetHistogramPROC glResetHistogram = NULL;
glResetMinmaxPROC glResetMinmax = NULL;
glTexImage3DPROC glTexImage3D = NULL;
glTexSubImage3DPROC glTexSubImage3D = NULL;
glCopyTexSubImage3DPROC glCopyTexSubImage3D = NULL;

/* 1.3 */

glActiveTexturePROC glActiveTexture = NULL;
glClientActiveTexturePROC glClientActiveTexture = NULL;
glMultiTexCoord1dPROC glMultiTexCoord1d = NULL;
glMultiTexCoord1dvPROC glMultiTexCoord1dv = NULL;
glMultiTexCoord1fPROC glMultiTexCoord1f = NULL;
glMultiTexCoord1fvPROC glMultiTexCoord1fv = NULL;
glMultiTexCoord1iPROC glMultiTexCoord1i = NULL;
glMultiTexCoord1ivPROC glMultiTexCoord1iv = NULL;
glMultiTexCoord1sPROC glMultiTexCoord1s = NULL;
glMultiTexCoord1svPROC glMultiTexCoord1sv = NULL;
glMultiTexCoord2dPROC glMultiTexCoord2d = NULL;
glMultiTexCoord2dvPROC glMultiTexCoord2dv = NULL;
glMultiTexCoord2fPROC glMultiTexCoord2f = NULL;
glMultiTexCoord2fvPROC glMultiTexCoord2fv = NULL;
glMultiTexCoord2iPROC glMultiTexCoord2i = NULL;
glMultiTexCoord2ivPROC glMultiTexCoord2iv = NULL;
glMultiTexCoord2sPROC glMultiTexCoord2s = NULL;
glMultiTexCoord2svPROC glMultiTexCoord2sv = NULL;
glMultiTexCoord3dPROC glMultiTexCoord3d = NULL;
glMultiTexCoord3dvPROC glMultiTexCoord3dv = NULL;
glMultiTexCoord3fPROC glMultiTexCoord3f = NULL;
glMultiTexCoord3fvPROC glMultiTexCoord3fv = NULL;
glMultiTexCoord3iPROC glMultiTexCoord3i = NULL;
glMultiTexCoord3ivPROC glMultiTexCoord3iv = NULL;
glMultiTexCoord3sPROC glMultiTexCoord3s = NULL;
glMultiTexCoord3svPROC glMultiTexCoord3sv = NULL;
glMultiTexCoord4dPROC glMultiTexCoord4d = NULL;
glMultiTexCoord4dvPROC glMultiTexCoord4dv = NULL;
glMultiTexCoord4fPROC glMultiTexCoord4f = NULL;
glMultiTexCoord4fvPROC glMultiTexCoord4fv = NULL;
glMultiTexCoord4iPROC glMultiTexCoord4i = NULL;
glMultiTexCoord4ivPROC glMultiTexCoord4iv = NULL;
glMultiTexCoord4sPROC glMultiTexCoord4s = NULL;
glMultiTexCoord4svPROC glMultiTexCoord4sv = NULL;
glLoadTransposeMatrixfPROC glLoadTransposeMatrixf = NULL;
glLoadTransposeMatrixdPROC glLoadTransposeMatrixd = NULL;
glMultTransposeMatrixfPROC glMultTransposeMatrixf = NULL;
glMultTransposeMatrixdPROC glMultTransposeMatrixd = NULL;
glCompressedTexImage3DPROC glCompressedTexImage3D = NULL;
glCompressedTexImage2DPROC glCompressedTexImage2D = NULL;
glCompressedTexImage1DPROC glCompressedTexImage1D = NULL;
glCompressedTexSubImage3DPROC glCompressedTexSubImage3D = NULL;
glCompressedTexSubImage2DPROC glCompressedTexSubImage2D = NULL;
glCompressedTexSubImage1DPROC glCompressedTexSubImage1D = NULL;
glGetCompressedTexImagePROC glGetCompressedTexImage = NULL;
glSampleCoveragePROC glSampleCoverage = NULL;

/* ARB_multitexture */

glActiveTexturePROC glActiveTextureARB = NULL;
glClientActiveTexturePROC glClientActiveTextureARB = NULL;
glMultiTexCoord1dPROC glMultiTexCoord1dARB = NULL;
glMultiTexCoord1dvPROC glMultiTexCoord1dvARB = NULL;
glMultiTexCoord1fPROC glMultiTexCoord1fARB = NULL;
glMultiTexCoord1fvPROC glMultiTexCoord1fvARB = NULL;
glMultiTexCoord1iPROC glMultiTexCoord1iARB = NULL;
glMultiTexCoord1ivPROC glMultiTexCoord1ivARB = NULL;
glMultiTexCoord1sPROC glMultiTexCoord1sARB = NULL;
glMultiTexCoord1svPROC glMultiTexCoord1svARB = NULL;
glMultiTexCoord2dPROC glMultiTexCoord2dARB = NULL;
glMultiTexCoord2dvPROC glMultiTexCoord2dvARB = NULL;
glMultiTexCoord2fPROC glMultiTexCoord2fARB = NULL;
glMultiTexCoord2fvPROC glMultiTexCoord2fvARB = NULL;
glMultiTexCoord2iPROC glMultiTexCoord2iARB = NULL;
glMultiTexCoord2ivPROC glMultiTexCoord2ivARB = NULL;
glMultiTexCoord2sPROC glMultiTexCoord2sARB = NULL;
glMultiTexCoord2svPROC glMultiTexCoord2svARB = NULL;
glMultiTexCoord3dPROC glMultiTexCoord3dARB = NULL;
glMultiTexCoord3dvPROC glMultiTexCoord3dvARB = NULL;
glMultiTexCoord3fPROC glMultiTexCoord3fARB = NULL;
glMultiTexCoord3fvPROC glMultiTexCoord3fvARB = NULL;
glMultiTexCoord3iPROC glMultiTexCoord3iARB = NULL;
glMultiTexCoord3ivPROC glMultiTexCoord3ivARB = NULL;
glMultiTexCoord3sPROC glMultiTexCoord3sARB = NULL;
glMultiTexCoord3svPROC glMultiTexCoord3svARB = NULL;
glMultiTexCoord4dPROC glMultiTexCoord4dARB = NULL;
glMultiTexCoord4dvPROC glMultiTexCoord4dvARB = NULL;
glMultiTexCoord4fPROC glMultiTexCoord4fARB = NULL;
glMultiTexCoord4fvPROC glMultiTexCoord4fvARB = NULL;
glMultiTexCoord4iPROC glMultiTexCoord4iARB = NULL;
glMultiTexCoord4ivPROC glMultiTexCoord4ivARB = NULL;
glMultiTexCoord4sPROC glMultiTexCoord4sARB = NULL;
glMultiTexCoord4svPROC glMultiTexCoord4svARB = NULL;

/* ARB_transpose_matrix */

glLoadTransposeMatrixfPROC glLoadTransposeMatrixfARB = NULL;
glLoadTransposeMatrixdPROC glLoadTransposeMatrixdARB = NULL;
glMultTransposeMatrixfPROC glMultTransposeMatrixfARB = NULL;
glMultTransposeMatrixdPROC glMultTransposeMatrixdARB = NULL;

/* ARB_texture_compression */

glCompressedTexImage3DPROC glCompressedTexImage3DARB = NULL;
glCompressedTexImage2DPROC glCompressedTexImage2DARB = NULL;
glCompressedTexImage1DPROC glCompressedTexImage1DARB = NULL; 
glCompressedTexSubImage3DPROC glCompressedTexSubImage3DARB = NULL;
glCompressedTexSubImage2DPROC glCompressedTexSubImage2DARB = NULL;
glCompressedTexSubImage1DPROC glCompressedTexSubImage1DARB = NULL;
glGetCompressedTexImagePROC glGetCompressedTexImageARB = NULL;

glSecondaryColor3bEXTPROC glSecondaryColor3bEXT = NULL;
glSecondaryColor3bvEXTPROC glSecondaryColor3bvEXT = NULL;
glSecondaryColor3dEXTPROC glSecondaryColor3dEXT = NULL;
glSecondaryColor3dvEXTPROC glSecondaryColor3dvEXT = NULL;
glSecondaryColor3fEXTPROC glSecondaryColor3fEXT = NULL;
glSecondaryColor3fvEXTPROC glSecondaryColor3fvEXT = NULL;
glSecondaryColor3iEXTPROC glSecondaryColor3iEXT = NULL;
glSecondaryColor3ivEXTPROC glSecondaryColor3ivEXT = NULL;
glSecondaryColor3sEXTPROC glSecondaryColor3sEXT = NULL;
glSecondaryColor3svEXTPROC glSecondaryColor3svEXT = NULL;
glSecondaryColor3ubEXTPROC glSecondaryColor3ubEXT = NULL;
glSecondaryColor3ubvEXTPROC glSecondaryColor3ubvEXT = NULL;
glSecondaryColor3uiEXTPROC glSecondaryColor3uiEXT = NULL;
glSecondaryColor3uivEXTPROC glSecondaryColor3uivEXT = NULL;
glSecondaryColor3usEXTPROC glSecondaryColor3usEXT = NULL;
glSecondaryColor3usvEXTPROC glSecondaryColor3usvEXT = NULL;
glSecondaryColorPointerEXTPROC glSecondaryColorPointerEXT = NULL;

/* ETX_compiled_vertex_array */

glLockArraysEXTPROC glLockArraysEXT = NULL;
glUnlockArraysEXTPROC glUnlockArraysEXT = NULL;


/* EXT_fog_color */

glFogCoordfEXTPROC glFogCoordfEXT = NULL;
glFogCoordfvEXTPROC glFogCoordfvEXT = NULL;
glFogCoorddEXTPROC glFogCoorddEXT = NULL;
glFogCoorddvEXTPROC glFogCoorddvEXT = NULL;
glFogCoordPointerEXTPROC glFogCoordPointerEXT = NULL;

/* NV_vertex_array_range */

glFlushVertexArrayRangeNVPROC glFlushVertexArrayRangeNV = NULL;
glVertexArrayRangeNVPROC glVertexArrayRangeNV = NULL;

/* EXT_point_parameters */

glPointParameterfEXTPROC glPointParameterfEXT = NULL;
glPointParameterfvEXTPROC glPointParameterfvEXT = NULL;

/* NV_register_combiners */

glCombinerParameterfvNVPROC glCombinerParameterfvNV = NULL;
glCombinerParameterfNVPROC  glCombinerParameterfNV = NULL;
glCombinerParameterivNVPROC glCombinerParameterivNV = NULL;
glCombinerParameteriNVPROC glCombinerParameteriNV = NULL;
glCombinerInputNVPROC glCombinerInputNV = NULL;
glCombinerOutputNVPROC glCombinerOutputNV = NULL;
glFinalCombinerInputNVPROC glFinalCombinerInputNV = NULL;
glGetCombinerInputParameterfvNVPROC glGetCombinerInputParameterfvNV = NULL;
glGetCombinerInputParameterivNVPROC glGetCombinerInputParameterivNV = NULL;
glGetCombinerOutputParameterfvNVPROC glGetCombinerOutputParameterfvNV = NULL;
glGetCombinerOutputParameterivNVPROC glGetCombinerOutputParameterivNV = NULL;
glGetFinalCombinerInputParameterfvNVPROC glGetFinalCombinerInputParameterfvNV = NULL;
glGetFinalCombinerInputParameterivNVPROC glGetFinalCombinerInputParameterivNV = NULL;

/* ARB_multisample */

glSampleCoveragePROC glSampleCoverageARB = NULL;

/* EXT_vertex_weighting */

glVertexWeightfEXTPROC glVertexWeightfEXT = NULL;
glVertexWeightfvEXTPROC glVertexWeightfvEXT = NULL;
glVertexWeightPointerEXTPROC glVertexWeightPointerEXT = NULL;

/* NV_vertex_program */

glBindProgramNVPROC glBindProgramNV = NULL;
glDeleteProgramsNVPROC glDeleteProgramsNV = NULL;
glExecuteProgramNVPROC glExecuteProgramNV = NULL;
glGenProgramsNVPROC glGenProgramsNV = NULL;
glAreProgramsResidentNVPROC glAreProgramsResidentNV = NULL;
glRequestResidentProgramsNVPROC glRequestResidentProgramsNV = NULL;
glGetProgramParameterfvNVPROC glGetProgramParameterfvNV = NULL;
glGetProgramParameterdvNVPROC glGetProgramParameterdvNV = NULL;
glGetProgramivNVPROC glGetProgramivNV = NULL;
glGetProgramStringNVPROC glGetProgramStringNV = NULL;
glGetTrackMatrixivNVPROC glGetTrackMatrixivNV = NULL;
glGetVertexAttribdvNVPROC glGetVertexAttribdvNV = NULL;
glGetVertexAttribfvNVPROC glGetVertexAttribfvNV = NULL;
glGetVertexAttribivNVPROC glGetVertexAttribivNV = NULL;
glGetVertexAttribPointervNVPROC glGetVertexAttribPointervNV = NULL;
glIsProgramNVPROC glIsProgramNV = NULL;
glLoadProgramNVPROC glLoadProgramNV = NULL;
glProgramParameter4fNVPROC glProgramParameter4fNV = NULL;
glProgramParameter4dNVPROC glProgramParameter4dNV = NULL;
glProgramParameter4dvNVPROC glProgramParameter4dvNV = NULL;
glProgramParameter4fvNVPROC glProgramParameter4fvNV = NULL;
glProgramParameters4dvNVPROC glProgramParameters4dvNV = NULL;
glProgramParameters4fvNVPROC glProgramParameters4fvNV = NULL;
glTrackMatrixNVPROC glTrackMatrixNV = NULL;
glVertexAttribPointerNVPROC glVertexAttribPointerNV = NULL;
glVertexAttrib1sNVPROC glVertexAttrib1sNV = NULL;
glVertexAttrib1fNVPROC glVertexAttrib1fNV = NULL;
glVertexAttrib1dNVPROC glVertexAttrib1dNV = NULL;
glVertexAttrib2sNVPROC glVertexAttrib2sNV = NULL;
glVertexAttrib2fNVPROC glVertexAttrib2fNV = NULL;
glVertexAttrib2dNVPROC glVertexAttrib2dNV = NULL;
glVertexAttrib3sNVPROC glVertexAttrib3sNV = NULL;
glVertexAttrib3fNVPROC glVertexAttrib3fNV = NULL;
glVertexAttrib3dNVPROC glVertexAttrib3dNV = NULL;
glVertexAttrib4sNVPROC glVertexAttrib4sNV = NULL;
glVertexAttrib4fNVPROC glVertexAttrib4fNV = NULL;
glVertexAttrib4dNVPROC glVertexAttrib4dNV = NULL;
glVertexAttrib4ubNVPROC glVertexAttrib4ubNV = NULL;
glVertexAttrib1svNVPROC glVertexAttrib1svNV = NULL;
glVertexAttrib1fvNVPROC glVertexAttrib1fvNV = NULL;
glVertexAttrib1dvNVPROC glVertexAttrib1dvNV = NULL;
glVertexAttrib2svNVPROC glVertexAttrib2svNV = NULL;
glVertexAttrib2fvNVPROC glVertexAttrib2fvNV = NULL;
glVertexAttrib2dvNVPROC glVertexAttrib2dvNV = NULL;
glVertexAttrib3svNVPROC glVertexAttrib3svNV = NULL;
glVertexAttrib3fvNVPROC glVertexAttrib3fvNV = NULL;
glVertexAttrib3dvNVPROC glVertexAttrib3dvNV = NULL;
glVertexAttrib4svNVPROC glVertexAttrib4svNV = NULL;
glVertexAttrib4fvNVPROC glVertexAttrib4fvNV = NULL;
glVertexAttrib4dvNVPROC glVertexAttrib4dvNV = NULL;
glVertexAttrib4ubvNVPROC glVertexAttrib4ubvNV = NULL;
glVertexAttribs1svNVPROC glVertexAttribs1svNV = NULL;
glVertexAttribs1fvNVPROC glVertexAttribs1fvNV = NULL;
glVertexAttribs1dvNVPROC glVertexAttribs1dvNV = NULL;
glVertexAttribs2svNVPROC glVertexAttribs2svNV = NULL;
glVertexAttribs2fvNVPROC glVertexAttribs2fvNV = NULL;
glVertexAttribs2dvNVPROC glVertexAttribs2dvNV = NULL;
glVertexAttribs3svNVPROC glVertexAttribs3svNV = NULL;
glVertexAttribs3fvNVPROC glVertexAttribs3fvNV = NULL;
glVertexAttribs3dvNVPROC glVertexAttribs3dvNV = NULL;
glVertexAttribs4svNVPROC glVertexAttribs4svNV = NULL;
glVertexAttribs4fvNVPROC glVertexAttribs4fvNV = NULL;
glVertexAttribs4dvNVPROC glVertexAttribs4dvNV = NULL;
glVertexAttribs4ubvNVPROC glVertexAttribs4ubvNV = NULL;

/* NV_fence */

glGenFencesNVPROC glGenFencesNV = NULL;
glDeleteFencesNVPROC glDeleteFencesNV = NULL;
glSetFenceNVPROC glSetFenceNV = NULL;
glTestFenceNVPROC glTestFenceNV = NULL;
glFinishFenceNVPROC glFinishFenceNV = NULL;
glIsFenceNVPROC glIsFenceNV = NULL;
glGetFenceivNVPROC glGetFenceivNV = NULL;

/* NV_register_combiners2 */

glCombinerStageParameterfvNVPROC glCombinerStageParameterfvNV = NULL;
glGetCombinerStageParameterfvNVPROC glGetCombinerStageParameterfvNV = NULL;

/* NV_evaluators */

glMapControlPointsNVPROC glMapControlPointsNV = NULL;
glMapParameterivNVPROC glMapParameterivNV = NULL;
glMapParameterfvNVPROC glMapParameterfvNV = NULL;
glGetMapControlPointsNVPROC glGetMapControlPointsNV = NULL;
glGetMapParameterivNVPROC glGetMapParameterivNV = NULL;
glGetMapParameterfvNVPROC glGetMapParameterfvNV = NULL;
glGetMapAttribParameterivNVPROC glGetMapAttribParameterivNV = NULL;
glGetMapAttribParameterfvNVPROC glGetMapAttribParameterfvNV = NULL;
glEvalMapsNVPROC glEvalMapsNV = NULL;

/* ATI_pn_triangles */

glPNTrianglesiATIPROC glPNTrianglesiATI = NULL;
glPNTrianglesfATIPROC glPNTrianglesfATI = NULL;

/* ARB_point_parameters */

glPointParameterfARBPROC glPointParameterfARB = NULL;
glPointParameterfvARBPROC glPointParameterfvARB = NULL;

/* ARB_vertex_blend */

glWeightbvARBPROC glWeightbvARB = NULL;
glWeightsvARBPROC glWeightsvARB = NULL;
glWeightivARBPROC glWeightivARB = NULL;
glWeightfvARBPROC glWeightfvARB = NULL;
glWeightdvARBPROC glWeightdvARB = NULL;
glWeightubvARBPROC glWeightubvARB = NULL;
glWeightusvARBPROC glWeightusvARB = NULL;
glWeightuivARBPROC glWeightuivARB = NULL;
glWeightPointerARBPROC glWeightPointerARB = NULL;
glVertexBlendARBPROC glVertexBlendARB = NULL;

/* EXT_multi_draw_arrays */

glMultiDrawArraysEXTPROC glMultiDrawArraysEXT = NULL;
glMultiDrawElementsEXTPROC glMultiDrawElementsEXT = NULL;

/* ARB_matrix_palette */

glCurrentPaletteMatrixARBPROC glCurrentPaletteMatrixARB = NULL;
glMatrixIndexubvARBPROC glMatrixIndexubvARB = NULL;
glMatrixIndexusvARBPROC glMatrixIndexusvARB = NULL;
glMatrixIndexuivARBPROC glMatrixIndexuivARB = NULL;
glMatrixIndexPointerARBPROC glMatrixIndexPointerARB = NULL;

/* EXT_vertex_shader */

glBeginVertexShaderEXTPROC glBeginVertexShaderEXT = NULL;
glEndVertexShaderEXTPROC glEndVertexShaderEXT = NULL;
glBindVertexShaderEXTPROC glBindVertexShaderEXT = NULL;
glGenVertexShadersEXTPROC glGenVertexShadersEXT = NULL;
glDeleteVertexShaderEXTPROC glDeleteVertexShaderEXT = NULL;
glShaderOp1EXTPROC glShaderOp1EXT = NULL;
glShaderOp2EXTPROC glShaderOp2EXT = NULL;
glShaderOp3EXTPROC glShaderOp3EXT = NULL;
glSwizzleEXTPROC glSwizzleEXT = NULL;
glWriteMaskEXTPROC glWriteMaskEXT = NULL;
glInsertComponentEXTPROC glInsertComponentEXT = NULL;
glExtractComponentEXTPROC glExtractComponentEXT = NULL;
glGenSymbolsEXTPROC glGenSymbolsEXT = NULL;
glSetInvariantEXTPROC glSetInvariantEXT = NULL;
glSetLocalConstantEXTPROC glSetLocalConstantEXT = NULL;
glVariantbvEXTPROC glVariantbvEXT = NULL;
glVariantsvEXTPROC glVariantsvEXT = NULL;
glVariantivEXTPROC glVariantivEXT = NULL;
glVariantfvEXTPROC glVariantfvEXT = NULL;
glVariantdvEXTPROC glVariantdvEXT = NULL;
glVariantubvEXTPROC glVariantubvEXT = NULL;
glVariantusvEXTPROC glVariantusvEXT = NULL;
glVariantuivEXTPROC glVariantuivEXT = NULL;
glVariantPointerEXTPROC glVariantPointerEXT = NULL;
glEnableVariantClientStateEXTPROC glEnableVariantClientStateEXT = NULL;
glDisableVariantClientStateEXTPROC glDisableVariantClientStateEXT = NULL;
glBindLightParameterEXTPROC glBindLightParameterEXT = NULL;
glBindMaterialParameterEXTPROC glBindMaterialParameterEXT = NULL;
glBindTexGenParameterEXTPROC glBindTexGenParameterEXT = NULL;
glBindTextureUnitParameterEXTPROC glBindTextureUnitParameterEXT = NULL;
glBindParameterEXTPROC glBindParameterEXT = NULL;
glIsVariantEnabledEXTPROC glIsVariantEnabledEXT = NULL;
glGetVariantBooleanvEXTPROC glGetVariantBooleanvEXT = NULL;
glGetVariantIntegervEXTPROC glGetVariantIntegervEXT = NULL;
glGetVariantFloatvEXTPROC glGetVariantFloatvEXT = NULL;
glGetVariantPointervEXTPROC glGetVariantPointervEXT = NULL;
glGetInvariantBooleanvEXTPROC glGetInvariantBooleanvEXT = NULL;
glGetInvariantIntegervEXTPROC glGetInvariantIntegervEXT = NULL;
glGetInvariantFloatvEXTPROC glGetInvariantFloatvEXT = NULL;
glGetLocalConstantBooleanvEXTPROC glGetLocalConstantBooleanvEXT = NULL;
glGetLocalConstantIntegervEXTPROC glGetLocalConstantIntegervEXT = NULL;
glGetLocalConstantFloatvEXTPROC glGetLocalConstantFloatvEXT = NULL;

/* ATI_envmap_bumpmap */

glTexBumpParameterivATIPROC glTexBumpParameterivATI = NULL;
glTexBumpParameterfvATIPROC glTexBumpParameterfvATI = NULL;
glGetTexBumpParameterivATIPROC glGetTexBumpParameterivATI = NULL;
glGetTexBumpParameterfvATIPROC glGetTexBumpParameterfvATI = NULL;

/* ATI_fragment_shader */

glGenFragmentShadersATIPROC glGenFragmentShadersATI = NULL;
glBindFragmentShaderATIPROC glBindFragmentShaderATI = NULL;
glDeleteFragmentShaderATIPROC glDeleteFragmentShaderATI = NULL;
glBeginFragmentShaderATIPROC glBeginFragmentShaderATI = NULL;
glEndFragmentShaderATIPROC glEndFragmentShaderATI = NULL;
glPassTexCoordATIPROC glPassTexCoordATI = NULL;
glSampleMapATIPROC glSampleMapATI = NULL;
glColorFragmentOp1ATIPROC glColorFragmentOp1ATI = NULL;
glColorFragmentOp2ATIPROC glColorFragmentOp2ATI = NULL;
glColorFragmentOp3ATIPROC glColorFragmentOp3ATI = NULL;
glAlphaFragmentOp1ATIPROC glAlphaFragmentOp1ATI = NULL;
glAlphaFragmentOp2ATIPROC glAlphaFragmentOp2ATI = NULL;
glAlphaFragmentOp3ATIPROC glAlphaFragmentOp3ATI = NULL;
glSetFragmentShaderConstantATIPROC glSetFragmentShaderConstantATI = NULL;

/* ATI_element_array */

glElementPointerATIPROC glElementPointerATI = NULL;
glDrawElementArrayATIPROC glDrawElementArrayATI = NULL;
glDrawRangeElementArrayATIPROC glDrawRangeElementArrayATI = NULL;

/* ATI_vertex_streams */

glClientActiveVertexStreamATIPROC glClientActiveVertexStreamATI = NULL;
glVertexBlendEnviATIPROC glVertexBlendEnviATI = NULL;
glVertexBlendEnvfATIPROC glVertexBlendEnvfATI = NULL;
glVertexStream2sATIPROC glVertexStream2sATI = NULL;
glVertexStream2svATIPROC glVertexStream2svATI = NULL;
glVertexStream2iATIPROC glVertexStream2iATI = NULL;
glVertexStream2ivATIPROC glVertexStream2ivATI = NULL;
glVertexStream2fATIPROC glVertexStream2fATI = NULL;
glVertexStream2fvATIPROC glVertexStream2fvATI = NULL;
glVertexStream2dATIPROC glVertexStream2dATI = NULL;
glVertexStream2dvATIPROC glVertexStream2dvATI = NULL;
glVertexStream3sATIPROC glVertexStream3sATI = NULL;
glVertexStream3svATIPROC glVertexStream3svATI = NULL;
glVertexStream3iATIPROC glVertexStream3iATI = NULL;
glVertexStream3ivATIPROC glVertexStream3ivATI = NULL;
glVertexStream3fATIPROC glVertexStream3fATI = NULL;
glVertexStream3fvATIPROC glVertexStream3fvATI = NULL;
glVertexStream3dATIPROC glVertexStream3dATI = NULL;
glVertexStream3dvATIPROC glVertexStream3dvATI = NULL;
glVertexStream4sATIPROC glVertexStream4sATI = NULL;
glVertexStream4svATIPROC glVertexStream4svATI = NULL;
glVertexStream4iATIPROC glVertexStream4iATI = NULL;
glVertexStream4ivATIPROC glVertexStream4ivATI = NULL;
glVertexStream4fATIPROC glVertexStream4fATI = NULL;
glVertexStream4fvATIPROC glVertexStream4fvATI = NULL;
glVertexStream4dATIPROC glVertexStream4dATI = NULL;
glVertexStream4dvATIPROC glVertexStream4dvATI = NULL;
glNormalStream3bATIPROC glNormalStream3bATI = NULL;
glNormalStream3bvATIPROC glNormalStream3bvATI = NULL;
glNormalStream3sATIPROC glNormalStream3sATI = NULL;
glNormalStream3svATIPROC glNormalStream3svATI = NULL;
glNormalStream3iATIPROC glNormalStream3iATI = NULL;
glNormalStream3ivATIPROC glNormalStream3ivATI = NULL;
glNormalStream3fATIPROC glNormalStream3fATI = NULL;
glNormalStream3fvATIPROC glNormalStream3fvATI = NULL;
glNormalStream3dATIPROC glNormalStream3dATI = NULL;
glNormalStream3dvATIPROC glNormalStream3dvATI = NULL;

/* ATI_vertex_array_object */

glNewObjectBufferATIPROC glNewObjectBufferATI = NULL;
glIsObjectBufferATIPROC glIsObjectBufferATI = NULL;
glUpdateObjectBufferATIPROC glUpdateObjectBufferATI = NULL;
glGetObjectBufferfvATIPROC glGetObjectBufferfvATI = NULL;
glGetObjectBufferivATIPROC glGetObjectBufferivATI = NULL;
glFreeObjectBufferATIPROC glFreeObjectBufferATI = NULL;
glArrayObjectATIPROC glArrayObjectATI = NULL;
glGetArrayObjectfvATIPROC glGetArrayObjectfvATI = NULL;
glGetArrayObjectivATIPROC glGetArrayObjectivATI = NULL;
glVariantArrayObjectATIPROC glVariantArrayObjectATI = NULL;
glGetVariantArrayObjectfvATIPROC glGetVariantArrayObjectfvATI = NULL;
glGetVariantArrayObjectivATIPROC glGetVariantArrayObjectivATI = NULL;

/* NV_occlusion_query */

glGenOcclusionQueriesNVPROC glGenOcclusionQueriesNV = NULL;
glDeleteOcclusionQueriesNVPROC glDeleteOcclusionQueriesNV = NULL;
glIsOcclusionQueryNVPROC glIsOcclusionQueryNV = NULL;
glBeginOcclusionQueryNVPROC glBeginOcclusionQueryNV = NULL;
glEndOcclusionQueryNVPROC glEndOcclusionQueryNV = NULL;
glGetOcclusionQueryivNVPROC glGetOcclusionQueryivNV = NULL;
glGetOcclusionQueryuivNVPROC glGetOcclusionQueryuivNV = NULL;

/* NV_point_sprite */

glPointParameteriNVPROC glPointParameteriNV = NULL;
glPointParameterivNVPROC glPointParameterivNV = NULL;

/* ARB_window_pos */

glWindowPos2dARBPROC glWindowPos2dARB = NULL;
glWindowPos2fARBPROC glWindowPos2fARB = NULL;
glWindowPos2iARBPROC glWindowPos2iARB = NULL;
glWindowPos2sARBPROC glWindowPos2sARB = NULL;
glWindowPos2dvARBPROC glWindowPos2dvARB = NULL;
glWindowPos2fvARBPROC glWindowPos2fvARB = NULL;
glWindowPos2ivARBPROC glWindowPos2ivARB = NULL;
glWindowPos2svARBPROC glWindowPos2svARB = NULL;
glWindowPos3dARBPROC glWindowPos3dARB = NULL;
glWindowPos3fARBPROC glWindowPos3fARB = NULL;
glWindowPos3iARBPROC glWindowPos3iARB = NULL;
glWindowPos3sARBPROC glWindowPos3sARB = NULL;
glWindowPos3dvARBPROC glWindowPos3dvARB = NULL;
glWindowPos3fvARBPROC glWindowPos3fvARB = NULL;
glWindowPos3ivARBPROC glWindowPos3ivARB = NULL;
glWindowPos3svARBPROC glWindowPos3svARB = NULL;

/* EXT_draw_range_elements */

glDrawRangeElementsEXTPROC glDrawRangeElementsEXT = NULL;

/* EXT_stencil_two_side */

glActiveStencilFaceEXTPROC glActiveStencilFaceEXT = NULL;

/* ARB_vertex_program */

glVertexAttrib1sARBPROC glVertexAttrib1sARB = NULL;
glVertexAttrib1fARBPROC glVertexAttrib1fARB = NULL;
glVertexAttrib1dARBPROC glVertexAttrib1dARB = NULL;
glVertexAttrib2sARBPROC glVertexAttrib2sARB = NULL;
glVertexAttrib2fARBPROC glVertexAttrib2fARB = NULL;
glVertexAttrib2dARBPROC glVertexAttrib2dARB = NULL;
glVertexAttrib3sARBPROC glVertexAttrib3sARB = NULL;
glVertexAttrib3fARBPROC glVertexAttrib3fARB = NULL;
glVertexAttrib3dARBPROC glVertexAttrib3dARB = NULL;
glVertexAttrib4sARBPROC glVertexAttrib4sARB = NULL;
glVertexAttrib4fARBPROC glVertexAttrib4fARB = NULL;
glVertexAttrib4dARBPROC glVertexAttrib4dARB = NULL;
glVertexAttrib4NubARBPROC glVertexAttrib4NubARB = NULL;
glVertexAttrib1svARBPROC glVertexAttrib1svARB = NULL;
glVertexAttrib1fvARBPROC glVertexAttrib1fvARB = NULL;
glVertexAttrib1dvARBPROC glVertexAttrib1dvARB = NULL;
glVertexAttrib2svARBPROC glVertexAttrib2svARB = NULL;
glVertexAttrib2fvARBPROC glVertexAttrib2fvARB = NULL;
glVertexAttrib2dvARBPROC glVertexAttrib2dvARB = NULL;
glVertexAttrib3svARBPROC glVertexAttrib3svARB = NULL;
glVertexAttrib3fvARBPROC glVertexAttrib3fvARB = NULL;
glVertexAttrib3dvARBPROC glVertexAttrib3dvARB = NULL;
glVertexAttrib4bvARBPROC glVertexAttrib4bvARB = NULL;
glVertexAttrib4svARBPROC glVertexAttrib4svARB = NULL;
glVertexAttrib4ivARBPROC glVertexAttrib4ivARB = NULL;
glVertexAttrib4ubvARBPROC glVertexAttrib4ubvARB = NULL;
glVertexAttrib4usvARBPROC glVertexAttrib4usvARB = NULL;
glVertexAttrib4uivARBPROC glVertexAttrib4uivARB = NULL;
glVertexAttrib4fvARBPROC glVertexAttrib4fvARB = NULL;
glVertexAttrib4dvARBPROC glVertexAttrib4dvARB = NULL;
glVertexAttrib4NbvARBPROC glVertexAttrib4NbvARB = NULL;
glVertexAttrib4NsvARBPROC glVertexAttrib4NsvARB = NULL;
glVertexAttrib4NivARBPROC glVertexAttrib4NivARB = NULL;
glVertexAttrib4NubvARBPROC glVertexAttrib4NubvARB = NULL;
glVertexAttrib4NusvARBPROC glVertexAttrib4NusvARB = NULL;
glVertexAttrib4NuivARBPROC glVertexAttrib4NuivARB = NULL;
glVertexAttribPointerARBPROC glVertexAttribPointerARB = NULL;
glEnableVertexAttribArrayARBPROC glEnableVertexAttribArrayARB = NULL;
glDisableVertexAttribArrayARBPROC glDisableVertexAttribArrayARB = NULL;
glProgramStringARBPROC glProgramStringARB = NULL;
glBindProgramARBPROC glBindProgramARB = NULL;
glDeleteProgramsARBPROC glDeleteProgramsARB = NULL;
glGenProgramsARBPROC glGenProgramsARB = NULL;
glProgramEnvParameter4dARBPROC glProgramEnvParameter4dARB = NULL;
glProgramEnvParameter4dvARBPROC glProgramEnvParameter4dvARB = NULL;
glProgramEnvParameter4fARBPROC glProgramEnvParameter4fARB = NULL;
glProgramEnvParameter4fvARBPROC glProgramEnvParameter4fvARB = NULL;
glProgramLocalParameter4dARBPROC glProgramLocalParameter4dARB = NULL;
glProgramLocalParameter4dvARBPROC glProgramLocalParameter4dvARB = NULL;
glProgramLocalParameter4fARBPROC glProgramLocalParameter4fARB = NULL;
glProgramLocalParameter4fvARBPROC glProgramLocalParameter4fvARB = NULL;
glGetProgramEnvParameterdvARBPROC glGetProgramEnvParameterdvARB = NULL;
glGetProgramEnvParameterfvARBPROC glGetProgramEnvParameterfvARB = NULL;
glGetProgramLocalParameterdvARBPROC glGetProgramLocalParameterdvARB = NULL;
glGetProgramLocalParameterfvARBPROC glGetProgramLocalParameterfvARB = NULL;
glGetProgramivARBPROC glGetProgramivARB = NULL;
glGetProgramStringARBPROC glGetProgramStringARB = NULL;
glGetVertexAttribdvARBPROC glGetVertexAttribdvARB = NULL;
glGetVertexAttribfvARBPROC glGetVertexAttribfvARB = NULL;
glGetVertexAttribivARBPROC glGetVertexAttribivARB = NULL;
glGetVertexAttribPointervARBPROC glGetVertexAttribPointervARB = NULL;
glIsProgramARBPROC glIsProgramARB = NULL;


/* misc  */

static int error = 0;

struct ExtensionTypes SupportedExtensions;

/* getProcAddress */

void *lglGetProcAddress(char *name)
{
#ifdef _WIN32
    void *t = wglGetProcAddress(name);
/*    char err[1000];*/
    if (t == NULL)
    {
        /*sprintf(err, "wglGetProcAddress on %s failed", name);*/
        error = 1;  /*      MessageBox(0, err, "Error", MB_OK | MB_ICONHAND);*/
    }
    return t;
#else
    void *t = glXGetProcAddressARB((byte*)name);
    if (t == NULL)
    {
        error = 1:
    }
    return t;
#endif
}

/*-----------------------------------------------------*/
/* WGL stuff */
/*-----------------------------------------------------*/

#ifdef _WIN32

static HDC _dc = 0;

#endif /* WIN32 */

#ifdef _WIN32

/* set the DC you will be working with */

/** returns true if the extention is available */
int QueryWGLExtension(const char *name)
{
    const GLubyte *extensions = NULL;
    const GLubyte *start;
    GLubyte *where, *terminator;

    /* Extension names should not have spaces. */
    where = (GLubyte *) strchr(name, ' ');
    if (where || *name == '\0')
        return 0;
    if ((wglGetExtensionsStringARB == NULL) || (_dc == 0))
        if (wglGetExtensionsStringEXT == NULL)
            return 0;
        else
            extensions = wglGetExtensionsStringEXT();
    else
        extensions = wglGetExtensionsStringARB(_dc);
    /* It takes a bit of care to be fool-proof about parsing the
         OpenGL extensions string. Don't be fooled by sub-strings,
        etc. */
    start = extensions;
    for (;;) 
    {
        where = (GLubyte *) strstr((const char *) start, name);
        if (!where)
            break;
        terminator = where + strlen(name);
        if (where == start || *(where - 1) == ' ')
            if (*terminator == ' ' || *terminator == '\0')
                return 1;
        start = terminator;
    }
    return 0;
}

void InitWGLARBBufferRegion()
{
    if (!SupportedExtensions.wgl.ARB_buffer_region)
        return;
    wglCreateBufferRegionARB = (wglCreateBufferRegionARBPROC) lglGetProcAddress("wglCreateBufferRegionARB");
    wglDeleteBufferRegionARB = (wglDeleteBufferRegionARBPROC) lglGetProcAddress("wglDeleteBufferRegionARB");
    wglSaveBufferRegionARB = (wglSaveBufferRegionARBPROC) lglGetProcAddress("wglSaveBufferRegionARB");
    wglRestoreBufferRegionARB = (wglRestoreBufferRegionARBPROC) lglGetProcAddress("wglRestoreBufferRegionARB");

}

void InitWGLARBPbuffer()
{
    if (!SupportedExtensions.wgl.ARB_pbuffer)
        return;
    wglCreatePbufferARB = (wglCreatePbufferARBPROC) lglGetProcAddress("wglCreatePbufferARB");
    wglGetPbufferDCARB = (wglGetPbufferDCARBPROC) lglGetProcAddress("wglGetPbufferDCARB");
    wglReleasePbufferDCARB = (wglReleasePbufferDCARBPROC) lglGetProcAddress("wglReleasePbufferDCARB");
    wglDestroyPbufferARB = (wglDestroyPbufferARBPROC) lglGetProcAddress("wglDestroyPbufferARB");
    wglQueryPbufferARB = (wglQueryPbufferARBPROC) lglGetProcAddress("wglQueryPbufferARB");
}

void InitWGLARBPixelFormat()
{
    if (!SupportedExtensions.wgl.ARB_pixel_format)
        return;
    wglGetPixelFormatAttribivARB = (wglGetPixelFormatAttribivARBPROC) lglGetProcAddress("wglGetPixelFormatAttribivARB");
    wglGetPixelFormatAttribfvARB = (wglGetPixelFormatAttribfvARBPROC) lglGetProcAddress("wglGetPixelFormatAttribfvARB");
    wglChoosePixelFormatARB = (wglChoosePixelFormatARBPROC) lglGetProcAddress("wglChoosePixelFormatARB");
}

void InitWGLARBRenderTexture()
{
    if (!SupportedExtensions.wgl.ARB_render_texture)
        return;
    wglBindTexImageARB = (wglBindTexImageARBPROC) lglGetProcAddress("wglBindTexImageARB");
    wglReleaseTexImageARB = (wglReleaseTexImageARBPROC) lglGetProcAddress("wglReleaseTexImageARB");
    wglSetPbufferAttribARB = (wglSetPbufferAttribARBPROC) lglGetProcAddress("wglSetPbufferAttribARB");
}

void InitWGLEXTSwapControl()
{
    if (!SupportedExtensions.wgl.EXT_swap_control)
        return;
    wglSwapIntervalEXT = (wglSwapIntervalEXTPROC) lglGetProcAddress("wglSwapIntervalEXT");
    wglGetSwapIntervalEXT = (wglGetSwapIntervalEXTPROC) lglGetProcAddress("wglGetSwapIntervalEXT");
}

void InitWGLARBMakeCurrentRead()
{
    if (!SupportedExtensions.wgl.ARB_make_current_read)
        return;
    wglMakeContextCurrentARB = (wglMakeContextCurrentARBPROC) lglGetProcAddress("wglMakeContextCurrentARB");
    wglGetCurrentReadDCARB = (wglGetCurrentReadDCARBPROC) lglGetProcAddress("wglGetCurrentReadDCARB");
}

void InitSupportedWGLExtensions()
{
    SupportedExtensions.wgl.ARB_buffer_region = QueryWGLExtension("WGL_ARB_buffer_region");
    SupportedExtensions.wgl.ARB_pbuffer = QueryWGLExtension("WGL_ARB_pbuffer");
    SupportedExtensions.wgl.ARB_pixel_format = QueryWGLExtension("WGL_ARB_pixel_format");
    SupportedExtensions.wgl.ARB_render_texture = QueryWGLExtension("WGL_ARB_render_texture");
    SupportedExtensions.wgl.EXT_swap_control = QueryWGLExtension("WGL_EXT_swap_control");
    SupportedExtensions.wgl.ARB_make_current_read = QueryWGLExtension("WGL_ARB_make_current_read");
    SupportedExtensions.wgl.ARB_multisample = QueryWGLExtension("WGL_ARB_multisample");
}

void _wglSetDC(HDC dc)
{
    _dc = dc;
}

int wglInitialize(HDC dc)
{
    _dc = dc;    
    error = 0;
    wglGetExtensionsStringARB = (wglGetExtensionsStringARBPROC) lglGetProcAddress("wglGetExtensionsStringARB");
    wglGetExtensionsStringEXT = (wglGetExtensionsStringEXTPROC) lglGetProcAddress("wglGetExtensionsStringEXT");
    SupportedExtensions.wgl.ARB_extensions_string = wglGetExtensionsStringARB != NULL;
    SupportedExtensions.wgl.EXT_extensions_string = wglGetExtensionsStringEXT != NULL;
    error = 0;

    InitSupportedWGLExtensions();
   

    InitWGLARBMakeCurrentRead();
    InitWGLEXTSwapControl();
    InitWGLARBRenderTexture();
    InitWGLARBPixelFormat();
    InitWGLARBPbuffer();
    InitWGLARBBufferRegion();
    
    return error;
}

#endif /* WIN32 */

/*-----------------------------------------------------*/
/* WGL stuff END*/
/*-----------------------------------------------------*/

/** returns true if the extention is available */
int QueryExtension(const char *name)
{
    const GLubyte *extensions = NULL;
    const GLubyte *start;
    GLubyte *where, *terminator;

    /* Extension names should not have spaces. */
    where = (GLubyte *) strchr(name, ' ');
    if (where || *name == '\0')
        return 0;
    extensions = glGetString(GL_EXTENSIONS);
    /* It takes a bit of care to be fool-proof about parsing the
         OpenGL extensions string. Don't be fooled by sub-strings,
        etc. */
    start = extensions;
    for (;;) 
    {
        where = (GLubyte *) strstr((const char *) start, name);
        if (!where)
            break;
        terminator = where + strlen(name);
        if (where == start || *(where - 1) == ' ')
            if (*terminator == ' ' || *terminator == '\0')
                return 1;
        start = terminator;
    }
    return 0;
    
/*    char *s = (char*)glGetString(GL_EXTENSIONS);
    char *temp = strstr(s, name);
    return temp!=NULL;*/
}
 
void InitARBVertexProgram()
{
    if (!SupportedExtensions.ARB_vertex_program)
        return;
    glVertexAttrib1sARB = (glVertexAttrib1sARBPROC) lglGetProcAddress("glVertexAttrib1sARB");
    glVertexAttrib1fARB = (glVertexAttrib1fARBPROC) lglGetProcAddress("glVertexAttrib1fARB");
    glVertexAttrib1dARB = (glVertexAttrib1dARBPROC) lglGetProcAddress("glVertexAttrib1dARB");
    glVertexAttrib2sARB = (glVertexAttrib2sARBPROC) lglGetProcAddress("glVertexAttrib2sARB");
    glVertexAttrib2fARB = (glVertexAttrib2fARBPROC) lglGetProcAddress("glVertexAttrib2fARB");
    glVertexAttrib2dARB = (glVertexAttrib2dARBPROC) lglGetProcAddress("glVertexAttrib2dARB");
    glVertexAttrib3sARB = (glVertexAttrib3sARBPROC) lglGetProcAddress("glVertexAttrib3sARB");
    glVertexAttrib3fARB = (glVertexAttrib3fARBPROC) lglGetProcAddress("glVertexAttrib3fARB");
    glVertexAttrib3dARB = (glVertexAttrib3dARBPROC) lglGetProcAddress("glVertexAttrib3dARB");
    glVertexAttrib4sARB = (glVertexAttrib4sARBPROC) lglGetProcAddress("glVertexAttrib4sARB");
    glVertexAttrib4fARB = (glVertexAttrib4fARBPROC) lglGetProcAddress("glVertexAttrib4fARB");
    glVertexAttrib4dARB = (glVertexAttrib4dARBPROC) lglGetProcAddress("glVertexAttrib4dARB");
    glVertexAttrib4NubARB = (glVertexAttrib4NubARBPROC) lglGetProcAddress("glVertexAttrib4NubARB");
    glVertexAttrib1svARB = (glVertexAttrib1svARBPROC) lglGetProcAddress("glVertexAttrib1svARB");
    glVertexAttrib1fvARB = (glVertexAttrib1fvARBPROC) lglGetProcAddress("glVertexAttrib1fvARB");
    glVertexAttrib1dvARB = (glVertexAttrib1dvARBPROC) lglGetProcAddress("glVertexAttrib1dvARB");
    glVertexAttrib2svARB = (glVertexAttrib2svARBPROC) lglGetProcAddress("glVertexAttrib2svARB");
    glVertexAttrib2fvARB = (glVertexAttrib2fvARBPROC) lglGetProcAddress("glVertexAttrib2fvARB");
    glVertexAttrib2dvARB = (glVertexAttrib2dvARBPROC) lglGetProcAddress("glVertexAttrib2dvARB");
    glVertexAttrib3svARB = (glVertexAttrib3svARBPROC) lglGetProcAddress("glVertexAttrib3svARB");
    glVertexAttrib3fvARB = (glVertexAttrib3fvARBPROC) lglGetProcAddress("glVertexAttrib3fvARB");
    glVertexAttrib3dvARB = (glVertexAttrib3dvARBPROC) lglGetProcAddress("glVertexAttrib3dvARB");
    glVertexAttrib4bvARB = (glVertexAttrib4bvARBPROC) lglGetProcAddress("glVertexAttrib4bvARB");
    glVertexAttrib4svARB = (glVertexAttrib4svARBPROC) lglGetProcAddress("glVertexAttrib4svARB");
    glVertexAttrib4ivARB = (glVertexAttrib4ivARBPROC) lglGetProcAddress("glVertexAttrib4ivARB");
    glVertexAttrib4ubvARB = (glVertexAttrib4ubvARBPROC) lglGetProcAddress("glVertexAttrib4ubvARB");
    glVertexAttrib4usvARB = (glVertexAttrib4usvARBPROC) lglGetProcAddress("glVertexAttrib4usvARB");
    glVertexAttrib4uivARB = (glVertexAttrib4uivARBPROC) lglGetProcAddress("glVertexAttrib4uivARB");
    glVertexAttrib4fvARB = (glVertexAttrib4fvARBPROC) lglGetProcAddress("glVertexAttrib4fvARB");
    glVertexAttrib4dvARB = (glVertexAttrib4dvARBPROC) lglGetProcAddress("glVertexAttrib4dvARB");
    glVertexAttrib4NbvARB = (glVertexAttrib4NbvARBPROC) lglGetProcAddress("glVertexAttrib4NbvARB");
    glVertexAttrib4NsvARB = (glVertexAttrib4NsvARBPROC) lglGetProcAddress("glVertexAttrib4NsvARB");
    glVertexAttrib4NivARB = (glVertexAttrib4NivARBPROC) lglGetProcAddress("glVertexAttrib4NivARB");
    glVertexAttrib4NubvARB = (glVertexAttrib4NubvARBPROC) lglGetProcAddress("glVertexAttrib4NubvARB");
    glVertexAttrib4NusvARB = (glVertexAttrib4NusvARBPROC) lglGetProcAddress("glVertexAttrib4NusvARB");
    glVertexAttrib4NuivARB = (glVertexAttrib4NuivARBPROC) lglGetProcAddress("glVertexAttrib4NuivARB");
    glVertexAttribPointerARB = (glVertexAttribPointerARBPROC) lglGetProcAddress("glVertexAttribPointerARB");
    glEnableVertexAttribArrayARB = (glEnableVertexAttribArrayARBPROC) lglGetProcAddress("glEnableVertexAttribArrayARB");
    glDisableVertexAttribArrayARB = (glDisableVertexAttribArrayARBPROC) lglGetProcAddress("glDisableVertexAttribArrayARB");
    glProgramStringARB = (glProgramStringARBPROC) lglGetProcAddress("glProgramStringARB");
    glBindProgramARB = (glBindProgramARBPROC) lglGetProcAddress("glBindProgramARB");
    glDeleteProgramsARB = (glDeleteProgramsARBPROC) lglGetProcAddress("glDeleteProgramsARB");
    glGenProgramsARB = (glGenProgramsARBPROC) lglGetProcAddress("glGenProgramsARB");
    glProgramEnvParameter4dARB = (glProgramEnvParameter4dARBPROC) lglGetProcAddress("glProgramEnvParameter4dARB");
    glProgramEnvParameter4dvARB = (glProgramEnvParameter4dvARBPROC) lglGetProcAddress("glProgramEnvParameter4dvARB");
    glProgramEnvParameter4fARB = (glProgramEnvParameter4fARBPROC) lglGetProcAddress("glProgramEnvParameter4fARB");
    glProgramEnvParameter4fvARB = (glProgramEnvParameter4fvARBPROC) lglGetProcAddress("glProgramEnvParameter4fvARB");
    glProgramLocalParameter4dARB = (glProgramLocalParameter4dARBPROC) lglGetProcAddress("glProgramLocalParameter4dARB");
    glProgramLocalParameter4dvARB = (glProgramLocalParameter4dvARBPROC) lglGetProcAddress("glProgramLocalParameter4dvARB");
    glProgramLocalParameter4fARB = (glProgramLocalParameter4fARBPROC) lglGetProcAddress("glProgramLocalParameter4fARB");
    glProgramLocalParameter4fvARB = (glProgramLocalParameter4fvARBPROC) lglGetProcAddress("glProgramLocalParameter4fvARB");
    glGetProgramEnvParameterdvARB = (glGetProgramEnvParameterdvARBPROC) lglGetProcAddress("glGetProgramEnvParameterdvARB");
    glGetProgramEnvParameterfvARB = (glGetProgramEnvParameterfvARBPROC) lglGetProcAddress("glGetProgramEnvParameterfvARB");
    glGetProgramLocalParameterdvARB = (glGetProgramLocalParameterdvARBPROC) lglGetProcAddress("glGetProgramLocalParameterdvARB");
    glGetProgramLocalParameterfvARB = (glGetProgramLocalParameterfvARBPROC) lglGetProcAddress("glGetProgramLocalParameterfvARB");
    glGetProgramivARB = (glGetProgramivARBPROC) lglGetProcAddress("glGetProgramivARB");
    glGetProgramStringARB = (glGetProgramStringARBPROC) lglGetProcAddress("glGetProgramStringARB");
    glGetVertexAttribdvARB = (glGetVertexAttribdvARBPROC) lglGetProcAddress("glGetVertexAttribdvARB");
    glGetVertexAttribfvARB = (glGetVertexAttribfvARBPROC) lglGetProcAddress("glGetVertexAttribfvARB");
    glGetVertexAttribivARB = (glGetVertexAttribivARBPROC) lglGetProcAddress("glGetVertexAttribivARB");
    glGetVertexAttribPointervARB = (glGetVertexAttribPointervARBPROC) lglGetProcAddress("glGetVertexAttribPointervARB");
    glIsProgramARB = (glIsProgramARBPROC) lglGetProcAddress("glIsProgramARB");
   
}

void InitEXTStencilTwoSide()
{
    if (!SupportedExtensions.EXT_stencil_two_side)
        return;
    glActiveStencilFaceEXT = (glActiveStencilFaceEXTPROC) lglGetProcAddress("glActiveStencilFaceEXT");
}

void InitARBWindowPos()
{
    if (!SupportedExtensions.ARB_window_pos)
        return;
    glWindowPos2dARB = (glWindowPos2dARBPROC) lglGetProcAddress("glWindowPos2dARB");
    glWindowPos2fARB = (glWindowPos2fARBPROC) lglGetProcAddress("glWindowPos2fARB");
    glWindowPos2iARB = (glWindowPos2iARBPROC) lglGetProcAddress("glWindowPos2iARB");
    glWindowPos2sARB = (glWindowPos2sARBPROC) lglGetProcAddress("glWindowPos2sARB");
    glWindowPos2dvARB = (glWindowPos2dvARBPROC) lglGetProcAddress("glWindowPos2dvARB");
    glWindowPos2fvARB = (glWindowPos2fvARBPROC) lglGetProcAddress("glWindowPos2fvARB");
    glWindowPos2ivARB = (glWindowPos2ivARBPROC) lglGetProcAddress("glWindowPos2ivARB");
    glWindowPos2svARB = (glWindowPos2svARBPROC) lglGetProcAddress("glWindowPos2svARB");
    glWindowPos3dARB = (glWindowPos3dARBPROC) lglGetProcAddress("glWindowPos3dARB");
    glWindowPos3fARB = (glWindowPos3fARBPROC) lglGetProcAddress("glWindowPos3fARB");
    glWindowPos3iARB = (glWindowPos3iARBPROC) lglGetProcAddress("glWindowPos3iARB");
    glWindowPos3sARB = (glWindowPos3sARBPROC) lglGetProcAddress("glWindowPos3sARB");
    glWindowPos3dvARB = (glWindowPos3dvARBPROC) lglGetProcAddress("glWindowPos3dvARB");
    glWindowPos3fvARB = (glWindowPos3fvARBPROC) lglGetProcAddress("glWindowPos3fvARB");
    glWindowPos3ivARB = (glWindowPos3ivARBPROC) lglGetProcAddress("glWindowPos3ivARB");
    glWindowPos3svARB = (glWindowPos3svARBPROC) lglGetProcAddress("glWindowPos3svARB");
}

void InitARBTextureCompression()
{
    if (!SupportedExtensions.ARB_texture_compression)
        return;
    glCompressedTexImage3DARB = (glCompressedTexImage3DPROC) lglGetProcAddress("glCompressedTexImage3DARB");
    glCompressedTexImage2DARB = (glCompressedTexImage2DPROC) lglGetProcAddress("glCompressedTexImage2DARB");
    glCompressedTexImage1DARB = (glCompressedTexImage1DPROC) lglGetProcAddress("glCompressedTexImage1DARB");
    glCompressedTexSubImage3DARB = (glCompressedTexSubImage3DPROC) lglGetProcAddress("glCompressedTexSubImage3DARB");
    glCompressedTexSubImage2DARB = (glCompressedTexSubImage2DPROC) lglGetProcAddress("glCompressedTexSubImage2DARB");
    glCompressedTexSubImage1DARB = (glCompressedTexSubImage1DPROC) lglGetProcAddress("glCompressedTexSubImage1DARB");
    glGetCompressedTexImageARB = (glGetCompressedTexImagePROC) lglGetProcAddress("glGetCompressedTexImageARB");
}

void InitNVPointSprite()
{
    if (!SupportedExtensions.NV_point_sprite)
        return;
    glPointParameteriNV = (glPointParameteriNVPROC) lglGetProcAddress("glPointParameteriNV");
    glPointParameterivNV = (glPointParameterivNVPROC) lglGetProcAddress("glPointParameterivNV");
}

void InitNVOcclusionQuery()
{
    if (!SupportedExtensions.NV_occlusion_query)
        return;
    glGenOcclusionQueriesNV = (glGenOcclusionQueriesNVPROC) lglGetProcAddress("glGenOcclusionQueriesNV");
    glDeleteOcclusionQueriesNV = (glDeleteOcclusionQueriesNVPROC) lglGetProcAddress("glDeleteOcclusionQueriesNV");
    glIsOcclusionQueryNV = (glIsOcclusionQueryNVPROC) lglGetProcAddress("glIsOcclusionQueryNV");
    glBeginOcclusionQueryNV = (glBeginOcclusionQueryNVPROC) lglGetProcAddress("glBeginOcclusionQueryNV");
    glEndOcclusionQueryNV = (glEndOcclusionQueryNVPROC) lglGetProcAddress("glEndOcclusionQueryNV");
    glGetOcclusionQueryivNV = (glGetOcclusionQueryivNVPROC) lglGetProcAddress("glGetOcclusionQueryivNV");
    glGetOcclusionQueryuivNV = (glGetOcclusionQueryuivNVPROC) lglGetProcAddress("glGetOcclusionQueryuivNV");
}

void InitATIVertexArrayObject()
{
    if (!SupportedExtensions.ATI_vertex_array_object)
        return;
    glNewObjectBufferATI = (glNewObjectBufferATIPROC) lglGetProcAddress("glNewObjectBufferATI");
    glIsObjectBufferATI = (glIsObjectBufferATIPROC) lglGetProcAddress("glIsObjectBufferATI");
    glUpdateObjectBufferATI = (glUpdateObjectBufferATIPROC) lglGetProcAddress("glUpdateObjectBufferATI");
    glGetObjectBufferfvATI = (glGetObjectBufferfvATIPROC) lglGetProcAddress("glGetObjectBufferfvATI");
    glGetObjectBufferivATI = (glGetObjectBufferivATIPROC) lglGetProcAddress("glGetObjectBufferivATI");
    glFreeObjectBufferATI = (glFreeObjectBufferATIPROC) lglGetProcAddress("glFreeObjectBufferATI");
    glArrayObjectATI = (glArrayObjectATIPROC) lglGetProcAddress("glArrayObjectATI");
    glGetArrayObjectfvATI = (glGetArrayObjectfvATIPROC) lglGetProcAddress("glGetArrayObjectfvATI");
    glGetArrayObjectivATI = (glGetArrayObjectivATIPROC) lglGetProcAddress("glGetArrayObjectivATI");
    glVariantArrayObjectATI = (glVariantArrayObjectATIPROC) lglGetProcAddress("glVariantArrayObjectATI");
    glGetVariantArrayObjectfvATI = (glGetVariantArrayObjectfvATIPROC) lglGetProcAddress("glGetVariantArrayObjectfvATI");
    glGetVariantArrayObjectivATI = (glGetVariantArrayObjectivATIPROC) lglGetProcAddress("glGetVariantArrayObjectivATI");
}

void InitATIVertexStreams()
{
    if (!SupportedExtensions.ATI_vertex_streams)
        return;
    glClientActiveVertexStreamATI = (glClientActiveVertexStreamATIPROC) lglGetProcAddress("glClientActiveVertexStreamATI");
    glVertexBlendEnviATI = (glVertexBlendEnviATIPROC) lglGetProcAddress("glVertexBlendEnviATI");
    glVertexBlendEnvfATI = (glVertexBlendEnvfATIPROC) lglGetProcAddress("glVertexBlendEnvfATI");
    glVertexStream2sATI = (glVertexStream2sATIPROC) lglGetProcAddress("glVertexStream2sATI");
    glVertexStream2svATI = (glVertexStream2svATIPROC) lglGetProcAddress("glVertexStream2svATI");
    glVertexStream2iATI = (glVertexStream2iATIPROC) lglGetProcAddress("glVertexStream2iATI");
    glVertexStream2ivATI = (glVertexStream2ivATIPROC) lglGetProcAddress("glVertexStream2ivATI");
    glVertexStream2fATI = (glVertexStream2fATIPROC) lglGetProcAddress("glVertexStream2fATI");
    glVertexStream2fvATI = (glVertexStream2fvATIPROC) lglGetProcAddress("glVertexStream2fvATI");
    glVertexStream2dATI = (glVertexStream2dATIPROC) lglGetProcAddress("glVertexStream2dATI");
    glVertexStream2dvATI = (glVertexStream2dvATIPROC) lglGetProcAddress("glVertexStream2dvATI");
    glVertexStream3sATI = (glVertexStream3sATIPROC) lglGetProcAddress("glVertexStream3sATI");
    glVertexStream3svATI = (glVertexStream3svATIPROC) lglGetProcAddress("glVertexStream3svATI");
    glVertexStream3iATI = (glVertexStream3iATIPROC) lglGetProcAddress("glVertexStream3iATI");
    glVertexStream3ivATI = (glVertexStream3ivATIPROC) lglGetProcAddress("glVertexStream3ivATI");
    glVertexStream3fATI = (glVertexStream3fATIPROC) lglGetProcAddress("glVertexStream3fATI");
    glVertexStream3fvATI = (glVertexStream3fvATIPROC) lglGetProcAddress("glVertexStream3fvATI");
    glVertexStream3dATI = (glVertexStream3dATIPROC) lglGetProcAddress("glVertexStream3dATI");
    glVertexStream3dvATI = (glVertexStream3dvATIPROC) lglGetProcAddress("glVertexStream3dvATI");
    glVertexStream4sATI = (glVertexStream4sATIPROC) lglGetProcAddress("glVertexStream4sATI");
    glVertexStream4svATI = (glVertexStream4svATIPROC) lglGetProcAddress("glVertexStream4svATI");
    glVertexStream4iATI = (glVertexStream4iATIPROC) lglGetProcAddress("glVertexStream4iATI");
    glVertexStream4ivATI = (glVertexStream4ivATIPROC) lglGetProcAddress("glVertexStream4ivATI");
    glVertexStream4fATI = (glVertexStream4fATIPROC) lglGetProcAddress("glVertexStream4fATI");
    glVertexStream4fvATI = (glVertexStream4fvATIPROC) lglGetProcAddress("glVertexStream4fvATI");
    glVertexStream4dATI = (glVertexStream4dATIPROC) lglGetProcAddress("glVertexStream4dATI");
    glVertexStream4dvATI = (glVertexStream4dvATIPROC) lglGetProcAddress("glVertexStream4dvATI");
    glNormalStream3bATI = (glNormalStream3bATIPROC) lglGetProcAddress("glNormalStream3bATI");
    glNormalStream3bvATI = (glNormalStream3bvATIPROC) lglGetProcAddress("glNormalStream3bvATI");
    glNormalStream3sATI = (glNormalStream3sATIPROC) lglGetProcAddress("glNormalStream3sATI");
    glNormalStream3svATI = (glNormalStream3svATIPROC) lglGetProcAddress("glNormalStream3svATI");
    glNormalStream3iATI = (glNormalStream3iATIPROC) lglGetProcAddress("glNormalStream3iATI");
    glNormalStream3ivATI = (glNormalStream3ivATIPROC) lglGetProcAddress("glNormalStream3ivATI");
    glNormalStream3fATI = (glNormalStream3fATIPROC) lglGetProcAddress("glNormalStream3fATI");
    glNormalStream3fvATI = (glNormalStream3fvATIPROC) lglGetProcAddress("glNormalStream3fvATI");
    glNormalStream3dATI = (glNormalStream3dATIPROC) lglGetProcAddress("glNormalStream3dATI");
    glNormalStream3dvATI = (glNormalStream3dvATIPROC) lglGetProcAddress("glNormalStream3dvATI");
}

void InitATIElementArray()
{
    if (!SupportedExtensions.ATI_element_array)
        return;
    glElementPointerATI = (glElementPointerATIPROC) lglGetProcAddress("glElementPointerATI");
    glDrawElementArrayATI = (glDrawElementArrayATIPROC) lglGetProcAddress("glDrawElementArrayATI");
    glDrawRangeElementArrayATI = (glDrawRangeElementArrayATIPROC) lglGetProcAddress("glDrawRangeElementArrayATI");
}

void InitATIFragmentShader()
{
    if (!SupportedExtensions.ATI_fragment_shader)
        return;
    glGenFragmentShadersATI = (glGenFragmentShadersATIPROC) lglGetProcAddress("glGenFragmentShadersATI");
    glBindFragmentShaderATI = (glBindFragmentShaderATIPROC) lglGetProcAddress("glBindFragmentShaderATI");
    glDeleteFragmentShaderATI = (glDeleteFragmentShaderATIPROC) lglGetProcAddress("glDeleteFragmentShaderATI");
    glBeginFragmentShaderATI = (glBeginFragmentShaderATIPROC) lglGetProcAddress("glBeginFragmentShaderATI");
    glEndFragmentShaderATI = (glEndFragmentShaderATIPROC) lglGetProcAddress("glEndFragmentShaderATI");
    glPassTexCoordATI = (glPassTexCoordATIPROC) lglGetProcAddress("glPassTexCoordATI");
    glSampleMapATI = (glSampleMapATIPROC) lglGetProcAddress("glSampleMapATI");
    glColorFragmentOp1ATI = (glColorFragmentOp1ATIPROC) lglGetProcAddress("glColorFragmentOp1ATI");
    glColorFragmentOp2ATI = (glColorFragmentOp2ATIPROC) lglGetProcAddress("glColorFragmentOp2ATI");
    glColorFragmentOp3ATI = (glColorFragmentOp3ATIPROC) lglGetProcAddress("glColorFragmentOp3ATI");
    glAlphaFragmentOp1ATI = (glAlphaFragmentOp1ATIPROC) lglGetProcAddress("glAlphaFragmentOp1ATI");
    glAlphaFragmentOp2ATI = (glAlphaFragmentOp2ATIPROC) lglGetProcAddress("glAlphaFragmentOp2ATI");
    glAlphaFragmentOp3ATI = (glAlphaFragmentOp3ATIPROC) lglGetProcAddress("glAlphaFragmentOp3ATI");
    glSetFragmentShaderConstantATI = (glSetFragmentShaderConstantATIPROC) lglGetProcAddress("glSetFragmentShaderConstantATI");
}

void InitATIEnvmapBumpmap()
{
    if (!SupportedExtensions.ATI_envmap_bumpmap)
        return;
    glTexBumpParameterivATI = (glTexBumpParameterivATIPROC) lglGetProcAddress("glTexBumpParameterivATI");
    glTexBumpParameterfvATI = (glTexBumpParameterfvATIPROC) lglGetProcAddress("glTexBumpParameterfvATI");
    glGetTexBumpParameterivATI = (glGetTexBumpParameterivATIPROC) lglGetProcAddress("glGetTexBumpParameterivATI");
    glGetTexBumpParameterfvATI = (glGetTexBumpParameterfvATIPROC) lglGetProcAddress("glGetTexBumpParameterfvATI");
}

void InitEXTVertexShader()
{
    if (!SupportedExtensions.EXT_vertex_shader)
        return;
    glBeginVertexShaderEXT = (glBeginVertexShaderEXTPROC) lglGetProcAddress("glBeginVertexShaderEXT");
    glEndVertexShaderEXT = (glEndVertexShaderEXTPROC) lglGetProcAddress("glEndVertexShaderEXT");
    glBindVertexShaderEXT = (glBindVertexShaderEXTPROC) lglGetProcAddress("glBindVertexShaderEXT");
    glGenVertexShadersEXT = (glGenVertexShadersEXTPROC) lglGetProcAddress("glGenVertexShadersEXT");
    glDeleteVertexShaderEXT = (glDeleteVertexShaderEXTPROC) lglGetProcAddress("glDeleteVertexShaderEXT");
    glShaderOp1EXT = (glShaderOp1EXTPROC) lglGetProcAddress("glShaderOp1EXT");
    glShaderOp2EXT = (glShaderOp2EXTPROC) lglGetProcAddress("glShaderOp2EXT");
    glShaderOp3EXT = (glShaderOp3EXTPROC) lglGetProcAddress("glShaderOp3EXT");
    glSwizzleEXT = (glSwizzleEXTPROC) lglGetProcAddress("glSwizzleEXT");
    glWriteMaskEXT = (glWriteMaskEXTPROC) lglGetProcAddress("glWriteMaskEXT");
    glInsertComponentEXT = (glInsertComponentEXTPROC) lglGetProcAddress("glInsertComponentEXT");
    glExtractComponentEXT = (glExtractComponentEXTPROC) lglGetProcAddress("glExtractComponentEXT");
    glGenSymbolsEXT = (glGenSymbolsEXTPROC) lglGetProcAddress("glGenSymbolsEXT");
    glSetInvariantEXT = (glSetInvariantEXTPROC) lglGetProcAddress("glSetInvarianceEXT");
    glSetLocalConstantEXT = (glSetLocalConstantEXTPROC) lglGetProcAddress("glSetLocalConstantEXT");
    glVariantbvEXT = (glVariantbvEXTPROC) lglGetProcAddress("glVariantbvEXT");
    glVariantsvEXT = (glVariantsvEXTPROC) lglGetProcAddress("glVariantsvEXT");
    glVariantivEXT = (glVariantivEXTPROC) lglGetProcAddress("glVariantivEXT");
    glVariantfvEXT = (glVariantfvEXTPROC) lglGetProcAddress("glVariantfvEXT");
    glVariantdvEXT = (glVariantdvEXTPROC) lglGetProcAddress("glVariantdvEXT");
    glVariantubvEXT = (glVariantubvEXTPROC) lglGetProcAddress("glVariantubvEXT");
    glVariantusvEXT = (glVariantusvEXTPROC) lglGetProcAddress("glVariantusvEXT");
    glVariantuivEXT = (glVariantuivEXTPROC) lglGetProcAddress("glVariantuivEXT");
    glVariantPointerEXT = (glVariantPointerEXTPROC) lglGetProcAddress("glVariantPointerEXT");
    glEnableVariantClientStateEXT = (glEnableVariantClientStateEXTPROC) lglGetProcAddress("glEnableVariantClientStateEXT");
    glDisableVariantClientStateEXT = (glDisableVariantClientStateEXTPROC) lglGetProcAddress("glDisableVariantClientStateEXT");
    glBindLightParameterEXT = (glBindLightParameterEXTPROC) lglGetProcAddress("glBindLightParameterEXT");
    glBindMaterialParameterEXT = (glBindMaterialParameterEXTPROC) lglGetProcAddress("glBindMaterialParameterEXT");
    glBindTexGenParameterEXT = (glBindTexGenParameterEXTPROC) lglGetProcAddress("glBindTexGenParameterEXT");
    glBindTextureUnitParameterEXT = (glBindTextureUnitParameterEXTPROC) lglGetProcAddress("glBindTextureUnitParameterEXT");
    glBindParameterEXT = (glBindParameterEXTPROC) lglGetProcAddress("glBindParameterEXT");
    glIsVariantEnabledEXT = (glIsVariantEnabledEXTPROC) lglGetProcAddress("glIsVariantEnabledEXT");
    glGetVariantBooleanvEXT = (glGetVariantBooleanvEXTPROC) lglGetProcAddress("glGetVariantBooleanvEXT");
    glGetVariantIntegervEXT = (glGetVariantIntegervEXTPROC) lglGetProcAddress("glGetVariantIntegervEXT");
    glGetVariantFloatvEXT = (glGetVariantFloatvEXTPROC) lglGetProcAddress("glGetVariantFloatvEXT");
    glGetVariantPointervEXT = (glGetVariantPointervEXTPROC) lglGetProcAddress("glGetVariantPointervEXT");
    glGetInvariantBooleanvEXT = (glGetInvariantBooleanvEXTPROC) lglGetProcAddress("glGetInvariantBooleanvEXT");
    glGetInvariantIntegervEXT = (glGetInvariantIntegervEXTPROC) lglGetProcAddress("glGetInvariantIntegervEXT");
    glGetInvariantFloatvEXT = (glGetInvariantFloatvEXTPROC) lglGetProcAddress("glGetInvariantFloatvEXT");
    glGetLocalConstantBooleanvEXT = (glGetLocalConstantBooleanvEXTPROC) lglGetProcAddress("glGetLocalConstantBooleanvEXT");
    glGetLocalConstantIntegervEXT = (glGetLocalConstantIntegervEXTPROC) lglGetProcAddress("glGetLocalConstantIntegervEXT");
    glGetLocalConstantFloatvEXT = (glGetLocalConstantFloatvEXTPROC) lglGetProcAddress("glGetLocalConstantFloatvEXT");
}

void InitARBMatrixPalette()
{
    if (!SupportedExtensions.ARB_matrix_palette)
        return;
    glCurrentPaletteMatrixARB = (glCurrentPaletteMatrixARBPROC) lglGetProcAddress("glCurrentPaletteMatrixARB");
    glMatrixIndexubvARB = (glMatrixIndexubvARBPROC) lglGetProcAddress("glMatrixIndexubvARB");
    glMatrixIndexusvARB = (glMatrixIndexusvARBPROC) lglGetProcAddress("glMatrixIndexusvARB");
    glMatrixIndexuivARB = (glMatrixIndexuivARBPROC) lglGetProcAddress("glMatrixIndexuivARB");
    glMatrixIndexPointerARB = (glMatrixIndexPointerARBPROC) lglGetProcAddress("glMatrixIndexPointerARB");
}

void InitEXTMultiDrawArrays()
{
    if (!SupportedExtensions.EXT_multi_draw_arrays)
        return;
    glMultiDrawArraysEXT = (glMultiDrawArraysEXTPROC) lglGetProcAddress("glMultiDrawArraysEXT");
    glMultiDrawElementsEXT = (glMultiDrawElementsEXTPROC) lglGetProcAddress("glMultiDrawElementsEXT");
}

void InitARBVertexBlend()
{
    if (!SupportedExtensions.ARB_vertex_blend)
        return;
    glWeightbvARB = (glWeightbvARBPROC) lglGetProcAddress("glWeightbvARB");
    glWeightsvARB = (glWeightsvARBPROC) lglGetProcAddress("glWeightsvARB");
    glWeightivARB = (glWeightivARBPROC) lglGetProcAddress("glWeightivARB");
    glWeightfvARB = (glWeightfvARBPROC) lglGetProcAddress("glWeightfvARB");
    glWeightdvARB = (glWeightdvARBPROC) lglGetProcAddress("glWeightdvARB");
    glWeightubvARB = (glWeightubvARBPROC) lglGetProcAddress("glWeightubvARB");
    glWeightusvARB = (glWeightusvARBPROC) lglGetProcAddress("glWeightusvARB");
    glWeightuivARB = (glWeightuivARBPROC) lglGetProcAddress("glWeightuivARB");
    glWeightPointerARB = (glWeightPointerARBPROC) lglGetProcAddress("glWeightPointerARB");
    glVertexBlendARB = (glVertexBlendARBPROC) lglGetProcAddress("glVertexBlendARB");
}

void InitARBPointParameters()
{
    if (!SupportedExtensions.ARB_point_parameters)
        return;
    glPointParameterfARB = (glPointParameterfARBPROC) lglGetProcAddress("glPointParameterfARB");
    glPointParameterfvARB = (glPointParameterfvARBPROC) lglGetProcAddress("glPointParameterfvARB");
}

void InitATIPNTriangles()
{
    if (!SupportedExtensions.ATI_pn_triangles)
        return;
    glPNTrianglesiATI = (glPNTrianglesiATIPROC) lglGetProcAddress("glPNTrianglesiATI");
    glPNTrianglesfATI = (glPNTrianglesfATIPROC) lglGetProcAddress("glPNTrianglesfATI");
}

void InitNVEvaluators()
{
    if (!SupportedExtensions.NV_evaluators)
        return;
    glMapControlPointsNV = (glMapControlPointsNVPROC) lglGetProcAddress("glMapControlPointsNV");
    glMapParameterivNV = (glMapParameterivNVPROC) lglGetProcAddress("glMapParameterivNV");
    glMapParameterfvNV = (glMapParameterfvNVPROC) lglGetProcAddress("glMapParameterfvNV");
    glGetMapControlPointsNV = (glGetMapControlPointsNVPROC) lglGetProcAddress("glGetMapControlPointsNV");
    glGetMapParameterivNV = (glGetMapParameterivNVPROC) lglGetProcAddress("glGetMapParameterivNV");
    glGetMapParameterfvNV = (glGetMapParameterfvNVPROC) lglGetProcAddress("glGetMapParameterfvNV");
    glGetMapAttribParameterivNV = (glGetMapAttribParameterivNVPROC) lglGetProcAddress("glGetMapAttribParameterivNV");
    glGetMapAttribParameterfvNV = (glGetMapAttribParameterfvNVPROC) lglGetProcAddress("glGetMapAttribParameterfvNV");
    glEvalMapsNV = (glEvalMapsNVPROC) lglGetProcAddress("glEvalMapsNV");
}

void InitNVRegisterCombiners2()
{
    if (!SupportedExtensions.NV_register_combiners2)
        return;
    glCombinerStageParameterfvNV = (glCombinerStageParameterfvNVPROC) lglGetProcAddress("glCombinerStageParameterfvNV");
    glGetCombinerStageParameterfvNV = (glGetCombinerStageParameterfvNVPROC) lglGetProcAddress("glGetCombinerStageParameterfvNV");
}

void InitNVFence()
{
    if (!SupportedExtensions.NV_fence)
        return;
    glGenFencesNV = (glGenFencesNVPROC) lglGetProcAddress("glGenFencesNV");
    glDeleteFencesNV = (glDeleteFencesNVPROC) lglGetProcAddress("glDeleteFencesNV");
    glSetFenceNV = (glSetFenceNVPROC) lglGetProcAddress("glSetFenceNV");
    glTestFenceNV = (glTestFenceNVPROC) lglGetProcAddress("glTestFenceNV");
    glFinishFenceNV = (glFinishFenceNVPROC) lglGetProcAddress("glFinishFenceNV");
    glIsFenceNV = (glIsFenceNVPROC) lglGetProcAddress("glIsFenceNV");
    glGetFenceivNV = (glGetFenceivNVPROC) lglGetProcAddress("glGetFenceivNV");
}

void InitNVVertexProgram()
{
    if (!SupportedExtensions.NV_vertex_program)
        return;
    glBindProgramNV = (glBindProgramNVPROC) lglGetProcAddress("glBindProgramNV");
    glDeleteProgramsNV = (glDeleteProgramsNVPROC) lglGetProcAddress("glDeleteProgramsNV");
    glExecuteProgramNV = (glExecuteProgramNVPROC) lglGetProcAddress("glExecuteProgramNV");
    glGenProgramsNV = (glGenProgramsNVPROC) lglGetProcAddress("glGenProgramsNV");
    glAreProgramsResidentNV = (glAreProgramsResidentNVPROC) lglGetProcAddress("glAreProgramsResidentNV");
    glRequestResidentProgramsNV = (glRequestResidentProgramsNVPROC) lglGetProcAddress("glRequestResidentProgramsNV");
    glGetProgramParameterfvNV = (glGetProgramParameterfvNVPROC) lglGetProcAddress("glGetProgramParameterfvNV");
    glGetProgramParameterdvNV = (glGetProgramParameterdvNVPROC) lglGetProcAddress("glGetProgramParameterdvNV");
    glGetProgramivNV = (glGetProgramivNVPROC) lglGetProcAddress("glGetProgramivNV");
    glGetProgramStringNV = (glGetProgramStringNVPROC) lglGetProcAddress("glGetProgramStringNV");
    glGetTrackMatrixivNV = (glGetTrackMatrixivNVPROC) lglGetProcAddress("glGetTrackMatrixivNV");
    glGetVertexAttribdvNV = (glGetVertexAttribdvNVPROC) lglGetProcAddress("glGetVertexAttribdvNV");
    glGetVertexAttribfvNV = (glGetVertexAttribfvNVPROC) lglGetProcAddress("glGetVertexAttribfvNV");
    glGetVertexAttribivNV = (glGetVertexAttribivNVPROC) lglGetProcAddress("glGetVertexAttribivNV");
    glGetVertexAttribPointervNV = (glGetVertexAttribPointervNVPROC) lglGetProcAddress("glGetVertexAttribPointervNV");
    glIsProgramNV = (glIsProgramNVPROC) lglGetProcAddress("glIsProgramNV");
    glLoadProgramNV = (glLoadProgramNVPROC) lglGetProcAddress("glLoadProgramNV");
    glProgramParameter4fNV = (glProgramParameter4fNVPROC) lglGetProcAddress("glProgramParameter4fNV");
    glProgramParameter4dNV = (glProgramParameter4dNVPROC) lglGetProcAddress("glProgramParameter4dNV");
    glProgramParameter4dvNV = (glProgramParameter4dvNVPROC) lglGetProcAddress("glProgramParameter4dvNV");
    glProgramParameter4fvNV = (glProgramParameter4fvNVPROC) lglGetProcAddress("glProgramParameter4fvNV");
    glProgramParameters4dvNV = (glProgramParameters4dvNVPROC) lglGetProcAddress("glProgramParameters4dvNV");
    glProgramParameters4fvNV = (glProgramParameters4fvNVPROC) lglGetProcAddress("glProgramParameters4fvNV");
    glTrackMatrixNV = (glTrackMatrixNVPROC) lglGetProcAddress("glTrackMatrixNV");
    glVertexAttribPointerNV = (glVertexAttribPointerNVPROC) lglGetProcAddress("glVertexAttribPointerNV");
    glVertexAttrib1sNV = (glVertexAttrib1sNVPROC) lglGetProcAddress("glVertexAttrib1sNV");
    glVertexAttrib1fNV = (glVertexAttrib1fNVPROC) lglGetProcAddress("glVertexAttrib1fNV");
    glVertexAttrib1dNV = (glVertexAttrib1dNVPROC) lglGetProcAddress("glVertexAttrib1dNV");
    glVertexAttrib2sNV = (glVertexAttrib2sNVPROC) lglGetProcAddress("glVertexAttrib2sNV");
    glVertexAttrib2fNV = (glVertexAttrib2fNVPROC) lglGetProcAddress("glVertexAttrib2fNV");
    glVertexAttrib2dNV = (glVertexAttrib2dNVPROC) lglGetProcAddress("glVertexAttrib2dNV");
    glVertexAttrib3sNV = (glVertexAttrib3sNVPROC) lglGetProcAddress("glVertexAttrib3sNV");
    glVertexAttrib3fNV = (glVertexAttrib3fNVPROC) lglGetProcAddress("glVertexAttrib3fNV");
    glVertexAttrib3dNV = (glVertexAttrib3dNVPROC) lglGetProcAddress("glVertexAttrib3dNV");
    glVertexAttrib4sNV = (glVertexAttrib4sNVPROC) lglGetProcAddress("glVertexAttrib4sNV");
    glVertexAttrib4fNV = (glVertexAttrib4fNVPROC) lglGetProcAddress("glVertexAttrib4fNV");
    glVertexAttrib4dNV = (glVertexAttrib4dNVPROC) lglGetProcAddress("glVertexAttrib4dNV");
    glVertexAttrib4ubNV = (glVertexAttrib4ubNVPROC) lglGetProcAddress("glVertexAttrib4ubNV");
    glVertexAttrib1svNV = (glVertexAttrib1svNVPROC) lglGetProcAddress("glVertexAttrib1svNV");
    glVertexAttrib1fvNV = (glVertexAttrib1fvNVPROC) lglGetProcAddress("glVertexAttrib1fvNV");
    glVertexAttrib1dvNV = (glVertexAttrib1dvNVPROC) lglGetProcAddress("glVertexAttrib1dvNV");
    glVertexAttrib2svNV = (glVertexAttrib2svNVPROC) lglGetProcAddress("glVertexAttrib2svNV");
    glVertexAttrib2fvNV = (glVertexAttrib2fvNVPROC) lglGetProcAddress("glVertexAttrib2fvNV");
    glVertexAttrib2dvNV = (glVertexAttrib2dvNVPROC) lglGetProcAddress("glVertexAttrib2dvNV");
    glVertexAttrib3svNV = (glVertexAttrib3svNVPROC) lglGetProcAddress("glVertexAttrib3svNV");
    glVertexAttrib3fvNV = (glVertexAttrib3fvNVPROC) lglGetProcAddress("glVertexAttrib3fvNV");
    glVertexAttrib3dvNV = (glVertexAttrib3dvNVPROC) lglGetProcAddress("glVertexAttrib3dvNV");
    glVertexAttrib4svNV = (glVertexAttrib4svNVPROC) lglGetProcAddress("glVertexAttrib4svNV");
    glVertexAttrib4fvNV = (glVertexAttrib4fvNVPROC) lglGetProcAddress("glVertexAttrib4fvNV");
    glVertexAttrib4dvNV = (glVertexAttrib4dvNVPROC) lglGetProcAddress("glVertexAttrib4dvNV");
    glVertexAttrib4ubvNV = (glVertexAttrib4ubvNVPROC) lglGetProcAddress("glVertexAttrib4ubvNV");
    glVertexAttribs1svNV = (glVertexAttribs1svNVPROC) lglGetProcAddress("glVertexAttribs1svNV");
    glVertexAttribs1fvNV = (glVertexAttribs1fvNVPROC) lglGetProcAddress("glVertexAttribs1fvNV");
    glVertexAttribs1dvNV = (glVertexAttribs1dvNVPROC) lglGetProcAddress("glVertexAttribs1dvNV");
    glVertexAttribs2svNV = (glVertexAttribs2svNVPROC) lglGetProcAddress("glVertexAttribs2svNV");
    glVertexAttribs2fvNV = (glVertexAttribs2fvNVPROC) lglGetProcAddress("glVertexAttribs2fvNV");
    glVertexAttribs2dvNV = (glVertexAttribs2dvNVPROC) lglGetProcAddress("glVertexAttribs2dvNV");
    glVertexAttribs3svNV = (glVertexAttribs3svNVPROC) lglGetProcAddress("glVertexAttribs3svNV");
    glVertexAttribs3fvNV = (glVertexAttribs3fvNVPROC) lglGetProcAddress("glVertexAttribs3fvNV");
    glVertexAttribs3dvNV = (glVertexAttribs3dvNVPROC) lglGetProcAddress("glVertexAttribs3dvNV");
    glVertexAttribs4svNV = (glVertexAttribs4svNVPROC) lglGetProcAddress("glVertexAttribs4svNV");
    glVertexAttribs4fvNV = (glVertexAttribs4fvNVPROC) lglGetProcAddress("glVertexAttribs4fvNV");
    glVertexAttribs4dvNV = (glVertexAttribs4dvNVPROC) lglGetProcAddress("glVertexAttribs4dvNV");
    glVertexAttribs4ubvNV = (glVertexAttribs4ubvNVPROC) lglGetProcAddress("glVertexAttribs4ubvNV");
}

void InitEXTVertexWeighting()
{
    if (!SupportedExtensions.EXT_vertex_weighting)
        return;
    glVertexWeightfEXT = (glVertexWeightfEXTPROC) lglGetProcAddress("glVertexWeightfEXT");
    glVertexWeightfvEXT = (glVertexWeightfvEXTPROC) lglGetProcAddress("glVertexWeightfvEXT");
    glVertexWeightPointerEXT = (glVertexWeightPointerEXTPROC) lglGetProcAddress("glVertexWeightPointerEXT");
}

void InitARBMultisample()
{
    if (!SupportedExtensions.ARB_multisample)
        return;
    glSampleCoverageARB = (glSampleCoveragePROC) lglGetProcAddress("glSampleCoverageARB");
}

void InitNVRegisterCombiners()
{
    if (!SupportedExtensions.NV_register_combiners)
        return;
    glCombinerParameterfvNV = (glCombinerParameterfvNVPROC) lglGetProcAddress("glCombinerParameterfvNV");
    glCombinerParameterfNV = (glCombinerParameterfNVPROC) lglGetProcAddress("glCombinerParameterfNV");
    glCombinerParameterivNV = (glCombinerParameterivNVPROC) lglGetProcAddress("glCombinerParameterivNV");
    glCombinerParameteriNV = (glCombinerParameteriNVPROC) lglGetProcAddress("glCombinerParameteriNV");
    glCombinerInputNV = (glCombinerInputNVPROC) lglGetProcAddress("glCombinerInputNV");
    glCombinerOutputNV = (glCombinerOutputNVPROC) lglGetProcAddress("glCombinerOutputNV");
    glFinalCombinerInputNV = (glFinalCombinerInputNVPROC) lglGetProcAddress("glFinalCombinerInputNV");
    glGetCombinerInputParameterfvNV = (glGetCombinerInputParameterfvNVPROC) lglGetProcAddress("glGetCombinerInputParameterfvNV");
    glGetCombinerInputParameterivNV = (glGetCombinerInputParameterivNVPROC) lglGetProcAddress("glGetCombinerInputParameterivNV");
    glGetCombinerOutputParameterfvNV = (glGetCombinerOutputParameterfvNVPROC) lglGetProcAddress("glGetCombinerOutputParameterfvNV");
    glGetCombinerOutputParameterivNV = (glGetCombinerOutputParameterivNVPROC) lglGetProcAddress("glGetCombinerOutputParameterivNV");
    glGetFinalCombinerInputParameterfvNV = (glGetFinalCombinerInputParameterfvNVPROC) lglGetProcAddress("glGetFinalCombinerInputParameterfvNV");
    glGetFinalCombinerInputParameterivNV = (glGetFinalCombinerInputParameterivNVPROC) lglGetProcAddress("glGetFinalCombinerInputParameterivNV");
}

void InitEXTPointParameters()
{
    if (!SupportedExtensions.EXT_point_parameters)
        return;
    glPointParameterfEXT = (glPointParameterfEXTPROC) lglGetProcAddress("glPointParameterfEXT");
    glPointParameterfvEXT = (glPointParameterfvEXTPROC) lglGetProcAddress("glPointParameterfvEXT");
}

void InitNVVertexArrayRange()
{
    if (!SupportedExtensions.NV_vertex_array_range)
        return;
    glFlushVertexArrayRangeNV = (glFlushVertexArrayRangeNVPROC) lglGetProcAddress("glFlushVertexArrayRangeNV");
    glVertexArrayRangeNV = (glVertexArrayRangeNVPROC) lglGetProcAddress("glVertexArrayRangeNV");
#ifdef _WIN32
    wglAllocateMemoryNV = (wglAllocateMemoryNVPROC) lglGetProcAddress("wglAllocateMemoryNV");
    wglFreeMemoryNV = (wglFreeMemoryNVPROC) lglGetProcAddress("wglFreeMemoryNV");
#endif /* WIN32 */
}
 
void InitEXTFogCoord()
{
    if (!SupportedExtensions.EXT_fog_coord)
        return;
    glFogCoordfEXT = (glFogCoordfEXTPROC) lglGetProcAddress("glFogCoordfEXT");
    glFogCoordfvEXT = (glFogCoordfvEXTPROC) lglGetProcAddress("glFogCoordfvEXT");
    glFogCoorddEXT = (glFogCoorddEXTPROC) lglGetProcAddress("glFogCoorddEXT");
    glFogCoorddvEXT = (glFogCoorddvEXTPROC) lglGetProcAddress("glFogCoorddvEXT");
    glFogCoordPointerEXT = (glFogCoordPointerEXTPROC) lglGetProcAddress("glFogCoordPointerEXT");
}

void InitEXTSecondaryColor()
{
    if (!SupportedExtensions.EXT_secondary_color)
        return;
    glSecondaryColor3bEXT = (glSecondaryColor3bEXTPROC) lglGetProcAddress("glSecondaryColor3bEXT");
    glSecondaryColor3bvEXT = (glSecondaryColor3bvEXTPROC) lglGetProcAddress("glSecondaryColor3bvEXT");
    glSecondaryColor3dEXT = (glSecondaryColor3dEXTPROC) lglGetProcAddress("glSecondaryColor3dEXT");
    glSecondaryColor3dvEXT = (glSecondaryColor3dvEXTPROC) lglGetProcAddress("glSecondaryColor3dvEXT");
    glSecondaryColor3fEXT = (glSecondaryColor3fEXTPROC) lglGetProcAddress("glSecondaryColor3fEXT");
    glSecondaryColor3fvEXT = (glSecondaryColor3fvEXTPROC) lglGetProcAddress("glSecondaryColor3fvEXT");
    glSecondaryColor3iEXT = (glSecondaryColor3iEXTPROC) lglGetProcAddress("glSecondaryColor3iEXT");
    glSecondaryColor3ivEXT = (glSecondaryColor3ivEXTPROC) lglGetProcAddress("glSecondaryColor3ivEXT");
    glSecondaryColor3sEXT = (glSecondaryColor3sEXTPROC) lglGetProcAddress("glSecondaryColor3sEXT");
    glSecondaryColor3svEXT = (glSecondaryColor3svEXTPROC) lglGetProcAddress("glSecondaryColor3svEXT");
    glSecondaryColor3ubEXT = (glSecondaryColor3ubEXTPROC) lglGetProcAddress("glSecondaryColor3ubEXT");
    glSecondaryColor3ubvEXT = (glSecondaryColor3ubvEXTPROC) lglGetProcAddress("glSecondaryColor3ubvEXT");
    glSecondaryColor3uiEXT = (glSecondaryColor3uiEXTPROC) lglGetProcAddress("glSecondaryColor3uiEXT");
    glSecondaryColor3uivEXT = (glSecondaryColor3uivEXTPROC) lglGetProcAddress("glSecondaryColor3uivEXT");
    glSecondaryColor3usEXT = (glSecondaryColor3usEXTPROC) lglGetProcAddress("glSecondaryColor3usEXT");
    glSecondaryColor3usvEXT = (glSecondaryColor3usvEXTPROC) lglGetProcAddress("glSecondaryColor3usvEXT");
    glSecondaryColorPointerEXT = (glSecondaryColorPointerEXTPROC) lglGetProcAddress("glSecondaryColorPointerEXT");
}

void InitEXTCompiledVertexArray()
{
    if (!SupportedExtensions.EXT_compiled_vertex_array)
        return;
    glLockArraysEXT = (glLockArraysEXTPROC) lglGetProcAddress("glLockArraysEXT");
    glUnlockArraysEXT = (glUnlockArraysEXTPROC) lglGetProcAddress("glUnlockArraysEXT");
}

void InitARBTransposeMatrix()
{
    if (!SupportedExtensions.ARB_transpose_matrix)
        return;
    glLoadTransposeMatrixfARB = (glLoadTransposeMatrixfPROC) lglGetProcAddress("glLoadTransposeMatrixfARB");
    glLoadTransposeMatrixdARB = (glLoadTransposeMatrixdPROC) lglGetProcAddress("glLoadTransposeMatrixdARB");
    glMultTransposeMatrixfARB = (glMultTransposeMatrixfPROC) lglGetProcAddress("glMultTransposeMatrixfARB");
    glMultTransposeMatrixdARB = (glMultTransposeMatrixdPROC) lglGetProcAddress("glMultTransposeMatrixdARB");
}

void InitEXTDrawRangeElements()
{
    if (!SupportedExtensions.EXT_draw_range_elements)
        return;
    glDrawRangeElementsEXT = (glDrawRangeElementsEXTPROC) lglGetProcAddress("glDrawRangeElementsEXT");
}

void InitARBMultitexture()
{
    if (!SupportedExtensions.ARB_multitexture)
        return;
#ifdef _WIN32
    glActiveTextureARB = (glActiveTexturePROC) lglGetProcAddress("glActiveTextureARB");
    glClientActiveTextureARB = (glClientActiveTexturePROC) lglGetProcAddress("glClientActiveTextureARB");

    glMultiTexCoord1dARB = (glMultiTexCoord1dPROC) lglGetProcAddress("glMultiTexCoord1dARB");
    glMultiTexCoord1dvARB = (glMultiTexCoord1dvPROC) lglGetProcAddress("glMultiTexCoord1dvARB");
    glMultiTexCoord1fARB = (glMultiTexCoord1fPROC) lglGetProcAddress("glMultiTexCoord1fARB");
    glMultiTexCoord1fvARB = (glMultiTexCoord1fvPROC) lglGetProcAddress("glMultiTexCoord1fvARB");
    glMultiTexCoord1iARB = (glMultiTexCoord1iPROC) lglGetProcAddress("glMultiTexCoord1iARB");
    glMultiTexCoord1ivARB = (glMultiTexCoord1ivPROC) lglGetProcAddress("glMultiTexCoord1ivARB");
    glMultiTexCoord1sARB = (glMultiTexCoord1sPROC) lglGetProcAddress("glMultiTexCoord1sARB");
    glMultiTexCoord1svARB = (glMultiTexCoord1svPROC) lglGetProcAddress("glMultiTexCoord1svARB");

    glMultiTexCoord2dARB = (glMultiTexCoord2dPROC) lglGetProcAddress("glMultiTexCoord2dARB");
    glMultiTexCoord2dvARB = (glMultiTexCoord2dvPROC) lglGetProcAddress("glMultiTexCoord2dvARB");
    glMultiTexCoord2fARB = (glMultiTexCoord2fPROC) lglGetProcAddress("glMultiTexCoord2fARB");
    glMultiTexCoord2fvARB = (glMultiTexCoord2fvPROC) lglGetProcAddress("glMultiTexCoord2fvARB");
    glMultiTexCoord2iARB = (glMultiTexCoord2iPROC) lglGetProcAddress("glMultiTexCoord2iARB");
    glMultiTexCoord2ivARB = (glMultiTexCoord2ivPROC) lglGetProcAddress("glMultiTexCoord2ivARB");
    glMultiTexCoord2sARB = (glMultiTexCoord2sPROC) lglGetProcAddress("glMultiTexCoord2sARB");
    glMultiTexCoord2svARB = (glMultiTexCoord2svPROC) lglGetProcAddress("glMultiTexCoord2svARB");

    glMultiTexCoord3dARB = (glMultiTexCoord3dPROC) lglGetProcAddress("glMultiTexCoord3dARB");
    glMultiTexCoord3dvARB = (glMultiTexCoord3dvPROC) lglGetProcAddress("glMultiTexCoord3dvARB");
    glMultiTexCoord3fARB = (glMultiTexCoord3fPROC) lglGetProcAddress("glMultiTexCoord3fARB");
    glMultiTexCoord3fvARB = (glMultiTexCoord3fvPROC) lglGetProcAddress("glMultiTexCoord3fvARB");
    glMultiTexCoord3iARB = (glMultiTexCoord3iPROC) lglGetProcAddress("glMultiTexCoord3iARB");
    glMultiTexCoord3ivARB = (glMultiTexCoord3ivPROC) lglGetProcAddress("glMultiTexCoord3ivARB");
    glMultiTexCoord3sARB = (glMultiTexCoord3sPROC) lglGetProcAddress("glMultiTexCoord3sARB");
    glMultiTexCoord3svARB = (glMultiTexCoord3svPROC) lglGetProcAddress("glMultiTexCoord3svARB");

    glMultiTexCoord4dARB = (glMultiTexCoord4dPROC) lglGetProcAddress("glMultiTexCoord4dARB");
    glMultiTexCoord4dvARB = (glMultiTexCoord4dvPROC) lglGetProcAddress("glMultiTexCoord4dvARB");
    glMultiTexCoord4fARB = (glMultiTexCoord4fPROC) lglGetProcAddress("glMultiTexCoord4fARB");
    glMultiTexCoord4fvARB = (glMultiTexCoord4fvPROC) lglGetProcAddress("glMultiTexCoord4fvARB");
    glMultiTexCoord4iARB = (glMultiTexCoord4iPROC) lglGetProcAddress("glMultiTexCoord4iARB");
    glMultiTexCoord4ivARB = (glMultiTexCoord4ivPROC) lglGetProcAddress("glMultiTexCoord4ivARB");
    glMultiTexCoord4sARB = (glMultiTexCoord4sPROC) lglGetProcAddress("glMultiTexCoord4sARB");
    glMultiTexCoord4svARB = (glMultiTexCoord4svPROC) lglGetProcAddress("glMultiTexCoord4svARB");
#endif 
}

void InitOpenGL1_2()
{
    char *s = (char*) glGetString(GL_VERSION);
    if (!s)
        return;
    s = strstr(s, "1.");
    if (s == NULL) return;
    if (s[2] < '2') return;
#ifdef _WIN32
    glTexImage3D = (glTexImage3DPROC) lglGetProcAddress("glTexImage3D");
    glTexSubImage3D = (glTexSubImage3DPROC) lglGetProcAddress("glTexSubImage3D");
    glCopyTexSubImage3D = (glCopyTexSubImage3DPROC) lglGetProcAddress("glCopyTexSubImage3D");
    glDrawRangeElements = (glDrawRangeElementsPROC) lglGetProcAddress("glDrawRangeElements");
#endif
}

void InitARBImaging()
{
    if (!SupportedExtensions.ARB_imaging)
        return;
    glBlendColor = (glBlendColorPROC) lglGetProcAddress("glBlendColor");
    glBlendEquation = (glBlendEquationPROC) lglGetProcAddress("glBlendEquation");
    glColorTable = (glColorTablePROC) lglGetProcAddress("glColorTable");
    glColorTableParameterfv = (glColorTableParameterfvPROC) lglGetProcAddress("glColorTableParameterfv");
    glColorTableParameteriv = (glColorTableParameterivPROC) lglGetProcAddress("glColorTableParameteriv");
    glCopyColorTable = (glCopyColorTablePROC) lglGetProcAddress("glCopyColorTable");
    glGetColorTable = (glGetColorTablePROC) lglGetProcAddress("glGetColorTable");
    glGetColorTableParameterfv = (glGetColorTableParameterfvPROC) lglGetProcAddress("glGetColorTableParameterfv");
    glGetColorTableParameteriv = (glGetColorTableParameterivPROC) lglGetProcAddress("glGetColorTableParameteriv");
    glColorSubTable = (glColorSubTablePROC) lglGetProcAddress("glColorSubTable");
    glCopyColorSubTable = (glCopyColorSubTablePROC) lglGetProcAddress("glCopyColorSubTable");
    glConvolutionFilter1D = (glConvolutionFilter1DPROC) lglGetProcAddress("glConvolutionFilter1D");
    glConvolutionFilter2D = (glConvolutionFilter2DPROC) lglGetProcAddress("glConvolutionFilter2D");
    glConvolutionParameterf = (glConvolutionParameterfPROC) lglGetProcAddress("glConvolutionParameterf");
    glConvolutionParameterfv = (glConvolutionParameterfvPROC) lglGetProcAddress("glConvolutionParameterfv");
    glConvolutionParameteri = (glConvolutionParameteriPROC) lglGetProcAddress("glConvolutionParameteri");
    glConvolutionParameteriv = (glConvolutionParameterivPROC) lglGetProcAddress("glConvolutionParameteriv");
    glCopyConvolutionFilter1D = (glCopyConvolutionFilter1DPROC) lglGetProcAddress("glCopyConvolutionFilter1D");
    glCopyConvolutionFilter2D = (glCopyConvolutionFilter2DPROC) lglGetProcAddress("glCopyConvolutionFilter2D");
    glGetConvolutionFilter = (glGetConvolutionFilterPROC) lglGetProcAddress("glGetConvolutionFilter");
    glGetConvolutionParameterfv = (glGetConvolutionParameterfvPROC) lglGetProcAddress("glGetConvolutionParameterfv");
    glGetConvolutionParameteriv = (glGetConvolutionParameterivPROC) lglGetProcAddress("glGetConvolutionParameteriv");
    glGetSeparableFilter = (glGetSeparableFilterPROC) lglGetProcAddress("glGetSeparableFilter");
    glSeparableFilter2D = (glSeparableFilter2DPROC) lglGetProcAddress("glSeparableFilter2D");
    glGetHistogram = (glGetHistogramPROC) lglGetProcAddress("glGetHistogram");
    glGetHistogramParameterfv = (glGetHistogramParameterfvPROC) lglGetProcAddress("glGetHistogramParameterfv");
    glGetHistogramParameteriv = (glGetHistogramParameterivPROC) lglGetProcAddress("glGetHistogramParameteriv");
    glGetMinmax = (glGetMinmaxPROC) lglGetProcAddress("glGetMinmax");
    glGetMinmaxParameterfv = (glGetMinmaxParameterfvPROC) lglGetProcAddress("glGetMinmaxParameterfv");
    glGetMinmaxParameteriv = (glGetMinmaxParameterivPROC) lglGetProcAddress("glGetMinmaxParameteriv");
    glHistogram = (glHistogramPROC) lglGetProcAddress("glHistogram");
    glMinmax = (glMinmaxPROC) lglGetProcAddress("glMinmax");
    glResetHistogram = (glResetHistogramPROC) lglGetProcAddress("glResetHistogram");
    glResetMinmax = (glResetMinmaxPROC) lglGetProcAddress("glResetMinmax");
}

void InitOpenGL1_3()
{
    char *s = (char*) glGetString(GL_VERSION);
    if (!s) return;
    s = strstr(s, "1.");
    if (s == NULL) return;
    if (s[2] < '3') return;
#ifdef _WIN32    
    glActiveTexture = (glActiveTexturePROC) lglGetProcAddress("glActiveTexture");
    glClientActiveTexture = (glClientActiveTexturePROC) lglGetProcAddress("glClientActiveTexture");

    glMultiTexCoord1d = (glMultiTexCoord1dPROC) lglGetProcAddress("glMultiTexCoord1d");
    glMultiTexCoord1dv = (glMultiTexCoord1dvPROC) lglGetProcAddress("glMultiTexCoord1dv");
    glMultiTexCoord1f = (glMultiTexCoord1fPROC) lglGetProcAddress("glMultiTexCoord1f");
    glMultiTexCoord1fv = (glMultiTexCoord1fvPROC) lglGetProcAddress("glMultiTexCoord1fv");
    glMultiTexCoord1i = (glMultiTexCoord1iPROC) lglGetProcAddress("glMultiTexCoord1i");
    glMultiTexCoord1iv = (glMultiTexCoord1ivPROC) lglGetProcAddress("glMultiTexCoord1iv");
    glMultiTexCoord1s = (glMultiTexCoord1sPROC) lglGetProcAddress("glMultiTexCoord1s");
    glMultiTexCoord1sv = (glMultiTexCoord1svPROC) lglGetProcAddress("glMultiTexCoord1sv");

    glMultiTexCoord2d = (glMultiTexCoord2dPROC) lglGetProcAddress("glMultiTexCoord2d");
    glMultiTexCoord2dv = (glMultiTexCoord2dvPROC) lglGetProcAddress("glMultiTexCoord2dv");
    glMultiTexCoord2f = (glMultiTexCoord2fPROC) lglGetProcAddress("glMultiTexCoord2f");
    glMultiTexCoord2fv = (glMultiTexCoord2fvPROC) lglGetProcAddress("glMultiTexCoord2fv");
    glMultiTexCoord2i = (glMultiTexCoord2iPROC) lglGetProcAddress("glMultiTexCoord2i");
    glMultiTexCoord2iv = (glMultiTexCoord2ivPROC) lglGetProcAddress("glMultiTexCoord2iv");
    glMultiTexCoord2s = (glMultiTexCoord2sPROC) lglGetProcAddress("glMultiTexCoord2s");
    glMultiTexCoord2sv = (glMultiTexCoord2svPROC) lglGetProcAddress("glMultiTexCoord2sv");

    glMultiTexCoord3d = (glMultiTexCoord3dPROC) lglGetProcAddress("glMultiTexCoord3d");
    glMultiTexCoord3dv = (glMultiTexCoord3dvPROC) lglGetProcAddress("glMultiTexCoord3dv");
    glMultiTexCoord3f = (glMultiTexCoord3fPROC) lglGetProcAddress("glMultiTexCoord3f");
    glMultiTexCoord3fv = (glMultiTexCoord3fvPROC) lglGetProcAddress("glMultiTexCoord3fv");
    glMultiTexCoord3i = (glMultiTexCoord3iPROC) lglGetProcAddress("glMultiTexCoord3i");
    glMultiTexCoord3iv = (glMultiTexCoord3ivPROC) lglGetProcAddress("glMultiTexCoord3iv");
    glMultiTexCoord3s = (glMultiTexCoord3sPROC) lglGetProcAddress("glMultiTexCoord3s");
    glMultiTexCoord3sv = (glMultiTexCoord3svPROC) lglGetProcAddress("glMultiTexCoord3sv");

    glMultiTexCoord4d = (glMultiTexCoord4dPROC) lglGetProcAddress("glMultiTexCoord4d");
    glMultiTexCoord4dv = (glMultiTexCoord4dvPROC) lglGetProcAddress("glMultiTexCoord4dv");
    glMultiTexCoord4f = (glMultiTexCoord4fPROC) lglGetProcAddress("glMultiTexCoord4f");
    glMultiTexCoord4fv = (glMultiTexCoord4fvPROC) lglGetProcAddress("glMultiTexCoord4fv");
    glMultiTexCoord4i = (glMultiTexCoord4iPROC) lglGetProcAddress("glMultiTexCoord4i");
    glMultiTexCoord4iv = (glMultiTexCoord4ivPROC) lglGetProcAddress("glMultiTexCoord4iv");
    glMultiTexCoord4s = (glMultiTexCoord4sPROC) lglGetProcAddress("glMultiTexCoord4s");
    glMultiTexCoord4sv = (glMultiTexCoord4svPROC) lglGetProcAddress("glMultiTexCoord4sv");

    glLoadTransposeMatrixf = (glLoadTransposeMatrixfPROC) lglGetProcAddress("glLoadTransposeMatrixf");
    glLoadTransposeMatrixd = (glLoadTransposeMatrixdPROC) lglGetProcAddress("glLoadTransposeMatrixd");
    glMultTransposeMatrixf = (glMultTransposeMatrixfPROC) lglGetProcAddress("glMultTransposeMatrixf");
    glMultTransposeMatrixd = (glMultTransposeMatrixdPROC) lglGetProcAddress("glMultTransposeMatrixd");
    glCompressedTexImage3D = (glCompressedTexImage3DPROC) lglGetProcAddress("glCompressedTexImage3D");
    glCompressedTexImage2D = (glCompressedTexImage2DPROC) lglGetProcAddress("glCompressedTexImage2D");
    glCompressedTexImage1D = (glCompressedTexImage1DPROC) lglGetProcAddress("glCompressedTexImage1D");
    glCompressedTexSubImage3D = (glCompressedTexSubImage3DPROC) lglGetProcAddress("glCompressedTexSubImage3D");
    glCompressedTexSubImage2D = (glCompressedTexSubImage2DPROC) lglGetProcAddress("glCompressedTexSubImage2D");
    glCompressedTexSubImage1D = (glCompressedTexSubImage1DPROC) lglGetProcAddress("glCompressedTexSubImage1D");
    glGetCompressedTexImage = (glGetCompressedTexImagePROC) lglGetProcAddress("glGetCompressedTexImage");

    glSampleCoverage = (glSampleCoveragePROC) lglGetProcAddress("glSampleCoverage");
#endif
}

void InitSupportedExtensions()
{
    char *s = (char*) glGetString(GL_VERSION);
    if (!s)
        return;
    s = strstr(s, "1.");
    if (s == NULL)
    {
        SupportedExtensions.OpenGL12 = 0;    
        SupportedExtensions.OpenGL13 = 0;    
        SupportedExtensions.OpenGL14 = 0;
    }
    else
    {
        if (s[2] <= '4')
        {
            SupportedExtensions.OpenGL12 = 1;    
            SupportedExtensions.OpenGL13 = 1;    
            SupportedExtensions.OpenGL14 = 1;    
        }
        if (s[2] <= '3')
        {
            SupportedExtensions.OpenGL12 = 1;    
            SupportedExtensions.OpenGL13 = 1;    
            SupportedExtensions.OpenGL14 = 0;    
        }
        if (s[2] <= '2')
        {
            SupportedExtensions.OpenGL12 = 1;    
            SupportedExtensions.OpenGL13 = 0;    
            SupportedExtensions.OpenGL14 = 0;    
        }
        if (s[2] < '2')
        {
            SupportedExtensions.OpenGL12 = 0;    
            SupportedExtensions.OpenGL13 = 0;    
            SupportedExtensions.OpenGL14 = 0;    
        }
    }
    SupportedExtensions.ARB_imaging = QueryExtension("GL_ARB_imaging");
    SupportedExtensions.ARB_matrix_palette = QueryExtension("GL_ARB_matrix_palette");
    SupportedExtensions.ARB_multisample = QueryExtension("GL_ARB_multisample");
    SupportedExtensions.ARB_multitexture = QueryExtension("GL_ARB_multitexture");
    SupportedExtensions.ARB_point_parameters = QueryExtension("GL_ARB_point_parameters");
    SupportedExtensions.ARB_texture_border_clamp = QueryExtension("GL_ARB_texture_border_clamp");
    SupportedExtensions.ARB_texture_compression = QueryExtension("GL_ARB_texture_compression");
    SupportedExtensions.ARB_texture_cube_map = QueryExtension("GL_ARB_texture_cube_map");
    SupportedExtensions.ARB_texture_env_add = QueryExtension("GL_ARB_texture_env_add");
    SupportedExtensions.ARB_texture_env_combine = QueryExtension("GL_ARB_texture_env_combine");
    SupportedExtensions.ARB_texture_env_crossbar = QueryExtension("GL_ARB_texture_env_crossbar");
    SupportedExtensions.ARB_texture_env_dot3 = QueryExtension("GL_ARB_texture_env_dot3");
    SupportedExtensions.ARB_transpose_matrix = QueryExtension("GL_ARB_transpose_matrix");
    SupportedExtensions.ARB_vertex_blend = QueryExtension("GL_ARB_vertex_blend");
    SupportedExtensions.ATI_element_array = QueryExtension("GL_ATI_element_array");
    SupportedExtensions.ATI_envmap_bumpmap = QueryExtension("GL_ATI_envmap_bumpmap");
    SupportedExtensions.ATI_fragment_shader = QueryExtension("GL_ATI_fragment_shader");
    SupportedExtensions.ATI_pn_triangles = QueryExtension("GL_ATI_pn_triangles");
    SupportedExtensions.ATI_texture_mirror_once = QueryExtension("GL_ATI_texture_mirror_once");
    SupportedExtensions.ATI_vertex_array_object = QueryExtension("GL_ATI_vertex_array_object");
    SupportedExtensions.ATI_vertex_streams = QueryExtension("GL_ATI_vertex_streams");
    SupportedExtensions.ATIX_point_sprites = QueryExtension("GL_ATIX_point_sprites");
    SupportedExtensions.ATIX_texture_env_route = QueryExtension("GL_ATIX_texture_env_route");
    SupportedExtensions.EXT_abgr = QueryExtension("GL_EXT_abgr");
    SupportedExtensions.EXT_compiled_vertex_array = QueryExtension("GL_EXT_compiled_vertex_array");
    SupportedExtensions.EXT_fog_coord = QueryExtension("GL_EXT_fog_coord");
    SupportedExtensions.EXT_multi_draw_arrays = QueryExtension("GL_EXT_multi_draw_arrays");
    SupportedExtensions.EXT_point_parameters = QueryExtension("GL_EXT_point_parameters");
    SupportedExtensions.EXT_secondary_color = QueryExtension("GL_EXT_secondary_color");
    SupportedExtensions.EXT_stencil_wrap = QueryExtension("GL_EXT_stencil_wrap");
    SupportedExtensions.EXT_texture_filter_anisotropic = QueryExtension("GL_EXT_texture_filter_anisotropic");
    SupportedExtensions.EXT_texture_lod_bias = QueryExtension("GL_EXT_texture_lod_bias");
    SupportedExtensions.EXT_vertex_shader = QueryExtension("GL_EXT_vertex_shader");
    SupportedExtensions.EXT_vertex_weighting = QueryExtension("GL_EXT_vertex_weighting");
    SupportedExtensions.HP_occlusion_test = QueryExtension("GL_HP_occlusion_test");
    SupportedExtensions.NV_blend_square = QueryExtension("GL_NV_blend_square");
    SupportedExtensions.NV_copy_depth_to_color = QueryExtension("GL_NV_copy_depth_to_color");
    SupportedExtensions.NV_depth_clamp = QueryExtension("GL_NV_depth_clamp");
    SupportedExtensions.NV_evaluators = QueryExtension("GL_NV_evaluators");
    SupportedExtensions.NV_fence = QueryExtension("GL_NV_fence");
    SupportedExtensions.NV_fog_distance = QueryExtension("GL_NV_fog_distance");
    SupportedExtensions.NV_light_max_exponent = QueryExtension("GL_NV_light_max_exponent");
    SupportedExtensions.NV_occlusion_query = QueryExtension("GL_NV_occlusion_query");
    SupportedExtensions.NV_packed_depth_stencil = QueryExtension("GL_NV_packed_depth_stencil");
    SupportedExtensions.NV_point_sprite = QueryExtension("GL_NV_point_sprite");
    SupportedExtensions.NV_register_combiners = QueryExtension("GL_NV_register_combiners");
    SupportedExtensions.NV_register_combiners2 = QueryExtension("GL_NV_register_combiners2");
    SupportedExtensions.NV_texgen_reflection = QueryExtension("GL_NV_texgen_reflection");
    SupportedExtensions.NV_texture_env_combine4 = QueryExtension("GL_NV_texture_env_combine4");
    SupportedExtensions.NV_texture_rectangle = QueryExtension("GL_NV_texture_rectangle");
    SupportedExtensions.NV_texture_shader = QueryExtension("GL_NV_texture_shader");
    SupportedExtensions.NV_texture_shader2 = QueryExtension("GL_NV_texture_shader2");
    SupportedExtensions.NV_texture_shader3 = QueryExtension("GL_NV_texture_shader3");
    SupportedExtensions.NV_vertex_array_range = QueryExtension("GL_NV_vertex_array_range");
    SupportedExtensions.NV_vertex_array_range2 = QueryExtension("GL_NV_vertex_array_range2");
    SupportedExtensions.NV_vertex_program = QueryExtension("GL_NV_vertex_program");
    SupportedExtensions.NV_vertex_program1_1 = QueryExtension("GL_NV_vertex_program1_1");
    SupportedExtensions.SGIS_generate_mipmap = QueryExtension("GL_SGIS_generate_mipmap");
    SupportedExtensions.SGIX_depth_texture = QueryExtension("GL_SGIX_depth_texture");
    SupportedExtensions.SGIX_shadow = QueryExtension("GL_SGIX_shadow");
    SupportedExtensions.ARB_texture_mirrored_repeat = QueryExtension("GL_ARB_texture_mirrored_repeat");
    SupportedExtensions.ARB_depth_texture = QueryExtension("GL_ARB_depth_texture");
    SupportedExtensions.ARB_shadow = QueryExtension("GL_ARB_shadow");
    SupportedExtensions.ARB_shadow_ambient = QueryExtension("GL_ARB_shadow_ambient");
    SupportedExtensions.ARB_window_pos = QueryExtension("GL_ARB_window_pos");
    SupportedExtensions.EXT_shadow_funcs = QueryExtension("GL_EXT_shadow_funcs");
    SupportedExtensions.EXT_texture_compression_s3tc = QueryExtension("GL_EXT_texture_compression_s3tc");
    SupportedExtensions.EXT_draw_range_elements = QueryExtension("GL_EXT_draw_range_elements");
    SupportedExtensions.EXT_stencil_two_side = QueryExtension("GL_EXT_stencil_two_side");
    SupportedExtensions.ARB_vertex_program = QueryExtension("GL_ARB_vertex_program");
}

/* init the extensions and load all the functions */
int glInitialize()
{
    error = 0;
    InitSupportedExtensions();
    
    /* first load the etensions */
    InitARBImaging();
    InitARBTransposeMatrix();
    InitARBMultisample();
    InitEXTCompiledVertexArray();
    InitEXTSecondaryColor();
    InitEXTFogCoord();
    InitNVVertexArrayRange();
    InitEXTPointParameters();
    InitNVRegisterCombiners();
    InitEXTVertexWeighting();
    InitNVVertexProgram();
    InitNVFence();
    InitNVRegisterCombiners2();
    InitATIPNTriangles();
    InitARBPointParameters();
    InitARBVertexBlend();
    InitEXTMultiDrawArrays();
    InitARBMatrixPalette();
    InitEXTVertexShader();
    InitATIEnvmapBumpmap();
    InitATIFragmentShader();
    InitATIElementArray();
    InitATIVertexStreams();
    InitATIVertexArrayObject();
    InitNVOcclusionQuery();
    InitNVPointSprite();
    InitARBWindowPos();
    InitARBTextureCompression();
    InitEXTDrawRangeElements();
    InitEXTStencilTwoSide();
    InitARBVertexProgram();

#ifdef _WIN32
    InitARBMultitexture();
    /* now load core opengl */
    InitOpenGL1_2();
    InitOpenGL1_3();
    wglInitialize(_dc);
#endif
    return error;
}

