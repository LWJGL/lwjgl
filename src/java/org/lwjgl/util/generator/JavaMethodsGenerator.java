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
 * This class generates the methods in the generated java source files.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */

import org.lwjgl.opengl.PointerWrapper;

import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;
import com.sun.mirror.type.*;

import java.io.*;
import java.util.*;
import java.nio.*;

public class JavaMethodsGenerator {
	private final static String SAVED_PARAMETER_POSTFIX = "_saved";

	public static void generateMethodsJava(AnnotationProcessorEnvironment env, TypeMap type_map, PrintWriter writer, InterfaceDeclaration interface_decl, boolean generate_error_checks, boolean context_specific) {
		for (MethodDeclaration method : interface_decl.getMethods())
			generateMethodJava(env, type_map, writer, interface_decl, method, generate_error_checks, context_specific);
	}

	private static void generateMethodJava(AnnotationProcessorEnvironment env, TypeMap type_map, PrintWriter writer, InterfaceDeclaration interface_decl, MethodDeclaration method, boolean generate_error_checks, boolean context_specific) {
		writer.println();
		if (Utils.isMethodIndirect(generate_error_checks, context_specific, method)) {
			if (method.getAnnotation(GenerateAutos.class) != null) {
				printMethodWithMultiType(env, type_map, writer, interface_decl, method, TypeInfo.getDefaultTypeInfoMap(method), Mode.AUTOS, generate_error_checks, context_specific);
			}
			Collection<Map<ParameterDeclaration, TypeInfo>> cross_product = TypeInfo.getTypeInfoCrossProduct(type_map, method);
			for (Map<ParameterDeclaration, TypeInfo> typeinfos_instance : cross_product) {
				printMethodWithMultiType(env, type_map, writer, interface_decl, method, typeinfos_instance, Mode.NORMAL, generate_error_checks, context_specific);
			}
		}
		printJavaNativeStub(writer, method, Mode.NORMAL, generate_error_checks, context_specific);
		if (Utils.hasMethodBufferObjectParameter(method)) {
			printMethodWithMultiType(env, type_map, writer, interface_decl, method, TypeInfo.getDefaultTypeInfoMap(method), Mode.BUFFEROBJECT, generate_error_checks, context_specific);
			printJavaNativeStub(writer, method, Mode.BUFFEROBJECT, generate_error_checks, context_specific);
		}
	}

	private static void printJavaNativeStub(PrintWriter writer, MethodDeclaration method, Mode mode, boolean generate_error_checks, boolean context_specific) {
		if (Utils.isMethodIndirect(generate_error_checks, context_specific, method)) {
			writer.print("\tprivate static native ");
		} else {
			Utils.printDocComment(writer, method);
			writer.print("\tpublic static native ");
		}
		printResultType(writer, method, true);
		writer.print(" " + Utils.getSimpleNativeMethodName(method, generate_error_checks, context_specific));
		if (mode == Mode.BUFFEROBJECT)
			writer.print(Utils.BUFFER_OBJECT_METHOD_POSTFIX);
		writer.print("(");
		boolean first_parameter = generateParametersJava(writer, method, TypeInfo.getDefaultTypeInfoMap(method), true, mode);
		if (context_specific) {
			if (!first_parameter)
				writer.print(", ");
			writer.print("long " + Utils.FUNCTION_POINTER_VAR_NAME);
		}
		writer.println(");");
	}

