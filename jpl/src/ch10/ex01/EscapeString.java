package ch10.ex01;

public class EscapeString {
	private String convert(String text) {
		String result = "";
		for (int i = 0; i < text.length(); i++) {
			char bufChar = text.charAt(i);
			if (bufChar == '\n') {
				result += "\\n";
			} else if (bufChar == '\t') {
				result += "\\t";
			} else if (bufChar == '\b') {
				result += "\\b";
			} else if (bufChar == '\r') {
				result += "\\r";
			} else if (bufChar == '\f') {
				result += "\\f";
			} else if (bufChar == '\\') {
				result += "\\\\";
			} else if (bufChar == '\'') {
				result += "\\'";
			} else if (bufChar == '\"') {
				result += "\\\"";
			} else {
				result += String.valueOf(bufChar);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String text = "hellow\\\b\t\f\'\"";
		System.out.println("origin: " + text);
		System.out.println("convert: " + new EscapeString().convert(text));
	}
}
