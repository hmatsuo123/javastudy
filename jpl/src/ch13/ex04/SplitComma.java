package ch13.ex04;

public class SplitComma {
	static int splitSize = 3;

	public static String insertComma(String text) {
		String result = text;
		if (text == null)
			throw new NullPointerException();

		for (int i = splitSize; i < result.length(); i += splitSize + 1) {
			result = result.substring(0, i) + "," + result.substring(i);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(insertComma("123456789000000"));
	}
}
