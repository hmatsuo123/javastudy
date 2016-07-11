package ch20.ex02;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class TranslateByteFilterReader extends FilterReader {
	private final byte from;
	private final byte to;

	public TranslateByteFilterReader(Reader in, byte from, byte to) {
		super(in);
		this.from = from;
		this.to = to;
	}

	@Override
	public int read() throws IOException {
		int c = super.read();
		return (c == from ? to : c);
	}

	@Override
	public int read(char[] buf, int offset, int count) throws IOException {
		int nread = super.read(buf, offset, count);
		int last = offset + nread;
		for (int i = offset; i < last; i++)
			buf[i] = Character.toUpperCase(buf[i]);
		return nread;
	}
}
