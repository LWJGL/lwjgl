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

import com.sun.mirror.declaration.*;
import com.sun.mirror.type.*;

import java.io.*;
import java.util.*;

public class FieldsGenerator {
	private static void validateField(FieldDeclaration field) {
		Collection<Modifier> modifiers = field.getModifiers();
		if (modifiers.size() != 3 || !modifiers.contains(Modifier.PUBLIC) || !modifiers.contains(Modifier.STATIC) ||
				!modifiers.contains(Modifier.FINAL))
			throw new RuntimeException("Field " + field.getSimpleName() + " is not declared public static final");
		TypeMirror field_type = field.getType();
		if (!(field_type instanceof PrimitiveType))
			throw new RuntimeException("Field " + field.getSimpleName() + " is not a primitive type");
		PrimitiveType field_type_prim = (PrimitiveType)field_type;
		if (field_type_prim.getKind() != PrimitiveType.Kind.INT)
			throw new RuntimeException("Field " + field.getSimpleName() + " is not of type 'int'");
		Integer field_value = (Integer)field.getConstantValue();
		if (field_value == null)
			throw new RuntimeException("Field " + field.getSimpleName() + " has no initial value");
	}

	private static void generateField(PrintWriter writer, FieldDeclaration field) {
		Integer field_value = (Integer)field.getConstantValue();
		validateField(field);
		String field_value_string = Integer.toHexString(field_value);
		Utils.printDocComment(writer, field);
		// Print field declaration
		writer.println("\tpublic static final " + field.getType().toString() + " " + field.getSimpleName() + " = 0x" + field_value_string + ";");
	}

	public static void generateFields(PrintWriter writer, Collection<FieldDeclaration> fields) {
		for (FieldDeclaration field : fields)
			generateField(writer, field);
	}

}
