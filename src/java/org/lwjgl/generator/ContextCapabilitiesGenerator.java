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

package org.lwjgl.generator;

import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;
import com.sun.mirror.type.*;
import com.sun.mirror.util.*;

import java.util.*;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;

import java.nio.*;
import java.lang.annotation.Annotation;

/**
 * $Id$
 *
 * Generator visitor for the context capabilities generator tool
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */
public class ContextCapabilitiesGenerator {
	public final static String CONTEXT_CAPS_CLASS_NAME = "ContextCapabilities";
	private final static String CACHED_EXTS_VAR_NAME = "supported_extensions";
	private final static String EXTENSION_PREFIX = "GL_";
	private final static String CORE_PREFIX = "Open";

	public static void generateClassPrologue(PrintWriter writer) {
		writer.println("public class " + CONTEXT_CAPS_CLASS_NAME + " {");
	}

	public static void generateInitializerPrologue(PrintWriter writer) {
//		writer.println("\t\t/*");
//		writer.println("\t\t * Special case: initialize GL11 unconditionally,");
//		writer.println("\t\t * and let exceptions abort the constructor");
//		writer.println("\t\t */");

//		writer.println("\t\tGL11.initNativeStubs();");*/
		writer.println("\t" + CONTEXT_CAPS_CLASS_NAME + "(Set " + CACHED_EXTS_VAR_NAME + ") {");
	}

	private static String translateFieldName(String interface_name) {
		if (interface_name.startsWith("GL"))
			return CORE_PREFIX + interface_name;
		else
			return EXTENSION_PREFIX + interface_name;
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

	public static void generateInitStubsPrologue(PrintWriter writer) {
		writer.println("\tstatic Set initAllStubs() throws LWJGLException {");
		writer.println("\t\torg.lwjgl.opengl.GL11.initNativeStubs();");
		writer.println("\t\tSet " + CACHED_EXTS_VAR_NAME + " = GLContext.getSupportedExtensions();");
	}

	public static void generateInitStubsEpilogue(PrintWriter writer) {
		writer.println("\t\treturn " + CACHED_EXTS_VAR_NAME + ";");
		writer.println("\t}");
	}

	public static void generateUnloadStubs(PrintWriter writer, InterfaceDeclaration d) {
		if (d.getMethods().size() > 0) {
			writer.print("\t\tGLContext.resetNativeStubs(" + Utils.getSimpleClassName(d));
			writer.println(".class);");
		}
	}

	public static void generateInitStubs(PrintWriter writer, InterfaceDeclaration d) {
		if (d.getMethods().size() > 0) {
			writer.print("\t\tGLContext.initNativeStubs(" + Utils.getSimpleClassName(d));
			writer.println(".class, supported_extensions, \"" + translateFieldName(d.getSimpleName()) + "\");");
		}
	}
	
	public static void generateAddExtension(PrintWriter writer, InterfaceDeclaration d) {
		writer.print("\t\t" + CACHED_EXTS_VAR_NAME + ".add(\"");
		writer.println(translateFieldName(d.getSimpleName()) + "\");");
	}

/*	public static void generateSymbolPointers(PrintWriter writer, InterfaceDeclaration d) {
		for (MethodDeclaration method : d.getMethods()) {
			writer.println("\tlong " + d.getSimpleName() + "_" + method.getSimpleName() + ";");
		}
	}
*/
	public static void generateField(PrintWriter writer, InterfaceDeclaration d) {
		writer.println("\tpublic final boolean " + translateFieldName(d.getSimpleName()) + ";");
	}
}
