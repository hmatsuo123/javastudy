package ch16.ex05;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClassContents {
	@Deprecated
	public class TestClass extends TestSuperClass {
		@Deprecated
		public int count = 10;
		private final String text = "test";

		public TestClass() {
			super();
		}

		@Deprecated
		public int getCount() {
			return count;
		}
	}

	public class TestSuperClass {
		public int count2 = 20;

		public int getCount() {
			return count2;
		}
	}

	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			printMembers(Field.class, c.getFields(), c.getDeclaredFields());
			printMembers(Constructor.class, c.getConstructors(), c.getDeclaredConstructors());
			printMembers(Method.class, c.getMethods(), c.getDeclaredMethods());
		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
	}

	private static void printMembers(Class<?> cls, Member[] mems, Member[] declaredMems) {
		List<String> list = new ArrayList<String>();
		for (Member m : declaredMems) {
			if (m.getDeclaringClass() == Object.class) {
				continue;
			}
			String decl = m.toString();
			System.out.print("  ");
			System.out.println(strip(decl, "java.lang."));
			printAnnotation(m, cls);
			list.add(decl);
		}
		for (Member m : mems) {
			if (m.getDeclaringClass() == Object.class || list.contains(m.toString())) {
				continue;
			}
			String decl = m.toString();
			System.out.print("  ");
			System.out.println(strip(decl, "java.lang."));
			printAnnotation(m, cls);
		}
	}

	private static void printAnnotation(Member mems, Class<?> cls) {
		// TODO:要検討
		if (cls.equals(Field.class)) {
			Field field = Field.class.cast(mems);
			for (Annotation annotation : field.getAnnotations()) {
				System.out.println("    " + annotation.toString());
			}
		} else if (cls.equals(Constructor.class)) {
			Constructor constructor = Constructor.class.cast(mems);
			for (Annotation annotation : constructor.getAnnotations()) {
				System.out.println("    " + annotation.toString());
			}
		} else if (cls.equals(Method.class)) {
			Method method = Method.class.cast(mems);
			for (Annotation annotation : method.getAnnotations()) {
				System.out.println("    " + annotation.toString());
			}
		}
	}

	private static String strip(String source, String delete) {
		return source.replaceAll(delete, "");
	}
}
