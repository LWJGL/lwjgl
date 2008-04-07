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

package org.lwjgl.util.generator;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.type.InterfaceType;

/**
 *
 * Generator visitor for the context capabilities generator tool
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */
public class ContextCapabilitiesGenerator {
	private final static String STUBS_LOADED_NAME = "loaded_stubs";
	private final static String ALL_INIT_METHOD_NAME = "initAllStubs";
	private final static String POINTER_INITIALIZER_POSTFIX = "_initNativeFunctionAddresses";
	private final static String CACHED_EXTS_VAR_NAME = "supported_extensions";
	private final static String EXTENSION_PREFIX = "GL_";
	private final static String CORE_PREFIX = "Open";

	public static void generateClassPrologue(PrintWriter writer, boolean context_specific) {
		writer.println("public class " + Utils.CONTEXT_CAPS_CLASS_NAME + " {");
		writer.println("\tfinal StateTracker tracker;");
		writer.println("\tfinal IntBuffer scratch_int_buffer = BufferUtils.createIntBuffer(16);");
		writer.println();
		if (!context_specific) {
			writer.println("\tprivate static boolean " + STUBS_LOADED_NAME + " = false;");
		}
	}

	public static void generateInitializerPrologue(PrintWriter writer) {
		writer.println("\t" + Utils.CONTEXT_CAPS_CLASS_NAME + "() throws LWJGLException {");
		writer.println("\t\tSet " + CACHED_EXTS_VAR_NAME + " = " + ALL_INIT_METHOD_NAME + "();");
	}

	private static String translateFieldName(String interface_name) {
		if (interface_name.startsWith("GL"))
			return CORE_PREFIX + interface_name;
		else
			return EXTENSION_PREFIX + interface_name;
	}

	public static void generateSuperClassAdds(PrintWriter writer, InterfaceDeclaration d) {
		Collection<InterfaceType> super_interfaces = d.getSuperinterfaces();
		if (super_interfaces.size() > 1)
			throw new RuntimeException(d + " extends more than one other interface");
		if (super_interfaces.size() == 1) {
			InterfaceType super_interface = super_interfaces.iterator().next();
			writer.print("\t\tif (" + CACHED_EXTS_VAR_NAME + ".contains(\"");
			writer.println(translateFieldName(d.getSimpleName()) + "\"))");
			writer.print("\t\t\t");
			generateAddExtension(writer, super_interface.getDeclaration());
		}
	}

	public static void generateInitializer(PrintWriter writer, InterfaceDeclaration d) {
		String translated_field_name = translateFieldName(d.getSimpleName());
		writer.print("\t\tthis." + translated_field_name + " = ");
		writer.print(CACHED_EXTS_VAR_NAME + ".contains(\"");
		writer.print(translated_field_name + "\")");
		Collection<InterfaceType> super_interfaces = d.getSuperinterfaces();
		if (super_interfaces.size() > 1)
			throw new RuntimeException(d + " extends more than one other interface");
		if (super_interfaces.size() == 1) {
			InterfaceType super_interface = super_interfaces.iterator().next();
			writer.println();
			writer.print("\t\t\t&& " + CACHED_EXTS_VAR_NAME + ".contains(\"");
			writer.print(translateFieldName(super_interface.getDeclaration().getSimpleName()) + "\")");
		}
		writer.println(";");
	}

	private static String getAddressesInitializerName(String class_name) {
		return class_name + POINTER_INITIALIZER_POSTFIX;
	}
	
	public static void generateInitStubsPrologue(PrintWriter writer, boolean context_specific) {
		writer.println("\tprivate Set " + ALL_INIT_METHOD_NAME + "() throws LWJGLException {");
		if (!context_specific) {
			writer.println("\t\tif (" + STUBS_LOADED_NAME + ")");
			writer.println("\t\t\treturn GLContext.getSupportedExtensions();");
			writer.println("\t\torg.lwjgl.opengl.GL11." + Utils.STUB_INITIALIZER_NAME + "();");
		} else {
			writer.println("\t\tif (!" + getAddressesInitializerName("GL11") + "())");
			writer.println("\t\t\tthrow new LWJGLException(\"GL11 not supported\");");
		}
		writer.println("\t\tGLContext.setCapabilities(this);");
		writer.println("\t\tSet " + CACHED_EXTS_VAR_NAME + " = GLContext.getSupportedExtensions();");
	}

