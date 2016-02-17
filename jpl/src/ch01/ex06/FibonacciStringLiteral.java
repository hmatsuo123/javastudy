package ch01.ex06;

public class FibonacciStringLiteral {
	static final String title = "\"フィボナッチ数列\"";

	/** 値が50未満のフィボナッチ数列を表示する */
	public static void main(String[] args) {
		//タイトルを出力
		System.out.println(title);

		int lo = 1,
    	    hi = 1;
		System.out.println(lo);

		while(hi < 50) {
			System.out.println(hi);
			hi += lo;
			lo = hi - lo;
		}
	}
}
