package ch16.ex04;

import java.lang.annotation.Annotation;

public class PrintAnnotation {
	@Deprecated
	public class TestClass extends TestSuperClass {
		public int count = 10;
		private final String text = "test";

		public TestClass() {
			super();
		}

		@Override
		public int getCount() {
			return count;
		}
	}

	public class TestSuperClass {
		public int count2 = 20;

		public int getCount() {
			return count2;
		}

		@Deprecated
		public void deprecatedMethod() {

		}
	}

	public static void main(String[] args) {
		try {
			Class<?> cls = Class.forName(args[0]);
			System.out.println(cls);
			for (Annotation annotation : cls.getAnnotations()) {
				System.out.println(annotation);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