	private static boolean generateParametersJava(PrintWriter writer, MethodDeclaration method, Map<ParameterDeclaration, TypeInfo> typeinfos_instance,
			boolean native_stub, Mode mode) {
		boolean first_parameter = true;
		for (ParameterDeclaration param : method.getParameters()) {
			AnnotationMirror auto_annotation_mirror = Utils.getParameterAutoAnnotation(param);
			boolean hide_auto_parameter = mode == Mode.NORMAL && !native_stub && auto_annotation_mirror != null;
			if (hide_auto_parameter) {
				AutoType auto_type_annotation = param.getAnnotation(AutoType.class);
				if (auto_type_annotation != null) {
					ParameterDeclaration auto_parameter = Utils.findParameter(method, auto_type_annotation.value());
					TypeInfo auto_param_type_info = typeinfos_instance.get(auto_parameter);
					if (auto_param_type_info.getSignedness() == Signedness.BOTH) {
						if (!first_parameter)
							writer.print(", ");
						first_parameter = false;
						writer.print("boolean " + TypeInfo.UNSIGNED_PARAMETER_NAME);
					}
				}
			} else if (param.getAnnotation(Result.class) == null && (native_stub || param.getAnnotation(Constant.class) == null) &&
					(getAutoTypeParameter(method, param) == null || mode != Mode.AUTOS)) {
				TypeInfo type_info = typeinfos_instance.get(param);
				first_parameter = generateParameterJava(writer, param, type_info, native_stub, first_parameter, mode);
			}
		}
		TypeMirror result_type = Utils.getMethodReturnType(method);
		if ((native_stub && Utils.getNIOBufferType(result_type) != null) || Utils.needResultSize(method)) {
			if (!first_parameter)
				writer.print(", ");
			first_parameter = false;
			writer.print("long " + Utils.RESULT_SIZE_NAME);
		}
		if (method.getAnnotation(CachedResult.class) != null) {
			if (!first_parameter)
				writer.print(", ");
			first_parameter = false;
			printResultType(writer, method, native_stub);
			writer.print(" " + Utils.CACHED_BUFFER_NAME);
		}
		return first_parameter;
	}

	private static boolean generateParameterJava(PrintWriter writer, ParameterDeclaration param, TypeInfo type_info, boolean native_stub, boolean first_parameter, Mode mode) {
		Class buffer_type = Utils.getNIOBufferType(param.getType());
		if (!first_parameter)
			writer.print(", ");
		BufferObject bo_annotation = param.getAnnotation(BufferObject.class);
		if (bo_annotation != null && mode == Mode.BUFFEROBJECT) {
			if (buffer_type == null)
				throw new RuntimeException("type of " + param + " is not a nio Buffer parameter but is annotated as buffer object");
			writer.print("long " + param.getSimpleName() + Utils.BUFFER_OBJECT_PARAMETER_POSTFIX);
		} else {
			if ( native_stub && param.getAnnotation(GLpointer.class) != null )
				writer.print("long");
			else
				writer.print(type_info.getType().getSimpleName());
			writer.print(" " + param.getSimpleName());
			if (buffer_type != null && native_stub)
				writer.print(", int " + param.getSimpleName() + NativeMethodStubsGenerator.BUFFER_POSITION_POSTFIX);
		}
		return false;
	}

	private static void printBufferObjectCheck(PrintWriter writer, BufferKind kind, Mode mode) {
		String bo_check_method_name = kind.toString();
		writer.print("\t\t" + Utils.CHECKS_CLASS_NAME + ".ensure" + bo_check_method_name);
		if (mode == Mode.BUFFEROBJECT)
			writer.print("enabled");
		else
			writer.print("disabled");
		writer.println("(caps);");
	}

	private static void printBufferObjectChecks(PrintWriter writer, MethodDeclaration method, Mode mode) {
		EnumSet<BufferKind> check_set = EnumSet.noneOf(BufferKind.class);
		for (ParameterDeclaration param : method.getParameters()) {
			BufferObject bo_annotation = param.getAnnotation(BufferObject.class);
			if (bo_annotation != null)
				check_set.add(bo_annotation.value());
		}
		for (BufferKind kind : check_set)
			printBufferObjectCheck(writer, kind, mode);
	}

