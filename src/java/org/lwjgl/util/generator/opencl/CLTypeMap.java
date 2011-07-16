/*
 * Copyright (c) 2002-2010 LWJGL Project
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

package org.lwjgl.util.generator.opencl;

/**
 *
 * OpenCL specific generator behaviour
 *
 * @author Spasi
 */

import org.lwjgl.PointerBuffer;
import org.lwjgl.util.generator.*;
import org.lwjgl.util.generator.opengl.GLreturn;

import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.nio.*;
import java.util.HashMap;
import java.util.Map;

import com.sun.mirror.declaration.AnnotationMirror;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.type.PrimitiveType;

public class CLTypeMap implements TypeMap {

	private static final Map<Class, PrimitiveType.Kind> native_types_to_primitive;

	static {
		native_types_to_primitive = new HashMap<Class, PrimitiveType.Kind>();
		native_types_to_primitive.put(cl_void.class, PrimitiveType.Kind.BYTE);
		native_types_to_primitive.put(cl_byte.class, PrimitiveType.Kind.BYTE);
		native_types_to_primitive.put(cl_char.class, PrimitiveType.Kind.BYTE);
		native_types_to_primitive.put(cl_uchar.class, PrimitiveType.Kind.BYTE);
		native_types_to_primitive.put(cl_short.class, PrimitiveType.Kind.SHORT);
		native_types_to_primitive.put(cl_bool.class, PrimitiveType.Kind.INT);
		native_types_to_primitive.put(cl_int.class, PrimitiveType.Kind.INT);
		native_types_to_primitive.put(cl_uint.class, PrimitiveType.Kind.INT);
		native_types_to_primitive.put(cl_long.class, PrimitiveType.Kind.LONG);
		native_types_to_primitive.put(size_t.class, PrimitiveType.Kind.LONG);
		native_types_to_primitive.put(cl_bitfield.class, PrimitiveType.Kind.LONG);
		native_types_to_primitive.put(cl_float.class, PrimitiveType.Kind.FLOAT);
		native_types_to_primitive.put(cl_double.class, PrimitiveType.Kind.DOUBLE);
	}

	public PrimitiveType.Kind getPrimitiveTypeFromNativeType(Class native_type) {
		PrimitiveType.Kind kind = native_types_to_primitive.get(native_type);
		if ( kind == null )
			throw new RuntimeException("Unsupported type " + native_type);
		return kind;
	}

	public void printCapabilitiesInit(final PrintWriter writer) {
	}

	public String getCapabilities() {
		return "CLCapabilities";
	}

	public String getAPIUtilParam(boolean comma) {
		return "";
	}

	public void printErrorCheckMethod(final PrintWriter writer, final MethodDeclaration method, final String tabs) {
		final Check check = method.getAnnotation(Check.class);
		if ( check != null ) // Get the error code from an IntBuffer output parameter
			writer.println(tabs + "Util.checkCLError(" + check.value() + ".get(" + check.value() + ".position()));");
		else {
			final Class return_type = Utils.getJavaType(method.getReturnType());
			if ( return_type == int.class )
				writer.println(tabs + "Util.checkCLError(__result);");
			else {
				boolean hasErrCodeParam = false;
				for ( final ParameterDeclaration param : method.getParameters() ) {
					if ( "errcode_ret".equals(param.getSimpleName()) && Utils.getJavaType(param.getType()) == IntBuffer.class ) {
						hasErrCodeParam = true;
						break;
					}
				}
				if ( hasErrCodeParam )
					throw new RuntimeException("A method is missing the @Check annotation: " + method.toString());
			}
		}
	}

	public String getRegisterNativesFunctionName() {
		return "extcl_InitializeClass";
	}

	public Signedness getSignednessFromType(Class type) {
		if ( cl_uint.class.equals(type) )
			return Signedness.UNSIGNED;
		else if ( cl_int.class.equals(type) )
			return Signedness.SIGNED;
		else
			return Signedness.NONE;
	}

	public String translateAnnotation(Class annotation_type) {
		if ( annotation_type.equals(cl_uint.class) || annotation_type.equals(cl_int.class) )
			return "i";
		else if ( annotation_type.equals(cl_short.class) )
			return "s";
		else if ( annotation_type.equals(cl_byte.class) )
			return "b";
		else if ( annotation_type.equals(cl_float.class) )
			return "f";
		else if ( annotation_type.equals(cl_double.class) )
			return "d";
		else
			throw new RuntimeException(annotation_type + " is not allowed");
	}

