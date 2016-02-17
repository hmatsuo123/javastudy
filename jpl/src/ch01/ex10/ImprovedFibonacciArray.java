package ch01.ex10;

public class ImprovedFibonacciArray {
	static final int MAX_INDEX = 9;

	/**
	 * 偶数要素に'*'をつけて、フィボナッチ数列の
	 * 最初のほうの要素を表示する
	 * */
	public static void main(String[] args) {
		//フィボナッチ数列の値と値が偶数かのフラグを持つクラス
		class Fibonacci {
			public int value = 0;
			public boolean isEvenNumber = false;

			public Fibonacci setFibonacci(int value) {
				this.value = value;
				if (this.value % 2 == 0)
					this.isEvenNumber = true;
				else
					this.isEvenNumber = false;

				return this;
			}
		}

		int lo = 1;
		int hi = 1;
		Fibonacci[] fibonacciArray = new Fibonacci[MAX_INDEX];
		int count = 1;

		fibonacciArray[count - 1] = new Fibonacci().setFibonacci(lo);
		count++;

		for(int i = MAX_INDEX; i >= 2; i--) {
			fibonacciArray[count - 1] = new Fibonacci().setFibonacci(hi);
			hi = lo + hi;
			lo = hi - lo;
			count++;
		}

		int index = 1;
		for (Fibonacci fibonacci : fibonacciArray) {
			String mark;
			if (fibonacci.isEvenNumber)
				mark = " *";
			else
				mark = "";
			System.out.println(index + ": " + fibonacci.value + mark);
			index++;
		}
	}
}