	private static void printMethodWithMultiType(AnnotationProcessorEnvironment env, TypeMap type_map, PrintWriter writer, InterfaceDeclaration interface_decl, MethodDeclaration method, Map<ParameterDeclaration, TypeInfo> typeinfos_instance, Mode mode, boolean generate_error_checks, boolean context_specific) {
		Utils.printDocComment(writer, method);
		writer.print("\tpublic static ");
		printResultType(writer, method, false);
		StripPostfix strip_annotation = method.getAnnotation(StripPostfix.class);
		String method_name = method.getSimpleName();
		if (strip_annotation != null && mode == Mode.NORMAL)
			method_name = getPostfixStrippedName(type_map, interface_decl, method);
		writer.print(" " + method_name + "(");
		generateParametersJava(writer, method, typeinfos_instance, false, mode);
		TypeMirror result_type = Utils.getMethodReturnType(method);
		writer.println(") {");
		if (context_specific) {
			writer.println("\t\tContextCapabilities caps = GLContext.getCapabilities();");
			writer.print("\t\tlong " + Utils.FUNCTION_POINTER_VAR_NAME + " = caps.");
			writer.println(Utils.getFunctionAddressName(interface_decl, method) + ";");
			writer.print("\t\tBufferChecks.checkFunctionAddress(");
			writer.println(Utils.FUNCTION_POINTER_VAR_NAME + ");");
		}
		Code code_annotation = method.getAnnotation(Code.class);
		if (code_annotation != null)
			writer.println(code_annotation.value());
		printBufferObjectChecks(writer, method, mode);
		printParameterChecks(writer, method, typeinfos_instance, mode);
		printParameterCaching(writer, interface_decl, method, mode);
		writer.print("\t\t");
		boolean has_result = !result_type.equals(env.getTypeUtils().getVoidType());
		if (has_result) {
			printResultType(writer, method, false);
			writer.print(" " + Utils.RESULT_VAR_NAME + " = ");

			if ( method.getAnnotation(GLpointer.class) != null )
				writer.print("new " + method.getReturnType() + "(");
		}
		writer.print(Utils.getSimpleNativeMethodName(method, generate_error_checks, context_specific));
		if (mode == Mode.BUFFEROBJECT)
			writer.print(Utils.BUFFER_OBJECT_METHOD_POSTFIX);
		writer.print("(");
		boolean first_parameter = printMethodCallArguments(writer, method, typeinfos_instance, mode);
		if (context_specific) {
			if (!first_parameter)
				writer.print(", ");
			writer.print(Utils.FUNCTION_POINTER_VAR_NAME);
		}
		if ( has_result && method.getAnnotation(GLpointer.class) != null )
			writer.print(")");
		writer.println(");");
		if (generate_error_checks && method.getAnnotation(NoErrorCheck.class) == null)
			writer.println("\t\t" + type_map.getErrorCheckMethodName() + ";");
		printNondirectParameterCopies(writer, method, mode);
		if (has_result)
			writer.println("\t\treturn " + Utils.RESULT_VAR_NAME + ";");
		writer.println("\t}");
	}

	private static String getExtensionPostfix(InterfaceDeclaration interface_decl) {
		String interface_simple_name = interface_decl.getSimpleName();
		Extension extension_annotation = interface_decl.getAnnotation(Extension.class);
		if (extension_annotation == null) {
			int underscore_index = interface_simple_name.indexOf("_");
			if (underscore_index != -1)
				return interface_simple_name.substring(0, underscore_index);
			else
				return "";
		} else
			return extension_annotation.postfix();
	}

	private static ParameterDeclaration getAutoTypeParameter(MethodDeclaration method, ParameterDeclaration target_parameter) {
		for (ParameterDeclaration param : method.getParameters()) {
			AnnotationMirror auto_annotation = Utils.getParameterAutoAnnotation(param);
			if (auto_annotation != null) {
				Class annotation_type = NativeTypeTranslator.getClassFromType(auto_annotation.getAnnotationType());
				String parameter_name;
				if (annotation_type.equals(AutoType.class))
					parameter_name = param.getAnnotation(AutoType.class).value();
				else if (annotation_type.equals(AutoSize.class))
					parameter_name = param.getAnnotation(AutoSize.class).value();
				else
					throw new RuntimeException("Unkown annotation type " + annotation_type);
				if (target_parameter.getSimpleName().equals(parameter_name))
					return param;
			}
		}
		return null;
	}

