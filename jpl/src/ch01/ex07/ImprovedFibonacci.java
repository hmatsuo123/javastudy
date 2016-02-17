package ch01.ex07;

public class ImprovedFibonacci {
	static final int MAX_INDEX = 9;

	/**
	 * 偶数要素に'*'をつけて、フィボナッチ数列の
	 * 最初のほうの要素を表示する
	 * */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;

		System.out.println("1: " + lo);
		int index = 2;
		//TODO:マジックナンバー"2"がわかりにくい。1行目もfor文に入れたほうがよさそう
		for(int i = MAX_INDEX; i >= 2; i--) {
			if (hi % 2 == 0)
				mark = " *";
			else
				mark = "";
			System.out.println(index + ": " + hi + mark);
			hi = lo + hi;
			lo = hi - lo;
			index++;
		}
	}
}
