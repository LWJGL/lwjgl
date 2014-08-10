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

import java.nio.Buffer;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.util.SimpleTypeVisitor6;
import org.lwjgl.PointerBuffer;

/**
 *
 * A TypeVisitor that translates TypeMirrors to JNI
 * type strings.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */
public class JNITypeTranslator extends SimpleTypeVisitor6 {

	private final StringBuilder signature = new StringBuilder();

	private boolean objectReturn;

	public String getSignature() {
		return signature.toString();
	}

	public String getReturnSignature() {
		return objectReturn ? "jobject" : signature.toString();
	}

	public Object visitArray(ArrayType t, Object o) {
		final String className = t.getComponentType().toString();
		if ( "java.lang.CharSequence".equals(className) )
			signature.append("jlong");
		else if ( "java.nio.ByteBuffer".equals(className) )
			signature.append("jobjectArray");
		else if ( "org.lwjgl.opencl.CLMem".equals(className) )
			signature.append("jobjectArray");
		else
			throw new RuntimeException(t + " is not allowed");
                return className;
	}

	public void visitClassType(DeclaredType t) {
		final Class<?> type = Utils.getJavaType(t.asElement().asType());
		if ( Buffer.class.isAssignableFrom(type) || PointerBuffer.class.isAssignableFrom(type) ) {
			signature.append("jlong");
			objectReturn = true;
		} else
			signature.append("jobject");
	}

	public Object visitDeclared(DeclaredType t, Object o) {
            if(t.asElement().getKind().isClass())
                visitClassType(t);
            return signature;
	}

	public Object visitPrimitive(PrimitiveType t, Object o) {
		String type;
		switch (t.getKind()) {
			case LONG:
				type = "jlong";
				break;
			case INT:
				type = "jint";
				break;
			case FLOAT:
				type = "jfloat";
				break;
			case SHORT:
				type = "jshort";
				break;
			case BYTE:
				type = "jbyte";
				break;
			case DOUBLE:
				type = "jdouble";
				break;
			case BOOLEAN:
				type = "jboolean";
				break;
			default:
				throw new RuntimeException(t + " is not allowed");
		}
		signature.append(type);
                return signature;
	}

	public Object visitNoType(NoType t, Object o) {
		signature.append(t.toString());
                return signature;
	}
	
}
