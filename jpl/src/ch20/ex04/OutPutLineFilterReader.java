package ch20.ex04;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class OutPutLineFilterReader extends FilterReader{
	public OutPutLineFilterReader(Reader in) {
		super(in);
	}

	public void printLine() throws IOException {
		int c;
		StringBuilder str = new StringBuilder();
		try {
			while ((c = read()) != -1) {
				str.append((char) c);
				if (c == System.getProperty("line.separator").charAt(0)) {
					System.out.println(str);
					str.setLength(0);
				}
			}
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
