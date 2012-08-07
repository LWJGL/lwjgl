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

package org.lwjgl.util.generator.opengl;

import org.lwjgl.util.generator.Utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.util.DeclarationFilter;

import static java.util.Collections.*;

/**
 * Generator tool for creating the ContexCapabilities class
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision: 3316 $
 *          $Id: ContextGeneratorProcessorFactory.java 3316 2010-04-09 23:57:40Z spasi $
 */
public class GLESGeneratorProcessorFactory implements AnnotationProcessorFactory, RoundCompleteListener {

	private static boolean first_round = true;

	// Process any set of annotations
	private static final Collection<String> supportedAnnotations =
		unmodifiableCollection(Arrays.asList("*"));

	public Collection<String> supportedAnnotationTypes() {
		return supportedAnnotations;
	}

	public Collection<String> supportedOptions() {
		return unmodifiableCollection(Arrays.asList("-Acontextspecific", "-Ageneratechecks"));
	}

	public void roundComplete(RoundCompleteEvent event) {
		first_round = false;
	}

	public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env) {
		// Only process the initial types, not the generated ones
		if ( first_round ) {
			env.addListener(this);
			return new GeneratorProcessor(env);
		} else
			return AnnotationProcessors.NO_OP;
	}

	private static class GeneratorProcessor implements AnnotationProcessor {

		private final AnnotationProcessorEnvironment env;

		GeneratorProcessor(AnnotationProcessorEnvironment env) {
			this.env = env;
		}

		public void process() {
			Map<String, String> options = env.getOptions();
			boolean generate_error_checks = options.containsKey("-Ageneratechecks");
			boolean context_specific = options.containsKey("-Acontextspecific");
			try {
				generateContextCapabilitiesSource(context_specific, generate_error_checks);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void generateContextCapabilitiesSource(boolean context_specific, boolean generate_error_checks) throws IOException {
			PrintWriter writer = env.getFiler().createTextFile(Filer.Location.SOURCE_TREE, "org.lwjgl.opengles", new File(Utils.CONTEXT_CAPS_CLASS_NAME + ".java"), null);
			writer.println("/* MACHINE GENERATED FILE, DO NOT EDIT */");
			writer.println();
			writer.println("package org.lwjgl.opengles;");
			writer.println();
			writer.println("import org.lwjgl.LWJGLException;");
			writer.println("import org.lwjgl.LWJGLUtil;");
			writer.println("import java.util.Set;");
			writer.println("import java.util.HashSet;");
			writer.println();
			GLESCapabilitiesGenerator.generateClassPrologue(writer, context_specific, generate_error_checks);
			DeclarationFilter filter = DeclarationFilter.getFilter(InterfaceDeclaration.class);
			Collection<TypeDeclaration> interface_decls = filter.filter(env.getSpecifiedTypeDeclarations());
			for ( TypeDeclaration typedecl : interface_decls ) {
				InterfaceDeclaration interface_decl = (InterfaceDeclaration)typedecl;
				if ( Utils.isFinal(interface_decl) )
					GLESCapabilitiesGenerator.generateField(writer, interface_decl);
			}
			writer.println();
			if ( context_specific ) {
				for ( TypeDeclaration typedecl : interface_decls ) {
					InterfaceDeclaration interface_decl = (InterfaceDeclaration)typedecl;
					GLESCapabilitiesGenerator.generateSymbolAddresses(writer, interface_decl);
				}
				writer.println();
				for ( TypeDeclaration typedecl : interface_decls ) {
					InterfaceDeclaration interface_decl = (InterfaceDeclaration)typedecl;
					GLESCapabilitiesGenerator.generateAddressesInitializers(writer, interface_decl);
				}
				writer.println();
			}

			if ( context_specific ) {
				writer.println("\tprivate static void remove(Set supported_extensions, String extension) {");
				writer.println("\t\tLWJGLUtil.log(extension + \" was reported as available but an entry point is missing\");");
				writer.println("\t\tsupported_extensions.remove(extension);");
				writer.println("\t}\n");
			}

			GLESCapabilitiesGenerator.generateInitStubsPrologue(writer, context_specific);
			for ( TypeDeclaration typedecl : interface_decls ) {
				InterfaceDeclaration interface_decl = (InterfaceDeclaration)typedecl;
				GLESCapabilitiesGenerator.generateSuperClassAdds(writer, interface_decl);
			}
			for ( TypeDeclaration typedecl : interface_decls ) {
				InterfaceDeclaration interface_decl = (InterfaceDeclaration)typedecl;
				if ( "GLES20".equals(interface_decl.getSimpleName()) )
					continue;
				GLESCapabilitiesGenerator.generateInitStubs(writer, interface_decl, context_specific);
			}
			GLESCapabilitiesGenerator.generateInitStubsEpilogue(writer, context_specific);
			writer.println();
			writer.println("\tstatic void unloadAllStubs() {");
			if ( !context_specific ) {
				writer.println("\t\tif (!loaded_stubs)");
				writer.println("\t\t\treturn;");
				for ( TypeDeclaration typedecl : interface_decls ) {
					InterfaceDeclaration interface_decl = (InterfaceDeclaration)typedecl;
					GLESCapabilitiesGenerator.generateUnloadStubs(writer, interface_decl);
				}
				writer.println("\t\tloaded_stubs = false;");
			}
			writer.println("\t}");
			writer.println();
			GLESCapabilitiesGenerator.generateInitializerPrologue(writer);
			for ( TypeDeclaration typedecl : interface_decls ) {
				InterfaceDeclaration interface_decl = (InterfaceDeclaration)typedecl;
				if ( Utils.isFinal(interface_decl) )
					GLESCapabilitiesGenerator.generateInitializer(writer, interface_decl);
			}
			writer.println("\t}");
			writer.println("}");
			writer.close();
		}
	}
}