	private static boolean hasAnyParameterAutoTypeAnnotation(MethodDeclaration method, ParameterDeclaration target_param) {
		for (ParameterDeclaration param : method.getParameters()) {
			AutoType auto_type_annotation = param.getAnnotation(AutoType.class);
			if (auto_type_annotation != null) {
				ParameterDeclaration type_target_param = Utils.findParameter(method, auto_type_annotation.value());
				if (target_param.equals(type_target_param))
					return true;
			}
		}
		return false;
	}

	private static String getPostfixStrippedName(TypeMap type_map, InterfaceDeclaration interface_decl, MethodDeclaration method) {
		StripPostfix strip_annotation = method.getAnnotation(StripPostfix.class);
		ParameterDeclaration postfix_parameter = Utils.findParameter(method, strip_annotation.value());
		PostfixTranslator translator = new PostfixTranslator(type_map, postfix_parameter);
		postfix_parameter.getType().accept(translator);
		String postfix = translator.getSignature();
		String method_name = method.getSimpleName();
		String extension_postfix = "NULL".equals(strip_annotation.extension()) ? getExtensionPostfix(interface_decl) : strip_annotation.extension();
		String result;
		if (method_name.endsWith(postfix + "v" + extension_postfix))
			result = method_name.substring(0, method_name.length() - (postfix.length() + 1 + extension_postfix.length()));
		else if (method_name.endsWith(postfix + extension_postfix))
			result = method_name.substring(0, method_name.length() - (postfix.length() + extension_postfix.length()));
		else if ( method_name.endsWith("_v" + extension_postfix) )
			result = method_name.substring(0, method_name.length() - (2 + extension_postfix.length()));
		else if (method_name.endsWith("v" + extension_postfix))
			result = method_name.substring(0, method_name.length() - (1 + extension_postfix.length()));
		else
			throw new RuntimeException(method + " is specified as being postfix stripped on parameter " + postfix_parameter + ", but it's postfix is not '" + postfix + "' nor 'v'");
		return result + extension_postfix;
	}

	private static int getBufferElementSizeExponent(Class c) {
		if (IntBuffer.class.equals(c))
			return 2;
		else if (LongBuffer.class.equals(c))
			return 3;
		else if (DoubleBuffer.class.equals(c))
			return 3;
		else if (ShortBuffer.class.equals(c))
			return 1;
		else if (ByteBuffer.class.equals(c))
			return 0;
		else if (FloatBuffer.class.equals(c))
			return 2;
		else
			throw new RuntimeException(c + " is not allowed");
	}

