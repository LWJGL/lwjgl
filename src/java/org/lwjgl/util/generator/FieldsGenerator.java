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

import java.io.PrintWriter;
import java.util.Collection;

import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.type.PrimitiveType;
import com.sun.mirror.type.TypeMirror;

public class FieldsGenerator {

	private static void validateField(FieldDeclaration field) {
		// Check if field is "public static final"
		Collection<Modifier> modifiers = field.getModifiers();
		if ( modifiers.size() != 3
		     || !modifiers.contains(Modifier.PUBLIC)
		     || !modifiers.contains(Modifier.STATIC)
		     || !modifiers.contains(Modifier.FINAL) ) {
			throw new RuntimeException("Field " + field.getSimpleName() + " is not declared public static final");
		}

		// Check suported types (int, long, float, String)
		TypeMirror field_type = field.getType();
		if ( field_type instanceof PrimitiveType ) {
			PrimitiveType field_type_prim = (PrimitiveType)field_type;
			PrimitiveType.Kind field_kind = field_type_prim.getKind();
			if ( field_kind != PrimitiveType.Kind.INT
			     && field_kind != PrimitiveType.Kind.LONG
			     && field_kind != PrimitiveType.Kind.FLOAT
			     && field_kind != PrimitiveType.Kind.BYTE ) {
				throw new RuntimeException("Field " + field.getSimpleName() + " is not of type 'int', 'long' or 'float'");
			}
		} else if ( "java.lang.String".equals(field_type.toString()) ) {
		} else {
			throw new RuntimeException("Field " + field.getSimpleName() + " is not a primitive type or String");
		}

		Object field_value = field.getConstantValue();
		if ( field_value == null ) {
			throw new RuntimeException("Field " + field.getSimpleName() + " has no initial value");
		}
	}

	private static void generateField(PrintWriter writer, FieldDeclaration field, FieldDeclaration prev_field) {
		validateField(field);

		Object value = field.getConstantValue();
		String field_value_string;
		Class field_value_class = value.getClass();
		if ( field_value_class.equals(Integer.class) ) {
			field_value_string = "0x" + Integer.toHexString((Integer)field.getConstantValue()).toUpperCase();
		} else if ( field_value_class.equals(Long.class) ) {
			field_value_string = "0x" + Long.toHexString((Long)field.getConstantValue()).toUpperCase() + 'L';
		} else if ( field_value_class.equals(Float.class) ) {
			field_value_string = field.getConstantValue() + "f";
		} else if ( value.getClass().equals(Byte.class) ) {
			field_value_string = "0x" + Integer.toHexString((Byte)field.getConstantValue()).toUpperCase();
		} else if ( field_value_class.equals(String.class) ) {
			field_value_string = "\"" + field.getConstantValue() + "\"";
		} else {
			throw new RuntimeException("Field is of unexpected type. This means there is a bug in validateField().");
		}

		boolean hadDoc = prev_field != null && prev_field.getDocComment() != null;
		boolean hasDoc = field.getDocComment() != null;
		boolean newBatch = prev_field == null || !prev_field.getType().equals(field.getType()) || (!hadDoc && field.getDocComment() != null) || (hadDoc && hasDoc && !prev_field.getDocComment().equals(field.getDocComment()));

		// Print field declaration
		if ( newBatch ) {
			if ( prev_field != null )
				writer.println(";\n");

			Utils.printDocComment(writer, field);
			writer.print("\tpublic static final " + field.getType().toString() + " " + field.getSimpleName() + " = " + field_value_string);
		} else
			writer.print(",\n\t\t" + field.getSimpleName() + " = " + field_value_string);
	}

	public static void generateFields(PrintWriter writer, Collection<FieldDeclaration> fields) {
		if ( 0 < fields.size() ) {
			writer.println();
			FieldDeclaration prev_field = null;
			for ( FieldDeclaration field : fields ) {
				generateField(writer, field, prev_field);
				prev_field = field;
			}
			writer.println(";");
		}
	}

}
