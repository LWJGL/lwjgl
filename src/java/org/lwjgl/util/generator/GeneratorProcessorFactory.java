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

import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;

import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.Arrays;

import static java.util.Collections.*;
import static com.sun.mirror.util.DeclarationVisitors.*;

/**
 *
 * Generator tool for creating the java classes and native code
 * from an annotated template java interface.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */
public class GeneratorProcessorFactory implements AnnotationProcessorFactory, RoundCompleteListener {
	private static boolean first_round = true;

	// Process any set of annotations
	private static final Collection<String> supportedAnnotations =
		unmodifiableCollection(Arrays.asList("*"));

	private static final Collection<String> supportedOptions =
		unmodifiableCollection(Arrays.asList("-Atypemap", "-Ageneratechecks", "-Acontextspecific"));

	public Collection<String> supportedAnnotationTypes() {
		return supportedAnnotations;
	}

	public Collection<String> supportedOptions() {
		return supportedOptions;
	}

	public void roundComplete(RoundCompleteEvent event) {
		first_round = false;
	}

	public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env) {
		// Only process the initial types, not the generated ones
		if (first_round) {
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
			String typemap_classname = null;
			boolean generate_error_checks = options.containsKey("-Ageneratechecks");
			boolean context_specific = options.containsKey("-Acontextspecific");
			for (String k : options.keySet()) {
				int delimiter = k.indexOf('=');
				if (delimiter != -1) {
					if (k.startsWith("-Atypemap")) {
						typemap_classname = k.substring(delimiter + 1);
					}
				}
			}
			if (typemap_classname == null)
				throw new RuntimeException("No TypeMap class name specified with -Atypemap=<class-name>");
			try {
				TypeMap type_map = (TypeMap)(Class.forName(typemap_classname).newInstance());
				for (TypeDeclaration typedecl : env.getSpecifiedTypeDeclarations()) {
					typedecl.accept(getDeclarationScanner(new GeneratorVisitor(env, type_map, generate_error_checks, context_specific), NO_OP));
				}
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