	private static boolean printMethodCallArgument(PrintWriter writer, MethodDeclaration method, ParameterDeclaration param, Map<ParameterDeclaration, TypeInfo> typeinfos_instance, Mode mode, boolean first_parameter) {
		if (!first_parameter)
			writer.print(", ");
		AnnotationMirror auto_annotation = Utils.getParameterAutoAnnotation(param);
		Constant constant_annotation = param.getAnnotation(Constant.class);
		if (constant_annotation != null) {
			writer.print(constant_annotation.value());
		} else if (auto_annotation != null && mode == Mode.NORMAL) {
			Class param_type = NativeTypeTranslator.getClassFromType(auto_annotation.getAnnotationType());
			if (AutoType.class.equals(param_type)) {
				AutoType auto_type_annotation = param.getAnnotation(AutoType.class);
				String auto_parameter_name = auto_type_annotation.value();
				ParameterDeclaration auto_parameter = Utils.findParameter(method, auto_parameter_name);
				String auto_type = typeinfos_instance.get(auto_parameter).getAutoType();
				if (auto_type == null)
					throw new RuntimeException("No auto type for parameter " + param.getSimpleName() + " in method " + method);
				writer.print(auto_type);
			} else if (AutoSize.class.equals(param_type)) {
				AutoSize auto_type_annotation = param.getAnnotation(AutoSize.class);
				String auto_parameter_name = auto_type_annotation.value();
				ParameterDeclaration auto_target_param = Utils.findParameter(method, auto_parameter_name);
				TypeInfo auto_target_type_info = typeinfos_instance.get(auto_target_param);
				writer.print("(" + auto_parameter_name + ".remaining()");
				// Shift the remaining if the target parameter is multityped and there's no AutoType to track type
				boolean shift_remaining = !hasAnyParameterAutoTypeAnnotation(method, auto_target_param) && Utils.isParameterMultiTyped(auto_target_param);
				if (shift_remaining) {
					int shifting = getBufferElementSizeExponent(auto_target_type_info.getType());
					if (shifting > 0)
						writer.print(" << " + shifting);
				}
				writer.print(")");
				writer.print(auto_type_annotation.expression());
			} else
				throw new RuntimeException("Unknown auto annotation " + param_type);
		} else {
			if (mode == Mode.BUFFEROBJECT && param.getAnnotation(BufferObject.class) != null) {
				writer.print(param.getSimpleName() + Utils.BUFFER_OBJECT_PARAMETER_POSTFIX);
			} else {
				boolean hide_buffer = mode == Mode.AUTOS && getAutoTypeParameter(method, param) != null;
				if (hide_buffer)
					writer.print("null");
				else
					writer.print(param.getSimpleName());
				Class buffer_type = Utils.getNIOBufferType(param.getType());
				if (buffer_type != null) {
					writer.print(", ");
					if (!hide_buffer) {
						TypeInfo type_info = typeinfos_instance.get(param);
						Check check_annotation = param.getAnnotation(Check.class);
						int shifting;
						if (Utils.getNIOBufferType(param.getType()).equals(Buffer.class))
							shifting = getBufferElementSizeExponent(type_info.getType());
						else
							shifting = 0;
						writer.print(param.getSimpleName());
						if (check_annotation != null && check_annotation.canBeNull())
							writer.print(" != null ? " + param.getSimpleName());
						writer.print(".position()");
						if (shifting > 0)
							writer.print(" << " + shifting);
						if (check_annotation != null && check_annotation.canBeNull())
							writer.print(" : 0");
					} else
						writer.print("0");
				} else if ( param.getAnnotation(GLpointer.class) != null ) {
					writer.print(".getPointer()");
				}
			}
		}
		return false;
	}

	private static boolean printMethodCallArguments(PrintWriter writer, MethodDeclaration method, Map<ParameterDeclaration, TypeInfo> typeinfos_instance, Mode mode) {
		boolean first_parameter = true;
		for (ParameterDeclaration param : method.getParameters())
			if (param.getAnnotation(Result.class) == null) {
				first_parameter = printMethodCallArgument(writer, method, param, typeinfos_instance, mode, first_parameter);
			}
		if (Utils.getNIOBufferType(Utils.getMethodReturnType(method)) != null) {
			if (!first_parameter)
				writer.print(", ");
			first_parameter = false;
			AutoResultSize auto_result_size_annotation = method.getAnnotation(AutoResultSize.class);
			String result_size_expression;
			if (auto_result_size_annotation == null)
				result_size_expression = Utils.RESULT_SIZE_NAME;
			else
				result_size_expression = auto_result_size_annotation.value();
			Utils.printExtraCallArguments(writer, method, result_size_expression);
		}
		return first_parameter;
	}

	private static void printParameterCaching(PrintWriter writer, InterfaceDeclaration  interface_decl, MethodDeclaration method, Mode mode) {
		for (ParameterDeclaration param : method.getParameters()) {
			Class java_type = Utils.getJavaType(param.getType());
                        CachedReference cachedReference = param.getAnnotation(CachedReference.class);
			if (Buffer.class.isAssignableFrom(java_type) &&
                                        cachedReference != null &&
					(mode != Mode.BUFFEROBJECT || param.getAnnotation(BufferObject.class) == null) &&
					param.getAnnotation(Result.class) == null) {
				writer.print("\t\t" + Utils.CHECKS_CLASS_NAME + ".getReferences(caps).");
                                if(cachedReference.name().length() > 0) {
                                    writer.print(cachedReference.name());
                                } else {
                                    writer.print(Utils.getReferenceName(interface_decl, method, param));
                                }
                                if(cachedReference.index().length() > 0) {
                                    writer.print("[" + cachedReference.index() + "]");
                                }
				writer.println(" = " + param.getSimpleName() + ";");
			}
		}
	}

