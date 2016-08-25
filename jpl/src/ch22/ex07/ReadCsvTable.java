package ch22.ex07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

//TODO: 作成途中
public class ReadCsvTable {
	public List<String[]> readCSVTable(Readable source, int cellCount) throws IOException {
		if (cellCount < 1)
			throw new IllegalArgumentException();
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();

		try {
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
		} finally {
			in.close();
		}
		return vals;
	}
}
