package ch22.ex10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IgnoreCommentScanner {
	public static void main(String[] args) {
		BufferedReader input = null;
		try {
			FileReader file = new FileReader("src/ch22/ex10/test.txt");
			input = new BufferedReader(file);
			List<String> result = new IgnoreCommentScanner().readLines(input);
			for (String line : result)
				System.out.println(line);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<String> readLines(Readable source) {
		Scanner in = null;
		List<String> vals = new ArrayList<String>();
		//String exp = "//.*\n";
		//Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		try {
			in = new Scanner(source);
			in.useDelimiter("//.*\n");
			while (in.hasNext()) {
				String line = in.next();
				if (line != null)
					vals.add(line);
			}
		} finally {
			in.close();
		}

		return vals;
	}
}
