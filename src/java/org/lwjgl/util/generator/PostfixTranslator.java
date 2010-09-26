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
 * A TypeVisitor that translates (annotated) TypeMirrors to
 * postfixes.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */

import com.sun.mirror.declaration.*;
import com.sun.mirror.type.*;
import com.sun.mirror.util.*;

import java.lang.annotation.Annotation;
import java.nio.*;

public class PostfixTranslator implements TypeVisitor {
	private final StringBuilder signature = new StringBuilder();
	private final Declaration declaration;
	private final TypeMap type_map;

	public PostfixTranslator(TypeMap type_map, Declaration declaration) {
		this.declaration = declaration;
		this.type_map = type_map;
	}

	public String getSignature() {
		return signature.toString();
	}

	public void visitAnnotationType(AnnotationType t) {
		throw new RuntimeException(t + " is not allowed");
	}

	public void visitArrayType(ArrayType t) {
		throw new RuntimeException(t + " is not allowed");
	}

	private static PrimitiveType.Kind getPrimitiveKindFromBufferClass(Class c) {
		if (IntBuffer.class.equals(c) || int.class.equals(c) )
			return PrimitiveType.Kind.INT;
		else if (DoubleBuffer.class.equals(c) || double.class.equals(c) )
			return PrimitiveType.Kind.DOUBLE;
		else if (ShortBuffer.class.equals(c) || short.class.equals(c) )
			return PrimitiveType.Kind.SHORT;
		else if (ByteBuffer.class.equals(c) || byte.class.equals(c) )
			return PrimitiveType.Kind.BYTE;
		else if (FloatBuffer.class.equals(c) || float.class.equals(c))
			return PrimitiveType.Kind.FLOAT;
		else if (LongBuffer.class.equals(c) || long.class.equals(c) )
			return PrimitiveType.Kind.LONG;
		else
			throw new RuntimeException(c + " is not allowed");
	}

	public void visitClassType(ClassType t) {
		Class<?> c = NativeTypeTranslator.getClassFromType(t);
		PrimitiveType.Kind kind = getPrimitiveKindFromBufferClass(c);
		visitPrimitiveTypeKind(kind);
	}

	public void visitDeclaredType(DeclaredType t) {
		throw new RuntimeException(t + " is not allowed");
	}

	public void visitEnumType(EnumType t) {
		throw new RuntimeException(t + " is not allowed");
	}

	public void visitInterfaceType(InterfaceType t) {
		throw new RuntimeException(t + " is not allowed");
	}

	private boolean translateAnnotation(AnnotationMirror annotation) {
		NativeType native_type = NativeTypeTranslator.getAnnotation(annotation, NativeType.class);
		if (native_type != null) {
			Class<? extends Annotation> annotation_class = NativeTypeTranslator.getClassFromType(annotation.getAnnotationType());
			signature.append(type_map.translateAnnotation(annotation_class));
			return true;
		} else
			return false;
	}

	private boolean translateAnnotations() {
		boolean result = false;
		for (AnnotationMirror annotation : Utils.getSortedAnnotations(declaration.getAnnotationMirrors()))
			if (translateAnnotation(annotation)) {
				if (result)
					throw new RuntimeException("Multiple native types");
				result = true;
			}
		return result;
	}

	public void visitPrimitiveType(PrimitiveType t) {
		visitPrimitiveTypeKind(t.getKind());
	}

	private void visitPrimitiveTypeKind(PrimitiveType.Kind kind) {
		boolean annotated_translation = translateAnnotations();
		if (annotated_translation)
			return;
		// No annotation type was specified, fall back to default
		String type;
		switch (kind) {
			case INT:
				type = "i";
				break;
			case DOUBLE:
				type = "d";
				break;
			case FLOAT:
				type = "f";
				break;
			case SHORT:
				type = "s";
				break;
			case BYTE:
				type = "b";
				break;
			case LONG:
				type = "i64";
				break;
			default:
				throw new RuntimeException(kind + " is not allowed");
		}
		signature.append(type);
	}

	public void visitReferenceType(ReferenceType t) {
		throw new RuntimeException(t + " is not allowed");
	}

	public void visitTypeMirror(TypeMirror t) {
		throw new RuntimeException(t + " is not allowed");
	}

	public void visitTypeVariable(TypeVariable t) {
		throw new RuntimeException(t + " is not allowed");
	}

	public void visitVoidType(VoidType t) {
	}

	public void visitWildcardType(WildcardType t) {
		throw new RuntimeException(t + " is not allowed");
	}
}