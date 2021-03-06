package ch22.ex07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class ReadCsvTable {
	public static void main(String[] args) {
		BufferedReader input = null;
		try {
			FileReader file = new FileReader("src/ch22/ex07/test.csv");
			input = new BufferedReader(file);
			List<String[]> result = new ReadCsvTable().readCSVTable(input, 5);
			for (String[] values : result) {
				for (int i = 0; i < values.length; i++)
					System.out.print(values[i] + ", ");
				System.out.println();
			}
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

	public List<String[]> readCSVTable(Readable source, int cellCount) throws IOException {
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();

		String exp = "^(.*)";
		for (int i = 1; i < cellCount; i++)
			exp += ",(.*)";
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			String line = in.findInLine(pat);
			if (line != null) {
				String[] cells = new String[cellCount];
				MatchResult match = in.match();
				for (int i = 0; i < cells.length; ++i)
					cells[i] = match.group(i + 1);
				vals.add(cells);
				in.nextLine();
			} else {
				throw new IOException("input format error");
			}
		}

		IOException ex = in.ioException();
		if (ex != null)
			throw ex;

		return vals;
	}
}
