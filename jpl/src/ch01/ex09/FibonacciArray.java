package ch01.ex09;

public class FibonacciArray {
	static final int MAX_INDEX = 9;

	/** 値が50未満のフィボナッチ数列を表示する */
	public static void main(String[] args) {
		int[] fibonacciArray = new int[MAX_INDEX];
		int index = 1;
		int lo = 1,
    	    hi = 1;

		fibonacciArray[index - 1] = lo;
		index++;

		while(hi < 50) {
			fibonacciArray[index - 1] = hi;
			hi += lo;
			lo = hi - lo;
			index++;
		}

		//タイトルを出力
		System.out.println("\"フィボナッチ数列\"");

		for (int value : fibonacciArray) {
			System.out.println(value);
		}
	}
}
