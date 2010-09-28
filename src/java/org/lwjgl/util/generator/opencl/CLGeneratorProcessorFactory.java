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

import org.lwjgl.PointerWrapper;
import org.lwjgl.opencl.CLDevice;
import org.lwjgl.opencl.CLPlatform;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.InterfaceDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.util.DeclarationFilter;

import static java.util.Collections.*;

/**
 * Generator tool for creating the OpenCL capabilities classes
 *
 * @author Spasi
 */
public class CLGeneratorProcessorFactory implements AnnotationProcessorFactory, RoundCompleteListener {

	public static final String CLCAPS_CLASS_NAME = "CLCapabilities";
	public static final String PLATFORM_CAPS_CLASS_NAME = "CLPlatformCapabilities";
	public static final String DEVICE_CAPS_CLASS_NAME = "CLDeviceCapabilities";

	private static final String EXTENSION_PREFIX = "CL_";
	private static final String CORE_PREFIX = "Open";

	private static boolean first_round = true;

	// Process any set of annotations
	private static final Collection<String> supportedAnnotations = unmodifiableCollection(Arrays.asList("*"));

	public Collection<String> supportedAnnotationTypes() {
		return supportedAnnotations;
	}

	public Collection<String> supportedOptions() {
		return unmodifiableCollection(Arrays.asList("-Acontextspecific"));
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

	static String getExtensionName(String interface_name) {
		if ( interface_name.startsWith("CL") )
			return CORE_PREFIX + interface_name;
		else
			return EXTENSION_PREFIX + interface_name;
	}

	private static class GeneratorProcessor implements AnnotationProcessor {

		private final AnnotationProcessorEnvironment env;

		GeneratorProcessor(AnnotationProcessorEnvironment env) {
			this.env = env;
		}

		public void process() {
			try {
				generateCLCapabilitiesSource();
				generateCLPDCapabilitiesSource(CLPlatformExtension.class, PLATFORM_CAPS_CLASS_NAME, CLPlatform.class, "platform");
				generateCLPDCapabilitiesSource(CLDeviceExtension.class, DEVICE_CAPS_CLASS_NAME, CLDevice.class, "device");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private static void printHeader(final PrintWriter writer) {
			writer.println("/* MACHINE GENERATED FILE, DO NOT EDIT */");
			writer.println();
			writer.println("package org.lwjgl.opencl;");
			writer.println();
		}

		private void generateCLCapabilitiesSource() throws IOException {
			final PrintWriter writer = env.getFiler().createTextFile(Filer.Location.SOURCE_TREE, "org.lwjgl.opencl", new File(CLCAPS_CLASS_NAME + ".java"), null);
			printHeader(writer);

			CLCapabilitiesGenerator.generateClassPrologue(writer);

			final Collection<TypeDeclaration> templates = DeclarationFilter.getFilter(InterfaceDeclaration.class).filter(env.getSpecifiedTypeDeclarations());

			for ( final TypeDeclaration t : templates ) {
				if ( t.getAnnotation(CLPlatformExtension.class) == null && t.getAnnotation(CLDeviceExtension.class) == null && !t.getSimpleName().startsWith("CL") )
					throw new RuntimeException("An OpenCL extension is missing an extension type annotation: " + t.getSimpleName());

				CLCapabilitiesGenerator.generateSymbolAddresses(writer, (InterfaceDeclaration)t);
			}
			writer.println();

			CLCapabilitiesGenerator.generateConstructor(writer, templates);

			CLCapabilitiesGenerator.generateCapabilitiesGetters(writer);

			for ( final TypeDeclaration template : templates )
				CLCapabilitiesGenerator.generateExtensionChecks(writer, (InterfaceDeclaration)template);

			writer.println("}");
			writer.close();
		}

		private void generateCLPDCapabilitiesSource(final Class<? extends Annotation> capsType, final String capsName, final Class<? extends PointerWrapper> objectType, final String objectName) throws IOException {
			final PrintWriter writer = env.getFiler().createTextFile(Filer.Location.SOURCE_TREE, "org.lwjgl.opencl", new File(capsName + ".java"), null);
			printHeader(writer);
			writer.println("import java.util.*;");
			writer.println();

			CLPDCapabilitiesGenerator.generateClassPrologue(writer, capsName);

			final Collection<TypeDeclaration> templates = DeclarationFilter.getFilter(InterfaceDeclaration.class).filter(env.getSpecifiedTypeDeclarations());

			for ( final TypeDeclaration t : templates ) {
				if ( t.getAnnotation(capsType) != null )
					CLPDCapabilitiesGenerator.generateExtensions(writer, (InterfaceDeclaration)t);
			}
			writer.println();

			CLPDCapabilitiesGenerator.generateConstructor(writer, templates, capsType, capsName, objectType, objectName);

			CLPDCapabilitiesGenerator.generateGetters(writer);

			CLPDCapabilitiesGenerator.generateToString(writer, templates, capsType);

			writer.println("}");
			writer.close();
		}

	}
}