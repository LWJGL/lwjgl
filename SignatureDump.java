import java.lang.reflect.*;

public final class SignatureDump {
	public final static void main(String[] args) {
		String class_name = args[0];
		try {
			Class clazz = Class.forName(class_name);
			Method[] methods = clazz.getDeclaredMethods();
			System.out.println("\tJavaMethodAndExtFunction functions[] = {");
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				int modifiers = method.getModifiers();
				if (!Modifier.isNative(modifiers))
					continue;
				System.out.print("\t\t");
				System.out.print("{\"");
				System.out.print(method.getName());
				System.out.print("\", \"");
				System.out.print(getMethodSignature(method));
				System.out.print("\", (void*)&");
				System.out.print(getMethodMangled(class_name, method));
				System.out.print(", ");
				String gl_name = getExtFunctionName(method);
				if (gl_name != null) {
					System.out.print("\"");
					System.out.print(gl_name);
					System.out.print("\"");
				} else
					System.out.print("NULL");
				System.out.print(", ");
				if (gl_name != null) {
					System.out.print("(void**)&");
					System.out.print(gl_name);
				} else
					System.out.print("NULL");
				System.out.print("}");
                                if (i != methods.length - 1)
					System.out.print(",");
				System.out.println();
			}
			System.out.print("\t};");
			System.out.println();
			System.out.println("\tint num_functions = NUMFUNCTIONS(functions);");
			System.out.print("\tjclass clazz = ext_ResetClass(env, \"");
			String class_name_mangled = clazz.getName().replaceAll("\\.", "/");
			System.out.print(class_name_mangled);
			System.out.println("\");");
			System.out.println("\tif (extgl_Extensions.)");
			System.out.println("\t\text_InitializeClass(env, clazz, ext_set, <ext_name>, num_functions, functions);");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private final static String getExtFunctionName(Method method) {
		String name = method.getName();
		if (name.charAt(0) == 'n')
			name = name.substring(1);
		if (name.endsWith("VBO"))
			return null;
		return name;
	}

	private final static String getMethodMangled(String class_name, Method method) {
                class_name = class_name.replaceAll("\\.", "_");
		String mangled = "Java_" + class_name + "_" + method.getName();
                return mangled;
	}

	private final static String getMethodSignature(Method method) {
		Class[] arg_types = method.getParameterTypes();
		String signature = "(";
		for (int i = 0; i < arg_types.length; i++)
			signature += getTypeSignature(arg_types[i]);
		Class return_type = method.getReturnType();
		signature += ")";
		signature += getTypeSignature(return_type);
		return signature;
	}

	private final static String getTypeSignature(Class type) {
		if (type.equals(Boolean.class))
			return "Z";
		else if (type.equals(int.class))
			return "I";
		else if (type.equals(float.class))
			return "F";
		else if (type.equals(short.class))
			return "S";
		else if (type.equals(double.class))
			return "D";
		else if (type.equals(boolean.class))
			return "Z";
		else if (type.equals(byte.class))
			return "B";
		else if (type.equals(void.class))
			return "V";
		else if (type.equals(char.class))
			throw new RuntimeException();
		else if (type.equals(long.class))
			throw new RuntimeException();
		else if (type.isArray())
			throw new RuntimeException();
		else {
			String type_name = type.getName().replaceAll("\\.", "/");
			return "L" + type_name + ";";
		}
	}
}
