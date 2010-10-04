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

import org.lwjgl.opencl.CLMem;

import java.nio.ByteBuffer;

import com.sun.mirror.type.*;
import com.sun.mirror.util.*;

/**
 * A TypeVisitor that translates (annotated) TypeMirrors to
 * java types (represented by a Class)
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */
public class JavaTypeTranslator implements TypeVisitor {
	private Class type;

	public Class getType() {
		return type;
	}

	public void visitAnnotationType(AnnotationType t) {
		throw new RuntimeException(t + " is not allowed");
	}

	public void visitArrayType(ArrayType t) {
		final TypeMirror componentType = t.getComponentType();
		if ( componentType instanceof PrimitiveType ) {
			type = getPrimitiveArrayClassFromKind(((PrimitiveType)componentType).getKind());
		} else {
			try {
				final Class c = Class.forName(t.getComponentType().toString());
				if ( CharSequence.class.isAssignableFrom(c) || ByteBuffer.class.isAssignableFrom(c) || org.lwjgl.PointerWrapper.class.isAssignableFrom(c) )
					type = Class.forName("[L" + t.getComponentType() + ";");
				else {
					throw new RuntimeException(t + " is not allowed");
				}
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static Class getPrimitiveClassFromKind(PrimitiveType.Kind kind) {
		switch ( kind ) {
			case LONG:
				return long.class;
			case INT:
				return int.class;
			case DOUBLE:
				return double.class;
			case FLOAT:
				return float.class;
			case SHORT:
				return short.class;
			case BYTE:
				return byte.class;
			case BOOLEAN:
				return boolean.class;
			default:
				throw new RuntimeException(kind + " is not allowed");
		}
	}

	private static Class getPrimitiveArrayClassFromKind(PrimitiveType.Kind kind) {
		switch ( kind ) {
			case LONG:
				return long[].class;
			case INT:
				return int[].class;
			case DOUBLE:
				return double[].class;
			case FLOAT:
				return float[].class;
			case SHORT:
				return short[].class;
			case BYTE:
				return byte[].class;
			case BOOLEAN:
				return boolean[].class;
			default:
				throw new RuntimeException(kind + " is not allowed");
		}
	}

	public void visitPrimitiveType(PrimitiveType t) {
		type = getPrimitiveClassFromKind(t.getKind());
	}

	public void visitDeclaredType(DeclaredType t) {
		throw new RuntimeException(t + " is not allowed");
	}

	public void visitEnumType(EnumType t) {
		throw new RuntimeException(t + " is not allowed");
	}

	public void visitClassType(ClassType t) {
		type = NativeTypeTranslator.getClassFromType(t);
	}

	public void visitInterfaceType(InterfaceType t) {
		type = NativeTypeTranslator.getClassFromType(t);
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
		type = void.class;
	}

	public void visitWildcardType(WildcardType t) {
		throw new RuntimeException(t + " is not allowed");
	}
}
