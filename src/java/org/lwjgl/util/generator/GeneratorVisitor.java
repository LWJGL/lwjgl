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
import com.sun.mirror.type.*;
import com.sun.mirror.util.*;

import java.io.*;
import java.lang.annotation.Annotation;
import java.nio.channels.FileChannel;
import java.util.*;

import java.nio.*;

/**
 *
 * Generator visitor for the generator tool
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */
public class GeneratorVisitor extends SimpleDeclarationVisitor {
	private final AnnotationProcessorEnvironment env;
	private final TypeMap type_map;
	private final boolean generate_error_checks;
	private final boolean context_specific;
	private final long generatorLM;

	public GeneratorVisitor(AnnotationProcessorEnvironment env, TypeMap type_map, boolean generate_error_checks, boolean context_specific, long generatorLM) {
		this.env = env;
		this.type_map = type_map;
		this.generate_error_checks = generate_error_checks;
		this.context_specific = context_specific;
		this.generatorLM = generatorLM;
	}

	private void validateMethod(MethodDeclaration method) {
		if (method.isVarArgs())
			throw new RuntimeException("Method " + method.getSimpleName() + " is variadic");
		Collection<Modifier> modifiers = method.getModifiers();
		if (!modifiers.contains(Modifier.PUBLIC))
			throw new RuntimeException("Method " + method.getSimpleName() + " is not public");
		if (method.getThrownTypes().size() > 0)
			throw new RuntimeException("Method " + method.getSimpleName() + " throws checked exceptions");
		validateParameters(method);
		StripPostfix strip_annotation = method.getAnnotation(StripPostfix.class);
		if (strip_annotation != null && method.getAnnotation(Alternate.class) == null) {
			String postfix_param_name = strip_annotation.value();
			ParameterDeclaration postfix_param = Utils.findParameter(method, postfix_param_name);
			if (Utils.isParameterMultiTyped(postfix_param))
				throw new RuntimeException("Postfix parameter can't be the same as a multityped parameter in method " + method);
			if (Utils.getNIOBufferType(postfix_param.getType()) == null)
				throw new RuntimeException("Postfix parameter type must be a nio Buffer");
		}
		if (Utils.getResultParameter(method) != null && !method.getReturnType().equals(env.getTypeUtils().getVoidType()))
			throw new RuntimeException(method + " return type is not void but a parameter is annotated with Result");
		if (method.getAnnotation(CachedResult.class) != null) {
			if (Utils.getNIOBufferType(Utils.getMethodReturnType(method)) == null)
				throw new RuntimeException(method + " return type is not a Buffer, but is annotated with CachedResult");
			if (method.getAnnotation(AutoSize.class) == null)
				throw new RuntimeException(method + " is annotated with CachedResult but misses an AutoSize annotation");
		}
		validateTypes(method, method.getAnnotationMirrors(), method.getReturnType());
	}

	private void validateType(MethodDeclaration method, Class<?extends Annotation> annotation_type, Class type) {
		Class[] valid_types = type_map.getValidAnnotationTypes(type);
		for ( Class valid_type : valid_types )
			if ( valid_type.equals(annotation_type) )
				return;
		throw new RuntimeException(type + " is annotated with invalid native type " + annotation_type +
				" in method " + method);
	}

	private void validateTypes(MethodDeclaration method, Collection<AnnotationMirror> annotations, TypeMirror type_mirror) {
		for (AnnotationMirror annotation : annotations) {
			NativeType native_type_annotation = NativeTypeTranslator.getAnnotation(annotation, NativeType.class);
			if (native_type_annotation != null) {
				Class<? extends Annotation> annotation_type = NativeTypeTranslator.getClassFromType(annotation.getAnnotationType());
				Class type = Utils.getJavaType(type_mirror);
				if (Buffer.class.equals(type))
					continue;
				validateType(method, annotation_type, type);
			}
		}
	}

