package ch13.ex02;

public class SearchStringCount {
	public int stringCount(String text, String str) {
		if (text == null || str == null) {
			return 0;
		}

		if (text.length() < str.length()) {
			return 0;
		}

		int count = 0;
		for (int i = 0; i < text.length() - str.length() + 1; i++) {
			String targetString = text.substring(i, i + str.length());
			if (targetString.equals(str)) {
				count++;
			}
		}

		return count;

	}
}
