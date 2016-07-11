package ch20.ex03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream{
	private final int key = 256;

	public DecryptInputStream(InputStream in) {
		super(in);
	}

	@Override
	public int read() throws IOException {
		int in = super.read();
		if (in != -1) {
			in = in ^ key;
		}

		return in;
	}
}