	private void validateParameters(MethodDeclaration method) {
		for (ParameterDeclaration param : method.getParameters()) {
			validateTypes(method, param.getAnnotationMirrors(), param.getType());
			Class<?> param_type = Utils.getJavaType(param.getType());
			if (Utils.getNIOBufferType(param.getType()) != null && param_type != CharSequence.class && param_type != CharSequence[].class) {
				Check parameter_check_annotation = param.getAnnotation(Check.class);
				NullTerminated null_terminated_annotation = param.getAnnotation(NullTerminated.class);
				if (parameter_check_annotation == null && null_terminated_annotation == null) {
					boolean found_auto_size_param = false;
					for (ParameterDeclaration inner_param : method.getParameters()) {
						AutoSize auto_size_annotation = inner_param.getAnnotation(AutoSize.class);
						if (auto_size_annotation != null &&
								auto_size_annotation.value().equals(param.getSimpleName())) {
							found_auto_size_param = true;
							break;
						}
					}
					if (!found_auto_size_param
							&& param.getAnnotation(Result.class) == null
					        && param.getAnnotation(Constant.class) == null
							&& !Utils.isReturnParameter(method, param)
						)
						throw new RuntimeException(param + " has no Check, Result nor Constant annotation, is not the return parameter and no other parameter has" +
													" an @AutoSize annotation on it in method " + method);
				}
				if (param.getAnnotation(CachedReference.class) != null && param.getAnnotation(Result.class) != null)
					throw new RuntimeException(param + " can't be annotated with both CachedReference and Result");
				if (param.getAnnotation(BufferObject.class) != null && param.getAnnotation(Result.class) != null)
					throw new RuntimeException(param + " can't be annotated with both BufferObject and Result");
				//if (param.getAnnotation(Constant.class) != null)
					//throw new RuntimeException("Buffer parameter " + param + " cannot be Constant");
			} else {
				if (param.getAnnotation(BufferObject.class) != null)
					throw new RuntimeException(param + " type is not a buffer, but annotated as a BufferObject");
				if (param.getAnnotation(CachedReference.class) != null)
					throw new RuntimeException(param + " type is not a buffer, but annotated as a CachedReference");
			}
		}
	}

	private static void generateMethodsNativePointers(PrintWriter writer, Collection<? extends MethodDeclaration> methods) {
		for (MethodDeclaration method : methods) {
			if ( method.getAnnotation(Alternate.class) == null )
				generateMethodNativePointers(writer, method);
		}
	}

	private static void generateMethodNativePointers(PrintWriter writer, MethodDeclaration method) {
		if ( method.getAnnotation(Extern.class) == null )
			writer.print("static ");
		writer.println(Utils.getTypedefName(method) + " " + method.getSimpleName() + ";");
	}

	private void generateJavaSource(InterfaceDeclaration d, PrintWriter java_writer) throws IOException {
		java_writer.println("/* MACHINE GENERATED FILE, DO NOT EDIT */");
		java_writer.println();
		java_writer.println("package " + d.getPackage().getQualifiedName() + ";");
		java_writer.println();
		java_writer.println("import org.lwjgl.*;");
		java_writer.println("import java.nio.*;");
		Imports imports = d.getAnnotation(Imports.class);
		if ( imports != null ) {
			for ( String i : imports.value() )
				java_writer.println("import " + i + ";");
		}
		java_writer.println();
		Utils.printDocComment(java_writer, d);
		if ( d.getAnnotation(Private.class) == null )
			java_writer.print("public ");
		boolean is_final = Utils.isFinal(d);
		if (is_final)
			java_writer.write("final ");
		java_writer.print("class " + Utils.getSimpleClassName(d));
		Collection<InterfaceType> super_interfaces = d.getSuperinterfaces();
		if (super_interfaces.size() > 1)
			throw new RuntimeException(d + " extends more than one interface");
		if (super_interfaces.size() == 1) {
			InterfaceDeclaration super_interface = super_interfaces.iterator().next().getDeclaration();
			java_writer.print(" extends " + Utils.getSimpleClassName(super_interface));
		}
		java_writer.println(" {");
		FieldsGenerator.generateFields(java_writer, d.getFields());
		java_writer.println();
		if (is_final) {
			// Write private constructor to avoid instantiation
			java_writer.println("\tprivate " + Utils.getSimpleClassName(d) + "() {}");
		}
		if (d.getMethods().size() > 0 && !context_specific) {
			java_writer.println();
			java_writer.println("\tstatic native void " + Utils.STUB_INITIALIZER_NAME + "() throws LWJGLException;");
		}
		JavaMethodsGenerator.generateMethodsJava(env, type_map, java_writer, d, generate_error_checks, context_specific);
		java_writer.println("}");
		java_writer.close();
		String qualified_interface_name = Utils.getQualifiedClassName(d);
		env.getMessager().printNotice("Generated class " + qualified_interface_name);
	}

