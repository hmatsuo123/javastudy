package ch20.ex02;

import java.io.IOException;
import java.io.StringReader;

public class TranslateByte {
	public static void main(String[] args) {
		StringReader in = new StringReader(args[2]);
		TranslateByteFilterReader reader = new TranslateByteFilterReader(in, (byte) args[0].charAt(0), (byte) args[1].charAt(0));
		int c;
		try {
			while ((c = reader.read()) != -1) {
				System.out.print((char) c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}