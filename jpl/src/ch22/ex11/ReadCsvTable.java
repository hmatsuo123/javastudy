package ch22.ex11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class ReadCsvTable {
	public static void main(String[] args) {
		BufferedReader input = null;
		try {
			FileReader file = new FileReader("src/ch22/ex11/test.csv");
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

	public List<String[]> readCSVTable(Reader source, int nCells)
			throws IOException {
		if (nCells < 1)
			throw new IllegalArgumentException();
		StreamTokenizer in = new StreamTokenizer(source);
		in.ordinaryChar(',');
		in.eolIsSignificant(true);
		List<String[]> vals = new ArrayList<>();
		List<String> cells = new ArrayList<>();

		boolean eof = false;
		do {
			int token = in.nextToken();
			switch (token) {
			case StreamTokenizer.TT_EOF:
				eof = true;
				break;
			case StreamTokenizer.TT_EOL:
				vals.add(cells.toArray(new String[0]));
				cells = new ArrayList<>();
				break;
			case StreamTokenizer.TT_WORD:
				cells.add(in.sval);
				break;
			}
		} while (!eof);

		vals.add(cells.toArray(new String[0]));
		return vals;
	}
}
