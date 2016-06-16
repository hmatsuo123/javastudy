package ch16.ex03;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class ClassContents {
	public class TestClass extends TestSuperClass {
		public int count = 10;
		private final String text = "test";

		public TestClass() {
			super();
		}

		public int getCount() {
			return count;
		}
	}

	public class TestSuperClass {
		public int count2 = 20;
	}

	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			printMembers(c.getFields(), c.getDeclaredFields());
			printMembers(c.getConstructors(), c.getDeclaredConstructors());
			printMembers(c.getMethods(), c.getDeclaredMethods());
		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
	}

	private static void printMembers(Member[] mems, Member[] declaredMems) {
		List<String> list = new ArrayList<String>();
		for (Member m : declaredMems) {
			if (m.getDeclaringClass() == Object.class) {
				continue;
			}
			String decl = m.toString();
			System.out.print("   ");
			System.out.println(strip(decl, "java.lang."));
			list.add(decl);
		}
		for (Member m : mems) {
			if (m.getDeclaringClass() == Object.class || list.contains(m.toString())) {
				continue;
			}
			String decl = m.toString();
			System.out.print("   ");
			System.out.println(strip(decl, "java.lang."));
		}
	}

	private static String strip(String source, String delete) {
		return source.replaceAll(delete, "");
	}
}
