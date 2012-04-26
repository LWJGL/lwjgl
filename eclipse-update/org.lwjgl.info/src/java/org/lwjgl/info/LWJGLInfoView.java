/*******************************************************************************
 * Copyright (c) 2011 LWJGL Project and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html, and under the terms of the 
 * BSD license, see http://lwjgl.org/license.php for details.
 *
 * Contributors:
 *    Jens von Pilgrim - initial implementation
 ******************************************************************************/

package org.lwjgl.info;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eclipse.core.runtime.IBundleGroup;
import org.eclipse.core.runtime.IBundleGroupProvider;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.opengl.GLData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Registry;

/**
 * LWJGLInfoView
 * There should really be more documentation here.
 *
 * @author 	Jens von Pilgrim
 * @version	$Revision$
 * @since 	Nov 23, 2010
 */
public class LWJGLInfoView extends ViewPart {

	static final String NL = System.getProperty("line.separator", "\n");

	static final int TAB = 16;

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite i_parent) {

		Text info = new Text(i_parent, SWT.READ_ONLY | SWT.LEFT | SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL);

		GLCanvas canvas = new GLCanvas(i_parent, SWT.NONE, new GLData());
		canvas.setCurrent();
		try {
			GLContext.useContext(canvas);
		} catch (LWJGLException ex) {
			// TODO Implement catch block for LWJGLException
			ex.printStackTrace();
		}

		String infoString = gatherInformation();
		info.setText(infoString);
	}

	static String getFeatureVersion(String myFeatureId) {
		IBundleGroupProvider[] providers = Platform.getBundleGroupProviders();
		if (providers != null) {
			for (int i = 0; i < providers.length; ++i) {
				IBundleGroup[] bundleGroups = providers[i].getBundleGroups();
				for (IBundleGroup bg : bundleGroups) {
					if (bg.getIdentifier().equals(myFeatureId)) {
						return bg.getVersion();
					}
				}
			}
		}
		return "Feature not found";
	}

	/**
	 * @param i_canvas 
	 * @return
	 */
	private static String gatherInformation() {
		StringBuffer strb = new StringBuffer();

		try {
			infoNL(strb, "LWJGL feature version",
					getFeatureVersion("org.lwjgl"));
		} catch (Exception ex) {
			warnNL(strb, "Error retrieving feature version: " + ex.getMessage());
		}

		infoNL(strb, "LWJGL version", Sys.getVersion()
				+ (Sys.is64Bit() ? " (64bit)" : ""));

		infoNL(strb, "Java", System.getProperty("java.version"),
				System.getProperty("java.vendor"));
		infoNL(strb, "Platform", LWJGLUtil.getPlatformName());
		infoNL(strb, "Graphics card", Display.getAdapter());
		infoNL(strb, "Driver version", Display.getVersion());
		infoNL(strb, "OpenGL driver version", GL11.glGetString(GL11.GL_VERSION));

		infoNL(strb, "GLU version", Registry.gluGetString(GLU.GLU_VERSION));
		infoNL(strb, "GLU extensions",
				Registry.gluGetString(GLU.GLU_EXTENSIONS));

		ContextCapabilities caps = GLContext.getCapabilities();
		openGLVersions(strb, caps);

		strb.append(NL).append("Capabilities").append(NL);
		TreeMap<String, Boolean> capInfos = new TreeMap<String, Boolean>();
		gatherCapabilities(caps, capInfos);
		infoGroupedCaps(strb, capInfos);

		Display.destroy();

		return strb.toString();
	}

	static void openGLVersions(StringBuffer strb, ContextCapabilities caps) {
		StringBuffer versions = new StringBuffer();
		if (caps.OpenGL11)
			versions.append(", 1.1");
		if (caps.OpenGL12)
			versions.append(", 1.2");
		if (caps.OpenGL13)
			versions.append(", 1.3");
		if (caps.OpenGL14)
			versions.append(", 1.4");
		if (caps.OpenGL15)
			versions.append(", 1.5");
		if (caps.OpenGL20)
			versions.append(", 2.0");
		if (caps.OpenGL21)
			versions.append(", 2.1");
		if (caps.OpenGL30)
			versions.append(", 3.0");
		if (caps.OpenGL31)
			versions.append(", 3.1");
		if (caps.OpenGL32)
			versions.append(", 3.2");
		if (caps.OpenGL33)
			versions.append(", 3.3");
		if (caps.OpenGL40)
			versions.append(", 4.0");
		if (caps.OpenGL41)
			versions.append(", 4.1");
		if (strb.length() > 2)
			versions.delete(0, 2);
		infoNL(strb, "Supported OpenGL versions", versions.toString());
	}

	/**
	 * @param i_strb
	 * @param i_capInfos
	 */
	static void infoGroupedCaps(StringBuffer strb,
			TreeMap<String, Boolean> capInfos) {
		String currentGroup = "*NOGROUP*";
		Set<String> groupInfosSupported = new TreeSet<String>();
		Set<String> groupInfosNA = new TreeSet<String>();
		for (String key : capInfos.keySet()) {
			if (!key.startsWith(currentGroup)) {
				printGroup(strb, currentGroup, groupInfosSupported,
						groupInfosNA);
				currentGroup = key.substring(0, key.indexOf(' '));
			}
			if (capInfos.get(key).booleanValue()) {
				groupInfosSupported.add(key);
			} else {
				groupInfosNA.add(key);
			}
		}
		printGroup(strb, currentGroup, groupInfosSupported, groupInfosNA);

	}

	/**
	 * @param i_currentGroup
	 * @param i_groupInfosSupported
	 * @param i_groupInfosNA
	 */
	private static void printGroup(StringBuffer strb, String currentGroup,
			Set<String> groupInfosSupported, Set<String> groupInfosNA) {
		String title = currentGroup + " extensions";
		if (!(groupInfosSupported.isEmpty() && groupInfosNA.isEmpty())) {
			if (groupInfosSupported.isEmpty()) {
				infoNL(strb, title, "n.a.");
			} else {

				strb.append(title).append(": ");
				for (int i = 0; i < TAB - title.length(); i++) {
					strb.append(' ');
				}
				int c = 1;
				for (String s : groupInfosSupported) {
					if (c > 1)
						strb.append(',');
					if (c % 4 == 0) {
						strb.append(NL).append("    ");
					} else if (c > 1) {
						strb.append(' ');
					}
					strb.append(s.substring(currentGroup.length() + 1));
					c++;
				}
				strb.append(NL);
			}

		}
		groupInfosNA.clear();
		groupInfosSupported.clear();

	}

	/**
	 * @param caps
	 * @param capInfos
	 */
	private static void gatherCapabilities(ContextCapabilities caps,
			Map<String, Boolean> capInfos) {
		capInfos.put("AMD conservative depth", caps.GL_AMD_conservative_depth);
		capInfos.put("AMD debug output", caps.GL_AMD_debug_output);
		capInfos.put("AMD draw buffers blend", caps.GL_AMD_draw_buffers_blend);
		capInfos.put("AMD name gen delete", caps.GL_AMD_name_gen_delete);
		capInfos.put("AMD performance monitor", caps.GL_AMD_performance_monitor);
		capInfos.put("AMD seamless cubemap per texture",
				caps.GL_AMD_seamless_cubemap_per_texture);
		capInfos.put("AMD shader stencil export",
				caps.GL_AMD_shader_stencil_export);
		capInfos.put("AMD texture texture4", caps.GL_AMD_texture_texture4);
		capInfos.put("AMD transform feedback3 lines triangles",
				caps.GL_AMD_transform_feedback3_lines_triangles);
		capInfos.put("AMD vertex shader tessellator",
				caps.GL_AMD_vertex_shader_tessellator);
		capInfos.put("APPLE aux depth stencil", caps.GL_APPLE_aux_depth_stencil);
		capInfos.put("APPLE client storage", caps.GL_APPLE_client_storage);
		capInfos.put("APPLE element array", caps.GL_APPLE_element_array);
		capInfos.put("APPLE fence", caps.GL_APPLE_fence);
		capInfos.put("APPLE float pixels", caps.GL_APPLE_float_pixels);
		capInfos.put("APPLE flush buffer range",
				caps.GL_APPLE_flush_buffer_range);
		capInfos.put("APPLE object purgeable", caps.GL_APPLE_object_purgeable);
		capInfos.put("APPLE packed pixels", caps.GL_APPLE_packed_pixels);
		capInfos.put("APPLE rgb 422", caps.GL_APPLE_rgb_422);
		capInfos.put("APPLE row bytes", caps.GL_APPLE_row_bytes);
		capInfos.put("APPLE texture range", caps.GL_APPLE_texture_range);
		capInfos.put("APPLE vertex array object",
				caps.GL_APPLE_vertex_array_object);
		capInfos.put("APPLE vertex array range",
				caps.GL_APPLE_vertex_array_range);
		capInfos.put("APPLE vertex program evaluators",
				caps.GL_APPLE_vertex_program_evaluators);
		capInfos.put("APPLE ycbcr 422", caps.GL_APPLE_ycbcr_422);
		capInfos.put("ARB ES2 compatibility", caps.GL_ARB_ES2_compatibility);
		capInfos.put("ARB blend func extended", caps.GL_ARB_blend_func_extended);
		capInfos.put("ARB cl event", caps.GL_ARB_cl_event);
		capInfos.put("ARB color buffer float", caps.GL_ARB_color_buffer_float);
		capInfos.put("ARB compatibility", caps.GL_ARB_compatibility);
		capInfos.put("ARB copy buffer", caps.GL_ARB_copy_buffer);
		capInfos.put("ARB debug output", caps.GL_ARB_debug_output);
		capInfos.put("ARB depth buffer float", caps.GL_ARB_depth_buffer_float);
		capInfos.put("ARB depth clamp", caps.GL_ARB_depth_clamp);
		capInfos.put("ARB depth texture", caps.GL_ARB_depth_texture);
		capInfos.put("ARB draw buffers", caps.GL_ARB_draw_buffers);
		capInfos.put("ARB draw buffers blend", caps.GL_ARB_draw_buffers_blend);
		capInfos.put("ARB draw elements base vertex",
				caps.GL_ARB_draw_elements_base_vertex);
		capInfos.put("ARB draw indirect", caps.GL_ARB_draw_indirect);
		capInfos.put("ARB draw instanced", caps.GL_ARB_draw_instanced);
		capInfos.put("ARB explicit attrib location",
				caps.GL_ARB_explicit_attrib_location);
		capInfos.put("ARB fragment coord conventions",
				caps.GL_ARB_fragment_coord_conventions);
		capInfos.put("ARB fragment program", caps.GL_ARB_fragment_program);
		capInfos.put("ARB fragment program shadow",
				caps.GL_ARB_fragment_program_shadow);
		capInfos.put("ARB fragment shader", caps.GL_ARB_fragment_shader);
		capInfos.put("ARB framebuffer object", caps.GL_ARB_framebuffer_object);
		capInfos.put("ARB framebuffer sRGB", caps.GL_ARB_framebuffer_sRGB);
		capInfos.put("ARB geometry shader4", caps.GL_ARB_geometry_shader4);
		capInfos.put("ARB get program binary", caps.GL_ARB_get_program_binary);
		capInfos.put("ARB gpu shader5", caps.GL_ARB_gpu_shader5);
		capInfos.put("ARB gpu shader fp64", caps.GL_ARB_gpu_shader_fp64);
		capInfos.put("ARB half float pixel", caps.GL_ARB_half_float_pixel);
		capInfos.put("ARB half float vertex", caps.GL_ARB_half_float_vertex);
		capInfos.put("ARB imaging", caps.GL_ARB_imaging);
		capInfos.put("ARB instanced arrays", caps.GL_ARB_instanced_arrays);
		capInfos.put("ARB map buffer range", caps.GL_ARB_map_buffer_range);
		capInfos.put("ARB matrix palette", caps.GL_ARB_matrix_palette);
		capInfos.put("ARB multisample", caps.GL_ARB_multisample);
		capInfos.put("ARB multitexture", caps.GL_ARB_multitexture);
		capInfos.put("ARB occlusion query", caps.GL_ARB_occlusion_query);
		capInfos.put("ARB occlusion query2", caps.GL_ARB_occlusion_query2);
		capInfos.put("ARB pixel buffer object", caps.GL_ARB_pixel_buffer_object);
		capInfos.put("ARB point parameters", caps.GL_ARB_point_parameters);
		capInfos.put("ARB point sprite", caps.GL_ARB_point_sprite);
		capInfos.put("ARB provoking vertex", caps.GL_ARB_provoking_vertex);
		capInfos.put("ARB robustness", caps.GL_ARB_robustness);
		capInfos.put("ARB sample shading", caps.GL_ARB_sample_shading);
		capInfos.put("ARB sampler objects", caps.GL_ARB_sampler_objects);
		capInfos.put("ARB seamless cube map", caps.GL_ARB_seamless_cube_map);
		capInfos.put("ARB separate shader objects",
				caps.GL_ARB_separate_shader_objects);
		capInfos.put("ARB shader bit encoding", caps.GL_ARB_shader_bit_encoding);
		capInfos.put("ARB shader objects", caps.GL_ARB_shader_objects);
		capInfos.put("ARB shader precision", caps.GL_ARB_shader_precision);
		capInfos.put("ARB shader stencil export",
				caps.GL_ARB_shader_stencil_export);
		capInfos.put("ARB shader subroutine", caps.GL_ARB_shader_subroutine);
		capInfos.put("ARB shader texture lod", caps.GL_ARB_shader_texture_lod);
		capInfos.put("ARB shading language 100",
				caps.GL_ARB_shading_language_100);
		capInfos.put("ARB shading language include",
				caps.GL_ARB_shading_language_include);
		capInfos.put("ARB shadow", caps.GL_ARB_shadow);
		capInfos.put("ARB shadow ambient", caps.GL_ARB_shadow_ambient);
		capInfos.put("ARB sync", caps.GL_ARB_sync);
		capInfos.put("ARB tessellation shader", caps.GL_ARB_tessellation_shader);
		capInfos.put("ARB texture border clamp",
				caps.GL_ARB_texture_border_clamp);
		capInfos.put("ARB texture buffer object",
				caps.GL_ARB_texture_buffer_object);
		capInfos.put("ARB texture buffer object rgb32",
				caps.GL_ARB_texture_buffer_object_rgb32);
		capInfos.put("ARB texture compression", caps.GL_ARB_texture_compression);
		capInfos.put("ARB texture compression bptc",
				caps.GL_ARB_texture_compression_bptc);
		capInfos.put("ARB texture compression rgtc",
				caps.GL_ARB_texture_compression_rgtc);
		capInfos.put("ARB texture cube map", caps.GL_ARB_texture_cube_map);
		capInfos.put("ARB texture cube map array",
				caps.GL_ARB_texture_cube_map_array);
		capInfos.put("ARB texture env add", caps.GL_ARB_texture_env_add);
		capInfos.put("ARB texture env combine", caps.GL_ARB_texture_env_combine);
		capInfos.put("ARB texture env crossbar",
				caps.GL_ARB_texture_env_crossbar);
		capInfos.put("ARB texture env dot3", caps.GL_ARB_texture_env_dot3);
		capInfos.put("ARB texture float", caps.GL_ARB_texture_float);
		capInfos.put("ARB texture gather", caps.GL_ARB_texture_gather);
		capInfos.put("ARB texture mirrored repeat",
				caps.GL_ARB_texture_mirrored_repeat);
		capInfos.put("ARB texture multisample", caps.GL_ARB_texture_multisample);
		capInfos.put("ARB texture non power of two",
				caps.GL_ARB_texture_non_power_of_two);
		capInfos.put("ARB texture query lod", caps.GL_ARB_texture_query_lod);
		capInfos.put("ARB texture rectangle", caps.GL_ARB_texture_rectangle);
		capInfos.put("ARB texture rg", caps.GL_ARB_texture_rg);
		capInfos.put("ARB texture rgb10 a2ui", caps.GL_ARB_texture_rgb10_a2ui);
		capInfos.put("ARB texture swizzle", caps.GL_ARB_texture_swizzle);
		capInfos.put("ARB timer query", caps.GL_ARB_timer_query);
		capInfos.put("ARB transform feedback2", caps.GL_ARB_transform_feedback2);
		capInfos.put("ARB transform feedback3", caps.GL_ARB_transform_feedback3);
		capInfos.put("ARB transpose matrix", caps.GL_ARB_transpose_matrix);
		capInfos.put("ARB uniform buffer object",
				caps.GL_ARB_uniform_buffer_object);
		capInfos.put("ARB vertex array bgra", caps.GL_ARB_vertex_array_bgra);
		capInfos.put("ARB vertex array object", caps.GL_ARB_vertex_array_object);
		capInfos.put("ARB vertex attrib 64bit", caps.GL_ARB_vertex_attrib_64bit);
		capInfos.put("ARB vertex blend", caps.GL_ARB_vertex_blend);
		capInfos.put("ARB vertex buffer object",
				caps.GL_ARB_vertex_buffer_object);
		capInfos.put("ARB vertex program", caps.GL_ARB_vertex_program);
		capInfos.put("ARB vertex shader", caps.GL_ARB_vertex_shader);
		capInfos.put("ARB vertex type 2 10 10 10 rev",
				caps.GL_ARB_vertex_type_2_10_10_10_rev);
		capInfos.put("ARB viewport array", caps.GL_ARB_viewport_array);
		capInfos.put("ARB window pos", caps.GL_ARB_window_pos);
		capInfos.put("ATI draw buffers", caps.GL_ATI_draw_buffers);
		capInfos.put("ATI element array", caps.GL_ATI_element_array);
		capInfos.put("ATI envmap bumpmap", caps.GL_ATI_envmap_bumpmap);
		capInfos.put("ATI fragment shader", caps.GL_ATI_fragment_shader);
		capInfos.put("ATI map object buffer", caps.GL_ATI_map_object_buffer);
		capInfos.put("ATI meminfo", caps.GL_ATI_meminfo);
		capInfos.put("ATI pn triangles", caps.GL_ATI_pn_triangles);
		capInfos.put("ATI separate stencil", caps.GL_ATI_separate_stencil);
		capInfos.put("ATI shader texture lod", caps.GL_ATI_shader_texture_lod);
		capInfos.put("ATI text fragment shader",
				caps.GL_ATI_text_fragment_shader);
		capInfos.put("ATI texture compression 3dc",
				caps.GL_ATI_texture_compression_3dc);
		capInfos.put("ATI texture env combine3",
				caps.GL_ATI_texture_env_combine3);
		capInfos.put("ATI texture float", caps.GL_ATI_texture_float);
		capInfos.put("ATI texture mirror once", caps.GL_ATI_texture_mirror_once);
		capInfos.put("ATI vertex array object", caps.GL_ATI_vertex_array_object);
		capInfos.put("ATI vertex attrib array object",
				caps.GL_ATI_vertex_attrib_array_object);
		capInfos.put("ATI vertex streams", caps.GL_ATI_vertex_streams);
		capInfos.put("EXT abgr", caps.GL_EXT_abgr);
		capInfos.put("EXT bgra", caps.GL_EXT_bgra);
		capInfos.put("EXT bindable uniform", caps.GL_EXT_bindable_uniform);
		capInfos.put("EXT blend color", caps.GL_EXT_blend_color);
		capInfos.put("EXT blend equation separate",
				caps.GL_EXT_blend_equation_separate);
		capInfos.put("EXT blend func separate", caps.GL_EXT_blend_func_separate);
		capInfos.put("EXT blend minmax", caps.GL_EXT_blend_minmax);
		capInfos.put("EXT blend subtract", caps.GL_EXT_blend_subtract);
		capInfos.put("EXT Cg shader", caps.GL_EXT_Cg_shader);
		capInfos.put("EXT compiled vertex array",
				caps.GL_EXT_compiled_vertex_array);
		capInfos.put("EXT depth bounds test", caps.GL_EXT_depth_bounds_test);
		capInfos.put("EXT direct state access", caps.GL_EXT_direct_state_access);
		capInfos.put("EXT draw buffers2", caps.GL_EXT_draw_buffers2);
		capInfos.put("EXT draw instanced", caps.GL_EXT_draw_instanced);
		capInfos.put("EXT draw range elements", caps.GL_EXT_draw_range_elements);
		capInfos.put("EXT fog coord", caps.GL_EXT_fog_coord);
		capInfos.put("EXT framebuffer blit", caps.GL_EXT_framebuffer_blit);
		capInfos.put("EXT framebuffer multisample",
				caps.GL_EXT_framebuffer_multisample);
		capInfos.put("EXT framebuffer object", caps.GL_EXT_framebuffer_object);
		capInfos.put("EXT framebuffer sRGB", caps.GL_EXT_framebuffer_sRGB);
		capInfos.put("EXT geometry shader4", caps.GL_EXT_geometry_shader4);
		capInfos.put("EXT gpu program parameters",
				caps.GL_EXT_gpu_program_parameters);
		capInfos.put("EXT gpu shader4", caps.GL_EXT_gpu_shader4);
		capInfos.put("EXT multi draw arrays", caps.GL_EXT_multi_draw_arrays);
		capInfos.put("EXT packed depth stencil",
				caps.GL_EXT_packed_depth_stencil);
		capInfos.put("EXT packed float", caps.GL_EXT_packed_float);
		capInfos.put("EXT packed pixels", caps.GL_EXT_packed_pixels);
		capInfos.put("EXT paletted texture", caps.GL_EXT_paletted_texture);
		capInfos.put("EXT pixel buffer object", caps.GL_EXT_pixel_buffer_object);
		capInfos.put("EXT point parameters", caps.GL_EXT_point_parameters);
		capInfos.put("EXT provoking vertex", caps.GL_EXT_provoking_vertex);
		capInfos.put("EXT rescale normal", caps.GL_EXT_rescale_normal);
		capInfos.put("EXT secondary color", caps.GL_EXT_secondary_color);
		capInfos.put("EXT separate shader objects",
				caps.GL_EXT_separate_shader_objects);
		capInfos.put("EXT separate specular color",
				caps.GL_EXT_separate_specular_color);
		capInfos.put("EXT shader image load store",
				caps.GL_EXT_shader_image_load_store);
		capInfos.put("EXT shadow funcs", caps.GL_EXT_shadow_funcs);
		capInfos.put("EXT shared texture palette",
				caps.GL_EXT_shared_texture_palette);
		capInfos.put("EXT stencil clear tag", caps.GL_EXT_stencil_clear_tag);
		capInfos.put("EXT stencil two side", caps.GL_EXT_stencil_two_side);
		capInfos.put("EXT stencil wrap", caps.GL_EXT_stencil_wrap);
		capInfos.put("EXT texture 3d", caps.GL_EXT_texture_3d);
		capInfos.put("EXT texture array", caps.GL_EXT_texture_array);
		capInfos.put("EXT texture buffer object",
				caps.GL_EXT_texture_buffer_object);
		capInfos.put("EXT texture compression latc",
				caps.GL_EXT_texture_compression_latc);
		capInfos.put("EXT texture compression rgtc",
				caps.GL_EXT_texture_compression_rgtc);
		capInfos.put("EXT texture compression s3tc",
				caps.GL_EXT_texture_compression_s3tc);
		capInfos.put("EXT texture env combine", caps.GL_EXT_texture_env_combine);
		capInfos.put("EXT texture env dot3", caps.GL_EXT_texture_env_dot3);
		capInfos.put("EXT texture filter anisotropic",
				caps.GL_EXT_texture_filter_anisotropic);
		capInfos.put("EXT texture integer", caps.GL_EXT_texture_integer);
		capInfos.put("EXT texture lod bias", caps.GL_EXT_texture_lod_bias);
		capInfos.put("EXT texture mirror clamp",
				caps.GL_EXT_texture_mirror_clamp);
		capInfos.put("EXT texture rectangle", caps.GL_EXT_texture_rectangle);
		capInfos.put("EXT texture sRGB", caps.GL_EXT_texture_sRGB);
		capInfos.put("EXT texture shared exponent",
				caps.GL_EXT_texture_shared_exponent);
		capInfos.put("EXT texture snorm", caps.GL_EXT_texture_snorm);
		capInfos.put("EXT texture swizzle", caps.GL_EXT_texture_swizzle);
		capInfos.put("EXT timer query", caps.GL_EXT_timer_query);
		capInfos.put("EXT transform feedback", caps.GL_EXT_transform_feedback);
		capInfos.put("EXT vertex array bgra", caps.GL_EXT_vertex_array_bgra);
		capInfos.put("EXT vertex attrib 64bit", caps.GL_EXT_vertex_attrib_64bit);
		capInfos.put("EXT vertex shader", caps.GL_EXT_vertex_shader);
		capInfos.put("EXT vertex weighting", caps.GL_EXT_vertex_weighting);

		capInfos.put("GREMEDY string marker", caps.GL_GREMEDY_string_marker);
		capInfos.put("HP occlusion test", caps.GL_HP_occlusion_test);
		capInfos.put("IBM rasterpos clip", caps.GL_IBM_rasterpos_clip);
		capInfos.put("NVX gpu memory info", caps.GL_NVX_gpu_memory_info);
		capInfos.put("NV blend square", caps.GL_NV_blend_square);
		capInfos.put("NV conditional render", caps.GL_NV_conditional_render);
		capInfos.put("NV copy depth to color", caps.GL_NV_copy_depth_to_color);
		capInfos.put("NV copy image", caps.GL_NV_copy_image);
		capInfos.put("NV depth buffer float", caps.GL_NV_depth_buffer_float);
		capInfos.put("NV depth clamp", caps.GL_NV_depth_clamp);
		capInfos.put("NV evaluators", caps.GL_NV_evaluators);
		capInfos.put("NV explicit multisample", caps.GL_NV_explicit_multisample);
		capInfos.put("NV fence", caps.GL_NV_fence);
		capInfos.put("NV float buffer", caps.GL_NV_float_buffer);
		capInfos.put("NV fog distance", caps.GL_NV_fog_distance);
		capInfos.put("NV fragment program", caps.GL_NV_fragment_program);
		capInfos.put("NV fragment program2", caps.GL_NV_fragment_program2);
		capInfos.put("NV fragment program4", caps.GL_NV_fragment_program4);
		capInfos.put("NV fragment program option",
				caps.GL_NV_fragment_program_option);
		capInfos.put("NV framebuffer multisample coverage",
				caps.GL_NV_framebuffer_multisample_coverage);
		capInfos.put("NV geometry program4", caps.GL_NV_geometry_program4);
		capInfos.put("NV geometry shader4", caps.GL_NV_geometry_shader4);
		capInfos.put("NV gpu program4", caps.GL_NV_gpu_program4);
		capInfos.put("NV gpu program5", caps.GL_NV_gpu_program5);
		capInfos.put("NV gpu shader5", caps.GL_NV_gpu_shader5);
		capInfos.put("NV half float", caps.GL_NV_half_float);
		capInfos.put("NV light max exponent", caps.GL_NV_light_max_exponent);
		capInfos.put("NV multisample coverage", caps.GL_NV_multisample_coverage);
		capInfos.put("NV multisample filter hint",
				caps.GL_NV_multisample_filter_hint);
		capInfos.put("NV occlusion query", caps.GL_NV_occlusion_query);
		capInfos.put("NV packed depth stencil", caps.GL_NV_packed_depth_stencil);
		capInfos.put("NV parameter buffer object",
				caps.GL_NV_parameter_buffer_object);
		capInfos.put("NV parameter buffer object2",
				caps.GL_NV_parameter_buffer_object2);
		capInfos.put("NV pixel data range", caps.GL_NV_pixel_data_range);
		capInfos.put("NV point sprite", caps.GL_NV_point_sprite);
		capInfos.put("NV primitive restart", caps.GL_NV_primitive_restart);
		capInfos.put("NV register combiners", caps.GL_NV_register_combiners);
		capInfos.put("NV register combiners2", caps.GL_NV_register_combiners2);
		capInfos.put("NV shader buffer load", caps.GL_NV_shader_buffer_load);
		capInfos.put("NV shader buffer store", caps.GL_NV_shader_buffer_store);
		capInfos.put("NV tessellation program5",
				caps.GL_NV_tessellation_program5);
		capInfos.put("NV texgen reflection", caps.GL_NV_texgen_reflection);
		capInfos.put("NV texture barrier", caps.GL_NV_texture_barrier);
		capInfos.put("NV texture compression vtc",
				caps.GL_NV_texture_compression_vtc);
		capInfos.put("NV texture env combine4", caps.GL_NV_texture_env_combine4);
		capInfos.put("NV texture expand normal",
				caps.GL_NV_texture_expand_normal);
		capInfos.put("NV texture rectangle", caps.GL_NV_texture_rectangle);
		capInfos.put("NV texture shader", caps.GL_NV_texture_shader);
		capInfos.put("NV texture shader2", caps.GL_NV_texture_shader2);
		capInfos.put("NV texture shader3", caps.GL_NV_texture_shader3);
		capInfos.put("NV transform feedback", caps.GL_NV_transform_feedback);
		capInfos.put("NV transform feedback2", caps.GL_NV_transform_feedback2);
		capInfos.put("NV vertex array range", caps.GL_NV_vertex_array_range);
		capInfos.put("NV vertex array range2", caps.GL_NV_vertex_array_range2);
		capInfos.put("NV vertex attrib integer 64bit",
				caps.GL_NV_vertex_attrib_integer_64bit);
		capInfos.put("NV vertex buffer unified memory",
				caps.GL_NV_vertex_buffer_unified_memory);
		capInfos.put("NV vertex program", caps.GL_NV_vertex_program);
		capInfos.put("NV vertex program1 1", caps.GL_NV_vertex_program1_1);
		capInfos.put("NV vertex program2", caps.GL_NV_vertex_program2);
		capInfos.put("NV vertex program2 option",
				caps.GL_NV_vertex_program2_option);
		capInfos.put("NV vertex program3", caps.GL_NV_vertex_program3);
		capInfos.put("NV vertex program4", caps.GL_NV_vertex_program4);
		capInfos.put("SGIS generate mipmap", caps.GL_SGIS_generate_mipmap);
		capInfos.put("SGIS texture lod", caps.GL_SGIS_texture_lod);
		capInfos.put("SUN slice accum", caps.GL_SUN_slice_accum);
	}

	/**
	 * @param io_strb
	 * @param title
	 * @param available
	 */
	static void availableNL(StringBuffer io_strb, String title, boolean flag) {
		infoNL(io_strb, title, flag ? "supported" : "not available");

	}

	static void warnNL(StringBuffer io_strb, String msg) {
		io_strb.append(msg).append(NL);
	}

	static void infoNL(StringBuffer io_strb, String title, String... msg) {
		io_strb.append(title).append(":");
		for (int i = 0; i < TAB - title.length(); i++) {
			io_strb.append(' ');
		}
		for (String s : msg) {
			io_strb.append(' ');
			if (s != null)
				io_strb.append(s);
			else
				io_strb.append("n.a.");
		}
		io_strb.append(NL);
	}

	public static void main(String[] args) throws Exception {

		Display.setFullscreen(false);
		Display.create();

		String s = gatherInformation();
		System.out.println(s);
	}

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO implement method LWJGLInfoView.setFocus

	}

}
