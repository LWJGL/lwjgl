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

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.StringTokenizer;

import java.lang.reflect.Method;

import org.lwjgl.Sys;
import org.lwjgl.LWJGLException;

/**
 * $Id$
 * <p/>
 * Manages GL contexts. Before any rendering is done by a LWJGL system, a call should be made to
 * GLContext.setContext() with a context. This will ensure that GLContext has an accurate reflection
 * of the current context's capabilities and function pointers.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */
public final class GLContext {

	/** The currently initialised context */
	private static WeakReference currentContext;

	/*
	 * Available extensions
	 */
	public static boolean GL_ARB_imaging;
	public static boolean GL_ARB_depth_texture;
	public static boolean GL_ARB_fragment_program;
	public static boolean GL_ARB_fragment_program_shadow;
	public static boolean GL_ARB_fragment_shader;
	public static boolean GL_ARB_matrix_palette;
	public static boolean GL_ARB_multisample;
	public static boolean GL_ARB_multitexture;
	public static boolean GL_ARB_occlusion_query;
	public static boolean GL_ARB_point_parameters;
	public static boolean GL_ARB_point_sprite;
	public static boolean GL_ARB_shading_language_100;
	public static boolean GL_ARB_shader_objects;
	public static boolean GL_ARB_shadow;
	public static boolean GL_ARB_shadow_ambient;
	public static boolean GL_ARB_texture_border_clamp;
	public static boolean GL_ARB_texture_compression;
	public static boolean GL_ARB_texture_cube_map;
	public static boolean GL_ARB_texture_env_add;
	public static boolean GL_ARB_texture_env_combine;
	public static boolean GL_ARB_texture_env_crossbar;
	public static boolean GL_ARB_texture_env_dot3;
	public static boolean GL_ARB_texture_mirrored_repeat;
	public static boolean GL_ARB_texture_non_power_of_two;
	public static boolean GL_ARB_transpose_matrix;
	public static boolean GL_ARB_vertex_blend;
	public static boolean GL_ARB_vertex_buffer_object;
	public static boolean GL_ARB_vertex_program;
	public static boolean GL_ARB_vertex_shader;
	public static boolean GL_ARB_window_pos;

	public static boolean GL_EXT_abgr;
	public static boolean GL_EXT_bgra;
	public static boolean GL_EXT_blend_equation_separate;
	public static boolean GL_EXT_blend_func_separate;
	public static boolean GL_EXT_blend_subtract;
	public static boolean GL_EXT_Cg_shader;
	public static boolean GL_EXT_compiled_vertex_array;
	public static boolean GL_EXT_depth_bounds_test;
	public static boolean GL_EXT_draw_range_elements;
	public static boolean GL_EXT_fog_coord;
	public static boolean GL_EXT_multi_draw_arrays;
	public static boolean GL_EXT_packed_pixels;
	public static boolean GL_EXT_pixel_buffer_object;
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
	public static boolean GL_EXT_texture_mirror_clamp;
	public static boolean GL_EXT_texture_rectangle;
	public static boolean GL_EXT_vertex_shader;
	public static boolean GL_EXT_vertex_weighting;

	public static boolean GL_ATI_draw_buffers;
	public static boolean GL_ATI_element_array;
	public static boolean GL_ATI_envmap_bumpmap;
	public static boolean GL_ATI_fragment_shader;
	public static boolean GL_ATI_map_object_buffer;
	public static boolean GL_ATI_pn_triangles;
	public static boolean GL_ATI_separate_stencil;
	public static boolean GL_ATI_texture_float;
	public static boolean GL_ATI_texture_mirror_once;
	public static boolean GL_ATI_vertex_array_object;
	public static boolean GL_ATI_vertex_streams;
	public static boolean GL_ATI_vertex_attrib_array_object;

	public static boolean GL_NV_blend_square;
	public static boolean GL_NV_copy_depth_to_color;
	public static boolean GL_NV_depth_clamp;
	public static boolean GL_NV_evaluators;
	public static boolean GL_NV_fence;
	public static boolean GL_NV_float_buffer;
	public static boolean GL_NV_fog_distance;
	public static boolean GL_NV_fragment_program;
	public static boolean GL_NV_fragment_program2;
	public static boolean GL_NV_fragment_program_option;
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
	public static boolean GL_NV_vertex_program2_option;
	public static boolean GL_NV_vertex_program3;

