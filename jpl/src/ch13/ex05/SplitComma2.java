package ch13.ex05;

public class SplitComma2 {

	public static String insertComma(String text, char targetChar, int splitSize) {
		String result = text;
		if (text == null)
			throw new NullPointerException();

		for (int i = splitSize; i < result.length(); i += splitSize + 1) {
			result = result.substring(0, i) + targetChar + result.substring(i);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(insertComma("123456789000000", ',', 2));
	}
}
