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

import org.lwjgl.Sys;
import org.lwjgl.Window;

import java.lang.reflect.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * $Id$
 *
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
	public static boolean GL_ARB_matrix_palette;
	public static boolean GL_ARB_multisample;
	public static boolean GL_ARB_multitexture;
	public static boolean GL_ARB_point_parameters;
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
	public static boolean GL_ARB_transpose_matrix;
	public static boolean GL_ARB_vertex_blend;
	public static boolean GL_ARB_vertex_program;
	public static boolean GL_ARB_vertex_buffer_object;
	public static boolean GL_ARB_window_pos;
	public static boolean GL_EXT_abgr;
	public static boolean GL_EXT_bgra;
	public static boolean GL_EXT_blend_color;
	public static boolean GL_EXT_blend_function_separate;
	public static boolean GL_EXT_blend_minmax;
	public static boolean GL_EXT_blend_subtract;
	public static boolean GL_EXT_compiled_vertex_array;
	public static boolean GL_EXT_cull_vertex;
	public static boolean GL_EXT_draw_range_elements;
	public static boolean GL_EXT_fog_coord;
	public static boolean GL_EXT_light_max_exponent;
	public static boolean GL_EXT_multi_draw_arrays;
	public static boolean GL_EXT_packed_pixels;
	public static boolean GL_EXT_paletted_texture;
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
	public static boolean GL_EXT_vertex_array;
	public static boolean GL_EXT_vertex_shader;
	public static boolean GL_EXT_vertex_weighting;
	public static boolean GL_ATI_element_array;
	public static boolean GL_ATI_envmap_bumpmap;
	public static boolean GL_ATI_fragment_shader;
	public static boolean GL_ATI_pn_triangles;
	public static boolean GL_ATI_texture_mirror_once;
	public static boolean GL_ATI_vertex_array_object;
	public static boolean GL_ATI_vertex_streams;
	public static boolean GL_ATIX_point_sprites;
	public static boolean GL_ATIX_texture_env_route;
	public static boolean GL_HP_occlusion_test;
	public static boolean GL_NV_blend_square;
	public static boolean GL_NV_copy_depth_to_color;
	public static boolean GL_NV_depth_clamp;
	public static boolean GL_NV_evaluators;
	public static boolean GL_NV_fence;
	public static boolean GL_NV_fog_distance;
	public static boolean GL_NV_light_max_exponent;
	public static boolean GL_NV_occlusion_query;
	public static boolean GL_NV_packed_depth_stencil;
	public static boolean GL_NV_point_sprite;
	public static boolean GL_NV_register_combiners;
	public static boolean GL_NV_register_combiners2;
	public static boolean GL_NV_texgen_reflection;
	public static boolean GL_NV_texture_env_combine4;
	public static boolean GL_NV_texture_rectangle;
	public static boolean GL_NV_texture_shader;
	public static boolean GL_NV_texture_shader2;
	public static boolean GL_NV_texture_shader3;
	public static boolean GL_NV_vertex_array_range;
	public static boolean GL_NV_vertex_array_range2;
	public static boolean GL_NV_vertex_program;
	public static boolean GL_NV_vertex_program1_1;
	public static boolean GL_SGIS_generate_mipmap;
	public static boolean GL_SGIX_shadow;
	public static boolean GL_SGIX_depth_texture;
	public static boolean OpenGL10;
	public static boolean OpenGL11;
	public static boolean OpenGL12;
	public static boolean OpenGL13;
	public static boolean OpenGL14;

	/*
	 * Available WGL extensions
	 */
	public static boolean WGL_ARB_buffer_region;
	public static boolean WGL_ARB_extensions_string;
	public static boolean WGL_ARB_pbuffer;
	public static boolean WGL_ARB_pixel_format;
	public static boolean WGL_ARB_render_texture;
	public static boolean WGL_EXT_extensions_string;
	public static boolean WGL_EXT_swap_control;
		
	static {
		System.loadLibrary(Sys.getLibraryName());
	}

	private static void setExtensionFields(String exts, HashMap field_map) {
		StringTokenizer st = new StringTokenizer(exts);
		while (st.hasMoreTokens()) {
			String ext = st.nextToken();
			if(org.lwjgl.Sys.DEBUG) {
				System.out.println(ext);
			}

			Field f = (Field)field_map.get(ext);
			if (f != null) {
				try {
					f.setBoolean(GLCaps.class, true);
				} catch (IllegalAccessException e) {
					e.printStackTrace(System.err);
				}
			}

		}
	}

	/**
	 * Determine which extensions are available. Use this to initialize capability fields.
	 * Can only be called _after_ a GLWindow or Pbuffer has been created.
	 */
	public static void determineAvailableExtensions() {

		// Grab all the public static booleans out of this class
		Field[] fields = GLCaps.class.getDeclaredFields();
		HashMap map = new HashMap(fields.length);
		for (int i = 0; i < fields.length; i++) {
			if (Modifier.isStatic(fields[i].getModifiers())
				&& fields[i].getType() == boolean.class) {
				map.put(fields[i].getName(), fields[i]);
				// reset fields
				try {
					fields[i].setBoolean(GLCaps.class, false);
				} catch (IllegalAccessException e) {
					e.printStackTrace(System.err);
				}
			}
		}

		determineAvailableWGLExtensions(map);
		String exts = CoreGL11.glGetString(CoreGL11.GL_EXTENSIONS);
		if(org.lwjgl.Sys.DEBUG) {
			System.out.println("Available GL extensions:");
		}
		setExtensionFields(exts, map);

		// Let's see what openGL version we are too:
		String version = CoreGL11.glGetString(CoreGL11.GL_VERSION);
		int i = version.indexOf("1.");
		if (i > -1) {
			char c = version.charAt(i + 2);
			// Each case intentionally falls through!
			switch (c) {
				case '4':
					OpenGL14 = true;
				case '3':
					OpenGL13 = true;
				case '2':
					OpenGL12 = true;
				case '1':
					OpenGL11 = true;
				case '0':
					OpenGL10 = true;
					break ;
				default:
					// Unexpected character - ignore
			}
		}
	}

	private static native boolean isWGLEXTExtensionsStringAvailable();

	private static native boolean isWGLARBExtensionsStringAvailable();

	/**
	 * Determine which WGL extensions are available
	 */
	private static void determineAvailableWGLExtensions(HashMap field_map) {

		// First we must determine if WGL_EXT_extensions_string is available
		WGL_ARB_extensions_string = isWGLARBExtensionsStringAvailable();
		WGL_EXT_extensions_string = isWGLEXTExtensionsStringAvailable();
		if (!WGL_EXT_extensions_string && !WGL_ARB_extensions_string)
			return;
		final String exts;

		if (WGL_ARB_extensions_string)
			exts = GL.wglGetExtensionsStringARB(Window.getHandle());
		// Remember - this is an HWND not an HDC, which is what's required. The native
		// code on the other side of wglGetExtensionsStringARB gets the HDC from the HWND
		// behind the scenes.
		else
			exts = GL.wglGetExtensionsStringEXT();

		if (exts == null)
			return;

		if(org.lwjgl.Sys.DEBUG) {
			System.out.println("Available WGL extensions:");
		}
		setExtensionFields(exts, field_map);
	}
}
