package ch13.ex01;

public class SearchCharCount {
	public int charCount(String text, char ch) {
		if (text == null) {
			return 0;
		}

		int count = 0;
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == ch) {
				count++;
			}
		}

		return count;
	}
}
