/*
 * Copyright (c) 2002 Lightweight Java Game Library Project
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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.lwjgl.Sys;

/**
 * $Id$
 * <p/>
 * A static class describing all supported GL capabilities.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */
public abstract class GLCaps {

	/*
	 * Available extensions
	 */
	public static boolean GL_ARB_imaging;
	public static boolean GL_ARB_depth_texture;
	public static boolean GL_ARB_fragment_program;
	public static boolean GL_ARB_fragment_shader;
	public static boolean GL_ARB_matrix_palette;
	public static boolean GL_ARB_multisample;
	public static boolean GL_ARB_multitexture;
	public static boolean GL_ARB_occlusion_query;
	public static boolean GL_ARB_point_parameters;
	public static boolean GL_ARB_point_sprite;
	public static boolean GL_ARB_shading_language;
	public static boolean GL_ARB_shader_objects;
	public static boolean GL_ARB_shadow;
	public static boolean GL_ARB_shadow_ambient;
	public static boolean GL_ARB_texture_compression;
	public static boolean GL_ARB_texture_env_add;
	public static boolean GL_ARB_texture_env_dot3;
	public static boolean GL_ARB_texture_env_combine;
	public static boolean GL_ARB_texture_env_crossbar;
	public static boolean GL_ARB_texture_border_clamp;
	public static boolean GL_ARB_texture_cube_map;
	public static boolean GL_ARB_texture_mirrored_repeat;
	public static boolean GL_ARB_texture_non_power_of_two;
	public static boolean GL_ARB_transpose_matrix;
	public static boolean GL_ARB_vertex_blend;
	public static boolean GL_ARB_vertex_program;
	public static boolean GL_ARB_vertex_buffer_object;
	public static boolean GL_ARB_vertex_shader;
	public static boolean GL_ARB_window_pos;

	public static boolean GL_EXT_abgr;
	public static boolean GL_EXT_bgra;
	public static boolean GL_EXT_blend_function_separate;
	public static boolean GL_EXT_blend_subtract;
	public static boolean GL_EXT_compiled_vertex_array;
	public static boolean GL_EXT_draw_range_elements;
	public static boolean GL_EXT_multi_draw_arrays;
	public static boolean GL_EXT_fog_coord;
	public static boolean GL_EXT_packed_pixels;
	public static boolean GL_EXT_point_parameters;
	public static boolean GL_EXT_rescale_normal;
	public static boolean GL_EXT_secondary_color;
	public static boolean GL_EXT_separate_specular_color;
	public static boolean GL_EXT_shadow_funcs;
	public static boolean GL_EXT_shared_texture_palette;
	public static boolean GL_EXT_stencil_two_side;
	public static boolean GL_EXT_stencil_wrap;
	public static boolean GL_EXT_texture_compression_s3tc;
	public static boolean GL_EXT_texture_env_combine;
	public static boolean GL_EXT_texture_env_dot3;
	public static boolean GL_EXT_texture_filter_anisotropic;
	public static boolean GL_EXT_texture_lod_bias;
	public static boolean GL_EXT_vertex_shader;
	public static boolean GL_EXT_vertex_weighting;

	public static boolean GL_ATI_draw_buffers;
	public static boolean GL_ATI_element_array;
	public static boolean GL_ATI_envmap_bumpmap;
	public static boolean GL_ATI_fragment_shader;
	public static boolean GL_ATI_map_object_buffer;
	public static boolean GL_ATI_pn_triangles;
	public static boolean GL_ATI_texture_float;
	public static boolean GL_ATI_texture_mirror_once;
	public static boolean GL_ATI_vertex_array_object;
	public static boolean GL_ATI_vertex_attrib_array_object;
	public static boolean GL_ATI_vertex_streams;
	public static boolean GL_ATI_separate_stencil;

	public static boolean GL_ATIX_point_sprites;
	public static boolean GL_ATIX_texture_env_route;

	public static boolean GL_NV_blend_square;
	public static boolean GL_NV_copy_depth_to_color;
	public static boolean GL_NV_depth_clamp;
	public static boolean GL_NV_evaluators;
	public static boolean GL_NV_fence;
	public static boolean GL_NV_fragment_program;
	public static boolean GL_NV_float_buffer;
	public static boolean GL_NV_fog_distance;
	public static boolean GL_NV_half_float;
	public static boolean GL_NV_light_max_exponent;
	public static boolean GL_NV_multisample_filter_hint;
	public static boolean GL_NV_occlusion_query;
	public static boolean GL_NV_packed_depth_stencil;
	public static boolean GL_NV_pixel_data_range;
	public static boolean GL_NV_point_sprite;
	public static boolean GL_NV_primitive_restart;
	public static boolean GL_NV_register_combiners;
	public static boolean GL_NV_register_combiners2;
	public static boolean GL_NV_texgen_reflection;
	public static boolean GL_NV_texture_compression_vtc;
	public static boolean GL_NV_texture_env_combine4;
	public static boolean GL_NV_texture_expand_normal;
	public static boolean GL_NV_texture_rectangle;
	public static boolean GL_NV_texture_shader;
	public static boolean GL_NV_texture_shader2;
	public static boolean GL_NV_texture_shader3;
	public static boolean GL_NV_vertex_array_range;
	public static boolean GL_NV_vertex_array_range2;
	public static boolean GL_NV_vertex_program;
	public static boolean GL_NV_vertex_program1_1;
	public static boolean GL_NV_vertex_program2;

	public static boolean OpenGL11;
	public static boolean OpenGL12;
	public static boolean OpenGL13;
	public static boolean OpenGL14;
	public static boolean OpenGL15;

	static {
		System.loadLibrary(Sys.getLibraryName());
	}

	private static void setExtensionFields(Set exts, HashMap field_map) {
		Sys.log("Available extensions:");
		Iterator it = exts.iterator();
		while ( it.hasNext() ) {
			String ext = (String)it.next();
			Sys.log(ext);

			Field f = (Field)field_map.get(ext);
			if ( f != null ) {
				try {
					f.setBoolean(GLCaps.class, true);
				} catch (IllegalAccessException e) {
					e.printStackTrace(System.err);
				}
			}

		}
	}

	/**
	 * Determine which extensions are available. Use this to initialize capability fields. Can only be
	 * called _after_ a GLWindow or Pbuffer has been created (or a context from some other GL library).
	 * Using LWJGL, this method is called automatically for you when the LWJGL Window is created and there
	 * is no need to call it yourself.
	 *
	 * @param exts A Set of OpenGL extension string names
	 */
	public static void determineAvailableExtensions(Set exts) {
		// Grab all the public static booleans out of this class
		Field[] fields = GLCaps.class.getDeclaredFields();
		HashMap map = new HashMap(fields.length);
		for ( int i = 0; i < fields.length; i++ ) {
			if ( Modifier.isStatic(fields[i].getModifiers()) && fields[i].getType() == boolean.class ) {
				map.put(fields[i].getName(), fields[i]);
				// reset fields
				try {
					fields[i].setBoolean(GLCaps.class, false);
				} catch (IllegalAccessException e) {
					e.printStackTrace(System.err);
				}
			}
		}

		setExtensionFields(exts, map);
	}
}
