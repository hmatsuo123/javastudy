package ch09.ex01;

import static java.lang.Double.*;

public class Infinity {
	public static void main(String[] args) {
		System.out.println(POSITIVE_INFINITY + POSITIVE_INFINITY);
		System.out.println(POSITIVE_INFINITY - POSITIVE_INFINITY);
		System.out.println(POSITIVE_INFINITY * POSITIVE_INFINITY);
		System.out.println(POSITIVE_INFINITY / POSITIVE_INFINITY);

		System.out.println();
		System.out.println(NEGATIVE_INFINITY + NEGATIVE_INFINITY);
		System.out.println(NEGATIVE_INFINITY - NEGATIVE_INFINITY);
		System.out.println(NEGATIVE_INFINITY * NEGATIVE_INFINITY);
		System.out.println(NEGATIVE_INFINITY / NEGATIVE_INFINITY);

		System.out.println();
		System.out.println(POSITIVE_INFINITY + NEGATIVE_INFINITY);
		System.out.println(POSITIVE_INFINITY - NEGATIVE_INFINITY);
		System.out.println(POSITIVE_INFINITY * NEGATIVE_INFINITY);
		System.out.println(POSITIVE_INFINITY / NEGATIVE_INFINITY);
	}
}