	public Class getNativeTypeFromPrimitiveType(PrimitiveType.Kind kind) {
		Class type;
		switch ( kind ) {
			case INT:
				type = cl_int.class;
				break;
			case DOUBLE:
				type = cl_double.class;
				break;
			case FLOAT:
				type = cl_float.class;
				break;
			case SHORT:
				type = cl_short.class;
				break;
			case BYTE:
				type = cl_byte.class;
				break;
			case LONG:
				type = cl_long.class;
				break;
			case BOOLEAN:
				type = cl_bool.class;
				break;
			default:
				throw new RuntimeException(kind + " is not allowed");
		}
		return type;
	}

	public Class<? extends Annotation> getVoidType() {
		return cl_void.class;
	}

	public Class<? extends Annotation> getStringElementType() {
		return cl_char.class;
	}

	public Class<? extends Annotation> getStringArrayType() {
		return cl_char.class;
	}

	public Class<? extends Annotation> getByteBufferArrayType() {
		return cl_uchar.class;
	}

	private static Class[] getValidBufferTypes(Class type) {
		if ( type.equals(IntBuffer.class) )
			return new Class[] { cl_int.class, cl_uint.class };
		else if ( type.equals(FloatBuffer.class) )
			return new Class[] { cl_float.class };
		else if ( type.equals(ByteBuffer.class) )
			return new Class[] { cl_byte.class, cl_char.class, cl_uchar.class, cl_void.class };
		else if ( type.equals(ShortBuffer.class) )
			return new Class[] { cl_short.class };
		else if ( type.equals(DoubleBuffer.class) )
			return new Class[] { cl_double.class };
		else if ( type.equals(LongBuffer.class) )
			return new Class[] { cl_long.class };
		else if ( type.equals(PointerBuffer.class) )
			return new Class[] { size_t.class };
		else
			return new Class[] { };
	}

	private static Class[] getValidPrimitiveTypes(Class type) {
		if ( type.equals(long.class) )
			return new Class[] { cl_long.class, size_t.class, cl_bitfield.class };
		else if ( type.equals(int.class) )
			return new Class[] { cl_int.class, cl_uint.class, cl_bool.class };
		else if ( type.equals(double.class) )
			return new Class[] { cl_double.class };
		else if ( type.equals(float.class) )
			return new Class[] { cl_float.class };
		else if ( type.equals(short.class) )
			return new Class[] { cl_short.class };
		else if ( type.equals(byte.class) )
			return new Class[] { cl_byte.class, cl_char.class, cl_uchar.class };
		else if ( type.equals(boolean.class) )
			return new Class[] { cl_bool.class };
		else if ( type.equals(void.class) )
			return new Class[] { cl_void.class };
		else
			return new Class[] { };
	}

	public String getTypedefPostfix() {
		return "CL_API_ENTRY ";
	}

	public String getFunctionPrefix() {
		return "CL_API_CALL";
	}

	public void printNativeIncludes(PrintWriter writer) {
		writer.println("#include \"extcl.h\"");
	}

	public Class[] getValidAnnotationTypes(Class type) {
		Class[] valid_types;
		if ( Buffer.class.isAssignableFrom(type) || PointerBuffer.class.isAssignableFrom(type) )
			valid_types = getValidBufferTypes(type);
		else if ( type.isPrimitive() )
			valid_types = getValidPrimitiveTypes(type);
		else if ( String.class.equals(type) )
			valid_types = new Class[] { cl_byte.class };
		else if ( org.lwjgl.PointerWrapper.class.isAssignableFrom(type) )
			valid_types = new Class[] { PointerWrapper.class };
		else if ( ByteBuffer[].class == type )
			valid_types = new Class[] { cl_char.class, cl_uchar.class };
		else if ( void.class.equals(type) )
			valid_types = new Class[] { GLreturn.class };
		else
			valid_types = new Class[] { };
		return valid_types;
	}

	public Class<? extends Annotation> getInverseType(Class type) {
		return null;
	}

	public String getAutoTypeFromAnnotation(AnnotationMirror annotation) {
		return null;
	}
}