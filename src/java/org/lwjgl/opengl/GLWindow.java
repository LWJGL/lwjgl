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

import org.lwjgl.*;
import org.lwjgl.Window;

import java.lang.reflect.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * $Id$
 *
 * A visible GL context. Can either be windowed or fullscreen.
 * 
 * Each instance of GLWindow is only valid in the thread that creates it. 
 * In addition, only one instance may be created at any one time.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public class GLWindow extends Window {
	
	/** Has the GL been created yet? */
	private boolean created;
	
	/** Handle to the native GL rendering context */
	protected int handle;
	
	/** Color bits */
	protected final int color;
	
	/** Alpha bits */
	protected final int alpha;
	
	/** Depth bits */
	protected final int depth;
	
	/** Stencil bits */
	protected final int stencil;
	
	private int x, y;
	
	/** Fullscreen */
	protected final boolean fullscreen;

	/*
	 * Available extensions
	 */
	public boolean GL_ARB_imaging;
	public boolean GL_ARB_depth_texture;
	public boolean GL_ARB_matrix_palette;
	public boolean GL_ARB_multisample;
	public boolean GL_ARB_multitexture;
	public boolean GL_ARB_point_parameters;
	public boolean GL_ARB_shadow;
	public boolean GL_ARB_shadow_ambient;
	public boolean GL_ARB_texture_compression;
	public boolean GL_ARB_texture_env_add;
	public boolean GL_ARB_texture_env_dot3;
	public boolean GL_ARB_texture_env_combine;
	public boolean GL_ARB_texture_env_crossbar;
	public boolean GL_ARB_texture_border_clamp;
	public boolean GL_ARB_texture_cube_map;
	public boolean GL_ARB_texture_mirrored_repeat;
	public boolean GL_ARB_transpose_matrix;
	public boolean GL_ARB_vertex_blend;
	public boolean GL_ARB_vertex_program;
	public boolean GL_ARB_vertex_buffer_object;
	public boolean GL_ARB_window_pos;
	public boolean GL_EXT_abgr;
	public boolean GL_EXT_bgra;
	public boolean GL_EXT_blend_color;
	public boolean GL_EXT_blend_function_separate;
	public boolean GL_EXT_blend_minmax;
	public boolean GL_EXT_blend_subtract;
	public boolean GL_EXT_compiled_vertex_array;
	public boolean GL_EXT_cull_vertex;
	public boolean GL_EXT_draw_range_elements;
	public boolean GL_EXT_fog_coord;
	public boolean GL_EXT_light_max_exponent;
	public boolean GL_EXT_multi_draw_arrays;
	public boolean GL_EXT_packed_pixels;
	public boolean GL_EXT_paletted_texture;
	public boolean GL_EXT_point_parameters;
	public boolean GL_EXT_rescale_normal;
	public boolean GL_EXT_secondary_color;
	public boolean GL_EXT_separate_specular_color;
	public boolean GL_EXT_shadow_funcs;
	public boolean GL_EXT_shared_texture_palette;
	public boolean GL_EXT_stencil_two_side;
	public boolean GL_EXT_stencil_wrap;
	public boolean GL_EXT_texture_compression_s3tc;
	public boolean GL_EXT_texture_env_combine;
	public boolean GL_EXT_texture_env_dot3;
	public boolean GL_EXT_texture_filter_anisotropic;
	public boolean GL_EXT_texture_lod_bias;
	public boolean GL_EXT_vertex_array;
	public boolean GL_EXT_vertex_shader;
	public boolean GL_EXT_vertex_weighting;
	public boolean GL_ATI_element_array;
	public boolean GL_ATI_envmap_bumpmap;
	public boolean GL_ATI_fragment_shader;
	public boolean GL_ATI_pn_triangles;
	public boolean GL_ATI_texture_mirror_once;
	public boolean GL_ATI_vertex_array_object;
	public boolean GL_ATI_vertex_streams;
	public boolean GL_ATIX_point_sprites;
	public boolean GL_ATIX_texture_env_route;
	public boolean GL_HP_occlusion_test;
	public boolean GL_NV_blend_square;
	public boolean GL_NV_copy_depth_to_color;
	public boolean GL_NV_depth_clamp;
	public boolean GL_NV_evaluators;
	public boolean GL_NV_fence;
	public boolean GL_NV_fog_distance;
	public boolean GL_NV_light_max_exponent;
	public boolean GL_NV_occlusion_query;
	public boolean GL_NV_packed_depth_stencil;
	public boolean GL_NV_point_sprite;
	public boolean GL_NV_register_combiners;
	public boolean GL_NV_register_combiners2;
	public boolean GL_NV_texgen_reflection;
	public boolean GL_NV_texture_env_combine4;
	public boolean GL_NV_texture_rectangle;
	public boolean GL_NV_texture_shader;
	public boolean GL_NV_texture_shader2;
	public boolean GL_NV_texture_shader3;
	public boolean GL_NV_vertex_array_range;
	public boolean GL_NV_vertex_array_range2;
	public boolean GL_NV_vertex_program;
	public boolean GL_NV_vertex_program1_1;
	public boolean GL_SGIS_generate_mipmap;
	public boolean GL_SGIX_shadow;
	public boolean GL_SGIX_depth_texture;
	public boolean OpenGL10;
	public boolean OpenGL11;
	public boolean OpenGL12;
	public boolean OpenGL13;
	public boolean OpenGL14;

	/*
	 * Available WGL extensions
	 */
	public boolean WGL_ARB_buffer_region;
	public boolean WGL_ARB_extensions_string;
	public boolean WGL_ARB_pbuffer;
	public boolean WGL_ARB_pixel_format;
	public boolean WGL_ARB_render_texture;
	public boolean WGL_EXT_extensions_string;
	public boolean WGL_EXT_swap_control;
		
	/**
	 * Determine which extensions are available
	 */
	private void determineAvailableExtensions() {
		if (Display.getPlatform() == Display.PLATFORM_WGL)
			determineAvailableWGLExtensions();

		// Grab all the public booleans out of this class
		Field[] fields = GLWindow.class.getDeclaredFields();
		HashMap map = new HashMap(fields.length);
		for (int i = 0; i < fields.length; i++) {
			if (!Modifier.isStatic(fields[i].getModifiers())
				&& fields[i].getType() == boolean.class)
				map.put(fields[i].getName(), fields[i]);
		}

		String exts = CoreGL11.glGetString(CoreGL11.GL_EXTENSIONS);
		StringTokenizer st = new StringTokenizer(exts);
		while (st.hasMoreTokens()) {
			String ext = st.nextToken();
			Field f = (Field) map.get(ext);
			if (f != null) {
				//System.out.println("Extension : "+ext+" : present");
				try {
					f.setBoolean(this, true);
				} catch (IllegalAccessException e) {
					e.printStackTrace(System.err);
				}
//		      } else {
//			      System.out.println("Extension : "+ext+" : NOT AVAILABLE");
			}

		}

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

	/**
	 * Checks and sets WGL_EXT_extensions_string and WGL_ARB_extensions_string
	 * if available.
	 */
	private native void checkWGLExtensionsString();

	/**
	 * Determine which WGL extensions are available
	 */
	private void determineAvailableWGLExtensions() {

		// First we must determine if WGL_EXT_extensions_string is available
		checkWGLExtensionsString();
		if (!WGL_EXT_extensions_string && !WGL_ARB_extensions_string)
			return;

		// Grab all the public booleans out of this class
		Field[] fields = GLWindow.class.getDeclaredFields();
		HashMap map = new HashMap(fields.length);
		for (int i = 0; i < fields.length; i++) {
			if (!Modifier.isStatic(fields[i].getModifiers())
				&& fields[i].getType() == boolean.class)
				map.put(fields[i].getName(), fields[i]);
		}

		final String exts;

		if (WGL_ARB_extensions_string)
			exts = GL.wglGetExtensionsStringARB(getHandle());
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
		StringTokenizer st = new StringTokenizer(exts);
		while (st.hasMoreTokens()) {
			String ext = st.nextToken();

			if(org.lwjgl.Sys.DEBUG) {
				System.out.println(ext);
			}

			Field f = (Field) map.get(ext);
			if (f != null) {
				try {
					f.setBoolean(this, true);
				} catch (IllegalAccessException e) {
					e.printStackTrace(System.err);
				}
			}

		}
	}

	static {
		System.loadLibrary(Sys.getLibraryName());
	}

	/**
	 * Construct a windowed context. If the underlying OS does not
	 * support windowed mode, then the width and height must match the current
	 * display resolution, or an Exception will be thrown. Otherwise a fullscreen
	 * window will be created.
	 * 
	 * @param title The title of the window
	 * @param x The position of the window on the x axis. May be ignored.
	 * @param y The position of the window on the y axis. May be ignored.
	 * @param width The width of the window's client area
	 * @param height The height of the window's client area
	 * @param bpp Require colour bits
	 * @param alpha Required alpha bits
	 * @param depth Required depth bits
	 * @param stencil Required stencil bits
	 */
	public GLWindow(String title, int x, int y, int width, int height, int bpp, int alpha, int depth, int stencil) {
		super(title, x, y, width, height);
		
		this.x = x;
		this.y = y;
		this.color = bpp;
		this.alpha = alpha;
		this.depth = depth;
		this.stencil = stencil;
		this.fullscreen = false;
	}

	/**
	 * Construct a fullscreen context. If the underlying OS does not
	 * support fullscreen mode, then a window will be created instead. If this
	 * fails too then an Exception will be thrown.
	 * 
	 * @param title The title of the window
	 * @param bpp Minimum bits per pixel
	 * @param alpha Minimum bits per pixel in alpha buffer
	 * @param depth Minimum bits per pixel in depth buffer
	 * @param stencil Minimum bits per pixel in stencil buffer
	 */
	public GLWindow(String title, int bpp, int alpha, int depth, int stencil) {
		super(title, 0, 0, Display.getWidth(), Display.getHeight());
		
		this.x = 0;
		this.y = 0;
		this.color = bpp;
		this.alpha = alpha;
		this.depth = depth;
		this.stencil = stencil;
		this.fullscreen = true;

	}
	
	protected void doCreate() throws Exception {
		nCreate(getTitle(), x, y, getWidth(), getHeight(), color, alpha, depth, stencil, fullscreen);
		determineAvailableExtensions();
	}

	protected void doPaint() {
		swapBuffers();
	}

        /**
         * Swap the buffers.
         */
        private native void swapBuffers();
	
	/**
	 * Native method to create a windowed GL
	 */
	private native void nCreate(
		String title,
		int x,
		int y,
		int width,
		int height,
		int bpp,
		int alpha,
		int depth,
		int stencil,
		boolean fullscreen) throws Exception;
	
	/* (non-Javadoc)
	 * @see org.lwjgl.Window#doDestroy()
	 */
	protected void doDestroy() {
		nDestroyGL();
	}

	/**
	 * Natively destroy the context
	 */
	private native void nDestroyGL();
}
