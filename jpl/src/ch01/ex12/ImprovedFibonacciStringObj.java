package ch01.ex12;

public class ImprovedFibonacciStringObj {
	static final int MAX_INDEX = 9;

	/**
	 * 偶数要素に'*'をつけて、フィボナッチ数列の
	 * 最初のほうの要素を表示する
	 * */
	public static void main(String[] args) {
		String[] fibonacciArray = new String[MAX_INDEX];
		int lo = 1;
		int hi = 1;
		String mark;
		int index = 1;

		fibonacciArray[index - 1] = index + ": " + lo;
		index++;

		for(int i = MAX_INDEX; i >= 2; i--) {
			if (hi % 2 == 0)
				mark = " *";
			else
				mark = "";
			fibonacciArray[index - 1] = index + ": " + hi + mark;
			hi = lo + hi;
			lo = hi - lo;
			index++;
		}

		for (String fibonacci : fibonacciArray) {
			System.out.println(fibonacci);
		}
	}
}
