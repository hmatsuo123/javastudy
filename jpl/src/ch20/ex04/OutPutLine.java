package ch20.ex04;

import java.io.IOException;
import java.io.StringReader;

public class OutPutLine {
	public static void main(String[] args) {
		//StringReader in = new StringReader(args[0]);
		StringReader in = new StringReader("matsuo\nhiroki");
		OutPutLineFilterReader reader = new OutPutLineFilterReader(in);
		try {
			reader.printLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