	public static boolean OpenGL11;
	public static boolean OpenGL12;
	public static boolean OpenGL13;
	public static boolean OpenGL14;
	public static boolean OpenGL15;

	/** Map of classes that have native stubs loaded */
	private static Map exts;
	private static int gl_ref_count = 0;
	private static boolean did_auto_load = false;

	static {
		Sys.initialize();
	}

	/**
	 * Determine which extensions are available. Use this to initialize capability fields. Can only be
	 * called _after_ the Display context or a Pbuffer has been created (or a context from some other GL library).
	 * Using LWJGL, this method is called automatically for you when the LWJGL Window is created and there
	 * is no need to call it yourself.
	 *
	 * @param exts A Set of OpenGL extension string names
	 */
	private static void determineAvailableExtensions(Set exts) {
		// Grab all the public static booleans out of this class
		Field[] fields = GLContext.class.getDeclaredFields();
		for ( int i = 0; i < fields.length; i++ ) {
			if ( Modifier.isStatic(fields[i].getModifiers()) && fields[i].getType().equals(boolean.class) ) {
				// reset fields
				try {
					fields[i].setBoolean(GLContext.class, exts.contains(fields[i].getName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	/**
	 * Makes a GL context the current LWJGL context by loading GL function pointers.
	 * The context must be current before a call to this method!
	 * Instead it simply ensures that the current context is reflected accurately by GLContext's
	 * extension caps and function pointers. Use useContext(null) when no context is active.
	 * <p>If the context is the same as last time, then this is a no-op.
	 * <p>If the context has not been encountered before it will be fully initialized from scratch.
	 * Otherwise a cached set of caps and function pointers will be used.
	 * <p>The reference to the context is held in a weak reference; therefore if no strong reference
	 * exists to the GL context it will automatically be forgotten by the VM at an indeterminate point
	 * in the future, freeing up a little RAM.
	 * @param context The context object, which uniquely identifies a GL context. If context is null,
	 * 		  the native stubs are unloaded.
	 * @throws LWJGLException if context non-null, and the gl library can't be loaded or the basic GL11 functions can't be loaded
	 */
	public static synchronized void useContext(Object context) throws LWJGLException {
		if (context == null) {
			unloadStubs();
			if (did_auto_load)
				unloadOpenGLLibrary();
			return;
		}
		// Is this the same as last time?
		Object current = currentContext == null ? null : currentContext.get();
		if (current == context) {
			// Yes, so we don't need to do anything. Our caps and function pointers are still valid.
			return;
		}
		
		// Ok, now it's the current context.
		if (gl_ref_count == 0) {
			loadOpenGLLibrary();
			did_auto_load = true;
		}
		try {
			GL11.initNativeStubs();
			loadStubs();
			currentContext = new WeakReference(context);
			VBOTracker.setCurrent(currentContext);
		} catch (LWJGLException e) {
			if (did_auto_load)
				unloadOpenGLLibrary();
			throw e;
		}
	}

	private static void getExtensionClassesAndNames(Map exts, Set exts_names) {
		String version_string = GL11.glGetString(GL11.GL_VERSION);
		int version_index = version_string.indexOf("1.");
		if (version_index != -1) {
			String version = version_string.substring(version_index);
			char minor_version = version_string.charAt(2);
			switch (minor_version) {
				case '5':
					addExtensionClass(exts, exts_names, "GL15", "OpenGL15");
					// Fall through
				case '4':
					addExtensionClass(exts, exts_names, "GL14", "OpenGL14");
					// Fall through
				case '3':
					addExtensionClass(exts, exts_names, "GL13", "OpenGL13");
					// Fall through
				case '2':
					addExtensionClass(exts, exts_names, "GL12", "OpenGL12");
					// Fall through
				default:
					break;
			}
				
		}
		addExtensionClass(exts, exts_names, "EXTTextureCompressionS3TC", "");
		String extensions_string = GL11.glGetString(GL11.GL_EXTENSIONS);
		StringTokenizer tokenizer = new StringTokenizer(extensions_string);
		while (tokenizer.hasMoreTokens()) {
			String extension_string = tokenizer.nextToken();
			StringBuffer converted_name = new StringBuffer();
			int gl_prefix_index = extension_string.indexOf("GL_");
			if (gl_prefix_index == -1)
				continue;
			if (extension_string.equals("GL_EXT_texture_compression_s3tc")) {
				// Special workaround
				addExtensionClass(exts, exts_names, "EXTTextureCompressionS3TC", "GL_EXT_texture_compression_s3tc");
			} else if (extension_string.equals("GL_EXT_texture_lod_bias")) {
				// Special workaround
				addExtensionClass(exts, exts_names, "EXTTextureLODBias", "GL_EXT_texture_lod_bias");
			} else if (extension_string.equals("GL_NV_texture_compression_vtc")) {
				// Special workaround
				addExtensionClass(exts, exts_names, "NVTextureCompressionVTC", "GL_NV_texture_compression_vtc");
			} else {
				for (int i = gl_prefix_index + 3; i < extension_string.length(); i++) {
					char c;
					if (extension_string.charAt(i) == '_') {
						i++;
						c = Character.toUpperCase(extension_string.charAt(i));
					} else
						c = extension_string.charAt(i);
					converted_name.append(c);
				}
				addExtensionClass(exts, exts_names, converted_name.toString(), extension_string);
			}
		}
		addExtensionClass(exts, exts_names, "ARBBufferObject", null);
		addExtensionClass(exts, exts_names, "ARBProgram", null);
		addExtensionClass(exts, exts_names, "NVProgram", null);
	}

	private static void addExtensionClass(Map exts, Set exts_names, String ext_class_name, String ext_name) {
		if (ext_name != null) {
			if (exts_names.contains(ext_name)) {
				// Already added; ignore
				return;
			}
			exts_names.add(ext_name);
		}
		try {
			Class extension_class = Class.forName("org.lwjgl.opengl." + ext_class_name);
			extension_class.getDeclaredMethod("initNativeStubs", null); // check for existance of initNativeStubs method
			exts.put(extension_class, ext_name);
		} catch (ClassNotFoundException e) {
			// ignore
		} catch (NoSuchMethodException e) {
			// ignore
		}
	}

	private static void loadStubs() {
		exts = new HashMap();
		Set exts_names = new HashSet();
		getExtensionClassesAndNames(exts, exts_names);
		Iterator exts_it = exts.keySet().iterator();
		while (exts_it.hasNext()) {
			Class extension_class = (Class)exts_it.next();
			resetNativeStubs(extension_class);
			try {
				Method init_stubs_method = extension_class.getDeclaredMethod("initNativeStubs", null);
				init_stubs_method.invoke(null, null);
				String ext_name = (String)exts.get(extension_class);
			} catch (Exception e) {
				Sys.log("Failed to initialize extension " + extension_class);
				exts_it.remove();
				exts_names.remove(exts.get(extension_class));
			}
		}
		determineAvailableExtensions(exts_names);
	}

	private static void unloadStubs() {
		Iterator exts_it = exts.keySet().iterator();
		while (exts_it.hasNext()) {
			Class ext_class = (Class)exts_it.next();
			resetNativeStubs(ext_class);
		}
		resetNativeStubs(org.lwjgl.opengl.GL11.class);
	}

	/**
	 * If the OpenGL reference count is 0, the library is loaded. The
	 * reference count is then incremented.
	 */
	public static void loadOpenGLLibrary() throws LWJGLException {
		if (gl_ref_count == 0)
			nLoadOpenGLLibrary();
		gl_ref_count++;
	}

	private static native void nLoadOpenGLLibrary() throws LWJGLException;

	/**
	 * The OpenGL library reference count is decremented, and if it 
	 * reaches 0, the library is unloaded.
	 */
	public static void unloadOpenGLLibrary() {
		gl_ref_count--;
		if (gl_ref_count == 0)
			nUnloadOpenGLLibrary();
	}

	private static native void nUnloadOpenGLLibrary();

	/**
	 * Native method to clear native stub bindings
	 */
	private static native void resetNativeStubs(Class clazz);
}
