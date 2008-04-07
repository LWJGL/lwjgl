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

/**
 *
 * This class generates the functions in the native source files.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */

import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;
import com.sun.mirror.type.*;

import java.io.*;
import java.util.*;
import java.nio.*;

public class NativeMethodStubsGenerator {
	private static final String BUFFER_ADDRESS_POSTFIX = "_address";
	public static final String BUFFER_POSITION_POSTFIX = "_position";

	public static void generateNativeMethodStubs(AnnotationProcessorEnvironment env, TypeMap type_map, PrintWriter writer, InterfaceDeclaration d, boolean generate_error_checks, boolean context_specific) {
		for (MethodDeclaration method : d.getMethods()) {
			generateMethodStub(env, type_map, writer, Utils.getQualifiedClassName(d), method, Mode.NORMAL, generate_error_checks, context_specific);
			if (Utils.hasMethodBufferObjectParameter(method))
				generateMethodStub(env, type_map, writer, Utils.getQualifiedClassName(d), method, Mode.BUFFEROBJECT, generate_error_checks, context_specific);
		}
	}

	private static void generateParameters(PrintWriter writer, Collection<ParameterDeclaration> params, Mode mode) {
		for (ParameterDeclaration param : params)
			if (param.getAnnotation(Result.class) == null)
				generateParameter(writer, param, mode);
	}

	private static void generateParameter(PrintWriter writer, ParameterDeclaration param, Mode mode) {
		writer.print(", ");
		if (mode == Mode.BUFFEROBJECT && param.getAnnotation(BufferObject.class) != null) {
			writer.print("jlong " + param.getSimpleName() + Utils.BUFFER_OBJECT_PARAMETER_POSTFIX);
		} else {
			JNITypeTranslator translator = new JNITypeTranslator();
			param.getType().accept(translator);
			writer.print(translator.getSignature() + " " + param.getSimpleName());
			if (Utils.getNIOBufferType(param.getType()) != null)
				writer.print(", jint " + param.getSimpleName() + BUFFER_POSITION_POSTFIX);
		}
	}

	private static void generateMethodStub(AnnotationProcessorEnvironment env, TypeMap type_map, PrintWriter writer, String interface_name, MethodDeclaration method, Mode mode, boolean generate_error_checks, boolean context_specific) {
		TypeMirror result_type = Utils.getMethodReturnType(method);
		JNITypeTranslator translator = new JNITypeTranslator();
		result_type.accept(translator);
		if (!context_specific)
			writer.print("static ");
		else
			writer.print("JNIEXPORT ");
		writer.print(translator.getSignature() + " JNICALL ");
		writer.print(Utils.getQualifiedNativeMethodName(interface_name, method, generate_error_checks, context_specific));
		if (mode == Mode.BUFFEROBJECT)
			writer.print(Utils.BUFFER_OBJECT_METHOD_POSTFIX);
		writer.print("(JNIEnv *env, jclass clazz");
		generateParameters(writer, method.getParameters(), mode);
		if (Utils.getNIOBufferType(result_type) != null) {
			writer.print(", jlong " + Utils.RESULT_SIZE_NAME);
			if (method.getAnnotation(CachedResult.class) != null)
				writer.print(", jobject " + Utils.CACHED_BUFFER_NAME);
		}
		if (context_specific) {
			writer.print(", jlong " + Utils.FUNCTION_POINTER_VAR_NAME);
		}
		writer.println(") {");
		generateBufferParameterAddresses(type_map, writer, method, mode);
		if (context_specific) {
			String typedef_name = Utils.getTypedefName(method);
			writer.print("\t" + typedef_name + " " + method.getSimpleName());
			writer.print(" = (" + typedef_name + ")((intptr_t)");
			writer.println(Utils.FUNCTION_POINTER_VAR_NAME + ");");
		}
		writer.print("\t");
		if (!result_type.equals(env.getTypeUtils().getVoidType())) {
			Declaration return_declaration;
			ParameterDeclaration result_param = Utils.getResultParameter(method);
			if (result_param != null)
				return_declaration = result_param;
			else
				return_declaration = method;
			NativeTypeTranslator native_translator = new NativeTypeTranslator(type_map, return_declaration);
			result_type.accept(native_translator);
			writer.print(native_translator.getSignature() + " " + Utils.RESULT_VAR_NAME);
			if (result_param != null) {
				writer.println(";");
				writer.print("\t");
			} else
				writer.print(" = ");
		}
		writer.print(method.getSimpleName() + "(");
		generateCallParameters(writer, type_map, method.getParameters());
		writer.print(")");
		writer.println(";");
		generateStringDeallocations(writer, method.getParameters());
		if (!result_type.equals(env.getTypeUtils().getVoidType())) {
			writer.print("\treturn ");
			Class java_result_type = Utils.getJavaType(result_type);
			if (Buffer.class.isAssignableFrom(java_result_type)) {
				if (method.getAnnotation(CachedResult.class) != null)
					writer.print("safeNewBufferCached(env, ");
				else
					writer.print("safeNewBuffer(env, ");
			} else if (String.class.equals(java_result_type))
				writer.print("NewStringNativeUnsigned(env, ");
			writer.print(Utils.RESULT_VAR_NAME);
			if (Buffer.class.isAssignableFrom(java_result_type)) {
				writer.print(", ");
				Utils.printExtraCallArguments(writer, method, Utils.RESULT_SIZE_NAME);
			}
			if (Buffer.class.isAssignableFrom(java_result_type) ||
				String.class.equals(java_result_type))
				writer.print(")");
			writer.println(";");
		}
		writer.println("}");
		writer.println();
	}

