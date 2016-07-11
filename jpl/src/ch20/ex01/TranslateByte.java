package ch20.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte {
	public static void main(String[] args) {
		try {
			TranslateByte.translate(new String[] {args[0], args[1]}, System.in, System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void translate(String[] input, InputStream in, OutputStream out) throws IOException {
		byte from = (byte) input[0].charAt(0);
		byte to = (byte) input[1].charAt(0);
		int b;
		while ((b = in.read()) != -1) {
			out.write(b == from ? to : b);
		}
	}
}
