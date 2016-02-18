package ch01.ex13;

public class ImprovedFibonacciPrintf {
	static final int MAX_INDEX = 9;

	/**
	 * 偶数要素に'*'をつけて、フィボナッチ数列の
	 * 最初のほうの要素を表示する
	 * */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;

		System.out.printf("1: %2d %n", lo);
		int index = 2;

		for(int i = MAX_INDEX; i >= 2; i--) {
			if (hi % 2 == 0)
				mark = " *";
			else
				mark = "";
			System.out.printf(index + ": " + "%2d" + mark + "%n", hi);
			hi = lo + hi;
			lo = hi - lo;
			index++;
		}
	}
}
