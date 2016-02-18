package ch01.ex02;

public class HellowWorldError {

	public static void main(String[] args) {
		/* 構文エラー */
		//System.out.println("Hellow, world")

		/* 型の不一致 */
		//int message = "Hellow, world";

		/* 全角スペース（トークン "Invalid Character" に構文エラーがあります。このトークンを削除してください） */
		//String　message = "Hellow, world";

		System.out.println("Hellow, world");

		/* voidメソッドは値を返せない */
		//return "hellow";
	}

}
