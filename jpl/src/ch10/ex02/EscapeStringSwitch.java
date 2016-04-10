package ch10.ex02;

public class EscapeStringSwitch {
	private String convert(String text) {
		String result = "";
		for (int i = 0; i < text.length(); i++) {
			char bufChar = text.charAt(i);

			switch (bufChar) {
			case '\n':
				result += "\\n";
				break;
	        case '\t':
	        	result += "\\t";
	        	break;
	        case '\b':
	        	result += "\\b";
	        	break;
	        case '\r':
	        	result += "\\r";
	        	break;
	        case '\f':
	        	result += "\\f";
	        	break;
	        case '\\':
	        	result += "\\\\";
	        	break;
	        case '\'':
	        	result += "\\'";
	        	break;
	        case '\"':
	        	result += "\\\"";
	        	break;
	        default:
	        	result += String.valueOf(bufChar);
	        	break;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String text = "hellow\\\b\t\f\'\"";
		System.out.println("origin: " + text);
		System.out.println("convert: " + new EscapeStringSwitch().convert(text));
	}
}
