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

package org.lwjgl.util.generator.opencl;

import org.lwjgl.util.generator.*;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

/**
 * CLCapabilities generator.
 *
 * @author Spasi
 */
public class CLCapabilitiesGenerator {

	private static final String SUPPORTED_EXTS = "supported_extensions";

	static void generateClassPrologue(final PrintWriter writer) {
		writer.println("public final class " + CLGeneratorProcessorFactory.CLCAPS_CLASS_NAME + " {");
		writer.println();
	}

	static void generateSymbolAddresses(final PrintWriter writer, final InterfaceDeclaration d) {
		final Alias alias_annotation = d.getAnnotation(Alias.class);
		final boolean aliased = alias_annotation != null && alias_annotation.postfix().length() > 0;

		boolean first = true;
		for ( final MethodDeclaration method : d.getMethods() ) {
			if ( method.getAnnotation(Alternate.class) != null || method.getAnnotation(Reuse.class) != null )
				continue;

			if ( first ) {
				writer.println("\t// " + d.getSimpleName());
				first = false;
			}
			writer.print("\tstatic final long " + Utils.getFunctionAddressName(d, method) + " = CL.getFunctionAddress(");

			if ( aliased )
				writer.println("new String [] {\"" + Utils.getFunctionAddressName(d, method) + "\",\"" + method.getSimpleName() + alias_annotation.postfix() + "\"});");
			else
				writer.println("\"" + Utils.getFunctionAddressName(d, method) + "\");");
		}
	}

	static void generateConstructor(final PrintWriter writer, final Collection<TypeDeclaration> interface_decls) {
		writer.println("\tprivate static final Set<String> " + SUPPORTED_EXTS + " = new HashSet<String>();");
		writer.println();
		writer.println("\tprivate " + CLGeneratorProcessorFactory.CLCAPS_CLASS_NAME + "() {}");
		writer.println();
		writer.println("\tstatic {");

		for ( final TypeDeclaration d : interface_decls ) {
			if ( d.getMethods().isEmpty() )
				continue;

			writer.println("\t\tif ( " + getExtensionSupportedName(d.getSimpleName()) + "() )");
			writer.println("\t\t\t" + SUPPORTED_EXTS + ".add(\"" + CLGeneratorProcessorFactory.getExtensionName(d.getSimpleName()) + "\");");
		}

		writer.println("\t}\n");
	}

	static void generateExtensionSupported(final PrintWriter writer) {
		writer.println("\tstatic boolean isExtensionSupported(final String name) {\n" +
		               "\t\treturn " + SUPPORTED_EXTS + ".contains(name);\n" +
		               "\t}\n");
	}

	static void generateExtensionChecks(final PrintWriter writer, final InterfaceDeclaration d) {
		Iterator<? extends MethodDeclaration> methods = d.getMethods().iterator();
		if ( !methods.hasNext() )
			return;

		writer.println("\tstatic boolean " + getExtensionSupportedName(d.getSimpleName()) + "() {");
		writer.println("\t\treturn ");

		boolean first = true;
		while ( methods.hasNext() ) {
			MethodDeclaration method = methods.next();
			if ( method.getAnnotation(Alternate.class) != null )
				continue;

			if ( !first )
				writer.println(" &");
			else
				first = false;

			final boolean optional = method.getAnnotation(Optional.class) != null;

			writer.print("\t\t\t");
			if ( optional )
				writer.print('(');
			writer.print(Utils.getFunctionAddressName(d, method) + " != 0");
			if ( optional )
				writer.print(" || true)");
		}
		writer.println(";");
		writer.println("\t}");
		writer.println();
	}

	private static String getExtensionSupportedName(final String class_name) {
		return "is" + class_name + "Supported";
	}

	public static void generateCapabilitiesGetters(final PrintWriter writer) {
		writer.println("\tpublic static CLPlatformCapabilities getPlatformCapabilities(final CLPlatform platform) {\n" +
		               "\t\treturn CLPlatformImpl.getCapabilities(platform);\n" +
		               "\t}\n");

		writer.println("\tpublic static CLDeviceCapabilities getDeviceCapabilities(final CLDevice device) {\n" +
		               "\t\treturn CLDeviceImpl.getCapabilities(device);\n" +
		               "\t}\n");

	}
}