	private static void generateCallParameters(PrintWriter writer, TypeMap type_map, Collection<ParameterDeclaration> params) {
		if (params.size() > 0) {
			Iterator<ParameterDeclaration> it = params.iterator();
			generateCallParameter(writer, type_map, it.next());
			while (it.hasNext()) {
				writer.print(", ");
				generateCallParameter(writer, type_map, it.next());
			}
		}
	}

	private static void generateCallParameter(PrintWriter writer, TypeMap type_map, ParameterDeclaration param) {
		boolean is_indirect = param.getAnnotation(Indirect.class) != null;
		if (is_indirect) {
			writer.print("(");
			NativeTypeTranslator translator = new NativeTypeTranslator(type_map, param);
			param.getType().accept(translator);
			writer.print(translator.getSignature());
			writer.print("*)");
		}
		if (param.getAnnotation(Result.class) != null || is_indirect)
			writer.print("&");
		if (param.getAnnotation(Result.class) != null) {
			writer.print(Utils.RESULT_VAR_NAME);
		} else {
			writer.print(param.getSimpleName());
			if (Utils.isAddressableType(param.getType())) {
				writer.print(BUFFER_ADDRESS_POSTFIX);
			}
		}
	}

	private static void generateStringDeallocations(PrintWriter writer, Collection<ParameterDeclaration> params) {
		for (ParameterDeclaration param : params)
			if (Utils.getJavaType(param.getType()).equals(String.class) &&
					param.getAnnotation(Result.class) == null)
				writer.println("\tfree(" + param.getSimpleName() + BUFFER_ADDRESS_POSTFIX + ");");
	}

	private static void generateBufferParameterAddresses(TypeMap type_map, PrintWriter writer, MethodDeclaration method, Mode mode) {
		for (ParameterDeclaration param : method.getParameters())
			if (Utils.isAddressableType(param.getType()) &&
					param.getAnnotation(Result.class) == null)
				generateBufferParameterAddress(type_map, writer, method,  param, mode);
	}

	private static void generateBufferParameterAddress(TypeMap type_map, PrintWriter writer, MethodDeclaration method, ParameterDeclaration param, Mode mode) {
		NativeTypeTranslator translator = new NativeTypeTranslator(type_map, param);
		param.getType().accept(translator);
		writer.print("\t" + translator.getSignature() + param.getSimpleName());
		writer.print(BUFFER_ADDRESS_POSTFIX + " = ((");
		writer.print(translator.getSignature());
		Check check_annotation = param.getAnnotation(Check.class);
		writer.print(")");
		if (mode == Mode.BUFFEROBJECT && param.getAnnotation(BufferObject.class) != null) {
			writer.print("offsetToPointer(" + param.getSimpleName() + Utils.BUFFER_OBJECT_PARAMETER_POSTFIX + "))");
		} else {
			Class java_type = Utils.getJavaType(param.getType());
			if (Buffer.class.isAssignableFrom(java_type)) {
				boolean explicitly_byte_sized = java_type.equals(Buffer.class) ||
					translator.getAnnotationType().equals(type_map.getVoidType());
				if (explicitly_byte_sized)
					writer.print("(((char *)");
				if (method.getAnnotation(GenerateAutos.class) != null || (check_annotation != null && check_annotation.canBeNull())) {
					writer.print("safeGetBufferAddress(env, " + param.getSimpleName());
				} else {
					writer.print("(*env)->GetDirectBufferAddress(env, " + param.getSimpleName());
				}
				writer.print("))");
				writer.print(" + " + param.getSimpleName() + BUFFER_POSITION_POSTFIX);
				if (explicitly_byte_sized)
					writer.print("))");
			} else if (java_type.equals(String.class)) {
				writer.print("GetStringNativeChars(env, " + param.getSimpleName() + "))");
			} else
				throw new RuntimeException("Illegal type " + java_type);
		}
		writer.println(";");
	}
}