	public static void generateInitStubsEpilogue(PrintWriter writer, boolean context_specific) {
		if (!context_specific) {
			writer.println("\t\t" + STUBS_LOADED_NAME + " = true;");
		}
		writer.println("\t\treturn " + CACHED_EXTS_VAR_NAME + ";");
		writer.println("\t}");
	}

	public static void generateUnloadStubs(PrintWriter writer, InterfaceDeclaration d) {
		if (d.getMethods().size() > 0) {
			writer.print("\t\tGLContext.resetNativeStubs(" + Utils.getSimpleClassName(d));
			writer.println(".class);");
		}
	}

	public static void generateInitStubs(PrintWriter writer, InterfaceDeclaration d, boolean context_specific) {
		if (d.getMethods().size() > 0) {
			if (context_specific) {
				writer.print("\t\tif (" + CACHED_EXTS_VAR_NAME + ".contains(\"");
				writer.print(translateFieldName(d.getSimpleName()) + "\")");
				writer.println(" && !" + getAddressesInitializerName(d.getSimpleName()) + "())");
				writer.print("\t\t\t" + CACHED_EXTS_VAR_NAME + ".remove(\"");
				writer.println(translateFieldName(d.getSimpleName()) + "\");");
			} else {
				writer.print("\t\tGLContext." + Utils.STUB_INITIALIZER_NAME + "(" + Utils.getSimpleClassName(d));
				writer.println(".class, " + CACHED_EXTS_VAR_NAME + ", \"" + translateFieldName(d.getSimpleName()) + "\");");
			}
		}
	}
	
	private static void generateAddExtension(PrintWriter writer, InterfaceDeclaration d) {
		writer.print(CACHED_EXTS_VAR_NAME + ".add(\"");
		writer.println(translateFieldName(d.getSimpleName()) + "\");");
	}

	public static void generateAddressesInitializers(PrintWriter writer, InterfaceDeclaration d) {
		Iterator<? extends MethodDeclaration> methods = d.getMethods().iterator();
		if (methods.hasNext()) {
			writer.println("\tprivate boolean " + getAddressesInitializerName(d.getSimpleName()) + "() {");
			writer.println("\t\treturn ");
			while (methods.hasNext()) {
				MethodDeclaration method = methods.next();
				writer.print("\t\t\t(" + Utils.getFunctionAddressName(d, method) + " = ");
				PlatformDependent platform_dependent = method.getAnnotation(PlatformDependent.class);
				if (platform_dependent != null) {
					EnumSet<Platform> platform_set = EnumSet.copyOf(Arrays.asList(platform_dependent.value()));
					writer.print("GLContext.getPlatformSpecificFunctionAddress(\"");
					writer.print(Platform.ALL.getPrefix() + "\", ");
					writer.print("new String[]{");
					Iterator<Platform> platforms = platform_set.iterator();
					while (platforms.hasNext()) {
						writer.print("\"" + platforms.next().getOSPrefix() + "\"");
						if(platforms.hasNext())
							writer.print(", ");
					}
					writer.print("}, new String[]{");
					platforms = platform_set.iterator();
					while (platforms.hasNext()) {
						writer.print("\"" + platforms.next().getPrefix() + "\"");
						if(platforms.hasNext())
							writer.print(", ");
					}
					writer.print("}, ");
				} else
					writer.print("GLContext.getFunctionAddress(");
				writer.print("\"" + method.getSimpleName() + "\")) != 0");
				if (methods.hasNext())
					writer.println(" &&");
			}
			writer.println(";");
			writer.println("\t}");
			writer.println();
		}
	}

	public static void generateSymbolAddresses(PrintWriter writer, InterfaceDeclaration d) {
		for (MethodDeclaration method : d.getMethods()) {
			writer.println("\tlong " + Utils.getFunctionAddressName(d, method) + ";");
		}
	}

	public static void generateField(PrintWriter writer, InterfaceDeclaration d) {
		writer.println("\tpublic final boolean " + translateFieldName(d.getSimpleName()) + ";");
	}
}