	private static void printNondirectParameterCopies(PrintWriter writer, MethodDeclaration method, Mode mode) {
		for (ParameterDeclaration param : method.getParameters()) {
			Class java_type = Utils.getJavaType(param.getType());
			if (Utils.isAddressableType(java_type) &&
					(mode != Mode.BUFFEROBJECT || param.getAnnotation(BufferObject.class) == null) &&
					(mode != Mode.AUTOS || getAutoTypeParameter(method, param) == null) &&
					param.getAnnotation(Result.class) == null) {
				if (Buffer.class.isAssignableFrom(java_type)) {
					boolean out_parameter = param.getAnnotation(OutParameter.class) != null;
					if (out_parameter)
						writer.println("\t\tNondirectBufferWrapper.copy(" + param.getSimpleName() + ", " + param.getSimpleName() + SAVED_PARAMETER_POSTFIX + ");");
				}
			}
		}
	}

	private static void printParameterChecks(PrintWriter writer, MethodDeclaration method, Map<ParameterDeclaration, TypeInfo> typeinfos, Mode mode) {
		for (ParameterDeclaration param : method.getParameters()) {
			Class java_type = Utils.getJavaType(param.getType());
			if (Utils.isAddressableType(java_type) &&
					(mode != Mode.BUFFEROBJECT || param.getAnnotation(BufferObject.class) == null) &&
					(mode != Mode.AUTOS || getAutoTypeParameter(method, param) == null) &&
					param.getAnnotation(Result.class) == null) {
				String check_value = null;
				boolean can_be_null = false;
				Check check_annotation = param.getAnnotation(Check.class);
				if (check_annotation != null) {
					check_value = check_annotation.value();
					can_be_null = check_annotation.canBeNull();
				}
				boolean null_terminated = param.getAnnotation(NullTerminated.class) != null;
				if (Buffer.class.isAssignableFrom(java_type)) {
					boolean indirect_buffer_allowed = param.getAnnotation(CachedReference.class) == null;
					boolean out_parameter = param.getAnnotation(OutParameter.class) != null;
					TypeInfo typeinfo = typeinfos.get(param);
					printParameterCheck(writer, param.getSimpleName(), typeinfo.getType().getSimpleName(), check_value, can_be_null, null_terminated, indirect_buffer_allowed, out_parameter);
				} else if (String.class.equals(java_type)) {
					if (!can_be_null)
						writer.println("\t\tBufferChecks.checkNotNull(" + param.getSimpleName() + ");");
				}
			}
		}
		if (method.getAnnotation(CachedResult.class) != null)
			printParameterCheck(writer, Utils.CACHED_BUFFER_NAME, null, null, true, false, false, false);
	}

	private static void printParameterCheck(PrintWriter writer, String name, String type, String check_value, boolean can_be_null, boolean null_terminated, boolean indirect_buffer_allowed, boolean out_parameter) {
		if (indirect_buffer_allowed && out_parameter) {
			writer.println("\t\t" + type + " " + name + SAVED_PARAMETER_POSTFIX + " = " + name + ";");
		}
		if (can_be_null) {
			writer.println("\t\tif (" + name + " != null)");
			writer.print("\t");
		}
		if (indirect_buffer_allowed) {
			writer.print("\t\t" + name + " = NondirectBufferWrapper.wrap");
			if (out_parameter)
				writer.print("NoCopy");
		} else
			writer.print("\t\tBufferChecks.check");
		if (check_value != null && !"".equals(check_value) ) {
			writer.print("Buffer(" + name + ", " + check_value);
		} else {
			writer.print("Direct(" + name);
		}
		writer.println(");");
		if (null_terminated)
			writer.println("\t\tBufferChecks.checkNullTerminated(" + name + ");");
	}

	private static void printResultType(PrintWriter writer, MethodDeclaration method, boolean native_stub) {
		if ( native_stub && method.getAnnotation(GLpointer.class) != null )
			writer.print("long");
		else
			writer.print(Utils.getMethodReturnType(method).toString());
	}
}
