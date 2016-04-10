package ch10.ex04;

public class SquareNumber {
	public static void main(String[] args) {
		//タイトルを出力
		System.out.println("\"平方表\"");

		int i = 0;
		/*while (i < 10) {
			System.out.println(i * i);
			i++;
		}*/

		do {
			System.out.println(i * i);
			i++;
		} while (i < 10);
	}
}
