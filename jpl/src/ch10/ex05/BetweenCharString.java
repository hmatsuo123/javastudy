package ch10.ex05;

public class BetweenCharString {
	public String getBetweenString(char start, char end) {
		if (start > end) {
			char buf = start;
			start = end;
			end = buf;
		}
		String result = "";
		for (char bufChar = start; bufChar <= end; bufChar++) {
			result += String.valueOf(bufChar);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(new BetweenCharString().getBetweenString('b', 'j'));
		System.out.println(new BetweenCharString().getBetweenString('さ', 'う'));
		System.out.println(new BetweenCharString().getBetweenString('松', '尾'));
	}
}
