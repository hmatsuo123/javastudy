package ch13.ex03;

import java.util.ArrayList;
import java.util.List;

public class DelimitedStringClass {
	public static List<String> delimitedString(String from, char start, char end) {
		int startPos = from.indexOf(start);
		int endPos = from.lastIndexOf(end);
		List<String> result = new ArrayList<>();

		if (startPos == -1) {
			// 開始文字が見つからない
			return null;
		} else if (endPos == -1) {
			// 終了文字が見つからない
			result.add(from.substring(startPos));
			return result;
		} else if (startPos > endPos) {
			// 開始文字が終了文字の後にある
			return null;
		} else {
			// 開始文字と終了文字が見つかった
			String bufStr = from;
	        while (true) {
	            startPos = bufStr.indexOf(start);
	            endPos = bufStr.indexOf(end);
	            if (startPos == -1 || endPos == -1) {
	            	break;
	            }
	            // 開始文字の前に終了文字がある場合は終了文字まで切り取る
	            if (startPos > endPos) {
	            	bufStr = bufStr.substring(endPos + 1);
	            	continue;
	            }
	            result.add(bufStr.substring(startPos, endPos + 1));
	            bufStr = bufStr.substring(endPos + 1);
	        }

	        return result;
		}
	}

	public static void main(String[] args) {
		System.out.println("orijinString: <matsuo>aaa><hiroki>");
        for (String s : delimitedString("<matsuo>aaa><hiroki>", '<', '>')) {
        	System.out.println(s);
        }
    }
}
