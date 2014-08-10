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


import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collection;
import static java.util.Collections.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * Generator tool for creating the java classes and native code
 * from an annotated template java interface.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 *          $Id$
 */
public class GeneratorProcessorFactory {
        /* deprecated implements AnnotationProcessorFactory, RoundCompleteListener {*/

	private static boolean first_round = true;

	// Process any set of annotations
	private static final Set<String> supportedAnnotations =
		unmodifiableSet(new HashSet<>(Arrays.asList("*")));

	private static final Set<String> supportedOptions =
		unmodifiableSet(new HashSet<>(Arrays.asList("-Atypemap", "-Ageneratechecks", "-Acontextspecific")));

        Processor processor;

        public Processor getProcessor() {
                return first_round ? processor : new AbstractProcessor() {

                        @Override
                        public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
                            return true;
                        }
                };
        }
        
        public GeneratorProcessorFactory(ProcessingEnvironment env) {
                processor = new GeneratorProcessor(env);
        }

        
	private static class GeneratorProcessor extends AbstractProcessor {

		private final ProcessingEnvironment env;

		GeneratorProcessor(ProcessingEnvironment env) {
			this.env = env;
		}

                @Override
                public Set<String> getSupportedOptions() {
                        return supportedOptions;
                }

                @Override
                public Set<String> getSupportedAnnotationTypes() {
                        return supportedAnnotations;
                }

                @Override
                public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
                        Map<String, String> options = env.getOptions();
			String typemap_classname = null;
			String bin_path = null;
			boolean generate_error_checks = options.containsKey("-Ageneratechecks");
			boolean context_specific = options.containsKey("-Acontextspecific");
			for ( String k : options.keySet() ) {
				if ( !k.startsWith("-A") )
					continue;

				int delimiter = k.indexOf('=');
				if ( delimiter != -1 ) {
					if ( k.startsWith("-Atypemap") ) {
						typemap_classname = k.substring(delimiter + 1);
					} else if ( k.startsWith("-Abinpath") ) {
						bin_path = k.substring(delimiter + 1);
					}
				}
			}
			if ( typemap_classname == null )
				throw new RuntimeException("No TypeMap class name specified with -Atypemap=<class-name>");
			if ( bin_path == null )
				throw new RuntimeException("No path specified for the bin directory with -Abinpath=<path>");

			TypeElement lastFile = null;
			try {
				long generatorLM = getGeneratorLastModified(bin_path);
				TypeMap type_map = (TypeMap)(Class.forName(typemap_classname).newInstance());
				/*for ( TypeElement typedecl : env.getSpecifiedTypeDeclarations() ) {
					lastFile = typedecl;
					typedecl.accept(getDeclarationScanner(new GeneratorVisitor(env, type_map, generate_error_checks, context_specific, generatorLM), NO_OP));
				}*/
                                return first_round = roundEnv.processingOver();        		
                        } catch (Exception e) {
				if ( lastFile == null )
					throw new RuntimeException(e);
				else
					throw new RuntimeException("\n-- Failed to process template: " + lastFile.getQualifiedName() + " --", e);
			}                        
		}

		/**
		 * Gets the time of the latest change on the Generator classes.
		 *
		 * @return time of the latest change
		 */
		private static long getGeneratorLastModified(final String bin_path) {
			long lastModified = getDirectoryLastModified(bin_path, "/org/lwjgl/util/generator");
			lastModified = Math.max(lastModified, getDirectoryLastModified(bin_path, "/org/lwjgl/util/generator/openal"));
			lastModified = Math.max(lastModified, getDirectoryLastModified(bin_path, "/org/lwjgl/util/generator/opengl"));
			lastModified = Math.max(lastModified, getDirectoryLastModified(bin_path, "/org/lwjgl/util/generator/opencl"));

			return lastModified;
		}

		private static long getDirectoryLastModified(final String bin_path, final String path) {
			final File pck = new File(bin_path + path);
			if ( !pck.exists() || !pck.isDirectory() )
				return Long.MAX_VALUE;

			final File[] classes = pck.listFiles(new FileFilter() {
				public boolean accept(final File pathname) {
					return pathname.isFile() && pathname.getName().endsWith(".class");
				}
			});

			if ( classes == null || classes.length == 0 )
				return Long.MAX_VALUE;

			long lastModified = 0;

			for ( File clazz : classes ) {
				long lm = clazz.lastModified();
				if ( lastModified < lm )
					lastModified = lm;
			}

			return lastModified;
		}

       }
}