package ch21.ex01;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class OutPutLineFilterReader extends FilterReader{
	public OutPutLineFilterReader(Reader in) {
		super(in);
	}

	public List<String> printLine() throws IOException {
		int c;
		StringBuilder str = new StringBuilder();
		List<String> list = new ArrayList<String>();
		char _tmp = "\n".charAt(0);
		try {
			while ((c = read()) != -1) {
				str.append((char) c);
				// TODO:判定式に問題あり
				if (c == System.getProperty("line.separator").charAt(0)) {
					//System.out.println(str);
					list.add(str.toString());
					str.setLength(0);
				}
			}
			//System.out.println(str);
			list.add(str.toString());

			String[] array = (String[])list.toArray(new String[0]);
			Arrays.sort(array, new Comparator<String>() {
			  @Override
			    public int compare(String obj0, String obj1) {
			        return obj0.compareTo(obj1);
			    }
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
