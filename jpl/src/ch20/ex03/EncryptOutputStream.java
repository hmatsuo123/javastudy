package ch20.ex03;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream{
	private final int key = 256;

	public EncryptOutputStream(OutputStream out) {
		super(out);
	}

	@Override
	public void write(int data) throws IOException {
		int enccypted = data ^ key;
		super.write(enccypted);
	}
}