	private void generateNativeSource(InterfaceDeclaration d) throws IOException {
		String qualified_interface_name = Utils.getQualifiedClassName(d);
		String qualified_native_name = Utils.getNativeQualifiedName(qualified_interface_name)+ ".c";
		PrintWriter native_writer = env.getFiler().createTextFile(Filer.Location.CLASS_TREE, "", new File(qualified_native_name), "UTF-8");
		native_writer.println("/* MACHINE GENERATED FILE, DO NOT EDIT */");
		native_writer.println();
		native_writer.println("#include <jni.h>");
		type_map.printNativeIncludes(native_writer);
		native_writer.println();
		TypedefsGenerator.generateNativeTypedefs(type_map, native_writer, d.getMethods());
		native_writer.println();
		if (!context_specific) {
			generateMethodsNativePointers(native_writer, d.getMethods());
			native_writer.println();
		}
		NativeMethodStubsGenerator.generateNativeMethodStubs(env, type_map, native_writer, d, generate_error_checks, context_specific);
		if (!context_specific) {
			native_writer.print("JNIEXPORT void JNICALL " + Utils.getQualifiedNativeMethodName(qualified_interface_name, Utils.STUB_INITIALIZER_NAME));
			native_writer.println("(JNIEnv *env, jclass clazz) {");
			native_writer.println("\tJavaMethodAndExtFunction functions[] = {");
			RegisterStubsGenerator.generateMethodsNativeStubBind(native_writer, d, generate_error_checks, context_specific);
			native_writer.println("\t};");
			native_writer.println("\tint num_functions = NUMFUNCTIONS(functions);");
			native_writer.print("\t");
			native_writer.print(type_map.getRegisterNativesFunctionName());
			native_writer.println("(env, clazz, num_functions, functions);");
			native_writer.println("}");
		}
		native_writer.close();
		env.getMessager().printNotice("Generated C source " + qualified_interface_name);
	}

	public void visitInterfaceDeclaration(InterfaceDeclaration d) {
		final File input = d.getPosition().file();
		final File outputJava = new File(env.getOptions().get("-s") + '/' + d.getPackage().getQualifiedName().replace('.', '/'), Utils.getSimpleClassName(d) + ".java");

		PrintWriter java_writer = null;

		try {
			final Collection<? extends MethodDeclaration> methods = d.getMethods();
			if ( methods.size() == 0 && d.getFields().size() == 0 )
				return;

			// Skip this class if the output exists and the input has not been modified.
			if ( outputJava.exists() && Math.max(input.lastModified(), generatorLM) < outputJava.lastModified() )
				return;

			for ( final MethodDeclaration method : methods )
				validateMethod(method);
			java_writer = env.getFiler().createTextFile(Filer.Location.SOURCE_TREE, d.getPackage().getQualifiedName(), new File(Utils.getSimpleClassName(d) + ".java"), null);
			generateJavaSource(d, java_writer);

			if ( methods.size() > 0 ) {
				boolean noNative = true;
				for ( final MethodDeclaration method : methods ) {
					Alternate alt_annotation = method.getAnnotation(Alternate.class);
					if ( (alt_annotation == null || alt_annotation.nativeAlt()) && method.getAnnotation(Reuse.class) == null ) {
						noNative = false;
						break;
					}
				}
				if ( noNative )
					return;

				final String outputPath = env.getOptions().get("-d") + '/' + Utils.getNativeQualifiedName(Utils.getQualifiedClassName(d));
				final File outputNative = new File(outputPath + ".c");
				final File outputBackup = new File(outputPath + "_backup.c");

				// If the native file exists, rename.
				final ByteBuffer nativeBefore;
				if ( outputNative.exists() ) {
					nativeBefore = readFile(outputNative);
					outputNative.renameTo(outputBackup);
				} else
					nativeBefore = null;

				try {
					generateNativeSource(d);

					// If the native file did exist, compare with the new file. If they're the same,
					// reset the last modified time to avoid ridiculous C compilation times.
					if ( nativeBefore != null && outputNative.length() == nativeBefore.capacity() ) {
						final ByteBuffer nativeAfter = readFile(outputNative);
						boolean same = true;
						for ( int i = nativeBefore.position(); i < nativeBefore.limit(); i++ ) {
							if ( nativeBefore.get(i) != nativeAfter.get(i) ) {
								same = false;
								break;
							}
						}

						if ( same ) {
							outputNative.delete();
							outputBackup.renameTo(outputNative);
						}
					}
				} finally {
					if ( outputBackup.exists() )
						outputBackup.delete();
				}
			}
		} catch (Exception e) {
			// If anything goes wrong mid-gen, delete output to allow regen next time we run.
			if ( java_writer != null ) java_writer.close();
			if ( outputJava.exists() ) outputJava.delete();

			throw new RuntimeException(e);
		}
	}

	private static ByteBuffer readFile(final File file) throws IOException {
		final FileChannel channel = new FileInputStream(file).getChannel();

		final long bytesTotal = channel.size();
		final ByteBuffer buffer = ByteBuffer.allocateDirect((int)bytesTotal);

		long bytesRead = 0;
		do {
			bytesRead += channel.read(buffer);
		} while ( bytesRead < bytesTotal );
		buffer.flip();

		channel.close();

		return buffer;
	}

}
