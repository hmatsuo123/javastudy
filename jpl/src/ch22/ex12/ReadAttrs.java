package ch22.ex12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Scanner;

public class ReadAttrs {
	public static void main(String[] args) throws IOException {
		BufferedReader in = null;
		try {
			FileReader file = new FileReader("src/ch22/ex12/test.txt");
			in = new BufferedReader(file);
			Attributed<String> attrs = new ReadAttrs().readAttrs(in);
			Iterator<Attr<String>> iterator = attrs.attrs();
			while (iterator.hasNext())
				System.out.println(iterator.next());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				in.close();
		}
	}

	public Attributed<String> readAttrs(Reader source) {
		Attributed<String> attrs = new AttributedImpl();
		Scanner in = null;
		try {
			in = new Scanner(source);
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] kv = line.split("=");
				attrs.add(new Attr<>(kv[0], kv[1]));
			}
		} finally {
			if (in != null)
				in.close();
		}
		return attrs;
	}
}
