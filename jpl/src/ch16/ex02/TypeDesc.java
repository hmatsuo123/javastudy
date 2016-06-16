package ch16.ex02;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TypeDesc {
	public class NestedClass extends Thread implements Runnable {
	}

	public static void main(String[] args) {
		TypeDesc desc = new TypeDesc();

		NestedClass nestedClass = desc.new NestedClass();
		Class<?> startClass = nestedClass.getClass();
		desc.printType(startClass, 0, basic);

		/*try {
			Class<?> startClass = Class.forName("java.util.Map$Entry");
			desc.printType(startClass, 0, basic);
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}*/
	}

	private java.io.PrintStream out = System.out;

	private static String[]
			basic = {"class", "interface", "enum", "annotation"},
			supercl = {"extends", "implements"},
			iFace = {null, "extends" };

	private void printType(Type type, int depth, String[] labels) {
		if (type == null || type.equals(Object.class)) {
			return;
		}

		// TypeをClassオブジェクトに変換する
		Class<?> cls = null;
		if (type instanceof Class<?>) {
			cls = (Class<?>) type;
		}
		else if (type instanceof ParameterizedType) {
			cls = (Class<?>) ((ParameterizedType) type).getRawType();
		}
		else {
			throw new Error("Unexpected non-class type");
		}

		// この型を表示
		for (int i = 0; i < depth; i++) {
			out.print("  ");
		}
		int kind = cls.isAnnotation() ? 3 :
			cls.isEnum() ? 2 :
				cls.isInterface() ? 1 : 0;
		out.print(labels[kind] + " ");
		out.print(cls.getCanonicalName());

		// あれば、ジェネリック型パラメータを表示
		TypeVariable<?>[] params = cls.getTypeParameters();
		if (params.length > 0) {
			out.print('<');
			for (TypeVariable<?> param : params) {
				out.print(param.getName());
				out.print(", ");
			}
			out.println("\b\b>");
		} else {
			out.println();
		}

		// ネストしたクラスか確認
		if (cls.isMemberClass()) {
			for (int i = 0; i < depth; i++) {
				out.print("  ");
			}
			out.println("エンクロージング型：" + cls.getEnclosingClass());
		}

		// このクラスが実装している全てのインタフェースを表示
		Type[] interfaces = cls.getGenericInterfaces();
		for (Type iface : interfaces) {
			printType(iface, depth + 1, cls.isInterface() ? iFace : supercl);
		}

		// スーパークラスに対して再帰
		printType(cls.getGenericSuperclass(), depth + 1, supercl);
	}
}
