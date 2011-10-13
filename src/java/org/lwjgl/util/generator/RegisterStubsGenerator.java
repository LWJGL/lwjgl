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
 * This class generates the initNatives native function.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */

import com.sun.mirror.declaration.*;
import com.sun.mirror.type.*;

import java.io.*;
import java.util.*;

public class RegisterStubsGenerator {
	public static void generateMethodsNativeStubBind(PrintWriter writer, InterfaceDeclaration d, boolean generate_error_checks, boolean context_specific) {
		Iterator<? extends MethodDeclaration> it = d.getMethods().iterator();
		while (it.hasNext()) {
			MethodDeclaration method = it.next();
			Alternate alt_annotation = method.getAnnotation(Alternate.class);
			if ( (alt_annotation != null && (!alt_annotation.nativeAlt() || alt_annotation.skipNative())) || method.getAnnotation(Reuse.class) != null )
				continue;
			EnumSet<Platform> platforms;
			PlatformDependent platform_annotation = method.getAnnotation(PlatformDependent.class);
			if (platform_annotation != null)
				platforms = EnumSet.copyOf(Arrays.asList(platform_annotation.value()));
			else
				platforms = EnumSet.of(Platform.ALL);
			for (Platform platform : platforms) {
				platform.printPrologue(writer);
				boolean has_buffer_parameter = Utils.hasMethodBufferObjectParameter(method);
				printMethodNativeStubBind(writer, d, method, platform, Mode.NORMAL, it.hasNext() || has_buffer_parameter, generate_error_checks, context_specific);
				if (has_buffer_parameter) {
					printMethodNativeStubBind(writer, d, method, platform, Mode.BUFFEROBJECT, it.hasNext(), generate_error_checks, context_specific);
				}
				platform.printEpilogue(writer);
			}
		}
		writer.println();
	}

	private static String getTypeSignature(TypeMirror type, boolean add_position_signature) {
		SignatureTranslator v = new SignatureTranslator(add_position_signature);
		type.accept(v);
		return v.getSignature();
	}

	private static String getMethodSignature(MethodDeclaration method, Mode mode) {
		Collection<ParameterDeclaration> params = method.getParameters();
		String signature = "(";
		for (ParameterDeclaration param : params) {
			if ( param.getAnnotation(Result.class) != null || (param.getAnnotation(Helper.class) != null && !param.getAnnotation(Helper.class).passToNative()) )
				continue;

			final Constant constant_annotation = param.getAnnotation(Constant.class);
			if ( constant_annotation != null && constant_annotation.isNative() )
				continue;

			if (mode == Mode.BUFFEROBJECT && param.getAnnotation(BufferObject.class) != null)
				signature += "J";
			else
				signature += getTypeSignature(param.getType(), true);
		}

		final TypeMirror result_type = Utils.getMethodReturnType(method);
		final CachedResult cached_result_annotation = method.getAnnotation(CachedResult.class);
		final AutoSize auto_size_annotation = method.getAnnotation(AutoSize.class);

		final boolean isNIOBuffer = Utils.getNIOBufferType(result_type) != null;
		if ( isNIOBuffer && (auto_size_annotation == null || !auto_size_annotation.isNative()) )
			signature += "J";

		final String result_type_signature = isNIOBuffer ? "Ljava/nio/ByteBuffer;" : getTypeSignature(result_type, false);
		if ( cached_result_annotation != null )
			signature += result_type_signature;

		signature += ")";
		signature += result_type_signature;
		return signature;
	}

	private static void printMethodNativeStubBind(PrintWriter writer, InterfaceDeclaration d, MethodDeclaration method, Platform platform, Mode mode, boolean has_more, boolean generate_error_checks, boolean context_specific) {
		writer.print("\t\t{\"" + Utils.getSimpleNativeMethodName(method, generate_error_checks, context_specific));
		if (mode == Mode.BUFFEROBJECT)
			writer.print(Utils.BUFFER_OBJECT_METHOD_POSTFIX);
		writer.print("\", \"" + getMethodSignature(method, mode) + "\", (void *)&");
		writer.print(Utils.getQualifiedNativeMethodName(Utils.getQualifiedClassName(d), method, generate_error_checks, context_specific));
		if (mode == Mode.BUFFEROBJECT)
			writer.print(Utils.BUFFER_OBJECT_METHOD_POSTFIX);

		final Alternate alt_annotation = method.getAnnotation(Alternate.class);
		final String methodName = alt_annotation == null ? method.getSimpleName() : alt_annotation.value();
		String opengl_handle_name = methodName.replaceFirst("gl", platform.getPrefix());
		writer.print(", \"" + opengl_handle_name + "\", (void *)&" + methodName + ", " + (method.getAnnotation(Optional.class) == null ? "false" : "true") + "}");
		if (has_more)
			writer.println(",");
	}

}
