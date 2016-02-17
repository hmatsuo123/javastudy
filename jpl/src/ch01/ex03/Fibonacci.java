package ch01.ex03;

public class Fibonacci {

	/** 値が50未満のフィボナッチ数列を表示する */
	public static void main(String[] args) {
		//タイトルを出力
		System.out.println("\"フィボナッチ数列\"");

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
