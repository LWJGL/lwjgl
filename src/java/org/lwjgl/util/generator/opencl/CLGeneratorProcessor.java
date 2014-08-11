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

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import static java.util.Collections.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import org.lwjgl.PointerWrapper;
import org.lwjgl.opencl.CLDevice;
import org.lwjgl.opencl.CLPlatform;

/**
 * Generator tool for creating the OpenCL capabilities classes
 *
 * @author Spasi
 */
public class CLGeneratorProcessor extends AbstractProcessor {

        public static final String CLCAPS_CLASS_NAME = "CLCapabilities";
        public static final String PLATFORM_CAPS_CLASS_NAME = "CLPlatformCapabilities";
        public static final String DEVICE_CAPS_CLASS_NAME = "CLDeviceCapabilities";

        private static final String EXTENSION_PREFIX = "CL_";
        private static final String CORE_PREFIX = "Open";

        private static boolean first_round = true;

        @Override
        public Set<String> getSupportedAnnotationTypes() {
                return unmodifiableSet(new HashSet(Arrays.asList("*")));
        }

        @Override
        public Set<String> getSupportedOptions() {
                return unmodifiableSet(new HashSet<>(Arrays.asList("contextspecific")));
        }

        static String getExtensionName(String interface_name) {
                if (interface_name.startsWith("CL")) {
                        return CORE_PREFIX + interface_name;
                } else {
                        return EXTENSION_PREFIX + interface_name;
                }
        }

        @Override
        public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
                if (roundEnv.processingOver() || first_round) {
                        return false;
                }
                try {
                        generateCLCapabilitiesSource();
                        generateCLPDCapabilitiesSource(CLPlatformExtension.class, PLATFORM_CAPS_CLASS_NAME, CLPlatform.class, "platform");
                        generateCLPDCapabilitiesSource(CLDeviceExtension.class, DEVICE_CAPS_CLASS_NAME, CLDevice.class, "device");
                        return first_round = true;
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
                final PrintWriter writer = new PrintWriter(processingEnv.getFiler().createSourceFile(CLCAPS_CLASS_NAME, processingEnv.getElementUtils().getPackageElement("org.lwjgl.opencl")).openWriter());
                printHeader(writer);

                CLCapabilitiesGenerator.generateClassPrologue(writer);

                final List<TypeElement> templates = ElementFilter.typesIn(processingEnv.getElementUtils().getAllMembers(processingEnv.getElementUtils().getTypeElement("org.lwjgl.opencl." + CLCAPS_CLASS_NAME)));
                for (final TypeElement t : templates) {
                        if (t.getAnnotation(CLPlatformExtension.class) == null && t.getAnnotation(CLDeviceExtension.class) == null && !t.getSimpleName().toString().startsWith("CL")) {
                                throw new RuntimeException("An OpenCL extension is missing an extension type annotation: " + t.getSimpleName());
                        }

                        CLCapabilitiesGenerator.generateSymbolAddresses(writer, (TypeElement) t);
                }
                writer.println();

                CLCapabilitiesGenerator.generateConstructor(writer, templates);

                CLCapabilitiesGenerator.generateCapabilitiesGetters(writer);

                for (final TypeElement template : templates) {
                        CLCapabilitiesGenerator.generateExtensionChecks(writer, (TypeElement) template);
                }

                writer.println("}");
                writer.close();
        }

        private void generateCLPDCapabilitiesSource(final Class<? extends Annotation> capsType, final String capsName, final Class<? extends PointerWrapper> objectType, final String objectName) throws IOException {
                final PrintWriter writer = new PrintWriter(processingEnv.getFiler().createSourceFile(capsName, processingEnv.getElementUtils().getPackageElement("org.lwjgl.opencl")).openWriter());
                printHeader(writer);
                writer.println("import java.util.*;");
                writer.println();

                CLPDCapabilitiesGenerator.generateClassPrologue(writer, capsName);

                final List<TypeElement> templates = ElementFilter.typesIn(processingEnv.getElementUtils().getAllMembers(processingEnv.getElementUtils().getTypeElement("org.lwjgl.opencl" + capsName)));

                for (final TypeElement t : templates) {
                        if (t.getAnnotation(capsType) != null) {
                                CLPDCapabilitiesGenerator.generateExtensions(writer, (TypeElement) t);
                        }
                }
                writer.println();

                CLPDCapabilitiesGenerator.generateConstructor(writer, templates, capsType, capsName, objectType, objectName);

                CLPDCapabilitiesGenerator.generateGetters(writer);

                CLPDCapabilitiesGenerator.generateToString(writer, templates, capsType);

                writer.println("}");
                writer.close();
        }
}
