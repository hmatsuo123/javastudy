package ch23.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PlugClass implements Runnable{
	private InputStream in;
	private OutputStream out;

	public PlugClass(InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
	}

	@Override
	public void run() {
		int c;
		try {
			while ((c = in.read()) != -1)
				out.write(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
